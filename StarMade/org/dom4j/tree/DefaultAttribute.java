package org.dom4j.tree;

import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;

public class DefaultAttribute
  extends FlyweightAttribute
{
  private Element parent;
  
  public DefaultAttribute(QName qname)
  {
    super(qname);
  }
  
  public DefaultAttribute(QName qname, String value)
  {
    super(qname, value);
  }
  
  public DefaultAttribute(Element parent, QName qname, String value)
  {
    super(qname, value);
    this.parent = parent;
  }
  
  public DefaultAttribute(String name, String value)
  {
    super(name, value);
  }
  
  public DefaultAttribute(String name, String value, Namespace namespace)
  {
    super(name, value, namespace);
  }
  
  public DefaultAttribute(Element parent, String name, String value, Namespace namespace)
  {
    super(name, value, namespace);
    this.parent = parent;
  }
  
  public void setValue(String value)
  {
    this.value = value;
  }
  
  public Element getParent()
  {
    return this.parent;
  }
  
  public void setParent(Element parent)
  {
    this.parent = parent;
  }
  
  public boolean supportsParent()
  {
    return true;
  }
  
  public boolean isReadOnly()
  {
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.tree.DefaultAttribute
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */