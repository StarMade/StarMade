/*    1:     */package org.dom4j.tree;
/*    2:     */
/*    3:     */import java.io.IOException;
/*    4:     */import java.io.StringWriter;
/*    5:     */import java.io.Writer;
/*    6:     */import java.util.ArrayList;
/*    7:     */import java.util.Collections;
/*    8:     */import java.util.Iterator;
/*    9:     */import java.util.List;
/*   10:     */import java.util.Map;
/*   11:     */import org.dom4j.Attribute;
/*   12:     */import org.dom4j.CDATA;
/*   13:     */import org.dom4j.CharacterData;
/*   14:     */import org.dom4j.Comment;
/*   15:     */import org.dom4j.Document;
/*   16:     */import org.dom4j.DocumentFactory;
/*   17:     */import org.dom4j.Element;
/*   18:     */import org.dom4j.Entity;
/*   19:     */import org.dom4j.IllegalAddException;
/*   20:     */import org.dom4j.Namespace;
/*   21:     */import org.dom4j.Node;
/*   22:     */import org.dom4j.ProcessingInstruction;
/*   23:     */import org.dom4j.QName;
/*   24:     */import org.dom4j.Text;
/*   25:     */import org.dom4j.Visitor;
/*   26:     */import org.dom4j.io.OutputFormat;
/*   27:     */import org.dom4j.io.XMLWriter;
/*   28:     */import org.xml.sax.Attributes;
/*   29:     */
/*   47:     */public abstract class AbstractElement
/*   48:     */  extends AbstractBranch
/*   49:     */  implements Element
/*   50:     */{
/*   51:  51 */  private static final DocumentFactory DOCUMENT_FACTORY = ;
/*   52:     */  
/*   54:  54 */  protected static final List EMPTY_LIST = Collections.EMPTY_LIST;
/*   55:     */  
/*   56:  56 */  protected static final Iterator EMPTY_ITERATOR = EMPTY_LIST.iterator();
/*   57:     */  
/*   59:     */  protected static final boolean VERBOSE_TOSTRING = false;
/*   60:     */  
/*   61:     */  protected static final boolean USE_STRINGVALUE_SEPARATOR = false;
/*   62:     */  
/*   64:     */  public short getNodeType()
/*   65:     */  {
/*   66:  66 */    return 1;
/*   67:     */  }
/*   68:     */  
/*   69:     */  public boolean isRootElement() {
/*   70:  70 */    Document document = getDocument();
/*   71:     */    
/*   72:  72 */    if (document != null) {
/*   73:  73 */      Element root = document.getRootElement();
/*   74:     */      
/*   75:  75 */      if (root == this) {
/*   76:  76 */        return true;
/*   77:     */      }
/*   78:     */    }
/*   79:     */    
/*   80:  80 */    return false;
/*   81:     */  }
/*   82:     */  
/*   83:     */  public void setName(String name) {
/*   84:  84 */    setQName(getDocumentFactory().createQName(name));
/*   85:     */  }
/*   86:     */  
/*   87:     */  public void setNamespace(Namespace namespace) {
/*   88:  88 */    setQName(getDocumentFactory().createQName(getName(), namespace));
/*   89:     */  }
/*   90:     */  
/*   98:     */  public String getXPathNameStep()
/*   99:     */  {
/*  100: 100 */    String uri = getNamespaceURI();
/*  101:     */    
/*  102: 102 */    if ((uri == null) || (uri.length() == 0)) {
/*  103: 103 */      return getName();
/*  104:     */    }
/*  105:     */    
/*  106: 106 */    String prefix = getNamespacePrefix();
/*  107:     */    
/*  108: 108 */    if ((prefix == null) || (prefix.length() == 0)) {
/*  109: 109 */      return "*[name()='" + getName() + "']";
/*  110:     */    }
/*  111:     */    
/*  112: 112 */    return getQualifiedName();
/*  113:     */  }
/*  114:     */  
/*  115:     */  public String getPath(Element context) {
/*  116: 116 */    if (this == context) {
/*  117: 117 */      return ".";
/*  118:     */    }
/*  119:     */    
/*  120: 120 */    Element parent = getParent();
/*  121:     */    
/*  122: 122 */    if (parent == null)
/*  123: 123 */      return "/" + getXPathNameStep();
/*  124: 124 */    if (parent == context) {
/*  125: 125 */      return getXPathNameStep();
/*  126:     */    }
/*  127:     */    
/*  128: 128 */    return parent.getPath(context) + "/" + getXPathNameStep();
/*  129:     */  }
/*  130:     */  
/*  131:     */  public String getUniquePath(Element context) {
/*  132: 132 */    Element parent = getParent();
/*  133:     */    
/*  134: 134 */    if (parent == null) {
/*  135: 135 */      return "/" + getXPathNameStep();
/*  136:     */    }
/*  137:     */    
/*  138: 138 */    StringBuffer buffer = new StringBuffer();
/*  139:     */    
/*  140: 140 */    if (parent != context) {
/*  141: 141 */      buffer.append(parent.getUniquePath(context));
/*  142:     */      
/*  143: 143 */      buffer.append("/");
/*  144:     */    }
/*  145:     */    
/*  146: 146 */    buffer.append(getXPathNameStep());
/*  147:     */    
/*  148: 148 */    List mySiblings = parent.elements(getQName());
/*  149:     */    
/*  150: 150 */    if (mySiblings.size() > 1) {
/*  151: 151 */      int idx = mySiblings.indexOf(this);
/*  152:     */      
/*  153: 153 */      if (idx >= 0) {
/*  154: 154 */        buffer.append("[");
/*  155:     */        
/*  156: 156 */        buffer.append(Integer.toString(++idx));
/*  157:     */        
/*  158: 158 */        buffer.append("]");
/*  159:     */      }
/*  160:     */    }
/*  161:     */    
/*  162: 162 */    return buffer.toString();
/*  163:     */  }
/*  164:     */  
/*  165:     */  public String asXML() {
/*  166:     */    try {
/*  167: 167 */      StringWriter out = new StringWriter();
/*  168: 168 */      XMLWriter writer = new XMLWriter(out, new OutputFormat());
/*  169:     */      
/*  170: 170 */      writer.write(this);
/*  171: 171 */      writer.flush();
/*  172:     */      
/*  173: 173 */      return out.toString();
/*  174:     */    } catch (IOException e) {
/*  175: 175 */      throw new RuntimeException("IOException while generating textual representation: " + e.getMessage());
/*  176:     */    }
/*  177:     */  }
/*  178:     */  
/*  179:     */  public void write(Writer out) throws IOException
/*  180:     */  {
/*  181: 181 */    XMLWriter writer = new XMLWriter(out, new OutputFormat());
/*  182: 182 */    writer.write(this);
/*  183:     */  }
/*  184:     */  
/*  193:     */  public void accept(Visitor visitor)
/*  194:     */  {
/*  195: 195 */    visitor.visit(this);
/*  196:     */    
/*  198: 198 */    int i = 0; for (int size = attributeCount(); i < size; i++) {
/*  199: 199 */      Attribute attribute = attribute(i);
/*  200:     */      
/*  201: 201 */      visitor.visit(attribute);
/*  202:     */    }
/*  203:     */    
/*  205: 205 */    int i = 0; for (int size = nodeCount(); i < size; i++) {
/*  206: 206 */      Node node = node(i);
/*  207:     */      
/*  208: 208 */      node.accept(visitor);
/*  209:     */    }
/*  210:     */  }
/*  211:     */  
/*  212:     */  public String toString() {
/*  213: 213 */    String uri = getNamespaceURI();
/*  214:     */    
/*  215: 215 */    if ((uri != null) && (uri.length() > 0))
/*  216:     */    {
/*  221: 221 */      return super.toString() + " [Element: <" + getQualifiedName() + " uri: " + uri + " attributes: " + attributeList() + "/>]";
/*  222:     */    }
/*  223:     */    
/*  231: 231 */    return super.toString() + " [Element: <" + getQualifiedName() + " attributes: " + attributeList() + "/>]";
/*  232:     */  }
/*  233:     */  
/*  238:     */  public Namespace getNamespace()
/*  239:     */  {
/*  240: 240 */    return getQName().getNamespace();
/*  241:     */  }
/*  242:     */  
/*  243:     */  public String getName() {
/*  244: 244 */    return getQName().getName();
/*  245:     */  }
/*  246:     */  
/*  247:     */  public String getNamespacePrefix() {
/*  248: 248 */    return getQName().getNamespacePrefix();
/*  249:     */  }
/*  250:     */  
/*  251:     */  public String getNamespaceURI() {
/*  252: 252 */    return getQName().getNamespaceURI();
/*  253:     */  }
/*  254:     */  
/*  255:     */  public String getQualifiedName() {
/*  256: 256 */    return getQName().getQualifiedName();
/*  257:     */  }
/*  258:     */  
/*  259:     */  public Object getData() {
/*  260: 260 */    return getText();
/*  261:     */  }
/*  262:     */  
/*  265:     */  public void setData(Object data) {}
/*  266:     */  
/*  268:     */  public Node node(int index)
/*  269:     */  {
/*  270: 270 */    if (index >= 0) {
/*  271: 271 */      List list = contentList();
/*  272:     */      
/*  273: 273 */      if (index >= list.size()) {
/*  274: 274 */        return null;
/*  275:     */      }
/*  276:     */      
/*  277: 277 */      Object node = list.get(index);
/*  278:     */      
/*  279: 279 */      if (node != null) {
/*  280: 280 */        if ((node instanceof Node)) {
/*  281: 281 */          return (Node)node;
/*  282:     */        }
/*  283: 283 */        return getDocumentFactory().createText(node.toString());
/*  284:     */      }
/*  285:     */    }
/*  286:     */    
/*  288: 288 */    return null;
/*  289:     */  }
/*  290:     */  
/*  291:     */  public int indexOf(Node node) {
/*  292: 292 */    return contentList().indexOf(node);
/*  293:     */  }
/*  294:     */  
/*  295:     */  public int nodeCount() {
/*  296: 296 */    return contentList().size();
/*  297:     */  }
/*  298:     */  
/*  299:     */  public Iterator nodeIterator() {
/*  300: 300 */    return contentList().iterator();
/*  301:     */  }
/*  302:     */  
/*  304:     */  public Element element(String name)
/*  305:     */  {
/*  306: 306 */    List list = contentList();
/*  307:     */    
/*  308: 308 */    int size = list.size();
/*  309:     */    
/*  310: 310 */    for (int i = 0; i < size; i++) {
/*  311: 311 */      Object object = list.get(i);
/*  312:     */      
/*  313: 313 */      if ((object instanceof Element)) {
/*  314: 314 */        Element element = (Element)object;
/*  315:     */        
/*  316: 316 */        if (name.equals(element.getName())) {
/*  317: 317 */          return element;
/*  318:     */        }
/*  319:     */      }
/*  320:     */    }
/*  321:     */    
/*  322: 322 */    return null;
/*  323:     */  }
/*  324:     */  
/*  325:     */  public Element element(QName qName) {
/*  326: 326 */    List list = contentList();
/*  327:     */    
/*  328: 328 */    int size = list.size();
/*  329:     */    
/*  330: 330 */    for (int i = 0; i < size; i++) {
/*  331: 331 */      Object object = list.get(i);
/*  332:     */      
/*  333: 333 */      if ((object instanceof Element)) {
/*  334: 334 */        Element element = (Element)object;
/*  335:     */        
/*  336: 336 */        if (qName.equals(element.getQName())) {
/*  337: 337 */          return element;
/*  338:     */        }
/*  339:     */      }
/*  340:     */    }
/*  341:     */    
/*  342: 342 */    return null;
/*  343:     */  }
/*  344:     */  
/*  345:     */  public Element element(String name, Namespace namespace) {
/*  346: 346 */    return element(getDocumentFactory().createQName(name, namespace));
/*  347:     */  }
/*  348:     */  
/*  349:     */  public List elements() {
/*  350: 350 */    List list = contentList();
/*  351:     */    
/*  352: 352 */    BackedList answer = createResultList();
/*  353:     */    
/*  354: 354 */    int size = list.size();
/*  355:     */    
/*  356: 356 */    for (int i = 0; i < size; i++) {
/*  357: 357 */      Object object = list.get(i);
/*  358:     */      
/*  359: 359 */      if ((object instanceof Element)) {
/*  360: 360 */        answer.addLocal(object);
/*  361:     */      }
/*  362:     */    }
/*  363:     */    
/*  364: 364 */    return answer;
/*  365:     */  }
/*  366:     */  
/*  367:     */  public List elements(String name) {
/*  368: 368 */    List list = contentList();
/*  369:     */    
/*  370: 370 */    BackedList answer = createResultList();
/*  371:     */    
/*  372: 372 */    int size = list.size();
/*  373:     */    
/*  374: 374 */    for (int i = 0; i < size; i++) {
/*  375: 375 */      Object object = list.get(i);
/*  376:     */      
/*  377: 377 */      if ((object instanceof Element)) {
/*  378: 378 */        Element element = (Element)object;
/*  379:     */        
/*  380: 380 */        if (name.equals(element.getName())) {
/*  381: 381 */          answer.addLocal(element);
/*  382:     */        }
/*  383:     */      }
/*  384:     */    }
/*  385:     */    
/*  386: 386 */    return answer;
/*  387:     */  }
/*  388:     */  
/*  389:     */  public List elements(QName qName) {
/*  390: 390 */    List list = contentList();
/*  391:     */    
/*  392: 392 */    BackedList answer = createResultList();
/*  393:     */    
/*  394: 394 */    int size = list.size();
/*  395:     */    
/*  396: 396 */    for (int i = 0; i < size; i++) {
/*  397: 397 */      Object object = list.get(i);
/*  398:     */      
/*  399: 399 */      if ((object instanceof Element)) {
/*  400: 400 */        Element element = (Element)object;
/*  401:     */        
/*  402: 402 */        if (qName.equals(element.getQName())) {
/*  403: 403 */          answer.addLocal(element);
/*  404:     */        }
/*  405:     */      }
/*  406:     */    }
/*  407:     */    
/*  408: 408 */    return answer;
/*  409:     */  }
/*  410:     */  
/*  411:     */  public List elements(String name, Namespace namespace) {
/*  412: 412 */    return elements(getDocumentFactory().createQName(name, namespace));
/*  413:     */  }
/*  414:     */  
/*  415:     */  public Iterator elementIterator() {
/*  416: 416 */    List list = elements();
/*  417:     */    
/*  418: 418 */    return list.iterator();
/*  419:     */  }
/*  420:     */  
/*  421:     */  public Iterator elementIterator(String name) {
/*  422: 422 */    List list = elements(name);
/*  423:     */    
/*  424: 424 */    return list.iterator();
/*  425:     */  }
/*  426:     */  
/*  427:     */  public Iterator elementIterator(QName qName) {
/*  428: 428 */    List list = elements(qName);
/*  429:     */    
/*  430: 430 */    return list.iterator();
/*  431:     */  }
/*  432:     */  
/*  433:     */  public Iterator elementIterator(String name, Namespace ns) {
/*  434: 434 */    return elementIterator(getDocumentFactory().createQName(name, ns));
/*  435:     */  }
/*  436:     */  
/*  438:     */  public List attributes()
/*  439:     */  {
/*  440: 440 */    return new ContentListFacade(this, attributeList());
/*  441:     */  }
/*  442:     */  
/*  443:     */  public Iterator attributeIterator() {
/*  444: 444 */    return attributeList().iterator();
/*  445:     */  }
/*  446:     */  
/*  447:     */  public Attribute attribute(int index) {
/*  448: 448 */    return (Attribute)attributeList().get(index);
/*  449:     */  }
/*  450:     */  
/*  451:     */  public int attributeCount() {
/*  452: 452 */    return attributeList().size();
/*  453:     */  }
/*  454:     */  
/*  455:     */  public Attribute attribute(String name) {
/*  456: 456 */    List list = attributeList();
/*  457:     */    
/*  458: 458 */    int size = list.size();
/*  459:     */    
/*  460: 460 */    for (int i = 0; i < size; i++) {
/*  461: 461 */      Attribute attribute = (Attribute)list.get(i);
/*  462:     */      
/*  463: 463 */      if (name.equals(attribute.getName())) {
/*  464: 464 */        return attribute;
/*  465:     */      }
/*  466:     */    }
/*  467:     */    
/*  468: 468 */    return null;
/*  469:     */  }
/*  470:     */  
/*  471:     */  public Attribute attribute(QName qName) {
/*  472: 472 */    List list = attributeList();
/*  473:     */    
/*  474: 474 */    int size = list.size();
/*  475:     */    
/*  476: 476 */    for (int i = 0; i < size; i++) {
/*  477: 477 */      Attribute attribute = (Attribute)list.get(i);
/*  478:     */      
/*  479: 479 */      if (qName.equals(attribute.getQName())) {
/*  480: 480 */        return attribute;
/*  481:     */      }
/*  482:     */    }
/*  483:     */    
/*  484: 484 */    return null;
/*  485:     */  }
/*  486:     */  
/*  487:     */  public Attribute attribute(String name, Namespace namespace) {
/*  488: 488 */    return attribute(getDocumentFactory().createQName(name, namespace));
/*  489:     */  }
/*  490:     */  
/*  503:     */  public void setAttributes(Attributes attributes, NamespaceStack namespaceStack, boolean noNamespaceAttributes)
/*  504:     */  {
/*  505: 505 */    int size = attributes.getLength();
/*  506:     */    
/*  507: 507 */    if (size > 0) {
/*  508: 508 */      DocumentFactory factory = getDocumentFactory();
/*  509:     */      
/*  510: 510 */      if (size == 1)
/*  511:     */      {
/*  512: 512 */        String name = attributes.getQName(0);
/*  513:     */        
/*  514: 514 */        if ((noNamespaceAttributes) || (!name.startsWith("xmlns"))) {
/*  515: 515 */          String attributeURI = attributes.getURI(0);
/*  516:     */          
/*  517: 517 */          String attributeLocalName = attributes.getLocalName(0);
/*  518:     */          
/*  519: 519 */          String attributeValue = attributes.getValue(0);
/*  520:     */          
/*  521: 521 */          QName attributeQName = namespaceStack.getAttributeQName(attributeURI, attributeLocalName, name);
/*  522:     */          
/*  524: 524 */          add(factory.createAttribute(this, attributeQName, attributeValue));
/*  525:     */        }
/*  526:     */      }
/*  527:     */      else {
/*  528: 528 */        List list = attributeList(size);
/*  529:     */        
/*  530: 530 */        list.clear();
/*  531:     */        
/*  532: 532 */        for (int i = 0; i < size; i++)
/*  533:     */        {
/*  535: 535 */          String attributeName = attributes.getQName(i);
/*  536:     */          
/*  537: 537 */          if ((noNamespaceAttributes) || (!attributeName.startsWith("xmlns")))
/*  538:     */          {
/*  539: 539 */            String attributeURI = attributes.getURI(i);
/*  540:     */            
/*  541: 541 */            String attributeLocalName = attributes.getLocalName(i);
/*  542:     */            
/*  543: 543 */            String attributeValue = attributes.getValue(i);
/*  544:     */            
/*  545: 545 */            QName attributeQName = namespaceStack.getAttributeQName(attributeURI, attributeLocalName, attributeName);
/*  546:     */            
/*  549: 549 */            Attribute attribute = factory.createAttribute(this, attributeQName, attributeValue);
/*  550:     */            
/*  552: 552 */            list.add(attribute);
/*  553:     */            
/*  554: 554 */            childAdded(attribute);
/*  555:     */          }
/*  556:     */        }
/*  557:     */      }
/*  558:     */    }
/*  559:     */  }
/*  560:     */  
/*  561:     */  public String attributeValue(String name) {
/*  562: 562 */    Attribute attrib = attribute(name);
/*  563:     */    
/*  564: 564 */    if (attrib == null) {
/*  565: 565 */      return null;
/*  566:     */    }
/*  567: 567 */    return attrib.getValue();
/*  568:     */  }
/*  569:     */  
/*  570:     */  public String attributeValue(QName qName)
/*  571:     */  {
/*  572: 572 */    Attribute attrib = attribute(qName);
/*  573:     */    
/*  574: 574 */    if (attrib == null) {
/*  575: 575 */      return null;
/*  576:     */    }
/*  577: 577 */    return attrib.getValue();
/*  578:     */  }
/*  579:     */  
/*  580:     */  public String attributeValue(String name, String defaultValue)
/*  581:     */  {
/*  582: 582 */    String answer = attributeValue(name);
/*  583:     */    
/*  584: 584 */    return answer != null ? answer : defaultValue;
/*  585:     */  }
/*  586:     */  
/*  587:     */  public String attributeValue(QName qName, String defaultValue) {
/*  588: 588 */    String answer = attributeValue(qName);
/*  589:     */    
/*  590: 590 */    return answer != null ? answer : defaultValue;
/*  591:     */  }
/*  592:     */  
/*  601:     */  /**
/*  602:     */   * @deprecated
/*  603:     */   */
/*  604:     */  public void setAttributeValue(String name, String value)
/*  605:     */  {
/*  606: 606 */    addAttribute(name, value);
/*  607:     */  }
/*  608:     */  
/*  617:     */  /**
/*  618:     */   * @deprecated
/*  619:     */   */
/*  620:     */  public void setAttributeValue(QName qName, String value)
/*  621:     */  {
/*  622: 622 */    addAttribute(qName, value);
/*  623:     */  }
/*  624:     */  
/*  625:     */  public void add(Attribute attribute) {
/*  626: 626 */    if (attribute.getParent() != null) {
/*  627: 627 */      String message = "The Attribute already has an existing parent \"" + attribute.getParent().getQualifiedName() + "\"";
/*  628:     */      
/*  630: 630 */      throw new IllegalAddException(this, attribute, message);
/*  631:     */    }
/*  632:     */    
/*  633: 633 */    if (attribute.getValue() == null)
/*  634:     */    {
/*  637: 637 */      Attribute oldAttribute = attribute(attribute.getQName());
/*  638:     */      
/*  639: 639 */      if (oldAttribute != null) {
/*  640: 640 */        remove(oldAttribute);
/*  641:     */      }
/*  642:     */    } else {
/*  643: 643 */      attributeList().add(attribute);
/*  644:     */      
/*  645: 645 */      childAdded(attribute);
/*  646:     */    }
/*  647:     */  }
/*  648:     */  
/*  649:     */  public boolean remove(Attribute attribute) {
/*  650: 650 */    List list = attributeList();
/*  651:     */    
/*  652: 652 */    boolean answer = list.remove(attribute);
/*  653:     */    
/*  654: 654 */    if (answer) {
/*  655: 655 */      childRemoved(attribute);
/*  656:     */    }
/*  657:     */    else {
/*  658: 658 */      Attribute copy = attribute(attribute.getQName());
/*  659:     */      
/*  660: 660 */      if (copy != null) {
/*  661: 661 */        list.remove(copy);
/*  662:     */        
/*  663: 663 */        answer = true;
/*  664:     */      }
/*  665:     */    }
/*  666:     */    
/*  667: 667 */    return answer;
/*  668:     */  }
/*  669:     */  
/*  671:     */  public List processingInstructions()
/*  672:     */  {
/*  673: 673 */    List list = contentList();
/*  674:     */    
/*  675: 675 */    BackedList answer = createResultList();
/*  676:     */    
/*  677: 677 */    int size = list.size();
/*  678:     */    
/*  679: 679 */    for (int i = 0; i < size; i++) {
/*  680: 680 */      Object object = list.get(i);
/*  681:     */      
/*  682: 682 */      if ((object instanceof ProcessingInstruction)) {
/*  683: 683 */        answer.addLocal(object);
/*  684:     */      }
/*  685:     */    }
/*  686:     */    
/*  687: 687 */    return answer;
/*  688:     */  }
/*  689:     */  
/*  690:     */  public List processingInstructions(String target) {
/*  691: 691 */    List list = contentList();
/*  692:     */    
/*  693: 693 */    BackedList answer = createResultList();
/*  694:     */    
/*  695: 695 */    int size = list.size();
/*  696:     */    
/*  697: 697 */    for (int i = 0; i < size; i++) {
/*  698: 698 */      Object object = list.get(i);
/*  699:     */      
/*  700: 700 */      if ((object instanceof ProcessingInstruction)) {
/*  701: 701 */        ProcessingInstruction pi = (ProcessingInstruction)object;
/*  702:     */        
/*  703: 703 */        if (target.equals(pi.getName())) {
/*  704: 704 */          answer.addLocal(pi);
/*  705:     */        }
/*  706:     */      }
/*  707:     */    }
/*  708:     */    
/*  709: 709 */    return answer;
/*  710:     */  }
/*  711:     */  
/*  712:     */  public ProcessingInstruction processingInstruction(String target) {
/*  713: 713 */    List list = contentList();
/*  714:     */    
/*  715: 715 */    int size = list.size();
/*  716:     */    
/*  717: 717 */    for (int i = 0; i < size; i++) {
/*  718: 718 */      Object object = list.get(i);
/*  719:     */      
/*  720: 720 */      if ((object instanceof ProcessingInstruction)) {
/*  721: 721 */        ProcessingInstruction pi = (ProcessingInstruction)object;
/*  722:     */        
/*  723: 723 */        if (target.equals(pi.getName())) {
/*  724: 724 */          return pi;
/*  725:     */        }
/*  726:     */      }
/*  727:     */    }
/*  728:     */    
/*  729: 729 */    return null;
/*  730:     */  }
/*  731:     */  
/*  732:     */  public boolean removeProcessingInstruction(String target) {
/*  733: 733 */    List list = contentList();
/*  734:     */    
/*  735: 735 */    for (Iterator iter = list.iterator(); iter.hasNext();) {
/*  736: 736 */      Object object = iter.next();
/*  737:     */      
/*  738: 738 */      if ((object instanceof ProcessingInstruction)) {
/*  739: 739 */        ProcessingInstruction pi = (ProcessingInstruction)object;
/*  740:     */        
/*  741: 741 */        if (target.equals(pi.getName())) {
/*  742: 742 */          iter.remove();
/*  743:     */          
/*  744: 744 */          return true;
/*  745:     */        }
/*  746:     */      }
/*  747:     */    }
/*  748:     */    
/*  749: 749 */    return false;
/*  750:     */  }
/*  751:     */  
/*  753:     */  public Node getXPathResult(int index)
/*  754:     */  {
/*  755: 755 */    Node answer = node(index);
/*  756:     */    
/*  757: 757 */    if ((answer != null) && (!answer.supportsParent())) {
/*  758: 758 */      return answer.asXPathResult(this);
/*  759:     */    }
/*  760:     */    
/*  761: 761 */    return answer;
/*  762:     */  }
/*  763:     */  
/*  764:     */  public Element addAttribute(String name, String value)
/*  765:     */  {
/*  766: 766 */    Attribute attribute = attribute(name);
/*  767:     */    
/*  768: 768 */    if (value != null) {
/*  769: 769 */      if (attribute == null) {
/*  770: 770 */        add(getDocumentFactory().createAttribute(this, name, value));
/*  771: 771 */      } else if (attribute.isReadOnly()) {
/*  772: 772 */        remove(attribute);
/*  773:     */        
/*  774: 774 */        add(getDocumentFactory().createAttribute(this, name, value));
/*  775:     */      } else {
/*  776: 776 */        attribute.setValue(value);
/*  777:     */      }
/*  778: 778 */    } else if (attribute != null) {
/*  779: 779 */      remove(attribute);
/*  780:     */    }
/*  781:     */    
/*  782: 782 */    return this;
/*  783:     */  }
/*  784:     */  
/*  785:     */  public Element addAttribute(QName qName, String value)
/*  786:     */  {
/*  787: 787 */    Attribute attribute = attribute(qName);
/*  788:     */    
/*  789: 789 */    if (value != null) {
/*  790: 790 */      if (attribute == null) {
/*  791: 791 */        add(getDocumentFactory().createAttribute(this, qName, value));
/*  792: 792 */      } else if (attribute.isReadOnly()) {
/*  793: 793 */        remove(attribute);
/*  794:     */        
/*  795: 795 */        add(getDocumentFactory().createAttribute(this, qName, value));
/*  796:     */      } else {
/*  797: 797 */        attribute.setValue(value);
/*  798:     */      }
/*  799: 799 */    } else if (attribute != null) {
/*  800: 800 */      remove(attribute);
/*  801:     */    }
/*  802:     */    
/*  803: 803 */    return this;
/*  804:     */  }
/*  805:     */  
/*  806:     */  public Element addCDATA(String cdata) {
/*  807: 807 */    CDATA node = getDocumentFactory().createCDATA(cdata);
/*  808:     */    
/*  809: 809 */    addNewNode(node);
/*  810:     */    
/*  811: 811 */    return this;
/*  812:     */  }
/*  813:     */  
/*  814:     */  public Element addComment(String comment) {
/*  815: 815 */    Comment node = getDocumentFactory().createComment(comment);
/*  816:     */    
/*  817: 817 */    addNewNode(node);
/*  818:     */    
/*  819: 819 */    return this;
/*  820:     */  }
/*  821:     */  
/*  822:     */  public Element addElement(String name) {
/*  823: 823 */    DocumentFactory factory = getDocumentFactory();
/*  824:     */    
/*  825: 825 */    int index = name.indexOf(":");
/*  826:     */    
/*  827: 827 */    String prefix = "";
/*  828:     */    
/*  829: 829 */    String localName = name;
/*  830:     */    
/*  831: 831 */    Namespace namespace = null;
/*  832:     */    
/*  833: 833 */    if (index > 0) {
/*  834: 834 */      prefix = name.substring(0, index);
/*  835:     */      
/*  836: 836 */      localName = name.substring(index + 1);
/*  837:     */      
/*  838: 838 */      namespace = getNamespaceForPrefix(prefix);
/*  839:     */      
/*  840: 840 */      if (namespace == null) {
/*  841: 841 */        throw new IllegalAddException("No such namespace prefix: " + prefix + " is in scope on: " + this + " so cannot add element: " + name);
/*  842:     */      }
/*  843:     */    }
/*  844:     */    else
/*  845:     */    {
/*  846: 846 */      namespace = getNamespaceForPrefix("");
/*  847:     */    }
/*  848:     */    
/*  849:     */    Element node;
/*  850:     */    Element node;
/*  851: 851 */    if (namespace != null) {
/*  852: 852 */      QName qname = factory.createQName(localName, namespace);
/*  853:     */      
/*  854: 854 */      node = factory.createElement(qname);
/*  855:     */    } else {
/*  856: 856 */      node = factory.createElement(name);
/*  857:     */    }
/*  858:     */    
/*  859: 859 */    addNewNode(node);
/*  860:     */    
/*  861: 861 */    return node;
/*  862:     */  }
/*  863:     */  
/*  864:     */  public Element addEntity(String name, String text) {
/*  865: 865 */    Entity node = getDocumentFactory().createEntity(name, text);
/*  866:     */    
/*  867: 867 */    addNewNode(node);
/*  868:     */    
/*  869: 869 */    return this;
/*  870:     */  }
/*  871:     */  
/*  872:     */  public Element addNamespace(String prefix, String uri) {
/*  873: 873 */    Namespace node = getDocumentFactory().createNamespace(prefix, uri);
/*  874:     */    
/*  875: 875 */    addNewNode(node);
/*  876:     */    
/*  877: 877 */    return this;
/*  878:     */  }
/*  879:     */  
/*  880:     */  public Element addProcessingInstruction(String target, String data) {
/*  881: 881 */    ProcessingInstruction node = getDocumentFactory().createProcessingInstruction(target, data);
/*  882:     */    
/*  884: 884 */    addNewNode(node);
/*  885:     */    
/*  886: 886 */    return this;
/*  887:     */  }
/*  888:     */  
/*  889:     */  public Element addProcessingInstruction(String target, Map data) {
/*  890: 890 */    ProcessingInstruction node = getDocumentFactory().createProcessingInstruction(target, data);
/*  891:     */    
/*  893: 893 */    addNewNode(node);
/*  894:     */    
/*  895: 895 */    return this;
/*  896:     */  }
/*  897:     */  
/*  898:     */  public Element addText(String text) {
/*  899: 899 */    Text node = getDocumentFactory().createText(text);
/*  900:     */    
/*  901: 901 */    addNewNode(node);
/*  902:     */    
/*  903: 903 */    return this;
/*  904:     */  }
/*  905:     */  
/*  906:     */  public void add(Node node)
/*  907:     */  {
/*  908: 908 */    switch (node.getNodeType()) {
/*  909:     */    case 1: 
/*  910: 910 */      add((Element)node);
/*  911:     */      
/*  912: 912 */      break;
/*  913:     */    
/*  914:     */    case 2: 
/*  915: 915 */      add((Attribute)node);
/*  916:     */      
/*  917: 917 */      break;
/*  918:     */    
/*  919:     */    case 3: 
/*  920: 920 */      add((Text)node);
/*  921:     */      
/*  922: 922 */      break;
/*  923:     */    
/*  924:     */    case 4: 
/*  925: 925 */      add((CDATA)node);
/*  926:     */      
/*  927: 927 */      break;
/*  928:     */    
/*  929:     */    case 5: 
/*  930: 930 */      add((Entity)node);
/*  931:     */      
/*  932: 932 */      break;
/*  933:     */    
/*  934:     */    case 7: 
/*  935: 935 */      add((ProcessingInstruction)node);
/*  936:     */      
/*  937: 937 */      break;
/*  938:     */    
/*  939:     */    case 8: 
/*  940: 940 */      add((Comment)node);
/*  941:     */      
/*  942: 942 */      break;
/*  943:     */    
/*  948:     */    case 13: 
/*  949: 949 */      add((Namespace)node);
/*  950:     */      
/*  951: 951 */      break;
/*  952:     */    case 6: case 9: case 10: 
/*  953:     */    case 11: case 12: default: 
/*  954: 954 */      invalidNodeTypeAddException(node);
/*  955:     */    }
/*  956:     */  }
/*  957:     */  
/*  958:     */  public boolean remove(Node node) {
/*  959: 959 */    switch (node.getNodeType()) {
/*  960:     */    case 1: 
/*  961: 961 */      return remove((Element)node);
/*  962:     */    
/*  963:     */    case 2: 
/*  964: 964 */      return remove((Attribute)node);
/*  965:     */    
/*  966:     */    case 3: 
/*  967: 967 */      return remove((Text)node);
/*  968:     */    
/*  969:     */    case 4: 
/*  970: 970 */      return remove((CDATA)node);
/*  971:     */    
/*  972:     */    case 5: 
/*  973: 973 */      return remove((Entity)node);
/*  974:     */    
/*  975:     */    case 7: 
/*  976: 976 */      return remove((ProcessingInstruction)node);
/*  977:     */    
/*  978:     */    case 8: 
/*  979: 979 */      return remove((Comment)node);
/*  980:     */    
/*  984:     */    case 13: 
/*  985: 985 */      return remove((Namespace)node);
/*  986:     */    }
/*  987:     */    
/*  988: 988 */    return false;
/*  989:     */  }
/*  990:     */  
/*  992:     */  public void add(CDATA cdata)
/*  993:     */  {
/*  994: 994 */    addNode(cdata);
/*  995:     */  }
/*  996:     */  
/*  997:     */  public void add(Comment comment) {
/*  998: 998 */    addNode(comment);
/*  999:     */  }
/* 1000:     */  
/* 1001:     */  public void add(Element element) {
/* 1002:1002 */    addNode(element);
/* 1003:     */  }
/* 1004:     */  
/* 1005:     */  public void add(Entity entity) {
/* 1006:1006 */    addNode(entity);
/* 1007:     */  }
/* 1008:     */  
/* 1009:     */  public void add(Namespace namespace) {
/* 1010:1010 */    addNode(namespace);
/* 1011:     */  }
/* 1012:     */  
/* 1013:     */  public void add(ProcessingInstruction pi) {
/* 1014:1014 */    addNode(pi);
/* 1015:     */  }
/* 1016:     */  
/* 1017:     */  public void add(Text text) {
/* 1018:1018 */    addNode(text);
/* 1019:     */  }
/* 1020:     */  
/* 1021:     */  public boolean remove(CDATA cdata) {
/* 1022:1022 */    return removeNode(cdata);
/* 1023:     */  }
/* 1024:     */  
/* 1025:     */  public boolean remove(Comment comment) {
/* 1026:1026 */    return removeNode(comment);
/* 1027:     */  }
/* 1028:     */  
/* 1029:     */  public boolean remove(Element element) {
/* 1030:1030 */    return removeNode(element);
/* 1031:     */  }
/* 1032:     */  
/* 1033:     */  public boolean remove(Entity entity) {
/* 1034:1034 */    return removeNode(entity);
/* 1035:     */  }
/* 1036:     */  
/* 1037:     */  public boolean remove(Namespace namespace) {
/* 1038:1038 */    return removeNode(namespace);
/* 1039:     */  }
/* 1040:     */  
/* 1041:     */  public boolean remove(ProcessingInstruction pi) {
/* 1042:1042 */    return removeNode(pi);
/* 1043:     */  }
/* 1044:     */  
/* 1045:     */  public boolean remove(Text text) {
/* 1046:1046 */    return removeNode(text);
/* 1047:     */  }
/* 1048:     */  
/* 1050:     */  public boolean hasMixedContent()
/* 1051:     */  {
/* 1052:1052 */    List content = contentList();
/* 1053:     */    
/* 1054:1054 */    if ((content == null) || (content.isEmpty()) || (content.size() < 2)) {
/* 1055:1055 */      return false;
/* 1056:     */    }
/* 1057:     */    
/* 1058:1058 */    Class prevClass = null;
/* 1059:     */    
/* 1060:1060 */    for (Iterator iter = content.iterator(); iter.hasNext();) {
/* 1061:1061 */      Object object = iter.next();
/* 1062:     */      
/* 1063:1063 */      Class newClass = object.getClass();
/* 1064:     */      
/* 1065:1065 */      if (newClass != prevClass) {
/* 1066:1066 */        if (prevClass != null) {
/* 1067:1067 */          return true;
/* 1068:     */        }
/* 1069:     */        
/* 1070:1070 */        prevClass = newClass;
/* 1071:     */      }
/* 1072:     */    }
/* 1073:     */    
/* 1074:1074 */    return false;
/* 1075:     */  }
/* 1076:     */  
/* 1077:     */  public boolean isTextOnly() {
/* 1078:1078 */    List content = contentList();
/* 1079:     */    
/* 1080:1080 */    if ((content == null) || (content.isEmpty())) {
/* 1081:1081 */      return true;
/* 1082:     */    }
/* 1083:     */    
/* 1084:1084 */    for (Iterator iter = content.iterator(); iter.hasNext();) {
/* 1085:1085 */      Object object = iter.next();
/* 1086:     */      
/* 1087:1087 */      if ((!(object instanceof CharacterData)) && (!(object instanceof String)))
/* 1088:     */      {
/* 1089:1089 */        return false;
/* 1090:     */      }
/* 1091:     */    }
/* 1092:     */    
/* 1093:1093 */    return true;
/* 1094:     */  }
/* 1095:     */  
/* 1096:     */  public void setText(String text)
/* 1097:     */  {
/* 1098:1098 */    List allContent = contentList();
/* 1099:     */    
/* 1100:1100 */    if (allContent != null) {
/* 1101:1101 */      Iterator it = allContent.iterator();
/* 1102:     */      
/* 1103:1103 */      while (it.hasNext()) {
/* 1104:1104 */        Node node = (Node)it.next();
/* 1105:     */        
/* 1106:1106 */        switch (node.getNodeType())
/* 1107:     */        {
/* 1109:     */        case 3: 
/* 1110:     */        case 4: 
/* 1111:     */        case 5: 
/* 1112:1112 */          it.remove();
/* 1113:     */        }
/* 1114:     */        
/* 1115:     */      }
/* 1116:     */    }
/* 1117:     */    
/* 1120:1120 */    addText(text);
/* 1121:     */  }
/* 1122:     */  
/* 1123:     */  public String getStringValue() {
/* 1124:1124 */    List list = contentList();
/* 1125:     */    
/* 1126:1126 */    int size = list.size();
/* 1127:     */    
/* 1128:1128 */    if (size > 0) {
/* 1129:1129 */      if (size == 1)
/* 1130:     */      {
/* 1131:1131 */        return getContentAsStringValue(list.get(0));
/* 1132:     */      }
/* 1133:1133 */      StringBuffer buffer = new StringBuffer();
/* 1134:     */      
/* 1135:1135 */      for (int i = 0; i < size; i++) {
/* 1136:1136 */        Object node = list.get(i);
/* 1137:     */        
/* 1138:1138 */        String string = getContentAsStringValue(node);
/* 1139:     */        
/* 1140:1140 */        if (string.length() > 0)
/* 1141:     */        {
/* 1147:1147 */          buffer.append(string);
/* 1148:     */        }
/* 1149:     */      }
/* 1150:     */      
/* 1151:1151 */      return buffer.toString();
/* 1152:     */    }
/* 1153:     */    
/* 1155:1155 */    return "";
/* 1156:     */  }
/* 1157:     */  
/* 1174:     */  public void normalize()
/* 1175:     */  {
/* 1176:1176 */    List content = contentList();
/* 1177:     */    
/* 1178:1178 */    Text previousText = null;
/* 1179:     */    
/* 1180:1180 */    int i = 0;
/* 1181:     */    
/* 1182:1182 */    while (i < content.size()) {
/* 1183:1183 */      Node node = (Node)content.get(i);
/* 1184:     */      
/* 1185:1185 */      if ((node instanceof Text)) {
/* 1186:1186 */        Text text = (Text)node;
/* 1187:     */        
/* 1188:1188 */        if (previousText != null) {
/* 1189:1189 */          previousText.appendText(text.getText());
/* 1190:     */          
/* 1191:1191 */          remove(text);
/* 1192:     */        } else {
/* 1193:1193 */          String value = text.getText();
/* 1194:     */          
/* 1197:1197 */          if ((value == null) || (value.length() <= 0)) {
/* 1198:1198 */            remove(text);
/* 1199:     */          } else {
/* 1200:1200 */            previousText = text;
/* 1201:     */            
/* 1202:1202 */            i++;
/* 1203:     */          }
/* 1204:     */        }
/* 1205:     */      } else {
/* 1206:1206 */        if ((node instanceof Element)) {
/* 1207:1207 */          Element element = (Element)node;
/* 1208:     */          
/* 1209:1209 */          element.normalize();
/* 1210:     */        }
/* 1211:     */        
/* 1212:1212 */        previousText = null;
/* 1213:     */        
/* 1214:1214 */        i++;
/* 1215:     */      }
/* 1216:     */    }
/* 1217:     */  }
/* 1218:     */  
/* 1219:     */  public String elementText(String name) {
/* 1220:1220 */    Element element = element(name);
/* 1221:     */    
/* 1222:1222 */    return element != null ? element.getText() : null;
/* 1223:     */  }
/* 1224:     */  
/* 1225:     */  public String elementText(QName qName) {
/* 1226:1226 */    Element element = element(qName);
/* 1227:     */    
/* 1228:1228 */    return element != null ? element.getText() : null;
/* 1229:     */  }
/* 1230:     */  
/* 1231:     */  public String elementTextTrim(String name) {
/* 1232:1232 */    Element element = element(name);
/* 1233:     */    
/* 1234:1234 */    return element != null ? element.getTextTrim() : null;
/* 1235:     */  }
/* 1236:     */  
/* 1237:     */  public String elementTextTrim(QName qName) {
/* 1238:1238 */    Element element = element(qName);
/* 1239:     */    
/* 1240:1240 */    return element != null ? element.getTextTrim() : null;
/* 1241:     */  }
/* 1242:     */  
/* 1244:     */  public void appendAttributes(Element element)
/* 1245:     */  {
/* 1246:1246 */    int i = 0; for (int size = element.attributeCount(); i < size; i++) {
/* 1247:1247 */      Attribute attribute = element.attribute(i);
/* 1248:     */      
/* 1249:1249 */      if (attribute.supportsParent()) {
/* 1250:1250 */        addAttribute(attribute.getQName(), attribute.getValue());
/* 1251:     */      } else {
/* 1252:1252 */        add(attribute);
/* 1253:     */      }
/* 1254:     */    }
/* 1255:     */  }
/* 1256:     */  
/* 1269:     */  public Element createCopy()
/* 1270:     */  {
/* 1271:1271 */    Element clone = createElement(getQName());
/* 1272:     */    
/* 1273:1273 */    clone.appendAttributes(this);
/* 1274:     */    
/* 1275:1275 */    clone.appendContent(this);
/* 1276:     */    
/* 1277:1277 */    return clone;
/* 1278:     */  }
/* 1279:     */  
/* 1280:     */  public Element createCopy(String name) {
/* 1281:1281 */    Element clone = createElement(name);
/* 1282:     */    
/* 1283:1283 */    clone.appendAttributes(this);
/* 1284:     */    
/* 1285:1285 */    clone.appendContent(this);
/* 1286:     */    
/* 1287:1287 */    return clone;
/* 1288:     */  }
/* 1289:     */  
/* 1290:     */  public Element createCopy(QName qName) {
/* 1291:1291 */    Element clone = createElement(qName);
/* 1292:     */    
/* 1293:1293 */    clone.appendAttributes(this);
/* 1294:     */    
/* 1295:1295 */    clone.appendContent(this);
/* 1296:     */    
/* 1297:1297 */    return clone;
/* 1298:     */  }
/* 1299:     */  
/* 1300:     */  public QName getQName(String qualifiedName) {
/* 1301:1301 */    String prefix = "";
/* 1302:     */    
/* 1303:1303 */    String localName = qualifiedName;
/* 1304:     */    
/* 1305:1305 */    int index = qualifiedName.indexOf(":");
/* 1306:     */    
/* 1307:1307 */    if (index > 0) {
/* 1308:1308 */      prefix = qualifiedName.substring(0, index);
/* 1309:     */      
/* 1310:1310 */      localName = qualifiedName.substring(index + 1);
/* 1311:     */    }
/* 1312:     */    
/* 1313:1313 */    Namespace namespace = getNamespaceForPrefix(prefix);
/* 1314:     */    
/* 1315:1315 */    if (namespace != null) {
/* 1316:1316 */      return getDocumentFactory().createQName(localName, namespace);
/* 1317:     */    }
/* 1318:1318 */    return getDocumentFactory().createQName(localName);
/* 1319:     */  }
/* 1320:     */  
/* 1321:     */  public Namespace getNamespaceForPrefix(String prefix)
/* 1322:     */  {
/* 1323:1323 */    if (prefix == null) {
/* 1324:1324 */      prefix = "";
/* 1325:     */    }
/* 1326:     */    
/* 1327:1327 */    if (prefix.equals(getNamespacePrefix()))
/* 1328:1328 */      return getNamespace();
/* 1329:1329 */    if (prefix.equals("xml")) {
/* 1330:1330 */      return Namespace.XML_NAMESPACE;
/* 1331:     */    }
/* 1332:1332 */    List list = contentList();
/* 1333:     */    
/* 1334:1334 */    int size = list.size();
/* 1335:     */    
/* 1336:1336 */    for (int i = 0; i < size; i++) {
/* 1337:1337 */      Object object = list.get(i);
/* 1338:     */      
/* 1339:1339 */      if ((object instanceof Namespace)) {
/* 1340:1340 */        Namespace namespace = (Namespace)object;
/* 1341:     */        
/* 1342:1342 */        if (prefix.equals(namespace.getPrefix())) {
/* 1343:1343 */          return namespace;
/* 1344:     */        }
/* 1345:     */      }
/* 1346:     */    }
/* 1347:     */    
/* 1349:1349 */    Element parent = getParent();
/* 1350:     */    
/* 1351:1351 */    if (parent != null) {
/* 1352:1352 */      Namespace answer = parent.getNamespaceForPrefix(prefix);
/* 1353:     */      
/* 1354:1354 */      if (answer != null) {
/* 1355:1355 */        return answer;
/* 1356:     */      }
/* 1357:     */    }
/* 1358:     */    
/* 1359:1359 */    if ((prefix == null) || (prefix.length() <= 0)) {
/* 1360:1360 */      return Namespace.NO_NAMESPACE;
/* 1361:     */    }
/* 1362:     */    
/* 1363:1363 */    return null;
/* 1364:     */  }
/* 1365:     */  
/* 1366:     */  public Namespace getNamespaceForURI(String uri) {
/* 1367:1367 */    if ((uri == null) || (uri.length() <= 0))
/* 1368:1368 */      return Namespace.NO_NAMESPACE;
/* 1369:1369 */    if (uri.equals(getNamespaceURI())) {
/* 1370:1370 */      return getNamespace();
/* 1371:     */    }
/* 1372:1372 */    List list = contentList();
/* 1373:     */    
/* 1374:1374 */    int size = list.size();
/* 1375:     */    
/* 1376:1376 */    for (int i = 0; i < size; i++) {
/* 1377:1377 */      Object object = list.get(i);
/* 1378:     */      
/* 1379:1379 */      if ((object instanceof Namespace)) {
/* 1380:1380 */        Namespace namespace = (Namespace)object;
/* 1381:     */        
/* 1382:1382 */        if (uri.equals(namespace.getURI())) {
/* 1383:1383 */          return namespace;
/* 1384:     */        }
/* 1385:     */      }
/* 1386:     */    }
/* 1387:     */    
/* 1388:1388 */    return null;
/* 1389:     */  }
/* 1390:     */  
/* 1391:     */  public List getNamespacesForURI(String uri)
/* 1392:     */  {
/* 1393:1393 */    BackedList answer = createResultList();
/* 1394:     */    
/* 1400:1400 */    List list = contentList();
/* 1401:     */    
/* 1402:1402 */    int size = list.size();
/* 1403:     */    
/* 1404:1404 */    for (int i = 0; i < size; i++) {
/* 1405:1405 */      Object object = list.get(i);
/* 1406:     */      
/* 1407:1407 */      if (((object instanceof Namespace)) && (((Namespace)object).getURI().equals(uri)))
/* 1408:     */      {
/* 1409:1409 */        answer.addLocal(object);
/* 1410:     */      }
/* 1411:     */    }
/* 1412:     */    
/* 1413:1413 */    return answer;
/* 1414:     */  }
/* 1415:     */  
/* 1416:     */  public List declaredNamespaces() {
/* 1417:1417 */    BackedList answer = createResultList();
/* 1418:     */    
/* 1425:1425 */    List list = contentList();
/* 1426:     */    
/* 1427:1427 */    int size = list.size();
/* 1428:     */    
/* 1429:1429 */    for (int i = 0; i < size; i++) {
/* 1430:1430 */      Object object = list.get(i);
/* 1431:     */      
/* 1432:1432 */      if ((object instanceof Namespace)) {
/* 1433:1433 */        answer.addLocal(object);
/* 1434:     */      }
/* 1435:     */    }
/* 1436:     */    
/* 1437:1437 */    return answer;
/* 1438:     */  }
/* 1439:     */  
/* 1440:     */  public List additionalNamespaces() {
/* 1441:1441 */    List list = contentList();
/* 1442:     */    
/* 1443:1443 */    int size = list.size();
/* 1444:     */    
/* 1445:1445 */    BackedList answer = createResultList();
/* 1446:     */    
/* 1447:1447 */    for (int i = 0; i < size; i++) {
/* 1448:1448 */      Object object = list.get(i);
/* 1449:     */      
/* 1450:1450 */      if ((object instanceof Namespace)) {
/* 1451:1451 */        Namespace namespace = (Namespace)object;
/* 1452:     */        
/* 1453:1453 */        if (!namespace.equals(getNamespace())) {
/* 1454:1454 */          answer.addLocal(namespace);
/* 1455:     */        }
/* 1456:     */      }
/* 1457:     */    }
/* 1458:     */    
/* 1459:1459 */    return answer;
/* 1460:     */  }
/* 1461:     */  
/* 1462:     */  public List additionalNamespaces(String defaultNamespaceURI) {
/* 1463:1463 */    List list = contentList();
/* 1464:     */    
/* 1465:1465 */    BackedList answer = createResultList();
/* 1466:     */    
/* 1467:1467 */    int size = list.size();
/* 1468:     */    
/* 1469:1469 */    for (int i = 0; i < size; i++) {
/* 1470:1470 */      Object object = list.get(i);
/* 1471:     */      
/* 1472:1472 */      if ((object instanceof Namespace)) {
/* 1473:1473 */        Namespace namespace = (Namespace)object;
/* 1474:     */        
/* 1475:1475 */        if (!defaultNamespaceURI.equals(namespace.getURI())) {
/* 1476:1476 */          answer.addLocal(namespace);
/* 1477:     */        }
/* 1478:     */      }
/* 1479:     */    }
/* 1480:     */    
/* 1481:1481 */    return answer;
/* 1482:     */  }
/* 1483:     */  
/* 1492:     */  public void ensureAttributesCapacity(int minCapacity)
/* 1493:     */  {
/* 1494:1494 */    if (minCapacity > 1) {
/* 1495:1495 */      List list = attributeList();
/* 1496:     */      
/* 1497:1497 */      if ((list instanceof ArrayList)) {
/* 1498:1498 */        ArrayList arrayList = (ArrayList)list;
/* 1499:     */        
/* 1500:1500 */        arrayList.ensureCapacity(minCapacity);
/* 1501:     */      }
/* 1502:     */    }
/* 1503:     */  }
/* 1504:     */  
/* 1506:     */  protected Element createElement(String name)
/* 1507:     */  {
/* 1508:1508 */    return getDocumentFactory().createElement(name);
/* 1509:     */  }
/* 1510:     */  
/* 1511:     */  protected Element createElement(QName qName) {
/* 1512:1512 */    return getDocumentFactory().createElement(qName);
/* 1513:     */  }
/* 1514:     */  
/* 1515:     */  protected void addNode(Node node) {
/* 1516:1516 */    if (node.getParent() != null)
/* 1517:     */    {
/* 1518:1518 */      String message = "The Node already has an existing parent of \"" + node.getParent().getQualifiedName() + "\"";
/* 1519:     */      
/* 1521:1521 */      throw new IllegalAddException(this, node, message);
/* 1522:     */    }
/* 1523:     */    
/* 1524:1524 */    addNewNode(node);
/* 1525:     */  }
/* 1526:     */  
/* 1527:     */  protected void addNode(int index, Node node) {
/* 1528:1528 */    if (node.getParent() != null)
/* 1529:     */    {
/* 1530:1530 */      String message = "The Node already has an existing parent of \"" + node.getParent().getQualifiedName() + "\"";
/* 1531:     */      
/* 1533:1533 */      throw new IllegalAddException(this, node, message);
/* 1534:     */    }
/* 1535:     */    
/* 1536:1536 */    addNewNode(index, node);
/* 1537:     */  }
/* 1538:     */  
/* 1544:     */  protected void addNewNode(Node node)
/* 1545:     */  {
/* 1546:1546 */    contentList().add(node);
/* 1547:     */    
/* 1548:1548 */    childAdded(node);
/* 1549:     */  }
/* 1550:     */  
/* 1551:     */  protected void addNewNode(int index, Node node) {
/* 1552:1552 */    contentList().add(index, node);
/* 1553:     */    
/* 1554:1554 */    childAdded(node);
/* 1555:     */  }
/* 1556:     */  
/* 1557:     */  protected boolean removeNode(Node node) {
/* 1558:1558 */    boolean answer = contentList().remove(node);
/* 1559:     */    
/* 1560:1560 */    if (answer) {
/* 1561:1561 */      childRemoved(node);
/* 1562:     */    }
/* 1563:     */    
/* 1564:1564 */    return answer;
/* 1565:     */  }
/* 1566:     */  
/* 1572:     */  protected void childAdded(Node node)
/* 1573:     */  {
/* 1574:1574 */    if (node != null) {
/* 1575:1575 */      node.setParent(this);
/* 1576:     */    }
/* 1577:     */  }
/* 1578:     */  
/* 1579:     */  protected void childRemoved(Node node) {
/* 1580:1580 */    if (node != null) {
/* 1581:1581 */      node.setParent(null);
/* 1582:     */      
/* 1583:1583 */      node.setDocument(null);
/* 1584:     */    }
/* 1585:     */  }
/* 1586:     */  
/* 1592:     */  protected abstract List attributeList();
/* 1593:     */  
/* 1599:     */  protected abstract List attributeList(int paramInt);
/* 1600:     */  
/* 1605:     */  protected DocumentFactory getDocumentFactory()
/* 1606:     */  {
/* 1607:1607 */    QName qName = getQName();
/* 1608:     */    
/* 1610:1610 */    if (qName != null) {
/* 1611:1611 */      DocumentFactory factory = qName.getDocumentFactory();
/* 1612:     */      
/* 1613:1613 */      if (factory != null) {
/* 1614:1614 */        return factory;
/* 1615:     */      }
/* 1616:     */    }
/* 1617:     */    
/* 1618:1618 */    return DOCUMENT_FACTORY;
/* 1619:     */  }
/* 1620:     */  
/* 1626:     */  protected List createAttributeList()
/* 1627:     */  {
/* 1628:1628 */    return createAttributeList(5);
/* 1629:     */  }
/* 1630:     */  
/* 1639:     */  protected List createAttributeList(int size)
/* 1640:     */  {
/* 1641:1641 */    return new ArrayList(size);
/* 1642:     */  }
/* 1643:     */  
/* 1644:     */  protected Iterator createSingleIterator(Object result) {
/* 1645:1645 */    return new SingleIterator(result);
/* 1646:     */  }
/* 1647:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.AbstractElement
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */