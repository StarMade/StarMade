import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import org.schema.schine.network.client.ClientState;

public final class class_288
  extends class_1402
{
  private class_781 field_89;
  private String jdField_field_90_of_type_JavaLangString;
  private boolean jdField_field_90_of_type_Boolean;
  
  public class_288(ClientState paramClientState, class_781 paramclass_781, String paramString, int paramInt)
  {
    super(paramClientState, 510.0F, 30.0F, new Vector4f());
    this.field_89 = paramclass_781;
    this.jdField_field_90_of_type_JavaLangString = paramString;
    a72(paramInt);
  }
  
  public final void a72(int paramInt)
  {
    this.field_89 = (paramInt % 2 == 0 ? new Vector4f(0.0F, 0.0F, 0.0F, 0.0F) : new Vector4f(0.1F, 0.1F, 0.1F, 0.5F));
  }
  
  public final void b()
  {
    super.b();
  }
  
  public final void c()
  {
    if (!this.jdField_field_90_of_type_Boolean)
    {
      super.c();
      class_930 localclass_9301;
      (localclass_9301 = new class_930(10, 10, a24())).a136(this.jdField_field_90_of_type_JavaLangString);
      class_930 localclass_9302;
      (localclass_9302 = new class_930(10, 10, a24())).a136(this.field_89.jdField_field_136_of_type_JavaLangString);
      class_930 localclass_9303;
      (localclass_9303 = new class_930(10, 10, a24())).a136(this.field_89.jdField_field_139_of_type_JavaLangString);
      class_930 localclass_9304;
      (localclass_9304 = new class_930(10, 10, a24())).a136(String.valueOf(this.field_89.jdField_field_139_of_type_Int));
      class_930 localclass_9305 = new class_930(10, 10, a24());
      if (this.field_89.jdField_field_136_of_type_Float > 0.0F) {
        localclass_9305.a136(class_41.a2(this.field_89.jdField_field_136_of_type_Float) + "/" + class_781.field_183);
      } else {
        localclass_9305.a136("n/a");
      }
      a24();
      localclass_9302.a83().field_615 += 7.0F;
      localclass_9302.a83().field_615 += 210.0F;
      localclass_9303.a83().field_615 += 140.0F;
      localclass_9304.a83().field_615 += 100.0F;
      localclass_9301.a83().field_616 = 5.0F;
      localclass_9302.a83().field_616 = 5.0F;
      localclass_9303.a83().field_616 = 5.0F;
      localclass_9304.a83().field_616 = 5.0F;
      localclass_9305.a83().field_616 = 5.0F;
      a9(localclass_9301);
      a9(localclass_9302);
      a9(localclass_9303);
      a9(localclass_9304);
      a9(localclass_9305);
      this.jdField_field_90_of_type_Boolean = true;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_288
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */