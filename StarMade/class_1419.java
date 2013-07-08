import com.bulletphysics.collision.broadphase.AxisSweep3;
import com.bulletphysics.collision.broadphase.BroadphaseInterface;
import com.bulletphysics.collision.broadphase.BroadphasePair;
import com.bulletphysics.collision.broadphase.BroadphaseProxy;
import com.bulletphysics.collision.broadphase.HashedOverlappingPairCache;
import com.bulletphysics.collision.dispatch.CollisionConfiguration;
import com.bulletphysics.collision.dispatch.CollisionDispatcher;
import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
import com.bulletphysics.collision.dispatch.CollisionWorld.ConvexResultCallback;
import com.bulletphysics.collision.dispatch.DefaultCollisionConfiguration;
import com.bulletphysics.collision.dispatch.PairCachingGhostObject;
import com.bulletphysics.collision.shapes.BoxShape;
import com.bulletphysics.collision.shapes.BvhTriangleMeshShape;
import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.collision.shapes.CompoundShape;
import com.bulletphysics.collision.shapes.ConvexHullShape;
import com.bulletphysics.collision.shapes.CylinderShape;
import com.bulletphysics.collision.shapes.CylinderShapeX;
import com.bulletphysics.collision.shapes.CylinderShapeZ;
import com.bulletphysics.collision.shapes.ShapeHull;
import com.bulletphysics.collision.shapes.StridingMeshInterface;
import com.bulletphysics.collision.shapes.TriangleIndexVertexArray;
import com.bulletphysics.dynamics.ActionInterface;
import com.bulletphysics.dynamics.DiscreteDynamicsWorld;
import com.bulletphysics.dynamics.DynamicsWorld;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.dynamics.RigidBodyConstructionInfo;
import com.bulletphysics.dynamics.constraintsolver.ConstraintSolver;
import com.bulletphysics.dynamics.constraintsolver.SequentialImpulseConstraintSolver;
import com.bulletphysics.dynamics.constraintsolver.TypedConstraint;
import com.bulletphysics.dynamics.vehicle.DefaultVehicleRaycaster;
import com.bulletphysics.dynamics.vehicle.RaycastVehicle;
import com.bulletphysics.dynamics.vehicle.VehicleTuning;
import com.bulletphysics.extras.gimpact.GImpactCollisionAlgorithm;
import com.bulletphysics.extras.gimpact.GImpactMeshShape;
import com.bulletphysics.linearmath.DefaultMotionState;
import com.bulletphysics.linearmath.IDebugDraw;
import com.bulletphysics.linearmath.MotionState;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.util.ObjectArrayList;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.vecmath.Quat4f;
import javax.vecmath.Tuple3f;
import javax.vecmath.Vector3f;
import org.lwjgl.opengl.GL15;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.graphicsengine.forms.Mesh;
import org.schema.schine.network.Identifiable;
import org.schema.schine.network.objects.container.PhysicsDataContainer;
import org.schema.schine.physics.CollisionShapeNotFoundException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class class_1419
{
  public static final float PHYSICS_GRAVITY = 0.0F;
  private Vector vehicles = new Vector();
  private BroadphaseInterface overlappingPairCache;
  private CollisionDispatcher dispatcher;
  private ConstraintSolver solver;
  private CollisionConfiguration collisionConfiguration;
  protected DynamicsWorld dynamicsWorld;
  private class_1407 state;
  private final Transform tempTrans = new Transform();
  private HashMap shapeEntityMap = new HashMap();
  public HashMap dataConstraintMap = new HashMap();
  float counter;
  private float iterations = 25.0F;
  private boolean hitIndicator;
  private final List physicsExceptions = new ArrayList();
  protected static ThreadLocal threadLocal = new class_1417();
  public static class_1409 helpervars;
  
  public class_1419(class_1407 paramclass_1407)
  {
    setState(paramclass_1407);
    initPhysics();
  }
  
  public String physicsSlowMsg()
  {
    return getState().b();
  }
  
  public RigidBody addBodyFromShape(class_1421 paramclass_1421, float paramFloat)
  {
    CollisionShape localCollisionShape = paramclass_1421.getPhysicsDataContainer().getShape();
    assert (localCollisionShape != null);
    Transform localTransform = paramclass_1421.getPhysicsDataContainer().initialTransform;
    assert (localTransform != null);
    (paramFloat = getBodyFromShape(localCollisionShape, paramFloat, localTransform)).setUserPointer(Integer.valueOf(((Identifiable)paramclass_1421).getId()));
    if (((paramclass_1421 instanceof class_1423)) && (((class_1423)paramclass_1421).a1().equals("split"))) {
      ((DiscreteDynamicsWorld)this.dynamicsWorld).addRigidBody(paramFloat, (short)1, (short)8);
    } else {
      addObject(paramFloat);
    }
    paramclass_1421.getPhysicsDataContainer().setObject(paramFloat);
    return paramFloat;
  }
  
  public void addCollisionObjectFromEntity(class_1421 paramclass_1421, short paramShort1, short paramShort2)
  {
    CollisionObject localCollisionObject;
    (localCollisionObject = paramclass_1421.getPhysicsDataContainer().getObject()).setUserPointer(Integer.valueOf(((Identifiable)paramclass_1421).getId()));
    System.err.println("[PHYSICS] adding new object to world: " + localCollisionObject.getClass().getSimpleName() + ", Shape: " + localCollisionObject.getCollisionShape().getClass().getSimpleName() + ", userP: " + localCollisionObject.getUserPointer());
    localCollisionObject.setRestitution(0.3F);
    localCollisionObject.setFriction(0.7F);
    ((DiscreteDynamicsWorld)this.dynamicsWorld).addCollisionObject(localCollisionObject, paramShort1, paramShort2);
  }
  
  private void addCompound(class_1421 paramclass_1421, class_984 paramclass_984, CompoundShape paramCompoundShape)
  {
    if ((paramclass_984 instanceof class_996))
    {
      paramclass_984 = (class_996)paramclass_984;
      Object localObject;
      try
      {
        localObject = getShapeTypeFromUserdata(paramclass_984);
      }
      catch (CollisionShapeNotFoundException localCollisionShapeNotFoundException)
      {
        localCollisionShapeNotFoundException.printStackTrace();
        return;
      }
      Vector3f localVector3f = paramclass_984.d7();
      Quat4f localQuat4f = new Quat4f(paramclass_984.b22());
      Transform localTransform;
      (localTransform = new Transform()).setIdentity();
      localTransform.origin.set(localVector3f);
      localTransform.setRotation(localQuat4f);
      if (((String)localObject).equals("box")) {
        paramCompoundShape.addChildShape(localTransform, createBox(paramclass_984));
      }
      if (((String)localObject).equals("cylinder")) {
        paramCompoundShape.addChildShape(localTransform, createCylinder(paramclass_984));
      }
      if (((String)localObject).equals("triangle")) {
        paramCompoundShape.addChildShape(localTransform, createTriangle(paramclass_984));
      }
      if (((String)localObject).equals("mesh")) {
        paramCompoundShape.addChildShape(localTransform, createMeshShape((Mesh)paramclass_984, false));
      }
      if (((String)localObject).equals("convexhull")) {
        paramCompoundShape.addChildShape(localTransform, createConvexHullShape(paramclass_984.jdField_field_89_of_type_ArrayOfJavaxVecmathVector3f, new Vector3f(), true));
      }
      if (paramclass_984.a152().size() > 0)
      {
        paramclass_984 = paramclass_984.a152().iterator();
        while (paramclass_984.hasNext())
        {
          localObject = (class_984)paramclass_984.next();
          addCompound(paramclass_1421, (class_984)localObject, paramCompoundShape);
        }
      }
    }
  }
  
  protected void addGround()
  {
    Object localObject1 = new BoxShape(new Vector3f(1000.0F, 20.0F, 1000.0F));
    Vector3f localVector3f = new Vector3f(0.0F, 0.0F, 0.0F);
    (localObject2 = new Transform()).setIdentity();
    ((Transform)localObject2).origin.set(0.0F, -500.0F, 0.0F);
    Object localObject2 = new DefaultMotionState((Transform)localObject2);
    localObject1 = new RigidBodyConstructionInfo(0.0F, (MotionState)localObject2, (CollisionShape)localObject1, localVector3f);
    (localObject1 = new RigidBody((RigidBodyConstructionInfo)localObject1)).forceActivationState(1);
    ((RigidBody)localObject1).setRestitution(0.0F);
    ((RigidBody)localObject1).setFriction(0.2F);
    ((RigidBody)localObject1).setDamping(0.1F, 0.1F);
    ((DiscreteDynamicsWorld)this.dynamicsWorld).addRigidBody((RigidBody)localObject1, (short)9, (short)1);
  }
  
  public void addObject(CollisionObject paramCollisionObject)
  {
    addObject(paramCollisionObject, (short)-1, (short)-1);
  }
  
  public void addObject(CollisionObject paramCollisionObject, short paramShort1, short paramShort2)
  {
    if (!containsObject(paramCollisionObject))
    {
      if ((paramCollisionObject instanceof RigidBody))
      {
        ((DiscreteDynamicsWorld)this.dynamicsWorld).addRigidBody((RigidBody)paramCollisionObject, paramShort1, paramShort2);
        return;
      }
      ((DiscreteDynamicsWorld)this.dynamicsWorld).addCollisionObject(paramCollisionObject, paramShort1, paramShort2);
    }
  }
  
  @Deprecated
  public void addShapeFromEntity(class_1423 paramclass_1423)
  {
    Object localObject1 = paramclass_1423.a2();
    if ("split".equals(paramclass_1423.a1())) {
      localObject1 = class_1004.a166();
    }
    localObject1 = ((class_1004)localObject1).a152().iterator();
    while (((Iterator)localObject1).hasNext())
    {
      Object localObject2;
      if (((localObject2 = (class_984)((Iterator)localObject1).next()) instanceof class_996))
      {
        CompoundShape localCompoundShape = null;
        if (((class_984)localObject2).a152().size() > 0) {
          localCompoundShape = new CompoundShape();
        }
        localObject2 = (class_996)localObject2;
        try
        {
          localObject3 = getShapeFromUserdata((class_996)localObject2);
        }
        catch (CollisionShapeNotFoundException localCollisionShapeNotFoundException)
        {
          localCollisionShapeNotFoundException;
        }
        continue;
        if ((localCompoundShape instanceof CompoundShape))
        {
          (localObject4 = new Transform()).setIdentity();
          ((CompoundShape)localCompoundShape).addChildShape((Transform)localObject4, (CollisionShape)localObject3);
          localObject3 = ((class_996)localObject2).a152().iterator();
          while (((Iterator)localObject3).hasNext())
          {
            localObject4 = (class_984)((Iterator)localObject3).next();
            addCompound(paramclass_1423, (class_984)localObject4, (CompoundShape)localCompoundShape);
          }
        }
        Object localObject4 = ((class_996)localObject2).d7();
        Object localObject3 = new Quat4f(((class_996)localObject2).b22());
        ((class_996)localObject2).c10();
        (localObject2 = new Transform()).setIdentity();
        ((Transform)localObject2).origin.set((Tuple3f)localObject4);
        ((Transform)localObject2).setRotation((Quat4f)localObject3);
      }
    }
    spawnAddedObject(paramclass_1423, 1.0F);
  }
  
  public class_1403 addTimedPhysicsException(CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2, int paramInt)
  {
    paramCollisionObject1 = new class_1403(paramCollisionObject1, paramCollisionObject2, System.currentTimeMillis(), paramInt);
    getPhysicsExceptions().add(paramCollisionObject1);
    return paramCollisionObject1;
  }
  
  public boolean containsAction(ActionInterface paramActionInterface)
  {
    for (int i = 0; i < getDynamicsWorld().getNumActions(); i++) {
      if (getDynamicsWorld().getAction(i) == paramActionInterface) {
        return true;
      }
    }
    return false;
  }
  
  public boolean containsObject(CollisionObject paramCollisionObject)
  {
    return ((DiscreteDynamicsWorld)this.dynamicsWorld).getCollisionObjectArray().contains(paramCollisionObject);
  }
  
  private CollisionShape createBox(class_996 paramclass_996)
  {
    Object localObject = new Vector3f();
    paramclass_996.a151().a1((Vector3f)localObject);
    localObject = new BoxShape((Vector3f)localObject);
    if (!((Mesh)paramclass_996).a4()) {
      System.err.println("[PHYSICS] WARNING: trying to create BOX shape for an object, which's pivot is not centered. this could result in wrong positioned physics shapes!");
    }
    ((CollisionShape)localObject).setMargin(0.01F);
    return localObject;
  }
  
  public CollisionShape createConvexHullShape(Vector3f[] paramArrayOfVector3f, Vector3f paramVector3f, boolean paramBoolean)
  {
    ObjectArrayList localObjectArrayList = new ObjectArrayList(paramArrayOfVector3f.length);
    int i = (paramArrayOfVector3f = paramArrayOfVector3f).length;
    Object localObject;
    for (int j = 0; j < i; j++)
    {
      (localObject = paramArrayOfVector3f[j]).add(paramVector3f);
      localObjectArrayList.add(localObject);
    }
    paramArrayOfVector3f = new ConvexHullShape(localObjectArrayList);
    if (!paramBoolean) {
      return paramArrayOfVector3f;
    }
    ShapeHull localShapeHull = new ShapeHull(paramArrayOfVector3f);
    float f = paramArrayOfVector3f.getMargin();
    localShapeHull.buildHull(f);
    (localObject = new ConvexHullShape(localShapeHull.getVertexPointer())).recalcLocalAabb();
    return localObject;
  }
  
  private CollisionShape createCylinder(class_996 paramclass_996)
  {
    Object localObject = paramclass_996.a163();
    Vector3f localVector3f = new Vector3f();
    paramclass_996.a151().a1(localVector3f);
    if ((localObject = ((Document)localObject).getElementsByTagName("CylinderOrientation").item(0).getTextContent()).equals("Z"))
    {
      System.err.println("Cylinder Z");
      localObject = new CylinderShapeZ(new Vector3f(localVector3f.field_615, localVector3f.field_616, localVector3f.field_617));
    }
    else if (((String)localObject).equals("X"))
    {
      System.err.println("Cylinder X");
      localObject = new CylinderShapeX(new Vector3f(localVector3f.field_615, localVector3f.field_616, localVector3f.field_617));
    }
    else
    {
      System.err.println("Cylinder Y");
      localObject = new CylinderShape(new Vector3f(localVector3f.field_615, localVector3f.field_616, localVector3f.field_617));
    }
    if (!((Mesh)paramclass_996).a4()) {
      System.err.println("[PHYSICS] WARNING: trying to create CYLINDER shape for an object, which's pivot is not centered. this could result in wrong positioned physics shapes!");
    }
    return localObject;
  }
  
  private CollisionShape createMeshShape(Mesh paramMesh, boolean paramBoolean)
  {
    Object localObject1 = paramMesh.jdField_field_89_of_type_ArrayOfJavaxVecmathVector3f;
    paramMesh = paramMesh.jdField_field_89_of_type_ArrayOfClass_1002;
    int i = localObject1.length;
    int j = paramMesh.length;
    ByteBuffer localByteBuffer1 = ByteBuffer.allocateDirect(i * 3 << 2).order(ByteOrder.nativeOrder());
    ByteBuffer localByteBuffer2 = ByteBuffer.allocateDirect(j * 3 << 2).order(ByteOrder.nativeOrder());
    int k = 0;
    Object localObject3;
    for (localObject3 : localObject1)
    {
      localByteBuffer1.putFloat(k * 3 << 2, localObject3.field_615);
      localByteBuffer1.putFloat(k * 3 + 1 << 2, localObject3.field_616);
      localByteBuffer1.putFloat(k * 3 + 2 << 2, localObject3.field_617);
      k++;
    }
    k = 0;
    for (localObject3 : paramMesh)
    {
      localByteBuffer2.putInt(k * 3 << 2, localObject3.field_1280[0]);
      localByteBuffer2.putInt(k * 3 + 1 << 2, localObject3.field_1280[1]);
      localByteBuffer2.putInt(k * 3 + 2 << 2, localObject3.field_1280[2]);
      k++;
    }
    localByteBuffer2.rewind();
    localObject1 = new TriangleIndexVertexArray(j, localByteBuffer2, 12, i, localByteBuffer1, 12);
    Object localObject2;
    if (paramBoolean) {
      localObject2 = new BvhTriangleMeshShape((StridingMeshInterface)localObject1, true, true);
    } else {
      ((GImpactMeshShape)(localObject2 = new GImpactMeshShape((StridingMeshInterface)localObject1))).updateBound();
    }
    return localObject2;
  }
  
  public CollisionShape createMeshShapeFromIndexBuffer(ByteBuffer paramByteBuffer1, int paramInt1, ByteBuffer paramByteBuffer2, int paramInt2, boolean paramBoolean)
  {
    paramByteBuffer1 = new TriangleIndexVertexArray(paramInt2, paramByteBuffer2, 12, paramInt1, paramByteBuffer1, 12);
    if (paramBoolean) {
      paramByteBuffer1 = new BvhTriangleMeshShape(paramByteBuffer1, true, true);
    } else {
      ((GImpactMeshShape)(paramByteBuffer1 = new GImpactMeshShape(paramByteBuffer1))).updateBound();
    }
    return paramByteBuffer1;
  }
  
  public CollisionShape createMeshShapeFromIndexBuffer(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean)
  {
    System.err.println("binding vertexBuffer to " + paramInt1);
    GL15.glBindBuffer(34962, paramInt1);
    GL15.glMapBuffer(34962, 35000, null);
    System.err.println("binding index to " + paramInt2);
    GL15.glBindBuffer(34963, paramInt2);
    GL15.glMapBuffer(34963, 35000, null);
    paramInt3 = new TriangleIndexVertexArray(paramInt3, null, 12, paramInt4, null, 12);
    if (paramBoolean) {
      paramInt3 = new BvhTriangleMeshShape(paramInt3, true, true);
    } else {
      ((GImpactMeshShape)(paramInt3 = new GImpactMeshShape(paramInt3))).updateBound();
    }
    GL15.glBindBuffer(34962, paramInt1);
    GL15.glUnmapBuffer(34962);
    GL15.glBindBuffer(34963, paramInt2);
    GL15.glUnmapBuffer(34963);
    return paramInt3;
  }
  
  private CollisionShape createTriangle(class_996 paramclass_996)
  {
    Vector3f[] arrayOfVector3f;
    (arrayOfVector3f = new Vector3f[3])[0] = paramclass_996.jdField_field_89_of_type_ArrayOfJavaxVecmathVector3f[0];
    arrayOfVector3f[1] = paramclass_996.jdField_field_89_of_type_ArrayOfJavaxVecmathVector3f[1];
    arrayOfVector3f[2] = paramclass_996.jdField_field_89_of_type_ArrayOfJavaxVecmathVector3f[2];
    (paramclass_996 = createConvexHullShape(arrayOfVector3f, new Vector3f(), true)).setMargin(0.5F);
    return paramclass_996;
  }
  
  private RaycastVehicle createVehicle(PhysicsDataContainer paramPhysicsDataContainer, class_1421 paramclass_1421, VehicleTuning paramVehicleTuning)
  {
    paramPhysicsDataContainer = (RigidBody)paramPhysicsDataContainer.getObject();
    paramclass_1421 = new DefaultVehicleRaycaster(this.dynamicsWorld);
    paramclass_1421 = new RaycastVehicle(paramVehicleTuning, paramPhysicsDataContainer, paramclass_1421);
    paramPhysicsDataContainer.setActivationState(4);
    paramclass_1421.setCoordinateSystem(0, 1, 2);
    return paramclass_1421;
  }
  
  public void drawDebugObjects()
  {
    if ((getDynamicsWorld() == null) || (getDynamicsWorld().getDebugDrawer() == null)) {
      return;
    }
    DiscreteDynamicsWorld localDiscreteDynamicsWorld;
    int i = (localDiscreteDynamicsWorld = (DiscreteDynamicsWorld)getDynamicsWorld()).getNumCollisionObjects();
    try
    {
      for (int j = 0; j < i; j++)
      {
        Object localObject;
        if ((localObject = (CollisionObject)localDiscreteDynamicsWorld.getCollisionObjectArray().get(j)) != null)
        {
          ((CollisionObject)localObject).getWorldTransform(this.tempTrans);
          class_919.a(this.tempTrans, ((CollisionObject)localObject).getCollisionShape(), new Vector3f(1.0F, 0.3F, 1.0F), getDynamicsWorld().getDebugDrawer().getDebugMode());
          if ((localObject = RigidBody.upcast((CollisionObject)localObject)) == null)
          {
            class_919.a2(this.tempTrans, 10.0F, new Vector3f(1.0F, 0.3F, 1.0F));
          }
          else
          {
            ((RigidBody)localObject).getCenterOfMassTransform(this.tempTrans);
            class_919.a2(this.tempTrans, 10.0F, new Vector3f(1.0F, 0.3F, 1.0F));
          }
        }
      }
      j = localDiscreteDynamicsWorld.getNumConstraints();
      for (int k = 0; k < j; k++)
      {
        getDynamicsWorld().getDebugDrawer().getDebugMode();
        class_919.a1(localDiscreteDynamicsWorld.getConstraint(k), new Vector3f(1.0F, 0.0F, 1.0F));
      }
      return;
    }
    catch (Exception localException)
    {
      localException;
    }
  }
  
  public RigidBody getBodyFromShape(CollisionShape paramCollisionShape, float paramFloat, Transform paramTransform)
  {
    int i = paramFloat != 0.0F ? 1 : 0;
    assert ((!(paramCollisionShape instanceof CompoundShape)) || (((CompoundShape)paramCollisionShape).getNumChildShapes() != 0)) : "tried to add empty compound shape";
    Vector3f localVector3f = new Vector3f();
    if (i != 0) {
      paramCollisionShape.calculateLocalInertia(paramFloat, localVector3f);
    }
    paramCollisionShape.setMargin(0.13F);
    paramTransform = new class_1427(paramTransform);
    paramCollisionShape = new RigidBodyConstructionInfo(paramFloat, paramTransform, paramCollisionShape, localVector3f);
    (paramCollisionShape = new RigidBody(paramCollisionShape)).setMassProps(paramFloat, localVector3f);
    paramCollisionShape.updateInertiaTensor();
    paramCollisionShape.setRestitution(0.4F);
    paramCollisionShape.setFriction(0.3F);
    paramCollisionShape.setDamping(0.08F, 0.08F);
    return paramCollisionShape;
  }
  
  public CollisionConfiguration getCollisionConfiguration()
  {
    return this.collisionConfiguration;
  }
  
  public Vector3f getCollisionPoint(Vector3f paramVector3f1, Vector3f paramVector3f2, boolean paramBoolean)
  {
    return testRayCollisionPoint(paramVector3f1, paramVector3f2, paramBoolean).hitPointWorld;
  }
  
  public CollisionDispatcher getDispatcher()
  {
    return this.dispatcher;
  }
  
  public DynamicsWorld getDynamicsWorld()
  {
    return this.dynamicsWorld;
  }
  
  public float getIterations()
  {
    return this.iterations;
  }
  
  public BroadphaseInterface getOverlappingPairCache()
  {
    return this.overlappingPairCache;
  }
  
  public List getPhysicsExceptions()
  {
    return this.physicsExceptions;
  }
  
  private CollisionShape getShapeFromUserdata(class_996 paramclass_996)
  {
    String str = getShapeTypeFromUserdata(paramclass_996);
    CollisionShape localCollisionShape = null;
    if (str.equals("box")) {
      localCollisionShape = createBox(paramclass_996);
    }
    if (str.equals("cylinder")) {
      localCollisionShape = createCylinder(paramclass_996);
    }
    if (str.equals("triangle")) {
      localCollisionShape = createTriangle(paramclass_996);
    }
    if (str.equals("mesh")) {
      localCollisionShape = createMeshShape((Mesh)paramclass_996, false);
    }
    if (str.equals("convexhull")) {
      localCollisionShape = createConvexHullShape(paramclass_996.jdField_field_89_of_type_ArrayOfJavaxVecmathVector3f, new Vector3f(), true);
    }
    return localCollisionShape;
  }
  
  private String getShapeTypeFromUserdata(class_996 paramclass_996)
  {
    String str = null;
    Document localDocument;
    if ((localDocument = paramclass_996.a163()) == null)
    {
      System.err.println("no userData found for mesh " + paramclass_996 + " adding as non physics normal child");
      throw new IllegalAccessError();
    }
    for (paramclass_996 = 0; paramclass_996 < localDocument.getFirstChild().getChildNodes().getLength(); paramclass_996++)
    {
      Node localNode;
      if ((localNode = localDocument.getFirstChild().getChildNodes().item(paramclass_996)).getNodeName().equals("ShapeType"))
      {
        str = localNode.getTextContent();
        break;
      }
    }
    if (str == null) {
      throw new CollisionShapeNotFoundException();
    }
    return str;
  }
  
  public ConstraintSolver getSolver()
  {
    return this.solver;
  }
  
  public class_1407 getState()
  {
    return this.state;
  }
  
  public Vector getVehicles()
  {
    return this.vehicles;
  }
  
  public void initPhysics()
  {
    setCollisionConfiguration(new DefaultCollisionConfiguration());
    setDispatcher(new CollisionDispatcher(getCollisionConfiguration()));
    Object localObject = new Vector3f(-10000.0F, -10000.0F, -10000.0F);
    Vector3f localVector3f = new Vector3f(10000.0F, 10000.0F, 10000.0F);
    setOverlappingPairCache(new AxisSweep3((Vector3f)localObject, localVector3f, 1024, new HashedOverlappingPairCache()));
    localObject = new SequentialImpulseConstraintSolver();
    setSolver((ConstraintSolver)localObject);
    setDynamicsWorld(new DiscreteDynamicsWorld(getDispatcher(), getOverlappingPairCache(), getSolver(), getCollisionConfiguration()));
    GImpactCollisionAlgorithm.registerAlgorithm((CollisionDispatcher)this.dynamicsWorld.getDispatcher());
    getDynamicsWorld().setGravity(new Vector3f(0.0F, -0.0F, 0.0F));
  }
  
  public void orientate(class_1421 paramclass_1421, Vector3f paramVector3f1, Vector3f paramVector3f2, Vector3f paramVector3f3, float paramFloat)
  {
    RigidBody localRigidBody;
    if (((localRigidBody = (RigidBody)paramclass_1421.getPhysicsDataContainer().getObject()).getCollisionFlags() == 1) || (paramclass_1421.getMass() <= 0.0F)) {
      return;
    }
    if ((paramVector3f1 != null) && (paramVector3f1.length() > 0.0F) && (paramVector3f2 != null) && (paramVector3f2.length() > 0.0F))
    {
      class_1409 localclass_1409 = (class_1409)threadLocal.get();
      paramclass_1421 = paramclass_1421.getPhysicsDataContainer().getCurrentPhysicsTransform();
      GlUtil.c(localclass_1409.field_1623, paramclass_1421);
      GlUtil.f(localclass_1409.field_1624, paramclass_1421);
      GlUtil.e(localclass_1409.field_1632, paramclass_1421);
      localclass_1409.field_1630.set(paramVector3f3);
      localclass_1409.field_1630.normalize();
      localclass_1409.field_1629.set(paramVector3f1);
      localclass_1409.field_1629.normalize();
      localclass_1409.field_1631.set(paramVector3f2);
      localclass_1409.field_1631.normalize();
      paramclass_1421 = new Vector3f();
      paramVector3f1 = new Vector3f();
      paramVector3f2 = new Vector3f();
      paramclass_1421.sub(localclass_1409.field_1623, localclass_1409.field_1629);
      paramVector3f1.sub(localclass_1409.field_1624, localclass_1409.field_1631);
      paramVector3f2.sub(localclass_1409.field_1632, localclass_1409.field_1630);
      localclass_1409.field_1625.cross(localclass_1409.field_1623, localclass_1409.field_1629);
      localclass_1409.field_1625.normalize();
      localclass_1409.field_1626.cross(localclass_1409.field_1624, localclass_1409.field_1631);
      localclass_1409.field_1626.normalize();
      localclass_1409.field_1627.cross(localclass_1409.field_1632, localclass_1409.field_1630);
      localclass_1409.field_1627.normalize();
      if ((paramclass_1421.length() < 1.192093E-007F) && (paramVector3f1.length() < 1.192093E-007F) && (paramVector3f2.length() < 1.192093E-007F)) {
        return;
      }
      localclass_1409.field_1625.scale(paramclass_1421.length());
      localclass_1409.field_1626.scale(paramVector3f1.length());
      localclass_1409.field_1627.scale(paramVector3f2.length());
      localclass_1409.field_1628.add(localclass_1409.field_1625, localclass_1409.field_1626);
      localclass_1409.field_1628.add(localclass_1409.field_1627);
      if ((localclass_1409.field_1628.length() > 5.0E-005F) && (!localRigidBody.isActive())) {
        localRigidBody.activate();
      }
      paramclass_1421 = new Vector3f();
      localRigidBody.getAngularVelocity(paramclass_1421);
      localclass_1409.field_1628.scale(paramFloat);
      if ((!Float.isNaN(localclass_1409.field_1628.field_615)) && (!Float.isNaN(localclass_1409.field_1628.field_616)) && (!Float.isNaN(localclass_1409.field_1628.field_617))) {
        localRigidBody.setAngularVelocity(localclass_1409.field_1628);
      }
      localclass_1409.field_1633.set(localclass_1409.field_1628);
    }
  }
  
  private void processConstraint(class_996 paramclass_996, class_1421 paramclass_1421)
  {
    (paramclass_996 = paramclass_996.a163()).getElementsByTagName("ConstraintType").item(0).getTextContent();
    paramclass_1421 = Boolean.parseBoolean(paramclass_996.getElementsByTagName("ObjectToObject").item(0).getTextContent());
    paramclass_996.getElementsByTagName("FromObject").item(0).getTextContent();
    if (paramclass_1421 != 0) {
      paramclass_996.getElementsByTagName("ToObject").item(0).getTextContent();
    }
  }
  
  private void processConstraintsAndVehicles(ArrayList paramArrayList, class_1421 paramclass_1421)
  {
    paramArrayList = paramArrayList.iterator();
    while (paramArrayList.hasNext())
    {
      Object localObject1;
      if (((localObject1 = (class_984)paramArrayList.next()) instanceof class_996))
      {
        Object localObject2;
        if ((localObject2 = (localObject1 = (class_996)localObject1).a163()) == null) {
          return;
        }
        if ((localObject2 = ((Document)localObject2).getFirstChild().getNodeName()).equals("Constraint")) {
          processConstraint((class_996)localObject1, paramclass_1421);
        } else if (((String)localObject2).equals("VehicleChassis")) {
          new VehicleTuning();
        }
      }
    }
  }
  
  public void removeObject(CollisionObject paramCollisionObject)
  {
    if (this.dynamicsWorld.getCollisionObjectArray().contains(paramCollisionObject))
    {
      if ((paramCollisionObject instanceof RigidBody))
      {
        this.dynamicsWorld.removeRigidBody((RigidBody)paramCollisionObject);
        return;
      }
      this.dynamicsWorld.removeCollisionObject(paramCollisionObject);
    }
  }
  
  public void removeShapeOfEntity(class_1421 paramclass_1421)
  {
    if (paramclass_1421.getPhysicsDataContainer().getObject() == null) {
      return;
    }
    CollisionObject localCollisionObject;
    if (((localCollisionObject = paramclass_1421.getPhysicsDataContainer().getObject()) instanceof RigidBody)) {
      this.dynamicsWorld.removeRigidBody((RigidBody)localCollisionObject);
    } else {
      this.dynamicsWorld.removeCollisionObject(localCollisionObject);
    }
    this.shapeEntityMap.remove(localCollisionObject.getCollisionShape());
    for (int i = 0; i < this.dynamicsWorld.getNumConstraints(); i++)
    {
      TypedConstraint localTypedConstraint;
      if (((localTypedConstraint = this.dynamicsWorld.getConstraint(i)).getRigidBodyA() == localCollisionObject) || (localTypedConstraint.getRigidBodyB() == localCollisionObject)) {
        this.dynamicsWorld.removeConstraint(localTypedConstraint);
      }
    }
    paramclass_1421.getPhysicsDataContainer().clearPhysicsInfo();
  }
  
  public void removeTimedExceptions(class_1421 paramclass_14211, class_1421 paramclass_14212)
  {
    paramclass_14211 = new class_1403(paramclass_14211.getPhysicsDataContainer().getObject(), paramclass_14212.getPhysicsDataContainer().getObject(), 0L, 0L);
    while (getPhysicsExceptions().contains(paramclass_14211)) {
      getPhysicsExceptions().remove(paramclass_14211);
    }
  }
  
  public void resetAll()
  {
    System.err.println("[PHYSICS] TOTAL RESET!");
    for (int i = 0; i < this.dynamicsWorld.getCollisionObjectArray().size(); i++) {
      this.dynamicsWorld.removeCollisionObject((CollisionObject)this.dynamicsWorld.getCollisionObjectArray().get(i));
    }
    for (i = 0; i < this.dynamicsWorld.getNumConstraints(); i++) {
      this.dynamicsWorld.removeConstraint(this.dynamicsWorld.getConstraint(i));
    }
    Iterator localIterator = this.vehicles.iterator();
    while (localIterator.hasNext())
    {
      RaycastVehicle localRaycastVehicle = (RaycastVehicle)localIterator.next();
      this.dynamicsWorld.removeVehicle(localRaycastVehicle);
    }
    getVehicles().clear();
    this.shapeEntityMap.clear();
    this.dataConstraintMap.clear();
    initPhysics();
  }
  
  public void setCollisionConfiguration(CollisionConfiguration paramCollisionConfiguration)
  {
    this.collisionConfiguration = paramCollisionConfiguration;
  }
  
  public void setDispatcher(CollisionDispatcher paramCollisionDispatcher)
  {
    this.dispatcher = paramCollisionDispatcher;
  }
  
  public void setDynamicsWorld(DynamicsWorld paramDynamicsWorld)
  {
    this.dynamicsWorld = paramDynamicsWorld;
  }
  
  public void setIterations(float paramFloat)
  {
    this.iterations = paramFloat;
  }
  
  public void setOverlappingPairCache(BroadphaseInterface paramBroadphaseInterface)
  {
    this.overlappingPairCache = paramBroadphaseInterface;
  }
  
  public void setSolver(ConstraintSolver paramConstraintSolver)
  {
    this.solver = paramConstraintSolver;
  }
  
  public void setState(class_1407 paramclass_1407)
  {
    this.state = paramclass_1407;
  }
  
  public void setVehicles(Vector paramVector)
  {
    this.vehicles = paramVector;
  }
  
  public void shoot(class_1421 paramclass_1421, float paramFloat, Vector3f paramVector3f, boolean paramBoolean)
  {
    paramclass_1421 = (RigidBody)paramclass_1421.getPhysicsDataContainer().getObject();
    shoot(paramclass_1421, paramFloat, paramVector3f, paramBoolean);
  }
  
  public void shoot(RigidBody paramRigidBody, float paramFloat, Vector3f paramVector3f, boolean paramBoolean)
  {
    if ((paramVector3f != null) && (paramVector3f.length() > 0.0F))
    {
      (paramVector3f = new Vector3f(paramVector3f)).normalize();
      paramVector3f.scale(paramFloat);
      paramRigidBody.setLinearVelocity(paramVector3f);
    }
  }
  
  public void spawnAddedObject(class_1421 paramclass_1421, float paramFloat)
  {
    spawnPhysicsEntity(paramclass_1421, paramFloat);
  }
  
  private void spawnPhysicsEntity(class_1421 paramclass_1421, float paramFloat)
  {
    assert (this.dynamicsWorld != null);
    addBodyFromShape(paramclass_1421, paramFloat);
  }
  
  public void stopAngular(class_1421 paramclass_1421)
  {
    ((RigidBody)paramclass_1421.getPhysicsDataContainer().getObject()).setAngularVelocity(new Vector3f(0.0F, 0.0F, 0.0F));
  }
  
  public void stopForces(class_1421 paramclass_1421)
  {
    paramclass_1421 = (RigidBody)paramclass_1421.getPhysicsDataContainer().getObject();
    assert (paramclass_1421 != null);
    if (!paramclass_1421.isActive()) {
      paramclass_1421.activate();
    }
    paramclass_1421.clearForces();
    paramclass_1421.setLinearVelocity(new Vector3f(0.0F, 0.0F, 0.0F));
    paramclass_1421.setAngularVelocity(new Vector3f(0.0F, 0.0F, 0.0F));
  }
  
  public void testOverlapAABB(PairCachingGhostObject paramPairCachingGhostObject)
  {
    System.err.println("overlapping: " + paramPairCachingGhostObject.getNumOverlappingObjects() + ", " + paramPairCachingGhostObject.getActivationState());
    ObjectArrayList localObjectArrayList = paramPairCachingGhostObject.getOverlappingPairCache().getOverlappingPairArray();
    for (int i = 0; i < localObjectArrayList.size(); i++)
    {
      Object localObject = (CollisionObject)((localObject = (BroadphasePair)localObjectArrayList.get(i)).pProxy0.clientObject != paramPairCachingGhostObject ? ((BroadphasePair)localObject).pProxy0 : ((BroadphasePair)localObject).pProxy1).clientObject;
      System.err.println("overlapping: " + localObject);
    }
  }
  
  public CollisionWorld.ClosestRayResultCallback testRayCollisionPoint(Vector3f paramVector3f1, Vector3f paramVector3f2, boolean paramBoolean)
  {
    CollisionWorld.ClosestRayResultCallback localClosestRayResultCallback = new CollisionWorld.ClosestRayResultCallback(paramVector3f1, paramVector3f2);
    this.dynamicsWorld.rayTest(paramVector3f1, paramVector3f2, localClosestRayResultCallback);
    if ((localClosestRayResultCallback.collisionObject != null) && ((paramVector3f1 = RigidBody.upcast(localClosestRayResultCallback.collisionObject)) != null) && (paramBoolean) && (!paramVector3f1.isStaticObject())) {
      return null;
    }
    return localClosestRayResultCallback;
  }
  
  public void testSweepBoundingBox(Vector3f paramVector3f1, Vector3f paramVector3f2, Vector3f paramVector3f3)
  {
    paramVector3f3 = new BoxShape(paramVector3f3);
    Transform localTransform;
    (localTransform = new Transform()).setIdentity();
    localTransform.origin.set(paramVector3f1);
    (paramVector3f1 = new Transform()).setIdentity();
    paramVector3f1.origin.set(paramVector3f2);
    this.hitIndicator = false;
    paramVector3f2 = new class_1415(this);
    paramVector3f3.recalcLocalAabb();
    this.dynamicsWorld.convexSweepTest(paramVector3f3, localTransform, paramVector3f1, paramVector3f2);
    System.err.println("tested bb: " + this.hitIndicator + ", callback: has hit " + paramVector3f2.hasHit());
  }
  
  public void update(class_941 paramclass_941, float paramFloat)
  {
    assert (getState() != null);
    this.counter = 0.0F;
    try
    {
      if ((paramFloat = (int)(paramFloat > 0.0F ? Math.max(1.0F, 60.0F + paramFloat * 12.0F) : 0.0F)) > 0)
      {
        System.err.println("MAX EXTRA SUBSTEPS ARE: " + paramFloat);
        getDynamicsWorld().getSolverInfo().erp = (0.2F / (paramFloat - 60));
        this.dynamicsWorld.stepSimulation(paramclass_941.a(), paramFloat + 14, 1.0F / paramFloat);
      }
      else
      {
        getDynamicsWorld().getSolverInfo().erp = 0.2F;
        this.dynamicsWorld.stepSimulation(paramclass_941.a(), 14);
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      assert (getState() != null);
      System.err.println("Exception: PHYSICS EXCEPTION HAS BEEN CATCHED! " + getState().c());
    }
    paramFloat = this.dynamicsWorld.getNumConstraints();
    Object localObject;
    for (paramclass_941 = 0; paramclass_941 < paramFloat; paramclass_941++)
    {
      localObject = this.dynamicsWorld.getConstraint(paramclass_941);
      class_1411 localclass_1411;
      if ((localclass_1411 = (class_1411)this.dataConstraintMap.get(localObject)) != null) {
        switch (class_1413.field_1634[localObject.getConstraintType().ordinal()])
        {
        case 1: 
          ((class_1425)localclass_1411).a();
          break;
        case 2: 
          ((class_1405)localclass_1411).a();
          if (class_1040.a1()) {
            class_1405.b();
          }
          break;
        }
      }
    }
    for (paramclass_941 = 0; paramclass_941 < getPhysicsExceptions().size(); paramclass_941++) {
      if (((localObject = (class_1403)getPhysicsExceptions().get(paramclass_941)).field_1621 >= 0L) && (System.currentTimeMillis() - ((class_1403)localObject).field_1620 > ((class_1403)localObject).field_1621))
      {
        getPhysicsExceptions().remove(paramclass_941);
        paramclass_941--;
      }
    }
  }
  
  public void cleanUp()
  {
    this.dynamicsWorld.destroy();
  }
  
  public void softClean() {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1419
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */