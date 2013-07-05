/*     */ package org.dom4j.datatype;
/*     */ 
/*     */ import com.sun.msv.datatype.DatabindableDatatype;
/*     */ import com.sun.msv.datatype.SerializationContext;
/*     */ import com.sun.msv.datatype.xsd.XSDatatype;
/*     */ import org.dom4j.Element;
/*     */ import org.dom4j.Namespace;
/*     */ import org.dom4j.Node;
/*     */ import org.dom4j.QName;
/*     */ import org.dom4j.tree.DefaultElement;
/*     */ import org.relaxng.datatype.DatatypeException;
/*     */ import org.relaxng.datatype.ValidationContext;
/*     */ 
/*     */ public class DatatypeElement extends DefaultElement
/*     */   implements SerializationContext, ValidationContext
/*     */ {
/*     */   private XSDatatype datatype;
/*     */   private Object data;
/*     */ 
/*     */   public DatatypeElement(QName qname, XSDatatype datatype)
/*     */   {
/*  42 */     super(qname);
/*  43 */     this.datatype = datatype;
/*     */   }
/*     */ 
/*     */   public DatatypeElement(QName qname, int attributeCount, XSDatatype type) {
/*  47 */     super(qname, attributeCount);
/*  48 */     this.datatype = type;
/*     */   }
/*     */ 
/*     */   public String toString() {
/*  52 */     return getClass().getName() + hashCode() + " [Element: <" + getQualifiedName() + " attributes: " + attributeList() + " data: " + getData() + " />]";
/*     */   }
/*     */ 
/*     */   public XSDatatype getXSDatatype()
/*     */   {
/*  63 */     return this.datatype;
/*     */   }
/*     */ 
/*     */   public String getNamespacePrefix(String uri)
/*     */   {
/*  69 */     Namespace namespace = getNamespaceForURI(uri);
/*     */ 
/*  71 */     return namespace != null ? namespace.getPrefix() : null;
/*     */   }
/*     */ 
/*     */   public String getBaseUri()
/*     */   {
/*  78 */     return null;
/*     */   }
/*     */ 
/*     */   public boolean isNotation(String notationName)
/*     */   {
/*  83 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean isUnparsedEntity(String entityName)
/*     */   {
/*  88 */     return true;
/*     */   }
/*     */ 
/*     */   public String resolveNamespacePrefix(String prefix) {
/*  92 */     Namespace namespace = getNamespaceForPrefix(prefix);
/*     */ 
/*  94 */     if (namespace != null) {
/*  95 */       return namespace.getURI();
/*     */     }
/*     */ 
/*  98 */     return null;
/*     */   }
/*     */ 
/*     */   public Object getData()
/*     */   {
/* 104 */     if (this.data == null) {
/* 105 */       String text = getTextTrim();
/*     */ 
/* 107 */       if ((text != null) && (text.length() > 0)) {
/* 108 */         if ((this.datatype instanceof DatabindableDatatype)) {
/* 109 */           DatabindableDatatype bind = this.datatype;
/* 110 */           this.data = bind.createJavaObject(text, this);
/*     */         } else {
/* 112 */           this.data = this.datatype.createValue(text, this);
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 117 */     return this.data;
/*     */   }
/*     */ 
/*     */   public void setData(Object data) {
/* 121 */     String s = this.datatype.convertToLexicalValue(data, this);
/* 122 */     validate(s);
/* 123 */     this.data = data;
/* 124 */     setText(s);
/*     */   }
/*     */ 
/*     */   public Element addText(String text) {
/* 128 */     validate(text);
/*     */ 
/* 130 */     return super.addText(text);
/*     */   }
/*     */ 
/*     */   public void setText(String text) {
/* 134 */     validate(text);
/* 135 */     super.setText(text);
/*     */   }
/*     */ 
/*     */   protected void childAdded(Node node)
/*     */   {
/* 148 */     this.data = null;
/* 149 */     super.childAdded(node);
/*     */   }
/*     */ 
/*     */   protected void childRemoved(Node node)
/*     */   {
/* 159 */     this.data = null;
/* 160 */     super.childRemoved(node);
/*     */   }
/*     */ 
/*     */   protected void validate(String text) throws IllegalArgumentException {
/*     */     try {
/* 165 */       this.datatype.checkValid(text, this);
/*     */     } catch (DatatypeException e) {
/* 167 */       throw new IllegalArgumentException(e.getMessage());
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.datatype.DatatypeElement
 * JD-Core Version:    0.6.2
 */