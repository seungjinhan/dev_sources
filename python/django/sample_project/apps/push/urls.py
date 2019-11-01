from django.urls import path
from rest_framework.urlpatterns import format_suffix_patterns
from apps.push.views import HealthNotePushSenderView

urlpatterns = [
    path('send.run', HealthNotePushSenderView.as_view()),
]

urlpatterns = format_suffix_patterns(urlpatterns)