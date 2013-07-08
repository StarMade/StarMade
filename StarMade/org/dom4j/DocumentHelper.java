/*   1:    */package org.dom4j;
/*   2:    */
/*   3:    */import java.io.StringReader;
/*   4:    */import java.util.List;
/*   5:    */import java.util.Map;
/*   6:    */import java.util.StringTokenizer;
/*   7:    */import org.dom4j.io.SAXReader;
/*   8:    */import org.dom4j.rule.Pattern;
/*   9:    */import org.jaxen.VariableContext;
/*  10:    */import org.xml.sax.InputSource;
/*  11:    */
/*  32:    */public final class DocumentHelper
/*  33:    */{
/*  34:    */  private static DocumentFactory getDocumentFactory()
/*  35:    */  {
/*  36: 36 */    return DocumentFactory.getInstance();
/*  37:    */  }
/*  38:    */  
/*  39:    */  public static Document createDocument()
/*  40:    */  {
/*  41: 41 */    return getDocumentFactory().createDocument();
/*  42:    */  }
/*  43:    */  
/*  44:    */  public static Document createDocument(Element rootElement) {
/*  45: 45 */    return getDocumentFactory().createDocument(rootElement);
/*  46:    */  }
/*  47:    */  
/*  48:    */  public static Element createElement(QName qname) {
/*  49: 49 */    return getDocumentFactory().createElement(qname);
/*  50:    */  }
/*  51:    */  
/*  52:    */  public static Element createElement(String name) {
/*  53: 53 */    return getDocumentFactory().createElement(name);
/*  54:    */  }
/*  55:    */  
/*  56:    */  public static Attribute createAttribute(Element owner, QName qname, String value)
/*  57:    */  {
/*  58: 58 */    return getDocumentFactory().createAttribute(owner, qname, value);
/*  59:    */  }
/*  60:    */  
/*  61:    */  public static Attribute createAttribute(Element owner, String name, String value)
/*  62:    */  {
/*  63: 63 */    return getDocumentFactory().createAttribute(owner, name, value);
/*  64:    */  }
/*  65:    */  
/*  66:    */  public static CDATA createCDATA(String text) {
/*  67: 67 */    return DocumentFactory.getInstance().createCDATA(text);
/*  68:    */  }
/*  69:    */  
/*  70:    */  public static Comment createComment(String text) {
/*  71: 71 */    return DocumentFactory.getInstance().createComment(text);
/*  72:    */  }
/*  73:    */  
/*  74:    */  public static Text createText(String text) {
/*  75: 75 */    return DocumentFactory.getInstance().createText(text);
/*  76:    */  }
/*  77:    */  
/*  78:    */  public static Entity createEntity(String name, String text) {
/*  79: 79 */    return DocumentFactory.getInstance().createEntity(name, text);
/*  80:    */  }
/*  81:    */  
/*  82:    */  public static Namespace createNamespace(String prefix, String uri) {
/*  83: 83 */    return DocumentFactory.getInstance().createNamespace(prefix, uri);
/*  84:    */  }
/*  85:    */  
/*  86:    */  public static ProcessingInstruction createProcessingInstruction(String pi, String d)
/*  87:    */  {
/*  88: 88 */    return getDocumentFactory().createProcessingInstruction(pi, d);
/*  89:    */  }
/*  90:    */  
/*  91:    */  public static ProcessingInstruction createProcessingInstruction(String pi, Map data)
/*  92:    */  {
/*  93: 93 */    return getDocumentFactory().createProcessingInstruction(pi, data);
/*  94:    */  }
/*  95:    */  
/*  96:    */  public static QName createQName(String localName, Namespace namespace) {
/*  97: 97 */    return getDocumentFactory().createQName(localName, namespace);
/*  98:    */  }
/*  99:    */  
/* 100:    */  public static QName createQName(String localName) {
/* 101:101 */    return getDocumentFactory().createQName(localName);
/* 102:    */  }
/* 103:    */  
/* 118:    */  public static XPath createXPath(String xpathExpression)
/* 119:    */    throws InvalidXPathException
/* 120:    */  {
/* 121:121 */    return getDocumentFactory().createXPath(xpathExpression);
/* 122:    */  }
/* 123:    */  
/* 140:    */  public static XPath createXPath(String xpathExpression, VariableContext context)
/* 141:    */    throws InvalidXPathException
/* 142:    */  {
/* 143:143 */    return getDocumentFactory().createXPath(xpathExpression, context);
/* 144:    */  }
/* 145:    */  
/* 158:    */  public static NodeFilter createXPathFilter(String xpathFilterExpression)
/* 159:    */  {
/* 160:160 */    return getDocumentFactory().createXPathFilter(xpathFilterExpression);
/* 161:    */  }
/* 162:    */  
/* 174:    */  public static Pattern createPattern(String xpathPattern)
/* 175:    */  {
/* 176:176 */    return getDocumentFactory().createPattern(xpathPattern);
/* 177:    */  }
/* 178:    */  
/* 192:    */  public static List selectNodes(String xpathFilterExpression, List nodes)
/* 193:    */  {
/* 194:194 */    XPath xpath = createXPath(xpathFilterExpression);
/* 195:    */    
/* 196:196 */    return xpath.selectNodes(nodes);
/* 197:    */  }
/* 198:    */  
/* 212:    */  public static List selectNodes(String xpathFilterExpression, Node node)
/* 213:    */  {
/* 214:214 */    XPath xpath = createXPath(xpathFilterExpression);
/* 215:    */    
/* 216:216 */    return xpath.selectNodes(node);
/* 217:    */  }
/* 218:    */  
/* 229:    */  public static void sort(List list, String xpathExpression)
/* 230:    */  {
/* 231:231 */    XPath xpath = createXPath(xpathExpression);
/* 232:232 */    xpath.sort(list);
/* 233:    */  }
/* 234:    */  
/* 249:    */  public static void sort(List list, String expression, boolean distinct)
/* 250:    */  {
/* 251:251 */    XPath xpath = createXPath(expression);
/* 252:252 */    xpath.sort(list, distinct);
/* 253:    */  }
/* 254:    */  
/* 267:    */  public static Document parseText(String text)
/* 268:    */    throws DocumentException
/* 269:    */  {
/* 270:270 */    Document result = null;
/* 271:    */    
/* 272:272 */    SAXReader reader = new SAXReader();
/* 273:273 */    String encoding = getEncoding(text);
/* 274:    */    
/* 275:275 */    InputSource source = new InputSource(new StringReader(text));
/* 276:276 */    source.setEncoding(encoding);
/* 277:    */    
/* 278:278 */    result = reader.read(source);
/* 279:    */    
/* 282:282 */    if (result.getXMLEncoding() == null) {
/* 283:283 */      result.setXMLEncoding(encoding);
/* 284:    */    }
/* 285:    */    
/* 286:286 */    return result;
/* 287:    */  }
/* 288:    */  
/* 289:    */  private static String getEncoding(String text) {
/* 290:290 */    String result = null;
/* 291:    */    
/* 292:292 */    String xml = text.trim();
/* 293:    */    
/* 294:294 */    if (xml.startsWith("<?xml")) {
/* 295:295 */      int end = xml.indexOf("?>");
/* 296:296 */      String sub = xml.substring(0, end);
/* 297:297 */      StringTokenizer tokens = new StringTokenizer(sub, " =\"'");
/* 298:    */      
/* 299:299 */      while (tokens.hasMoreTokens()) {
/* 300:300 */        String token = tokens.nextToken();
/* 301:    */        
/* 302:302 */        if ("encoding".equals(token)) {
/* 303:303 */          if (!tokens.hasMoreTokens()) break;
/* 304:304 */          result = tokens.nextToken();break;
/* 305:    */        }
/* 306:    */      }
/* 307:    */    }
/* 308:    */    
/* 312:312 */    return result;
/* 313:    */  }
/* 314:    */  
/* 335:    */  public static Element makeElement(Branch source, String path)
/* 336:    */  {
/* 337:337 */    StringTokenizer tokens = new StringTokenizer(path, "/");
/* 338:    */    
/* 339:    */    Element parent;
/* 340:340 */    if ((source instanceof Document)) {
/* 341:341 */      Document document = (Document)source;
/* 342:342 */      Element parent = document.getRootElement();
/* 343:    */      
/* 346:346 */      String name = tokens.nextToken();
/* 347:    */      
/* 348:348 */      if (parent == null) {
/* 349:349 */        parent = document.addElement(name);
/* 350:    */      }
/* 351:    */    } else {
/* 352:352 */      parent = (Element)source;
/* 353:    */    }
/* 354:    */    
/* 355:355 */    Element element = null;
/* 356:    */    
/* 357:357 */    while (tokens.hasMoreTokens()) {
/* 358:358 */      String name = tokens.nextToken();
/* 359:    */      
/* 360:360 */      if (name.indexOf(':') > 0) {
/* 361:361 */        element = parent.element(parent.getQName(name));
/* 362:    */      } else {
/* 363:363 */        element = parent.element(name);
/* 364:    */      }
/* 365:    */      
/* 366:366 */      if (element == null) {
/* 367:367 */        element = parent.addElement(name);
/* 368:    */      }
/* 369:    */      
/* 370:370 */      parent = element;
/* 371:    */    }
/* 372:    */    
/* 373:373 */    return element;
/* 374:    */  }
/* 375:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.DocumentHelper
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */