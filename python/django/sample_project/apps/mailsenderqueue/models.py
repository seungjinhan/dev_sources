from django.db import models

# Create your models here.
class EmailSenderQueue(models.Model):
    no = models.BigAutoField(primary_key=True)
    contents = models.TextField(blank=True, null=True)
    description = models.CharField(max_length=1000, blank=True, null=True)
    in_date = models.CharField(max_length=20, blank=True, null=True)
    is_send = models.CharField(max_length=5, blank=True, null=True)
    send_date = models.CharField(max_length=20, blank=True, null=True)
    subject = models.CharField(max_length=50)
    to_email = models.CharField(max_length=50)
    is_emergency = models.CharField(max_length=5, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'email_sender_queue'