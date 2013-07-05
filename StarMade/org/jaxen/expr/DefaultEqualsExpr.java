/*    */ package org.jaxen.expr;
/*    */ 
/*    */ import org.jaxen.function.NumberFunction;
/*    */ 
/*    */ class DefaultEqualsExpr extends DefaultEqualityExpr
/*    */ {
/*    */   private static final long serialVersionUID = -8327599812627931648L;
/*    */ 
/*    */   DefaultEqualsExpr(Expr lhs, Expr rhs)
/*    */   {
/* 63 */     super(lhs, rhs);
/*    */   }
/*    */ 
/*    */   public String getOperator()
/*    */   {
/* 68 */     return "=";
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 73 */     return "[(DefaultEqualsExpr): " + getLHS() + ", " + getRHS() + "]";
/*    */   }
/*    */ 
/*    */   protected boolean evaluateObjectObject(Object lhs, Object rhs)
/*    */   {
/* 78 */     if (eitherIsNumber(lhs, rhs))
/*    */     {
/* 80 */       if ((NumberFunction.isNaN((Double)lhs)) || (NumberFunction.isNaN((Double)rhs)))
/*    */       {
/* 82 */         return false;
/*    */       }
/*    */     }
/*    */ 
/* 86 */     return lhs.equals(rhs);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultEqualsExpr
 * JD-Core Version:    0.6.2
 */