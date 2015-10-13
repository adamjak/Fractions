/*
 * Copyright 2015 Tomas Adamjak - http://thomas.adamjak.net 
 * License: The BSD 3-Clause License
 */
package net.adamjak.thomas.fractions;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tomas Adamjak <thomas@adamjak.net>
 */
public class FractionTest
{
	/**
	 * Test of createFraction method, of class Fraction.
	 */
	@Test
	public void testCreateFraction_Integer_Integer()
	{
		Integer numerator = 4;
		Integer detominator = 6;
		Integer expResultNumerator = 2;
		Integer expResultDetominator = 3;
		Fraction result = Fraction.createFraction(numerator, detominator);
		assertEquals(expResultNumerator, result.getNumerator());
		assertEquals(expResultDetominator, result.getDenominator());
	}

	/**
	 * Test of createFraction method, of class Fraction.
	 */
	@Test
	public void testCreateFraction_Integer()
	{
		Integer numerator = 3;
		Integer expResultNumerator = 3;
		Integer expResultDetominator = 1;
		Fraction result = Fraction.createFraction(numerator);
		assertEquals(expResultNumerator, result.getNumerator());
		assertEquals(expResultDetominator, result.getDenominator());
	}

	/**
	 * Test of createFraction method, of class Fraction.
	 */
	@Test
	public void testCreateFraction_Long_Long()
	{
		Long numerator = 4L;
		Long detominator = 6L;
		Integer expResultNumerator = 2;
		Integer expResultDetominator = 3;
		Fraction result = Fraction.createFraction(numerator, detominator);
		assertEquals(expResultNumerator, result.getNumerator());
		assertEquals(expResultDetominator, result.getDenominator());
	}

	/**
	 * Test of createFraction method, of class Fraction.
	 */
	@Test
	public void testCreateFraction_Long()
	{
		Long numerator = 3L;
		Integer expResultNumerator = 3;
		Integer expResultDetominator = 1;
		Fraction result = Fraction.createFraction(numerator);
		assertEquals(expResultNumerator, result.getNumerator());
		assertEquals(expResultDetominator, result.getDenominator());
	}

	/**
	 * Test of createFraction method, of class Fraction.
	 */
	@Test
	public void testCreateFraction_Fraction()
	{
		Fraction fraction = Fraction.createFraction(4, 6);
		Integer expResultNumerator = 2;
		Integer expResultDetominator = 3;
		Fraction result = Fraction.createFraction(fraction);
		assertEquals(expResultNumerator, result.getNumerator());
		assertEquals(expResultDetominator, result.getDenominator());
	}

	/**
	 * Test of createFraction method, of class Fraction.
	 */
	@Test
	public void testCreateFraction_Double()
	{
		Double d = 0.75;
		Integer expResultNumerator = 3;
		Integer expResultDetominator = 4;
		Fraction result = Fraction.createFraction(d);
		assertEquals(expResultNumerator, result.getNumerator());
		assertEquals(expResultDetominator, result.getDenominator());
	}

	/**
	 * Test of add method, of class Fraction.
	 */
	@Test
	public void testAdd_Integer()
	{
		Integer number = 6;
		Fraction instance = Fraction.createFraction(4, 6);
		Fraction expResult = Fraction.createFraction(20, 3);
		Fraction result = instance.add(number);
		assertEquals(expResult, result);
	}

	/**
	 * Test of add method, of class Fraction.
	 */
	@Test
	public void testAdd_Fraction()
	{
		Fraction fraction = Fraction.createFraction(2, 3);
		Fraction instance = Fraction.createFraction(4, 5);
		Fraction expResult = Fraction.createFraction(22, 15);
		Fraction result = instance.add(fraction);
		assertEquals(expResult, result);
	}

	/**
	 * Test of add method, of class Fraction.
	 */
	@Test
	public void testAdd_Double()
	{
		Double d = 0.75;
		Fraction instance = Fraction.createFraction(1, 2);
		Fraction expResult = Fraction.createFraction(5, 4);
		Fraction result = instance.add(d);
		assertEquals(expResult, result);
	}

	/**
	 * Test of multiply method, of class Fraction.
	 */
	@Test
	public void testMultiply_Integer()
	{
		Integer number = 5;
		Fraction instance = Fraction.createFraction(2, 3);
		Fraction expResult = Fraction.createFraction(10, 3);
		Fraction result = instance.multiply(number);
		assertEquals(expResult, result);
	}

	/**
	 * Test of multiply method, of class Fraction.
	 */
	@Test
	public void testMultiply_Fraction()
	{
		Fraction fraction = Fraction.createFraction(2, 3);
		Fraction instance = Fraction.createFraction(4, 5);
		Fraction expResult = Fraction.createFraction(8, 15);
		Fraction result = instance.multiply(fraction);
		assertEquals(expResult, result);
	}

	/**
	 * Test of multiply method, of class Fraction.
	 */
	@Test
	public void testMultiply_Double()
	{
		Double d = 0.5;
		Fraction instance = Fraction.createFraction(2, 3);
		Fraction expResult = Fraction.createFraction(1, 3);
		Fraction result = instance.multiply(d);
		assertEquals(expResult, result);
	}

	/**
	 * Test of subtract method, of class Fraction.
	 */
	@Test
	public void testSubtract_Integer()
	{
		Integer number = 2;
		Fraction instance = Fraction.createFraction(7, 3);
		Fraction expResult = Fraction.createFraction(1, 3);
		Fraction result = instance.subtract(number);
		assertEquals(expResult, result);
	}

	/**
	 * Test of subtract method, of class Fraction.
	 */
	@Test
	public void testSubtract_Fraction()
	{
		Fraction fraction = Fraction.createFraction(8, 3);
		Fraction instance = Fraction.createFraction(10, 3);
		Fraction expResult = Fraction.createFraction(2, 3);
		Fraction result = instance.subtract(fraction);
		assertEquals(expResult, result);
	}

	/**
	 * Test of subtract method, of class Fraction.
	 */
	@Test
	public void testSubtract_Double()
	{
		Double d = 0.75;
		Fraction instance = Fraction.createFraction(5, 4);
		Fraction expResult = Fraction.createFraction(1, 2);
		Fraction result = instance.subtract(d);
		assertEquals(expResult, result);
	}

	/**
	 * Test of divide method, of class Fraction.
	 */
	@Test
	public void testDivide_Integer()
	{
		Integer number = 2;
		Fraction instance = Fraction.createFraction(2, 3);
		Fraction expResult = Fraction.createFraction(1, 3);
		Fraction result = instance.divide(number);
		assertEquals(expResult, result);
	}

	/**
	 * Test of divide method, of class Fraction.
	 */
	@Test
	public void testDivide_Fraction()
	{
		Fraction fraction = Fraction.createFraction(4, 5);
		Fraction instance = Fraction.createFraction(2, 3);
		Fraction expResult = Fraction.createFraction(5, 6);
		Fraction result = instance.divide(fraction);
		assertEquals(expResult, result);
	}

	/**
	 * Test of divide method, of class Fraction.
	 */
	@Test
	public void testDivide_Double()
	{
		Double d = 0.75;
		Fraction instance = Fraction.createFraction(5, 4);
		Fraction expResult = Fraction.createFraction(5, 3);
		Fraction result = instance.divide(d);
		assertEquals(expResult, result);
	}

	/**
	 * Test of max method, of class Fraction.
	 */
	@Test
	public void testMax()
	{
		Fraction f = Fraction.createFraction(1, 2);
		Fraction instance = Fraction.createFraction(2, 3);
		Fraction expResult = instance;
		Fraction result = instance.max(f);
		assertEquals(expResult, result);
	}

	/**
	 * Test of min method, of class Fraction.
	 */
	@Test
	public void testMin()
	{
		Fraction z = Fraction.createFraction(1, 2);
		Fraction instance = Fraction.createFraction(2, 3);
		Fraction expResult = z;
		Fraction result = instance.min(z);
		assertEquals(expResult, result);
	}

	/**
	 * Test of pow method, of class Fraction when exponent is greater that 1.
	 */
	@Test
	public void testPowPositive()
	{
		int exponent = 3;
		Fraction instance = Fraction.createFraction(2, 3);
		Fraction expResult = Fraction.createFraction(8, 27);
		Fraction result = instance.pow(exponent);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of pow method, of class Fraction when exponent is 1.
	 */
	@Test
	public void testPow1()
	{
		int exponent = 1;
		Fraction instance = Fraction.createFraction(2, 3);
		Fraction expResult = Fraction.createFraction(2, 3);
		Fraction result = instance.pow(exponent);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of pow method, of class Fraction when exponent is 0.
	 */
	@Test
	public void testPow0()
	{
		int exponent = 0;
		Fraction instance = Fraction.createFraction(2, 3);
		Fraction expResult = Fraction.createFraction(1, 1);
		Fraction result = instance.pow(exponent);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of pow method, of class Fraction when exponent is less that 0.
	 */
	@Test
	public void testPowNegative()
	{
		int exponent = -2;
		Fraction instance = Fraction.createFraction(2, 3);
		Fraction expResult = Fraction.createFraction(9, 4);
		Fraction result = instance.pow(exponent);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of reciprocal method, of class Fraction.
	 */
	@Test
	public void reciprocal()
	{
		Fraction instance = Fraction.createFraction(2, 3);
		Fraction expResult = Fraction.createFraction(3, 2);
		Fraction result = instance.reciprocal();
		assertEquals(expResult, result);
	}

	/**
	 * Test of random method, of class Fraction.
	 */
	@Test
	public void testRandom()
	{
		Fraction result = Fraction.random();
		assertNotNull(result);
	}

	/**
	 * Test of getNumerator method, of class Fraction.
	 */
	@Test
	public void testGetNumerator()
	{
		Fraction instance = Fraction.createFraction(4, 6);
		Integer expResult = 2;
		Integer result = instance.getNumerator();
		assertEquals(expResult, result);
	}

	/**
	 * Test of getDenominator method, of class Fraction.
	 */
	@Test
	public void testGetDetominator()
	{
		Fraction instance = Fraction.createFraction(4, 6);
		Integer expResult = 3;
		Integer result = instance.getDenominator();
		assertEquals(expResult, result);
	}

	/**
	 * Test of intValue method, of class Fraction.
	 */
	@Test
	public void testIntValue()
	{
		Fraction instance = Fraction.createFraction(5, 4);
		int expResult = 1;
		int result = instance.intValue();
		assertEquals(expResult, result);
	}

	/**
	 * Test of longValue method, of class Fraction.
	 */
	@Test
	public void testLongValue()
	{
		Fraction instance = Fraction.createFraction(5, 4);
		long expResult = 1L;
		long result = instance.longValue();
		assertEquals(expResult, result);
	}

	/**
	 * Test of floatValue method, of class Fraction.
	 */
	@Test
	public void testFloatValue()
	{
		Fraction instance = Fraction.createFraction(3, 4);
		float expResult = 0.75F;
		float result = instance.floatValue();
		assertEquals(expResult, result, 0.00000001);
	}

	/**
	 * Test of doubleValue method, of class Fraction.
	 */
	@Test
	public void testDoubleValue()
	{
		Fraction instance = Fraction.createFraction(5, 4);
		double expResult = 1.25;
		double result = instance.doubleValue();
		assertEquals(expResult, result, 0.00000001);
	}

	/**
	 * Test of compareTo method, of class Fraction when result is positive.
	 */
	@Test
	public void testCompareToPositive()
	{
		Fraction f = Fraction.createFraction(2, 3);
		Fraction instance = Fraction.createFraction(3, 4);
		int result = instance.compareTo(f);
		assertTrue(result > 0);
	}
	
	/**
	 * Test of compareTo method, of class Fraction when result is negative.
	 */
	@Test
	public void testCompareToNegative()
	{
		Fraction f = Fraction.createFraction(3, 4);
		Fraction instance = Fraction.createFraction(2, 3);
		int result = instance.compareTo(f);
		assertTrue(result < 0);
	}
	
	/**
	 * Test of compareTo method, of class Fraction when result is same.
	 */
	@Test
	public void testCompareToSame()
	{
		Fraction f = Fraction.createFraction(3, 4);
		Fraction instance = Fraction.createFraction(3, 4);
		int result = instance.compareTo(f);
		assertTrue(result == 0);
	}

	/**
	 * Test of equals method, of class Fraction.
	 */
	@Test
	public void testEquals()
	{
		Object o = Fraction.createFraction(2, 3);
		Fraction instance = Fraction.createFraction(4, 6);
		boolean expResult = true;
		boolean result = instance.equals(o);
		assertEquals(expResult, result);
	}

	/**
	 * Test of hashCode method, of class Fraction.
	 */
	@Test
	public void testHashCode()
	{
		Integer numerator = 2;
		Integer detominator = 3;
		
		Fraction instance = Fraction.createFraction(numerator, detominator);
		int expResult = (13 + numerator.hashCode()) * 13 + detominator.hashCode();
		int result = instance.hashCode();
		assertEquals(expResult, result);
	}

	/**
	 * Test of toString method, of class Fraction.
	 */
	@Test
	public void testToString()
	{
		Fraction instance = Fraction.createFraction(4, 6);
		String expResult = "2/3";
		String result = instance.toString();
		assertEquals(expResult, result);
	}
	
}
