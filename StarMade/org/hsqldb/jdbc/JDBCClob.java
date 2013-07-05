package org.hsqldb.jdbc;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Clob;
import java.sql.SQLException;
import org.hsqldb.lib.KMPSearchAlgorithm;
import org.hsqldb.lib.java.JavaSystem;

public class JDBCClob
  implements Clob
{
  private static final long MIN_POS = 1L;
  private static final long MAX_POS = 2147483648L;
  private boolean m_closed;
  private String m_data;
  private final boolean m_createdByConnection;

  public long length()
    throws SQLException
  {
    return getData().length();
  }

  public String getSubString(long paramLong, int paramInt)
    throws SQLException
  {
    String str = getData();
    int i = str.length();
    if ((paramLong < 1L) || (paramLong > i))
      Util.outOfRangeArgument("pos: " + paramLong);
    paramLong -= 1L;
    if ((paramInt < 0) || (paramInt > i - paramLong))
      throw Util.outOfRangeArgument("length: " + paramInt);
    return (paramLong == 0L) && (paramInt == i) ? str : str.substring((int)paramLong, (int)paramLong + paramInt);
  }

  public Reader getCharacterStream()
    throws SQLException
  {
    return new StringReader(getData());
  }

  public InputStream getAsciiStream()
    throws SQLException
  {
    try
    {
      return new ByteArrayInputStream(getData().getBytes("US-ASCII"));
    }
    catch (IOException localIOException)
    {
    }
    return null;
  }

  public long position(String paramString, long paramLong)
    throws SQLException
  {
    String str = getData();
    if (paramLong < 1L)
      throw Util.outOfRangeArgument("start: " + paramLong);
    if ((paramString == null) || (paramLong > 2147483648L))
      return -1L;
    int i = KMPSearchAlgorithm.search(str, paramString, null, (int)paramLong);
    return i == -1 ? -1L : i + 1;
  }

  public long position(Clob paramClob, long paramLong)
    throws SQLException
  {
    String str1 = getData();
    if (paramLong < 1L)
      throw Util.outOfRangeArgument("start: " + paramLong);
    if (paramClob == null)
      return -1L;
    long l1 = str1.length();
    long l2 = paramClob.length();
    paramLong -= 1L;
    if (paramLong > l1 - l2)
      return -1L;
    String str2;
    if ((paramClob instanceof JDBCClob))
      str2 = ((JDBCClob)paramClob).data();
    else
      str2 = paramClob.getSubString(1L, (int)l2);
    int i = KMPSearchAlgorithm.search(str1, str2, null, (int)paramLong);
    return i == -1 ? -1L : i + 1;
  }

  public int setString(long paramLong, String paramString)
    throws SQLException
  {
    if (paramString == null)
      throw Util.nullArgument("str");
    return setString(paramLong, paramString, 0, paramString.length());
  }

  public int setString(long paramLong, String paramString, int paramInt1, int paramInt2)
    throws SQLException
  {
    if (!this.m_createdByConnection)
      throw Util.notSupported();
    String str = getData();
    if (paramString == null)
      throw Util.nullArgument("str");
    int i = paramString.length();
    if ((paramInt1 < 0) || (paramInt1 > i))
      throw Util.outOfRangeArgument("offset: " + paramInt1);
    if (paramInt2 > i - paramInt1)
      throw Util.outOfRangeArgument("len: " + paramInt2);
    if ((paramLong < 1L) || (paramLong > 1L + (2147483647 - paramInt2)))
      throw Util.outOfRangeArgument("pos: " + paramLong);
    int j = str.length();
    int k = (int)(paramLong - 1L);
    StringBuffer localStringBuffer;
    if (k > j - paramInt2)
    {
      localStringBuffer = new StringBuffer(k + paramInt2);
      localStringBuffer.append(str.substring(0, k));
      str = null;
      localStringBuffer.append(paramString.substring(paramInt1, paramInt1 + paramInt2));
      paramString = null;
    }
    else
    {
      localStringBuffer = new StringBuffer(str);
      str = null;
      int m = k;
      for (int n = 0; n < paramInt2; n++)
      {
        localStringBuffer.setCharAt(m, paramString.charAt(paramInt1 + n));
        m++;
      }
      paramString = null;
    }
    setData(localStringBuffer.toString());
    return paramInt2;
  }

  public OutputStream setAsciiStream(final long paramLong)
    throws SQLException
  {
    if (!this.m_createdByConnection)
      throw Util.notSupported();
    checkClosed();
    if ((paramLong < 1L) || (paramLong > 2147483648L))
      throw Util.outOfRangeArgument("pos: " + paramLong);
    return new ByteArrayOutputStream()
    {
      public synchronized void close()
        throws IOException
      {
        try
        {
          JDBCClob.this.setString(paramLong, new String(toByteArray(), "US-ASCII"));
        }
        catch (SQLException localSQLException)
        {
          throw JavaSystem.toIOException(localSQLException);
        }
        finally
        {
          super.close();
        }
      }
    };
  }

  public Writer setCharacterStream(final long paramLong)
    throws SQLException
  {
    if (!this.m_createdByConnection)
      throw Util.notSupported();
    checkClosed();
    if ((paramLong < 1L) || (paramLong > 2147483648L))
      throw Util.outOfRangeArgument("pos: " + paramLong);
    return new StringWriter()
    {
      public synchronized void close()
        throws IOException
      {
        try
        {
          JDBCClob.this.setString(paramLong, toString());
        }
        catch (SQLException localSQLException)
        {
          throw JavaSystem.toIOException(localSQLException);
        }
      }
    };
  }

  public void truncate(long paramLong)
    throws SQLException
  {
    String str = getData();
    long l = str.length();
    if (paramLong != l)
    {
      if ((paramLong < 0L) || (paramLong > l))
        throw Util.outOfRangeArgument("len: " + paramLong);
      setData(str.substring(0, (int)paramLong));
    }
  }

  public synchronized void free()
    throws SQLException
  {
    this.m_closed = true;
    this.m_data = null;
  }

  public Reader getCharacterStream(long paramLong1, long paramLong2)
    throws SQLException
  {
    if (paramLong2 > 2147483647L)
      throw Util.outOfRangeArgument("length: " + paramLong2);
    return new StringReader(getSubString(paramLong1, (int)paramLong2));
  }

  public JDBCClob(String paramString)
    throws SQLException
  {
    if (paramString == null)
      throw Util.nullArgument();
    this.m_data = paramString;
    this.m_createdByConnection = false;
  }

  protected JDBCClob()
  {
    this.m_data = "";
    this.m_createdByConnection = true;
  }

  protected synchronized void checkClosed()
    throws SQLException
  {
    if (this.m_closed)
      throw Util.sqlException(1251);
  }

  protected String data()
    throws SQLException
  {
    return getData();
  }

  private synchronized String getData()
    throws SQLException
  {
    checkClosed();
    return this.m_data;
  }

  private synchronized void setData(String paramString)
    throws SQLException
  {
    checkClosed();
    this.m_data = paramString;
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.jdbc.JDBCClob
 * JD-Core Version:    0.6.2
 */