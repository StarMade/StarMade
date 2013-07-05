/*     */ package org.dom4j.jaxb;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.OutputStream;
/*     */ import java.io.Reader;
/*     */ import java.io.Writer;
/*     */ import java.net.URL;
/*     */ import java.nio.charset.Charset;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import org.dom4j.Document;
/*     */ import org.dom4j.DocumentException;
/*     */ import org.dom4j.io.ElementModifier;
/*     */ import org.dom4j.io.OutputFormat;
/*     */ import org.dom4j.io.SAXModifier;
/*     */ import org.dom4j.io.XMLWriter;
/*     */ import org.xml.sax.InputSource;
/*     */ 
/*     */ public class JAXBModifier extends JAXBSupport
/*     */ {
/*     */   private SAXModifier modifier;
/*     */   private XMLWriter xmlWriter;
/*     */   private boolean pruneElements;
/*     */   private OutputFormat outputFormat;
/*  54 */   private HashMap modifiers = new HashMap();
/*     */ 
/*     */   public JAXBModifier(String contextPath)
/*     */   {
/*  67 */     super(contextPath);
/*  68 */     this.outputFormat = new OutputFormat();
/*     */   }
/*     */ 
/*     */   public JAXBModifier(String contextPath, ClassLoader classloader)
/*     */   {
/*  85 */     super(contextPath, classloader);
/*  86 */     this.outputFormat = new OutputFormat();
/*     */   }
/*     */ 
/*     */   public JAXBModifier(String contextPath, OutputFormat outputFormat)
/*     */   {
/* 102 */     super(contextPath);
/* 103 */     this.outputFormat = outputFormat;
/*     */   }
/*     */ 
/*     */   public JAXBModifier(String contextPath, ClassLoader classloader, OutputFormat outputFormat)
/*     */   {
/* 122 */     super(contextPath, classloader);
/* 123 */     this.outputFormat = outputFormat;
/*     */   }
/*     */ 
/*     */   public Document modify(File source)
/*     */     throws DocumentException, IOException
/*     */   {
/* 141 */     return installModifier().modify(source);
/*     */   }
/*     */ 
/*     */   public Document modify(File source, Charset charset)
/*     */     throws DocumentException, IOException
/*     */   {
/*     */     try
/*     */     {
/* 164 */       Reader reader = new InputStreamReader(new FileInputStream(source), charset);
/*     */ 
/* 167 */       return installModifier().modify(reader);
/*     */     } catch (JAXBRuntimeException ex) {
/* 169 */       Throwable cause = ex.getCause();
/* 170 */       throw new DocumentException(cause.getMessage(), cause);
/*     */     } catch (FileNotFoundException ex) {
/* 172 */       throw new DocumentException(ex.getMessage(), ex);
/*     */     }
/*     */   }
/*     */ 
/*     */   public Document modify(InputSource source)
/*     */     throws DocumentException, IOException
/*     */   {
/*     */     try
/*     */     {
/* 193 */       return installModifier().modify(source);
/*     */     } catch (JAXBRuntimeException ex) {
/* 195 */       Throwable cause = ex.getCause();
/* 196 */       throw new DocumentException(cause.getMessage(), cause);
/*     */     }
/*     */   }
/*     */ 
/*     */   public Document modify(InputStream source)
/*     */     throws DocumentException, IOException
/*     */   {
/*     */     try
/*     */     {
/* 217 */       return installModifier().modify(source);
/*     */     } catch (JAXBRuntimeException ex) {
/* 219 */       Throwable cause = ex.getCause();
/* 220 */       throw new DocumentException(cause.getMessage(), cause);
/*     */     }
/*     */   }
/*     */ 
/*     */   public Document modify(InputStream source, String systemId)
/*     */     throws DocumentException, IOException
/*     */   {
/*     */     try
/*     */     {
/* 243 */       return installModifier().modify(source);
/*     */     } catch (JAXBRuntimeException ex) {
/* 245 */       Throwable cause = ex.getCause();
/* 246 */       throw new DocumentException(cause.getMessage(), cause);
/*     */     }
/*     */   }
/*     */ 
/*     */   public Document modify(Reader r)
/*     */     throws DocumentException, IOException
/*     */   {
/*     */     try
/*     */     {
/* 266 */       return installModifier().modify(r);
/*     */     } catch (JAXBRuntimeException ex) {
/* 268 */       Throwable cause = ex.getCause();
/* 269 */       throw new DocumentException(cause.getMessage(), cause);
/*     */     }
/*     */   }
/*     */ 
/*     */   public Document modify(Reader source, String systemId)
/*     */     throws DocumentException, IOException
/*     */   {
/*     */     try
/*     */     {
/* 292 */       return installModifier().modify(source);
/*     */     } catch (JAXBRuntimeException ex) {
/* 294 */       Throwable cause = ex.getCause();
/* 295 */       throw new DocumentException(cause.getMessage(), cause);
/*     */     }
/*     */   }
/*     */ 
/*     */   public Document modify(String url)
/*     */     throws DocumentException, IOException
/*     */   {
/*     */     try
/*     */     {
/* 315 */       return installModifier().modify(url);
/*     */     } catch (JAXBRuntimeException ex) {
/* 317 */       Throwable cause = ex.getCause();
/* 318 */       throw new DocumentException(cause.getMessage(), cause);
/*     */     }
/*     */   }
/*     */ 
/*     */   public Document modify(URL source)
/*     */     throws DocumentException, IOException
/*     */   {
/*     */     try
/*     */     {
/* 338 */       return installModifier().modify(source);
/*     */     } catch (JAXBRuntimeException ex) {
/* 340 */       Throwable cause = ex.getCause();
/* 341 */       throw new DocumentException(cause.getMessage(), cause);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setOutput(File file)
/*     */     throws IOException
/*     */   {
/* 355 */     createXMLWriter().setOutputStream(new FileOutputStream(file));
/*     */   }
/*     */ 
/*     */   public void setOutput(OutputStream outputStream)
/*     */     throws IOException
/*     */   {
/* 368 */     createXMLWriter().setOutputStream(outputStream);
/*     */   }
/*     */ 
/*     */   public void setOutput(Writer writer)
/*     */     throws IOException
/*     */   {
/* 381 */     createXMLWriter().setWriter(writer);
/*     */   }
/*     */ 
/*     */   public void addObjectModifier(String path, JAXBObjectModifier mod)
/*     */   {
/* 394 */     this.modifiers.put(path, mod);
/*     */   }
/*     */ 
/*     */   public void removeObjectModifier(String path)
/*     */   {
/* 405 */     this.modifiers.remove(path);
/* 406 */     getModifier().removeModifier(path);
/*     */   }
/*     */ 
/*     */   public void resetObjectModifiers()
/*     */   {
/* 414 */     this.modifiers.clear();
/* 415 */     getModifier().resetModifiers();
/*     */   }
/*     */ 
/*     */   public boolean isPruneElements()
/*     */   {
/* 425 */     return this.pruneElements;
/*     */   }
/*     */ 
/*     */   public void setPruneElements(boolean pruneElements)
/*     */   {
/* 436 */     this.pruneElements = pruneElements;
/*     */   }
/*     */ 
/*     */   private SAXModifier installModifier() throws IOException {
/* 440 */     this.modifier = new SAXModifier(isPruneElements());
/*     */ 
/* 442 */     this.modifier.resetModifiers();
/*     */ 
/* 444 */     Iterator modifierIt = this.modifiers.entrySet().iterator();
/*     */ 
/* 446 */     while (modifierIt.hasNext()) {
/* 447 */       Map.Entry entry = (Map.Entry)modifierIt.next();
/* 448 */       ElementModifier mod = new JAXBElementModifier(this, (JAXBObjectModifier)entry.getValue());
/*     */ 
/* 450 */       getModifier().addModifier((String)entry.getKey(), mod);
/*     */     }
/*     */ 
/* 453 */     this.modifier.setXMLWriter(getXMLWriter());
/*     */ 
/* 455 */     return this.modifier;
/*     */   }
/*     */ 
/*     */   private SAXModifier getModifier() {
/* 459 */     if (this.modifier == null) {
/* 460 */       this.modifier = new SAXModifier(isPruneElements());
/*     */     }
/*     */ 
/* 463 */     return this.modifier;
/*     */   }
/*     */ 
/*     */   private XMLWriter getXMLWriter() {
/* 467 */     return this.xmlWriter;
/*     */   }
/*     */ 
/*     */   private XMLWriter createXMLWriter() throws IOException {
/* 471 */     if (this.xmlWriter == null) {
/* 472 */       this.xmlWriter = new XMLWriter(this.outputFormat);
/*     */     }
/*     */ 
/* 475 */     return this.xmlWriter;
/*     */   }
/*     */ 
/*     */   private class JAXBElementModifier implements ElementModifier
/*     */   {
/*     */     private JAXBModifier jaxbModifier;
/*     */     private JAXBObjectModifier objectModifier;
/*     */ 
/*     */     public JAXBElementModifier(JAXBModifier jaxbModifier, JAXBObjectModifier objectModifier) {
/* 485 */       this.jaxbModifier = jaxbModifier;
/* 486 */       this.objectModifier = objectModifier;
/*     */     }
/*     */ 
/*     */     public org.dom4j.Element modifyElement(org.dom4j.Element element) throws Exception
/*     */     {
/* 491 */       javax.xml.bind.Element originalObject = this.jaxbModifier.unmarshal(element);
/*     */ 
/* 493 */       javax.xml.bind.Element modifiedObject = this.objectModifier.modifyObject(originalObject);
/*     */ 
/* 496 */       return this.jaxbModifier.marshal(modifiedObject);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.jaxb.JAXBModifier
 * JD-Core Version:    0.6.2
 */