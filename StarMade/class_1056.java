import java.util.ArrayList;
import java.util.Collection;

public final class class_1056
  extends class_1032
  implements Cloneable
{
  private class_1386 jdField_field_238_of_type_Class_1386;
  private final ArrayList jdField_field_238_of_type_JavaUtilArrayList = new ArrayList();
  private final ArrayList field_239 = new ArrayList();
  
  public class_1056() {}
  
  public class_1056(class_1386 paramclass_1386)
  {
    this.jdField_field_238_of_type_Class_1386 = paramclass_1386;
    paramclass_1386 = this;
    if (this.jdField_field_238_of_type_Class_1386.a8() != null) {
      paramclass_1386.jdField_field_238_of_type_Class_1386.a8().a10();
    }
  }
  
  public final class_1028 a()
  {
    class_1028 localclass_1028 = new class_1028(this);
    this.jdField_field_238_of_type_JavaUtilArrayList.add(localclass_1028);
    return localclass_1028;
  }
  
  public final class_1034 a1(String paramString)
  {
    return this.jdField_field_238_of_type_Class_1386.a8().a1(paramString);
  }
  
  public final Collection a2()
  {
    return this.jdField_field_238_of_type_Class_1386.a8().a2();
  }
  
  public final class_1384 a3()
  {
    return this.jdField_field_238_of_type_Class_1386.a8();
  }
  
  final void a4()
  {
    for (int i = 0; i < this.field_239.size(); i++) {
      this.field_239.get(i);
    }
  }
  
  private void b()
  {
    for (int i = 0; i < this.field_239.size(); i++) {
      this.field_239.get(i);
    }
  }
  
  public final void a5(class_941 paramclass_941)
  {
    if (this.jdField_field_238_of_type_Class_1386.a8() != null)
    {
      if (!this.jdField_field_238_of_type_Class_1386.a8().a9()) {
        this.jdField_field_238_of_type_Class_1386.a8().a8(paramclass_941);
      }
      this.jdField_field_238_of_type_Class_1386.a8().a10();
      for (int i = 0; i < this.jdField_field_238_of_type_JavaUtilArrayList.size(); i++)
      {
        class_941 localclass_941 = paramclass_941;
        class_1028 localclass_1028;
        if ((localclass_1028 = (class_1028)this.jdField_field_238_of_type_JavaUtilArrayList.get(i)).jdField_field_1293_of_type_Class_1034 != null)
        {
          if (localclass_1028.jdField_field_1294_of_type_Class_1034 != null)
          {
            localclass_1028.jdField_field_1294_of_type_Class_1034.a(localclass_1028.field_1295, 1.0F - localclass_1028.field_1297, localclass_1028.jdField_field_1293_of_type_Class_1056, localclass_1028);
            localclass_1028.field_1295 += localclass_941.a() * localclass_1028.field_1296;
            localclass_1028.field_1295 = class_1028.a(localclass_1028.field_1295, localclass_1028.jdField_field_1294_of_type_Class_1034.jdField_field_1303_of_type_Float, localclass_1028.jdField_field_1294_of_type_Class_1048);
            if (localclass_1028.field_1295 < 0.0F)
            {
              localclass_1028.field_1295 = (-localclass_1028.field_1295);
              localclass_1028.field_1296 = (-localclass_1028.field_1296);
            }
            localclass_1028.field_1297 += localclass_941.a() * localclass_1028.field_1298;
            if (localclass_1028.field_1297 > 1.0F)
            {
              localclass_1028.field_1297 = 1.0F;
              localclass_1028.jdField_field_1294_of_type_Class_1034 = null;
            }
          }
          localclass_1028.jdField_field_1293_of_type_Class_1034.a(localclass_1028.jdField_field_1293_of_type_Float, localclass_1028.field_1297, localclass_1028.jdField_field_1293_of_type_Class_1056, localclass_1028);
          localclass_1028.jdField_field_1293_of_type_Float += localclass_941.a() * localclass_1028.jdField_field_1294_of_type_Float;
          if (localclass_1028.jdField_field_1293_of_type_Class_1034.jdField_field_1303_of_type_Float > 0.0F) {
            if (localclass_1028.jdField_field_1293_of_type_Float >= localclass_1028.jdField_field_1293_of_type_Class_1034.jdField_field_1303_of_type_Float) {
              localclass_1028.jdField_field_1293_of_type_Class_1056.b();
            } else if (localclass_1028.jdField_field_1293_of_type_Float < 0.0F) {
              localclass_1028.jdField_field_1293_of_type_Class_1056.b();
            }
          }
          localclass_1028.jdField_field_1293_of_type_Float = class_1028.a(localclass_1028.jdField_field_1293_of_type_Float, localclass_1028.jdField_field_1293_of_type_Class_1034.jdField_field_1303_of_type_Float, localclass_1028.jdField_field_1293_of_type_Class_1048);
          if (localclass_1028.jdField_field_1293_of_type_Float < 0.0F)
          {
            localclass_1028.jdField_field_1293_of_type_Float = (-localclass_1028.jdField_field_1293_of_type_Float);
            localclass_1028.jdField_field_1294_of_type_Float = (-localclass_1028.jdField_field_1294_of_type_Float);
          }
        }
      }
      if (this.jdField_field_238_of_type_Class_1386 != null) {
        this.jdField_field_238_of_type_Class_1386.a5(paramclass_941);
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1056
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */