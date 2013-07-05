/*    */ package org.jaxen.expr;
/*    */ 
/*    */ import org.jaxen.Context;
/*    */ import org.jaxen.JaxenException;
/*    */ import org.jaxen.function.NumberFunction;
/*    */ 
/*    */ class DefaultMultiplyExpr extends DefaultMultiplicativeExpr
/*    */ {
/*    */   private static final long serialVersionUID = 2760053878102260365L;
/*    */ 
/*    */   DefaultMultiplyExpr(Expr lhs, Expr rhs)
/*    */   {
/* 64 */     super(lhs, rhs);
/*    */   }
/*    */ 
/*    */   public String getOperator()
/*    */   {
/* 70 */     return "*";
/*    */   }
/*    */ 
/*    */   public Object evaluate(Context context) throws JaxenException
/*    */   {
/* 75 */     Number lhsValue = NumberFunction.evaluate(getLHS().evaluate(context), context.getNavigator());
/*    */ 
/* 77 */     Number rhsValue = NumberFunction.evaluate(getRHS().evaluate(context), context.getNavigator());
/*    */ 
/* 80 */     double result = lhsValue.doubleValue() * rhsValue.doubleValue();
/*    */ 
/* 82 */     return new Double(result);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultMultiplyExpr
 * JD-Core Version:    0.6.2
 */