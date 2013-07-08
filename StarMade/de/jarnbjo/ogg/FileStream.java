package de.jarnbjo.ogg;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class FileStream
  implements PhysicalOggStream
{
  private boolean closed = false;
  private RandomAccessFile source;
  private long[] pageOffsets;
  private long numberOfSamples = -1L;
  private HashMap logicalStreams = new HashMap();
  
  public FileStream(RandomAccessFile source)
    throws OggFormatException, IOException
  {
    this.source = source;
    ArrayList local_po = new ArrayList();
    int pageNumber = 0;
    try
    {
      for (;;)
      {
        local_po.add(new Long(this.source.getFilePointer()));
        OggPage local_op = getNextPage(pageNumber > 0);
        if (local_op == null) {
          break;
        }
        LogicalOggStreamImpl los = (LogicalOggStreamImpl)getLogicalStream(local_op.getStreamSerialNumber());
        if (los == null)
        {
          los = new LogicalOggStreamImpl(this, local_op.getStreamSerialNumber());
          this.logicalStreams.put(new Integer(local_op.getStreamSerialNumber()), los);
        }
        if (pageNumber == 0) {
          los.checkFormat(local_op);
        }
        los.addPageNumberMapping(pageNumber);
        los.addGranulePosition(local_op.getAbsoluteGranulePosition());
        if (pageNumber > 0) {
          this.source.seek(this.source.getFilePointer() + local_op.getTotalLength());
        }
        pageNumber++;
      }
    }
    catch (EndOfOggStreamException local_op) {}catch (IOException local_op)
    {
      throw local_op;
    }
    this.source.seek(0L);
    this.pageOffsets = new long[local_po.size()];
    int local_op = 0;
    Iterator los = local_po.iterator();
    while (los.hasNext()) {
      this.pageOffsets[(local_op++)] = ((Long)los.next()).longValue();
    }
  }
  
  public Collection getLogicalStreams()
  {
    return this.logicalStreams.values();
  }
  
  public boolean isOpen()
  {
    return !this.closed;
  }
  
  public void close()
    throws IOException
  {
    this.closed = true;
    this.source.close();
  }
  
  private OggPage getNextPage()
    throws EndOfOggStreamException, IOException, OggFormatException
  {
    return getNextPage(false);
  }
  
  private OggPage getNextPage(boolean skipData)
    throws EndOfOggStreamException, IOException, OggFormatException
  {
    return OggPage.create(this.source, skipData);
  }
  
  public OggPage getOggPage(int index)
    throws IOException
  {
    this.source.seek(this.pageOffsets[index]);
    return OggPage.create(this.source);
  }
  
  private LogicalOggStream getLogicalStream(int serialNumber)
  {
    return (LogicalOggStream)this.logicalStreams.get(new Integer(serialNumber));
  }
  
  public void setTime(long granulePosition)
    throws IOException
  {
    Iterator iter = this.logicalStreams.values().iterator();
    while (iter.hasNext())
    {
      LogicalOggStream los = (LogicalOggStream)iter.next();
      los.setTime(granulePosition);
    }
  }
  
  public boolean isSeekable()
  {
    return true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     de.jarnbjo.ogg.FileStream
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */