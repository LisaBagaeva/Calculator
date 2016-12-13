package com.liskin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.liskin.Calculator;

public class Main {

	public static void main(String[] args){
		File file = new File("Expressions.txt");
		// Pattern p = Pattern.compile("^[\\d|(]+[*+/-]+\\d+[\\d|)+]$");
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String expression = "";
			
			while ((expression = br.readLine()) != null){
			//	 Matcher m = p.matcher(expression);
			//	 System.out.println(p + " " + m.matches() + " " + expression);
		//	if(m.matches())
				System.out.println(expression +" = " + Calculator.calculateExpression(expression));

		}
		}
		catch (IOException ex){
			System.out.println(ex.getMessage());
		}
		
	}
}
