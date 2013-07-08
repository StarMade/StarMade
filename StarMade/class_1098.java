import it.unimi.dsi.fastutil.objects.ObjectArrayFIFOQueue;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.world.Universe;
import org.schema.game.server.controller.EntityNotFountException;
import org.schema.game.server.controller.GameServerController;

public class class_1098
  implements class_80
{
  private final ObjectArrayList jdField_field_136_of_type_ItUnimiDsiFastutilObjectsObjectArrayList = new ObjectArrayList();
  private final class_1041 jdField_field_136_of_type_Class_1041;
  private long jdField_field_136_of_type_Long = 0L;
  private long field_139 = 0L;
  private long field_182 = 0L;
  private final class_1102 jdField_field_136_of_type_Class_1102;
  private final ObjectArrayFIFOQueue jdField_field_136_of_type_ItUnimiDsiFastutilObjectsObjectArrayFIFOQueue = new ObjectArrayFIFOQueue();
  
  public class_1098(class_1041 paramclass_1041)
  {
    this.jdField_field_136_of_type_Class_1041 = paramclass_1041;
    this.jdField_field_136_of_type_Long = System.currentTimeMillis();
    this.field_182 = this.jdField_field_136_of_type_Long;
    this.jdField_field_136_of_type_Class_1102 = new class_1102(this);
  }
  
  public final void a13()
  {
    try
    {
      class_69 localclass_69 = this.jdField_field_136_of_type_Class_1041.a59().a46("SIMULATION_STATE", "sim");
      fromTagStructure(localclass_69);
    }
    catch (IOException localIOException)
    {
      localIOException;
    }
    catch (EntityNotFountException localEntityNotFountException)
    {
      System.err.println("[SIMULATION] no simulation state found on disk. creating new...");
    }
    this.jdField_field_136_of_type_Class_1102.start();
  }
  
  public final void a208(class_973 paramclass_973)
  {
    synchronized (this.jdField_field_136_of_type_ItUnimiDsiFastutilObjectsObjectArrayFIFOQueue)
    {
      this.jdField_field_136_of_type_ItUnimiDsiFastutilObjectsObjectArrayFIFOQueue.enqueue(paramclass_973);
      return;
    }
  }
  
  public final void a209(class_48 paramclass_48, int paramInt)
  {
    if (a19(paramclass_48))
    {
      System.err.println("[SIM] cannot spawn group: collision at " + paramclass_48);
      return;
    }
    synchronized (this.jdField_field_136_of_type_ItUnimiDsiFastutilObjectsObjectArrayList)
    {
      if (this.jdField_field_136_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.size() >= ((Integer)class_1057.field_1320.a3()).intValue())
      {
        System.err.println("[SIM] cannot spawn group: LIMIT REACHED " + this.jdField_field_136_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.size());
        return;
      }
    }
    (??? = new class_1069(this.jdField_field_136_of_type_Class_1041)).a23(paramclass_48, -1, a212(paramInt));
    ((class_989)???).field_223 = new class_1242((class_1063)???);
    b41((class_1063)???);
  }
  
  public final boolean a19(class_48 paramclass_48)
  {
    class_48 localclass_48 = new class_48();
    for (int i = 0; i < this.jdField_field_136_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.size(); i++)
    {
      class_1063 localclass_1063;
      Iterator localIterator = (localclass_1063 = (class_1063)this.jdField_field_136_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.get(i)).a18().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        try
        {
          if (localclass_1063.a14(str, localclass_48).equals(paramclass_48)) {
            return true;
          }
        }
        catch (EntityNotFountException localEntityNotFountException)
        {
          localEntityNotFountException.printStackTrace();
          System.err.println("Exception REMOVING GROUP NOW " + localclass_1063.a18());
          this.jdField_field_136_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.remove(i);
          i--;
          break;
        }
        catch (SQLException localSQLException)
        {
          localSQLException;
        }
      }
    }
    return false;
  }
  
  private void b41(class_1063 paramclass_1063)
  {
    synchronized (this.jdField_field_136_of_type_ItUnimiDsiFastutilObjectsObjectArrayList)
    {
      if (this.jdField_field_136_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.size() < ((Integer)class_1057.field_1320.a3()).intValue()) {
        this.jdField_field_136_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.add(paramclass_1063);
      } else {
        System.err.println("[SIMUALTION] WARNING: Simulation group " + paramclass_1063 + " ignored: MAX GROUPS REACHED " + this.jdField_field_136_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.size() + "/" + (Integer)class_1057.field_1320.a3());
      }
      return;
    }
  }
  
  public final boolean a210(class_48 paramclass_481, class_48 paramclass_482, int paramInt)
  {
    if (a19(paramclass_481))
    {
      System.err.println("[SIM] cannot spawn group: collision at " + paramclass_481);
      return false;
    }
    synchronized (this.jdField_field_136_of_type_ItUnimiDsiFastutilObjectsObjectArrayList)
    {
      if (this.jdField_field_136_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.size() >= ((Integer)class_1057.field_1320.a3()).intValue())
      {
        System.err.println("[SIM] cannot spawn group: LIMIT REACHED " + this.jdField_field_136_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.size());
        return false;
      }
    }
    ??? = new class_1023(this.jdField_field_136_of_type_Class_1041, new class_48(paramclass_482));
    if (class_1057.field_1323.a4()) {
      ((class_1023)???).field_223 = true;
    }
    ((class_1023)???).a23(paramclass_481, -2, a212(paramInt));
    paramclass_481 = new class_1167((class_1063)???);
    ((class_989)???).field_223 = paramclass_481;
    b41((class_1063)???);
    return true;
  }
  
  public final boolean b42(class_48 paramclass_481, class_48 paramclass_482, int paramInt)
  {
    if (a19(paramclass_481))
    {
      System.err.println("[SIM] cannot spawn group: collision at " + paramclass_481);
      return false;
    }
    synchronized (this.jdField_field_136_of_type_ItUnimiDsiFastutilObjectsObjectArrayList)
    {
      if (this.jdField_field_136_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.size() >= ((Integer)class_1057.field_1320.a3()).intValue())
      {
        System.err.println("[SIM] cannot spawn group: LIMIT REACHED " + this.jdField_field_136_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.size());
        return false;
      }
    }
    (??? = new class_1023(this.jdField_field_136_of_type_Class_1041, new class_48(paramclass_482))).a23(paramclass_481, -1, a212(paramInt));
    ((class_989)???).field_223 = new class_1167((class_1063)???);
    b41((class_1063)???);
    return true;
  }
  
  public final class_1063 a211(class_797 paramclass_797)
  {
    synchronized (this.jdField_field_136_of_type_ItUnimiDsiFastutilObjectsObjectArrayList)
    {
      if (this.jdField_field_136_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.size() >= ((Integer)class_1057.field_1320.a3()).intValue())
      {
        System.err.println("[SIM] cannot spawn group: LIMIT REACHED " + this.jdField_field_136_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.size());
        return null;
      }
    }
    if ((??? = this.jdField_field_136_of_type_Class_1041.a62().getSector(paramclass_797.getSectorId())) != null)
    {
      class_1104 localclass_1104 = new class_1104(this.jdField_field_136_of_type_Class_1041, new class_48(((class_670)???).field_136), paramclass_797.getUniqueIdentifier());
      ??? = a192(((class_670)???).field_136, new class_48());
      if (a19((class_48)???))
      {
        System.err.println("[SIM] cannot spawn group: collision at " + ???);
        return null;
      }
      localclass_1104.a23((class_48)???, -2, a212(5));
      (??? = new class_1167(localclass_1104)).a12(paramclass_797.getId());
      localclass_1104.field_223 = ((class_983)???);
      b41(localclass_1104);
      return localclass_1104;
    }
    return null;
  }
  
  private class_781[] a212(int paramInt)
  {
    Object localObject = this.jdField_field_136_of_type_Class_1041.a53().a101();
    ArrayList localArrayList = new ArrayList();
    localObject = ((Collection)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      class_781 localclass_781;
      if ((localclass_781 = (class_781)((Iterator)localObject).next()).d10()) {
        localArrayList.add(localclass_781);
      }
    }
    if (localArrayList.isEmpty())
    {
      System.err.println("[WAVE] Server will not spawn any waves, the catalog is empty");
      return new class_781[0];
    }
    Collections.sort(localArrayList, new class_1100());
    float f = localArrayList.size() / 10.0F;
    int i = (int)Math.min(localArrayList.size() - 1, Math.ceil(f));
    class_781[] arrayOfclass_781 = new class_781[paramInt];
    for (int j = 0; j < paramInt; j++)
    {
      int k = Math.min(localArrayList.size() - 1, Math.max(0, i - 2 + j));
      arrayOfclass_781[j] = ((class_781)localArrayList.get(k));
    }
    return arrayOfclass_781;
  }
  
  public final void b4()
  {
    if (!this.jdField_field_136_of_type_ItUnimiDsiFastutilObjectsObjectArrayFIFOQueue.isEmpty()) {
      synchronized (this.jdField_field_136_of_type_ItUnimiDsiFastutilObjectsObjectArrayFIFOQueue)
      {
        while (!this.jdField_field_136_of_type_ItUnimiDsiFastutilObjectsObjectArrayFIFOQueue.isEmpty()) {
          ((class_973)this.jdField_field_136_of_type_ItUnimiDsiFastutilObjectsObjectArrayFIFOQueue.dequeue()).a(this);
        }
      }
    }
    long l = System.currentTimeMillis();
    this.field_139 += l - this.field_182;
    int j = (int)(this.field_139 / 30L);
    this.field_139 %= 30L;
    this.field_182 = l;
    if (j > 0) {
      for (int i = 0; i < this.jdField_field_136_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.size(); localObject1++)
      {
        if (((class_1063)this.jdField_field_136_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.get(i)).a18().isEmpty()) {
          synchronized (this.jdField_field_136_of_type_ItUnimiDsiFastutilObjectsObjectArrayList)
          {
            System.err.println("[SIMULATION] Removing Sim Group for lack of members");
            this.jdField_field_136_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.remove(i);
            i--;
          }
        }
        ((class_1063)this.jdField_field_136_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.get(localObject1)).a11(j);
      }
    }
  }
  
  public final void a213(class_1063 paramclass_1063)
  {
    paramclass_1063.b2();
    class_1063 localclass_1063 = paramclass_1063;
    paramclass_1063 = this;
    synchronized (this.jdField_field_136_of_type_ItUnimiDsiFastutilObjectsObjectArrayList)
    {
      paramclass_1063.jdField_field_136_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.remove(localclass_1063);
      return;
    }
  }
  
  public final void a106(String paramString)
  {
    for (int i = 0; i < this.jdField_field_136_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.size(); i++) {
      ((class_1063)this.jdField_field_136_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.get(i)).a18().remove(paramString);
    }
  }
  
  private class_48 a192(class_48 paramclass_481, class_48 paramclass_482)
  {
    for (int i = 2; this.jdField_field_136_of_type_Class_1041.a62().isSectorLoaded(paramclass_482); i++)
    {
      int j;
      if ((j = Universe.getRandom().nextInt(3)) == 0)
      {
        paramclass_482.b(paramclass_481.field_475 + i, paramclass_481.field_476, paramclass_481.field_477);
        if (!this.jdField_field_136_of_type_Class_1041.a62().isSectorLoaded(paramclass_482)) {
          break;
        }
        paramclass_482.b(paramclass_481.field_475 - i, paramclass_481.field_476, paramclass_481.field_477);
        if (!this.jdField_field_136_of_type_Class_1041.a62().isSectorLoaded(paramclass_482)) {
          break;
        }
        paramclass_482.b(paramclass_481.field_475, paramclass_481.field_476 + i, paramclass_481.field_477);
        if (!this.jdField_field_136_of_type_Class_1041.a62().isSectorLoaded(paramclass_482)) {
          break;
        }
        paramclass_482.b(paramclass_481.field_475, paramclass_481.field_476 - i, paramclass_481.field_477);
        if (!this.jdField_field_136_of_type_Class_1041.a62().isSectorLoaded(paramclass_482)) {
          break;
        }
        paramclass_482.b(paramclass_481.field_475, paramclass_481.field_476, paramclass_481.field_477 + i);
        if (!this.jdField_field_136_of_type_Class_1041.a62().isSectorLoaded(paramclass_482)) {
          break;
        }
        paramclass_482.b(paramclass_481.field_475, paramclass_481.field_476, paramclass_481.field_477 - i);
      }
      else if (j == 1)
      {
        paramclass_482.b(paramclass_481.field_475, paramclass_481.field_476 + i, paramclass_481.field_477);
        if (!this.jdField_field_136_of_type_Class_1041.a62().isSectorLoaded(paramclass_482)) {
          break;
        }
        paramclass_482.b(paramclass_481.field_475, paramclass_481.field_476 - i, paramclass_481.field_477);
        if (!this.jdField_field_136_of_type_Class_1041.a62().isSectorLoaded(paramclass_482)) {
          break;
        }
        paramclass_482.b(paramclass_481.field_475 + i, paramclass_481.field_476, paramclass_481.field_477);
        if (!this.jdField_field_136_of_type_Class_1041.a62().isSectorLoaded(paramclass_482)) {
          break;
        }
        paramclass_482.b(paramclass_481.field_475 - i, paramclass_481.field_476, paramclass_481.field_477);
        if (!this.jdField_field_136_of_type_Class_1041.a62().isSectorLoaded(paramclass_482)) {
          break;
        }
        paramclass_482.b(paramclass_481.field_475, paramclass_481.field_476, paramclass_481.field_477 + i);
        if (!this.jdField_field_136_of_type_Class_1041.a62().isSectorLoaded(paramclass_482)) {
          break;
        }
        paramclass_482.b(paramclass_481.field_475, paramclass_481.field_476, paramclass_481.field_477 - i);
      }
      else
      {
        paramclass_482.b(paramclass_481.field_475, paramclass_481.field_476, paramclass_481.field_477 + i);
        if (!this.jdField_field_136_of_type_Class_1041.a62().isSectorLoaded(paramclass_482)) {
          break;
        }
        paramclass_482.b(paramclass_481.field_475, paramclass_481.field_476, paramclass_481.field_477 - i);
        if (!this.jdField_field_136_of_type_Class_1041.a62().isSectorLoaded(paramclass_482)) {
          break;
        }
        paramclass_482.b(paramclass_481.field_475 + i, paramclass_481.field_476, paramclass_481.field_477);
        if (!this.jdField_field_136_of_type_Class_1041.a62().isSectorLoaded(paramclass_482)) {
          break;
        }
        paramclass_482.b(paramclass_481.field_475 - i, paramclass_481.field_476, paramclass_481.field_477);
        if (!this.jdField_field_136_of_type_Class_1041.a62().isSectorLoaded(paramclass_482)) {
          break;
        }
        paramclass_482.b(paramclass_481.field_475, paramclass_481.field_476 + i, paramclass_481.field_477);
        if (!this.jdField_field_136_of_type_Class_1041.a62().isSectorLoaded(paramclass_482)) {
          break;
        }
        paramclass_482.b(paramclass_481.field_475, paramclass_481.field_476 - i, paramclass_481.field_477);
      }
    }
    return paramclass_482;
  }
  
  public String getUniqueIdentifier()
  {
    return "SIMULATION_STATE";
  }
  
  public boolean isVolatile()
  {
    return false;
  }
  
  public final void c1()
  {
    synchronized (this.jdField_field_136_of_type_ItUnimiDsiFastutilObjectsObjectArrayList)
    {
      for (int i = 0; i < this.jdField_field_136_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.size(); i++) {
        ((class_1063)this.jdField_field_136_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.get(i)).a13();
      }
      return;
    }
  }
  
  public void fromTagStructure(class_69 paramclass_69)
  {
    ((Byte)(paramclass_69 = (class_69[])paramclass_69.a4())[0].a4()).byteValue();
    paramclass_69 = (class_69[])paramclass_69[1].a4();
    for (int i = 0; (i < paramclass_69.length) && (paramclass_69[i].a3() != class_79.field_548); i++) {
      synchronized (this.jdField_field_136_of_type_ItUnimiDsiFastutilObjectsObjectArrayList)
      {
        int j = ((Integer)((class_69[])paramclass_69[i].a4())[1].a4()).intValue();
        try
        {
          localclass_1063 = null;
          localclass_1063 = class_1015.values()[j].field_1289.a(this.jdField_field_136_of_type_Class_1041);
          System.err.println("[SIM] loading simulation group type: " + j + ": " + localclass_1063);
          localclass_1063.fromTagStructure(paramclass_69[i]);
          b41(localclass_1063);
        }
        catch (SecurityException localSecurityException)
        {
          localclass_1063 = null;
          localSecurityException.printStackTrace();
          if (!jdField_field_136_of_type_Boolean) {
            throw new AssertionError();
          }
        }
        catch (IllegalArgumentException localIllegalArgumentException)
        {
          class_1063 localclass_1063 = null;
          localIllegalArgumentException.printStackTrace();
        }
      }
    }
  }
  
  public class_69 toTagStructure()
  {
    class_69 localclass_69 = new class_69(class_79.field_549, null, Byte.valueOf((byte)0));
    Object localObject2;
    synchronized (this.jdField_field_136_of_type_ItUnimiDsiFastutilObjectsObjectArrayList)
    {
      localObject2 = new class_69[this.jdField_field_136_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.size() + 1];
      for (int i = 0; i < this.jdField_field_136_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.size(); i++) {
        localObject2[i] = ((class_1063)this.jdField_field_136_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.get(i)).toTagStructure();
      }
      localObject2[this.jdField_field_136_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.size()] = new class_69(class_79.field_548, null, null);
      localObject2 = new class_69(class_79.field_561, null, (class_69[])localObject2);
    }
    return new class_69(class_79.field_561, "SimulationState", new class_69[] { localObject1, localObject2, new class_69(class_79.field_548, null, null) });
  }
  
  public final void a214(class_747 paramclass_747, class_797 paramclass_797)
  {
    for (int i = 0; i < this.jdField_field_136_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.size(); i++)
    {
      class_1063 localclass_1063;
      if ((localclass_1063 = (class_1063)this.jdField_field_136_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.get(i)).a18().contains(paramclass_747.getUniqueIdentifier())) {
        localclass_1063.a21(paramclass_797);
      }
    }
  }
  
  public final class_1041 a215()
  {
    return this.jdField_field_136_of_type_Class_1041;
  }
  
  public final class_1102 a216()
  {
    return this.jdField_field_136_of_type_Class_1102;
  }
  
  public final String a217(SegmentController paramSegmentController)
  {
    for (int i = 0; i < this.jdField_field_136_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.size(); i++)
    {
      class_1063 localclass_1063;
      if ((localclass_1063 = (class_1063)this.jdField_field_136_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.get(i)).a18().contains(paramSegmentController.getUniqueIdentifier())) {
        return localclass_1063.a22();
      }
    }
    return "NOSIM";
  }
  
  public final void a168(SegmentController paramSegmentController)
  {
    if (paramSegmentController.getUniqueIdentifier().startsWith("ENTITY_SHIP_MOB_SIM")) {
      a106(paramSegmentController.getUniqueIdentifier());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1098
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */