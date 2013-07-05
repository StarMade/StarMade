/*     */ import javax.vecmath.Vector3f;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import org.lwjgl.opengl.GL13;
/*     */ import org.schema.schine.graphicsengine.core.GlUtil;
/*     */ 
/*     */ public class zv
/*     */   implements zn
/*     */ {
/*  24 */   private static int jdField_a_of_type_Int = 33992;
/*  25 */   private static int jdField_b_of_type_Int = 8;
/*     */ 
/*  27 */   private static int c = 33991;
/*  28 */   private static int d = 7;
/*     */ 
/*  30 */   private static int e = 33990;
/*  31 */   private static int f = 6;
/*     */ 
/*  33 */   private int g = -1;
/*     */   public Vector3f a;
/*     */   private zy jdField_a_of_type_Zy;
/*     */   private float jdField_a_of_type_Float;
/*     */   private float jdField_b_of_type_Float;
/*     */   private xi jdField_a_of_type_Xi;
/*     */ 
/*     */   public zv()
/*     */   {
/*  34 */     this.jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/*     */   }
/*     */ 
/*     */   public final void d()
/*     */   {
/*  51 */     GL13.glActiveTexture(c);
/*  52 */     GL11.glBindTexture(3553, 0);
/*     */ 
/*  54 */     GL13.glActiveTexture(jdField_a_of_type_Int);
/*  55 */     GL11.glBindTexture(3553, 0);
/*     */ 
/*  57 */     GL13.glActiveTexture(e);
/*  58 */     GL11.glBindTexture(3553, 0);
/*     */ 
/*  60 */     GL13.glActiveTexture(33984);
/*     */   }
/*     */ 
/*     */   public final void a(int paramInt, xi paramxi)
/*     */   {
/*  69 */     this.g = paramInt;
/*  70 */     this.jdField_a_of_type_Xi = paramxi;
/*     */   }
/*     */ 
/*     */   public final boolean a() {
/*  74 */     this.jdField_a_of_type_Float = (this.jdField_a_of_type_JavaxVecmathVector3f.x / xm.b());
/*  75 */     this.jdField_b_of_type_Float = (1.0F - this.jdField_a_of_type_JavaxVecmathVector3f.y / xm.a());
/*     */ 
/*  77 */     return true;
/*     */   }
/*     */ 
/*     */   public final void a(zj paramzj)
/*     */   {
/*  89 */     if (this.jdField_a_of_type_Zy == null) {
/*  90 */       this.jdField_a_of_type_Zy = xe.a().a("lens_flare").a().a();
/*     */     }
/*     */ 
/* 100 */     if ((!jdField_a_of_type_Boolean) && (this.g < 0)) throw new AssertionError();
/*     */ 
/* 102 */     GlUtil.a(paramzj, "screenRatio", xm.a() / xm.b());
/*     */ 
/* 110 */     GlUtil.a(paramzj, "lightPositionOnScreen", this.jdField_a_of_type_Float, this.jdField_b_of_type_Float);
/*     */ 
/* 118 */     GlUtil.a(paramzj, "Param1", this.jdField_a_of_type_Float, this.jdField_b_of_type_Float, 1.0F / this.jdField_a_of_type_Xi.jdField_b_of_type_Int, 0.5F / this.jdField_a_of_type_Zy.jdField_b_of_type_Int);
/* 119 */     GlUtil.a(paramzj, "Param2", 1.0F, 0.2F, 1.0F, 1.0F);
/*     */ 
/* 121 */     GL13.glActiveTexture(c);
/* 122 */     GL11.glBindTexture(3553, this.g);
/* 123 */     GlUtil.a(paramzj, "firstPass", d);
/*     */ 
/* 125 */     GL13.glActiveTexture(jdField_a_of_type_Int);
/* 126 */     GL11.glBindTexture(3553, this.jdField_a_of_type_Zy.c);
/* 127 */     GlUtil.a(paramzj, "Texture", jdField_b_of_type_Int);
/*     */ 
/* 129 */     GL13.glActiveTexture(e);
/* 130 */     GL11.glBindTexture(3553, this.jdField_a_of_type_Xi.jdField_a_of_type_Int);
/* 131 */     GlUtil.a(paramzj, "Scene", f);
/*     */ 
/* 137 */     GL13.glActiveTexture(33984);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     zv
 * JD-Core Version:    0.6.2
 */