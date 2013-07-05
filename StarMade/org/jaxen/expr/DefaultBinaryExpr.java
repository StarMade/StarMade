/*     */ package org.jaxen.expr;
/*     */ 
/*     */ abstract class DefaultBinaryExpr extends DefaultExpr
/*     */   implements BinaryExpr
/*     */ {
/*     */   private Expr lhs;
/*     */   private Expr rhs;
/*     */ 
/*     */   DefaultBinaryExpr(Expr lhs, Expr rhs)
/*     */   {
/*  59 */     this.lhs = lhs;
/*  60 */     this.rhs = rhs;
/*     */   }
/*     */ 
/*     */   public Expr getLHS()
/*     */   {
/*  65 */     return this.lhs;
/*     */   }
/*     */ 
/*     */   public Expr getRHS()
/*     */   {
/*  70 */     return this.rhs;
/*     */   }
/*     */ 
/*     */   public void setLHS(Expr lhs)
/*     */   {
/*  75 */     this.lhs = lhs;
/*     */   }
/*     */ 
/*     */   public void setRHS(Expr rhs)
/*     */   {
/*  80 */     this.rhs = rhs;
/*     */   }
/*     */ 
/*     */   public abstract String getOperator();
/*     */ 
/*     */   public String getText()
/*     */   {
/*  87 */     return "(" + getLHS().getText() + " " + getOperator() + " " + getRHS().getText() + ")";
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/*  92 */     return "[" + getClass().getName() + ": " + getLHS() + ", " + getRHS() + "]";
/*     */   }
/*     */ 
/*     */   public Expr simplify()
/*     */   {
/*  97 */     setLHS(getLHS().simplify());
/*  98 */     setRHS(getRHS().simplify());
/*     */ 
/* 100 */     return this;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultBinaryExpr
 * JD-Core Version:    0.6.2
 */