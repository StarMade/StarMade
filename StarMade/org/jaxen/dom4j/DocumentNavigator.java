/*   1:    */package org.jaxen.dom4j;
/*   2:    */
/*   3:    */import java.util.ArrayList;
/*   4:    */import java.util.HashSet;
/*   5:    */import java.util.Iterator;
/*   6:    */import java.util.List;
/*   7:    */import org.dom4j.Attribute;
/*   8:    */import org.dom4j.Branch;
/*   9:    */import org.dom4j.CDATA;
/*  10:    */import org.dom4j.Comment;
/*  11:    */import org.dom4j.Document;
/*  12:    */import org.dom4j.DocumentException;
/*  13:    */import org.dom4j.Element;
/*  14:    */import org.dom4j.Namespace;
/*  15:    */import org.dom4j.Node;
/*  16:    */import org.dom4j.ProcessingInstruction;
/*  17:    */import org.dom4j.QName;
/*  18:    */import org.dom4j.Text;
/*  19:    */import org.dom4j.io.SAXReader;
/*  20:    */import org.jaxen.DefaultNavigator;
/*  21:    */import org.jaxen.FunctionCallException;
/*  22:    */import org.jaxen.JaxenConstants;
/*  23:    */import org.jaxen.NamedAccessNavigator;
/*  24:    */import org.jaxen.Navigator;
/*  25:    */import org.jaxen.XPath;
/*  26:    */import org.jaxen.saxpath.SAXPathException;
/*  27:    */import org.jaxen.util.SingleObjectIterator;
/*  28:    */
/*  97:    */public class DocumentNavigator
/*  98:    */  extends DefaultNavigator
/*  99:    */  implements NamedAccessNavigator
/* 100:    */{
/* 101:    */  private static final long serialVersionUID = 5582300797286535936L;
/* 102:    */  private transient SAXReader reader;
/* 103:    */  
/* 104:    */  private static class Singleton
/* 105:    */  {
/* 106:106 */    private static DocumentNavigator instance = new DocumentNavigator();
/* 107:    */  }
/* 108:    */  
/* 111:    */  public static Navigator getInstance()
/* 112:    */  {
/* 113:113 */    return Singleton.instance;
/* 114:    */  }
/* 115:    */  
/* 116:    */  public boolean isElement(Object obj)
/* 117:    */  {
/* 118:118 */    return obj instanceof Element;
/* 119:    */  }
/* 120:    */  
/* 121:    */  public boolean isComment(Object obj)
/* 122:    */  {
/* 123:123 */    return obj instanceof Comment;
/* 124:    */  }
/* 125:    */  
/* 126:    */  public boolean isText(Object obj)
/* 127:    */  {
/* 128:128 */    return ((obj instanceof Text)) || ((obj instanceof CDATA));
/* 129:    */  }
/* 130:    */  
/* 133:    */  public boolean isAttribute(Object obj)
/* 134:    */  {
/* 135:135 */    return obj instanceof Attribute;
/* 136:    */  }
/* 137:    */  
/* 138:    */  public boolean isProcessingInstruction(Object obj)
/* 139:    */  {
/* 140:140 */    return obj instanceof ProcessingInstruction;
/* 141:    */  }
/* 142:    */  
/* 143:    */  public boolean isDocument(Object obj)
/* 144:    */  {
/* 145:145 */    return obj instanceof Document;
/* 146:    */  }
/* 147:    */  
/* 148:    */  public boolean isNamespace(Object obj)
/* 149:    */  {
/* 150:150 */    return obj instanceof Namespace;
/* 151:    */  }
/* 152:    */  
/* 153:    */  public String getElementName(Object obj)
/* 154:    */  {
/* 155:155 */    Element elem = (Element)obj;
/* 156:    */    
/* 157:157 */    return elem.getName();
/* 158:    */  }
/* 159:    */  
/* 160:    */  public String getElementNamespaceUri(Object obj)
/* 161:    */  {
/* 162:162 */    Element elem = (Element)obj;
/* 163:    */    
/* 164:164 */    String uri = elem.getNamespaceURI();
/* 165:165 */    if (uri == null) {
/* 166:166 */      return "";
/* 167:    */    }
/* 168:168 */    return uri;
/* 169:    */  }
/* 170:    */  
/* 171:    */  public String getElementQName(Object obj)
/* 172:    */  {
/* 173:173 */    Element elem = (Element)obj;
/* 174:    */    
/* 175:175 */    return elem.getQualifiedName();
/* 176:    */  }
/* 177:    */  
/* 178:    */  public String getAttributeName(Object obj)
/* 179:    */  {
/* 180:180 */    Attribute attr = (Attribute)obj;
/* 181:    */    
/* 182:182 */    return attr.getName();
/* 183:    */  }
/* 184:    */  
/* 185:    */  public String getAttributeNamespaceUri(Object obj)
/* 186:    */  {
/* 187:187 */    Attribute attr = (Attribute)obj;
/* 188:    */    
/* 189:189 */    String uri = attr.getNamespaceURI();
/* 190:190 */    if (uri == null) {
/* 191:191 */      return "";
/* 192:    */    }
/* 193:193 */    return uri;
/* 194:    */  }
/* 195:    */  
/* 196:    */  public String getAttributeQName(Object obj)
/* 197:    */  {
/* 198:198 */    Attribute attr = (Attribute)obj;
/* 199:    */    
/* 200:200 */    return attr.getQualifiedName();
/* 201:    */  }
/* 202:    */  
/* 203:    */  public Iterator getChildAxisIterator(Object contextNode)
/* 204:    */  {
/* 205:205 */    Iterator result = null;
/* 206:206 */    if ((contextNode instanceof Branch))
/* 207:    */    {
/* 208:208 */      Branch node = (Branch)contextNode;
/* 209:209 */      result = node.nodeIterator();
/* 210:    */    }
/* 211:211 */    if (result != null) {
/* 212:212 */      return result;
/* 213:    */    }
/* 214:214 */    return JaxenConstants.EMPTY_ITERATOR;
/* 215:    */  }
/* 216:    */  
/* 229:    */  public Iterator getChildAxisIterator(Object contextNode, String localName, String namespacePrefix, String namespaceURI)
/* 230:    */  {
/* 231:231 */    if ((contextNode instanceof Element)) {
/* 232:232 */      Element node = (Element)contextNode;
/* 233:233 */      return node.elementIterator(QName.get(localName, namespacePrefix, namespaceURI));
/* 234:    */    }
/* 235:235 */    if ((contextNode instanceof Document)) {
/* 236:236 */      Document node = (Document)contextNode;
/* 237:237 */      Element el = node.getRootElement();
/* 238:238 */      if ((el == null) || (!el.getName().equals(localName))) {
/* 239:239 */        return JaxenConstants.EMPTY_ITERATOR;
/* 240:    */      }
/* 241:241 */      if ((namespaceURI != null) && 
/* 242:242 */        (!namespaceURI.equals(el.getNamespaceURI()))) {
/* 243:243 */        return JaxenConstants.EMPTY_ITERATOR;
/* 244:    */      }
/* 245:    */      
/* 246:246 */      return new SingleObjectIterator(el);
/* 247:    */    }
/* 248:    */    
/* 249:249 */    return JaxenConstants.EMPTY_ITERATOR;
/* 250:    */  }
/* 251:    */  
/* 252:    */  public Iterator getParentAxisIterator(Object contextNode)
/* 253:    */  {
/* 254:254 */    if ((contextNode instanceof Document))
/* 255:    */    {
/* 256:256 */      return JaxenConstants.EMPTY_ITERATOR;
/* 257:    */    }
/* 258:    */    
/* 259:259 */    Node node = (Node)contextNode;
/* 260:    */    
/* 261:261 */    Object parent = node.getParent();
/* 262:    */    
/* 263:263 */    if (parent == null)
/* 264:    */    {
/* 265:265 */      parent = node.getDocument();
/* 266:    */    }
/* 267:    */    
/* 268:268 */    return new SingleObjectIterator(parent);
/* 269:    */  }
/* 270:    */  
/* 271:    */  public Iterator getAttributeAxisIterator(Object contextNode)
/* 272:    */  {
/* 273:273 */    if (!(contextNode instanceof Element))
/* 274:    */    {
/* 275:275 */      return JaxenConstants.EMPTY_ITERATOR;
/* 276:    */    }
/* 277:    */    
/* 278:278 */    Element elem = (Element)contextNode;
/* 279:    */    
/* 280:280 */    return elem.attributeIterator();
/* 281:    */  }
/* 282:    */  
/* 294:    */  public Iterator getAttributeAxisIterator(Object contextNode, String localName, String namespacePrefix, String namespaceURI)
/* 295:    */  {
/* 296:296 */    if ((contextNode instanceof Element)) {
/* 297:297 */      Element node = (Element)contextNode;
/* 298:298 */      Attribute attr = node.attribute(QName.get(localName, namespacePrefix, namespaceURI));
/* 299:299 */      if (attr == null) {
/* 300:300 */        return JaxenConstants.EMPTY_ITERATOR;
/* 301:    */      }
/* 302:302 */      return new SingleObjectIterator(attr);
/* 303:    */    }
/* 304:304 */    return JaxenConstants.EMPTY_ITERATOR;
/* 305:    */  }
/* 306:    */  
/* 307:    */  public Iterator getNamespaceAxisIterator(Object contextNode)
/* 308:    */  {
/* 309:309 */    if (!(contextNode instanceof Element))
/* 310:    */    {
/* 311:311 */      return JaxenConstants.EMPTY_ITERATOR;
/* 312:    */    }
/* 313:    */    
/* 314:314 */    Element element = (Element)contextNode;
/* 315:315 */    List nsList = new ArrayList();
/* 316:316 */    HashSet prefixes = new HashSet();
/* 317:317 */    Iterator iter; for (Element context = element; context != null; context = context.getParent()) {
/* 318:318 */      List declaredNS = new ArrayList(context.declaredNamespaces());
/* 319:319 */      declaredNS.add(context.getNamespace());
/* 320:    */      
/* 321:321 */      for (Iterator iter = context.attributes().iterator(); iter.hasNext();)
/* 322:    */      {
/* 323:323 */        Attribute attr = (Attribute)iter.next();
/* 324:324 */        declaredNS.add(attr.getNamespace());
/* 325:    */      }
/* 326:    */      
/* 327:327 */      for (iter = declaredNS.iterator(); iter.hasNext();)
/* 328:    */      {
/* 329:329 */        Namespace namespace = (Namespace)iter.next();
/* 330:330 */        if (namespace != Namespace.NO_NAMESPACE)
/* 331:    */        {
/* 332:332 */          String prefix = namespace.getPrefix();
/* 333:333 */          if (!prefixes.contains(prefix)) {
/* 334:334 */            prefixes.add(prefix);
/* 335:335 */            nsList.add(namespace.asXPathResult(element));
/* 336:    */          }
/* 337:    */        }
/* 338:    */      }
/* 339:    */    }
/* 340:340 */    nsList.add(Namespace.XML_NAMESPACE.asXPathResult(element));
/* 341:341 */    return nsList.iterator();
/* 342:    */  }
/* 343:    */  
/* 344:    */  public Object getDocumentNode(Object contextNode)
/* 345:    */  {
/* 346:346 */    if ((contextNode instanceof Document))
/* 347:    */    {
/* 348:348 */      return contextNode;
/* 349:    */    }
/* 350:350 */    if ((contextNode instanceof Node))
/* 351:    */    {
/* 352:352 */      Node node = (Node)contextNode;
/* 353:353 */      return node.getDocument();
/* 354:    */    }
/* 355:355 */    return null;
/* 356:    */  }
/* 357:    */  
/* 360:    */  public XPath parseXPath(String xpath)
/* 361:    */    throws SAXPathException
/* 362:    */  {
/* 363:363 */    return new Dom4jXPath(xpath);
/* 364:    */  }
/* 365:    */  
/* 366:    */  public Object getParentNode(Object contextNode)
/* 367:    */  {
/* 368:368 */    if ((contextNode instanceof Node))
/* 369:    */    {
/* 370:370 */      Node node = (Node)contextNode;
/* 371:371 */      Object answer = node.getParent();
/* 372:372 */      if (answer == null)
/* 373:    */      {
/* 374:374 */        answer = node.getDocument();
/* 375:375 */        if (answer == contextNode) {
/* 376:376 */          return null;
/* 377:    */        }
/* 378:    */      }
/* 379:379 */      return answer;
/* 380:    */    }
/* 381:381 */    return null;
/* 382:    */  }
/* 383:    */  
/* 384:    */  public String getTextStringValue(Object obj)
/* 385:    */  {
/* 386:386 */    return getNodeStringValue((Node)obj);
/* 387:    */  }
/* 388:    */  
/* 389:    */  public String getElementStringValue(Object obj)
/* 390:    */  {
/* 391:391 */    return getNodeStringValue((Node)obj);
/* 392:    */  }
/* 393:    */  
/* 394:    */  public String getAttributeStringValue(Object obj)
/* 395:    */  {
/* 396:396 */    return getNodeStringValue((Node)obj);
/* 397:    */  }
/* 398:    */  
/* 399:    */  private String getNodeStringValue(Node node)
/* 400:    */  {
/* 401:401 */    return node.getStringValue();
/* 402:    */  }
/* 403:    */  
/* 404:    */  public String getNamespaceStringValue(Object obj)
/* 405:    */  {
/* 406:406 */    Namespace ns = (Namespace)obj;
/* 407:    */    
/* 408:408 */    return ns.getURI();
/* 409:    */  }
/* 410:    */  
/* 411:    */  public String getNamespacePrefix(Object obj)
/* 412:    */  {
/* 413:413 */    Namespace ns = (Namespace)obj;
/* 414:    */    
/* 415:415 */    return ns.getPrefix();
/* 416:    */  }
/* 417:    */  
/* 418:    */  public String getCommentStringValue(Object obj)
/* 419:    */  {
/* 420:420 */    Comment cmt = (Comment)obj;
/* 421:    */    
/* 422:422 */    return cmt.getText();
/* 423:    */  }
/* 424:    */  
/* 425:    */  public String translateNamespacePrefixToUri(String prefix, Object context)
/* 426:    */  {
/* 427:427 */    Element element = null;
/* 428:428 */    if ((context instanceof Element))
/* 429:    */    {
/* 430:430 */      element = (Element)context;
/* 431:    */    }
/* 432:432 */    else if ((context instanceof Node))
/* 433:    */    {
/* 434:434 */      Node node = (Node)context;
/* 435:435 */      element = node.getParent();
/* 436:    */    }
/* 437:437 */    if (element != null)
/* 438:    */    {
/* 439:439 */      Namespace namespace = element.getNamespaceForPrefix(prefix);
/* 440:    */      
/* 441:441 */      if (namespace != null)
/* 442:    */      {
/* 443:443 */        return namespace.getURI();
/* 444:    */      }
/* 445:    */    }
/* 446:446 */    return null;
/* 447:    */  }
/* 448:    */  
/* 449:    */  public short getNodeType(Object node)
/* 450:    */  {
/* 451:451 */    if ((node instanceof Node))
/* 452:    */    {
/* 453:453 */      return ((Node)node).getNodeType();
/* 454:    */    }
/* 455:455 */    return 0;
/* 456:    */  }
/* 457:    */  
/* 458:    */  public Object getDocument(String uri) throws FunctionCallException
/* 459:    */  {
/* 460:    */    try
/* 461:    */    {
/* 462:462 */      return getSAXReader().read(uri);
/* 463:    */    }
/* 464:    */    catch (DocumentException e)
/* 465:    */    {
/* 466:466 */      throw new FunctionCallException("Failed to parse document for URI: " + uri, e);
/* 467:    */    }
/* 468:    */  }
/* 469:    */  
/* 470:    */  public String getProcessingInstructionTarget(Object obj)
/* 471:    */  {
/* 472:472 */    ProcessingInstruction pi = (ProcessingInstruction)obj;
/* 473:    */    
/* 474:474 */    return pi.getTarget();
/* 475:    */  }
/* 476:    */  
/* 477:    */  public String getProcessingInstructionData(Object obj)
/* 478:    */  {
/* 479:479 */    ProcessingInstruction pi = (ProcessingInstruction)obj;
/* 480:    */    
/* 481:481 */    return pi.getText();
/* 482:    */  }
/* 483:    */  
/* 486:    */  public SAXReader getSAXReader()
/* 487:    */  {
/* 488:488 */    if (this.reader == null)
/* 489:    */    {
/* 490:490 */      this.reader = new SAXReader();
/* 491:491 */      this.reader.setMergeAdjacentText(true);
/* 492:    */    }
/* 493:493 */    return this.reader;
/* 494:    */  }
/* 495:    */  
/* 496:    */  public void setSAXReader(SAXReader reader)
/* 497:    */  {
/* 498:498 */    this.reader = reader;
/* 499:    */  }
/* 500:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.dom4j.DocumentNavigator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */