/*     */ package org.dom4j.io;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.dom4j.DocumentFactory;
/*     */ import org.dom4j.Element;
/*     */ import org.dom4j.ElementHandler;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.Locator;
/*     */ import org.xml.sax.SAXException;
/*     */ 
/*     */ class SAXModifyContentHandler extends SAXContentHandler
/*     */ {
/*     */   private XMLWriter xmlWriter;
/*     */ 
/*     */   public SAXModifyContentHandler()
/*     */   {
/*     */   }
/*     */ 
/*     */   public SAXModifyContentHandler(DocumentFactory documentFactory)
/*     */   {
/*  36 */     super(documentFactory);
/*     */   }
/*     */ 
/*     */   public SAXModifyContentHandler(DocumentFactory documentFactory, ElementHandler elementHandler)
/*     */   {
/*  41 */     super(documentFactory, elementHandler);
/*     */   }
/*     */ 
/*     */   public SAXModifyContentHandler(DocumentFactory documentFactory, ElementHandler elementHandler, ElementStack elementStack)
/*     */   {
/*  46 */     super(documentFactory, elementHandler, elementStack);
/*     */   }
/*     */ 
/*     */   public void setXMLWriter(XMLWriter writer) {
/*  50 */     this.xmlWriter = writer;
/*     */   }
/*     */ 
/*     */   public void startCDATA() throws SAXException {
/*  54 */     super.startCDATA();
/*     */ 
/*  56 */     if ((!activeHandlers()) && (this.xmlWriter != null))
/*  57 */       this.xmlWriter.startCDATA();
/*     */   }
/*     */ 
/*     */   public void startDTD(String name, String publicId, String systemId)
/*     */     throws SAXException
/*     */   {
/*  63 */     super.startDTD(name, publicId, systemId);
/*     */ 
/*  65 */     if (this.xmlWriter != null)
/*  66 */       this.xmlWriter.startDTD(name, publicId, systemId);
/*     */   }
/*     */ 
/*     */   public void endDTD() throws SAXException
/*     */   {
/*  71 */     super.endDTD();
/*     */ 
/*  73 */     if (this.xmlWriter != null)
/*  74 */       this.xmlWriter.endDTD();
/*     */   }
/*     */ 
/*     */   public void comment(char[] characters, int parm2, int parm3)
/*     */     throws SAXException
/*     */   {
/*  80 */     super.comment(characters, parm2, parm3);
/*     */ 
/*  82 */     if ((!activeHandlers()) && (this.xmlWriter != null))
/*  83 */       this.xmlWriter.comment(characters, parm2, parm3);
/*     */   }
/*     */ 
/*     */   public void startEntity(String name) throws SAXException
/*     */   {
/*  88 */     super.startEntity(name);
/*     */ 
/*  90 */     if (this.xmlWriter != null)
/*  91 */       this.xmlWriter.startEntity(name);
/*     */   }
/*     */ 
/*     */   public void endCDATA() throws SAXException
/*     */   {
/*  96 */     super.endCDATA();
/*     */ 
/*  98 */     if ((!activeHandlers()) && (this.xmlWriter != null))
/*  99 */       this.xmlWriter.endCDATA();
/*     */   }
/*     */ 
/*     */   public void endEntity(String name) throws SAXException
/*     */   {
/* 104 */     super.endEntity(name);
/*     */ 
/* 106 */     if (this.xmlWriter != null)
/* 107 */       this.xmlWriter.endEntity(name);
/*     */   }
/*     */ 
/*     */   public void unparsedEntityDecl(String name, String publicId, String systemId, String notation)
/*     */     throws SAXException
/*     */   {
/* 113 */     super.unparsedEntityDecl(name, publicId, systemId, notation);
/*     */ 
/* 115 */     if ((!activeHandlers()) && (this.xmlWriter != null))
/* 116 */       this.xmlWriter.unparsedEntityDecl(name, publicId, systemId, notation);
/*     */   }
/*     */ 
/*     */   public void notationDecl(String name, String publicId, String systemId)
/*     */     throws SAXException
/*     */   {
/* 122 */     super.notationDecl(name, publicId, systemId);
/*     */ 
/* 124 */     if (this.xmlWriter != null)
/* 125 */       this.xmlWriter.notationDecl(name, publicId, systemId);
/*     */   }
/*     */ 
/*     */   public void startElement(String uri, String localName, String qName, Attributes atts)
/*     */     throws SAXException
/*     */   {
/* 131 */     super.startElement(uri, localName, qName, atts);
/*     */ 
/* 133 */     if ((!activeHandlers()) && (this.xmlWriter != null))
/* 134 */       this.xmlWriter.startElement(uri, localName, qName, atts);
/*     */   }
/*     */ 
/*     */   public void startDocument() throws SAXException
/*     */   {
/* 139 */     super.startDocument();
/*     */ 
/* 141 */     if (this.xmlWriter != null)
/* 142 */       this.xmlWriter.startDocument();
/*     */   }
/*     */ 
/*     */   public void ignorableWhitespace(char[] parm1, int parm2, int parm3)
/*     */     throws SAXException
/*     */   {
/* 148 */     super.ignorableWhitespace(parm1, parm2, parm3);
/*     */ 
/* 150 */     if ((!activeHandlers()) && (this.xmlWriter != null))
/* 151 */       this.xmlWriter.ignorableWhitespace(parm1, parm2, parm3);
/*     */   }
/*     */ 
/*     */   public void processingInstruction(String target, String data)
/*     */     throws SAXException
/*     */   {
/* 157 */     super.processingInstruction(target, data);
/*     */ 
/* 159 */     if ((!activeHandlers()) && (this.xmlWriter != null))
/* 160 */       this.xmlWriter.processingInstruction(target, data);
/*     */   }
/*     */ 
/*     */   public void setDocumentLocator(Locator locator)
/*     */   {
/* 165 */     super.setDocumentLocator(locator);
/*     */ 
/* 167 */     if (this.xmlWriter != null)
/* 168 */       this.xmlWriter.setDocumentLocator(locator);
/*     */   }
/*     */ 
/*     */   public void skippedEntity(String name) throws SAXException
/*     */   {
/* 173 */     super.skippedEntity(name);
/*     */ 
/* 175 */     if ((!activeHandlers()) && (this.xmlWriter != null))
/* 176 */       this.xmlWriter.skippedEntity(name);
/*     */   }
/*     */ 
/*     */   public void endDocument() throws SAXException
/*     */   {
/* 181 */     super.endDocument();
/*     */ 
/* 183 */     if (this.xmlWriter != null)
/* 184 */       this.xmlWriter.endDocument();
/*     */   }
/*     */ 
/*     */   public void startPrefixMapping(String prefix, String uri)
/*     */     throws SAXException
/*     */   {
/* 190 */     super.startPrefixMapping(prefix, uri);
/*     */ 
/* 192 */     if (this.xmlWriter != null)
/* 193 */       this.xmlWriter.startPrefixMapping(prefix, uri);
/*     */   }
/*     */ 
/*     */   public void endElement(String uri, String localName, String qName)
/*     */     throws SAXException
/*     */   {
/* 199 */     ElementHandler currentHandler = getElementStack().getDispatchHandler().getHandler(getElementStack().getPath());
/*     */ 
/* 202 */     super.endElement(uri, localName, qName);
/*     */ 
/* 204 */     if ((!activeHandlers()) && 
/* 205 */       (this.xmlWriter != null))
/* 206 */       if (currentHandler == null) {
/* 207 */         this.xmlWriter.endElement(uri, localName, qName);
/* 208 */       } else if ((currentHandler instanceof SAXModifyElementHandler)) {
/* 209 */         SAXModifyElementHandler modifyHandler = (SAXModifyElementHandler)currentHandler;
/*     */ 
/* 211 */         Element modifiedElement = modifyHandler.getModifiedElement();
/*     */         try
/*     */         {
/* 215 */           this.xmlWriter.write(modifiedElement);
/*     */         } catch (IOException ex) {
/* 217 */           throw new SAXModifyException(ex);
/*     */         }
/*     */       }
/*     */   }
/*     */ 
/*     */   public void endPrefixMapping(String prefix)
/*     */     throws SAXException
/*     */   {
/* 225 */     super.endPrefixMapping(prefix);
/*     */ 
/* 227 */     if (this.xmlWriter != null)
/* 228 */       this.xmlWriter.endPrefixMapping(prefix);
/*     */   }
/*     */ 
/*     */   public void characters(char[] parm1, int parm2, int parm3)
/*     */     throws SAXException
/*     */   {
/* 234 */     super.characters(parm1, parm2, parm3);
/*     */ 
/* 236 */     if ((!activeHandlers()) && (this.xmlWriter != null))
/* 237 */       this.xmlWriter.characters(parm1, parm2, parm3);
/*     */   }
/*     */ 
/*     */   protected XMLWriter getXMLWriter()
/*     */   {
/* 242 */     return this.xmlWriter;
/*     */   }
/*     */ 
/*     */   private boolean activeHandlers() {
/* 246 */     DispatchHandler handler = getElementStack().getDispatchHandler();
/*     */ 
/* 248 */     return handler.getActiveHandlerCount() > 0;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.io.SAXModifyContentHandler
 * JD-Core Version:    0.6.2
 */