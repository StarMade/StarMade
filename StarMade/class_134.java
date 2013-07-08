import com.bulletphysics.linearmath.Transform;
import java.io.PrintStream;
import java.nio.FloatBuffer;
import java.util.List;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.util.vector.Matrix4f;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.graphicsengine.forms.Mesh;

public final class class_134
  implements class_965
{
  private class_371 jdField_field_98_of_type_Class_371;
  private float jdField_field_98_of_type_Float;
  private Matrix3f jdField_field_98_of_type_JavaxVecmathMatrix3f;
  private Transform jdField_field_98_of_type_ComBulletphysicsLinearmathTransform;
  private FloatBuffer jdField_field_98_of_type_JavaNioFloatBuffer;
  private float[] jdField_field_98_of_type_ArrayOfFloat;
  private boolean jdField_field_98_of_type_Boolean;
  private Mesh jdField_field_98_of_type_OrgSchemaSchineGraphicsengineFormsMesh;
  
  public class_134(class_371 paramclass_371)
  {
    new Transform();
    new Vector3f();
    this.jdField_field_98_of_type_Float = 0.0F;
    this.jdField_field_98_of_type_JavaxVecmathMatrix3f = new Matrix3f();
    this.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform = new Transform();
    this.jdField_field_98_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(16);
    this.jdField_field_98_of_type_ArrayOfFloat = new float[16];
    this.jdField_field_98_of_type_Class_371 = paramclass_371;
  }
  
  public final void a() {}
  
  public final void b()
  {
    if (!this.jdField_field_98_of_type_Boolean) {
      c();
    }
    try
    {
      class_747 localclass_747;
      if ((localclass_747 = this.jdField_field_98_of_type_Class_371.a25()) != null)
      {
        Matrix4f localMatrix4f = class_969.field_1259;
        this.jdField_field_98_of_type_JavaNioFloatBuffer.rewind();
        localMatrix4f.store(this.jdField_field_98_of_type_JavaNioFloatBuffer);
        this.jdField_field_98_of_type_JavaNioFloatBuffer.rewind();
        this.jdField_field_98_of_type_JavaNioFloatBuffer.get(this.jdField_field_98_of_type_ArrayOfFloat);
        this.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform.setFromOpenGLMatrix(this.jdField_field_98_of_type_ArrayOfFloat);
        this.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform.origin.set(0.0F, 0.0F, 0.0F);
        class_1363.j();
        GlUtil.d1();
        GlUtil.c4(class_933.b() / 2, 105.0F, 0.0F);
        GlUtil.b5(10.0F, -10.0F, 10.0F);
        this.jdField_field_98_of_type_JavaxVecmathMatrix3f.set(localclass_747.getWorldTransform().basis);
        this.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform.basis.mul(this.jdField_field_98_of_type_JavaxVecmathMatrix3f);
        GlUtil.b3(this.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform);
        GL11.glDisable(2896);
        GL11.glDisable(2929);
        GL11.glEnable(2977);
        GL11.glDisable(2884);
        GL11.glEnable(2929);
        this.jdField_field_98_of_type_OrgSchemaSchineGraphicsengineFormsMesh.a153().a2();
        this.jdField_field_98_of_type_OrgSchemaSchineGraphicsengineFormsMesh.e();
        this.jdField_field_98_of_type_OrgSchemaSchineGraphicsengineFormsMesh.a153().c();
        GlUtil.c2();
        class_1363.h1();
        this.jdField_field_98_of_type_Float += 0.15F;
        GL11.glEnable(2896);
        GL11.glDisable(2977);
        GL11.glEnable(2929);
        GL15.glBindBuffer(34962, 0);
      }
      return;
    }
    catch (NullPointerException localNullPointerException)
    {
      System.err.println("EXCPETION HAS BEEN HANDLED");
      localNullPointerException.printStackTrace();
    }
  }
  
  public final void c()
  {
    this.jdField_field_98_of_type_OrgSchemaSchineGraphicsengineFormsMesh = ((Mesh)class_969.a2().a4("Arrow").a152().get(0));
    this.jdField_field_98_of_type_Boolean = true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_134
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */