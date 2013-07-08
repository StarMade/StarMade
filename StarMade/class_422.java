public class class_422
  extends class_12
{
  private final class_797 jdField_field_4_of_type_Class_797;
  private class_141 jdField_field_4_of_type_Class_141;
  
  public class_422(class_371 paramclass_371, class_797 paramclass_797)
  {
    super(paramclass_371);
    this.jdField_field_4_of_type_Class_797 = paramclass_797;
    this.jdField_field_4_of_type_Class_141 = new class_141(this.field_4, this, paramclass_797);
  }
  
  public final void a(class_1363 paramclass_1363, class_939 paramclass_939)
  {
    if (paramclass_939.a())
    {
      if (paramclass_1363.b29().equals("NEUTRAL"))
      {
        this.field_4.a20().a141().a205(0, this.jdField_field_4_of_type_Class_797);
        d();
        return;
      }
      if (paramclass_1363.b29().equals("FACTION"))
      {
        this.field_4.a20().a141().a205(this.field_4.a20().h1(), this.jdField_field_4_of_type_Class_797);
        d();
        return;
      }
      if (paramclass_1363.b29().equals("HOMEBASE"))
      {
        this.field_4.a45().a164(this.field_4.a20().getName(), this.field_4.a20().h1(), this.jdField_field_4_of_type_Class_797.getUniqueIdentifier(), this.field_4.a20().a44());
        d();
        return;
      }
      if (paramclass_1363.b29().equals("HOMEBASE_REVOKE"))
      {
        this.field_4.a45().a164(this.field_4.a20().getName(), this.field_4.a20().h1(), "", new class_48());
        d();
        return;
      }
      if ((paramclass_1363.b29().equals("CANCEL")) || (paramclass_1363.b29().equals("X"))) {
        d();
      }
    }
  }
  
  public final boolean a1()
  {
    return false;
  }
  
  public void handleKeyEvent() {}
  
  public final class_1363 a3()
  {
    return this.jdField_field_4_of_type_Class_141;
  }
  
  public void a2() {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_422
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */