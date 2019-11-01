from os.path import join
import datetime
import os


DEBUG = True

ALLOWED_HOSTS = ['*']

now = datetime.datetime.now()

# 로그파일 폴더 생성
logfilepath = join( 'E:\\document\\dev\\log\\', str(now.year) , str(now.month))
try:
    if not( os.path.exists( logfilepath)):
        os.makedirs( logfilepath)
except OSError as e:
    print("fail to create log directory")
    raise
        
LOGGING = { 
    'version': 1,
    'disable_existing_loggers': False,
    
    'filters' :{
        'require_debug_true':{
            '()': 'django.utils.log.RequireDebugTrue',
        },
        'require_debug_false': {
            '()': 'django.utils.log.RequireDebugFalse',
        },
    },
        
    'formatters': {
        'verbose': {
            'format' : "[{asctime}s] {levelname} [{name}:{lineno}] {message}",
            'datefmt' : "%d/%b/%Y %H:%M:%S",
            'style': '{',            
        },
        'simple': {
            'format': '[{levelname}]|[{asctime}s]|[{levelname}]|[{name}:{lineno}]|[{message}]',
            'style': '{',
        },
    },

        
    'handlers': {
        
        'file': {
            
            'level': 'DEBUG',
            'class': 'logging.handlers.TimedRotatingFileHandler',       # https://docs.python.org/2/library/logging.handlers.html#timedrotatingfilehandler
            'filename': logfilepath +"\\" + 'console'+str(now.day)+"_"+ str(now.hour)+"_"+ str(now.minute)+"_"+str(now.second) +'.log',
            'when': 'D', # this specifies the interval
            'interval': 1, # defaults to 1, only necessary for other values 
            'backupCount': 10, # how many backup file to keep, 10 days
            'formatter': 'verbose',
        },
        'console': {
            'level': 'DEBUG',
            'filters': ['require_debug_true'],
            'class': 'logging.StreamHandler',
            'formatter': 'simple'
        },  
          

    },  
    'loggers': {
        '': {
            'handlers': ['file', 'console'],
            'level': 'DEBUG',
        },          
    },  
}
