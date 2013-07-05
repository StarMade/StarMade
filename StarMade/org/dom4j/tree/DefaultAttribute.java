/*     */ package org.dom4j.tree;
/*     */ 
/*     */ import org.dom4j.Element;
/*     */ import org.dom4j.Namespace;
/*     */ import org.dom4j.QName;
/*     */ 
/*     */ public class DefaultAttribute extends FlyweightAttribute
/*     */ {
/*     */   private Element parent;
/*     */ 
/*     */   public DefaultAttribute(QName qname)
/*     */   {
/*  28 */     super(qname);
/*     */   }
/*     */ 
/*     */   public DefaultAttribute(QName qname, String value) {
/*  32 */     super(qname, value);
/*     */   }
/*     */ 
/*     */   public DefaultAttribute(Element parent, QName qname, String value) {
/*  36 */     super(qname, value);
/*  37 */     this.parent = parent;
/*     */   }
/*     */ 
/*     */   public DefaultAttribute(String name, String value)
/*     */   {
/*  50 */     super(name, value);
/*     */   }
/*     */ 
/*     */   public DefaultAttribute(String name, String value, Namespace namespace)
/*     */   {
/*  65 */     super(name, value, namespace);
/*     */   }
/*     */ 
/*     */   public DefaultAttribute(Element parent, String name, String value, Namespace namespace)
/*     */   {
/*  83 */     super(name, value, namespace);
/*  84 */     this.parent = parent;
/*     */   }
/*     */ 
/*     */   public void setValue(String value) {
/*  88 */     this.value = value;
/*     */   }
/*     */ 
/*     */   public Element getParent() {
/*  92 */     return this.parent;
/*     */   }
/*     */ 
/*     */   public void setParent(Element parent) {
/*  96 */     this.parent = parent;
/*     */   }
/*     */ 
/*     */   public boolean supportsParent() {
/* 100 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean isReadOnly() {
/* 104 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.DefaultAttribute
 * JD-Core Version:    0.6.2
 */