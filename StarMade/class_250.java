import java.util.Observable;
import java.util.Observer;
import org.schema.game.network.objects.NetworkPlayer;
import org.schema.schine.network.client.ClientState;
import org.schema.schine.network.objects.remote.RemoteBoolean;

public final class class_250
  extends class_1363
  implements Observer
{
  private class_972 jdField_field_89_of_type_Class_972;
  private class_1414 jdField_field_89_of_type_Class_1414;
  private class_1414 jdField_field_90_of_type_Class_1414;
  private class_1414 jdField_field_92_of_type_Class_1414;
  private class_930 jdField_field_89_of_type_Class_930;
  private class_930 jdField_field_90_of_type_Class_930;
  private class_930 jdField_field_92_of_type_Class_930;
  private class_164 jdField_field_89_of_type_Class_164;
  private class_266 jdField_field_89_of_type_Class_266;
  private class_278 jdField_field_89_of_type_Class_278;
  private class_1363 jdField_field_89_of_type_Class_1363;
  private boolean jdField_field_89_of_type_Boolean;
  
  public class_250(ClientState paramClientState)
  {
    super(paramClientState);
    a14().addObserver(this);
    ((class_371)a24()).a20().a122().addObserver(this);
  }
  
  public final void a2() {}
  
  public final void b()
  {
    if (this.jdField_field_89_of_type_Boolean)
    {
      class_250 localclass_250 = this;
      if (this.jdField_field_89_of_type_Class_1363 != null) {
        localclass_250.jdField_field_89_of_type_Class_972.b2(localclass_250.jdField_field_89_of_type_Class_1363);
      }
      localclass_250.jdField_field_89_of_type_Boolean = false;
      if (localclass_250.a14().jdField_field_4_of_type_Class_430.field_5)
      {
        localclass_250.jdField_field_89_of_type_Class_972.a9(localclass_250.jdField_field_89_of_type_Class_164);
        localclass_250.jdField_field_89_of_type_Class_1363 = localclass_250.jdField_field_89_of_type_Class_164;
        localclass_250.a7(localclass_250.jdField_field_89_of_type_Class_930);
      }
      else if (localclass_250.a14().jdField_field_4_of_type_Class_425.field_5)
      {
        localclass_250.jdField_field_89_of_type_Class_972.a9(localclass_250.jdField_field_89_of_type_Class_266);
        localclass_250.jdField_field_89_of_type_Class_1363 = localclass_250.jdField_field_89_of_type_Class_266;
        localclass_250.a7(localclass_250.jdField_field_92_of_type_Class_930);
      }
      else if (localclass_250.a14().jdField_field_4_of_type_Class_426.field_5)
      {
        localclass_250.jdField_field_89_of_type_Class_972.a9(localclass_250.jdField_field_89_of_type_Class_278);
        localclass_250.jdField_field_89_of_type_Class_1363 = localclass_250.jdField_field_89_of_type_Class_278;
        localclass_250.a7(localclass_250.jdField_field_90_of_type_Class_930);
      }
      else
      {
        localclass_250.jdField_field_89_of_type_Boolean = true;
      }
    }
    if (!((Boolean)((class_371)a24()).a20().a127().isAdminClient.get()).booleanValue()) {
      this.jdField_field_90_of_type_Class_930.field_89.field_1779 = 0.0F;
    }
    k();
  }
  
  private void a7(class_930 paramclass_930)
  {
    this.jdField_field_90_of_type_Class_930.field_89.field_1779 = 1.0F;
    this.jdField_field_90_of_type_Class_930.field_89.field_1776 = 0.5F;
    this.jdField_field_90_of_type_Class_930.field_89.field_1777 = 0.5F;
    this.jdField_field_90_of_type_Class_930.field_89.field_1778 = 0.5F;
    this.jdField_field_89_of_type_Class_930.field_89.field_1779 = 1.0F;
    this.jdField_field_89_of_type_Class_930.field_89.field_1776 = 0.5F;
    this.jdField_field_89_of_type_Class_930.field_89.field_1777 = 0.5F;
    this.jdField_field_89_of_type_Class_930.field_89.field_1778 = 0.5F;
    this.jdField_field_92_of_type_Class_930.field_89.field_1779 = 1.0F;
    this.jdField_field_92_of_type_Class_930.field_89.field_1776 = 0.5F;
    this.jdField_field_92_of_type_Class_930.field_89.field_1777 = 0.5F;
    this.jdField_field_92_of_type_Class_930.field_89.field_1778 = 0.5F;
    paramclass_930.field_89.field_1779 = 1.0F;
    paramclass_930.field_89.field_1776 = 1.0F;
    paramclass_930.field_89.field_1777 = 1.0F;
    paramclass_930.field_89.field_1778 = 1.0F;
    if (!((Boolean)((class_371)a24()).a20().a127().isAdminClient.get()).booleanValue()) {
      this.jdField_field_90_of_type_Class_930.field_89.field_1779 = 0.0F;
    }
  }
  
  public final void c()
  {
    this.jdField_field_89_of_type_Class_972 = new class_972(class_969.a2().a5("catalog-panel-gui-"), a24());
    this.jdField_field_89_of_type_Class_164 = new class_164(a24());
    this.jdField_field_89_of_type_Class_164.addObserver(this);
    this.jdField_field_89_of_type_Class_164.c();
    this.jdField_field_89_of_type_Class_266 = new class_266(a24());
    this.jdField_field_89_of_type_Class_266.addObserver(this);
    this.jdField_field_89_of_type_Class_266.c();
    this.jdField_field_89_of_type_Class_278 = new class_278(a24());
    this.jdField_field_89_of_type_Class_278.addObserver(this);
    this.jdField_field_89_of_type_Class_278.c();
    this.jdField_field_89_of_type_Class_164.a161(264.0F, 105.0F, 0.0F);
    this.jdField_field_89_of_type_Class_266.a161(264.0F, 105.0F, 0.0F);
    this.jdField_field_89_of_type_Class_278.a161(264.0F, 105.0F, 0.0F);
    this.jdField_field_89_of_type_Class_1414 = new class_1414(a24(), 180.0F, 30.0F);
    this.jdField_field_90_of_type_Class_1414 = new class_1414(a24(), 180.0F, 30.0F);
    this.jdField_field_92_of_type_Class_1414 = new class_1414(a24(), 180.0F, 30.0F);
    this.jdField_field_89_of_type_Class_930 = new class_930(180, 40, class_29.d(), a24());
    this.jdField_field_90_of_type_Class_930 = new class_930(180, 40, class_29.d(), a24());
    this.jdField_field_92_of_type_Class_930 = new class_930(180, 40, class_29.d(), a24());
    this.jdField_field_89_of_type_Class_930.a136("Personal");
    this.jdField_field_92_of_type_Class_930.a136("Available");
    this.jdField_field_90_of_type_Class_930.a136("Admin");
    this.jdField_field_89_of_type_Class_1414.a9(this.jdField_field_89_of_type_Class_930);
    this.jdField_field_90_of_type_Class_1414.a9(this.jdField_field_90_of_type_Class_930);
    this.jdField_field_92_of_type_Class_1414.a9(this.jdField_field_92_of_type_Class_930);
    this.jdField_field_89_of_type_Class_972.a9(this.jdField_field_89_of_type_Class_1414);
    this.jdField_field_89_of_type_Class_972.a9(this.jdField_field_92_of_type_Class_1414);
    this.jdField_field_89_of_type_Class_972.a9(this.jdField_field_90_of_type_Class_1414);
    this.jdField_field_89_of_type_Class_1414.a161(264.0F, 74.0F, 0.0F);
    this.jdField_field_92_of_type_Class_1414.a161(444.0F, 74.0F, 0.0F);
    this.jdField_field_90_of_type_Class_1414.a161(625.0F, 74.0F, 0.0F);
    this.jdField_field_89_of_type_Class_1414.field_96 = true;
    this.jdField_field_92_of_type_Class_1414.field_96 = true;
    this.jdField_field_90_of_type_Class_1414.field_96 = true;
    this.jdField_field_89_of_type_Class_1414.field_89 = "PERSONAL";
    this.jdField_field_89_of_type_Class_1414.a141(a14());
    this.jdField_field_92_of_type_Class_1414.field_89 = "ACCESSIBLE";
    this.jdField_field_92_of_type_Class_1414.a141(a14());
    this.jdField_field_90_of_type_Class_1414.field_89 = "ADMIN";
    this.jdField_field_90_of_type_Class_1414.a141(a14());
    this.jdField_field_89_of_type_Class_1363 = this.jdField_field_89_of_type_Class_164;
    a9(this.jdField_field_89_of_type_Class_972);
    h3(48);
  }
  
  private class_427 a14()
  {
    return ((class_371)a24()).a14().field_4.field_4.field_4;
  }
  
  public final void update(Observable paramObservable, Object paramObject)
  {
    this.jdField_field_89_of_type_Boolean = true;
    this.jdField_field_89_of_type_Class_164.update(paramObservable, paramObject);
    this.jdField_field_89_of_type_Class_266.update(paramObservable, paramObject);
    this.jdField_field_89_of_type_Class_278.update(paramObservable, paramObject);
  }
  
  public final float a3()
  {
    return this.jdField_field_89_of_type_Class_972.a3();
  }
  
  public final float b1()
  {
    return this.jdField_field_89_of_type_Class_972.b1();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_250
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */