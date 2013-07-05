/*    */ package org.jaxen.expr;
/*    */ 
/*    */ import org.jaxen.Context;
/*    */ import org.jaxen.JaxenException;
/*    */ 
/*    */ class DefaultPredicate
/*    */   implements Predicate
/*    */ {
/*    */   private static final long serialVersionUID = -4140068594075364971L;
/*    */   private Expr expr;
/*    */ 
/*    */   DefaultPredicate(Expr expr)
/*    */   {
/* 65 */     setExpr(expr);
/*    */   }
/*    */ 
/*    */   public Expr getExpr()
/*    */   {
/* 70 */     return this.expr;
/*    */   }
/*    */ 
/*    */   public void setExpr(Expr expr)
/*    */   {
/* 75 */     this.expr = expr;
/*    */   }
/*    */ 
/*    */   public String getText()
/*    */   {
/* 80 */     return "[" + getExpr().getText() + "]";
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 85 */     return "[(DefaultPredicate): " + getExpr() + "]";
/*    */   }
/*    */ 
/*    */   public void simplify()
/*    */   {
/* 90 */     setExpr(getExpr().simplify());
/*    */   }
/*    */ 
/*    */   public Object evaluate(Context context) throws JaxenException
/*    */   {
/* 95 */     return getExpr().evaluate(context);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultPredicate
 * JD-Core Version:    0.6.2
 */