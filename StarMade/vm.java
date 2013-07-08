/*  1:   */import java.io.IOException;
/*  2:   */import java.sql.SQLException;
/*  3:   */import org.schema.schine.network.RegisteredClientOnServer;
/*  4:   */
/*  8:   */public final class vm
/*  9:   */  implements vp
/* 10:   */{
/* 11:   */  private q jdField_a_of_type_Q;
/* 12:   */  private RegisteredClientOnServer jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer;
/* 13:   */  private String jdField_a_of_type_JavaLangString;
/* 14:   */  
/* 15:   */  public vm(q paramq, RegisteredClientOnServer paramRegisteredClientOnServer, String paramString)
/* 16:   */  {
/* 17:17 */    this.jdField_a_of_type_Q = paramq;
/* 18:18 */    this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer = paramRegisteredClientOnServer;
/* 19:19 */    this.jdField_a_of_type_JavaLangString = paramString;
/* 20:   */  }
/* 21:   */  
/* 22:   */  public final boolean a(vg paramvg) {
/* 23:   */    try {
/* 24:24 */      tO.a(this.jdField_a_of_type_JavaLangString, this.jdField_a_of_type_Q, paramvg);
/* 25:25 */      if (this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer != null) {
/* 26:26 */        this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("sector " + this.jdField_a_of_type_Q + " exporting sucessfull to " + this.jdField_a_of_type_JavaLangString);
/* 27:   */      }
/* 28:28 */      return true;
/* 29:   */    } catch (SQLException paramvg) {
/* 30:   */      try {
/* 31:31 */        if (this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer != null)
/* 32:32 */          this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("sector exporting failed: " + paramvg.getClass().getSimpleName() + ": " + paramvg.getMessage());
/* 33:   */      } catch (IOException localIOException1) {
/* 34:34 */        
/* 35:   */        
/* 36:36 */          localIOException1;
/* 37:   */      }
/* 38:   */      
/* 39:37 */      paramvg.printStackTrace();
/* 40:   */    } catch (IOException paramvg) {
/* 41:   */      try {
/* 42:40 */        if (this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer != null)
/* 43:41 */          this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("sector exporting failed: " + paramvg.getClass().getSimpleName() + ": " + paramvg.getMessage());
/* 44:   */      } catch (IOException localIOException2) {
/* 45:43 */        
/* 46:   */        
/* 47:45 */          localIOException2;
/* 48:   */      }
/* 49:   */      
/* 50:46 */      paramvg.printStackTrace();
/* 51:   */    }
/* 52:48 */    return false;
/* 53:   */  }
/* 54:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     vm
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */