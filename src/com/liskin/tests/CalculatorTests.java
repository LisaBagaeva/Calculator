package com.liskin.tests;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import com.liskin.model.Calculator;

public class CalculatorTests {

	@Test(expected = IllegalArgumentException.class)
	public void testDoubleOperator() {
	String expression = new String("5**");
	Calculator.calculateExpression(expression);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testOpenBracket() {
	String expression = new String("(5+3+2*2");
	Calculator.calculateExpression(expression);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCloseBracket() {
	String expression = new String("5+3+2*2)");
	Calculator.calculateExpression(expression);
	}
	
	@Test()
	public void testCalculate—orrectly() {
	String expression = new String("2*(5+5)/10+1");
	Double expectedResult = new Double(3);
	Assert.assertEquals(expectedResult, Calculator.calculateExpression(expression));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testIncorrectSymbols() {
	String expression = new String("5+5&");
	Double expectedResult = new Double(3);
	Assert.assertEquals(expectedResult, Calculator.calculateExpression(expression));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testBrackets() {
	String expression = new String("(())");
	Calculator.calculateExpression(expression);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testMinOperands() {
	String expression = new String("5+5+");
	Calculator.calculateExpression(expression);
	}
	
}
