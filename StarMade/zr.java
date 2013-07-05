/*    */ import org.lwjgl.opengl.GL11;
/*    */ import org.lwjgl.opengl.GL13;
/*    */ import org.schema.schine.graphicsengine.core.GlUtil;
/*    */ 
/*    */ public final class zr
/*    */   implements xg, zn
/*    */ {
/*    */   private xi jdField_a_of_type_Xi;
/*    */   private xi b;
/*    */   private zu jdField_a_of_type_Zu;
/*    */ 
/*    */   public zr(xi paramxi1, xi paramxi2, zu paramzu)
/*    */   {
/* 24 */     this.jdField_a_of_type_Xi = paramxi1;
/*    */ 
/* 26 */     this.b = paramxi2;
/* 27 */     this.jdField_a_of_type_Zu = paramzu;
/*    */   }
/*    */ 
/*    */   public final void a()
/*    */   {
/*    */   }
/*    */ 
/*    */   public final void b()
/*    */   {
/*    */     zj localzj;
/* 38 */     (
/* 39 */       localzj = d.u).a = 
/* 39 */       this;
/*    */ 
/* 41 */     this.b.d();
/* 42 */     GL11.glClear(16640);
/* 43 */     this.jdField_a_of_type_Xi.a(localzj);
/* 44 */     this.b.b();
/*    */   }
/*    */ 
/*    */   public final void d()
/*    */   {
/* 57 */     GL13.glActiveTexture(33984);
/* 58 */     GL11.glBindTexture(3553, 0);
/* 59 */     GL13.glActiveTexture(33985);
/* 60 */     GL11.glBindTexture(3553, 0);
/* 61 */     GL13.glActiveTexture(33984);
/*    */   }
/*    */ 
/*    */   public final void c()
/*    */   {
/*    */   }
/*    */ 
/*    */   public final void a(zj paramzj)
/*    */   {
/* 83 */     zu.d();
/*    */ 
/* 85 */     GlUtil.c(paramzj, "MVP", zq.a);
/*    */ 
/* 88 */     GlUtil.a(paramzj, "LumThresh", 1.0F - ((Float)xu.ad.a()).floatValue());
/*    */ 
/* 90 */     GL13.glActiveTexture(33984);
/* 91 */     GL11.glBindTexture(3553, this.jdField_a_of_type_Xi.a());
/* 92 */     GlUtil.a(paramzj, "RenderTex", 0);
/*    */ 
/* 94 */     GL13.glActiveTexture(33985);
/* 95 */     GL11.glBindTexture(3553, this.jdField_a_of_type_Zu.a);
/* 96 */     GlUtil.a(paramzj, "SilhouetteTex", 1);
/* 97 */     GL13.glActiveTexture(33984);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     zr
 * JD-Core Version:    0.6.2
 */