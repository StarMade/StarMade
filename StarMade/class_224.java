import java.util.ArrayList;
import javax.vecmath.Vector3f;

public final class class_224
  implements class_965
{
  public class_1360 field_98;
  private class_1356 field_98;
  private boolean field_106;
  public float field_98;
  public Vector3f field_98;
  public Vector3f field_106;
  public float field_106;
  public boolean field_98;
  
  public class_224()
  {
    this.jdField_field_106_of_type_Boolean = true;
    this.jdField_field_98_of_type_Float = 0.2F;
    this.jdField_field_98_of_type_JavaxVecmathVector3f = new Vector3f();
    this.jdField_field_106_of_type_JavaxVecmathVector3f = new Vector3f();
    this.jdField_field_106_of_type_Float = 0.0F;
    this.jdField_field_98_of_type_Class_1360 = new class_1360();
    this.jdField_field_98_of_type_Class_1356 = new class_1356(this.jdField_field_98_of_type_Class_1360);
  }
  
  public final void a() {}
  
  public final void b()
  {
    if (class_949.field_1192.b1())
    {
      if (this.jdField_field_106_of_type_Boolean) {
        c();
      }
      this.jdField_field_98_of_type_Class_1356.b();
      if (class_949.field_1191.b1()) {
        class_971.field_98.add("SPACE PARTICLES: " + this.jdField_field_98_of_type_Class_1360.b1());
      }
    }
  }
  
  public final void c()
  {
    this.jdField_field_98_of_type_Class_1356.c();
    this.jdField_field_106_of_type_Boolean = false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_224
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */