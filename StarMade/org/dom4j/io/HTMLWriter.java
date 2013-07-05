/*     */ package org.dom4j.io;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.io.StringWriter;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.io.Writer;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import java.util.Stack;
/*     */ import org.dom4j.Document;
/*     */ import org.dom4j.DocumentException;
/*     */ import org.dom4j.DocumentHelper;
/*     */ import org.dom4j.Element;
/*     */ import org.dom4j.Entity;
/*     */ import org.xml.sax.SAXException;
/*     */ 
/*     */ public class HTMLWriter extends XMLWriter
/*     */ {
/* 182 */   private static String lineSeparator = System.getProperty("line.separator");
/*     */ 
/* 189 */   protected static final HashSet DEFAULT_PREFORMATTED_TAGS = new HashSet();
/*     */   protected static final OutputFormat DEFAULT_HTML_FORMAT;
/* 204 */   private Stack formatStack = new Stack();
/*     */ 
/* 206 */   private String lastText = "";
/*     */ 
/* 208 */   private int tagsOuput = 0;
/*     */ 
/* 211 */   private int newLineAfterNTags = -1;
/*     */ 
/* 213 */   private HashSet preformattedTags = DEFAULT_PREFORMATTED_TAGS;
/*     */   private HashSet omitElementCloseSet;
/*     */ 
/*     */   public HTMLWriter(Writer writer)
/*     */   {
/* 222 */     super(writer, DEFAULT_HTML_FORMAT);
/*     */   }
/*     */ 
/*     */   public HTMLWriter(Writer writer, OutputFormat format) {
/* 226 */     super(writer, format);
/*     */   }
/*     */ 
/*     */   public HTMLWriter() throws UnsupportedEncodingException {
/* 230 */     super(DEFAULT_HTML_FORMAT);
/*     */   }
/*     */ 
/*     */   public HTMLWriter(OutputFormat format) throws UnsupportedEncodingException {
/* 234 */     super(format);
/*     */   }
/*     */ 
/*     */   public HTMLWriter(OutputStream out) throws UnsupportedEncodingException {
/* 238 */     super(out, DEFAULT_HTML_FORMAT);
/*     */   }
/*     */ 
/*     */   public HTMLWriter(OutputStream out, OutputFormat format) throws UnsupportedEncodingException
/*     */   {
/* 243 */     super(out, format);
/*     */   }
/*     */ 
/*     */   public void startCDATA()
/*     */     throws SAXException
/*     */   {
/*     */   }
/*     */ 
/*     */   public void endCDATA() throws SAXException
/*     */   {
/*     */   }
/*     */ 
/*     */   protected void writeCDATA(String text) throws IOException
/*     */   {
/* 257 */     if (getOutputFormat().isXHTML())
/* 258 */       super.writeCDATA(text);
/*     */     else {
/* 260 */       this.writer.write(text);
/*     */     }
/*     */ 
/* 263 */     this.lastOutputNodeType = 4;
/*     */   }
/*     */ 
/*     */   protected void writeEntity(Entity entity) throws IOException {
/* 267 */     this.writer.write(entity.getText());
/* 268 */     this.lastOutputNodeType = 5;
/*     */   }
/*     */ 
/*     */   protected void writeDeclaration()
/*     */     throws IOException
/*     */   {
/*     */   }
/*     */ 
/*     */   protected void writeString(String text)
/*     */     throws IOException
/*     */   {
/* 287 */     if (text.equals("\n")) {
/* 288 */       if (!this.formatStack.empty()) {
/* 289 */         super.writeString(lineSeparator);
/*     */       }
/*     */ 
/* 292 */       return;
/*     */     }
/*     */ 
/* 295 */     this.lastText = text;
/*     */ 
/* 297 */     if (this.formatStack.empty())
/* 298 */       super.writeString(text.trim());
/*     */     else
/* 300 */       super.writeString(text);
/*     */   }
/*     */ 
/*     */   protected void writeClose(String qualifiedName)
/*     */     throws IOException
/*     */   {
/* 315 */     if (!omitElementClose(qualifiedName))
/* 316 */       super.writeClose(qualifiedName);
/*     */   }
/*     */ 
/*     */   protected void writeEmptyElementClose(String qualifiedName)
/*     */     throws IOException
/*     */   {
/* 322 */     if (getOutputFormat().isXHTML())
/*     */     {
/* 324 */       if (omitElementClose(qualifiedName))
/*     */       {
/* 330 */         this.writer.write(" />");
/*     */       }
/* 332 */       else super.writeEmptyElementClose(qualifiedName);
/*     */ 
/*     */     }
/* 336 */     else if (omitElementClose(qualifiedName))
/*     */     {
/* 338 */       this.writer.write(">");
/*     */     }
/*     */     else
/*     */     {
/* 342 */       super.writeEmptyElementClose(qualifiedName);
/*     */     }
/*     */   }
/*     */ 
/*     */   protected boolean omitElementClose(String qualifiedName)
/*     */   {
/* 348 */     return internalGetOmitElementCloseSet().contains(qualifiedName.toUpperCase());
/*     */   }
/*     */ 
/*     */   private HashSet internalGetOmitElementCloseSet()
/*     */   {
/* 353 */     if (this.omitElementCloseSet == null) {
/* 354 */       this.omitElementCloseSet = new HashSet();
/* 355 */       loadOmitElementCloseSet(this.omitElementCloseSet);
/*     */     }
/*     */ 
/* 358 */     return this.omitElementCloseSet;
/*     */   }
/*     */ 
/*     */   protected void loadOmitElementCloseSet(Set set)
/*     */   {
/* 363 */     set.add("AREA");
/* 364 */     set.add("BASE");
/* 365 */     set.add("BR");
/* 366 */     set.add("COL");
/* 367 */     set.add("HR");
/* 368 */     set.add("IMG");
/* 369 */     set.add("INPUT");
/* 370 */     set.add("LINK");
/* 371 */     set.add("META");
/* 372 */     set.add("P");
/* 373 */     set.add("PARAM");
/*     */   }
/*     */ 
/*     */   public Set getOmitElementCloseSet()
/*     */   {
/* 386 */     return (Set)internalGetOmitElementCloseSet().clone();
/*     */   }
/*     */ 
/*     */   public void setOmitElementCloseSet(Set newSet)
/*     */   {
/* 407 */     this.omitElementCloseSet = new HashSet();
/*     */ 
/* 409 */     if (newSet != null) {
/* 410 */       this.omitElementCloseSet = new HashSet();
/*     */ 
/* 413 */       Iterator iter = newSet.iterator();
/*     */ 
/* 415 */       while (iter.hasNext()) {
/* 416 */         Object aTag = iter.next();
/*     */ 
/* 418 */         if (aTag != null)
/* 419 */           this.omitElementCloseSet.add(aTag.toString().toUpperCase());
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public Set getPreformattedTags()
/*     */   {
/* 429 */     return (Set)this.preformattedTags.clone();
/*     */   }
/*     */ 
/*     */   public void setPreformattedTags(Set newSet)
/*     */   {
/* 534 */     this.preformattedTags = new HashSet();
/*     */ 
/* 536 */     if (newSet != null)
/*     */     {
/* 538 */       Iterator iter = newSet.iterator();
/*     */ 
/* 540 */       while (iter.hasNext()) {
/* 541 */         Object aTag = iter.next();
/*     */ 
/* 543 */         if (aTag != null)
/* 544 */           this.preformattedTags.add(aTag.toString().toUpperCase());
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean isPreformattedTag(String qualifiedName)
/*     */   {
/* 565 */     return (this.preformattedTags != null) && (this.preformattedTags.contains(qualifiedName.toUpperCase()));
/*     */   }
/*     */ 
/*     */   protected void writeElement(Element element)
/*     */     throws IOException
/*     */   {
/* 584 */     if (this.newLineAfterNTags == -1) {
/* 585 */       lazyInitNewLinesAfterNTags();
/*     */     }
/*     */ 
/* 588 */     if ((this.newLineAfterNTags > 0) && 
/* 589 */       (this.tagsOuput > 0) && (this.tagsOuput % this.newLineAfterNTags == 0)) {
/* 590 */       this.writer.write(lineSeparator);
/*     */     }
/*     */ 
/* 594 */     this.tagsOuput += 1;
/*     */ 
/* 596 */     String qualifiedName = element.getQualifiedName();
/* 597 */     String saveLastText = this.lastText;
/* 598 */     int size = element.nodeCount();
/*     */ 
/* 600 */     if (isPreformattedTag(qualifiedName)) {
/* 601 */       OutputFormat currentFormat = getOutputFormat();
/* 602 */       boolean saveNewlines = currentFormat.isNewlines();
/* 603 */       boolean saveTrimText = currentFormat.isTrimText();
/* 604 */       String currentIndent = currentFormat.getIndent();
/*     */ 
/* 608 */       this.formatStack.push(new FormatState(saveNewlines, saveTrimText, currentIndent));
/*     */       try
/*     */       {
/* 614 */         super.writePrintln();
/*     */ 
/* 616 */         if ((saveLastText.trim().length() == 0) && (currentIndent != null) && (currentIndent.length() > 0))
/*     */         {
/* 624 */           this.writer.write(justSpaces(saveLastText));
/*     */         }
/*     */ 
/* 629 */         currentFormat.setNewlines(false);
/* 630 */         currentFormat.setTrimText(false);
/* 631 */         currentFormat.setIndent("");
/*     */ 
/* 634 */         super.writeElement(element);
/*     */       } finally {
/* 636 */         FormatState state = (FormatState)this.formatStack.pop();
/* 637 */         currentFormat.setNewlines(state.isNewlines());
/* 638 */         currentFormat.setTrimText(state.isTrimText());
/* 639 */         currentFormat.setIndent(state.getIndent());
/*     */       }
/*     */     } else {
/* 642 */       super.writeElement(element);
/*     */     }
/*     */   }
/*     */ 
/*     */   private String justSpaces(String text) {
/* 647 */     int size = text.length();
/* 648 */     StringBuffer res = new StringBuffer(size);
/*     */ 
/* 651 */     for (int i = 0; i < size; i++) {
/* 652 */       char c = text.charAt(i);
/*     */ 
/* 654 */       switch (c)
/*     */       {
/*     */       case '\n':
/*     */       case '\r':
/* 658 */         break;
/*     */       default:
/* 661 */         res.append(c);
/*     */       }
/*     */     }
/*     */ 
/* 665 */     return res.toString();
/*     */   }
/*     */ 
/*     */   private void lazyInitNewLinesAfterNTags() {
/* 669 */     if (getOutputFormat().isNewlines())
/*     */     {
/* 671 */       this.newLineAfterNTags = 0;
/*     */     }
/* 673 */     else this.newLineAfterNTags = getOutputFormat().getNewLineAfterNTags();
/*     */   }
/*     */ 
/*     */   public static String prettyPrintHTML(String html)
/*     */     throws IOException, UnsupportedEncodingException, DocumentException
/*     */   {
/* 697 */     return prettyPrintHTML(html, true, true, false, true);
/*     */   }
/*     */ 
/*     */   public static String prettyPrintXHTML(String html)
/*     */     throws IOException, UnsupportedEncodingException, DocumentException
/*     */   {
/* 719 */     return prettyPrintHTML(html, true, true, true, false);
/*     */   }
/*     */ 
/*     */   public static String prettyPrintHTML(String html, boolean newlines, boolean trim, boolean isXHTML, boolean expandEmpty)
/*     */     throws IOException, UnsupportedEncodingException, DocumentException
/*     */   {
/* 750 */     StringWriter sw = new StringWriter();
/* 751 */     OutputFormat format = OutputFormat.createPrettyPrint();
/* 752 */     format.setNewlines(newlines);
/* 753 */     format.setTrimText(trim);
/* 754 */     format.setXHTML(isXHTML);
/* 755 */     format.setExpandEmptyElements(expandEmpty);
/*     */ 
/* 757 */     HTMLWriter writer = new HTMLWriter(sw, format);
/* 758 */     Document document = DocumentHelper.parseText(html);
/* 759 */     writer.write(document);
/* 760 */     writer.flush();
/*     */ 
/* 762 */     return sw.toString();
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/* 190 */     DEFAULT_PREFORMATTED_TAGS.add("PRE");
/* 191 */     DEFAULT_PREFORMATTED_TAGS.add("SCRIPT");
/* 192 */     DEFAULT_PREFORMATTED_TAGS.add("STYLE");
/* 193 */     DEFAULT_PREFORMATTED_TAGS.add("TEXTAREA");
/*     */ 
/* 199 */     DEFAULT_HTML_FORMAT = new OutputFormat("  ", true);
/* 200 */     DEFAULT_HTML_FORMAT.setTrimText(true);
/* 201 */     DEFAULT_HTML_FORMAT.setSuppressDeclaration(true);
/*     */   }
/*     */ 
/*     */   private class FormatState
/*     */   {
/* 768 */     private boolean newlines = false;
/*     */ 
/* 770 */     private boolean trimText = false;
/*     */ 
/* 772 */     private String indent = "";
/*     */ 
/*     */     public FormatState(boolean newLines, boolean trimText, String indent) {
/* 775 */       this.newlines = newLines;
/* 776 */       this.trimText = trimText;
/* 777 */       this.indent = indent;
/*     */     }
/*     */ 
/*     */     public boolean isNewlines() {
/* 781 */       return this.newlines;
/*     */     }
/*     */ 
/*     */     public boolean isTrimText() {
/* 785 */       return this.trimText;
/*     */     }
/*     */ 
/*     */     public String getIndent() {
/* 789 */       return this.indent;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.io.HTMLWriter
 * JD-Core Version:    0.6.2
 */