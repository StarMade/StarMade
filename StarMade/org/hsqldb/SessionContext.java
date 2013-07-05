package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.HashMappedList;
import org.hsqldb.lib.HashSet;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.lib.LongDeque;
import org.hsqldb.navigator.RangeIterator;
import org.hsqldb.navigator.RowSetNavigatorDataChange;
import org.hsqldb.navigator.RowSetNavigatorDataChangeMemory;
import org.hsqldb.persist.PersistentStoreCollectionSession;
import org.hsqldb.store.ValuePool;

public class SessionContext
{
  Session session;
  public Boolean isAutoCommit;
  Boolean isReadOnly;
  Boolean noSQL;
  int currentMaxRows;
  HashMappedList sessionVariables;
  RangeVariable[] sessionVariablesRange;
  private HsqlArrayList stack;
  Object[] diagnosticsVariables = ValuePool.emptyObjectArray;
  Object[] routineArguments = ValuePool.emptyObjectArray;
  Object[] routineVariables = ValuePool.emptyObjectArray;
  Object[] dynamicArguments = ValuePool.emptyObjectArray;
  Object[][] triggerArguments = (Object[][])null;
  public int depth;
  Number lastIdentity = ValuePool.INTEGER_0;
  HashMappedList savepoints;
  LongDeque savepointTimestamps;
  RangeIterator[] rangeIterators;
  HashMappedList sessionTables;
  HashMappedList popSessionTables;
  public Statement currentStatement;
  public int rownum;
  HashSet constraintPath;
  StatementResultUpdate rowUpdateStatement = new StatementResultUpdate();

  SessionContext(Session paramSession)
  {
    this.session = paramSession;
    this.diagnosticsVariables = new Object[ExpressionColumn.diagnosticsVariableTokens.length];
    this.rangeIterators = new RangeIterator[8];
    this.savepoints = new HashMappedList(4);
    this.savepointTimestamps = new LongDeque();
    this.sessionVariables = new HashMappedList();
    this.sessionVariablesRange = new RangeVariable[1];
    this.sessionVariablesRange[0] = new RangeVariable(this.sessionVariables, null, true, 4);
    this.isAutoCommit = Boolean.FALSE;
    this.isReadOnly = Boolean.FALSE;
    this.noSQL = Boolean.FALSE;
  }

  public void push()
  {
    if (this.session.sessionContext.depth > 256)
      throw Error.error(458);
    this.session.sessionData.persistentStoreCollection.push();
    if (this.stack == null)
      this.stack = new HsqlArrayList(32, true);
    this.stack.add(this.diagnosticsVariables);
    this.stack.add(this.dynamicArguments);
    this.stack.add(this.routineArguments);
    this.stack.add(this.triggerArguments);
    this.stack.add(this.routineVariables);
    this.stack.add(this.rangeIterators);
    this.stack.add(this.savepoints);
    this.stack.add(this.savepointTimestamps);
    this.stack.add(this.lastIdentity);
    this.stack.add(this.isAutoCommit);
    this.stack.add(this.isReadOnly);
    this.stack.add(this.noSQL);
    this.stack.add(ValuePool.getInt(this.currentMaxRows));
    this.stack.add(ValuePool.getInt(this.rownum));
    this.diagnosticsVariables = new Object[ExpressionColumn.diagnosticsVariableTokens.length];
    this.rangeIterators = new RangeIterator[8];
    this.savepoints = new HashMappedList(4);
    this.savepointTimestamps = new LongDeque();
    this.isAutoCommit = Boolean.FALSE;
    this.currentMaxRows = 0;
    this.depth += 1;
  }

  public void pop()
  {
    this.session.sessionData.persistentStoreCollection.pop();
    this.rownum = ((Integer)this.stack.remove(this.stack.size() - 1)).intValue();
    this.currentMaxRows = ((Integer)this.stack.remove(this.stack.size() - 1)).intValue();
    this.noSQL = ((Boolean)this.stack.remove(this.stack.size() - 1));
    this.isReadOnly = ((Boolean)this.stack.remove(this.stack.size() - 1));
    this.isAutoCommit = ((Boolean)this.stack.remove(this.stack.size() - 1));
    this.lastIdentity = ((Number)this.stack.remove(this.stack.size() - 1));
    this.savepointTimestamps = ((LongDeque)this.stack.remove(this.stack.size() - 1));
    this.savepoints = ((HashMappedList)this.stack.remove(this.stack.size() - 1));
    this.rangeIterators = ((RangeIterator[])this.stack.remove(this.stack.size() - 1));
    this.routineVariables = ((Object[])this.stack.remove(this.stack.size() - 1));
    this.triggerArguments = ((Object[][])this.stack.remove(this.stack.size() - 1));
    this.routineArguments = ((Object[])this.stack.remove(this.stack.size() - 1));
    this.dynamicArguments = ((Object[])this.stack.remove(this.stack.size() - 1));
    this.diagnosticsVariables = ((Object[])this.stack.remove(this.stack.size() - 1));
    this.depth -= 1;
  }

  public void pushDynamicArguments(Object[] paramArrayOfObject)
  {
    push();
    this.dynamicArguments = paramArrayOfObject;
  }

  public void pushStatementState()
  {
    if (this.stack == null)
      this.stack = new HsqlArrayList(32, true);
    this.stack.add(ValuePool.getInt(this.rownum));
  }

  public void popStatementState()
  {
    this.rownum = ((Integer)this.stack.remove(this.stack.size() - 1)).intValue();
  }

  public void setDynamicArguments(Object[] paramArrayOfObject)
  {
    this.dynamicArguments = paramArrayOfObject;
  }

  RowSetNavigatorDataChange getRowSetDataChange()
  {
    return new RowSetNavigatorDataChangeMemory(this.session);
  }

  void clearStructures(StatementDMQL paramStatementDMQL)
  {
    int i = paramStatementDMQL.rangeIteratorCount;
    if (i > this.rangeIterators.length)
      i = this.rangeIterators.length;
    for (int j = 0; j < i; j++)
      if (this.rangeIterators[j] != null)
      {
        this.rangeIterators[j].release();
        this.rangeIterators[j] = null;
      }
  }

  public RangeVariable.RangeIteratorBase getCheckIterator(RangeVariable paramRangeVariable)
  {
    Object localObject = this.rangeIterators[0];
    if (localObject == null)
    {
      localObject = paramRangeVariable.getIterator(this.session);
      this.rangeIterators[0] = localObject;
    }
    return (RangeVariable.RangeIteratorBase)localObject;
  }

  public void setRangeIterator(RangeIterator paramRangeIterator)
  {
    int i = paramRangeIterator.getRangePosition();
    if (i >= this.rangeIterators.length)
      this.rangeIterators = ((RangeIterator[])ArrayUtil.resizeArray(this.rangeIterators, i + 4));
    this.rangeIterators[i] = paramRangeIterator;
  }

  public void unsetRangeIterator(RangeIterator paramRangeIterator)
  {
    int i = paramRangeIterator.getRangePosition();
    this.rangeIterators[i] = null;
  }

  public HashSet getConstraintPath()
  {
    if (this.constraintPath == null)
      this.constraintPath = new HashSet();
    else
      this.constraintPath.clear();
    return this.constraintPath;
  }

  public void addSessionVariable(ColumnSchema paramColumnSchema)
  {
    int i = this.sessionVariables.size();
    if (!this.sessionVariables.add(paramColumnSchema.getName().name, paramColumnSchema))
      throw Error.error(5504);
    Object[] arrayOfObject = new Object[this.sessionVariables.size()];
    ArrayUtil.copyArray(this.routineVariables, arrayOfObject, this.routineVariables.length);
    this.routineVariables = arrayOfObject;
    this.routineVariables[i] = paramColumnSchema.getDefaultValue(this.session);
  }

  public void pushRoutineTables(HashMappedList paramHashMappedList)
  {
    this.popSessionTables = this.sessionTables;
    this.sessionTables = paramHashMappedList;
  }

  public void popRoutineTables()
  {
    this.sessionTables = this.popSessionTables;
  }

  public void addSessionTable(Table paramTable)
  {
    if (this.sessionTables == null)
      this.sessionTables = new HashMappedList();
    if (this.sessionTables.containsKey(paramTable.getName().name))
      throw Error.error(5504);
    this.sessionTables.add(paramTable.getName().name, paramTable);
  }

  public void setSessionTables(Table[] paramArrayOfTable)
  {
  }

  public Table findSessionTable(String paramString)
  {
    if (this.sessionTables == null)
      return null;
    return (Table)this.sessionTables.get(paramString);
  }

  public void dropSessionTable(String paramString)
  {
    this.sessionTables.remove(paramString);
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.SessionContext
 * JD-Core Version:    0.6.2
 */