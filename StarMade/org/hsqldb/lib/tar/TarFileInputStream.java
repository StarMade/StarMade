package org.hsqldb.lib.tar;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

public class TarFileInputStream
{
  protected long bytesRead = 0L;
  private InputStream readStream;
  protected byte[] readBuffer;
  protected int readBufferBlocks;
  protected int compressionType;
  
  public TarFileInputStream(File paramFile)
    throws IOException
  {
    this(paramFile, 0);
  }
  
  public TarFileInputStream(File paramFile, int paramInt)
    throws IOException
  {
    this(paramFile, paramInt, 20);
  }
  
  public int getReadBufferBlocks()
  {
    return this.readBufferBlocks;
  }
  
  public TarFileInputStream(File paramFile, int paramInt1, int paramInt2)
    throws IOException
  {
    if (!paramFile.isFile()) {
      throw new FileNotFoundException(paramFile.getAbsolutePath());
    }
    if (!paramFile.canRead()) {
      throw new IOException(class_1436.read_denied.getString(new String[] { paramFile.getAbsolutePath() }));
    }
    this.readBufferBlocks = paramInt2;
    this.compressionType = paramInt1;
    this.readBuffer = new byte[paramInt2 * 512];
    switch (paramInt1)
    {
    case 0: 
      this.readStream = new FileInputStream(paramFile);
      break;
    case 1: 
      this.readStream = new GZIPInputStream(new FileInputStream(paramFile), this.readBuffer.length);
      break;
    default: 
      throw new IllegalArgumentException(class_1436.compression_unknown.getString(paramInt1));
    }
  }
  
  public void readBlocks(int paramInt)
    throws IOException, TarMalformatException
  {
    if (this.compressionType != 0)
    {
      readCompressedBlocks(paramInt);
      return;
    }
    int i = this.readStream.read(this.readBuffer, 0, paramInt * 512);
    this.bytesRead += i;
    if (i != paramInt * 512) {
      throw new TarMalformatException(class_1436.insufficient_read.getString(paramInt * 512, i));
    }
  }
  
  protected void readCompressedBlocks(int paramInt)
    throws IOException
  {
    int i = 0;
    int j = 512 * paramInt;
    while (i < j)
    {
      int k = this.readStream.read(this.readBuffer, i, j - i);
      if (k < 0) {
        throw new EOFException(class_1436.decompression_ranout.getString(i, j));
      }
      this.bytesRead += k;
      i += k;
    }
  }
  
  public void readBlock()
    throws IOException, TarMalformatException
  {
    readBlocks(1);
  }
  
  public boolean readNextHeaderBlock()
    throws IOException, TarMalformatException
  {
    try
    {
      while (this.readStream.available() > 0)
      {
        readBlock();
        if (this.readBuffer[0] != 0) {
          return true;
        }
      }
    }
    catch (EOFException localEOFException) {}
    close();
    return false;
  }
  
  public void close()
    throws IOException
  {
    if (this.readStream == null) {
      return;
    }
    try
    {
      this.readStream.close();
    }
    finally
    {
      this.readStream = null;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.lib.tar.TarFileInputStream
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */