/*    */ public final class bh
/*    */ {
/* 12 */   public long a = 117L;
/*    */ 
/*    */   public bh(bh parambh)
/*    */   {
/* 27 */     this.a = parambh.a;
/*    */   }
/*    */ 
/*    */   public bh()
/*    */   {
/*    */   }
/*    */ 
/*    */   public final void a(boolean paramBoolean, long paramLong)
/*    */   {
/* 55 */     if (paramBoolean) {
/* 56 */       this.a |= paramLong; return;
/*    */     }
/* 58 */     this.a &= (paramLong ^ 0xFFFFFFFF);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     bh
 * JD-Core Version:    0.6.2
 */