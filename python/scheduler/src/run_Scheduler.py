import time


from config import init
from workers import worker_mail_sender_queue, worker_logs_save_db,\
    log_file_move_daily
import logging
import schedule

def setting():
        
    logging.info("################################################")
    logging.info("App Scheduler")
    logging.info("################################################")
    

    logging.info("1. Email queue 메일 전송 - 1분단위로 호출")
    schedule.every(1).minutes.do(worker_mail_sender_queue.run)
    
    logging.info("2. Email queue 메일 전송 - 1초단위로 긴급 호출")
    schedule.every(1).seconds.do(worker_mail_sender_queue.run_emergency)
    
    logging.info("3. Log Data 저장 - 1분단위로 호출")
    schedule.every(1).minutes.do(worker_logs_save_db.run)
    
    logging.info("4. Log(App Server, Admin Server) file Daily 생성 - 매일 23시 50분에 실행")
    schedule.every().day.at("23:50").do( log_file_move_daily.run)
    
if __name__ ==  '__main__':
    
    init()
    setting()
    
    while True:
        schedule.run_pending()
        time.sleep(1)     


# 오늘의 실험리스트를 관리자에게 전송    
#schedule.every().day.at("18:00").do(lab_list_send_to_manager)

# 문법: https://schedule.readthedocs.io/en/stable/api.html

    
'''
# 10초에 한번씩 실행 schedule.every(10).second.do(job) 
# 10분에 한번씩 실행 schedule.every(10).minutes.do(job) 
# 매 시간 실행 schedule.every().hour.do(job) 
# 매일 10:30 에 실행 schedule.every().day.at("10:30").do(job) 
# 매주 월요일 실행 schedule.every().monday.do(job) 
# 매주 수요일 13:15 에 실행 schedule.every().wednesday.at("13:15").do(job) 
'''    
 