/*     */ package org.dom4j.bean;
/*     */ 
/*     */ import java.util.AbstractList;
/*     */ import org.dom4j.Attribute;
/*     */ import org.dom4j.QName;
/*     */ 
/*     */ public class BeanAttributeList extends AbstractList
/*     */ {
/*     */   private BeanElement parent;
/*     */   private BeanMetaData beanMetaData;
/*     */   private BeanAttribute[] attributes;
/*     */ 
/*     */   public BeanAttributeList(BeanElement parent, BeanMetaData beanMetaData)
/*     */   {
/*  35 */     this.parent = parent;
/*  36 */     this.beanMetaData = beanMetaData;
/*  37 */     this.attributes = new BeanAttribute[beanMetaData.attributeCount()];
/*     */   }
/*     */ 
/*     */   public BeanAttributeList(BeanElement parent) {
/*  41 */     this.parent = parent;
/*     */ 
/*  43 */     Object data = parent.getData();
/*  44 */     Class beanClass = data != null ? data.getClass() : null;
/*  45 */     this.beanMetaData = BeanMetaData.get(beanClass);
/*  46 */     this.attributes = new BeanAttribute[this.beanMetaData.attributeCount()];
/*     */   }
/*     */ 
/*     */   public Attribute attribute(String name) {
/*  50 */     int index = this.beanMetaData.getIndex(name);
/*     */ 
/*  52 */     return attribute(index);
/*     */   }
/*     */ 
/*     */   public Attribute attribute(QName qname) {
/*  56 */     int index = this.beanMetaData.getIndex(qname);
/*     */ 
/*  58 */     return attribute(index);
/*     */   }
/*     */ 
/*     */   public BeanAttribute attribute(int index) {
/*  62 */     if ((index >= 0) && (index <= this.attributes.length)) {
/*  63 */       BeanAttribute attribute = this.attributes[index];
/*     */ 
/*  65 */       if (attribute == null) {
/*  66 */         attribute = createAttribute(this.parent, index);
/*  67 */         this.attributes[index] = attribute;
/*     */       }
/*     */ 
/*  70 */       return attribute;
/*     */     }
/*     */ 
/*  73 */     return null;
/*     */   }
/*     */ 
/*     */   public BeanElement getParent() {
/*  77 */     return this.parent;
/*     */   }
/*     */ 
/*     */   public QName getQName(int index) {
/*  81 */     return this.beanMetaData.getQName(index);
/*     */   }
/*     */ 
/*     */   public Object getData(int index) {
/*  85 */     return this.beanMetaData.getData(index, this.parent.getData());
/*     */   }
/*     */ 
/*     */   public void setData(int index, Object data) {
/*  89 */     this.beanMetaData.setData(index, this.parent.getData(), data);
/*     */   }
/*     */ 
/*     */   public int size()
/*     */   {
/*  95 */     return this.attributes.length;
/*     */   }
/*     */ 
/*     */   public Object get(int index) {
/*  99 */     BeanAttribute attribute = this.attributes[index];
/*     */ 
/* 101 */     if (attribute == null) {
/* 102 */       attribute = createAttribute(this.parent, index);
/* 103 */       this.attributes[index] = attribute;
/*     */     }
/*     */ 
/* 106 */     return attribute;
/*     */   }
/*     */ 
/*     */   public boolean add(Object object) {
/* 110 */     throw new UnsupportedOperationException("add(Object) unsupported");
/*     */   }
/*     */ 
/*     */   public void add(int index, Object object) {
/* 114 */     throw new UnsupportedOperationException("add(int,Object) unsupported");
/*     */   }
/*     */ 
/*     */   public Object set(int index, Object object) {
/* 118 */     throw new UnsupportedOperationException("set(int,Object) unsupported");
/*     */   }
/*     */ 
/*     */   public boolean remove(Object object) {
/* 122 */     return false;
/*     */   }
/*     */ 
/*     */   public Object remove(int index) {
/* 126 */     BeanAttribute attribute = (BeanAttribute)get(index);
/* 127 */     Object oldValue = attribute.getValue();
/* 128 */     attribute.setValue(null);
/*     */ 
/* 130 */     return oldValue;
/*     */   }
/*     */ 
/*     */   public void clear() {
/* 134 */     int i = 0; for (int size = this.attributes.length; i < size; i++) {
/* 135 */       BeanAttribute attribute = this.attributes[i];
/*     */ 
/* 137 */       if (attribute != null)
/* 138 */         attribute.setValue(null);
/*     */     }
/*     */   }
/*     */ 
/*     */   protected BeanAttribute createAttribute(BeanElement element, int index)
/*     */   {
/* 146 */     return new BeanAttribute(this, index);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.bean.BeanAttributeList
 * JD-Core Version:    0.6.2
 */