/*    */ import javax.vecmath.Vector4f;
/*    */ import org.schema.schine.network.client.ClientState;
/*    */ 
/*    */ public final class gc extends eX
/*    */ {
/*    */   private ys b;
/*    */ 
/*    */   public gc(ClientState paramClientState, ys paramys, lL paramlL)
/*    */   {
/* 18 */     super(paramClientState, paramys, "Rate", "Rate " + paramlL.a);
/* 19 */     this.a = false;
/* 20 */     this.b = paramys;
/*    */   }
/*    */ 
/*    */   public final void c()
/*    */   {
/* 28 */     super.c();
/*    */ 
/* 30 */     for (int i = 0; i < 10; i++) {
/* 31 */       float f = i / 10.0F;
/*    */       yN localyN;
/* 32 */       (
/* 37 */         localyN = new yN(a(), 30, 30, new Vector4f(1.0F - f, f, 0.2F, 1.0F), new Vector4f(1.0F, 1.0F, 1.0F, 1.0F), d.e(), String.valueOf(i + 1), this.b)).a = 
/* 37 */         new Integer(i);
/* 38 */       if (i < 9) {
/* 39 */         localyN.b(6, 0);
/*    */       }
/* 41 */       int j = i * 40;
/*    */ 
/* 45 */       localyN.a(j, 35.0F, 0.0F);
/* 46 */       this.a.a(localyN);
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     gc
 * JD-Core Version:    0.6.2
 */