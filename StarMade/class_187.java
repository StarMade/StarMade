import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.network.client.ClientState;

public final class class_187
  extends class_1363
{
  private class_968 jdField_field_89_of_type_Class_968;
  private boolean jdField_field_89_of_type_Boolean;
  private class_972 jdField_field_89_of_type_Class_972;
  class_161 jdField_field_89_of_type_Class_161;
  
  public class_187(ClientState paramClientState)
  {
    super(paramClientState);
    this.jdField_field_89_of_type_Class_968 = new class_968(536.0F, 360.0F, paramClientState);
    a9(this.jdField_field_89_of_type_Class_968);
  }
  
  public final void a2() {}
  
  public final void b()
  {
    if (!this.jdField_field_89_of_type_Boolean) {
      c();
    }
    GlUtil.d1();
    r();
    this.jdField_field_89_of_type_Class_968.b();
    GlUtil.c2();
  }
  
  public final float a3()
  {
    return 360.0F;
  }
  
  public final float b1()
  {
    return 536.0F;
  }
  
  public final void c()
  {
    this.jdField_field_89_of_type_Class_972 = new class_183(a24(), this.jdField_field_89_of_type_Class_968);
    this.jdField_field_89_of_type_Class_968.c7(this.jdField_field_89_of_type_Class_972);
    this.jdField_field_89_of_type_Class_161 = new class_161((class_371)a24());
    this.jdField_field_89_of_type_Class_161.a54(((class_371)a24()).a20().getInventory(null));
    this.jdField_field_89_of_type_Class_161.c();
    this.jdField_field_89_of_type_Class_972.a9(this.jdField_field_89_of_type_Class_161);
    this.jdField_field_89_of_type_Boolean = true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_187
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */