# 스케쥴러 프로그램
 - 로그파일 처리
 - DB백업
 - MailQueue 메일 전송
 
## Source 설명 
1. Mysql File로 백업
------------------------------------------------------------------------------------------------------------------------------------
import time
import os
import logging

def database_backup():
    
    now = time.localtime()
    now_date = "%04d%02d%02d%02d%02d%02d" % (now.tm_year, now.tm_mon, now.tm_mday, now.tm_hour, now.tm_min, now.tm_sec)

    #운영
    os.system("mysqldump -u chunlab -pchunlabtest --max_allowed_packet=1024M --databases smilebiome > /home/chunlab/data/smilebiome_db_backup/smilebiome_"+now_date+".sql")
    logging.info('Complete DATABASE Backup...:' + now_date)
------------------------------------------------------------------------------------------------------------------------------------    

2. LOG file 날짜폴더별로 저장처리
------------------------------------------------------------------------------------------------------------------------------------    
# 로그파일 이동처리
def __log_file_move( path):

    year = date_util.year()
    month = date_util.month()
    day = date_util.day()
    
    file_list = file_util.get_file_names_in_dir(path)
    file_list_for_move = [f for f in file_list if f.endswith('.log')==False]
    file_util.move_files(file_list_for_move, path, path+"/"+year+"/"+month+"/"+day)

def run():
    
    # app server
    path_app_server = '/usr/local/tools/logs/app_server'
    __log_file_move(path_app_server)
    
    # admin server
    path_admin_server = '/usr/local/tools/logs/admin_server'
    __log_file_move(path_admin_server)
------------------------------------------------------------------------------------------------------------------------------------    


## 모듈설치
pip install schedule
pip install requests
pip install --upgrade git+https://github.com/seungjinhan/python_jimmy_util.git

## svn update 및 실행
서버의 해당 폴더에 가서
- cd /usr/local/tools/server/scheduler/scheduler/src
- svn update
- ps -ef|grep python
- kill
- nohup python run_Scheduler.py &
- ps -ef|grep python
- tail -f nohup
