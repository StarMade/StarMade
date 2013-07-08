/*   1:    */package org.jaxen.saxpath.helpers;
/*   2:    */
/*   3:    */import org.jaxen.saxpath.SAXPathException;
/*   4:    */import org.jaxen.saxpath.XPathReader;
/*   5:    */
/*  84:    */public class XPathReaderFactory
/*  85:    */{
/*  86:    */  public static final String DRIVER_PROPERTY = "org.saxpath.driver";
/*  87:    */  protected static final String DEFAULT_DRIVER = "org.jaxen.saxpath.base.XPathReader";
/*  88:    */  
/*  89:    */  public static XPathReader createReader()
/*  90:    */    throws SAXPathException
/*  91:    */  {
/*  92: 92 */    String className = null;
/*  93:    */    
/*  94:    */    try
/*  95:    */    {
/*  96: 96 */      className = System.getProperty("org.saxpath.driver");
/*  97:    */    }
/*  98:    */    catch (SecurityException e) {}
/*  99:    */    
/* 103:103 */    if ((className == null) || (className.length() == 0))
/* 104:    */    {
/* 107:107 */      className = "org.jaxen.saxpath.base.XPathReader";
/* 108:    */    }
/* 109:    */    
/* 110:110 */    return createReader(className);
/* 111:    */  }
/* 112:    */  
/* 125:    */  public static XPathReader createReader(String className)
/* 126:    */    throws SAXPathException
/* 127:    */  {
/* 128:128 */    Class readerClass = null;
/* 129:129 */    XPathReader reader = null;
/* 130:    */    
/* 135:    */    try
/* 136:    */    {
/* 137:137 */      readerClass = Class.forName(className, true, XPathReaderFactory.class.getClassLoader());
/* 138:    */      
/* 144:144 */      if (!XPathReader.class.isAssignableFrom(readerClass))
/* 145:    */      {
/* 146:146 */        throw new SAXPathException("Class [" + className + "] does not implement the org.jaxen.saxpath.XPathReader interface.");
/* 147:    */      }
/* 148:    */      
/* 149:    */    }
/* 150:    */    catch (ClassNotFoundException e)
/* 151:    */    {
/* 152:152 */      throw new SAXPathException(e);
/* 153:    */    }
/* 154:    */    
/* 155:    */    try
/* 156:    */    {
/* 157:157 */      reader = (XPathReader)readerClass.newInstance();
/* 158:    */    }
/* 159:    */    catch (IllegalAccessException e)
/* 160:    */    {
/* 161:161 */      throw new SAXPathException(e);
/* 162:    */    }
/* 163:    */    catch (InstantiationException e)
/* 164:    */    {
/* 165:165 */      throw new SAXPathException(e);
/* 166:    */    }
/* 167:    */    
/* 168:168 */    return reader;
/* 169:    */  }
/* 170:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.saxpath.helpers.XPathReaderFactory
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */