package org.dom4j.bean;

import java.io.PrintStream;
import org.dom4j.Attribute;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.QName;
import org.dom4j.tree.DefaultAttribute;
import org.xml.sax.Attributes;

public class BeanDocumentFactory
  extends DocumentFactory
{
  private static BeanDocumentFactory singleton = new BeanDocumentFactory();
  
  public static DocumentFactory getInstance()
  {
    return singleton;
  }
  
  public Element createElement(QName qname)
  {
    Object bean = createBean(qname);
    if (bean == null) {
      return new BeanElement(qname);
    }
    return new BeanElement(qname, bean);
  }
  
  public Element createElement(QName qname, Attributes attributes)
  {
    Object bean = createBean(qname, attributes);
    if (bean == null) {
      return new BeanElement(qname);
    }
    return new BeanElement(qname, bean);
  }
  
  public Attribute createAttribute(Element owner, QName qname, String value)
  {
    return new DefaultAttribute(qname, value);
  }
  
  protected Object createBean(QName qname)
  {
    return null;
  }
  
  protected Object createBean(QName qname, Attributes attributes)
  {
    String value = attributes.getValue("class");
    if (value != null) {
      try
      {
        Class beanClass = Class.forName(value, true, BeanDocumentFactory.class.getClassLoader());
        return beanClass.newInstance();
      }
      catch (Exception beanClass)
      {
        handleException(beanClass);
      }
    }
    return null;
  }
  
  protected void handleException(Exception local_e)
  {
    System.out.println("#### Warning: couldn't create bean: " + local_e);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.bean.BeanDocumentFactory
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */