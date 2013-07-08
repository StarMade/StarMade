package org.hsqldb.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.IllegalCharsetNameException;
import java.sql.Clob;
import java.sql.SQLException;
import org.hsqldb.HsqlException;
import org.hsqldb.SessionInterface;
import org.hsqldb.types.ClobDataID;
import org.hsqldb.types.ClobInputStream;

public class JDBCClobClient
  implements Clob
{
  ClobDataID originalClob;
  ClobDataID clob;
  SessionInterface session;
  int colIndex;
  private boolean isClosed;
  private boolean isWritable;
  JDBCResultSet resultSet;
  
  public synchronized InputStream getAsciiStream()
    throws SQLException
  {
    checkClosed();
    new InputStream()
    {
      private final byte[] oneChar = new byte[1];
      private boolean m_closed;
      private CharBuffer m_charBuffer = (CharBuffer)CharBuffer.allocate(65536).flip();
      private ByteBuffer m_byteBuffer = ByteBuffer.allocate(1024);
      private Charset m_charset = JDBCClobClient.charsetForName("US-ASCII");
      private CharsetEncoder m_encoder = this.m_charset.newEncoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE);
      private Reader m_reader = JDBCClobClient.this.clob.getCharacterStream(JDBCClobClient.this.session);
      
      public int read()
        throws IOException
      {
        if (isEOF()) {
          return -1;
        }
        synchronized (this.oneChar)
        {
          int i = read(this.oneChar, 0, 1);
          return i == 1 ? this.oneChar[0] : -1;
        }
      }
      
      public int read(byte[] paramAnonymousArrayOfByte, int paramAnonymousInt1, int paramAnonymousInt2)
        throws IOException
      {
        checkClosed();
        if (isEOF()) {
          return -1;
        }
        CharBuffer localCharBuffer = this.m_charBuffer;
        if (localCharBuffer.remaining() == 0)
        {
          localCharBuffer.clear();
          int i = this.m_reader.read(localCharBuffer);
          localCharBuffer.flip();
          if (i < 0)
          {
            setEOF();
            return -1;
          }
          if (i == 0) {
            return 0;
          }
        }
        ByteBuffer localByteBuffer = this.m_byteBuffer.capacity() < paramAnonymousInt2 ? ByteBuffer.allocate(paramAnonymousInt2) : this.m_byteBuffer;
        int k = localCharBuffer.limit();
        int m = localCharBuffer.position();
        localCharBuffer.limit(m + paramAnonymousInt2);
        localByteBuffer.clear();
        int n = localByteBuffer.position();
        CoderResult localCoderResult = this.m_encoder.encode(localCharBuffer, localByteBuffer, false);
        if ((n == localByteBuffer.position()) && (localCoderResult.isUnderflow()))
        {
          localCharBuffer.limit(localCharBuffer.limit() + 1);
          this.m_encoder.encode(localCharBuffer, localByteBuffer, false);
        }
        localCharBuffer.limit(k);
        localByteBuffer.flip();
        int j = localByteBuffer.limit();
        if (j == 0)
        {
          setEOF();
          return -1;
        }
        this.m_byteBuffer = localByteBuffer;
        localByteBuffer.get(paramAnonymousArrayOfByte, paramAnonymousInt1, j);
        return j;
      }
      
      public void close()
        throws IOException
      {
        boolean bool = this.m_closed;
        if (!bool)
        {
          this.m_closed = true;
          this.m_charBuffer = null;
          this.m_charset = null;
          this.m_encoder = null;
          try
          {
            this.m_reader.close();
          }
          catch (Exception localException) {}
        }
      }
      
      private boolean isEOF()
      {
        Reader localReader = this.m_reader;
        return localReader == null;
      }
      
      private void setEOF()
      {
        Reader localReader = this.m_reader;
        if (localReader != null) {
          try
          {
            localReader.close();
          }
          catch (IOException localIOException) {}
        }
        this.m_reader = null;
      }
      
      private void checkClosed()
        throws IOException
      {
        if (JDBCClobClient.this.isClosed()) {
          try
          {
            close();
          }
          catch (Exception localException) {}
        }
        if (this.m_closed) {
          throw new IOException("The stream is closed.");
        }
      }
    };
  }
  
  public synchronized Reader getCharacterStream()
    throws SQLException
  {
    checkClosed();
    return new ClobInputStream(this.session, this.clob, 0L, length());
  }
  
  public synchronized String getSubString(long paramLong, int paramInt)
    throws SQLException
  {
    checkClosed();
    if (!isInLimits(9223372036854775807L, paramLong - 1L, paramInt)) {
      throw Util.outOfRangeArgument();
    }
    try
    {
      return this.clob.getSubString(this.session, paramLong - 1L, paramInt);
    }
    catch (HsqlException localHsqlException)
    {
      throw Util.sqlException(localHsqlException);
    }
  }
  
  public synchronized long length()
    throws SQLException
  {
    checkClosed();
    try
    {
      return this.clob.length(this.session);
    }
    catch (HsqlException localHsqlException)
    {
      throw Util.sqlException(localHsqlException);
    }
  }
  
  public synchronized long position(String paramString, long paramLong)
    throws SQLException
  {
    if (!isInLimits(9223372036854775807L, paramLong - 1L, 0L)) {
      throw Util.outOfRangeArgument();
    }
    checkClosed();
    try
    {
      return this.clob.position(this.session, paramString, paramLong - 1L);
    }
    catch (HsqlException localHsqlException)
    {
      throw Util.sqlException(localHsqlException);
    }
  }
  
  public synchronized long position(Clob paramClob, long paramLong)
    throws SQLException
  {
    if (!isInLimits(9223372036854775807L, paramLong - 1L, 0L)) {
      throw Util.outOfRangeArgument();
    }
    if ((paramClob instanceof JDBCClobClient))
    {
      ClobDataID localClobDataID = ((JDBCClobClient)paramClob).clob;
      try
      {
        return this.clob.position(this.session, localClobDataID, paramLong - 1L);
      }
      catch (HsqlException localHsqlException)
      {
        throw Util.sqlException(localHsqlException);
      }
    }
    return position(paramClob.getSubString(1L, (int)paramClob.length()), paramLong);
  }
  
  public synchronized OutputStream setAsciiStream(final long paramLong)
    throws SQLException
  {
    checkClosed();
    if (paramLong < 1L) {
      throw Util.outOfRangeArgument("pos: " + paramLong);
    }
    if (!this.isWritable) {
      throw Util.notUpdatableColumn();
    }
    startUpdate();
    new OutputStream()
    {
      private long m_position = paramLong - 1L;
      private Charset m_charset = JDBCClobClient.charsetForName("US-ASCII");
      private CharsetDecoder m_decoder = this.m_charset.newDecoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE);
      private CharBuffer m_charBuffer = CharBuffer.allocate(65536);
      private ByteBuffer m_byteBuffer = ByteBuffer.allocate(1024);
      private final byte[] oneByte = new byte[1];
      private boolean m_closed;
      
      public void write(int paramAnonymousInt)
        throws IOException
      {
        synchronized (this.oneByte)
        {
          this.oneByte[0] = ((byte)paramAnonymousInt);
          write(this.oneByte, 0, 1);
        }
      }
      
      public void write(byte[] paramAnonymousArrayOfByte, int paramAnonymousInt1, int paramAnonymousInt2)
        throws IOException
      {
        checkClosed();
        ByteBuffer localByteBuffer = this.m_byteBuffer.capacity() < paramAnonymousInt2 ? ByteBuffer.allocate(paramAnonymousInt2) : this.m_byteBuffer;
        if (this.m_charBuffer.remaining() < paramAnonymousInt2) {
          flush0();
        }
        CharBuffer localCharBuffer = this.m_charBuffer.capacity() < paramAnonymousInt2 ? CharBuffer.allocate(paramAnonymousInt2) : this.m_charBuffer;
        localByteBuffer.clear();
        localByteBuffer.put(paramAnonymousArrayOfByte, paramAnonymousInt1, paramAnonymousInt2);
        localByteBuffer.flip();
        this.m_decoder.decode(localByteBuffer, localCharBuffer, false);
        if (localCharBuffer.remaining() == 0) {
          flush();
        }
      }
      
      public void flush()
        throws IOException
      {
        checkClosed();
        flush0();
      }
      
      public void close()
        throws IOException
      {
        if (!this.m_closed) {
          try
          {
            flush0();
          }
          finally
          {
            this.m_closed = true;
            this.m_byteBuffer = null;
            this.m_charBuffer = null;
            this.m_charset = null;
            this.m_decoder = null;
          }
        }
      }
      
      private void checkClosed()
        throws IOException
      {
        if (JDBCClobClient.this.isClosed()) {
          try
          {
            close();
          }
          catch (Exception localException) {}
        }
        if (this.m_closed) {
          throw new IOException("The stream is closed.");
        }
      }
      
      private void flush0()
        throws IOException
      {
        CharBuffer localCharBuffer = this.m_charBuffer;
        localCharBuffer.flip();
        char[] arrayOfChar = new char[localCharBuffer.length()];
        localCharBuffer.get(arrayOfChar);
        localCharBuffer.clear();
        try
        {
          JDBCClobClient.this.clob.setChars(JDBCClobClient.this.session, this.m_position, arrayOfChar, 0, arrayOfChar.length);
        }
        catch (Exception localException)
        {
          throw new IOException(localException.toString());
        }
        this.m_position += arrayOfChar.length;
      }
    };
  }
  
  public synchronized Writer setCharacterStream(final long paramLong)
    throws SQLException
  {
    checkClosed();
    if (paramLong < 1L) {
      throw Util.outOfRangeArgument("pos: " + paramLong);
    }
    if (!this.isWritable) {
      throw Util.notUpdatableColumn();
    }
    startUpdate();
    new Writer()
    {
      private long m_clobPosition = paramLong - 1L;
      private boolean m_closed;
      
      public void write(char[] paramAnonymousArrayOfChar, int paramAnonymousInt1, int paramAnonymousInt2)
        throws IOException
      {
        checkClosed();
        JDBCClobClient.this.clob.setChars(JDBCClobClient.this.session, this.m_clobPosition, paramAnonymousArrayOfChar, paramAnonymousInt1, paramAnonymousInt2);
        this.m_clobPosition += paramAnonymousInt2;
      }
      
      public void flush()
        throws IOException
      {}
      
      public void close()
        throws IOException
      {
        this.m_closed = true;
      }
      
      private void checkClosed()
        throws IOException
      {
        if ((this.m_closed) || (JDBCClobClient.this.isClosed())) {
          throw new IOException("The stream is closed");
        }
      }
    };
  }
  
  public synchronized int setString(long paramLong, String paramString)
    throws SQLException
  {
    return setString(paramLong, paramString, 0, paramString.length());
  }
  
  public synchronized int setString(long paramLong, String paramString, int paramInt1, int paramInt2)
    throws SQLException
  {
    if (!isInLimits(paramString.length(), paramInt1, paramInt2)) {
      throw Util.outOfRangeArgument();
    }
    checkClosed();
    if (paramLong < 1L) {
      throw Util.outOfRangeArgument("pos: " + paramLong);
    }
    if (!this.isWritable) {
      throw Util.notUpdatableColumn();
    }
    startUpdate();
    paramString = paramString.substring(paramInt1, paramInt1 + paramInt2);
    try
    {
      this.clob.setString(this.session, paramLong - 1L, paramString);
      return paramInt2;
    }
    catch (HsqlException localHsqlException)
    {
      throw Util.sqlException(localHsqlException);
    }
  }
  
  public synchronized void truncate(long paramLong)
    throws SQLException
  {
    if (paramLong < 0L) {
      throw Util.outOfRangeArgument("len: " + paramLong);
    }
    checkClosed();
    try
    {
      this.clob.truncate(this.session, paramLong);
    }
    catch (HsqlException localHsqlException)
    {
      throw Util.sqlException(localHsqlException);
    }
  }
  
  public synchronized void free()
    throws SQLException
  {
    this.isClosed = true;
    this.clob = null;
    this.session = null;
  }
  
  public synchronized Reader getCharacterStream(long paramLong1, long paramLong2)
    throws SQLException
  {
    if (!isInLimits(9223372036854775807L, paramLong1 - 1L, paramLong2)) {
      throw Util.outOfRangeArgument();
    }
    checkClosed();
    return new ClobInputStream(this.session, this.clob, paramLong1 - 1L, paramLong2);
  }
  
  char[] getChars(long paramLong, int paramInt)
    throws SQLException
  {
    try
    {
      return this.clob.getChars(this.session, paramLong - 1L, paramInt);
    }
    catch (HsqlException localHsqlException)
    {
      throw Util.sqlException(localHsqlException);
    }
  }
  
  public JDBCClobClient(SessionInterface paramSessionInterface, ClobDataID paramClobDataID)
  {
    this.session = paramSessionInterface;
    this.clob = paramClobDataID;
  }
  
  public ClobDataID getClob()
  {
    return this.clob;
  }
  
  public synchronized boolean isClosed()
  {
    return this.isClosed;
  }
  
  public synchronized void setWritable(JDBCResultSet paramJDBCResultSet, int paramInt)
  {
    this.isWritable = true;
    this.resultSet = paramJDBCResultSet;
    this.colIndex = paramInt;
  }
  
  public synchronized void clearUpdates()
  {
    if (this.originalClob != null)
    {
      this.clob = this.originalClob;
      this.originalClob = null;
    }
  }
  
  private void startUpdate()
    throws SQLException
  {
    if (this.originalClob != null) {
      return;
    }
    this.originalClob = this.clob;
    this.clob = ((ClobDataID)this.clob.duplicate(this.session));
    this.resultSet.startUpdate(this.colIndex + 1);
    this.resultSet.preparedStatement.parameterValues[this.colIndex] = this.clob;
    this.resultSet.preparedStatement.parameterSet[this.colIndex] = Boolean.TRUE;
  }
  
  private void checkClosed()
    throws SQLException
  {
    if (this.isClosed) {
      throw Util.sqlException(1251);
    }
  }
  
  static boolean isInLimits(long paramLong1, long paramLong2, long paramLong3)
  {
    return (paramLong1 >= 0L) && (paramLong2 >= 0L) && (paramLong3 >= 0L) && (paramLong2 <= paramLong1 - paramLong3);
  }
  
  protected static Charset charsetForName(String paramString)
    throws SQLException
  {
    String str = paramString;
    if (str == null) {
      str = Charset.defaultCharset().name();
    }
    try
    {
      if (Charset.isSupported(str)) {
        return Charset.forName(str);
      }
    }
    catch (IllegalCharsetNameException localIllegalCharsetNameException) {}
    throw Util.sqlException(new UnsupportedEncodingException(str));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.jdbc.JDBCClobClient
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */