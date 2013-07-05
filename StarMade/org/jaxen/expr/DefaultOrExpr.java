/*     */ package org.jaxen.expr;
/*     */ 
/*     */ import org.jaxen.Context;
/*     */ import org.jaxen.JaxenException;
/*     */ import org.jaxen.Navigator;
/*     */ import org.jaxen.function.BooleanFunction;
/*     */ 
/*     */ class DefaultOrExpr extends DefaultLogicalExpr
/*     */ {
/*     */   private static final long serialVersionUID = 4894552680753026730L;
/*     */ 
/*     */   DefaultOrExpr(Expr lhs, Expr rhs)
/*     */   {
/*  67 */     super(lhs, rhs);
/*     */   }
/*     */ 
/*     */   public String getOperator()
/*     */   {
/*  73 */     return "or";
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/*  78 */     return "[(DefaultOrExpr): " + getLHS() + ", " + getRHS() + "]";
/*     */   }
/*     */ 
/*     */   public Object evaluate(Context context) throws JaxenException
/*     */   {
/*  83 */     Navigator nav = context.getNavigator();
/*  84 */     Boolean lhsValue = BooleanFunction.evaluate(getLHS().evaluate(context), nav);
/*     */ 
/*  86 */     if (lhsValue.booleanValue())
/*     */     {
/*  88 */       return Boolean.TRUE;
/*     */     }
/*     */ 
/*  93 */     Boolean rhsValue = BooleanFunction.evaluate(getRHS().evaluate(context), nav);
/*     */ 
/*  95 */     if (rhsValue.booleanValue())
/*     */     {
/*  97 */       return Boolean.TRUE;
/*     */     }
/*     */ 
/* 100 */     return Boolean.FALSE;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultOrExpr
 * JD-Core Version:    0.6.2
 */