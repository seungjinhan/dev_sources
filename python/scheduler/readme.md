# 스케쥴러 프로그램<br>
 - 로그파일 처리<br>
 - DB백업<br>
 - MailQueue 메일 전송<br>
 <br>
 <br>
## Source 설명 <br>
1. Mysql File로 백업<br>
-----------------------------------------------------------------------------------------------------------------------------<br>
import time
import os
import logging

def database_backup():
    
    now = time.localtime()
    now_date = "%04d%02d%02d%02d%02d%02d" % (now.tm_year, now.tm_mon, now.tm_mday, now.tm_hour, now.tm_min, now.tm_sec)

    #운영
    os.system("mysqldump -u chunlab -pchunlabtest --max_allowed_packet=1024M --databases smilebiome > /home/chunlab/data/smilebiome_db_backup/smilebiome_"+now_date+".sql")
    logging.info('Complete DATABASE Backup...:' + now_date)
---------------------------------------------------------------------------------------------------------------   <br> 

<br>
<br>

2. LOG file 날짜폴더별로 저장처리<br>
-----------------------------------------------------------------------------------------------------------------    <br>
## 로그파일 이동처리<br>
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
<br>
<br>

## 모듈설치<br>
pip install schedule <br> 
pip install requests <br>
pip install --upgrade git+https://github.com/seungjinhan/python_jimmy_util.git<br>
<br>
<br>
## svn update 및 실행<br>
서버의 해당 폴더에 가서<br>
- cd /usr/local/tools/server/scheduler/scheduler/src<br>
- svn update<br>
- ps -ef|grep python<br>
- kill<br>
- nohup python run_Scheduler.py &<br>
- ps -ef|grep python<br>
- tail -f nohup<br>
