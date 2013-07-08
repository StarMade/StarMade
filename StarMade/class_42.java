public class class_42
  extends class_12
{
  private class_257 field_4;
  
  public class_42(class_371 paramclass_371, String paramString1, String paramString2)
  {
    super(paramclass_371);
    this.field_4 = new class_257(paramclass_371, this, paramString1, paramString2);
  }
  
  public final void a(class_1363 paramclass_1363, class_939 paramclass_939)
  {
    if ((paramclass_939.jdField_field_1163_of_type_Boolean) && (paramclass_939.jdField_field_1163_of_type_Int == 0) && (!b1()))
    {
      class_12.field_4 = System.currentTimeMillis();
      if (paramclass_1363.b29().equals("OK"))
      {
        class_969.b("0022_menu_ui - enter");
        d();
        return;
      }
      if (paramclass_1363.b29().equals("CANCEL"))
      {
        class_969.b("0022_menu_ui - enter");
        d();
        return;
      }
      if (!field_5) {
        throw new AssertionError("not known command: " + paramclass_1363.b29());
      }
    }
  }
  
  public final class_1363 a3()
  {
    return this.field_4;
  }
  
  public void handleKeyEvent() {}
  
  public final boolean a1()
  {
    return false;
  }
  
  public final void a2() {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_42
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */