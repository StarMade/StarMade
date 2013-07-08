import java.io.PrintStream;
import java.util.ArrayList;
import org.schema.schine.ai.stateMachines.FSMException;

public abstract class class_454
  extends class_999
{
  final class_371 jdField_field_128_of_type_Class_371;
  String jdField_field_128_of_type_JavaLangString;
  private boolean field_158;
  protected boolean field_128;
  protected boolean field_162;
  private static final long serialVersionUID = 111733781395879161L;
  private class_110 jdField_field_128_of_type_Class_110;
  private class_482 jdField_field_128_of_type_Class_482;
  
  public class_454(class_981 paramclass_981, String paramString, class_371 paramclass_371)
  {
    super(paramclass_981);
    this.jdField_field_128_of_type_Class_371 = paramclass_371;
    this.jdField_field_128_of_type_JavaLangString = paramString;
  }
  
  protected abstract boolean a();
  
  public final void a2()
  {
    this.field_158 = true;
  }
  
  public String a1()
  {
    return this.jdField_field_128_of_type_JavaLangString;
  }
  
  public boolean c()
  {
    this.jdField_field_128_of_type_Boolean = false;
    this.jdField_field_128_of_type_Class_110 = null;
    this.field_158 = false;
    try
    {
      if ((this.field_162) && (a()))
      {
        System.err.println("AUTO SKIP STATE " + this);
        this.field_158 = true;
      }
    }
    catch (FSMException localFSMException)
    {
      localFSMException;
    }
    if (!this.field_158)
    {
      this.jdField_field_128_of_type_Class_371.a4().a5();
      class_460.a2();
      if (!(this instanceof class_403)) {
        this.jdField_field_128_of_type_Class_482 = new class_482(this.jdField_field_128_of_type_Class_371, a1() + "\n(click NEXT to take control)", this);
      } else {
        this.jdField_field_128_of_type_Class_482 = new class_482(this.jdField_field_128_of_type_Class_371, a1(), this);
      }
      this.jdField_field_128_of_type_Class_371.b().add(this.jdField_field_128_of_type_Class_482);
    }
    return true;
  }
  
  public boolean b()
  {
    if (this.jdField_field_128_of_type_Class_482 != null) {
      this.jdField_field_128_of_type_Class_482.d();
    }
    return true;
  }
  
  public boolean d()
  {
    class_454 localclass_454 = this;
    this.jdField_field_128_of_type_Class_371.a4().a5();
    class_460.a2();
    if (((localclass_454.jdField_field_128_of_type_Boolean) && (a())) || (this.field_158))
    {
      this.field_158 = false;
      if (this.jdField_field_128_of_type_Class_110 != null) {
        this.jdField_field_128_of_type_Class_110.g();
      }
      a8().a4().a2().a1(new class_391());
      return false;
    }
    this.jdField_field_128_of_type_Class_371.a4().a5();
    class_460.a2();
    if (this.jdField_field_128_of_type_Boolean)
    {
      this.jdField_field_128_of_type_Class_371.a4().a5();
      class_460.a2();
      if (this.jdField_field_128_of_type_Class_110 == null)
      {
        this.jdField_field_128_of_type_Class_110 = this.jdField_field_128_of_type_Class_371.a4().e(a1());
      }
      else
      {
        if (!(this instanceof class_403)) {
          this.jdField_field_128_of_type_Class_110.a17(a1() + "\n(press 'k' to skip)");
        } else {
          this.jdField_field_128_of_type_Class_110.a17(a1());
        }
        this.jdField_field_128_of_type_Class_110.e();
      }
    }
    return true;
  }
  
  public final void b1()
  {
    this.jdField_field_128_of_type_Boolean = true;
  }
  
  public String toString()
  {
    return super.toString() + ": " + a1();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_454
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */