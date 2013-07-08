package org.hsqldb.jdbc;

import java.sql.SQLException;

public abstract interface JDBCConnectionEventListener
{
  public abstract void connectionClosed();
  
  public abstract void connectionErrorOccured(SQLException paramSQLException);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.jdbc.JDBCConnectionEventListener
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */