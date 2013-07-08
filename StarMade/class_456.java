public abstract class class_456
{
  class_999 jdField_field_166_of_type_Class_999;
  class_999 jdField_field_167_of_type_Class_999;
  class_999 jdField_field_798_of_type_Class_999;
  private class_999 jdField_field_799_of_type_Class_999;
  private class_999 jdField_field_800_of_type_Class_999;
  private static class_1003 jdField_field_166_of_type_Class_1003 = new class_391();
  private static class_1003 jdField_field_167_of_type_Class_1003 = new class_385();
  private static class_1003 jdField_field_798_of_type_Class_1003 = new class_375();
  private static class_1003 jdField_field_799_of_type_Class_1003 = new class_383();
  private static class_1003 jdField_field_800_of_type_Class_1003 = new class_393();
  private static class_1003 field_801 = new class_395();
  protected class_371 field_166;
  
  public class_456(class_999 paramclass_9991, class_999 paramclass_9992, class_999 paramclass_9993, class_371 paramclass_371)
  {
    this.jdField_field_166_of_type_Class_999 = paramclass_9991;
    this.jdField_field_799_of_type_Class_999 = paramclass_9992;
    this.jdField_field_800_of_type_Class_999 = paramclass_9993;
    this.jdField_field_166_of_type_Class_371 = paramclass_371;
  }
  
  public final class_999 a3()
  {
    a1();
    if ((!jdField_field_166_of_type_Boolean) && (this.jdField_field_167_of_type_Class_999 == null)) {
      throw new AssertionError();
    }
    if ((!jdField_field_166_of_type_Boolean) && (this.jdField_field_798_of_type_Class_999 == null)) {
      throw new AssertionError();
    }
    this.jdField_field_167_of_type_Class_999.a9(field_801, this.jdField_field_166_of_type_Class_999);
    this.jdField_field_166_of_type_Class_999.a9(jdField_field_166_of_type_Class_1003, this.jdField_field_167_of_type_Class_999);
    this.jdField_field_166_of_type_Class_999.a9(jdField_field_167_of_type_Class_1003, this.jdField_field_799_of_type_Class_999);
    this.jdField_field_166_of_type_Class_999.a9(jdField_field_800_of_type_Class_1003, this.jdField_field_800_of_type_Class_999);
    class_999 localclass_999 = a(this.jdField_field_167_of_type_Class_999);
    a2(localclass_999, this.jdField_field_798_of_type_Class_999);
    if ((!jdField_field_166_of_type_Boolean) && (this.jdField_field_167_of_type_Class_999.a10().a() <= 0)) {
      throw new AssertionError();
    }
    return this.jdField_field_798_of_type_Class_999;
  }
  
  protected abstract class_999 a(class_999 paramclass_999);
  
  protected abstract void a1();
  
  protected void a2(class_999 paramclass_9991, class_999 paramclass_9992)
  {
    paramclass_9991.a9(jdField_field_166_of_type_Class_1003, paramclass_9992);
    paramclass_9991.a9(jdField_field_167_of_type_Class_1003, this.jdField_field_799_of_type_Class_999);
    paramclass_9991.a9(jdField_field_799_of_type_Class_1003, this.jdField_field_166_of_type_Class_999);
    paramclass_9991.a9(jdField_field_798_of_type_Class_1003, this.jdField_field_798_of_type_Class_999);
    paramclass_9991.a9(jdField_field_800_of_type_Class_1003, this.jdField_field_800_of_type_Class_999);
    paramclass_9992.a9(field_801, paramclass_9991);
  }
  
  protected final void a4(class_999 paramclass_9991, class_999 paramclass_9992, class_999 paramclass_9993)
  {
    paramclass_9991.a9(jdField_field_166_of_type_Class_1003, paramclass_9992);
    paramclass_9991.a9(jdField_field_167_of_type_Class_1003, this.jdField_field_799_of_type_Class_999);
    paramclass_9991.a9(jdField_field_799_of_type_Class_1003, this.jdField_field_166_of_type_Class_999);
    paramclass_9991.a9(jdField_field_798_of_type_Class_1003, this.jdField_field_798_of_type_Class_999);
    paramclass_9991.a9(jdField_field_800_of_type_Class_1003, this.jdField_field_800_of_type_Class_999);
    paramclass_9992.a9(field_801, paramclass_9993);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_456
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */