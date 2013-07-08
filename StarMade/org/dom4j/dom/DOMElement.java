/*   1:    */package org.dom4j.dom;
/*   2:    */
/*   3:    */import java.util.ArrayList;
/*   4:    */import java.util.List;
/*   5:    */import org.dom4j.Attribute;
/*   6:    */import org.dom4j.DocumentFactory;
/*   7:    */import org.dom4j.Namespace;
/*   8:    */import org.dom4j.QName;
/*   9:    */import org.dom4j.tree.DefaultElement;
/*  10:    */import org.w3c.dom.Attr;
/*  11:    */import org.w3c.dom.DOMException;
/*  12:    */import org.w3c.dom.Document;
/*  13:    */import org.w3c.dom.Element;
/*  14:    */import org.w3c.dom.NamedNodeMap;
/*  15:    */import org.w3c.dom.Node;
/*  16:    */import org.w3c.dom.NodeList;
/*  17:    */
/*  32:    */public class DOMElement
/*  33:    */  extends DefaultElement
/*  34:    */  implements Element
/*  35:    */{
/*  36: 36 */  private static final DocumentFactory DOCUMENT_FACTORY = ;
/*  37:    */  
/*  38:    */  public DOMElement(String name)
/*  39:    */  {
/*  40: 40 */    super(name);
/*  41:    */  }
/*  42:    */  
/*  43:    */  public DOMElement(QName qname) {
/*  44: 44 */    super(qname);
/*  45:    */  }
/*  46:    */  
/*  47:    */  public DOMElement(QName qname, int attributeCount) {
/*  48: 48 */    super(qname, attributeCount);
/*  49:    */  }
/*  50:    */  
/*  51:    */  public DOMElement(String name, Namespace namespace) {
/*  52: 52 */    super(name, namespace);
/*  53:    */  }
/*  54:    */  
/*  56:    */  public boolean supports(String feature, String version)
/*  57:    */  {
/*  58: 58 */    return DOMNodeHelper.supports(this, feature, version);
/*  59:    */  }
/*  60:    */  
/*  61:    */  public String getNamespaceURI() {
/*  62: 62 */    return getQName().getNamespaceURI();
/*  63:    */  }
/*  64:    */  
/*  65:    */  public String getPrefix() {
/*  66: 66 */    return getQName().getNamespacePrefix();
/*  67:    */  }
/*  68:    */  
/*  69:    */  public void setPrefix(String prefix) throws DOMException {
/*  70: 70 */    DOMNodeHelper.setPrefix(this, prefix);
/*  71:    */  }
/*  72:    */  
/*  73:    */  public String getLocalName() {
/*  74: 74 */    return getQName().getName();
/*  75:    */  }
/*  76:    */  
/*  77:    */  public String getNodeName() {
/*  78: 78 */    return getName();
/*  79:    */  }
/*  80:    */  
/*  82:    */  public String getNodeValue()
/*  83:    */    throws DOMException
/*  84:    */  {
/*  85: 85 */    return null;
/*  86:    */  }
/*  87:    */  
/*  88:    */  public void setNodeValue(String nodeValue) throws DOMException
/*  89:    */  {}
/*  90:    */  
/*  91:    */  public Node getParentNode() {
/*  92: 92 */    return DOMNodeHelper.getParentNode(this);
/*  93:    */  }
/*  94:    */  
/*  95:    */  public NodeList getChildNodes() {
/*  96: 96 */    return DOMNodeHelper.createNodeList(content());
/*  97:    */  }
/*  98:    */  
/*  99:    */  public Node getFirstChild() {
/* 100:100 */    return DOMNodeHelper.asDOMNode(node(0));
/* 101:    */  }
/* 102:    */  
/* 103:    */  public Node getLastChild() {
/* 104:104 */    return DOMNodeHelper.asDOMNode(node(nodeCount() - 1));
/* 105:    */  }
/* 106:    */  
/* 107:    */  public Node getPreviousSibling() {
/* 108:108 */    return DOMNodeHelper.getPreviousSibling(this);
/* 109:    */  }
/* 110:    */  
/* 111:    */  public Node getNextSibling() {
/* 112:112 */    return DOMNodeHelper.getNextSibling(this);
/* 113:    */  }
/* 114:    */  
/* 115:    */  public NamedNodeMap getAttributes() {
/* 116:116 */    return new DOMAttributeNodeMap(this);
/* 117:    */  }
/* 118:    */  
/* 119:    */  public Document getOwnerDocument() {
/* 120:120 */    return DOMNodeHelper.getOwnerDocument(this);
/* 121:    */  }
/* 122:    */  
/* 123:    */  public Node insertBefore(Node newChild, Node refChild) throws DOMException
/* 124:    */  {
/* 125:125 */    checkNewChildNode(newChild);
/* 126:    */    
/* 127:127 */    return DOMNodeHelper.insertBefore(this, newChild, refChild);
/* 128:    */  }
/* 129:    */  
/* 130:    */  public Node replaceChild(Node newChild, Node oldChild) throws DOMException
/* 131:    */  {
/* 132:132 */    checkNewChildNode(newChild);
/* 133:    */    
/* 134:134 */    return DOMNodeHelper.replaceChild(this, newChild, oldChild);
/* 135:    */  }
/* 136:    */  
/* 137:    */  public Node removeChild(Node oldChild) throws DOMException
/* 138:    */  {
/* 139:139 */    return DOMNodeHelper.removeChild(this, oldChild);
/* 140:    */  }
/* 141:    */  
/* 142:    */  public Node appendChild(Node newChild) throws DOMException
/* 143:    */  {
/* 144:144 */    checkNewChildNode(newChild);
/* 145:    */    
/* 146:146 */    return DOMNodeHelper.appendChild(this, newChild);
/* 147:    */  }
/* 148:    */  
/* 149:    */  private void checkNewChildNode(Node newChild) throws DOMException
/* 150:    */  {
/* 151:151 */    int nodeType = newChild.getNodeType();
/* 152:    */    
/* 153:153 */    if ((nodeType != 1) && (nodeType != 3) && (nodeType != 8) && (nodeType != 7) && (nodeType != 4) && (nodeType != 5))
/* 154:    */    {
/* 158:158 */      throw new DOMException((short)3, "Given node cannot be a child of element");
/* 159:    */    }
/* 160:    */  }
/* 161:    */  
/* 162:    */  public boolean hasChildNodes()
/* 163:    */  {
/* 164:164 */    return nodeCount() > 0;
/* 165:    */  }
/* 166:    */  
/* 167:    */  public Node cloneNode(boolean deep) {
/* 168:168 */    return DOMNodeHelper.cloneNode(this, deep);
/* 169:    */  }
/* 170:    */  
/* 171:    */  public boolean isSupported(String feature, String version) {
/* 172:172 */    return DOMNodeHelper.isSupported(this, feature, version);
/* 173:    */  }
/* 174:    */  
/* 175:    */  public boolean hasAttributes() {
/* 176:176 */    return DOMNodeHelper.hasAttributes(this);
/* 177:    */  }
/* 178:    */  
/* 180:    */  public String getTagName()
/* 181:    */  {
/* 182:182 */    return getName();
/* 183:    */  }
/* 184:    */  
/* 185:    */  public String getAttribute(String name) {
/* 186:186 */    String answer = attributeValue(name);
/* 187:    */    
/* 188:188 */    return answer != null ? answer : "";
/* 189:    */  }
/* 190:    */  
/* 191:    */  public void setAttribute(String name, String value) throws DOMException {
/* 192:192 */    addAttribute(name, value);
/* 193:    */  }
/* 194:    */  
/* 195:    */  public void removeAttribute(String name) throws DOMException {
/* 196:196 */    Attribute attribute = attribute(name);
/* 197:    */    
/* 198:198 */    if (attribute != null) {
/* 199:199 */      remove(attribute);
/* 200:    */    }
/* 201:    */  }
/* 202:    */  
/* 203:    */  public Attr getAttributeNode(String name) {
/* 204:204 */    return DOMNodeHelper.asDOMAttr(attribute(name));
/* 205:    */  }
/* 206:    */  
/* 207:    */  public Attr setAttributeNode(Attr newAttr) throws DOMException
/* 208:    */  {
/* 209:209 */    if (isReadOnly()) {
/* 210:210 */      throw new DOMException((short)7, "No modification allowed");
/* 211:    */    }
/* 212:    */    
/* 214:214 */    Attribute attribute = attribute(newAttr);
/* 215:    */    
/* 216:216 */    if (attribute != newAttr) {
/* 217:217 */      if (newAttr.getOwnerElement() != null) {
/* 218:218 */        throw new DOMException((short)10, "Attribute is already in use");
/* 219:    */      }
/* 220:    */      
/* 222:222 */      Attribute newAttribute = createAttribute(newAttr);
/* 223:    */      
/* 224:224 */      if (attribute != null) {
/* 225:225 */        attribute.detach();
/* 226:    */      }
/* 227:    */      
/* 228:228 */      add(newAttribute);
/* 229:    */    }
/* 230:    */    
/* 231:231 */    return DOMNodeHelper.asDOMAttr(attribute);
/* 232:    */  }
/* 233:    */  
/* 234:    */  public Attr removeAttributeNode(Attr oldAttr) throws DOMException
/* 235:    */  {
/* 236:236 */    Attribute attribute = attribute(oldAttr);
/* 237:    */    
/* 238:238 */    if (attribute != null) {
/* 239:239 */      attribute.detach();
/* 240:    */      
/* 241:241 */      return DOMNodeHelper.asDOMAttr(attribute);
/* 242:    */    }
/* 243:243 */    throw new DOMException((short)8, "No such attribute");
/* 244:    */  }
/* 245:    */  
/* 247:    */  public String getAttributeNS(String namespaceURI, String localName)
/* 248:    */  {
/* 249:249 */    Attribute attribute = attribute(namespaceURI, localName);
/* 250:    */    
/* 251:251 */    if (attribute != null) {
/* 252:252 */      String answer = attribute.getValue();
/* 253:    */      
/* 254:254 */      if (answer != null) {
/* 255:255 */        return answer;
/* 256:    */      }
/* 257:    */    }
/* 258:    */    
/* 259:259 */    return "";
/* 260:    */  }
/* 261:    */  
/* 262:    */  public void setAttributeNS(String namespaceURI, String qualifiedName, String value) throws DOMException
/* 263:    */  {
/* 264:264 */    Attribute attribute = attribute(namespaceURI, qualifiedName);
/* 265:    */    
/* 266:266 */    if (attribute != null) {
/* 267:267 */      attribute.setValue(value);
/* 268:    */    } else {
/* 269:269 */      QName qname = getQName(namespaceURI, qualifiedName);
/* 270:270 */      addAttribute(qname, value);
/* 271:    */    }
/* 272:    */  }
/* 273:    */  
/* 274:    */  public void removeAttributeNS(String namespaceURI, String localName) throws DOMException
/* 275:    */  {
/* 276:276 */    Attribute attribute = attribute(namespaceURI, localName);
/* 277:    */    
/* 278:278 */    if (attribute != null) {
/* 279:279 */      remove(attribute);
/* 280:    */    }
/* 281:    */  }
/* 282:    */  
/* 283:    */  public Attr getAttributeNodeNS(String namespaceURI, String localName)
/* 284:    */  {
/* 285:285 */    Attribute attribute = attribute(namespaceURI, localName);
/* 286:    */    
/* 287:287 */    if (attribute != null) {
/* 288:288 */      DOMNodeHelper.asDOMAttr(attribute);
/* 289:    */    }
/* 290:    */    
/* 291:291 */    return null;
/* 292:    */  }
/* 293:    */  
/* 294:    */  public Attr setAttributeNodeNS(Attr newAttr) throws DOMException
/* 295:    */  {
/* 296:296 */    Attribute attribute = attribute(newAttr.getNamespaceURI(), newAttr.getLocalName());
/* 297:    */    
/* 299:299 */    if (attribute != null) {
/* 300:300 */      attribute.setValue(newAttr.getValue());
/* 301:    */    } else {
/* 302:302 */      attribute = createAttribute(newAttr);
/* 303:303 */      add(attribute);
/* 304:    */    }
/* 305:    */    
/* 306:306 */    return DOMNodeHelper.asDOMAttr(attribute);
/* 307:    */  }
/* 308:    */  
/* 309:    */  public NodeList getElementsByTagName(String name) {
/* 310:310 */    ArrayList list = new ArrayList();
/* 311:311 */    DOMNodeHelper.appendElementsByTagName(list, this, name);
/* 312:    */    
/* 313:313 */    return DOMNodeHelper.createNodeList(list);
/* 314:    */  }
/* 315:    */  
/* 316:    */  public NodeList getElementsByTagNameNS(String namespace, String lName) {
/* 317:317 */    ArrayList list = new ArrayList();
/* 318:318 */    DOMNodeHelper.appendElementsByTagNameNS(list, this, namespace, lName);
/* 319:    */    
/* 320:320 */    return DOMNodeHelper.createNodeList(list);
/* 321:    */  }
/* 322:    */  
/* 323:    */  public boolean hasAttribute(String name) {
/* 324:324 */    return attribute(name) != null;
/* 325:    */  }
/* 326:    */  
/* 327:    */  public boolean hasAttributeNS(String namespaceURI, String localName) {
/* 328:328 */    return attribute(namespaceURI, localName) != null;
/* 329:    */  }
/* 330:    */  
/* 332:    */  protected DocumentFactory getDocumentFactory()
/* 333:    */  {
/* 334:334 */    DocumentFactory factory = getQName().getDocumentFactory();
/* 335:    */    
/* 336:336 */    return factory != null ? factory : DOCUMENT_FACTORY;
/* 337:    */  }
/* 338:    */  
/* 339:    */  protected Attribute attribute(Attr attr) {
/* 340:340 */    return attribute(DOCUMENT_FACTORY.createQName(attr.getLocalName(), attr.getPrefix(), attr.getNamespaceURI()));
/* 341:    */  }
/* 342:    */  
/* 343:    */  protected Attribute attribute(String namespaceURI, String localName)
/* 344:    */  {
/* 345:345 */    List attributes = attributeList();
/* 346:346 */    int size = attributes.size();
/* 347:    */    
/* 348:348 */    for (int i = 0; i < size; i++) {
/* 349:349 */      Attribute attribute = (Attribute)attributes.get(i);
/* 350:    */      
/* 351:351 */      if ((localName.equals(attribute.getName())) && (((namespaceURI != null) && (namespaceURI.length() != 0)) || ((attribute.getNamespaceURI() == null) || (attribute.getNamespaceURI().length() == 0) || ((namespaceURI != null) && (namespaceURI.equals(attribute.getNamespaceURI()))))))
/* 352:    */      {
/* 357:357 */        return attribute;
/* 358:    */      }
/* 359:    */    }
/* 360:    */    
/* 361:361 */    return null;
/* 362:    */  }
/* 363:    */  
/* 364:    */  protected Attribute createAttribute(Attr newAttr) {
/* 365:365 */    QName qname = null;
/* 366:366 */    String name = newAttr.getLocalName();
/* 367:    */    
/* 368:368 */    if (name != null) {
/* 369:369 */      String prefix = newAttr.getPrefix();
/* 370:370 */      String uri = newAttr.getNamespaceURI();
/* 371:371 */      qname = getDocumentFactory().createQName(name, prefix, uri);
/* 372:    */    } else {
/* 373:373 */      name = newAttr.getName();
/* 374:374 */      qname = getDocumentFactory().createQName(name);
/* 375:    */    }
/* 376:    */    
/* 377:377 */    return new DOMAttribute(qname, newAttr.getValue());
/* 378:    */  }
/* 379:    */  
/* 380:    */  protected QName getQName(String namespace, String qualifiedName) {
/* 381:381 */    int index = qualifiedName.indexOf(':');
/* 382:382 */    String prefix = "";
/* 383:383 */    String localName = qualifiedName;
/* 384:    */    
/* 385:385 */    if (index >= 0) {
/* 386:386 */      prefix = qualifiedName.substring(0, index);
/* 387:387 */      localName = qualifiedName.substring(index + 1);
/* 388:    */    }
/* 389:    */    
/* 390:390 */    return getDocumentFactory().createQName(localName, prefix, namespace);
/* 391:    */  }
/* 392:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.dom.DOMElement
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */