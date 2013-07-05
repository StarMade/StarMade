/*     */ package org.jaxen;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ class QualifiedName
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 2734958615642751535L;
/*     */   private String namespaceURI;
/*     */   private String localName;
/*     */ 
/*     */   QualifiedName(String namespaceURI, String localName)
/*     */   {
/*  74 */     if (namespaceURI == null) namespaceURI = "";
/*  75 */     this.namespaceURI = namespaceURI;
/*  76 */     this.localName = localName;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/*  81 */     return this.localName.hashCode() ^ this.namespaceURI.hashCode();
/*     */   }
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/*  90 */     QualifiedName other = (QualifiedName)o;
/*  91 */     return (this.namespaceURI.equals(other.namespaceURI)) && (other.localName.equals(this.localName));
/*     */   }
/*     */ 
/*     */   String getClarkForm()
/*     */   {
/*  99 */     if ("".equals(this.namespaceURI)) return this.localName;
/* 100 */     return "{" + this.namespaceURI + "}" + ":" + this.localName;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.QualifiedName
 * JD-Core Version:    0.6.2
 */