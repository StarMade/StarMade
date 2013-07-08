import com.bulletphysics.linearmath.Transform;
import java.io.PrintStream;
import javax.vecmath.Matrix3f;
import org.lwjgl.input.Keyboard;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.element.ElementDocking;
import org.schema.game.common.data.world.Segment;
import org.schema.schine.graphicsengine.camera.Camera;

final class class_193
  extends Camera
{
  private class_298 jdField_field_89_of_type_Class_298;
  private class_301 jdField_field_89_of_type_Class_301;
  
  public class_193(class_191 paramclass_191, class_962 paramclass_962)
  {
    super(paramclass_962);
    this.jdField_field_89_of_type_Class_301 = new class_301(this, paramclass_191.jdField_field_89_of_type_OrgSchemaGameCommonControllerSegmentController);
    this.field_89 = this.jdField_field_89_of_type_Class_301;
    this.field_92 = 0.2F;
    this.field_90 = 0.2F;
    this.jdField_field_89_of_type_Class_298 = new class_298(this, paramclass_191.jdField_field_89_of_type_OrgSchemaGameCommonControllerSegmentController);
  }
  
  public final void a2()
  {
    super.a2();
    getWorldTransform().basis.set(this.jdField_field_89_of_type_Class_191.jdField_field_89_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransform().basis);
  }
  
  public final void a12(class_941 paramclass_941)
  {
    if (this.jdField_field_89_of_type_Class_191.jdField_field_89_of_type_OrgSchemaGameCommonControllerSegmentController.getDockingController().a4() != null)
    {
      if ((this.jdField_field_89_of_type_Class_298.jdField_field_123_of_type_Int != org.schema.game.common.data.element.Element.orientationBackMapping[this.jdField_field_89_of_type_Class_191.jdField_field_89_of_type_OrgSchemaGameCommonControllerSegmentController.getDockingController().a4().field_1740.b1()]) || (this.jdField_field_89_of_type_Class_298.jdField_field_123_of_type_Class_1382 != this.jdField_field_89_of_type_Class_191.jdField_field_89_of_type_OrgSchemaGameCommonControllerSegmentController.getDockingController().a4().field_1740.a7().a15()))
      {
        System.err.println("NEW LOOKING ALGO " + this.jdField_field_89_of_type_Class_298.jdField_field_123_of_type_Int + " / " + org.schema.game.common.data.element.Element.orientationBackMapping[this.jdField_field_89_of_type_Class_191.jdField_field_89_of_type_OrgSchemaGameCommonControllerSegmentController.getDockingController().a4().field_1740.b1()] + "; " + this.jdField_field_89_of_type_Class_298.jdField_field_123_of_type_Class_1382 + " / " + this.jdField_field_89_of_type_Class_191.jdField_field_89_of_type_OrgSchemaGameCommonControllerSegmentController.getDockingController().a4().field_1740.a7().a15());
        this.jdField_field_89_of_type_Class_298 = new class_298(this, this.jdField_field_89_of_type_Class_191.jdField_field_89_of_type_OrgSchemaGameCommonControllerSegmentController);
      }
      this.jdField_field_89_of_type_Class_298.jdField_field_123_of_type_Boolean = this.jdField_field_89_of_type_Class_191.field_90;
      this.jdField_field_89_of_type_Class_298.jdField_field_123_of_type_ComBulletphysicsLinearmathTransform.set(this.jdField_field_89_of_type_Class_191.jdField_field_89_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransform());
      this.jdField_field_89_of_type_Class_298.a4(this.jdField_field_89_of_type_Class_191.jdField_field_89_of_type_OrgSchemaGameCommonControllerSegmentController.getDockingController().a4().field_1740.a7().a15());
      this.jdField_field_89_of_type_Class_298.a5(org.schema.game.common.data.element.Element.orientationBackMapping[this.jdField_field_89_of_type_Class_191.jdField_field_89_of_type_OrgSchemaGameCommonControllerSegmentController.getDockingController().a4().field_1740.b1()]);
      this.field_89 = this.jdField_field_89_of_type_Class_298;
    }
    else
    {
      this.field_89 = this.jdField_field_89_of_type_Class_301;
      ((class_301)this.field_89).jdField_field_123_of_type_Boolean = this.jdField_field_89_of_type_Class_191.field_90;
      ((class_301)this.field_89).jdField_field_123_of_type_ComBulletphysicsLinearmathTransform.set(this.jdField_field_89_of_type_Class_191.jdField_field_89_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransform());
      ((class_301)this.field_89).jdField_field_123_of_type_Int = this.jdField_field_89_of_type_Class_191.jdField_field_89_of_type_Int;
    }
    if (class_1046.a1())
    {
      e();
      if (class_367.field_723.a6()) {
        this.field_89.a1(0.0F, 0.0F, -0.03F, 0.0F, b1(), a3());
      }
      if (class_367.field_724.a6()) {
        this.field_89.a1(0.0F, 0.0F, 0.03F, 0.0F, b1(), a3());
      }
      if (Keyboard.isKeyDown(29)) {
        this.field_89.a1(0.0F, this.jdField_field_89_of_type_Class_1046.field_1308 / (class_933.a() / 2), this.jdField_field_89_of_type_Class_1046.field_1307 / (class_933.b() / 2), 0.0F, b1(), a3());
      } else {
        this.field_89.a1(this.jdField_field_89_of_type_Class_1046.field_1307 / (class_933.b() / 2), ((class_949.field_1243.b1()) || (class_949.field_1244.b1()) ? -this.jdField_field_89_of_type_Class_1046.field_1308 : this.jdField_field_89_of_type_Class_1046.field_1308) / (class_933.a() / 2), 0.0F, a3(), b1(), 0.0F);
      }
      if (this.jdField_field_89_of_type_Class_191.jdField_field_89_of_type_Boolean)
      {
        this.jdField_field_89_of_type_Class_191.jdField_field_89_of_type_Class_193.getWorldTransform().basis.setIdentity();
        this.field_89.a6();
        this.jdField_field_89_of_type_Class_191.jdField_field_89_of_type_Boolean = false;
      }
      b32(paramclass_941);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_193
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */