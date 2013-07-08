package org.schema.game.server.data.admin;

import java.util.Arrays;

public class AdminCommandIllegalArgument
  extends Exception
{
  private AdminCommands jdField_field_1663_of_type_OrgSchemaGameServerDataAdminAdminCommands;
  private String jdField_field_1663_of_type_JavaLangString;
  private static final long serialVersionUID = -8281348024528573904L;
  
  public AdminCommandIllegalArgument(AdminCommands paramAdminCommands, String[] paramArrayOfString)
  {
    super("[ERROR] " + paramAdminCommands.name() + ". parameters " + Arrays.toString(paramArrayOfString) + ". Usage: " + paramAdminCommands.a3());
    this.jdField_field_1663_of_type_OrgSchemaGameServerDataAdminAdminCommands = paramAdminCommands;
  }
  
  public AdminCommandIllegalArgument(AdminCommands paramAdminCommands, String[] paramArrayOfString, String paramString)
  {
    super("[ERROR] " + paramAdminCommands.name() + ". parameters " + Arrays.toString(paramArrayOfString) + ". Message: " + paramString + ". Usage: " + paramAdminCommands.a3());
    this.jdField_field_1663_of_type_OrgSchemaGameServerDataAdminAdminCommands = paramAdminCommands;
    this.jdField_field_1663_of_type_JavaLangString = paramString;
  }
  
  public final AdminCommands a()
  {
    return this.jdField_field_1663_of_type_OrgSchemaGameServerDataAdminAdminCommands;
  }
  
  public final String a1()
  {
    return this.jdField_field_1663_of_type_JavaLangString;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.server.data.admin.AdminCommandIllegalArgument
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */