/*    1:     */package org.dom4j.tree;
/*    2:     */
/*    3:     */import java.util.ArrayList;
/*    4:     */import java.util.Iterator;
/*    5:     */import java.util.List;
/*    6:     */import org.dom4j.Attribute;
/*    7:     */import org.dom4j.Branch;
/*    8:     */import org.dom4j.Document;
/*    9:     */import org.dom4j.DocumentFactory;
/*   10:     */import org.dom4j.Element;
/*   11:     */import org.dom4j.IllegalAddException;
/*   12:     */import org.dom4j.Namespace;
/*   13:     */import org.dom4j.Node;
/*   14:     */import org.dom4j.ProcessingInstruction;
/*   15:     */import org.dom4j.QName;
/*   16:     */
/*   33:     */public class DefaultElement
/*   34:     */  extends AbstractElement
/*   35:     */{
/*   36:  36 */  private static final transient DocumentFactory DOCUMENT_FACTORY = ;
/*   37:     */  
/*   41:     */  private QName qname;
/*   42:     */  
/*   46:     */  private Branch parentBranch;
/*   47:     */  
/*   50:     */  private Object content;
/*   51:     */  
/*   54:     */  private Object attributes;
/*   55:     */  
/*   59:     */  public DefaultElement(String name)
/*   60:     */  {
/*   61:  61 */    this.qname = DOCUMENT_FACTORY.createQName(name);
/*   62:     */  }
/*   63:     */  
/*   64:     */  public DefaultElement(QName qname) {
/*   65:  65 */    this.qname = qname;
/*   66:     */  }
/*   67:     */  
/*   68:     */  public DefaultElement(QName qname, int attributeCount) {
/*   69:  69 */    this.qname = qname;
/*   70:     */    
/*   71:  71 */    if (attributeCount > 1) {
/*   72:  72 */      this.attributes = new ArrayList(attributeCount);
/*   73:     */    }
/*   74:     */  }
/*   75:     */  
/*   76:     */  public DefaultElement(String name, Namespace namespace) {
/*   77:  77 */    this.qname = DOCUMENT_FACTORY.createQName(name, namespace);
/*   78:     */  }
/*   79:     */  
/*   80:     */  public Element getParent() {
/*   81:  81 */    Element result = null;
/*   82:     */    
/*   83:  83 */    if ((this.parentBranch instanceof Element)) {
/*   84:  84 */      result = (Element)this.parentBranch;
/*   85:     */    }
/*   86:     */    
/*   87:  87 */    return result;
/*   88:     */  }
/*   89:     */  
/*   90:     */  public void setParent(Element parent) {
/*   91:  91 */    if (((this.parentBranch instanceof Element)) || (parent != null)) {
/*   92:  92 */      this.parentBranch = parent;
/*   93:     */    }
/*   94:     */  }
/*   95:     */  
/*   96:     */  public Document getDocument() {
/*   97:  97 */    if ((this.parentBranch instanceof Document))
/*   98:  98 */      return (Document)this.parentBranch;
/*   99:  99 */    if ((this.parentBranch instanceof Element)) {
/*  100: 100 */      Element parent = (Element)this.parentBranch;
/*  101:     */      
/*  102: 102 */      return parent.getDocument();
/*  103:     */    }
/*  104:     */    
/*  105: 105 */    return null;
/*  106:     */  }
/*  107:     */  
/*  108:     */  public void setDocument(Document document) {
/*  109: 109 */    if (((this.parentBranch instanceof Document)) || (document != null)) {
/*  110: 110 */      this.parentBranch = document;
/*  111:     */    }
/*  112:     */  }
/*  113:     */  
/*  114:     */  public boolean supportsParent() {
/*  115: 115 */    return true;
/*  116:     */  }
/*  117:     */  
/*  118:     */  public QName getQName() {
/*  119: 119 */    return this.qname;
/*  120:     */  }
/*  121:     */  
/*  122:     */  public void setQName(QName name) {
/*  123: 123 */    this.qname = name;
/*  124:     */  }
/*  125:     */  
/*  126:     */  public String getText() {
/*  127: 127 */    Object contentShadow = this.content;
/*  128:     */    
/*  129: 129 */    if ((contentShadow instanceof List)) {
/*  130: 130 */      return super.getText();
/*  131:     */    }
/*  132: 132 */    if (contentShadow != null) {
/*  133: 133 */      return getContentAsText(contentShadow);
/*  134:     */    }
/*  135: 135 */    return "";
/*  136:     */  }
/*  137:     */  
/*  139:     */  public String getStringValue()
/*  140:     */  {
/*  141: 141 */    Object contentShadow = this.content;
/*  142:     */    
/*  143: 143 */    if ((contentShadow instanceof List)) {
/*  144: 144 */      List list = (List)contentShadow;
/*  145:     */      
/*  146: 146 */      int size = list.size();
/*  147:     */      
/*  148: 148 */      if (size > 0) {
/*  149: 149 */        if (size == 1)
/*  150:     */        {
/*  151: 151 */          return getContentAsStringValue(list.get(0));
/*  152:     */        }
/*  153: 153 */        StringBuffer buffer = new StringBuffer();
/*  154:     */        
/*  155: 155 */        for (int i = 0; i < size; i++) {
/*  156: 156 */          Object node = list.get(i);
/*  157:     */          
/*  158: 158 */          String string = getContentAsStringValue(node);
/*  159:     */          
/*  160: 160 */          if (string.length() > 0)
/*  161:     */          {
/*  167: 167 */            buffer.append(string);
/*  168:     */          }
/*  169:     */        }
/*  170:     */        
/*  171: 171 */        return buffer.toString();
/*  172:     */      }
/*  173:     */      
/*  174:     */    }
/*  175: 175 */    else if (contentShadow != null) {
/*  176: 176 */      return getContentAsStringValue(contentShadow);
/*  177:     */    }
/*  178:     */    
/*  180: 180 */    return "";
/*  181:     */  }
/*  182:     */  
/*  183:     */  public Object clone() {
/*  184: 184 */    DefaultElement answer = (DefaultElement)super.clone();
/*  185:     */    
/*  186: 186 */    if (answer != this) {
/*  187: 187 */      answer.content = null;
/*  188:     */      
/*  189: 189 */      answer.attributes = null;
/*  190:     */      
/*  191: 191 */      answer.appendAttributes(this);
/*  192:     */      
/*  193: 193 */      answer.appendContent(this);
/*  194:     */    }
/*  195:     */    
/*  196: 196 */    return answer;
/*  197:     */  }
/*  198:     */  
/*  199:     */  public Namespace getNamespaceForPrefix(String prefix) {
/*  200: 200 */    if (prefix == null) {
/*  201: 201 */      prefix = "";
/*  202:     */    }
/*  203:     */    
/*  204: 204 */    if (prefix.equals(getNamespacePrefix()))
/*  205: 205 */      return getNamespace();
/*  206: 206 */    if (prefix.equals("xml")) {
/*  207: 207 */      return Namespace.XML_NAMESPACE;
/*  208:     */    }
/*  209: 209 */    Object contentShadow = this.content;
/*  210:     */    
/*  211: 211 */    if ((contentShadow instanceof List)) {
/*  212: 212 */      List list = (List)contentShadow;
/*  213:     */      
/*  214: 214 */      int size = list.size();
/*  215:     */      
/*  216: 216 */      for (int i = 0; i < size; i++) {
/*  217: 217 */        Object object = list.get(i);
/*  218:     */        
/*  219: 219 */        if ((object instanceof Namespace)) {
/*  220: 220 */          Namespace namespace = (Namespace)object;
/*  221:     */          
/*  222: 222 */          if (prefix.equals(namespace.getPrefix())) {
/*  223: 223 */            return namespace;
/*  224:     */          }
/*  225:     */        }
/*  226:     */      }
/*  227: 227 */    } else if ((contentShadow instanceof Namespace)) {
/*  228: 228 */      Namespace namespace = (Namespace)contentShadow;
/*  229:     */      
/*  230: 230 */      if (prefix.equals(namespace.getPrefix())) {
/*  231: 231 */        return namespace;
/*  232:     */      }
/*  233:     */    }
/*  234:     */    
/*  236: 236 */    Element parent = getParent();
/*  237:     */    
/*  238: 238 */    if (parent != null) {
/*  239: 239 */      Namespace answer = parent.getNamespaceForPrefix(prefix);
/*  240:     */      
/*  241: 241 */      if (answer != null) {
/*  242: 242 */        return answer;
/*  243:     */      }
/*  244:     */    }
/*  245:     */    
/*  246: 246 */    if ((prefix == null) || (prefix.length() <= 0)) {
/*  247: 247 */      return Namespace.NO_NAMESPACE;
/*  248:     */    }
/*  249:     */    
/*  250: 250 */    return null;
/*  251:     */  }
/*  252:     */  
/*  253:     */  public Namespace getNamespaceForURI(String uri) {
/*  254: 254 */    if ((uri == null) || (uri.length() <= 0))
/*  255: 255 */      return Namespace.NO_NAMESPACE;
/*  256: 256 */    if (uri.equals(getNamespaceURI())) {
/*  257: 257 */      return getNamespace();
/*  258:     */    }
/*  259: 259 */    Object contentShadow = this.content;
/*  260:     */    
/*  261: 261 */    if ((contentShadow instanceof List)) {
/*  262: 262 */      List list = (List)contentShadow;
/*  263:     */      
/*  264: 264 */      int size = list.size();
/*  265:     */      
/*  266: 266 */      for (int i = 0; i < size; i++) {
/*  267: 267 */        Object object = list.get(i);
/*  268:     */        
/*  269: 269 */        if ((object instanceof Namespace)) {
/*  270: 270 */          Namespace namespace = (Namespace)object;
/*  271:     */          
/*  272: 272 */          if (uri.equals(namespace.getURI())) {
/*  273: 273 */            return namespace;
/*  274:     */          }
/*  275:     */        }
/*  276:     */      }
/*  277: 277 */    } else if ((contentShadow instanceof Namespace)) {
/*  278: 278 */      Namespace namespace = (Namespace)contentShadow;
/*  279:     */      
/*  280: 280 */      if (uri.equals(namespace.getURI())) {
/*  281: 281 */        return namespace;
/*  282:     */      }
/*  283:     */    }
/*  284:     */    
/*  285: 285 */    Element parent = getParent();
/*  286:     */    
/*  287: 287 */    if (parent != null) {
/*  288: 288 */      return parent.getNamespaceForURI(uri);
/*  289:     */    }
/*  290:     */    
/*  291: 291 */    return null;
/*  292:     */  }
/*  293:     */  
/*  294:     */  public List declaredNamespaces()
/*  295:     */  {
/*  296: 296 */    BackedList answer = createResultList();
/*  297:     */    
/*  303: 303 */    Object contentShadow = this.content;
/*  304:     */    
/*  305: 305 */    if ((contentShadow instanceof List)) {
/*  306: 306 */      List list = (List)contentShadow;
/*  307:     */      
/*  308: 308 */      int size = list.size();
/*  309:     */      
/*  310: 310 */      for (int i = 0; i < size; i++) {
/*  311: 311 */        Object object = list.get(i);
/*  312:     */        
/*  313: 313 */        if ((object instanceof Namespace)) {
/*  314: 314 */          answer.addLocal(object);
/*  315:     */        }
/*  316:     */      }
/*  317:     */    }
/*  318: 318 */    else if ((contentShadow instanceof Namespace)) {
/*  319: 319 */      answer.addLocal(contentShadow);
/*  320:     */    }
/*  321:     */    
/*  323: 323 */    return answer;
/*  324:     */  }
/*  325:     */  
/*  326:     */  public List additionalNamespaces() {
/*  327: 327 */    Object contentShadow = this.content;
/*  328:     */    
/*  329: 329 */    if ((contentShadow instanceof List)) {
/*  330: 330 */      List list = (List)contentShadow;
/*  331:     */      
/*  332: 332 */      int size = list.size();
/*  333:     */      
/*  334: 334 */      BackedList answer = createResultList();
/*  335:     */      
/*  336: 336 */      for (int i = 0; i < size; i++) {
/*  337: 337 */        Object object = list.get(i);
/*  338:     */        
/*  339: 339 */        if ((object instanceof Namespace)) {
/*  340: 340 */          Namespace namespace = (Namespace)object;
/*  341:     */          
/*  342: 342 */          if (!namespace.equals(getNamespace())) {
/*  343: 343 */            answer.addLocal(namespace);
/*  344:     */          }
/*  345:     */        }
/*  346:     */      }
/*  347:     */      
/*  348: 348 */      return answer;
/*  349:     */    }
/*  350: 350 */    if ((contentShadow instanceof Namespace)) {
/*  351: 351 */      Namespace namespace = (Namespace)contentShadow;
/*  352:     */      
/*  353: 353 */      if (namespace.equals(getNamespace())) {
/*  354: 354 */        return createEmptyList();
/*  355:     */      }
/*  356:     */      
/*  357: 357 */      return createSingleResultList(namespace);
/*  358:     */    }
/*  359: 359 */    return createEmptyList();
/*  360:     */  }
/*  361:     */  
/*  363:     */  public List additionalNamespaces(String defaultNamespaceURI)
/*  364:     */  {
/*  365: 365 */    Object contentShadow = this.content;
/*  366:     */    
/*  367: 367 */    if ((contentShadow instanceof List)) {
/*  368: 368 */      List list = (List)contentShadow;
/*  369:     */      
/*  370: 370 */      BackedList answer = createResultList();
/*  371:     */      
/*  372: 372 */      int size = list.size();
/*  373:     */      
/*  374: 374 */      for (int i = 0; i < size; i++) {
/*  375: 375 */        Object object = list.get(i);
/*  376:     */        
/*  377: 377 */        if ((object instanceof Namespace)) {
/*  378: 378 */          Namespace namespace = (Namespace)object;
/*  379:     */          
/*  380: 380 */          if (!defaultNamespaceURI.equals(namespace.getURI())) {
/*  381: 381 */            answer.addLocal(namespace);
/*  382:     */          }
/*  383:     */        }
/*  384:     */      }
/*  385:     */      
/*  386: 386 */      return answer;
/*  387:     */    }
/*  388: 388 */    if ((contentShadow instanceof Namespace)) {
/*  389: 389 */      Namespace namespace = (Namespace)contentShadow;
/*  390:     */      
/*  391: 391 */      if (!defaultNamespaceURI.equals(namespace.getURI())) {
/*  392: 392 */        return createSingleResultList(namespace);
/*  393:     */      }
/*  394:     */    }
/*  395:     */    
/*  397: 397 */    return createEmptyList();
/*  398:     */  }
/*  399:     */  
/*  400:     */  public List processingInstructions()
/*  401:     */  {
/*  402: 402 */    Object contentShadow = this.content;
/*  403:     */    
/*  404: 404 */    if ((contentShadow instanceof List)) {
/*  405: 405 */      List list = (List)contentShadow;
/*  406:     */      
/*  407: 407 */      BackedList answer = createResultList();
/*  408:     */      
/*  409: 409 */      int size = list.size();
/*  410:     */      
/*  411: 411 */      for (int i = 0; i < size; i++) {
/*  412: 412 */        Object object = list.get(i);
/*  413:     */        
/*  414: 414 */        if ((object instanceof ProcessingInstruction)) {
/*  415: 415 */          answer.addLocal(object);
/*  416:     */        }
/*  417:     */      }
/*  418:     */      
/*  419: 419 */      return answer;
/*  420:     */    }
/*  421: 421 */    if ((contentShadow instanceof ProcessingInstruction)) {
/*  422: 422 */      return createSingleResultList(contentShadow);
/*  423:     */    }
/*  424:     */    
/*  425: 425 */    return createEmptyList();
/*  426:     */  }
/*  427:     */  
/*  428:     */  public List processingInstructions(String target)
/*  429:     */  {
/*  430: 430 */    Object shadow = this.content;
/*  431:     */    
/*  432: 432 */    if ((shadow instanceof List)) {
/*  433: 433 */      List list = (List)shadow;
/*  434:     */      
/*  435: 435 */      BackedList answer = createResultList();
/*  436:     */      
/*  437: 437 */      int size = list.size();
/*  438:     */      
/*  439: 439 */      for (int i = 0; i < size; i++) {
/*  440: 440 */        Object object = list.get(i);
/*  441:     */        
/*  442: 442 */        if ((object instanceof ProcessingInstruction)) {
/*  443: 443 */          ProcessingInstruction pi = (ProcessingInstruction)object;
/*  444:     */          
/*  445: 445 */          if (target.equals(pi.getName())) {
/*  446: 446 */            answer.addLocal(pi);
/*  447:     */          }
/*  448:     */        }
/*  449:     */      }
/*  450:     */      
/*  451: 451 */      return answer;
/*  452:     */    }
/*  453: 453 */    if ((shadow instanceof ProcessingInstruction)) {
/*  454: 454 */      ProcessingInstruction pi = (ProcessingInstruction)shadow;
/*  455:     */      
/*  456: 456 */      if (target.equals(pi.getName())) {
/*  457: 457 */        return createSingleResultList(pi);
/*  458:     */      }
/*  459:     */    }
/*  460:     */    
/*  461: 461 */    return createEmptyList();
/*  462:     */  }
/*  463:     */  
/*  464:     */  public ProcessingInstruction processingInstruction(String target)
/*  465:     */  {
/*  466: 466 */    Object shadow = this.content;
/*  467:     */    
/*  468: 468 */    if ((shadow instanceof List)) {
/*  469: 469 */      List list = (List)shadow;
/*  470:     */      
/*  471: 471 */      int size = list.size();
/*  472:     */      
/*  473: 473 */      for (int i = 0; i < size; i++) {
/*  474: 474 */        Object object = list.get(i);
/*  475:     */        
/*  476: 476 */        if ((object instanceof ProcessingInstruction)) {
/*  477: 477 */          ProcessingInstruction pi = (ProcessingInstruction)object;
/*  478:     */          
/*  479: 479 */          if (target.equals(pi.getName())) {
/*  480: 480 */            return pi;
/*  481:     */          }
/*  482:     */        }
/*  483:     */      }
/*  484:     */    }
/*  485: 485 */    else if ((shadow instanceof ProcessingInstruction)) {
/*  486: 486 */      ProcessingInstruction pi = (ProcessingInstruction)shadow;
/*  487:     */      
/*  488: 488 */      if (target.equals(pi.getName())) {
/*  489: 489 */        return pi;
/*  490:     */      }
/*  491:     */    }
/*  492:     */    
/*  494: 494 */    return null;
/*  495:     */  }
/*  496:     */  
/*  497:     */  public boolean removeProcessingInstruction(String target) {
/*  498: 498 */    Object shadow = this.content;
/*  499:     */    Iterator iter;
/*  500: 500 */    if ((shadow instanceof List)) {
/*  501: 501 */      List list = (List)shadow;
/*  502:     */      
/*  503: 503 */      for (iter = list.iterator(); iter.hasNext();) {
/*  504: 504 */        Object object = iter.next();
/*  505:     */        
/*  506: 506 */        if ((object instanceof ProcessingInstruction)) {
/*  507: 507 */          ProcessingInstruction pi = (ProcessingInstruction)object;
/*  508:     */          
/*  509: 509 */          if (target.equals(pi.getName())) {
/*  510: 510 */            iter.remove();
/*  511:     */            
/*  512: 512 */            return true;
/*  513:     */          }
/*  514:     */        }
/*  515:     */      }
/*  516:     */    }
/*  517: 517 */    else if ((shadow instanceof ProcessingInstruction)) {
/*  518: 518 */      ProcessingInstruction pi = (ProcessingInstruction)shadow;
/*  519:     */      
/*  520: 520 */      if (target.equals(pi.getName())) {
/*  521: 521 */        this.content = null;
/*  522:     */        
/*  523: 523 */        return true;
/*  524:     */      }
/*  525:     */    }
/*  526:     */    
/*  528: 528 */    return false;
/*  529:     */  }
/*  530:     */  
/*  531:     */  public Element element(String name) {
/*  532: 532 */    Object contentShadow = this.content;
/*  533:     */    
/*  534: 534 */    if ((contentShadow instanceof List)) {
/*  535: 535 */      List list = (List)contentShadow;
/*  536:     */      
/*  537: 537 */      int size = list.size();
/*  538:     */      
/*  539: 539 */      for (int i = 0; i < size; i++) {
/*  540: 540 */        Object object = list.get(i);
/*  541:     */        
/*  542: 542 */        if ((object instanceof Element)) {
/*  543: 543 */          Element element = (Element)object;
/*  544:     */          
/*  545: 545 */          if (name.equals(element.getName())) {
/*  546: 546 */            return element;
/*  547:     */          }
/*  548:     */        }
/*  549:     */      }
/*  550:     */    }
/*  551: 551 */    else if ((contentShadow instanceof Element)) {
/*  552: 552 */      Element element = (Element)contentShadow;
/*  553:     */      
/*  554: 554 */      if (name.equals(element.getName())) {
/*  555: 555 */        return element;
/*  556:     */      }
/*  557:     */    }
/*  558:     */    
/*  560: 560 */    return null;
/*  561:     */  }
/*  562:     */  
/*  563:     */  public Element element(QName qName) {
/*  564: 564 */    Object contentShadow = this.content;
/*  565:     */    
/*  566: 566 */    if ((contentShadow instanceof List)) {
/*  567: 567 */      List list = (List)contentShadow;
/*  568:     */      
/*  569: 569 */      int size = list.size();
/*  570:     */      
/*  571: 571 */      for (int i = 0; i < size; i++) {
/*  572: 572 */        Object object = list.get(i);
/*  573:     */        
/*  574: 574 */        if ((object instanceof Element)) {
/*  575: 575 */          Element element = (Element)object;
/*  576:     */          
/*  577: 577 */          if (qName.equals(element.getQName())) {
/*  578: 578 */            return element;
/*  579:     */          }
/*  580:     */        }
/*  581:     */      }
/*  582:     */    }
/*  583: 583 */    else if ((contentShadow instanceof Element)) {
/*  584: 584 */      Element element = (Element)contentShadow;
/*  585:     */      
/*  586: 586 */      if (qName.equals(element.getQName())) {
/*  587: 587 */        return element;
/*  588:     */      }
/*  589:     */    }
/*  590:     */    
/*  592: 592 */    return null;
/*  593:     */  }
/*  594:     */  
/*  595:     */  public Element element(String name, Namespace namespace) {
/*  596: 596 */    return element(getDocumentFactory().createQName(name, namespace));
/*  597:     */  }
/*  598:     */  
/*  599:     */  public void setContent(List content) {
/*  600: 600 */    contentRemoved();
/*  601:     */    
/*  602: 602 */    if ((content instanceof ContentListFacade)) {
/*  603: 603 */      content = ((ContentListFacade)content).getBackingList();
/*  604:     */    }
/*  605:     */    
/*  606: 606 */    if (content == null) {
/*  607: 607 */      this.content = null;
/*  608:     */    } else {
/*  609: 609 */      int size = content.size();
/*  610:     */      
/*  611: 611 */      List newContent = createContentList(size);
/*  612:     */      
/*  613: 613 */      for (int i = 0; i < size; i++) {
/*  614: 614 */        Object object = content.get(i);
/*  615:     */        
/*  616: 616 */        if ((object instanceof Node)) {
/*  617: 617 */          Node node = (Node)object;
/*  618: 618 */          Element parent = node.getParent();
/*  619:     */          
/*  620: 620 */          if ((parent != null) && (parent != this)) {
/*  621: 621 */            node = (Node)node.clone();
/*  622:     */          }
/*  623:     */          
/*  624: 624 */          newContent.add(node);
/*  625: 625 */          childAdded(node);
/*  626: 626 */        } else if (object != null) {
/*  627: 627 */          String text = object.toString();
/*  628: 628 */          Node node = getDocumentFactory().createText(text);
/*  629: 629 */          newContent.add(node);
/*  630: 630 */          childAdded(node);
/*  631:     */        }
/*  632:     */      }
/*  633:     */      
/*  634: 634 */      this.content = newContent;
/*  635:     */    }
/*  636:     */  }
/*  637:     */  
/*  638:     */  public void clearContent() {
/*  639: 639 */    if (this.content != null) {
/*  640: 640 */      contentRemoved();
/*  641:     */      
/*  642: 642 */      this.content = null;
/*  643:     */    }
/*  644:     */  }
/*  645:     */  
/*  646:     */  public Node node(int index) {
/*  647: 647 */    if (index >= 0) {
/*  648: 648 */      Object contentShadow = this.content;
/*  649:     */      Object node;
/*  650:     */      Object node;
/*  651: 651 */      if ((contentShadow instanceof List)) {
/*  652: 652 */        List list = (List)contentShadow;
/*  653:     */        
/*  654: 654 */        if (index >= list.size()) {
/*  655: 655 */          return null;
/*  656:     */        }
/*  657:     */        
/*  658: 658 */        node = list.get(index);
/*  659:     */      } else {
/*  660: 660 */        node = index == 0 ? contentShadow : null;
/*  661:     */      }
/*  662:     */      
/*  663: 663 */      if (node != null) {
/*  664: 664 */        if ((node instanceof Node)) {
/*  665: 665 */          return (Node)node;
/*  666:     */        }
/*  667: 667 */        return new DefaultText(node.toString());
/*  668:     */      }
/*  669:     */    }
/*  670:     */    
/*  672: 672 */    return null;
/*  673:     */  }
/*  674:     */  
/*  675:     */  public int indexOf(Node node) {
/*  676: 676 */    Object contentShadow = this.content;
/*  677:     */    
/*  678: 678 */    if ((contentShadow instanceof List)) {
/*  679: 679 */      List list = (List)contentShadow;
/*  680:     */      
/*  681: 681 */      return list.indexOf(node);
/*  682:     */    }
/*  683: 683 */    if ((contentShadow != null) && (contentShadow.equals(node))) {
/*  684: 684 */      return 0;
/*  685:     */    }
/*  686: 686 */    return -1;
/*  687:     */  }
/*  688:     */  
/*  690:     */  public int nodeCount()
/*  691:     */  {
/*  692: 692 */    Object contentShadow = this.content;
/*  693:     */    
/*  694: 694 */    if ((contentShadow instanceof List)) {
/*  695: 695 */      List list = (List)contentShadow;
/*  696:     */      
/*  697: 697 */      return list.size();
/*  698:     */    }
/*  699: 699 */    return contentShadow != null ? 1 : 0;
/*  700:     */  }
/*  701:     */  
/*  702:     */  public Iterator nodeIterator()
/*  703:     */  {
/*  704: 704 */    Object contentShadow = this.content;
/*  705:     */    
/*  706: 706 */    if ((contentShadow instanceof List)) {
/*  707: 707 */      List list = (List)contentShadow;
/*  708:     */      
/*  709: 709 */      return list.iterator();
/*  710:     */    }
/*  711: 711 */    if (contentShadow != null) {
/*  712: 712 */      return createSingleIterator(contentShadow);
/*  713:     */    }
/*  714: 714 */    return EMPTY_ITERATOR;
/*  715:     */  }
/*  716:     */  
/*  718:     */  public List attributes()
/*  719:     */  {
/*  720: 720 */    return new ContentListFacade(this, attributeList());
/*  721:     */  }
/*  722:     */  
/*  723:     */  public void setAttributes(List attributes) {
/*  724: 724 */    if ((attributes instanceof ContentListFacade)) {
/*  725: 725 */      attributes = ((ContentListFacade)attributes).getBackingList();
/*  726:     */    }
/*  727:     */    
/*  728: 728 */    this.attributes = attributes;
/*  729:     */  }
/*  730:     */  
/*  731:     */  public Iterator attributeIterator() {
/*  732: 732 */    Object attributesShadow = this.attributes;
/*  733:     */    
/*  734: 734 */    if ((attributesShadow instanceof List)) {
/*  735: 735 */      List list = (List)attributesShadow;
/*  736:     */      
/*  737: 737 */      return list.iterator(); }
/*  738: 738 */    if (attributesShadow != null) {
/*  739: 739 */      return createSingleIterator(attributesShadow);
/*  740:     */    }
/*  741: 741 */    return EMPTY_ITERATOR;
/*  742:     */  }
/*  743:     */  
/*  744:     */  public Attribute attribute(int index)
/*  745:     */  {
/*  746: 746 */    Object attributesShadow = this.attributes;
/*  747:     */    
/*  748: 748 */    if ((attributesShadow instanceof List)) {
/*  749: 749 */      List list = (List)attributesShadow;
/*  750:     */      
/*  751: 751 */      return (Attribute)list.get(index); }
/*  752: 752 */    if ((attributesShadow != null) && (index == 0)) {
/*  753: 753 */      return (Attribute)attributesShadow;
/*  754:     */    }
/*  755: 755 */    return null;
/*  756:     */  }
/*  757:     */  
/*  758:     */  public int attributeCount()
/*  759:     */  {
/*  760: 760 */    Object attributesShadow = this.attributes;
/*  761:     */    
/*  762: 762 */    if ((attributesShadow instanceof List)) {
/*  763: 763 */      List list = (List)attributesShadow;
/*  764:     */      
/*  765: 765 */      return list.size();
/*  766:     */    }
/*  767: 767 */    return attributesShadow != null ? 1 : 0;
/*  768:     */  }
/*  769:     */  
/*  770:     */  public Attribute attribute(String name)
/*  771:     */  {
/*  772: 772 */    Object attributesShadow = this.attributes;
/*  773:     */    
/*  774: 774 */    if ((attributesShadow instanceof List)) {
/*  775: 775 */      List list = (List)attributesShadow;
/*  776:     */      
/*  777: 777 */      int size = list.size();
/*  778:     */      
/*  779: 779 */      for (int i = 0; i < size; i++) {
/*  780: 780 */        Attribute attribute = (Attribute)list.get(i);
/*  781:     */        
/*  782: 782 */        if (name.equals(attribute.getName())) {
/*  783: 783 */          return attribute;
/*  784:     */        }
/*  785:     */      }
/*  786: 786 */    } else if (attributesShadow != null) {
/*  787: 787 */      Attribute attribute = (Attribute)attributesShadow;
/*  788:     */      
/*  789: 789 */      if (name.equals(attribute.getName())) {
/*  790: 790 */        return attribute;
/*  791:     */      }
/*  792:     */    }
/*  793:     */    
/*  794: 794 */    return null;
/*  795:     */  }
/*  796:     */  
/*  797:     */  public Attribute attribute(QName qName) {
/*  798: 798 */    Object attributesShadow = this.attributes;
/*  799:     */    
/*  800: 800 */    if ((attributesShadow instanceof List)) {
/*  801: 801 */      List list = (List)attributesShadow;
/*  802:     */      
/*  803: 803 */      int size = list.size();
/*  804:     */      
/*  805: 805 */      for (int i = 0; i < size; i++) {
/*  806: 806 */        Attribute attribute = (Attribute)list.get(i);
/*  807:     */        
/*  808: 808 */        if (qName.equals(attribute.getQName())) {
/*  809: 809 */          return attribute;
/*  810:     */        }
/*  811:     */      }
/*  812: 812 */    } else if (attributesShadow != null) {
/*  813: 813 */      Attribute attribute = (Attribute)attributesShadow;
/*  814:     */      
/*  815: 815 */      if (qName.equals(attribute.getQName())) {
/*  816: 816 */        return attribute;
/*  817:     */      }
/*  818:     */    }
/*  819:     */    
/*  820: 820 */    return null;
/*  821:     */  }
/*  822:     */  
/*  823:     */  public Attribute attribute(String name, Namespace namespace) {
/*  824: 824 */    return attribute(getDocumentFactory().createQName(name, namespace));
/*  825:     */  }
/*  826:     */  
/*  827:     */  public void add(Attribute attribute) {
/*  828: 828 */    if (attribute.getParent() != null) {
/*  829: 829 */      String message = "The Attribute already has an existing parent \"" + attribute.getParent().getQualifiedName() + "\"";
/*  830:     */      
/*  832: 832 */      throw new IllegalAddException(this, attribute, message);
/*  833:     */    }
/*  834:     */    
/*  835: 835 */    if (attribute.getValue() == null)
/*  836:     */    {
/*  839: 839 */      Attribute oldAttribute = attribute(attribute.getQName());
/*  840:     */      
/*  841: 841 */      if (oldAttribute != null) {
/*  842: 842 */        remove(oldAttribute);
/*  843:     */      }
/*  844:     */    } else {
/*  845: 845 */      if (this.attributes == null) {
/*  846: 846 */        this.attributes = attribute;
/*  847:     */      } else {
/*  848: 848 */        attributeList().add(attribute);
/*  849:     */      }
/*  850:     */      
/*  851: 851 */      childAdded(attribute);
/*  852:     */    }
/*  853:     */  }
/*  854:     */  
/*  855:     */  public boolean remove(Attribute attribute) {
/*  856: 856 */    boolean answer = false;
/*  857: 857 */    Object attributesShadow = this.attributes;
/*  858:     */    
/*  859: 859 */    if ((attributesShadow instanceof List)) {
/*  860: 860 */      List list = (List)attributesShadow;
/*  861:     */      
/*  862: 862 */      answer = list.remove(attribute);
/*  863:     */      
/*  864: 864 */      if (!answer)
/*  865:     */      {
/*  866: 866 */        Attribute copy = attribute(attribute.getQName());
/*  867:     */        
/*  868: 868 */        if (copy != null) {
/*  869: 869 */          list.remove(copy);
/*  870:     */          
/*  871: 871 */          answer = true;
/*  872:     */        }
/*  873:     */      }
/*  874: 874 */    } else if (attributesShadow != null) {
/*  875: 875 */      if (attribute.equals(attributesShadow)) {
/*  876: 876 */        this.attributes = null;
/*  877:     */        
/*  878: 878 */        answer = true;
/*  879:     */      }
/*  880:     */      else {
/*  881: 881 */        Attribute other = (Attribute)attributesShadow;
/*  882:     */        
/*  883: 883 */        if (attribute.getQName().equals(other.getQName())) {
/*  884: 884 */          this.attributes = null;
/*  885:     */          
/*  886: 886 */          answer = true;
/*  887:     */        }
/*  888:     */      }
/*  889:     */    }
/*  890:     */    
/*  891: 891 */    if (answer) {
/*  892: 892 */      childRemoved(attribute);
/*  893:     */    }
/*  894:     */    
/*  895: 895 */    return answer;
/*  896:     */  }
/*  897:     */  
/*  899:     */  protected void addNewNode(Node node)
/*  900:     */  {
/*  901: 901 */    Object contentShadow = this.content;
/*  902:     */    
/*  903: 903 */    if (contentShadow == null) {
/*  904: 904 */      this.content = node;
/*  905:     */    }
/*  906: 906 */    else if ((contentShadow instanceof List)) {
/*  907: 907 */      List list = (List)contentShadow;
/*  908:     */      
/*  909: 909 */      list.add(node);
/*  910:     */    } else {
/*  911: 911 */      List list = createContentList();
/*  912:     */      
/*  913: 913 */      list.add(contentShadow);
/*  914:     */      
/*  915: 915 */      list.add(node);
/*  916:     */      
/*  917: 917 */      this.content = list;
/*  918:     */    }
/*  919:     */    
/*  921: 921 */    childAdded(node);
/*  922:     */  }
/*  923:     */  
/*  924:     */  protected boolean removeNode(Node node) {
/*  925: 925 */    boolean answer = false;
/*  926: 926 */    Object contentShadow = this.content;
/*  927:     */    
/*  928: 928 */    if (contentShadow != null) {
/*  929: 929 */      if (contentShadow == node) {
/*  930: 930 */        this.content = null;
/*  931:     */        
/*  932: 932 */        answer = true;
/*  933: 933 */      } else if ((contentShadow instanceof List)) {
/*  934: 934 */        List list = (List)contentShadow;
/*  935:     */        
/*  936: 936 */        answer = list.remove(node);
/*  937:     */      }
/*  938:     */    }
/*  939:     */    
/*  940: 940 */    if (answer) {
/*  941: 941 */      childRemoved(node);
/*  942:     */    }
/*  943:     */    
/*  944: 944 */    return answer;
/*  945:     */  }
/*  946:     */  
/*  947:     */  protected List contentList() {
/*  948: 948 */    Object contentShadow = this.content;
/*  949:     */    
/*  950: 950 */    if ((contentShadow instanceof List)) {
/*  951: 951 */      return (List)contentShadow;
/*  952:     */    }
/*  953: 953 */    List list = createContentList();
/*  954:     */    
/*  955: 955 */    if (contentShadow != null) {
/*  956: 956 */      list.add(contentShadow);
/*  957:     */    }
/*  958:     */    
/*  959: 959 */    this.content = list;
/*  960:     */    
/*  961: 961 */    return list;
/*  962:     */  }
/*  963:     */  
/*  964:     */  protected List attributeList()
/*  965:     */  {
/*  966: 966 */    Object attributesShadow = this.attributes;
/*  967:     */    
/*  968: 968 */    if ((attributesShadow instanceof List))
/*  969: 969 */      return (List)attributesShadow;
/*  970: 970 */    if (attributesShadow != null) {
/*  971: 971 */      List list = createAttributeList();
/*  972:     */      
/*  973: 973 */      list.add(attributesShadow);
/*  974:     */      
/*  975: 975 */      this.attributes = list;
/*  976:     */      
/*  977: 977 */      return list;
/*  978:     */    }
/*  979: 979 */    List list = createAttributeList();
/*  980:     */    
/*  981: 981 */    this.attributes = list;
/*  982:     */    
/*  983: 983 */    return list;
/*  984:     */  }
/*  985:     */  
/*  986:     */  protected List attributeList(int size)
/*  987:     */  {
/*  988: 988 */    Object attributesShadow = this.attributes;
/*  989:     */    
/*  990: 990 */    if ((attributesShadow instanceof List))
/*  991: 991 */      return (List)attributesShadow;
/*  992: 992 */    if (attributesShadow != null) {
/*  993: 993 */      List list = createAttributeList(size);
/*  994:     */      
/*  995: 995 */      list.add(attributesShadow);
/*  996:     */      
/*  997: 997 */      this.attributes = list;
/*  998:     */      
/*  999: 999 */      return list;
/* 1000:     */    }
/* 1001:1001 */    List list = createAttributeList(size);
/* 1002:     */    
/* 1003:1003 */    this.attributes = list;
/* 1004:     */    
/* 1005:1005 */    return list;
/* 1006:     */  }
/* 1007:     */  
/* 1008:     */  protected void setAttributeList(List attributeList)
/* 1009:     */  {
/* 1010:1010 */    this.attributes = attributeList;
/* 1011:     */  }
/* 1012:     */  
/* 1013:     */  protected DocumentFactory getDocumentFactory() {
/* 1014:1014 */    DocumentFactory factory = this.qname.getDocumentFactory();
/* 1015:     */    
/* 1016:1016 */    return factory != null ? factory : DOCUMENT_FACTORY;
/* 1017:     */  }
/* 1018:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.DefaultElement
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */