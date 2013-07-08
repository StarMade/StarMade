import it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import org.schema.game.network.objects.NetworkClientChannel;
import org.schema.game.network.objects.remote.RemoteMapEntryAnswer;
import org.schema.game.network.objects.remote.RemoteMapEntryAnswerBuffer;

public final class class_343
{
  public final HashMap field_685;
  public final ArrayList field_685;
  public final ArrayList field_686 = new ArrayList();
  public class_45 field_685;
  public final Object2LongOpenHashMap field_685;
  
  public class_343(class_45 paramclass_45)
  {
    this.jdField_field_685_of_type_JavaUtilHashMap = new HashMap();
    this.jdField_field_685_of_type_JavaUtilArrayList = new ArrayList();
    this.jdField_field_685_of_type_ItUnimiDsiFastutilObjectsObject2LongOpenHashMap = new Object2LongOpenHashMap();
    this.jdField_field_685_of_type_Class_45 = paramclass_45;
  }
  
  public final void a(class_48 paramclass_48)
  {
    System.err.println("[CLIENT][MAPREQUESTMANAGER] requesting system map for " + paramclass_48);
    synchronized (this.jdField_field_685_of_type_JavaUtilArrayList)
    {
      this.jdField_field_685_of_type_JavaUtilArrayList.add(new class_341((byte)2, paramclass_48));
      return;
    }
  }
  
  public final void a1(NetworkClientChannel paramNetworkClientChannel)
  {
    for (int i = 0; i < paramNetworkClientChannel.mapAnswers.getReceiveBuffer().size(); i++)
    {
      class_339 localclass_339 = (class_339)((RemoteMapEntryAnswer)paramNetworkClientChannel.mapAnswers.getReceiveBuffer().get(i)).get();
      synchronized (this.field_686)
      {
        this.field_686.add(localclass_339);
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_343
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */