import java.util.Set;
import org.schema.game.common.controller.CannotBeControlledException;
import org.schema.game.common.controller.EditableSendableSegmentController;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;

final class class_451
  implements class_479
{
  class_451(class_453 paramclass_453) {}
  
  public final void a(class_48 paramclass_481, class_48 paramclass_482, short paramShort)
  {
    if (ElementKeyMap.getInfo(paramclass_482 = this.field_159.a6().a14().a18().a79().a60().a54()).getControlledBy().contains(Short.valueOf((short)1)))
    {
      if ((paramShort = class_453.a71(this.field_159).a1().getSegmentBuffer().a9(class_453.a71(this.field_159).a(), false)) != null) {
        try
        {
          class_453.a71(this.field_159).a1().setCurrentBlockController(paramShort, paramclass_481);
        }
        catch (CannotBeControlledException paramShort)
        {
          this.field_159.a81(paramShort);
        }
      }
      if ((class_949.field_1233.b1()) && (ElementKeyMap.getInfo(paramclass_482).getControlling().size() > 0) && ((paramShort = class_453.a71(this.field_159).a1().getSegmentBuffer().a9(paramclass_481, false)) != null)) {
        class_453.a72(this.field_159, paramShort);
      }
      return;
    }
    if ((class_453.a73(this.field_159) != null) && (paramclass_481 != null))
    {
      class_453.a73(this.field_159).a12();
      if (class_453.a73(this.field_159).a9() != 0) {
        try
        {
          class_453.a71(this.field_159).a1().setCurrentBlockController(class_453.a73(this.field_159), paramclass_481);
          return;
        }
        catch (CannotBeControlledException paramShort)
        {
          this.field_159.a81(paramShort);
          return;
        }
      }
      if (ElementKeyMap.getInfo(paramclass_482).getControlledBy().size() > 0) {
        this.field_159.a6().a4().d1("Note:\nYou placed a controllable Block\nWithout having a conroller selected!\nE.g. if you place weapons, please\nselect/place the weapon computer\nfirst.");
      }
    }
    else if ((class_453.a73(this.field_159) == null) && (ElementKeyMap.getInfo(paramclass_482).getControlledBy().size() > 0))
    {
      this.field_159.a6().a4().d1("Note:\nYou placed a controllable Block\nWithout having a conroller selected!\nE.g. if you place weapons, please\nselect/place the weapon computer\nfirst.");
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_451
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */