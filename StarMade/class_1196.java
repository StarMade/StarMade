import com.bulletphysics.linearmath.Transform;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.vecmath.Matrix3f;
import javax.vecmath.Matrix4f;
import javax.vecmath.Vector3f;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.element.ElementDocking;
import org.schema.game.common.data.physics.PairCachingGhostObjectAlignable;
import org.schema.game.common.data.world.Segment;
import org.schema.game.common.data.world.Universe;
import org.schema.game.server.controller.GameServerController;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.objects.Sendable;
import org.schema.schine.network.objects.container.PhysicsDataContainer;
import org.schema.schine.network.server.ServerMessage;

public class class_1196
{
  private class_797 jdField_field_1403_of_type_Class_797;
  private class_48 jdField_field_1403_of_type_Class_48;
  private int jdField_field_1403_of_type_Int;
  private static Transform jdField_field_1403_of_type_ComBulletphysicsLinearmathTransform;
  private static Transform jdField_field_1404_of_type_ComBulletphysicsLinearmathTransform;
  private static Transform jdField_field_1405_of_type_ComBulletphysicsLinearmathTransform;
  private static Transform jdField_field_1406_of_type_ComBulletphysicsLinearmathTransform;
  private static Vector3f jdField_field_1403_of_type_JavaxVecmathVector3f;
  private static Matrix4f jdField_field_1403_of_type_JavaxVecmathMatrix4f;
  private static Matrix4f jdField_field_1404_of_type_JavaxVecmathMatrix4f;
  private static class_48 jdField_field_1404_of_type_Class_48;
  private static class_48 jdField_field_1405_of_type_Class_48;
  private static class_48 jdField_field_1406_of_type_Class_48 = new class_48();
  
  public class_1196(class_797 paramclass_797, class_48 paramclass_48, int paramInt)
  {
    this.jdField_field_1403_of_type_Class_797 = paramclass_797;
    this.jdField_field_1403_of_type_Class_48 = paramclass_48;
    this.jdField_field_1403_of_type_Int = paramInt;
  }
  
  public final void a(class_1041 paramclass_1041)
  {
    if (this.jdField_field_1403_of_type_Class_797.getPhysicsDataContainer().getObject() == null) {
      if (((this.jdField_field_1403_of_type_Class_797 instanceof SegmentController)) && (((SegmentController)this.jdField_field_1403_of_type_Class_797).getDockingController().b3())) {
        System.err.println("[SECTORSWITCH] Exception (catched): tried to change sector on dockedobject: " + this.jdField_field_1403_of_type_Class_797);
      } else {
        throw new NullPointerException("[SECTORSWITCH] no physics object for: " + this.jdField_field_1403_of_type_Class_797);
      }
    }
    class_670 localclass_6701 = paramclass_1041.a62().getSector(this.jdField_field_1403_of_type_Class_797.getSectorId());
    class_670 localclass_6702 = paramclass_1041.a62().getSector(this.jdField_field_1403_of_type_Class_48);
    if ((!jdField_field_1403_of_type_Boolean) && (paramclass_1041.a62().getSector(localclass_6702.a3()) != localclass_6702)) {
      throw new AssertionError(localclass_6702.a3() + ": " + paramclass_1041.a62().getSector(localclass_6702.a3()));
    }
    if (((this.jdField_field_1403_of_type_Class_797 instanceof class_365)) && (((class_365)this.jdField_field_1403_of_type_Class_797).a26().isEmpty())) {
      localclass_6702.c1();
    } else {
      localclass_6702.b4();
    }
    this.jdField_field_1403_of_type_Class_797.onPhysicsRemove();
    this.jdField_field_1403_of_type_Class_797.setSectorId(localclass_6702.a3());
    HashSet localHashSet = new HashSet();
    Iterator localIterator;
    Object localObject1;
    if ((this.jdField_field_1403_of_type_Class_797 instanceof class_365))
    {
      localIterator = ((class_365)this.jdField_field_1403_of_type_Class_797).a26().iterator();
      while (localIterator.hasNext())
      {
        class_748 localclass_748 = (class_748)localIterator.next();
        System.err.println("[SERVER] " + this.jdField_field_1403_of_type_Class_797 + " has players attached. Doing Sector Change for " + localclass_748 + ": " + localclass_6701 + " -> " + localclass_6702);
        localclass_748.a73(localclass_6702.field_136);
        localclass_748.c13(localclass_6702.a3());
        localObject1 = localclass_748.a121();
        localHashSet.add(localObject1);
        if (localObject1 != null)
        {
          System.err.println("[SERVER] " + this.jdField_field_1403_of_type_Class_797 + " has CHARACTER. Doing Sector Change for " + localObject1 + ": " + localclass_6701 + " -> " + localclass_6702 + " ID " + localclass_6702.a3());
          ((class_750)localObject1).setSectorId(localclass_6702.a3());
        }
        else
        {
          System.err.println("[SERVER] WARNING NO PLAYER CHARACTER ATTACHED TO " + localclass_748);
        }
      }
    }
    if ((this.jdField_field_1403_of_type_Class_797 instanceof SegmentController)) {
      a1((SegmentController)this.jdField_field_1403_of_type_Class_797, localclass_6702);
    }
    this.jdField_field_1403_of_type_Class_797.onPhysicsRemove();
    jdField_field_1403_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
    jdField_field_1403_of_type_ComBulletphysicsLinearmathTransform.set(this.jdField_field_1403_of_type_Class_797.getWorldTransform());
    if (this.jdField_field_1403_of_type_Int == 0)
    {
      Universe.calcSecPos(localclass_6701.field_136, localclass_6702.field_136, paramclass_1041.a59().calculateStartTime(), System.currentTimeMillis(), jdField_field_1403_of_type_ComBulletphysicsLinearmathTransform);
      jdField_field_1404_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
      jdField_field_1405_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
      float f = (float)((System.currentTimeMillis() - paramclass_1041.a59().calculateStartTime()) % 1200000L) / 1200000.0F;
      localObject1 = class_789.a192(localclass_6701.field_136, jdField_field_1406_of_type_Class_48);
      if (!class_791.a19(localclass_6701.field_136)) {
        f = 0.0F;
      }
      class_791.a19(localclass_6701.field_136);
      class_791.a19(localclass_6702.field_136);
      jdField_field_1404_of_type_Class_48.b1((class_48)localObject1);
      jdField_field_1405_of_type_Class_48.b1(localclass_6701.field_136);
      jdField_field_1404_of_type_Class_48.a5(16);
      jdField_field_1404_of_type_Class_48.a(8, 8, 8);
      jdField_field_1404_of_type_Class_48.c1(jdField_field_1405_of_type_Class_48);
      jdField_field_1403_of_type_JavaxVecmathVector3f.set(jdField_field_1404_of_type_Class_48.field_475 * Universe.getSectorSizeWithMargin(), jdField_field_1404_of_type_Class_48.field_476 * Universe.getSectorSizeWithMargin(), jdField_field_1404_of_type_Class_48.field_477 * Universe.getSectorSizeWithMargin());
      jdField_field_1404_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
      jdField_field_1404_of_type_ComBulletphysicsLinearmathTransform.origin.add(jdField_field_1403_of_type_JavaxVecmathVector3f);
      jdField_field_1404_of_type_ComBulletphysicsLinearmathTransform.basis.rotX(6.283186F * f);
      jdField_field_1404_of_type_ComBulletphysicsLinearmathTransform.basis.transform(jdField_field_1404_of_type_ComBulletphysicsLinearmathTransform.origin);
      jdField_field_1406_of_type_ComBulletphysicsLinearmathTransform.set(this.jdField_field_1403_of_type_Class_797.getWorldTransform());
      jdField_field_1406_of_type_ComBulletphysicsLinearmathTransform.origin.negate();
      jdField_field_1404_of_type_ComBulletphysicsLinearmathTransform.origin.add(jdField_field_1406_of_type_ComBulletphysicsLinearmathTransform.origin);
      jdField_field_1404_of_type_Class_48.b1((class_48)localObject1);
      jdField_field_1405_of_type_Class_48.b1(localclass_6702.field_136);
      jdField_field_1404_of_type_Class_48.a5(16);
      jdField_field_1404_of_type_Class_48.a(8, 8, 8);
      jdField_field_1404_of_type_Class_48.c1(jdField_field_1405_of_type_Class_48);
      jdField_field_1403_of_type_JavaxVecmathVector3f.set(jdField_field_1404_of_type_Class_48.field_475 * Universe.getSectorSizeWithMargin(), jdField_field_1404_of_type_Class_48.field_476 * Universe.getSectorSizeWithMargin(), jdField_field_1404_of_type_Class_48.field_477 * Universe.getSectorSizeWithMargin());
      jdField_field_1405_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
      jdField_field_1405_of_type_ComBulletphysicsLinearmathTransform.origin.add(jdField_field_1403_of_type_JavaxVecmathVector3f);
      jdField_field_1405_of_type_ComBulletphysicsLinearmathTransform.basis.rotX(6.283186F * f);
      jdField_field_1405_of_type_ComBulletphysicsLinearmathTransform.basis.transform(jdField_field_1405_of_type_ComBulletphysicsLinearmathTransform.origin);
      jdField_field_1404_of_type_ComBulletphysicsLinearmathTransform.getMatrix(jdField_field_1403_of_type_JavaxVecmathMatrix4f);
      jdField_field_1405_of_type_ComBulletphysicsLinearmathTransform.getMatrix(jdField_field_1404_of_type_JavaxVecmathMatrix4f);
      jdField_field_1405_of_type_ComBulletphysicsLinearmathTransform.inverse();
      jdField_field_1404_of_type_ComBulletphysicsLinearmathTransform.mul(jdField_field_1405_of_type_ComBulletphysicsLinearmathTransform);
      jdField_field_1403_of_type_ComBulletphysicsLinearmathTransform.origin.set(jdField_field_1404_of_type_ComBulletphysicsLinearmathTransform.origin);
      jdField_field_1403_of_type_ComBulletphysicsLinearmathTransform.origin.negate();
      jdField_field_1403_of_type_ComBulletphysicsLinearmathTransform.basis.set(this.jdField_field_1403_of_type_Class_797.getWorldTransform().basis);
    }
    else if (this.jdField_field_1403_of_type_Int == 1)
    {
      System.err.println("[SERVER] sector change is a jump. not normalizing object position");
    }
    else
    {
      throw new IllegalArgumentException("JUMP SIGN WRONG: " + this.jdField_field_1403_of_type_Int);
    }
    if (this.jdField_field_1403_of_type_Class_797.toString().contains("schema")) {
      System.err.println("[SERVER] Sector change " + this.jdField_field_1403_of_type_Class_797 + " from " + localclass_6701.field_136 + " to " + localclass_6702.field_136 + "; pos: " + this.jdField_field_1403_of_type_Class_797.getWorldTransform().origin + " -> " + jdField_field_1403_of_type_ComBulletphysicsLinearmathTransform.origin);
    }
    if (jdField_field_1403_of_type_ComBulletphysicsLinearmathTransform.origin.length() > Universe.getSectorSizeWithMargin() << 2)
    {
      jdField_field_1403_of_type_ComBulletphysicsLinearmathTransform.origin.normalize();
      jdField_field_1403_of_type_ComBulletphysicsLinearmathTransform.origin.scale(Universe.getSectorSizeWithMargin() / 3);
      System.err.println("[SERVER] Exception: Abnormal Sector change " + this.jdField_field_1403_of_type_Class_797 + " from " + localclass_6701.field_136 + " to " + localclass_6702.field_136 + "; CORRECTION ATTEMPT: pos: " + this.jdField_field_1403_of_type_Class_797.getWorldTransform().origin + " -> " + jdField_field_1403_of_type_ComBulletphysicsLinearmathTransform.origin);
    }
    this.jdField_field_1403_of_type_Class_797.getWorldTransform().set(jdField_field_1403_of_type_ComBulletphysicsLinearmathTransform);
    if (!this.jdField_field_1403_of_type_Class_797.isHidden()) {
      this.jdField_field_1403_of_type_Class_797.onPhysicsAdd();
    }
    synchronized (paramclass_1041.getLocalAndRemoteObjectContainer().getLocalObjects())
    {
      localIterator = paramclass_1041.getLocalAndRemoteObjectContainer().getLocalUpdatableObjects().values().iterator();
      while (localIterator.hasNext())
      {
        Sendable localSendable = (Sendable)localIterator.next();
        if ((!localHashSet.contains(localSendable)) && ((localSendable instanceof class_750)) && (((PairCachingGhostObjectAlignable)((class_750)localSendable).getPhysicsDataContainer().getObject()).getAttached() == this.jdField_field_1403_of_type_Class_797))
        {
          System.err.println("[SERVER] " + this.jdField_field_1403_of_type_Class_797 + " has ATTACHED gravity CHARACTER. Doing Sector Change for " + localSendable + ": " + localclass_6701 + " -> " + localclass_6702 + " ID " + localclass_6702.a3());
          ((class_750)localSendable).onPhysicsRemove();
          ((class_750)localSendable).setSectorId(localclass_6702.a3());
          ((class_750)localSendable).onPhysicsAdd();
          ((class_750)localSendable).sectorChangedDelay = 15;
          if ((localSendable instanceof class_365))
          {
            localObject1 = null;
            paramclass_1041 = ((class_365)localSendable).a26().iterator();
            while (paramclass_1041.hasNext())
            {
              localObject1 = (class_748)paramclass_1041.next();
              System.err.println("[SERVER] " + localSendable + " has players attached. Doing Sector Change for " + localObject1 + ": " + localclass_6701 + " -> " + localclass_6702);
              ((class_748)localObject1).a73(localclass_6702.field_136);
              ((class_748)localObject1).c13(localclass_6702.a3());
              ((class_748)localObject1).a129(new ServerMessage("WARNING:\nThe ransition to or from\na Star System might have\ndisorientated you", 1, ((class_748)localObject1).getId()));
              class_750 localclass_750;
              if ((localclass_750 = ((class_748)localObject1).a121()) != null)
              {
                System.err.println("[SERVER] " + this.jdField_field_1403_of_type_Class_797 + " has CHARACTER. Doing Sector Change for " + localclass_750 + ": " + localclass_6701 + " -> " + localclass_6702 + " ID " + localclass_6702.a3());
                localclass_750.setSectorId(localclass_6702.a3());
              }
              else
              {
                System.err.println("[SERVER] WARNING NO PLAYER CHARACTER ATTACHED TO " + localObject1);
              }
            }
          }
        }
      }
    }
    this.jdField_field_1403_of_type_Class_797.warpTransformable(jdField_field_1403_of_type_ComBulletphysicsLinearmathTransform, true);
  }
  
  private void a1(SegmentController paramSegmentController, class_670 paramclass_670)
  {
    if (((paramSegmentController instanceof SegmentController)) && (!(paramSegmentController = paramSegmentController).getDockingController().a5().isEmpty()))
    {
      paramSegmentController = paramSegmentController.getDockingController().a5().iterator();
      while (paramSegmentController.hasNext())
      {
        SegmentController localSegmentController1;
        class_670 localclass_670 = paramclass_670;
        SegmentController localSegmentController2;
        (localSegmentController2 = localSegmentController1 = ((ElementDocking)paramSegmentController.next()).from.a7().a15()).setSectorId(localclass_670.a3());
        if ((localSegmentController2 instanceof class_365))
        {
          Iterator localIterator = ((class_365)localSegmentController2).a26().iterator();
          while (localIterator.hasNext())
          {
            Object localObject = (class_748)localIterator.next();
            System.err.println("[SERVER] " + localSegmentController2 + " has players attached. Doing Sector Change for " + localObject);
            ((class_748)localObject).a73(localclass_670.field_136);
            ((class_748)localObject).c13(localclass_670.a3());
            if ((localObject = ((class_748)localObject).a121()) != null) {
              ((class_750)localObject).setSectorId(localclass_670.a3());
            }
          }
        }
        a1(localSegmentController1, paramclass_670);
      }
    }
  }
  
  public boolean equals(Object paramObject)
  {
    return ((class_1196)paramObject).jdField_field_1403_of_type_Class_797 == this.jdField_field_1403_of_type_Class_797;
  }
  
  public int hashCode()
  {
    return this.jdField_field_1403_of_type_Class_797.getId();
  }
  
  static
  {
    new Vector3f();
    jdField_field_1403_of_type_ComBulletphysicsLinearmathTransform = new Transform();
    jdField_field_1404_of_type_ComBulletphysicsLinearmathTransform = new Transform();
    jdField_field_1405_of_type_ComBulletphysicsLinearmathTransform = new Transform();
    jdField_field_1406_of_type_ComBulletphysicsLinearmathTransform = new Transform();
    jdField_field_1403_of_type_JavaxVecmathVector3f = new Vector3f();
    jdField_field_1403_of_type_JavaxVecmathMatrix4f = new Matrix4f();
    jdField_field_1404_of_type_JavaxVecmathMatrix4f = new Matrix4f();
    jdField_field_1404_of_type_Class_48 = new class_48();
    jdField_field_1405_of_type_Class_48 = new class_48();
    new Transform();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1196
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */