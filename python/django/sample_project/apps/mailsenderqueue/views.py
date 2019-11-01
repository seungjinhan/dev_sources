from rest_framework.views import APIView
from apps.mailsenderqueue.serializers import MailSenderQueueSerializer
from rest_framework.response import Response
from apps.mailsenderqueue.models import EmailSenderQueue
from pytilhan.utils.mail_util import send_mail
from pytilhan.utils import date_util
import logging

def send_email( msq):
    
    logging.info("------ Email Send Start ------ total: "+ str(len(msq))+"개")
    index = 0
    for d in msq:
        logging.info("[" + str(index) + "]")
        logging.info('no: ' + str(d.no))
        logging.info('Email:' + d.to_email)
        logging.info('Subject:' + d.subject)
        logging.info('')            
        send_mail( d.to_email, d.subject, d.contents)
        EmailSenderQueue.objects.filter( no=d.no).update(is_send='Y',send_date=date_util.yyyymmddhhmmss() )
    data = MailSenderQueueSerializer( msq, many=True).data
    
    logging.info("------ Email Send End ------")
    
    return data


class MailSenderQueueView( APIView):
    
    def get(self, req):
        msq = EmailSenderQueue.objects.filter( is_send = 'N')
        data = send_email(msq);
        return Response( data)

class MailSenderEmergencyQueueView( APIView):
    
    def get(self, req):
        msq = EmailSenderQueue.objects.filter( is_emergency = 'Y')
        data = send_email(msq);
        return Response( data)