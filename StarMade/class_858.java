import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.linearmath.MotionState;
import com.bulletphysics.linearmath.Transform;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.io.PrintStream;
import java.util.ArrayList;
import javax.vecmath.Matrix3f;
import javax.vecmath.Matrix4f;
import javax.vecmath.Tuple3f;
import javax.vecmath.Vector3f;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.physics.PairCachingGhostObjectAlignable;
import org.schema.schine.network.Identifiable;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.client.ClientProcessor;
import org.schema.schine.network.client.ClientState;
import org.schema.schine.network.client.ClientStateInterface;
import org.schema.schine.network.objects.NetworkEntity;
import org.schema.schine.network.objects.NetworkTransformation;
import org.schema.schine.network.objects.container.PhysicsDataContainer;
import org.schema.schine.network.objects.remote.RemoteFloatArrayBuffer;
import org.schema.schine.network.objects.remote.RemoteFloatPrimitive;
import org.schema.schine.network.objects.remote.RemoteFloatPrimitiveArray;
import org.schema.schine.network.objects.remote.RemotePhysicsTransform;
import org.schema.schine.network.server.ServerStateInterface;

public abstract class class_858
  implements class_1382, class_1421
{
  private class_714 jdField_field_116_of_type_Class_714;
  private final Transform jdField_field_116_of_type_ComBulletphysicsLinearmathTransform;
  private PhysicsDataContainer jdField_field_116_of_type_OrgSchemaSchineNetworkObjectsContainerPhysicsDataContainer;
  private float jdField_field_116_of_type_Float = 0.1F;
  private Identifiable jdField_field_116_of_type_OrgSchemaSchineNetworkIdentifiable;
  private StateInterface jdField_field_116_of_type_OrgSchemaSchineNetworkStateInterface;
  public boolean field_116;
  public final ArrayList field_116;
  private Transform jdField_field_117_of_type_ComBulletphysicsLinearmathTransform = new Transform();
  private boolean jdField_field_117_of_type_Boolean;
  private final boolean jdField_field_118_of_type_Boolean;
  private final class_856 jdField_field_116_of_type_Class_856 = new class_856((byte)0);
  private final class_856 jdField_field_117_of_type_Class_856 = new class_856((byte)0);
  private int jdField_field_116_of_type_Int = 0;
  private Vector3f jdField_field_116_of_type_JavaxVecmathVector3f = new Vector3f();
  private final Transform jdField_field_118_of_type_ComBulletphysicsLinearmathTransform = new Transform();
  private int jdField_field_117_of_type_Int;
  private long jdField_field_116_of_type_Long;
  private final Vector3f jdField_field_117_of_type_JavaxVecmathVector3f = new Vector3f();
  
  public class_858(Identifiable paramIdentifiable, StateInterface paramStateInterface)
  {
    this.jdField_field_116_of_type_Boolean = true;
    this.jdField_field_116_of_type_JavaUtilArrayList = new ArrayList();
    this.jdField_field_116_of_type_OrgSchemaSchineNetworkIdentifiable = paramIdentifiable;
    this.jdField_field_116_of_type_OrgSchemaSchineNetworkStateInterface = paramStateInterface;
    this.jdField_field_118_of_type_Boolean = (paramStateInterface instanceof ServerStateInterface);
    this.jdField_field_116_of_type_Class_714 = new class_714(this);
    this.jdField_field_116_of_type_ComBulletphysicsLinearmathTransform = new Transform();
    this.jdField_field_116_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
    this.jdField_field_116_of_type_OrgSchemaSchineNetworkObjectsContainerPhysicsDataContainer = new PhysicsDataContainer();
  }
  
  public final void a5(Transform paramTransform, NetworkEntity paramNetworkEntity)
  {
    if ((!field_206) && (!(getState() instanceof ServerStateInterface))) {
      throw new AssertionError();
    }
    RemoteFloatPrimitiveArray localRemoteFloatPrimitiveArray = new RemoteFloatPrimitiveArray(16, paramNetworkEntity);
    paramTransform.getOpenGLMatrix(localRemoteFloatPrimitiveArray.getFloatArray());
    paramNetworkEntity.warpingTransformation.add(localRemoteFloatPrimitiveArray);
  }
  
  public final Identifiable a6()
  {
    return this.jdField_field_116_of_type_OrgSchemaSchineNetworkIdentifiable;
  }
  
  public final Transform a7()
  {
    return this.jdField_field_116_of_type_ComBulletphysicsLinearmathTransform;
  }
  
  public float getMass()
  {
    return this.jdField_field_116_of_type_Float;
  }
  
  public PhysicsDataContainer getPhysicsDataContainer()
  {
    return this.jdField_field_116_of_type_OrgSchemaSchineNetworkObjectsContainerPhysicsDataContainer;
  }
  
  public Transform getWorldTransform()
  {
    return this.jdField_field_116_of_type_OrgSchemaSchineNetworkObjectsContainerPhysicsDataContainer.getCurrentPhysicsTransform();
  }
  
  public final void a8(float paramFloat)
  {
    this.jdField_field_116_of_type_Float = paramFloat;
  }
  
  public final void a9(PhysicsDataContainer paramPhysicsDataContainer)
  {
    this.jdField_field_116_of_type_OrgSchemaSchineNetworkObjectsContainerPhysicsDataContainer = paramPhysicsDataContainer;
  }
  
  public final void a10(class_941 paramclass_941)
  {
    if (getPhysicsDataContainer().isInitialized())
    {
      Object localObject1 = this;
      if (this.jdField_field_116_of_type_Class_856.jdField_field_1096_of_type_Boolean) {
        synchronized (((class_858)localObject1).jdField_field_116_of_type_Class_856)
        {
          ((class_858)localObject1).jdField_field_117_of_type_Class_856.jdField_field_1096_of_type_Long = ((class_858)localObject1).jdField_field_116_of_type_Class_856.jdField_field_1096_of_type_Long;
          ((class_858)localObject1).jdField_field_117_of_type_Class_856.jdField_field_1096_of_type_ComBulletphysicsLinearmathTransform.set(((class_858)localObject1).jdField_field_116_of_type_Class_856.jdField_field_1096_of_type_ComBulletphysicsLinearmathTransform);
          ((class_858)localObject1).jdField_field_117_of_type_Class_856.jdField_field_1096_of_type_JavaxVecmathVector3f.set(((class_858)localObject1).jdField_field_116_of_type_Class_856.jdField_field_1096_of_type_JavaxVecmathVector3f);
          ((class_858)localObject1).jdField_field_117_of_type_Class_856.jdField_field_1097_of_type_JavaxVecmathVector3f.set(((class_858)localObject1).jdField_field_116_of_type_Class_856.jdField_field_1097_of_type_JavaxVecmathVector3f);
          ((class_858)localObject1).jdField_field_117_of_type_Class_856.jdField_field_1097_of_type_Boolean = ((class_858)localObject1).jdField_field_116_of_type_Class_856.jdField_field_1097_of_type_Boolean;
          ((class_858)localObject1).jdField_field_117_of_type_Class_856.jdField_field_1096_of_type_Boolean = true;
          ((class_858)localObject1).jdField_field_117_of_type_Class_856.field_1098 = true;
          ((class_858)localObject1).jdField_field_116_of_type_Class_856.jdField_field_1096_of_type_Boolean = false;
        }
      }
      if (((class_858)localObject1).jdField_field_117_of_type_Class_856.jdField_field_1096_of_type_Boolean)
      {
        ((class_858)localObject1).jdField_field_117_of_type_Class_856.jdField_field_1096_of_type_Boolean = false;
        if (((??? = ((class_858)localObject1).getPhysicsDataContainer().getObject()) instanceof RigidBody))
        {
          localRigidBody = (RigidBody)???;
          if ((((class_858)localObject1).jdField_field_117_of_type_Class_856.jdField_field_1097_of_type_Boolean) && (!localRigidBody.isStaticObject()))
          {
            localRigidBody.setLinearVelocity(((class_858)localObject1).jdField_field_117_of_type_Class_856.jdField_field_1096_of_type_JavaxVecmathVector3f);
            localRigidBody.setAngularVelocity(((class_858)localObject1).jdField_field_117_of_type_Class_856.jdField_field_1097_of_type_JavaxVecmathVector3f);
          }
        }
        RigidBody localRigidBody = null;
        int j = 0;
        if ((((((class_858)localObject1).jdField_field_116_of_type_OrgSchemaSchineNetworkIdentifiable instanceof class_797)) && (((class_797)((class_858)localObject1).jdField_field_116_of_type_OrgSchemaSchineNetworkIdentifiable).getSectorId() == ((class_371)((class_858)localObject1).getState()).a8()) ? 1 : ((class_858)localObject1).jdField_field_118_of_type_Boolean ? ((class_858)localObject1).jdField_field_116_of_type_OrgSchemaSchineNetworkIdentifiable instanceof class_797 : 0) != 0)
        {
          int i = 0;
          localTransform = null;
          if (((((class_858)localObject1).jdField_field_116_of_type_OrgSchemaSchineNetworkIdentifiable instanceof SegmentController)) && (((SegmentController)((class_858)localObject1).jdField_field_116_of_type_OrgSchemaSchineNetworkIdentifiable).getDockingController().b3())) {
            i = 1;
          }
          if ((i == 0) && (((class_858)localObject1).jdField_field_116_of_type_Boolean) && (!((class_797)((class_858)localObject1).jdField_field_116_of_type_OrgSchemaSchineNetworkIdentifiable).isHidden()))
          {
            new Vector3f(((class_858)localObject1).jdField_field_117_of_type_Class_856.jdField_field_1096_of_type_JavaxVecmathVector3f);
            ((class_858)localObject1).jdField_field_116_of_type_Class_714.a1(new Transform(((class_858)localObject1).jdField_field_117_of_type_Class_856.jdField_field_1096_of_type_ComBulletphysicsLinearmathTransform));
          }
        }
        else
        {
          ((class_858)localObject1).jdField_field_116_of_type_Class_714.a(((class_858)localObject1).jdField_field_117_of_type_Class_856.jdField_field_1096_of_type_ComBulletphysicsLinearmathTransform);
        }
      }
      while (!this.jdField_field_116_of_type_JavaUtilArrayList.isEmpty())
      {
        localObject1 = (Transform)this.jdField_field_116_of_type_JavaUtilArrayList.remove(0);
        Object localObject2;
        (localObject2 = getPhysicsDataContainer().getObject()).setWorldTransform((Transform)localObject1);
        ((CollisionObject)localObject2).setInterpolationWorldTransform((Transform)localObject1);
        if ((getPhysicsDataContainer().getObject() instanceof RigidBody))
        {
          ((RigidBody)getPhysicsDataContainer().getObject()).setCenterOfMassTransform((Transform)localObject1);
          localTransform = null;
          ((RigidBody)getPhysicsDataContainer().getObject()).getMotionState().setWorldTransform((Transform)localObject1);
        }
        getWorldTransform().set((Transform)localObject1);
        getPhysicsDataContainer().updatePhysical();
        this.jdField_field_116_of_type_Class_714.a3();
        if (((this.jdField_field_116_of_type_OrgSchemaSchineNetworkStateInterface instanceof class_371)) && (this.jdField_field_116_of_type_OrgSchemaSchineNetworkIdentifiable == ((class_371)this.jdField_field_116_of_type_OrgSchemaSchineNetworkStateInterface).a25()))
        {
          localTransform = null;
          if (((class_371)this.jdField_field_116_of_type_OrgSchemaSchineNetworkStateInterface).a14().a18().a79().a60().a51().a45().a38().c())
          {
            (localObject2 = (class_191)class_969.a1()).getWorldTransform().basis.set(((Transform)localObject1).basis);
            ((class_191)localObject2).a12(paramclass_941);
          }
        }
      }
      Transform localTransform = null;
      if ((this.jdField_field_118_of_type_Boolean) && ((this.jdField_field_116_of_type_OrgSchemaSchineNetworkIdentifiable instanceof class_797)) && (((class_797)this.jdField_field_116_of_type_OrgSchemaSchineNetworkIdentifiable).isConrolledByActivePlayer())) {
        if ((!(this.jdField_field_116_of_type_OrgSchemaSchineNetworkIdentifiable instanceof SegmentController)) || (!((SegmentController)this.jdField_field_116_of_type_OrgSchemaSchineNetworkIdentifiable).getDockingController().b3()))
        {
          boolean bool;
          if ((getPhysicsDataContainer().getObject() != null) && ((getPhysicsDataContainer().getObject() instanceof PairCachingGhostObjectAlignable)) && (((PairCachingGhostObjectAlignable)getPhysicsDataContainer().getObject()).getAttached() != null))
          {
            bool = true;
            localTransform = ((PairCachingGhostObjectAlignable)getPhysicsDataContainer().getObject()).localWorldTransform;
            this.jdField_field_117_of_type_JavaxVecmathVector3f.sub(this.jdField_field_117_of_type_Class_856.jdField_field_1096_of_type_ComBulletphysicsLinearmathTransform.origin, localTransform.origin);
          }
          else
          {
            bool = false;
            getWorldTransform();
            this.jdField_field_117_of_type_JavaxVecmathVector3f.sub(this.jdField_field_117_of_type_Class_856.jdField_field_1096_of_type_ComBulletphysicsLinearmathTransform.origin, getWorldTransform().origin);
          }
          if ((this.jdField_field_117_of_type_JavaxVecmathVector3f.length() > 15.0F) && (this.jdField_field_117_of_type_Class_856.field_1098))
          {
            System.err.println("[SERVER] snapped player controlled object " + this.jdField_field_116_of_type_OrgSchemaSchineNetworkIdentifiable + " from " + getWorldTransform().origin + " to " + this.jdField_field_117_of_type_Class_856.jdField_field_1096_of_type_ComBulletphysicsLinearmathTransform.origin + "; ATTACHED: " + bool);
            this.jdField_field_116_of_type_Class_714.a(new Transform(this.jdField_field_117_of_type_Class_856.jdField_field_1096_of_type_ComBulletphysicsLinearmathTransform));
          }
        }
      }
    }
    else
    {
      System.err.println("[WARNING] physics not inizialized;");
    }
  }
  
  public final void a11(NetworkEntity paramNetworkEntity)
  {
    if (((this.jdField_field_116_of_type_OrgSchemaSchineNetworkStateInterface instanceof ClientStateInterface)) && (getPhysicsDataContainer().isInitialized()))
    {
      this.jdField_field_116_of_type_Float = paramNetworkEntity.mass.get().floatValue();
      getPhysicsDataContainer().updateMass(getMass(), true);
    }
    this.jdField_field_116_of_type_ComBulletphysicsLinearmathTransform.setFromOpenGLMatrix(paramNetworkEntity.initialTransform.getFloatArray());
  }
  
  public final void b(NetworkEntity paramNetworkEntity)
  {
    if (((this.jdField_field_116_of_type_OrgSchemaSchineNetworkStateInterface instanceof ClientStateInterface)) && (getMass() != paramNetworkEntity.mass.get().floatValue()) && (getPhysicsDataContainer().isInitialized()))
    {
      this.jdField_field_116_of_type_Float = paramNetworkEntity.mass.get().floatValue();
      getPhysicsDataContainer().updateMass(getMass(), true);
    }
    Object localObject1 = paramNetworkEntity;
    class_858 localclass_858 = this;
    int i;
    Object localObject2;
    Transform localTransform;
    if (((getState() instanceof ClientState)) && (localclass_858.getPhysicsDataContainer().isInitialized())) {
      for (i = 0; i < ((NetworkEntity)localObject1).warpingTransformation.getReceiveBuffer().size(); i++)
      {
        localObject2 = (RemoteFloatPrimitiveArray)((NetworkEntity)localObject1).warpingTransformation.getReceiveBuffer().get(i);
        (localTransform = new Transform()).setFromOpenGLMatrix(((RemoteFloatPrimitiveArray)localObject2).getFloatArray());
        System.err.println("[REMOTE] Received warp: " + localclass_858.jdField_field_116_of_type_OrgSchemaSchineNetworkIdentifiable + ": " + localTransform.origin);
        localclass_858.jdField_field_116_of_type_JavaUtilArrayList.add(localTransform);
      }
    }
    localObject1 = paramNetworkEntity;
    localclass_858 = this;
    if ((!(getState() instanceof ClientState)) || (!localclass_858.jdField_field_117_of_type_Boolean))
    {
      i = 0;
      localObject2 = (NetworkTransformation)((NetworkEntity)localObject1).transformationBuffer.get();
      localTransform = ((NetworkTransformation)((NetworkEntity)localObject1).transformationBuffer.get()).getTransformReceive();
      paramNetworkEntity = new Vector3f(((NetworkTransformation)((NetworkEntity)localObject1).transformationBuffer.get()).getLinReceive());
      localObject1 = new Vector3f(((NetworkTransformation)((NetworkEntity)localObject1).transformationBuffer.get()).getAngReceive());
      long l;
      if (localclass_858.jdField_field_118_of_type_Boolean) {
        l = ((NetworkTransformation)localObject2).getTimeStampReceive();
      } else {
        l = ((ClientProcessor)((class_371)localclass_858.getState()).getProcessor()).getServerPacketSentTimestamp();
      }
      if (((NetworkTransformation)localObject2).received)
      {
        ((NetworkTransformation)localObject2).received = false;
        i = 1;
      }
      if (i != 0) {
        synchronized (localclass_858.jdField_field_116_of_type_Class_856)
        {
          localclass_858.jdField_field_116_of_type_Class_856.jdField_field_1096_of_type_Long = l;
          localclass_858.jdField_field_116_of_type_Class_856.jdField_field_1096_of_type_ComBulletphysicsLinearmathTransform.set(localTransform);
          localclass_858.jdField_field_116_of_type_Class_856.jdField_field_1096_of_type_JavaxVecmathVector3f.set(paramNetworkEntity);
          localclass_858.jdField_field_116_of_type_Class_856.jdField_field_1097_of_type_JavaxVecmathVector3f.set((Tuple3f)localObject1);
          localclass_858.jdField_field_116_of_type_Class_856.jdField_field_1097_of_type_Boolean = ((NetworkTransformation)localObject2).receivedVil;
          if ((!field_206) && (localclass_858.jdField_field_116_of_type_Class_856.jdField_field_1096_of_type_ComBulletphysicsLinearmathTransform.getMatrix(new Matrix4f()).determinant() == 0.0F)) {
            throw new AssertionError(localclass_858.jdField_field_116_of_type_OrgSchemaSchineNetworkIdentifiable + "\n" + localclass_858.jdField_field_116_of_type_Class_856.jdField_field_1096_of_type_ComBulletphysicsLinearmathTransform.getMatrix(new Matrix4f()));
          }
          ((NetworkTransformation)localObject2).receivedVil = false;
          localclass_858.jdField_field_116_of_type_Class_856.jdField_field_1096_of_type_Boolean = true;
          return;
        }
      }
    }
  }
  
  public final void c(NetworkEntity paramNetworkEntity)
  {
    if ((this.jdField_field_116_of_type_OrgSchemaSchineNetworkStateInterface instanceof ServerStateInterface))
    {
      paramNetworkEntity.mass.set(getMass());
      if (getPhysicsDataContainer().isInitialized()) {
        this.jdField_field_116_of_type_ComBulletphysicsLinearmathTransform.set(getWorldTransform());
      }
      this.jdField_field_116_of_type_ComBulletphysicsLinearmathTransform.getOpenGLMatrix(paramNetworkEntity.initialTransform.getFloatArray());
      paramNetworkEntity.initialTransform.setChanged(true);
      return;
    }
    paramNetworkEntity.initialTransform.forceClientUpdates();
    this.jdField_field_116_of_type_ComBulletphysicsLinearmathTransform.getOpenGLMatrix(paramNetworkEntity.initialTransform.getFloatArray());
    paramNetworkEntity.initialTransform.setChanged(true);
  }
  
  public final void d(NetworkEntity paramNetworkEntity)
  {
    PairCachingGhostObjectAlignable localPairCachingGhostObjectAlignable = null;
    if ((this.jdField_field_118_of_type_Boolean) && (paramNetworkEntity.mass.getFloat() != getMass())) {
      paramNetworkEntity.mass.set(getMass());
    }
    if ((getPhysicsDataContainer() == null) || (!getPhysicsDataContainer().isInitialized())) {
      return;
    }
    if ((this.jdField_field_118_of_type_Boolean) || (this.jdField_field_117_of_type_Boolean))
    {
      NetworkEntity localNetworkEntity = paramNetworkEntity;
      paramNetworkEntity = this;
      if (((!(this.jdField_field_116_of_type_OrgSchemaSchineNetworkIdentifiable instanceof SegmentController)) || (!((SegmentController)paramNetworkEntity.jdField_field_116_of_type_OrgSchemaSchineNetworkIdentifiable).getDockingController().b3())) && (paramNetworkEntity.getPhysicsDataContainer().isInitialized()) && ((!(paramNetworkEntity.jdField_field_116_of_type_OrgSchemaSchineNetworkIdentifiable instanceof class_797)) || (!((class_797)paramNetworkEntity.jdField_field_116_of_type_OrgSchemaSchineNetworkIdentifiable).isHidden())))
      {
        paramNetworkEntity.jdField_field_118_of_type_ComBulletphysicsLinearmathTransform.set(paramNetworkEntity.getPhysicsDataContainer().getCurrentPhysicsTransform());
        if (((paramNetworkEntity.getPhysicsDataContainer().getObject() instanceof PairCachingGhostObjectAlignable)) && ((localPairCachingGhostObjectAlignable = (PairCachingGhostObjectAlignable)paramNetworkEntity.getPhysicsDataContainer().getObject()).getAttached() != null)) {
          paramNetworkEntity.jdField_field_118_of_type_ComBulletphysicsLinearmathTransform.set(localPairCachingGhostObjectAlignable.localWorldTransform);
        }
        if ((!paramNetworkEntity.getPhysicsDataContainer().getObject().isStaticOrKinematicObject()) && (System.currentTimeMillis() - paramNetworkEntity.jdField_field_116_of_type_Long > 10000L) && (paramNetworkEntity.jdField_field_117_of_type_Int >= 4))
        {
          paramNetworkEntity.jdField_field_117_of_type_Int = 3;
          paramNetworkEntity.jdField_field_116_of_type_Int = 17;
        }
        boolean bool;
        if ((!(bool = paramNetworkEntity.jdField_field_117_of_type_ComBulletphysicsLinearmathTransform.equals(paramNetworkEntity.jdField_field_118_of_type_ComBulletphysicsLinearmathTransform))) || (paramNetworkEntity.jdField_field_117_of_type_Int < 4))
        {
          if (!bool) {
            paramNetworkEntity.jdField_field_117_of_type_Int = 0;
          } else {
            paramNetworkEntity.jdField_field_117_of_type_Int += 1;
          }
          ((NetworkTransformation)localNetworkEntity.transformationBuffer.get()).setTimeStamp(System.currentTimeMillis());
          ((NetworkTransformation)localNetworkEntity.transformationBuffer.get()).getTransform().set(paramNetworkEntity.jdField_field_118_of_type_ComBulletphysicsLinearmathTransform);
          Object localObject;
          if (((localObject = paramNetworkEntity.getPhysicsDataContainer().getObject()) instanceof RigidBody))
          {
            localObject = (RigidBody)localObject;
            ((NetworkTransformation)localNetworkEntity.transformationBuffer.get()).getLin().set(((RigidBody)localObject).getLinearVelocity(paramNetworkEntity.jdField_field_116_of_type_JavaxVecmathVector3f));
            ((NetworkTransformation)localNetworkEntity.transformationBuffer.get()).getAng().set(((RigidBody)localObject).getAngularVelocity(paramNetworkEntity.jdField_field_116_of_type_JavaxVecmathVector3f));
            if (paramNetworkEntity.jdField_field_116_of_type_Int > 16)
            {
              ((NetworkTransformation)localNetworkEntity.transformationBuffer.get()).sendVil = true;
              paramNetworkEntity.jdField_field_116_of_type_Int = 0;
            }
            else
            {
              ((NetworkTransformation)localNetworkEntity.transformationBuffer.get()).sendVil = false;
            }
          }
          if ((!field_206) && (((NetworkTransformation)localNetworkEntity.transformationBuffer.get()).getTransform().getMatrix(new Matrix4f()).determinant() == 0.0F)) {
            throw new AssertionError("\n" + paramNetworkEntity.jdField_field_116_of_type_Class_856.jdField_field_1096_of_type_ComBulletphysicsLinearmathTransform.getMatrix(new Matrix4f()));
          }
          localNetworkEntity.transformationBuffer.setChanged(true);
          localNetworkEntity.setChanged(true);
          paramNetworkEntity.jdField_field_117_of_type_ComBulletphysicsLinearmathTransform.set(paramNetworkEntity.jdField_field_118_of_type_ComBulletphysicsLinearmathTransform);
          paramNetworkEntity.jdField_field_116_of_type_Int += 1;
          paramNetworkEntity.jdField_field_116_of_type_Long = System.currentTimeMillis();
        }
      }
    }
  }
  
  public final void a12(Transform paramTransform)
  {
    if (this.jdField_field_116_of_type_OrgSchemaSchineNetworkObjectsContainerPhysicsDataContainer.getObject() != null)
    {
      Transform localTransform;
      (localTransform = this.jdField_field_116_of_type_OrgSchemaSchineNetworkObjectsContainerPhysicsDataContainer.getObject().getWorldTransform(new Transform())).origin.set(paramTransform.origin);
      (paramTransform = getPhysicsDataContainer().getObject()).setWorldTransform(localTransform);
      paramTransform.setInterpolationWorldTransform(localTransform);
      if ((getPhysicsDataContainer().getObject() instanceof RigidBody))
      {
        ((RigidBody)getPhysicsDataContainer().getObject()).setCenterOfMassTransform(localTransform);
        ((RigidBody)getPhysicsDataContainer().getObject()).getMotionState().setWorldTransform(localTransform);
      }
      getWorldTransform().set(localTransform);
      getPhysicsDataContainer().updatePhysical();
      this.jdField_field_116_of_type_Class_714.a3();
    }
  }
  
  public static boolean a13()
  {
    return false;
  }
  
  public final boolean b1()
  {
    return this.jdField_field_117_of_type_Boolean;
  }
  
  public final void a2(boolean paramBoolean)
  {
    this.jdField_field_117_of_type_Boolean = paramBoolean;
  }
  
  public final void a14()
  {
    this.jdField_field_117_of_type_Int = 0;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_858
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */