/*   1:    */package org.apache.commons.lang3;
/*   2:    */
/*   3:    */import java.nio.charset.Charset;
/*   4:    */import java.nio.charset.IllegalCharsetNameException;
/*   5:    */
/*  84:    */public class CharEncoding
/*  85:    */{
/*  86:    */  public static final String ISO_8859_1 = "ISO-8859-1";
/*  87:    */  public static final String US_ASCII = "US-ASCII";
/*  88:    */  public static final String UTF_16 = "UTF-16";
/*  89:    */  public static final String UTF_16BE = "UTF-16BE";
/*  90:    */  public static final String UTF_16LE = "UTF-16LE";
/*  91:    */  public static final String UTF_8 = "UTF-8";
/*  92:    */  
/*  93:    */  public static boolean isSupported(String name)
/*  94:    */  {
/*  95: 95 */    if (name == null) {
/*  96: 96 */      return false;
/*  97:    */    }
/*  98:    */    try {
/*  99: 99 */      return Charset.isSupported(name);
/* 100:    */    } catch (IllegalCharsetNameException ex) {}
/* 101:101 */    return false;
/* 102:    */  }
/* 103:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.CharEncoding
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */