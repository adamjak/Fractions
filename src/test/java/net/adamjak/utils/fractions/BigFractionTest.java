package net.adamjak.utils.fractions;

import org.junit.Test;

import java.math.BigInteger;
import java.text.ParseException;

import static org.junit.Assert.*;

/**
 * Created by Tomas Adamjak on 22.1.2016.
 * Copyright 2016, Tomas Adamjak
 * License: The BSD 3-Clause License
 */
public class BigFractionTest
{
	@Test
	public void testCreateBigFraction_Number_Number()
	{
		Number numerator = 4;
		Number denominator = 6;
		BigInteger expResultNumerator = BigInteger.valueOf(2);
		BigInteger expResultDenominator = BigInteger.valueOf(3);
		BigFraction result = BigFraction.createFraction(numerator, denominator);
		assertEquals(expResultNumerator, result.getNumerator());
		assertEquals(expResultDenominator, result.getDenominator());
	}

	@Test(expected = NullPointerException.class)
	public void testCreateFraction_Number_Number_NullPointerException_Numerator()
	{
		Integer numerator = null;
		Integer denominator = 6;
		BigFraction.createFraction(numerator, denominator);
	}

	@Test(expected = NullPointerException.class)
	public void testCreateFraction_Number_Number_NullPointerException_Denominator()
	{
		Integer numerator = 4;
		Integer denominator = null;
		BigFraction.createFraction(numerator, denominator);
	}

	@Test(expected = ArithmeticException.class)
	public void testCreateFraction_Number_Number_ArithmeticException()
	{
		Integer numerator = 4;
		Integer denominator = 0;
		BigFraction.createFraction(numerator, denominator);
	}

	@Test
	public void testCreateFraction_Number()
	{
		Integer numerator = 3;
		BigInteger expResultNumerator = BigInteger.valueOf(3L);
		BigInteger expResultDenominator = BigInteger.valueOf(1L);
		BigFraction result = BigFraction.createFraction(numerator);
		assertEquals(expResultNumerator, result.getNumerator());
		assertEquals(expResultDenominator, result.getDenominator());
	}

	@Test(expected = NullPointerException.class)
	public void testCreateFraction_Number_NullPointerException_Numerator()
	{
		Integer numerator = null;
		BigFraction.createFraction(numerator);
	}

	@Test
	public void testCreateFraction_Fraction()
	{
		BigFraction fraction = BigFraction.createFraction(4, 6);
		BigInteger expResultNumerator = BigInteger.valueOf(2L);
		BigInteger expResultDenominator = BigInteger.valueOf(3L);
		BigFraction result = BigFraction.createFraction(fraction);
		assertEquals(expResultNumerator, result.getNumerator());
		assertEquals(expResultDenominator, result.getDenominator());
	}

	@Test(expected = NullPointerException.class)
	public void testCreateFraction_Fraction_NullPointerException()
	{
		BigFraction fraction = null;
		BigFraction.createFraction(fraction);
	}

	@Test
	public void testToFraction()
	{
		BigFraction fraction = BigFraction.createFraction(1,2);
		Fraction expResult = Fraction.createFraction(1,2);
		Fraction result = fraction.toFraction();
		assertEquals(expResult,result);
	}

	@Test
	public void testAdd_Number()
	{
		Integer number = 6;
		BigFraction instance = BigFraction.createFraction(4, 6);
		BigFraction expResult = BigFraction.createFraction(20, 3);
		BigFraction result = instance.add(number);
		assertEquals(expResult, result);
	}

	@Test(expected = NullPointerException.class)
	public void testAdd_Number_NullPointerException()
	{
		Integer nullInt = null;
		BigFraction instance = BigFraction.createFraction(2, 3);
		instance.add(nullInt);
	}

	@Test
	public void testAdd_Fraction()
	{
		BigFraction fraction = BigFraction.createFraction(2, 3);
		BigFraction instance = BigFraction.createFraction(4, 5);
		BigFraction expResult = BigFraction.createFraction(22, 15);
		BigFraction result = instance.add(fraction);
		assertEquals(expResult, result);
	}

	@Test(expected = NullPointerException.class)
	public void testAdd_Fraction_NullPointerException()
	{
		BigFraction nullFrac = null;
		BigFraction instance = BigFraction.createFraction(2, 3);
		instance.add(nullFrac);
	}

	@Test
	public void testMultiply_Number()
	{
		Integer number = 5;
		BigFraction instance = BigFraction.createFraction(2, 3);
		BigFraction expResult = BigFraction.createFraction(10, 3);
		BigFraction result = instance.multiply(number);
		assertEquals(expResult, result);
	}

	@Test(expected = NullPointerException.class)
	public void testMultiply_Number_NullPointerException()
	{
		Integer nullInt = null;
		BigFraction instance = BigFraction.createFraction(2, 3);
		instance.multiply(nullInt);
	}

	@Test
	public void testMultiply_Fraction()
	{
		BigFraction fraction = BigFraction.createFraction(2, 3);
		BigFraction instance = BigFraction.createFraction(4, 5);
		BigFraction expResult = BigFraction.createFraction(8, 15);
		BigFraction result = instance.multiply(fraction);
		assertEquals(expResult, result);
	}

	@Test(expected = NullPointerException.class)
	public void testMultiply_Fraction_NullPointerException()
	{
		BigFraction nullFrac = null;
		BigFraction instance = BigFraction.createFraction(2, 3);
		instance.multiply(nullFrac);
	}

	@Test
	public void testSubtract_Number()
	{
		Integer number = 2;
		BigFraction instance = BigFraction.createFraction(7, 3);
		BigFraction expResult = BigFraction.createFraction(1, 3);
		BigFraction result = instance.subtract(number);
		assertEquals(expResult, result);
	}

	@Test(expected = NullPointerException.class)
	public void testSubtract_Number_NullPointerException()
	{
		Integer nullInt = null;
		BigFraction instance = BigFraction.createFraction(2, 3);
		instance.subtract(nullInt);
	}

	@Test
	public void testSubtract_Fraction()
	{
		BigFraction fraction = BigFraction.createFraction(8, 3);
		BigFraction instance = BigFraction.createFraction(10, 3);
		BigFraction expResult = BigFraction.createFraction(2, 3);
		BigFraction result = instance.subtract(fraction);
		assertEquals(expResult, result);
	}

	@Test(expected = NullPointerException.class)
	public void testSubtract_Fraction_NullPointerException()
	{
		BigFraction nullFrac = null;
		BigFraction instance = BigFraction.createFraction(2, 3);
		instance.subtract(nullFrac);
	}

	@Test
	public void testDivide_Number()
	{
		Integer number = 2;
		BigFraction instance = BigFraction.createFraction(2, 3);
		BigFraction expResult = BigFraction.createFraction(1, 3);
		BigFraction result = instance.divide(number);
		assertEquals(expResult, result);
	}

	@Test(expected = NullPointerException.class)
	public void testDivide_Number_NullPointerException()
	{
		Integer nullInt = null;
		BigFraction instance = BigFraction.createFraction(2, 3);
		instance.divide(nullInt);
	}

	@Test(expected = ArithmeticException.class)
	public void testDivide_Integer_ArithmeticException()
	{
		Integer zeroInt = 0;
		BigFraction instance = BigFraction.createFraction(2, 3);
		instance.divide(zeroInt);
	}

	@Test
	public void testDivide_Fraction()
	{
		BigFraction fraction = BigFraction.createFraction(4, 5);
		BigFraction instance = BigFraction.createFraction(2, 3);
		BigFraction expResult = BigFraction.createFraction(5, 6);
		BigFraction result = instance.divide(fraction);
		assertEquals(expResult, result);
	}

	@Test(expected = NullPointerException.class)
	public void testDivide_Fraction_NullPointerException()
	{
		BigFraction nullFrac = null;
		BigFraction instance = BigFraction.createFraction(2, 3);
		instance.divide(nullFrac);
	}

	@Test(expected = ArithmeticException.class)
	public void testDivide_Fraction_ArithmeticException()
	{
		BigFraction zeroFracNum = BigFraction.createFraction(0, 2);
		BigFraction instance = BigFraction.createFraction(2, 3);
		instance.divide(zeroFracNum);
	}

	@Test
	public void testMax()
	{
		BigFraction f = BigFraction.createFraction(1, 2);
		BigFraction instance = BigFraction.createFraction(2, 3);
		BigFraction expResult = instance;
		BigFraction result = instance.max(f);
		assertEquals(expResult, result);
	}

	@Test(expected = NullPointerException.class)
	public void testMax_NullPointerException()
	{
		BigFraction nullFrac = null;
		BigFraction instance = BigFraction.createFraction(2, 3);
		instance.max(nullFrac);
	}

	@Test
	public void testMin()
	{
		BigFraction z = BigFraction.createFraction(1, 2);
		BigFraction instance = BigFraction.createFraction(2, 3);
		BigFraction expResult = z;
		BigFraction result = instance.min(z);
		assertEquals(expResult, result);
	}

	@Test(expected = NullPointerException.class)
	public void testMin_NullPointerException()
	{
		BigFraction nullFrac = null;
		BigFraction instance = BigFraction.createFraction(2, 3);
		instance.min(nullFrac);
	}

	@Test
	public void testPowPositive()
	{
		Integer exponent = 3;
		BigFraction instance = BigFraction.createFraction(2, 3);
		BigFraction expResult = BigFraction.createFraction(8, 27);
		BigFraction result = instance.pow(exponent);
		assertEquals(expResult, result);
	}

	@Test
	public void testPow1()
	{
		Integer exponent = 1;
		BigFraction instance = BigFraction.createFraction(2, 3);
		BigFraction expResult = BigFraction.createFraction(2, 3);
		BigFraction result = instance.pow(exponent);
		assertEquals(expResult, result);
	}

	@Test
	public void testPow0()
	{
		Integer exponent = 0;
		BigFraction instance = BigFraction.createFraction(2, 3);
		BigFraction expResult = BigFraction.createFraction(1, 1);
		BigFraction result = instance.pow(exponent);
		assertEquals(expResult, result);
	}

	@Test
	public void testPowNegative()
	{
		Integer exponent = -2;
		BigFraction instance = BigFraction.createFraction(2, 3);
		BigFraction expResult = BigFraction.createFraction(9, 4);
		BigFraction result = instance.pow(exponent);
		assertEquals(expResult, result);
	}

	@Test(expected = NullPointerException.class)
	public void testPow_NullPointerException()
	{
		Integer nullInt = null;
		BigFraction instance = BigFraction.createFraction(2, 3);
		instance.pow(nullInt);
	}

	@Test
	public void testReciprocal()
	{
		BigFraction instance = BigFraction.createFraction(2, 3);
		BigFraction expResult = BigFraction.createFraction(3, 2);
		BigFraction result = instance.reciprocal();
		assertEquals(expResult, result);
	}

	@Test(expected = ArithmeticException.class)
	public void testReciprocal_ArithmeticException()
	{
		BigFraction instance = BigFraction.createFraction(0, 3);
		instance.reciprocal();
	}

	@Test
	public void testInPercentage()
	{
		BigFraction instance = BigFraction.createFraction(1, 2);
		Double expResult = 50.0;
		Double result = instance.inPercentage();
		assertEquals(expResult, result);
	}

	@Test
	public void testLog()
	{
		BigFraction instance = BigFraction.createFraction(1, 3);
		Double expResult = -1.09861228866810969139524523692252570464749055782274;
		Double result = instance.log();
		assertEquals(expResult, result, 0.00000001);
	}

	@Test
	public void testComplement_One()
	{
		BigFraction instance = BigFraction.createFraction(3, 4);
		BigFraction expResult = BigFraction.createFraction(1, 4);
		BigFraction result = instance.complement();
		assertEquals(expResult, result);
	}

	@Test
	public void testComplement_Number()
	{
		BigFraction instance = BigFraction.createFraction(3, 4);
		BigFraction number = BigFraction.createFraction(3, 2);
		BigFraction expResult = BigFraction.createFraction(3, 4);
		BigFraction result = instance.complement(number);
		assertEquals(expResult, result);
	}

	@Test(expected = NullPointerException.class)
	public void testComplement_Number_NullPointerException()
	{
		BigFraction instance = BigFraction.createFraction(3, 4);
		Double number = null;
		instance.complement(number);

	}

	@Test
	public void testRandom()
	{
		BigFraction result = BigFraction.random();
		assertNotNull(result);
	}

	@Test
	public void testGetNumerator()
	{
		BigFraction instance = BigFraction.createFraction(4, 6);
		BigInteger expResult = BigInteger.valueOf(2L);
		BigInteger result = instance.getNumerator();
		assertEquals(expResult, result);
	}

	@Test
	public void testGetDenominator()
	{
		BigFraction instance = BigFraction.createFraction(4, 6);
		BigInteger expResult = BigInteger.valueOf(3L);
		BigInteger result = instance.getDenominator();
		assertEquals(expResult, result);
	}

	@Test
	public void testTryParse_String_Mixed_Fraction() throws ParseException
	{
		String s = "1/2/3";
		BigFraction expResult = BigFraction.createFraction(5, 3);
		BigFraction result = BigFraction.tryParse(s);
		assertEquals(expResult, result);
	}

	@Test
	public void testTryParse_String_Fraction() throws ParseException
	{
		String s = "2/3";
		BigFraction expResult = BigFraction.createFraction(2, 3);
		BigFraction result = BigFraction.tryParse(s);
		assertEquals(expResult, result);
	}

	@Test
	public void testTryParse_String_Number() throws ParseException
	{
		String s = "3";
		BigFraction expResult = BigFraction.createFraction(3);
		BigFraction result = BigFraction.tryParse(s);
		assertEquals(expResult, result);
	}

	@Test
	public void testTryParse_String_Character() throws ParseException
	{
		String s = "1:2:3";
		BigFraction expResult = BigFraction.createFraction(5, 3);
		BigFraction result = BigFraction.tryParse(s,':');
		assertEquals(expResult, result);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTryParse_String_Character_IllegalArgumentException_String_Null() throws ParseException
	{
		String s = null;
		Character c = '/';
		BigFraction.tryParse(s,c);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTryParse_String_Character_IllegalArgumentException_String_Empty() throws ParseException
	{
		String s = "";
		Character c = '/';
		BigFraction.tryParse(s,c);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTryParse_String_Character_IllegalArgumentException_Character_Null() throws ParseException
	{
		String s = "1/2";
		Character c = null;
		BigFraction.tryParse(s,c);
	}

	@Test(expected = ParseException.class)
	public void testTryParse_String_Character_ParseException_Too_Many_Separators() throws ParseException
	{
		String s = "1/2/3/4";
		Character c = '/';
		BigFraction.tryParse(s,c);
	}

	@Test(expected = ParseException.class)
	public void testTryParse_String_Character_ParseException_Wrong_Separator() throws ParseException
	{
		String s = "1:2";
		Character c = '/';
		BigFraction.tryParse(s,c);
	}

	@Test
	public void testIntValue()
	{
		BigFraction instance = BigFraction.createFraction(5, 4);
		int expResult = 1;
		int result = instance.intValue();
		assertEquals(expResult, result);
	}

	@Test
	public void testLongValue()
	{
		BigFraction instance = BigFraction.createFraction(5, 4);
		long expResult = 1L;
		long result = instance.longValue();
		assertEquals(expResult, result);
	}

	@Test
	public void testFloatValue()
	{
		BigFraction instance = BigFraction.createFraction(3, 4);
		float expResult = 0.75F;
		float result = instance.floatValue();
		assertEquals(expResult, result, 0.00000001);
	}

	@Test
	public void testDoubleValue()
	{
		BigFraction instance = BigFraction.createFraction(5, 4);
		double expResult = 1.25;
		double result = instance.doubleValue();
		assertEquals(expResult, result, 0.00000001);
	}

	@Test
	public void testCompareToPositive()
	{
		BigFraction f = BigFraction.createFraction(2, 3);
		BigFraction instance = BigFraction.createFraction(3, 4);
		int result = instance.compareTo(f);
		assertTrue(result > 0);
	}

	@Test
	public void testCompareToNegative()
	{
		BigFraction f = BigFraction.createFraction(3, 4);
		BigFraction instance = BigFraction.createFraction(2, 3);
		int result = instance.compareTo(f);
		assertTrue(result < 0);
	}

	@Test
	public void testCompareToSame()
	{
		BigFraction f = BigFraction.createFraction(3, 4);
		BigFraction instance = BigFraction.createFraction(3, 4);
		int result = instance.compareTo(f);
		assertTrue(result == 0);
	}

	@Test
	public void testEquals()
	{
		Object o = BigFraction.createFraction(2, 3);
		BigFraction instance = BigFraction.createFraction(4, 6);
		boolean expResult = true;
		boolean result = instance.equals(o);
		assertEquals(expResult, result);
	}

	@Test
	public void testHashCode()
	{
		Integer numerator = 2;
		Integer denominator = 3;

		BigFraction instance = BigFraction.createFraction(numerator, denominator);
		int expResult = (13 + numerator.hashCode()) * 13 + denominator.hashCode();
		int result = instance.hashCode();
		assertEquals(expResult, result);
	}

	@Test
	public void testToString()
	{
		BigFraction instance = BigFraction.createFraction(4, 6);
		String expResult = "2/3";
		String result = instance.toString();
		assertEquals(expResult, result);

		instance = BigFraction.createFraction(2, 1);
		expResult = "2";
		result = instance.toString();
		assertEquals(expResult, result);
	}
}
