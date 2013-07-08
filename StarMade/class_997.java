import java.io.Serializable;
import org.schema.schine.ai.stateMachines.FSMException;

public abstract class class_997
  implements Serializable
{
  private static final long serialVersionUID = -6064345513645124981L;
  private class_999 jdField_field_164_of_type_Class_999;
  private class_995 jdField_field_164_of_type_Class_995;
  public class_981 field_164;
  private class_983 jdField_field_164_of_type_Class_983;
  
  public class_997(class_981 paramclass_981, class_983 paramclass_983)
  {
    if ((!jdField_field_164_of_type_Boolean) && (paramclass_981 == null)) {
      throw new AssertionError();
    }
    this.jdField_field_164_of_type_Class_981 = paramclass_981;
    this.jdField_field_164_of_type_Class_983 = paramclass_983;
    a();
  }
  
  public abstract void a();
  
  public final class_995 a2()
  {
    return this.jdField_field_164_of_type_Class_995;
  }
  
  public class_983 a1()
  {
    return this.jdField_field_164_of_type_Class_983;
  }
  
  public final void a3(class_999 paramclass_999)
  {
    b(paramclass_999);
    this.jdField_field_164_of_type_Class_995 = new class_995(paramclass_999, this);
  }
  
  protected final void b(class_999 paramclass_999)
  {
    if ((!jdField_field_164_of_type_Boolean) && (this.jdField_field_164_of_type_Class_981 == null)) {
      throw new AssertionError();
    }
    this.jdField_field_164_of_type_Class_999 = paramclass_999;
  }
  
  public String toString()
  {
    return getClass().getSimpleName();
  }
  
  public final void b1()
  {
    if (this.jdField_field_164_of_type_Class_999 == null) {
      throw new FSMException("[CRITICAL] no state set! please set the FiniteStateMachine.setStartState(State state) Method in createFSM()");
    }
    class_999 localclass_999;
    if ((localclass_999 = this.jdField_field_164_of_type_Class_999).e())
    {
      this.jdField_field_164_of_type_Class_999.c();
      localclass_999.a11(false);
      return;
    }
    this.jdField_field_164_of_type_Class_999.d();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_997
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */