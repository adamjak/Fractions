/*
 * Copyright 2015 Tomas Adamjak - http://thomas.adamjak.net 
 * License: The BSD 3-Clause License
 */
package net.adamjak.thomas.fractions;

/**
 *
 * @author Tomas Adamjak <thomas@adamjak.net>
 */
public class Fraction extends Number implements Comparable<Fraction>
{
	private final Integer numerator;
	private final Integer denominator;
	
	// -------------------------------------------------------------------------
	// Constants
	// -------------------------------------------------------------------------
	
	private final static int ONE = 1;
	private final static int ZERO = 0;
	
	// -------------------------------------------------------------------------
	// Error texts
	// -------------------------------------------------------------------------
	private final static String ERR_NULL_NUMERATOR = "Numerator is null.";
	private final static String ERR_NULL_DENOMINATOR = "Denominator is null.";
	private final static String ERR_NULL_FRACTION = "Fraction is null.";
	private final static String ERR_DIVITE_BY_ZERO = "Divide by zero.";
	private final static String ERR_NUMBER_NAN = "Number is NaN.";
	private final static String ERR_NUMBER_INFINITE = "Number is infinite.";
	private final static String ERR_NUMBER_EMPTY = "Number is empty.";
	
	// -------------------------------------------------------------------------
	// Construct method
	// -------------------------------------------------------------------------
	
	private Fraction (Integer numerator, Integer denominator)
	{
		this.numerator = numerator;
		this.denominator = denominator;
	}

	// -------------------------------------------------------------------------
	// Fabric method
	// -------------------------------------------------------------------------
	
	public static Fraction createFraction (Integer numerator, Integer denominator)
	{		
		if(numerator == null) throw new IllegalArgumentException(Fraction.ERR_NULL_NUMERATOR);
		if(denominator == null) throw new IllegalArgumentException(Fraction.ERR_NULL_DENOMINATOR);
		if(denominator.equals(Fraction.ZERO)) throw new ArithmeticException(Fraction.ERR_DIVITE_BY_ZERO);

		if(Fraction.signum(denominator) < Fraction.ZERO)
		{
			numerator = negation(numerator);
			denominator = negation(denominator);
		}

		int gcd = Fraction.greatestCommonDivisor((int) Math.abs(numerator), (int) Math.abs(denominator));
		
		return new Fraction(numerator / gcd, denominator / gcd);
	}
	
	public static Fraction createFraction (Integer numerator)
	{
		if(numerator == null) throw new IllegalArgumentException(Fraction.ERR_NULL_NUMERATOR);
		
		return new Fraction(numerator, Fraction.ONE);
	}
	
	public static Fraction createFraction (Long numerator, Long detominator)
	{
		if(numerator == null) throw new IllegalArgumentException(Fraction.ERR_NULL_NUMERATOR);
		if(detominator == null) throw new IllegalArgumentException(Fraction.ERR_NULL_DENOMINATOR);
		
		return Fraction.createFraction(numerator.intValue(), detominator.intValue());
	}
	
	public static Fraction createFraction (Long numerator)
	{
		if(numerator == null) throw new IllegalArgumentException(Fraction.ERR_NULL_NUMERATOR);
		
		return new Fraction(numerator.intValue(), Fraction.ONE);
	}
	
	public static Fraction createFraction (Fraction fraction)
	{
		if(fraction == null) throw new IllegalArgumentException(Fraction.ERR_NULL_FRACTION);
		
		return new Fraction(fraction.getNumerator(), fraction.getDenominator());
	}
	
	public static Fraction createFraction (Double d)
	{
		if(d == null) throw new IllegalArgumentException(Fraction.ERR_NUMBER_EMPTY);
		if(d.isInfinite()) throw new IllegalArgumentException(Fraction.ERR_NUMBER_INFINITE);
		if(d.isNaN()) throw new IllegalArgumentException(Fraction.ERR_NUMBER_NAN);

		if(d == 0)
		{
			return new Fraction(Fraction.ZERO, Fraction.ONE);
		}

		boolean negative = false;
		
		if (d < 0)
		{
			negative = true;
		}
		
		d = Math.abs(d);
		
		int intValue = d.intValue();
		long decimalPart = Long.valueOf(d.toString().split("\\.")[1]);
		int decimalPartLenght = String.valueOf(decimalPart).length();
		Long tmpDetominator = (long) Math.pow(10, decimalPartLenght);
		Long tmpNumerator = intValue * tmpDetominator + decimalPart;
		
		if (negative)
		{
			tmpNumerator = tmpNumerator * -1;
		}
		
		return Fraction.createFraction(tmpNumerator, tmpDetominator);
	}
	
	// -------------------------------------------------------------------------
	// Calculate method
	// -------------------------------------------------------------------------
	
	public Fraction add(Integer number)
	{
		if(number == null) throw new IllegalArgumentException(Fraction.ERR_NUMBER_EMPTY);
		
		return this.add(Fraction.createFraction(number));
	}
	
	public Fraction add (Fraction fraction)
	{
		if(fraction == null) throw new IllegalArgumentException(Fraction.ERR_NULL_FRACTION);
		
		return Fraction.createFraction(this.numerator * fraction.getDenominator() + fraction.getNumerator() * this.denominator, this.denominator * fraction.getDenominator());
	}
	
	public Fraction add (Double d)
	{
		if(d == null) throw new IllegalArgumentException(Fraction.ERR_NUMBER_EMPTY);
		if(d.isInfinite()) throw new IllegalArgumentException(Fraction.ERR_NUMBER_INFINITE);
		if(d.isNaN()) throw new IllegalArgumentException(Fraction.ERR_NUMBER_NAN);
		
		return this.add(Fraction.createFraction(d));
	}
	
	public Fraction multiply (Integer number)
	{
		if(number == null) throw new IllegalArgumentException(Fraction.ERR_NUMBER_EMPTY);
		
		return Fraction.createFraction(number * this.numerator, this.denominator);
	}
	
	public Fraction multiply (Fraction fraction)
	{
		if(fraction == null) throw new IllegalArgumentException(Fraction.ERR_NULL_FRACTION);
		
		return Fraction.createFraction(fraction.getNumerator() * this.numerator, fraction.getDenominator() * this.denominator);
	}
	
	public Fraction multiply (Double d)
	{
		if(d == null) throw new IllegalArgumentException(Fraction.ERR_NUMBER_EMPTY);
		if(d.isInfinite()) throw new IllegalArgumentException(Fraction.ERR_NUMBER_INFINITE);
		if(d.isNaN()) throw new IllegalArgumentException(Fraction.ERR_NUMBER_NAN);
		
		return this.multiply(Fraction.createFraction(d));
	}
	
	public Fraction subtract (Integer number)
	{
		if(number == null) throw new IllegalArgumentException(Fraction.ERR_NUMBER_EMPTY);
		
		return this.subtract(Fraction.createFraction(number));
	}
	
	public Fraction subtract (Fraction fraction)
	{
		if(fraction == null) throw new IllegalArgumentException(Fraction.ERR_NULL_FRACTION);
		
		return Fraction.createFraction(this.numerator * fraction.getDenominator() - fraction.getNumerator() * this.denominator, this.denominator * fraction.getDenominator());
	}
	
	public Fraction subtract (Double d)
	{
		if(d == null) throw new IllegalArgumentException(Fraction.ERR_NUMBER_EMPTY);
		if(d.isInfinite()) throw new IllegalArgumentException(Fraction.ERR_NUMBER_INFINITE);
		if(d.isNaN()) throw new IllegalArgumentException(Fraction.ERR_NUMBER_NAN);
		
		return this.subtract(Fraction.createFraction(d));
	}
	
	public Fraction divide (Integer number)
	{
		if (number == null) throw new IllegalArgumentException(Fraction.ERR_NUMBER_EMPTY);
		if (number == Fraction.ZERO) throw new ArithmeticException(Fraction.ERR_DIVITE_BY_ZERO);
		
		return this.divide(Fraction.createFraction(number));
	}
	
	public Fraction divide (Fraction fraction)
	{
		if (fraction == null) throw new IllegalArgumentException(Fraction.ERR_NULL_FRACTION);
		if (fraction.getNumerator() == Fraction.ZERO) throw new ArithmeticException(Fraction.ERR_DIVITE_BY_ZERO);
		
		return Fraction.createFraction(this.numerator * fraction.getDenominator(), this.denominator * fraction.getNumerator());
	}
	
	public Fraction divide (Double d)
	{
		if (d == null) throw new IllegalArgumentException(Fraction.ERR_NUMBER_EMPTY);
		if (d == Fraction.ZERO) throw new ArithmeticException(Fraction.ERR_DIVITE_BY_ZERO);
		
		return this.divide(Fraction.createFraction(d));
	}
	
	public Fraction max (Fraction fraction)
	{
		return this.compareTo(fraction) >= 0 ? this : fraction;
	}
	
	public Fraction min (Fraction fraction)
	{
		return this.compareTo(fraction) <= 0 ? this : fraction;
	}
	
	public Fraction pow (int exponent)
	{
		if(exponent == 0)
		{
			return Fraction.createFraction(1);
		}
		else if (exponent == 1)
		{
			return this;
		}
		else if (exponent < 0)
		{
			return Fraction.createFraction((int) Math.pow(this.denominator, -exponent), (int) Math.pow(this.numerator, -exponent));
		}
		else
		{
			return Fraction.createFraction((int) Math.pow(this.numerator, exponent), (int) Math.pow(this.denominator, exponent));
		}
	}
	
	public Fraction reciprocal()
	{
		if (this.numerator == 0) throw new ArithmeticException(Fraction.ERR_DIVITE_BY_ZERO);
		
		return Fraction.createFraction(this.denominator, this.numerator);
	}
	
	public static Fraction random()
	{
		return Fraction.createFraction(Math.random());
	}
	
	public Integer getNumerator()
	{
		return this.numerator;
	}

	public Integer getDenominator()
	{
		return this.denominator;
	}
	
	// -------------------------------------------------------------------------
	// Overide method
	// -------------------------------------------------------------------------
	
	@Override
	public int intValue()
	{
		return (int) Math.max(Integer.MIN_VALUE, Math.min(Integer.MAX_VALUE, longValue()));
	}

	@Override
	public long longValue()
	{
		return Math.round(this.doubleValue());
	}

	@Override
	public float floatValue()
	{
		return (float) this.doubleValue();
	}

	@Override
	public double doubleValue()
	{
		return this.numerator.doubleValue() / this.denominator.doubleValue();
	}

	@Override
	public int compareTo(Fraction fraction)
	{	
		if(fraction == null) throw new IllegalArgumentException("Null argument");
		
		if(Fraction.signum(this.numerator) != Fraction.signum(fraction.getNumerator()))
		{
			return Fraction.signum(this.numerator) - Fraction.signum(fraction.getNumerator());
		}

		if(this.denominator.equals(fraction.getDenominator()))
		{
			return this.numerator.compareTo(fraction.getNumerator());
		}

		return Integer.valueOf(this.numerator * fraction.getDenominator()).compareTo(this.denominator * fraction.getNumerator());
	}

	@Override
	public boolean equals(Object o)
	{
		if((o == null) || (o.getClass() != this.getClass()))
		{
			return false;
		}

		Fraction f = (Fraction) o;
		
		if (this.numerator.equals(f.getNumerator()) && this.denominator.equals(f.getDenominator()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public int hashCode()
	{
		return (13 + this.numerator.hashCode()) * 13 + this.denominator.hashCode();
	}

	@Override
	public String toString()
	{
		return this.numerator + "/" + this.denominator;
	}
	
	// -------------------------------------------------------------------------
	// Private method
	// -------------------------------------------------------------------------
	
	private static Integer greatestCommonDivisor(int a, int b)
	{
		if (a < 1 || b < 1) throw new IllegalArgumentException("One of the specified numbers is less than 1.");
		
		while (b != 0)
		{
			int tmp = a;
			a = b;
			b = tmp % b;
		}
		
		return a;
	}
	
	private static int signum(int a)
	{
		if (a > 0)
		{
			return 1;
		}
		else if (a < 0)
		{
			return -1;
		}
		else
		{
			return 0;
		}
	}
	
	private static int negation(int a)
	{
		return a * -1;
	}
	
}
