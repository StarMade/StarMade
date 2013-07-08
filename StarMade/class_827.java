import java.util.HashMap;
import org.schema.schine.network.client.ClientState;

public final class class_827
  extends class_1414
{
  private class_968 field_89;
  private class_1412 field_90;
  
  public class_827(ClientState paramClientState, class_1412 paramclass_1412)
  {
    super(paramClientState, 530.0F, 60.0F);
    this.jdField_field_90_of_type_Class_1412 = paramclass_1412;
  }
  
  public final void b()
  {
    Object localObject;
    class_773 localclass_773;
    if (((localObject = (class_371)a24()).a20().h1() != 0) && ((localclass_773 = ((class_371)localObject).a45().a156(((class_371)localObject).a20().h1())) != null) && ((localObject = (class_758)localclass_773.a162().get(((class_371)localObject).a20().getName())) != null) && (((class_758)localObject).a148(localclass_773))) {
      super.b();
    }
  }
  
  public final void c()
  {
    this.jdField_field_89_of_type_Class_968 = new class_968(this.jdField_field_90_of_type_Float, this.jdField_field_89_of_type_Float, a24());
    class_1414 localclass_1414 = new class_1414(a24(), 100.0F, 40.0F);
    class_930 localclass_9301;
    (localclass_9301 = new class_930(100, 20, a24())).a136("Public Faction");
    class_829 localclass_829 = new class_829(a24());
    class_930 localclass_9302;
    (localclass_9302 = new class_930(100, 20, a24())).a136("Consider neutral enemy");
    class_839 localclass_839 = new class_839(a24());
    class_930 localclass_9303;
    (localclass_9303 = new class_930(100, 20, a24())).a136("Declare war on hostile action");
    class_841 localclass_841 = new class_841(a24());
    class_934 localclass_9341;
    (localclass_9341 = new class_934(a24(), 90, 20, "Post News", this.jdField_field_90_of_type_Class_1412, ((class_371)a24()).a14().field_4.field_4.field_4)).field_89 = "POST_NEWS";
    localclass_9341.a83().field_615 = 260.0F;
    class_835 localclass_835;
    (localclass_835 = new class_835(a24(), "Roles", this.jdField_field_90_of_type_Class_1412, ((class_371)a24()).a14().field_4.field_4.field_4)).field_89 = "ROLES";
    localclass_835.a83().field_615 = 430.0F;
    class_934 localclass_9342;
    (localclass_9342 = new class_934(a24(), 120, 20, "Edit Description", this.jdField_field_90_of_type_Class_1412, ((class_371)a24()).a14().field_4.field_4.field_4)).field_89 = "EDIT_DESC";
    localclass_9342.a83().field_615 = 130.0F;
    class_934 localclass_9343;
    (localclass_9343 = new class_934(a24(), 60, 20, "Offers", new class_837(this), ((class_371)a24()).a14().field_4.field_4.field_4)).a83().field_615 = 360.0F;
    localclass_829.a83().field_616 = 23.0F;
    localclass_9301.a83().field_615 = 35.0F;
    localclass_9301.a83().field_616 = 32.0F;
    localclass_841.a83().field_615 = 145.0F;
    localclass_841.a83().field_616 = 23.0F;
    localclass_9303.a83().field_615 = 180.0F;
    localclass_9303.a83().field_616 = 32.0F;
    localclass_839.a83().field_615 = 350.0F;
    localclass_839.a83().field_616 = 23.0F;
    localclass_9302.a83().field_615 = 385.0F;
    localclass_9302.a83().field_616 = 32.0F;
    localclass_1414.a9(localclass_829);
    localclass_1414.a9(localclass_9301);
    localclass_1414.a9(localclass_9302);
    localclass_1414.a9(localclass_839);
    localclass_1414.a9(localclass_9303);
    localclass_1414.a9(localclass_841);
    localclass_1414.a9(localclass_9341);
    localclass_1414.a9(localclass_9342);
    localclass_1414.a9(localclass_9343);
    localclass_1414.a9(localclass_835);
    this.jdField_field_89_of_type_Class_968.c7(localclass_1414);
    a9(this.jdField_field_89_of_type_Class_968);
    super.c();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_827
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */