package com.liskin.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {

		File fileIn = new File("Expressions.txt");
		File fileOut = new File("Results.txt");
		BufferedWriter wr = new BufferedWriter(new FileWriter(fileOut));
		int index = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(fileIn))) {
			String expression = "";
			while ((expression = br.readLine()) != null) {
				try {
					wr.write(index + ") " + expression + " = " + Calculator.calculateExpression(expression) + "\r\n");
					index++;
					System.out.println(Calculator.calculateExpression(expression));

				} catch (IOException ex) {
					System.out.println(Calculator.calculateExpression(expression));
					System.out.println(ex.getMessage());
				}

				catch (IllegalArgumentException ex) {
					System.out.println(Calculator.calculateExpression(expression));
					wr.write(index + ") " + ex.getMessage() + "\r\n");
					index++;
				}
			}
			if (index == 0)
				throw new IllegalArgumentException("Illegal expression: expression is NULL");
			wr.close();
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}

	}
}