package org.w3c.tidy;

import org.w3c.dom.CDATASection;

public class DOMCDATASectionImpl extends DOMTextImpl
  implements CDATASection
{
  protected DOMCDATASectionImpl(Node paramNode)
  {
    super(paramNode);
  }

  public String getNodeName()
  {
    return "#cdata-section";
  }

  public short getNodeType()
  {
    return 4;
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.w3c.tidy.DOMCDATASectionImpl
 * JD-Core Version:    0.6.2
 */