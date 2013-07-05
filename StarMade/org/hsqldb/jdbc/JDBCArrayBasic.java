package org.hsqldb.jdbc;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import org.hsqldb.ColumnBase;
import org.hsqldb.navigator.RowSetNavigatorClient;
import org.hsqldb.result.Result;
import org.hsqldb.result.ResultMetaData;
import org.hsqldb.types.Type;

public class JDBCArrayBasic
  implements Array
{
  Type arrayType;
  Type elementType;
  Object[] data;

  public String getBaseTypeName()
    throws SQLException
  {
    checkClosed();
    return this.elementType.getNameString();
  }

  public int getBaseType()
    throws SQLException
  {
    checkClosed();
    return this.elementType.getJDBCTypeCode();
  }

  public Object getArray()
  {
    return this.data;
  }

  public Object getArray(Map<String, Class<?>> paramMap)
    throws SQLException
  {
    return getArray();
  }

  public Object getArray(long paramLong, int paramInt)
    throws SQLException
  {
    checkClosed();
    if (!JDBCClobClient.isInLimits(this.data.length, paramLong - 1L, paramInt))
      throw Util.outOfRangeArgument();
    Object[] arrayOfObject = new Object[paramInt];
    for (int i = 0; i < paramInt; i++)
      arrayOfObject[i] = this.data[((int)paramLong + i - 1)];
    return arrayOfObject;
  }

  public Object getArray(long paramLong, int paramInt, Map<String, Class<?>> paramMap)
    throws SQLException
  {
    return getArray(paramLong, paramInt);
  }

  public ResultSet getResultSet()
    throws SQLException
  {
    checkClosed();
    Result localResult = newColumnResult(0L, this.data.length);
    return JDBCResultSet.newJDBCResultSet(localResult, localResult.metaData);
  }

  public ResultSet getResultSet(Map<String, Class<?>> paramMap)
    throws SQLException
  {
    return getResultSet();
  }

  public ResultSet getResultSet(long paramLong, int paramInt)
    throws SQLException
  {
    checkClosed();
    Result localResult = newColumnResult(paramLong - 1L, paramInt);
    return new JDBCResultSet(null, localResult, localResult.metaData);
  }

  public ResultSet getResultSet(long paramLong, int paramInt, Map<String, Class<?>> paramMap)
    throws SQLException
  {
    return getResultSet(paramLong, paramInt);
  }

  public String toString()
  {
    if (this.arrayType == null)
      this.arrayType = Type.getDefaultArrayType(this.elementType.typeCode);
    return this.arrayType.convertToString(this.data);
  }

  public void free()
    throws SQLException
  {
  }

  public JDBCArrayBasic(Object[] paramArrayOfObject, Type paramType)
  {
    this.data = paramArrayOfObject;
    this.elementType = paramType;
  }

  Object[] getArrayInternal()
  {
    return this.data;
  }

  private Result newColumnResult(long paramLong, int paramInt)
    throws SQLException
  {
    if (!JDBCClobClient.isInLimits(this.data.length, paramLong, paramInt))
      throw Util.outOfRangeArgument();
    Type[] arrayOfType = new Type[2];
    arrayOfType[0] = Type.SQL_INTEGER;
    arrayOfType[1] = this.elementType;
    ResultMetaData localResultMetaData = ResultMetaData.newSimpleResultMetaData(arrayOfType);
    localResultMetaData.columnLabels = new String[] { "C1", "C2" };
    localResultMetaData.colIndexes = new int[] { -1, -1 };
    localResultMetaData.columns = new ColumnBase[2];
    for (int i = 0; i < localResultMetaData.columns.length; i++)
    {
      ColumnBase localColumnBase = new ColumnBase("", "", "", "");
      localColumnBase.setType(arrayOfType[i]);
      localResultMetaData.columns[i] = localColumnBase;
    }
    RowSetNavigatorClient localRowSetNavigatorClient = new RowSetNavigatorClient();
    for (int j = (int)paramLong; j < paramLong + paramInt; j++)
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = Integer.valueOf(j + 1);
      arrayOfObject[1] = this.data[j];
      localRowSetNavigatorClient.add(arrayOfObject);
    }
    Result localResult = Result.newDataResult(localResultMetaData);
    localResult.setNavigator(localRowSetNavigatorClient);
    return localResult;
  }

  private void checkClosed()
    throws SQLException
  {
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.jdbc.JDBCArrayBasic
 * JD-Core Version:    0.6.2
 */