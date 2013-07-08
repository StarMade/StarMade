import com.bulletphysics.linearmath.Transform;
import java.io.PrintStream;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.schema.schine.graphicsengine.camera.Camera;
import org.schema.schine.graphicsengine.core.GlUtil;

public class class_948
  implements class_965
{
  public class_944 field_98;
  public static boolean field_98;
  private IntBuffer jdField_field_98_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
  private IntBuffer jdField_field_106_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
  private static FloatBuffer jdField_field_98_of_type_JavaNioFloatBuffer;
  private static FloatBuffer jdField_field_106_of_type_JavaNioFloatBuffer;
  private float jdField_field_98_of_type_Float = 3.0F;
  private Vector3f jdField_field_98_of_type_JavaxVecmathVector3f = new Vector3f();
  private Vector3f jdField_field_106_of_type_JavaxVecmathVector3f = new Vector3f();
  private Vector3f jdField_field_108_of_type_JavaxVecmathVector3f = new Vector3f();
  private Vector3f field_109 = new Vector3f();
  private Vector3f field_110 = new Vector3f();
  private Vector3f field_111 = new Vector3f();
  private Vector3f field_112 = new Vector3f();
  private Transform jdField_field_98_of_type_ComBulletphysicsLinearmathTransform = new Transform();
  private Vector3f field_113 = new Vector3f();
  private Vector3f field_114;
  private Vector4f jdField_field_98_of_type_JavaxVecmathVector4f;
  private Vector3f[] jdField_field_98_of_type_ArrayOfJavaxVecmathVector3f;
  private Vector3f[] jdField_field_106_of_type_ArrayOfJavaxVecmathVector3f;
  private Vector3f[] jdField_field_108_of_type_ArrayOfJavaxVecmathVector3f;
  private static float[] jdField_field_98_of_type_ArrayOfFloat = { 0.0F, 0.5F, 0.75F, 0.25F };
  public boolean field_106;
  private int jdField_field_98_of_type_Int;
  private boolean jdField_field_108_of_type_Boolean;
  
  private class_948(class_944 paramclass_944)
  {
    new Vector3f();
    new Vector3f();
    new Vector3f();
    this.field_114 = new Vector3f();
    new Vector3f();
    this.jdField_field_98_of_type_JavaxVecmathVector4f = new Vector4f();
    this.jdField_field_98_of_type_ArrayOfJavaxVecmathVector3f = new Vector3f[4];
    this.jdField_field_106_of_type_ArrayOfJavaxVecmathVector3f = new Vector3f[4];
    this.jdField_field_108_of_type_ArrayOfJavaxVecmathVector3f = new Vector3f[4];
    this.jdField_field_106_of_type_Boolean = true;
    this.jdField_field_98_of_type_Class_944 = paramclass_944;
    this.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
  }
  
  public class_948(class_944 paramclass_944, byte paramByte)
  {
    this(paramclass_944);
    for (paramclass_944 = 0; paramclass_944 < this.jdField_field_98_of_type_ArrayOfJavaxVecmathVector3f.length; paramclass_944++)
    {
      this.jdField_field_98_of_type_ArrayOfJavaxVecmathVector3f[paramclass_944] = new Vector3f();
      this.jdField_field_106_of_type_ArrayOfJavaxVecmathVector3f[paramclass_944] = new Vector3f();
      this.jdField_field_108_of_type_ArrayOfJavaxVecmathVector3f[paramclass_944] = new Vector3f();
    }
  }
  
  public final void a() {}
  
  public void b()
  {
    class_971.field_98.add("# DParticles: " + this.jdField_field_98_of_type_Class_944.b1());
    class_950 localclass_950 = null;
    if (this.jdField_field_98_of_type_Class_944.b1() > 0)
    {
      if (jdField_field_98_of_type_Boolean)
      {
        a54(this.jdField_field_98_of_type_Class_944.field_9);
        return;
      }
      if (this.jdField_field_108_of_type_Boolean)
      {
        GlUtil.d1();
        GL11.glDisable(2884);
        GL11.glDisable(2896);
        if (this.jdField_field_106_of_type_Boolean)
        {
          GL11.glEnable(3042);
          GL11.glBlendFunc(770, 771);
        }
        localclass_948 = this;
        GL11.glEnableClientState(32884);
        GL11.glEnableClientState(32888);
        GL15.glBindBuffer(34962, localclass_948.jdField_field_98_of_type_JavaNioIntBuffer.get(0));
        GL11.glVertexPointer(4, 5126, 0, 0L);
        GL15.glBindBuffer(34962, localclass_948.jdField_field_106_of_type_JavaNioIntBuffer.get(0));
        GL11.glTexCoordPointer(4, 5126, 0, 0L);
        GL11.glDrawArrays(7, 0, localclass_948.jdField_field_98_of_type_Int / 4);
        GL15.glBindBuffer(34962, 0);
        GL11.glDisableClientState(32884);
        GL11.glDisableClientState(32888);
        GL11.glDisable(3042);
        GlUtil.c2();
        GL11.glDisable(2903);
        GL11.glEnable(2884);
        GL11.glEnable(2896);
      }
      class_948 localclass_948 = this;
      localclass_950 = this.jdField_field_98_of_type_Class_944.field_9;
      this.jdField_field_108_of_type_Boolean = localclass_948.a55(localclass_950);
    }
  }
  
  private void a53(Vector3f paramVector3f, float paramFloat1, float paramFloat2, FloatBuffer paramFloatBuffer)
  {
    this.jdField_field_98_of_type_JavaxVecmathVector4f.set(paramVector3f.field_615, paramFloat1, paramFloat1, jdField_field_98_of_type_ArrayOfFloat[0]);
    GlUtil.a25(paramFloatBuffer, this.jdField_field_98_of_type_JavaxVecmathVector4f);
    this.jdField_field_98_of_type_JavaxVecmathVector4f.set(paramVector3f.field_615, paramFloat1, paramFloat1, jdField_field_98_of_type_ArrayOfFloat[1]);
    GlUtil.a25(paramFloatBuffer, this.jdField_field_98_of_type_JavaxVecmathVector4f);
    this.jdField_field_98_of_type_JavaxVecmathVector4f.set(paramVector3f.field_615, paramFloat2, paramFloat2, jdField_field_98_of_type_ArrayOfFloat[2]);
    GlUtil.a25(paramFloatBuffer, this.jdField_field_98_of_type_JavaxVecmathVector4f);
    this.jdField_field_98_of_type_JavaxVecmathVector4f.set(paramVector3f.field_615, paramFloat2, paramFloat2, jdField_field_98_of_type_ArrayOfFloat[3]);
    GlUtil.a25(paramFloatBuffer, this.jdField_field_98_of_type_JavaxVecmathVector4f);
  }
  
  private void a54(class_950 paramclass_950)
  {
    GlUtil.d1();
    GL11.glBlendFunc(770, 771);
    GL11.glEnable(3042);
    GL11.glDisable(2929);
    GL11.glDisable(2896);
    GL11.glEnable(2903);
    GL11.glDisable(3553);
    GlUtil.a38(1.0F, 1.0F, 1.0F, 1.0F);
    GL11.glPointSize(10.0F);
    GL11.glBegin(0);
    int i = Math.min(512, this.jdField_field_98_of_type_Class_944.b1());
    System.err.println("DRAWING " + i + " PARTICLES ");
    for (int j = 0; j < i; j++)
    {
      class_950 localclass_950 = paramclass_950;
      int k = j;
      class_948 localclass_948 = this;
      localclass_950.a4(k, localclass_948.field_109);
      GL11.glVertex3f(localclass_948.field_109.field_615, localclass_948.field_109.field_616, localclass_948.field_109.field_617);
    }
    GL11.glEnd();
    GL11.glDisable(2903);
    GlUtil.a38(1.0F, 1.0F, 1.0F, 1.0F);
    GL11.glDisable(3042);
    GL11.glEnable(2929);
    GlUtil.c2();
  }
  
  public void c()
  {
    class_948 localclass_948 = this;
    if (jdField_field_98_of_type_JavaNioFloatBuffer == null) {
      jdField_field_98_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(24576);
    } else {
      jdField_field_98_of_type_JavaNioFloatBuffer.rewind();
    }
    GL15.glGenBuffers(localclass_948.jdField_field_98_of_type_JavaNioIntBuffer);
    GL15.glBindBuffer(34962, localclass_948.jdField_field_98_of_type_JavaNioIntBuffer.get(0));
    GL15.glBufferData(34962, jdField_field_98_of_type_JavaNioFloatBuffer, 35048);
    GL15.glBindBuffer(34962, 0);
    if (jdField_field_106_of_type_JavaNioFloatBuffer == null) {
      jdField_field_106_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(24576);
    } else {
      jdField_field_106_of_type_JavaNioFloatBuffer.rewind();
    }
    GL15.glGenBuffers(localclass_948.jdField_field_106_of_type_JavaNioIntBuffer);
    GL15.glBindBuffer(34962, localclass_948.jdField_field_106_of_type_JavaNioIntBuffer.get(0));
    GL15.glBufferData(34962, jdField_field_106_of_type_JavaNioFloatBuffer, 35048);
    GL15.glBindBuffer(34962, 0);
  }
  
  private boolean a55(class_950 paramclass_950)
  {
    jdField_field_98_of_type_JavaNioFloatBuffer.clear();
    jdField_field_106_of_type_JavaNioFloatBuffer.clear();
    this.jdField_field_98_of_type_JavaxVecmathVector3f = GlUtil.c1(this.jdField_field_98_of_type_JavaxVecmathVector3f);
    this.jdField_field_106_of_type_JavaxVecmathVector3f = GlUtil.b1(this.jdField_field_106_of_type_JavaxVecmathVector3f);
    this.jdField_field_108_of_type_JavaxVecmathVector3f = GlUtil.a6(this.jdField_field_108_of_type_JavaxVecmathVector3f);
    int i = Math.min(512, this.jdField_field_98_of_type_Class_944.b1());
    for (int j = 0; j < i; j++)
    {
      class_950 localclass_950 = paramclass_950;
      int k = j;
      class_948 localclass_948 = this;
      localclass_950.d(k, localclass_948.field_111);
      localclass_950.a4(k, localclass_948.field_109);
      localclass_948.field_114.set(localclass_948.field_109);
      localclass_948.field_111.normalize();
      localclass_948.field_114.field_615 += localclass_948.field_111.field_615 * localclass_948.jdField_field_98_of_type_Float;
      localclass_948.field_114.field_616 += localclass_948.field_111.field_616 * localclass_948.jdField_field_98_of_type_Float;
      localclass_948.field_114.field_617 += localclass_948.field_111.field_617 * localclass_948.jdField_field_98_of_type_Float;
      if (((GlUtil.a20(localclass_948.field_109, class_969.field_1259.a())) || (GlUtil.a20(localclass_948.field_114, class_969.field_1259.a()))) && ((class_969.a1().a186(localclass_948.field_109.field_615, localclass_948.field_109.field_616, localclass_948.field_109.field_617)) || (class_969.a1().a186(localclass_948.field_114.field_615, localclass_948.field_114.field_616, localclass_948.field_114.field_617))))
      {
        float f2 = localclass_950.a3(k);
        localclass_950.b(k, localclass_948.field_110);
        localclass_948.field_113.sub(localclass_948.field_114, localclass_948.field_109);
        localclass_948.field_113.normalize();
        localclass_948.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
        float f1 = Math.max(100.0F, Math.min(1000.0F, localclass_950.c(k, localclass_948.field_112).field_615)) * 0.001F;
        GlUtil.a(localclass_948.field_109, localclass_948.field_113, class_969.a1().a83(), localclass_948.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform);
        GlUtil.a9(localclass_948.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform, f1, localclass_948.jdField_field_98_of_type_ArrayOfJavaxVecmathVector3f);
        localclass_948.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
        GlUtil.a(localclass_948.field_114, localclass_948.field_113, class_969.a1().a83(), localclass_948.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform);
        GlUtil.a9(localclass_948.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform, f1, localclass_948.jdField_field_108_of_type_ArrayOfJavaxVecmathVector3f);
        localclass_948.jdField_field_106_of_type_ArrayOfJavaxVecmathVector3f[0].set(localclass_948.jdField_field_98_of_type_ArrayOfJavaxVecmathVector3f[2]);
        localclass_948.jdField_field_106_of_type_ArrayOfJavaxVecmathVector3f[1].set(localclass_948.jdField_field_98_of_type_ArrayOfJavaxVecmathVector3f[3]);
        localclass_948.jdField_field_106_of_type_ArrayOfJavaxVecmathVector3f[2].set(localclass_948.jdField_field_108_of_type_ArrayOfJavaxVecmathVector3f[0]);
        localclass_948.jdField_field_106_of_type_ArrayOfJavaxVecmathVector3f[3].set(localclass_948.jdField_field_108_of_type_ArrayOfJavaxVecmathVector3f[1]);
        GlUtil.a28(jdField_field_98_of_type_JavaNioFloatBuffer, f2, localclass_948.jdField_field_98_of_type_ArrayOfJavaxVecmathVector3f);
        GlUtil.a28(jdField_field_98_of_type_JavaNioFloatBuffer, f2, localclass_948.jdField_field_106_of_type_ArrayOfJavaxVecmathVector3f);
        GlUtil.a28(jdField_field_98_of_type_JavaNioFloatBuffer, f2, localclass_948.jdField_field_108_of_type_ArrayOfJavaxVecmathVector3f);
        localclass_948.a53(localclass_948.field_110, 0.0F, 0.3333333F, jdField_field_106_of_type_JavaNioFloatBuffer);
        localclass_948.a53(localclass_948.field_110, 0.3333333F, 0.6666666F, jdField_field_106_of_type_JavaNioFloatBuffer);
        localclass_948.a53(localclass_948.field_110, 0.6666666F, 1.0F, jdField_field_106_of_type_JavaNioFloatBuffer);
      }
    }
    if (jdField_field_98_of_type_JavaNioFloatBuffer.position() == 0)
    {
      this.jdField_field_98_of_type_Int = 0;
      return false;
    }
    jdField_field_98_of_type_JavaNioFloatBuffer.flip();
    jdField_field_106_of_type_JavaNioFloatBuffer.flip();
    this.jdField_field_98_of_type_Int = jdField_field_98_of_type_JavaNioFloatBuffer.limit();
    GL15.glBindBuffer(34962, this.jdField_field_98_of_type_JavaNioIntBuffer.get(0));
    GL15.glBufferSubData(34962, 0L, jdField_field_98_of_type_JavaNioFloatBuffer);
    GL15.glBindBuffer(34962, this.jdField_field_106_of_type_JavaNioIntBuffer.get(0));
    GL15.glBufferSubData(34962, 0L, jdField_field_106_of_type_JavaNioFloatBuffer);
    return true;
  }
  
  static
  {
    yU.class.desiredAssertionStatus();
    jdField_field_98_of_type_Boolean = false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_948
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */