package org.hsqldb.jdbc.pool;

import java.sql.SQLException;
import java.sql.Savepoint;
import org.hsqldb.jdbc.JDBCConnection;

public class JDBCXAConnectionWrapper extends JDBCConnection
{
  private JDBCXAResource xaResource;

  public void setAutoCommit(boolean paramBoolean)
    throws SQLException
  {
    validateNotWithinTransaction();
    super.setAutoCommit(paramBoolean);
  }

  public void commit()
    throws SQLException
  {
    validateNotWithinTransaction();
    super.commit();
  }

  public void rollback()
    throws SQLException
  {
    validateNotWithinTransaction();
    super.rollback();
  }

  public void rollback(Savepoint paramSavepoint)
    throws SQLException
  {
    validateNotWithinTransaction();
    super.rollback(paramSavepoint);
  }

  public Savepoint setSavepoint()
    throws SQLException
  {
    validateNotWithinTransaction();
    return super.setSavepoint();
  }

  public Savepoint setSavepoint(String paramString)
    throws SQLException
  {
    validateNotWithinTransaction();
    return super.setSavepoint(paramString);
  }

  public void setTransactionIsolation(int paramInt)
    throws SQLException
  {
    validateNotWithinTransaction();
    super.setTransactionIsolation(paramInt);
  }

  public JDBCXAConnectionWrapper(JDBCXAResource paramJDBCXAResource, JDBCXAConnection paramJDBCXAConnection, JDBCConnection paramJDBCConnection)
    throws SQLException
  {
    super(paramJDBCConnection, paramJDBCXAConnection);
    paramJDBCXAResource.setConnection(this);
    this.xaResource = paramJDBCXAResource;
  }

  private void validateNotWithinTransaction()
    throws SQLException
  {
    if (this.xaResource.withinGlobalTransaction())
      throw new SQLException("Method prohibited within a global transaction");
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.jdbc.pool.JDBCXAConnectionWrapper
 * JD-Core Version:    0.6.2
 */