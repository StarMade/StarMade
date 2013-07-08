package org.dom4j;

public class InvalidXPathException
  extends IllegalArgumentException
{
  private static final long serialVersionUID = 3257009869058881592L;
  
  public InvalidXPathException(String xpath)
  {
    super("Invalid XPath expression: " + xpath);
  }
  
  public InvalidXPathException(String xpath, String reason)
  {
    super("Invalid XPath expression: " + xpath + " " + reason);
  }
  
  public InvalidXPathException(String xpath, Throwable local_t)
  {
    super("Invalid XPath expression: '" + xpath + "'. Caused by: " + local_t.getMessage());
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.InvalidXPathException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */