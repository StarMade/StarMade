/*     */ package org.jaxen.saxpath.helpers;
/*     */ 
/*     */ import org.jaxen.saxpath.SAXPathException;
/*     */ import org.jaxen.saxpath.XPathReader;
/*     */ 
/*     */ public class XPathReaderFactory
/*     */ {
/*     */   public static final String DRIVER_PROPERTY = "org.saxpath.driver";
/*     */   protected static final String DEFAULT_DRIVER = "org.jaxen.saxpath.base.XPathReader";
/*     */ 
/*     */   public static XPathReader createReader()
/*     */     throws SAXPathException
/*     */   {
/*  92 */     String className = null;
/*     */     try
/*     */     {
/*  96 */       className = System.getProperty("org.saxpath.driver");
/*     */     }
/*     */     catch (SecurityException e)
/*     */     {
/*     */     }
/*     */ 
/* 103 */     if ((className == null) || (className.length() == 0))
/*     */     {
/* 107 */       className = "org.jaxen.saxpath.base.XPathReader";
/*     */     }
/*     */ 
/* 110 */     return createReader(className);
/*     */   }
/*     */ 
/*     */   public static XPathReader createReader(String className)
/*     */     throws SAXPathException
/*     */   {
/* 128 */     Class readerClass = null;
/* 129 */     XPathReader reader = null;
/*     */     try
/*     */     {
/* 137 */       readerClass = Class.forName(className, true, XPathReaderFactory.class.getClassLoader());
/*     */ 
/* 144 */       if (!XPathReader.class.isAssignableFrom(readerClass))
/*     */       {
/* 146 */         throw new SAXPathException("Class [" + className + "] does not implement the org.jaxen.saxpath.XPathReader interface.");
/*     */       }
/*     */ 
/*     */     }
/*     */     catch (ClassNotFoundException e)
/*     */     {
/* 152 */       throw new SAXPathException(e);
/*     */     }
/*     */ 
/*     */     try
/*     */     {
/* 157 */       reader = (XPathReader)readerClass.newInstance();
/*     */     }
/*     */     catch (IllegalAccessException e)
/*     */     {
/* 161 */       throw new SAXPathException(e);
/*     */     }
/*     */     catch (InstantiationException e)
/*     */     {
/* 165 */       throw new SAXPathException(e);
/*     */     }
/*     */ 
/* 168 */     return reader;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.saxpath.helpers.XPathReaderFactory
 * JD-Core Version:    0.6.2
 */