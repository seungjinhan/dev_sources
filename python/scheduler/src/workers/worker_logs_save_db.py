from utils import call


def run():
    
    url = "http://127.0.0.1:9001/logs/save_apicall.do"
    msg = "LOG 데이터 DB에 저장"
    call( url, msg)