import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.schema.game.common.controller.database.DatabaseIndex;
import org.schema.game.common.data.world.Universe;
import org.schema.game.server.controller.GameServerController;
import org.schema.schine.network.server.ServerState;

public final class class_1102
  extends Thread
{
  private final class_1098 jdField_field_1370_of_type_Class_1098;
  private final class_1041 jdField_field_1370_of_type_Class_1041;
  private final Random jdField_field_1370_of_type_JavaUtilRandom;
  private final ArrayList jdField_field_1370_of_type_JavaUtilArrayList = new ArrayList();
  
  public class_1102(class_1098 paramclass_1098)
  {
    super("SimPlanner");
    this.jdField_field_1370_of_type_Class_1098 = paramclass_1098;
    this.jdField_field_1370_of_type_Class_1041 = paramclass_1098.a215();
    this.jdField_field_1370_of_type_JavaUtilRandom = new Random();
  }
  
  private void a(String paramString)
  {
    if (class_1057.field_1330.a4()) {
      this.jdField_field_1370_of_type_Class_1041.a59().broadcastMessage(paramString, 1);
    }
  }
  
  public final void run()
  {
    while (!ServerState.shutdown)
    {
      try
      {
        Thread.sleep(((Integer)class_1057.field_1322.a3()).intValue() * 1000);
      }
      catch (InterruptedException localInterruptedException)
      {
        localInterruptedException;
      }
      int i = this.jdField_field_1370_of_type_JavaUtilArrayList.size();
      if ((class_1057.field_1319.a4()) && (i > 0)) {
        try
        {
          class_1102 localclass_1102 = this;
          class_748 localclass_748;
          if ((localclass_748 = a1()) != null)
          {
            System.err.println("[SIMPLANNER] random player: " + localclass_748);
            class_48 localclass_481 = localclass_748.a44();
            if (localclass_1102.jdField_field_1370_of_type_Class_1041.a62().getSector(localclass_748.c2()) != null)
            {
              boolean bool = false;
              class_48 localclass_482 = null;
              Object localObject1 = new class_48(localclass_481.field_475 - 4, localclass_481.field_476 - 4, localclass_481.field_477 - 4);
              class_48 localclass_483 = new class_48(localclass_481.field_475 + 4, localclass_481.field_476 + 4, localclass_481.field_477 + 4);
              int[] arrayOfInt = { 1, 2 };
              localObject1 = localclass_1102.jdField_field_1370_of_type_Class_1041.a66().a10((class_48)localObject1, localclass_483, arrayOfInt);
              int j = 0;
              Object localObject2;
              for (int k = 0; k < ((List)localObject1).size(); k++)
              {
                if (((localObject2 = (class_757)((List)localObject1).get(k)).jdField_field_1017_of_type_Int == 2) && (((class_757)localObject2).field_1018 > 0))
                {
                  j = ((class_757)localObject2).field_1018;
                  break;
                }
                if ((((class_757)localObject2).jdField_field_1017_of_type_Int == 1) && (((class_757)localObject2).jdField_field_1017_of_type_Class_48.equals(localclass_481)))
                {
                  bool = true;
                  break;
                }
              }
              localclass_1102.a("In safe zone: " + bool + "\nsectors: " + ((List)localObject1).size());
              for (k = 0; k < ((List)localObject1).size(); k++)
              {
                localObject2 = (class_757)((List)localObject1).get(k);
                if (!localclass_1102.jdField_field_1370_of_type_Class_1041.a62().isSectorLoaded(((class_757)localObject2).jdField_field_1017_of_type_Class_48))
                {
                  if ((!bool) && (localclass_1102.jdField_field_1370_of_type_JavaUtilRandom.nextInt(22) == 0) && (((class_757)localObject2).jdField_field_1017_of_type_Int == 2) && (((class_757)localObject2).field_1019 == class_780.field_1039.ordinal()))
                  {
                    localclass_1102.a("Spawning Pirate Scan\nBase: " + ((class_757)localObject2).jdField_field_1017_of_type_Class_48);
                    localclass_1102.jdField_field_1370_of_type_Class_1098.a208(new class_977(new class_48(((class_757)localObject2).jdField_field_1017_of_type_Class_48), localclass_1102.jdField_field_1370_of_type_JavaUtilRandom.nextInt(5) + 1));
                  }
                  if ((localclass_1102.jdField_field_1370_of_type_JavaUtilRandom.nextInt(22) == 0) && (((class_757)localObject2).jdField_field_1017_of_type_Int == 1)) {
                    if (localclass_482 == null)
                    {
                      localclass_482 = new class_48(((class_757)localObject2).jdField_field_1017_of_type_Class_48);
                    }
                    else
                    {
                      localclass_1102.a("Spawning Trading Route\n" + ((class_757)localObject2).jdField_field_1017_of_type_Class_48 + " -> " + localclass_482);
                      localclass_1102.jdField_field_1370_of_type_Class_1098.a208(new class_979(new class_48(((class_757)localObject2).jdField_field_1017_of_type_Class_48), localclass_482, 3));
                      localclass_482 = new class_48(((class_757)localObject2).jdField_field_1017_of_type_Class_48);
                    }
                  }
                }
                else if ((localclass_1102.jdField_field_1370_of_type_JavaUtilRandom.nextInt(20) == 0) && (((class_757)localObject2).jdField_field_1017_of_type_Int == 1) && (localclass_482 != null))
                {
                  localclass_1102.a("Spawning Trading Route\n" + localclass_482 + " -> " + ((class_757)localObject2).jdField_field_1017_of_type_Class_48);
                  localclass_1102.jdField_field_1370_of_type_Class_1098.a208(new class_979(new class_48(localclass_482), new class_48(((class_757)localObject2).jdField_field_1017_of_type_Class_48), 3));
                }
              }
              class_48 localclass_484;
              if (localclass_1102.jdField_field_1370_of_type_JavaUtilRandom.nextInt(22) == 0)
              {
                localclass_484 = new class_48(localclass_1102.jdField_field_1370_of_type_JavaUtilRandom.nextInt(8) - 4, localclass_1102.jdField_field_1370_of_type_JavaUtilRandom.nextInt(8) - 4, localclass_1102.jdField_field_1370_of_type_JavaUtilRandom.nextInt(8) - 4);
                localObject2 = new class_48(localclass_1102.jdField_field_1370_of_type_JavaUtilRandom.nextInt(8) - 4, localclass_1102.jdField_field_1370_of_type_JavaUtilRandom.nextInt(8) - 4, localclass_1102.jdField_field_1370_of_type_JavaUtilRandom.nextInt(8) - 4);
                localclass_484.a1(localclass_481);
                if (!localclass_1102.jdField_field_1370_of_type_Class_1041.a62().isSectorLoaded(localclass_484))
                {
                  localclass_1102.a("Spawning Trading Patrol");
                  localclass_1102.jdField_field_1370_of_type_Class_1098.a208(new class_979(localclass_484, (class_48)localObject2, localclass_1102.jdField_field_1370_of_type_JavaUtilRandom.nextInt(4) + 1));
                }
              }
              if ((!bool) && (j <= 0) && (localclass_1102.jdField_field_1370_of_type_JavaUtilRandom.nextInt(31) == 0))
              {
                localclass_484 = new class_48(localclass_1102.jdField_field_1370_of_type_JavaUtilRandom.nextInt(8) - 4, localclass_1102.jdField_field_1370_of_type_JavaUtilRandom.nextInt(8) - 4, localclass_1102.jdField_field_1370_of_type_JavaUtilRandom.nextInt(8) - 4);
                localObject2 = new class_48(localclass_1102.jdField_field_1370_of_type_JavaUtilRandom.nextInt(8) - 4, localclass_1102.jdField_field_1370_of_type_JavaUtilRandom.nextInt(8) - 4, localclass_1102.jdField_field_1370_of_type_JavaUtilRandom.nextInt(8) - 4);
                localclass_484.a1(localclass_481);
                if (!localclass_1102.jdField_field_1370_of_type_Class_1041.a62().isSectorLoaded(localclass_484))
                {
                  localclass_1102.a("Spawning Pirate Patrol");
                  localclass_1102.jdField_field_1370_of_type_Class_1098.a208(new class_975(localclass_484, (class_48)localObject2, localclass_1102.jdField_field_1370_of_type_JavaUtilRandom.nextInt(4) + 1));
                }
              }
              if ((!bool) && (j <= 0) && (localclass_1102.jdField_field_1370_of_type_JavaUtilRandom.nextInt(230) == 0))
              {
                (localclass_484 = new class_48(localclass_1102.jdField_field_1370_of_type_JavaUtilRandom.nextInt(8) - 4, localclass_1102.jdField_field_1370_of_type_JavaUtilRandom.nextInt(8) - 4, localclass_1102.jdField_field_1370_of_type_JavaUtilRandom.nextInt(8) - 4)).a1(localclass_481);
                if (!localclass_1102.jdField_field_1370_of_type_Class_1041.a62().isSectorLoaded(localclass_484))
                {
                  localclass_1102.a("Spawning Battle");
                  localclass_1102.jdField_field_1370_of_type_Class_1098.a208(new class_979(localclass_484, localclass_748.a44(), localclass_1102.jdField_field_1370_of_type_JavaUtilRandom.nextInt(4) + 10));
                  localclass_1102.jdField_field_1370_of_type_Class_1098.a208(new class_975(localclass_484, localclass_748.a44(), localclass_1102.jdField_field_1370_of_type_JavaUtilRandom.nextInt(4) + 10));
                }
              }
            }
          }
        }
        catch (Exception localException)
        {
          localException;
        }
      }
    }
  }
  
  private class_748 a1()
  {
    synchronized (this.jdField_field_1370_of_type_JavaUtilArrayList)
    {
      if (this.jdField_field_1370_of_type_JavaUtilArrayList.isEmpty()) {
        return null;
      }
      class_748 localclass_7481 = (class_748)this.jdField_field_1370_of_type_JavaUtilArrayList.get(this.jdField_field_1370_of_type_JavaUtilRandom.nextInt(this.jdField_field_1370_of_type_JavaUtilArrayList.size()));
    }
    return localclass_7482;
  }
  
  public final void a2(class_748 paramclass_748)
  {
    synchronized (this.jdField_field_1370_of_type_JavaUtilArrayList)
    {
      this.jdField_field_1370_of_type_JavaUtilArrayList.remove(paramclass_748);
      return;
    }
  }
  
  public final void b(class_748 paramclass_748)
  {
    synchronized (this.jdField_field_1370_of_type_JavaUtilArrayList)
    {
      this.jdField_field_1370_of_type_JavaUtilArrayList.add(paramclass_748);
      return;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1102
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */