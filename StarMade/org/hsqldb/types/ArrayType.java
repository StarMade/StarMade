package org.hsqldb.types;

import java.sql.Array;
import org.hsqldb.Session;
import org.hsqldb.SessionInterface;
import org.hsqldb.SortAndSlice;
import org.hsqldb.error.Error;
import org.hsqldb.jdbc.JDBCArray;
import org.hsqldb.jdbc.JDBCArrayBasic;
import org.hsqldb.lib.ArraySort;

public class ArrayType extends Type
{
  public static final int defaultArrayCardinality = 1024;
  public static final int defaultLargeArrayCardinality = 2147483647;
  final Type dataType;
  final int maxCardinality;

  public ArrayType(Type paramType, int paramInt)
  {
    super(50, 50, 0L, 0);
    if (paramType == null)
      paramType = Type.SQL_ALL_TYPES;
    this.dataType = paramType;
    this.maxCardinality = paramInt;
  }

  public int displaySize()
  {
    return 7 + (this.dataType.displaySize() + 1) * this.maxCardinality;
  }

  public int getJDBCTypeCode()
  {
    return 2003;
  }

  public Class getJDBCClass()
  {
    return Array.class;
  }

  public String getJDBCClassName()
  {
    return "java.sql.Array";
  }

  public int getJDBCScale()
  {
    return 0;
  }

  public int getJDBCPrecision()
  {
    return 0;
  }

  public int getSQLGenericTypeCode()
  {
    return 0;
  }

  public String getNameString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append(this.dataType.getNameString()).append(' ');
    localStringBuffer.append("ARRAY");
    if (this.maxCardinality != 1024)
      localStringBuffer.append('[').append(this.maxCardinality).append(']');
    return localStringBuffer.toString();
  }

  public String getFullNameString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append(this.dataType.getFullNameString()).append(' ');
    localStringBuffer.append("ARRAY");
    if (this.maxCardinality != 1024)
      localStringBuffer.append('[').append(this.maxCardinality).append(']');
    return localStringBuffer.toString();
  }

  public String getDefinition()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append(this.dataType.getDefinition()).append(' ');
    localStringBuffer.append("ARRAY");
    if (this.maxCardinality != 1024)
      localStringBuffer.append('[').append(this.maxCardinality).append(']');
    return localStringBuffer.toString();
  }

  public int compare(Session paramSession, Object paramObject1, Object paramObject2)
  {
    if (paramObject1 == paramObject2)
      return 0;
    if (paramObject1 == null)
      return -1;
    if (paramObject2 == null)
      return 1;
    Object[] arrayOfObject1 = (Object[])paramObject1;
    Object[] arrayOfObject2 = (Object[])paramObject2;
    int i = arrayOfObject1.length;
    if (arrayOfObject2.length < i)
      i = arrayOfObject2.length;
    for (int j = 0; j < i; j++)
    {
      int k = this.dataType.compare(paramSession, arrayOfObject1[j], arrayOfObject2[j]);
      if (k != 0)
        return k;
    }
    if (arrayOfObject1.length > arrayOfObject2.length)
      return 1;
    if (arrayOfObject1.length < arrayOfObject2.length)
      return -1;
    return 0;
  }

  public Object convertToTypeLimits(SessionInterface paramSessionInterface, Object paramObject)
  {
    if (paramObject == null)
      return null;
    Object[] arrayOfObject1 = (Object[])paramObject;
    if (arrayOfObject1.length > this.maxCardinality)
      throw Error.error(3491);
    Object[] arrayOfObject2 = new Object[arrayOfObject1.length];
    for (int i = 0; i < arrayOfObject1.length; i++)
      arrayOfObject2[i] = this.dataType.convertToTypeLimits(paramSessionInterface, arrayOfObject1[i]);
    return arrayOfObject2;
  }

  public Object convertToType(SessionInterface paramSessionInterface, Object paramObject, Type paramType)
  {
    if (paramObject == null)
      return null;
    if (paramType == null)
      return paramObject;
    if (!paramType.isArrayType())
      throw Error.error(5562);
    Object[] arrayOfObject1 = (Object[])paramObject;
    if (arrayOfObject1.length > this.maxCardinality)
      throw Error.error(3491);
    Type localType = paramType.collectionBaseType();
    if (this.dataType.equals(localType))
      return paramObject;
    Object[] arrayOfObject2 = new Object[arrayOfObject1.length];
    for (int i = 0; i < arrayOfObject1.length; i++)
      arrayOfObject2[i] = this.dataType.convertToType(paramSessionInterface, arrayOfObject1[i], localType);
    return arrayOfObject2;
  }

  public Object convertJavaToSQL(SessionInterface paramSessionInterface, Object paramObject)
  {
    int i = 0;
    if (paramObject == null)
      return null;
    Object[] arrayOfObject1;
    if ((paramObject instanceof Object[]))
    {
      arrayOfObject1 = (Object[])paramObject;
      i = 1;
    }
    else if ((paramObject instanceof JDBCArray))
    {
      arrayOfObject1 = ((JDBCArray)paramObject).getArrayInternal();
    }
    else if ((paramObject instanceof JDBCArrayBasic))
    {
      arrayOfObject1 = (Object[])((JDBCArrayBasic)paramObject).getArray();
      i = 1;
    }
    else if ((paramObject instanceof Array))
    {
      try
      {
        arrayOfObject1 = (Object[])((Array)paramObject).getArray();
        i = 1;
      }
      catch (Exception localException)
      {
        throw Error.error(5561);
      }
    }
    else
    {
      throw Error.error(5561);
    }
    if (i != 0)
    {
      Object[] arrayOfObject2 = new Object[arrayOfObject1.length];
      for (int j = 0; j < arrayOfObject1.length; j++)
      {
        arrayOfObject2[j] = this.dataType.convertJavaToSQL(paramSessionInterface, arrayOfObject1[j]);
        arrayOfObject2[j] = this.dataType.convertToTypeLimits(paramSessionInterface, arrayOfObject1[j]);
      }
      return arrayOfObject2;
    }
    return arrayOfObject1;
  }

  public Object convertSQLToJava(SessionInterface paramSessionInterface, Object paramObject)
  {
    if ((paramObject instanceof Object[]))
    {
      Object[] arrayOfObject = (Object[])paramObject;
      return new JDBCArray(arrayOfObject, collectionBaseType(), this, paramSessionInterface);
    }
    throw Error.error(5561);
  }

  public Object convertToDefaultType(SessionInterface paramSessionInterface, Object paramObject)
  {
    return paramObject;
  }

  public String convertToString(Object paramObject)
  {
    if (paramObject == null)
      return null;
    return convertToSQLString(paramObject);
  }

  public String convertToSQLString(Object paramObject)
  {
    if (paramObject == null)
      return "NULL";
    Object[] arrayOfObject = (Object[])paramObject;
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("ARRAY");
    localStringBuffer.append('[');
    for (int i = 0; i < arrayOfObject.length; i++)
    {
      if (i > 0)
        localStringBuffer.append(',');
      localStringBuffer.append(this.dataType.convertToSQLString(arrayOfObject[i]));
    }
    localStringBuffer.append(']');
    return localStringBuffer.toString();
  }

  public boolean canConvertFrom(Type paramType)
  {
    if (paramType == null)
      return true;
    if (!paramType.isArrayType())
      return false;
    Type localType = paramType.collectionBaseType();
    return this.dataType.canConvertFrom(localType);
  }

  public int canMoveFrom(Type paramType)
  {
    if (paramType == this)
      return 0;
    if (!paramType.isArrayType())
      return -1;
    if (this.maxCardinality >= ((ArrayType)paramType).maxCardinality)
      return this.dataType.canMoveFrom((ArrayType)paramType);
    if (this.dataType.canMoveFrom((ArrayType)paramType) == -1)
      return -1;
    return 1;
  }

  public boolean canBeAssignedFrom(Type paramType)
  {
    if (paramType == null)
      return true;
    Type localType = paramType.collectionBaseType();
    return (localType != null) && (this.dataType.canBeAssignedFrom(localType));
  }

  public Type collectionBaseType()
  {
    return this.dataType;
  }

  public int arrayLimitCardinality()
  {
    return this.maxCardinality;
  }

  public boolean isArrayType()
  {
    return true;
  }

  public Type getAggregateType(Type paramType)
  {
    if (paramType == null)
      return this;
    if (paramType == SQL_ALL_TYPES)
      return this;
    if (this == paramType)
      return this;
    if (!paramType.isArrayType())
      throw Error.error(5562);
    Type localType1 = paramType.collectionBaseType();
    if (this.dataType.equals(localType1))
      return ((ArrayType)paramType).maxCardinality > this.maxCardinality ? paramType : this;
    Type localType2 = this.dataType.getAggregateType(localType1);
    int i = ((ArrayType)paramType).maxCardinality > this.maxCardinality ? ((ArrayType)paramType).maxCardinality : this.maxCardinality;
    return new ArrayType(localType2, i);
  }

  public Type getCombinedType(Session paramSession, Type paramType, int paramInt)
  {
    ArrayType localArrayType = (ArrayType)getAggregateType(paramType);
    if (paramType == null)
      return localArrayType;
    if (paramInt != 36)
      return localArrayType;
    if (localArrayType.maxCardinality == 2147483647)
      return localArrayType;
    long l = ((ArrayType)paramType).maxCardinality + this.maxCardinality;
    if (l > 2147483647L)
      l = 2147483647L;
    return new ArrayType(this.dataType, (int)l);
  }

  public int cardinality(Session paramSession, Object paramObject)
  {
    if (paramObject == null)
      return 0;
    return ((Object[])paramObject).length;
  }

  public Object concat(Session paramSession, Object paramObject1, Object paramObject2)
  {
    if ((paramObject1 == null) || (paramObject2 == null))
      return null;
    int i = ((Object[])paramObject1).length + ((Object[])paramObject2).length;
    Object[] arrayOfObject = new Object[i];
    System.arraycopy(paramObject1, 0, arrayOfObject, 0, ((Object[])paramObject1).length);
    System.arraycopy(paramObject2, 0, arrayOfObject, ((Object[])paramObject1).length, ((Object[])paramObject2).length);
    return arrayOfObject;
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == this)
      return true;
    if ((paramObject instanceof Type))
    {
      if (((Type)paramObject).typeCode != 50)
        return false;
      return (this.maxCardinality == ((ArrayType)paramObject).maxCardinality) && (this.dataType.equals(((ArrayType)paramObject).dataType));
    }
    return false;
  }

  public int hashCode(Object paramObject)
  {
    if (paramObject == null)
      return 0;
    int i = 0;
    Object[] arrayOfObject = (Object[])paramObject;
    for (int j = 0; (j < arrayOfObject.length) && (j < 4); j++)
      i += this.dataType.hashCode(arrayOfObject[j]);
    return i;
  }

  public void sort(Session paramSession, Object paramObject, SortAndSlice paramSortAndSlice)
  {
    Object[] arrayOfObject = (Object[])paramObject;
    Type.TypedComparator localTypedComparator = paramSession.getComparator();
    localTypedComparator.setType(this.dataType, paramSortAndSlice);
    ArraySort.sort(arrayOfObject, 0, arrayOfObject.length, localTypedComparator);
  }

  public int deDuplicate(Session paramSession, Object paramObject, SortAndSlice paramSortAndSlice)
  {
    Object[] arrayOfObject = (Object[])paramObject;
    Type.TypedComparator localTypedComparator = paramSession.getComparator();
    localTypedComparator.setType(this.dataType, paramSortAndSlice);
    return ArraySort.deDuplicate(arrayOfObject, 0, arrayOfObject.length, localTypedComparator);
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.types.ArrayType
 * JD-Core Version:    0.6.2
 */