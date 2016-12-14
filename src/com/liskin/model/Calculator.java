package com.liskin.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

public class Calculator {
	private static final Map<String, Integer> PRIORITY;
    private static final Integer MIN_OPERANDS;
    
    static
    {
    	MIN_OPERANDS = 2;
    }
	static {
		PRIORITY = new HashMap<String, Integer>();
		PRIORITY.put("*", 1);
		PRIORITY.put("/", 1);
		PRIORITY.put("+", 0);
		PRIORITY.put("-", 0);
	}

	private static String sortingStation(String expression) {
		String rpn = new String("");
		if (expression.matches("^.*[+-/*][+-/*].*$")) // Check 5**
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
				if (operations.isEmpty() || operations.peek().equals("(")) // check ()
					throw new IllegalArgumentException("Illegal expression: Empty brackets: " + expression);
				while (!operations.peek().equals("(")){
					rpn = rpn.concat(operations.pop() + " ");
					if(operations.empty()) //check open-bracket
						throw new IllegalArgumentException("Illegal expression: Missing the open-bracket: " + expression);
				}
				operations.pop();
			}

			else if (!(token.matches("^[+-/*]$") || token.matches("^[0-9]+$"))) { // check incorrect symbol
				throw new IllegalArgumentException("Illegal expression: Invalid symbol: " + expression);
			}

			else if (PRIORITY.keySet().contains(token)) {
				if (!operations.empty() && !operations.peek().equals("("))
					if (PRIORITY.get(token) <= PRIORITY.get(operations.peek()))
						rpn = rpn.concat(operations.pop() + " ");
				operations.push(token);

			} else {
				rpn = rpn.concat(token + " ");
			}
		}
		while (!operations.empty()) {
			if (operations.contains("(")) // check close-bracket
				throw new IllegalArgumentException("Illegal expression: Illegal expression: Missing the close-bracket: " + expression); 
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
				if (!PRIORITY.keySet().contains(token)) {
					operands.push(new Double(token));
				} else {
					if(operands.size()<MIN_OPERANDS) //check 5* and 5+5+
						throw new IllegalArgumentException("Illegal expression: " + expression); // check 5*
					Double op2 = operands.pop();
					Double op1 = operands.pop();
					if (token.equals("+"))
						operands.push(op1 + op2);
					if (token.equals("-"))
						operands.push(op1 - op2);
					if (token.equals("*"))
						operands.push(op1 * op2);
					if (token.equals("/"))
						operands.push(op1 / op2);

				}
			}
		}

		return operands.peek();
	}
}