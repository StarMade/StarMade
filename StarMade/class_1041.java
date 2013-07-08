import com.bulletphysics.linearmath.Transform;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.objects.ObjectArrayFIFOQueue;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import org.lwjgl.BufferUtils;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.database.DatabaseIndex;
import org.schema.game.common.data.world.Universe;
import org.schema.game.network.objects.NetworkGameState;
import org.schema.game.server.controller.GameServerController;
import org.schema.game.server.controller.NoIPException;
import org.schema.game.server.data.PlayerNotFountException;
import org.schema.schine.network.ChatSystem;
import org.schema.schine.network.ClientIdNotFoundException;
import org.schema.schine.network.NetworkProcessor;
import org.schema.schine.network.RegisteredClientOnServer;
import org.schema.schine.network.objects.Sendable;
import org.schema.schine.network.objects.remote.RemoteFloat;
import org.schema.schine.network.server.ServerState;

public final class class_1041
  extends ServerState
  implements class_361, class_667, class_1072, class_1039
{
  private static final String jdField_field_150_of_type_JavaLangString;
  public static final String field_144;
  public static final String field_145;
  public static final String field_146;
  public static final String field_147;
  public static final String field_148;
  public static final String field_149;
  public static final byte[] field_144;
  public static Deflater field_144;
  public final IntOpenHashSet field_144;
  public static Inflater field_144;
  public static int field_144;
  public static int field_145;
  public static int field_146;
  public static boolean field_144;
  public static int field_147;
  public static int field_148;
  public static int field_149;
  public static class_1041 field_144;
  private static byte[] jdField_field_145_of_type_ArrayOfByte;
  public static int field_150;
  public static int field_151;
  public static int field_152;
  public static int field_153;
  public final HashMap field_144;
  private final ObjectArrayList jdField_field_144_of_type_ItUnimiDsiFastutilObjectsObjectArrayList = new ObjectArrayList();
  private final HashSet jdField_field_144_of_type_JavaUtilHashSet = new HashSet();
  private final HashMap jdField_field_145_of_type_JavaUtilHashMap = new HashMap();
  private final Universe jdField_field_144_of_type_OrgSchemaGameCommonDataWorldUniverse;
  private ChatSystem jdField_field_144_of_type_OrgSchemaSchineNetworkChatSystem;
  private final ArrayList jdField_field_145_of_type_JavaUtilArrayList = new ArrayList();
  private final ArrayList jdField_field_146_of_type_JavaUtilArrayList = new ArrayList();
  private final ArrayList jdField_field_147_of_type_JavaUtilArrayList = new ArrayList();
  private final ArrayList jdField_field_148_of_type_JavaUtilArrayList = new ArrayList();
  private final ArrayList jdField_field_149_of_type_JavaUtilArrayList = new ArrayList();
  private final ArrayList jdField_field_150_of_type_JavaUtilArrayList = new ArrayList();
  private class_876 jdField_field_144_of_type_Class_876;
  private final class_1098 jdField_field_144_of_type_Class_1098;
  private final class_729 jdField_field_144_of_type_Class_729 = new class_729("SERVER");
  private final ObjectArrayFIFOQueue jdField_field_145_of_type_ItUnimiDsiFastutilObjectsObjectArrayFIFOQueue = new ObjectArrayFIFOQueue();
  private class_900 jdField_field_144_of_type_Class_900;
  private final HashMap jdField_field_146_of_type_JavaUtilHashMap = new HashMap();
  private final HashMap jdField_field_147_of_type_JavaUtilHashMap = new HashMap();
  private final HashMap jdField_field_148_of_type_JavaUtilHashMap = new HashMap();
  private ArrayList jdField_field_151_of_type_JavaUtilArrayList = new ArrayList();
  private final ArrayList jdField_field_152_of_type_JavaUtilArrayList = new ArrayList();
  private HashSet jdField_field_145_of_type_JavaUtilHashSet = new HashSet();
  private HashSet jdField_field_146_of_type_JavaUtilHashSet = new HashSet();
  private HashSet jdField_field_147_of_type_JavaUtilHashSet = new HashSet();
  private final HashSet jdField_field_148_of_type_JavaUtilHashSet = new HashSet();
  private final HashSet jdField_field_149_of_type_JavaUtilHashSet = new HashSet();
  private final HashSet jdField_field_150_of_type_JavaUtilHashSet = new HashSet();
  private final HashSet jdField_field_151_of_type_JavaUtilHashSet = new HashSet();
  private byte[] jdField_field_146_of_type_ArrayOfByte = new byte[1048576];
  private ByteBuffer jdField_field_144_of_type_JavaNioByteBuffer = BufferUtils.createByteBuffer(1048576);
  private class_800 jdField_field_144_of_type_Class_800;
  private final HashSet jdField_field_152_of_type_JavaUtilHashSet = new HashSet();
  private long jdField_field_144_of_type_Long = System.currentTimeMillis();
  private long jdField_field_145_of_type_Long;
  private long jdField_field_146_of_type_Long;
  private int field_154;
  private boolean jdField_field_145_of_type_Boolean;
  private final ArrayList field_153 = new ArrayList();
  private final DatabaseIndex jdField_field_144_of_type_OrgSchemaGameCommonControllerDatabaseDatabaseIndex;
  private final class_1037 jdField_field_144_of_type_Class_1037;
  private final class_1220 jdField_field_144_of_type_Class_1220;
  public ObjectArrayFIFOQueue field_144;
  private final ObjectArrayList jdField_field_145_of_type_ItUnimiDsiFastutilObjectsObjectArrayList = new ObjectArrayList();
  public final ArrayList field_144;
  private String jdField_field_151_of_type_JavaLangString;
  private String jdField_field_152_of_type_JavaLangString;
  private byte[] jdField_field_147_of_type_ArrayOfByte;
  private byte[] jdField_field_148_of_type_ArrayOfByte;
  
  public class_1041()
  {
    this.jdField_field_144_of_type_ItUnimiDsiFastutilIntsIntOpenHashSet = new IntOpenHashSet();
    this.jdField_field_144_of_type_JavaUtilHashMap = new HashMap();
    this.jdField_field_144_of_type_JavaUtilArrayList = new ArrayList();
    class_1057.a1();
    try
    {
      class_1057.b1();
    }
    catch (IOException localIOException1)
    {
      localIOException1;
    }
    this.jdField_field_144_of_type_Class_1037 = new class_1037(this, (byte)0);
    this.jdField_field_144_of_type_Class_1037.setPriority(3);
    this.jdField_field_144_of_type_Class_1037.start();
    this.jdField_field_144_of_type_Class_900 = class_900.field_1127;
    this.jdField_field_144_of_type_Class_876 = new class_876(this);
    this.jdField_field_144_of_type_Class_1220 = new class_1220(this);
    this.jdField_field_144_of_type_OrgSchemaGameCommonDataWorldUniverse = new Universe(this, 3465436L);
    boolean bool = DatabaseIndex.a1();
    this.jdField_field_144_of_type_OrgSchemaGameCommonControllerDatabaseDatabaseIndex = new DatabaseIndex();
    if (!bool)
    {
      this.jdField_field_144_of_type_OrgSchemaGameCommonControllerDatabaseDatabaseIndex.a4();
    }
    else
    {
      this.jdField_field_144_of_type_OrgSchemaGameCommonControllerDatabaseDatabaseIndex.c();
      try
      {
        this.jdField_field_144_of_type_OrgSchemaGameCommonControllerDatabaseDatabaseIndex.b();
      }
      catch (IOException localIOException2)
      {
        localIOException2;
      }
    }
    this.jdField_field_144_of_type_Class_1098 = new class_1098(this);
    jdField_field_144_of_type_Class_1041 = this;
  }
  
  public final void chat(ChatSystem paramChatSystem, String paramString1, String paramString2, boolean paramBoolean)
  {
    this.jdField_field_148_of_type_JavaUtilArrayList.add(paramChatSystem.getOwnerStateId() + ": " + paramString1);
  }
  
  public final ArrayList a10()
  {
    return this.jdField_field_151_of_type_JavaUtilArrayList;
  }
  
  public final HashSet a54()
  {
    return this.jdField_field_144_of_type_JavaUtilHashSet;
  }
  
  public final HashMap a58()
  {
    return this.jdField_field_145_of_type_JavaUtilHashMap;
  }
  
  public final HashSet b8()
  {
    return this.jdField_field_148_of_type_JavaUtilHashSet;
  }
  
  public final HashSet c6()
  {
    return this.jdField_field_150_of_type_JavaUtilHashSet;
  }
  
  public final ArrayList b()
  {
    return this.jdField_field_152_of_type_JavaUtilArrayList;
  }
  
  public final ChatSystem getChat()
  {
    return this.jdField_field_144_of_type_OrgSchemaSchineNetworkChatSystem;
  }
  
  public final String[] getCommandPrefixes()
  {
    return null;
  }
  
  public final GameServerController a59()
  {
    return (GameServerController)super.getController();
  }
  
  public final byte[] getDataBuffer()
  {
    return this.jdField_field_146_of_type_ArrayOfByte;
  }
  
  public final ArrayList c()
  {
    return this.jdField_field_147_of_type_JavaUtilArrayList;
  }
  
  public final class_900 a11()
  {
    return this.jdField_field_144_of_type_Class_900;
  }
  
  public final class_800 a12()
  {
    return this.jdField_field_144_of_type_Class_800;
  }
  
  public final float getMaxMsBetweenUpdates()
  {
    return 100.0F;
  }
  
  public final class_748 a60(String paramString)
  {
    class_748 localclass_748;
    if ((localclass_748 = (class_748)this.jdField_field_147_of_type_JavaUtilHashMap.get(paramString)) != null) {
      return localclass_748;
    }
    throw new PlayerNotFountException(paramString);
  }
  
  public final class_748 a61(int paramInt)
  {
    class_748 localclass_748;
    if ((localclass_748 = (class_748)this.jdField_field_148_of_type_JavaUtilHashMap.get(Integer.valueOf(paramInt))) != null) {
      return localclass_748;
    }
    throw new PlayerNotFountException("CLIENT-ID(" + paramInt + ") ");
  }
  
  public final NetworkProcessor getProcessor(int paramInt)
  {
    return ((RegisteredClientOnServer)getClients().get(Integer.valueOf(paramInt))).getProcessor();
  }
  
  public final ArrayList d()
  {
    return this.jdField_field_149_of_type_JavaUtilArrayList;
  }
  
  public final class_876 a24()
  {
    return this.jdField_field_144_of_type_Class_876;
  }
  
  public final ArrayList e2()
  {
    return this.jdField_field_150_of_type_JavaUtilArrayList;
  }
  
  public final HashSet d4()
  {
    return this.jdField_field_152_of_type_JavaUtilHashSet;
  }
  
  public final class_729 a26()
  {
    return this.jdField_field_144_of_type_Class_729;
  }
  
  public final Universe a62()
  {
    return this.jdField_field_144_of_type_OrgSchemaGameCommonDataWorldUniverse;
  }
  
  public final float getVersion()
  {
    return class_1266.field_1446;
  }
  
  public final void a()
  {
    long l1 = System.currentTimeMillis();
    Sendable localSendable3;
    class_1041 localclass_1041;
    if (!this.jdField_field_145_of_type_JavaUtilHashSet.isEmpty())
    {
      HashSet localHashSet1 = new HashSet(this.jdField_field_145_of_type_JavaUtilHashSet.size());
      synchronized (this.jdField_field_145_of_type_JavaUtilHashSet)
      {
        localHashSet1.addAll(this.jdField_field_145_of_type_JavaUtilHashSet);
      }
      ??? = localObject2.iterator();
      while (((Iterator)???).hasNext())
      {
        Sendable localSendable2 = (Sendable)((Iterator)???).next();
        localSendable3 = localSendable2;
        localclass_1041 = this;
        if ((localSendable3 instanceof class_748))
        {
          localclass_1041.jdField_field_147_of_type_JavaUtilHashMap.put(((class_748)localSendable3).getName(), (class_748)localSendable3);
          localclass_1041.jdField_field_148_of_type_JavaUtilHashMap.put(Integer.valueOf(((class_748)localSendable3).a3()), (class_748)localSendable3);
          localclass_1041.jdField_field_144_of_type_Class_1098.a216().b((class_748)localSendable3);
        }
        if ((localSendable3 instanceof SegmentController)) {
          localclass_1041.jdField_field_146_of_type_JavaUtilHashMap.put(((SegmentController)localSendable3).getUniqueIdentifier(), (SegmentController)localSendable3);
        }
        if (((localSendable3 instanceof class_797)) && (((class_797)localSendable3).isGravitySource())) {
          localclass_1041.jdField_field_144_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.add((class_797)localSendable3);
        }
        class_753 localclass_753;
        if (((localSendable2 instanceof class_749)) && ((localclass_753 = ((class_749)localSendable2).a48()) != null))
        {
          localclass_753.setServerSendableSegmentController((class_749)localSendable2);
          ((class_749)localSendable2).a2();
        }
        setChanged();
        notifyObservers(localSendable2);
      }
      this.jdField_field_145_of_type_JavaUtilHashSet.clear();
    }
    long l2;
    if ((l2 = System.currentTimeMillis() - l1) > 10L) {
      System.err.println("[SERVER][UPDATE] WARNING: handleAddedAndRemovedObjects update took " + l2);
    }
    if (!this.jdField_field_146_of_type_JavaUtilHashSet.isEmpty())
    {
      HashSet localHashSet2 = new HashSet(this.jdField_field_146_of_type_JavaUtilHashSet.size());
      synchronized (this.jdField_field_146_of_type_JavaUtilHashSet)
      {
        localHashSet2.addAll(this.jdField_field_146_of_type_JavaUtilHashSet);
      }
      ??? = localHashSet2.iterator();
      while (((Iterator)???).hasNext())
      {
        Sendable localSendable1;
        if (((localSendable1 = (Sendable)((Iterator)???).next()) instanceof class_80)) {
          ((GameServerController)super.getController()).a51((class_80)localSendable1, true);
        }
        localSendable3 = localSendable1;
        localclass_1041 = this;
        if ((localSendable3 instanceof class_748))
        {
          localclass_1041.jdField_field_147_of_type_JavaUtilHashMap.remove(((class_748)localSendable3).getName());
          localclass_1041.jdField_field_148_of_type_JavaUtilHashMap.remove(Integer.valueOf(((class_748)localSendable3).a3()));
          localclass_1041.jdField_field_144_of_type_Class_1098.a216().a2((class_748)localSendable3);
        }
        if ((localSendable3 instanceof SegmentController)) {
          localclass_1041.jdField_field_146_of_type_JavaUtilHashMap.remove(((SegmentController)localSendable3).getUniqueIdentifier());
        }
        if (((localSendable3 instanceof class_797)) && (((class_797)localSendable3).isGravitySource())) {
          localclass_1041.jdField_field_144_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.remove(localSendable3);
        }
        if (localSendable3.isMarkedForPermanentDelete())
        {
          if ((localSendable3 instanceof class_80)) {
            localclass_1041.jdField_field_144_of_type_Class_1098.a106(((class_80)localSendable3).getUniqueIdentifier());
          }
          localSendable3.destroyPersistent();
        }
        setChanged();
        notifyObservers(localSendable1);
      }
      this.jdField_field_146_of_type_JavaUtilHashSet.clear();
    }
  }
  
  public final void b1()
  {
    long l1 = System.currentTimeMillis();
    if (!this.jdField_field_147_of_type_JavaUtilHashSet.isEmpty())
    {
      HashSet localHashSet = new HashSet();
      synchronized (this.jdField_field_147_of_type_JavaUtilHashSet)
      {
        localHashSet.addAll(this.jdField_field_147_of_type_JavaUtilHashSet);
      }
      ??? = localObject1.iterator();
      while (((Iterator)???).hasNext())
      {
        Sendable localSendable = (Sendable)((Iterator)???).next();
        setChanged();
        notifyObservers(localSendable);
      }
      this.jdField_field_147_of_type_JavaUtilHashSet.clear();
    }
    long l2;
    if ((l2 = System.currentTimeMillis() - l1) > 10L) {
      System.err.println("[SERVER][UPDATE] WARNING: needsNotifyObjects update took " + l2);
    }
  }
  
  public final void notifyOfAddedObject(Sendable paramSendable)
  {
    SegmentController localSegmentController;
    if (((paramSendable instanceof SegmentController)) && ((localSegmentController = (SegmentController)paramSendable).getCreatorThread() == null)) {
      localSegmentController.startCreatorThread();
    }
    synchronized (this.jdField_field_145_of_type_JavaUtilHashSet)
    {
      this.jdField_field_145_of_type_JavaUtilHashSet.add(paramSendable);
      return;
    }
  }
  
  public final void notifyOfRemovedObject(Sendable paramSendable)
  {
    synchronized (this.jdField_field_146_of_type_JavaUtilHashSet)
    {
      this.jdField_field_146_of_type_JavaUtilHashSet.add(paramSendable);
      return;
    }
  }
  
  public final String onAutoComplete(String paramString1, class_1079 paramclass_1079, String paramString2)
  {
    System.err.println("NO AUTOCOMPLETE ON SERVER");
    return paramString1;
  }
  
  public final void onStringCommand(String paramString1, class_1079 paramclass_1079, String paramString2)
  {
    throw new IllegalArgumentException();
  }
  
  public final void a30(ChatSystem paramChatSystem)
  {
    this.jdField_field_144_of_type_OrgSchemaSchineNetworkChatSystem = paramChatSystem;
  }
  
  public final void a36(class_800 paramclass_800)
  {
    this.jdField_field_144_of_type_Class_800 = paramclass_800;
  }
  
  public final void a63(int paramInt1, String paramString, int paramInt2, Transform paramTransform, int paramInt3, class_1216 paramclass_1216)
  {
    this.jdField_field_144_of_type_Class_1037.a(paramInt1, paramString, paramInt2, paramTransform, paramInt3, paramclass_1216);
  }
  
  public final long a18()
  {
    return this.jdField_field_144_of_type_Long;
  }
  
  public final long b9()
  {
    return this.jdField_field_145_of_type_Long;
  }
  
  public final void a41(long paramLong)
  {
    this.jdField_field_145_of_type_Long = paramLong;
  }
  
  public final int getClientIdByName(String paramString)
  {
    try
    {
      return a60(paramString).a3();
    }
    catch (PlayerNotFountException localPlayerNotFountException)
    {
      throw new ClientIdNotFoundException(paramString);
    }
  }
  
  public final void a34(int paramInt)
  {
    if (paramInt >= 0)
    {
      this.jdField_field_146_of_type_Long = System.currentTimeMillis();
      this.field_154 = paramInt;
      return;
    }
    this.jdField_field_146_of_type_Long = -1L;
    this.jdField_field_144_of_type_Class_800.a52().serverShutdown.set(Float.valueOf(-1.0F));
  }
  
  public final long c7()
  {
    return this.jdField_field_146_of_type_Long;
  }
  
  public final int a8()
  {
    return this.field_154;
  }
  
  public final void a64(class_45 paramclass_45, String paramString)
  {
    synchronized (this.jdField_field_146_of_type_JavaUtilArrayList)
    {
      this.jdField_field_146_of_type_JavaUtilArrayList.add(new class_1035(paramclass_45, paramString));
      return;
    }
  }
  
  public final ArrayList f2()
  {
    return this.jdField_field_146_of_type_JavaUtilArrayList;
  }
  
  public final ArrayList g2()
  {
    return this.jdField_field_145_of_type_JavaUtilArrayList;
  }
  
  public final class_765 a45()
  {
    return this.jdField_field_144_of_type_Class_800.a51();
  }
  
  public final HashMap b10()
  {
    return this.jdField_field_147_of_type_JavaUtilHashMap;
  }
  
  public final boolean onChatTextEnterHook(ChatSystem paramChatSystem, String paramString, boolean paramBoolean)
  {
    return false;
  }
  
  public final boolean a28()
  {
    return this.jdField_field_145_of_type_Boolean;
  }
  
  public final void a33(boolean paramBoolean)
  {
    this.jdField_field_145_of_type_Boolean = paramBoolean;
  }
  
  public final void a65(Sendable paramSendable)
  {
    synchronized (this.field_153)
    {
      this.field_153.add(paramSendable);
      return;
    }
  }
  
  public final ArrayList h1()
  {
    return this.field_153;
  }
  
  public final class_738 a53()
  {
    return this.jdField_field_144_of_type_Class_800.a53();
  }
  
  public final HashMap c8()
  {
    return this.jdField_field_146_of_type_JavaUtilHashMap;
  }
  
  public final ByteBuffer getDataByteBuffer()
  {
    return this.jdField_field_144_of_type_JavaNioByteBuffer;
  }
  
  public final DatabaseIndex a66()
  {
    return this.jdField_field_144_of_type_OrgSchemaGameCommonControllerDatabaseDatabaseIndex;
  }
  
  public final void needsNotify(Sendable paramSendable)
  {
    synchronized (this.jdField_field_147_of_type_JavaUtilHashSet)
    {
      this.jdField_field_147_of_type_JavaUtilHashSet.add(paramSendable);
      return;
    }
  }
  
  public static synchronized int b3()
  {
    return jdField_field_148_of_type_Int++;
  }
  
  public final class_1220 a67()
  {
    return this.jdField_field_144_of_type_Class_1220;
  }
  
  public final boolean a68(String paramString)
  {
    return (this.jdField_field_144_of_type_JavaUtilHashSet.isEmpty()) || (this.jdField_field_144_of_type_JavaUtilHashSet.contains(paramString));
  }
  
  public static byte[] a55(int paramInt)
  {
    if (jdField_field_145_of_type_ArrayOfByte.length < paramInt)
    {
      int i = jdField_field_145_of_type_ArrayOfByte.length;
      while (i < paramInt) {
        i <<= 1;
      }
      jdField_field_145_of_type_ArrayOfByte = new byte[i];
    }
    return jdField_field_145_of_type_ArrayOfByte;
  }
  
  public final int getMaxClients()
  {
    return ((Integer)class_1057.field_1337.a3()).intValue();
  }
  
  public final long getStartTime()
  {
    return this.jdField_field_144_of_type_Long;
  }
  
  public final String getServerName()
  {
    return this.jdField_field_144_of_type_Class_800.b10();
  }
  
  public final String getServerDesc()
  {
    return this.jdField_field_144_of_type_Class_800.a55();
  }
  
  public final String executeAdminCommand(String paramString1, String paramString2)
  {
    if (!class_1057.field_1338.a4()) {
      return "ERROR: super admin not enabled on this server";
    }
    if ((paramString1 != null) && (paramString1.equals(class_1057.field_1339.a3()))) {
      try
      {
        if ((paramString1 = paramString2.split(" "))[0].equals("shutdown")) {
          a34(Integer.parseInt(paramString1[1]));
        }
        if (paramString1[0].equals("chat"))
        {
          paramString1 = paramString2.split(" ", 2);
          ((GameServerController)super.getController()).broadcastMessage(paramString1[1], 0);
        }
        if (paramString1[0].equals("warn"))
        {
          paramString1 = paramString2.split(" ", 2);
          ((GameServerController)super.getController()).broadcastMessage(paramString1[1], 2);
        }
        return "SUCCESS: Command executed";
      }
      catch (Exception localException)
      {
        (paramString1 = localException).printStackTrace();
        return "ERROR: Failed to execute command " + paramString1.getClass().getSimpleName() + ": " + paramString1.getMessage();
      }
    }
    return "ERROR: incorrect server password";
  }
  
  public final class_1098 a69()
  {
    return this.jdField_field_144_of_type_Class_1098;
  }
  
  public final ObjectArrayList b11()
  {
    return this.jdField_field_145_of_type_ItUnimiDsiFastutilObjectsObjectArrayList;
  }
  
  public final void a70(class_48 paramclass_48, RegisteredClientOnServer paramRegisteredClientOnServer, String paramString)
  {
    paramclass_48 = new class_1027(paramclass_48, paramRegisteredClientOnServer, paramString);
    a72(paramclass_48);
  }
  
  public final void b12(class_48 paramclass_48, RegisteredClientOnServer paramRegisteredClientOnServer, String paramString)
  {
    paramclass_48 = new class_1029(paramclass_48, paramRegisteredClientOnServer, paramString);
    a72(paramclass_48);
  }
  
  public final void a71(RegisteredClientOnServer paramRegisteredClientOnServer, String paramString)
  {
    paramRegisteredClientOnServer = new class_1033(paramRegisteredClientOnServer, paramString, false);
    a72(paramRegisteredClientOnServer);
  }
  
  public final void b13(RegisteredClientOnServer paramRegisteredClientOnServer, String paramString)
  {
    paramRegisteredClientOnServer = new class_1033(paramRegisteredClientOnServer, paramString, true);
    a72(paramRegisteredClientOnServer);
  }
  
  private void a72(class_1051 paramclass_1051)
  {
    synchronized (this.jdField_field_145_of_type_ItUnimiDsiFastutilObjectsObjectArrayFIFOQueue)
    {
      this.jdField_field_145_of_type_ItUnimiDsiFastutilObjectsObjectArrayFIFOQueue.enqueue(paramclass_1051);
      return;
    }
  }
  
  public final ObjectArrayFIFOQueue a73()
  {
    return this.jdField_field_145_of_type_ItUnimiDsiFastutilObjectsObjectArrayFIFOQueue;
  }
  
  public final int getSocketBufferSize()
  {
    return ((Integer)class_1057.field_1341.a3()).intValue();
  }
  
  public final String getAcceptingIP()
  {
    return (String)class_1057.field_1340.a3();
  }
  
  public final ObjectArrayList a56()
  {
    return this.jdField_field_144_of_type_ItUnimiDsiFastutilObjectsObjectArrayList;
  }
  
  public final HashSet e4()
  {
    return this.jdField_field_149_of_type_JavaUtilHashSet;
  }
  
  public final HashSet f3()
  {
    return this.jdField_field_151_of_type_JavaUtilHashSet;
  }
  
  public final boolean filterJoinMessages()
  {
    return class_1057.field_1349.a4();
  }
  
  public final void a57(String paramString)
  {
    this.jdField_field_151_of_type_JavaLangString = paramString;
  }
  
  public final void b7(String paramString)
  {
    this.jdField_field_152_of_type_JavaLangString = paramString;
  }
  
  public final String a51()
  {
    return this.jdField_field_151_of_type_JavaLangString;
  }
  
  public final String b5()
  {
    return this.jdField_field_152_of_type_JavaLangString;
  }
  
  public final byte[] a74()
  {
    return this.jdField_field_147_of_type_ArrayOfByte;
  }
  
  public final byte[] b14()
  {
    return this.jdField_field_148_of_type_ArrayOfByte;
  }
  
  public final void a75(byte[] paramArrayOfByte)
  {
    this.jdField_field_147_of_type_ArrayOfByte = paramArrayOfByte;
  }
  
  public final void b15(byte[] paramArrayOfByte)
  {
    this.jdField_field_148_of_type_ArrayOfByte = paramArrayOfByte;
  }
  
  public final boolean useUDP()
  {
    return class_1057.field_1350.a4();
  }
  
  public final void a76(class_1074 paramclass_1074, String paramString)
  {
    try
    {
      if ((paramString = a60(paramString)) != null)
      {
        if (class_1057.field_1352.a4())
        {
          System.err.println("[SERVER] banning name for modified blueprint use " + paramString.getName());
          ((GameServerController)super.getController()).d2(paramString.getName());
        }
        if ((class_1057.field_1353.a4()) && (paramString.a() != null))
        {
          System.err.println("[SERVER] banning IP for modified blueprint use " + paramString.a());
          try
          {
            ((GameServerController)super.getController()).c2(paramString.a());
          }
          catch (NoIPException localNoIPException)
          {
            localNoIPException;
          }
        }
        if (class_1057.field_1351.a4()) {
          try
          {
            System.err.println("[SERVER] kicking for modified blueprint use " + paramString.getName());
            ((GameServerController)super.getController()).sendLogout(paramString.getId(), "You have been kick for using a modified blueprint");
          }
          catch (IOException localIOException)
          {
            localIOException;
          }
        }
        if (class_1057.field_1354.a4())
        {
          System.err.println("[SERVER] removing modified blueprint " + paramclass_1074.a4());
          this.jdField_field_144_of_type_Class_800.a53().a106(paramclass_1074.a4());
        }
      }
      return;
    }
    catch (PlayerNotFountException localPlayerNotFountException)
    {
      localPlayerNotFountException;
    }
  }
  
  public final boolean tcpNoDelay()
  {
    return class_1057.field_1355.a4();
  }
  
  public final boolean flushPingImmediately()
  {
    return class_1057.field_1356.a4();
  }
  
  static
  {
    jdField_field_144_of_type_JavaLangString = class_1041.jdField_field_150_of_type_JavaLangString = "." + File.separator + "server-database" + File.separator;
    jdField_field_145_of_type_JavaLangString = "." + File.separator + "blueprints" + File.separator;
    jdField_field_146_of_type_JavaLangString = "." + File.separator + "blueprints-default" + File.separator;
    jdField_field_147_of_type_JavaLangString = "." + File.separator + "blueprints" + File.separator + "DATA" + File.separator;
    jdField_field_148_of_type_JavaLangString = "." + File.separator + "blueprints-default" + File.separator + "DATA" + File.separator;
    jdField_field_149_of_type_JavaLangString = jdField_field_150_of_type_JavaLangString + File.separator + "DATA" + File.separator;
    jdField_field_144_of_type_ArrayOfByte = new byte[1048576];
    jdField_field_144_of_type_JavaUtilZipDeflater = new Deflater();
    jdField_field_144_of_type_JavaUtilZipInflater = new Inflater();
    jdField_field_149_of_type_Int = -1;
    jdField_field_145_of_type_ArrayOfByte = new byte[16400];
    new ByteArrayOutputStream(102400);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1041
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */