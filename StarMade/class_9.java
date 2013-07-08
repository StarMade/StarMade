import java.util.ArrayList;

public final class class_9
  extends class_12
{
  private class_128 jdField_field_4_of_type_Class_128;
  private long jdField_field_5_of_type_Long;
  private long field_6 = 20000L;
  private final class_773 jdField_field_4_of_type_Class_773;
  private final class_773 jdField_field_5_of_type_Class_773;
  private String jdField_field_4_of_type_JavaLangString;
  private String jdField_field_5_of_type_JavaLangString;
  
  public class_9(class_371 paramclass_371, int paramInt1, int paramInt2)
  {
    super(paramclass_371);
    this.jdField_field_4_of_type_Class_773 = paramclass_371.a45().a156(paramInt1);
    this.jdField_field_5_of_type_Class_773 = paramclass_371.a45().a156(paramInt2);
    this.jdField_field_4_of_type_JavaLangString = ("(UNKNOWN)" + paramInt1);
    this.jdField_field_5_of_type_JavaLangString = ("(UNKNOWN)" + paramInt2);
    this.jdField_field_4_of_type_Class_128 = new class_128(paramclass_371, a4());
    this.jdField_field_5_of_type_Long = System.currentTimeMillis();
    this.jdField_field_4_of_type_Boolean = false;
  }
  
  public final void a(class_1363 paramclass_1363, class_939 paramclass_939)
  {
    if ((paramclass_939.jdField_field_1163_of_type_Boolean) && (paramclass_939.jdField_field_1163_of_type_Int == 0) && (!this.jdField_field_4_of_type_Class_371.a14().field_4.field_5) && (!b1())) {
      class_12.jdField_field_4_of_type_Long = System.currentTimeMillis();
    }
  }
  
  public final boolean c()
  {
    this.jdField_field_4_of_type_Class_128.a17(a4());
    return System.currentTimeMillis() - this.jdField_field_5_of_type_Long > this.field_6;
  }
  
  private String a4()
  {
    long l = (this.field_6 - (System.currentTimeMillis() - this.jdField_field_5_of_type_Long)) / 1000L;
    return "Round has ended! \nTeam \"" + this.jdField_field_4_of_type_JavaLangString + "\" has won\nby destroying the base of \nthe pathetic team \n\"" + this.jdField_field_5_of_type_JavaLangString + "\"\n\n\nThe Round Will \nRestart in " + l + " seconds";
  }
  
  public final void handleKeyEvent() {}
  
  public final boolean a1()
  {
    return this.jdField_field_4_of_type_Class_371.b().indexOf(this) != this.jdField_field_4_of_type_Class_371.b().size() - 1;
  }
  
  public final void a2() {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_9
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */