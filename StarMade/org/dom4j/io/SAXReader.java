/*    1:     */package org.dom4j.io;
/*    2:     */
/*    3:     */import java.io.File;
/*    4:     */import java.io.FileInputStream;
/*    5:     */import java.io.FileNotFoundException;
/*    6:     */import java.io.InputStream;
/*    7:     */import java.io.Reader;
/*    8:     */import java.io.Serializable;
/*    9:     */import java.net.URL;
/*   10:     */import org.dom4j.Document;
/*   11:     */import org.dom4j.DocumentException;
/*   12:     */import org.dom4j.DocumentFactory;
/*   13:     */import org.dom4j.ElementHandler;
/*   14:     */import org.xml.sax.EntityResolver;
/*   15:     */import org.xml.sax.ErrorHandler;
/*   16:     */import org.xml.sax.InputSource;
/*   17:     */import org.xml.sax.SAXException;
/*   18:     */import org.xml.sax.SAXParseException;
/*   19:     */import org.xml.sax.XMLFilter;
/*   20:     */import org.xml.sax.XMLReader;
/*   21:     */import org.xml.sax.helpers.DefaultHandler;
/*   22:     */import org.xml.sax.helpers.XMLReaderFactory;
/*   23:     */
/*   98:     */public class SAXReader
/*   99:     */{
/*  100:     */  private static final String SAX_STRING_INTERNING = "http://xml.org/sax/features/string-interning";
/*  101:     */  private static final String SAX_NAMESPACE_PREFIXES = "http://xml.org/sax/features/namespace-prefixes";
/*  102:     */  private static final String SAX_NAMESPACES = "http://xml.org/sax/features/namespaces";
/*  103:     */  private static final String SAX_DECL_HANDLER = "http://xml.org/sax/properties/declaration-handler";
/*  104:     */  private static final String SAX_LEXICAL_HANDLER = "http://xml.org/sax/properties/lexical-handler";
/*  105:     */  private static final String SAX_LEXICALHANDLER = "http://xml.org/sax/handlers/LexicalHandler";
/*  106:     */  private DocumentFactory factory;
/*  107:     */  private XMLReader xmlReader;
/*  108:     */  private boolean validating;
/*  109:     */  private DispatchHandler dispatchHandler;
/*  110:     */  private ErrorHandler errorHandler;
/*  111:     */  private EntityResolver entityResolver;
/*  112: 112 */  private boolean stringInternEnabled = true;
/*  113:     */  
/*  115: 115 */  private boolean includeInternalDTDDeclarations = false;
/*  116:     */  
/*  118: 118 */  private boolean includeExternalDTDDeclarations = false;
/*  119:     */  
/*  121: 121 */  private boolean mergeAdjacentText = false;
/*  122:     */  
/*  124: 124 */  private boolean stripWhitespaceText = false;
/*  125:     */  
/*  127: 127 */  private boolean ignoreComments = false;
/*  128:     */  
/*  130: 130 */  private String encoding = null;
/*  131:     */  
/*  133:     */  private XMLFilter xmlFilter;
/*  134:     */  
/*  137:     */  public SAXReader() {}
/*  138:     */  
/*  140:     */  public SAXReader(boolean validating)
/*  141:     */  {
/*  142: 142 */    this.validating = validating;
/*  143:     */  }
/*  144:     */  
/*  145:     */  public SAXReader(DocumentFactory factory) {
/*  146: 146 */    this.factory = factory;
/*  147:     */  }
/*  148:     */  
/*  149:     */  public SAXReader(DocumentFactory factory, boolean validating) {
/*  150: 150 */    this.factory = factory;
/*  151: 151 */    this.validating = validating;
/*  152:     */  }
/*  153:     */  
/*  154:     */  public SAXReader(XMLReader xmlReader) {
/*  155: 155 */    this.xmlReader = xmlReader;
/*  156:     */  }
/*  157:     */  
/*  158:     */  public SAXReader(XMLReader xmlReader, boolean validating) {
/*  159: 159 */    this.xmlReader = xmlReader;
/*  160: 160 */    this.validating = validating;
/*  161:     */  }
/*  162:     */  
/*  163:     */  public SAXReader(String xmlReaderClassName) throws SAXException {
/*  164: 164 */    if (xmlReaderClassName != null) {
/*  165: 165 */      this.xmlReader = XMLReaderFactory.createXMLReader(xmlReaderClassName);
/*  166:     */    }
/*  167:     */  }
/*  168:     */  
/*  169:     */  public SAXReader(String xmlReaderClassName, boolean validating)
/*  170:     */    throws SAXException
/*  171:     */  {
/*  172: 172 */    if (xmlReaderClassName != null) {
/*  173: 173 */      this.xmlReader = XMLReaderFactory.createXMLReader(xmlReaderClassName);
/*  174:     */    }
/*  175:     */    
/*  177: 177 */    this.validating = validating;
/*  178:     */  }
/*  179:     */  
/*  195:     */  public void setProperty(String name, Object value)
/*  196:     */    throws SAXException
/*  197:     */  {
/*  198: 198 */    getXMLReader().setProperty(name, value);
/*  199:     */  }
/*  200:     */  
/*  215:     */  public void setFeature(String name, boolean value)
/*  216:     */    throws SAXException
/*  217:     */  {
/*  218: 218 */    getXMLReader().setFeature(name, value);
/*  219:     */  }
/*  220:     */  
/*  238:     */  public Document read(File file)
/*  239:     */    throws DocumentException
/*  240:     */  {
/*  241:     */    try
/*  242:     */    {
/*  243: 243 */      InputSource source = new InputSource(new FileInputStream(file));
/*  244: 244 */      if (this.encoding != null) {
/*  245: 245 */        source.setEncoding(this.encoding);
/*  246:     */      }
/*  247: 247 */      String path = file.getAbsolutePath();
/*  248:     */      
/*  249: 249 */      if (path != null)
/*  250:     */      {
/*  251: 251 */        StringBuffer sb = new StringBuffer("file://");
/*  252:     */        
/*  254: 254 */        if (!path.startsWith(File.separator)) {
/*  255: 255 */          sb.append("/");
/*  256:     */        }
/*  257:     */        
/*  258: 258 */        path = path.replace('\\', '/');
/*  259: 259 */        sb.append(path);
/*  260:     */        
/*  261: 261 */        source.setSystemId(sb.toString());
/*  262:     */      }
/*  263:     */      
/*  264: 264 */      return read(source);
/*  265:     */    } catch (FileNotFoundException e) {
/*  266: 266 */      throw new DocumentException(e.getMessage(), e);
/*  267:     */    }
/*  268:     */  }
/*  269:     */  
/*  281:     */  public Document read(URL url)
/*  282:     */    throws DocumentException
/*  283:     */  {
/*  284: 284 */    String systemID = url.toExternalForm();
/*  285:     */    
/*  286: 286 */    InputSource source = new InputSource(systemID);
/*  287: 287 */    if (this.encoding != null) {
/*  288: 288 */      source.setEncoding(this.encoding);
/*  289:     */    }
/*  290:     */    
/*  291: 291 */    return read(source);
/*  292:     */  }
/*  293:     */  
/*  313:     */  public Document read(String systemId)
/*  314:     */    throws DocumentException
/*  315:     */  {
/*  316: 316 */    InputSource source = new InputSource(systemId);
/*  317: 317 */    if (this.encoding != null) {
/*  318: 318 */      source.setEncoding(this.encoding);
/*  319:     */    }
/*  320:     */    
/*  321: 321 */    return read(source);
/*  322:     */  }
/*  323:     */  
/*  335:     */  public Document read(InputStream in)
/*  336:     */    throws DocumentException
/*  337:     */  {
/*  338: 338 */    InputSource source = new InputSource(in);
/*  339: 339 */    if (this.encoding != null) {
/*  340: 340 */      source.setEncoding(this.encoding);
/*  341:     */    }
/*  342:     */    
/*  343: 343 */    return read(source);
/*  344:     */  }
/*  345:     */  
/*  357:     */  public Document read(Reader reader)
/*  358:     */    throws DocumentException
/*  359:     */  {
/*  360: 360 */    InputSource source = new InputSource(reader);
/*  361: 361 */    if (this.encoding != null) {
/*  362: 362 */      source.setEncoding(this.encoding);
/*  363:     */    }
/*  364:     */    
/*  365: 365 */    return read(source);
/*  366:     */  }
/*  367:     */  
/*  382:     */  public Document read(InputStream in, String systemId)
/*  383:     */    throws DocumentException
/*  384:     */  {
/*  385: 385 */    InputSource source = new InputSource(in);
/*  386: 386 */    source.setSystemId(systemId);
/*  387: 387 */    if (this.encoding != null) {
/*  388: 388 */      source.setEncoding(this.encoding);
/*  389:     */    }
/*  390:     */    
/*  391: 391 */    return read(source);
/*  392:     */  }
/*  393:     */  
/*  408:     */  public Document read(Reader reader, String systemId)
/*  409:     */    throws DocumentException
/*  410:     */  {
/*  411: 411 */    InputSource source = new InputSource(reader);
/*  412: 412 */    source.setSystemId(systemId);
/*  413: 413 */    if (this.encoding != null) {
/*  414: 414 */      source.setEncoding(this.encoding);
/*  415:     */    }
/*  416:     */    
/*  417: 417 */    return read(source);
/*  418:     */  }
/*  419:     */  
/*  430:     */  public Document read(InputSource in)
/*  431:     */    throws DocumentException
/*  432:     */  {
/*  433:     */    try
/*  434:     */    {
/*  435: 435 */      XMLReader reader = getXMLReader();
/*  436:     */      
/*  437: 437 */      reader = installXMLFilter(reader);
/*  438:     */      
/*  439: 439 */      EntityResolver thatEntityResolver = this.entityResolver;
/*  440:     */      
/*  441: 441 */      if (thatEntityResolver == null) {
/*  442: 442 */        thatEntityResolver = createDefaultEntityResolver(in.getSystemId());
/*  443:     */        
/*  444: 444 */        this.entityResolver = thatEntityResolver;
/*  445:     */      }
/*  446:     */      
/*  447: 447 */      reader.setEntityResolver(thatEntityResolver);
/*  448:     */      
/*  449: 449 */      SAXContentHandler contentHandler = createContentHandler(reader);
/*  450: 450 */      contentHandler.setEntityResolver(thatEntityResolver);
/*  451: 451 */      contentHandler.setInputSource(in);
/*  452:     */      
/*  453: 453 */      boolean internal = isIncludeInternalDTDDeclarations();
/*  454: 454 */      boolean external = isIncludeExternalDTDDeclarations();
/*  455:     */      
/*  456: 456 */      contentHandler.setIncludeInternalDTDDeclarations(internal);
/*  457: 457 */      contentHandler.setIncludeExternalDTDDeclarations(external);
/*  458: 458 */      contentHandler.setMergeAdjacentText(isMergeAdjacentText());
/*  459: 459 */      contentHandler.setStripWhitespaceText(isStripWhitespaceText());
/*  460: 460 */      contentHandler.setIgnoreComments(isIgnoreComments());
/*  461: 461 */      reader.setContentHandler(contentHandler);
/*  462:     */      
/*  463: 463 */      configureReader(reader, contentHandler);
/*  464:     */      
/*  465: 465 */      reader.parse(in);
/*  466:     */      
/*  467: 467 */      return contentHandler.getDocument();
/*  468:     */    } catch (Exception e) {
/*  469: 469 */      if ((e instanceof SAXParseException))
/*  470:     */      {
/*  471: 471 */        SAXParseException parseException = (SAXParseException)e;
/*  472: 472 */        String systemId = parseException.getSystemId();
/*  473:     */        
/*  474: 474 */        if (systemId == null) {
/*  475: 475 */          systemId = "";
/*  476:     */        }
/*  477:     */        
/*  478: 478 */        String message = "Error on line " + parseException.getLineNumber() + " of document " + systemId + " : " + parseException.getMessage();
/*  479:     */        
/*  482: 482 */        throw new DocumentException(message, e);
/*  483:     */      }
/*  484: 484 */      throw new DocumentException(e.getMessage(), e);
/*  485:     */    }
/*  486:     */  }
/*  487:     */  
/*  497:     */  public boolean isValidating()
/*  498:     */  {
/*  499: 499 */    return this.validating;
/*  500:     */  }
/*  501:     */  
/*  507:     */  public void setValidation(boolean validation)
/*  508:     */  {
/*  509: 509 */    this.validating = validation;
/*  510:     */  }
/*  511:     */  
/*  517:     */  public boolean isIncludeInternalDTDDeclarations()
/*  518:     */  {
/*  519: 519 */    return this.includeInternalDTDDeclarations;
/*  520:     */  }
/*  521:     */  
/*  529:     */  public void setIncludeInternalDTDDeclarations(boolean include)
/*  530:     */  {
/*  531: 531 */    this.includeInternalDTDDeclarations = include;
/*  532:     */  }
/*  533:     */  
/*  539:     */  public boolean isIncludeExternalDTDDeclarations()
/*  540:     */  {
/*  541: 541 */    return this.includeExternalDTDDeclarations;
/*  542:     */  }
/*  543:     */  
/*  551:     */  public void setIncludeExternalDTDDeclarations(boolean include)
/*  552:     */  {
/*  553: 553 */    this.includeExternalDTDDeclarations = include;
/*  554:     */  }
/*  555:     */  
/*  561:     */  public boolean isStringInternEnabled()
/*  562:     */  {
/*  563: 563 */    return this.stringInternEnabled;
/*  564:     */  }
/*  565:     */  
/*  572:     */  public void setStringInternEnabled(boolean stringInternEnabled)
/*  573:     */  {
/*  574: 574 */    this.stringInternEnabled = stringInternEnabled;
/*  575:     */  }
/*  576:     */  
/*  581:     */  public boolean isMergeAdjacentText()
/*  582:     */  {
/*  583: 583 */    return this.mergeAdjacentText;
/*  584:     */  }
/*  585:     */  
/*  592:     */  public void setMergeAdjacentText(boolean mergeAdjacentText)
/*  593:     */  {
/*  594: 594 */    this.mergeAdjacentText = mergeAdjacentText;
/*  595:     */  }
/*  596:     */  
/*  602:     */  public boolean isStripWhitespaceText()
/*  603:     */  {
/*  604: 604 */    return this.stripWhitespaceText;
/*  605:     */  }
/*  606:     */  
/*  613:     */  public void setStripWhitespaceText(boolean stripWhitespaceText)
/*  614:     */  {
/*  615: 615 */    this.stripWhitespaceText = stripWhitespaceText;
/*  616:     */  }
/*  617:     */  
/*  622:     */  public boolean isIgnoreComments()
/*  623:     */  {
/*  624: 624 */    return this.ignoreComments;
/*  625:     */  }
/*  626:     */  
/*  632:     */  public void setIgnoreComments(boolean ignoreComments)
/*  633:     */  {
/*  634: 634 */    this.ignoreComments = ignoreComments;
/*  635:     */  }
/*  636:     */  
/*  642:     */  public DocumentFactory getDocumentFactory()
/*  643:     */  {
/*  644: 644 */    if (this.factory == null) {
/*  645: 645 */      this.factory = DocumentFactory.getInstance();
/*  646:     */    }
/*  647:     */    
/*  648: 648 */    return this.factory;
/*  649:     */  }
/*  650:     */  
/*  661:     */  public void setDocumentFactory(DocumentFactory documentFactory)
/*  662:     */  {
/*  663: 663 */    this.factory = documentFactory;
/*  664:     */  }
/*  665:     */  
/*  670:     */  public ErrorHandler getErrorHandler()
/*  671:     */  {
/*  672: 672 */    return this.errorHandler;
/*  673:     */  }
/*  674:     */  
/*  681:     */  public void setErrorHandler(ErrorHandler errorHandler)
/*  682:     */  {
/*  683: 683 */    this.errorHandler = errorHandler;
/*  684:     */  }
/*  685:     */  
/*  690:     */  public EntityResolver getEntityResolver()
/*  691:     */  {
/*  692: 692 */    return this.entityResolver;
/*  693:     */  }
/*  694:     */  
/*  700:     */  public void setEntityResolver(EntityResolver entityResolver)
/*  701:     */  {
/*  702: 702 */    this.entityResolver = entityResolver;
/*  703:     */  }
/*  704:     */  
/*  711:     */  public XMLReader getXMLReader()
/*  712:     */    throws SAXException
/*  713:     */  {
/*  714: 714 */    if (this.xmlReader == null) {
/*  715: 715 */      this.xmlReader = createXMLReader();
/*  716:     */    }
/*  717:     */    
/*  718: 718 */    return this.xmlReader;
/*  719:     */  }
/*  720:     */  
/*  726:     */  public void setXMLReader(XMLReader reader)
/*  727:     */  {
/*  728: 728 */    this.xmlReader = reader;
/*  729:     */  }
/*  730:     */  
/*  737:     */  public String getEncoding()
/*  738:     */  {
/*  739: 739 */    return this.encoding;
/*  740:     */  }
/*  741:     */  
/*  747:     */  public void setEncoding(String encoding)
/*  748:     */  {
/*  749: 749 */    this.encoding = encoding;
/*  750:     */  }
/*  751:     */  
/*  762:     */  public void setXMLReaderClassName(String xmlReaderClassName)
/*  763:     */    throws SAXException
/*  764:     */  {
/*  765: 765 */    setXMLReader(XMLReaderFactory.createXMLReader(xmlReaderClassName));
/*  766:     */  }
/*  767:     */  
/*  777:     */  public void addHandler(String path, ElementHandler handler)
/*  778:     */  {
/*  779: 779 */    getDispatchHandler().addHandler(path, handler);
/*  780:     */  }
/*  781:     */  
/*  788:     */  public void removeHandler(String path)
/*  789:     */  {
/*  790: 790 */    getDispatchHandler().removeHandler(path);
/*  791:     */  }
/*  792:     */  
/*  801:     */  public void setDefaultHandler(ElementHandler handler)
/*  802:     */  {
/*  803: 803 */    getDispatchHandler().setDefaultHandler(handler);
/*  804:     */  }
/*  805:     */  
/*  810:     */  public void resetHandlers()
/*  811:     */  {
/*  812: 812 */    getDispatchHandler().resetHandlers();
/*  813:     */  }
/*  814:     */  
/*  819:     */  public XMLFilter getXMLFilter()
/*  820:     */  {
/*  821: 821 */    return this.xmlFilter;
/*  822:     */  }
/*  823:     */  
/*  829:     */  public void setXMLFilter(XMLFilter filter)
/*  830:     */  {
/*  831: 831 */    this.xmlFilter = filter;
/*  832:     */  }
/*  833:     */  
/*  846:     */  protected XMLReader installXMLFilter(XMLReader reader)
/*  847:     */  {
/*  848: 848 */    XMLFilter filter = getXMLFilter();
/*  849:     */    
/*  850: 850 */    if (filter != null)
/*  851:     */    {
/*  852: 852 */      XMLFilter root = filter;
/*  853:     */      for (;;)
/*  854:     */      {
/*  855: 855 */        XMLReader parent = root.getParent();
/*  856:     */        
/*  857: 857 */        if (!(parent instanceof XMLFilter)) break;
/*  858: 858 */        root = (XMLFilter)parent;
/*  859:     */      }
/*  860:     */      
/*  864: 864 */      root.setParent(reader);
/*  865:     */      
/*  866: 866 */      return filter;
/*  867:     */    }
/*  868:     */    
/*  869: 869 */    return reader;
/*  870:     */  }
/*  871:     */  
/*  872:     */  protected DispatchHandler getDispatchHandler() {
/*  873: 873 */    if (this.dispatchHandler == null) {
/*  874: 874 */      this.dispatchHandler = new DispatchHandler();
/*  875:     */    }
/*  876:     */    
/*  877: 877 */    return this.dispatchHandler;
/*  878:     */  }
/*  879:     */  
/*  880:     */  protected void setDispatchHandler(DispatchHandler dispatchHandler) {
/*  881: 881 */    this.dispatchHandler = dispatchHandler;
/*  882:     */  }
/*  883:     */  
/*  891:     */  protected XMLReader createXMLReader()
/*  892:     */    throws SAXException
/*  893:     */  {
/*  894: 894 */    return SAXHelper.createXMLReader(isValidating());
/*  895:     */  }
/*  896:     */  
/*  908:     */  protected void configureReader(XMLReader reader, DefaultHandler handler)
/*  909:     */    throws DocumentException
/*  910:     */  {
/*  911: 911 */    SAXHelper.setParserProperty(reader, "http://xml.org/sax/handlers/LexicalHandler", handler);
/*  912:     */    
/*  914: 914 */    SAXHelper.setParserProperty(reader, "http://xml.org/sax/properties/lexical-handler", handler);
/*  915:     */    
/*  917: 917 */    if ((this.includeInternalDTDDeclarations) || (this.includeExternalDTDDeclarations)) {
/*  918: 918 */      SAXHelper.setParserProperty(reader, "http://xml.org/sax/properties/declaration-handler", handler);
/*  919:     */    }
/*  920:     */    
/*  922: 922 */    SAXHelper.setParserFeature(reader, "http://xml.org/sax/features/namespaces", true);
/*  923:     */    
/*  924: 924 */    SAXHelper.setParserFeature(reader, "http://xml.org/sax/features/namespace-prefixes", false);
/*  925:     */    
/*  927: 927 */    SAXHelper.setParserFeature(reader, "http://xml.org/sax/features/string-interning", isStringInternEnabled());
/*  928:     */    
/*  939: 939 */    SAXHelper.setParserFeature(reader, "http://xml.org/sax/features/use-locator2", true);
/*  940:     */    
/*  942:     */    try
/*  943:     */    {
/*  944: 944 */      reader.setFeature("http://xml.org/sax/features/validation", isValidating());
/*  945:     */      
/*  947: 947 */      if (this.errorHandler != null) {
/*  948: 948 */        reader.setErrorHandler(this.errorHandler);
/*  949:     */      } else {
/*  950: 950 */        reader.setErrorHandler(handler);
/*  951:     */      }
/*  952:     */    } catch (Exception e) {
/*  953: 953 */      if (isValidating()) {
/*  954: 954 */        throw new DocumentException("Validation not supported for XMLReader: " + reader, e);
/*  955:     */      }
/*  956:     */    }
/*  957:     */  }
/*  958:     */  
/*  967:     */  protected SAXContentHandler createContentHandler(XMLReader reader)
/*  968:     */  {
/*  969: 969 */    return new SAXContentHandler(getDocumentFactory(), this.dispatchHandler);
/*  970:     */  }
/*  971:     */  
/*  972:     */  protected EntityResolver createDefaultEntityResolver(String systemId) {
/*  973: 973 */    String prefix = null;
/*  974:     */    
/*  975: 975 */    if ((systemId != null) && (systemId.length() > 0)) {
/*  976: 976 */      int idx = systemId.lastIndexOf('/');
/*  977:     */      
/*  978: 978 */      if (idx > 0) {
/*  979: 979 */        prefix = systemId.substring(0, idx + 1);
/*  980:     */      }
/*  981:     */    }
/*  982:     */    
/*  983: 983 */    return new SAXEntityResolver(prefix);
/*  984:     */  }
/*  985:     */  
/*  986:     */  protected static class SAXEntityResolver implements EntityResolver, Serializable
/*  987:     */  {
/*  988:     */    protected String uriPrefix;
/*  989:     */    
/*  990:     */    public SAXEntityResolver(String uriPrefix) {
/*  991: 991 */      this.uriPrefix = uriPrefix;
/*  992:     */    }
/*  993:     */    
/*  994:     */    public InputSource resolveEntity(String publicId, String systemId)
/*  995:     */    {
/*  996: 996 */      if ((systemId != null) && (systemId.length() > 0) && 
/*  997: 997 */        (this.uriPrefix != null) && (systemId.indexOf(':') <= 0)) {
/*  998: 998 */        systemId = this.uriPrefix + systemId;
/*  999:     */      }
/* 1000:     */      
/* 1002:1002 */      return new InputSource(systemId);
/* 1003:     */    }
/* 1004:     */  }
/* 1005:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.io.SAXReader
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */