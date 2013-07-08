/*   1:    */package org.apache.commons.lang3.text;
/*   2:    */
/*   3:    */import java.util.Formattable;
/*   4:    */import java.util.Formatter;
/*   5:    */import org.apache.commons.lang3.ObjectUtils;
/*   6:    */import org.apache.commons.lang3.Validate;
/*   7:    */
/*  60:    */public class FormattableUtils
/*  61:    */{
/*  62:    */  private static final String SIMPLEST_FORMAT = "%s";
/*  63:    */  
/*  64:    */  public static String toString(Formattable formattable)
/*  65:    */  {
/*  66: 66 */    return String.format("%s", new Object[] { formattable });
/*  67:    */  }
/*  68:    */  
/*  81:    */  public static Formatter append(CharSequence seq, Formatter formatter, int flags, int width, int precision)
/*  82:    */  {
/*  83: 83 */    return append(seq, formatter, flags, width, precision, ' ', null);
/*  84:    */  }
/*  85:    */  
/*  98:    */  public static Formatter append(CharSequence seq, Formatter formatter, int flags, int width, int precision, char padChar)
/*  99:    */  {
/* 100:100 */    return append(seq, formatter, flags, width, precision, padChar, null);
/* 101:    */  }
/* 102:    */  
/* 116:    */  public static Formatter append(CharSequence seq, Formatter formatter, int flags, int width, int precision, CharSequence ellipsis)
/* 117:    */  {
/* 118:118 */    return append(seq, formatter, flags, width, precision, ' ', ellipsis);
/* 119:    */  }
/* 120:    */  
/* 134:    */  public static Formatter append(CharSequence seq, Formatter formatter, int flags, int width, int precision, char padChar, CharSequence ellipsis)
/* 135:    */  {
/* 136:136 */    Validate.isTrue((ellipsis == null) || (precision < 0) || (ellipsis.length() <= precision), "Specified ellipsis '%1$s' exceeds precision of %2$s", new Object[] { ellipsis, Integer.valueOf(precision) });
/* 137:    */    
/* 138:138 */    StringBuilder buf = new StringBuilder(seq);
/* 139:139 */    if ((precision >= 0) && (precision < seq.length())) {
/* 140:140 */      CharSequence _ellipsis = (CharSequence)ObjectUtils.defaultIfNull(ellipsis, "");
/* 141:141 */      buf.replace(precision - _ellipsis.length(), seq.length(), _ellipsis.toString());
/* 142:    */    }
/* 143:143 */    boolean leftJustify = (flags & 0x1) == 1;
/* 144:144 */    for (int i = buf.length(); i < width; i++) {
/* 145:145 */      buf.insert(leftJustify ? i : 0, padChar);
/* 146:    */    }
/* 147:147 */    formatter.format(buf.toString(), new Object[0]);
/* 148:148 */    return formatter;
/* 149:    */  }
/* 150:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.text.FormattableUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */