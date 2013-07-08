import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.database.DatabaseIndex;
import org.schema.game.common.data.world.Universe;
import org.schema.game.server.controller.EntityNotFountException;
import org.schema.schine.ai.stateMachines.FSMException;
import org.schema.schine.graphicsengine.shader.ErrorDialogException;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.server.ServerEntityWriterThread;

public abstract class class_1063
  extends class_989
  implements class_80
{
  class_48 jdField_field_223_of_type_Class_48;
  final ObjectArrayList jdField_field_223_of_type_ItUnimiDsiFastutilObjectsObjectArrayList = new ObjectArrayList();
  private final Object2ObjectOpenHashMap jdField_field_223_of_type_ItUnimiDsiFastutilObjectsObject2ObjectOpenHashMap = new Object2ObjectOpenHashMap();
  final class_1041 jdField_field_223_of_type_Class_1041;
  private long jdField_field_223_of_type_Long = System.currentTimeMillis();
  private int jdField_field_223_of_type_Int;
  
  public abstract class_1015 a10();
  
  public class_1063(class_1041 paramclass_1041)
  {
    super("SimulationGroup", paramclass_1041);
    this.jdField_field_223_of_type_Class_1041 = paramclass_1041;
  }
  
  public final void a11(int paramInt)
  {
    this.jdField_field_223_of_type_Int += paramInt;
    if (this.jdField_field_223_of_type_Int > 50)
    {
      try
      {
        a2(null);
      }
      catch (FSMException localFSMException)
      {
        localFSMException;
      }
      catch (ErrorDialogException localErrorDialogException)
      {
        localErrorDialogException;
      }
      catch (Exception localException)
      {
        localException;
      }
      this.jdField_field_223_of_type_Int -= 50;
    }
  }
  
  public void a2(class_941 paramclass_941)
  {
    if ((this.field_223 != null) && (!this.field_223.b1())) {
      this.field_223.a13().b1();
    }
  }
  
  public final boolean a12(String paramString, class_48 paramclass_48)
  {
    Object localObject1;
    Object localObject2;
    class_48 localclass_48;
    if (a16(paramString))
    {
      localObject1 = (SegmentController)this.jdField_field_223_of_type_Class_1041.c8().get(paramString);
      localObject2 = this.jdField_field_223_of_type_Class_1041.a62().getSector(((SegmentController)localObject1).getSectorId());
      localclass_48 = new class_48(paramclass_48);
      if (localObject2 != null) {
        try
        {
          if (((class_670)localObject2).a68() == class_808.field_1067) {
            if (paramclass_48.field_476 > ((class_670)localObject2).field_136.field_476) {
              localclass_48.b(((class_670)localObject2).field_136.field_475 - 3, ((class_670)localObject2).field_136.field_476 - 1, ((class_670)localObject2).field_136.field_477);
            } else {
              localclass_48.b(((class_670)localObject2).field_136.field_475 - 3, ((class_670)localObject2).field_136.field_476 + 1, ((class_670)localObject2).field_136.field_477);
            }
          }
        }
        catch (IOException localIOException)
        {
          localIOException.printStackTrace();
          return false;
        }
        catch (Exception localException1)
        {
          localException1.printStackTrace();
          return false;
        }
      }
      class_983 localclass_983;
      if (((localObject1 instanceof class_991)) && (((localclass_983 = ((class_991)localObject1).a().a().a3()) instanceof class_1256)))
      {
        ((class_1256)localclass_983).a10(localclass_48);
        if ((this.field_223 instanceof class_1256)) {
          if (this.jdField_field_223_of_type_Class_1041.getLocalAndRemoteObjectContainer().getLocalObjects().containsKey(((class_1256)this.field_223).a11())) {
            ((class_1256)localclass_983).a12(((class_1256)this.field_223).a11());
          } else {
            ((class_1256)this.field_223).a12(-1);
          }
        }
      }
    }
    else
    {
      localObject1 = new class_48();
      localObject2 = new class_48();
      localclass_48 = new class_48();
      try
      {
        localObject1 = a14(paramString, (class_48)localObject1);
        float f = -1.0F;
        for (int i = 0; i < 6; i++)
        {
          ((class_48)localObject2).b1((class_48)localObject1);
          ((class_48)localObject2).a1(org.schema.game.common.data.element.Element.DIRECTIONSi[i]);
          ((class_48)localObject2).c1(paramclass_48);
          ((class_48)localObject2).b3();
          if ((f < 0.0F) || (((class_48)localObject2).a4() < f))
          {
            localclass_48.b1((class_48)localObject1);
            localclass_48.a1(org.schema.game.common.data.element.Element.DIRECTIONSi[i]);
            f = ((class_48)localObject2).a4();
          }
        }
        if (f >= 0.0F) {
          a17(paramString, localclass_48, false);
        }
      }
      catch (EntityNotFountException localEntityNotFountException)
      {
        localEntityNotFountException.printStackTrace();
        return false;
      }
      catch (SQLException localSQLException)
      {
        localSQLException.printStackTrace();
        return false;
      }
      catch (Exception localException2)
      {
        localException2.printStackTrace();
        return false;
      }
    }
    return true;
  }
  
  public final void a13()
  {
    for (int i = 0; i < this.jdField_field_223_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.size(); i++) {
      try
      {
        a17((String)this.jdField_field_223_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.get(i), a14((String)this.jdField_field_223_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.get(i), new class_48()), true);
      }
      catch (EntityNotFountException localEntityNotFountException)
      {
        localEntityNotFountException;
      }
      catch (SQLException localSQLException)
      {
        localSQLException;
      }
      catch (Exception localException)
      {
        localException;
      }
    }
  }
  
  public final class_48 a14(String paramString, class_48 paramclass_48)
  {
    if (a16(paramString))
    {
      Object localObject = (SegmentController)this.jdField_field_223_of_type_Class_1041.c8().get(paramString);
      if ((localObject = this.jdField_field_223_of_type_Class_1041.a62().getSector(((SegmentController)localObject).getSectorId())) != null)
      {
        paramclass_48.b1(((class_670)localObject).field_136);
        return paramclass_48;
      }
    }
    if (this.jdField_field_223_of_type_ItUnimiDsiFastutilObjectsObject2ObjectOpenHashMap.containsKey(paramString))
    {
      paramclass_48.b1((class_48)this.jdField_field_223_of_type_ItUnimiDsiFastutilObjectsObject2ObjectOpenHashMap.get(paramString));
      return paramclass_48;
    }
    return this.jdField_field_223_of_type_Class_1041.a66().a7(paramString.split("_", 3)[2], paramclass_48);
  }
  
  public final boolean a15(int paramInt)
  {
    return a16((String)this.jdField_field_223_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.get(paramInt));
  }
  
  public final boolean a16(String paramString)
  {
    return this.jdField_field_223_of_type_Class_1041.c8().containsKey(paramString);
  }
  
  private void a17(String paramString, class_48 paramclass_48, boolean paramBoolean)
  {
    if (!a16(paramString))
    {
      if (!this.jdField_field_223_of_type_ItUnimiDsiFastutilObjectsObject2ObjectOpenHashMap.containsKey(paramString)) {
        this.jdField_field_223_of_type_ItUnimiDsiFastutilObjectsObject2ObjectOpenHashMap.put(paramString, new class_48());
      }
      ((class_48)this.jdField_field_223_of_type_ItUnimiDsiFastutilObjectsObject2ObjectOpenHashMap.get(paramString)).b1(paramclass_48);
      if (paramBoolean) {
        try
        {
          this.jdField_field_223_of_type_Class_1041.a66().a8(paramString.split("_", 3)[2], paramclass_48);
        }
        catch (SQLException localSQLException)
        {
          localSQLException;
        }
      }
      if (this.jdField_field_223_of_type_Class_1041.a62().isSectorLoaded(paramclass_48))
      {
        this.jdField_field_223_of_type_ItUnimiDsiFastutilObjectsObject2ObjectOpenHashMap.remove(paramString);
        System.err.println("[SIMULATION] SECTOR " + paramclass_48 + " IS LOADED: now LOADING " + paramString + "; " + this.jdField_field_223_of_type_Class_1041.c8().size());
        this.jdField_field_223_of_type_Class_1041.a62().getSector(paramclass_48).a66(this.jdField_field_223_of_type_Class_1041, paramString);
      }
    }
  }
  
  public final ObjectArrayList a18()
  {
    return this.jdField_field_223_of_type_ItUnimiDsiFastutilObjectsObjectArrayList;
  }
  
  public final class_1041 a19()
  {
    return this.jdField_field_223_of_type_Class_1041;
  }
  
  public final class_48 a20()
  {
    return this.jdField_field_223_of_type_Class_48;
  }
  
  public final void b2()
  {
    for (int i = 0; i < this.jdField_field_223_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.size(); i++)
    {
      Object localObject;
      if (a15(i))
      {
        localObject = null;
        ((SegmentController)this.jdField_field_223_of_type_Class_1041.c8().get(Integer.valueOf(i))).markedForPermanentDelete(true);
      }
      else
      {
        localObject = null;
        String str = (String)this.jdField_field_223_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.get(i);
        localObject = this;
        if ((!jdField_field_223_of_type_Boolean) && (!((class_989)localObject).field_224)) {
          throw new AssertionError();
        }
        localObject = ((class_1063)localObject).jdField_field_223_of_type_Class_1041;
        new StringBuilder().append(class_1041.field_144).append(str).toString();
        try
        {
          ((class_1041)localObject).a66().b1(str);
          File localFile = new File(class_1041.field_144 + str + ".ent");
          System.err.println("[SERVER][SEGMENTCONTROLLER] PERMANENTLY DELETING ENTITY: " + localFile.getName());
          localFile.delete();
          ((class_1041)localObject).getThreadQueue().enqueue(new class_1061(str));
        }
        catch (SQLException localSQLException)
        {
          localSQLException;
        }
      }
    }
  }
  
  public void a7(class_748 paramclass_748) {}
  
  public String getUniqueIdentifier()
  {
    return null;
  }
  
  public boolean isVolatile()
  {
    return false;
  }
  
  public void fromTagStructure(class_69 paramclass_69)
  {
    ((Byte)(paramclass_69 = (class_69[])paramclass_69.a4())[0].a4()).byteValue();
    int i = ((Integer)paramclass_69[1].a4()).intValue();
    if ((!jdField_field_223_of_type_Boolean) && (i != a10().ordinal())) {
      throw new AssertionError(i + " / " + a10().ordinal());
    }
    Object localObject = (class_69[])paramclass_69[2].a4();
    for (int j = 0; (j < localObject.length) && (localObject[j].a3() != class_79.field_548); j++) {
      this.jdField_field_223_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.add((String)localObject[j].a4());
    }
    this.jdField_field_223_of_type_Long = ((Long)paramclass_69[3].a4()).longValue();
    this.jdField_field_223_of_type_Class_48 = ((class_48)paramclass_69[4].a4());
    if ((j = ((Integer)paramclass_69[5].a4()).intValue()) >= 0) {
      try
      {
        localObject = class_1112.a(class_1112.values()[j], this);
        this.field_223 = ((class_983)localObject);
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        localIllegalArgumentException;
      }
      catch (SecurityException localSecurityException)
      {
        localSecurityException;
      }
      catch (InstantiationException localInstantiationException)
      {
        localInstantiationException;
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        localIllegalAccessException;
      }
      catch (InvocationTargetException localInvocationTargetException)
      {
        localInvocationTargetException;
      }
      catch (NoSuchMethodException localNoSuchMethodException)
      {
        localNoSuchMethodException;
      }
    }
    a9(paramclass_69[6]);
  }
  
  public class_69 toTagStructure()
  {
    System.err.println("[SIM][TAG] WRITING GROUP " + this + "; " + this.jdField_field_223_of_type_ItUnimiDsiFastutilObjectsObjectArrayList);
    class_69 localclass_691 = new class_69(class_79.field_549, null, Byte.valueOf((byte)1));
    if ((!jdField_field_223_of_type_Boolean) && (!getClass().isInstance(a10().field_1289.a(this.jdField_field_223_of_type_Class_1041)))) {
      throw new AssertionError();
    }
    class_69 localclass_692 = new class_69(class_79.field_551, null, Integer.valueOf(a10().ordinal()));
    Object localObject = new class_69[this.jdField_field_223_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.size() + 1];
    for (int i = 0; i < this.jdField_field_223_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.size(); i++) {
      localObject[i] = new class_69(class_79.field_556, null, this.jdField_field_223_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.get(i));
    }
    localObject[this.jdField_field_223_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.size()] = new class_69(class_79.field_548, null, null);
    class_69 localclass_693 = new class_69(class_79.field_561, null, (class_69[])localObject);
    localObject = new class_69(class_79.field_552, null, Long.valueOf(this.jdField_field_223_of_type_Long));
    class_69 localclass_694 = new class_69(class_79.field_558, null, this.jdField_field_223_of_type_Class_48);
    class_69 localclass_695 = new class_69(class_79.field_551, null, Integer.valueOf(this.field_223 != null ? class_1112.a1(this).ordinal() : -1));
    class_69 localclass_696 = a8();
    return new class_69(class_79.field_561, null, new class_69[] { localclass_691, localclass_692, localclass_693, localObject, localclass_694, localclass_695, localclass_696, new class_69(class_79.field_548, null, null) });
  }
  
  protected class_69 a8()
  {
    return new class_69(class_79.field_549, null, Byte.valueOf((byte)0));
  }
  
  protected void a9(class_69 paramclass_69) {}
  
  public final void a21(class_797 paramclass_797)
  {
    System.err.println("[SIM] AGRRRO from " + paramclass_797);
    if ((this.field_223 != null) && ((this.field_223 instanceof class_1256))) {
      ((class_1256)this.field_223).a12(paramclass_797.getId());
    }
    for (paramclass_797 = 0; paramclass_797 < this.jdField_field_223_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.size(); paramclass_797++)
    {
      Object localObject = (String)this.jdField_field_223_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.get(paramclass_797);
      if ((a16((String)localObject)) && (((localObject = (SegmentController)this.jdField_field_223_of_type_Class_1041.c8().get(localObject)) instanceof class_991)) && (((localObject = ((class_991)localObject).a().a().a3()) instanceof class_1256)) && ((this.field_223 instanceof class_1256))) {
        if (this.jdField_field_223_of_type_Class_1041.getLocalAndRemoteObjectContainer().getLocalObjects().containsKey(((class_1256)this.field_223).a11())) {
          ((class_1256)localObject).a12(((class_1256)this.field_223).a11());
        } else {
          ((class_1256)this.field_223).a12(-1);
        }
      }
    }
  }
  
  public void c()
  {
    for (int i = 0; i < this.jdField_field_223_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.size(); i++)
    {
      Object localObject = (String)this.jdField_field_223_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.get(i);
      if ((a16((String)localObject)) && (((localObject = (SegmentController)this.jdField_field_223_of_type_Class_1041.c8().get(localObject)) instanceof class_991)) && (((localObject = ((class_991)localObject).a().a().a3()) instanceof class_1256)) && ((this.field_223 instanceof class_1256))) {
        if (this.jdField_field_223_of_type_Class_1041.getLocalAndRemoteObjectContainer().getLocalObjects().containsKey(((class_1256)this.field_223).a11())) {
          ((class_1256)localObject).a12(((class_1256)this.field_223).a11());
        } else {
          ((class_1256)this.field_223).a12(-1);
        }
      }
    }
  }
  
  public final String a22()
  {
    return toString();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1063
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */