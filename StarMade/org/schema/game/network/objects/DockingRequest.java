/*    */ package org.schema.game.network.objects;
/*    */ 
/*    */ import q;
/*    */ 
/*    */ public class DockingRequest
/*    */ {
/*    */   public boolean dock;
/*    */   public String id;
/*    */   public q pos;
/*    */ 
/*    */   public DockingRequest()
/*    */   {
/*    */   }
/*    */ 
/*    */   public DockingRequest(boolean paramBoolean, String paramString, q paramq)
/*    */   {
/* 15 */     set(paramBoolean, paramString, paramq);
/*    */   }
/*    */ 
/*    */   public void set(boolean paramBoolean, String paramString, q paramq) {
/* 19 */     this.dock = paramBoolean;
/* 20 */     this.id = paramString;
/* 21 */     this.pos = paramq;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.DockingRequest
 * JD-Core Version:    0.6.2
 */