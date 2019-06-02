package il.co.ilrd.ComplexNumber;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ComplexNumberTest {
	ComplexNumber value = null;
	ComplexNumber valuecst = null;
	@Before
	public void setUp() throws Exception {
		valuecst = new ComplexNumber(5, 5);
		value = ComplexNumber.parse("5 +5i");
	}

	@Test
	public void testComplexNumber() {
		assertTrue(valuecst != null);
		assertTrue(value != null);
	}
	
	@Test
	public void testGetReal() {
	    System.out.println(value);
	    System.out.println(valuecst);
		assertEquals(5, valuecst.getReal(),3);
	}

	@Test
	public void testGetImaginary() {
		assertEquals(5, valuecst.getImaginary(),3);
	}

	@Test
	public void testSetReal() {
		value.setReal(8);
		valuecst.setReal(8);
		assertEquals(value.getReal(), 8,3);
		assertEquals(value.getReal(), valuecst.getReal(),3);
	}

	@Test
	public void testSetImaginary() {
		value.setImaginary(8);
		valuecst.setImaginary(8);
		assertEquals(value.getImaginary(), 8,3);
		assertEquals(value.getImaginary(), valuecst.getImaginary(),3);
	}

	@Test
	public void testAdd() {
		value = value.add(valuecst);
		assertEquals(value.getImaginary(),10,3);
		assertEquals(value.getReal(), 10,3);
	}

	@Test
	public void testSubstract() {
		value = value.substract(valuecst);
		assertEquals(value.getImaginary(),0,3);
		assertEquals(value.getReal(), 0,3);
	}
	
	@Test
	public void testIsReal() {
		value = value.substract(new ComplexNumber(0, 5));
		System.out.println(value.getReal());
		assertTrue(value.isReal());
	}

	@Test
	public void testIsImaginary() {
		value = value.substract(new ComplexNumber(5, 0));
		assertTrue(value.isImaginary());
	}

	@Test
	public void testEqualsObject() {
		assertTrue(value.equals(valuecst));
	}

	@Test
	public void testCompareTo() {
		assertEquals(value.compareTo(valuecst), 0);
		assertEquals(value.compareTo(new ComplexNumber(6, 5)), -1);
		assertEquals(value.compareTo(new ComplexNumber(5, 6)), -1);
		assertEquals(value.compareTo(new ComplexNumber(4, 6)), 1);
		assertEquals(value.compareTo(new ComplexNumber(4, 2)), 1);
	}

}
