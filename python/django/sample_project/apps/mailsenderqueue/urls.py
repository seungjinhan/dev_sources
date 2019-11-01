from django.urls import path
from rest_framework.urlpatterns import format_suffix_patterns
from apps.mailsenderqueue.views import MailSenderQueueView, MailSenderEmergencyQueueView 

urlpatterns = [
    path('send.run', MailSenderQueueView.as_view()),
    path('send_emergency.run', MailSenderEmergencyQueueView.as_view()),
]

urlpatterns = format_suffix_patterns(urlpatterns)