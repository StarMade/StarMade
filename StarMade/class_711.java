import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.util.ArrayList;
import org.schema.game.common.controller.SegmentController;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.StateInterface;

final class class_711
  implements Runnable
{
  class_711(class_717 paramclass_717) {}
  
  public final void run()
  {
    try
    {
      for (;;)
      {
        class_717.a3(this.field_976);
        int i = 0;
        for (int j = 0; j < class_717.a4(this.field_976).size(); j++)
        {
          SegmentController localSegmentController;
          if (((localSegmentController = (SegmentController)class_717.a4(this.field_976).get(j)).getCreatorThread() != null) && (((class_371)class_717.a5(this.field_976)).a7().containsKey(localSegmentController.getId())))
          {
            class_908 localclass_908;
            if (System.currentTimeMillis() - localclass_908.jdField_field_195_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentProvider().field_14 > 10000L)
            {
              localclass_908.jdField_field_195_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentProvider().b1();
              localclass_908.jdField_field_195_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentProvider().field_14 = System.currentTimeMillis();
            }
            if ((((localclass_908 = localSegmentController.getCreatorThread()).jdField_field_195_of_type_Boolean) && (!class_933.a1()) ? localclass_908.jdField_field_195_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentProvider().b2() : 0))
            {
              i = 1;
              Thread.sleep(5L);
            }
          }
          try
          {
            if (!class_717.a5(this.field_976).getLocalAndRemoteObjectContainer().getLocalObjects().containsKey(localSegmentController.getId()))
            {
              class_717.a4(this.field_976).remove(j);
              j--;
            }
          }
          catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException)
          {
            localArrayIndexOutOfBoundsException;
          }
        }
        if (i == 0) {
          Thread.sleep(20L);
        }
      }
    }
    catch (InterruptedException localInterruptedException)
    {
      localInterruptedException;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_711
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */