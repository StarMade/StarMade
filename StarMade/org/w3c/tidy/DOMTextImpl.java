package org.w3c.tidy;

import org.w3c.dom.DOMException;
import org.w3c.dom.Text;

public class DOMTextImpl extends DOMCharacterDataImpl
  implements Text
{
  protected DOMTextImpl(Node paramNode)
  {
    super(paramNode);
  }

  public String getNodeName()
  {
    return "#text";
  }

  public short getNodeType()
  {
    return 3;
  }

  public Text splitText(int paramInt)
    throws DOMException
  {
    throw new DOMException((short)7, "Not supported");
  }

  public String getWholeText()
  {
    return null;
  }

  public boolean isElementContentWhitespace()
  {
    return false;
  }

  public Text replaceWholeText(String paramString)
    throws DOMException
  {
    return this;
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.w3c.tidy.DOMTextImpl
 * JD-Core Version:    0.6.2
 */