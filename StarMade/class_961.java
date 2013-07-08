import java.util.ArrayList;
import java.util.List;
import org.schema.schine.network.client.ClientState;

public class class_961
  extends class_1414
  implements class_1412
{
  private boolean field_89;
  protected class_1363 field_89;
  protected class_1363 field_90;
  protected final class_964 field_89;
  private boolean field_90;
  private boolean field_92 = true;
  
  public class_961(ClientState paramClientState)
  {
    this(paramClientState, new class_964(paramClientState));
  }
  
  public class_961(ClientState paramClientState, class_1363 paramclass_13631, class_1363 paramclass_13632)
  {
    this(paramClientState, new class_964(paramClientState), paramclass_13631, paramclass_13632);
  }
  
  private class_961(ClientState paramClientState, class_964 paramclass_964)
  {
    this(paramClientState, paramclass_964, null, null);
  }
  
  public class_961(ClientState paramClientState, class_964 paramclass_964, class_1363 paramclass_13631, class_1363 paramclass_13632)
  {
    super(paramClientState);
    this.jdField_field_89_of_type_Boolean = true;
    this.jdField_field_90_of_type_Boolean = true;
    this.jdField_field_89_of_type_Class_964 = paramclass_964;
    this.jdField_field_89_of_type_Class_1363 = paramclass_13631;
    this.jdField_field_90_of_type_Class_1363 = paramclass_13632;
  }
  
  public final void a1(class_1363 paramclass_1363, class_939 paramclass_939)
  {
    if ((paramclass_939.a()) && (b3()))
    {
      if (paramclass_1363.field_89.equals("EXPAND"))
      {
        class_969.b("0022_menu_ui - enter");
        this.jdField_field_89_of_type_Boolean = false;
        this.jdField_field_90_of_type_Boolean = true;
        return;
      }
      if (paramclass_1363.field_89.equals("COLLAPSE"))
      {
        class_969.b("0022_menu_ui - enter");
        this.jdField_field_89_of_type_Boolean = true;
        this.jdField_field_90_of_type_Boolean = true;
        return;
      }
      if (!field_93) {
        throw new AssertionError("not known command: '" + paramclass_1363.field_89 + "'");
      }
    }
  }
  
  protected boolean b3()
  {
    return true;
  }
  
  public final void a2() {}
  
  public final void b()
  {
    if (this.field_92)
    {
      c();
      this.field_92 = false;
    }
    if (this.jdField_field_89_of_type_Boolean) {
      this.jdField_field_89_of_type_Class_964.a83().field_616 = this.jdField_field_89_of_type_Class_1363.a3();
    } else {
      this.jdField_field_89_of_type_Class_964.a83().field_616 = this.jdField_field_90_of_type_Class_1363.a3();
    }
    class_961 localclass_9611 = this;
    if (this.jdField_field_90_of_type_Boolean)
    {
      class_961 localclass_9612 = localclass_9611;
      while (0 < localclass_9612.field_89.size())
      {
        Object localObject1;
        if (((localObject1 = (class_984)localclass_9612.field_89.get(0)) instanceof class_1363))
        {
          localclass_9612.b2((class_1363)localObject1);
        }
        else
        {
          Object localObject2 = localObject1;
          localObject1 = localclass_9612;
          if (localObject2 != null)
          {
            localObject2.b25(null);
            ((class_984)localObject1).field_89.remove(localObject2);
          }
        }
      }
      if (localclass_9611.jdField_field_89_of_type_Boolean)
      {
        localclass_9611.b2(localclass_9611.jdField_field_89_of_type_Class_964);
        localclass_9611.b2(localclass_9611.jdField_field_90_of_type_Class_1363);
        localclass_9611.a9(localclass_9611.jdField_field_89_of_type_Class_1363);
      }
      else
      {
        localclass_9611.a9(localclass_9611.jdField_field_89_of_type_Class_964);
        localclass_9611.a9(localclass_9611.jdField_field_90_of_type_Class_1363);
        localclass_9611.b2(localclass_9611.jdField_field_89_of_type_Class_1363);
      }
      localclass_9611.jdField_field_90_of_type_Boolean = false;
      localclass_9611.setChanged();
      localclass_9611.notifyObservers();
    }
    super.b();
  }
  
  public final float a3()
  {
    if (this.jdField_field_89_of_type_Boolean) {
      return this.jdField_field_89_of_type_Class_1363.a3();
    }
    return this.jdField_field_90_of_type_Class_1363.a3() + this.jdField_field_89_of_type_Class_964.a3() + 10.0F;
  }
  
  public final class_964 a140()
  {
    return this.jdField_field_89_of_type_Class_964;
  }
  
  public final float b1()
  {
    if (this.jdField_field_89_of_type_Boolean) {
      return this.jdField_field_89_of_type_Class_1363.b1();
    }
    return this.jdField_field_89_of_type_Class_964.b1();
  }
  
  public final boolean a_()
  {
    if (a154() != null) {
      return (((class_1363)a154()).a_()) && (super.a_());
    }
    return super.a_();
  }
  
  public final boolean a4()
  {
    return false;
  }
  
  public final void c()
  {
    class_930 localclass_930;
    if (this.jdField_field_89_of_type_Class_1363 == null)
    {
      (localclass_930 = new class_930(176, 30, class_29.g(), a24())).field_90 = new ArrayList();
      localclass_930.field_90.add("ENTER");
      this.jdField_field_89_of_type_Class_1363 = localclass_930;
    }
    if (this.jdField_field_90_of_type_Class_1363 == null)
    {
      (localclass_930 = new class_930(176, 30, class_29.g(), a24())).field_90 = new ArrayList();
      localclass_930.field_90.add("BACK");
      this.jdField_field_90_of_type_Class_1363 = localclass_930;
    }
    this.jdField_field_89_of_type_Class_1363.a141(this);
    this.jdField_field_89_of_type_Class_1363.field_89 = "EXPAND";
    this.jdField_field_89_of_type_Class_1363.field_96 = true;
    this.jdField_field_90_of_type_Class_1363.a141(this);
    this.jdField_field_90_of_type_Class_1363.field_89 = "COLLAPSE";
    this.jdField_field_90_of_type_Class_1363.field_96 = true;
    a9(this.jdField_field_89_of_type_Class_1363);
    this.field_96 = true;
    this.jdField_field_89_of_type_Class_964.field_96 = true;
  }
  
  public final void a141(class_1412 paramclass_1412)
  {
    if (!field_93) {
      throw new AssertionError();
    }
    super.a141(paramclass_1412);
  }
  
  public final void a29(boolean paramBoolean)
  {
    this.jdField_field_89_of_type_Boolean = (!paramBoolean);
    this.jdField_field_90_of_type_Boolean = true;
  }
  
  public final boolean c1()
  {
    return !this.jdField_field_89_of_type_Boolean;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_961
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */