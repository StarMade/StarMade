package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.index.Index;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.navigator.RowIterator;
import org.hsqldb.persist.Logger;
import org.hsqldb.persist.PersistentStore;
import org.hsqldb.persist.PersistentStoreCollectionDatabase;
import org.hsqldb.persist.PersistentStoreCollectionSession;
import org.hsqldb.rights.Grantee;
import org.hsqldb.rights.User;
import org.hsqldb.types.Type;

public class TableWorks
{
  OrderedHashSet emptySet = new OrderedHashSet();
  private Database database;
  private Table table;
  private Session session;

  public TableWorks(Session paramSession, Table paramTable)
  {
    this.database = paramTable.database;
    this.table = paramTable;
    this.session = paramSession;
  }

  public Table getTable()
  {
    return this.table;
  }

  void checkCreateForeignKey(Constraint paramConstraint)
  {
    int i = (paramConstraint.core.updateAction == 4) || (paramConstraint.core.updateAction == 2) || (paramConstraint.core.updateAction == 0) || (paramConstraint.core.deleteAction == 4) || (paramConstraint.core.deleteAction == 2) ? 1 : 0;
    int j;
    if (i != 0)
      for (j = 0; j < paramConstraint.core.refCols.length; j++)
      {
        localObject1 = this.table.getColumn(paramConstraint.core.refCols[j]);
        if (((ColumnSchema)localObject1).isGenerated())
          throw Error.error(5524, ((ColumnSchema)localObject1).getNameString());
      }
    if ((paramConstraint.core.mainName == this.table.getName()) && (ArrayUtil.haveCommonElement(paramConstraint.core.refCols, paramConstraint.core.mainCols)))
      throw Error.error(5527);
    i = (paramConstraint.core.updateAction == 4) || (paramConstraint.core.deleteAction == 4) ? 1 : 0;
    if (i != 0)
      for (j = 0; j < paramConstraint.core.refCols.length; j++)
      {
        localObject1 = this.table.getColumn(paramConstraint.core.refCols[j]);
        localObject2 = ((ColumnSchema)localObject1).getDefaultExpression();
        if (localObject2 == null)
        {
          String str = ((ColumnSchema)localObject1).getName().statementName;
          throw Error.error(5521, str);
        }
      }
    i = (paramConstraint.core.updateAction == 2) || (paramConstraint.core.deleteAction == 2) ? 1 : 0;
    if ((i != 0) && (!this.session.isProcessingScript))
      for (j = 0; j < paramConstraint.core.refCols.length; j++)
      {
        localObject1 = this.table.getColumn(paramConstraint.core.refCols[j]);
        if (!((ColumnSchema)localObject1).isNullable())
        {
          localObject2 = ((ColumnSchema)localObject1).getName().statementName;
          throw Error.error(5520, (String)localObject2);
        }
      }
    this.database.schemaManager.checkSchemaObjectNotExists(paramConstraint.getName());
    if (this.table.getConstraint(paramConstraint.getName().name) != null)
      throw Error.error(5504, paramConstraint.getName().statementName);
    if (this.table.getFKConstraintForColumns(paramConstraint.core.mainTable, paramConstraint.core.mainCols, paramConstraint.core.refCols) != null)
      throw Error.error(5528, paramConstraint.getName().statementName);
    if (paramConstraint.core.mainTable.isTemp() != this.table.isTemp())
      throw Error.error(5524, paramConstraint.getName().statementName);
    Constraint localConstraint = paramConstraint.core.mainTable.getUniqueConstraintForColumns(paramConstraint.core.mainCols);
    if (localConstraint == null)
      throw Error.error(5529, paramConstraint.getMain().getName().statementName);
    paramConstraint.core.mainTable.checkColumnsMatch(paramConstraint.core.mainCols, this.table, paramConstraint.core.refCols);
    ArrayUtil.reorderMaps(localConstraint.getMainColumns(), paramConstraint.getMainColumns(), paramConstraint.getRefColumns());
    Object localObject1 = paramConstraint.core.mainTable.getColumnCheckList(paramConstraint.core.mainCols);
    Object localObject2 = this.session.getGrantee();
    ((Grantee)localObject2).checkReferences(paramConstraint.core.mainTable, (boolean[])localObject1);
  }

  void addForeignKey(Constraint paramConstraint)
  {
    checkModifyTable();
    checkCreateForeignKey(paramConstraint);
    Constraint localConstraint = paramConstraint.core.mainTable.getUniqueConstraintForColumns(paramConstraint.core.mainCols);
    Index localIndex1 = localConstraint.getMainIndex();
    localConstraint.checkReferencedRows(this.session, this.table);
    boolean bool = false;
    if (paramConstraint.core.mainTable.getSchemaName() == this.table.getSchemaName())
    {
      int i = this.database.schemaManager.getTableIndex(this.table);
      if ((i != -1) && (i < this.database.schemaManager.getTableIndex(paramConstraint.core.mainTable)))
        bool = true;
    }
    else
    {
      bool = true;
    }
    HsqlNameManager.HsqlName localHsqlName1 = this.database.nameManager.newAutoName("IDX", this.table.getSchemaName(), this.table.getName(), 20);
    Index localIndex2 = this.table.createIndexStructure(localHsqlName1, paramConstraint.core.refCols, null, null, false, true, bool);
    HsqlNameManager.HsqlName localHsqlName2 = this.database.nameManager.newAutoName("REF", paramConstraint.getName().name, this.table.getSchemaName(), this.table.getName(), 20);
    paramConstraint.core.uniqueName = localConstraint.getName();
    paramConstraint.core.mainName = localHsqlName2;
    paramConstraint.core.mainIndex = localIndex1;
    paramConstraint.core.refTable = this.table;
    paramConstraint.core.refName = paramConstraint.getName();
    paramConstraint.core.refIndex = localIndex2;
    paramConstraint.isForward = bool;
    Table localTable1 = this.table.moveDefinition(this.session, this.table.tableType, null, paramConstraint, localIndex2, -1, 0, this.emptySet, this.emptySet);
    moveData(this.table, localTable1, -1, 0);
    this.database.schemaManager.addSchemaObject(paramConstraint);
    setNewTableInSchema(localTable1);
    Table localTable2 = this.database.schemaManager.getTable(this.session, paramConstraint.core.mainTable.getName().name, paramConstraint.core.mainTable.getSchemaName().name);
    localTable2.addConstraint(new Constraint(localHsqlName2, paramConstraint));
    updateConstraints(localTable1, this.emptySet);
    this.database.schemaManager.recompileDependentObjects(localTable1);
    this.table = localTable1;
  }

  void checkAddColumn(ColumnSchema paramColumnSchema)
  {
    checkModifyTable();
    if ((this.table.isText()) && (!this.table.isEmpty(this.session)))
      throw Error.error(320);
    if (this.table.findColumn(paramColumnSchema.getName().name) != -1)
      throw Error.error(5504);
    if ((paramColumnSchema.isPrimaryKey()) && (this.table.hasPrimaryKey()))
      throw Error.error(5530);
    if ((paramColumnSchema.isIdentity()) && (this.table.hasIdentityColumn()))
      throw Error.error(5525);
    if ((!this.table.isEmpty(this.session)) && (!paramColumnSchema.hasDefault()) && ((!paramColumnSchema.isNullable()) || (paramColumnSchema.isPrimaryKey())) && (!paramColumnSchema.isIdentity()))
      throw Error.error(5531);
  }

  void addColumn(ColumnSchema paramColumnSchema, int paramInt, HsqlArrayList paramHsqlArrayList)
  {
    Index localIndex = null;
    Constraint localConstraint1 = null;
    int i = 0;
    int j = 0;
    int k = 0;
    checkAddColumn(paramColumnSchema);
    Constraint localConstraint2 = (Constraint)paramHsqlArrayList.get(0);
    if (localConstraint2.getConstraintType() == 4)
    {
      if (paramColumnSchema.getDataType().isLobType())
        throw Error.error(5534);
      localConstraint2.core.mainCols = new int[] { paramInt };
      this.database.schemaManager.checkSchemaObjectNotExists(localConstraint2.getName());
      if (this.table.hasPrimaryKey())
        throw Error.error(5530);
      j = 1;
    }
    else
    {
      localConstraint2 = null;
    }
    Table localTable = this.table.moveDefinition(this.session, this.table.tableType, paramColumnSchema, localConstraint2, null, paramInt, 1, this.emptySet, this.emptySet);
    for (int m = 1; m < paramHsqlArrayList.size(); m++)
    {
      localConstraint2 = (Constraint)paramHsqlArrayList.get(m);
      switch (localConstraint2.constType)
      {
      case 2:
        if (j != 0)
          throw Error.error(5522);
        if (paramColumnSchema.getDataType().isLobType())
          throw Error.error(5534);
        j = 1;
        localConstraint2.core.mainCols = new int[] { paramInt };
        this.database.schemaManager.checkSchemaObjectNotExists(localConstraint2.getName());
        HsqlNameManager.HsqlName localHsqlName1 = this.database.nameManager.newAutoName("IDX", localConstraint2.getName().name, this.table.getSchemaName(), this.table.getName(), 20);
        localIndex = localTable.createAndAddIndexStructure(localHsqlName1, localConstraint2.getMainColumns(), null, null, true, true, false);
        localConstraint2.core.mainTable = localTable;
        localConstraint2.core.mainIndex = localIndex;
        localTable.addConstraint(localConstraint2);
        break;
      case 0:
        if (i != 0)
          throw Error.error(5528);
        i = 1;
        localConstraint2.core.refCols = new int[] { paramInt };
        localConstraint2.core.mainTable = this.database.schemaManager.getUserTable(this.session, localConstraint2.getMainTableName());
        localConstraint2.core.refTable = localTable;
        localConstraint2.core.refName = localConstraint2.getName();
        int n = this.table == localConstraint2.core.mainTable ? 1 : 0;
        if (n != 0)
          localConstraint2.core.mainTable = localTable;
        localConstraint2.setColumnsIndexes(localTable);
        checkCreateForeignKey(localConstraint2);
        Constraint localConstraint3 = localConstraint2.core.mainTable.getUniqueConstraintForColumns(localConstraint2.core.mainCols);
        boolean bool = localConstraint2.core.mainTable.getSchemaName() != this.table.getSchemaName();
        int i1 = this.database.schemaManager.getTableIndex(this.table);
        if ((n == 0) && (i1 < this.database.schemaManager.getTableIndex(localConstraint2.core.mainTable)))
          bool = true;
        HsqlNameManager.HsqlName localHsqlName2 = this.database.nameManager.newAutoName("IDX", localConstraint2.getName().name, this.table.getSchemaName(), this.table.getName(), 20);
        localIndex = localTable.createAndAddIndexStructure(localHsqlName2, localConstraint2.getRefColumns(), null, null, false, true, bool);
        localConstraint2.core.uniqueName = localConstraint3.getName();
        localConstraint2.core.mainName = this.database.nameManager.newAutoName("REF", localConstraint2.core.refName.name, this.table.getSchemaName(), this.table.getName(), 20);
        localConstraint2.core.mainIndex = localConstraint3.getMainIndex();
        localConstraint2.core.refIndex = localIndex;
        localConstraint2.isForward = bool;
        localTable.addConstraint(localConstraint2);
        localConstraint1 = new Constraint(localConstraint2.core.mainName, localConstraint2);
        break;
      case 3:
        if (k != 0)
          throw Error.error(5528);
        k = 1;
        localConstraint2.prepareCheckConstraint(this.session, localTable, false);
        localTable.addConstraint(localConstraint2);
        if (localConstraint2.isNotNull())
        {
          paramColumnSchema.setNullable(false);
          localTable.setColumnTypeVars(paramInt);
          if ((!this.table.isEmpty(this.session)) && (!paramColumnSchema.hasDefault()))
            throw Error.error(5531);
        }
        break;
      case 1:
      }
    }
    paramColumnSchema.compile(this.session, localTable);
    moveData(this.table, localTable, paramInt, 1);
    if (localConstraint1 != null)
      localConstraint1.getMain().addConstraint(localConstraint1);
    registerConstraintNames(paramHsqlArrayList);
    setNewTableInSchema(localTable);
    updateConstraints(localTable, this.emptySet);
    this.database.schemaManager.addSchemaObject(paramColumnSchema);
    this.database.schemaManager.recompileDependentObjects(localTable);
    localTable.compile(this.session, null);
    this.table = localTable;
  }

  void updateConstraints(OrderedHashSet paramOrderedHashSet1, OrderedHashSet paramOrderedHashSet2)
  {
    for (int i = 0; i < paramOrderedHashSet1.size(); i++)
    {
      Table localTable = (Table)paramOrderedHashSet1.get(i);
      updateConstraints(localTable, paramOrderedHashSet2);
    }
  }

  void updateConstraints(Table paramTable, OrderedHashSet paramOrderedHashSet)
  {
    for (int i = paramTable.constraintList.length - 1; i >= 0; i--)
    {
      Constraint localConstraint1 = paramTable.constraintList[i];
      if (paramOrderedHashSet.contains(localConstraint1.getName()))
      {
        paramTable.removeConstraint(i);
      }
      else
      {
        Table localTable1;
        Table localTable2;
        Constraint localConstraint2;
        if (localConstraint1.getConstraintType() == 0)
        {
          localTable1 = this.database.schemaManager.getUserTable(this.session, localConstraint1.core.refTable.getName());
          localConstraint1.core.refTable = localTable1;
          localTable2 = this.database.schemaManager.getUserTable(this.session, localConstraint1.core.mainTable.getName());
          localConstraint2 = localTable2.getConstraint(localConstraint1.getMainName().name);
          localConstraint2.core = localConstraint1.core;
        }
        else if (localConstraint1.getConstraintType() == 1)
        {
          localTable1 = this.database.schemaManager.getUserTable(this.session, localConstraint1.core.mainTable.getName());
          localConstraint1.core.mainTable = localTable1;
          localTable2 = this.database.schemaManager.getUserTable(this.session, localConstraint1.core.refTable.getName());
          localConstraint2 = localTable2.getConstraint(localConstraint1.getRefName().name);
          localConstraint2.core = localConstraint1.core;
        }
      }
    }
  }

  OrderedHashSet makeNewTables(OrderedHashSet paramOrderedHashSet1, OrderedHashSet paramOrderedHashSet2, OrderedHashSet paramOrderedHashSet3)
  {
    OrderedHashSet localOrderedHashSet = new OrderedHashSet();
    for (int i = 0; i < paramOrderedHashSet1.size(); i++)
    {
      Table localTable = (Table)paramOrderedHashSet1.get(i);
      TableWorks localTableWorks = new TableWorks(this.session, localTable);
      localTableWorks.makeNewTable(paramOrderedHashSet2, paramOrderedHashSet3);
      localOrderedHashSet.add(localTableWorks.getTable());
    }
    return localOrderedHashSet;
  }

  void makeNewTable(OrderedHashSet paramOrderedHashSet1, OrderedHashSet paramOrderedHashSet2)
  {
    Table localTable = this.table.moveDefinition(this.session, this.table.tableType, null, null, null, -1, 0, paramOrderedHashSet1, paramOrderedHashSet2);
    if (localTable.indexList.length == this.table.indexList.length)
    {
      this.database.persistentStoreCollection.releaseStore(localTable);
      return;
    }
    moveData(this.table, localTable, -1, 0);
    this.table = localTable;
  }

  void alterIndex(Index paramIndex, int[] paramArrayOfInt)
  {
    Index localIndex = this.database.logger.newIndex(this.table, paramIndex, paramArrayOfInt);
    int i = paramIndex.getPosition();
    PersistentStore localPersistentStore = this.database.persistentStoreCollection.getStore(this.table);
    Index[] arrayOfIndex = localPersistentStore.getAccessorKeys();
    localIndex.setPosition(i);
    this.table.getIndexList()[i] = localIndex;
    this.table.setBestRowIdentifiers();
    arrayOfIndex[i] = localIndex;
    localPersistentStore.reindex(this.session, localIndex);
  }

  Index addIndex(int[] paramArrayOfInt, HsqlNameManager.HsqlName paramHsqlName, boolean paramBoolean)
  {
    checkModifyTable();
    Index localIndex;
    if ((this.table.isEmpty(this.session)) || (this.table.isIndexingMutable()))
    {
      localIndex = this.table.createIndex(this.session, paramHsqlName, paramArrayOfInt, null, null, paramBoolean, false, false);
    }
    else
    {
      localIndex = this.table.createIndexStructure(paramHsqlName, paramArrayOfInt, null, null, paramBoolean, false, false);
      Table localTable = this.table.moveDefinition(this.session, this.table.tableType, null, null, localIndex, -1, 0, this.emptySet, this.emptySet);
      moveData(this.table, localTable, -1, 0);
      this.table = localTable;
      setNewTableInSchema(this.table);
      updateConstraints(this.table, this.emptySet);
    }
    this.database.schemaManager.addSchemaObject(localIndex);
    this.database.schemaManager.recompileDependentObjects(this.table);
    return localIndex;
  }

  void addPrimaryKey(Constraint paramConstraint)
  {
    checkModifyTable();
    if (this.table.hasPrimaryKey())
      throw Error.error(5532);
    this.database.schemaManager.checkSchemaObjectNotExists(paramConstraint.getName());
    Table localTable = this.table.moveDefinition(this.session, this.table.tableType, null, paramConstraint, null, -1, 0, this.emptySet, this.emptySet);
    moveData(this.table, localTable, -1, 0);
    this.table = localTable;
    this.database.schemaManager.addSchemaObject(paramConstraint);
    setNewTableInSchema(this.table);
    updateConstraints(this.table, this.emptySet);
    this.database.schemaManager.recompileDependentObjects(this.table);
  }

  void addUniqueConstraint(int[] paramArrayOfInt, HsqlNameManager.HsqlName paramHsqlName)
  {
    checkModifyTable();
    this.database.schemaManager.checkSchemaObjectNotExists(paramHsqlName);
    if (this.table.getUniqueConstraintForColumns(paramArrayOfInt) != null)
      throw Error.error(5522);
    HsqlNameManager.HsqlName localHsqlName = this.database.nameManager.newAutoName("IDX", paramHsqlName.name, this.table.getSchemaName(), this.table.getName(), 20);
    Index localIndex = this.table.createIndexStructure(localHsqlName, paramArrayOfInt, null, null, true, true, false);
    Constraint localConstraint = new Constraint(paramHsqlName, this.table, localIndex, 2);
    Table localTable = this.table.moveDefinition(this.session, this.table.tableType, null, localConstraint, localIndex, -1, 0, this.emptySet, this.emptySet);
    moveData(this.table, localTable, -1, 0);
    this.table = localTable;
    this.database.schemaManager.addSchemaObject(localConstraint);
    setNewTableInSchema(this.table);
    updateConstraints(this.table, this.emptySet);
    this.database.schemaManager.recompileDependentObjects(this.table);
  }

  void addUniqueConstraint(Constraint paramConstraint)
  {
    checkModifyTable();
    this.database.schemaManager.checkSchemaObjectNotExists(paramConstraint.getName());
    if (this.table.getUniqueConstraintForColumns(paramConstraint.getMainColumns()) != null)
      throw Error.error(5522);
    Table localTable = this.table.moveDefinition(this.session, this.table.tableType, null, paramConstraint, paramConstraint.getMainIndex(), -1, 0, this.emptySet, this.emptySet);
    moveData(this.table, localTable, -1, 0);
    this.table = localTable;
    this.database.schemaManager.addSchemaObject(paramConstraint);
    setNewTableInSchema(this.table);
    updateConstraints(this.table, this.emptySet);
    this.database.schemaManager.recompileDependentObjects(this.table);
  }

  void addCheckConstraint(Constraint paramConstraint)
  {
    checkModifyTable();
    this.database.schemaManager.checkSchemaObjectNotExists(paramConstraint.getName());
    paramConstraint.prepareCheckConstraint(this.session, this.table, true);
    this.table.addConstraint(paramConstraint);
    if (paramConstraint.isNotNull())
    {
      ColumnSchema localColumnSchema = this.table.getColumn(paramConstraint.notNullColumnIndex);
      localColumnSchema.setNullable(false);
      this.table.setColumnTypeVars(paramConstraint.notNullColumnIndex);
    }
    this.database.schemaManager.addSchemaObject(paramConstraint);
  }

  void dropIndex(String paramString)
  {
    checkModifyTable();
    Index localIndex = this.table.getIndex(paramString);
    if (this.table.isIndexingMutable())
    {
      this.table.dropIndex(localIndex.getPosition());
    }
    else
    {
      OrderedHashSet localOrderedHashSet = new OrderedHashSet();
      localOrderedHashSet.add(this.table.getIndex(paramString).getName());
      Table localTable = this.table.moveDefinition(this.session, this.table.tableType, null, null, null, -1, 0, this.emptySet, localOrderedHashSet);
      moveData(this.table, localTable, -1, 0);
      setNewTableInSchema(localTable);
      updateConstraints(localTable, this.emptySet);
      this.table = localTable;
    }
    if (!localIndex.isConstraint())
      this.database.schemaManager.removeSchemaObject(localIndex.getName());
    this.database.schemaManager.recompileDependentObjects(this.table);
  }

  void dropColumn(int paramInt, boolean paramBoolean)
  {
    OrderedHashSet localOrderedHashSet1 = new OrderedHashSet();
    OrderedHashSet localOrderedHashSet2 = this.table.getDependentConstraints(paramInt);
    OrderedHashSet localOrderedHashSet3 = this.table.getContainingConstraints(paramInt);
    OrderedHashSet localOrderedHashSet4 = this.table.getContainingIndexNames(paramInt);
    ColumnSchema localColumnSchema = this.table.getColumn(paramInt);
    HsqlNameManager.HsqlName localHsqlName1 = localColumnSchema.getName();
    OrderedHashSet localOrderedHashSet5 = this.database.schemaManager.getReferencesTo(this.table.getName(), localHsqlName1);
    checkModifyTable();
    if (!paramBoolean)
    {
      HsqlNameManager.HsqlName localHsqlName2;
      if (!localOrderedHashSet3.isEmpty())
      {
        Constraint localConstraint1 = (Constraint)localOrderedHashSet3.get(0);
        localHsqlName2 = localConstraint1.getName();
        throw Error.error(5536, localHsqlName2.getSchemaQualifiedStatementName());
      }
      if (!localOrderedHashSet5.isEmpty())
        label221: for (int i = 0; i < localOrderedHashSet5.size(); i++)
        {
          localHsqlName2 = (HsqlNameManager.HsqlName)localOrderedHashSet5.get(i);
          if (localHsqlName2 != localHsqlName1)
          {
            for (int k = 0; k < localOrderedHashSet2.size(); k++)
            {
              Constraint localConstraint3 = (Constraint)localOrderedHashSet2.get(k);
              if (localConstraint3.getName() == localHsqlName2)
                break label221;
            }
            throw Error.error(5536, localHsqlName2.getSchemaQualifiedStatementName());
          }
        }
    }
    localOrderedHashSet2.addAll(localOrderedHashSet3);
    localOrderedHashSet3.clear();
    OrderedHashSet localOrderedHashSet6 = new OrderedHashSet();
    for (int j = 0; j < localOrderedHashSet2.size(); j++)
    {
      Constraint localConstraint2 = (Constraint)localOrderedHashSet2.get(j);
      if (localConstraint2.constType == 0)
      {
        localOrderedHashSet6.add(localConstraint2.getMain());
        localOrderedHashSet1.add(localConstraint2.getMainName());
        localOrderedHashSet1.add(localConstraint2.getRefName());
        localOrderedHashSet4.add(localConstraint2.getRefIndex().getName());
      }
      if (localConstraint2.constType == 1)
      {
        localOrderedHashSet6.add(localConstraint2.getRef());
        localOrderedHashSet1.add(localConstraint2.getMainName());
        localOrderedHashSet1.add(localConstraint2.getRefName());
        localOrderedHashSet4.add(localConstraint2.getRefIndex().getName());
      }
      localOrderedHashSet1.add(localConstraint2.getName());
    }
    localOrderedHashSet6 = makeNewTables(localOrderedHashSet6, localOrderedHashSet1, localOrderedHashSet4);
    Table localTable = this.table.moveDefinition(this.session, this.table.tableType, null, null, null, paramInt, -1, localOrderedHashSet1, localOrderedHashSet4);
    moveData(this.table, localTable, paramInt, -1);
    this.database.schemaManager.removeSchemaObjects(localOrderedHashSet5);
    this.database.schemaManager.removeSchemaObjects(localOrderedHashSet1);
    this.database.schemaManager.removeSchemaObject(localHsqlName1);
    setNewTableInSchema(localTable);
    setNewTablesInSchema(localOrderedHashSet6);
    updateConstraints(localTable, this.emptySet);
    updateConstraints(localOrderedHashSet6, localOrderedHashSet1);
    this.database.schemaManager.recompileDependentObjects(localOrderedHashSet6);
    this.database.schemaManager.recompileDependentObjects(localTable);
    localTable.compile(this.session, null);
    this.table = localTable;
  }

  void registerConstraintNames(HsqlArrayList paramHsqlArrayList)
  {
    for (int i = 0; i < paramHsqlArrayList.size(); i++)
    {
      Constraint localConstraint = (Constraint)paramHsqlArrayList.get(i);
      switch (localConstraint.constType)
      {
      case 2:
      case 3:
      case 4:
        this.database.schemaManager.addSchemaObject(localConstraint);
      }
    }
  }

  void dropConstraint(String paramString, boolean paramBoolean)
  {
    Constraint localConstraint = this.table.getConstraint(paramString);
    if (localConstraint == null)
      throw Error.error(5501, paramString);
    Object localObject1;
    Object localObject2;
    Object localObject3;
    OrderedHashSet localOrderedHashSet;
    Table localTable1;
    switch (localConstraint.getConstraintType())
    {
    case 1:
      throw Error.error(4002);
    case 2:
    case 4:
      checkModifyTable();
      localObject1 = this.table.getDependentConstraints(localConstraint);
      if ((!paramBoolean) && (!((OrderedHashSet)localObject1).isEmpty()))
      {
        localObject2 = (Constraint)((OrderedHashSet)localObject1).get(0);
        throw Error.error(5533, ((Constraint)localObject2).getName().getSchemaQualifiedStatementName());
      }
      localObject2 = new OrderedHashSet();
      localObject3 = new OrderedHashSet();
      localOrderedHashSet = new OrderedHashSet();
      Object localObject4;
      for (int i = 0; i < ((OrderedHashSet)localObject1).size(); i++)
      {
        localObject4 = (Constraint)((OrderedHashSet)localObject1).get(i);
        Table localTable2 = ((Constraint)localObject4).getMain();
        if (localTable2 != this.table)
          ((OrderedHashSet)localObject2).add(localTable2);
        localTable2 = ((Constraint)localObject4).getRef();
        if (localTable2 != this.table)
          ((OrderedHashSet)localObject2).add(localTable2);
        ((OrderedHashSet)localObject3).add(((Constraint)localObject4).getMainName());
        ((OrderedHashSet)localObject3).add(((Constraint)localObject4).getRefName());
        localOrderedHashSet.add(((Constraint)localObject4).getRefIndex().getName());
      }
      ((OrderedHashSet)localObject3).add(localConstraint.getName());
      if (localConstraint.getConstraintType() == 2)
        localOrderedHashSet.add(localConstraint.getMainIndex().getName());
      localTable1 = this.table.moveDefinition(this.session, this.table.tableType, null, null, null, -1, 0, (OrderedHashSet)localObject3, localOrderedHashSet);
      moveData(this.table, localTable1, -1, 0);
      localObject2 = makeNewTables((OrderedHashSet)localObject2, (OrderedHashSet)localObject3, localOrderedHashSet);
      if (localConstraint.getConstraintType() == 4)
      {
        localObject4 = localConstraint.getMainColumns();
        for (int j = 0; j < localObject4.length; j++)
        {
          localTable1.getColumn(localObject4[j]).setPrimaryKey(false);
          localTable1.setColumnTypeVars(localObject4[j]);
        }
      }
      this.database.schemaManager.removeSchemaObjects((OrderedHashSet)localObject3);
      setNewTableInSchema(localTable1);
      setNewTablesInSchema((OrderedHashSet)localObject2);
      updateConstraints(localTable1, this.emptySet);
      updateConstraints((OrderedHashSet)localObject2, (OrderedHashSet)localObject3);
      this.database.schemaManager.recompileDependentObjects((OrderedHashSet)localObject2);
      this.database.schemaManager.recompileDependentObjects(localTable1);
      this.table = localTable1;
      break;
    case 0:
      checkModifyTable();
      localObject1 = new OrderedHashSet();
      localObject2 = localConstraint.getMain();
      localObject3 = localConstraint.getMainName();
      ((OrderedHashSet)localObject1).add(localObject3);
      ((OrderedHashSet)localObject1).add(localConstraint.getRefName());
      localOrderedHashSet = new OrderedHashSet();
      localOrderedHashSet.add(localConstraint.getRefIndex().getName());
      localTable1 = this.table.moveDefinition(this.session, this.table.tableType, null, null, null, -1, 0, (OrderedHashSet)localObject1, localOrderedHashSet);
      moveData(this.table, localTable1, -1, 0);
      this.database.schemaManager.removeSchemaObject(localConstraint.getName());
      setNewTableInSchema(localTable1);
      ((Table)localObject2).removeConstraint(((HsqlNameManager.HsqlName)localObject3).name);
      updateConstraints(localTable1, this.emptySet);
      this.database.schemaManager.recompileDependentObjects(this.table);
      this.table = localTable1;
      break;
    case 3:
      this.database.schemaManager.removeSchemaObject(localConstraint.getName());
      if (localConstraint.isNotNull())
      {
        localObject1 = this.table.getColumn(localConstraint.notNullColumnIndex);
        ((ColumnSchema)localObject1).setNullable(false);
        this.table.setColumnTypeVars(localConstraint.notNullColumnIndex);
      }
      break;
    }
  }

  void retypeColumn(ColumnSchema paramColumnSchema1, ColumnSchema paramColumnSchema2)
  {
    Type localType1 = paramColumnSchema1.getDataType();
    Type localType2 = paramColumnSchema2.getDataType();
    checkModifyTable();
    if ((localType1.equals(localType2)) && (paramColumnSchema1.getIdentitySequence() == paramColumnSchema2.getIdentitySequence()))
      return;
    if ((!this.table.isEmpty(this.session)) && (localType1.typeCode != localType2.typeCode))
    {
      boolean bool = paramColumnSchema2.getDataType().canConvertFrom(paramColumnSchema1.getDataType());
      switch (localType1.typeCode)
      {
      case 1111:
      case 2000:
        bool = false;
      }
      if (!bool)
        throw Error.error(5561);
    }
    int i = this.table.getColumnIndex(paramColumnSchema1.getName().name);
    int j = localType2.canMoveFrom(localType1);
    if ((j == 0) && (paramColumnSchema2.isIdentity()) && (!paramColumnSchema1.isIdentity()) && (paramColumnSchema1.isNullable()) && (!paramColumnSchema1.isPrimaryKey()))
      j = 1;
    if (j == 1)
    {
      checkConvertColDataType(paramColumnSchema1, paramColumnSchema2);
      j = 0;
    }
    if (j == 0)
    {
      paramColumnSchema1.setType(paramColumnSchema2);
      paramColumnSchema1.setDefaultExpression(paramColumnSchema2.getDefaultExpression());
      paramColumnSchema1.setIdentity(paramColumnSchema2.getIdentitySequence());
      this.table.setColumnTypeVars(i);
      this.table.resetDefaultsFlag();
      return;
    }
    this.database.schemaManager.checkColumnIsReferenced(this.table.getName(), this.table.getColumn(i).getName());
    this.table.checkColumnInCheckConstraint(i);
    this.table.checkColumnInFKConstraint(i);
    checkConvertColDataType(paramColumnSchema1, paramColumnSchema2);
    retypeColumn(paramColumnSchema2, i);
  }

  void checkConvertColDataType(ColumnSchema paramColumnSchema1, ColumnSchema paramColumnSchema2)
  {
    int i = this.table.getColumnIndex(paramColumnSchema1.getName().name);
    RowIterator localRowIterator = this.table.rowIterator(this.session);
    while (localRowIterator.hasNext())
    {
      Row localRow = localRowIterator.getNextRow();
      Object localObject = localRow.getData()[i];
      if ((!paramColumnSchema2.isNullable()) && (localObject == null))
        throw Error.error(10);
      paramColumnSchema2.getDataType().convertToType(this.session, localObject, paramColumnSchema1.getDataType());
    }
  }

  private void retypeColumn(ColumnSchema paramColumnSchema, int paramInt)
  {
    Table localTable = this.table.moveDefinition(this.session, this.table.tableType, paramColumnSchema, null, null, paramInt, 0, this.emptySet, this.emptySet);
    moveData(this.table, localTable, paramInt, 0);
    setNewTableInSchema(localTable);
    updateConstraints(localTable, this.emptySet);
    this.database.schemaManager.recompileDependentObjects(this.table);
    this.table = localTable;
  }

  void setColNullability(ColumnSchema paramColumnSchema, boolean paramBoolean)
  {
    Constraint localConstraint = null;
    int i = this.table.getColumnIndex(paramColumnSchema.getName().name);
    if (paramColumnSchema.isNullable() == paramBoolean)
      return;
    if (paramBoolean)
    {
      if (paramColumnSchema.isPrimaryKey())
        throw Error.error(5526);
      this.table.checkColumnInFKConstraint(i, 2);
      removeColumnNotNullConstraints(i);
    }
    else
    {
      HsqlNameManager.HsqlName localHsqlName = this.database.nameManager.newAutoName("CT", this.table.getSchemaName(), this.table.getName(), 5);
      localConstraint = new Constraint(localHsqlName, null, 3);
      localConstraint.check = new ExpressionLogical(paramColumnSchema);
      localConstraint.prepareCheckConstraint(this.session, this.table, true);
      paramColumnSchema.setNullable(false);
      this.table.addConstraint(localConstraint);
      this.table.setColumnTypeVars(i);
      this.database.schemaManager.addSchemaObject(localConstraint);
    }
  }

  void setColDefaultExpression(int paramInt, Expression paramExpression)
  {
    if (paramExpression == null)
      this.table.checkColumnInFKConstraint(paramInt, 4);
    ColumnSchema localColumnSchema = this.table.getColumn(paramInt);
    localColumnSchema.setDefaultExpression(paramExpression);
    this.table.setColumnTypeVars(paramInt);
  }

  public boolean setTableType(Session paramSession, int paramInt)
  {
    int i = this.table.getTableType();
    if (i == paramInt)
      return false;
    switch (paramInt)
    {
    case 5:
      break;
    case 4:
      break;
    default:
      return false;
    }
    Table localTable;
    try
    {
      localTable = this.table.moveDefinition(paramSession, paramInt, null, null, null, -1, 0, this.emptySet, this.emptySet);
      moveData(this.table, localTable, -1, 0);
    }
    catch (HsqlException localHsqlException)
    {
      return false;
    }
    setNewTableInSchema(localTable);
    updateConstraints(localTable, this.emptySet);
    this.table = localTable;
    this.database.schemaManager.recompileDependentObjects(this.table);
    return true;
  }

  void setNewTablesInSchema(OrderedHashSet paramOrderedHashSet)
  {
    for (int i = 0; i < paramOrderedHashSet.size(); i++)
    {
      Table localTable = (Table)paramOrderedHashSet.get(i);
      setNewTableInSchema(localTable);
    }
  }

  void setNewTableInSchema(Table paramTable)
  {
    int i = this.database.schemaManager.getTableIndex(paramTable);
    if (i != -1)
      this.database.schemaManager.setTable(i, paramTable);
  }

  void removeColumnNotNullConstraints(int paramInt)
  {
    for (int i = this.table.constraintList.length - 1; i >= 0; i--)
    {
      Constraint localConstraint = this.table.constraintList[i];
      if ((localConstraint.isNotNull()) && (localConstraint.notNullColumnIndex == paramInt))
        this.database.schemaManager.removeSchemaObject(localConstraint.getName());
    }
    ColumnSchema localColumnSchema = this.table.getColumn(paramInt);
    localColumnSchema.setNullable(true);
    this.table.setColumnTypeVars(paramInt);
  }

  private void checkModifyTable()
  {
    if (this.session.getUser().isSystem())
      return;
    if (this.session.isProcessingScript)
      return;
    if ((this.database.isFilesReadOnly()) || (this.table.isReadOnly()))
      throw Error.error(456);
    if ((this.table.isText()) && (this.table.isConnected()))
      throw Error.error(320);
  }

  void moveData(Table paramTable1, Table paramTable2, int paramInt1, int paramInt2)
  {
    int i = paramTable1.getTableType();
    Object localObject;
    if (i == 3)
    {
      localObject = this.database.sessionManager.getAllSessions();
      for (int j = 0; j < localObject.length; j++)
        localObject[j].sessionData.persistentStoreCollection.moveData(paramTable1, paramTable2, paramInt1, paramInt2);
    }
    else
    {
      localObject = this.database.persistentStoreCollection.getStore(paramTable1);
      PersistentStore localPersistentStore = this.database.persistentStoreCollection.getStore(paramTable2);
      try
      {
        localPersistentStore.moveData(this.session, (PersistentStore)localObject, paramInt1, paramInt2);
      }
      catch (HsqlException localHsqlException)
      {
        localPersistentStore.release();
        this.database.persistentStoreCollection.setStore(paramTable2, null);
        throw localHsqlException;
      }
      this.database.persistentStoreCollection.releaseStore(paramTable1);
    }
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.TableWorks
 * JD-Core Version:    0.6.2
 */