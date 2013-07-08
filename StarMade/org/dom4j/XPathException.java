/*  1:   */package org.dom4j;
/*  2:   */
/* 10:   */public class XPathException
/* 11:   */  extends RuntimeException
/* 12:   */{
/* 13:   */  private String xpath;
/* 14:   */  
/* 22:   */  public XPathException(String xpath)
/* 23:   */  {
/* 24:24 */    super("Exception occurred evaluting XPath: " + xpath);
/* 25:25 */    this.xpath = xpath;
/* 26:   */  }
/* 27:   */  
/* 28:   */  public XPathException(String xpath, String reason) {
/* 29:29 */    super("Exception occurred evaluting XPath: " + xpath + " " + reason);
/* 30:30 */    this.xpath = xpath;
/* 31:   */  }
/* 32:   */  
/* 33:   */  public XPathException(String xpath, Exception e) {
/* 34:34 */    super("Exception occurred evaluting XPath: " + xpath + ". Exception: " + e.getMessage());
/* 35:   */    
/* 36:36 */    this.xpath = xpath;
/* 37:   */  }
/* 38:   */  
/* 43:   */  public String getXPath()
/* 44:   */  {
/* 45:45 */    return this.xpath;
/* 46:   */  }
/* 47:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.XPathException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */