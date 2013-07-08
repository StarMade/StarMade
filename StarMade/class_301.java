import com.bulletphysics.linearmath.Transform;
import javax.vecmath.Matrix3f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;
import org.schema.common.FastMath;
import org.schema.schine.graphicsengine.camera.Camera;
import org.schema.schine.graphicsengine.core.GlUtil;

public final class class_301
  implements class_1010
{
  private Camera jdField_field_123_of_type_OrgSchemaSchineGraphicsengineCameraCamera;
  private Vector3f jdField_field_123_of_type_JavaxVecmathVector3f = new Vector3f();
  private Quat4f jdField_field_123_of_type_JavaxVecmathQuat4f = new Quat4f(0.0F, 0.0F, 0.0F, 1.0F);
  boolean jdField_field_123_of_type_Boolean;
  int jdField_field_123_of_type_Int = 4;
  private class_1382 jdField_field_123_of_type_Class_1382;
  private final Transform jdField_field_122_of_type_ComBulletphysicsLinearmathTransform = new Transform();
  final Transform jdField_field_123_of_type_ComBulletphysicsLinearmathTransform = new Transform();
  private final Transform field_124 = new Transform();
  private final Transform field_125 = new Transform();
  private float jdField_field_123_of_type_Float = 0.7853982F;
  private int jdField_field_122_of_type_Int = 0;
  private boolean jdField_field_122_of_type_Boolean = true;
  
  public class_301(Camera paramCamera, class_1382 paramclass_1382)
  {
    this.jdField_field_123_of_type_OrgSchemaSchineGraphicsengineCameraCamera = paramCamera;
    this.jdField_field_123_of_type_Class_1382 = paramclass_1382;
    this.jdField_field_123_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
    this.field_124.setIdentity();
    this.jdField_field_122_of_type_ComBulletphysicsLinearmathTransform.set(paramclass_1382.getWorldTransform());
  }
  
  public final void a6()
  {
    this.jdField_field_123_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
    this.field_124.setIdentity();
    this.jdField_field_122_of_type_ComBulletphysicsLinearmathTransform.set(this.jdField_field_123_of_type_Class_1382.getWorldTransform());
  }
  
  private void a(Vector3f paramVector3f1, Vector3f paramVector3f2, Vector3f paramVector3f3, Transform paramTransform)
  {
    switch (this.jdField_field_123_of_type_Int)
    {
    case 4: 
      GlUtil.c(paramVector3f1, paramTransform);
      GlUtil.f(paramVector3f2, paramTransform);
      GlUtil.e(paramVector3f3, paramTransform);
      return;
    case 5: 
      GlUtil.c(paramVector3f1, paramTransform);
      GlUtil.f(paramVector3f2, paramTransform);
      GlUtil.e(paramVector3f3, paramTransform);
      paramVector3f1.negate();
      paramVector3f3.negate();
      return;
    case 1: 
      GlUtil.e(paramVector3f1, paramTransform);
      GlUtil.f(paramVector3f2, paramTransform);
      GlUtil.c(paramVector3f3, paramTransform);
      return;
    case 0: 
      GlUtil.d(paramVector3f1, paramTransform);
      GlUtil.f(paramVector3f2, paramTransform);
      GlUtil.c(paramVector3f3, paramTransform);
      return;
    case 2: 
      GlUtil.f(paramVector3f1, paramTransform);
      GlUtil.e(paramVector3f2, paramTransform);
      GlUtil.c(paramVector3f3, paramTransform);
      return;
    case 3: 
      GlUtil.b(paramVector3f1, paramTransform);
      GlUtil.d(paramVector3f2, paramTransform);
      GlUtil.c(paramVector3f3, paramTransform);
    }
  }
  
  public final void a1(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6)
  {
    this.field_125.set(this.jdField_field_123_of_type_OrgSchemaSchineGraphicsengineCameraCamera.getWorldTransform());
    this.jdField_field_122_of_type_Int = 0;
    this.jdField_field_123_of_type_JavaxVecmathVector3f.field_615 = (-paramFloat1 * paramFloat4);
    this.jdField_field_123_of_type_JavaxVecmathVector3f.field_616 = (paramFloat2 * paramFloat5);
    this.jdField_field_123_of_type_JavaxVecmathVector3f.field_617 = (paramFloat3 * paramFloat6);
    paramFloat1 = this;
    for (paramFloat2 = this;; paramFloat2 = paramFloat3)
    {
      paramFloat3 = new Vector3f();
      paramFloat4 = new Vector3f();
      paramFloat5 = new Vector3f();
      paramFloat2.a(paramFloat3, paramFloat4, paramFloat5, paramFloat2.jdField_field_123_of_type_Class_1382.getWorldTransform());
      if (paramFloat2.jdField_field_122_of_type_Boolean)
      {
        paramFloat2.jdField_field_123_of_type_OrgSchemaSchineGraphicsengineCameraCamera.a159(paramFloat3);
        paramFloat2.jdField_field_123_of_type_OrgSchemaSchineGraphicsengineCameraCamera.c12(paramFloat4);
        paramFloat2.jdField_field_123_of_type_OrgSchemaSchineGraphicsengineCameraCamera.b24(paramFloat5);
        paramFloat2.jdField_field_122_of_type_Boolean = false;
        paramFloat2.field_124.set(paramFloat2.jdField_field_123_of_type_OrgSchemaSchineGraphicsengineCameraCamera.getWorldTransform());
      }
      paramFloat2.jdField_field_123_of_type_OrgSchemaSchineGraphicsengineCameraCamera.getWorldTransform().set(paramFloat2.field_124);
      (paramFloat6 = new Vector3f()).cross(paramFloat3, paramFloat4);
      (paramFloat6 = new Vector3f(paramFloat6)).normalize();
      float f1;
      if ((f1 = paramFloat2.jdField_field_123_of_type_JavaxVecmathVector3f.field_616) > 1.0F) {
        f1 = 1.0F;
      }
      if (f1 < -1.0F) {
        f1 = -1.0F;
      }
      if (paramFloat2.jdField_field_123_of_type_JavaxVecmathVector3f.field_616 == 0.0F) {
        localObject1 = new Quat4f(0.0F, 0.0F, 0.0F, 1.0F);
      } else {
        localObject1 = new Quat4f(paramFloat6.field_615 * FastMath.h(localObject1), paramFloat6.field_616 * FastMath.h(localObject1), paramFloat6.field_617 * FastMath.h(localObject1), FastMath.d(localObject1));
      }
      (paramFloat6 = new Vector3f(paramFloat4)).normalize();
      float f2;
      if ((f2 = paramFloat2.jdField_field_123_of_type_JavaxVecmathVector3f.field_615) > 1.0F) {
        f2 = 1.0F;
      }
      if (f2 < -1.0F) {
        f2 = -1.0F;
      }
      if (paramFloat2.jdField_field_123_of_type_JavaxVecmathVector3f.field_615 == 0.0F) {
        localObject2 = new Quat4f(0.0F, 0.0F, 0.0F, 1.0F);
      } else {
        localObject2 = new Quat4f(paramFloat6.field_615 * FastMath.h(localObject2), paramFloat6.field_616 * FastMath.h(localObject2), paramFloat6.field_617 * FastMath.h(localObject2), FastMath.d(localObject2));
      }
      (paramFloat6 = new Vector3f(paramFloat3)).normalize();
      float f3;
      if ((f3 = paramFloat2.jdField_field_123_of_type_JavaxVecmathVector3f.field_617) > 1.0F) {
        f3 = 1.0F;
      }
      if (f3 < -1.0F) {
        f3 = -1.0F;
      }
      if (paramFloat2.jdField_field_123_of_type_JavaxVecmathVector3f.field_617 == 0.0F) {
        paramFloat6 = new Quat4f(0.0F, 0.0F, 0.0F, 1.0F);
      } else {
        paramFloat6 = new Quat4f(paramFloat6.field_615 * FastMath.h(f3), paramFloat6.field_616 * FastMath.h(f3), paramFloat6.field_617 * FastMath.h(f3), FastMath.d(f3));
      }
      paramFloat2.jdField_field_123_of_type_JavaxVecmathQuat4f.mul((Quat4f)localObject1, (Quat4f)localObject2);
      paramFloat2.jdField_field_123_of_type_JavaxVecmathQuat4f.mul(paramFloat6);
      paramFloat2.jdField_field_123_of_type_JavaxVecmathQuat4f.normalize();
      (paramFloat6 = new Quat4f()).conjugate(paramFloat2.jdField_field_123_of_type_JavaxVecmathQuat4f);
      Object localObject1 = new Quat4f(paramFloat2.jdField_field_123_of_type_OrgSchemaSchineGraphicsengineCameraCamera.c10().field_615, paramFloat2.jdField_field_123_of_type_OrgSchemaSchineGraphicsengineCameraCamera.c10().field_616, paramFloat2.jdField_field_123_of_type_OrgSchemaSchineGraphicsengineCameraCamera.c10().field_617, 0.0F);
      (localObject2 = new Quat4f()).mul(paramFloat2.jdField_field_123_of_type_JavaxVecmathQuat4f, (Quat4f)localObject1);
      ((Quat4f)localObject2).mul(paramFloat6);
      paramFloat3.field_615 = ((Quat4f)localObject2).field_596;
      paramFloat3.field_616 = ((Quat4f)localObject2).field_597;
      paramFloat3.field_617 = ((Quat4f)localObject2).field_598;
      paramFloat3.normalize();
      localObject1 = new Quat4f(paramFloat2.jdField_field_123_of_type_OrgSchemaSchineGraphicsengineCameraCamera.f5().field_615, paramFloat2.jdField_field_123_of_type_OrgSchemaSchineGraphicsengineCameraCamera.f5().field_616, paramFloat2.jdField_field_123_of_type_OrgSchemaSchineGraphicsengineCameraCamera.f5().field_617, 0.0F);
      ((Quat4f)localObject2).mul(paramFloat2.jdField_field_123_of_type_JavaxVecmathQuat4f, (Quat4f)localObject1);
      ((Quat4f)localObject2).mul(paramFloat6);
      paramFloat4.field_615 = ((Quat4f)localObject2).field_596;
      paramFloat4.field_616 = ((Quat4f)localObject2).field_597;
      paramFloat4.field_617 = ((Quat4f)localObject2).field_598;
      paramFloat4.normalize();
      localObject1 = new Quat4f(paramFloat2.jdField_field_123_of_type_OrgSchemaSchineGraphicsengineCameraCamera.e7().field_615, paramFloat2.jdField_field_123_of_type_OrgSchemaSchineGraphicsengineCameraCamera.e7().field_616, paramFloat2.jdField_field_123_of_type_OrgSchemaSchineGraphicsengineCameraCamera.e7().field_617, 0.0F);
      ((Quat4f)localObject2).mul(paramFloat2.jdField_field_123_of_type_JavaxVecmathQuat4f, (Quat4f)localObject1);
      ((Quat4f)localObject2).mul(paramFloat6);
      paramFloat5.field_615 = ((Quat4f)localObject2).field_596;
      paramFloat5.field_616 = ((Quat4f)localObject2).field_597;
      paramFloat5.field_617 = ((Quat4f)localObject2).field_598;
      paramFloat5.normalize();
      localObject1 = paramFloat2.jdField_field_123_of_type_Class_1382.getWorldTransform();
      paramFloat6 = paramFloat5;
      paramFloat5 = paramFloat4;
      paramFloat4 = paramFloat3;
      paramFloat3 = paramFloat2;
      Object localObject2 = new Vector3f();
      Vector3f localVector3f = new Vector3f();
      paramFloat2 = new Vector3f();
      paramFloat3.a((Vector3f)localObject2, localVector3f, paramFloat2, (Transform)localObject1);
      localObject1 = new Vector3f((Vector3f)localObject2);
      paramFloat4.normalize();
      paramFloat6.normalize();
      if (((Vector3f)localObject1).angle(paramFloat4) < paramFloat3.jdField_field_123_of_type_Float)
      {
        paramFloat3.jdField_field_123_of_type_OrgSchemaSchineGraphicsengineCameraCamera.a159(paramFloat4);
        paramFloat3.jdField_field_123_of_type_OrgSchemaSchineGraphicsengineCameraCamera.b24(paramFloat6);
        paramFloat3.jdField_field_123_of_type_OrgSchemaSchineGraphicsengineCameraCamera.c12(paramFloat5);
        break;
      }
      paramFloat3.jdField_field_123_of_type_JavaxVecmathVector3f.scale(0.5F);
      if (paramFloat3.jdField_field_122_of_type_Int >= 10) {
        break;
      }
      paramFloat3.jdField_field_122_of_type_Int += 1;
    }
    if (paramFloat1.jdField_field_123_of_type_Boolean)
    {
      (paramFloat2 = new Transform(paramFloat1.jdField_field_123_of_type_Class_1382.getWorldTransform())).basis.sub(paramFloat1.jdField_field_122_of_type_ComBulletphysicsLinearmathTransform.basis);
      paramFloat3 = new Vector3f();
      paramFloat4 = new Vector3f();
      paramFloat5 = new Vector3f();
      paramFloat1.a(paramFloat3, paramFloat4, paramFloat5, paramFloat2);
      GlUtil.a30(paramFloat3, paramFloat2);
      GlUtil.d2(paramFloat4, paramFloat2);
      GlUtil.c3(paramFloat5, paramFloat2);
      paramFloat1.jdField_field_123_of_type_OrgSchemaSchineGraphicsengineCameraCamera.getWorldTransform().basis.add(paramFloat2.basis);
      paramFloat1.jdField_field_122_of_type_ComBulletphysicsLinearmathTransform.set(paramFloat1.jdField_field_123_of_type_Class_1382.getWorldTransform());
    }
    paramFloat1.field_124.set(paramFloat1.jdField_field_123_of_type_OrgSchemaSchineGraphicsengineCameraCamera.getWorldTransform());
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_301
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */