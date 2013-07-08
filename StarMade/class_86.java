import javax.vecmath.Vector4f;
import org.schema.schine.network.client.ClientState;

public class class_86
  extends class_196
{
  private final class_773 jdField_field_89_of_type_Class_773;
  private final class_773 jdField_field_90_of_type_Class_773;
  private final class_344 jdField_field_89_of_type_Class_344;
  
  public class_86(ClientState paramClientState, class_773 paramclass_7731, class_773 paramclass_7732, class_344 paramclass_344)
  {
    super(paramClientState, paramclass_344, "Relationship to " + paramclass_7732.a(), "");
    this.jdField_field_89_of_type_Class_773 = paramclass_7731;
    this.jdField_field_90_of_type_Class_773 = paramclass_7732;
    this.jdField_field_89_of_type_Class_344 = paramclass_344;
  }
  
  public final void c()
  {
    super.c();
    class_930 localclass_930 = new class_930(100, 10, a24());
    class_762 localclass_762 = ((class_371)a24()).a45().a159(this.jdField_field_89_of_type_Class_773.a3(), this.jdField_field_90_of_type_Class_773.a3());
    class_934 localclass_9341 = new class_934(a24(), 100, 25, new Vector4f(0.7F, 0.0F, 0.0F, 1.0F), new Vector4f(1.0F, 1.0F, 1.0F, 1.0F), class_29.h(), "Declare War", this.jdField_field_89_of_type_Class_344);
    class_934 localclass_9342 = new class_934(a24(), 100, 25, new Vector4f(0.7F, 0.0F, 0.7F, 1.0F), new Vector4f(1.0F, 1.0F, 1.0F, 1.0F), class_29.h(), "Offer Peace", this.jdField_field_89_of_type_Class_344);
    class_934 localclass_9343 = new class_934(a24(), 100, 25, new Vector4f(0.0F, 0.0F, 0.7F, 1.0F), new Vector4f(1.0F, 1.0F, 1.0F, 1.0F), class_29.h(), "Offer Alliance", this.jdField_field_89_of_type_Class_344);
    localclass_9342.a83().field_616 = 15.0F;
    localclass_9341.a83().field_616 = 45.0F;
    localclass_9343.a83().field_616 = 75.0F;
    class_934 localclass_9344 = new class_934(a24(), 100, 25, new Vector4f(0.7F, 0.0F, 0.7F, 1.0F), new Vector4f(1.0F, 1.0F, 1.0F, 1.0F), class_29.h(), "Revoke Peace Offer", this.jdField_field_89_of_type_Class_344);
    class_934 localclass_9345 = new class_934(a24(), 100, 25, new Vector4f(0.0F, 0.0F, 0.7F, 1.0F), new Vector4f(1.0F, 1.0F, 1.0F, 1.0F), class_29.h(), "Revoke Alliance Offer", this.jdField_field_89_of_type_Class_344);
    localclass_9344.a83().field_616 = 45.0F;
    localclass_9345.a83().field_616 = 75.0F;
    switch (class_88.field_618[localclass_762.ordinal()])
    {
    case 1: 
      localclass_930.a136("You are on war with " + this.jdField_field_90_of_type_Class_773.a());
      this.jdField_field_89_of_type_Class_1414.a9(localclass_9342);
      this.jdField_field_89_of_type_Class_1414.a9(localclass_9343);
      break;
    case 2: 
      localclass_930.a136("You are allied to " + this.jdField_field_90_of_type_Class_773.a());
      this.jdField_field_89_of_type_Class_1414.a9(localclass_9341);
      this.jdField_field_89_of_type_Class_1414.a9(localclass_9342);
      break;
    case 3: 
      localclass_930.a136("You are on peace with " + this.jdField_field_90_of_type_Class_773.a());
      this.jdField_field_89_of_type_Class_1414.a9(localclass_9341);
      this.jdField_field_89_of_type_Class_1414.a9(localclass_9343);
      break;
    default: 
      if (!jdField_field_90_of_type_Boolean) {
        throw new AssertionError();
      }
      break;
    }
    this.jdField_field_89_of_type_Class_1414.a9(localclass_930);
    localclass_9341.field_89 = "WAR";
    localclass_9342.field_89 = "PEACE";
    localclass_9343.field_89 = "ALLY";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_86
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */