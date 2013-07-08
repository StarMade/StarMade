package org.dom4j.io;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import org.dom4j.Branch;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;
import org.dom4j.tree.NamespaceStack;
import org.w3c.dom.DocumentType;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMReader
{
  private DocumentFactory factory;
  private NamespaceStack namespaceStack;
  
  public DOMReader()
  {
    this.factory = DocumentFactory.getInstance();
    this.namespaceStack = new NamespaceStack(this.factory);
  }
  
  public DOMReader(DocumentFactory factory)
  {
    this.factory = factory;
    this.namespaceStack = new NamespaceStack(factory);
  }
  
  public DocumentFactory getDocumentFactory()
  {
    return this.factory;
  }
  
  public void setDocumentFactory(DocumentFactory docFactory)
  {
    this.factory = docFactory;
    this.namespaceStack.setDocumentFactory(this.factory);
  }
  
  public org.dom4j.Document read(org.w3c.dom.Document domDocument)
  {
    if ((domDocument instanceof org.dom4j.Document)) {
      return (org.dom4j.Document)domDocument;
    }
    org.dom4j.Document document = createDocument();
    clearNamespaceStack();
    NodeList nodeList = domDocument.getChildNodes();
    int local_i = 0;
    int size = nodeList.getLength();
    while (local_i < size)
    {
      readTree(nodeList.item(local_i), document);
      local_i++;
    }
    return document;
  }
  
  protected void readTree(Node node, Branch current)
  {
    Element element = null;
    org.dom4j.Document document = null;
    if ((current instanceof Element)) {
      element = (Element)current;
    } else {
      document = (org.dom4j.Document)current;
    }
    switch (node.getNodeType())
    {
    case 1: 
      readElement(node, current);
      break;
    case 7: 
      if ((current instanceof Element))
      {
        Element currentEl = (Element)current;
        currentEl.addProcessingInstruction(node.getNodeName(), node.getNodeValue());
      }
      else
      {
        org.dom4j.Document currentEl = (org.dom4j.Document)current;
        currentEl.addProcessingInstruction(node.getNodeName(), node.getNodeValue());
      }
      break;
    case 8: 
      if ((current instanceof Element)) {
        ((Element)current).addComment(node.getNodeValue());
      } else {
        ((org.dom4j.Document)current).addComment(node.getNodeValue());
      }
      break;
    case 10: 
      DocumentType currentEl = (DocumentType)node;
      document.addDocType(currentEl.getName(), currentEl.getPublicId(), currentEl.getSystemId());
      break;
    case 3: 
      element.addText(node.getNodeValue());
      break;
    case 4: 
      element.addCDATA(node.getNodeValue());
      break;
    case 5: 
      Node firstChild = node.getFirstChild();
      if (firstChild != null) {
        element.addEntity(node.getNodeName(), firstChild.getNodeValue());
      } else {
        element.addEntity(node.getNodeName(), "");
      }
      break;
    case 6: 
      element.addEntity(node.getNodeName(), node.getNodeValue());
      break;
    case 2: 
    case 9: 
    default: 
      System.out.println("WARNING: Unknown DOM node type: " + node.getNodeType());
    }
  }
  
  protected void readElement(Node node, Branch current)
  {
    int previouslyDeclaredNamespaces = this.namespaceStack.size();
    String namespaceUri = node.getNamespaceURI();
    String elementPrefix = node.getPrefix();
    if (elementPrefix == null) {
      elementPrefix = "";
    }
    NamedNodeMap attributeList = node.getAttributes();
    if ((attributeList != null) && (namespaceUri == null))
    {
      Node attribute = attributeList.getNamedItem("xmlns");
      if (attribute != null)
      {
        namespaceUri = attribute.getNodeValue();
        elementPrefix = "";
      }
    }
    QName attribute = this.namespaceStack.getQName(namespaceUri, node.getLocalName(), node.getNodeName());
    Element element = current.addElement(attribute);
    if (attributeList != null)
    {
      int size = attributeList.getLength();
      List attributes = new ArrayList(size);
      for (int local_i = 0; local_i < size; local_i++)
      {
        Node attribute = attributeList.item(local_i);
        String name = attribute.getNodeName();
        if (name.startsWith("xmlns"))
        {
          String prefix = getPrefix(name);
          String uri = attribute.getNodeValue();
          Namespace namespace = this.namespaceStack.addNamespace(prefix, uri);
          element.add(namespace);
        }
        else
        {
          attributes.add(attribute);
        }
      }
      size = attributes.size();
      for (int local_i = 0; local_i < size; local_i++)
      {
        Node attribute = (Node)attributes.get(local_i);
        QName name = this.namespaceStack.getQName(attribute.getNamespaceURI(), attribute.getLocalName(), attribute.getNodeName());
        element.addAttribute(name, attribute.getNodeValue());
      }
    }
    NodeList size = node.getChildNodes();
    int attributes = 0;
    int local_i = size.getLength();
    while (attributes < local_i)
    {
      Node attribute = size.item(attributes);
      readTree(attribute, element);
      attributes++;
    }
    while (this.namespaceStack.size() > previouslyDeclaredNamespaces) {
      this.namespaceStack.pop();
    }
  }
  
  protected Namespace getNamespace(String prefix, String uri)
  {
    return getDocumentFactory().createNamespace(prefix, uri);
  }
  
  protected org.dom4j.Document createDocument()
  {
    return getDocumentFactory().createDocument();
  }
  
  protected void clearNamespaceStack()
  {
    this.namespaceStack.clear();
    if (!this.namespaceStack.contains(Namespace.XML_NAMESPACE)) {
      this.namespaceStack.push(Namespace.XML_NAMESPACE);
    }
  }
  
  private String getPrefix(String xmlnsDecl)
  {
    int index = xmlnsDecl.indexOf(':', 5);
    if (index != -1) {
      return xmlnsDecl.substring(index + 1);
    }
    return "";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.io.DOMReader
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */