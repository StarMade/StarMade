import org.schema.game.common.data.element.ControlElementMap;
import org.schema.game.common.data.element.ControlledElementContainer;
import org.schema.game.common.data.element.ElementCollection;

public final class class_1111
  extends class_1168
{
  private int jdField_field_244_of_type_Int = 0;
  private final int jdField_field_241_of_type_Int;
  private class_48 field_242 = new class_48();
  private boolean field_240;
  private boolean jdField_field_244_of_type_Boolean;
  private boolean jdField_field_241_of_type_Boolean;
  
  public class_1111(class_48 paramclass_481, class_1068[] paramArrayOfclass_1068, class_48 paramclass_482, class_48 paramclass_483, byte paramByte)
  {
    super(paramclass_481, paramArrayOfclass_1068, paramclass_482, paramclass_483, 8, paramByte);
    this.jdField_field_241_of_type_Int = ((paramclass_483.field_475 - paramclass_482.field_475) / 2 + (paramclass_483.field_476 - paramclass_482.field_476) / 2 + (paramclass_483.field_477 - paramclass_482.field_477) / 2 - 4);
  }
  
  protected final short a(class_48 paramclass_48)
  {
    if (paramclass_48.equals(this.jdField_field_241_of_type_Class_48))
    {
      this.field_240 = true;
      this.jdField_field_244_of_type_Int += 1;
      this.jdField_field_244_of_type_Boolean = ((!this.jdField_field_241_of_type_Boolean) && (this.jdField_field_244_of_type_Int >= this.jdField_field_241_of_type_Int) && (this.field_240));
      return 7;
    }
    this.field_242.b1(this.jdField_field_241_of_type_Class_48);
    class_48 localclass_48 = org.schema.game.common.data.element.Element.DIRECTIONSi[a6(this.jdField_field_241_of_type_Class_48)];
    this.field_242.a1(localclass_48);
    if (((localclass_48.field_475 > 0) && (paramclass_48.field_475 > this.jdField_field_241_of_type_Class_48.field_475)) || ((localclass_48.field_475 < 0) && (paramclass_48.field_475 < this.jdField_field_241_of_type_Class_48.field_475)) || ((localclass_48.field_476 > 0) && (paramclass_48.field_476 > this.jdField_field_241_of_type_Class_48.field_476)) || ((localclass_48.field_476 < 0) && (paramclass_48.field_476 < this.jdField_field_241_of_type_Class_48.field_476)) || ((localclass_48.field_477 > 0) && (paramclass_48.field_477 > this.jdField_field_241_of_type_Class_48.field_477)) || ((localclass_48.field_477 < 0) && (paramclass_48.field_477 < this.jdField_field_241_of_type_Class_48.field_477))) {
      return 0;
    }
    this.field_242.b1(this.jdField_field_241_of_type_Class_48);
    this.field_242.c1(localclass_48);
    if ((paramclass_48.equals(this.field_242)) || ((paramclass_48.field_475 == this.field_242.field_475) && (paramclass_48.field_476 == this.field_242.field_476) && (paramclass_48.field_477 != this.field_242.field_477)) || ((paramclass_48.field_475 == this.field_242.field_475) && (paramclass_48.field_477 == this.field_242.field_477) && (paramclass_48.field_476 != this.field_242.field_476)) || ((paramclass_48.field_477 == this.field_242.field_477) && (paramclass_48.field_476 == this.field_242.field_476) && (paramclass_48.field_475 != this.field_242.field_475)))
    {
      this.field_240.addDelayed(new ControlledElementContainer(ElementCollection.getIndex(this.jdField_field_241_of_type_Class_48), new class_48(paramclass_48), (short)88, true, true));
      this.jdField_field_244_of_type_Int += 1;
      this.jdField_field_244_of_type_Boolean = ((!this.jdField_field_241_of_type_Boolean) && (this.jdField_field_244_of_type_Int >= this.jdField_field_241_of_type_Int) && (this.field_240));
      return 88;
    }
    return 32767;
  }
  
  public final boolean a2()
  {
    return this.jdField_field_244_of_type_Boolean;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1111
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */