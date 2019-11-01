import logging

from pytilhan.exceptions.custom_exception import CustomException
from pytilhan.utils import network_util, date_util
from pytilhan.utils.json_util import string_to_json
from rest_framework.response import Response
from rest_framework.views import APIView

from apps.push.models import AppDevice, PushContents
from apps.push.serializers import PushSerializer, PushFailDetailSerializer



class HealthNotePushSenderView( APIView):
    
    # Health note Push전송처리
    def get(self , req):
        
        search_type = 'HEALTHNOTE_PUSH'

        # push 전송 시간        
        time = req.GET['time']
        
        # push contents를 조회
        contents = PushContents.objects.filter( type=search_type).first()
        
        # push 대상조회
        search_list = AppDevice.objects.filter( push_yn='Y')\
                                .filter( push_option__contains='"PUSH_YN":"Y"')\
                                .filter( push_option__contains='"PUSH_SEND_HOUR":'+time)
        
        logging.info("------ PUSH Send Start ------ total: "+ str(len(search_list))+"개")
        
        
        tokes = '' # push전송 대상 token들
        
        app_device_list = {}  # app device 리스트 -> fail 처리를 위해서 필요함
        
        index = 0
        
        for d in search_list:
            
            tokes =  tokes + ',' + d.push_token # 토큰 추가
            app_device_list[index] = d # app device 추가
            index = index + 1
        
        # Push를 보낼 param
        param = {
            "title" : contents.title,
            "message" : contents.message,
            "landing_page" : "Test landing",
            "tokens" : tokes[1:]
            }
        res = ''
        
        # push 정보를 DB에 저장하기 위한 param
        param_push = {
            "contents_no"           : contents.no,
            "reservation_time"      : time,
            "send_date"             : date_util.yyyymmddhhmmss(),
            "target_count"          : len(search_list),
            "landing_page"          : "landing_page...",
            "push_send_complete"    : "N"
        }
        
        #DB에 저장
        push_serializer = PushSerializer( data=param_push)
        push_obj = {}  
        if push_serializer.is_valid():
            push_obj = push_serializer.save()
        else:
            print( push_serializer.errors)
            return Response( push_serializer.errors)
        
        try :
            # push 전송 요청
            res = network_util.call_post("http://54.180.21.228:9003/push", param)
                                
        except CustomException as e:
            print( str(e))
            return Response( str(e))
        
        res_data = string_to_json(res)
        
        # Push 정상 전송후 성공 정보 저장
        param_push['push_send_complete'] = 'Y'        
        param_push['error_count'] = res_data['failureCount']
        param_push['success_count'] = res_data['successCount']        
        param_push['no'] = push_obj.no        
        
        push_serializer = PushSerializer(push_obj,  data=param_push)
        if push_serializer.is_valid():
            d = push_serializer.save()
        else:
            return Response( push_serializer.errors)
        
        # 실패 목록을 가져와서 업데이트 해야 함
        
        fail_list = []
        fail_error_data = {}
        
        result_index = 0;
        for d in res_data['result']:
            
            res_d = d['responses']
            
            for d2 in res_d:
                
                if d2['success'] == False: # 실패 인것
                    
                    app_device_vo = app_device_list[result_index] # 해당 index의 app device 가져오기
                    fail_list.append(app_device_vo) # app device 추가
                    fail_error_data[app_device_vo.no] = d2['error'] # error 정보 추가
            
                result_index = result_index + 1
        

        # fail 목록 저장        
        for fl in fail_list:
            f_param = {
                "app_device_no"     : fl.no,
                "contents_no"       : contents.no,
                "type"              : search_type,
                "push_token"        : fl.push_token,
                "fail_date"         : date_util.yyyymmddhhmmss(),
                "message"           : str(fail_error_data[ fl.no]).replace('\'','"'),
                "push_no"           : push_obj.no,
                "push_re_no"        : -1
            }
            
            push_fail_serializer = PushFailDetailSerializer( data = f_param)
            
            if push_fail_serializer.is_valid():
                
                d = push_fail_serializer.save()
            else:
                
                return Response( push_fail_serializer.errors)
        
        return Response( res_data)

                