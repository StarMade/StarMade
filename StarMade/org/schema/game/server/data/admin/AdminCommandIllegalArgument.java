/*  1:   */package org.schema.game.server.data.admin;
/*  2:   */
/*  3:   */import java.util.Arrays;
/*  4:   */
/*  8:   */public class AdminCommandIllegalArgument
/*  9:   */  extends Exception
/* 10:   */{
/* 11:   */  private AdminCommands jdField_a_of_type_OrgSchemaGameServerDataAdminAdminCommands;
/* 12:   */  private String jdField_a_of_type_JavaLangString;
/* 13:   */  private static final long serialVersionUID = -8281348024528573904L;
/* 14:   */  
/* 15:   */  public AdminCommandIllegalArgument(AdminCommands paramAdminCommands, String[] paramArrayOfString)
/* 16:   */  {
/* 17:17 */    super("[ERROR] " + paramAdminCommands.name() + ". parameters " + Arrays.toString(paramArrayOfString) + ". Usage: " + paramAdminCommands.a());
/* 18:18 */    this.jdField_a_of_type_OrgSchemaGameServerDataAdminAdminCommands = paramAdminCommands;
/* 19:   */  }
/* 20:   */  
/* 21:   */  public AdminCommandIllegalArgument(AdminCommands paramAdminCommands, String[] paramArrayOfString, String paramString)
/* 22:   */  {
/* 23:23 */    super("[ERROR] " + paramAdminCommands.name() + ". parameters " + Arrays.toString(paramArrayOfString) + ". Message: " + paramString + ". Usage: " + paramAdminCommands.a());
/* 24:24 */    this.jdField_a_of_type_OrgSchemaGameServerDataAdminAdminCommands = paramAdminCommands;
/* 25:25 */    this.jdField_a_of_type_JavaLangString = paramString;
/* 26:   */  }
/* 27:   */  
/* 30:   */  public final AdminCommands a()
/* 31:   */  {
/* 32:32 */    return this.jdField_a_of_type_OrgSchemaGameServerDataAdminAdminCommands;
/* 33:   */  }
/* 34:   */  
/* 37:   */  public final String a()
/* 38:   */  {
/* 39:39 */    return this.jdField_a_of_type_JavaLangString;
/* 40:   */  }
/* 41:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.server.data.admin.AdminCommandIllegalArgument
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */