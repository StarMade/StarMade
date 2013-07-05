package org.hsqldb.jdbc;

import java.sql.SQLException;

public abstract interface JDBCConnectionEventListener
{
  public abstract void connectionClosed();

  public abstract void connectionErrorOccured(SQLException paramSQLException);
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.jdbc.JDBCConnectionEventListener
 * JD-Core Version:    0.6.2
 */