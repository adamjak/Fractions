/**
 * Copyright 2016, Tomas Adamjak
 * License: The BSD 3-Clause License
 */
package net.adamjak.utils.fractions;

import java.io.Serializable;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Class which represent a fraction as two BigIntegers and you can calculate math operations with fractions.
 *
 * @author Tomas Adamjak - thomas.adamjak.net
 *
 * @see <a href="http://utils.adamjak.net">Utils.adamjak.net</a>
 * @see Fraction
 */
public class BigFraction extends Number implements Comparable<BigFraction>, Cloneable, Serializable
{
	private final BigInteger numerator;
	private final BigInteger denominator;

	// -------------------------------------------------------------------------
	// Construct method
	// -------------------------------------------------------------------------

	private BigFraction (BigInteger numerator, BigInteger denominator)
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
	 * @return (BigFraction)
	 *
	 * @throws NullPointerException when numerator or denominator is null
	 * @throws ArithmeticException when denominator is zero
	 */
	public static BigFraction createFraction (Number numerator, Number denominator)
	{
		if(numerator == null) throw new NullPointerException(FractionConstants.ERR_NULL_NUMERATOR);
		if(denominator == null) throw new NullPointerException(FractionConstants.ERR_NULL_DENOMINATOR);
		if(denominator.equals(FractionConstants.ZERO)) throw new ArithmeticException(FractionConstants.ERR_DIVITE_BY_ZERO);

		BigFraction num, den;

		if (numerator instanceof BigInteger)
		{
			num = BigFraction.privateCreateFraction((BigInteger) numerator, BigInteger.ONE);
		}
		else if (numerator instanceof Long ||
				numerator instanceof Integer ||
				numerator instanceof Short ||
				numerator instanceof Byte ||
				numerator instanceof BigInteger ||
				numerator instanceof AtomicInteger ||
				numerator instanceof AtomicLong)
		{
			num = BigFraction.privateCreateFraction(BigInteger.valueOf(numerator.longValue()), BigInteger.valueOf(FractionConstants.ONE));
		}
		else
		{
			num = BigFraction.privateCreateFraction(numerator.doubleValue());
		}

		if (denominator instanceof BigInteger)
		{
			den = BigFraction.privateCreateFraction((BigInteger) denominator, BigInteger.ONE);
		}
		else if (denominator instanceof Long ||
				denominator instanceof Integer ||
				denominator instanceof Short ||
				denominator instanceof Byte ||
				denominator instanceof BigInteger ||
				denominator instanceof AtomicInteger ||
				denominator instanceof AtomicLong)
		{
			den = BigFraction.privateCreateFraction(BigInteger.valueOf(denominator.longValue()), BigInteger.ONE);
		}
		else
		{
			den = BigFraction.privateCreateFraction(denominator.doubleValue());
		}

		return BigFraction.privateCreateFraction(num.getNumerator().multiply(den.getDenominator()), num.getDenominator().multiply(den.getNumerator()));

	}

	/**
	 * createFraction - creating fraction from number
	 *
	 * @param number (Number)
	 *
	 * @return (BigFraction)
	 *
	 * @throws NullPointerException when numerator or denominator is null
	 * @throws ArithmeticException when denominator is zero
	 */
	public static BigFraction createFraction (Number number)
	{
		if (number == null) throw new NullPointerException(FractionConstants.ERR_NULL_NUMBER);

		return BigFraction.createFraction(number, 1);
	}

	/**
	 * privateCreateFraction - creating fraction from numerator and denominator
	 *
	 * @param numerator (Integer)
	 * @param denominator (Integer)
	 *
	 * @return (BigFraction)
	 *
	 * @throws NullPointerException when numerator or denominator is null
	 * @throws ArithmeticException when denominator is zero
	 */
	private static BigFraction privateCreateFraction (BigInteger numerator, BigInteger denominator)
	{
		if(numerator == null) throw new NullPointerException(FractionConstants.ERR_NULL_NUMERATOR);
		if(denominator == null) throw new NullPointerException(FractionConstants.ERR_NULL_DENOMINATOR);
		if(denominator.equals(BigInteger.ZERO)) throw new ArithmeticException(FractionConstants.ERR_DIVITE_BY_ZERO);

		if(denominator.signum() < FractionConstants.ZERO)
		{
			numerator = numerator.negate();
			denominator = denominator.negate();
		}

		if (numerator.equals(BigInteger.ZERO))
		{
			return new BigFraction(numerator, BigInteger.ONE);
		}

		BigInteger gcd = numerator.gcd(denominator);

		return new BigFraction(numerator.divide(gcd), denominator.divide(gcd));
	}

	/**
	 * createFraction - creating fraction from other fraction
	 *
	 * @param fraction (BigFraction)
	 *
	 * @return (BigFraction)
	 *
	 * @throws NullPointerException when fraction is null
	 */
	public static BigFraction createFraction (BigFraction fraction)
	{
		if(fraction == null) throw new NullPointerException(FractionConstants.ERR_NULL_FRACTION);

		return new BigFraction(fraction.getNumerator(), fraction.getDenominator());
	}

	/**
	 * privateCreateFraction - creating fraction from double number<br>
	 * For example from <em>3.2</em> create 16/5 fraction
	 *
	 * @param d (Double)
	 *
	 * @return (BigFraction)
	 *
	 * @throws NullPointerException when <em>d</em> is null
	 * @throws IllegalArgumentException if <em>d</em> is infinite or NaN
	 */
	private static BigFraction privateCreateFraction (Double d)
	{
		if(d == null) throw new NullPointerException(FractionConstants.ERR_NULL_NUMBER);
		if(d.isInfinite()) throw new IllegalArgumentException(FractionConstants.ERR_NUMBER_INFINITE);
		if(d.isNaN()) throw new IllegalArgumentException(FractionConstants.ERR_NUMBER_NAN);

		if(d == 0)
		{
			return new BigFraction(BigInteger.ZERO, BigInteger.ONE);
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

		BigFraction f = BigFraction.createFraction(numerator,denominator);
		f = f.add(intPart);

		return f;
	}

	/**
	 * tryParse - create BigFraction from string and separator.
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
	 * @return new instance of BigFraction
	 *
	 * @throws IllegalArgumentException If string is null or empty
	 * @throws IllegalArgumentException If separator is null
	 * @throws ParseException If string haven't correct format
	 */
	public static BigFraction tryParse (String s, Character separator) throws ParseException
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
				return BigFraction.createFraction(numerator);
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

		return BigFraction.createFraction(numerator, denominator);
	}

	/**
	 * tryParse - create BigFraction from string and default '/' separator or if in string is regular number.
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
	 * @return new instance of BigFraction
	 *
	 * @throws IllegalArgumentException If string is null or empty
	 * @throws IllegalArgumentException If separator is null
	 * @throws ParseException If string haven't correct format
	 */
	public static BigFraction tryParse (String s) throws ParseException
	{
		if (s.indexOf("/") > 0)
		{
			return BigFraction.tryParse(s, '/');
		}
		else
		{

			try
			{
				return BigFraction.privateCreateFraction(Double.valueOf(s));
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

	/**
	 * @return new instance of simple Fraction
	 *
	 * @see Fraction
	 */
	public Fraction toFraction()
	{
		return Fraction.createFraction(this.numerator,this.denominator);
	}

	// -------------------------------------------------------------------------
	// Calculate method
	// -------------------------------------------------------------------------

	/**
	 * @param number (Number) whitch will add to Fraction
	 *
	 * @return New Instance of BigFraction add by number
	 *
	 * @throws NullPointerException if number is null
	 */
	public BigFraction add(Number number)
	{
		if(number == null) throw new NullPointerException(FractionConstants.ERR_NULL_NUMBER);

		return this.add(BigFraction.createFraction(number));
	}

	/**
	 * @param fraction (BigFraction) whitch will add to Fraction
	 *
	 * @return New Instance of BigFraction add by BigFraction
	 *
	 * @throws NullPointerException if fraction is null
	 */
	public BigFraction add (BigFraction fraction)
	{
		if(fraction == null) throw new NullPointerException(FractionConstants.ERR_NULL_FRACTION);

		return BigFraction.createFraction(this.numerator.multiply(fraction.getDenominator()).add(fraction.getNumerator().multiply(this.denominator)), this.denominator.multiply(fraction.getDenominator()));
	}


	/**
	 * @param number (Number) whitch will multiply Fraction
	 *
	 * @return New instance of BigFraction multyply by number
	 *
	 * @throws NullPointerException if number is null
	 */
	public BigFraction multiply (Number number)
	{
		if(number == null) throw new NullPointerException(FractionConstants.ERR_NULL_NUMBER);

		return this.multiply(BigFraction.createFraction(number));
	}

	/**
	 * @param fraction (BigFraction) whitch will multiply Fraction
	 *
	 * @return New instance of BigFraction multyply by number
	 *
	 * @throws NullPointerException if fraction is null
	 */
	public BigFraction multiply (BigFraction fraction)
	{
		if(fraction == null) throw new NullPointerException(FractionConstants.ERR_NULL_FRACTION);

		return BigFraction.createFraction(fraction.getNumerator().multiply(this.numerator), fraction.getDenominator().multiply(this.denominator));
	}

	/**
	 * @param number (Number) whitch will subtract Fraction
	 *
	 * @return New instance of BigFraction subtract by number
	 *
	 * @throws NullPointerException if number is null
	 */
	public BigFraction subtract (Number number)
	{
		if(number == null) throw new NullPointerException(FractionConstants.ERR_NULL_NUMBER);

		return this.subtract(BigFraction.createFraction(number));
	}

	/**
	 * @param fraction (BigFraction) whitch will subtract Fraction
	 *
	 * @return New instance of BigFraction subtract by fraction
	 *
	 * @throws NullPointerException if fraction is null
	 */
	public BigFraction subtract (BigFraction fraction)
	{
		if(fraction == null) throw new NullPointerException(FractionConstants.ERR_NULL_FRACTION);

		return BigFraction.createFraction(this.numerator.multiply(fraction.getDenominator()).subtract(fraction.getNumerator().multiply(this.denominator)), this.denominator.multiply(fraction.getDenominator()));
	}

	/**
	 * @param number (Number) whitch will divide Fraction
	 *
	 * @return New instance of BigFraction divide by number.
	 *
	 * @throws NullPointerException if number is null
	 * @throws ArithmeticException if number is zero
	 */
	public BigFraction divide (Number number)
	{
		if (number == null) throw new NullPointerException(FractionConstants.ERR_NULL_NUMBER);
		if (number.equals(FractionConstants.ZERO)) throw new ArithmeticException(FractionConstants.ERR_DIVITE_BY_ZERO);

		return this.divide(BigFraction.createFraction(number));
	}

	/**
	 * @param fraction (BigFraction) whitch will divide Fraction
	 *
	 * @return New instance of BigFraction divide by fraction.
	 *
	 * @throws NullPointerException if fraction is null
	 * @throws ArithmeticException if fraction numerator is zero
	 */
	public BigFraction divide (BigFraction fraction)
	{
		if (fraction == null) throw new NullPointerException(FractionConstants.ERR_NULL_FRACTION);
		if (fraction.getNumerator().equals(BigInteger.ZERO)) throw new ArithmeticException(FractionConstants.ERR_DIVITE_BY_ZERO);

		return BigFraction.createFraction(this.numerator.multiply(fraction.getDenominator()), this.denominator.multiply(fraction.getNumerator()));
	}

	/**
	 * Returns the bigger fraction. If instance is bigger that inserted fraction return instance else return inserted fraction.
	 *
	 * @param fraction (BigFraction) comparing fractions
	 *
	 * @return (BigFraction)
	 *
	 * @throws NullPointerException if fraction is null
	 */
	public BigFraction max (BigFraction fraction)
	{
		if (fraction == null) throw new NullPointerException(FractionConstants.ERR_NULL_FRACTION);

		return this.compareTo(fraction) >= 0 ? this : fraction;
	}

	/**
	 * Returns the smaller fraction. If instance is smaller that inserted fraction return instance else return inserted fraction.
	 *
	 * @param fraction (BigFraction) comparing fractions
	 *
	 * @return (BigFraction)
	 *
	 * @throws NullPointerException if fraction is null
	 */
	public BigFraction min (BigFraction fraction)
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
	 * @param exponent (Integer)
	 *
	 * @return (BigFraction)
	 *
	 * @throws NullPointerException if exponent is null
	 *
	 * @see BigInteger#pow(int)
	 */
	public BigFraction pow (Integer exponent)
	{
		if (exponent == null) throw new NullPointerException("Exponent can't be null.");

		if(exponent == 0)
		{
			return BigFraction.createFraction(1);
		}
		else if (exponent == 1)
		{
			return this;
		}
		else if (exponent < 0)
		{
			return BigFraction.createFraction(this.denominator.pow(-exponent), this.numerator.pow(-exponent));
		}
		else
		{
			return BigFraction.createFraction(this.numerator.pow(exponent), this.denominator.pow(exponent));
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
	 * @return (BigFraction)
	 *
	 * @throws ArithmeticException if numerator of instance is zero
	 */
	public BigFraction reciprocal()
	{
		if (this.numerator.equals(BigInteger.ZERO)) throw new ArithmeticException(FractionConstants.ERR_DIVITE_BY_ZERO);

		return BigFraction.createFraction(this.denominator, this.numerator);
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
	 *
	 * @see Math#log(double)
	 */
	public Double log()
	{
		return Math.log(this.numerator.doubleValue()) - Math.log(this.denominator.doubleValue());
	}

	/**
	 * Returns fraction which, after the sum with this fraction is 1.
	 *
	 * @return (Fraction)
	 */
	public BigFraction complement()
	{
		return this.complement(1);
	}

	/**
	 * Returns fraction which, after the sum with this fraction is inserted number.
	 *
	 * @param number (Number)
	 * @return (BigFraction)
	 */
	public BigFraction complement(Number number)
	{
		if (number == null) throw new NullPointerException(FractionConstants.ERR_NULL_NUMBER);

		return BigFraction.createFraction(number).subtract(this);
	}

	/**
	 * Generate random fraction. Use <em>Math.random()</em>
	 *
	 * @return (BigFraction)
	 *
	 * @see Math#random()
	 */
	public static BigFraction random()
	{
		return BigFraction.createFraction(Math.random());
	}

	/**
	 * @return Numerator of fraction.
	 */
	public BigInteger getNumerator ()
	{
		return this.numerator;
	}

	/**
	 * @return Detominator of fraction.
	 */
	public BigInteger getDenominator ()
	{
		return this.denominator;
	}

	// -------------------------------------------------------------------------
	// Overide method
	// -------------------------------------------------------------------------

	@Override
	public int compareTo (BigFraction fraction)
	{
		if(fraction == null) throw new NullPointerException();


		if(this.numerator.signum() != fraction.getNumerator().signum())
		{

			return this.numerator.signum() - fraction.getNumerator().signum();
		}

		if(this.denominator.equals(fraction.getDenominator()))
		{
			return this.numerator.compareTo(fraction.getNumerator());
		}

		return this.numerator.multiply(fraction.getDenominator()).compareTo(this.denominator.multiply(fraction.getNumerator()));
	}

	@Override
	public int intValue ()
	{
		return this.numerator.divide(this.denominator).intValue();
	}

	@Override
	public long longValue ()
	{
		return this.numerator.divide(this.denominator).longValue();
	}

	@Override
	public float floatValue ()
	{
		return Double.valueOf(this.doubleValue()).floatValue();
	}

	@Override
	public double doubleValue ()
	{
		return this.numerator.doubleValue() / this.denominator.doubleValue();
	}

	@Override
	public String toString ()
	{
		if (this.denominator.equals(BigInteger.ONE))
		{
			return this.numerator.toString();
		}

		return this.numerator.toString() + "/" + this.denominator.toString();
	}

	@Override
	public int hashCode()
	{
		return (13 + this.numerator.hashCode()) * 13 + this.denominator.hashCode();
	}

	@Override
	public boolean equals(Object o)
	{
		if((o == null) || (o.getClass() != this.getClass()))
		{
			return false;
		}

		BigFraction f = (BigFraction) o;

		if (this.numerator.equals(f.getNumerator()) && this.denominator.equals(f.getDenominator()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
