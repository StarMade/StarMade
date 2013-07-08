package org.hsqldb;

import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.result.Result;

public class TriggerDefSQL
  extends TriggerDef
{
  OrderedHashSet references;
  
  public TriggerDefSQL(HsqlNameManager.HsqlName paramHsqlName, int paramInt1, int paramInt2, boolean paramBoolean, Table paramTable, Table[] paramArrayOfTable, RangeVariable[] paramArrayOfRangeVariable, Expression paramExpression, String paramString, int[] paramArrayOfInt, Routine paramRoutine)
  {
    super(paramHsqlName, paramInt1, paramInt2, paramBoolean, paramTable, paramArrayOfTable, paramArrayOfRangeVariable, paramExpression, paramString, paramArrayOfInt);
    this.routine = paramRoutine;
    this.references = paramRoutine.getReferences();
  }
  
  public OrderedHashSet getReferences()
  {
    return this.routine.getReferences();
  }
  
  public OrderedHashSet getComponents()
  {
    return null;
  }
  
  public void compile(Session paramSession, SchemaObject paramSchemaObject) {}
  
  public String getClassName()
  {
    return null;
  }
  
  public boolean hasOldTable()
  {
    return this.transitions[2] != null;
  }
  
  public boolean hasNewTable()
  {
    return this.transitions[3] != null;
  }
  
  synchronized void pushPair(Session paramSession, Object[] paramArrayOfObject1, Object[] paramArrayOfObject2)
  {
    Result localResult = Result.updateZeroResult;
    paramSession.sessionContext.push();
    if ((this.rangeVars[0] != null) || (this.rangeVars[1] != null)) {
      paramSession.sessionContext.triggerArguments = new Object[][] { paramArrayOfObject1, paramArrayOfObject2 };
    }
    if (this.condition.testCondition(paramSession))
    {
      int i = this.routine.getVariableCount();
      paramSession.sessionContext.routineVariables = new Object[i];
      localResult = this.routine.statement.execute(paramSession);
    }
    paramSession.sessionContext.pop();
    if (localResult.isError()) {
      throw localResult.getException();
    }
  }
  
  public String getSQL()
  {
    StringBuffer localStringBuffer = getSQLMain();
    localStringBuffer.append(this.routine.statement.getSQL());
    return localStringBuffer.toString();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.TriggerDefSQL
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */