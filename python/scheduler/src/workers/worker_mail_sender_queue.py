from utils import call


def run():
    
    url = "http://localhost:9002/mailsenderqueue/send.run"
    msg = "메일 큐 데이터 전송"
    call( url, msg)
    
def run_emergency():
    
    url = "http://localhost:9002/mailsenderqueue/send_emergency.run"
    msg = "메일 큐 데이터 전송"
    call( url, msg)
    
    