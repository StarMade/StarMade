package org.dom4j.io;

import java.io.IOException;
import org.dom4j.Document;
import org.xml.sax.ContentHandler;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class SAXValidator
{
  private XMLReader xmlReader;
  private ErrorHandler errorHandler;
  
  public SAXValidator() {}
  
  public SAXValidator(XMLReader xmlReader)
  {
    this.xmlReader = xmlReader;
  }
  
  public void validate(Document document)
    throws SAXException
  {
    if (document != null)
    {
      XMLReader reader = getXMLReader();
      if (this.errorHandler != null) {
        reader.setErrorHandler(this.errorHandler);
      }
      try
      {
        reader.parse(new DocumentInputSource(document));
      }
      catch (IOException local_e)
      {
        throw new RuntimeException("Caught and exception that should never happen: " + local_e);
      }
    }
  }
  
  public XMLReader getXMLReader()
    throws SAXException
  {
    if (this.xmlReader == null)
    {
      this.xmlReader = createXMLReader();
      configureReader();
    }
    return this.xmlReader;
  }
  
  public void setXMLReader(XMLReader reader)
    throws SAXException
  {
    this.xmlReader = reader;
    configureReader();
  }
  
  public ErrorHandler getErrorHandler()
  {
    return this.errorHandler;
  }
  
  public void setErrorHandler(ErrorHandler errorHandler)
  {
    this.errorHandler = errorHandler;
  }
  
  protected XMLReader createXMLReader()
    throws SAXException
  {
    return SAXHelper.createXMLReader(true);
  }
  
  protected void configureReader()
    throws SAXException
  {
    ContentHandler handler = this.xmlReader.getContentHandler();
    if (handler == null) {
      this.xmlReader.setContentHandler(new DefaultHandler());
    }
    this.xmlReader.setFeature("http://xml.org/sax/features/validation", true);
    this.xmlReader.setFeature("http://xml.org/sax/features/namespaces", true);
    this.xmlReader.setFeature("http://xml.org/sax/features/namespace-prefixes", false);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.io.SAXValidator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */