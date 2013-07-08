import com.bulletphysics.collision.shapes.BoxShape;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.linearmath.Transform;
import java.util.ArrayList;
import java.util.List;
import javax.vecmath.Vector3f;
import org.schema.game.common.controller.elements.lift.LiftUnit;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.graphicsengine.forms.Mesh;

public final class class_247
  implements class_965
{
  public final ArrayList field_98;
  private boolean jdField_field_98_of_type_Boolean;
  private Mesh jdField_field_98_of_type_OrgSchemaSchineGraphicsengineFormsMesh;
  private Transform jdField_field_98_of_type_ComBulletphysicsLinearmathTransform = new Transform();
  private Vector3f jdField_field_98_of_type_JavaxVecmathVector3f = new Vector3f();
  private class_1393 jdField_field_98_of_type_Class_1393;
  
  public class_247()
  {
    this.jdField_field_98_of_type_JavaUtilArrayList = new ArrayList();
  }
  
  public final void a() {}
  
  public final void b()
  {
    if (!this.jdField_field_98_of_type_Boolean) {
      c();
    }
    for (int i = 0; i < this.jdField_field_98_of_type_JavaUtilArrayList.size(); i++) {
      if (!((LiftUnit)this.jdField_field_98_of_type_JavaUtilArrayList.get(i)).isActive())
      {
        this.jdField_field_98_of_type_JavaUtilArrayList.remove(i);
        i--;
      }
      else
      {
        LiftUnit localLiftUnit = (LiftUnit)this.jdField_field_98_of_type_JavaUtilArrayList.get(i);
        class_1376.field_1574.c();
        this.jdField_field_98_of_type_Class_1393.a2();
        if (localLiftUnit.getBody() != null)
        {
          localLiftUnit.getBody().getWorldTransform(this.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform);
          GlUtil.d1();
          GlUtil.b3(this.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform);
          ((BoxShape)localLiftUnit.getBody().getCollisionShape()).getHalfExtentsWithoutMargin(this.jdField_field_98_of_type_JavaxVecmathVector3f);
          GlUtil.b5(this.jdField_field_98_of_type_JavaxVecmathVector3f.field_615 * 2.0F, this.jdField_field_98_of_type_JavaxVecmathVector3f.field_616 * 2.0F, this.jdField_field_98_of_type_JavaxVecmathVector3f.field_617 * 2.0F);
          this.jdField_field_98_of_type_OrgSchemaSchineGraphicsengineFormsMesh.b();
          GlUtil.c2();
        }
        this.jdField_field_98_of_type_Class_1393.c();
        class_1377.e();
      }
    }
  }
  
  public final void c()
  {
    this.jdField_field_98_of_type_OrgSchemaSchineGraphicsengineFormsMesh = ((Mesh)class_969.a2().a4("Box").a152().get(0));
    this.jdField_field_98_of_type_Class_1393 = new class_1393();
    this.jdField_field_98_of_type_Class_1393.f();
    this.jdField_field_98_of_type_Class_1393.c1(new float[] { 1.3F, 1.3F, 1.3F, 1.0F });
    this.jdField_field_98_of_type_Boolean = true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_247
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */