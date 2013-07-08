import java.util.Set;
import org.schema.game.common.controller.CannotBeControlledException;
import org.schema.game.common.data.element.ElementInformation;

public abstract class class_485
  extends class_16
{
  public class_485(class_371 paramclass_371)
  {
    super(paramclass_371);
  }
  
  public final void a81(CannotBeControlledException paramCannotBeControlledException)
  {
    if ((paramCannotBeControlledException != null) && (paramCannotBeControlledException.field_1757 != null) && (paramCannotBeControlledException.field_1757.controlledBy != null) && (paramCannotBeControlledException.field_1757.controlledBy.size() > 0)) {
      a6().a4().b1(paramCannotBeControlledException.field_1757.getName() + " cannot be\nconnected to selected block:\n" + paramCannotBeControlledException.field_1758.getName());
    }
  }
  
  public abstract void b();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_485
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */