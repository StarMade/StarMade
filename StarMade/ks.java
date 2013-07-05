/*    */ import org.schema.game.common.controller.SegmentController;
/*    */ import org.schema.schine.network.StateInterface;
/*    */ 
/*    */ public final class ks extends kp
/*    */ {
/*    */   public ks(StateInterface paramStateInterface, jA paramjA)
/*    */   {
/* 10 */     super(paramStateInterface, paramjA);
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
/* 44 */     return a(kq.c).a();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ks
 * JD-Core Version:    0.6.2
 */