package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.HashMappedList;
import org.hsqldb.lib.HashSet;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.lib.OrderedIntHashSet;
import org.hsqldb.navigator.RowSetNavigator;
import org.hsqldb.result.Result;
import org.hsqldb.result.ResultMetaData;
import org.hsqldb.types.Type;

public class StatementCompound extends Statement
  implements RangeGroup
{
  final boolean isLoop;
  HsqlNameManager.HsqlName label;
  StatementHandler[] handlers = StatementHandler.emptyExceptionHandlerArray;
  boolean hasUndoHandler;
  StatementQuery loopCursor;
  Statement[] statements;
  StatementExpression condition;
  boolean isAtomic;
  ColumnSchema[] variables = ColumnSchema.emptyArray;
  StatementCursor[] cursors = StatementCursor.emptyArray;
  HashMappedList scopeVariables;
  RangeVariable[] rangeVariables = RangeVariable.emptyArray;
  Table[] tables = Table.emptyArray;
  HashMappedList scopeTables;
  public static final StatementCompound[] emptyStatementArray = new StatementCompound[0];

  StatementCompound(int paramInt, HsqlNameManager.HsqlName paramHsqlName)
  {
    super(paramInt, 2007);
    this.label = paramHsqlName;
    this.isTransactionStatement = false;
    switch (paramInt)
    {
    case 46:
    case 90:
    case 95:
    case 97:
      this.isLoop = true;
      break;
    case 12:
    case 88:
      this.isLoop = false;
      break;
    default:
      throw Error.runtimeError(201, "StatementCompound");
    }
  }

  public String getSQL()
  {
    return this.sql;
  }

  protected String describe(Session paramSession, int paramInt)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append('\n');
    for (int i = 0; i < paramInt; i++)
      localStringBuffer.append(' ');
    localStringBuffer.append("STATEMENT");
    return localStringBuffer.toString();
  }

  public void setLocalDeclarations(Object[] paramArrayOfObject)
  {
    int i = 0;
    int j = 0;
    int k = 0;
    int m = 0;
    for (int n = 0; n < paramArrayOfObject.length; n++)
      if ((paramArrayOfObject[n] instanceof ColumnSchema))
        i++;
      else if ((paramArrayOfObject[n] instanceof StatementHandler))
        j++;
      else if ((paramArrayOfObject[n] instanceof Table))
        m++;
      else
        k++;
    if (i > 0)
      this.variables = new ColumnSchema[i];
    if (j > 0)
      this.handlers = new StatementHandler[j];
    if (m > 0)
      this.tables = new Table[m];
    if (k > 0)
      this.cursors = new StatementCursor[k];
    i = 0;
    j = 0;
    m = 0;
    k = 0;
    for (n = 0; n < paramArrayOfObject.length; n++)
      if ((paramArrayOfObject[n] instanceof ColumnSchema))
      {
        this.variables[(i++)] = ((ColumnSchema)paramArrayOfObject[n]);
      }
      else
      {
        Object localObject;
        if ((paramArrayOfObject[n] instanceof StatementHandler))
        {
          localObject = (StatementHandler)paramArrayOfObject[n];
          ((StatementHandler)localObject).setParent(this);
          this.handlers[(j++)] = localObject;
          if (((StatementHandler)localObject).handlerType == 7)
            this.hasUndoHandler = true;
        }
        else if ((paramArrayOfObject[n] instanceof Table))
        {
          localObject = (Table)paramArrayOfObject[n];
          this.tables[(m++)] = localObject;
        }
        else
        {
          localObject = (StatementCursor)paramArrayOfObject[n];
          this.cursors[(k++)] = localObject;
        }
      }
    setVariables();
    setHandlers();
    setTables();
    setCursors();
  }

  public void setLoopStatement(StatementQuery paramStatementQuery)
  {
    this.loopCursor = paramStatementQuery;
    HsqlNameManager.HsqlName[] arrayOfHsqlName = paramStatementQuery.queryExpression.getResultColumnNames();
    Type[] arrayOfType = paramStatementQuery.queryExpression.getColumnTypes();
    ColumnSchema[] arrayOfColumnSchema = new ColumnSchema[arrayOfHsqlName.length];
    for (int i = 0; i < arrayOfHsqlName.length; i++)
    {
      arrayOfColumnSchema[i] = new ColumnSchema(arrayOfHsqlName[i], arrayOfType[i], false, false, null);
      arrayOfColumnSchema[i].setParameterMode((byte)1);
    }
    setLocalDeclarations(arrayOfColumnSchema);
  }

  void setStatements(Statement[] paramArrayOfStatement)
  {
    for (int i = 0; i < paramArrayOfStatement.length; i++)
      paramArrayOfStatement[i].setParent(this);
    this.statements = paramArrayOfStatement;
  }

  public void setCondition(StatementExpression paramStatementExpression)
  {
    this.condition = paramStatementExpression;
  }

  public Result execute(Session paramSession)
  {
    Result localResult;
    switch (this.type)
    {
    case 12:
      initialiseVariables(paramSession);
      localResult = executeBlock(paramSession);
      break;
    case 46:
      localResult = executeForLoop(paramSession);
      break;
    case 90:
    case 95:
    case 97:
      localResult = executeLoop(paramSession);
      break;
    case 88:
      localResult = executeIf(paramSession);
      break;
    default:
      throw Error.runtimeError(201, "StatementCompound");
    }
    if (localResult.isError())
      localResult.getException().setStatementType(this.group, this.type);
    return localResult;
  }

  private Result executeBlock(Session paramSession)
  {
    Result localResult = Result.updateZeroResult;
    int i = !this.root.isTrigger() ? 1 : 0;
    if (i != 0)
    {
      paramSession.sessionContext.push();
      if (this.hasUndoHandler)
      {
        String str = HsqlNameManager.getAutoSavepointNameString(paramSession.actionTimestamp, paramSession.sessionContext.depth);
        paramSession.savepoint(str);
      }
    }
    for (int j = 0; j < this.statements.length; j++)
    {
      localResult = this.statements[j].execute(paramSession);
      localResult = handleCondition(paramSession, localResult);
      if ((localResult.isError()) || (localResult.getType() == 42) || (localResult.getType() == 3))
        break;
    }
    if ((localResult.getType() == 42) && (localResult.getErrorCode() == 89))
      if (localResult.getMainString() == null)
        localResult = Result.updateZeroResult;
      else if ((this.label != null) && (this.label.name.equals(localResult.getMainString())))
        localResult = Result.updateZeroResult;
    if (i != 0)
      paramSession.sessionContext.pop();
    return localResult;
  }

  private Result handleCondition(Session paramSession, Result paramResult)
  {
    String str1 = null;
    if (paramResult.isError())
      str1 = paramResult.getSubString();
    else if (paramSession.getLastWarning() != null)
      str1 = paramSession.getLastWarning().getSQLState();
    else
      return paramResult;
    if (str1 != null)
    {
      for (int i = 0; i < this.handlers.length; i++)
      {
        StatementHandler localStatementHandler = this.handlers[i];
        paramSession.clearWarnings();
        if (localStatementHandler.handlesCondition(str1))
        {
          paramSession.resetSchema();
          String str2 = this.label == null ? null : this.label.name;
          switch (localStatementHandler.handlerType)
          {
          case 5:
            paramResult = Result.updateZeroResult;
            break;
          case 7:
            paramSession.rollbackToSavepoint();
            paramResult = Result.newPSMResult(89, str2, null);
            break;
          case 6:
            paramResult = Result.newPSMResult(89, null, null);
          }
          Result localResult = localStatementHandler.statement.execute(paramSession);
          if (localResult.isError())
          {
            paramResult = localResult;
            handleCondition(paramSession, paramResult);
          }
          else
          {
            return paramResult;
          }
        }
      }
      if (this.parent != null)
        return this.parent.handleCondition(paramSession, paramResult);
    }
    return paramResult;
  }

  private Result executeForLoop(Session paramSession)
  {
    Result localResult1 = this.loopCursor.execute(paramSession);
    if (localResult1.isError())
      return localResult1;
    Result localResult2 = Result.updateZeroResult;
    while (localResult1.navigator.hasNext())
    {
      localResult1.navigator.next();
      Object[] arrayOfObject = localResult1.navigator.getCurrent();
      initialiseVariables(paramSession, arrayOfObject, localResult1.metaData.getColumnCount());
      for (int i = 0; i < this.statements.length; i++)
      {
        localResult2 = this.statements[i].execute(paramSession);
        if ((localResult2.isError()) || (localResult2.getType() == 42) || (localResult2.getType() == 3))
          break;
      }
      if (localResult2.isError())
        break;
      if (localResult2.getType() == 42)
      {
        if (localResult2.getErrorCode() == 102)
        {
          if (localResult2.getMainString() != null)
            if ((this.label == null) || (!this.label.name.equals(localResult2.getMainString())))
              break;
        }
        else
        {
          if (localResult2.getErrorCode() != 89)
            break;
          break;
        }
      }
      else
        if (localResult2.getType() == 3)
          break;
    }
    localResult1.navigator.release();
    return localResult2;
  }

  private Result executeLoop(Session paramSession)
  {
    Result localResult = Result.updateZeroResult;
    label229: 
    do
    {
      do
      {
        while (true)
        {
          if (this.type == 97)
          {
            localResult = this.condition.execute(paramSession);
            if (localResult.isError())
              break label288;
            if (!Boolean.TRUE.equals(localResult.getValueObject()))
            {
              localResult = Result.updateZeroResult;
              break label288;
            }
          }
          for (int i = 0; i < this.statements.length; i++)
          {
            localResult = this.statements[i].execute(paramSession);
            if ((localResult.isError()) || (localResult.getType() == 42) || (localResult.getType() == 3))
              break;
          }
          if (localResult.isError())
            break label288;
          if (localResult.getType() != 42)
            break label229;
          if (localResult.getErrorCode() != 102)
            break;
          if (localResult.getMainString() != null)
            if ((this.label == null) || (!this.label.name.equals(localResult.getMainString())))
              break label288;
        }
        if (localResult.getErrorCode() != 89)
          break;
        if (localResult.getMainString() == null)
          localResult = Result.updateZeroResult;
        if ((this.label == null) || (!this.label.name.equals(localResult.getMainString())))
          break;
        localResult = Result.updateZeroResult;
        break;
        if (localResult.getType() == 3)
          break;
      }
      while (this.type != 95);
      localResult = this.condition.execute(paramSession);
      if (localResult.isError())
        break;
    }
    while (!Boolean.TRUE.equals(localResult.getValueObject()));
    localResult = Result.updateZeroResult;
    label288: return localResult;
  }

  private Result executeIf(Session paramSession)
  {
    Result localResult = Result.updateZeroResult;
    boolean bool = false;
    for (int i = 0; i < this.statements.length; i++)
      if (this.statements[i].getType() == 1201)
      {
        if (!bool)
        {
          localResult = this.statements[i].execute(paramSession);
          if (!localResult.isError())
          {
            Object localObject = localResult.getValueObject();
            bool = Boolean.TRUE.equals(localObject);
            i++;
          }
        }
      }
      else
      {
        localResult = Result.updateZeroResult;
        if (bool)
        {
          localResult = this.statements[i].execute(paramSession);
          if ((localResult.isError()) || (localResult.getType() == 42))
            break;
        }
      }
    return localResult;
  }

  public void resolve(Session paramSession)
  {
    for (int i = 0; i < this.statements.length; i++)
      if ((this.statements[i].getType() == 89) || (this.statements[i].getType() == 102))
      {
        if (!findLabel((StatementSimple)this.statements[i]))
          throw Error.error(5508, ((StatementSimple)this.statements[i]).label.name);
      }
      else if ((this.statements[i].getType() == 58) && (!this.root.isFunction()))
        throw Error.error(5602, "RETURN");
    for (i = 0; i < this.statements.length; i++)
      this.statements[i].resolve(paramSession);
    for (i = 0; i < this.handlers.length; i++)
      this.handlers[i].resolve(paramSession);
    OrderedHashSet localOrderedHashSet1 = new OrderedHashSet();
    OrderedHashSet localOrderedHashSet2 = new OrderedHashSet();
    OrderedHashSet localOrderedHashSet3 = new OrderedHashSet();
    for (int j = 0; j < this.variables.length; j++)
    {
      OrderedHashSet localOrderedHashSet4 = this.variables[j].getReferences();
      if (localOrderedHashSet4 != null)
        localOrderedHashSet3.addAll(localOrderedHashSet4);
    }
    if (this.condition != null)
    {
      localOrderedHashSet3.addAll(this.condition.getReferences());
      localOrderedHashSet2.addAll(this.condition.getTableNamesForRead());
    }
    for (j = 0; j < this.statements.length; j++)
    {
      localOrderedHashSet3.addAll(this.statements[j].getReferences());
      localOrderedHashSet2.addAll(this.statements[j].getTableNamesForRead());
      localOrderedHashSet1.addAll(this.statements[j].getTableNamesForWrite());
    }
    for (j = 0; j < this.handlers.length; j++)
    {
      localOrderedHashSet3.addAll(this.handlers[j].getReferences());
      localOrderedHashSet2.addAll(this.handlers[j].getTableNamesForRead());
      localOrderedHashSet1.addAll(this.handlers[j].getTableNamesForWrite());
    }
    localOrderedHashSet2.removeAll(localOrderedHashSet1);
    this.readTableNames = new HsqlNameManager.HsqlName[localOrderedHashSet2.size()];
    localOrderedHashSet2.toArray(this.readTableNames);
    this.writeTableNames = new HsqlNameManager.HsqlName[localOrderedHashSet1.size()];
    localOrderedHashSet1.toArray(this.writeTableNames);
    this.references = localOrderedHashSet3;
  }

  public void setRoot(Routine paramRoutine)
  {
    this.root = paramRoutine;
  }

  public String describe(Session paramSession)
  {
    return "";
  }

  public OrderedHashSet getReferences()
  {
    return this.references;
  }

  public void setAtomic(boolean paramBoolean)
  {
    this.isAtomic = paramBoolean;
  }

  private void setVariables()
  {
    HashMappedList localHashMappedList = new HashMappedList();
    if (this.variables.length == 0)
    {
      if (this.parent == null)
        this.rangeVariables = this.root.getRangeVariables();
      else
        this.rangeVariables = this.parent.rangeVariables;
      this.scopeVariables = localHashMappedList;
      return;
    }
    if ((this.parent != null) && (this.parent.scopeVariables != null))
      for (i = 0; i < this.parent.scopeVariables.size(); i++)
        localHashMappedList.add(this.parent.scopeVariables.getKey(i), this.parent.scopeVariables.get(i));
    for (int i = 0; i < this.variables.length; i++)
    {
      localObject = this.variables[i].getName().name;
      j = localHashMappedList.add(localObject, this.variables[i]);
      if (j == 0)
        throw Error.error(5606, (String)localObject);
      if (this.root.getParameterIndex((String)localObject) != -1)
        throw Error.error(5606, (String)localObject);
    }
    this.scopeVariables = localHashMappedList;
    RangeVariable[] arrayOfRangeVariable = this.root.getRangeVariables();
    Object localObject = new RangeVariable(localHashMappedList, null, true, 4);
    this.rangeVariables = new RangeVariable[arrayOfRangeVariable.length + 1];
    for (int j = 0; j < arrayOfRangeVariable.length; j++)
      this.rangeVariables[j] = arrayOfRangeVariable[j];
    this.rangeVariables[arrayOfRangeVariable.length] = localObject;
    this.root.variableCount = localHashMappedList.size();
  }

  private void setHandlers()
  {
    if (this.handlers.length == 0)
      return;
    HashSet localHashSet = new HashSet();
    OrderedIntHashSet localOrderedIntHashSet = new OrderedIntHashSet();
    for (int i = 0; i < this.handlers.length; i++)
    {
      int[] arrayOfInt = this.handlers[i].getConditionTypes();
      for (int j = 0; j < arrayOfInt.length; j++)
        if (!localOrderedIntHashSet.add(arrayOfInt[j]))
          throw Error.error(5601);
      String[] arrayOfString = this.handlers[i].getConditionStates();
      for (int k = 0; k < arrayOfString.length; k++)
        if (!localHashSet.add(arrayOfString[k]))
          throw Error.error(5601);
    }
  }

  private void setTables()
  {
    if (this.tables.length == 0)
      return;
    HashMappedList localHashMappedList = new HashMappedList();
    if ((this.parent != null) && (this.parent.scopeTables != null))
      for (i = 0; i < this.parent.scopeTables.size(); i++)
        localHashMappedList.add(this.parent.scopeTables.getKey(i), this.parent.scopeTables.get(i));
    for (int i = 0; i < this.tables.length; i++)
    {
      String str = this.tables[i].getName().name;
      boolean bool = localHashMappedList.add(str, this.tables[i]);
      if (!bool)
        throw Error.error(5606, str);
    }
    this.scopeTables = localHashMappedList;
  }

  private void setCursors()
  {
    if (this.cursors.length == 0)
      return;
    HashSet localHashSet = new HashSet();
    for (int i = 0; i < this.cursors.length; i++)
    {
      StatementCursor localStatementCursor = this.cursors[i];
      boolean bool = localHashSet.add(localStatementCursor.getCursorName().name);
      if (!bool)
        throw Error.error(5606, localStatementCursor.getCursorName().name);
    }
  }

  private boolean findLabel(StatementSimple paramStatementSimple)
  {
    if ((this.label != null) && (paramStatementSimple.label.name.equals(this.label.name)))
      return (this.isLoop) || (paramStatementSimple.getType() != 102);
    if (this.parent == null)
      return false;
    return this.parent.findLabel(paramStatementSimple);
  }

  private void initialiseVariables(Session paramSession)
  {
    Object[] arrayOfObject = paramSession.sessionContext.routineVariables;
    int i = this.parent == null ? 0 : this.parent.scopeVariables.size();
    for (int j = 0; j < this.variables.length; j++)
      try
      {
        arrayOfObject[(i + j)] = this.variables[j].getDefaultValue(paramSession);
      }
      catch (HsqlException localHsqlException)
      {
      }
  }

  private void initialiseVariables(Session paramSession, Object[] paramArrayOfObject, int paramInt)
  {
    Object[] arrayOfObject = paramSession.sessionContext.routineVariables;
    int i = this.parent == null ? 0 : this.parent.scopeVariables.size();
    for (int j = 0; j < paramInt; j++)
      try
      {
        arrayOfObject[(i + j)] = paramArrayOfObject[j];
      }
      catch (HsqlException localHsqlException)
      {
      }
  }

  public RangeVariable[] getRangeVariables()
  {
    return this.rangeVariables;
  }

  public void setCorrelated()
  {
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.StatementCompound
 * JD-Core Version:    0.6.2
 */