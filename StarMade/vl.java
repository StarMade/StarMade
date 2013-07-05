/*    */ import java.io.IOException;
/*    */ import java.sql.SQLException;
/*    */ import org.schema.schine.network.RegisteredClientOnServer;
/*    */ 
/*    */ public final class vl
/*    */   implements vp
/*    */ {
/*    */   private q jdField_a_of_type_Q;
/*    */   private RegisteredClientOnServer jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer;
/*    */   private String jdField_a_of_type_JavaLangString;
/*    */ 
/*    */   public vl(q paramq, RegisteredClientOnServer paramRegisteredClientOnServer, String paramString)
/*    */   {
/* 17 */     this.jdField_a_of_type_Q = paramq;
/* 18 */     this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer = paramRegisteredClientOnServer;
/* 19 */     this.jdField_a_of_type_JavaLangString = paramString;
/*    */   }
/*    */ 
/*    */   public final boolean a(vg paramvg) {
/*    */     try {
/* 24 */       tO.a(this.jdField_a_of_type_Q, this.jdField_a_of_type_JavaLangString, paramvg);
/* 25 */       if (this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer != null) {
/* 26 */         this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("sector " + this.jdField_a_of_type_Q + " exporting sucessfull to " + this.jdField_a_of_type_JavaLangString);
/*    */       }
/* 28 */       return true;
/*    */     } catch (SQLException paramvg) {
/*    */       try {
/* 31 */         if (this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer != null)
/* 32 */           this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("sector exporting failed: " + paramvg.getClass().getSimpleName() + ": " + paramvg.getMessage());
/*    */       }
/*    */       catch (IOException localIOException1)
/*    */       {
/* 36 */         localIOException1.printStackTrace();
/*    */       }
/*    */ 
/* 37 */       paramvg.printStackTrace();
/*    */     } catch (IOException paramvg) {
/*    */       try {
/* 40 */         if (this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer != null)
/* 41 */           this.jdField_a_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.serverMessage("sector exporting failed: " + paramvg.getClass().getSimpleName() + ": " + paramvg.getMessage());
/*    */       }
/*    */       catch (IOException localIOException2)
/*    */       {
/* 45 */         localIOException2.printStackTrace();
/*    */       }
/*    */ 
/* 46 */       paramvg.printStackTrace();
/*    */     }
/* 48 */     return false;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     vl
 * JD-Core Version:    0.6.2
 */