package org.apache.commons.lang3.math;

import java.math.BigInteger;

public final class Fraction
  extends Number
  implements Comparable<Fraction>
{
  private static final long serialVersionUID = 65382027393090L;
  public static final Fraction ZERO = new Fraction(0, 1);
  public static final Fraction ONE = new Fraction(1, 1);
  public static final Fraction ONE_HALF = new Fraction(1, 2);
  public static final Fraction ONE_THIRD = new Fraction(1, 3);
  public static final Fraction TWO_THIRDS = new Fraction(2, 3);
  public static final Fraction ONE_QUARTER = new Fraction(1, 4);
  public static final Fraction TWO_QUARTERS = new Fraction(2, 4);
  public static final Fraction THREE_QUARTERS = new Fraction(3, 4);
  public static final Fraction ONE_FIFTH = new Fraction(1, 5);
  public static final Fraction TWO_FIFTHS = new Fraction(2, 5);
  public static final Fraction THREE_FIFTHS = new Fraction(3, 5);
  public static final Fraction FOUR_FIFTHS = new Fraction(4, 5);
  private final int numerator;
  private final int denominator;
  private transient int hashCode = 0;
  private transient String toString = null;
  private transient String toProperString = null;
  
  private Fraction(int numerator, int denominator)
  {
    this.numerator = numerator;
    this.denominator = denominator;
  }
  
  public static Fraction getFraction(int numerator, int denominator)
  {
    if (denominator == 0) {
      throw new ArithmeticException("The denominator must not be zero");
    }
    if (denominator < 0)
    {
      if ((numerator == -2147483648) || (denominator == -2147483648)) {
        throw new ArithmeticException("overflow: can't negate");
      }
      numerator = -numerator;
      denominator = -denominator;
    }
    return new Fraction(numerator, denominator);
  }
  
  public static Fraction getFraction(int whole, int numerator, int denominator)
  {
    if (denominator == 0) {
      throw new ArithmeticException("The denominator must not be zero");
    }
    if (denominator < 0) {
      throw new ArithmeticException("The denominator must not be negative");
    }
    if (numerator < 0) {
      throw new ArithmeticException("The numerator must not be negative");
    }
    long numeratorValue;
    long numeratorValue;
    if (whole < 0) {
      numeratorValue = whole * denominator - numerator;
    } else {
      numeratorValue = whole * denominator + numerator;
    }
    if ((numeratorValue < -2147483648L) || (numeratorValue > 2147483647L)) {
      throw new ArithmeticException("Numerator too large to represent as an Integer.");
    }
    return new Fraction((int)numeratorValue, denominator);
  }
  
  public static Fraction getReducedFraction(int numerator, int denominator)
  {
    if (denominator == 0) {
      throw new ArithmeticException("The denominator must not be zero");
    }
    if (numerator == 0) {
      return ZERO;
    }
    if ((denominator == -2147483648) && ((numerator & 0x1) == 0))
    {
      numerator /= 2;
      denominator /= 2;
    }
    if (denominator < 0)
    {
      if ((numerator == -2147483648) || (denominator == -2147483648)) {
        throw new ArithmeticException("overflow: can't negate");
      }
      numerator = -numerator;
      denominator = -denominator;
    }
    int gcd = greatestCommonDivisor(numerator, denominator);
    numerator /= gcd;
    denominator /= gcd;
    return new Fraction(numerator, denominator);
  }
  
  public static Fraction getFraction(double value)
  {
    int sign = value < 0.0D ? -1 : 1;
    value = Math.abs(value);
    if ((value > 2147483647.0D) || (Double.isNaN(value))) {
      throw new ArithmeticException("The value must not be greater than Integer.MAX_VALUE or NaN");
    }
    int wholeNumber = (int)value;
    value -= wholeNumber;
    int numer0 = 0;
    int denom0 = 1;
    int numer1 = 1;
    int denom1 = 0;
    int numer2 = 0;
    int denom2 = 0;
    int local_a1 = (int)value;
    int local_a2 = 0;
    double local_x1 = 1.0D;
    double local_x2 = 0.0D;
    double local_y1 = value - local_a1;
    double local_y2 = 0.0D;
    double delta2 = 1.7976931348623157E+308D;
    int local_i = 1;
    double delta1;
    do
    {
      delta1 = delta2;
      local_a2 = (int)(local_x1 / local_y1);
      local_x2 = local_y1;
      local_y2 = local_x1 - local_a2 * local_y1;
      numer2 = local_a1 * numer1 + numer0;
      denom2 = local_a1 * denom1 + denom0;
      double fraction = numer2 / denom2;
      delta2 = Math.abs(value - fraction);
      local_a1 = local_a2;
      local_x1 = local_x2;
      local_y1 = local_y2;
      numer0 = numer1;
      denom0 = denom1;
      numer1 = numer2;
      denom1 = denom2;
      local_i++;
    } while ((delta1 > delta2) && (denom2 <= 10000) && (denom2 > 0) && (local_i < 25));
    if (local_i == 25) {
      throw new ArithmeticException("Unable to convert double to fraction");
    }
    return getReducedFraction((numer0 + wholeNumber * denom0) * sign, denom0);
  }
  
  public static Fraction getFraction(String str)
  {
    if (str == null) {
      throw new IllegalArgumentException("The string must not be null");
    }
    int pos = str.indexOf('.');
    if (pos >= 0) {
      return getFraction(Double.parseDouble(str));
    }
    pos = str.indexOf(' ');
    if (pos > 0)
    {
      int whole = Integer.parseInt(str.substring(0, pos));
      str = str.substring(pos + 1);
      pos = str.indexOf('/');
      if (pos < 0) {
        throw new NumberFormatException("The fraction could not be parsed as the format X Y/Z");
      }
      int numer = Integer.parseInt(str.substring(0, pos));
      int denom = Integer.parseInt(str.substring(pos + 1));
      return getFraction(whole, numer, denom);
    }
    pos = str.indexOf('/');
    if (pos < 0) {
      return getFraction(Integer.parseInt(str), 1);
    }
    int whole = Integer.parseInt(str.substring(0, pos));
    int numer = Integer.parseInt(str.substring(pos + 1));
    return getFraction(whole, numer);
  }
  
  public int getNumerator()
  {
    return this.numerator;
  }
  
  public int getDenominator()
  {
    return this.denominator;
  }
  
  public int getProperNumerator()
  {
    return Math.abs(this.numerator % this.denominator);
  }
  
  public int getProperWhole()
  {
    return this.numerator / this.denominator;
  }
  
  public int intValue()
  {
    return this.numerator / this.denominator;
  }
  
  public long longValue()
  {
    return this.numerator / this.denominator;
  }
  
  public float floatValue()
  {
    return this.numerator / this.denominator;
  }
  
  public double doubleValue()
  {
    return this.numerator / this.denominator;
  }
  
  public Fraction reduce()
  {
    if (this.numerator == 0) {
      return equals(ZERO) ? this : ZERO;
    }
    int gcd = greatestCommonDivisor(Math.abs(this.numerator), this.denominator);
    if (gcd == 1) {
      return this;
    }
    return getFraction(this.numerator / gcd, this.denominator / gcd);
  }
  
  public Fraction invert()
  {
    if (this.numerator == 0) {
      throw new ArithmeticException("Unable to invert zero.");
    }
    if (this.numerator == -2147483648) {
      throw new ArithmeticException("overflow: can't negate numerator");
    }
    if (this.numerator < 0) {
      return new Fraction(-this.denominator, -this.numerator);
    }
    return new Fraction(this.denominator, this.numerator);
  }
  
  public Fraction negate()
  {
    if (this.numerator == -2147483648) {
      throw new ArithmeticException("overflow: too large to negate");
    }
    return new Fraction(-this.numerator, this.denominator);
  }
  
  public Fraction abs()
  {
    if (this.numerator >= 0) {
      return this;
    }
    return negate();
  }
  
  public Fraction pow(int power)
  {
    if (power == 1) {
      return this;
    }
    if (power == 0) {
      return ONE;
    }
    if (power < 0)
    {
      if (power == -2147483648) {
        return invert().pow(2).pow(-(power / 2));
      }
      return invert().pow(-power);
    }
    Fraction local_f = multiplyBy(this);
    if (power % 2 == 0) {
      return local_f.pow(power / 2);
    }
    return local_f.pow(power / 2).multiplyBy(this);
  }
  
  private static int greatestCommonDivisor(int local_u, int local_v)
  {
    if ((local_u == 0) || (local_v == 0))
    {
      if ((local_u == -2147483648) || (local_v == -2147483648)) {
        throw new ArithmeticException("overflow: gcd is 2^31");
      }
      return Math.abs(local_u) + Math.abs(local_v);
    }
    if ((Math.abs(local_u) == 1) || (Math.abs(local_v) == 1)) {
      return 1;
    }
    if (local_u > 0) {
      local_u = -local_u;
    }
    if (local_v > 0) {
      local_v = -local_v;
    }
    for (int local_k = 0; ((local_u & 0x1) == 0) && ((local_v & 0x1) == 0) && (local_k < 31); local_k++)
    {
      local_u /= 2;
      local_v /= 2;
    }
    if (local_k == 31) {
      throw new ArithmeticException("overflow: gcd is 2^31");
    }
    int local_t = (local_u & 0x1) == 1 ? local_v : -(local_u / 2);
    do
    {
      while ((local_t & 0x1) == 0) {
        local_t /= 2;
      }
      if (local_t > 0) {
        local_u = -local_t;
      } else {
        local_v = local_t;
      }
      local_t = (local_v - local_u) / 2;
    } while (local_t != 0);
    return -local_u * (1 << local_k);
  }
  
  private static int mulAndCheck(int local_x, int local_y)
  {
    long local_m = local_x * local_y;
    if ((local_m < -2147483648L) || (local_m > 2147483647L)) {
      throw new ArithmeticException("overflow: mul");
    }
    return (int)local_m;
  }
  
  private static int mulPosAndCheck(int local_x, int local_y)
  {
    long local_m = local_x * local_y;
    if (local_m > 2147483647L) {
      throw new ArithmeticException("overflow: mulPos");
    }
    return (int)local_m;
  }
  
  private static int addAndCheck(int local_x, int local_y)
  {
    long local_s = local_x + local_y;
    if ((local_s < -2147483648L) || (local_s > 2147483647L)) {
      throw new ArithmeticException("overflow: add");
    }
    return (int)local_s;
  }
  
  private static int subAndCheck(int local_x, int local_y)
  {
    long local_s = local_x - local_y;
    if ((local_s < -2147483648L) || (local_s > 2147483647L)) {
      throw new ArithmeticException("overflow: add");
    }
    return (int)local_s;
  }
  
  public Fraction add(Fraction fraction)
  {
    return addSub(fraction, true);
  }
  
  public Fraction subtract(Fraction fraction)
  {
    return addSub(fraction, false);
  }
  
  private Fraction addSub(Fraction fraction, boolean isAdd)
  {
    if (fraction == null) {
      throw new IllegalArgumentException("The fraction must not be null");
    }
    if (this.numerator == 0) {
      return isAdd ? fraction : fraction.negate();
    }
    if (fraction.numerator == 0) {
      return this;
    }
    int local_d1 = greatestCommonDivisor(this.denominator, fraction.denominator);
    if (local_d1 == 1)
    {
      int uvp = mulAndCheck(this.numerator, fraction.denominator);
      int upv = mulAndCheck(fraction.numerator, this.denominator);
      return new Fraction(isAdd ? addAndCheck(uvp, upv) : subAndCheck(uvp, upv), mulPosAndCheck(this.denominator, fraction.denominator));
    }
    BigInteger uvp = BigInteger.valueOf(this.numerator).multiply(BigInteger.valueOf(fraction.denominator / local_d1));
    BigInteger upv = BigInteger.valueOf(fraction.numerator).multiply(BigInteger.valueOf(this.denominator / local_d1));
    BigInteger local_t = isAdd ? uvp.add(upv) : uvp.subtract(upv);
    int tmodd1 = local_t.mod(BigInteger.valueOf(local_d1)).intValue();
    int local_d2 = tmodd1 == 0 ? local_d1 : greatestCommonDivisor(tmodd1, local_d1);
    BigInteger local_w = local_t.divide(BigInteger.valueOf(local_d2));
    if (local_w.bitLength() > 31) {
      throw new ArithmeticException("overflow: numerator too large after multiply");
    }
    return new Fraction(local_w.intValue(), mulPosAndCheck(this.denominator / local_d1, fraction.denominator / local_d2));
  }
  
  public Fraction multiplyBy(Fraction fraction)
  {
    if (fraction == null) {
      throw new IllegalArgumentException("The fraction must not be null");
    }
    if ((this.numerator == 0) || (fraction.numerator == 0)) {
      return ZERO;
    }
    int local_d1 = greatestCommonDivisor(this.numerator, fraction.denominator);
    int local_d2 = greatestCommonDivisor(fraction.numerator, this.denominator);
    return getReducedFraction(mulAndCheck(this.numerator / local_d1, fraction.numerator / local_d2), mulPosAndCheck(this.denominator / local_d2, fraction.denominator / local_d1));
  }
  
  public Fraction divideBy(Fraction fraction)
  {
    if (fraction == null) {
      throw new IllegalArgumentException("The fraction must not be null");
    }
    if (fraction.numerator == 0) {
      throw new ArithmeticException("The fraction to divide by must not be zero");
    }
    return multiplyBy(fraction.invert());
  }
  
  public boolean equals(Object obj)
  {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof Fraction)) {
      return false;
    }
    Fraction other = (Fraction)obj;
    return (getNumerator() == other.getNumerator()) && (getDenominator() == other.getDenominator());
  }
  
  public int hashCode()
  {
    if (this.hashCode == 0) {
      this.hashCode = (37 * (629 + getNumerator()) + getDenominator());
    }
    return this.hashCode;
  }
  
  public int compareTo(Fraction other)
  {
    if (this == other) {
      return 0;
    }
    if ((this.numerator == other.numerator) && (this.denominator == other.denominator)) {
      return 0;
    }
    long first = this.numerator * other.denominator;
    long second = other.numerator * this.denominator;
    if (first == second) {
      return 0;
    }
    if (first < second) {
      return -1;
    }
    return 1;
  }
  
  public String toString()
  {
    if (this.toString == null) {
      this.toString = (32 + getNumerator() + '/' + getDenominator());
    }
    return this.toString;
  }
  
  public String toProperString()
  {
    if (this.toProperString == null) {
      if (this.numerator == 0)
      {
        this.toProperString = "0";
      }
      else if (this.numerator == this.denominator)
      {
        this.toProperString = "1";
      }
      else if (this.numerator == -1 * this.denominator)
      {
        this.toProperString = "-1";
      }
      else if ((this.numerator > 0 ? -this.numerator : this.numerator) < -this.denominator)
      {
        int properNumerator = getProperNumerator();
        if (properNumerator == 0) {
          this.toProperString = Integer.toString(getProperWhole());
        } else {
          this.toProperString = (32 + getProperWhole() + ' ' + properNumerator + '/' + getDenominator());
        }
      }
      else
      {
        this.toProperString = (32 + getNumerator() + '/' + getDenominator());
      }
    }
    return this.toProperString;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.apache.commons.lang3.math.Fraction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */