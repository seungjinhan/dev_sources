package com.chunlab.app.token.engine;

class TokenEngingSupport {

	 static String machingNumberToChar1 = "AzXbGkPqRs";
	 static String machingNumberToChar2 = "QsFgTjxbNm";
	 static String machingNumberToChar3 = "ZxCvBnMjKl";
	 static String machingNumberToChar4 = "JhGfDsArTy";
	 static String machingNumberToChar5 = "TgBnHyUjMk";
	 static String machingNumberToChar6 = "DeFrGtHyJu";
	 static String machingNumberToChar7 = "PoLiJgDcSz";
	 static String machingNumberToChar8 = "RdHfGbVcXe";
	 static String machingNumberToChar9 = "OkJyGfDsAz";
    
	 final static String MAP_KEY_EMAIL = "email";
    
	 final static String CHANGE_STR1 = "_C_";
     final static String CHANGE_STR2 = "_B_";
     final static String CHANGE_STR3 = "_A_";
     final static String CHANGE_STR4 = "_D_";
     final static String CHANGE_STR5 = "_E_";
     final static String CHANGE_STR6 = "_F_";
     final static String CHANGE_STR7 = "_G_";
     final static String CHANGE_STR8 = "_H_";
     final static String CHANGE_STR9 = "_I_";
     final static String CHANGE_STR10 = "_J_";
     final static String CHANGE_STR11 = "_K_";
     final static String CHANGE_STR12 = "_L_";
     final static String CHANGE_STR13 = "_N_";
     final static String CHANGE_STR14 = "_M_";
     final static String CHANGE_STR15 = "_O_";
     final static String CHANGE_STR16 = "_P_";
     final static String CHANGE_STR17 = "_Q_";
     final static String CHANGE_STR18 = "_R_";
     final static String CHANGE_STR19 = "_S_";
     final static String CHANGE_STR20 = "_T_";
     final static String CHANGE_STR21 = "_U_";
    
     final static String CHANGE_STR_CONNECTOR = "_Z_";
    
	 final static int ACCESS_TOKEN_EXPIRE =  60;//Integer.MAX_VALUE; 	// Access token 만료시간 (분단위)
	 final static int REFRESH_TOKEN_EXPIRE = 30; // REFRESH token 만료시간 (일단위)

	
     static String specialStringEncoding( String str) {
    	
    	return str
    			.replace("/", CHANGE_STR1)
				.replace("%", CHANGE_STR2)
				.replace("#", CHANGE_STR3)
				.replace("+", CHANGE_STR4)
				.replace("&", CHANGE_STR5)
				.replace("?", CHANGE_STR6)
				.replace("*", CHANGE_STR7)
				.replace("$", CHANGE_STR8)
				.replace("|", CHANGE_STR9)
				.replace("`", CHANGE_STR10)
				.replace("^", CHANGE_STR11)
				.replace("(", CHANGE_STR12)
				.replace(")", CHANGE_STR13)
				.replace("<", CHANGE_STR14)
				.replace(">", CHANGE_STR15)
				.replace(";", CHANGE_STR16)
				.replace(":", CHANGE_STR17)
				.replace("\"", CHANGE_STR18)
				.replace("'", CHANGE_STR19)
				.replace("[", CHANGE_STR20)
				.replace("]", CHANGE_STR21)
				;
    }
    
     static String specialStringDecoding( String str) {
    
    	return str
    			.replace(CHANGE_STR21, "]")
    			.replace(CHANGE_STR20, "[")
    			.replace(CHANGE_STR19, "'")
    			.replace(CHANGE_STR18, "\"")
    			.replace(CHANGE_STR17, ":")
    			.replace(CHANGE_STR16, ";")
    			.replace(CHANGE_STR15, ">")
    			.replace(CHANGE_STR14, "<")
    			.replace(CHANGE_STR13, ")")
    			.replace(CHANGE_STR12, "(")
    			.replace(CHANGE_STR11, "^")
    			.replace(CHANGE_STR10, "`")
        		.replace(CHANGE_STR9, "|")
        		.replace(CHANGE_STR8, "$")
        		.replace(CHANGE_STR7, "*")
        		.replace(CHANGE_STR6, "?")
        		.replace(CHANGE_STR5, "&")
        		.replace(CHANGE_STR4, "+")
        		.replace(CHANGE_STR3, "#")
				.replace(CHANGE_STR2, "%")
				.replace(CHANGE_STR1, "/")
				;
    }
     
     
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
     static long decodingNumber( String data, int index) {
         
         String result = "";
         
         String mn = getMachingNumberData(index);
         
         for (int i = 0; i <  data.length(); i++) {
             
             result = result + mn.indexOf( data.charAt(i));
         }
         return Long.parseLong(result);
     }
}
