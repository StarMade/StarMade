import org.schema.game.common.data.element.ElementKeyMap;

public final class class_322
  extends class_316
{
  public class_322(class_371 paramclass_371, short paramShort, class_743 paramclass_743)
  {
    super(paramclass_371, paramShort, "Buy Quantity", new class_320(ElementKeyMap.getInfo(paramShort), paramclass_743), 1);
    ((class_320)this.jdField_field_4_of_type_JavaLangObject).field_682 = 1;
    a10(new class_324(this, paramShort));
    this.field_4.a83(new class_326(this));
  }
  
  public final boolean a7(String paramString)
  {
    paramString = Integer.parseInt(paramString);
    this.field_4.a20().a125().a(this.jdField_field_4_of_type_Short, paramString);
    return true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_322
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */