import java.util.Date;
import java.util.List;
import javax.vecmath.Vector4f;
import org.schema.schine.network.client.ClientState;

final class class_123
  extends class_959
  implements class_1412
{
  private class_1402 jdField_field_89_of_type_Class_1402;
  private class_777 jdField_field_89_of_type_Class_777;
  
  public class_123(ClientState paramClientState, class_777 paramclass_777, int paramInt)
  {
    super(paramClientState);
    this.jdField_field_89_of_type_Class_777 = paramclass_777;
    this.jdField_field_89_of_type_Class_1402 = new class_1402(a24(), 410.0F, 45.0F, paramInt % 2 == 0 ? new Vector4f(0.1F, 0.1F, 0.1F, 1.0F) : new Vector4f(0.2F, 0.2F, 0.2F, 2.0F));
    this.field_89 = this.jdField_field_89_of_type_Class_1402;
    this.field_90 = this.jdField_field_89_of_type_Class_1402;
  }
  
  public final void c()
  {
    class_930 localclass_930;
    (localclass_930 = new class_930(200, 30, a24())).a136(new Date(this.jdField_field_89_of_type_Class_777.a28()).toString());
    Object localObject = ((class_371)a24()).a45().a156(this.jdField_field_89_of_type_Class_777.a3());
    localclass_930.field_90.add("to: " + this.jdField_field_89_of_type_Class_777.a());
    localclass_930.field_90.add(localObject != null ? ((class_773)localObject).a() : "(ERROR)unknown");
    (localObject = new class_934(a24(), 50, 20, "Revoke", this)).field_89 = "REVOKE";
    this.jdField_field_89_of_type_Class_1402.a9(localclass_930);
    this.jdField_field_89_of_type_Class_1402.a9((class_1363)localObject);
    ((class_934)localObject).a83().field_615 = 300.0F;
    super.c();
  }
  
  public final void a1(class_1363 paramclass_1363, class_939 paramclass_939)
  {
    if ((paramclass_939.a()) && ("REVOKE".equals(paramclass_1363.field_89))) {
      ((class_371)a24()).a45().b27(this.jdField_field_89_of_type_Class_777);
    }
  }
  
  public final boolean a4()
  {
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_123
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */