package org.w3c.tidy;

import org.w3c.dom.NodeList;

public class DOMNodeListImpl
  implements NodeList
{
  private Node parent;
  
  protected DOMNodeListImpl(Node paramNode)
  {
    this.parent = paramNode;
  }
  
  public org.w3c.dom.Node item(int paramInt)
  {
    if (this.parent == null) {
      return null;
    }
    int i = 0;
    for (Node localNode = this.parent.content; (localNode != null) && (i < paramInt); localNode = localNode.next) {
      i++;
    }
    if (localNode != null) {
      return localNode.getAdapter();
    }
    return null;
  }
  
  public int getLength()
  {
    if (this.parent == null) {
      return 0;
    }
    int i = 0;
    for (Node localNode = this.parent.content; localNode != null; localNode = localNode.next) {
      i++;
    }
    return i;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.w3c.tidy.DOMNodeListImpl
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */