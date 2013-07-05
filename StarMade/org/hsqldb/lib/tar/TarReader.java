package org.hsqldb.lib.tar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.hsqldb.lib.StringUtil;

public class TarReader
{
  public static final int LIST_MODE = 0;
  public static final int EXTRACT_MODE = 1;
  public static final int OVERWRITE_MODE = 2;
  protected TarFileInputStream archive;
  protected Pattern[] patterns = null;
  protected int mode;
  protected File extractBaseDir;

  public static void main(String[] paramArrayOfString)
    throws IOException, TarMalformatException
  {
    if (paramArrayOfString.length < 1)
    {
      System.out.println(RB.TarReader_syntax.getString(new String[] { TarReader.class.getName() }));
      System.out.println(RB.listing_format.getString());
      System.exit(0);
    }
    File localFile = (paramArrayOfString.length > 1) && (paramArrayOfString[1].startsWith("--directory=")) ? new File(paramArrayOfString[1].substring("--directory=".length())) : null;
    int i = localFile == null ? 2 : 3;
    if ((paramArrayOfString.length < i) || ((!paramArrayOfString[0].equals("t")) && (!paramArrayOfString[0].equals("x"))))
      throw new IllegalArgumentException(RB.tarreader_syntaxerr.getString(new String[] { TarReader.class.getName() }));
    String[] arrayOfString = null;
    if (paramArrayOfString.length > i)
    {
      arrayOfString = new String[paramArrayOfString.length - i];
      for (j = i; j < paramArrayOfString.length; j++)
        arrayOfString[(j - i)] = paramArrayOfString[j];
    }
    if ((paramArrayOfString[0].equals("t")) && (localFile != null))
      throw new IllegalArgumentException(RB.dir_x_conflict.getString());
    int j = localFile == null ? 1 : 2;
    int k = paramArrayOfString[0].equals("t") ? 0 : 1;
    new TarReader(new File(paramArrayOfString[j]), k, arrayOfString, null, localFile).read();
  }

  public TarReader(File paramFile1, int paramInt, String[] paramArrayOfString, Integer paramInteger, File paramFile2)
    throws IOException
  {
    this.mode = paramInt;
    File localFile = paramFile1.getAbsoluteFile();
    this.extractBaseDir = (paramFile2 == null ? null : paramFile2.getAbsoluteFile());
    int i = 0;
    if ((localFile.getName().endsWith(".tgz")) || (localFile.getName().endsWith(".gz")))
      i = 1;
    if (paramArrayOfString != null)
    {
      this.patterns = new Pattern[paramArrayOfString.length];
      for (int j = 0; j < paramArrayOfString.length; j++)
        this.patterns[j] = Pattern.compile(paramArrayOfString[j]);
    }
    this.archive = (paramInteger == null ? new TarFileInputStream(localFile, i) : new TarFileInputStream(localFile, i, paramInteger.intValue()));
  }

  public void read()
    throws IOException, TarMalformatException
  {
    int i = 0;
    Long localLong = null;
    String str = null;
    try
    {
      while (this.archive.readNextHeaderBlock())
      {
        TarEntryHeader localTarEntryHeader = new TarEntryHeader(this.archive.readBuffer);
        int k = localTarEntryHeader.getEntryType();
        if (k == 120)
        {
          localLong = getPifData(localTarEntryHeader).getSize();
          str = localTarEntryHeader.toString();
        }
        else
        {
          if (localLong != null)
          {
            localTarEntryHeader.setDataSize(localLong.longValue());
            localLong = null;
          }
          if (this.patterns != null)
          {
            int j = 0;
            for (int m = 0; m < this.patterns.length; m++)
              if (this.patterns[m].matcher(localTarEntryHeader.getPath()).matches())
              {
                j = 1;
                break;
              }
            if (j == 0)
            {
              str = null;
              skipFileData(localTarEntryHeader);
            }
          }
          else
          {
            if ((k != 0) && (k != 48) && (k != 120))
              i = 1;
            switch (this.mode)
            {
            case 0:
              if (str != null)
                System.out.println(str);
              System.out.println(localTarEntryHeader.toString());
              skipFileData(localTarEntryHeader);
              break;
            case 1:
            case 2:
              if (str != null)
                System.out.println(str);
              System.out.println(localTarEntryHeader.toString());
              if ((k == 0) || (k == 48) || (k == 120))
                extractFile(localTarEntryHeader);
              else
                skipFileData(localTarEntryHeader);
              break;
            default:
              throw new IllegalArgumentException(RB.unsupported_mode.getString(this.mode));
            }
            str = null;
          }
        }
      }
      if (i != 0)
        System.out.println(RB.unsupported_entry_present.getString());
    }
    catch (IOException localIOException)
    {
      this.archive.close();
      throw localIOException;
    }
  }

  protected PIFData getPifData(TarEntryHeader paramTarEntryHeader)
    throws IOException, TarMalformatException
  {
    long l = paramTarEntryHeader.getDataSize();
    if (l < 1L)
      throw new TarMalformatException(RB.pif_unknown_datasize.getString());
    if (l > 2147483647L)
      throw new TarMalformatException(RB.pif_data_toobig.getString(Long.toString(l), 2147483647));
    int j = (int)(l / 512L);
    int k = (int)(l % 512L);
    PipedInputStream localPipedInputStream = null;
    PipedOutputStream localPipedOutputStream = new PipedOutputStream();
    try
    {
      localPipedInputStream = new PipedInputStream(localPipedOutputStream);
      while (j > 0)
      {
        int i = j > this.archive.getReadBufferBlocks() ? this.archive.getReadBufferBlocks() : j;
        this.archive.readBlocks(i);
        j -= i;
        localPipedOutputStream.write(this.archive.readBuffer, 0, i * 512);
      }
      if (k != 0)
      {
        this.archive.readBlock();
        localPipedOutputStream.write(this.archive.readBuffer, 0, k);
      }
      localPipedOutputStream.flush();
    }
    catch (IOException localIOException)
    {
      if (localPipedInputStream != null)
        localPipedInputStream.close();
      throw localIOException;
    }
    finally
    {
      try
      {
        localPipedOutputStream.close();
      }
      finally
      {
        localPipedOutputStream = null;
      }
    }
    return new PIFData(localPipedInputStream);
  }

  protected void extractFile(TarEntryHeader paramTarEntryHeader)
    throws IOException, TarMalformatException
  {
    if (paramTarEntryHeader.getDataSize() < 1L)
      throw new TarMalformatException(RB.data_size_unknown.getString());
    int j = (int)(paramTarEntryHeader.getDataSize() / 512L);
    int k = (int)(paramTarEntryHeader.getDataSize() % 512L);
    File localFile1 = paramTarEntryHeader.generateFile();
    if (!localFile1.isAbsolute())
      localFile1 = this.extractBaseDir == null ? localFile1.getAbsoluteFile() : new File(this.extractBaseDir, localFile1.getPath());
    File localFile2 = localFile1.getParentFile();
    if (localFile1.exists())
    {
      if (this.mode != 2)
        throw new IOException(RB.extraction_exists.getString(new String[] { localFile1.getAbsolutePath() }));
      if (!localFile1.isFile())
        throw new IOException(RB.extraction_exists_notfile.getString(new String[] { localFile1.getAbsolutePath() }));
    }
    if (localFile2.exists())
    {
      if (!localFile2.isDirectory())
        throw new IOException(RB.extraction_parent_not_dir.getString(new String[] { localFile2.getAbsolutePath() }));
      if (!localFile2.canWrite())
        throw new IOException(RB.extraction_parent_not_writable.getString(new String[] { localFile2.getAbsolutePath() }));
    }
    else if (!localFile2.mkdirs())
    {
      throw new IOException(RB.extraction_parent_mkfail.getString(new String[] { localFile2.getAbsolutePath() }));
    }
    int m = paramTarEntryHeader.getFileMode();
    FileOutputStream localFileOutputStream = new FileOutputStream(localFile1);
    try
    {
      localFile1.setExecutable(false, false);
      localFile1.setReadable(false, false);
      localFile1.setWritable(false, false);
      localFile1.setExecutable((m & 0x40) != 0, true);
      localFile1.setReadable((m & 0x100) != 0, true);
      localFile1.setWritable((m & 0x80) != 0, true);
      while (j > 0)
      {
        int i = j > this.archive.getReadBufferBlocks() ? this.archive.getReadBufferBlocks() : j;
        this.archive.readBlocks(i);
        j -= i;
        localFileOutputStream.write(this.archive.readBuffer, 0, i * 512);
      }
      if (k != 0)
      {
        this.archive.readBlock();
        localFileOutputStream.write(this.archive.readBuffer, 0, k);
      }
      localFileOutputStream.flush();
    }
    finally
    {
      try
      {
        localFileOutputStream.close();
      }
      finally
      {
        localFileOutputStream = null;
      }
    }
    localFile1.setLastModified(paramTarEntryHeader.getModTime() * 1000L);
    if (localFile1.length() != paramTarEntryHeader.getDataSize())
      throw new IOException(RB.write_count_mismatch.getString(new String[] { Long.toString(paramTarEntryHeader.getDataSize()), localFile1.getAbsolutePath(), Long.toString(localFile1.length()) }));
  }

  protected void skipFileData(TarEntryHeader paramTarEntryHeader)
    throws IOException, TarMalformatException
  {
    if (paramTarEntryHeader.getDataSize() == 0L)
      return;
    if (paramTarEntryHeader.getDataSize() < 0L)
      throw new TarMalformatException(RB.data_size_unknown.getString());
    int j = paramTarEntryHeader.getDataSize() % 512L == 0L ? 0 : 1;
    int k = (int)(paramTarEntryHeader.getDataSize() / 512L) + j;
    while (k > 0)
    {
      int i = k > this.archive.getReadBufferBlocks() ? this.archive.getReadBufferBlocks() : k;
      this.archive.readBlocks(i);
      k -= i;
    }
  }

  protected static class TarEntryHeader
  {
    protected SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    protected byte[] rawHeader;
    protected String path;
    protected int fileMode;
    protected long dataSize = -1L;
    protected long modTime;
    protected char entryType;
    protected String ownerName;
    protected boolean ustar;

    public TarEntryHeader(byte[] paramArrayOfByte)
      throws TarMalformatException
    {
      this.rawHeader = paramArrayOfByte;
      Long localLong1 = readInteger(TarHeaderField.checksum);
      try
      {
        if (localLong1 == null)
          throw new MissingField(TarHeaderField.checksum);
        long l = headerChecksum();
        if (localLong1.longValue() != l)
          throw new TarMalformatException(RB.checksum_mismatch.getString(new String[] { localLong1.toString(), Long.toString(l) }));
        this.path = readString(TarHeaderField.name);
        if (this.path == null)
          throw new MissingField(TarHeaderField.name);
        Long localLong2 = readInteger(TarHeaderField.mode);
        if (localLong2 == null)
          throw new MissingField(TarHeaderField.mode);
        this.fileMode = ((int)localLong2.longValue());
        localLong2 = readInteger(TarHeaderField.size);
        if (localLong2 != null)
          this.dataSize = localLong2.longValue();
        localLong2 = readInteger(TarHeaderField.mtime);
        if (localLong2 == null)
          throw new MissingField(TarHeaderField.mtime);
        this.modTime = localLong2.longValue();
      }
      catch (MissingField localMissingField)
      {
        throw new TarMalformatException(localMissingField.getMessage());
      }
      this.entryType = readChar(TarHeaderField.typeflag);
      this.ownerName = readString(TarHeaderField.uname);
      String str = readString(TarHeaderField.prefix);
      if (str != null)
        this.path = (str + '/' + this.path);
      this.ustar = isUstar();
    }

    public File generateFile()
    {
      if ((this.entryType != 0) && (this.entryType != '0'))
        throw new IllegalStateException(RB.create_only_normal.getString());
      return new File(this.path);
    }

    public char getEntryType()
    {
      return this.entryType;
    }

    public String getPath()
    {
      return this.path;
    }

    public void setDataSize(long paramLong)
    {
      this.dataSize = paramLong;
    }

    public long getDataSize()
    {
      return this.dataSize;
    }

    public long getModTime()
    {
      return this.modTime;
    }

    public int getFileMode()
    {
      return this.fileMode;
    }

    public String toString()
    {
      StringBuffer localStringBuffer = new StringBuffer(this.sdf.format(new Long(this.modTime * 1000L)) + ' ');
      localStringBuffer.append(this.entryType == 0 ? ' ' : this.entryType);
      localStringBuffer.append(this.ustar ? '*' : ' ');
      localStringBuffer.append(" " + StringUtil.toPaddedString(Integer.toOctalString(this.fileMode), 4, ' ', false) + ' ' + StringUtil.toPaddedString(Long.toString(this.dataSize), 11, ' ', false) + "  ");
      localStringBuffer.append(StringUtil.toPaddedString(this.ownerName == null ? "-" : this.ownerName, 8, ' ', true));
      localStringBuffer.append("  " + this.path);
      return localStringBuffer.toString();
    }

    public boolean isUstar()
      throws TarMalformatException
    {
      String str = readString(TarHeaderField.magic);
      return (str != null) && (str.startsWith("ustar"));
    }

    public static int indexOf(byte[] paramArrayOfByte, byte paramByte, int paramInt1, int paramInt2)
    {
      for (int i = paramInt1; i < paramInt2; i++)
        if (paramArrayOfByte[i] == paramByte)
          return i - paramInt1;
      return -1;
    }

    protected char readChar(TarHeaderField paramTarHeaderField)
      throws TarMalformatException
    {
      String str = readString(paramTarHeaderField);
      return str == null ? '\000' : str.charAt(0);
    }

    protected String readString(TarHeaderField paramTarHeaderField)
      throws TarMalformatException
    {
      // Byte code:
      //   0: aload_1
      //   1: invokevirtual 70	org/hsqldb/lib/tar/TarHeaderField:getStart	()I
      //   4: istore_2
      //   5: aload_1
      //   6: invokevirtual 71	org/hsqldb/lib/tar/TarHeaderField:getStop	()I
      //   9: istore_3
      //   10: aload_0
      //   11: getfield 9	org/hsqldb/lib/tar/TarReader$TarEntryHeader:rawHeader	[B
      //   14: iconst_0
      //   15: iload_2
      //   16: iload_3
      //   17: invokestatic 72	org/hsqldb/lib/tar/TarReader$TarEntryHeader:indexOf	([BBII)I
      //   20: istore 4
      //   22: iload 4
      //   24: lookupswitch	default:+35->59, -1:+30->54, 0:+28->52
      //   53: areturn
      //   54: iload_3
      //   55: iload_2
      //   56: isub
      //   57: istore 4
      //   59: new 18	java/lang/String
      //   62: dup
      //   63: aload_0
      //   64: getfield 9	org/hsqldb/lib/tar/TarReader$TarEntryHeader:rawHeader	[B
      //   67: iload_2
      //   68: iload 4
      //   70: invokespecial 73	java/lang/String:<init>	([BII)V
      //   73: areturn
      //   74: astore 5
      //   76: new 16	org/hsqldb/lib/tar/TarMalformatException
      //   79: dup
      //   80: getstatic 75	org/hsqldb/lib/tar/RB:bad_header_value	Lorg/hsqldb/lib/tar/RB;
      //   83: iconst_1
      //   84: anewarray 18	java/lang/String
      //   87: dup
      //   88: iconst_0
      //   89: aload_1
      //   90: invokevirtual 76	org/hsqldb/lib/tar/TarHeaderField:toString	()Ljava/lang/String;
      //   93: aastore
      //   94: invokevirtual 21	org/hsqldb/lib/tar/RB:getString	([Ljava/lang/String;)Ljava/lang/String;
      //   97: invokespecial 22	org/hsqldb/lib/tar/TarMalformatException:<init>	(Ljava/lang/String;)V
      //   100: athrow
      //
      // Exception table:
      //   from	to	target	type
      //   59	73	74	java/lang/Throwable
    }

    protected Long readInteger(TarHeaderField paramTarHeaderField)
      throws TarMalformatException
    {
      // Byte code:
      //   0: aload_0
      //   1: aload_1
      //   2: invokevirtual 24	org/hsqldb/lib/tar/TarReader$TarEntryHeader:readString	(Lorg/hsqldb/lib/tar/TarHeaderField;)Ljava/lang/String;
      //   5: astore_2
      //   6: aload_2
      //   7: ifnonnull +5 -> 12
      //   10: aconst_null
      //   11: areturn
      //   12: aload_2
      //   13: bipush 8
      //   15: invokestatic 77	java/lang/Long:valueOf	(Ljava/lang/String;I)Ljava/lang/Long;
      //   18: areturn
      //   19: astore_3
      //   20: new 16	org/hsqldb/lib/tar/TarMalformatException
      //   23: dup
      //   24: getstatic 79	org/hsqldb/lib/tar/RB:bad_numeric_header_value	Lorg/hsqldb/lib/tar/RB;
      //   27: iconst_2
      //   28: anewarray 18	java/lang/String
      //   31: dup
      //   32: iconst_0
      //   33: aload_1
      //   34: invokevirtual 76	org/hsqldb/lib/tar/TarHeaderField:toString	()Ljava/lang/String;
      //   37: aastore
      //   38: dup
      //   39: iconst_1
      //   40: aload_3
      //   41: invokevirtual 80	java/lang/NumberFormatException:toString	()Ljava/lang/String;
      //   44: aastore
      //   45: invokevirtual 21	org/hsqldb/lib/tar/RB:getString	([Ljava/lang/String;)Ljava/lang/String;
      //   48: invokespecial 22	org/hsqldb/lib/tar/TarMalformatException:<init>	(Ljava/lang/String;)V
      //   51: athrow
      //
      // Exception table:
      //   from	to	target	type
      //   12	18	19	java/lang/NumberFormatException
    }

    protected long headerChecksum()
    {
      long l = 0L;
      for (int i = 0; i < 512; i++)
      {
        int j = (i >= TarHeaderField.checksum.getStart()) && (i < TarHeaderField.checksum.getStop()) ? 1 : 0;
        l += (j != 0 ? 32L : 0xFF & this.rawHeader[i]);
      }
      return l;
    }

    protected static class MissingField extends Exception
    {
      private TarHeaderField field;

      public MissingField(TarHeaderField paramTarHeaderField)
      {
        this.field = paramTarHeaderField;
      }

      public String getMessage()
      {
        return RB.header_field_missing.getString(new String[] { this.field.toString() });
      }
    }
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.lib.tar.TarReader
 * JD-Core Version:    0.6.2
 */