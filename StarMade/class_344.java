import java.io.PrintStream;

public final class class_344
  extends class_12
{
  private class_773 jdField_field_4_of_type_Class_773;
  private class_773 field_5;
  private class_86 jdField_field_4_of_type_Class_86;
  
  public class_344(class_371 paramclass_371, class_773 paramclass_7731, class_773 paramclass_7732)
  {
    super(paramclass_371);
    this.jdField_field_4_of_type_Class_773 = paramclass_7731;
    this.field_5 = paramclass_7732;
    this.jdField_field_4_of_type_Class_86 = new class_86(paramclass_371, paramclass_7731, paramclass_7732, this);
    this.jdField_field_4_of_type_Class_86.e();
  }
  
  public final void a(class_1363 paramclass_1363, class_939 paramclass_939)
  {
    if (paramclass_939.a())
    {
      if (paramclass_1363.b29().equals("OK"))
      {
        d();
        return;
      }
      if ((paramclass_1363.b29().equals("CANCEL")) || (paramclass_1363.b29().equals("X")))
      {
        System.err.println("CANCEL");
        d();
        return;
      }
      class_773 localclass_773;
      if (paramclass_1363.b29().equals("WAR"))
      {
        localclass_773 = this.field_5;
        paramclass_939 = this.jdField_field_4_of_type_Class_773;
        (paramclass_1363 = this.field_4.a14().field_4.field_4.field_4).e2(true);
        new class_411(paramclass_1363, paramclass_1363.a6(), "Declare war", "Enter a message for your declaration", paramclass_939, localclass_773).c1();
        d();
        return;
      }
      if (paramclass_1363.b29().equals("PEACE"))
      {
        localclass_773 = this.field_5;
        paramclass_939 = this.jdField_field_4_of_type_Class_773;
        (paramclass_1363 = this.field_4.a14().field_4.field_4.field_4).e2(true);
        new class_414(paramclass_1363, paramclass_1363.a6(), "Peace Treaty Offer", "Enter a message for the offer", paramclass_939, localclass_773).c1();
        d();
        return;
      }
      if (paramclass_1363.b29().equals("ALLY"))
      {
        localclass_773 = this.field_5;
        paramclass_939 = this.jdField_field_4_of_type_Class_773;
        (paramclass_1363 = this.field_4.a14().field_4.field_4.field_4).e2(true);
        new class_416(paramclass_1363, paramclass_1363.a6(), "Alliance Offer", "Enter a message for the offer", paramclass_939, localclass_773).c1();
        d();
      }
    }
  }
  
  public final boolean a1()
  {
    return false;
  }
  
  public final void handleKeyEvent() {}
  
  public final class_1363 a3()
  {
    return this.jdField_field_4_of_type_Class_86;
  }
  
  public final void a2()
  {
    this.field_4.a14().field_4.field_4.field_4.a13(500);
    this.field_4.a14().field_4.field_4.field_4.e2(false);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_344
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */