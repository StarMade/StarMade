import it.unimi.dsi.fastutil.objects.Object2IntArrayMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.network.objects.NetworkGameState;
import org.schema.game.network.objects.remote.RemoteCatalogEntry;
import org.schema.game.network.objects.remote.RemoteCatalogEntryBuffer;
import org.schema.game.server.controller.CatalogEntryNotFoundException;
import org.schema.game.server.controller.EntityNotFountException;
import org.schema.game.server.controller.GameServerController;
import org.schema.game.server.data.blueprintnw.BlueprintEntry;
import org.schema.schine.network.objects.remote.RemoteArrayBuffer;
import org.schema.schine.network.objects.remote.RemoteField;
import org.schema.schine.network.objects.remote.RemoteStringArray;

public class class_738
  extends Observable
  implements class_80
{
  private final class_800 jdField_field_136_of_type_Class_800;
  private final HashMap jdField_field_136_of_type_JavaUtilHashMap = new HashMap();
  private final HashMap jdField_field_139_of_type_JavaUtilHashMap = new HashMap();
  private final HashMap jdField_field_182_of_type_JavaUtilHashMap = new HashMap();
  private final ArrayList jdField_field_136_of_type_JavaUtilArrayList = new ArrayList();
  private final ArrayList jdField_field_139_of_type_JavaUtilArrayList = new ArrayList();
  private final ArrayList jdField_field_182_of_type_JavaUtilArrayList = new ArrayList();
  private final ArrayList field_183 = new ArrayList();
  private final ArrayList field_184 = new ArrayList();
  private final HashSet jdField_field_136_of_type_JavaUtilHashSet = new HashSet();
  private boolean jdField_field_136_of_type_Boolean;
  
  public synchronized void addObserver(Observer paramObserver)
  {
    super.addObserver(paramObserver);
  }
  
  public class_738(class_800 paramclass_800)
  {
    this.jdField_field_136_of_type_Class_800 = paramclass_800;
    if (paramclass_800.isOnServer())
    {
      f1();
      try
      {
        paramclass_800 = ((class_1041)paramclass_800.getState()).a59().a46("CATALOG", "cat");
        fromTagStructure(paramclass_800);
        d2();
        return;
      }
      catch (IOException localIOException)
      {
        localIOException.printStackTrace();
        return;
      }
      catch (EntityNotFountException paramclass_800)
      {
        System.err.println("[SERVER] NO DB CATALOG FOUND ON DISK: " + paramclass_800.getMessage());
        d2();
      }
    }
  }
  
  public final void a95(String paramString1, String paramString2, int paramInt)
  {
    paramInt = Math.max(0, Math.min(class_781.field_183, paramInt));
    RemoteStringArray localRemoteStringArray;
    (localRemoteStringArray = new RemoteStringArray(3, this.jdField_field_136_of_type_Class_800.a52())).set(0, paramString1);
    localRemoteStringArray.set(1, paramString2);
    localRemoteStringArray.set(2, String.valueOf(paramInt));
    this.jdField_field_136_of_type_Class_800.a52().catalogRatingBuffer.add(localRemoteStringArray);
  }
  
  public final void a96(class_781 paramclass_781)
  {
    this.jdField_field_136_of_type_Class_800.a52().catalogChangeRequestBuffer.add(new RemoteCatalogEntry(paramclass_781, this.jdField_field_136_of_type_Class_800.a52()));
  }
  
  public final void b15(class_781 paramclass_781)
  {
    this.jdField_field_136_of_type_Class_800.a52().catalogDeleteRequestBuffer.add(new RemoteCatalogEntry(paramclass_781, this.jdField_field_136_of_type_Class_800.a52()));
  }
  
  private void d2()
  {
    int i = 0;
    if ((!jdField_field_139_of_type_Boolean) && (!this.jdField_field_136_of_type_Class_800.isOnServer())) {
      throw new AssertionError();
    }
    (localObject = new ArrayList(this.jdField_field_136_of_type_JavaUtilHashMap.size())).addAll(this.jdField_field_136_of_type_JavaUtilHashMap.values());
    Collections.sort((List)localObject, new class_736());
    Object localObject = ((ArrayList)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      class_785 localclass_785 = (class_785)((Iterator)localObject).next();
      if (!this.jdField_field_139_of_type_JavaUtilHashMap.containsKey(localclass_785.jdField_field_1042_of_type_JavaLangString))
      {
        class_781 localclass_781 = new class_781();
        if ((i == 0) || (class_1057.field_1328.a4())) {
          localclass_781.jdField_field_182_of_type_Int |= 16;
        }
        i++;
        localclass_781.jdField_field_139_of_type_JavaLangString = "(unknown)";
        localclass_781.jdField_field_136_of_type_JavaLangString = localclass_785.jdField_field_1042_of_type_JavaLangString;
        localclass_781.jdField_field_139_of_type_Int = localclass_785.jdField_field_1042_of_type_Int;
        localclass_781.jdField_field_182_of_type_JavaLangString = "no description given";
        this.jdField_field_139_of_type_JavaUtilHashMap.put(localclass_781.jdField_field_136_of_type_JavaLangString, localclass_781);
      }
    }
    this.jdField_field_136_of_type_Boolean = true;
  }
  
  public void fromTagStructure(class_69 paramclass_69)
  {
    if ("cv0".equals(paramclass_69.a2()))
    {
      class_69[] arrayOfclass_691 = (class_69[])(paramclass_69 = (class_69[])paramclass_69.a4())[0].a4();
      for (int i = 0; i < arrayOfclass_691.length - 1; i++)
      {
        class_781 localclass_781;
        (localclass_781 = new class_781()).fromTagStructure(arrayOfclass_691[i]);
        if (this.jdField_field_136_of_type_JavaUtilHashMap.containsKey(localclass_781.jdField_field_136_of_type_JavaLangString)) {
          this.jdField_field_139_of_type_JavaUtilHashMap.put(localclass_781.jdField_field_136_of_type_JavaLangString, localclass_781);
        } else {
          System.err.println("[CATALOG][ERROR] not found in raw Catalog: " + localclass_781.jdField_field_136_of_type_JavaLangString);
        }
      }
      class_69[] arrayOfclass_692 = (class_69[])paramclass_69[1].a4();
      for (int j = 0; j < arrayOfclass_692.length - 1; j++) {
        a97(arrayOfclass_692[j]);
      }
      e2();
    }
  }
  
  private void e2()
  {
    Iterator localIterator = this.jdField_field_182_of_type_JavaUtilHashMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      class_781 localclass_781;
      if ((localclass_781 = (class_781)this.jdField_field_139_of_type_JavaUtilHashMap.get(localEntry.getKey())) != null) {
        localclass_781.a190((Object2IntArrayMap)localEntry.getValue());
      }
    }
  }
  
  public String getUniqueIdentifier()
  {
    return "CATALOG";
  }
  
  public boolean isVolatile()
  {
    return false;
  }
  
  public class_69 toTagStructure()
  {
    (localObject1 = new class_69[this.jdField_field_139_of_type_JavaUtilHashMap.size() + 1])[this.jdField_field_139_of_type_JavaUtilHashMap.size()] = new class_69(class_79.field_548, null, null);
    int i = 0;
    Object localObject2 = this.jdField_field_139_of_type_JavaUtilHashMap.values().iterator();
    Object localObject3;
    while (((Iterator)localObject2).hasNext())
    {
      localObject3 = (class_781)((Iterator)localObject2).next();
      localObject1[i] = ((class_781)localObject3).toTagStructure();
      i++;
    }
    localObject2 = new class_69(class_79.field_561, "pv0", (class_69[])localObject1);
    (localObject3 = new class_69[this.jdField_field_182_of_type_JavaUtilHashMap.size() + 1])[this.jdField_field_182_of_type_JavaUtilHashMap.size()] = new class_69(class_79.field_548, null, null);
    i = 0;
    Object localObject1 = this.jdField_field_182_of_type_JavaUtilHashMap.entrySet().iterator();
    while (((Iterator)localObject1).hasNext())
    {
      Object localObject4 = (Map.Entry)((Iterator)localObject1).next();
      Object localObject5 = (Object2IntArrayMap)((Map.Entry)localObject4).getValue();
      localObject4 = (String)((Map.Entry)localObject4).getKey();
      class_69[] arrayOfclass_69;
      (arrayOfclass_69 = new class_69[((Object2IntArrayMap)localObject5).size() + 1])[localObject5.size()] = new class_69(class_79.field_548, null, null);
      int j = 0;
      localObject5 = ((Object2IntArrayMap)localObject5).entrySet().iterator();
      Map.Entry localEntry = (Map.Entry)((Iterator)localObject5).next();
      arrayOfclass_69[j] = new class_69(class_79.field_561, null, new class_69[] { new class_69(class_79.field_556, null, localEntry.getKey()), new class_69(class_79.field_549, null, Byte.valueOf(((Integer)localEntry.getValue()).byteValue())), new class_69(class_79.field_548, null, null) });
      localObject3[i] = new class_69(class_79.field_561, (String)localObject4, arrayOfclass_69);
      i++;
    }
    localObject1 = new class_69(class_79.field_561, "r0", (class_69[])localObject3);
    return new class_69(class_79.field_561, "cv0", new class_69[] { localObject2, localObject1, new class_69(class_79.field_548, null, null) });
  }
  
  private void a97(class_69 paramclass_69)
  {
    String str = paramclass_69.a2();
    Object2IntArrayMap localObject2IntArrayMap = new Object2IntArrayMap();
    this.jdField_field_182_of_type_JavaUtilHashMap.put(str, localObject2IntArrayMap);
    paramclass_69 = (class_69[])paramclass_69.a4();
    for (int i = 0; i < paramclass_69.length - 1; i++)
    {
      class_69[] arrayOfclass_69 = (class_69[])paramclass_69[i].a4();
      localObject2IntArrayMap.put((String)arrayOfclass_69[0].a4(), ((Byte)arrayOfclass_69[1].a4()).intValue());
    }
  }
  
  public final void a13()
  {
    int i = 0;
    Object localObject5;
    int n;
    if (!this.jdField_field_136_of_type_Class_800.isOnServer())
    {
      if (!this.jdField_field_139_of_type_JavaUtilArrayList.isEmpty()) {
        synchronized (this.jdField_field_139_of_type_JavaUtilArrayList)
        {
          while (!this.jdField_field_139_of_type_JavaUtilArrayList.isEmpty())
          {
            localObject5 = (class_781)this.jdField_field_139_of_type_JavaUtilArrayList.remove(0);
            this.jdField_field_136_of_type_JavaUtilHashSet.remove(localObject5);
            this.jdField_field_136_of_type_JavaUtilHashSet.add(localObject5);
            i = 1;
          }
        }
      }
      if (!this.jdField_field_182_of_type_JavaUtilArrayList.isEmpty()) {
        synchronized (this.jdField_field_182_of_type_JavaUtilArrayList)
        {
          while (!this.jdField_field_182_of_type_JavaUtilArrayList.isEmpty())
          {
            localObject5 = (class_781)this.jdField_field_182_of_type_JavaUtilArrayList.remove(0);
            this.jdField_field_136_of_type_JavaUtilHashSet.remove(localObject5);
            i = 1;
          }
        }
      }
    }
    else
    {
      class_781 localclass_781;
      if (!this.jdField_field_136_of_type_JavaUtilArrayList.isEmpty()) {
        synchronized (this.jdField_field_136_of_type_JavaUtilArrayList)
        {
          while (!this.jdField_field_136_of_type_JavaUtilArrayList.isEmpty())
          {
            localObject5 = (class_783)this.jdField_field_136_of_type_JavaUtilArrayList.remove(0);
            if ((localclass_781 = (class_781)this.jdField_field_139_of_type_JavaUtilHashMap.get(((class_783)localObject5).field_1041)) != null)
            {
              Object2IntArrayMap localObject2IntArrayMap;
              if ((localObject2IntArrayMap = (Object2IntArrayMap)this.jdField_field_182_of_type_JavaUtilHashMap.get(((class_783)localObject5).field_1041)) == null)
              {
                localObject2IntArrayMap = new Object2IntArrayMap();
                this.jdField_field_182_of_type_JavaUtilHashMap.put(((class_783)localObject5).field_1041, localObject2IntArrayMap);
              }
              int i1 = localObject2IntArrayMap.put(((class_783)localObject5).jdField_field_1040_of_type_JavaLangString, ((class_783)localObject5).jdField_field_1040_of_type_Int);
              localclass_781.a190(localObject2IntArrayMap);
              this.jdField_field_136_of_type_Class_800.a52().catalogBuffer.add(new RemoteCatalogEntry(localclass_781, this.jdField_field_136_of_type_Class_800.a52()));
              ((class_1041)this.jdField_field_136_of_type_Class_800.getState()).a59().a53(((class_783)localObject5).jdField_field_1040_of_type_JavaLangString, "Rated " + localclass_781.jdField_field_136_of_type_JavaLangString + ":\nYour Rating: " + ((class_783)localObject5).jdField_field_1040_of_type_Int + "\nYour Rating Before: " + i1, 1);
              int j = 1;
            }
            else
            {
              System.err.println("[SERVER][CATALOG][ERROR] cannot rate: " + ((class_783)localObject5).field_1041 + ": entry not found");
            }
          }
        }
      }
      if (!this.field_183.isEmpty()) {
        synchronized (this.field_183)
        {
          while (!this.field_183.isEmpty())
          {
            localObject5 = (class_781)this.field_183.remove(0);
            if (a98((class_781)localObject5))
            {
              localclass_781 = (class_781)this.jdField_field_139_of_type_JavaUtilHashMap.get(((class_781)localObject5).jdField_field_136_of_type_JavaLangString);
              ((class_781)localObject5).jdField_field_136_of_type_Float = localclass_781.jdField_field_136_of_type_Float;
              System.err.println("[SERVER][CATLOG] permission granted to change: " + localObject5);
              this.jdField_field_139_of_type_JavaUtilHashMap.put(((class_781)localObject5).jdField_field_136_of_type_JavaLangString, localObject5);
              this.jdField_field_136_of_type_Class_800.a52().catalogBuffer.add(new RemoteCatalogEntry((class_781)localObject5, this.jdField_field_136_of_type_Class_800.a52()));
              int k = 1;
            }
          }
        }
      }
      if (!this.field_184.isEmpty()) {
        synchronized (this.field_184)
        {
          while (!this.field_184.isEmpty())
          {
            localObject5 = (class_781)this.field_184.remove(0);
            System.err.println("[SERVER][CATALOG] handling delete request: " + localObject5);
            if (a98((class_781)localObject5))
            {
              localclass_781 = (class_781)this.jdField_field_139_of_type_JavaUtilHashMap.get(((class_781)localObject5).jdField_field_136_of_type_JavaLangString);
              ((class_781)localObject5).jdField_field_136_of_type_Float = localclass_781.jdField_field_136_of_type_Float;
              System.err.println("[SERVER][CATLOG] permission granted to delete: " + localObject5);
              this.jdField_field_139_of_type_JavaUtilHashMap.remove(((class_781)localObject5).jdField_field_136_of_type_JavaLangString);
              this.jdField_field_182_of_type_JavaUtilHashMap.remove(((class_781)localObject5).jdField_field_136_of_type_JavaLangString);
              BlueprintEntry localBlueprintEntry = new BlueprintEntry(((class_781)localObject5).jdField_field_136_of_type_JavaLangString);
              try
              {
                class_1216.field_1429.a(localBlueprintEntry.a4());
                System.err.println("[SERVER] PHYSICALLY DELETING BLUEPRINT ENTRY (backup export): " + localBlueprintEntry.a4());
                class_1216.field_1429.a11(localBlueprintEntry);
              }
              catch (IOException localIOException)
              {
                ((class_1041)this.jdField_field_136_of_type_Class_800.getState()).a59().a53(((class_781)localObject5).jdField_field_139_of_type_JavaLangString, "ERROR\nthere was an IO error\ndeleting the entry.\nPlease report!", 3);
                localIOException.printStackTrace();
              }
              catch (CatalogEntryNotFoundException localCatalogEntryNotFoundException)
              {
                ((class_1041)this.jdField_field_136_of_type_Class_800.getState()).a59().a53(((class_781)localObject5).jdField_field_139_of_type_JavaLangString, "ERROR\nentry not found.\nplease report!", 3);
                localCatalogEntryNotFoundException.printStackTrace();
              }
              this.jdField_field_136_of_type_Class_800.a52().catalogDeleteBuffer.add(new RemoteCatalogEntry((class_781)localObject5, this.jdField_field_136_of_type_Class_800.a52()));
              int m = 1;
            }
          }
        }
      }
      if (this.jdField_field_136_of_type_Boolean)
      {
        n = 1;
        this.jdField_field_136_of_type_Boolean = false;
      }
    }
    if (n != 0)
    {
      setChanged();
      notifyObservers();
    }
  }
  
  private boolean a98(class_781 paramclass_781)
  {
    String str = paramclass_781.jdField_field_139_of_type_JavaLangString;
    class_781 localclass_781 = (class_781)this.jdField_field_139_of_type_JavaUtilHashMap.get(paramclass_781.jdField_field_136_of_type_JavaLangString);
    boolean bool = paramclass_781.jdField_field_136_of_type_Boolean;
    if (localclass_781 == null)
    {
      ((class_1041)this.jdField_field_136_of_type_Class_800.getState()).a59().a53(str, "ERROR\ncatalog entry does not exist", 3);
      return false;
    }
    if ((!localclass_781.jdField_field_139_of_type_JavaLangString.equals(str)) && (!bool))
    {
      ((class_1041)this.jdField_field_136_of_type_Class_800.getState()).a59().a53(str, "ERROR\nyou do not own this entry", 3);
      return false;
    }
    System.err.println("[CATALOG] CHANGING REQUEST by " + str + ": " + paramclass_781 + "; ADMIN: " + bool);
    return true;
  }
  
  public static void b4() {}
  
  private void f1()
  {
    System.currentTimeMillis();
    Object localObject = class_1216.field_1429.a7();
    this.jdField_field_136_of_type_JavaUtilHashMap.clear();
    System.err.println("READ RAW BLUEPRINTS: " + ((List)localObject).size());
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      BlueprintEntry localBlueprintEntry = (BlueprintEntry)((Iterator)localObject).next();
      a99(localBlueprintEntry);
    }
  }
  
  private void a99(BlueprintEntry paramBlueprintEntry)
  {
    this.jdField_field_136_of_type_JavaUtilHashMap.put(paramBlueprintEntry.a4(), new class_785(paramBlueprintEntry.a4(), paramBlueprintEntry.a2(), paramBlueprintEntry.a17()));
  }
  
  public final void a100(NetworkGameState paramNetworkGameState)
  {
    Iterator localIterator = this.jdField_field_139_of_type_JavaUtilHashMap.values().iterator();
    while (localIterator.hasNext())
    {
      class_781 localclass_781 = (class_781)localIterator.next();
      class_785 localclass_785;
      if (((localclass_785 = (class_785)this.jdField_field_136_of_type_JavaUtilHashMap.get(localclass_781.jdField_field_136_of_type_JavaLangString)) != null) && (localclass_785.jdField_field_1042_of_type_Class_1084 == class_1084.field_1360)) {
        paramNetworkGameState.catalogBuffer.add(new RemoteCatalogEntry(localclass_781, paramNetworkGameState));
      } else {
        System.err.println("[CATALOGMANAGER][SERVER] NOT SENDING TO CLIENTS: " + localclass_781 + ": " + localclass_785);
      }
    }
  }
  
  public final void b16(NetworkGameState paramNetworkGameState)
  {
    Iterator localIterator = paramNetworkGameState.catalogRatingBuffer.getReceiveBuffer().iterator();
    Object localObject1;
    while (localIterator.hasNext())
    {
      RemoteStringArray localRemoteStringArray = (RemoteStringArray)localIterator.next();
      if ((!jdField_field_139_of_type_Boolean) && (!this.jdField_field_136_of_type_Class_800.isOnServer())) {
        throw new AssertionError();
      }
      String str1 = (String)localRemoteStringArray.get(0).get();
      String str2 = (String)localRemoteStringArray.get(1).get();
      int i = Integer.parseInt((String)localRemoteStringArray.get(2).get());
      localObject1 = new class_783(str1, str2, i);
      synchronized (this.jdField_field_136_of_type_JavaUtilArrayList)
      {
        this.jdField_field_136_of_type_JavaUtilArrayList.add(localObject1);
      }
    }
    localIterator = paramNetworkGameState.catalogBuffer.getReceiveBuffer().iterator();
    while (localIterator.hasNext())
    {
      localObject1 = (RemoteCatalogEntry)localIterator.next();
      if ((!jdField_field_139_of_type_Boolean) && (this.jdField_field_136_of_type_Class_800.isOnServer())) {
        throw new AssertionError();
      }
      ??? = (class_781)((RemoteCatalogEntry)localObject1).get();
      synchronized (this.jdField_field_139_of_type_JavaUtilArrayList)
      {
        this.jdField_field_139_of_type_JavaUtilArrayList.add(???);
      }
    }
    localIterator = paramNetworkGameState.catalogDeleteBuffer.getReceiveBuffer().iterator();
    while (localIterator.hasNext())
    {
      localObject1 = (RemoteCatalogEntry)localIterator.next();
      if ((!jdField_field_139_of_type_Boolean) && (this.jdField_field_136_of_type_Class_800.isOnServer())) {
        throw new AssertionError();
      }
      ??? = (class_781)((RemoteCatalogEntry)localObject1).get();
      synchronized (this.jdField_field_182_of_type_JavaUtilArrayList)
      {
        this.jdField_field_182_of_type_JavaUtilArrayList.add(???);
      }
    }
    localIterator = paramNetworkGameState.catalogChangeRequestBuffer.getReceiveBuffer().iterator();
    while (localIterator.hasNext())
    {
      localObject1 = (RemoteCatalogEntry)localIterator.next();
      if ((!jdField_field_139_of_type_Boolean) && (!this.jdField_field_136_of_type_Class_800.isOnServer())) {
        throw new AssertionError();
      }
      ??? = (class_781)((RemoteCatalogEntry)localObject1).get();
      System.err.println("[SERVER] received catalog change request: " + ???);
      synchronized (this.field_183)
      {
        this.field_183.add(???);
      }
    }
    localIterator = paramNetworkGameState.catalogDeleteRequestBuffer.getReceiveBuffer().iterator();
    while (localIterator.hasNext())
    {
      localObject1 = (RemoteCatalogEntry)localIterator.next();
      if ((!jdField_field_139_of_type_Boolean) && (!this.jdField_field_136_of_type_Class_800.isOnServer())) {
        throw new AssertionError();
      }
      ??? = (class_781)((RemoteCatalogEntry)localObject1).get();
      System.err.println("[SERVER] received catalog delete request: " + ???);
      synchronized (this.field_184)
      {
        this.field_184.add(???);
      }
    }
  }
  
  public static void c1() {}
  
  public final Collection a101()
  {
    if (this.jdField_field_136_of_type_Class_800.isOnServer()) {
      return this.jdField_field_139_of_type_JavaUtilHashMap.values();
    }
    return this.jdField_field_136_of_type_JavaUtilHashSet;
  }
  
  public final void a102(class_1134 paramclass_1134, class_748 paramclass_748)
  {
    if (class_1057.field_1333.a4())
    {
      int i = 0;
      String str = paramclass_1134.jdField_field_1382_of_type_JavaLangString;
      while (this.jdField_field_139_of_type_JavaUtilHashMap.containsKey(str))
      {
        str = paramclass_1134.jdField_field_1382_of_type_JavaLangString;
        str = str + String.valueOf(i);
        i++;
      }
      paramclass_1134.jdField_field_1382_of_type_JavaLangString = str;
    }
    else if (this.jdField_field_139_of_type_JavaUtilHashMap.containsKey(paramclass_1134.jdField_field_1382_of_type_JavaLangString))
    {
      ((class_1041)this.jdField_field_136_of_type_Class_800.getState()).a59().a53(paramclass_748.getName(), "Upload failed!\nEntry already exists!", 3);
      return;
    }
    paramclass_1134.jdField_field_1382_of_type_Class_747.writeAllBufferedSegmentsToDatabase();
    class_1216.field_1429.a12(paramclass_1134.jdField_field_1382_of_type_Class_747, paramclass_1134.jdField_field_1382_of_type_JavaLangString, false);
    List localList = class_1216.field_1429.a7();
    for (int j = 0; j < localList.size(); j++) {
      if (((BlueprintEntry)localList.get(j)).a4().equals(paramclass_1134.jdField_field_1382_of_type_JavaLangString))
      {
        a104((BlueprintEntry)localList.get(j), paramclass_748);
        return;
      }
    }
  }
  
  public final void a103(SegmentController paramSegmentController, String paramString, class_748 paramclass_748)
  {
    paramSegmentController.writeAllBufferedSegmentsToDatabase();
    class_1216.field_1429.a12(paramSegmentController, paramString, false);
    paramSegmentController = class_1216.field_1429.a7();
    for (int i = 0; i < paramSegmentController.size(); i++) {
      if (((BlueprintEntry)paramSegmentController.get(i)).a4().equals(paramString))
      {
        a104((BlueprintEntry)paramSegmentController.get(i), paramclass_748);
        return;
      }
    }
  }
  
  public final void a104(BlueprintEntry paramBlueprintEntry, class_748 paramclass_748)
  {
    class_781 localclass_781;
    (localclass_781 = new class_781()).jdField_field_136_of_type_JavaLangString = paramBlueprintEntry.a4();
    localclass_781.jdField_field_139_of_type_JavaLangString = paramclass_748.getName();
    System.err.println("[SERVER][CATALOG] ADDING ENTRY FROM RAW BLUEPRINT: " + paramBlueprintEntry.a4() + " for " + paramclass_748 + "; price: " + paramBlueprintEntry.a2());
    localclass_781.jdField_field_139_of_type_Int = paramBlueprintEntry.a2();
    localclass_781.jdField_field_182_of_type_JavaLangString = "no descrption given";
    localclass_781.jdField_field_182_of_type_Int &= -9;
    a99(paramBlueprintEntry);
    this.jdField_field_139_of_type_JavaUtilHashMap.put(localclass_781.jdField_field_136_of_type_JavaLangString, localclass_781);
    if (paramBlueprintEntry.a17() == class_1084.field_1360) {
      this.jdField_field_136_of_type_Class_800.a52().catalogBuffer.add(new RemoteCatalogEntry(localclass_781, this.jdField_field_136_of_type_Class_800.a52()));
    }
    this.jdField_field_136_of_type_Boolean = true;
  }
  
  public final void a105(File paramFile, class_748 paramclass_748)
  {
    class_1216.field_1429.a1(paramFile, new class_1190(this, paramclass_748));
  }
  
  public final void a106(String arg1)
  {
    class_781 localclass_781;
    (localclass_781 = new class_781()).jdField_field_136_of_type_JavaLangString = ???;
    localclass_781.jdField_field_139_of_type_JavaLangString = "ADMIN";
    localclass_781.jdField_field_136_of_type_Boolean = true;
    synchronized (this.field_184)
    {
      this.field_184.add(localclass_781);
      return;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_738
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */