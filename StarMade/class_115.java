import javax.vecmath.Vector3f;
import org.schema.schine.network.client.ClientState;

public final class class_115
  extends class_1363
{
  private class_972 jdField_field_89_of_type_Class_972;
  private class_972 field_90;
  private class_972 field_92;
  private class_972 field_93;
  private boolean jdField_field_89_of_type_Boolean;
  private class_972 field_94;
  
  public class_115(ClientState paramClientState)
  {
    super(paramClientState);
  }
  
  public final void a2() {}
  
  public final void b()
  {
    if (this.jdField_field_89_of_type_Boolean)
    {
      int i = ((class_371)a24()).a14().jdField_field_4_of_type_Class_24.field_4;
      if (a28().a52().field_5) {
        this.jdField_field_89_of_type_Class_972.b();
      }
      if (a28().a51().field_5)
      {
        if (i == 0) {
          this.field_90.b();
        }
        if (i == 1) {
          this.field_92.b();
        }
        if (i == 2) {
          this.field_93.b();
        }
      }
      return;
    }
    this.field_94.b();
  }
  
  public final float a3()
  {
    return 0.0F;
  }
  
  private class_443 a28()
  {
    return ((class_371)a24()).a14().jdField_field_4_of_type_Class_467.field_4.field_4;
  }
  
  public final float b1()
  {
    return 0.0F;
  }
  
  public final void c()
  {
    this.field_94 = new class_972(class_969.a2().a5("help-gui-"), a24());
    this.field_94.h3(9);
    this.field_94.c();
    this.field_94.a83().field_616 -= 64.0F;
    this.jdField_field_89_of_type_Class_972 = new class_972(class_969.a2().a5("help-gamecharacter-gui-"), a24());
    this.jdField_field_89_of_type_Class_972.h3(17);
    this.field_90 = new class_972(class_969.a2().a5("help-shipcommon-gui-"), a24());
    this.field_90.h3(17);
    this.field_92 = new class_972(class_969.a2().a5("help-shipbuild-gui-"), a24());
    this.field_92.h3(17);
    this.field_93 = new class_972(class_969.a2().a5("help-shipflight-gui-"), a24());
    this.field_93.h3(17);
  }
  
  public final void a29(boolean paramBoolean)
  {
    this.jdField_field_89_of_type_Boolean = paramBoolean;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_115
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */