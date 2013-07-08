package org.dom4j.dom;

import java.util.ArrayList;
import java.util.List;
import org.dom4j.Attribute;
import org.dom4j.DocumentFactory;
import org.dom4j.Namespace;
import org.dom4j.QName;
import org.dom4j.tree.DefaultElement;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMElement
  extends DefaultElement
  implements Element
{
  private static final DocumentFactory DOCUMENT_FACTORY = ;
  
  public DOMElement(String name)
  {
    super(name);
  }
  
  public DOMElement(QName qname)
  {
    super(qname);
  }
  
  public DOMElement(QName qname, int attributeCount)
  {
    super(qname, attributeCount);
  }
  
  public DOMElement(String name, Namespace namespace)
  {
    super(name, namespace);
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
    return DOMNodeHelper.createNodeList(content());
  }
  
  public Node getFirstChild()
  {
    return DOMNodeHelper.asDOMNode(node(0));
  }
  
  public Node getLastChild()
  {
    return DOMNodeHelper.asDOMNode(node(nodeCount() - 1));
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
    return new DOMAttributeNodeMap(this);
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
      throw new DOMException((short)3, "Given node cannot be a child of element");
    }
  }
  
  public boolean hasChildNodes()
  {
    return nodeCount() > 0;
  }
  
  public Node cloneNode(boolean deep)
  {
    return DOMNodeHelper.cloneNode(this, deep);
  }
  
  public boolean isSupported(String feature, String version)
  {
    return DOMNodeHelper.isSupported(this, feature, version);
  }
  
  public boolean hasAttributes()
  {
    return DOMNodeHelper.hasAttributes(this);
  }
  
  public String getTagName()
  {
    return getName();
  }
  
  public String getAttribute(String name)
  {
    String answer = attributeValue(name);
    return answer != null ? answer : "";
  }
  
  public void setAttribute(String name, String value)
    throws DOMException
  {
    addAttribute(name, value);
  }
  
  public void removeAttribute(String name)
    throws DOMException
  {
    Attribute attribute = attribute(name);
    if (attribute != null) {
      remove(attribute);
    }
  }
  
  public Attr getAttributeNode(String name)
  {
    return DOMNodeHelper.asDOMAttr(attribute(name));
  }
  
  public Attr setAttributeNode(Attr newAttr)
    throws DOMException
  {
    if (isReadOnly()) {
      throw new DOMException((short)7, "No modification allowed");
    }
    Attribute attribute = attribute(newAttr);
    if (attribute != newAttr)
    {
      if (newAttr.getOwnerElement() != null) {
        throw new DOMException((short)10, "Attribute is already in use");
      }
      Attribute newAttribute = createAttribute(newAttr);
      if (attribute != null) {
        attribute.detach();
      }
      add(newAttribute);
    }
    return DOMNodeHelper.asDOMAttr(attribute);
  }
  
  public Attr removeAttributeNode(Attr oldAttr)
    throws DOMException
  {
    Attribute attribute = attribute(oldAttr);
    if (attribute != null)
    {
      attribute.detach();
      return DOMNodeHelper.asDOMAttr(attribute);
    }
    throw new DOMException((short)8, "No such attribute");
  }
  
  public String getAttributeNS(String namespaceURI, String localName)
  {
    Attribute attribute = attribute(namespaceURI, localName);
    if (attribute != null)
    {
      String answer = attribute.getValue();
      if (answer != null) {
        return answer;
      }
    }
    return "";
  }
  
  public void setAttributeNS(String namespaceURI, String qualifiedName, String value)
    throws DOMException
  {
    Attribute attribute = attribute(namespaceURI, qualifiedName);
    if (attribute != null)
    {
      attribute.setValue(value);
    }
    else
    {
      QName qname = getQName(namespaceURI, qualifiedName);
      addAttribute(qname, value);
    }
  }
  
  public void removeAttributeNS(String namespaceURI, String localName)
    throws DOMException
  {
    Attribute attribute = attribute(namespaceURI, localName);
    if (attribute != null) {
      remove(attribute);
    }
  }
  
  public Attr getAttributeNodeNS(String namespaceURI, String localName)
  {
    Attribute attribute = attribute(namespaceURI, localName);
    if (attribute != null) {
      DOMNodeHelper.asDOMAttr(attribute);
    }
    return null;
  }
  
  public Attr setAttributeNodeNS(Attr newAttr)
    throws DOMException
  {
    Attribute attribute = attribute(newAttr.getNamespaceURI(), newAttr.getLocalName());
    if (attribute != null)
    {
      attribute.setValue(newAttr.getValue());
    }
    else
    {
      attribute = createAttribute(newAttr);
      add(attribute);
    }
    return DOMNodeHelper.asDOMAttr(attribute);
  }
  
  public NodeList getElementsByTagName(String name)
  {
    ArrayList list = new ArrayList();
    DOMNodeHelper.appendElementsByTagName(list, this, name);
    return DOMNodeHelper.createNodeList(list);
  }
  
  public NodeList getElementsByTagNameNS(String namespace, String lName)
  {
    ArrayList list = new ArrayList();
    DOMNodeHelper.appendElementsByTagNameNS(list, this, namespace, lName);
    return DOMNodeHelper.createNodeList(list);
  }
  
  public boolean hasAttribute(String name)
  {
    return attribute(name) != null;
  }
  
  public boolean hasAttributeNS(String namespaceURI, String localName)
  {
    return attribute(namespaceURI, localName) != null;
  }
  
  protected DocumentFactory getDocumentFactory()
  {
    DocumentFactory factory = getQName().getDocumentFactory();
    return factory != null ? factory : DOCUMENT_FACTORY;
  }
  
  protected Attribute attribute(Attr attr)
  {
    return attribute(DOCUMENT_FACTORY.createQName(attr.getLocalName(), attr.getPrefix(), attr.getNamespaceURI()));
  }
  
  protected Attribute attribute(String namespaceURI, String localName)
  {
    List attributes = attributeList();
    int size = attributes.size();
    for (int local_i = 0; local_i < size; local_i++)
    {
      Attribute attribute = (Attribute)attributes.get(local_i);
      if ((localName.equals(attribute.getName())) && (((namespaceURI != null) && (namespaceURI.length() != 0)) || ((attribute.getNamespaceURI() == null) || (attribute.getNamespaceURI().length() == 0) || ((namespaceURI != null) && (namespaceURI.equals(attribute.getNamespaceURI())))))) {
        return attribute;
      }
    }
    return null;
  }
  
  protected Attribute createAttribute(Attr newAttr)
  {
    QName qname = null;
    String name = newAttr.getLocalName();
    if (name != null)
    {
      String prefix = newAttr.getPrefix();
      String uri = newAttr.getNamespaceURI();
      qname = getDocumentFactory().createQName(name, prefix, uri);
    }
    else
    {
      name = newAttr.getName();
      qname = getDocumentFactory().createQName(name);
    }
    return new DOMAttribute(qname, newAttr.getValue());
  }
  
  protected QName getQName(String namespace, String qualifiedName)
  {
    int index = qualifiedName.indexOf(':');
    String prefix = "";
    String localName = qualifiedName;
    if (index >= 0)
    {
      prefix = qualifiedName.substring(0, index);
      localName = qualifiedName.substring(index + 1);
    }
    return getDocumentFactory().createQName(localName, prefix, namespace);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.dom.DOMElement
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */