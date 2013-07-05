package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.persist.Logger;
import org.hsqldb.result.Result;

public class StatementSchemaDefinition extends StatementSchema
{
  StatementSchema[] statements;

  StatementSchemaDefinition(StatementSchema[] paramArrayOfStatementSchema)
  {
    super(64, 2001);
    this.statements = paramArrayOfStatementSchema;
  }

  public Result execute(Session paramSession)
  {
    Result localResult;
    try
    {
      localResult = getResult(paramSession);
    }
    catch (Throwable localThrowable)
    {
      localResult = Result.newErrorResult(localThrowable, null);
    }
    if (localResult.isError())
      localResult.getException().setStatementType(this.group, this.type);
    return localResult;
  }

  Result getResult(Session paramSession)
  {
    HsqlNameManager.HsqlName localHsqlName1 = this.statements[0].getSchemaName();
    if (this.isExplain)
      return Result.newSingleColumnStringResult("OPERATION", describe(paramSession));
    Result localResult = this.statements[0].execute(paramSession);
    HsqlArrayList localHsqlArrayList = new HsqlArrayList();
    StatementSchema localStatementSchema2 = new StatementSchema(null, 1199);
    if ((this.statements.length == 1) || (localResult.isError()))
      return localResult;
    HsqlNameManager.HsqlName localHsqlName2 = paramSession.getCurrentSchemaHsqlName();
    Table localTable;
    for (int i = 1; i < this.statements.length; i++)
    {
      try
      {
        paramSession.setSchema(localHsqlName1.name);
      }
      catch (HsqlException localHsqlException3)
      {
      }
      this.statements[i].setSchemaHsqlName(localHsqlName1);
      paramSession.parser.reset(this.statements[i].getSQL());
      try
      {
        paramSession.parser.read();
        StatementSchema localStatementSchema1;
        switch (this.statements[i].getType())
        {
        case 48:
        case 49:
          localResult = this.statements[i].execute(paramSession);
          break;
        case 77:
          localStatementSchema1 = paramSession.parser.compileCreate();
          localStatementSchema1.isSchemaDefinition = true;
          localStatementSchema1.setSchemaHsqlName(localHsqlName1);
          if (paramSession.parser.token.tokenType != 848)
            throw paramSession.parser.unexpectedToken();
          localStatementSchema1.isLogged = false;
          localResult = localStatementSchema1.execute(paramSession);
          HsqlNameManager.HsqlName localHsqlName3 = ((Table)localStatementSchema1.arguments[0]).getName();
          localTable = (Table)paramSession.database.schemaManager.getSchemaObject(localHsqlName3);
          localHsqlArrayList.addAll((HsqlArrayList)localStatementSchema1.arguments[1]);
          ((HsqlArrayList)localStatementSchema1.arguments[1]).clear();
          localStatementSchema2.sql = localTable.getSQL();
          localStatementSchema2.execute(paramSession);
          break;
        case 8:
        case 10:
        case 61:
        case 83:
        case 133:
          localResult = this.statements[i].execute(paramSession);
          break;
        case 14:
        case 23:
        case 80:
        case 84:
        case 1073:
          localStatementSchema1 = paramSession.parser.compileCreate();
          localStatementSchema1.isSchemaDefinition = true;
          localStatementSchema1.setSchemaHsqlName(localHsqlName1);
          if (paramSession.parser.token.tokenType != 848)
            throw paramSession.parser.unexpectedToken();
          localResult = localStatementSchema1.execute(paramSession);
          break;
        case 6:
        case 52:
        case 79:
        case 114:
        case 117:
          throw paramSession.parser.unsupportedFeature();
        default:
          throw Error.runtimeError(201, "");
        }
        if (localResult.isError())
          break;
      }
      catch (HsqlException localHsqlException4)
      {
        localResult = Result.newErrorResult(localHsqlException4, this.statements[i].getSQL());
        break;
      }
    }
    if (!localResult.isError())
      try
      {
        for (i = 0; i < localHsqlArrayList.size(); i++)
        {
          Constraint localConstraint = (Constraint)localHsqlArrayList.get(i);
          localTable = paramSession.database.schemaManager.getUserTable(paramSession, localConstraint.core.refTableName);
          ParserDDL.addForeignKey(paramSession, localTable, localConstraint, null);
          localStatementSchema2.sql = localConstraint.getSQL();
          localStatementSchema2.execute(paramSession);
        }
      }
      catch (HsqlException localHsqlException1)
      {
        localResult = Result.newErrorResult(localHsqlException1, this.sql);
      }
    if (localResult.isError())
      try
      {
        paramSession.database.schemaManager.dropSchema(paramSession, localHsqlName1.name, true);
        paramSession.database.logger.writeOtherStatement(paramSession, getDropSchemaStatement(localHsqlName1));
      }
      catch (HsqlException localHsqlException2)
      {
      }
    paramSession.setCurrentSchemaHsqlName(localHsqlName2);
    return localResult;
  }

  String getDropSchemaStatement(HsqlNameManager.HsqlName paramHsqlName)
  {
    return "DROP SCHEMA " + paramHsqlName.statementName + " " + "CASCADE";
  }

  public boolean isAutoCommitStatement()
  {
    return true;
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.StatementSchemaDefinition
 * JD-Core Version:    0.6.2
 */