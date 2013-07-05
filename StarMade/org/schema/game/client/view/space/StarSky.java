/*     */ package org.schema.game.client.view.space;
/*     */ 
/*     */ import d;
/*     */ import iR;
/*     */ import iT;
/*     */ import java.nio.FloatBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.lwjgl.BufferUtils;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import org.lwjgl.opengl.GL13;
/*     */ import org.lwjgl.opengl.GL15;
/*     */ import org.schema.common.FastMath;
/*     */ import org.schema.schine.graphicsengine.camera.Camera;
/*     */ import org.schema.schine.graphicsengine.core.GlUtil;
/*     */ import org.schema.schine.graphicsengine.core.ResourceException;
/*     */ import org.schema.schine.graphicsengine.forms.Mesh;
/*     */ import xe;
/*     */ import xg;
/*     */ import xu;
/*     */ import zZ;
/*     */ import zj;
/*     */ import zy;
/*     */ 
/*     */ public class StarSky
/*     */   implements xg
/*     */ {
/*     */   private IntBuffer jdField_a_of_type_JavaNioIntBuffer;
/*     */   private IntBuffer jdField_b_of_type_JavaNioIntBuffer;
/*     */   private FloatBuffer jdField_a_of_type_JavaNioFloatBuffer;
/*     */   private FloatBuffer jdField_b_of_type_JavaNioFloatBuffer;
/* 105 */   private int jdField_a_of_type_Int = 2000;
/*     */ 
/* 110 */   private boolean jdField_a_of_type_Boolean = true;
/*     */   private iR jdField_a_of_type_IR;
/*     */   private iT jdField_a_of_type_IT;
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/*  27 */     paramArrayOfString = new StarSky();
/*     */     try { paramArrayOfString.d();
/*     */       return;
/*     */     } catch (ResourceException localResourceException) {
/*  32 */       localResourceException.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void b()
/*     */   {
/* 129 */     if (this.jdField_a_of_type_Boolean) {
/* 130 */       c();
/*     */     }
/*     */ 
/* 133 */     StarSky localStarSky = this; if (this.jdField_a_of_type_Boolean) localStarSky.c(); Vector3f localVector3f = xe.a().a(); GL11.glDisable(2929); GlUtil.d(); Mesh localMesh = (Mesh)xe.a().a("Box").a().get(0); GlUtil.c(localVector3f.x, localVector3f.y, localVector3f.z); GlUtil.b(10.0F, 10.0F, 10.0F); GL11.glDisable(2884); GL13.glActiveTexture(33984); GL11.glBindTexture(3553, 0); GL11.glBindTexture(34067, localStarSky.jdField_a_of_type_IT.a().c); d.s.a = localStarSky.jdField_a_of_type_IT; d.s.b(); localMesh.f(); d.s.d(); GL11.glBindTexture(34067, 0); GL11.glEnable(2884); GlUtil.c();
/*     */ 
/* 135 */     localStarSky = this; if (xu.M.b()) { if (localStarSky.jdField_a_of_type_Boolean) localStarSky.c(); GlUtil.d(); localVector3f = xe.a().a(); GL11.glDisable(2929); GL11.glTranslatef(localVector3f.x, localVector3f.y, localVector3f.z); d.f.a = localStarSky.jdField_a_of_type_IR; d.f.b(); GL11.glDisable(2896); GL11.glEnable(3042); GL11.glBlendFunc(770, 771); GL11.glEnableClientState(32884); GL15.glBindBuffer(34962, localStarSky.jdField_a_of_type_JavaNioIntBuffer.get(0)); GL11.glVertexPointer(3, 5126, 0, 0L); GL11.glEnableClientState(32886); GL15.glBindBuffer(34962, localStarSky.jdField_b_of_type_JavaNioIntBuffer.get(0)); GL11.glColorPointer(3, 5126, 0, 0L); GL11.glDisable(2884); GL11.glDrawArrays(0, 0, localStarSky.jdField_a_of_type_Int); GL11.glEnable(2884); GL15.glBindBuffer(34962, 0); GL11.glDisableClientState(32884); GL11.glDisableClientState(32886); GL11.glEnable(2929); GlUtil.c(); GL11.glBindTexture(3552, 0); GL11.glDisable(3552); GL11.glDisable(3553); GL11.glDisable(3042); d.f.d();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void d()
/*     */   {
/* 297 */     this.jdField_a_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(this.jdField_a_of_type_Int * 3);
/* 298 */     this.jdField_b_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(this.jdField_a_of_type_Int * 3);
/*     */     Vector3f[] arrayOfVector3f1;
/* 300 */     Vector3f[] arrayOfVector3f2 = arrayOfVector3f1 = new Vector3f[this.jdField_a_of_type_Int];
/* 300 */     float f1 = 3.141593F * (3.0F - FastMath.l(5.0F)); float f2 = 2.0F / arrayOfVector3f2.length; float f3 = 0.0F; for (int j = 0; j < arrayOfVector3f2.length; j++) { int k = 750 + FastMath.a.nextInt(1000); k = Math.random() > 0.5D ? -k : k; arrayOfVector3f2[j] = new Vector3f(); float f4 = j * f2 - 1.0F + f2 / 2.0F; float f5 = FastMath.l(1.0F - f4 * f4); float f6 = f3; f3 = (float)(f3 + (0.5D + Math.random()) * f1); arrayOfVector3f2[j].set(FastMath.d(f6) * f5, f4, FastMath.h(f6) * f5); arrayOfVector3f2[j].normalize(); arrayOfVector3f2[j].scale(k); }
/* 301 */     for (int i = 0; i < this.jdField_a_of_type_Int; i++)
/*     */     {
/* 320 */       Vector3f localVector3f = arrayOfVector3f1[i];
/*     */ 
/* 322 */       this.jdField_a_of_type_JavaNioFloatBuffer.put(localVector3f.x);
/* 323 */       this.jdField_a_of_type_JavaNioFloatBuffer.put(localVector3f.y);
/* 324 */       this.jdField_a_of_type_JavaNioFloatBuffer.put(localVector3f.z);
/*     */ 
/* 326 */       if (Math.random() >= 0.8D) {
/* 327 */         this.jdField_b_of_type_JavaNioFloatBuffer.put(0.5F + (float)Math.random() * 0.5F);
/* 328 */         this.jdField_b_of_type_JavaNioFloatBuffer.put(0.5F + (float)Math.random() * 0.5F);
/* 329 */         this.jdField_b_of_type_JavaNioFloatBuffer.put(0.5F + (float)Math.random() * 0.5F);
/*     */       } else {
/* 331 */         this.jdField_b_of_type_JavaNioFloatBuffer.put(0.8F + (float)Math.random() * 0.2F);
/* 332 */         this.jdField_b_of_type_JavaNioFloatBuffer.put(0.8F + (float)Math.random() * 0.2F);
/* 333 */         this.jdField_b_of_type_JavaNioFloatBuffer.put(0.8F + (float)Math.random() * 0.2F);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/*     */     try
/*     */     {
/* 350 */       d();
/*     */     }
/*     */     catch (ResourceException localResourceException) {
/* 353 */       localResourceException.printStackTrace();
/*     */     }
/*     */ 
/* 355 */     this.jdField_a_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
/* 356 */     GL15.glGenBuffers(this.jdField_a_of_type_JavaNioIntBuffer);
/* 357 */     GL15.glBindBuffer(34962, this.jdField_a_of_type_JavaNioIntBuffer.get(0));
/* 358 */     this.jdField_a_of_type_JavaNioFloatBuffer.rewind();
/* 359 */     GL15.glBufferData(34962, this.jdField_a_of_type_JavaNioFloatBuffer, 35044);
/* 360 */     GL15.glBindBuffer(34962, 0);
/*     */ 
/* 363 */     this.jdField_b_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
/* 364 */     GL15.glGenBuffers(this.jdField_b_of_type_JavaNioIntBuffer);
/* 365 */     GL15.glBindBuffer(34962, this.jdField_b_of_type_JavaNioIntBuffer.get(0));
/* 366 */     this.jdField_b_of_type_JavaNioFloatBuffer.rewind();
/* 367 */     GL15.glBufferData(34962, this.jdField_b_of_type_JavaNioFloatBuffer, 35044);
/* 368 */     GL15.glBindBuffer(34962, 0);
/*     */ 
/* 370 */     this.jdField_a_of_type_IR = new iR();
/* 371 */     this.jdField_a_of_type_IT = new iT();
/* 372 */     this.jdField_a_of_type_Boolean = false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.client.view.space.StarSky
 * JD-Core Version:    0.6.2
 */