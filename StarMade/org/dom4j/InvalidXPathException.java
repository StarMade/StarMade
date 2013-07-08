/*  1:   */package org.dom4j;
/*  2:   */
/* 10:   */public class InvalidXPathException
/* 11:   */  extends IllegalArgumentException
/* 12:   */{
/* 13:   */  private static final long serialVersionUID = 3257009869058881592L;
/* 14:   */  
/* 21:   */  public InvalidXPathException(String xpath)
/* 22:   */  {
/* 23:23 */    super("Invalid XPath expression: " + xpath);
/* 24:   */  }
/* 25:   */  
/* 26:   */  public InvalidXPathException(String xpath, String reason) {
/* 27:27 */    super("Invalid XPath expression: " + xpath + " " + reason);
/* 28:   */  }
/* 29:   */  
/* 30:   */  public InvalidXPathException(String xpath, Throwable t) {
/* 31:31 */    super("Invalid XPath expression: '" + xpath + "'. Caused by: " + t.getMessage());
/* 32:   */  }
/* 33:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.InvalidXPathException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */