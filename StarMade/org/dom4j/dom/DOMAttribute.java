package org.dom4j.dom;

import org.dom4j.QName;
import org.dom4j.tree.DefaultAttribute;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMAttribute
  extends DefaultAttribute
  implements Attr
{
  public DOMAttribute(QName qname)
  {
    super(qname);
  }
  
  public DOMAttribute(QName qname, String value)
  {
    super(qname, value);
  }
  
  public DOMAttribute(org.dom4j.Element parent, QName qname, String value)
  {
    super(parent, qname, value);
  }
  
  public boolean supports(String feature, String version)
  {
    return DOMNodeHelper.supports(this, feature, version);
  }
  
  public String getNamespaceURI()
  {
    return getQName().getNamespaceURI();
  }
  
  public String getPrefix()
  {
    return getQName().getNamespacePrefix();
  }
  
  public void setPrefix(String prefix)
    throws DOMException
  {
    DOMNodeHelper.setPrefix(this, prefix);
  }
  
  public String getLocalName()
  {
    return getQName().getName();
  }
  
  public String getNodeName()
  {
    return getName();
  }
  
  public String getNodeValue()
    throws DOMException
  {
    return DOMNodeHelper.getNodeValue(this);
  }
  
  public void setNodeValue(String nodeValue)
    throws DOMException
  {
    DOMNodeHelper.setNodeValue(this, nodeValue);
  }
  
  public Node getParentNode()
  {
    return null;
  }
  
  public NodeList getChildNodes()
  {
    return DOMNodeHelper.getChildNodes(this);
  }
  
  public Node getFirstChild()
  {
    return DOMNodeHelper.getFirstChild(this);
  }
  
  public Node getLastChild()
  {
    return DOMNodeHelper.getLastChild(this);
  }
  
  public Node getPreviousSibling()
  {
    return DOMNodeHelper.getPreviousSibling(this);
  }
  
  public Node getNextSibling()
  {
    return DOMNodeHelper.getNextSibling(this);
  }
  
  public NamedNodeMap getAttributes()
  {
    return null;
  }
  
  public Document getOwnerDocument()
  {
    return DOMNodeHelper.getOwnerDocument(this);
  }
  
  public Node insertBefore(Node newChild, Node refChild)
    throws DOMException
  {
    checkNewChildNode(newChild);
    return DOMNodeHelper.insertBefore(this, newChild, refChild);
  }
  
  public Node replaceChild(Node newChild, Node oldChild)
    throws DOMException
  {
    checkNewChildNode(newChild);
    return DOMNodeHelper.replaceChild(this, newChild, oldChild);
  }
  
  public Node removeChild(Node oldChild)
    throws DOMException
  {
    return DOMNodeHelper.removeChild(this, oldChild);
  }
  
  public Node appendChild(Node newChild)
    throws DOMException
  {
    checkNewChildNode(newChild);
    return DOMNodeHelper.appendChild(this, newChild);
  }
  
  private void checkNewChildNode(Node newChild)
    throws DOMException
  {
    int nodeType = newChild.getNodeType();
    if ((nodeType != 3) && (nodeType != 5)) {
      throw new DOMException((short)3, "The node cannot be a child of attribute");
    }
  }
  
  public boolean hasChildNodes()
  {
    return DOMNodeHelper.hasChildNodes(this);
  }
  
  public Node cloneNode(boolean deep)
  {
    return DOMNodeHelper.cloneNode(this, deep);
  }
  
  public void normalize()
  {
    DOMNodeHelper.normalize(this);
  }
  
  public boolean isSupported(String feature, String version)
  {
    return DOMNodeHelper.isSupported(this, feature, version);
  }
  
  public boolean hasAttributes()
  {
    return DOMNodeHelper.hasAttributes(this);
  }
  
  public boolean getSpecified()
  {
    return true;
  }
  
  public org.w3c.dom.Element getOwnerElement()
  {
    return DOMNodeHelper.asDOMElement(getParent());
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.dom.DOMAttribute
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */