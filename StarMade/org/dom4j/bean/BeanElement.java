/*     */ package org.dom4j.bean;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.dom4j.Attribute;
/*     */ import org.dom4j.DocumentFactory;
/*     */ import org.dom4j.Element;
/*     */ import org.dom4j.Namespace;
/*     */ import org.dom4j.QName;
/*     */ import org.dom4j.tree.DefaultElement;
/*     */ import org.dom4j.tree.NamespaceStack;
/*     */ import org.xml.sax.Attributes;
/*     */ 
/*     */ public class BeanElement extends DefaultElement
/*     */ {
/*  32 */   private static final DocumentFactory DOCUMENT_FACTORY = BeanDocumentFactory.getInstance();
/*     */   private Object bean;
/*     */ 
/*     */   public BeanElement(String name, Object bean)
/*     */   {
/*  39 */     this(DOCUMENT_FACTORY.createQName(name), bean);
/*     */   }
/*     */ 
/*     */   public BeanElement(String name, Namespace namespace, Object bean) {
/*  43 */     this(DOCUMENT_FACTORY.createQName(name, namespace), bean);
/*     */   }
/*     */ 
/*     */   public BeanElement(QName qname, Object bean) {
/*  47 */     super(qname);
/*  48 */     this.bean = bean;
/*     */   }
/*     */ 
/*     */   public BeanElement(QName qname) {
/*  52 */     super(qname);
/*     */   }
/*     */ 
/*     */   public Object getData()
/*     */   {
/*  61 */     return this.bean;
/*     */   }
/*     */ 
/*     */   public void setData(Object data) {
/*  65 */     this.bean = data;
/*     */ 
/*  70 */     setAttributeList(null);
/*     */   }
/*     */ 
/*     */   public Attribute attribute(String name) {
/*  74 */     return getBeanAttributeList().attribute(name);
/*     */   }
/*     */ 
/*     */   public Attribute attribute(QName qname) {
/*  78 */     return getBeanAttributeList().attribute(qname);
/*     */   }
/*     */ 
/*     */   public Element addAttribute(String name, String value) {
/*  82 */     Attribute attribute = attribute(name);
/*     */ 
/*  84 */     if (attribute != null) {
/*  85 */       attribute.setValue(value);
/*     */     }
/*     */ 
/*  88 */     return this;
/*     */   }
/*     */ 
/*     */   public Element addAttribute(QName qName, String value) {
/*  92 */     Attribute attribute = attribute(qName);
/*     */ 
/*  94 */     if (attribute != null) {
/*  95 */       attribute.setValue(value);
/*     */     }
/*     */ 
/*  98 */     return this;
/*     */   }
/*     */ 
/*     */   public void setAttributes(List attributes) {
/* 102 */     throw new UnsupportedOperationException("Not implemented yet.");
/*     */   }
/*     */ 
/*     */   public void setAttributes(Attributes attributes, NamespaceStack namespaceStack, boolean noNamespaceAttributes)
/*     */   {
/* 108 */     String className = attributes.getValue("class");
/*     */ 
/* 110 */     if (className != null) {
/*     */       try {
/* 112 */         Class beanClass = Class.forName(className, true, BeanElement.class.getClassLoader());
/*     */ 
/* 114 */         setData(beanClass.newInstance());
/*     */ 
/* 116 */         for (int i = 0; i < attributes.getLength(); i++) {
/* 117 */           String attributeName = attributes.getLocalName(i);
/*     */ 
/* 119 */           if (!"class".equalsIgnoreCase(attributeName))
/* 120 */             addAttribute(attributeName, attributes.getValue(i));
/*     */         }
/*     */       }
/*     */       catch (Exception ex)
/*     */       {
/* 125 */         ((BeanDocumentFactory)getDocumentFactory()).handleException(ex);
/*     */       }
/*     */     }
/*     */     else
/* 129 */       super.setAttributes(attributes, namespaceStack, noNamespaceAttributes);
/*     */   }
/*     */ 
/*     */   protected DocumentFactory getDocumentFactory()
/*     */   {
/* 137 */     return DOCUMENT_FACTORY;
/*     */   }
/*     */ 
/*     */   protected BeanAttributeList getBeanAttributeList() {
/* 141 */     return (BeanAttributeList)attributeList();
/*     */   }
/*     */ 
/*     */   protected List createAttributeList()
/*     */   {
/* 151 */     return new BeanAttributeList(this);
/*     */   }
/*     */ 
/*     */   protected List createAttributeList(int size) {
/* 155 */     return new BeanAttributeList(this);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.bean.BeanElement
 * JD-Core Version:    0.6.2
 */