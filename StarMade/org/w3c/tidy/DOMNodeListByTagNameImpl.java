package org.w3c.tidy;

import org.w3c.dom.NodeList;

public class DOMNodeListByTagNameImpl
  implements NodeList
{
  private Node first;
  private String tagName;
  private int currIndex;
  private int maxIndex;
  private Node currNode;

  protected DOMNodeListByTagNameImpl(Node paramNode, String paramString)
  {
    this.first = paramNode;
    this.tagName = paramString;
  }

  public org.w3c.dom.Node item(int paramInt)
  {
    this.currIndex = 0;
    this.maxIndex = paramInt;
    preTraverse(this.first);
    if ((this.currIndex > this.maxIndex) && (this.currNode != null))
      return this.currNode.getAdapter();
    return null;
  }

  public int getLength()
  {
    this.currIndex = 0;
    this.maxIndex = 2147483647;
    preTraverse(this.first);
    return this.currIndex;
  }

  protected void preTraverse(Node paramNode)
  {
    if (paramNode == null)
      return;
    if (((paramNode.type == 5) || (paramNode.type == 7)) && (this.currIndex <= this.maxIndex) && ((this.tagName.equals("*")) || (this.tagName.equals(paramNode.element))))
    {
      this.currIndex += 1;
      this.currNode = paramNode;
    }
    if (this.currIndex > this.maxIndex)
      return;
    for (paramNode = paramNode.content; paramNode != null; paramNode = paramNode.next)
      preTraverse(paramNode);
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.w3c.tidy.DOMNodeListByTagNameImpl
 * JD-Core Version:    0.6.2
 */