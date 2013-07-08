import com.bulletphysics.collision.shapes.BoxShape;
import com.bulletphysics.linearmath.AabbUtil2;
import com.bulletphysics.linearmath.MatrixUtil;
import com.bulletphysics.linearmath.Transform;
import java.io.PrintStream;
import java.util.Vector;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3f;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.physics.octree.ArrayOctree;
import org.schema.game.common.data.physics.octree.IntersectionCallback;
import org.schema.game.common.data.world.Segment;
import org.schema.game.common.data.world.SegmentData;

final class class_872
  implements class_888
{
  boolean jdField_field_105_of_type_Boolean;
  Transform jdField_field_105_of_type_ComBulletphysicsLinearmathTransform;
  float jdField_field_105_of_type_Float;
  boolean field_207 = false;
  
  private class_872(class_866 paramclass_866) {}
  
  public final boolean handle(Segment paramSegment)
  {
    if ((paramSegment == null) || (paramSegment.g()))
    {
      if (this.field_207) {
        System.err.println("Segment empty " + paramSegment);
      }
      return true;
    }
    SegmentData localSegmentData = paramSegment.a16();
    if (!class_866.a5(this.jdField_field_105_of_type_Class_866).jdField_field_1103_of_type_OrgSchemaGameCommonDataPhysicsOctreeIntersectionCallback.initialized) {
      class_866.a5(this.jdField_field_105_of_type_Class_866).jdField_field_1103_of_type_OrgSchemaGameCommonDataPhysicsOctreeIntersectionCallback.createHitCache(512);
    }
    class_866.a5(this.jdField_field_105_of_type_Class_866).jdField_field_1103_of_type_OrgSchemaGameCommonDataPhysicsOctreeIntersectionCallback.reset();
    class_866.a5(this.jdField_field_105_of_type_Class_866).jdField_field_1103_of_type_JavaxVecmathMatrix3f.set(paramSegment.a15().getWorldTransform().basis);
    MatrixUtil.absolute(class_866.a5(this.jdField_field_105_of_type_Class_866).jdField_field_1103_of_type_JavaxVecmathMatrix3f);
    localSegmentData.getOctree().findIntersecting(localSegmentData.getOctree().getSet(), class_866.a5(this.jdField_field_105_of_type_Class_866).jdField_field_1103_of_type_OrgSchemaGameCommonDataPhysicsOctreeIntersectionCallback, paramSegment, paramSegment.a15().getWorldTransform(), class_866.a5(this.jdField_field_105_of_type_Class_866).jdField_field_1103_of_type_JavaxVecmathMatrix3f, 0.9F, class_866.a5(this.jdField_field_105_of_type_Class_866).jdField_field_1105_of_type_JavaxVecmathVector3f, class_866.a5(this.jdField_field_105_of_type_Class_866).field_1106, 1.0F);
    if (class_866.a5(this.jdField_field_105_of_type_Class_866).jdField_field_1103_of_type_OrgSchemaGameCommonDataPhysicsOctreeIntersectionCallback.hitCount > 0) {
      for (int i = 0; i < class_866.a5(this.jdField_field_105_of_type_Class_866).jdField_field_1103_of_type_OrgSchemaGameCommonDataPhysicsOctreeIntersectionCallback.hitCount; i++)
      {
        class_866.a5(this.jdField_field_105_of_type_Class_866).jdField_field_1103_of_type_OrgSchemaGameCommonDataPhysicsOctreeIntersectionCallback.getHit(i, class_866.a5(this.jdField_field_105_of_type_Class_866).field_1109, class_866.a5(this.jdField_field_105_of_type_Class_866).field_1110, class_866.a5(this.jdField_field_105_of_type_Class_866).jdField_field_1103_of_type_Class_35, class_866.a5(this.jdField_field_105_of_type_Class_866).jdField_field_1104_of_type_Class_35);
        for (int j = class_866.a5(this.jdField_field_105_of_type_Class_866).jdField_field_1103_of_type_Class_35.field_453; j < class_866.a5(this.jdField_field_105_of_type_Class_866).jdField_field_1104_of_type_Class_35.field_453; j = (byte)(j + 1)) {
          for (int k = class_866.a5(this.jdField_field_105_of_type_Class_866).jdField_field_1103_of_type_Class_35.field_454; k < class_866.a5(this.jdField_field_105_of_type_Class_866).jdField_field_1104_of_type_Class_35.field_454; k = (byte)(k + 1)) {
            for (int m = class_866.a5(this.jdField_field_105_of_type_Class_866).jdField_field_1103_of_type_Class_35.field_455; m < class_866.a5(this.jdField_field_105_of_type_Class_866).jdField_field_1104_of_type_Class_35.field_455; m = (byte)(m + 1))
            {
              class_866.a5(this.jdField_field_105_of_type_Class_866).jdField_field_1105_of_type_Class_35.b((byte)(j + 8), (byte)(k + 8), (byte)(m + 8));
              if (localSegmentData.contains(class_866.a5(this.jdField_field_105_of_type_Class_866).jdField_field_1105_of_type_Class_35))
              {
                class_866.a5(this.jdField_field_105_of_type_Class_866).field_1112.set(j + paramSegment.field_34.field_475, k + paramSegment.field_34.field_476, m + paramSegment.field_34.field_477);
                class_866.a5(this.jdField_field_105_of_type_Class_866).field_1111.set(class_866.a5(this.jdField_field_105_of_type_Class_866).field_1112);
                class_866.a5(this.jdField_field_105_of_type_Class_866).jdField_field_1103_of_type_ComBulletphysicsLinearmathTransform.set(paramSegment.a15().getWorldTransform());
                class_866.a5(this.jdField_field_105_of_type_Class_866).jdField_field_1103_of_type_ComBulletphysicsLinearmathTransform.basis.transform(class_866.a5(this.jdField_field_105_of_type_Class_866).field_1111);
                class_866.a5(this.jdField_field_105_of_type_Class_866).jdField_field_1103_of_type_ComBulletphysicsLinearmathTransform.origin.add(class_866.a5(this.jdField_field_105_of_type_Class_866).field_1111);
                class_874.jdField_field_1103_of_type_ComBulletphysicsCollisionShapesBoxShape.setMargin(this.jdField_field_105_of_type_Float);
                class_874.jdField_field_1103_of_type_ComBulletphysicsCollisionShapesBoxShape.getAabb(class_866.a5(this.jdField_field_105_of_type_Class_866).jdField_field_1103_of_type_ComBulletphysicsLinearmathTransform, class_866.a5(this.jdField_field_105_of_type_Class_866).jdField_field_1103_of_type_JavaxVecmathVector3f, class_866.a5(this.jdField_field_105_of_type_Class_866).jdField_field_1104_of_type_JavaxVecmathVector3f);
                if (this.field_207)
                {
                  class_866.a5(this.jdField_field_105_of_type_Class_866).field_1107.set(class_866.a5(this.jdField_field_105_of_type_Class_866).jdField_field_1105_of_type_JavaxVecmathVector3f);
                  class_866.a5(this.jdField_field_105_of_type_Class_866).field_1108.set(class_866.a5(this.jdField_field_105_of_type_Class_866).field_1106);
                }
                else
                {
                  class_874.jdField_field_1103_of_type_ComBulletphysicsCollisionShapesBoxShape.getAabb(this.jdField_field_105_of_type_ComBulletphysicsLinearmathTransform, class_866.a5(this.jdField_field_105_of_type_Class_866).field_1107, class_866.a5(this.jdField_field_105_of_type_Class_866).field_1108);
                }
                if (class_949.field_1221.b1())
                {
                  (localObject = new Transform()).setIdentity();
                  class_994 localclass_994 = new class_994(new Vector3f(class_866.a5(this.jdField_field_105_of_type_Class_866).jdField_field_1103_of_type_JavaxVecmathVector3f), new Vector3f(class_866.a5(this.jdField_field_105_of_type_Class_866).jdField_field_1104_of_type_JavaxVecmathVector3f), (Transform)localObject, 1.0F, 1.0F);
                  Object localObject = new class_994(new Vector3f(class_866.a5(this.jdField_field_105_of_type_Class_866).field_1107), new Vector3f(class_866.a5(this.jdField_field_105_of_type_Class_866).field_1108), (Transform)localObject, 1.0F, 0.0F);
                  class_1428.field_1636.add(localclass_994);
                  class_1428.field_1636.add(localObject);
                }
                if (AabbUtil2.testAabbAgainstAabb2(class_866.a5(this.jdField_field_105_of_type_Class_866).jdField_field_1103_of_type_JavaxVecmathVector3f, class_866.a5(this.jdField_field_105_of_type_Class_866).jdField_field_1104_of_type_JavaxVecmathVector3f, class_866.a5(this.jdField_field_105_of_type_Class_866).field_1107, class_866.a5(this.jdField_field_105_of_type_Class_866).field_1108))
                {
                  this.jdField_field_105_of_type_Boolean = true;
                  return false;
                }
              }
            }
          }
        }
      }
    }
    return true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_872
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */