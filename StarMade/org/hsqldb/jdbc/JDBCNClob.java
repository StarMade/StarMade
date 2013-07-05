package org.hsqldb.jdbc;

import java.sql.NClob;
import java.sql.SQLException;

public class JDBCNClob extends JDBCClob
  implements NClob
{
  protected JDBCNClob()
  {
  }

  public JDBCNClob(String paramString)
    throws SQLException
  {
    super(paramString);
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.jdbc.JDBCNClob
 * JD-Core Version:    0.6.2
 */