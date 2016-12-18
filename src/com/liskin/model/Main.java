package com.liskin.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		File file = new File("Expressions.txt");
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String expression = "";

			while ((expression = br.readLine()) != null) {
				System.out.println(expression + " = " + Calculator.calculateExpression(expression));

			}
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
//		String expression ="1+(+";
//		if (expression.matches("^.*[0-9]+[ )(]+[0-9].*$"))
//			System.out.println("dfdf");
	}
}