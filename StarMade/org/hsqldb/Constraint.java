package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.index.Index;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.navigator.RowIterator;
import org.hsqldb.navigator.RowSetNavigator;
import org.hsqldb.persist.PersistentStore;
import org.hsqldb.result.Result;
import org.hsqldb.rights.Grantee;
import org.hsqldb.types.Type;

public final class Constraint
  implements SchemaObject
{
  ConstraintCore core;
  private HsqlNameManager.HsqlName name;
  int constType;
  boolean isForward;
  Expression check;
  private boolean isNotNull;
  int notNullColumnIndex;
  RangeVariable rangeVariable;
  OrderedHashSet mainColSet;
  OrderedHashSet refColSet;
  public static final Constraint[] emptyArray = new Constraint[0];
  
  private Constraint() {}
  
  public Constraint(HsqlNameManager.HsqlName paramHsqlName, Table paramTable, Index paramIndex, int paramInt)
  {
    this.name = paramHsqlName;
    this.constType = paramInt;
    this.core = new ConstraintCore();
    this.core.mainTable = paramTable;
    this.core.mainIndex = paramIndex;
    this.core.mainCols = paramIndex.getColumns();
    for (int i = 0; i < this.core.mainCols.length; i++)
    {
      Type localType = paramTable.getColumn(this.core.mainCols[i]).getDataType();
      if (localType.isLobType()) {
        throw Error.error(5534);
      }
    }
  }
  
  public Constraint(HsqlNameManager.HsqlName paramHsqlName, Table paramTable, int[] paramArrayOfInt, int paramInt)
  {
    this.name = paramHsqlName;
    this.constType = paramInt;
    this.core = new ConstraintCore();
    this.core.mainTable = paramTable;
    this.core.mainCols = paramArrayOfInt;
  }
  
  public Constraint(HsqlNameManager.HsqlName paramHsqlName, Constraint paramConstraint)
  {
    this.name = paramHsqlName;
    this.constType = 1;
    this.core = paramConstraint.core;
  }
  
  public Constraint(HsqlNameManager.HsqlName paramHsqlName1, HsqlNameManager.HsqlName paramHsqlName2, OrderedHashSet paramOrderedHashSet1, HsqlNameManager.HsqlName paramHsqlName3, OrderedHashSet paramOrderedHashSet2, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.name = paramHsqlName1;
    this.constType = paramInt1;
    this.mainColSet = paramOrderedHashSet2;
    this.refColSet = paramOrderedHashSet1;
    this.core = new ConstraintCore();
    this.core.refTableName = paramHsqlName2;
    this.core.mainTableName = paramHsqlName3;
    this.core.deleteAction = paramInt2;
    this.core.updateAction = paramInt3;
    this.core.matchType = paramInt4;
    switch (this.core.deleteAction)
    {
    case 0: 
    case 2: 
    case 4: 
      this.core.hasDeleteAction = true;
    }
    switch (this.core.updateAction)
    {
    case 0: 
    case 2: 
    case 4: 
      this.core.hasUpdateAction = true;
    }
  }
  
  public Constraint(HsqlNameManager.HsqlName paramHsqlName, OrderedHashSet paramOrderedHashSet, int paramInt)
  {
    this.name = paramHsqlName;
    this.constType = paramInt;
    this.mainColSet = paramOrderedHashSet;
    this.core = new ConstraintCore();
  }
  
  public Constraint(HsqlNameManager.HsqlName paramHsqlName1, HsqlNameManager.HsqlName paramHsqlName2, HsqlNameManager.HsqlName paramHsqlName3, Table paramTable1, Table paramTable2, int[] paramArrayOfInt1, int[] paramArrayOfInt2, Index paramIndex1, Index paramIndex2, int paramInt1, int paramInt2)
    throws HsqlException
  {
    this.name = paramHsqlName3;
    this.constType = 0;
    this.core = new ConstraintCore();
    this.core.uniqueName = paramHsqlName1;
    this.core.mainName = paramHsqlName2;
    this.core.refName = paramHsqlName3;
    this.core.mainTable = paramTable1;
    this.core.refTable = paramTable2;
    this.core.mainCols = paramArrayOfInt1;
    this.core.refCols = paramArrayOfInt2;
    this.core.mainIndex = paramIndex1;
    this.core.refIndex = paramIndex2;
    this.core.deleteAction = paramInt1;
    this.core.updateAction = paramInt2;
  }
  
  Constraint duplicate()
  {
    Constraint localConstraint = new Constraint();
    localConstraint.core = this.core.duplicate();
    localConstraint.name = this.name;
    localConstraint.constType = this.constType;
    localConstraint.isForward = this.isForward;
    localConstraint.check = this.check;
    localConstraint.isNotNull = this.isNotNull;
    localConstraint.notNullColumnIndex = this.notNullColumnIndex;
    localConstraint.rangeVariable = this.rangeVariable;
    return localConstraint;
  }
  
  void setColumnsIndexes(Table paramTable)
  {
    int i;
    Type localType;
    if (this.constType == 0)
    {
      if (this.mainColSet == null)
      {
        this.core.mainCols = this.core.mainTable.getPrimaryKey();
        if (this.core.mainCols == null) {
          throw Error.error(5581);
        }
      }
      else if (this.core.mainCols == null)
      {
        this.core.mainCols = this.core.mainTable.getColumnIndexes(this.mainColSet);
      }
      if (this.core.refCols == null) {
        this.core.refCols = paramTable.getColumnIndexes(this.refColSet);
      }
      for (i = 0; i < this.core.refCols.length; i++)
      {
        localType = paramTable.getColumn(this.core.refCols[i]).getDataType();
        if (localType.isLobType()) {
          throw Error.error(5534);
        }
      }
    }
    else if (this.mainColSet != null)
    {
      this.core.mainCols = paramTable.getColumnIndexes(this.mainColSet);
      for (i = 0; i < this.core.mainCols.length; i++)
      {
        localType = paramTable.getColumn(this.core.mainCols[i]).getDataType();
        if (localType.isLobType()) {
          throw Error.error(5534);
        }
      }
    }
  }
  
  public int getType()
  {
    return 5;
  }
  
  public HsqlNameManager.HsqlName getName()
  {
    return this.name;
  }
  
  public HsqlNameManager.HsqlName getCatalogName()
  {
    return this.name.schema.schema;
  }
  
  public HsqlNameManager.HsqlName getSchemaName()
  {
    return this.name.schema;
  }
  
  public Grantee getOwner()
  {
    return this.name.schema.owner;
  }
  
  public OrderedHashSet getReferences()
  {
    switch (this.constType)
    {
    case 3: 
      OrderedHashSet localOrderedHashSet1 = new OrderedHashSet();
      this.check.collectObjectNames(localOrderedHashSet1);
      for (int i = localOrderedHashSet1.size() - 1; i >= 0; i--)
      {
        HsqlNameManager.HsqlName localHsqlName = (HsqlNameManager.HsqlName)localOrderedHashSet1.get(i);
        if ((localHsqlName.type == 9) || (localHsqlName.type == 3)) {
          localOrderedHashSet1.remove(i);
        }
      }
      return localOrderedHashSet1;
    case 0: 
      OrderedHashSet localOrderedHashSet2 = new OrderedHashSet();
      localOrderedHashSet2.add(this.core.uniqueName);
      return localOrderedHashSet2;
    }
    return new OrderedHashSet();
  }
  
  public OrderedHashSet getComponents()
  {
    return null;
  }
  
  public void compile(Session paramSession, SchemaObject paramSchemaObject) {}
  
  public String getSQL()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    switch (getConstraintType())
    {
    case 4: 
      if ((getMainColumns().length > 1) || ((getMainColumns().length == 1) && (!getName().isReservedName())))
      {
        if (!getName().isReservedName())
        {
          localStringBuffer.append("CONSTRAINT").append(' ');
          localStringBuffer.append(getName().statementName).append(' ');
        }
        localStringBuffer.append("PRIMARY").append(' ').append("KEY");
        localStringBuffer.append(getMain().getColumnListSQL(getMainColumns(), getMainColumns().length));
      }
      break;
    case 2: 
      if (!getName().isReservedName())
      {
        localStringBuffer.append("CONSTRAINT").append(' ');
        localStringBuffer.append(getName().statementName);
        localStringBuffer.append(' ');
      }
      localStringBuffer.append("UNIQUE");
      int[] arrayOfInt = getMainColumns();
      localStringBuffer.append(getMain().getColumnListSQL(arrayOfInt, arrayOfInt.length));
      break;
    case 0: 
      if (this.isForward)
      {
        localStringBuffer.append("ALTER").append(' ').append("TABLE").append(' ');
        localStringBuffer.append(getRef().getName().getSchemaQualifiedStatementName());
        localStringBuffer.append(' ').append("ADD").append(' ');
        getFKStatement(localStringBuffer);
      }
      else
      {
        getFKStatement(localStringBuffer);
      }
      break;
    case 3: 
      if (!isNotNull())
      {
        if (!getName().isReservedName())
        {
          localStringBuffer.append("CONSTRAINT").append(' ');
          localStringBuffer.append(getName().statementName).append(' ');
        }
        localStringBuffer.append("CHECK").append('(');
        localStringBuffer.append(this.check.getSQL());
        localStringBuffer.append(')');
      }
      break;
    }
    return localStringBuffer.toString();
  }
  
  public long getChangeTimestamp()
  {
    return 0L;
  }
  
  private void getFKStatement(StringBuffer paramStringBuffer)
  {
    if (!getName().isReservedName())
    {
      paramStringBuffer.append("CONSTRAINT").append(' ');
      paramStringBuffer.append(getName().statementName);
      paramStringBuffer.append(' ');
    }
    paramStringBuffer.append("FOREIGN").append(' ').append("KEY");
    int[] arrayOfInt = getRefColumns();
    paramStringBuffer.append(getRef().getColumnListSQL(arrayOfInt, arrayOfInt.length));
    paramStringBuffer.append(' ').append("REFERENCES").append(' ');
    paramStringBuffer.append(getMain().getName().getSchemaQualifiedStatementName());
    arrayOfInt = getMainColumns();
    paramStringBuffer.append(getMain().getColumnListSQL(arrayOfInt, arrayOfInt.length));
    if (getDeleteAction() != 3)
    {
      paramStringBuffer.append(' ').append("ON").append(' ').append("DELETE").append(' ');
      paramStringBuffer.append(getDeleteActionString());
    }
    if (getUpdateAction() != 3)
    {
      paramStringBuffer.append(' ').append("ON").append(' ').append("UPDATE").append(' ');
      paramStringBuffer.append(getUpdateActionString());
    }
  }
  
  public HsqlNameManager.HsqlName getMainTableName()
  {
    return this.core.mainTableName;
  }
  
  public HsqlNameManager.HsqlName getMainName()
  {
    return this.core.mainName;
  }
  
  public HsqlNameManager.HsqlName getRefName()
  {
    return this.core.refName;
  }
  
  public HsqlNameManager.HsqlName getUniqueName()
  {
    return this.core.uniqueName;
  }
  
  public int getConstraintType()
  {
    return this.constType;
  }
  
  public Table getMain()
  {
    return this.core.mainTable;
  }
  
  Index getMainIndex()
  {
    return this.core.mainIndex;
  }
  
  public Table getRef()
  {
    return this.core.refTable;
  }
  
  Index getRefIndex()
  {
    return this.core.refIndex;
  }
  
  private static String getActionString(int paramInt)
  {
    switch (paramInt)
    {
    case 1: 
      return "RESTRICT";
    case 0: 
      return "CASCADE";
    case 4: 
      return "SET DEFAULT";
    case 2: 
      return "SET NULL";
    }
    return "NO ACTION";
  }
  
  public int getDeleteAction()
  {
    return this.core.deleteAction;
  }
  
  public String getDeleteActionString()
  {
    return getActionString(this.core.deleteAction);
  }
  
  public int getUpdateAction()
  {
    return this.core.updateAction;
  }
  
  public String getUpdateActionString()
  {
    return getActionString(this.core.updateAction);
  }
  
  public boolean hasTriggeredAction()
  {
    if (this.constType == 0)
    {
      switch (this.core.deleteAction)
      {
      case 0: 
      case 2: 
      case 4: 
        return true;
      }
      switch (this.core.updateAction)
      {
      case 0: 
      case 2: 
      case 4: 
        return true;
      }
    }
    return false;
  }
  
  public int getDeferability()
  {
    return 0;
  }
  
  public int[] getMainColumns()
  {
    return this.core.mainCols;
  }
  
  public int[] getRefColumns()
  {
    return this.core.refCols;
  }
  
  public String getCheckSQL()
  {
    return this.check.getSQL();
  }
  
  public boolean isNotNull()
  {
    return this.isNotNull;
  }
  
  boolean hasColumnOnly(int paramInt)
  {
    switch (this.constType)
    {
    case 3: 
      return (this.rangeVariable.usedColumns[paramInt] != 0) && (ArrayUtil.countTrueElements(this.rangeVariable.usedColumns) == 1);
    case 2: 
    case 4: 
      return (this.core.mainCols.length == 1) && (this.core.mainCols[0] == paramInt);
    case 1: 
      return (this.core.mainCols.length == 1) && (this.core.mainCols[0] == paramInt) && (this.core.mainTable == this.core.refTable);
    case 0: 
      return (this.core.refCols.length == 1) && (this.core.refCols[0] == paramInt) && (this.core.mainTable == this.core.refTable);
    }
    throw Error.runtimeError(201, "Constraint");
  }
  
  boolean hasColumnPlus(int paramInt)
  {
    switch (this.constType)
    {
    case 3: 
      return (this.rangeVariable.usedColumns[paramInt] != 0) && (ArrayUtil.countTrueElements(this.rangeVariable.usedColumns) > 1);
    case 2: 
    case 4: 
      return (this.core.mainCols.length != 1) && (ArrayUtil.find(this.core.mainCols, paramInt) != -1);
    case 1: 
      return (ArrayUtil.find(this.core.mainCols, paramInt) != -1) && ((this.core.mainCols.length != 1) || (this.core.mainTable != this.core.refTable));
    case 0: 
      return (ArrayUtil.find(this.core.refCols, paramInt) != -1) && ((this.core.mainCols.length != 1) || (this.core.mainTable != this.core.refTable));
    }
    throw Error.runtimeError(201, "Constraint");
  }
  
  boolean hasColumn(int paramInt)
  {
    switch (this.constType)
    {
    case 3: 
      return this.rangeVariable.usedColumns[paramInt];
    case 1: 
    case 2: 
    case 4: 
      return ArrayUtil.find(this.core.mainCols, paramInt) != -1;
    case 0: 
      return ArrayUtil.find(this.core.refCols, paramInt) != -1;
    }
    throw Error.runtimeError(201, "Constraint");
  }
  
  boolean isUniqueWithColumns(int[] paramArrayOfInt)
  {
    switch (this.constType)
    {
    case 2: 
    case 4: 
      if (this.core.mainCols.length == paramArrayOfInt.length) {
        return ArrayUtil.haveEqualSets(this.core.mainCols, paramArrayOfInt, paramArrayOfInt.length);
      }
      break;
    }
    return false;
  }
  
  boolean isEquivalent(Table paramTable1, int[] paramArrayOfInt1, Table paramTable2, int[] paramArrayOfInt2)
  {
    switch (this.constType)
    {
    case 0: 
    case 1: 
      if ((paramTable1 != this.core.mainTable) || (paramTable2 != this.core.refTable)) {
        return false;
      }
      if ((this.core.mainCols.length == paramArrayOfInt1.length) && (this.core.refCols.length == paramArrayOfInt2.length)) {
        return (ArrayUtil.areEqualSets(this.core.mainCols, paramArrayOfInt1)) && (ArrayUtil.areEqualSets(this.core.refCols, paramArrayOfInt2));
      }
      break;
    }
    return false;
  }
  
  void updateTable(Session paramSession, Table paramTable1, Table paramTable2, int paramInt1, int paramInt2)
  {
    if (paramTable1 == this.core.mainTable)
    {
      this.core.mainTable = paramTable2;
      if (this.core.mainIndex != null)
      {
        this.core.mainIndex = this.core.mainTable.getIndex(this.core.mainIndex.getName().name);
        this.core.mainCols = ArrayUtil.toAdjustedColumnArray(this.core.mainCols, paramInt1, paramInt2);
        this.core.mainIndex.setTable(paramTable2);
      }
    }
    if (paramTable1 == this.core.refTable)
    {
      this.core.refTable = paramTable2;
      if (this.core.refIndex != null)
      {
        this.core.refIndex = this.core.refTable.getIndex(this.core.refIndex.getName().name);
        this.core.refCols = ArrayUtil.toAdjustedColumnArray(this.core.refCols, paramInt1, paramInt2);
        this.core.refIndex.setTable(paramTable2);
      }
    }
    if (this.constType == 3) {
      recompile(paramSession, paramTable2);
    }
  }
  
  void checkInsert(Session paramSession, Table paramTable, Object[] paramArrayOfObject, boolean paramBoolean)
  {
    switch (this.constType)
    {
    case 3: 
      if (!this.isNotNull) {
        checkCheckConstraint(paramSession, paramTable, paramArrayOfObject);
      }
      return;
    case 0: 
      PersistentStore localPersistentStore = this.core.mainTable.getRowStore(paramSession);
      if (ArrayUtil.hasNull(paramArrayOfObject, this.core.refCols))
      {
        if (this.core.matchType == 59) {
          return;
        }
        if (this.core.refCols.length == 1) {
          return;
        }
        if (!ArrayUtil.hasAllNull(paramArrayOfObject, this.core.refCols)) {}
      }
      else if (this.core.mainIndex.existsParent(paramSession, localPersistentStore, paramArrayOfObject, this.core.refCols))
      {
        return;
      }
      throw getException(paramArrayOfObject);
    }
  }
  
  void checkCheckConstraint(Session paramSession, Table paramTable, Object[] paramArrayOfObject)
  {
    RangeVariable.RangeIteratorBase localRangeIteratorBase = paramSession.sessionContext.getCheckIterator(this.rangeVariable);
    localRangeIteratorBase.currentData = paramArrayOfObject;
    boolean bool = Boolean.FALSE.equals(this.check.getValue(paramSession));
    localRangeIteratorBase.currentData = null;
    if (bool)
    {
      String[] arrayOfString = { this.name.name, paramTable.getName().name };
      throw Error.error(null, 157, 2, arrayOfString);
    }
  }
  
  void checkCheckConstraint(Session paramSession, Table paramTable, ColumnSchema paramColumnSchema, Object paramObject)
  {
    paramSession.sessionData.currentValue = paramObject;
    boolean bool = Boolean.FALSE.equals(this.check.getValue(paramSession));
    paramSession.sessionData.currentValue = null;
    if (bool)
    {
      String[] arrayOfString = { this.name.statementName, paramTable == null ? "" : paramTable.getName().statementName, paramColumnSchema == null ? "" : paramColumnSchema.getName().statementName };
      throw Error.error(null, 157, 3, arrayOfString);
    }
  }
  
  public HsqlException getException(Object[] paramArrayOfObject)
  {
    Object localObject1;
    Object localObject2;
    switch (this.constType)
    {
    case 3: 
      localObject1 = new String[] { this.name.statementName };
      return Error.error(null, 157, 2, (Object[])localObject1);
    case 0: 
      localObject1 = new StringBuffer();
      for (int i = 0; i < this.core.refCols.length; i++)
      {
        localObject2 = paramArrayOfObject[this.core.refCols[i]];
        ((StringBuffer)localObject1).append(this.core.refTable.getColumnTypes()[this.core.refCols[i]].convertToString(localObject2));
        ((StringBuffer)localObject1).append(',');
      }
      String[] arrayOfString = { this.name.statementName, this.core.refTable.getName().statementName, ((StringBuffer)localObject1).toString() };
      return Error.error(null, 177, 2, arrayOfString);
    case 2: 
    case 4: 
      localObject1 = new StringBuffer();
      for (int j = 0; j < this.core.mainCols.length; j++)
      {
        localObject2 = paramArrayOfObject[this.core.mainCols[j]];
        ((StringBuffer)localObject1).append(this.core.mainTable.colTypes[this.core.mainCols[j]].convertToString(localObject2));
        ((StringBuffer)localObject1).append(',');
      }
      return Error.error(null, 104, 2, new String[] { this.name.statementName, this.core.mainTable.getName().statementName, ((StringBuffer)localObject1).toString() });
    }
    throw Error.runtimeError(201, "Constraint");
  }
  
  RowIterator findFkRef(Session paramSession, Object[] paramArrayOfObject)
  {
    if ((paramArrayOfObject == null) || (ArrayUtil.hasNull(paramArrayOfObject, this.core.mainCols))) {
      return this.core.refIndex.emptyIterator();
    }
    PersistentStore localPersistentStore = this.core.refTable.getRowStore(paramSession);
    return this.core.refIndex.findFirstRow(paramSession, localPersistentStore, paramArrayOfObject, this.core.mainCols);
  }
  
  void checkReferencedRows(Session paramSession, Table paramTable)
  {
    RowIterator localRowIterator = paramTable.rowIterator(paramSession);
    for (;;)
    {
      Row localRow = localRowIterator.getNextRow();
      if (localRow == null) {
        break;
      }
      Object[] arrayOfObject = localRow.getData();
      checkInsert(paramSession, paramTable, arrayOfObject, false);
    }
  }
  
  public Expression getCheckExpression()
  {
    return this.check;
  }
  
  public OrderedHashSet getCheckColumnExpressions()
  {
    OrderedHashSet localOrderedHashSet = new OrderedHashSet();
    this.check.collectAllExpressions(localOrderedHashSet, Expression.columnExpressionSet, Expression.emptyExpressionSet);
    return localOrderedHashSet;
  }
  
  void recompile(Session paramSession, Table paramTable)
  {
    String str = this.check.getSQL();
    Scanner localScanner = new Scanner(str);
    ParserDQL localParserDQL = new ParserDQL(paramSession, localScanner);
    localParserDQL.compileContext.reset(0);
    localParserDQL.read();
    localParserDQL.isCheckOrTriggerCondition = true;
    Expression localExpression = localParserDQL.XreadBooleanValueExpression();
    this.check = localExpression;
    QuerySpecification localQuerySpecification = Expression.getCheckSelect(paramSession, paramTable, this.check);
    this.rangeVariable = localQuerySpecification.rangeVariables[0];
    this.rangeVariable.setForCheckConstraint();
  }
  
  void prepareCheckConstraint(Session paramSession, Table paramTable, boolean paramBoolean)
  {
    this.check.checkValidCheckConstraint();
    if (paramTable == null)
    {
      this.check.resolveTypes(paramSession, null);
    }
    else
    {
      QuerySpecification localQuerySpecification = Expression.getCheckSelect(paramSession, paramTable, this.check);
      Result localResult = localQuerySpecification.getResult(paramSession, 1);
      if (localResult.getNavigator().getSize() != 0)
      {
        String[] arrayOfString = { this.name.statementName, paramTable.getName().statementName };
        throw Error.error(null, 157, 2, arrayOfString);
      }
      this.rangeVariable = localQuerySpecification.rangeVariables[0];
      this.rangeVariable.setForCheckConstraint();
    }
    if ((this.check.getType() == 48) && (this.check.getLeftNode().getType() == 47) && (this.check.getLeftNode().getLeftNode().getType() == 2))
    {
      this.notNullColumnIndex = this.check.getLeftNode().getLeftNode().getColumnIndex();
      this.isNotNull = true;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.Constraint
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */