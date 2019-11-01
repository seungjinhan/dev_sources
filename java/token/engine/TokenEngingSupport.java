package com.chunlab.app.token.engine;

public class TokenEngingSupport {

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 /**
	  * 보안상 제거부분
	  */
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     
     
 	/**
 	  * @Method Name : getMachingNumberData
 	  * @작성일 : 2019. 9. 26.
 	  * @작성자 : seungjin.han
 	  * @변경이력 : 
 	  * @Method 설명 : 숫자 매핑 정보 반환
 	  * @param index
 	  * @return
 	 */
 	static String getMachingNumberData( int index) {
 	
 		String mn =  machingNumberToChar9;
         switch(index) {
         case 1:
         	mn=machingNumberToChar1;
         	break;
         case 2:
         	mn=machingNumberToChar2;
         	break;
         case 3:
         	mn=machingNumberToChar3;
         	break;
         case 4:
         	mn=machingNumberToChar4;
         	break;
         case 5:
         	mn=machingNumberToChar5;
         	break;
         case 6:
         	mn=machingNumberToChar6;
         	break;
         case 7:
         	mn=machingNumberToChar7;
         	break;
         case 8:
         	mn=machingNumberToChar8;
         	break;
         case 9:
         	mn=machingNumberToChar9;
         	break;
         }
         
         return mn;
 	}

 	/**
 	 * 
 	  * @Method Name : encodingNumber
 	  * @작성일 : 2019. 9. 26.
 	  * @작성자 : seungjin.han
 	  * @변경이력 : 
 	  * @Method 설명 : 숫자를 문자로 인코딩 처리
 	  * @param data
 	  * @param index
 	  * @return
 	 */
     static String encodingNumber( String data, int index) {
         
         String result = "";
         
         String mn = getMachingNumberData(index);
         
         for (int i = 0; i <  data.length(); i++) {
             
             result = result + mn.charAt( Integer.parseInt(""+data.charAt(i)));
         }
         return result;
     }
     

     /**
      * 
       * @Method Name : decodingNumber
       * @작성일 : 2019. 9. 26.
       * @작성자 : seungjin.han
       * @변경이력 : 
       * @Method 설명 : 문자를 숫자로 디코딩 처리
       * @param data
       * @param index
       * @return
      */
     static Long decodingNumber( String data, int index) {
         
         String result = "";
         
         String mn = getMachingNumberData(index);
         
         for (int i = 0; i <  data.length(); i++) {
             
             result = result + mn.indexOf( data.charAt(i));
         }
         return Long.parseLong(result);
     }
}
