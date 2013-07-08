import com.bulletphysics.linearmath.Transform;
import java.io.PrintStream;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.schema.schine.graphicsengine.camera.Camera;
import org.schema.schine.graphicsengine.core.GlUtil;

public class class_1352
  implements class_965
{
  class_944 jdField_field_98_of_type_Class_944;
  private IntBuffer jdField_field_98_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
  private IntBuffer jdField_field_106_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
  private static FloatBuffer jdField_field_98_of_type_JavaNioFloatBuffer;
  private static FloatBuffer jdField_field_106_of_type_JavaNioFloatBuffer;
  public static float field_98;
  private Vector3f jdField_field_98_of_type_JavaxVecmathVector3f = new Vector3f();
  private Vector3f jdField_field_106_of_type_JavaxVecmathVector3f = new Vector3f();
  private Vector3f jdField_field_108_of_type_JavaxVecmathVector3f = new Vector3f();
  private Vector3f field_109 = new Vector3f();
  private Vector3f field_110 = new Vector3f();
  private Vector3f field_111 = new Vector3f();
  private Vector3f field_112 = new Vector3f();
  private Vector3f field_113 = new Vector3f();
  private Vector4f jdField_field_98_of_type_JavaxVecmathVector4f = new Vector4f();
  private Vector3f field_114 = new Vector3f();
  private Vector3f[] jdField_field_98_of_type_ArrayOfJavaxVecmathVector3f = new Vector3f[4];
  private Vector3f[] jdField_field_106_of_type_ArrayOfJavaxVecmathVector3f = new Vector3f[4];
  private Vector3f[] jdField_field_108_of_type_ArrayOfJavaxVecmathVector3f = new Vector3f[4];
  private Transform jdField_field_98_of_type_ComBulletphysicsLinearmathTransform = new Transform();
  boolean jdField_field_98_of_type_Boolean = true;
  private static float[] jdField_field_98_of_type_ArrayOfFloat = { 0.0F, 0.5F, 0.75F, 0.25F };
  private int jdField_field_98_of_type_Int;
  private Vector3f field_115 = new Vector3f();
  private Vector3f field_269 = new Vector3f();
  private Vector3f field_270 = new Vector3f();
  private Vector3f field_271 = new Vector3f();
  private Vector4f jdField_field_106_of_type_JavaxVecmathVector4f;
  
  private class_1352()
  {
    new Vector3f();
    new Vector3f();
    new Vector3f();
    new Vector3f();
    this.jdField_field_106_of_type_JavaxVecmathVector4f = new Vector4f();
    this.jdField_field_98_of_type_Class_944 = null;
    this.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
    for (int i = 0; i < this.jdField_field_98_of_type_ArrayOfJavaxVecmathVector3f.length; i++)
    {
      this.jdField_field_98_of_type_ArrayOfJavaxVecmathVector3f[i] = new Vector3f();
      this.jdField_field_108_of_type_ArrayOfJavaxVecmathVector3f[i] = new Vector3f();
      this.jdField_field_106_of_type_ArrayOfJavaxVecmathVector3f[i] = new Vector3f();
    }
  }
  
  public class_1352(byte paramByte)
  {
    this();
  }
  
  public final void a() {}
  
  public void b()
  {
    if (this.jdField_field_98_of_type_Class_944.b1() > 1)
    {
      class_1352 localclass_1352 = this;
      class_950 localclass_950 = this.jdField_field_98_of_type_Class_944.field_9;
      jdField_field_98_of_type_JavaNioFloatBuffer.rewind();
      jdField_field_106_of_type_JavaNioFloatBuffer.rewind();
      jdField_field_98_of_type_JavaNioFloatBuffer.limit(jdField_field_98_of_type_JavaNioFloatBuffer.capacity());
      jdField_field_106_of_type_JavaNioFloatBuffer.limit(jdField_field_106_of_type_JavaNioFloatBuffer.capacity());
      localclass_1352.jdField_field_98_of_type_JavaxVecmathVector3f = GlUtil.c1(localclass_1352.jdField_field_98_of_type_JavaxVecmathVector3f);
      localclass_1352.jdField_field_106_of_type_JavaxVecmathVector3f = GlUtil.b1(localclass_1352.jdField_field_106_of_type_JavaxVecmathVector3f);
      localclass_1352.jdField_field_108_of_type_JavaxVecmathVector3f = GlUtil.a6(localclass_1352.jdField_field_108_of_type_JavaxVecmathVector3f);
      int i = Math.min(512, localclass_1352.jdField_field_98_of_type_Class_944.b1());
      int j = localclass_1352.jdField_field_98_of_type_Class_944.a15() % localclass_1352.jdField_field_98_of_type_Class_944.field_9.a1();
      float f1 = 0.0F;
      float f2 = 1.0F / i;
      localclass_1352.jdField_field_98_of_type_Int = 0;
      int k;
      int m;
      int n;
      if (localclass_1352.jdField_field_98_of_type_Boolean)
      {
        j = j + i - 1;
        for (k = 0; k < i; k++)
        {
          m = j % localclass_1352.jdField_field_98_of_type_Class_944.field_9.a1();
          n = (j - 1) % localclass_1352.jdField_field_98_of_type_Class_944.field_9.a1();
          localclass_1352.a61(m, n, localclass_950, f1, k, i);
          f1 += f2;
          j--;
        }
      }
      else
      {
        for (k = 0; k < i; k++)
        {
          m = j % localclass_1352.jdField_field_98_of_type_Class_944.field_9.a1();
          n = (j + 1) % localclass_1352.jdField_field_98_of_type_Class_944.field_9.a1();
          localclass_1352.a61(m, n, localclass_950, f1, k, i);
          f1 += f2;
          j++;
        }
      }
      jdField_field_98_of_type_JavaNioFloatBuffer.flip();
      jdField_field_106_of_type_JavaNioFloatBuffer.flip();
      GL15.glBindBuffer(34962, localclass_1352.jdField_field_98_of_type_JavaNioIntBuffer.get(0));
      GL15.glBufferData(34962, jdField_field_98_of_type_JavaNioFloatBuffer, 35048);
      GL15.glBindBuffer(34962, localclass_1352.jdField_field_106_of_type_JavaNioIntBuffer.get(0));
      GL15.glBufferData(34962, jdField_field_106_of_type_JavaNioFloatBuffer, 35048);
      if ((jdField_field_98_of_type_JavaNioFloatBuffer.position() == 0 ? 0 : 1) != 0)
      {
        GlUtil.d1();
        GL11.glDisable(2884);
        GL11.glDisable(2896);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        localclass_1352 = this;
        GL11.glEnableClientState(32884);
        GL11.glEnableClientState(32888);
        GL15.glBindBuffer(34962, localclass_1352.jdField_field_98_of_type_JavaNioIntBuffer.get(0));
        GL11.glVertexPointer(4, 5126, 0, 0L);
        GL15.glBindBuffer(34962, localclass_1352.jdField_field_106_of_type_JavaNioIntBuffer.get(0));
        GL11.glTexCoordPointer(4, 5126, 0, 0L);
        GL11.glDrawArrays(8, 0, localclass_1352.jdField_field_98_of_type_Int);
        GL15.glBindBuffer(34962, 0);
        GL11.glDisableClientState(32884);
        GL11.glDisableClientState(32888);
        GL11.glDisable(3042);
        GlUtil.c2();
        GL11.glDisable(2903);
        GL11.glEnable(2884);
        GL11.glEnable(2896);
      }
    }
  }
  
  private void a61(int paramInt1, int paramInt2, class_950 paramclass_950, float paramFloat1, float paramFloat2, int paramInt3)
  {
    try
    {
      int i = paramFloat2 > 0.0F ? 1 : 0;
      paramclass_950.a4(paramInt1, this.field_109);
      if (paramFloat2 >= paramInt3 - 2)
      {
        this.field_111.set(this.field_112);
      }
      else
      {
        paramclass_950.a4(paramInt2, this.field_110);
        this.field_111.sub(this.field_110, this.field_109);
      }
      if (i != 0) {
        this.field_113.cross(this.field_111, this.field_112);
      }
      this.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
      GlUtil.a(this.field_109, this.field_111, class_969.a1().a83(), this.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform);
      paramclass_950.d(paramInt1, this.field_114);
      this.jdField_field_98_of_type_JavaxVecmathVector4f.set(this.field_109.field_615, this.field_109.field_616, this.field_109.field_617, paramclass_950.a3(paramInt1));
      GlUtil.a9(this.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform, jdField_field_98_of_type_Float, this.jdField_field_98_of_type_ArrayOfJavaxVecmathVector3f);
      this.field_115.sub(this.jdField_field_108_of_type_ArrayOfJavaxVecmathVector3f[3], this.jdField_field_98_of_type_ArrayOfJavaxVecmathVector3f[0]);
      this.field_269.sub(this.jdField_field_108_of_type_ArrayOfJavaxVecmathVector3f[2], this.jdField_field_98_of_type_ArrayOfJavaxVecmathVector3f[1]);
      this.field_115.scale(0.5F);
      this.field_269.scale(0.5F);
      this.field_270.add(this.jdField_field_98_of_type_ArrayOfJavaxVecmathVector3f[0], this.field_115);
      this.field_271.add(this.jdField_field_98_of_type_ArrayOfJavaxVecmathVector3f[1], this.field_269);
      if (paramFloat2 > 0.0F)
      {
        this.jdField_field_106_of_type_ArrayOfJavaxVecmathVector3f[0].set(this.jdField_field_108_of_type_ArrayOfJavaxVecmathVector3f[0]);
        this.jdField_field_106_of_type_ArrayOfJavaxVecmathVector3f[1].set(this.jdField_field_108_of_type_ArrayOfJavaxVecmathVector3f[1]);
        this.jdField_field_106_of_type_ArrayOfJavaxVecmathVector3f[2].set(this.field_271);
        this.jdField_field_106_of_type_ArrayOfJavaxVecmathVector3f[3].set(this.field_270);
        GlUtil.a27(jdField_field_98_of_type_JavaNioFloatBuffer, this.jdField_field_98_of_type_JavaxVecmathVector4f.field_599, this.jdField_field_106_of_type_ArrayOfJavaxVecmathVector3f, jdField_field_98_of_type_JavaNioFloatBuffer.position() - 8);
      }
      if (paramFloat2 > 0.0F)
      {
        this.jdField_field_106_of_type_ArrayOfJavaxVecmathVector3f[0].set(this.field_270);
        this.jdField_field_106_of_type_ArrayOfJavaxVecmathVector3f[1].set(this.field_271);
        this.jdField_field_106_of_type_ArrayOfJavaxVecmathVector3f[2].set(this.jdField_field_98_of_type_ArrayOfJavaxVecmathVector3f[2]);
        this.jdField_field_106_of_type_ArrayOfJavaxVecmathVector3f[3].set(this.jdField_field_98_of_type_ArrayOfJavaxVecmathVector3f[3]);
        GlUtil.a27(jdField_field_98_of_type_JavaNioFloatBuffer, this.jdField_field_98_of_type_JavaxVecmathVector4f.field_599, this.jdField_field_106_of_type_ArrayOfJavaxVecmathVector3f, -1);
        this.jdField_field_98_of_type_Int += 2;
      }
      else
      {
        GlUtil.a27(jdField_field_98_of_type_JavaNioFloatBuffer, this.jdField_field_98_of_type_JavaxVecmathVector4f.field_599, this.jdField_field_98_of_type_ArrayOfJavaxVecmathVector3f, -1);
        this.jdField_field_98_of_type_Int += 2;
      }
      for (paramInt1 = 0; paramInt1 < 2; paramInt1++)
      {
        this.jdField_field_106_of_type_JavaxVecmathVector4f.set(0.0F, paramFloat2 * 0.05F, paramFloat1, jdField_field_98_of_type_ArrayOfFloat[paramInt1]);
        GlUtil.a25(jdField_field_106_of_type_JavaNioFloatBuffer, this.jdField_field_106_of_type_JavaxVecmathVector4f);
      }
      for (paramInt1 = 0; paramInt1 < 4; paramInt1++) {
        if (paramFloat2 > 0.0F) {
          this.jdField_field_108_of_type_ArrayOfJavaxVecmathVector3f[paramInt1].set(this.jdField_field_106_of_type_ArrayOfJavaxVecmathVector3f[paramInt1]);
        } else {
          this.jdField_field_108_of_type_ArrayOfJavaxVecmathVector3f[paramInt1].set(this.jdField_field_98_of_type_ArrayOfJavaxVecmathVector3f[paramInt1]);
        }
      }
    }
    catch (Exception localException)
    {
      System.err.println("[ERROR][TRAILDRAWER] " + localException.getClass().getSimpleName() + " " + localException.getMessage());
    }
    this.field_112.set(this.field_111);
  }
  
  public void c()
  {
    class_1352 localclass_1352 = this;
    if (jdField_field_98_of_type_JavaNioFloatBuffer == null) {
      jdField_field_98_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(4104);
    } else {
      jdField_field_98_of_type_JavaNioFloatBuffer.rewind();
    }
    GL15.glGenBuffers(localclass_1352.jdField_field_98_of_type_JavaNioIntBuffer);
    GL15.glBindBuffer(34962, localclass_1352.jdField_field_98_of_type_JavaNioIntBuffer.get(0));
    GL15.glBufferData(34962, jdField_field_98_of_type_JavaNioFloatBuffer, 35048);
    GL15.glBindBuffer(34962, 0);
    if (jdField_field_106_of_type_JavaNioFloatBuffer == null) {
      jdField_field_106_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(4104);
    } else {
      jdField_field_106_of_type_JavaNioFloatBuffer.rewind();
    }
    GL15.glGenBuffers(localclass_1352.jdField_field_106_of_type_JavaNioIntBuffer);
    GL15.glBindBuffer(34962, localclass_1352.jdField_field_106_of_type_JavaNioIntBuffer.get(0));
    GL15.glBufferData(34962, jdField_field_106_of_type_JavaNioFloatBuffer, 35048);
    GL15.glBindBuffer(34962, 0);
  }
  
  public final void a62(class_944 paramclass_944)
  {
    this.jdField_field_98_of_type_Class_944 = paramclass_944;
  }
  
  static
  {
    jdField_field_98_of_type_Float = 0.5F;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1352
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */