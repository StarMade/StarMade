/*     */ package org.dom4j.jaxb;
/*     */ 
/*     */ import java.io.StringReader;
/*     */ import javax.xml.bind.JAXBContext;
/*     */ import javax.xml.bind.JAXBException;
/*     */ import javax.xml.bind.Marshaller;
/*     */ import javax.xml.bind.Unmarshaller;
/*     */ import javax.xml.transform.Source;
/*     */ import javax.xml.transform.stream.StreamSource;
/*     */ import org.dom4j.dom.DOMDocument;
/*     */ 
/*     */ abstract class JAXBSupport
/*     */ {
/*     */   private String contextPath;
/*     */   private ClassLoader classloader;
/*     */   private JAXBContext jaxbContext;
/*     */   private Marshaller marshaller;
/*     */   private Unmarshaller unmarshaller;
/*     */ 
/*     */   public JAXBSupport(String contextPath)
/*     */   {
/*  38 */     this.contextPath = contextPath;
/*     */   }
/*     */ 
/*     */   public JAXBSupport(String contextPath, ClassLoader classloader) {
/*  42 */     this.contextPath = contextPath;
/*  43 */     this.classloader = classloader;
/*     */   }
/*     */ 
/*     */   protected org.dom4j.Element marshal(javax.xml.bind.Element element)
/*     */     throws JAXBException
/*     */   {
/*  60 */     DOMDocument doc = new DOMDocument();
/*  61 */     getMarshaller().marshal(element, doc);
/*     */ 
/*  63 */     return doc.getRootElement();
/*     */   }
/*     */ 
/*     */   protected javax.xml.bind.Element unmarshal(org.dom4j.Element element)
/*     */     throws JAXBException
/*     */   {
/*  80 */     Source source = new StreamSource(new StringReader(element.asXML()));
/*     */ 
/*  82 */     return (javax.xml.bind.Element)getUnmarshaller().unmarshal(source);
/*     */   }
/*     */ 
/*     */   private Marshaller getMarshaller() throws JAXBException {
/*  86 */     if (this.marshaller == null) {
/*  87 */       this.marshaller = getContext().createMarshaller();
/*     */     }
/*     */ 
/*  90 */     return this.marshaller;
/*     */   }
/*     */ 
/*     */   private Unmarshaller getUnmarshaller() throws JAXBException {
/*  94 */     if (this.unmarshaller == null) {
/*  95 */       this.unmarshaller = getContext().createUnmarshaller();
/*     */     }
/*     */ 
/*  98 */     return this.unmarshaller;
/*     */   }
/*     */ 
/*     */   private JAXBContext getContext() throws JAXBException {
/* 102 */     if (this.jaxbContext == null) {
/* 103 */       if (this.classloader == null)
/* 104 */         this.jaxbContext = JAXBContext.newInstance(this.contextPath);
/*     */       else {
/* 106 */         this.jaxbContext = JAXBContext.newInstance(this.contextPath, this.classloader);
/*     */       }
/*     */     }
/*     */ 
/* 110 */     return this.jaxbContext;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.jaxb.JAXBSupport
 * JD-Core Version:    0.6.2
 */