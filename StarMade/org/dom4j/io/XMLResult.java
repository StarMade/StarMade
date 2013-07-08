package org.dom4j.io;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import javax.xml.transform.sax.SAXResult;
import org.xml.sax.ContentHandler;
import org.xml.sax.ext.LexicalHandler;

public class XMLResult
  extends SAXResult
{
  private XMLWriter xmlWriter;
  
  public XMLResult()
  {
    this(new XMLWriter());
  }
  
  public XMLResult(Writer writer)
  {
    this(new XMLWriter(writer));
  }
  
  public XMLResult(Writer writer, OutputFormat format)
  {
    this(new XMLWriter(writer, format));
  }
  
  public XMLResult(OutputStream out)
    throws UnsupportedEncodingException
  {
    this(new XMLWriter(out));
  }
  
  public XMLResult(OutputStream out, OutputFormat format)
    throws UnsupportedEncodingException
  {
    this(new XMLWriter(out, format));
  }
  
  public XMLResult(XMLWriter xmlWriter)
  {
    super(xmlWriter);
    this.xmlWriter = xmlWriter;
    setLexicalHandler(xmlWriter);
  }
  
  public XMLWriter getXMLWriter()
  {
    return this.xmlWriter;
  }
  
  public void setXMLWriter(XMLWriter writer)
  {
    this.xmlWriter = writer;
    setHandler(this.xmlWriter);
    setLexicalHandler(this.xmlWriter);
  }
  
  public ContentHandler getHandler()
  {
    return this.xmlWriter;
  }
  
  public LexicalHandler getLexicalHandler()
  {
    return this.xmlWriter;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.io.XMLResult
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */