package org.hsqldb.jdbc;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import org.hsqldb.ColumnBase;
import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.result.ResultMetaData;
import org.hsqldb.types.CharacterType;
import org.hsqldb.types.DateTimeType;
import org.hsqldb.types.IntervalType;
import org.hsqldb.types.Type;

public class JDBCResultSetMetaData
  implements ResultSetMetaData
{
  private ResultMetaData resultMetaData;
  private boolean useColumnName;
  private boolean translateTTIType;
  private int columnCount;
  
  public int getColumnCount()
    throws SQLException
  {
    return this.resultMetaData.getColumnCount();
  }
  
  public boolean isAutoIncrement(int paramInt)
    throws SQLException
  {
    checkColumn(paramInt);
    return this.resultMetaData.columns[(--paramInt)].isIdentity();
  }
  
  public boolean isCaseSensitive(int paramInt)
    throws SQLException
  {
    checkColumn(paramInt);
    Type localType = translateType(this.resultMetaData.columnTypes[(--paramInt)]);
    if (localType.isCharacterType()) {
      return !((CharacterType)localType).isCaseInsensitive();
    }
    return false;
  }
  
  public boolean isSearchable(int paramInt)
    throws SQLException
  {
    checkColumn(paramInt);
    return this.resultMetaData.columns[(--paramInt)].isSearchable();
  }
  
  public boolean isCurrency(int paramInt)
    throws SQLException
  {
    checkColumn(paramInt);
    Type localType = translateType(this.resultMetaData.columnTypes[(--paramInt)]);
    return ((localType.typeCode == 3) || (localType.typeCode == 2)) && (localType.scale > 0);
  }
  
  public int isNullable(int paramInt)
    throws SQLException
  {
    checkColumn(paramInt);
    return this.resultMetaData.columns[(--paramInt)].getNullability();
  }
  
  public boolean isSigned(int paramInt)
    throws SQLException
  {
    checkColumn(paramInt);
    Type localType = translateType(this.resultMetaData.columnTypes[(--paramInt)]);
    return localType.isNumberType();
  }
  
  public int getColumnDisplaySize(int paramInt)
    throws SQLException
  {
    checkColumn(paramInt);
    Type localType = translateType(this.resultMetaData.columnTypes[(--paramInt)]);
    return localType.displaySize();
  }
  
  public String getColumnLabel(int paramInt)
    throws SQLException
  {
    checkColumn(paramInt--);
    String str = this.resultMetaData.columnLabels[paramInt];
    if ((str != null) && (str.length() > 0)) {
      return str;
    }
    return this.resultMetaData.columns[paramInt].getNameString();
  }
  
  public String getColumnName(int paramInt)
    throws SQLException
  {
    checkColumn(paramInt--);
    if (this.useColumnName)
    {
      str = this.resultMetaData.columns[paramInt].getNameString();
      if ((str != null) && (str.length() > 0)) {
        return str;
      }
    }
    String str = this.resultMetaData.columnLabels[paramInt];
    return str == null ? this.resultMetaData.columns[paramInt].getNameString() : str;
  }
  
  public String getSchemaName(int paramInt)
    throws SQLException
  {
    checkColumn(paramInt);
    String str = this.resultMetaData.columns[(--paramInt)].getSchemaNameString();
    return str == null ? "" : str;
  }
  
  public int getPrecision(int paramInt)
    throws SQLException
  {
    checkColumn(paramInt);
    Type localType = translateType(this.resultMetaData.columnTypes[(--paramInt)]);
    return localType.getJDBCPrecision();
  }
  
  public int getScale(int paramInt)
    throws SQLException
  {
    checkColumn(paramInt);
    Type localType = translateType(this.resultMetaData.columnTypes[(--paramInt)]);
    return localType.getJDBCScale();
  }
  
  public String getTableName(int paramInt)
    throws SQLException
  {
    checkColumn(paramInt);
    String str = this.resultMetaData.columns[(--paramInt)].getTableNameString();
    return str == null ? "" : str;
  }
  
  public String getCatalogName(int paramInt)
    throws SQLException
  {
    checkColumn(paramInt);
    String str = this.resultMetaData.columns[(--paramInt)].getCatalogNameString();
    return str == null ? "" : str;
  }
  
  public int getColumnType(int paramInt)
    throws SQLException
  {
    checkColumn(paramInt);
    Type localType = translateType(this.resultMetaData.columnTypes[(--paramInt)]);
    return localType.getJDBCTypeCode();
  }
  
  public String getColumnTypeName(int paramInt)
    throws SQLException
  {
    checkColumn(paramInt);
    Type localType = translateType(this.resultMetaData.columnTypes[(--paramInt)]);
    return localType.getNameString();
  }
  
  public boolean isReadOnly(int paramInt)
    throws SQLException
  {
    checkColumn(paramInt);
    return !this.resultMetaData.columns[(--paramInt)].isWriteable();
  }
  
  public boolean isWritable(int paramInt)
    throws SQLException
  {
    checkColumn(paramInt);
    return (this.resultMetaData.colIndexes != null) && (this.resultMetaData.colIndexes[(--paramInt)] > -1);
  }
  
  public boolean isDefinitelyWritable(int paramInt)
    throws SQLException
  {
    checkColumn(paramInt);
    return (this.resultMetaData.colIndexes != null) && (this.resultMetaData.colIndexes[(--paramInt)] > -1);
  }
  
  public String getColumnClassName(int paramInt)
    throws SQLException
  {
    checkColumn(paramInt);
    Type localType = translateType(this.resultMetaData.columnTypes[(--paramInt)]);
    return localType.getJDBCClassName();
  }
  
  public <T> T unwrap(Class<T> paramClass)
    throws SQLException
  {
    if (isWrapperFor(paramClass)) {
      return this;
    }
    throw Util.invalidArgument("iface: " + paramClass);
  }
  
  public boolean isWrapperFor(Class<?> paramClass)
    throws SQLException
  {
    return (paramClass != null) && (paramClass.isAssignableFrom(getClass()));
  }
  
  JDBCResultSetMetaData(ResultMetaData paramResultMetaData, boolean paramBoolean1, boolean paramBoolean2, JDBCConnection paramJDBCConnection)
    throws SQLException
  {
    init(paramResultMetaData, paramJDBCConnection);
  }
  
  void init(ResultMetaData paramResultMetaData, JDBCConnection paramJDBCConnection)
    throws SQLException
  {
    this.resultMetaData = paramResultMetaData;
    this.columnCount = this.resultMetaData.getColumnCount();
    this.useColumnName = true;
    if (paramJDBCConnection == null) {
      return;
    }
    if (paramJDBCConnection.connProperties != null) {
      this.useColumnName = paramJDBCConnection.connProperties.isPropertyTrue("get_column_name", true);
    }
    if (paramJDBCConnection.clientProperties != null) {
      this.translateTTIType = paramJDBCConnection.clientProperties.isPropertyTrue("jdbc.translate_tti_types");
    }
  }
  
  private void checkColumn(int paramInt)
    throws SQLException
  {
    if ((paramInt < 1) || (paramInt > this.columnCount)) {
      throw Util.sqlException(421, String.valueOf(paramInt));
    }
  }
  
  private Type translateType(Type paramType)
  {
    if (this.translateTTIType) {
      if (paramType.isIntervalType()) {
        paramType = ((IntervalType)paramType).getCharacterType();
      } else if (paramType.isDateTimeTypeWithZone()) {
        paramType = ((DateTimeType)paramType).getDateTimeTypeWithoutZone();
      }
    }
    return paramType;
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append(super.toString());
    if (this.columnCount == 0)
    {
      localStringBuffer.append("[columnCount=0]");
      return localStringBuffer.toString();
    }
    localStringBuffer.append('[');
    for (int i = 0; i < this.columnCount; i++)
    {
      JDBCColumnMetaData localJDBCColumnMetaData = getColumnMetaData(i + 1);
      localStringBuffer.append('\n');
      localStringBuffer.append("   column_");
      localStringBuffer.append(i + 1);
      localStringBuffer.append('=');
      localStringBuffer.append(localJDBCColumnMetaData);
      if (i + 1 < this.columnCount)
      {
        localStringBuffer.append(',');
        localStringBuffer.append(' ');
      }
    }
    localStringBuffer.append('\n');
    localStringBuffer.append(']');
    return localStringBuffer.toString();
  }
  
  JDBCColumnMetaData getColumnMetaData(int paramInt)
  {
    JDBCColumnMetaData localJDBCColumnMetaData = new JDBCColumnMetaData();
    try
    {
      localJDBCColumnMetaData.catalogName = getCatalogName(paramInt);
      localJDBCColumnMetaData.columnClassName = getColumnClassName(paramInt);
      localJDBCColumnMetaData.columnDisplaySize = getColumnDisplaySize(paramInt);
      localJDBCColumnMetaData.columnLabel = getColumnLabel(paramInt);
      localJDBCColumnMetaData.columnName = getColumnName(paramInt);
      localJDBCColumnMetaData.columnType = getColumnType(paramInt);
      localJDBCColumnMetaData.isAutoIncrement = isAutoIncrement(paramInt);
      localJDBCColumnMetaData.isCaseSensitive = isCaseSensitive(paramInt);
      localJDBCColumnMetaData.isCurrency = isCurrency(paramInt);
      localJDBCColumnMetaData.isDefinitelyWritable = isDefinitelyWritable(paramInt);
      localJDBCColumnMetaData.isNullable = isNullable(paramInt);
      localJDBCColumnMetaData.isReadOnly = isReadOnly(paramInt);
      localJDBCColumnMetaData.isSearchable = isSearchable(paramInt);
      localJDBCColumnMetaData.isSigned = isSigned(paramInt);
      localJDBCColumnMetaData.isWritable = isWritable(paramInt);
      localJDBCColumnMetaData.precision = getPrecision(paramInt);
      localJDBCColumnMetaData.scale = getScale(paramInt);
      localJDBCColumnMetaData.schemaName = getSchemaName(paramInt);
      localJDBCColumnMetaData.tableName = getTableName(paramInt);
    }
    catch (SQLException localSQLException) {}
    return localJDBCColumnMetaData;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.jdbc.JDBCResultSetMetaData
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */