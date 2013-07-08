package org.dom4j.util;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.QName;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;

public class XMLErrorHandler
  implements ErrorHandler
{
  protected static final QName ERROR_QNAME = QName.get("error");
  protected static final QName FATALERROR_QNAME = QName.get("fatalError");
  protected static final QName WARNING_QNAME = QName.get("warning");
  private Element errors;
  private QName errorQName = ERROR_QNAME;
  private QName fatalErrorQName = FATALERROR_QNAME;
  private QName warningQName = WARNING_QNAME;
  
  public XMLErrorHandler()
  {
    this.errors = DocumentHelper.createElement("errors");
  }
  
  public XMLErrorHandler(Element errors)
  {
    this.errors = errors;
  }
  
  public void error(SAXParseException local_e)
  {
    Element element = this.errors.addElement(this.errorQName);
    addException(element, local_e);
  }
  
  public void fatalError(SAXParseException local_e)
  {
    Element element = this.errors.addElement(this.fatalErrorQName);
    addException(element, local_e);
  }
  
  public void warning(SAXParseException local_e)
  {
    Element element = this.errors.addElement(this.warningQName);
    addException(element, local_e);
  }
  
  public Element getErrors()
  {
    return this.errors;
  }
  
  public void setErrors(Element errors)
  {
    this.errors = errors;
  }
  
  public QName getErrorQName()
  {
    return this.errorQName;
  }
  
  public void setErrorQName(QName errorQName)
  {
    this.errorQName = errorQName;
  }
  
  public QName getFatalErrorQName()
  {
    return this.fatalErrorQName;
  }
  
  public void setFatalErrorQName(QName fatalErrorQName)
  {
    this.fatalErrorQName = fatalErrorQName;
  }
  
  public QName getWarningQName()
  {
    return this.warningQName;
  }
  
  public void setWarningQName(QName warningQName)
  {
    this.warningQName = warningQName;
  }
  
  protected void addException(Element element, SAXParseException local_e)
  {
    element.addAttribute("column", Integer.toString(local_e.getColumnNumber()));
    element.addAttribute("line", Integer.toString(local_e.getLineNumber()));
    String publicID = local_e.getPublicId();
    if ((publicID != null) && (publicID.length() > 0)) {
      element.addAttribute("publicID", publicID);
    }
    String systemID = local_e.getSystemId();
    if ((systemID != null) && (systemID.length() > 0)) {
      element.addAttribute("systemID", systemID);
    }
    element.addText(local_e.getMessage());
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.util.XMLErrorHandler
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */