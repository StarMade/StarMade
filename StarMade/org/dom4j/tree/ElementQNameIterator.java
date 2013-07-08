package org.dom4j.tree;

import java.util.Iterator;
import org.dom4j.Element;
import org.dom4j.QName;

/**
 * @deprecated
 */
public class ElementQNameIterator
  extends FilterIterator
{
  private QName qName;
  
  public ElementQNameIterator(Iterator proxy, QName qName)
  {
    super(proxy);
    this.qName = qName;
  }
  
  protected boolean matches(Object object)
  {
    if ((object instanceof Element))
    {
      Element element = (Element)object;
      return this.qName.equals(element.getQName());
    }
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.tree.ElementQNameIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */