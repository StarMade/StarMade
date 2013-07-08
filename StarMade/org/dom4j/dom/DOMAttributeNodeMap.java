package org.dom4j.dom;

import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class DOMAttributeNodeMap
  implements NamedNodeMap
{
  private DOMElement element;
  
  public DOMAttributeNodeMap(DOMElement element)
  {
    this.element = element;
  }
  
  public void foo()
    throws DOMException
  {}
  
  public Node getNamedItem(String name)
  {
    return this.element.getAttributeNode(name);
  }
  
  public Node setNamedItem(Node arg)
    throws DOMException
  {
    if ((arg instanceof Attr)) {
      return this.element.setAttributeNode((Attr)arg);
    }
    throw new DOMException((short)9, "Node is not an Attr: " + arg);
  }
  
  public Node removeNamedItem(String name)
    throws DOMException
  {
    Attr attr = this.element.getAttributeNode(name);
    if (attr == null) {
      throw new DOMException((short)8, "No attribute named " + name);
    }
    return this.element.removeAttributeNode(attr);
  }
  
  public Node item(int index)
  {
    return DOMNodeHelper.asDOMAttr(this.element.attribute(index));
  }
  
  public int getLength()
  {
    return this.element.attributeCount();
  }
  
  public Node getNamedItemNS(String namespaceURI, String localName)
  {
    return this.element.getAttributeNodeNS(namespaceURI, localName);
  }
  
  public Node setNamedItemNS(Node arg)
    throws DOMException
  {
    if ((arg instanceof Attr)) {
      return this.element.setAttributeNodeNS((Attr)arg);
    }
    throw new DOMException((short)9, "Node is not an Attr: " + arg);
  }
  
  public Node removeNamedItemNS(String namespaceURI, String localName)
    throws DOMException
  {
    Attr attr = this.element.getAttributeNodeNS(namespaceURI, localName);
    if (attr != null) {
      return this.element.removeAttributeNode(attr);
    }
    return attr;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.dom.DOMAttributeNodeMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */