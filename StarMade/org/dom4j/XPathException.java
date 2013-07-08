package org.dom4j;

public class XPathException
  extends RuntimeException
{
  private String xpath;
  
  public XPathException(String xpath)
  {
    super("Exception occurred evaluting XPath: " + xpath);
    this.xpath = xpath;
  }
  
  public XPathException(String xpath, String reason)
  {
    super("Exception occurred evaluting XPath: " + xpath + " " + reason);
    this.xpath = xpath;
  }
  
  public XPathException(String xpath, Exception local_e)
  {
    super("Exception occurred evaluting XPath: " + xpath + ". Exception: " + local_e.getMessage());
    this.xpath = xpath;
  }
  
  public String getXPath()
  {
    return this.xpath;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.XPathException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */