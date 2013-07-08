import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.schema.game.client.view.gui.weapon.NoneElementException;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.game.common.data.player.ShipConfigurationNotFoundException;
import org.schema.game.common.data.world.Segment;
import org.schema.schine.network.client.ClientState;

final class class_148
  extends class_959
{
  public class_148(class_796 paramclass_796, ClientState paramClientState)
  {
    super(new class_930(200, 30, class_29.a16(), paramClientState), new class_930(200, 30, class_29.b2(), paramClientState), paramClientState);
    Object localObject = paramclass_796.a2(new class_48());
    int i;
    if ((i = paramclass_796.a9()) != 0)
    {
      paramClientState = (class_371)paramClientState;
      ElementInformation localElementInformation = null;
      ((class_930)this.field_89).field_90 = new ArrayList();
      class_747 localclass_747 = (class_747)paramclass_796.a7().a15();
      String str;
      if ((i == 1) && (paramclass_796.a7().a15().getDockingController().a4() != null))
      {
        str = "Undock Self";
      }
      else if (str == 1)
      {
        str = "Docking Beam (Core)";
      }
      else if (ElementKeyMap.getInfo(str).isDockable())
      {
        str = "Undock " + paramclass_796.a7().a15().toNiceString();
      }
      else
      {
        localElementInformation = ElementKeyMap.getInfo(str);
        str = localElementInformation.getName() + " " + localObject;
      }
      if (paramClientState.a20().a131(paramclass_796.a7().a15())) {
        try
        {
          paramClientState = (ClientState)localObject;
          if ((localObject = (Integer)paramClientState.a20().a128(localclass_747).field_139.get(paramClientState)) != null) {
            str = "(" + (((Integer)localObject).intValue() + 1) % 10 + ") " + str;
          }
        }
        catch (ShipConfigurationNotFoundException localShipConfigurationNotFoundException)
        {
          localShipConfigurationNotFoundException;
        }
      }
      ((class_930)this.field_89).field_90.add(str);
      ((class_930)this.field_90).field_90 = new ArrayList();
      ((class_930)this.field_90).field_90.add(str);
      this.field_89.field_89 = paramclass_796;
      return;
    }
    throw new NoneElementException();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_148
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */