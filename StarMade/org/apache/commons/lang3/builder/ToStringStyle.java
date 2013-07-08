/*    1:     */package org.apache.commons.lang3.builder;
/*    2:     */
/*    3:     */import java.io.Serializable;
/*    4:     */import java.lang.reflect.Array;
/*    5:     */import java.util.Collection;
/*    6:     */import java.util.Map;
/*    7:     */import java.util.WeakHashMap;
/*    8:     */import org.apache.commons.lang3.ClassUtils;
/*    9:     */import org.apache.commons.lang3.ObjectUtils;
/*   10:     */import org.apache.commons.lang3.SystemUtils;
/*   11:     */
/*   77:     */public abstract class ToStringStyle
/*   78:     */  implements Serializable
/*   79:     */{
/*   80:     */  private static final long serialVersionUID = -2587890625525655916L;
/*   81:  81 */  public static final ToStringStyle DEFAULT_STYLE = new DefaultToStringStyle();
/*   82:     */  
/*   95:  95 */  public static final ToStringStyle MULTI_LINE_STYLE = new MultiLineToStringStyle();
/*   96:     */  
/*  106: 106 */  public static final ToStringStyle NO_FIELD_NAMES_STYLE = new NoFieldNameToStringStyle();
/*  107:     */  
/*  118: 118 */  public static final ToStringStyle SHORT_PREFIX_STYLE = new ShortPrefixToStringStyle();
/*  119:     */  
/*  128: 128 */  public static final ToStringStyle SIMPLE_STYLE = new SimpleToStringStyle();
/*  129:     */  
/*  136: 136 */  private static final ThreadLocal<WeakHashMap<Object, Object>> REGISTRY = new ThreadLocal();
/*  137:     */  
/*  146:     */  static Map<Object, Object> getRegistry()
/*  147:     */  {
/*  148: 148 */    return (Map)REGISTRY.get();
/*  149:     */  }
/*  150:     */  
/*  161:     */  static boolean isRegistered(Object value)
/*  162:     */  {
/*  163: 163 */    Map<Object, Object> m = getRegistry();
/*  164: 164 */    return (m != null) && (m.containsKey(value));
/*  165:     */  }
/*  166:     */  
/*  175:     */  static void register(Object value)
/*  176:     */  {
/*  177: 177 */    if (value != null) {
/*  178: 178 */      Map<Object, Object> m = getRegistry();
/*  179: 179 */      if (m == null) {
/*  180: 180 */        REGISTRY.set(new WeakHashMap());
/*  181:     */      }
/*  182: 182 */      getRegistry().put(value, null);
/*  183:     */    }
/*  184:     */  }
/*  185:     */  
/*  197:     */  static void unregister(Object value)
/*  198:     */  {
/*  199: 199 */    if (value != null) {
/*  200: 200 */      Map<Object, Object> m = getRegistry();
/*  201: 201 */      if (m != null) {
/*  202: 202 */        m.remove(value);
/*  203: 203 */        if (m.isEmpty()) {
/*  204: 204 */          REGISTRY.remove();
/*  205:     */        }
/*  206:     */      }
/*  207:     */    }
/*  208:     */  }
/*  209:     */  
/*  213: 213 */  private boolean useFieldNames = true;
/*  214:     */  
/*  218: 218 */  private boolean useClassName = true;
/*  219:     */  
/*  223: 223 */  private boolean useShortClassName = false;
/*  224:     */  
/*  228: 228 */  private boolean useIdentityHashCode = true;
/*  229:     */  
/*  233: 233 */  private String contentStart = "[";
/*  234:     */  
/*  238: 238 */  private String contentEnd = "]";
/*  239:     */  
/*  243: 243 */  private String fieldNameValueSeparator = "=";
/*  244:     */  
/*  248: 248 */  private boolean fieldSeparatorAtStart = false;
/*  249:     */  
/*  253: 253 */  private boolean fieldSeparatorAtEnd = false;
/*  254:     */  
/*  258: 258 */  private String fieldSeparator = ",";
/*  259:     */  
/*  263: 263 */  private String arrayStart = "{";
/*  264:     */  
/*  268: 268 */  private String arraySeparator = ",";
/*  269:     */  
/*  273: 273 */  private boolean arrayContentDetail = true;
/*  274:     */  
/*  278: 278 */  private String arrayEnd = "}";
/*  279:     */  
/*  284: 284 */  private boolean defaultFullDetail = true;
/*  285:     */  
/*  289: 289 */  private String nullText = "<null>";
/*  290:     */  
/*  294: 294 */  private String sizeStartText = "<size=";
/*  295:     */  
/*  299: 299 */  private String sizeEndText = ">";
/*  300:     */  
/*  304: 304 */  private String summaryObjectStartText = "<";
/*  305:     */  
/*  309: 309 */  private String summaryObjectEndText = ">";
/*  310:     */  
/*  331:     */  public void appendSuper(StringBuffer buffer, String superToString)
/*  332:     */  {
/*  333: 333 */    appendToString(buffer, superToString);
/*  334:     */  }
/*  335:     */  
/*  345:     */  public void appendToString(StringBuffer buffer, String toString)
/*  346:     */  {
/*  347: 347 */    if (toString != null) {
/*  348: 348 */      int pos1 = toString.indexOf(this.contentStart) + this.contentStart.length();
/*  349: 349 */      int pos2 = toString.lastIndexOf(this.contentEnd);
/*  350: 350 */      if ((pos1 != pos2) && (pos1 >= 0) && (pos2 >= 0)) {
/*  351: 351 */        String data = toString.substring(pos1, pos2);
/*  352: 352 */        if (this.fieldSeparatorAtStart) {
/*  353: 353 */          removeLastFieldSeparator(buffer);
/*  354:     */        }
/*  355: 355 */        buffer.append(data);
/*  356: 356 */        appendFieldSeparator(buffer);
/*  357:     */      }
/*  358:     */    }
/*  359:     */  }
/*  360:     */  
/*  366:     */  public void appendStart(StringBuffer buffer, Object object)
/*  367:     */  {
/*  368: 368 */    if (object != null) {
/*  369: 369 */      appendClassName(buffer, object);
/*  370: 370 */      appendIdentityHashCode(buffer, object);
/*  371: 371 */      appendContentStart(buffer);
/*  372: 372 */      if (this.fieldSeparatorAtStart) {
/*  373: 373 */        appendFieldSeparator(buffer);
/*  374:     */      }
/*  375:     */    }
/*  376:     */  }
/*  377:     */  
/*  384:     */  public void appendEnd(StringBuffer buffer, Object object)
/*  385:     */  {
/*  386: 386 */    if (!this.fieldSeparatorAtEnd) {
/*  387: 387 */      removeLastFieldSeparator(buffer);
/*  388:     */    }
/*  389: 389 */    appendContentEnd(buffer);
/*  390: 390 */    unregister(object);
/*  391:     */  }
/*  392:     */  
/*  398:     */  protected void removeLastFieldSeparator(StringBuffer buffer)
/*  399:     */  {
/*  400: 400 */    int len = buffer.length();
/*  401: 401 */    int sepLen = this.fieldSeparator.length();
/*  402: 402 */    if ((len > 0) && (sepLen > 0) && (len >= sepLen)) {
/*  403: 403 */      boolean match = true;
/*  404: 404 */      for (int i = 0; i < sepLen; i++) {
/*  405: 405 */        if (buffer.charAt(len - 1 - i) != this.fieldSeparator.charAt(sepLen - 1 - i)) {
/*  406: 406 */          match = false;
/*  407: 407 */          break;
/*  408:     */        }
/*  409:     */      }
/*  410: 410 */      if (match) {
/*  411: 411 */        buffer.setLength(len - sepLen);
/*  412:     */      }
/*  413:     */    }
/*  414:     */  }
/*  415:     */  
/*  428:     */  public void append(StringBuffer buffer, String fieldName, Object value, Boolean fullDetail)
/*  429:     */  {
/*  430: 430 */    appendFieldStart(buffer, fieldName);
/*  431:     */    
/*  432: 432 */    if (value == null) {
/*  433: 433 */      appendNullText(buffer, fieldName);
/*  434:     */    }
/*  435:     */    else {
/*  436: 436 */      appendInternal(buffer, fieldName, value, isFullDetail(fullDetail));
/*  437:     */    }
/*  438:     */    
/*  439: 439 */    appendFieldEnd(buffer, fieldName);
/*  440:     */  }
/*  441:     */  
/*  460:     */  protected void appendInternal(StringBuffer buffer, String fieldName, Object value, boolean detail)
/*  461:     */  {
/*  462: 462 */    if ((isRegistered(value)) && (!(value instanceof Number)) && (!(value instanceof Boolean)) && (!(value instanceof Character)))
/*  463:     */    {
/*  464: 464 */      appendCyclicObject(buffer, fieldName, value);
/*  465: 465 */      return;
/*  466:     */    }
/*  467:     */    
/*  468: 468 */    register(value);
/*  469:     */    try
/*  470:     */    {
/*  471: 471 */      if ((value instanceof Collection)) {
/*  472: 472 */        if (detail) {
/*  473: 473 */          appendDetail(buffer, fieldName, (Collection)value);
/*  474:     */        } else {
/*  475: 475 */          appendSummarySize(buffer, fieldName, ((Collection)value).size());
/*  476:     */        }
/*  477:     */      }
/*  478: 478 */      else if ((value instanceof Map)) {
/*  479: 479 */        if (detail) {
/*  480: 480 */          appendDetail(buffer, fieldName, (Map)value);
/*  481:     */        } else {
/*  482: 482 */          appendSummarySize(buffer, fieldName, ((Map)value).size());
/*  483:     */        }
/*  484:     */      }
/*  485: 485 */      else if ((value instanceof long[])) {
/*  486: 486 */        if (detail) {
/*  487: 487 */          appendDetail(buffer, fieldName, (long[])value);
/*  488:     */        } else {
/*  489: 489 */          appendSummary(buffer, fieldName, (long[])value);
/*  490:     */        }
/*  491:     */      }
/*  492: 492 */      else if ((value instanceof int[])) {
/*  493: 493 */        if (detail) {
/*  494: 494 */          appendDetail(buffer, fieldName, (int[])value);
/*  495:     */        } else {
/*  496: 496 */          appendSummary(buffer, fieldName, (int[])value);
/*  497:     */        }
/*  498:     */      }
/*  499: 499 */      else if ((value instanceof short[])) {
/*  500: 500 */        if (detail) {
/*  501: 501 */          appendDetail(buffer, fieldName, (short[])value);
/*  502:     */        } else {
/*  503: 503 */          appendSummary(buffer, fieldName, (short[])value);
/*  504:     */        }
/*  505:     */      }
/*  506: 506 */      else if ((value instanceof byte[])) {
/*  507: 507 */        if (detail) {
/*  508: 508 */          appendDetail(buffer, fieldName, (byte[])value);
/*  509:     */        } else {
/*  510: 510 */          appendSummary(buffer, fieldName, (byte[])value);
/*  511:     */        }
/*  512:     */      }
/*  513: 513 */      else if ((value instanceof char[])) {
/*  514: 514 */        if (detail) {
/*  515: 515 */          appendDetail(buffer, fieldName, (char[])value);
/*  516:     */        } else {
/*  517: 517 */          appendSummary(buffer, fieldName, (char[])value);
/*  518:     */        }
/*  519:     */      }
/*  520: 520 */      else if ((value instanceof double[])) {
/*  521: 521 */        if (detail) {
/*  522: 522 */          appendDetail(buffer, fieldName, (double[])value);
/*  523:     */        } else {
/*  524: 524 */          appendSummary(buffer, fieldName, (double[])value);
/*  525:     */        }
/*  526:     */      }
/*  527: 527 */      else if ((value instanceof float[])) {
/*  528: 528 */        if (detail) {
/*  529: 529 */          appendDetail(buffer, fieldName, (float[])value);
/*  530:     */        } else {
/*  531: 531 */          appendSummary(buffer, fieldName, (float[])value);
/*  532:     */        }
/*  533:     */      }
/*  534: 534 */      else if ((value instanceof boolean[])) {
/*  535: 535 */        if (detail) {
/*  536: 536 */          appendDetail(buffer, fieldName, (boolean[])value);
/*  537:     */        } else {
/*  538: 538 */          appendSummary(buffer, fieldName, (boolean[])value);
/*  539:     */        }
/*  540:     */      }
/*  541: 541 */      else if (value.getClass().isArray()) {
/*  542: 542 */        if (detail) {
/*  543: 543 */          appendDetail(buffer, fieldName, (Object[])value);
/*  544:     */        } else {
/*  545: 545 */          appendSummary(buffer, fieldName, (Object[])value);
/*  546:     */        }
/*  547:     */        
/*  548:     */      }
/*  549: 549 */      else if (detail) {
/*  550: 550 */        appendDetail(buffer, fieldName, value);
/*  551:     */      } else {
/*  552: 552 */        appendSummary(buffer, fieldName, value);
/*  553:     */      }
/*  554:     */    }
/*  555:     */    finally {
/*  556: 556 */      unregister(value);
/*  557:     */    }
/*  558:     */  }
/*  559:     */  
/*  571:     */  protected void appendCyclicObject(StringBuffer buffer, String fieldName, Object value)
/*  572:     */  {
/*  573: 573 */    ObjectUtils.identityToString(buffer, value);
/*  574:     */  }
/*  575:     */  
/*  584:     */  protected void appendDetail(StringBuffer buffer, String fieldName, Object value)
/*  585:     */  {
/*  586: 586 */    buffer.append(value);
/*  587:     */  }
/*  588:     */  
/*  596:     */  protected void appendDetail(StringBuffer buffer, String fieldName, Collection<?> coll)
/*  597:     */  {
/*  598: 598 */    buffer.append(coll);
/*  599:     */  }
/*  600:     */  
/*  608:     */  protected void appendDetail(StringBuffer buffer, String fieldName, Map<?, ?> map)
/*  609:     */  {
/*  610: 610 */    buffer.append(map);
/*  611:     */  }
/*  612:     */  
/*  621:     */  protected void appendSummary(StringBuffer buffer, String fieldName, Object value)
/*  622:     */  {
/*  623: 623 */    buffer.append(this.summaryObjectStartText);
/*  624: 624 */    buffer.append(getShortClassName(value.getClass()));
/*  625: 625 */    buffer.append(this.summaryObjectEndText);
/*  626:     */  }
/*  627:     */  
/*  637:     */  public void append(StringBuffer buffer, String fieldName, long value)
/*  638:     */  {
/*  639: 639 */    appendFieldStart(buffer, fieldName);
/*  640: 640 */    appendDetail(buffer, fieldName, value);
/*  641: 641 */    appendFieldEnd(buffer, fieldName);
/*  642:     */  }
/*  643:     */  
/*  651:     */  protected void appendDetail(StringBuffer buffer, String fieldName, long value)
/*  652:     */  {
/*  653: 653 */    buffer.append(value);
/*  654:     */  }
/*  655:     */  
/*  665:     */  public void append(StringBuffer buffer, String fieldName, int value)
/*  666:     */  {
/*  667: 667 */    appendFieldStart(buffer, fieldName);
/*  668: 668 */    appendDetail(buffer, fieldName, value);
/*  669: 669 */    appendFieldEnd(buffer, fieldName);
/*  670:     */  }
/*  671:     */  
/*  679:     */  protected void appendDetail(StringBuffer buffer, String fieldName, int value)
/*  680:     */  {
/*  681: 681 */    buffer.append(value);
/*  682:     */  }
/*  683:     */  
/*  693:     */  public void append(StringBuffer buffer, String fieldName, short value)
/*  694:     */  {
/*  695: 695 */    appendFieldStart(buffer, fieldName);
/*  696: 696 */    appendDetail(buffer, fieldName, value);
/*  697: 697 */    appendFieldEnd(buffer, fieldName);
/*  698:     */  }
/*  699:     */  
/*  707:     */  protected void appendDetail(StringBuffer buffer, String fieldName, short value)
/*  708:     */  {
/*  709: 709 */    buffer.append(value);
/*  710:     */  }
/*  711:     */  
/*  721:     */  public void append(StringBuffer buffer, String fieldName, byte value)
/*  722:     */  {
/*  723: 723 */    appendFieldStart(buffer, fieldName);
/*  724: 724 */    appendDetail(buffer, fieldName, value);
/*  725: 725 */    appendFieldEnd(buffer, fieldName);
/*  726:     */  }
/*  727:     */  
/*  735:     */  protected void appendDetail(StringBuffer buffer, String fieldName, byte value)
/*  736:     */  {
/*  737: 737 */    buffer.append(value);
/*  738:     */  }
/*  739:     */  
/*  749:     */  public void append(StringBuffer buffer, String fieldName, char value)
/*  750:     */  {
/*  751: 751 */    appendFieldStart(buffer, fieldName);
/*  752: 752 */    appendDetail(buffer, fieldName, value);
/*  753: 753 */    appendFieldEnd(buffer, fieldName);
/*  754:     */  }
/*  755:     */  
/*  763:     */  protected void appendDetail(StringBuffer buffer, String fieldName, char value)
/*  764:     */  {
/*  765: 765 */    buffer.append(value);
/*  766:     */  }
/*  767:     */  
/*  777:     */  public void append(StringBuffer buffer, String fieldName, double value)
/*  778:     */  {
/*  779: 779 */    appendFieldStart(buffer, fieldName);
/*  780: 780 */    appendDetail(buffer, fieldName, value);
/*  781: 781 */    appendFieldEnd(buffer, fieldName);
/*  782:     */  }
/*  783:     */  
/*  791:     */  protected void appendDetail(StringBuffer buffer, String fieldName, double value)
/*  792:     */  {
/*  793: 793 */    buffer.append(value);
/*  794:     */  }
/*  795:     */  
/*  805:     */  public void append(StringBuffer buffer, String fieldName, float value)
/*  806:     */  {
/*  807: 807 */    appendFieldStart(buffer, fieldName);
/*  808: 808 */    appendDetail(buffer, fieldName, value);
/*  809: 809 */    appendFieldEnd(buffer, fieldName);
/*  810:     */  }
/*  811:     */  
/*  819:     */  protected void appendDetail(StringBuffer buffer, String fieldName, float value)
/*  820:     */  {
/*  821: 821 */    buffer.append(value);
/*  822:     */  }
/*  823:     */  
/*  833:     */  public void append(StringBuffer buffer, String fieldName, boolean value)
/*  834:     */  {
/*  835: 835 */    appendFieldStart(buffer, fieldName);
/*  836: 836 */    appendDetail(buffer, fieldName, value);
/*  837: 837 */    appendFieldEnd(buffer, fieldName);
/*  838:     */  }
/*  839:     */  
/*  847:     */  protected void appendDetail(StringBuffer buffer, String fieldName, boolean value)
/*  848:     */  {
/*  849: 849 */    buffer.append(value);
/*  850:     */  }
/*  851:     */  
/*  861:     */  public void append(StringBuffer buffer, String fieldName, Object[] array, Boolean fullDetail)
/*  862:     */  {
/*  863: 863 */    appendFieldStart(buffer, fieldName);
/*  864:     */    
/*  865: 865 */    if (array == null) {
/*  866: 866 */      appendNullText(buffer, fieldName);
/*  867:     */    }
/*  868: 868 */    else if (isFullDetail(fullDetail)) {
/*  869: 869 */      appendDetail(buffer, fieldName, array);
/*  870:     */    }
/*  871:     */    else {
/*  872: 872 */      appendSummary(buffer, fieldName, array);
/*  873:     */    }
/*  874:     */    
/*  875: 875 */    appendFieldEnd(buffer, fieldName);
/*  876:     */  }
/*  877:     */  
/*  888:     */  protected void appendDetail(StringBuffer buffer, String fieldName, Object[] array)
/*  889:     */  {
/*  890: 890 */    buffer.append(this.arrayStart);
/*  891: 891 */    for (int i = 0; i < array.length; i++) {
/*  892: 892 */      Object item = array[i];
/*  893: 893 */      if (i > 0) {
/*  894: 894 */        buffer.append(this.arraySeparator);
/*  895:     */      }
/*  896: 896 */      if (item == null) {
/*  897: 897 */        appendNullText(buffer, fieldName);
/*  898:     */      }
/*  899:     */      else {
/*  900: 900 */        appendInternal(buffer, fieldName, item, this.arrayContentDetail);
/*  901:     */      }
/*  902:     */    }
/*  903: 903 */    buffer.append(this.arrayEnd);
/*  904:     */  }
/*  905:     */  
/*  914:     */  protected void reflectionAppendArrayDetail(StringBuffer buffer, String fieldName, Object array)
/*  915:     */  {
/*  916: 916 */    buffer.append(this.arrayStart);
/*  917: 917 */    int length = Array.getLength(array);
/*  918: 918 */    for (int i = 0; i < length; i++) {
/*  919: 919 */      Object item = Array.get(array, i);
/*  920: 920 */      if (i > 0) {
/*  921: 921 */        buffer.append(this.arraySeparator);
/*  922:     */      }
/*  923: 923 */      if (item == null) {
/*  924: 924 */        appendNullText(buffer, fieldName);
/*  925:     */      }
/*  926:     */      else {
/*  927: 927 */        appendInternal(buffer, fieldName, item, this.arrayContentDetail);
/*  928:     */      }
/*  929:     */    }
/*  930: 930 */    buffer.append(this.arrayEnd);
/*  931:     */  }
/*  932:     */  
/*  941:     */  protected void appendSummary(StringBuffer buffer, String fieldName, Object[] array)
/*  942:     */  {
/*  943: 943 */    appendSummarySize(buffer, fieldName, array.length);
/*  944:     */  }
/*  945:     */  
/*  957:     */  public void append(StringBuffer buffer, String fieldName, long[] array, Boolean fullDetail)
/*  958:     */  {
/*  959: 959 */    appendFieldStart(buffer, fieldName);
/*  960:     */    
/*  961: 961 */    if (array == null) {
/*  962: 962 */      appendNullText(buffer, fieldName);
/*  963:     */    }
/*  964: 964 */    else if (isFullDetail(fullDetail)) {
/*  965: 965 */      appendDetail(buffer, fieldName, array);
/*  966:     */    }
/*  967:     */    else {
/*  968: 968 */      appendSummary(buffer, fieldName, array);
/*  969:     */    }
/*  970:     */    
/*  971: 971 */    appendFieldEnd(buffer, fieldName);
/*  972:     */  }
/*  973:     */  
/*  982:     */  protected void appendDetail(StringBuffer buffer, String fieldName, long[] array)
/*  983:     */  {
/*  984: 984 */    buffer.append(this.arrayStart);
/*  985: 985 */    for (int i = 0; i < array.length; i++) {
/*  986: 986 */      if (i > 0) {
/*  987: 987 */        buffer.append(this.arraySeparator);
/*  988:     */      }
/*  989: 989 */      appendDetail(buffer, fieldName, array[i]);
/*  990:     */    }
/*  991: 991 */    buffer.append(this.arrayEnd);
/*  992:     */  }
/*  993:     */  
/* 1002:     */  protected void appendSummary(StringBuffer buffer, String fieldName, long[] array)
/* 1003:     */  {
/* 1004:1004 */    appendSummarySize(buffer, fieldName, array.length);
/* 1005:     */  }
/* 1006:     */  
/* 1018:     */  public void append(StringBuffer buffer, String fieldName, int[] array, Boolean fullDetail)
/* 1019:     */  {
/* 1020:1020 */    appendFieldStart(buffer, fieldName);
/* 1021:     */    
/* 1022:1022 */    if (array == null) {
/* 1023:1023 */      appendNullText(buffer, fieldName);
/* 1024:     */    }
/* 1025:1025 */    else if (isFullDetail(fullDetail)) {
/* 1026:1026 */      appendDetail(buffer, fieldName, array);
/* 1027:     */    }
/* 1028:     */    else {
/* 1029:1029 */      appendSummary(buffer, fieldName, array);
/* 1030:     */    }
/* 1031:     */    
/* 1032:1032 */    appendFieldEnd(buffer, fieldName);
/* 1033:     */  }
/* 1034:     */  
/* 1043:     */  protected void appendDetail(StringBuffer buffer, String fieldName, int[] array)
/* 1044:     */  {
/* 1045:1045 */    buffer.append(this.arrayStart);
/* 1046:1046 */    for (int i = 0; i < array.length; i++) {
/* 1047:1047 */      if (i > 0) {
/* 1048:1048 */        buffer.append(this.arraySeparator);
/* 1049:     */      }
/* 1050:1050 */      appendDetail(buffer, fieldName, array[i]);
/* 1051:     */    }
/* 1052:1052 */    buffer.append(this.arrayEnd);
/* 1053:     */  }
/* 1054:     */  
/* 1063:     */  protected void appendSummary(StringBuffer buffer, String fieldName, int[] array)
/* 1064:     */  {
/* 1065:1065 */    appendSummarySize(buffer, fieldName, array.length);
/* 1066:     */  }
/* 1067:     */  
/* 1079:     */  public void append(StringBuffer buffer, String fieldName, short[] array, Boolean fullDetail)
/* 1080:     */  {
/* 1081:1081 */    appendFieldStart(buffer, fieldName);
/* 1082:     */    
/* 1083:1083 */    if (array == null) {
/* 1084:1084 */      appendNullText(buffer, fieldName);
/* 1085:     */    }
/* 1086:1086 */    else if (isFullDetail(fullDetail)) {
/* 1087:1087 */      appendDetail(buffer, fieldName, array);
/* 1088:     */    }
/* 1089:     */    else {
/* 1090:1090 */      appendSummary(buffer, fieldName, array);
/* 1091:     */    }
/* 1092:     */    
/* 1093:1093 */    appendFieldEnd(buffer, fieldName);
/* 1094:     */  }
/* 1095:     */  
/* 1104:     */  protected void appendDetail(StringBuffer buffer, String fieldName, short[] array)
/* 1105:     */  {
/* 1106:1106 */    buffer.append(this.arrayStart);
/* 1107:1107 */    for (int i = 0; i < array.length; i++) {
/* 1108:1108 */      if (i > 0) {
/* 1109:1109 */        buffer.append(this.arraySeparator);
/* 1110:     */      }
/* 1111:1111 */      appendDetail(buffer, fieldName, array[i]);
/* 1112:     */    }
/* 1113:1113 */    buffer.append(this.arrayEnd);
/* 1114:     */  }
/* 1115:     */  
/* 1124:     */  protected void appendSummary(StringBuffer buffer, String fieldName, short[] array)
/* 1125:     */  {
/* 1126:1126 */    appendSummarySize(buffer, fieldName, array.length);
/* 1127:     */  }
/* 1128:     */  
/* 1140:     */  public void append(StringBuffer buffer, String fieldName, byte[] array, Boolean fullDetail)
/* 1141:     */  {
/* 1142:1142 */    appendFieldStart(buffer, fieldName);
/* 1143:     */    
/* 1144:1144 */    if (array == null) {
/* 1145:1145 */      appendNullText(buffer, fieldName);
/* 1146:     */    }
/* 1147:1147 */    else if (isFullDetail(fullDetail)) {
/* 1148:1148 */      appendDetail(buffer, fieldName, array);
/* 1149:     */    }
/* 1150:     */    else {
/* 1151:1151 */      appendSummary(buffer, fieldName, array);
/* 1152:     */    }
/* 1153:     */    
/* 1154:1154 */    appendFieldEnd(buffer, fieldName);
/* 1155:     */  }
/* 1156:     */  
/* 1165:     */  protected void appendDetail(StringBuffer buffer, String fieldName, byte[] array)
/* 1166:     */  {
/* 1167:1167 */    buffer.append(this.arrayStart);
/* 1168:1168 */    for (int i = 0; i < array.length; i++) {
/* 1169:1169 */      if (i > 0) {
/* 1170:1170 */        buffer.append(this.arraySeparator);
/* 1171:     */      }
/* 1172:1172 */      appendDetail(buffer, fieldName, array[i]);
/* 1173:     */    }
/* 1174:1174 */    buffer.append(this.arrayEnd);
/* 1175:     */  }
/* 1176:     */  
/* 1185:     */  protected void appendSummary(StringBuffer buffer, String fieldName, byte[] array)
/* 1186:     */  {
/* 1187:1187 */    appendSummarySize(buffer, fieldName, array.length);
/* 1188:     */  }
/* 1189:     */  
/* 1201:     */  public void append(StringBuffer buffer, String fieldName, char[] array, Boolean fullDetail)
/* 1202:     */  {
/* 1203:1203 */    appendFieldStart(buffer, fieldName);
/* 1204:     */    
/* 1205:1205 */    if (array == null) {
/* 1206:1206 */      appendNullText(buffer, fieldName);
/* 1207:     */    }
/* 1208:1208 */    else if (isFullDetail(fullDetail)) {
/* 1209:1209 */      appendDetail(buffer, fieldName, array);
/* 1210:     */    }
/* 1211:     */    else {
/* 1212:1212 */      appendSummary(buffer, fieldName, array);
/* 1213:     */    }
/* 1214:     */    
/* 1215:1215 */    appendFieldEnd(buffer, fieldName);
/* 1216:     */  }
/* 1217:     */  
/* 1226:     */  protected void appendDetail(StringBuffer buffer, String fieldName, char[] array)
/* 1227:     */  {
/* 1228:1228 */    buffer.append(this.arrayStart);
/* 1229:1229 */    for (int i = 0; i < array.length; i++) {
/* 1230:1230 */      if (i > 0) {
/* 1231:1231 */        buffer.append(this.arraySeparator);
/* 1232:     */      }
/* 1233:1233 */      appendDetail(buffer, fieldName, array[i]);
/* 1234:     */    }
/* 1235:1235 */    buffer.append(this.arrayEnd);
/* 1236:     */  }
/* 1237:     */  
/* 1246:     */  protected void appendSummary(StringBuffer buffer, String fieldName, char[] array)
/* 1247:     */  {
/* 1248:1248 */    appendSummarySize(buffer, fieldName, array.length);
/* 1249:     */  }
/* 1250:     */  
/* 1262:     */  public void append(StringBuffer buffer, String fieldName, double[] array, Boolean fullDetail)
/* 1263:     */  {
/* 1264:1264 */    appendFieldStart(buffer, fieldName);
/* 1265:     */    
/* 1266:1266 */    if (array == null) {
/* 1267:1267 */      appendNullText(buffer, fieldName);
/* 1268:     */    }
/* 1269:1269 */    else if (isFullDetail(fullDetail)) {
/* 1270:1270 */      appendDetail(buffer, fieldName, array);
/* 1271:     */    }
/* 1272:     */    else {
/* 1273:1273 */      appendSummary(buffer, fieldName, array);
/* 1274:     */    }
/* 1275:     */    
/* 1276:1276 */    appendFieldEnd(buffer, fieldName);
/* 1277:     */  }
/* 1278:     */  
/* 1287:     */  protected void appendDetail(StringBuffer buffer, String fieldName, double[] array)
/* 1288:     */  {
/* 1289:1289 */    buffer.append(this.arrayStart);
/* 1290:1290 */    for (int i = 0; i < array.length; i++) {
/* 1291:1291 */      if (i > 0) {
/* 1292:1292 */        buffer.append(this.arraySeparator);
/* 1293:     */      }
/* 1294:1294 */      appendDetail(buffer, fieldName, array[i]);
/* 1295:     */    }
/* 1296:1296 */    buffer.append(this.arrayEnd);
/* 1297:     */  }
/* 1298:     */  
/* 1307:     */  protected void appendSummary(StringBuffer buffer, String fieldName, double[] array)
/* 1308:     */  {
/* 1309:1309 */    appendSummarySize(buffer, fieldName, array.length);
/* 1310:     */  }
/* 1311:     */  
/* 1323:     */  public void append(StringBuffer buffer, String fieldName, float[] array, Boolean fullDetail)
/* 1324:     */  {
/* 1325:1325 */    appendFieldStart(buffer, fieldName);
/* 1326:     */    
/* 1327:1327 */    if (array == null) {
/* 1328:1328 */      appendNullText(buffer, fieldName);
/* 1329:     */    }
/* 1330:1330 */    else if (isFullDetail(fullDetail)) {
/* 1331:1331 */      appendDetail(buffer, fieldName, array);
/* 1332:     */    }
/* 1333:     */    else {
/* 1334:1334 */      appendSummary(buffer, fieldName, array);
/* 1335:     */    }
/* 1336:     */    
/* 1337:1337 */    appendFieldEnd(buffer, fieldName);
/* 1338:     */  }
/* 1339:     */  
/* 1348:     */  protected void appendDetail(StringBuffer buffer, String fieldName, float[] array)
/* 1349:     */  {
/* 1350:1350 */    buffer.append(this.arrayStart);
/* 1351:1351 */    for (int i = 0; i < array.length; i++) {
/* 1352:1352 */      if (i > 0) {
/* 1353:1353 */        buffer.append(this.arraySeparator);
/* 1354:     */      }
/* 1355:1355 */      appendDetail(buffer, fieldName, array[i]);
/* 1356:     */    }
/* 1357:1357 */    buffer.append(this.arrayEnd);
/* 1358:     */  }
/* 1359:     */  
/* 1368:     */  protected void appendSummary(StringBuffer buffer, String fieldName, float[] array)
/* 1369:     */  {
/* 1370:1370 */    appendSummarySize(buffer, fieldName, array.length);
/* 1371:     */  }
/* 1372:     */  
/* 1384:     */  public void append(StringBuffer buffer, String fieldName, boolean[] array, Boolean fullDetail)
/* 1385:     */  {
/* 1386:1386 */    appendFieldStart(buffer, fieldName);
/* 1387:     */    
/* 1388:1388 */    if (array == null) {
/* 1389:1389 */      appendNullText(buffer, fieldName);
/* 1390:     */    }
/* 1391:1391 */    else if (isFullDetail(fullDetail)) {
/* 1392:1392 */      appendDetail(buffer, fieldName, array);
/* 1393:     */    }
/* 1394:     */    else {
/* 1395:1395 */      appendSummary(buffer, fieldName, array);
/* 1396:     */    }
/* 1397:     */    
/* 1398:1398 */    appendFieldEnd(buffer, fieldName);
/* 1399:     */  }
/* 1400:     */  
/* 1409:     */  protected void appendDetail(StringBuffer buffer, String fieldName, boolean[] array)
/* 1410:     */  {
/* 1411:1411 */    buffer.append(this.arrayStart);
/* 1412:1412 */    for (int i = 0; i < array.length; i++) {
/* 1413:1413 */      if (i > 0) {
/* 1414:1414 */        buffer.append(this.arraySeparator);
/* 1415:     */      }
/* 1416:1416 */      appendDetail(buffer, fieldName, array[i]);
/* 1417:     */    }
/* 1418:1418 */    buffer.append(this.arrayEnd);
/* 1419:     */  }
/* 1420:     */  
/* 1429:     */  protected void appendSummary(StringBuffer buffer, String fieldName, boolean[] array)
/* 1430:     */  {
/* 1431:1431 */    appendSummarySize(buffer, fieldName, array.length);
/* 1432:     */  }
/* 1433:     */  
/* 1441:     */  protected void appendClassName(StringBuffer buffer, Object object)
/* 1442:     */  {
/* 1443:1443 */    if ((this.useClassName) && (object != null)) {
/* 1444:1444 */      register(object);
/* 1445:1445 */      if (this.useShortClassName) {
/* 1446:1446 */        buffer.append(getShortClassName(object.getClass()));
/* 1447:     */      } else {
/* 1448:1448 */        buffer.append(object.getClass().getName());
/* 1449:     */      }
/* 1450:     */    }
/* 1451:     */  }
/* 1452:     */  
/* 1458:     */  protected void appendIdentityHashCode(StringBuffer buffer, Object object)
/* 1459:     */  {
/* 1460:1460 */    if ((isUseIdentityHashCode()) && (object != null)) {
/* 1461:1461 */      register(object);
/* 1462:1462 */      buffer.append('@');
/* 1463:1463 */      buffer.append(Integer.toHexString(System.identityHashCode(object)));
/* 1464:     */    }
/* 1465:     */  }
/* 1466:     */  
/* 1471:     */  protected void appendContentStart(StringBuffer buffer)
/* 1472:     */  {
/* 1473:1473 */    buffer.append(this.contentStart);
/* 1474:     */  }
/* 1475:     */  
/* 1480:     */  protected void appendContentEnd(StringBuffer buffer)
/* 1481:     */  {
/* 1482:1482 */    buffer.append(this.contentEnd);
/* 1483:     */  }
/* 1484:     */  
/* 1492:     */  protected void appendNullText(StringBuffer buffer, String fieldName)
/* 1493:     */  {
/* 1494:1494 */    buffer.append(this.nullText);
/* 1495:     */  }
/* 1496:     */  
/* 1501:     */  protected void appendFieldSeparator(StringBuffer buffer)
/* 1502:     */  {
/* 1503:1503 */    buffer.append(this.fieldSeparator);
/* 1504:     */  }
/* 1505:     */  
/* 1511:     */  protected void appendFieldStart(StringBuffer buffer, String fieldName)
/* 1512:     */  {
/* 1513:1513 */    if ((this.useFieldNames) && (fieldName != null)) {
/* 1514:1514 */      buffer.append(fieldName);
/* 1515:1515 */      buffer.append(this.fieldNameValueSeparator);
/* 1516:     */    }
/* 1517:     */  }
/* 1518:     */  
/* 1524:     */  protected void appendFieldEnd(StringBuffer buffer, String fieldName)
/* 1525:     */  {
/* 1526:1526 */    appendFieldSeparator(buffer);
/* 1527:     */  }
/* 1528:     */  
/* 1543:     */  protected void appendSummarySize(StringBuffer buffer, String fieldName, int size)
/* 1544:     */  {
/* 1545:1545 */    buffer.append(this.sizeStartText);
/* 1546:1546 */    buffer.append(size);
/* 1547:1547 */    buffer.append(this.sizeEndText);
/* 1548:     */  }
/* 1549:     */  
/* 1563:     */  protected boolean isFullDetail(Boolean fullDetailRequest)
/* 1564:     */  {
/* 1565:1565 */    if (fullDetailRequest == null) {
/* 1566:1566 */      return this.defaultFullDetail;
/* 1567:     */    }
/* 1568:1568 */    return fullDetailRequest.booleanValue();
/* 1569:     */  }
/* 1570:     */  
/* 1579:     */  protected String getShortClassName(Class<?> cls)
/* 1580:     */  {
/* 1581:1581 */    return ClassUtils.getShortClassName(cls);
/* 1582:     */  }
/* 1583:     */  
/* 1593:     */  protected boolean isUseClassName()
/* 1594:     */  {
/* 1595:1595 */    return this.useClassName;
/* 1596:     */  }
/* 1597:     */  
/* 1602:     */  protected void setUseClassName(boolean useClassName)
/* 1603:     */  {
/* 1604:1604 */    this.useClassName = useClassName;
/* 1605:     */  }
/* 1606:     */  
/* 1614:     */  protected boolean isUseShortClassName()
/* 1615:     */  {
/* 1616:1616 */    return this.useShortClassName;
/* 1617:     */  }
/* 1618:     */  
/* 1624:     */  protected void setUseShortClassName(boolean useShortClassName)
/* 1625:     */  {
/* 1626:1626 */    this.useShortClassName = useShortClassName;
/* 1627:     */  }
/* 1628:     */  
/* 1635:     */  protected boolean isUseIdentityHashCode()
/* 1636:     */  {
/* 1637:1637 */    return this.useIdentityHashCode;
/* 1638:     */  }
/* 1639:     */  
/* 1644:     */  protected void setUseIdentityHashCode(boolean useIdentityHashCode)
/* 1645:     */  {
/* 1646:1646 */    this.useIdentityHashCode = useIdentityHashCode;
/* 1647:     */  }
/* 1648:     */  
/* 1655:     */  protected boolean isUseFieldNames()
/* 1656:     */  {
/* 1657:1657 */    return this.useFieldNames;
/* 1658:     */  }
/* 1659:     */  
/* 1664:     */  protected void setUseFieldNames(boolean useFieldNames)
/* 1665:     */  {
/* 1666:1666 */    this.useFieldNames = useFieldNames;
/* 1667:     */  }
/* 1668:     */  
/* 1676:     */  protected boolean isDefaultFullDetail()
/* 1677:     */  {
/* 1678:1678 */    return this.defaultFullDetail;
/* 1679:     */  }
/* 1680:     */  
/* 1686:     */  protected void setDefaultFullDetail(boolean defaultFullDetail)
/* 1687:     */  {
/* 1688:1688 */    this.defaultFullDetail = defaultFullDetail;
/* 1689:     */  }
/* 1690:     */  
/* 1697:     */  protected boolean isArrayContentDetail()
/* 1698:     */  {
/* 1699:1699 */    return this.arrayContentDetail;
/* 1700:     */  }
/* 1701:     */  
/* 1706:     */  protected void setArrayContentDetail(boolean arrayContentDetail)
/* 1707:     */  {
/* 1708:1708 */    this.arrayContentDetail = arrayContentDetail;
/* 1709:     */  }
/* 1710:     */  
/* 1717:     */  protected String getArrayStart()
/* 1718:     */  {
/* 1719:1719 */    return this.arrayStart;
/* 1720:     */  }
/* 1721:     */  
/* 1729:     */  protected void setArrayStart(String arrayStart)
/* 1730:     */  {
/* 1731:1731 */    if (arrayStart == null) {
/* 1732:1732 */      arrayStart = "";
/* 1733:     */    }
/* 1734:1734 */    this.arrayStart = arrayStart;
/* 1735:     */  }
/* 1736:     */  
/* 1743:     */  protected String getArrayEnd()
/* 1744:     */  {
/* 1745:1745 */    return this.arrayEnd;
/* 1746:     */  }
/* 1747:     */  
/* 1755:     */  protected void setArrayEnd(String arrayEnd)
/* 1756:     */  {
/* 1757:1757 */    if (arrayEnd == null) {
/* 1758:1758 */      arrayEnd = "";
/* 1759:     */    }
/* 1760:1760 */    this.arrayEnd = arrayEnd;
/* 1761:     */  }
/* 1762:     */  
/* 1769:     */  protected String getArraySeparator()
/* 1770:     */  {
/* 1771:1771 */    return this.arraySeparator;
/* 1772:     */  }
/* 1773:     */  
/* 1781:     */  protected void setArraySeparator(String arraySeparator)
/* 1782:     */  {
/* 1783:1783 */    if (arraySeparator == null) {
/* 1784:1784 */      arraySeparator = "";
/* 1785:     */    }
/* 1786:1786 */    this.arraySeparator = arraySeparator;
/* 1787:     */  }
/* 1788:     */  
/* 1795:     */  protected String getContentStart()
/* 1796:     */  {
/* 1797:1797 */    return this.contentStart;
/* 1798:     */  }
/* 1799:     */  
/* 1807:     */  protected void setContentStart(String contentStart)
/* 1808:     */  {
/* 1809:1809 */    if (contentStart == null) {
/* 1810:1810 */      contentStart = "";
/* 1811:     */    }
/* 1812:1812 */    this.contentStart = contentStart;
/* 1813:     */  }
/* 1814:     */  
/* 1821:     */  protected String getContentEnd()
/* 1822:     */  {
/* 1823:1823 */    return this.contentEnd;
/* 1824:     */  }
/* 1825:     */  
/* 1833:     */  protected void setContentEnd(String contentEnd)
/* 1834:     */  {
/* 1835:1835 */    if (contentEnd == null) {
/* 1836:1836 */      contentEnd = "";
/* 1837:     */    }
/* 1838:1838 */    this.contentEnd = contentEnd;
/* 1839:     */  }
/* 1840:     */  
/* 1847:     */  protected String getFieldNameValueSeparator()
/* 1848:     */  {
/* 1849:1849 */    return this.fieldNameValueSeparator;
/* 1850:     */  }
/* 1851:     */  
/* 1859:     */  protected void setFieldNameValueSeparator(String fieldNameValueSeparator)
/* 1860:     */  {
/* 1861:1861 */    if (fieldNameValueSeparator == null) {
/* 1862:1862 */      fieldNameValueSeparator = "";
/* 1863:     */    }
/* 1864:1864 */    this.fieldNameValueSeparator = fieldNameValueSeparator;
/* 1865:     */  }
/* 1866:     */  
/* 1873:     */  protected String getFieldSeparator()
/* 1874:     */  {
/* 1875:1875 */    return this.fieldSeparator;
/* 1876:     */  }
/* 1877:     */  
/* 1885:     */  protected void setFieldSeparator(String fieldSeparator)
/* 1886:     */  {
/* 1887:1887 */    if (fieldSeparator == null) {
/* 1888:1888 */      fieldSeparator = "";
/* 1889:     */    }
/* 1890:1890 */    this.fieldSeparator = fieldSeparator;
/* 1891:     */  }
/* 1892:     */  
/* 1901:     */  protected boolean isFieldSeparatorAtStart()
/* 1902:     */  {
/* 1903:1903 */    return this.fieldSeparatorAtStart;
/* 1904:     */  }
/* 1905:     */  
/* 1912:     */  protected void setFieldSeparatorAtStart(boolean fieldSeparatorAtStart)
/* 1913:     */  {
/* 1914:1914 */    this.fieldSeparatorAtStart = fieldSeparatorAtStart;
/* 1915:     */  }
/* 1916:     */  
/* 1925:     */  protected boolean isFieldSeparatorAtEnd()
/* 1926:     */  {
/* 1927:1927 */    return this.fieldSeparatorAtEnd;
/* 1928:     */  }
/* 1929:     */  
/* 1936:     */  protected void setFieldSeparatorAtEnd(boolean fieldSeparatorAtEnd)
/* 1937:     */  {
/* 1938:1938 */    this.fieldSeparatorAtEnd = fieldSeparatorAtEnd;
/* 1939:     */  }
/* 1940:     */  
/* 1947:     */  protected String getNullText()
/* 1948:     */  {
/* 1949:1949 */    return this.nullText;
/* 1950:     */  }
/* 1951:     */  
/* 1959:     */  protected void setNullText(String nullText)
/* 1960:     */  {
/* 1961:1961 */    if (nullText == null) {
/* 1962:1962 */      nullText = "";
/* 1963:     */    }
/* 1964:1964 */    this.nullText = nullText;
/* 1965:     */  }
/* 1966:     */  
/* 1976:     */  protected String getSizeStartText()
/* 1977:     */  {
/* 1978:1978 */    return this.sizeStartText;
/* 1979:     */  }
/* 1980:     */  
/* 1991:     */  protected void setSizeStartText(String sizeStartText)
/* 1992:     */  {
/* 1993:1993 */    if (sizeStartText == null) {
/* 1994:1994 */      sizeStartText = "";
/* 1995:     */    }
/* 1996:1996 */    this.sizeStartText = sizeStartText;
/* 1997:     */  }
/* 1998:     */  
/* 2008:     */  protected String getSizeEndText()
/* 2009:     */  {
/* 2010:2010 */    return this.sizeEndText;
/* 2011:     */  }
/* 2012:     */  
/* 2023:     */  protected void setSizeEndText(String sizeEndText)
/* 2024:     */  {
/* 2025:2025 */    if (sizeEndText == null) {
/* 2026:2026 */      sizeEndText = "";
/* 2027:     */    }
/* 2028:2028 */    this.sizeEndText = sizeEndText;
/* 2029:     */  }
/* 2030:     */  
/* 2040:     */  protected String getSummaryObjectStartText()
/* 2041:     */  {
/* 2042:2042 */    return this.summaryObjectStartText;
/* 2043:     */  }
/* 2044:     */  
/* 2055:     */  protected void setSummaryObjectStartText(String summaryObjectStartText)
/* 2056:     */  {
/* 2057:2057 */    if (summaryObjectStartText == null) {
/* 2058:2058 */      summaryObjectStartText = "";
/* 2059:     */    }
/* 2060:2060 */    this.summaryObjectStartText = summaryObjectStartText;
/* 2061:     */  }
/* 2062:     */  
/* 2072:     */  protected String getSummaryObjectEndText()
/* 2073:     */  {
/* 2074:2074 */    return this.summaryObjectEndText;
/* 2075:     */  }
/* 2076:     */  
/* 2087:     */  protected void setSummaryObjectEndText(String summaryObjectEndText)
/* 2088:     */  {
/* 2089:2089 */    if (summaryObjectEndText == null) {
/* 2090:2090 */      summaryObjectEndText = "";
/* 2091:     */    }
/* 2092:2092 */    this.summaryObjectEndText = summaryObjectEndText;
/* 2093:     */  }
/* 2094:     */  
/* 2108:     */  private static final class DefaultToStringStyle
/* 2109:     */    extends ToStringStyle
/* 2110:     */  {
/* 2111:     */    private static final long serialVersionUID = 1L;
/* 2112:     */    
/* 2125:     */    private Object readResolve()
/* 2126:     */    {
/* 2127:2127 */      return ToStringStyle.DEFAULT_STYLE;
/* 2128:     */    }
/* 2129:     */  }
/* 2130:     */  
/* 2138:     */  private static final class NoFieldNameToStringStyle
/* 2139:     */    extends ToStringStyle
/* 2140:     */  {
/* 2141:     */    private static final long serialVersionUID = 1L;
/* 2142:     */    
/* 2150:     */    NoFieldNameToStringStyle()
/* 2151:     */    {
/* 2152:2152 */      setUseFieldNames(false);
/* 2153:     */    }
/* 2154:     */    
/* 2159:     */    private Object readResolve()
/* 2160:     */    {
/* 2161:2161 */      return ToStringStyle.NO_FIELD_NAMES_STYLE;
/* 2162:     */    }
/* 2163:     */  }
/* 2164:     */  
/* 2172:     */  private static final class ShortPrefixToStringStyle
/* 2173:     */    extends ToStringStyle
/* 2174:     */  {
/* 2175:     */    private static final long serialVersionUID = 1L;
/* 2176:     */    
/* 2184:     */    ShortPrefixToStringStyle()
/* 2185:     */    {
/* 2186:2186 */      setUseShortClassName(true);
/* 2187:2187 */      setUseIdentityHashCode(false);
/* 2188:     */    }
/* 2189:     */    
/* 2193:     */    private Object readResolve()
/* 2194:     */    {
/* 2195:2195 */      return ToStringStyle.SHORT_PREFIX_STYLE;
/* 2196:     */    }
/* 2197:     */  }
/* 2198:     */  
/* 2205:     */  private static final class SimpleToStringStyle
/* 2206:     */    extends ToStringStyle
/* 2207:     */  {
/* 2208:     */    private static final long serialVersionUID = 1L;
/* 2209:     */    
/* 2216:     */    SimpleToStringStyle()
/* 2217:     */    {
/* 2218:2218 */      setUseClassName(false);
/* 2219:2219 */      setUseIdentityHashCode(false);
/* 2220:2220 */      setUseFieldNames(false);
/* 2221:2221 */      setContentStart("");
/* 2222:2222 */      setContentEnd("");
/* 2223:     */    }
/* 2224:     */    
/* 2228:     */    private Object readResolve()
/* 2229:     */    {
/* 2230:2230 */      return ToStringStyle.SIMPLE_STYLE;
/* 2231:     */    }
/* 2232:     */  }
/* 2233:     */  
/* 2241:     */  private static final class MultiLineToStringStyle
/* 2242:     */    extends ToStringStyle
/* 2243:     */  {
/* 2244:     */    private static final long serialVersionUID = 1L;
/* 2245:     */    
/* 2252:     */    MultiLineToStringStyle()
/* 2253:     */    {
/* 2254:2254 */      setContentStart("[");
/* 2255:2255 */      setFieldSeparator(SystemUtils.LINE_SEPARATOR + "  ");
/* 2256:2256 */      setFieldSeparatorAtStart(true);
/* 2257:2257 */      setContentEnd(SystemUtils.LINE_SEPARATOR + "]");
/* 2258:     */    }
/* 2259:     */    
/* 2264:     */    private Object readResolve()
/* 2265:     */    {
/* 2266:2266 */      return ToStringStyle.MULTI_LINE_STYLE;
/* 2267:     */    }
/* 2268:     */  }
/* 2269:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.builder.ToStringStyle
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */