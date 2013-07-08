import java.io.IOException;
import java.sql.SQLException;
import org.schema.schine.network.RegisteredClientOnServer;

public final class class_1027
  implements class_1051
{
  private class_48 jdField_field_229_of_type_Class_48;
  private RegisteredClientOnServer jdField_field_229_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer;
  private String jdField_field_229_of_type_JavaLangString;
  
  public class_1027(class_48 paramclass_48, RegisteredClientOnServer paramRegisteredClientOnServer, String paramString)
  {
    this.jdField_field_229_of_type_Class_48 = paramclass_48;
    this.jdField_field_229_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer = paramRegisteredClientOnServer;
    this.jdField_field_229_of_type_JavaLangString = paramString;
  }
  
  public final boolean a(class_1041 paramclass_1041)
  {
    try
    {
      class_1194.a3(this.jdField_field_229_of_type_Class_48, this.jdField_field_229_of_type_JavaLangString, paramclass_1041);
      if (this.jdField_field_229_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer != null) {
        this.jdField_field_229_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("sector " + this.jdField_field_229_of_type_Class_48 + " exporting sucessfull to " + this.jdField_field_229_of_type_JavaLangString);
      }
      return true;
    }
    catch (SQLException paramclass_1041)
    {
      try
      {
        if (this.jdField_field_229_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer != null) {
          this.jdField_field_229_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("sector exporting failed: " + paramclass_1041.getClass().getSimpleName() + ": " + paramclass_1041.getMessage());
        }
      }
      catch (IOException localIOException1)
      {
        localIOException1;
      }
      paramclass_1041.printStackTrace();
    }
    catch (IOException paramclass_1041)
    {
      try
      {
        if (this.jdField_field_229_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer != null) {
          this.jdField_field_229_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("sector exporting failed: " + paramclass_1041.getClass().getSimpleName() + ": " + paramclass_1041.getMessage());
        }
      }
      catch (IOException localIOException2)
      {
        localIOException2;
      }
      paramclass_1041.printStackTrace();
    }
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1027
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */