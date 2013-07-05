/*     */ package org.dom4j;
/*     */ 
/*     */ import java.io.StringReader;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.StringTokenizer;
/*     */ import org.dom4j.io.SAXReader;
/*     */ import org.dom4j.rule.Pattern;
/*     */ import org.jaxen.VariableContext;
/*     */ import org.xml.sax.InputSource;
/*     */ 
/*     */ public final class DocumentHelper
/*     */ {
/*     */   private static DocumentFactory getDocumentFactory()
/*     */   {
/*  36 */     return DocumentFactory.getInstance();
/*     */   }
/*     */ 
/*     */   public static Document createDocument()
/*     */   {
/*  41 */     return getDocumentFactory().createDocument();
/*     */   }
/*     */ 
/*     */   public static Document createDocument(Element rootElement) {
/*  45 */     return getDocumentFactory().createDocument(rootElement);
/*     */   }
/*     */ 
/*     */   public static Element createElement(QName qname) {
/*  49 */     return getDocumentFactory().createElement(qname);
/*     */   }
/*     */ 
/*     */   public static Element createElement(String name) {
/*  53 */     return getDocumentFactory().createElement(name);
/*     */   }
/*     */ 
/*     */   public static Attribute createAttribute(Element owner, QName qname, String value)
/*     */   {
/*  58 */     return getDocumentFactory().createAttribute(owner, qname, value);
/*     */   }
/*     */ 
/*     */   public static Attribute createAttribute(Element owner, String name, String value)
/*     */   {
/*  63 */     return getDocumentFactory().createAttribute(owner, name, value);
/*     */   }
/*     */ 
/*     */   public static CDATA createCDATA(String text) {
/*  67 */     return DocumentFactory.getInstance().createCDATA(text);
/*     */   }
/*     */ 
/*     */   public static Comment createComment(String text) {
/*  71 */     return DocumentFactory.getInstance().createComment(text);
/*     */   }
/*     */ 
/*     */   public static Text createText(String text) {
/*  75 */     return DocumentFactory.getInstance().createText(text);
/*     */   }
/*     */ 
/*     */   public static Entity createEntity(String name, String text) {
/*  79 */     return DocumentFactory.getInstance().createEntity(name, text);
/*     */   }
/*     */ 
/*     */   public static Namespace createNamespace(String prefix, String uri) {
/*  83 */     return DocumentFactory.getInstance().createNamespace(prefix, uri);
/*     */   }
/*     */ 
/*     */   public static ProcessingInstruction createProcessingInstruction(String pi, String d)
/*     */   {
/*  88 */     return getDocumentFactory().createProcessingInstruction(pi, d);
/*     */   }
/*     */ 
/*     */   public static ProcessingInstruction createProcessingInstruction(String pi, Map data)
/*     */   {
/*  93 */     return getDocumentFactory().createProcessingInstruction(pi, data);
/*     */   }
/*     */ 
/*     */   public static QName createQName(String localName, Namespace namespace) {
/*  97 */     return getDocumentFactory().createQName(localName, namespace);
/*     */   }
/*     */ 
/*     */   public static QName createQName(String localName) {
/* 101 */     return getDocumentFactory().createQName(localName);
/*     */   }
/*     */ 
/*     */   public static XPath createXPath(String xpathExpression)
/*     */     throws InvalidXPathException
/*     */   {
/* 121 */     return getDocumentFactory().createXPath(xpathExpression);
/*     */   }
/*     */ 
/*     */   public static XPath createXPath(String xpathExpression, VariableContext context)
/*     */     throws InvalidXPathException
/*     */   {
/* 143 */     return getDocumentFactory().createXPath(xpathExpression, context);
/*     */   }
/*     */ 
/*     */   public static NodeFilter createXPathFilter(String xpathFilterExpression)
/*     */   {
/* 160 */     return getDocumentFactory().createXPathFilter(xpathFilterExpression);
/*     */   }
/*     */ 
/*     */   public static Pattern createPattern(String xpathPattern)
/*     */   {
/* 176 */     return getDocumentFactory().createPattern(xpathPattern);
/*     */   }
/*     */ 
/*     */   public static List selectNodes(String xpathFilterExpression, List nodes)
/*     */   {
/* 194 */     XPath xpath = createXPath(xpathFilterExpression);
/*     */ 
/* 196 */     return xpath.selectNodes(nodes);
/*     */   }
/*     */ 
/*     */   public static List selectNodes(String xpathFilterExpression, Node node)
/*     */   {
/* 214 */     XPath xpath = createXPath(xpathFilterExpression);
/*     */ 
/* 216 */     return xpath.selectNodes(node);
/*     */   }
/*     */ 
/*     */   public static void sort(List list, String xpathExpression)
/*     */   {
/* 231 */     XPath xpath = createXPath(xpathExpression);
/* 232 */     xpath.sort(list);
/*     */   }
/*     */ 
/*     */   public static void sort(List list, String expression, boolean distinct)
/*     */   {
/* 251 */     XPath xpath = createXPath(expression);
/* 252 */     xpath.sort(list, distinct);
/*     */   }
/*     */ 
/*     */   public static Document parseText(String text)
/*     */     throws DocumentException
/*     */   {
/* 270 */     Document result = null;
/*     */ 
/* 272 */     SAXReader reader = new SAXReader();
/* 273 */     String encoding = getEncoding(text);
/*     */ 
/* 275 */     InputSource source = new InputSource(new StringReader(text));
/* 276 */     source.setEncoding(encoding);
/*     */ 
/* 278 */     result = reader.read(source);
/*     */ 
/* 282 */     if (result.getXMLEncoding() == null) {
/* 283 */       result.setXMLEncoding(encoding);
/*     */     }
/*     */ 
/* 286 */     return result;
/*     */   }
/*     */ 
/*     */   private static String getEncoding(String text) {
/* 290 */     String result = null;
/*     */ 
/* 292 */     String xml = text.trim();
/*     */ 
/* 294 */     if (xml.startsWith("<?xml")) {
/* 295 */       int end = xml.indexOf("?>");
/* 296 */       String sub = xml.substring(0, end);
/* 297 */       StringTokenizer tokens = new StringTokenizer(sub, " =\"'");
/*     */ 
/* 299 */       while (tokens.hasMoreTokens()) {
/* 300 */         String token = tokens.nextToken();
/*     */ 
/* 302 */         if ("encoding".equals(token)) {
/* 303 */           if (!tokens.hasMoreTokens()) break;
/* 304 */           result = tokens.nextToken(); break;
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 312 */     return result;
/*     */   }
/*     */ 
/*     */   public static Element makeElement(Branch source, String path)
/*     */   {
/* 337 */     StringTokenizer tokens = new StringTokenizer(path, "/");
/*     */     Element parent;
/* 340 */     if ((source instanceof Document)) {
/* 341 */       Document document = (Document)source;
/* 342 */       Element parent = document.getRootElement();
/*     */ 
/* 346 */       String name = tokens.nextToken();
/*     */ 
/* 348 */       if (parent == null)
/* 349 */         parent = document.addElement(name);
/*     */     }
/*     */     else {
/* 352 */       parent = (Element)source;
/*     */     }
/*     */ 
/* 355 */     Element element = null;
/*     */ 
/* 357 */     while (tokens.hasMoreTokens()) {
/* 358 */       String name = tokens.nextToken();
/*     */ 
/* 360 */       if (name.indexOf(':') > 0)
/* 361 */         element = parent.element(parent.getQName(name));
/*     */       else {
/* 363 */         element = parent.element(name);
/*     */       }
/*     */ 
/* 366 */       if (element == null) {
/* 367 */         element = parent.addElement(name);
/*     */       }
/*     */ 
/* 370 */       parent = element;
/*     */     }
/*     */ 
/* 373 */     return element;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.DocumentHelper
 * JD-Core Version:    0.6.2
 */