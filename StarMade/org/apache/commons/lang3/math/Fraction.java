/*     */ package org.apache.commons.lang3.math;
/*     */ 
/*     */ import java.math.BigInteger;
/*     */ 
/*     */ public final class Fraction extends Number
/*     */   implements Comparable<Fraction>
/*     */ {
/*     */   private static final long serialVersionUID = 65382027393090L;
/*  47 */   public static final Fraction ZERO = new Fraction(0, 1);
/*     */ 
/*  51 */   public static final Fraction ONE = new Fraction(1, 1);
/*     */ 
/*  55 */   public static final Fraction ONE_HALF = new Fraction(1, 2);
/*     */ 
/*  59 */   public static final Fraction ONE_THIRD = new Fraction(1, 3);
/*     */ 
/*  63 */   public static final Fraction TWO_THIRDS = new Fraction(2, 3);
/*     */ 
/*  67 */   public static final Fraction ONE_QUARTER = new Fraction(1, 4);
/*     */ 
/*  71 */   public static final Fraction TWO_QUARTERS = new Fraction(2, 4);
/*     */ 
/*  75 */   public static final Fraction THREE_QUARTERS = new Fraction(3, 4);
/*     */ 
/*  79 */   public static final Fraction ONE_FIFTH = new Fraction(1, 5);
/*     */ 
/*  83 */   public static final Fraction TWO_FIFTHS = new Fraction(2, 5);
/*     */ 
/*  87 */   public static final Fraction THREE_FIFTHS = new Fraction(3, 5);
/*     */ 
/*  91 */   public static final Fraction FOUR_FIFTHS = new Fraction(4, 5);
/*     */   private final int numerator;
/*     */   private final int denominator;
/* 106 */   private transient int hashCode = 0;
/*     */ 
/* 110 */   private transient String toString = null;
/*     */ 
/* 114 */   private transient String toProperString = null;
/*     */ 
/*     */   private Fraction(int numerator, int denominator)
/*     */   {
/* 125 */     this.numerator = numerator;
/* 126 */     this.denominator = denominator;
/*     */   }
/*     */ 
/*     */   public static Fraction getFraction(int numerator, int denominator)
/*     */   {
/* 142 */     if (denominator == 0) {
/* 143 */       throw new ArithmeticException("The denominator must not be zero");
/*     */     }
/* 145 */     if (denominator < 0) {
/* 146 */       if ((numerator == -2147483648) || (denominator == -2147483648))
/*     */       {
/* 148 */         throw new ArithmeticException("overflow: can't negate");
/*     */       }
/* 150 */       numerator = -numerator;
/* 151 */       denominator = -denominator;
/*     */     }
/* 153 */     return new Fraction(numerator, denominator);
/*     */   }
/*     */ 
/*     */   public static Fraction getFraction(int whole, int numerator, int denominator)
/*     */   {
/* 173 */     if (denominator == 0) {
/* 174 */       throw new ArithmeticException("The denominator must not be zero");
/*     */     }
/* 176 */     if (denominator < 0) {
/* 177 */       throw new ArithmeticException("The denominator must not be negative");
/*     */     }
/* 179 */     if (numerator < 0)
/* 180 */       throw new ArithmeticException("The numerator must not be negative");
/*     */     long numeratorValue;
/*     */     long numeratorValue;
/* 183 */     if (whole < 0)
/* 184 */       numeratorValue = whole * denominator - numerator;
/*     */     else {
/* 186 */       numeratorValue = whole * denominator + numerator;
/*     */     }
/* 188 */     if ((numeratorValue < -2147483648L) || (numeratorValue > 2147483647L))
/*     */     {
/* 190 */       throw new ArithmeticException("Numerator too large to represent as an Integer.");
/*     */     }
/* 192 */     return new Fraction((int)numeratorValue, denominator);
/*     */   }
/*     */ 
/*     */   public static Fraction getReducedFraction(int numerator, int denominator)
/*     */   {
/* 210 */     if (denominator == 0) {
/* 211 */       throw new ArithmeticException("The denominator must not be zero");
/*     */     }
/* 213 */     if (numerator == 0) {
/* 214 */       return ZERO;
/*     */     }
/*     */ 
/* 217 */     if ((denominator == -2147483648) && ((numerator & 0x1) == 0)) {
/* 218 */       numerator /= 2; denominator /= 2;
/*     */     }
/* 220 */     if (denominator < 0) {
/* 221 */       if ((numerator == -2147483648) || (denominator == -2147483648))
/*     */       {
/* 223 */         throw new ArithmeticException("overflow: can't negate");
/*     */       }
/* 225 */       numerator = -numerator;
/* 226 */       denominator = -denominator;
/*     */     }
/*     */ 
/* 229 */     int gcd = greatestCommonDivisor(numerator, denominator);
/* 230 */     numerator /= gcd;
/* 231 */     denominator /= gcd;
/* 232 */     return new Fraction(numerator, denominator);
/*     */   }
/*     */ 
/*     */   public static Fraction getFraction(double value)
/*     */   {
/* 250 */     int sign = value < 0.0D ? -1 : 1;
/* 251 */     value = Math.abs(value);
/* 252 */     if ((value > 2147483647.0D) || (Double.isNaN(value))) {
/* 253 */       throw new ArithmeticException("The value must not be greater than Integer.MAX_VALUE or NaN"); } 
/*     */ int wholeNumber = (int)value;
/* 257 */     value -= wholeNumber;
/*     */ 
/* 259 */     int numer0 = 0;
/* 260 */     int denom0 = 1;
/* 261 */     int numer1 = 1;
/* 262 */     int denom1 = 0;
/* 263 */     int numer2 = 0;
/* 264 */     int denom2 = 0;
/* 265 */     int a1 = (int)value;
/* 266 */     int a2 = 0;
/* 267 */     double x1 = 1.0D;
/* 268 */     double x2 = 0.0D;
/* 269 */     double y1 = value - a1;
/* 270 */     double y2 = 0.0D;
/* 271 */     double delta2 = 1.7976931348623157E+308D;
/*     */ 
/* 273 */     int i = 1;
/*     */     double delta1;
/*     */     do { delta1 = delta2;
/* 277 */       a2 = (int)(x1 / y1);
/* 278 */       x2 = y1;
/* 279 */       y2 = x1 - a2 * y1;
/* 280 */       numer2 = a1 * numer1 + numer0;
/* 281 */       denom2 = a1 * denom1 + denom0;
/* 282 */       double fraction = numer2 / denom2;
/* 283 */       delta2 = Math.abs(value - fraction);
/*     */ 
/* 285 */       a1 = a2;
/* 286 */       x1 = x2;
/* 287 */       y1 = y2;
/* 288 */       numer0 = numer1;
/* 289 */       denom0 = denom1;
/* 290 */       numer1 = numer2;
/* 291 */       denom1 = denom2;
/* 292 */       i++;
/*     */     }
/* 294 */     while ((delta1 > delta2) && (denom2 <= 10000) && (denom2 > 0) && (i < 25));
/* 295 */     if (i == 25) {
/* 296 */       throw new ArithmeticException("Unable to convert double to fraction");
/*     */     }
/* 298 */     return getReducedFraction((numer0 + wholeNumber * denom0) * sign, denom0);
/*     */   }
/*     */ 
/*     */   public static Fraction getFraction(String str)
/*     */   {
/* 320 */     if (str == null) {
/* 321 */       throw new IllegalArgumentException("The string must not be null");
/*     */     }
/*     */ 
/* 324 */     int pos = str.indexOf('.');
/* 325 */     if (pos >= 0) {
/* 326 */       return getFraction(Double.parseDouble(str));
/*     */     }
/*     */ 
/* 330 */     pos = str.indexOf(' ');
/* 331 */     if (pos > 0) {
/* 332 */       int whole = Integer.parseInt(str.substring(0, pos));
/* 333 */       str = str.substring(pos + 1);
/* 334 */       pos = str.indexOf('/');
/* 335 */       if (pos < 0) {
/* 336 */         throw new NumberFormatException("The fraction could not be parsed as the format X Y/Z");
/*     */       }
/* 338 */       int numer = Integer.parseInt(str.substring(0, pos));
/* 339 */       int denom = Integer.parseInt(str.substring(pos + 1));
/* 340 */       return getFraction(whole, numer, denom);
/*     */     }
/*     */ 
/* 345 */     pos = str.indexOf('/');
/* 346 */     if (pos < 0)
/*     */     {
/* 348 */       return getFraction(Integer.parseInt(str), 1);
/*     */     }
/* 350 */     int numer = Integer.parseInt(str.substring(0, pos));
/* 351 */     int denom = Integer.parseInt(str.substring(pos + 1));
/* 352 */     return getFraction(numer, denom);
/*     */   }
/*     */ 
/*     */   public int getNumerator()
/*     */   {
/* 368 */     return this.numerator;
/*     */   }
/*     */ 
/*     */   public int getDenominator()
/*     */   {
/* 377 */     return this.denominator;
/*     */   }
/*     */ 
/*     */   public int getProperNumerator()
/*     */   {
/* 392 */     return Math.abs(this.numerator % this.denominator);
/*     */   }
/*     */ 
/*     */   public int getProperWhole()
/*     */   {
/* 407 */     return this.numerator / this.denominator;
/*     */   }
/*     */ 
/*     */   public int intValue()
/*     */   {
/* 421 */     return this.numerator / this.denominator;
/*     */   }
/*     */ 
/*     */   public long longValue()
/*     */   {
/* 432 */     return this.numerator / this.denominator;
/*     */   }
/*     */ 
/*     */   public float floatValue()
/*     */   {
/* 443 */     return this.numerator / this.denominator;
/*     */   }
/*     */ 
/*     */   public double doubleValue()
/*     */   {
/* 454 */     return this.numerator / this.denominator;
/*     */   }
/*     */ 
/*     */   public Fraction reduce()
/*     */   {
/* 470 */     if (this.numerator == 0) {
/* 471 */       return equals(ZERO) ? this : ZERO;
/*     */     }
/* 473 */     int gcd = greatestCommonDivisor(Math.abs(this.numerator), this.denominator);
/* 474 */     if (gcd == 1) {
/* 475 */       return this;
/*     */     }
/* 477 */     return getFraction(this.numerator / gcd, this.denominator / gcd);
/*     */   }
/*     */ 
/*     */   public Fraction invert()
/*     */   {
/* 490 */     if (this.numerator == 0) {
/* 491 */       throw new ArithmeticException("Unable to invert zero.");
/*     */     }
/* 493 */     if (this.numerator == -2147483648) {
/* 494 */       throw new ArithmeticException("overflow: can't negate numerator");
/*     */     }
/* 496 */     if (this.numerator < 0) {
/* 497 */       return new Fraction(-this.denominator, -this.numerator);
/*     */     }
/* 499 */     return new Fraction(this.denominator, this.numerator);
/*     */   }
/*     */ 
/*     */   public Fraction negate()
/*     */   {
/* 512 */     if (this.numerator == -2147483648) {
/* 513 */       throw new ArithmeticException("overflow: too large to negate");
/*     */     }
/* 515 */     return new Fraction(-this.numerator, this.denominator);
/*     */   }
/*     */ 
/*     */   public Fraction abs()
/*     */   {
/* 528 */     if (this.numerator >= 0) {
/* 529 */       return this;
/*     */     }
/* 531 */     return negate();
/*     */   }
/*     */ 
/*     */   public Fraction pow(int power)
/*     */   {
/* 547 */     if (power == 1)
/* 548 */       return this;
/* 549 */     if (power == 0)
/* 550 */       return ONE;
/* 551 */     if (power < 0) {
/* 552 */       if (power == -2147483648) {
/* 553 */         return invert().pow(2).pow(-(power / 2));
/*     */       }
/* 555 */       return invert().pow(-power);
/*     */     }
/* 557 */     Fraction f = multiplyBy(this);
/* 558 */     if (power % 2 == 0) {
/* 559 */       return f.pow(power / 2);
/*     */     }
/* 561 */     return f.pow(power / 2).multiplyBy(this);
/*     */   }
/*     */ 
/*     */   private static int greatestCommonDivisor(int u, int v)
/*     */   {
/* 578 */     if ((u == 0) || (v == 0)) {
/* 579 */       if ((u == -2147483648) || (v == -2147483648)) {
/* 580 */         throw new ArithmeticException("overflow: gcd is 2^31");
/*     */       }
/* 582 */       return Math.abs(u) + Math.abs(v);
/*     */     }
/*     */ 
/* 585 */     if ((Math.abs(u) == 1) || (Math.abs(v) == 1)) {
/* 586 */       return 1;
/*     */     }
/*     */ 
/* 592 */     if (u > 0) u = -u;
/* 593 */     if (v > 0) v = -v;
/*     */ 
/* 595 */     for (int k = 0; 
/* 596 */       ((u & 0x1) == 0) && ((v & 0x1) == 0) && (k < 31); 
/* 597 */       k++) { u /= 2; v /= 2;
/*     */     }
/* 599 */     if (k == 31) {
/* 600 */       throw new ArithmeticException("overflow: gcd is 2^31");
/*     */     }
/*     */ 
/* 604 */     int t = (u & 0x1) == 1 ? v : -(u / 2);
/*     */     do
/*     */     {
/* 610 */       while ((t & 0x1) == 0) {
/* 611 */         t /= 2;
/*     */       }
/*     */ 
/* 614 */       if (t > 0)
/* 615 */         u = -t;
/*     */       else {
/* 617 */         v = t;
/*     */       }
/*     */ 
/* 620 */       t = (v - u) / 2;
/*     */     }
/*     */ 
/* 623 */     while (t != 0);
/* 624 */     return -u * (1 << k);
/*     */   }
/*     */ 
/*     */   private static int mulAndCheck(int x, int y)
/*     */   {
/* 640 */     long m = x * y;
/* 641 */     if ((m < -2147483648L) || (m > 2147483647L))
/*     */     {
/* 643 */       throw new ArithmeticException("overflow: mul");
/*     */     }
/* 645 */     return (int)m;
/*     */   }
/*     */ 
/*     */   private static int mulPosAndCheck(int x, int y)
/*     */   {
/* 659 */     long m = x * y;
/* 660 */     if (m > 2147483647L) {
/* 661 */       throw new ArithmeticException("overflow: mulPos");
/*     */     }
/* 663 */     return (int)m;
/*     */   }
/*     */ 
/*     */   private static int addAndCheck(int x, int y)
/*     */   {
/* 676 */     long s = x + y;
/* 677 */     if ((s < -2147483648L) || (s > 2147483647L))
/*     */     {
/* 679 */       throw new ArithmeticException("overflow: add");
/*     */     }
/* 681 */     return (int)s;
/*     */   }
/*     */ 
/*     */   private static int subAndCheck(int x, int y)
/*     */   {
/* 694 */     long s = x - y;
/* 695 */     if ((s < -2147483648L) || (s > 2147483647L))
/*     */     {
/* 697 */       throw new ArithmeticException("overflow: add");
/*     */     }
/* 699 */     return (int)s;
/*     */   }
/*     */ 
/*     */   public Fraction add(Fraction fraction)
/*     */   {
/* 713 */     return addSub(fraction, true);
/*     */   }
/*     */ 
/*     */   public Fraction subtract(Fraction fraction)
/*     */   {
/* 727 */     return addSub(fraction, false);
/*     */   }
/*     */ 
/*     */   private Fraction addSub(Fraction fraction, boolean isAdd)
/*     */   {
/* 741 */     if (fraction == null) {
/* 742 */       throw new IllegalArgumentException("The fraction must not be null");
/*     */     }
/*     */ 
/* 745 */     if (this.numerator == 0) {
/* 746 */       return isAdd ? fraction : fraction.negate();
/*     */     }
/* 748 */     if (fraction.numerator == 0) {
/* 749 */       return this;
/*     */     }
/*     */ 
/* 753 */     int d1 = greatestCommonDivisor(this.denominator, fraction.denominator);
/* 754 */     if (d1 == 1)
/*     */     {
/* 756 */       int uvp = mulAndCheck(this.numerator, fraction.denominator);
/* 757 */       int upv = mulAndCheck(fraction.numerator, this.denominator);
/* 758 */       return new Fraction(isAdd ? addAndCheck(uvp, upv) : subAndCheck(uvp, upv), mulPosAndCheck(this.denominator, fraction.denominator));
/*     */     }
/*     */ 
/* 765 */     BigInteger uvp = BigInteger.valueOf(this.numerator).multiply(BigInteger.valueOf(fraction.denominator / d1));
/*     */ 
/* 767 */     BigInteger upv = BigInteger.valueOf(fraction.numerator).multiply(BigInteger.valueOf(this.denominator / d1));
/*     */ 
/* 769 */     BigInteger t = isAdd ? uvp.add(upv) : uvp.subtract(upv);
/*     */ 
/* 772 */     int tmodd1 = t.mod(BigInteger.valueOf(d1)).intValue();
/* 773 */     int d2 = tmodd1 == 0 ? d1 : greatestCommonDivisor(tmodd1, d1);
/*     */ 
/* 776 */     BigInteger w = t.divide(BigInteger.valueOf(d2));
/* 777 */     if (w.bitLength() > 31) {
/* 778 */       throw new ArithmeticException("overflow: numerator too large after multiply");
/*     */     }
/*     */ 
/* 781 */     return new Fraction(w.intValue(), mulPosAndCheck(this.denominator / d1, fraction.denominator / d2));
/*     */   }
/*     */ 
/*     */   public Fraction multiplyBy(Fraction fraction)
/*     */   {
/* 797 */     if (fraction == null) {
/* 798 */       throw new IllegalArgumentException("The fraction must not be null");
/*     */     }
/* 800 */     if ((this.numerator == 0) || (fraction.numerator == 0)) {
/* 801 */       return ZERO;
/*     */     }
/*     */ 
/* 805 */     int d1 = greatestCommonDivisor(this.numerator, fraction.denominator);
/* 806 */     int d2 = greatestCommonDivisor(fraction.numerator, this.denominator);
/* 807 */     return getReducedFraction(mulAndCheck(this.numerator / d1, fraction.numerator / d2), mulPosAndCheck(this.denominator / d2, fraction.denominator / d1));
/*     */   }
/*     */ 
/*     */   public Fraction divideBy(Fraction fraction)
/*     */   {
/* 823 */     if (fraction == null) {
/* 824 */       throw new IllegalArgumentException("The fraction must not be null");
/*     */     }
/* 826 */     if (fraction.numerator == 0) {
/* 827 */       throw new ArithmeticException("The fraction to divide by must not be zero");
/*     */     }
/* 829 */     return multiplyBy(fraction.invert());
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 845 */     if (obj == this) {
/* 846 */       return true;
/*     */     }
/* 848 */     if (!(obj instanceof Fraction)) {
/* 849 */       return false;
/*     */     }
/* 851 */     Fraction other = (Fraction)obj;
/* 852 */     return (getNumerator() == other.getNumerator()) && (getDenominator() == other.getDenominator());
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 863 */     if (this.hashCode == 0)
/*     */     {
/* 865 */       this.hashCode = (37 * (629 + getNumerator()) + getDenominator());
/*     */     }
/* 867 */     return this.hashCode;
/*     */   }
/*     */ 
/*     */   public int compareTo(Fraction other)
/*     */   {
/* 883 */     if (this == other) {
/* 884 */       return 0;
/*     */     }
/* 886 */     if ((this.numerator == other.numerator) && (this.denominator == other.denominator)) {
/* 887 */       return 0;
/*     */     }
/*     */ 
/* 891 */     long first = this.numerator * other.denominator;
/* 892 */     long second = other.numerator * this.denominator;
/* 893 */     if (first == second)
/* 894 */       return 0;
/* 895 */     if (first < second) {
/* 896 */       return -1;
/*     */     }
/* 898 */     return 1;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 911 */     if (this.toString == null) {
/* 912 */       this.toString = (32 + getNumerator() + '/' + getDenominator());
/*     */     }
/*     */ 
/* 917 */     return this.toString;
/*     */   }
/*     */ 
/*     */   public String toProperString()
/*     */   {
/* 930 */     if (this.toProperString == null) {
/* 931 */       if (this.numerator == 0) {
/* 932 */         this.toProperString = "0";
/* 933 */       } else if (this.numerator == this.denominator) {
/* 934 */         this.toProperString = "1";
/* 935 */       } else if (this.numerator == -1 * this.denominator) {
/* 936 */         this.toProperString = "-1";
/* 937 */       } else if ((this.numerator > 0 ? -this.numerator : this.numerator) < -this.denominator)
/*     */       {
/* 942 */         int properNumerator = getProperNumerator();
/* 943 */         if (properNumerator == 0)
/* 944 */           this.toProperString = Integer.toString(getProperWhole());
/*     */         else {
/* 946 */           this.toProperString = (32 + getProperWhole() + ' ' + properNumerator + '/' + getDenominator());
/*     */         }
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/* 952 */         this.toProperString = (32 + getNumerator() + '/' + getDenominator());
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 957 */     return this.toProperString;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.math.Fraction
 * JD-Core Version:    0.6.2
 */