package org.hsqldb;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.hsqldb.dbinfo.DatabaseInformation;
import org.hsqldb.error.Error;
import org.hsqldb.index.Index;
import org.hsqldb.lib.Collection;
import org.hsqldb.lib.HashMap;
import org.hsqldb.lib.HashMappedList;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.lib.Iterator;
import org.hsqldb.lib.MultiValueHashMap;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.lib.Set;
import org.hsqldb.lib.StringConverter;
import org.hsqldb.lib.WrapperIterator;
import org.hsqldb.navigator.RowIterator;
import org.hsqldb.persist.PersistentStoreCollectionDatabase;
import org.hsqldb.rights.Grantee;
import org.hsqldb.rights.GranteeManager;
import org.hsqldb.rights.UserManager;
import org.hsqldb.types.Collation;
import org.hsqldb.types.Type;
import org.hsqldb.types.UserTypeModifier;

public class SchemaManager
{
  Database database;
  HsqlNameManager.HsqlName defaultSchemaHsqlName;
  HashMappedList schemaMap = new HashMappedList();
  MultiValueHashMap referenceMap = new MultiValueHashMap();
  int defaultTableType = 4;
  long schemaChangeTimestamp;
  HsqlNameManager.HsqlName[] catalogNameArray;
  ReadWriteLock lock = new ReentrantReadWriteLock();
  Lock readLock = this.lock.readLock();
  Lock writeLock = this.lock.writeLock();
  Table dualTable;
  public Table dataChangeTable;
  long[][] tempIndexRoots;

  public SchemaManager(Database paramDatabase)
  {
    this.database = paramDatabase;
    this.defaultSchemaHsqlName = SqlInvariants.INFORMATION_SCHEMA_HSQLNAME;
    this.catalogNameArray = new HsqlNameManager.HsqlName[] { paramDatabase.getCatalogName() };
    Schema localSchema = new Schema(SqlInvariants.INFORMATION_SCHEMA_HSQLNAME, SqlInvariants.INFORMATION_SCHEMA_HSQLNAME.owner);
    this.schemaMap.put(localSchema.getName().name, localSchema);
    try
    {
      localSchema.typeLookup.add(TypeInvariants.CARDINAL_NUMBER);
      localSchema.typeLookup.add(TypeInvariants.YES_OR_NO);
      localSchema.typeLookup.add(TypeInvariants.CHARACTER_DATA);
      localSchema.typeLookup.add(TypeInvariants.SQL_IDENTIFIER);
      localSchema.typeLookup.add(TypeInvariants.TIME_STAMP);
      localSchema.charsetLookup.add(TypeInvariants.SQL_TEXT);
      localSchema.charsetLookup.add(TypeInvariants.SQL_IDENTIFIER_CHARSET);
      localSchema.charsetLookup.add(TypeInvariants.SQL_CHARACTER);
      localSchema.collationLookup.add(Collation.getDefaultInstance());
    }
    catch (HsqlException localHsqlException)
    {
    }
  }

  public void setSchemaChangeTimestamp()
  {
    this.schemaChangeTimestamp = this.database.txManager.getGlobalChangeTimestamp();
  }

  public long getSchemaChangeTimestamp()
  {
    return this.schemaChangeTimestamp;
  }

  public HsqlNameManager.HsqlName getSQLJSchemaHsqlName()
  {
    return SqlInvariants.SQLJ_SCHEMA_HSQLNAME;
  }

  public void createPublicSchema()
  {
    this.writeLock.lock();
    try
    {
      HsqlNameManager.HsqlName localHsqlName = this.database.nameManager.newHsqlName(null, "PUBLIC", 2);
      Schema localSchema = new Schema(localHsqlName, this.database.getGranteeManager().getDBARole());
      this.defaultSchemaHsqlName = localSchema.getName();
      this.schemaMap.put(localSchema.getName().name, localSchema);
    }
    finally
    {
      this.writeLock.unlock();
    }
  }

  public void createSchema(HsqlNameManager.HsqlName paramHsqlName, Grantee paramGrantee)
  {
    this.writeLock.lock();
    try
    {
      SqlInvariants.checkSchemaNameNotSystem(paramHsqlName.name);
      Schema localSchema = new Schema(paramHsqlName, paramGrantee);
      this.schemaMap.add(paramHsqlName.name, localSchema);
    }
    finally
    {
      this.writeLock.unlock();
    }
  }

  public void dropSchema(Session paramSession, String paramString, boolean paramBoolean)
  {
    this.writeLock.lock();
    try
    {
      Schema localSchema = (Schema)this.schemaMap.get(paramString);
      if (localSchema == null)
        throw Error.error(5501, paramString);
      if (SqlInvariants.isLobsSchemaName(paramString))
        throw Error.error(5503, paramString);
      if ((!paramBoolean) && (!localSchema.isEmpty()))
        throw Error.error(4200);
      OrderedHashSet localOrderedHashSet = new OrderedHashSet();
      getCascadingReferencesToSchema(localSchema.getName(), localOrderedHashSet);
      removeSchemaObjects(localOrderedHashSet);
      Iterator localIterator = localSchema.schemaObjectIterator(3);
      Object localObject2;
      while (localIterator.hasNext())
      {
        localObject1 = (Table)localIterator.next();
        localObject2 = ((Table)localObject1).getFKConstraints();
        for (int i = 0; i < localObject2.length; i++)
        {
          SchemaObject localSchemaObject = localObject2[i];
          if (localSchemaObject.getMain().getSchemaName() != localSchema.getName())
          {
            localSchemaObject.getMain().removeConstraint(localSchemaObject.getMainName().name);
            removeReferencesFrom(localSchemaObject);
          }
        }
        removeTable(paramSession, (Table)localObject1);
      }
      Object localObject1 = localSchema.schemaObjectIterator(7);
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (NumberSequence)((Iterator)localObject1).next();
        this.database.getGranteeManager().removeDbObject(((NumberSequence)localObject2).getName());
      }
      localSchema.release();
      this.schemaMap.remove(paramString);
      if (this.defaultSchemaHsqlName.name.equals(paramString))
      {
        localObject2 = this.database.nameManager.newHsqlName(paramString, false, 2);
        localSchema = new Schema((HsqlNameManager.HsqlName)localObject2, this.database.getGranteeManager().getDBARole());
        this.defaultSchemaHsqlName = localSchema.getName();
        this.schemaMap.put(localSchema.getName().name, localSchema);
      }
      this.database.getUserManager().removeSchemaReference(paramString);
      this.database.getSessionManager().removeSchemaReference(localSchema);
    }
    finally
    {
      this.writeLock.unlock();
    }
  }

  public void renameSchema(HsqlNameManager.HsqlName paramHsqlName1, HsqlNameManager.HsqlName paramHsqlName2)
  {
    this.writeLock.lock();
    try
    {
      Schema localSchema1 = (Schema)this.schemaMap.get(paramHsqlName1.name);
      Schema localSchema2 = (Schema)this.schemaMap.get(paramHsqlName2.name);
      if (localSchema1 == null)
        throw Error.error(5501, paramHsqlName1.name);
      if (localSchema2 != null)
        throw Error.error(5504, paramHsqlName2.name);
      SqlInvariants.checkSchemaNameNotSystem(paramHsqlName1.name);
      SqlInvariants.checkSchemaNameNotSystem(paramHsqlName2.name);
      int i = this.schemaMap.getIndex(paramHsqlName1.name);
      localSchema1.getName().rename(paramHsqlName2);
      this.schemaMap.set(i, paramHsqlName2.name, localSchema1);
    }
    finally
    {
      this.writeLock.unlock();
    }
  }

  public void release()
  {
    this.writeLock.lock();
    try
    {
      Iterator localIterator = this.schemaMap.values().iterator();
      while (localIterator.hasNext())
      {
        Schema localSchema = (Schema)localIterator.next();
        localSchema.release();
      }
    }
    finally
    {
      this.writeLock.unlock();
    }
  }

  public String[] getSchemaNamesArray()
  {
    this.readLock.lock();
    try
    {
      String[] arrayOfString1 = new String[this.schemaMap.size()];
      this.schemaMap.toKeysArray(arrayOfString1);
      String[] arrayOfString2 = arrayOfString1;
      return arrayOfString2;
    }
    finally
    {
      this.readLock.unlock();
    }
  }

  public Schema[] getAllSchemas()
  {
    this.readLock.lock();
    try
    {
      Schema[] arrayOfSchema1 = new Schema[this.schemaMap.size()];
      this.schemaMap.toValuesArray(arrayOfSchema1);
      Schema[] arrayOfSchema2 = arrayOfSchema1;
      return arrayOfSchema2;
    }
    finally
    {
      this.readLock.unlock();
    }
  }

  public HsqlNameManager.HsqlName getUserSchemaHsqlName(String paramString)
  {
    this.readLock.lock();
    try
    {
      Schema localSchema = (Schema)this.schemaMap.get(paramString);
      if (localSchema == null)
        throw Error.error(4850, paramString);
      if (localSchema.getName() == SqlInvariants.INFORMATION_SCHEMA_HSQLNAME)
        throw Error.error(4850, paramString);
      HsqlNameManager.HsqlName localHsqlName = localSchema.getName();
      return localHsqlName;
    }
    finally
    {
      this.readLock.unlock();
    }
  }

  public Grantee toSchemaOwner(String paramString)
  {
    this.readLock.lock();
    try
    {
      Schema localSchema = (Schema)this.schemaMap.get(paramString);
      Grantee localGrantee = localSchema == null ? null : localSchema.getOwner();
      return localGrantee;
    }
    finally
    {
      this.readLock.unlock();
    }
  }

  public HsqlNameManager.HsqlName getDefaultSchemaHsqlName()
  {
    return this.defaultSchemaHsqlName;
  }

  public void setDefaultSchemaHsqlName(HsqlNameManager.HsqlName paramHsqlName)
  {
    this.defaultSchemaHsqlName = paramHsqlName;
  }

  public boolean schemaExists(String paramString)
  {
    this.readLock.lock();
    try
    {
      boolean bool = this.schemaMap.containsKey(paramString);
      return bool;
    }
    finally
    {
      this.readLock.unlock();
    }
  }

  public HsqlNameManager.HsqlName findSchemaHsqlName(String paramString)
  {
    this.readLock.lock();
    try
    {
      Schema localSchema = (Schema)this.schemaMap.get(paramString);
      if (localSchema == null)
      {
        localHsqlName = null;
        return localHsqlName;
      }
      HsqlNameManager.HsqlName localHsqlName = localSchema.getName();
      return localHsqlName;
    }
    finally
    {
      this.readLock.unlock();
    }
  }

  public HsqlNameManager.HsqlName getSchemaHsqlName(String paramString)
  {
    if (paramString == null)
      return this.defaultSchemaHsqlName;
    this.readLock.lock();
    try
    {
      Schema localSchema = (Schema)this.schemaMap.get(paramString);
      if (localSchema == null)
        throw Error.error(4850, paramString);
      HsqlNameManager.HsqlName localHsqlName = localSchema.getName();
      return localHsqlName;
    }
    finally
    {
      this.readLock.unlock();
    }
  }

  public String getSchemaName(String paramString)
  {
    return getSchemaHsqlName(paramString).name;
  }

  public Schema findSchema(String paramString)
  {
    this.readLock.lock();
    try
    {
      Schema localSchema = (Schema)this.schemaMap.get(paramString);
      return localSchema;
    }
    finally
    {
      this.readLock.unlock();
    }
  }

  public void dropSchemas(Session paramSession, Grantee paramGrantee, boolean paramBoolean)
  {
    this.writeLock.lock();
    try
    {
      HsqlArrayList localHsqlArrayList = getSchemas(paramGrantee);
      Iterator localIterator = localHsqlArrayList.iterator();
      while (localIterator.hasNext())
      {
        Schema localSchema = (Schema)localIterator.next();
        dropSchema(paramSession, localSchema.getName().name, paramBoolean);
      }
    }
    finally
    {
      this.writeLock.unlock();
    }
  }

  public HsqlArrayList getSchemas(Grantee paramGrantee)
  {
    this.readLock.lock();
    try
    {
      HsqlArrayList localHsqlArrayList = new HsqlArrayList();
      Iterator localIterator = this.schemaMap.values().iterator();
      while (localIterator.hasNext())
      {
        localObject1 = (Schema)localIterator.next();
        if (paramGrantee.equals(((Schema)localObject1).getOwner()))
          localHsqlArrayList.add(localObject1);
      }
      Object localObject1 = localHsqlArrayList;
      return localObject1;
    }
    finally
    {
      this.readLock.unlock();
    }
  }

  public boolean hasSchemas(Grantee paramGrantee)
  {
    this.readLock.lock();
    try
    {
      Iterator localIterator = this.schemaMap.values().iterator();
      while (localIterator.hasNext())
      {
        Schema localSchema = (Schema)localIterator.next();
        if (paramGrantee.equals(localSchema.getOwner()))
        {
          boolean bool2 = true;
          return bool2;
        }
      }
      boolean bool1 = false;
      return bool1;
    }
    finally
    {
      this.readLock.unlock();
    }
  }

  public HsqlArrayList getAllTables(boolean paramBoolean)
  {
    this.readLock.lock();
    try
    {
      HsqlArrayList localHsqlArrayList1 = new HsqlArrayList();
      String[] arrayOfString = getSchemaNamesArray();
      for (int i = 0; i < arrayOfString.length; i++)
      {
        String str = arrayOfString[i];
        if (((paramBoolean) || (!SqlInvariants.isLobsSchemaName(str))) && (!SqlInvariants.isSystemSchemaName(str)))
        {
          HashMappedList localHashMappedList = getTables(str);
          localHsqlArrayList1.addAll(localHashMappedList.values());
        }
      }
      HsqlArrayList localHsqlArrayList2 = localHsqlArrayList1;
      return localHsqlArrayList2;
    }
    finally
    {
      this.readLock.unlock();
    }
  }

  public HashMappedList getTables(String paramString)
  {
    this.readLock.lock();
    try
    {
      Schema localSchema = (Schema)this.schemaMap.get(paramString);
      HashMappedList localHashMappedList = localSchema.tableList;
      return localHashMappedList;
    }
    finally
    {
      this.readLock.unlock();
    }
  }

  public HsqlNameManager.HsqlName[] getCatalogNameArray()
  {
    return this.catalogNameArray;
  }

  public HsqlNameManager.HsqlName[] getCatalogAndBaseTableNames()
  {
    this.readLock.lock();
    try
    {
      OrderedHashSet localOrderedHashSet = new OrderedHashSet();
      HsqlArrayList localHsqlArrayList = getAllTables(false);
      for (int i = 0; i < localHsqlArrayList.size(); i++)
      {
        localObject1 = (Table)localHsqlArrayList.get(i);
        if (!((Table)localObject1).isTemp())
          localOrderedHashSet.add(((Table)localObject1).getName());
      }
      localOrderedHashSet.add(this.database.getCatalogName());
      HsqlNameManager.HsqlName[] arrayOfHsqlName = new HsqlNameManager.HsqlName[localOrderedHashSet.size()];
      localOrderedHashSet.toArray(arrayOfHsqlName);
      Object localObject1 = arrayOfHsqlName;
      return localObject1;
    }
    finally
    {
      this.readLock.unlock();
    }
  }

  public HsqlNameManager.HsqlName[] getCatalogAndBaseTableNames(HsqlNameManager.HsqlName paramHsqlName)
  {
    this.readLock.lock();
    if (paramHsqlName == null)
      return this.catalogNameArray;
    try
    {
      switch (paramHsqlName.type)
      {
      case 2:
        if (findSchemaHsqlName(paramHsqlName.name) == null)
        {
          localObject1 = this.catalogNameArray;
          return localObject1;
        }
        localObject1 = new OrderedHashSet();
        ((OrderedHashSet)localObject1).add(this.database.getCatalogName());
        localObject2 = getTables(paramHsqlName.name);
        for (int i = 0; i < ((HashMappedList)localObject2).size(); i++)
          ((OrderedHashSet)localObject1).add(((SchemaObject)((HashMappedList)localObject2).get(i)).getName());
        localObject3 = new HsqlNameManager.HsqlName[((OrderedHashSet)localObject1).size()];
        ((OrderedHashSet)localObject1).toArray((Object[])localObject3);
        localObject4 = localObject3;
        return localObject4;
      case 11:
        localObject1 = this.catalogNameArray;
        return localObject1;
      case 5:
      case 20:
        findSchemaObject(paramHsqlName.name, paramHsqlName.schema.name, paramHsqlName.type);
      }
      Object localObject1 = findSchemaObject(paramHsqlName.name, paramHsqlName.schema.name, paramHsqlName.type);
      if (localObject1 == null)
      {
        localObject2 = this.catalogNameArray;
        return localObject2;
      }
      Object localObject2 = ((SchemaObject)localObject1).getName().parent;
      Object localObject3 = getReferencesTo(((SchemaObject)localObject1).getName());
      Object localObject4 = new OrderedHashSet();
      ((OrderedHashSet)localObject4).add(this.database.getCatalogName());
      if (localObject2 != null)
      {
        SchemaObject localSchemaObject = findSchemaObject(((HsqlNameManager.HsqlName)localObject2).name, ((HsqlNameManager.HsqlName)localObject2).schema.name, ((HsqlNameManager.HsqlName)localObject2).type);
        if ((localSchemaObject != null) && (localSchemaObject.getName().type == 3))
          ((OrderedHashSet)localObject4).add(localSchemaObject.getName());
      }
      if (((SchemaObject)localObject1).getName().type == 3)
        ((OrderedHashSet)localObject4).add(((SchemaObject)localObject1).getName());
      for (int j = 0; j < ((OrderedHashSet)localObject3).size(); j++)
      {
        localObject5 = (HsqlNameManager.HsqlName)((OrderedHashSet)localObject3).get(j);
        if (((HsqlNameManager.HsqlName)localObject5).type == 3)
        {
          Table localTable = findUserTable(null, ((HsqlNameManager.HsqlName)localObject5).name, ((HsqlNameManager.HsqlName)localObject5).schema.name);
          if ((localTable != null) && (!localTable.isTemp()))
            ((OrderedHashSet)localObject4).add(localObject5);
        }
      }
      HsqlNameManager.HsqlName[] arrayOfHsqlName = new HsqlNameManager.HsqlName[((OrderedHashSet)localObject4).size()];
      ((OrderedHashSet)localObject4).toArray(arrayOfHsqlName);
      Object localObject5 = arrayOfHsqlName;
      return localObject5;
    }
    finally
    {
      this.readLock.unlock();
    }
  }

  private SchemaObjectSet getSchemaObjectSet(Schema paramSchema, int paramInt)
  {
    this.readLock.lock();
    try
    {
      SchemaObjectSet localSchemaObjectSet1 = null;
      switch (paramInt)
      {
      case 7:
        localSchemaObjectSet1 = paramSchema.sequenceLookup;
        break;
      case 3:
      case 4:
        localSchemaObjectSet1 = paramSchema.tableLookup;
        break;
      case 14:
        localSchemaObjectSet1 = paramSchema.charsetLookup;
        break;
      case 15:
        localSchemaObjectSet1 = paramSchema.collationLookup;
        break;
      case 17:
        localSchemaObjectSet1 = paramSchema.procedureLookup;
        break;
      case 16:
        localSchemaObjectSet1 = paramSchema.functionLookup;
        break;
      case 12:
      case 13:
        localSchemaObjectSet1 = paramSchema.typeLookup;
        break;
      case 20:
        localSchemaObjectSet1 = paramSchema.indexLookup;
        break;
      case 5:
        localSchemaObjectSet1 = paramSchema.constraintLookup;
        break;
      case 8:
        localSchemaObjectSet1 = paramSchema.triggerLookup;
        break;
      case 24:
        localSchemaObjectSet1 = paramSchema.specificRoutineLookup;
      case 6:
      case 9:
      case 10:
      case 11:
      case 18:
      case 19:
      case 21:
      case 22:
      case 23:
      }
      SchemaObjectSet localSchemaObjectSet2 = localSchemaObjectSet1;
      return localSchemaObjectSet2;
    }
    finally
    {
      this.readLock.unlock();
    }
  }

  public void checkSchemaObjectNotExists(HsqlNameManager.HsqlName paramHsqlName)
  {
    this.readLock.lock();
    try
    {
      Schema localSchema = (Schema)this.schemaMap.get(paramHsqlName.schema.name);
      SchemaObjectSet localSchemaObjectSet = getSchemaObjectSet(localSchema, paramHsqlName.type);
      localSchemaObjectSet.checkAdd(paramHsqlName);
    }
    finally
    {
      this.readLock.unlock();
    }
  }

  public Table getTable(Session paramSession, String paramString1, String paramString2)
  {
    this.readLock.lock();
    try
    {
      Table localTable1 = null;
      if (("MODULE".equals(paramString2)) || ("SESSION".equals(paramString2)))
      {
        localTable1 = findSessionTable(paramSession, paramString1);
        if (localTable1 == null)
          throw Error.error(5501, paramString1);
        localTable2 = localTable1;
        return localTable2;
      }
      if (paramString2 == null)
      {
        if (((paramSession.database.sqlSyntaxOra) || (paramSession.database.sqlSyntaxDb2)) && ("DUAL".equals(paramString1)))
        {
          localTable2 = this.dualTable;
          return localTable2;
        }
        localTable1 = findSessionTable(paramSession, paramString1);
      }
      if (localTable1 == null)
      {
        paramString2 = paramSession.getSchemaName(paramString2);
        localTable1 = findUserTable(paramSession, paramString1, paramString2);
      }
      if ((localTable1 == null) && ("INFORMATION_SCHEMA".equals(paramString2)) && (this.database.dbInfo != null))
        localTable1 = this.database.dbInfo.getSystemTable(paramSession, paramString1);
      if (localTable1 == null)
        throw Error.error(5501, paramString1);
      Table localTable2 = localTable1;
      return localTable2;
    }
    finally
    {
      this.readLock.unlock();
    }
  }

  public Table getUserTable(Session paramSession, HsqlNameManager.HsqlName paramHsqlName)
  {
    return getUserTable(paramSession, paramHsqlName.name, paramHsqlName.schema.name);
  }

  public Table getUserTable(Session paramSession, String paramString1, String paramString2)
  {
    Table localTable = findUserTable(paramSession, paramString1, paramString2);
    if (localTable == null)
    {
      String str = paramString2 + '.' + paramString1;
      throw Error.error(5501, str);
    }
    return localTable;
  }

  public Table findUserTable(Session paramSession, String paramString1, String paramString2)
  {
    this.readLock.lock();
    try
    {
      Schema localSchema = (Schema)this.schemaMap.get(paramString2);
      if (localSchema == null)
      {
        Table localTable1 = null;
        return localTable1;
      }
      int i = localSchema.tableList.getIndex(paramString1);
      if (i == -1)
      {
        localTable2 = null;
        return localTable2;
      }
      Table localTable2 = (Table)localSchema.tableList.get(i);
      return localTable2;
    }
    finally
    {
      this.readLock.unlock();
    }
  }

  public Table findSessionTable(Session paramSession, String paramString)
  {
    return paramSession.sessionContext.findSessionTable(paramString);
  }

  public void dropTableOrView(Session paramSession, Table paramTable, boolean paramBoolean)
  {
    this.writeLock.lock();
    try
    {
      if (paramTable.isView())
        dropView(paramTable, paramBoolean);
      else
        dropTable(paramSession, paramTable, paramBoolean);
    }
    finally
    {
      this.writeLock.unlock();
    }
  }

  private void dropView(Table paramTable, boolean paramBoolean)
  {
    Schema localSchema = (Schema)this.schemaMap.get(paramTable.getSchemaName().name);
    removeSchemaObject(paramTable.getName(), paramBoolean);
    localSchema.triggerLookup.removeParent(paramTable.getName());
  }

  private void dropTable(Session paramSession, Table paramTable, boolean paramBoolean)
  {
    Schema localSchema = (Schema)this.schemaMap.get(paramTable.getSchemaName().name);
    int i = localSchema.tableList.getIndex(paramTable.getName().name);
    OrderedHashSet localOrderedHashSet1 = paramTable.getDependentExternalConstraints();
    OrderedHashSet localOrderedHashSet2 = new OrderedHashSet();
    getCascadingReferencesTo(paramTable.getName(), localOrderedHashSet2);
    if (!paramBoolean)
    {
      for (int j = 0; j < localOrderedHashSet1.size(); j++)
      {
        localObject1 = (Constraint)localOrderedHashSet1.get(j);
        localObject2 = ((Constraint)localObject1).getRefName();
        if (((Constraint)localObject1).getConstraintType() == 1)
          throw Error.error(5533, ((HsqlNameManager.HsqlName)localObject2).getSchemaQualifiedStatementName());
      }
      if (!localOrderedHashSet2.isEmpty())
        for (j = 0; j < localOrderedHashSet2.size(); j++)
        {
          localObject1 = (HsqlNameManager.HsqlName)localOrderedHashSet2.get(j);
          if (((HsqlNameManager.HsqlName)localObject1).parent != paramTable.getName())
            throw Error.error(5502, ((HsqlNameManager.HsqlName)localObject1).getSchemaQualifiedStatementName());
        }
    }
    OrderedHashSet localOrderedHashSet3 = new OrderedHashSet();
    Object localObject1 = new OrderedHashSet();
    Object localObject2 = new OrderedHashSet();
    for (int k = 0; k < localOrderedHashSet1.size(); k++)
    {
      localObject3 = (Constraint)localOrderedHashSet1.get(k);
      Table localTable = ((Constraint)localObject3).getMain();
      if (localTable != paramTable)
        localOrderedHashSet3.add(localTable);
      localTable = ((Constraint)localObject3).getRef();
      if (localTable != paramTable)
        localOrderedHashSet3.add(localTable);
      ((OrderedHashSet)localObject1).add(((Constraint)localObject3).getMainName());
      ((OrderedHashSet)localObject1).add(((Constraint)localObject3).getRefName());
      ((OrderedHashSet)localObject2).add(((Constraint)localObject3).getRefIndex().getName());
    }
    OrderedHashSet localOrderedHashSet4 = paramTable.getUniquePKConstraintNames();
    Object localObject3 = new TableWorks(paramSession, paramTable);
    localOrderedHashSet3 = ((TableWorks)localObject3).makeNewTables(localOrderedHashSet3, (OrderedHashSet)localObject1, (OrderedHashSet)localObject2);
    ((TableWorks)localObject3).setNewTablesInSchema(localOrderedHashSet3);
    ((TableWorks)localObject3).updateConstraints(localOrderedHashSet3, (OrderedHashSet)localObject1);
    removeSchemaObjects(localOrderedHashSet2);
    removeTableDependentReferences(paramTable);
    removeReferencesTo(localOrderedHashSet4);
    removeReferencesTo(paramTable.getName());
    removeReferencesFrom(paramTable);
    localSchema.tableList.remove(i);
    localSchema.indexLookup.removeParent(paramTable.getName());
    localSchema.constraintLookup.removeParent(paramTable.getName());
    localSchema.triggerLookup.removeParent(paramTable.getName());
    removeTable(paramSession, paramTable);
    recompileDependentObjects(localOrderedHashSet3);
  }

  private void removeTable(Session paramSession, Table paramTable)
  {
    this.database.getGranteeManager().removeDbObject(paramTable.getName());
    paramTable.releaseTriggers();
    if (paramTable.hasLobColumn())
    {
      RowIterator localRowIterator = paramTable.rowIterator(paramSession);
      while (localRowIterator.hasNext())
      {
        Row localRow = localRowIterator.getNextRow();
        Object[] arrayOfObject = localRow.getData();
        paramSession.sessionData.adjustLobUsageCount(paramTable, arrayOfObject, -1);
      }
    }
    this.database.persistentStoreCollection.releaseStore(paramTable);
  }

  public void setTable(int paramInt, Table paramTable)
  {
    this.writeLock.lock();
    try
    {
      Schema localSchema = (Schema)this.schemaMap.get(paramTable.getSchemaName().name);
      localSchema.tableList.set(paramInt, paramTable.getName().name, paramTable);
    }
    finally
    {
      this.writeLock.unlock();
    }
  }

  public int getTableIndex(Table paramTable)
  {
    this.readLock.lock();
    try
    {
      Schema localSchema = (Schema)this.schemaMap.get(paramTable.getSchemaName().name);
      if (localSchema == null)
      {
        int i = -1;
        return i;
      }
      HsqlNameManager.HsqlName localHsqlName = paramTable.getName();
      int j = localSchema.tableList.getIndex(localHsqlName.name);
      return j;
    }
    finally
    {
      this.readLock.unlock();
    }
  }

  public void recompileDependentObjects(OrderedHashSet paramOrderedHashSet)
  {
    this.writeLock.lock();
    try
    {
      OrderedHashSet localOrderedHashSet = new OrderedHashSet();
      for (int i = 0; i < paramOrderedHashSet.size(); i++)
      {
        Table localTable = (Table)paramOrderedHashSet.get(i);
        localOrderedHashSet.addAll(getReferencesTo(localTable.getName()));
      }
      Session localSession = this.database.sessionManager.getSysSession();
      Object localObject1;
      for (int j = 0; j < localOrderedHashSet.size(); j++)
      {
        HsqlNameManager.HsqlName localHsqlName = (HsqlNameManager.HsqlName)localOrderedHashSet.get(j);
        switch (localHsqlName.type)
        {
        case 4:
        case 5:
        case 6:
        case 16:
        case 17:
        case 18:
        case 24:
          localObject1 = getSchemaObject(localHsqlName);
          ((SchemaObject)localObject1).compile(localSession, null);
        case 7:
        case 8:
        case 9:
        case 10:
        case 11:
        case 12:
        case 13:
        case 14:
        case 15:
        case 19:
        case 20:
        case 21:
        case 22:
        case 23:
        }
      }
      if (Error.TRACE)
      {
        HsqlArrayList localHsqlArrayList = getAllTables(false);
        for (int k = 0; k < localHsqlArrayList.size(); k++)
        {
          localObject1 = (Table)localHsqlArrayList.get(k);
          ((Table)localObject1).verifyConstraintsIntegrity();
        }
      }
    }
    finally
    {
      this.writeLock.unlock();
    }
  }

  public void recompileDependentObjects(Table paramTable)
  {
    this.writeLock.lock();
    try
    {
      OrderedHashSet localOrderedHashSet = new OrderedHashSet();
      getCascadingReferencesTo(paramTable.getName(), localOrderedHashSet);
      Session localSession = this.database.sessionManager.getSysSession();
      Object localObject1;
      for (int i = 0; i < localOrderedHashSet.size(); i++)
      {
        HsqlNameManager.HsqlName localHsqlName = (HsqlNameManager.HsqlName)localOrderedHashSet.get(i);
        switch (localHsqlName.type)
        {
        case 4:
        case 5:
        case 6:
        case 16:
        case 17:
        case 18:
        case 24:
          localObject1 = getSchemaObject(localHsqlName);
          ((SchemaObject)localObject1).compile(localSession, null);
        case 7:
        case 8:
        case 9:
        case 10:
        case 11:
        case 12:
        case 13:
        case 14:
        case 15:
        case 19:
        case 20:
        case 21:
        case 22:
        case 23:
        }
      }
      if (Error.TRACE)
      {
        HsqlArrayList localHsqlArrayList = getAllTables(false);
        for (int j = 0; j < localHsqlArrayList.size(); j++)
        {
          localObject1 = (Table)localHsqlArrayList.get(j);
          ((Table)localObject1).verifyConstraintsIntegrity();
        }
      }
    }
    finally
    {
      this.writeLock.unlock();
    }
  }

  public NumberSequence getSequence(String paramString1, String paramString2, boolean paramBoolean)
  {
    this.readLock.lock();
    try
    {
      Schema localSchema = (Schema)this.schemaMap.get(paramString2);
      if (localSchema != null)
      {
        localNumberSequence1 = (NumberSequence)localSchema.sequenceList.get(paramString1);
        if (localNumberSequence1 != null)
        {
          NumberSequence localNumberSequence2 = localNumberSequence1;
          return localNumberSequence2;
        }
      }
      if (paramBoolean)
        throw Error.error(5501, paramString1);
      NumberSequence localNumberSequence1 = null;
      return localNumberSequence1;
    }
    finally
    {
      this.readLock.unlock();
    }
  }

  public Type getUserDefinedType(String paramString1, String paramString2, boolean paramBoolean)
  {
    this.readLock.lock();
    try
    {
      Schema localSchema = (Schema)this.schemaMap.get(paramString2);
      if (localSchema != null)
      {
        localSchemaObject = localSchema.typeLookup.getObject(paramString1);
        if (localSchemaObject != null)
        {
          Type localType = (Type)localSchemaObject;
          return localType;
        }
      }
      if (paramBoolean)
        throw Error.error(5501, paramString1);
      SchemaObject localSchemaObject = null;
      return localSchemaObject;
    }
    finally
    {
      this.readLock.unlock();
    }
  }

  public Type getDomainOrUDT(String paramString1, String paramString2, boolean paramBoolean)
  {
    this.readLock.lock();
    try
    {
      Schema localSchema = (Schema)this.schemaMap.get(paramString2);
      if (localSchema != null)
      {
        localSchemaObject = localSchema.typeLookup.getObject(paramString1);
        if (localSchemaObject != null)
        {
          Type localType = (Type)localSchemaObject;
          return localType;
        }
      }
      if (paramBoolean)
        throw Error.error(5501, paramString1);
      SchemaObject localSchemaObject = null;
      return localSchemaObject;
    }
    finally
    {
      this.readLock.unlock();
    }
  }

  public Type getDomain(String paramString1, String paramString2, boolean paramBoolean)
  {
    this.readLock.lock();
    try
    {
      Schema localSchema = (Schema)this.schemaMap.get(paramString2);
      if (localSchema != null)
      {
        localSchemaObject = localSchema.typeLookup.getObject(paramString1);
        if ((localSchemaObject != null) && (((Type)localSchemaObject).isDomainType()))
        {
          Type localType = (Type)localSchemaObject;
          return localType;
        }
      }
      if (paramBoolean)
        throw Error.error(5501, paramString1);
      SchemaObject localSchemaObject = null;
      return localSchemaObject;
    }
    finally
    {
      this.readLock.unlock();
    }
  }

  public Type getDistinctType(String paramString1, String paramString2, boolean paramBoolean)
  {
    this.readLock.lock();
    try
    {
      Schema localSchema = (Schema)this.schemaMap.get(paramString2);
      if (localSchema != null)
      {
        localSchemaObject = localSchema.typeLookup.getObject(paramString1);
        if ((localSchemaObject != null) && (((Type)localSchemaObject).isDistinctType()))
        {
          Type localType = (Type)localSchemaObject;
          return localType;
        }
      }
      if (paramBoolean)
        throw Error.error(5501, paramString1);
      SchemaObject localSchemaObject = null;
      return localSchemaObject;
    }
    finally
    {
      this.readLock.unlock();
    }
  }

  public SchemaObject getSchemaObject(String paramString1, String paramString2, int paramInt)
  {
    this.readLock.lock();
    try
    {
      SchemaObject localSchemaObject1 = findSchemaObject(paramString1, paramString2, paramInt);
      if (localSchemaObject1 == null)
        throw Error.error(SchemaObjectSet.getGetErrorCode(paramInt), paramString1);
      SchemaObject localSchemaObject2 = localSchemaObject1;
      return localSchemaObject2;
    }
    finally
    {
      this.readLock.unlock();
    }
  }

  public SchemaObject findSchemaObject(String paramString1, String paramString2, int paramInt)
  {
    this.readLock.lock();
    try
    {
      Schema localSchema = (Schema)this.schemaMap.get(paramString2);
      if (localSchema == null)
      {
        localObject1 = null;
        return localObject1;
      }
      Object localObject1 = null;
      Object localObject2;
      HsqlNameManager.HsqlName localHsqlName;
      Table localTable;
      switch (paramInt)
      {
      case 7:
        localObject2 = localSchema.sequenceLookup.getObject(paramString1);
        return localObject2;
      case 3:
      case 4:
        localObject2 = localSchema.tableLookup.getObject(paramString1);
        return localObject2;
      case 14:
        if (paramString1.equals("SQL_IDENTIFIER"))
        {
          localObject2 = TypeInvariants.SQL_IDENTIFIER_CHARSET;
          return localObject2;
        }
        if (paramString1.equals("SQL_TEXT"))
        {
          localObject2 = TypeInvariants.SQL_TEXT;
          return localObject2;
        }
        if (paramString1.equals("LATIN1"))
        {
          localObject2 = TypeInvariants.LATIN1;
          return localObject2;
        }
        if (paramString1.equals("ASCII_GRAPHIC"))
        {
          localObject2 = TypeInvariants.ASCII_GRAPHIC;
          return localObject2;
        }
        localObject2 = localSchema.charsetLookup.getObject(paramString1);
        return localObject2;
      case 15:
        localObject2 = localSchema.collationLookup.getObject(paramString1);
        return localObject2;
      case 17:
        localObject2 = localSchema.procedureLookup.getObject(paramString1);
        return localObject2;
      case 16:
        localObject2 = localSchema.functionLookup.getObject(paramString1);
        return localObject2;
      case 18:
        localObject2 = localSchema.procedureLookup.getObject(paramString1);
        if (localObject2 == null)
          localObject2 = localSchema.functionLookup.getObject(paramString1);
        Object localObject3 = localObject2;
        return localObject3;
      case 24:
        localObject2 = localSchema.specificRoutineLookup.getObject(paramString1);
        return localObject2;
      case 12:
      case 13:
        localObject2 = localSchema.typeLookup.getObject(paramString1);
        return localObject2;
      case 20:
        localObject1 = localSchema.indexLookup;
        localHsqlName = ((SchemaObjectSet)localObject1).getName(paramString1);
        if (localHsqlName == null)
        {
          localObject2 = null;
          return localObject2;
        }
        localTable = (Table)localSchema.tableList.get(localHsqlName.parent.name);
        localObject2 = localTable.getIndex(paramString1);
        return localObject2;
      case 5:
        localObject1 = localSchema.constraintLookup;
        localHsqlName = ((SchemaObjectSet)localObject1).getName(paramString1);
        if (localHsqlName == null)
        {
          localObject2 = null;
          return localObject2;
        }
        localTable = (Table)localSchema.tableList.get(localHsqlName.parent.name);
        if (localTable == null)
        {
          localObject2 = null;
          return localObject2;
        }
        localObject2 = localTable.getConstraint(paramString1);
        return localObject2;
      case 8:
        localObject1 = localSchema.indexLookup;
        localHsqlName = ((SchemaObjectSet)localObject1).getName(paramString1);
        if (localHsqlName == null)
        {
          localObject2 = null;
          return localObject2;
        }
        localTable = (Table)localSchema.tableList.get(localHsqlName.parent.name);
        localObject2 = localTable.getTrigger(paramString1);
        return localObject2;
      case 6:
      case 9:
      case 10:
      case 11:
      case 19:
      case 21:
      case 22:
      case 23:
      }
      throw Error.runtimeError(201, "SchemaManager");
    }
    finally
    {
      this.readLock.unlock();
    }
  }

  Table findUserTableForIndex(Session paramSession, String paramString1, String paramString2)
  {
    this.readLock.lock();
    try
    {
      Schema localSchema = (Schema)this.schemaMap.get(paramString2);
      HsqlNameManager.HsqlName localHsqlName = localSchema.indexLookup.getName(paramString1);
      if (localHsqlName == null)
      {
        localTable = null;
        return localTable;
      }
      Table localTable = findUserTable(paramSession, localHsqlName.parent.name, paramString2);
      return localTable;
    }
    finally
    {
      this.readLock.unlock();
    }
  }

  void dropIndex(Session paramSession, HsqlNameManager.HsqlName paramHsqlName)
  {
    this.writeLock.lock();
    try
    {
      Table localTable = getTable(paramSession, paramHsqlName.parent.name, paramHsqlName.parent.schema.name);
      TableWorks localTableWorks = new TableWorks(paramSession, localTable);
      localTableWorks.dropIndex(paramHsqlName.name);
    }
    finally
    {
      this.writeLock.unlock();
    }
  }

  void dropConstraint(Session paramSession, HsqlNameManager.HsqlName paramHsqlName, boolean paramBoolean)
  {
    this.writeLock.lock();
    try
    {
      Table localTable = getTable(paramSession, paramHsqlName.parent.name, paramHsqlName.parent.schema.name);
      TableWorks localTableWorks = new TableWorks(paramSession, localTable);
      localTableWorks.dropConstraint(paramHsqlName.name, paramBoolean);
    }
    finally
    {
      this.writeLock.unlock();
    }
  }

  void removeDependentObjects(HsqlNameManager.HsqlName paramHsqlName)
  {
    this.writeLock.lock();
    try
    {
      Schema localSchema = (Schema)this.schemaMap.get(paramHsqlName.schema.name);
      localSchema.indexLookup.removeParent(paramHsqlName);
      localSchema.constraintLookup.removeParent(paramHsqlName);
      localSchema.triggerLookup.removeParent(paramHsqlName);
    }
    finally
    {
      this.writeLock.unlock();
    }
  }

  void removeExportedKeys(Table paramTable)
  {
    this.writeLock.lock();
    try
    {
      Schema localSchema = (Schema)this.schemaMap.get(paramTable.getSchemaName().name);
      for (int i = 0; i < localSchema.tableList.size(); i++)
      {
        Table localTable1 = (Table)localSchema.tableList.get(i);
        Constraint[] arrayOfConstraint = localTable1.getConstraints();
        for (int j = arrayOfConstraint.length - 1; j >= 0; j--)
        {
          Table localTable2 = arrayOfConstraint[j].getRef();
          if (paramTable == localTable2)
            localTable1.removeConstraint(j);
        }
      }
    }
    finally
    {
      this.writeLock.unlock();
    }
  }

  public Iterator databaseObjectIterator(String paramString, int paramInt)
  {
    this.readLock.lock();
    try
    {
      Schema localSchema = (Schema)this.schemaMap.get(paramString);
      Iterator localIterator = localSchema.schemaObjectIterator(paramInt);
      return localIterator;
    }
    finally
    {
      this.readLock.unlock();
    }
  }

  public Iterator databaseObjectIterator(int paramInt)
  {
    this.readLock.lock();
    try
    {
      Iterator localIterator = this.schemaMap.values().iterator();
      WrapperIterator localWrapperIterator1 = new WrapperIterator();
      while (localIterator.hasNext())
      {
        int i = paramInt;
        if (paramInt == 18)
          i = 16;
        Schema localSchema = (Schema)localIterator.next();
        SchemaObjectSet localSchemaObjectSet = localSchema.getObjectSet(i);
        Object[] arrayOfObject;
        if (localSchemaObjectSet.map.size() != 0)
        {
          arrayOfObject = new Object[localSchemaObjectSet.map.size()];
          localSchemaObjectSet.map.valuesToArray(arrayOfObject);
          localWrapperIterator1 = new WrapperIterator(localWrapperIterator1, new WrapperIterator(arrayOfObject));
        }
        if (paramInt == 18)
        {
          localSchemaObjectSet = localSchema.getObjectSet(17);
          if (localSchemaObjectSet.map.size() != 0)
          {
            arrayOfObject = new Object[localSchemaObjectSet.map.size()];
            localSchemaObjectSet.map.valuesToArray(arrayOfObject);
            localWrapperIterator1 = new WrapperIterator(localWrapperIterator1, new WrapperIterator(arrayOfObject));
          }
        }
      }
      WrapperIterator localWrapperIterator2 = localWrapperIterator1;
      return localWrapperIterator2;
    }
    finally
    {
      this.readLock.unlock();
    }
  }

  private void addReferencesFrom(SchemaObject paramSchemaObject)
  {
    OrderedHashSet localOrderedHashSet = paramSchemaObject.getReferences();
    HsqlNameManager.HsqlName localHsqlName1 = paramSchemaObject.getName();
    if (localOrderedHashSet == null)
      return;
    for (int i = 0; i < localOrderedHashSet.size(); i++)
    {
      HsqlNameManager.HsqlName localHsqlName2 = (HsqlNameManager.HsqlName)localOrderedHashSet.get(i);
      if ((paramSchemaObject instanceof Routine))
        localHsqlName1 = ((Routine)paramSchemaObject).getSpecificName();
      this.referenceMap.put(localHsqlName2, localHsqlName1);
    }
  }

  private void removeReferencesTo(OrderedHashSet paramOrderedHashSet)
  {
    for (int i = 0; i < paramOrderedHashSet.size(); i++)
    {
      HsqlNameManager.HsqlName localHsqlName = (HsqlNameManager.HsqlName)paramOrderedHashSet.get(i);
      this.referenceMap.remove(localHsqlName);
    }
  }

  private void removeReferencesTo(HsqlNameManager.HsqlName paramHsqlName)
  {
    this.referenceMap.remove(paramHsqlName);
  }

  private void removeReferencesFrom(SchemaObject paramSchemaObject)
  {
    HsqlNameManager.HsqlName localHsqlName1 = paramSchemaObject.getName();
    OrderedHashSet localOrderedHashSet = paramSchemaObject.getReferences();
    if (localOrderedHashSet == null)
      return;
    for (int i = 0; i < localOrderedHashSet.size(); i++)
    {
      HsqlNameManager.HsqlName localHsqlName2 = (HsqlNameManager.HsqlName)localOrderedHashSet.get(i);
      if ((paramSchemaObject instanceof Routine))
        localHsqlName1 = ((Routine)paramSchemaObject).getSpecificName();
      this.referenceMap.remove(localHsqlName2, localHsqlName1);
    }
  }

  private void removeTableDependentReferences(Table paramTable)
  {
    OrderedHashSet localOrderedHashSet = paramTable.getReferencesForDependents();
    if (localOrderedHashSet == null)
      return;
    for (int i = 0; i < localOrderedHashSet.size(); i++)
    {
      HsqlNameManager.HsqlName localHsqlName = (HsqlNameManager.HsqlName)localOrderedHashSet.get(i);
      Object localObject = null;
      switch (localHsqlName.type)
      {
      case 5:
        localObject = paramTable.getConstraint(localHsqlName.name);
        break;
      case 8:
        localObject = paramTable.getTrigger(localHsqlName.name);
        break;
      case 9:
        localObject = paramTable.getColumn(paramTable.getColumnIndex(localHsqlName.name));
      case 6:
      case 7:
      }
      removeReferencesFrom((SchemaObject)localObject);
    }
  }

  public OrderedHashSet getReferencesTo(HsqlNameManager.HsqlName paramHsqlName)
  {
    this.readLock.lock();
    try
    {
      OrderedHashSet localOrderedHashSet = new OrderedHashSet();
      Iterator localIterator = this.referenceMap.get(paramHsqlName);
      while (localIterator.hasNext())
      {
        localObject1 = (HsqlNameManager.HsqlName)localIterator.next();
        localOrderedHashSet.add(localObject1);
      }
      Object localObject1 = localOrderedHashSet;
      return localObject1;
    }
    finally
    {
      this.readLock.unlock();
    }
  }

  public OrderedHashSet getReferencesTo(HsqlNameManager.HsqlName paramHsqlName1, HsqlNameManager.HsqlName paramHsqlName2)
  {
    this.readLock.lock();
    try
    {
      OrderedHashSet localOrderedHashSet1 = new OrderedHashSet();
      Iterator localIterator = this.referenceMap.get(paramHsqlName1);
      while (localIterator.hasNext())
      {
        localObject1 = (HsqlNameManager.HsqlName)localIterator.next();
        SchemaObject localSchemaObject = getSchemaObject((HsqlNameManager.HsqlName)localObject1);
        OrderedHashSet localOrderedHashSet2 = localSchemaObject.getReferences();
        if (localOrderedHashSet2.contains(paramHsqlName2))
          localOrderedHashSet1.add(localObject1);
      }
      Object localObject1 = localOrderedHashSet1;
      return localObject1;
    }
    finally
    {
      this.readLock.unlock();
    }
  }

  private boolean isReferenced(HsqlNameManager.HsqlName paramHsqlName)
  {
    this.writeLock.lock();
    try
    {
      boolean bool = this.referenceMap.containsKey(paramHsqlName);
      return bool;
    }
    finally
    {
      this.writeLock.unlock();
    }
  }

  public void getCascadingReferencesTo(HsqlNameManager.HsqlName paramHsqlName, OrderedHashSet paramOrderedHashSet)
  {
    this.readLock.lock();
    try
    {
      OrderedHashSet localOrderedHashSet = new OrderedHashSet();
      Iterator localIterator = this.referenceMap.get(paramHsqlName);
      while (localIterator.hasNext())
      {
        HsqlNameManager.HsqlName localHsqlName1 = (HsqlNameManager.HsqlName)localIterator.next();
        boolean bool = paramOrderedHashSet.add(localHsqlName1);
        if (bool)
          localOrderedHashSet.add(localHsqlName1);
      }
      for (int i = 0; i < localOrderedHashSet.size(); i++)
      {
        HsqlNameManager.HsqlName localHsqlName2 = (HsqlNameManager.HsqlName)localOrderedHashSet.get(i);
        getCascadingReferencesTo(localHsqlName2, paramOrderedHashSet);
      }
    }
    finally
    {
      this.readLock.unlock();
    }
  }

  public void getCascadingReferencesToSchema(HsqlNameManager.HsqlName paramHsqlName, OrderedHashSet paramOrderedHashSet)
  {
    Iterator localIterator = this.referenceMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      HsqlNameManager.HsqlName localHsqlName1 = (HsqlNameManager.HsqlName)localIterator.next();
      if (localHsqlName1.schema == paramHsqlName)
        getCascadingReferencesTo(localHsqlName1, paramOrderedHashSet);
    }
    for (int i = paramOrderedHashSet.size() - 1; i >= 0; i--)
    {
      HsqlNameManager.HsqlName localHsqlName2 = (HsqlNameManager.HsqlName)paramOrderedHashSet.get(i);
      if (localHsqlName2.schema == paramHsqlName)
        paramOrderedHashSet.remove(i);
    }
  }

  public MultiValueHashMap getReferencesToSchema(String paramString)
  {
    MultiValueHashMap localMultiValueHashMap = new MultiValueHashMap();
    Iterator localIterator1 = this.referenceMap.keySet().iterator();
    while (localIterator1.hasNext())
    {
      HsqlNameManager.HsqlName localHsqlName = (HsqlNameManager.HsqlName)localIterator1.next();
      if (localHsqlName.schema.name.equals(paramString))
      {
        Iterator localIterator2 = this.referenceMap.get(localHsqlName);
        while (localIterator2.hasNext())
          localMultiValueHashMap.put(localHsqlName, localIterator2.next());
      }
    }
    return localMultiValueHashMap;
  }

  public HsqlNameManager.HsqlName getSchemaObjectName(HsqlNameManager.HsqlName paramHsqlName, String paramString, int paramInt, boolean paramBoolean)
  {
    this.readLock.lock();
    try
    {
      Schema localSchema = (Schema)this.schemaMap.get(paramHsqlName.name);
      SchemaObjectSet localSchemaObjectSet = null;
      if (localSchema == null)
      {
        if (paramBoolean)
          throw Error.error(SchemaObjectSet.getGetErrorCode(paramInt));
        localObject1 = null;
        return localObject1;
      }
      if (paramInt == 18)
      {
        localSchemaObjectSet = localSchema.functionLookup;
        localObject1 = localSchema.functionLookup.getObject(paramString);
        if (localObject1 == null)
        {
          localSchemaObjectSet = localSchema.procedureLookup;
          localObject1 = localSchema.procedureLookup.getObject(paramString);
        }
      }
      else
      {
        localSchemaObjectSet = getSchemaObjectSet(localSchema, paramInt);
      }
      if (paramBoolean)
        localSchemaObjectSet.checkExists(paramString);
      Object localObject1 = localSchemaObjectSet.getName(paramString);
      return localObject1;
    }
    finally
    {
      this.readLock.unlock();
    }
  }

  public SchemaObject getSchemaObject(HsqlNameManager.HsqlName paramHsqlName)
  {
    this.readLock.lock();
    try
    {
      Schema localSchema = (Schema)this.schemaMap.get(paramHsqlName.schema.name);
      if (localSchema == null)
      {
        localSchemaObject = null;
        return localSchemaObject;
      }
      Object localObject1;
      Table localTable;
      Object localObject2;
      switch (paramHsqlName.type)
      {
      case 7:
        localSchemaObject = (SchemaObject)localSchema.sequenceList.get(paramHsqlName.name);
        return localSchemaObject;
      case 3:
      case 4:
        localSchemaObject = (SchemaObject)localSchema.tableList.get(paramHsqlName.name);
        return localSchemaObject;
      case 14:
        localSchemaObject = localSchema.charsetLookup.getObject(paramHsqlName.name);
        return localSchemaObject;
      case 15:
        localSchemaObject = localSchema.collationLookup.getObject(paramHsqlName.name);
        return localSchemaObject;
      case 17:
        localSchemaObject = localSchema.procedureLookup.getObject(paramHsqlName.name);
        return localSchemaObject;
      case 16:
        localSchemaObject = localSchema.functionLookup.getObject(paramHsqlName.name);
        return localSchemaObject;
      case 24:
        localSchemaObject = localSchema.specificRoutineLookup.getObject(paramHsqlName.name);
        return localSchemaObject;
      case 18:
        localSchemaObject = localSchema.functionLookup.getObject(paramHsqlName.name);
        if (localSchemaObject == null)
          localSchemaObject = localSchema.procedureLookup.getObject(paramHsqlName.name);
        localObject1 = localSchemaObject;
        return localObject1;
      case 12:
      case 13:
        localObject1 = localSchema.typeLookup.getObject(paramHsqlName.name);
        return localObject1;
      case 8:
        paramHsqlName = localSchema.triggerLookup.getName(paramHsqlName.name);
        if (paramHsqlName == null)
        {
          localObject1 = null;
          return localObject1;
        }
        localObject1 = paramHsqlName.parent;
        localTable = (Table)localSchema.tableList.get(((HsqlNameManager.HsqlName)localObject1).name);
        localObject2 = localTable.getTrigger(paramHsqlName.name);
        return localObject2;
      case 5:
        paramHsqlName = localSchema.constraintLookup.getName(paramHsqlName.name);
        if (paramHsqlName == null)
        {
          localObject1 = null;
          return localObject1;
        }
        localObject1 = paramHsqlName.parent;
        localTable = (Table)localSchema.tableList.get(((HsqlNameManager.HsqlName)localObject1).name);
        localObject2 = localTable.getConstraint(paramHsqlName.name);
        return localObject2;
      case 6:
        localObject1 = null;
        return localObject1;
      case 20:
        paramHsqlName = localSchema.indexLookup.getName(paramHsqlName.name);
        if (paramHsqlName == null)
        {
          localObject1 = null;
          return localObject1;
        }
        localObject1 = paramHsqlName.parent;
        localTable = (Table)localSchema.tableList.get(((HsqlNameManager.HsqlName)localObject1).name);
        localObject2 = localTable.getIndex(paramHsqlName.name);
        return localObject2;
      case 9:
      case 10:
      case 11:
      case 19:
      case 21:
      case 22:
      case 23:
      }
      SchemaObject localSchemaObject = null;
      return localSchemaObject;
    }
    finally
    {
      this.readLock.unlock();
    }
  }

  public void checkColumnIsReferenced(HsqlNameManager.HsqlName paramHsqlName1, HsqlNameManager.HsqlName paramHsqlName2)
  {
    OrderedHashSet localOrderedHashSet = getReferencesTo(paramHsqlName1, paramHsqlName2);
    if (!localOrderedHashSet.isEmpty())
    {
      HsqlNameManager.HsqlName localHsqlName = (HsqlNameManager.HsqlName)localOrderedHashSet.get(0);
      throw Error.error(5502, localHsqlName.getSchemaQualifiedStatementName());
    }
  }

  public void checkObjectIsReferenced(HsqlNameManager.HsqlName paramHsqlName)
  {
    OrderedHashSet localOrderedHashSet = getReferencesTo(paramHsqlName);
    HsqlNameManager.HsqlName localHsqlName = null;
    for (int i = 0; i < localOrderedHashSet.size(); i++)
    {
      localHsqlName = (HsqlNameManager.HsqlName)localOrderedHashSet.get(i);
      if (localHsqlName.parent != paramHsqlName)
        break;
      localHsqlName = null;
    }
    if (localHsqlName == null)
      return;
    i = 5502;
    if (localHsqlName.type == 0)
      i = 5533;
    throw Error.error(i, localHsqlName.getSchemaQualifiedStatementName());
  }

  public void checkSchemaNameCanChange(HsqlNameManager.HsqlName paramHsqlName)
  {
    this.readLock.lock();
    try
    {
      Iterator localIterator = this.referenceMap.values().iterator();
      for (HsqlNameManager.HsqlName localHsqlName = null; localIterator.hasNext(); localHsqlName = null)
      {
        localHsqlName = (HsqlNameManager.HsqlName)localIterator.next();
        switch (localHsqlName.type)
        {
        case 4:
        case 8:
        case 16:
        case 17:
        case 18:
        case 24:
          if (localHsqlName.schema == paramHsqlName)
            break label120;
        }
      }
      label120: if (localHsqlName == null)
        return;
      throw Error.error(5502, localHsqlName.getSchemaQualifiedStatementName());
    }
    finally
    {
      this.readLock.unlock();
    }
  }

  public void addSchemaObject(SchemaObject paramSchemaObject)
  {
    this.writeLock.lock();
    try
    {
      HsqlNameManager.HsqlName localHsqlName1 = paramSchemaObject.getName();
      Schema localSchema = (Schema)this.schemaMap.get(localHsqlName1.schema.name);
      SchemaObjectSet localSchemaObjectSet1 = getSchemaObjectSet(localSchema, localHsqlName1.type);
      Object localObject1;
      HsqlNameManager.HsqlName localHsqlName2;
      switch (localHsqlName1.type)
      {
      case 16:
      case 17:
        localObject1 = (RoutineSchema)localSchemaObjectSet1.getObject(localHsqlName1.name);
        SchemaObjectSet localSchemaObjectSet2;
        if (localObject1 == null)
        {
          localObject1 = new RoutineSchema(localHsqlName1.type, localHsqlName1);
          ((RoutineSchema)localObject1).addSpecificRoutine(this.database, (Routine)paramSchemaObject);
          localSchemaObjectSet1.checkAdd(localHsqlName1);
          localSchemaObjectSet2 = getSchemaObjectSet(localSchema, 24);
          localSchemaObjectSet2.checkAdd(((Routine)paramSchemaObject).getSpecificName());
          localSchemaObjectSet1.add((SchemaObject)localObject1);
          localSchemaObjectSet2.add(paramSchemaObject);
        }
        else
        {
          localSchemaObjectSet2 = getSchemaObjectSet(localSchema, 24);
          localHsqlName2 = ((Routine)paramSchemaObject).getSpecificName();
          if (localHsqlName2 != null)
            localSchemaObjectSet2.checkAdd(localHsqlName2);
          ((RoutineSchema)localObject1).addSpecificRoutine(this.database, (Routine)paramSchemaObject);
          localSchemaObjectSet2.add(paramSchemaObject);
        }
        addReferencesFrom(paramSchemaObject);
        return;
      case 3:
        localObject1 = ((Table)paramSchemaObject).getReferencesForDependents();
        for (int i = 0; i < ((OrderedHashSet)localObject1).size(); i++)
        {
          localHsqlName2 = (HsqlNameManager.HsqlName)((OrderedHashSet)localObject1).get(i);
          switch (localHsqlName2.type)
          {
          case 9:
            int j = ((Table)paramSchemaObject).findColumn(localHsqlName2.name);
            ColumnSchema localColumnSchema = ((Table)paramSchemaObject).getColumn(j);
            addSchemaObject(localColumnSchema);
          }
        }
        break;
      case 9:
        localObject1 = paramSchemaObject.getReferences();
        if ((localObject1 == null) || (((OrderedHashSet)localObject1).isEmpty()))
          return;
        break;
      }
      if (localSchemaObjectSet1 != null)
        localSchemaObjectSet1.add(paramSchemaObject);
      addReferencesFrom(paramSchemaObject);
    }
    finally
    {
      this.writeLock.unlock();
    }
  }

  public void removeSchemaObject(HsqlNameManager.HsqlName paramHsqlName, boolean paramBoolean)
  {
    this.writeLock.lock();
    try
    {
      OrderedHashSet localOrderedHashSet = new OrderedHashSet();
      Object localObject1;
      Object localObject2;
      switch (paramHsqlName.type)
      {
      case 16:
      case 17:
      case 18:
        localObject1 = (RoutineSchema)getSchemaObject(paramHsqlName);
        if (localObject1 != null)
        {
          localObject2 = ((RoutineSchema)localObject1).getSpecificRoutines();
          for (int i = 0; i < localObject2.length; i++)
            getCascadingReferencesTo(localObject2[i].getSpecificName(), localOrderedHashSet);
        }
        break;
      case 3:
      case 4:
      case 7:
      case 12:
      case 14:
      case 15:
      case 24:
        getCascadingReferencesTo(paramHsqlName, localOrderedHashSet);
        break;
      case 13:
        localObject1 = getReferencesTo(paramHsqlName);
        localObject2 = ((OrderedHashSet)localObject1).iterator();
        HsqlNameManager.HsqlName localHsqlName;
        while (((Iterator)localObject2).hasNext())
        {
          localHsqlName = (HsqlNameManager.HsqlName)((Iterator)localObject2).next();
          if (localHsqlName.type == 9)
            ((Iterator)localObject2).remove();
        }
        if (!((OrderedHashSet)localObject1).isEmpty())
        {
          localHsqlName = (HsqlNameManager.HsqlName)((OrderedHashSet)localObject1).get(0);
          throw Error.error(5502, localHsqlName.getSchemaQualifiedStatementName());
        }
        break;
      case 5:
      case 6:
      case 8:
      case 9:
      case 10:
      case 11:
      case 19:
      case 20:
      case 21:
      case 22:
      case 23:
      }
      if (localOrderedHashSet.isEmpty())
      {
        removeSchemaObject(paramHsqlName);
        return;
      }
      if (!paramBoolean)
      {
        localObject1 = (HsqlNameManager.HsqlName)localOrderedHashSet.get(0);
        throw Error.error(5502, ((HsqlNameManager.HsqlName)localObject1).getSchemaQualifiedStatementName());
      }
      localOrderedHashSet.add(paramHsqlName);
      removeSchemaObjects(localOrderedHashSet);
    }
    finally
    {
      this.writeLock.unlock();
    }
  }

  public void removeSchemaObjects(OrderedHashSet paramOrderedHashSet)
  {
    this.writeLock.lock();
    try
    {
      for (int i = 0; i < paramOrderedHashSet.size(); i++)
      {
        HsqlNameManager.HsqlName localHsqlName = (HsqlNameManager.HsqlName)paramOrderedHashSet.get(i);
        removeSchemaObject(localHsqlName);
      }
    }
    finally
    {
      this.writeLock.unlock();
    }
  }

  public void removeSchemaObject(HsqlNameManager.HsqlName paramHsqlName)
  {
    this.writeLock.lock();
    try
    {
      Schema localSchema = (Schema)this.schemaMap.get(paramHsqlName.schema.name);
      Object localObject1 = null;
      SchemaObjectSet localSchemaObjectSet = null;
      Object localObject2;
      Routine[] arrayOfRoutine;
      int i;
      switch (paramHsqlName.type)
      {
      case 7:
        localSchemaObjectSet = localSchema.sequenceLookup;
        localObject1 = localSchemaObjectSet.getObject(paramHsqlName.name);
        break;
      case 3:
      case 4:
        localSchemaObjectSet = localSchema.tableLookup;
        localObject1 = localSchemaObjectSet.getObject(paramHsqlName.name);
        break;
      case 9:
        localObject2 = (Table)getSchemaObject(paramHsqlName.parent);
        if (localObject2 != null)
          localObject1 = ((Table)localObject2).getColumn(((Table)localObject2).getColumnIndex(paramHsqlName.name));
        break;
      case 14:
        localSchemaObjectSet = localSchema.charsetLookup;
        localObject1 = localSchemaObjectSet.getObject(paramHsqlName.name);
        break;
      case 15:
        localSchemaObjectSet = localSchema.collationLookup;
        localObject1 = localSchemaObjectSet.getObject(paramHsqlName.name);
        break;
      case 17:
        localSchemaObjectSet = localSchema.procedureLookup;
        localObject2 = (RoutineSchema)localSchemaObjectSet.getObject(paramHsqlName.name);
        localObject1 = localObject2;
        arrayOfRoutine = ((RoutineSchema)localObject2).getSpecificRoutines();
        for (i = 0; i < arrayOfRoutine.length; i++)
          removeSchemaObject(arrayOfRoutine[i].getSpecificName());
        break;
      case 16:
        localSchemaObjectSet = localSchema.functionLookup;
        localObject2 = (RoutineSchema)localSchemaObjectSet.getObject(paramHsqlName.name);
        localObject1 = localObject2;
        arrayOfRoutine = ((RoutineSchema)localObject2).getSpecificRoutines();
        for (i = 0; i < arrayOfRoutine.length; i++)
          removeSchemaObject(arrayOfRoutine[i].getSpecificName());
        break;
      case 24:
        localSchemaObjectSet = localSchema.specificRoutineLookup;
        localObject2 = (Routine)localSchemaObjectSet.getObject(paramHsqlName.name);
        localObject1 = localObject2;
        ((Routine)localObject2).routineSchema.removeSpecificRoutine((Routine)localObject2);
        if (((Routine)localObject2).routineSchema.getSpecificRoutines().length == 0)
          removeSchemaObject(((Routine)localObject2).getName());
        break;
      case 12:
      case 13:
        localSchemaObjectSet = localSchema.typeLookup;
        localObject1 = localSchemaObjectSet.getObject(paramHsqlName.name);
        break;
      case 20:
        localSchemaObjectSet = localSchema.indexLookup;
        break;
      case 5:
        localSchemaObjectSet = localSchema.constraintLookup;
        if (paramHsqlName.parent.type == 3)
        {
          localObject2 = (Table)localSchema.tableList.get(paramHsqlName.parent.name);
          localObject1 = ((Table)localObject2).getConstraint(paramHsqlName.name);
          ((Table)localObject2).removeConstraint(paramHsqlName.name);
        }
        else if (paramHsqlName.parent.type == 13)
        {
          localObject2 = (Type)localSchema.typeLookup.getObject(paramHsqlName.parent.name);
          localObject1 = ((Type)localObject2).userTypeModifier.getConstraint(paramHsqlName.name);
          ((Type)localObject2).userTypeModifier.removeConstraint(paramHsqlName.name);
        }
        break;
      case 8:
        localSchemaObjectSet = localSchema.triggerLookup;
        localObject2 = (Table)localSchema.tableList.get(paramHsqlName.parent.name);
        localObject1 = ((Table)localObject2).getTrigger(paramHsqlName.name);
        if (localObject1 != null)
          ((Table)localObject2).removeTrigger((TriggerDef)localObject1);
        break;
      case 6:
      case 10:
      case 11:
      case 18:
      case 19:
      case 21:
      case 22:
      case 23:
      default:
        throw Error.runtimeError(201, "SchemaManager");
      }
      if (localObject1 != null)
      {
        this.database.getGranteeManager().removeDbObject(paramHsqlName);
        removeReferencesFrom((SchemaObject)localObject1);
      }
      if (localSchemaObjectSet != null)
        localSchemaObjectSet.remove(paramHsqlName.name);
      removeReferencesTo(paramHsqlName);
    }
    finally
    {
      this.writeLock.unlock();
    }
  }

  public void renameSchemaObject(HsqlNameManager.HsqlName paramHsqlName1, HsqlNameManager.HsqlName paramHsqlName2)
  {
    this.writeLock.lock();
    try
    {
      if (paramHsqlName1.schema != paramHsqlName2.schema)
        throw Error.error(5505, paramHsqlName2.schema.name);
      checkObjectIsReferenced(paramHsqlName1);
      Schema localSchema = (Schema)this.schemaMap.get(paramHsqlName1.schema.name);
      SchemaObjectSet localSchemaObjectSet = getSchemaObjectSet(localSchema, paramHsqlName1.type);
      localSchemaObjectSet.rename(paramHsqlName1, paramHsqlName2);
    }
    finally
    {
      this.writeLock.unlock();
    }
  }

  public void replaceReferences(SchemaObject paramSchemaObject1, SchemaObject paramSchemaObject2)
  {
    this.writeLock.lock();
    try
    {
      removeReferencesFrom(paramSchemaObject1);
      addReferencesFrom(paramSchemaObject2);
    }
    finally
    {
      this.writeLock.unlock();
    }
  }

  public String[] getSQLArray()
  {
    this.readLock.lock();
    try
    {
      OrderedHashSet localOrderedHashSet1 = new OrderedHashSet();
      OrderedHashSet localOrderedHashSet2 = new OrderedHashSet();
      HsqlArrayList localHsqlArrayList = new HsqlArrayList();
      Iterator localIterator = this.schemaMap.values().iterator();
      localIterator = this.schemaMap.values().iterator();
      while (localIterator.hasNext())
      {
        localObject1 = (Schema)localIterator.next();
        if ((!SqlInvariants.isSystemSchemaName(((Schema)localObject1).getName().name)) && (!SqlInvariants.isLobsSchemaName(((Schema)localObject1).getName().name)))
        {
          localHsqlArrayList.add(((Schema)localObject1).getSQL());
          ((Schema)localObject1).addSimpleObjects(localOrderedHashSet2);
        }
      }
      while (true)
      {
        localObject1 = localOrderedHashSet2.iterator();
        if (!((Iterator)localObject1).hasNext())
          break;
        localObject2 = new OrderedHashSet();
        SchemaObjectSet.addAllSQL(localOrderedHashSet1, localOrderedHashSet2, localHsqlArrayList, (Iterator)localObject1, (OrderedHashSet)localObject2);
        localOrderedHashSet2.removeAll((Collection)localObject2);
        if (((OrderedHashSet)localObject2).size() == 0)
          break;
      }
      localIterator = this.schemaMap.values().iterator();
      while (localIterator.hasNext())
      {
        localObject1 = (Schema)localIterator.next();
        if ((!SqlInvariants.isLobsSchemaName(((Schema)localObject1).getName().name)) && (!SqlInvariants.isSystemSchemaName(((Schema)localObject1).getName().name)))
          localHsqlArrayList.addAll(((Schema)localObject1).getSQLArray(localOrderedHashSet1, localOrderedHashSet2));
      }
      while (true)
      {
        localObject1 = localOrderedHashSet2.iterator();
        if (!((Iterator)localObject1).hasNext())
          break;
        localObject2 = new OrderedHashSet();
        SchemaObjectSet.addAllSQL(localOrderedHashSet1, localOrderedHashSet2, localHsqlArrayList, (Iterator)localObject1, (OrderedHashSet)localObject2);
        localOrderedHashSet2.removeAll((Collection)localObject2);
        if (((OrderedHashSet)localObject2).size() == 0)
          break;
      }
      Object localObject1 = localOrderedHashSet2.iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (SchemaObject)((Iterator)localObject1).next();
        if ((localObject2 instanceof Routine))
          localHsqlArrayList.add(((Routine)localObject2).getSQLDeclaration());
      }
      localObject1 = localOrderedHashSet2.iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (SchemaObject)((Iterator)localObject1).next();
        if ((localObject2 instanceof Routine))
          localHsqlArrayList.add(((Routine)localObject2).getSQLAlter());
        else
          localHsqlArrayList.add(((SchemaObject)localObject2).getSQL());
      }
      localIterator = this.schemaMap.values().iterator();
      while (localIterator.hasNext())
      {
        localObject2 = (Schema)localIterator.next();
        if ((!SqlInvariants.isLobsSchemaName(((Schema)localObject2).getName().name)) && (!SqlInvariants.isSystemSchemaName(((Schema)localObject2).getName().name)))
        {
          localObject3 = ((Schema)localObject2).getTriggerSQL();
          if (localObject3.length > 0)
          {
            localHsqlArrayList.add(Schema.getSetSchemaSQL(((Schema)localObject2).getName()));
            localHsqlArrayList.addAll((Object[])localObject3);
          }
        }
      }
      localIterator = this.schemaMap.values().iterator();
      while (localIterator.hasNext())
      {
        localObject2 = (Schema)localIterator.next();
        localHsqlArrayList.addAll(((Schema)localObject2).getSequenceRestartSQL());
      }
      if (this.defaultSchemaHsqlName != null)
      {
        localObject2 = new StringBuffer();
        ((StringBuffer)localObject2).append("SET").append(' ').append("DATABASE");
        ((StringBuffer)localObject2).append(' ').append("DEFAULT").append(' ');
        ((StringBuffer)localObject2).append("INITIAL").append(' ').append("SCHEMA");
        ((StringBuffer)localObject2).append(' ').append(this.defaultSchemaHsqlName.statementName);
        localHsqlArrayList.add(((StringBuffer)localObject2).toString());
      }
      Object localObject2 = new String[localHsqlArrayList.size()];
      localHsqlArrayList.toArray(localObject2);
      Object localObject3 = localObject2;
      return localObject3;
    }
    finally
    {
      this.readLock.unlock();
    }
  }

  public String[] getTablePropsSQL(boolean paramBoolean)
  {
    this.readLock.lock();
    try
    {
      HsqlArrayList localHsqlArrayList1 = getAllTables(false);
      HsqlArrayList localHsqlArrayList2 = new HsqlArrayList();
      for (int i = 0; i < localHsqlArrayList1.size(); i++)
      {
        localObject1 = (Table)localHsqlArrayList1.get(i);
        if (((Table)localObject1).isText())
        {
          localObject2 = ((Table)localObject1).getSQLForTextSource(paramBoolean);
          localHsqlArrayList2.addAll((Object[])localObject2);
        }
        Object localObject2 = ((Table)localObject1).getSQLForReadOnly();
        if (localObject2 != null)
          localHsqlArrayList2.add(localObject2);
        if (((Table)localObject1).isCached())
        {
          localObject2 = ((Table)localObject1).getSQLForClustered();
          if (localObject2 != null)
            localHsqlArrayList2.add(localObject2);
        }
      }
      String[] arrayOfString = new String[localHsqlArrayList2.size()];
      localHsqlArrayList2.toArray(arrayOfString);
      Object localObject1 = arrayOfString;
      return localObject1;
    }
    finally
    {
      this.readLock.unlock();
    }
  }

  public String[] getIndexRootsSQL()
  {
    this.readLock.lock();
    try
    {
      Session localSession = this.database.sessionManager.getSysSession();
      long[][] arrayOfLong = getIndexRoots(localSession);
      HsqlArrayList localHsqlArrayList1 = getAllTables(true);
      HsqlArrayList localHsqlArrayList2 = new HsqlArrayList();
      for (int i = 0; i < arrayOfLong.length; i++)
      {
        localObject1 = (Table)localHsqlArrayList1.get(i);
        if ((arrayOfLong[i] != null) && (arrayOfLong[i].length > 0) && (arrayOfLong[i][0] != -1L))
        {
          String str = ((Table)localHsqlArrayList1.get(i)).getIndexRootsSQL(arrayOfLong[i]);
          localHsqlArrayList2.add(str);
        }
      }
      String[] arrayOfString = new String[localHsqlArrayList2.size()];
      localHsqlArrayList2.toArray(arrayOfString);
      Object localObject1 = arrayOfString;
      return localObject1;
    }
    finally
    {
      this.readLock.unlock();
    }
  }

  public String[] getCommentsArray()
  {
    this.readLock.lock();
    try
    {
      HsqlArrayList localHsqlArrayList1 = getAllTables(false);
      HsqlArrayList localHsqlArrayList2 = new HsqlArrayList();
      StringBuffer localStringBuffer = new StringBuffer();
      for (int i = 0; i < localHsqlArrayList1.size(); i++)
      {
        localObject1 = (Table)localHsqlArrayList1.get(i);
        if (((Table)localObject1).getTableType() != 1)
        {
          int j = ((Table)localObject1).getColumnCount();
          for (int k = 0; k < j; k++)
          {
            ColumnSchema localColumnSchema = ((Table)localObject1).getColumn(k);
            if (localColumnSchema.getName().comment != null)
            {
              localStringBuffer.setLength(0);
              localStringBuffer.append("COMMENT").append(' ').append("ON");
              localStringBuffer.append(' ').append("COLUMN").append(' ');
              localStringBuffer.append(((Table)localObject1).getName().getSchemaQualifiedStatementName());
              localStringBuffer.append('.').append(localColumnSchema.getName().statementName);
              localStringBuffer.append(' ').append("IS").append(' ');
              localStringBuffer.append(StringConverter.toQuotedString(localColumnSchema.getName().comment, '\'', true));
              localHsqlArrayList2.add(localStringBuffer.toString());
            }
          }
          if (((Table)localObject1).getName().comment != null)
          {
            localStringBuffer.setLength(0);
            localStringBuffer.append("COMMENT").append(' ').append("ON");
            localStringBuffer.append(' ').append("TABLE").append(' ');
            localStringBuffer.append(((Table)localObject1).getName().getSchemaQualifiedStatementName());
            localStringBuffer.append(' ').append("IS").append(' ');
            localStringBuffer.append(StringConverter.toQuotedString(((Table)localObject1).getName().comment, '\'', true));
            localHsqlArrayList2.add(localStringBuffer.toString());
          }
        }
      }
      Iterator localIterator = databaseObjectIterator(18);
      while (localIterator.hasNext())
      {
        localObject1 = (SchemaObject)localIterator.next();
        if (((SchemaObject)localObject1).getName().comment != null)
        {
          localStringBuffer.setLength(0);
          localStringBuffer.append("COMMENT").append(' ').append("ON");
          localStringBuffer.append(' ').append("ROUTINE").append(' ');
          localStringBuffer.append(((SchemaObject)localObject1).getName().getSchemaQualifiedStatementName());
          localStringBuffer.append(' ').append("IS").append(' ');
          localStringBuffer.append(StringConverter.toQuotedString(((SchemaObject)localObject1).getName().comment, '\'', true));
          localHsqlArrayList2.add(localStringBuffer.toString());
        }
      }
      Object localObject1 = new String[localHsqlArrayList2.size()];
      localHsqlArrayList2.toArray(localObject1);
      Object localObject2 = localObject1;
      return localObject2;
    }
    finally
    {
      this.readLock.unlock();
    }
  }

  public void setTempIndexRoots(long[][] paramArrayOfLong)
  {
    this.tempIndexRoots = paramArrayOfLong;
  }

  public long[][] getIndexRoots(Session paramSession)
  {
    this.readLock.lock();
    try
    {
      if (this.tempIndexRoots != null)
      {
        localObject1 = this.tempIndexRoots;
        this.tempIndexRoots = ((long[][])null);
        localObject2 = localObject1;
        return localObject2;
      }
      Object localObject1 = getAllTables(true);
      Object localObject2 = new HsqlArrayList();
      int i = 0;
      int j = ((HsqlArrayList)localObject1).size();
      while (i < j)
      {
        Table localTable = (Table)((HsqlArrayList)localObject1).get(i);
        if (localTable.getTableType() == 5)
        {
          long[] arrayOfLong = localTable.getIndexRootsArray();
          ((HsqlArrayList)localObject2).add(arrayOfLong);
        }
        else
        {
          ((HsqlArrayList)localObject2).add(null);
        }
        i++;
      }
      long[][] arrayOfLong1 = new long[((HsqlArrayList)localObject2).size()][];
      ((HsqlArrayList)localObject2).toArray(arrayOfLong1);
      long[][] arrayOfLong2 = arrayOfLong1;
      return arrayOfLong2;
    }
    finally
    {
      this.readLock.unlock();
    }
  }

  public void setIndexRoots(long[][] paramArrayOfLong)
  {
    this.readLock.lock();
    try
    {
      HsqlArrayList localHsqlArrayList = this.database.schemaManager.getAllTables(true);
      int i = 0;
      int j = localHsqlArrayList.size();
      while (i < j)
      {
        Table localTable = (Table)localHsqlArrayList.get(i);
        if (localTable.getTableType() == 5)
        {
          long[] arrayOfLong = paramArrayOfLong[i];
          if (paramArrayOfLong != null)
            localTable.setIndexRoots(arrayOfLong);
        }
        i++;
      }
    }
    finally
    {
      this.readLock.unlock();
    }
  }

  public void setDefaultTableType(int paramInt)
  {
    this.defaultTableType = paramInt;
  }

  public int getDefaultTableType()
  {
    return this.defaultTableType;
  }

  public void createSystemTables()
  {
    this.dualTable = TableUtil.newSingleColumnTable(this.database, SqlInvariants.DUAL_TABLE_HSQLNAME, 12, SqlInvariants.DUAL_COLUMN_HSQLNAME, Type.SQL_VARCHAR);
    this.dualTable.insertSys(this.database.sessionManager.getSysSession(), this.dualTable.getRowStore(null), new Object[] { "X" });
    this.dualTable.setDataReadOnly(true);
    Type[] arrayOfType = { Type.SQL_BIGINT, Type.SQL_BIGINT, Type.SQL_BIGINT, TypeInvariants.SQL_IDENTIFIER, TypeInvariants.SQL_IDENTIFIER, Type.SQL_BOOLEAN };
    HsqlNameManager.HsqlName localHsqlName1 = this.database.nameManager.getSubqueryTableName();
    HashMappedList localHashMappedList = new HashMappedList();
    for (int i = 0; i < arrayOfType.length; i++)
    {
      HsqlNameManager.HsqlName localHsqlName2 = HsqlNameManager.getAutoColumnName(i + 1);
      ColumnSchema localColumnSchema = new ColumnSchema(localHsqlName2, arrayOfType[i], true, false, null);
      localHashMappedList.add(localHsqlName2.name, localColumnSchema);
    }
    this.dataChangeTable = new TableDerived(this.database, localHsqlName1, 13, arrayOfType, localHashMappedList, new int[] { 0 });
    this.dataChangeTable.createIndexForColumns(null, new int[] { 1 });
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.SchemaManager
 * JD-Core Version:    0.6.2
 */