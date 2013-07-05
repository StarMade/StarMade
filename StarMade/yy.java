/*    */ import javax.vecmath.Vector4f;
/*    */ import org.schema.schine.network.client.ClientState;
/*    */ 
/*    */ public final class yy extends yx
/*    */ {
/*    */   private yz a;
/*    */ 
/*    */   public yy(ClientState paramClientState, float paramFloat1, float paramFloat2, Vector4f paramVector4f, yz paramyz)
/*    */   {
/* 14 */     super(paramClientState, paramFloat1, paramFloat2, paramVector4f);
/* 15 */     this.a = paramVector4f;
/* 16 */     this.a = paramyz;
/*    */   }
/*    */ 
/*    */   public final void b()
/*    */   {
/* 28 */     if (this.a != null) {
/* 29 */       this.a.b();
/* 30 */       if (this.a.g) {
/* 31 */         this.a.l();
/*    */       }
/*    */ 
/*    */     }
/*    */ 
/* 36 */     super.b();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     yy
 * JD-Core Version:    0.6.2
 */