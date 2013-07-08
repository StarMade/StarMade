import com.bulletphysics.linearmath.Transform;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import javax.vecmath.Vector3f;
import org.schema.common.util.ByteUtil;
import org.schema.game.common.controller.database.DatabaseIndex;
import org.schema.game.common.data.world.Universe;
import org.schema.game.network.objects.NetworkClientChannel;
import org.schema.game.network.objects.remote.RemoteMapEntryAnswer;
import org.schema.game.network.objects.remote.RemoteMapEntryAnswerBuffer;
import org.schema.game.network.objects.remote.RemoteMapEntryRequest;
import org.schema.game.network.objects.remote.RemoteMapEntryRequestBuffer;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.objects.Sendable;

public class class_1220
  extends Thread
{
  private final class_1041 jdField_field_1434_of_type_Class_1041;
  private ArrayList jdField_field_1434_of_type_JavaUtilArrayList = new ArrayList();
  private ArrayList field_1435 = new ArrayList();
  
  public class_1220(class_1041 paramclass_1041)
  {
    this.jdField_field_1434_of_type_Class_1041 = paramclass_1041;
    start();
  }
  
  public void run()
  {
    for (;;)
    {
      synchronized (this.jdField_field_1434_of_type_JavaUtilArrayList)
      {
        if (this.jdField_field_1434_of_type_JavaUtilArrayList.isEmpty())
        {
          try
          {
            this.jdField_field_1434_of_type_JavaUtilArrayList.wait();
          }
          catch (InterruptedException localInterruptedException)
          {
            localInterruptedException;
          }
          continue;
        }
        class_1053 localclass_1053 = (class_1053)this.jdField_field_1434_of_type_JavaUtilArrayList.remove(0);
      }
      ??? = localObject1;
      class_1220 localclass_1220 = this;
      class_339 localclass_339 = new class_339(???.jdField_field_234_of_type_Byte, ???.jdField_field_234_of_type_Class_48);
      if (???.jdField_field_234_of_type_Byte == 3)
      {
        try
        {
          localclass_1220.jdField_field_1434_of_type_Class_1041.a66().a9(???.jdField_field_234_of_type_Class_48, 0);
        }
        catch (SQLException localSQLException1)
        {
          localSQLException1;
          if (!jdField_field_1434_of_type_Boolean) {
            throw new AssertionError();
          }
        }
      }
      else if (???.jdField_field_234_of_type_Byte == 2)
      {
        try
        {
          class_48 localclass_481 = new class_48(???.jdField_field_234_of_type_Class_48.field_475 << 4, ???.jdField_field_234_of_type_Class_48.field_476 << 4, ???.jdField_field_234_of_type_Class_48.field_477 << 4);
          class_48 localclass_482 = new class_48((???.jdField_field_234_of_type_Class_48.field_475 << 4) + 16, (???.jdField_field_234_of_type_Class_48.field_476 << 4) + 16, (???.jdField_field_234_of_type_Class_48.field_477 << 4) + 16);
          Object localObject4 = { 1, 2, 4 };
          localObject4 = localclass_1220.jdField_field_1434_of_type_Class_1041.a66().a10(localclass_481, localclass_482, (int[])localObject4);
          HashSet localHashSet = new HashSet(((List)localObject4).size());
          Object localObject6;
          synchronized (localclass_1220.jdField_field_1434_of_type_Class_1041.getLocalAndRemoteObjectContainer().getLocalObjects())
          {
            localObject5 = localclass_1220.jdField_field_1434_of_type_Class_1041.getLocalAndRemoteObjectContainer().getLocalObjects().values().iterator();
            while (((Iterator)localObject5).hasNext()) {
              if (((localObject6 = (Sendable)((Iterator)localObject5).next()) instanceof class_797))
              {
                localObject6 = (class_797)localObject6;
                class_670 localclass_670;
                if (((localclass_670 = localclass_1220.jdField_field_1434_of_type_Class_1041.a62().getSector(((class_797)localObject6).getSectorId())).field_136.field_475 >= localclass_481.field_475) && (localclass_670.field_136.field_476 >= localclass_481.field_476) && (localclass_670.field_136.field_477 >= localclass_481.field_477) && (localclass_670.field_136.field_475 < localclass_482.field_475) && (localclass_670.field_136.field_476 < localclass_482.field_476) && (localclass_670.field_136.field_477 < localclass_482.field_477) && (((localObject6 instanceof class_743)) || ((localObject6 instanceof class_737)) || ((localObject6 instanceof class_864))))
                {
                  Object localObject7 = null;
                  if ((localObject6 instanceof class_743)) {
                    (localObject7 = new class_337()).jdField_field_136_of_type_Byte = 1;
                  }
                  if ((localObject6 instanceof class_864))
                  {
                    (localObject7 = new class_349()).jdField_field_136_of_type_Byte = 4;
                    ((class_349)localObject7).field_136 = ((class_864)localObject6).a69();
                  }
                  if ((localObject6 instanceof class_737)) {
                    (localObject7 = new class_337()).jdField_field_136_of_type_Byte = 2;
                  }
                  ((class_337)localObject7).jdField_field_136_of_type_JavaxVecmathVector3f = a1(((class_797)localObject6).getWorldTransform().origin, localclass_670.field_136);
                  ((class_337)localObject7).jdField_field_136_of_type_JavaLangString = localObject6.getUniqueIdentifier().split("_")[2];
                  if ((!jdField_field_1434_of_type_Boolean) && (localObject7 == null)) {
                    throw new AssertionError();
                  }
                  localHashSet.add(localObject7);
                }
              }
            }
          }
          for (int i = 0; i < ((List)localObject4).size(); i++)
          {
            localObject5 = (class_757)((List)localObject4).get(i);
            localObject6 = null;
            switch (((class_757)localObject5).jdField_field_1017_of_type_Int)
            {
            case 2: 
              localObject6 = new class_337();
              break;
            case 4: 
              ((class_349)(localObject6 = new class_349())).field_136 = class_810.values()[localObject5.field_1019];
              break;
            case 5: 
              localObject6 = new class_337();
              break;
            case 1: 
              localObject6 = new class_337();
              break;
            case 3: 
              localObject6 = new class_337();
              break;
            default: 
              if (!jdField_field_1434_of_type_Boolean) {
                throw new AssertionError();
              }
              break;
            }
            ((class_337)localObject6).jdField_field_136_of_type_JavaxVecmathVector3f = a1(((class_757)localObject5).jdField_field_1017_of_type_JavaxVecmathVector3f, ((class_757)localObject5).jdField_field_1017_of_type_Class_48);
            ((class_337)localObject6).jdField_field_136_of_type_Byte = ((byte)((class_757)localObject5).jdField_field_1017_of_type_Int);
            ((class_337)localObject6).jdField_field_136_of_type_JavaLangString = localObject5.jdField_field_1017_of_type_JavaLangString.split("_")[2];
            localHashSet.add(localObject6);
          }
          localclass_339.field_683 = new class_337[localHashSet.size()];
          i = 0;
          Object localObject5 = localHashSet.iterator();
          while (((Iterator)localObject5).hasNext())
          {
            localObject6 = (class_337)((Iterator)localObject5).next();
            localclass_339.field_683[i] = localObject6;
            i++;
          }
        }
        catch (SQLException localSQLException2)
        {
          localSQLException2;
          if (!jdField_field_1434_of_type_Boolean) {
            throw new AssertionError();
          }
        }
      }
      else
      {
        System.err.println("[SERVER][GAMEMAP] request type " + ???.jdField_field_234_of_type_Byte + " is unknown");
        if (!jdField_field_1434_of_type_Boolean) {
          throw new AssertionError();
        }
        continue;
      }
      if ((!jdField_field_1434_of_type_Boolean) && (localclass_339.field_683 == null)) {
        throw new AssertionError();
      }
      synchronized (localclass_1220.field_1435)
      {
        localclass_1220.field_1435.add(new class_1218(???, new RemoteMapEntryAnswer(localclass_339, true)));
      }
    }
  }
  
  public final void a()
  {
    if (!this.field_1435.isEmpty()) {
      synchronized (this.field_1435)
      {
        while (!this.field_1435.isEmpty())
        {
          class_1218 localclass_1218;
          (localclass_1218 = (class_1218)this.field_1435.remove(0)).jdField_field_1432_of_type_Class_1053.jdField_field_234_of_type_Class_45.a().mapAnswers.add(localclass_1218.jdField_field_1432_of_type_OrgSchemaGameNetworkObjectsRemoteRemoteMapEntryAnswer);
        }
        return;
      }
    }
  }
  
  public static Vector3f a1(Vector3f paramVector3f, class_48 paramclass_48)
  {
    float f = Universe.getSectorSizeWithMargin();
    return new Vector3f((paramVector3f.field_615 + f / 2.0F) / f * 6.25F + ByteUtil.d(paramclass_48.field_475) / 16.0F * 100.0F, (paramVector3f.field_616 + f / 2.0F) / f * 6.25F + ByteUtil.d(paramclass_48.field_476) / 16.0F * 100.0F, (paramVector3f.field_617 + f / 2.0F) / f * 6.25F + ByteUtil.d(paramclass_48.field_477) / 16.0F * 100.0F);
  }
  
  public final void a2(class_45 paramclass_45)
  {
    for (int i = 0; i < paramclass_45.a().mapRequests.getReceiveBuffer().size(); i++)
    {
      class_341 localclass_341 = (class_341)((RemoteMapEntryRequest)paramclass_45.a().mapRequests.getReceiveBuffer().get(i)).get();
      synchronized (this.jdField_field_1434_of_type_JavaUtilArrayList)
      {
        this.jdField_field_1434_of_type_JavaUtilArrayList.add(new class_1053(localclass_341, paramclass_45));
        this.jdField_field_1434_of_type_JavaUtilArrayList.notify();
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1220
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */