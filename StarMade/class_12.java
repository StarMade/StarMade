import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Observable;

public abstract class class_12
  extends Observable
  implements class_954, class_1412
{
  public static long field_4;
  public class_371 field_4;
  boolean field_4;
  private long jdField_field_5_of_type_Long;
  
  public static boolean b1()
  {
    return System.currentTimeMillis() - jdField_field_4_of_type_Long < 300L;
  }
  
  public class_12(class_371 paramclass_371)
  {
    this.jdField_field_4_of_type_Boolean = true;
    this.jdField_field_4_of_type_Class_371 = paramclass_371;
    e();
  }
  
  public final void c1()
  {
    if ((!jdField_field_5_of_type_Boolean) && (a3() == null)) {
      throw new AssertionError();
    }
    synchronized (this.jdField_field_4_of_type_Class_371.b())
    {
      this.jdField_field_5_of_type_Long = 0L;
      this.jdField_field_4_of_type_Class_371.b().remove(this);
      this.jdField_field_4_of_type_Class_371.b().add(this);
      return;
    }
  }
  
  public boolean c()
  {
    return false;
  }
  
  public final void d()
  {
    synchronized (this.jdField_field_4_of_type_Class_371.b())
    {
      boolean bool;
      if (((bool = this.jdField_field_4_of_type_Class_371.b().remove(this))) && ((a3() instanceof class_43))) {
        this.jdField_field_4_of_type_Class_371.a10().add(this);
      }
      if (!bool) {
        System.err.println("[CLIENT][PlayerInput] not found: " + this + " to deactivate: " + this.jdField_field_4_of_type_Class_371.b());
      } else {
        System.err.println("[CLIENT][PlayerInput] successfully deactivated " + this);
      }
      a2();
      this.jdField_field_5_of_type_Long = System.currentTimeMillis();
      if (bool) {
        this.jdField_field_4_of_type_Class_371.setLastDeactivatedMenu(System.currentTimeMillis());
      }
      return;
    }
  }
  
  public final long a5()
  {
    return this.jdField_field_5_of_type_Long;
  }
  
  public abstract class_1363 a3();
  
  public final class_371 a6()
  {
    return this.jdField_field_4_of_type_Class_371;
  }
  
  protected void e() {}
  
  public abstract void a2();
  
  public final void f()
  {
    if ((a3() instanceof class_43))
    {
      float f = 0.0F;
      if (this.jdField_field_5_of_type_Long > 0L) {
        if ((f = (float)(System.currentTimeMillis() - this.jdField_field_5_of_type_Long)) > 200.0F) {
          f = 1.0F;
        } else {
          f /= 200.0F;
        }
      }
      ((class_43)a3()).a(f);
    }
  }
  
  static
  {
    jdField_field_4_of_type_Long = 0L;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_12
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */