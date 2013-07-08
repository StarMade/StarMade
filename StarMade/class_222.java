import com.bulletphysics.linearmath.Transform;
import java.util.Observable;
import javax.vecmath.Vector3f;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.ShieldContainerInterface;
import org.schema.game.common.controller.elements.shield.ShieldCollectionManager;

public final class class_222
  extends Observable
  implements class_965
{
  SegmentController jdField_field_98_of_type_OrgSchemaGameCommonControllerSegmentController;
  private ShieldCollectionManager jdField_field_98_of_type_OrgSchemaGameCommonControllerElementsShieldShieldCollectionManager;
  private Vector3f jdField_field_98_of_type_JavaxVecmathVector3f = new Vector3f();
  private class_838 jdField_field_98_of_type_Class_838;
  private Transform jdField_field_98_of_type_ComBulletphysicsLinearmathTransform = new Transform();
  private Transform field_106 = new Transform();
  private float jdField_field_98_of_type_Float;
  
  public class_222(class_798 paramclass_798)
  {
    a2(paramclass_798);
    this.jdField_field_98_of_type_Class_838 = new class_838();
  }
  
  public final void a4(Vector3f paramVector3f)
  {
    if (!class_949.field_1195.b1()) {
      return;
    }
    this.jdField_field_98_of_type_JavaxVecmathVector3f.set(paramVector3f);
    this.jdField_field_98_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransformInverse().transform(this.jdField_field_98_of_type_JavaxVecmathVector3f);
    this.jdField_field_98_of_type_Class_838.a3(this.jdField_field_98_of_type_JavaxVecmathVector3f);
    if (this.jdField_field_98_of_type_Class_838.a4() > 0)
    {
      setChanged();
      notifyObservers(Boolean.valueOf(true));
    }
  }
  
  public final void a() {}
  
  public final void b()
  {
    if (!class_949.field_1195.b1()) {}
  }
  
  public final void c()
  {
    if (!class_949.field_1195.b1()) {}
  }
  
  public final void a2(class_798 paramclass_798)
  {
    this.jdField_field_98_of_type_OrgSchemaGameCommonControllerSegmentController = paramclass_798.a1();
    this.jdField_field_98_of_type_OrgSchemaGameCommonControllerElementsShieldShieldCollectionManager = ((ShieldContainerInterface)paramclass_798.a()).getShieldManager();
  }
  
  public final void a1(class_941 paramclass_941)
  {
    if (!class_949.field_1195.b1()) {
      return;
    }
    this.jdField_field_98_of_type_Class_838.a5(paramclass_941);
    if (this.jdField_field_98_of_type_Class_838.a4() <= 0)
    {
      setChanged();
      notifyObservers(Boolean.valueOf(false));
    }
    this.jdField_field_98_of_type_Float = ((float)this.jdField_field_98_of_type_OrgSchemaGameCommonControllerElementsShieldShieldCollectionManager.getShields() / (float)this.jdField_field_98_of_type_OrgSchemaGameCommonControllerElementsShieldShieldCollectionManager.getShieldCapabilityHP());
  }
  
  public final boolean a5()
  {
    return this.jdField_field_98_of_type_Class_838.a4() > 0;
  }
  
  public final class_838 a6()
  {
    return this.jdField_field_98_of_type_Class_838;
  }
  
  public final float a7()
  {
    return this.jdField_field_98_of_type_Float;
  }
  
  public final void d()
  {
    this.jdField_field_98_of_type_OrgSchemaGameCommonControllerSegmentController = null;
    this.jdField_field_98_of_type_OrgSchemaGameCommonControllerElementsShieldShieldCollectionManager = null;
    this.jdField_field_98_of_type_JavaxVecmathVector3f.set(0.0F, 0.0F, 0.0F);
    this.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
    this.field_106.setIdentity();
    this.jdField_field_98_of_type_Class_838.a2();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_222
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */