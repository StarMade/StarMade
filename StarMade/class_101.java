import java.util.ArrayList;
import java.util.List;
import javax.vecmath.Vector4f;
import org.schema.schine.network.client.ClientState;

final class class_101
  extends class_1414
{
  private class_930 jdField_field_89_of_type_Class_930;
  private class_930 jdField_field_90_of_type_Class_930;
  private class_934 jdField_field_89_of_type_Class_934;
  private class_934 jdField_field_90_of_type_Class_934;
  private class_930 field_92;
  
  public class_101(class_860 paramclass_860, ClientState paramClientState, class_773 paramclass_773, String paramString, int paramInt)
  {
    super(paramClientState, 310.0F, 25.0F);
    this.jdField_field_89_of_type_Class_930 = new class_930(100, 10, paramClientState);
    this.jdField_field_90_of_type_Class_930 = new class_930(100, 10, paramClientState);
    this.field_92 = new class_930(100, 10, paramClientState);
    this.jdField_field_90_of_type_Class_930.a83().field_615 = 140.0F;
    this.field_92.a83().field_615 = 190.0F;
    class_930 localclass_930 = new class_930(100, 10, paramClientState);
    class_103 localclass_103 = new class_103(paramclass_773);
    class_92 localclass_92 = new class_92(paramclass_773);
    class_94 localclass_94 = new class_94(paramclass_773);
    class_96 localclass_96 = new class_96(this, paramclass_773);
    class_98 localclass_98 = new class_98(this, paramclass_773);
    this.jdField_field_89_of_type_Class_930.field_90 = new ArrayList();
    this.jdField_field_90_of_type_Class_930.field_90 = new ArrayList();
    this.field_92.field_90 = new ArrayList();
    this.jdField_field_89_of_type_Class_930.field_90.add(localclass_103);
    this.jdField_field_90_of_type_Class_930.field_90.add(localclass_92);
    this.field_92.field_90.add(localclass_94);
    localclass_930.a136(paramString);
    if (paramString.length() > 0) {
      this.jdField_field_89_of_type_Class_930.a83().field_615 = 8.0F;
    }
    this.jdField_field_90_of_type_Class_934 = new class_934(paramClientState, 90, 20, localclass_98, class_860.a120(paramclass_860));
    this.jdField_field_90_of_type_Class_934.field_89 = ("REL_" + paramclass_773.a3());
    this.jdField_field_90_of_type_Class_934.a83().field_615 = 310.0F;
    this.jdField_field_90_of_type_Class_934.b17(10, 2);
    this.jdField_field_89_of_type_Class_934 = new class_934(a24(), 70, 20, localclass_96, class_860.a120(paramclass_860));
    this.jdField_field_89_of_type_Class_934.a83().field_615 = 410.0F;
    this.jdField_field_89_of_type_Class_934.field_89 = ("JOIN_" + paramclass_773.a3());
    this.jdField_field_89_of_type_Class_930.a83().field_616 = 2.0F;
    this.jdField_field_90_of_type_Class_930.a83().field_616 = 2.0F;
    this.field_92.a83().field_616 = 2.0F;
    this.jdField_field_90_of_type_Class_934.a83().field_616 = 2.0F;
    this.jdField_field_89_of_type_Class_934.a83().field_616 = 2.0F;
    localclass_930.a83().field_616 = 2.0F;
    if (paramInt < 0)
    {
      a9(this.jdField_field_89_of_type_Class_930);
      a9(this.jdField_field_90_of_type_Class_930);
      a9(this.field_92);
      a9(this.jdField_field_90_of_type_Class_934);
      a9(this.jdField_field_89_of_type_Class_934);
      a9(localclass_930);
    }
    else
    {
      (paramclass_860 = new class_1402(a24(), 510.0F, a3(), paramInt % 2 == 0 ? new Vector4f(0.0F, 0.0F, 0.0F, 0.0F) : new Vector4f(0.1F, 0.1F, 0.1F, 0.5F))).a9(this.jdField_field_89_of_type_Class_930);
      paramclass_860.a9(this.jdField_field_90_of_type_Class_930);
      paramclass_860.a9(this.field_92);
      paramclass_860.a9(this.jdField_field_90_of_type_Class_934);
      paramclass_860.a9(this.jdField_field_89_of_type_Class_934);
      paramclass_860.a9(localclass_930);
      a9(paramclass_860);
    }
    this.field_89 = paramclass_773;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_101
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */