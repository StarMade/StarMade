import java.util.List;
import javax.vecmath.Vector4f;
import org.schema.schine.network.client.ClientState;

public final class class_819
  extends class_1363
{
  private class_934 jdField_field_89_of_type_Class_934;
  private class_934 field_90;
  private class_934 field_92;
  private class_1402 jdField_field_89_of_type_Class_1402;
  private class_930 jdField_field_89_of_type_Class_930;
  private class_167 jdField_field_89_of_type_Class_167;
  private class_138 jdField_field_89_of_type_Class_138;
  private class_934 field_93;
  
  public class_819(ClientState paramClientState)
  {
    super(paramClientState);
  }
  
  public final void a2() {}
  
  public final void b()
  {
    this.jdField_field_89_of_type_Class_930.field_90.set(0, "Current Faction: " + ((class_371)a24()).a20().a141().a());
    k();
  }
  
  public final void c()
  {
    class_423 localclass_423 = ((class_371)a24()).a14().field_4.field_4.field_4;
    this.jdField_field_89_of_type_Class_1402 = new class_1402(a24(), 540.0F, 30.0F, new Vector4f(0.0F, 0.3F, 0.0F, 0.5F));
    this.jdField_field_89_of_type_Class_1402.field_92 = 3.0F;
    this.jdField_field_89_of_type_Class_930 = new class_930(200, 30, class_29.d(), a24());
    this.jdField_field_89_of_type_Class_930.a136("Current Faction: ");
    this.jdField_field_89_of_type_Class_1402.a9(this.jdField_field_89_of_type_Class_930);
    this.jdField_field_89_of_type_Class_934 = new class_934(a24(), 130, 20, "Create new faction", localclass_423, localclass_423);
    this.jdField_field_89_of_type_Class_934.field_89 = "CREATE_FACTION";
    this.jdField_field_89_of_type_Class_934.a161(0.0F, 35.0F, 0.0F);
    this.field_90 = new class_934(a24(), 100, 20, "Leave Faction", localclass_423, localclass_423);
    this.field_90.field_89 = "LEAVE_FACTION";
    this.field_90.a161(400.0F, 35.0F, 0.0F);
    class_821 localclass_821 = new class_821(this);
    this.field_92 = new class_934(a24(), 100, 20, localclass_821, localclass_423, localclass_423);
    this.field_92.field_89 = "INCOMING_INVITES";
    this.field_92.a161(150.0F, 35.0F, 0.0F);
    this.field_93 = new class_934(a24(), 120, 20, "Pending Invites", localclass_423, localclass_423);
    this.field_93.field_89 = "OUTGOING_INVITES";
    this.field_93.a161(260.0F, 35.0F, 0.0F);
    this.jdField_field_89_of_type_Class_138 = new class_862(a24());
    this.jdField_field_89_of_type_Class_138.c();
    this.jdField_field_89_of_type_Class_138.a161(0.0F, 60.0F, 0.0F);
    this.jdField_field_89_of_type_Class_167 = new class_167(a24());
    this.jdField_field_89_of_type_Class_167.a161(0.0F, 140.0F, 0.0F);
    this.jdField_field_89_of_type_Class_167.c();
    this.field_96 = true;
    a9(this.jdField_field_89_of_type_Class_934);
    a9(this.jdField_field_89_of_type_Class_1402);
    a9(this.field_90);
    a9(this.field_92);
    a9(this.field_93);
    a9(this.jdField_field_89_of_type_Class_138);
    a9(this.jdField_field_89_of_type_Class_167);
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
 * Qualified Name:     class_819
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */