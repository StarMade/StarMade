/*     */ import java.nio.FloatBuffer;
/*     */ import org.lwjgl.BufferUtils;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import org.lwjgl.opengl.GL13;
/*     */ import org.schema.schine.graphicsengine.core.GlUtil;
/*     */ 
/*     */ public final class zs
/*     */   implements xg, zn
/*     */ {
/*     */   private xi jdField_a_of_type_Xi;
/*     */   private xi b;
/*     */   private xi c;
/*     */   private float[] jdField_a_of_type_ArrayOfFloat;
/*  25 */   private FloatBuffer jdField_a_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(10);
/*     */ 
/*     */   public zs(xi paramxi1, xi paramxi2, xi paramxi3)
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
/*  56 */       localzj = d.v).a = 
/*  56 */       this;
/*     */ 
/*  59 */     this.c.d();
/*  60 */     GL11.glClear(16640);
/*  61 */     this.jdField_a_of_type_Xi.a(localzj);
/*  62 */     this.c.b();
/*     */   }
/*     */ 
/*     */   public final void d()
/*     */   {
/*  75 */     GL13.glActiveTexture(33984);
/*  76 */     GL11.glBindTexture(3553, 0);
/*  77 */     GL13.glActiveTexture(33985);
/*  78 */     GL11.glBindTexture(3553, 0);
/*  79 */     GL13.glActiveTexture(33984);
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void a(zj paramzj)
/*     */   {
/* 112 */     GlUtil.c(paramzj, "MVP", zq.jdField_a_of_type_JavaNioFloatBuffer);
/*     */ 
/* 121 */     GlUtil.a(paramzj, "Height", this.b.c);
/*     */ 
/* 124 */     this.jdField_a_of_type_JavaNioFloatBuffer.rewind();
/* 125 */     GlUtil.a(paramzj, "Weight", this.jdField_a_of_type_JavaNioFloatBuffer);
/*     */ 
/* 130 */     GL13.glActiveTexture(33985);
/* 131 */     GL11.glBindTexture(3553, this.b.a());
/* 132 */     GlUtil.a(paramzj, "BlurTex", 1);
/*     */ 
/* 134 */     GL13.glActiveTexture(33984);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     zs
 * JD-Core Version:    0.6.2
 */