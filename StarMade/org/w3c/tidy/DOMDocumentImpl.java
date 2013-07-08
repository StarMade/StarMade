package org.w3c.tidy;

import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.EntityReference;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;

public class DOMDocumentImpl
  extends DOMNodeImpl
  implements Document
{
  private TagTable field_372 = new TagTable();
  
  protected DOMDocumentImpl(Node paramNode)
  {
    super(paramNode);
  }
  
  public String getNodeName()
  {
    return "#document";
  }
  
  public short getNodeType()
  {
    return 9;
  }
  
  public DocumentType getDoctype()
  {
    for (Node localNode = this.adaptee.content; (localNode != null) && (localNode.type != 1); localNode = localNode.next) {}
    if (localNode != null) {
      return (DocumentType)localNode.getAdapter();
    }
    return null;
  }
  
  public DOMImplementation getImplementation()
  {
    throw new DOMException((short)9, "DOM method not supported");
  }
  
  public Element getDocumentElement()
  {
    for (Node localNode = this.adaptee.content; (localNode != null) && (localNode.type != 5) && (localNode.type != 7); localNode = localNode.next) {}
    if (localNode != null) {
      return (Element)localNode.getAdapter();
    }
    return null;
  }
  
  public Element createElement(String paramString)
    throws DOMException
  {
    Node localNode = new Node((short)7, null, 0, 0, paramString, this.field_372);
    if (localNode != null)
    {
      if (localNode.tag == null) {
        localNode.tag = TagTable.XML_TAGS;
      }
      return (Element)localNode.getAdapter();
    }
    return null;
  }
  
  public DocumentFragment createDocumentFragment()
  {
    throw new DOMException((short)9, "DOM method not supported");
  }
  
  public Text createTextNode(String paramString)
  {
    byte[] arrayOfByte = TidyUtils.getBytes(paramString);
    Node localNode = new Node((short)4, arrayOfByte, 0, arrayOfByte.length);
    if (localNode != null) {
      return (Text)localNode.getAdapter();
    }
    return null;
  }
  
  public Comment createComment(String paramString)
  {
    byte[] arrayOfByte = TidyUtils.getBytes(paramString);
    Node localNode = new Node((short)2, arrayOfByte, 0, arrayOfByte.length);
    if (localNode != null) {
      return (Comment)localNode.getAdapter();
    }
    return null;
  }
  
  public CDATASection createCDATASection(String paramString)
    throws DOMException
  {
    throw new DOMException((short)9, "HTML document");
  }
  
  public ProcessingInstruction createProcessingInstruction(String paramString1, String paramString2)
    throws DOMException
  {
    throw new DOMException((short)9, "HTML document");
  }
  
  public Attr createAttribute(String paramString)
    throws DOMException
  {
    AttVal localAttVal = new AttVal(null, null, 34, paramString, null);
    if (localAttVal != null)
    {
      localAttVal.dict = AttributeTable.getDefaultAttributeTable().findAttribute(localAttVal);
      return localAttVal.getAdapter();
    }
    return null;
  }
  
  public EntityReference createEntityReference(String paramString)
    throws DOMException
  {
    throw new DOMException((short)9, "createEntityReference not supported");
  }
  
  public NodeList getElementsByTagName(String paramString)
  {
    return new DOMNodeListByTagNameImpl(this.adaptee, paramString);
  }
  
  public org.w3c.dom.Node importNode(org.w3c.dom.Node paramNode, boolean paramBoolean)
    throws DOMException
  {
    throw new DOMException((short)9, "importNode not supported");
  }
  
  public Attr createAttributeNS(String paramString1, String paramString2)
    throws DOMException
  {
    throw new DOMException((short)9, "createAttributeNS not supported");
  }
  
  public Element createElementNS(String paramString1, String paramString2)
    throws DOMException
  {
    throw new DOMException((short)9, "createElementNS not supported");
  }
  
  public NodeList getElementsByTagNameNS(String paramString1, String paramString2)
  {
    throw new DOMException((short)9, "getElementsByTagNameNS not supported");
  }
  
  public Element getElementById(String paramString)
  {
    return null;
  }
  
  public org.w3c.dom.Node adoptNode(org.w3c.dom.Node paramNode)
    throws DOMException
  {
    throw new DOMException((short)9, "DOM method not supported");
  }
  
  public String getDocumentURI()
  {
    return null;
  }
  
  public DOMConfiguration getDomConfig()
  {
    return null;
  }
  
  public String getInputEncoding()
  {
    return null;
  }
  
  public boolean getStrictErrorChecking()
  {
    return true;
  }
  
  public String getXmlEncoding()
  {
    return null;
  }
  
  public boolean getXmlStandalone()
  {
    return false;
  }
  
  public String getXmlVersion()
  {
    return "1.0";
  }
  
  public void normalizeDocument() {}
  
  public org.w3c.dom.Node renameNode(org.w3c.dom.Node paramNode, String paramString1, String paramString2)
    throws DOMException
  {
    throw new DOMException((short)9, "DOM method not supported");
  }
  
  public void setDocumentURI(String paramString) {}
  
  public void setStrictErrorChecking(boolean paramBoolean) {}
  
  public void setXmlStandalone(boolean paramBoolean)
    throws DOMException
  {}
  
  public void setXmlVersion(String paramString)
    throws DOMException
  {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.w3c.tidy.DOMDocumentImpl
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */