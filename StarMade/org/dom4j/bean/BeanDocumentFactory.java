/*  1:   */package org.dom4j.bean;
/*  2:   */
/*  3:   */import java.io.PrintStream;
/*  4:   */import org.dom4j.Attribute;
/*  5:   */import org.dom4j.DocumentFactory;
/*  6:   */import org.dom4j.Element;
/*  7:   */import org.dom4j.QName;
/*  8:   */import org.dom4j.tree.DefaultAttribute;
/*  9:   */import org.xml.sax.Attributes;
/* 10:   */
/* 30:   */public class BeanDocumentFactory
/* 31:   */  extends DocumentFactory
/* 32:   */{
/* 33:33 */  private static BeanDocumentFactory singleton = new BeanDocumentFactory();
/* 34:   */  
/* 41:   */  public static DocumentFactory getInstance()
/* 42:   */  {
/* 43:43 */    return singleton;
/* 44:   */  }
/* 45:   */  
/* 46:   */  public Element createElement(QName qname)
/* 47:   */  {
/* 48:48 */    Object bean = createBean(qname);
/* 49:   */    
/* 50:50 */    if (bean == null) {
/* 51:51 */      return new BeanElement(qname);
/* 52:   */    }
/* 53:53 */    return new BeanElement(qname, bean);
/* 54:   */  }
/* 55:   */  
/* 56:   */  public Element createElement(QName qname, Attributes attributes)
/* 57:   */  {
/* 58:58 */    Object bean = createBean(qname, attributes);
/* 59:   */    
/* 60:60 */    if (bean == null) {
/* 61:61 */      return new BeanElement(qname);
/* 62:   */    }
/* 63:63 */    return new BeanElement(qname, bean);
/* 64:   */  }
/* 65:   */  
/* 66:   */  public Attribute createAttribute(Element owner, QName qname, String value)
/* 67:   */  {
/* 68:68 */    return new DefaultAttribute(qname, value);
/* 69:   */  }
/* 70:   */  
/* 71:   */  protected Object createBean(QName qname)
/* 72:   */  {
/* 73:73 */    return null;
/* 74:   */  }
/* 75:   */  
/* 76:   */  protected Object createBean(QName qname, Attributes attributes) {
/* 77:77 */    String value = attributes.getValue("class");
/* 78:   */    
/* 79:79 */    if (value != null) {
/* 80:   */      try {
/* 81:81 */        Class beanClass = Class.forName(value, true, BeanDocumentFactory.class.getClassLoader());
/* 82:   */        
/* 84:84 */        return beanClass.newInstance();
/* 85:   */      } catch (Exception e) {
/* 86:86 */        handleException(e);
/* 87:   */      }
/* 88:   */    }
/* 89:   */    
/* 90:90 */    return null;
/* 91:   */  }
/* 92:   */  
/* 93:   */  protected void handleException(Exception e)
/* 94:   */  {
/* 95:95 */    System.out.println("#### Warning: couldn't create bean: " + e);
/* 96:   */  }
/* 97:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.bean.BeanDocumentFactory
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */