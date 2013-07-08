package org.apache.ws.commons.serialize;

import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.EntityReference;
import org.w3c.dom.Node;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

public class DOMBuilder
  implements ContentHandler
{
  private Document document;
  private Node target;
  private Node currentNode;
  private Locator locator;
  private boolean prefixMappingIsAttribute;
  private List prefixes;
  
  public boolean isPrefixMappingIsAttribute()
  {
    return this.prefixMappingIsAttribute;
  }
  
  public void setPrefixMappingIsAttribute(boolean pPrefixMappingIsAttribute)
  {
    this.prefixMappingIsAttribute = pPrefixMappingIsAttribute;
  }
  
  public void setDocument(Document pDocument)
  {
    this.document = pDocument;
  }
  
  public Document getDocument()
  {
    return this.document;
  }
  
  public void setDocumentLocator(Locator pLocator)
  {
    this.locator = pLocator;
  }
  
  public Locator getDocumentLocator()
  {
    return this.locator;
  }
  
  public void setTarget(Node pNode)
  {
    this.target = pNode;
    this.currentNode = pNode;
    if (getDocument() == null) {
      setDocument(pNode.getNodeType() == 9 ? (Document)pNode : pNode.getOwnerDocument());
    }
  }
  
  public Node getTarget()
  {
    return this.target;
  }
  
  public void startDocument()
    throws SAXException
  {}
  
  public void endDocument()
    throws SAXException
  {}
  
  public void startPrefixMapping(String prefix, String uri)
    throws SAXException
  {
    if (isPrefixMappingIsAttribute())
    {
      if (this.prefixes == null) {
        this.prefixes = new ArrayList();
      }
      this.prefixes.add(prefix);
      this.prefixes.add(uri);
    }
  }
  
  public void endPrefixMapping(String prefix)
    throws SAXException
  {}
  
  public void startElement(String pNamespaceURI, String pLocalName, String pQName, Attributes pAttr)
    throws SAXException
  {
    Document doc = getDocument();
    Element element;
    Element element;
    if ((pNamespaceURI == null) || (pNamespaceURI.length() == 0)) {
      element = doc.createElement(pQName);
    } else {
      element = doc.createElementNS(pNamespaceURI, pQName);
    }
    if (pAttr != null) {
      for (int local_i = 0; local_i < pAttr.getLength(); local_i++)
      {
        String uri = pAttr.getURI(local_i);
        String qName = pAttr.getQName(local_i);
        String value = pAttr.getValue(local_i);
        if ((uri == null) || (uri.length() == 0)) {
          element.setAttribute(qName, value);
        } else {
          element.setAttributeNS(uri, qName, value);
        }
      }
    }
    if (this.prefixes != null)
    {
      for (int local_i = 0; local_i < this.prefixes.size(); local_i += 2)
      {
        String uri = (String)this.prefixes.get(local_i);
        String qName = (String)this.prefixes.get(local_i + 1);
        if ((uri == null) || ("".equals(uri))) {
          element.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns", qName);
        } else {
          element.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:" + uri, qName);
        }
      }
      this.prefixes.clear();
    }
    this.currentNode.appendChild(element);
    this.currentNode = element;
  }
  
  public void endElement(String namespaceURI, String localName, String qName)
    throws SAXException
  {
    this.currentNode = this.currentNode.getParentNode();
  }
  
  public void characters(char[] local_ch, int start, int length)
    throws SAXException
  {
    Node node = this.currentNode.getLastChild();
    String local_s = new String(local_ch, start, length);
    if ((node != null) && (node.getNodeType() == 3))
    {
      ((Text)node).appendData(local_s);
    }
    else
    {
      Text text = getDocument().createTextNode(local_s);
      this.currentNode.appendChild(text);
    }
  }
  
  public void ignorableWhitespace(char[] local_ch, int start, int length)
    throws SAXException
  {
    characters(local_ch, start, length);
  }
  
  public void processingInstruction(String pTarget, String pData)
    throws SAXException
  {
    ProcessingInstruction local_pi = getDocument().createProcessingInstruction(pTarget, pData);
    this.currentNode.appendChild(local_pi);
  }
  
  public void skippedEntity(String pName)
    throws SAXException
  {
    EntityReference entity = getDocument().createEntityReference(pName);
    this.currentNode.appendChild(entity);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.apache.ws.commons.serialize.DOMBuilder
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */