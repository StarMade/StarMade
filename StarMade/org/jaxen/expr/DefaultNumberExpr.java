/*    */ package org.jaxen.expr;
/*    */ 
/*    */ import org.jaxen.Context;
/*    */ 
/*    */ class DefaultNumberExpr extends DefaultExpr
/*    */   implements NumberExpr
/*    */ {
/*    */   private static final long serialVersionUID = -6021898973386269611L;
/*    */   private Double number;
/*    */ 
/*    */   DefaultNumberExpr(Double number)
/*    */   {
/* 65 */     this.number = number;
/*    */   }
/*    */ 
/*    */   public Number getNumber()
/*    */   {
/* 70 */     return this.number;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 75 */     return "[(DefaultNumberExpr): " + getNumber() + "]";
/*    */   }
/*    */ 
/*    */   public String getText()
/*    */   {
/* 80 */     return getNumber().toString();
/*    */   }
/*    */ 
/*    */   public Object evaluate(Context context)
/*    */   {
/* 85 */     return getNumber();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultNumberExpr
 * JD-Core Version:    0.6.2
 */