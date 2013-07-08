public class class_346
  extends class_12
{
  private final class_90 jdField_field_4_of_type_Class_90;
  private final class_773 jdField_field_4_of_type_Class_773;
  
  public class_346(class_371 paramclass_371, class_773 paramclass_773)
  {
    super(paramclass_371);
    this.jdField_field_4_of_type_Class_90 = new class_90(this.field_4, this, paramclass_773);
    this.jdField_field_4_of_type_Class_773 = paramclass_773;
  }
  
  public final boolean a1()
  {
    return false;
  }
  
  public void handleKeyEvent() {}
  
  public final class_1363 a3()
  {
    return this.jdField_field_4_of_type_Class_90;
  }
  
  public void a2()
  {
    this.field_4.a14().field_4.field_4.field_4.a13(500);
  }
  
  public final void a(class_1363 paramclass_1363, class_939 paramclass_939)
  {
    if (paramclass_939.a())
    {
      paramclass_939 = this.jdField_field_4_of_type_Class_90.a13();
      for (int i = 0; i < 5; i++) {
        if (paramclass_939[i].f1()) {
          return;
        }
      }
      if (paramclass_1363.b29().equals("OK"))
      {
        class_625 localclass_625;
        (localclass_625 = new class_625()).field_136 = this.jdField_field_4_of_type_Class_773.a3();
        for (paramclass_1363 = 0; paramclass_1363 < 5; paramclass_1363++)
        {
          if (paramclass_939[paramclass_1363].e1()) {
            localclass_625.a25()[paramclass_1363] |= 1L;
          }
          if (paramclass_939[paramclass_1363].c1()) {
            localclass_625.a25()[paramclass_1363] |= 4L;
          }
          if (paramclass_939[paramclass_1363].b3()) {
            localclass_625.a25()[paramclass_1363] |= 2L;
          }
          if (paramclass_939[paramclass_1363].d1()) {
            localclass_625.a25()[paramclass_1363] |= 8L;
          }
          localclass_625.a26()[paramclass_1363] = paramclass_939[paramclass_1363].a16();
        }
        paramclass_1363 = null;
        this.field_4.a45().a27(localclass_625);
        d();
        return;
      }
      if ((paramclass_1363.b29().equals("CANCEL")) || (paramclass_1363.b29().equals("X")))
      {
        paramclass_1363 = null;
        d();
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_346
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */