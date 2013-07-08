import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;

public final class class_433
{
  public final class_48 field_795 = new class_48(8, 8, 8);
  public final class_48 field_796;
  public final class_48 field_797 = new class_48(8, 8, 8);
  public boolean field_795;
  public boolean field_796;
  public boolean field_797;
  public int field_795;
  public int field_796;
  
  public class_433()
  {
    this.jdField_field_796_of_type_Class_48 = new class_48(8, 8, 8);
    this.jdField_field_796_of_type_Int = 1;
  }
  
  public static int a(short paramShort, boolean paramBoolean1, int paramInt, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4)
  {
    if (paramShort != 0)
    {
      if (!paramBoolean1) {
        paramInt += 8;
      }
      if ((paramShort = ElementKeyMap.getInfo(paramShort)).getBlockStyle() > 0)
      {
        paramInt = org.schema.game.common.data.element.Element.orientationMapping[paramInt];
        if (paramBoolean2) {
          paramInt = class_384.field_102[(paramShort.blockStyle - 1)][paramInt];
        }
        if (paramBoolean3) {
          paramInt = class_384.field_775[(paramShort.blockStyle - 1)][paramInt];
        }
        if (paramBoolean4) {
          paramInt = class_384.field_776[(paramShort.blockStyle - 1)][paramInt];
        }
        paramInt = org.schema.game.common.data.element.Element.orientationBackMapping[paramInt];
      }
      if (!paramBoolean1) {
        paramInt -= 8;
      }
    }
    return paramInt;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_433
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */