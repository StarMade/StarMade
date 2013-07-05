/*    */ import org.lwjgl.opengl.GL11;
/*    */ import org.schema.schine.graphicsengine.core.GlUtil;
/*    */ 
/*    */ public class zd extends yX
/*    */   implements zn
/*    */ {
/* 27 */   private zj jdField_a_of_type_Zj = d.d;
/*    */   private boolean b;
/*    */   private static zy jdField_a_of_type_Zy;
/*    */ 
/*    */   public zd(yW paramyW, float paramFloat)
/*    */   {
/* 32 */     super(paramyW, paramFloat);
/*    */ 
/* 34 */     if ((!c) && (d.d == null)) throw new AssertionError();
/* 35 */     this.jdField_a_of_type_Boolean = true;
/*    */   }
/*    */ 
/*    */   public final void b()
/*    */   {
/* 47 */     if (!this.b) {
/* 48 */       c();
/*    */     }
/* 50 */     if (this.jdField_a_of_type_YW.b() > 0)
/*    */     {
/* 52 */       this.jdField_a_of_type_Zj.a = this;
/* 53 */       this.jdField_a_of_type_Zj.b();
/* 54 */       super.b();
/* 55 */       this.jdField_a_of_type_Zj.d();
/*    */     }
/*    */   }
/*    */ 
/*    */   public final void d()
/*    */   {
/* 61 */     GL11.glBindTexture(3553, 0);
/*    */   }
/*    */ 
/*    */   public final void c()
/*    */   {
/* 74 */     if (jdField_a_of_type_Zy == null) {
/* 75 */       jdField_a_of_type_Zy = xe.a().a("starSprite").a().a();
/*    */     }
/* 77 */     super.c();
/* 78 */     this.b = true;
/*    */   }
/*    */ 
/*    */   public final void a(zj paramzj)
/*    */   {
/* 95 */     GlUtil.a(paramzj, "time", 0.0F);
/* 96 */     GL11.glBindTexture(3553, jdField_a_of_type_Zy.c);
/* 97 */     GlUtil.a(paramzj, "tex", 0);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     zd
 * JD-Core Version:    0.6.2
 */