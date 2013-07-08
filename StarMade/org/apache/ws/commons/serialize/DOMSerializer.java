package org.apache.ws.commons.serialize;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.helpers.AttributesImpl;

public class DOMSerializer
{
  private boolean namespaceDeclarationAttribute;
  private boolean parentsNamespaceDeclarationDisabled;
  private boolean startingDocument = true;
  
  public void setNamespaceDeclarationAttribute(boolean pXmlDeclarationAttribute)
  {
    this.namespaceDeclarationAttribute = pXmlDeclarationAttribute;
  }
  
  public boolean isNamespaceDeclarationAttribute()
  {
    return this.namespaceDeclarationAttribute;
  }
  
  public void setParentsNamespaceDeclarationDisabled(boolean pParentsXmlDeclarationDisabled)
  {
    this.parentsNamespaceDeclarationDisabled = pParentsXmlDeclarationDisabled;
  }
  
  public boolean isParentsNamespaceDeclarationDisabled()
  {
    return this.parentsNamespaceDeclarationDisabled;
  }
  
  public boolean isStartingDocument()
  {
    return this.startingDocument;
  }
  
  public void setStartingDocument(boolean pStartingDocument)
  {
    this.startingDocument = pStartingDocument;
  }
  
  protected void doSerializeChilds(Node pNode, ContentHandler pHandler)
    throws SAXException
  {
    for (Node child = pNode.getFirstChild(); child != null; child = child.getNextSibling()) {
      doSerialize(child, pHandler);
    }
  }
  
  private void parentsStartPrefixMappingEvents(Node pNode, ContentHandler pHandler)
    throws SAXException
  {
    if (pNode != null)
    {
      parentsStartPrefixMappingEvents(pNode.getParentNode(), pHandler);
      if (pNode.getNodeType() == 1) {
        startPrefixMappingEvents(pNode, pHandler);
      }
    }
  }
  
  private void parentsEndPrefixMappingEvents(Node pNode, ContentHandler pHandler)
    throws SAXException
  {
    if (pNode != null)
    {
      if (pNode.getNodeType() == 1) {
        endPrefixMappingEvents(pNode, pHandler);
      }
      parentsEndPrefixMappingEvents(pNode.getParentNode(), pHandler);
    }
  }
  
  private void startPrefixMappingEvents(Node pNode, ContentHandler pHandler)
    throws SAXException
  {
    NamedNodeMap nnm = pNode.getAttributes();
    if (nnm != null) {
      for (int local_i = 0; local_i < nnm.getLength(); local_i++)
      {
        Node attr = nnm.item(local_i);
        if ("http://www.w3.org/2000/xmlns/".equals(attr.getNamespaceURI()))
        {
          String prefix;
          if ("xmlns".equals(attr.getPrefix()))
          {
            prefix = attr.getLocalName();
          }
          else
          {
            String prefix;
            if ("xmlns".equals(attr.getNodeName())) {
              prefix = "";
            } else {
              throw new IllegalStateException("Unable to parse namespace declaration: " + attr.getNodeName());
            }
          }
          String prefix;
          String uri = attr.getNodeValue();
          if (uri == null) {
            uri = "";
          }
          pHandler.startPrefixMapping(prefix, uri);
        }
      }
    }
  }
  
  private void endPrefixMappingEvents(Node pNode, ContentHandler pHandler)
    throws SAXException
  {
    NamedNodeMap nnm = pNode.getAttributes();
    if (nnm != null) {
      for (int local_i = nnm.getLength() - 1; local_i >= 0; local_i--)
      {
        Node attr = nnm.item(local_i);
        if ("http://www.w3.org/2000/xmlns/".equals(attr.getNamespaceURI()))
        {
          String prefix = attr.getLocalName();
          pHandler.endPrefixMapping(prefix);
        }
      }
    }
  }
  
  private void characters(ContentHandler pHandler, String pValue, boolean pCdata)
    throws SAXException
  {
    LexicalHandler local_lh;
    LexicalHandler local_lh;
    if (pCdata) {
      local_lh = (pHandler instanceof LexicalHandler) ? (LexicalHandler)pHandler : null;
    } else {
      local_lh = null;
    }
    if (local_lh != null) {
      local_lh.startCDATA();
    }
    pHandler.characters(pValue.toCharArray(), 0, pValue.length());
    if (local_lh != null) {
      local_lh.endCDATA();
    }
  }
  
  public void serialize(Node pNode, ContentHandler pHandler)
    throws SAXException
  {
    if ((!isNamespaceDeclarationAttribute()) && (!isParentsNamespaceDeclarationDisabled())) {
      parentsStartPrefixMappingEvents(pNode.getParentNode(), pHandler);
    }
    doSerialize(pNode, pHandler);
    if ((!isNamespaceDeclarationAttribute()) && (!isParentsNamespaceDeclarationDisabled())) {
      parentsEndPrefixMappingEvents(pNode.getParentNode(), pHandler);
    }
  }
  
  protected void doSerialize(Node pNode, ContentHandler pHandler)
    throws SAXException
  {
    switch (pNode.getNodeType())
    {
    case 9: 
      boolean startDocumentEvent = isStartingDocument();
      if (startDocumentEvent) {
        pHandler.startDocument();
      }
      doSerializeChilds(pNode, pHandler);
      if (startDocumentEvent) {
        pHandler.endDocument();
      }
      break;
    case 11: 
      doSerializeChilds(pNode, pHandler);
      break;
    case 1: 
      AttributesImpl attr = new AttributesImpl();
      boolean isNamespaceDeclarationAttribute = isNamespaceDeclarationAttribute();
      if (!isNamespaceDeclarationAttribute) {
        startPrefixMappingEvents(pNode, pHandler);
      }
      NamedNodeMap nnm = pNode.getAttributes();
      if (nnm != null) {
        for (int local_i = 0; local_i < nnm.getLength(); local_i++)
        {
          Node local_a = nnm.item(local_i);
          if ((isNamespaceDeclarationAttribute) || (!"http://www.w3.org/2000/xmlns/".equals(local_a.getNamespaceURI())))
          {
            String aUri = local_a.getNamespaceURI();
            String aLocalName = local_a.getLocalName();
            String aNodeName = local_a.getNodeName();
            if (aLocalName == null) {
              if ((aUri == null) || (aUri.length() == 0)) {
                aLocalName = aNodeName;
              } else {
                throw new IllegalStateException("aLocalName is null");
              }
            }
            attr.addAttribute(aUri == null ? "" : aUri, aNodeName, aLocalName, "CDATA", local_a.getNodeValue());
          }
        }
      }
      String local_i = pNode.getNamespaceURI();
      if (local_i == null) {
        local_i = "";
      }
      pHandler.startElement(local_i, pNode.getLocalName(), pNode.getNodeName(), attr);
      doSerializeChilds(pNode, pHandler);
      pHandler.endElement(local_i, pNode.getLocalName(), pNode.getNodeName());
      if (!isNamespaceDeclarationAttribute) {
        endPrefixMappingEvents(pNode, pHandler);
      }
      break;
    case 3: 
      characters(pHandler, pNode.getNodeValue(), false);
      break;
    case 4: 
      characters(pHandler, pNode.getNodeValue(), true);
      break;
    case 7: 
      pHandler.processingInstruction(pNode.getNodeName(), pNode.getNodeValue());
      break;
    case 5: 
      pHandler.skippedEntity(pNode.getNodeName());
      break;
    case 8: 
      if ((pHandler instanceof LexicalHandler))
      {
        String local_a = pNode.getNodeValue();
        ((LexicalHandler)pHandler).comment(local_a.toCharArray(), 0, local_a.length());
      }
      break;
    case 2: 
    case 6: 
    case 10: 
    default: 
      throw new IllegalStateException("Unknown node type: " + pNode.getNodeType());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.apache.ws.commons.serialize.DOMSerializer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */