/*   1:    */package org.apache.commons.lang3.math;
/*   2:    */
/*   3:    */import java.math.BigInteger;
/*   4:    */
/*  42:    */public final class Fraction
/*  43:    */  extends Number
/*  44:    */  implements Comparable<Fraction>
/*  45:    */{
/*  46:    */  private static final long serialVersionUID = 65382027393090L;
/*  47: 47 */  public static final Fraction ZERO = new Fraction(0, 1);
/*  48:    */  
/*  51: 51 */  public static final Fraction ONE = new Fraction(1, 1);
/*  52:    */  
/*  55: 55 */  public static final Fraction ONE_HALF = new Fraction(1, 2);
/*  56:    */  
/*  59: 59 */  public static final Fraction ONE_THIRD = new Fraction(1, 3);
/*  60:    */  
/*  63: 63 */  public static final Fraction TWO_THIRDS = new Fraction(2, 3);
/*  64:    */  
/*  67: 67 */  public static final Fraction ONE_QUARTER = new Fraction(1, 4);
/*  68:    */  
/*  71: 71 */  public static final Fraction TWO_QUARTERS = new Fraction(2, 4);
/*  72:    */  
/*  75: 75 */  public static final Fraction THREE_QUARTERS = new Fraction(3, 4);
/*  76:    */  
/*  79: 79 */  public static final Fraction ONE_FIFTH = new Fraction(1, 5);
/*  80:    */  
/*  83: 83 */  public static final Fraction TWO_FIFTHS = new Fraction(2, 5);
/*  84:    */  
/*  87: 87 */  public static final Fraction THREE_FIFTHS = new Fraction(3, 5);
/*  88:    */  
/*  91: 91 */  public static final Fraction FOUR_FIFTHS = new Fraction(4, 5);
/*  92:    */  
/*  96:    */  private final int numerator;
/*  97:    */  
/* 101:    */  private final int denominator;
/* 102:    */  
/* 106:106 */  private transient int hashCode = 0;
/* 107:    */  
/* 110:110 */  private transient String toString = null;
/* 111:    */  
/* 114:114 */  private transient String toProperString = null;
/* 115:    */  
/* 123:    */  private Fraction(int numerator, int denominator)
/* 124:    */  {
/* 125:125 */    this.numerator = numerator;
/* 126:126 */    this.denominator = denominator;
/* 127:    */  }
/* 128:    */  
/* 140:    */  public static Fraction getFraction(int numerator, int denominator)
/* 141:    */  {
/* 142:142 */    if (denominator == 0) {
/* 143:143 */      throw new ArithmeticException("The denominator must not be zero");
/* 144:    */    }
/* 145:145 */    if (denominator < 0) {
/* 146:146 */      if ((numerator == -2147483648) || (denominator == -2147483648))
/* 147:    */      {
/* 148:148 */        throw new ArithmeticException("overflow: can't negate");
/* 149:    */      }
/* 150:150 */      numerator = -numerator;
/* 151:151 */      denominator = -denominator;
/* 152:    */    }
/* 153:153 */    return new Fraction(numerator, denominator);
/* 154:    */  }
/* 155:    */  
/* 171:    */  public static Fraction getFraction(int whole, int numerator, int denominator)
/* 172:    */  {
/* 173:173 */    if (denominator == 0) {
/* 174:174 */      throw new ArithmeticException("The denominator must not be zero");
/* 175:    */    }
/* 176:176 */    if (denominator < 0) {
/* 177:177 */      throw new ArithmeticException("The denominator must not be negative");
/* 178:    */    }
/* 179:179 */    if (numerator < 0)
/* 180:180 */      throw new ArithmeticException("The numerator must not be negative");
/* 181:    */    long numeratorValue;
/* 182:    */    long numeratorValue;
/* 183:183 */    if (whole < 0) {
/* 184:184 */      numeratorValue = whole * denominator - numerator;
/* 185:    */    } else {
/* 186:186 */      numeratorValue = whole * denominator + numerator;
/* 187:    */    }
/* 188:188 */    if ((numeratorValue < -2147483648L) || (numeratorValue > 2147483647L))
/* 189:    */    {
/* 190:190 */      throw new ArithmeticException("Numerator too large to represent as an Integer.");
/* 191:    */    }
/* 192:192 */    return new Fraction((int)numeratorValue, denominator);
/* 193:    */  }
/* 194:    */  
/* 208:    */  public static Fraction getReducedFraction(int numerator, int denominator)
/* 209:    */  {
/* 210:210 */    if (denominator == 0) {
/* 211:211 */      throw new ArithmeticException("The denominator must not be zero");
/* 212:    */    }
/* 213:213 */    if (numerator == 0) {
/* 214:214 */      return ZERO;
/* 215:    */    }
/* 216:    */    
/* 217:217 */    if ((denominator == -2147483648) && ((numerator & 0x1) == 0)) {
/* 218:218 */      numerator /= 2;denominator /= 2;
/* 219:    */    }
/* 220:220 */    if (denominator < 0) {
/* 221:221 */      if ((numerator == -2147483648) || (denominator == -2147483648))
/* 222:    */      {
/* 223:223 */        throw new ArithmeticException("overflow: can't negate");
/* 224:    */      }
/* 225:225 */      numerator = -numerator;
/* 226:226 */      denominator = -denominator;
/* 227:    */    }
/* 228:    */    
/* 229:229 */    int gcd = greatestCommonDivisor(numerator, denominator);
/* 230:230 */    numerator /= gcd;
/* 231:231 */    denominator /= gcd;
/* 232:232 */    return new Fraction(numerator, denominator);
/* 233:    */  }
/* 234:    */  
/* 248:    */  public static Fraction getFraction(double value)
/* 249:    */  {
/* 250:250 */    int sign = value < 0.0D ? -1 : 1;
/* 251:251 */    value = Math.abs(value);
/* 252:252 */    if ((value > 2147483647.0D) || (Double.isNaN(value))) {
/* 253:253 */      throw new ArithmeticException("The value must not be greater than Integer.MAX_VALUE or NaN");
/* 254:    */    }
/* 255:    */    
/* 256:256 */    int wholeNumber = (int)value;
/* 257:257 */    value -= wholeNumber;
/* 258:    */    
/* 259:259 */    int numer0 = 0;
/* 260:260 */    int denom0 = 1;
/* 261:261 */    int numer1 = 1;
/* 262:262 */    int denom1 = 0;
/* 263:263 */    int numer2 = 0;
/* 264:264 */    int denom2 = 0;
/* 265:265 */    int a1 = (int)value;
/* 266:266 */    int a2 = 0;
/* 267:267 */    double x1 = 1.0D;
/* 268:268 */    double x2 = 0.0D;
/* 269:269 */    double y1 = value - a1;
/* 270:270 */    double y2 = 0.0D;
/* 271:271 */    double delta2 = 1.7976931348623157E+308D;
/* 272:    */    
/* 273:273 */    int i = 1;
/* 274:    */    double delta1;
/* 275:    */    do {
/* 276:276 */      delta1 = delta2;
/* 277:277 */      a2 = (int)(x1 / y1);
/* 278:278 */      x2 = y1;
/* 279:279 */      y2 = x1 - a2 * y1;
/* 280:280 */      numer2 = a1 * numer1 + numer0;
/* 281:281 */      denom2 = a1 * denom1 + denom0;
/* 282:282 */      double fraction = numer2 / denom2;
/* 283:283 */      delta2 = Math.abs(value - fraction);
/* 284:    */      
/* 285:285 */      a1 = a2;
/* 286:286 */      x1 = x2;
/* 287:287 */      y1 = y2;
/* 288:288 */      numer0 = numer1;
/* 289:289 */      denom0 = denom1;
/* 290:290 */      numer1 = numer2;
/* 291:291 */      denom1 = denom2;
/* 292:292 */      i++;
/* 293:    */    }
/* 294:294 */    while ((delta1 > delta2) && (denom2 <= 10000) && (denom2 > 0) && (i < 25));
/* 295:295 */    if (i == 25) {
/* 296:296 */      throw new ArithmeticException("Unable to convert double to fraction");
/* 297:    */    }
/* 298:298 */    return getReducedFraction((numer0 + wholeNumber * denom0) * sign, denom0);
/* 299:    */  }
/* 300:    */  
/* 318:    */  public static Fraction getFraction(String str)
/* 319:    */  {
/* 320:320 */    if (str == null) {
/* 321:321 */      throw new IllegalArgumentException("The string must not be null");
/* 322:    */    }
/* 323:    */    
/* 324:324 */    int pos = str.indexOf('.');
/* 325:325 */    if (pos >= 0) {
/* 326:326 */      return getFraction(Double.parseDouble(str));
/* 327:    */    }
/* 328:    */    
/* 330:330 */    pos = str.indexOf(' ');
/* 331:331 */    if (pos > 0) {
/* 332:332 */      int whole = Integer.parseInt(str.substring(0, pos));
/* 333:333 */      str = str.substring(pos + 1);
/* 334:334 */      pos = str.indexOf('/');
/* 335:335 */      if (pos < 0) {
/* 336:336 */        throw new NumberFormatException("The fraction could not be parsed as the format X Y/Z");
/* 337:    */      }
/* 338:338 */      int numer = Integer.parseInt(str.substring(0, pos));
/* 339:339 */      int denom = Integer.parseInt(str.substring(pos + 1));
/* 340:340 */      return getFraction(whole, numer, denom);
/* 341:    */    }
/* 342:    */    
/* 345:345 */    pos = str.indexOf('/');
/* 346:346 */    if (pos < 0)
/* 347:    */    {
/* 348:348 */      return getFraction(Integer.parseInt(str), 1);
/* 349:    */    }
/* 350:350 */    int numer = Integer.parseInt(str.substring(0, pos));
/* 351:351 */    int denom = Integer.parseInt(str.substring(pos + 1));
/* 352:352 */    return getFraction(numer, denom);
/* 353:    */  }
/* 354:    */  
/* 366:    */  public int getNumerator()
/* 367:    */  {
/* 368:368 */    return this.numerator;
/* 369:    */  }
/* 370:    */  
/* 375:    */  public int getDenominator()
/* 376:    */  {
/* 377:377 */    return this.denominator;
/* 378:    */  }
/* 379:    */  
/* 390:    */  public int getProperNumerator()
/* 391:    */  {
/* 392:392 */    return Math.abs(this.numerator % this.denominator);
/* 393:    */  }
/* 394:    */  
/* 405:    */  public int getProperWhole()
/* 406:    */  {
/* 407:407 */    return this.numerator / this.denominator;
/* 408:    */  }
/* 409:    */  
/* 419:    */  public int intValue()
/* 420:    */  {
/* 421:421 */    return this.numerator / this.denominator;
/* 422:    */  }
/* 423:    */  
/* 430:    */  public long longValue()
/* 431:    */  {
/* 432:432 */    return this.numerator / this.denominator;
/* 433:    */  }
/* 434:    */  
/* 441:    */  public float floatValue()
/* 442:    */  {
/* 443:443 */    return this.numerator / this.denominator;
/* 444:    */  }
/* 445:    */  
/* 452:    */  public double doubleValue()
/* 453:    */  {
/* 454:454 */    return this.numerator / this.denominator;
/* 455:    */  }
/* 456:    */  
/* 468:    */  public Fraction reduce()
/* 469:    */  {
/* 470:470 */    if (this.numerator == 0) {
/* 471:471 */      return equals(ZERO) ? this : ZERO;
/* 472:    */    }
/* 473:473 */    int gcd = greatestCommonDivisor(Math.abs(this.numerator), this.denominator);
/* 474:474 */    if (gcd == 1) {
/* 475:475 */      return this;
/* 476:    */    }
/* 477:477 */    return getFraction(this.numerator / gcd, this.denominator / gcd);
/* 478:    */  }
/* 479:    */  
/* 488:    */  public Fraction invert()
/* 489:    */  {
/* 490:490 */    if (this.numerator == 0) {
/* 491:491 */      throw new ArithmeticException("Unable to invert zero.");
/* 492:    */    }
/* 493:493 */    if (this.numerator == -2147483648) {
/* 494:494 */      throw new ArithmeticException("overflow: can't negate numerator");
/* 495:    */    }
/* 496:496 */    if (this.numerator < 0) {
/* 497:497 */      return new Fraction(-this.denominator, -this.numerator);
/* 498:    */    }
/* 499:499 */    return new Fraction(this.denominator, this.numerator);
/* 500:    */  }
/* 501:    */  
/* 510:    */  public Fraction negate()
/* 511:    */  {
/* 512:512 */    if (this.numerator == -2147483648) {
/* 513:513 */      throw new ArithmeticException("overflow: too large to negate");
/* 514:    */    }
/* 515:515 */    return new Fraction(-this.numerator, this.denominator);
/* 516:    */  }
/* 517:    */  
/* 526:    */  public Fraction abs()
/* 527:    */  {
/* 528:528 */    if (this.numerator >= 0) {
/* 529:529 */      return this;
/* 530:    */    }
/* 531:531 */    return negate();
/* 532:    */  }
/* 533:    */  
/* 545:    */  public Fraction pow(int power)
/* 546:    */  {
/* 547:547 */    if (power == 1)
/* 548:548 */      return this;
/* 549:549 */    if (power == 0)
/* 550:550 */      return ONE;
/* 551:551 */    if (power < 0) {
/* 552:552 */      if (power == -2147483648) {
/* 553:553 */        return invert().pow(2).pow(-(power / 2));
/* 554:    */      }
/* 555:555 */      return invert().pow(-power);
/* 556:    */    }
/* 557:557 */    Fraction f = multiplyBy(this);
/* 558:558 */    if (power % 2 == 0) {
/* 559:559 */      return f.pow(power / 2);
/* 560:    */    }
/* 561:561 */    return f.pow(power / 2).multiplyBy(this);
/* 562:    */  }
/* 563:    */  
/* 576:    */  private static int greatestCommonDivisor(int u, int v)
/* 577:    */  {
/* 578:578 */    if ((u == 0) || (v == 0)) {
/* 579:579 */      if ((u == -2147483648) || (v == -2147483648)) {
/* 580:580 */        throw new ArithmeticException("overflow: gcd is 2^31");
/* 581:    */      }
/* 582:582 */      return Math.abs(u) + Math.abs(v);
/* 583:    */    }
/* 584:    */    
/* 585:585 */    if ((Math.abs(u) == 1) || (Math.abs(v) == 1)) {
/* 586:586 */      return 1;
/* 587:    */    }
/* 588:    */    
/* 592:592 */    if (u > 0) u = -u;
/* 593:593 */    if (v > 0) { v = -v;
/* 594:    */    }
/* 595:595 */    for (int k = 0; 
/* 596:596 */        ((u & 0x1) == 0) && ((v & 0x1) == 0) && (k < 31); 
/* 597:597 */        k++) { u /= 2;v /= 2;
/* 598:    */    }
/* 599:599 */    if (k == 31) {
/* 600:600 */      throw new ArithmeticException("overflow: gcd is 2^31");
/* 601:    */    }
/* 602:    */    
/* 604:604 */    int t = (u & 0x1) == 1 ? v : -(u / 2);
/* 605:    */    
/* 608:    */    do
/* 609:    */    {
/* 610:610 */      while ((t & 0x1) == 0) {
/* 611:611 */        t /= 2;
/* 612:    */      }
/* 613:    */      
/* 614:614 */      if (t > 0) {
/* 615:615 */        u = -t;
/* 616:    */      } else {
/* 617:617 */        v = t;
/* 618:    */      }
/* 619:    */      
/* 620:620 */      t = (v - u) / 2;
/* 622:    */    }
/* 623:623 */    while (t != 0);
/* 624:624 */    return -u * (1 << k);
/* 625:    */  }
/* 626:    */  
/* 638:    */  private static int mulAndCheck(int x, int y)
/* 639:    */  {
/* 640:640 */    long m = x * y;
/* 641:641 */    if ((m < -2147483648L) || (m > 2147483647L))
/* 642:    */    {
/* 643:643 */      throw new ArithmeticException("overflow: mul");
/* 644:    */    }
/* 645:645 */    return (int)m;
/* 646:    */  }
/* 647:    */  
/* 657:    */  private static int mulPosAndCheck(int x, int y)
/* 658:    */  {
/* 659:659 */    long m = x * y;
/* 660:660 */    if (m > 2147483647L) {
/* 661:661 */      throw new ArithmeticException("overflow: mulPos");
/* 662:    */    }
/* 663:663 */    return (int)m;
/* 664:    */  }
/* 665:    */  
/* 674:    */  private static int addAndCheck(int x, int y)
/* 675:    */  {
/* 676:676 */    long s = x + y;
/* 677:677 */    if ((s < -2147483648L) || (s > 2147483647L))
/* 678:    */    {
/* 679:679 */      throw new ArithmeticException("overflow: add");
/* 680:    */    }
/* 681:681 */    return (int)s;
/* 682:    */  }
/* 683:    */  
/* 692:    */  private static int subAndCheck(int x, int y)
/* 693:    */  {
/* 694:694 */    long s = x - y;
/* 695:695 */    if ((s < -2147483648L) || (s > 2147483647L))
/* 696:    */    {
/* 697:697 */      throw new ArithmeticException("overflow: add");
/* 698:    */    }
/* 699:699 */    return (int)s;
/* 700:    */  }
/* 701:    */  
/* 711:    */  public Fraction add(Fraction fraction)
/* 712:    */  {
/* 713:713 */    return addSub(fraction, true);
/* 714:    */  }
/* 715:    */  
/* 725:    */  public Fraction subtract(Fraction fraction)
/* 726:    */  {
/* 727:727 */    return addSub(fraction, false);
/* 728:    */  }
/* 729:    */  
/* 739:    */  private Fraction addSub(Fraction fraction, boolean isAdd)
/* 740:    */  {
/* 741:741 */    if (fraction == null) {
/* 742:742 */      throw new IllegalArgumentException("The fraction must not be null");
/* 743:    */    }
/* 744:    */    
/* 745:745 */    if (this.numerator == 0) {
/* 746:746 */      return isAdd ? fraction : fraction.negate();
/* 747:    */    }
/* 748:748 */    if (fraction.numerator == 0) {
/* 749:749 */      return this;
/* 750:    */    }
/* 751:    */    
/* 753:753 */    int d1 = greatestCommonDivisor(this.denominator, fraction.denominator);
/* 754:754 */    if (d1 == 1)
/* 755:    */    {
/* 756:756 */      int uvp = mulAndCheck(this.numerator, fraction.denominator);
/* 757:757 */      int upv = mulAndCheck(fraction.numerator, this.denominator);
/* 758:758 */      return new Fraction(isAdd ? addAndCheck(uvp, upv) : subAndCheck(uvp, upv), mulPosAndCheck(this.denominator, fraction.denominator));
/* 759:    */    }
/* 760:    */    
/* 765:765 */    BigInteger uvp = BigInteger.valueOf(this.numerator).multiply(BigInteger.valueOf(fraction.denominator / d1));
/* 766:    */    
/* 767:767 */    BigInteger upv = BigInteger.valueOf(fraction.numerator).multiply(BigInteger.valueOf(this.denominator / d1));
/* 768:    */    
/* 769:769 */    BigInteger t = isAdd ? uvp.add(upv) : uvp.subtract(upv);
/* 770:    */    
/* 772:772 */    int tmodd1 = t.mod(BigInteger.valueOf(d1)).intValue();
/* 773:773 */    int d2 = tmodd1 == 0 ? d1 : greatestCommonDivisor(tmodd1, d1);
/* 774:    */    
/* 776:776 */    BigInteger w = t.divide(BigInteger.valueOf(d2));
/* 777:777 */    if (w.bitLength() > 31) {
/* 778:778 */      throw new ArithmeticException("overflow: numerator too large after multiply");
/* 779:    */    }
/* 780:    */    
/* 781:781 */    return new Fraction(w.intValue(), mulPosAndCheck(this.denominator / d1, fraction.denominator / d2));
/* 782:    */  }
/* 783:    */  
/* 795:    */  public Fraction multiplyBy(Fraction fraction)
/* 796:    */  {
/* 797:797 */    if (fraction == null) {
/* 798:798 */      throw new IllegalArgumentException("The fraction must not be null");
/* 799:    */    }
/* 800:800 */    if ((this.numerator == 0) || (fraction.numerator == 0)) {
/* 801:801 */      return ZERO;
/* 802:    */    }
/* 803:    */    
/* 805:805 */    int d1 = greatestCommonDivisor(this.numerator, fraction.denominator);
/* 806:806 */    int d2 = greatestCommonDivisor(fraction.numerator, this.denominator);
/* 807:807 */    return getReducedFraction(mulAndCheck(this.numerator / d1, fraction.numerator / d2), mulPosAndCheck(this.denominator / d2, fraction.denominator / d1));
/* 808:    */  }
/* 809:    */  
/* 821:    */  public Fraction divideBy(Fraction fraction)
/* 822:    */  {
/* 823:823 */    if (fraction == null) {
/* 824:824 */      throw new IllegalArgumentException("The fraction must not be null");
/* 825:    */    }
/* 826:826 */    if (fraction.numerator == 0) {
/* 827:827 */      throw new ArithmeticException("The fraction to divide by must not be zero");
/* 828:    */    }
/* 829:829 */    return multiplyBy(fraction.invert());
/* 830:    */  }
/* 831:    */  
/* 843:    */  public boolean equals(Object obj)
/* 844:    */  {
/* 845:845 */    if (obj == this) {
/* 846:846 */      return true;
/* 847:    */    }
/* 848:848 */    if (!(obj instanceof Fraction)) {
/* 849:849 */      return false;
/* 850:    */    }
/* 851:851 */    Fraction other = (Fraction)obj;
/* 852:852 */    return (getNumerator() == other.getNumerator()) && (getDenominator() == other.getDenominator());
/* 853:    */  }
/* 854:    */  
/* 861:    */  public int hashCode()
/* 862:    */  {
/* 863:863 */    if (this.hashCode == 0)
/* 864:    */    {
/* 865:865 */      this.hashCode = (37 * (629 + getNumerator()) + getDenominator());
/* 866:    */    }
/* 867:867 */    return this.hashCode;
/* 868:    */  }
/* 869:    */  
/* 881:    */  public int compareTo(Fraction other)
/* 882:    */  {
/* 883:883 */    if (this == other) {
/* 884:884 */      return 0;
/* 885:    */    }
/* 886:886 */    if ((this.numerator == other.numerator) && (this.denominator == other.denominator)) {
/* 887:887 */      return 0;
/* 888:    */    }
/* 889:    */    
/* 891:891 */    long first = this.numerator * other.denominator;
/* 892:892 */    long second = other.numerator * this.denominator;
/* 893:893 */    if (first == second)
/* 894:894 */      return 0;
/* 895:895 */    if (first < second) {
/* 896:896 */      return -1;
/* 897:    */    }
/* 898:898 */    return 1;
/* 899:    */  }
/* 900:    */  
/* 909:    */  public String toString()
/* 910:    */  {
/* 911:911 */    if (this.toString == null) {
/* 912:912 */      this.toString = (32 + getNumerator() + '/' + getDenominator());
/* 913:    */    }
/* 914:    */    
/* 917:917 */    return this.toString;
/* 918:    */  }
/* 919:    */  
/* 928:    */  public String toProperString()
/* 929:    */  {
/* 930:930 */    if (this.toProperString == null) {
/* 931:931 */      if (this.numerator == 0) {
/* 932:932 */        this.toProperString = "0";
/* 933:933 */      } else if (this.numerator == this.denominator) {
/* 934:934 */        this.toProperString = "1";
/* 935:935 */      } else if (this.numerator == -1 * this.denominator) {
/* 936:936 */        this.toProperString = "-1";
/* 937:937 */      } else if ((this.numerator > 0 ? -this.numerator : this.numerator) < -this.denominator)
/* 938:    */      {
/* 942:942 */        int properNumerator = getProperNumerator();
/* 943:943 */        if (properNumerator == 0) {
/* 944:944 */          this.toProperString = Integer.toString(getProperWhole());
/* 945:    */        } else {
/* 946:946 */          this.toProperString = (32 + getProperWhole() + ' ' + properNumerator + '/' + getDenominator());
/* 947:    */        }
/* 948:    */        
/* 949:    */      }
/* 950:    */      else
/* 951:    */      {
/* 952:952 */        this.toProperString = (32 + getNumerator() + '/' + getDenominator());
/* 953:    */      }
/* 954:    */    }
/* 955:    */    
/* 957:957 */    return this.toProperString;
/* 958:    */  }
/* 959:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.math.Fraction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */