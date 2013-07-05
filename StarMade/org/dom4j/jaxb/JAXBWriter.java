/*     */ package org.dom4j.jaxb;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.io.Writer;
/*     */ import javax.xml.bind.JAXBException;
/*     */ import org.dom4j.io.OutputFormat;
/*     */ import org.dom4j.io.XMLWriter;
/*     */ import org.xml.sax.SAXException;
/*     */ 
/*     */ public class JAXBWriter extends JAXBSupport
/*     */ {
/*     */   private XMLWriter xmlWriter;
/*     */   private OutputFormat outputFormat;
/*     */ 
/*     */   public JAXBWriter(String contextPath)
/*     */   {
/*  50 */     super(contextPath);
/*  51 */     this.outputFormat = new OutputFormat();
/*     */   }
/*     */ 
/*     */   public JAXBWriter(String contextPath, OutputFormat outputFormat)
/*     */   {
/*  67 */     super(contextPath);
/*  68 */     this.outputFormat = outputFormat;
/*     */   }
/*     */ 
/*     */   public JAXBWriter(String contextPath, ClassLoader classloader)
/*     */   {
/*  85 */     super(contextPath, classloader);
/*     */   }
/*     */ 
/*     */   public JAXBWriter(String contextPath, ClassLoader classloader, OutputFormat outputFormat)
/*     */   {
/* 104 */     super(contextPath, classloader);
/* 105 */     this.outputFormat = outputFormat;
/*     */   }
/*     */ 
/*     */   public OutputFormat getOutputFormat()
/*     */   {
/* 114 */     return this.outputFormat;
/*     */   }
/*     */ 
/*     */   public void setOutput(File file)
/*     */     throws IOException
/*     */   {
/* 128 */     getWriter().setOutputStream(new FileOutputStream(file));
/*     */   }
/*     */ 
/*     */   public void setOutput(OutputStream outputStream)
/*     */     throws IOException
/*     */   {
/* 142 */     getWriter().setOutputStream(outputStream);
/*     */   }
/*     */ 
/*     */   public void setOutput(Writer writer)
/*     */     throws IOException
/*     */   {
/* 154 */     getWriter().setWriter(writer);
/*     */   }
/*     */ 
/*     */   public void startDocument()
/*     */     throws IOException, SAXException
/*     */   {
/* 167 */     getWriter().startDocument();
/*     */   }
/*     */ 
/*     */   public void endDocument()
/*     */     throws IOException, SAXException
/*     */   {
/* 180 */     getWriter().endDocument();
/*     */   }
/*     */ 
/*     */   public void write(javax.xml.bind.Element jaxbObject)
/*     */     throws IOException, JAXBException
/*     */   {
/* 197 */     getWriter().write(marshal(jaxbObject));
/*     */   }
/*     */ 
/*     */   public void writeClose(javax.xml.bind.Element jaxbObject)
/*     */     throws IOException, JAXBException
/*     */   {
/* 216 */     getWriter().writeClose(marshal(jaxbObject));
/*     */   }
/*     */ 
/*     */   public void writeOpen(javax.xml.bind.Element jaxbObject)
/*     */     throws IOException, JAXBException
/*     */   {
/* 234 */     getWriter().writeOpen(marshal(jaxbObject));
/*     */   }
/*     */ 
/*     */   public void writeElement(org.dom4j.Element element)
/*     */     throws IOException
/*     */   {
/* 247 */     getWriter().write(element);
/*     */   }
/*     */ 
/*     */   public void writeCloseElement(org.dom4j.Element element)
/*     */     throws IOException
/*     */   {
/* 261 */     getWriter().writeClose(element);
/*     */   }
/*     */ 
/*     */   public void writeOpenElement(org.dom4j.Element element)
/*     */     throws IOException
/*     */   {
/* 275 */     getWriter().writeOpen(element);
/*     */   }
/*     */ 
/*     */   private XMLWriter getWriter() throws IOException {
/* 279 */     if (this.xmlWriter == null) {
/* 280 */       if (this.outputFormat != null)
/* 281 */         this.xmlWriter = new XMLWriter(this.outputFormat);
/*     */       else {
/* 283 */         this.xmlWriter = new XMLWriter();
/*     */       }
/*     */     }
/*     */ 
/* 287 */     return this.xmlWriter;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.jaxb.JAXBWriter
 * JD-Core Version:    0.6.2
 */