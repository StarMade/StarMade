/*     */ import javax.vecmath.Vector3f;
/*     */ import javax.vecmath.Vector4f;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public final class yb extends ya
/*     */ {
/*  64 */   private Vector4f jdField_a_of_type_JavaxVecmathVector4f = new Vector4f(0.5F, 0.5F, 0.5F, 0.7F);
/*     */   private xK jdField_a_of_type_XK;
/*     */   private float jdField_a_of_type_Float;
/*     */   private float b;
/*     */   private float c;
/*     */ 
/*     */   public yb(xK paramxK)
/*     */   {
/*  85 */     this.c = 300.0F;
/*  86 */     this.jdField_a_of_type_Float = 1.4F;
/*     */ 
/*  88 */     this.b = 0.2F;
/*  89 */     this.jdField_a_of_type_XK = paramxK;
/*     */   }
/*     */ 
/*     */   public final void b()
/*     */   {
/*  98 */     GL11.glDisable(2929);
/*  99 */     GL11.glDisable(2896);
/* 100 */     GL11.glEnable(3042);
/* 101 */     GL11.glEnable(2903);
/*     */     yg localyg;
/* 103 */     (
/* 104 */       localyg = xe.a().a("box"))
/* 104 */       .a(a().x, a().y, a().z);
/* 105 */     localyg.b(false);
/* 106 */     localyg.a(true);
/* 107 */     localyg.a.set(this.jdField_a_of_type_Float, this.b, 1.0F);
/* 108 */     localyg.c(this.jdField_a_of_type_JavaxVecmathVector4f);
/* 109 */     localyg.b();
/*     */ 
/* 111 */     this.jdField_a_of_type_XK.a.set(1.0F, 1.0F, 1.0F);
/* 112 */     this.jdField_a_of_type_XK.a(a().x, a().y, a().z);
/* 113 */     this.jdField_a_of_type_XK.a.jdField_a_of_type_Float = (-(this.c / 2.0F) + 9.0F);
/* 114 */     this.jdField_a_of_type_XK.a.b = (0.0F - this.jdField_a_of_type_XK.a() - 3.0F);
/* 115 */     this.jdField_a_of_type_XK.d();
/* 116 */     this.jdField_a_of_type_XK.b();
/*     */ 
/* 119 */     GL11.glDisable(3042);
/* 120 */     GL11.glDisable(2903);
/* 121 */     GL11.glEnable(2929);
/* 122 */     GL11.glEnable(2896);
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     yb
 * JD-Core Version:    0.6.2
 */