package org.dom4j.dom;

import org.dom4j.Element;
import org.dom4j.tree.DefaultEntity;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.EntityReference;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMEntityReference
  extends DefaultEntity
  implements EntityReference
{
  public DOMEntityReference(String name)
  {
    super(name);
  }
  
  public DOMEntityReference(String name, String text)
  {
    super(name, text);
  }
  
  public DOMEntityReference(Element parent, String name, String text)
  {
    super(parent, name, text);
  }
  
  public boolean supports(String feature, String version)
  {
    return DOMNodeHelper.supports(this, feature, version);
  }
  
  public String getNamespaceURI()
  {
    return DOMNodeHelper.getNamespaceURI(this);
  }
  
  public String getPrefix()
  {
    return DOMNodeHelper.getPrefix(this);
  }
  
  public void setPrefix(String prefix)
    throws DOMException
  {
    DOMNodeHelper.setPrefix(this, prefix);
  }
  
  public String getLocalName()
  {
    return DOMNodeHelper.getLocalName(this);
  }
  
  public String getNodeName()
  {
    return getName();
  }
  
  public String getNodeValue()
    throws DOMException
  {
    return null;
  }
  
  public void setNodeValue(String nodeValue)
    throws DOMException
  {}
  
  public Node getParentNode()
  {
    return DOMNodeHelper.getParentNode(this);
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
    if ((nodeType != 1) && (nodeType != 3) && (nodeType != 8) && (nodeType != 7) && (nodeType != 4) && (nodeType != 5)) {
      throw new DOMException((short)3, "Given node cannot be a child of an entity reference");
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
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.dom.DOMEntityReference
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */