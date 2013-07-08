package org.w3c.tidy;

import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.TypeInfo;

public class DOMAttrImpl
  extends DOMNodeImpl
  implements Attr, Cloneable
{
  protected AttVal avAdaptee;
  
  protected DOMAttrImpl(AttVal paramAttVal)
  {
    super(null);
    this.avAdaptee = paramAttVal;
  }
  
  public String getNodeValue()
    throws DOMException
  {
    return getValue();
  }
  
  public void setNodeValue(String paramString)
    throws DOMException
  {
    setValue(paramString);
  }
  
  public String getNodeName()
  {
    return getName();
  }
  
  public short getNodeType()
  {
    return 2;
  }
  
  public String getName()
  {
    return this.avAdaptee.attribute;
  }
  
  public boolean getSpecified()
  {
    return this.avAdaptee.value != null;
  }
  
  public String getValue()
  {
    return this.avAdaptee.value == null ? this.avAdaptee.attribute : this.avAdaptee.value;
  }
  
  public void setValue(String paramString)
  {
    this.avAdaptee.value = paramString;
  }
  
  public Node getParentNode()
  {
    return null;
  }
  
  public NodeList getChildNodes()
  {
    return new DOMNodeListImpl(null);
  }
  
  public Node getFirstChild()
  {
    return null;
  }
  
  public Node getLastChild()
  {
    return null;
  }
  
  public Node getPreviousSibling()
  {
    return null;
  }
  
  public Node getNextSibling()
  {
    return null;
  }
  
  public NamedNodeMap getAttributes()
  {
    return null;
  }
  
  public Document getOwnerDocument()
  {
    return null;
  }
  
  public Node insertBefore(Node paramNode1, Node paramNode2)
    throws DOMException
  {
    throw new DOMException((short)7, "Not supported");
  }
  
  public Node replaceChild(Node paramNode1, Node paramNode2)
    throws DOMException
  {
    throw new DOMException((short)7, "Not supported");
  }
  
  public Node removeChild(Node paramNode)
    throws DOMException
  {
    throw new DOMException((short)7, "Not supported");
  }
  
  public Node appendChild(Node paramNode)
    throws DOMException
  {
    throw new DOMException((short)7, "Not supported");
  }
  
  public boolean hasChildNodes()
  {
    return false;
  }
  
  public Node cloneNode(boolean paramBoolean)
  {
    return (Node)clone();
  }
  
  public Element getOwnerElement()
  {
    return null;
  }
  
  public TypeInfo getSchemaTypeInfo()
  {
    return null;
  }
  
  public boolean isId()
  {
    return "id".equals(this.avAdaptee.getAttribute());
  }
  
  protected Object clone()
  {
    DOMAttrImpl localDOMAttrImpl;
    try
    {
      localDOMAttrImpl = (DOMAttrImpl)super.clone();
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      throw new RuntimeException("Clone not supported");
    }
    localDOMAttrImpl.avAdaptee = ((AttVal)this.avAdaptee.clone());
    return localDOMAttrImpl;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.w3c.tidy.DOMAttrImpl
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */