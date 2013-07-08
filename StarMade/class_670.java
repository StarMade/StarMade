import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.dynamics.DynamicsWorld;
import com.bulletphysics.linearmath.AabbUtil2;
import com.bulletphysics.linearmath.Transform;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import org.schema.common.util.ByteUtil;
import org.schema.game.common.controller.EditableSendableSegmentController;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.database.DatabaseIndex;
import org.schema.game.common.data.physics.CubeRayCastResult;
import org.schema.game.common.data.physics.ModifiedDynamicsWorld;
import org.schema.game.common.data.physics.PhysicsExt;
import org.schema.game.common.data.world.Segment;
import org.schema.game.common.data.world.Universe;
import org.schema.game.server.controller.EntityAlreadyExistsException;
import org.schema.game.server.controller.EntityNotFountException;
import org.schema.game.server.controller.GameServerController;
import org.schema.schine.graphicsengine.shader.ErrorDialogException;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.SynchronizationContainerController;
import org.schema.schine.network.objects.Sendable;
import org.schema.schine.network.objects.container.PhysicsDataContainer;
import org.schema.schine.network.server.ServerEntityWriterThread;
import org.schema.schine.network.server.ServerMessage;

public class class_670
  implements class_80, class_1407
{
  private static class_902[] jdField_field_136_of_type_ArrayOfClass_902;
  public class_48 field_136;
  private final class_1041 jdField_field_136_of_type_Class_1041;
  private float jdField_field_136_of_type_Float;
  private boolean jdField_field_139_of_type_Boolean = false;
  private boolean jdField_field_182_of_type_Boolean;
  public static final class_48 field_139;
  public Set field_136;
  private class_1419 jdField_field_136_of_type_Class_1419;
  private static int jdField_field_136_of_type_Int = 1;
  private class_665 jdField_field_136_of_type_Class_665;
  private int jdField_field_139_of_type_Int = 0;
  private Vector3f jdField_field_136_of_type_JavaxVecmathVector3f = new Vector3f();
  private Vector3f jdField_field_139_of_type_JavaxVecmathVector3f = new Vector3f();
  private Vector3f jdField_field_182_of_type_JavaxVecmathVector3f = new Vector3f();
  private Vector3f jdField_field_183_of_type_JavaxVecmathVector3f = new Vector3f();
  private Vector3f jdField_field_184_of_type_JavaxVecmathVector3f = new Vector3f(-jdField_field_136_of_type_Int << 4, -jdField_field_136_of_type_Int << 4, -jdField_field_136_of_type_Int << 4);
  private Vector3f jdField_field_185_of_type_JavaxVecmathVector3f = new Vector3f(jdField_field_136_of_type_Int << 4, jdField_field_136_of_type_Int << 4, jdField_field_136_of_type_Int << 4);
  private ArrayList jdField_field_136_of_type_JavaUtilArrayList = new ArrayList();
  private int jdField_field_182_of_type_Int;
  private long jdField_field_136_of_type_Long;
  private boolean jdField_field_183_of_type_Boolean;
  private static ArrayList jdField_field_139_of_type_JavaUtilArrayList = new ArrayList();
  private Object jdField_field_136_of_type_JavaLangObject = new Object();
  private class_8 jdField_field_136_of_type_Class_8;
  private boolean jdField_field_184_of_type_Boolean;
  private final Map jdField_field_136_of_type_JavaUtilMap = new HashMap();
  private long jdField_field_139_of_type_Long;
  private boolean jdField_field_185_of_type_Boolean;
  private class_791 jdField_field_136_of_type_Class_791;
  private float jdField_field_139_of_type_Float = 0.0F;
  private Transform jdField_field_136_of_type_ComBulletphysicsLinearmathTransform = new Transform();
  private Vector3f jdField_field_186_of_type_JavaxVecmathVector3f = new Vector3f();
  private Vector3f jdField_field_192_of_type_JavaxVecmathVector3f = new Vector3f();
  private Vector3f field_193 = new Vector3f();
  private Vector3f field_194 = new Vector3f();
  private ArrayList jdField_field_182_of_type_JavaUtilArrayList = new ArrayList();
  private boolean jdField_field_186_of_type_Boolean;
  private long jdField_field_182_of_type_Long = -1L;
  public boolean field_136;
  private long jdField_field_183_of_type_Long;
  
  public class_670(class_1041 paramclass_1041)
  {
    this.jdField_field_136_of_type_JavaUtilSet = new HashSet();
    this.jdField_field_136_of_type_Class_1041 = paramclass_1041;
    this.jdField_field_182_of_type_Int = paramclass_1041.getNextFreeObjectId();
    this.jdField_field_136_of_type_Class_1419 = paramclass_1041.a62().getPhysicsInstance(this);
    this.jdField_field_136_of_type_Class_1419.getDynamicsWorld().setInternalTickCallback(new class_805(this, (byte)0), null);
    this.jdField_field_139_of_type_Long = System.currentTimeMillis();
  }
  
  private void a60(class_1041 paramclass_1041, class_48 paramclass_481, class_48 paramclass_482)
  {
    class_48 localclass_481;
    a73(localclass_481 = new class_48());
    class_705 localclass_705;
    (localclass_705 = new class_705(paramclass_1041)).setSeed(Universe.getRandom().nextInt());
    int i = Universe.getRandom().nextInt(5) - 2;
    class_48 localclass_482 = class_789.a192(localclass_481, new class_48());
    if (paramclass_1041.a62().getStellarSystemFromSecPos(localclass_482).field_136 == class_808.field_1069)
    {
      int m = ByteUtil.d((localclass_482 = this.jdField_field_136_of_type_Class_48).field_475) - 8;
      int n = ByteUtil.d(localclass_482.field_476) - 8;
      int j = ByteUtil.d(localclass_482.field_477) - 8;
      Vector3f localVector3f2 = new Vector3f(8.0F, 8.0F, 8.0F);
      Vector3f localVector3f1 = new Vector3f(m, n, j);
      float f = Math.min(1.0F, localVector3f1.length() / localVector3f2.length());
      f = 1.0F - f;
      for (int i1 = 0; (i1 < class_902.values().length) && (jdField_field_136_of_type_ArrayOfClass_902[i1].field_1129 <= f); i1++) {}
      i1 = Math.min(class_902.values().length - 1, Math.max(0, i1 + i));
      localclass_705.setCreatorId(jdField_field_136_of_type_ArrayOfClass_902[i1].ordinal());
    }
    else
    {
      localclass_705.setCreatorId(Universe.getRandom().nextInt(class_902.values().length));
    }
    localclass_705.setUniqueIdentifier("ENTITY_FLOATINGROCK_" + System.currentTimeMillis());
    localclass_705.getMinPos().b1(paramclass_481);
    localclass_705.getMaxPos().b1(paramclass_482);
    localclass_705.setId(paramclass_1041.getNextFreeObjectId());
    localclass_705.setSectorId(this.jdField_field_182_of_type_Int);
    localclass_705.initialize();
    int k = 0;
    long l1 = System.currentTimeMillis();
    while ((a61(localclass_705, localclass_481) != null) && (k < 1000))
    {
      a73(localclass_481);
      k++;
    }
    long l2;
    if ((l2 = System.currentTimeMillis() - l1) > 10L) {
      System.err.println("[SECTOR] Placing ROCK took " + l2 + "ms");
    }
    if (k < 1000)
    {
      this.jdField_field_136_of_type_JavaUtilArrayList.add(localclass_705);
      paramclass_1041.a59().a4().addImmediateSynchronizedObject(localclass_705);
      return;
    }
    try
    {
      throw new RuntimeException("Could not place rock " + localclass_705.getMinPos() + "; " + localclass_705.getMaxPos());
    }
    catch (RuntimeException localRuntimeException)
    {
      localRuntimeException.printStackTrace();
      System.out.println("[ERRORLOG][SECTOR] PRINTING AABB of all objects");
      paramclass_1041 = this.jdField_field_136_of_type_JavaUtilArrayList.iterator();
      while (paramclass_1041.hasNext()) {
        if (((paramclass_481 = (SegmentController)paramclass_1041.next()) instanceof class_1421)) {
          if ((!(paramclass_481 instanceof class_797)) || (((class_797)paramclass_481).getSectorId() == localclass_705.getSectorId()))
          {
            (paramclass_482 = (class_1421)paramclass_481).getPhysicsDataContainer().getShape().getAabb(paramclass_482.getPhysicsDataContainer().getCurrentPhysicsTransform(), this.jdField_field_182_of_type_JavaxVecmathVector3f, this.jdField_field_183_of_type_JavaxVecmathVector3f);
            System.out.println("[ERRORLOG][SECTOR] " + paramclass_481 + ": [" + this.jdField_field_182_of_type_JavaxVecmathVector3f + " " + this.jdField_field_183_of_type_JavaxVecmathVector3f + "]");
          }
        }
      }
    }
  }
  
  private Sendable a61(SegmentController paramSegmentController, class_48 paramclass_48)
  {
    long l1 = System.currentTimeMillis();
    if ((paramSegmentController instanceof SegmentController))
    {
      SegmentController localSegmentController;
      (localSegmentController = paramSegmentController).getInitialTransform().setIdentity();
      localSegmentController.getInitialTransform().origin.set(paramclass_48.field_475, paramclass_48.field_476, paramclass_48.field_477);
      this.jdField_field_184_of_type_JavaxVecmathVector3f.set(localSegmentController.getMinPos().field_475 << 4, localSegmentController.getMinPos().field_476 << 4, localSegmentController.getMinPos().field_477 << 4);
      this.jdField_field_185_of_type_JavaxVecmathVector3f.set(localSegmentController.getMaxPos().field_475 << 4, localSegmentController.getMaxPos().field_476 << 4, localSegmentController.getMaxPos().field_477 << 4);
      AabbUtil2.transformAabb(this.jdField_field_184_of_type_JavaxVecmathVector3f, this.jdField_field_185_of_type_JavaxVecmathVector3f, 100.0F, localSegmentController.getInitialTransform(), this.jdField_field_136_of_type_JavaxVecmathVector3f, this.jdField_field_139_of_type_JavaxVecmathVector3f);
      Iterator localIterator = this.jdField_field_136_of_type_JavaUtilArrayList.iterator();
      while (localIterator.hasNext()) {
        if (((paramSegmentController = (SegmentController)localIterator.next()) instanceof class_1421)) {
          if ((!(paramSegmentController instanceof class_797)) || (((class_797)paramSegmentController).getSectorId() == localSegmentController.getSectorId()))
          {
            class_1421 localclass_1421;
            (localclass_1421 = (class_1421)paramSegmentController).getPhysicsDataContainer().getShape().getAabb(localclass_1421.getPhysicsDataContainer().getCurrentPhysicsTransform(), this.jdField_field_182_of_type_JavaxVecmathVector3f, this.jdField_field_183_of_type_JavaxVecmathVector3f);
            if (AabbUtil2.testAabbAgainstAabb2(this.jdField_field_136_of_type_JavaxVecmathVector3f, this.jdField_field_139_of_type_JavaxVecmathVector3f, this.jdField_field_182_of_type_JavaxVecmathVector3f, this.jdField_field_183_of_type_JavaxVecmathVector3f))
            {
              long l3;
              if ((l3 = System.currentTimeMillis() - l1) > 10L) {
                System.err.println("[Sector] [Sector] collision test at " + paramclass_48 + " is true: trying another pos " + l3 + "ms");
              }
              return paramSegmentController;
            }
          }
        }
      }
    }
    long l2;
    if ((l2 = System.currentTimeMillis() - l1) > 10L) {
      System.err.println("[Sector] No Collission: " + l2 + "ms");
    }
    return null;
  }
  
  public final Sendable a62(class_797 paramclass_797, Vector3f paramVector3f)
  {
    long l1 = System.currentTimeMillis();
    Object localObject1;
    (localObject1 = new Transform()).basis.set(paramclass_797.getWorldTransform().basis);
    ((Transform)localObject1).origin.set(paramVector3f);
    paramclass_797.getRemoteTransformable().getPhysicsDataContainer().getShape().getAabb((Transform)localObject1, this.jdField_field_136_of_type_JavaxVecmathVector3f, this.jdField_field_139_of_type_JavaxVecmathVector3f);
    synchronized (this.jdField_field_136_of_type_Class_1041.getLocalAndRemoteObjectContainer().getLocalObjects())
    {
      Iterator localIterator = this.jdField_field_136_of_type_Class_1041.getLocalAndRemoteObjectContainer().getLocalObjects().values().iterator();
      while (localIterator.hasNext()) {
        if (((localObject1 = (Sendable)localIterator.next()) instanceof class_1421)) {
          if ((!(localObject1 instanceof class_797)) || (((class_797)localObject1).getSectorId() == paramclass_797.getSectorId()))
          {
            class_1421 localclass_1421;
            (localclass_1421 = (class_1421)localObject1).getPhysicsDataContainer().getShape().getAabb(localclass_1421.getPhysicsDataContainer().getCurrentPhysicsTransform(), this.jdField_field_182_of_type_JavaxVecmathVector3f, this.jdField_field_183_of_type_JavaxVecmathVector3f);
            if (AabbUtil2.testAabbAgainstAabb2(this.jdField_field_136_of_type_JavaxVecmathVector3f, this.jdField_field_139_of_type_JavaxVecmathVector3f, this.jdField_field_182_of_type_JavaxVecmathVector3f, this.jdField_field_183_of_type_JavaxVecmathVector3f))
            {
              long l3;
              if ((l3 = System.currentTimeMillis() - l1) > 10L) {
                System.err.println("[Sector] collision test at " + paramVector3f + " is true: trying another pos " + this.jdField_field_136_of_type_JavaxVecmathVector3f + ", " + this.jdField_field_139_of_type_JavaxVecmathVector3f + " ---> " + this.jdField_field_182_of_type_JavaxVecmathVector3f + ", " + this.jdField_field_183_of_type_JavaxVecmathVector3f + ": " + l3 + "ms");
              }
              return localObject1;
            }
          }
        }
      }
    }
    long l2;
    if ((l2 = System.currentTimeMillis() - l1) > 10L) {
      System.err.println("[Sector] No Collission: " + l2 + "ms");
    }
    return null;
  }
  
  public final void a13()
  {
    synchronized (this.jdField_field_136_of_type_JavaLangObject)
    {
      ((ModifiedDynamicsWorld)this.jdField_field_136_of_type_Class_1419.getDynamicsWorld()).clean();
      this.jdField_field_136_of_type_Class_1041.a62().addToFreePhysics(this.jdField_field_136_of_type_Class_1419, this);
      return;
    }
  }
  
  public void fromTagStructure(class_69 paramclass_69)
  {
    paramclass_69 = (class_69[])paramclass_69.a4();
    this.jdField_field_136_of_type_Class_48 = ((class_48)paramclass_69[0].a4());
    if ((!jdField_field_192_of_type_Boolean) && (this.jdField_field_136_of_type_Class_48 == null)) {
      throw new AssertionError();
    }
    class_69[] arrayOfclass_69;
    int i = (arrayOfclass_69 = (class_69[])paramclass_69[1].a4()).length;
    Object localObject;
    for (int j = 0; (j < i) && ((localObject = arrayOfclass_69[j]).a3() != class_79.field_548); j++)
    {
      localObject = (String)((class_69)localObject).a4();
      this.jdField_field_136_of_type_JavaUtilSet.add(localObject);
    }
    if ((paramclass_69.length > 2) && (paramclass_69[2].a3() == class_79.field_551)) {
      this.jdField_field_139_of_type_Int = ((Integer)paramclass_69[2].a4()).intValue();
    }
    if ((paramclass_69.length > 3) && (paramclass_69[3].a3() == class_79.field_561))
    {
      arrayOfclass_69 = (class_69[])paramclass_69[3].a4();
      for (i = 0; i < arrayOfclass_69.length - 1; i++)
      {
        class_637 localclass_637;
        (localclass_637 = new class_637()).fromTagStructure(arrayOfclass_69[i]);
        localclass_637.b6(class_1041.field_148++);
        if (localclass_637.a34() != 0) {
          this.jdField_field_136_of_type_JavaUtilMap.put(Integer.valueOf(localclass_637.b5()), localclass_637);
        }
      }
    }
    System.err.println("Read From Disk: " + this);
  }
  
  public class_69 toTagStructure()
  {
    ArrayList localArrayList;
    class_69[] arrayOfclass_691 = new class_69[(localArrayList = a75()).size() + 1];
    int i = 0;
    for (int j = 0; j < localArrayList.size(); j++)
    {
      arrayOfclass_691[i] = new class_69(class_79.field_556, null, ((class_797)localArrayList.get(j)).getUniqueIdentifier());
      i++;
    }
    i = 0;
    class_69[] arrayOfclass_692 = new class_69[this.jdField_field_136_of_type_Class_665.a40().size() + 1];
    Iterator localIterator = this.jdField_field_136_of_type_Class_665.a40().values().iterator();
    while (localIterator.hasNext())
    {
      class_637 localclass_637 = (class_637)localIterator.next();
      arrayOfclass_692[i] = localclass_637.toTagStructure();
      i++;
    }
    arrayOfclass_692[(arrayOfclass_692.length - 1)] = new class_69(class_79.field_548, null, null);
    arrayOfclass_691[localArrayList.size()] = new class_69(class_79.field_548, null, null);
    return new class_69(class_79.field_561, "SECTOR", new class_69[] { new class_69(class_79.field_558, "POS", this.jdField_field_136_of_type_Class_48), new class_69(class_79.field_561, "idents", arrayOfclass_691), new class_69(class_79.field_551, "PROT", Integer.valueOf(this.jdField_field_139_of_type_Int)), new class_69(class_79.field_561, "items", arrayOfclass_692), new class_69(class_79.field_548, null, null) });
  }
  
  public final int a3()
  {
    return this.jdField_field_182_of_type_Int;
  }
  
  public final long a28()
  {
    return this.jdField_field_136_of_type_Long;
  }
  
  public final class_8 a63()
  {
    return this.jdField_field_136_of_type_Class_8;
  }
  
  public final class_1419 a64()
  {
    return this.jdField_field_136_of_type_Class_1419;
  }
  
  public String getUniqueIdentifier()
  {
    return "SECTOR_" + this.jdField_field_136_of_type_Class_48.field_475 + "." + this.jdField_field_136_of_type_Class_48.field_476 + "." + this.jdField_field_136_of_type_Class_48.field_477;
  }
  
  public final void d2()
  {
    this.jdField_field_136_of_type_Float = Math.max(this.jdField_field_136_of_type_Float, 0.0F);
  }
  
  public final boolean a7()
  {
    return this.jdField_field_139_of_type_Boolean;
  }
  
  public boolean isVolatile()
  {
    return false;
  }
  
  public final void a65(class_1041 paramclass_1041)
  {
    Iterator localIterator = this.jdField_field_136_of_type_JavaUtilSet.iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      a66(paramclass_1041, str);
    }
  }
  
  public final void a66(class_1041 paramclass_1041, String paramString)
  {
    synchronized (paramclass_1041.getLocalAndRemoteObjectContainer().getLocalObjects())
    {
      int i = 0;
      Iterator localIterator = paramclass_1041.getLocalAndRemoteObjectContainer().getLocalObjects().values().iterator();
      while (localIterator.hasNext())
      {
        Sendable localSendable;
        if ((((localSendable = (Sendable)localIterator.next()) instanceof class_80)) && (((class_80)localSendable).getUniqueIdentifier().equals(paramString)))
        {
          System.err.println("[SECTOR] entity " + paramString + " is still active -> not loaded from disk again");
          i = 1;
          break;
        }
      }
      if (i != 0) {
        return;
      }
    }
    Object localObject1;
    if (paramString.startsWith("ENTITY_FLOATINGROCK_"))
    {
      ??? = paramString.substring(20);
      if ((localObject1 = paramclass_1041.a66().a6((String)???, 0)).size() > 0)
      {
        int j = 0;
        for (int k = 0; k < ((List)localObject1).size(); k++) {
          if ((((class_757)((List)localObject1).get(k)).jdField_field_1017_of_type_Int == 3) && (!((class_757)((List)localObject1).get(k)).jdField_field_1017_of_type_Boolean))
          {
            Universe.loadEntity(paramclass_1041, (class_757)((List)localObject1).get(k), this);
            j = 1;
            break;
          }
        }
        if (j != 0) {
          return;
        }
      }
    }
    ??? = new File(class_1041.field_144 + paramString + ".ent");
    try
    {
      if ((localObject1 = ((File)???).getName().split("_")).length <= 0) {
        return;
      }
      a67(localObject1[1], (File)???);
      return;
    }
    catch (Exception localException)
    {
      System.err.println("[SERVER][ERROR] Exception Loading Sector " + ((File)???).getName() + ";");
      localException.printStackTrace();
    }
  }
  
  private Sendable a67(String paramString, File paramFile)
  {
    return Universe.loadEntity(this.jdField_field_136_of_type_Class_1041, paramString, paramFile, this);
  }
  
  public final void b4()
  {
    this.jdField_field_136_of_type_Long = System.currentTimeMillis();
  }
  
  public final void c1()
  {
    this.jdField_field_136_of_type_Long = (System.currentTimeMillis() - ((Integer)class_1057.field_1324.a3()).intValue() * 1000 + 1000L);
  }
  
  public final class_808 a68()
  {
    return this.jdField_field_136_of_type_Class_1041.a62().getStellarSystemFromSecPos(this.jdField_field_136_of_type_Class_48).a196(this.jdField_field_136_of_type_Class_48);
  }
  
  public final class_810 a69()
  {
    return this.jdField_field_136_of_type_Class_1041.a62().getStellarSystemFromSecPos(this.jdField_field_136_of_type_Class_48).a197(this.jdField_field_136_of_type_Class_48);
  }
  
  public final class_780 a70()
  {
    return this.jdField_field_136_of_type_Class_1041.a62().getStellarSystemFromSecPos(this.jdField_field_136_of_type_Class_48).a198(this.jdField_field_136_of_type_Class_48);
  }
  
  public final void b11(class_1041 paramclass_1041)
  {
    Object localObject = paramclass_1041.a62().getStellarSystemFromSecPos(this.jdField_field_136_of_type_Class_48);
    switch (class_806.field_1061[localObject.a196(this.jdField_field_136_of_type_Class_48).ordinal()])
    {
    case 1: 
      localObject = paramclass_1041;
      paramclass_1041 = this;
      int i = Universe.getRandom().nextInt(5) + 2;
      if (Math.random() < 0.08D)
      {
        class_743 localclass_743;
        (localclass_743 = new class_743((StateInterface)localObject)).setSeed(Universe.getRandom().nextInt());
        localclass_743.setUniqueIdentifier("ENTITY_SHOP_" + System.currentTimeMillis());
        localclass_743.getMinPos().b1(new class_48(0, -6, 0));
        localclass_743.getMaxPos().b1(new class_48(0, 6, 0));
        localclass_743.setId(((class_1041)localObject).getNextFreeObjectId());
        localclass_743.setSectorId(paramclass_1041.jdField_field_182_of_type_Int);
        localclass_743.initialize();
        localclass_743.a72(false);
        localclass_743.getInitialTransform().setIdentity();
        localclass_743.getInitialTransform().origin.set(500.0F, Universe.getRandom().nextInt(250), 500.0F);
        ((class_1041)localObject).a59().a4().addImmediateSynchronizedObject(localclass_743);
        paramclass_1041.jdField_field_136_of_type_JavaUtilArrayList.add(localclass_743);
      }
      for (int j = 0; j < i; j++) {
        paramclass_1041.a60((class_1041)localObject, new class_48(-jdField_field_136_of_type_Int - Universe.getRandom().nextInt(4), -jdField_field_136_of_type_Int - Universe.getRandom().nextInt(4), -jdField_field_136_of_type_Int - Universe.getRandom().nextInt(4)), new class_48(jdField_field_136_of_type_Int + Universe.getRandom().nextInt(4), jdField_field_136_of_type_Int + Universe.getRandom().nextInt(4), jdField_field_136_of_type_Int + Universe.getRandom().nextInt(4)));
      }
      return;
    case 2: 
      class_780 localclass_780 = ((class_791)localObject).a198(this.jdField_field_136_of_type_Class_48);
      localObject = paramclass_1041;
      paramclass_1041 = this;
      switch (class_806.field_1062[localclass_780.ordinal()])
      {
      case 1: 
        return;
      case 2: 
        paramclass_1041.e1((class_1041)localObject);
        return;
      case 3: 
        paramclass_1041.f((class_1041)localObject);
      }
      return;
    case 3: 
      a71(paramclass_1041, ((class_791)localObject).a197(this.jdField_field_136_of_type_Class_48));
      return;
    case 4: 
      d3(paramclass_1041);
      return;
    case 5: 
      return;
    case 6: 
      return;
    case 7: 
      return;
    }
    if (!jdField_field_192_of_type_Boolean) {
      throw new AssertionError("unknown sector type " + ((class_791)localObject).a196(this.jdField_field_136_of_type_Class_48));
    }
  }
  
  private void d3(class_1041 paramclass_1041)
  {
    class_743 localclass_743;
    (localclass_743 = new class_743(paramclass_1041)).setSeed(Universe.getRandom().nextInt());
    localclass_743.setUniqueIdentifier("ENTITY_SHOP_" + System.currentTimeMillis());
    localclass_743.getMinPos().b1(new class_48(0, -10, 0));
    localclass_743.getMaxPos().b1(new class_48(0, 10, 0));
    localclass_743.setId(paramclass_1041.getNextFreeObjectId());
    localclass_743.setSectorId(this.jdField_field_182_of_type_Int);
    localclass_743.initialize();
    localclass_743.a72(false);
    localclass_743.getInitialTransform().setIdentity();
    localclass_743.getInitialTransform().origin.set(0.0F, 0.0F, 0.0F);
    paramclass_1041.a59().a4().addImmediateSynchronizedObject(localclass_743);
    this.jdField_field_136_of_type_JavaUtilArrayList.add(localclass_743);
    int i = 1 + Universe.getRandom().nextInt(3);
    for (int j = 0; j < i; j++) {
      a60(paramclass_1041, new class_48(-jdField_field_136_of_type_Int - Universe.getRandom().nextInt(3), -jdField_field_136_of_type_Int - Universe.getRandom().nextInt(3), -jdField_field_136_of_type_Int - Universe.getRandom().nextInt(3)), new class_48(jdField_field_136_of_type_Int + Universe.getRandom().nextInt(3), jdField_field_136_of_type_Int + Universe.getRandom().nextInt(3), jdField_field_136_of_type_Int + Universe.getRandom().nextInt(3)));
    }
  }
  
  private void a71(class_1041 paramclass_1041, class_810 paramclass_810)
  {
    class_48 localclass_48 = new class_48();
    System.err.println("populating sector " + localclass_48);
    class_864 localclass_864;
    (localclass_864 = new class_864(paramclass_1041)).setCreatorId(paramclass_810.ordinal());
    localclass_864.setSeed(Universe.getRandom().nextInt());
    localclass_864.setUniqueIdentifier("ENTITY_PLANET_" + this.jdField_field_136_of_type_Class_48.field_475 + "_" + this.jdField_field_136_of_type_Class_48.field_476 + "_" + this.jdField_field_136_of_type_Class_48.field_477 + "_" + System.currentTimeMillis());
    localclass_864.getMinPos().b1(new class_48(-16, 0, -16));
    localclass_864.getMaxPos().b1(new class_48(16, 4, 16));
    localclass_864.setId(paramclass_1041.getNextFreeObjectId());
    localclass_864.setSectorId(this.jdField_field_182_of_type_Int);
    localclass_864.initialize();
    while (a61(localclass_864, localclass_48) != null) {
      localclass_48.field_476 += 16;
    }
    paramclass_1041.a59().a4().addImmediateSynchronizedObject(localclass_864);
    this.jdField_field_136_of_type_JavaUtilArrayList.add(localclass_864);
  }
  
  private void e1(class_1041 paramclass_1041)
  {
    class_737 localclass_737;
    (localclass_737 = new class_737(paramclass_1041)).setSeed(Universe.getRandom().nextLong());
    localclass_737.setUniqueIdentifier("ENTITY_SPACESTATION_" + System.currentTimeMillis());
    localclass_737.setRealName("Station " + this.jdField_field_182_of_type_Int);
    localclass_737.getMinPos().b1(new class_48(-3, -3, -3));
    localclass_737.getMaxPos().b1(new class_48(3, 3, 3));
    localclass_737.setCreatorId(class_780.field_1037.ordinal());
    localclass_737.setId(paramclass_1041.getNextFreeObjectId());
    localclass_737.setSectorId(this.jdField_field_182_of_type_Int);
    localclass_737.initialize();
    localclass_737.getInitialTransform().setIdentity();
    paramclass_1041.a59().a4().addImmediateSynchronizedObject(localclass_737);
    this.jdField_field_136_of_type_JavaUtilArrayList.add(localclass_737);
    int i = Universe.getRandom().nextInt(4) + 2;
    for (int j = 0; j < i; j++) {
      a60(paramclass_1041, new class_48(-jdField_field_136_of_type_Int - Universe.getRandom().nextInt(3), -jdField_field_136_of_type_Int - Universe.getRandom().nextInt(3), -jdField_field_136_of_type_Int - Universe.getRandom().nextInt(3)), new class_48(jdField_field_136_of_type_Int + Universe.getRandom().nextInt(3), jdField_field_136_of_type_Int + Universe.getRandom().nextInt(3), jdField_field_136_of_type_Int + Universe.getRandom().nextInt(3)));
    }
  }
  
  private void f(class_1041 paramclass_1041)
  {
    class_737 localclass_737;
    (localclass_737 = new class_737(paramclass_1041)).setSeed(Universe.getRandom().nextLong());
    localclass_737.setUniqueIdentifier("ENTITY_SPACESTATION_P" + System.currentTimeMillis());
    localclass_737.setRealName("Station " + this.jdField_field_182_of_type_Int);
    localclass_737.getMinPos().b1(new class_48(-3, -3, -3));
    localclass_737.getMaxPos().b1(new class_48(3, 3, 3));
    localclass_737.setCreatorId(class_780.field_1039.ordinal());
    localclass_737.setFactionId(-1);
    localclass_737.setId(paramclass_1041.getNextFreeObjectId());
    localclass_737.setSectorId(this.jdField_field_182_of_type_Int);
    localclass_737.initialize();
    localclass_737.getInitialTransform().setIdentity();
    paramclass_1041.a59().a4().addImmediateSynchronizedObject(localclass_737);
    this.jdField_field_136_of_type_JavaUtilArrayList.add(localclass_737);
    int i = Universe.getRandom().nextInt(4) + 2;
    for (int j = 0; j < i; j++) {
      a60(paramclass_1041, new class_48(-jdField_field_136_of_type_Int - Universe.getRandom().nextInt(3), -jdField_field_136_of_type_Int - Universe.getRandom().nextInt(3), -jdField_field_136_of_type_Int - Universe.getRandom().nextInt(3)), new class_48(jdField_field_136_of_type_Int + Universe.getRandom().nextInt(3), jdField_field_136_of_type_Int + Universe.getRandom().nextInt(3), jdField_field_136_of_type_Int + Universe.getRandom().nextInt(3)));
    }
  }
  
  public final void a72(boolean paramBoolean)
  {
    if ((!this.jdField_field_139_of_type_Boolean) && (paramBoolean))
    {
      this.jdField_field_182_of_type_Boolean = false;
      this.jdField_field_183_of_type_Boolean = true;
      this.jdField_field_136_of_type_Long = System.currentTimeMillis();
      class_670 localclass_670 = this;
      if (jdField_field_139_of_type_JavaUtilArrayList.isEmpty())
      {
        localclass_670.jdField_field_136_of_type_Class_8 = new class_8(localclass_670.jdField_field_136_of_type_Class_1041, localclass_670.jdField_field_182_of_type_Int);
      }
      else
      {
        class_8 localclass_8;
        (localclass_8 = (class_8)jdField_field_139_of_type_JavaUtilArrayList.remove(0)).a5(localclass_670.jdField_field_182_of_type_Int);
        localclass_670.jdField_field_136_of_type_Class_8 = localclass_8;
      }
      synchronized (this.jdField_field_136_of_type_JavaLangObject)
      {
        this.jdField_field_139_of_type_Boolean = paramBoolean;
        return;
      }
    }
    if ((this.jdField_field_139_of_type_Boolean) && (!paramBoolean))
    {
      ??? = this;
      if ((!jdField_field_192_of_type_Boolean) && (((class_670)???).jdField_field_136_of_type_Class_8 == null)) {
        throw new AssertionError();
      }
      jdField_field_139_of_type_JavaUtilArrayList.add(((class_670)???).jdField_field_136_of_type_Class_8);
      ((class_670)???).jdField_field_136_of_type_Class_8 = null;
      if ((!jdField_field_192_of_type_Boolean) && (jdField_field_139_of_type_JavaUtilArrayList.get(jdField_field_139_of_type_JavaUtilArrayList.size() - 1) == null)) {
        throw new AssertionError();
      }
      this.jdField_field_139_of_type_Long = System.currentTimeMillis();
    }
    this.jdField_field_139_of_type_Boolean = paramBoolean;
  }
  
  public final void a36(int paramInt)
  {
    this.jdField_field_182_of_type_Int = paramInt;
  }
  
  private static void a73(class_48 paramclass_48)
  {
    paramclass_48.b(0, 0, 0);
    while (paramclass_48.a4() < 25.0F)
    {
      int i = Universe.getRandom().nextInt(500) - 250;
      int j = Universe.getRandom().nextInt(250) - 125;
      int k = Universe.getRandom().nextInt(500) - 250;
      paramclass_48.b(i, j, k);
    }
  }
  
  public String toString()
  {
    return "Sector[" + this.jdField_field_182_of_type_Int + "]" + this.jdField_field_136_of_type_Class_48;
  }
  
  public final String c5()
  {
    return "Sector[" + this.jdField_field_182_of_type_Int + "]" + this.jdField_field_136_of_type_Class_48 + "; @" + this.jdField_field_136_of_type_Class_1419;
  }
  
  public final void a74(class_941 paramclass_941)
  {
    if ((!jdField_field_192_of_type_Boolean) && (this.jdField_field_136_of_type_Boolean)) {
      throw new AssertionError();
    }
    class_1041.field_150 += 1;
    if (this.jdField_field_186_of_type_Boolean)
    {
      i();
      this.jdField_field_186_of_type_Boolean = false;
    }
    if (this.jdField_field_139_of_type_Boolean)
    {
      class_1041.field_151 += 1;
      if ((!jdField_field_192_of_type_Boolean) && (this.jdField_field_136_of_type_Class_8 == null)) {
        throw new AssertionError();
      }
      this.jdField_field_136_of_type_Class_8.a6(paramclass_941);
      if (this.jdField_field_184_of_type_Boolean)
      {
        class_670 localclass_670 = this;
        class_808 localclass_808 = this.jdField_field_136_of_type_Class_1041.a62().getStellarSystemFromSecPos(localclass_670.jdField_field_136_of_type_Class_48).a196(localclass_670.jdField_field_136_of_type_Class_48);
        if ((localclass_670.jdField_field_136_of_type_Class_48.equals(jdField_field_139_of_type_Class_48)) && (class_1057.field_1318.a4())) {
          localclass_670.a77(2, true);
        }
        if (((localclass_670.jdField_field_139_of_type_Int & 0x1) == 1 ? 1 : 0) == 0)
        {
          if (class_1057.field_1321.a4()) {}
        }
        else
        {
          System.err.println("[SECTOR] NEW SECTOR IS PROTECTED FROM SPAWNING ANY ENEMIES");
          break label252;
        }
        if ((localclass_808 == class_808.field_1066) && (Universe.getRandom().nextInt(100) == 0)) {
          try
          {
            localclass_670.jdField_field_136_of_type_Class_1041.a59().a43(Universe.getRandom().nextInt(8) + 3, 1, 3, class_1216.field_1429, localclass_670.jdField_field_182_of_type_Int);
          }
          catch (EntityNotFountException localEntityNotFountException)
          {
            localEntityNotFountException;
          }
          catch (ErrorDialogException localErrorDialogException)
          {
            localErrorDialogException;
          }
          catch (EntityAlreadyExistsException localEntityAlreadyExistsException)
          {
            localEntityAlreadyExistsException;
          }
        }
        label252:
        this.jdField_field_184_of_type_Boolean = false;
      }
      this.jdField_field_136_of_type_Class_1419.update(paramclass_941, this.jdField_field_136_of_type_Float);
      this.jdField_field_182_of_type_Boolean = false;
      this.jdField_field_136_of_type_Float = 0.0F;
      if ((this.jdField_field_185_of_type_Boolean) && (this.jdField_field_136_of_type_Class_791.field_136 == class_808.field_1069)) {
        b12(paramclass_941);
      }
    }
    else
    {
      if (this.jdField_field_183_of_type_Boolean)
      {
        long l1 = System.currentTimeMillis();
        a76(2, this.jdField_field_136_of_type_Class_1041.a62());
        long l2;
        if ((l2 = System.currentTimeMillis() - l1) > 4L) {
          System.err.println("[SERVER][SECTOR] WRITING SECTOR ID " + this.jdField_field_182_of_type_Int + " -> " + this.jdField_field_136_of_type_Class_48 + " TOOK " + l2 + "ms");
        }
      }
      this.jdField_field_183_of_type_Boolean = false;
    }
  }
  
  private void i()
  {
    try
    {
      localIterator = null;
      localIterator = this.jdField_field_136_of_type_Class_1041.a66().a9(this.jdField_field_136_of_type_Class_48, 0).iterator();
      while (localIterator.hasNext())
      {
        class_757 localclass_757 = (class_757)localIterator.next();
        try
        {
          if (!this.jdField_field_136_of_type_Class_1041.c8().containsKey(localclass_757.jdField_field_1017_of_type_JavaLangString.trim()))
          {
            String str = localclass_757.jdField_field_1017_of_type_JavaLangString.split("_", 3)[1];
            System.err.println("[REPAIR] FOUND SECTOR ENTITY: " + localclass_757.jdField_field_1017_of_type_JavaLangString + " [" + str + "]");
            a67(str, new File(localclass_757.jdField_field_1017_of_type_JavaLangString + ".ent"));
          }
        }
        catch (IOException localIOException)
        {
          localIOException;
        }
        catch (EntityNotFountException localEntityNotFountException)
        {
          localEntityNotFountException;
        }
        catch (ErrorDialogException localErrorDialogException)
        {
          localErrorDialogException;
        }
      }
    }
    catch (SQLException localSQLException)
    {
      Iterator localIterator = null;
      localSQLException.printStackTrace();
    }
    new File(class_1041.field_144).listFiles();
  }
  
  private void b12(class_941 arg1)
  {
    this.jdField_field_139_of_type_Float += ???.a();
    ??? = class_791.b38(this.jdField_field_136_of_type_Class_48) ? 0.005F : 0.5F;
    float f1 = class_791.b38(this.jdField_field_136_of_type_Class_48) ? 666.0F : 80.0F;
    if (this.jdField_field_139_of_type_Float > ???)
    {
      Universe.calcSunPosInnerStarSystem(this.jdField_field_136_of_type_Class_48, this.jdField_field_136_of_type_Class_791, this.jdField_field_136_of_type_Class_1041.a59().calculateStartTime(), this.jdField_field_136_of_type_ComBulletphysicsLinearmathTransform);
      this.jdField_field_182_of_type_JavaUtilArrayList.clear();
      synchronized (this.jdField_field_136_of_type_Class_1041.getLocalAndRemoteObjectContainer().getLocalObjects())
      {
        Iterator localIterator = this.jdField_field_136_of_type_Class_1041.getLocalAndRemoteObjectContainer().getLocalUpdatableObjects().values().iterator();
        while (localIterator.hasNext())
        {
          Sendable localSendable;
          if ((((localSendable = (Sendable)localIterator.next()) instanceof class_747)) && (((SegmentController)localSendable).getSectorId() == this.jdField_field_182_of_type_Int)) {
            this.jdField_field_182_of_type_JavaUtilArrayList.add((EditableSendableSegmentController)localSendable);
          }
        }
      }
      if (this.jdField_field_182_of_type_JavaUtilArrayList.isEmpty()) {
        return;
      }
      ??? = (EditableSendableSegmentController)this.jdField_field_182_of_type_JavaUtilArrayList.get(Universe.getRandom().nextInt(this.jdField_field_182_of_type_JavaUtilArrayList.size()));
      this.jdField_field_192_of_type_JavaxVecmathVector3f.set(???.getWorldTransform().origin);
      float f2 = ???.getSegmentBuffer().a6().a10() / 3.0F;
      this.jdField_field_186_of_type_JavaxVecmathVector3f.sub(this.jdField_field_192_of_type_JavaxVecmathVector3f, this.jdField_field_136_of_type_ComBulletphysicsLinearmathTransform.origin);
      float f3 = this.jdField_field_186_of_type_JavaxVecmathVector3f.length();
      this.jdField_field_186_of_type_JavaxVecmathVector3f.normalize();
      this.field_193.set(0.0F, 1.0F, 0.0F);
      this.field_194.cross(this.jdField_field_186_of_type_JavaxVecmathVector3f, this.field_193);
      this.field_194.normalize();
      this.field_193.cross(this.field_194, this.jdField_field_186_of_type_JavaxVecmathVector3f);
      this.field_193.normalize();
      this.jdField_field_186_of_type_JavaxVecmathVector3f.scale(f3 + f2);
      this.jdField_field_192_of_type_JavaxVecmathVector3f.add(this.jdField_field_136_of_type_ComBulletphysicsLinearmathTransform.origin, this.jdField_field_186_of_type_JavaxVecmathVector3f);
      this.field_193.scale(f2 * ((Universe.getRandom().nextFloat() - 0.5F) * 2.0F));
      this.field_194.scale(f2 * ((Universe.getRandom().nextFloat() - 0.5F) * 2.0F));
      this.jdField_field_192_of_type_JavaxVecmathVector3f.add(this.field_193);
      this.jdField_field_192_of_type_JavaxVecmathVector3f.add(this.field_194);
      Object localObject2;
      if (class_949.field_1221.b1())
      {
        localObject2 = new class_1424(new Vector3f(this.jdField_field_192_of_type_JavaxVecmathVector3f), new Vector4f(1.0F, 1.0F, 0.0F, 1.0F));
        class_1428.field_1638.add(localObject2);
        localObject2 = new class_992(this.jdField_field_136_of_type_ComBulletphysicsLinearmathTransform.origin, this.jdField_field_192_of_type_JavaxVecmathVector3f);
        class_1428.field_1639.add(localObject2);
      }
      if (((localObject2 = ((PhysicsExt)this.jdField_field_136_of_type_Class_1419).testRayCollisionPoint(this.jdField_field_136_of_type_ComBulletphysicsLinearmathTransform.origin, this.jdField_field_192_of_type_JavaxVecmathVector3f, false, null, null, true, null, false)) != null) && (((CollisionWorld.ClosestRayResultCallback)localObject2).hasHit()) && ((localObject2 instanceof CubeRayCastResult)) && (((CubeRayCastResult)localObject2).getSegment() != null) && (((CubeRayCastResult)localObject2).getSegment().a15() == ???))
      {
        ???.handleHit((CollisionWorld.ClosestRayResultCallback)localObject2, null, f1);
        if (((??? instanceof class_365)) && (System.currentTimeMillis() - this.jdField_field_183_of_type_Long > 3000L))
        {
          localObject2 = ((class_365)???).a26().iterator();
          while (((Iterator)localObject2).hasNext()) {
            (??? = (class_748)((Iterator)localObject2).next()).a129(new ServerMessage("WARNING!\nthe heat of the sun is\ndamaging the hulls!\nHide behind natural objects!\nINTESITY: " + f1, 3, ???.getId()));
          }
          this.jdField_field_183_of_type_Long = System.currentTimeMillis();
        }
      }
      this.jdField_field_139_of_type_Float = 0.0F;
    }
  }
  
  public final ArrayList a75()
  {
    ArrayList localArrayList;
    for (;;)
    {
      localArrayList = new ArrayList();
      try
      {
        Iterator localIterator = this.jdField_field_136_of_type_Class_1041.getLocalAndRemoteObjectContainer().getLocalObjects().values().iterator();
        while (localIterator.hasNext())
        {
          Object localObject;
          if ((((localObject = (Sendable)localIterator.next()) instanceof class_797)) && (((class_797)localObject).getSectorId() == this.jdField_field_182_of_type_Int))
          {
            (localObject = (class_797)localObject).getWorldTransform();
            localArrayList.add(localObject);
          }
        }
      }
      catch (ConcurrentModificationException localConcurrentModificationException)
      {
        localConcurrentModificationException.printStackTrace();
        System.err.println("CATCHED EXCEPTION!!!!!!!!!!!!!!!!!! (sector entity calc)");
      }
    }
    return localArrayList;
  }
  
  public final void a76(int paramInt, Universe paramUniverse)
  {
    ArrayList localArrayList = a75();
    class_1041 localclass_1041 = this.jdField_field_136_of_type_Class_1041;
    paramInt = new class_673(this, localclass_1041, localArrayList, paramInt, paramUniverse);
    try
    {
      localclass_1041.getThreadQueue().enqueue(paramInt);
      return;
    }
    catch (RejectedExecutionException localRejectedExecutionException)
    {
      localRejectedExecutionException.printStackTrace();
      System.err.println(localclass_1041.getThreadPool().getActiveCount() + "/" + localclass_1041.getThreadPool().getMaximumPoolSize());
    }
  }
  
  public final int b5()
  {
    return this.jdField_field_139_of_type_Int;
  }
  
  public final void b6(int paramInt)
  {
    this.jdField_field_139_of_type_Int = paramInt;
  }
  
  public final void e2()
  {
    this.jdField_field_184_of_type_Boolean = true;
  }
  
  public final void b13(boolean paramBoolean)
  {
    a77(2, paramBoolean);
  }
  
  public final void c6(boolean paramBoolean)
  {
    a77(1, paramBoolean);
  }
  
  private void a77(int paramInt, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      if ((this.jdField_field_139_of_type_Int & paramInt) != paramInt) {
        this.jdField_field_139_of_type_Int += paramInt;
      }
    }
    else if ((this.jdField_field_139_of_type_Int & paramInt) == paramInt) {
      this.jdField_field_139_of_type_Int -= paramInt;
    }
  }
  
  public final boolean b2()
  {
    return (this.jdField_field_139_of_type_Int & 0x2) == 2;
  }
  
  public final void f1()
  {
    if ((!jdField_field_192_of_type_Boolean) && (this.jdField_field_136_of_type_Class_665 != null)) {
      throw new AssertionError();
    }
    this.jdField_field_136_of_type_Class_665 = new class_665(this.jdField_field_136_of_type_Class_1041);
    this.jdField_field_136_of_type_Class_665.a39(this);
    this.jdField_field_136_of_type_Class_665.a41(this.jdField_field_136_of_type_JavaUtilMap);
    this.jdField_field_136_of_type_Class_1041.a59().a4().addNewSynchronizedObjectQueued(this.jdField_field_136_of_type_Class_665);
    class_48 localclass_48;
    int j = ByteUtil.d((localclass_48 = this.jdField_field_136_of_type_Class_48).field_475) - 8;
    int k = ByteUtil.d(localclass_48.field_476) - 8;
    int i = ByteUtil.d(localclass_48.field_477) - 8;
    this.jdField_field_185_of_type_Boolean = (class_49.a6(j, k, i) < 1.42F);
    class_791 localclass_791 = this.jdField_field_136_of_type_Class_1041.a62().getStellarSystemFromSecPos(this.jdField_field_136_of_type_Class_48);
    this.jdField_field_136_of_type_Class_791 = localclass_791;
    localclass_791.a196(this.jdField_field_136_of_type_Class_48);
  }
  
  public final class_665 a78()
  {
    return this.jdField_field_136_of_type_Class_665;
  }
  
  public final Map a79()
  {
    return this.jdField_field_136_of_type_Class_665.a40();
  }
  
  public final boolean c3()
  {
    return this.jdField_field_182_of_type_Boolean;
  }
  
  public final boolean a33(long paramLong)
  {
    int i;
    return ((i = ((Integer)class_1057.field_1325.a3()).intValue()) >= 0) && (paramLong > this.jdField_field_139_of_type_Long + i * 1000);
  }
  
  public static boolean a80(class_48 paramclass_481, class_48 paramclass_482)
  {
    (paramclass_482 = new class_48(paramclass_482)).c1(paramclass_481);
    return paramclass_482.a4() < 1.42F;
  }
  
  public final void g()
  {
    this.jdField_field_136_of_type_Class_1419 = null;
  }
  
  public final String b()
  {
    return "[PHYSICS][SERVER] WARNING: PHYSICS SYNC IN DANGER. SECTOR: " + this.jdField_field_136_of_type_Class_48 + " [" + this.jdField_field_182_of_type_Int + "]";
  }
  
  public final void h()
  {
    this.jdField_field_186_of_type_Boolean = true;
  }
  
  public final long b14()
  {
    return this.jdField_field_182_of_type_Long;
  }
  
  public final void a38(long paramLong)
  {
    this.jdField_field_182_of_type_Long = paramLong;
  }
  
  public static byte[] a81(Map paramMap)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream(paramMap.size() * 18);
    DataOutputStream localDataOutputStream = new DataOutputStream(localByteArrayOutputStream);
    paramMap = paramMap.entrySet().iterator();
    while (paramMap.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramMap.next();
      localDataOutputStream.writeShort(((class_637)localEntry.getValue()).a34());
      localDataOutputStream.writeInt(((class_637)localEntry.getValue()).a3());
      localDataOutputStream.writeFloat(((class_637)localEntry.getValue()).a8().field_615);
      localDataOutputStream.writeFloat(((class_637)localEntry.getValue()).a8().field_616);
      localDataOutputStream.writeFloat(((class_637)localEntry.getValue()).a8().field_617);
    }
    localByteArrayOutputStream.flush();
    return localByteArrayOutputStream.toByteArray();
  }
  
  public final void a82(byte[] paramArrayOfByte)
  {
    DataInputStream localDataInputStream = new DataInputStream(new ByteArrayInputStream(paramArrayOfByte));
    paramArrayOfByte = paramArrayOfByte.length / 18;
    System.err.println("[SERVER][SECTOR] READING " + paramArrayOfByte + " ITEMS");
    for (byte[] arrayOfByte = 0; arrayOfByte < paramArrayOfByte; arrayOfByte++)
    {
      class_637 localclass_637;
      (localclass_637 = new class_637()).b6(class_1041.b3());
      localclass_637.a35(localDataInputStream.readShort());
      localclass_637.a36(localDataInputStream.readInt());
      localclass_637.a37(new Vector3f(localDataInputStream.readFloat(), localDataInputStream.readFloat(), localDataInputStream.readFloat()));
      if (localclass_637.a34() != 0)
      {
        System.err.println("[SERVER][SECTOR] LOADED ITEM " + localclass_637.a34() + ": " + localclass_637.a3() + " at " + localclass_637.a8() + " with ID: " + localclass_637.b5());
        this.jdField_field_136_of_type_JavaUtilMap.put(Integer.valueOf(localclass_637.b5()), localclass_637);
      }
    }
  }
  
  public final void c7(class_1041 paramclass_1041)
  {
    this.jdField_field_136_of_type_JavaUtilSet = paramclass_1041.a66().a25(this.jdField_field_136_of_type_Class_48);
  }
  
  public boolean equals(Object paramObject)
  {
    return this.jdField_field_182_of_type_Int == ((class_670)paramObject).jdField_field_182_of_type_Int;
  }
  
  public int hashCode()
  {
    return this.jdField_field_182_of_type_Int;
  }
  
  public final float b1()
  {
    if (this.jdField_field_136_of_type_Class_1041.a12() != null) {
      return this.jdField_field_136_of_type_Class_1041.a12().b11();
    }
    throw new NullPointerException();
  }
  
  public final float c8()
  {
    if (this.jdField_field_136_of_type_Class_1041.a12() != null) {
      return this.jdField_field_136_of_type_Class_1041.a12().c3();
    }
    throw new NullPointerException();
  }
  
  static
  {
    jdField_field_136_of_type_ArrayOfClass_902 = new class_902[class_902.values().length];
    for (int i = 0; i < jdField_field_136_of_type_ArrayOfClass_902.length; i++) {
      jdField_field_136_of_type_ArrayOfClass_902[i] = class_902.values()[i];
    }
    Arrays.sort(jdField_field_136_of_type_ArrayOfClass_902, new class_675());
    for (i = 0; i < jdField_field_136_of_type_ArrayOfClass_902.length; i++) {}
    jdField_field_139_of_type_Class_48 = new class_48(2, 2, 2);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_670
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */