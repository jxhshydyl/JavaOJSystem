package cn.superman.web.util;

import java.util.Random;

public class GetVerifyCode {
    public static String verifyCode(){
    	Random a = new Random();
    	String code = "";
    	for(int i=0;i<4;i++){
        	int n = a.nextInt(26)+65;
    		code = code + (char)n;
    	}
    	return code;
    }
}
