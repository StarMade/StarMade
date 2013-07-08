package org.jaxen.saxpath;

public class XPathSyntaxException
  extends SAXPathException
{
  private static final long serialVersionUID = 3567675610742422397L;
  private String xpath;
  private int position;
  private static final String lineSeparator = System.getProperty("line.separator");
  
  public XPathSyntaxException(String xpath, int position, String message)
  {
    super(message);
    this.position = position;
    this.xpath = xpath;
  }
  
  public int getPosition()
  {
    return this.position;
  }
  
  public String getXPath()
  {
    return this.xpath;
  }
  
  public String toString()
  {
    return getClass() + ": " + getXPath() + ": " + getPosition() + ": " + getMessage();
  }
  
  private String getPositionMarker()
  {
    int pos = getPosition();
    StringBuffer buf = new StringBuffer(pos + 1);
    for (int local_i = 0; local_i < pos; local_i++) {
      buf.append(" ");
    }
    buf.append("^");
    return buf.toString();
  }
  
  public String getMultilineMessage()
  {
    StringBuffer buf = new StringBuffer();
    buf.append(getMessage());
    buf.append(lineSeparator);
    buf.append(getXPath());
    buf.append(lineSeparator);
    buf.append(getPositionMarker());
    return buf.toString();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.saxpath.XPathSyntaxException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */