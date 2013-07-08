package org.jaxen.saxpath.helpers;

import org.jaxen.saxpath.SAXPathException;
import org.jaxen.saxpath.XPathReader;

public class XPathReaderFactory
{
  public static final String DRIVER_PROPERTY = "org.saxpath.driver";
  protected static final String DEFAULT_DRIVER = "org.jaxen.saxpath.base.XPathReader";
  
  public static XPathReader createReader()
    throws SAXPathException
  {
    String className = null;
    try
    {
      className = System.getProperty("org.saxpath.driver");
    }
    catch (SecurityException local_e) {}
    if ((className == null) || (className.length() == 0)) {
      className = "org.jaxen.saxpath.base.XPathReader";
    }
    return createReader(className);
  }
  
  public static XPathReader createReader(String className)
    throws SAXPathException
  {
    Class readerClass = null;
    XPathReader reader = null;
    try
    {
      readerClass = Class.forName(className, true, XPathReaderFactory.class.getClassLoader());
      if (!XPathReader.class.isAssignableFrom(readerClass)) {
        throw new SAXPathException("Class [" + className + "] does not implement the org.jaxen.saxpath.XPathReader interface.");
      }
    }
    catch (ClassNotFoundException local_e)
    {
      throw new SAXPathException(local_e);
    }
    try
    {
      reader = (XPathReader)readerClass.newInstance();
    }
    catch (IllegalAccessException local_e)
    {
      throw new SAXPathException(local_e);
    }
    catch (InstantiationException local_e)
    {
      throw new SAXPathException(local_e);
    }
    return reader;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.saxpath.helpers.XPathReaderFactory
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */