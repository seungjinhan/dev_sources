from pytilhan.utils import date_util, file_util

# 로그파일 이동처리
def __log_file_move( path):

    year = date_util.year()
    month = date_util.month()
    day = date_util.day()
    
    file_list = file_util.get_file_names_in_dir(path)
    file_list_for_move = [f for f in file_list if f.endswith('.log')==False]
    file_util.move_files(file_list_for_move, path, path+"/"+year+"/"+month+"/"+day)

# 로그파일 이동
def run():
    
    # app server
    path_app_server = '/usr/local/tools/logs/app_server'
    __log_file_move(path_app_server)
    
    # admin server
    path_admin_server = '/usr/local/tools/logs/admin_server'
    __log_file_move(path_admin_server)
    
    
    

    