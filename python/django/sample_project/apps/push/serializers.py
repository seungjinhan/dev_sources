from rest_framework import serializers
from apps.push.models import Push, PushFailDetail, PushRe, PushContents

class PushContentsSerializer( serializers.ModelSerializer):
    
    
    class Meta:
        model = PushContents
        fields = '__all__'
    
class PushSerializer( serializers.ModelSerializer):
    
    def update(self, instance, validated_data):
        instance.no                 = validated_data.get('no', instance.no)
        instance.success_count      = validated_data.get('success_count', instance.success_count)
        instance.error_count        = validated_data.get('error_count', instance.error_count)
        instance.push_send_complete = validated_data.get('push_send_complete', instance.push_send_complete)
        instance.save()
        return instance
    
    class Meta:
        model = Push
        fields = '__all__'
        
class PushFailDetailSerializer( serializers.ModelSerializer):
    
    class Meta:
        model = PushFailDetail
        fields = '__all__'
        
class PushReSerializer( serializers.ModelSerializer):
    
    class Meta:
        model = PushRe
        fields = '__all__'
        
        
    