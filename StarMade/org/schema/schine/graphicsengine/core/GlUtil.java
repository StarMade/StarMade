/*      */ package org.schema.schine.graphicsengine.core;
/*      */ 
/*      */ import com.bulletphysics.linearmath.AabbUtil2;
/*      */ import com.bulletphysics.linearmath.Transform;
/*      */ import java.awt.image.BufferedImage;
/*      */ import java.io.File;
/*      */ import java.io.IOException;
/*      */ import java.io.PrintStream;
/*      */ import java.lang.reflect.InvocationTargetException;
/*      */ import java.lang.reflect.Method;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.nio.FloatBuffer;
/*      */ import java.nio.IntBuffer;
/*      */ import java.util.ArrayList;
/*      */ import javax.imageio.ImageIO;
/*      */ import javax.vecmath.Color4f;
/*      */ import javax.vecmath.Matrix3f;
/*      */ import javax.vecmath.Vector4f;
/*      */ import org.lwjgl.BufferUtils;
/*      */ import org.lwjgl.opengl.GL11;
/*      */ import org.lwjgl.opengl.GL20;
/*      */ import org.lwjgl.util.vector.Matrix4f;
/*      */ import org.schema.common.FastMath;
/*      */ import org.schema.schine.graphicsengine.camera.Camera;
/*      */ import org.schema.schine.graphicsengine.forms.Mesh;
/*      */ import xO;
/*      */ import xd;
/*      */ import xe;
/*      */ import xm;
/*      */ import xu;
/*      */ import zj;
/*      */ 
/*      */ public class GlUtil
/*      */ {
/*      */   public static FloatBuffer a;
/*      */   public static FloatBuffer b;
/*      */   private static IntBuffer jdField_a_of_type_JavaNioIntBuffer;
/*      */   private static ByteBuffer[] jdField_a_of_type_ArrayOfJavaNioByteBuffer;
/*      */   private static javax.vecmath.Vector3f jdField_a_of_type_JavaxVecmathVector3f;
/*      */   private static javax.vecmath.Vector3f jdField_b_of_type_JavaxVecmathVector3f;
/*      */   private static javax.vecmath.Vector3f jdField_c_of_type_JavaxVecmathVector3f;
/*      */   private static javax.vecmath.Vector3f jdField_d_of_type_JavaxVecmathVector3f;
/*      */   private static Vector4f jdField_a_of_type_JavaxVecmathVector4f;
/*      */   private static Vector4f jdField_b_of_type_JavaxVecmathVector4f;
/*      */   private static Vector4f jdField_c_of_type_JavaxVecmathVector4f;
/*      */   private static Vector4f jdField_d_of_type_JavaxVecmathVector4f;
/*      */   private static javax.vecmath.Vector3f jdField_e_of_type_JavaxVecmathVector3f;
/*      */   private static javax.vecmath.Vector3f f;
/*      */   private static javax.vecmath.Vector3f g;
/*      */   private static javax.vecmath.Vector3f h;
/*      */   private static javax.vecmath.Vector3f i;
/*      */   private static javax.vecmath.Vector3f j;
/*      */   private static javax.vecmath.Vector3f k;
/*      */   private static javax.vecmath.Vector3f l;
/*      */   private static org.lwjgl.util.vector.Vector3f jdField_a_of_type_OrgLwjglUtilVectorVector3f;
/*      */   private static FloatBuffer[] jdField_a_of_type_ArrayOfJavaNioFloatBuffer;
/*      */   private static int jdField_a_of_type_Int;
/*      */   private static FloatBuffer[] jdField_b_of_type_ArrayOfJavaNioFloatBuffer;
/*      */   private static int jdField_b_of_type_Int;
/*  160 */   private static float[] jdField_a_of_type_ArrayOfFloat = new float[16];
/*  161 */   private static FloatBuffer jdField_c_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(16);
/*  162 */   private static Matrix4f jdField_a_of_type_OrgLwjglUtilVectorMatrix4f = new Matrix4f();
/*      */   private static int jdField_c_of_type_Int;
/*  166 */   private static Vector4f jdField_e_of_type_JavaxVecmathVector4f = new Vector4f();
/*      */ 
/*      */   public static Transform a(javax.vecmath.Vector3f paramVector3f1, javax.vecmath.Vector3f paramVector3f2, javax.vecmath.Vector3f paramVector3f3, Transform paramTransform)
/*      */   {
/*  171 */     j.sub(paramVector3f3, paramVector3f1);
/*  172 */     j.normalize();
/*      */ 
/*  175 */     k.set(paramVector3f2);
/*  176 */     l.cross(k, j);
/*      */ 
/*  180 */     l.normalize();
/*      */ 
/*  185 */     j.cross(l, k);
/*      */ 
/*  187 */     d(k, paramTransform);
/*  188 */     a(j, paramTransform);
/*  189 */     c(l, paramTransform);
/*  190 */     paramTransform.origin.set(paramVector3f1);
/*      */ 
/*  192 */     return paramTransform;
/*      */   }
/*      */ 
/*      */   public static boolean a(javax.vecmath.Vector3f paramVector3f1, javax.vecmath.Vector3f paramVector3f2)
/*      */   {
/*  246 */     return (paramVector3f1.x <= paramVector3f2.x) && (paramVector3f1.y <= paramVector3f2.y) && (paramVector3f1.z <= paramVector3f2.z);
/*      */   }
/*      */ 
/*      */   public static void a() {
/*  250 */     for (int m = 0; m < jdField_a_of_type_ArrayOfJavaNioFloatBuffer.length; m++) {
/*  251 */       jdField_a_of_type_ArrayOfJavaNioFloatBuffer[m] = BufferUtils.createFloatBuffer(16);
/*      */     }
/*  253 */     for (m = 0; m < jdField_b_of_type_ArrayOfJavaNioFloatBuffer.length; m++)
/*  254 */       jdField_b_of_type_ArrayOfJavaNioFloatBuffer[m] = BufferUtils.createFloatBuffer(16);
/*      */   }
/*      */ 
/*      */   public static void a(ByteBuffer paramByteBuffer)
/*      */   {
/*      */     Method localMethod;
/*  273 */     (
/*  274 */       localMethod = paramByteBuffer.getClass().getMethod("cleaner", new Class[0]))
/*  274 */       .setAccessible(true);
/*      */ 
/*  276 */     (
/*  277 */       localMethod = (
/*  276 */       paramByteBuffer = localMethod.invoke(paramByteBuffer, new Object[0]))
/*  276 */       .getClass().getMethod("clean", new Class[0]))
/*  277 */       .setAccessible(true);
/*  278 */     localMethod.invoke(paramByteBuffer, new Object[0]);
/*      */   }
/*      */ 
/*      */   public static javax.vecmath.Vector3f a(javax.vecmath.Vector3f paramVector3f, Transform paramTransform)
/*      */   {
/*  318 */     paramTransform.basis.getColumn(2, paramVector3f);
/*  319 */     paramVector3f.negate();
/*  320 */     return paramVector3f;
/*      */   }
/*      */ 
/*      */   public static javax.vecmath.Vector3f b(javax.vecmath.Vector3f paramVector3f, Transform paramTransform)
/*      */   {
/*  329 */     f(paramVector3f, paramTransform);
/*  330 */     paramVector3f.negate();
/*  331 */     return paramVector3f;
/*      */   }
/*      */ 
/*      */   public static ByteBuffer a(int paramInt1, int paramInt2) {
/*  335 */     if (((
/*  335 */       paramInt2 = jdField_a_of_type_ArrayOfJavaNioByteBuffer[paramInt2]) == null) || 
/*  335 */       (paramInt2.capacity() < paramInt1)) {
/*  336 */       if (paramInt2 != null)
/*      */       {
/*      */         try {
/*  339 */           a(paramInt2);
/*      */         }
/*      */         catch (IllegalArgumentException localIllegalArgumentException)
/*      */         {
/*  350 */           localIllegalArgumentException.printStackTrace();
/*      */         }
/*      */         catch (SecurityException localSecurityException)
/*      */         {
/*  350 */           localSecurityException.printStackTrace();
/*      */         }
/*      */         catch (IllegalAccessException localIllegalAccessException)
/*      */         {
/*  350 */           localIllegalAccessException.printStackTrace();
/*      */         }
/*      */         catch (InvocationTargetException localInvocationTargetException)
/*      */         {
/*  350 */           localInvocationTargetException.printStackTrace();
/*      */         }
/*      */         catch (NoSuchMethodException localNoSuchMethodException)
/*      */         {
/*  350 */           localNoSuchMethodException.printStackTrace();
/*      */         }
/*      */ 
/*  351 */         System.gc();
/*      */       }
/*      */ 
/*  355 */       paramInt2 = BufferUtils.createByteBuffer(paramInt1);
/*      */     }
/*  357 */     paramInt2.limit(paramInt1);
/*  358 */     paramInt2.rewind();
/*  359 */     return paramInt2;
/*      */   }
/*      */ 
/*      */   public static javax.vecmath.Vector3f a(javax.vecmath.Vector3f paramVector3f) {
/*  363 */     jdField_b_of_type_JavaNioFloatBuffer.rewind();
/*  364 */     xe.jdField_a_of_type_OrgLwjglUtilVectorMatrix4f.store(jdField_b_of_type_JavaNioFloatBuffer);
/*  365 */     paramVector3f.x = jdField_b_of_type_JavaNioFloatBuffer.get(2);
/*  366 */     paramVector3f.y = jdField_b_of_type_JavaNioFloatBuffer.get(6);
/*  367 */     paramVector3f.z = jdField_b_of_type_JavaNioFloatBuffer.get(10);
/*  368 */     return paramVector3f;
/*      */   }
/*      */ 
/*      */   public static javax.vecmath.Vector3f c(javax.vecmath.Vector3f paramVector3f, Transform paramTransform)
/*      */   {
/*  377 */     paramTransform.basis.getColumn(2, paramVector3f);
/*  378 */     return paramVector3f;
/*      */   }
/*      */ 
/*      */   public static String a()
/*      */   {
/*  387 */     int m = GL11.glGetError();
/*  388 */     String str = "unknown " + m;
/*  389 */     switch (m) {
/*      */     case 0:
/*  391 */       return null;
/*      */     case 1285:
/*  393 */       str = "GL_OUT_OF_MEMORY";
/*  394 */       break;
/*      */     case 1280:
/*  396 */       str = "GL_INVALID_ENUM";
/*  397 */       break;
/*      */     case 1281:
/*  399 */       str = "GL_INVALID_VALUE";
/*  400 */       break;
/*      */     case 1282:
/*  402 */       str = "GL_INVALID_OPERATION";
/*  403 */       break;
/*      */     case 1283:
/*  405 */       str = "GL_STACK_OVERFLOW: modelstack-sizes: proj: " + GL11.glGetInteger(2980) + ", model: " + GL11.glGetInteger(2979);
/*  406 */       break;
/*      */     case 1284:
/*  408 */       str = "GL_STACK_UNDERFLOW";
/*  409 */       break;
/*      */     case 1286:
/*  411 */       str = "GL_INVALID_FRAMEBUFFER_OPERATION";
/*      */     }
/*  413 */     return str;
/*      */   }
/*      */   public static IntBuffer a() {
/*  416 */     jdField_a_of_type_JavaNioIntBuffer.rewind();
/*  417 */     return jdField_a_of_type_JavaNioIntBuffer;
/*      */   }
/*      */ 
/*      */   public static javax.vecmath.Vector3f d(javax.vecmath.Vector3f paramVector3f, Transform paramTransform)
/*      */   {
/*  426 */     e(paramVector3f, paramTransform);
/*  427 */     paramVector3f.negate();
/*  428 */     return paramVector3f;
/*      */   }
/*      */ 
/*      */   public static void a(Transform paramTransform, float paramFloat, javax.vecmath.Vector3f[] paramArrayOfVector3f) {
/*  432 */     paramArrayOfVector3f[0].set(-paramFloat, -paramFloat, 0.0F);
/*      */ 
/*  435 */     paramArrayOfVector3f[1].set(paramFloat, -paramFloat, 0.0F);
/*  436 */     paramArrayOfVector3f[2].set(paramFloat, paramFloat, 0.0F);
/*  437 */     paramArrayOfVector3f[3].set(-paramFloat, paramFloat, 0.0F);
/*      */ 
/*  439 */     paramTransform.transform(paramArrayOfVector3f[0]);
/*  440 */     paramTransform.transform(paramArrayOfVector3f[1]);
/*  441 */     paramTransform.transform(paramArrayOfVector3f[2]);
/*  442 */     paramTransform.transform(paramArrayOfVector3f[3]);
/*      */   }
/*      */   public static javax.vecmath.Vector3f b(javax.vecmath.Vector3f paramVector3f) {
/*  445 */     jdField_b_of_type_JavaNioFloatBuffer.rewind();
/*  446 */     xe.jdField_a_of_type_OrgLwjglUtilVectorMatrix4f.store(jdField_b_of_type_JavaNioFloatBuffer);
/*  447 */     paramVector3f.x = jdField_b_of_type_JavaNioFloatBuffer.get(0);
/*  448 */     paramVector3f.y = jdField_b_of_type_JavaNioFloatBuffer.get(4);
/*  449 */     paramVector3f.z = jdField_b_of_type_JavaNioFloatBuffer.get(8);
/*  450 */     return paramVector3f;
/*      */   }
/*      */ 
/*      */   public static javax.vecmath.Vector3f e(javax.vecmath.Vector3f paramVector3f, Transform paramTransform)
/*      */   {
/*  459 */     paramTransform.basis.getColumn(0, paramVector3f);
/*  460 */     return paramVector3f;
/*      */   }
/*      */ 
/*      */   public static javax.vecmath.Vector3f c(javax.vecmath.Vector3f paramVector3f) {
/*  464 */     jdField_b_of_type_JavaNioFloatBuffer.rewind();
/*  465 */     xe.jdField_a_of_type_OrgLwjglUtilVectorMatrix4f.store(jdField_b_of_type_JavaNioFloatBuffer);
/*  466 */     paramVector3f.x = jdField_b_of_type_JavaNioFloatBuffer.get(1);
/*  467 */     paramVector3f.y = jdField_b_of_type_JavaNioFloatBuffer.get(5);
/*  468 */     paramVector3f.z = jdField_b_of_type_JavaNioFloatBuffer.get(9);
/*  469 */     return paramVector3f;
/*      */   }
/*      */ 
/*      */   public static javax.vecmath.Vector3f f(javax.vecmath.Vector3f paramVector3f, Transform paramTransform)
/*      */   {
/*  479 */     paramTransform.basis.getColumn(1, paramVector3f);
/*  480 */     return paramVector3f;
/*      */   }
/*      */   public static void b() {
/*  483 */     if (jdField_c_of_type_Int == 5889)
/*  484 */       xe.b.setIdentity();
/*      */     else {
/*  486 */       xe.jdField_a_of_type_OrgLwjglUtilVectorMatrix4f.setIdentity();
/*      */     }
/*  488 */     GL11.glLoadIdentity();
/*      */   }
/*      */ 
/*      */   public static void a(FloatBuffer paramFloatBuffer)
/*      */   {
/*  496 */     paramFloatBuffer.rewind();
/*  497 */     if (jdField_c_of_type_Int == 5889)
/*  498 */       xe.b.load(paramFloatBuffer);
/*      */     else {
/*  500 */       xe.jdField_a_of_type_OrgLwjglUtilVectorMatrix4f.load(paramFloatBuffer);
/*      */     }
/*  502 */     paramFloatBuffer.rewind();
/*  503 */     GL11.glLoadMatrix(paramFloatBuffer);
/*      */   }
/*      */   public static void a(Transform paramTransform) {
/*  506 */     paramTransform.getOpenGLMatrix(jdField_a_of_type_ArrayOfFloat);
/*  507 */     jdField_c_of_type_JavaNioFloatBuffer.rewind();
/*  508 */     jdField_c_of_type_JavaNioFloatBuffer.put(jdField_a_of_type_ArrayOfFloat);
/*  509 */     jdField_c_of_type_JavaNioFloatBuffer.rewind();
/*  510 */     a(jdField_c_of_type_JavaNioFloatBuffer);
/*      */   }
/*      */ 
/*      */   public static void a(int paramInt) {
/*  514 */     GL11.glMatrixMode(GlUtil.jdField_c_of_type_Int = paramInt);
/*      */   }
/*      */ 
/*      */   public static void b(Transform paramTransform)
/*      */   {
/*  566 */     jdField_c_of_type_JavaNioFloatBuffer.rewind();
/*  567 */     FloatBuffer localFloatBuffer = jdField_c_of_type_JavaNioFloatBuffer; paramTransform = paramTransform; localFloatBuffer.put(paramTransform.basis.m00); localFloatBuffer.put(paramTransform.basis.m10); localFloatBuffer.put(paramTransform.basis.m20); localFloatBuffer.put(0.0F); localFloatBuffer.put(paramTransform.basis.m01); localFloatBuffer.put(paramTransform.basis.m11); localFloatBuffer.put(paramTransform.basis.m21); localFloatBuffer.put(0.0F); localFloatBuffer.put(paramTransform.basis.m02); localFloatBuffer.put(paramTransform.basis.m12); localFloatBuffer.put(paramTransform.basis.m22); localFloatBuffer.put(0.0F); localFloatBuffer.put(paramTransform.origin.x); localFloatBuffer.put(paramTransform.origin.y); localFloatBuffer.put(paramTransform.origin.z); localFloatBuffer.put(1.0F);
/*  568 */     jdField_c_of_type_JavaNioFloatBuffer.rewind();
/*  569 */     (paramTransform = jdField_c_of_type_JavaNioFloatBuffer).rewind(); jdField_a_of_type_OrgLwjglUtilVectorMatrix4f.load(paramTransform); Matrix4f.mul(xe.jdField_a_of_type_OrgLwjglUtilVectorMatrix4f, jdField_a_of_type_OrgLwjglUtilVectorMatrix4f, xe.jdField_a_of_type_OrgLwjglUtilVectorMatrix4f); paramTransform.rewind(); GL11.glMultMatrix(paramTransform);
/*      */   }
/*      */ 
/*      */   public static final void a(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5)
/*      */   {
/*  600 */     float f2 = 2.0F / paramFloat1;
/*  601 */     float f3 = 2.0F / (paramFloat3 - paramFloat2);
/*  602 */     float f4 = -2.0F / (paramFloat5 - paramFloat4);
/*  603 */     float f1 = -(paramFloat1 + 0.0F) / paramFloat1;
/*  604 */     paramFloat1 = -(paramFloat3 + paramFloat2) / (paramFloat3 - paramFloat2);
/*  605 */     paramFloat2 = -(paramFloat5 + paramFloat4) / (paramFloat5 - paramFloat4);
/*      */ 
/*  607 */     jdField_a_of_type_ArrayOfFloat[0] = f2;
/*  608 */     jdField_a_of_type_ArrayOfFloat[1] = 0.0F;
/*  609 */     jdField_a_of_type_ArrayOfFloat[2] = 0.0F;
/*  610 */     jdField_a_of_type_ArrayOfFloat[3] = 0.0F;
/*      */ 
/*  612 */     jdField_a_of_type_ArrayOfFloat[4] = 0.0F;
/*  613 */     jdField_a_of_type_ArrayOfFloat[5] = f3;
/*  614 */     jdField_a_of_type_ArrayOfFloat[6] = 0.0F;
/*  615 */     jdField_a_of_type_ArrayOfFloat[7] = 0.0F;
/*      */ 
/*  617 */     jdField_a_of_type_ArrayOfFloat[8] = 0.0F;
/*  618 */     jdField_a_of_type_ArrayOfFloat[9] = 0.0F;
/*  619 */     jdField_a_of_type_ArrayOfFloat[10] = f4;
/*  620 */     jdField_a_of_type_ArrayOfFloat[11] = 0.0F;
/*      */ 
/*  622 */     jdField_a_of_type_ArrayOfFloat[12] = f1;
/*  623 */     jdField_a_of_type_ArrayOfFloat[13] = paramFloat1;
/*  624 */     jdField_a_of_type_ArrayOfFloat[14] = paramFloat2;
/*  625 */     jdField_a_of_type_ArrayOfFloat[15] = 1.0F;
/*      */ 
/*  627 */     jdField_c_of_type_JavaNioFloatBuffer.rewind();
/*  628 */     jdField_c_of_type_JavaNioFloatBuffer.put(jdField_a_of_type_ArrayOfFloat);
/*  629 */     jdField_c_of_type_JavaNioFloatBuffer.rewind();
/*      */ 
/*  631 */     xe.b.load(jdField_c_of_type_JavaNioFloatBuffer);
/*  632 */     jdField_c_of_type_JavaNioFloatBuffer.rewind();
/*  633 */     GL11.glLoadMatrix(jdField_c_of_type_JavaNioFloatBuffer);
/*      */   }
/*      */ 
/*      */   public static void c() {
/*  637 */     if (jdField_c_of_type_Int == 5889) {
/*  638 */       if ((!jdField_a_of_type_Boolean) && (jdField_b_of_type_Int <= 0)) throw new AssertionError(); jdField_b_of_type_Int -= 1; jdField_b_of_type_ArrayOfJavaNioFloatBuffer[jdField_b_of_type_Int].rewind(); xe.b.load(jdField_b_of_type_ArrayOfJavaNioFloatBuffer[jdField_b_of_type_Int]); GL11.glPopMatrix(); return;
/*      */     }
/*  640 */     if ((!jdField_a_of_type_Boolean) && (jdField_a_of_type_Int <= 0)) throw new AssertionError(); jdField_a_of_type_Int -= 1; jdField_a_of_type_ArrayOfJavaNioFloatBuffer[jdField_a_of_type_Int].rewind(); xe.jdField_a_of_type_OrgLwjglUtilVectorMatrix4f.load(jdField_a_of_type_ArrayOfJavaNioFloatBuffer[jdField_a_of_type_Int]); GL11.glPopMatrix();
/*      */   }
/*      */ 
/*      */   public static void d()
/*      */   {
/*  658 */     if (jdField_c_of_type_Int == 5889) {
/*  659 */       jdField_b_of_type_ArrayOfJavaNioFloatBuffer[jdField_b_of_type_Int].rewind(); xe.b.store(jdField_b_of_type_ArrayOfJavaNioFloatBuffer[jdField_b_of_type_Int]); jdField_b_of_type_Int += 1; if ((!jdField_a_of_type_Boolean) && (jdField_b_of_type_Int > jdField_b_of_type_ArrayOfJavaNioFloatBuffer.length)) throw new AssertionError(); GL11.glPushMatrix(); return;
/*      */     }
/*  661 */     jdField_a_of_type_ArrayOfJavaNioFloatBuffer[jdField_a_of_type_Int].rewind(); xe.jdField_a_of_type_OrgLwjglUtilVectorMatrix4f.store(jdField_a_of_type_ArrayOfJavaNioFloatBuffer[jdField_a_of_type_Int]); jdField_a_of_type_Int += 1; if ((!jdField_a_of_type_Boolean) && (jdField_a_of_type_Int > jdField_a_of_type_ArrayOfJavaNioFloatBuffer.length)) throw new AssertionError(); GL11.glPushMatrix();
/*      */   }
/*      */ 
/*      */   public static void a(float paramFloat1, float paramFloat2, float paramFloat3)
/*      */   {
/*  683 */     a(paramFloat1, paramFloat2, paramFloat3, -1.0F, 1.0F);
/*      */   }
/*      */ 
/*      */   public static Matrix4f a(Matrix4f paramMatrix4f, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, boolean paramBoolean)
/*      */   {
/*  761 */     paramMatrix4f.setZero();
/*      */     float f1;
/*  767 */     float f2 = (
/*  767 */       paramFloat1 = -(
/*  765 */       f1 = paramFloat3 * FastMath.n(paramFloat1 / 360.0F * 3.141593F))) / 
/*  767 */       paramFloat2;
/*  768 */     paramFloat2 = f1 / paramFloat2;
/*      */ 
/*  771 */     paramMatrix4f.m00 = (paramFloat3 * 2.0F / (f1 - paramFloat1));
/*  772 */     paramMatrix4f.m11 = (paramFloat3 * 2.0F / (paramFloat2 - f2));
/*  773 */     paramMatrix4f.m22 = (-(paramFloat4 + paramFloat3) / (paramFloat4 - paramFloat3));
/*      */ 
/*  775 */     paramMatrix4f.m20 = ((f1 + paramFloat1) / (f1 - paramFloat1));
/*  776 */     paramMatrix4f.m21 = ((paramFloat2 + f2) / (paramFloat2 - f2));
/*  777 */     paramMatrix4f.m23 = -1.0F;
/*      */ 
/*  779 */     paramMatrix4f.m32 = (-(paramFloat4 * 2.0F * paramFloat3) / (paramFloat4 - paramFloat3));
/*      */ 
/*  782 */     if (paramBoolean) {
/*  783 */       jdField_a_of_type_JavaNioFloatBuffer.rewind();
/*  784 */       paramMatrix4f.store(jdField_a_of_type_JavaNioFloatBuffer);
/*  785 */       jdField_a_of_type_JavaNioFloatBuffer.rewind();
/*      */ 
/*  787 */       a(jdField_a_of_type_JavaNioFloatBuffer);
/*      */     }
/*      */ 
/*  790 */     return paramMatrix4f;
/*      */   }
/*      */ 
/*      */   public static int a(float paramFloat1, float paramFloat2, float paramFloat3, FloatBuffer paramFloatBuffer1, FloatBuffer paramFloatBuffer2, IntBuffer paramIntBuffer, FloatBuffer paramFloatBuffer3)
/*      */   {
/*      */     float[] arrayOfFloat;
/*  847 */     (
/*  850 */       arrayOfFloat = new float[8])[
/*  850 */       0] = (paramFloatBuffer1.get(0) * paramFloat1 + paramFloatBuffer1.get(4) * paramFloat2 + paramFloatBuffer1.get(8) * paramFloat3 + paramFloatBuffer1.get(12));
/*      */ 
/*  852 */     arrayOfFloat[1] = (paramFloatBuffer1.get(1) * paramFloat1 + paramFloatBuffer1.get(5) * paramFloat2 + paramFloatBuffer1.get(9) * paramFloat3 + paramFloatBuffer1.get(13));
/*      */ 
/*  854 */     arrayOfFloat[2] = (paramFloatBuffer1.get(2) * paramFloat1 + paramFloatBuffer1.get(6) * paramFloat2 + paramFloatBuffer1.get(10) * paramFloat3 + paramFloatBuffer1.get(14));
/*      */ 
/*  856 */     arrayOfFloat[3] = (paramFloatBuffer1.get(3) * paramFloat1 + paramFloatBuffer1.get(7) * paramFloat2 + paramFloatBuffer1.get(11) * paramFloat3 + paramFloatBuffer1.get(15));
/*      */ 
/*  862 */     arrayOfFloat[4] = (paramFloatBuffer2.get(0) * arrayOfFloat[0] + paramFloatBuffer2.get(4) * arrayOfFloat[1] + paramFloatBuffer2.get(8) * arrayOfFloat[2] + paramFloatBuffer2.get(12) * arrayOfFloat[3]);
/*      */ 
/*  864 */     arrayOfFloat[5] = (paramFloatBuffer2.get(1) * arrayOfFloat[0] + paramFloatBuffer2.get(5) * arrayOfFloat[1] + paramFloatBuffer2.get(9) * arrayOfFloat[2] + paramFloatBuffer2.get(13) * arrayOfFloat[3]);
/*      */ 
/*  866 */     arrayOfFloat[6] = (paramFloatBuffer2.get(2) * arrayOfFloat[0] + paramFloatBuffer2.get(6) * arrayOfFloat[1] + paramFloatBuffer2.get(10) * arrayOfFloat[2] + paramFloatBuffer2.get(14) * arrayOfFloat[3]);
/*      */ 
/*  868 */     arrayOfFloat[7] = (-arrayOfFloat[2]);
/*      */ 
/*  871 */     if (arrayOfFloat[7] == 0.0D) {
/*  872 */       return 0;
/*      */     }
/*  874 */     arrayOfFloat[7] = (1.0F / arrayOfFloat[7]);
/*      */ 
/*  877 */     arrayOfFloat[4] *= arrayOfFloat[7];
/*  878 */     arrayOfFloat[5] *= arrayOfFloat[7];
/*  879 */     arrayOfFloat[6] *= arrayOfFloat[7];
/*      */ 
/*  883 */     paramFloatBuffer3.put(0, (arrayOfFloat[4] * 0.5F + 0.5F) * paramIntBuffer.get(2) + paramIntBuffer.get(0));
/*      */ 
/*  885 */     paramFloatBuffer3.put(1, (arrayOfFloat[5] * 0.5F + 0.5F) * paramIntBuffer.get(3) + paramIntBuffer.get(1));
/*      */ 
/*  889 */     paramFloatBuffer3.put(2, (1.0F + arrayOfFloat[6]) * 0.5F);
/*  890 */     return 1;
/*      */   }
/*      */ 
/*      */   private static void a(zj paramzj, String paramString) {
/*  894 */     if (xu.n.b()) {
/*  895 */       paramzj = "[ERROR][SHADER] " + paramzj.jdField_a_of_type_JavaLangString + " - " + paramString + " HANDLE -1 ";
/*  896 */       if (!xd.a.contains(paramzj))
/*  897 */         xd.a.add(paramzj);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static boolean a(Transform paramTransform, Mesh paramMesh)
/*      */   {
/*  907 */     h.set(paramMesh.a().jdField_a_of_type_JavaxVecmathVector3f);
/*  908 */     i.set(paramMesh.a().jdField_b_of_type_JavaxVecmathVector3f);
/*  909 */     AabbUtil2.transformAabb(h, i, 0.0F, paramTransform, jdField_e_of_type_JavaxVecmathVector3f, f);
/*  910 */     return xe.a().a(jdField_e_of_type_JavaxVecmathVector3f, f);
/*      */   }
/*      */ 
/*      */   public static boolean a(javax.vecmath.Vector3f paramVector3f1, javax.vecmath.Vector3f paramVector3f2, float paramFloat) {
/*  914 */     if ((a(paramVector3f1, paramFloat)) && (a(paramVector3f2, paramFloat))) { i.x = Math.min(paramVector3f1.x, paramVector3f2.x); i.y = Math.min(paramVector3f1.y, paramVector3f2.y); i.z = Math.min(paramVector3f1.z, paramVector3f2.z); h.x = Math.max(paramVector3f1.x, paramVector3f2.x); h.y = Math.max(paramVector3f1.y, paramVector3f2.y); h.z = Math.max(paramVector3f1.z, paramVector3f2.z); if (xe.a().a(h, i)) return true;  } return false;
/*      */   }
/*      */ 
/*      */   public static boolean a(javax.vecmath.Vector3f paramVector3f, float paramFloat)
/*      */   {
/*  929 */     g.sub(paramVector3f, xe.a().a());
/*  930 */     return g.length() <= paramFloat;
/*      */   }
/*      */   public static boolean b(javax.vecmath.Vector3f paramVector3f, float paramFloat) {
/*  933 */     return (a(paramVector3f, paramFloat)) && (xe.a().a(paramVector3f));
/*      */   }
/*      */ 
/*      */   public static void main(String[] paramArrayOfString)
/*      */   {
/*      */   }
/*      */ 
/*      */   public static void e()
/*      */   {
/*      */     String str;
/* 1056 */     if ((
/* 1056 */       str = a()) != null)
/*      */       try
/*      */       {
/* 1058 */         throw new GLException(str);
/*      */       } catch (GLException localGLException) {
/* 1060 */         System.err.println("GL_ERROR: " + str + " ");
/* 1061 */         localGLException.printStackTrace();
/*      */       }
/*      */   }
/*      */ 
/*      */   public static void f()
/*      */   {
/*      */     String str;
/* 1073 */     if ((
/* 1073 */       str = a()) != null)
/*      */       try
/*      */       {
/* 1075 */         throw new GLException(str);
/*      */       } catch (GLException localGLException) {
/* 1077 */         System.err.println("GL_ERROR: " + str + " ");
/* 1078 */         localGLException.printStackTrace();
/* 1079 */         xm.a(localGLException);
/*      */       }
/*      */   }
/*      */ 
/*      */   public static void a(javax.vecmath.Vector3f paramVector3f1, javax.vecmath.Vector3f paramVector3f2, javax.vecmath.Vector3f paramVector3f3)
/*      */   {
/* 1102 */     javax.vecmath.Vector3f localVector3f = new javax.vecmath.Vector3f(paramVector3f1);
/* 1103 */     new javax.vecmath.Vector3f(paramVector3f1)
/* 1104 */       .normalize();
/* 1105 */     localVector3f.normalize();
/*      */ 
/* 1110 */     localVector3f.scale(paramVector3f2.dot(localVector3f));
/*      */ 
/* 1112 */     paramVector3f3.sub(paramVector3f2, localVector3f);
/*      */   }
/*      */ 
/*      */   public static javax.vecmath.Vector3f a(javax.vecmath.Vector3f paramVector3f1, javax.vecmath.Vector3f paramVector3f2, javax.vecmath.Vector3f paramVector3f3)
/*      */   {
/* 1128 */     double d1 = paramVector3f2.dot(paramVector3f3);
/*      */ 
/* 1130 */     paramVector3f1.set(paramVector3f2);
/* 1131 */     paramVector3f1.x = ((float)(paramVector3f1.x - d1 * paramVector3f3.x));
/* 1132 */     paramVector3f1.y = ((float)(paramVector3f1.y - d1 * paramVector3f3.y));
/* 1133 */     paramVector3f1.z = ((float)(paramVector3f1.z - d1 * paramVector3f3.z));
/* 1134 */     return paramVector3f1;
/*      */   }
/*      */ 
/*      */   public static void a(FloatBuffer paramFloatBuffer, javax.vecmath.Vector3f paramVector3f1, javax.vecmath.Vector3f paramVector3f2, Vector4f paramVector4f)
/*      */   {
/* 1344 */     jdField_a_of_type_JavaxVecmathVector3f.set(paramVector4f.x, paramVector4f.y, paramVector4f.z);
/* 1345 */     jdField_a_of_type_JavaxVecmathVector3f.sub(paramVector3f1);
/*      */ 
/* 1347 */     jdField_b_of_type_JavaxVecmathVector3f.set(paramVector4f.x, paramVector4f.y, paramVector4f.z);
/* 1348 */     jdField_b_of_type_JavaxVecmathVector3f.add(paramVector3f2);
/*      */ 
/* 1350 */     jdField_c_of_type_JavaxVecmathVector3f.set(paramVector4f.x, paramVector4f.y, paramVector4f.z);
/* 1351 */     jdField_c_of_type_JavaxVecmathVector3f.add(paramVector3f1);
/*      */ 
/* 1353 */     jdField_d_of_type_JavaxVecmathVector3f.set(paramVector4f.x, paramVector4f.y, paramVector4f.z);
/* 1354 */     jdField_d_of_type_JavaxVecmathVector3f.sub(paramVector3f2);
/*      */ 
/* 1356 */     jdField_a_of_type_JavaxVecmathVector4f.set(jdField_a_of_type_JavaxVecmathVector3f.x, jdField_a_of_type_JavaxVecmathVector3f.y, jdField_a_of_type_JavaxVecmathVector3f.z, paramVector4f.w);
/* 1357 */     jdField_b_of_type_JavaxVecmathVector4f.set(jdField_b_of_type_JavaxVecmathVector3f.x, jdField_b_of_type_JavaxVecmathVector3f.y, jdField_b_of_type_JavaxVecmathVector3f.z, paramVector4f.w);
/* 1358 */     jdField_c_of_type_JavaxVecmathVector4f.set(jdField_c_of_type_JavaxVecmathVector3f.x, jdField_c_of_type_JavaxVecmathVector3f.y, jdField_c_of_type_JavaxVecmathVector3f.z, paramVector4f.w);
/* 1359 */     jdField_d_of_type_JavaxVecmathVector4f.set(jdField_d_of_type_JavaxVecmathVector3f.x, jdField_d_of_type_JavaxVecmathVector3f.y, jdField_d_of_type_JavaxVecmathVector3f.z, paramVector4f.w);
/*      */ 
/* 1361 */     a(paramFloatBuffer, jdField_a_of_type_JavaxVecmathVector4f);
/* 1362 */     a(paramFloatBuffer, jdField_b_of_type_JavaxVecmathVector4f);
/* 1363 */     a(paramFloatBuffer, jdField_c_of_type_JavaxVecmathVector4f);
/* 1364 */     a(paramFloatBuffer, jdField_d_of_type_JavaxVecmathVector4f);
/*      */   }
/*      */ 
/*      */   public static void a(FloatBuffer paramFloatBuffer, javax.vecmath.Vector3f paramVector3f) {
/* 1368 */     paramFloatBuffer.put(paramVector3f.x);
/* 1369 */     paramFloatBuffer.put(paramVector3f.y);
/* 1370 */     paramFloatBuffer.put(paramVector3f.z);
/*      */   }
/*      */ 
/*      */   public static void a(FloatBuffer paramFloatBuffer, Vector4f paramVector4f) {
/* 1374 */     paramFloatBuffer.put(paramVector4f.x);
/* 1375 */     paramFloatBuffer.put(paramVector4f.y);
/* 1376 */     paramFloatBuffer.put(paramVector4f.z);
/* 1377 */     paramFloatBuffer.put(paramVector4f.w);
/*      */   }
/*      */   private static void a(FloatBuffer paramFloatBuffer, Vector4f paramVector4f, int paramInt) {
/* 1380 */     paramFloatBuffer.put(paramInt, paramVector4f.x);
/* 1381 */     paramFloatBuffer.put(paramInt + 1, paramVector4f.y);
/* 1382 */     paramFloatBuffer.put(paramInt + 2, paramVector4f.z);
/* 1383 */     paramFloatBuffer.put(paramInt + 3, paramVector4f.w);
/*      */   }
/*      */ 
/*      */   public static void a(FloatBuffer paramFloatBuffer, float paramFloat, javax.vecmath.Vector3f[] paramArrayOfVector3f, int paramInt)
/*      */   {
/* 1418 */     jdField_c_of_type_JavaxVecmathVector4f.set(paramArrayOfVector3f[2].x, paramArrayOfVector3f[2].y, paramArrayOfVector3f[2].z, paramFloat);
/* 1419 */     jdField_d_of_type_JavaxVecmathVector4f.set(paramArrayOfVector3f[3].x, paramArrayOfVector3f[3].y, paramArrayOfVector3f[3].z, paramFloat);
/* 1420 */     if (paramInt < 0)
/*      */     {
/* 1423 */       a(paramFloatBuffer, jdField_c_of_type_JavaxVecmathVector4f);
/* 1424 */       a(paramFloatBuffer, jdField_d_of_type_JavaxVecmathVector4f); return;
/*      */     }
/* 1426 */     a(paramFloatBuffer, jdField_c_of_type_JavaxVecmathVector4f, paramInt);
/*      */ 
/* 1430 */     a(paramFloatBuffer, jdField_d_of_type_JavaxVecmathVector4f, paramInt + 4);
/*      */   }
/*      */ 
/*      */   public static void a(FloatBuffer paramFloatBuffer, float paramFloat, javax.vecmath.Vector3f[] paramArrayOfVector3f)
/*      */   {
/* 1437 */     jdField_a_of_type_JavaxVecmathVector4f.set(paramArrayOfVector3f[0].x, paramArrayOfVector3f[0].y, paramArrayOfVector3f[0].z, paramFloat);
/* 1438 */     jdField_b_of_type_JavaxVecmathVector4f.set(paramArrayOfVector3f[1].x, paramArrayOfVector3f[1].y, paramArrayOfVector3f[1].z, paramFloat);
/* 1439 */     jdField_c_of_type_JavaxVecmathVector4f.set(paramArrayOfVector3f[2].x, paramArrayOfVector3f[2].y, paramArrayOfVector3f[2].z, paramFloat);
/* 1440 */     jdField_d_of_type_JavaxVecmathVector4f.set(paramArrayOfVector3f[3].x, paramArrayOfVector3f[3].y, paramArrayOfVector3f[3].z, paramFloat);
/*      */ 
/* 1442 */     a(paramFloatBuffer, jdField_a_of_type_JavaxVecmathVector4f);
/* 1443 */     a(paramFloatBuffer, jdField_b_of_type_JavaxVecmathVector4f);
/* 1444 */     a(paramFloatBuffer, jdField_c_of_type_JavaxVecmathVector4f);
/* 1445 */     a(paramFloatBuffer, jdField_d_of_type_JavaxVecmathVector4f);
/*      */   }
/*      */ 
/*      */   public static void a(float paramFloat)
/*      */   {
/* 1507 */     jdField_a_of_type_OrgLwjglUtilVectorVector3f.set(0.0F, 0.0F, 1.0F);
/* 1508 */     xe.jdField_a_of_type_OrgLwjglUtilVectorMatrix4f.rotate(paramFloat, jdField_a_of_type_OrgLwjglUtilVectorVector3f);
/* 1509 */     GL11.glRotatef(paramFloat, 0.0F, 0.0F, 1.0F);
/*      */   }
/*      */   public static void b(float paramFloat1, float paramFloat2, float paramFloat3) {
/* 1512 */     jdField_a_of_type_OrgLwjglUtilVectorVector3f.set(paramFloat1, paramFloat2, paramFloat3);
/* 1513 */     xe.jdField_a_of_type_OrgLwjglUtilVectorMatrix4f.scale(jdField_a_of_type_OrgLwjglUtilVectorVector3f);
/* 1514 */     GL11.glScalef(paramFloat1, paramFloat2, paramFloat3);
/*      */   }
/*      */ 
/*      */   public static void a(javax.vecmath.Vector3f paramVector3f, Transform paramTransform)
/*      */   {
/* 1535 */     paramTransform.basis.setColumn(2, paramVector3f);
/*      */   }
/*      */ 
/*      */   public static void b(javax.vecmath.Vector3f paramVector3f, Transform paramTransform)
/*      */   {
/* 1544 */     paramVector3f.negate();
/* 1545 */     c(paramVector3f, paramTransform);
/*      */   }
/*      */ 
/*      */   public static void c(javax.vecmath.Vector3f paramVector3f, Transform paramTransform)
/*      */   {
/* 1554 */     paramTransform.basis.setColumn(0, paramVector3f);
/*      */   }
/*      */ 
/*      */   public static void d(javax.vecmath.Vector3f paramVector3f, Transform paramTransform)
/*      */   {
/* 1563 */     paramTransform.basis.setColumn(1, paramVector3f);
/*      */   }
/*      */ 
/*      */   public static void a(javax.vecmath.Vector3f paramVector3f)
/*      */   {
/* 1584 */     c(paramVector3f.x, paramVector3f.y, paramVector3f.z);
/*      */   }
/*      */   public static void c(float paramFloat1, float paramFloat2, float paramFloat3) {
/* 1587 */     jdField_a_of_type_OrgLwjglUtilVectorVector3f.set(paramFloat1, paramFloat2, paramFloat3);
/* 1588 */     xe.jdField_a_of_type_OrgLwjglUtilVectorMatrix4f.translate(jdField_a_of_type_OrgLwjglUtilVectorVector3f);
/* 1589 */     GL11.glTranslatef(paramFloat1, paramFloat2, paramFloat3);
/*      */   }
/*      */ 
/*      */   public static void a(zj paramzj, String paramString, Color4f paramColor4f)
/*      */   {
/*      */     int m;
/* 1603 */     if ((
/* 1603 */       m = paramzj.a(paramString)) == 
/* 1603 */       -1) {
/* 1604 */       a(paramzj, paramString); return;
/*      */     }
/* 1606 */     GL20.glUniform4f(m, paramColor4f.x, paramColor4f.y, paramColor4f.z, paramColor4f.w);
/*      */   }
/*      */ 
/*      */   public static void a(zj paramzj, String paramString, float paramFloat)
/*      */   {
/*      */     int m;
/* 1620 */     if ((
/* 1620 */       m = paramzj.a(paramString)) == 
/* 1620 */       -1) {
/* 1621 */       a(paramzj, paramString); return;
/*      */     }
/* 1623 */     GL20.glUniform1f(m, paramFloat);
/*      */   }
/*      */ 
/*      */   public static void a(zj paramzj, String paramString, FloatBuffer paramFloatBuffer)
/*      */   {
/*      */     int m;
/* 1636 */     if ((
/* 1636 */       m = paramzj.a(paramString)) == 
/* 1636 */       -1) {
/* 1637 */       a(paramzj, paramString); return;
/*      */     }
/* 1639 */     GL20.glUniform1(m, paramFloatBuffer);
/*      */   }
/*      */ 
/*      */   public static void b(zj paramzj, String paramString, FloatBuffer paramFloatBuffer)
/*      */   {
/*      */     int m;
/* 1652 */     if ((
/* 1652 */       m = paramzj.a(paramString)) == 
/* 1652 */       -1) {
/* 1653 */       a(paramzj, paramString); return;
/*      */     }
/* 1655 */     GL20.glUniform3(m, paramFloatBuffer);
/*      */   }
/*      */ 
/*      */   public static void a(zj paramzj, String paramString, int paramInt)
/*      */   {
/*      */     int m;
/* 1668 */     if ((
/* 1668 */       m = paramzj.a(paramString)) == 
/* 1668 */       -1) {
/* 1669 */       a(paramzj, paramString); return;
/*      */     }
/* 1671 */     GL20.glUniform1i(m, paramInt);
/*      */   }
/*      */ 
/*      */   public static void c(zj paramzj, String paramString, FloatBuffer paramFloatBuffer)
/*      */   {
/* 1709 */     int m = paramzj.a(paramString);
/* 1710 */     if ((!jdField_a_of_type_Boolean) && (paramFloatBuffer.remaining() != 16)) throw new AssertionError();
/* 1711 */     if (m == -1) {
/* 1712 */       a(paramzj, paramString); return;
/*      */     }
/* 1714 */     GL20.glUniformMatrix4(m, false, paramFloatBuffer);
/*      */   }
/*      */ 
/*      */   public static void a(zj paramzj, String paramString, Transform[] paramArrayOfTransform)
/*      */   {
/*      */     FloatBuffer localFloatBuffer;
/* 1789 */     if (((
/* 1789 */       localFloatBuffer = paramzj.jdField_a_of_type_JavaNioFloatBuffer) == null) || 
/* 1789 */       (localFloatBuffer.capacity() < paramArrayOfTransform.length << 4)) {
/* 1790 */       localFloatBuffer = BufferUtils.createFloatBuffer(paramArrayOfTransform.length << 4);
/* 1791 */       paramzj.jdField_a_of_type_JavaNioFloatBuffer = localFloatBuffer;
/*      */     }
/* 1793 */     localFloatBuffer.limit(paramArrayOfTransform.length << 4);
/*      */ 
/* 1795 */     localFloatBuffer.rewind();
/* 1796 */     int m = (paramArrayOfTransform = paramArrayOfTransform).length; for (int n = 0; n < m; n++) { paramArrayOfTransform[n]
/* 1797 */         .getOpenGLMatrix(jdField_a_of_type_ArrayOfFloat);
/* 1798 */       localFloatBuffer.put(jdField_a_of_type_ArrayOfFloat);
/*      */     }
/* 1800 */     localFloatBuffer.flip();
/*      */ 
/* 1803 */     if ((
/* 1803 */       paramArrayOfTransform = paramzj.a(paramString)) == 
/* 1803 */       -1) {
/* 1804 */       a(paramzj, paramString); return;
/*      */     }
/* 1806 */     GL20.glUniformMatrix4(paramArrayOfTransform, false, localFloatBuffer);
/*      */   }
/*      */ 
/*      */   public static void a(zj paramzj, String paramString, float paramFloat1, float paramFloat2)
/*      */   {
/*      */     int m;
/* 1820 */     if ((
/* 1820 */       m = paramzj.a(paramString)) == 
/* 1820 */       -1) {
/* 1821 */       a(paramzj, paramString); return;
/*      */     }
/* 1823 */     GL20.glUniform2f(m, paramFloat1, paramFloat2);
/*      */   }
/*      */ 
/*      */   public static void a(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
/*      */   {
/* 1864 */     if ((jdField_e_of_type_JavaxVecmathVector4f.x != paramFloat1) || (jdField_e_of_type_JavaxVecmathVector4f.y != paramFloat2) || (jdField_e_of_type_JavaxVecmathVector4f.z != paramFloat3) || (jdField_e_of_type_JavaxVecmathVector4f.w != paramFloat4))
/*      */     {
/* 1869 */       jdField_e_of_type_JavaxVecmathVector4f.set(paramFloat1, paramFloat2, paramFloat3, paramFloat4);
/* 1870 */       GL11.glColor4f(paramFloat1, paramFloat2, paramFloat3, paramFloat4);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void a(zj paramzj, String paramString, float paramFloat1, float paramFloat2, float paramFloat3)
/*      */   {
/*      */     int m;
/* 1878 */     if ((
/* 1878 */       m = paramzj.a(paramString)) == 
/* 1878 */       -1) {
/* 1879 */       a(paramzj, paramString); return;
/*      */     }
/* 1881 */     GL20.glUniform3f(m, paramFloat1, paramFloat2, paramFloat3);
/*      */   }
/*      */ 
/*      */   public static void a(zj paramzj, String paramString, javax.vecmath.Vector3f paramVector3f)
/*      */   {
/*      */     int m;
/* 1888 */     if ((
/* 1888 */       m = paramzj.a(paramString)) == 
/* 1888 */       -1) {
/* 1889 */       a(paramzj, paramString); return;
/*      */     }
/* 1891 */     GL20.glUniform3f(m, paramVector3f.x, paramVector3f.y, paramVector3f.z);
/*      */   }
/*      */ 
/*      */   public static void a(zj paramzj, String paramString, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
/*      */   {
/*      */     int m;
/* 1914 */     if ((
/* 1914 */       m = paramzj.a(paramString)) == 
/* 1914 */       -1) {
/* 1915 */       a(paramzj, paramString); return;
/*      */     }
/* 1917 */     GL20.glUniform4f(m, paramFloat1, paramFloat2, paramFloat3, paramFloat4);
/*      */   }
/*      */ 
/*      */   public static void a(zj paramzj, String paramString, Vector4f paramVector4f)
/*      */   {
/*      */     int m;
/* 1922 */     if ((
/* 1922 */       m = paramzj.a(paramString)) == 
/* 1922 */       -1) {
/* 1923 */       a(paramzj, paramString); return;
/*      */     }
/* 1925 */     GL20.glUniform4f(m, paramVector4f.x, paramVector4f.y, paramVector4f.z, paramVector4f.w);
/*      */   }
/*      */ 
/*      */   public static void a(String paramString1, String paramString2, int paramInt1, int paramInt2)
/*      */   {
/* 1940 */     ByteBuffer localByteBuffer = a(paramInt1 * paramInt2 << 2, 0);
/*      */ 
/* 1942 */     GL11.glReadPixels(0, 0, paramInt1, paramInt2, 6408, 5121, localByteBuffer);
/*      */ 
/* 1944 */     paramString1 = new File(paramString1 + "." + paramString2);
/* 1945 */     paramString2 = paramString2.toUpperCase();
/* 1946 */     BufferedImage localBufferedImage = new BufferedImage(paramInt1, paramInt2, 2);
/*      */ 
/* 1948 */     for (int m = 0; m < paramInt1; m++) {
/* 1949 */       for (int n = 0; n < paramInt2; n++) {
/* 1950 */         int i1 = m + paramInt1 * n << 2;
/* 1951 */         int i2 = localByteBuffer.get(i1) & 0xFF;
/* 1952 */         int i3 = localByteBuffer.get(i1 + 1) & 0xFF;
/* 1953 */         int i4 = localByteBuffer.get(i1 + 2) & 0xFF;
/* 1954 */         i1 = localByteBuffer.get(i1 + 3) & 0xFF;
/*      */ 
/* 1957 */         localBufferedImage.setRGB(m, paramInt2 - (n + 1), i1 << 24 | i2 << 16 | i3 << 8 | i4);
/*      */       }
/*      */     }
/*      */ 
/*      */     try
/*      */     {
/* 1963 */       ImageIO.write(localBufferedImage, paramString2, paramString1);
/* 1964 */       return; } catch (IOException localIOException) { localIOException.printStackTrace(); }
/*      */ 
/*      */   }
/*      */ 
/*      */   static
/*      */   {
/*   90 */     BufferUtils.createFloatBuffer(3);
/*      */ 
/*   92 */     jdField_a_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(16);
/*   93 */     jdField_b_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(16);
/*   94 */     BufferUtils.createFloatBuffer(16);
/*   95 */     BufferUtils.createFloatBuffer(16);
/*   96 */     BufferUtils.createFloatBuffer(16);
/*   97 */     new Matrix3f();
/*      */ 
/*   99 */     new javax.vecmath.Vector3f();
/*  100 */     new javax.vecmath.Vector3f();
/*      */ 
/*  102 */     new javax.vecmath.Vector3f();
/*      */ 
/*  104 */     new Transform();
/*  105 */     BufferUtils.createIntBuffer(1);
/*  106 */     jdField_a_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
/*  107 */     jdField_a_of_type_ArrayOfJavaNioByteBuffer = new ByteBuffer[6];
/*  108 */     BufferUtils.createFloatBuffer(16);
/*      */ 
/*  110 */     jdField_a_of_type_JavaxVecmathVector3f = new javax.vecmath.Vector3f();
/*  111 */     jdField_b_of_type_JavaxVecmathVector3f = new javax.vecmath.Vector3f();
/*      */ 
/*  113 */     jdField_c_of_type_JavaxVecmathVector3f = new javax.vecmath.Vector3f();
/*  114 */     jdField_d_of_type_JavaxVecmathVector3f = new javax.vecmath.Vector3f();
/*      */ 
/*  116 */     jdField_a_of_type_JavaxVecmathVector4f = new Vector4f();
/*  117 */     jdField_b_of_type_JavaxVecmathVector4f = new Vector4f();
/*  118 */     jdField_c_of_type_JavaxVecmathVector4f = new Vector4f();
/*      */ 
/*  122 */     jdField_d_of_type_JavaxVecmathVector4f = new Vector4f();
/*  123 */     new javax.vecmath.Vector3f();
/*  124 */     new javax.vecmath.Vector3f();
/*      */ 
/*  126 */     new javax.vecmath.Vector3f();
/*      */ 
/*  128 */     new javax.vecmath.Vector3f();
/*      */ 
/*  130 */     jdField_e_of_type_JavaxVecmathVector3f = new javax.vecmath.Vector3f();
/*      */ 
/*  132 */     f = new javax.vecmath.Vector3f();
/*      */ 
/*  135 */     new Vector4f();
/*  136 */     new Vector4f();
/*  137 */     g = new javax.vecmath.Vector3f();
/*  138 */     h = new javax.vecmath.Vector3f();
/*      */ 
/*  141 */     i = new javax.vecmath.Vector3f();
/*      */ 
/*  143 */     j = new javax.vecmath.Vector3f();
/*      */ 
/*  146 */     k = new javax.vecmath.Vector3f();
/*      */ 
/*  148 */     l = new javax.vecmath.Vector3f();
/*      */ 
/*  151 */     jdField_a_of_type_OrgLwjglUtilVectorVector3f = new org.lwjgl.util.vector.Vector3f(0.0F, 0.0F, 0.0F);
/*      */ 
/*  153 */     jdField_a_of_type_ArrayOfJavaNioFloatBuffer = new FloatBuffer[64];
/*  154 */     jdField_a_of_type_Int = 0;
/*  155 */     jdField_b_of_type_ArrayOfJavaNioFloatBuffer = new FloatBuffer[64];
/*      */ 
/*  157 */     jdField_b_of_type_Int = 0;
/*      */ 
/*  159 */     new Transform();
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.graphicsengine.core.GlUtil
 * JD-Core Version:    0.6.2
 */