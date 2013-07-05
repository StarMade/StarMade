/*     */ import java.nio.FloatBuffer;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.lwjgl.BufferUtils;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import org.lwjgl.opengl.GL13;
/*     */ import org.schema.schine.graphicsengine.core.GlUtil;
/*     */ 
/*     */ public final class zx
/*     */   implements zn
/*     */ {
/*  71 */   private float[] jdField_a_of_type_ArrayOfFloat = { 0.3F, 0.3F, 0.3F, 1.0F };
/*     */ 
/*  74 */   private float[] jdField_b_of_type_ArrayOfFloat = { 0.6F, 0.6F, 0.6F, 1.0F };
/*     */ 
/*  77 */   private float[] jdField_c_of_type_ArrayOfFloat = { 0.8F, 0.8F, 0.8F, 1.0F };
/*     */ 
/*  80 */   private float[] jdField_d_of_type_ArrayOfFloat = { 10.0F };
/*     */ 
/*  83 */   private static FloatBuffer jdField_a_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(4);
/*     */ 
/*  86 */   private static FloatBuffer jdField_b_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(4);
/*     */ 
/*  89 */   private static FloatBuffer jdField_c_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(4);
/*     */ 
/*  91 */   private static FloatBuffer jdField_d_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(1);
/*     */ 
/*  94 */   private boolean jdField_a_of_type_Boolean = false;
/*     */ 
/*  97 */   private boolean jdField_b_of_type_Boolean = false;
/*     */   private zy jdField_a_of_type_Zy;
/*     */   private String jdField_a_of_type_JavaLangString;
/*     */   private String jdField_b_of_type_JavaLangString;
/*     */   private zy jdField_b_of_type_Zy;
/*     */   private zy jdField_c_of_type_Zy;
/*     */   private boolean jdField_c_of_type_Boolean;
/*     */   private int jdField_a_of_type_Int;
/*     */ 
/*     */   public final void a()
/*     */   {
/* 136 */     jdField_a_of_type_JavaNioFloatBuffer.rewind();
/* 137 */     jdField_b_of_type_JavaNioFloatBuffer.rewind();
/* 138 */     jdField_c_of_type_JavaNioFloatBuffer.rewind();
/* 139 */     jdField_d_of_type_JavaNioFloatBuffer.rewind();
/*     */ 
/* 141 */     jdField_a_of_type_JavaNioFloatBuffer.put(this.jdField_a_of_type_ArrayOfFloat);
/* 142 */     jdField_b_of_type_JavaNioFloatBuffer.put(this.jdField_b_of_type_ArrayOfFloat);
/* 143 */     jdField_c_of_type_JavaNioFloatBuffer.put(this.jdField_c_of_type_ArrayOfFloat);
/* 144 */     jdField_d_of_type_JavaNioFloatBuffer.put(this.jdField_d_of_type_ArrayOfFloat);
/*     */ 
/* 146 */     jdField_a_of_type_JavaNioFloatBuffer.rewind();
/* 147 */     jdField_b_of_type_JavaNioFloatBuffer.rewind();
/* 148 */     jdField_c_of_type_JavaNioFloatBuffer.rewind();
/* 149 */     jdField_d_of_type_JavaNioFloatBuffer.rewind();
/*     */ 
/* 151 */     GL11.glMaterial(1032, 4608, jdField_a_of_type_JavaNioFloatBuffer);
/* 152 */     GL11.glMaterial(1032, 4609, jdField_b_of_type_JavaNioFloatBuffer);
/* 153 */     GL11.glMaterial(1032, 4610, jdField_c_of_type_JavaNioFloatBuffer);
/* 154 */     GL11.glMaterialf(1032, 5633, jdField_d_of_type_JavaNioFloatBuffer.get(0));
/*     */ 
/* 156 */     if (xu.z.b()) { xM.g(); if ((this.jdField_a_of_type_Boolean) && (this.jdField_b_of_type_Boolean) && (this.jdField_c_of_type_Boolean))
/*     */       {
/* 160 */         d.l.a = this;
/* 161 */         d.l.b(); return;
/*     */       } }
/* 163 */     if (this.jdField_a_of_type_Boolean)
/*     */     {
/* 165 */       if (this.jdField_a_of_type_Zy == null) {
/* 166 */         throw new IllegalArgumentException("no texture loaded for " + this.jdField_a_of_type_JavaLangString + " but sould be " + this.jdField_b_of_type_JavaLangString);
/*     */       }
/*     */ 
/* 169 */       this.jdField_a_of_type_Zy.a();
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void b()
/*     */   {
/* 184 */     if (this.jdField_a_of_type_Zy != null) {
/* 185 */       GL11.glDeleteTextures(3);
/*     */     }
/* 187 */     if (this.jdField_b_of_type_Boolean) {
/* 188 */       GL11.glDeleteTextures(3);
/*     */     }
/* 190 */     if (this.jdField_c_of_type_Boolean)
/* 191 */       GL11.glDeleteTextures(3);
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/* 202 */     if (xu.z.b()) { xM.g(); if ((this.jdField_a_of_type_Boolean) && (this.jdField_b_of_type_Boolean) && (this.jdField_c_of_type_Boolean))
/* 203 */         d.l
/* 204 */           .d();
/*     */     }
/*     */   }
/*     */ 
/*     */   public final String a()
/*     */   {
/* 232 */     return this.jdField_a_of_type_JavaLangString;
/*     */   }
/*     */ 
/*     */   public final zy a()
/*     */   {
/* 268 */     return this.jdField_a_of_type_Zy;
/*     */   }
/*     */ 
/*     */   public final String b()
/*     */   {
/* 277 */     return this.jdField_b_of_type_JavaLangString;
/*     */   }
/*     */ 
/*     */   public final boolean a()
/*     */   {
/* 302 */     return this.jdField_a_of_type_Boolean;
/*     */   }
/*     */ 
/*     */   public final void d()
/*     */   {
/* 321 */     GL13.glActiveTexture(33984);
/* 322 */     GL11.glDisable(3553);
/* 323 */     GL11.glBindTexture(3553, 0);
/*     */ 
/* 325 */     if (this.jdField_b_of_type_Boolean) {
/* 326 */       GL13.glActiveTexture(33985);
/* 327 */       GL11.glDisable(3553);
/* 328 */       GL11.glBindTexture(3553, 0);
/*     */     }
/* 330 */     if (this.jdField_c_of_type_Boolean) {
/* 331 */       GL13.glActiveTexture(33986);
/* 332 */       GL11.glDisable(3553);
/* 333 */       GL11.glBindTexture(3553, 0);
/*     */     }
/*     */ 
/* 336 */     GL13.glActiveTexture(33984);
/*     */   }
/*     */ 
/*     */   public final void a(float[] paramArrayOfFloat)
/*     */   {
/* 345 */     this.jdField_a_of_type_ArrayOfFloat = paramArrayOfFloat;
/*     */   }
/*     */ 
/*     */   public final void b(float[] paramArrayOfFloat)
/*     */   {
/* 354 */     this.jdField_b_of_type_ArrayOfFloat = paramArrayOfFloat;
/*     */   }
/*     */ 
/*     */   public final void e()
/*     */   {
/* 363 */     this.jdField_b_of_type_Boolean = true;
/*     */   }
/*     */ 
/*     */   public final void a(boolean paramBoolean)
/*     */   {
/* 372 */     if (paramBoolean) {
/* 373 */       this.jdField_a_of_type_Int += 1;
/*     */     }
/* 375 */     this.jdField_a_of_type_Boolean = paramBoolean;
/*     */   }
/*     */ 
/*     */   public final void a(String paramString)
/*     */   {
/* 384 */     this.jdField_a_of_type_JavaLangString = paramString;
/*     */   }
/*     */ 
/*     */   public final void a(zy paramzy)
/*     */   {
/* 393 */     this.jdField_b_of_type_Zy = paramzy;
/*     */ 
/* 395 */     if (paramzy != null) {
/* 396 */       this.jdField_a_of_type_Int += 1;
/* 397 */       this.jdField_b_of_type_Boolean = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void f()
/*     */   {
/* 407 */     this.jdField_d_of_type_ArrayOfFloat[0] = 64.0F;
/*     */   }
/*     */ 
/*     */   public final void c(float[] paramArrayOfFloat)
/*     */   {
/* 417 */     this.jdField_c_of_type_ArrayOfFloat = paramArrayOfFloat;
/*     */   }
/*     */ 
/*     */   public final void b(zy paramzy)
/*     */   {
/* 426 */     this.jdField_c_of_type_Zy = paramzy;
/*     */ 
/* 428 */     if (paramzy != null) {
/* 429 */       this.jdField_a_of_type_Int += 1;
/* 430 */       this.jdField_c_of_type_Boolean = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void g()
/*     */   {
/* 440 */     this.jdField_c_of_type_Boolean = true;
/*     */   }
/*     */ 
/*     */   public final void c(zy paramzy)
/*     */   {
/* 449 */     if (paramzy == null)
/* 450 */       a(false);
/*     */     else {
/* 452 */       a(true);
/*     */     }
/* 454 */     this.jdField_a_of_type_Zy = paramzy;
/*     */   }
/*     */ 
/*     */   public final void b(String paramString)
/*     */   {
/* 466 */     this.jdField_b_of_type_JavaLangString = paramString;
/*     */   }
/*     */ 
/*     */   public final void a(zj paramzj)
/*     */   {
/* 490 */     GlUtil.a(paramzj, "light.ambient", xd.a.a());
/* 491 */     GlUtil.a(paramzj, "light.diffuse", xd.a.c());
/* 492 */     GlUtil.a(paramzj, "light.specular", xd.a.d());
/* 493 */     GlUtil.a(paramzj, "light.position", xd.a.a().x, xd.a.a().y, xd.a.a().z, 1.0F);
/*     */ 
/* 499 */     GlUtil.a(paramzj, "shininess", 2.0F);
/*     */ 
/* 502 */     GL13.glActiveTexture(33984);
/* 503 */     GL11.glEnable(3553);
/* 504 */     GL11.glBindTexture(3553, this.jdField_a_of_type_Zy.c);
/* 505 */     GlUtil.a(paramzj, "diffuseTexture", 0);
/*     */ 
/* 507 */     GL13.glActiveTexture(33985);
/* 508 */     GL11.glEnable(3553);
/* 509 */     GL11.glBindTexture(3553, this.jdField_b_of_type_Zy.c);
/* 510 */     GlUtil.a(paramzj, "specularMap", 1);
/*     */ 
/* 512 */     GL13.glActiveTexture(33986);
/* 513 */     GL11.glEnable(3553);
/* 514 */     GL11.glBindTexture(3553, this.jdField_c_of_type_Zy.c);
/* 515 */     GlUtil.a(paramzj, "normalMap", 2);
/*     */ 
/* 517 */     GL13.glActiveTexture(33987);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     zx
 * JD-Core Version:    0.6.2
 */