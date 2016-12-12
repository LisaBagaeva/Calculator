package com.liskin;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

public class Calculator {

	private static final Map<String, Integer> PRIORITY;
	static {
		PRIORITY = new HashMap<String, Integer>();
		PRIORITY.put("*", 1);
		PRIORITY.put("/", 1);
		PRIORITY.put("+", 0);
		PRIORITY.put("-", 0);
	}

	private static String sortingStation(String expression) {
		String rpn = "";
		StringTokenizer exprMod = new StringTokenizer(expression, " ");
		Stack<String> operations = new Stack<>();
		while (exprMod.hasMoreTokens()) {
			String token = exprMod.nextToken();
			if (token.equals("("))
				operations.push(token);
			else if (token.equals(")")) {
				while (!operations.peek().equals("("))
					rpn = rpn.concat(operations.pop() + " ");
				operations.pop();
			} else if (PRIORITY.keySet().contains(token)) {
				if (!operations.empty() && !operations.peek().equals("("))
					if (PRIORITY.get(token) <= PRIORITY.get(operations.peek())) 
						rpn = rpn.concat(operations.pop() + " ");
	
				operations.push(token);
			} else
				rpn = rpn.concat(token + " ");
		}
		while (!operations.empty())
			rpn = rpn.concat(operations.pop() + " ");

		return rpn;
	}

	public static Double calculateExpression(String expression){
		String rpn = Calculator.sortingStation(expression);
		Stack<Double> operands = new Stack<>();
		StringTokenizer rpnMod = new StringTokenizer(rpn, " ");
		while (rpnMod.hasMoreTokens()) {
			String token = rpnMod.nextToken();
			if (!PRIORITY.keySet().contains(token))
				operands.push(new Double(token));
			else {
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
			//	throw new Exception("Illegal operator: " + token);
			}
		}

		return operands.peek();
	}

}
