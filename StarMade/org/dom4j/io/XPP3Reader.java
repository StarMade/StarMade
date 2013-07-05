/*     */ package org.dom4j.io;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.CharArrayReader;
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
/*     */ import org.dom4j.QName;
/*     */ import org.xmlpull.v1.XmlPullParser;
/*     */ import org.xmlpull.v1.XmlPullParserException;
/*     */ import org.xmlpull.v1.XmlPullParserFactory;
/*     */ 
/*     */ public class XPP3Reader
/*     */ {
/*     */   private DocumentFactory factory;
/*     */   private XmlPullParser xppParser;
/*     */   private XmlPullParserFactory xppFactory;
/*     */   private DispatchHandler dispatchHandler;
/*     */ 
/*     */   public XPP3Reader()
/*     */   {
/*     */   }
/*     */ 
/*     */   public XPP3Reader(DocumentFactory factory)
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
/* 211 */     getXPPParser().setInput(new CharArrayReader(text));
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
/* 282 */     this.xppFactory.setNamespaceAware(true);
/*     */ 
/* 284 */     return this.xppFactory;
/*     */   }
/*     */ 
/*     */   public void setXPPFactory(XmlPullParserFactory xPPfactory) {
/* 288 */     this.xppFactory = xPPfactory;
/*     */   }
/*     */ 
/*     */   public DocumentFactory getDocumentFactory()
/*     */   {
/* 298 */     if (this.factory == null) {
/* 299 */       this.factory = DocumentFactory.getInstance();
/*     */     }
/*     */ 
/* 302 */     return this.factory;
/*     */   }
/*     */ 
/*     */   public void setDocumentFactory(DocumentFactory documentFactory)
/*     */   {
/* 317 */     this.factory = documentFactory;
/*     */   }
/*     */ 
/*     */   public void addHandler(String path, ElementHandler handler)
/*     */   {
/* 331 */     getDispatchHandler().addHandler(path, handler);
/*     */   }
/*     */ 
/*     */   public void removeHandler(String path)
/*     */   {
/* 342 */     getDispatchHandler().removeHandler(path);
/*     */   }
/*     */ 
/*     */   public void setDefaultHandler(ElementHandler handler)
/*     */   {
/* 355 */     getDispatchHandler().setDefaultHandler(handler);
/*     */   }
/*     */ 
/*     */   protected Document parseDocument()
/*     */     throws DocumentException, IOException, XmlPullParserException
/*     */   {
/* 362 */     DocumentFactory df = getDocumentFactory();
/* 363 */     Document document = df.createDocument();
/* 364 */     Element parent = null;
/* 365 */     XmlPullParser pp = getXPPParser();
/* 366 */     pp.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
/*     */     while (true)
/*     */     {
/* 369 */       int type = pp.nextToken();
/*     */ 
/* 371 */       switch (type) {
/*     */       case 8:
/* 373 */         String text = pp.getText();
/* 374 */         int loc = text.indexOf(" ");
/*     */ 
/* 376 */         if (loc >= 0) {
/* 377 */           String target = text.substring(0, loc);
/* 378 */           String txt = text.substring(loc + 1);
/* 379 */           document.addProcessingInstruction(target, txt);
/*     */         } else {
/* 381 */           document.addProcessingInstruction(text, "");
/*     */         }
/*     */ 
/* 384 */         break;
/*     */       case 9:
/* 388 */         if (parent != null)
/* 389 */           parent.addComment(pp.getText());
/*     */         else {
/* 391 */           document.addComment(pp.getText());
/*     */         }
/*     */ 
/* 394 */         break;
/*     */       case 5:
/* 398 */         if (parent != null) {
/* 399 */           parent.addCDATA(pp.getText());
/*     */         } else {
/* 401 */           String msg = "Cannot have text content outside of the root document";
/*     */ 
/* 403 */           throw new DocumentException(msg);
/*     */         }
/*     */ 
/*     */         break;
/*     */       case 6:
/* 410 */         break;
/*     */       case 1:
/* 413 */         return document;
/*     */       case 2:
/* 416 */         QName qname = pp.getPrefix() == null ? df.createQName(pp.getName(), pp.getNamespace()) : df.createQName(pp.getName(), pp.getPrefix(), pp.getNamespace());
/*     */ 
/* 419 */         Element newElement = df.createElement(qname);
/* 420 */         int nsStart = pp.getNamespaceCount(pp.getDepth() - 1);
/* 421 */         int nsEnd = pp.getNamespaceCount(pp.getDepth());
/*     */ 
/* 423 */         for (int i = nsStart; i < nsEnd; i++) {
/* 424 */           if (pp.getNamespacePrefix(i) != null) {
/* 425 */             newElement.addNamespace(pp.getNamespacePrefix(i), pp.getNamespaceUri(i));
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/* 430 */         for (int i = 0; i < pp.getAttributeCount(); i++) {
/* 431 */           QName qa = pp.getAttributePrefix(i) == null ? df.createQName(pp.getAttributeName(i)) : df.createQName(pp.getAttributeName(i), pp.getAttributePrefix(i), pp.getAttributeNamespace(i));
/*     */ 
/* 436 */           newElement.addAttribute(qa, pp.getAttributeValue(i));
/*     */         }
/*     */ 
/* 439 */         if (parent != null)
/* 440 */           parent.add(newElement);
/*     */         else {
/* 442 */           document.add(newElement);
/*     */         }
/*     */ 
/* 445 */         parent = newElement;
/*     */ 
/* 447 */         break;
/*     */       case 3:
/* 451 */         if (parent != null)
/* 452 */           parent = parent.getParent(); break;
/*     */       case 4:
/* 459 */         String text = pp.getText();
/*     */ 
/* 461 */         if (parent != null) {
/* 462 */           parent.addText(text);
/*     */         } else {
/* 464 */           String msg = "Cannot have text content outside of the root document";
/*     */ 
/* 466 */           throw new DocumentException(msg);
/*     */         }
/*     */         break;
/*     */       case 7:
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   protected DispatchHandler getDispatchHandler()
/*     */   {
/* 479 */     if (this.dispatchHandler == null) {
/* 480 */       this.dispatchHandler = new DispatchHandler();
/*     */     }
/*     */ 
/* 483 */     return this.dispatchHandler;
/*     */   }
/*     */ 
/*     */   protected void setDispatchHandler(DispatchHandler dispatchHandler) {
/* 487 */     this.dispatchHandler = dispatchHandler;
/*     */   }
/*     */ 
/*     */   protected Reader createReader(InputStream in)
/*     */     throws IOException
/*     */   {
/* 502 */     return new BufferedReader(new InputStreamReader(in));
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.io.XPP3Reader
 * JD-Core Version:    0.6.2
 */