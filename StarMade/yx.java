/*     */ import javax.vecmath.Vector4f;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import org.schema.schine.graphicsengine.core.GlUtil;
/*     */ import org.schema.schine.network.client.ClientState;
/*     */ 
/*     */ public class yx extends yr
/*     */ {
/*     */   protected int a;
/*     */   protected boolean a;
/*     */   public Vector4f a;
/*     */   public float c;
/*     */ 
/*     */   public yx(ClientState paramClientState, float paramFloat1, float paramFloat2, Vector4f paramVector4f)
/*     */   {
/*  19 */     super(paramClientState, paramFloat1, paramFloat2);
/*  20 */     this.jdField_a_of_type_JavaxVecmathVector4f = paramVector4f;
/*     */   }
/*     */ 
/*     */   public void b()
/*     */   {
/*  31 */     if (!this.jdField_a_of_type_Boolean) {
/*  32 */       f();
/*     */     }
/*  34 */     GlUtil.d();
/*  35 */     r();
/*  36 */     GL11.glBlendFunc(770, 771);
/*  37 */     GL11.glEnable(3042);
/*  38 */     GL11.glDisable(2929);
/*  39 */     GL11.glDisable(2896);
/*  40 */     GL11.glEnable(2903);
/*  41 */     GL11.glDisable(3553);
/*     */ 
/*  43 */     GlUtil.a(this.jdField_a_of_type_JavaxVecmathVector4f.x, this.jdField_a_of_type_JavaxVecmathVector4f.y, this.jdField_a_of_type_JavaxVecmathVector4f.z, this.jdField_a_of_type_JavaxVecmathVector4f.w);
/*  44 */     if ((!jdField_b_of_type_Boolean) && (!this.jdField_a_of_type_Boolean)) throw new AssertionError();
/*  45 */     GL11.glCallList(this.jdField_a_of_type_Int);
/*  46 */     GL11.glDisable(2903);
/*     */ 
/*  48 */     GlUtil.a(1.0F, 1.0F, 1.0F, 1.0F);
/*  49 */     GL11.glDisable(3042);
/*  50 */     GL11.glEnable(2929);
/*  51 */     GlUtil.c();
/*     */ 
/*  53 */     super.b();
/*     */   }
/*     */ 
/*     */   public final void e()
/*     */   {
/*  58 */     super.b();
/*     */   }
/*     */ 
/*     */   protected void f()
/*     */   {
/*  63 */     if (this.jdField_a_of_type_Int != 0) {
/*  64 */       GL11.glDeleteLists(this.jdField_a_of_type_Int, 1);
/*     */     }
/*  66 */     this.jdField_a_of_type_Int = GL11.glGenLists(1);
/*     */ 
/*  68 */     GL11.glNewList(this.jdField_a_of_type_Int, 4864);
/*     */ 
/*  70 */     if (this.c == 0.0F) {
/*  71 */       GL11.glBegin(7);
/*  72 */       GL11.glVertex2f(0.0F, 0.0F);
/*  73 */       GL11.glVertex2f(0.0F, a());
/*  74 */       GL11.glVertex2f(b(), a());
/*  75 */       GL11.glVertex2f(b(), 0.0F);
/*     */     }
/*     */     else {
/*  78 */       GL11.glBegin(9);
/*  79 */       GL11.glVertex2f(0.0F, this.c);
/*  80 */       GL11.glVertex2f(0.0F, a() - this.c);
/*  81 */       GL11.glVertex2f(this.c, a());
/*  82 */       GL11.glVertex2f(b() - this.c, a());
/*  83 */       GL11.glVertex2f(b(), a() - this.c);
/*  84 */       GL11.glVertex2f(b(), this.c);
/*  85 */       GL11.glVertex2f(b() - this.c, 0.0F);
/*  86 */       GL11.glVertex2f(this.c, 0.0F);
/*  87 */       GL11.glVertex2f(this.c, this.c);
/*     */     }
/*     */ 
/*  90 */     GL11.glEnd();
/*  91 */     GL11.glEndList();
/*     */ 
/*  93 */     this.jdField_a_of_type_Boolean = true;
/*     */   }
/*     */ 
/*     */   public void c()
/*     */   {
/* 100 */     super.c();
/* 101 */     f();
/*     */   }
/*     */   public final void b(int paramInt) {
/* 104 */     this.jdField_a_of_type_Float = paramInt;
/* 105 */     this.jdField_a_of_type_Boolean = false;
/*     */   }
/*     */   public final void c(int paramInt) {
/* 108 */     this.jdField_b_of_type_Float = paramInt;
/* 109 */     this.jdField_a_of_type_Boolean = false;
/*     */   }
/*     */ 
/*     */   public final Vector4f a()
/*     */   {
/* 115 */     return this.jdField_a_of_type_JavaxVecmathVector4f;
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*  10 */     jdField_b_of_type_Boolean = !yx.class.desiredAssertionStatus();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     yx
 * JD-Core Version:    0.6.2
 */