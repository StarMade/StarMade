/*     */ package org.dom4j.tree;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ import org.dom4j.Attribute;
/*     */ import org.dom4j.Element;
/*     */ import org.dom4j.Namespace;
/*     */ import org.dom4j.Node;
/*     */ import org.dom4j.QName;
/*     */ import org.dom4j.Visitor;
/*     */ 
/*     */ public abstract class AbstractAttribute extends AbstractNode
/*     */   implements Attribute
/*     */ {
/*     */   public short getNodeType()
/*     */   {
/*  31 */     return 2;
/*     */   }
/*     */ 
/*     */   public void setNamespace(Namespace namespace) {
/*  35 */     String msg = "This Attribute is read only and cannot be changed";
/*  36 */     throw new UnsupportedOperationException(msg);
/*     */   }
/*     */ 
/*     */   public String getText() {
/*  40 */     return getValue();
/*     */   }
/*     */ 
/*     */   public void setText(String text) {
/*  44 */     setValue(text);
/*     */   }
/*     */ 
/*     */   public void setValue(String value) {
/*  48 */     String msg = "This Attribute is read only and cannot be changed";
/*  49 */     throw new UnsupportedOperationException(msg);
/*     */   }
/*     */ 
/*     */   public Object getData() {
/*  53 */     return getValue();
/*     */   }
/*     */ 
/*     */   public void setData(Object data) {
/*  57 */     setValue(data == null ? null : data.toString());
/*     */   }
/*     */ 
/*     */   public String toString() {
/*  61 */     return super.toString() + " [Attribute: name " + getQualifiedName() + " value \"" + getValue() + "\"]";
/*     */   }
/*     */ 
/*     */   public String asXML()
/*     */   {
/*  66 */     return getQualifiedName() + "=\"" + getValue() + "\"";
/*     */   }
/*     */ 
/*     */   public void write(Writer writer) throws IOException {
/*  70 */     writer.write(getQualifiedName());
/*  71 */     writer.write("=\"");
/*  72 */     writer.write(getValue());
/*  73 */     writer.write("\"");
/*     */   }
/*     */ 
/*     */   public void accept(Visitor visitor) {
/*  77 */     visitor.visit(this);
/*     */   }
/*     */ 
/*     */   public Namespace getNamespace()
/*     */   {
/*  82 */     return getQName().getNamespace();
/*     */   }
/*     */ 
/*     */   public String getName() {
/*  86 */     return getQName().getName();
/*     */   }
/*     */ 
/*     */   public String getNamespacePrefix() {
/*  90 */     return getQName().getNamespacePrefix();
/*     */   }
/*     */ 
/*     */   public String getNamespaceURI() {
/*  94 */     return getQName().getNamespaceURI();
/*     */   }
/*     */ 
/*     */   public String getQualifiedName() {
/*  98 */     return getQName().getQualifiedName();
/*     */   }
/*     */ 
/*     */   public String getPath(Element context) {
/* 102 */     StringBuffer result = new StringBuffer();
/*     */ 
/* 104 */     Element parent = getParent();
/*     */ 
/* 106 */     if ((parent != null) && (parent != context)) {
/* 107 */       result.append(parent.getPath(context));
/* 108 */       result.append("/");
/*     */     }
/*     */ 
/* 111 */     result.append("@");
/*     */ 
/* 113 */     String uri = getNamespaceURI();
/* 114 */     String prefix = getNamespacePrefix();
/*     */ 
/* 116 */     if ((uri == null) || (uri.length() == 0) || (prefix == null) || (prefix.length() == 0))
/*     */     {
/* 118 */       result.append(getName());
/*     */     }
/* 120 */     else result.append(getQualifiedName());
/*     */ 
/* 123 */     return result.toString();
/*     */   }
/*     */ 
/*     */   public String getUniquePath(Element context) {
/* 127 */     StringBuffer result = new StringBuffer();
/*     */ 
/* 129 */     Element parent = getParent();
/*     */ 
/* 131 */     if ((parent != null) && (parent != context)) {
/* 132 */       result.append(parent.getUniquePath(context));
/* 133 */       result.append("/");
/*     */     }
/*     */ 
/* 136 */     result.append("@");
/*     */ 
/* 138 */     String uri = getNamespaceURI();
/* 139 */     String prefix = getNamespacePrefix();
/*     */ 
/* 141 */     if ((uri == null) || (uri.length() == 0) || (prefix == null) || (prefix.length() == 0))
/*     */     {
/* 143 */       result.append(getName());
/*     */     }
/* 145 */     else result.append(getQualifiedName());
/*     */ 
/* 148 */     return result.toString();
/*     */   }
/*     */ 
/*     */   protected Node createXPathResult(Element parent) {
/* 152 */     return new DefaultAttribute(parent, getQName(), getValue());
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.AbstractAttribute
 * JD-Core Version:    0.6.2
 */