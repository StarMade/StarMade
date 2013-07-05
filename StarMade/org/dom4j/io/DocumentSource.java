/*     */ package org.dom4j.io;
/*     */ 
/*     */ import javax.xml.transform.sax.SAXSource;
/*     */ import org.dom4j.Document;
/*     */ import org.dom4j.Node;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.XMLFilter;
/*     */ import org.xml.sax.XMLReader;
/*     */ 
/*     */ public class DocumentSource extends SAXSource
/*     */ {
/*     */   public static final String DOM4J_FEATURE = "http://org.dom4j.io.DoucmentSource/feature";
/*  38 */   private XMLReader xmlReader = new SAXWriter();
/*     */ 
/*     */   public DocumentSource(Node node)
/*     */   {
/*  47 */     setDocument(node.getDocument());
/*     */   }
/*     */ 
/*     */   public DocumentSource(Document document)
/*     */   {
/*  57 */     setDocument(document);
/*     */   }
/*     */ 
/*     */   public Document getDocument()
/*     */   {
/*  69 */     DocumentInputSource source = (DocumentInputSource)getInputSource();
/*  70 */     return source.getDocument();
/*     */   }
/*     */ 
/*     */   public void setDocument(Document document)
/*     */   {
/*  80 */     super.setInputSource(new DocumentInputSource(document));
/*     */   }
/*     */ 
/*     */   public XMLReader getXMLReader()
/*     */   {
/*  92 */     return this.xmlReader;
/*     */   }
/*     */ 
/*     */   public void setInputSource(InputSource inputSource)
/*     */     throws UnsupportedOperationException
/*     */   {
/* 107 */     if ((inputSource instanceof DocumentInputSource))
/* 108 */       super.setInputSource((DocumentInputSource)inputSource);
/*     */     else
/* 110 */       throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public void setXMLReader(XMLReader reader)
/*     */     throws UnsupportedOperationException
/*     */   {
/* 125 */     if ((reader instanceof SAXWriter)) {
/* 126 */       this.xmlReader = ((SAXWriter)reader);
/* 127 */     } else if ((reader instanceof XMLFilter)) {
/* 128 */       XMLFilter filter = (XMLFilter)reader;
/*     */       while (true)
/*     */       {
/* 131 */         XMLReader parent = filter.getParent();
/*     */ 
/* 133 */         if (!(parent instanceof XMLFilter)) break;
/* 134 */         filter = (XMLFilter)parent;
/*     */       }
/*     */ 
/* 141 */       filter.setParent(this.xmlReader);
/* 142 */       this.xmlReader = filter;
/*     */     } else {
/* 144 */       throw new UnsupportedOperationException();
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.io.DocumentSource
 * JD-Core Version:    0.6.2
 */