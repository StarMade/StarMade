package org.hsqldb.jdbc.pool;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.XAConnection;
import javax.transaction.xa.XAResource;
import org.hsqldb.jdbc.JDBCConnection;

public class JDBCXAConnection
  extends JDBCPooledConnection
  implements XAConnection
{
  JDBCXAResource xaResource;
  
  public JDBCXAConnection(JDBCXADataSource paramJDBCXADataSource, JDBCConnection paramJDBCConnection)
  {
    super(paramJDBCConnection);
    this.xaResource = new JDBCXAResource(paramJDBCXADataSource, paramJDBCConnection);
  }
  
  public XAResource getXAResource()
    throws SQLException
  {
    return this.xaResource;
  }
  
  public synchronized Connection getConnection()
    throws SQLException
  {
    if (this.isInUse) {
      throw new SQLException("Connection in use");
    }
    this.isInUse = true;
    return new JDBCXAConnectionWrapper(this.xaResource, this, this.connection);
  }
  
  public void close()
    throws SQLException
  {
    super.close();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.jdbc.pool.JDBCXAConnection
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */