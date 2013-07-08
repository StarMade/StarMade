import org.schema.game.common.controller.EditableSendableSegmentController;
import org.schema.game.common.data.player.inventory.NoSlotFreeException;
import org.schema.game.network.objects.NetworkSegmentController;
import org.schema.schine.network.objects.remote.RemoteIntPrimitive;

public final class class_709
  extends class_13
{
  public class_709(EditableSendableSegmentController paramEditableSendableSegmentController, class_371 paramclass_371, Object paramObject1, Object paramObject2, class_796 paramclass_796, short paramShort, class_481 paramclass_481, boolean paramBoolean)
  {
    super(paramclass_371, paramObject1, paramObject2);
  }
  
  public final boolean a1()
  {
    return false;
  }
  
  public final void b()
  {
    short s;
    if ((s = EditableSendableSegmentController.access$000(this.jdField_field_4_of_type_OrgSchemaGameCommonControllerEditableSendableSegmentController, this.jdField_field_4_of_type_Class_796, this.jdField_field_4_of_type_Short, this.jdField_field_4_of_type_Class_481, this.field_5)) != 0)
    {
      class_639 localclass_639 = this.field_4.a20().getInventory(null);
      try
      {
        int i = localclass_639.b8(s, 1);
        class_969.b("0022_action - buttons push medium");
        this.field_4.a20().sendInventoryModification(i, null);
      }
      catch (NoSlotFreeException localNoSlotFreeException)
      {
        this.field_4.a4().b1("ERROR\nno free slot in inventory\nthe element is lost");
      }
      this.jdField_field_4_of_type_OrgSchemaGameCommonControllerEditableSendableSegmentController.getNetworkObject().lastModifiedPlayerId.forceClientUpdates();
      this.jdField_field_4_of_type_OrgSchemaGameCommonControllerEditableSendableSegmentController.getNetworkObject().lastModifiedPlayerId.set(this.field_4.a20().getId());
      d();
    }
  }
  
  public final void a2()
  {
    this.field_4.a14().field_4.field_4.a13(400);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_709
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */