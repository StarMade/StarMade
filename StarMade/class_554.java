import javax.swing.JLabel;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;

final class class_554
  implements class_684
{
  class_554(class_556 paramclass_556) {}
  
  public final void a(ElementInformation paramElementInformation)
  {
    class_550.a(this.field_169.field_861, paramElementInformation.getId());
    class_550.a2(this.field_169.field_861).setText(class_550.a1(this.field_169.field_861) > 0 ? ElementKeyMap.getInfo(class_550.a1(this.field_169.field_861)).toString() : "undefined");
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_554
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */