/*   1:    */package org.dom4j.io;
/*   2:    */
/*   3:    */import java.lang.reflect.Method;
/*   4:    */import java.util.ArrayList;
/*   5:    */import java.util.HashMap;
/*   6:    */import java.util.List;
/*   7:    */import java.util.Map;
/*   8:    */import org.dom4j.Branch;
/*   9:    */import org.dom4j.Document;
/*  10:    */import org.dom4j.DocumentFactory;
/*  11:    */import org.dom4j.DocumentType;
/*  12:    */import org.dom4j.Element;
/*  13:    */import org.dom4j.ElementHandler;
/*  14:    */import org.dom4j.Namespace;
/*  15:    */import org.dom4j.QName;
/*  16:    */import org.dom4j.dtd.AttributeDecl;
/*  17:    */import org.dom4j.dtd.ElementDecl;
/*  18:    */import org.dom4j.dtd.ExternalEntityDecl;
/*  19:    */import org.dom4j.dtd.InternalEntityDecl;
/*  20:    */import org.dom4j.tree.AbstractElement;
/*  21:    */import org.dom4j.tree.NamespaceStack;
/*  22:    */import org.xml.sax.Attributes;
/*  23:    */import org.xml.sax.DTDHandler;
/*  24:    */import org.xml.sax.EntityResolver;
/*  25:    */import org.xml.sax.InputSource;
/*  26:    */import org.xml.sax.Locator;
/*  27:    */import org.xml.sax.SAXException;
/*  28:    */import org.xml.sax.SAXParseException;
/*  29:    */import org.xml.sax.ext.DeclHandler;
/*  30:    */import org.xml.sax.ext.LexicalHandler;
/*  31:    */import org.xml.sax.helpers.DefaultHandler;
/*  32:    */
/*  72:    */public class SAXContentHandler
/*  73:    */  extends DefaultHandler
/*  74:    */  implements LexicalHandler, DeclHandler, DTDHandler
/*  75:    */{
/*  76:    */  private DocumentFactory documentFactory;
/*  77:    */  private Document document;
/*  78:    */  private ElementStack elementStack;
/*  79:    */  private NamespaceStack namespaceStack;
/*  80:    */  private ElementHandler elementHandler;
/*  81:    */  private Locator locator;
/*  82:    */  private String entity;
/*  83:    */  private boolean insideDTDSection;
/*  84:    */  private boolean insideCDATASection;
/*  85:    */  private StringBuffer cdataText;
/*  86: 86 */  private Map availableNamespaceMap = new HashMap();
/*  87:    */  
/*  89: 89 */  private List declaredNamespaceList = new ArrayList();
/*  90:    */  
/*  92:    */  private List internalDTDDeclarations;
/*  93:    */  
/*  95:    */  private List externalDTDDeclarations;
/*  96:    */  
/*  98:    */  private int declaredNamespaceIndex;
/*  99:    */  
/* 101:    */  private EntityResolver entityResolver;
/* 102:    */  
/* 104:    */  private InputSource inputSource;
/* 105:    */  
/* 107:    */  private Element currentElement;
/* 108:    */  
/* 109:109 */  private boolean includeInternalDTDDeclarations = false;
/* 110:    */  
/* 112:112 */  private boolean includeExternalDTDDeclarations = false;
/* 113:    */  
/* 115:    */  private int entityLevel;
/* 116:    */  
/* 118:118 */  private boolean internalDTDsubset = false;
/* 119:    */  
/* 121:121 */  private boolean mergeAdjacentText = false;
/* 122:    */  
/* 124:124 */  private boolean textInTextBuffer = false;
/* 125:    */  
/* 127:127 */  private boolean ignoreComments = false;
/* 128:    */  
/* 130:    */  private StringBuffer textBuffer;
/* 131:    */  
/* 133:133 */  private boolean stripWhitespaceText = false;
/* 134:    */  
/* 135:    */  public SAXContentHandler() {
/* 136:136 */    this(DocumentFactory.getInstance());
/* 137:    */  }
/* 138:    */  
/* 139:    */  public SAXContentHandler(DocumentFactory documentFactory) {
/* 140:140 */    this(documentFactory, null);
/* 141:    */  }
/* 142:    */  
/* 143:    */  public SAXContentHandler(DocumentFactory documentFactory, ElementHandler elementHandler)
/* 144:    */  {
/* 145:145 */    this(documentFactory, elementHandler, null);
/* 146:146 */    this.elementStack = createElementStack();
/* 147:    */  }
/* 148:    */  
/* 149:    */  public SAXContentHandler(DocumentFactory documentFactory, ElementHandler elementHandler, ElementStack elementStack)
/* 150:    */  {
/* 151:151 */    this.documentFactory = documentFactory;
/* 152:152 */    this.elementHandler = elementHandler;
/* 153:153 */    this.elementStack = elementStack;
/* 154:154 */    this.namespaceStack = new NamespaceStack(documentFactory);
/* 155:    */  }
/* 156:    */  
/* 161:    */  public Document getDocument()
/* 162:    */  {
/* 163:163 */    if (this.document == null) {
/* 164:164 */      this.document = createDocument();
/* 165:    */    }
/* 166:    */    
/* 167:167 */    return this.document;
/* 168:    */  }
/* 169:    */  
/* 171:    */  public void setDocumentLocator(Locator documentLocator)
/* 172:    */  {
/* 173:173 */    this.locator = documentLocator;
/* 174:    */  }
/* 175:    */  
/* 176:    */  public void processingInstruction(String target, String data) throws SAXException
/* 177:    */  {
/* 178:178 */    if ((this.mergeAdjacentText) && (this.textInTextBuffer)) {
/* 179:179 */      completeCurrentTextNode();
/* 180:    */    }
/* 181:    */    
/* 182:182 */    if (this.currentElement != null) {
/* 183:183 */      this.currentElement.addProcessingInstruction(target, data);
/* 184:    */    } else {
/* 185:185 */      getDocument().addProcessingInstruction(target, data);
/* 186:    */    }
/* 187:    */  }
/* 188:    */  
/* 189:    */  public void startPrefixMapping(String prefix, String uri) throws SAXException
/* 190:    */  {
/* 191:191 */    this.namespaceStack.push(prefix, uri);
/* 192:    */  }
/* 193:    */  
/* 194:    */  public void endPrefixMapping(String prefix) throws SAXException {
/* 195:195 */    this.namespaceStack.pop(prefix);
/* 196:196 */    this.declaredNamespaceIndex = this.namespaceStack.size();
/* 197:    */  }
/* 198:    */  
/* 199:    */  public void startDocument() throws SAXException
/* 200:    */  {
/* 201:201 */    this.document = null;
/* 202:202 */    this.currentElement = null;
/* 203:    */    
/* 204:204 */    this.elementStack.clear();
/* 205:    */    
/* 206:206 */    if ((this.elementHandler != null) && ((this.elementHandler instanceof DispatchHandler)))
/* 207:    */    {
/* 208:208 */      this.elementStack.setDispatchHandler((DispatchHandler)this.elementHandler);
/* 209:    */    }
/* 210:    */    
/* 211:211 */    this.namespaceStack.clear();
/* 212:212 */    this.declaredNamespaceIndex = 0;
/* 213:    */    
/* 214:214 */    if ((this.mergeAdjacentText) && (this.textBuffer == null)) {
/* 215:215 */      this.textBuffer = new StringBuffer();
/* 216:    */    }
/* 217:    */    
/* 218:218 */    this.textInTextBuffer = false;
/* 219:    */  }
/* 220:    */  
/* 221:    */  public void endDocument() throws SAXException {
/* 222:222 */    this.namespaceStack.clear();
/* 223:223 */    this.elementStack.clear();
/* 224:224 */    this.currentElement = null;
/* 225:225 */    this.textBuffer = null;
/* 226:    */  }
/* 227:    */  
/* 228:    */  public void startElement(String namespaceURI, String localName, String qualifiedName, Attributes attributes) throws SAXException
/* 229:    */  {
/* 230:230 */    if ((this.mergeAdjacentText) && (this.textInTextBuffer)) {
/* 231:231 */      completeCurrentTextNode();
/* 232:    */    }
/* 233:    */    
/* 234:234 */    QName qName = this.namespaceStack.getQName(namespaceURI, localName, qualifiedName);
/* 235:    */    
/* 237:237 */    Branch branch = this.currentElement;
/* 238:    */    
/* 239:239 */    if (branch == null) {
/* 240:240 */      branch = getDocument();
/* 241:    */    }
/* 242:    */    
/* 243:243 */    Element element = branch.addElement(qName);
/* 244:    */    
/* 246:246 */    addDeclaredNamespaces(element);
/* 247:    */    
/* 249:249 */    addAttributes(element, attributes);
/* 250:    */    
/* 251:251 */    this.elementStack.pushElement(element);
/* 252:252 */    this.currentElement = element;
/* 253:    */    
/* 254:254 */    this.entity = null;
/* 255:    */    
/* 256:256 */    if (this.elementHandler != null) {
/* 257:257 */      this.elementHandler.onStart(this.elementStack);
/* 258:    */    }
/* 259:    */  }
/* 260:    */  
/* 261:    */  public void endElement(String namespaceURI, String localName, String qName) throws SAXException
/* 262:    */  {
/* 263:263 */    if ((this.mergeAdjacentText) && (this.textInTextBuffer)) {
/* 264:264 */      completeCurrentTextNode();
/* 265:    */    }
/* 266:    */    
/* 267:267 */    if ((this.elementHandler != null) && (this.currentElement != null)) {
/* 268:268 */      this.elementHandler.onEnd(this.elementStack);
/* 269:    */    }
/* 270:    */    
/* 271:271 */    this.elementStack.popElement();
/* 272:272 */    this.currentElement = this.elementStack.peekElement();
/* 273:    */  }
/* 274:    */  
/* 275:    */  public void characters(char[] ch, int start, int end) throws SAXException {
/* 276:276 */    if (end == 0) {
/* 277:277 */      return;
/* 278:    */    }
/* 279:    */    
/* 280:280 */    if (this.currentElement != null) {
/* 281:281 */      if (this.entity != null) {
/* 282:282 */        if ((this.mergeAdjacentText) && (this.textInTextBuffer)) {
/* 283:283 */          completeCurrentTextNode();
/* 284:    */        }
/* 285:    */        
/* 286:286 */        this.currentElement.addEntity(this.entity, new String(ch, start, end));
/* 287:287 */        this.entity = null;
/* 288:288 */      } else if (this.insideCDATASection) {
/* 289:289 */        if ((this.mergeAdjacentText) && (this.textInTextBuffer)) {
/* 290:290 */          completeCurrentTextNode();
/* 291:    */        }
/* 292:    */        
/* 293:293 */        this.cdataText.append(new String(ch, start, end));
/* 294:    */      }
/* 295:295 */      else if (this.mergeAdjacentText) {
/* 296:296 */        this.textBuffer.append(ch, start, end);
/* 297:297 */        this.textInTextBuffer = true;
/* 298:    */      } else {
/* 299:299 */        this.currentElement.addText(new String(ch, start, end));
/* 300:    */      }
/* 301:    */    }
/* 302:    */  }
/* 303:    */  
/* 315:    */  public void warning(SAXParseException exception)
/* 316:    */    throws SAXException
/* 317:    */  {}
/* 318:    */  
/* 330:    */  public void error(SAXParseException exception)
/* 331:    */    throws SAXException
/* 332:    */  {
/* 333:333 */    throw exception;
/* 334:    */  }
/* 335:    */  
/* 344:    */  public void fatalError(SAXParseException exception)
/* 345:    */    throws SAXException
/* 346:    */  {
/* 347:347 */    throw exception;
/* 348:    */  }
/* 349:    */  
/* 351:    */  public void startDTD(String name, String publicId, String systemId)
/* 352:    */    throws SAXException
/* 353:    */  {
/* 354:354 */    getDocument().addDocType(name, publicId, systemId);
/* 355:355 */    this.insideDTDSection = true;
/* 356:356 */    this.internalDTDsubset = true;
/* 357:    */  }
/* 358:    */  
/* 359:    */  public void endDTD() throws SAXException {
/* 360:360 */    this.insideDTDSection = false;
/* 361:    */    
/* 362:362 */    DocumentType docType = getDocument().getDocType();
/* 363:    */    
/* 364:364 */    if (docType != null) {
/* 365:365 */      if (this.internalDTDDeclarations != null) {
/* 366:366 */        docType.setInternalDeclarations(this.internalDTDDeclarations);
/* 367:    */      }
/* 368:    */      
/* 369:369 */      if (this.externalDTDDeclarations != null) {
/* 370:370 */        docType.setExternalDeclarations(this.externalDTDDeclarations);
/* 371:    */      }
/* 372:    */    }
/* 373:    */    
/* 374:374 */    this.internalDTDDeclarations = null;
/* 375:375 */    this.externalDTDDeclarations = null;
/* 376:    */  }
/* 377:    */  
/* 378:    */  public void startEntity(String name) throws SAXException {
/* 379:379 */    this.entityLevel += 1;
/* 380:    */    
/* 382:382 */    this.entity = null;
/* 383:    */    
/* 384:384 */    if ((!this.insideDTDSection) && 
/* 385:385 */      (!isIgnorableEntity(name))) {
/* 386:386 */      this.entity = name;
/* 387:    */    }
/* 388:    */    
/* 394:394 */    this.internalDTDsubset = false;
/* 395:    */  }
/* 396:    */  
/* 397:    */  public void endEntity(String name) throws SAXException {
/* 398:398 */    this.entityLevel -= 1;
/* 399:399 */    this.entity = null;
/* 400:    */    
/* 401:401 */    if (this.entityLevel == 0) {
/* 402:402 */      this.internalDTDsubset = true;
/* 403:    */    }
/* 404:    */  }
/* 405:    */  
/* 406:    */  public void startCDATA() throws SAXException {
/* 407:407 */    this.insideCDATASection = true;
/* 408:408 */    this.cdataText = new StringBuffer();
/* 409:    */  }
/* 410:    */  
/* 411:    */  public void endCDATA() throws SAXException {
/* 412:412 */    this.insideCDATASection = false;
/* 413:413 */    this.currentElement.addCDATA(this.cdataText.toString());
/* 414:    */  }
/* 415:    */  
/* 416:    */  public void comment(char[] ch, int start, int end) throws SAXException {
/* 417:417 */    if (!this.ignoreComments) {
/* 418:418 */      if ((this.mergeAdjacentText) && (this.textInTextBuffer)) {
/* 419:419 */        completeCurrentTextNode();
/* 420:    */      }
/* 421:    */      
/* 422:422 */      String text = new String(ch, start, end);
/* 423:    */      
/* 424:424 */      if ((!this.insideDTDSection) && (text.length() > 0)) {
/* 425:425 */        if (this.currentElement != null) {
/* 426:426 */          this.currentElement.addComment(text);
/* 427:    */        } else {
/* 428:428 */          getDocument().addComment(text);
/* 429:    */        }
/* 430:    */      }
/* 431:    */    }
/* 432:    */  }
/* 433:    */  
/* 455:    */  public void elementDecl(String name, String model)
/* 456:    */    throws SAXException
/* 457:    */  {
/* 458:458 */    if (this.internalDTDsubset) {
/* 459:459 */      if (this.includeInternalDTDDeclarations) {
/* 460:460 */        addDTDDeclaration(new ElementDecl(name, model));
/* 461:    */      }
/* 462:    */    }
/* 463:463 */    else if (this.includeExternalDTDDeclarations) {
/* 464:464 */      addExternalDTDDeclaration(new ElementDecl(name, model));
/* 465:    */    }
/* 466:    */  }
/* 467:    */  
/* 501:    */  public void attributeDecl(String eName, String aName, String type, String valueDefault, String val)
/* 502:    */    throws SAXException
/* 503:    */  {
/* 504:504 */    if (this.internalDTDsubset) {
/* 505:505 */      if (this.includeInternalDTDDeclarations) {
/* 506:506 */        addDTDDeclaration(new AttributeDecl(eName, aName, type, valueDefault, val));
/* 507:    */      }
/* 508:    */      
/* 509:    */    }
/* 510:510 */    else if (this.includeExternalDTDDeclarations) {
/* 511:511 */      addExternalDTDDeclaration(new AttributeDecl(eName, aName, type, valueDefault, val));
/* 512:    */    }
/* 513:    */  }
/* 514:    */  
/* 537:    */  public void internalEntityDecl(String name, String value)
/* 538:    */    throws SAXException
/* 539:    */  {
/* 540:540 */    if (this.internalDTDsubset) {
/* 541:541 */      if (this.includeInternalDTDDeclarations) {
/* 542:542 */        addDTDDeclaration(new InternalEntityDecl(name, value));
/* 543:    */      }
/* 544:    */    }
/* 545:545 */    else if (this.includeExternalDTDDeclarations) {
/* 546:546 */      addExternalDTDDeclaration(new InternalEntityDecl(name, value));
/* 547:    */    }
/* 548:    */  }
/* 549:    */  
/* 572:    */  public void externalEntityDecl(String name, String publicId, String sysId)
/* 573:    */    throws SAXException
/* 574:    */  {
/* 575:575 */    ExternalEntityDecl declaration = new ExternalEntityDecl(name, publicId, sysId);
/* 576:    */    
/* 578:578 */    if (this.internalDTDsubset) {
/* 579:579 */      if (this.includeInternalDTDDeclarations) {
/* 580:580 */        addDTDDeclaration(declaration);
/* 581:    */      }
/* 582:    */    }
/* 583:583 */    else if (this.includeExternalDTDDeclarations) {
/* 584:584 */      addExternalDTDDeclaration(declaration);
/* 585:    */    }
/* 586:    */  }
/* 587:    */  
/* 611:    */  public void notationDecl(String name, String publicId, String systemId)
/* 612:    */    throws SAXException
/* 613:    */  {}
/* 614:    */  
/* 638:    */  public void unparsedEntityDecl(String name, String publicId, String systemId, String notationName)
/* 639:    */    throws SAXException
/* 640:    */  {}
/* 641:    */  
/* 664:    */  public ElementStack getElementStack()
/* 665:    */  {
/* 666:666 */    return this.elementStack;
/* 667:    */  }
/* 668:    */  
/* 669:    */  public void setElementStack(ElementStack elementStack) {
/* 670:670 */    this.elementStack = elementStack;
/* 671:    */  }
/* 672:    */  
/* 673:    */  public EntityResolver getEntityResolver() {
/* 674:674 */    return this.entityResolver;
/* 675:    */  }
/* 676:    */  
/* 677:    */  public void setEntityResolver(EntityResolver entityResolver) {
/* 678:678 */    this.entityResolver = entityResolver;
/* 679:    */  }
/* 680:    */  
/* 681:    */  public InputSource getInputSource() {
/* 682:682 */    return this.inputSource;
/* 683:    */  }
/* 684:    */  
/* 685:    */  public void setInputSource(InputSource inputSource) {
/* 686:686 */    this.inputSource = inputSource;
/* 687:    */  }
/* 688:    */  
/* 694:    */  public boolean isIncludeInternalDTDDeclarations()
/* 695:    */  {
/* 696:696 */    return this.includeInternalDTDDeclarations;
/* 697:    */  }
/* 698:    */  
/* 706:    */  public void setIncludeInternalDTDDeclarations(boolean include)
/* 707:    */  {
/* 708:708 */    this.includeInternalDTDDeclarations = include;
/* 709:    */  }
/* 710:    */  
/* 716:    */  public boolean isIncludeExternalDTDDeclarations()
/* 717:    */  {
/* 718:718 */    return this.includeExternalDTDDeclarations;
/* 719:    */  }
/* 720:    */  
/* 728:    */  public void setIncludeExternalDTDDeclarations(boolean include)
/* 729:    */  {
/* 730:730 */    this.includeExternalDTDDeclarations = include;
/* 731:    */  }
/* 732:    */  
/* 737:    */  public boolean isMergeAdjacentText()
/* 738:    */  {
/* 739:739 */    return this.mergeAdjacentText;
/* 740:    */  }
/* 741:    */  
/* 748:    */  public void setMergeAdjacentText(boolean mergeAdjacentText)
/* 749:    */  {
/* 750:750 */    this.mergeAdjacentText = mergeAdjacentText;
/* 751:    */  }
/* 752:    */  
/* 758:    */  public boolean isStripWhitespaceText()
/* 759:    */  {
/* 760:760 */    return this.stripWhitespaceText;
/* 761:    */  }
/* 762:    */  
/* 769:    */  public void setStripWhitespaceText(boolean stripWhitespaceText)
/* 770:    */  {
/* 771:771 */    this.stripWhitespaceText = stripWhitespaceText;
/* 772:    */  }
/* 773:    */  
/* 778:    */  public boolean isIgnoreComments()
/* 779:    */  {
/* 780:780 */    return this.ignoreComments;
/* 781:    */  }
/* 782:    */  
/* 788:    */  public void setIgnoreComments(boolean ignoreComments)
/* 789:    */  {
/* 790:790 */    this.ignoreComments = ignoreComments;
/* 791:    */  }
/* 792:    */  
/* 799:    */  protected void completeCurrentTextNode()
/* 800:    */  {
/* 801:801 */    if (this.stripWhitespaceText) {
/* 802:802 */      boolean whitespace = true;
/* 803:    */      
/* 804:804 */      int i = 0; for (int size = this.textBuffer.length(); i < size; i++) {
/* 805:805 */        if (!Character.isWhitespace(this.textBuffer.charAt(i))) {
/* 806:806 */          whitespace = false;
/* 807:    */          
/* 808:808 */          break;
/* 809:    */        }
/* 810:    */      }
/* 811:    */      
/* 812:812 */      if (!whitespace) {
/* 813:813 */        this.currentElement.addText(this.textBuffer.toString());
/* 814:    */      }
/* 815:    */    } else {
/* 816:816 */      this.currentElement.addText(this.textBuffer.toString());
/* 817:    */    }
/* 818:    */    
/* 819:819 */    this.textBuffer.setLength(0);
/* 820:820 */    this.textInTextBuffer = false;
/* 821:    */  }
/* 822:    */  
/* 827:    */  protected Document createDocument()
/* 828:    */  {
/* 829:829 */    String encoding = getEncoding();
/* 830:830 */    Document doc = this.documentFactory.createDocument(encoding);
/* 831:    */    
/* 833:833 */    doc.setEntityResolver(this.entityResolver);
/* 834:    */    
/* 835:835 */    if (this.inputSource != null) {
/* 836:836 */      doc.setName(this.inputSource.getSystemId());
/* 837:    */    }
/* 838:    */    
/* 839:839 */    return doc;
/* 840:    */  }
/* 841:    */  
/* 842:    */  private String getEncoding() {
/* 843:843 */    if (this.locator == null) {
/* 844:844 */      return null;
/* 845:    */    }
/* 846:    */    
/* 848:    */    try
/* 849:    */    {
/* 850:850 */      Method m = this.locator.getClass().getMethod("getEncoding", new Class[0]);
/* 851:    */      
/* 853:853 */      if (m != null) {
/* 854:854 */        return (String)m.invoke(this.locator, null);
/* 855:    */      }
/* 856:    */    }
/* 857:    */    catch (Exception e) {}
/* 858:    */    
/* 861:861 */    return null;
/* 862:    */  }
/* 863:    */  
/* 871:    */  protected boolean isIgnorableEntity(String name)
/* 872:    */  {
/* 873:873 */    return ("amp".equals(name)) || ("apos".equals(name)) || ("gt".equals(name)) || ("lt".equals(name)) || ("quot".equals(name));
/* 874:    */  }
/* 875:    */  
/* 884:    */  protected void addDeclaredNamespaces(Element element)
/* 885:    */  {
/* 886:886 */    Namespace elementNamespace = element.getNamespace();
/* 887:    */    
/* 888:888 */    for (int size = this.namespaceStack.size(); this.declaredNamespaceIndex < size; 
/* 889:889 */        this.declaredNamespaceIndex += 1) {
/* 890:890 */      Namespace namespace = this.namespaceStack.getNamespace(this.declaredNamespaceIndex);
/* 891:    */      
/* 894:894 */      element.add(namespace);
/* 895:    */    }
/* 896:    */  }
/* 897:    */  
/* 909:    */  protected void addAttributes(Element element, Attributes attributes)
/* 910:    */  {
/* 911:911 */    boolean noNamespaceAttributes = false;
/* 912:    */    
/* 913:913 */    if ((element instanceof AbstractElement))
/* 914:    */    {
/* 915:915 */      AbstractElement baseElement = (AbstractElement)element;
/* 916:916 */      baseElement.setAttributes(attributes, this.namespaceStack, noNamespaceAttributes);
/* 917:    */    }
/* 918:    */    else {
/* 919:919 */      int size = attributes.getLength();
/* 920:    */      
/* 921:921 */      for (int i = 0; i < size; i++) {
/* 922:922 */        String attributeQName = attributes.getQName(i);
/* 923:    */        
/* 924:924 */        if ((noNamespaceAttributes) || (!attributeQName.startsWith("xmlns")))
/* 925:    */        {
/* 926:926 */          String attributeURI = attributes.getURI(i);
/* 927:927 */          String attributeLocalName = attributes.getLocalName(i);
/* 928:928 */          String attributeValue = attributes.getValue(i);
/* 929:    */          
/* 930:930 */          QName qName = this.namespaceStack.getAttributeQName(attributeURI, attributeLocalName, attributeQName);
/* 931:    */          
/* 932:932 */          element.addAttribute(qName, attributeValue);
/* 933:    */        }
/* 934:    */      }
/* 935:    */    }
/* 936:    */  }
/* 937:    */  
/* 943:    */  protected void addDTDDeclaration(Object declaration)
/* 944:    */  {
/* 945:945 */    if (this.internalDTDDeclarations == null) {
/* 946:946 */      this.internalDTDDeclarations = new ArrayList();
/* 947:    */    }
/* 948:    */    
/* 949:949 */    this.internalDTDDeclarations.add(declaration);
/* 950:    */  }
/* 951:    */  
/* 957:    */  protected void addExternalDTDDeclaration(Object declaration)
/* 958:    */  {
/* 959:959 */    if (this.externalDTDDeclarations == null) {
/* 960:960 */      this.externalDTDDeclarations = new ArrayList();
/* 961:    */    }
/* 962:    */    
/* 963:963 */    this.externalDTDDeclarations.add(declaration);
/* 964:    */  }
/* 965:    */  
/* 966:    */  protected ElementStack createElementStack() {
/* 967:967 */    return new ElementStack();
/* 968:    */  }
/* 969:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.io.SAXContentHandler
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */