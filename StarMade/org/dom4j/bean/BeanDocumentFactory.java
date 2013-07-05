/*    */ package org.dom4j.bean;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import org.dom4j.Attribute;
/*    */ import org.dom4j.DocumentFactory;
/*    */ import org.dom4j.Element;
/*    */ import org.dom4j.QName;
/*    */ import org.dom4j.tree.DefaultAttribute;
/*    */ import org.xml.sax.Attributes;
/*    */ 
/*    */ public class BeanDocumentFactory extends DocumentFactory
/*    */ {
/* 33 */   private static BeanDocumentFactory singleton = new BeanDocumentFactory();
/*    */ 
/*    */   public static DocumentFactory getInstance()
/*    */   {
/* 43 */     return singleton;
/*    */   }
/*    */ 
/*    */   public Element createElement(QName qname)
/*    */   {
/* 48 */     Object bean = createBean(qname);
/*    */ 
/* 50 */     if (bean == null) {
/* 51 */       return new BeanElement(qname);
/*    */     }
/* 53 */     return new BeanElement(qname, bean);
/*    */   }
/*    */ 
/*    */   public Element createElement(QName qname, Attributes attributes)
/*    */   {
/* 58 */     Object bean = createBean(qname, attributes);
/*    */ 
/* 60 */     if (bean == null) {
/* 61 */       return new BeanElement(qname);
/*    */     }
/* 63 */     return new BeanElement(qname, bean);
/*    */   }
/*    */ 
/*    */   public Attribute createAttribute(Element owner, QName qname, String value)
/*    */   {
/* 68 */     return new DefaultAttribute(qname, value);
/*    */   }
/*    */ 
/*    */   protected Object createBean(QName qname)
/*    */   {
/* 73 */     return null;
/*    */   }
/*    */ 
/*    */   protected Object createBean(QName qname, Attributes attributes) {
/* 77 */     String value = attributes.getValue("class");
/*    */ 
/* 79 */     if (value != null) {
/*    */       try {
/* 81 */         Class beanClass = Class.forName(value, true, BeanDocumentFactory.class.getClassLoader());
/*    */ 
/* 84 */         return beanClass.newInstance();
/*    */       } catch (Exception e) {
/* 86 */         handleException(e);
/*    */       }
/*    */     }
/*    */ 
/* 90 */     return null;
/*    */   }
/*    */ 
/*    */   protected void handleException(Exception e)
/*    */   {
/* 95 */     System.out.println("#### Warning: couldn't create bean: " + e);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.bean.BeanDocumentFactory
 * JD-Core Version:    0.6.2
 */