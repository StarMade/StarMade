import java.util.ArrayList;
import java.util.List;
import javax.vecmath.Vector3f;
import org.schema.schine.network.client.ClientState;

final class class_104
  extends class_1363
{
  private class_930 jdField_field_89_of_type_Class_930;
  private class_930 field_90;
  private class_930 field_92;
  private class_930 field_93;
  private class_930 field_94;
  private int jdField_field_89_of_type_Int = 100;
  
  public class_104(ClientState paramClientState)
  {
    super(paramClientState);
    this.jdField_field_89_of_type_Class_930 = new class_930(300, 30, class_29.b2(), paramClientState);
    this.jdField_field_89_of_type_Class_930.field_90 = new ArrayList();
    this.jdField_field_89_of_type_Class_930.field_90.add("");
    this.field_90 = new class_930(300, 30, class_29.b2(), paramClientState);
    this.field_90.field_90 = new ArrayList();
    this.field_90.field_90.add("");
    this.field_90.a83().field_615 += this.jdField_field_89_of_type_Int;
    this.field_92 = new class_930(300, 30, class_29.b2(), paramClientState);
    this.field_92.field_90 = new ArrayList();
    this.field_92.field_90.add("");
    this.field_92.a83().field_615 += 2 * this.jdField_field_89_of_type_Int;
    this.field_93 = new class_930(300, 30, class_29.b2(), paramClientState);
    this.field_93.field_90 = new ArrayList();
    this.field_93.field_90.add("");
    this.field_93.a83().field_615 += 3 * this.jdField_field_89_of_type_Int;
    this.field_94 = new class_930(300, 30, class_29.b2(), paramClientState);
    this.field_94.field_90 = new ArrayList();
    this.field_94.field_90.add("");
    this.field_94.a83().field_615 += 4 * this.jdField_field_89_of_type_Int;
  }
  
  public final void a2() {}
  
  public final void b()
  {
    r();
    this.jdField_field_89_of_type_Class_930.b();
    this.field_90.b();
    this.field_92.b();
    this.field_93.b();
    this.field_94.b();
  }
  
  public final float a3()
  {
    return this.jdField_field_89_of_type_Class_930.a3();
  }
  
  public final float b1()
  {
    return this.jdField_field_89_of_type_Class_930.b1() * 4.0F + 4 * this.jdField_field_89_of_type_Int;
  }
  
  public final void c()
  {
    this.jdField_field_89_of_type_Class_930.c();
    this.field_90.c();
    this.field_92.c();
    this.field_93.c();
    this.field_94.c();
  }
  
  public final void a15(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    this.jdField_field_89_of_type_Class_930.field_90.set(0, paramString1);
    this.field_90.field_90.set(0, paramString2);
    this.field_92.field_90.set(0, paramString3);
    this.field_93.field_90.set(0, paramString4);
    this.field_94.field_90.set(0, paramString5);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_104
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */