package org.dom4j.util;

import org.dom4j.Namespace;
import org.dom4j.QName;
import org.dom4j.tree.BaseElement;

public class NonLazyElement
  extends BaseElement
{
  public NonLazyElement(String name)
  {
    super(name);
    this.attributes = createAttributeList();
    this.content = createContentList();
  }
  
  public NonLazyElement(QName qname)
  {
    super(qname);
    this.attributes = createAttributeList();
    this.content = createContentList();
  }
  
  public NonLazyElement(String name, Namespace namespace)
  {
    super(name, namespace);
    this.attributes = createAttributeList();
    this.content = createContentList();
  }
  
  public NonLazyElement(QName qname, int attributeCount)
  {
    super(qname);
    this.attributes = createAttributeList(attributeCount);
    this.content = createContentList();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.util.NonLazyElement
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */