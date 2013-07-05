package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.HashMappedList;
import org.hsqldb.lib.OrderedHashSet;

public class View extends TableDerived
{
  private String statement;
  private HsqlNameManager.HsqlName[] columnNames;
  private OrderedHashSet schemaObjectNames;
  private int checkOption;
  private Table baseTable;
  boolean isTriggerInsertable;
  boolean isTriggerUpdatable;
  boolean isTriggerDeletable;

  View(Database paramDatabase, HsqlNameManager.HsqlName paramHsqlName, HsqlNameManager.HsqlName[] paramArrayOfHsqlName, int paramInt)
  {
    super(paramDatabase, paramHsqlName, 8);
    this.columnNames = paramArrayOfHsqlName;
    this.checkOption = paramInt;
  }

  public int getType()
  {
    return 4;
  }

  public OrderedHashSet getReferences()
  {
    return this.schemaObjectNames;
  }

  public OrderedHashSet getComponents()
  {
    return null;
  }

  public void compile(Session paramSession, SchemaObject paramSchemaObject)
  {
    ParserDQL localParserDQL = new ParserDQL(paramSession, new Scanner(this.statement));
    localParserDQL.read();
    TableDerived localTableDerived = localParserDQL.XreadViewSubqueryTable(this, true);
    this.queryExpression = localTableDerived.queryExpression;
    if (getColumnCount() == 0)
    {
      if (this.columnNames == null)
        this.columnNames = localTableDerived.queryExpression.getResultColumnNames();
      if (this.columnNames.length != localTableDerived.queryExpression.getColumnCount())
        throw Error.error(5593, getName().statementName);
      TableUtil.setColumnsInSchemaTable(this, this.columnNames, this.queryExpression.getColumnTypes());
    }
    this.schemaObjectNames = localParserDQL.compileContext.getSchemaObjectNames();
    this.canRecompile = true;
    this.baseTable = this.queryExpression.getBaseTable();
    if (this.baseTable == null)
      return;
    switch (this.checkOption)
    {
    case 0:
    case 1:
    case 2:
      break;
    default:
      throw Error.runtimeError(201, "View");
    }
  }

  public String getSQL()
  {
    StringBuffer localStringBuffer = new StringBuffer(128);
    localStringBuffer.append("CREATE").append(' ').append("VIEW");
    localStringBuffer.append(' ');
    localStringBuffer.append(getName().getSchemaQualifiedStatementName()).append(' ');
    localStringBuffer.append('(');
    int i = getColumnCount();
    for (int j = 0; j < i; j++)
    {
      localStringBuffer.append(getColumn(j).getName().statementName);
      if (j < i - 1)
        localStringBuffer.append(',');
    }
    localStringBuffer.append(')').append(' ').append("AS").append(' ');
    localStringBuffer.append(getStatement());
    return localStringBuffer.toString();
  }

  public int[] getUpdatableColumns()
  {
    return this.queryExpression.getBaseTableColumnMap();
  }

  public boolean isTriggerInsertable()
  {
    return this.isTriggerInsertable;
  }

  public boolean isTriggerUpdatable()
  {
    return this.isTriggerUpdatable;
  }

  public boolean isTriggerDeletable()
  {
    return this.isTriggerDeletable;
  }

  public boolean isInsertable()
  {
    return this.isTriggerInsertable ? false : super.isInsertable();
  }

  public boolean isUpdatable()
  {
    return this.isTriggerUpdatable ? false : super.isUpdatable();
  }

  void addTrigger(TriggerDef paramTriggerDef, HsqlNameManager.HsqlName paramHsqlName)
  {
    switch (paramTriggerDef.operationType)
    {
    case 50:
      if (this.isTriggerInsertable)
        throw Error.error(5538);
      this.isTriggerInsertable = true;
      break;
    case 19:
      if (this.isTriggerDeletable)
        throw Error.error(5538);
      this.isTriggerDeletable = true;
      break;
    case 82:
      if (this.isTriggerUpdatable)
        throw Error.error(5538);
      this.isTriggerUpdatable = true;
      break;
    default:
      throw Error.runtimeError(201, "View");
    }
    super.addTrigger(paramTriggerDef, paramHsqlName);
  }

  void removeTrigger(TriggerDef paramTriggerDef)
  {
    switch (paramTriggerDef.operationType)
    {
    case 50:
      this.isTriggerInsertable = false;
      break;
    case 19:
      this.isTriggerDeletable = false;
      break;
    case 82:
      this.isTriggerUpdatable = false;
      break;
    default:
      throw Error.runtimeError(201, "View");
    }
    super.removeTrigger(paramTriggerDef);
  }

  public void setDataReadOnly(boolean paramBoolean)
  {
    throw Error.error(4000);
  }

  public int getCheckOption()
  {
    return this.checkOption;
  }

  public String getStatement()
  {
    return this.statement;
  }

  public void setStatement(String paramString)
  {
    this.statement = paramString;
  }

  public TableDerived newDerivedTable(Session paramSession)
  {
    TableDerived localTableDerived;
    if (isRecompiled())
    {
      ParserDQL localParserDQL = new ParserDQL(paramSession, new Scanner(this.statement));
      localParserDQL.reset(this.statement, paramSession.parser.compileContext.getRangeVarCount());
      localParserDQL.read();
      localTableDerived = localParserDQL.XreadViewSubqueryTable(this, false);
      paramSession.parser.compileContext.setNextRangeVarIndex(localParserDQL.compileContext.getRangeVarCount());
    }
    else
    {
      localTableDerived = new TableDerived(this.database, this.tableName, 8, this.queryExpression, null, 0, 1);
      localTableDerived.columnList = this.columnList;
      localTableDerived.columnCount = this.columnList.size();
      localTableDerived.createPrimaryKey();
      localTableDerived.triggerList = this.triggerList;
      localTableDerived.triggerLists = this.triggerLists;
    }
    return localTableDerived;
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.View
 * JD-Core Version:    0.6.2
 */