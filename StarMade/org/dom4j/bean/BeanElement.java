/*   1:    */package org.dom4j.bean;
/*   2:    */
/*   3:    */import java.util.List;
/*   4:    */import org.dom4j.Attribute;
/*   5:    */import org.dom4j.DocumentFactory;
/*   6:    */import org.dom4j.Element;
/*   7:    */import org.dom4j.Namespace;
/*   8:    */import org.dom4j.QName;
/*   9:    */import org.dom4j.tree.DefaultElement;
/*  10:    */import org.dom4j.tree.NamespaceStack;
/*  11:    */import org.xml.sax.Attributes;
/*  12:    */
/*  29:    */public class BeanElement
/*  30:    */  extends DefaultElement
/*  31:    */{
/*  32: 32 */  private static final DocumentFactory DOCUMENT_FACTORY = ;
/*  33:    */  
/*  34:    */  private Object bean;
/*  35:    */  
/*  37:    */  public BeanElement(String name, Object bean)
/*  38:    */  {
/*  39: 39 */    this(DOCUMENT_FACTORY.createQName(name), bean);
/*  40:    */  }
/*  41:    */  
/*  42:    */  public BeanElement(String name, Namespace namespace, Object bean) {
/*  43: 43 */    this(DOCUMENT_FACTORY.createQName(name, namespace), bean);
/*  44:    */  }
/*  45:    */  
/*  46:    */  public BeanElement(QName qname, Object bean) {
/*  47: 47 */    super(qname);
/*  48: 48 */    this.bean = bean;
/*  49:    */  }
/*  50:    */  
/*  51:    */  public BeanElement(QName qname) {
/*  52: 52 */    super(qname);
/*  53:    */  }
/*  54:    */  
/*  59:    */  public Object getData()
/*  60:    */  {
/*  61: 61 */    return this.bean;
/*  62:    */  }
/*  63:    */  
/*  64:    */  public void setData(Object data) {
/*  65: 65 */    this.bean = data;
/*  66:    */    
/*  70: 70 */    setAttributeList(null);
/*  71:    */  }
/*  72:    */  
/*  73:    */  public Attribute attribute(String name) {
/*  74: 74 */    return getBeanAttributeList().attribute(name);
/*  75:    */  }
/*  76:    */  
/*  77:    */  public Attribute attribute(QName qname) {
/*  78: 78 */    return getBeanAttributeList().attribute(qname);
/*  79:    */  }
/*  80:    */  
/*  81:    */  public Element addAttribute(String name, String value) {
/*  82: 82 */    Attribute attribute = attribute(name);
/*  83:    */    
/*  84: 84 */    if (attribute != null) {
/*  85: 85 */      attribute.setValue(value);
/*  86:    */    }
/*  87:    */    
/*  88: 88 */    return this;
/*  89:    */  }
/*  90:    */  
/*  91:    */  public Element addAttribute(QName qName, String value) {
/*  92: 92 */    Attribute attribute = attribute(qName);
/*  93:    */    
/*  94: 94 */    if (attribute != null) {
/*  95: 95 */      attribute.setValue(value);
/*  96:    */    }
/*  97:    */    
/*  98: 98 */    return this;
/*  99:    */  }
/* 100:    */  
/* 101:    */  public void setAttributes(List attributes) {
/* 102:102 */    throw new UnsupportedOperationException("Not implemented yet.");
/* 103:    */  }
/* 104:    */  
/* 106:    */  public void setAttributes(Attributes attributes, NamespaceStack namespaceStack, boolean noNamespaceAttributes)
/* 107:    */  {
/* 108:108 */    String className = attributes.getValue("class");
/* 109:    */    
/* 110:110 */    if (className != null) {
/* 111:    */      try {
/* 112:112 */        Class beanClass = Class.forName(className, true, BeanElement.class.getClassLoader());
/* 113:    */        
/* 114:114 */        setData(beanClass.newInstance());
/* 115:    */        
/* 116:116 */        for (int i = 0; i < attributes.getLength(); i++) {
/* 117:117 */          String attributeName = attributes.getLocalName(i);
/* 118:    */          
/* 119:119 */          if (!"class".equalsIgnoreCase(attributeName)) {
/* 120:120 */            addAttribute(attributeName, attributes.getValue(i));
/* 121:    */          }
/* 122:    */        }
/* 123:    */      }
/* 124:    */      catch (Exception ex) {
/* 125:125 */        ((BeanDocumentFactory)getDocumentFactory()).handleException(ex);
/* 126:    */      }
/* 127:    */      
/* 128:    */    } else {
/* 129:129 */      super.setAttributes(attributes, namespaceStack, noNamespaceAttributes);
/* 130:    */    }
/* 131:    */  }
/* 132:    */  
/* 135:    */  protected DocumentFactory getDocumentFactory()
/* 136:    */  {
/* 137:137 */    return DOCUMENT_FACTORY;
/* 138:    */  }
/* 139:    */  
/* 140:    */  protected BeanAttributeList getBeanAttributeList() {
/* 141:141 */    return (BeanAttributeList)attributeList();
/* 142:    */  }
/* 143:    */  
/* 149:    */  protected List createAttributeList()
/* 150:    */  {
/* 151:151 */    return new BeanAttributeList(this);
/* 152:    */  }
/* 153:    */  
/* 154:    */  protected List createAttributeList(int size) {
/* 155:155 */    return new BeanAttributeList(this);
/* 156:    */  }
/* 157:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.bean.BeanElement
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */