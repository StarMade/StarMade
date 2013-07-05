/*     */ package org.dom4j;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.dom4j.rule.Pattern;
/*     */ import org.dom4j.tree.AbstractDocument;
/*     */ import org.dom4j.tree.DefaultAttribute;
/*     */ import org.dom4j.tree.DefaultCDATA;
/*     */ import org.dom4j.tree.DefaultComment;
/*     */ import org.dom4j.tree.DefaultDocument;
/*     */ import org.dom4j.tree.DefaultDocumentType;
/*     */ import org.dom4j.tree.DefaultElement;
/*     */ import org.dom4j.tree.DefaultEntity;
/*     */ import org.dom4j.tree.DefaultProcessingInstruction;
/*     */ import org.dom4j.tree.DefaultText;
/*     */ import org.dom4j.tree.QNameCache;
/*     */ import org.dom4j.util.SimpleSingleton;
/*     */ import org.dom4j.util.SingletonStrategy;
/*     */ import org.dom4j.xpath.DefaultXPath;
/*     */ import org.dom4j.xpath.XPathPattern;
/*     */ import org.jaxen.VariableContext;
/*     */ 
/*     */ public class DocumentFactory
/*     */   implements Serializable
/*     */ {
/*  48 */   private static SingletonStrategy singleton = null;
/*     */   protected transient QNameCache cache;
/*     */   private Map xpathNamespaceURIs;
/*     */ 
/*     */   private static SingletonStrategy createSingleton()
/*     */   {
/*  56 */     SingletonStrategy result = null;
/*     */     String documentFactoryClassName;
/*     */     try
/*     */     {
/*  60 */       documentFactoryClassName = System.getProperty("org.dom4j.factory", "org.dom4j.DocumentFactory");
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*     */       String documentFactoryClassName;
/*  63 */       documentFactoryClassName = "org.dom4j.DocumentFactory";
/*     */     }
/*     */     try
/*     */     {
/*  67 */       String singletonClass = System.getProperty("org.dom4j.DocumentFactory.singleton.strategy", "org.dom4j.util.SimpleSingleton");
/*     */ 
/*  70 */       Class clazz = Class.forName(singletonClass);
/*  71 */       result = (SingletonStrategy)clazz.newInstance();
/*     */     } catch (Exception e) {
/*  73 */       result = new SimpleSingleton();
/*     */     }
/*     */ 
/*  76 */     result.setSingletonClassName(documentFactoryClassName);
/*     */ 
/*  78 */     return result;
/*     */   }
/*     */ 
/*     */   public DocumentFactory() {
/*  82 */     init();
/*     */   }
/*     */ 
/*     */   public static synchronized DocumentFactory getInstance()
/*     */   {
/*  94 */     if (singleton == null) {
/*  95 */       singleton = createSingleton();
/*     */     }
/*  97 */     return (DocumentFactory)singleton.instance();
/*     */   }
/*     */ 
/*     */   public Document createDocument()
/*     */   {
/* 102 */     DefaultDocument answer = new DefaultDocument();
/* 103 */     answer.setDocumentFactory(this);
/*     */ 
/* 105 */     return answer;
/*     */   }
/*     */ 
/*     */   public Document createDocument(String encoding)
/*     */   {
/* 122 */     Document answer = createDocument();
/*     */ 
/* 124 */     if ((answer instanceof AbstractDocument)) {
/* 125 */       ((AbstractDocument)answer).setXMLEncoding(encoding);
/*     */     }
/*     */ 
/* 128 */     return answer;
/*     */   }
/*     */ 
/*     */   public Document createDocument(Element rootElement) {
/* 132 */     Document answer = createDocument();
/* 133 */     answer.setRootElement(rootElement);
/*     */ 
/* 135 */     return answer;
/*     */   }
/*     */ 
/*     */   public DocumentType createDocType(String name, String publicId, String systemId)
/*     */   {
/* 140 */     return new DefaultDocumentType(name, publicId, systemId);
/*     */   }
/*     */ 
/*     */   public Element createElement(QName qname) {
/* 144 */     return new DefaultElement(qname);
/*     */   }
/*     */ 
/*     */   public Element createElement(String name) {
/* 148 */     return createElement(createQName(name));
/*     */   }
/*     */ 
/*     */   public Element createElement(String qualifiedName, String namespaceURI) {
/* 152 */     return createElement(createQName(qualifiedName, namespaceURI));
/*     */   }
/*     */ 
/*     */   public Attribute createAttribute(Element owner, QName qname, String value) {
/* 156 */     return new DefaultAttribute(qname, value);
/*     */   }
/*     */ 
/*     */   public Attribute createAttribute(Element owner, String name, String value) {
/* 160 */     return createAttribute(owner, createQName(name), value);
/*     */   }
/*     */ 
/*     */   public CDATA createCDATA(String text) {
/* 164 */     return new DefaultCDATA(text);
/*     */   }
/*     */ 
/*     */   public Comment createComment(String text) {
/* 168 */     return new DefaultComment(text);
/*     */   }
/*     */ 
/*     */   public Text createText(String text) {
/* 172 */     if (text == null) {
/* 173 */       String msg = "Adding text to an XML document must not be null";
/* 174 */       throw new IllegalArgumentException(msg);
/*     */     }
/*     */ 
/* 177 */     return new DefaultText(text);
/*     */   }
/*     */ 
/*     */   public Entity createEntity(String name, String text) {
/* 181 */     return new DefaultEntity(name, text);
/*     */   }
/*     */ 
/*     */   public Namespace createNamespace(String prefix, String uri) {
/* 185 */     return Namespace.get(prefix, uri);
/*     */   }
/*     */ 
/*     */   public ProcessingInstruction createProcessingInstruction(String target, String data)
/*     */   {
/* 190 */     return new DefaultProcessingInstruction(target, data);
/*     */   }
/*     */ 
/*     */   public ProcessingInstruction createProcessingInstruction(String target, Map data)
/*     */   {
/* 195 */     return new DefaultProcessingInstruction(target, data);
/*     */   }
/*     */ 
/*     */   public QName createQName(String localName, Namespace namespace) {
/* 199 */     return this.cache.get(localName, namespace);
/*     */   }
/*     */ 
/*     */   public QName createQName(String localName) {
/* 203 */     return this.cache.get(localName);
/*     */   }
/*     */ 
/*     */   public QName createQName(String name, String prefix, String uri) {
/* 207 */     return this.cache.get(name, Namespace.get(prefix, uri));
/*     */   }
/*     */ 
/*     */   public QName createQName(String qualifiedName, String uri) {
/* 211 */     return this.cache.get(qualifiedName, uri);
/*     */   }
/*     */ 
/*     */   public XPath createXPath(String xpathExpression)
/*     */     throws InvalidXPathException
/*     */   {
/* 230 */     DefaultXPath xpath = new DefaultXPath(xpathExpression);
/*     */ 
/* 232 */     if (this.xpathNamespaceURIs != null) {
/* 233 */       xpath.setNamespaceURIs(this.xpathNamespaceURIs);
/*     */     }
/*     */ 
/* 236 */     return xpath;
/*     */   }
/*     */ 
/*     */   public XPath createXPath(String xpathExpression, VariableContext variableContext)
/*     */   {
/* 254 */     XPath xpath = createXPath(xpathExpression);
/* 255 */     xpath.setVariableContext(variableContext);
/*     */ 
/* 257 */     return xpath;
/*     */   }
/*     */ 
/*     */   public NodeFilter createXPathFilter(String xpathFilterExpression, VariableContext variableContext)
/*     */   {
/* 276 */     XPath answer = createXPath(xpathFilterExpression);
/*     */ 
/* 279 */     answer.setVariableContext(variableContext);
/*     */ 
/* 281 */     return answer;
/*     */   }
/*     */ 
/*     */   public NodeFilter createXPathFilter(String xpathFilterExpression)
/*     */   {
/* 297 */     return createXPath(xpathFilterExpression);
/*     */   }
/*     */ 
/*     */   public Pattern createPattern(String xpathPattern)
/*     */   {
/* 315 */     return new XPathPattern(xpathPattern);
/*     */   }
/*     */ 
/*     */   public List getQNames()
/*     */   {
/* 328 */     return this.cache.getQNames();
/*     */   }
/*     */ 
/*     */   public Map getXPathNamespaceURIs()
/*     */   {
/* 341 */     return this.xpathNamespaceURIs;
/*     */   }
/*     */ 
/*     */   public void setXPathNamespaceURIs(Map namespaceURIs)
/*     */   {
/* 353 */     this.xpathNamespaceURIs = namespaceURIs;
/*     */   }
/*     */ 
/*     */   protected static DocumentFactory createSingleton(String className)
/*     */   {
/*     */     try
/*     */     {
/* 375 */       Class theClass = Class.forName(className, true, DocumentFactory.class.getClassLoader());
/*     */ 
/* 378 */       return (DocumentFactory)theClass.newInstance();
/*     */     } catch (Throwable e) {
/* 380 */       System.out.println("WARNING: Cannot load DocumentFactory: " + className);
/*     */     }
/*     */ 
/* 383 */     return new DocumentFactory();
/*     */   }
/*     */ 
/*     */   protected QName intern(QName qname)
/*     */   {
/* 397 */     return this.cache.intern(qname);
/*     */   }
/*     */ 
/*     */   protected QNameCache createQNameCache()
/*     */   {
/* 407 */     return new QNameCache(this);
/*     */   }
/*     */ 
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
/*     */   {
/* 412 */     in.defaultReadObject();
/* 413 */     init();
/*     */   }
/*     */ 
/*     */   protected void init() {
/* 417 */     this.cache = createQNameCache();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.DocumentFactory
 * JD-Core Version:    0.6.2
 */