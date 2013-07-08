import com.bulletphysics.collision.shapes.BoxShape;
import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.linearmath.AabbUtil2;
import com.bulletphysics.linearmath.MatrixUtil;
import com.bulletphysics.linearmath.Transform;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.Set;
import javax.vecmath.Matrix3f;
import javax.vecmath.Tuple3f;
import javax.vecmath.Vector3f;
import org.schema.common.FastMath;
import org.schema.common.util.ByteUtil;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.element.ElementDocking;
import org.schema.game.common.data.physics.octree.ArrayOctree;
import org.schema.game.common.data.physics.octree.IntersectionCallback;
import org.schema.game.common.data.world.Segment;
import org.schema.game.common.data.world.SegmentData;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.objects.Sendable;
import org.schema.schine.network.objects.container.PhysicsDataContainer;

public final class class_866
{
  boolean jdField_field_1101_of_type_Boolean;
  private SegmentController jdField_field_1101_of_type_OrgSchemaGameCommonControllerSegmentController;
  private class_874 jdField_field_1101_of_type_Class_874;
  private static ThreadLocal jdField_field_1101_of_type_JavaLangThreadLocal = new class_868();
  
  public class_866(SegmentController paramSegmentController)
  {
    this.jdField_field_1101_of_type_OrgSchemaGameCommonControllerSegmentController = paramSegmentController;
    this.jdField_field_1101_of_type_Class_874 = ((class_874)jdField_field_1101_of_type_JavaLangThreadLocal.get());
  }
  
  public final boolean a(class_796 arg1, class_892 paramclass_892)
  {
    Transform localTransform = new Transform(this.jdField_field_1101_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransform());
    ???.a6(this.jdField_field_1101_of_type_Class_874.jdField_field_1106_of_type_Class_35);
    Object localObject1;
    (localObject1 = new Vector3f(this.jdField_field_1101_of_type_Class_874.jdField_field_1106_of_type_Class_35.field_453 - 8, this.jdField_field_1101_of_type_Class_874.jdField_field_1106_of_type_Class_35.field_454 - 8, this.jdField_field_1101_of_type_Class_874.jdField_field_1106_of_type_Class_35.field_455 - 8)).field_615 += ???.a7().field_34.field_475;
    localObject1.field_616 += ???.a7().field_34.field_476;
    localObject1.field_617 += ???.a7().field_34.field_477;
    localTransform.basis.transform((Tuple3f)localObject1);
    localTransform.origin.add((Tuple3f)localObject1);
    class_874.jdField_field_1103_of_type_ComBulletphysicsCollisionShapesBoxShape.getAabb(localTransform, this.jdField_field_1101_of_type_Class_874.jdField_field_1105_of_type_JavaxVecmathVector3f, this.jdField_field_1101_of_type_Class_874.jdField_field_1106_of_type_JavaxVecmathVector3f);
    synchronized (this.jdField_field_1101_of_type_OrgSchemaGameCommonControllerSegmentController.getState().getLocalAndRemoteObjectContainer().getLocalObjects())
    {
      localObject1 = this.jdField_field_1101_of_type_OrgSchemaGameCommonControllerSegmentController.getState().getLocalAndRemoteObjectContainer().getLocalObjects().values().iterator();
      while (((Iterator)localObject1).hasNext())
      {
        Sendable localSendable;
        Object localObject2;
        if (((localSendable = (Sendable)((Iterator)localObject1).next()) instanceof SegmentController))
        {
          if ((localSendable != this.jdField_field_1101_of_type_OrgSchemaGameCommonControllerSegmentController) && ((localObject2 = (SegmentController)localSendable).getSectorId() == this.jdField_field_1101_of_type_OrgSchemaGameCommonControllerSegmentController.getSectorId())) {
            if (a1((SegmentController)localObject2, localTransform, 0.01F, false))
            {
              paramclass_892.field_1122 = localObject2;
              return true;
            }
          }
        }
        else if (((localSendable instanceof class_797)) && (!(localObject2 = (class_797)localSendable).isHidden()) && (this.jdField_field_1101_of_type_OrgSchemaGameCommonControllerSegmentController.getSectorId() == ((class_797)localObject2).getSectorId()))
        {
          ((class_797)localObject2).getTransformedAABB(this.jdField_field_1101_of_type_Class_874.jdField_field_1103_of_type_JavaxVecmathVector3f, this.jdField_field_1101_of_type_Class_874.jdField_field_1104_of_type_JavaxVecmathVector3f, 0.0F, this.jdField_field_1101_of_type_Class_874.field_1113, this.jdField_field_1101_of_type_Class_874.field_1114);
          if (AabbUtil2.testAabbAgainstAabb2(this.jdField_field_1101_of_type_Class_874.jdField_field_1103_of_type_JavaxVecmathVector3f, this.jdField_field_1101_of_type_Class_874.jdField_field_1104_of_type_JavaxVecmathVector3f, this.jdField_field_1101_of_type_Class_874.jdField_field_1105_of_type_JavaxVecmathVector3f, this.jdField_field_1101_of_type_Class_874.jdField_field_1106_of_type_JavaxVecmathVector3f))
          {
            System.err.println("[PIECECOLLISION] ElementToBuild blocked by " + localSendable);
            paramclass_892.field_1122 = localObject2;
            return true;
          }
        }
      }
    }
    return false;
  }
  
  private boolean a1(SegmentController paramSegmentController, Transform paramTransform, float paramFloat, boolean paramBoolean)
  {
    paramSegmentController.getPhysicsDataContainer().getShape().getAabb(paramSegmentController.getWorldTransform(), this.jdField_field_1101_of_type_Class_874.jdField_field_1103_of_type_JavaxVecmathVector3f, this.jdField_field_1101_of_type_Class_874.jdField_field_1104_of_type_JavaxVecmathVector3f);
    boolean bool = AabbUtil2.testAabbAgainstAabb2(this.jdField_field_1101_of_type_Class_874.jdField_field_1103_of_type_JavaxVecmathVector3f, this.jdField_field_1101_of_type_Class_874.jdField_field_1104_of_type_JavaxVecmathVector3f, this.jdField_field_1101_of_type_Class_874.jdField_field_1105_of_type_JavaxVecmathVector3f, this.jdField_field_1101_of_type_Class_874.jdField_field_1106_of_type_JavaxVecmathVector3f);
    if (paramBoolean) {
      System.err.println("[COLLISIONCHECKER] PRE-AABB TEST " + this.jdField_field_1101_of_type_OrgSchemaGameCommonControllerSegmentController + " -> " + paramSegmentController + " AT AABB[" + this.jdField_field_1101_of_type_Class_874.jdField_field_1103_of_type_JavaxVecmathVector3f + ", " + this.jdField_field_1101_of_type_Class_874.jdField_field_1104_of_type_JavaxVecmathVector3f + " | " + this.jdField_field_1101_of_type_Class_874.jdField_field_1105_of_type_JavaxVecmathVector3f + ", " + this.jdField_field_1101_of_type_Class_874.jdField_field_1106_of_type_JavaxVecmathVector3f + "] -> " + bool + "; " + this.jdField_field_1101_of_type_OrgSchemaGameCommonControllerSegmentController.getState());
    }
    Object localObject;
    if (bool)
    {
      (localObject = new class_872(this, (byte)0)).jdField_field_105_of_type_Boolean = false;
      ((class_872)localObject).jdField_field_105_of_type_Float = paramFloat;
      ((class_872)localObject).jdField_field_105_of_type_ComBulletphysicsLinearmathTransform = new Transform(paramTransform);
      ((class_872)localObject).field_207 = paramBoolean;
      paramSegmentController.getSegmentBuffer().b3((class_888)localObject, true);
      return ((class_872)localObject).jdField_field_105_of_type_Boolean;
    }
    if (!paramSegmentController.getDockingController().a5().isEmpty())
    {
      localObject = paramSegmentController.getDockingController().a5().iterator();
      while (((Iterator)localObject).hasNext())
      {
        paramSegmentController = (ElementDocking)((Iterator)localObject).next();
        a1(paramSegmentController.from.a7().a15(), paramTransform, paramFloat, paramBoolean);
      }
    }
    return false;
  }
  
  public final boolean a2(SegmentController paramSegmentController, Transform paramTransform)
  {
    class_874.jdField_field_1103_of_type_ComBulletphysicsCollisionShapesBoxShape.getAabb(paramTransform, this.jdField_field_1101_of_type_Class_874.jdField_field_1105_of_type_JavaxVecmathVector3f, this.jdField_field_1101_of_type_Class_874.jdField_field_1106_of_type_JavaxVecmathVector3f);
    return a1(paramSegmentController, paramTransform, 0.1F, false);
  }
  
  public final boolean a3(class_48 paramclass_481, class_48 paramclass_482)
  {
    class_48 localclass_481 = new class_48();
    class_48 localclass_482 = new class_48();
    localclass_481.field_475 = (ByteUtil.b(paramclass_481.field_475 - 1) << 4);
    localclass_481.field_476 = (ByteUtil.b(paramclass_481.field_476 - 1) << 4);
    localclass_481.field_477 = (ByteUtil.b(paramclass_481.field_477 - 1) << 4);
    localclass_482.field_475 = (FastMath.b4((paramclass_482.field_475 + 1) / 16.0F) << 4);
    localclass_482.field_476 = (FastMath.b4((paramclass_482.field_476 + 1) / 16.0F) << 4);
    localclass_482.field_477 = (FastMath.b4((paramclass_482.field_477 + 1) / 16.0F) << 4);
    this.jdField_field_1101_of_type_Boolean = false;
    this.jdField_field_1101_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentBuffer().b4(new class_870(this, paramclass_481, paramclass_482), localclass_481, localclass_482);
    return this.jdField_field_1101_of_type_Boolean;
  }
  
  public final boolean a4(Segment paramSegment, class_48 paramclass_481, class_48 paramclass_482)
  {
    SegmentData localSegmentData = paramSegment.a16();
    if (!this.jdField_field_1101_of_type_Class_874.jdField_field_1103_of_type_OrgSchemaGameCommonDataPhysicsOctreeIntersectionCallback.initialized) {
      this.jdField_field_1101_of_type_Class_874.jdField_field_1103_of_type_OrgSchemaGameCommonDataPhysicsOctreeIntersectionCallback.createHitCache(512);
    }
    this.jdField_field_1101_of_type_Class_874.jdField_field_1103_of_type_OrgSchemaGameCommonDataPhysicsOctreeIntersectionCallback.reset();
    this.jdField_field_1101_of_type_Class_874.jdField_field_1103_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
    this.jdField_field_1101_of_type_Class_874.jdField_field_1105_of_type_JavaxVecmathVector3f.set(paramclass_481.field_475 - 8, paramclass_481.field_476 - 8, paramclass_481.field_477 - 8);
    this.jdField_field_1101_of_type_Class_874.jdField_field_1106_of_type_JavaxVecmathVector3f.set(paramclass_482.field_475 - 8, paramclass_482.field_476 - 8, paramclass_482.field_477 - 8);
    this.jdField_field_1101_of_type_Class_874.jdField_field_1103_of_type_JavaxVecmathMatrix3f.set(this.jdField_field_1101_of_type_Class_874.jdField_field_1103_of_type_ComBulletphysicsLinearmathTransform.basis);
    MatrixUtil.absolute(this.jdField_field_1101_of_type_Class_874.jdField_field_1103_of_type_JavaxVecmathMatrix3f);
    localSegmentData.getOctree().findIntersecting(localSegmentData.getOctree().getSet(), this.jdField_field_1101_of_type_Class_874.jdField_field_1103_of_type_OrgSchemaGameCommonDataPhysicsOctreeIntersectionCallback, paramSegment, this.jdField_field_1101_of_type_Class_874.jdField_field_1103_of_type_ComBulletphysicsLinearmathTransform, this.jdField_field_1101_of_type_Class_874.jdField_field_1103_of_type_JavaxVecmathMatrix3f, 0.0F, this.jdField_field_1101_of_type_Class_874.jdField_field_1105_of_type_JavaxVecmathVector3f, this.jdField_field_1101_of_type_Class_874.jdField_field_1106_of_type_JavaxVecmathVector3f, 1.0F);
    if (this.jdField_field_1101_of_type_Class_874.jdField_field_1103_of_type_OrgSchemaGameCommonDataPhysicsOctreeIntersectionCallback.hitCount > 0) {
      for (int i = 0; i < this.jdField_field_1101_of_type_Class_874.jdField_field_1103_of_type_OrgSchemaGameCommonDataPhysicsOctreeIntersectionCallback.hitCount; i++)
      {
        this.jdField_field_1101_of_type_Class_874.jdField_field_1103_of_type_OrgSchemaGameCommonDataPhysicsOctreeIntersectionCallback.getHit(i, this.jdField_field_1101_of_type_Class_874.field_1109, this.jdField_field_1101_of_type_Class_874.field_1110, this.jdField_field_1101_of_type_Class_874.jdField_field_1103_of_type_Class_35, this.jdField_field_1101_of_type_Class_874.jdField_field_1104_of_type_Class_35);
        for (int j = this.jdField_field_1101_of_type_Class_874.jdField_field_1103_of_type_Class_35.field_453; j < this.jdField_field_1101_of_type_Class_874.jdField_field_1104_of_type_Class_35.field_453; j = (byte)(j + 1)) {
          for (int k = this.jdField_field_1101_of_type_Class_874.jdField_field_1103_of_type_Class_35.field_454; k < this.jdField_field_1101_of_type_Class_874.jdField_field_1104_of_type_Class_35.field_454; k = (byte)(k + 1)) {
            for (int m = this.jdField_field_1101_of_type_Class_874.jdField_field_1103_of_type_Class_35.field_455; m < this.jdField_field_1101_of_type_Class_874.jdField_field_1104_of_type_Class_35.field_455; m = (byte)(m + 1))
            {
              this.jdField_field_1101_of_type_Class_874.jdField_field_1105_of_type_Class_35.b((byte)(j + 8), (byte)(k + 8), (byte)(m + 8));
              if (localSegmentData.contains(this.jdField_field_1101_of_type_Class_874.jdField_field_1105_of_type_Class_35))
              {
                this.jdField_field_1101_of_type_Class_874.jdField_field_1103_of_type_Class_48.b(paramSegment.field_34.field_475 + (j + 8), paramSegment.field_34.field_476 + (k + 8), paramSegment.field_34.field_477 + (m + 8));
                if ((this.jdField_field_1101_of_type_Class_874.jdField_field_1103_of_type_Class_48.field_475 <= paramclass_482.field_475) && (this.jdField_field_1101_of_type_Class_874.jdField_field_1103_of_type_Class_48.field_476 <= paramclass_482.field_476) && (this.jdField_field_1101_of_type_Class_874.jdField_field_1103_of_type_Class_48.field_477 <= paramclass_482.field_477) && (this.jdField_field_1101_of_type_Class_874.jdField_field_1103_of_type_Class_48.field_475 >= paramclass_481.field_475) && (this.jdField_field_1101_of_type_Class_874.jdField_field_1103_of_type_Class_48.field_476 >= paramclass_481.field_476) && (this.jdField_field_1101_of_type_Class_874.jdField_field_1103_of_type_Class_48.field_477 >= paramclass_481.field_477)) {
                  return true;
                }
              }
            }
          }
        }
      }
    }
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_866
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */