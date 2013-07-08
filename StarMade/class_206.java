import java.util.ArrayList;
import java.util.List;
import org.schema.schine.network.client.ClientState;

public final class class_206
  extends class_922
{
  private class_930 jdField_field_89_of_type_Class_930;
  private class_934 jdField_field_89_of_type_Class_934;
  private boolean jdField_field_89_of_type_Boolean;
  private final int jdField_field_89_of_type_Int;
  
  public class_206(ClientState paramClientState, int paramInt)
  {
    super(paramClientState);
    this.field_96 = true;
    this.jdField_field_89_of_type_Int = paramInt;
    this.jdField_field_89_of_type_Class_930 = new class_930(30, 30, class_29.e(), a24());
  }
  
  public final class_433 a11()
  {
    class_443 localclass_443;
    if ((localclass_443 = ((class_371)a24()).a14().field_4.field_4.field_4).a51().a45().field_4.field_6) {
      return localclass_443.a51().a45().field_4.a70();
    }
    return localclass_443.a53().a36().a70();
  }
  
  public final void a2() {}
  
  protected final void d() {}
  
  public final void b()
  {
    if (!this.jdField_field_89_of_type_Boolean) {
      c();
    }
    super.k();
  }
  
  public final float a3()
  {
    return this.jdField_field_89_of_type_Class_930.a3();
  }
  
  public final float b1()
  {
    return 100.0F;
  }
  
  public final void c()
  {
    this.jdField_field_89_of_type_Class_930.field_90 = new ArrayList();
    switch (this.jdField_field_89_of_type_Int)
    {
    case 1: 
      this.jdField_field_89_of_type_Class_930.field_90.add("XY-Plane");
      break;
    case 2: 
      this.jdField_field_89_of_type_Class_930.field_90.add("XZ-Plane");
      break;
    case 4: 
      this.jdField_field_89_of_type_Class_930.field_90.add("YZ-Plane");
    }
    this.jdField_field_89_of_type_Class_930.c();
    this.jdField_field_89_of_type_Class_934 = new class_934(a24(), 70, 20, new class_208(this), new class_210(this));
    this.jdField_field_89_of_type_Class_934.a83().field_615 = 40.0F;
    a9(this.jdField_field_89_of_type_Class_934);
    this.jdField_field_89_of_type_Boolean = true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_206
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */