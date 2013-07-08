import com.bulletphysics.linearmath.Transform;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import javax.vecmath.AxisAngle4f;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.schema.schine.graphicsengine.core.GlUtil;

public class class_946
  implements class_965
{
  public class_944 field_98;
  private IntBuffer jdField_field_98_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
  private IntBuffer jdField_field_106_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
  protected static FloatBuffer field_98;
  protected static FloatBuffer field_106;
  private float jdField_field_98_of_type_Float = 1.0F;
  private Vector3f field_108 = new Vector3f();
  private Vector3f field_109 = new Vector3f();
  private Vector3f field_110 = new Vector3f();
  private Vector3f field_111 = new Vector3f();
  private Vector3f field_112 = new Vector3f();
  protected Vector3f field_98;
  protected Vector4f field_98;
  protected Vector3f field_106;
  private Vector3f field_113 = new Vector3f();
  private Transform jdField_field_106_of_type_ComBulletphysicsLinearmathTransform = new Transform();
  private AxisAngle4f jdField_field_98_of_type_JavaxVecmathAxisAngle4f = new AxisAngle4f(this.field_110, 3.141593F);
  private int jdField_field_98_of_type_Int;
  public boolean field_98;
  public Transform field_98;
  protected static float[] field_98;
  protected Vector4f field_106;
  
  private class_946(class_944 paramclass_944)
  {
    this.jdField_field_98_of_type_JavaxVecmathVector3f = new Vector3f();
    this.jdField_field_98_of_type_JavaxVecmathVector4f = new Vector4f();
    this.jdField_field_106_of_type_JavaxVecmathVector3f = new Vector3f();
    this.jdField_field_106_of_type_JavaxVecmathVector4f = new Vector4f();
    this.jdField_field_98_of_type_Class_944 = paramclass_944;
    this.jdField_field_106_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
  }
  
  public class_946(class_944 paramclass_944, float paramFloat)
  {
    this(paramclass_944);
    this.jdField_field_98_of_type_Float = paramFloat;
  }
  
  public final void a() {}
  
  public void b()
  {
    if (this.jdField_field_98_of_type_Class_944.b1() > 0)
    {
      class_946 localclass_946 = this;
      class_950 localclass_950 = this.jdField_field_98_of_type_Class_944.field_9;
      jdField_field_98_of_type_JavaNioFloatBuffer.rewind();
      jdField_field_106_of_type_JavaNioFloatBuffer.rewind();
      jdField_field_98_of_type_JavaNioFloatBuffer.limit(jdField_field_98_of_type_JavaNioFloatBuffer.capacity());
      jdField_field_106_of_type_JavaNioFloatBuffer.limit(jdField_field_106_of_type_JavaNioFloatBuffer.capacity());
      localclass_946.field_108 = GlUtil.c1(localclass_946.field_108);
      localclass_946.field_109 = GlUtil.b1(localclass_946.field_109);
      localclass_946.field_110 = GlUtil.a6(localclass_946.field_110);
      int i = Math.min(512, localclass_946.jdField_field_98_of_type_Class_944.b1());
      localclass_946.jdField_field_98_of_type_Int = 0;
      int j;
      if (localclass_946.jdField_field_98_of_type_Boolean) {
        for (j = i - 1; j >= 0; j--) {
          localclass_946.jdField_field_98_of_type_Int += localclass_946.a52(j, localclass_950);
        }
      } else {
        for (j = 0; j < i; j++) {
          localclass_946.jdField_field_98_of_type_Int += localclass_946.a52(j, localclass_950);
        }
      }
      jdField_field_98_of_type_JavaNioFloatBuffer.flip();
      jdField_field_106_of_type_JavaNioFloatBuffer.flip();
      GL15.glBindBuffer(34962, localclass_946.jdField_field_98_of_type_JavaNioIntBuffer.get(0));
      GL15.glBufferData(34962, jdField_field_98_of_type_JavaNioFloatBuffer, 35048);
      GL15.glBindBuffer(34962, localclass_946.jdField_field_106_of_type_JavaNioIntBuffer.get(0));
      GL15.glBufferData(34962, jdField_field_106_of_type_JavaNioFloatBuffer, 35048);
      if ((jdField_field_98_of_type_JavaNioFloatBuffer.position() == 0 ? 0 : 1) != 0)
      {
        GlUtil.d1();
        GL11.glDisable(2896);
        GL11.glEnable(3042);
        GL11.glDepthMask(false);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(2884);
        localclass_946 = this;
        GL11.glEnableClientState(32884);
        GL11.glEnableClientState(32888);
        GL15.glBindBuffer(34962, localclass_946.jdField_field_98_of_type_JavaNioIntBuffer.get(0));
        GL11.glVertexPointer(4, 5126, 0, 0L);
        GL15.glBindBuffer(34962, localclass_946.jdField_field_106_of_type_JavaNioIntBuffer.get(0));
        GL11.glTexCoordPointer(4, 5126, 0, 0L);
        GL11.glDrawArrays(7, 0, localclass_946.jdField_field_98_of_type_Int);
        GL15.glBindBuffer(34962, 0);
        GL11.glDisableClientState(32884);
        GL11.glDisableClientState(32888);
        GL11.glDisable(3042);
        GL11.glEnable(2884);
        GL11.glDisable(2903);
        GL11.glEnable(2896);
        GL11.glDepthMask(true);
        GlUtil.c2();
      }
    }
  }
  
  public float a51(int paramInt, class_950 paramclass_950)
  {
    return this.jdField_field_98_of_type_Float;
  }
  
  protected int a52(int paramInt, class_950 paramclass_950)
  {
    paramclass_950.a4(paramInt, this.jdField_field_98_of_type_JavaxVecmathVector3f);
    if (this.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform != null) {
      this.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform.transform(this.jdField_field_98_of_type_JavaxVecmathVector3f);
    }
    if (!GlUtil.b4(this.jdField_field_98_of_type_JavaxVecmathVector3f, class_969.field_1259.a())) {
      return 0;
    }
    if (this.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform != null) {
      paramclass_950.a4(paramInt, this.jdField_field_98_of_type_JavaxVecmathVector3f);
    }
    this.jdField_field_106_of_type_ComBulletphysicsLinearmathTransform.basis.setIdentity();
    paramclass_950.d(paramInt, this.field_113);
    if (this.field_113.length() != 0.0F)
    {
      this.jdField_field_98_of_type_JavaxVecmathAxisAngle4f.set(this.field_110, this.field_108.angle(this.field_113) * 2.0F);
      this.jdField_field_106_of_type_ComBulletphysicsLinearmathTransform.basis.set(this.jdField_field_98_of_type_JavaxVecmathAxisAngle4f);
    }
    this.field_111.set(this.field_109);
    this.field_111.add(this.field_108);
    this.field_111.scale(a51(paramInt, paramclass_950));
    this.field_112.set(this.field_109);
    this.field_112.sub(this.field_108);
    this.field_112.scale(a51(paramInt, paramclass_950));
    this.jdField_field_106_of_type_ComBulletphysicsLinearmathTransform.transform(this.field_111);
    this.jdField_field_106_of_type_ComBulletphysicsLinearmathTransform.transform(this.field_112);
    this.jdField_field_98_of_type_JavaxVecmathVector4f.set(this.jdField_field_98_of_type_JavaxVecmathVector3f.field_615, this.jdField_field_98_of_type_JavaxVecmathVector3f.field_616, this.jdField_field_98_of_type_JavaxVecmathVector3f.field_617, paramclass_950.a3(paramInt));
    GlUtil.a23(jdField_field_98_of_type_JavaNioFloatBuffer, this.field_111, this.field_112, this.jdField_field_98_of_type_JavaxVecmathVector4f);
    paramclass_950.b(paramInt, this.jdField_field_106_of_type_JavaxVecmathVector3f);
    for (paramInt = 0; paramInt < 4; paramInt++)
    {
      this.jdField_field_106_of_type_JavaxVecmathVector4f.set(this.jdField_field_106_of_type_JavaxVecmathVector3f.field_615, this.jdField_field_106_of_type_JavaxVecmathVector3f.field_616, this.jdField_field_106_of_type_JavaxVecmathVector3f.field_617, jdField_field_98_of_type_ArrayOfFloat[paramInt]);
      GlUtil.a25(jdField_field_106_of_type_JavaNioFloatBuffer, this.jdField_field_106_of_type_JavaxVecmathVector4f);
    }
    return 4;
  }
  
  public void c()
  {
    class_946 localclass_946 = this;
    if (jdField_field_98_of_type_JavaNioFloatBuffer == null)
    {
      jdField_field_98_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(8192);
      jdField_field_106_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(8192);
    }
    else
    {
      jdField_field_98_of_type_JavaNioFloatBuffer.rewind();
      jdField_field_106_of_type_JavaNioFloatBuffer.rewind();
    }
    GL15.glGenBuffers(localclass_946.jdField_field_98_of_type_JavaNioIntBuffer);
    GL15.glBindBuffer(34962, localclass_946.jdField_field_98_of_type_JavaNioIntBuffer.get(0));
    GL15.glBufferData(34962, jdField_field_98_of_type_JavaNioFloatBuffer, 35048);
    GL15.glBindBuffer(34962, 0);
    GL15.glGenBuffers(localclass_946.jdField_field_106_of_type_JavaNioIntBuffer);
    GL15.glBindBuffer(34962, localclass_946.jdField_field_106_of_type_JavaNioIntBuffer.get(0));
    GL15.glBufferData(34962, jdField_field_106_of_type_JavaNioFloatBuffer, 35048);
    GL15.glBindBuffer(34962, 0);
  }
  
  static
  {
    jdField_field_98_of_type_ArrayOfFloat = new float[] { 0.0F, 0.5F, 0.75F, 0.25F };
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_946
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */