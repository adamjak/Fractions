/*
 * Copyright 2015 Tomas Adamjak - http://thomas.adamjak.net 
 * License: The BSD 3-Clause License
 */
package net.adamjak.thomas.fractions;

import java.io.Serializable;
import java.text.ParseException;

/**
 * Class which represent a fraction and you can calculate math operations with fractions.
 * 
 * @author Tomas Adamjak <thomas@adamjak.net>
 * 
 * @see http://fractions.thomas.adamjak.net
 */
public class Fraction extends Number implements Comparable<Fraction>, Cloneable, Serializable
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
	private final static String ERR_NULL_NUMBER = "Number is null.";
	private final static String ERR_DIVITE_BY_ZERO = "Divide by zero.";
	private final static String ERR_NUMBER_NAN = "Number is NaN.";
	private final static String ERR_NUMBER_INFINITE = "Number is infinite.";
	
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
	
	/**
	 * createFraction - creating fraction from numerator and denominator
	 * 
	 * @param numerator (Integer)
	 * @param denominator (Integer)
	 * 
	 * @return (Fraction) 
	 * 
	 * @throws NullPointerException when numerator or denominator is null
	 * @throws ArithmeticException when denominator is zero
	 */
	public static Fraction createFraction (Integer numerator, Integer denominator)
	{		
		if(numerator == null) throw new NullPointerException(Fraction.ERR_NULL_NUMERATOR);
		if(denominator == null) throw new NullPointerException(Fraction.ERR_NULL_DENOMINATOR);
		if(denominator.equals(Fraction.ZERO)) throw new ArithmeticException(Fraction.ERR_DIVITE_BY_ZERO);

		if(Fraction.signum(denominator) < Fraction.ZERO)
		{
			numerator = negation(numerator);
			denominator = negation(denominator);
		}

		if (numerator.equals(Fraction.ZERO))
		{
			return new Fraction(numerator, denominator);
		}
		
		int gcd = Fraction.greatestCommonDivisor((int) Math.abs(numerator), (int) Math.abs(denominator));
		
		return new Fraction(numerator / gcd, denominator / gcd);
	}
	
	/**
	 * createFraction - creating fraction from number<br />
	 * For example from <em>3</em> create 3/1 fraction
	 * 
	 * @param numerator (Integer)
	 * 
	 * @return (Fraction) 
	 * 
	 * @throws NullPointerException when numerator is null
	 */
	public static Fraction createFraction (Integer numerator)
	{
		if(numerator == null) throw new NullPointerException(Fraction.ERR_NULL_NUMBER);
		
		return new Fraction(numerator, Fraction.ONE);
	}
	
	/**
	 * createFraction - creating fraction from numerator and denominator
	 * 
	 * @param numerator (Long)
	 * @param denominator (Long)
	 * 
	 * @return (Fraction) 
	 * 
	 * @throws NullPointerException when numerator or denominator is null
	 * @throws ArithmeticException when denominator is zero
	 */
	public static Fraction createFraction (Long numerator, Long denominator)
	{
		if(numerator == null) throw new NullPointerException(Fraction.ERR_NULL_NUMERATOR);
		if(denominator == null) throw new NullPointerException(Fraction.ERR_NULL_DENOMINATOR);
		
		return Fraction.createFraction(numerator.intValue(), denominator.intValue());
	}
	
	/**
	 * createFraction - creating fraction from number<br />
	 * For example from <em>3</em> create 3/1 fraction
	 * 
	 * @param numerator (Long)
	 * 
	 * @return (Fraction) 
	 * 
	 * @throws NullPointerException when numerator is null
	 */
	public static Fraction createFraction (Long numerator)
	{
		if(numerator == null) throw new NullPointerException(Fraction.ERR_NULL_NUMBER);
		
		return new Fraction(numerator.intValue(), Fraction.ONE);
	}
	
	/**
	 * createFraction - creating fraction from other fraction
	 * 
	 * @param fraction (Fraction)
	 * 
	 * @return (Fraction) 
	 * 
	 * @throws NullPointerException when fraction is null
	 */
	public static Fraction createFraction (Fraction fraction)
	{
		if(fraction == null) throw new NullPointerException(Fraction.ERR_NULL_FRACTION);
		
		return new Fraction(fraction.getNumerator().intValue(), fraction.getDenominator().intValue());
	}
	
	/**
	 * createFraction - creating fraction from double number<br />
	 * For example from <em>3.2</em> create 16/5 fraction
	 * 
	 * @param d (Double)
	 * 
	 * @return (Fraction) 
	 * 
	 * @throws NullPointerException when <em>d</em> is null
	 * @throws IllegalArgumentException if <em>d</em> is infinite or NaN
	 */
	public static Fraction createFraction (Double d)
	{
		if(d == null) throw new NullPointerException(Fraction.ERR_NULL_NUMBER);
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
		Long tmpDenominator = (long) Math.pow(10, decimalPartLenght);
		Long tmpNumerator = intValue * tmpDenominator + decimalPart;
		
		if (negative)
		{
			tmpNumerator = tmpNumerator * -1;
		}
		
		return Fraction.createFraction(tmpNumerator, tmpDenominator);
	}
	
	// -------------------------------------------------------------------------
	// Calculate method
	// -------------------------------------------------------------------------
	
	/**
	 * @return New Instance of fraction add by number
	 * 
	 * @throws NullPointerException if number is null
	 */
	public Fraction add(Integer number)
	{
		if(number == null) throw new NullPointerException(Fraction.ERR_NULL_NUMBER);
		
		return this.add(Fraction.createFraction(number));
	}
	
	/**
	 * @return New Instance of fraction add by fraction
	 * 
	 * @throws NullPointerException if fraction is null
	 */
	public Fraction add (Fraction fraction)
	{
		if(fraction == null) throw new NullPointerException(Fraction.ERR_NULL_FRACTION);
		
		return Fraction.createFraction(this.numerator * fraction.getDenominator() + fraction.getNumerator() * this.denominator, this.denominator * fraction.getDenominator());
	}
	
	/**
	 * @return New Instance of fraction add by double number
	 * 
	 * @throws NullPointerException if number is null
	 * @throws IllegalArgumentException if number is infinite or NaN
	 */
	public Fraction add (Double d)
	{
		if(d == null) throw new NullPointerException(Fraction.ERR_NULL_NUMBER);
		if(d.isInfinite()) throw new IllegalArgumentException(Fraction.ERR_NUMBER_INFINITE);
		if(d.isNaN()) throw new IllegalArgumentException(Fraction.ERR_NUMBER_NAN);
		
		return this.add(Fraction.createFraction(d));
	}
	
	/**
	 * @return New instance of Fraction multyply by number
	 * 
	 * @throws NullPointerException if number is null
	 */
	public Fraction multiply (Integer number)
	{
		if(number == null) throw new NullPointerException(Fraction.ERR_NULL_NUMBER);
		
		return Fraction.createFraction(number * this.numerator, this.denominator);
	}
	
	/**
	 * @return New instance of Fraction multyply by number
	 * 
	 * @throws NullPointerException if fraction is null
	 */
	public Fraction multiply (Fraction fraction)
	{
		if(fraction == null) throw new NullPointerException(Fraction.ERR_NULL_FRACTION);
		
		return Fraction.createFraction(fraction.getNumerator() * this.numerator, fraction.getDenominator() * this.denominator);
	}
	
	/**
	 * @return New instance of Fraction multyply by number
	 * 
	 * @throws NullPointerException if number is null
	 * @throws IllegalArgumentException if number is infinite or NaN
	 */
	public Fraction multiply (Double d)
	{
		if(d == null) throw new NullPointerException(Fraction.ERR_NULL_NUMBER);
		if(d.isInfinite()) throw new IllegalArgumentException(Fraction.ERR_NUMBER_INFINITE);
		if(d.isNaN()) throw new IllegalArgumentException(Fraction.ERR_NUMBER_NAN);
		
		return this.multiply(Fraction.createFraction(d));
	}
	
	/**
	 * @return New instance of Fraction subtract by number
	 * 
	 * @throws NullPointerException if number is null
	 */
	public Fraction subtract (Integer number)
	{
		if(number == null) throw new NullPointerException(Fraction.ERR_NULL_NUMBER);
		
		return this.subtract(Fraction.createFraction(number));
	}
	
	/**
	 * @return New instance of Fraction subtract by fraction
	 * 
	 * @throws NullPointerException if fraction is null
	 */
	public Fraction subtract (Fraction fraction)
	{
		if(fraction == null) throw new NullPointerException(Fraction.ERR_NULL_FRACTION);
		
		return Fraction.createFraction(this.numerator * fraction.getDenominator() - fraction.getNumerator() * this.denominator, this.denominator * fraction.getDenominator());
	}
	
	/**
	 * @return New instance of Fraction subtract by double number
	 * 
	 * @throws NullPointerException if number is null
	 * @throws IllegalArgumentException if number is infinite or NaN
	 */
	public Fraction subtract (Double d)
	{
		if(d == null) throw new NullPointerException(Fraction.ERR_NULL_NUMBER);
		if(d.isInfinite()) throw new IllegalArgumentException(Fraction.ERR_NUMBER_INFINITE);
		if(d.isNaN()) throw new IllegalArgumentException(Fraction.ERR_NUMBER_NAN);
		
		return this.subtract(Fraction.createFraction(d));
	}
	
	/**
	 * @return New instance of Fraction divide by number.
	 * 
	 * @throws NullPointerException if number is null
	 * @throws ArithmeticException if number is zero
	 */
	public Fraction divide (Integer number)
	{
		if (number == null) throw new NullPointerException(Fraction.ERR_NULL_NUMBER);
		if (number == Fraction.ZERO) throw new ArithmeticException(Fraction.ERR_DIVITE_BY_ZERO);
		
		return this.divide(Fraction.createFraction(number));
	}
	
	/**
	 * @return New instance of Fraction divide by fraction.
	 * 
	 * @throws NullPointerException if fraction is null
	 * @throws ArithmeticException if fraction numerator is zero
	 */
	public Fraction divide (Fraction fraction)
	{
		if (fraction == null) throw new NullPointerException(Fraction.ERR_NULL_FRACTION);
		if (fraction.getNumerator() == Fraction.ZERO) throw new ArithmeticException(Fraction.ERR_DIVITE_BY_ZERO);
		
		return Fraction.createFraction(this.numerator * fraction.getDenominator(), this.denominator * fraction.getNumerator());
	}
	
	/**
	 * @return New instance of Fraction divide by double number.
	 * 
	 * @throws NullPointerException if number is null
	 * @throws ArithmeticException if number is zero
	 * @throws IllegalArgumentException if number is infinite or NaN
	 */
	public Fraction divide (Double d)
	{
		if (d == null) throw new NullPointerException(Fraction.ERR_NULL_NUMBER);
		if (d.isInfinite()) throw new IllegalArgumentException(Fraction.ERR_NUMBER_INFINITE);
		if (d.isNaN()) throw new IllegalArgumentException(Fraction.ERR_NUMBER_NAN);
		if (d == Fraction.ZERO) throw new ArithmeticException(Fraction.ERR_DIVITE_BY_ZERO);
		
		return this.divide(Fraction.createFraction(d));
	}
	
	/**
	 * Returns the bigger fraction. If instance is bigger that inserted fraction return instance else return inserted fraction.
	 * 
	 * @param fraction (Fraction) comparing fractions
	 * 
	 * @return (Fraction)
	 * 
	 * @throws NullPointerException if fraction is null
	 */
	public Fraction max (Fraction fraction)
	{
		if (fraction == null) throw new NullPointerException(Fraction.ERR_NULL_FRACTION);
		
		return this.compareTo(fraction) >= 0 ? this : fraction;
	}
	
	/**
	 * Returns the smaller fraction. If instance is smaller that inserted fraction return instance else return inserted fraction.
	 * 
	 * @param fraction (Fraction) comparing fractions
	 * 
	 * @return (Fraction)
	 * 
	 * @throws NullPointerException if fraction is null
	 */
	public Fraction min (Fraction fraction)
	{
		if (fraction == null) throw new NullPointerException(Fraction.ERR_NULL_FRACTION);
		
		return this.compareTo(fraction) <= 0 ? this : fraction;
	}
	
	/**
	 * Create power of fraction.
	 * <p>
	 * Examples:<br />
	 * - (2/3)<sup>0</sup> = 1/1<br />
	 * - (2/3)<sup>1</sup> = 2/3<br />
	 * - (2/3)<sup>2</sup> = 4/9<br />
	 * - (2/3)<sup>-2</sup> = 9/4<br />
	 * </p>
	 * @param exponent (Integer)
	 *
	 * @return (Fraction)
	 * 
	 * @throws NullPointerException if exponent is null
	 */
	public Fraction pow (Integer exponent)
	{
		if (exponent == null) throw new NullPointerException("Exponent can't be null.");
		
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
	
	/**
	 * <p>
	 * Create new reciprocal fraction from instance.
	 * </p>
	 * <p>
	 * Example: 2/3 &rarr; 3/2
	 * </p>
	 * 
	 * @return (Fraction)
	 * 
	 * @throws ArithmeticException if numerator of instance is zero
	 */
	public Fraction reciprocal()
	{
		if (this.numerator == 0) throw new ArithmeticException(Fraction.ERR_DIVITE_BY_ZERO);
		
		return Fraction.createFraction(this.denominator, this.numerator);
	}
	
	/**
	 * Percentage value of fraction.
	 * 
	 * @return (Double)
	 */
	public Double inPercentage()
	{
		return this.doubleValue() * 100;
	}
	
	/**
	 * Natural logarithm of fraction. User <em>Math.log()</em>
	 * 
	 * @return (Double)
	 */
	public Double log()
	{
		return Math.log(this.numerator) - Math.log(this.denominator);
	}
	
	/**
	 * Generate random fraction. Use <em>Math.random()</em>
	 * 
	 * @return (Fraction)
	 * 
	 * @see java.lang.Math.random()
	 */
	public static Fraction random()
	{
		return Fraction.createFraction(Math.random());
	}
	
	/**
	 * Get numerator from fraction.
	 * 
	 * @return (Integer)
	 */
	public Integer getNumerator()
	{
		return this.numerator;
	}

	/**
	 * Get denominator from fraction.
	 * 
	 * @return (Integer)
	 */
	public Integer getDenominator()
	{
		return this.denominator;
	}
	
	/**
	 * tryParse - create Fraction from string and separator.
	 * <p>
	 * Supporded formats:<br />
	 *  - simple number - numerator<br />
	 *	- classic fraction - numerator[separator]denominator<br />
	 *  - mixed fraction - number[separator]numerator[separator]denominator
	 * </p>
	 * <p>
	 * For example string <em>1/2/3</em> and separator <em>/</em> create fraction with value 5/3
	 * </p>
	 * @param s (String)
	 * @param separator (Character) 
	 *
	 * @return new instance of Fraction
	 * 
	 * @throws IllegalArgumentException If string is null or empty
	 * @throws IllegalArgumentException If separator is null
	 * @throws ParseException If string haven't correct format
	 */
	public static Fraction tryParse (String s, Character separator) throws ParseException
	{
		if (s == null || s.isEmpty()) throw new IllegalArgumentException("String can not by empty.");
		if (separator == null) throw new IllegalArgumentException("Seprator character can not by empty.");
		
		int parserCharacterCount = 0;
		s = s.trim();
		
		for (Character c : s.toCharArray())
		{
			if (c.equals(separator)) parserCharacterCount++;
		}
		
		if (parserCharacterCount > 2)
		{
			throw new ParseException("The seprator characters are located in the string too many times.",s.lastIndexOf(separator.toString()));
		}
		
		if (parserCharacterCount == 0)
		{
			Integer numerator;
			try
			{
				numerator = Integer.parseInt(s);
				return Fraction.createFraction(numerator);
			}
			catch (NumberFormatException nfe)
			{
				int i = 0;
				for (Character c : s.toCharArray())
				{
					try
					{
						Integer.parseInt(c.toString());
					}
					catch (NumberFormatException e)
					{
						break;
					}
					
					i++;
				}
				throw new ParseException("Wrong separator", i);
			}
		}
		
		String subStrings[] = s.split(separator.toString());
		
		Integer numerator, denominator;
		
		if (subStrings.length == 3)
		{
			Integer number = Integer.parseInt(subStrings[0]);
			numerator = Integer.parseInt(subStrings[1]);
			denominator = Integer.parseInt(subStrings[2]);
			
			numerator = number * denominator + numerator;
		}
		else
		{
			numerator = Integer.parseInt(subStrings[0]);
			denominator = Integer.parseInt(subStrings[1]);
		}
		
		return Fraction.createFraction(numerator, denominator);
	}
	
	/**
	 * tryParse - create Fraction from string and default '/' separator.
	 * <p>
	 * Supporded formats:<br />
	 *	- classic fraction - numerator/denominator<br />
	 *  - mixed fraction - number/numerator/denominator
	 * </p>
	 * <p>
	 * For example string <em>1/2/3</em> create fraction with value 5/3
	 * </p>
	 * @param s (String)
	 *
	 * @return new instance of Fraction
	 * 
	 * @throws IllegalArgumentException If string is null or empty
	 * @throws IllegalArgumentException If separator is null
	 * @throws ParseException If string haven't correct format
	 */
	public static Fraction tryParse (String s) throws ParseException
	{
		return Fraction.tryParse(s, '/');
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
		if(fraction == null) throw new NullPointerException();
		
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

	/**
	 * Return string representation of fraction.
	 */
	@Override
	public String toString()
	{
		return this.numerator + "/" + this.denominator;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException
	{
		return new Fraction(new Integer(this.numerator.intValue()), new Integer(this.denominator.intValue()));
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
