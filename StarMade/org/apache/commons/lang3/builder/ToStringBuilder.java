/*    1:     */package org.apache.commons.lang3.builder;
/*    2:     */
/*    3:     */import org.apache.commons.lang3.ObjectUtils;
/*    4:     */
/*   91:     */public class ToStringBuilder
/*   92:     */  implements Builder<String>
/*   93:     */{
/*   94:  94 */  private static volatile ToStringStyle defaultStyle = ToStringStyle.DEFAULT_STYLE;
/*   95:     */  
/*   99:     */  private final StringBuffer buffer;
/*  100:     */  
/*  104:     */  private final Object object;
/*  105:     */  
/*  109:     */  private final ToStringStyle style;
/*  110:     */  
/*  115:     */  public static ToStringStyle getDefaultStyle()
/*  116:     */  {
/*  117: 117 */    return defaultStyle;
/*  118:     */  }
/*  119:     */  
/*  134:     */  public static void setDefaultStyle(ToStringStyle style)
/*  135:     */  {
/*  136: 136 */    if (style == null) {
/*  137: 137 */      throw new IllegalArgumentException("The style must not be null");
/*  138:     */    }
/*  139: 139 */    defaultStyle = style;
/*  140:     */  }
/*  141:     */  
/*  150:     */  public static String reflectionToString(Object object)
/*  151:     */  {
/*  152: 152 */    return ReflectionToStringBuilder.toString(object);
/*  153:     */  }
/*  154:     */  
/*  163:     */  public static String reflectionToString(Object object, ToStringStyle style)
/*  164:     */  {
/*  165: 165 */    return ReflectionToStringBuilder.toString(object, style);
/*  166:     */  }
/*  167:     */  
/*  177:     */  public static String reflectionToString(Object object, ToStringStyle style, boolean outputTransients)
/*  178:     */  {
/*  179: 179 */    return ReflectionToStringBuilder.toString(object, style, outputTransients, false, null);
/*  180:     */  }
/*  181:     */  
/*  198:     */  public static <T> String reflectionToString(T object, ToStringStyle style, boolean outputTransients, Class<? super T> reflectUpToClass)
/*  199:     */  {
/*  200: 200 */    return ReflectionToStringBuilder.toString(object, style, outputTransients, false, reflectUpToClass);
/*  201:     */  }
/*  202:     */  
/*  224:     */  public ToStringBuilder(Object object)
/*  225:     */  {
/*  226: 226 */    this(object, null, null);
/*  227:     */  }
/*  228:     */  
/*  236:     */  public ToStringBuilder(Object object, ToStringStyle style)
/*  237:     */  {
/*  238: 238 */    this(object, style, null);
/*  239:     */  }
/*  240:     */  
/*  251:     */  public ToStringBuilder(Object object, ToStringStyle style, StringBuffer buffer)
/*  252:     */  {
/*  253: 253 */    if (style == null) {
/*  254: 254 */      style = getDefaultStyle();
/*  255:     */    }
/*  256: 256 */    if (buffer == null) {
/*  257: 257 */      buffer = new StringBuffer(512);
/*  258:     */    }
/*  259: 259 */    this.buffer = buffer;
/*  260: 260 */    this.style = style;
/*  261: 261 */    this.object = object;
/*  262:     */    
/*  263: 263 */    style.appendStart(buffer, object);
/*  264:     */  }
/*  265:     */  
/*  274:     */  public ToStringBuilder append(boolean value)
/*  275:     */  {
/*  276: 276 */    this.style.append(this.buffer, null, value);
/*  277: 277 */    return this;
/*  278:     */  }
/*  279:     */  
/*  288:     */  public ToStringBuilder append(boolean[] array)
/*  289:     */  {
/*  290: 290 */    this.style.append(this.buffer, null, array, null);
/*  291: 291 */    return this;
/*  292:     */  }
/*  293:     */  
/*  302:     */  public ToStringBuilder append(byte value)
/*  303:     */  {
/*  304: 304 */    this.style.append(this.buffer, null, value);
/*  305: 305 */    return this;
/*  306:     */  }
/*  307:     */  
/*  316:     */  public ToStringBuilder append(byte[] array)
/*  317:     */  {
/*  318: 318 */    this.style.append(this.buffer, null, array, null);
/*  319: 319 */    return this;
/*  320:     */  }
/*  321:     */  
/*  330:     */  public ToStringBuilder append(char value)
/*  331:     */  {
/*  332: 332 */    this.style.append(this.buffer, null, value);
/*  333: 333 */    return this;
/*  334:     */  }
/*  335:     */  
/*  344:     */  public ToStringBuilder append(char[] array)
/*  345:     */  {
/*  346: 346 */    this.style.append(this.buffer, null, array, null);
/*  347: 347 */    return this;
/*  348:     */  }
/*  349:     */  
/*  358:     */  public ToStringBuilder append(double value)
/*  359:     */  {
/*  360: 360 */    this.style.append(this.buffer, null, value);
/*  361: 361 */    return this;
/*  362:     */  }
/*  363:     */  
/*  372:     */  public ToStringBuilder append(double[] array)
/*  373:     */  {
/*  374: 374 */    this.style.append(this.buffer, null, array, null);
/*  375: 375 */    return this;
/*  376:     */  }
/*  377:     */  
/*  386:     */  public ToStringBuilder append(float value)
/*  387:     */  {
/*  388: 388 */    this.style.append(this.buffer, null, value);
/*  389: 389 */    return this;
/*  390:     */  }
/*  391:     */  
/*  400:     */  public ToStringBuilder append(float[] array)
/*  401:     */  {
/*  402: 402 */    this.style.append(this.buffer, null, array, null);
/*  403: 403 */    return this;
/*  404:     */  }
/*  405:     */  
/*  414:     */  public ToStringBuilder append(int value)
/*  415:     */  {
/*  416: 416 */    this.style.append(this.buffer, null, value);
/*  417: 417 */    return this;
/*  418:     */  }
/*  419:     */  
/*  428:     */  public ToStringBuilder append(int[] array)
/*  429:     */  {
/*  430: 430 */    this.style.append(this.buffer, null, array, null);
/*  431: 431 */    return this;
/*  432:     */  }
/*  433:     */  
/*  442:     */  public ToStringBuilder append(long value)
/*  443:     */  {
/*  444: 444 */    this.style.append(this.buffer, null, value);
/*  445: 445 */    return this;
/*  446:     */  }
/*  447:     */  
/*  456:     */  public ToStringBuilder append(long[] array)
/*  457:     */  {
/*  458: 458 */    this.style.append(this.buffer, null, array, null);
/*  459: 459 */    return this;
/*  460:     */  }
/*  461:     */  
/*  470:     */  public ToStringBuilder append(Object obj)
/*  471:     */  {
/*  472: 472 */    this.style.append(this.buffer, null, obj, null);
/*  473: 473 */    return this;
/*  474:     */  }
/*  475:     */  
/*  484:     */  public ToStringBuilder append(Object[] array)
/*  485:     */  {
/*  486: 486 */    this.style.append(this.buffer, null, array, null);
/*  487: 487 */    return this;
/*  488:     */  }
/*  489:     */  
/*  498:     */  public ToStringBuilder append(short value)
/*  499:     */  {
/*  500: 500 */    this.style.append(this.buffer, null, value);
/*  501: 501 */    return this;
/*  502:     */  }
/*  503:     */  
/*  512:     */  public ToStringBuilder append(short[] array)
/*  513:     */  {
/*  514: 514 */    this.style.append(this.buffer, null, array, null);
/*  515: 515 */    return this;
/*  516:     */  }
/*  517:     */  
/*  525:     */  public ToStringBuilder append(String fieldName, boolean value)
/*  526:     */  {
/*  527: 527 */    this.style.append(this.buffer, fieldName, value);
/*  528: 528 */    return this;
/*  529:     */  }
/*  530:     */  
/*  538:     */  public ToStringBuilder append(String fieldName, boolean[] array)
/*  539:     */  {
/*  540: 540 */    this.style.append(this.buffer, fieldName, array, null);
/*  541: 541 */    return this;
/*  542:     */  }
/*  543:     */  
/*  558:     */  public ToStringBuilder append(String fieldName, boolean[] array, boolean fullDetail)
/*  559:     */  {
/*  560: 560 */    this.style.append(this.buffer, fieldName, array, Boolean.valueOf(fullDetail));
/*  561: 561 */    return this;
/*  562:     */  }
/*  563:     */  
/*  571:     */  public ToStringBuilder append(String fieldName, byte value)
/*  572:     */  {
/*  573: 573 */    this.style.append(this.buffer, fieldName, value);
/*  574: 574 */    return this;
/*  575:     */  }
/*  576:     */  
/*  583:     */  public ToStringBuilder append(String fieldName, byte[] array)
/*  584:     */  {
/*  585: 585 */    this.style.append(this.buffer, fieldName, array, null);
/*  586: 586 */    return this;
/*  587:     */  }
/*  588:     */  
/*  603:     */  public ToStringBuilder append(String fieldName, byte[] array, boolean fullDetail)
/*  604:     */  {
/*  605: 605 */    this.style.append(this.buffer, fieldName, array, Boolean.valueOf(fullDetail));
/*  606: 606 */    return this;
/*  607:     */  }
/*  608:     */  
/*  616:     */  public ToStringBuilder append(String fieldName, char value)
/*  617:     */  {
/*  618: 618 */    this.style.append(this.buffer, fieldName, value);
/*  619: 619 */    return this;
/*  620:     */  }
/*  621:     */  
/*  629:     */  public ToStringBuilder append(String fieldName, char[] array)
/*  630:     */  {
/*  631: 631 */    this.style.append(this.buffer, fieldName, array, null);
/*  632: 632 */    return this;
/*  633:     */  }
/*  634:     */  
/*  649:     */  public ToStringBuilder append(String fieldName, char[] array, boolean fullDetail)
/*  650:     */  {
/*  651: 651 */    this.style.append(this.buffer, fieldName, array, Boolean.valueOf(fullDetail));
/*  652: 652 */    return this;
/*  653:     */  }
/*  654:     */  
/*  662:     */  public ToStringBuilder append(String fieldName, double value)
/*  663:     */  {
/*  664: 664 */    this.style.append(this.buffer, fieldName, value);
/*  665: 665 */    return this;
/*  666:     */  }
/*  667:     */  
/*  675:     */  public ToStringBuilder append(String fieldName, double[] array)
/*  676:     */  {
/*  677: 677 */    this.style.append(this.buffer, fieldName, array, null);
/*  678: 678 */    return this;
/*  679:     */  }
/*  680:     */  
/*  695:     */  public ToStringBuilder append(String fieldName, double[] array, boolean fullDetail)
/*  696:     */  {
/*  697: 697 */    this.style.append(this.buffer, fieldName, array, Boolean.valueOf(fullDetail));
/*  698: 698 */    return this;
/*  699:     */  }
/*  700:     */  
/*  708:     */  public ToStringBuilder append(String fieldName, float value)
/*  709:     */  {
/*  710: 710 */    this.style.append(this.buffer, fieldName, value);
/*  711: 711 */    return this;
/*  712:     */  }
/*  713:     */  
/*  721:     */  public ToStringBuilder append(String fieldName, float[] array)
/*  722:     */  {
/*  723: 723 */    this.style.append(this.buffer, fieldName, array, null);
/*  724: 724 */    return this;
/*  725:     */  }
/*  726:     */  
/*  741:     */  public ToStringBuilder append(String fieldName, float[] array, boolean fullDetail)
/*  742:     */  {
/*  743: 743 */    this.style.append(this.buffer, fieldName, array, Boolean.valueOf(fullDetail));
/*  744: 744 */    return this;
/*  745:     */  }
/*  746:     */  
/*  754:     */  public ToStringBuilder append(String fieldName, int value)
/*  755:     */  {
/*  756: 756 */    this.style.append(this.buffer, fieldName, value);
/*  757: 757 */    return this;
/*  758:     */  }
/*  759:     */  
/*  767:     */  public ToStringBuilder append(String fieldName, int[] array)
/*  768:     */  {
/*  769: 769 */    this.style.append(this.buffer, fieldName, array, null);
/*  770: 770 */    return this;
/*  771:     */  }
/*  772:     */  
/*  787:     */  public ToStringBuilder append(String fieldName, int[] array, boolean fullDetail)
/*  788:     */  {
/*  789: 789 */    this.style.append(this.buffer, fieldName, array, Boolean.valueOf(fullDetail));
/*  790: 790 */    return this;
/*  791:     */  }
/*  792:     */  
/*  800:     */  public ToStringBuilder append(String fieldName, long value)
/*  801:     */  {
/*  802: 802 */    this.style.append(this.buffer, fieldName, value);
/*  803: 803 */    return this;
/*  804:     */  }
/*  805:     */  
/*  813:     */  public ToStringBuilder append(String fieldName, long[] array)
/*  814:     */  {
/*  815: 815 */    this.style.append(this.buffer, fieldName, array, null);
/*  816: 816 */    return this;
/*  817:     */  }
/*  818:     */  
/*  833:     */  public ToStringBuilder append(String fieldName, long[] array, boolean fullDetail)
/*  834:     */  {
/*  835: 835 */    this.style.append(this.buffer, fieldName, array, Boolean.valueOf(fullDetail));
/*  836: 836 */    return this;
/*  837:     */  }
/*  838:     */  
/*  846:     */  public ToStringBuilder append(String fieldName, Object obj)
/*  847:     */  {
/*  848: 848 */    this.style.append(this.buffer, fieldName, obj, null);
/*  849: 849 */    return this;
/*  850:     */  }
/*  851:     */  
/*  861:     */  public ToStringBuilder append(String fieldName, Object obj, boolean fullDetail)
/*  862:     */  {
/*  863: 863 */    this.style.append(this.buffer, fieldName, obj, Boolean.valueOf(fullDetail));
/*  864: 864 */    return this;
/*  865:     */  }
/*  866:     */  
/*  874:     */  public ToStringBuilder append(String fieldName, Object[] array)
/*  875:     */  {
/*  876: 876 */    this.style.append(this.buffer, fieldName, array, null);
/*  877: 877 */    return this;
/*  878:     */  }
/*  879:     */  
/*  894:     */  public ToStringBuilder append(String fieldName, Object[] array, boolean fullDetail)
/*  895:     */  {
/*  896: 896 */    this.style.append(this.buffer, fieldName, array, Boolean.valueOf(fullDetail));
/*  897: 897 */    return this;
/*  898:     */  }
/*  899:     */  
/*  907:     */  public ToStringBuilder append(String fieldName, short value)
/*  908:     */  {
/*  909: 909 */    this.style.append(this.buffer, fieldName, value);
/*  910: 910 */    return this;
/*  911:     */  }
/*  912:     */  
/*  920:     */  public ToStringBuilder append(String fieldName, short[] array)
/*  921:     */  {
/*  922: 922 */    this.style.append(this.buffer, fieldName, array, null);
/*  923: 923 */    return this;
/*  924:     */  }
/*  925:     */  
/*  940:     */  public ToStringBuilder append(String fieldName, short[] array, boolean fullDetail)
/*  941:     */  {
/*  942: 942 */    this.style.append(this.buffer, fieldName, array, Boolean.valueOf(fullDetail));
/*  943: 943 */    return this;
/*  944:     */  }
/*  945:     */  
/*  954:     */  public ToStringBuilder appendAsObjectToString(Object object)
/*  955:     */  {
/*  956: 956 */    ObjectUtils.identityToString(getStringBuffer(), object);
/*  957: 957 */    return this;
/*  958:     */  }
/*  959:     */  
/*  973:     */  public ToStringBuilder appendSuper(String superToString)
/*  974:     */  {
/*  975: 975 */    if (superToString != null) {
/*  976: 976 */      this.style.appendSuper(this.buffer, superToString);
/*  977:     */    }
/*  978: 978 */    return this;
/*  979:     */  }
/*  980:     */  
/* 1007:     */  public ToStringBuilder appendToString(String toString)
/* 1008:     */  {
/* 1009:1009 */    if (toString != null) {
/* 1010:1010 */      this.style.appendToString(this.buffer, toString);
/* 1011:     */    }
/* 1012:1012 */    return this;
/* 1013:     */  }
/* 1014:     */  
/* 1020:     */  public Object getObject()
/* 1021:     */  {
/* 1022:1022 */    return this.object;
/* 1023:     */  }
/* 1024:     */  
/* 1029:     */  public StringBuffer getStringBuffer()
/* 1030:     */  {
/* 1031:1031 */    return this.buffer;
/* 1032:     */  }
/* 1033:     */  
/* 1041:     */  public ToStringStyle getStyle()
/* 1042:     */  {
/* 1043:1043 */    return this.style;
/* 1044:     */  }
/* 1045:     */  
/* 1056:     */  public String toString()
/* 1057:     */  {
/* 1058:1058 */    if (getObject() == null) {
/* 1059:1059 */      getStringBuffer().append(getStyle().getNullText());
/* 1060:     */    } else {
/* 1061:1061 */      this.style.appendEnd(getStringBuffer(), getObject());
/* 1062:     */    }
/* 1063:1063 */    return getStringBuffer().toString();
/* 1064:     */  }
/* 1065:     */  
/* 1075:     */  public String build()
/* 1076:     */  {
/* 1077:1077 */    return toString();
/* 1078:     */  }
/* 1079:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.builder.ToStringBuilder
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */