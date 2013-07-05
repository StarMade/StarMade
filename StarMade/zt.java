/*     */ import java.nio.FloatBuffer;
/*     */ import org.lwjgl.BufferUtils;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import org.lwjgl.opengl.GL13;
/*     */ import org.schema.schine.graphicsengine.core.GlUtil;
/*     */ 
/*     */ public final class zt
/*     */   implements xg, zn
/*     */ {
/*     */   private xi jdField_a_of_type_Xi;
/*     */   private xi b;
/*     */   private xi c;
/*     */   private float[] jdField_a_of_type_ArrayOfFloat;
/*  25 */   private FloatBuffer jdField_a_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(10);
/*     */ 
/*     */   public zt(xi paramxi1, xi paramxi2, xi paramxi3)
/*     */   {
/*  29 */     this.jdField_a_of_type_Xi = paramxi1;
/*     */ 
/*  31 */     this.b = paramxi2;
/*  32 */     this.c = paramxi3;
/*  33 */     this.jdField_a_of_type_ArrayOfFloat = zq.a();
/*  34 */     this.jdField_a_of_type_JavaNioFloatBuffer.rewind();
/*  35 */     for (paramxi1 = 9; paramxi1 >= 0; paramxi1--) {
/*  36 */       this.jdField_a_of_type_JavaNioFloatBuffer.put(this.jdField_a_of_type_ArrayOfFloat[paramxi1]);
/*     */     }
/*  38 */     this.jdField_a_of_type_JavaNioFloatBuffer.rewind();
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void b()
/*     */   {
/*     */     zj localzj;
/*  55 */     (
/*  56 */       localzj = d.w).a = 
/*  56 */       this;
/*  57 */     this.jdField_a_of_type_Xi.a(localzj);
/*     */   }
/*     */ 
/*     */   public final void d()
/*     */   {
/*  69 */     GL13.glActiveTexture(33984);
/*  70 */     GL11.glBindTexture(3553, 0);
/*  71 */     GL13.glActiveTexture(33985);
/*  72 */     GL11.glBindTexture(3553, 0);
/*  73 */     GL13.glActiveTexture(33984);
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void a(zj paramzj)
/*     */   {
/* 106 */     GlUtil.c(paramzj, "MVP", zq.jdField_a_of_type_JavaNioFloatBuffer);
/*     */ 
/* 115 */     GlUtil.a(paramzj, "Width", this.b.b);
/*     */ 
/* 120 */     this.jdField_a_of_type_JavaNioFloatBuffer.rewind();
/* 121 */     GlUtil.a(paramzj, "Weight", this.jdField_a_of_type_JavaNioFloatBuffer);
/*     */ 
/* 123 */     GL13.glActiveTexture(33984);
/* 124 */     GL11.glBindTexture(3553, this.jdField_a_of_type_Xi.a());
/* 125 */     GlUtil.a(paramzj, "RenderTex", 0);
/*     */ 
/* 127 */     GL13.glActiveTexture(33985);
/* 128 */     GL11.glBindTexture(3553, this.c.a());
/* 129 */     GlUtil.a(paramzj, "BlurTex", 1);
/*     */ 
/* 131 */     GL13.glActiveTexture(33984);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     zt
 * JD-Core Version:    0.6.2
 */