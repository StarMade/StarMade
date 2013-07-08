/*   1:    */package org.dom4j.bean;
/*   2:    */
/*   3:    */import java.beans.BeanInfo;
/*   4:    */import java.beans.IntrospectionException;
/*   5:    */import java.beans.Introspector;
/*   6:    */import java.beans.PropertyDescriptor;
/*   7:    */import java.lang.reflect.Method;
/*   8:    */import java.util.HashMap;
/*   9:    */import java.util.Map;
/*  10:    */import org.dom4j.DocumentFactory;
/*  11:    */import org.dom4j.QName;
/*  12:    */
/*  29:    */public class BeanMetaData
/*  30:    */{
/*  31: 31 */  protected static final Object[] NULL_ARGS = new Object[0];
/*  32:    */  
/*  34: 34 */  private static Map singletonCache = new HashMap();
/*  35:    */  
/*  36: 36 */  private static final DocumentFactory DOCUMENT_FACTORY = BeanDocumentFactory.getInstance();
/*  37:    */  
/*  40:    */  private Class beanClass;
/*  41:    */  
/*  43:    */  private PropertyDescriptor[] propertyDescriptors;
/*  44:    */  
/*  46:    */  private QName[] qNames;
/*  47:    */  
/*  49:    */  private Method[] readMethods;
/*  50:    */  
/*  52:    */  private Method[] writeMethods;
/*  53:    */  
/*  55: 55 */  private Map nameMap = new HashMap();
/*  56:    */  
/*  57:    */  public BeanMetaData(Class beanClass) {
/*  58: 58 */    this.beanClass = beanClass;
/*  59:    */    
/*  60: 60 */    if (beanClass != null) {
/*  61:    */      try {
/*  62: 62 */        BeanInfo beanInfo = Introspector.getBeanInfo(beanClass);
/*  63: 63 */        this.propertyDescriptors = beanInfo.getPropertyDescriptors();
/*  64:    */      } catch (IntrospectionException e) {
/*  65: 65 */        handleException(e);
/*  66:    */      }
/*  67:    */    }
/*  68:    */    
/*  69: 69 */    if (this.propertyDescriptors == null) {
/*  70: 70 */      this.propertyDescriptors = new PropertyDescriptor[0];
/*  71:    */    }
/*  72:    */    
/*  73: 73 */    int size = this.propertyDescriptors.length;
/*  74: 74 */    this.qNames = new QName[size];
/*  75: 75 */    this.readMethods = new Method[size];
/*  76: 76 */    this.writeMethods = new Method[size];
/*  77:    */    
/*  78: 78 */    for (int i = 0; i < size; i++) {
/*  79: 79 */      PropertyDescriptor propertyDescriptor = this.propertyDescriptors[i];
/*  80: 80 */      String name = propertyDescriptor.getName();
/*  81: 81 */      QName qName = DOCUMENT_FACTORY.createQName(name);
/*  82: 82 */      this.qNames[i] = qName;
/*  83: 83 */      this.readMethods[i] = propertyDescriptor.getReadMethod();
/*  84: 84 */      this.writeMethods[i] = propertyDescriptor.getWriteMethod();
/*  85:    */      
/*  86: 86 */      Integer index = new Integer(i);
/*  87: 87 */      this.nameMap.put(name, index);
/*  88: 88 */      this.nameMap.put(qName, index);
/*  89:    */    }
/*  90:    */  }
/*  91:    */  
/*  99:    */  public static BeanMetaData get(Class beanClass)
/* 100:    */  {
/* 101:101 */    BeanMetaData answer = (BeanMetaData)singletonCache.get(beanClass);
/* 102:    */    
/* 103:103 */    if (answer == null) {
/* 104:104 */      answer = new BeanMetaData(beanClass);
/* 105:105 */      singletonCache.put(beanClass, answer);
/* 106:    */    }
/* 107:    */    
/* 108:108 */    return answer;
/* 109:    */  }
/* 110:    */  
/* 115:    */  public int attributeCount()
/* 116:    */  {
/* 117:117 */    return this.propertyDescriptors.length;
/* 118:    */  }
/* 119:    */  
/* 120:    */  public BeanAttributeList createAttributeList(BeanElement parent) {
/* 121:121 */    return new BeanAttributeList(parent, this);
/* 122:    */  }
/* 123:    */  
/* 124:    */  public QName getQName(int index) {
/* 125:125 */    return this.qNames[index];
/* 126:    */  }
/* 127:    */  
/* 128:    */  public int getIndex(String name) {
/* 129:129 */    Integer index = (Integer)this.nameMap.get(name);
/* 130:    */    
/* 131:131 */    return index != null ? index.intValue() : -1;
/* 132:    */  }
/* 133:    */  
/* 134:    */  public int getIndex(QName qName) {
/* 135:135 */    Integer index = (Integer)this.nameMap.get(qName);
/* 136:    */    
/* 137:137 */    return index != null ? index.intValue() : -1;
/* 138:    */  }
/* 139:    */  
/* 140:    */  public Object getData(int index, Object bean) {
/* 141:    */    try {
/* 142:142 */      Method method = this.readMethods[index];
/* 143:    */      
/* 144:144 */      return method.invoke(bean, NULL_ARGS);
/* 145:    */    } catch (Exception e) {
/* 146:146 */      handleException(e);
/* 147:    */    }
/* 148:148 */    return null;
/* 149:    */  }
/* 150:    */  
/* 151:    */  public void setData(int index, Object bean, Object data)
/* 152:    */  {
/* 153:    */    try {
/* 154:154 */      Method method = this.writeMethods[index];
/* 155:155 */      Object[] args = { data };
/* 156:156 */      method.invoke(bean, args);
/* 157:    */    } catch (Exception e) {
/* 158:158 */      handleException(e);
/* 159:    */    }
/* 160:    */  }
/* 161:    */  
/* 162:    */  protected void handleException(Exception e) {}
/* 163:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.bean.BeanMetaData
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */