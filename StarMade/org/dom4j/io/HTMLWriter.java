/*   1:    */package org.dom4j.io;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */import java.io.OutputStream;
/*   5:    */import java.io.StringWriter;
/*   6:    */import java.io.UnsupportedEncodingException;
/*   7:    */import java.io.Writer;
/*   8:    */import java.util.HashSet;
/*   9:    */import java.util.Iterator;
/*  10:    */import java.util.Set;
/*  11:    */import java.util.Stack;
/*  12:    */import org.dom4j.Document;
/*  13:    */import org.dom4j.DocumentException;
/*  14:    */import org.dom4j.DocumentHelper;
/*  15:    */import org.dom4j.Element;
/*  16:    */import org.dom4j.Entity;
/*  17:    */import org.xml.sax.SAXException;
/*  18:    */
/* 179:    */public class HTMLWriter
/* 180:    */  extends XMLWriter
/* 181:    */{
/* 182:182 */  private static String lineSeparator = System.getProperty("line.separator");
/* 183:    */  
/* 189:189 */  protected static final HashSet DEFAULT_PREFORMATTED_TAGS = new HashSet();
/* 190:190 */  static { DEFAULT_PREFORMATTED_TAGS.add("PRE");
/* 191:191 */    DEFAULT_PREFORMATTED_TAGS.add("SCRIPT");
/* 192:192 */    DEFAULT_PREFORMATTED_TAGS.add("STYLE");
/* 193:193 */    DEFAULT_PREFORMATTED_TAGS.add("TEXTAREA");
/* 194:    */    
/* 199:199 */    DEFAULT_HTML_FORMAT = new OutputFormat("  ", true);
/* 200:200 */    DEFAULT_HTML_FORMAT.setTrimText(true);
/* 201:201 */    DEFAULT_HTML_FORMAT.setSuppressDeclaration(true); }
/* 202:    */  
/* 203:    */  protected static final OutputFormat DEFAULT_HTML_FORMAT;
/* 204:204 */  private Stack formatStack = new Stack();
/* 205:    */  
/* 206:206 */  private String lastText = "";
/* 207:    */  
/* 208:208 */  private int tagsOuput = 0;
/* 209:    */  
/* 211:211 */  private int newLineAfterNTags = -1;
/* 212:    */  
/* 213:213 */  private HashSet preformattedTags = DEFAULT_PREFORMATTED_TAGS;
/* 214:    */  
/* 216:    */  private HashSet omitElementCloseSet;
/* 217:    */  
/* 220:    */  public HTMLWriter(Writer writer)
/* 221:    */  {
/* 222:222 */    super(writer, DEFAULT_HTML_FORMAT);
/* 223:    */  }
/* 224:    */  
/* 225:    */  public HTMLWriter(Writer writer, OutputFormat format) {
/* 226:226 */    super(writer, format);
/* 227:    */  }
/* 228:    */  
/* 229:    */  public HTMLWriter() throws UnsupportedEncodingException {
/* 230:230 */    super(DEFAULT_HTML_FORMAT);
/* 231:    */  }
/* 232:    */  
/* 233:    */  public HTMLWriter(OutputFormat format) throws UnsupportedEncodingException {
/* 234:234 */    super(format);
/* 235:    */  }
/* 236:    */  
/* 237:    */  public HTMLWriter(OutputStream out) throws UnsupportedEncodingException {
/* 238:238 */    super(out, DEFAULT_HTML_FORMAT);
/* 239:    */  }
/* 240:    */  
/* 241:    */  public HTMLWriter(OutputStream out, OutputFormat format) throws UnsupportedEncodingException
/* 242:    */  {
/* 243:243 */    super(out, format);
/* 244:    */  }
/* 245:    */  
/* 254:    */  protected void writeCDATA(String text)
/* 255:    */    throws IOException
/* 256:    */  {
/* 257:257 */    if (getOutputFormat().isXHTML()) {
/* 258:258 */      super.writeCDATA(text);
/* 259:    */    } else {
/* 260:260 */      this.writer.write(text);
/* 261:    */    }
/* 262:    */    
/* 263:263 */    this.lastOutputNodeType = 4;
/* 264:    */  }
/* 265:    */  
/* 266:    */  protected void writeEntity(Entity entity) throws IOException {
/* 267:267 */    this.writer.write(entity.getText());
/* 268:268 */    this.lastOutputNodeType = 5;
/* 269:    */  }
/* 270:    */  
/* 284:    */  protected void writeString(String text)
/* 285:    */    throws IOException
/* 286:    */  {
/* 287:287 */    if (text.equals("\n")) {
/* 288:288 */      if (!this.formatStack.empty()) {
/* 289:289 */        super.writeString(lineSeparator);
/* 290:    */      }
/* 291:    */      
/* 292:292 */      return;
/* 293:    */    }
/* 294:    */    
/* 295:295 */    this.lastText = text;
/* 296:    */    
/* 297:297 */    if (this.formatStack.empty()) {
/* 298:298 */      super.writeString(text.trim());
/* 299:    */    } else {
/* 300:300 */      super.writeString(text);
/* 301:    */    }
/* 302:    */  }
/* 303:    */  
/* 312:    */  protected void writeClose(String qualifiedName)
/* 313:    */    throws IOException
/* 314:    */  {
/* 315:315 */    if (!omitElementClose(qualifiedName)) {
/* 316:316 */      super.writeClose(qualifiedName);
/* 317:    */    }
/* 318:    */  }
/* 319:    */  
/* 320:    */  protected void writeEmptyElementClose(String qualifiedName) throws IOException
/* 321:    */  {
/* 322:322 */    if (getOutputFormat().isXHTML())
/* 323:    */    {
/* 324:324 */      if (omitElementClose(qualifiedName))
/* 325:    */      {
/* 330:330 */        this.writer.write(" />");
/* 331:    */      } else {
/* 332:332 */        super.writeEmptyElementClose(qualifiedName);
/* 333:    */      }
/* 334:    */      
/* 335:    */    }
/* 336:336 */    else if (omitElementClose(qualifiedName))
/* 337:    */    {
/* 338:338 */      this.writer.write(">");
/* 339:    */    }
/* 340:    */    else
/* 341:    */    {
/* 342:342 */      super.writeEmptyElementClose(qualifiedName);
/* 343:    */    }
/* 344:    */  }
/* 345:    */  
/* 346:    */  protected boolean omitElementClose(String qualifiedName)
/* 347:    */  {
/* 348:348 */    return internalGetOmitElementCloseSet().contains(qualifiedName.toUpperCase());
/* 349:    */  }
/* 350:    */  
/* 351:    */  private HashSet internalGetOmitElementCloseSet()
/* 352:    */  {
/* 353:353 */    if (this.omitElementCloseSet == null) {
/* 354:354 */      this.omitElementCloseSet = new HashSet();
/* 355:355 */      loadOmitElementCloseSet(this.omitElementCloseSet);
/* 356:    */    }
/* 357:    */    
/* 358:358 */    return this.omitElementCloseSet;
/* 359:    */  }
/* 360:    */  
/* 361:    */  protected void loadOmitElementCloseSet(Set set)
/* 362:    */  {
/* 363:363 */    set.add("AREA");
/* 364:364 */    set.add("BASE");
/* 365:365 */    set.add("BR");
/* 366:366 */    set.add("COL");
/* 367:367 */    set.add("HR");
/* 368:368 */    set.add("IMG");
/* 369:369 */    set.add("INPUT");
/* 370:370 */    set.add("LINK");
/* 371:371 */    set.add("META");
/* 372:372 */    set.add("P");
/* 373:373 */    set.add("PARAM");
/* 374:    */  }
/* 375:    */  
/* 384:    */  public Set getOmitElementCloseSet()
/* 385:    */  {
/* 386:386 */    return (Set)internalGetOmitElementCloseSet().clone();
/* 387:    */  }
/* 388:    */  
/* 405:    */  public void setOmitElementCloseSet(Set newSet)
/* 406:    */  {
/* 407:407 */    this.omitElementCloseSet = new HashSet();
/* 408:    */    
/* 409:409 */    if (newSet != null) {
/* 410:410 */      this.omitElementCloseSet = new HashSet();
/* 411:    */      
/* 413:413 */      Iterator iter = newSet.iterator();
/* 414:    */      
/* 415:415 */      while (iter.hasNext()) {
/* 416:416 */        Object aTag = iter.next();
/* 417:    */        
/* 418:418 */        if (aTag != null) {
/* 419:419 */          this.omitElementCloseSet.add(aTag.toString().toUpperCase());
/* 420:    */        }
/* 421:    */      }
/* 422:    */    }
/* 423:    */  }
/* 424:    */  
/* 427:    */  public Set getPreformattedTags()
/* 428:    */  {
/* 429:429 */    return (Set)this.preformattedTags.clone();
/* 430:    */  }
/* 431:    */  
/* 532:    */  public void setPreformattedTags(Set newSet)
/* 533:    */  {
/* 534:534 */    this.preformattedTags = new HashSet();
/* 535:    */    
/* 536:536 */    if (newSet != null)
/* 537:    */    {
/* 538:538 */      Iterator iter = newSet.iterator();
/* 539:    */      
/* 540:540 */      while (iter.hasNext()) {
/* 541:541 */        Object aTag = iter.next();
/* 542:    */        
/* 543:543 */        if (aTag != null) {
/* 544:544 */          this.preformattedTags.add(aTag.toString().toUpperCase());
/* 545:    */        }
/* 546:    */      }
/* 547:    */    }
/* 548:    */  }
/* 549:    */  
/* 563:    */  public boolean isPreformattedTag(String qualifiedName)
/* 564:    */  {
/* 565:565 */    return (this.preformattedTags != null) && (this.preformattedTags.contains(qualifiedName.toUpperCase()));
/* 566:    */  }
/* 567:    */  
/* 581:    */  protected void writeElement(Element element)
/* 582:    */    throws IOException
/* 583:    */  {
/* 584:584 */    if (this.newLineAfterNTags == -1) {
/* 585:585 */      lazyInitNewLinesAfterNTags();
/* 586:    */    }
/* 587:    */    
/* 588:588 */    if ((this.newLineAfterNTags > 0) && 
/* 589:589 */      (this.tagsOuput > 0) && (this.tagsOuput % this.newLineAfterNTags == 0)) {
/* 590:590 */      this.writer.write(lineSeparator);
/* 591:    */    }
/* 592:    */    
/* 594:594 */    this.tagsOuput += 1;
/* 595:    */    
/* 596:596 */    String qualifiedName = element.getQualifiedName();
/* 597:597 */    String saveLastText = this.lastText;
/* 598:598 */    int size = element.nodeCount();
/* 599:    */    
/* 600:600 */    if (isPreformattedTag(qualifiedName)) {
/* 601:601 */      OutputFormat currentFormat = getOutputFormat();
/* 602:602 */      boolean saveNewlines = currentFormat.isNewlines();
/* 603:603 */      boolean saveTrimText = currentFormat.isTrimText();
/* 604:604 */      String currentIndent = currentFormat.getIndent();
/* 605:    */      
/* 608:608 */      this.formatStack.push(new FormatState(saveNewlines, saveTrimText, currentIndent));
/* 609:    */      
/* 612:    */      try
/* 613:    */      {
/* 614:614 */        super.writePrintln();
/* 615:    */        
/* 616:616 */        if ((saveLastText.trim().length() == 0) && (currentIndent != null) && (currentIndent.length() > 0))
/* 617:    */        {
/* 624:624 */          this.writer.write(justSpaces(saveLastText));
/* 625:    */        }
/* 626:    */        
/* 629:629 */        currentFormat.setNewlines(false);
/* 630:630 */        currentFormat.setTrimText(false);
/* 631:631 */        currentFormat.setIndent("");
/* 632:    */        
/* 634:634 */        super.writeElement(element);
/* 635:    */      } finally {
/* 636:636 */        FormatState state = (FormatState)this.formatStack.pop();
/* 637:637 */        currentFormat.setNewlines(state.isNewlines());
/* 638:638 */        currentFormat.setTrimText(state.isTrimText());
/* 639:639 */        currentFormat.setIndent(state.getIndent());
/* 640:    */      }
/* 641:    */    } else {
/* 642:642 */      super.writeElement(element);
/* 643:    */    }
/* 644:    */  }
/* 645:    */  
/* 646:    */  private String justSpaces(String text) {
/* 647:647 */    int size = text.length();
/* 648:648 */    StringBuffer res = new StringBuffer(size);
/* 649:    */    
/* 651:651 */    for (int i = 0; i < size; i++) {
/* 652:652 */      char c = text.charAt(i);
/* 653:    */      
/* 654:654 */      switch (c)
/* 655:    */      {
/* 656:    */      case '\n': 
/* 657:    */      case '\r': 
/* 658:658 */        break;
/* 659:    */      
/* 660:    */      default: 
/* 661:661 */        res.append(c);
/* 662:    */      }
/* 663:    */      
/* 664:    */    }
/* 665:665 */    return res.toString();
/* 666:    */  }
/* 667:    */  
/* 668:    */  private void lazyInitNewLinesAfterNTags() {
/* 669:669 */    if (getOutputFormat().isNewlines())
/* 670:    */    {
/* 671:671 */      this.newLineAfterNTags = 0;
/* 672:    */    } else {
/* 673:673 */      this.newLineAfterNTags = getOutputFormat().getNewLineAfterNTags();
/* 674:    */    }
/* 675:    */  }
/* 676:    */  
/* 694:    */  public static String prettyPrintHTML(String html)
/* 695:    */    throws IOException, UnsupportedEncodingException, DocumentException
/* 696:    */  {
/* 697:697 */    return prettyPrintHTML(html, true, true, false, true);
/* 698:    */  }
/* 699:    */  
/* 716:    */  public static String prettyPrintXHTML(String html)
/* 717:    */    throws IOException, UnsupportedEncodingException, DocumentException
/* 718:    */  {
/* 719:719 */    return prettyPrintHTML(html, true, true, true, false);
/* 720:    */  }
/* 721:    */  
/* 747:    */  public static String prettyPrintHTML(String html, boolean newlines, boolean trim, boolean isXHTML, boolean expandEmpty)
/* 748:    */    throws IOException, UnsupportedEncodingException, DocumentException
/* 749:    */  {
/* 750:750 */    StringWriter sw = new StringWriter();
/* 751:751 */    OutputFormat format = OutputFormat.createPrettyPrint();
/* 752:752 */    format.setNewlines(newlines);
/* 753:753 */    format.setTrimText(trim);
/* 754:754 */    format.setXHTML(isXHTML);
/* 755:755 */    format.setExpandEmptyElements(expandEmpty);
/* 756:    */    
/* 757:757 */    HTMLWriter writer = new HTMLWriter(sw, format);
/* 758:758 */    Document document = DocumentHelper.parseText(html);
/* 759:759 */    writer.write(document);
/* 760:760 */    writer.flush();
/* 761:    */    
/* 762:762 */    return sw.toString();
/* 763:    */  }
/* 764:    */  
/* 765:    */  public void startCDATA() throws SAXException
/* 766:    */  {}
/* 767:    */  
/* 768:768 */  private class FormatState { private boolean newlines = false;
/* 769:    */    
/* 770:770 */    private boolean trimText = false;
/* 771:    */    
/* 772:772 */    private String indent = "";
/* 773:    */    
/* 774:    */    public FormatState(boolean newLines, boolean trimText, String indent) {
/* 775:775 */      this.newlines = newLines;
/* 776:776 */      this.trimText = trimText;
/* 777:777 */      this.indent = indent;
/* 778:    */    }
/* 779:    */    
/* 780:    */    public boolean isNewlines() {
/* 781:781 */      return this.newlines;
/* 782:    */    }
/* 783:    */    
/* 784:    */    public boolean isTrimText() {
/* 785:785 */      return this.trimText;
/* 786:    */    }
/* 787:    */    
/* 788:    */    public String getIndent() {
/* 789:789 */      return this.indent;
/* 790:    */    }
/* 791:    */  }
/* 792:    */  
/* 793:    */  public void endCDATA()
/* 794:    */    throws SAXException
/* 795:    */  {}
/* 796:    */  
/* 797:    */  protected void writeDeclaration()
/* 798:    */    throws IOException
/* 799:    */  {}
/* 800:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.io.HTMLWriter
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */