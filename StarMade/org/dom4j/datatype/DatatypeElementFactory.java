/*     */ package org.dom4j.datatype;
/*     */ 
/*     */ import com.sun.msv.datatype.xsd.XSDatatype;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.dom4j.Attribute;
/*     */ import org.dom4j.DocumentFactory;
/*     */ import org.dom4j.Element;
/*     */ import org.dom4j.QName;
/*     */ 
/*     */ public class DatatypeElementFactory extends DocumentFactory
/*     */ {
/*     */   private QName elementQName;
/*  37 */   private Map attributeXSDatatypes = new HashMap();
/*     */ 
/*  43 */   private Map childrenXSDatatypes = new HashMap();
/*     */ 
/*     */   public DatatypeElementFactory(QName elementQName) {
/*  46 */     this.elementQName = elementQName;
/*     */   }
/*     */ 
/*     */   public QName getQName()
/*     */   {
/*  55 */     return this.elementQName;
/*     */   }
/*     */ 
/*     */   public XSDatatype getAttributeXSDatatype(QName attributeQName)
/*     */   {
/*  68 */     return (XSDatatype)this.attributeXSDatatypes.get(attributeQName);
/*     */   }
/*     */ 
/*     */   public void setAttributeXSDatatype(QName attributeQName, XSDatatype type)
/*     */   {
/*  81 */     this.attributeXSDatatypes.put(attributeQName, type);
/*     */   }
/*     */ 
/*     */   public XSDatatype getChildElementXSDatatype(QName qname)
/*     */   {
/*  94 */     return (XSDatatype)this.childrenXSDatatypes.get(qname);
/*     */   }
/*     */ 
/*     */   public void setChildElementXSDatatype(QName qname, XSDatatype dataType) {
/*  98 */     this.childrenXSDatatypes.put(qname, dataType);
/*     */   }
/*     */ 
/*     */   public Element createElement(QName qname)
/*     */   {
/* 106 */     XSDatatype dataType = getChildElementXSDatatype(qname);
/*     */ 
/* 108 */     if (dataType != null) {
/* 109 */       return new DatatypeElement(qname, dataType);
/*     */     }
/*     */ 
/* 112 */     DocumentFactory factory = qname.getDocumentFactory();
/*     */ 
/* 114 */     if ((factory instanceof DatatypeElementFactory)) {
/* 115 */       DatatypeElementFactory dtFactory = (DatatypeElementFactory)factory;
/* 116 */       dataType = dtFactory.getChildElementXSDatatype(qname);
/*     */ 
/* 118 */       if (dataType != null) {
/* 119 */         return new DatatypeElement(qname, dataType);
/*     */       }
/*     */     }
/*     */ 
/* 123 */     return super.createElement(qname);
/*     */   }
/*     */ 
/*     */   public Attribute createAttribute(Element owner, QName qname, String value) {
/* 127 */     XSDatatype dataType = getAttributeXSDatatype(qname);
/*     */ 
/* 129 */     if (dataType == null) {
/* 130 */       return super.createAttribute(owner, qname, value);
/*     */     }
/* 132 */     return new DatatypeAttribute(qname, dataType, value);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.datatype.DatatypeElementFactory
 * JD-Core Version:    0.6.2
 */