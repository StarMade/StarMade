/*     */ package org.dom4j.io;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.Reader;
/*     */ import java.net.URL;
/*     */ import org.dom4j.Document;
/*     */ import org.dom4j.DocumentException;
/*     */ import org.dom4j.DocumentFactory;
/*     */ import org.dom4j.Element;
/*     */ import org.dom4j.ElementHandler;
/*     */ import org.dom4j.xpp.ProxyXmlStartTag;
/*     */ import org.gjt.xpp.XmlEndTag;
/*     */ import org.gjt.xpp.XmlPullParser;
/*     */ import org.gjt.xpp.XmlPullParserException;
/*     */ import org.gjt.xpp.XmlPullParserFactory;
/*     */ 
/*     */ public class XPPReader
/*     */ {
/*     */   private DocumentFactory factory;
/*     */   private XmlPullParser xppParser;
/*     */   private XmlPullParserFactory xppFactory;
/*     */   private DispatchHandler dispatchHandler;
/*     */ 
/*     */   public XPPReader()
/*     */   {
/*     */   }
/*     */ 
/*     */   public XPPReader(DocumentFactory factory)
/*     */   {
/*  59 */     this.factory = factory;
/*     */   }
/*     */ 
/*     */   public Document read(File file)
/*     */     throws DocumentException, IOException, XmlPullParserException
/*     */   {
/*  81 */     String systemID = file.getAbsolutePath();
/*     */ 
/*  83 */     return read(new BufferedReader(new FileReader(file)), systemID);
/*     */   }
/*     */ 
/*     */   public Document read(URL url)
/*     */     throws DocumentException, IOException, XmlPullParserException
/*     */   {
/* 105 */     String systemID = url.toExternalForm();
/*     */ 
/* 107 */     return read(createReader(url.openStream()), systemID);
/*     */   }
/*     */ 
/*     */   public Document read(String systemID)
/*     */     throws DocumentException, IOException, XmlPullParserException
/*     */   {
/* 137 */     if (systemID.indexOf(':') >= 0)
/*     */     {
/* 139 */       return read(new URL(systemID));
/*     */     }
/*     */ 
/* 142 */     return read(new File(systemID));
/*     */   }
/*     */ 
/*     */   public Document read(InputStream in)
/*     */     throws DocumentException, IOException, XmlPullParserException
/*     */   {
/* 165 */     return read(createReader(in));
/*     */   }
/*     */ 
/*     */   public Document read(Reader reader)
/*     */     throws DocumentException, IOException, XmlPullParserException
/*     */   {
/* 187 */     getXPPParser().setInput(reader);
/*     */ 
/* 189 */     return parseDocument();
/*     */   }
/*     */ 
/*     */   public Document read(char[] text)
/*     */     throws DocumentException, IOException, XmlPullParserException
/*     */   {
/* 211 */     getXPPParser().setInput(text);
/*     */ 
/* 213 */     return parseDocument();
/*     */   }
/*     */ 
/*     */   public Document read(InputStream in, String systemID)
/*     */     throws DocumentException, IOException, XmlPullParserException
/*     */   {
/* 237 */     return read(createReader(in), systemID);
/*     */   }
/*     */ 
/*     */   public Document read(Reader reader, String systemID)
/*     */     throws DocumentException, IOException, XmlPullParserException
/*     */   {
/* 261 */     Document document = read(reader);
/* 262 */     document.setName(systemID);
/*     */ 
/* 264 */     return document;
/*     */   }
/*     */ 
/*     */   public XmlPullParser getXPPParser()
/*     */     throws XmlPullParserException
/*     */   {
/* 270 */     if (this.xppParser == null) {
/* 271 */       this.xppParser = getXPPFactory().newPullParser();
/*     */     }
/*     */ 
/* 274 */     return this.xppParser;
/*     */   }
/*     */ 
/*     */   public XmlPullParserFactory getXPPFactory() throws XmlPullParserException {
/* 278 */     if (this.xppFactory == null) {
/* 279 */       this.xppFactory = XmlPullParserFactory.newInstance();
/*     */     }
/*     */ 
/* 282 */     return this.xppFactory;
/*     */   }
/*     */ 
/*     */   public void setXPPFactory(XmlPullParserFactory xPPFactory) {
/* 286 */     this.xppFactory = xPPFactory;
/*     */   }
/*     */ 
/*     */   public DocumentFactory getDocumentFactory()
/*     */   {
/* 296 */     if (this.factory == null) {
/* 297 */       this.factory = DocumentFactory.getInstance();
/*     */     }
/*     */ 
/* 300 */     return this.factory;
/*     */   }
/*     */ 
/*     */   public void setDocumentFactory(DocumentFactory documentFactory)
/*     */   {
/* 315 */     this.factory = documentFactory;
/*     */   }
/*     */ 
/*     */   public void addHandler(String path, ElementHandler handler)
/*     */   {
/* 329 */     getDispatchHandler().addHandler(path, handler);
/*     */   }
/*     */ 
/*     */   public void removeHandler(String path)
/*     */   {
/* 340 */     getDispatchHandler().removeHandler(path);
/*     */   }
/*     */ 
/*     */   public void setDefaultHandler(ElementHandler handler)
/*     */   {
/* 353 */     getDispatchHandler().setDefaultHandler(handler);
/*     */   }
/*     */ 
/*     */   protected Document parseDocument()
/*     */     throws DocumentException, IOException, XmlPullParserException
/*     */   {
/* 360 */     Document document = getDocumentFactory().createDocument();
/* 361 */     Element parent = null;
/* 362 */     XmlPullParser parser = getXPPParser();
/* 363 */     parser.setNamespaceAware(true);
/*     */ 
/* 365 */     ProxyXmlStartTag startTag = new ProxyXmlStartTag();
/* 366 */     XmlEndTag endTag = this.xppFactory.newEndTag();
/*     */     while (true)
/*     */     {
/* 369 */       int type = parser.next();
/*     */ 
/* 371 */       switch (type) {
/*     */       case 1:
/* 373 */         return document;
/*     */       case 2:
/* 376 */         parser.readStartTag(startTag);
/*     */ 
/* 378 */         Element newElement = startTag.getElement();
/*     */ 
/* 380 */         if (parent != null)
/* 381 */           parent.add(newElement);
/*     */         else {
/* 383 */           document.add(newElement);
/*     */         }
/*     */ 
/* 386 */         parent = newElement;
/*     */ 
/* 388 */         break;
/*     */       case 3:
/* 392 */         parser.readEndTag(endTag);
/*     */ 
/* 394 */         if (parent != null)
/* 395 */           parent = parent.getParent(); break;
/*     */       case 4:
/* 402 */         String text = parser.readContent();
/*     */ 
/* 404 */         if (parent != null) {
/* 405 */           parent.addText(text);
/*     */         } else {
/* 407 */           String msg = "Cannot have text content outside of the root document";
/*     */ 
/* 409 */           throw new DocumentException(msg);
/*     */         }
/*     */ 
/*     */         break;
/*     */       default:
/* 416 */         throw new DocumentException("Error: unknown type: " + type);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   protected DispatchHandler getDispatchHandler() {
/* 422 */     if (this.dispatchHandler == null) {
/* 423 */       this.dispatchHandler = new DispatchHandler();
/*     */     }
/*     */ 
/* 426 */     return this.dispatchHandler;
/*     */   }
/*     */ 
/*     */   protected void setDispatchHandler(DispatchHandler dispatchHandler) {
/* 430 */     this.dispatchHandler = dispatchHandler;
/*     */   }
/*     */ 
/*     */   protected Reader createReader(InputStream in)
/*     */     throws IOException
/*     */   {
/* 445 */     return new BufferedReader(new InputStreamReader(in));
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.io.XPPReader
 * JD-Core Version:    0.6.2
 */