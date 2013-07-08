package org.w3c.tidy;

import org.w3c.dom.DOMException;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class DOMAttrMapImpl
  implements NamedNodeMap
{
  private AttVal first;
  
  protected DOMAttrMapImpl(AttVal paramAttVal)
  {
    this.first = paramAttVal;
  }
  
  public Node getNamedItem(String paramString)
  {
    for (AttVal localAttVal = this.first; (localAttVal != null) && (!localAttVal.attribute.equals(paramString)); localAttVal = localAttVal.next) {}
    if (localAttVal != null) {
      return localAttVal.getAdapter();
    }
    return null;
  }
  
  public Node item(int paramInt)
  {
    int i = 0;
    for (AttVal localAttVal = this.first; (localAttVal != null) && (i < paramInt); localAttVal = localAttVal.next) {
      i++;
    }
    if (localAttVal != null) {
      return localAttVal.getAdapter();
    }
    return null;
  }
  
  public int getLength()
  {
    int i = 0;
    for (AttVal localAttVal = this.first; localAttVal != null; localAttVal = localAttVal.next) {
      i++;
    }
    return i;
  }
  
  public Node setNamedItem(Node paramNode)
    throws DOMException
  {
    throw new DOMException((short)9, "DOM method not supported");
  }
  
  public Node removeNamedItem(String paramString)
    throws DOMException
  {
    AttVal localAttVal1 = this.first;
    AttVal localAttVal2 = null;
    while (localAttVal1 != null)
    {
      if (localAttVal1.attribute.equals(paramString))
      {
        if (localAttVal2 == null)
        {
          this.first = localAttVal1.getNext();
          break;
        }
        localAttVal2.setNext(localAttVal1.getNext());
        break;
      }
      localAttVal2 = localAttVal1;
      localAttVal1 = localAttVal1.next;
    }
    if (localAttVal1 != null) {
      return localAttVal1.getAdapter();
    }
    throw new DOMException((short)8, "Named item " + paramString + "Not found");
  }
  
  public Node getNamedItemNS(String paramString1, String paramString2)
  {
    throw new DOMException((short)9, "DOM method not supported");
  }
  
  public Node setNamedItemNS(Node paramNode)
    throws DOMException
  {
    throw new DOMException((short)9, "DOM method not supported");
  }
  
  public Node removeNamedItemNS(String paramString1, String paramString2)
    throws DOMException
  {
    throw new DOMException((short)9, "DOM method not supported");
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.w3c.tidy.DOMAttrMapImpl
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */