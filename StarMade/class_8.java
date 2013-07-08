import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
import com.bulletphysics.dynamics.DynamicsWorld;
import com.bulletphysics.linearmath.Transform;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.Set;
import javax.vecmath.Matrix3f;
import javax.vecmath.Matrix4f;
import javax.vecmath.Tuple3f;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import org.schema.game.common.controller.EditableSendableSegmentController;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.ShieldContainerInterface;
import org.schema.game.common.controller.elements.shield.ShieldCollectionManager;
import org.schema.game.common.data.element.Element;
import org.schema.game.common.data.element.ElementDocking;
import org.schema.game.common.data.physics.CubeRayCastResult;
import org.schema.game.common.data.physics.ModifiedDynamicsWorld;
import org.schema.game.common.data.physics.PhysicsExt;
import org.schema.game.common.data.world.Segment;
import org.schema.game.common.data.world.SegmentData;
import org.schema.game.common.data.world.Universe;
import org.schema.schine.network.ControllerInterface;
import org.schema.schine.network.Identifiable;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.objects.Sendable;

public class class_8
  extends class_944
{
  private StateInterface jdField_field_9_of_type_OrgSchemaSchineNetworkStateInterface;
  private int jdField_field_9_of_type_Int;
  private Vector3f jdField_field_9_of_type_JavaxVecmathVector3f = new Vector3f();
  private Vector4f jdField_field_9_of_type_JavaxVecmathVector4f = new Vector4f();
  private Vector3f jdField_field_10_of_type_JavaxVecmathVector3f = new Vector3f();
  private Vector3f field_11 = new Vector3f();
  private Vector3f field_12 = new Vector3f();
  private Vector3f field_13 = new Vector3f();
  private boolean jdField_field_9_of_type_Boolean;
  private Segment jdField_field_9_of_type_OrgSchemaGameCommonDataWorldSegment;
  
  public class_8(StateInterface paramStateInterface, int paramInt)
  {
    new Vector3f();
    this.jdField_field_9_of_type_OrgSchemaSchineNetworkStateInterface = paramStateInterface;
    this.jdField_field_9_of_type_Int = paramInt;
  }
  
  public final void a(SegmentController paramSegmentController, Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat1, float paramFloat2)
  {
    paramVector3f1 = super.a14(paramVector3f1, paramVector3f2);
    this.field_9.c1(paramVector3f1, paramFloat1, paramFloat2, paramSegmentController.getId());
  }
  
  private boolean a1(SegmentController paramSegmentController, Vector3f paramVector3f1, Vector3f paramVector3f2, int paramInt)
  {
    if (((paramVector3f1 = a2().testRayCollisionPoint(paramVector3f1, paramVector3f2, false, paramSegmentController, null, true, this.jdField_field_9_of_type_OrgSchemaGameCommonDataWorldSegment, false)).hasHit()) && (paramVector3f1.collisionObject != null))
    {
      if ((!jdField_field_10_of_type_Boolean) && (paramVector3f1.collisionObject.getUserPointer() == null)) {
        throw new AssertionError(paramVector3f1.collisionObject);
      }
      synchronized (this.jdField_field_9_of_type_OrgSchemaSchineNetworkStateInterface.getLocalAndRemoteObjectContainer().getLocalObjects())
      {
        paramVector3f2 = (Sendable)this.jdField_field_9_of_type_OrgSchemaSchineNetworkStateInterface.getLocalAndRemoteObjectContainer().getLocalUpdatableObjects().get(paramVector3f1.collisionObject.getUserPointer());
      }
      if (((paramVector3f1 instanceof CubeRayCastResult)) && (((CubeRayCastResult)paramVector3f1).getSegment() != null))
      {
        SegmentController localSegmentController = (??? = (CubeRayCastResult)paramVector3f1).getSegment().a16().getSegmentController();
        int i = 0;
        int j = 0;
        Object localObject2;
        if (paramSegmentController.getDockingController().b3())
        {
          i = (localObject2 = paramSegmentController.getDockingController().a4().field_1740.a7().a15()) == localSegmentController ? 1 : 0;
          Iterator localIterator = ((SegmentController)localObject2).getDockingController().a5().iterator();
          while (localIterator.hasNext()) {
            if (((ElementDocking)localIterator.next()).from.a7().a15().equals(localSegmentController))
            {
              j = 1;
              break;
            }
          }
        }
        if ((i != 0) || (j != 0)) {
          return true;
        }
        if ((!paramSegmentController.equals(localSegmentController)) && (i == 0) && (j == 0))
        {
          localObject2 = ((CubeRayCastResult)???).getSegment();
          this.jdField_field_9_of_type_OrgSchemaGameCommonDataWorldSegment = ((CubeRayCastResult)???).getSegment();
          float f2 = this.field_9.c(paramInt, this.field_11).field_615;
          if (((((Segment)localObject2).a15() instanceof EditableSendableSegmentController)) && (!((EditableSendableSegmentController)((Segment)localObject2).a15()).canAttack(paramSegmentController))) {
            return true;
          }
          if (((((Segment)localObject2).a15() instanceof class_798)) && (((paramVector3f1 = ((class_798)((Segment)localObject2).a15()).a()) instanceof ShieldContainerInterface)) && ((paramVector3f1 = (ShieldContainerInterface)paramVector3f1).getShieldManager().getShields() > 0.0D) && ((f2 = (float)paramVector3f1.handleShieldHit(((CubeRayCastResult)???).hitPointWorld, paramSegmentController, f2)) <= 0.0F)) {
            return true;
          }
          ((Segment)localObject2).a15().handleHit((CollisionWorld.ClosestRayResultCallback)???, paramSegmentController, f2);
          return true;
        }
      }
      ??? = (class_1421)paramVector3f2;
      if ((paramVector3f1.hasHit()) && ((??? == null) || (paramSegmentController.getId() != ((Identifiable)???).getId())))
      {
        if (??? == null)
        {
          if ((this.jdField_field_9_of_type_OrgSchemaSchineNetworkStateInterface instanceof class_371)) {
            ((class_371)this.jdField_field_9_of_type_OrgSchemaSchineNetworkStateInterface).a27().a91().a22(paramVector3f1);
          }
        }
        else if (((this.jdField_field_9_of_type_OrgSchemaSchineNetworkStateInterface instanceof StateInterface)) && ((paramVector3f2 instanceof C)))
        {
          float f1 = this.field_9.c(paramInt, this.field_11).field_615;
          ((C)paramVector3f2).handleHit(paramVector3f1, paramSegmentController, f1);
        }
        return true;
      }
    }
    else if (paramVector3f1.hasHit())
    {
      return true;
    }
    return false;
  }
  
  private PhysicsExt a2()
  {
    if ((this.jdField_field_9_of_type_OrgSchemaSchineNetworkStateInterface instanceof class_1041)) {
      return (PhysicsExt)((class_1041)this.jdField_field_9_of_type_OrgSchemaSchineNetworkStateInterface).a62().getSector(this.jdField_field_9_of_type_Int).a64();
    }
    return (PhysicsExt)((class_371)this.jdField_field_9_of_type_OrgSchemaSchineNetworkStateInterface).a19();
  }
  
  public final StateInterface a3()
  {
    return this.jdField_field_9_of_type_OrgSchemaSchineNetworkStateInterface;
  }
  
  protected final void a4()
  {
    this.jdField_field_9_of_type_Boolean = true;
  }
  
  public final void a5(int paramInt)
  {
    this.jdField_field_9_of_type_Int = paramInt;
  }
  
  public final void a6(class_941 paramclass_941)
  {
    if (b1() > 0)
    {
      if (a2().getDynamicsWorld().getNumCollisionObjects() > 32) {
        ((ModifiedDynamicsWorld)a2().getDynamicsWorld()).buildCache();
      }
      super.a6(paramclass_941);
      ((ModifiedDynamicsWorld)a2().getDynamicsWorld()).cacheValid = false;
    }
  }
  
  public final boolean a7(int paramInt, class_941 paramclass_941)
  {
    this.field_9.d(paramInt, this.jdField_field_9_of_type_JavaxVecmathVector3f);
    this.field_9.a2(paramInt, this.jdField_field_9_of_type_JavaxVecmathVector4f);
    this.field_9.a4(paramInt, this.jdField_field_10_of_type_JavaxVecmathVector3f);
    this.field_9.b(paramInt, this.field_12);
    this.field_13.set(this.jdField_field_10_of_type_JavaxVecmathVector3f);
    float f1 = this.field_9.a3(paramInt);
    if (this.jdField_field_9_of_type_JavaxVecmathVector3f.length() == 0.0F)
    {
      this.field_9.a6(paramInt, (float)(f1 + paramclass_941.a() * 1000.1D));
    }
    else
    {
      this.jdField_field_9_of_type_JavaxVecmathVector3f.scale((float)(paramclass_941.a() * 1000.0D));
      this.field_9.a6(paramInt, f1 + this.jdField_field_9_of_type_JavaxVecmathVector3f.length());
      this.jdField_field_10_of_type_JavaxVecmathVector3f.add(this.jdField_field_9_of_type_JavaxVecmathVector3f);
    }
    this.field_9.a7(paramInt, this.jdField_field_10_of_type_JavaxVecmathVector3f.field_615, this.jdField_field_10_of_type_JavaxVecmathVector3f.field_616, this.jdField_field_10_of_type_JavaxVecmathVector3f.field_617);
    float f2 = (paramclass_941 = this.field_9.c(paramInt, this.field_11)).field_616;
    paramclass_941 = (int)paramclass_941.field_617;
    Sendable localSendable;
    if (((localSendable = (Sendable)this.jdField_field_9_of_type_OrgSchemaSchineNetworkStateInterface.getLocalAndRemoteObjectContainer().getLocalObjects().get(paramclass_941)) != null) && ((localSendable instanceof SegmentController)))
    {
      paramclass_941 = a1((SegmentController)localSendable, this.field_13, this.jdField_field_10_of_type_JavaxVecmathVector3f, paramInt);
      if (this.jdField_field_9_of_type_Boolean) {
        this.jdField_field_9_of_type_Boolean = false;
      }
      if ((paramclass_941 == 0) && ((this.jdField_field_9_of_type_OrgSchemaSchineNetworkStateInterface instanceof class_1041)) && ((Math.abs(this.jdField_field_10_of_type_JavaxVecmathVector3f.field_615) > Universe.getSectorSizeWithMargin() / 3) || (Math.abs(this.jdField_field_10_of_type_JavaxVecmathVector3f.field_616) > Universe.getSectorSizeWithMargin() / 3) || (Math.abs(this.jdField_field_10_of_type_JavaxVecmathVector3f.field_617) > Universe.getSectorSizeWithMargin() / 3)) && (a8(this.jdField_field_10_of_type_JavaxVecmathVector3f, paramInt))) {
        return false;
      }
      return (f1 < f2) && (paramclass_941 == 0);
    }
    System.err.println("Exception: No owner for particle found: " + paramclass_941 + " -> " + localSendable);
    return false;
  }
  
  private boolean a8(Vector3f paramVector3f, int paramInt)
  {
    if ((!jdField_field_10_of_type_Boolean) && (!(this.jdField_field_9_of_type_OrgSchemaSchineNetworkStateInterface instanceof class_1041))) {
      throw new AssertionError();
    }
    Vector3f localVector3f1 = new Vector3f(paramVector3f);
    class_670 localclass_6701;
    if ((localclass_6701 = ((class_1041)this.jdField_field_9_of_type_OrgSchemaSchineNetworkStateInterface).a62().getSector(this.jdField_field_9_of_type_Int)) != null)
    {
      class_48 localclass_481 = localclass_6701.field_136;
      int i = -1;
      Vector3f localVector3f2 = new Vector3f(localVector3f1);
      class_48 localclass_482 = new class_48();
      boolean bool = class_791.a19(localclass_6701.field_136);
      for (int j = 0; j < Element.DIRECTIONSi.length; j++)
      {
        Object localObject;
        (localObject = new class_48(Element.DIRECTIONSi[j])).a1(localclass_481);
        Transform localTransform;
        (localTransform = new Transform()).setIdentity();
        if (bool)
        {
          Universe.calcSecPos(localclass_481, (class_48)localObject, this.jdField_field_9_of_type_OrgSchemaSchineNetworkStateInterface.getController().calculateStartTime(), System.currentTimeMillis(), localTransform);
        }
        else
        {
          localTransform.origin.set(Element.DIRECTIONSi[j].field_475, Element.DIRECTIONSi[j].field_476, Element.DIRECTIONSi[j].field_477);
          localTransform.origin.scale(Universe.getSectorSizeWithMargin());
        }
        (localObject = new Vector3f()).sub(localVector3f1, localTransform.origin);
        if (((Vector3f)localObject).lengthSquared() < localVector3f2.lengthSquared())
        {
          localVector3f2.set((Tuple3f)localObject);
          i = j;
        }
      }
      if (i >= 0)
      {
        localclass_482.b1(localclass_481);
        localclass_482.a1(Element.DIRECTIONSi[i]);
      }
      else
      {
        return false;
      }
      try
      {
        if (((class_1041)this.jdField_field_9_of_type_OrgSchemaSchineNetworkStateInterface).a62().isSectorLoaded(localclass_482))
        {
          class_670 localclass_6702 = ((class_1041)this.jdField_field_9_of_type_OrgSchemaSchineNetworkStateInterface).a62().getSector(localclass_482);
          a10(this.jdField_field_9_of_type_OrgSchemaSchineNetworkStateInterface, localclass_6701, localclass_6702, localVector3f1, paramVector3f);
          a9(localclass_6702.a63(), paramInt, paramVector3f);
          return true;
        }
      }
      catch (Exception localException)
      {
        localException;
      }
    }
    else
    {
      System.err.println("[SERVER][PROJECTILE] Stopping projectile: out of loaded sector range");
    }
    return true;
  }
  
  private void a9(class_8 paramclass_8, int paramInt, Vector3f paramVector3f)
  {
    if ((localclass_81 = paramclass_8).field_10 >= localclass_81.field_9.a1() - 1) {
      localclass_81.field_9.a8();
    }
    class_8 localclass_82 = localclass_81.field_10 % localclass_81.field_9.a1();
    localclass_81.field_10 += 1;
    class_8 localclass_81 = localclass_82;
    float[] arrayOfFloat1 = this.field_9.a5();
    float[] arrayOfFloat2 = paramclass_8.field_9.a5();
    for (int i = 0; i < 17; i++) {
      arrayOfFloat2[(localclass_81 * 17 + i)] = arrayOfFloat1[(paramInt * 17 + i)];
    }
    paramclass_8.field_9.a7(localclass_81, paramVector3f.field_615, paramVector3f.field_616, paramVector3f.field_617);
  }
  
  public static void a10(StateInterface paramStateInterface, class_670 paramclass_6701, class_670 paramclass_6702, Vector3f paramVector3f1, Vector3f paramVector3f2)
  {
    Transform localTransform1;
    (localTransform1 = new Transform()).setIdentity();
    localTransform1.origin.set(paramVector3f1);
    Universe.calcSecPos(paramclass_6701.field_136, paramclass_6702.field_136, paramStateInterface.getController().calculateStartTime(), System.currentTimeMillis(), localTransform1);
    Transform localTransform2;
    (localTransform2 = new Transform()).setIdentity();
    Transform localTransform3;
    (localTransform3 = new Transform()).setIdentity();
    paramStateInterface = (float)((System.currentTimeMillis() - paramStateInterface.getController().calculateStartTime()) % 1200000L) / 1200000.0F;
    class_48 localclass_48 = class_789.a192(paramclass_6701.field_136, new class_48());
    if (!class_791.a19(paramclass_6701.field_136)) {
      paramStateInterface = 0.0F;
    }
    Object localObject = new class_48(localclass_48);
    paramclass_6701 = new class_48(paramclass_6701.field_136);
    ((class_48)localObject).a5(16);
    ((class_48)localObject).a(8, 8, 8);
    ((class_48)localObject).c1(paramclass_6701);
    (paramclass_6701 = new Vector3f()).set(((class_48)localObject).field_475 * Universe.getSectorSizeWithMargin(), ((class_48)localObject).field_476 * Universe.getSectorSizeWithMargin(), ((class_48)localObject).field_477 * Universe.getSectorSizeWithMargin());
    localTransform2.setIdentity();
    localTransform2.origin.add(paramclass_6701);
    localTransform2.basis.rotX(6.283186F * paramStateInterface);
    localTransform2.basis.transform(localTransform2.origin);
    (paramclass_6701 = new Transform()).setIdentity();
    paramclass_6701.origin.set(paramVector3f1);
    paramclass_6701.origin.negate();
    localTransform2.origin.add(paramclass_6701.origin);
    localObject = new class_48(localclass_48);
    paramclass_6701 = new class_48(paramclass_6702.field_136);
    ((class_48)localObject).a5(16);
    ((class_48)localObject).a(8, 8, 8);
    ((class_48)localObject).c1(paramclass_6701);
    (paramclass_6701 = new Vector3f()).set(((class_48)localObject).field_475 * Universe.getSectorSizeWithMargin(), ((class_48)localObject).field_476 * Universe.getSectorSizeWithMargin(), ((class_48)localObject).field_477 * Universe.getSectorSizeWithMargin());
    localTransform3.setIdentity();
    localTransform3.origin.add(paramclass_6701);
    localTransform3.basis.rotX(6.283186F * paramStateInterface);
    localTransform3.basis.transform(localTransform3.origin);
    localObject = new Matrix4f();
    paramclass_6701 = new Matrix4f();
    localTransform2.getMatrix((Matrix4f)localObject);
    localTransform3.getMatrix(paramclass_6701);
    new Transform(localTransform2);
    localTransform3.inverse();
    localTransform2.mul(localTransform3);
    localTransform1.origin.set(localTransform2.origin);
    localTransform1.origin.negate();
    paramVector3f2.set(localTransform1.origin);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_8
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */