/*   1:    */package org.apache.commons.lang3;
/*   2:    */
/*  61:    */public class CharSetUtils
/*  62:    */{
/*  63:    */  public static String squeeze(String str, String... set)
/*  64:    */  {
/*  65: 65 */    if ((StringUtils.isEmpty(str)) || (deepEmpty(set))) {
/*  66: 66 */      return str;
/*  67:    */    }
/*  68: 68 */    CharSet chars = CharSet.getInstance(set);
/*  69: 69 */    StringBuilder buffer = new StringBuilder(str.length());
/*  70: 70 */    char[] chrs = str.toCharArray();
/*  71: 71 */    int sz = chrs.length;
/*  72: 72 */    char lastChar = ' ';
/*  73: 73 */    char ch = ' ';
/*  74: 74 */    for (int i = 0; i < sz; i++) {
/*  75: 75 */      ch = chrs[i];
/*  76:    */      
/*  77: 77 */      if ((ch != lastChar) || (i == 0) || (!chars.contains(ch)))
/*  78:    */      {
/*  80: 80 */        buffer.append(ch);
/*  81: 81 */        lastChar = ch;
/*  82:    */      } }
/*  83: 83 */    return buffer.toString();
/*  84:    */  }
/*  85:    */  
/* 105:    */  public static int count(String str, String... set)
/* 106:    */  {
/* 107:107 */    if ((StringUtils.isEmpty(str)) || (deepEmpty(set))) {
/* 108:108 */      return 0;
/* 109:    */    }
/* 110:110 */    CharSet chars = CharSet.getInstance(set);
/* 111:111 */    int count = 0;
/* 112:112 */    for (char c : str.toCharArray()) {
/* 113:113 */      if (chars.contains(c)) {
/* 114:114 */        count++;
/* 115:    */      }
/* 116:    */    }
/* 117:117 */    return count;
/* 118:    */  }
/* 119:    */  
/* 140:    */  public static String keep(String str, String... set)
/* 141:    */  {
/* 142:142 */    if (str == null) {
/* 143:143 */      return null;
/* 144:    */    }
/* 145:145 */    if ((str.length() == 0) || (deepEmpty(set))) {
/* 146:146 */      return "";
/* 147:    */    }
/* 148:148 */    return modify(str, set, true);
/* 149:    */  }
/* 150:    */  
/* 170:    */  public static String delete(String str, String... set)
/* 171:    */  {
/* 172:172 */    if ((StringUtils.isEmpty(str)) || (deepEmpty(set))) {
/* 173:173 */      return str;
/* 174:    */    }
/* 175:175 */    return modify(str, set, false);
/* 176:    */  }
/* 177:    */  
/* 186:    */  private static String modify(String str, String[] set, boolean expect)
/* 187:    */  {
/* 188:188 */    CharSet chars = CharSet.getInstance(set);
/* 189:189 */    StringBuilder buffer = new StringBuilder(str.length());
/* 190:190 */    char[] chrs = str.toCharArray();
/* 191:191 */    int sz = chrs.length;
/* 192:192 */    for (int i = 0; i < sz; i++) {
/* 193:193 */      if (chars.contains(chrs[i]) == expect) {
/* 194:194 */        buffer.append(chrs[i]);
/* 195:    */      }
/* 196:    */    }
/* 197:197 */    return buffer.toString();
/* 198:    */  }
/* 199:    */  
/* 206:    */  private static boolean deepEmpty(String[] strings)
/* 207:    */  {
/* 208:208 */    if (strings != null) {
/* 209:209 */      for (String s : strings) {
/* 210:210 */        if (StringUtils.isNotEmpty(s)) {
/* 211:211 */          return false;
/* 212:    */        }
/* 213:    */      }
/* 214:    */    }
/* 215:215 */    return true;
/* 216:    */  }
/* 217:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.CharSetUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */