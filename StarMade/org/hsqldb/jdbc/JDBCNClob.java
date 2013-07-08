package org.hsqldb.jdbc;

import java.sql.NClob;
import java.sql.SQLException;

public class JDBCNClob
  extends JDBCClob
  implements NClob
{
  protected JDBCNClob() {}
  
  public JDBCNClob(String paramString)
    throws SQLException
  {
    super(paramString);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.jdbc.JDBCNClob
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */