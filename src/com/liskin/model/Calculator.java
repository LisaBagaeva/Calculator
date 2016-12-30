package com.liskin.model;

import java.util.Stack;
import java.util.StringTokenizer;

public class Calculator {
	private static final Integer MIN_OPERANDS;

	static {
		MIN_OPERANDS = 2;
	}

	private static String sortingStation(String expression) {
		String rpn = new String("");
		if (expression.matches("^.*[+-/*][+-/*].*$") || expression.matches("^.*[0-9]+[ )(]+[0-9].*$")
				|| expression.matches("^.*[0-9]+[ (]+[/*+-]+.*$") || expression.matches("^[ ]$")
				|| expression.matches("^$")) // Check
			// 5**
			throw new IllegalArgumentException("Illegal expression: " + expression);

		StringTokenizer exprMod = new StringTokenizer(expression, " (+/*-)", true);
		Stack<String> operations = new Stack<>();
		while (exprMod.hasMoreTokens()) {
			String token = exprMod.nextToken();
			if (token.equals(" "))
				continue;
			else if (token.equals("("))
				operations.push(token);

			else if (token.equals(")")) {
				if (operations.peek().equals("(") && rpn.isEmpty()) // check
					// //
					// ()
					throw new IllegalArgumentException("Illegal expression: Empty brackets: " + expression);

				while (!operations.peek().equals("(")) {
					rpn = rpn.concat(operations.pop() + " ");
					if (operations.empty()) // check open-bracket
						throw new IllegalArgumentException(
								"Illegal expression: Missing the open-bracket: " + expression);
				}
				operations.pop();
			}

			else if (!(token.matches("^[+-/*]$") || token.matches("^[0-9]+$"))) { // check
																					// incorrect
																					// symbol
				throw new IllegalArgumentException("Illegal expression: Invalid symbol: " + expression);
			}

			else if (Operations.contains(token.toCharArray()[0])) {
				if (!operations.empty() && !operations.peek().equals("("))
					while (operations.size() != 0 && !operations.peek().equals("(")
							&& Operations.getOperation(token.toCharArray()[0]).getPrioroty() <= Operations
									.getOperation(operations.peek().toCharArray()[0]).getPrioroty()) {
						rpn = rpn.concat(operations.pop() + " ");
					}
				operations.push(token);

			} else
				rpn = rpn.concat(token + " ");
		}
		while (!operations.empty()) {
			if (operations.contains("(")) // check close-bracket
				throw new IllegalArgumentException("Illegal expression: Missing the close-bracket: " + expression);
			rpn = rpn.concat(operations.pop() + " ");
		}
		return rpn;
	}

	public static Double calculateExpression(String expression) {
		String rpn = Calculator.sortingStation(expression);
		Stack<Double> operands = new Stack<>();
		StringTokenizer rpnMod = new StringTokenizer(rpn, " (+/*-)", true);
		while (rpnMod.hasMoreTokens()) {
			String token = rpnMod.nextToken();
			if (!token.equals(" ")) {
				if (!Operations.contains(token.toCharArray()[0])) {
					operands.push(new Double(token));
				} else {
					if (operands.size() < MIN_OPERANDS) // check 5* and 5+5+
						throw new IllegalArgumentException("Illegal expression: " + expression); // check
																									// 5*
					Double op2 = operands.pop();
					Double op1 = operands.pop();

					switch (token) {
					case "+":
						operands.push(Operations.ADDITION.calculate(op1, op2));
						break;

					case "-":
						operands.push(Operations.SUBSTRUCTION.calculate(op1, op2));
						break;
					case "*":
						operands.push(Operations.MULTIPLICATION.calculate(op1, op2));
						break;

					case "/":
						operands.push(Operations.DIVISION.calculate(op1, op2));
						break;
					}
				}
			}
		}
		return operands.peek();
	}
}