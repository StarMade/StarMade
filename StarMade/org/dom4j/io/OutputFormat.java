/*     */ package org.dom4j.io;
/*     */ 
/*     */ public class OutputFormat
/*     */   implements Cloneable
/*     */ {
/*     */   protected static final String STANDARD_INDENT = "  ";
/*  27 */   private boolean suppressDeclaration = false;
/*     */ 
/*  33 */   private boolean newLineAfterDeclaration = true;
/*     */ 
/*  36 */   private String encoding = "UTF-8";
/*     */ 
/*  42 */   private boolean omitEncoding = false;
/*     */ 
/*  45 */   private String indent = null;
/*     */ 
/*  51 */   private boolean expandEmptyElements = false;
/*     */ 
/*  57 */   private boolean newlines = false;
/*     */ 
/*  60 */   private String lineSeparator = "\n";
/*     */ 
/*  63 */   private boolean trimText = false;
/*     */ 
/*  66 */   private boolean padText = false;
/*     */ 
/*  69 */   private boolean doXHTML = false;
/*     */ 
/*  75 */   private int newLineAfterNTags = 0;
/*     */ 
/*  78 */   private char attributeQuoteChar = '"';
/*     */ 
/*     */   public OutputFormat()
/*     */   {
/*     */   }
/*     */ 
/*     */   public OutputFormat(String indent)
/*     */   {
/*  97 */     this.indent = indent;
/*     */   }
/*     */ 
/*     */   public OutputFormat(String indent, boolean newlines)
/*     */   {
/* 112 */     this.indent = indent;
/* 113 */     this.newlines = newlines;
/*     */   }
/*     */ 
/*     */   public OutputFormat(String indent, boolean newlines, String encoding)
/*     */   {
/* 129 */     this.indent = indent;
/* 130 */     this.newlines = newlines;
/* 131 */     this.encoding = encoding;
/*     */   }
/*     */ 
/*     */   public String getLineSeparator() {
/* 135 */     return this.lineSeparator;
/*     */   }
/*     */ 
/*     */   public void setLineSeparator(String separator)
/*     */   {
/* 152 */     this.lineSeparator = separator;
/*     */   }
/*     */ 
/*     */   public boolean isNewlines() {
/* 156 */     return this.newlines;
/*     */   }
/*     */ 
/*     */   public void setNewlines(boolean newlines)
/*     */   {
/* 169 */     this.newlines = newlines;
/*     */   }
/*     */ 
/*     */   public String getEncoding() {
/* 173 */     return this.encoding;
/*     */   }
/*     */ 
/*     */   public void setEncoding(String encoding)
/*     */   {
/* 183 */     if (encoding != null)
/* 184 */       this.encoding = encoding;
/*     */   }
/*     */ 
/*     */   public boolean isOmitEncoding()
/*     */   {
/* 189 */     return this.omitEncoding;
/*     */   }
/*     */ 
/*     */   public void setOmitEncoding(boolean omitEncoding)
/*     */   {
/* 205 */     this.omitEncoding = omitEncoding;
/*     */   }
/*     */ 
/*     */   public void setSuppressDeclaration(boolean suppressDeclaration)
/*     */   {
/* 221 */     this.suppressDeclaration = suppressDeclaration;
/*     */   }
/*     */ 
/*     */   public boolean isSuppressDeclaration()
/*     */   {
/* 232 */     return this.suppressDeclaration;
/*     */   }
/*     */ 
/*     */   public void setNewLineAfterDeclaration(boolean newLineAfterDeclaration)
/*     */   {
/* 246 */     this.newLineAfterDeclaration = newLineAfterDeclaration;
/*     */   }
/*     */ 
/*     */   public boolean isNewLineAfterDeclaration()
/*     */   {
/* 255 */     return this.newLineAfterDeclaration;
/*     */   }
/*     */ 
/*     */   public boolean isExpandEmptyElements() {
/* 259 */     return this.expandEmptyElements;
/*     */   }
/*     */ 
/*     */   public void setExpandEmptyElements(boolean expandEmptyElements)
/*     */   {
/* 274 */     this.expandEmptyElements = expandEmptyElements;
/*     */   }
/*     */ 
/*     */   public boolean isTrimText() {
/* 278 */     return this.trimText;
/*     */   }
/*     */ 
/*     */   public void setTrimText(boolean trimText)
/*     */   {
/* 300 */     this.trimText = trimText;
/*     */   }
/*     */ 
/*     */   public boolean isPadText() {
/* 304 */     return this.padText;
/*     */   }
/*     */ 
/*     */   public void setPadText(boolean padText)
/*     */   {
/* 332 */     this.padText = padText;
/*     */   }
/*     */ 
/*     */   public String getIndent() {
/* 336 */     return this.indent;
/*     */   }
/*     */ 
/*     */   public void setIndent(String indent)
/*     */   {
/* 352 */     if ((indent != null) && (indent.length() <= 0)) {
/* 353 */       indent = null;
/*     */     }
/*     */ 
/* 356 */     this.indent = indent;
/*     */   }
/*     */ 
/*     */   public void setIndent(boolean doIndent)
/*     */   {
/* 367 */     if (doIndent)
/* 368 */       this.indent = "  ";
/*     */     else
/* 370 */       this.indent = null;
/*     */   }
/*     */ 
/*     */   public void setIndentSize(int indentSize)
/*     */   {
/* 385 */     StringBuffer indentBuffer = new StringBuffer();
/*     */ 
/* 387 */     for (int i = 0; i < indentSize; i++) {
/* 388 */       indentBuffer.append(" ");
/*     */     }
/*     */ 
/* 391 */     this.indent = indentBuffer.toString();
/*     */   }
/*     */ 
/*     */   public boolean isXHTML()
/*     */   {
/* 410 */     return this.doXHTML;
/*     */   }
/*     */ 
/*     */   public void setXHTML(boolean xhtml)
/*     */   {
/* 431 */     this.doXHTML = xhtml;
/*     */   }
/*     */ 
/*     */   public int getNewLineAfterNTags() {
/* 435 */     return this.newLineAfterNTags;
/*     */   }
/*     */ 
/*     */   public void setNewLineAfterNTags(int tagCount)
/*     */   {
/* 451 */     this.newLineAfterNTags = tagCount;
/*     */   }
/*     */ 
/*     */   public char getAttributeQuoteCharacter() {
/* 455 */     return this.attributeQuoteChar;
/*     */   }
/*     */ 
/*     */   public void setAttributeQuoteCharacter(char quoteChar)
/*     */   {
/* 471 */     if ((quoteChar == '\'') || (quoteChar == '"'))
/* 472 */       this.attributeQuoteChar = quoteChar;
/*     */     else
/* 474 */       throw new IllegalArgumentException("Invalid attribute quote character (" + quoteChar + ")");
/*     */   }
/*     */ 
/*     */   public int parseOptions(String[] args, int i)
/*     */   {
/* 491 */     for (int size = args.length; i < size; i++) {
/* 492 */       if (args[i].equals("-suppressDeclaration"))
/* 493 */         setSuppressDeclaration(true);
/* 494 */       else if (args[i].equals("-omitEncoding"))
/* 495 */         setOmitEncoding(true);
/* 496 */       else if (args[i].equals("-indent"))
/* 497 */         setIndent(args[(++i)]);
/* 498 */       else if (args[i].equals("-indentSize"))
/* 499 */         setIndentSize(Integer.parseInt(args[(++i)]));
/* 500 */       else if (args[i].startsWith("-expandEmpty"))
/* 501 */         setExpandEmptyElements(true);
/* 502 */       else if (args[i].equals("-encoding"))
/* 503 */         setEncoding(args[(++i)]);
/* 504 */       else if (args[i].equals("-newlines"))
/* 505 */         setNewlines(true);
/* 506 */       else if (args[i].equals("-lineSeparator"))
/* 507 */         setLineSeparator(args[(++i)]);
/* 508 */       else if (args[i].equals("-trimText"))
/* 509 */         setTrimText(true);
/* 510 */       else if (args[i].equals("-padText"))
/* 511 */         setPadText(true);
/* 512 */       else if (args[i].startsWith("-xhtml"))
/* 513 */         setXHTML(true);
/*     */       else {
/* 515 */         return i;
/*     */       }
/*     */     }
/*     */ 
/* 519 */     return i;
/*     */   }
/*     */ 
/*     */   public static OutputFormat createPrettyPrint()
/*     */   {
/* 530 */     OutputFormat format = new OutputFormat();
/* 531 */     format.setIndentSize(2);
/* 532 */     format.setNewlines(true);
/* 533 */     format.setTrimText(true);
/* 534 */     format.setPadText(true);
/*     */ 
/* 536 */     return format;
/*     */   }
/*     */ 
/*     */   public static OutputFormat createCompactFormat()
/*     */   {
/* 547 */     OutputFormat format = new OutputFormat();
/* 548 */     format.setIndent(false);
/* 549 */     format.setNewlines(false);
/* 550 */     format.setTrimText(true);
/*     */ 
/* 552 */     return format;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.io.OutputFormat
 * JD-Core Version:    0.6.2
 */