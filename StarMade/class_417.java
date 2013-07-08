import java.util.Set;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;

final class class_417
  implements class_479
{
  class_417(class_431 paramclass_431) {}
  
  public final void a(class_48 paramclass_481, class_48 paramclass_482, short paramShort)
  {
    if ((paramShort != 0) && (ElementKeyMap.getInfo(paramShort).controlling.size() > 0)) {
      this.field_159.a6().a4().b1("Warning:\nController blocks placed\nin astronaut-mode have\nto be connected manually\n(use '" + class_367.field_736.b1() + "'(select) and '" + class_367.field_737.b1() + "'(connect))");
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_417
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */