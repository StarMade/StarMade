package org.dom4j.io;

import java.io.PrintStream;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

class SAXHelper
{
  private static boolean loggedWarning = true;
  
  public static boolean setParserProperty(XMLReader reader, String propertyName, Object value)
  {
    try
    {
      reader.setProperty(propertyName, value);
      return true;
    }
    catch (SAXNotSupportedException local_e) {}catch (SAXNotRecognizedException local_e) {}
    return false;
  }
  
  public static boolean setParserFeature(XMLReader reader, String featureName, boolean value)
  {
    try
    {
      reader.setFeature(featureName, value);
      return true;
    }
    catch (SAXNotSupportedException local_e) {}catch (SAXNotRecognizedException local_e) {}
    return false;
  }
  
  public static XMLReader createXMLReader(boolean validating)
    throws SAXException
  {
    XMLReader reader = null;
    if (reader == null) {
      reader = createXMLReaderViaJAXP(validating, true);
    }
    if (reader == null) {
      try
      {
        reader = XMLReaderFactory.createXMLReader();
      }
      catch (Exception local_e)
      {
        if (isVerboseErrorReporting())
        {
          System.out.println("Warning: Caught exception attempting to use SAX to load a SAX XMLReader ");
          System.out.println("Warning: Exception was: " + local_e);
          System.out.println("Warning: I will print the stack trace then carry on using the default SAX parser");
          local_e.printStackTrace();
        }
        throw new SAXException(local_e);
      }
    }
    if (reader == null) {
      throw new SAXException("Couldn't create SAX reader");
    }
    return reader;
  }
  
  protected static XMLReader createXMLReaderViaJAXP(boolean validating, boolean namespaceAware)
  {
    try
    {
      return JAXPHelper.createXMLReader(validating, namespaceAware);
    }
    catch (Throwable local_e)
    {
      if (!loggedWarning)
      {
        loggedWarning = true;
        if (isVerboseErrorReporting())
        {
          System.out.println("Warning: Caught exception attempting to use JAXP to load a SAX XMLReader");
          System.out.println("Warning: Exception was: " + local_e);
          local_e.printStackTrace();
        }
      }
    }
    return null;
  }
  
  protected static boolean isVerboseErrorReporting()
  {
    try
    {
      String flag = System.getProperty("org.dom4j.verbose");
      if ((flag != null) && (flag.equalsIgnoreCase("true"))) {
        return true;
      }
    }
    catch (Exception flag) {}
    return true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.io.SAXHelper
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */