from rest_framework import serializers
from apps.mailsenderqueue.models import EmailSenderQueue

class MailSenderQueueSerializer( serializers.ModelSerializer):
    
    class Meta:
        model = EmailSenderQueue
        fields = '__all__'
    