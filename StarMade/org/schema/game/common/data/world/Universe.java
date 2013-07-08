package org.schema.game.common.data.world;

import class_1041;
import class_1057;
import class_1098;
import class_1407;
import class_1419;
import class_48;
import class_665;
import class_670;
import class_69;
import class_705;
import class_707;
import class_737;
import class_743;
import class_747;
import class_748;
import class_750;
import class_753;
import class_757;
import class_765;
import class_782;
import class_789;
import class_791;
import class_797;
import class_80;
import class_800;
import class_858;
import class_864;
import class_880;
import class_886;
import class_941;
import com.bulletphysics.dynamics.DynamicsWorld;
import com.bulletphysics.linearmath.Transform;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayFIFOQueue;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.vecmath.Matrix3f;
import javax.vecmath.Tuple3f;
import javax.vecmath.Vector3f;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.database.DatabaseIndex;
import org.schema.game.common.data.element.Element;
import org.schema.game.common.data.physics.PairCachingGhostObjectAlignable;
import org.schema.game.common.data.physics.PhysicsExt;
import org.schema.game.server.controller.GameServerController;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.SynchronizationContainerController;
import org.schema.schine.network.objects.Sendable;
import org.schema.schine.network.objects.container.PhysicsDataContainer;
import org.schema.schine.network.server.ServerEntityWriterThread;

public class Universe
  implements class_80
{
  public static final int SECTOR_GENERATION_LENGTH = 5;
  private final HashMap starSystemMap = new HashMap();
  public static final float SECTOR_MARGIN = 300.0F;
  private String name;
  private final Int2ObjectOpenHashMap sectors = new Int2ObjectOpenHashMap();
  private final HashMap sectorPositions = new HashMap();
  private final class_1041 state;
  Vector3f tmpObjectPos = new Vector3f();
  class_48 belogingVector = new class_48();
  private static Random random;
  private final ObjectArrayFIFOQueue toClear = new ObjectArrayFIFOQueue();
  private ObjectOpenHashSet inactiveWrittenSectors = new ObjectOpenHashSet();
  private ObjectOpenHashSet entityCleaningSectors = new ObjectOpenHashSet();
  private final ArrayList physicsRepository = new ArrayList();
  private final class_48 where = new class_48();
  private static final int MAX_PHYSICS_REPOSITORY_SIZE = 30;
  private long lastPing;
  
  public static Random getRandom()
  {
    return random;
  }
  
  public static int getSectorSizeWithMargin()
  {
    return 1300;
  }
  
  private static String getVoidSystemFileName(class_48 paramclass_48)
  {
    return "VOIDSYSTEM_" + paramclass_48.field_475 + "_" + paramclass_48.field_476 + "_" + paramclass_48.field_477 + ".ssys";
  }
  
  public static Sendable loadEntity(class_1041 paramclass_1041, class_757 paramclass_757, class_670 paramclass_670)
  {
    if (paramclass_757.jdField_field_1017_of_type_Int == 3)
    {
      class_705 localclass_705;
      (localclass_705 = new class_705(paramclass_1041)).setId(paramclass_1041.getNextFreeObjectId());
      localclass_705.setSectorId(paramclass_670.a3());
      localclass_705.initialize();
      localclass_705.getMinPos().b1(paramclass_757.jdField_field_1018_of_type_Class_48);
      localclass_705.getMaxPos().b1(paramclass_757.jdField_field_1019_of_type_Class_48);
      localclass_705.a72(paramclass_757.jdField_field_1017_of_type_Boolean);
      localclass_705.setRealName(paramclass_757.field_1020);
      localclass_705.setFactionId(paramclass_757.jdField_field_1018_of_type_Int);
      localclass_705.setLastModified(paramclass_757.jdField_field_1018_of_type_JavaLangString);
      localclass_705.setSpawner(paramclass_757.jdField_field_1019_of_type_JavaLangString);
      localclass_705.setUniqueIdentifier(paramclass_757.jdField_field_1017_of_type_JavaLangString);
      localclass_705.setMass(0.0F);
      localclass_705.getRemoteTransformable().a7().setIdentity();
      localclass_705.getRemoteTransformable().a7().origin.set(paramclass_757.jdField_field_1017_of_type_JavaxVecmathVector3f);
      localclass_705.getRemoteTransformable().getWorldTransform().setIdentity();
      localclass_705.getRemoteTransformable().getWorldTransform().origin.set(paramclass_757.jdField_field_1017_of_type_JavaxVecmathVector3f);
      if (paramclass_757.jdField_field_1017_of_type_Long == 0L) {
        localclass_705.setSeed(getRandom().nextLong());
      } else {
        localclass_705.setSeed(paramclass_757.jdField_field_1017_of_type_Long);
      }
      paramclass_1041.a59().a4().addNewSynchronizedObjectQueued(localclass_705);
      return localclass_705;
    }
    if (!$assertionsDisabled) {
      throw new AssertionError(paramclass_757);
    }
    return null;
  }
  
  public static Sendable loadEntity(class_1041 paramclass_1041, String paramString, File paramFile, class_670 paramclass_670)
  {
    Object localObject;
    if (paramString.equals("SHIP"))
    {
      paramString = paramclass_1041.a59().a46(paramFile.getName(), "");
      (localObject = new class_747(paramclass_1041)).setId(paramclass_1041.getNextFreeObjectId());
      ((class_747)localObject).setSectorId(paramclass_670.a3());
      ((class_747)localObject).initialize();
      System.err.println("adding loaded object from disk: " + paramFile.getName() + " in sector " + paramclass_670.jdField_field_136_of_type_Class_48);
      ((class_747)localObject).fromTagStructure(paramString);
      System.err.println("added loaded object from disk: " + localObject + " from " + paramFile.getName());
      paramclass_1041.a59().a4().addNewSynchronizedObjectQueued((Sendable)localObject);
      return localObject;
    }
    if (paramString.equals("SPACEOBJECT"))
    {
      System.err.println("found existing playerstate! ");
      throw new IllegalArgumentException();
    }
    if (paramString.equals("FLOATINGROCK"))
    {
      paramString = paramclass_1041.a59().a46(paramFile.getName(), "");
      (localObject = new class_705(paramclass_1041)).setId(paramclass_1041.getNextFreeObjectId());
      ((class_705)localObject).setSectorId(paramclass_670.a3());
      ((class_705)localObject).initialize();
      ((class_705)localObject).fromTagStructure(paramString);
      paramclass_1041.a59().a4().addNewSynchronizedObjectQueued((Sendable)localObject);
      return localObject;
    }
    if (paramString.equals("SHOP"))
    {
      paramString = paramclass_1041.a59().a46(paramFile.getName(), "");
      (localObject = new class_743(paramclass_1041)).setId(paramclass_1041.getNextFreeObjectId());
      ((class_743)localObject).setSectorId(paramclass_670.a3());
      ((class_743)localObject).initialize();
      ((class_743)localObject).fromTagStructure(paramString);
      paramclass_1041.a59().a4().addNewSynchronizedObjectQueued((Sendable)localObject);
      return localObject;
    }
    if (paramString.equals("SPACESTATION"))
    {
      paramString = paramclass_1041.a59().a46(paramFile.getName(), "");
      (localObject = new class_737(paramclass_1041)).setId(paramclass_1041.getNextFreeObjectId());
      ((class_737)localObject).setSectorId(paramclass_670.a3());
      ((class_737)localObject).initialize();
      ((class_737)localObject).fromTagStructure(paramString);
      System.err.println("[UNIVERSE] adding loaded object from disk: " + localObject);
      paramclass_1041.a59().a4().addNewSynchronizedObjectQueued((Sendable)localObject);
      return localObject;
    }
    if (paramString.equals("PLANET"))
    {
      paramString = paramclass_1041.a59().a46(paramFile.getName(), "");
      (localObject = new class_864(paramclass_1041)).setId(paramclass_1041.getNextFreeObjectId());
      ((class_864)localObject).setSectorId(paramclass_670.a3());
      ((class_864)localObject).initialize();
      ((class_864)localObject).fromTagStructure(paramString);
      paramclass_1041.a59().a4().addNewSynchronizedObjectQueued((Sendable)localObject);
      return localObject;
    }
    if (paramString.equals("DEATHSTAR"))
    {
      paramString = paramclass_1041.a59().a46(paramFile.getName(), "");
      (localObject = new class_782(paramclass_1041)).setId(paramclass_1041.getNextFreeObjectId());
      ((class_782)localObject).setSectorId(paramclass_670.a3());
      ((class_782)localObject).initialize();
      ((class_782)localObject).fromTagStructure(paramString);
      paramclass_1041.a59().a4().addNewSynchronizedObjectQueued((Sendable)localObject);
      return localObject;
    }
    System.err.println("[LOADENTITY] WARNING: type not known: " + paramString);
    return null;
  }
  
  public Universe(class_1041 paramclass_1041, long paramLong)
  {
    this.state = paramclass_1041;
    random = new Random(System.currentTimeMillis());
  }
  
  private class_670 addNewSector(class_48 paramclass_48)
  {
    class_670 localclass_670;
    (localclass_670 = new class_670(this.state)).jdField_field_136_of_type_Class_48 = new class_48(paramclass_48);
    addSector(localclass_670);
    localclass_670.b11(this.state);
    return localclass_670;
  }
  
  public void addSector(class_670 paramclass_670)
  {
    assert (paramclass_670 != null);
    this.sectors.put(paramclass_670.a3(), paramclass_670);
    paramclass_670.f1();
    this.sectorPositions.put(paramclass_670.jdField_field_136_of_type_Class_48, paramclass_670);
  }
  
  public void addToClear(class_753 paramclass_753)
  {
    synchronized (this.toClear)
    {
      this.toClear.enqueue(paramclass_753);
      return;
    }
  }
  
  public void fromTagStructure(class_69 paramclass_69) {}
  
  public boolean getIsSectorActive(int paramInt)
  {
    return ((paramInt = (class_670)this.sectors.get(paramInt)) != null) && (paramInt.a7());
  }
  
  public boolean existsSector(int paramInt)
  {
    return (class_670)this.sectors.get(paramInt) != null;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public class_670 getSector(int paramInt)
  {
    return (class_670)this.sectors.get(paramInt);
  }
  
  public class_670 getSectorWithoutLoading(class_48 paramclass_48)
  {
    return (class_670)this.sectorPositions.get(paramclass_48);
  }
  
  public class_670 getSector(class_48 paramclass_48)
  {
    if (!this.sectorPositions.containsKey(paramclass_48)) {
      loadOrGenerateSector(paramclass_48);
    }
    class_670 localclass_670;
    if ((!(localclass_670 = (class_670)this.sectorPositions.get(paramclass_48)).a7()) && ((this.entityCleaningSectors.contains(localclass_670)) || (this.inactiveWrittenSectors.contains(localclass_670)))) {
      loadOrGenerateSector(paramclass_48);
    }
    if (!this.sectors.containsKey(localclass_670.a3()))
    {
      assert (localclass_670 != null);
      this.sectors.put(localclass_670.a3(), localclass_670);
    }
    assert (localclass_670 != null) : (paramclass_48 + " - " + this.sectorPositions);
    localclass_670.a72(true);
    return localclass_670;
  }
  
  public void updateProximitySectorInformation(class_48 paramclass_48)
  {
    class_48[] arrayOfclass_48;
    (arrayOfclass_48 = new class_48[8])[0] = new class_48(paramclass_48.field_475 + 8, paramclass_48.field_476 + 8, paramclass_48.field_477 + 8);
    arrayOfclass_48[1] = new class_48(paramclass_48.field_475 - 8, paramclass_48.field_476 + 8, paramclass_48.field_477 + 8);
    arrayOfclass_48[2] = new class_48(paramclass_48.field_475 + 8, paramclass_48.field_476 - 8, paramclass_48.field_477 + 8);
    arrayOfclass_48[3] = new class_48(paramclass_48.field_475 - 8, paramclass_48.field_476 - 8, paramclass_48.field_477 + 8);
    arrayOfclass_48[4] = new class_48(paramclass_48.field_475 + 8, paramclass_48.field_476 + 8, paramclass_48.field_477 - 8);
    arrayOfclass_48[5] = new class_48(paramclass_48.field_475 - 8, paramclass_48.field_476 + 8, paramclass_48.field_477 - 8);
    arrayOfclass_48[6] = new class_48(paramclass_48.field_475 + 8, paramclass_48.field_476 - 8, paramclass_48.field_477 - 8);
    arrayOfclass_48[7] = new class_48(paramclass_48.field_475 - 8, paramclass_48.field_476 - 8, paramclass_48.field_477 - 8);
    for (paramclass_48 = 0; paramclass_48 < arrayOfclass_48.length; paramclass_48++) {
      getStellarSystemFromSecPos(arrayOfclass_48[paramclass_48]);
    }
  }
  
  public Collection getSectorSet()
  {
    return this.sectors.values();
  }
  
  public class_791 getStellarSystemFromSecPos(class_48 paramclass_48)
  {
    paramclass_48 = class_789.a192(paramclass_48, new class_48());
    class_789 localclass_789;
    if ((localclass_789 = (class_789)this.starSystemMap.get(paramclass_48)) == null)
    {
      localclass_789 = loadOrGenerateVoidSystem(paramclass_48);
      this.starSystemMap.put(localclass_789.jdField_field_136_of_type_Class_48, localclass_789);
    }
    return localclass_789;
  }
  
  public class_48 getSectorBelonging(class_797 paramclass_797)
  {
    for (int i = 0; i < Element.DIRECTIONSi.length; i++) {
      paramclass_797.surround[i].b2(getSector(paramclass_797.getSectorId()).jdField_field_136_of_type_Class_48, Element.DIRECTIONSi[i]);
    }
    if (paramclass_797.isHidden()) {
      return getSector(paramclass_797.getSectorId()).jdField_field_136_of_type_Class_48;
    }
    if (((paramclass_797 instanceof SegmentController)) && (((SegmentController)paramclass_797).getDockingController().b3())) {
      return getSector(paramclass_797.getSectorId()).jdField_field_136_of_type_Class_48;
    }
    if (((paramclass_797 instanceof class_750)) && (((PairCachingGhostObjectAlignable)((class_750)paramclass_797).getPhysicsDataContainer().getObject()).getAttached() != null)) {
      return getSector(paramclass_797.getSectorId()).jdField_field_136_of_type_Class_48;
    }
    class_670 localclass_670;
    class_48 localclass_48 = (localclass_670 = getSector(paramclass_797.getSectorId())).jdField_field_136_of_type_Class_48;
    int j = -1;
    Vector3f localVector3f = new Vector3f(paramclass_797.getWorldTransform().origin);
    boolean bool = class_791.a19(localclass_670.jdField_field_136_of_type_Class_48);
    for (int k = 0; k < Element.DIRECTIONSi.length; k++)
    {
      Object localObject;
      (localObject = new class_48(Element.DIRECTIONSi[k])).a1(localclass_48);
      Transform localTransform;
      (localTransform = new Transform()).setIdentity();
      if (bool)
      {
        calcSecPos(localclass_48, (class_48)localObject, this.state.a59().calculateStartTime(), System.currentTimeMillis(), localTransform);
      }
      else
      {
        localTransform.origin.set(Element.DIRECTIONSi[k].field_475, Element.DIRECTIONSi[k].field_476, Element.DIRECTIONSi[k].field_477);
        localTransform.origin.scale(getSectorSizeWithMargin());
      }
      (localObject = new Vector3f()).sub(paramclass_797.getWorldTransform().origin, localTransform.origin);
      if (((Vector3f)localObject).lengthSquared() < localVector3f.lengthSquared())
      {
        localVector3f.set((Tuple3f)localObject);
        j = k;
      }
    }
    if (j >= 0)
    {
      this.belogingVector.b1(localclass_48);
      this.belogingVector.a1(Element.DIRECTIONSi[j]);
      this.state.a59().a45(paramclass_797, this.belogingVector, 0);
      return this.belogingVector;
    }
    return localclass_48;
  }
  
  public static void calcSunPosInnerStarSystem(class_48 paramclass_48, class_791 paramclass_791, long paramLong, Transform paramTransform)
  {
    paramLong = (float)((System.currentTimeMillis() - paramLong) % 1200000L) / 1200000.0F;
    paramTransform.basis.rotX(6.283186F * paramLong);
    paramTransform.origin.set((paramclass_791.jdField_field_136_of_type_Class_48.field_475 << 4) + 8 - paramclass_48.field_475, (paramclass_791.jdField_field_136_of_type_Class_48.field_476 << 4) + 8 - paramclass_48.field_476, (paramclass_791.jdField_field_136_of_type_Class_48.field_477 << 4) + 8 - paramclass_48.field_477);
    paramTransform.origin.scale(getSectorSizeWithMargin());
    paramTransform.basis.transform(paramTransform.origin);
  }
  
  public static void calcSecPos(class_48 paramclass_481, class_48 paramclass_482, long paramLong1, long paramLong2, Transform paramTransform)
  {
    class_48 localclass_481 = class_789.a192(paramclass_482, new class_48());
    class_48 localclass_482;
    (localclass_482 = new class_48()).a6(paramclass_482, paramclass_481);
    if (!class_791.a19(paramclass_481))
    {
      paramLong1 = 0L;
      paramLong2 = 0L;
    }
    paramclass_482 = (float)((paramLong2 - paramLong1) % 1200000L) / 1200000.0F;
    localclass_481.a5(16);
    localclass_481.a(8, 8, 8);
    localclass_481.c1(paramclass_481);
    paramclass_481 = new Vector3f();
    paramLong1 = new Vector3f();
    paramclass_481.set(localclass_482.field_475 * getSectorSizeWithMargin(), localclass_482.field_476 * getSectorSizeWithMargin(), localclass_482.field_477 * getSectorSizeWithMargin());
    paramLong1.set(localclass_481.field_475 * getSectorSizeWithMargin(), localclass_481.field_476 * getSectorSizeWithMargin(), localclass_481.field_477 * getSectorSizeWithMargin());
    paramTransform.setIdentity();
    if (paramLong1.lengthSquared() > 0.0F)
    {
      paramTransform.origin.add(paramLong1);
      paramTransform.basis.rotX(6.283186F * paramclass_482);
      (paramclass_482 = new Vector3f()).sub(paramclass_481, paramLong1);
      paramTransform.origin.add(paramclass_482);
      paramTransform.basis.transform(paramTransform.origin);
      return;
    }
    paramTransform.basis.rotX(6.283186F * paramclass_482);
    paramTransform.origin.set(paramclass_481);
    paramTransform.basis.transform(paramTransform.origin);
  }
  
  public class_791 getStellarSystemFromStellarPos(class_48 paramclass_48)
  {
    class_789 localclass_789;
    if ((localclass_789 = (class_789)this.starSystemMap.get(paramclass_48)) == null)
    {
      localclass_789 = loadOrGenerateVoidSystem(paramclass_48);
      this.starSystemMap.put(localclass_789.jdField_field_136_of_type_Class_48, localclass_789);
    }
    return localclass_789;
  }
  
  public String getUniqueIdentifier()
  {
    return this.name;
  }
  
  public boolean isEmpty()
  {
    return this.sectors.isEmpty();
  }
  
  public boolean isVolatile()
  {
    return false;
  }
  
  public boolean isSectorLoaded(class_48 paramclass_48)
  {
    return this.sectorPositions.containsKey(paramclass_48);
  }
  
  public void loadOrGenerateSector(class_48 paramclass_48)
  {
    class_48 localclass_48 = new class_48(paramclass_48);
    System.out.println("[SERVER][UNIVERSE] LOADING SECTOR... " + localclass_48);
    class_670 localclass_670 = new class_670(this.state);
    if (!this.state.a66().a28(paramclass_48, localclass_670))
    {
      (localclass_670 = addNewSector(localclass_48)).e2();
    }
    else
    {
      localclass_670.c7(this.state);
      addSector(localclass_670);
    }
    localclass_670.a72(true);
    localclass_670.a65(this.state);
  }
  
  private class_789 loadOrGenerateVoidSystem(class_48 paramclass_48)
  {
    class_789 localclass_789 = new class_789();
    if (this.state.a66().a24(paramclass_48, localclass_789))
    {
      if ((!$assertionsDisabled) && (localclass_789.field_139 < 0L)) {
        throw new AssertionError();
      }
    }
    else
    {
      localclass_789.jdField_field_136_of_type_Class_48.b1(paramclass_48);
      localclass_789.a194(random);
      try
      {
        long l = this.state.a66().a23(localclass_789, false);
        if (localclass_789.field_139 < 0L) {
          localclass_789.field_139 = l;
        }
      }
      catch (SQLException localSQLException)
      {
        localSQLException;
      }
    }
    return localclass_789;
  }
  
  private class_789 loadVoidSystemFromFile(String paramString, class_48 paramclass_48)
  {
    paramString = new File(paramString);
    class_789 localclass_789 = new class_789();
    if (paramString.exists())
    {
      paramclass_48 = class_69.a10(paramString = new DataInputStream(new BufferedInputStream(new FileInputStream(paramString))), true);
      paramString.close();
      localclass_789.fromTagStructure(paramclass_48);
    }
    else
    {
      localclass_789.jdField_field_136_of_type_Class_48.b1(paramclass_48);
      localclass_789.a194(random);
    }
    return localclass_789;
  }
  
  public void pingSectors()
  {
    Iterator localIterator = this.state.b10().values().iterator();
    while (localIterator.hasNext())
    {
      class_748 localclass_748 = (class_748)localIterator.next();
      pingSectorBlock(localclass_748.a44());
    }
  }
  
  private void pingSectorBlock(class_48 paramclass_48)
  {
    for (int i = paramclass_48.field_475 - 1; i <= paramclass_48.field_475 + 1; i++) {
      for (int j = paramclass_48.field_476 - 1; j <= paramclass_48.field_476 + 1; j++) {
        for (int k = paramclass_48.field_477 - 1; k <= paramclass_48.field_477 + 1; k++)
        {
          this.where.b(i, j, k);
          getSector(this.where).b4();
        }
      }
    }
  }
  
  public void resetAllSectors()
  {
    class_670 localclass_6701 = getSector(new class_48(class_670.field_139));
    class_670 localclass_6702;
    (localclass_6702 = new class_670(this.state)).a36(localclass_6701.a3());
    localclass_6702.jdField_field_136_of_type_Class_48 = new class_48(class_670.field_139);
    localclass_6702.b11(this.state);
    addSector(localclass_6702);
    localclass_6701.a13();
  }
  
  public void setName(String paramString)
  {
    this.name = paramString;
  }
  
  public class_69 toTagStructure()
  {
    return null;
  }
  
  public void update(class_941 paramclass_941)
  {
    if (System.currentTimeMillis() - this.lastPing > 500L)
    {
      l1 = System.currentTimeMillis();
      pingSectors();
      long l2;
      if ((l2 = System.currentTimeMillis() - l1) > 10L) {
        System.err.println("[UNIVERSE] WARNING: Sector Ping took " + l2);
      }
      this.lastPing = System.currentTimeMillis();
    }
    long l1 = System.currentTimeMillis();
    int i = ((Integer)class_1057.field_1324.a3()).intValue();
    this.sectors.int2ObjectEntrySet();
    long l3 = System.currentTimeMillis();
    class_1041.field_150 = 0;
    class_1041.field_151 = 0;
    Iterator localIterator2 = getSectorSet().iterator();
    while (localIterator2.hasNext())
    {
      class_670 localclass_6701;
      if (((localclass_6701 = (class_670)localIterator2.next()).a7()) && (i >= 0) && (l3 - localclass_6701.a28() > i * 1000))
      {
        int k = 0;
        Iterator localIterator3 = this.state.b10().values().iterator();
        while (localIterator3.hasNext()) {
          if (((class_748)localIterator3.next()).c2() == localclass_6701.a3())
          {
            k = 1;
            localclass_6701.b4();
          }
        }
        if (k == 0) {
          localclass_6701.a72(false);
        }
      }
      localclass_6701.a74(paramclass_941);
      if ((!localclass_6701.a7()) && (localclass_6701.a33(l3)) && (localclass_6701.c3()) && (!this.entityCleaningSectors.contains(localclass_6701))) {
        this.inactiveWrittenSectors.add(localclass_6701);
      }
    }
    class_1041.field_152 = class_1041.field_150;
    class_1041.field_153 = class_1041.field_151;
    long l4;
    if ((l4 = System.currentTimeMillis() - l1) > 30L) {
      System.err.println("[UNIVERSE] WARNING: Sector UPDATE took " + l4 + "; sectors updated: " + getSectorSet().size());
    }
    long l5 = System.currentTimeMillis();
    if (!this.toClear.isEmpty()) {
      synchronized (this.toClear)
      {
        if (!this.toClear.isEmpty())
        {
          (paramclass_941 = (class_753)this.toClear.dequeue()).getSegmentBuffer().d();
          paramclass_941.getSegmentBuffer().a1();
          paramclass_941.getSegmentProvider().f1();
        }
      }
    }
    int j = this.inactiveWrittenSectors.size();
    paramclass_941 = this.inactiveWrittenSectors.iterator();
    class_670 localclass_6702;
    Iterator localIterator1;
    while (paramclass_941.hasNext())
    {
      localObject1 = (class_670)paramclass_941.next();
      localclass_6702 = (class_670)this.sectorPositions.remove(((class_670)localObject1).jdField_field_136_of_type_Class_48);
      class_670 localclass_6703 = (class_670)this.sectors.remove(((class_670)localObject1).a3());
      System.err.println("[SECTOR][CLEANUP] removing entities and " + localObject1 + ": " + localclass_6702 + "; " + localclass_6703);
      assert ((localclass_6702 != null) && (localclass_6703 != null));
      localIterator1 = ((class_670)localObject1).a75().iterator();
      while (localIterator1.hasNext()) {
        ((class_797)localIterator1.next()).setMarkedForDeleteVolatile(true);
      }
      this.entityCleaningSectors.add(localObject1);
    }
    this.inactiveWrittenSectors.clear();
    paramclass_941 = this.entityCleaningSectors.size();
    Object localObject1 = this.entityCleaningSectors.iterator();
    while (((ObjectIterator)localObject1).hasNext())
    {
      localclass_6702 = (class_670)((ObjectIterator)localObject1).next();
      int m = 0;
      localIterator1 = localclass_6702.a75().iterator();
      while (localIterator1.hasNext())
      {
        class_797 localclass_797 = (class_797)localIterator1.next();
        if (this.state.getLocalAndRemoteObjectContainer().getLocalObjects().containsKey(localclass_797.getId()))
        {
          m = 1;
          break;
        }
      }
      if (m == 0)
      {
        localclass_6702.a13();
        localclass_6702.a78().setMarkedForDeleteVolatile(true);
        localclass_6702.jdField_field_136_of_type_Boolean = true;
        ((ObjectIterator)localObject1).remove();
      }
      else
      {
        System.err.println("[SERVER] waiting for sector " + localclass_6702 + " entities to be cleaned up");
      }
    }
    long l6;
    if ((l6 = System.currentTimeMillis() - l5) > 5L) {
      System.err.println("[UNIVERSE] WARNING SECTOR CLEANUP TIME: " + l6 + "; QUEUE: " + paramclass_941 + "; InactSectors: " + j);
    }
  }
  
  public void writeStarSystems()
  {
    Iterator localIterator = this.starSystemMap.values().iterator();
    while (localIterator.hasNext()) {
      localIterator.next();
    }
  }
  
  private void write(class_80 paramclass_80, String paramString)
  {
    Object localObject1 = null;
    synchronized (this.state.jdField_field_144_of_type_JavaUtilHashMap)
    {
      if ((localObject1 = this.state.jdField_field_144_of_type_JavaUtilHashMap.get(paramString)) == null)
      {
        localObject1 = new Object();
        this.state.jdField_field_144_of_type_JavaUtilHashMap.put(paramString, localObject1);
      }
    }
    if ((??? = new File("./tmp/" + paramString + ".tmp")).exists())
    {
      System.err.println("Exception: tried parallel write: " + ((File)???).getName());
      return;
    }
    DataOutputStream localDataOutputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream((File)???), 1024));
    class_69 localclass_69 = paramclass_80.toTagStructure();
    try
    {
      localclass_69.a11(localDataOutputStream, true);
      localDataOutputStream.close();
    }
    catch (RuntimeException localRuntimeException)
    {
      System.err.println("Exception during write of: " + paramclass_80);
      throw localRuntimeException;
    }
    synchronized (localRuntimeException)
    {
      paramclass_80 = new File(class_1041.jdField_field_144_of_type_JavaLangString + paramString);
      paramString = new File(class_1041.jdField_field_144_of_type_JavaLangString + paramString + ".old");
      if (paramclass_80.exists())
      {
        if (paramString.exists())
        {
          System.err.println("Exception: tried parallel write off OLD: " + paramString.getName());
          paramString.delete();
        }
        paramclass_80.renameTo(paramString);
        paramclass_80.delete();
      }
      ((File)???).renameTo(paramclass_80);
      if (paramString.exists()) {
        paramString.delete();
      }
      return;
    }
  }
  
  public void writeToDatabase(boolean paramBoolean1, boolean paramBoolean2)
  {
    writeFactionsAndCatalog();
    Iterator localIterator = this.sectors.values().iterator();
    while (localIterator.hasNext())
    {
      class_670 localclass_670;
      if (((localclass_670 = (class_670)localIterator.next()).a7()) || (!localclass_670.c3())) {
        if (paramBoolean1) {
          localclass_670.a76(1, this);
        } else if (paramBoolean2) {
          localclass_670.a76(2, this);
        } else {
          localclass_670.a76(0, this);
        }
      }
    }
    writeStarSystems();
    writeSimulationState();
    if (paramBoolean1) {
      try
      {
        this.state.getThreadPool().shutdown();
        System.out.println("[SERVER] WAITING FOR THREAD POOL TO TERMINATE; Active: " + this.state.getThreadPool().getActiveCount());
        this.state.getThreadPool().awaitTermination(20L, TimeUnit.MINUTES);
        System.out.println("[SERVER] SERVER THREAD POOL TERMINATED");
        System.out.println("[SERVER] WAITING FOR WRITING QUEUE TO FINISH; Active: " + this.state.getThreadQueue().getActiveCount());
        while (this.state.getThreadQueue().getActiveCount() > 0) {
          Thread.sleep(300L);
        }
        System.out.println("[SERVER] SERVER THREAD QUEUE TERMINATED");
        return;
      }
      catch (InterruptedException localInterruptedException)
      {
        localInterruptedException;
      }
    }
  }
  
  private void writeSimulationState()
  {
    this.state.a69().c1();
    write(this.state.a69(), this.state.a69().getUniqueIdentifier() + ".sim");
  }
  
  private void writeFactionsAndCatalog()
  {
    if ((this.state != null) && (this.state.a12() != null))
    {
      System.err.println("[SERVER] WRITING FACTIONS AND CATALOG!!! Count: " + this.state.a12().a51().a101().size());
      write(this.state.a12().a51(), "FACTIONS.fac");
      write(this.state.a12().a53(), "CATALOG.cat");
    }
  }
  
  public void addToFreePhysics(class_1419 paramclass_1419, class_670 paramclass_670)
  {
    int i = 0;
    ((PhysicsExt)paramclass_1419).setState(null);
    ((PhysicsExt)paramclass_1419).getDynamicsWorld().setInternalTickCallback(null, null);
    synchronized (getPhysicsRepository())
    {
      if (((i = getPhysicsRepository().size() > 30 ? 1 : 0) == 0) && (!getPhysicsRepository().contains(paramclass_1419)))
      {
        System.err.println("[SERVER] physics for " + this + ": " + paramclass_1419 + " has been added to repository");
        getPhysicsRepository().add(paramclass_1419);
        paramclass_1419.softClean();
      }
    }
    if (i != 0)
    {
      System.out.println("Cleaned up Physics, because repository is full");
      paramclass_1419.cleanUp();
      paramclass_670.g();
      paramclass_670.jdField_field_136_of_type_JavaUtilSet = null;
    }
  }
  
  public class_1419 getPhysicsInstance(class_1407 paramclass_1407)
  {
    synchronized (getPhysicsRepository())
    {
      if (getPhysicsRepository().size() > 0)
      {
        PhysicsExt localPhysicsExt;
        (localPhysicsExt = (PhysicsExt)getPhysicsRepository().remove(0)).setState(paramclass_1407);
        System.err.println("[SERVER] physics for " + this + ": " + localPhysicsExt + " has been fetched repository");
        return localPhysicsExt;
      }
    }
    return new PhysicsExt(paramclass_1407);
  }
  
  public ArrayList getPhysicsRepository()
  {
    return this.physicsRepository;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.world.Universe
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */