package org.hsqldb.jdbc;

import java.sql.SQLException;
import java.sql.Savepoint;

public class JDBCSavepoint
  implements Savepoint
{
  int id;
  String name;
  JDBCConnection connection;

  JDBCSavepoint(String paramString, JDBCConnection paramJDBCConnection)
    throws SQLException
  {
    if (paramString == null)
      throw Util.nullArgument("name");
    if (paramJDBCConnection == null)
      throw Util.nullArgument("conn");
    this.name = paramString;
    this.id = -1;
    this.connection = paramJDBCConnection;
  }

  JDBCSavepoint(JDBCConnection paramJDBCConnection)
    throws SQLException
  {
    if (paramJDBCConnection == null)
      throw Util.nullArgument("conn");
    this.id = paramJDBCConnection.getSavepointID();
    this.name = ("SYSTEM_SAVEPOINT_" + this.id);
    this.connection = paramJDBCConnection;
  }

  public int getSavepointId()
    throws SQLException
  {
    if (this.id != -1)
      return this.id;
    throw Util.notSupported();
  }

  public String getSavepointName()
    throws SQLException
  {
    if (this.id == -1)
      return this.name;
    throw Util.notSupported();
  }

  public String toString()
  {
    return super.toString() + "[name=" + this.name + "]";
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.jdbc.JDBCSavepoint
 * JD-Core Version:    0.6.2
 */