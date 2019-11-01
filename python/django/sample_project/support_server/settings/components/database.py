import os
from support_server.settings import BASE_DIR, ENV
DATABASE_LIST = {
    'server': {
        'default': {
            'ENGINE'    :'django.db.backends.mysql',
            'NAME'      :'healthcare',
            'USER'      :'chunlab',
            'PASSWORD'  :'chunlab1234',
            'HOST'      :'localhost',
            'POST'      :'3306',  
            'OPTIONS'   : {
                'init_command': 'SET sql_mode="STRICT_TRANS_TABLES"'
            }        
        }
    },
    'dev': {
            'default': {
                 'ENGINE'    :'django.db.backends.mysql',
                 'NAME'      :'healthcare',
                 'USER'      :'chunlab',
                 'PASSWORD'  :'chunlab1234',
                 'HOST'      :'localhost',
                 'POST'      :'3306',  
                 'OPTIONS'   : {
                     'init_command': 'SET sql_mode="STRICT_TRANS_TABLES"'
            }
        }
    },
    'dev_sqlite': {
        'ENGINE': 'django.db.backends.sqlite3',
             'NAME': os.path.join(BASE_DIR, 'db.sqlite3-server'),
    }
}
    
DATABASES = DATABASE_LIST[ENV]
