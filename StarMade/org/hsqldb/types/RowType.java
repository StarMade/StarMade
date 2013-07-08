package org.hsqldb.types;

import java.sql.ResultSet;
import org.hsqldb.Session;
import org.hsqldb.SessionInterface;
import org.hsqldb.SortAndSlice;
import org.hsqldb.error.Error;

public class RowType
  extends Type
{
  final Type[] dataTypes;
  Type.TypedComparator comparator;
  
  public RowType(Type[] paramArrayOfType)
  {
    super(19, 19, 0L, 0);
    this.dataTypes = paramArrayOfType;
  }
  
  public int displaySize()
  {
    return 0;
  }
  
  public int getJDBCTypeCode()
  {
    return 0;
  }
  
  public Class getJDBCClass()
  {
    return ResultSet.class;
  }
  
  public String getJDBCClassName()
  {
    return "java.sql.ResultSet";
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
    return 19;
  }
  
  public boolean isRowType()
  {
    return true;
  }
  
  public String getNameString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("ROW");
    localStringBuffer.append('(');
    for (int i = 0; i < this.dataTypes.length; i++)
    {
      if (i > 0) {
        localStringBuffer.append(',');
      }
      localStringBuffer.append(this.dataTypes[i].getDefinition());
    }
    localStringBuffer.append(')');
    return localStringBuffer.toString();
  }
  
  public String getDefinition()
  {
    return getNameString();
  }
  
  public int compare(Session paramSession, Object paramObject1, Object paramObject2)
  {
    if (paramObject1 == paramObject2) {
      return 0;
    }
    if (paramObject1 == null) {
      return -1;
    }
    if (paramObject2 == null) {
      return 1;
    }
    Object[] arrayOfObject1 = (Object[])paramObject1;
    Object[] arrayOfObject2 = (Object[])paramObject2;
    int i = arrayOfObject1.length;
    if (arrayOfObject2.length < i) {
      i = arrayOfObject2.length;
    }
    for (int j = 0; j < i; j++)
    {
      int k = this.dataTypes[j].compare(paramSession, arrayOfObject1[j], arrayOfObject2[j]);
      if (k != 0) {
        return k;
      }
    }
    if (arrayOfObject1.length > arrayOfObject2.length) {
      return 1;
    }
    if (arrayOfObject1.length < arrayOfObject2.length) {
      return -1;
    }
    return 0;
  }
  
  public Object convertToTypeLimits(SessionInterface paramSessionInterface, Object paramObject)
  {
    if (paramObject == null) {
      return null;
    }
    Object[] arrayOfObject1 = (Object[])paramObject;
    Object[] arrayOfObject2 = new Object[arrayOfObject1.length];
    for (int i = 0; i < arrayOfObject1.length; i++) {
      arrayOfObject2[i] = this.dataTypes[i].convertToTypeLimits(paramSessionInterface, arrayOfObject1[i]);
    }
    return arrayOfObject2;
  }
  
  public Object convertToType(SessionInterface paramSessionInterface, Object paramObject, Type paramType)
  {
    if (paramObject == null) {
      return null;
    }
    if (paramType == null) {
      return paramObject;
    }
    if (!paramType.isRowType()) {
      throw Error.error(5562);
    }
    Type[] arrayOfType = ((RowType)paramType).getTypesArray();
    if (this.dataTypes.length != arrayOfType.length) {
      throw Error.error(5564);
    }
    Object[] arrayOfObject1 = (Object[])paramObject;
    Object[] arrayOfObject2 = new Object[arrayOfObject1.length];
    for (int i = 0; i < arrayOfObject1.length; i++) {
      arrayOfObject2[i] = this.dataTypes[i].convertToType(paramSessionInterface, arrayOfObject1[i], arrayOfType[i]);
    }
    return arrayOfObject2;
  }
  
  public Object convertToDefaultType(SessionInterface paramSessionInterface, Object paramObject)
  {
    return paramObject;
  }
  
  public String convertToString(Object paramObject)
  {
    if (paramObject == null) {
      return null;
    }
    return convertToSQLString(paramObject);
  }
  
  public String convertToSQLString(Object paramObject)
  {
    if (paramObject == null) {
      return "NULL";
    }
    Object[] arrayOfObject = (Object[])paramObject;
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("ROW");
    localStringBuffer.append('(');
    for (int i = 0; i < arrayOfObject.length; i++)
    {
      if (i > 0) {
        localStringBuffer.append(',');
      }
      localStringBuffer.append(this.dataTypes[i].convertToSQLString(arrayOfObject[i]));
    }
    localStringBuffer.append(')');
    return localStringBuffer.toString();
  }
  
  public boolean canConvertFrom(Type paramType)
  {
    if (paramType == null) {
      return true;
    }
    if (!paramType.isRowType()) {
      return false;
    }
    Type[] arrayOfType = ((RowType)paramType).getTypesArray();
    if (this.dataTypes.length != arrayOfType.length) {
      return false;
    }
    for (int i = 0; i < this.dataTypes.length; i++) {
      if (!this.dataTypes[i].canConvertFrom(arrayOfType[i])) {
        return false;
      }
    }
    return true;
  }
  
  public boolean canBeAssignedFrom(Type paramType)
  {
    if (paramType == null) {
      return true;
    }
    if (!paramType.isRowType()) {
      return false;
    }
    Type[] arrayOfType = ((RowType)paramType).getTypesArray();
    if (this.dataTypes.length != arrayOfType.length) {
      return false;
    }
    for (int i = 0; i < this.dataTypes.length; i++) {
      if (!this.dataTypes[i].canBeAssignedFrom(arrayOfType[i])) {
        return false;
      }
    }
    return true;
  }
  
  public Type getAggregateType(Type paramType)
  {
    if (paramType == null) {
      return this;
    }
    if (paramType == SQL_ALL_TYPES) {
      return this;
    }
    if (paramType == this) {
      return this;
    }
    if (!paramType.isRowType()) {
      throw Error.error(5562);
    }
    Type[] arrayOfType1 = new Type[this.dataTypes.length];
    Type[] arrayOfType2 = ((RowType)paramType).getTypesArray();
    if (this.dataTypes.length != arrayOfType2.length) {
      throw Error.error(5564);
    }
    for (int i = 0; i < this.dataTypes.length; i++) {
      arrayOfType1[i] = this.dataTypes[i].getAggregateType(arrayOfType2[i]);
    }
    return new RowType(arrayOfType1);
  }
  
  public Type getCombinedType(Session paramSession, Type paramType, int paramInt)
  {
    if (paramInt != 36) {
      return getAggregateType(paramType);
    }
    if (paramType == null) {
      return this;
    }
    if (!paramType.isRowType()) {
      throw Error.error(5562);
    }
    Type[] arrayOfType1 = new Type[this.dataTypes.length];
    Type[] arrayOfType2 = ((RowType)paramType).getTypesArray();
    if (this.dataTypes.length != arrayOfType2.length) {
      throw Error.error(5564);
    }
    for (int i = 0; i < this.dataTypes.length; i++) {
      arrayOfType1[i] = this.dataTypes[i].getAggregateType(arrayOfType2[i]);
    }
    return new RowType(arrayOfType1);
  }
  
  public Type[] getTypesArray()
  {
    return this.dataTypes;
  }
  
  public int compare(Session paramSession, Object paramObject1, Object paramObject2, SortAndSlice paramSortAndSlice)
  {
    if (paramObject1 == paramObject2) {
      return 0;
    }
    if (paramObject1 == null) {
      return -1;
    }
    if (paramObject2 == null) {
      return 1;
    }
    Object[] arrayOfObject1 = (Object[])paramObject1;
    Object[] arrayOfObject2 = (Object[])paramObject2;
    int i = paramSortAndSlice.sortOrder.length;
    for (int j = 0; j < i; j++)
    {
      paramObject1 = arrayOfObject1[paramSortAndSlice.sortOrder[j]];
      paramObject2 = arrayOfObject2[paramSortAndSlice.sortOrder[j]];
      if (paramObject1 != paramObject2)
      {
        if (paramSortAndSlice.sortNullsLast[j] != 0)
        {
          if (paramObject1 == null) {
            return 1;
          }
          if (paramObject2 == null) {
            return -1;
          }
        }
        int k = this.dataTypes[j].compare(paramSession, paramObject1, paramObject2);
        if (k != 0)
        {
          if (paramSortAndSlice.sortDescending[j] != 0) {
            return -k;
          }
          return k;
        }
      }
    }
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    if ((paramObject instanceof Type))
    {
      if (((Type)paramObject).typeCode != 19) {
        return false;
      }
      Type[] arrayOfType = ((RowType)paramObject).dataTypes;
      if (arrayOfType.length != this.dataTypes.length) {
        return false;
      }
      int i = 0;
      if (i < this.dataTypes.length) {
        return this.dataTypes[i].equals(arrayOfType[i]);
      }
    }
    return false;
  }
  
  public int hashCode(Object paramObject)
  {
    if (paramObject == null) {
      return 0;
    }
    int i = 0;
    Object[] arrayOfObject = (Object[])paramObject;
    for (int j = 0; (j < this.dataTypes.length) && (j < 4); j++) {
      i += this.dataTypes[j].hashCode(arrayOfObject[j]);
    }
    return i;
  }
  
  synchronized Type.TypedComparator getComparator(Session paramSession)
  {
    if (this.comparator == null)
    {
      Type.TypedComparator localTypedComparator = Type.newComparator(paramSession);
      SortAndSlice localSortAndSlice = new SortAndSlice();
      localSortAndSlice.prepareMultiColumn(this.dataTypes.length);
      localTypedComparator.setType(this, localSortAndSlice);
      this.comparator = localTypedComparator;
    }
    return this.comparator;
  }
  
  public static String convertToSQLString(Object[] paramArrayOfObject, Type[] paramArrayOfType, int paramInt)
  {
    if (paramArrayOfObject == null) {
      return "NULL";
    }
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append('(');
    for (int i = 0; i < paramArrayOfObject.length; i++)
    {
      if (i > 0) {
        localStringBuffer.append(',');
      }
      String str = paramArrayOfType[i].convertToSQLString(paramArrayOfObject[i]);
      if ((paramInt > 10) && (str.length() > paramInt))
      {
        localStringBuffer.append(str.substring(0, paramInt - 5));
        localStringBuffer.append(" ...");
      }
      else
      {
        localStringBuffer.append(str);
      }
    }
    localStringBuffer.append(')');
    return localStringBuffer.toString();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.types.RowType
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */