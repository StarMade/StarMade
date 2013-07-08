package org.schema.game.common.controller;

import class_1041;
import class_1057;
import class_1204;
import class_1277;
import class_1407;
import class_1419;
import class_1421;
import class_1428;
import class_197;
import class_20;
import class_35;
import class_371;
import class_433;
import class_479;
import class_48;
import class_481;
import class_52;
import class_670;
import class_69;
import class_7;
import class_703;
import class_707;
import class_713;
import class_784;
import class_79;
import class_796;
import class_797;
import class_798;
import class_858;
import class_866;
import class_880;
import class_886;
import class_894;
import class_896;
import class_908;
import class_941;
import class_949;
import class_969;
import class_988;
import class_994;
import com.bulletphysics.collision.broadphase.DispatcherInfo;
import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestConvexResultCallback;
import com.bulletphysics.collision.shapes.BoxShape;
import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.collision.shapes.CompoundShape;
import com.bulletphysics.collision.shapes.CompoundShapeChild;
import com.bulletphysics.collision.shapes.ConvexShape;
import com.bulletphysics.dynamics.DynamicsWorld;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.dynamics.constraintsolver.TypedConstraint;
import com.bulletphysics.linearmath.AabbUtil2;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.util.ObjectArrayList;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.Vector;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3f;
import org.schema.common.FastMath;
import org.schema.common.util.ByteUtil;
import org.schema.game.common.controller.database.DatabaseIndex;
import org.schema.game.common.controller.elements.ManagerContainer;
import org.schema.game.common.data.element.ControlElementMap;
import org.schema.game.common.data.element.ElementClassNotFoundException;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.game.common.data.physics.CubeShape;
import org.schema.game.common.data.physics.CubesCompoundShape;
import org.schema.game.common.data.physics.ModifiedDynamicsWorld;
import org.schema.game.common.data.physics.PhysicsExt;
import org.schema.game.common.data.world.Segment;
import org.schema.game.common.data.world.SegmentData;
import org.schema.game.common.data.world.Universe;
import org.schema.game.server.controller.GameServerController;
import org.schema.schine.graphicsengine.camera.Camera;
import org.schema.schine.graphicsengine.shader.ErrorDialogException;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.objects.Sendable;
import org.schema.schine.network.objects.container.PhysicsDataContainer;
import org.schema.schine.network.server.ServerStateInterface;

public abstract class SegmentController
  extends class_797
  implements class_7, class_1277
{
  private final class_707 dockingController;
  private class_880 segmentProvider;
  private int creatorId;
  private class_908 creatorThread;
  private int field_200 = -1234;
  private final class_48 maxPos = new class_48();
  private final class_48 minPos = new class_48();
  private String realName = "undef";
  private final Transform worldTransformInverse = new Transform();
  private final class_703 elementClassCountMap = new class_703();
  private int totalElements = 0;
  private final class_866 collisionChecker = new class_866(this);
  private final class_48 testPos = new class_48();
  private final class_886 segmentBuffer;
  private final Vector3f camPosLocal = new Vector3f((1.0F / 1.0F), (1.0F / 1.0F), (1.0F / 1.0F));
  private final Vector3f camForwLocal = new Vector3f();
  private final Vector3f camLeftLocal = new Vector3f();
  private final Vector3f camUpLocal = new Vector3f();
  private final ControlElementMap controlElementMap;
  private String uniqueIdentifier;
  protected final class_35 tmpLocalPos = new class_35();
  protected final class_48 posTmp = new class_48();
  private long timeCreated;
  private boolean aabbRecalcFlag;
  protected boolean flagUpdateMass;
  private boolean flagCheckDocking;
  public boolean flagPhysicsAABBUpdate;
  private boolean flagSegmentBufferAABBUpdate;
  private String spawner = "";
  private String lastModifier = "";
  private long seed;
  private final ArrayList needsActiveUpdateClient = new ArrayList();
  private long delayDockingCheck;
  private int lastSector;
  private final Transform clientTransformInverse = new Transform();
  private final Vector3f camLocalTmp = new Vector3f();
  
  public static void setContraintFrameOrientation(byte paramByte, Transform paramTransform, Vector3f paramVector3f1, Vector3f paramVector3f2, Vector3f paramVector3f3)
  {
    switch (paramByte)
    {
    case 5: 
      paramTransform.basis.setRow(0, -paramVector3f1.field_615, -paramVector3f2.field_615, -paramVector3f3.field_615);
      paramTransform.basis.setRow(1, -paramVector3f1.field_616, -paramVector3f2.field_616, -paramVector3f3.field_616);
      paramTransform.basis.setRow(2, -paramVector3f1.field_617, -paramVector3f2.field_617, -paramVector3f3.field_617);
      return;
    case 4: 
    default: 
      paramTransform.basis.setRow(0, paramVector3f1.field_615, paramVector3f2.field_615, paramVector3f3.field_615);
      paramTransform.basis.setRow(1, paramVector3f1.field_616, paramVector3f2.field_616, paramVector3f3.field_616);
      paramTransform.basis.setRow(2, paramVector3f1.field_617, paramVector3f2.field_617, paramVector3f3.field_617);
      return;
    case 0: 
      paramTransform.basis.setRow(0, -paramVector3f3.field_615, -paramVector3f2.field_615, -paramVector3f1.field_615);
      paramTransform.basis.setRow(1, -paramVector3f3.field_616, -paramVector3f2.field_616, -paramVector3f1.field_616);
      paramTransform.basis.setRow(2, -paramVector3f3.field_617, -paramVector3f2.field_617, -paramVector3f1.field_617);
      return;
    case 1: 
      paramTransform.basis.setRow(0, paramVector3f3.field_615, -paramVector3f2.field_615, paramVector3f1.field_615);
      paramTransform.basis.setRow(1, paramVector3f3.field_616, -paramVector3f2.field_616, paramVector3f1.field_616);
      paramTransform.basis.setRow(2, paramVector3f3.field_617, -paramVector3f2.field_617, paramVector3f1.field_617);
      return;
    case 2: 
      paramTransform.basis.setRow(0, paramVector3f3.field_615, paramVector3f1.field_615, paramVector3f2.field_615);
      paramTransform.basis.setRow(1, paramVector3f3.field_616, paramVector3f1.field_616, paramVector3f2.field_616);
      paramTransform.basis.setRow(2, paramVector3f3.field_617, paramVector3f1.field_617, paramVector3f2.field_617);
      return;
    }
    paramTransform.basis.setRow(0, -paramVector3f3.field_615, paramVector3f1.field_615, -paramVector3f2.field_615);
    paramTransform.basis.setRow(1, -paramVector3f3.field_616, paramVector3f1.field_616, -paramVector3f2.field_616);
    paramTransform.basis.setRow(2, -paramVector3f3.field_617, paramVector3f1.field_617, -paramVector3f2.field_617);
  }
  
  public SegmentController(StateInterface paramStateInterface)
  {
    super(paramStateInterface);
    if ((paramStateInterface instanceof ServerStateInterface)) {
      this.segmentProvider = new class_1204(this);
    } else {
      this.segmentProvider = new class_20(this);
    }
    this.segmentBuffer = new SegmentBufferManager(this);
    this.controlElementMap = new ControlElementMap();
    setTimeCreated(System.currentTimeMillis());
    this.dockingController = new class_707(this);
  }
  
  public void aabbRecalcFlag()
  {
    this.aabbRecalcFlag = true;
  }
  
  public void cleanUpOnEntityDelete()
  {
    super.cleanUpOnEntityDelete();
    try
    {
      getSegmentProvider().f1();
    }
    catch (IOException localIOException)
    {
      localIOException;
    }
    getCreatorThread().field_195 = false;
  }
  
  public void createConstraint(class_1421 paramclass_14211, class_1421 paramclass_14212, Object paramObject) {}
  
  public void decTotalElements()
  {
    this.totalElements -= 1;
    flagupdateMass();
    flagUpdateDocking();
  }
  
  public boolean existsNeighborSegment(class_48 paramclass_48, int paramInt)
  {
    getNeighborSegmentPos(paramclass_48, paramInt, this.testPos);
    return this.segmentBuffer.a3(this.testPos);
  }
  
  public void flagUpdateDocking()
  {
    this.flagCheckDocking = true;
  }
  
  public void flagupdateMass()
  {
    if (class_1041.jdField_field_149_of_type_Int == getId()) {
      try
      {
        throw new IllegalArgumentException("MASS SET DEBUG");
      }
      catch (Exception localException)
      {
        localException;
      }
    }
    this.flagUpdateMass = true;
  }
  
  public void setSpawner(String paramString)
  {
    this.spawner = paramString;
  }
  
  public void setLastModified(String paramString)
  {
    this.lastModifier = paramString;
  }
  
  public void fromTagStructure(class_69 paramclass_69)
  {
    assert (paramclass_69.a2().equals("sc"));
    paramclass_69 = (class_69[])paramclass_69.a4();
    setUniqueIdentifier((String)paramclass_69[0].a4());
    getMinPos().b1((class_48)paramclass_69[1].a4());
    getMaxPos().b1((class_48)paramclass_69[2].a4());
    getDockingController().a9(paramclass_69[3]);
    getControlElementMap().fromTagStructure(paramclass_69[4]);
    setRealName((String)paramclass_69[5].a4());
    super.fromTagStructure(paramclass_69[6]);
    if ((this instanceof class_798)) {
      ((class_798)this).a().fromTagStructure(paramclass_69[7]);
    }
    setCreatorId(((Integer)paramclass_69[8].a4()).intValue());
    if ((paramclass_69.length > 9) && (paramclass_69[9].a3() == class_79.field_556)) {
      this.spawner = ((String)paramclass_69[9].a4());
    }
    if ((paramclass_69.length > 10) && (paramclass_69[10].a3() == class_79.field_556)) {
      this.lastModifier = ((String)paramclass_69[10].a4());
    }
    if ((paramclass_69.length > 11) && (paramclass_69[11].a3() == class_79.field_552)) {
      this.seed = ((Long)paramclass_69[11].a4()).longValue();
    } else {
      this.seed = Universe.getRandom().nextLong();
    }
    if ((paramclass_69.length > 12) && (paramclass_69[12].a3() == class_79.field_549))
    {
      if ((this instanceof class_784)) {
        ((class_784)this).a(((Byte)paramclass_69[12].a4()).byteValue() == 1);
      }
    }
    else if ((this instanceof class_784)) {
      ((class_784)this).a(true);
    }
  }
  
  public class_69 toTagStructure()
  {
    class_69 localclass_691 = new class_69(class_79.field_556, "uniqueId", getUniqueIdentifier());
    class_69 localclass_692 = new class_69(class_79.field_551, "creatoreId", Integer.valueOf(getCreatorId()));
    class_69 localclass_693 = new class_69(class_79.field_558, "maxPos", getMinPos());
    class_69 localclass_694 = new class_69(class_79.field_558, "minPos", getMaxPos());
    class_69 localclass_695 = new class_69(class_79.field_556, "realname", getRealName());
    class_69 localclass_696 = this.dockingController.a10();
    class_69 localclass_697;
    if ((this instanceof class_798)) {
      localclass_697 = ((class_798)this).a().toTagStructure();
    } else {
      localclass_697 = new class_69(class_79.field_549, "dummy", Byte.valueOf((byte)0));
    }
    class_69 localclass_698 = getControlElementMap().toTagStructure();
    class_69 localclass_699 = new class_69(class_79.field_556, null, this.spawner != null ? this.spawner : "");
    class_69 localclass_6910 = new class_69(class_79.field_556, null, this.lastModifier != null ? this.lastModifier : "");
    class_69 localclass_6911 = new class_69(class_79.field_552, null, Long.valueOf(getSeed()));
    class_69 localclass_6912;
    if ((this instanceof class_784)) {
      localclass_6912 = new class_69(class_79.field_549, null, Byte.valueOf((byte)(((class_784)this).a1() ? 1 : 0)));
    } else {
      localclass_6912 = new class_69(class_79.field_549, null, Byte.valueOf((byte)1));
    }
    return new class_69(class_79.field_561, "sc", new class_69[] { localclass_691, localclass_693, localclass_694, localclass_696, localclass_698, localclass_695, super.toTagStructure(), localclass_697, localclass_692, localclass_699, localclass_6910, localclass_6911, localclass_6912, new class_69(class_79.field_548, null, null) });
  }
  
  public Vector3f getAbsoluteElementWorldPosition(class_48 paramclass_48, Vector3f paramVector3f)
  {
    paramVector3f.set(paramclass_48.field_475, paramclass_48.field_476, paramclass_48.field_477);
    getWorldTransform().basis.transform(paramVector3f);
    paramVector3f.add(getWorldTransform().origin);
    return paramVector3f;
  }
  
  public void getAbsoluteSegmentWorldPositionClient(Segment paramSegment, Vector3f paramVector3f)
  {
    paramVector3f.set(paramSegment.field_34.field_475, paramSegment.field_34.field_476, paramSegment.field_34.field_477);
    (paramSegment = getWorldTransformClient()).basis.transform(paramVector3f);
    paramVector3f.add(paramSegment.origin);
  }
  
  public class_988 getBoundingBox()
  {
    return getSegmentBuffer().a6();
  }
  
  public Vector3f getCamForwLocal()
  {
    return this.camForwLocal;
  }
  
  public Vector3f getCamLeftLocal()
  {
    return this.camLeftLocal;
  }
  
  public Vector3f getCamUpLocal()
  {
    return this.camUpLocal;
  }
  
  public class_866 getCollisionChecker()
  {
    return this.collisionChecker;
  }
  
  public ControlElementMap getControlElementMap()
  {
    return this.controlElementMap;
  }
  
  public int getCreatorId()
  {
    return this.creatorId;
  }
  
  public void setCreatorId(int paramInt)
  {
    this.creatorId = paramInt;
  }
  
  public class_908 getCreatorThread()
  {
    return this.creatorThread;
  }
  
  public class_707 getDockingController()
  {
    return this.dockingController;
  }
  
  public class_703 getElementClassCountMap()
  {
    return this.elementClassCountMap;
  }
  
  public final int getId()
  {
    return this.field_200;
  }
  
  public Vector3f getLocalCamPos()
  {
    return this.camPosLocal;
  }
  
  public float getMass()
  {
    return super.getMass();
  }
  
  private float getMaxPhysicsSubsteps(class_941 paramclass_941)
  {
    Vector3f localVector3f;
    float f2;
    if ((class_1057.field_1331.a4()) && (getMass() > 0.0F) && ((getPhysicsDataContainer().getObject() instanceof RigidBody)) && ((f2 = (localVector3f = ((RigidBody)getPhysicsDataContainer().getObject()).getLinearVelocity(new Vector3f())).length()) > 47.0F))
    {
      System.err.println("Tuned on Tunneling prevention for " + this + " at speed " + f2 + " on " + getState());
      (localObject = new Vector3f()).sub(getSegmentBuffer().a6().field_1274, getSegmentBuffer().a6().field_1273);
      ((Vector3f)localObject).scale(0.5F);
      localVector3f.scale(paramclass_941.a());
      paramclass_941 = new Transform(getWorldTransform());
      Transform localTransform = new Transform(getWorldTransform());
      paramclass_941.basis.transform(localVector3f);
      localTransform.origin.add(localVector3f);
      float f1 = getPhysics().getDynamicsWorld().getDispatchInfo().allowedCcdPenetration;
      Object localObject = new BoxShape((Vector3f)localObject);
      Iterator localIterator = getState().getLocalAndRemoteObjectContainer().getLocalObjects().values().iterator();
      while (localIterator.hasNext())
      {
        Sendable localSendable;
        if (((localSendable = (Sendable)localIterator.next()) != this) && ((localSendable instanceof SegmentController)) && (((SegmentController)localSendable).getSectorId() != getSectorId()))
        {
          CollisionWorld.ClosestConvexResultCallback localClosestConvexResultCallback = new CollisionWorld.ClosestConvexResultCallback(paramclass_941.origin, localTransform.origin);
          SegmentController localSegmentController = (SegmentController)localSendable;
          ModifiedDynamicsWorld.objectQuerySingle((ConvexShape)localObject, paramclass_941, localTransform, localSegmentController.getPhysicsDataContainer().getObject(), localSegmentController.getPhysicsDataContainer().getObject().getCollisionShape(), localSegmentController.getWorldTransform(), localClosestConvexResultCallback, f1);
          if (localClosestConvexResultCallback.hasHit())
          {
            (paramclass_941 = new Vector3f(getWorldTransform().origin)).sub(localClosestConvexResultCallback.hitPointWorld);
            System.err.println("[TunnelPrevention] HIT AT " + localClosestConvexResultCallback.hitPointWorld + " " + localSendable + " on " + getState() + " DIST: " + paramclass_941.length());
            return paramclass_941.length();
          }
        }
      }
    }
    return 0.0F;
  }
  
  public class_48 getMaxPos()
  {
    return this.maxPos;
  }
  
  public class_48 getMinPos()
  {
    return this.minPos;
  }
  
  public abstract void getNearestIntersectingElementPosition(Vector3f paramVector3f1, Vector3f paramVector3f2, class_48 paramclass_48, float paramFloat, class_481 paramclass_481, class_433 paramclass_433);
  
  public abstract int getNearestIntersection(short paramShort, Vector3f paramVector3f1, Vector3f paramVector3f2, class_479 paramclass_479, int paramInt1, boolean paramBoolean, class_713 paramclass_713, class_48 paramclass_48, int paramInt2, float paramFloat, class_433 paramclass_433);
  
  public class_796[] getNeighborElements(class_48 paramclass_48, short paramShort, class_796[] paramArrayOfclass_796)
  {
    assert (paramArrayOfclass_796.length == 6);
    for (int i = 0; i < 6; i++)
    {
      this.posTmp.b1(paramclass_48);
      this.posTmp.a1(org.schema.game.common.data.element.Element.DIRECTIONSi[i]);
      class_796 localclass_796;
      if (((localclass_796 = getSegmentBuffer().a9(this.posTmp, true)) != null) && ((paramShort == 32767) || (paramShort == localclass_796.a9()))) {
        paramArrayOfclass_796[i] = localclass_796;
      } else {
        paramArrayOfclass_796[i] = null;
      }
    }
    return paramArrayOfclass_796;
  }
  
  public class_796[] getNeighborElements(class_48 paramclass_48, short paramShort, class_796[] paramArrayOfclass_796, Set paramSet)
  {
    assert (paramArrayOfclass_796.length == 6);
    for (int i = 0; i < 6; i++)
    {
      this.posTmp.b1(paramclass_48);
      this.posTmp.a1(org.schema.game.common.data.element.Element.DIRECTIONSi[i]);
      int j = 0;
      class_796 localclass_796;
      if ((paramSet.contains(this.posTmp)) && ((localclass_796 = getSegmentBuffer().a10(this.posTmp, true, paramArrayOfclass_796[i])) != null) && ((paramShort == 32767) || (paramShort == localclass_796.a9())))
      {
        assert (localclass_796.a7() != null);
        j = 1;
      }
      paramArrayOfclass_796[i].a15(j);
    }
    for (i = 0; i < 6; i++) {
      if ((paramArrayOfclass_796[i].b() == 1) && (!$assertionsDisabled) && (paramArrayOfclass_796[i].a7() == null)) {
        throw new AssertionError();
      }
    }
    return paramArrayOfclass_796;
  }
  
  public Segment getNeighboringSegment(class_35 paramclass_35, Segment paramSegment, class_48 paramclass_48)
  {
    assert (paramSegment != null) : (this + ", " + getState() + " has null seg");
    if (SegmentData.valid(paramclass_35.field_453, paramclass_35.field_454, paramclass_35.field_455)) {
      return paramSegment;
    }
    paramclass_48.b1(paramSegment.field_34);
    paramSegment = ByteUtil.b(paramclass_35.field_453);
    int i = ByteUtil.b(paramclass_35.field_454);
    int j = ByteUtil.b(paramclass_35.field_455);
    paramclass_48.a(paramSegment << 4, i << 4, j << 4);
    paramclass_35.field_453 = ((byte)ByteUtil.d(paramclass_35.field_453));
    paramclass_35.field_454 = ((byte)ByteUtil.d(paramclass_35.field_454));
    paramclass_35.field_455 = ((byte)ByteUtil.d(paramclass_35.field_455));
    return this.segmentBuffer.a5(paramclass_48);
  }
  
  public Segment getNeighboringSegmentFast(Segment paramSegment, byte paramByte1, byte paramByte2, byte paramByte3)
  {
    assert (paramSegment != null) : (this + ", " + getState() + " has null seg");
    if (SegmentData.valid(paramByte1, paramByte2, paramByte3)) {
      return paramSegment;
    }
    paramByte1 = paramSegment.field_34.field_475 + (ByteUtil.b(paramByte1) << 4);
    paramByte2 = paramSegment.field_34.field_476 + (ByteUtil.b(paramByte2) << 4);
    paramSegment = paramSegment.field_34.field_477 + (ByteUtil.b(paramByte3) << 4);
    return this.segmentBuffer.a4(paramByte1, paramByte2, paramSegment);
  }
  
  public static void main(String[] paramArrayOfString)
  {
    System.err.println(ByteUtil.b(16));
  }
  
  public class_48 getNeighboringSegmentPosUnsave(class_35 paramclass_35, Segment paramSegment, class_48 paramclass_481, class_48 paramclass_482)
  {
    int i = paramclass_35.field_453 >> 4;
    int j = paramclass_35.field_454 >> 4;
    int k = paramclass_35.field_455 >> 4;
    paramclass_482.field_475 = (paramSegment.field_35.field_475 + i);
    paramclass_482.field_476 = (paramSegment.field_35.field_476 + j);
    paramclass_482.field_477 = (paramSegment.field_35.field_477 + k);
    paramclass_481.field_475 = (paramSegment.field_34.field_475 + (i << 4));
    paramclass_481.field_476 = (paramSegment.field_34.field_476 + (j << 4));
    paramclass_481.field_477 = (paramSegment.field_34.field_477 + (k << 4));
    paramclass_35.field_453 = ((byte)(paramclass_35.field_453 & 0xF));
    paramclass_35.field_454 = ((byte)(paramclass_35.field_454 & 0xF));
    paramclass_35.field_455 = ((byte)(paramclass_35.field_455 & 0xF));
    return paramclass_481;
  }
  
  private static void modVec(class_35 paramclass_35)
  {
    paramclass_35.field_453 = ((byte)ByteUtil.d(paramclass_35.field_453));
    paramclass_35.field_454 = ((byte)ByteUtil.d(paramclass_35.field_454));
    paramclass_35.field_455 = ((byte)ByteUtil.d(paramclass_35.field_455));
  }
  
  public class_48 getNeighborSegmentPos(class_48 paramclass_481, int paramInt, class_48 paramclass_482)
  {
    paramclass_482.b1(paramclass_481);
    switch (paramInt)
    {
    case 1: 
      paramclass_482.field_475 -= 16;
      break;
    case 0: 
      paramclass_482.field_475 += 16;
      break;
    case 3: 
      paramclass_482.field_476 -= 16;
      break;
    case 2: 
      paramclass_482.field_476 += 16;
      break;
    case 5: 
      paramclass_482.field_477 -= 16;
      break;
    case 4: 
      paramclass_482.field_477 += 16;
      break;
    default: 
      if (!$assertionsDisabled) {
        throw new AssertionError();
      }
      break;
    }
    return paramclass_482;
  }
  
  public ArrayList getRadius(Vector3f paramVector3f, float paramFloat)
  {
    paramVector3f = new Vector3f(paramVector3f);
    getWorldTransformInverse().transform(paramVector3f);
    paramVector3f.field_615 += 8.0F;
    paramVector3f.field_616 += 8.0F;
    paramVector3f.field_617 += 8.0F;
    return getRadiusRelative(paramVector3f, paramFloat);
  }
  
  public ArrayList getRadiusRelative(Vector3f paramVector3f, float paramFloat)
  {
    ArrayList localArrayList = new ArrayList();
    new Transform().setIdentity();
    Vector3f localVector3f = new Vector3f(paramVector3f);
    paramVector3f = new Vector3f(paramVector3f);
    localVector3f.field_615 += paramFloat;
    localVector3f.field_616 += paramFloat;
    localVector3f.field_617 += paramFloat;
    paramVector3f.field_615 -= paramFloat;
    paramVector3f.field_616 -= paramFloat;
    paramVector3f.field_617 -= paramFloat;
    paramVector3f = new class_48(paramVector3f.field_615, paramVector3f.field_616, paramVector3f.field_617);
    paramFloat = new class_48(FastMath.c(localVector3f.field_615), FastMath.c(localVector3f.field_616), FastMath.c(localVector3f.field_617));
    paramVector3f.b(ByteUtil.b(paramVector3f.field_475), ByteUtil.b(paramVector3f.field_476), ByteUtil.b(paramVector3f.field_477));
    paramFloat.b(ByteUtil.b(paramFloat.field_475), ByteUtil.b(paramFloat.field_476), ByteUtil.b(paramFloat.field_477));
    paramFloat.a(1, 1, 1);
    paramVector3f.a5(16);
    paramFloat.a5(16);
    getSegmentBuffer().a17(new class_894(localArrayList), paramVector3f, paramFloat);
    return localArrayList;
  }
  
  public String getRealName()
  {
    return this.realName;
  }
  
  public long getSeed()
  {
    return this.seed;
  }
  
  public void setSeed(long paramLong)
  {
    this.seed = paramLong;
  }
  
  public class_886 getSegmentBuffer()
  {
    return this.segmentBuffer;
  }
  
  public Segment getSegmentFromCache(int paramInt1, int paramInt2, int paramInt3)
  {
    return this.segmentBuffer.a4(paramInt1, paramInt2, paramInt3);
  }
  
  public class_880 getSegmentProvider()
  {
    return this.segmentProvider;
  }
  
  public int getTotalElements()
  {
    return this.totalElements;
  }
  
  public void getTransformedAABB(Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat, Vector3f paramVector3f3, Vector3f paramVector3f4)
  {
    paramVector3f3.set(getSegmentBuffer().a6().field_1273);
    paramVector3f4.set(getSegmentBuffer().a6().field_1274);
    if (getSegmentBuffer().c() == 0)
    {
      paramVector3f3.set(0.0F, 0.0F, 0.0F);
      paramVector3f4.set(0.0F, 0.0F, 0.0F);
      return;
    }
    if ((paramVector3f3.field_615 > paramVector3f4.field_615) || (paramVector3f3.field_616 > paramVector3f4.field_616) || (paramVector3f3.field_617 > paramVector3f4.field_617))
    {
      paramVector3f3.set(0.0F, 0.0F, 0.0F);
      paramVector3f4.set(0.0F, 0.0F, 0.0F);
    }
    AabbUtil2.transformAabb(paramVector3f3, paramVector3f4, paramFloat, getWorldTransform(), paramVector3f1, paramVector3f2);
  }
  
  public String getUniqueIdentifier()
  {
    return this.uniqueIdentifier;
  }
  
  public Transform getWorldTransformInverse()
  {
    return this.worldTransformInverse;
  }
  
  public boolean hasNeighborElements(Segment paramSegment, byte paramByte1, byte paramByte2, byte paramByte3, class_48 paramclass_48)
  {
    byte b1;
    byte b2;
    byte b3;
    if (SegmentData.allNeighborsInside(paramByte1, paramByte2, paramByte3)) {
      for (paramclass_48 = 0; paramclass_48 < 6; paramclass_48++)
      {
        b1 = (byte)ByteUtil.d(paramByte1 + org.schema.game.common.data.element.Element.DIRECTIONSb[paramclass_48].field_453);
        b2 = (byte)ByteUtil.d(paramByte2 + org.schema.game.common.data.element.Element.DIRECTIONSb[paramclass_48].field_454);
        b3 = (byte)ByteUtil.d(paramByte3 + org.schema.game.common.data.element.Element.DIRECTIONSb[paramclass_48].field_455);
        if (paramSegment.a16().containsUnsave(b1, b2, b3)) {
          return true;
        }
      }
    } else {
      for (paramclass_48 = 0; paramclass_48 < 6; paramclass_48++)
      {
        b1 = (byte)(paramByte1 + org.schema.game.common.data.element.Element.DIRECTIONSb[paramclass_48].field_453);
        b2 = (byte)(paramByte2 + org.schema.game.common.data.element.Element.DIRECTIONSb[paramclass_48].field_454);
        b3 = (byte)(paramByte3 + org.schema.game.common.data.element.Element.DIRECTIONSb[paramclass_48].field_455);
        Segment localSegment;
        if (((localSegment = getNeighboringSegmentFast(paramSegment, b1, b2, b3)) != null) && (!localSegment.g()))
        {
          b1 = (byte)ByteUtil.d(b1);
          b2 = (byte)ByteUtil.d(b2);
          b3 = (byte)ByteUtil.d(b3);
          if (localSegment.a16().containsUnsave(b1, b2, b3)) {
            return true;
          }
        }
      }
    }
    return false;
  }
  
  public void incTotalElements()
  {
    this.totalElements += 1;
    flagupdateMass();
    flagUpdateDocking();
  }
  
  public void initPhysics()
  {
    if (getPhysicsDataContainer().getObject() == null)
    {
      Transform localTransform1 = getRemoteTransformable().a7();
      Object localObject = new CubeShape(getSegmentBuffer());
      CubesCompoundShape localCubesCompoundShape = new CubesCompoundShape(this);
      Transform localTransform2;
      (localTransform2 = new Transform()).setIdentity();
      localCubesCompoundShape.addChildShape(localTransform2, (CollisionShape)localObject);
      getPhysicsDataContainer().setShapeChield((CompoundShapeChild)localCubesCompoundShape.getChildList().get(localCubesCompoundShape.getChildList().size() - 1));
      localCubesCompoundShape.recalculateLocalAabb();
      getPhysicsDataContainer().setShape(localCubesCompoundShape);
      getPhysicsDataContainer().initialTransform = new Transform();
      getPhysicsDataContainer().initialTransform.set(localTransform1);
      (localObject = getPhysics().getBodyFromShape(localCubesCompoundShape, getMass(), getPhysicsDataContainer().initialTransform)).setUserPointer(Integer.valueOf(getId()));
      getPhysicsDataContainer().setObject((CollisionObject)localObject);
      getWorldTransform().set(localTransform1);
    }
    else
    {
      System.err.println("[SegmentController][WARNING] not adding initial physics object. it already exists");
    }
    setFlagPhysicsInit(true);
  }
  
  public boolean isClientOwnObject()
  {
    return (!isOnServer()) && (((class_371)getState()).a6() == this);
  }
  
  public boolean isInbound(int paramInt1, int paramInt2, int paramInt3)
  {
    return (paramInt1 <= this.maxPos.field_475) && (paramInt2 <= this.maxPos.field_476) && (paramInt3 <= this.maxPos.field_477) && (paramInt1 >= this.minPos.field_475) && (paramInt2 >= this.minPos.field_476) && (paramInt3 >= this.minPos.field_477);
  }
  
  public boolean isInboundCoord(int paramInt, class_48 paramclass_48)
  {
    return (paramclass_48.a7(paramInt) <= this.maxPos.a7(paramInt)) && (paramclass_48.a7(paramInt) >= this.minPos.a7(paramInt));
  }
  
  public boolean isInbound(class_48 paramclass_48)
  {
    return isInbound(paramclass_48.field_475, paramclass_48.field_476, paramclass_48.field_477);
  }
  
  public boolean isInboundAbs(class_48 paramclass_48)
  {
    return isInbound(paramclass_48.field_475, paramclass_48.field_476, paramclass_48.field_477);
  }
  
  public boolean isInboundAbs(int paramInt1, int paramInt2, int paramInt3)
  {
    boolean bool = true;
    paramInt1 = ByteUtil.a(paramInt1);
    paramInt2 = ByteUtil.a(paramInt2);
    paramInt3 = ByteUtil.a(paramInt3);
    if (getMaxPos() != null) {
      bool = (paramInt1 <= getMaxPos().field_475) && (paramInt2 <= getMaxPos().field_476) && (paramInt3 <= getMaxPos().field_477);
    }
    if ((getMinPos() != null) && (bool)) {
      bool = (paramInt1 >= getMinPos().field_475) && (paramInt2 >= getMinPos().field_476) && (paramInt3 >= getMinPos().field_477);
    }
    return bool;
  }
  
  public boolean isInboundSegmentPos(int paramInt1, int paramInt2, int paramInt3)
  {
    boolean bool = true;
    if (getMaxPos() != null) {
      bool = (paramInt1 <= getMaxPos().field_475 << 4) && (paramInt2 <= getMaxPos().field_476 << 4) && (paramInt3 <= getMaxPos().field_477 << 4);
    }
    if (getMinPos() != null) {
      bool = (bool) && (paramInt1 >= getMinPos().field_475 << 4) && (paramInt2 >= getMinPos().field_476 << 4) && (paramInt3 >= getMinPos().field_477 << 4);
    }
    return bool;
  }
  
  public boolean isInboundSegmentPos(class_48 paramclass_48)
  {
    return isInboundSegmentPos(paramclass_48.field_475, paramclass_48.field_476, paramclass_48.field_477);
  }
  
  public void onAddedElement(short paramShort, byte paramByte1, byte paramByte2, byte paramByte3, int paramInt, Segment paramSegment, boolean paramBoolean)
  {
    if (paramBoolean) {
      this.segmentBuffer.a21(paramShort, paramInt, paramByte1, paramByte2, paramByte3, paramSegment);
    }
    getElementClassCountMap().b(paramShort);
    incTotalElements();
  }
  
  public void onProximity(SegmentController paramSegmentController) {}
  
  public void onRemovedElement(short paramShort, int paramInt, byte paramByte1, byte paramByte2, byte paramByte3, Segment paramSegment, boolean paramBoolean)
  {
    this.segmentBuffer.b5(paramShort, paramInt, paramByte1, paramByte2, paramByte3, paramSegment);
    getElementClassCountMap().a(paramShort);
    decTotalElements();
    if (!paramBoolean)
    {
      paramInt = new class_48();
      paramSegment.a13(paramByte1, paramByte2, paramByte3, paramInt);
      getControlElementMap().onRemoveElement(paramInt, paramShort);
      if ((isOnServer()) && (paramShort == 291))
      {
        System.err.println("[SREVER] FACTION BLOCK REMOVED FROM " + this + "; resetting faction !!!!!!!!!!!!!!");
        setFactionId(0);
      }
    }
  }
  
  public void removeAllConstraints(class_1421 paramclass_1421)
  {
    RigidBody localRigidBody = (RigidBody)paramclass_1421.getPhysicsDataContainer().getObject();
    PhysicsExt localPhysicsExt = getPhysics();
    for (int i = 0; i < localPhysicsExt.getDynamicsWorld().getNumConstraints(); i++)
    {
      TypedConstraint localTypedConstraint;
      if (((localTypedConstraint = localPhysicsExt.getDynamicsWorld().getConstraint(i)).getRigidBodyA() == localRigidBody) || (localTypedConstraint.getRigidBodyB() == localRigidBody))
      {
        localPhysicsExt.getDynamicsWorld().removeConstraint(localTypedConstraint);
        System.err.println("[SEGMENT-CONTROLLER][PHYSICS] constraint removed on " + this + " " + getState());
        getPhysics().removeTimedExceptions(this, paramclass_1421);
        getPhysics().addTimedPhysicsException(localTypedConstraint.getRigidBodyA(), localTypedConstraint.getRigidBodyB(), 4000);
        getPhysics().addTimedPhysicsException(getPhysicsDataContainer().getObject(), paramclass_1421.getPhysicsDataContainer().getObject(), 4000);
        localRigidBody.setDamping(0.1F, 0.1F);
        removeAllConstraints(paramclass_1421);
        return;
      }
    }
  }
  
  public void resetTotalElements()
  {
    this.totalElements = 0;
    flagupdateMass();
    flagUpdateDocking();
  }
  
  public void setCreatorThread(class_908 paramclass_908)
  {
    this.creatorThread = paramclass_908;
  }
  
  public void setCurrentBlockController(class_796 paramclass_796, class_48 paramclass_48)
  {
    setCurrentBlockController(paramclass_796, paramclass_48, 0);
  }
  
  public void setCurrentBlockController(class_796 paramclass_796, class_48 paramclass_48, int paramInt)
  {
    if (paramclass_48 != null) {
      try
      {
        class_796 localclass_796;
        if (((localclass_796 = getSegmentBuffer().a9(paramclass_48, true)) != null) && (paramclass_796 != null))
        {
          paramclass_796.a12();
          short s2 = paramclass_796.a7().a16().getType(paramclass_796.a6(this.tmpLocalPos));
          short s1 = localclass_796.a7().a16().getType(localclass_796.a6(this.tmpLocalPos));
          ElementInformation localElementInformation1 = ElementKeyMap.getInfo(s2);
          ElementInformation localElementInformation2;
          if ((localElementInformation2 = ElementKeyMap.getInfo(s1)) == null)
          {
            System.err.println("WARNING. cannot connect block controller to null");
            return;
          }
          if (((this instanceof class_798)) && (!((class_798)this).a().canBeControlled(s1)))
          {
            System.err.println("This cant be controlled by " + this + ": " + s1);
            throw new CannotBeControlledException(localElementInformation1, localElementInformation2);
          }
          ControlElementMap localControlElementMap;
          if (localElementInformation2.getControlledBy().contains(Short.valueOf(s2)))
          {
            localControlElementMap = paramclass_796.a7().a15().getControlElementMap();
            if (paramInt == 0)
            {
              localControlElementMap.switchControllerForElement(paramclass_796.a2(new class_48()), paramclass_48, s1);
              return;
            }
            if (paramInt == 1)
            {
              if (!localControlElementMap.isControlling(paramclass_796.a2(new class_48()), paramclass_48, s1)) {
                localControlElementMap.switchControllerForElement(paramclass_796.a2(new class_48()), paramclass_48, s1);
              }
            }
            else if (localControlElementMap.isControlling(paramclass_796.a2(new class_48()), paramclass_48, s1)) {
              localControlElementMap.switchControllerForElement(paramclass_796.a2(new class_48()), paramclass_48, s1);
            }
            return;
          }
          System.err.println("OBJECT " + s1 + " CANNOT BE CONTROLLED BY " + localControlElementMap);
          if ((!isOnServer()) && (localElementInformation2.getControlledBy().size() > 0)) {
            ((class_371)getState()).a4().b1(ElementKeyMap.getInfo(s1).getName() + " cannot be\nconnected to " + ElementKeyMap.getInfo(localControlElementMap).getName() + ".\nReason: Incompatible blocks");
          }
        }
        return;
      }
      catch (ElementClassNotFoundException localElementClassNotFoundException)
      {
        System.err.println("WARING TRIED TO CONNECT TO AIR");
        return;
      }
    }
    System.err.println("ADDING CONTROL WARNING: CONTROLLED IS NULL " + paramclass_796 + ": " + paramclass_48);
  }
  
  public void setId(int paramInt)
  {
    this.field_200 = paramInt;
  }
  
  public void setRealName(String paramString)
  {
    this.realName = paramString;
  }
  
  public void setSegmentProvider(class_880 paramclass_880)
  {
    this.segmentProvider = paramclass_880;
  }
  
  public void setUniqueIdentifier(String paramString)
  {
    this.uniqueIdentifier = paramString;
  }
  
  public abstract void startCreatorThread();
  
  public void updateLocal(class_941 paramclass_941)
  {
    super.updateLocal(paramclass_941);
    long l = System.currentTimeMillis();
    Object localObject1;
    if (class_949.field_1221.b1())
    {
      class_988 localclass_988 = getSegmentBuffer().a6();
      if (isOnServer())
      {
        if (((class_1041)getState()).a62().getSector(getSectorId()).field_136.a3(2, 2, 2))
        {
          localObject1 = new class_994(localclass_988.field_1273, localclass_988.field_1274, getWorldTransform(), 0.0F, 0.0F);
          class_1428.field_1636.add(localObject1);
        }
      }
      else
      {
        localObject1 = new class_994(localclass_988.field_1273, localclass_988.field_1274, getWorldTransformClient(), 1.0F, 1.0F);
        class_1428.field_1636.add(localObject1);
      }
    }
    if ((l - getLastSlowdown() < 5000L) || (isImmediateStuck()))
    {
      if (((getSlowdownStart() > 0L) && (l - getSlowdownStart() > 10000L)) || (isImmediateStuck()))
      {
        System.err.println("[SEGCON] " + getState() + " stuck physics detected on " + this);
        if (isOnServer()) {
          if (isImmediateStuck()) {
            ((class_1041)getState()).a59().broadcastMessage("Structure\n" + this + "\ndetected to be stuck\nWARPING OUT OF COLLISION!", 3);
          } else {
            ((class_1041)getState()).a59().broadcastMessage("Structure\n" + this + "\nis slowing down the server\nWARPING OUT OF COLLISION!", 3);
          }
        }
        warpOutOfCollision();
        getPhysicsDataContainer().updatePhysical();
        resetSlowdownStart();
        setImmediateStuck(false);
      }
    }
    else {
      resetSlowdownStart();
    }
    getDockingController().a12(paramclass_941);
    assert (getUniqueIdentifier() != null);
    getWorldTransformInverse().set(getWorldTransform());
    getWorldTransformInverse().inverse();
    if ((this.flagCheckDocking) && (getPhysicsDataContainer().isInitialized()) && (System.currentTimeMillis() - this.delayDockingCheck > 1000L)) {
      try
      {
        getDockingController().a();
        this.flagCheckDocking = false;
        this.delayDockingCheck = System.currentTimeMillis();
      }
      catch (CollectionNotLoadedException localCollectionNotLoadedException)
      {
        System.err.println("[SEGMENT-CONTROLLER] Cannot validate docking yet, because enhancers aren't fully loaded -> DELAY by 1 sec");
        this.delayDockingCheck = System.currentTimeMillis();
      }
    }
    if ((!isOnServer()) && (!getNeedsActiveUpdateClient().isEmpty())) {
      synchronized (getNeedsActiveUpdateClient())
      {
        while (!getNeedsActiveUpdateClient().isEmpty())
        {
          localObject1 = (class_796)getNeedsActiveUpdateClient().remove(0);
          ((class_798)this).a().handleBlockActivate((class_796)localObject1, ((class_796)localObject1).a10());
        }
      }
    }
    if ((this.aabbRecalcFlag) && (getPhysicsDataContainer().isInitialized()))
    {
      flagupdateMass();
      ((CompoundShape)getPhysicsDataContainer().getShape()).recalculateLocalAabb();
      this.aabbRecalcFlag = false;
    }
    if ((isFlagSegmentBufferAABBUpdate()) && (getPhysicsDataContainer().isInitialized()))
    {
      if (getPhysicsDataContainer().getObject() != null) {
        getPhysicsDataContainer().getObject().activate(true);
      }
      aabbRecalcFlag();
      setFlagSegmentBufferAABBUpdate(false);
    }
    if ((!isOnServer()) && (class_969.a1() != null))
    {
      this.camLocalTmp.set(class_969.a1().a83());
      getClientTransformInverse().set(getWorldTransformClient());
      getClientTransformInverse().inverse();
      getClientTransformInverse().transform(this.camLocalTmp);
      this.camPosLocal.set(this.camLocalTmp);
      if (((class_969.a1() instanceof class_197)) && (((class_197)class_969.a1()).a() == this))
      {
        getCamForwLocal().set(class_969.a1().i2());
        getCamLeftLocal().set(class_969.a1().h4());
        getCamLeftLocal().negate();
        getCamUpLocal().set(class_969.a1().g5());
        getWorldTransformInverse().basis.transform(getCamForwLocal());
        getWorldTransformInverse().basis.transform(getCamLeftLocal());
        getWorldTransformInverse().basis.transform(getCamUpLocal());
      }
    }
    try
    {
      this.segmentProvider.d1();
    }
    catch (InterruptedException localInterruptedException)
    {
      (??? = localInterruptedException).printStackTrace();
      throw new ErrorDialogException(((InterruptedException)???).getMessage());
    }
    catch (IOException localIOException)
    {
      localIOException;
    }
    getPhysicsState().d();
    getSegmentBuffer();
    this.lastSector = getSectorId();
  }
  
  private void warpOutOfCollision()
  {
    if ((isOnServer()) && (getMass() > 0.0F))
    {
      Vector3f localVector3f1 = new Vector3f();
      Vector3f localVector3f2 = new Vector3f();
      Transform localTransform = new Transform(getWorldTransform());
      int i = 0;
      do
      {
        getTransformedAABB(localVector3f1, localVector3f2, 1.0F, new Vector3f(), new Vector3f());
        i = 0;
        localObject1 = getState().getLocalAndRemoteObjectContainer().getLocalObjects().values().iterator();
        while (((Iterator)localObject1).hasNext())
        {
          Object localObject2;
          if ((((localObject2 = (Sendable)((Iterator)localObject1).next()) instanceof class_797)) && ((localObject2 = (class_797)localObject2) != this) && (((class_797)localObject2).getSectorId() == getSectorId()) && (!((class_797)localObject2).isHidden()))
          {
            Vector3f localVector3f3 = new Vector3f();
            Vector3f localVector3f4 = new Vector3f();
            ((class_797)localObject2).getTransformedAABB(localVector3f3, localVector3f4, 1.0F, new Vector3f(), new Vector3f());
            if (AabbUtil2.testAabbAgainstAabb2(localVector3f1, localVector3f2, localVector3f3, localVector3f4))
            {
              i = 1;
              break;
            }
          }
        }
        if (i != 0) {
          getWorldTransform().origin.field_616 += 20.0F;
        }
      } while (i != 0);
      System.err.println("[SEREVR][SEGMENTCONTROLLER] WARNING: COLLISION RECOVER: " + this + " warped from " + localTransform.origin + " to " + getWorldTransform().origin);
      Object localObject1 = new Transform(getWorldTransform());
      getWorldTransform().set(localTransform);
      warpTransformable((Transform)localObject1, true);
    }
  }
  
  public boolean updateMass()
  {
    return true;
  }
  
  public abstract void writeAllBufferedSegmentsToDatabase();
  
  public long getTimeCreated()
  {
    return this.timeCreated;
  }
  
  public void setTimeCreated(long paramLong)
  {
    this.timeCreated = paramLong;
  }
  
  public boolean isFlagSegmentBufferAABBUpdate()
  {
    return this.flagSegmentBufferAABBUpdate;
  }
  
  public void setFlagSegmentBufferAABBUpdate(boolean paramBoolean)
  {
    this.flagSegmentBufferAABBUpdate = paramBoolean;
  }
  
  public String getLastModifier()
  {
    return this.lastModifier;
  }
  
  public void setLastModifier(String paramString)
  {
    this.lastModifier = paramString;
  }
  
  public String getSpawner()
  {
    return this.spawner;
  }
  
  public ArrayList getNeedsActiveUpdateClient()
  {
    return this.needsActiveUpdateClient;
  }
  
  public Transform getClientTransformInverse()
  {
    return this.clientTransformInverse;
  }
  
  public void destroyPersistent()
  {
    assert (isOnServer());
    class_1041 localclass_1041 = (class_1041)getState();
    new StringBuilder().append(class_1041.field_144).append(getUniqueIdentifier()).toString();
    try
    {
      Object localObject1 = new File(class_1041.field_144 + getUniqueIdentifier() + ".ent");
      System.err.println("[SERVER][SEGMENTCONTROLLER] PERMANENTLY DELETING ENTITY: " + ((File)localObject1).getName());
      ((File)localObject1).delete();
      localObject1 = new class_896(this);
      for (Object localObject2 : new File(class_1041.jdField_field_149_of_type_JavaLangString).listFiles((FilenameFilter)localObject1))
      {
        System.err.println("[SERVER][SEGMENTCONTROLLER] PERMANENTLY DELETING ENTITY DATA: " + localObject2.getName());
        localObject2.delete();
      }
      localclass_1041.a66().b2(this);
      return;
    }
    catch (SQLException localSQLException)
    {
      localSQLException;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.SegmentController
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */