package org.hsqldb.lib.tar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.zip.GZIPOutputStream;

public class TarFileOutputStream
{
  public static boolean debug = Boolean.getBoolean("DEBUG");
  protected int blocksPerRecord;
  protected long bytesWritten = 0L;
  private OutputStream writeStream;
  private File targetFile;
  private File writeFile;
  public byte[] writeBuffer;
  public static final byte[] ZERO_BLOCK = new byte[512];

  public TarFileOutputStream(File paramFile)
    throws IOException
  {
    this(paramFile, 0);
  }

  public TarFileOutputStream(File paramFile, int paramInt)
    throws IOException
  {
    this(paramFile, paramInt, 20);
  }

  public TarFileOutputStream(File paramFile, int paramInt1, int paramInt2)
    throws IOException
  {
    this.blocksPerRecord = paramInt2;
    this.targetFile = paramFile;
    this.writeFile = new File(paramFile.getParentFile(), paramFile.getName() + "-partial");
    if (this.writeFile.exists())
      throw new IOException(RB.move_work_file.getString(new String[] { this.writeFile.getAbsolutePath() }));
    if ((paramFile.exists()) && (!paramFile.canWrite()))
      throw new IOException(RB.cant_overwrite.getString(new String[] { paramFile.getAbsolutePath() }));
    File localFile = paramFile.getAbsoluteFile().getParentFile();
    if ((localFile.exists()) && (localFile.isDirectory()))
    {
      if (!localFile.canWrite())
        throw new IOException(RB.cant_write_dir.getString(new String[] { localFile.getAbsolutePath() }));
    }
    else
      throw new IOException(RB.no_parent_dir.getString(new String[] { localFile.getAbsolutePath() }));
    this.writeBuffer = new byte[paramInt2 * 512];
    switch (paramInt1)
    {
    case 0:
      this.writeStream = new FileOutputStream(this.writeFile);
      break;
    case 1:
      this.writeStream = new GZIPOutputStream(new FileOutputStream(this.writeFile), this.writeBuffer.length);
      break;
    default:
      throw new IllegalArgumentException(RB.compression_unknown.getString(paramInt1));
    }
    this.writeFile.setExecutable(false, true);
    this.writeFile.setExecutable(false, false);
    this.writeFile.setReadable(false, false);
    this.writeFile.setReadable(true, true);
    this.writeFile.setWritable(false, false);
    this.writeFile.setWritable(true, true);
  }

  public void write(byte[] paramArrayOfByte, int paramInt)
    throws IOException
  {
    this.writeStream.write(paramArrayOfByte, 0, paramInt);
    this.bytesWritten += paramInt;
  }

  public void write(int paramInt)
    throws IOException
  {
    write(this.writeBuffer, paramInt);
  }

  public void writeBlock(byte[] paramArrayOfByte)
    throws IOException
  {
    if (paramArrayOfByte.length != 512)
      throw new IllegalArgumentException(RB.bad_block_write_len.getString(paramArrayOfByte.length));
    write(paramArrayOfByte, paramArrayOfByte.length);
  }

  public void writePadBlocks(int paramInt)
    throws IOException
  {
    for (int i = 0; i < paramInt; i++)
      write(ZERO_BLOCK, ZERO_BLOCK.length);
  }

  public void writePadBlock()
    throws IOException
  {
    writePadBlocks(1);
  }

  public int bytesLeftInBlock()
  {
    int i = (int)(this.bytesWritten % 512L);
    if (i == 0)
      return 0;
    return 512 - i;
  }

  public void assertAtBlockBoundary()
  {
    if (bytesLeftInBlock() != 0)
      throw new IllegalArgumentException(RB.illegal_block_boundary.getString(new String[] { Long.toString(this.bytesWritten) }));
  }

  public void padCurrentBlock()
    throws IOException
  {
    int i = bytesLeftInBlock();
    if (i == 0)
      return;
    write(ZERO_BLOCK, i);
    assertAtBlockBoundary();
  }

  public void flush()
    throws IOException
  {
    this.writeStream.flush();
  }

  public void close()
    throws IOException
  {
    if (this.writeStream == null)
      return;
    try
    {
      this.writeStream.close();
      if (!this.writeFile.delete())
        throw new IOException(RB.workfile_delete_fail.getString(new String[] { this.writeFile.getAbsolutePath() }));
    }
    finally
    {
      this.writeStream = null;
    }
  }

  public long getBytesWritten()
  {
    return this.bytesWritten;
  }

  public void finish()
    throws IOException
  {
    try
    {
      long l = this.bytesWritten / 512L + 2L;
      if (l % this.blocksPerRecord != 0L)
        l = (l / this.blocksPerRecord + 1L) * this.blocksPerRecord;
      int i = (int)(l - this.bytesWritten / 512L);
      if (debug)
        System.out.println(RB.pad_block_write.getString(i));
      writePadBlocks(i);
    }
    catch (IOException localIOException1)
    {
      try
      {
        close();
      }
      catch (IOException localIOException2)
      {
      }
      throw localIOException1;
    }
    this.writeStream.close();
    this.writeFile.renameTo(this.targetFile);
  }

  public static abstract interface Compression
  {
    public static final int NO_COMPRESSION = 0;
    public static final int GZIP_COMPRESSION = 1;
    public static final int DEFAULT_COMPRESSION = 0;
    public static final int DEFAULT_BLOCKS_PER_RECORD = 20;
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.lib.tar.TarFileOutputStream
 * JD-Core Version:    0.6.2
 */