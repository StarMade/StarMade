package de.jarnbjo.ogg;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class CachedUrlStream
  implements PhysicalOggStream
{
  private boolean closed = false;
  private URLConnection source;
  private InputStream sourceStream;
  private Object drainLock = new Object();
  private RandomAccessFile drain;
  private byte[] memoryCache;
  private ArrayList pageOffsets = new ArrayList();
  private ArrayList pageLengths = new ArrayList();
  private long numberOfSamples = -1L;
  private long cacheLength;
  private HashMap logicalStreams = new HashMap();
  private LoaderThread loaderThread;
  
  public CachedUrlStream(URL source)
    throws OggFormatException, IOException
  {
    this(source, null);
  }
  
  public CachedUrlStream(URL source, RandomAccessFile drain)
    throws OggFormatException, IOException
  {
    this.source = source.openConnection();
    if (drain == null)
    {
      int contentLength = this.source.getContentLength();
      if (contentLength == -1) {
        throw new IOException("The URLConncetion's content length must be set when operating with a in-memory cache.");
      }
      this.memoryCache = new byte[contentLength];
    }
    this.drain = drain;
    this.sourceStream = this.source.getInputStream();
    this.loaderThread = new LoaderThread(this.sourceStream, drain, this.memoryCache);
    new Thread(this.loaderThread).start();
    while ((!this.loaderThread.isBosDone()) || (this.pageOffsets.size() < 20)) {
      try
      {
        Thread.sleep(200L);
      }
      catch (InterruptedException contentLength) {}
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
    this.sourceStream.close();
  }
  
  public long getCacheLength()
  {
    return this.cacheLength;
  }
  
  public OggPage getOggPage(int index)
    throws IOException
  {
    synchronized (this.drainLock)
    {
      Long offset = (Long)this.pageOffsets.get(index);
      Long length = (Long)this.pageLengths.get(index);
      if (offset != null)
      {
        if (this.drain != null)
        {
          this.drain.seek(offset.longValue());
          return OggPage.create(this.drain);
        }
        byte[] tmpArray = new byte[length.intValue()];
        System.arraycopy(this.memoryCache, offset.intValue(), tmpArray, 0, length.intValue());
        return OggPage.create(tmpArray);
      }
      return null;
    }
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
  
  public class LoaderThread
    implements Runnable
  {
    private InputStream source;
    private RandomAccessFile drain;
    private byte[] memoryCache;
    private boolean bosDone = false;
    private int pageNumber;
    
    public LoaderThread(InputStream source, RandomAccessFile drain, byte[] memoryCache)
    {
      this.source = source;
      this.drain = drain;
      this.memoryCache = memoryCache;
    }
    
    public void run()
    {
      try
      {
        boolean eos = false;
        byte[] buffer = new byte[8192];
        while (!eos)
        {
          OggPage local_op = OggPage.create(this.source);
          synchronized (CachedUrlStream.this.drainLock)
          {
            int listSize = CachedUrlStream.this.pageOffsets.size();
            long pos = listSize > 0 ? ((Long)CachedUrlStream.this.pageOffsets.get(listSize - 1)).longValue() + ((Long)CachedUrlStream.this.pageLengths.get(listSize - 1)).longValue() : 0L;
            byte[] arr1 = local_op.getHeader();
            byte[] arr2 = local_op.getSegmentTable();
            byte[] arr3 = local_op.getData();
            if (this.drain != null)
            {
              this.drain.seek(pos);
              this.drain.write(arr1);
              this.drain.write(arr2);
              this.drain.write(arr3);
            }
            else
            {
              System.arraycopy(arr1, 0, this.memoryCache, (int)pos, arr1.length);
              System.arraycopy(arr2, 0, this.memoryCache, (int)pos + arr1.length, arr2.length);
              System.arraycopy(arr3, 0, this.memoryCache, (int)pos + arr1.length + arr2.length, arr3.length);
            }
            CachedUrlStream.this.pageOffsets.add(new Long(pos));
            CachedUrlStream.this.pageLengths.add(new Long(arr1.length + arr2.length + arr3.length));
          }
          if (!local_op.isBos()) {
            this.bosDone = true;
          }
          if (local_op.isEos()) {
            eos = true;
          }
          LogicalOggStreamImpl los = (LogicalOggStreamImpl)CachedUrlStream.this.getLogicalStream(local_op.getStreamSerialNumber());
          if (los == null)
          {
            los = new LogicalOggStreamImpl(CachedUrlStream.this, local_op.getStreamSerialNumber());
            CachedUrlStream.this.logicalStreams.put(new Integer(local_op.getStreamSerialNumber()), los);
            los.checkFormat(local_op);
          }
          los.addPageNumberMapping(this.pageNumber);
          los.addGranulePosition(local_op.getAbsoluteGranulePosition());
          this.pageNumber += 1;
          CachedUrlStream.this.cacheLength = local_op.getAbsoluteGranulePosition();
        }
      }
      catch (EndOfOggStreamException eos) {}catch (IOException eos)
      {
        eos.printStackTrace();
      }
    }
    
    public boolean isBosDone()
    {
      return this.bosDone;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     de.jarnbjo.ogg.CachedUrlStream
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */