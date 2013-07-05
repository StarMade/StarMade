/*     */ package org.dom4j.io;
/*     */ 
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.dom4j.Branch;
/*     */ import org.dom4j.Document;
/*     */ import org.dom4j.DocumentFactory;
/*     */ import org.dom4j.DocumentType;
/*     */ import org.dom4j.Element;
/*     */ import org.dom4j.ElementHandler;
/*     */ import org.dom4j.Namespace;
/*     */ import org.dom4j.QName;
/*     */ import org.dom4j.dtd.AttributeDecl;
/*     */ import org.dom4j.dtd.ElementDecl;
/*     */ import org.dom4j.dtd.ExternalEntityDecl;
/*     */ import org.dom4j.dtd.InternalEntityDecl;
/*     */ import org.dom4j.tree.AbstractElement;
/*     */ import org.dom4j.tree.NamespaceStack;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.DTDHandler;
/*     */ import org.xml.sax.EntityResolver;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.Locator;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.SAXParseException;
/*     */ import org.xml.sax.ext.DeclHandler;
/*     */ import org.xml.sax.ext.LexicalHandler;
/*     */ import org.xml.sax.helpers.DefaultHandler;
/*     */ 
/*     */ public class SAXContentHandler extends DefaultHandler
/*     */   implements LexicalHandler, DeclHandler, DTDHandler
/*     */ {
/*     */   private DocumentFactory documentFactory;
/*     */   private Document document;
/*     */   private ElementStack elementStack;
/*     */   private NamespaceStack namespaceStack;
/*     */   private ElementHandler elementHandler;
/*     */   private Locator locator;
/*     */   private String entity;
/*     */   private boolean insideDTDSection;
/*     */   private boolean insideCDATASection;
/*     */   private StringBuffer cdataText;
/*  86 */   private Map availableNamespaceMap = new HashMap();
/*     */ 
/*  89 */   private List declaredNamespaceList = new ArrayList();
/*     */   private List internalDTDDeclarations;
/*     */   private List externalDTDDeclarations;
/*     */   private int declaredNamespaceIndex;
/*     */   private EntityResolver entityResolver;
/*     */   private InputSource inputSource;
/*     */   private Element currentElement;
/* 109 */   private boolean includeInternalDTDDeclarations = false;
/*     */ 
/* 112 */   private boolean includeExternalDTDDeclarations = false;
/*     */   private int entityLevel;
/* 118 */   private boolean internalDTDsubset = false;
/*     */ 
/* 121 */   private boolean mergeAdjacentText = false;
/*     */ 
/* 124 */   private boolean textInTextBuffer = false;
/*     */ 
/* 127 */   private boolean ignoreComments = false;
/*     */   private StringBuffer textBuffer;
/* 133 */   private boolean stripWhitespaceText = false;
/*     */ 
/*     */   public SAXContentHandler() {
/* 136 */     this(DocumentFactory.getInstance());
/*     */   }
/*     */ 
/*     */   public SAXContentHandler(DocumentFactory documentFactory) {
/* 140 */     this(documentFactory, null);
/*     */   }
/*     */ 
/*     */   public SAXContentHandler(DocumentFactory documentFactory, ElementHandler elementHandler)
/*     */   {
/* 145 */     this(documentFactory, elementHandler, null);
/* 146 */     this.elementStack = createElementStack();
/*     */   }
/*     */ 
/*     */   public SAXContentHandler(DocumentFactory documentFactory, ElementHandler elementHandler, ElementStack elementStack)
/*     */   {
/* 151 */     this.documentFactory = documentFactory;
/* 152 */     this.elementHandler = elementHandler;
/* 153 */     this.elementStack = elementStack;
/* 154 */     this.namespaceStack = new NamespaceStack(documentFactory);
/*     */   }
/*     */ 
/*     */   public Document getDocument()
/*     */   {
/* 163 */     if (this.document == null) {
/* 164 */       this.document = createDocument();
/*     */     }
/*     */ 
/* 167 */     return this.document;
/*     */   }
/*     */ 
/*     */   public void setDocumentLocator(Locator documentLocator)
/*     */   {
/* 173 */     this.locator = documentLocator;
/*     */   }
/*     */ 
/*     */   public void processingInstruction(String target, String data) throws SAXException
/*     */   {
/* 178 */     if ((this.mergeAdjacentText) && (this.textInTextBuffer)) {
/* 179 */       completeCurrentTextNode();
/*     */     }
/*     */ 
/* 182 */     if (this.currentElement != null)
/* 183 */       this.currentElement.addProcessingInstruction(target, data);
/*     */     else
/* 185 */       getDocument().addProcessingInstruction(target, data);
/*     */   }
/*     */ 
/*     */   public void startPrefixMapping(String prefix, String uri)
/*     */     throws SAXException
/*     */   {
/* 191 */     this.namespaceStack.push(prefix, uri);
/*     */   }
/*     */ 
/*     */   public void endPrefixMapping(String prefix) throws SAXException {
/* 195 */     this.namespaceStack.pop(prefix);
/* 196 */     this.declaredNamespaceIndex = this.namespaceStack.size();
/*     */   }
/*     */ 
/*     */   public void startDocument() throws SAXException
/*     */   {
/* 201 */     this.document = null;
/* 202 */     this.currentElement = null;
/*     */ 
/* 204 */     this.elementStack.clear();
/*     */ 
/* 206 */     if ((this.elementHandler != null) && ((this.elementHandler instanceof DispatchHandler)))
/*     */     {
/* 208 */       this.elementStack.setDispatchHandler((DispatchHandler)this.elementHandler);
/*     */     }
/*     */ 
/* 211 */     this.namespaceStack.clear();
/* 212 */     this.declaredNamespaceIndex = 0;
/*     */ 
/* 214 */     if ((this.mergeAdjacentText) && (this.textBuffer == null)) {
/* 215 */       this.textBuffer = new StringBuffer();
/*     */     }
/*     */ 
/* 218 */     this.textInTextBuffer = false;
/*     */   }
/*     */ 
/*     */   public void endDocument() throws SAXException {
/* 222 */     this.namespaceStack.clear();
/* 223 */     this.elementStack.clear();
/* 224 */     this.currentElement = null;
/* 225 */     this.textBuffer = null;
/*     */   }
/*     */ 
/*     */   public void startElement(String namespaceURI, String localName, String qualifiedName, Attributes attributes) throws SAXException
/*     */   {
/* 230 */     if ((this.mergeAdjacentText) && (this.textInTextBuffer)) {
/* 231 */       completeCurrentTextNode();
/*     */     }
/*     */ 
/* 234 */     QName qName = this.namespaceStack.getQName(namespaceURI, localName, qualifiedName);
/*     */ 
/* 237 */     Branch branch = this.currentElement;
/*     */ 
/* 239 */     if (branch == null) {
/* 240 */       branch = getDocument();
/*     */     }
/*     */ 
/* 243 */     Element element = branch.addElement(qName);
/*     */ 
/* 246 */     addDeclaredNamespaces(element);
/*     */ 
/* 249 */     addAttributes(element, attributes);
/*     */ 
/* 251 */     this.elementStack.pushElement(element);
/* 252 */     this.currentElement = element;
/*     */ 
/* 254 */     this.entity = null;
/*     */ 
/* 256 */     if (this.elementHandler != null)
/* 257 */       this.elementHandler.onStart(this.elementStack);
/*     */   }
/*     */ 
/*     */   public void endElement(String namespaceURI, String localName, String qName)
/*     */     throws SAXException
/*     */   {
/* 263 */     if ((this.mergeAdjacentText) && (this.textInTextBuffer)) {
/* 264 */       completeCurrentTextNode();
/*     */     }
/*     */ 
/* 267 */     if ((this.elementHandler != null) && (this.currentElement != null)) {
/* 268 */       this.elementHandler.onEnd(this.elementStack);
/*     */     }
/*     */ 
/* 271 */     this.elementStack.popElement();
/* 272 */     this.currentElement = this.elementStack.peekElement();
/*     */   }
/*     */ 
/*     */   public void characters(char[] ch, int start, int end) throws SAXException {
/* 276 */     if (end == 0) {
/* 277 */       return;
/*     */     }
/*     */ 
/* 280 */     if (this.currentElement != null)
/* 281 */       if (this.entity != null) {
/* 282 */         if ((this.mergeAdjacentText) && (this.textInTextBuffer)) {
/* 283 */           completeCurrentTextNode();
/*     */         }
/*     */ 
/* 286 */         this.currentElement.addEntity(this.entity, new String(ch, start, end));
/* 287 */         this.entity = null;
/* 288 */       } else if (this.insideCDATASection) {
/* 289 */         if ((this.mergeAdjacentText) && (this.textInTextBuffer)) {
/* 290 */           completeCurrentTextNode();
/*     */         }
/*     */ 
/* 293 */         this.cdataText.append(new String(ch, start, end));
/*     */       }
/* 295 */       else if (this.mergeAdjacentText) {
/* 296 */         this.textBuffer.append(ch, start, end);
/* 297 */         this.textInTextBuffer = true;
/*     */       } else {
/* 299 */         this.currentElement.addText(new String(ch, start, end));
/*     */       }
/*     */   }
/*     */ 
/*     */   public void warning(SAXParseException exception)
/*     */     throws SAXException
/*     */   {
/*     */   }
/*     */ 
/*     */   public void error(SAXParseException exception)
/*     */     throws SAXException
/*     */   {
/* 333 */     throw exception;
/*     */   }
/*     */ 
/*     */   public void fatalError(SAXParseException exception)
/*     */     throws SAXException
/*     */   {
/* 347 */     throw exception;
/*     */   }
/*     */ 
/*     */   public void startDTD(String name, String publicId, String systemId)
/*     */     throws SAXException
/*     */   {
/* 354 */     getDocument().addDocType(name, publicId, systemId);
/* 355 */     this.insideDTDSection = true;
/* 356 */     this.internalDTDsubset = true;
/*     */   }
/*     */ 
/*     */   public void endDTD() throws SAXException {
/* 360 */     this.insideDTDSection = false;
/*     */ 
/* 362 */     DocumentType docType = getDocument().getDocType();
/*     */ 
/* 364 */     if (docType != null) {
/* 365 */       if (this.internalDTDDeclarations != null) {
/* 366 */         docType.setInternalDeclarations(this.internalDTDDeclarations);
/*     */       }
/*     */ 
/* 369 */       if (this.externalDTDDeclarations != null) {
/* 370 */         docType.setExternalDeclarations(this.externalDTDDeclarations);
/*     */       }
/*     */     }
/*     */ 
/* 374 */     this.internalDTDDeclarations = null;
/* 375 */     this.externalDTDDeclarations = null;
/*     */   }
/*     */ 
/*     */   public void startEntity(String name) throws SAXException {
/* 379 */     this.entityLevel += 1;
/*     */ 
/* 382 */     this.entity = null;
/*     */ 
/* 384 */     if ((!this.insideDTDSection) && 
/* 385 */       (!isIgnorableEntity(name))) {
/* 386 */       this.entity = name;
/*     */     }
/*     */ 
/* 394 */     this.internalDTDsubset = false;
/*     */   }
/*     */ 
/*     */   public void endEntity(String name) throws SAXException {
/* 398 */     this.entityLevel -= 1;
/* 399 */     this.entity = null;
/*     */ 
/* 401 */     if (this.entityLevel == 0)
/* 402 */       this.internalDTDsubset = true;
/*     */   }
/*     */ 
/*     */   public void startCDATA() throws SAXException
/*     */   {
/* 407 */     this.insideCDATASection = true;
/* 408 */     this.cdataText = new StringBuffer();
/*     */   }
/*     */ 
/*     */   public void endCDATA() throws SAXException {
/* 412 */     this.insideCDATASection = false;
/* 413 */     this.currentElement.addCDATA(this.cdataText.toString());
/*     */   }
/*     */ 
/*     */   public void comment(char[] ch, int start, int end) throws SAXException {
/* 417 */     if (!this.ignoreComments) {
/* 418 */       if ((this.mergeAdjacentText) && (this.textInTextBuffer)) {
/* 419 */         completeCurrentTextNode();
/*     */       }
/*     */ 
/* 422 */       String text = new String(ch, start, end);
/*     */ 
/* 424 */       if ((!this.insideDTDSection) && (text.length() > 0))
/* 425 */         if (this.currentElement != null)
/* 426 */           this.currentElement.addComment(text);
/*     */         else
/* 428 */           getDocument().addComment(text);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void elementDecl(String name, String model)
/*     */     throws SAXException
/*     */   {
/* 458 */     if (this.internalDTDsubset) {
/* 459 */       if (this.includeInternalDTDDeclarations) {
/* 460 */         addDTDDeclaration(new ElementDecl(name, model));
/*     */       }
/*     */     }
/* 463 */     else if (this.includeExternalDTDDeclarations)
/* 464 */       addExternalDTDDeclaration(new ElementDecl(name, model));
/*     */   }
/*     */ 
/*     */   public void attributeDecl(String eName, String aName, String type, String valueDefault, String val)
/*     */     throws SAXException
/*     */   {
/* 504 */     if (this.internalDTDsubset) {
/* 505 */       if (this.includeInternalDTDDeclarations) {
/* 506 */         addDTDDeclaration(new AttributeDecl(eName, aName, type, valueDefault, val));
/*     */       }
/*     */ 
/*     */     }
/* 510 */     else if (this.includeExternalDTDDeclarations)
/* 511 */       addExternalDTDDeclaration(new AttributeDecl(eName, aName, type, valueDefault, val));
/*     */   }
/*     */ 
/*     */   public void internalEntityDecl(String name, String value)
/*     */     throws SAXException
/*     */   {
/* 540 */     if (this.internalDTDsubset) {
/* 541 */       if (this.includeInternalDTDDeclarations) {
/* 542 */         addDTDDeclaration(new InternalEntityDecl(name, value));
/*     */       }
/*     */     }
/* 545 */     else if (this.includeExternalDTDDeclarations)
/* 546 */       addExternalDTDDeclaration(new InternalEntityDecl(name, value));
/*     */   }
/*     */ 
/*     */   public void externalEntityDecl(String name, String publicId, String sysId)
/*     */     throws SAXException
/*     */   {
/* 575 */     ExternalEntityDecl declaration = new ExternalEntityDecl(name, publicId, sysId);
/*     */ 
/* 578 */     if (this.internalDTDsubset) {
/* 579 */       if (this.includeInternalDTDDeclarations) {
/* 580 */         addDTDDeclaration(declaration);
/*     */       }
/*     */     }
/* 583 */     else if (this.includeExternalDTDDeclarations)
/* 584 */       addExternalDTDDeclaration(declaration);
/*     */   }
/*     */ 
/*     */   public void notationDecl(String name, String publicId, String systemId)
/*     */     throws SAXException
/*     */   {
/*     */   }
/*     */ 
/*     */   public void unparsedEntityDecl(String name, String publicId, String systemId, String notationName)
/*     */     throws SAXException
/*     */   {
/*     */   }
/*     */ 
/*     */   public ElementStack getElementStack()
/*     */   {
/* 666 */     return this.elementStack;
/*     */   }
/*     */ 
/*     */   public void setElementStack(ElementStack elementStack) {
/* 670 */     this.elementStack = elementStack;
/*     */   }
/*     */ 
/*     */   public EntityResolver getEntityResolver() {
/* 674 */     return this.entityResolver;
/*     */   }
/*     */ 
/*     */   public void setEntityResolver(EntityResolver entityResolver) {
/* 678 */     this.entityResolver = entityResolver;
/*     */   }
/*     */ 
/*     */   public InputSource getInputSource() {
/* 682 */     return this.inputSource;
/*     */   }
/*     */ 
/*     */   public void setInputSource(InputSource inputSource) {
/* 686 */     this.inputSource = inputSource;
/*     */   }
/*     */ 
/*     */   public boolean isIncludeInternalDTDDeclarations()
/*     */   {
/* 696 */     return this.includeInternalDTDDeclarations;
/*     */   }
/*     */ 
/*     */   public void setIncludeInternalDTDDeclarations(boolean include)
/*     */   {
/* 708 */     this.includeInternalDTDDeclarations = include;
/*     */   }
/*     */ 
/*     */   public boolean isIncludeExternalDTDDeclarations()
/*     */   {
/* 718 */     return this.includeExternalDTDDeclarations;
/*     */   }
/*     */ 
/*     */   public void setIncludeExternalDTDDeclarations(boolean include)
/*     */   {
/* 730 */     this.includeExternalDTDDeclarations = include;
/*     */   }
/*     */ 
/*     */   public boolean isMergeAdjacentText()
/*     */   {
/* 739 */     return this.mergeAdjacentText;
/*     */   }
/*     */ 
/*     */   public void setMergeAdjacentText(boolean mergeAdjacentText)
/*     */   {
/* 750 */     this.mergeAdjacentText = mergeAdjacentText;
/*     */   }
/*     */ 
/*     */   public boolean isStripWhitespaceText()
/*     */   {
/* 760 */     return this.stripWhitespaceText;
/*     */   }
/*     */ 
/*     */   public void setStripWhitespaceText(boolean stripWhitespaceText)
/*     */   {
/* 771 */     this.stripWhitespaceText = stripWhitespaceText;
/*     */   }
/*     */ 
/*     */   public boolean isIgnoreComments()
/*     */   {
/* 780 */     return this.ignoreComments;
/*     */   }
/*     */ 
/*     */   public void setIgnoreComments(boolean ignoreComments)
/*     */   {
/* 790 */     this.ignoreComments = ignoreComments;
/*     */   }
/*     */ 
/*     */   protected void completeCurrentTextNode()
/*     */   {
/* 801 */     if (this.stripWhitespaceText) {
/* 802 */       boolean whitespace = true;
/*     */ 
/* 804 */       int i = 0; for (int size = this.textBuffer.length(); i < size; i++) {
/* 805 */         if (!Character.isWhitespace(this.textBuffer.charAt(i))) {
/* 806 */           whitespace = false;
/*     */ 
/* 808 */           break;
/*     */         }
/*     */       }
/*     */ 
/* 812 */       if (!whitespace)
/* 813 */         this.currentElement.addText(this.textBuffer.toString());
/*     */     }
/*     */     else {
/* 816 */       this.currentElement.addText(this.textBuffer.toString());
/*     */     }
/*     */ 
/* 819 */     this.textBuffer.setLength(0);
/* 820 */     this.textInTextBuffer = false;
/*     */   }
/*     */ 
/*     */   protected Document createDocument()
/*     */   {
/* 829 */     String encoding = getEncoding();
/* 830 */     Document doc = this.documentFactory.createDocument(encoding);
/*     */ 
/* 833 */     doc.setEntityResolver(this.entityResolver);
/*     */ 
/* 835 */     if (this.inputSource != null) {
/* 836 */       doc.setName(this.inputSource.getSystemId());
/*     */     }
/*     */ 
/* 839 */     return doc;
/*     */   }
/*     */ 
/*     */   private String getEncoding() {
/* 843 */     if (this.locator == null) {
/* 844 */       return null;
/*     */     }
/*     */ 
/*     */     try
/*     */     {
/* 850 */       Method m = this.locator.getClass().getMethod("getEncoding", new Class[0]);
/*     */ 
/* 853 */       if (m != null) {
/* 854 */         return (String)m.invoke(this.locator, null);
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*     */     }
/*     */ 
/* 861 */     return null;
/*     */   }
/*     */ 
/*     */   protected boolean isIgnorableEntity(String name)
/*     */   {
/* 873 */     return ("amp".equals(name)) || ("apos".equals(name)) || ("gt".equals(name)) || ("lt".equals(name)) || ("quot".equals(name));
/*     */   }
/*     */ 
/*     */   protected void addDeclaredNamespaces(Element element)
/*     */   {
/* 886 */     Namespace elementNamespace = element.getNamespace();
/*     */ 
/* 888 */     for (int size = this.namespaceStack.size(); this.declaredNamespaceIndex < size; 
/* 889 */       this.declaredNamespaceIndex += 1) {
/* 890 */       Namespace namespace = this.namespaceStack.getNamespace(this.declaredNamespaceIndex);
/*     */ 
/* 894 */       element.add(namespace);
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void addAttributes(Element element, Attributes attributes)
/*     */   {
/* 911 */     boolean noNamespaceAttributes = false;
/*     */ 
/* 913 */     if ((element instanceof AbstractElement))
/*     */     {
/* 915 */       AbstractElement baseElement = (AbstractElement)element;
/* 916 */       baseElement.setAttributes(attributes, this.namespaceStack, noNamespaceAttributes);
/*     */     }
/*     */     else {
/* 919 */       int size = attributes.getLength();
/*     */ 
/* 921 */       for (int i = 0; i < size; i++) {
/* 922 */         String attributeQName = attributes.getQName(i);
/*     */ 
/* 924 */         if ((noNamespaceAttributes) || (!attributeQName.startsWith("xmlns")))
/*     */         {
/* 926 */           String attributeURI = attributes.getURI(i);
/* 927 */           String attributeLocalName = attributes.getLocalName(i);
/* 928 */           String attributeValue = attributes.getValue(i);
/*     */ 
/* 930 */           QName qName = this.namespaceStack.getAttributeQName(attributeURI, attributeLocalName, attributeQName);
/*     */ 
/* 932 */           element.addAttribute(qName, attributeValue);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void addDTDDeclaration(Object declaration)
/*     */   {
/* 945 */     if (this.internalDTDDeclarations == null) {
/* 946 */       this.internalDTDDeclarations = new ArrayList();
/*     */     }
/*     */ 
/* 949 */     this.internalDTDDeclarations.add(declaration);
/*     */   }
/*     */ 
/*     */   protected void addExternalDTDDeclaration(Object declaration)
/*     */   {
/* 959 */     if (this.externalDTDDeclarations == null) {
/* 960 */       this.externalDTDDeclarations = new ArrayList();
/*     */     }
/*     */ 
/* 963 */     this.externalDTDDeclarations.add(declaration);
/*     */   }
/*     */ 
/*     */   protected ElementStack createElementStack() {
/* 967 */     return new ElementStack();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.io.SAXContentHandler
 * JD-Core Version:    0.6.2
 */