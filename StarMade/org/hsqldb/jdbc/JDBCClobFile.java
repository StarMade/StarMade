package org.hsqldb.jdbc;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.IllegalCharsetNameException;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hsqldb.lib.FileUtil;
import org.hsqldb.lib.InOutUtil;
import org.hsqldb.lib.KMPSearchAlgorithm;

public class JDBCClobFile
  implements Clob
{
  public static final String TEMP_FILE_PREFIX = "hsql_jdbc_clob_file_";
  public static final String TEMP_FILE_SUFFIX = ".tmp";
  private final File m_file;
  private boolean m_closed;
  private boolean m_deleteOnFree;
  private String m_encoding;
  private Charset m_charset;
  private CharsetEncoder m_encoder;
  private boolean m_fixedWidthCharset;
  private int m_maxCharWidth;
  private List m_streams = new ArrayList();
  
  public long length()
    throws SQLException
  {
    checkClosed();
    if (this.m_fixedWidthCharset) {
      return this.m_file.length() / this.m_maxCharWidth;
    }
    ReaderAdapter localReaderAdapter = null;
    try
    {
      localReaderAdapter = new ReaderAdapter(this.m_file, 0L, 9223372036854775807L);
      long l1 = localReaderAdapter.skip(9223372036854775807L);
      long l2 = l1;
      return l2;
    }
    catch (Exception localException1)
    {
      throw Util.sqlException(localException1);
    }
    finally
    {
      if (localReaderAdapter != null) {
        try
        {
          localReaderAdapter.close();
        }
        catch (Exception localException3) {}
      }
    }
  }
  
  public String getSubString(long paramLong, int paramInt)
    throws SQLException
  {
    Reader localReader = null;
    localCharArrayWriter = null;
    try
    {
      int i = Math.min(8192, paramInt);
      localReader = getCharacterStream(paramLong, paramInt);
      localCharArrayWriter = new CharArrayWriter(i);
      InOutUtil.copy(localReader, localCharArrayWriter, paramInt);
      return localCharArrayWriter.toString();
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
      if (localReader != null) {
        try
        {
          localReader.close();
        }
        catch (Exception localException3) {}
      }
    }
  }
  
  public Reader getCharacterStream()
    throws SQLException
  {
    return getCharacterStream(1L, 9223372036854775807L);
  }
  
  public InputStream getAsciiStream()
    throws SQLException
  {
    JDBCBlobFile.InputStreamAdapter local1;
    try
    {
      local1 = new JDBCBlobFile.InputStreamAdapter(this.m_file, 0L, 9223372036854775807L)
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
            JDBCClobFile.this.m_streams.remove(this);
          }
        }
      };
    }
    catch (Exception localException)
    {
      throw Util.sqlException(localException);
    }
    this.m_streams.add(local1);
    return local1;
  }
  
  public long position(char[] paramArrayOfChar, long paramLong)
    throws SQLException
  {
    if (paramLong < 1L) {
      throw Util.outOfRangeArgument("start: " + paramLong);
    }
    if ((paramArrayOfChar == null) || (paramArrayOfChar.length == 0) || (paramLong > length())) {
      return -1L;
    }
    Reader localReader = null;
    try
    {
      localReader = getCharacterStream(paramLong, 9223372036854775807L);
      long l1 = KMPSearchAlgorithm.search(localReader, paramArrayOfChar, KMPSearchAlgorithm.computeTable(paramArrayOfChar));
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
      if (localReader != null) {
        try
        {
          localReader.close();
        }
        catch (Exception localException3) {}
      }
    }
  }
  
  public long position(String paramString, long paramLong)
    throws SQLException
  {
    return position(paramString == null ? null : paramString.toCharArray(), paramLong);
  }
  
  public long position(Clob paramClob, long paramLong)
    throws SQLException
  {
    if (paramLong < 1L) {
      throw Util.outOfRangeArgument("start: " + paramLong);
    }
    long l;
    if ((l = paramClob == null ? 0L : paramClob.length()) == 0L) {
      return -1L;
    }
    if (l > 2147483647L) {
      throw Util.outOfRangeArgument("pattern.length(): " + l);
    }
    char[] arrayOfChar;
    CharArrayWriter localCharArrayWriter;
    if ((paramClob instanceof JDBCClob))
    {
      arrayOfChar = ((JDBCClob)paramClob).data().toCharArray();
    }
    else
    {
      Reader localReader = null;
      localCharArrayWriter = new CharArrayWriter();
      try
      {
        localReader = paramClob.getCharacterStream();
        InOutUtil.copy(localReader, localCharArrayWriter, l);
        if (localReader != null) {
          try
          {
            localReader.close();
          }
          catch (IOException localIOException1) {}
        }
        arrayOfChar = localCharArrayWriter.toCharArray();
      }
      catch (IOException localIOException2)
      {
        throw Util.sqlException(localIOException2);
      }
      finally
      {
        if (localReader != null) {
          try
          {
            localReader.close();
          }
          catch (IOException localIOException3) {}
        }
      }
    }
    return position(arrayOfChar, paramLong);
  }
  
  public int setString(long paramLong, String paramString)
    throws SQLException
  {
    return setString(paramLong, paramString, 0, paramString == null ? 0 : paramString.length());
  }
  
  public int setString(long paramLong, String paramString, int paramInt1, int paramInt2)
    throws SQLException
  {
    if (paramString == null) {
      throw Util.nullArgument("str");
    }
    Writer localWriter = null;
    try
    {
      localWriter = setCharacterStream(paramLong);
      localWriter.write(paramString, paramInt1, paramInt2);
      return paramInt2;
    }
    catch (Exception localException2)
    {
      throw Util.sqlException(localException2);
    }
    finally
    {
      if (localWriter != null) {
        try
        {
          localWriter.close();
        }
        catch (Exception localException3) {}
      }
    }
  }
  
  public OutputStream setAsciiStream(long paramLong)
    throws SQLException
  {
    if (paramLong < 1L) {
      throw Util.invalidArgument("pos: " + paramLong);
    }
    checkClosed();
    createFile();
    JDBCBlobFile.OutputStreamAdapter local2;
    try
    {
      local2 = new JDBCBlobFile.OutputStreamAdapter(this.m_file, paramLong - 1L)
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
            JDBCClobFile.this.m_streams.remove(this);
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
  
  public Writer setCharacterStream(long paramLong)
    throws SQLException
  {
    if (paramLong < 1L) {
      throw Util.invalidArgument("pos: " + paramLong);
    }
    checkClosed();
    createFile();
    BufferedWriter localBufferedWriter;
    try
    {
      WriterAdapter local3 = new WriterAdapter(this.m_file, paramLong - 1L, // INTERNAL ERROR //

/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.jdbc.JDBCClobFile
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */