/*    */ package org.jaxen.expr;
/*    */ 
/*    */ import org.jaxen.Context;
/*    */ import org.jaxen.JaxenException;
/*    */ import org.jaxen.function.NumberFunction;
/*    */ 
/*    */ class DefaultMinusExpr extends DefaultAdditiveExpr
/*    */ {
/*    */   private static final long serialVersionUID = 6468563688098527800L;
/*    */ 
/*    */   DefaultMinusExpr(Expr lhs, Expr rhs)
/*    */   {
/* 66 */     super(lhs, rhs);
/*    */   }
/*    */ 
/*    */   public String getOperator()
/*    */   {
/* 72 */     return "-";
/*    */   }
/*    */ 
/*    */   public Object evaluate(Context context) throws JaxenException
/*    */   {
/* 77 */     Number lhsValue = NumberFunction.evaluate(getLHS().evaluate(context), context.getNavigator());
/*    */ 
/* 79 */     Number rhsValue = NumberFunction.evaluate(getRHS().evaluate(context), context.getNavigator());
/*    */ 
/* 82 */     double result = lhsValue.doubleValue() - rhsValue.doubleValue();
/* 83 */     return new Double(result);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultMinusExpr
 * JD-Core Version:    0.6.2
 */