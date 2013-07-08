import javax.swing.JLabel;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;

final class class_540
  implements class_684
{
  class_540(class_538 paramclass_538) {}
  
  public final void a(ElementInformation paramElementInformation)
  {
    class_515.a(this.field_169.field_841, paramElementInformation.getId());
    class_515.a2(this.field_169.field_841).setText(class_515.a1(this.field_169.field_841) > 0 ? ElementKeyMap.getInfo(class_515.a1(this.field_169.field_841)).toString() : "undefined");
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_540
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */