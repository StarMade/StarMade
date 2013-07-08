import com.bulletphysics.linearmath.Transform;
import java.nio.FloatBuffer;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.util.vector.Matrix4f;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.schine.graphicsengine.core.GlUtil;

public final class class_820
{
  private Transform jdField_field_1081_of_type_ComBulletphysicsLinearmathTransform = new Transform();
  private Transform field_1082;
  private Matrix3f jdField_field_1081_of_type_JavaxVecmathMatrix3f = new Matrix3f();
  private Transform field_1083;
  private FloatBuffer jdField_field_1081_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(16);
  private float[] jdField_field_1081_of_type_ArrayOfFloat = new float[16];
  public boolean field_1081;
  public int field_1081;
  public int field_1082;
  public int field_1083;
  public int field_1084 = 200;
  public int field_1085 = 200;
  
  public class_820()
  {
    this.jdField_field_1082_of_type_ComBulletphysicsLinearmathTransform = new Transform();
    this.jdField_field_1083_of_type_ComBulletphysicsLinearmathTransform = new Transform();
    this.jdField_field_1081_of_type_Boolean = false;
    this.jdField_field_1081_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
    this.jdField_field_1082_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
  }
  
  public final void a()
  {
    Object localObject = class_969.field_1259;
    this.jdField_field_1081_of_type_JavaNioFloatBuffer.rewind();
    ((Matrix4f)localObject).store(this.jdField_field_1081_of_type_JavaNioFloatBuffer);
    this.jdField_field_1081_of_type_JavaNioFloatBuffer.rewind();
    this.jdField_field_1081_of_type_JavaNioFloatBuffer.get(this.jdField_field_1081_of_type_ArrayOfFloat);
    this.jdField_field_1083_of_type_ComBulletphysicsLinearmathTransform.setFromOpenGLMatrix(this.jdField_field_1081_of_type_ArrayOfFloat);
    this.jdField_field_1083_of_type_ComBulletphysicsLinearmathTransform.origin.set(0.0F, 0.0F, 0.0F);
    GL11.glClear(256);
    if (this.jdField_field_1081_of_type_Boolean) {
      class_1363.a167(1024, 1024);
    } else {
      class_1363.j();
    }
    GL11.glDisable(2929);
    GL15.glBindBuffer(34962, 0);
    int i = (localObject = ElementKeyMap.typeList()).length;
    for (int j = 0; j < i; j++)
    {
      short s;
      int k;
      if ((k = ElementKeyMap.getInfo(s = localObject[j]).getBuildIconNum()) / 256 == this.jdField_field_1081_of_type_Int)
      {
        GlUtil.d1();
        int m = k % 16;
        k = (k - (this.jdField_field_1081_of_type_Int << 8)) / 16;
        GlUtil.c4(this.jdField_field_1082_of_type_Int + (m << 6), this.jdField_field_1083_of_type_Int + (k << 6), 0.0F);
        GlUtil.b5(32.0F, -32.0F, 32.0F);
        if (ElementKeyMap.getInfo(s).getBlockStyle() == 3)
        {
          this.jdField_field_1082_of_type_ComBulletphysicsLinearmathTransform.basis.set(this.jdField_field_1083_of_type_ComBulletphysicsLinearmathTransform.basis);
          this.jdField_field_1083_of_type_ComBulletphysicsLinearmathTransform.basis.setIdentity();
        }
        else
        {
          this.jdField_field_1081_of_type_JavaxVecmathMatrix3f.set(this.jdField_field_1081_of_type_ComBulletphysicsLinearmathTransform.basis);
          this.jdField_field_1083_of_type_ComBulletphysicsLinearmathTransform.basis.mul(this.jdField_field_1081_of_type_JavaxVecmathMatrix3f);
        }
        GlUtil.b3(this.jdField_field_1083_of_type_ComBulletphysicsLinearmathTransform);
        if (ElementKeyMap.getInfo(s).getBlockStyle() == 3) {
          this.jdField_field_1083_of_type_ComBulletphysicsLinearmathTransform.basis.set(this.jdField_field_1082_of_type_ComBulletphysicsLinearmathTransform.basis);
        }
        new class_822().a41(s);
        GlUtil.c2();
      }
    }
    class_1363.h1();
    GL11.glEnable(2896);
    GL11.glDisable(2977);
    GL11.glEnable(2929);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_820
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */