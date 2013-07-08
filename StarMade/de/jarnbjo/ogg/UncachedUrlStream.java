package de.jarnbjo.ogg;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

public class UncachedUrlStream
  implements PhysicalOggStream
{
  private boolean closed = false;
  private URLConnection source;
  private InputStream sourceStream;
  private Object drainLock = new Object();
  private LinkedList pageCache = new LinkedList();
  private long numberOfSamples = -1L;
  private HashMap logicalStreams = new HashMap();
  private LoaderThread loaderThread;
  private static final int PAGECACHE_SIZE = 10;
  
  public UncachedUrlStream(URL source)
    throws OggFormatException, IOException
  {
    this.source = source.openConnection();
    this.sourceStream = this.source.getInputStream();
    this.loaderThread = new LoaderThread(this.sourceStream, this.pageCache);
    new Thread(this.loaderThread).start();
    while ((!this.loaderThread.isBosDone()) || (this.pageCache.size() < 10)) {
      try
      {
        Thread.sleep(200L);
      }
      catch (InterruptedException local_ex) {}
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
  
  public OggPage getOggPage(int index)
    throws IOException
  {
    while (this.pageCache.size() == 0) {
      try
      {
        Thread.sleep(100L);
      }
      catch (InterruptedException local_ex) {}
    }
    synchronized (this.drainLock)
    {
      return (OggPage)this.pageCache.removeFirst();
    }
  }
  
  private LogicalOggStream getLogicalStream(int serialNumber)
  {
    return (LogicalOggStream)this.logicalStreams.get(new Integer(serialNumber));
  }
  
  public void setTime(long granulePosition)
    throws IOException
  {
    throw new UnsupportedOperationException("Method not supported by this class");
  }
  
  public boolean isSeekable()
  {
    return false;
  }
  
  public class LoaderThread
    implements Runnable
  {
    private InputStream source;
    private LinkedList pageCache;
    private RandomAccessFile drain;
    private byte[] memoryCache;
    private boolean bosDone = false;
    private int pageNumber;
    
    public LoaderThread(InputStream source, LinkedList pageCache)
    {
      this.source = source;
      this.pageCache = pageCache;
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
          synchronized (UncachedUrlStream.this.drainLock)
          {
            this.pageCache.add(local_op);
          }
          if (!local_op.isBos()) {
            this.bosDone = true;
          }
          if (local_op.isEos()) {
            eos = true;
          }
          LogicalOggStreamImpl los = (LogicalOggStreamImpl)UncachedUrlStream.this.getLogicalStream(local_op.getStreamSerialNumber());
          if (los == null)
          {
            los = new LogicalOggStreamImpl(UncachedUrlStream.this, local_op.getStreamSerialNumber());
            UncachedUrlStream.this.logicalStreams.put(new Integer(local_op.getStreamSerialNumber()), los);
            los.checkFormat(local_op);
          }
          this.pageNumber += 1;
          while (this.pageCache.size() > 10) {
            try
            {
              Thread.sleep(200L);
            }
            catch (InterruptedException local_ex) {}
          }
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
 * Qualified Name:     de.jarnbjo.ogg.UncachedUrlStream
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */