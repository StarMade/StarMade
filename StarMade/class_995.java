import java.io.PrintStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Vector;
import org.schema.schine.ai.stateMachines.FSMException;

public final class class_995
  implements Serializable
{
  private static final long serialVersionUID = 1860839739177998472L;
  class_999 jdField_field_1279_of_type_Class_999;
  private class_997 jdField_field_1279_of_type_Class_997;
  
  public class_995(class_999 paramclass_999, class_997 paramclass_997)
  {
    new HashMap();
    this.jdField_field_1279_of_type_Class_999 = paramclass_999;
    this.jdField_field_1279_of_type_Class_997 = paramclass_997;
    new class_1001();
  }
  
  public final class_999 a()
  {
    return this.jdField_field_1279_of_type_Class_999;
  }
  
  public final class_999 a1(class_1003 paramclass_1003)
  {
    if (this.jdField_field_1279_of_type_Class_999 == null) {
      throw new FSMException("ERROR (FSMclass): CURRENT STATE NOT FOUND " + this.jdField_field_1279_of_type_Class_999);
    }
    if (this.jdField_field_1279_of_type_Class_999 != this.jdField_field_1279_of_type_Class_999.a8().a5()) {
      throw new FSMException("ERROR The State <" + this.jdField_field_1279_of_type_Class_999 + "> of gameObject [" + this.jdField_field_1279_of_type_Class_999.a8() + "] is unequal with the firering state <" + this.jdField_field_1279_of_type_Class_999.a8().a5().toString() + ">");
    }
    class_993 localclass_993;
    class_1003 localclass_1003 = paramclass_1003;
    Object localObject;
    int i;
    if ((i = (localObject = localclass_993 = this.jdField_field_1279_of_type_Class_999.a10()).jdField_field_1277_of_type_JavaUtilVector.indexOf(localclass_1003)) < 0) {
      throw new FSMException(((class_993)localObject).jdField_field_1277_of_type_Class_999, localclass_1003);
    }
    if ((localObject = (class_999)((class_993)localObject).field_1278.get(i)) == null)
    {
      System.err.println("could not set state: discarding");
      throw new FSMException(this.jdField_field_1279_of_type_Class_999, paramclass_1003);
    }
    if (localObject == this.jdField_field_1279_of_type_Class_999)
    {
      this.jdField_field_1279_of_type_Class_999.b();
      this.jdField_field_1279_of_type_Class_999.a11(true);
      return this.jdField_field_1279_of_type_Class_999;
    }
    this.jdField_field_1279_of_type_Class_999.b();
    this.jdField_field_1279_of_type_Class_999 = ((class_999)localObject);
    this.jdField_field_1279_of_type_Class_997.b((class_999)localObject);
    this.jdField_field_1279_of_type_Class_999.a11(true);
    this.jdField_field_1279_of_type_Class_997.a1();
    class_983.g();
    return this.jdField_field_1279_of_type_Class_999;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_995
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */