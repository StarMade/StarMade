/*     */ package org.dom4j.util;
/*     */ 
/*     */ import org.dom4j.DocumentHelper;
/*     */ import org.dom4j.Element;
/*     */ import org.dom4j.QName;
/*     */ import org.xml.sax.ErrorHandler;
/*     */ import org.xml.sax.SAXParseException;
/*     */ 
/*     */ public class XMLErrorHandler
/*     */   implements ErrorHandler
/*     */ {
/*  26 */   protected static final QName ERROR_QNAME = QName.get("error");
/*     */ 
/*  28 */   protected static final QName FATALERROR_QNAME = QName.get("fatalError");
/*     */ 
/*  30 */   protected static final QName WARNING_QNAME = QName.get("warning");
/*     */   private Element errors;
/*  36 */   private QName errorQName = ERROR_QNAME;
/*     */ 
/*  39 */   private QName fatalErrorQName = FATALERROR_QNAME;
/*     */ 
/*  42 */   private QName warningQName = WARNING_QNAME;
/*     */ 
/*     */   public XMLErrorHandler() {
/*  45 */     this.errors = DocumentHelper.createElement("errors");
/*     */   }
/*     */ 
/*     */   public XMLErrorHandler(Element errors) {
/*  49 */     this.errors = errors;
/*     */   }
/*     */ 
/*     */   public void error(SAXParseException e) {
/*  53 */     Element element = this.errors.addElement(this.errorQName);
/*  54 */     addException(element, e);
/*     */   }
/*     */ 
/*     */   public void fatalError(SAXParseException e) {
/*  58 */     Element element = this.errors.addElement(this.fatalErrorQName);
/*  59 */     addException(element, e);
/*     */   }
/*     */ 
/*     */   public void warning(SAXParseException e) {
/*  63 */     Element element = this.errors.addElement(this.warningQName);
/*  64 */     addException(element, e);
/*     */   }
/*     */ 
/*     */   public Element getErrors()
/*     */   {
/*  70 */     return this.errors;
/*     */   }
/*     */ 
/*     */   public void setErrors(Element errors) {
/*  74 */     this.errors = errors;
/*     */   }
/*     */ 
/*     */   public QName getErrorQName()
/*     */   {
/*  79 */     return this.errorQName;
/*     */   }
/*     */ 
/*     */   public void setErrorQName(QName errorQName) {
/*  83 */     this.errorQName = errorQName;
/*     */   }
/*     */ 
/*     */   public QName getFatalErrorQName() {
/*  87 */     return this.fatalErrorQName;
/*     */   }
/*     */ 
/*     */   public void setFatalErrorQName(QName fatalErrorQName) {
/*  91 */     this.fatalErrorQName = fatalErrorQName;
/*     */   }
/*     */ 
/*     */   public QName getWarningQName() {
/*  95 */     return this.warningQName;
/*     */   }
/*     */ 
/*     */   public void setWarningQName(QName warningQName) {
/*  99 */     this.warningQName = warningQName;
/*     */   }
/*     */ 
/*     */   protected void addException(Element element, SAXParseException e)
/*     */   {
/* 114 */     element.addAttribute("column", Integer.toString(e.getColumnNumber()));
/* 115 */     element.addAttribute("line", Integer.toString(e.getLineNumber()));
/*     */ 
/* 117 */     String publicID = e.getPublicId();
/*     */ 
/* 119 */     if ((publicID != null) && (publicID.length() > 0)) {
/* 120 */       element.addAttribute("publicID", publicID);
/*     */     }
/*     */ 
/* 123 */     String systemID = e.getSystemId();
/*     */ 
/* 125 */     if ((systemID != null) && (systemID.length() > 0)) {
/* 126 */       element.addAttribute("systemID", systemID);
/*     */     }
/*     */ 
/* 129 */     element.addText(e.getMessage());
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.util.XMLErrorHandler
 * JD-Core Version:    0.6.2
 */