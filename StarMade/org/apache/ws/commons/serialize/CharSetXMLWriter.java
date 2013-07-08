/*  1:   */package org.apache.ws.commons.serialize;
/*  2:   */
/*  3:   */import java.nio.charset.Charset;
/*  4:   */import java.nio.charset.CharsetEncoder;
/*  5:   */import org.xml.sax.SAXException;
/*  6:   */
/* 24:   */public class CharSetXMLWriter
/* 25:   */  extends XMLWriterImpl
/* 26:   */{
/* 27:   */  private CharsetEncoder charsetEncoder;
/* 28:   */  
/* 29:   */  public void startDocument()
/* 30:   */    throws SAXException
/* 31:   */  {
/* 32:32 */    String enc = getEncoding();
/* 33:33 */    if (enc == null) {
/* 34:34 */      enc = "UTF-8";
/* 35:   */    }
/* 36:36 */    Charset charSet = Charset.forName(enc);
/* 37:37 */    if (charSet.canEncode()) {
/* 38:38 */      this.charsetEncoder = charSet.newEncoder();
/* 39:   */    }
/* 40:40 */    super.startDocument();
/* 41:   */  }
/* 42:   */  
/* 43:   */  public boolean canEncode(char c) {
/* 44:44 */    return this.charsetEncoder == null ? super.canEncode(c) : this.charsetEncoder.canEncode(c);
/* 45:   */  }
/* 46:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.ws.commons.serialize.CharSetXMLWriter
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */