package org.w3c.tidy;

import org.w3c.dom.DocumentType;
import org.w3c.dom.NamedNodeMap;

public class DOMDocumentTypeImpl
  extends DOMNodeImpl
  implements DocumentType
{
  protected DOMDocumentTypeImpl(Node paramNode)
  {
    super(paramNode);
  }
  
  public short getNodeType()
  {
    return 10;
  }
  
  public String getNodeName()
  {
    return getName();
  }
  
  public String getName()
  {
    String str = null;
    if ((this.adaptee.type == 1) && (this.adaptee.textarray != null) && (this.adaptee.start < this.adaptee.end)) {
      str = TidyUtils.getString(this.adaptee.textarray, this.adaptee.start, this.adaptee.end - this.adaptee.start);
    }
    return str;
  }
  
  public NamedNodeMap getEntities()
  {
    return null;
  }
  
  public NamedNodeMap getNotations()
  {
    return null;
  }
  
  public String getPublicId()
  {
    return null;
  }
  
  public String getSystemId()
  {
    return null;
  }
  
  public String getInternalSubset()
  {
    return null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.w3c.tidy.DOMDocumentTypeImpl
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */