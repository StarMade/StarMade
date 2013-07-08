import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.io.PrintStream;
import org.schema.game.common.controller.SegmentController;

public class class_394
{
  public int field_787;
  private final ObjectArrayList jdField_field_787_of_type_ItUnimiDsiFastutilObjectsObjectArrayList = new ObjectArrayList(((Integer)class_949.field_1248.a4()).intValue());
  private final ObjectArrayList jdField_field_788_of_type_ItUnimiDsiFastutilObjectsObjectArrayList = new ObjectArrayList(((Integer)class_949.field_1248.a4()).intValue());
  private int jdField_field_788_of_type_Int = this.jdField_field_787_of_type_Int;
  
  public class_394()
  {
    this.jdField_field_787_of_type_Int = (((Integer)class_949.field_1248.a4()).intValue() + 10);
  }
  
  public static void a() {}
  
  public final void a1(int paramInt)
  {
    synchronized (this.jdField_field_787_of_type_ItUnimiDsiFastutilObjectsObjectArrayList)
    {
      for (int i = 0; i < this.jdField_field_788_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.size(); i++)
      {
        class_657 localclass_657;
        if (((localclass_657 = ((class_400)this.jdField_field_788_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.get(i)).jdField_field_794_of_type_Class_657) != null) && (localclass_657.a4() < paramInt - 5000) && (!localclass_657.c()))
        {
          if (!this.jdField_field_787_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.contains(this.jdField_field_788_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.get(i)))
          {
            localclass_657.c1();
            localclass_657.b3();
            localclass_657.b7(false);
            System.err.println("WARNING: Exception: cleaned up stuck mesh! " + localclass_657 + "; " + localclass_657.a15() + "; SegSector: " + localclass_657.a15().getSectorId() + "; CurrentSector " + ((class_371)localclass_657.a15().getState()).a8());
            i--;
          }
        }
        else if (localclass_657 == null) {
          System.err.println("WARNING: Exception: null mesh! ");
        }
      }
      return;
    }
  }
  
  public final void b()
  {
    synchronized (this.jdField_field_787_of_type_ItUnimiDsiFastutilObjectsObjectArrayList)
    {
      this.jdField_field_787_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.clear();
      return;
    }
  }
  
  private class_400 a2()
  {
    synchronized (this.jdField_field_787_of_type_ItUnimiDsiFastutilObjectsObjectArrayList)
    {
      if ((this.jdField_field_787_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.isEmpty()) && (this.jdField_field_788_of_type_Int > 0))
      {
        localclass_400 = new class_400();
        this.jdField_field_788_of_type_Int -= 1;
        this.jdField_field_788_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.add(localclass_400);
        return localclass_400;
      }
      while (this.jdField_field_787_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.isEmpty())
      {
        this.jdField_field_787_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.wait(5000L);
        if (this.jdField_field_787_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.isEmpty())
        {
          System.err.println("[CUBEMESH] WARNING: overquota " + this.jdField_field_787_of_type_Int);
          this.jdField_field_787_of_type_Int += 1;
          localclass_400 = new class_400();
          this.jdField_field_788_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.add(localclass_400);
          return localclass_400;
        }
      }
      class_400 localclass_400 = (class_400)this.jdField_field_787_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.remove(0);
      this.jdField_field_788_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.add(localclass_400);
      localclass_400.jdField_field_794_of_type_Boolean = false;
      if ((!jdField_field_787_of_type_Boolean) && (this.jdField_field_787_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.size() > this.jdField_field_787_of_type_Int)) {
        throw new AssertionError();
      }
      return localclass_400;
    }
  }
  
  public final class_400 a3(class_657 paramclass_657)
  {
    synchronized (this.jdField_field_787_of_type_ItUnimiDsiFastutilObjectsObjectArrayList)
    {
      class_400 localclass_400;
      (localclass_400 = a2()).jdField_field_794_of_type_Class_657 = paramclass_657;
      if ((!jdField_field_787_of_type_Boolean) && (localclass_400.jdField_field_794_of_type_Class_657 == null)) {
        throw new AssertionError();
      }
      return localclass_400;
    }
  }
  
  public final void a4(class_400 paramclass_400)
  {
    synchronized (this.jdField_field_787_of_type_ItUnimiDsiFastutilObjectsObjectArrayList)
    {
      if ((paramclass_400 != null) && (!this.jdField_field_787_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.contains(paramclass_400)))
      {
        this.jdField_field_787_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.add(paramclass_400);
        this.jdField_field_788_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.remove(paramclass_400);
        this.jdField_field_787_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.notify();
      }
      return;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_394
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */