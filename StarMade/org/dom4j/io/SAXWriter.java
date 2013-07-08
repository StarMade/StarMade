/*   1:    */package org.dom4j.io;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */import java.util.HashMap;
/*   5:    */import java.util.Iterator;
/*   6:    */import java.util.List;
/*   7:    */import java.util.Map;
/*   8:    */import org.dom4j.Attribute;
/*   9:    */import org.dom4j.Branch;
/*  10:    */import org.dom4j.CDATA;
/*  11:    */import org.dom4j.CharacterData;
/*  12:    */import org.dom4j.Comment;
/*  13:    */import org.dom4j.Document;
/*  14:    */import org.dom4j.DocumentType;
/*  15:    */import org.dom4j.Element;
/*  16:    */import org.dom4j.Entity;
/*  17:    */import org.dom4j.Namespace;
/*  18:    */import org.dom4j.Node;
/*  19:    */import org.dom4j.ProcessingInstruction;
/*  20:    */import org.dom4j.Text;
/*  21:    */import org.dom4j.tree.NamespaceStack;
/*  22:    */import org.xml.sax.Attributes;
/*  23:    */import org.xml.sax.ContentHandler;
/*  24:    */import org.xml.sax.DTDHandler;
/*  25:    */import org.xml.sax.EntityResolver;
/*  26:    */import org.xml.sax.ErrorHandler;
/*  27:    */import org.xml.sax.InputSource;
/*  28:    */import org.xml.sax.SAXException;
/*  29:    */import org.xml.sax.SAXNotRecognizedException;
/*  30:    */import org.xml.sax.SAXNotSupportedException;
/*  31:    */import org.xml.sax.XMLReader;
/*  32:    */import org.xml.sax.ext.LexicalHandler;
/*  33:    */import org.xml.sax.helpers.AttributesImpl;
/*  34:    */import org.xml.sax.helpers.LocatorImpl;
/*  35:    */
/*  51:    */public class SAXWriter
/*  52:    */  implements XMLReader
/*  53:    */{
/*  54: 54 */  protected static final String[] LEXICAL_HANDLER_NAMES = { "http://xml.org/sax/properties/lexical-handler", "http://xml.org/sax/handlers/LexicalHandler" };
/*  55:    */  
/*  58:    */  protected static final String FEATURE_NAMESPACE_PREFIXES = "http://xml.org/sax/features/namespace-prefixes";
/*  59:    */  
/*  61:    */  protected static final String FEATURE_NAMESPACES = "http://xml.org/sax/features/namespaces";
/*  62:    */  
/*  64:    */  private ContentHandler contentHandler;
/*  65:    */  
/*  67:    */  private DTDHandler dtdHandler;
/*  68:    */  
/*  70:    */  private EntityResolver entityResolver;
/*  71:    */  
/*  73:    */  private ErrorHandler errorHandler;
/*  74:    */  
/*  76:    */  private LexicalHandler lexicalHandler;
/*  77:    */  
/*  79: 79 */  private AttributesImpl attributes = new AttributesImpl();
/*  80:    */  
/*  82: 82 */  private Map features = new HashMap();
/*  83:    */  
/*  85: 85 */  private Map properties = new HashMap();
/*  86:    */  
/*  87:    */  private boolean declareNamespaceAttributes;
/*  88:    */  
/*  89:    */  public SAXWriter()
/*  90:    */  {
/*  91: 91 */    this.properties.put("http://xml.org/sax/features/namespace-prefixes", Boolean.FALSE);
/*  92: 92 */    this.properties.put("http://xml.org/sax/features/namespace-prefixes", Boolean.TRUE);
/*  93:    */  }
/*  94:    */  
/*  95:    */  public SAXWriter(ContentHandler contentHandler) {
/*  96: 96 */    this();
/*  97: 97 */    this.contentHandler = contentHandler;
/*  98:    */  }
/*  99:    */  
/* 100:    */  public SAXWriter(ContentHandler contentHandler, LexicalHandler lexicalHandler)
/* 101:    */  {
/* 102:102 */    this();
/* 103:103 */    this.contentHandler = contentHandler;
/* 104:104 */    this.lexicalHandler = lexicalHandler;
/* 105:    */  }
/* 106:    */  
/* 107:    */  public SAXWriter(ContentHandler contentHandler, LexicalHandler lexicalHandler, EntityResolver entityResolver)
/* 108:    */  {
/* 109:109 */    this();
/* 110:110 */    this.contentHandler = contentHandler;
/* 111:111 */    this.lexicalHandler = lexicalHandler;
/* 112:112 */    this.entityResolver = entityResolver;
/* 113:    */  }
/* 114:    */  
/* 122:    */  public void write(Node node)
/* 123:    */    throws SAXException
/* 124:    */  {
/* 125:125 */    int nodeType = node.getNodeType();
/* 126:    */    
/* 127:127 */    switch (nodeType) {
/* 128:    */    case 1: 
/* 129:129 */      write((Element)node);
/* 130:    */      
/* 131:131 */      break;
/* 132:    */    
/* 133:    */    case 2: 
/* 134:134 */      write((Attribute)node);
/* 135:    */      
/* 136:136 */      break;
/* 137:    */    
/* 138:    */    case 3: 
/* 139:139 */      write(node.getText());
/* 140:    */      
/* 141:141 */      break;
/* 142:    */    
/* 143:    */    case 4: 
/* 144:144 */      write((CDATA)node);
/* 145:    */      
/* 146:146 */      break;
/* 147:    */    
/* 148:    */    case 5: 
/* 149:149 */      write((Entity)node);
/* 150:    */      
/* 151:151 */      break;
/* 152:    */    
/* 153:    */    case 7: 
/* 154:154 */      write((ProcessingInstruction)node);
/* 155:    */      
/* 156:156 */      break;
/* 157:    */    
/* 158:    */    case 8: 
/* 159:159 */      write((Comment)node);
/* 160:    */      
/* 161:161 */      break;
/* 162:    */    
/* 163:    */    case 9: 
/* 164:164 */      write((Document)node);
/* 165:    */      
/* 166:166 */      break;
/* 167:    */    
/* 168:    */    case 10: 
/* 169:169 */      write((DocumentType)node);
/* 170:    */      
/* 171:171 */      break;
/* 172:    */    
/* 176:    */    case 13: 
/* 177:177 */      break;
/* 178:    */    case 6: case 11: 
/* 179:    */    case 12: default: 
/* 180:180 */      throw new SAXException("Invalid node type: " + node);
/* 181:    */    }
/* 182:    */    
/* 183:    */  }
/* 184:    */  
/* 191:    */  public void write(Document document)
/* 192:    */    throws SAXException
/* 193:    */  {
/* 194:194 */    if (document != null) {
/* 195:195 */      checkForNullHandlers();
/* 196:    */      
/* 197:197 */      documentLocator(document);
/* 198:198 */      startDocument();
/* 199:199 */      entityResolver(document);
/* 200:200 */      dtdHandler(document);
/* 201:    */      
/* 202:202 */      writeContent(document, new NamespaceStack());
/* 203:203 */      endDocument();
/* 204:    */    }
/* 205:    */  }
/* 206:    */  
/* 214:    */  public void write(Element element)
/* 215:    */    throws SAXException
/* 216:    */  {
/* 217:217 */    write(element, new NamespaceStack());
/* 218:    */  }
/* 219:    */  
/* 230:    */  public void writeOpen(Element element)
/* 231:    */    throws SAXException
/* 232:    */  {
/* 233:233 */    startElement(element, null);
/* 234:    */  }
/* 235:    */  
/* 245:    */  public void writeClose(Element element)
/* 246:    */    throws SAXException
/* 247:    */  {
/* 248:248 */    endElement(element);
/* 249:    */  }
/* 250:    */  
/* 258:    */  public void write(String text)
/* 259:    */    throws SAXException
/* 260:    */  {
/* 261:261 */    if (text != null) {
/* 262:262 */      char[] chars = text.toCharArray();
/* 263:263 */      this.contentHandler.characters(chars, 0, chars.length);
/* 264:    */    }
/* 265:    */  }
/* 266:    */  
/* 274:    */  public void write(CDATA cdata)
/* 275:    */    throws SAXException
/* 276:    */  {
/* 277:277 */    String text = cdata.getText();
/* 278:    */    
/* 279:279 */    if (this.lexicalHandler != null) {
/* 280:280 */      this.lexicalHandler.startCDATA();
/* 281:281 */      write(text);
/* 282:282 */      this.lexicalHandler.endCDATA();
/* 283:    */    } else {
/* 284:284 */      write(text);
/* 285:    */    }
/* 286:    */  }
/* 287:    */  
/* 295:    */  public void write(Comment comment)
/* 296:    */    throws SAXException
/* 297:    */  {
/* 298:298 */    if (this.lexicalHandler != null) {
/* 299:299 */      String text = comment.getText();
/* 300:300 */      char[] chars = text.toCharArray();
/* 301:301 */      this.lexicalHandler.comment(chars, 0, chars.length);
/* 302:    */    }
/* 303:    */  }
/* 304:    */  
/* 312:    */  public void write(Entity entity)
/* 313:    */    throws SAXException
/* 314:    */  {
/* 315:315 */    String text = entity.getText();
/* 316:    */    
/* 317:317 */    if (this.lexicalHandler != null) {
/* 318:318 */      String name = entity.getName();
/* 319:319 */      this.lexicalHandler.startEntity(name);
/* 320:320 */      write(text);
/* 321:321 */      this.lexicalHandler.endEntity(name);
/* 322:    */    } else {
/* 323:323 */      write(text);
/* 324:    */    }
/* 325:    */  }
/* 326:    */  
/* 334:    */  public void write(ProcessingInstruction pi)
/* 335:    */    throws SAXException
/* 336:    */  {
/* 337:337 */    String target = pi.getTarget();
/* 338:338 */    String text = pi.getText();
/* 339:339 */    this.contentHandler.processingInstruction(target, text);
/* 340:    */  }
/* 341:    */  
/* 349:    */  public boolean isDeclareNamespaceAttributes()
/* 350:    */  {
/* 351:351 */    return this.declareNamespaceAttributes;
/* 352:    */  }
/* 353:    */  
/* 361:    */  public void setDeclareNamespaceAttributes(boolean declareNamespaceAttrs)
/* 362:    */  {
/* 363:363 */    this.declareNamespaceAttributes = declareNamespaceAttrs;
/* 364:    */  }
/* 365:    */  
/* 374:    */  public ContentHandler getContentHandler()
/* 375:    */  {
/* 376:376 */    return this.contentHandler;
/* 377:    */  }
/* 378:    */  
/* 385:    */  public void setContentHandler(ContentHandler contentHandler)
/* 386:    */  {
/* 387:387 */    this.contentHandler = contentHandler;
/* 388:    */  }
/* 389:    */  
/* 394:    */  public DTDHandler getDTDHandler()
/* 395:    */  {
/* 396:396 */    return this.dtdHandler;
/* 397:    */  }
/* 398:    */  
/* 404:    */  public void setDTDHandler(DTDHandler handler)
/* 405:    */  {
/* 406:406 */    this.dtdHandler = handler;
/* 407:    */  }
/* 408:    */  
/* 413:    */  public ErrorHandler getErrorHandler()
/* 414:    */  {
/* 415:415 */    return this.errorHandler;
/* 416:    */  }
/* 417:    */  
/* 423:    */  public void setErrorHandler(ErrorHandler errorHandler)
/* 424:    */  {
/* 425:425 */    this.errorHandler = errorHandler;
/* 426:    */  }
/* 427:    */  
/* 433:    */  public EntityResolver getEntityResolver()
/* 434:    */  {
/* 435:435 */    return this.entityResolver;
/* 436:    */  }
/* 437:    */  
/* 443:    */  public void setEntityResolver(EntityResolver entityResolver)
/* 444:    */  {
/* 445:445 */    this.entityResolver = entityResolver;
/* 446:    */  }
/* 447:    */  
/* 453:    */  public LexicalHandler getLexicalHandler()
/* 454:    */  {
/* 455:455 */    return this.lexicalHandler;
/* 456:    */  }
/* 457:    */  
/* 463:    */  public void setLexicalHandler(LexicalHandler lexicalHandler)
/* 464:    */  {
/* 465:465 */    this.lexicalHandler = lexicalHandler;
/* 466:    */  }
/* 467:    */  
/* 473:    */  public void setXMLReader(XMLReader xmlReader)
/* 474:    */  {
/* 475:475 */    setContentHandler(xmlReader.getContentHandler());
/* 476:476 */    setDTDHandler(xmlReader.getDTDHandler());
/* 477:477 */    setEntityResolver(xmlReader.getEntityResolver());
/* 478:478 */    setErrorHandler(xmlReader.getErrorHandler());
/* 479:    */  }
/* 480:    */  
/* 493:    */  public boolean getFeature(String name)
/* 494:    */    throws SAXNotRecognizedException, SAXNotSupportedException
/* 495:    */  {
/* 496:496 */    Boolean answer = (Boolean)this.features.get(name);
/* 497:    */    
/* 498:498 */    return (answer != null) && (answer.booleanValue());
/* 499:    */  }
/* 500:    */  
/* 514:    */  public void setFeature(String name, boolean value)
/* 515:    */    throws SAXNotRecognizedException, SAXNotSupportedException
/* 516:    */  {
/* 517:517 */    if ("http://xml.org/sax/features/namespace-prefixes".equals(name)) {
/* 518:518 */      setDeclareNamespaceAttributes(value);
/* 519:519 */    } else if (("http://xml.org/sax/features/namespace-prefixes".equals(name)) && 
/* 520:520 */      (!value)) {
/* 521:521 */      String msg = "Namespace feature is always supported in dom4j";
/* 522:522 */      throw new SAXNotSupportedException(msg);
/* 523:    */    }
/* 524:    */    
/* 526:526 */    this.features.put(name, value ? Boolean.TRUE : Boolean.FALSE);
/* 527:    */  }
/* 528:    */  
/* 536:    */  public void setProperty(String name, Object value)
/* 537:    */  {
/* 538:538 */    for (int i = 0; i < LEXICAL_HANDLER_NAMES.length; i++) {
/* 539:539 */      if (LEXICAL_HANDLER_NAMES[i].equals(name)) {
/* 540:540 */        setLexicalHandler((LexicalHandler)value);
/* 541:    */        
/* 542:542 */        return;
/* 543:    */      }
/* 544:    */    }
/* 545:    */    
/* 546:546 */    this.properties.put(name, value);
/* 547:    */  }
/* 548:    */  
/* 561:    */  public Object getProperty(String name)
/* 562:    */    throws SAXNotRecognizedException, SAXNotSupportedException
/* 563:    */  {
/* 564:564 */    for (int i = 0; i < LEXICAL_HANDLER_NAMES.length; i++) {
/* 565:565 */      if (LEXICAL_HANDLER_NAMES[i].equals(name)) {
/* 566:566 */        return getLexicalHandler();
/* 567:    */      }
/* 568:    */    }
/* 569:    */    
/* 570:570 */    return this.properties.get(name);
/* 571:    */  }
/* 572:    */  
/* 580:    */  public void parse(String systemId)
/* 581:    */    throws SAXNotSupportedException
/* 582:    */  {
/* 583:583 */    throw new SAXNotSupportedException("This XMLReader can only accept <dom4j> InputSource objects");
/* 584:    */  }
/* 585:    */  
/* 597:    */  public void parse(InputSource input)
/* 598:    */    throws SAXException
/* 599:    */  {
/* 600:600 */    if ((input instanceof DocumentInputSource)) {
/* 601:601 */      DocumentInputSource documentInput = (DocumentInputSource)input;
/* 602:602 */      Document document = documentInput.getDocument();
/* 603:603 */      write(document);
/* 604:    */    } else {
/* 605:605 */      throw new SAXNotSupportedException("This XMLReader can only accept <dom4j> InputSource objects");
/* 606:    */    }
/* 607:    */  }
/* 608:    */  
/* 612:    */  protected void writeContent(Branch branch, NamespaceStack namespaceStack)
/* 613:    */    throws SAXException
/* 614:    */  {
/* 615:615 */    for (Iterator iter = branch.nodeIterator(); iter.hasNext();) {
/* 616:616 */      Object object = iter.next();
/* 617:    */      
/* 618:618 */      if ((object instanceof Element)) {
/* 619:619 */        write((Element)object, namespaceStack);
/* 620:620 */      } else if ((object instanceof CharacterData)) {
/* 621:621 */        if ((object instanceof Text)) {
/* 622:622 */          Text text = (Text)object;
/* 623:623 */          write(text.getText());
/* 624:624 */        } else if ((object instanceof CDATA)) {
/* 625:625 */          write((CDATA)object);
/* 626:626 */        } else if ((object instanceof Comment)) {
/* 627:627 */          write((Comment)object);
/* 628:    */        } else {
/* 629:629 */          throw new SAXException("Invalid Node in DOM4J content: " + object + " of type: " + object.getClass());
/* 630:    */        }
/* 631:    */      }
/* 632:632 */      else if ((object instanceof String)) {
/* 633:633 */        write((String)object);
/* 634:634 */      } else if ((object instanceof Entity)) {
/* 635:635 */        write((Entity)object);
/* 636:636 */      } else if ((object instanceof ProcessingInstruction)) {
/* 637:637 */        write((ProcessingInstruction)object);
/* 638:638 */      } else if ((object instanceof Namespace)) {
/* 639:639 */        write((Namespace)object);
/* 640:    */      } else {
/* 641:641 */        throw new SAXException("Invalid Node in DOM4J content: " + object);
/* 642:    */      }
/* 643:    */    }
/* 644:    */  }
/* 645:    */  
/* 658:    */  protected void documentLocator(Document document)
/* 659:    */    throws SAXException
/* 660:    */  {
/* 661:661 */    LocatorImpl locator = new LocatorImpl();
/* 662:    */    
/* 663:663 */    String publicID = null;
/* 664:664 */    String systemID = null;
/* 665:665 */    DocumentType docType = document.getDocType();
/* 666:    */    
/* 667:667 */    if (docType != null) {
/* 668:668 */      publicID = docType.getPublicID();
/* 669:669 */      systemID = docType.getSystemID();
/* 670:    */    }
/* 671:    */    
/* 672:672 */    if (publicID != null) {
/* 673:673 */      locator.setPublicId(publicID);
/* 674:    */    }
/* 675:    */    
/* 676:676 */    if (systemID != null) {
/* 677:677 */      locator.setSystemId(systemID);
/* 678:    */    }
/* 679:    */    
/* 680:680 */    locator.setLineNumber(-1);
/* 681:681 */    locator.setColumnNumber(-1);
/* 682:    */    
/* 683:683 */    this.contentHandler.setDocumentLocator(locator);
/* 684:    */  }
/* 685:    */  
/* 686:    */  protected void entityResolver(Document document) throws SAXException {
/* 687:687 */    if (this.entityResolver != null) {
/* 688:688 */      DocumentType docType = document.getDocType();
/* 689:    */      
/* 690:690 */      if (docType != null) {
/* 691:691 */        String publicID = docType.getPublicID();
/* 692:692 */        String systemID = docType.getSystemID();
/* 693:    */        
/* 694:694 */        if ((publicID != null) || (systemID != null)) {
/* 695:    */          try {
/* 696:696 */            this.entityResolver.resolveEntity(publicID, systemID);
/* 697:    */          } catch (IOException e) {
/* 698:698 */            throw new SAXException("Could not resolve publicID: " + publicID + " systemID: " + systemID, e);
/* 699:    */          }
/* 700:    */        }
/* 701:    */      }
/* 702:    */    }
/* 703:    */  }
/* 704:    */  
/* 709:    */  protected void dtdHandler(Document document)
/* 710:    */    throws SAXException
/* 711:    */  {}
/* 712:    */  
/* 717:    */  protected void startDocument()
/* 718:    */    throws SAXException
/* 719:    */  {
/* 720:720 */    this.contentHandler.startDocument();
/* 721:    */  }
/* 722:    */  
/* 723:    */  protected void endDocument() throws SAXException {
/* 724:724 */    this.contentHandler.endDocument();
/* 725:    */  }
/* 726:    */  
/* 727:    */  protected void write(Element element, NamespaceStack namespaceStack) throws SAXException
/* 728:    */  {
/* 729:729 */    int stackSize = namespaceStack.size();
/* 730:730 */    AttributesImpl namespaceAttributes = startPrefixMapping(element, namespaceStack);
/* 731:    */    
/* 732:732 */    startElement(element, namespaceAttributes);
/* 733:733 */    writeContent(element, namespaceStack);
/* 734:734 */    endElement(element);
/* 735:735 */    endPrefixMapping(namespaceStack, stackSize);
/* 736:    */  }
/* 737:    */  
/* 751:    */  protected AttributesImpl startPrefixMapping(Element element, NamespaceStack namespaceStack)
/* 752:    */    throws SAXException
/* 753:    */  {
/* 754:754 */    AttributesImpl namespaceAttributes = null;
/* 755:    */    
/* 757:757 */    Namespace elementNamespace = element.getNamespace();
/* 758:    */    
/* 759:759 */    if ((elementNamespace != null) && (!isIgnoreableNamespace(elementNamespace, namespaceStack)))
/* 760:    */    {
/* 761:761 */      namespaceStack.push(elementNamespace);
/* 762:762 */      this.contentHandler.startPrefixMapping(elementNamespace.getPrefix(), elementNamespace.getURI());
/* 763:    */      
/* 764:764 */      namespaceAttributes = addNamespaceAttribute(namespaceAttributes, elementNamespace);
/* 765:    */    }
/* 766:    */    
/* 768:768 */    List declaredNamespaces = element.declaredNamespaces();
/* 769:    */    
/* 770:770 */    int i = 0; for (int size = declaredNamespaces.size(); i < size; i++) {
/* 771:771 */      Namespace namespace = (Namespace)declaredNamespaces.get(i);
/* 772:    */      
/* 773:773 */      if (!isIgnoreableNamespace(namespace, namespaceStack)) {
/* 774:774 */        namespaceStack.push(namespace);
/* 775:775 */        this.contentHandler.startPrefixMapping(namespace.getPrefix(), namespace.getURI());
/* 776:    */        
/* 777:777 */        namespaceAttributes = addNamespaceAttribute(namespaceAttributes, namespace);
/* 778:    */      }
/* 779:    */    }
/* 780:    */    
/* 782:782 */    return namespaceAttributes;
/* 783:    */  }
/* 784:    */  
/* 796:    */  protected void endPrefixMapping(NamespaceStack stack, int stackSize)
/* 797:    */    throws SAXException
/* 798:    */  {
/* 799:799 */    while (stack.size() > stackSize) {
/* 800:800 */      Namespace namespace = stack.pop();
/* 801:    */      
/* 802:802 */      if (namespace != null) {
/* 803:803 */        this.contentHandler.endPrefixMapping(namespace.getPrefix());
/* 804:    */      }
/* 805:    */    }
/* 806:    */  }
/* 807:    */  
/* 808:    */  protected void startElement(Element element, AttributesImpl namespaceAttributes) throws SAXException
/* 809:    */  {
/* 810:810 */    this.contentHandler.startElement(element.getNamespaceURI(), element.getName(), element.getQualifiedName(), createAttributes(element, namespaceAttributes));
/* 811:    */  }
/* 812:    */  
/* 813:    */  protected void endElement(Element element)
/* 814:    */    throws SAXException
/* 815:    */  {
/* 816:816 */    this.contentHandler.endElement(element.getNamespaceURI(), element.getName(), element.getQualifiedName());
/* 817:    */  }
/* 818:    */  
/* 819:    */  protected Attributes createAttributes(Element element, Attributes namespaceAttributes)
/* 820:    */    throws SAXException
/* 821:    */  {
/* 822:822 */    this.attributes.clear();
/* 823:    */    
/* 824:824 */    if (namespaceAttributes != null) {
/* 825:825 */      this.attributes.setAttributes(namespaceAttributes);
/* 826:    */    }
/* 827:    */    
/* 828:828 */    for (Iterator iter = element.attributeIterator(); iter.hasNext();) {
/* 829:829 */      Attribute attribute = (Attribute)iter.next();
/* 830:830 */      this.attributes.addAttribute(attribute.getNamespaceURI(), attribute.getName(), attribute.getQualifiedName(), "CDATA", attribute.getValue());
/* 831:    */    }
/* 832:    */    
/* 835:835 */    return this.attributes;
/* 836:    */  }
/* 837:    */  
/* 850:    */  protected AttributesImpl addNamespaceAttribute(AttributesImpl attrs, Namespace namespace)
/* 851:    */  {
/* 852:852 */    if (this.declareNamespaceAttributes) {
/* 853:853 */      if (attrs == null) {
/* 854:854 */        attrs = new AttributesImpl();
/* 855:    */      }
/* 856:    */      
/* 857:857 */      String prefix = namespace.getPrefix();
/* 858:858 */      String qualifiedName = "xmlns";
/* 859:    */      
/* 860:860 */      if ((prefix != null) && (prefix.length() > 0)) {
/* 861:861 */        qualifiedName = "xmlns:" + prefix;
/* 862:    */      }
/* 863:    */      
/* 864:864 */      String uri = "";
/* 865:865 */      String localName = prefix;
/* 866:866 */      String type = "CDATA";
/* 867:867 */      String value = namespace.getURI();
/* 868:    */      
/* 869:869 */      attrs.addAttribute(uri, localName, qualifiedName, type, value);
/* 870:    */    }
/* 871:    */    
/* 872:872 */    return attrs;
/* 873:    */  }
/* 874:    */  
/* 887:    */  protected boolean isIgnoreableNamespace(Namespace namespace, NamespaceStack namespaceStack)
/* 888:    */  {
/* 889:889 */    if ((namespace.equals(Namespace.NO_NAMESPACE)) || (namespace.equals(Namespace.XML_NAMESPACE)))
/* 890:    */    {
/* 891:891 */      return true;
/* 892:    */    }
/* 893:    */    
/* 894:894 */    String uri = namespace.getURI();
/* 895:    */    
/* 896:896 */    if ((uri == null) || (uri.length() <= 0)) {
/* 897:897 */      return true;
/* 898:    */    }
/* 899:    */    
/* 900:900 */    return namespaceStack.contains(namespace);
/* 901:    */  }
/* 902:    */  
/* 903:    */  protected void checkForNullHandlers() {}
/* 904:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.io.SAXWriter
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */