package com.junittest.learn;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalculatorTest {
	private Calculator calculatorUnderTest;
	@BeforeEach
	void setUp() throws Exception {
		calculatorUnderTest = new Calculator();
	}

	@Test
	void testSubtract() {
		long result = 10 - 33;
		assertEquals(result, calculatorUnderTest.subtract(10,33));
	}

	@Test
	void testAdd() {
	
		long result = 10 + 33;
		
		assertEquals(result,calculatorUnderTest.add(10,33));
		
		
	}

}
