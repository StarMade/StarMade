/*    */ package org.jaxen.expr;
/*    */ 
/*    */ import org.jaxen.Context;
/*    */ import org.jaxen.JaxenException;
/*    */ import org.jaxen.Navigator;
/*    */ import org.jaxen.function.BooleanFunction;
/*    */ 
/*    */ class DefaultAndExpr extends DefaultLogicalExpr
/*    */ {
/*    */   private static final long serialVersionUID = -5237984010263103742L;
/*    */ 
/*    */   DefaultAndExpr(Expr lhs, Expr rhs)
/*    */   {
/* 65 */     super(lhs, rhs);
/*    */   }
/*    */ 
/*    */   public String getOperator()
/*    */   {
/* 71 */     return "and";
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 76 */     return "[(DefaultAndExpr): " + getLHS() + ", " + getRHS() + "]";
/*    */   }
/*    */ 
/*    */   public Object evaluate(Context context) throws JaxenException
/*    */   {
/* 81 */     Navigator nav = context.getNavigator();
/* 82 */     Boolean lhsValue = BooleanFunction.evaluate(getLHS().evaluate(context), nav);
/*    */ 
/* 84 */     if (!lhsValue.booleanValue())
/*    */     {
/* 86 */       return Boolean.FALSE;
/*    */     }
/*    */ 
/* 91 */     Boolean rhsValue = BooleanFunction.evaluate(getRHS().evaluate(context), nav);
/*    */ 
/* 93 */     if (!rhsValue.booleanValue())
/*    */     {
/* 95 */       return Boolean.FALSE;
/*    */     }
/*    */ 
/* 98 */     return Boolean.TRUE;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultAndExpr
 * JD-Core Version:    0.6.2
 */