package org.dom4j.dom;

import org.dom4j.tree.DefaultDocumentType;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMDocumentType
  extends DefaultDocumentType
  implements DocumentType
{
  public DOMDocumentType() {}
  
  public DOMDocumentType(String elementName, String systemID)
  {
    super(elementName, systemID);
  }
  
  public DOMDocumentType(String name, String publicID, String systemID)
  {
    super(name, publicID, systemID);
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
    throw new DOMException((short)3, "DocumentType nodes cannot have children");
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
    return getPublicID();
  }
  
  public String getSystemId()
  {
    return getSystemID();
  }
  
  public String getInternalSubset()
  {
    return getElementName();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.dom.DOMDocumentType
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */