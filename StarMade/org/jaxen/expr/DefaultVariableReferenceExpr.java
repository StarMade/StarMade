/*     */ package org.jaxen.expr;
/*     */ 
/*     */ import org.jaxen.Context;
/*     */ import org.jaxen.UnresolvableException;
/*     */ 
/*     */ class DefaultVariableReferenceExpr extends DefaultExpr
/*     */   implements VariableReferenceExpr
/*     */ {
/*     */   private static final long serialVersionUID = 8832095437149358674L;
/*     */   private String prefix;
/*     */   private String localName;
/*     */ 
/*     */   DefaultVariableReferenceExpr(String prefix, String variableName)
/*     */   {
/*  65 */     this.prefix = prefix;
/*  66 */     this.localName = variableName;
/*     */   }
/*     */ 
/*     */   public String getPrefix()
/*     */   {
/*  71 */     return this.prefix;
/*     */   }
/*     */ 
/*     */   public String getVariableName()
/*     */   {
/*  76 */     return this.localName;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/*  81 */     return "[(DefaultVariableReferenceExpr): " + getQName() + "]";
/*     */   }
/*     */ 
/*     */   private String getQName() {
/*  85 */     if ("".equals(this.prefix))
/*     */     {
/*  87 */       return this.localName;
/*     */     }
/*  89 */     return this.prefix + ":" + this.localName;
/*     */   }
/*     */ 
/*     */   public String getText()
/*     */   {
/*  94 */     return "$" + getQName();
/*     */   }
/*     */ 
/*     */   public Object evaluate(Context context) throws UnresolvableException
/*     */   {
/*  99 */     String prefix = getPrefix();
/* 100 */     String namespaceURI = null;
/*     */ 
/* 102 */     if ((prefix != null) && (!"".equals(prefix))) {
/* 103 */       namespaceURI = context.translateNamespacePrefixToUri(prefix);
/*     */     }
/*     */ 
/* 106 */     return context.getVariableValue(namespaceURI, prefix, this.localName);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultVariableReferenceExpr
 * JD-Core Version:    0.6.2
 */