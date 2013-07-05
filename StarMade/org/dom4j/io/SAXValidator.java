/*     */ package org.dom4j.io;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.dom4j.Document;
/*     */ import org.xml.sax.ContentHandler;
/*     */ import org.xml.sax.ErrorHandler;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.XMLReader;
/*     */ import org.xml.sax.helpers.DefaultHandler;
/*     */ 
/*     */ public class SAXValidator
/*     */ {
/*     */   private XMLReader xmlReader;
/*     */   private ErrorHandler errorHandler;
/*     */ 
/*     */   public SAXValidator()
/*     */   {
/*     */   }
/*     */ 
/*     */   public SAXValidator(XMLReader xmlReader)
/*     */   {
/*  43 */     this.xmlReader = xmlReader;
/*     */   }
/*     */ 
/*     */   public void validate(Document document)
/*     */     throws SAXException
/*     */   {
/*  59 */     if (document != null) {
/*  60 */       XMLReader reader = getXMLReader();
/*     */ 
/*  62 */       if (this.errorHandler != null) {
/*  63 */         reader.setErrorHandler(this.errorHandler);
/*     */       }
/*     */       try
/*     */       {
/*  67 */         reader.parse(new DocumentInputSource(document));
/*     */       } catch (IOException e) {
/*  69 */         throw new RuntimeException("Caught and exception that should never happen: " + e);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public XMLReader getXMLReader()
/*     */     throws SAXException
/*     */   {
/*  87 */     if (this.xmlReader == null) {
/*  88 */       this.xmlReader = createXMLReader();
/*  89 */       configureReader();
/*     */     }
/*     */ 
/*  92 */     return this.xmlReader;
/*     */   }
/*     */ 
/*     */   public void setXMLReader(XMLReader reader)
/*     */     throws SAXException
/*     */   {
/* 105 */     this.xmlReader = reader;
/* 106 */     configureReader();
/*     */   }
/*     */ 
/*     */   public ErrorHandler getErrorHandler()
/*     */   {
/* 115 */     return this.errorHandler;
/*     */   }
/*     */ 
/*     */   public void setErrorHandler(ErrorHandler errorHandler)
/*     */   {
/* 126 */     this.errorHandler = errorHandler;
/*     */   }
/*     */ 
/*     */   protected XMLReader createXMLReader()
/*     */     throws SAXException
/*     */   {
/* 142 */     return SAXHelper.createXMLReader(true);
/*     */   }
/*     */ 
/*     */   protected void configureReader()
/*     */     throws SAXException
/*     */   {
/* 152 */     ContentHandler handler = this.xmlReader.getContentHandler();
/*     */ 
/* 154 */     if (handler == null) {
/* 155 */       this.xmlReader.setContentHandler(new DefaultHandler());
/*     */     }
/*     */ 
/* 159 */     this.xmlReader.setFeature("http://xml.org/sax/features/validation", true);
/*     */ 
/* 162 */     this.xmlReader.setFeature("http://xml.org/sax/features/namespaces", true);
/* 163 */     this.xmlReader.setFeature("http://xml.org/sax/features/namespace-prefixes", false);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.io.SAXValidator
 * JD-Core Version:    0.6.2
 */