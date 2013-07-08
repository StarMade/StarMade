package org.hsqldb.lib.tar;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hsqldb.lib.InputStreamInterface;
import org.hsqldb.lib.InputStreamWrapper;
import org.hsqldb.lib.StringUtil;

public class TarGenerator
{
  protected TarFileOutputStream archive;
  protected List<TarEntrySupplicant> entryQueue = new ArrayList();
  protected long paxThreshold = 8589934592L;
  
  public static void main(String[] paramArrayOfString)
    throws IOException, TarMalformatException
  {
    if (paramArrayOfString.length < 1)
    {
      System.out.println(RB.TarGenerator_syntax.getString(new String[] { DbBackup.class.getName() }));
      System.exit(0);
    }
    TarGenerator localTarGenerator = new TarGenerator(new File(paramArrayOfString[0]), true, null);
    if (paramArrayOfString.length == 1) {
      localTarGenerator.queueEntry("stdin", System.in, 10240);
    } else {
      for (int i = 1; i < paramArrayOfString.length; i++) {
        localTarGenerator.queueEntry(new File(paramArrayOfString[i]));
      }
    }
    localTarGenerator.write();
  }
  
  public void setPaxThreshold(long paramLong)
  {
    this.paxThreshold = paramLong;
  }
  
  public long getPaxThreshold()
  {
    return this.paxThreshold;
  }
  
  public TarGenerator(File paramFile, boolean paramBoolean, Integer paramInteger)
    throws IOException
  {
    File localFile1 = paramFile.getAbsoluteFile();
    int i = 0;
    if ((localFile1.getName().endsWith(".tgz")) || (localFile1.getName().endsWith(".tar.gz"))) {
      i = 1;
    } else if (!localFile1.getName().endsWith(".tar")) {
      throw new IllegalArgumentException(RB.unsupported_ext.getString(new String[] { getClass().getName(), localFile1.getPath() }));
    }
    if (localFile1.exists())
    {
      if (!paramBoolean) {
        throw new IOException(RB.dest_exists.getString(new String[] { localFile1.getPath() }));
      }
    }
    else
    {
      File localFile2 = localFile1.getParentFile();
      if (localFile2.exists())
      {
        if (!localFile2.isDirectory()) {
          throw new IOException(RB.parent_not_dir.getString(new String[] { localFile2.getPath() }));
        }
        if (!localFile2.canWrite()) {
          throw new IOException(RB.cant_write_parent.getString(new String[] { localFile2.getPath() }));
        }
      }
      else if (!localFile2.mkdirs())
      {
        throw new IOException(RB.parent_create_fail.getString(new String[] { localFile2.getPath() }));
      }
    }
    this.archive = (paramInteger == null ? new TarFileOutputStream(localFile1, i) : new TarFileOutputStream(localFile1, i, paramInteger.intValue()));
    if ((paramInteger != null) && (TarFileOutputStream.debug)) {
      System.out.println(RB.bpr_write.getString(paramInteger.intValue()));
    }
  }
  
  public void queueEntry(File paramFile)
    throws FileNotFoundException, TarMalformatException
  {
    queueEntry(null, paramFile);
  }
  
  public void queueEntry(String paramString, File paramFile)
    throws FileNotFoundException, TarMalformatException
  {
    this.entryQueue.add(new TarEntrySupplicant(paramString, paramFile, this.archive, this.paxThreshold));
  }
  
  public void queueEntry(String paramString, InputStreamInterface paramInputStreamInterface)
    throws FileNotFoundException, TarMalformatException
  {
    this.entryQueue.add(new TarEntrySupplicant(paramString, paramInputStreamInterface, this.archive, this.paxThreshold));
  }
  
  public void queueEntry(String paramString, InputStream paramInputStream, int paramInt)
    throws IOException, TarMalformatException
  {
    this.entryQueue.add(new TarEntrySupplicant(paramString, paramInputStream, paramInt, '0', this.archive));
  }
  
  public void write()
    throws IOException, TarMalformatException
  {
    if (TarFileOutputStream.debug) {
      System.out.println(RB.write_queue_report.getString(this.entryQueue.size()));
    }
    try
    {
      for (int i = 0; i < this.entryQueue.size(); i++)
      {
        System.err.print(Integer.toString(i + 1) + " / " + this.entryQueue.size() + ' ');
        TarEntrySupplicant localTarEntrySupplicant1 = (TarEntrySupplicant)this.entryQueue.get(i);
        System.err.print(localTarEntrySupplicant1.getPath() + "... ");
        localTarEntrySupplicant1.write();
        this.archive.assertAtBlockBoundary();
        System.err.println();
      }
      this.archive.finish();
    }
    catch (IOException localIOException1)
    {
      System.err.println();
      try
      {
        Iterator localIterator = this.entryQueue.iterator();
        while (localIterator.hasNext())
        {
          TarEntrySupplicant localTarEntrySupplicant2 = (TarEntrySupplicant)localIterator.next();
          localTarEntrySupplicant2.close();
        }
        this.archive.close();
      }
      catch (IOException localIOException2) {}
      throw localIOException1;
    }
  }
  
  protected static class TarEntrySupplicant
  {
    protected static byte[] HEADER_TEMPLATE = (byte[])TarFileOutputStream.ZERO_BLOCK.clone();
    static Character swapOutDelim = null;
    protected static final byte[] ustarBytes = { 117, 115, 116, 97, 114 };
    protected byte[] rawHeader = (byte[])HEADER_TEMPLATE.clone();
    protected String fileMode = "600";
    protected InputStreamInterface inputStream;
    protected String path;
    protected long modTime;
    protected TarFileOutputStream tarStream;
    protected long dataSize;
    protected boolean paxSized = false;
    protected final long paxThreshold;
    public static final String DEFAULT_FILE_MODES = "600";
    
    protected static void writeField(TarHeaderField paramTarHeaderField, String paramString, byte[] paramArrayOfByte)
      throws TarMalformatException
    {
      int i = paramTarHeaderField.getStart();
      int j = paramTarHeaderField.getStop();
      byte[] arrayOfByte;
      try
      {
        arrayOfByte = paramString.getBytes("ISO-8859-1");
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        throw new RuntimeException(localUnsupportedEncodingException);
      }
      if (arrayOfByte.length > j - i) {
        throw new TarMalformatException(RB.tar_field_toobig.getString(new String[] { paramTarHeaderField.toString(), paramString }));
      }
      for (int k = 0; k < arrayOfByte.length; k++) {
        paramArrayOfByte[(i + k)] = arrayOfByte[k];
      }
    }
    
    protected static void clearField(TarHeaderField paramTarHeaderField, byte[] paramArrayOfByte)
    {
      int i = paramTarHeaderField.getStart();
      int j = paramTarHeaderField.getStop();
      for (int k = i; k < j; k++) {
        paramArrayOfByte[k] = 0;
      }
    }
    
    protected static void writeField(TarHeaderField paramTarHeaderField, long paramLong, byte[] paramArrayOfByte)
      throws TarMalformatException
    {
      writeField(paramTarHeaderField, prePaddedOctalString(paramLong, paramTarHeaderField.getStop() - paramTarHeaderField.getStart()), paramArrayOfByte);
    }
    
    public static String prePaddedOctalString(long paramLong, int paramInt)
    {
      return StringUtil.toPaddedString(Long.toOctalString(paramLong), paramInt, '0', false);
    }
    
    public String getPath()
    {
      return this.path;
    }
    
    public long getDataSize()
    {
      return this.dataSize;
    }
    
    protected TarEntrySupplicant(String paramString, char paramChar, TarFileOutputStream paramTarFileOutputStream, long paramLong)
      throws TarMalformatException
    {
      this.paxThreshold = paramLong;
      if (paramString == null) {
        throw new IllegalArgumentException(RB.missing_supp_path.getString());
      }
      this.path = (swapOutDelim == null ? paramString : paramString.replace(swapOutDelim.charValue(), '/'));
      this.tarStream = paramTarFileOutputStream;
      writeField(TarHeaderField.typeflag, paramChar);
      if ((paramChar == 0) || (paramChar == ' '))
      {
        writeField(TarHeaderField.uname, System.getProperty("user.name"), HEADER_TEMPLATE);
        writeField(TarHeaderField.gname, "root", HEADER_TEMPLATE);
      }
    }
    
    public TarEntrySupplicant makeXentry()
      throws IOException, TarMalformatException
    {
      PIFGenerator localPIFGenerator = new PIFGenerator(new File(this.path));
      localPIFGenerator.addRecord("size", this.dataSize);
      return new TarEntrySupplicant(localPIFGenerator.getName(), new ByteArrayInputStream(localPIFGenerator.toByteArray()), localPIFGenerator.size(), 'x', this.tarStream);
    }
    
    public TarEntrySupplicant(String paramString, File paramFile, TarFileOutputStream paramTarFileOutputStream, long paramLong)
      throws FileNotFoundException, TarMalformatException
    {
      this(paramString == null ? paramFile.getPath() : paramString, '0', paramTarFileOutputStream, paramLong);
      if (!paramFile.isFile()) {
        throw new IllegalArgumentException(RB.nonfile_entry.getString());
      }
      if (!paramFile.canRead()) {
        throw new IllegalArgumentException(RB.read_denied.getString(new String[] { paramFile.getAbsolutePath() }));
      }
      this.modTime = (paramFile.lastModified() / 1000L);
      this.fileMode = getLameMode(paramFile);
      this.dataSize = paramFile.length();
      this.inputStream = new InputStreamWrapper(new FileInputStream(paramFile));
    }
    
    public TarEntrySupplicant(String paramString, InputStreamInterface paramInputStreamInterface, TarFileOutputStream paramTarFileOutputStream, long paramLong)
      throws FileNotFoundException, TarMalformatException
    {
      this(paramString, '0', paramTarFileOutputStream, paramLong);
      this.modTime = (System.currentTimeMillis() / 1000L);
      this.fileMode = "600";
      this.inputStream = paramInputStreamInterface;
    }
    
    public TarEntrySupplicant(String paramString, InputStream paramInputStream, int paramInt, char paramChar, TarFileOutputStream paramTarFileOutputStream)
      throws IOException, TarMalformatException
    {
      this(paramString, paramChar, paramTarFileOutputStream, 8589934592L);
      if (paramInt < 1) {
        throw new IllegalArgumentException(RB.read_lt_1.getString());
      }
      PipedOutputStream localPipedOutputStream = new PipedOutputStream();
      try
      {
        this.inputStream = new InputStreamWrapper(new PipedInputStream(localPipedOutputStream));
        int i;
        while ((i = paramInputStream.read(paramTarFileOutputStream.writeBuffer, 0, paramTarFileOutputStream.writeBuffer.length)) > 0) {
          localPipedOutputStream.write(paramTarFileOutputStream.writeBuffer, 0, i);
        }
        localPipedOutputStream.flush();
        this.dataSize = this.inputStream.available();
        if (TarFileOutputStream.debug) {
          System.out.println(RB.stream_buffer_report.getString(new String[] { Long.toString(this.dataSize) }));
        }
      }
      catch (IOException localIOException)
      {
        close();
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
      this.modTime = (new Date().getTime() / 1000L);
    }
    
    public void close()
      throws IOException
    {
      if (this.inputStream == null) {
        return;
      }
      try
      {
        this.inputStream.close();
      }
      finally
      {
        this.inputStream = null;
      }
    }
    
    protected long headerChecksum()
    {
      long l = 0L;
      for (int i = 0; i < this.rawHeader.length; i++)
      {
        int j = (i >= TarHeaderField.checksum.getStart()) && (i < TarHeaderField.checksum.getStop()) ? 1 : 0;
        l += (j != 0 ? 32L : 0xFF & this.rawHeader[i]);
      }
      return l;
    }
    
    protected void clearField(TarHeaderField paramTarHeaderField)
    {
      clearField(paramTarHeaderField, this.rawHeader);
    }
    
    protected void writeField(TarHeaderField paramTarHeaderField, String paramString)
      throws TarMalformatException
    {
      writeField(paramTarHeaderField, paramString, this.rawHeader);
    }
    
    protected void writeField(TarHeaderField paramTarHeaderField, long paramLong)
      throws TarMalformatException
    {
      writeField(paramTarHeaderField, paramLong, this.rawHeader);
    }
    
    protected void writeField(TarHeaderField paramTarHeaderField, char paramChar)
      throws TarMalformatException
    {
      writeField(paramTarHeaderField, Character.toString(paramChar), this.rawHeader);
    }
    
    public void write()
      throws IOException, TarMalformatException
    {
      try
      {
        long l1 = this.inputStream.getSizeLimit();
        if (l1 == 0L) {
          return;
        }
        if (l1 > 0L) {
          this.dataSize = l1;
        }
        if (this.dataSize >= this.paxThreshold)
        {
          this.paxSized = true;
          makeXentry().write();
          System.err.print("x... ");
        }
        writeField(TarHeaderField.name, this.path);
        writeField(TarHeaderField.mode, this.fileMode);
        if (!this.paxSized) {
          writeField(TarHeaderField.size, this.dataSize);
        }
        writeField(TarHeaderField.mtime, this.modTime);
        writeField(TarHeaderField.checksum, prePaddedOctalString(headerChecksum(), 6) + "");
        this.tarStream.writeBlock(this.rawHeader);
        long l2 = this.tarStream.getBytesWritten();
        int i;
        while ((i = this.inputStream.read(this.tarStream.writeBuffer)) > 0) {
          this.tarStream.write(i);
        }
        if (l2 + this.dataSize != this.tarStream.getBytesWritten()) {
          throw new IOException(RB.data_changed.getString(new String[] { Long.toString(this.dataSize), Long.toString(this.tarStream.getBytesWritten() - l2) }));
        }
        this.tarStream.padCurrentBlock();
      }
      finally
      {
        close();
      }
    }
    
    protected static String getLameMode(File paramFile)
    {
      int i = 0;
      if (paramFile.canExecute()) {
        i = 1;
      }
      if (paramFile.canWrite()) {
        i += 2;
      }
      if (paramFile.canRead()) {
        i += 4;
      }
      return "0" + i + "00";
    }
    
    static
    {
      char c = System.getProperty("file.separator").charAt(0);
      if (c != '/') {
        swapOutDelim = new Character(c);
      }
      try
      {
        writeField(TarHeaderField.uid, 0L, HEADER_TEMPLATE);
        writeField(TarHeaderField.gid, 0L, HEADER_TEMPLATE);
      }
      catch (TarMalformatException localTarMalformatException)
      {
        throw new RuntimeException(localTarMalformatException);
      }
      int i = TarHeaderField.magic.getStart();
      for (int j = 0; j < ustarBytes.length; j++) {
        HEADER_TEMPLATE[(i + j)] = ustarBytes[j];
      }
      HEADER_TEMPLATE[263] = 48;
      HEADER_TEMPLATE[264] = 48;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.lib.tar.TarGenerator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */