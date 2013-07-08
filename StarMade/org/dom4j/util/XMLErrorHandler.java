/*   1:    */package org.dom4j.util;
/*   2:    */
/*   3:    */import org.dom4j.DocumentHelper;
/*   4:    */import org.dom4j.Element;
/*   5:    */import org.dom4j.QName;
/*   6:    */import org.xml.sax.ErrorHandler;
/*   7:    */import org.xml.sax.SAXParseException;
/*   8:    */
/*  23:    */public class XMLErrorHandler
/*  24:    */  implements ErrorHandler
/*  25:    */{
/*  26: 26 */  protected static final QName ERROR_QNAME = QName.get("error");
/*  27:    */  
/*  28: 28 */  protected static final QName FATALERROR_QNAME = QName.get("fatalError");
/*  29:    */  
/*  30: 30 */  protected static final QName WARNING_QNAME = QName.get("warning");
/*  31:    */  
/*  33:    */  private Element errors;
/*  34:    */  
/*  36: 36 */  private QName errorQName = ERROR_QNAME;
/*  37:    */  
/*  39: 39 */  private QName fatalErrorQName = FATALERROR_QNAME;
/*  40:    */  
/*  42: 42 */  private QName warningQName = WARNING_QNAME;
/*  43:    */  
/*  44:    */  public XMLErrorHandler() {
/*  45: 45 */    this.errors = DocumentHelper.createElement("errors");
/*  46:    */  }
/*  47:    */  
/*  48:    */  public XMLErrorHandler(Element errors) {
/*  49: 49 */    this.errors = errors;
/*  50:    */  }
/*  51:    */  
/*  52:    */  public void error(SAXParseException e) {
/*  53: 53 */    Element element = this.errors.addElement(this.errorQName);
/*  54: 54 */    addException(element, e);
/*  55:    */  }
/*  56:    */  
/*  57:    */  public void fatalError(SAXParseException e) {
/*  58: 58 */    Element element = this.errors.addElement(this.fatalErrorQName);
/*  59: 59 */    addException(element, e);
/*  60:    */  }
/*  61:    */  
/*  62:    */  public void warning(SAXParseException e) {
/*  63: 63 */    Element element = this.errors.addElement(this.warningQName);
/*  64: 64 */    addException(element, e);
/*  65:    */  }
/*  66:    */  
/*  68:    */  public Element getErrors()
/*  69:    */  {
/*  70: 70 */    return this.errors;
/*  71:    */  }
/*  72:    */  
/*  73:    */  public void setErrors(Element errors) {
/*  74: 74 */    this.errors = errors;
/*  75:    */  }
/*  76:    */  
/*  77:    */  public QName getErrorQName()
/*  78:    */  {
/*  79: 79 */    return this.errorQName;
/*  80:    */  }
/*  81:    */  
/*  82:    */  public void setErrorQName(QName errorQName) {
/*  83: 83 */    this.errorQName = errorQName;
/*  84:    */  }
/*  85:    */  
/*  86:    */  public QName getFatalErrorQName() {
/*  87: 87 */    return this.fatalErrorQName;
/*  88:    */  }
/*  89:    */  
/*  90:    */  public void setFatalErrorQName(QName fatalErrorQName) {
/*  91: 91 */    this.fatalErrorQName = fatalErrorQName;
/*  92:    */  }
/*  93:    */  
/*  94:    */  public QName getWarningQName() {
/*  95: 95 */    return this.warningQName;
/*  96:    */  }
/*  97:    */  
/*  98:    */  public void setWarningQName(QName warningQName) {
/*  99: 99 */    this.warningQName = warningQName;
/* 100:    */  }
/* 101:    */  
/* 112:    */  protected void addException(Element element, SAXParseException e)
/* 113:    */  {
/* 114:114 */    element.addAttribute("column", Integer.toString(e.getColumnNumber()));
/* 115:115 */    element.addAttribute("line", Integer.toString(e.getLineNumber()));
/* 116:    */    
/* 117:117 */    String publicID = e.getPublicId();
/* 118:    */    
/* 119:119 */    if ((publicID != null) && (publicID.length() > 0)) {
/* 120:120 */      element.addAttribute("publicID", publicID);
/* 121:    */    }
/* 122:    */    
/* 123:123 */    String systemID = e.getSystemId();
/* 124:    */    
/* 125:125 */    if ((systemID != null) && (systemID.length() > 0)) {
/* 126:126 */      element.addAttribute("systemID", systemID);
/* 127:    */    }
/* 128:    */    
/* 129:129 */    element.addText(e.getMessage());
/* 130:    */  }
/* 131:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.util.XMLErrorHandler
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */