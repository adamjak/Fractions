Fractions
==========

Author: Tomáš Adamják

Web: <http://thomas.adamjak.net>

License
---------------

Copyright (c) 2015, Tomáš Adamják

All rights reserved.

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.

2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.

3. Neither the name of the copyright holder nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.

Maven
---------------
```xml
<repositories>
	<repository>
		<releases>
			<enabled>true</enabled>
			<updatePolicy>always</updatePolicy>
			<checksumPolicy>fail</checksumPolicy>
		</releases>
		<id>Fractions</id>
		<name>Tomas Adamjak</name>
		<url>http://utils.adamjak.net/maven2</url>
		<layout>default</layout>
	</repository>
</repositories>

<dependency>
	<groupId>net.adamjak.utils</groupId>
	<artifactId>Fractions</artifactId>
	<version>0.3.0</version>
</dependency>
```

Usage
---------------

### Create fraction

```java
Integer   i1 = 1;
Integer   i2 = 2;
Long      l1 = 3;
Double    d1 = 2.5;
String    s1 = "4/5";
String    s2 = "1:2:3";
Character c1 = ':';

Fraction f1 = Fraction.createFraction(i1,i2); // 1/2
Fraction f2 = Fraction.createFraction(l1);    // 3/1
Fraction f3 = Fraction.createFraction(d1);    // 5/2
Fraction f4 = Fraction.createFraction(f3);    // 5/2
Fraction f5 = Fraction.tryParse(s1);          // 4/5
Fraction f6 = Fraction.tryParse(s2,c1);       // 5/3
Fraction f7 = Fraction.random();              // create new random fraction
```

### Math operations
```java
// add - create new fraction with result
f1.add(f2); // 7/2
f1.add(i1); // 3/2
f2.add(d1); // 11/2

// multiply - create new fraction with result
f1.multiply(f2); // 3/2
f1.multiply(i2); // 1/1
f2.multiply(d1); // 15/2

// subtract - create new fraction with result
f1.subtract(f2); // -5/2
f1.subtract(i1); // -1/2
f2.subtract(d1); // 1/2

// divide - create new fraction with result
f1.divide(f2); // 1/6
f1.divide(i2); // 1/4
f2.divide(d1); // 6/5
f2.divide(0);  // ArithmeticException - Divide by zero

// pow - create new fraction with result
f1.pow(i2); // 1/4

```