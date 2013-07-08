import java.util.ArrayList;

public final class class_469
  extends class_16
  implements class_1412
{
  public class_639 field_4;
  
  public class_469(class_371 paramclass_371)
  {
    super(paramclass_371);
  }
  
  public final class_371 a6()
  {
    return super.a6();
  }
  
  public final void handleKeyEvent() {}
  
  public final void a12(class_939 paramclass_939)
  {
    super.a12(paramclass_939);
  }
  
  public final void b2(boolean paramBoolean)
  {
    class_1046.field_1306 = !paramBoolean;
    if (paramBoolean)
    {
      class_969.b("0022_menu_ui - swoosh scroll large");
      setChanged();
      notifyObservers();
    }
    else
    {
      class_969.b("0022_menu_ui - swoosh scroll small");
    }
    super.a6().a14().field_4.field_4.field_4.a51().a45().jdField_field_4_of_type_Class_332.e2(paramBoolean);
    super.a6().a14().field_4.field_4.field_4.a51().a45().jdField_field_4_of_type_Class_453.e2(paramBoolean);
    super.b2(paramBoolean);
  }
  
  public final void a15(class_941 paramclass_941)
  {
    class_1046.field_1306 = false;
    super.a6().a14().field_4.field_4.field_4.e2(true);
  }
  
  public final void a(class_1363 paramclass_1363, class_939 paramclass_939)
  {
    if ((paramclass_939.jdField_field_1163_of_type_Boolean) && (paramclass_939.jdField_field_1163_of_type_Int == 0) && ("CONVERT".equals(paramclass_1363.b29())) && (this.field_4 != null) && ((this.field_4 instanceof class_627))) {
      ((class_627)this.field_4).c1();
    }
  }
  
  public final boolean a1()
  {
    return false;
  }
  
  public final void b()
  {
    synchronized (super.a6().b())
    {
      super.a6().b().add(new class_314(super.a6()));
      e2(true);
      return;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_469
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */