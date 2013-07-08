import java.util.Observable;
import java.util.Observer;
import org.schema.schine.network.client.ClientState;

public final class class_84
  extends class_1363
  implements Observer
{
  private class_255 jdField_field_89_of_type_Class_255;
  private class_815 jdField_field_89_of_type_Class_815;
  private class_819 jdField_field_89_of_type_Class_819;
  private class_1414 jdField_field_89_of_type_Class_1414;
  private class_1414 jdField_field_90_of_type_Class_1414;
  private class_1414 jdField_field_92_of_type_Class_1414;
  private class_930 jdField_field_89_of_type_Class_930;
  private class_930 jdField_field_90_of_type_Class_930;
  private class_930 jdField_field_92_of_type_Class_930;
  private class_972 jdField_field_89_of_type_Class_972;
  private boolean jdField_field_89_of_type_Boolean = true;
  private class_1363 jdField_field_89_of_type_Class_1363;
  
  public class_84(ClientState paramClientState)
  {
    super(paramClientState);
  }
  
  public final void a2() {}
  
  public final void b()
  {
    if (this.jdField_field_89_of_type_Boolean)
    {
      class_84 localclass_84 = this;
      if (this.jdField_field_89_of_type_Class_1363 != null) {
        localclass_84.jdField_field_89_of_type_Class_972.b2(localclass_84.jdField_field_89_of_type_Class_1363);
      }
      localclass_84.jdField_field_89_of_type_Boolean = false;
      if (localclass_84.a8().jdField_field_4_of_type_Class_406.field_5)
      {
        localclass_84.jdField_field_89_of_type_Class_972.a9(localclass_84.jdField_field_89_of_type_Class_255);
        localclass_84.jdField_field_89_of_type_Class_1363 = localclass_84.jdField_field_89_of_type_Class_255;
        localclass_84.a7(localclass_84.jdField_field_92_of_type_Class_930);
      }
      else if (localclass_84.a8().jdField_field_4_of_type_Class_350.field_5)
      {
        localclass_84.jdField_field_89_of_type_Class_972.a9(localclass_84.jdField_field_89_of_type_Class_819);
        localclass_84.jdField_field_89_of_type_Class_1363 = localclass_84.jdField_field_89_of_type_Class_819;
        localclass_84.a7(localclass_84.jdField_field_89_of_type_Class_930);
      }
      else if (localclass_84.a8().jdField_field_4_of_type_Class_348.field_5)
      {
        localclass_84.jdField_field_89_of_type_Class_972.a9(localclass_84.jdField_field_89_of_type_Class_815);
        localclass_84.jdField_field_89_of_type_Class_1363 = localclass_84.jdField_field_89_of_type_Class_815;
        localclass_84.a7(localclass_84.jdField_field_90_of_type_Class_930);
      }
      else
      {
        localclass_84.jdField_field_89_of_type_Boolean = true;
      }
    }
    k();
  }
  
  private void a7(class_930 paramclass_930)
  {
    this.jdField_field_92_of_type_Class_930.field_89.field_1779 = 1.0F;
    this.jdField_field_92_of_type_Class_930.field_89.field_1776 = 0.5F;
    this.jdField_field_92_of_type_Class_930.field_89.field_1777 = 0.5F;
    this.jdField_field_92_of_type_Class_930.field_89.field_1778 = 0.5F;
    this.jdField_field_89_of_type_Class_930.field_89.field_1779 = 1.0F;
    this.jdField_field_89_of_type_Class_930.field_89.field_1776 = 0.5F;
    this.jdField_field_89_of_type_Class_930.field_89.field_1777 = 0.5F;
    this.jdField_field_89_of_type_Class_930.field_89.field_1778 = 0.5F;
    this.jdField_field_90_of_type_Class_930.field_89.field_1779 = 1.0F;
    this.jdField_field_90_of_type_Class_930.field_89.field_1776 = 0.5F;
    this.jdField_field_90_of_type_Class_930.field_89.field_1777 = 0.5F;
    this.jdField_field_90_of_type_Class_930.field_89.field_1778 = 0.5F;
    paramclass_930.field_89.field_1779 = 1.0F;
    paramclass_930.field_89.field_1776 = 1.0F;
    paramclass_930.field_89.field_1777 = 1.0F;
    paramclass_930.field_89.field_1778 = 1.0F;
  }
  
  public final void c()
  {
    this.jdField_field_89_of_type_Class_972 = new class_972(class_969.a2().a5("faction-personal-panel-gui-"), a24());
    a8().addObserver(this);
    this.jdField_field_89_of_type_Class_819 = new class_819(a24());
    this.jdField_field_89_of_type_Class_815 = new class_815(a24());
    this.jdField_field_89_of_type_Class_255 = new class_255(a24());
    this.jdField_field_89_of_type_Class_819.a161(264.0F, 105.0F, 0.0F);
    this.jdField_field_89_of_type_Class_815.a161(264.0F, 105.0F, 0.0F);
    this.jdField_field_89_of_type_Class_255.a161(264.0F, 105.0F, 0.0F);
    this.jdField_field_89_of_type_Class_819.c();
    this.jdField_field_89_of_type_Class_815.c();
    this.jdField_field_89_of_type_Class_255.c();
    this.jdField_field_89_of_type_Class_1414 = new class_1414(a24(), 180.0F, 30.0F);
    this.jdField_field_92_of_type_Class_1414 = new class_1414(a24(), 180.0F, 30.0F);
    this.jdField_field_90_of_type_Class_1414 = new class_1414(a24(), 180.0F, 30.0F);
    this.jdField_field_89_of_type_Class_930 = new class_930(180, 40, class_29.d(), a24());
    this.jdField_field_92_of_type_Class_930 = new class_930(180, 40, class_29.d(), a24());
    this.jdField_field_90_of_type_Class_930 = new class_930(180, 40, class_29.d(), a24());
    this.jdField_field_89_of_type_Class_930.a136("Personal");
    this.jdField_field_90_of_type_Class_930.a136("Faction");
    this.jdField_field_92_of_type_Class_930.a136("Faction Hub");
    this.jdField_field_89_of_type_Class_1414.a9(this.jdField_field_89_of_type_Class_930);
    this.jdField_field_92_of_type_Class_1414.a9(this.jdField_field_92_of_type_Class_930);
    this.jdField_field_90_of_type_Class_1414.a9(this.jdField_field_90_of_type_Class_930);
    this.jdField_field_89_of_type_Class_972.a9(this.jdField_field_89_of_type_Class_1414);
    this.jdField_field_89_of_type_Class_972.a9(this.jdField_field_90_of_type_Class_1414);
    this.jdField_field_89_of_type_Class_972.a9(this.jdField_field_92_of_type_Class_1414);
    this.jdField_field_89_of_type_Class_1414.a161(264.0F, 74.0F, 0.0F);
    this.jdField_field_90_of_type_Class_1414.a161(444.0F, 74.0F, 0.0F);
    this.jdField_field_92_of_type_Class_1414.a161(625.0F, 74.0F, 0.0F);
    this.jdField_field_89_of_type_Class_1414.field_96 = true;
    this.jdField_field_90_of_type_Class_1414.field_96 = true;
    this.jdField_field_92_of_type_Class_1414.field_96 = true;
    this.jdField_field_89_of_type_Class_1414.field_89 = "PERSONAL";
    this.jdField_field_89_of_type_Class_1414.a141(a8());
    this.jdField_field_90_of_type_Class_1414.field_89 = "LOCAL";
    this.jdField_field_90_of_type_Class_1414.a141(a8());
    this.jdField_field_92_of_type_Class_1414.field_89 = "HUB";
    this.jdField_field_92_of_type_Class_1414.a141(a8());
    this.jdField_field_89_of_type_Class_1363 = this.jdField_field_89_of_type_Class_819;
    a9(this.jdField_field_89_of_type_Class_972);
    h3(48);
    this.field_96 = true;
  }
  
  private class_423 a8()
  {
    return ((class_371)a24()).a14().field_4.field_4.field_4;
  }
  
  public final float a3()
  {
    return this.jdField_field_89_of_type_Class_972.a3();
  }
  
  public final float b1()
  {
    return this.jdField_field_89_of_type_Class_972.b1();
  }
  
  public final void update(Observable paramObservable, Object paramObject)
  {
    this.jdField_field_89_of_type_Boolean = true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_84
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */