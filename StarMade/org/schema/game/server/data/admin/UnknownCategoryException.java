/*    */ package org.schema.game.server.data.admin;
/*    */ 
/*    */ public class UnknownCategoryException extends Exception
/*    */ {
/*    */   private static final long serialVersionUID = -2713992827958593228L;
/*    */   private String a;
/*    */ 
/*    */   public UnknownCategoryException(String paramString)
/*    */   {
/* 12 */     this.a = paramString;
/*    */   }
/*    */ 
/*    */   public final String a()
/*    */   {
/* 19 */     return this.a;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.server.data.admin.UnknownCategoryException
 * JD-Core Version:    0.6.2
 */