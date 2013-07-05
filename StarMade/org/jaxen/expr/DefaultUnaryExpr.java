/*    */ package org.jaxen.expr;
/*    */ 
/*    */ import org.jaxen.Context;
/*    */ import org.jaxen.JaxenException;
/*    */ import org.jaxen.function.NumberFunction;
/*    */ 
/*    */ class DefaultUnaryExpr extends DefaultExpr
/*    */   implements UnaryExpr
/*    */ {
/*    */   private static final long serialVersionUID = 2303714238683092334L;
/*    */   private Expr expr;
/*    */ 
/*    */   DefaultUnaryExpr(Expr expr)
/*    */   {
/* 66 */     this.expr = expr;
/*    */   }
/*    */ 
/*    */   public Expr getExpr()
/*    */   {
/* 71 */     return this.expr;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 76 */     return "[(DefaultUnaryExpr): " + getExpr() + "]";
/*    */   }
/*    */ 
/*    */   public String getText()
/*    */   {
/* 81 */     return "-(" + getExpr().getText() + ")";
/*    */   }
/*    */ 
/*    */   public Expr simplify()
/*    */   {
/* 86 */     this.expr = this.expr.simplify();
/*    */ 
/* 88 */     return this;
/*    */   }
/*    */ 
/*    */   public Object evaluate(Context context) throws JaxenException
/*    */   {
/* 93 */     Number number = NumberFunction.evaluate(getExpr().evaluate(context), context.getNavigator());
/*    */ 
/* 96 */     return new Double(number.doubleValue() * -1.0D);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultUnaryExpr
 * JD-Core Version:    0.6.2
 */