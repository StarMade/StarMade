/*     */ package org.dom4j.io;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.SAXNotRecognizedException;
/*     */ import org.xml.sax.SAXNotSupportedException;
/*     */ import org.xml.sax.XMLReader;
/*     */ import org.xml.sax.helpers.XMLReaderFactory;
/*     */ 
/*     */ class SAXHelper
/*     */ {
/*  26 */   private static boolean loggedWarning = true;
/*     */ 
/*     */   public static boolean setParserProperty(XMLReader reader, String propertyName, Object value)
/*     */   {
/*     */     try
/*     */     {
/*  34 */       reader.setProperty(propertyName, value);
/*     */ 
/*  36 */       return true;
/*     */     }
/*     */     catch (SAXNotSupportedException e)
/*     */     {
/*     */     }
/*     */     catch (SAXNotRecognizedException e) {
/*     */     }
/*  43 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean setParserFeature(XMLReader reader, String featureName, boolean value)
/*     */   {
/*     */     try {
/*  49 */       reader.setFeature(featureName, value);
/*     */ 
/*  51 */       return true;
/*     */     }
/*     */     catch (SAXNotSupportedException e)
/*     */     {
/*     */     }
/*     */     catch (SAXNotRecognizedException e) {
/*     */     }
/*  58 */     return false;
/*     */   }
/*     */ 
/*     */   public static XMLReader createXMLReader(boolean validating)
/*     */     throws SAXException
/*     */   {
/*  75 */     XMLReader reader = null;
/*     */ 
/*  77 */     if (reader == null) {
/*  78 */       reader = createXMLReaderViaJAXP(validating, true);
/*     */     }
/*     */ 
/*  81 */     if (reader == null) {
/*     */       try {
/*  83 */         reader = XMLReaderFactory.createXMLReader();
/*     */       } catch (Exception e) {
/*  85 */         if (isVerboseErrorReporting())
/*     */         {
/*  88 */           System.out.println("Warning: Caught exception attempting to use SAX to load a SAX XMLReader ");
/*     */ 
/*  90 */           System.out.println("Warning: Exception was: " + e);
/*  91 */           System.out.println("Warning: I will print the stack trace then carry on using the default SAX parser");
/*     */ 
/*  95 */           e.printStackTrace();
/*     */         }
/*     */ 
/*  98 */         throw new SAXException(e);
/*     */       }
/*     */     }
/*     */ 
/* 102 */     if (reader == null) {
/* 103 */       throw new SAXException("Couldn't create SAX reader");
/*     */     }
/*     */ 
/* 106 */     return reader;
/*     */   }
/*     */ 
/*     */   protected static XMLReader createXMLReaderViaJAXP(boolean validating, boolean namespaceAware)
/*     */   {
/*     */     try
/*     */     {
/* 125 */       return JAXPHelper.createXMLReader(validating, namespaceAware);
/*     */     } catch (Throwable e) {
/* 127 */       if (!loggedWarning) {
/* 128 */         loggedWarning = true;
/*     */ 
/* 130 */         if (isVerboseErrorReporting())
/*     */         {
/* 133 */           System.out.println("Warning: Caught exception attempting to use JAXP to load a SAX XMLReader");
/*     */ 
/* 135 */           System.out.println("Warning: Exception was: " + e);
/* 136 */           e.printStackTrace();
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 141 */     return null;
/*     */   }
/*     */ 
/*     */   protected static boolean isVerboseErrorReporting() {
/*     */     try {
/* 146 */       String flag = System.getProperty("org.dom4j.verbose");
/*     */ 
/* 148 */       if ((flag != null) && (flag.equalsIgnoreCase("true"))) {
/* 149 */         return true;
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*     */     }
/*     */ 
/* 156 */     return true;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.io.SAXHelper
 * JD-Core Version:    0.6.2
 */