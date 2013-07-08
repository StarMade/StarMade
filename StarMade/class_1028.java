import java.util.ArrayList;
import java.util.BitSet;
import java.util.Iterator;

public final class class_1028
{
  public class_1056 field_1293;
  BitSet jdField_field_1293_of_type_JavaUtilBitSet;
  class_1034 jdField_field_1293_of_type_Class_1034;
  class_1034 jdField_field_1294_of_type_Class_1034;
  float jdField_field_1293_of_type_Float;
  float jdField_field_1294_of_type_Float;
  float field_1295;
  float field_1296;
  class_1048 jdField_field_1293_of_type_Class_1048;
  class_1048 jdField_field_1294_of_type_Class_1048;
  float field_1297 = 1.0F;
  float field_1298 = 0.0F;
  
  static float a(float paramFloat1, float paramFloat2, class_1048 paramclass_1048)
  {
    if (paramFloat2 == (1.0F / 1.0F)) {
      return paramFloat1;
    }
    if (paramFloat1 < 0.0F) {
      switch (class_1030.field_1299[paramclass_1048.ordinal()])
      {
      case 1: 
        return 0.0F;
      case 2: 
        return paramFloat1;
      case 3: 
        return paramFloat2 - paramFloat1;
      }
    } else if (paramFloat1 > paramFloat2) {
      switch (class_1030.field_1299[paramclass_1048.ordinal()])
      {
      case 1: 
        return paramFloat2;
      case 2: 
        return -(paramFloat2 * 2.0F - paramFloat1);
      case 3: 
        return paramFloat1 - paramFloat2;
      }
    }
    return paramFloat1;
  }
  
  class_1028(class_1056 paramclass_1056)
  {
    this.jdField_field_1293_of_type_Class_1056 = paramclass_1056;
  }
  
  private void b(class_986 paramclass_986)
  {
    paramclass_986 = paramclass_986.field_98;
    if (this.jdField_field_1293_of_type_JavaUtilBitSet == null) {
      this.jdField_field_1293_of_type_JavaUtilBitSet = new BitSet(this.jdField_field_1293_of_type_Class_1056.a3().a4());
    }
    this.jdField_field_1293_of_type_JavaUtilBitSet.set(paramclass_986);
  }
  
  public final void a1(class_986 paramclass_986)
  {
    b(paramclass_986);
    if (paramclass_986.a57() == null) {
      return;
    }
    paramclass_986 = paramclass_986.a57().iterator();
    while (paramclass_986.hasNext())
    {
      class_986 localclass_986 = (class_986)paramclass_986.next();
      b(localclass_986);
      a1(localclass_986);
    }
  }
  
  public final void a2(String paramString, float paramFloat)
  {
    if (paramString == null) {
      throw new NullPointerException("no name");
    }
    if (paramFloat < 0.0F) {
      throw new IllegalArgumentException("blendTime cannot be less than zero");
    }
    class_1034 localclass_1034;
    if ((localclass_1034 = this.jdField_field_1293_of_type_Class_1056.a1(paramString)) == null) {
      throw new IllegalArgumentException("Cannot find animation named: '" + paramString + "' in " + this.jdField_field_1293_of_type_Class_1056.a2());
    }
    this.jdField_field_1293_of_type_Class_1056.a4();
    if ((this.jdField_field_1293_of_type_Class_1034 != null) && (paramFloat > 0.0F))
    {
      this.jdField_field_1294_of_type_Class_1034 = this.jdField_field_1293_of_type_Class_1034;
      this.field_1295 = this.jdField_field_1293_of_type_Float;
      this.field_1296 = this.jdField_field_1294_of_type_Float;
      this.jdField_field_1294_of_type_Class_1048 = this.jdField_field_1293_of_type_Class_1048;
      this.field_1297 = 0.0F;
      this.field_1298 = (1.0F / paramFloat);
    }
    this.jdField_field_1293_of_type_Class_1034 = localclass_1034;
    this.jdField_field_1293_of_type_Float = 0.0F;
    this.jdField_field_1294_of_type_Float = 1.0F;
    this.jdField_field_1293_of_type_Class_1048 = class_1048.field_1315;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1028
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */