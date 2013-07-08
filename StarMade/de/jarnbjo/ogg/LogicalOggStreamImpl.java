package de.jarnbjo.ogg;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class LogicalOggStreamImpl
  implements LogicalOggStream
{
  private PhysicalOggStream source;
  private int serialNumber;
  private ArrayList pageNumberMapping = new ArrayList();
  private ArrayList granulePositions = new ArrayList();
  private int pageIndex = 0;
  private OggPage currentPage;
  private int currentSegmentIndex;
  private boolean open = true;
  private String format = "application/octet-stream";
  
  public LogicalOggStreamImpl(PhysicalOggStream source, int serialNumber)
  {
    this.source = source;
    this.serialNumber = serialNumber;
  }
  
  public void addPageNumberMapping(int physicalPageNumber)
  {
    this.pageNumberMapping.add(new Integer(physicalPageNumber));
  }
  
  public void addGranulePosition(long granulePosition)
  {
    this.granulePositions.add(new Long(granulePosition));
  }
  
  public synchronized void reset()
    throws OggFormatException, IOException
  {
    this.currentPage = null;
    this.currentSegmentIndex = 0;
    this.pageIndex = 0;
  }
  
  public synchronized OggPage getNextOggPage()
    throws EndOfOggStreamException, OggFormatException, IOException
  {
    if (this.source.isSeekable()) {
      this.currentPage = this.source.getOggPage(((Integer)this.pageNumberMapping.get(this.pageIndex++)).intValue());
    } else {
      this.currentPage = this.source.getOggPage(-1);
    }
    return this.currentPage;
  }
  
  public synchronized byte[] getNextOggPacket()
    throws EndOfOggStreamException, OggFormatException, IOException
  {
    ByteArrayOutputStream res = new ByteArrayOutputStream();
    int segmentLength = 0;
    if (this.currentPage == null) {
      this.currentPage = getNextOggPage();
    }
    do
    {
      if (this.currentSegmentIndex >= this.currentPage.getSegmentOffsets().length)
      {
        this.currentSegmentIndex = 0;
        if (!this.currentPage.isEos())
        {
          if ((this.source.isSeekable()) && (this.pageNumberMapping.size() <= this.pageIndex)) {
            while (this.pageNumberMapping.size() <= this.pageIndex + 10) {
              try
              {
                Thread.sleep(1000L);
              }
              catch (InterruptedException local_ex) {}
            }
          }
          this.currentPage = getNextOggPage();
          if ((res.size() == 0) && (this.currentPage.isContinued()))
          {
            boolean local_ex = false;
            while (!local_ex)
            {
              if (this.currentPage.getSegmentLengths()[(this.currentSegmentIndex++)] != 255) {
                local_ex = true;
              }
              if (this.currentSegmentIndex > this.currentPage.getSegmentTable().length) {
                this.currentPage = this.source.getOggPage(((Integer)this.pageNumberMapping.get(this.pageIndex++)).intValue());
              }
            }
          }
        }
        else
        {
          throw new EndOfOggStreamException();
        }
      }
      segmentLength = this.currentPage.getSegmentLengths()[this.currentSegmentIndex];
      res.write(this.currentPage.getData(), this.currentPage.getSegmentOffsets()[this.currentSegmentIndex], segmentLength);
      this.currentSegmentIndex += 1;
    } while (segmentLength == 255);
    return res.toByteArray();
  }
  
  public boolean isOpen()
  {
    return this.open;
  }
  
  public void close()
    throws IOException
  {
    this.open = false;
  }
  
  public long getMaximumGranulePosition()
  {
    Long mgp = (Long)this.granulePositions.get(this.granulePositions.size() - 1);
    return mgp.longValue();
  }
  
  public synchronized long getTime()
  {
    return this.currentPage != null ? this.currentPage.getAbsoluteGranulePosition() : -1L;
  }
  
  public synchronized void setTime(long granulePosition)
    throws IOException
  {
    int page = 0;
    for (page = 0; page < this.granulePositions.size(); page++)
    {
      Long local_gp = (Long)this.granulePositions.get(page);
      if (local_gp.longValue() > granulePosition) {
        break;
      }
    }
    this.pageIndex = page;
    this.currentPage = this.source.getOggPage(((Integer)this.pageNumberMapping.get(this.pageIndex++)).intValue());
    this.currentSegmentIndex = 0;
    int local_gp = 0;
    do
    {
      if (this.currentSegmentIndex >= this.currentPage.getSegmentOffsets().length)
      {
        this.currentSegmentIndex = 0;
        if (this.pageIndex >= this.pageNumberMapping.size()) {
          throw new EndOfOggStreamException();
        }
        this.currentPage = this.source.getOggPage(((Integer)this.pageNumberMapping.get(this.pageIndex++)).intValue());
      }
      local_gp = this.currentPage.getSegmentLengths()[this.currentSegmentIndex];
      this.currentSegmentIndex += 1;
    } while (local_gp == 255);
  }
  
  public void checkFormat(OggPage page)
  {
    byte[] data = page.getData();
    if ((data.length >= 7) && (data[1] == 118) && (data[2] == 111) && (data[3] == 114) && (data[4] == 98) && (data[5] == 105) && (data[6] == 115)) {
      this.format = "audio/x-vorbis";
    } else if ((data.length >= 7) && (data[1] == 116) && (data[2] == 104) && (data[3] == 101) && (data[4] == 111) && (data[5] == 114) && (data[6] == 97)) {
      this.format = "video/x-theora";
    } else if ((data.length == 4) && (data[0] == 102) && (data[1] == 76) && (data[2] == 97) && (data[3] == 67)) {
      this.format = "audio/x-flac";
    }
  }
  
  public String getFormat()
  {
    return this.format;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     de.jarnbjo.ogg.LogicalOggStreamImpl
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */