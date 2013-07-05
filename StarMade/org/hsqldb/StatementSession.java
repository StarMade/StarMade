package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.navigator.RowSetNavigator;
import org.hsqldb.persist.Logger;
import org.hsqldb.result.Result;
import org.hsqldb.rights.Grantee;
import org.hsqldb.rights.GranteeManager;
import org.hsqldb.rights.User;
import org.hsqldb.rights.UserManager;
import org.hsqldb.types.CharacterType;
import org.hsqldb.types.IntervalSecondData;
import org.hsqldb.types.Type;

public class StatementSession extends Statement
{
  public static final StatementSession commitNoChainStatement = new StatementSession(11, new Object[] { Boolean.FALSE });
  public static final StatementSession rollbackNoChainStatement = new StatementSession(62, new Object[] { Boolean.FALSE });
  public static final StatementSession commitAndChainStatement = new StatementSession(11, new Object[] { Boolean.TRUE });
  public static final StatementSession rollbackAndChainStatement = new StatementSession(62, new Object[] { Boolean.TRUE });
  Expression[] expressions;
  Object[] parameters;

  StatementSession(int paramInt, Expression[] paramArrayOfExpression)
  {
    super(paramInt);
    this.expressions = paramArrayOfExpression;
    this.isTransactionStatement = false;
    this.isLogged = false;
    switch (paramInt)
    {
    case 66:
    case 69:
    case 71:
    case 72:
    case 73:
    case 74:
    case 76:
    case 136:
      this.group = 2008;
      break;
    default:
      throw Error.runtimeError(201, "StateemntSession");
    }
  }

  StatementSession(int paramInt, Object[] paramArrayOfObject)
  {
    super(paramInt);
    this.parameters = paramArrayOfObject;
    this.isTransactionStatement = false;
    this.isLogged = false;
    switch (paramInt)
    {
    case 74:
      this.group = 2008;
      this.isLogged = true;
      break;
    case 1075:
      this.group = 2011;
      this.isLogged = true;
      break;
    case 1:
      this.group = 2003;
      break;
    case 2:
    case 15:
    case 16:
      this.group = 2010;
      break;
    case 38:
      this.group = 2004;
      break;
    case 37:
    case 39:
    case 40:
      this.group = 2003;
      break;
    case 45:
    case 47:
    case 53:
    case 98:
    case 99:
      this.group = 2003;
      break;
    case 54:
    case 55:
    case 56:
      this.group = 2010;
      break;
    case 22:
      this.group = 2006;
      break;
    case 67:
    case 68:
    case 70:
    case 109:
    case 118:
    case 136:
    case 1064:
    case 1065:
    case 1066:
      this.group = 2011;
      break;
    case 1042:
      this.isLogged = true;
      this.group = 2011;
      break;
    case 11:
    case 57:
    case 62:
    case 63:
    case 75:
    case 111:
    case 1067:
      this.group = 2005;
      break;
    case 32:
    case 1068:
      this.group = 2008;
      break;
    default:
      throw Error.runtimeError(201, "StatementSession");
    }
  }

  StatementSession(int paramInt, HsqlNameManager.HsqlName[] paramArrayOfHsqlName1, HsqlNameManager.HsqlName[] paramArrayOfHsqlName2)
  {
    super(paramInt);
    this.isTransactionStatement = true;
    this.readTableNames = paramArrayOfHsqlName1;
    this.writeTableNames = paramArrayOfHsqlName2;
    switch (paramInt)
    {
    case 1063:
      this.group = 2015;
      break;
    default:
      throw Error.runtimeError(201, "StatementSession");
    }
  }

  public Result execute(Session paramSession)
  {
    Result localResult;
    try
    {
      localResult = getResult(paramSession);
    }
    catch (Throwable localThrowable1)
    {
      localResult = Result.newErrorResult(localThrowable1, null);
    }
    if (localResult.isError())
    {
      localResult.getException().setStatementType(this.group, this.type);
      return localResult;
    }
    try
    {
      if (this.isLogged)
        paramSession.database.logger.writeOtherStatement(paramSession, this.sql);
    }
    catch (Throwable localThrowable2)
    {
      return Result.newErrorResult(localThrowable2, this.sql);
    }
    return localResult;
  }

  Result getResult(Session paramSession)
  {
    int i = 0;
    if (this.isExplain)
      return Result.newSingleColumnStringResult("OPERATION", describe(paramSession));
    Object localObject1;
    Object localObject3;
    Object localObject5;
    int m;
    Object localObject2;
    Object localObject4;
    switch (this.type)
    {
    case 1:
    case 2:
      return Result.updateZeroResult;
    case 11:
      try
      {
        boolean bool1 = ((Boolean)this.parameters[0]).booleanValue();
        paramSession.commit(bool1);
        return Result.updateZeroResult;
      }
      catch (HsqlException localHsqlException1)
      {
        return Result.newErrorResult(localHsqlException1, this.sql);
      }
    case 15:
    case 16:
      return Result.updateZeroResult;
    case 22:
      paramSession.close();
      return Result.updateZeroResult;
    case 37:
    case 38:
    case 39:
    case 40:
    case 45:
    case 47:
    case 53:
    case 54:
    case 55:
    case 56:
    case 98:
    case 99:
      return Result.updateZeroResult;
    case 1063:
      return Result.updateZeroResult;
    case 57:
      String str = (String)this.parameters[0];
      try
      {
        paramSession.releaseSavepoint(str);
        return Result.updateZeroResult;
      }
      catch (HsqlException localHsqlException6)
      {
        return Result.newErrorResult(localHsqlException6, this.sql);
      }
    case 62:
      boolean bool2 = ((Boolean)this.parameters[0]).booleanValue();
      paramSession.rollback(bool2);
      return Result.updateZeroResult;
    case 1067:
      localObject1 = (String)this.parameters[0];
      try
      {
        paramSession.rollbackToSavepoint((String)localObject1);
        return Result.updateZeroResult;
      }
      catch (HsqlException localHsqlException7)
      {
        return Result.newErrorResult(localHsqlException7, this.sql);
      }
    case 63:
      localObject1 = (String)this.parameters[0];
      paramSession.savepoint((String)localObject1);
      return Result.updateZeroResult;
    case 66:
      try
      {
        localObject1 = (String)this.expressions[0].getValue(paramSession);
        localObject1 = (String)Type.SQL_VARCHAR.trim(paramSession, localObject1, 32, true, true);
        if (paramSession.database.getCatalogName().name.equals(localObject1))
          return Result.updateZeroResult;
        return Result.newErrorResult(Error.error(4840), this.sql);
      }
      catch (HsqlException localHsqlException8)
      {
        return Result.newErrorResult(localHsqlException8, this.sql);
      }
    case 67:
    case 68:
    case 70:
      return Result.updateZeroResult;
    case 71:
      localObject1 = null;
      if ((this.expressions[0].getType() == 1) && (this.expressions[0].getConstantValueNoCheck(paramSession) == null))
      {
        paramSession.setZoneSeconds(paramSession.sessionTimeZoneSeconds);
        return Result.updateZeroResult;
      }
      try
      {
        localObject1 = this.expressions[0].getValue(paramSession);
      }
      catch (HsqlException localHsqlException9)
      {
      }
      if ((localObject1 instanceof Result))
      {
        Result localResult1 = (Result)localObject1;
        if (localResult1.isData())
        {
          Object[] arrayOfObject = (Object[])localResult1.getNavigator().getNext();
          int i1 = !localResult1.getNavigator().next() ? 1 : 0;
          if ((i1 != 0) && (arrayOfObject != null) && (arrayOfObject[0] != null))
          {
            localObject1 = arrayOfObject[0];
            localResult1.getNavigator().release();
          }
          else
          {
            localResult1.getNavigator().release();
            return Result.newErrorResult(Error.error(3409), this.sql);
          }
        }
        else
        {
          return Result.newErrorResult(Error.error(3409), this.sql);
        }
      }
      else if (localObject1 == null)
      {
        return Result.newErrorResult(Error.error(3409), this.sql);
      }
      long l = ((IntervalSecondData)localObject1).getSeconds();
      if ((-50400L <= l) && (l <= 50400L))
      {
        paramSession.setZoneSeconds((int)l);
        return Result.updateZeroResult;
      }
      return Result.newErrorResult(Error.error(3409), this.sql);
    case 72:
      return Result.updateZeroResult;
    case 69:
      return Result.updateZeroResult;
    case 73:
      localObject3 = null;
      try
      {
        localObject1 = (String)this.expressions[0].getValue(paramSession);
        if (localObject1 != null)
        {
          localObject1 = (String)Type.SQL_VARCHAR.trim(paramSession, localObject1, 32, true, true);
          localObject3 = paramSession.database.granteeManager.getRole((String)localObject1);
        }
      }
      catch (HsqlException localHsqlException12)
      {
        return Result.newErrorResult(Error.error(2200), this.sql);
      }
      if (paramSession.isInMidTransaction())
        return Result.newErrorResult(Error.error(3701), this.sql);
      if (localObject3 == null)
        paramSession.setRole(null);
      if (paramSession.getGrantee().hasRole((Grantee)localObject3))
      {
        paramSession.setRole((Grantee)localObject3);
        return Result.updateZeroResult;
      }
      return Result.newErrorResult(Error.error(2200), this.sql);
    case 74:
      try
      {
        if (this.expressions == null)
          localObject1 = ((HsqlNameManager.HsqlName)this.parameters[0]).name;
        else
          localObject1 = (String)this.expressions[0].getValue(paramSession);
        localObject1 = (String)Type.SQL_VARCHAR.trim(paramSession, localObject1, 32, true, true);
        localObject3 = paramSession.database.schemaManager.getSchemaHsqlName((String)localObject1);
        paramSession.setCurrentSchemaHsqlName((HsqlNameManager.HsqlName)localObject3);
        return Result.updateZeroResult;
      }
      catch (HsqlException localHsqlException13)
      {
        return Result.newErrorResult(localHsqlException13, this.sql);
      }
    case 76:
      if (paramSession.isInMidTransaction())
        return Result.newErrorResult(Error.error(3701), this.sql);
      try
      {
        localObject3 = null;
        localObject1 = (String)this.expressions[0].getValue(paramSession);
        localObject1 = (String)Type.SQL_VARCHAR.trim(paramSession, localObject1, 32, true, true);
        if (this.expressions[1] != null)
          localObject3 = (String)this.expressions[1].getValue(paramSession);
        if (localObject3 == null)
          localObject5 = paramSession.database.userManager.get((String)localObject1);
        else
          localObject5 = paramSession.database.getUserManager().getUser((String)localObject1, (String)localObject3);
        if (localObject5 == null)
          throw Error.error(4001);
        this.sql = ((User)localObject5).getConnectUserSQL();
        if (localObject5 == paramSession.getGrantee())
          return Result.updateZeroResult;
        if ((localObject3 == null) && (!paramSession.isProcessingLog()) && (((User)localObject5).isAdmin()) && (!paramSession.getGrantee().isAdmin()))
          throw Error.error(4000);
        if (paramSession.getGrantee().canChangeAuthorisation())
        {
          paramSession.setUser((User)localObject5);
          paramSession.setRole(null);
          paramSession.resetSchema();
          return Result.updateZeroResult;
        }
        throw Error.error(4000);
      }
      catch (HsqlException localHsqlException2)
      {
        return Result.newErrorResult(localHsqlException2, this.sql);
      }
    case 109:
      try
      {
        if (this.parameters[0] != null)
        {
          boolean bool3 = ((Boolean)this.parameters[0]).booleanValue();
          paramSession.setReadOnlyDefault(bool3);
        }
        if (this.parameters[1] != null)
        {
          int j = ((Integer)this.parameters[1]).intValue();
          paramSession.setIsolationDefault(j);
        }
        return Result.updateZeroResult;
      }
      catch (HsqlException localHsqlException3)
      {
        return Result.newErrorResult(localHsqlException3, this.sql);
      }
    case 136:
      return Result.updateZeroResult;
    case 118:
      return Result.updateZeroResult;
    case 111:
      i = 1;
    case 75:
      try
      {
        if (this.parameters[0] != null)
        {
          boolean bool4 = ((Boolean)this.parameters[0]).booleanValue();
          paramSession.setReadOnly(bool4);
        }
        if (this.parameters[1] != null)
        {
          int k = ((Integer)this.parameters[1]).intValue();
          paramSession.setIsolation(k);
        }
        if (i != 0)
          paramSession.startTransaction();
        return Result.updateZeroResult;
      }
      catch (HsqlException localHsqlException4)
      {
        return Result.newErrorResult(localHsqlException4, this.sql);
      }
    case 1064:
      boolean bool5 = ((Boolean)this.parameters[0]).booleanValue();
      try
      {
        paramSession.setAutoCommit(bool5);
        return Result.updateZeroResult;
      }
      catch (HsqlException localHsqlException10)
      {
        return Result.newErrorResult(localHsqlException10, this.sql);
      }
    case 1075:
      ColumnSchema[] arrayOfColumnSchema = (ColumnSchema[])this.parameters[0];
      try
      {
        for (int n = 0; n < arrayOfColumnSchema.length; n++)
          paramSession.sessionContext.addSessionVariable(arrayOfColumnSchema[n]);
        return Result.updateZeroResult;
      }
      catch (HsqlException localHsqlException11)
      {
        return Result.newErrorResult(localHsqlException11, this.sql);
      }
    case 1065:
      m = ((Integer)this.parameters[0]).intValue();
      paramSession.setSQLMaxRows(m);
      return Result.updateZeroResult;
    case 1066:
      m = ((Integer)this.parameters[0]).intValue();
      paramSession.setResultMemoryRowCount(m);
      return Result.updateZeroResult;
    case 1042:
      try
      {
        boolean bool6 = ((Boolean)this.parameters[0]).booleanValue();
        paramSession.setIgnoreCase(bool6);
        return Result.updateZeroResult;
      }
      catch (HsqlException localHsqlException5)
      {
        return Result.newErrorResult(localHsqlException5, this.sql);
      }
    case 1068:
      localObject2 = (Table)this.parameters[0];
      localObject4 = (HsqlArrayList)this.parameters[1];
      localObject5 = (StatementDMQL)this.parameters[2];
      try
      {
        if (((HsqlArrayList)localObject4).size() != 0)
          localObject2 = ParserDDL.addTableConstraintDefinitions(paramSession, (Table)localObject2, (HsqlArrayList)localObject4, null, false);
        ((Table)localObject2).compile(paramSession, null);
        paramSession.sessionContext.addSessionTable((Table)localObject2);
        if (((Table)localObject2).hasLobColumn)
          throw Error.error(5534);
        if (localObject5 != null)
        {
          Result localResult2 = ((StatementDMQL)localObject5).execute(paramSession);
          ((Table)localObject2).insertIntoTable(paramSession, localResult2);
        }
        return Result.updateZeroResult;
      }
      catch (HsqlException localHsqlException14)
      {
        return Result.newErrorResult(localHsqlException14, this.sql);
      }
    case 32:
      localObject2 = (HsqlNameManager.HsqlName)this.parameters[0];
      localObject4 = (Boolean)this.parameters[1];
      localObject5 = paramSession.sessionContext.findSessionTable(((HsqlNameManager.HsqlName)localObject2).name);
      if (localObject5 == null)
      {
        if (((Boolean)localObject4).booleanValue())
          return Result.updateZeroResult;
        throw Error.error(5501, ((HsqlNameManager.HsqlName)localObject2).name);
      }
      paramSession.sessionContext.dropSessionTable(((HsqlNameManager.HsqlName)localObject2).name);
      return Result.updateZeroResult;
    }
    throw Error.runtimeError(201, "StatementSession");
  }

  public boolean isAutoCommitStatement()
  {
    return false;
  }

  public String describe(Session paramSession)
  {
    return this.sql;
  }

  public boolean isCatalogLock()
  {
    return false;
  }

  public boolean isCatalogChange()
  {
    return false;
  }

  static
  {
    commitNoChainStatement.sql = "COMMIT";
    commitAndChainStatement.sql = "COMMIT CHAIN";
    rollbackNoChainStatement.sql = "ROLLBACK";
    rollbackAndChainStatement.sql = "ROLLBACK CHAIN";
    commitNoChainStatement.compileTimestamp = 9223372036854775807L;
    commitAndChainStatement.compileTimestamp = 9223372036854775807L;
    rollbackNoChainStatement.compileTimestamp = 9223372036854775807L;
    rollbackAndChainStatement.compileTimestamp = 9223372036854775807L;
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.StatementSession
 * JD-Core Version:    0.6.2
 */