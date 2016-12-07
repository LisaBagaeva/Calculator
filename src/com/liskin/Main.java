package com.liskin;

public class Main {

	  public static void main(String[] args) {
		  for(String s:args){
			  System.out.print(s);  
		  System.out.println("  " + Calculator.calculateExpression(s));
	  }
}
}
