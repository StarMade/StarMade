/*   1:    */package org.schema.game.client.view.space;
/*   2:    */
/*   3:    */import Ad;
/*   4:    */import iR;
/*   5:    */import iT;
/*   6:    */import java.util.List;
/*   7:    */import java.util.Random;
/*   8:    */import org.lwjgl.BufferUtils;
/*   9:    */import org.lwjgl.opengl.GL13;
/*  10:    */import org.schema.schine.graphicsengine.camera.Camera;
/*  11:    */import org.schema.schine.graphicsengine.forms.Mesh;
/*  12:    */import xe;
/*  13:    */import xg;
/*  14:    */import xu;
/*  15:    */import zC;
/*  16:    */import zj;
/*  17:    */
/*  18:    */public class StarSky implements xg
/*  19:    */{
/*  20:    */  private java.nio.IntBuffer jdField_a_of_type_JavaNioIntBuffer;
/*  21:    */  private java.nio.IntBuffer jdField_b_of_type_JavaNioIntBuffer;
/*  22:    */  private java.nio.FloatBuffer jdField_a_of_type_JavaNioFloatBuffer;
/*  23:    */  private java.nio.FloatBuffer jdField_b_of_type_JavaNioFloatBuffer;
/*  24:    */  
/*  25:    */  public static void main(String[] paramArrayOfString)
/*  26:    */  {
/*  27: 27 */    paramArrayOfString = new StarSky();
/*  28:    */    try {
/*  29: 29 */      paramArrayOfString.d(); return;
/*  30: 30 */    } catch (org.schema.schine.graphicsengine.core.ResourceException localResourceException) { 
/*  31:    */      
/*  32: 32 */        localResourceException;
/*  33:    */    }
/*  34:    */  }
/*  35:    */  
/* 107:105 */  private int jdField_a_of_type_Int = 2000;
/* 108:    */  
/* 112:110 */  private boolean jdField_a_of_type_Boolean = true;
/* 113:    */  
/* 116:    */  private iR jdField_a_of_type_IR;
/* 117:    */  
/* 120:    */  private iT jdField_a_of_type_IT;
/* 121:    */  
/* 125:    */  public final void a() {}
/* 126:    */  
/* 129:    */  public final void b()
/* 130:    */  {
/* 131:129 */    if (this.jdField_a_of_type_Boolean) {
/* 132:130 */      c();
/* 133:    */    }
/* 134:    */    
/* 135:133 */    StarSky localStarSky = this; if (this.jdField_a_of_type_Boolean) localStarSky.c(); javax.vecmath.Vector3f localVector3f = xe.a().a();org.lwjgl.opengl.GL11.glDisable(2929);org.schema.schine.graphicsengine.core.GlUtil.d();Mesh localMesh = (Mesh)xe.a().a("Box").a().get(0);org.schema.schine.graphicsengine.core.GlUtil.c(localVector3f.x, localVector3f.y, localVector3f.z);org.schema.schine.graphicsengine.core.GlUtil.b(10.0F, 10.0F, 10.0F);org.lwjgl.opengl.GL11.glDisable(2884);GL13.glActiveTexture(33984);org.lwjgl.opengl.GL11.glBindTexture(3553, 0);org.lwjgl.opengl.GL11.glBindTexture(34067, localStarSky.jdField_a_of_type_IT.a().c);zk.m.a = localStarSky.jdField_a_of_type_IT;zk.m.b();localMesh.e();zk.m.d();org.lwjgl.opengl.GL11.glBindTexture(34067, 0);org.lwjgl.opengl.GL11.glEnable(2884);org.schema.schine.graphicsengine.core.GlUtil.c();
/* 136:    */    
/* 137:135 */    localStarSky = this; if (xu.M.b()) { if (localStarSky.jdField_a_of_type_Boolean) localStarSky.c(); org.schema.schine.graphicsengine.core.GlUtil.d();localVector3f = xe.a().a();org.lwjgl.opengl.GL11.glDisable(2929);org.lwjgl.opengl.GL11.glTranslatef(localVector3f.x, localVector3f.y, localVector3f.z);zk.c.a = localStarSky.jdField_a_of_type_IR;zk.c.b();org.lwjgl.opengl.GL11.glDisable(2896);org.lwjgl.opengl.GL11.glEnable(3042);org.lwjgl.opengl.GL11.glBlendFunc(770, 771);org.lwjgl.opengl.GL11.glEnableClientState(32884);org.lwjgl.opengl.GL15.glBindBuffer(34962, localStarSky.jdField_a_of_type_JavaNioIntBuffer.get(0));org.lwjgl.opengl.GL11.glVertexPointer(3, 5126, 0, 0L);org.lwjgl.opengl.GL11.glEnableClientState(32886);org.lwjgl.opengl.GL15.glBindBuffer(34962, localStarSky.jdField_b_of_type_JavaNioIntBuffer.get(0));org.lwjgl.opengl.GL11.glColorPointer(3, 5126, 0, 0L);org.lwjgl.opengl.GL11.glDisable(2884);org.lwjgl.opengl.GL11.glDrawArrays(0, 0, localStarSky.jdField_a_of_type_Int);org.lwjgl.opengl.GL11.glEnable(2884);org.lwjgl.opengl.GL15.glBindBuffer(34962, 0);org.lwjgl.opengl.GL11.glDisableClientState(32884);org.lwjgl.opengl.GL11.glDisableClientState(32886);org.lwjgl.opengl.GL11.glEnable(2929);org.schema.schine.graphicsengine.core.GlUtil.c();org.lwjgl.opengl.GL11.glBindTexture(3552, 0);org.lwjgl.opengl.GL11.glDisable(3552);org.lwjgl.opengl.GL11.glDisable(3553);org.lwjgl.opengl.GL11.glDisable(3042);zk.c.d();
/* 138:    */    }
/* 139:    */  }
/* 140:    */  
/* 297:    */  private void d()
/* 298:    */  {
/* 299:297 */    this.jdField_a_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(this.jdField_a_of_type_Int * 3);
/* 300:298 */    this.jdField_b_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(this.jdField_a_of_type_Int * 3);
/* 301:    */    javax.vecmath.Vector3f[] arrayOfVector3f1;
/* 302:300 */    javax.vecmath.Vector3f[] arrayOfVector3f2 = arrayOfVector3f1 = new javax.vecmath.Vector3f[this.jdField_a_of_type_Int];float f1 = 3.141593F * (3.0F - org.schema.common.FastMath.l(5.0F));float f2 = 2.0F / arrayOfVector3f2.length;float f3 = 0.0F; for (int j = 0; j < arrayOfVector3f2.length; j++) { int k = 750 + org.schema.common.FastMath.a.nextInt(1000);k = Math.random() > 0.5D ? -k : k;arrayOfVector3f2[j] = new javax.vecmath.Vector3f();float f4 = j * f2 - 1.0F + f2 / 2.0F;float f5 = org.schema.common.FastMath.l(1.0F - f4 * f4);float f6 = f3;f3 = (float)(f3 + (0.5D + Math.random()) * f1);arrayOfVector3f2[j].set(org.schema.common.FastMath.d(f6) * f5, f4, org.schema.common.FastMath.h(f6) * f5);arrayOfVector3f2[j].normalize();arrayOfVector3f2[j].scale(k); }
/* 303:301 */    for (int i = 0; i < this.jdField_a_of_type_Int; i++)
/* 304:    */    {
/* 322:320 */      javax.vecmath.Vector3f localVector3f = arrayOfVector3f1[i];
/* 323:    */      
/* 324:322 */      this.jdField_a_of_type_JavaNioFloatBuffer.put(localVector3f.x);
/* 325:323 */      this.jdField_a_of_type_JavaNioFloatBuffer.put(localVector3f.y);
/* 326:324 */      this.jdField_a_of_type_JavaNioFloatBuffer.put(localVector3f.z);
/* 327:    */      
/* 328:326 */      if (Math.random() >= 0.8D) {
/* 329:327 */        this.jdField_b_of_type_JavaNioFloatBuffer.put(0.5F + (float)Math.random() * 0.5F);
/* 330:328 */        this.jdField_b_of_type_JavaNioFloatBuffer.put(0.5F + (float)Math.random() * 0.5F);
/* 331:329 */        this.jdField_b_of_type_JavaNioFloatBuffer.put(0.5F + (float)Math.random() * 0.5F);
/* 332:    */      } else {
/* 333:331 */        this.jdField_b_of_type_JavaNioFloatBuffer.put(0.8F + (float)Math.random() * 0.2F);
/* 334:332 */        this.jdField_b_of_type_JavaNioFloatBuffer.put(0.8F + (float)Math.random() * 0.2F);
/* 335:333 */        this.jdField_b_of_type_JavaNioFloatBuffer.put(0.8F + (float)Math.random() * 0.2F);
/* 336:    */      }
/* 337:    */    }
/* 338:    */  }
/* 339:    */  
/* 348:    */  public final void c()
/* 349:    */  {
/* 350:    */    try
/* 351:    */    {
/* 352:350 */      d();
/* 353:351 */    } catch (org.schema.schine.graphicsengine.core.ResourceException localResourceException) { 
/* 354:    */      
/* 355:353 */        localResourceException;
/* 356:    */    }
/* 357:    */    
/* 359:355 */    this.jdField_a_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
/* 360:356 */    org.lwjgl.opengl.GL15.glGenBuffers(this.jdField_a_of_type_JavaNioIntBuffer);
/* 361:357 */    org.lwjgl.opengl.GL15.glBindBuffer(34962, this.jdField_a_of_type_JavaNioIntBuffer.get(0));
/* 362:358 */    this.jdField_a_of_type_JavaNioFloatBuffer.rewind();
/* 363:359 */    org.lwjgl.opengl.GL15.glBufferData(34962, this.jdField_a_of_type_JavaNioFloatBuffer, 35044);
/* 364:360 */    org.lwjgl.opengl.GL15.glBindBuffer(34962, 0);
/* 365:    */    
/* 367:363 */    this.jdField_b_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
/* 368:364 */    org.lwjgl.opengl.GL15.glGenBuffers(this.jdField_b_of_type_JavaNioIntBuffer);
/* 369:365 */    org.lwjgl.opengl.GL15.glBindBuffer(34962, this.jdField_b_of_type_JavaNioIntBuffer.get(0));
/* 370:366 */    this.jdField_b_of_type_JavaNioFloatBuffer.rewind();
/* 371:367 */    org.lwjgl.opengl.GL15.glBufferData(34962, this.jdField_b_of_type_JavaNioFloatBuffer, 35044);
/* 372:368 */    org.lwjgl.opengl.GL15.glBindBuffer(34962, 0);
/* 373:    */    
/* 374:370 */    this.jdField_a_of_type_IR = new iR();
/* 375:371 */    this.jdField_a_of_type_IT = new iT();
/* 376:372 */    this.jdField_a_of_type_Boolean = false;
/* 377:    */  }
/* 378:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.client.view.space.StarSky
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */