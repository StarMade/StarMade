/*   1:    */package org.jaxen.xom;
/*   2:    */
/*   3:    */import java.util.Collection;
/*   4:    */import java.util.HashMap;
/*   5:    */import java.util.Iterator;
/*   6:    */import java.util.Map;
/*   7:    */import nu.xom.Attribute;
/*   8:    */import nu.xom.Builder;
/*   9:    */import nu.xom.Comment;
/*  10:    */import nu.xom.Document;
/*  11:    */import nu.xom.Element;
/*  12:    */import nu.xom.Node;
/*  13:    */import nu.xom.NodeFactory;
/*  14:    */import nu.xom.ParentNode;
/*  15:    */import nu.xom.ProcessingInstruction;
/*  16:    */import nu.xom.Text;
/*  17:    */import org.jaxen.BaseXPath;
/*  18:    */import org.jaxen.DefaultNavigator;
/*  19:    */import org.jaxen.FunctionCallException;
/*  20:    */import org.jaxen.JaxenConstants;
/*  21:    */import org.jaxen.UnsupportedAxisException;
/*  22:    */import org.jaxen.XPath;
/*  23:    */import org.jaxen.saxpath.SAXPathException;
/*  24:    */import org.jaxen.util.SingleObjectIterator;
/*  25:    */
/*  89:    */public class DocumentNavigator
/*  90:    */  extends DefaultNavigator
/*  91:    */{
/*  92:    */  private static final long serialVersionUID = 3159311338575942877L;
/*  93:    */  
/*  94:    */  public boolean isAttribute(Object o)
/*  95:    */  {
/*  96: 96 */    return o instanceof Attribute;
/*  97:    */  }
/*  98:    */  
/*  99:    */  public boolean isComment(Object o) {
/* 100:100 */    return o instanceof Comment;
/* 101:    */  }
/* 102:    */  
/* 103:    */  public boolean isDocument(Object o) {
/* 104:104 */    return o instanceof Document;
/* 105:    */  }
/* 106:    */  
/* 107:    */  public boolean isElement(Object o) {
/* 108:108 */    return o instanceof Element;
/* 109:    */  }
/* 110:    */  
/* 111:    */  public boolean isNamespace(Object o) {
/* 112:112 */    return o instanceof XPathNamespace;
/* 113:    */  }
/* 114:    */  
/* 115:    */  public boolean isProcessingInstruction(Object o) {
/* 116:116 */    return o instanceof ProcessingInstruction;
/* 117:    */  }
/* 118:    */  
/* 119:    */  public boolean isText(Object o) {
/* 120:120 */    return o instanceof Text;
/* 121:    */  }
/* 122:    */  
/* 124:    */  public String getAttributeName(Object o)
/* 125:    */  {
/* 126:126 */    return isAttribute(o) ? ((Attribute)o).getLocalName() : null;
/* 127:    */  }
/* 128:    */  
/* 129:    */  public String getAttributeNamespaceUri(Object o) {
/* 130:130 */    return isAttribute(o) ? ((Attribute)o).getNamespaceURI() : null;
/* 131:    */  }
/* 132:    */  
/* 133:    */  public String getAttributeQName(Object o) {
/* 134:134 */    return isAttribute(o) ? ((Attribute)o).getQualifiedName() : null;
/* 135:    */  }
/* 136:    */  
/* 137:    */  public String getAttributeStringValue(Object o) {
/* 138:138 */    return isAttribute(o) ? ((Attribute)o).getValue() : null;
/* 139:    */  }
/* 140:    */  
/* 142:    */  public String getCommentStringValue(Object o)
/* 143:    */  {
/* 144:144 */    return isComment(o) ? ((Comment)o).getValue() : null;
/* 145:    */  }
/* 146:    */  
/* 147:    */  public String getElementName(Object o) {
/* 148:148 */    return isElement(o) ? ((Element)o).getLocalName() : null;
/* 149:    */  }
/* 150:    */  
/* 151:    */  public String getElementNamespaceUri(Object o) {
/* 152:152 */    return isElement(o) ? ((Element)o).getNamespaceURI() : null;
/* 153:    */  }
/* 154:    */  
/* 155:    */  public String getElementQName(Object o) {
/* 156:156 */    return isElement(o) ? ((Element)o).getQualifiedName() : null;
/* 157:    */  }
/* 158:    */  
/* 159:    */  public String getElementStringValue(Object o) {
/* 160:160 */    return (o instanceof Node) ? ((Node)o).getValue() : null;
/* 161:    */  }
/* 162:    */  
/* 164:    */  public String getNamespacePrefix(Object o)
/* 165:    */  {
/* 166:166 */    if (isElement(o))
/* 167:167 */      return ((Element)o).getNamespacePrefix();
/* 168:168 */    if (isAttribute(o))
/* 169:169 */      return ((Attribute)o).getNamespacePrefix();
/* 170:170 */    if ((o instanceof XPathNamespace)) {
/* 171:171 */      return ((XPathNamespace)o).getNamespacePrefix();
/* 172:    */    }
/* 173:173 */    return null;
/* 174:    */  }
/* 175:    */  
/* 176:    */  public String getNamespaceStringValue(Object o) {
/* 177:177 */    if (isElement(o))
/* 178:178 */      return ((Element)o).getNamespaceURI();
/* 179:179 */    if (isAttribute(o))
/* 180:180 */      return ((Attribute)o).getNamespaceURI();
/* 181:181 */    if ((o instanceof XPathNamespace)) {
/* 182:182 */      return ((XPathNamespace)o).getNamespaceURI();
/* 183:    */    }
/* 184:184 */    return null;
/* 185:    */  }
/* 186:    */  
/* 188:    */  public String getTextStringValue(Object o)
/* 189:    */  {
/* 190:190 */    return (o instanceof Text) ? ((Text)o).getValue() : null;
/* 191:    */  }
/* 192:    */  
/* 193:    */  public Object getDocument(String s) throws FunctionCallException
/* 194:    */  {
/* 195:    */    try
/* 196:    */    {
/* 197:197 */      return new Builder(new NodeFactory()).build(s);
/* 198:    */    } catch (Exception pe) {
/* 199:199 */      throw new FunctionCallException(pe);
/* 200:    */    }
/* 201:    */  }
/* 202:    */  
/* 203:    */  public Object getDocumentNode(Object o) {
/* 204:204 */    ParentNode parent = null;
/* 205:205 */    if ((o instanceof ParentNode)) {
/* 206:206 */      parent = (ParentNode)o;
/* 207:207 */    } else if ((o instanceof Node)) {
/* 208:208 */      parent = ((Node)o).getParent();
/* 209:    */    }
/* 210:210 */    return parent.getDocument();
/* 211:    */  }
/* 212:    */  
/* 213:    */  private static abstract class IndexIterator
/* 214:    */    implements Iterator
/* 215:    */  {
/* 216:216 */    private Object o = null;
/* 217:217 */    private int pos = 0; private int end = -1;
/* 218:    */    
/* 219:219 */    public IndexIterator(Object o, int pos, int end) { this.o = o;
/* 220:220 */      this.pos = pos;
/* 221:221 */      this.end = end;
/* 222:    */    }
/* 223:    */    
/* 224:224 */    public boolean hasNext() { return this.pos < this.end; }
/* 225:    */    
/* 226:    */    public abstract Object get(Object paramObject, int paramInt);
/* 227:    */    
/* 228:    */    public Object next() {
/* 229:229 */      return get(this.o, this.pos++);
/* 230:    */    }
/* 231:    */    
/* 232:    */    public void remove() {
/* 233:233 */      throw new UnsupportedOperationException();
/* 234:    */    }
/* 235:    */  }
/* 236:    */  
/* 238:    */  public Iterator getAttributeAxisIterator(Object o)
/* 239:    */  {
/* 240:240 */    if (isElement(o)) {
/* 241:241 */      new IndexIterator(o, 0, ((Element)o).getAttributeCount()) {
/* 242:    */        public Object get(Object o, int i) {
/* 243:243 */          return ((Element)o).getAttribute(i);
/* 244:    */        }
/* 245:    */      };
/* 246:    */    }
/* 247:247 */    return JaxenConstants.EMPTY_ITERATOR;
/* 248:    */  }
/* 249:    */  
/* 250:    */  public Iterator getChildAxisIterator(Object o) {
/* 251:251 */    if ((isElement(o)) || ((o instanceof Document))) {
/* 252:252 */      new IndexIterator(o, 0, ((ParentNode)o).getChildCount()) {
/* 253:    */        public Object get(Object o, int i) {
/* 254:254 */          return ((ParentNode)o).getChild(i);
/* 255:    */        }
/* 256:    */      };
/* 257:    */    }
/* 258:258 */    return JaxenConstants.EMPTY_ITERATOR;
/* 259:    */  }
/* 260:    */  
/* 262:    */  public Iterator getParentAxisIterator(Object o)
/* 263:    */  {
/* 264:264 */    Object parent = null;
/* 265:265 */    if ((o instanceof Node)) {
/* 266:266 */      parent = ((Node)o).getParent();
/* 267:267 */    } else if (isNamespace(o)) {
/* 268:268 */      parent = ((XPathNamespace)o).getElement();
/* 269:    */    }
/* 270:270 */    return parent != null ? new SingleObjectIterator(parent) : null;
/* 271:    */  }
/* 272:    */  
/* 273:    */  public Object getParentNode(Object o) {
/* 274:274 */    return (o instanceof Node) ? ((Node)o).getParent() : null;
/* 275:    */  }
/* 276:    */  
/* 277:    */  public Iterator getPrecedingAxisIterator(Object o)
/* 278:    */    throws UnsupportedAxisException
/* 279:    */  {
/* 280:280 */    return super.getPrecedingAxisIterator(o);
/* 281:    */  }
/* 282:    */  
/* 283:    */  public Iterator getPrecedingSiblingAxisIterator(Object o) throws UnsupportedAxisException {
/* 284:284 */    return super.getPrecedingSiblingAxisIterator(o);
/* 285:    */  }
/* 286:    */  
/* 288:    */  public String getProcessingInstructionData(Object o)
/* 289:    */  {
/* 290:290 */    return (o instanceof ProcessingInstruction) ? ((ProcessingInstruction)o).getValue() : null;
/* 291:    */  }
/* 292:    */  
/* 293:    */  public String getProcessingInstructionTarget(Object o) {
/* 294:294 */    return (o instanceof ProcessingInstruction) ? ((ProcessingInstruction)o).getTarget() : null;
/* 295:    */  }
/* 296:    */  
/* 298:    */  public String translateNamespacePrefixToUri(String s, Object o)
/* 299:    */  {
/* 300:300 */    Element element = null;
/* 301:301 */    if ((o instanceof Element)) {
/* 302:302 */      element = (Element)o;
/* 303:303 */    } else if (!(o instanceof ParentNode))
/* 304:    */    {
/* 305:305 */      if ((o instanceof Node)) {
/* 306:306 */        element = (Element)((Node)o).getParent();
/* 307:    */      }
/* 308:308 */      else if ((o instanceof XPathNamespace))
/* 309:    */      {
/* 310:310 */        element = ((XPathNamespace)o).getElement(); }
/* 311:    */    }
/* 312:312 */    if (element != null) {
/* 313:313 */      return element.getNamespaceURI(s);
/* 314:    */    }
/* 315:315 */    return null;
/* 316:    */  }
/* 317:    */  
/* 318:    */  public XPath parseXPath(String s)
/* 319:    */    throws SAXPathException
/* 320:    */  {
/* 321:321 */    return new BaseXPath(s, this);
/* 322:    */  }
/* 323:    */  
/* 326:    */  private static class XPathNamespace
/* 327:    */  {
/* 328:    */    private Element element;
/* 329:    */    
/* 331:    */    private String uri;
/* 332:    */    
/* 334:    */    private String prefix;
/* 335:    */    
/* 337:    */    public XPathNamespace(Element elt, String uri, String prefix)
/* 338:    */    {
/* 339:339 */      this.element = elt;
/* 340:340 */      this.uri = uri;
/* 341:341 */      this.prefix = prefix;
/* 342:    */    }
/* 343:    */    
/* 348:    */    public Element getElement()
/* 349:    */    {
/* 350:350 */      return this.element;
/* 351:    */    }
/* 352:    */    
/* 353:    */    public String getNamespaceURI()
/* 354:    */    {
/* 355:355 */      return this.uri;
/* 356:    */    }
/* 357:    */    
/* 358:    */    public String getNamespacePrefix()
/* 359:    */    {
/* 360:360 */      return this.prefix;
/* 361:    */    }
/* 362:    */    
/* 363:    */    public String toString()
/* 364:    */    {
/* 365:365 */      return "[xmlns:" + this.prefix + "=\"" + this.uri + "\", element=" + this.element.getLocalName() + "]";
/* 366:    */    }
/* 367:    */  }
/* 368:    */  
/* 373:    */  private boolean addNamespaceForElement(Element elt, String uri, String prefix, Map map)
/* 374:    */  {
/* 375:375 */    if ((uri != null) && (uri.length() > 0) && (!map.containsKey(prefix))) {
/* 376:376 */      map.put(prefix, new XPathNamespace(elt, uri, prefix));
/* 377:377 */      return true;
/* 378:    */    }
/* 379:379 */    return false;
/* 380:    */  }
/* 381:    */  
/* 382:    */  public Iterator getNamespaceAxisIterator(Object o)
/* 383:    */  {
/* 384:384 */    if (!isElement(o)) {
/* 385:385 */      return JaxenConstants.EMPTY_ITERATOR;
/* 386:    */    }
/* 387:387 */    Map nsMap = new HashMap();
/* 388:388 */    Element elt = (Element)o;
/* 389:389 */    ParentNode parent = elt;
/* 390:    */    
/* 391:391 */    while ((parent instanceof Element)) {
/* 392:392 */      elt = (Element)parent;
/* 393:393 */      String uri = elt.getNamespaceURI();
/* 394:394 */      String prefix = elt.getNamespacePrefix();
/* 395:395 */      addNamespaceForElement(elt, uri, prefix, nsMap);
/* 396:396 */      int count = elt.getNamespaceDeclarationCount();
/* 397:397 */      for (int i = 0; i < count; i++) {
/* 398:398 */        prefix = elt.getNamespacePrefix(i);
/* 399:399 */        uri = elt.getNamespaceURI(prefix);
/* 400:400 */        addNamespaceForElement(elt, uri, prefix, nsMap);
/* 401:    */      }
/* 402:402 */      parent = elt.getParent();
/* 403:    */    }
/* 404:404 */    addNamespaceForElement(elt, "http://www.w3.org/XML/1998/namespace", "xml", nsMap);
/* 405:    */    
/* 406:406 */    return nsMap.values().iterator();
/* 407:    */  }
/* 408:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.xom.DocumentNavigator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */