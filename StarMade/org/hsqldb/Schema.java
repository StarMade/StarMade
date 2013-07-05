package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.Collection;
import org.hsqldb.lib.HashMap;
import org.hsqldb.lib.HashMappedList;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.lib.Iterator;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.lib.WrapperIterator;
import org.hsqldb.rights.Grantee;

public final class Schema
  implements SchemaObject
{
  private HsqlNameManager.HsqlName name;
  SchemaObjectSet triggerLookup;
  SchemaObjectSet constraintLookup;
  SchemaObjectSet indexLookup;
  SchemaObjectSet tableLookup;
  SchemaObjectSet sequenceLookup;
  SchemaObjectSet typeLookup;
  SchemaObjectSet charsetLookup;
  SchemaObjectSet collationLookup;
  SchemaObjectSet procedureLookup;
  SchemaObjectSet functionLookup;
  SchemaObjectSet specificRoutineLookup;
  SchemaObjectSet assertionLookup;
  HashMappedList tableList;
  HashMappedList sequenceList;
  long changeTimestamp;

  public Schema(HsqlNameManager.HsqlName paramHsqlName, Grantee paramGrantee)
  {
    this.name = paramHsqlName;
    this.triggerLookup = new SchemaObjectSet(8);
    this.indexLookup = new SchemaObjectSet(20);
    this.constraintLookup = new SchemaObjectSet(5);
    this.tableLookup = new SchemaObjectSet(3);
    this.sequenceLookup = new SchemaObjectSet(7);
    this.typeLookup = new SchemaObjectSet(12);
    this.charsetLookup = new SchemaObjectSet(14);
    this.collationLookup = new SchemaObjectSet(15);
    this.procedureLookup = new SchemaObjectSet(17);
    this.functionLookup = new SchemaObjectSet(16);
    this.specificRoutineLookup = new SchemaObjectSet(24);
    this.assertionLookup = new SchemaObjectSet(6);
    this.tableList = ((HashMappedList)this.tableLookup.map);
    this.sequenceList = ((HashMappedList)this.sequenceLookup.map);
    paramHsqlName.owner = paramGrantee;
  }

  public int getType()
  {
    return 2;
  }

  public HsqlNameManager.HsqlName getName()
  {
    return this.name;
  }

  public HsqlNameManager.HsqlName getSchemaName()
  {
    return null;
  }

  public HsqlNameManager.HsqlName getCatalogName()
  {
    return null;
  }

  public Grantee getOwner()
  {
    return this.name.owner;
  }

  public OrderedHashSet getReferences()
  {
    return new OrderedHashSet();
  }

  public OrderedHashSet getComponents()
  {
    return null;
  }

  public void compile(Session paramSession, SchemaObject paramSchemaObject)
  {
  }

  public long getChangeTimestamp()
  {
    return this.changeTimestamp;
  }

  public String getSQL()
  {
    StringBuffer localStringBuffer = new StringBuffer(128);
    localStringBuffer.append("CREATE").append(' ');
    localStringBuffer.append("SCHEMA").append(' ');
    localStringBuffer.append(getName().statementName).append(' ');
    localStringBuffer.append("AUTHORIZATION").append(' ');
    localStringBuffer.append(getOwner().getName().getStatementName());
    return localStringBuffer.toString();
  }

  static String getSetSchemaSQL(HsqlNameManager.HsqlName paramHsqlName)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("SET").append(' ');
    localStringBuffer.append("SCHEMA").append(' ');
    localStringBuffer.append(paramHsqlName.statementName);
    return localStringBuffer.toString();
  }

  public String[] getSQLArray(OrderedHashSet paramOrderedHashSet1, OrderedHashSet paramOrderedHashSet2)
  {
    HsqlArrayList localHsqlArrayList = new HsqlArrayList();
    String str = getSetSchemaSQL(this.name);
    localHsqlArrayList.add(str);
    String[] arrayOfString1 = this.sequenceLookup.getSQL(paramOrderedHashSet1, paramOrderedHashSet2);
    localHsqlArrayList.addAll(arrayOfString1);
    arrayOfString1 = this.tableLookup.getSQL(paramOrderedHashSet1, paramOrderedHashSet2);
    localHsqlArrayList.addAll(arrayOfString1);
    arrayOfString1 = this.functionLookup.getSQL(paramOrderedHashSet1, paramOrderedHashSet2);
    localHsqlArrayList.addAll(arrayOfString1);
    arrayOfString1 = this.procedureLookup.getSQL(paramOrderedHashSet1, paramOrderedHashSet2);
    localHsqlArrayList.addAll(arrayOfString1);
    arrayOfString1 = this.assertionLookup.getSQL(paramOrderedHashSet1, paramOrderedHashSet2);
    localHsqlArrayList.addAll(arrayOfString1);
    if (localHsqlArrayList.size() == 1)
      return new String[0];
    String[] arrayOfString2 = new String[localHsqlArrayList.size()];
    localHsqlArrayList.toArray(arrayOfString2);
    return arrayOfString2;
  }

  public String[] getSequenceRestartSQL()
  {
    HsqlArrayList localHsqlArrayList = new HsqlArrayList();
    Iterator localIterator = this.sequenceLookup.map.values().iterator();
    while (localIterator.hasNext())
    {
      localObject = (NumberSequence)localIterator.next();
      String str = ((NumberSequence)localObject).getRestartSQL();
      localHsqlArrayList.add(str);
    }
    Object localObject = new String[localHsqlArrayList.size()];
    localHsqlArrayList.toArray(localObject);
    return localObject;
  }

  public String[] getTriggerSQL()
  {
    HsqlArrayList localHsqlArrayList = new HsqlArrayList();
    Iterator localIterator = this.tableLookup.map.values().iterator();
    while (localIterator.hasNext())
    {
      localObject = (Table)localIterator.next();
      String[] arrayOfString = ((Table)localObject).getTriggerSQL();
      localHsqlArrayList.addAll(arrayOfString);
    }
    Object localObject = new String[localHsqlArrayList.size()];
    localHsqlArrayList.toArray(localObject);
    return localObject;
  }

  public void addSimpleObjects(OrderedHashSet paramOrderedHashSet)
  {
    Iterator localIterator = this.specificRoutineLookup.map.values().iterator();
    while (localIterator.hasNext())
    {
      Routine localRoutine = (Routine)localIterator.next();
      if ((localRoutine.dataImpact == 1) || (localRoutine.dataImpact == 2))
        paramOrderedHashSet.add(localRoutine);
    }
    paramOrderedHashSet.addAll(this.typeLookup.map.values());
    paramOrderedHashSet.addAll(this.charsetLookup.map.values());
    paramOrderedHashSet.addAll(this.collationLookup.map.values());
  }

  boolean isEmpty()
  {
    return (this.sequenceLookup.isEmpty()) && (this.tableLookup.isEmpty()) && (this.typeLookup.isEmpty()) && (this.charsetLookup.isEmpty()) && (this.collationLookup.isEmpty()) && (this.specificRoutineLookup.isEmpty());
  }

  public SchemaObjectSet getObjectSet(int paramInt)
  {
    switch (paramInt)
    {
    case 7:
      return this.sequenceLookup;
    case 3:
    case 4:
      return this.tableLookup;
    case 14:
      return this.charsetLookup;
    case 15:
      return this.collationLookup;
    case 17:
      return this.procedureLookup;
    case 16:
      return this.functionLookup;
    case 18:
      return this.functionLookup;
    case 24:
      return this.specificRoutineLookup;
    case 12:
    case 13:
      return this.typeLookup;
    case 6:
      return this.assertionLookup;
    case 8:
      return this.triggerLookup;
    case 20:
      return this.indexLookup;
    case 5:
      return this.constraintLookup;
    case 9:
    case 10:
    case 11:
    case 19:
    case 21:
    case 22:
    case 23:
    }
    throw Error.runtimeError(201, "Schema");
  }

  Iterator schemaObjectIterator(int paramInt)
  {
    switch (paramInt)
    {
    case 7:
      return this.sequenceLookup.map.values().iterator();
    case 3:
    case 4:
      return this.tableLookup.map.values().iterator();
    case 14:
      return this.charsetLookup.map.values().iterator();
    case 15:
      return this.collationLookup.map.values().iterator();
    case 17:
      return this.procedureLookup.map.values().iterator();
    case 16:
      return this.functionLookup.map.values().iterator();
    case 18:
      Iterator localIterator = this.functionLookup.map.values().iterator();
      return new WrapperIterator(localIterator, this.procedureLookup.map.values().iterator());
    case 24:
      return this.specificRoutineLookup.map.values().iterator();
    case 12:
    case 13:
      return this.typeLookup.map.values().iterator();
    case 6:
      return this.assertionLookup.map.values().iterator();
    case 8:
      return this.triggerLookup.map.values().iterator();
    case 20:
      return this.indexLookup.map.values().iterator();
    case 5:
      return this.constraintLookup.map.values().iterator();
    case 9:
    case 10:
    case 11:
    case 19:
    case 21:
    case 22:
    case 23:
    }
    throw Error.runtimeError(201, "Schema");
  }

  void release()
  {
    this.tableList.clear();
    this.sequenceList.clear();
    this.triggerLookup = null;
    this.indexLookup = null;
    this.constraintLookup = null;
    this.procedureLookup = null;
    this.functionLookup = null;
    this.sequenceLookup = null;
    this.tableLookup = null;
    this.typeLookup = null;
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.Schema
 * JD-Core Version:    0.6.2
 */