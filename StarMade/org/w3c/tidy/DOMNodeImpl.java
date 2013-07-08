package org.w3c.tidy;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.UserDataHandler;

public class DOMNodeImpl
  implements org.w3c.dom.Node
{
  protected Node adaptee;
  
  protected DOMNodeImpl(Node paramNode)
  {
    this.adaptee = paramNode;
  }
  
  public String getNodeValue()
  {
    String str = "";
    if (((this.adaptee.type == 4) || (this.adaptee.type == 8) || (this.adaptee.type == 2) || (this.adaptee.type == 3)) && (this.adaptee.textarray != null) && (this.adaptee.start < this.adaptee.end)) {
      str = TidyUtils.getString(this.adaptee.textarray, this.adaptee.start, this.adaptee.end - this.adaptee.start);
    }
    return str;
  }
  
  public void setNodeValue(String paramString)
  {
    if ((this.adaptee.type == 4) || (this.adaptee.type == 8) || (this.adaptee.type == 2) || (this.adaptee.type == 3))
    {
      byte[] arrayOfByte = TidyUtils.getBytes(paramString);
      this.adaptee.textarray = arrayOfByte;
      this.adaptee.start = 0;
      this.adaptee.end = arrayOfByte.length;
    }
  }
  
  public String getNodeName()
  {
    return this.adaptee.element;
  }
  
  public short getNodeType()
  {
    short s = -1;
    switch (this.adaptee.type)
    {
    case 0: 
      s = 9;
      break;
    case 1: 
      s = 10;
      break;
    case 2: 
      s = 8;
      break;
    case 3: 
      s = 7;
      break;
    case 4: 
      s = 3;
      break;
    case 8: 
      s = 4;
      break;
    case 5: 
    case 7: 
      s = 1;
    }
    return s;
  }
  
  public org.w3c.dom.Node getParentNode()
  {
    if (this.adaptee.parent != null) {
      return this.adaptee.parent.getAdapter();
    }
    return null;
  }
  
  public NodeList getChildNodes()
  {
    return new DOMNodeListImpl(this.adaptee);
  }
  
  public org.w3c.dom.Node getFirstChild()
  {
    if (this.adaptee.content != null) {
      return this.adaptee.content.getAdapter();
    }
    return null;
  }
  
  public org.w3c.dom.Node getLastChild()
  {
    if (this.adaptee.last != null) {
      return this.adaptee.last.getAdapter();
    }
    return null;
  }
  
  public org.w3c.dom.Node getPreviousSibling()
  {
    if (this.adaptee.prev != null) {
      return this.adaptee.prev.getAdapter();
    }
    return null;
  }
  
  public org.w3c.dom.Node getNextSibling()
  {
    if (this.adaptee.next != null) {
      return this.adaptee.next.getAdapter();
    }
    return null;
  }
  
  public NamedNodeMap getAttributes()
  {
    return new DOMAttrMapImpl(this.adaptee.attributes);
  }
  
  public Document getOwnerDocument()
  {
    Node localNode = this.adaptee;
    if ((localNode != null) && (localNode.type == 0)) {
      return null;
    }
    while ((localNode != null) && (localNode.type != 0)) {
      localNode = localNode.parent;
    }
    if (localNode != null) {
      return (Document)localNode.getAdapter();
    }
    return null;
  }
  
  public org.w3c.dom.Node insertBefore(org.w3c.dom.Node paramNode1, org.w3c.dom.Node paramNode2)
  {
    if (paramNode1 == null) {
      return null;
    }
    if (!(paramNode1 instanceof DOMNodeImpl)) {
      throw new DOMException((short)4, "newChild not instanceof DOMNodeImpl");
    }
    DOMNodeImpl localDOMNodeImpl = (DOMNodeImpl)paramNode1;
    if (this.adaptee.type == 0)
    {
      if ((localDOMNodeImpl.adaptee.type != 1) && (localDOMNodeImpl.adaptee.type != 3)) {
        throw new DOMException((short)3, "newChild cannot be a child of this node");
      }
    }
    else if ((this.adaptee.type == 5) && (localDOMNodeImpl.adaptee.type != 5) && (localDOMNodeImpl.adaptee.type != 7) && (localDOMNodeImpl.adaptee.type != 2) && (localDOMNodeImpl.adaptee.type != 4) && (localDOMNodeImpl.adaptee.type != 8)) {
      throw new DOMException((short)3, "newChild cannot be a child of this node");
    }
    if (paramNode2 == null)
    {
      this.adaptee.insertNodeAtEnd(localDOMNodeImpl.adaptee);
      if (this.adaptee.type == 7) {
        this.adaptee.setType((short)5);
      }
    }
    else
    {
      for (Node localNode = this.adaptee.content; (localNode != null) && (localNode.getAdapter() != paramNode2); localNode = localNode.next) {}
      if (localNode == null) {
        throw new DOMException((short)8, "refChild not found");
      }
      Node.insertNodeBeforeElement(localNode, localDOMNodeImpl.adaptee);
    }
    return paramNode1;
  }
  
  public org.w3c.dom.Node replaceChild(org.w3c.dom.Node paramNode1, org.w3c.dom.Node paramNode2)
  {
    if (paramNode1 == null) {
      return null;
    }
    if (!(paramNode1 instanceof DOMNodeImpl)) {
      throw new DOMException((short)4, "newChild not instanceof DOMNodeImpl");
    }
    DOMNodeImpl localDOMNodeImpl = (DOMNodeImpl)paramNode1;
    if (this.adaptee.type == 0)
    {
      if ((localDOMNodeImpl.adaptee.type != 1) && (localDOMNodeImpl.adaptee.type != 3)) {
        throw new DOMException((short)3, "newChild cannot be a child of this node");
      }
    }
    else if ((this.adaptee.type == 5) && (localDOMNodeImpl.adaptee.type != 5) && (localDOMNodeImpl.adaptee.type != 7) && (localDOMNodeImpl.adaptee.type != 2) && (localDOMNodeImpl.adaptee.type != 4) && (localDOMNodeImpl.adaptee.type != 8)) {
      throw new DOMException((short)3, "newChild cannot be a child of this node");
    }
    if (paramNode2 == null) {
      throw new DOMException((short)8, "oldChild not found");
    }
    for (Node localNode2 = this.adaptee.content; (localNode2 != null) && (localNode2.getAdapter() != paramNode2); localNode2 = localNode2.next) {}
    if (localNode2 == null) {
      throw new DOMException((short)8, "oldChild not found");
    }
    localDOMNodeImpl.adaptee.next = localNode2.next;
    localDOMNodeImpl.adaptee.prev = localNode2.prev;
    localDOMNodeImpl.adaptee.last = localNode2.last;
    localDOMNodeImpl.adaptee.parent = localNode2.parent;
    localDOMNodeImpl.adaptee.content = localNode2.content;
    if (localNode2.parent != null)
    {
      if (localNode2.parent.content == localNode2) {
        localNode2.parent.content = localDOMNodeImpl.adaptee;
      }
      if (localNode2.parent.last == localNode2) {
        localNode2.parent.last = localDOMNodeImpl.adaptee;
      }
    }
    if (localNode2.prev != null) {
      localNode2.prev.next = localDOMNodeImpl.adaptee;
    }
    if (localNode2.next != null) {
      localNode2.next.prev = localDOMNodeImpl.adaptee;
    }
    for (Node localNode1 = localNode2.content; localNode1 != null; localNode1 = localNode1.next) {
      if (localNode1.parent == localNode2) {
        localNode1.parent = localDOMNodeImpl.adaptee;
      }
    }
    return paramNode2;
  }
  
  public org.w3c.dom.Node removeChild(org.w3c.dom.Node paramNode)
  {
    if (paramNode == null) {
      return null;
    }
    for (Node localNode = this.adaptee.content; (localNode != null) && (localNode.getAdapter() != paramNode); localNode = localNode.next) {}
    if (localNode == null) {
      throw new DOMException((short)8, "refChild not found");
    }
    Node.discardElement(localNode);
    if ((this.adaptee.content == null) && (this.adaptee.type == 5)) {
      this.adaptee.setType((short)7);
    }
    return paramNode;
  }
  
  public org.w3c.dom.Node appendChild(org.w3c.dom.Node paramNode)
  {
    if (paramNode == null) {
      return null;
    }
    if (!(paramNode instanceof DOMNodeImpl)) {
      throw new DOMException((short)4, "newChild not instanceof DOMNodeImpl");
    }
    DOMNodeImpl localDOMNodeImpl = (DOMNodeImpl)paramNode;
    if (this.adaptee.type == 0)
    {
      if ((localDOMNodeImpl.adaptee.type != 1) && (localDOMNodeImpl.adaptee.type != 3)) {
        throw new DOMException((short)3, "newChild cannot be a child of this node");
      }
    }
    else if ((this.adaptee.type == 5) && (localDOMNodeImpl.adaptee.type != 5) && (localDOMNodeImpl.adaptee.type != 7) && (localDOMNodeImpl.adaptee.type != 2) && (localDOMNodeImpl.adaptee.type != 4) && (localDOMNodeImpl.adaptee.type != 8)) {
      throw new DOMException((short)3, "newChild cannot be a child of this node");
    }
    this.adaptee.insertNodeAtEnd(localDOMNodeImpl.adaptee);
    if (this.adaptee.type == 7) {
      this.adaptee.setType((short)5);
    }
    return paramNode;
  }
  
  public boolean hasChildNodes()
  {
    return this.adaptee.content != null;
  }
  
  public org.w3c.dom.Node cloneNode(boolean paramBoolean)
  {
    Node localNode = this.adaptee.cloneNode(paramBoolean);
    localNode.parent = null;
    return localNode.getAdapter();
  }
  
  public void normalize() {}
  
  public boolean supports(String paramString1, String paramString2)
  {
    return isSupported(paramString1, paramString2);
  }
  
  public String getNamespaceURI()
  {
    return null;
  }
  
  public String getPrefix()
  {
    return null;
  }
  
  public void setPrefix(String paramString)
    throws DOMException
  {}
  
  public String getLocalName()
  {
    return getNodeName();
  }
  
  public boolean isSupported(String paramString1, String paramString2)
  {
    return false;
  }
  
  public boolean hasAttributes()
  {
    return this.adaptee.attributes != null;
  }
  
  public short compareDocumentPosition(org.w3c.dom.Node paramNode)
    throws DOMException
  {
    throw new DOMException((short)9, "DOM method not supported");
  }
  
  public String getBaseURI()
  {
    return null;
  }
  
  public Object getFeature(String paramString1, String paramString2)
  {
    return null;
  }
  
  public String getTextContent()
    throws DOMException
  {
    return null;
  }
  
  public Object getUserData(String paramString)
  {
    return null;
  }
  
  public boolean isDefaultNamespace(String paramString)
  {
    return false;
  }
  
  public boolean isEqualNode(org.w3c.dom.Node paramNode)
  {
    return false;
  }
  
  public boolean isSameNode(org.w3c.dom.Node paramNode)
  {
    return false;
  }
  
  public String lookupNamespaceURI(String paramString)
  {
    return null;
  }
  
  public String lookupPrefix(String paramString)
  {
    return null;
  }
  
  public void setTextContent(String paramString)
    throws DOMException
  {
    throw new DOMException((short)7, "Node is read only");
  }
  
  public Object setUserData(String paramString, Object paramObject, UserDataHandler paramUserDataHandler)
  {
    return null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.w3c.tidy.DOMNodeImpl
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */