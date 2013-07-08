/*    1:     */package org.apache.commons.lang3.time;
/*    2:     */
/*    3:     */import java.text.ParseException;
/*    4:     */import java.text.ParsePosition;
/*    5:     */import java.text.SimpleDateFormat;
/*    6:     */import java.util.Calendar;
/*    7:     */import java.util.Date;
/*    8:     */import java.util.Iterator;
/*    9:     */import java.util.NoSuchElementException;
/*   10:     */
/*   67:     */public class DateUtils
/*   68:     */{
/*   69:     */  public static final long MILLIS_PER_SECOND = 1000L;
/*   70:     */  public static final long MILLIS_PER_MINUTE = 60000L;
/*   71:     */  public static final long MILLIS_PER_HOUR = 3600000L;
/*   72:     */  public static final long MILLIS_PER_DAY = 86400000L;
/*   73:     */  public static final int SEMI_MONTH = 1001;
/*   74:  74 */  private static final int[][] fields = { { 14 }, { 13 }, { 12 }, { 11, 10 }, { 5, 5, 9 }, { 2, 1001 }, { 1 }, { 0 } };
/*   75:     */  
/*   82:     */  public static final int RANGE_WEEK_SUNDAY = 1;
/*   83:     */  
/*   90:     */  public static final int RANGE_WEEK_MONDAY = 2;
/*   91:     */  
/*   98:     */  public static final int RANGE_WEEK_RELATIVE = 3;
/*   99:     */  
/*  106:     */  public static final int RANGE_WEEK_CENTER = 4;
/*  107:     */  
/*  114:     */  public static final int RANGE_MONTH_SUNDAY = 5;
/*  115:     */  
/*  122:     */  public static final int RANGE_MONTH_MONDAY = 6;
/*  123:     */  
/*  130:     */  private static final int MODIFY_TRUNCATE = 0;
/*  131:     */  
/*  137:     */  private static final int MODIFY_ROUND = 1;
/*  138:     */  
/*  144:     */  private static final int MODIFY_CEILING = 2;
/*  145:     */  
/*  152:     */  public static boolean isSameDay(Date date1, Date date2)
/*  153:     */  {
/*  154: 154 */    if ((date1 == null) || (date2 == null)) {
/*  155: 155 */      throw new IllegalArgumentException("The date must not be null");
/*  156:     */    }
/*  157: 157 */    Calendar cal1 = Calendar.getInstance();
/*  158: 158 */    cal1.setTime(date1);
/*  159: 159 */    Calendar cal2 = Calendar.getInstance();
/*  160: 160 */    cal2.setTime(date2);
/*  161: 161 */    return isSameDay(cal1, cal2);
/*  162:     */  }
/*  163:     */  
/*  176:     */  public static boolean isSameDay(Calendar cal1, Calendar cal2)
/*  177:     */  {
/*  178: 178 */    if ((cal1 == null) || (cal2 == null)) {
/*  179: 179 */      throw new IllegalArgumentException("The date must not be null");
/*  180:     */    }
/*  181: 181 */    return (cal1.get(0) == cal2.get(0)) && (cal1.get(1) == cal2.get(1)) && (cal1.get(6) == cal2.get(6));
/*  182:     */  }
/*  183:     */  
/*  197:     */  public static boolean isSameInstant(Date date1, Date date2)
/*  198:     */  {
/*  199: 199 */    if ((date1 == null) || (date2 == null)) {
/*  200: 200 */      throw new IllegalArgumentException("The date must not be null");
/*  201:     */    }
/*  202: 202 */    return date1.getTime() == date2.getTime();
/*  203:     */  }
/*  204:     */  
/*  215:     */  public static boolean isSameInstant(Calendar cal1, Calendar cal2)
/*  216:     */  {
/*  217: 217 */    if ((cal1 == null) || (cal2 == null)) {
/*  218: 218 */      throw new IllegalArgumentException("The date must not be null");
/*  219:     */    }
/*  220: 220 */    return cal1.getTime().getTime() == cal2.getTime().getTime();
/*  221:     */  }
/*  222:     */  
/*  235:     */  public static boolean isSameLocalTime(Calendar cal1, Calendar cal2)
/*  236:     */  {
/*  237: 237 */    if ((cal1 == null) || (cal2 == null)) {
/*  238: 238 */      throw new IllegalArgumentException("The date must not be null");
/*  239:     */    }
/*  240: 240 */    return (cal1.get(14) == cal2.get(14)) && (cal1.get(13) == cal2.get(13)) && (cal1.get(12) == cal2.get(12)) && (cal1.get(11) == cal2.get(11)) && (cal1.get(6) == cal2.get(6)) && (cal1.get(1) == cal2.get(1)) && (cal1.get(0) == cal2.get(0)) && (cal1.getClass() == cal2.getClass());
/*  241:     */  }
/*  242:     */  
/*  263:     */  public static Date parseDate(String str, String... parsePatterns)
/*  264:     */    throws ParseException
/*  265:     */  {
/*  266: 266 */    return parseDateWithLeniency(str, parsePatterns, true);
/*  267:     */  }
/*  268:     */  
/*  283:     */  public static Date parseDateStrictly(String str, String... parsePatterns)
/*  284:     */    throws ParseException
/*  285:     */  {
/*  286: 286 */    return parseDateWithLeniency(str, parsePatterns, false);
/*  287:     */  }
/*  288:     */  
/*  303:     */  private static Date parseDateWithLeniency(String str, String[] parsePatterns, boolean lenient)
/*  304:     */    throws ParseException
/*  305:     */  {
/*  306: 306 */    if ((str == null) || (parsePatterns == null)) {
/*  307: 307 */      throw new IllegalArgumentException("Date and Patterns must not be null");
/*  308:     */    }
/*  309:     */    
/*  310: 310 */    SimpleDateFormat parser = new SimpleDateFormat();
/*  311: 311 */    parser.setLenient(lenient);
/*  312: 312 */    ParsePosition pos = new ParsePosition(0);
/*  313: 313 */    for (String parsePattern : parsePatterns)
/*  314:     */    {
/*  315: 315 */      String pattern = parsePattern;
/*  316:     */      
/*  318: 318 */      if (parsePattern.endsWith("ZZ")) {
/*  319: 319 */        pattern = pattern.substring(0, pattern.length() - 1);
/*  320:     */      }
/*  321:     */      
/*  322: 322 */      parser.applyPattern(pattern);
/*  323: 323 */      pos.setIndex(0);
/*  324:     */      
/*  325: 325 */      String str2 = str;
/*  326:     */      
/*  327: 327 */      if (parsePattern.endsWith("ZZ")) {
/*  328: 328 */        str2 = str.replaceAll("([-+][0-9][0-9]):([0-9][0-9])$", "$1$2");
/*  329:     */      }
/*  330:     */      
/*  331: 331 */      Date date = parser.parse(str2, pos);
/*  332: 332 */      if ((date != null) && (pos.getIndex() == str2.length())) {
/*  333: 333 */        return date;
/*  334:     */      }
/*  335:     */    }
/*  336: 336 */    throw new ParseException("Unable to parse the date: " + str, -1);
/*  337:     */  }
/*  338:     */  
/*  348:     */  public static Date addYears(Date date, int amount)
/*  349:     */  {
/*  350: 350 */    return add(date, 1, amount);
/*  351:     */  }
/*  352:     */  
/*  362:     */  public static Date addMonths(Date date, int amount)
/*  363:     */  {
/*  364: 364 */    return add(date, 2, amount);
/*  365:     */  }
/*  366:     */  
/*  376:     */  public static Date addWeeks(Date date, int amount)
/*  377:     */  {
/*  378: 378 */    return add(date, 3, amount);
/*  379:     */  }
/*  380:     */  
/*  390:     */  public static Date addDays(Date date, int amount)
/*  391:     */  {
/*  392: 392 */    return add(date, 5, amount);
/*  393:     */  }
/*  394:     */  
/*  404:     */  public static Date addHours(Date date, int amount)
/*  405:     */  {
/*  406: 406 */    return add(date, 11, amount);
/*  407:     */  }
/*  408:     */  
/*  418:     */  public static Date addMinutes(Date date, int amount)
/*  419:     */  {
/*  420: 420 */    return add(date, 12, amount);
/*  421:     */  }
/*  422:     */  
/*  432:     */  public static Date addSeconds(Date date, int amount)
/*  433:     */  {
/*  434: 434 */    return add(date, 13, amount);
/*  435:     */  }
/*  436:     */  
/*  446:     */  public static Date addMilliseconds(Date date, int amount)
/*  447:     */  {
/*  448: 448 */    return add(date, 14, amount);
/*  449:     */  }
/*  450:     */  
/*  461:     */  private static Date add(Date date, int calendarField, int amount)
/*  462:     */  {
/*  463: 463 */    if (date == null) {
/*  464: 464 */      throw new IllegalArgumentException("The date must not be null");
/*  465:     */    }
/*  466: 466 */    Calendar c = Calendar.getInstance();
/*  467: 467 */    c.setTime(date);
/*  468: 468 */    c.add(calendarField, amount);
/*  469: 469 */    return c.getTime();
/*  470:     */  }
/*  471:     */  
/*  482:     */  public static Date setYears(Date date, int amount)
/*  483:     */  {
/*  484: 484 */    return set(date, 1, amount);
/*  485:     */  }
/*  486:     */  
/*  497:     */  public static Date setMonths(Date date, int amount)
/*  498:     */  {
/*  499: 499 */    return set(date, 2, amount);
/*  500:     */  }
/*  501:     */  
/*  512:     */  public static Date setDays(Date date, int amount)
/*  513:     */  {
/*  514: 514 */    return set(date, 5, amount);
/*  515:     */  }
/*  516:     */  
/*  528:     */  public static Date setHours(Date date, int amount)
/*  529:     */  {
/*  530: 530 */    return set(date, 11, amount);
/*  531:     */  }
/*  532:     */  
/*  543:     */  public static Date setMinutes(Date date, int amount)
/*  544:     */  {
/*  545: 545 */    return set(date, 12, amount);
/*  546:     */  }
/*  547:     */  
/*  558:     */  public static Date setSeconds(Date date, int amount)
/*  559:     */  {
/*  560: 560 */    return set(date, 13, amount);
/*  561:     */  }
/*  562:     */  
/*  573:     */  public static Date setMilliseconds(Date date, int amount)
/*  574:     */  {
/*  575: 575 */    return set(date, 14, amount);
/*  576:     */  }
/*  577:     */  
/*  590:     */  private static Date set(Date date, int calendarField, int amount)
/*  591:     */  {
/*  592: 592 */    if (date == null) {
/*  593: 593 */      throw new IllegalArgumentException("The date must not be null");
/*  594:     */    }
/*  595:     */    
/*  596: 596 */    Calendar c = Calendar.getInstance();
/*  597: 597 */    c.setLenient(false);
/*  598: 598 */    c.setTime(date);
/*  599: 599 */    c.set(calendarField, amount);
/*  600: 600 */    return c.getTime();
/*  601:     */  }
/*  602:     */  
/*  611:     */  public static Calendar toCalendar(Date date)
/*  612:     */  {
/*  613: 613 */    Calendar c = Calendar.getInstance();
/*  614: 614 */    c.setTime(date);
/*  615: 615 */    return c;
/*  616:     */  }
/*  617:     */  
/*  644:     */  public static Date round(Date date, int field)
/*  645:     */  {
/*  646: 646 */    if (date == null) {
/*  647: 647 */      throw new IllegalArgumentException("The date must not be null");
/*  648:     */    }
/*  649: 649 */    Calendar gval = Calendar.getInstance();
/*  650: 650 */    gval.setTime(date);
/*  651: 651 */    modify(gval, field, 1);
/*  652: 652 */    return gval.getTime();
/*  653:     */  }
/*  654:     */  
/*  681:     */  public static Calendar round(Calendar date, int field)
/*  682:     */  {
/*  683: 683 */    if (date == null) {
/*  684: 684 */      throw new IllegalArgumentException("The date must not be null");
/*  685:     */    }
/*  686: 686 */    Calendar rounded = (Calendar)date.clone();
/*  687: 687 */    modify(rounded, field, 1);
/*  688: 688 */    return rounded;
/*  689:     */  }
/*  690:     */  
/*  718:     */  public static Date round(Object date, int field)
/*  719:     */  {
/*  720: 720 */    if (date == null) {
/*  721: 721 */      throw new IllegalArgumentException("The date must not be null");
/*  722:     */    }
/*  723: 723 */    if ((date instanceof Date))
/*  724: 724 */      return round((Date)date, field);
/*  725: 725 */    if ((date instanceof Calendar)) {
/*  726: 726 */      return round((Calendar)date, field).getTime();
/*  727:     */    }
/*  728: 728 */    throw new ClassCastException("Could not round " + date);
/*  729:     */  }
/*  730:     */  
/*  747:     */  public static Date truncate(Date date, int field)
/*  748:     */  {
/*  749: 749 */    if (date == null) {
/*  750: 750 */      throw new IllegalArgumentException("The date must not be null");
/*  751:     */    }
/*  752: 752 */    Calendar gval = Calendar.getInstance();
/*  753: 753 */    gval.setTime(date);
/*  754: 754 */    modify(gval, field, 0);
/*  755: 755 */    return gval.getTime();
/*  756:     */  }
/*  757:     */  
/*  772:     */  public static Calendar truncate(Calendar date, int field)
/*  773:     */  {
/*  774: 774 */    if (date == null) {
/*  775: 775 */      throw new IllegalArgumentException("The date must not be null");
/*  776:     */    }
/*  777: 777 */    Calendar truncated = (Calendar)date.clone();
/*  778: 778 */    modify(truncated, field, 0);
/*  779: 779 */    return truncated;
/*  780:     */  }
/*  781:     */  
/*  797:     */  public static Date truncate(Object date, int field)
/*  798:     */  {
/*  799: 799 */    if (date == null) {
/*  800: 800 */      throw new IllegalArgumentException("The date must not be null");
/*  801:     */    }
/*  802: 802 */    if ((date instanceof Date))
/*  803: 803 */      return truncate((Date)date, field);
/*  804: 804 */    if ((date instanceof Calendar)) {
/*  805: 805 */      return truncate((Calendar)date, field).getTime();
/*  806:     */    }
/*  807: 807 */    throw new ClassCastException("Could not truncate " + date);
/*  808:     */  }
/*  809:     */  
/*  827:     */  public static Date ceiling(Date date, int field)
/*  828:     */  {
/*  829: 829 */    if (date == null) {
/*  830: 830 */      throw new IllegalArgumentException("The date must not be null");
/*  831:     */    }
/*  832: 832 */    Calendar gval = Calendar.getInstance();
/*  833: 833 */    gval.setTime(date);
/*  834: 834 */    modify(gval, field, 2);
/*  835: 835 */    return gval.getTime();
/*  836:     */  }
/*  837:     */  
/*  853:     */  public static Calendar ceiling(Calendar date, int field)
/*  854:     */  {
/*  855: 855 */    if (date == null) {
/*  856: 856 */      throw new IllegalArgumentException("The date must not be null");
/*  857:     */    }
/*  858: 858 */    Calendar ceiled = (Calendar)date.clone();
/*  859: 859 */    modify(ceiled, field, 2);
/*  860: 860 */    return ceiled;
/*  861:     */  }
/*  862:     */  
/*  879:     */  public static Date ceiling(Object date, int field)
/*  880:     */  {
/*  881: 881 */    if (date == null) {
/*  882: 882 */      throw new IllegalArgumentException("The date must not be null");
/*  883:     */    }
/*  884: 884 */    if ((date instanceof Date))
/*  885: 885 */      return ceiling((Date)date, field);
/*  886: 886 */    if ((date instanceof Calendar)) {
/*  887: 887 */      return ceiling((Calendar)date, field).getTime();
/*  888:     */    }
/*  889: 889 */    throw new ClassCastException("Could not find ceiling of for type: " + date.getClass());
/*  890:     */  }
/*  891:     */  
/*  901:     */  private static void modify(Calendar val, int field, int modType)
/*  902:     */  {
/*  903: 903 */    if (val.get(1) > 280000000) {
/*  904: 904 */      throw new ArithmeticException("Calendar value too large for accurate calculations");
/*  905:     */    }
/*  906:     */    
/*  907: 907 */    if (field == 14) {
/*  908: 908 */      return;
/*  909:     */    }
/*  910:     */    
/*  917: 917 */    Date date = val.getTime();
/*  918: 918 */    long time = date.getTime();
/*  919: 919 */    boolean done = false;
/*  920:     */    
/*  922: 922 */    int millisecs = val.get(14);
/*  923: 923 */    if ((0 == modType) || (millisecs < 500)) {
/*  924: 924 */      time -= millisecs;
/*  925:     */    }
/*  926: 926 */    if (field == 13) {
/*  927: 927 */      done = true;
/*  928:     */    }
/*  929:     */    
/*  931: 931 */    int seconds = val.get(13);
/*  932: 932 */    if ((!done) && ((0 == modType) || (seconds < 30))) {
/*  933: 933 */      time -= seconds * 1000L;
/*  934:     */    }
/*  935: 935 */    if (field == 12) {
/*  936: 936 */      done = true;
/*  937:     */    }
/*  938:     */    
/*  940: 940 */    int minutes = val.get(12);
/*  941: 941 */    if ((!done) && ((0 == modType) || (minutes < 30))) {
/*  942: 942 */      time -= minutes * 60000L;
/*  943:     */    }
/*  944:     */    
/*  946: 946 */    if (date.getTime() != time) {
/*  947: 947 */      date.setTime(time);
/*  948: 948 */      val.setTime(date);
/*  949:     */    }
/*  950:     */    
/*  952: 952 */    boolean roundUp = false;
/*  953: 953 */    for (int[] aField : fields) {
/*  954: 954 */      for (int element : aField) {
/*  955: 955 */        if (element == field)
/*  956:     */        {
/*  957: 957 */          if ((modType == 2) || ((modType == 1) && (roundUp))) {
/*  958: 958 */            if (field == 1001)
/*  959:     */            {
/*  962: 962 */              if (val.get(5) == 1) {
/*  963: 963 */                val.add(5, 15);
/*  964:     */              } else {
/*  965: 965 */                val.add(5, -15);
/*  966: 966 */                val.add(2, 1);
/*  967:     */              }
/*  968:     */            }
/*  969: 969 */            else if (field == 9)
/*  970:     */            {
/*  973: 973 */              if (val.get(11) == 0) {
/*  974: 974 */                val.add(11, 12);
/*  975:     */              } else {
/*  976: 976 */                val.add(11, -12);
/*  977: 977 */                val.add(5, 1);
/*  978:     */              }
/*  979:     */              
/*  981:     */            }
/*  982:     */            else {
/*  983: 983 */              val.add(aField[0], 1);
/*  984:     */            }
/*  985:     */          }
/*  986: 986 */          return;
/*  987:     */        }
/*  988:     */      }
/*  989:     */      
/*  990: 990 */      int offset = 0;
/*  991: 991 */      boolean offsetSet = false;
/*  992:     */      
/*  993: 993 */      switch (field) {
/*  994:     */      case 1001: 
/*  995: 995 */        if (aField[0] == 5)
/*  996:     */        {
/*  999: 999 */          offset = val.get(5) - 1;
/* 1000:     */          
/* 1002:1002 */          if (offset >= 15) {
/* 1003:1003 */            offset -= 15;
/* 1004:     */          }
/* 1005:     */          
/* 1006:1006 */          roundUp = offset > 7;
/* 1007:1007 */          offsetSet = true; } break;
/* 1008:     */      
/* 1010:     */      case 9: 
/* 1011:1011 */        if (aField[0] == 11)
/* 1012:     */        {
/* 1014:1014 */          offset = val.get(11);
/* 1015:1015 */          if (offset >= 12) {
/* 1016:1016 */            offset -= 12;
/* 1017:     */          }
/* 1018:1018 */          roundUp = offset >= 6;
/* 1019:1019 */          offsetSet = true;
/* 1020:     */        }
/* 1021:     */        break;
/* 1022:     */      }
/* 1023:1023 */      if (!offsetSet) {
/* 1024:1024 */        int min = val.getActualMinimum(aField[0]);
/* 1025:1025 */        int max = val.getActualMaximum(aField[0]);
/* 1026:     */        
/* 1027:1027 */        offset = val.get(aField[0]) - min;
/* 1028:     */        
/* 1029:1029 */        roundUp = offset > (max - min) / 2;
/* 1030:     */      }
/* 1031:     */      
/* 1032:1032 */      if (offset != 0) {
/* 1033:1033 */        val.set(aField[0], val.get(aField[0]) - offset);
/* 1034:     */      }
/* 1035:     */    }
/* 1036:1036 */    throw new IllegalArgumentException("The field " + field + " is not supported");
/* 1037:     */  }
/* 1038:     */  
/* 1064:     */  public static Iterator<Calendar> iterator(Date focus, int rangeStyle)
/* 1065:     */  {
/* 1066:1066 */    if (focus == null) {
/* 1067:1067 */      throw new IllegalArgumentException("The date must not be null");
/* 1068:     */    }
/* 1069:1069 */    Calendar gval = Calendar.getInstance();
/* 1070:1070 */    gval.setTime(focus);
/* 1071:1071 */    return iterator(gval, rangeStyle);
/* 1072:     */  }
/* 1073:     */  
/* 1097:     */  public static Iterator<Calendar> iterator(Calendar focus, int rangeStyle)
/* 1098:     */  {
/* 1099:1099 */    if (focus == null) {
/* 1100:1100 */      throw new IllegalArgumentException("The date must not be null");
/* 1101:     */    }
/* 1102:1102 */    Calendar start = null;
/* 1103:1103 */    Calendar end = null;
/* 1104:1104 */    int startCutoff = 1;
/* 1105:1105 */    int endCutoff = 7;
/* 1106:1106 */    switch (rangeStyle)
/* 1107:     */    {
/* 1108:     */    case 5: 
/* 1109:     */    case 6: 
/* 1110:1110 */      start = truncate(focus, 2);
/* 1111:     */      
/* 1112:1112 */      end = (Calendar)start.clone();
/* 1113:1113 */      end.add(2, 1);
/* 1114:1114 */      end.add(5, -1);
/* 1115:     */      
/* 1116:1116 */      if (rangeStyle == 6) {
/* 1117:1117 */        startCutoff = 2;
/* 1118:1118 */        endCutoff = 1; } break;
/* 1119:     */    
/* 1122:     */    case 1: 
/* 1123:     */    case 2: 
/* 1124:     */    case 3: 
/* 1125:     */    case 4: 
/* 1126:1126 */      start = truncate(focus, 5);
/* 1127:1127 */      end = truncate(focus, 5);
/* 1128:1128 */      switch (rangeStyle)
/* 1129:     */      {
/* 1130:     */      case 1: 
/* 1131:1131 */        break;
/* 1132:     */      case 2: 
/* 1133:1133 */        startCutoff = 2;
/* 1134:1134 */        endCutoff = 1;
/* 1135:1135 */        break;
/* 1136:     */      case 3: 
/* 1137:1137 */        startCutoff = focus.get(7);
/* 1138:1138 */        endCutoff = startCutoff - 1;
/* 1139:1139 */        break;
/* 1140:     */      case 4: 
/* 1141:1141 */        startCutoff = focus.get(7) - 3;
/* 1142:1142 */        endCutoff = focus.get(7) + 3;
/* 1143:     */      }
/* 1144:     */      
/* 1145:1145 */      break;
/* 1146:     */    default: 
/* 1147:1147 */      throw new IllegalArgumentException("The range style " + rangeStyle + " is not valid.");
/* 1148:     */    }
/* 1149:1149 */    if (startCutoff < 1) {
/* 1150:1150 */      startCutoff += 7;
/* 1151:     */    }
/* 1152:1152 */    if (startCutoff > 7) {
/* 1153:1153 */      startCutoff -= 7;
/* 1154:     */    }
/* 1155:1155 */    if (endCutoff < 1) {
/* 1156:1156 */      endCutoff += 7;
/* 1157:     */    }
/* 1158:1158 */    if (endCutoff > 7) {
/* 1159:1159 */      endCutoff -= 7;
/* 1160:     */    }
/* 1161:1161 */    while (start.get(7) != startCutoff) {
/* 1162:1162 */      start.add(5, -1);
/* 1163:     */    }
/* 1164:1164 */    while (end.get(7) != endCutoff) {
/* 1165:1165 */      end.add(5, 1);
/* 1166:     */    }
/* 1167:1167 */    return new DateIterator(start, end);
/* 1168:     */  }
/* 1169:     */  
/* 1185:     */  public static Iterator<?> iterator(Object focus, int rangeStyle)
/* 1186:     */  {
/* 1187:1187 */    if (focus == null) {
/* 1188:1188 */      throw new IllegalArgumentException("The date must not be null");
/* 1189:     */    }
/* 1190:1190 */    if ((focus instanceof Date))
/* 1191:1191 */      return iterator((Date)focus, rangeStyle);
/* 1192:1192 */    if ((focus instanceof Calendar)) {
/* 1193:1193 */      return iterator((Calendar)focus, rangeStyle);
/* 1194:     */    }
/* 1195:1195 */    throw new ClassCastException("Could not iterate based on " + focus);
/* 1196:     */  }
/* 1197:     */  
/* 1231:     */  public static long getFragmentInMilliseconds(Date date, int fragment)
/* 1232:     */  {
/* 1233:1233 */    return getFragment(date, fragment, 14);
/* 1234:     */  }
/* 1235:     */  
/* 1271:     */  public static long getFragmentInSeconds(Date date, int fragment)
/* 1272:     */  {
/* 1273:1273 */    return getFragment(date, fragment, 13);
/* 1274:     */  }
/* 1275:     */  
/* 1311:     */  public static long getFragmentInMinutes(Date date, int fragment)
/* 1312:     */  {
/* 1313:1313 */    return getFragment(date, fragment, 12);
/* 1314:     */  }
/* 1315:     */  
/* 1351:     */  public static long getFragmentInHours(Date date, int fragment)
/* 1352:     */  {
/* 1353:1353 */    return getFragment(date, fragment, 11);
/* 1354:     */  }
/* 1355:     */  
/* 1391:     */  public static long getFragmentInDays(Date date, int fragment)
/* 1392:     */  {
/* 1393:1393 */    return getFragment(date, fragment, 6);
/* 1394:     */  }
/* 1395:     */  
/* 1431:     */  public static long getFragmentInMilliseconds(Calendar calendar, int fragment)
/* 1432:     */  {
/* 1433:1433 */    return getFragment(calendar, fragment, 14);
/* 1434:     */  }
/* 1435:     */  
/* 1470:     */  public static long getFragmentInSeconds(Calendar calendar, int fragment)
/* 1471:     */  {
/* 1472:1472 */    return getFragment(calendar, fragment, 13);
/* 1473:     */  }
/* 1474:     */  
/* 1510:     */  public static long getFragmentInMinutes(Calendar calendar, int fragment)
/* 1511:     */  {
/* 1512:1512 */    return getFragment(calendar, fragment, 12);
/* 1513:     */  }
/* 1514:     */  
/* 1550:     */  public static long getFragmentInHours(Calendar calendar, int fragment)
/* 1551:     */  {
/* 1552:1552 */    return getFragment(calendar, fragment, 11);
/* 1553:     */  }
/* 1554:     */  
/* 1592:     */  public static long getFragmentInDays(Calendar calendar, int fragment)
/* 1593:     */  {
/* 1594:1594 */    return getFragment(calendar, fragment, 6);
/* 1595:     */  }
/* 1596:     */  
/* 1607:     */  private static long getFragment(Date date, int fragment, int unit)
/* 1608:     */  {
/* 1609:1609 */    if (date == null) {
/* 1610:1610 */      throw new IllegalArgumentException("The date must not be null");
/* 1611:     */    }
/* 1612:1612 */    Calendar calendar = Calendar.getInstance();
/* 1613:1613 */    calendar.setTime(date);
/* 1614:1614 */    return getFragment(calendar, fragment, unit);
/* 1615:     */  }
/* 1616:     */  
/* 1627:     */  private static long getFragment(Calendar calendar, int fragment, int unit)
/* 1628:     */  {
/* 1629:1629 */    if (calendar == null) {
/* 1630:1630 */      throw new IllegalArgumentException("The date must not be null");
/* 1631:     */    }
/* 1632:1632 */    long millisPerUnit = getMillisPerUnit(unit);
/* 1633:1633 */    long result = 0L;
/* 1634:     */    
/* 1636:1636 */    switch (fragment) {
/* 1637:     */    case 1: 
/* 1638:1638 */      result += calendar.get(6) * 86400000L / millisPerUnit;
/* 1639:1639 */      break;
/* 1640:     */    case 2: 
/* 1641:1641 */      result += calendar.get(5) * 86400000L / millisPerUnit;
/* 1642:     */    }
/* 1643:     */    
/* 1644:     */    
/* 1645:1645 */    switch (fragment)
/* 1646:     */    {
/* 1649:     */    case 1: 
/* 1650:     */    case 2: 
/* 1651:     */    case 5: 
/* 1652:     */    case 6: 
/* 1653:1653 */      result += calendar.get(11) * 3600000L / millisPerUnit;
/* 1654:     */    
/* 1655:     */    case 11: 
/* 1656:1656 */      result += calendar.get(12) * 60000L / millisPerUnit;
/* 1657:     */    
/* 1658:     */    case 12: 
/* 1659:1659 */      result += calendar.get(13) * 1000L / millisPerUnit;
/* 1660:     */    
/* 1661:     */    case 13: 
/* 1662:1662 */      result += calendar.get(14) * 1 / millisPerUnit;
/* 1663:1663 */      break;
/* 1664:1664 */    case 14:  break;
/* 1665:1665 */    case 3: case 4: case 7: case 8: case 9: case 10: default:  throw new IllegalArgumentException("The fragment " + fragment + " is not supported"); }
/* 1666:     */    
/* 1667:1667 */    return result;
/* 1668:     */  }
/* 1669:     */  
/* 1682:     */  public static boolean truncatedEquals(Calendar cal1, Calendar cal2, int field)
/* 1683:     */  {
/* 1684:1684 */    return truncatedCompareTo(cal1, cal2, field) == 0;
/* 1685:     */  }
/* 1686:     */  
/* 1699:     */  public static boolean truncatedEquals(Date date1, Date date2, int field)
/* 1700:     */  {
/* 1701:1701 */    return truncatedCompareTo(date1, date2, field) == 0;
/* 1702:     */  }
/* 1703:     */  
/* 1717:     */  public static int truncatedCompareTo(Calendar cal1, Calendar cal2, int field)
/* 1718:     */  {
/* 1719:1719 */    Calendar truncatedCal1 = truncate(cal1, field);
/* 1720:1720 */    Calendar truncatedCal2 = truncate(cal2, field);
/* 1721:1721 */    return truncatedCal1.compareTo(truncatedCal2);
/* 1722:     */  }
/* 1723:     */  
/* 1737:     */  public static int truncatedCompareTo(Date date1, Date date2, int field)
/* 1738:     */  {
/* 1739:1739 */    Date truncatedDate1 = truncate(date1, field);
/* 1740:1740 */    Date truncatedDate2 = truncate(date2, field);
/* 1741:1741 */    return truncatedDate1.compareTo(truncatedDate2);
/* 1742:     */  }
/* 1743:     */  
/* 1752:     */  private static long getMillisPerUnit(int unit)
/* 1753:     */  {
/* 1754:1754 */    long result = 9223372036854775807L;
/* 1755:1755 */    switch (unit) {
/* 1756:     */    case 5: 
/* 1757:     */    case 6: 
/* 1758:1758 */      result = 86400000L;
/* 1759:1759 */      break;
/* 1760:     */    case 11: 
/* 1761:1761 */      result = 3600000L;
/* 1762:1762 */      break;
/* 1763:     */    case 12: 
/* 1764:1764 */      result = 60000L;
/* 1765:1765 */      break;
/* 1766:     */    case 13: 
/* 1767:1767 */      result = 1000L;
/* 1768:1768 */      break;
/* 1769:     */    case 14: 
/* 1770:1770 */      result = 1L;
/* 1771:1771 */      break;
/* 1772:1772 */    case 7: case 8: case 9: case 10: default:  throw new IllegalArgumentException("The unit " + unit + " cannot be represented is milleseconds");
/* 1773:     */    }
/* 1774:1774 */    return result;
/* 1775:     */  }
/* 1776:     */  
/* 1780:     */  static class DateIterator
/* 1781:     */    implements Iterator<Calendar>
/* 1782:     */  {
/* 1783:     */    private final Calendar endFinal;
/* 1784:     */    
/* 1787:     */    private final Calendar spot;
/* 1788:     */    
/* 1791:     */    DateIterator(Calendar startFinal, Calendar endFinal)
/* 1792:     */    {
/* 1793:1793 */      this.endFinal = endFinal;
/* 1794:1794 */      this.spot = startFinal;
/* 1795:1795 */      this.spot.add(5, -1);
/* 1796:     */    }
/* 1797:     */    
/* 1802:     */    public boolean hasNext()
/* 1803:     */    {
/* 1804:1804 */      return this.spot.before(this.endFinal);
/* 1805:     */    }
/* 1806:     */    
/* 1811:     */    public Calendar next()
/* 1812:     */    {
/* 1813:1813 */      if (this.spot.equals(this.endFinal)) {
/* 1814:1814 */        throw new NoSuchElementException();
/* 1815:     */      }
/* 1816:1816 */      this.spot.add(5, 1);
/* 1817:1817 */      return (Calendar)this.spot.clone();
/* 1818:     */    }
/* 1819:     */    
/* 1825:     */    public void remove()
/* 1826:     */    {
/* 1827:1827 */      throw new UnsupportedOperationException();
/* 1828:     */    }
/* 1829:     */  }
/* 1830:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.time.DateUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */