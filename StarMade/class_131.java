import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import javax.vecmath.Vector4f;
import org.schema.schine.network.client.ClientState;

final class class_131
  extends class_959
{
  private class_1402 jdField_field_89_of_type_Class_1402;
  private class_629 jdField_field_89_of_type_Class_629;
  
  public class_131(ClientState paramClientState, class_629 paramclass_629, int paramInt)
  {
    super(paramClientState);
    this.jdField_field_89_of_type_Class_629 = paramclass_629;
    this.jdField_field_89_of_type_Class_1402 = new class_1402(a24(), 410.0F, 100.0F, paramInt % 2 == 0 ? new Vector4f(0.1F, 0.1F, 0.1F, 1.0F) : new Vector4f(0.2F, 0.2F, 0.2F, 1.0F));
    this.field_89 = this.jdField_field_89_of_type_Class_1402;
    this.field_90 = this.jdField_field_89_of_type_Class_1402;
  }
  
  public final void c()
  {
    super.c();
    class_930 localclass_930;
    (localclass_930 = new class_930(200, 80, a24())).field_90 = new ArrayList();
    class_773 localclass_7731 = ((class_371)a24()).a45().a156(this.jdField_field_89_of_type_Class_629.field_136);
    class_773 localclass_7732 = ((class_371)a24()).a45().a156(this.jdField_field_89_of_type_Class_629.field_139);
    if ((localclass_7731 != null) && (localclass_7732 != null))
    {
      if (this.jdField_field_89_of_type_Class_629.a7()) {
        localclass_930.field_90.add("WAR DECLARATION");
      } else if (this.jdField_field_89_of_type_Class_629.b2()) {
        localclass_930.field_90.add("ALLIANCE PROPOSAL");
      } else if (this.jdField_field_89_of_type_Class_629.c3()) {
        localclass_930.field_90.add("PEACE OFFER");
      }
      localclass_930.field_90.add("From: [" + localclass_7731.a() + "]" + this.jdField_field_89_of_type_Class_629.b());
      for (Object localObject2 : this.jdField_field_89_of_type_Class_629.a().split("\\\\n")) {
        localclass_930.field_90.add(localObject2);
      }
      ??? = new class_934(a24(), 80, 18, "Accept", new class_129(this));
      class_934 localclass_934 = new class_934(a24(), 80, 18, "Decline", new class_135(this));
      ((class_934)???).a83().field_615 = 220.0F;
      localclass_934.a83().field_615 = 310.0F;
      this.jdField_field_89_of_type_Class_1402.a9(localclass_930);
      this.jdField_field_89_of_type_Class_1402.a9((class_1363)???);
      this.jdField_field_89_of_type_Class_1402.a9(localclass_934);
      System.err.println("[GUI] attached faction offer! " + localclass_7731.a() + " -> " + localclass_7732.a());
      return;
    }
    System.err.println("Invalid offer: " + this.jdField_field_89_of_type_Class_629.field_136 + " / " + this.jdField_field_89_of_type_Class_629.field_139);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_131
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */