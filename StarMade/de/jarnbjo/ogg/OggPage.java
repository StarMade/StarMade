package de.jarnbjo.ogg;

import de.jarnbjo.util.io.BitInputStream;
import de.jarnbjo.util.io.ByteArrayBitInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.RandomAccessFile;

public class OggPage
{
  private int version;
  private boolean continued;
  private boolean bos;
  private boolean eos;
  private long absoluteGranulePosition;
  private int streamSerialNumber;
  private int pageSequenceNumber;
  private int pageCheckSum;
  private int[] segmentOffsets;
  private int[] segmentLengths;
  private int totalLength;
  private byte[] header;
  private byte[] segmentTable;
  private byte[] data;
  
  protected OggPage() {}
  
  private OggPage(int version, boolean continued, boolean bos, boolean eos, long absoluteGranulePosition, int streamSerialNumber, int pageSequenceNumber, int pageCheckSum, int[] segmentOffsets, int[] segmentLengths, int totalLength, byte[] header, byte[] segmentTable, byte[] data)
  {
    this.version = version;
    this.continued = continued;
    this.bos = bos;
    this.eos = eos;
    this.absoluteGranulePosition = absoluteGranulePosition;
    this.streamSerialNumber = streamSerialNumber;
    this.pageSequenceNumber = pageSequenceNumber;
    this.pageCheckSum = pageCheckSum;
    this.segmentOffsets = segmentOffsets;
    this.segmentLengths = segmentLengths;
    this.totalLength = totalLength;
    this.header = header;
    this.segmentTable = segmentTable;
    this.data = data;
  }
  
  public static OggPage create(RandomAccessFile source)
    throws IOException, EndOfOggStreamException, OggFormatException
  {
    return create(source, false);
  }
  
  public static OggPage create(RandomAccessFile source, boolean skipData)
    throws IOException, EndOfOggStreamException, OggFormatException
  {
    return create(source, skipData);
  }
  
  public static OggPage create(InputStream source)
    throws IOException, EndOfOggStreamException, OggFormatException
  {
    return create(source, false);
  }
  
  public static OggPage create(InputStream source, boolean skipData)
    throws IOException, EndOfOggStreamException, OggFormatException
  {
    return create(source, skipData);
  }
  
  public static OggPage create(byte[] source)
    throws IOException, EndOfOggStreamException, OggFormatException
  {
    return create(source, false);
  }
  
  public static OggPage create(byte[] source, boolean skipData)
    throws IOException, EndOfOggStreamException, OggFormatException
  {
    return create(source, skipData);
  }
  
  private static OggPage create(Object source, boolean skipData)
    throws IOException, EndOfOggStreamException, OggFormatException
  {
    try
    {
      int sourceOffset = 27;
      byte[] header = new byte[27];
      if ((source instanceof RandomAccessFile))
      {
        RandomAccessFile raf = (RandomAccessFile)source;
        if (raf.getFilePointer() == raf.length()) {
          return null;
        }
        raf.readFully(header);
      }
      else if ((source instanceof InputStream))
      {
        readFully((InputStream)source, header);
      }
      else if ((source instanceof byte[]))
      {
        System.arraycopy((byte[])source, 0, header, 0, 27);
      }
      BitInputStream raf = new ByteArrayBitInputStream(header);
      int capture = raf.getInt(32);
      if (capture != 1399285583)
      {
        for (String local_cs = Integer.toHexString(capture); local_cs.length() < 8; local_cs = "0" + local_cs) {}
        local_cs = local_cs.substring(6, 8) + local_cs.substring(4, 6) + local_cs.substring(2, 4) + local_cs.substring(0, 2);
        char local_c1 = (char)Integer.valueOf(local_cs.substring(0, 2), 16).intValue();
        char local_c2 = (char)Integer.valueOf(local_cs.substring(2, 4), 16).intValue();
        char local_c3 = (char)Integer.valueOf(local_cs.substring(4, 6), 16).intValue();
        char local_c4 = (char)Integer.valueOf(local_cs.substring(6, 8), 16).intValue();
        System.out.println("Ogg packet header is 0x" + local_cs + " (" + local_c1 + local_c2 + local_c3 + local_c4 + "), should be 0x4f676753 (OggS)");
      }
      int local_cs = raf.getInt(8);
      byte local_c1 = (byte)raf.getInt(8);
      boolean local_c2 = (local_c1 & 0x1) != 0;
      boolean local_c3 = (local_c1 & 0x2) != 0;
      boolean local_c4 = (local_c1 & 0x4) != 0;
      long absoluteGranulePosition = raf.getLong(64);
      int streamSerialNumber = raf.getInt(32);
      int pageSequenceNumber = raf.getInt(32);
      int pageCheckSum = raf.getInt(32);
      int pageSegments = raf.getInt(8);
      int[] segmentOffsets = new int[pageSegments];
      int[] segmentLengths = new int[pageSegments];
      int totalLength = 0;
      byte[] segmentTable = new byte[pageSegments];
      byte[] tmpBuf = new byte[1];
      for (int local_i = 0; local_i < pageSegments; local_i++)
      {
        int local_l = 0;
        if ((source instanceof RandomAccessFile))
        {
          local_l = ((RandomAccessFile)source).readByte() & 0xFF;
        }
        else if ((source instanceof InputStream))
        {
          local_l = ((InputStream)source).read();
        }
        else if ((source instanceof byte[]))
        {
          local_l = ((byte[])(byte[])source)[(sourceOffset++)];
          local_l &= 255;
        }
        segmentTable[local_i] = ((byte)local_l);
        segmentLengths[local_i] = local_l;
        segmentOffsets[local_i] = totalLength;
        totalLength += local_l;
      }
      byte[] local_i = null;
      if (!skipData)
      {
        local_i = new byte[totalLength];
        if ((source instanceof RandomAccessFile)) {
          ((RandomAccessFile)source).readFully(local_i);
        } else if ((source instanceof InputStream)) {
          readFully((InputStream)source, local_i);
        } else if ((source instanceof byte[])) {
          System.arraycopy(source, sourceOffset, local_i, 0, totalLength);
        }
      }
      return new OggPage(local_cs, local_c2, local_c3, local_c4, absoluteGranulePosition, streamSerialNumber, pageSequenceNumber, pageCheckSum, segmentOffsets, segmentLengths, totalLength, header, segmentTable, local_i);
    }
    catch (EOFException sourceOffset)
    {
      throw new EndOfOggStreamException();
    }
  }
  
  private static void readFully(InputStream source, byte[] buffer)
    throws IOException
  {
    int total = 0;
    while (total < buffer.length)
    {
      int read = source.read(buffer, total, buffer.length - total);
      if (read == -1) {
        throw new EndOfOggStreamException();
      }
      total += read;
    }
  }
  
  public long getAbsoluteGranulePosition()
  {
    return this.absoluteGranulePosition;
  }
  
  public int getStreamSerialNumber()
  {
    return this.streamSerialNumber;
  }
  
  public int getPageSequenceNumber()
  {
    return this.pageSequenceNumber;
  }
  
  public int getPageCheckSum()
  {
    return this.pageCheckSum;
  }
  
  public int getTotalLength()
  {
    if (this.data != null) {
      return 27 + this.segmentTable.length + this.data.length;
    }
    return this.totalLength;
  }
  
  public byte[] getData()
  {
    return this.data;
  }
  
  public byte[] getHeader()
  {
    return this.header;
  }
  
  public byte[] getSegmentTable()
  {
    return this.segmentTable;
  }
  
  public int[] getSegmentOffsets()
  {
    return this.segmentOffsets;
  }
  
  public int[] getSegmentLengths()
  {
    return this.segmentLengths;
  }
  
  public boolean isContinued()
  {
    return this.continued;
  }
  
  public boolean isFresh()
  {
    return !this.continued;
  }
  
  public boolean isBos()
  {
    return this.bos;
  }
  
  public boolean isEos()
  {
    return this.eos;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     de.jarnbjo.ogg.OggPage
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */