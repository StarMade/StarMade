package org.hsqldb.auth;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Set;
import org.hsqldb.lib.FrameworkLogger;

public class HsqldbSlaveAuthBean
  implements AuthFunctionBean
{
  private static FrameworkLogger logger = FrameworkLogger.getLog(HsqldbSlaveAuthBean.class);
  private String masterJdbcUrl;
  private String validationUser;
  private String validationPassword;
  private boolean delegateRolesSchema = true;
  protected boolean initialized;
  
  public void setValidationUser(String paramString)
  {
    this.validationUser = paramString;
  }
  
  public void setValidationPassword(String paramString)
  {
    this.validationPassword = paramString;
  }
  
  public void setMasterJdbcUrl(String paramString)
  {
    this.masterJdbcUrl = paramString;
  }
  
  public void setDelegateRolesSchema(boolean paramBoolean)
  {
    this.delegateRolesSchema = paramBoolean;
  }
  
  public void init()
    throws SQLException
  {
    if (this.masterJdbcUrl == null) {
      throw new IllegalStateException("Required property 'masterJdbcUrl' not set");
    }
    if ((this.validationUser != null) || (this.validationPassword != null))
    {
      if ((this.validationUser == null) || (this.validationPassword == null)) {
        throw new IllegalStateException("If you set one property of 'validationUser' or 'validationPassword', then you must set both.");
      }
      Connection localConnection = null;
      Object localObject1 = null;
      try
      {
        localConnection = DriverManager.getConnection(this.masterJdbcUrl, this.validationUser, this.validationPassword);
        if (localConnection != null) {
          try
          {
            localConnection.close();
            localConnection = null;
          }
          catch (SQLException localSQLException1)
          {
            logger.error("Failed to close test master/slave Connection", localSQLException1);
            if (localObject1 == null) {
              throw localSQLException1;
            }
          }
        }
        this.initialized = true;
      }
      catch (SQLException localSQLException2)
      {
        logger.error("Master/slave Connection validation failure", localSQLException2);
        localObject1 = localSQLException2;
      }
      finally
      {
        if (localConnection != null) {
          try
          {
            localConnection.close();
            localConnection = null;
          }
          catch (SQLException localSQLException4)
          {
            logger.error("Failed to close test master/slave Connection", localSQLException4);
            if (localObject1 == null) {
              throw localSQLException4;
            }
          }
        }
      }
    }
  }
  
  public String[] authenticate(String paramString1, String paramString2)
    throws DenyException
  {
    if (!this.initialized) {
      throw new IllegalStateException("You must invoke the 'init' method to initialize the " + HsqldbSlaveAuthBean.class.getName() + " instance.");
    }
    Connection localConnection = null;
    try
    {
      localConnection = DriverManager.getConnection(this.masterJdbcUrl, paramString1, paramString2);
      if (this.delegateRolesSchema)
      {
        localSet = AuthUtils.getEnabledRoles(localConnection);
        String str = AuthUtils.getInitialSchema(localConnection);
        if (str != null) {
          localSet.add(str);
        }
        logger.finer("Slave delegating schema+roles: " + localSet);
        String[] arrayOfString = (String[])localSet.toArray(new String[0]);
        return arrayOfString;
      }
      Set localSet = null;
      return localSet;
    }
    catch (SQLException localSQLException1)
    {
      throw new DenyException();
    }
    finally
    {
      if (localConnection != null) {
        try
        {
          localConnection.close();
          localConnection = null;
        }
        catch (SQLException localSQLException4)
        {
          logger.severe("Failed to close master/slave Connection", localSQLException4);
        }
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.auth.HsqldbSlaveAuthBean
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */