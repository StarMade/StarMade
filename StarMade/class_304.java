import org.schema.game.common.data.element.ElementKeyMap;

public final class class_304
  extends class_316
{
  public class_304(class_371 paramclass_371, short paramShort, int paramInt, class_743 paramclass_743)
  {
    super(paramclass_371, paramShort, "Sell Quantity", new class_306(ElementKeyMap.getInfo(paramShort), paramclass_743), paramInt);
    ((class_306)this.jdField_field_4_of_type_JavaLangObject).field_658 = (-paramInt);
    a10(new class_310(this, paramShort));
    this.field_4.a83(new class_308(this));
  }
  
  public final boolean a7(String paramString)
  {
    paramString = Integer.parseInt(paramString);
    this.field_4.a20().a125().b(this.jdField_field_4_of_type_Short, paramString);
    return true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_304
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */