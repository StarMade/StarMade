import com.bulletphysics.linearmath.Transform;
import javax.vecmath.Matrix4f;
import org.schema.schine.graphicsengine.camera.Camera;

public final class class_1008
  extends Camera
{
  private final Camera jdField_field_89_of_type_OrgSchemaSchineGraphicsengineCameraCamera;
  private final Camera jdField_field_90_of_type_OrgSchemaSchineGraphicsengineCameraCamera;
  private Matrix4f jdField_field_89_of_type_JavaxVecmathMatrix4f = new Matrix4f();
  private Matrix4f jdField_field_90_of_type_JavaxVecmathMatrix4f = new Matrix4f();
  private Matrix4f field_92 = new Matrix4f();
  private float field_93 = 0.0F;
  
  public class_1008(Camera paramCamera1, Camera paramCamera2)
  {
    super(paramCamera1.a184());
    this.jdField_field_89_of_type_OrgSchemaSchineGraphicsengineCameraCamera = paramCamera1;
    this.jdField_field_90_of_type_OrgSchemaSchineGraphicsengineCameraCamera = paramCamera2;
  }
  
  public final void a12(class_941 paramclass_941)
  {
    this.field_93 += paramclass_941.a();
    paramclass_941 = this.field_93 / 0.2F;
    this.jdField_field_89_of_type_OrgSchemaSchineGraphicsengineCameraCamera.getWorldTransform().getMatrix(this.jdField_field_89_of_type_JavaxVecmathMatrix4f);
    this.jdField_field_90_of_type_OrgSchemaSchineGraphicsengineCameraCamera.getWorldTransform().getMatrix(this.jdField_field_90_of_type_JavaxVecmathMatrix4f);
    this.field_92.sub(this.jdField_field_90_of_type_JavaxVecmathMatrix4f, this.jdField_field_89_of_type_JavaxVecmathMatrix4f);
    this.field_92.mul(paramclass_941);
    this.jdField_field_89_of_type_JavaxVecmathMatrix4f.add(this.field_92);
    getWorldTransform().set(this.jdField_field_89_of_type_JavaxVecmathMatrix4f);
  }
  
  public final boolean a4()
  {
    return this.field_93 < 0.2F;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1008
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */