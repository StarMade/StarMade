/*   1:    */package org.dom4j.io;
/*   2:    */
/*   3:    */import java.io.PrintStream;
/*   4:    */import org.xml.sax.SAXException;
/*   5:    */import org.xml.sax.SAXNotRecognizedException;
/*   6:    */import org.xml.sax.SAXNotSupportedException;
/*   7:    */import org.xml.sax.XMLReader;
/*   8:    */import org.xml.sax.helpers.XMLReaderFactory;
/*   9:    */
/*  24:    */class SAXHelper
/*  25:    */{
/*  26: 26 */  private static boolean loggedWarning = true;
/*  27:    */  
/*  30:    */  public static boolean setParserProperty(XMLReader reader, String propertyName, Object value)
/*  31:    */  {
/*  32:    */    try
/*  33:    */    {
/*  34: 34 */      reader.setProperty(propertyName, value);
/*  35:    */      
/*  36: 36 */      return true;
/*  37:    */    }
/*  38:    */    catch (SAXNotSupportedException e) {}catch (SAXNotRecognizedException e) {}
/*  39:    */    
/*  43: 43 */    return false;
/*  44:    */  }
/*  45:    */  
/*  46:    */  public static boolean setParserFeature(XMLReader reader, String featureName, boolean value)
/*  47:    */  {
/*  48:    */    try {
/*  49: 49 */      reader.setFeature(featureName, value);
/*  50:    */      
/*  51: 51 */      return true;
/*  52:    */    }
/*  53:    */    catch (SAXNotSupportedException e) {}catch (SAXNotRecognizedException e) {}
/*  54:    */    
/*  58: 58 */    return false;
/*  59:    */  }
/*  60:    */  
/*  72:    */  public static XMLReader createXMLReader(boolean validating)
/*  73:    */    throws SAXException
/*  74:    */  {
/*  75: 75 */    XMLReader reader = null;
/*  76:    */    
/*  77: 77 */    if (reader == null) {
/*  78: 78 */      reader = createXMLReaderViaJAXP(validating, true);
/*  79:    */    }
/*  80:    */    
/*  81: 81 */    if (reader == null) {
/*  82:    */      try {
/*  83: 83 */        reader = XMLReaderFactory.createXMLReader();
/*  84:    */      } catch (Exception e) {
/*  85: 85 */        if (isVerboseErrorReporting())
/*  86:    */        {
/*  88: 88 */          System.out.println("Warning: Caught exception attempting to use SAX to load a SAX XMLReader ");
/*  89:    */          
/*  90: 90 */          System.out.println("Warning: Exception was: " + e);
/*  91: 91 */          System.out.println("Warning: I will print the stack trace then carry on using the default SAX parser");
/*  92:    */          
/*  95: 95 */          e.printStackTrace();
/*  96:    */        }
/*  97:    */        
/*  98: 98 */        throw new SAXException(e);
/*  99:    */      }
/* 100:    */    }
/* 101:    */    
/* 102:102 */    if (reader == null) {
/* 103:103 */      throw new SAXException("Couldn't create SAX reader");
/* 104:    */    }
/* 105:    */    
/* 106:106 */    return reader;
/* 107:    */  }
/* 108:    */  
/* 121:    */  protected static XMLReader createXMLReaderViaJAXP(boolean validating, boolean namespaceAware)
/* 122:    */  {
/* 123:    */    try
/* 124:    */    {
/* 125:125 */      return JAXPHelper.createXMLReader(validating, namespaceAware);
/* 126:    */    } catch (Throwable e) {
/* 127:127 */      if (!loggedWarning) {
/* 128:128 */        loggedWarning = true;
/* 129:    */        
/* 130:130 */        if (isVerboseErrorReporting())
/* 131:    */        {
/* 133:133 */          System.out.println("Warning: Caught exception attempting to use JAXP to load a SAX XMLReader");
/* 134:    */          
/* 135:135 */          System.out.println("Warning: Exception was: " + e);
/* 136:136 */          e.printStackTrace();
/* 137:    */        }
/* 138:    */      }
/* 139:    */    }
/* 140:    */    
/* 141:141 */    return null;
/* 142:    */  }
/* 143:    */  
/* 144:    */  protected static boolean isVerboseErrorReporting() {
/* 145:    */    try {
/* 146:146 */      String flag = System.getProperty("org.dom4j.verbose");
/* 147:    */      
/* 148:148 */      if ((flag != null) && (flag.equalsIgnoreCase("true"))) {
/* 149:149 */        return true;
/* 150:    */      }
/* 151:    */    }
/* 152:    */    catch (Exception e) {}
/* 153:    */    
/* 156:156 */    return true;
/* 157:    */  }
/* 158:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.io.SAXHelper
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */