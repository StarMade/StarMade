import org.schema.game.common.controller.CannotBeControlledException;
import org.schema.game.common.controller.EditableSendableSegmentController;

final class class_449
  implements class_479
{
  class_449(class_453 paramclass_453) {}
  
  public final void a(class_48 paramclass_481, class_48 paramclass_482, short paramShort)
  {
    paramShort = this.field_159.a6().a14().a18().a79().a60().a54();
    int i = 0;
    if (class_453.a74(this.field_159) == null)
    {
      i = 1;
      class_453.a75(this.field_159, new class_713());
    }
    if ((paramclass_481 != null) && (i != 0)) {
      if (paramclass_481.field_475 - paramclass_482.field_475 != 0)
      {
        i = paramclass_481.field_475;
        (paramclass_482 = class_453.a74(this.field_159)).jdField_field_978_of_type_ArrayOfInt[0] = i;
        paramclass_482.jdField_field_978_of_type_ArrayOfBoolean[0] = true;
      }
      else if (paramclass_481.field_476 - paramclass_482.field_476 != 0)
      {
        i = paramclass_481.field_476;
        (paramclass_482 = class_453.a74(this.field_159)).jdField_field_978_of_type_ArrayOfInt[1] = i;
        paramclass_482.jdField_field_978_of_type_ArrayOfBoolean[1] = true;
      }
      else if (paramclass_481.field_477 - paramclass_482.field_477 != 0)
      {
        i = paramclass_481.field_477;
        (paramclass_482 = class_453.a74(this.field_159)).jdField_field_978_of_type_ArrayOfInt[2] = i;
        paramclass_482.jdField_field_978_of_type_ArrayOfBoolean[2] = true;
      }
    }
    if (paramShort == 8)
    {
      if ((paramclass_482 = class_453.a71(this.field_159).a1().getSegmentBuffer().a9(class_453.a71(this.field_159).a(), false)) != null) {
        try
        {
          class_453.a71(this.field_159).a1().setCurrentBlockController(paramclass_482, paramclass_481);
          return;
        }
        catch (CannotBeControlledException paramclass_481)
        {
          this.field_159.a81(paramclass_481);
        }
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
        catch (CannotBeControlledException paramclass_482)
        {
          this.field_159.a81(paramclass_482);
        }
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_449
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */