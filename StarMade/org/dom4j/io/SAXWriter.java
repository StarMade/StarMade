/*     */ package org.dom4j.io;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.dom4j.Attribute;
/*     */ import org.dom4j.Branch;
/*     */ import org.dom4j.CDATA;
/*     */ import org.dom4j.CharacterData;
/*     */ import org.dom4j.Comment;
/*     */ import org.dom4j.Document;
/*     */ import org.dom4j.DocumentType;
/*     */ import org.dom4j.Element;
/*     */ import org.dom4j.Entity;
/*     */ import org.dom4j.Namespace;
/*     */ import org.dom4j.Node;
/*     */ import org.dom4j.ProcessingInstruction;
/*     */ import org.dom4j.Text;
/*     */ import org.dom4j.tree.NamespaceStack;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.ContentHandler;
/*     */ import org.xml.sax.DTDHandler;
/*     */ import org.xml.sax.EntityResolver;
/*     */ import org.xml.sax.ErrorHandler;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.SAXNotRecognizedException;
/*     */ import org.xml.sax.SAXNotSupportedException;
/*     */ import org.xml.sax.XMLReader;
/*     */ import org.xml.sax.ext.LexicalHandler;
/*     */ import org.xml.sax.helpers.AttributesImpl;
/*     */ import org.xml.sax.helpers.LocatorImpl;
/*     */ 
/*     */ public class SAXWriter
/*     */   implements XMLReader
/*     */ {
/*  54 */   protected static final String[] LEXICAL_HANDLER_NAMES = { "http://xml.org/sax/properties/lexical-handler", "http://xml.org/sax/handlers/LexicalHandler" };
/*     */   protected static final String FEATURE_NAMESPACE_PREFIXES = "http://xml.org/sax/features/namespace-prefixes";
/*     */   protected static final String FEATURE_NAMESPACES = "http://xml.org/sax/features/namespaces";
/*     */   private ContentHandler contentHandler;
/*     */   private DTDHandler dtdHandler;
/*     */   private EntityResolver entityResolver;
/*     */   private ErrorHandler errorHandler;
/*     */   private LexicalHandler lexicalHandler;
/*  79 */   private AttributesImpl attributes = new AttributesImpl();
/*     */ 
/*  82 */   private Map features = new HashMap();
/*     */ 
/*  85 */   private Map properties = new HashMap();
/*     */   private boolean declareNamespaceAttributes;
/*     */ 
/*     */   public SAXWriter()
/*     */   {
/*  91 */     this.properties.put("http://xml.org/sax/features/namespace-prefixes", Boolean.FALSE);
/*  92 */     this.properties.put("http://xml.org/sax/features/namespace-prefixes", Boolean.TRUE);
/*     */   }
/*     */ 
/*     */   public SAXWriter(ContentHandler contentHandler) {
/*  96 */     this();
/*  97 */     this.contentHandler = contentHandler;
/*     */   }
/*     */ 
/*     */   public SAXWriter(ContentHandler contentHandler, LexicalHandler lexicalHandler)
/*     */   {
/* 102 */     this();
/* 103 */     this.contentHandler = contentHandler;
/* 104 */     this.lexicalHandler = lexicalHandler;
/*     */   }
/*     */ 
/*     */   public SAXWriter(ContentHandler contentHandler, LexicalHandler lexicalHandler, EntityResolver entityResolver)
/*     */   {
/* 109 */     this();
/* 110 */     this.contentHandler = contentHandler;
/* 111 */     this.lexicalHandler = lexicalHandler;
/* 112 */     this.entityResolver = entityResolver;
/*     */   }
/*     */ 
/*     */   public void write(Node node)
/*     */     throws SAXException
/*     */   {
/* 125 */     int nodeType = node.getNodeType();
/*     */ 
/* 127 */     switch (nodeType) {
/*     */     case 1:
/* 129 */       write((Element)node);
/*     */ 
/* 131 */       break;
/*     */     case 2:
/* 134 */       write((Attribute)node);
/*     */ 
/* 136 */       break;
/*     */     case 3:
/* 139 */       write(node.getText());
/*     */ 
/* 141 */       break;
/*     */     case 4:
/* 144 */       write((CDATA)node);
/*     */ 
/* 146 */       break;
/*     */     case 5:
/* 149 */       write((Entity)node);
/*     */ 
/* 151 */       break;
/*     */     case 7:
/* 154 */       write((ProcessingInstruction)node);
/*     */ 
/* 156 */       break;
/*     */     case 8:
/* 159 */       write((Comment)node);
/*     */ 
/* 161 */       break;
/*     */     case 9:
/* 164 */       write((Document)node);
/*     */ 
/* 166 */       break;
/*     */     case 10:
/* 169 */       write((DocumentType)node);
/*     */ 
/* 171 */       break;
/*     */     case 13:
/* 177 */       break;
/*     */     case 6:
/*     */     case 11:
/*     */     case 12:
/*     */     default:
/* 180 */       throw new SAXException("Invalid node type: " + node);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void write(Document document)
/*     */     throws SAXException
/*     */   {
/* 194 */     if (document != null) {
/* 195 */       checkForNullHandlers();
/*     */ 
/* 197 */       documentLocator(document);
/* 198 */       startDocument();
/* 199 */       entityResolver(document);
/* 200 */       dtdHandler(document);
/*     */ 
/* 202 */       writeContent(document, new NamespaceStack());
/* 203 */       endDocument();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void write(Element element)
/*     */     throws SAXException
/*     */   {
/* 217 */     write(element, new NamespaceStack());
/*     */   }
/*     */ 
/*     */   public void writeOpen(Element element)
/*     */     throws SAXException
/*     */   {
/* 233 */     startElement(element, null);
/*     */   }
/*     */ 
/*     */   public void writeClose(Element element)
/*     */     throws SAXException
/*     */   {
/* 248 */     endElement(element);
/*     */   }
/*     */ 
/*     */   public void write(String text)
/*     */     throws SAXException
/*     */   {
/* 261 */     if (text != null) {
/* 262 */       char[] chars = text.toCharArray();
/* 263 */       this.contentHandler.characters(chars, 0, chars.length);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void write(CDATA cdata)
/*     */     throws SAXException
/*     */   {
/* 277 */     String text = cdata.getText();
/*     */ 
/* 279 */     if (this.lexicalHandler != null) {
/* 280 */       this.lexicalHandler.startCDATA();
/* 281 */       write(text);
/* 282 */       this.lexicalHandler.endCDATA();
/*     */     } else {
/* 284 */       write(text);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void write(Comment comment)
/*     */     throws SAXException
/*     */   {
/* 298 */     if (this.lexicalHandler != null) {
/* 299 */       String text = comment.getText();
/* 300 */       char[] chars = text.toCharArray();
/* 301 */       this.lexicalHandler.comment(chars, 0, chars.length);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void write(Entity entity)
/*     */     throws SAXException
/*     */   {
/* 315 */     String text = entity.getText();
/*     */ 
/* 317 */     if (this.lexicalHandler != null) {
/* 318 */       String name = entity.getName();
/* 319 */       this.lexicalHandler.startEntity(name);
/* 320 */       write(text);
/* 321 */       this.lexicalHandler.endEntity(name);
/*     */     } else {
/* 323 */       write(text);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void write(ProcessingInstruction pi)
/*     */     throws SAXException
/*     */   {
/* 337 */     String target = pi.getTarget();
/* 338 */     String text = pi.getText();
/* 339 */     this.contentHandler.processingInstruction(target, text);
/*     */   }
/*     */ 
/*     */   public boolean isDeclareNamespaceAttributes()
/*     */   {
/* 351 */     return this.declareNamespaceAttributes;
/*     */   }
/*     */ 
/*     */   public void setDeclareNamespaceAttributes(boolean declareNamespaceAttrs)
/*     */   {
/* 363 */     this.declareNamespaceAttributes = declareNamespaceAttrs;
/*     */   }
/*     */ 
/*     */   public ContentHandler getContentHandler()
/*     */   {
/* 376 */     return this.contentHandler;
/*     */   }
/*     */ 
/*     */   public void setContentHandler(ContentHandler contentHandler)
/*     */   {
/* 387 */     this.contentHandler = contentHandler;
/*     */   }
/*     */ 
/*     */   public DTDHandler getDTDHandler()
/*     */   {
/* 396 */     return this.dtdHandler;
/*     */   }
/*     */ 
/*     */   public void setDTDHandler(DTDHandler handler)
/*     */   {
/* 406 */     this.dtdHandler = handler;
/*     */   }
/*     */ 
/*     */   public ErrorHandler getErrorHandler()
/*     */   {
/* 415 */     return this.errorHandler;
/*     */   }
/*     */ 
/*     */   public void setErrorHandler(ErrorHandler errorHandler)
/*     */   {
/* 425 */     this.errorHandler = errorHandler;
/*     */   }
/*     */ 
/*     */   public EntityResolver getEntityResolver()
/*     */   {
/* 435 */     return this.entityResolver;
/*     */   }
/*     */ 
/*     */   public void setEntityResolver(EntityResolver entityResolver)
/*     */   {
/* 445 */     this.entityResolver = entityResolver;
/*     */   }
/*     */ 
/*     */   public LexicalHandler getLexicalHandler()
/*     */   {
/* 455 */     return this.lexicalHandler;
/*     */   }
/*     */ 
/*     */   public void setLexicalHandler(LexicalHandler lexicalHandler)
/*     */   {
/* 465 */     this.lexicalHandler = lexicalHandler;
/*     */   }
/*     */ 
/*     */   public void setXMLReader(XMLReader xmlReader)
/*     */   {
/* 475 */     setContentHandler(xmlReader.getContentHandler());
/* 476 */     setDTDHandler(xmlReader.getDTDHandler());
/* 477 */     setEntityResolver(xmlReader.getEntityResolver());
/* 478 */     setErrorHandler(xmlReader.getErrorHandler());
/*     */   }
/*     */ 
/*     */   public boolean getFeature(String name)
/*     */     throws SAXNotRecognizedException, SAXNotSupportedException
/*     */   {
/* 496 */     Boolean answer = (Boolean)this.features.get(name);
/*     */ 
/* 498 */     return (answer != null) && (answer.booleanValue());
/*     */   }
/*     */ 
/*     */   public void setFeature(String name, boolean value)
/*     */     throws SAXNotRecognizedException, SAXNotSupportedException
/*     */   {
/* 517 */     if ("http://xml.org/sax/features/namespace-prefixes".equals(name)) {
/* 518 */       setDeclareNamespaceAttributes(value);
/* 519 */     } else if (("http://xml.org/sax/features/namespace-prefixes".equals(name)) && 
/* 520 */       (!value)) {
/* 521 */       String msg = "Namespace feature is always supported in dom4j";
/* 522 */       throw new SAXNotSupportedException(msg);
/*     */     }
/*     */ 
/* 526 */     this.features.put(name, value ? Boolean.TRUE : Boolean.FALSE);
/*     */   }
/*     */ 
/*     */   public void setProperty(String name, Object value)
/*     */   {
/* 538 */     for (int i = 0; i < LEXICAL_HANDLER_NAMES.length; i++) {
/* 539 */       if (LEXICAL_HANDLER_NAMES[i].equals(name)) {
/* 540 */         setLexicalHandler((LexicalHandler)value);
/*     */ 
/* 542 */         return;
/*     */       }
/*     */     }
/*     */ 
/* 546 */     this.properties.put(name, value);
/*     */   }
/*     */ 
/*     */   public Object getProperty(String name)
/*     */     throws SAXNotRecognizedException, SAXNotSupportedException
/*     */   {
/* 564 */     for (int i = 0; i < LEXICAL_HANDLER_NAMES.length; i++) {
/* 565 */       if (LEXICAL_HANDLER_NAMES[i].equals(name)) {
/* 566 */         return getLexicalHandler();
/*     */       }
/*     */     }
/*     */ 
/* 570 */     return this.properties.get(name);
/*     */   }
/*     */ 
/*     */   public void parse(String systemId)
/*     */     throws SAXNotSupportedException
/*     */   {
/* 583 */     throw new SAXNotSupportedException("This XMLReader can only accept <dom4j> InputSource objects");
/*     */   }
/*     */ 
/*     */   public void parse(InputSource input)
/*     */     throws SAXException
/*     */   {
/* 600 */     if ((input instanceof DocumentInputSource)) {
/* 601 */       DocumentInputSource documentInput = (DocumentInputSource)input;
/* 602 */       Document document = documentInput.getDocument();
/* 603 */       write(document);
/*     */     } else {
/* 605 */       throw new SAXNotSupportedException("This XMLReader can only accept <dom4j> InputSource objects");
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void writeContent(Branch branch, NamespaceStack namespaceStack)
/*     */     throws SAXException
/*     */   {
/* 615 */     for (Iterator iter = branch.nodeIterator(); iter.hasNext(); ) {
/* 616 */       Object object = iter.next();
/*     */ 
/* 618 */       if ((object instanceof Element))
/* 619 */         write((Element)object, namespaceStack);
/* 620 */       else if ((object instanceof CharacterData)) {
/* 621 */         if ((object instanceof Text)) {
/* 622 */           Text text = (Text)object;
/* 623 */           write(text.getText());
/* 624 */         } else if ((object instanceof CDATA)) {
/* 625 */           write((CDATA)object);
/* 626 */         } else if ((object instanceof Comment)) {
/* 627 */           write((Comment)object);
/*     */         } else {
/* 629 */           throw new SAXException("Invalid Node in DOM4J content: " + object + " of type: " + object.getClass());
/*     */         }
/*     */       }
/* 632 */       else if ((object instanceof String))
/* 633 */         write((String)object);
/* 634 */       else if ((object instanceof Entity))
/* 635 */         write((Entity)object);
/* 636 */       else if ((object instanceof ProcessingInstruction))
/* 637 */         write((ProcessingInstruction)object);
/* 638 */       else if ((object instanceof Namespace))
/* 639 */         write((Namespace)object);
/*     */       else
/* 641 */         throw new SAXException("Invalid Node in DOM4J content: " + object);
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void documentLocator(Document document)
/*     */     throws SAXException
/*     */   {
/* 661 */     LocatorImpl locator = new LocatorImpl();
/*     */ 
/* 663 */     String publicID = null;
/* 664 */     String systemID = null;
/* 665 */     DocumentType docType = document.getDocType();
/*     */ 
/* 667 */     if (docType != null) {
/* 668 */       publicID = docType.getPublicID();
/* 669 */       systemID = docType.getSystemID();
/*     */     }
/*     */ 
/* 672 */     if (publicID != null) {
/* 673 */       locator.setPublicId(publicID);
/*     */     }
/*     */ 
/* 676 */     if (systemID != null) {
/* 677 */       locator.setSystemId(systemID);
/*     */     }
/*     */ 
/* 680 */     locator.setLineNumber(-1);
/* 681 */     locator.setColumnNumber(-1);
/*     */ 
/* 683 */     this.contentHandler.setDocumentLocator(locator);
/*     */   }
/*     */ 
/*     */   protected void entityResolver(Document document) throws SAXException {
/* 687 */     if (this.entityResolver != null) {
/* 688 */       DocumentType docType = document.getDocType();
/*     */ 
/* 690 */       if (docType != null) {
/* 691 */         String publicID = docType.getPublicID();
/* 692 */         String systemID = docType.getSystemID();
/*     */ 
/* 694 */         if ((publicID != null) || (systemID != null))
/*     */           try {
/* 696 */             this.entityResolver.resolveEntity(publicID, systemID);
/*     */           } catch (IOException e) {
/* 698 */             throw new SAXException("Could not resolve publicID: " + publicID + " systemID: " + systemID, e);
/*     */           }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void dtdHandler(Document document)
/*     */     throws SAXException
/*     */   {
/*     */   }
/*     */ 
/*     */   protected void startDocument()
/*     */     throws SAXException
/*     */   {
/* 720 */     this.contentHandler.startDocument();
/*     */   }
/*     */ 
/*     */   protected void endDocument() throws SAXException {
/* 724 */     this.contentHandler.endDocument();
/*     */   }
/*     */ 
/*     */   protected void write(Element element, NamespaceStack namespaceStack) throws SAXException
/*     */   {
/* 729 */     int stackSize = namespaceStack.size();
/* 730 */     AttributesImpl namespaceAttributes = startPrefixMapping(element, namespaceStack);
/*     */ 
/* 732 */     startElement(element, namespaceAttributes);
/* 733 */     writeContent(element, namespaceStack);
/* 734 */     endElement(element);
/* 735 */     endPrefixMapping(namespaceStack, stackSize);
/*     */   }
/*     */ 
/*     */   protected AttributesImpl startPrefixMapping(Element element, NamespaceStack namespaceStack)
/*     */     throws SAXException
/*     */   {
/* 754 */     AttributesImpl namespaceAttributes = null;
/*     */ 
/* 757 */     Namespace elementNamespace = element.getNamespace();
/*     */ 
/* 759 */     if ((elementNamespace != null) && (!isIgnoreableNamespace(elementNamespace, namespaceStack)))
/*     */     {
/* 761 */       namespaceStack.push(elementNamespace);
/* 762 */       this.contentHandler.startPrefixMapping(elementNamespace.getPrefix(), elementNamespace.getURI());
/*     */ 
/* 764 */       namespaceAttributes = addNamespaceAttribute(namespaceAttributes, elementNamespace);
/*     */     }
/*     */ 
/* 768 */     List declaredNamespaces = element.declaredNamespaces();
/*     */ 
/* 770 */     int i = 0; for (int size = declaredNamespaces.size(); i < size; i++) {
/* 771 */       Namespace namespace = (Namespace)declaredNamespaces.get(i);
/*     */ 
/* 773 */       if (!isIgnoreableNamespace(namespace, namespaceStack)) {
/* 774 */         namespaceStack.push(namespace);
/* 775 */         this.contentHandler.startPrefixMapping(namespace.getPrefix(), namespace.getURI());
/*     */ 
/* 777 */         namespaceAttributes = addNamespaceAttribute(namespaceAttributes, namespace);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 782 */     return namespaceAttributes;
/*     */   }
/*     */ 
/*     */   protected void endPrefixMapping(NamespaceStack stack, int stackSize)
/*     */     throws SAXException
/*     */   {
/* 799 */     while (stack.size() > stackSize) {
/* 800 */       Namespace namespace = stack.pop();
/*     */ 
/* 802 */       if (namespace != null)
/* 803 */         this.contentHandler.endPrefixMapping(namespace.getPrefix());
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void startElement(Element element, AttributesImpl namespaceAttributes)
/*     */     throws SAXException
/*     */   {
/* 810 */     this.contentHandler.startElement(element.getNamespaceURI(), element.getName(), element.getQualifiedName(), createAttributes(element, namespaceAttributes));
/*     */   }
/*     */ 
/*     */   protected void endElement(Element element)
/*     */     throws SAXException
/*     */   {
/* 816 */     this.contentHandler.endElement(element.getNamespaceURI(), element.getName(), element.getQualifiedName());
/*     */   }
/*     */ 
/*     */   protected Attributes createAttributes(Element element, Attributes namespaceAttributes)
/*     */     throws SAXException
/*     */   {
/* 822 */     this.attributes.clear();
/*     */ 
/* 824 */     if (namespaceAttributes != null) {
/* 825 */       this.attributes.setAttributes(namespaceAttributes);
/*     */     }
/*     */ 
/* 828 */     for (Iterator iter = element.attributeIterator(); iter.hasNext(); ) {
/* 829 */       Attribute attribute = (Attribute)iter.next();
/* 830 */       this.attributes.addAttribute(attribute.getNamespaceURI(), attribute.getName(), attribute.getQualifiedName(), "CDATA", attribute.getValue());
/*     */     }
/*     */ 
/* 835 */     return this.attributes;
/*     */   }
/*     */ 
/*     */   protected AttributesImpl addNamespaceAttribute(AttributesImpl attrs, Namespace namespace)
/*     */   {
/* 852 */     if (this.declareNamespaceAttributes) {
/* 853 */       if (attrs == null) {
/* 854 */         attrs = new AttributesImpl();
/*     */       }
/*     */ 
/* 857 */       String prefix = namespace.getPrefix();
/* 858 */       String qualifiedName = "xmlns";
/*     */ 
/* 860 */       if ((prefix != null) && (prefix.length() > 0)) {
/* 861 */         qualifiedName = "xmlns:" + prefix;
/*     */       }
/*     */ 
/* 864 */       String uri = "";
/* 865 */       String localName = prefix;
/* 866 */       String type = "CDATA";
/* 867 */       String value = namespace.getURI();
/*     */ 
/* 869 */       attrs.addAttribute(uri, localName, qualifiedName, type, value);
/*     */     }
/*     */ 
/* 872 */     return attrs;
/*     */   }
/*     */ 
/*     */   protected boolean isIgnoreableNamespace(Namespace namespace, NamespaceStack namespaceStack)
/*     */   {
/* 889 */     if ((namespace.equals(Namespace.NO_NAMESPACE)) || (namespace.equals(Namespace.XML_NAMESPACE)))
/*     */     {
/* 891 */       return true;
/*     */     }
/*     */ 
/* 894 */     String uri = namespace.getURI();
/*     */ 
/* 896 */     if ((uri == null) || (uri.length() <= 0)) {
/* 897 */       return true;
/*     */     }
/*     */ 
/* 900 */     return namespaceStack.contains(namespace);
/*     */   }
/*     */ 
/*     */   protected void checkForNullHandlers()
/*     */   {
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.io.SAXWriter
 * JD-Core Version:    0.6.2
 */