import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.player.inventory.NoSlotFreeException;
import org.schema.schine.network.server.ServerMessage;

public class class_1023
  extends class_1067
{
  public class_48 field_224;
  public boolean field_223;
  
  public class_1023(class_1041 paramclass_1041, class_48 paramclass_48)
  {
    super(paramclass_1041);
    this.field_224 = paramclass_48;
  }
  
  public class_1023(class_1041 paramclass_1041)
  {
    super(paramclass_1041);
  }
  
  public final void c()
  {
    super.c();
    if (this.field_223)
    {
      class_1023 localclass_1023 = this;
      Iterator localIterator = this.jdField_field_223_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.iterator();
      while (localIterator.hasNext())
      {
        Object localObject = (String)localIterator.next();
        if ((localclass_1023.a16((String)localObject)) && ((localObject = (SegmentController)localclass_1023.jdField_field_223_of_type_Class_1041.c8().get(localObject)) != null) && ((localObject instanceof class_739)) && (!(localObject = (class_739)localObject).a().isEmpty()))
        {
          localObject = (class_743)((class_739)localObject).a().iterator().next();
          System.err.println("[SIMULATION] " + localclass_1023 + " filling stock of: " + localObject);
          try
          {
            ((class_743)localObject).a72(true);
          }
          catch (NoSlotFreeException localNoSlotFreeException)
          {
            localNoSlotFreeException;
          }
          localclass_1023.field_223 = false;
        }
      }
    }
  }
  
  public void a7(class_748 paramclass_748)
  {
    paramclass_748.a129(new ServerMessage("#### Transmission Start\nHostile identified...\nExterminate...\n#### Transmission End\n", 2, paramclass_748.getId()));
  }
  
  protected class_69 a8()
  {
    return new class_69(class_79.field_558, null, this.field_224);
  }
  
  protected void a9(class_69 paramclass_69)
  {
    this.field_224 = ((class_48)paramclass_69.a4());
    if (this.field_223 != null) {
      ((class_1256)this.field_223).a10(this.field_224);
    }
  }
  
  public class_1015 a10()
  {
    return class_1015.field_1289;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1023
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */