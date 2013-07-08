package org.dom4j.dom;

import java.util.ArrayList;
import org.dom4j.DocumentFactory;
import org.dom4j.QName;
import org.dom4j.tree.DefaultDocument;
import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.EntityReference;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;

public class DOMDocument
  extends DefaultDocument
  implements Document
{
  private static final DOMDocumentFactory DOCUMENT_FACTORY = (DOMDocumentFactory)DOMDocumentFactory.getInstance();
  
  public DOMDocument()
  {
    init();
  }
  
  public DOMDocument(String name)
  {
    super(name);
    init();
  }
  
  public DOMDocument(DOMElement rootElement)
  {
    super(rootElement);
    init();
  }
  
  public DOMDocument(DOMDocumentType docType)
  {
    super(docType);
    init();
  }
  
  public DOMDocument(DOMElement rootElement, DOMDocumentType docType)
  {
    super(rootElement, docType);
    init();
  }
  
  public DOMDocument(String name, DOMElement rootElement, DOMDocumentType docType)
  {
    super(name, rootElement, docType);
    init();
  }
  
  private void init()
  {
    setDocumentFactory(DOCUMENT_FACTORY);
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
    return "#document";
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
    return null;
  }
  
  public Document getOwnerDocument()
  {
    return null;
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
    if ((nodeType != 1) && (nodeType != 8) && (nodeType != 7) && (nodeType != 10)) {
      throw new DOMException((short)3, "Given node cannot be a child of document");
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
  
  public NodeList getElementsByTagName(String name)
  {
    ArrayList list = new ArrayList();
    DOMNodeHelper.appendElementsByTagName(list, this, name);
    return DOMNodeHelper.createNodeList(list);
  }
  
  public NodeList getElementsByTagNameNS(String namespace, String name)
  {
    ArrayList list = new ArrayList();
    DOMNodeHelper.appendElementsByTagNameNS(list, this, namespace, name);
    return DOMNodeHelper.createNodeList(list);
  }
  
  public DocumentType getDoctype()
  {
    return DOMNodeHelper.asDOMDocumentType(getDocType());
  }
  
  public DOMImplementation getImplementation()
  {
    if ((getDocumentFactory() instanceof DOMImplementation)) {
      return (DOMImplementation)getDocumentFactory();
    }
    return DOCUMENT_FACTORY;
  }
  
  public Element getDocumentElement()
  {
    return DOMNodeHelper.asDOMElement(getRootElement());
  }
  
  public Element createElement(String name)
    throws DOMException
  {
    return (Element)getDocumentFactory().createElement(name);
  }
  
  public DocumentFragment createDocumentFragment()
  {
    DOMNodeHelper.notSupported();
    return null;
  }
  
  public Text createTextNode(String data)
  {
    return (Text)getDocumentFactory().createText(data);
  }
  
  public Comment createComment(String data)
  {
    return (Comment)getDocumentFactory().createComment(data);
  }
  
  public CDATASection createCDATASection(String data)
    throws DOMException
  {
    return (CDATASection)getDocumentFactory().createCDATA(data);
  }
  
  public ProcessingInstruction createProcessingInstruction(String target, String data)
    throws DOMException
  {
    return (ProcessingInstruction)getDocumentFactory().createProcessingInstruction(target, data);
  }
  
  public Attr createAttribute(String name)
    throws DOMException
  {
    QName qname = getDocumentFactory().createQName(name);
    return (Attr)getDocumentFactory().createAttribute(null, qname, "");
  }
  
  public EntityReference createEntityReference(String name)
    throws DOMException
  {
    return (EntityReference)getDocumentFactory().createEntity(name, null);
  }
  
  public Node importNode(Node importedNode, boolean deep)
    throws DOMException
  {
    DOMNodeHelper.notSupported();
    return null;
  }
  
  public Element createElementNS(String namespaceURI, String qualifiedName)
    throws DOMException
  {
    QName qname = getDocumentFactory().createQName(qualifiedName, namespaceURI);
    return (Element)getDocumentFactory().createElement(qname);
  }
  
  public Attr createAttributeNS(String namespaceURI, String qualifiedName)
    throws DOMException
  {
    QName qname = getDocumentFactory().createQName(qualifiedName, namespaceURI);
    return (Attr)getDocumentFactory().createAttribute(null, qname, null);
  }
  
  public Element getElementById(String elementId)
  {
    return DOMNodeHelper.asDOMElement(elementByID(elementId));
  }
  
  protected DocumentFactory getDocumentFactory()
  {
    if (super.getDocumentFactory() == null) {
      return DOCUMENT_FACTORY;
    }
    return super.getDocumentFactory();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.dom.DOMDocument
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */