/*
 * Copyright 2015 Tomas Adamjak - http://thomas.adamjak.net 
 * License: The BSD 3-Clause License
 */
package net.adamjak.utils.fractions;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Class which represent a fraction as two longs and you can calculate math operations with fractions.
 * 
 * @author Tomas Adamjak - thomas.adamjak.net
 * 
 * @see <a href="http://utils.adamjak.net">Utils.adamjak.net</a>
 */
public class Fraction extends Number implements Comparable<Fraction>, Cloneable, Serializable
{
	private final Long numerator;
	private final Long denominator;
	
	// -------------------------------------------------------------------------
	// Construct method
	// -------------------------------------------------------------------------
	private Fraction (Long numerator, Long denominator)
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
	 * @param numerator (Number)
	 * @param denominator (Number)
	 * 
	 * @return (Fraction) 
	 * 
	 * @throws NullPointerException when numerator or denominator is null
	 * @throws ArithmeticException when denominator is zero
	 */
	public static Fraction createFraction (Number numerator, Number denominator)
	{
		if(numerator == null) throw new NullPointerException(FractionConstants.ERR_NULL_NUMERATOR);
		if(denominator == null) throw new NullPointerException(FractionConstants.ERR_NULL_DENOMINATOR);
		if(denominator.equals(FractionConstants.ZERO)) throw new ArithmeticException(FractionConstants.ERR_DIVITE_BY_ZERO);
		
		Fraction num, den;
		
		if (numerator instanceof Long ||
			numerator instanceof Integer ||
			numerator instanceof Short ||
			numerator instanceof Byte ||
			numerator instanceof BigInteger ||
			numerator instanceof AtomicInteger ||
			numerator instanceof AtomicLong)
		{
			num = Fraction.privateCreateFraction(numerator.longValue(), FractionConstants.ONE);
		}
		else
		{
			num = Fraction.privateCreateFraction(numerator.doubleValue());
		}
		
		if (denominator instanceof Long ||
			denominator instanceof Integer ||
			denominator instanceof Short ||
			denominator instanceof Byte ||
			denominator instanceof BigInteger ||
			denominator instanceof AtomicInteger ||
			denominator instanceof AtomicLong)
		{
			den = Fraction.privateCreateFraction(denominator.longValue(), FractionConstants.ONE);
		}
		else
		{
			den = Fraction.privateCreateFraction(denominator.doubleValue());
		}
		
		return Fraction.privateCreateFraction(num.getNumerator() * den.getDenominator(), num.getDenominator() * den.getNumerator());
		
	}
	
	/**
	 * createFraction - creating fraction from number
	 * 
	 * @param number (Number)
	 * 
	 * @return (Fraction) 
	 * 
	 * @throws NullPointerException when numerator or denominator is null
	 * @throws ArithmeticException when denominator is zero
	 */
	public static Fraction createFraction (Number number)
	{
		if (number == null) throw new NullPointerException(FractionConstants.ERR_NULL_NUMBER);
		
		return Fraction.createFraction(number, 1);
	}
	
	/**
	 * privateCreateFraction - creating fraction from numerator and denominator
	 * 
	 * @param numerator (Integer)
	 * @param denominator (Integer)
	 * 
	 * @return (Fraction) 
	 * 
	 * @throws NullPointerException when numerator or denominator is null
	 * @throws ArithmeticException when denominator is zero
	 */
	private static Fraction privateCreateFraction (Long numerator, Long denominator)
	{		
		if(numerator == null) throw new NullPointerException(FractionConstants.ERR_NULL_NUMERATOR);
		if(denominator == null) throw new NullPointerException(FractionConstants.ERR_NULL_DENOMINATOR);
		if(denominator.equals(FractionConstants.ZERO)) throw new ArithmeticException(FractionConstants.ERR_DIVITE_BY_ZERO);

		if(Fraction.signum(denominator) < FractionConstants.ZERO)
		{
			numerator = negation(numerator);
			denominator = negation(denominator);
		}

		if (numerator.equals(FractionConstants.ZERO))
		{
			return new Fraction(numerator, FractionConstants.ONE);
		}
		
		long gcd = Fraction.greatestCommonDivisor((long) Math.abs(numerator), (long) Math.abs(denominator));
		
		return new Fraction(numerator / gcd, denominator / gcd);
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
		if(fraction == null) throw new NullPointerException(FractionConstants.ERR_NULL_FRACTION);
		
		return new Fraction(fraction.getNumerator(), fraction.getDenominator());
	}
	
	/**
	 * privateCreateFraction - creating fraction from double number<br>
	 * For example from <em>3.2</em> create 16/5 fraction
	 * 
	 * @param d (Double)
	 * 
	 * @return (Fraction) 
	 * 
	 * @throws NullPointerException when <em>d</em> is null
	 * @throws IllegalArgumentException if <em>d</em> is infinite or NaN
	 */
	private static Fraction privateCreateFraction (Double d)
	{
		if(d == null) throw new NullPointerException(FractionConstants.ERR_NULL_NUMBER);
		if(d.isInfinite()) throw new IllegalArgumentException(FractionConstants.ERR_NUMBER_INFINITE);
		if(d.isNaN()) throw new IllegalArgumentException(FractionConstants.ERR_NUMBER_NAN);

		if(d == 0)
		{
			return new Fraction(FractionConstants.ZERO, FractionConstants.ONE);
		}

		boolean negative = false;

		if (d < 0)
		{
			negative = true;
		}

		d = Math.abs(d);

		Integer intPart = d.intValue();
		d = d - intPart;

		Long numerator = Double.valueOf(d * Math.pow(10,FractionConstants.LONG_DIGITS)).longValue();
		Long denominator = Double.valueOf(Math.pow(10,FractionConstants.LONG_DIGITS)).longValue();

		if (negative)
		{
			numerator = numerator * -1;
		}

		Fraction f = Fraction.createFraction(numerator,denominator);
		f = f.add(intPart);

		return f;
	}

	/**
	 * @return new instance of BigFraction
	 *
	 * @see BigFraction
	 */
	public BigFraction toBigFraction()
	{
		return BigFraction.createFraction(this.numerator, this.denominator);
	}
	
	// -------------------------------------------------------------------------
	// Calculate method
	// -------------------------------------------------------------------------
	
	/**
	 * @param number (Number) whitch will add to Fraction
	 * 
	 * @return New Instance of fraction add by number
	 * 
	 * @throws NullPointerException if number is null
	 */
	public Fraction add(Number number)
	{
		if(number == null) throw new NullPointerException(FractionConstants.ERR_NULL_NUMBER);
		
		return this.add(Fraction.createFraction(number));
	}
	
	/**
	 * @param fraction (Fraction) whitch will add to Fraction
	 * 
	 * @return New Instance of fraction add by fraction
	 * 
	 * @throws NullPointerException if fraction is null
	 */
	public Fraction add (Fraction fraction)
	{
		if(fraction == null) throw new NullPointerException(FractionConstants.ERR_NULL_FRACTION);
		
		return Fraction.createFraction(this.numerator * fraction.getDenominator() + fraction.getNumerator() * this.denominator, this.denominator * fraction.getDenominator());
	}
	
	
	/**
	 * @param number (Number) whitch will multiply Fraction
	 * 
	 * @return New instance of Fraction multyply by number
	 * 
	 * @throws NullPointerException if number is null
	 */
	public Fraction multiply (Number number)
	{
		if(number == null) throw new NullPointerException(FractionConstants.ERR_NULL_NUMBER);
		
		return this.multiply(Fraction.createFraction(number));
	}
	
	/**
	 * @param fraction (Fraction) whitch will multiply Fraction
	 * 
	 * @return New instance of Fraction multyply by number
	 * 
	 * @throws NullPointerException if fraction is null
	 */
	public Fraction multiply (Fraction fraction)
	{
		if(fraction == null) throw new NullPointerException(FractionConstants.ERR_NULL_FRACTION);
		
		return Fraction.createFraction(fraction.getNumerator() * this.numerator, fraction.getDenominator() * this.denominator);
	}
	
	/**
	 * @param number (Number) whitch will subtract Fraction
	 * 
	 * @return New instance of Fraction subtract by number
	 * 
	 * @throws NullPointerException if number is null
	 */
	public Fraction subtract (Number number)
	{
		if(number == null) throw new NullPointerException(FractionConstants.ERR_NULL_NUMBER);
		
		return this.subtract(Fraction.createFraction(number));
	}
	
	/**
	 * @param fraction (Fraction) whitch will subtract Fraction
	 * 
	 * @return New instance of Fraction subtract by fraction
	 * 
	 * @throws NullPointerException if fraction is null
	 */
	public Fraction subtract (Fraction fraction)
	{
		if(fraction == null) throw new NullPointerException(FractionConstants.ERR_NULL_FRACTION);
		
		return Fraction.createFraction(this.numerator * fraction.getDenominator() - fraction.getNumerator() * this.denominator, this.denominator * fraction.getDenominator());
	}
	
	/**
	 * @param number (Number) whitch will divide Fraction
	 * 
	 * @return New instance of Fraction divide by number.
	 * 
	 * @throws NullPointerException if number is null
	 * @throws ArithmeticException if number is zero
	 */
	public Fraction divide (Number number)
	{
		if (number == null) throw new NullPointerException(FractionConstants.ERR_NULL_NUMBER);
		if (number.doubleValue() == FractionConstants.ZERO) throw new ArithmeticException(FractionConstants.ERR_DIVITE_BY_ZERO);
		
		return this.divide(Fraction.createFraction(number));
	}
	
	/**
	 * @param fraction (Fraction) whitch will divide Fraction
	 * 
	 * @return New instance of Fraction divide by fraction.
	 * 
	 * @throws NullPointerException if fraction is null
	 * @throws ArithmeticException if fraction numerator is zero
	 */
	public Fraction divide (Fraction fraction)
	{
		if (fraction == null) throw new NullPointerException(FractionConstants.ERR_NULL_FRACTION);
		if (fraction.getNumerator() == FractionConstants.ZERO) throw new ArithmeticException(FractionConstants.ERR_DIVITE_BY_ZERO);
		
		return Fraction.createFraction(this.numerator * fraction.getDenominator(), this.denominator * fraction.getNumerator());
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
		if (fraction == null) throw new NullPointerException(FractionConstants.ERR_NULL_FRACTION);
		
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
		if (fraction == null) throw new NullPointerException(FractionConstants.ERR_NULL_FRACTION);
		
		return this.compareTo(fraction) <= 0 ? this : fraction;
	}
	
	/**
	 * Create power of fraction.
	 * <p>
	 * Examples:<br>
	 * - (2/3)<sup>0</sup> = 1/1<br>
	 * - (2/3)<sup>1</sup> = 2/3<br>
	 * - (2/3)<sup>2</sup> = 4/9<br>
	 * - (2/3)<sup>-2</sup> = 9/4<br>
	 * </p>
	 * @param exponent (Double)
	 *
	 * @return (Fraction)
	 * 
	 * @throws NullPointerException if exponent is null
	 */
	public Fraction pow (Double exponent)
	{
		if (exponent == null) throw new NullPointerException("Exponent can't be null.");
		
		if(exponent == 0)
		{
			return Fraction.createFraction(1);
		}
		else if (exponent == 1)
		{
			return Fraction.createFraction(this.getNumerator(),this.getDenominator());
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
		if (this.numerator == 0) throw new ArithmeticException(FractionConstants.ERR_DIVITE_BY_ZERO);
		
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
	 * Returns fraction which, after the sum with this fraction is 1.
	 * 
	 * @return (Fraction)
	 */	
	public Fraction complement()
	{
		return this.complement(1);
	}
	
	/**
	 * Returns fraction which, after the sum with this fraction is inserted number.
	 * 
	 * @param number (Number)
	 * @return (Fraction)
	 */
	public Fraction complement(Number number)
	{
		if (number == null) throw new NullPointerException(FractionConstants.ERR_NULL_NUMBER);
		
		return Fraction.createFraction(number).subtract(this);
	}
	
	/**
	 * Generate random fraction. Use <em>Math.random()</em>
	 * 
	 * @return (Fraction)
	 */
	public static Fraction random()
	{
		return Fraction.createFraction(Math.random());
	}
	
	/**
	 * Get numerator from fraction.
	 * 
	 * @return (Long)
	 */
	public Long getNumerator()
	{
		return this.numerator;
	}

	/**
	 * Get denominator from fraction.
	 * 
	 * @return (Long)
	 */
	public Long getDenominator()
	{
		return this.denominator;
	}
	
	/**
	 * tryParse - create Fraction from string and separator.
	 * <p>
	 * Supporded formats:<br>
	 *  - simple number - numerator<br>
	 *	- classic fraction - numerator[separator]denominator<br>
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
		
		Long numerator, denominator;
		
		if (subStrings.length == 3)
		{
			Long number = Long.parseLong(subStrings[0]);
			numerator = Long.parseLong(subStrings[1]);
			denominator = Long.parseLong(subStrings[2]);
			
			numerator = number * denominator + numerator;
		}
		else
		{
			numerator = Long.parseLong(subStrings[0]);
			denominator = Long.parseLong(subStrings[1]);
		}
		
		return Fraction.createFraction(numerator, denominator);
	}
	
	/**
	 * tryParse - create Fraction from string and default '/' separator or if in string is regular number.
	 * <p>
	 * Supporded formats:<br>
	 *	- classic fraction - numerator/denominator<br>
	 *  - mixed fraction - number/numerator/denominator<br>
	 *  - clasic or double number
	 * </p>
	 * <p>
	 * Examples:<br>
	 *  - string "<em>1/2/3</em>" create fraction with value 5/3<br>
	 *  - string "<em>1.125</em>" create fraction with value 9/8<br> 
	 *  - string "<em>1,125</em>" throws NumberFormatException becauce ',' is not correct decimal separator
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
		if (s.indexOf("/") > 0)
		{
			return Fraction.tryParse(s, '/');
		}
		else
		{
			
			try
			{
				return Fraction.privateCreateFraction(Double.valueOf(s));
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
				throw new ParseException(nfe.getMessage(), i);
			}
		}
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

		return Long.valueOf(this.numerator * fraction.getDenominator()).compareTo(this.denominator * fraction.getNumerator());
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
	 * @return String representation of fraction.
	 */
	@Override
	public String toString()
	{
		if (this.denominator == FractionConstants.ONE)
		{
			return this.numerator.toString();
		}
		
		return this.numerator + "/" + this.denominator;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException
	{
		return new Fraction(new Long(this.numerator.longValue()), new Long(this.denominator.longValue()));
	}
	
	
	
	// -------------------------------------------------------------------------
	// Private method
	// -------------------------------------------------------------------------
	
	private static Long greatestCommonDivisor(long a, long b)
	{
		if (a < 1 || b < 1) throw new IllegalArgumentException("One of the specified numbers is less than 1.");
		
		while (b != 0)
		{
			long tmp = a;
			a = b;
			b = tmp % b;
		}
		
		return a;
	}

	/**
	 * Clasic signum method.
	 * @param a long number for signum
	 * @return Return 1 if a is positive, return -1 if a is negative and return 0 if a is 0.
	 */
	private static int signum(long a)
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
	
	private static long negation(long a)
	{
		return a * -1;
	}

	private static String doubleToString(Double d)
	{
		BigDecimal bigDecimal = new BigDecimal(d);
		return bigDecimal.toPlainString();
	}
}
