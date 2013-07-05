/*     */ package org.dom4j.io;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.InputStream;
/*     */ import java.io.Reader;
/*     */ import java.net.URL;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import org.dom4j.Document;
/*     */ import org.dom4j.DocumentException;
/*     */ import org.dom4j.DocumentFactory;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.XMLReader;
/*     */ 
/*     */ public class SAXModifier
/*     */ {
/*     */   private XMLWriter xmlWriter;
/*     */   private XMLReader xmlReader;
/*     */   private boolean pruneElements;
/*     */   private SAXModifyReader modifyReader;
/*  53 */   private HashMap modifiers = new HashMap();
/*     */ 
/*     */   public SAXModifier()
/*     */   {
/*     */   }
/*     */ 
/*     */   public SAXModifier(boolean pruneElements)
/*     */   {
/*  75 */     this.pruneElements = pruneElements;
/*     */   }
/*     */ 
/*     */   public SAXModifier(XMLReader xmlReader)
/*     */   {
/*  86 */     this.xmlReader = xmlReader;
/*     */   }
/*     */ 
/*     */   public SAXModifier(XMLReader xmlReader, boolean pruneElements)
/*     */   {
/* 100 */     this.xmlReader = xmlReader;
/*     */   }
/*     */ 
/*     */   public Document modify(File source)
/*     */     throws DocumentException
/*     */   {
/*     */     try
/*     */     {
/* 119 */       return installModifyReader().read(source);
/*     */     } catch (SAXModifyException ex) {
/* 121 */       Throwable cause = ex.getCause();
/* 122 */       throw new DocumentException(cause.getMessage(), cause);
/*     */     }
/*     */   }
/*     */ 
/*     */   public Document modify(InputSource source)
/*     */     throws DocumentException
/*     */   {
/*     */     try
/*     */     {
/* 142 */       return installModifyReader().read(source);
/*     */     } catch (SAXModifyException ex) {
/* 144 */       Throwable cause = ex.getCause();
/* 145 */       throw new DocumentException(cause.getMessage(), cause);
/*     */     }
/*     */   }
/*     */ 
/*     */   public Document modify(InputStream source)
/*     */     throws DocumentException
/*     */   {
/*     */     try
/*     */     {
/* 165 */       return installModifyReader().read(source);
/*     */     } catch (SAXModifyException ex) {
/* 167 */       Throwable cause = ex.getCause();
/* 168 */       throw new DocumentException(cause.getMessage(), cause);
/*     */     }
/*     */   }
/*     */ 
/*     */   public Document modify(InputStream source, String systemId)
/*     */     throws DocumentException
/*     */   {
/*     */     try
/*     */     {
/* 191 */       return installModifyReader().read(source);
/*     */     } catch (SAXModifyException ex) {
/* 193 */       Throwable cause = ex.getCause();
/* 194 */       throw new DocumentException(cause.getMessage(), cause);
/*     */     }
/*     */   }
/*     */ 
/*     */   public Document modify(Reader source)
/*     */     throws DocumentException
/*     */   {
/*     */     try
/*     */     {
/* 214 */       return installModifyReader().read(source);
/*     */     } catch (SAXModifyException ex) {
/* 216 */       Throwable cause = ex.getCause();
/* 217 */       throw new DocumentException(cause.getMessage(), cause);
/*     */     }
/*     */   }
/*     */ 
/*     */   public Document modify(Reader source, String systemId)
/*     */     throws DocumentException
/*     */   {
/*     */     try
/*     */     {
/* 240 */       return installModifyReader().read(source);
/*     */     } catch (SAXModifyException ex) {
/* 242 */       Throwable cause = ex.getCause();
/* 243 */       throw new DocumentException(cause.getMessage(), cause);
/*     */     }
/*     */   }
/*     */ 
/*     */   public Document modify(URL source)
/*     */     throws DocumentException
/*     */   {
/*     */     try
/*     */     {
/* 263 */       return installModifyReader().read(source);
/*     */     } catch (SAXModifyException ex) {
/* 265 */       Throwable cause = ex.getCause();
/* 266 */       throw new DocumentException(cause.getMessage(), cause);
/*     */     }
/*     */   }
/*     */ 
/*     */   public Document modify(String source)
/*     */     throws DocumentException
/*     */   {
/*     */     try
/*     */     {
/* 286 */       return installModifyReader().read(source);
/*     */     } catch (SAXModifyException ex) {
/* 288 */       Throwable cause = ex.getCause();
/* 289 */       throw new DocumentException(cause.getMessage(), cause);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void addModifier(String path, ElementModifier modifier)
/*     */   {
/* 304 */     this.modifiers.put(path, modifier);
/*     */   }
/*     */ 
/*     */   public void resetModifiers()
/*     */   {
/* 312 */     this.modifiers.clear();
/* 313 */     getSAXModifyReader().resetHandlers();
/*     */   }
/*     */ 
/*     */   public void removeModifier(String path)
/*     */   {
/* 324 */     this.modifiers.remove(path);
/* 325 */     getSAXModifyReader().removeHandler(path);
/*     */   }
/*     */ 
/*     */   public DocumentFactory getDocumentFactory()
/*     */   {
/* 335 */     return getSAXModifyReader().getDocumentFactory();
/*     */   }
/*     */ 
/*     */   public void setDocumentFactory(DocumentFactory factory)
/*     */   {
/* 346 */     getSAXModifyReader().setDocumentFactory(factory);
/*     */   }
/*     */ 
/*     */   public XMLWriter getXMLWriter()
/*     */   {
/* 355 */     return this.xmlWriter;
/*     */   }
/*     */ 
/*     */   public void setXMLWriter(XMLWriter writer)
/*     */   {
/* 365 */     this.xmlWriter = writer;
/*     */   }
/*     */ 
/*     */   public boolean isPruneElements()
/*     */   {
/* 375 */     return this.pruneElements;
/*     */   }
/*     */ 
/*     */   private SAXReader installModifyReader() throws DocumentException {
/*     */     try {
/* 380 */       SAXModifyReader reader = getSAXModifyReader();
/*     */ 
/* 382 */       if (isPruneElements()) {
/* 383 */         this.modifyReader.setDispatchHandler(new PruningDispatchHandler());
/*     */       }
/*     */ 
/* 386 */       reader.resetHandlers();
/*     */ 
/* 388 */       Iterator modifierIt = this.modifiers.entrySet().iterator();
/*     */ 
/* 390 */       while (modifierIt.hasNext()) {
/* 391 */         Map.Entry entry = (Map.Entry)modifierIt.next();
/*     */ 
/* 393 */         SAXModifyElementHandler handler = new SAXModifyElementHandler((ElementModifier)entry.getValue());
/*     */ 
/* 395 */         reader.addHandler((String)entry.getKey(), handler);
/*     */       }
/*     */ 
/* 398 */       reader.setXMLWriter(getXMLWriter());
/* 399 */       reader.setXMLReader(getXMLReader());
/*     */ 
/* 401 */       return reader;
/*     */     } catch (SAXException ex) {
/* 403 */       throw new DocumentException(ex.getMessage(), ex);
/*     */     }
/*     */   }
/*     */ 
/*     */   private XMLReader getXMLReader() throws SAXException {
/* 408 */     if (this.xmlReader == null) {
/* 409 */       this.xmlReader = SAXHelper.createXMLReader(false);
/*     */     }
/*     */ 
/* 412 */     return this.xmlReader;
/*     */   }
/*     */ 
/*     */   private SAXModifyReader getSAXModifyReader() {
/* 416 */     if (this.modifyReader == null) {
/* 417 */       this.modifyReader = new SAXModifyReader();
/*     */     }
/*     */ 
/* 420 */     return this.modifyReader;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.io.SAXModifier
 * JD-Core Version:    0.6.2
 */