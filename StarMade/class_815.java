import java.util.ArrayList;
import java.util.List;
import org.schema.schine.network.client.ClientState;

public final class class_815
  extends class_1363
{
  private class_173 jdField_field_89_of_type_Class_173;
  private class_1414 jdField_field_89_of_type_Class_1414;
  private class_1414 field_90;
  private class_827 jdField_field_89_of_type_Class_827;
  private class_930 jdField_field_89_of_type_Class_930;
  class_773 jdField_field_89_of_type_Class_773;
  
  public class_815(ClientState paramClientState)
  {
    super(paramClientState);
  }
  
  public final void a2() {}
  
  public final void b()
  {
    k();
  }
  
  private class_423 a8()
  {
    return ((class_371)a24()).a14().field_4.field_4.field_4;
  }
  
  public final void c()
  {
    this.jdField_field_89_of_type_Class_1414 = new class_1414(a24(), 400.0F, 40.0F);
    this.field_90 = new class_1414(a24(), 400.0F, 40.0F);
    this.jdField_field_89_of_type_Class_173 = new class_817(a24(), a8());
    this.jdField_field_89_of_type_Class_827 = new class_827(a24(), ((class_371)a24()).a14().field_4.field_4.field_4);
    this.jdField_field_89_of_type_Class_827.c();
    this.field_90.a83().field_616 = 40.0F;
    this.jdField_field_89_of_type_Class_827.a83().field_616 = 80.0F;
    this.jdField_field_89_of_type_Class_1414.a9(this.jdField_field_89_of_type_Class_827);
    this.jdField_field_89_of_type_Class_1414.c();
    this.jdField_field_89_of_type_Class_930 = new class_930(100, 40, class_29.d(), a24());
    this.jdField_field_89_of_type_Class_930.field_90 = new ArrayList();
    this.jdField_field_89_of_type_Class_930.field_90.add(new class_811(this));
    class_930 localclass_930;
    (localclass_930 = new class_930(100, 30, class_29.h(), a24())).a136(new class_813(this));
    class_825 localclass_825;
    (localclass_825 = new class_825(this, a24(), "Abandon Home", new class_823(this), a8())).a83().field_615 = 400.0F;
    this.field_90.a9(localclass_930);
    this.field_90.a9(localclass_825);
    this.jdField_field_89_of_type_Class_1414.a9(this.jdField_field_89_of_type_Class_930);
    this.jdField_field_89_of_type_Class_173.a83().field_616 = 140.0F;
    this.jdField_field_89_of_type_Class_173.c();
    a9(this.jdField_field_89_of_type_Class_173);
    a9(this.jdField_field_89_of_type_Class_1414);
    a9(this.field_90);
  }
  
  public final float a3()
  {
    if (this.jdField_field_89_of_type_Class_173 != null) {
      return this.jdField_field_89_of_type_Class_173.a3();
    }
    return 0.0F;
  }
  
  public final float b1()
  {
    if (this.jdField_field_89_of_type_Class_173 != null) {
      return this.jdField_field_89_of_type_Class_173.b1();
    }
    return 0.0F;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_815
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */