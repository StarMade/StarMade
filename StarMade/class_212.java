import com.bulletphysics.linearmath.Transform;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3f;
import org.schema.game.common.controller.SegmentController;
import org.schema.schine.graphicsengine.camera.Camera;

public class class_212
  implements Comparable
{
  private static final Vector3f jdField_field_633_of_type_JavaxVecmathVector3f = new Vector3f();
  private static final Vector3f field_634 = new Vector3f();
  private SegmentController jdField_field_633_of_type_OrgSchemaGameCommonControllerSegmentController;
  private class_48 jdField_field_633_of_type_Class_48;
  private static Vector3f field_635 = new Vector3f();
  private static Transform jdField_field_633_of_type_ComBulletphysicsLinearmathTransform = new Transform();
  private static Vector3f field_636 = new Vector3f(-50.0F, 0.0F, 0.0F);
  
  public final int a(class_212 paramclass_212)
  {
    if (paramclass_212 == null) {
      return 1;
    }
    if (paramclass_212.hashCode() == hashCode()) {
      return 0;
    }
    a1(jdField_field_633_of_type_ComBulletphysicsLinearmathTransform, field_636);
    jdField_field_633_of_type_JavaxVecmathVector3f.set(jdField_field_633_of_type_ComBulletphysicsLinearmathTransform.origin);
    paramclass_212.a1(jdField_field_633_of_type_ComBulletphysicsLinearmathTransform, field_636);
    field_634.set(jdField_field_633_of_type_ComBulletphysicsLinearmathTransform.origin);
    jdField_field_633_of_type_JavaxVecmathVector3f.sub(class_969.a1().a83());
    field_634.sub(class_969.a1().a83());
    if ((paramclass_212 = (int)(jdField_field_633_of_type_JavaxVecmathVector3f.length() * 10000.0F - field_634.length() * 10000.0F)) == 0) {
      return 1;
    }
    return paramclass_212;
  }
  
  public boolean equals(Object paramObject)
  {
    return (this.jdField_field_633_of_type_Class_48.equals(((class_212)paramObject).jdField_field_633_of_type_Class_48)) && (this.jdField_field_633_of_type_OrgSchemaGameCommonControllerSegmentController == ((class_212)paramObject).jdField_field_633_of_type_OrgSchemaGameCommonControllerSegmentController);
  }
  
  public final void a1(Transform paramTransform, Vector3f paramVector3f)
  {
    jdField_field_633_of_type_ComBulletphysicsLinearmathTransform.set(this.jdField_field_633_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransformClient());
    field_635.set(this.jdField_field_633_of_type_Class_48.field_475 - 8 + paramVector3f.field_615, this.jdField_field_633_of_type_Class_48.field_476 - 8 + paramVector3f.field_616, this.jdField_field_633_of_type_Class_48.field_477 - 8 + paramVector3f.field_617);
    jdField_field_633_of_type_ComBulletphysicsLinearmathTransform.basis.transform(field_635);
    jdField_field_633_of_type_ComBulletphysicsLinearmathTransform.origin.add(field_635);
    paramTransform.set(jdField_field_633_of_type_ComBulletphysicsLinearmathTransform);
  }
  
  public int hashCode()
  {
    return this.jdField_field_633_of_type_Class_48.hashCode() + this.jdField_field_633_of_type_OrgSchemaGameCommonControllerSegmentController.hashCode();
  }
  
  public final void a2(SegmentController paramSegmentController, class_48 paramclass_48)
  {
    this.jdField_field_633_of_type_OrgSchemaGameCommonControllerSegmentController = paramSegmentController;
    this.jdField_field_633_of_type_Class_48 = paramclass_48;
  }
  
  public final void a3()
  {
    this.jdField_field_633_of_type_OrgSchemaGameCommonControllerSegmentController = null;
    this.jdField_field_633_of_type_Class_48 = null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_212
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */