
FILE_UPLOAD_HANDLERS = [
        "django.core.files.uploadhandler.MemoryFileUploadHandler",
        "django.core.files.uploadhandler.TemporaryFileUploadHandler"]

REST_FRAMEWORK = {

    'DEFAULT_FILTER_BACKENDS': ['django_filters.rest_framework.DjangoFilterBackend'],
    
    'DEFAULT_PAGINATION_CLASS': 'rest_framework.pagination.LimitOffsetPagination',
    
    'PAGE_SIZE': 10,
}
