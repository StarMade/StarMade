import java.io.Serializable;
import java.util.Vector;

public abstract class class_999
  implements Serializable
{
  private static final long serialVersionUID = -493388144215505990L;
  private class_981 jdField_field_128_of_type_Class_981;
  private boolean jdField_field_128_of_type_Boolean = true;
  private class_993 jdField_field_128_of_type_Class_993;
  
  public class_999(class_981 paramclass_981)
  {
    this.jdField_field_128_of_type_Class_981 = paramclass_981;
    this.jdField_field_128_of_type_Class_993 = new class_993(this);
  }
  
  public final class_999 a9(class_1003 paramclass_1003, class_999 paramclass_999)
  {
    class_999 localclass_999 = paramclass_999;
    paramclass_999 = paramclass_1003;
    (paramclass_1003 = this.jdField_field_128_of_type_Class_993).field_1278.add(localclass_999);
    paramclass_1003.field_1277.add(paramclass_999);
    return this;
  }
  
  public boolean equals(Object paramObject)
  {
    return (paramObject != null) && (getClass().equals(paramObject.getClass()));
  }
  
  public class_981 a8()
  {
    return this.jdField_field_128_of_type_Class_981;
  }
  
  public final class_993 a10()
  {
    return this.jdField_field_128_of_type_Class_993;
  }
  
  public final boolean e()
  {
    return this.jdField_field_128_of_type_Boolean;
  }
  
  public abstract boolean c();
  
  public abstract boolean b();
  
  public abstract boolean d();
  
  public final void a11(boolean paramBoolean)
  {
    this.jdField_field_128_of_type_Boolean = paramBoolean;
  }
  
  public final void a12(class_1003 paramclass_1003)
  {
    if ((!field_162) && (a8() == null)) {
      throw new AssertionError();
    }
    if ((!field_162) && (a8().a4() == null)) {
      throw new AssertionError();
    }
    if ((!field_162) && (a8().a4().a2() == null)) {
      throw new AssertionError();
    }
    a8().a4().a2().a1(paramclass_1003);
  }
  
  public String toString()
  {
    return getClass().getSimpleName();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_999
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */