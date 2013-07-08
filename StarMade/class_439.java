import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import org.schema.game.common.controller.EditableSendableSegmentController;
import org.schema.game.common.data.player.inventory.NoSlotFreeException;

final class class_439
  implements class_481
{
  class_439(class_443 paramclass_443, EditableSendableSegmentController paramEditableSendableSegmentController, IntOpenHashSet paramIntOpenHashSet) {}
  
  public final void a(short paramShort)
  {
    if ((paramShort != 0) && (paramShort != 1))
    {
      class_639 localclass_639 = ((class_371)this.jdField_field_160_of_type_OrgSchemaGameCommonControllerEditableSendableSegmentController.getState()).a20().getInventory(null);
      try
      {
        paramShort = localclass_639.b8(paramShort, 1);
        this.jdField_field_160_of_type_ItUnimiDsiFastutilIntsIntOpenHashSet.add(paramShort);
        return;
      }
      catch (NoSlotFreeException localNoSlotFreeException)
      {
        this.jdField_field_160_of_type_Class_443.a6().a4().b1("ERROR\nno free slot in inventory\nthe element is lost");
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_439
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */