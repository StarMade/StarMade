/*   1:    */package de.jarnbjo.ogg;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */import java.io.InputStream;
/*   5:    */import java.io.RandomAccessFile;
/*   6:    */import java.net.URL;
/*   7:    */import java.net.URLConnection;
/*   8:    */import java.util.ArrayList;
/*   9:    */import java.util.Collection;
/*  10:    */import java.util.HashMap;
/*  11:    */import java.util.Iterator;
/*  12:    */
/*  36:    */public class CachedUrlStream
/*  37:    */  implements PhysicalOggStream
/*  38:    */{
/*  39: 39 */  private boolean closed = false;
/*  40:    */  private URLConnection source;
/*  41:    */  private InputStream sourceStream;
/*  42: 42 */  private Object drainLock = new Object();
/*  43:    */  private RandomAccessFile drain;
/*  44:    */  private byte[] memoryCache;
/*  45: 45 */  private ArrayList pageOffsets = new ArrayList();
/*  46: 46 */  private ArrayList pageLengths = new ArrayList();
/*  47: 47 */  private long numberOfSamples = -1L;
/*  48:    */  
/*  49:    */  private long cacheLength;
/*  50: 50 */  private HashMap logicalStreams = new HashMap();
/*  51:    */  
/*  53:    */  private LoaderThread loaderThread;
/*  54:    */  
/*  56:    */  public CachedUrlStream(URL source)
/*  57:    */    throws OggFormatException, IOException
/*  58:    */  {
/*  59: 59 */    this(source, null);
/*  60:    */  }
/*  61:    */  
/*  66:    */  public CachedUrlStream(URL source, RandomAccessFile drain)
/*  67:    */    throws OggFormatException, IOException
/*  68:    */  {
/*  69: 69 */    this.source = source.openConnection();
/*  70:    */    
/*  71: 71 */    if (drain == null) {
/*  72: 72 */      int contentLength = this.source.getContentLength();
/*  73: 73 */      if (contentLength == -1) {
/*  74: 74 */        throw new IOException("The URLConncetion's content length must be set when operating with a in-memory cache.");
/*  75:    */      }
/*  76: 76 */      this.memoryCache = new byte[contentLength];
/*  77:    */    }
/*  78:    */    
/*  79: 79 */    this.drain = drain;
/*  80: 80 */    this.sourceStream = this.source.getInputStream();
/*  81:    */    
/*  82: 82 */    this.loaderThread = new LoaderThread(this.sourceStream, drain, this.memoryCache);
/*  83: 83 */    new Thread(this.loaderThread).start();
/*  84:    */    
/*  85: 85 */    while ((!this.loaderThread.isBosDone()) || (this.pageOffsets.size() < 20)) {
/*  86:    */      try
/*  87:    */      {
/*  88: 88 */        Thread.sleep(200L);
/*  89:    */      }
/*  90:    */      catch (InterruptedException ex) {}
/*  91:    */    }
/*  92:    */  }
/*  93:    */  
/*  96:    */  public Collection getLogicalStreams()
/*  97:    */  {
/*  98: 98 */    return this.logicalStreams.values();
/*  99:    */  }
/* 100:    */  
/* 101:    */  public boolean isOpen() {
/* 102:102 */    return !this.closed;
/* 103:    */  }
/* 104:    */  
/* 105:    */  public void close() throws IOException {
/* 106:106 */    this.closed = true;
/* 107:107 */    this.sourceStream.close();
/* 108:    */  }
/* 109:    */  
/* 110:    */  public long getCacheLength() {
/* 111:111 */    return this.cacheLength;
/* 112:    */  }
/* 113:    */  
/* 122:    */  public OggPage getOggPage(int index)
/* 123:    */    throws IOException
/* 124:    */  {
/* 125:125 */    synchronized (this.drainLock) {
/* 126:126 */      Long offset = (Long)this.pageOffsets.get(index);
/* 127:127 */      Long length = (Long)this.pageLengths.get(index);
/* 128:128 */      if (offset != null) {
/* 129:129 */        if (this.drain != null) {
/* 130:130 */          this.drain.seek(offset.longValue());
/* 131:131 */          return OggPage.create(this.drain);
/* 132:    */        }
/* 133:    */        
/* 134:134 */        byte[] tmpArray = new byte[length.intValue()];
/* 135:135 */        System.arraycopy(this.memoryCache, offset.intValue(), tmpArray, 0, length.intValue());
/* 136:136 */        return OggPage.create(tmpArray);
/* 137:    */      }
/* 138:    */      
/* 140:140 */      return null;
/* 141:    */    }
/* 142:    */  }
/* 143:    */  
/* 144:    */  private LogicalOggStream getLogicalStream(int serialNumber)
/* 145:    */  {
/* 146:146 */    return (LogicalOggStream)this.logicalStreams.get(new Integer(serialNumber));
/* 147:    */  }
/* 148:    */  
/* 149:    */  public void setTime(long granulePosition) throws IOException {
/* 150:150 */    for (Iterator iter = this.logicalStreams.values().iterator(); iter.hasNext();) {
/* 151:151 */      LogicalOggStream los = (LogicalOggStream)iter.next();
/* 152:152 */      los.setTime(granulePosition);
/* 153:    */    }
/* 154:    */  }
/* 155:    */  
/* 156:    */  public class LoaderThread
/* 157:    */    implements Runnable
/* 158:    */  {
/* 159:    */    private InputStream source;
/* 160:    */    private RandomAccessFile drain;
/* 161:    */    private byte[] memoryCache;
/* 162:162 */    private boolean bosDone = false;
/* 163:    */    private int pageNumber;
/* 164:    */    
/* 165:    */    public LoaderThread(InputStream source, RandomAccessFile drain, byte[] memoryCache)
/* 166:    */    {
/* 167:167 */      this.source = source;
/* 168:168 */      this.drain = drain;
/* 169:169 */      this.memoryCache = memoryCache;
/* 170:    */    }
/* 171:    */    
/* 172:    */    public void run() {
/* 173:    */      try {
/* 174:174 */        boolean eos = false;
/* 175:175 */        byte[] buffer = new byte[8192];
/* 176:176 */        while (!eos) {
/* 177:177 */          OggPage op = OggPage.create(this.source);
/* 178:178 */          synchronized (CachedUrlStream.this.drainLock) {
/* 179:179 */            int listSize = CachedUrlStream.this.pageOffsets.size();
/* 180:    */            
/* 181:181 */            long pos = listSize > 0 ? ((Long)CachedUrlStream.this.pageOffsets.get(listSize - 1)).longValue() + ((Long)CachedUrlStream.this.pageLengths.get(listSize - 1)).longValue() : 0L;
/* 182:    */            
/* 187:187 */            byte[] arr1 = op.getHeader();
/* 188:188 */            byte[] arr2 = op.getSegmentTable();
/* 189:189 */            byte[] arr3 = op.getData();
/* 190:    */            
/* 191:191 */            if (this.drain != null) {
/* 192:192 */              this.drain.seek(pos);
/* 193:193 */              this.drain.write(arr1);
/* 194:194 */              this.drain.write(arr2);
/* 195:195 */              this.drain.write(arr3);
/* 196:    */            }
/* 197:    */            else {
/* 198:198 */              System.arraycopy(arr1, 0, this.memoryCache, (int)pos, arr1.length);
/* 199:199 */              System.arraycopy(arr2, 0, this.memoryCache, (int)pos + arr1.length, arr2.length);
/* 200:200 */              System.arraycopy(arr3, 0, this.memoryCache, (int)pos + arr1.length + arr2.length, arr3.length);
/* 201:    */            }
/* 202:    */            
/* 203:203 */            CachedUrlStream.this.pageOffsets.add(new Long(pos));
/* 204:204 */            CachedUrlStream.this.pageLengths.add(new Long(arr1.length + arr2.length + arr3.length));
/* 205:    */          }
/* 206:    */          
/* 207:207 */          if (!op.isBos()) {
/* 208:208 */            this.bosDone = true;
/* 209:    */          }
/* 210:    */          
/* 211:211 */          if (op.isEos()) {
/* 212:212 */            eos = true;
/* 213:    */          }
/* 214:    */          
/* 215:215 */          LogicalOggStreamImpl los = (LogicalOggStreamImpl)CachedUrlStream.this.getLogicalStream(op.getStreamSerialNumber());
/* 216:216 */          if (los == null) {
/* 217:217 */            los = new LogicalOggStreamImpl(CachedUrlStream.this, op.getStreamSerialNumber());
/* 218:218 */            CachedUrlStream.this.logicalStreams.put(new Integer(op.getStreamSerialNumber()), los);
/* 219:219 */            los.checkFormat(op);
/* 220:    */          }
/* 221:    */          
/* 222:222 */          los.addPageNumberMapping(this.pageNumber);
/* 223:223 */          los.addGranulePosition(op.getAbsoluteGranulePosition());
/* 224:    */          
/* 225:225 */          this.pageNumber += 1;
/* 226:226 */          CachedUrlStream.this.cacheLength = op.getAbsoluteGranulePosition();
/* 227:    */        }
/* 228:    */        
/* 230:    */      }
/* 231:    */      catch (EndOfOggStreamException e) {}catch (IOException e)
/* 232:    */      {
/* 234:234 */        e.printStackTrace();
/* 235:    */      }
/* 236:    */    }
/* 237:    */    
/* 238:    */    public boolean isBosDone() {
/* 239:239 */      return this.bosDone;
/* 240:    */    }
/* 241:    */  }
/* 242:    */  
/* 243:    */  public boolean isSeekable() {
/* 244:244 */    return true;
/* 245:    */  }
/* 246:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     de.jarnbjo.ogg.CachedUrlStream
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */