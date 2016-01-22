package net.adamjak.utils.fractions;

/**
 * Copyright 2016, Tomas Adamjak
 * License: The BSD 3-Clause License
 */
public class FractionConstants
{
	// -------------------------------------------------------------------------
	// Number Constants
	// -------------------------------------------------------------------------
	public final static long ONE = 1;
	public final static long ZERO = 0;

	// -------------------------------------------------------------------------
	// Error texts
	// -------------------------------------------------------------------------
	public final static String ERR_NULL_NUMERATOR = "Numerator is null.";
	public final static String ERR_NULL_DENOMINATOR = "Denominator is null.";
	public final static String ERR_NULL_FRACTION = "Fraction is null.";
	public final static String ERR_NULL_NUMBER = "Number is null.";
	public final static String ERR_DIVITE_BY_ZERO = "Divide by zero.";
	public final static String ERR_NUMBER_NAN = "Number is NaN.";
	public final static String ERR_NUMBER_INFINITE = "Number is infinite.";

	// -------------------------------------------------------------------------
	// The most popular fractions
	// -------------------------------------------------------------------------
	public final static Fraction HALF = Fraction.createFraction(1,2);
	public final static Fraction ONE_THIRD = Fraction.createFraction(1,3);
	public final static Fraction ONE_SIXTH = Fraction.createFraction(1,6);
	public final static Fraction PI_02 = Fraction.createFraction(22,7);
	public final static Fraction PI_06 = Fraction.createFraction(355,113);
	public final static Fraction MU_K = Fraction.createFraction(Math.PI,3*Math.sqrt(2)); // Hermite constant Sphere packing 3D Kepler conjecture
	public final static Fraction PI_ON_E = Fraction.createFraction(Math.PI).pow(Math.E); // pi^e
	public final static Fraction S_K = Fraction.createFraction(Fraction.createFraction(Math.PI,2),Fraction.createFraction(2,Math.PI)); //Lower limit in the moving sofa problem
}
