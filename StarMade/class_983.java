import java.util.HashMap;
import org.schema.schine.network.StateInterface;

public abstract class class_983
{
  public final class_989 field_163;
  private final HashMap jdField_field_163_of_type_JavaUtilHashMap = new HashMap();
  public class_997 field_163;
  private boolean jdField_field_163_of_type_Boolean;
  
  public class_983(class_989 paramclass_989, boolean paramBoolean)
  {
    this.jdField_field_163_of_type_Class_989 = paramclass_989;
    a4(this.jdField_field_163_of_type_JavaUtilHashMap);
    this.jdField_field_163_of_type_Class_997 = ((class_997)this.jdField_field_163_of_type_JavaUtilHashMap.get(a5()));
    if ((!field_1272) && (this.jdField_field_163_of_type_Class_997 == null)) {
      throw new AssertionError();
    }
    this.jdField_field_163_of_type_Boolean = paramBoolean;
  }
  
  protected abstract void a4(HashMap paramHashMap);
  
  protected abstract String a5();
  
  public StateInterface a6()
  {
    return this.jdField_field_163_of_type_Class_989.a6();
  }
  
  public final class_997 a13()
  {
    return this.jdField_field_163_of_type_Class_997;
  }
  
  public final void a14(class_941 paramclass_941)
  {
    this.jdField_field_163_of_type_Class_989.a2(paramclass_941);
  }
  
  public final boolean b1()
  {
    return this.jdField_field_163_of_type_Boolean;
  }
  
  public final void b2(boolean paramBoolean)
  {
    this.jdField_field_163_of_type_Boolean = paramBoolean;
  }
  
  public static void g() {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_983
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */