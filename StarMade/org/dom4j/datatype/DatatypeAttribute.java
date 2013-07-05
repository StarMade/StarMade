/*     */ package org.dom4j.datatype;
/*     */ 
/*     */ import com.sun.msv.datatype.DatabindableDatatype;
/*     */ import com.sun.msv.datatype.SerializationContext;
/*     */ import com.sun.msv.datatype.xsd.XSDatatype;
/*     */ import org.dom4j.Element;
/*     */ import org.dom4j.Namespace;
/*     */ import org.dom4j.QName;
/*     */ import org.dom4j.tree.AbstractAttribute;
/*     */ import org.relaxng.datatype.DatatypeException;
/*     */ import org.relaxng.datatype.ValidationContext;
/*     */ 
/*     */ public class DatatypeAttribute extends AbstractAttribute
/*     */   implements SerializationContext, ValidationContext
/*     */ {
/*     */   private Element parent;
/*     */   private QName qname;
/*     */   private XSDatatype datatype;
/*     */   private Object data;
/*     */   private String text;
/*     */ 
/*     */   public DatatypeAttribute(QName qname, XSDatatype datatype)
/*     */   {
/*  50 */     this.qname = qname;
/*  51 */     this.datatype = datatype;
/*     */   }
/*     */ 
/*     */   public DatatypeAttribute(QName qname, XSDatatype datatype, String text) {
/*  55 */     this.qname = qname;
/*  56 */     this.datatype = datatype;
/*  57 */     this.text = text;
/*  58 */     this.data = convertToValue(text);
/*     */   }
/*     */ 
/*     */   public String toString() {
/*  62 */     return getClass().getName() + hashCode() + " [Attribute: name " + getQualifiedName() + " value \"" + getValue() + "\" data: " + getData() + "]";
/*     */   }
/*     */ 
/*     */   public XSDatatype getXSDatatype()
/*     */   {
/*  73 */     return this.datatype;
/*     */   }
/*     */ 
/*     */   public String getNamespacePrefix(String uri)
/*     */   {
/*  79 */     Element parentElement = getParent();
/*     */ 
/*  81 */     if (parentElement != null) {
/*  82 */       Namespace namespace = parentElement.getNamespaceForURI(uri);
/*     */ 
/*  84 */       if (namespace != null) {
/*  85 */         return namespace.getPrefix();
/*     */       }
/*     */     }
/*     */ 
/*  89 */     return null;
/*     */   }
/*     */ 
/*     */   public String getBaseUri()
/*     */   {
/*  96 */     return null;
/*     */   }
/*     */ 
/*     */   public boolean isNotation(String notationName)
/*     */   {
/* 101 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean isUnparsedEntity(String entityName)
/*     */   {
/* 106 */     return true;
/*     */   }
/*     */ 
/*     */   public String resolveNamespacePrefix(String prefix)
/*     */   {
/* 111 */     if (prefix.equals(getNamespacePrefix())) {
/* 112 */       return getNamespaceURI();
/*     */     }
/* 114 */     Element parentElement = getParent();
/*     */ 
/* 116 */     if (parentElement != null) {
/* 117 */       Namespace namespace = parentElement.getNamespaceForPrefix(prefix);
/*     */ 
/* 120 */       if (namespace != null) {
/* 121 */         return namespace.getURI();
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 126 */     return null;
/*     */   }
/*     */ 
/*     */   public QName getQName()
/*     */   {
/* 132 */     return this.qname;
/*     */   }
/*     */ 
/*     */   public String getValue() {
/* 136 */     return this.text;
/*     */   }
/*     */ 
/*     */   public void setValue(String value) {
/* 140 */     validate(value);
/*     */ 
/* 142 */     this.text = value;
/* 143 */     this.data = convertToValue(value);
/*     */   }
/*     */ 
/*     */   public Object getData() {
/* 147 */     return this.data;
/*     */   }
/*     */ 
/*     */   public void setData(Object data) {
/* 151 */     String s = this.datatype.convertToLexicalValue(data, this);
/* 152 */     validate(s);
/* 153 */     this.text = s;
/* 154 */     this.data = data;
/*     */   }
/*     */ 
/*     */   public Element getParent() {
/* 158 */     return this.parent;
/*     */   }
/*     */ 
/*     */   public void setParent(Element parent) {
/* 162 */     this.parent = parent;
/*     */   }
/*     */ 
/*     */   public boolean supportsParent() {
/* 166 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean isReadOnly() {
/* 170 */     return false;
/*     */   }
/*     */ 
/*     */   protected void validate(String txt) throws IllegalArgumentException
/*     */   {
/*     */     try
/*     */     {
/* 177 */       this.datatype.checkValid(txt, this);
/*     */     } catch (DatatypeException e) {
/* 179 */       throw new IllegalArgumentException(e.getMessage());
/*     */     }
/*     */   }
/*     */ 
/*     */   protected Object convertToValue(String txt) {
/* 184 */     if ((this.datatype instanceof DatabindableDatatype)) {
/* 185 */       DatabindableDatatype bindable = this.datatype;
/*     */ 
/* 187 */       return bindable.createJavaObject(txt, this);
/*     */     }
/* 189 */     return this.datatype.createValue(txt, this);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.datatype.DatatypeAttribute
 * JD-Core Version:    0.6.2
 */