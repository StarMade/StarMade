/*     */ import java.io.IOException;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import org.schema.schine.graphicsengine.core.GlUtil;
/*     */ 
/*     */ public class ze extends zf
/*     */   implements zn
/*     */ {
/*     */   private zj jdField_a_of_type_Zj;
/*     */   private boolean jdField_b_of_type_Boolean;
/*     */   private static zy jdField_a_of_type_Zy;
/*  30 */   private float jdField_b_of_type_Float = 0.0F;
/*     */ 
/*     */   public ze() {
/*  33 */     super((byte)0);
/*  34 */     this.jdField_a_of_type_Zj = d.F;
/*  35 */     if ((!c) && (d.F == null)) throw new AssertionError();
/*     */   }
/*     */ 
/*     */   public final void b()
/*     */   {
/*  47 */     if (!this.jdField_b_of_type_Boolean) {
/*  48 */       c();
/*     */     }
/*  50 */     if (this.jdField_a_of_type_YW.b() > 1) {
/*  51 */       this.jdField_a_of_type_Zj.a = this;
/*  52 */       this.jdField_a_of_type_Zj.b();
/*  53 */       super.b();
/*  54 */       this.jdField_a_of_type_Zj.d();
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void d()
/*     */   {
/*  61 */     GL11.glBindTexture(32879, 0);
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/*  73 */     this.jdField_a_of_type_Boolean = false;
/*     */ 
/*  75 */     if (jdField_a_of_type_Zy == null) {
/*     */       try {
/*  77 */         jdField_a_of_type_Zy = xe.a().a("data/./effects/noise.jpg", true);
/*     */       }
/*     */       catch (IOException localIOException) {
/*  80 */         localIOException.printStackTrace();
/*     */       }
/*     */     }
/*     */ 
/*  82 */     super.c();
/*  83 */     this.jdField_b_of_type_Boolean = true;
/*     */   }
/*     */ 
/*     */   public final void a(xq paramxq) {
/*  87 */     this.jdField_b_of_type_Float += paramxq.a();
/*  88 */     while (this.jdField_b_of_type_Float > 1.0F)
/*  89 */       this.jdField_b_of_type_Float -= 1.0F;
/*     */   }
/*     */ 
/*     */   public final void a(zj paramzj)
/*     */   {
/* 100 */     GlUtil.a(paramzj, "time", this.jdField_b_of_type_Float);
/* 101 */     GL11.glBindTexture(3553, jdField_a_of_type_Zy.c);
/* 102 */     GlUtil.a(paramzj, "tex", 0);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ze
 * JD-Core Version:    0.6.2
 */