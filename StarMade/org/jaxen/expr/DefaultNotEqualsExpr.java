/*    */ package org.jaxen.expr;
/*    */ 
/*    */ import org.jaxen.function.NumberFunction;
/*    */ 
/*    */ class DefaultNotEqualsExpr extends DefaultEqualityExpr
/*    */ {
/*    */   private static final long serialVersionUID = -8001267398136979152L;
/*    */ 
/*    */   DefaultNotEqualsExpr(Expr lhs, Expr rhs)
/*    */   {
/* 63 */     super(lhs, rhs);
/*    */   }
/*    */ 
/*    */   public String getOperator()
/*    */   {
/* 68 */     return "!=";
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 73 */     return "[(DefaultNotEqualsExpr): " + getLHS() + ", " + getRHS() + "]";
/*    */   }
/*    */ 
/*    */   protected boolean evaluateObjectObject(Object lhs, Object rhs)
/*    */   {
/* 78 */     if (eitherIsNumber(lhs, rhs))
/*    */     {
/* 81 */       if ((NumberFunction.isNaN((Double)lhs)) || (NumberFunction.isNaN((Double)rhs)))
/*    */       {
/* 83 */         return true;
/*    */       }
/*    */     }
/*    */ 
/* 87 */     return !lhs.equals(rhs);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultNotEqualsExpr
 * JD-Core Version:    0.6.2
 */