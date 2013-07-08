import java.io.PrintStream;
import java.nio.BufferOverflowException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Collection;
import java.util.Iterator;
import org.lwjgl.BufferUtils;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.vector.Matrix4f;
import org.schema.schine.graphicsengine.camera.Camera;
import org.schema.schine.graphicsengine.core.GlUtil;

public class class_1380
  extends class_1392
{
  private int jdField_field_90_of_type_Int = 1;
  private int jdField_field_92_of_type_Int = 0;
  private boolean jdField_field_89_of_type_Boolean = false;
  private boolean jdField_field_90_of_type_Boolean = true;
  private boolean jdField_field_92_of_type_Boolean = true;
  private javax.vecmath.Vector4f jdField_field_89_of_type_JavaxVecmathVector4f;
  private int jdField_field_93_of_type_Int;
  private int jdField_field_94_of_type_Int;
  private boolean jdField_field_93_of_type_Boolean = false;
  private boolean jdField_field_95_of_type_Boolean = false;
  private IntBuffer jdField_field_89_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
  private IntBuffer jdField_field_90_of_type_JavaNioIntBuffer;
  private IntBuffer jdField_field_92_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
  private boolean jdField_field_96_of_type_Boolean = true;
  private boolean jdField_field_97_of_type_Boolean = true;
  private int jdField_field_95_of_type_Int;
  private int jdField_field_96_of_type_Int;
  private static FloatBuffer jdField_field_90_of_type_JavaNioFloatBuffer;
  private static FloatBuffer jdField_field_92_of_type_JavaNioFloatBuffer;
  private static FloatBuffer jdField_field_93_of_type_JavaNioFloatBuffer;
  private static int jdField_field_97_of_type_Int;
  private static int jdField_field_100_of_type_Int;
  private static int field_216;
  private static int field_275;
  private static FloatBuffer jdField_field_94_of_type_JavaNioFloatBuffer;
  public int field_89;
  private static IntBuffer jdField_field_93_of_type_JavaNioIntBuffer;
  private static FloatBuffer jdField_field_95_of_type_JavaNioFloatBuffer;
  private static FloatBuffer jdField_field_96_of_type_JavaNioFloatBuffer;
  private static FloatBuffer jdField_field_97_of_type_JavaNioFloatBuffer;
  
  public static void a148(class_1380 paramclass_1380)
  {
    if (paramclass_1380.jdField_field_89_of_type_JavaxVecmathVector4f != null) {
      GlUtil.a38(paramclass_1380.jdField_field_89_of_type_JavaxVecmathVector4f.field_596, paramclass_1380.jdField_field_89_of_type_JavaxVecmathVector4f.field_597, paramclass_1380.jdField_field_89_of_type_JavaxVecmathVector4f.field_598, paramclass_1380.jdField_field_89_of_type_JavaxVecmathVector4f.field_599);
    } else {
      GlUtil.a38(1.0F, 1.0F, 1.0F, 1.0F);
    }
    if (field_275 != paramclass_1380.jdField_field_92_of_type_Int)
    {
      GL15.glBindBuffer(34962, paramclass_1380.jdField_field_90_of_type_JavaNioIntBuffer.get(paramclass_1380.jdField_field_92_of_type_Int));
      GL11.glTexCoordPointer(2, 5126, 0, 0L);
      field_275 = paramclass_1380.jdField_field_92_of_type_Int;
    }
    GL11.glDrawArrays(7, 0, 4);
  }
  
  public static void b30(class_1380 paramclass_1380)
  {
    field_275 = -1;
    if (paramclass_1380.jdField_field_97_of_type_Boolean) {
      paramclass_1380.c();
    }
    if (class_949.field_1209.b1()) {
      n1();
    }
    GL11.glDisable(2896);
    if (paramclass_1380.jdField_field_90_of_type_Boolean) {
      GL11.glEnable(2929);
    } else {
      GL11.glDisable(2929);
    }
    if (paramclass_1380.jdField_field_92_of_type_Boolean)
    {
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
    }
    else
    {
      GL11.glDisable(3042);
    }
    paramclass_1380.jdField_field_89_of_type_Class_1393.a1().a();
    if ((paramclass_1380.jdField_field_96_of_type_Boolean) && (class_949.field_1231.b1()))
    {
      GL11.glEnableClientState(32884);
      GL11.glEnableClientState(32888);
      GL11.glEnableClientState(32885);
      GL15.glBindBuffer(34962, paramclass_1380.jdField_field_89_of_type_JavaNioIntBuffer.get(0));
      GL11.glVertexPointer(3, 5126, 0, 0L);
      GL13.glActiveTexture(33984);
      if (paramclass_1380.jdField_field_89_of_type_JavaxVecmathVector4f != null) {
        GlUtil.a38(paramclass_1380.jdField_field_89_of_type_JavaxVecmathVector4f.field_596, paramclass_1380.jdField_field_89_of_type_JavaxVecmathVector4f.field_597, paramclass_1380.jdField_field_89_of_type_JavaxVecmathVector4f.field_598, paramclass_1380.jdField_field_89_of_type_JavaxVecmathVector4f.field_599);
      } else {
        GlUtil.a38(1.0F, 1.0F, 1.0F, 1.0F);
      }
      GL15.glBindBuffer(34962, paramclass_1380.jdField_field_90_of_type_JavaNioIntBuffer.get(paramclass_1380.jdField_field_92_of_type_Int));
      GL11.glTexCoordPointer(2, 5126, 0, 0L);
      GL15.glBindBuffer(34962, paramclass_1380.jdField_field_92_of_type_JavaNioIntBuffer.get(0));
      GL11.glNormalPointer(5126, 0, 0L);
      return;
    }
    if (!jdField_field_100_of_type_Boolean) {
      throw new AssertionError();
    }
  }
  
  public static void c14(class_1380 paramclass_1380)
  {
    GL11.glDisableClientState(32884);
    GL11.glDisableClientState(32888);
    GL11.glDisableClientState(32885);
    paramclass_1380.jdField_field_89_of_type_Class_1393.a1();
    class_1395.b();
    GlUtil.a38(1.0F, 1.0F, 1.0F, 1.0F);
    GL11.glDisable(3042);
    GL11.glEnable(2929);
    GL11.glDisable(2903);
    GL11.glEnable(2896);
    if (class_949.field_1209.b1()) {
      n1();
    }
  }
  
  public static void d9(class_1380 paramVarArgs)
  {
    if (paramVarArgs.jdField_field_97_of_type_Boolean) {
      paramVarArgs.c();
    }
    if (paramVarArgs.h2()) {
      return;
    }
    if (class_949.field_1209.b1()) {
      n1();
    }
    GlUtil.d1();
    GL11.glDisable(2896);
    if (paramVarArgs.jdField_field_90_of_type_Boolean) {
      GL11.glEnable(2929);
    } else {
      GL11.glDisable(2929);
    }
    if (paramVarArgs.jdField_field_92_of_type_Boolean)
    {
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
    }
    else
    {
      GL11.glDisable(3042);
    }
    paramVarArgs.jdField_field_89_of_type_Class_1393.a1().a();
    if ((paramVarArgs.jdField_field_96_of_type_Boolean) && (class_949.field_1231.b1()))
    {
      GL11.glEnableClientState(32884);
      GL11.glEnableClientState(32888);
      GL11.glEnableClientState(32885);
      GL15.glBindBuffer(34962, paramVarArgs.jdField_field_89_of_type_JavaNioIntBuffer.get(0));
      GL11.glVertexPointer(3, 5126, 0, 0L);
      GL13.glActiveTexture(33984);
      if (paramVarArgs.jdField_field_89_of_type_JavaxVecmathVector4f != null) {
        GlUtil.a38(paramVarArgs.jdField_field_89_of_type_JavaxVecmathVector4f.field_596, paramVarArgs.jdField_field_89_of_type_JavaxVecmathVector4f.field_597, paramVarArgs.jdField_field_89_of_type_JavaxVecmathVector4f.field_598, paramVarArgs.jdField_field_89_of_type_JavaxVecmathVector4f.field_599);
      } else {
        GlUtil.a38(1.0F, 1.0F, 1.0F, 1.0F);
      }
      GL15.glBindBuffer(34962, paramVarArgs.jdField_field_90_of_type_JavaNioIntBuffer.get(paramVarArgs.jdField_field_92_of_type_Int));
      GL11.glTexCoordPointer(2, 5126, 0, 0L);
      GL15.glBindBuffer(34962, paramVarArgs.jdField_field_92_of_type_JavaNioIntBuffer.get(0));
      GL11.glNormalPointer(5126, 0, 0L);
      GlUtil.d1();
      if (paramVarArgs.jdField_field_95_of_type_Boolean)
      {
        GlUtil.a31(paramVarArgs.a83());
        jdField_field_94_of_type_JavaNioFloatBuffer.put(0, paramVarArgs.field_89.field_615);
        jdField_field_94_of_type_JavaNioFloatBuffer.put(5, -paramVarArgs.field_89.field_616);
        jdField_field_94_of_type_JavaNioFloatBuffer.put(10, paramVarArgs.field_89.field_617);
        jdField_field_94_of_type_JavaNioFloatBuffer.put(12, class_969.jdField_field_1259_of_type_OrgLwjglUtilVectorMatrix4f.m30);
        jdField_field_94_of_type_JavaNioFloatBuffer.put(13, class_969.jdField_field_1259_of_type_OrgLwjglUtilVectorMatrix4f.m31);
        jdField_field_94_of_type_JavaNioFloatBuffer.put(14, class_969.jdField_field_1259_of_type_OrgLwjglUtilVectorMatrix4f.m32);
        jdField_field_94_of_type_JavaNioFloatBuffer.rewind();
        GlUtil.a10(jdField_field_94_of_type_JavaNioFloatBuffer);
      }
      GL11.glDrawArrays(7, 0, 4);
      GlUtil.c2();
      GL11.glDisableClientState(32884);
      GL11.glDisableClientState(32888);
      GL11.glDisableClientState(32885);
    }
    else
    {
      float f1 = paramVarArgs.jdField_field_89_of_type_Class_1393.a1().field_1591 * paramVarArgs.field_89.field_615;
      float f2 = paramVarArgs.jdField_field_89_of_type_Class_1393.a1().field_1590 * paramVarArgs.field_89.field_616;
      GL11.glBegin(7);
      if (paramVarArgs.jdField_field_89_of_type_Boolean)
      {
        GL11.glTexCoord2f(0.0F, 0.0F);
        GL11.glVertex3f(-f1 / 2.0F, -f2 / 2.0F, 0.0F);
        GL11.glTexCoord2f(0.0F, 1.0F);
        GL11.glVertex3f(-f1 / 2.0F, f2 / 2.0F, 0.0F);
        GL11.glTexCoord2f(1.0F, 1.0F);
        GL11.glVertex3f(f1 / 2.0F, f2 / 2.0F, 0.0F);
        GL11.glTexCoord2f(1.0F, 0.0F);
        GL11.glVertex3f(f1 / 2.0F, -f2 / 2.0F, 0.0F);
      }
      else
      {
        GL11.glTexCoord2f(0.0F, 0.0F);
        GL11.glVertex3f(0.0F, 0.0F, 0.0F);
        GL11.glTexCoord2f(0.0F, 1.0F);
        GL11.glVertex3f(0.0F, f2, 0.0F);
        GL11.glTexCoord2f(1.0F, 1.0F);
        GL11.glVertex3f(f1, f2, 0.0F);
        GL11.glTexCoord2f(1.0F, 0.0F);
        GL11.glVertex3f(f1, 0.0F, 0.0F);
      }
      GL11.glEnd();
    }
    paramVarArgs.jdField_field_89_of_type_Class_1393.a1();
    class_1395.b();
    GlUtil.c2();
    GlUtil.a38(1.0F, 1.0F, 1.0F, 1.0F);
    GL11.glDisable(3042);
    GL11.glEnable(2929);
    GL11.glDisable(2903);
    GL11.glEnable(2896);
    if (class_949.field_1209.b1()) {
      n1();
    }
  }
  
  public static void a170(class_1380 paramclass_1380, class_953[] paramArrayOfclass_953, Camera paramCamera)
  {
    if (paramclass_1380.jdField_field_97_of_type_Boolean) {
      paramclass_1380.c();
    }
    if (paramclass_1380.h2()) {
      return;
    }
    int i = (paramArrayOfclass_953.length > 0) && ((paramArrayOfclass_953[0] instanceof class_955)) ? 1 : 0;
    int j = (paramArrayOfclass_953.length > 0) && ((paramArrayOfclass_953[0] instanceof class_1388)) ? 1 : 0;
    if (class_949.field_1209.b1()) {
      n1();
    }
    GlUtil.d1();
    class_1388 localclass_1388 = null;
    GL11.glDisable(2896);
    if (paramclass_1380.jdField_field_90_of_type_Boolean) {
      GL11.glEnable(2929);
    } else {
      GL11.glDisable(2929);
    }
    if (paramclass_1380.jdField_field_92_of_type_Boolean)
    {
      GL11.glEnable(3042);
      if (paramclass_1380.jdField_field_89_of_type_Int == 0) {
        GL11.glBlendFunc(770, 771);
      } else {
        GL11.glBlendFunc(770, 1);
      }
    }
    else
    {
      GL11.glDisable(3042);
    }
    float f1 = 0.0F;
    float f2 = 0.0F;
    if (j != 0)
    {
      f1 = Mouse.getX();
      f2 = Mouse.getY();
    }
    paramclass_1380.jdField_field_89_of_type_Class_1393.a1().a();
    if ((paramclass_1380.jdField_field_96_of_type_Boolean) && (class_949.field_1231.b1()))
    {
      class_1376.field_1569.c();
      GlUtil.a41(class_1376.field_1569, "selectionColor", 1.0F, 1.0F, 1.0F, 1.0F);
      GlUtil.a35(class_1376.field_1569, "mainTexA", 0);
      GL11.glEnableClientState(32884);
      GL11.glEnableClientState(32888);
      GL11.glEnableClientState(32885);
      GL15.glBindBuffer(34962, paramclass_1380.jdField_field_89_of_type_JavaNioIntBuffer.get(0));
      GL11.glVertexPointer(3, 5126, 0, 0L);
      GL13.glActiveTexture(33984);
      if (paramclass_1380.jdField_field_89_of_type_JavaxVecmathVector4f != null) {
        GlUtil.a38(paramclass_1380.jdField_field_89_of_type_JavaxVecmathVector4f.field_596, paramclass_1380.jdField_field_89_of_type_JavaxVecmathVector4f.field_597, paramclass_1380.jdField_field_89_of_type_JavaxVecmathVector4f.field_598, paramclass_1380.jdField_field_89_of_type_JavaxVecmathVector4f.field_599);
      } else {
        GlUtil.a38(1.0F, 1.0F, 1.0F, 1.0F);
      }
      GL15.glBindBuffer(34962, paramclass_1380.jdField_field_92_of_type_JavaNioIntBuffer.get(0));
      GL11.glNormalPointer(5126, 0, 0L);
      GlUtil.d1();
      int k = -1;
      javax.vecmath.Vector3f localVector3f1 = new javax.vecmath.Vector3f(paramCamera.f5());
      javax.vecmath.Vector3f localVector3f2 = new javax.vecmath.Vector3f(paramCamera.e7());
      localVector3f1.scale(0.3F);
      localVector3f2.scale(0.3F);
      long l = System.currentTimeMillis();
      for (int m = 0; m < paramArrayOfclass_953.length; m++)
      {
        class_953 localclass_953 = paramArrayOfclass_953[m];
        if (paramCamera.a187(localclass_953.a83()))
        {
          paramclass_1380.b13(localclass_953.a105(paramclass_1380));
          if (paramclass_1380.jdField_field_92_of_type_Int != k)
          {
            GL15.glBindBuffer(34962, paramclass_1380.jdField_field_90_of_type_JavaNioIntBuffer.get(paramclass_1380.jdField_field_92_of_type_Int));
            GL11.glTexCoordPointer(2, 5126, 0, 0L);
            k = paramclass_1380.jdField_field_92_of_type_Int;
          }
          if (i != 0)
          {
            class_955 localclass_955 = (class_955)localclass_953;
            GlUtil.a41(class_1376.field_1569, "selectionColor", localclass_955.a63().field_596, localclass_955.a63().field_597, localclass_955.a63().field_598, localclass_955.a63().field_599);
            GlUtil.a38(localclass_955.a63().field_596, localclass_955.a63().field_597, localclass_955.a63().field_598, localclass_955.a63().field_599);
          }
          GlUtil.d1();
          if (!paramclass_1380.jdField_field_89_of_type_Boolean) {
            GlUtil.c4(localclass_953.a83().field_615 + localVector3f1.field_615 + localVector3f2.field_615, localclass_953.a83().field_616 + localVector3f1.field_616 + localVector3f2.field_616, localclass_953.a83().field_617 + localVector3f1.field_617 + localVector3f2.field_617);
          } else {
            GlUtil.c4(localclass_953.a83().field_615, localclass_953.a83().field_616, localclass_953.a83().field_617);
          }
          float f5 = localclass_953.a106(l);
          jdField_field_94_of_type_JavaNioFloatBuffer.put(0, f5);
          jdField_field_94_of_type_JavaNioFloatBuffer.put(5, -f5);
          jdField_field_94_of_type_JavaNioFloatBuffer.put(10, f5);
          jdField_field_94_of_type_JavaNioFloatBuffer.put(12, class_969.jdField_field_1259_of_type_OrgLwjglUtilVectorMatrix4f.m30);
          jdField_field_94_of_type_JavaNioFloatBuffer.put(13, class_969.jdField_field_1259_of_type_OrgLwjglUtilVectorMatrix4f.m31);
          jdField_field_94_of_type_JavaNioFloatBuffer.put(14, class_969.jdField_field_1259_of_type_OrgLwjglUtilVectorMatrix4f.m32);
          jdField_field_94_of_type_JavaNioFloatBuffer.rewind();
          GlUtil.a10(jdField_field_94_of_type_JavaNioFloatBuffer);
          if ((j != 0) && (((class_1388)localclass_953).b()))
          {
            org.lwjgl.util.vector.Vector4f localVector4f = new org.lwjgl.util.vector.Vector4f();
            Matrix4f.transform(class_969.jdField_field_1259_of_type_OrgLwjglUtilVectorMatrix4f, new org.lwjgl.util.vector.Vector4f(0.0F, 0.0F, 0.0F, 1.0F), localVector4f);
            Matrix4f.transform(class_969.field_1260, new org.lwjgl.util.vector.Vector4f(localVector4f), localVector4f);
            float f6 = localVector4f.field_142 / localVector4f.field_143 * 0.5F + 0.5F;
            float f9 = f6;
            float f8 = f2;
            float f7 = f1;
            class_1380 localclass_1380 = paramclass_1380;
            Matrix4f localMatrix4f1 = class_969.jdField_field_1259_of_type_OrgLwjglUtilVectorMatrix4f;
            Matrix4f localMatrix4f2 = class_969.field_1260;
            jdField_field_93_of_type_JavaNioIntBuffer.rewind();
            jdField_field_93_of_type_JavaNioIntBuffer.put(class_969.jdField_field_1259_of_type_JavaNioIntBuffer);
            class_969.jdField_field_1259_of_type_JavaNioIntBuffer.rewind();
            jdField_field_93_of_type_JavaNioIntBuffer.rewind();
            jdField_field_93_of_type_JavaNioIntBuffer.put(0, 0);
            jdField_field_93_of_type_JavaNioIntBuffer.put(1, 0);
            jdField_field_93_of_type_JavaNioIntBuffer.put(2, jdField_field_93_of_type_JavaNioIntBuffer.get(2));
            jdField_field_93_of_type_JavaNioIntBuffer.put(3, jdField_field_93_of_type_JavaNioIntBuffer.get(3));
            if (jdField_field_96_of_type_JavaNioFloatBuffer == null)
            {
              jdField_field_95_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(16);
              jdField_field_96_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(16);
            }
            jdField_field_96_of_type_JavaNioFloatBuffer.rewind();
            localMatrix4f1.store(jdField_field_96_of_type_JavaNioFloatBuffer);
            jdField_field_96_of_type_JavaNioFloatBuffer.rewind();
            jdField_field_95_of_type_JavaNioFloatBuffer.rewind();
            localMatrix4f2.store(jdField_field_95_of_type_JavaNioFloatBuffer);
            jdField_field_95_of_type_JavaNioFloatBuffer.rewind();
            GLU.gluUnProject(f7, f8, f9, jdField_field_96_of_type_JavaNioFloatBuffer, jdField_field_95_of_type_JavaNioFloatBuffer, jdField_field_93_of_type_JavaNioIntBuffer, jdField_field_97_of_type_JavaNioFloatBuffer);
            f7 = jdField_field_97_of_type_JavaNioFloatBuffer.get(0);
            f8 = jdField_field_97_of_type_JavaNioFloatBuffer.get(1);
            jdField_field_97_of_type_JavaNioFloatBuffer.get(2);
            f7 += localclass_1380.jdField_field_93_of_type_Int / 2;
            f8 += localclass_1380.jdField_field_94_of_type_Int / 2;
            int i1 = (f7 < localclass_1380.jdField_field_93_of_type_Int) && (f7 > 0.0F) ? 1 : 0;
            int n = (f8 < localclass_1380.jdField_field_94_of_type_Int) && (f8 > 0.0F) ? 1 : 0;
            if (((i1 != 0) && (n != 0) ? 1 : 0) != 0)
            {
              if (localclass_1388 != null)
              {
                if (localclass_1388.a2() > ((class_1388)localclass_953).a2())
                {
                  localclass_1388.a1();
                  ((class_1388)localclass_953).a(f6);
                  localclass_1388 = (class_1388)localclass_953;
                }
                else
                {
                  ((class_1388)localclass_953).a1();
                }
              }
              else
              {
                ((class_1388)localclass_953).a(f6);
                localclass_1388 = (class_1388)localclass_953;
              }
            }
            else {
              ((class_1388)localclass_953).a1();
            }
          }
          GL11.glDrawArrays(7, 0, 4);
          GlUtil.c2();
        }
      }
      GlUtil.c2();
      class_1377.e();
      GL11.glDisableClientState(32884);
      GL11.glDisableClientState(32888);
      GL11.glDisableClientState(32885);
    }
    else
    {
      float f3 = paramclass_1380.jdField_field_89_of_type_Class_1393.a1().field_1591 * paramclass_1380.field_89.field_615;
      float f4 = paramclass_1380.jdField_field_89_of_type_Class_1393.a1().field_1590 * paramclass_1380.field_89.field_616;
      GL11.glBegin(7);
      if (paramclass_1380.jdField_field_89_of_type_Boolean)
      {
        GL11.glTexCoord2f(0.0F, 0.0F);
        GL11.glVertex3f(-f3 / 2.0F, -f4 / 2.0F, 0.0F);
        GL11.glTexCoord2f(0.0F, 1.0F);
        GL11.glVertex3f(-f3 / 2.0F, f4 / 2.0F, 0.0F);
        GL11.glTexCoord2f(1.0F, 1.0F);
        GL11.glVertex3f(f3 / 2.0F, f4 / 2.0F, 0.0F);
        GL11.glTexCoord2f(1.0F, 0.0F);
        GL11.glVertex3f(f3 / 2.0F, -f4 / 2.0F, 0.0F);
      }
      else
      {
        GL11.glTexCoord2f(0.0F, 0.0F);
        GL11.glVertex3f(0.0F, 0.0F, 0.0F);
        GL11.glTexCoord2f(0.0F, 1.0F);
        GL11.glVertex3f(0.0F, f4, 0.0F);
        GL11.glTexCoord2f(1.0F, 1.0F);
        GL11.glVertex3f(f3, f4, 0.0F);
        GL11.glTexCoord2f(1.0F, 0.0F);
        GL11.glVertex3f(f3, 0.0F, 0.0F);
      }
      GL11.glEnd();
    }
    paramclass_1380.jdField_field_89_of_type_Class_1393.a1();
    class_1395.b();
    GlUtil.c2();
    GlUtil.a38(1.0F, 1.0F, 1.0F, 1.0F);
    GL11.glDisable(3042);
    GL11.glEnable(2929);
    GL11.glDisable(2903);
    GL11.glEnable(2896);
    if (class_949.field_1209.b1()) {
      n1();
    }
  }
  
  public static void a171(class_1380 paramclass_1380, Collection paramCollection, Camera paramCamera)
  {
    if (paramclass_1380.jdField_field_97_of_type_Boolean) {
      paramclass_1380.c();
    }
    if (paramclass_1380.h2()) {
      return;
    }
    int i = (paramCollection.size() > 0) && ((paramCollection.iterator().next() instanceof class_955)) ? 1 : 0;
    if (class_949.field_1209.b1()) {
      n1();
    }
    GlUtil.d1();
    GL11.glDisable(2896);
    if (paramclass_1380.jdField_field_90_of_type_Boolean) {
      GL11.glEnable(2929);
    } else {
      GL11.glDisable(2929);
    }
    class_955 localclass_955 = null;
    if (paramclass_1380.jdField_field_92_of_type_Boolean)
    {
      GL11.glEnable(3042);
      if (paramclass_1380.jdField_field_89_of_type_Int == 0) {
        GL11.glBlendFunc(770, 771);
      } else {
        GL11.glBlendFunc(770, 1);
      }
    }
    else
    {
      GL11.glDisable(3042);
    }
    paramclass_1380.jdField_field_89_of_type_Class_1393.a1().a();
    if ((paramclass_1380.jdField_field_96_of_type_Boolean) && (class_949.field_1231.b1()))
    {
      class_1376.field_1569.c();
      GlUtil.a41(class_1376.field_1569, "selectionColor", 1.0F, 1.0F, 1.0F, 1.0F);
      GlUtil.a35(class_1376.field_1569, "mainTexA", 0);
      GL11.glEnableClientState(32884);
      GL11.glEnableClientState(32888);
      GL11.glEnableClientState(32885);
      GL15.glBindBuffer(34962, paramclass_1380.jdField_field_89_of_type_JavaNioIntBuffer.get(0));
      GL11.glVertexPointer(3, 5126, 0, 0L);
      GL13.glActiveTexture(33984);
      if (paramclass_1380.jdField_field_89_of_type_JavaxVecmathVector4f != null) {
        GlUtil.a38(paramclass_1380.jdField_field_89_of_type_JavaxVecmathVector4f.field_596, paramclass_1380.jdField_field_89_of_type_JavaxVecmathVector4f.field_597, paramclass_1380.jdField_field_89_of_type_JavaxVecmathVector4f.field_598, paramclass_1380.jdField_field_89_of_type_JavaxVecmathVector4f.field_599);
      } else {
        GlUtil.a38(1.0F, 1.0F, 1.0F, 1.0F);
      }
      GL15.glBindBuffer(34962, paramclass_1380.jdField_field_92_of_type_JavaNioIntBuffer.get(0));
      GL11.glNormalPointer(5126, 0, 0L);
      GlUtil.d1();
      int j = -1;
      javax.vecmath.Vector3f localVector3f1 = new javax.vecmath.Vector3f(paramCamera.f5());
      javax.vecmath.Vector3f localVector3f2 = new javax.vecmath.Vector3f(paramCamera.e7());
      localVector3f1.scale(0.3F);
      localVector3f2.scale(0.3F);
      long l = System.currentTimeMillis();
      paramCollection = paramCollection.iterator();
      while (paramCollection.hasNext())
      {
        class_953 localclass_953 = (class_953)paramCollection.next();
        if ((paramCamera.a187(localclass_953.a83())) && (localclass_953.a105(paramclass_1380) >= 0))
        {
          paramclass_1380.b13(localclass_953.a105(paramclass_1380));
          localclass_955 = null;
          if (paramclass_1380.jdField_field_92_of_type_Int != j)
          {
            GL15.glBindBuffer(34962, paramclass_1380.jdField_field_90_of_type_JavaNioIntBuffer.get(paramclass_1380.jdField_field_92_of_type_Int));
            GL11.glTexCoordPointer(2, 5126, 0, 0L);
            j = paramclass_1380.jdField_field_92_of_type_Int;
          }
          if (i != 0)
          {
            localclass_955 = (class_955)localclass_953;
            GlUtil.a41(class_1376.field_1569, "selectionColor", localclass_955.a63().field_596, localclass_955.a63().field_597, localclass_955.a63().field_598, localclass_955.a63().field_599);
            GlUtil.a38(localclass_955.a63().field_596, localclass_955.a63().field_597, localclass_955.a63().field_598, localclass_955.a63().field_599);
          }
          GlUtil.d1();
          if (!paramclass_1380.jdField_field_89_of_type_Boolean) {
            GlUtil.c4(localclass_953.a83().field_615 + localVector3f1.field_615 + localVector3f2.field_615, localclass_953.a83().field_616 + localVector3f1.field_616 + localVector3f2.field_616, localclass_953.a83().field_617 + localVector3f1.field_617 + localVector3f2.field_617);
          } else {
            GlUtil.c4(localclass_953.a83().field_615, localclass_953.a83().field_616, localclass_953.a83().field_617);
          }
          float f3 = localclass_953.a106(l);
          jdField_field_94_of_type_JavaNioFloatBuffer.put(0, f3);
          jdField_field_94_of_type_JavaNioFloatBuffer.put(5, -f3);
          jdField_field_94_of_type_JavaNioFloatBuffer.put(10, f3);
          jdField_field_94_of_type_JavaNioFloatBuffer.put(12, class_969.jdField_field_1259_of_type_OrgLwjglUtilVectorMatrix4f.m30);
          jdField_field_94_of_type_JavaNioFloatBuffer.put(13, class_969.jdField_field_1259_of_type_OrgLwjglUtilVectorMatrix4f.m31);
          jdField_field_94_of_type_JavaNioFloatBuffer.put(14, class_969.jdField_field_1259_of_type_OrgLwjglUtilVectorMatrix4f.m32);
          jdField_field_94_of_type_JavaNioFloatBuffer.rewind();
          GlUtil.a10(jdField_field_94_of_type_JavaNioFloatBuffer);
          GL11.glDrawArrays(7, 0, 4);
          GlUtil.c2();
        }
      }
      GlUtil.c2();
      class_1377.e();
      GL11.glDisableClientState(32884);
      GL11.glDisableClientState(32888);
      GL11.glDisableClientState(32885);
    }
    else
    {
      paramCollection = null;
      float f1 = paramclass_1380.jdField_field_89_of_type_Class_1393.a1().field_1591 * paramclass_1380.field_89.field_615;
      float f2 = paramclass_1380.jdField_field_89_of_type_Class_1393.a1().field_1590 * paramclass_1380.field_89.field_616;
      GL11.glBegin(7);
      if (paramclass_1380.jdField_field_89_of_type_Boolean)
      {
        GL11.glTexCoord2f(0.0F, 0.0F);
        GL11.glVertex3f(-f1 / 2.0F, -f2 / 2.0F, 0.0F);
        GL11.glTexCoord2f(0.0F, 1.0F);
        GL11.glVertex3f(-f1 / 2.0F, f2 / 2.0F, 0.0F);
        GL11.glTexCoord2f(1.0F, 1.0F);
        GL11.glVertex3f(f1 / 2.0F, f2 / 2.0F, 0.0F);
        GL11.glTexCoord2f(1.0F, 0.0F);
        GL11.glVertex3f(f1 / 2.0F, -f2 / 2.0F, 0.0F);
      }
      else
      {
        GL11.glTexCoord2f(0.0F, 0.0F);
        GL11.glVertex3f(0.0F, 0.0F, 0.0F);
        GL11.glTexCoord2f(0.0F, 1.0F);
        GL11.glVertex3f(0.0F, f2, 0.0F);
        GL11.glTexCoord2f(1.0F, 1.0F);
        GL11.glVertex3f(f1, f2, 0.0F);
        GL11.glTexCoord2f(1.0F, 0.0F);
        GL11.glVertex3f(f1, 0.0F, 0.0F);
      }
      GL11.glEnd();
    }
    paramclass_1380.jdField_field_89_of_type_Class_1393.a1();
    class_1395.b();
    GlUtil.c2();
    GlUtil.a38(1.0F, 1.0F, 1.0F, 1.0F);
    GL11.glDisable(3042);
    GL11.glEnable(2929);
    GL11.glDisable(2903);
    GL11.glEnable(2896);
    if (class_949.field_1209.b1()) {
      n1();
    }
  }
  
  private class_1380(int paramInt1, int paramInt2)
  {
    this.jdField_field_89_of_type_Class_1393 = new class_1393();
    this.jdField_field_93_of_type_Int = paramInt1;
    this.jdField_field_94_of_type_Int = paramInt2;
  }
  
  public class_1380(class_1395 paramclass_1395)
  {
    this(paramclass_1395.field_1591, paramclass_1395.field_1590);
    a153().c2(paramclass_1395);
  }
  
  public final void b()
  {
    
    if (this.jdField_field_93_of_type_Boolean) {
      GL11.glScalef(1.0F, -1.0F, 1.0F);
    }
    r();
    d9(this);
    GlUtil.c2();
  }
  
  public final int a57()
  {
    return this.jdField_field_94_of_type_Int;
  }
  
  public final int b12()
  {
    return this.jdField_field_90_of_type_Int;
  }
  
  public final javax.vecmath.Vector4f a63()
  {
    return this.jdField_field_89_of_type_JavaxVecmathVector4f;
  }
  
  public final int c5()
  {
    return this.jdField_field_93_of_type_Int;
  }
  
  public final void c()
  {
    if (!this.jdField_field_97_of_type_Boolean) {
      return;
    }
    if (this.jdField_field_96_of_type_Boolean)
    {
      int i = 1;
      int j = 1;
      class_34[] arrayOfclass_341 = null;
      if (this.jdField_field_90_of_type_Int > 1)
      {
        if (this.jdField_field_95_of_type_Int != this.jdField_field_96_of_type_Int) {
          System.err.println("UNEVEN PARTS OF SPRITE " + this.jdField_field_95_of_type_Int + " / " + this.jdField_field_96_of_type_Int);
        }
        i = this.jdField_field_95_of_type_Int;
        j = this.jdField_field_96_of_type_Int;
      }
      float f1 = 1.0F / i;
      float f2 = 1.0F / j;
      if (jdField_field_90_of_type_JavaNioFloatBuffer == null)
      {
        jdField_field_90_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(jdField_field_97_of_type_Int);
        jdField_field_92_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(jdField_field_100_of_type_Int);
        jdField_field_93_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(field_216);
      }
      else
      {
        jdField_field_90_of_type_JavaNioFloatBuffer.rewind();
        jdField_field_92_of_type_JavaNioFloatBuffer.rewind();
        jdField_field_93_of_type_JavaNioFloatBuffer.rewind();
      }
      int n;
      try
      {
        arrayOfclass_341 = new class_34[4];
        class_34[] arrayOfclass_342 = new class_34[4];
        class_34 localclass_341 = null;
        if (this.jdField_field_89_of_type_Boolean)
        {
          arrayOfclass_341[3] = new class_34(-this.jdField_field_93_of_type_Int / 2, -this.jdField_field_94_of_type_Int / 2, 0.0F);
          arrayOfclass_342[3] = new class_34(0.0F, 0.0F, 1.0F);
          arrayOfclass_341[2] = new class_34(this.jdField_field_93_of_type_Int / 2, -this.jdField_field_94_of_type_Int / 2, 0.0F);
          arrayOfclass_342[2] = new class_34(0.0F, 0.0F, 1.0F);
          arrayOfclass_341[1] = new class_34(this.jdField_field_93_of_type_Int / 2, this.jdField_field_94_of_type_Int / 2, 0.0F);
          arrayOfclass_342[1] = new class_34(0.0F, 0.0F, 1.0F);
          arrayOfclass_341[0] = new class_34(-this.jdField_field_93_of_type_Int / 2, this.jdField_field_94_of_type_Int / 2, 0.0F);
          arrayOfclass_342[0] = new class_34(0.0F, 0.0F, 1.0F);
        }
        else
        {
          arrayOfclass_341[3] = new class_34(0.0F, 0.0F, 0.0F);
          arrayOfclass_342[3] = new class_34(0.0F, 0.0F, 1.0F);
          arrayOfclass_341[2] = new class_34(this.jdField_field_93_of_type_Int, 0.0F, 0.0F);
          arrayOfclass_342[2] = new class_34(0.0F, 0.0F, 1.0F);
          arrayOfclass_341[1] = new class_34(this.jdField_field_93_of_type_Int, this.jdField_field_94_of_type_Int, 0.0F);
          arrayOfclass_342[1] = new class_34(0.0F, 0.0F, 1.0F);
          arrayOfclass_341[0] = new class_34(0.0F, this.jdField_field_94_of_type_Int, 0.0F);
          arrayOfclass_342[0] = new class_34(0.0F, 0.0F, 1.0F);
        }
        for (n = 0; n < arrayOfclass_341.length; n++)
        {
          localclass_341 = arrayOfclass_341[n];
          class_34 localclass_342 = arrayOfclass_342[n];
          jdField_field_90_of_type_JavaNioFloatBuffer.put(localclass_341.field_450);
          jdField_field_90_of_type_JavaNioFloatBuffer.put(localclass_341.field_451);
          jdField_field_90_of_type_JavaNioFloatBuffer.put(localclass_341.field_452);
          jdField_field_93_of_type_JavaNioFloatBuffer.put(localclass_342.field_450);
          jdField_field_93_of_type_JavaNioFloatBuffer.put(localclass_342.field_451);
          jdField_field_93_of_type_JavaNioFloatBuffer.put(localclass_342.field_452);
        }
      }
      catch (BufferOverflowException localBufferOverflowException)
      {
        localBufferOverflowException;
      }
      jdField_field_90_of_type_JavaNioFloatBuffer.rewind();
      jdField_field_93_of_type_JavaNioFloatBuffer.rewind();
      GL15.glGenBuffers(this.jdField_field_89_of_type_JavaNioIntBuffer);
      GL15.glBindBuffer(34962, this.jdField_field_89_of_type_JavaNioIntBuffer.get(0));
      GL15.glBufferData(34962, jdField_field_90_of_type_JavaNioFloatBuffer, 35044);
      this.jdField_field_90_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(this.jdField_field_90_of_type_Int);
      GL15.glGenBuffers(this.jdField_field_90_of_type_JavaNioIntBuffer);
      int k = 0;
      for (int m = 0; m < j; m++) {
        for (n = 0; n < i; n++)
        {
          float f6 = (m + 1) * f2;
          float f5 = m * f2;
          float f4 = (n + 1) * f1;
          float f3 = n * f1;
          javax.vecmath.Vector3f[] arrayOfVector3f;
          (arrayOfVector3f = new javax.vecmath.Vector3f[4])[3] = new javax.vecmath.Vector3f(f3, f5, 0.0F);
          arrayOfVector3f[2] = new javax.vecmath.Vector3f(f4, f5, 0.0F);
          arrayOfVector3f[1] = new javax.vecmath.Vector3f(f4, f6, 0.0F);
          arrayOfVector3f[0] = new javax.vecmath.Vector3f(f3, f6, 0.0F);
          Object localObject = arrayOfVector3f;
          jdField_field_92_of_type_JavaNioFloatBuffer.rewind();
          for (int i1 = 0; i1 < localObject.length; i1++)
          {
            jdField_field_92_of_type_JavaNioFloatBuffer.put(localObject[i1].field_615);
            jdField_field_92_of_type_JavaNioFloatBuffer.put(localObject[i1].field_616);
          }
          jdField_field_92_of_type_JavaNioFloatBuffer.rewind();
          GL15.glBindBuffer(34962, this.jdField_field_90_of_type_JavaNioIntBuffer.get(k));
          GL15.glBufferData(34962, jdField_field_92_of_type_JavaNioFloatBuffer, 35044);
          k++;
        }
      }
      GL15.glGenBuffers(this.jdField_field_92_of_type_JavaNioIntBuffer);
      GL15.glBindBuffer(34962, this.jdField_field_92_of_type_JavaNioIntBuffer.get(0));
      GL15.glBufferData(34962, jdField_field_93_of_type_JavaNioFloatBuffer, 35044);
    }
    this.jdField_field_97_of_type_Boolean = false;
  }
  
  public final void a29(boolean paramBoolean)
  {
    this.jdField_field_95_of_type_Boolean = paramBoolean;
  }
  
  public final void d()
  {
    this.jdField_field_92_of_type_Boolean = true;
  }
  
  public final void b21(boolean paramBoolean)
  {
    this.jdField_field_90_of_type_Boolean = paramBoolean;
  }
  
  public final void c15(boolean paramBoolean)
  {
    this.jdField_field_93_of_type_Boolean = paramBoolean;
  }
  
  public final void a72(int paramInt)
  {
    this.jdField_field_94_of_type_Int = paramInt;
  }
  
  public final void a167(int paramInt1, int paramInt2)
  {
    this.jdField_field_90_of_type_Int = (paramInt1 * paramInt2);
    this.jdField_field_95_of_type_Int = paramInt1;
    this.jdField_field_96_of_type_Int = paramInt2;
  }
  
  public final void d10(boolean paramBoolean)
  {
    this.jdField_field_89_of_type_Boolean = paramBoolean;
  }
  
  public final void b13(int paramInt)
  {
    if ((!jdField_field_100_of_type_Boolean) && ((paramInt >= this.jdField_field_90_of_type_Int) || (paramInt < 0))) {
      throw new AssertionError("tried to set " + paramInt + " / " + this.jdField_field_90_of_type_Int);
    }
    this.jdField_field_92_of_type_Int = paramInt;
  }
  
  public final void c6(javax.vecmath.Vector4f paramVector4f)
  {
    this.jdField_field_89_of_type_JavaxVecmathVector4f = paramVector4f;
  }
  
  public final void c4(int paramInt)
  {
    this.jdField_field_93_of_type_Int = paramInt;
  }
  
  public final void a12(class_941 paramclass_941)
  {
    System.err.println("Cannot update static Sprite");
  }
  
  static
  {
    jdField_field_97_of_type_Int = 12;
    jdField_field_100_of_type_Int = 8;
    field_216 = 12;
    field_275 = -1;
    jdField_field_94_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(16);
    Matrix4f localMatrix4f;
    (localMatrix4f = new Matrix4f()).setIdentity();
    localMatrix4f.scale(new org.lwjgl.util.vector.Vector3f(0.01F, -0.01F, 0.01F));
    localMatrix4f.store(jdField_field_94_of_type_JavaNioFloatBuffer);
    jdField_field_94_of_type_JavaNioFloatBuffer.rewind();
    jdField_field_93_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(16);
    jdField_field_95_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(16);
    jdField_field_96_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(16);
    jdField_field_97_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(3);
    BufferUtils.createFloatBuffer(1);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1380
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */