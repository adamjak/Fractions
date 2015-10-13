/*
 * Copyright 2015 Tomas Adamjak - http://thomas.adamjak.net 
 * License: The BSD 3-Clause License
 */
package net.adamjak.thomas.fractions;

/**
 *
 * @author Tomas Adamjak <thomas@adamjak.net>
 */
public class Main
{
	public static void main (String args[])
	{
		Fraction f1 = Fraction.createFraction(2, 3);
		Fraction f2 = Fraction.createFraction(4L, 5L);
	
		System.out.println("*******************************************");
		System.out.println("Fractions: " + f1 + " and " + f2);
		System.out.println("*******************************************");
		
		System.out.println("Add: " + f1.add(f2));
		System.out.println("Subtract: " + f1.subtract(f2));
		System.out.println("Multiply: " + f1.multiply(f2));
		System.out.println("Divide: " + f1.divide(f2));
		
		System.out.println("Max: " + f1.max(f2));
		System.out.println("Min: " + f1.min(f2));
		System.out.println("Reciprocal: " + f1.reciprocal());
		
		System.out.println("Random: " + Fraction.random());
		
	}
}
