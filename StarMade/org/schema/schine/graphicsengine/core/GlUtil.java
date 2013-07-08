package org.schema.schine.graphicsengine.core;

import class_1377;
import class_933;
import class_949;
import class_969;
import class_971;
import class_988;
import com.bulletphysics.linearmath.AabbUtil2;
import com.bulletphysics.linearmath.Transform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.vecmath.Color4f;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;
import org.schema.common.FastMath;
import org.schema.schine.graphicsengine.camera.Camera;
import org.schema.schine.graphicsengine.forms.Mesh;

public class GlUtil
{
  public static FloatBuffer field_1718;
  public static FloatBuffer field_1719;
  private static IntBuffer jdField_field_1718_of_type_JavaNioIntBuffer;
  private static ByteBuffer[] jdField_field_1718_of_type_ArrayOfJavaNioByteBuffer;
  private static javax.vecmath.Vector3f jdField_field_1718_of_type_JavaxVecmathVector3f;
  private static javax.vecmath.Vector3f jdField_field_1719_of_type_JavaxVecmathVector3f;
  private static javax.vecmath.Vector3f jdField_field_1720_of_type_JavaxVecmathVector3f;
  private static javax.vecmath.Vector3f jdField_field_1721_of_type_JavaxVecmathVector3f;
  private static Vector4f jdField_field_1718_of_type_JavaxVecmathVector4f;
  private static Vector4f jdField_field_1719_of_type_JavaxVecmathVector4f;
  private static Vector4f jdField_field_1720_of_type_JavaxVecmathVector4f;
  private static Vector4f jdField_field_1721_of_type_JavaxVecmathVector4f;
  private static javax.vecmath.Vector3f jdField_field_1722_of_type_JavaxVecmathVector3f;
  private static javax.vecmath.Vector3f field_1723;
  private static javax.vecmath.Vector3f field_1724;
  private static javax.vecmath.Vector3f field_1725;
  private static javax.vecmath.Vector3f field_1726;
  private static javax.vecmath.Vector3f field_1727;
  private static javax.vecmath.Vector3f field_1728;
  private static javax.vecmath.Vector3f field_1729;
  private static org.lwjgl.util.vector.Vector3f jdField_field_1718_of_type_OrgLwjglUtilVectorVector3f;
  private static FloatBuffer[] jdField_field_1718_of_type_ArrayOfJavaNioFloatBuffer;
  private static int jdField_field_1718_of_type_Int;
  private static FloatBuffer[] jdField_field_1719_of_type_ArrayOfJavaNioFloatBuffer;
  private static int jdField_field_1719_of_type_Int;
  private static float[] jdField_field_1718_of_type_ArrayOfFloat = new float[16];
  private static FloatBuffer jdField_field_1720_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(16);
  private static Matrix4f jdField_field_1718_of_type_OrgLwjglUtilVectorMatrix4f = new Matrix4f();
  private static int jdField_field_1720_of_type_Int;
  private static Vector4f jdField_field_1722_of_type_JavaxVecmathVector4f = new Vector4f();
  
  public static Transform a(javax.vecmath.Vector3f paramVector3f1, javax.vecmath.Vector3f paramVector3f2, javax.vecmath.Vector3f paramVector3f3, Transform paramTransform)
  {
    field_1727.sub(paramVector3f3, paramVector3f1);
    field_1727.normalize();
    field_1728.set(paramVector3f2);
    field_1729.cross(field_1728, field_1727);
    field_1729.normalize();
    field_1727.cross(field_1729, field_1728);
    d2(field_1728, paramTransform);
    a30(field_1727, paramTransform);
    c3(field_1729, paramTransform);
    paramTransform.origin.set(paramVector3f1);
    return paramTransform;
  }
  
  public static boolean a1(javax.vecmath.Vector3f paramVector3f1, javax.vecmath.Vector3f paramVector3f2)
  {
    return (paramVector3f1.field_615 <= paramVector3f2.field_615) && (paramVector3f1.field_616 <= paramVector3f2.field_616) && (paramVector3f1.field_617 <= paramVector3f2.field_617);
  }
  
  public static void a2()
  {
    for (int i = 0; i < jdField_field_1718_of_type_ArrayOfJavaNioFloatBuffer.length; i++) {
      jdField_field_1718_of_type_ArrayOfJavaNioFloatBuffer[i] = BufferUtils.createFloatBuffer(16);
    }
    for (i = 0; i < jdField_field_1719_of_type_ArrayOfJavaNioFloatBuffer.length; i++) {
      jdField_field_1719_of_type_ArrayOfJavaNioFloatBuffer[i] = BufferUtils.createFloatBuffer(16);
    }
  }
  
  public static void a3(ByteBuffer paramByteBuffer)
  {
    Method localMethod;
    (localMethod = paramByteBuffer.getClass().getMethod("cleaner", new Class[0])).setAccessible(true);
    (localMethod = (paramByteBuffer = localMethod.invoke(paramByteBuffer, new Object[0])).getClass().getMethod("clean", new Class[0])).setAccessible(true);
    localMethod.invoke(paramByteBuffer, new Object[0]);
  }
  
  public static javax.vecmath.Vector3f a4(javax.vecmath.Vector3f paramVector3f, Transform paramTransform)
  {
    paramTransform.basis.getColumn(2, paramVector3f);
    paramVector3f.negate();
    return paramVector3f;
  }
  
  public static javax.vecmath.Vector3f b(javax.vecmath.Vector3f paramVector3f, Transform paramTransform)
  {
    f(paramVector3f, paramTransform);
    paramVector3f.negate();
    return paramVector3f;
  }
  
  public static ByteBuffer a5(int paramInt1, int paramInt2)
  {
    if (((paramInt2 = jdField_field_1718_of_type_ArrayOfJavaNioByteBuffer[paramInt2]) == null) || (paramInt2.capacity() < paramInt1))
    {
      if (paramInt2 != null)
      {
        try
        {
          a3(paramInt2);
        }
        catch (IllegalArgumentException localIllegalArgumentException)
        {
          localIllegalArgumentException;
        }
        catch (SecurityException localSecurityException)
        {
          localSecurityException;
        }
        catch (IllegalAccessException localIllegalAccessException)
        {
          localIllegalAccessException;
        }
        catch (InvocationTargetException localInvocationTargetException)
        {
          localInvocationTargetException;
        }
        catch (NoSuchMethodException localNoSuchMethodException)
        {
          localNoSuchMethodException;
        }
        System.gc();
      }
      paramInt2 = BufferUtils.createByteBuffer(paramInt1);
    }
    paramInt2.limit(paramInt1);
    paramInt2.rewind();
    return paramInt2;
  }
  
  public static javax.vecmath.Vector3f a6(javax.vecmath.Vector3f paramVector3f)
  {
    jdField_field_1719_of_type_JavaNioFloatBuffer.rewind();
    class_969.field_1259.store(jdField_field_1719_of_type_JavaNioFloatBuffer);
    paramVector3f.field_615 = jdField_field_1719_of_type_JavaNioFloatBuffer.get(2);
    paramVector3f.field_616 = jdField_field_1719_of_type_JavaNioFloatBuffer.get(6);
    paramVector3f.field_617 = jdField_field_1719_of_type_JavaNioFloatBuffer.get(10);
    return paramVector3f;
  }
  
  public static javax.vecmath.Vector3f c(javax.vecmath.Vector3f paramVector3f, Transform paramTransform)
  {
    paramTransform.basis.getColumn(2, paramVector3f);
    return paramVector3f;
  }
  
  public static String a7()
  {
    int i = GL11.glGetError();
    String str = "unknown " + i;
    switch (i)
    {
    case 0: 
      return null;
    case 1285: 
      str = "GL_OUT_OF_MEMORY";
      break;
    case 1280: 
      str = "GL_INVALID_ENUM";
      break;
    case 1281: 
      str = "GL_INVALID_VALUE";
      break;
    case 1282: 
      str = "GL_INVALID_OPERATION";
      break;
    case 1283: 
      str = "GL_STACK_OVERFLOW: modelstack-sizes: proj: " + GL11.glGetInteger(2980) + ", model: " + GL11.glGetInteger(2979);
      break;
    case 1284: 
      str = "GL_STACK_UNDERFLOW";
      break;
    case 1286: 
      str = "GL_INVALID_FRAMEBUFFER_OPERATION";
    }
    return str;
  }
  
  public static IntBuffer a8()
  {
    jdField_field_1718_of_type_JavaNioIntBuffer.rewind();
    return jdField_field_1718_of_type_JavaNioIntBuffer;
  }
  
  public static javax.vecmath.Vector3f d(javax.vecmath.Vector3f paramVector3f, Transform paramTransform)
  {
    e(paramVector3f, paramTransform);
    paramVector3f.negate();
    return paramVector3f;
  }
  
  public static void a9(Transform paramTransform, float paramFloat, javax.vecmath.Vector3f[] paramArrayOfVector3f)
  {
    paramArrayOfVector3f[0].set(-paramFloat, -paramFloat, 0.0F);
    paramArrayOfVector3f[1].set(paramFloat, -paramFloat, 0.0F);
    paramArrayOfVector3f[2].set(paramFloat, paramFloat, 0.0F);
    paramArrayOfVector3f[3].set(-paramFloat, paramFloat, 0.0F);
    paramTransform.transform(paramArrayOfVector3f[0]);
    paramTransform.transform(paramArrayOfVector3f[1]);
    paramTransform.transform(paramArrayOfVector3f[2]);
    paramTransform.transform(paramArrayOfVector3f[3]);
  }
  
  public static javax.vecmath.Vector3f b1(javax.vecmath.Vector3f paramVector3f)
  {
    jdField_field_1719_of_type_JavaNioFloatBuffer.rewind();
    class_969.field_1259.store(jdField_field_1719_of_type_JavaNioFloatBuffer);
    paramVector3f.field_615 = jdField_field_1719_of_type_JavaNioFloatBuffer.get(0);
    paramVector3f.field_616 = jdField_field_1719_of_type_JavaNioFloatBuffer.get(4);
    paramVector3f.field_617 = jdField_field_1719_of_type_JavaNioFloatBuffer.get(8);
    return paramVector3f;
  }
  
  public static javax.vecmath.Vector3f e(javax.vecmath.Vector3f paramVector3f, Transform paramTransform)
  {
    paramTransform.basis.getColumn(0, paramVector3f);
    return paramVector3f;
  }
  
  public static javax.vecmath.Vector3f c1(javax.vecmath.Vector3f paramVector3f)
  {
    jdField_field_1719_of_type_JavaNioFloatBuffer.rewind();
    class_969.field_1259.store(jdField_field_1719_of_type_JavaNioFloatBuffer);
    paramVector3f.field_615 = jdField_field_1719_of_type_JavaNioFloatBuffer.get(1);
    paramVector3f.field_616 = jdField_field_1719_of_type_JavaNioFloatBuffer.get(5);
    paramVector3f.field_617 = jdField_field_1719_of_type_JavaNioFloatBuffer.get(9);
    return paramVector3f;
  }
  
  public static javax.vecmath.Vector3f f(javax.vecmath.Vector3f paramVector3f, Transform paramTransform)
  {
    paramTransform.basis.getColumn(1, paramVector3f);
    return paramVector3f;
  }
  
  public static void b2()
  {
    if (jdField_field_1720_of_type_Int == 5889) {
      class_969.field_1260.setIdentity();
    } else {
      class_969.field_1259.setIdentity();
    }
    GL11.glLoadIdentity();
  }
  
  public static void a10(FloatBuffer paramFloatBuffer)
  {
    paramFloatBuffer.rewind();
    if (jdField_field_1720_of_type_Int == 5889) {
      class_969.field_1260.load(paramFloatBuffer);
    } else {
      class_969.field_1259.load(paramFloatBuffer);
    }
    paramFloatBuffer.rewind();
    GL11.glLoadMatrix(paramFloatBuffer);
  }
  
  public static void a11(Transform paramTransform)
  {
    paramTransform.getOpenGLMatrix(jdField_field_1718_of_type_ArrayOfFloat);
    jdField_field_1720_of_type_JavaNioFloatBuffer.rewind();
    jdField_field_1720_of_type_JavaNioFloatBuffer.put(jdField_field_1718_of_type_ArrayOfFloat);
    jdField_field_1720_of_type_JavaNioFloatBuffer.rewind();
    a10(jdField_field_1720_of_type_JavaNioFloatBuffer);
  }
  
  public static void a12(int paramInt)
  {
    GL11.glMatrixMode(GlUtil.jdField_field_1720_of_type_Int = paramInt);
  }
  
  public static void b3(Transform paramTransform)
  {
    jdField_field_1720_of_type_JavaNioFloatBuffer.rewind();
    FloatBuffer localFloatBuffer = jdField_field_1720_of_type_JavaNioFloatBuffer;
    paramTransform = paramTransform;
    localFloatBuffer.put(paramTransform.basis.m00);
    localFloatBuffer.put(paramTransform.basis.m10);
    localFloatBuffer.put(paramTransform.basis.m20);
    localFloatBuffer.put(0.0F);
    localFloatBuffer.put(paramTransform.basis.m01);
    localFloatBuffer.put(paramTransform.basis.m11);
    localFloatBuffer.put(paramTransform.basis.m21);
    localFloatBuffer.put(0.0F);
    localFloatBuffer.put(paramTransform.basis.m02);
    localFloatBuffer.put(paramTransform.basis.m12);
    localFloatBuffer.put(paramTransform.basis.m22);
    localFloatBuffer.put(0.0F);
    localFloatBuffer.put(paramTransform.origin.field_615);
    localFloatBuffer.put(paramTransform.origin.field_616);
    localFloatBuffer.put(paramTransform.origin.field_617);
    localFloatBuffer.put(1.0F);
    jdField_field_1720_of_type_JavaNioFloatBuffer.rewind();
    (paramTransform = jdField_field_1720_of_type_JavaNioFloatBuffer).rewind();
    jdField_field_1718_of_type_OrgLwjglUtilVectorMatrix4f.load(paramTransform);
    Matrix4f.mul(class_969.field_1259, jdField_field_1718_of_type_OrgLwjglUtilVectorMatrix4f, class_969.field_1259);
    paramTransform.rewind();
    GL11.glMultMatrix(paramTransform);
  }
  
  public static final void a13(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5)
  {
    float f2 = 2.0F / paramFloat1;
    float f3 = 2.0F / (paramFloat3 - paramFloat2);
    float f4 = -2.0F / (paramFloat5 - paramFloat4);
    float f1 = -(paramFloat1 + 0.0F) / paramFloat1;
    paramFloat1 = -(paramFloat3 + paramFloat2) / (paramFloat3 - paramFloat2);
    paramFloat2 = -(paramFloat5 + paramFloat4) / (paramFloat5 - paramFloat4);
    jdField_field_1718_of_type_ArrayOfFloat[0] = f2;
    jdField_field_1718_of_type_ArrayOfFloat[1] = 0.0F;
    jdField_field_1718_of_type_ArrayOfFloat[2] = 0.0F;
    jdField_field_1718_of_type_ArrayOfFloat[3] = 0.0F;
    jdField_field_1718_of_type_ArrayOfFloat[4] = 0.0F;
    jdField_field_1718_of_type_ArrayOfFloat[5] = f3;
    jdField_field_1718_of_type_ArrayOfFloat[6] = 0.0F;
    jdField_field_1718_of_type_ArrayOfFloat[7] = 0.0F;
    jdField_field_1718_of_type_ArrayOfFloat[8] = 0.0F;
    jdField_field_1718_of_type_ArrayOfFloat[9] = 0.0F;
    jdField_field_1718_of_type_ArrayOfFloat[10] = f4;
    jdField_field_1718_of_type_ArrayOfFloat[11] = 0.0F;
    jdField_field_1718_of_type_ArrayOfFloat[12] = f1;
    jdField_field_1718_of_type_ArrayOfFloat[13] = paramFloat1;
    jdField_field_1718_of_type_ArrayOfFloat[14] = paramFloat2;
    jdField_field_1718_of_type_ArrayOfFloat[15] = 1.0F;
    jdField_field_1720_of_type_JavaNioFloatBuffer.rewind();
    jdField_field_1720_of_type_JavaNioFloatBuffer.put(jdField_field_1718_of_type_ArrayOfFloat);
    jdField_field_1720_of_type_JavaNioFloatBuffer.rewind();
    class_969.field_1260.load(jdField_field_1720_of_type_JavaNioFloatBuffer);
    jdField_field_1720_of_type_JavaNioFloatBuffer.rewind();
    GL11.glLoadMatrix(jdField_field_1720_of_type_JavaNioFloatBuffer);
  }
  
  public static void c2()
  {
    if (jdField_field_1720_of_type_Int == 5889)
    {
      if ((!jdField_field_1718_of_type_Boolean) && (jdField_field_1719_of_type_Int <= 0)) {
        throw new AssertionError();
      }
      jdField_field_1719_of_type_Int -= 1;
      jdField_field_1719_of_type_ArrayOfJavaNioFloatBuffer[jdField_field_1719_of_type_Int].rewind();
      class_969.field_1260.load(jdField_field_1719_of_type_ArrayOfJavaNioFloatBuffer[jdField_field_1719_of_type_Int]);
      GL11.glPopMatrix();
      return;
    }
    if ((!jdField_field_1718_of_type_Boolean) && (jdField_field_1718_of_type_Int <= 0)) {
      throw new AssertionError();
    }
    jdField_field_1718_of_type_Int -= 1;
    jdField_field_1718_of_type_ArrayOfJavaNioFloatBuffer[jdField_field_1718_of_type_Int].rewind();
    class_969.field_1259.load(jdField_field_1718_of_type_ArrayOfJavaNioFloatBuffer[jdField_field_1718_of_type_Int]);
    GL11.glPopMatrix();
  }
  
  public static void d1()
  {
    if (jdField_field_1720_of_type_Int == 5889)
    {
      jdField_field_1719_of_type_ArrayOfJavaNioFloatBuffer[jdField_field_1719_of_type_Int].rewind();
      class_969.field_1260.store(jdField_field_1719_of_type_ArrayOfJavaNioFloatBuffer[jdField_field_1719_of_type_Int]);
      jdField_field_1719_of_type_Int += 1;
      if ((!jdField_field_1718_of_type_Boolean) && (jdField_field_1719_of_type_Int > jdField_field_1719_of_type_ArrayOfJavaNioFloatBuffer.length)) {
        throw new AssertionError();
      }
      GL11.glPushMatrix();
      return;
    }
    jdField_field_1718_of_type_ArrayOfJavaNioFloatBuffer[jdField_field_1718_of_type_Int].rewind();
    class_969.field_1259.store(jdField_field_1718_of_type_ArrayOfJavaNioFloatBuffer[jdField_field_1718_of_type_Int]);
    jdField_field_1718_of_type_Int += 1;
    if ((!jdField_field_1718_of_type_Boolean) && (jdField_field_1718_of_type_Int > jdField_field_1718_of_type_ArrayOfJavaNioFloatBuffer.length)) {
      throw new AssertionError();
    }
    GL11.glPushMatrix();
  }
  
  public static void a14(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    a13(paramFloat1, paramFloat2, paramFloat3, -1.0F, 1.0F);
  }
  
  public static Matrix4f a15(Matrix4f paramMatrix4f, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, boolean paramBoolean)
  {
    paramMatrix4f.setZero();
    float f1;
    float f2 = (paramFloat1 = -(f1 = paramFloat3 * FastMath.n(paramFloat1 / 360.0F * 3.141593F))) / paramFloat2;
    paramFloat2 = f1 / paramFloat2;
    paramMatrix4f.m00 = (paramFloat3 * 2.0F / (f1 - paramFloat1));
    paramMatrix4f.m11 = (paramFloat3 * 2.0F / (paramFloat2 - f2));
    paramMatrix4f.m22 = (-(paramFloat4 + paramFloat3) / (paramFloat4 - paramFloat3));
    paramMatrix4f.m20 = ((f1 + paramFloat1) / (f1 - paramFloat1));
    paramMatrix4f.m21 = ((paramFloat2 + f2) / (paramFloat2 - f2));
    paramMatrix4f.m23 = -1.0F;
    paramMatrix4f.m32 = (-(paramFloat4 * 2.0F * paramFloat3) / (paramFloat4 - paramFloat3));
    if (paramBoolean)
    {
      jdField_field_1718_of_type_JavaNioFloatBuffer.rewind();
      paramMatrix4f.store(jdField_field_1718_of_type_JavaNioFloatBuffer);
      jdField_field_1718_of_type_JavaNioFloatBuffer.rewind();
      a10(jdField_field_1718_of_type_JavaNioFloatBuffer);
    }
    return paramMatrix4f;
  }
  
  public static int a16(float paramFloat1, float paramFloat2, float paramFloat3, FloatBuffer paramFloatBuffer1, FloatBuffer paramFloatBuffer2, IntBuffer paramIntBuffer, FloatBuffer paramFloatBuffer3)
  {
    float[] arrayOfFloat;
    (arrayOfFloat = new float[8])[0] = (paramFloatBuffer1.get(0) * paramFloat1 + paramFloatBuffer1.get(4) * paramFloat2 + paramFloatBuffer1.get(8) * paramFloat3 + paramFloatBuffer1.get(12));
    arrayOfFloat[1] = (paramFloatBuffer1.get(1) * paramFloat1 + paramFloatBuffer1.get(5) * paramFloat2 + paramFloatBuffer1.get(9) * paramFloat3 + paramFloatBuffer1.get(13));
    arrayOfFloat[2] = (paramFloatBuffer1.get(2) * paramFloat1 + paramFloatBuffer1.get(6) * paramFloat2 + paramFloatBuffer1.get(10) * paramFloat3 + paramFloatBuffer1.get(14));
    arrayOfFloat[3] = (paramFloatBuffer1.get(3) * paramFloat1 + paramFloatBuffer1.get(7) * paramFloat2 + paramFloatBuffer1.get(11) * paramFloat3 + paramFloatBuffer1.get(15));
    arrayOfFloat[4] = (paramFloatBuffer2.get(0) * arrayOfFloat[0] + paramFloatBuffer2.get(4) * arrayOfFloat[1] + paramFloatBuffer2.get(8) * arrayOfFloat[2] + paramFloatBuffer2.get(12) * arrayOfFloat[3]);
    arrayOfFloat[5] = (paramFloatBuffer2.get(1) * arrayOfFloat[0] + paramFloatBuffer2.get(5) * arrayOfFloat[1] + paramFloatBuffer2.get(9) * arrayOfFloat[2] + paramFloatBuffer2.get(13) * arrayOfFloat[3]);
    arrayOfFloat[6] = (paramFloatBuffer2.get(2) * arrayOfFloat[0] + paramFloatBuffer2.get(6) * arrayOfFloat[1] + paramFloatBuffer2.get(10) * arrayOfFloat[2] + paramFloatBuffer2.get(14) * arrayOfFloat[3]);
    arrayOfFloat[7] = (-arrayOfFloat[2]);
    if (arrayOfFloat[7] == 0.0D) {
      return 0;
    }
    arrayOfFloat[7] = (1.0F / arrayOfFloat[7]);
    arrayOfFloat[4] *= arrayOfFloat[7];
    arrayOfFloat[5] *= arrayOfFloat[7];
    arrayOfFloat[6] *= arrayOfFloat[7];
    paramFloatBuffer3.put(0, (arrayOfFloat[4] * 0.5F + 0.5F) * paramIntBuffer.get(2) + paramIntBuffer.get(0));
    paramFloatBuffer3.put(1, (arrayOfFloat[5] * 0.5F + 0.5F) * paramIntBuffer.get(3) + paramIntBuffer.get(1));
    paramFloatBuffer3.put(2, (1.0F + arrayOfFloat[6]) * 0.5F);
    return 1;
  }
  
  private static void a17(class_1377 paramclass_1377, String paramString)
  {
    if (class_949.field_1188.b1())
    {
      paramclass_1377 = "[ERROR][SHADER] " + paramclass_1377.jdField_field_1578_of_type_JavaLangString + " - " + paramString + " HANDLE -1 ";
      if (!class_971.field_98.contains(paramclass_1377)) {
        class_971.field_98.add(paramclass_1377);
      }
    }
  }
  
  public static boolean a18(Transform paramTransform, Mesh paramMesh)
  {
    field_1725.set(paramMesh.a151().field_1273);
    field_1726.set(paramMesh.a151().field_1274);
    AabbUtil2.transformAabb(field_1725, field_1726, 0.0F, paramTransform, jdField_field_1722_of_type_JavaxVecmathVector3f, field_1723);
    return class_969.a1().a185(jdField_field_1722_of_type_JavaxVecmathVector3f, field_1723);
  }
  
  public static boolean a19(javax.vecmath.Vector3f paramVector3f1, javax.vecmath.Vector3f paramVector3f2, float paramFloat)
  {
    if ((a20(paramVector3f1, paramFloat)) && (a20(paramVector3f2, paramFloat)))
    {
      field_1726.field_615 = Math.min(paramVector3f1.field_615, paramVector3f2.field_615);
      field_1726.field_616 = Math.min(paramVector3f1.field_616, paramVector3f2.field_616);
      field_1726.field_617 = Math.min(paramVector3f1.field_617, paramVector3f2.field_617);
      field_1725.field_615 = Math.max(paramVector3f1.field_615, paramVector3f2.field_615);
      field_1725.field_616 = Math.max(paramVector3f1.field_616, paramVector3f2.field_616);
      field_1725.field_617 = Math.max(paramVector3f1.field_617, paramVector3f2.field_617);
      if (class_969.a1().a185(field_1725, field_1726)) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean a20(javax.vecmath.Vector3f paramVector3f, float paramFloat)
  {
    field_1724.sub(paramVector3f, class_969.a1().a83());
    return field_1724.length() <= paramFloat;
  }
  
  public static boolean b4(javax.vecmath.Vector3f paramVector3f, float paramFloat)
  {
    return (a20(paramVector3f, paramFloat)) && (class_969.a1().a187(paramVector3f));
  }
  
  public static void main(String[] paramArrayOfString) {}
  
  public static void e1()
  {
    String str;
    if ((str = a7()) != null) {
      try
      {
        throw new GLException(str);
      }
      catch (GLException localGLException)
      {
        System.err.println("GL_ERROR: " + str + " ");
        localGLException.printStackTrace();
      }
    }
  }
  
  public static void f1()
  {
    String str;
    if ((str = a7()) != null) {
      try
      {
        throw new GLException(str);
      }
      catch (GLException localGLException)
      {
        System.err.println("GL_ERROR: " + str + " ");
        localGLException.printStackTrace();
        class_933.a2(localGLException);
      }
    }
  }
  
  public static void a21(javax.vecmath.Vector3f paramVector3f1, javax.vecmath.Vector3f paramVector3f2, javax.vecmath.Vector3f paramVector3f3)
  {
    javax.vecmath.Vector3f localVector3f = new javax.vecmath.Vector3f(paramVector3f1);
    new javax.vecmath.Vector3f(paramVector3f1).normalize();
    localVector3f.normalize();
    localVector3f.scale(paramVector3f2.dot(localVector3f));
    paramVector3f3.sub(paramVector3f2, localVector3f);
  }
  
  public static javax.vecmath.Vector3f a22(javax.vecmath.Vector3f paramVector3f1, javax.vecmath.Vector3f paramVector3f2, javax.vecmath.Vector3f paramVector3f3)
  {
    double d = paramVector3f2.dot(paramVector3f3);
    paramVector3f1.set(paramVector3f2);
    paramVector3f1.field_615 = ((float)(paramVector3f1.field_615 - d * paramVector3f3.field_615));
    paramVector3f1.field_616 = ((float)(paramVector3f1.field_616 - d * paramVector3f3.field_616));
    paramVector3f1.field_617 = ((float)(paramVector3f1.field_617 - d * paramVector3f3.field_617));
    return paramVector3f1;
  }
  
  public static void a23(FloatBuffer paramFloatBuffer, javax.vecmath.Vector3f paramVector3f1, javax.vecmath.Vector3f paramVector3f2, Vector4f paramVector4f)
  {
    jdField_field_1718_of_type_JavaxVecmathVector3f.set(paramVector4f.field_596, paramVector4f.field_597, paramVector4f.field_598);
    jdField_field_1718_of_type_JavaxVecmathVector3f.sub(paramVector3f1);
    jdField_field_1719_of_type_JavaxVecmathVector3f.set(paramVector4f.field_596, paramVector4f.field_597, paramVector4f.field_598);
    jdField_field_1719_of_type_JavaxVecmathVector3f.add(paramVector3f2);
    jdField_field_1720_of_type_JavaxVecmathVector3f.set(paramVector4f.field_596, paramVector4f.field_597, paramVector4f.field_598);
    jdField_field_1720_of_type_JavaxVecmathVector3f.add(paramVector3f1);
    jdField_field_1721_of_type_JavaxVecmathVector3f.set(paramVector4f.field_596, paramVector4f.field_597, paramVector4f.field_598);
    jdField_field_1721_of_type_JavaxVecmathVector3f.sub(paramVector3f2);
    jdField_field_1718_of_type_JavaxVecmathVector4f.set(jdField_field_1718_of_type_JavaxVecmathVector3f.field_615, jdField_field_1718_of_type_JavaxVecmathVector3f.field_616, jdField_field_1718_of_type_JavaxVecmathVector3f.field_617, paramVector4f.field_599);
    jdField_field_1719_of_type_JavaxVecmathVector4f.set(jdField_field_1719_of_type_JavaxVecmathVector3f.field_615, jdField_field_1719_of_type_JavaxVecmathVector3f.field_616, jdField_field_1719_of_type_JavaxVecmathVector3f.field_617, paramVector4f.field_599);
    jdField_field_1720_of_type_JavaxVecmathVector4f.set(jdField_field_1720_of_type_JavaxVecmathVector3f.field_615, jdField_field_1720_of_type_JavaxVecmathVector3f.field_616, jdField_field_1720_of_type_JavaxVecmathVector3f.field_617, paramVector4f.field_599);
    jdField_field_1721_of_type_JavaxVecmathVector4f.set(jdField_field_1721_of_type_JavaxVecmathVector3f.field_615, jdField_field_1721_of_type_JavaxVecmathVector3f.field_616, jdField_field_1721_of_type_JavaxVecmathVector3f.field_617, paramVector4f.field_599);
    a25(paramFloatBuffer, jdField_field_1718_of_type_JavaxVecmathVector4f);
    a25(paramFloatBuffer, jdField_field_1719_of_type_JavaxVecmathVector4f);
    a25(paramFloatBuffer, jdField_field_1720_of_type_JavaxVecmathVector4f);
    a25(paramFloatBuffer, jdField_field_1721_of_type_JavaxVecmathVector4f);
  }
  
  public static void a24(FloatBuffer paramFloatBuffer, javax.vecmath.Vector3f paramVector3f)
  {
    paramFloatBuffer.put(paramVector3f.field_615);
    paramFloatBuffer.put(paramVector3f.field_616);
    paramFloatBuffer.put(paramVector3f.field_617);
  }
  
  public static void a25(FloatBuffer paramFloatBuffer, Vector4f paramVector4f)
  {
    paramFloatBuffer.put(paramVector4f.field_596);
    paramFloatBuffer.put(paramVector4f.field_597);
    paramFloatBuffer.put(paramVector4f.field_598);
    paramFloatBuffer.put(paramVector4f.field_599);
  }
  
  private static void a26(FloatBuffer paramFloatBuffer, Vector4f paramVector4f, int paramInt)
  {
    paramFloatBuffer.put(paramInt, paramVector4f.field_596);
    paramFloatBuffer.put(paramInt + 1, paramVector4f.field_597);
    paramFloatBuffer.put(paramInt + 2, paramVector4f.field_598);
    paramFloatBuffer.put(paramInt + 3, paramVector4f.field_599);
  }
  
  public static void a27(FloatBuffer paramFloatBuffer, float paramFloat, javax.vecmath.Vector3f[] paramArrayOfVector3f, int paramInt)
  {
    jdField_field_1720_of_type_JavaxVecmathVector4f.set(paramArrayOfVector3f[2].field_615, paramArrayOfVector3f[2].field_616, paramArrayOfVector3f[2].field_617, paramFloat);
    jdField_field_1721_of_type_JavaxVecmathVector4f.set(paramArrayOfVector3f[3].field_615, paramArrayOfVector3f[3].field_616, paramArrayOfVector3f[3].field_617, paramFloat);
    if (paramInt < 0)
    {
      a25(paramFloatBuffer, jdField_field_1720_of_type_JavaxVecmathVector4f);
      a25(paramFloatBuffer, jdField_field_1721_of_type_JavaxVecmathVector4f);
      return;
    }
    a26(paramFloatBuffer, jdField_field_1720_of_type_JavaxVecmathVector4f, paramInt);
    a26(paramFloatBuffer, jdField_field_1721_of_type_JavaxVecmathVector4f, paramInt + 4);
  }
  
  public static void a28(FloatBuffer paramFloatBuffer, float paramFloat, javax.vecmath.Vector3f[] paramArrayOfVector3f)
  {
    jdField_field_1718_of_type_JavaxVecmathVector4f.set(paramArrayOfVector3f[0].field_615, paramArrayOfVector3f[0].field_616, paramArrayOfVector3f[0].field_617, paramFloat);
    jdField_field_1719_of_type_JavaxVecmathVector4f.set(paramArrayOfVector3f[1].field_615, paramArrayOfVector3f[1].field_616, paramArrayOfVector3f[1].field_617, paramFloat);
    jdField_field_1720_of_type_JavaxVecmathVector4f.set(paramArrayOfVector3f[2].field_615, paramArrayOfVector3f[2].field_616, paramArrayOfVector3f[2].field_617, paramFloat);
    jdField_field_1721_of_type_JavaxVecmathVector4f.set(paramArrayOfVector3f[3].field_615, paramArrayOfVector3f[3].field_616, paramArrayOfVector3f[3].field_617, paramFloat);
    a25(paramFloatBuffer, jdField_field_1718_of_type_JavaxVecmathVector4f);
    a25(paramFloatBuffer, jdField_field_1719_of_type_JavaxVecmathVector4f);
    a25(paramFloatBuffer, jdField_field_1720_of_type_JavaxVecmathVector4f);
    a25(paramFloatBuffer, jdField_field_1721_of_type_JavaxVecmathVector4f);
  }
  
  public static void a29(float paramFloat)
  {
    jdField_field_1718_of_type_OrgLwjglUtilVectorVector3f.set(0.0F, 0.0F, 1.0F);
    class_969.field_1259.rotate(paramFloat, jdField_field_1718_of_type_OrgLwjglUtilVectorVector3f);
    GL11.glRotatef(paramFloat, 0.0F, 0.0F, 1.0F);
  }
  
  public static void b5(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    jdField_field_1718_of_type_OrgLwjglUtilVectorVector3f.set(paramFloat1, paramFloat2, paramFloat3);
    class_969.field_1259.scale(jdField_field_1718_of_type_OrgLwjglUtilVectorVector3f);
    GL11.glScalef(paramFloat1, paramFloat2, paramFloat3);
  }
  
  public static void a30(javax.vecmath.Vector3f paramVector3f, Transform paramTransform)
  {
    paramTransform.basis.setColumn(2, paramVector3f);
  }
  
  public static void b6(javax.vecmath.Vector3f paramVector3f, Transform paramTransform)
  {
    paramVector3f.negate();
    c3(paramVector3f, paramTransform);
  }
  
  public static void c3(javax.vecmath.Vector3f paramVector3f, Transform paramTransform)
  {
    paramTransform.basis.setColumn(0, paramVector3f);
  }
  
  public static void d2(javax.vecmath.Vector3f paramVector3f, Transform paramTransform)
  {
    paramTransform.basis.setColumn(1, paramVector3f);
  }
  
  public static void a31(javax.vecmath.Vector3f paramVector3f)
  {
    c4(paramVector3f.field_615, paramVector3f.field_616, paramVector3f.field_617);
  }
  
  public static void c4(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    jdField_field_1718_of_type_OrgLwjglUtilVectorVector3f.set(paramFloat1, paramFloat2, paramFloat3);
    class_969.field_1259.translate(jdField_field_1718_of_type_OrgLwjglUtilVectorVector3f);
    GL11.glTranslatef(paramFloat1, paramFloat2, paramFloat3);
  }
  
  public static void a32(class_1377 paramclass_1377, String paramString, Color4f paramColor4f)
  {
    int i;
    if ((i = paramclass_1377.a3(paramString)) == -1)
    {
      a17(paramclass_1377, paramString);
      return;
    }
    GL20.glUniform4f(i, paramColor4f.field_596, paramColor4f.field_597, paramColor4f.field_598, paramColor4f.field_599);
  }
  
  public static void a33(class_1377 paramclass_1377, String paramString, float paramFloat)
  {
    int i;
    if ((i = paramclass_1377.a3(paramString)) == -1)
    {
      a17(paramclass_1377, paramString);
      return;
    }
    GL20.glUniform1f(i, paramFloat);
  }
  
  public static void a34(class_1377 paramclass_1377, String paramString, FloatBuffer paramFloatBuffer)
  {
    int i;
    if ((i = paramclass_1377.a3(paramString)) == -1)
    {
      a17(paramclass_1377, paramString);
      return;
    }
    GL20.glUniform1(i, paramFloatBuffer);
  }
  
  public static void b7(class_1377 paramclass_1377, String paramString, FloatBuffer paramFloatBuffer)
  {
    int i;
    if ((i = paramclass_1377.a3(paramString)) == -1)
    {
      a17(paramclass_1377, paramString);
      return;
    }
    GL20.glUniform3(i, paramFloatBuffer);
  }
  
  public static void a35(class_1377 paramclass_1377, String paramString, int paramInt)
  {
    int i;
    if ((i = paramclass_1377.a3(paramString)) == -1)
    {
      a17(paramclass_1377, paramString);
      return;
    }
    GL20.glUniform1i(i, paramInt);
  }
  
  public static void c5(class_1377 paramclass_1377, String paramString, FloatBuffer paramFloatBuffer)
  {
    int i = paramclass_1377.a3(paramString);
    if ((!jdField_field_1718_of_type_Boolean) && (paramFloatBuffer.remaining() != 16)) {
      throw new AssertionError();
    }
    if (i == -1)
    {
      a17(paramclass_1377, paramString);
      return;
    }
    GL20.glUniformMatrix4(i, false, paramFloatBuffer);
  }
  
  public static void a36(class_1377 paramclass_1377, String paramString, Transform[] paramArrayOfTransform)
  {
    FloatBuffer localFloatBuffer;
    if (((localFloatBuffer = paramclass_1377.jdField_field_1578_of_type_JavaNioFloatBuffer) == null) || (localFloatBuffer.capacity() < paramArrayOfTransform.length << 4))
    {
      localFloatBuffer = BufferUtils.createFloatBuffer(paramArrayOfTransform.length << 4);
      paramclass_1377.jdField_field_1578_of_type_JavaNioFloatBuffer = localFloatBuffer;
    }
    localFloatBuffer.limit(paramArrayOfTransform.length << 4);
    localFloatBuffer.rewind();
    int i = (paramArrayOfTransform = paramArrayOfTransform).length;
    for (int j = 0; j < i; j++)
    {
      paramArrayOfTransform[j].getOpenGLMatrix(jdField_field_1718_of_type_ArrayOfFloat);
      localFloatBuffer.put(jdField_field_1718_of_type_ArrayOfFloat);
    }
    localFloatBuffer.flip();
    if ((paramArrayOfTransform = paramclass_1377.a3(paramString)) == -1)
    {
      a17(paramclass_1377, paramString);
      return;
    }
    GL20.glUniformMatrix4(paramArrayOfTransform, false, localFloatBuffer);
  }
  
  public static void a37(class_1377 paramclass_1377, String paramString, float paramFloat1, float paramFloat2)
  {
    int i;
    if ((i = paramclass_1377.a3(paramString)) == -1)
    {
      a17(paramclass_1377, paramString);
      return;
    }
    GL20.glUniform2f(i, paramFloat1, paramFloat2);
  }
  
  public static void a38(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    if ((jdField_field_1722_of_type_JavaxVecmathVector4f.field_596 != paramFloat1) || (jdField_field_1722_of_type_JavaxVecmathVector4f.field_597 != paramFloat2) || (jdField_field_1722_of_type_JavaxVecmathVector4f.field_598 != paramFloat3) || (jdField_field_1722_of_type_JavaxVecmathVector4f.field_599 != paramFloat4))
    {
      jdField_field_1722_of_type_JavaxVecmathVector4f.set(paramFloat1, paramFloat2, paramFloat3, paramFloat4);
      GL11.glColor4f(paramFloat1, paramFloat2, paramFloat3, paramFloat4);
    }
  }
  
  public static void a39(class_1377 paramclass_1377, String paramString, float paramFloat1, float paramFloat2, float paramFloat3)
  {
    int i;
    if ((i = paramclass_1377.a3(paramString)) == -1)
    {
      a17(paramclass_1377, paramString);
      return;
    }
    GL20.glUniform3f(i, paramFloat1, paramFloat2, paramFloat3);
  }
  
  public static void a40(class_1377 paramclass_1377, String paramString, javax.vecmath.Vector3f paramVector3f)
  {
    int i;
    if ((i = paramclass_1377.a3(paramString)) == -1)
    {
      a17(paramclass_1377, paramString);
      return;
    }
    GL20.glUniform3f(i, paramVector3f.field_615, paramVector3f.field_616, paramVector3f.field_617);
  }
  
  public static void a41(class_1377 paramclass_1377, String paramString, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    int i;
    if ((i = paramclass_1377.a3(paramString)) == -1)
    {
      a17(paramclass_1377, paramString);
      return;
    }
    GL20.glUniform4f(i, paramFloat1, paramFloat2, paramFloat3, paramFloat4);
  }
  
  public static void a42(class_1377 paramclass_1377, String paramString, Vector4f paramVector4f)
  {
    int i;
    if ((i = paramclass_1377.a3(paramString)) == -1)
    {
      a17(paramclass_1377, paramString);
      return;
    }
    GL20.glUniform4f(i, paramVector4f.field_596, paramVector4f.field_597, paramVector4f.field_598, paramVector4f.field_599);
  }
  
  public static void a43(String paramString1, String paramString2, int paramInt1, int paramInt2)
  {
    ByteBuffer localByteBuffer = a5(paramInt1 * paramInt2 << 2, 0);
    GL11.glReadPixels(0, 0, paramInt1, paramInt2, 6408, 5121, localByteBuffer);
    paramString1 = new File(paramString1 + "." + paramString2);
    paramString2 = paramString2.toUpperCase();
    BufferedImage localBufferedImage = new BufferedImage(paramInt1, paramInt2, 2);
    for (int i = 0; i < paramInt1; i++) {
      for (int j = 0; j < paramInt2; j++)
      {
        int k = i + paramInt1 * j << 2;
        int m = localByteBuffer.get(k) & 0xFF;
        int n = localByteBuffer.get(k + 1) & 0xFF;
        int i1 = localByteBuffer.get(k + 2) & 0xFF;
        k = localByteBuffer.get(k + 3) & 0xFF;
        localBufferedImage.setRGB(i, paramInt2 - (j + 1), k << 24 | m << 16 | n << 8 | i1);
      }
    }
    try
    {
      ImageIO.write(localBufferedImage, paramString2, paramString1);
      return;
    }
    catch (IOException localIOException)
    {
      localIOException;
    }
  }
  
  static
  {
    BufferUtils.createFloatBuffer(3);
    jdField_field_1718_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(16);
    jdField_field_1719_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(16);
    BufferUtils.createFloatBuffer(16);
    BufferUtils.createFloatBuffer(16);
    BufferUtils.createFloatBuffer(16);
    new Matrix3f();
    new javax.vecmath.Vector3f();
    new javax.vecmath.Vector3f();
    new javax.vecmath.Vector3f();
    new Transform();
    BufferUtils.createIntBuffer(1);
    jdField_field_1718_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
    jdField_field_1718_of_type_ArrayOfJavaNioByteBuffer = new ByteBuffer[6];
    BufferUtils.createFloatBuffer(16);
    jdField_field_1718_of_type_JavaxVecmathVector3f = new javax.vecmath.Vector3f();
    jdField_field_1719_of_type_JavaxVecmathVector3f = new javax.vecmath.Vector3f();
    jdField_field_1720_of_type_JavaxVecmathVector3f = new javax.vecmath.Vector3f();
    jdField_field_1721_of_type_JavaxVecmathVector3f = new javax.vecmath.Vector3f();
    jdField_field_1718_of_type_JavaxVecmathVector4f = new Vector4f();
    jdField_field_1719_of_type_JavaxVecmathVector4f = new Vector4f();
    jdField_field_1720_of_type_JavaxVecmathVector4f = new Vector4f();
    jdField_field_1721_of_type_JavaxVecmathVector4f = new Vector4f();
    new javax.vecmath.Vector3f();
    new javax.vecmath.Vector3f();
    new javax.vecmath.Vector3f();
    new javax.vecmath.Vector3f();
    jdField_field_1722_of_type_JavaxVecmathVector3f = new javax.vecmath.Vector3f();
    field_1723 = new javax.vecmath.Vector3f();
    new Vector4f();
    new Vector4f();
    field_1724 = new javax.vecmath.Vector3f();
    field_1725 = new javax.vecmath.Vector3f();
    field_1726 = new javax.vecmath.Vector3f();
    field_1727 = new javax.vecmath.Vector3f();
    field_1728 = new javax.vecmath.Vector3f();
    field_1729 = new javax.vecmath.Vector3f();
    jdField_field_1718_of_type_OrgLwjglUtilVectorVector3f = new org.lwjgl.util.vector.Vector3f(0.0F, 0.0F, 0.0F);
    jdField_field_1718_of_type_ArrayOfJavaNioFloatBuffer = new FloatBuffer[64];
    jdField_field_1718_of_type_Int = 0;
    jdField_field_1719_of_type_ArrayOfJavaNioFloatBuffer = new FloatBuffer[64];
    jdField_field_1719_of_type_Int = 0;
    new Transform();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.graphicsengine.core.GlUtil
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */