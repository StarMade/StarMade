package org.dom4j.bean;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.dom4j.DocumentFactory;
import org.dom4j.QName;

public class BeanMetaData
{
  protected static final Object[] NULL_ARGS = new Object[0];
  private static Map singletonCache = new HashMap();
  private static final DocumentFactory DOCUMENT_FACTORY = BeanDocumentFactory.getInstance();
  private Class beanClass;
  private PropertyDescriptor[] propertyDescriptors;
  private QName[] qNames;
  private Method[] readMethods;
  private Method[] writeMethods;
  private Map nameMap = new HashMap();
  
  public BeanMetaData(Class beanClass)
  {
    this.beanClass = beanClass;
    if (beanClass != null) {
      try
      {
        BeanInfo beanInfo = Introspector.getBeanInfo(beanClass);
        this.propertyDescriptors = beanInfo.getPropertyDescriptors();
      }
      catch (IntrospectionException beanInfo)
      {
        handleException(beanInfo);
      }
    }
    if (this.propertyDescriptors == null) {
      this.propertyDescriptors = new PropertyDescriptor[0];
    }
    int beanInfo = this.propertyDescriptors.length;
    this.qNames = new QName[beanInfo];
    this.readMethods = new Method[beanInfo];
    this.writeMethods = new Method[beanInfo];
    for (int local_i = 0; local_i < beanInfo; local_i++)
    {
      PropertyDescriptor propertyDescriptor = this.propertyDescriptors[local_i];
      String name = propertyDescriptor.getName();
      QName qName = DOCUMENT_FACTORY.createQName(name);
      this.qNames[local_i] = qName;
      this.readMethods[local_i] = propertyDescriptor.getReadMethod();
      this.writeMethods[local_i] = propertyDescriptor.getWriteMethod();
      Integer index = new Integer(local_i);
      this.nameMap.put(name, index);
      this.nameMap.put(qName, index);
    }
  }
  
  public static BeanMetaData get(Class beanClass)
  {
    BeanMetaData answer = (BeanMetaData)singletonCache.get(beanClass);
    if (answer == null)
    {
      answer = new BeanMetaData(beanClass);
      singletonCache.put(beanClass, answer);
    }
    return answer;
  }
  
  public int attributeCount()
  {
    return this.propertyDescriptors.length;
  }
  
  public BeanAttributeList createAttributeList(BeanElement parent)
  {
    return new BeanAttributeList(parent, this);
  }
  
  public QName getQName(int index)
  {
    return this.qNames[index];
  }
  
  public int getIndex(String name)
  {
    Integer index = (Integer)this.nameMap.get(name);
    return index != null ? index.intValue() : -1;
  }
  
  public int getIndex(QName qName)
  {
    Integer index = (Integer)this.nameMap.get(qName);
    return index != null ? index.intValue() : -1;
  }
  
  public Object getData(int index, Object bean)
  {
    try
    {
      Method method = this.readMethods[index];
      return method.invoke(bean, NULL_ARGS);
    }
    catch (Exception method)
    {
      handleException(method);
    }
    return null;
  }
  
  public void setData(int index, Object bean, Object data)
  {
    try
    {
      Method method = this.writeMethods[index];
      Object[] args = { data };
      method.invoke(bean, args);
    }
    catch (Exception method)
    {
      handleException(method);
    }
  }
  
  protected void handleException(Exception local_e) {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.bean.BeanMetaData
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */