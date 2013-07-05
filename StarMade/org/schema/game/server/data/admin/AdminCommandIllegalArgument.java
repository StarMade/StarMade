/*    */ package org.schema.game.server.data.admin;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ 
/*    */ public class AdminCommandIllegalArgument extends Exception
/*    */ {
/*    */   private AdminCommands jdField_a_of_type_OrgSchemaGameServerDataAdminAdminCommands;
/*    */   private String jdField_a_of_type_JavaLangString;
/*    */   private static final long serialVersionUID = -8281348024528573904L;
/*    */ 
/*    */   public AdminCommandIllegalArgument(AdminCommands paramAdminCommands, String[] paramArrayOfString)
/*    */   {
/* 17 */     super("[ERROR] " + paramAdminCommands.name() + ". parameters " + Arrays.toString(paramArrayOfString) + ". Usage: " + paramAdminCommands.a());
/* 18 */     this.jdField_a_of_type_OrgSchemaGameServerDataAdminAdminCommands = paramAdminCommands;
/*    */   }
/*    */ 
/*    */   public AdminCommandIllegalArgument(AdminCommands paramAdminCommands, String[] paramArrayOfString, String paramString)
/*    */   {
/* 23 */     super("[ERROR] " + paramAdminCommands.name() + ". parameters " + Arrays.toString(paramArrayOfString) + ". Message: " + paramString + ". Usage: " + paramAdminCommands.a());
/* 24 */     this.jdField_a_of_type_OrgSchemaGameServerDataAdminAdminCommands = paramAdminCommands;
/* 25 */     this.jdField_a_of_type_JavaLangString = paramString;
/*    */   }
/*    */ 
/*    */   public final AdminCommands a()
/*    */   {
/* 32 */     return this.jdField_a_of_type_OrgSchemaGameServerDataAdminAdminCommands;
/*    */   }
/*    */ 
/*    */   public final String a()
/*    */   {
/* 39 */     return this.jdField_a_of_type_JavaLangString;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.server.data.admin.AdminCommandIllegalArgument
 * JD-Core Version:    0.6.2
 */