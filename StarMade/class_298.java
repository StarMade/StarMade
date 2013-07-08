import com.bulletphysics.collision.shapes.CompoundShapeChild;
import com.bulletphysics.linearmath.Transform;
import javax.vecmath.Matrix3f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;
import org.schema.common.FastMath;
import org.schema.game.common.controller.SegmentController;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.network.objects.container.PhysicsDataContainer;

public final class class_298
  implements class_1010
{
  private class_1382 jdField_field_122_of_type_Class_1382;
  private Vector3f jdField_field_123_of_type_JavaxVecmathVector3f = new Vector3f();
  private Vector3f jdField_field_122_of_type_JavaxVecmathVector3f = new Vector3f();
  private Vector3f jdField_field_124_of_type_JavaxVecmathVector3f = new Vector3f();
  public boolean field_123;
  int jdField_field_123_of_type_Int = 4;
  private class_1382 jdField_field_124_of_type_Class_1382;
  private final Transform jdField_field_122_of_type_ComBulletphysicsLinearmathTransform = new Transform();
  public final Transform field_123;
  private final Transform jdField_field_124_of_type_ComBulletphysicsLinearmathTransform = new Transform();
  private final Transform jdField_field_125_of_type_ComBulletphysicsLinearmathTransform = new Transform();
  private float jdField_field_123_of_type_Float = 0.7853982F;
  private int jdField_field_122_of_type_Int = 0;
  private boolean jdField_field_122_of_type_Boolean = true;
  public class_1382 field_123;
  private Quat4f jdField_field_123_of_type_JavaxVecmathQuat4f = new Quat4f();
  private Quat4f jdField_field_122_of_type_JavaxVecmathQuat4f = new Quat4f();
  private Quat4f jdField_field_124_of_type_JavaxVecmathQuat4f = new Quat4f();
  private Quat4f jdField_field_125_of_type_JavaxVecmathQuat4f = new Quat4f();
  private Quat4f field_126 = new Quat4f(0.0F, 0.0F, 0.0F, 1.0F);
  private Quat4f field_127 = new Quat4f();
  private Vector3f jdField_field_125_of_type_JavaxVecmathVector3f = new Vector3f();
  
  public class_298(class_1382 paramclass_13821, class_1382 paramclass_13822)
  {
    this.jdField_field_123_of_type_ComBulletphysicsLinearmathTransform = new Transform();
    this.jdField_field_122_of_type_Class_1382 = paramclass_13821;
    this.jdField_field_124_of_type_Class_1382 = paramclass_13822;
    this.jdField_field_123_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
    this.jdField_field_124_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
    this.jdField_field_122_of_type_ComBulletphysicsLinearmathTransform.set(paramclass_13822.getWorldTransform());
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
      GlUtil.b(paramVector3f2, paramTransform);
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
    this.jdField_field_125_of_type_ComBulletphysicsLinearmathTransform.set(this.jdField_field_122_of_type_Class_1382.getWorldTransform());
    this.jdField_field_122_of_type_Int = 0;
    this.jdField_field_124_of_type_JavaxVecmathVector3f.field_615 = (-paramFloat1 * paramFloat4);
    this.jdField_field_124_of_type_JavaxVecmathVector3f.field_616 = (paramFloat2 * paramFloat5);
    this.jdField_field_124_of_type_JavaxVecmathVector3f.field_617 = (paramFloat3 * paramFloat6);
    paramFloat1 = this;
    for (paramFloat2 = this;; paramFloat2 = paramFloat6)
    {
      if (paramFloat2.jdField_field_122_of_type_Boolean)
      {
        paramFloat2.jdField_field_122_of_type_Boolean = false;
        paramFloat2.jdField_field_124_of_type_ComBulletphysicsLinearmathTransform.set(((SegmentController)paramFloat2.jdField_field_123_of_type_Class_1382).getPhysicsDataContainer().getShapeChild().transform);
      }
      paramFloat2.jdField_field_122_of_type_Class_1382.getWorldTransform().set(paramFloat2.jdField_field_124_of_type_ComBulletphysicsLinearmathTransform);
      paramFloat3 = -0.7853982F;
      paramFloat4 = 1.570796F;
      switch (paramFloat2.jdField_field_123_of_type_Int)
      {
      case 2: 
        paramFloat3 = 0.7853982F;
        paramFloat4 = 0.1F;
        break;
      case 3: 
        paramFloat2.jdField_field_124_of_type_JavaxVecmathVector3f.field_616 = (-paramFloat2.jdField_field_124_of_type_JavaxVecmathVector3f.field_616);
        paramFloat3 = -3.041593F;
        paramFloat4 = 4.026991F;
        break;
      case 4: 
        paramFloat3 = -0.7853982F;
        paramFloat4 = 1.670796F;
        break;
      case 5: 
        paramFloat2.jdField_field_124_of_type_JavaxVecmathVector3f.field_616 = (-paramFloat2.jdField_field_124_of_type_JavaxVecmathVector3f.field_616);
        paramFloat3 = -1.570796F;
        paramFloat4 = 2.456194F;
        break;
      case 0: 
        paramFloat2.jdField_field_124_of_type_JavaxVecmathVector3f.field_616 = (-paramFloat2.jdField_field_124_of_type_JavaxVecmathVector3f.field_616);
        paramFloat3 = -1.570796F;
        paramFloat4 = 2.356195F;
        break;
      case 1: 
        paramFloat2.jdField_field_124_of_type_JavaxVecmathVector3f.field_616 = (-paramFloat2.jdField_field_124_of_type_JavaxVecmathVector3f.field_616);
        paramFloat3 = -1.570796F;
        paramFloat4 = 2.456194F;
      }
      paramFloat2.jdField_field_125_of_type_JavaxVecmathVector3f.add(paramFloat2.jdField_field_124_of_type_JavaxVecmathVector3f);
      if (paramFloat2.jdField_field_125_of_type_JavaxVecmathVector3f.field_616 > paramFloat3)
      {
        paramFloat2.jdField_field_125_of_type_JavaxVecmathVector3f.field_616 -= paramFloat2.jdField_field_124_of_type_JavaxVecmathVector3f.field_616;
        paramFloat2.jdField_field_124_of_type_JavaxVecmathVector3f.field_616 = (paramFloat3 - paramFloat2.jdField_field_125_of_type_JavaxVecmathVector3f.field_616);
        paramFloat2.jdField_field_125_of_type_JavaxVecmathVector3f.field_616 = paramFloat3;
      }
      if (paramFloat2.jdField_field_125_of_type_JavaxVecmathVector3f.field_616 < -paramFloat4)
      {
        paramFloat2.jdField_field_125_of_type_JavaxVecmathVector3f.field_616 -= paramFloat2.jdField_field_124_of_type_JavaxVecmathVector3f.field_616;
        paramFloat2.jdField_field_124_of_type_JavaxVecmathVector3f.field_616 = (-(paramFloat4 - Math.abs(paramFloat2.jdField_field_125_of_type_JavaxVecmathVector3f.field_616)));
        paramFloat2.jdField_field_125_of_type_JavaxVecmathVector3f.field_616 = (-paramFloat4);
      }
      paramFloat3 = GlUtil.c(new Vector3f(), paramFloat2.jdField_field_122_of_type_Class_1382.getWorldTransform());
      paramFloat4 = GlUtil.f(new Vector3f(), paramFloat2.jdField_field_122_of_type_Class_1382.getWorldTransform());
      paramFloat5 = GlUtil.e(new Vector3f(), paramFloat2.jdField_field_122_of_type_Class_1382.getWorldTransform());
      float f3 = paramFloat5;
      float f2 = paramFloat4;
      float f1 = paramFloat3;
      if ((paramFloat6 = paramFloat2).jdField_field_124_of_type_JavaxVecmathVector3f.field_616 != 0.0F)
      {
        paramFloat6.jdField_field_123_of_type_JavaxVecmathVector3f.cross(GlUtil.c(new Vector3f(), paramFloat6.jdField_field_122_of_type_Class_1382.getWorldTransform()), GlUtil.f(new Vector3f(), paramFloat6.jdField_field_122_of_type_Class_1382.getWorldTransform()));
        paramFloat6.jdField_field_122_of_type_JavaxVecmathVector3f.set(paramFloat6.jdField_field_123_of_type_JavaxVecmathVector3f);
        paramFloat6.jdField_field_122_of_type_JavaxVecmathVector3f.normalize();
        switch (paramFloat6.jdField_field_123_of_type_Int)
        {
        case 2: 
          paramFloat6.a3(paramFloat6.jdField_field_124_of_type_JavaxVecmathVector3f.field_616, paramFloat6.jdField_field_122_of_type_JavaxVecmathVector3f, f1, f2, f3);
          break;
        case 3: 
          paramFloat6.jdField_field_122_of_type_JavaxVecmathVector3f.negate();
          paramFloat6.a3(paramFloat6.jdField_field_124_of_type_JavaxVecmathVector3f.field_616, paramFloat6.jdField_field_122_of_type_JavaxVecmathVector3f, f1, f2, f3);
          break;
        case 4: 
          paramFloat6.a3(paramFloat6.jdField_field_124_of_type_JavaxVecmathVector3f.field_616, paramFloat6.jdField_field_122_of_type_JavaxVecmathVector3f, f1, f3, f2);
          break;
        case 5: 
          paramFloat6.jdField_field_122_of_type_JavaxVecmathVector3f.negate();
          paramFloat6.a3(paramFloat6.jdField_field_124_of_type_JavaxVecmathVector3f.field_616, paramFloat6.jdField_field_122_of_type_JavaxVecmathVector3f, f1, f3, f2);
          break;
        case 0: 
          paramFloat6.jdField_field_122_of_type_JavaxVecmathVector3f.set(GlUtil.c(new Vector3f(), paramFloat6.jdField_field_122_of_type_Class_1382.getWorldTransform()));
          paramFloat6.a3(paramFloat6.jdField_field_124_of_type_JavaxVecmathVector3f.field_616, paramFloat6.jdField_field_122_of_type_JavaxVecmathVector3f, f3, f2, f1);
          break;
        case 1: 
          paramFloat6.jdField_field_122_of_type_JavaxVecmathVector3f.set(GlUtil.c(new Vector3f(), paramFloat6.jdField_field_122_of_type_Class_1382.getWorldTransform()));
          paramFloat6.a3(paramFloat6.jdField_field_124_of_type_JavaxVecmathVector3f.field_616, paramFloat6.jdField_field_122_of_type_JavaxVecmathVector3f, f3, f2, f1);
        }
      }
      f3 = paramFloat5;
      f2 = paramFloat4;
      f1 = paramFloat3;
      paramFloat6 = paramFloat2;
      Object localObject = new Vector3f();
      switch (paramFloat6.jdField_field_123_of_type_Int)
      {
      case 2: 
        ((Vector3f)localObject).set(0.0F, 1.0F, 0.0F);
        break;
      case 3: 
        ((Vector3f)localObject).set(0.0F, -1.0F, 0.0F);
        break;
      case 4: 
        ((Vector3f)localObject).set(0.0F, 0.0F, 1.0F);
        break;
      case 5: 
        ((Vector3f)localObject).set(0.0F, 0.0F, -1.0F);
        break;
      case 0: 
        ((Vector3f)localObject).set(1.0F, 0.0F, 0.0F);
        break;
      case 1: 
        ((Vector3f)localObject).set(-1.0F, 0.0F, 0.0F);
      }
      if (paramFloat6.jdField_field_124_of_type_JavaxVecmathVector3f.field_615 != 0.0F) {
        paramFloat6.a3(paramFloat6.jdField_field_124_of_type_JavaxVecmathVector3f.field_615, (Vector3f)localObject, f1, f2, f3);
      }
      if (paramFloat2.jdField_field_123_of_type_Class_1382 != null) {
        break label1196;
      }
      localObject = paramFloat2.jdField_field_124_of_type_Class_1382.getWorldTransform();
      f3 = paramFloat5;
      f2 = paramFloat4;
      f1 = paramFloat3;
      paramFloat6 = paramFloat2;
      paramFloat3 = new Vector3f();
      paramFloat4 = new Vector3f();
      paramFloat5 = new Vector3f();
      paramFloat6.a(paramFloat3, paramFloat4, paramFloat5, (Transform)localObject);
      paramFloat3 = new Vector3f(paramFloat3);
      f1.normalize();
      f3.normalize();
      if (paramFloat3.angle(f1) < paramFloat6.jdField_field_123_of_type_Float)
      {
        GlUtil.a30(f1, paramFloat6.jdField_field_122_of_type_Class_1382.getWorldTransform());
        GlUtil.c3(f3, paramFloat6.jdField_field_122_of_type_Class_1382.getWorldTransform());
        GlUtil.d2(f2, paramFloat6.jdField_field_122_of_type_Class_1382.getWorldTransform());
        break;
      }
      paramFloat6.jdField_field_124_of_type_JavaxVecmathVector3f.scale(0.5F);
      if (paramFloat6.jdField_field_122_of_type_Int >= 10) {
        break;
      }
      paramFloat6.jdField_field_122_of_type_Int += 1;
    }
    break label1237;
    label1196:
    GlUtil.a30(paramFloat3, paramFloat2.jdField_field_122_of_type_Class_1382.getWorldTransform());
    GlUtil.c3(paramFloat5, paramFloat2.jdField_field_122_of_type_Class_1382.getWorldTransform());
    GlUtil.d2(paramFloat4, paramFloat2.jdField_field_122_of_type_Class_1382.getWorldTransform());
    label1237:
    if (paramFloat1.jdField_field_123_of_type_Boolean)
    {
      (paramFloat2 = new Transform(paramFloat1.jdField_field_124_of_type_Class_1382.getWorldTransform())).basis.sub(paramFloat1.jdField_field_122_of_type_ComBulletphysicsLinearmathTransform.basis);
      paramFloat3 = new Vector3f();
      paramFloat4 = new Vector3f();
      paramFloat5 = new Vector3f();
      paramFloat1.a(paramFloat3, paramFloat4, paramFloat5, paramFloat2);
      GlUtil.a30(paramFloat3, paramFloat2);
      GlUtil.d2(paramFloat4, paramFloat2);
      GlUtil.c3(paramFloat5, paramFloat2);
      paramFloat1.jdField_field_122_of_type_Class_1382.getWorldTransform().basis.add(paramFloat2.basis);
      paramFloat1.jdField_field_122_of_type_ComBulletphysicsLinearmathTransform.set(paramFloat1.jdField_field_124_of_type_Class_1382.getWorldTransform());
    }
    if (paramFloat1.jdField_field_123_of_type_Class_1382 == null)
    {
      paramFloat1.jdField_field_124_of_type_ComBulletphysicsLinearmathTransform.set(paramFloat1.jdField_field_122_of_type_Class_1382.getWorldTransform());
      return;
    }
    paramFloat1.jdField_field_124_of_type_ComBulletphysicsLinearmathTransform.set(paramFloat1.jdField_field_122_of_type_Class_1382.getWorldTransform());
    if ((paramFloat1.jdField_field_123_of_type_Int == 0) || (paramFloat1.jdField_field_123_of_type_Int == 1))
    {
      paramFloat2 = new Vector3f();
      paramFloat3 = new Vector3f();
      paramFloat4 = new Vector3f();
      paramFloat1.a(paramFloat2, paramFloat3, paramFloat4, paramFloat1.jdField_field_122_of_type_Class_1382.getWorldTransform());
      GlUtil.a30(paramFloat2, paramFloat1.jdField_field_122_of_type_Class_1382.getWorldTransform());
      GlUtil.d2(paramFloat3, paramFloat1.jdField_field_122_of_type_Class_1382.getWorldTransform());
      GlUtil.c3(paramFloat4, paramFloat1.jdField_field_122_of_type_Class_1382.getWorldTransform());
    }
    (paramFloat2 = new Transform(((SegmentController)paramFloat1.jdField_field_123_of_type_Class_1382).getWorldTransform())).basis.mul(paramFloat1.jdField_field_122_of_type_Class_1382.getWorldTransform().basis);
    paramFloat1.jdField_field_122_of_type_Class_1382.getWorldTransform().basis.set(paramFloat2.basis);
    paramFloat1.jdField_field_122_of_type_ComBulletphysicsLinearmathTransform.set(paramFloat1.jdField_field_123_of_type_Class_1382.getWorldTransform());
  }
  
  private void a2(Vector3f paramVector3f)
  {
    this.field_127.set(paramVector3f.field_615, paramVector3f.field_616, paramVector3f.field_617, 0.0F);
    this.jdField_field_122_of_type_JavaxVecmathQuat4f.mul(this.jdField_field_125_of_type_JavaxVecmathQuat4f, this.field_127);
    this.jdField_field_123_of_type_JavaxVecmathQuat4f.mul(this.jdField_field_122_of_type_JavaxVecmathQuat4f, this.jdField_field_124_of_type_JavaxVecmathQuat4f);
    paramVector3f.set(this.jdField_field_123_of_type_JavaxVecmathQuat4f.field_596, this.jdField_field_123_of_type_JavaxVecmathQuat4f.field_597, this.jdField_field_123_of_type_JavaxVecmathQuat4f.field_598);
  }
  
  private void a3(float paramFloat, Vector3f paramVector3f1, Vector3f paramVector3f2, Vector3f paramVector3f3, Vector3f paramVector3f4)
  {
    this.jdField_field_125_of_type_JavaxVecmathQuat4f.field_596 = (paramVector3f1.field_615 * FastMath.h(paramFloat / 2.0F));
    this.jdField_field_125_of_type_JavaxVecmathQuat4f.field_597 = (paramVector3f1.field_616 * FastMath.h(paramFloat / 2.0F));
    this.jdField_field_125_of_type_JavaxVecmathQuat4f.field_598 = (paramVector3f1.field_617 * FastMath.h(paramFloat / 2.0F));
    this.jdField_field_125_of_type_JavaxVecmathQuat4f.field_599 = FastMath.d(paramFloat / 2.0F);
    this.jdField_field_125_of_type_JavaxVecmathQuat4f.normalize();
    this.jdField_field_124_of_type_JavaxVecmathQuat4f.conjugate(this.jdField_field_125_of_type_JavaxVecmathQuat4f);
    a2(paramVector3f2);
    a2(paramVector3f3);
    a2(paramVector3f4);
    this.field_126.mul(this.jdField_field_125_of_type_JavaxVecmathQuat4f);
  }
  
  public final void a4(class_1382 paramclass_1382)
  {
    if ((paramclass_1382 != null) && (paramclass_1382 != this.jdField_field_123_of_type_Class_1382))
    {
      this.jdField_field_122_of_type_ComBulletphysicsLinearmathTransform.set(paramclass_1382.getWorldTransform());
      this.jdField_field_124_of_type_ComBulletphysicsLinearmathTransform.set(((SegmentController)paramclass_1382).getPhysicsDataContainer().getShapeChild().transform);
      this.jdField_field_122_of_type_Boolean = true;
    }
    this.jdField_field_123_of_type_Class_1382 = paramclass_1382;
  }
  
  public final void a5(int paramInt)
  {
    if (paramInt != this.jdField_field_123_of_type_Int)
    {
      this.jdField_field_122_of_type_ComBulletphysicsLinearmathTransform.set(this.jdField_field_123_of_type_Class_1382.getWorldTransform());
      this.jdField_field_124_of_type_ComBulletphysicsLinearmathTransform.set(((SegmentController)this.jdField_field_123_of_type_Class_1382).getPhysicsDataContainer().getShapeChild().transform);
      this.jdField_field_122_of_type_Boolean = true;
    }
    this.jdField_field_123_of_type_Int = paramInt;
  }
  
  public final void a6() {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_298
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */