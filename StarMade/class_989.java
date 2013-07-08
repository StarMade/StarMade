import org.schema.schine.network.StateInterface;
import org.schema.schine.network.server.ServerStateInterface;

public class class_989
  implements class_981
{
  private String jdField_field_223_of_type_JavaLangString;
  public class_983 field_223;
  private final StateInterface jdField_field_223_of_type_OrgSchemaSchineNetworkStateInterface;
  public final boolean field_224;
  
  public class_989(String paramString, StateInterface paramStateInterface)
  {
    this.jdField_field_223_of_type_JavaLangString = paramString;
    this.jdField_field_223_of_type_OrgSchemaSchineNetworkStateInterface = paramStateInterface;
    this.field_224 = (this.jdField_field_223_of_type_OrgSchemaSchineNetworkStateInterface instanceof ServerStateInterface);
  }
  
  public final class_983 a3()
  {
    return this.jdField_field_223_of_type_Class_983;
  }
  
  public final class_997 a4()
  {
    return this.jdField_field_223_of_type_Class_983.a13();
  }
  
  public final class_999 a5()
  {
    return this.jdField_field_223_of_type_Class_983.a13().a2().a();
  }
  
  public final void d()
  {
    this.jdField_field_223_of_type_Class_983 = null;
  }
  
  public String toString()
  {
    String str = this.jdField_field_223_of_type_JavaLangString;
    if (this.jdField_field_223_of_type_Class_983 == null) {
      return str + "[NULL_PROGRAM]";
    }
    if (this.jdField_field_223_of_type_Class_983.a13().a2().field_1279 == null) {
      return str + "\n->[" + this.jdField_field_223_of_type_Class_983.getClass().getSimpleName() + "->NULL_STATE]";
    }
    return str + "\n->[" + this.jdField_field_223_of_type_Class_983.getClass().getSimpleName() + "->" + this.jdField_field_223_of_type_Class_983.a13().a2().field_1279.getClass().getSimpleName() + "]";
  }
  
  public void a2(class_941 paramclass_941)
  {
    if ((this.jdField_field_223_of_type_Class_983 != null) && (!this.jdField_field_223_of_type_Class_983.b1())) {
      this.jdField_field_223_of_type_Class_983.a13().b1();
    }
  }
  
  public boolean a1()
  {
    return (this.jdField_field_223_of_type_Class_983 != null) && (!this.jdField_field_223_of_type_Class_983.b1());
  }
  
  public StateInterface a6()
  {
    return this.jdField_field_223_of_type_OrgSchemaSchineNetworkStateInterface;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_989
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */