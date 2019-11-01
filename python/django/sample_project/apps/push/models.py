from django.db import models

# Create your models here.

class AppDevice(models.Model):
    no = models.BigAutoField(primary_key=True)
    app_key = models.CharField(max_length=100)
    app_version = models.CharField(max_length=20)
    device_os = models.CharField(max_length=10)
    install_date = models.CharField(max_length=20)
    push_option = models.CharField(max_length=1000)
    push_token = models.CharField(max_length=200, blank=True, null=True)
    push_yn = models.CharField(max_length=5)
    update_date = models.CharField(max_length=20)
    user_no = models.BigIntegerField(unique=True)

    class Meta:
        managed = False
        db_table = 'app_device'

class Push(models.Model):
    no = models.BigAutoField(primary_key=True)
    contents_no = models.BigIntegerField()
    reservation_time = models.IntegerField()
    send_date = models.CharField(max_length=20)
    target_count = models.IntegerField()
    push_send_complete = models.CharField(max_length=5)
    error_count = models.IntegerField(blank=True, null=True)
    landing_page = models.CharField(max_length=1000)
    success_count = models.IntegerField(blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'push'


class PushContents(models.Model):
    no = models.BigAutoField(primary_key=True)
    message = models.CharField(max_length=2000)
    title = models.CharField(max_length=100)
    type = models.CharField(max_length=30)

    class Meta:
        managed = False
        db_table = 'push_contents'


class PushFailDetail(models.Model):
    no = models.BigAutoField(primary_key=True)
    app_device_no = models.BigIntegerField()
    contents_no = models.BigIntegerField()
    type = models.CharField(max_length=20)
    fail_date = models.CharField(max_length=20)
    message = models.CharField(max_length=200)
    push_no = models.BigIntegerField()
    push_re_no = models.BigIntegerField()
    push_token = models.CharField(max_length=200, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'push_fail_detail'


class PushRe(models.Model):
    no = models.BigAutoField(primary_key=True)
    contents_no = models.BigIntegerField(unique=True)
    error_count = models.IntegerField(unique=True)
    push_no = models.BigIntegerField(unique=True)
    push_send_complete = models.CharField(max_length=5)
    re_order = models.BigIntegerField(unique=True)
    send_date = models.CharField(max_length=20)
    success_count = models.IntegerField(unique=True)
    target_count = models.IntegerField(unique=True)

    class Meta:
        managed = False
        db_table = 'push_re'