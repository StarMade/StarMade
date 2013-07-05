package org.hsqldb.jdbc;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import org.hsqldb.lib.KMPSearchAlgorithm;
import org.hsqldb.lib.java.JavaSystem;

public class JDBCBlob
  implements Blob
{
  public static final long MIN_POS = 1L;
  public static final long MAX_POS = 2147483648L;
  private boolean m_closed;
  private byte[] m_data;
  private final boolean m_createdByConnection;

  public long length()
    throws SQLException
  {
    return getData().length;
  }

  public byte[] getBytes(long paramLong, int paramInt)
    throws SQLException
  {
    byte[] arrayOfByte1 = getData();
    int i = arrayOfByte1.length;
    if ((paramLong < 1L) || (paramLong > 1L + i))
      throw Util.outOfRangeArgument("pos: " + paramLong);
    paramLong -= 1L;
    if ((paramInt < 0) || (paramInt > i - paramLong))
      throw Util.outOfRangeArgument("length: " + paramInt);
    byte[] arrayOfByte2 = new byte[paramInt];
    System.arraycopy(arrayOfByte1, (int)paramLong, arrayOfByte2, 0, paramInt);
    return arrayOfByte2;
  }

  public InputStream getBinaryStream()
    throws SQLException
  {
    return new ByteArrayInputStream(getData());
  }

  public long position(byte[] paramArrayOfByte, long paramLong)
    throws SQLException
  {
    byte[] arrayOfByte = getData();
    int i = arrayOfByte.length;
    if (paramLong < 1L)
      throw Util.outOfRangeArgument("start: " + paramLong);
    if ((paramLong > i) || (paramArrayOfByte == null))
      return -1L;
    int j = (int)paramLong - 1;
    int k = paramArrayOfByte.length;
    if ((k == 0) || (j > i - k))
      return -1L;
    int m = KMPSearchAlgorithm.search(arrayOfByte, paramArrayOfByte, KMPSearchAlgorithm.computeTable(paramArrayOfByte), j);
    return m == -1 ? -1L : m + 1;
  }

  public long position(Blob paramBlob, long paramLong)
    throws SQLException
  {
    byte[] arrayOfByte1 = getData();
    int i = arrayOfByte1.length;
    if (paramLong < 1L)
      throw Util.outOfRangeArgument("start: " + paramLong);
    if ((paramLong > i) || (paramBlob == null))
      return -1L;
    int j = (int)paramLong - 1;
    long l = paramBlob.length();
    if ((l == 0L) || (j > i - l))
      return -1L;
    int k = (int)l;
    byte[] arrayOfByte2;
    if ((paramBlob instanceof JDBCBlob))
      arrayOfByte2 = ((JDBCBlob)paramBlob).data();
    else
      arrayOfByte2 = paramBlob.getBytes(1L, k);
    int m = KMPSearchAlgorithm.search(arrayOfByte1, arrayOfByte2, KMPSearchAlgorithm.computeTable(arrayOfByte2), j);
    return m == -1 ? -1L : m + 1;
  }

  public int setBytes(long paramLong, byte[] paramArrayOfByte)
    throws SQLException
  {
    if (paramArrayOfByte == null)
      throw Util.nullArgument("bytes");
    return setBytes(paramLong, paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public int setBytes(long paramLong, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws SQLException
  {
    if (!this.m_createdByConnection)
      throw Util.notSupported();
    if (paramArrayOfByte == null)
      throw Util.nullArgument("bytes");
    if ((paramInt1 < 0) || (paramInt1 > paramArrayOfByte.length))
      throw Util.outOfRangeArgument("offset: " + paramInt1);
    if (paramInt2 > paramArrayOfByte.length - paramInt1)
      throw Util.outOfRangeArgument("len: " + paramInt2);
    if ((paramLong < 1L) || (paramLong > 1L + (2147483647 - paramInt2)))
      throw Util.outOfRangeArgument("pos: " + paramLong);
    paramLong -= 1L;
    Object localObject = getData();
    int i = localObject.length;
    if (paramLong + paramInt2 > i)
    {
      byte[] arrayOfByte = new byte[(int)paramLong + paramInt2];
      System.arraycopy(localObject, 0, arrayOfByte, 0, i);
      localObject = arrayOfByte;
      arrayOfByte = null;
    }
    System.arraycopy(paramArrayOfByte, paramInt1, localObject, (int)paramLong, paramInt2);
    checkClosed();
    setData((byte[])localObject);
    return paramInt2;
  }

  public OutputStream setBinaryStream(final long paramLong)
    throws SQLException
  {
    if (!this.m_createdByConnection)
      throw Util.notSupported();
    if ((paramLong < 1L) || (paramLong > 2147483648L))
      throw Util.outOfRangeArgument("pos: " + paramLong);
    checkClosed();
    return new ByteArrayOutputStream()
    {
      public synchronized void close()
        throws IOException
      {
        try
        {
          JDBCBlob.this.setBytes(paramLong, toByteArray());
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

  public void truncate(long paramLong)
    throws SQLException
  {
    byte[] arrayOfByte1 = getData();
    if ((paramLong < 0L) || (paramLong > arrayOfByte1.length))
      throw Util.outOfRangeArgument("len: " + paramLong);
    if (paramLong == arrayOfByte1.length)
      return;
    byte[] arrayOfByte2 = new byte[(int)paramLong];
    System.arraycopy(arrayOfByte1, 0, arrayOfByte2, 0, (int)paramLong);
    checkClosed();
    setData(arrayOfByte2);
  }

  public synchronized void free()
    throws SQLException
  {
    this.m_closed = true;
    this.m_data = null;
  }

  public InputStream getBinaryStream(long paramLong1, long paramLong2)
    throws SQLException
  {
    byte[] arrayOfByte1 = getData();
    int i = arrayOfByte1.length;
    if ((paramLong1 < 1L) || (paramLong1 > i))
      throw Util.outOfRangeArgument("pos: " + paramLong1);
    paramLong1 -= 1L;
    if ((paramLong2 < 0L) || (paramLong2 > i - paramLong1))
      throw Util.outOfRangeArgument("length: " + paramLong2);
    if ((paramLong1 == 0L) && (paramLong2 == i))
      return new ByteArrayInputStream(arrayOfByte1);
    byte[] arrayOfByte2 = new byte[(int)paramLong2];
    System.arraycopy(arrayOfByte1, (int)paramLong1, arrayOfByte2, 0, (int)paramLong2);
    return new ByteArrayInputStream(arrayOfByte2);
  }

  public JDBCBlob(byte[] paramArrayOfByte)
    throws SQLException
  {
    if (paramArrayOfByte == null)
      throw Util.nullArgument();
    this.m_data = paramArrayOfByte;
    this.m_createdByConnection = false;
  }

  protected JDBCBlob()
  {
    this.m_data = new byte[0];
    this.m_createdByConnection = true;
  }

  protected synchronized void checkClosed()
    throws SQLException
  {
    if (this.m_closed)
      throw Util.sqlException(1251);
  }

  protected byte[] data()
    throws SQLException
  {
    return getData();
  }

  private synchronized byte[] getData()
    throws SQLException
  {
    checkClosed();
    return this.m_data;
  }

  private synchronized void setData(byte[] paramArrayOfByte)
    throws SQLException
  {
    checkClosed();
    this.m_data = paramArrayOfByte;
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.jdbc.JDBCBlob
 * JD-Core Version:    0.6.2
 */