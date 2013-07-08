import javax.vecmath.Vector4f;
import org.schema.schine.network.client.ClientState;

public final class class_1410
  extends class_1363
{
  private class_1402 jdField_field_89_of_type_Class_1402;
  private class_968 jdField_field_89_of_type_Class_968;
  private class_930 jdField_field_89_of_type_Class_930;
  
  public class_1410(ClientState paramClientState)
  {
    super(paramClientState);
    this.jdField_field_89_of_type_Class_930 = new class_930(400, 150, class_29.n(), paramClientState);
    this.jdField_field_89_of_type_Class_930.field_89 = 100;
    this.jdField_field_89_of_type_Class_930.field_90 = a24().getGeneralChatLog();
    this.jdField_field_89_of_type_Class_968 = new class_966(paramClientState);
    this.jdField_field_89_of_type_Class_1402 = new class_1402(paramClientState, 400.0F, 150.0F, new Vector4f(1.0F, 1.0F, 1.0F, 0.1F));
  }
  
  public final void a2() {}
  
  public final void b()
  {
    this.jdField_field_89_of_type_Class_1402.b();
  }
  
  public final void c()
  {
    this.jdField_field_89_of_type_Class_1402.field_92 = 5.0F;
    this.jdField_field_89_of_type_Class_1402.a9(this.jdField_field_89_of_type_Class_968);
    this.jdField_field_89_of_type_Class_968.c7(this.jdField_field_89_of_type_Class_930);
    this.jdField_field_89_of_type_Class_1402.c();
    this.jdField_field_89_of_type_Class_968.c();
    this.jdField_field_89_of_type_Class_930.c();
  }
  
  public final float a3()
  {
    return 0.0F;
  }
  
  public final float b1()
  {
    return 0.0F;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1410
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */