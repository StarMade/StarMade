/*   1:    */package org.apache.commons.lang3.text;
/*   2:    */
/*   3:    */import java.util.Arrays;
/*   4:    */import org.apache.commons.lang3.StringUtils;
/*   5:    */
/*  36:    */public abstract class StrMatcher
/*  37:    */{
/*  38: 38 */  private static final StrMatcher COMMA_MATCHER = new CharMatcher(',');
/*  39:    */  
/*  42: 42 */  private static final StrMatcher TAB_MATCHER = new CharMatcher('\t');
/*  43:    */  
/*  46: 46 */  private static final StrMatcher SPACE_MATCHER = new CharMatcher(' ');
/*  47:    */  
/*  51: 51 */  private static final StrMatcher SPLIT_MATCHER = new CharSetMatcher(" \t\n\r\f".toCharArray());
/*  52:    */  
/*  55: 55 */  private static final StrMatcher TRIM_MATCHER = new TrimMatcher();
/*  56:    */  
/*  59: 59 */  private static final StrMatcher SINGLE_QUOTE_MATCHER = new CharMatcher('\'');
/*  60:    */  
/*  63: 63 */  private static final StrMatcher DOUBLE_QUOTE_MATCHER = new CharMatcher('"');
/*  64:    */  
/*  67: 67 */  private static final StrMatcher QUOTE_MATCHER = new CharSetMatcher("'\"".toCharArray());
/*  68:    */  
/*  71: 71 */  private static final StrMatcher NONE_MATCHER = new NoMatcher();
/*  72:    */  
/*  79:    */  public static StrMatcher commaMatcher()
/*  80:    */  {
/*  81: 81 */    return COMMA_MATCHER;
/*  82:    */  }
/*  83:    */  
/*  88:    */  public static StrMatcher tabMatcher()
/*  89:    */  {
/*  90: 90 */    return TAB_MATCHER;
/*  91:    */  }
/*  92:    */  
/*  97:    */  public static StrMatcher spaceMatcher()
/*  98:    */  {
/*  99: 99 */    return SPACE_MATCHER;
/* 100:    */  }
/* 101:    */  
/* 107:    */  public static StrMatcher splitMatcher()
/* 108:    */  {
/* 109:109 */    return SPLIT_MATCHER;
/* 110:    */  }
/* 111:    */  
/* 116:    */  public static StrMatcher trimMatcher()
/* 117:    */  {
/* 118:118 */    return TRIM_MATCHER;
/* 119:    */  }
/* 120:    */  
/* 125:    */  public static StrMatcher singleQuoteMatcher()
/* 126:    */  {
/* 127:127 */    return SINGLE_QUOTE_MATCHER;
/* 128:    */  }
/* 129:    */  
/* 134:    */  public static StrMatcher doubleQuoteMatcher()
/* 135:    */  {
/* 136:136 */    return DOUBLE_QUOTE_MATCHER;
/* 137:    */  }
/* 138:    */  
/* 143:    */  public static StrMatcher quoteMatcher()
/* 144:    */  {
/* 145:145 */    return QUOTE_MATCHER;
/* 146:    */  }
/* 147:    */  
/* 152:    */  public static StrMatcher noneMatcher()
/* 153:    */  {
/* 154:154 */    return NONE_MATCHER;
/* 155:    */  }
/* 156:    */  
/* 162:    */  public static StrMatcher charMatcher(char ch)
/* 163:    */  {
/* 164:164 */    return new CharMatcher(ch);
/* 165:    */  }
/* 166:    */  
/* 172:    */  public static StrMatcher charSetMatcher(char... chars)
/* 173:    */  {
/* 174:174 */    if ((chars == null) || (chars.length == 0)) {
/* 175:175 */      return NONE_MATCHER;
/* 176:    */    }
/* 177:177 */    if (chars.length == 1) {
/* 178:178 */      return new CharMatcher(chars[0]);
/* 179:    */    }
/* 180:180 */    return new CharSetMatcher(chars);
/* 181:    */  }
/* 182:    */  
/* 188:    */  public static StrMatcher charSetMatcher(String chars)
/* 189:    */  {
/* 190:190 */    if ((chars == null) || (chars.length() == 0)) {
/* 191:191 */      return NONE_MATCHER;
/* 192:    */    }
/* 193:193 */    if (chars.length() == 1) {
/* 194:194 */      return new CharMatcher(chars.charAt(0));
/* 195:    */    }
/* 196:196 */    return new CharSetMatcher(chars.toCharArray());
/* 197:    */  }
/* 198:    */  
/* 204:    */  public static StrMatcher stringMatcher(String str)
/* 205:    */  {
/* 206:206 */    if (StringUtils.isEmpty(str)) {
/* 207:207 */      return NONE_MATCHER;
/* 208:    */    }
/* 209:209 */    return new StringMatcher(str);
/* 210:    */  }
/* 211:    */  
/* 238:    */  public abstract int isMatch(char[] paramArrayOfChar, int paramInt1, int paramInt2, int paramInt3);
/* 239:    */  
/* 266:    */  public int isMatch(char[] buffer, int pos)
/* 267:    */  {
/* 268:268 */    return isMatch(buffer, pos, 0, buffer.length);
/* 269:    */  }
/* 270:    */  
/* 275:    */  static final class CharSetMatcher
/* 276:    */    extends StrMatcher
/* 277:    */  {
/* 278:    */    private final char[] chars;
/* 279:    */    
/* 284:    */    CharSetMatcher(char[] chars)
/* 285:    */    {
/* 286:286 */      this.chars = ((char[])chars.clone());
/* 287:287 */      Arrays.sort(this.chars);
/* 288:    */    }
/* 289:    */    
/* 299:    */    public int isMatch(char[] buffer, int pos, int bufferStart, int bufferEnd)
/* 300:    */    {
/* 301:301 */      return Arrays.binarySearch(this.chars, buffer[pos]) >= 0 ? 1 : 0;
/* 302:    */    }
/* 303:    */  }
/* 304:    */  
/* 309:    */  static final class CharMatcher
/* 310:    */    extends StrMatcher
/* 311:    */  {
/* 312:    */    private final char ch;
/* 313:    */    
/* 318:    */    CharMatcher(char ch)
/* 319:    */    {
/* 320:320 */      this.ch = ch;
/* 321:    */    }
/* 322:    */    
/* 332:    */    public int isMatch(char[] buffer, int pos, int bufferStart, int bufferEnd)
/* 333:    */    {
/* 334:334 */      return this.ch == buffer[pos] ? 1 : 0;
/* 335:    */    }
/* 336:    */  }
/* 337:    */  
/* 342:    */  static final class StringMatcher
/* 343:    */    extends StrMatcher
/* 344:    */  {
/* 345:    */    private final char[] chars;
/* 346:    */    
/* 351:    */    StringMatcher(String str)
/* 352:    */    {
/* 353:353 */      this.chars = str.toCharArray();
/* 354:    */    }
/* 355:    */    
/* 365:    */    public int isMatch(char[] buffer, int pos, int bufferStart, int bufferEnd)
/* 366:    */    {
/* 367:367 */      int len = this.chars.length;
/* 368:368 */      if (pos + len > bufferEnd) {
/* 369:369 */        return 0;
/* 370:    */      }
/* 371:371 */      for (int i = 0; i < this.chars.length; pos++) {
/* 372:372 */        if (this.chars[i] != buffer[pos]) {
/* 373:373 */          return 0;
/* 374:    */        }
/* 375:371 */        i++;
/* 376:    */      }
/* 377:    */      
/* 380:376 */      return len;
/* 381:    */    }
/* 382:    */  }
/* 383:    */  
/* 403:    */  static final class NoMatcher
/* 404:    */    extends StrMatcher
/* 405:    */  {
/* 406:    */    public int isMatch(char[] buffer, int pos, int bufferStart, int bufferEnd)
/* 407:    */    {
/* 408:404 */      return 0;
/* 409:    */    }
/* 410:    */  }
/* 411:    */  
/* 431:    */  static final class TrimMatcher
/* 432:    */    extends StrMatcher
/* 433:    */  {
/* 434:    */    public int isMatch(char[] buffer, int pos, int bufferStart, int bufferEnd)
/* 435:    */    {
/* 436:432 */      return buffer[pos] <= ' ' ? 1 : 0;
/* 437:    */    }
/* 438:    */  }
/* 439:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.text.StrMatcher
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */