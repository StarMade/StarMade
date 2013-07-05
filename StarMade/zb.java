/*     */ import java.nio.FloatBuffer;
/*     */ import javax.vecmath.Vector3f;
/*     */ import javax.vecmath.Vector4f;
/*     */ import org.lwjgl.BufferUtils;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import org.lwjgl.util.vector.Matrix4f;
/*     */ import org.schema.common.FastMath;
/*     */ import org.schema.schine.graphicsengine.camera.Camera;
/*     */ import org.schema.schine.graphicsengine.core.GlUtil;
/*     */ 
/*     */ public class zb extends yX
/*     */   implements zn
/*     */ {
/*  33 */   private zj jdField_a_of_type_Zj = d.k;
/*     */   private static zy jdField_a_of_type_Zy;
/*     */   private int jdField_a_of_type_Int;
/*     */   private static Matrix4f[] jdField_a_of_type_ArrayOfOrgLwjglUtilVectorMatrix4f;
/*  36 */   private static FloatBuffer c = BufferUtils.createFloatBuffer(16);
/*     */ 
/*  38 */   public zb(yW paramyW) { super(paramyW, 0.1F);
/*     */ 
/*  40 */     if ((!jdField_b_of_type_Boolean) && (d.k == null)) throw new AssertionError();
/*  41 */     jdField_a_of_type_ArrayOfOrgLwjglUtilVectorMatrix4f = new Matrix4f[300];
/*  42 */     for (paramyW = 0; paramyW < jdField_a_of_type_ArrayOfOrgLwjglUtilVectorMatrix4f.length; paramyW++) {
/*  43 */       jdField_a_of_type_ArrayOfOrgLwjglUtilVectorMatrix4f[paramyW] = new Matrix4f();
/*  44 */       jdField_a_of_type_ArrayOfOrgLwjglUtilVectorMatrix4f[paramyW].setIdentity();
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void b()
/*     */   {
/*  59 */     if (this.jdField_a_of_type_YW.b() > 0) {
/*  60 */       this.jdField_a_of_type_Zj.a = this;
/*  61 */       this.jdField_a_of_type_Zj.b();
/*  62 */       super.b();
/*  63 */       this.jdField_a_of_type_Zj.d();
/*     */     }
/*     */   }
/*     */ 
/*     */   protected final int a(int paramInt, yV paramyV)
/*     */   {
/*  73 */     paramyV.a(paramInt, this.jdField_a_of_type_JavaxVecmathVector3f);
/*  74 */     if (!xe.a().a(this.jdField_a_of_type_JavaxVecmathVector3f)) {
/*  75 */       return 0;
/*     */     }
/*     */ 
/*  78 */     this.jdField_a_of_type_JavaxVecmathVector4f.set(this.jdField_a_of_type_JavaxVecmathVector3f.x, this.jdField_a_of_type_JavaxVecmathVector3f.y, this.jdField_a_of_type_JavaxVecmathVector3f.z, paramyV.a(paramInt));
/*  79 */     paramyV.b(paramInt, this.jdField_b_of_type_JavaxVecmathVector3f);
/*  80 */     this.jdField_b_of_type_JavaxVecmathVector4f.set(this.jdField_b_of_type_JavaxVecmathVector3f.x, this.jdField_b_of_type_JavaxVecmathVector3f.y, this.jdField_b_of_type_JavaxVecmathVector3f.z, 0.0F);
/*  81 */     for (paramInt = 0; paramInt < 4; paramInt++) {
/*  82 */       GlUtil.a(jdField_a_of_type_JavaNioFloatBuffer, this.jdField_a_of_type_JavaxVecmathVector4f);
/*     */ 
/*  84 */       this.jdField_b_of_type_JavaxVecmathVector4f.w = jdField_a_of_type_ArrayOfFloat[paramInt];
/*  85 */       GlUtil.a(jdField_b_of_type_JavaNioFloatBuffer, this.jdField_b_of_type_JavaxVecmathVector4f);
/*     */     }
/*  87 */     return 4;
/*     */   }
/*     */ 
/*     */   public final void d() {
/*  91 */     GL11.glBindTexture(3553, 0);
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/*  98 */     this.jdField_a_of_type_Boolean = true;
/*     */ 
/* 100 */     if (jdField_a_of_type_Zy == null) {
/* 101 */       jdField_a_of_type_Zy = xe.a().a("star").a().a();
/*     */     }
/* 103 */     super.c();
/*     */   }
/*     */ 
/*     */   public final void a(zj paramzj)
/*     */   {
/* 117 */     c.rewind();
/* 118 */     jdField_a_of_type_ArrayOfOrgLwjglUtilVectorMatrix4f[FastMath.b(this.jdField_a_of_type_Int - jdField_a_of_type_ArrayOfOrgLwjglUtilVectorMatrix4f.length - 2, jdField_a_of_type_ArrayOfOrgLwjglUtilVectorMatrix4f.length - 1)].store(c);
/* 119 */     c.rewind();
/* 120 */     GlUtil.c(paramzj, "oldModelViewMatrix", c);
/*     */ 
/* 122 */     GlUtil.a(paramzj, "Param", new Vector4f(1.0F, 0.1F, 0.15F, 0.0005F));
/* 123 */     GL11.glBindTexture(3553, jdField_a_of_type_Zy.c);
/* 124 */     GlUtil.a(paramzj, "Texture", 0);
/*     */ 
/* 128 */     if ((
/* 128 */       paramzj = (za)this.jdField_a_of_type_YW).a > 
/* 128 */       0.015F)
/*     */     {
/* 130 */       jdField_a_of_type_ArrayOfOrgLwjglUtilVectorMatrix4f[this.jdField_a_of_type_Int].load(xe.a);
/*     */ 
/* 132 */       this.jdField_a_of_type_Int = FastMath.b(this.jdField_a_of_type_Int + 1, jdField_a_of_type_ArrayOfOrgLwjglUtilVectorMatrix4f.length - 1);
/*     */ 
/* 134 */       paramzj.a = 0.0F;
/*     */     }
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*  24 */     jdField_b_of_type_Boolean = !zb.class.desiredAssertionStatus();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     zb
 * JD-Core Version:    0.6.2
 */