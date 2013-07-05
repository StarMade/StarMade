/*      */ package org.apache.commons.lang3.time;
/*      */ 
/*      */ import java.text.ParseException;
/*      */ import java.text.ParsePosition;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.Calendar;
/*      */ import java.util.Date;
/*      */ import java.util.Iterator;
/*      */ import java.util.NoSuchElementException;
/*      */ 
/*      */ public class DateUtils
/*      */ {
/*      */   public static final long MILLIS_PER_SECOND = 1000L;
/*      */   public static final long MILLIS_PER_MINUTE = 60000L;
/*      */   public static final long MILLIS_PER_HOUR = 3600000L;
/*      */   public static final long MILLIS_PER_DAY = 86400000L;
/*      */   public static final int SEMI_MONTH = 1001;
/*   74 */   private static final int[][] fields = { { 14 }, { 13 }, { 12 }, { 11, 10 }, { 5, 5, 9 }, { 2, 1001 }, { 1 }, { 0 } };
/*      */   public static final int RANGE_WEEK_SUNDAY = 1;
/*      */   public static final int RANGE_WEEK_MONDAY = 2;
/*      */   public static final int RANGE_WEEK_RELATIVE = 3;
/*      */   public static final int RANGE_WEEK_CENTER = 4;
/*      */   public static final int RANGE_MONTH_SUNDAY = 5;
/*      */   public static final int RANGE_MONTH_MONDAY = 6;
/*      */   private static final int MODIFY_TRUNCATE = 0;
/*      */   private static final int MODIFY_ROUND = 1;
/*      */   private static final int MODIFY_CEILING = 2;
/*      */ 
/*      */   public static boolean isSameDay(Date date1, Date date2)
/*      */   {
/*  154 */     if ((date1 == null) || (date2 == null)) {
/*  155 */       throw new IllegalArgumentException("The date must not be null");
/*      */     }
/*  157 */     Calendar cal1 = Calendar.getInstance();
/*  158 */     cal1.setTime(date1);
/*  159 */     Calendar cal2 = Calendar.getInstance();
/*  160 */     cal2.setTime(date2);
/*  161 */     return isSameDay(cal1, cal2);
/*      */   }
/*      */ 
/*      */   public static boolean isSameDay(Calendar cal1, Calendar cal2)
/*      */   {
/*  178 */     if ((cal1 == null) || (cal2 == null)) {
/*  179 */       throw new IllegalArgumentException("The date must not be null");
/*      */     }
/*  181 */     return (cal1.get(0) == cal2.get(0)) && (cal1.get(1) == cal2.get(1)) && (cal1.get(6) == cal2.get(6));
/*      */   }
/*      */ 
/*      */   public static boolean isSameInstant(Date date1, Date date2)
/*      */   {
/*  199 */     if ((date1 == null) || (date2 == null)) {
/*  200 */       throw new IllegalArgumentException("The date must not be null");
/*      */     }
/*  202 */     return date1.getTime() == date2.getTime();
/*      */   }
/*      */ 
/*      */   public static boolean isSameInstant(Calendar cal1, Calendar cal2)
/*      */   {
/*  217 */     if ((cal1 == null) || (cal2 == null)) {
/*  218 */       throw new IllegalArgumentException("The date must not be null");
/*      */     }
/*  220 */     return cal1.getTime().getTime() == cal2.getTime().getTime();
/*      */   }
/*      */ 
/*      */   public static boolean isSameLocalTime(Calendar cal1, Calendar cal2)
/*      */   {
/*  237 */     if ((cal1 == null) || (cal2 == null)) {
/*  238 */       throw new IllegalArgumentException("The date must not be null");
/*      */     }
/*  240 */     return (cal1.get(14) == cal2.get(14)) && (cal1.get(13) == cal2.get(13)) && (cal1.get(12) == cal2.get(12)) && (cal1.get(11) == cal2.get(11)) && (cal1.get(6) == cal2.get(6)) && (cal1.get(1) == cal2.get(1)) && (cal1.get(0) == cal2.get(0)) && (cal1.getClass() == cal2.getClass());
/*      */   }
/*      */ 
/*      */   public static Date parseDate(String str, String[] parsePatterns)
/*      */     throws ParseException
/*      */   {
/*  266 */     return parseDateWithLeniency(str, parsePatterns, true);
/*      */   }
/*      */ 
/*      */   public static Date parseDateStrictly(String str, String[] parsePatterns)
/*      */     throws ParseException
/*      */   {
/*  286 */     return parseDateWithLeniency(str, parsePatterns, false);
/*      */   }
/*      */ 
/*      */   private static Date parseDateWithLeniency(String str, String[] parsePatterns, boolean lenient)
/*      */     throws ParseException
/*      */   {
/*  306 */     if ((str == null) || (parsePatterns == null)) {
/*  307 */       throw new IllegalArgumentException("Date and Patterns must not be null");
/*      */     }
/*      */ 
/*  310 */     SimpleDateFormat parser = new SimpleDateFormat();
/*  311 */     parser.setLenient(lenient);
/*  312 */     ParsePosition pos = new ParsePosition(0);
/*  313 */     for (String parsePattern : parsePatterns)
/*      */     {
/*  315 */       String pattern = parsePattern;
/*      */ 
/*  318 */       if (parsePattern.endsWith("ZZ")) {
/*  319 */         pattern = pattern.substring(0, pattern.length() - 1);
/*      */       }
/*      */ 
/*  322 */       parser.applyPattern(pattern);
/*  323 */       pos.setIndex(0);
/*      */ 
/*  325 */       String str2 = str;
/*      */ 
/*  327 */       if (parsePattern.endsWith("ZZ")) {
/*  328 */         str2 = str.replaceAll("([-+][0-9][0-9]):([0-9][0-9])$", "$1$2");
/*      */       }
/*      */ 
/*  331 */       Date date = parser.parse(str2, pos);
/*  332 */       if ((date != null) && (pos.getIndex() == str2.length())) {
/*  333 */         return date;
/*      */       }
/*      */     }
/*  336 */     throw new ParseException("Unable to parse the date: " + str, -1);
/*      */   }
/*      */ 
/*      */   public static Date addYears(Date date, int amount)
/*      */   {
/*  350 */     return add(date, 1, amount);
/*      */   }
/*      */ 
/*      */   public static Date addMonths(Date date, int amount)
/*      */   {
/*  364 */     return add(date, 2, amount);
/*      */   }
/*      */ 
/*      */   public static Date addWeeks(Date date, int amount)
/*      */   {
/*  378 */     return add(date, 3, amount);
/*      */   }
/*      */ 
/*      */   public static Date addDays(Date date, int amount)
/*      */   {
/*  392 */     return add(date, 5, amount);
/*      */   }
/*      */ 
/*      */   public static Date addHours(Date date, int amount)
/*      */   {
/*  406 */     return add(date, 11, amount);
/*      */   }
/*      */ 
/*      */   public static Date addMinutes(Date date, int amount)
/*      */   {
/*  420 */     return add(date, 12, amount);
/*      */   }
/*      */ 
/*      */   public static Date addSeconds(Date date, int amount)
/*      */   {
/*  434 */     return add(date, 13, amount);
/*      */   }
/*      */ 
/*      */   public static Date addMilliseconds(Date date, int amount)
/*      */   {
/*  448 */     return add(date, 14, amount);
/*      */   }
/*      */ 
/*      */   private static Date add(Date date, int calendarField, int amount)
/*      */   {
/*  463 */     if (date == null) {
/*  464 */       throw new IllegalArgumentException("The date must not be null");
/*      */     }
/*  466 */     Calendar c = Calendar.getInstance();
/*  467 */     c.setTime(date);
/*  468 */     c.add(calendarField, amount);
/*  469 */     return c.getTime();
/*      */   }
/*      */ 
/*      */   public static Date setYears(Date date, int amount)
/*      */   {
/*  484 */     return set(date, 1, amount);
/*      */   }
/*      */ 
/*      */   public static Date setMonths(Date date, int amount)
/*      */   {
/*  499 */     return set(date, 2, amount);
/*      */   }
/*      */ 
/*      */   public static Date setDays(Date date, int amount)
/*      */   {
/*  514 */     return set(date, 5, amount);
/*      */   }
/*      */ 
/*      */   public static Date setHours(Date date, int amount)
/*      */   {
/*  530 */     return set(date, 11, amount);
/*      */   }
/*      */ 
/*      */   public static Date setMinutes(Date date, int amount)
/*      */   {
/*  545 */     return set(date, 12, amount);
/*      */   }
/*      */ 
/*      */   public static Date setSeconds(Date date, int amount)
/*      */   {
/*  560 */     return set(date, 13, amount);
/*      */   }
/*      */ 
/*      */   public static Date setMilliseconds(Date date, int amount)
/*      */   {
/*  575 */     return set(date, 14, amount);
/*      */   }
/*      */ 
/*      */   private static Date set(Date date, int calendarField, int amount)
/*      */   {
/*  592 */     if (date == null) {
/*  593 */       throw new IllegalArgumentException("The date must not be null");
/*      */     }
/*      */ 
/*  596 */     Calendar c = Calendar.getInstance();
/*  597 */     c.setLenient(false);
/*  598 */     c.setTime(date);
/*  599 */     c.set(calendarField, amount);
/*  600 */     return c.getTime();
/*      */   }
/*      */ 
/*      */   public static Calendar toCalendar(Date date)
/*      */   {
/*  613 */     Calendar c = Calendar.getInstance();
/*  614 */     c.setTime(date);
/*  615 */     return c;
/*      */   }
/*      */ 
/*      */   public static Date round(Date date, int field)
/*      */   {
/*  646 */     if (date == null) {
/*  647 */       throw new IllegalArgumentException("The date must not be null");
/*      */     }
/*  649 */     Calendar gval = Calendar.getInstance();
/*  650 */     gval.setTime(date);
/*  651 */     modify(gval, field, 1);
/*  652 */     return gval.getTime();
/*      */   }
/*      */ 
/*      */   public static Calendar round(Calendar date, int field)
/*      */   {
/*  683 */     if (date == null) {
/*  684 */       throw new IllegalArgumentException("The date must not be null");
/*      */     }
/*  686 */     Calendar rounded = (Calendar)date.clone();
/*  687 */     modify(rounded, field, 1);
/*  688 */     return rounded;
/*      */   }
/*      */ 
/*      */   public static Date round(Object date, int field)
/*      */   {
/*  720 */     if (date == null) {
/*  721 */       throw new IllegalArgumentException("The date must not be null");
/*      */     }
/*  723 */     if ((date instanceof Date))
/*  724 */       return round((Date)date, field);
/*  725 */     if ((date instanceof Calendar)) {
/*  726 */       return round((Calendar)date, field).getTime();
/*      */     }
/*  728 */     throw new ClassCastException("Could not round " + date);
/*      */   }
/*      */ 
/*      */   public static Date truncate(Date date, int field)
/*      */   {
/*  749 */     if (date == null) {
/*  750 */       throw new IllegalArgumentException("The date must not be null");
/*      */     }
/*  752 */     Calendar gval = Calendar.getInstance();
/*  753 */     gval.setTime(date);
/*  754 */     modify(gval, field, 0);
/*  755 */     return gval.getTime();
/*      */   }
/*      */ 
/*      */   public static Calendar truncate(Calendar date, int field)
/*      */   {
/*  774 */     if (date == null) {
/*  775 */       throw new IllegalArgumentException("The date must not be null");
/*      */     }
/*  777 */     Calendar truncated = (Calendar)date.clone();
/*  778 */     modify(truncated, field, 0);
/*  779 */     return truncated;
/*      */   }
/*      */ 
/*      */   public static Date truncate(Object date, int field)
/*      */   {
/*  799 */     if (date == null) {
/*  800 */       throw new IllegalArgumentException("The date must not be null");
/*      */     }
/*  802 */     if ((date instanceof Date))
/*  803 */       return truncate((Date)date, field);
/*  804 */     if ((date instanceof Calendar)) {
/*  805 */       return truncate((Calendar)date, field).getTime();
/*      */     }
/*  807 */     throw new ClassCastException("Could not truncate " + date);
/*      */   }
/*      */ 
/*      */   public static Date ceiling(Date date, int field)
/*      */   {
/*  829 */     if (date == null) {
/*  830 */       throw new IllegalArgumentException("The date must not be null");
/*      */     }
/*  832 */     Calendar gval = Calendar.getInstance();
/*  833 */     gval.setTime(date);
/*  834 */     modify(gval, field, 2);
/*  835 */     return gval.getTime();
/*      */   }
/*      */ 
/*      */   public static Calendar ceiling(Calendar date, int field)
/*      */   {
/*  855 */     if (date == null) {
/*  856 */       throw new IllegalArgumentException("The date must not be null");
/*      */     }
/*  858 */     Calendar ceiled = (Calendar)date.clone();
/*  859 */     modify(ceiled, field, 2);
/*  860 */     return ceiled;
/*      */   }
/*      */ 
/*      */   public static Date ceiling(Object date, int field)
/*      */   {
/*  881 */     if (date == null) {
/*  882 */       throw new IllegalArgumentException("The date must not be null");
/*      */     }
/*  884 */     if ((date instanceof Date))
/*  885 */       return ceiling((Date)date, field);
/*  886 */     if ((date instanceof Calendar)) {
/*  887 */       return ceiling((Calendar)date, field).getTime();
/*      */     }
/*  889 */     throw new ClassCastException("Could not find ceiling of for type: " + date.getClass());
/*      */   }
/*      */ 
/*      */   private static void modify(Calendar val, int field, int modType)
/*      */   {
/*  903 */     if (val.get(1) > 280000000) {
/*  904 */       throw new ArithmeticException("Calendar value too large for accurate calculations");
/*      */     }
/*      */ 
/*  907 */     if (field == 14) {
/*  908 */       return;
/*      */     }
/*      */ 
/*  917 */     Date date = val.getTime();
/*  918 */     long time = date.getTime();
/*  919 */     boolean done = false;
/*      */ 
/*  922 */     int millisecs = val.get(14);
/*  923 */     if ((0 == modType) || (millisecs < 500)) {
/*  924 */       time -= millisecs;
/*      */     }
/*  926 */     if (field == 13) {
/*  927 */       done = true;
/*      */     }
/*      */ 
/*  931 */     int seconds = val.get(13);
/*  932 */     if ((!done) && ((0 == modType) || (seconds < 30))) {
/*  933 */       time -= seconds * 1000L;
/*      */     }
/*  935 */     if (field == 12) {
/*  936 */       done = true;
/*      */     }
/*      */ 
/*  940 */     int minutes = val.get(12);
/*  941 */     if ((!done) && ((0 == modType) || (minutes < 30))) {
/*  942 */       time -= minutes * 60000L;
/*      */     }
/*      */ 
/*  946 */     if (date.getTime() != time) {
/*  947 */       date.setTime(time);
/*  948 */       val.setTime(date);
/*      */     }
/*      */ 
/*  952 */     boolean roundUp = false;
/*  953 */     for (int[] aField : fields) {
/*  954 */       for (int element : aField) {
/*  955 */         if (element == field)
/*      */         {
/*  957 */           if ((modType == 2) || ((modType == 1) && (roundUp))) {
/*  958 */             if (field == 1001)
/*      */             {
/*  962 */               if (val.get(5) == 1) {
/*  963 */                 val.add(5, 15);
/*      */               } else {
/*  965 */                 val.add(5, -15);
/*  966 */                 val.add(2, 1);
/*      */               }
/*      */             }
/*  969 */             else if (field == 9)
/*      */             {
/*  973 */               if (val.get(11) == 0) {
/*  974 */                 val.add(11, 12);
/*      */               } else {
/*  976 */                 val.add(11, -12);
/*  977 */                 val.add(5, 1);
/*      */               }
/*      */ 
/*      */             }
/*      */             else
/*      */             {
/*  983 */               val.add(aField[0], 1);
/*      */             }
/*      */           }
/*  986 */           return;
/*      */         }
/*      */       }
/*      */ 
/*  990 */       int offset = 0;
/*  991 */       boolean offsetSet = false;
/*      */ 
/*  993 */       switch (field) {
/*      */       case 1001:
/*  995 */         if (aField[0] == 5)
/*      */         {
/*  999 */           offset = val.get(5) - 1;
/*      */ 
/* 1002 */           if (offset >= 15) {
/* 1003 */             offset -= 15;
/*      */           }
/*      */ 
/* 1006 */           roundUp = offset > 7;
/* 1007 */           offsetSet = true; } break;
/*      */       case 9:
/* 1011 */         if (aField[0] == 11)
/*      */         {
/* 1014 */           offset = val.get(11);
/* 1015 */           if (offset >= 12) {
/* 1016 */             offset -= 12;
/*      */           }
/* 1018 */           roundUp = offset >= 6;
/* 1019 */           offsetSet = true;
/*      */         }
/*      */         break;
/*      */       }
/* 1023 */       if (!offsetSet) {
/* 1024 */         int min = val.getActualMinimum(aField[0]);
/* 1025 */         int max = val.getActualMaximum(aField[0]);
/*      */ 
/* 1027 */         offset = val.get(aField[0]) - min;
/*      */ 
/* 1029 */         roundUp = offset > (max - min) / 2;
/*      */       }
/*      */ 
/* 1032 */       if (offset != 0) {
/* 1033 */         val.set(aField[0], val.get(aField[0]) - offset);
/*      */       }
/*      */     }
/* 1036 */     throw new IllegalArgumentException("The field " + field + " is not supported");
/*      */   }
/*      */ 
/*      */   public static Iterator<Calendar> iterator(Date focus, int rangeStyle)
/*      */   {
/* 1066 */     if (focus == null) {
/* 1067 */       throw new IllegalArgumentException("The date must not be null");
/*      */     }
/* 1069 */     Calendar gval = Calendar.getInstance();
/* 1070 */     gval.setTime(focus);
/* 1071 */     return iterator(gval, rangeStyle);
/*      */   }
/*      */ 
/*      */   public static Iterator<Calendar> iterator(Calendar focus, int rangeStyle)
/*      */   {
/* 1099 */     if (focus == null) {
/* 1100 */       throw new IllegalArgumentException("The date must not be null");
/*      */     }
/* 1102 */     Calendar start = null;
/* 1103 */     Calendar end = null;
/* 1104 */     int startCutoff = 1;
/* 1105 */     int endCutoff = 7;
/* 1106 */     switch (rangeStyle)
/*      */     {
/*      */     case 5:
/*      */     case 6:
/* 1110 */       start = truncate(focus, 2);
/*      */ 
/* 1112 */       end = (Calendar)start.clone();
/* 1113 */       end.add(2, 1);
/* 1114 */       end.add(5, -1);
/*      */ 
/* 1116 */       if (rangeStyle == 6) {
/* 1117 */         startCutoff = 2;
/* 1118 */         endCutoff = 1; } break;
/*      */     case 1:
/*      */     case 2:
/*      */     case 3:
/*      */     case 4:
/* 1126 */       start = truncate(focus, 5);
/* 1127 */       end = truncate(focus, 5);
/* 1128 */       switch (rangeStyle)
/*      */       {
/*      */       case 1:
/* 1131 */         break;
/*      */       case 2:
/* 1133 */         startCutoff = 2;
/* 1134 */         endCutoff = 1;
/* 1135 */         break;
/*      */       case 3:
/* 1137 */         startCutoff = focus.get(7);
/* 1138 */         endCutoff = startCutoff - 1;
/* 1139 */         break;
/*      */       case 4:
/* 1141 */         startCutoff = focus.get(7) - 3;
/* 1142 */         endCutoff = focus.get(7) + 3;
/*      */       }
/*      */ 
/* 1145 */       break;
/*      */     default:
/* 1147 */       throw new IllegalArgumentException("The range style " + rangeStyle + " is not valid.");
/*      */     }
/* 1149 */     if (startCutoff < 1) {
/* 1150 */       startCutoff += 7;
/*      */     }
/* 1152 */     if (startCutoff > 7) {
/* 1153 */       startCutoff -= 7;
/*      */     }
/* 1155 */     if (endCutoff < 1) {
/* 1156 */       endCutoff += 7;
/*      */     }
/* 1158 */     if (endCutoff > 7) {
/* 1159 */       endCutoff -= 7;
/*      */     }
/* 1161 */     while (start.get(7) != startCutoff) {
/* 1162 */       start.add(5, -1);
/*      */     }
/* 1164 */     while (end.get(7) != endCutoff) {
/* 1165 */       end.add(5, 1);
/*      */     }
/* 1167 */     return new DateIterator(start, end);
/*      */   }
/*      */ 
/*      */   public static Iterator<?> iterator(Object focus, int rangeStyle)
/*      */   {
/* 1187 */     if (focus == null) {
/* 1188 */       throw new IllegalArgumentException("The date must not be null");
/*      */     }
/* 1190 */     if ((focus instanceof Date))
/* 1191 */       return iterator((Date)focus, rangeStyle);
/* 1192 */     if ((focus instanceof Calendar)) {
/* 1193 */       return iterator((Calendar)focus, rangeStyle);
/*      */     }
/* 1195 */     throw new ClassCastException("Could not iterate based on " + focus);
/*      */   }
/*      */ 
/*      */   public static long getFragmentInMilliseconds(Date date, int fragment)
/*      */   {
/* 1233 */     return getFragment(date, fragment, 14);
/*      */   }
/*      */ 
/*      */   public static long getFragmentInSeconds(Date date, int fragment)
/*      */   {
/* 1273 */     return getFragment(date, fragment, 13);
/*      */   }
/*      */ 
/*      */   public static long getFragmentInMinutes(Date date, int fragment)
/*      */   {
/* 1313 */     return getFragment(date, fragment, 12);
/*      */   }
/*      */ 
/*      */   public static long getFragmentInHours(Date date, int fragment)
/*      */   {
/* 1353 */     return getFragment(date, fragment, 11);
/*      */   }
/*      */ 
/*      */   public static long getFragmentInDays(Date date, int fragment)
/*      */   {
/* 1393 */     return getFragment(date, fragment, 6);
/*      */   }
/*      */ 
/*      */   public static long getFragmentInMilliseconds(Calendar calendar, int fragment)
/*      */   {
/* 1433 */     return getFragment(calendar, fragment, 14);
/*      */   }
/*      */ 
/*      */   public static long getFragmentInSeconds(Calendar calendar, int fragment)
/*      */   {
/* 1472 */     return getFragment(calendar, fragment, 13);
/*      */   }
/*      */ 
/*      */   public static long getFragmentInMinutes(Calendar calendar, int fragment)
/*      */   {
/* 1512 */     return getFragment(calendar, fragment, 12);
/*      */   }
/*      */ 
/*      */   public static long getFragmentInHours(Calendar calendar, int fragment)
/*      */   {
/* 1552 */     return getFragment(calendar, fragment, 11);
/*      */   }
/*      */ 
/*      */   public static long getFragmentInDays(Calendar calendar, int fragment)
/*      */   {
/* 1594 */     return getFragment(calendar, fragment, 6);
/*      */   }
/*      */ 
/*      */   private static long getFragment(Date date, int fragment, int unit)
/*      */   {
/* 1609 */     if (date == null) {
/* 1610 */       throw new IllegalArgumentException("The date must not be null");
/*      */     }
/* 1612 */     Calendar calendar = Calendar.getInstance();
/* 1613 */     calendar.setTime(date);
/* 1614 */     return getFragment(calendar, fragment, unit);
/*      */   }
/*      */ 
/*      */   private static long getFragment(Calendar calendar, int fragment, int unit)
/*      */   {
/* 1629 */     if (calendar == null) {
/* 1630 */       throw new IllegalArgumentException("The date must not be null");
/*      */     }
/* 1632 */     long millisPerUnit = getMillisPerUnit(unit);
/* 1633 */     long result = 0L;
/*      */ 
/* 1636 */     switch (fragment) {
/*      */     case 1:
/* 1638 */       result += calendar.get(6) * 86400000L / millisPerUnit;
/* 1639 */       break;
/*      */     case 2:
/* 1641 */       result += calendar.get(5) * 86400000L / millisPerUnit;
/*      */     }
/*      */ 
/* 1645 */     switch (fragment)
/*      */     {
/*      */     case 1:
/*      */     case 2:
/*      */     case 5:
/*      */     case 6:
/* 1653 */       result += calendar.get(11) * 3600000L / millisPerUnit;
/*      */     case 11:
/* 1656 */       result += calendar.get(12) * 60000L / millisPerUnit;
/*      */     case 12:
/* 1659 */       result += calendar.get(13) * 1000L / millisPerUnit;
/*      */     case 13:
/* 1662 */       result += calendar.get(14) * 1 / millisPerUnit;
/* 1663 */       break;
/*      */     case 14:
/* 1664 */       break;
/*      */     case 3:
/*      */     case 4:
/*      */     case 7:
/*      */     case 8:
/*      */     case 9:
/*      */     case 10:
/*      */     default:
/* 1665 */       throw new IllegalArgumentException("The fragment " + fragment + " is not supported");
/*      */     }
/* 1667 */     return result;
/*      */   }
/*      */ 
/*      */   public static boolean truncatedEquals(Calendar cal1, Calendar cal2, int field)
/*      */   {
/* 1684 */     return truncatedCompareTo(cal1, cal2, field) == 0;
/*      */   }
/*      */ 
/*      */   public static boolean truncatedEquals(Date date1, Date date2, int field)
/*      */   {
/* 1701 */     return truncatedCompareTo(date1, date2, field) == 0;
/*      */   }
/*      */ 
/*      */   public static int truncatedCompareTo(Calendar cal1, Calendar cal2, int field)
/*      */   {
/* 1719 */     Calendar truncatedCal1 = truncate(cal1, field);
/* 1720 */     Calendar truncatedCal2 = truncate(cal2, field);
/* 1721 */     return truncatedCal1.compareTo(truncatedCal2);
/*      */   }
/*      */ 
/*      */   public static int truncatedCompareTo(Date date1, Date date2, int field)
/*      */   {
/* 1739 */     Date truncatedDate1 = truncate(date1, field);
/* 1740 */     Date truncatedDate2 = truncate(date2, field);
/* 1741 */     return truncatedDate1.compareTo(truncatedDate2);
/*      */   }
/*      */ 
/*      */   private static long getMillisPerUnit(int unit)
/*      */   {
/* 1754 */     long result = 9223372036854775807L;
/* 1755 */     switch (unit) {
/*      */     case 5:
/*      */     case 6:
/* 1758 */       result = 86400000L;
/* 1759 */       break;
/*      */     case 11:
/* 1761 */       result = 3600000L;
/* 1762 */       break;
/*      */     case 12:
/* 1764 */       result = 60000L;
/* 1765 */       break;
/*      */     case 13:
/* 1767 */       result = 1000L;
/* 1768 */       break;
/*      */     case 14:
/* 1770 */       result = 1L;
/* 1771 */       break;
/*      */     case 7:
/*      */     case 8:
/*      */     case 9:
/*      */     case 10:
/*      */     default:
/* 1772 */       throw new IllegalArgumentException("The unit " + unit + " cannot be represented is milleseconds");
/*      */     }
/* 1774 */     return result;
/*      */   }
/*      */ 
/*      */   static class DateIterator
/*      */     implements Iterator<Calendar>
/*      */   {
/*      */     private final Calendar endFinal;
/*      */     private final Calendar spot;
/*      */ 
/*      */     DateIterator(Calendar startFinal, Calendar endFinal)
/*      */     {
/* 1793 */       this.endFinal = endFinal;
/* 1794 */       this.spot = startFinal;
/* 1795 */       this.spot.add(5, -1);
/*      */     }
/*      */ 
/*      */     public boolean hasNext()
/*      */     {
/* 1804 */       return this.spot.before(this.endFinal);
/*      */     }
/*      */ 
/*      */     public Calendar next()
/*      */     {
/* 1813 */       if (this.spot.equals(this.endFinal)) {
/* 1814 */         throw new NoSuchElementException();
/*      */       }
/* 1816 */       this.spot.add(5, 1);
/* 1817 */       return (Calendar)this.spot.clone();
/*      */     }
/*      */ 
/*      */     public void remove()
/*      */     {
/* 1827 */       throw new UnsupportedOperationException();
/*      */     }
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.time.DateUtils
 * JD-Core Version:    0.6.2
 */