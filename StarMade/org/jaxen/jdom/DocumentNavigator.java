/*   1:    */package org.jaxen.jdom;
/*   2:    */
/*   3:    */import java.util.Collection;
/*   4:    */import java.util.HashMap;
/*   5:    */import java.util.Iterator;
/*   6:    */import java.util.List;
/*   7:    */import java.util.Map;
/*   8:    */import org.jaxen.DefaultNavigator;
/*   9:    */import org.jaxen.FunctionCallException;
/*  10:    */import org.jaxen.JaxenConstants;
/*  11:    */import org.jaxen.NamedAccessNavigator;
/*  12:    */import org.jaxen.Navigator;
/*  13:    */import org.jaxen.XPath;
/*  14:    */import org.jaxen.saxpath.SAXPathException;
/*  15:    */import org.jaxen.util.SingleObjectIterator;
/*  16:    */import org.jdom.Attribute;
/*  17:    */import org.jdom.CDATA;
/*  18:    */import org.jdom.Comment;
/*  19:    */import org.jdom.Document;
/*  20:    */import org.jdom.Element;
/*  21:    */import org.jdom.Namespace;
/*  22:    */import org.jdom.ProcessingInstruction;
/*  23:    */import org.jdom.Text;
/*  24:    */import org.jdom.input.SAXBuilder;
/*  25:    */
/*  92:    */public class DocumentNavigator
/*  93:    */  extends DefaultNavigator
/*  94:    */  implements NamedAccessNavigator
/*  95:    */{
/*  96:    */  private static final long serialVersionUID = -1636727587303584165L;
/*  97:    */  
/*  98:    */  private static class Singleton
/*  99:    */  {
/* 100:100 */    private static DocumentNavigator instance = new DocumentNavigator();
/* 101:    */  }
/* 102:    */  
/* 103:    */  public static Navigator getInstance()
/* 104:    */  {
/* 105:105 */    return Singleton.instance;
/* 106:    */  }
/* 107:    */  
/* 108:    */  public boolean isElement(Object obj)
/* 109:    */  {
/* 110:110 */    return obj instanceof Element;
/* 111:    */  }
/* 112:    */  
/* 113:    */  public boolean isComment(Object obj)
/* 114:    */  {
/* 115:115 */    return obj instanceof Comment;
/* 116:    */  }
/* 117:    */  
/* 118:    */  public boolean isText(Object obj)
/* 119:    */  {
/* 120:120 */    return ((obj instanceof Text)) || ((obj instanceof CDATA));
/* 121:    */  }
/* 122:    */  
/* 125:    */  public boolean isAttribute(Object obj)
/* 126:    */  {
/* 127:127 */    return obj instanceof Attribute;
/* 128:    */  }
/* 129:    */  
/* 130:    */  public boolean isProcessingInstruction(Object obj)
/* 131:    */  {
/* 132:132 */    return obj instanceof ProcessingInstruction;
/* 133:    */  }
/* 134:    */  
/* 135:    */  public boolean isDocument(Object obj)
/* 136:    */  {
/* 137:137 */    return obj instanceof Document;
/* 138:    */  }
/* 139:    */  
/* 140:    */  public boolean isNamespace(Object obj)
/* 141:    */  {
/* 142:142 */    return ((obj instanceof Namespace)) || ((obj instanceof XPathNamespace));
/* 143:    */  }
/* 144:    */  
/* 145:    */  public String getElementName(Object obj)
/* 146:    */  {
/* 147:147 */    Element elem = (Element)obj;
/* 148:    */    
/* 149:149 */    return elem.getName();
/* 150:    */  }
/* 151:    */  
/* 152:    */  public String getElementNamespaceUri(Object obj)
/* 153:    */  {
/* 154:154 */    Element elem = (Element)obj;
/* 155:    */    
/* 156:156 */    String uri = elem.getNamespaceURI();
/* 157:157 */    if ((uri != null) && (uri.length() == 0)) {
/* 158:158 */      return null;
/* 159:    */    }
/* 160:160 */    return uri;
/* 161:    */  }
/* 162:    */  
/* 163:    */  public String getAttributeName(Object obj)
/* 164:    */  {
/* 165:165 */    Attribute attr = (Attribute)obj;
/* 166:    */    
/* 167:167 */    return attr.getName();
/* 168:    */  }
/* 169:    */  
/* 170:    */  public String getAttributeNamespaceUri(Object obj)
/* 171:    */  {
/* 172:172 */    Attribute attr = (Attribute)obj;
/* 173:    */    
/* 174:174 */    String uri = attr.getNamespaceURI();
/* 175:175 */    if ((uri != null) && (uri.length() == 0)) {
/* 176:176 */      return null;
/* 177:    */    }
/* 178:178 */    return uri;
/* 179:    */  }
/* 180:    */  
/* 181:    */  public Iterator getChildAxisIterator(Object contextNode)
/* 182:    */  {
/* 183:183 */    if ((contextNode instanceof Element))
/* 184:    */    {
/* 185:185 */      return ((Element)contextNode).getContent().iterator();
/* 186:    */    }
/* 187:187 */    if ((contextNode instanceof Document))
/* 188:    */    {
/* 189:189 */      return ((Document)contextNode).getContent().iterator();
/* 190:    */    }
/* 191:    */    
/* 192:192 */    return JaxenConstants.EMPTY_ITERATOR;
/* 193:    */  }
/* 194:    */  
/* 206:    */  public Iterator getChildAxisIterator(Object contextNode, String localName, String namespacePrefix, String namespaceURI)
/* 207:    */  {
/* 208:208 */    if ((contextNode instanceof Element)) {
/* 209:209 */      Element node = (Element)contextNode;
/* 210:210 */      if (namespaceURI == null) {
/* 211:211 */        return node.getChildren(localName).iterator();
/* 212:    */      }
/* 213:213 */      return node.getChildren(localName, Namespace.getNamespace(namespacePrefix, namespaceURI)).iterator();
/* 214:    */    }
/* 215:215 */    if ((contextNode instanceof Document)) {
/* 216:216 */      Document node = (Document)contextNode;
/* 217:    */      
/* 218:218 */      Element el = node.getRootElement();
/* 219:219 */      if (!el.getName().equals(localName)) {
/* 220:220 */        return JaxenConstants.EMPTY_ITERATOR;
/* 221:    */      }
/* 222:222 */      if (namespaceURI != null)
/* 223:    */      {
/* 224:224 */        if (!Namespace.getNamespace(namespacePrefix, namespaceURI).equals(el.getNamespace())) {
/* 225:225 */          return JaxenConstants.EMPTY_ITERATOR;
/* 226:    */        }
/* 227:    */      }
/* 228:228 */      else if (el.getNamespace() != Namespace.NO_NAMESPACE) {
/* 229:229 */        return JaxenConstants.EMPTY_ITERATOR;
/* 230:    */      }
/* 231:    */      
/* 232:232 */      return new SingleObjectIterator(el);
/* 233:    */    }
/* 234:    */    
/* 235:235 */    return JaxenConstants.EMPTY_ITERATOR;
/* 236:    */  }
/* 237:    */  
/* 238:    */  public Iterator getNamespaceAxisIterator(Object contextNode)
/* 239:    */  {
/* 240:240 */    if (!(contextNode instanceof Element))
/* 241:    */    {
/* 242:242 */      return JaxenConstants.EMPTY_ITERATOR;
/* 243:    */    }
/* 244:    */    
/* 245:245 */    Element elem = (Element)contextNode;
/* 246:    */    
/* 247:247 */    Map nsMap = new HashMap();
/* 248:    */    
/* 249:249 */    Element current = elem;
/* 250:    */    
/* 251:251 */    while (current != null)
/* 252:    */    {
/* 253:253 */      Namespace ns = current.getNamespace();
/* 254:    */      
/* 255:255 */      if ((ns != Namespace.NO_NAMESPACE) && 
/* 256:256 */        (!nsMap.containsKey(ns.getPrefix()))) {
/* 257:257 */        nsMap.put(ns.getPrefix(), new XPathNamespace(elem, ns));
/* 258:    */      }
/* 259:    */      
/* 260:260 */      Iterator additional = current.getAdditionalNamespaces().iterator();
/* 261:    */      
/* 262:262 */      while (additional.hasNext())
/* 263:    */      {
/* 264:264 */        ns = (Namespace)additional.next();
/* 265:265 */        if (!nsMap.containsKey(ns.getPrefix())) {
/* 266:266 */          nsMap.put(ns.getPrefix(), new XPathNamespace(elem, ns));
/* 267:    */        }
/* 268:    */      }
/* 269:269 */      Iterator attributes = current.getAttributes().iterator();
/* 270:    */      
/* 271:271 */      while (attributes.hasNext())
/* 272:    */      {
/* 273:273 */        Attribute attribute = (Attribute)attributes.next();
/* 274:    */        
/* 275:275 */        Namespace attrNS = attribute.getNamespace();
/* 276:    */        
/* 277:277 */        if ((attrNS != Namespace.NO_NAMESPACE) && 
/* 278:278 */          (!nsMap.containsKey(attrNS.getPrefix()))) {
/* 279:279 */          nsMap.put(attrNS.getPrefix(), new XPathNamespace(elem, attrNS));
/* 280:    */        }
/* 281:    */      }
/* 282:    */      
/* 283:283 */      if ((current.getParent() instanceof Element)) {
/* 284:284 */        current = (Element)current.getParent();
/* 285:    */      } else {
/* 286:286 */        current = null;
/* 287:    */      }
/* 288:    */    }
/* 289:    */    
/* 290:290 */    nsMap.put("xml", new XPathNamespace(elem, Namespace.XML_NAMESPACE));
/* 291:    */    
/* 292:292 */    return nsMap.values().iterator();
/* 293:    */  }
/* 294:    */  
/* 295:    */  public Iterator getParentAxisIterator(Object contextNode)
/* 296:    */  {
/* 297:297 */    Object parent = null;
/* 298:    */    
/* 299:299 */    if ((contextNode instanceof Document))
/* 300:    */    {
/* 301:301 */      return JaxenConstants.EMPTY_ITERATOR;
/* 302:    */    }
/* 303:303 */    if ((contextNode instanceof Element))
/* 304:    */    {
/* 305:305 */      parent = ((Element)contextNode).getParent();
/* 306:    */      
/* 307:307 */      if (parent == null)
/* 308:    */      {
/* 309:309 */        if (((Element)contextNode).isRootElement())
/* 310:    */        {
/* 311:311 */          parent = ((Element)contextNode).getDocument();
/* 312:    */        }
/* 313:    */      }
/* 314:    */    }
/* 315:315 */    else if ((contextNode instanceof Attribute))
/* 316:    */    {
/* 317:317 */      parent = ((Attribute)contextNode).getParent();
/* 318:    */    }
/* 319:319 */    else if ((contextNode instanceof XPathNamespace))
/* 320:    */    {
/* 321:321 */      parent = ((XPathNamespace)contextNode).getJDOMElement();
/* 322:    */    }
/* 323:323 */    else if ((contextNode instanceof ProcessingInstruction))
/* 324:    */    {
/* 325:325 */      parent = ((ProcessingInstruction)contextNode).getParent();
/* 326:    */    }
/* 327:327 */    else if ((contextNode instanceof Comment))
/* 328:    */    {
/* 329:329 */      parent = ((Comment)contextNode).getParent();
/* 330:    */    }
/* 331:331 */    else if ((contextNode instanceof Text))
/* 332:    */    {
/* 333:333 */      parent = ((Text)contextNode).getParent();
/* 334:    */    }
/* 335:    */    
/* 336:336 */    if (parent != null)
/* 337:    */    {
/* 338:338 */      return new SingleObjectIterator(parent);
/* 339:    */    }
/* 340:    */    
/* 341:341 */    return JaxenConstants.EMPTY_ITERATOR;
/* 342:    */  }
/* 343:    */  
/* 344:    */  public Iterator getAttributeAxisIterator(Object contextNode)
/* 345:    */  {
/* 346:346 */    if (!(contextNode instanceof Element))
/* 347:    */    {
/* 348:348 */      return JaxenConstants.EMPTY_ITERATOR;
/* 349:    */    }
/* 350:    */    
/* 351:351 */    Element elem = (Element)contextNode;
/* 352:    */    
/* 353:353 */    return elem.getAttributes().iterator();
/* 354:    */  }
/* 355:    */  
/* 367:    */  public Iterator getAttributeAxisIterator(Object contextNode, String localName, String namespacePrefix, String namespaceURI)
/* 368:    */  {
/* 369:369 */    if ((contextNode instanceof Element)) {
/* 370:370 */      Element node = (Element)contextNode;
/* 371:371 */      Namespace namespace = namespaceURI == null ? Namespace.NO_NAMESPACE : Namespace.getNamespace(namespacePrefix, namespaceURI);
/* 372:    */      
/* 373:373 */      Attribute attr = node.getAttribute(localName, namespace);
/* 374:374 */      if (attr != null) {
/* 375:375 */        return new SingleObjectIterator(attr);
/* 376:    */      }
/* 377:    */    }
/* 378:378 */    return JaxenConstants.EMPTY_ITERATOR;
/* 379:    */  }
/* 380:    */  
/* 383:    */  public XPath parseXPath(String xpath)
/* 384:    */    throws SAXPathException
/* 385:    */  {
/* 386:386 */    return new JDOMXPath(xpath);
/* 387:    */  }
/* 388:    */  
/* 389:    */  public Object getDocumentNode(Object contextNode)
/* 390:    */  {
/* 391:391 */    if ((contextNode instanceof Document))
/* 392:    */    {
/* 393:393 */      return contextNode;
/* 394:    */    }
/* 395:    */    
/* 396:396 */    Element elem = (Element)contextNode;
/* 397:    */    
/* 398:398 */    return elem.getDocument();
/* 399:    */  }
/* 400:    */  
/* 401:    */  public String getElementQName(Object obj)
/* 402:    */  {
/* 403:403 */    Element elem = (Element)obj;
/* 404:    */    
/* 405:405 */    String prefix = elem.getNamespacePrefix();
/* 406:    */    
/* 407:407 */    if ((prefix == null) || (prefix.length() == 0))
/* 408:    */    {
/* 409:409 */      return elem.getName();
/* 410:    */    }
/* 411:    */    
/* 412:412 */    return prefix + ":" + elem.getName();
/* 413:    */  }
/* 414:    */  
/* 415:    */  public String getAttributeQName(Object obj)
/* 416:    */  {
/* 417:417 */    Attribute attr = (Attribute)obj;
/* 418:    */    
/* 419:419 */    String prefix = attr.getNamespacePrefix();
/* 420:    */    
/* 421:421 */    if ((prefix == null) || ("".equals(prefix)))
/* 422:    */    {
/* 423:423 */      return attr.getName();
/* 424:    */    }
/* 425:    */    
/* 426:426 */    return prefix + ":" + attr.getName();
/* 427:    */  }
/* 428:    */  
/* 429:    */  public String getNamespaceStringValue(Object obj)
/* 430:    */  {
/* 431:431 */    if ((obj instanceof Namespace))
/* 432:    */    {
/* 433:433 */      Namespace ns = (Namespace)obj;
/* 434:434 */      return ns.getURI();
/* 435:    */    }
/* 436:    */    
/* 437:437 */    XPathNamespace ns = (XPathNamespace)obj;
/* 438:438 */    return ns.getJDOMNamespace().getURI();
/* 439:    */  }
/* 440:    */  
/* 443:    */  public String getNamespacePrefix(Object obj)
/* 444:    */  {
/* 445:445 */    if ((obj instanceof Namespace))
/* 446:    */    {
/* 447:447 */      Namespace ns = (Namespace)obj;
/* 448:448 */      return ns.getPrefix();
/* 449:    */    }
/* 450:    */    
/* 451:451 */    XPathNamespace ns = (XPathNamespace)obj;
/* 452:452 */    return ns.getJDOMNamespace().getPrefix();
/* 453:    */  }
/* 454:    */  
/* 456:    */  public String getTextStringValue(Object obj)
/* 457:    */  {
/* 458:458 */    if ((obj instanceof Text))
/* 459:    */    {
/* 460:460 */      return ((Text)obj).getText();
/* 461:    */    }
/* 462:    */    
/* 463:463 */    if ((obj instanceof CDATA))
/* 464:    */    {
/* 465:465 */      return ((CDATA)obj).getText();
/* 466:    */    }
/* 467:    */    
/* 468:468 */    return "";
/* 469:    */  }
/* 470:    */  
/* 471:    */  public String getAttributeStringValue(Object obj)
/* 472:    */  {
/* 473:473 */    Attribute attr = (Attribute)obj;
/* 474:    */    
/* 475:475 */    return attr.getValue();
/* 476:    */  }
/* 477:    */  
/* 478:    */  public String getElementStringValue(Object obj)
/* 479:    */  {
/* 480:480 */    Element elem = (Element)obj;
/* 481:    */    
/* 482:482 */    StringBuffer buf = new StringBuffer();
/* 483:    */    
/* 484:484 */    List content = elem.getContent();
/* 485:485 */    Iterator contentIter = content.iterator();
/* 486:486 */    Object each = null;
/* 487:    */    
/* 488:488 */    while (contentIter.hasNext())
/* 489:    */    {
/* 490:490 */      each = contentIter.next();
/* 491:    */      
/* 492:492 */      if ((each instanceof Text))
/* 493:    */      {
/* 494:494 */        buf.append(((Text)each).getText());
/* 495:    */      }
/* 496:496 */      else if ((each instanceof CDATA))
/* 497:    */      {
/* 498:498 */        buf.append(((CDATA)each).getText());
/* 499:    */      }
/* 500:500 */      else if ((each instanceof Element))
/* 501:    */      {
/* 502:502 */        buf.append(getElementStringValue(each));
/* 503:    */      }
/* 504:    */    }
/* 505:    */    
/* 506:506 */    return buf.toString();
/* 507:    */  }
/* 508:    */  
/* 509:    */  public String getProcessingInstructionTarget(Object obj)
/* 510:    */  {
/* 511:511 */    ProcessingInstruction pi = (ProcessingInstruction)obj;
/* 512:    */    
/* 513:513 */    return pi.getTarget();
/* 514:    */  }
/* 515:    */  
/* 516:    */  public String getProcessingInstructionData(Object obj)
/* 517:    */  {
/* 518:518 */    ProcessingInstruction pi = (ProcessingInstruction)obj;
/* 519:    */    
/* 520:520 */    return pi.getData();
/* 521:    */  }
/* 522:    */  
/* 523:    */  public String getCommentStringValue(Object obj)
/* 524:    */  {
/* 525:525 */    Comment cmt = (Comment)obj;
/* 526:    */    
/* 527:527 */    return cmt.getText();
/* 528:    */  }
/* 529:    */  
/* 530:    */  public String translateNamespacePrefixToUri(String prefix, Object context)
/* 531:    */  {
/* 532:532 */    Element element = null;
/* 533:533 */    if ((context instanceof Element))
/* 534:    */    {
/* 535:535 */      element = (Element)context;
/* 536:    */    }
/* 537:537 */    else if ((context instanceof Text))
/* 538:    */    {
/* 539:539 */      element = (Element)((Text)context).getParent();
/* 540:    */    }
/* 541:541 */    else if ((context instanceof Attribute))
/* 542:    */    {
/* 543:543 */      element = ((Attribute)context).getParent();
/* 544:    */    }
/* 545:545 */    else if ((context instanceof XPathNamespace))
/* 546:    */    {
/* 547:547 */      element = ((XPathNamespace)context).getJDOMElement();
/* 548:    */    }
/* 549:549 */    else if ((context instanceof Comment))
/* 550:    */    {
/* 551:551 */      element = (Element)((Comment)context).getParent();
/* 552:    */    }
/* 553:553 */    else if ((context instanceof ProcessingInstruction))
/* 554:    */    {
/* 555:555 */      element = (Element)((ProcessingInstruction)context).getParent();
/* 556:    */    }
/* 557:    */    
/* 558:558 */    if (element != null)
/* 559:    */    {
/* 560:560 */      Namespace namespace = element.getNamespace(prefix);
/* 561:    */      
/* 562:562 */      if (namespace != null)
/* 563:    */      {
/* 564:564 */        return namespace.getURI();
/* 565:    */      }
/* 566:    */    }
/* 567:567 */    return null;
/* 568:    */  }
/* 569:    */  
/* 570:    */  public Object getDocument(String url) throws FunctionCallException
/* 571:    */  {
/* 572:    */    try
/* 573:    */    {
/* 574:574 */      SAXBuilder builder = new SAXBuilder();
/* 575:    */      
/* 576:576 */      return builder.build(url);
/* 577:    */    }
/* 578:    */    catch (Exception e)
/* 579:    */    {
/* 580:580 */      throw new FunctionCallException(e.getMessage());
/* 581:    */    }
/* 582:    */  }
/* 583:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.jdom.DocumentNavigator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */