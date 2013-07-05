package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.Collection;
import org.hsqldb.lib.HashMap;
import org.hsqldb.lib.HashMappedList;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.lib.Iterator;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.store.ValuePool;

public class SchemaObjectSet
{
  HashMap map;
  int type;

  SchemaObjectSet(int paramInt)
  {
    this.type = paramInt;
    switch (paramInt)
    {
    case 3:
    case 4:
    case 6:
    case 7:
    case 8:
    case 12:
    case 13:
    case 14:
    case 15:
    case 16:
    case 17:
    case 24:
      this.map = new HashMappedList();
      break;
    case 5:
    case 9:
    case 20:
      this.map = new HashMap();
    case 10:
    case 11:
    case 18:
    case 19:
    case 21:
    case 22:
    case 23:
    }
  }

  HsqlNameManager.HsqlName getName(String paramString)
  {
    switch (this.type)
    {
    case 3:
    case 4:
    case 6:
    case 7:
    case 8:
    case 12:
    case 13:
    case 14:
    case 15:
    case 16:
    case 17:
    case 24:
      SchemaObject localSchemaObject = (SchemaObject)this.map.get(paramString);
      return localSchemaObject == null ? null : localSchemaObject.getName();
    case 5:
    case 9:
    case 20:
      return (HsqlNameManager.HsqlName)this.map.get(paramString);
    case 10:
    case 11:
    case 18:
    case 19:
    case 21:
    case 22:
    case 23:
    }
    return (HsqlNameManager.HsqlName)this.map.get(paramString);
  }

  public SchemaObject getObject(String paramString)
  {
    switch (this.type)
    {
    case 3:
    case 4:
    case 6:
    case 7:
    case 8:
    case 9:
    case 12:
    case 13:
    case 14:
    case 15:
    case 16:
    case 17:
    case 24:
      return (SchemaObject)this.map.get(paramString);
    case 5:
    case 10:
    case 11:
    case 18:
    case 19:
    case 20:
    case 21:
    case 22:
    case 23:
    }
    throw Error.runtimeError(201, "SchemaObjectSet");
  }

  public boolean contains(String paramString)
  {
    return this.map.containsKey(paramString);
  }

  void checkAdd(HsqlNameManager.HsqlName paramHsqlName)
  {
    if (this.map.containsKey(paramHsqlName.name))
    {
      int i = getAddErrorCode(paramHsqlName.type);
      throw Error.error(i, paramHsqlName.name);
    }
  }

  boolean isEmpty()
  {
    return this.map.isEmpty();
  }

  void checkExists(String paramString)
  {
    if (!this.map.containsKey(paramString))
    {
      int i = getGetErrorCode(this.type);
      throw Error.error(i, paramString);
    }
  }

  public void add(SchemaObject paramSchemaObject)
  {
    HsqlNameManager.HsqlName localHsqlName = paramSchemaObject.getName();
    if (this.type == 24)
      localHsqlName = ((Routine)paramSchemaObject).getSpecificName();
    if (this.map.containsKey(localHsqlName.name))
    {
      int i = getAddErrorCode(localHsqlName.type);
      throw Error.error(i, localHsqlName.name);
    }
    Object localObject = paramSchemaObject;
    switch (localHsqlName.type)
    {
    case 5:
    case 20:
      localObject = localHsqlName;
    }
    this.map.put(localHsqlName.name, localObject);
  }

  void remove(String paramString)
  {
    this.map.remove(paramString);
  }

  void removeParent(HsqlNameManager.HsqlName paramHsqlName)
  {
    Iterator localIterator = this.map.values().iterator();
    while (localIterator.hasNext())
    {
      Object localObject;
      if ((this.type == 8) || (this.type == 24))
      {
        localObject = (SchemaObject)localIterator.next();
        if (((SchemaObject)localObject).getName().parent == paramHsqlName)
          localIterator.remove();
      }
      else
      {
        localObject = (HsqlNameManager.HsqlName)localIterator.next();
        if (((HsqlNameManager.HsqlName)localObject).parent == paramHsqlName)
          localIterator.remove();
      }
    }
  }

  void rename(HsqlNameManager.HsqlName paramHsqlName1, HsqlNameManager.HsqlName paramHsqlName2)
  {
    int i;
    if (this.map.containsKey(paramHsqlName2.name))
    {
      i = getAddErrorCode(paramHsqlName1.type);
      throw Error.error(i, paramHsqlName2.name);
    }
    switch (paramHsqlName2.type)
    {
    case 3:
    case 4:
    case 6:
    case 7:
    case 8:
    case 12:
    case 13:
    case 14:
    case 15:
    case 16:
    case 17:
      i = ((HashMappedList)this.map).getIndex(paramHsqlName1.name);
      if (i == -1)
      {
        int j = getGetErrorCode(paramHsqlName1.type);
        throw Error.error(j, paramHsqlName1.name);
      }
      SchemaObject localSchemaObject = (SchemaObject)((HashMappedList)this.map).get(i);
      localSchemaObject.getName().rename(paramHsqlName2);
      ((HashMappedList)this.map).setKey(i, paramHsqlName1.name);
      break;
    case 5:
    case 9:
    case 20:
      this.map.remove(paramHsqlName1.name);
      paramHsqlName1.rename(paramHsqlName2);
      this.map.put(paramHsqlName1.name, paramHsqlName1);
    case 10:
    case 11:
    case 18:
    case 19:
    }
  }

  static int getAddErrorCode(int paramInt)
  {
    int i;
    switch (paramInt)
    {
    case 3:
    case 4:
    case 5:
    case 6:
    case 7:
    case 8:
    case 9:
    case 12:
    case 13:
    case 14:
    case 15:
    case 16:
    case 17:
    case 20:
    case 24:
      i = 5504;
      break;
    case 10:
    case 11:
    case 18:
    case 19:
    case 21:
    case 22:
    case 23:
    default:
      throw Error.runtimeError(201, "SchemaObjectSet");
    }
    return i;
  }

  static int getGetErrorCode(int paramInt)
  {
    int i;
    switch (paramInt)
    {
    case 3:
    case 4:
    case 5:
    case 6:
    case 7:
    case 8:
    case 9:
    case 12:
    case 13:
    case 14:
    case 15:
    case 16:
    case 17:
    case 20:
    case 24:
      i = 5501;
      break;
    case 10:
    case 11:
    case 18:
    case 19:
    case 21:
    case 22:
    case 23:
    default:
      throw Error.runtimeError(201, "SchemaObjectSet");
    }
    return i;
  }

  public static String getName(int paramInt)
  {
    switch (paramInt)
    {
    case 4:
      return "VIEW";
    case 9:
      return "COLUMN";
    case 3:
      return "TABLE";
    case 7:
      return "SEQUENCE";
    case 14:
      return "CHARACTER SET";
    case 13:
      return "DOMAIN";
    case 12:
      return "TYPE";
    case 5:
      return "CONSTRAINT";
    case 15:
      return "COLLATION";
    case 17:
      return "PROCEDURE";
    case 16:
      return "FUNCTION";
    case 6:
      return "ASSERTION";
    case 20:
      return "INDEX";
    case 8:
      return "TRIGGER";
    case 10:
    case 11:
    case 18:
    case 19:
    }
    throw Error.runtimeError(201, "SchemaObjectSet");
  }

  String[] getSQL(OrderedHashSet paramOrderedHashSet1, OrderedHashSet paramOrderedHashSet2)
  {
    HsqlArrayList localHsqlArrayList = new HsqlArrayList();
    if (!(this.map instanceof HashMappedList))
      return null;
    if (this.map.isEmpty())
      return ValuePool.emptyStringArray;
    Iterator localIterator = this.map.values().iterator();
    if ((this.type == 16) || (this.type == 17))
    {
      localObject = new OrderedHashSet();
      while (localIterator.hasNext())
      {
        RoutineSchema localRoutineSchema = (RoutineSchema)localIterator.next();
        for (int i = 0; i < localRoutineSchema.routines.length; i++)
        {
          Routine localRoutine = localRoutineSchema.routines[i];
          if ((localRoutine.dataImpact != 1) && (localRoutine.dataImpact != 2))
            ((OrderedHashSet)localObject).add(localRoutine);
        }
      }
      localIterator = ((OrderedHashSet)localObject).iterator();
    }
    addAllSQL(paramOrderedHashSet1, paramOrderedHashSet2, localHsqlArrayList, localIterator, null);
    Object localObject = new String[localHsqlArrayList.size()];
    localHsqlArrayList.toArray(localObject);
    return localObject;
  }

  static void addAllSQL(OrderedHashSet paramOrderedHashSet1, OrderedHashSet paramOrderedHashSet2, HsqlArrayList paramHsqlArrayList, Iterator paramIterator, OrderedHashSet paramOrderedHashSet3)
  {
    while (paramIterator.hasNext())
    {
      SchemaObject localSchemaObject = (SchemaObject)paramIterator.next();
      OrderedHashSet localOrderedHashSet = localSchemaObject.getReferences();
      int i = 1;
      for (int j = 0; j < localOrderedHashSet.size(); j++)
      {
        HsqlNameManager.HsqlName localHsqlName2 = (HsqlNameManager.HsqlName)localOrderedHashSet.get(j);
        if (!SqlInvariants.isSchemaNameSystem(localHsqlName2))
          switch (localHsqlName2.type)
          {
          case 3:
            if (!paramOrderedHashSet1.contains(localHsqlName2))
              i = 0;
            break;
          case 9:
            if (localSchemaObject.getType() == 3)
            {
              int k = ((Table)localSchemaObject).findColumn(localHsqlName2.name);
              ColumnSchema localColumnSchema = ((Table)localSchemaObject).getColumn(k);
              if (!isChildObjectResolved(localColumnSchema, paramOrderedHashSet1))
                i = 0;
            }
            else if (!paramOrderedHashSet1.contains(localHsqlName2.parent))
            {
              i = 0;
            }
            break;
          case 5:
            if (localHsqlName2.parent == localSchemaObject.getName())
            {
              Constraint localConstraint = ((Table)localSchemaObject).getConstraint(localHsqlName2.name);
              if ((localConstraint.getConstraintType() == 3) && (!isChildObjectResolved(localConstraint, paramOrderedHashSet1)))
                i = 0;
            }
            break;
          case 14:
          case 12:
          case 13:
          case 16:
          case 17:
          case 24:
            if ((localHsqlName2.schema != null) && (!paramOrderedHashSet1.contains(localHsqlName2)))
              i = 0;
            break;
          case 4:
          case 6:
          case 7:
          case 8:
          case 10:
          case 11:
          case 15:
          case 18:
          case 19:
          case 20:
          case 21:
          case 22:
          case 23:
          }
      }
      if (i == 0)
      {
        paramOrderedHashSet2.add(localSchemaObject);
      }
      else
      {
        HsqlNameManager.HsqlName localHsqlName1;
        if ((localSchemaObject.getType() == 16) || (localSchemaObject.getType() == 17))
          localHsqlName1 = ((Routine)localSchemaObject).getSpecificName();
        else
          localHsqlName1 = localSchemaObject.getName();
        paramOrderedHashSet1.add(localHsqlName1);
        if (paramOrderedHashSet3 != null)
          paramOrderedHashSet3.add(localSchemaObject);
        if (localSchemaObject.getType() == 3)
        {
          paramHsqlArrayList.addAll(((Table)localSchemaObject).getSQL(paramOrderedHashSet1, paramOrderedHashSet2));
        }
        else
        {
          switch (localSchemaObject.getType())
          {
          case 16:
          case 17:
            if (((Routine)localSchemaObject).isRecursive)
            {
              paramHsqlArrayList.add(((Routine)localSchemaObject).getSQLDeclaration());
              paramHsqlArrayList.add(((Routine)localSchemaObject).getSQLAlter());
            }
            break;
          }
          paramHsqlArrayList.add(localSchemaObject.getSQL());
        }
      }
    }
  }

  static boolean isChildObjectResolved(SchemaObject paramSchemaObject, OrderedHashSet paramOrderedHashSet)
  {
    OrderedHashSet localOrderedHashSet = paramSchemaObject.getReferences();
    for (int i = 0; i < localOrderedHashSet.size(); i++)
    {
      HsqlNameManager.HsqlName localHsqlName = (HsqlNameManager.HsqlName)localOrderedHashSet.get(i);
      if ((!SqlInvariants.isSchemaNameSystem(localHsqlName)) && (!paramOrderedHashSet.contains(localHsqlName)))
        return false;
    }
    return true;
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.SchemaObjectSet
 * JD-Core Version:    0.6.2
 */