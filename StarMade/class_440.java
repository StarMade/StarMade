import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;

public final class class_440
  extends class_454
{
  private static final long serialVersionUID = 438885771406304916L;
  private short field_128;
  
  public class_440(class_981 paramclass_981, class_371 paramclass_371, short paramShort)
  {
    super(paramclass_981, "", paramclass_371);
    this.field_128 = paramShort;
    this.field_162 = true;
  }
  
  protected final boolean a()
  {
    if (this.jdField_field_128_of_type_Class_371.a4().a5().field_163 != null)
    {
      Integer localInteger;
      return ((localInteger = Integer.valueOf(this.jdField_field_128_of_type_Class_371.a4().a5().field_163.getElementClassCountMap().a1(this.field_128))) != null) && (localInteger.intValue() > 0);
    }
    a8().a4().a2().a1(new class_389());
    return false;
  }
  
  public final boolean d()
  {
    int i;
    if ((i = this.jdField_field_128_of_type_Class_371.a20().getInventory(null).a42(this.field_128)) < 0) {
      this.jdField_field_128_of_type_JavaLangString = ("You don't have any \n" + ElementKeyMap.getInfo(this.field_128).getName() + "\nModules.\nPlease buy one in\nthe shop (Press B)\n");
    } else if (i > 10) {
      this.jdField_field_128_of_type_JavaLangString = ("Please drag \n" + ElementKeyMap.getInfo(this.field_128).getName() + "\nto the bottom bar from\nthe inventory (Press I)");
    } else {
      this.jdField_field_128_of_type_JavaLangString = ("Please select \n" + ElementKeyMap.getInfo(this.field_128).getName() + " (press " + (i + 1) % 10 + ")\nfrom the bottom bar and place\none on the ship");
    }
    return super.d();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_440
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */