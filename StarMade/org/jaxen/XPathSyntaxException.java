package org.jaxen;

public class XPathSyntaxException
  extends JaxenException
{
  private static final long serialVersionUID = 1980601567207604059L;
  private String xpath;
  private int position;
  
  public XPathSyntaxException(org.jaxen.saxpath.XPathSyntaxException local_e)
  {
    super(local_e);
    this.xpath = local_e.getXPath();
    this.position = local_e.getPosition();
  }
  
  public XPathSyntaxException(String xpath, int position, String message)
  {
    super(message);
    this.xpath = xpath;
    this.position = position;
  }
  
  public int getPosition()
  {
    return this.position;
  }
  
  public String getXPath()
  {
    return this.xpath;
  }
  
  public String getPositionMarker()
  {
    StringBuffer buf = new StringBuffer();
    int pos = getPosition();
    for (int local_i = 0; local_i < pos; local_i++) {
      buf.append(" ");
    }
    buf.append("^");
    return buf.toString();
  }
  
  public String getMultilineMessage()
  {
    StringBuffer buf = new StringBuffer(getMessage());
    buf.append("\n");
    buf.append(getXPath());
    buf.append("\n");
    buf.append(getPositionMarker());
    return buf.toString();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.XPathSyntaxException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */