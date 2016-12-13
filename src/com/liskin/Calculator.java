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
        int countBrackets =0;
    	boolean operationsIsExist = false;
		String rpn = "";
		StringTokenizer exprMod = new StringTokenizer(expression, " (+/*-)", true);
		Stack<String> operations = new Stack<>();
		while (exprMod.hasMoreTokens()) {
			String token = exprMod.nextToken();
			if (token.equals("(")){
				operations.push(token);
				countBrackets++;
			}
			else if (token.equals(")")) {
				countBrackets--;
				while (!operations.peek().equals("("))
					rpn = rpn.concat(operations.pop() + " ");
				operations.pop();
			} else if (PRIORITY.keySet().contains(token)) {
				operationsIsExist = true;
				if (!operations.empty() && !operations.peek().equals("("))
					if (PRIORITY.get(token) <= PRIORITY.get(operations.peek())) 
						rpn = rpn.concat(operations.pop() + " ");
	
				operations.push(token);
			} 
			else {
		      //  if(token.matches("//D"))
			   // throw new IllegalArgumentException("Illegal expression: " + expression);
				 rpn = rpn.concat(token + " ");
				
			}
		}
		while (!operations.empty())
			rpn = rpn.concat(operations.pop() + " ");
		
		if(countBrackets !=0)
		throw new IllegalArgumentException("Illegal expression: " + expression);
		
		if(!operationsIsExist)
			throw new IllegalArgumentException("Illegal expression: " + expression);

		return rpn;
	}

	public static Double calculateExpression(String expression){
		String rpn = Calculator.sortingStation(expression);
		Stack<Double> operands = new Stack<>();
		StringTokenizer rpnMod = new StringTokenizer(rpn, " (+/*-)", true);
		while (rpnMod.hasMoreTokens()) {
			String token = rpnMod.nextToken();
			if(!token.equals(" ")){
			if (!PRIORITY.keySet().contains(token)){
				operands.push(new Double(token));
			}
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
			
			}
		}
		}
		
		return  operands.peek();
	}

}
