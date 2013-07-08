package org.w3c.tidy;

import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.TypeInfo;

public class DOMElementImpl
  extends DOMNodeImpl
  implements Element
{
  protected DOMElementImpl(Node paramNode)
  {
    super(paramNode);
  }
  
  public short getNodeType()
  {
    return 1;
  }
  
  public String getTagName()
  {
    return super.getNodeName();
  }
  
  public String getAttribute(String paramString)
  {
    if (this.adaptee == null) {
      return null;
    }
    for (AttVal localAttVal = this.adaptee.attributes; (localAttVal != null) && (!localAttVal.attribute.equals(paramString)); localAttVal = localAttVal.next) {}
    if (localAttVal != null) {
      return localAttVal.value;
    }
    return "";
  }
  
  public void setAttribute(String paramString1, String paramString2)
    throws DOMException
  {
    if (this.adaptee == null) {
      return;
    }
    for (AttVal localAttVal = this.adaptee.attributes; (localAttVal != null) && (!localAttVal.attribute.equals(paramString1)); localAttVal = localAttVal.next) {}
    if (localAttVal != null)
    {
      localAttVal.value = paramString2;
    }
    else
    {
      localAttVal = new AttVal(null, null, 34, paramString1, paramString2);
      localAttVal.dict = AttributeTable.getDefaultAttributeTable().findAttribute(localAttVal);
      if (this.adaptee.attributes == null)
      {
        this.adaptee.attributes = localAttVal;
      }
      else
      {
        localAttVal.next = this.adaptee.attributes;
        this.adaptee.attributes = localAttVal;
      }
    }
  }
  
  public void removeAttribute(String paramString)
    throws DOMException
  {
    if (this.adaptee == null) {
      return;
    }
    AttVal localAttVal1 = this.adaptee.attributes;
    AttVal localAttVal2 = null;
    while ((localAttVal1 != null) && (!localAttVal1.attribute.equals(paramString)))
    {
      localAttVal2 = localAttVal1;
      localAttVal1 = localAttVal1.next;
    }
    if (localAttVal1 != null) {
      if (localAttVal2 == null) {
        this.adaptee.attributes = localAttVal1.next;
      } else {
        localAttVal2.next = localAttVal1.next;
      }
    }
  }
  
  public Attr getAttributeNode(String paramString)
  {
    if (this.adaptee == null) {
      return null;
    }
    for (AttVal localAttVal = this.adaptee.attributes; (localAttVal != null) && (!localAttVal.attribute.equals(paramString)); localAttVal = localAttVal.next) {}
    if (localAttVal != null) {
      return localAttVal.getAdapter();
    }
    return null;
  }
  
  public Attr setAttributeNode(Attr paramAttr)
    throws DOMException
  {
    if (paramAttr == null) {
      return null;
    }
    if (!(paramAttr instanceof DOMAttrImpl)) {
      throw new DOMException((short)4, "newAttr not instanceof DOMAttrImpl");
    }
    DOMAttrImpl localDOMAttrImpl = (DOMAttrImpl)paramAttr;
    String str = localDOMAttrImpl.avAdaptee.attribute;
    Attr localAttr = null;
    for (AttVal localAttVal = this.adaptee.attributes; (localAttVal != null) && (!localAttVal.attribute.equals(str)); localAttVal = localAttVal.next) {}
    if (localAttVal != null)
    {
      localAttr = localAttVal.getAdapter();
      localAttVal.adapter = paramAttr;
    }
    else if (this.adaptee.attributes == null)
    {
      this.adaptee.attributes = localDOMAttrImpl.avAdaptee;
    }
    else
    {
      localDOMAttrImpl.avAdaptee.next = this.adaptee.attributes;
      this.adaptee.attributes = localDOMAttrImpl.avAdaptee;
    }
    return localAttr;
  }
  
  public Attr removeAttributeNode(Attr paramAttr)
    throws DOMException
  {
    if (paramAttr == null) {
      return null;
    }
    Attr localAttr = null;
    AttVal localAttVal1 = this.adaptee.attributes;
    AttVal localAttVal2 = null;
    while ((localAttVal1 != null) && (localAttVal1.getAdapter() != paramAttr))
    {
      localAttVal2 = localAttVal1;
      localAttVal1 = localAttVal1.next;
    }
    if (localAttVal1 != null)
    {
      if (localAttVal2 == null) {
        this.adaptee.attributes = localAttVal1.next;
      } else {
        localAttVal2.next = localAttVal1.next;
      }
      localAttr = paramAttr;
    }
    else
    {
      throw new DOMException((short)8, "oldAttr not found");
    }
    return localAttr;
  }
  
  public NodeList getElementsByTagName(String paramString)
  {
    return new DOMNodeListByTagNameImpl(this.adaptee, paramString);
  }
  
  public void normalize() {}
  
  public String getAttributeNS(String paramString1, String paramString2)
  {
    throw new DOMException((short)9, "DOM method not supported");
  }
  
  public void setAttributeNS(String paramString1, String paramString2, String paramString3)
    throws DOMException
  {
    throw new DOMException((short)9, "DOM method not supported");
  }
  
  public void removeAttributeNS(String paramString1, String paramString2)
    throws DOMException
  {
    throw new DOMException((short)9, "DOM method not supported");
  }
  
  public Attr getAttributeNodeNS(String paramString1, String paramString2)
  {
    throw new DOMException((short)9, "DOM method not supported");
  }
  
  public Attr setAttributeNodeNS(Attr paramAttr)
    throws DOMException
  {
    throw new DOMException((short)9, "DOM method not supported");
  }
  
  public NodeList getElementsByTagNameNS(String paramString1, String paramString2)
  {
    throw new DOMException((short)9, "DOM method not supported");
  }
  
  public boolean hasAttribute(String paramString)
  {
    return false;
  }
  
  public boolean hasAttributeNS(String paramString1, String paramString2)
  {
    return false;
  }
  
  public TypeInfo getSchemaTypeInfo()
  {
    return null;
  }
  
  public void setIdAttribute(String paramString, boolean paramBoolean)
    throws DOMException
  {
    throw new DOMException((short)9, "DOM method not supported");
  }
  
  public void setIdAttributeNode(Attr paramAttr, boolean paramBoolean)
    throws DOMException
  {
    throw new DOMException((short)9, "DOM method not supported");
  }
  
  public void setIdAttributeNS(String paramString1, String paramString2, boolean paramBoolean)
    throws DOMException
  {
    throw new DOMException((short)9, "DOM method not supported");
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.w3c.tidy.DOMElementImpl
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */