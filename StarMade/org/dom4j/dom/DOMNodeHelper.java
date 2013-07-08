/*   1:    */package org.dom4j.dom;
/*   2:    */
/*   3:    */import java.io.PrintStream;
/*   4:    */import java.util.List;
/*   5:    */import org.dom4j.Branch;
/*   6:    */import org.dom4j.CharacterData;
/*   7:    */import org.w3c.dom.Attr;
/*   8:    */import org.w3c.dom.DOMException;
/*   9:    */import org.w3c.dom.NamedNodeMap;
/*  10:    */import org.w3c.dom.NodeList;
/*  11:    */import org.w3c.dom.Text;
/*  12:    */
/*  31:    */public class DOMNodeHelper
/*  32:    */{
/*  33: 33 */  public static final NodeList EMPTY_NODE_LIST = new EmptyNodeList();
/*  34:    */  
/*  39:    */  public static boolean supports(org.dom4j.Node node, String feature, String version)
/*  40:    */  {
/*  41: 41 */    return false;
/*  42:    */  }
/*  43:    */  
/*  44:    */  public static String getNamespaceURI(org.dom4j.Node node) {
/*  45: 45 */    return null;
/*  46:    */  }
/*  47:    */  
/*  48:    */  public static String getPrefix(org.dom4j.Node node) {
/*  49: 49 */    return null;
/*  50:    */  }
/*  51:    */  
/*  52:    */  public static String getLocalName(org.dom4j.Node node) {
/*  53: 53 */    return null;
/*  54:    */  }
/*  55:    */  
/*  56:    */  public static void setPrefix(org.dom4j.Node node, String prefix) throws DOMException
/*  57:    */  {}
/*  58:    */  
/*  59:    */  public static String getNodeValue(org.dom4j.Node node) throws DOMException
/*  60:    */  {
/*  61: 61 */    return node.getText();
/*  62:    */  }
/*  63:    */  
/*  64:    */  public static void setNodeValue(org.dom4j.Node node, String nodeValue) throws DOMException
/*  65:    */  {
/*  66: 66 */    node.setText(nodeValue);
/*  67:    */  }
/*  68:    */  
/*  69:    */  public static org.w3c.dom.Node getParentNode(org.dom4j.Node node) {
/*  70: 70 */    return asDOMNode(node.getParent());
/*  71:    */  }
/*  72:    */  
/*  73:    */  public static NodeList getChildNodes(org.dom4j.Node node) {
/*  74: 74 */    return EMPTY_NODE_LIST;
/*  75:    */  }
/*  76:    */  
/*  77:    */  public static org.w3c.dom.Node getFirstChild(org.dom4j.Node node) {
/*  78: 78 */    return null;
/*  79:    */  }
/*  80:    */  
/*  81:    */  public static org.w3c.dom.Node getLastChild(org.dom4j.Node node) {
/*  82: 82 */    return null;
/*  83:    */  }
/*  84:    */  
/*  85:    */  public static org.w3c.dom.Node getPreviousSibling(org.dom4j.Node node) {
/*  86: 86 */    org.dom4j.Element parent = node.getParent();
/*  87:    */    
/*  88: 88 */    if (parent != null) {
/*  89: 89 */      int index = parent.indexOf(node);
/*  90:    */      
/*  91: 91 */      if (index > 0) {
/*  92: 92 */        org.dom4j.Node previous = parent.node(index - 1);
/*  93:    */        
/*  94: 94 */        return asDOMNode(previous);
/*  95:    */      }
/*  96:    */    }
/*  97:    */    
/*  98: 98 */    return null;
/*  99:    */  }
/* 100:    */  
/* 101:    */  public static org.w3c.dom.Node getNextSibling(org.dom4j.Node node) {
/* 102:102 */    org.dom4j.Element parent = node.getParent();
/* 103:    */    
/* 104:104 */    if (parent != null) {
/* 105:105 */      int index = parent.indexOf(node);
/* 106:    */      
/* 107:107 */      if (index >= 0) {
/* 108:108 */        index++; if (index < parent.nodeCount()) {
/* 109:109 */          org.dom4j.Node next = parent.node(index);
/* 110:    */          
/* 111:111 */          return asDOMNode(next);
/* 112:    */        }
/* 113:    */      }
/* 114:    */    }
/* 115:    */    
/* 116:116 */    return null;
/* 117:    */  }
/* 118:    */  
/* 119:    */  public static NamedNodeMap getAttributes(org.dom4j.Node node) {
/* 120:120 */    return null;
/* 121:    */  }
/* 122:    */  
/* 123:    */  public static org.w3c.dom.Document getOwnerDocument(org.dom4j.Node node) {
/* 124:124 */    return asDOMDocument(node.getDocument());
/* 125:    */  }
/* 126:    */  
/* 127:    */  public static org.w3c.dom.Node insertBefore(org.dom4j.Node node, org.w3c.dom.Node newChild, org.w3c.dom.Node refChild)
/* 128:    */    throws DOMException
/* 129:    */  {
/* 130:130 */    if ((node instanceof Branch)) {
/* 131:131 */      Branch branch = (Branch)node;
/* 132:132 */      List list = branch.content();
/* 133:133 */      int index = list.indexOf(refChild);
/* 134:    */      
/* 135:135 */      if (index < 0) {
/* 136:136 */        branch.add((org.dom4j.Node)newChild);
/* 137:    */      } else {
/* 138:138 */        list.add(index, newChild);
/* 139:    */      }
/* 140:    */      
/* 141:141 */      return newChild;
/* 142:    */    }
/* 143:143 */    throw new DOMException((short)3, "Children not allowed for this node: " + node);
/* 144:    */  }
/* 145:    */  
/* 148:    */  public static org.w3c.dom.Node replaceChild(org.dom4j.Node node, org.w3c.dom.Node newChild, org.w3c.dom.Node oldChild)
/* 149:    */    throws DOMException
/* 150:    */  {
/* 151:151 */    if ((node instanceof Branch)) {
/* 152:152 */      Branch branch = (Branch)node;
/* 153:153 */      List list = branch.content();
/* 154:154 */      int index = list.indexOf(oldChild);
/* 155:    */      
/* 156:156 */      if (index < 0) {
/* 157:157 */        throw new DOMException((short)8, "Tried to replace a non existing child for node: " + node);
/* 158:    */      }
/* 159:    */      
/* 162:162 */      list.set(index, newChild);
/* 163:    */      
/* 164:164 */      return oldChild;
/* 165:    */    }
/* 166:166 */    throw new DOMException((short)3, "Children not allowed for this node: " + node);
/* 167:    */  }
/* 168:    */  
/* 170:    */  public static org.w3c.dom.Node removeChild(org.dom4j.Node node, org.w3c.dom.Node oldChild)
/* 171:    */    throws DOMException
/* 172:    */  {
/* 173:173 */    if ((node instanceof Branch)) {
/* 174:174 */      Branch branch = (Branch)node;
/* 175:175 */      branch.remove((org.dom4j.Node)oldChild);
/* 176:    */      
/* 177:177 */      return oldChild;
/* 178:    */    }
/* 179:    */    
/* 180:180 */    throw new DOMException((short)3, "Children not allowed for this node: " + node);
/* 181:    */  }
/* 182:    */  
/* 183:    */  public static org.w3c.dom.Node appendChild(org.dom4j.Node node, org.w3c.dom.Node newChild)
/* 184:    */    throws DOMException
/* 185:    */  {
/* 186:186 */    if ((node instanceof Branch)) {
/* 187:187 */      Branch branch = (Branch)node;
/* 188:188 */      org.w3c.dom.Node previousParent = newChild.getParentNode();
/* 189:    */      
/* 190:190 */      if (previousParent != null) {
/* 191:191 */        previousParent.removeChild(newChild);
/* 192:    */      }
/* 193:    */      
/* 194:194 */      branch.add((org.dom4j.Node)newChild);
/* 195:    */      
/* 196:196 */      return newChild;
/* 197:    */    }
/* 198:    */    
/* 199:199 */    throw new DOMException((short)3, "Children not allowed for this node: " + node);
/* 200:    */  }
/* 201:    */  
/* 202:    */  public static boolean hasChildNodes(org.dom4j.Node node)
/* 203:    */  {
/* 204:204 */    return false;
/* 205:    */  }
/* 206:    */  
/* 207:    */  public static org.w3c.dom.Node cloneNode(org.dom4j.Node node, boolean deep) {
/* 208:208 */    return asDOMNode((org.dom4j.Node)node.clone());
/* 209:    */  }
/* 210:    */  
/* 212:    */  public static void normalize(org.dom4j.Node node) {}
/* 213:    */  
/* 214:    */  public static boolean isSupported(org.dom4j.Node n, String feature, String version)
/* 215:    */  {
/* 216:216 */    return false;
/* 217:    */  }
/* 218:    */  
/* 219:    */  public static boolean hasAttributes(org.dom4j.Node node) {
/* 220:220 */    if ((node != null) && ((node instanceof org.dom4j.Element))) {
/* 221:221 */      return ((org.dom4j.Element)node).attributeCount() > 0;
/* 222:    */    }
/* 223:223 */    return false;
/* 224:    */  }
/* 225:    */  
/* 227:    */  public static String getData(CharacterData charData)
/* 228:    */    throws DOMException
/* 229:    */  {
/* 230:230 */    return charData.getText();
/* 231:    */  }
/* 232:    */  
/* 233:    */  public static void setData(CharacterData charData, String data) throws DOMException
/* 234:    */  {
/* 235:235 */    charData.setText(data);
/* 236:    */  }
/* 237:    */  
/* 238:    */  public static int getLength(CharacterData charData) {
/* 239:239 */    String text = charData.getText();
/* 240:    */    
/* 241:241 */    return text != null ? text.length() : 0;
/* 242:    */  }
/* 243:    */  
/* 244:    */  public static String substringData(CharacterData charData, int offset, int count) throws DOMException
/* 245:    */  {
/* 246:246 */    if (count < 0) {
/* 247:247 */      throw new DOMException((short)1, "Illegal value for count: " + count);
/* 248:    */    }
/* 249:    */    
/* 251:251 */    String text = charData.getText();
/* 252:252 */    int length = text != null ? text.length() : 0;
/* 253:    */    
/* 254:254 */    if ((offset < 0) || (offset >= length)) {
/* 255:255 */      throw new DOMException((short)1, "No text at offset: " + offset);
/* 256:    */    }
/* 257:    */    
/* 259:259 */    if (offset + count > length) {
/* 260:260 */      return text.substring(offset);
/* 261:    */    }
/* 262:    */    
/* 263:263 */    return text.substring(offset, offset + count);
/* 264:    */  }
/* 265:    */  
/* 266:    */  public static void appendData(CharacterData charData, String arg) throws DOMException
/* 267:    */  {
/* 268:268 */    if (charData.isReadOnly()) {
/* 269:269 */      throw new DOMException((short)7, "CharacterData node is read only: " + charData);
/* 270:    */    }
/* 271:    */    
/* 272:272 */    String text = charData.getText();
/* 273:    */    
/* 274:274 */    if (text == null) {
/* 275:275 */      charData.setText(text);
/* 276:    */    } else {
/* 277:277 */      charData.setText(text + arg);
/* 278:    */    }
/* 279:    */  }
/* 280:    */  
/* 281:    */  public static void insertData(CharacterData data, int offset, String arg)
/* 282:    */    throws DOMException
/* 283:    */  {
/* 284:284 */    if (data.isReadOnly()) {
/* 285:285 */      throw new DOMException((short)7, "CharacterData node is read only: " + data);
/* 286:    */    }
/* 287:    */    
/* 288:288 */    String text = data.getText();
/* 289:    */    
/* 290:290 */    if (text == null) {
/* 291:291 */      data.setText(arg);
/* 292:    */    } else {
/* 293:293 */      int length = text.length();
/* 294:    */      
/* 295:295 */      if ((offset < 0) || (offset > length)) {
/* 296:296 */        throw new DOMException((short)1, "No text at offset: " + offset);
/* 297:    */      }
/* 298:    */      
/* 299:299 */      StringBuffer buffer = new StringBuffer(text);
/* 300:300 */      buffer.insert(offset, arg);
/* 301:301 */      data.setText(buffer.toString());
/* 302:    */    }
/* 303:    */  }
/* 304:    */  
/* 306:    */  public static void deleteData(CharacterData charData, int offset, int count)
/* 307:    */    throws DOMException
/* 308:    */  {
/* 309:309 */    if (charData.isReadOnly()) {
/* 310:310 */      throw new DOMException((short)7, "CharacterData node is read only: " + charData);
/* 311:    */    }
/* 312:    */    
/* 313:313 */    if (count < 0) {
/* 314:314 */      throw new DOMException((short)1, "Illegal value for count: " + count);
/* 315:    */    }
/* 316:    */    
/* 318:318 */    String text = charData.getText();
/* 319:    */    
/* 320:320 */    if (text != null) {
/* 321:321 */      int length = text.length();
/* 322:    */      
/* 323:323 */      if ((offset < 0) || (offset >= length)) {
/* 324:324 */        throw new DOMException((short)1, "No text at offset: " + offset);
/* 325:    */      }
/* 326:    */      
/* 327:327 */      StringBuffer buffer = new StringBuffer(text);
/* 328:328 */      buffer.delete(offset, offset + count);
/* 329:329 */      charData.setText(buffer.toString());
/* 330:    */    }
/* 331:    */  }
/* 332:    */  
/* 334:    */  public static void replaceData(CharacterData charData, int offset, int count, String arg)
/* 335:    */    throws DOMException
/* 336:    */  {
/* 337:337 */    if (charData.isReadOnly()) {
/* 338:338 */      throw new DOMException((short)7, "CharacterData node is read only: " + charData);
/* 339:    */    }
/* 340:    */    
/* 341:341 */    if (count < 0) {
/* 342:342 */      throw new DOMException((short)1, "Illegal value for count: " + count);
/* 343:    */    }
/* 344:    */    
/* 346:346 */    String text = charData.getText();
/* 347:    */    
/* 348:348 */    if (text != null) {
/* 349:349 */      int length = text.length();
/* 350:    */      
/* 351:351 */      if ((offset < 0) || (offset >= length)) {
/* 352:352 */        throw new DOMException((short)1, "No text at offset: " + offset);
/* 353:    */      }
/* 354:    */      
/* 355:355 */      StringBuffer buffer = new StringBuffer(text);
/* 356:356 */      buffer.replace(offset, offset + count, arg);
/* 357:357 */      charData.setText(buffer.toString());
/* 358:    */    }
/* 359:    */  }
/* 360:    */  
/* 365:    */  public static void appendElementsByTagName(List list, Branch parent, String name)
/* 366:    */  {
/* 367:367 */    boolean isStar = "*".equals(name);
/* 368:    */    
/* 369:369 */    int i = 0; for (int size = parent.nodeCount(); i < size; i++) {
/* 370:370 */      org.dom4j.Node node = parent.node(i);
/* 371:    */      
/* 372:372 */      if ((node instanceof org.dom4j.Element)) {
/* 373:373 */        org.dom4j.Element element = (org.dom4j.Element)node;
/* 374:    */        
/* 375:375 */        if ((isStar) || (name.equals(element.getName()))) {
/* 376:376 */          list.add(element);
/* 377:    */        }
/* 378:    */        
/* 379:379 */        appendElementsByTagName(list, element, name);
/* 380:    */      }
/* 381:    */    }
/* 382:    */  }
/* 383:    */  
/* 384:    */  public static void appendElementsByTagNameNS(List list, Branch parent, String namespace, String localName)
/* 385:    */  {
/* 386:386 */    boolean isStarNS = "*".equals(namespace);
/* 387:387 */    boolean isStar = "*".equals(localName);
/* 388:    */    
/* 389:389 */    int i = 0; for (int size = parent.nodeCount(); i < size; i++) {
/* 390:390 */      org.dom4j.Node node = parent.node(i);
/* 391:    */      
/* 392:392 */      if ((node instanceof org.dom4j.Element)) {
/* 393:393 */        org.dom4j.Element element = (org.dom4j.Element)node;
/* 394:    */        
/* 395:395 */        if (((isStarNS) || (((namespace != null) && (namespace.length() != 0)) || ((element.getNamespaceURI() == null) || (element.getNamespaceURI().length() == 0) || ((namespace != null) && (namespace.equals(element.getNamespaceURI())))))) && ((isStar) || (localName.equals(element.getName()))))
/* 396:    */        {
/* 403:403 */          list.add(element);
/* 404:    */        }
/* 405:    */        
/* 406:406 */        appendElementsByTagNameNS(list, element, namespace, localName);
/* 407:    */      }
/* 408:    */    }
/* 409:    */  }
/* 410:    */  
/* 412:    */  public static NodeList createNodeList(List list)
/* 413:    */  {
/* 414:414 */    new NodeList() { private final List val$list;
/* 415:    */      
/* 416:416 */      public org.w3c.dom.Node item(int index) { if (index >= getLength())
/* 417:    */        {
/* 422:422 */          return null;
/* 423:    */        }
/* 424:424 */        return DOMNodeHelper.asDOMNode((org.dom4j.Node)this.val$list.get(index));
/* 425:    */      }
/* 426:    */      
/* 427:    */      public int getLength()
/* 428:    */      {
/* 429:429 */        return this.val$list.size();
/* 430:    */      }
/* 431:    */    };
/* 432:    */  }
/* 433:    */  
/* 434:    */  public static org.w3c.dom.Node asDOMNode(org.dom4j.Node node) {
/* 435:435 */    if (node == null) {
/* 436:436 */      return null;
/* 437:    */    }
/* 438:    */    
/* 439:439 */    if ((node instanceof org.w3c.dom.Node)) {
/* 440:440 */      return (org.w3c.dom.Node)node;
/* 441:    */    }
/* 442:    */    
/* 443:443 */    System.out.println("Cannot convert: " + node + " into a W3C DOM Node");
/* 444:    */    
/* 445:445 */    notSupported();
/* 446:    */    
/* 447:447 */    return null;
/* 448:    */  }
/* 449:    */  
/* 450:    */  public static org.w3c.dom.Document asDOMDocument(org.dom4j.Document document)
/* 451:    */  {
/* 452:452 */    if (document == null) {
/* 453:453 */      return null;
/* 454:    */    }
/* 455:    */    
/* 456:456 */    if ((document instanceof org.w3c.dom.Document)) {
/* 457:457 */      return (org.w3c.dom.Document)document;
/* 458:    */    }
/* 459:    */    
/* 460:460 */    notSupported();
/* 461:    */    
/* 462:462 */    return null;
/* 463:    */  }
/* 464:    */  
/* 465:    */  public static org.w3c.dom.DocumentType asDOMDocumentType(org.dom4j.DocumentType dt)
/* 466:    */  {
/* 467:467 */    if (dt == null) {
/* 468:468 */      return null;
/* 469:    */    }
/* 470:    */    
/* 471:471 */    if ((dt instanceof org.w3c.dom.DocumentType)) {
/* 472:472 */      return (org.w3c.dom.DocumentType)dt;
/* 473:    */    }
/* 474:    */    
/* 475:475 */    notSupported();
/* 476:    */    
/* 477:477 */    return null;
/* 478:    */  }
/* 479:    */  
/* 480:    */  public static Text asDOMText(CharacterData text)
/* 481:    */  {
/* 482:482 */    if (text == null) {
/* 483:483 */      return null;
/* 484:    */    }
/* 485:    */    
/* 486:486 */    if ((text instanceof Text)) {
/* 487:487 */      return (Text)text;
/* 488:    */    }
/* 489:    */    
/* 490:490 */    notSupported();
/* 491:    */    
/* 492:492 */    return null;
/* 493:    */  }
/* 494:    */  
/* 495:    */  public static org.w3c.dom.Element asDOMElement(org.dom4j.Node element)
/* 496:    */  {
/* 497:497 */    if (element == null) {
/* 498:498 */      return null;
/* 499:    */    }
/* 500:    */    
/* 501:501 */    if ((element instanceof org.w3c.dom.Element)) {
/* 502:502 */      return (org.w3c.dom.Element)element;
/* 503:    */    }
/* 504:    */    
/* 505:505 */    notSupported();
/* 506:    */    
/* 507:507 */    return null;
/* 508:    */  }
/* 509:    */  
/* 510:    */  public static Attr asDOMAttr(org.dom4j.Node attribute)
/* 511:    */  {
/* 512:512 */    if (attribute == null) {
/* 513:513 */      return null;
/* 514:    */    }
/* 515:    */    
/* 516:516 */    if ((attribute instanceof Attr)) {
/* 517:517 */      return (Attr)attribute;
/* 518:    */    }
/* 519:    */    
/* 520:520 */    notSupported();
/* 521:    */    
/* 522:522 */    return null;
/* 523:    */  }
/* 524:    */  
/* 531:    */  public static void notSupported()
/* 532:    */  {
/* 533:533 */    throw new DOMException((short)9, "Not supported yet");
/* 534:    */  }
/* 535:    */  
/* 536:    */  public static class EmptyNodeList implements NodeList
/* 537:    */  {
/* 538:    */    public org.w3c.dom.Node item(int index) {
/* 539:539 */      return null;
/* 540:    */    }
/* 541:    */    
/* 542:    */    public int getLength() {
/* 543:543 */      return 0;
/* 544:    */    }
/* 545:    */  }
/* 546:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.dom.DOMNodeHelper
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */