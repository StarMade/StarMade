/*   1:    */package org.dom4j.tree;
/*   2:    */
/*   3:    */import java.util.ArrayList;
/*   4:    */import java.util.Iterator;
/*   5:    */import java.util.List;
/*   6:    */import java.util.StringTokenizer;
/*   7:    */import org.dom4j.Branch;
/*   8:    */import org.dom4j.Comment;
/*   9:    */import org.dom4j.DocumentFactory;
/*  10:    */import org.dom4j.Element;
/*  11:    */import org.dom4j.IllegalAddException;
/*  12:    */import org.dom4j.Namespace;
/*  13:    */import org.dom4j.Node;
/*  14:    */import org.dom4j.ProcessingInstruction;
/*  15:    */import org.dom4j.QName;
/*  16:    */
/*  32:    */public abstract class AbstractBranch
/*  33:    */  extends AbstractNode
/*  34:    */  implements Branch
/*  35:    */{
/*  36:    */  protected static final int DEFAULT_CONTENT_LIST_SIZE = 5;
/*  37:    */  
/*  38:    */  public boolean isReadOnly()
/*  39:    */  {
/*  40: 40 */    return false;
/*  41:    */  }
/*  42:    */  
/*  43:    */  public boolean hasContent() {
/*  44: 44 */    return nodeCount() > 0;
/*  45:    */  }
/*  46:    */  
/*  47:    */  public List content() {
/*  48: 48 */    List backingList = contentList();
/*  49:    */    
/*  50: 50 */    return new ContentListFacade(this, backingList);
/*  51:    */  }
/*  52:    */  
/*  53:    */  public String getText() {
/*  54: 54 */    List content = contentList();
/*  55:    */    
/*  56: 56 */    if (content != null) {
/*  57: 57 */      int size = content.size();
/*  58:    */      
/*  59: 59 */      if (size >= 1) {
/*  60: 60 */        Object first = content.get(0);
/*  61: 61 */        String firstText = getContentAsText(first);
/*  62:    */        
/*  63: 63 */        if (size == 1)
/*  64:    */        {
/*  65: 65 */          return firstText;
/*  66:    */        }
/*  67: 67 */        StringBuffer buffer = new StringBuffer(firstText);
/*  68:    */        
/*  69: 69 */        for (int i = 1; i < size; i++) {
/*  70: 70 */          Object node = content.get(i);
/*  71: 71 */          buffer.append(getContentAsText(node));
/*  72:    */        }
/*  73:    */        
/*  74: 74 */        return buffer.toString();
/*  75:    */      }
/*  76:    */    }
/*  77:    */    
/*  79: 79 */    return "";
/*  80:    */  }
/*  81:    */  
/*  90:    */  protected String getContentAsText(Object content)
/*  91:    */  {
/*  92: 92 */    if ((content instanceof Node)) {
/*  93: 93 */      Node node = (Node)content;
/*  94:    */      
/*  95: 95 */      switch (node.getNodeType())
/*  96:    */      {
/*  98:    */      case 3: 
/*  99:    */      case 4: 
/* 100:    */      case 5: 
/* 101:101 */        return node.getText();
/* 102:    */      
/* 103:    */      }
/* 104:    */      
/* 105:    */    }
/* 106:106 */    else if ((content instanceof String)) {
/* 107:107 */      return (String)content;
/* 108:    */    }
/* 109:    */    
/* 110:110 */    return "";
/* 111:    */  }
/* 112:    */  
/* 120:    */  protected String getContentAsStringValue(Object content)
/* 121:    */  {
/* 122:122 */    if ((content instanceof Node)) {
/* 123:123 */      Node node = (Node)content;
/* 124:    */      
/* 125:125 */      switch (node.getNodeType())
/* 126:    */      {
/* 128:    */      case 1: 
/* 129:    */      case 3: 
/* 130:    */      case 4: 
/* 131:    */      case 5: 
/* 132:132 */        return node.getStringValue();
/* 133:    */      
/* 134:    */      }
/* 135:    */      
/* 136:    */    }
/* 137:137 */    else if ((content instanceof String)) {
/* 138:138 */      return (String)content;
/* 139:    */    }
/* 140:    */    
/* 141:141 */    return "";
/* 142:    */  }
/* 143:    */  
/* 144:    */  public String getTextTrim() {
/* 145:145 */    String text = getText();
/* 146:    */    
/* 147:147 */    StringBuffer textContent = new StringBuffer();
/* 148:148 */    StringTokenizer tokenizer = new StringTokenizer(text);
/* 149:    */    
/* 150:150 */    while (tokenizer.hasMoreTokens()) {
/* 151:151 */      String str = tokenizer.nextToken();
/* 152:152 */      textContent.append(str);
/* 153:    */      
/* 154:154 */      if (tokenizer.hasMoreTokens()) {
/* 155:155 */        textContent.append(" ");
/* 156:    */      }
/* 157:    */    }
/* 158:    */    
/* 159:159 */    return textContent.toString();
/* 160:    */  }
/* 161:    */  
/* 162:    */  public void setProcessingInstructions(List listOfPIs) {
/* 163:163 */    for (Iterator iter = listOfPIs.iterator(); iter.hasNext();) {
/* 164:164 */      ProcessingInstruction pi = (ProcessingInstruction)iter.next();
/* 165:165 */      addNode(pi);
/* 166:    */    }
/* 167:    */  }
/* 168:    */  
/* 169:    */  public Element addElement(String name) {
/* 170:170 */    Element node = getDocumentFactory().createElement(name);
/* 171:171 */    add(node);
/* 172:    */    
/* 173:173 */    return node;
/* 174:    */  }
/* 175:    */  
/* 176:    */  public Element addElement(String qualifiedName, String namespaceURI) {
/* 177:177 */    Element node = getDocumentFactory().createElement(qualifiedName, namespaceURI);
/* 178:    */    
/* 179:179 */    add(node);
/* 180:    */    
/* 181:181 */    return node;
/* 182:    */  }
/* 183:    */  
/* 184:    */  public Element addElement(QName qname) {
/* 185:185 */    Element node = getDocumentFactory().createElement(qname);
/* 186:186 */    add(node);
/* 187:    */    
/* 188:188 */    return node;
/* 189:    */  }
/* 190:    */  
/* 191:    */  public Element addElement(String name, String prefix, String uri) {
/* 192:192 */    Namespace namespace = Namespace.get(prefix, uri);
/* 193:193 */    QName qName = getDocumentFactory().createQName(name, namespace);
/* 194:    */    
/* 195:195 */    return addElement(qName);
/* 196:    */  }
/* 197:    */  
/* 198:    */  public void add(Node node)
/* 199:    */  {
/* 200:200 */    switch (node.getNodeType()) {
/* 201:    */    case 1: 
/* 202:202 */      add((Element)node);
/* 203:    */      
/* 204:204 */      break;
/* 205:    */    
/* 206:    */    case 8: 
/* 207:207 */      add((Comment)node);
/* 208:    */      
/* 209:209 */      break;
/* 210:    */    
/* 211:    */    case 7: 
/* 212:212 */      add((ProcessingInstruction)node);
/* 213:    */      
/* 214:214 */      break;
/* 215:    */    
/* 216:    */    default: 
/* 217:217 */      invalidNodeTypeAddException(node);
/* 218:    */    }
/* 219:    */  }
/* 220:    */  
/* 221:    */  public boolean remove(Node node) {
/* 222:222 */    switch (node.getNodeType()) {
/* 223:    */    case 1: 
/* 224:224 */      return remove((Element)node);
/* 225:    */    
/* 226:    */    case 8: 
/* 227:227 */      return remove((Comment)node);
/* 228:    */    
/* 229:    */    case 7: 
/* 230:230 */      return remove((ProcessingInstruction)node);
/* 231:    */    }
/* 232:    */    
/* 233:233 */    invalidNodeTypeAddException(node);
/* 234:    */    
/* 235:235 */    return false;
/* 236:    */  }
/* 237:    */  
/* 239:    */  public void add(Comment comment)
/* 240:    */  {
/* 241:241 */    addNode(comment);
/* 242:    */  }
/* 243:    */  
/* 244:    */  public void add(Element element) {
/* 245:245 */    addNode(element);
/* 246:    */  }
/* 247:    */  
/* 248:    */  public void add(ProcessingInstruction pi) {
/* 249:249 */    addNode(pi);
/* 250:    */  }
/* 251:    */  
/* 252:    */  public boolean remove(Comment comment) {
/* 253:253 */    return removeNode(comment);
/* 254:    */  }
/* 255:    */  
/* 256:    */  public boolean remove(Element element) {
/* 257:257 */    return removeNode(element);
/* 258:    */  }
/* 259:    */  
/* 260:    */  public boolean remove(ProcessingInstruction pi) {
/* 261:261 */    return removeNode(pi);
/* 262:    */  }
/* 263:    */  
/* 264:    */  public Element elementByID(String elementID) {
/* 265:265 */    int i = 0; for (int size = nodeCount(); i < size; i++) {
/* 266:266 */      Node node = node(i);
/* 267:    */      
/* 268:268 */      if ((node instanceof Element)) {
/* 269:269 */        Element element = (Element)node;
/* 270:270 */        String id = elementID(element);
/* 271:    */        
/* 272:272 */        if ((id != null) && (id.equals(elementID))) {
/* 273:273 */          return element;
/* 274:    */        }
/* 275:275 */        element = element.elementByID(elementID);
/* 276:    */        
/* 277:277 */        if (element != null) {
/* 278:278 */          return element;
/* 279:    */        }
/* 280:    */      }
/* 281:    */    }
/* 282:    */    
/* 284:284 */    return null;
/* 285:    */  }
/* 286:    */  
/* 287:    */  public void appendContent(Branch branch) {
/* 288:288 */    int i = 0; for (int size = branch.nodeCount(); i < size; i++) {
/* 289:289 */      Node node = branch.node(i);
/* 290:290 */      add((Node)node.clone());
/* 291:    */    }
/* 292:    */  }
/* 293:    */  
/* 294:    */  public Node node(int index) {
/* 295:295 */    Object object = contentList().get(index);
/* 296:    */    
/* 297:297 */    if ((object instanceof Node)) {
/* 298:298 */      return (Node)object;
/* 299:    */    }
/* 300:    */    
/* 301:301 */    if ((object instanceof String)) {
/* 302:302 */      return getDocumentFactory().createText(object.toString());
/* 303:    */    }
/* 304:    */    
/* 305:305 */    return null;
/* 306:    */  }
/* 307:    */  
/* 308:    */  public int nodeCount() {
/* 309:309 */    return contentList().size();
/* 310:    */  }
/* 311:    */  
/* 312:    */  public int indexOf(Node node) {
/* 313:313 */    return contentList().indexOf(node);
/* 314:    */  }
/* 315:    */  
/* 316:    */  public Iterator nodeIterator() {
/* 317:317 */    return contentList().iterator();
/* 318:    */  }
/* 319:    */  
/* 331:    */  protected String elementID(Element element)
/* 332:    */  {
/* 333:333 */    return element.attributeValue("ID");
/* 334:    */  }
/* 335:    */  
/* 341:    */  protected abstract List contentList();
/* 342:    */  
/* 348:    */  protected List createContentList()
/* 349:    */  {
/* 350:350 */    return new ArrayList(5);
/* 351:    */  }
/* 352:    */  
/* 361:    */  protected List createContentList(int size)
/* 362:    */  {
/* 363:363 */    return new ArrayList(size);
/* 364:    */  }
/* 365:    */  
/* 371:    */  protected BackedList createResultList()
/* 372:    */  {
/* 373:373 */    return new BackedList(this, contentList());
/* 374:    */  }
/* 375:    */  
/* 384:    */  protected List createSingleResultList(Object result)
/* 385:    */  {
/* 386:386 */    BackedList list = new BackedList(this, contentList(), 1);
/* 387:387 */    list.addLocal(result);
/* 388:    */    
/* 389:389 */    return list;
/* 390:    */  }
/* 391:    */  
/* 397:    */  protected List createEmptyList()
/* 398:    */  {
/* 399:399 */    return new BackedList(this, contentList(), 0);
/* 400:    */  }
/* 401:    */  
/* 405:    */  protected abstract void addNode(Node paramNode);
/* 406:    */  
/* 410:    */  protected abstract void addNode(int paramInt, Node paramNode);
/* 411:    */  
/* 415:    */  protected abstract boolean removeNode(Node paramNode);
/* 416:    */  
/* 420:    */  protected abstract void childAdded(Node paramNode);
/* 421:    */  
/* 425:    */  protected abstract void childRemoved(Node paramNode);
/* 426:    */  
/* 429:    */  protected void contentRemoved()
/* 430:    */  {
/* 431:431 */    List content = contentList();
/* 432:    */    
/* 433:433 */    int i = 0; for (int size = content.size(); i < size; i++) {
/* 434:434 */      Object object = content.get(i);
/* 435:    */      
/* 436:436 */      if ((object instanceof Node)) {
/* 437:437 */        childRemoved((Node)object);
/* 438:    */      }
/* 439:    */    }
/* 440:    */  }
/* 441:    */  
/* 451:    */  protected void invalidNodeTypeAddException(Node node)
/* 452:    */  {
/* 453:453 */    throw new IllegalAddException("Invalid node type. Cannot add node: " + node + " to this branch: " + this);
/* 454:    */  }
/* 455:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.AbstractBranch
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */