import javax.vecmath.Quat4f;
import javax.vecmath.Vector2f;
import javax.vecmath.Vector3f;
import org.schema.common.FastMath;
import org.schema.schine.graphicsengine.camera.Camera;

public final class class_1012
  implements class_1010
{
  private Camera jdField_field_123_of_type_OrgSchemaSchineGraphicsengineCameraCamera;
  private Vector3f jdField_field_123_of_type_JavaxVecmathVector3f = new Vector3f();
  private Vector3f jdField_field_122_of_type_JavaxVecmathVector3f = new Vector3f();
  private Vector2f jdField_field_123_of_type_JavaxVecmathVector2f = new Vector2f();
  private Vector2f jdField_field_122_of_type_JavaxVecmathVector2f = new Vector2f();
  private Quat4f jdField_field_123_of_type_JavaxVecmathQuat4f = new Quat4f();
  private Quat4f jdField_field_122_of_type_JavaxVecmathQuat4f = new Quat4f();
  private Quat4f field_124 = new Quat4f();
  private Quat4f field_125 = new Quat4f();
  private Quat4f field_126 = new Quat4f(0.0F, 0.0F, 0.0F, 1.0F);
  private Quat4f field_127 = new Quat4f();
  
  public class_1012(Camera paramCamera)
  {
    this.jdField_field_123_of_type_OrgSchemaSchineGraphicsengineCameraCamera = paramCamera;
  }
  
  public final void a1(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6)
  {
    this.jdField_field_123_of_type_JavaxVecmathVector2f.field_577 = (-paramFloat1 * paramFloat4);
    this.jdField_field_123_of_type_JavaxVecmathVector2f.field_578 = ((class_949.field_1244.b1() ? -paramFloat2 : paramFloat2) * paramFloat5);
    this.jdField_field_122_of_type_JavaxVecmathVector2f.add(this.jdField_field_123_of_type_JavaxVecmathVector2f);
    if (this.jdField_field_122_of_type_JavaxVecmathVector2f.field_578 > 1.570796F)
    {
      this.jdField_field_122_of_type_JavaxVecmathVector2f.field_578 -= this.jdField_field_123_of_type_JavaxVecmathVector2f.field_578;
      this.jdField_field_123_of_type_JavaxVecmathVector2f.field_578 = (1.570796F - this.jdField_field_122_of_type_JavaxVecmathVector2f.field_578);
      this.jdField_field_122_of_type_JavaxVecmathVector2f.field_578 = 1.570796F;
    }
    if (this.jdField_field_122_of_type_JavaxVecmathVector2f.field_578 < -1.570796F)
    {
      this.jdField_field_122_of_type_JavaxVecmathVector2f.field_578 -= this.jdField_field_123_of_type_JavaxVecmathVector2f.field_578;
      this.jdField_field_123_of_type_JavaxVecmathVector2f.field_578 = (-(1.570796F - Math.abs(this.jdField_field_122_of_type_JavaxVecmathVector2f.field_578)));
      this.jdField_field_122_of_type_JavaxVecmathVector2f.field_578 = -1.570796F;
    }
    paramFloat1 = new Vector3f(this.jdField_field_123_of_type_OrgSchemaSchineGraphicsengineCameraCamera.c10());
    paramFloat2 = new Vector3f(this.jdField_field_123_of_type_OrgSchemaSchineGraphicsengineCameraCamera.f5());
    paramFloat3 = new Vector3f(this.jdField_field_123_of_type_OrgSchemaSchineGraphicsengineCameraCamera.e7());
    if (this.jdField_field_123_of_type_JavaxVecmathVector2f.field_578 != 0.0F)
    {
      this.jdField_field_123_of_type_JavaxVecmathVector3f.cross(this.jdField_field_123_of_type_OrgSchemaSchineGraphicsengineCameraCamera.c10(), this.jdField_field_123_of_type_OrgSchemaSchineGraphicsengineCameraCamera.f5());
      this.jdField_field_122_of_type_JavaxVecmathVector3f.set(this.jdField_field_123_of_type_JavaxVecmathVector3f);
      this.jdField_field_122_of_type_JavaxVecmathVector3f.normalize();
      a3(this.jdField_field_123_of_type_JavaxVecmathVector2f.field_578, this.jdField_field_122_of_type_JavaxVecmathVector3f, paramFloat1, paramFloat2, paramFloat3);
    }
    a3(this.jdField_field_123_of_type_JavaxVecmathVector2f.field_577, new Vector3f(0.0F, 1.0F, 0.0F), paramFloat1, paramFloat2, paramFloat3);
    this.jdField_field_123_of_type_OrgSchemaSchineGraphicsengineCameraCamera.a159(paramFloat1);
    this.jdField_field_123_of_type_OrgSchemaSchineGraphicsengineCameraCamera.c12(paramFloat2);
    this.jdField_field_123_of_type_OrgSchemaSchineGraphicsengineCameraCamera.b24(paramFloat3);
  }
  
  private void a2(Vector3f paramVector3f)
  {
    this.field_127.set(paramVector3f.field_615, paramVector3f.field_616, paramVector3f.field_617, 0.0F);
    this.jdField_field_122_of_type_JavaxVecmathQuat4f.mul(this.field_125, this.field_127);
    this.jdField_field_123_of_type_JavaxVecmathQuat4f.mul(this.jdField_field_122_of_type_JavaxVecmathQuat4f, this.field_124);
    paramVector3f.set(this.jdField_field_123_of_type_JavaxVecmathQuat4f.field_596, this.jdField_field_123_of_type_JavaxVecmathQuat4f.field_597, this.jdField_field_123_of_type_JavaxVecmathQuat4f.field_598);
  }
  
  private void a3(float paramFloat, Vector3f paramVector3f1, Vector3f paramVector3f2, Vector3f paramVector3f3, Vector3f paramVector3f4)
  {
    this.field_125.field_596 = (paramVector3f1.field_615 * FastMath.h(paramFloat / 2.0F));
    this.field_125.field_597 = (paramVector3f1.field_616 * FastMath.h(paramFloat / 2.0F));
    this.field_125.field_598 = (paramVector3f1.field_617 * FastMath.h(paramFloat / 2.0F));
    this.field_125.field_599 = FastMath.d(paramFloat / 2.0F);
    this.field_124.conjugate(this.field_125);
    a2(paramVector3f2);
    a2(paramVector3f3);
    a2(paramVector3f4);
    this.field_126.mul(this.field_125);
  }
  
  public final void a6() {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1012
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */