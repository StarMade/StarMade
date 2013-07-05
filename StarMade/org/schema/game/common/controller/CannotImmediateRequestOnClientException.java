/*    */ package org.schema.game.common.controller;
/*    */ 
/*    */ import q;
/*    */ 
/*    */ public class CannotImmediateRequestOnClientException extends RuntimeException
/*    */ {
/*    */   private static final long serialVersionUID = -7838434728382878333L;
/*    */   private final q a;
/*    */ 
/*    */   public CannotImmediateRequestOnClientException(q paramq)
/*    */   {
/* 14 */     this.a = new q(paramq);
/*    */   }
/*    */ 
/*    */   public final q a()
/*    */   {
/* 21 */     return this.a;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.CannotImmediateRequestOnClientException
 * JD-Core Version:    0.6.2
 */