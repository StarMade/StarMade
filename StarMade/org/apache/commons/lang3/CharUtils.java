/*   1:    */package org.apache.commons.lang3;
/*   2:    */
/*  30:    */public class CharUtils
/*  31:    */{
/*  32: 32 */  private static final String[] CHAR_STRING_ARRAY = new String[''];
/*  33:    */  
/*  39:    */  public static final char LF = '\n';
/*  40:    */  
/*  45:    */  public static final char CR = '\r';
/*  46:    */  
/*  52:    */  static
/*  53:    */  {
/*  54: 54 */    for (char c = '\000'; c < CHAR_STRING_ARRAY.length; c = (char)(c + '\001')) {
/*  55: 55 */      CHAR_STRING_ARRAY[c] = String.valueOf(c);
/*  56:    */    }
/*  57:    */  }
/*  58:    */  
/*  85:    */  @Deprecated
/*  86:    */  public static Character toCharacterObject(char ch)
/*  87:    */  {
/*  88: 88 */    return Character.valueOf(ch);
/*  89:    */  }
/*  90:    */  
/* 107:    */  public static Character toCharacterObject(String str)
/* 108:    */  {
/* 109:109 */    if (StringUtils.isEmpty(str)) {
/* 110:110 */      return null;
/* 111:    */    }
/* 112:112 */    return Character.valueOf(str.charAt(0));
/* 113:    */  }
/* 114:    */  
/* 128:    */  public static char toChar(Character ch)
/* 129:    */  {
/* 130:130 */    if (ch == null) {
/* 131:131 */      throw new IllegalArgumentException("The Character must not be null");
/* 132:    */    }
/* 133:133 */    return ch.charValue();
/* 134:    */  }
/* 135:    */  
/* 148:    */  public static char toChar(Character ch, char defaultValue)
/* 149:    */  {
/* 150:150 */    if (ch == null) {
/* 151:151 */      return defaultValue;
/* 152:    */    }
/* 153:153 */    return ch.charValue();
/* 154:    */  }
/* 155:    */  
/* 171:    */  public static char toChar(String str)
/* 172:    */  {
/* 173:173 */    if (StringUtils.isEmpty(str)) {
/* 174:174 */      throw new IllegalArgumentException("The String must not be empty");
/* 175:    */    }
/* 176:176 */    return str.charAt(0);
/* 177:    */  }
/* 178:    */  
/* 193:    */  public static char toChar(String str, char defaultValue)
/* 194:    */  {
/* 195:195 */    if (StringUtils.isEmpty(str)) {
/* 196:196 */      return defaultValue;
/* 197:    */    }
/* 198:198 */    return str.charAt(0);
/* 199:    */  }
/* 200:    */  
/* 216:    */  public static int toIntValue(char ch)
/* 217:    */  {
/* 218:218 */    if (!isAsciiNumeric(ch)) {
/* 219:219 */      throw new IllegalArgumentException("The character " + ch + " is not in the range '0' - '9'");
/* 220:    */    }
/* 221:221 */    return ch - '0';
/* 222:    */  }
/* 223:    */  
/* 238:    */  public static int toIntValue(char ch, int defaultValue)
/* 239:    */  {
/* 240:240 */    if (!isAsciiNumeric(ch)) {
/* 241:241 */      return defaultValue;
/* 242:    */    }
/* 243:243 */    return ch - '0';
/* 244:    */  }
/* 245:    */  
/* 261:    */  public static int toIntValue(Character ch)
/* 262:    */  {
/* 263:263 */    if (ch == null) {
/* 264:264 */      throw new IllegalArgumentException("The character must not be null");
/* 265:    */    }
/* 266:266 */    return toIntValue(ch.charValue());
/* 267:    */  }
/* 268:    */  
/* 284:    */  public static int toIntValue(Character ch, int defaultValue)
/* 285:    */  {
/* 286:286 */    if (ch == null) {
/* 287:287 */      return defaultValue;
/* 288:    */    }
/* 289:289 */    return toIntValue(ch.charValue(), defaultValue);
/* 290:    */  }
/* 291:    */  
/* 306:    */  public static String toString(char ch)
/* 307:    */  {
/* 308:308 */    if (ch < '') {
/* 309:309 */      return CHAR_STRING_ARRAY[ch];
/* 310:    */    }
/* 311:311 */    return new String(new char[] { ch });
/* 312:    */  }
/* 313:    */  
/* 330:    */  public static String toString(Character ch)
/* 331:    */  {
/* 332:332 */    if (ch == null) {
/* 333:333 */      return null;
/* 334:    */    }
/* 335:335 */    return toString(ch.charValue());
/* 336:    */  }
/* 337:    */  
/* 351:    */  public static String unicodeEscaped(char ch)
/* 352:    */  {
/* 353:353 */    if (ch < '\020')
/* 354:354 */      return "\\u000" + Integer.toHexString(ch);
/* 355:355 */    if (ch < 'Ā')
/* 356:356 */      return "\\u00" + Integer.toHexString(ch);
/* 357:357 */    if (ch < 'က') {
/* 358:358 */      return "\\u0" + Integer.toHexString(ch);
/* 359:    */    }
/* 360:360 */    return "\\u" + Integer.toHexString(ch);
/* 361:    */  }
/* 362:    */  
/* 378:    */  public static String unicodeEscaped(Character ch)
/* 379:    */  {
/* 380:380 */    if (ch == null) {
/* 381:381 */      return null;
/* 382:    */    }
/* 383:383 */    return unicodeEscaped(ch.charValue());
/* 384:    */  }
/* 385:    */  
/* 401:    */  public static boolean isAscii(char ch)
/* 402:    */  {
/* 403:403 */    return ch < '';
/* 404:    */  }
/* 405:    */  
/* 420:    */  public static boolean isAsciiPrintable(char ch)
/* 421:    */  {
/* 422:422 */    return (ch >= ' ') && (ch < '');
/* 423:    */  }
/* 424:    */  
/* 439:    */  public static boolean isAsciiControl(char ch)
/* 440:    */  {
/* 441:441 */    return (ch < ' ') || (ch == '');
/* 442:    */  }
/* 443:    */  
/* 458:    */  public static boolean isAsciiAlpha(char ch)
/* 459:    */  {
/* 460:460 */    return ((ch >= 'A') && (ch <= 'Z')) || ((ch >= 'a') && (ch <= 'z'));
/* 461:    */  }
/* 462:    */  
/* 477:    */  public static boolean isAsciiAlphaUpper(char ch)
/* 478:    */  {
/* 479:479 */    return (ch >= 'A') && (ch <= 'Z');
/* 480:    */  }
/* 481:    */  
/* 496:    */  public static boolean isAsciiAlphaLower(char ch)
/* 497:    */  {
/* 498:498 */    return (ch >= 'a') && (ch <= 'z');
/* 499:    */  }
/* 500:    */  
/* 515:    */  public static boolean isAsciiNumeric(char ch)
/* 516:    */  {
/* 517:517 */    return (ch >= '0') && (ch <= '9');
/* 518:    */  }
/* 519:    */  
/* 534:    */  public static boolean isAsciiAlphanumeric(char ch)
/* 535:    */  {
/* 536:536 */    return ((ch >= 'A') && (ch <= 'Z')) || ((ch >= 'a') && (ch <= 'z')) || ((ch >= '0') && (ch <= '9'));
/* 537:    */  }
/* 538:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.CharUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */