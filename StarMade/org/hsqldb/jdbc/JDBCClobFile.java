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
    if (this.m_fixedWidthCharset)
      return this.m_file.length() / this.m_maxCharWidth;
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
      if (localReaderAdapter != null)
        try
        {
          localReaderAdapter.close();
        }
        catch (Exception localException3)
        {
        }
    }
  }

  public String getSubString(long paramLong, int paramInt)
    throws SQLException
  {
    Reader localReader = null;
    CharArrayWriter localCharArrayWriter = null;
    try
    {
      int i = Math.min(8192, paramInt);
      localReader = getCharacterStream(paramLong, paramInt);
      localCharArrayWriter = new CharArrayWriter(i);
      InOutUtil.copy(localReader, localCharArrayWriter, paramInt);
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
      if (localReader != null)
        try
        {
          localReader.close();
        }
        catch (Exception localException3)
        {
        }
    }
    return localCharArrayWriter.toString();
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
    if (paramLong < 1L)
      throw Util.outOfRangeArgument("start: " + paramLong);
    if ((paramArrayOfChar == null) || (paramArrayOfChar.length == 0) || (paramLong > length()))
      return -1L;
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
      if (localReader != null)
        try
        {
          localReader.close();
        }
        catch (Exception localException3)
        {
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
    if (paramLong < 1L)
      throw Util.outOfRangeArgument("start: " + paramLong);
    long l;
    if ((l = paramClob == null ? 0L : paramClob.length()) == 0L)
      return -1L;
    if (l > 2147483647L)
      throw Util.outOfRangeArgument("pattern.length(): " + l);
    char[] arrayOfChar;
    if ((paramClob instanceof JDBCClob))
    {
      arrayOfChar = ((JDBCClob)paramClob).data().toCharArray();
    }
    else
    {
      Reader localReader = null;
      CharArrayWriter localCharArrayWriter = new CharArrayWriter();
      try
      {
        localReader = paramClob.getCharacterStream();
        InOutUtil.copy(localReader, localCharArrayWriter, l);
      }
      catch (IOException localIOException2)
      {
        throw Util.sqlException(localIOException2);
      }
      finally
      {
        if (localReader != null)
          try
          {
            localReader.close();
          }
          catch (IOException localIOException3)
          {
          }
      }
      arrayOfChar = localCharArrayWriter.toCharArray();
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
    if (paramString == null)
      throw Util.nullArgument("str");
    Writer localWriter = null;
    try
    {
      localWriter = setCharacterStream(paramLong);
      localWriter.write(paramString, paramInt1, paramInt2);
    }
    catch (Exception localException2)
    {
      throw Util.sqlException(localException2);
    }
    finally
    {
      if (localWriter != null)
        try
        {
          localWriter.close();
        }
        catch (Exception localException3)
        {
        }
    }
    return paramInt2;
  }

  public OutputStream setAsciiStream(long paramLong)
    throws SQLException
  {
    if (paramLong < 1L)
      throw Util.invalidArgument("pos: " + paramLong);
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
    // Byte code:
    //   0: lload_1
    //   1: lconst_1
    //   2: lcmp
    //   3: ifge +26 -> 29
    //   6: new 29	java/lang/StringBuilder
    //   9: dup
    //   10: invokespecial 30	java/lang/StringBuilder:<init>	()V
    //   13: ldc 60
    //   15: invokevirtual 32	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   18: lload_1
    //   19: invokevirtual 33	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   22: invokevirtual 34	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   25: invokestatic 61	org/hsqldb/jdbc/Util:invalidArgument	(Ljava/lang/String;)Ljava/sql/SQLException;
    //   28: athrow
    //   29: aload_0
    //   30: invokevirtual 7	org/hsqldb/jdbc/JDBCClobFile:checkClosed	()V
    //   33: aload_0
    //   34: invokevirtual 62	org/hsqldb/jdbc/JDBCClobFile:createFile	()V
    //   37: new 65	org/hsqldb/jdbc/JDBCClobFile$3
    //   40: dup
    //   41: aload_0
    //   42: aload_0
    //   43: getfield 8	org/hsqldb/jdbc/JDBCClobFile:m_file	Ljava/io/File;
    //   46: lload_1
    //   47: lconst_1
    //   48: lsub
    //   49: invokespecial 66	org/hsqldb/jdbc/JDBCClobFile$3:<init>	(Lorg/hsqldb/jdbc/JDBCClobFile;Ljava/io/File;J)V
    //   52: astore 4
    //   54: new 67	java/io/BufferedWriter
    //   57: dup
    //   58: aload 4
    //   60: invokespecial 68	java/io/BufferedWriter:<init>	(Ljava/io/Writer;)V
    //   63: astore_3
    //   64: goto +11 -> 75
    //   67: astore 4
    //   69: aload 4
    //   71: invokestatic 17	org/hsqldb/jdbc/Util:sqlException	(Ljava/lang/Throwable;)Ljava/sql/SQLException;
    //   74: athrow
    //   75: aload_0
    //   76: getfield 6	org/hsqldb/jdbc/JDBCClobFile:m_streams	Ljava/util/List;
    //   79: aload_3
    //   80: invokeinterface 28 2 0
    //   85: pop
    //   86: aload_3
    //   87: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   37	64	67	java/lang/Exception
  }

  public void truncate(long paramLong)
    throws SQLException
  {
    if (paramLong < 0L)
      throw Util.invalidArgument("len: " + paramLong);
    checkClosed();
    ReaderAdapter localReaderAdapter = null;
    RandomAccessFile localRandomAccessFile = null;
    try
    {
      localReaderAdapter = new ReaderAdapter(this.m_file, paramLong, 9223372036854775807L);
      long l = localReaderAdapter.getFilePointer();
      localReaderAdapter.close();
      localRandomAccessFile = new RandomAccessFile(this.m_file, "rw");
      localRandomAccessFile.setLength(l);
    }
    catch (Exception localException3)
    {
      throw Util.sqlException(localException3);
    }
    finally
    {
      if (localReaderAdapter != null)
        try
        {
          localReaderAdapter.close();
        }
        catch (Exception localException4)
        {
        }
      if (localRandomAccessFile != null)
        try
        {
          localRandomAccessFile.close();
        }
        catch (Exception localException5)
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

  public Reader getCharacterStream(long paramLong1, long paramLong2)
    throws SQLException
  {
    // Byte code:
    //   0: lload_1
    //   1: lconst_1
    //   2: lcmp
    //   3: ifge +26 -> 29
    //   6: new 29	java/lang/StringBuilder
    //   9: dup
    //   10: invokespecial 30	java/lang/StringBuilder:<init>	()V
    //   13: ldc 60
    //   15: invokevirtual 32	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   18: lload_1
    //   19: invokevirtual 33	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   22: invokevirtual 34	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   25: invokestatic 35	org/hsqldb/jdbc/Util:outOfRangeArgument	(Ljava/lang/String;)Ljava/sql/SQLException;
    //   28: athrow
    //   29: lload_1
    //   30: lconst_1
    //   31: lsub
    //   32: lstore_1
    //   33: lload_3
    //   34: lconst_0
    //   35: lcmp
    //   36: ifge +26 -> 62
    //   39: new 29	java/lang/StringBuilder
    //   42: dup
    //   43: invokespecial 30	java/lang/StringBuilder:<init>	()V
    //   46: ldc 89
    //   48: invokevirtual 32	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   51: lload_3
    //   52: invokevirtual 33	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   55: invokevirtual 34	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   58: invokestatic 35	org/hsqldb/jdbc/Util:outOfRangeArgument	(Ljava/lang/String;)Ljava/sql/SQLException;
    //   61: athrow
    //   62: new 90	org/hsqldb/jdbc/JDBCClobFile$4
    //   65: dup
    //   66: aload_0
    //   67: aload_0
    //   68: getfield 8	org/hsqldb/jdbc/JDBCClobFile:m_file	Ljava/io/File;
    //   71: lload_1
    //   72: lload_3
    //   73: invokespecial 91	org/hsqldb/jdbc/JDBCClobFile$4:<init>	(Lorg/hsqldb/jdbc/JDBCClobFile;Ljava/io/File;JJ)V
    //   76: astore 5
    //   78: goto +11 -> 89
    //   81: astore 6
    //   83: aload 6
    //   85: invokestatic 17	org/hsqldb/jdbc/Util:sqlException	(Ljava/lang/Throwable;)Ljava/sql/SQLException;
    //   88: athrow
    //   89: aload_0
    //   90: getfield 6	org/hsqldb/jdbc/JDBCClobFile:m_streams	Ljava/util/List;
    //   93: aload 5
    //   95: invokeinterface 28 2 0
    //   100: pop
    //   101: aload 5
    //   103: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   62	78	81	java/lang/Exception
  }

  public File getFile()
  {
    return this.m_file;
  }

  public String getEncoding()
  {
    return this.m_encoding;
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
      try
      {
        free();
      }
      catch (Throwable localThrowable2)
      {
      }
    }
  }

  public JDBCClobFile()
    throws SQLException
  {
    this((String)null);
  }

  public JDBCClobFile(String paramString)
    throws SQLException
  {
    try
    {
      setEncoding(paramString);
      this.m_file = File.createTempFile("hsql_jdbc_clob_file_", ".tmp");
      this.m_deleteOnFree = true;
    }
    catch (Exception localException)
    {
      throw Util.sqlException(localException);
    }
  }

  public JDBCClobFile(File paramFile)
    throws SQLException
  {
    this(paramFile, null);
  }

  public JDBCClobFile(File paramFile, String paramString)
    throws SQLException
  {
    if (paramFile == null)
      throw Util.nullArgument("file");
    try
    {
      setEncoding(paramString);
      this.m_file = paramFile.getCanonicalFile();
      checkIsFile(false);
      this.m_deleteOnFree = false;
    }
    catch (Exception localException)
    {
      throw Util.sqlException(localException);
    }
  }

  protected final void setEncoding(String paramString)
    throws UnsupportedEncodingException
  {
    Charset localCharset = charsetForName(paramString);
    CharsetEncoder localCharsetEncoder = localCharset.newEncoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE);
    float f1 = localCharsetEncoder.maxBytesPerChar();
    float f2 = localCharsetEncoder.averageBytesPerChar();
    boolean bool = (f1 == Math.round(f1)) && (f1 == f2);
    this.m_fixedWidthCharset = bool;
    this.m_maxCharWidth = Math.round(f1);
    this.m_charset = localCharset;
    this.m_encoder = localCharsetEncoder;
    this.m_encoding = this.m_charset.name();
  }

  protected static Charset charsetForName(String paramString)
    throws UnsupportedEncodingException
  {
    String str = paramString;
    if (str == null)
      str = Charset.defaultCharset().name();
    try
    {
      if (Charset.isSupported(str))
        return Charset.forName(str);
    }
    catch (IllegalCharsetNameException localIllegalCharsetNameException)
    {
    }
    throw new UnsupportedEncodingException(str);
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

  protected void checkClosed()
    throws SQLException
  {
    if (this.m_closed)
      throw Util.sqlException(1251);
  }

  protected void createFile()
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

  protected class ReaderAdapter extends Reader
  {
    private static final int CHARBUFFER_CAPACTIY = 128;
    private final Reader m_reader;
    private long m_remaining = 9223372036854775807L;
    private long m_filePointer;
    private ByteBuffer m_byteBuffer;
    private CharBuffer m_charBuffer;

    public ReaderAdapter(File paramLong1, long arg3, long arg5)
      throws FileNotFoundException, IOException
    {
      if (paramLong1 == null)
        throw new NullPointerException("file");
      if (??? < 0L)
        throw new IllegalArgumentException("pos: " + ???);
      Object localObject;
      if (localObject < 0L)
        throw new IllegalArgumentException("length: " + localObject);
      if (!JDBCClobFile.this.m_fixedWidthCharset)
      {
        int i = 128 * JDBCClobFile.this.m_maxCharWidth;
        this.m_charBuffer = CharBuffer.allocate(128);
        this.m_byteBuffer = ByteBuffer.allocate(i);
      }
      FileInputStream localFileInputStream = new FileInputStream(paramLong1);
      BufferedInputStream localBufferedInputStream = new BufferedInputStream(localFileInputStream);
      InputStreamReader localInputStreamReader = new InputStreamReader(localBufferedInputStream, JDBCClobFile.this.m_charset);
      this.m_reader = localInputStreamReader;
      for (long l1 = 0L; l1 < ???; l1 += 1L)
      {
        int j = read();
        if (j == -1)
          break;
      }
      this.m_remaining = localObject;
    }

    public int read(char[] paramArrayOfChar, int paramInt1, int paramInt2)
      throws IOException
    {
      long l = this.m_remaining;
      if (l <= 0L)
        return -1;
      if (l < paramInt2)
        paramInt2 = (int)l;
      int i = this.m_reader.read(paramArrayOfChar, paramInt1, paramInt2);
      if (i == -1)
        return -1;
      if (i > l)
      {
        i = (int)l;
        this.m_remaining = 0L;
      }
      else
      {
        this.m_remaining -= i;
      }
      int j;
      if (JDBCClobFile.this.m_fixedWidthCharset)
      {
        j = JDBCClobFile.this.m_maxCharWidth * i;
      }
      else
      {
        int k = i > this.m_charBuffer.capacity() ? 1 : 0;
        CharBuffer localCharBuffer = k != 0 ? CharBuffer.allocate(i) : this.m_charBuffer;
        ByteBuffer localByteBuffer = k != 0 ? ByteBuffer.allocate(i * JDBCClobFile.this.m_maxCharWidth) : this.m_byteBuffer;
        localCharBuffer.clear();
        localByteBuffer.clear();
        localCharBuffer.put(paramArrayOfChar, paramInt1, i);
        localCharBuffer.flip();
        JDBCClobFile.this.m_encoder.encode(localCharBuffer, localByteBuffer, true);
        localByteBuffer.flip();
        j = localByteBuffer.limit();
        if (k != 0)
        {
          this.m_byteBuffer = localByteBuffer;
          this.m_charBuffer = localCharBuffer;
        }
      }
      this.m_filePointer += j;
      return i;
    }

    public void close()
      throws IOException
    {
      this.m_reader.close();
    }

    public long getFilePointer()
    {
      return this.m_filePointer;
    }
  }

  protected class WriterAdapter extends Writer
  {
    private final RandomAccessFile m_randomAccessFile;

    public WriterAdapter(File paramLong, long arg3)
      throws FileNotFoundException, IOException
    {
      if (paramLong == null)
        throw new NullPointerException("file");
      Object localObject1;
      if (localObject1 < 0L)
        throw new IllegalArgumentException("pos: " + localObject1);
      JDBCClobFile.ReaderAdapter localReaderAdapter = null;
      long l;
      try
      {
        localReaderAdapter = new JDBCClobFile.ReaderAdapter(JDBCClobFile.this, paramLong, localObject1, 9223372036854775807L);
        l = localReaderAdapter.getFilePointer();
      }
      finally
      {
        if (localReaderAdapter != null)
          try
          {
            localReaderAdapter.close();
          }
          catch (Exception localException2)
          {
          }
      }
      this.m_randomAccessFile = new RandomAccessFile(paramLong, "rw");
      this.m_randomAccessFile.seek(l);
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

    public void write(char[] paramArrayOfChar, int paramInt1, int paramInt2)
      throws IOException
    {
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      OutputStreamWriter localOutputStreamWriter = JDBCClobFile.this.m_encoding == null ? new OutputStreamWriter(localByteArrayOutputStream) : new OutputStreamWriter(localByteArrayOutputStream, JDBCClobFile.this.m_charset);
      localOutputStreamWriter.write(paramArrayOfChar, paramInt1, paramInt2);
      localOutputStreamWriter.close();
      this.m_randomAccessFile.write(localByteArrayOutputStream.toByteArray());
    }
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.jdbc.JDBCClobFile
 * JD-Core Version:    0.6.2
 */