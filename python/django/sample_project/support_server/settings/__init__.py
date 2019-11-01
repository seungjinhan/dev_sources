import os
import glob
import json
from split_settings.tools import include
from support_server.settings.components.app import PROJECT_APPS

with open("../../server_info.json", mode="r", encoding="utf-8") as f:
    file_data = f.read()

system_global_data = json.loads(file_data)
s_type = system_global_data['type']

BASE_DIR = os.path.dirname(
    os.path.dirname(
        os.path.dirname(
            os.path.abspath(__file__)
        )
    )
)

SECRET_KEY = 'n-!sf59+mhj4=km7cw2cl^-$=ew!yk@&r56wj0z&eyx%xpnagj'

ENV = os.environ.get('PROJECT_ENV', s_type)

COMPONENTS_DIR = os.path.join(
    BASE_DIR,
    'support_server',
    'settings',
    'components'
)

COMPONENTS = [
    'components/{}'.format(os.path.basename(component))
    for component in glob.glob(os.path.join(COMPONENTS_DIR, '*.py'))
]
ENVIRONMENTS = ['environments/{}.py'.format(ENV)]
PROJECT_APPS_SETTINGS = []

for apps_name in PROJECT_APPS:
    PROJECT_APPS_SETTINGS.append(os.path.join(BASE_DIR, apps_name.replace("." , "/"), '__init__.py'))

SETTINGS = COMPONENTS + ENVIRONMENTS + PROJECT_APPS_SETTINGS

include(*SETTINGS)
