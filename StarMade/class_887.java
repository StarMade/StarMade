import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.network.client.ClientState;

public final class class_887
  extends class_1363
{
  private class_1395 jdField_field_89_of_type_Class_1395;
  private class_972 jdField_field_89_of_type_Class_972;
  private class_940 jdField_field_89_of_type_Class_940;
  private int jdField_field_89_of_type_Int = 113;
  private int field_90 = 113;
  private class_889 jdField_field_89_of_type_Class_889;
  private class_851 jdField_field_89_of_type_Class_851;
  
  public class_887(ClientState paramClientState)
  {
    super(paramClientState);
  }
  
  public final void a2() {}
  
  public final void b()
  {
    GlUtil.d1();
    r();
    this.jdField_field_89_of_type_Class_972.b();
    GlUtil.c2();
  }
  
  public final void c()
  {
    this.jdField_field_89_of_type_Class_1395 = class_969.a2().a5("radarBackground").a153().a1();
    this.jdField_field_89_of_type_Class_940 = new class_940(a24(), this.field_90, this.jdField_field_89_of_type_Int, this.jdField_field_89_of_type_Class_1395);
    this.jdField_field_89_of_type_Class_940.c();
    this.jdField_field_89_of_type_Class_972 = new class_972(class_969.a2().a5("radarGUIBackground"), a24());
    this.jdField_field_89_of_type_Class_972.a9(this.jdField_field_89_of_type_Class_940);
    this.jdField_field_89_of_type_Class_940.a161(8.0F, 8.0F, 0.0F);
    this.jdField_field_89_of_type_Class_889 = new class_889(a24(), this.field_90);
    this.jdField_field_89_of_type_Class_889.c();
    this.jdField_field_89_of_type_Class_940.a9(this.jdField_field_89_of_type_Class_889);
    this.jdField_field_89_of_type_Class_851 = new class_851(a24());
    this.jdField_field_89_of_type_Class_851.c();
    this.jdField_field_89_of_type_Class_972.a9(this.jdField_field_89_of_type_Class_851);
  }
  
  public final float a3()
  {
    return this.jdField_field_89_of_type_Class_972.a3();
  }
  
  public final float b1()
  {
    return this.jdField_field_89_of_type_Class_972.b1();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_887
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */