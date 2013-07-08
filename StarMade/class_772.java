import java.util.ArrayList;
import java.util.Arrays;

public final class class_772
  implements class_80
{
  private final String jdField_field_136_of_type_JavaLangString;
  private class_1018 jdField_field_136_of_type_Class_1018;
  private Object jdField_field_136_of_type_JavaLangObject;
  private final class_774 jdField_field_136_of_type_Class_774;
  private final class_776 jdField_field_136_of_type_Class_776;
  
  public class_772(class_776 paramclass_776, Object paramObject, class_1018 paramclass_1018, class_774 paramclass_774)
  {
    this.jdField_field_136_of_type_JavaLangString = paramclass_776.field_1032;
    this.jdField_field_136_of_type_Class_776 = paramclass_776;
    this.jdField_field_136_of_type_Class_1018 = paramclass_1018;
    this.jdField_field_136_of_type_Class_774 = paramclass_774;
    a173(paramObject, false);
  }
  
  public final void fromTagStructure(class_69 paramclass_69) {}
  
  public final Object a171()
  {
    return this.jdField_field_136_of_type_JavaLangObject;
  }
  
  public final String a()
  {
    return this.jdField_field_136_of_type_JavaLangString;
  }
  
  public final String b()
  {
    return this.jdField_field_136_of_type_Class_776.name();
  }
  
  public final class_776 a172()
  {
    return this.jdField_field_136_of_type_Class_776;
  }
  
  public final String getUniqueIdentifier()
  {
    return null;
  }
  
  public final boolean a7()
  {
    return ((this.jdField_field_136_of_type_JavaLangObject instanceof Boolean)) && (((Boolean)this.jdField_field_136_of_type_JavaLangObject).booleanValue());
  }
  
  public final boolean isVolatile()
  {
    return false;
  }
  
  public final void a173(Object paramObject, boolean paramBoolean)
  {
    int i = !paramObject.equals(this.jdField_field_136_of_type_JavaLangObject) ? 1 : 0;
    this.jdField_field_136_of_type_JavaLangObject = paramObject;
    if (i != 0) {
      this.jdField_field_136_of_type_Class_774.a182(this, paramBoolean);
    }
  }
  
  public final void a13()
  {
    a173(this.jdField_field_136_of_type_Class_1018.a2(), true);
  }
  
  public final void b4()
  {
    Object localObject = this.jdField_field_136_of_type_Class_1018.a2();
    a173(localObject, true);
  }
  
  public final void a174(String paramString, boolean paramBoolean)
  {
    a173(this.jdField_field_136_of_type_Class_1018.a(paramString), paramBoolean);
  }
  
  public final void c1()
  {
    a173(this.jdField_field_136_of_type_Class_1018.b(), true);
  }
  
  public final String toString()
  {
    return this.jdField_field_136_of_type_JavaLangString + " (" + this.jdField_field_136_of_type_Class_1018.a1() + ") " + this.jdField_field_136_of_type_JavaLangObject + " " + Arrays.toString(this.jdField_field_136_of_type_Class_1018.field_228);
  }
  
  public final class_69 toTagStructure()
  {
    class_69 localclass_691 = new class_69(class_79.field_556, "type", this.jdField_field_136_of_type_Class_776.name());
    class_69 localclass_692 = new class_69(class_79.field_556, "state", this.jdField_field_136_of_type_JavaLangObject.toString());
    return new class_69(class_79.field_561, "AIElement", new class_69[] { localclass_691, localclass_692, new class_69(class_79.field_548, "fin", null) });
  }
  
  static
  {
    new ArrayList();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_772
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */