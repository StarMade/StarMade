/*     */ package org.dom4j.jaxb;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.Reader;
/*     */ import java.net.URL;
/*     */ import java.nio.charset.Charset;
/*     */ import org.dom4j.Document;
/*     */ import org.dom4j.DocumentException;
/*     */ import org.dom4j.ElementHandler;
/*     */ import org.dom4j.ElementPath;
/*     */ import org.dom4j.io.SAXReader;
/*     */ import org.xml.sax.InputSource;
/*     */ 
/*     */ public class JAXBReader extends JAXBSupport
/*     */ {
/*     */   private SAXReader reader;
/*     */   private boolean pruneElements;
/*     */ 
/*     */   public JAXBReader(String contextPath)
/*     */   {
/*  55 */     super(contextPath);
/*     */   }
/*     */ 
/*     */   public JAXBReader(String contextPath, ClassLoader classloader)
/*     */   {
/*  72 */     super(contextPath, classloader);
/*     */   }
/*     */ 
/*     */   public Document read(File source)
/*     */     throws DocumentException
/*     */   {
/*  87 */     return getReader().read(source);
/*     */   }
/*     */ 
/*     */   public Document read(File file, Charset charset)
/*     */     throws DocumentException
/*     */   {
/*     */     try
/*     */     {
/* 106 */       Reader xmlReader = new InputStreamReader(new FileInputStream(file), charset);
/*     */ 
/* 109 */       return getReader().read(xmlReader);
/*     */     } catch (JAXBRuntimeException ex) {
/* 111 */       Throwable cause = ex.getCause();
/* 112 */       throw new DocumentException(cause.getMessage(), cause);
/*     */     } catch (FileNotFoundException ex) {
/* 114 */       throw new DocumentException(ex.getMessage(), ex);
/*     */     }
/*     */   }
/*     */ 
/*     */   public Document read(InputSource source)
/*     */     throws DocumentException
/*     */   {
/*     */     try
/*     */     {
/* 131 */       return getReader().read(source);
/*     */     } catch (JAXBRuntimeException ex) {
/* 133 */       Throwable cause = ex.getCause();
/* 134 */       throw new DocumentException(cause.getMessage(), cause);
/*     */     }
/*     */   }
/*     */ 
/*     */   public Document read(InputStream source)
/*     */     throws DocumentException
/*     */   {
/*     */     try
/*     */     {
/* 151 */       return getReader().read(source);
/*     */     } catch (JAXBRuntimeException ex) {
/* 153 */       Throwable cause = ex.getCause();
/* 154 */       throw new DocumentException(cause.getMessage(), cause);
/*     */     }
/*     */   }
/*     */ 
/*     */   public Document read(InputStream source, String systemId)
/*     */     throws DocumentException
/*     */   {
/*     */     try
/*     */     {
/* 174 */       return getReader().read(source);
/*     */     } catch (JAXBRuntimeException ex) {
/* 176 */       Throwable cause = ex.getCause();
/* 177 */       throw new DocumentException(cause.getMessage(), cause);
/*     */     }
/*     */   }
/*     */ 
/*     */   public Document read(Reader source)
/*     */     throws DocumentException
/*     */   {
/*     */     try
/*     */     {
/* 194 */       return getReader().read(source);
/*     */     } catch (JAXBRuntimeException ex) {
/* 196 */       Throwable cause = ex.getCause();
/* 197 */       throw new DocumentException(cause.getMessage(), cause);
/*     */     }
/*     */   }
/*     */ 
/*     */   public Document read(Reader source, String systemId)
/*     */     throws DocumentException
/*     */   {
/*     */     try
/*     */     {
/* 217 */       return getReader().read(source);
/*     */     } catch (JAXBRuntimeException ex) {
/* 219 */       Throwable cause = ex.getCause();
/* 220 */       throw new DocumentException(cause.getMessage(), cause);
/*     */     }
/*     */   }
/*     */ 
/*     */   public Document read(String source)
/*     */     throws DocumentException
/*     */   {
/*     */     try
/*     */     {
/* 237 */       return getReader().read(source);
/*     */     } catch (JAXBRuntimeException ex) {
/* 239 */       Throwable cause = ex.getCause();
/* 240 */       throw new DocumentException(cause.getMessage(), cause);
/*     */     }
/*     */   }
/*     */ 
/*     */   public Document read(URL source)
/*     */     throws DocumentException
/*     */   {
/*     */     try
/*     */     {
/* 257 */       return getReader().read(source);
/*     */     } catch (JAXBRuntimeException ex) {
/* 259 */       Throwable cause = ex.getCause();
/* 260 */       throw new DocumentException(cause.getMessage(), cause);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void addObjectHandler(String path, JAXBObjectHandler handler)
/*     */   {
/* 275 */     ElementHandler eHandler = new UnmarshalElementHandler(this, handler);
/* 276 */     getReader().addHandler(path, eHandler);
/*     */   }
/*     */ 
/*     */   public void removeObjectHandler(String path)
/*     */   {
/* 287 */     getReader().removeHandler(path);
/*     */   }
/*     */ 
/*     */   public void addHandler(String path, ElementHandler handler)
/*     */   {
/* 301 */     getReader().addHandler(path, handler);
/*     */   }
/*     */ 
/*     */   public void removeHandler(String path)
/*     */   {
/* 312 */     getReader().removeHandler(path);
/*     */   }
/*     */ 
/*     */   public void resetHandlers()
/*     */   {
/* 320 */     getReader().resetHandlers();
/*     */   }
/*     */ 
/*     */   public boolean isPruneElements()
/*     */   {
/* 329 */     return this.pruneElements;
/*     */   }
/*     */ 
/*     */   public void setPruneElements(boolean pruneElements)
/*     */   {
/* 339 */     this.pruneElements = pruneElements;
/*     */ 
/* 341 */     if (pruneElements)
/* 342 */       getReader().setDefaultHandler(new PruningElementHandler());
/*     */   }
/*     */ 
/*     */   private SAXReader getReader()
/*     */   {
/* 347 */     if (this.reader == null) {
/* 348 */       this.reader = new SAXReader();
/*     */     }
/*     */ 
/* 351 */     return this.reader;
/*     */   }
/*     */ 
/*     */   private class PruningElementHandler
/*     */     implements ElementHandler
/*     */   {
/*     */     public PruningElementHandler()
/*     */     {
/*     */     }
/*     */ 
/*     */     public void onStart(ElementPath parm1)
/*     */     {
/*     */     }
/*     */ 
/*     */     public void onEnd(ElementPath elementPath)
/*     */     {
/* 394 */       org.dom4j.Element elem = elementPath.getCurrent();
/* 395 */       elem.detach();
/* 396 */       elem = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   private class UnmarshalElementHandler
/*     */     implements ElementHandler
/*     */   {
/*     */     private JAXBReader jaxbReader;
/*     */     private JAXBObjectHandler handler;
/*     */ 
/*     */     public UnmarshalElementHandler(JAXBReader documentReader, JAXBObjectHandler handler)
/*     */     {
/* 361 */       this.jaxbReader = documentReader;
/* 362 */       this.handler = handler;
/*     */     }
/*     */ 
/*     */     public void onStart(ElementPath elementPath) {
/*     */     }
/*     */ 
/*     */     public void onEnd(ElementPath elementPath) {
/*     */       try {
/* 370 */         org.dom4j.Element elem = elementPath.getCurrent();
/*     */ 
/* 372 */         javax.xml.bind.Element jaxbObject = this.jaxbReader.unmarshal(elem);
/*     */ 
/* 375 */         if (this.jaxbReader.isPruneElements()) {
/* 376 */           elem.detach();
/*     */         }
/*     */ 
/* 379 */         this.handler.handleObject(jaxbObject);
/*     */       } catch (Exception ex) {
/* 381 */         throw new JAXBRuntimeException(ex);
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.jaxb.JAXBReader
 * JD-Core Version:    0.6.2
 */