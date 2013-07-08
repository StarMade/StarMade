package org.hsqldb.jdbc;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import org.hsqldb.ColumnBase;
import org.hsqldb.SessionInterface;
import org.hsqldb.navigator.RowSetNavigatorClient;
import org.hsqldb.result.Result;
import org.hsqldb.result.ResultMetaData;
import org.hsqldb.types.Type;

public class JDBCArray
  implements Array
{
  volatile boolean closed;
  Type arrayType;
  Type elementType;
  Object[] data;
  JDBCConnection connection;
  SessionInterface sessionProxy;
  
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
    throws SQLException
  {
    checkClosed();
    Object[] arrayOfObject = new Object[this.data.length];
    for (int i = 0; i < this.data.length; i++) {
      arrayOfObject[i] = this.elementType.convertSQLToJava(this.sessionProxy, this.data[i]);
    }
    return arrayOfObject;
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
    if (!JDBCClobClient.isInLimits(this.data.length, paramLong - 1L, paramInt)) {
      throw Util.outOfRangeArgument();
    }
    Object[] arrayOfObject = new Object[paramInt];
    for (int i = 0; i < paramInt; i++) {
      arrayOfObject[i] = this.elementType.convertSQLToJava(this.sessionProxy, this.data[((int)paramLong + i - 1)]);
    }
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
    return new JDBCResultSet(this.connection, localResult, localResult.metaData);
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
    return new JDBCResultSet(this.connection, localResult, localResult.metaData);
  }
  
  public ResultSet getResultSet(long paramLong, int paramInt, Map<String, Class<?>> paramMap)
    throws SQLException
  {
    return getResultSet(paramLong, paramInt);
  }
  
  public String toString()
  {
    if (this.arrayType == null) {
      this.arrayType = Type.getDefaultArrayType(this.elementType.typeCode);
    }
    return this.arrayType.convertToString(this.data);
  }
  
  public void free()
    throws SQLException
  {
    if (!this.closed)
    {
      this.closed = true;
      this.connection = null;
      this.sessionProxy = null;
    }
  }
  
  public JDBCArray(Object[] paramArrayOfObject, Type paramType1, Type paramType2, SessionInterface paramSessionInterface)
  {
    this(paramArrayOfObject, paramType1, paramType2, paramSessionInterface.getJDBCConnection());
    this.sessionProxy = paramSessionInterface;
  }
  
  JDBCArray(Object[] paramArrayOfObject, Type paramType, JDBCConnection paramJDBCConnection)
    throws SQLException
  {
    this(paramArrayOfObject, paramType, null, paramJDBCConnection);
  }
  
  JDBCArray(Object[] paramArrayOfObject, Type paramType1, Type paramType2, JDBCConnection paramJDBCConnection)
  {
    this.data = paramArrayOfObject;
    this.elementType = paramType1;
    this.arrayType = paramType2;
    this.connection = paramJDBCConnection;
    this.sessionProxy = paramJDBCConnection.sessionProxy;
  }
  
  public Object[] getArrayInternal()
  {
    return this.data;
  }
  
  private Result newColumnResult(long paramLong, int paramInt)
    throws SQLException
  {
    if (!JDBCClobClient.isInLimits(this.data.length, paramLong, paramInt)) {
      throw Util.outOfRangeArgument();
    }
    Type[] arrayOfType = new Type[2];
    arrayOfType[0] = Type.SQL_INTEGER;
    arrayOfType[1] = this.elementType;
    ResultMetaData localResultMetaData = ResultMetaData.newSimpleResultMetaData(arrayOfType);
    localResultMetaData.columnLabels = new String[] { "C1", "C2" };
    localResultMetaData.colIndexes = new int[] { -1, -1 };
    localResultMetaData.columns = new ColumnBase[2];
    ColumnBase localColumnBase = new ColumnBase("", "", "", "");
    localColumnBase.setType(arrayOfType[0]);
    localResultMetaData.columns[0] = localColumnBase;
    localColumnBase = new ColumnBase("", "", "", "");
    localColumnBase.setType(arrayOfType[1]);
    localResultMetaData.columns[1] = localColumnBase;
    RowSetNavigatorClient localRowSetNavigatorClient = new RowSetNavigatorClient();
    for (int i = (int)paramLong; i < paramLong + paramInt; i++)
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = Integer.valueOf(i + 1);
      arrayOfObject[1] = this.data[i];
      localRowSetNavigatorClient.add(arrayOfObject);
    }
    Result localResult = Result.newDataResult(localResultMetaData);
    localResult.setNavigator(localRowSetNavigatorClient);
    return localResult;
  }
  
  private void checkClosed()
    throws SQLException
  {
    if (this.closed) {
      throw Util.sqlException(1251);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.jdbc.JDBCArray
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */