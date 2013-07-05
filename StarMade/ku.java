/*    */ import org.schema.game.common.controller.SegmentController;
/*    */ import org.schema.schine.network.StateInterface;
/*    */ 
/*    */ public final class ku extends kp
/*    */ {
/*    */   public ku(StateInterface paramStateInterface, ki paramki)
/*    */   {
/* 10 */     super(paramStateInterface, paramki);
/*    */   }
/*    */ 
/*    */   public final String getUniqueIdentifier()
/*    */   {
/* 18 */     return this.a.getUniqueIdentifier() + "_AI";
/*    */   }
/*    */ 
/*    */   public final boolean isVolatile()
/*    */   {
/* 24 */     return false;
/*    */   }
/*    */ 
/*    */   protected final void b()
/*    */   {
/*    */   }
/*    */ 
/*    */   public final boolean b()
/*    */   {
/* 43 */     return a(kq.c).a();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ku
 * JD-Core Version:    0.6.2
 */