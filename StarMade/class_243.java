import com.bulletphysics.linearmath.Transform;
import javax.vecmath.Vector3f;
import org.schema.game.common.controller.SegmentController;
import org.schema.schine.graphicsengine.camera.Camera;

public final class class_243
  extends Camera
  implements class_197, class_954
{
  private SegmentController jdField_field_89_of_type_OrgSchemaGameCommonControllerSegmentController;
  private class_1008 jdField_field_89_of_type_Class_1008;
  private Camera jdField_field_89_of_type_OrgSchemaSchineGraphicsengineCameraCamera;
  
  public class_243(Camera paramCamera, class_457 paramclass_457)
  {
    super(new class_199(paramclass_457));
    this.jdField_field_89_of_type_OrgSchemaGameCommonControllerSegmentController = paramclass_457.a1();
    this.field_89 = new class_1006(this);
    this.jdField_field_89_of_type_OrgSchemaSchineGraphicsengineCameraCamera = paramCamera;
  }
  
  public final Vector3f b9()
  {
    return ((class_199)a184()).b9();
  }
  
  public final void handleKeyEvent()
  {
    ((class_199)a184()).handleKeyEvent();
  }
  
  public final void a84(class_48 paramclass_48)
  {
    ((class_199)a184()).a84(new class_48(paramclass_48));
  }
  
  public final void a12(class_941 paramclass_941)
  {
    if (!class_367.field_758.a6())
    {
      ((class_1006)this.field_89).field_123.set(this.jdField_field_89_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransform());
      super.a12(paramclass_941);
    }
    if ((this.jdField_field_89_of_type_Class_1008 != null) && (this.jdField_field_89_of_type_Class_1008.a4()))
    {
      this.jdField_field_89_of_type_Class_1008.a12(paramclass_941);
      getWorldTransform().set(this.jdField_field_89_of_type_Class_1008.getWorldTransform());
    }
    if (this.jdField_field_89_of_type_OrgSchemaSchineGraphicsengineCameraCamera != null)
    {
      a78(this.jdField_field_89_of_type_OrgSchemaSchineGraphicsengineCameraCamera);
      this.jdField_field_89_of_type_OrgSchemaSchineGraphicsengineCameraCamera = null;
    }
  }
  
  public final void a78(Camera paramCamera)
  {
    this.jdField_field_89_of_type_Class_1008 = new class_1008(paramCamera, this);
  }
  
  public final SegmentController a79()
  {
    return this.jdField_field_89_of_type_OrgSchemaGameCommonControllerSegmentController;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_243
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */