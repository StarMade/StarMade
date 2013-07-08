package org.hsqldb.jdbc;

import java.sql.SQLException;
import java.sql.Savepoint;

public class JDBCSavepoint
  implements Savepoint
{
  int field_2148;
  String name;
  JDBCConnection connection;
  
  JDBCSavepoint(String paramString, JDBCConnection paramJDBCConnection)
    throws SQLException
  {
    if (paramString == null) {
      throw Util.nullArgument("name");
    }
    if (paramJDBCConnection == null) {
      throw Util.nullArgument("conn");
    }
    this.name = paramString;
    this.field_2148 = -1;
    this.connection = paramJDBCConnection;
  }
  
  JDBCSavepoint(JDBCConnection paramJDBCConnection)
    throws SQLException
  {
    if (paramJDBCConnection == null) {
      throw Util.nullArgument("conn");
    }
    this.field_2148 = paramJDBCConnection.getSavepointID();
    this.name = ("SYSTEM_SAVEPOINT_" + this.field_2148);
    this.connection = paramJDBCConnection;
  }
  
  public int getSavepointId()
    throws SQLException
  {
    if (this.field_2148 != -1) {
      return this.field_2148;
    }
    throw Util.notSupported();
  }
  
  public String getSavepointName()
    throws SQLException
  {
    if (this.field_2148 == -1) {
      return this.name;
    }
    throw Util.notSupported();
  }
  
  public String toString()
  {
    return super.toString() + "[name=" + this.name + "]";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.jdbc.JDBCSavepoint
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */