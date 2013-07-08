package org.apache.ws.commons.serialize;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import org.xml.sax.SAXException;

public class CharSetXMLWriter
  extends XMLWriterImpl
{
  private CharsetEncoder charsetEncoder;
  
  public void startDocument()
    throws SAXException
  {
    String enc = getEncoding();
    if (enc == null) {
      enc = "UTF-8";
    }
    Charset charSet = Charset.forName(enc);
    if (charSet.canEncode()) {
      this.charsetEncoder = charSet.newEncoder();
    }
    super.startDocument();
  }
  
  public boolean canEncode(char local_c)
  {
    return this.charsetEncoder == null ? super.canEncode(local_c) : this.charsetEncoder.canEncode(local_c);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.apache.ws.commons.serialize.CharSetXMLWriter
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */