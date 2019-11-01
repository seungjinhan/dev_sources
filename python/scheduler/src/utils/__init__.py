import time
import logging
import requests


def call( url, msg):

    now = time.localtime()
    now_date = "%04d%02d%02d%02d%02d%02d" % (now.tm_year, now.tm_mon, now.tm_mday, now.tm_hour, now.tm_min, now.tm_sec)

    logging.info("Call: " + url  +" [" +  now_date +"]")
    
    try :
        res = requests.get(url)
        if res.status_code != 200:
            logging.info("실패:")
        else:
            logging.info(res.content)
    except:
        logging.info("호출 실패")
