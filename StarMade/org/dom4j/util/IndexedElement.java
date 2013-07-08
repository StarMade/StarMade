package org.dom4j.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.dom4j.Attribute;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.QName;
import org.dom4j.tree.BackedList;
import org.dom4j.tree.DefaultElement;

public class IndexedElement
  extends DefaultElement
{
  private Map elementIndex;
  private Map attributeIndex;
  
  public IndexedElement(String name)
  {
    super(name);
  }
  
  public IndexedElement(QName qname)
  {
    super(qname);
  }
  
  public IndexedElement(QName qname, int attributeCount)
  {
    super(qname, attributeCount);
  }
  
  public Attribute attribute(String name)
  {
    return (Attribute)attributeIndex().get(name);
  }
  
  public Attribute attribute(QName qName)
  {
    return (Attribute)attributeIndex().get(qName);
  }
  
  public Element element(String name)
  {
    return asElement(elementIndex().get(name));
  }
  
  public Element element(QName qName)
  {
    return asElement(elementIndex().get(qName));
  }
  
  public List elements(String name)
  {
    return asElementList(elementIndex().get(name));
  }
  
  public List elements(QName qName)
  {
    return asElementList(elementIndex().get(qName));
  }
  
  protected Element asElement(Object object)
  {
    if ((object instanceof Element)) {
      return (Element)object;
    }
    if (object != null)
    {
      List list = (List)object;
      if (list.size() >= 1) {
        return (Element)list.get(0);
      }
    }
    return null;
  }
  
  protected List asElementList(Object object)
  {
    if ((object instanceof Element)) {
      return createSingleResultList(object);
    }
    if (object != null)
    {
      List list = (List)object;
      BackedList answer = createResultList();
      int local_i = 0;
      int size = list.size();
      while (local_i < size)
      {
        answer.addLocal(list.get(local_i));
        local_i++;
      }
      return answer;
    }
    return createEmptyList();
  }
  
  /**
   * @deprecated
   */
  protected Iterator asElementIterator(Object object)
  {
    return asElementList(object).iterator();
  }
  
  protected void addNode(Node node)
  {
    super.addNode(node);
    if ((this.elementIndex != null) && ((node instanceof Element))) {
      addToElementIndex((Element)node);
    } else if ((this.attributeIndex != null) && ((node instanceof Attribute))) {
      addToAttributeIndex((Attribute)node);
    }
  }
  
  protected boolean removeNode(Node node)
  {
    if (super.removeNode(node))
    {
      if ((this.elementIndex != null) && ((node instanceof Element))) {
        removeFromElementIndex((Element)node);
      } else if ((this.attributeIndex != null) && ((node instanceof Attribute))) {
        removeFromAttributeIndex((Attribute)node);
      }
      return true;
    }
    return false;
  }
  
  protected Map attributeIndex()
  {
    if (this.attributeIndex == null)
    {
      this.attributeIndex = createAttributeIndex();
      Iterator iter = attributeIterator();
      while (iter.hasNext()) {
        addToAttributeIndex((Attribute)iter.next());
      }
    }
    return this.attributeIndex;
  }
  
  protected Map elementIndex()
  {
    if (this.elementIndex == null)
    {
      this.elementIndex = createElementIndex();
      Iterator iter = elementIterator();
      while (iter.hasNext()) {
        addToElementIndex((Element)iter.next());
      }
    }
    return this.elementIndex;
  }
  
  protected Map createAttributeIndex()
  {
    Map answer = createIndex();
    return answer;
  }
  
  protected Map createElementIndex()
  {
    Map answer = createIndex();
    return answer;
  }
  
  protected void addToElementIndex(Element element)
  {
    QName qName = element.getQName();
    String name = qName.getName();
    addToElementIndex(qName, element);
    addToElementIndex(name, element);
  }
  
  protected void addToElementIndex(Object key, Element value)
  {
    Object oldValue = this.elementIndex.get(key);
    if (oldValue == null)
    {
      this.elementIndex.put(key, value);
    }
    else if ((oldValue instanceof List))
    {
      List list = (List)oldValue;
      list.add(value);
    }
    else
    {
      List list = createList();
      list.add(oldValue);
      list.add(value);
      this.elementIndex.put(key, list);
    }
  }
  
  protected void removeFromElementIndex(Element element)
  {
    QName qName = element.getQName();
    String name = qName.getName();
    removeFromElementIndex(qName, element);
    removeFromElementIndex(name, element);
  }
  
  protected void removeFromElementIndex(Object key, Element value)
  {
    Object oldValue = this.elementIndex.get(key);
    if ((oldValue instanceof List))
    {
      List list = (List)oldValue;
      list.remove(value);
    }
    else
    {
      this.elementIndex.remove(key);
    }
  }
  
  protected void addToAttributeIndex(Attribute attribute)
  {
    QName qName = attribute.getQName();
    String name = qName.getName();
    addToAttributeIndex(qName, attribute);
    addToAttributeIndex(name, attribute);
  }
  
  protected void addToAttributeIndex(Object key, Attribute value)
  {
    Object oldValue = this.attributeIndex.get(key);
    if (oldValue != null) {
      this.attributeIndex.put(key, value);
    }
  }
  
  protected void removeFromAttributeIndex(Attribute attribute)
  {
    QName qName = attribute.getQName();
    String name = qName.getName();
    removeFromAttributeIndex(qName, attribute);
    removeFromAttributeIndex(name, attribute);
  }
  
  protected void removeFromAttributeIndex(Object key, Attribute value)
  {
    Object oldValue = this.attributeIndex.get(key);
    if ((oldValue != null) && (oldValue.equals(value))) {
      this.attributeIndex.remove(key);
    }
  }
  
  protected Map createIndex()
  {
    return new HashMap();
  }
  
  protected List createList()
  {
    return new ArrayList();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.util.IndexedElement
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */