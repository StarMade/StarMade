import java.util.HashSet;
import org.schema.game.network.objects.NetworkPlayer;
import org.schema.schine.network.objects.remote.RemoteBoolean;

public final class class_427
  extends class_16
  implements class_1412
{
  public class_430 field_4;
  public class_425 field_4;
  public class_426 field_4;
  
  public class_427(class_371 paramclass_371)
  {
    super(paramclass_371);
    paramclass_371 = this;
    this.jdField_field_4_of_type_Class_430 = new class_430(paramclass_371.a6());
    paramclass_371.jdField_field_4_of_type_Class_425 = new class_425(paramclass_371.a6());
    paramclass_371.jdField_field_4_of_type_Class_426 = new class_426(paramclass_371.a6());
    paramclass_371.field_4.add(paramclass_371.jdField_field_4_of_type_Class_430);
    paramclass_371.field_4.add(paramclass_371.jdField_field_4_of_type_Class_425);
    paramclass_371.field_4.add(paramclass_371.jdField_field_4_of_type_Class_426);
    paramclass_371.jdField_field_4_of_type_Class_430.c2(true);
  }
  
  public final void b2(boolean paramBoolean)
  {
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
    super.b2(paramBoolean);
  }
  
  public final void a15(class_941 paramclass_941)
  {
    class_1046.field_1306 = false;
    super.a15(paramclass_941);
  }
  
  public final void a(class_1363 paramclass_1363, class_939 paramclass_939)
  {
    if ((paramclass_939.jdField_field_1163_of_type_Boolean) && (paramclass_939.jdField_field_1163_of_type_Int == 0))
    {
      if ("PERSONAL".equals(paramclass_1363.b29()))
      {
        a11(this.jdField_field_4_of_type_Class_430);
        setChanged();
        notifyObservers();
        return;
      }
      if ("ACCESSIBLE".equals(paramclass_1363.b29()))
      {
        a11(this.jdField_field_4_of_type_Class_425);
        setChanged();
        notifyObservers();
        return;
      }
      if (("ADMIN".equals(paramclass_1363.b29())) && (((Boolean)a6().a20().a127().isAdminClient.get()).booleanValue()))
      {
        a11(this.jdField_field_4_of_type_Class_426);
        setChanged();
        notifyObservers();
      }
    }
  }
  
  public final boolean a1()
  {
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_427
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */