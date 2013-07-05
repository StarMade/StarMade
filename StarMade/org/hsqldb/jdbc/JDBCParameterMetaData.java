package org.hsqldb.jdbc;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.ParameterMetaData;
import java.sql.SQLException;
import java.sql.Wrapper;
import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.result.ResultMetaData;
import org.hsqldb.types.DateTimeType;
import org.hsqldb.types.IntervalType;
import org.hsqldb.types.Type;

public class JDBCParameterMetaData
  implements ParameterMetaData, Wrapper
{
  ResultMetaData rmd;
  String[] classNames;
  int parameterCount;
  private boolean translateTTIType;

  public int getParameterCount()
    throws SQLException
  {
    return this.parameterCount;
  }

  public int isNullable(int paramInt)
    throws SQLException
  {
    checkRange(paramInt);
    return 2;
  }

  public boolean isSigned(int paramInt)
    throws SQLException
  {
    checkRange(paramInt);
    Type localType = translateType(this.rmd.columnTypes[(--paramInt)]);
    return localType.isNumberType();
  }

  public int getPrecision(int paramInt)
    throws SQLException
  {
    checkRange(paramInt);
    Type localType = translateType(this.rmd.columnTypes[(--paramInt)]);
    if (localType.isDateTimeType())
      return localType.displaySize();
    long l = localType.precision;
    if (l > 2147483647L)
      l = 0L;
    return (int)l;
  }

  public int getScale(int paramInt)
    throws SQLException
  {
    checkRange(paramInt);
    Type localType = translateType(this.rmd.columnTypes[(--paramInt)]);
    return localType.scale;
  }

  public int getParameterType(int paramInt)
    throws SQLException
  {
    checkRange(paramInt);
    Type localType = translateType(this.rmd.columnTypes[(--paramInt)]);
    return localType.getJDBCTypeCode();
  }

  public String getParameterTypeName(int paramInt)
    throws SQLException
  {
    checkRange(paramInt);
    Type localType = translateType(this.rmd.columnTypes[(--paramInt)]);
    return localType.getNameString();
  }

  public String getParameterClassName(int paramInt)
    throws SQLException
  {
    checkRange(paramInt);
    Type localType = translateType(this.rmd.columnTypes[(--paramInt)]);
    return localType.getJDBCClassName();
  }

  public int getParameterMode(int paramInt)
    throws SQLException
  {
    checkRange(paramInt);
    return this.rmd.paramModes[(--paramInt)];
  }

  public <T> T unwrap(Class<T> paramClass)
    throws SQLException
  {
    if (isWrapperFor(paramClass))
      return this;
    throw Util.invalidArgument("iface: " + paramClass);
  }

  public boolean isWrapperFor(Class<?> paramClass)
    throws SQLException
  {
    return (paramClass != null) && (paramClass.isAssignableFrom(getClass()));
  }

  JDBCParameterMetaData(JDBCConnection paramJDBCConnection, ResultMetaData paramResultMetaData)
    throws SQLException
  {
    this.rmd = paramResultMetaData;
    this.parameterCount = this.rmd.getColumnCount();
    if (paramJDBCConnection.clientProperties != null)
      this.translateTTIType = paramJDBCConnection.clientProperties.isPropertyTrue("jdbc.translate_tti_types");
  }

  private Type translateType(Type paramType)
  {
    if (this.translateTTIType)
      if (paramType.isIntervalType())
        paramType = ((IntervalType)paramType).getCharacterType();
      else if (paramType.isDateTimeTypeWithZone())
        paramType = ((DateTimeType)paramType).getDateTimeTypeWithoutZone();
    return paramType;
  }

  void checkRange(int paramInt)
    throws SQLException
  {
    if ((paramInt < 1) || (paramInt > this.parameterCount))
    {
      String str = paramInt + " is out of range";
      throw Util.outOfRangeArgument(str);
    }
  }

  public String toString()
  {
    try
    {
      return toStringImpl();
    }
    catch (Throwable localThrowable)
    {
      return super.toString() + "[toStringImpl_exception=" + localThrowable + "]";
    }
  }

  private String toStringImpl()
    throws Exception
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append(super.toString());
    int i = getParameterCount();
    if (i == 0)
    {
      localStringBuffer.append("[parameterCount=0]");
      return localStringBuffer.toString();
    }
    Method[] arrayOfMethod = getClass().getDeclaredMethods();
    localStringBuffer.append('[');
    int j = arrayOfMethod.length;
    for (int k = 0; k < i; k++)
    {
      localStringBuffer.append('\n');
      localStringBuffer.append("    parameter_");
      localStringBuffer.append(k + 1);
      localStringBuffer.append('=');
      localStringBuffer.append('[');
      for (int m = 0; m < j; m++)
      {
        Method localMethod = arrayOfMethod[m];
        if ((Modifier.isPublic(localMethod.getModifiers())) && (localMethod.getParameterTypes().length == 1))
        {
          localStringBuffer.append(localMethod.getName());
          localStringBuffer.append('=');
          localStringBuffer.append(localMethod.invoke(this, new Object[] { new Integer(k + 1) }));
          if (m + 1 < j)
          {
            localStringBuffer.append(',');
            localStringBuffer.append(' ');
          }
        }
      }
      localStringBuffer.append(']');
      if (k + 1 < i)
      {
        localStringBuffer.append(',');
        localStringBuffer.append(' ');
      }
    }
    localStringBuffer.append('\n');
    localStringBuffer.append(']');
    return localStringBuffer.toString();
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.jdbc.JDBCParameterMetaData
 * JD-Core Version:    0.6.2
 */