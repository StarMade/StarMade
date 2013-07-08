/*   1:    */package org.dom4j.bean;
/*   2:    */
/*   3:    */import java.util.AbstractList;
/*   4:    */import org.dom4j.Attribute;
/*   5:    */import org.dom4j.QName;
/*   6:    */
/*  26:    */public class BeanAttributeList
/*  27:    */  extends AbstractList
/*  28:    */{
/*  29:    */  private BeanElement parent;
/*  30:    */  private BeanMetaData beanMetaData;
/*  31:    */  private BeanAttribute[] attributes;
/*  32:    */  
/*  33:    */  public BeanAttributeList(BeanElement parent, BeanMetaData beanMetaData)
/*  34:    */  {
/*  35: 35 */    this.parent = parent;
/*  36: 36 */    this.beanMetaData = beanMetaData;
/*  37: 37 */    this.attributes = new BeanAttribute[beanMetaData.attributeCount()];
/*  38:    */  }
/*  39:    */  
/*  40:    */  public BeanAttributeList(BeanElement parent) {
/*  41: 41 */    this.parent = parent;
/*  42:    */    
/*  43: 43 */    Object data = parent.getData();
/*  44: 44 */    Class beanClass = data != null ? data.getClass() : null;
/*  45: 45 */    this.beanMetaData = BeanMetaData.get(beanClass);
/*  46: 46 */    this.attributes = new BeanAttribute[this.beanMetaData.attributeCount()];
/*  47:    */  }
/*  48:    */  
/*  49:    */  public Attribute attribute(String name) {
/*  50: 50 */    int index = this.beanMetaData.getIndex(name);
/*  51:    */    
/*  52: 52 */    return attribute(index);
/*  53:    */  }
/*  54:    */  
/*  55:    */  public Attribute attribute(QName qname) {
/*  56: 56 */    int index = this.beanMetaData.getIndex(qname);
/*  57:    */    
/*  58: 58 */    return attribute(index);
/*  59:    */  }
/*  60:    */  
/*  61:    */  public BeanAttribute attribute(int index) {
/*  62: 62 */    if ((index >= 0) && (index <= this.attributes.length)) {
/*  63: 63 */      BeanAttribute attribute = this.attributes[index];
/*  64:    */      
/*  65: 65 */      if (attribute == null) {
/*  66: 66 */        attribute = createAttribute(this.parent, index);
/*  67: 67 */        this.attributes[index] = attribute;
/*  68:    */      }
/*  69:    */      
/*  70: 70 */      return attribute;
/*  71:    */    }
/*  72:    */    
/*  73: 73 */    return null;
/*  74:    */  }
/*  75:    */  
/*  76:    */  public BeanElement getParent() {
/*  77: 77 */    return this.parent;
/*  78:    */  }
/*  79:    */  
/*  80:    */  public QName getQName(int index) {
/*  81: 81 */    return this.beanMetaData.getQName(index);
/*  82:    */  }
/*  83:    */  
/*  84:    */  public Object getData(int index) {
/*  85: 85 */    return this.beanMetaData.getData(index, this.parent.getData());
/*  86:    */  }
/*  87:    */  
/*  88:    */  public void setData(int index, Object data) {
/*  89: 89 */    this.beanMetaData.setData(index, this.parent.getData(), data);
/*  90:    */  }
/*  91:    */  
/*  93:    */  public int size()
/*  94:    */  {
/*  95: 95 */    return this.attributes.length;
/*  96:    */  }
/*  97:    */  
/*  98:    */  public Object get(int index) {
/*  99: 99 */    BeanAttribute attribute = this.attributes[index];
/* 100:    */    
/* 101:101 */    if (attribute == null) {
/* 102:102 */      attribute = createAttribute(this.parent, index);
/* 103:103 */      this.attributes[index] = attribute;
/* 104:    */    }
/* 105:    */    
/* 106:106 */    return attribute;
/* 107:    */  }
/* 108:    */  
/* 109:    */  public boolean add(Object object) {
/* 110:110 */    throw new UnsupportedOperationException("add(Object) unsupported");
/* 111:    */  }
/* 112:    */  
/* 113:    */  public void add(int index, Object object) {
/* 114:114 */    throw new UnsupportedOperationException("add(int,Object) unsupported");
/* 115:    */  }
/* 116:    */  
/* 117:    */  public Object set(int index, Object object) {
/* 118:118 */    throw new UnsupportedOperationException("set(int,Object) unsupported");
/* 119:    */  }
/* 120:    */  
/* 121:    */  public boolean remove(Object object) {
/* 122:122 */    return false;
/* 123:    */  }
/* 124:    */  
/* 125:    */  public Object remove(int index) {
/* 126:126 */    BeanAttribute attribute = (BeanAttribute)get(index);
/* 127:127 */    Object oldValue = attribute.getValue();
/* 128:128 */    attribute.setValue(null);
/* 129:    */    
/* 130:130 */    return oldValue;
/* 131:    */  }
/* 132:    */  
/* 133:    */  public void clear() {
/* 134:134 */    int i = 0; for (int size = this.attributes.length; i < size; i++) {
/* 135:135 */      BeanAttribute attribute = this.attributes[i];
/* 136:    */      
/* 137:137 */      if (attribute != null) {
/* 138:138 */        attribute.setValue(null);
/* 139:    */      }
/* 140:    */    }
/* 141:    */  }
/* 142:    */  
/* 144:    */  protected BeanAttribute createAttribute(BeanElement element, int index)
/* 145:    */  {
/* 146:146 */    return new BeanAttribute(this, index);
/* 147:    */  }
/* 148:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.bean.BeanAttributeList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */