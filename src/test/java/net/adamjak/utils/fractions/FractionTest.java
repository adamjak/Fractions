/*
 * Copyright 2015 Tomas Adamjak - http://thomas.adamjak.net 
 * License: The BSD 3-Clause License
 */
package net.adamjak.utils.fractions;

import java.text.ParseException;
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
	public void testCreateFraction_Number_Number()
	{
		Number numerator = 4;
		Number denominator = 6;
		Long expResultNumerator = 2L;
		Long expResultDenominator = 3L;
		Fraction result = Fraction.createFraction(numerator, denominator);
		assertEquals(expResultNumerator, result.getNumerator());
		assertEquals(expResultDenominator, result.getDenominator());
	}
	
	/**
	 * Test of createFraction method, of class Fraction. IllegalArgumentException test
	 */
	@Test(expected = NullPointerException.class)
	public void testCreateFraction_Number_Number_NullPointerException_Numerator()
	{
		Integer numerator = null;
		Integer denominator = 6;
		Fraction.createFraction(numerator, denominator);
	}
	
	/**
	 * Test of createFraction method, of class Fraction. IllegalArgumentException test
	 */
	@Test(expected = NullPointerException.class)
	public void testCreateFraction_Number_Number_NullPointerException_Denominator()
	{
		Integer numerator = 4;
		Integer denominator = null;
		Fraction.createFraction(numerator, denominator);
	}
	
	/**
	 * Test of createFraction method, of class Fraction. IllegalArgumentException test
	 */
	@Test(expected = ArithmeticException.class)
	public void testCreateFraction_Number_Number_ArithmeticException()
	{
		Integer numerator = 4;
		Integer denominator = 0;
		Fraction.createFraction(numerator, denominator);
	}

	/**
	 * Test of createFraction method, of class Fraction.
	 */
	@Test
	public void testCreateFraction_Number()
	{
		Integer numerator = 3;
		Long expResultNumerator = 3L;
		Long expResultDenominator = 1L;
		Fraction result = Fraction.createFraction(numerator);
		assertEquals(expResultNumerator, result.getNumerator());
		assertEquals(expResultDenominator, result.getDenominator());
	}
	
	/**
	 * Test of createFraction method, of class Fraction. IllegalArgumentException test
	 */
	@Test(expected = NullPointerException.class)
	public void testCreateFraction_Number_NullPointerException_Numerator()
	{
		Integer numerator = null;
		Fraction.createFraction(numerator);
	}

	/**
	 * Test of createFraction method, of class Fraction.
	 */
	@Test
	public void testCreateFraction_Fraction()
	{
		Fraction fraction = Fraction.createFraction(4, 6);
		Long expResultNumerator = 2L;
		Long expResultDenominator = 3L;
		Fraction result = Fraction.createFraction(fraction);
		assertEquals(expResultNumerator, result.getNumerator());
		assertEquals(expResultDenominator, result.getDenominator());
	}
	
	/**
	 * Test of createFraction method, of class Fraction. IllegalArgumentException test
	 */
	@Test(expected = NullPointerException.class)
	public void testCreateFraction_Fraction_NullPointerException()
	{
		Fraction fraction = null;
		Fraction.createFraction(fraction);
	}

	

	/**
	 * Test of add method, of class Fraction.
	 */
	@Test
	public void testAdd_Number()
	{
		Integer number = 6;
		Fraction instance = Fraction.createFraction(4, 6);
		Fraction expResult = Fraction.createFraction(20, 3);
		Fraction result = instance.add(number);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of exception in add method, of class Fraction.
	 */
	@Test(expected = NullPointerException.class)
	public void testAdd_Number_NullPointerException()
	{
		Integer nullInt = null;
		Fraction instance = Fraction.createFraction(2, 3);
		instance.add(nullInt);
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
	 * Test of exception in add method, of class Fraction.
	 */
	@Test(expected = NullPointerException.class)
	public void testAdd_Fraction_NullPointerException()
	{
		Fraction nullFrac = null;
		Fraction instance = Fraction.createFraction(2, 3);
		instance.add(nullFrac);
	}
	
	/**
	 * Test of multiply method, of class Fraction.
	 */
	@Test
	public void testMultiply_Number()
	{
		Integer number = 5;
		Fraction instance = Fraction.createFraction(2, 3);
		Fraction expResult = Fraction.createFraction(10, 3);
		Fraction result = instance.multiply(number);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of exception in multiply method, of class Fraction.
	 */
	@Test(expected = NullPointerException.class)
	public void testMultiply_Number_NullPointerException()
	{
		Integer nullInt = null;
		Fraction instance = Fraction.createFraction(2, 3);
		instance.multiply(nullInt);
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
	 * Test of exception in multiply method, of class Fraction.
	 */
	@Test(expected = NullPointerException.class)
	public void testMultiply_Fraction_NullPointerException()
	{
		Fraction nullFrac = null;
		Fraction instance = Fraction.createFraction(2, 3);
		instance.multiply(nullFrac);
	}

	/**
	 * Test of subtract method, of class Fraction.
	 */
	@Test
	public void testSubtract_Number()
	{
		Integer number = 2;
		Fraction instance = Fraction.createFraction(7, 3);
		Fraction expResult = Fraction.createFraction(1, 3);
		Fraction result = instance.subtract(number);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of exception in subtract method, of class Fraction.
	 */
	@Test(expected = NullPointerException.class)
	public void testSubtract_Number_NullPointerException()
	{
		Integer nullInt = null;
		Fraction instance = Fraction.createFraction(2, 3);
		instance.subtract(nullInt);
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
	 * Test of exception in subtract method, of class Fraction.
	 */
	@Test(expected = NullPointerException.class)
	public void testSubtract_Fraction_NullPointerException()
	{
		Fraction nullFrac = null;
		Fraction instance = Fraction.createFraction(2, 3);
		instance.subtract(nullFrac);
	}

	/**
	 * Test of divide method, of class Fraction.
	 */
	@Test
	public void testDivide_Number()
	{
		Integer number = 2;
		Fraction instance = Fraction.createFraction(2, 3);
		Fraction expResult = Fraction.createFraction(1, 3);
		Fraction result = instance.divide(number);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of exception in divide method, of class Fraction.
	 */
	@Test(expected = NullPointerException.class)
	public void testDivide_Number_NullPointerException()
	{
		Integer nullInt = null;
		Fraction instance = Fraction.createFraction(2, 3);
		instance.divide(nullInt);
	}
	
	/**
	 * Test of exception in divide method, of class Fraction.
	 */
	@Test(expected = ArithmeticException.class)
	public void testDivide_Integer_ArithmeticException()
	{
		Integer zeroInt = 0;
		Fraction instance = Fraction.createFraction(2, 3);
		instance.divide(zeroInt);
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
	 * Test of exception in divide method, of class Fraction.
	 */
	@Test(expected = NullPointerException.class)
	public void testDivide_Fraction_NullPointerException()
	{
		Fraction nullFrac = null;
		Fraction instance = Fraction.createFraction(2, 3);
		instance.divide(nullFrac);
	}
	
	/**
	 * Test of exception in divide method, of class Fraction.
	 */
	@Test(expected = ArithmeticException.class)
	public void testDivide_Fraction_ArithmeticException()
	{
		Fraction zeroFracNum = Fraction.createFraction(0, 2);
		Fraction instance = Fraction.createFraction(2, 3);
		instance.divide(zeroFracNum);
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
	 * Test of exception in max method of Fraction class.
	 */
	@Test(expected = NullPointerException.class)
	public void testMax_NullPointerException()
	{
		Fraction nullFrac = null;
		Fraction instance = Fraction.createFraction(2, 3);
		instance.max(nullFrac);
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
	 * Test of exception in min method of Fraction class.
	 */
	@Test(expected = NullPointerException.class)
	public void testMin_NullPointerException()
	{
		Fraction nullFrac = null;
		Fraction instance = Fraction.createFraction(2, 3);
		instance.min(nullFrac);
	}

	/**
	 * Test of pow method, of class Fraction when exponent is greater that 1.
	 */
	@Test
	public void testPowPositive()
	{
		Integer exponent = 3;
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
		Integer exponent = 1;
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
		Integer exponent = 0;
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
		Integer exponent = -2;
		Fraction instance = Fraction.createFraction(2, 3);
		Fraction expResult = Fraction.createFraction(9, 4);
		Fraction result = instance.pow(exponent);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of exception in pow method of Fraction class.
	 */
	@Test(expected = NullPointerException.class)
	public void testPow_NullPointerException()
	{
		Integer nullInt = null;
		Fraction instance = Fraction.createFraction(2, 3);
		instance.pow(nullInt);
	}
	
	/**
	 * Test of reciprocal method, of class Fraction.
	 */
	@Test
	public void testReciprocal()
	{
		Fraction instance = Fraction.createFraction(2, 3);
		Fraction expResult = Fraction.createFraction(3, 2);
		Fraction result = instance.reciprocal();
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of exception in reciprocal method, of class Fraction.
	 */
	@Test(expected = ArithmeticException.class)
	public void testReciprocal_ArithmeticException()
	{
		Fraction instance = Fraction.createFraction(0, 3);
		instance.reciprocal();
	}
	
	/**
	 * Test of inPercentage method, of class Fraction.
	 */
	@Test
	public void testInPercentage()
	{
		Fraction instance = Fraction.createFraction(1, 2);
		Double expResult = 50.0;
		Double result = instance.inPercentage();
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of log method, of class Fraction.
	 */
	@Test
	public void testLog()
	{
		Fraction instance = Fraction.createFraction(1, 3);
		Double expResult = -1.09861228866810969139524523692252570464749055782274;
		Double result = instance.log();
		assertEquals(expResult, result, 0.00000001);
	}
	
	/**
	 * Test of complement method, of class Fraction.
	 */
	@Test
	public void testComplement()
	{
		Fraction instance = Fraction.createFraction(3, 4);
		Fraction expResult = Fraction.createFraction(1, 4);
		Fraction result = instance.complement();
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
		Long expResult = 2L;
		Long result = instance.getNumerator();
		assertEquals(expResult, result);
	}

	/**
	 * Test of getDenominator method, of class Fraction.
	 */
	@Test
	public void testGetDenominator()
	{
		Fraction instance = Fraction.createFraction(4, 6);
		Long expResult = 3L;
		Long result = instance.getDenominator();
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of tryParse method, of class Fraction.
	 */
	@Test
	public void testTryParse_String_Mixed_Fraction() throws ParseException
	{
		String s = "1/2/3";
		Fraction expResult = Fraction.createFraction(5, 3);
		Fraction result = Fraction.tryParse(s);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of tryParse method, of class Fraction.
	 */
	@Test
	public void testTryParse_String_Fraction() throws ParseException
	{
		String s = "2/3";
		Fraction expResult = Fraction.createFraction(2, 3);
		Fraction result = Fraction.tryParse(s);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of tryParse method, of class Fraction.
	 */
	@Test
	public void testTryParse_String_Number() throws ParseException
	{
		String s = "3";
		Fraction expResult = Fraction.createFraction(3);
		Fraction result = Fraction.tryParse(s);
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of tryParse method, of class Fraction.
	 */
	@Test
	public void testTryParse_String_Character() throws ParseException
	{
		String s = "1:2:3";
		Fraction expResult = Fraction.createFraction(5, 3);
		Fraction result = Fraction.tryParse(s,':');
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of exception in tryParse method, of class Fraction.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testTryParse_String_Character_IllegalArgumentException_String_Null() throws ParseException
	{
		String s = null;
		Character c = '/';
		Fraction.tryParse(s,c);
	}
	
	/**
	 * Test of exception in tryParse method, of class Fraction.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testTryParse_String_Character_IllegalArgumentException_String_Empty() throws ParseException
	{
		String s = "";
		Character c = '/';
		Fraction.tryParse(s,c);
	}
	
	/**
	 * Test of exception in tryParse method, of class Fraction.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testTryParse_String_Character_IllegalArgumentException_Character_Null() throws ParseException
	{
		String s = "1/2";
		Character c = null;
		Fraction.tryParse(s,c);
	}
	
	/**
	 * Test of exception in tryParse method, of class Fraction.
	 */
	@Test(expected = ParseException.class)
	public void testTryParse_String_Character_ParseException_Too_Many_Separators() throws ParseException
	{
		String s = "1/2/3/4";
		Character c = '/';
		Fraction.tryParse(s,c);
	}
	
	/**
	 * Test of exception in tryParse method, of class Fraction.
	 */
	@Test(expected = ParseException.class)
	public void testTryParse_String_Character_ParseException_Wrong_Separator() throws ParseException
	{
		String s = "1:2";
		Character c = '/';
		Fraction.tryParse(s,c);
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
		Integer denominator = 3;
		
		Fraction instance = Fraction.createFraction(numerator, denominator);
		int expResult = (13 + numerator.hashCode()) * 13 + denominator.hashCode();
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
