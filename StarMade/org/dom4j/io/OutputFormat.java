/*   1:    */package org.dom4j.io;
/*   2:    */
/*  13:    */public class OutputFormat
/*  14:    */  implements Cloneable
/*  15:    */{
/*  16:    */  protected static final String STANDARD_INDENT = "  ";
/*  17:    */  
/*  27: 27 */  private boolean suppressDeclaration = false;
/*  28:    */  
/*  33: 33 */  private boolean newLineAfterDeclaration = true;
/*  34:    */  
/*  36: 36 */  private String encoding = "UTF-8";
/*  37:    */  
/*  42: 42 */  private boolean omitEncoding = false;
/*  43:    */  
/*  45: 45 */  private String indent = null;
/*  46:    */  
/*  51: 51 */  private boolean expandEmptyElements = false;
/*  52:    */  
/*  57: 57 */  private boolean newlines = false;
/*  58:    */  
/*  60: 60 */  private String lineSeparator = "\n";
/*  61:    */  
/*  63: 63 */  private boolean trimText = false;
/*  64:    */  
/*  66: 66 */  private boolean padText = false;
/*  67:    */  
/*  69: 69 */  private boolean doXHTML = false;
/*  70:    */  
/*  75: 75 */  private int newLineAfterNTags = 0;
/*  76:    */  
/*  78: 78 */  private char attributeQuoteChar = '"';
/*  79:    */  
/*  87:    */  public OutputFormat() {}
/*  88:    */  
/*  95:    */  public OutputFormat(String indent)
/*  96:    */  {
/*  97: 97 */    this.indent = indent;
/*  98:    */  }
/*  99:    */  
/* 110:    */  public OutputFormat(String indent, boolean newlines)
/* 111:    */  {
/* 112:112 */    this.indent = indent;
/* 113:113 */    this.newlines = newlines;
/* 114:    */  }
/* 115:    */  
/* 127:    */  public OutputFormat(String indent, boolean newlines, String encoding)
/* 128:    */  {
/* 129:129 */    this.indent = indent;
/* 130:130 */    this.newlines = newlines;
/* 131:131 */    this.encoding = encoding;
/* 132:    */  }
/* 133:    */  
/* 134:    */  public String getLineSeparator() {
/* 135:135 */    return this.lineSeparator;
/* 136:    */  }
/* 137:    */  
/* 150:    */  public void setLineSeparator(String separator)
/* 151:    */  {
/* 152:152 */    this.lineSeparator = separator;
/* 153:    */  }
/* 154:    */  
/* 155:    */  public boolean isNewlines() {
/* 156:156 */    return this.newlines;
/* 157:    */  }
/* 158:    */  
/* 167:    */  public void setNewlines(boolean newlines)
/* 168:    */  {
/* 169:169 */    this.newlines = newlines;
/* 170:    */  }
/* 171:    */  
/* 172:    */  public String getEncoding() {
/* 173:173 */    return this.encoding;
/* 174:    */  }
/* 175:    */  
/* 181:    */  public void setEncoding(String encoding)
/* 182:    */  {
/* 183:183 */    if (encoding != null) {
/* 184:184 */      this.encoding = encoding;
/* 185:    */    }
/* 186:    */  }
/* 187:    */  
/* 188:    */  public boolean isOmitEncoding() {
/* 189:189 */    return this.omitEncoding;
/* 190:    */  }
/* 191:    */  
/* 203:    */  public void setOmitEncoding(boolean omitEncoding)
/* 204:    */  {
/* 205:205 */    this.omitEncoding = omitEncoding;
/* 206:    */  }
/* 207:    */  
/* 219:    */  public void setSuppressDeclaration(boolean suppressDeclaration)
/* 220:    */  {
/* 221:221 */    this.suppressDeclaration = suppressDeclaration;
/* 222:    */  }
/* 223:    */  
/* 230:    */  public boolean isSuppressDeclaration()
/* 231:    */  {
/* 232:232 */    return this.suppressDeclaration;
/* 233:    */  }
/* 234:    */  
/* 244:    */  public void setNewLineAfterDeclaration(boolean newLineAfterDeclaration)
/* 245:    */  {
/* 246:246 */    this.newLineAfterDeclaration = newLineAfterDeclaration;
/* 247:    */  }
/* 248:    */  
/* 253:    */  public boolean isNewLineAfterDeclaration()
/* 254:    */  {
/* 255:255 */    return this.newLineAfterDeclaration;
/* 256:    */  }
/* 257:    */  
/* 258:    */  public boolean isExpandEmptyElements() {
/* 259:259 */    return this.expandEmptyElements;
/* 260:    */  }
/* 261:    */  
/* 272:    */  public void setExpandEmptyElements(boolean expandEmptyElements)
/* 273:    */  {
/* 274:274 */    this.expandEmptyElements = expandEmptyElements;
/* 275:    */  }
/* 276:    */  
/* 277:    */  public boolean isTrimText() {
/* 278:278 */    return this.trimText;
/* 279:    */  }
/* 280:    */  
/* 298:    */  public void setTrimText(boolean trimText)
/* 299:    */  {
/* 300:300 */    this.trimText = trimText;
/* 301:    */  }
/* 302:    */  
/* 303:    */  public boolean isPadText() {
/* 304:304 */    return this.padText;
/* 305:    */  }
/* 306:    */  
/* 330:    */  public void setPadText(boolean padText)
/* 331:    */  {
/* 332:332 */    this.padText = padText;
/* 333:    */  }
/* 334:    */  
/* 335:    */  public String getIndent() {
/* 336:336 */    return this.indent;
/* 337:    */  }
/* 338:    */  
/* 350:    */  public void setIndent(String indent)
/* 351:    */  {
/* 352:352 */    if ((indent != null) && (indent.length() <= 0)) {
/* 353:353 */      indent = null;
/* 354:    */    }
/* 355:    */    
/* 356:356 */    this.indent = indent;
/* 357:    */  }
/* 358:    */  
/* 365:    */  public void setIndent(boolean doIndent)
/* 366:    */  {
/* 367:367 */    if (doIndent) {
/* 368:368 */      this.indent = "  ";
/* 369:    */    } else {
/* 370:370 */      this.indent = null;
/* 371:    */    }
/* 372:    */  }
/* 373:    */  
/* 383:    */  public void setIndentSize(int indentSize)
/* 384:    */  {
/* 385:385 */    StringBuffer indentBuffer = new StringBuffer();
/* 386:    */    
/* 387:387 */    for (int i = 0; i < indentSize; i++) {
/* 388:388 */      indentBuffer.append(" ");
/* 389:    */    }
/* 390:    */    
/* 391:391 */    this.indent = indentBuffer.toString();
/* 392:    */  }
/* 393:    */  
/* 408:    */  public boolean isXHTML()
/* 409:    */  {
/* 410:410 */    return this.doXHTML;
/* 411:    */  }
/* 412:    */  
/* 429:    */  public void setXHTML(boolean xhtml)
/* 430:    */  {
/* 431:431 */    this.doXHTML = xhtml;
/* 432:    */  }
/* 433:    */  
/* 434:    */  public int getNewLineAfterNTags() {
/* 435:435 */    return this.newLineAfterNTags;
/* 436:    */  }
/* 437:    */  
/* 449:    */  public void setNewLineAfterNTags(int tagCount)
/* 450:    */  {
/* 451:451 */    this.newLineAfterNTags = tagCount;
/* 452:    */  }
/* 453:    */  
/* 454:    */  public char getAttributeQuoteCharacter() {
/* 455:455 */    return this.attributeQuoteChar;
/* 456:    */  }
/* 457:    */  
/* 469:    */  public void setAttributeQuoteCharacter(char quoteChar)
/* 470:    */  {
/* 471:471 */    if ((quoteChar == '\'') || (quoteChar == '"')) {
/* 472:472 */      this.attributeQuoteChar = quoteChar;
/* 473:    */    } else {
/* 474:474 */      throw new IllegalArgumentException("Invalid attribute quote character (" + quoteChar + ")");
/* 475:    */    }
/* 476:    */  }
/* 477:    */  
/* 489:    */  public int parseOptions(String[] args, int i)
/* 490:    */  {
/* 491:491 */    for (int size = args.length; i < size; i++) {
/* 492:492 */      if (args[i].equals("-suppressDeclaration")) {
/* 493:493 */        setSuppressDeclaration(true);
/* 494:494 */      } else if (args[i].equals("-omitEncoding")) {
/* 495:495 */        setOmitEncoding(true);
/* 496:496 */      } else if (args[i].equals("-indent")) {
/* 497:497 */        setIndent(args[(++i)]);
/* 498:498 */      } else if (args[i].equals("-indentSize")) {
/* 499:499 */        setIndentSize(Integer.parseInt(args[(++i)]));
/* 500:500 */      } else if (args[i].startsWith("-expandEmpty")) {
/* 501:501 */        setExpandEmptyElements(true);
/* 502:502 */      } else if (args[i].equals("-encoding")) {
/* 503:503 */        setEncoding(args[(++i)]);
/* 504:504 */      } else if (args[i].equals("-newlines")) {
/* 505:505 */        setNewlines(true);
/* 506:506 */      } else if (args[i].equals("-lineSeparator")) {
/* 507:507 */        setLineSeparator(args[(++i)]);
/* 508:508 */      } else if (args[i].equals("-trimText")) {
/* 509:509 */        setTrimText(true);
/* 510:510 */      } else if (args[i].equals("-padText")) {
/* 511:511 */        setPadText(true);
/* 512:512 */      } else if (args[i].startsWith("-xhtml")) {
/* 513:513 */        setXHTML(true);
/* 514:    */      } else {
/* 515:515 */        return i;
/* 516:    */      }
/* 517:    */    }
/* 518:    */    
/* 519:519 */    return i;
/* 520:    */  }
/* 521:    */  
/* 528:    */  public static OutputFormat createPrettyPrint()
/* 529:    */  {
/* 530:530 */    OutputFormat format = new OutputFormat();
/* 531:531 */    format.setIndentSize(2);
/* 532:532 */    format.setNewlines(true);
/* 533:533 */    format.setTrimText(true);
/* 534:534 */    format.setPadText(true);
/* 535:    */    
/* 536:536 */    return format;
/* 537:    */  }
/* 538:    */  
/* 545:    */  public static OutputFormat createCompactFormat()
/* 546:    */  {
/* 547:547 */    OutputFormat format = new OutputFormat();
/* 548:548 */    format.setIndent(false);
/* 549:549 */    format.setNewlines(false);
/* 550:550 */    format.setTrimText(true);
/* 551:    */    
/* 552:552 */    return format;
/* 553:    */  }
/* 554:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.io.OutputFormat
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */