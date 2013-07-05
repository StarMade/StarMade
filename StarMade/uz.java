/*    */ import org.schema.game.common.data.world.Segment;
/*    */ 
/*    */ public final class uz extends uC
/*    */ {
/*    */   private boolean a;
/*    */ 
/*    */   public uz(q paramq1, uu[] paramArrayOfuu, q paramq2, q paramq3, int paramInt)
/*    */   {
/* 18 */     super(paramq1, paramArrayOfuu, paramq2, paramq3, paramInt, (byte)0);
/*    */   }
/*    */ 
/*    */   protected final short a(q paramq)
/*    */   {
/* 27 */     if (paramq.equals(this.c)) {
/* 28 */       this.a = true;
/* 29 */       return 120;
/*    */     }
/* 31 */     return 32767;
/*    */   }
/*    */ 
/*    */   public final uF a(Segment paramSegment)
/*    */   {
/*    */     uE localuE;
/* 35 */     (
/* 36 */       localuE = new uE())
/* 36 */       .a(this, paramSegment);
/* 37 */     this.a = false;
/*    */ 
/* 39 */     return localuE;
/*    */   }
/*    */ 
/*    */   public final boolean a() {
/* 43 */     return this.a;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     uz
 * JD-Core Version:    0.6.2
 */