/*   1:    */package de.jarnbjo.ogg;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */import java.io.InputStream;
/*   5:    */import java.io.RandomAccessFile;
/*   6:    */import java.net.URL;
/*   7:    */import java.net.URLConnection;
/*   8:    */import java.util.Collection;
/*   9:    */import java.util.HashMap;
/*  10:    */import java.util.LinkedList;
/*  11:    */
/*  34:    */public class UncachedUrlStream
/*  35:    */  implements PhysicalOggStream
/*  36:    */{
/*  37: 37 */  private boolean closed = false;
/*  38:    */  private URLConnection source;
/*  39:    */  private InputStream sourceStream;
/*  40: 40 */  private Object drainLock = new Object();
/*  41: 41 */  private LinkedList pageCache = new LinkedList();
/*  42: 42 */  private long numberOfSamples = -1L;
/*  43:    */  
/*  44: 44 */  private HashMap logicalStreams = new HashMap();
/*  45:    */  
/*  47:    */  private LoaderThread loaderThread;
/*  48:    */  
/*  50:    */  private static final int PAGECACHE_SIZE = 10;
/*  51:    */  
/*  53:    */  public UncachedUrlStream(URL source)
/*  54:    */    throws OggFormatException, IOException
/*  55:    */  {
/*  56: 56 */    this.source = source.openConnection();
/*  57: 57 */    this.sourceStream = this.source.getInputStream();
/*  58:    */    
/*  59: 59 */    this.loaderThread = new LoaderThread(this.sourceStream, this.pageCache);
/*  60: 60 */    new Thread(this.loaderThread).start();
/*  61:    */    
/*  62: 62 */    while ((!this.loaderThread.isBosDone()) || (this.pageCache.size() < 10)) {
/*  63:    */      try {
/*  64: 64 */        Thread.sleep(200L);
/*  65:    */      }
/*  66:    */      catch (InterruptedException ex) {}
/*  67:    */    }
/*  68:    */  }
/*  69:    */  
/*  72:    */  public Collection getLogicalStreams()
/*  73:    */  {
/*  74: 74 */    return this.logicalStreams.values();
/*  75:    */  }
/*  76:    */  
/*  77:    */  public boolean isOpen() {
/*  78: 78 */    return !this.closed;
/*  79:    */  }
/*  80:    */  
/*  81:    */  public void close() throws IOException {
/*  82: 82 */    this.closed = true;
/*  83: 83 */    this.sourceStream.close();
/*  84:    */  }
/*  85:    */  
/* 100:    */  public OggPage getOggPage(int index)
/* 101:    */    throws IOException
/* 102:    */  {
/* 103:103 */    while (this.pageCache.size() == 0) {
/* 104:    */      try {
/* 105:105 */        Thread.sleep(100L);
/* 106:    */      }
/* 107:    */      catch (InterruptedException ex) {}
/* 108:    */    }
/* 109:    */    
/* 110:110 */    synchronized (this.drainLock)
/* 111:    */    {
/* 114:114 */      return (OggPage)this.pageCache.removeFirst();
/* 115:    */    }
/* 116:    */  }
/* 117:    */  
/* 118:    */  private LogicalOggStream getLogicalStream(int serialNumber) {
/* 119:119 */    return (LogicalOggStream)this.logicalStreams.get(new Integer(serialNumber));
/* 120:    */  }
/* 121:    */  
/* 122:    */  public void setTime(long granulePosition) throws IOException {
/* 123:123 */    throw new UnsupportedOperationException("Method not supported by this class");
/* 124:    */  }
/* 125:    */  
/* 126:    */  public class LoaderThread
/* 127:    */    implements Runnable
/* 128:    */  {
/* 129:    */    private InputStream source;
/* 130:    */    private LinkedList pageCache;
/* 131:    */    private RandomAccessFile drain;
/* 132:    */    private byte[] memoryCache;
/* 133:133 */    private boolean bosDone = false;
/* 134:    */    private int pageNumber;
/* 135:    */    
/* 136:    */    public LoaderThread(InputStream source, LinkedList pageCache)
/* 137:    */    {
/* 138:138 */      this.source = source;
/* 139:139 */      this.pageCache = pageCache;
/* 140:    */    }
/* 141:    */    
/* 142:    */    public void run() {
/* 143:    */      try {
/* 144:144 */        boolean eos = false;
/* 145:145 */        byte[] buffer = new byte[8192];
/* 146:146 */        while (!eos) {
/* 147:147 */          OggPage op = OggPage.create(this.source);
/* 148:148 */          synchronized (UncachedUrlStream.this.drainLock) {
/* 149:149 */            this.pageCache.add(op);
/* 150:    */          }
/* 151:    */          
/* 152:152 */          if (!op.isBos()) {
/* 153:153 */            this.bosDone = true;
/* 154:    */          }
/* 155:155 */          if (op.isEos()) {
/* 156:156 */            eos = true;
/* 157:    */          }
/* 158:    */          
/* 159:159 */          LogicalOggStreamImpl los = (LogicalOggStreamImpl)UncachedUrlStream.this.getLogicalStream(op.getStreamSerialNumber());
/* 160:160 */          if (los == null) {
/* 161:161 */            los = new LogicalOggStreamImpl(UncachedUrlStream.this, op.getStreamSerialNumber());
/* 162:162 */            UncachedUrlStream.this.logicalStreams.put(new Integer(op.getStreamSerialNumber()), los);
/* 163:163 */            los.checkFormat(op);
/* 164:    */          }
/* 165:    */          
/* 169:169 */          this.pageNumber += 1;
/* 170:    */          
/* 171:171 */          while (this.pageCache.size() > 10) {
/* 172:    */            try {
/* 173:173 */              Thread.sleep(200L);
/* 175:    */            }
/* 176:    */            catch (InterruptedException ex) {}
/* 177:    */          }
/* 178:    */          
/* 179:    */        }
/* 180:    */        
/* 181:    */      }
/* 182:    */      catch (EndOfOggStreamException e) {}catch (IOException e)
/* 183:    */      {
/* 184:184 */        e.printStackTrace();
/* 185:    */      }
/* 186:    */    }
/* 187:    */    
/* 188:    */    public boolean isBosDone() {
/* 189:189 */      return this.bosDone;
/* 190:    */    }
/* 191:    */  }
/* 192:    */  
/* 196:    */  public boolean isSeekable()
/* 197:    */  {
/* 198:198 */    return false;
/* 199:    */  }
/* 200:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     de.jarnbjo.ogg.UncachedUrlStream
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */