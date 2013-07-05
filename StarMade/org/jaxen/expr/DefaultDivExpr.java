/*    */ package org.jaxen.expr;
/*    */ 
/*    */ import org.jaxen.Context;
/*    */ import org.jaxen.JaxenException;
/*    */ import org.jaxen.function.NumberFunction;
/*    */ 
/*    */ class DefaultDivExpr extends DefaultMultiplicativeExpr
/*    */ {
/*    */   private static final long serialVersionUID = 6318739386201615441L;
/*    */ 
/*    */   DefaultDivExpr(Expr lhs, Expr rhs)
/*    */   {
/* 65 */     super(lhs, rhs);
/*    */   }
/*    */ 
/*    */   public String getOperator()
/*    */   {
/* 71 */     return "div";
/*    */   }
/*    */ 
/*    */   public Object evaluate(Context context) throws JaxenException
/*    */   {
/* 76 */     Number lhsValue = NumberFunction.evaluate(getLHS().evaluate(context), context.getNavigator());
/*    */ 
/* 78 */     Number rhsValue = NumberFunction.evaluate(getRHS().evaluate(context), context.getNavigator());
/*    */ 
/* 81 */     double result = lhsValue.doubleValue() / rhsValue.doubleValue();
/*    */ 
/* 83 */     return new Double(result);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultDivExpr
 * JD-Core Version:    0.6.2
 */