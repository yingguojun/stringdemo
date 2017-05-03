package com.huaxin.stringdemo;


import org.apache.commons.lang.StringUtils;

import junit.framework.TestCase;

public class StringDemoTest extends TestCase {
	
	public void testStringEqualsOne(){
		String a1 = "A";
	    String a3 =  new String("A");
	    assertTrue(a1==a3);
	}
	
	
	public void testStringEqualsTwo(){
		String a1 = "A";
	    String a3 =  new String("A");
	    assertEquals(a1,a3);
	}
	
	public void testStringEqualsThree(){
		String s1 = new String("abc");   
		String s2 = new String("abc");
		 assertTrue(s1==s2);
	}
	
	
	public void testStringNew(){
		
		char[] value = new char[]{'T','h','a','n','k','s'};
		String a5 = new String(value,1,3);
		a5.length();
		
		
		System.out.println(a5);
		
	}
	
	
    public void testSubString(){
		
		String s = "abcdefg";
		
		System.out.println(s.substring(0));
		System.out.println(s.substring(-1));
		
	}
    
    
   public void testStringReplace(){
		
		String s = "a6AazAaa77abaa";
				
		System.out.println(s.replace("aa", "foo"));
		
		
		StringBuffer s2 = new StringBuffer();
		
		s2.reverse();
		
		StringUtils su = new StringUtils();
		
		
		
		
		
		
		
	}
   
   
   public void stringFormat(){
	   
	   int maxLength = 4;
	   
	   int num =  111;
	   
	   String str = String.format("%0" + maxLength + "d", num);
	   
	   String str2=String.format("Hi,%s", "华信"); 
	   
	   System.out.println(str2);
   }
	
	
	

}
