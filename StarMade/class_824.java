import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.List;
import javax.vecmath.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.schema.common.FastMath;
import org.schema.schine.graphicsengine.camera.Camera;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.graphicsengine.forms.Mesh;

public final class class_824
  implements class_965
{
  private IntBuffer jdField_field_98_of_type_JavaNioIntBuffer;
  private IntBuffer field_106;
  private boolean jdField_field_98_of_type_Boolean = true;
  public class_812 field_98;
  private class_816 jdField_field_98_of_type_Class_816;
  private class_814 jdField_field_98_of_type_Class_814;
  private Integer jdField_field_98_of_type_JavaLangInteger;
  
  public final void a() {}
  
  public final void b()
  {
    if (this.jdField_field_98_of_type_Boolean) {
      c();
    }
    d();
    e();
  }
  
  public final void d()
  {
    if (!class_949.field_1197.b1()) {
      return;
    }
    if (this.jdField_field_98_of_type_Boolean) {
      c();
    }
    Vector3f localVector3f = class_969.a1().a83();
    GL11.glDisable(2929);
    GlUtil.d1();
    Mesh localMesh = (Mesh)class_969.a2().a4("Box").a152().get(0);
    GlUtil.c4(localVector3f.field_615, localVector3f.field_616, localVector3f.field_617);
    GlUtil.b5(10.0F, 10.0F, 10.0F);
    GL11.glDisable(2884);
    GL13.glActiveTexture(33984);
    GL11.glBindTexture(3553, 0);
    GL11.glBindTexture(34067, this.jdField_field_98_of_type_Class_814.a1().field_1592);
    class_1376.field_1560.field_1578 = this.jdField_field_98_of_type_Class_814;
    class_1376.field_1560.b();
    localMesh.e();
    class_1376.field_1560.d();
    GL11.glBindTexture(34067, 0);
    GL11.glEnable(2884);
    GlUtil.c2();
  }
  
  public final void e()
  {
    if (!class_949.field_1230.b1()) {
      return;
    }
    if (this.jdField_field_98_of_type_Boolean) {
      c();
    }
    if (this.jdField_field_98_of_type_JavaNioIntBuffer == null) {
      return;
    }
    GlUtil.d1();
    Vector3f localVector3f = class_969.a1().a83();
    GL11.glDisable(2929);
    GL11.glTranslatef(localVector3f.field_615, localVector3f.field_616, localVector3f.field_617);
    GL11.glDisable(2896);
    GL11.glEnable(3042);
    if ((class_949.field_1206.b1()) || (class_949.field_1240.b1())) {
      GL11.glBlendFunc(1, 771);
    } else {
      GL11.glBlendFunc(770, 771);
    }
    GL11.glEnableClientState(32884);
    GL15.glBindBuffer(34962, this.jdField_field_98_of_type_JavaNioIntBuffer.get(0));
    GL11.glVertexPointer(3, 5126, 0, 0L);
    GL11.glEnableClientState(32886);
    GL15.glBindBuffer(34962, this.field_106.get(0));
    GL11.glColorPointer(3, 5126, 0, 0L);
    GL11.glDisable(2884);
    class_1376.field_1547.field_1578 = this.jdField_field_98_of_type_Class_812;
    class_1376.field_1547.b();
    GL11.glDrawArrays(7, 0, this.jdField_field_98_of_type_JavaLangInteger.intValue());
    class_1376.field_1547.d();
    if (!class_949.field_1206.b1()) {
      class_949.field_1240.b1();
    }
    GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
    class_1376.field_1548.field_1578 = this.jdField_field_98_of_type_Class_816;
    class_1376.field_1548.b();
    GL11.glDrawArrays(7, 0, this.jdField_field_98_of_type_JavaLangInteger.intValue());
    class_1376.field_1548.d();
    GL11.glEnable(2884);
    GL15.glBindBuffer(34962, 0);
    GL11.glDisableClientState(32884);
    GL11.glDisableClientState(32886);
    GL11.glEnable(2929);
    GlUtil.c2();
    GL11.glBindTexture(3552, 0);
    GL11.glDisable(3552);
    GL11.glDisable(3553);
    GL11.glDisable(3042);
  }
  
  public final void c()
  {
    this.jdField_field_98_of_type_JavaLangInteger = ((Integer)class_949.field_1184.a4());
    this.jdField_field_98_of_type_Class_812 = new class_812();
    this.jdField_field_98_of_type_Class_816 = new class_816();
    this.jdField_field_98_of_type_Class_814 = new class_814();
    if (this.jdField_field_98_of_type_JavaLangInteger.intValue() <= 0)
    {
      class_949.field_1230.a8(Boolean.valueOf(false));
      this.jdField_field_98_of_type_Boolean = false;
      return;
    }
    ByteBuffer localByteBuffer1 = BufferUtils.createByteBuffer(this.jdField_field_98_of_type_JavaLangInteger.intValue() * 3 << 2 << 2);
    ByteBuffer localByteBuffer2 = BufferUtils.createByteBuffer(this.jdField_field_98_of_type_JavaLangInteger.intValue() * 3 << 2 << 2);
    FloatBuffer localFloatBuffer1 = localByteBuffer1.asFloatBuffer();
    FloatBuffer localFloatBuffer2 = localByteBuffer2.asFloatBuffer();
    FloatBuffer localFloatBuffer4 = localFloatBuffer2;
    FloatBuffer localFloatBuffer3 = localFloatBuffer1;
    class_824 localclass_824 = this;
    float[] arrayOfFloat = { 0.0F, 0.5F, 0.75F, 0.25F };
    Vector3f[] arrayOfVector3f1;
    Vector3f[] arrayOfVector3f2 = arrayOfVector3f1 = new Vector3f[localclass_824.jdField_field_98_of_type_JavaLangInteger.intValue()];
    float f1 = 3.141593F * (3.0F - FastMath.l(5.0F));
    float f2 = 2.0F / arrayOfVector3f2.length;
    float f3 = 0.0F;
    for (int k = 0; k < arrayOfVector3f2.length; k++)
    {
      double d = (Math.random() + 1.0D) / 2.0D * 10.0D;
      if (Math.random() < 0.5D) {
        d = -d;
      }
      arrayOfVector3f2[k] = new Vector3f();
      float f4 = k * f2 - 1.0F + f2 / 2.0F;
      float f5 = FastMath.l(1.0F - f4 * f4);
      float f6 = f3;
      f3 = (float)(f3 + (0.5D + Math.random()) * f1);
      arrayOfVector3f2[k].set(FastMath.d(f6) * f5, Math.random() > 0.5D ? f4 / 5.5F : f4, FastMath.i(f6) * f5);
      arrayOfVector3f2[k].normalize();
      arrayOfVector3f2[k].scale((float)d);
    }
    for (int i = 0; i < localclass_824.jdField_field_98_of_type_JavaLangInteger.intValue(); i++)
    {
      Vector3f localVector3f = arrayOfVector3f1[i];
      for (int j = 0; j < 4; j++)
      {
        localFloatBuffer3.put(localVector3f.field_615);
        localFloatBuffer3.put(localVector3f.field_616);
        localFloatBuffer3.put(localVector3f.field_617);
        localFloatBuffer4.put(1.0F);
        localFloatBuffer4.put(arrayOfFloat[j]);
        localFloatBuffer4.put(0.5F + (float)Math.random() * 0.5F);
      }
    }
    if (localFloatBuffer1.position() <= 0)
    {
      class_949.field_1230.a8(Boolean.valueOf(false));
      this.jdField_field_98_of_type_Boolean = false;
      return;
    }
    this.jdField_field_98_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
    GL15.glGenBuffers(this.jdField_field_98_of_type_JavaNioIntBuffer);
    GL15.glBindBuffer(34962, this.jdField_field_98_of_type_JavaNioIntBuffer.get(0));
    localFloatBuffer1.rewind();
    GL15.glBufferData(34962, localFloatBuffer1, 35044);
    GL15.glBindBuffer(34962, 0);
    this.field_106 = BufferUtils.createIntBuffer(1);
    GL15.glGenBuffers(this.field_106);
    GL15.glBindBuffer(34962, this.field_106.get(0));
    localFloatBuffer2.rewind();
    GL15.glBufferData(34962, localFloatBuffer2, 35044);
    GL15.glBindBuffer(34962, 0);
    try
    {
      GlUtil.a3(localByteBuffer1);
      GlUtil.a3(localByteBuffer2);
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
    this.jdField_field_98_of_type_Boolean = false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_824
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */