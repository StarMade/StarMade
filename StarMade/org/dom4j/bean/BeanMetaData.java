/*     */ package org.dom4j.bean;
/*     */ 
/*     */ import java.beans.BeanInfo;
/*     */ import java.beans.IntrospectionException;
/*     */ import java.beans.Introspector;
/*     */ import java.beans.PropertyDescriptor;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.dom4j.DocumentFactory;
/*     */ import org.dom4j.QName;
/*     */ 
/*     */ public class BeanMetaData
/*     */ {
/*  31 */   protected static final Object[] NULL_ARGS = new Object[0];
/*     */ 
/*  34 */   private static Map singletonCache = new HashMap();
/*     */ 
/*  36 */   private static final DocumentFactory DOCUMENT_FACTORY = BeanDocumentFactory.getInstance();
/*     */   private Class beanClass;
/*     */   private PropertyDescriptor[] propertyDescriptors;
/*     */   private QName[] qNames;
/*     */   private Method[] readMethods;
/*     */   private Method[] writeMethods;
/*  55 */   private Map nameMap = new HashMap();
/*     */ 
/*     */   public BeanMetaData(Class beanClass) {
/*  58 */     this.beanClass = beanClass;
/*     */ 
/*  60 */     if (beanClass != null) {
/*     */       try {
/*  62 */         BeanInfo beanInfo = Introspector.getBeanInfo(beanClass);
/*  63 */         this.propertyDescriptors = beanInfo.getPropertyDescriptors();
/*     */       } catch (IntrospectionException e) {
/*  65 */         handleException(e);
/*     */       }
/*     */     }
/*     */ 
/*  69 */     if (this.propertyDescriptors == null) {
/*  70 */       this.propertyDescriptors = new PropertyDescriptor[0];
/*     */     }
/*     */ 
/*  73 */     int size = this.propertyDescriptors.length;
/*  74 */     this.qNames = new QName[size];
/*  75 */     this.readMethods = new Method[size];
/*  76 */     this.writeMethods = new Method[size];
/*     */ 
/*  78 */     for (int i = 0; i < size; i++) {
/*  79 */       PropertyDescriptor propertyDescriptor = this.propertyDescriptors[i];
/*  80 */       String name = propertyDescriptor.getName();
/*  81 */       QName qName = DOCUMENT_FACTORY.createQName(name);
/*  82 */       this.qNames[i] = qName;
/*  83 */       this.readMethods[i] = propertyDescriptor.getReadMethod();
/*  84 */       this.writeMethods[i] = propertyDescriptor.getWriteMethod();
/*     */ 
/*  86 */       Integer index = new Integer(i);
/*  87 */       this.nameMap.put(name, index);
/*  88 */       this.nameMap.put(qName, index);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static BeanMetaData get(Class beanClass)
/*     */   {
/* 101 */     BeanMetaData answer = (BeanMetaData)singletonCache.get(beanClass);
/*     */ 
/* 103 */     if (answer == null) {
/* 104 */       answer = new BeanMetaData(beanClass);
/* 105 */       singletonCache.put(beanClass, answer);
/*     */     }
/*     */ 
/* 108 */     return answer;
/*     */   }
/*     */ 
/*     */   public int attributeCount()
/*     */   {
/* 117 */     return this.propertyDescriptors.length;
/*     */   }
/*     */ 
/*     */   public BeanAttributeList createAttributeList(BeanElement parent) {
/* 121 */     return new BeanAttributeList(parent, this);
/*     */   }
/*     */ 
/*     */   public QName getQName(int index) {
/* 125 */     return this.qNames[index];
/*     */   }
/*     */ 
/*     */   public int getIndex(String name) {
/* 129 */     Integer index = (Integer)this.nameMap.get(name);
/*     */ 
/* 131 */     return index != null ? index.intValue() : -1;
/*     */   }
/*     */ 
/*     */   public int getIndex(QName qName) {
/* 135 */     Integer index = (Integer)this.nameMap.get(qName);
/*     */ 
/* 137 */     return index != null ? index.intValue() : -1;
/*     */   }
/*     */ 
/*     */   public Object getData(int index, Object bean) {
/*     */     try {
/* 142 */       Method method = this.readMethods[index];
/*     */ 
/* 144 */       return method.invoke(bean, NULL_ARGS);
/*     */     } catch (Exception e) {
/* 146 */       handleException(e);
/*     */     }
/* 148 */     return null;
/*     */   }
/*     */ 
/*     */   public void setData(int index, Object bean, Object data)
/*     */   {
/*     */     try {
/* 154 */       Method method = this.writeMethods[index];
/* 155 */       Object[] args = { data };
/* 156 */       method.invoke(bean, args);
/*     */     } catch (Exception e) {
/* 158 */       handleException(e);
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void handleException(Exception e)
/*     */   {
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.bean.BeanMetaData
 * JD-Core Version:    0.6.2
 */