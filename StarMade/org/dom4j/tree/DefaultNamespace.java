/*     */ package org.dom4j.tree;
/*     */ 
/*     */ import org.dom4j.Element;
/*     */ import org.dom4j.Namespace;
/*     */ 
/*     */ public class DefaultNamespace extends Namespace
/*     */ {
/*     */   private Element parent;
/*     */ 
/*     */   public DefaultNamespace(String prefix, String uri)
/*     */   {
/*  36 */     super(prefix, uri);
/*     */   }
/*     */ 
/*     */   public DefaultNamespace(Element parent, String prefix, String uri)
/*     */   {
/*  50 */     super(prefix, uri);
/*  51 */     this.parent = parent;
/*     */   }
/*     */ 
/*     */   protected int createHashCode()
/*     */   {
/*  61 */     int hashCode = super.createHashCode();
/*     */ 
/*  63 */     if (this.parent != null) {
/*  64 */       hashCode ^= this.parent.hashCode();
/*     */     }
/*     */ 
/*  67 */     return hashCode;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/*  80 */     if ((object instanceof DefaultNamespace)) {
/*  81 */       DefaultNamespace that = (DefaultNamespace)object;
/*     */ 
/*  83 */       if (that.parent == this.parent) {
/*  84 */         return super.equals(object);
/*     */       }
/*     */     }
/*     */ 
/*  88 */     return false;
/*     */   }
/*     */ 
/*     */   public int hashCode() {
/*  92 */     return super.hashCode();
/*     */   }
/*     */ 
/*     */   public Element getParent() {
/*  96 */     return this.parent;
/*     */   }
/*     */ 
/*     */   public void setParent(Element parent) {
/* 100 */     this.parent = parent;
/*     */   }
/*     */ 
/*     */   public boolean supportsParent() {
/* 104 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean isReadOnly() {
/* 108 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.DefaultNamespace
 * JD-Core Version:    0.6.2
 */