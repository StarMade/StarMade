/*   1:    */package org.dom4j;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */import java.io.ObjectInputStream;
/*   5:    */import java.io.PrintStream;
/*   6:    */import java.io.Serializable;
/*   7:    */import java.util.List;
/*   8:    */import java.util.Map;
/*   9:    */import org.dom4j.rule.Pattern;
/*  10:    */import org.dom4j.tree.AbstractDocument;
/*  11:    */import org.dom4j.tree.DefaultAttribute;
/*  12:    */import org.dom4j.tree.DefaultCDATA;
/*  13:    */import org.dom4j.tree.DefaultComment;
/*  14:    */import org.dom4j.tree.DefaultDocument;
/*  15:    */import org.dom4j.tree.DefaultDocumentType;
/*  16:    */import org.dom4j.tree.DefaultElement;
/*  17:    */import org.dom4j.tree.DefaultEntity;
/*  18:    */import org.dom4j.tree.DefaultProcessingInstruction;
/*  19:    */import org.dom4j.tree.DefaultText;
/*  20:    */import org.dom4j.tree.QNameCache;
/*  21:    */import org.dom4j.util.SimpleSingleton;
/*  22:    */import org.dom4j.util.SingletonStrategy;
/*  23:    */import org.dom4j.xpath.DefaultXPath;
/*  24:    */import org.dom4j.xpath.XPathPattern;
/*  25:    */import org.jaxen.VariableContext;
/*  26:    */
/*  45:    */public class DocumentFactory
/*  46:    */  implements Serializable
/*  47:    */{
/*  48: 48 */  private static SingletonStrategy singleton = null;
/*  49:    */  
/*  50:    */  protected transient QNameCache cache;
/*  51:    */  
/*  52:    */  private Map xpathNamespaceURIs;
/*  53:    */  
/*  54:    */  private static SingletonStrategy createSingleton()
/*  55:    */  {
/*  56: 56 */    SingletonStrategy result = null;
/*  57:    */    String documentFactoryClassName;
/*  58:    */    try
/*  59:    */    {
/*  60: 60 */      documentFactoryClassName = System.getProperty("org.dom4j.factory", "org.dom4j.DocumentFactory");
/*  61:    */    } catch (Exception e) {
/*  62:    */      String documentFactoryClassName;
/*  63: 63 */      documentFactoryClassName = "org.dom4j.DocumentFactory";
/*  64:    */    }
/*  65:    */    try
/*  66:    */    {
/*  67: 67 */      String singletonClass = System.getProperty("org.dom4j.DocumentFactory.singleton.strategy", "org.dom4j.util.SimpleSingleton");
/*  68:    */      
/*  70: 70 */      Class clazz = Class.forName(singletonClass);
/*  71: 71 */      result = (SingletonStrategy)clazz.newInstance();
/*  72:    */    } catch (Exception e) {
/*  73: 73 */      result = new SimpleSingleton();
/*  74:    */    }
/*  75:    */    
/*  76: 76 */    result.setSingletonClassName(documentFactoryClassName);
/*  77:    */    
/*  78: 78 */    return result;
/*  79:    */  }
/*  80:    */  
/*  81:    */  public DocumentFactory() {
/*  82: 82 */    init();
/*  83:    */  }
/*  84:    */  
/*  92:    */  public static synchronized DocumentFactory getInstance()
/*  93:    */  {
/*  94: 94 */    if (singleton == null) {
/*  95: 95 */      singleton = createSingleton();
/*  96:    */    }
/*  97: 97 */    return (DocumentFactory)singleton.instance();
/*  98:    */  }
/*  99:    */  
/* 100:    */  public Document createDocument()
/* 101:    */  {
/* 102:102 */    DefaultDocument answer = new DefaultDocument();
/* 103:103 */    answer.setDocumentFactory(this);
/* 104:    */    
/* 105:105 */    return answer;
/* 106:    */  }
/* 107:    */  
/* 120:    */  public Document createDocument(String encoding)
/* 121:    */  {
/* 122:122 */    Document answer = createDocument();
/* 123:    */    
/* 124:124 */    if ((answer instanceof AbstractDocument)) {
/* 125:125 */      ((AbstractDocument)answer).setXMLEncoding(encoding);
/* 126:    */    }
/* 127:    */    
/* 128:128 */    return answer;
/* 129:    */  }
/* 130:    */  
/* 131:    */  public Document createDocument(Element rootElement) {
/* 132:132 */    Document answer = createDocument();
/* 133:133 */    answer.setRootElement(rootElement);
/* 134:    */    
/* 135:135 */    return answer;
/* 136:    */  }
/* 137:    */  
/* 138:    */  public DocumentType createDocType(String name, String publicId, String systemId)
/* 139:    */  {
/* 140:140 */    return new DefaultDocumentType(name, publicId, systemId);
/* 141:    */  }
/* 142:    */  
/* 143:    */  public Element createElement(QName qname) {
/* 144:144 */    return new DefaultElement(qname);
/* 145:    */  }
/* 146:    */  
/* 147:    */  public Element createElement(String name) {
/* 148:148 */    return createElement(createQName(name));
/* 149:    */  }
/* 150:    */  
/* 151:    */  public Element createElement(String qualifiedName, String namespaceURI) {
/* 152:152 */    return createElement(createQName(qualifiedName, namespaceURI));
/* 153:    */  }
/* 154:    */  
/* 155:    */  public Attribute createAttribute(Element owner, QName qname, String value) {
/* 156:156 */    return new DefaultAttribute(qname, value);
/* 157:    */  }
/* 158:    */  
/* 159:    */  public Attribute createAttribute(Element owner, String name, String value) {
/* 160:160 */    return createAttribute(owner, createQName(name), value);
/* 161:    */  }
/* 162:    */  
/* 163:    */  public CDATA createCDATA(String text) {
/* 164:164 */    return new DefaultCDATA(text);
/* 165:    */  }
/* 166:    */  
/* 167:    */  public Comment createComment(String text) {
/* 168:168 */    return new DefaultComment(text);
/* 169:    */  }
/* 170:    */  
/* 171:    */  public Text createText(String text) {
/* 172:172 */    if (text == null) {
/* 173:173 */      String msg = "Adding text to an XML document must not be null";
/* 174:174 */      throw new IllegalArgumentException(msg);
/* 175:    */    }
/* 176:    */    
/* 177:177 */    return new DefaultText(text);
/* 178:    */  }
/* 179:    */  
/* 180:    */  public Entity createEntity(String name, String text) {
/* 181:181 */    return new DefaultEntity(name, text);
/* 182:    */  }
/* 183:    */  
/* 184:    */  public Namespace createNamespace(String prefix, String uri) {
/* 185:185 */    return Namespace.get(prefix, uri);
/* 186:    */  }
/* 187:    */  
/* 188:    */  public ProcessingInstruction createProcessingInstruction(String target, String data)
/* 189:    */  {
/* 190:190 */    return new DefaultProcessingInstruction(target, data);
/* 191:    */  }
/* 192:    */  
/* 193:    */  public ProcessingInstruction createProcessingInstruction(String target, Map data)
/* 194:    */  {
/* 195:195 */    return new DefaultProcessingInstruction(target, data);
/* 196:    */  }
/* 197:    */  
/* 198:    */  public QName createQName(String localName, Namespace namespace) {
/* 199:199 */    return this.cache.get(localName, namespace);
/* 200:    */  }
/* 201:    */  
/* 202:    */  public QName createQName(String localName) {
/* 203:203 */    return this.cache.get(localName);
/* 204:    */  }
/* 205:    */  
/* 206:    */  public QName createQName(String name, String prefix, String uri) {
/* 207:207 */    return this.cache.get(name, Namespace.get(prefix, uri));
/* 208:    */  }
/* 209:    */  
/* 210:    */  public QName createQName(String qualifiedName, String uri) {
/* 211:211 */    return this.cache.get(qualifiedName, uri);
/* 212:    */  }
/* 213:    */  
/* 227:    */  public XPath createXPath(String xpathExpression)
/* 228:    */    throws InvalidXPathException
/* 229:    */  {
/* 230:230 */    DefaultXPath xpath = new DefaultXPath(xpathExpression);
/* 231:    */    
/* 232:232 */    if (this.xpathNamespaceURIs != null) {
/* 233:233 */      xpath.setNamespaceURIs(this.xpathNamespaceURIs);
/* 234:    */    }
/* 235:    */    
/* 236:236 */    return xpath;
/* 237:    */  }
/* 238:    */  
/* 252:    */  public XPath createXPath(String xpathExpression, VariableContext variableContext)
/* 253:    */  {
/* 254:254 */    XPath xpath = createXPath(xpathExpression);
/* 255:255 */    xpath.setVariableContext(variableContext);
/* 256:    */    
/* 257:257 */    return xpath;
/* 258:    */  }
/* 259:    */  
/* 274:    */  public NodeFilter createXPathFilter(String xpathFilterExpression, VariableContext variableContext)
/* 275:    */  {
/* 276:276 */    XPath answer = createXPath(xpathFilterExpression);
/* 277:    */    
/* 279:279 */    answer.setVariableContext(variableContext);
/* 280:    */    
/* 281:281 */    return answer;
/* 282:    */  }
/* 283:    */  
/* 295:    */  public NodeFilter createXPathFilter(String xpathFilterExpression)
/* 296:    */  {
/* 297:297 */    return createXPath(xpathFilterExpression);
/* 298:    */  }
/* 299:    */  
/* 313:    */  public Pattern createPattern(String xpathPattern)
/* 314:    */  {
/* 315:315 */    return new XPathPattern(xpathPattern);
/* 316:    */  }
/* 317:    */  
/* 326:    */  public List getQNames()
/* 327:    */  {
/* 328:328 */    return this.cache.getQNames();
/* 329:    */  }
/* 330:    */  
/* 339:    */  public Map getXPathNamespaceURIs()
/* 340:    */  {
/* 341:341 */    return this.xpathNamespaceURIs;
/* 342:    */  }
/* 343:    */  
/* 351:    */  public void setXPathNamespaceURIs(Map namespaceURIs)
/* 352:    */  {
/* 353:353 */    this.xpathNamespaceURIs = namespaceURIs;
/* 354:    */  }
/* 355:    */  
/* 371:    */  protected static DocumentFactory createSingleton(String className)
/* 372:    */  {
/* 373:    */    try
/* 374:    */    {
/* 375:375 */      Class theClass = Class.forName(className, true, DocumentFactory.class.getClassLoader());
/* 376:    */      
/* 378:378 */      return (DocumentFactory)theClass.newInstance();
/* 379:    */    } catch (Throwable e) {
/* 380:380 */      System.out.println("WARNING: Cannot load DocumentFactory: " + className);
/* 381:    */    }
/* 382:    */    
/* 383:383 */    return new DocumentFactory();
/* 384:    */  }
/* 385:    */  
/* 395:    */  protected QName intern(QName qname)
/* 396:    */  {
/* 397:397 */    return this.cache.intern(qname);
/* 398:    */  }
/* 399:    */  
/* 405:    */  protected QNameCache createQNameCache()
/* 406:    */  {
/* 407:407 */    return new QNameCache(this);
/* 408:    */  }
/* 409:    */  
/* 410:    */  private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
/* 411:    */  {
/* 412:412 */    in.defaultReadObject();
/* 413:413 */    init();
/* 414:    */  }
/* 415:    */  
/* 416:    */  protected void init() {
/* 417:417 */    this.cache = createQNameCache();
/* 418:    */  }
/* 419:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.DocumentFactory
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */