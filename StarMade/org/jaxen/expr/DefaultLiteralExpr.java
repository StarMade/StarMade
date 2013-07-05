/*    */ package org.jaxen.expr;
/*    */ 
/*    */ import org.jaxen.Context;
/*    */ 
/*    */ class DefaultLiteralExpr extends DefaultExpr
/*    */   implements LiteralExpr
/*    */ {
/*    */   private static final long serialVersionUID = -953829179036273338L;
/*    */   private String literal;
/*    */ 
/*    */   DefaultLiteralExpr(String literal)
/*    */   {
/* 64 */     this.literal = literal;
/*    */   }
/*    */ 
/*    */   public String getLiteral()
/*    */   {
/* 69 */     return this.literal;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 74 */     return "[(DefaultLiteralExpr): " + getLiteral() + "]";
/*    */   }
/*    */ 
/*    */   public String getText()
/*    */   {
/* 80 */     if (this.literal.indexOf('"') == -1) {
/* 81 */       return "\"" + getLiteral() + "\"";
/*    */     }
/*    */ 
/* 84 */     return "'" + getLiteral() + "'";
/*    */   }
/*    */ 
/*    */   public Object evaluate(Context context)
/*    */   {
/* 91 */     return getLiteral();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultLiteralExpr
 * JD-Core Version:    0.6.2
 */