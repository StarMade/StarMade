package org.hsqldb.jdbc;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hsqldb.lib.CountdownInputStream;
import org.hsqldb.lib.FileUtil;
import org.hsqldb.lib.InOutUtil;
import org.hsqldb.lib.KMPSearchAlgorithm;

public class JDBCBlobFile
  implements Blob
{
  public static final String TEMP_FILE_PREFIX = "hsql_jdbc_blob_file_";
  public static final String TEMP_FILE_SUFFIX = ".tmp";
  private final File m_file;
  private boolean m_closed;
  private boolean m_deleteOnFree;
  private List m_streams = new ArrayList();

  public long length()
    throws SQLException
  {
    checkClosed();
    try
    {
      return this.m_file.length();
    }
    catch (Exception localException)
    {
      throw Util.sqlException(localException);
    }
  }

  public byte[] getBytes(long paramLong, int paramInt)
    throws SQLException
  {
    InputStream localInputStream = null;
    ByteArrayOutputStream localByteArrayOutputStream = null;
    int i = Math.min(8192, paramInt);
    try
    {
      localInputStream = getBinaryStream(paramLong, paramInt);
      localByteArrayOutputStream = new ByteArrayOutputStream(i);
      InOutUtil.copy(localInputStream, localByteArrayOutputStream, paramInt);
    }
    catch (SQLException localSQLException)
    {
      throw localSQLException;
    }
    catch (Exception localException2)
    {
      throw Util.sqlException(localException2);
    }
    finally
    {
      if (localInputStream != null)
        try
        {
          localInputStream.close();
        }
        catch (Exception localException3)
        {
        }
    }
    return localByteArrayOutputStream.toByteArray();
  }

  public InputStream getBinaryStream()
    throws SQLException
  {
    return getBinaryStream(1L, 9223372036854775807L);
  }

  public long position(byte[] paramArrayOfByte, long paramLong)
    throws SQLException
  {
    if (paramLong < 1L)
      throw Util.outOfRangeArgument("start: " + paramLong);
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length == 0) || (paramLong > length()))
      return -1L;
    InputStream localInputStream = null;
    try
    {
      localInputStream = getBinaryStream(paramLong, 9223372036854775807L);
      long l1 = KMPSearchAlgorithm.search(localInputStream, paramArrayOfByte, KMPSearchAlgorithm.computeTable(paramArrayOfByte));
      long l2 = l1 == -1L ? -1L : paramLong + l1;
      return l2;
    }
    catch (SQLException localSQLException)
    {
      throw localSQLException;
    }
    catch (Exception localException1)
    {
      throw Util.sqlException(localException1);
    }
    finally
    {
      if (localInputStream != null)
        try
        {
          localInputStream.close();
        }
        catch (Exception localException3)
        {
        }
    }
  }

  public long position(Blob paramBlob, long paramLong)
    throws SQLException
  {
    if (paramLong < 1L)
      throw Util.outOfRangeArgument("start: " + paramLong);
    long l;
    if (((l = paramBlob == null ? 0L : paramBlob.length()) == 0L) || (paramLong > length()))
      return -1L;
    if (l > 2147483647L)
      throw Util.outOfRangeArgument("pattern.length(): " + l);
    byte[] arrayOfByte;
    if ((paramBlob instanceof JDBCBlob))
      arrayOfByte = ((JDBCBlob)paramBlob).data();
    else
      arrayOfByte = paramBlob.getBytes(1L, (int)l);
    return position(arrayOfByte, paramLong);
  }

  public int setBytes(long paramLong, byte[] paramArrayOfByte)
    throws SQLException
  {
    return setBytes(paramLong, paramArrayOfByte, 0, paramArrayOfByte == null ? 0 : paramArrayOfByte.length);
  }

  public int setBytes(long paramLong, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws SQLException
  {
    if (paramArrayOfByte == null)
      throw Util.nullArgument("bytes");
    OutputStream localOutputStream = setBinaryStream(paramLong);
    try
    {
      localOutputStream.write(paramArrayOfByte, paramInt1, paramInt2);
    }
    catch (Exception localException2)
    {
      throw Util.sqlException(localException2);
    }
    finally
    {
      try
      {
        localOutputStream.close();
      }
      catch (Exception localException3)
      {
      }
    }
    return paramInt2;
  }

  public OutputStream setBinaryStream(long paramLong)
    throws SQLException
  {
    if (paramLong < 1L)
      throw Util.invalidArgument("pos: " + paramLong);
    checkClosed();
    createFile();
    OutputStreamAdapter local1;
    try
    {
      local1 = new OutputStreamAdapter(this.m_file, paramLong - 1L)
      {
        public void close()
          throws IOException
        {
          try
          {
            super.close();
          }
          finally
          {
            JDBCBlobFile.this.m_streams.remove(this);
          }
        }
      };
    }
    catch (Exception localException)
    {
      throw Util.sqlException(localException);
    }
    this.m_streams.add(local1);
    BufferedOutputStream localBufferedOutputStream = new BufferedOutputStream(local1);
    return localBufferedOutputStream;
  }

  public void truncate(long paramLong)
    throws SQLException
  {
    if (paramLong < 0L)
      throw Util.invalidArgument("len: " + paramLong);
    checkClosed();
    RandomAccessFile localRandomAccessFile = null;
    try
    {
      localRandomAccessFile = new RandomAccessFile(this.m_file, "rw");
      localRandomAccessFile.setLength(paramLong);
    }
    catch (Exception localException2)
    {
      throw Util.sqlException(localException2);
    }
    finally
    {
      if (localRandomAccessFile != null)
        try
        {
          localRandomAccessFile.close();
        }
        catch (Exception localException3)
        {
        }
    }
  }

  public synchronized void free()
    throws SQLException
  {
    if (this.m_closed)
      return;
    this.m_closed = true;
    ArrayList localArrayList = new ArrayList();
    localArrayList.addAll(this.m_streams);
    this.m_streams = null;
    Iterator localIterator = localArrayList.iterator();
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      if ((localObject instanceof InputStream))
        try
        {
          ((InputStream)localObject).close();
        }
        catch (Exception localException2)
        {
        }
      else if ((localObject instanceof OutputStream))
        try
        {
          ((OutputStream)localObject).close();
        }
        catch (Exception localException3)
        {
        }
    }
    if (this.m_deleteOnFree)
      try
      {
        this.m_file.delete();
      }
      catch (Exception localException1)
      {
      }
  }

  public InputStream getBinaryStream(long paramLong1, long paramLong2)
    throws SQLException
  {
    if (paramLong1 < 1L)
      throw Util.outOfRangeArgument("pos: " + paramLong1);
    checkClosed();
    InputStreamAdapter local2;
    try
    {
      local2 = new InputStreamAdapter(this.m_file, paramLong1 - 1L, paramLong2)
      {
        public void close()
          throws IOException
        {
          try
          {
            super.close();
          }
          finally
          {
            JDBCBlobFile.this.m_streams.remove(this);
          }
        }
      };
    }
    catch (Exception localException)
    {
      throw Util.sqlException(localException);
    }
    this.m_streams.add(local2);
    return local2;
  }

  public File getFile()
  {
    return this.m_file;
  }

  public boolean isDeleteOnFree()
  {
    return this.m_deleteOnFree;
  }

  public void setDeleteOnFree(boolean paramBoolean)
  {
    this.m_deleteOnFree = paramBoolean;
  }

  protected void finalize()
    throws Throwable
  {
    try
    {
      super.finalize();
    }
    finally
    {
      free();
    }
  }

  public JDBCBlobFile()
    throws SQLException
  {
    this(true);
  }

  public JDBCBlobFile(boolean paramBoolean)
    throws SQLException
  {
    this.m_deleteOnFree = paramBoolean;
    try
    {
      this.m_file = File.createTempFile("hsql_jdbc_blob_file_", ".tmp").getCanonicalFile();
    }
    catch (Exception localException)
    {
      throw Util.sqlException(localException);
    }
  }

  public JDBCBlobFile(File paramFile)
    throws SQLException
  {
    this(paramFile, false);
  }

  public JDBCBlobFile(File paramFile, boolean paramBoolean)
    throws SQLException
  {
    this.m_deleteOnFree = paramBoolean;
    try
    {
      this.m_file = paramFile.getCanonicalFile();
    }
    catch (Exception localException)
    {
      throw Util.sqlException(localException);
    }
    checkIsFile(false);
  }

  protected final void checkIsFile(boolean paramBoolean)
    throws SQLException
  {
    boolean bool1 = false;
    boolean bool2 = false;
    try
    {
      bool1 = this.m_file.exists();
    }
    catch (Exception localException1)
    {
      throw Util.sqlException(localException1);
    }
    if (bool1)
      try
      {
        bool2 = this.m_file.isFile();
      }
      catch (Exception localException2)
      {
        throw Util.sqlException(localException2);
      }
    if (bool1)
    {
      if (!bool2)
        throw Util.invalidArgument("Is not a file: " + this.m_file);
    }
    else if (paramBoolean)
      throw Util.invalidArgument("Does not exist: " + this.m_file);
  }

  private void checkClosed()
    throws SQLException
  {
    if (this.m_closed)
      throw Util.sqlException(1251);
  }

  private void createFile()
    throws SQLException
  {
    try
    {
      if (!this.m_file.exists())
      {
        FileUtil.getFileUtil().makeParentDirectories(this.m_file);
        this.m_file.createNewFile();
      }
    }
    catch (Exception localException)
    {
      throw Util.sqlException(localException);
    }
    checkIsFile(true);
  }

  static class InputStreamAdapter extends InputStream
  {
    private final CountdownInputStream m_countdownInputStream;

    InputStreamAdapter(File paramFile, long paramLong1, long paramLong2)
      throws FileNotFoundException, IOException
    {
      if (paramFile == null)
        throw new NullPointerException("file");
      if (paramLong1 < 0L)
        throw new IllegalArgumentException("pos: " + paramLong1);
      if (paramLong2 < 0L)
        throw new IllegalArgumentException("length: " + paramLong2);
      FileInputStream localFileInputStream = new FileInputStream(paramFile);
      if (paramLong1 > 0L)
        long l = localFileInputStream.skip(paramLong1);
      BufferedInputStream localBufferedInputStream = new BufferedInputStream(localFileInputStream);
      CountdownInputStream localCountdownInputStream = new CountdownInputStream(localBufferedInputStream);
      localCountdownInputStream.setCount(paramLong2);
      this.m_countdownInputStream = localCountdownInputStream;
    }

    public int available()
      throws IOException
    {
      return this.m_countdownInputStream.available();
    }

    public int read()
      throws IOException
    {
      return this.m_countdownInputStream.read();
    }

    public int read(byte[] paramArrayOfByte)
      throws IOException
    {
      return this.m_countdownInputStream.read(paramArrayOfByte);
    }

    public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws IOException
    {
      return this.m_countdownInputStream.read(paramArrayOfByte, paramInt1, paramInt2);
    }

    public long skip(long paramLong)
      throws IOException
    {
      return this.m_countdownInputStream.skip(paramLong);
    }

    public void close()
      throws IOException
    {
      this.m_countdownInputStream.close();
    }
  }

  protected static class OutputStreamAdapter extends OutputStream
  {
    private final RandomAccessFile m_randomAccessFile;

    public OutputStreamAdapter(File paramFile, long paramLong)
      throws FileNotFoundException, IOException
    {
      if (paramLong < 0L)
        throw new IllegalArgumentException("pos: " + paramLong);
      this.m_randomAccessFile = new RandomAccessFile(paramFile, "rw");
      this.m_randomAccessFile.seek(paramLong);
    }

    public void write(int paramInt)
      throws IOException
    {
      this.m_randomAccessFile.write(paramInt);
    }

    public void write(byte[] paramArrayOfByte)
      throws IOException
    {
      this.m_randomAccessFile.write(paramArrayOfByte);
    }

    public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws IOException
    {
      this.m_randomAccessFile.write(paramArrayOfByte, paramInt1, paramInt2);
    }

    public void flush()
      throws IOException
    {
      this.m_randomAccessFile.getFD().sync();
    }

    public void close()
      throws IOException
    {
      this.m_randomAccessFile.close();
    }
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.jdbc.JDBCBlobFile
 * JD-Core Version:    0.6.2
 */