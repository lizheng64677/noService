package com.suyin.simple.test;


public class Test {

	public static void main(String[] args) {
			
         
         for(int a=0;a<6;a++){
             //生成8位随机说
        	 int i=(int)((Math.random()*9+1)*10000000);
        	 System.out.println(i);
         }
        
	}

	
}
