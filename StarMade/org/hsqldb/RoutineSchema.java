package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.rights.Grantee;
import org.hsqldb.types.Type;

public class RoutineSchema
  implements SchemaObject
{
  Routine[] routines = Routine.emptyArray;
  int routineType;
  private HsqlNameManager.HsqlName name;

  public RoutineSchema(int paramInt, HsqlNameManager.HsqlName paramHsqlName)
  {
    this.routineType = paramInt;
    this.name = paramHsqlName;
  }

  public int getType()
  {
    return this.routineType;
  }

  public HsqlNameManager.HsqlName getCatalogName()
  {
    return this.name.schema.schema;
  }

  public HsqlNameManager.HsqlName getSchemaName()
  {
    return this.name.schema;
  }

  public HsqlNameManager.HsqlName getName()
  {
    return this.name;
  }

  public Grantee getOwner()
  {
    return this.name.schema.owner;
  }

  public OrderedHashSet getReferences()
  {
    OrderedHashSet localOrderedHashSet = new OrderedHashSet();
    for (int i = 0; i < this.routines.length; i++)
      localOrderedHashSet.addAll(this.routines[i].getReferences());
    return localOrderedHashSet;
  }

  public OrderedHashSet getComponents()
  {
    OrderedHashSet localOrderedHashSet = new OrderedHashSet();
    localOrderedHashSet.addAll(this.routines);
    return localOrderedHashSet;
  }

  public void compile(Session paramSession, SchemaObject paramSchemaObject)
  {
  }

  public String getSQL()
  {
    return null;
  }

  public long getChangeTimestamp()
  {
    return 0L;
  }

  public String[] getSQLArray()
  {
    HsqlArrayList localHsqlArrayList = new HsqlArrayList();
    for (int i = 0; i < this.routines.length; i++)
      localHsqlArrayList.add(this.routines[i].getSQL());
    String[] arrayOfString = new String[localHsqlArrayList.size()];
    localHsqlArrayList.toArray(arrayOfString);
    return arrayOfString;
  }

  public void addSpecificRoutine(Database paramDatabase, Routine paramRoutine)
  {
    int i = paramRoutine.getParameterSignature();
    Type[] arrayOfType = paramRoutine.getParameterTypes();
    for (int j = 0; j < this.routines.length; j++)
      if (this.routines[j].parameterTypes.length == arrayOfType.length)
      {
        if (this.routineType == 17)
          throw Error.error(5605);
        if (this.routines[j].isAggregate() != paramRoutine.isAggregate())
          throw Error.error(5605);
        int k = 1;
        for (int m = 0; m < arrayOfType.length; m++)
          if (!this.routines[j].parameterTypes[m].equals(arrayOfType[m]))
          {
            k = 0;
            break;
          }
        if (k != 0)
          throw Error.error(5605);
      }
    if (paramRoutine.getSpecificName() == null)
    {
      HsqlNameManager.HsqlName localHsqlName = paramDatabase.nameManager.newSpecificRoutineName(this.name);
      paramRoutine.setSpecificName(localHsqlName);
    }
    else
    {
      paramRoutine.getSpecificName().parent = this.name;
      paramRoutine.getSpecificName().schema = this.name.schema;
    }
    paramRoutine.setName(this.name);
    paramRoutine.routineSchema = this;
    this.routines = ((Routine[])ArrayUtil.resizeArray(this.routines, this.routines.length + 1));
    this.routines[(this.routines.length - 1)] = paramRoutine;
  }

  public void removeSpecificRoutine(Routine paramRoutine)
  {
    for (int i = 0; i < this.routines.length; i++)
      if (this.routines[i] == paramRoutine)
      {
        this.routines = ((Routine[])ArrayUtil.toAdjustedArray(this.routines, null, i, -1));
        break;
      }
  }

  public Routine[] getSpecificRoutines()
  {
    return this.routines;
  }

  public Routine getSpecificRoutine(Type[] paramArrayOfType)
  {
    Routine localRoutine = findSpecificRoutine(paramArrayOfType);
    if (localRoutine == null)
    {
      StringBuffer localStringBuffer = new StringBuffer();
      localStringBuffer.append(this.name.getSchemaQualifiedStatementName());
      localStringBuffer.append("(");
      for (int i = 0; i < paramArrayOfType.length; i++)
      {
        if (i != 0)
          localStringBuffer.append(",");
        localStringBuffer.append(paramArrayOfType[i].getNameString());
      }
      localStringBuffer.append(")");
      throw Error.error(5609, localStringBuffer.toString());
    }
    return localRoutine;
  }

  public Routine findSpecificRoutine(Type[] paramArrayOfType)
  {
    int i = -1;
    label377: for (int j = 0; j < this.routines.length; j++)
    {
      int k = 0;
      int m;
      int n;
      int i1;
      if ((this.routines[j].isAggregate()) && (paramArrayOfType.length == 1))
      {
        if (paramArrayOfType[0] == null)
          return this.routines[j];
        m = paramArrayOfType[0].precedenceDegree(this.routines[j].parameterTypes[0]);
        if (m < -128)
        {
          if (i != -1)
          {
            n = paramArrayOfType[0].precedenceDegree(this.routines[i].parameterTypes[0]);
            i1 = paramArrayOfType[0].precedenceDegree(this.routines[j].parameterTypes[0]);
            if ((n != i1) && (i1 < n))
              i = j;
          }
        }
        else
        {
          if (m == 0)
            return this.routines[j];
          i = j;
        }
      }
      else if (this.routines[j].parameterTypes.length == paramArrayOfType.length)
      {
        if (paramArrayOfType.length == 0)
          return this.routines[j];
        for (m = 0; m < paramArrayOfType.length; m++)
          if (paramArrayOfType[m] != null)
          {
            n = paramArrayOfType[m].precedenceDegree(this.routines[j].parameterTypes[m]);
            if (n < -128)
              break label377;
            if ((n == 0) && (k == m))
              k = m + 1;
          }
        if (k == paramArrayOfType.length)
          return this.routines[j];
        if (i == -1)
          i = j;
        else
          for (m = 0; m < paramArrayOfType.length; m++)
            if (paramArrayOfType[m] != null)
            {
              n = paramArrayOfType[m].precedenceDegree(this.routines[i].parameterTypes[m]);
              i1 = paramArrayOfType[m].precedenceDegree(this.routines[j].parameterTypes[m]);
              if (n != i1)
              {
                if (i1 >= n)
                  break;
                i = j;
                break;
              }
            }
      }
    }
    return i < 0 ? null : this.routines[i];
  }

  public Routine getSpecificRoutine(int paramInt)
  {
    for (int i = 0; i < this.routines.length; i++)
      if (this.routines[i].parameterTypes.length == paramInt)
        return this.routines[i];
    throw Error.error(5501);
  }

  public boolean isAggregate()
  {
    return this.routines[0].isAggregate;
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.RoutineSchema
 * JD-Core Version:    0.6.2
 */