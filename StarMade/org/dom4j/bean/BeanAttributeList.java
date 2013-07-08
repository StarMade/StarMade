package org.dom4j.bean;

import java.util.AbstractList;
import org.dom4j.Attribute;
import org.dom4j.QName;

public class BeanAttributeList
  extends AbstractList
{
  private BeanElement parent;
  private BeanMetaData beanMetaData;
  private BeanAttribute[] attributes;
  
  public BeanAttributeList(BeanElement parent, BeanMetaData beanMetaData)
  {
    this.parent = parent;
    this.beanMetaData = beanMetaData;
    this.attributes = new BeanAttribute[beanMetaData.attributeCount()];
  }
  
  public BeanAttributeList(BeanElement parent)
  {
    this.parent = parent;
    Object data = parent.getData();
    Class beanClass = data != null ? data.getClass() : null;
    this.beanMetaData = BeanMetaData.get(beanClass);
    this.attributes = new BeanAttribute[this.beanMetaData.attributeCount()];
  }
  
  public Attribute attribute(String name)
  {
    int index = this.beanMetaData.getIndex(name);
    return attribute(index);
  }
  
  public Attribute attribute(QName qname)
  {
    int index = this.beanMetaData.getIndex(qname);
    return attribute(index);
  }
  
  public BeanAttribute attribute(int index)
  {
    if ((index >= 0) && (index <= this.attributes.length))
    {
      BeanAttribute attribute = this.attributes[index];
      if (attribute == null)
      {
        attribute = createAttribute(this.parent, index);
        this.attributes[index] = attribute;
      }
      return attribute;
    }
    return null;
  }
  
  public BeanElement getParent()
  {
    return this.parent;
  }
  
  public QName getQName(int index)
  {
    return this.beanMetaData.getQName(index);
  }
  
  public Object getData(int index)
  {
    return this.beanMetaData.getData(index, this.parent.getData());
  }
  
  public void setData(int index, Object data)
  {
    this.beanMetaData.setData(index, this.parent.getData(), data);
  }
  
  public int size()
  {
    return this.attributes.length;
  }
  
  public Object get(int index)
  {
    BeanAttribute attribute = this.attributes[index];
    if (attribute == null)
    {
      attribute = createAttribute(this.parent, index);
      this.attributes[index] = attribute;
    }
    return attribute;
  }
  
  public boolean add(Object object)
  {
    throw new UnsupportedOperationException("add(Object) unsupported");
  }
  
  public void add(int index, Object object)
  {
    throw new UnsupportedOperationException("add(int,Object) unsupported");
  }
  
  public Object set(int index, Object object)
  {
    throw new UnsupportedOperationException("set(int,Object) unsupported");
  }
  
  public boolean remove(Object object)
  {
    return false;
  }
  
  public Object remove(int index)
  {
    BeanAttribute attribute = (BeanAttribute)get(index);
    Object oldValue = attribute.getValue();
    attribute.setValue(null);
    return oldValue;
  }
  
  public void clear()
  {
    int local_i = 0;
    int size = this.attributes.length;
    while (local_i < size)
    {
      BeanAttribute attribute = this.attributes[local_i];
      if (attribute != null) {
        attribute.setValue(null);
      }
      local_i++;
    }
  }
  
  protected BeanAttribute createAttribute(BeanElement element, int index)
  {
    return new BeanAttribute(this, index);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.bean.BeanAttributeList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */