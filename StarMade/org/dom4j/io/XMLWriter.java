/*    1:     */package org.dom4j.io;
/*    2:     */
/*    3:     */import java.io.BufferedWriter;
/*    4:     */import java.io.IOException;
/*    5:     */import java.io.OutputStream;
/*    6:     */import java.io.OutputStreamWriter;
/*    7:     */import java.io.UnsupportedEncodingException;
/*    8:     */import java.io.Writer;
/*    9:     */import java.util.HashMap;
/*   10:     */import java.util.Iterator;
/*   11:     */import java.util.List;
/*   12:     */import java.util.Map;
/*   13:     */import java.util.Map.Entry;
/*   14:     */import java.util.Set;
/*   15:     */import java.util.StringTokenizer;
/*   16:     */import org.dom4j.Attribute;
/*   17:     */import org.dom4j.CDATA;
/*   18:     */import org.dom4j.Comment;
/*   19:     */import org.dom4j.Document;
/*   20:     */import org.dom4j.DocumentType;
/*   21:     */import org.dom4j.Element;
/*   22:     */import org.dom4j.Entity;
/*   23:     */import org.dom4j.Namespace;
/*   24:     */import org.dom4j.Node;
/*   25:     */import org.dom4j.ProcessingInstruction;
/*   26:     */import org.dom4j.Text;
/*   27:     */import org.dom4j.tree.NamespaceStack;
/*   28:     */import org.xml.sax.Attributes;
/*   29:     */import org.xml.sax.InputSource;
/*   30:     */import org.xml.sax.Locator;
/*   31:     */import org.xml.sax.SAXException;
/*   32:     */import org.xml.sax.SAXNotRecognizedException;
/*   33:     */import org.xml.sax.SAXNotSupportedException;
/*   34:     */import org.xml.sax.XMLReader;
/*   35:     */import org.xml.sax.ext.LexicalHandler;
/*   36:     */import org.xml.sax.helpers.XMLFilterImpl;
/*   37:     */
/*   69:     */public class XMLWriter
/*   70:     */  extends XMLFilterImpl
/*   71:     */  implements LexicalHandler
/*   72:     */{
/*   73:     */  private static final String PAD_TEXT = " ";
/*   74:  74 */  protected static final String[] LEXICAL_HANDLER_NAMES = { "http://xml.org/sax/properties/lexical-handler", "http://xml.org/sax/handlers/LexicalHandler" };
/*   75:     */  
/*   78:  78 */  protected static final OutputFormat DEFAULT_FORMAT = new OutputFormat();
/*   79:     */  
/*   81:  81 */  private boolean resolveEntityRefs = true;
/*   82:     */  
/*   87:     */  protected int lastOutputNodeType;
/*   88:     */  
/*   93:  93 */  private boolean lastElementClosed = false;
/*   94:     */  
/*   96:  96 */  protected boolean preserve = false;
/*   97:     */  
/*   99:     */  protected Writer writer;
/*  100:     */  
/*  102: 102 */  private NamespaceStack namespaceStack = new NamespaceStack();
/*  103:     */  
/*  105:     */  private OutputFormat format;
/*  106:     */  
/*  108: 108 */  private boolean escapeText = true;
/*  109:     */  
/*  114: 114 */  private int indentLevel = 0;
/*  115:     */  
/*  117: 117 */  private StringBuffer buffer = new StringBuffer();
/*  118:     */  
/*  122: 122 */  private boolean charsAdded = false;
/*  123:     */  
/*  126:     */  private char lastChar;
/*  127:     */  
/*  130:     */  private boolean autoFlush;
/*  131:     */  
/*  134:     */  private LexicalHandler lexicalHandler;
/*  135:     */  
/*  137:     */  private boolean showCommentsInDTDs;
/*  138:     */  
/*  140:     */  private boolean inDTD;
/*  141:     */  
/*  143:     */  private Map namespacesMap;
/*  144:     */  
/*  146:     */  private int maximumAllowedCharacter;
/*  147:     */  
/*  150:     */  public XMLWriter(Writer writer)
/*  151:     */  {
/*  152: 152 */    this(writer, DEFAULT_FORMAT);
/*  153:     */  }
/*  154:     */  
/*  155:     */  public XMLWriter(Writer writer, OutputFormat format) {
/*  156: 156 */    this.writer = writer;
/*  157: 157 */    this.format = format;
/*  158: 158 */    this.namespaceStack.push(Namespace.NO_NAMESPACE);
/*  159:     */  }
/*  160:     */  
/*  161:     */  public XMLWriter() {
/*  162: 162 */    this.format = DEFAULT_FORMAT;
/*  163: 163 */    this.writer = new BufferedWriter(new OutputStreamWriter(System.out));
/*  164: 164 */    this.autoFlush = true;
/*  165: 165 */    this.namespaceStack.push(Namespace.NO_NAMESPACE);
/*  166:     */  }
/*  167:     */  
/*  168:     */  public XMLWriter(OutputStream out) throws UnsupportedEncodingException {
/*  169: 169 */    this.format = DEFAULT_FORMAT;
/*  170: 170 */    this.writer = createWriter(out, this.format.getEncoding());
/*  171: 171 */    this.autoFlush = true;
/*  172: 172 */    this.namespaceStack.push(Namespace.NO_NAMESPACE);
/*  173:     */  }
/*  174:     */  
/*  175:     */  public XMLWriter(OutputStream out, OutputFormat format) throws UnsupportedEncodingException
/*  176:     */  {
/*  177: 177 */    this.format = format;
/*  178: 178 */    this.writer = createWriter(out, format.getEncoding());
/*  179: 179 */    this.autoFlush = true;
/*  180: 180 */    this.namespaceStack.push(Namespace.NO_NAMESPACE);
/*  181:     */  }
/*  182:     */  
/*  183:     */  public XMLWriter(OutputFormat format) throws UnsupportedEncodingException {
/*  184: 184 */    this.format = format;
/*  185: 185 */    this.writer = createWriter(System.out, format.getEncoding());
/*  186: 186 */    this.autoFlush = true;
/*  187: 187 */    this.namespaceStack.push(Namespace.NO_NAMESPACE);
/*  188:     */  }
/*  189:     */  
/*  190:     */  public void setWriter(Writer writer) {
/*  191: 191 */    this.writer = writer;
/*  192: 192 */    this.autoFlush = false;
/*  193:     */  }
/*  194:     */  
/*  195:     */  public void setOutputStream(OutputStream out) throws UnsupportedEncodingException
/*  196:     */  {
/*  197: 197 */    this.writer = createWriter(out, this.format.getEncoding());
/*  198: 198 */    this.autoFlush = true;
/*  199:     */  }
/*  200:     */  
/*  207:     */  public boolean isEscapeText()
/*  208:     */  {
/*  209: 209 */    return this.escapeText;
/*  210:     */  }
/*  211:     */  
/*  219:     */  public void setEscapeText(boolean escapeText)
/*  220:     */  {
/*  221: 221 */    this.escapeText = escapeText;
/*  222:     */  }
/*  223:     */  
/*  231:     */  public void setIndentLevel(int indentLevel)
/*  232:     */  {
/*  233: 233 */    this.indentLevel = indentLevel;
/*  234:     */  }
/*  235:     */  
/*  242:     */  public int getMaximumAllowedCharacter()
/*  243:     */  {
/*  244: 244 */    if (this.maximumAllowedCharacter == 0) {
/*  245: 245 */      this.maximumAllowedCharacter = defaultMaximumAllowedCharacter();
/*  246:     */    }
/*  247:     */    
/*  248: 248 */    return this.maximumAllowedCharacter;
/*  249:     */  }
/*  250:     */  
/*  260:     */  public void setMaximumAllowedCharacter(int maximumAllowedCharacter)
/*  261:     */  {
/*  262: 262 */    this.maximumAllowedCharacter = maximumAllowedCharacter;
/*  263:     */  }
/*  264:     */  
/*  269:     */  public void flush()
/*  270:     */    throws IOException
/*  271:     */  {
/*  272: 272 */    this.writer.flush();
/*  273:     */  }
/*  274:     */  
/*  279:     */  public void close()
/*  280:     */    throws IOException
/*  281:     */  {
/*  282: 282 */    this.writer.close();
/*  283:     */  }
/*  284:     */  
/*  289:     */  public void println()
/*  290:     */    throws IOException
/*  291:     */  {
/*  292: 292 */    this.writer.write(this.format.getLineSeparator());
/*  293:     */  }
/*  294:     */  
/*  302:     */  public void write(Attribute attribute)
/*  303:     */    throws IOException
/*  304:     */  {
/*  305: 305 */    writeAttribute(attribute);
/*  306:     */    
/*  307: 307 */    if (this.autoFlush) {
/*  308: 308 */      flush();
/*  309:     */    }
/*  310:     */  }
/*  311:     */  
/*  332:     */  public void write(Document doc)
/*  333:     */    throws IOException
/*  334:     */  {
/*  335: 335 */    writeDeclaration();
/*  336:     */    
/*  337: 337 */    if (doc.getDocType() != null) {
/*  338: 338 */      indent();
/*  339: 339 */      writeDocType(doc.getDocType());
/*  340:     */    }
/*  341:     */    
/*  342: 342 */    int i = 0; for (int size = doc.nodeCount(); i < size; i++) {
/*  343: 343 */      Node node = doc.node(i);
/*  344: 344 */      writeNode(node);
/*  345:     */    }
/*  346:     */    
/*  347: 347 */    writePrintln();
/*  348:     */    
/*  349: 349 */    if (this.autoFlush) {
/*  350: 350 */      flush();
/*  351:     */    }
/*  352:     */  }
/*  353:     */  
/*  366:     */  public void write(Element element)
/*  367:     */    throws IOException
/*  368:     */  {
/*  369: 369 */    writeElement(element);
/*  370:     */    
/*  371: 371 */    if (this.autoFlush) {
/*  372: 372 */      flush();
/*  373:     */    }
/*  374:     */  }
/*  375:     */  
/*  383:     */  public void write(CDATA cdata)
/*  384:     */    throws IOException
/*  385:     */  {
/*  386: 386 */    writeCDATA(cdata.getText());
/*  387:     */    
/*  388: 388 */    if (this.autoFlush) {
/*  389: 389 */      flush();
/*  390:     */    }
/*  391:     */  }
/*  392:     */  
/*  400:     */  public void write(Comment comment)
/*  401:     */    throws IOException
/*  402:     */  {
/*  403: 403 */    writeComment(comment.getText());
/*  404:     */    
/*  405: 405 */    if (this.autoFlush) {
/*  406: 406 */      flush();
/*  407:     */    }
/*  408:     */  }
/*  409:     */  
/*  417:     */  public void write(DocumentType docType)
/*  418:     */    throws IOException
/*  419:     */  {
/*  420: 420 */    writeDocType(docType);
/*  421:     */    
/*  422: 422 */    if (this.autoFlush) {
/*  423: 423 */      flush();
/*  424:     */    }
/*  425:     */  }
/*  426:     */  
/*  434:     */  public void write(Entity entity)
/*  435:     */    throws IOException
/*  436:     */  {
/*  437: 437 */    writeEntity(entity);
/*  438:     */    
/*  439: 439 */    if (this.autoFlush) {
/*  440: 440 */      flush();
/*  441:     */    }
/*  442:     */  }
/*  443:     */  
/*  451:     */  public void write(Namespace namespace)
/*  452:     */    throws IOException
/*  453:     */  {
/*  454: 454 */    writeNamespace(namespace);
/*  455:     */    
/*  456: 456 */    if (this.autoFlush) {
/*  457: 457 */      flush();
/*  458:     */    }
/*  459:     */  }
/*  460:     */  
/*  469:     */  public void write(ProcessingInstruction processingInstruction)
/*  470:     */    throws IOException
/*  471:     */  {
/*  472: 472 */    writeProcessingInstruction(processingInstruction);
/*  473:     */    
/*  474: 474 */    if (this.autoFlush) {
/*  475: 475 */      flush();
/*  476:     */    }
/*  477:     */  }
/*  478:     */  
/*  489:     */  public void write(String text)
/*  490:     */    throws IOException
/*  491:     */  {
/*  492: 492 */    writeString(text);
/*  493:     */    
/*  494: 494 */    if (this.autoFlush) {
/*  495: 495 */      flush();
/*  496:     */    }
/*  497:     */  }
/*  498:     */  
/*  506:     */  public void write(Text text)
/*  507:     */    throws IOException
/*  508:     */  {
/*  509: 509 */    writeString(text.getText());
/*  510:     */    
/*  511: 511 */    if (this.autoFlush) {
/*  512: 512 */      flush();
/*  513:     */    }
/*  514:     */  }
/*  515:     */  
/*  523:     */  public void write(Node node)
/*  524:     */    throws IOException
/*  525:     */  {
/*  526: 526 */    writeNode(node);
/*  527:     */    
/*  528: 528 */    if (this.autoFlush) {
/*  529: 529 */      flush();
/*  530:     */    }
/*  531:     */  }
/*  532:     */  
/*  541:     */  public void write(Object object)
/*  542:     */    throws IOException
/*  543:     */  {
/*  544: 544 */    if ((object instanceof Node)) {
/*  545: 545 */      write((Node)object);
/*  546: 546 */    } else if ((object instanceof String)) {
/*  547: 547 */      write((String)object);
/*  548: 548 */    } else if ((object instanceof List)) {
/*  549: 549 */      List list = (List)object;
/*  550:     */      
/*  551: 551 */      int i = 0; for (int size = list.size(); i < size; i++) {
/*  552: 552 */        write(list.get(i));
/*  553:     */      }
/*  554: 554 */    } else if (object != null) {
/*  555: 555 */      throw new IOException("Invalid object: " + object);
/*  556:     */    }
/*  557:     */  }
/*  558:     */  
/*  569:     */  public void writeOpen(Element element)
/*  570:     */    throws IOException
/*  571:     */  {
/*  572: 572 */    this.writer.write("<");
/*  573: 573 */    this.writer.write(element.getQualifiedName());
/*  574: 574 */    writeAttributes(element);
/*  575: 575 */    this.writer.write(">");
/*  576:     */  }
/*  577:     */  
/*  587:     */  public void writeClose(Element element)
/*  588:     */    throws IOException
/*  589:     */  {
/*  590: 590 */    writeClose(element.getQualifiedName());
/*  591:     */  }
/*  592:     */  
/*  593:     */  public void parse(InputSource source)
/*  594:     */    throws IOException, SAXException
/*  595:     */  {
/*  596: 596 */    installLexicalHandler();
/*  597: 597 */    super.parse(source);
/*  598:     */  }
/*  599:     */  
/*  600:     */  public void setProperty(String name, Object value) throws SAXNotRecognizedException, SAXNotSupportedException
/*  601:     */  {
/*  602: 602 */    for (int i = 0; i < LEXICAL_HANDLER_NAMES.length; i++) {
/*  603: 603 */      if (LEXICAL_HANDLER_NAMES[i].equals(name)) {
/*  604: 604 */        setLexicalHandler((LexicalHandler)value);
/*  605:     */        
/*  606: 606 */        return;
/*  607:     */      }
/*  608:     */    }
/*  609:     */    
/*  610: 610 */    super.setProperty(name, value);
/*  611:     */  }
/*  612:     */  
/*  613:     */  public Object getProperty(String name) throws SAXNotRecognizedException, SAXNotSupportedException
/*  614:     */  {
/*  615: 615 */    for (int i = 0; i < LEXICAL_HANDLER_NAMES.length; i++) {
/*  616: 616 */      if (LEXICAL_HANDLER_NAMES[i].equals(name)) {
/*  617: 617 */        return getLexicalHandler();
/*  618:     */      }
/*  619:     */    }
/*  620:     */    
/*  621: 621 */    return super.getProperty(name);
/*  622:     */  }
/*  623:     */  
/*  624:     */  public void setLexicalHandler(LexicalHandler handler) {
/*  625: 625 */    if (handler == null) {
/*  626: 626 */      throw new NullPointerException("Null lexical handler");
/*  627:     */    }
/*  628: 628 */    this.lexicalHandler = handler;
/*  629:     */  }
/*  630:     */  
/*  631:     */  public LexicalHandler getLexicalHandler()
/*  632:     */  {
/*  633: 633 */    return this.lexicalHandler;
/*  634:     */  }
/*  635:     */  
/*  637:     */  public void setDocumentLocator(Locator locator)
/*  638:     */  {
/*  639: 639 */    super.setDocumentLocator(locator);
/*  640:     */  }
/*  641:     */  
/*  642:     */  public void startDocument() throws SAXException {
/*  643:     */    try {
/*  644: 644 */      writeDeclaration();
/*  645: 645 */      super.startDocument();
/*  646:     */    } catch (IOException e) {
/*  647: 647 */      handleException(e);
/*  648:     */    }
/*  649:     */  }
/*  650:     */  
/*  651:     */  public void endDocument() throws SAXException {
/*  652: 652 */    super.endDocument();
/*  653:     */    
/*  654: 654 */    if (this.autoFlush) {
/*  655:     */      try {
/*  656: 656 */        flush();
/*  657:     */      }
/*  658:     */      catch (IOException e) {}
/*  659:     */    }
/*  660:     */  }
/*  661:     */  
/*  662:     */  public void startPrefixMapping(String prefix, String uri) throws SAXException
/*  663:     */  {
/*  664: 664 */    if (this.namespacesMap == null) {
/*  665: 665 */      this.namespacesMap = new HashMap();
/*  666:     */    }
/*  667:     */    
/*  668: 668 */    this.namespacesMap.put(prefix, uri);
/*  669: 669 */    super.startPrefixMapping(prefix, uri);
/*  670:     */  }
/*  671:     */  
/*  672:     */  public void endPrefixMapping(String prefix) throws SAXException {
/*  673: 673 */    super.endPrefixMapping(prefix);
/*  674:     */  }
/*  675:     */  
/*  676:     */  public void startElement(String namespaceURI, String localName, String qName, Attributes attributes) throws SAXException
/*  677:     */  {
/*  678:     */    try {
/*  679: 679 */      this.charsAdded = false;
/*  680:     */      
/*  681: 681 */      writePrintln();
/*  682: 682 */      indent();
/*  683: 683 */      this.writer.write("<");
/*  684: 684 */      this.writer.write(qName);
/*  685: 685 */      writeNamespaces();
/*  686: 686 */      writeAttributes(attributes);
/*  687: 687 */      this.writer.write(">");
/*  688: 688 */      this.indentLevel += 1;
/*  689: 689 */      this.lastOutputNodeType = 1;
/*  690: 690 */      this.lastElementClosed = false;
/*  691:     */      
/*  692: 692 */      super.startElement(namespaceURI, localName, qName, attributes);
/*  693:     */    } catch (IOException e) {
/*  694: 694 */      handleException(e);
/*  695:     */    }
/*  696:     */  }
/*  697:     */  
/*  698:     */  public void endElement(String namespaceURI, String localName, String qName) throws SAXException
/*  699:     */  {
/*  700:     */    try {
/*  701: 701 */      this.charsAdded = false;
/*  702: 702 */      this.indentLevel -= 1;
/*  703:     */      
/*  704: 704 */      if (this.lastElementClosed) {
/*  705: 705 */        writePrintln();
/*  706: 706 */        indent();
/*  707:     */      }
/*  708:     */      
/*  711: 711 */      boolean hadContent = true;
/*  712:     */      
/*  713: 713 */      if (hadContent) {
/*  714: 714 */        writeClose(qName);
/*  715:     */      } else {
/*  716: 716 */        writeEmptyElementClose(qName);
/*  717:     */      }
/*  718:     */      
/*  719: 719 */      this.lastOutputNodeType = 1;
/*  720: 720 */      this.lastElementClosed = true;
/*  721:     */      
/*  722: 722 */      super.endElement(namespaceURI, localName, qName);
/*  723:     */    } catch (IOException e) {
/*  724: 724 */      handleException(e);
/*  725:     */    }
/*  726:     */  }
/*  727:     */  
/*  728:     */  public void characters(char[] ch, int start, int length) throws SAXException
/*  729:     */  {
/*  730: 730 */    if ((ch == null) || (ch.length == 0) || (length <= 0)) {
/*  731: 731 */      return;
/*  732:     */    }
/*  733:     */    
/*  739:     */    try
/*  740:     */    {
/*  741: 741 */      String string = String.valueOf(ch, start, length);
/*  742:     */      
/*  743: 743 */      if (this.escapeText) {
/*  744: 744 */        string = escapeElementEntities(string);
/*  745:     */      }
/*  746:     */      
/*  747: 747 */      if (this.format.isTrimText()) {
/*  748: 748 */        if ((this.lastOutputNodeType == 3) && (!this.charsAdded)) {
/*  749: 749 */          this.writer.write(32);
/*  750: 750 */        } else if ((this.charsAdded) && (Character.isWhitespace(this.lastChar))) {
/*  751: 751 */          this.writer.write(32);
/*  752: 752 */        } else if ((this.lastOutputNodeType == 1) && (this.format.isPadText()) && (this.lastElementClosed) && (Character.isWhitespace(ch[0])))
/*  753:     */        {
/*  755: 755 */          this.writer.write(" ");
/*  756:     */        }
/*  757:     */        
/*  758: 758 */        String delim = "";
/*  759: 759 */        StringTokenizer tokens = new StringTokenizer(string);
/*  760:     */        
/*  761: 761 */        while (tokens.hasMoreTokens()) {
/*  762: 762 */          this.writer.write(delim);
/*  763: 763 */          this.writer.write(tokens.nextToken());
/*  764: 764 */          delim = " ";
/*  765:     */        }
/*  766:     */      } else {
/*  767: 767 */        this.writer.write(string);
/*  768:     */      }
/*  769:     */      
/*  770: 770 */      this.charsAdded = true;
/*  771: 771 */      this.lastChar = ch[(start + length - 1)];
/*  772: 772 */      this.lastOutputNodeType = 3;
/*  773:     */      
/*  774: 774 */      super.characters(ch, start, length);
/*  775:     */    } catch (IOException e) {
/*  776: 776 */      handleException(e);
/*  777:     */    }
/*  778:     */  }
/*  779:     */  
/*  780:     */  public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException
/*  781:     */  {
/*  782: 782 */    super.ignorableWhitespace(ch, start, length);
/*  783:     */  }
/*  784:     */  
/*  785:     */  public void processingInstruction(String target, String data) throws SAXException
/*  786:     */  {
/*  787:     */    try {
/*  788: 788 */      indent();
/*  789: 789 */      this.writer.write("<?");
/*  790: 790 */      this.writer.write(target);
/*  791: 791 */      this.writer.write(" ");
/*  792: 792 */      this.writer.write(data);
/*  793: 793 */      this.writer.write("?>");
/*  794: 794 */      writePrintln();
/*  795: 795 */      this.lastOutputNodeType = 7;
/*  796:     */      
/*  797: 797 */      super.processingInstruction(target, data);
/*  798:     */    } catch (IOException e) {
/*  799: 799 */      handleException(e);
/*  800:     */    }
/*  801:     */  }
/*  802:     */  
/*  804:     */  public void notationDecl(String name, String publicID, String systemID)
/*  805:     */    throws SAXException
/*  806:     */  {
/*  807: 807 */    super.notationDecl(name, publicID, systemID);
/*  808:     */  }
/*  809:     */  
/*  810:     */  public void unparsedEntityDecl(String name, String publicID, String systemID, String notationName) throws SAXException
/*  811:     */  {
/*  812: 812 */    super.unparsedEntityDecl(name, publicID, systemID, notationName);
/*  813:     */  }
/*  814:     */  
/*  816:     */  public void startDTD(String name, String publicID, String systemID)
/*  817:     */    throws SAXException
/*  818:     */  {
/*  819: 819 */    this.inDTD = true;
/*  820:     */    try
/*  821:     */    {
/*  822: 822 */      writeDocType(name, publicID, systemID);
/*  823:     */    } catch (IOException e) {
/*  824: 824 */      handleException(e);
/*  825:     */    }
/*  826:     */    
/*  827: 827 */    if (this.lexicalHandler != null) {
/*  828: 828 */      this.lexicalHandler.startDTD(name, publicID, systemID);
/*  829:     */    }
/*  830:     */  }
/*  831:     */  
/*  832:     */  public void endDTD() throws SAXException {
/*  833: 833 */    this.inDTD = false;
/*  834:     */    
/*  835: 835 */    if (this.lexicalHandler != null) {
/*  836: 836 */      this.lexicalHandler.endDTD();
/*  837:     */    }
/*  838:     */  }
/*  839:     */  
/*  840:     */  public void startCDATA() throws SAXException {
/*  841:     */    try {
/*  842: 842 */      this.writer.write("<![CDATA[");
/*  843:     */    } catch (IOException e) {
/*  844: 844 */      handleException(e);
/*  845:     */    }
/*  846:     */    
/*  847: 847 */    if (this.lexicalHandler != null) {
/*  848: 848 */      this.lexicalHandler.startCDATA();
/*  849:     */    }
/*  850:     */  }
/*  851:     */  
/*  852:     */  public void endCDATA() throws SAXException {
/*  853:     */    try {
/*  854: 854 */      this.writer.write("]]>");
/*  855:     */    } catch (IOException e) {
/*  856: 856 */      handleException(e);
/*  857:     */    }
/*  858:     */    
/*  859: 859 */    if (this.lexicalHandler != null) {
/*  860: 860 */      this.lexicalHandler.endCDATA();
/*  861:     */    }
/*  862:     */  }
/*  863:     */  
/*  864:     */  public void startEntity(String name) throws SAXException {
/*  865:     */    try {
/*  866: 866 */      writeEntityRef(name);
/*  867:     */    } catch (IOException e) {
/*  868: 868 */      handleException(e);
/*  869:     */    }
/*  870:     */    
/*  871: 871 */    if (this.lexicalHandler != null) {
/*  872: 872 */      this.lexicalHandler.startEntity(name);
/*  873:     */    }
/*  874:     */  }
/*  875:     */  
/*  876:     */  public void endEntity(String name) throws SAXException {
/*  877: 877 */    if (this.lexicalHandler != null) {
/*  878: 878 */      this.lexicalHandler.endEntity(name);
/*  879:     */    }
/*  880:     */  }
/*  881:     */  
/*  882:     */  public void comment(char[] ch, int start, int length) throws SAXException {
/*  883: 883 */    if ((this.showCommentsInDTDs) || (!this.inDTD)) {
/*  884:     */      try {
/*  885: 885 */        this.charsAdded = false;
/*  886: 886 */        writeComment(new String(ch, start, length));
/*  887:     */      } catch (IOException e) {
/*  888: 888 */        handleException(e);
/*  889:     */      }
/*  890:     */    }
/*  891:     */    
/*  892: 892 */    if (this.lexicalHandler != null) {
/*  893: 893 */      this.lexicalHandler.comment(ch, start, length);
/*  894:     */    }
/*  895:     */  }
/*  896:     */  
/*  897:     */  protected void writeElement(Element element)
/*  898:     */    throws IOException
/*  899:     */  {
/*  900: 900 */    int size = element.nodeCount();
/*  901: 901 */    String qualifiedName = element.getQualifiedName();
/*  902:     */    
/*  903: 903 */    writePrintln();
/*  904: 904 */    indent();
/*  905:     */    
/*  906: 906 */    this.writer.write("<");
/*  907: 907 */    this.writer.write(qualifiedName);
/*  908:     */    
/*  909: 909 */    int previouslyDeclaredNamespaces = this.namespaceStack.size();
/*  910: 910 */    Namespace ns = element.getNamespace();
/*  911:     */    
/*  912: 912 */    if (isNamespaceDeclaration(ns)) {
/*  913: 913 */      this.namespaceStack.push(ns);
/*  914: 914 */      writeNamespace(ns);
/*  915:     */    }
/*  916:     */    
/*  918: 918 */    boolean textOnly = true;
/*  919:     */    
/*  920: 920 */    for (int i = 0; i < size; i++) {
/*  921: 921 */      Node node = element.node(i);
/*  922:     */      
/*  923: 923 */      if ((node instanceof Namespace)) {
/*  924: 924 */        Namespace additional = (Namespace)node;
/*  925:     */        
/*  926: 926 */        if (isNamespaceDeclaration(additional)) {
/*  927: 927 */          this.namespaceStack.push(additional);
/*  928: 928 */          writeNamespace(additional);
/*  929:     */        }
/*  930: 930 */      } else if ((node instanceof Element)) {
/*  931: 931 */        textOnly = false;
/*  932: 932 */      } else if ((node instanceof Comment)) {
/*  933: 933 */        textOnly = false;
/*  934:     */      }
/*  935:     */    }
/*  936:     */    
/*  937: 937 */    writeAttributes(element);
/*  938:     */    
/*  939: 939 */    this.lastOutputNodeType = 1;
/*  940:     */    
/*  941: 941 */    if (size <= 0) {
/*  942: 942 */      writeEmptyElementClose(qualifiedName);
/*  943:     */    } else {
/*  944: 944 */      this.writer.write(">");
/*  945:     */      
/*  946: 946 */      if (textOnly)
/*  947:     */      {
/*  949: 949 */        writeElementContent(element);
/*  950:     */      }
/*  951:     */      else {
/*  952: 952 */        this.indentLevel += 1;
/*  953:     */        
/*  954: 954 */        writeElementContent(element);
/*  955:     */        
/*  956: 956 */        this.indentLevel -= 1;
/*  957:     */        
/*  958: 958 */        writePrintln();
/*  959: 959 */        indent();
/*  960:     */      }
/*  961:     */      
/*  962: 962 */      this.writer.write("</");
/*  963: 963 */      this.writer.write(qualifiedName);
/*  964: 964 */      this.writer.write(">");
/*  965:     */    }
/*  966:     */    
/*  968: 968 */    while (this.namespaceStack.size() > previouslyDeclaredNamespaces) {
/*  969: 969 */      this.namespaceStack.pop();
/*  970:     */    }
/*  971:     */    
/*  972: 972 */    this.lastOutputNodeType = 1;
/*  973:     */  }
/*  974:     */  
/*  983:     */  protected final boolean isElementSpacePreserved(Element element)
/*  984:     */  {
/*  985: 985 */    Attribute attr = element.attribute("space");
/*  986: 986 */    boolean preserveFound = this.preserve;
/*  987:     */    
/*  988: 988 */    if (attr != null) {
/*  989: 989 */      if (("xml".equals(attr.getNamespacePrefix())) && ("preserve".equals(attr.getText())))
/*  990:     */      {
/*  991: 991 */        preserveFound = true;
/*  992:     */      } else {
/*  993: 993 */        preserveFound = false;
/*  994:     */      }
/*  995:     */    }
/*  996:     */    
/*  997: 997 */    return preserveFound;
/*  998:     */  }
/*  999:     */  
/* 1011:     */  protected void writeElementContent(Element element)
/* 1012:     */    throws IOException
/* 1013:     */  {
/* 1014:1014 */    boolean trim = this.format.isTrimText();
/* 1015:1015 */    boolean oldPreserve = this.preserve;
/* 1016:     */    
/* 1017:1017 */    if (trim) {
/* 1018:1018 */      this.preserve = isElementSpacePreserved(element);
/* 1019:1019 */      trim = !this.preserve;
/* 1020:     */    }
/* 1021:     */    
/* 1022:1022 */    if (trim)
/* 1023:     */    {
/* 1025:1025 */      Text lastTextNode = null;
/* 1026:1026 */      StringBuffer buff = null;
/* 1027:1027 */      boolean textOnly = true;
/* 1028:     */      
/* 1029:1029 */      int i = 0; for (int size = element.nodeCount(); i < size; i++) {
/* 1030:1030 */        Node node = element.node(i);
/* 1031:     */        
/* 1032:1032 */        if ((node instanceof Text)) {
/* 1033:1033 */          if (lastTextNode == null) {
/* 1034:1034 */            lastTextNode = (Text)node;
/* 1035:     */          } else {
/* 1036:1036 */            if (buff == null) {
/* 1037:1037 */              buff = new StringBuffer(lastTextNode.getText());
/* 1038:     */            }
/* 1039:     */            
/* 1040:1040 */            buff.append(((Text)node).getText());
/* 1041:     */          }
/* 1042:     */        } else {
/* 1043:1043 */          if ((!textOnly) && (this.format.isPadText()))
/* 1044:     */          {
/* 1046:1046 */            char firstChar = 'a';
/* 1047:1047 */            if (buff != null) {
/* 1048:1048 */              firstChar = buff.charAt(0);
/* 1049:1049 */            } else if (lastTextNode != null) {
/* 1050:1050 */              firstChar = lastTextNode.getText().charAt(0);
/* 1051:     */            }
/* 1052:     */            
/* 1053:1053 */            if (Character.isWhitespace(firstChar)) {
/* 1054:1054 */              this.writer.write(" ");
/* 1055:     */            }
/* 1056:     */          }
/* 1057:     */          
/* 1058:1058 */          if (lastTextNode != null) {
/* 1059:1059 */            if (buff != null) {
/* 1060:1060 */              writeString(buff.toString());
/* 1061:1061 */              buff = null;
/* 1062:     */            } else {
/* 1063:1063 */              writeString(lastTextNode.getText());
/* 1064:     */            }
/* 1065:     */            
/* 1066:1066 */            if (this.format.isPadText())
/* 1067:     */            {
/* 1069:1069 */              char lastTextChar = 'a';
/* 1070:1070 */              if (buff != null) {
/* 1071:1071 */                lastTextChar = buff.charAt(buff.length() - 1);
/* 1072:1072 */              } else if (lastTextNode != null) {
/* 1073:1073 */                String txt = lastTextNode.getText();
/* 1074:1074 */                lastTextChar = txt.charAt(txt.length() - 1);
/* 1075:     */              }
/* 1076:     */              
/* 1077:1077 */              if (Character.isWhitespace(lastTextChar)) {
/* 1078:1078 */                this.writer.write(" ");
/* 1079:     */              }
/* 1080:     */            }
/* 1081:     */            
/* 1082:1082 */            lastTextNode = null;
/* 1083:     */          }
/* 1084:     */          
/* 1085:1085 */          textOnly = false;
/* 1086:1086 */          writeNode(node);
/* 1087:     */        }
/* 1088:     */      }
/* 1089:     */      
/* 1090:1090 */      if (lastTextNode != null) {
/* 1091:1091 */        if ((!textOnly) && (this.format.isPadText()))
/* 1092:     */        {
/* 1094:1094 */          char firstChar = 'a';
/* 1095:1095 */          if (buff != null) {
/* 1096:1096 */            firstChar = buff.charAt(0);
/* 1097:     */          } else {
/* 1098:1098 */            firstChar = lastTextNode.getText().charAt(0);
/* 1099:     */          }
/* 1100:     */          
/* 1101:1101 */          if (Character.isWhitespace(firstChar)) {
/* 1102:1102 */            this.writer.write(" ");
/* 1103:     */          }
/* 1104:     */        }
/* 1105:     */        
/* 1106:1106 */        if (buff != null) {
/* 1107:1107 */          writeString(buff.toString());
/* 1108:1108 */          buff = null;
/* 1109:     */        } else {
/* 1110:1110 */          writeString(lastTextNode.getText());
/* 1111:     */        }
/* 1112:     */        
/* 1113:1113 */        lastTextNode = null;
/* 1114:     */      }
/* 1115:     */    } else {
/* 1116:1116 */      Node lastTextNode = null;
/* 1117:     */      
/* 1118:1118 */      int i = 0; for (int size = element.nodeCount(); i < size; i++) {
/* 1119:1119 */        Node node = element.node(i);
/* 1120:     */        
/* 1121:1121 */        if ((node instanceof Text)) {
/* 1122:1122 */          writeNode(node);
/* 1123:1123 */          lastTextNode = node;
/* 1124:     */        } else {
/* 1125:1125 */          if ((lastTextNode != null) && (this.format.isPadText()))
/* 1126:     */          {
/* 1128:1128 */            String txt = lastTextNode.getText();
/* 1129:1129 */            char lastTextChar = txt.charAt(txt.length() - 1);
/* 1130:     */            
/* 1131:1131 */            if (Character.isWhitespace(lastTextChar)) {
/* 1132:1132 */              this.writer.write(" ");
/* 1133:     */            }
/* 1134:     */          }
/* 1135:     */          
/* 1136:1136 */          writeNode(node);
/* 1137:     */          
/* 1142:1142 */          lastTextNode = null;
/* 1143:     */        }
/* 1144:     */      }
/* 1145:     */    }
/* 1146:     */    
/* 1147:1147 */    this.preserve = oldPreserve;
/* 1148:     */  }
/* 1149:     */  
/* 1150:     */  protected void writeCDATA(String text) throws IOException {
/* 1151:1151 */    this.writer.write("<![CDATA[");
/* 1152:     */    
/* 1153:1153 */    if (text != null) {
/* 1154:1154 */      this.writer.write(text);
/* 1155:     */    }
/* 1156:     */    
/* 1157:1157 */    this.writer.write("]]>");
/* 1158:     */    
/* 1159:1159 */    this.lastOutputNodeType = 4;
/* 1160:     */  }
/* 1161:     */  
/* 1162:     */  protected void writeDocType(DocumentType docType) throws IOException {
/* 1163:1163 */    if (docType != null) {
/* 1164:1164 */      docType.write(this.writer);
/* 1165:1165 */      writePrintln();
/* 1166:     */    }
/* 1167:     */  }
/* 1168:     */  
/* 1169:     */  protected void writeNamespace(Namespace namespace) throws IOException {
/* 1170:1170 */    if (namespace != null) {
/* 1171:1171 */      writeNamespace(namespace.getPrefix(), namespace.getURI());
/* 1172:     */    }
/* 1173:     */  }
/* 1174:     */  
/* 1179:     */  protected void writeNamespaces()
/* 1180:     */    throws IOException
/* 1181:     */  {
/* 1182:1182 */    if (this.namespacesMap != null) {
/* 1183:1183 */      Iterator iter = this.namespacesMap.entrySet().iterator();
/* 1184:1184 */      while (iter.hasNext()) {
/* 1185:1185 */        Map.Entry entry = (Map.Entry)iter.next();
/* 1186:1186 */        String prefix = (String)entry.getKey();
/* 1187:1187 */        String uri = (String)entry.getValue();
/* 1188:1188 */        writeNamespace(prefix, uri);
/* 1189:     */      }
/* 1190:     */      
/* 1191:1191 */      this.namespacesMap = null;
/* 1192:     */    }
/* 1193:     */  }
/* 1194:     */  
/* 1204:     */  protected void writeNamespace(String prefix, String uri)
/* 1205:     */    throws IOException
/* 1206:     */  {
/* 1207:1207 */    if ((prefix != null) && (prefix.length() > 0)) {
/* 1208:1208 */      this.writer.write(" xmlns:");
/* 1209:1209 */      this.writer.write(prefix);
/* 1210:1210 */      this.writer.write("=\"");
/* 1211:     */    } else {
/* 1212:1212 */      this.writer.write(" xmlns=\"");
/* 1213:     */    }
/* 1214:     */    
/* 1215:1215 */    this.writer.write(uri);
/* 1216:1216 */    this.writer.write("\"");
/* 1217:     */  }
/* 1218:     */  
/* 1219:     */  protected void writeProcessingInstruction(ProcessingInstruction pi)
/* 1220:     */    throws IOException
/* 1221:     */  {
/* 1222:1222 */    this.writer.write("<?");
/* 1223:1223 */    this.writer.write(pi.getName());
/* 1224:1224 */    this.writer.write(" ");
/* 1225:1225 */    this.writer.write(pi.getText());
/* 1226:1226 */    this.writer.write("?>");
/* 1227:1227 */    writePrintln();
/* 1228:     */    
/* 1229:1229 */    this.lastOutputNodeType = 7;
/* 1230:     */  }
/* 1231:     */  
/* 1232:     */  protected void writeString(String text) throws IOException {
/* 1233:1233 */    if ((text != null) && (text.length() > 0)) {
/* 1234:1234 */      if (this.escapeText) {
/* 1235:1235 */        text = escapeElementEntities(text);
/* 1236:     */      }
/* 1237:     */      
/* 1243:1243 */      if (this.format.isTrimText()) {
/* 1244:1244 */        boolean first = true;
/* 1245:1245 */        StringTokenizer tokenizer = new StringTokenizer(text);
/* 1246:     */        
/* 1247:1247 */        while (tokenizer.hasMoreTokens()) {
/* 1248:1248 */          String token = tokenizer.nextToken();
/* 1249:     */          
/* 1250:1250 */          if (first) {
/* 1251:1251 */            first = false;
/* 1252:     */            
/* 1253:1253 */            if (this.lastOutputNodeType == 3) {
/* 1254:1254 */              this.writer.write(" ");
/* 1255:     */            }
/* 1256:     */          } else {
/* 1257:1257 */            this.writer.write(" ");
/* 1258:     */          }
/* 1259:     */          
/* 1260:1260 */          this.writer.write(token);
/* 1261:1261 */          this.lastOutputNodeType = 3;
/* 1262:1262 */          this.lastChar = token.charAt(token.length() - 1);
/* 1263:     */        }
/* 1264:     */      } else {
/* 1265:1265 */        this.lastOutputNodeType = 3;
/* 1266:1266 */        this.writer.write(text);
/* 1267:1267 */        this.lastChar = text.charAt(text.length() - 1);
/* 1268:     */      }
/* 1269:     */    }
/* 1270:     */  }
/* 1271:     */  
/* 1280:     */  protected void writeNodeText(Node node)
/* 1281:     */    throws IOException
/* 1282:     */  {
/* 1283:1283 */    String text = node.getText();
/* 1284:     */    
/* 1285:1285 */    if ((text != null) && (text.length() > 0)) {
/* 1286:1286 */      if (this.escapeText) {
/* 1287:1287 */        text = escapeElementEntities(text);
/* 1288:     */      }
/* 1289:     */      
/* 1290:1290 */      this.lastOutputNodeType = 3;
/* 1291:1291 */      this.writer.write(text);
/* 1292:1292 */      this.lastChar = text.charAt(text.length() - 1);
/* 1293:     */    }
/* 1294:     */  }
/* 1295:     */  
/* 1296:     */  protected void writeNode(Node node) throws IOException {
/* 1297:1297 */    int nodeType = node.getNodeType();
/* 1298:     */    
/* 1299:1299 */    switch (nodeType) {
/* 1300:     */    case 1: 
/* 1301:1301 */      writeElement((Element)node);
/* 1302:     */      
/* 1303:1303 */      break;
/* 1304:     */    
/* 1305:     */    case 2: 
/* 1306:1306 */      writeAttribute((Attribute)node);
/* 1307:     */      
/* 1308:1308 */      break;
/* 1309:     */    
/* 1310:     */    case 3: 
/* 1311:1311 */      writeNodeText(node);
/* 1312:     */      
/* 1314:1314 */      break;
/* 1315:     */    
/* 1316:     */    case 4: 
/* 1317:1317 */      writeCDATA(node.getText());
/* 1318:     */      
/* 1319:1319 */      break;
/* 1320:     */    
/* 1321:     */    case 5: 
/* 1322:1322 */      writeEntity((Entity)node);
/* 1323:     */      
/* 1324:1324 */      break;
/* 1325:     */    
/* 1326:     */    case 7: 
/* 1327:1327 */      writeProcessingInstruction((ProcessingInstruction)node);
/* 1328:     */      
/* 1329:1329 */      break;
/* 1330:     */    
/* 1331:     */    case 8: 
/* 1332:1332 */      writeComment(node.getText());
/* 1333:     */      
/* 1334:1334 */      break;
/* 1335:     */    
/* 1336:     */    case 9: 
/* 1337:1337 */      write((Document)node);
/* 1338:     */      
/* 1339:1339 */      break;
/* 1340:     */    
/* 1341:     */    case 10: 
/* 1342:1342 */      writeDocType((DocumentType)node);
/* 1343:     */      
/* 1344:1344 */      break;
/* 1345:     */    
/* 1349:     */    case 13: 
/* 1350:1350 */      break;
/* 1351:     */    case 6: case 11: 
/* 1352:     */    case 12: default: 
/* 1353:1353 */      throw new IOException("Invalid node type: " + node);
/* 1354:     */    }
/* 1355:     */  }
/* 1356:     */  
/* 1357:     */  protected void installLexicalHandler() {
/* 1358:1358 */    XMLReader parent = getParent();
/* 1359:     */    
/* 1360:1360 */    if (parent == null) {
/* 1361:1361 */      throw new NullPointerException("No parent for filter");
/* 1362:     */    }
/* 1363:     */    
/* 1365:1365 */    for (int i = 0; i < LEXICAL_HANDLER_NAMES.length; i++) {
/* 1366:     */      try {
/* 1367:1367 */        parent.setProperty(LEXICAL_HANDLER_NAMES[i], this);
/* 1368:     */      }
/* 1369:     */      catch (SAXNotRecognizedException ex) {}catch (SAXNotSupportedException ex) {}
/* 1370:     */    }
/* 1371:     */  }
/* 1372:     */  
/* 1377:     */  protected void writeDocType(String name, String publicID, String systemID)
/* 1378:     */    throws IOException
/* 1379:     */  {
/* 1380:1380 */    boolean hasPublic = false;
/* 1381:     */    
/* 1382:1382 */    this.writer.write("<!DOCTYPE ");
/* 1383:1383 */    this.writer.write(name);
/* 1384:     */    
/* 1385:1385 */    if ((publicID != null) && (!publicID.equals(""))) {
/* 1386:1386 */      this.writer.write(" PUBLIC \"");
/* 1387:1387 */      this.writer.write(publicID);
/* 1388:1388 */      this.writer.write("\"");
/* 1389:1389 */      hasPublic = true;
/* 1390:     */    }
/* 1391:     */    
/* 1392:1392 */    if ((systemID != null) && (!systemID.equals(""))) {
/* 1393:1393 */      if (!hasPublic) {
/* 1394:1394 */        this.writer.write(" SYSTEM");
/* 1395:     */      }
/* 1396:     */      
/* 1397:1397 */      this.writer.write(" \"");
/* 1398:1398 */      this.writer.write(systemID);
/* 1399:1399 */      this.writer.write("\"");
/* 1400:     */    }
/* 1401:     */    
/* 1402:1402 */    this.writer.write(">");
/* 1403:1403 */    writePrintln();
/* 1404:     */  }
/* 1405:     */  
/* 1406:     */  protected void writeEntity(Entity entity) throws IOException {
/* 1407:1407 */    if (!resolveEntityRefs()) {
/* 1408:1408 */      writeEntityRef(entity.getName());
/* 1409:     */    } else {
/* 1410:1410 */      this.writer.write(entity.getText());
/* 1411:     */    }
/* 1412:     */  }
/* 1413:     */  
/* 1414:     */  protected void writeEntityRef(String name) throws IOException {
/* 1415:1415 */    this.writer.write("&");
/* 1416:1416 */    this.writer.write(name);
/* 1417:1417 */    this.writer.write(";");
/* 1418:     */    
/* 1419:1419 */    this.lastOutputNodeType = 5;
/* 1420:     */  }
/* 1421:     */  
/* 1422:     */  protected void writeComment(String text) throws IOException {
/* 1423:1423 */    if (this.format.isNewlines()) {
/* 1424:1424 */      println();
/* 1425:1425 */      indent();
/* 1426:     */    }
/* 1427:     */    
/* 1428:1428 */    this.writer.write("<!--");
/* 1429:1429 */    this.writer.write(text);
/* 1430:1430 */    this.writer.write("-->");
/* 1431:     */    
/* 1432:1432 */    this.lastOutputNodeType = 8;
/* 1433:     */  }
/* 1434:     */  
/* 1446:     */  protected void writeAttributes(Element element)
/* 1447:     */    throws IOException
/* 1448:     */  {
/* 1449:1449 */    int i = 0; for (int size = element.attributeCount(); i < size; i++) {
/* 1450:1450 */      Attribute attribute = element.attribute(i);
/* 1451:1451 */      Namespace ns = attribute.getNamespace();
/* 1452:     */      
/* 1453:1453 */      if ((ns != null) && (ns != Namespace.NO_NAMESPACE) && (ns != Namespace.XML_NAMESPACE))
/* 1454:     */      {
/* 1455:1455 */        String prefix = ns.getPrefix();
/* 1456:1456 */        String uri = this.namespaceStack.getURI(prefix);
/* 1457:     */        
/* 1458:1458 */        if (!ns.getURI().equals(uri)) {
/* 1459:1459 */          writeNamespace(ns);
/* 1460:1460 */          this.namespaceStack.push(ns);
/* 1461:     */        }
/* 1462:     */      }
/* 1463:     */      
/* 1467:1467 */      String attName = attribute.getName();
/* 1468:     */      
/* 1469:1469 */      if (attName.startsWith("xmlns:")) {
/* 1470:1470 */        String prefix = attName.substring(6);
/* 1471:     */        
/* 1472:1472 */        if (this.namespaceStack.getNamespaceForPrefix(prefix) == null) {
/* 1473:1473 */          String uri = attribute.getValue();
/* 1474:1474 */          this.namespaceStack.push(prefix, uri);
/* 1475:1475 */          writeNamespace(prefix, uri);
/* 1476:     */        }
/* 1477:1477 */      } else if (attName.equals("xmlns")) {
/* 1478:1478 */        if (this.namespaceStack.getDefaultNamespace() == null) {
/* 1479:1479 */          String uri = attribute.getValue();
/* 1480:1480 */          this.namespaceStack.push(null, uri);
/* 1481:1481 */          writeNamespace(null, uri);
/* 1482:     */        }
/* 1483:     */      } else {
/* 1484:1484 */        char quote = this.format.getAttributeQuoteCharacter();
/* 1485:1485 */        this.writer.write(" ");
/* 1486:1486 */        this.writer.write(attribute.getQualifiedName());
/* 1487:1487 */        this.writer.write("=");
/* 1488:1488 */        this.writer.write(quote);
/* 1489:1489 */        writeEscapeAttributeEntities(attribute.getValue());
/* 1490:1490 */        this.writer.write(quote);
/* 1491:     */      }
/* 1492:     */    }
/* 1493:     */  }
/* 1494:     */  
/* 1495:     */  protected void writeAttribute(Attribute attribute) throws IOException {
/* 1496:1496 */    this.writer.write(" ");
/* 1497:1497 */    this.writer.write(attribute.getQualifiedName());
/* 1498:1498 */    this.writer.write("=");
/* 1499:     */    
/* 1500:1500 */    char quote = this.format.getAttributeQuoteCharacter();
/* 1501:1501 */    this.writer.write(quote);
/* 1502:     */    
/* 1503:1503 */    writeEscapeAttributeEntities(attribute.getValue());
/* 1504:     */    
/* 1505:1505 */    this.writer.write(quote);
/* 1506:1506 */    this.lastOutputNodeType = 2;
/* 1507:     */  }
/* 1508:     */  
/* 1509:     */  protected void writeAttributes(Attributes attributes) throws IOException {
/* 1510:1510 */    int i = 0; for (int size = attributes.getLength(); i < size; i++) {
/* 1511:1511 */      writeAttribute(attributes, i);
/* 1512:     */    }
/* 1513:     */  }
/* 1514:     */  
/* 1515:     */  protected void writeAttribute(Attributes attributes, int index) throws IOException
/* 1516:     */  {
/* 1517:1517 */    char quote = this.format.getAttributeQuoteCharacter();
/* 1518:1518 */    this.writer.write(" ");
/* 1519:1519 */    this.writer.write(attributes.getQName(index));
/* 1520:1520 */    this.writer.write("=");
/* 1521:1521 */    this.writer.write(quote);
/* 1522:1522 */    writeEscapeAttributeEntities(attributes.getValue(index));
/* 1523:1523 */    this.writer.write(quote);
/* 1524:     */  }
/* 1525:     */  
/* 1526:     */  protected void indent() throws IOException {
/* 1527:1527 */    String indent = this.format.getIndent();
/* 1528:     */    
/* 1529:1529 */    if ((indent != null) && (indent.length() > 0)) {
/* 1530:1530 */      for (int i = 0; i < this.indentLevel; i++) {
/* 1531:1531 */        this.writer.write(indent);
/* 1532:     */      }
/* 1533:     */    }
/* 1534:     */  }
/* 1535:     */  
/* 1542:     */  protected void writePrintln()
/* 1543:     */    throws IOException
/* 1544:     */  {
/* 1545:1545 */    if (this.format.isNewlines()) {
/* 1546:1546 */      String seperator = this.format.getLineSeparator();
/* 1547:1547 */      if (this.lastChar != seperator.charAt(seperator.length() - 1)) {
/* 1548:1548 */        this.writer.write(this.format.getLineSeparator());
/* 1549:     */      }
/* 1550:     */    }
/* 1551:     */  }
/* 1552:     */  
/* 1565:     */  protected Writer createWriter(OutputStream outStream, String encoding)
/* 1566:     */    throws UnsupportedEncodingException
/* 1567:     */  {
/* 1568:1568 */    return new BufferedWriter(new OutputStreamWriter(outStream, encoding));
/* 1569:     */  }
/* 1570:     */  
/* 1578:     */  protected void writeDeclaration()
/* 1579:     */    throws IOException
/* 1580:     */  {
/* 1581:1581 */    String encoding = this.format.getEncoding();
/* 1582:     */    
/* 1584:1584 */    if (!this.format.isSuppressDeclaration())
/* 1585:     */    {
/* 1586:1586 */      if (encoding.equals("UTF8")) {
/* 1587:1587 */        this.writer.write("<?xml version=\"1.0\"");
/* 1588:     */        
/* 1589:1589 */        if (!this.format.isOmitEncoding()) {
/* 1590:1590 */          this.writer.write(" encoding=\"UTF-8\"");
/* 1591:     */        }
/* 1592:     */        
/* 1593:1593 */        this.writer.write("?>");
/* 1594:     */      } else {
/* 1595:1595 */        this.writer.write("<?xml version=\"1.0\"");
/* 1596:     */        
/* 1597:1597 */        if (!this.format.isOmitEncoding()) {
/* 1598:1598 */          this.writer.write(" encoding=\"" + encoding + "\"");
/* 1599:     */        }
/* 1600:     */        
/* 1601:1601 */        this.writer.write("?>");
/* 1602:     */      }
/* 1603:     */      
/* 1604:1604 */      if (this.format.isNewLineAfterDeclaration()) {
/* 1605:1605 */        println();
/* 1606:     */      }
/* 1607:     */    }
/* 1608:     */  }
/* 1609:     */  
/* 1610:     */  protected void writeClose(String qualifiedName) throws IOException {
/* 1611:1611 */    this.writer.write("</");
/* 1612:1612 */    this.writer.write(qualifiedName);
/* 1613:1613 */    this.writer.write(">");
/* 1614:     */  }
/* 1615:     */  
/* 1616:     */  protected void writeEmptyElementClose(String qualifiedName)
/* 1617:     */    throws IOException
/* 1618:     */  {
/* 1619:1619 */    if (!this.format.isExpandEmptyElements()) {
/* 1620:1620 */      this.writer.write("/>");
/* 1621:     */    } else {
/* 1622:1622 */      this.writer.write("></");
/* 1623:1623 */      this.writer.write(qualifiedName);
/* 1624:1624 */      this.writer.write(">");
/* 1625:     */    }
/* 1626:     */  }
/* 1627:     */  
/* 1628:     */  protected boolean isExpandEmptyElements() {
/* 1629:1629 */    return this.format.isExpandEmptyElements();
/* 1630:     */  }
/* 1631:     */  
/* 1641:     */  protected String escapeElementEntities(String text)
/* 1642:     */  {
/* 1643:1643 */    char[] block = null;
/* 1644:     */    
/* 1645:1645 */    int last = 0;
/* 1646:1646 */    int size = text.length();
/* 1647:     */    
/* 1648:1648 */    for (int i = 0; i < size; i++) {
/* 1649:1649 */      String entity = null;
/* 1650:1650 */      char c = text.charAt(i);
/* 1651:     */      
/* 1652:1652 */      switch (c) {
/* 1653:     */      case '<': 
/* 1654:1654 */        entity = "&lt;";
/* 1655:     */        
/* 1656:1656 */        break;
/* 1657:     */      
/* 1658:     */      case '>': 
/* 1659:1659 */        entity = "&gt;";
/* 1660:     */        
/* 1661:1661 */        break;
/* 1662:     */      
/* 1663:     */      case '&': 
/* 1664:1664 */        entity = "&amp;";
/* 1665:     */        
/* 1666:1666 */        break;
/* 1667:     */      
/* 1670:     */      case '\t': 
/* 1671:     */      case '\n': 
/* 1672:     */      case '\r': 
/* 1673:1673 */        if (this.preserve)
/* 1674:1674 */          entity = String.valueOf(c); break;
/* 1675:     */      
/* 1680:     */      default: 
/* 1681:1681 */        if ((c < ' ') || (shouldEncodeChar(c))) {
/* 1682:1682 */          entity = "&#" + c + ";";
/* 1683:     */        }
/* 1684:     */        
/* 1685:     */        break;
/* 1686:     */      }
/* 1687:     */      
/* 1688:1688 */      if (entity != null) {
/* 1689:1689 */        if (block == null) {
/* 1690:1690 */          block = text.toCharArray();
/* 1691:     */        }
/* 1692:     */        
/* 1693:1693 */        this.buffer.append(block, last, i - last);
/* 1694:1694 */        this.buffer.append(entity);
/* 1695:1695 */        last = i + 1;
/* 1696:     */      }
/* 1697:     */    }
/* 1698:     */    
/* 1699:1699 */    if (last == 0) {
/* 1700:1700 */      return text;
/* 1701:     */    }
/* 1702:     */    
/* 1703:1703 */    if (last < size) {
/* 1704:1704 */      if (block == null) {
/* 1705:1705 */        block = text.toCharArray();
/* 1706:     */      }
/* 1707:     */      
/* 1708:1708 */      this.buffer.append(block, last, i - last);
/* 1709:     */    }
/* 1710:     */    
/* 1711:1711 */    String answer = this.buffer.toString();
/* 1712:1712 */    this.buffer.setLength(0);
/* 1713:     */    
/* 1714:1714 */    return answer;
/* 1715:     */  }
/* 1716:     */  
/* 1717:     */  protected void writeEscapeAttributeEntities(String txt) throws IOException {
/* 1718:1718 */    if (txt != null) {
/* 1719:1719 */      String escapedText = escapeAttributeEntities(txt);
/* 1720:1720 */      this.writer.write(escapedText);
/* 1721:     */    }
/* 1722:     */  }
/* 1723:     */  
/* 1733:     */  protected String escapeAttributeEntities(String text)
/* 1734:     */  {
/* 1735:1735 */    char quote = this.format.getAttributeQuoteCharacter();
/* 1736:     */    
/* 1737:1737 */    char[] block = null;
/* 1738:     */    
/* 1739:1739 */    int last = 0;
/* 1740:1740 */    int size = text.length();
/* 1741:     */    
/* 1742:1742 */    for (int i = 0; i < size; i++) {
/* 1743:1743 */      String entity = null;
/* 1744:1744 */      char c = text.charAt(i);
/* 1745:     */      
/* 1746:1746 */      switch (c) {
/* 1747:     */      case '<': 
/* 1748:1748 */        entity = "&lt;";
/* 1749:     */        
/* 1750:1750 */        break;
/* 1751:     */      
/* 1752:     */      case '>': 
/* 1753:1753 */        entity = "&gt;";
/* 1754:     */        
/* 1755:1755 */        break;
/* 1756:     */      
/* 1758:     */      case '\'': 
/* 1759:1759 */        if (quote == '\'')
/* 1760:1760 */          entity = "&apos;"; break;
/* 1761:     */      
/* 1766:     */      case '"': 
/* 1767:1767 */        if (quote == '"')
/* 1768:1768 */          entity = "&quot;"; break;
/* 1769:     */      
/* 1773:     */      case '&': 
/* 1774:1774 */        entity = "&amp;";
/* 1775:     */        
/* 1776:1776 */        break;
/* 1777:     */      
/* 1780:     */      case '\t': 
/* 1781:     */      case '\n': 
/* 1782:     */      case '\r': 
/* 1783:1783 */        break;
/* 1784:     */      
/* 1786:     */      default: 
/* 1787:1787 */        if ((c < ' ') || (shouldEncodeChar(c))) {
/* 1788:1788 */          entity = "&#" + c + ";";
/* 1789:     */        }
/* 1790:     */        
/* 1791:     */        break;
/* 1792:     */      }
/* 1793:     */      
/* 1794:1794 */      if (entity != null) {
/* 1795:1795 */        if (block == null) {
/* 1796:1796 */          block = text.toCharArray();
/* 1797:     */        }
/* 1798:     */        
/* 1799:1799 */        this.buffer.append(block, last, i - last);
/* 1800:1800 */        this.buffer.append(entity);
/* 1801:1801 */        last = i + 1;
/* 1802:     */      }
/* 1803:     */    }
/* 1804:     */    
/* 1805:1805 */    if (last == 0) {
/* 1806:1806 */      return text;
/* 1807:     */    }
/* 1808:     */    
/* 1809:1809 */    if (last < size) {
/* 1810:1810 */      if (block == null) {
/* 1811:1811 */        block = text.toCharArray();
/* 1812:     */      }
/* 1813:     */      
/* 1814:1814 */      this.buffer.append(block, last, i - last);
/* 1815:     */    }
/* 1816:     */    
/* 1817:1817 */    String answer = this.buffer.toString();
/* 1818:1818 */    this.buffer.setLength(0);
/* 1819:     */    
/* 1820:1820 */    return answer;
/* 1821:     */  }
/* 1822:     */  
/* 1831:     */  protected boolean shouldEncodeChar(char c)
/* 1832:     */  {
/* 1833:1833 */    int max = getMaximumAllowedCharacter();
/* 1834:     */    
/* 1835:1835 */    return (max > 0) && (c > max);
/* 1836:     */  }
/* 1837:     */  
/* 1844:     */  protected int defaultMaximumAllowedCharacter()
/* 1845:     */  {
/* 1846:1846 */    String encoding = this.format.getEncoding();
/* 1847:     */    
/* 1848:1848 */    if ((encoding != null) && 
/* 1849:1849 */      (encoding.equals("US-ASCII"))) {
/* 1850:1850 */      return 127;
/* 1851:     */    }
/* 1852:     */    
/* 1855:1855 */    return -1;
/* 1856:     */  }
/* 1857:     */  
/* 1858:     */  protected boolean isNamespaceDeclaration(Namespace ns) {
/* 1859:1859 */    if ((ns != null) && (ns != Namespace.XML_NAMESPACE)) {
/* 1860:1860 */      String uri = ns.getURI();
/* 1861:     */      
/* 1862:1862 */      if ((uri != null) && 
/* 1863:1863 */        (!this.namespaceStack.contains(ns))) {
/* 1864:1864 */        return true;
/* 1865:     */      }
/* 1866:     */    }
/* 1867:     */    
/* 1869:1869 */    return false;
/* 1870:     */  }
/* 1871:     */  
/* 1872:     */  protected void handleException(IOException e) throws SAXException {
/* 1873:1873 */    throw new SAXException(e);
/* 1874:     */  }
/* 1875:     */  
/* 1885:     */  protected OutputFormat getOutputFormat()
/* 1886:     */  {
/* 1887:1887 */    return this.format;
/* 1888:     */  }
/* 1889:     */  
/* 1890:     */  public boolean resolveEntityRefs() {
/* 1891:1891 */    return this.resolveEntityRefs;
/* 1892:     */  }
/* 1893:     */  
/* 1894:     */  public void setResolveEntityRefs(boolean resolve) {
/* 1895:1895 */    this.resolveEntityRefs = resolve;
/* 1896:     */  }
/* 1897:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.io.XMLWriter
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */