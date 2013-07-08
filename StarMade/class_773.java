import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import org.schema.game.network.objects.NetworkGameState;
import org.schema.game.network.objects.NetworkPlayer;
import org.schema.schine.network.objects.remote.RemoteArray;
import org.schema.schine.network.objects.remote.RemoteArrayBuffer;
import org.schema.schine.network.objects.remote.RemoteBoolean;
import org.schema.schine.network.objects.remote.RemoteBuffer;
import org.schema.schine.network.objects.remote.RemoteStringArray;

public class class_773
  implements class_80
{
  private final HashMap jdField_field_136_of_type_JavaUtilHashMap = new HashMap();
  private String jdField_field_136_of_type_JavaLangString;
  private String jdField_field_139_of_type_JavaLangString;
  private String jdField_field_182_of_type_JavaLangString;
  private int jdField_field_136_of_type_Int;
  private long jdField_field_136_of_type_Long;
  private String jdField_field_183_of_type_JavaLangString = "";
  private boolean jdField_field_136_of_type_Boolean;
  private boolean jdField_field_139_of_type_Boolean;
  private boolean jdField_field_182_of_type_Boolean;
  private boolean jdField_field_183_of_type_Boolean;
  private final class_625 jdField_field_136_of_type_Class_625 = new class_625();
  private String jdField_field_184_of_type_JavaLangString = "";
  private final class_48 jdField_field_136_of_type_Class_48 = new class_48();
  
  public class_773(int paramInt, String paramString1, String paramString2, String paramString3)
  {
    this();
    this.jdField_field_136_of_type_Int = paramInt;
    this.jdField_field_136_of_type_Class_625.jdField_field_136_of_type_Int = paramInt;
    this.jdField_field_136_of_type_JavaLangString = paramString1;
    this.jdField_field_139_of_type_JavaLangString = paramString2;
    this.jdField_field_182_of_type_JavaLangString = paramString3;
    this.jdField_field_136_of_type_Long = System.currentTimeMillis();
  }
  
  public class_773() {}
  
  public final HashMap a162()
  {
    return this.jdField_field_136_of_type_JavaUtilHashMap;
  }
  
  public final String a()
  {
    return this.jdField_field_139_of_type_JavaLangString;
  }
  
  public final void a106(String paramString)
  {
    this.jdField_field_139_of_type_JavaLangString = paramString;
  }
  
  public final String b()
  {
    return this.jdField_field_136_of_type_JavaLangString;
  }
  
  public final void b31(String paramString)
  {
    this.jdField_field_136_of_type_JavaLangString = paramString;
  }
  
  public void fromTagStructure(class_69 paramclass_69)
  {
    paramclass_69 = (class_69[])paramclass_69.a4();
    this.jdField_field_136_of_type_JavaLangString = ((String)paramclass_69[0].a4());
    this.jdField_field_139_of_type_JavaLangString = ((String)paramclass_69[1].a4());
    this.jdField_field_182_of_type_JavaLangString = ((String)paramclass_69[2].a4());
    this.jdField_field_136_of_type_Long = ((Long)paramclass_69[3].a4()).longValue();
    class_69[] arrayOfclass_69 = (class_69[])paramclass_69[4].a4();
    for (int i = 0; i < arrayOfclass_69.length - 1; i++)
    {
      class_758 localclass_758;
      (localclass_758 = new class_758()).fromTagStructure(arrayOfclass_69[i]);
      this.jdField_field_136_of_type_JavaUtilHashMap.put(localclass_758.jdField_field_136_of_type_JavaLangString, localclass_758);
    }
    this.jdField_field_182_of_type_Boolean = (((Byte)paramclass_69[5].a4()).byteValue() == 1);
    this.jdField_field_136_of_type_Class_625.fromTagStructure(paramclass_69[6]);
    this.jdField_field_184_of_type_JavaLangString = ((String)paramclass_69[7].a4());
    this.jdField_field_183_of_type_JavaLangString = ((String)paramclass_69[8].a4());
    this.jdField_field_136_of_type_Int = ((Integer)paramclass_69[9].a4()).intValue();
    this.jdField_field_139_of_type_Boolean = (((Byte)paramclass_69[10].a4()).byteValue() == 1);
    this.jdField_field_136_of_type_Boolean = (((Byte)paramclass_69[11].a4()).byteValue() == 1);
    this.jdField_field_136_of_type_Class_48.b1((class_48)paramclass_69[12].a4());
    if ((paramclass_69.length > 13) && (paramclass_69[13].a3() == class_79.field_549)) {
      this.jdField_field_183_of_type_Boolean = (((Byte)paramclass_69[13].a4()).byteValue() == 1);
    }
    System.err.println("[SERVER] loaded faction from disk: " + this);
  }
  
  public class_69 toTagStructure()
  {
    class_69[] arrayOfclass_69;
    (arrayOfclass_69 = new class_69[15])[0] = new class_69(class_79.field_556, null, this.jdField_field_136_of_type_JavaLangString);
    arrayOfclass_69[1] = new class_69(class_79.field_556, null, this.jdField_field_139_of_type_JavaLangString);
    arrayOfclass_69[2] = new class_69(class_79.field_556, null, this.jdField_field_182_of_type_JavaLangString);
    arrayOfclass_69[3] = new class_69(class_79.field_552, null, Long.valueOf(this.jdField_field_136_of_type_Long));
    arrayOfclass_69[4] = class_69.a6(this.jdField_field_136_of_type_JavaUtilHashMap.values(), "mem");
    arrayOfclass_69[5] = new class_69(class_79.field_549, null, Byte.valueOf(this.jdField_field_182_of_type_Boolean ? 1 : 0));
    arrayOfclass_69[6] = this.jdField_field_136_of_type_Class_625.toTagStructure();
    arrayOfclass_69[7] = new class_69(class_79.field_556, "home", this.jdField_field_184_of_type_JavaLangString);
    arrayOfclass_69[8] = new class_69(class_79.field_556, "pw", this.jdField_field_183_of_type_JavaLangString);
    arrayOfclass_69[9] = new class_69(class_79.field_551, "id", Integer.valueOf(this.jdField_field_136_of_type_Int));
    arrayOfclass_69[10] = new class_69(class_79.field_549, "fn", Byte.valueOf(this.jdField_field_139_of_type_Boolean ? 1 : 0));
    arrayOfclass_69[11] = new class_69(class_79.field_549, "en", Byte.valueOf(this.jdField_field_136_of_type_Boolean ? 1 : 0));
    arrayOfclass_69[12] = new class_69(class_79.field_558, null, this.jdField_field_136_of_type_Class_48);
    arrayOfclass_69[13] = new class_69(class_79.field_549, "aw", Byte.valueOf(this.jdField_field_183_of_type_Boolean ? 1 : 0));
    arrayOfclass_69[14] = new class_69(class_79.field_548, null, null);
    return new class_69(class_79.field_561, "f0", arrayOfclass_69);
  }
  
  public String getUniqueIdentifier()
  {
    return "FACTION_" + this.jdField_field_136_of_type_JavaLangString;
  }
  
  public boolean isVolatile()
  {
    return false;
  }
  
  public final String c5()
  {
    return this.jdField_field_182_of_type_JavaLangString;
  }
  
  public final void c18(String paramString)
  {
    this.jdField_field_182_of_type_JavaLangString = paramString;
  }
  
  public String toString()
  {
    return "Faction [id=" + this.jdField_field_136_of_type_Int + ", name=" + this.jdField_field_139_of_type_JavaLangString + ", description=" + this.jdField_field_182_of_type_JavaLangString + " size: " + this.jdField_field_136_of_type_JavaUtilHashMap.size() + "]";
  }
  
  public final int a3()
  {
    return this.jdField_field_136_of_type_Int;
  }
  
  public final void a36(int paramInt)
  {
    this.jdField_field_136_of_type_Int = paramInt;
    this.jdField_field_136_of_type_Class_625.jdField_field_136_of_type_Int = paramInt;
  }
  
  public final void a175(String paramString1, String paramString2, byte paramByte, class_800 paramclass_800)
  {
    synchronized (this.jdField_field_136_of_type_JavaUtilHashMap)
    {
      this.jdField_field_136_of_type_JavaUtilHashMap.put(paramString2, new class_758(paramString2, paramByte));
      System.err.println("[FACTION] Added to members " + paramString2 + " perm(" + paramByte + ") of " + this + " on " + paramclass_800.getState());
    }
    if (paramclass_800.isOnServer())
    {
      (??? = new RemoteStringArray(4, paramclass_800.a52())).set(0, paramString2);
      ((RemoteStringArray)???).set(1, String.valueOf(this.jdField_field_136_of_type_Int));
      ((RemoteStringArray)???).set(2, String.valueOf(paramByte));
      ((RemoteStringArray)???).set(3, paramString1);
      paramclass_800.a52().factionMemberMod.add((RemoteArray)???);
    }
  }
  
  public final void b32(String paramString1, String paramString2, byte paramByte, class_800 paramclass_800)
  {
    System.err.println("[CLIENT][Faction] Sending modding of member " + paramString2 + " from " + this);
    RemoteStringArray localRemoteStringArray;
    (localRemoteStringArray = new RemoteStringArray(4, paramclass_800.a52())).set(0, paramString2);
    localRemoteStringArray.set(1, String.valueOf(this.jdField_field_136_of_type_Int));
    localRemoteStringArray.set(2, String.valueOf(paramByte));
    localRemoteStringArray.set(3, paramString1);
    paramclass_800.a52().factionMemberMod.add(localRemoteStringArray);
  }
  
  public final void a176(String paramString1, String paramString2, class_800 paramclass_800)
  {
    System.err.println("[CLIENT][Faction] Sending removal of member " + paramString2 + " from " + this);
    RemoteStringArray localRemoteStringArray;
    (localRemoteStringArray = new RemoteStringArray(3, paramclass_800.a52())).set(0, paramString2);
    localRemoteStringArray.set(1, String.valueOf(this.jdField_field_136_of_type_Int));
    localRemoteStringArray.set(2, paramString1);
    paramclass_800.a52().factionkickMemberRequests.add(localRemoteStringArray);
  }
  
  public final void a177(String paramString, class_800 paramclass_800)
  {
    synchronized (this.jdField_field_136_of_type_JavaUtilHashMap)
    {
      RemoteStringArray localRemoteStringArray = null;
      if ((class_758)this.jdField_field_136_of_type_JavaUtilHashMap.remove(paramString) != null)
      {
        if (paramclass_800.isOnServer())
        {
          System.err.println("[SERVER][Faction] Sending removal of member " + paramString + " from " + this);
          (localRemoteStringArray = new RemoteStringArray(4, paramclass_800.a52())).set(0, paramString);
          localRemoteStringArray.set(1, String.valueOf(this.jdField_field_136_of_type_Int));
          localRemoteStringArray.set(2, "r");
          localRemoteStringArray.set(3, paramString);
          paramclass_800.a52().factionMemberMod.add(localRemoteStringArray);
        }
      }
      else {
        System.err.println("[Faction] WARNING: could not remove " + paramString + " from " + this.jdField_field_136_of_type_JavaUtilHashMap + " on " + paramclass_800.getState());
      }
      return;
    }
  }
  
  public final void a1(DataInputStream paramDataInputStream)
  {
    int i = paramDataInputStream.readInt();
    for (int j = 0; j < i; j++)
    {
      Object localObject = paramDataInputStream.readUTF();
      byte b = paramDataInputStream.readByte();
      localObject = new class_758((String)localObject, b);
      this.jdField_field_136_of_type_JavaUtilHashMap.put(((class_758)localObject).jdField_field_136_of_type_JavaLangString, localObject);
    }
  }
  
  public final void a2(DataOutputStream paramDataOutputStream)
  {
    paramDataOutputStream.writeInt(this.jdField_field_136_of_type_JavaUtilHashMap.size());
    Iterator localIterator = this.jdField_field_136_of_type_JavaUtilHashMap.values().iterator();
    while (localIterator.hasNext())
    {
      class_758 localclass_758 = (class_758)localIterator.next();
      paramDataOutputStream.writeUTF(localclass_758.jdField_field_136_of_type_JavaLangString);
      paramDataOutputStream.writeByte(localclass_758.jdField_field_136_of_type_Byte);
    }
  }
  
  public final boolean a7()
  {
    return this.jdField_field_139_of_type_Boolean;
  }
  
  public final void a72(boolean paramBoolean)
  {
    this.jdField_field_139_of_type_Boolean = paramBoolean;
  }
  
  public final boolean b2()
  {
    return this.jdField_field_136_of_type_Boolean;
  }
  
  public final void b13(boolean paramBoolean)
  {
    this.jdField_field_136_of_type_Boolean = paramBoolean;
  }
  
  public final boolean c3()
  {
    return this.jdField_field_182_of_type_Boolean;
  }
  
  public final void a117(class_809 paramclass_809)
  {
    if ((!jdField_field_184_of_type_Boolean) && (!(paramclass_809.getState() instanceof class_1041))) {
      throw new AssertionError();
    }
    if ((this.jdField_field_183_of_type_Boolean) && ((paramclass_809 instanceof class_797)))
    {
      class_765 localclass_765 = ((class_1039)paramclass_809.getState()).a();
      int i;
      if (((i = ((class_797)paramclass_809).getFactionId()) == 0) && ((paramclass_809 instanceof class_365)) && (((class_365)paramclass_809).a26().size() > 0)) {
        i = ((class_748)((class_365)paramclass_809).a26().get(0)).h1();
      }
      if ((this.jdField_field_136_of_type_Int != i) && (localclass_765.c17(this.jdField_field_136_of_type_Int, i)))
      {
        if ((i == 0) || (localclass_765.a156(i) == null))
        {
          System.err.println("[SERVER][FACTION] Hostlie action detected from NEUTRAL " + paramclass_809 + ", FID: " + i + ": DECLARING WAR");
          this.jdField_field_136_of_type_Boolean = true;
          b34("ADMIN", true, ((class_1041)paramclass_809.getState()).a12());
          return;
        }
        class_773 localclass_773 = localclass_765.a156(i);
        class_629 localclass_629 = new class_629();
        String str = "AUTO-DECLARED-WAR:\n\nhostile action detected from\n" + paramclass_809;
        if (((paramclass_809 instanceof class_365)) && (((class_365)paramclass_809).a26().size() > 0)) {
          str = str + "\nPilot: " + ((class_748)((class_365)paramclass_809).a26().get(0)).getName();
        }
        localclass_629.a30("ADMIN", this.jdField_field_136_of_type_Int, i, (byte)1, str);
        System.err.println("[SERVER][FACTION] Hostlie action detected from " + paramclass_809 + " of Faction " + localclass_773 + ": DECLARING WAR");
        localclass_765.a167(localclass_629);
      }
    }
  }
  
  public final void c6(boolean paramBoolean)
  {
    this.jdField_field_182_of_type_Boolean = paramBoolean;
  }
  
  public static void a92(class_748 paramclass_748, boolean paramBoolean)
  {
    paramclass_748.a127().requestFactionOpenToJoin.add(new RemoteBoolean(paramBoolean, paramclass_748.a127()));
  }
  
  public static void b33(class_748 paramclass_748, boolean paramBoolean)
  {
    paramclass_748.a127().requestAttackNeutral.add(new RemoteBoolean(paramBoolean, paramclass_748.a127()));
  }
  
  public static void c19(class_748 paramclass_748, boolean paramBoolean)
  {
    paramclass_748.a127().requestAutoDeclareWar.add(new RemoteBoolean(paramBoolean, paramclass_748.a127()));
  }
  
  public final void a178(String paramString, boolean paramBoolean, class_800 paramclass_800)
  {
    a179(paramString, "OTJ", String.valueOf(paramBoolean), paramclass_800);
  }
  
  public final void b34(String paramString, boolean paramBoolean, class_800 paramclass_800)
  {
    a179(paramString, "ATN", String.valueOf(paramBoolean), paramclass_800);
  }
  
  public final void c20(String paramString, boolean paramBoolean, class_800 paramclass_800)
  {
    a179(paramString, "ADW", String.valueOf(paramBoolean), paramclass_800);
  }
  
  public final void b35(String paramString1, String paramString2, class_800 paramclass_800)
  {
    a179(paramString1, "DES", paramString2, paramclass_800);
  }
  
  public final void c21(String paramString1, String paramString2, class_800 paramclass_800)
  {
    a179(paramString1, "NAM", paramString2, paramclass_800);
  }
  
  private void a179(String paramString1, String paramString2, String paramString3, class_800 paramclass_800)
  {
    RemoteStringArray localRemoteStringArray;
    (localRemoteStringArray = new RemoteStringArray(4, paramclass_800.a52())).set(0, paramString1);
    localRemoteStringArray.set(1, String.valueOf(this.jdField_field_136_of_type_Int));
    localRemoteStringArray.set(2, paramString2);
    localRemoteStringArray.set(3, paramString3);
    paramclass_800.a52().factionMod.add(localRemoteStringArray);
  }
  
  public final class_625 a180()
  {
    return this.jdField_field_136_of_type_Class_625;
  }
  
  public final String d8()
  {
    return this.jdField_field_184_of_type_JavaLangString;
  }
  
  public final void d9(String paramString)
  {
    this.jdField_field_184_of_type_JavaLangString = paramString;
  }
  
  public final class_48 a44()
  {
    return this.jdField_field_136_of_type_Class_48;
  }
  
  public final boolean d10()
  {
    return this.jdField_field_183_of_type_Boolean;
  }
  
  public final void d11(boolean paramBoolean)
  {
    this.jdField_field_183_of_type_Boolean = paramBoolean;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_773
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */