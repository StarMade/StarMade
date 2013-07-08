/*   1:    */package org.apache.commons.lang3.builder;
/*   2:    */
/*   3:    */import java.lang.reflect.AccessibleObject;
/*   4:    */import java.lang.reflect.Field;
/*   5:    */import java.lang.reflect.Modifier;
/*   6:    */import java.util.Collection;
/*   7:    */import java.util.HashSet;
/*   8:    */import java.util.Set;
/*   9:    */import org.apache.commons.lang3.ArrayUtils;
/*  10:    */
/* 105:    */public class HashCodeBuilder
/* 106:    */  implements Builder<Integer>
/* 107:    */{
/* 108:108 */  private static final ThreadLocal<Set<IDKey>> REGISTRY = new ThreadLocal();
/* 109:    */  
/* 121:    */  private final int iConstant;
/* 122:    */  
/* 134:    */  static Set<IDKey> getRegistry()
/* 135:    */  {
/* 136:136 */    return (Set)REGISTRY.get();
/* 137:    */  }
/* 138:    */  
/* 149:    */  static boolean isRegistered(Object value)
/* 150:    */  {
/* 151:151 */    Set<IDKey> registry = getRegistry();
/* 152:152 */    return (registry != null) && (registry.contains(new IDKey(value)));
/* 153:    */  }
/* 154:    */  
/* 171:    */  private static void reflectionAppend(Object object, Class<?> clazz, HashCodeBuilder builder, boolean useTransients, String[] excludeFields)
/* 172:    */  {
/* 173:173 */    if (isRegistered(object)) {
/* 174:174 */      return;
/* 175:    */    }
/* 176:    */    try {
/* 177:177 */      register(object);
/* 178:178 */      Field[] fields = clazz.getDeclaredFields();
/* 179:179 */      AccessibleObject.setAccessible(fields, true);
/* 180:180 */      for (Field field : fields) {
/* 181:181 */        if ((!ArrayUtils.contains(excludeFields, field.getName())) && (field.getName().indexOf('$') == -1) && ((useTransients) || (!Modifier.isTransient(field.getModifiers()))) && (!Modifier.isStatic(field.getModifiers())))
/* 182:    */        {
/* 183:    */          try
/* 184:    */          {
/* 186:186 */            Object fieldValue = field.get(object);
/* 187:187 */            builder.append(fieldValue);
/* 188:    */          }
/* 189:    */          catch (IllegalAccessException e)
/* 190:    */          {
/* 191:191 */            throw new InternalError("Unexpected IllegalAccessException");
/* 192:    */          }
/* 193:    */        }
/* 194:    */      }
/* 195:    */    } finally {
/* 196:196 */      unregister(object);
/* 197:    */    }
/* 198:    */  }
/* 199:    */  
/* 236:    */  public static int reflectionHashCode(int initialNonZeroOddNumber, int multiplierNonZeroOddNumber, Object object)
/* 237:    */  {
/* 238:238 */    return reflectionHashCode(initialNonZeroOddNumber, multiplierNonZeroOddNumber, object, false, null, new String[0]);
/* 239:    */  }
/* 240:    */  
/* 280:    */  public static int reflectionHashCode(int initialNonZeroOddNumber, int multiplierNonZeroOddNumber, Object object, boolean testTransients)
/* 281:    */  {
/* 282:282 */    return reflectionHashCode(initialNonZeroOddNumber, multiplierNonZeroOddNumber, object, testTransients, null, new String[0]);
/* 283:    */  }
/* 284:    */  
/* 333:    */  public static <T> int reflectionHashCode(int initialNonZeroOddNumber, int multiplierNonZeroOddNumber, T object, boolean testTransients, Class<? super T> reflectUpToClass, String... excludeFields)
/* 334:    */  {
/* 335:335 */    if (object == null) {
/* 336:336 */      throw new IllegalArgumentException("The object to build a hash code for must not be null");
/* 337:    */    }
/* 338:338 */    HashCodeBuilder builder = new HashCodeBuilder(initialNonZeroOddNumber, multiplierNonZeroOddNumber);
/* 339:339 */    Class<?> clazz = object.getClass();
/* 340:340 */    reflectionAppend(object, clazz, builder, testTransients, excludeFields);
/* 341:341 */    while ((clazz.getSuperclass() != null) && (clazz != reflectUpToClass)) {
/* 342:342 */      clazz = clazz.getSuperclass();
/* 343:343 */      reflectionAppend(object, clazz, builder, testTransients, excludeFields);
/* 344:    */    }
/* 345:345 */    return builder.toHashCode();
/* 346:    */  }
/* 347:    */  
/* 379:    */  public static int reflectionHashCode(Object object, boolean testTransients)
/* 380:    */  {
/* 381:381 */    return reflectionHashCode(17, 37, object, testTransients, null, new String[0]);
/* 382:    */  }
/* 383:    */  
/* 415:    */  public static int reflectionHashCode(Object object, Collection<String> excludeFields)
/* 416:    */  {
/* 417:417 */    return reflectionHashCode(object, ReflectionToStringBuilder.toNoNullStringArray(excludeFields));
/* 418:    */  }
/* 419:    */  
/* 453:    */  public static int reflectionHashCode(Object object, String... excludeFields)
/* 454:    */  {
/* 455:455 */    return reflectionHashCode(17, 37, object, false, null, excludeFields);
/* 456:    */  }
/* 457:    */  
/* 465:    */  static void register(Object value)
/* 466:    */  {
/* 467:467 */    synchronized (HashCodeBuilder.class) {
/* 468:468 */      if (getRegistry() == null) {
/* 469:469 */        REGISTRY.set(new HashSet());
/* 470:    */      }
/* 471:    */    }
/* 472:472 */    getRegistry().add(new IDKey(value));
/* 473:    */  }
/* 474:    */  
/* 486:    */  static void unregister(Object value)
/* 487:    */  {
/* 488:488 */    Set<IDKey> registry = getRegistry();
/* 489:489 */    if (registry != null) {
/* 490:490 */      registry.remove(new IDKey(value));
/* 491:491 */      synchronized (HashCodeBuilder.class)
/* 492:    */      {
/* 493:493 */        registry = getRegistry();
/* 494:494 */        if ((registry != null) && (registry.isEmpty())) {
/* 495:495 */          REGISTRY.remove();
/* 496:    */        }
/* 497:    */      }
/* 498:    */    }
/* 499:    */  }
/* 500:    */  
/* 509:509 */  private int iTotal = 0;
/* 510:    */  
/* 515:    */  public HashCodeBuilder()
/* 516:    */  {
/* 517:517 */    this.iConstant = 37;
/* 518:518 */    this.iTotal = 17;
/* 519:    */  }
/* 520:    */  
/* 537:    */  public HashCodeBuilder(int initialNonZeroOddNumber, int multiplierNonZeroOddNumber)
/* 538:    */  {
/* 539:539 */    if (initialNonZeroOddNumber == 0) {
/* 540:540 */      throw new IllegalArgumentException("HashCodeBuilder requires a non zero initial value");
/* 541:    */    }
/* 542:542 */    if (initialNonZeroOddNumber % 2 == 0) {
/* 543:543 */      throw new IllegalArgumentException("HashCodeBuilder requires an odd initial value");
/* 544:    */    }
/* 545:545 */    if (multiplierNonZeroOddNumber == 0) {
/* 546:546 */      throw new IllegalArgumentException("HashCodeBuilder requires a non zero multiplier");
/* 547:    */    }
/* 548:548 */    if (multiplierNonZeroOddNumber % 2 == 0) {
/* 549:549 */      throw new IllegalArgumentException("HashCodeBuilder requires an odd multiplier");
/* 550:    */    }
/* 551:551 */    this.iConstant = multiplierNonZeroOddNumber;
/* 552:552 */    this.iTotal = initialNonZeroOddNumber;
/* 553:    */  }
/* 554:    */  
/* 575:    */  public HashCodeBuilder append(boolean value)
/* 576:    */  {
/* 577:577 */    this.iTotal = (this.iTotal * this.iConstant + (value ? 0 : 1));
/* 578:578 */    return this;
/* 579:    */  }
/* 580:    */  
/* 589:    */  public HashCodeBuilder append(boolean[] array)
/* 590:    */  {
/* 591:591 */    if (array == null) {
/* 592:592 */      this.iTotal *= this.iConstant;
/* 593:    */    } else {
/* 594:594 */      for (boolean element : array) {
/* 595:595 */        append(element);
/* 596:    */      }
/* 597:    */    }
/* 598:598 */    return this;
/* 599:    */  }
/* 600:    */  
/* 611:    */  public HashCodeBuilder append(byte value)
/* 612:    */  {
/* 613:613 */    this.iTotal = (this.iTotal * this.iConstant + value);
/* 614:614 */    return this;
/* 615:    */  }
/* 616:    */  
/* 627:    */  public HashCodeBuilder append(byte[] array)
/* 628:    */  {
/* 629:629 */    if (array == null) {
/* 630:630 */      this.iTotal *= this.iConstant;
/* 631:    */    } else {
/* 632:632 */      for (byte element : array) {
/* 633:633 */        append(element);
/* 634:    */      }
/* 635:    */    }
/* 636:636 */    return this;
/* 637:    */  }
/* 638:    */  
/* 647:    */  public HashCodeBuilder append(char value)
/* 648:    */  {
/* 649:649 */    this.iTotal = (this.iTotal * this.iConstant + value);
/* 650:650 */    return this;
/* 651:    */  }
/* 652:    */  
/* 661:    */  public HashCodeBuilder append(char[] array)
/* 662:    */  {
/* 663:663 */    if (array == null) {
/* 664:664 */      this.iTotal *= this.iConstant;
/* 665:    */    } else {
/* 666:666 */      for (char element : array) {
/* 667:667 */        append(element);
/* 668:    */      }
/* 669:    */    }
/* 670:670 */    return this;
/* 671:    */  }
/* 672:    */  
/* 681:    */  public HashCodeBuilder append(double value)
/* 682:    */  {
/* 683:683 */    return append(Double.doubleToLongBits(value));
/* 684:    */  }
/* 685:    */  
/* 694:    */  public HashCodeBuilder append(double[] array)
/* 695:    */  {
/* 696:696 */    if (array == null) {
/* 697:697 */      this.iTotal *= this.iConstant;
/* 698:    */    } else {
/* 699:699 */      for (double element : array) {
/* 700:700 */        append(element);
/* 701:    */      }
/* 702:    */    }
/* 703:703 */    return this;
/* 704:    */  }
/* 705:    */  
/* 714:    */  public HashCodeBuilder append(float value)
/* 715:    */  {
/* 716:716 */    this.iTotal = (this.iTotal * this.iConstant + Float.floatToIntBits(value));
/* 717:717 */    return this;
/* 718:    */  }
/* 719:    */  
/* 728:    */  public HashCodeBuilder append(float[] array)
/* 729:    */  {
/* 730:730 */    if (array == null) {
/* 731:731 */      this.iTotal *= this.iConstant;
/* 732:    */    } else {
/* 733:733 */      for (float element : array) {
/* 734:734 */        append(element);
/* 735:    */      }
/* 736:    */    }
/* 737:737 */    return this;
/* 738:    */  }
/* 739:    */  
/* 748:    */  public HashCodeBuilder append(int value)
/* 749:    */  {
/* 750:750 */    this.iTotal = (this.iTotal * this.iConstant + value);
/* 751:751 */    return this;
/* 752:    */  }
/* 753:    */  
/* 762:    */  public HashCodeBuilder append(int[] array)
/* 763:    */  {
/* 764:764 */    if (array == null) {
/* 765:765 */      this.iTotal *= this.iConstant;
/* 766:    */    } else {
/* 767:767 */      for (int element : array) {
/* 768:768 */        append(element);
/* 769:    */      }
/* 770:    */    }
/* 771:771 */    return this;
/* 772:    */  }
/* 773:    */  
/* 786:    */  public HashCodeBuilder append(long value)
/* 787:    */  {
/* 788:788 */    this.iTotal = (this.iTotal * this.iConstant + (int)(value ^ value >> 32));
/* 789:789 */    return this;
/* 790:    */  }
/* 791:    */  
/* 800:    */  public HashCodeBuilder append(long[] array)
/* 801:    */  {
/* 802:802 */    if (array == null) {
/* 803:803 */      this.iTotal *= this.iConstant;
/* 804:    */    } else {
/* 805:805 */      for (long element : array) {
/* 806:806 */        append(element);
/* 807:    */      }
/* 808:    */    }
/* 809:809 */    return this;
/* 810:    */  }
/* 811:    */  
/* 820:    */  public HashCodeBuilder append(Object object)
/* 821:    */  {
/* 822:822 */    if (object == null) {
/* 823:823 */      this.iTotal *= this.iConstant;
/* 825:    */    }
/* 826:826 */    else if (object.getClass().isArray())
/* 827:    */    {
/* 829:829 */      if ((object instanceof long[])) {
/* 830:830 */        append((long[])object);
/* 831:831 */      } else if ((object instanceof int[])) {
/* 832:832 */        append((int[])object);
/* 833:833 */      } else if ((object instanceof short[])) {
/* 834:834 */        append((short[])object);
/* 835:835 */      } else if ((object instanceof char[])) {
/* 836:836 */        append((char[])object);
/* 837:837 */      } else if ((object instanceof byte[])) {
/* 838:838 */        append((byte[])object);
/* 839:839 */      } else if ((object instanceof double[])) {
/* 840:840 */        append((double[])object);
/* 841:841 */      } else if ((object instanceof float[])) {
/* 842:842 */        append((float[])object);
/* 843:843 */      } else if ((object instanceof boolean[])) {
/* 844:844 */        append((boolean[])object);
/* 845:    */      }
/* 846:    */      else {
/* 847:847 */        append((Object[])object);
/* 848:    */      }
/* 849:    */    } else {
/* 850:850 */      this.iTotal = (this.iTotal * this.iConstant + object.hashCode());
/* 851:    */    }
/* 852:    */    
/* 853:853 */    return this;
/* 854:    */  }
/* 855:    */  
/* 864:    */  public HashCodeBuilder append(Object[] array)
/* 865:    */  {
/* 866:866 */    if (array == null) {
/* 867:867 */      this.iTotal *= this.iConstant;
/* 868:    */    } else {
/* 869:869 */      for (Object element : array) {
/* 870:870 */        append(element);
/* 871:    */      }
/* 872:    */    }
/* 873:873 */    return this;
/* 874:    */  }
/* 875:    */  
/* 884:    */  public HashCodeBuilder append(short value)
/* 885:    */  {
/* 886:886 */    this.iTotal = (this.iTotal * this.iConstant + value);
/* 887:887 */    return this;
/* 888:    */  }
/* 889:    */  
/* 898:    */  public HashCodeBuilder append(short[] array)
/* 899:    */  {
/* 900:900 */    if (array == null) {
/* 901:901 */      this.iTotal *= this.iConstant;
/* 902:    */    } else {
/* 903:903 */      for (short element : array) {
/* 904:904 */        append(element);
/* 905:    */      }
/* 906:    */    }
/* 907:907 */    return this;
/* 908:    */  }
/* 909:    */  
/* 919:    */  public HashCodeBuilder appendSuper(int superHashCode)
/* 920:    */  {
/* 921:921 */    this.iTotal = (this.iTotal * this.iConstant + superHashCode);
/* 922:922 */    return this;
/* 923:    */  }
/* 924:    */  
/* 931:    */  public int toHashCode()
/* 932:    */  {
/* 933:933 */    return this.iTotal;
/* 934:    */  }
/* 935:    */  
/* 942:    */  public Integer build()
/* 943:    */  {
/* 944:944 */    return Integer.valueOf(toHashCode());
/* 945:    */  }
/* 946:    */  
/* 956:    */  public int hashCode()
/* 957:    */  {
/* 958:958 */    return toHashCode();
/* 959:    */  }
/* 960:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.builder.HashCodeBuilder
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */