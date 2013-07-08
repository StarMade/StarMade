import java.util.ArrayList;
import java.util.HashSet;

public final class class_423
  extends class_16
  implements class_1412
{
  public class_350 field_4;
  public class_406 field_4;
  public class_348 field_4;
  
  public class_423(class_371 paramclass_371)
  {
    super(paramclass_371);
    paramclass_371 = this;
    this.jdField_field_4_of_type_Class_350 = new class_350(paramclass_371.a6());
    paramclass_371.jdField_field_4_of_type_Class_406 = new class_406(paramclass_371.a6());
    paramclass_371.jdField_field_4_of_type_Class_348 = new class_348(paramclass_371.a6());
    paramclass_371.field_4.add(paramclass_371.jdField_field_4_of_type_Class_350);
    paramclass_371.field_4.add(paramclass_371.jdField_field_4_of_type_Class_406);
    paramclass_371.field_4.add(paramclass_371.jdField_field_4_of_type_Class_348);
    paramclass_371.jdField_field_4_of_type_Class_350.c2(true);
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
  
  private class_773 a46()
  {
    int i = a6().a20().h1();
    return a6().a45().a156(i);
  }
  
  public final void a(class_1363 paramclass_1363, class_939 paramclass_939)
  {
    if ((paramclass_939.jdField_field_1163_of_type_Boolean) && (paramclass_939.jdField_field_1163_of_type_Int == 0))
    {
      if ("PERSONAL".equals(paramclass_1363.b29()))
      {
        a11(this.jdField_field_4_of_type_Class_350);
        setChanged();
        notifyObservers();
        return;
      }
      if ("HUB".equals(paramclass_1363.b29()))
      {
        a11(this.jdField_field_4_of_type_Class_406);
        setChanged();
        notifyObservers();
        return;
      }
      if ("LOCAL".equals(paramclass_1363.b29()))
      {
        a11(this.jdField_field_4_of_type_Class_348);
        setChanged();
        notifyObservers();
        return;
      }
      if ("INCOMING_INVITES".equals(paramclass_1363.b29()))
      {
        new class_258(a6(), this).c1();
        return;
      }
      if ("OUTGOING_INVITES".equals(paramclass_1363.b29()))
      {
        new class_121(a6(), this).c1();
        return;
      }
      if ("EDIT_DESC".equals(paramclass_1363.b29()))
      {
        e2(true);
        paramclass_1363 = a46();
        new class_424(this, a6(), "Edit Faction Description", "Enter a description for the faction", paramclass_1363 != null ? paramclass_1363.c5() : "ERROR: NO FACTION").c1();
        return;
      }
      if ("POST_NEWS".equals(paramclass_1363.b29()))
      {
        e2(true);
        a46();
        new class_410(this, a6(), "Post Faction News", "Enter text for a new News Post", "").c1();
        return;
      }
      if ("CREATE_FACTION".equals(paramclass_1363.b29()))
      {
        if (a6().b().isEmpty())
        {
          paramclass_1363 = new class_415(a6(), "Change Faction Name", this);
          a6().b().add(paramclass_1363);
          e2(true);
          setChanged();
          notifyObservers();
        }
      }
      else if ("LEAVE_FACTION".equals(paramclass_1363.b29()))
      {
        if (a6().b().isEmpty()) {
          new class_409(a6(), "Confirm", "Do you really want to leave the faction").c1();
        }
      }
      else if (("ROLES".equals(paramclass_1363.b29())) && (a6().b().isEmpty()))
      {
        if ((paramclass_1363 = a46()) != null)
        {
          paramclass_939 = paramclass_1363;
          paramclass_1363 = this;
          e2(true);
          new class_413(paramclass_1363, paramclass_1363.a6(), paramclass_939).c1();
          return;
        }
        a6().a4().b1("ERROR: not in a faction");
      }
    }
  }
  
  public final boolean a1()
  {
    return false;
  }
  
  public final void b()
  {
    e2(true);
    new class_436(a6()).c1();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_423
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */