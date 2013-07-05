/*     */ package de.jarnbjo.ogg;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.RandomAccessFile;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class CachedUrlStream
/*     */   implements PhysicalOggStream
/*     */ {
/*  39 */   private boolean closed = false;
/*     */   private URLConnection source;
/*     */   private InputStream sourceStream;
/*  42 */   private Object drainLock = new Object();
/*     */   private RandomAccessFile drain;
/*     */   private byte[] memoryCache;
/*  45 */   private ArrayList pageOffsets = new ArrayList();
/*  46 */   private ArrayList pageLengths = new ArrayList();
/*  47 */   private long numberOfSamples = -1L;
/*     */   private long cacheLength;
/*  50 */   private HashMap logicalStreams = new HashMap();
/*     */   private LoaderThread loaderThread;
/*     */ 
/*     */   public CachedUrlStream(URL source)
/*     */     throws OggFormatException, IOException
/*     */   {
/*  59 */     this(source, null);
/*     */   }
/*     */ 
/*     */   public CachedUrlStream(URL source, RandomAccessFile drain)
/*     */     throws OggFormatException, IOException
/*     */   {
/*  69 */     this.source = source.openConnection();
/*     */ 
/*  71 */     if (drain == null) {
/*  72 */       int contentLength = this.source.getContentLength();
/*  73 */       if (contentLength == -1) {
/*  74 */         throw new IOException("The URLConncetion's content length must be set when operating with a in-memory cache.");
/*     */       }
/*  76 */       this.memoryCache = new byte[contentLength];
/*     */     }
/*     */ 
/*  79 */     this.drain = drain;
/*  80 */     this.sourceStream = this.source.getInputStream();
/*     */ 
/*  82 */     this.loaderThread = new LoaderThread(this.sourceStream, drain, this.memoryCache);
/*  83 */     new Thread(this.loaderThread).start();
/*     */ 
/*  85 */     while ((!this.loaderThread.isBosDone()) || (this.pageOffsets.size() < 20))
/*     */       try
/*     */       {
/*  88 */         Thread.sleep(200L);
/*     */       }
/*     */       catch (InterruptedException ex)
/*     */       {
/*     */       }
/*     */   }
/*     */ 
/*     */   public Collection getLogicalStreams()
/*     */   {
/*  98 */     return this.logicalStreams.values();
/*     */   }
/*     */ 
/*     */   public boolean isOpen() {
/* 102 */     return !this.closed;
/*     */   }
/*     */ 
/*     */   public void close() throws IOException {
/* 106 */     this.closed = true;
/* 107 */     this.sourceStream.close();
/*     */   }
/*     */ 
/*     */   public long getCacheLength() {
/* 111 */     return this.cacheLength;
/*     */   }
/*     */ 
/*     */   public OggPage getOggPage(int index)
/*     */     throws IOException
/*     */   {
/* 125 */     synchronized (this.drainLock) {
/* 126 */       Long offset = (Long)this.pageOffsets.get(index);
/* 127 */       Long length = (Long)this.pageLengths.get(index);
/* 128 */       if (offset != null) {
/* 129 */         if (this.drain != null) {
/* 130 */           this.drain.seek(offset.longValue());
/* 131 */           return OggPage.create(this.drain);
/*     */         }
/*     */ 
/* 134 */         byte[] tmpArray = new byte[length.intValue()];
/* 135 */         System.arraycopy(this.memoryCache, offset.intValue(), tmpArray, 0, length.intValue());
/* 136 */         return OggPage.create(tmpArray);
/*     */       }
/*     */ 
/* 140 */       return null;
/*     */     }
/*     */   }
/*     */ 
/*     */   private LogicalOggStream getLogicalStream(int serialNumber)
/*     */   {
/* 146 */     return (LogicalOggStream)this.logicalStreams.get(new Integer(serialNumber));
/*     */   }
/*     */ 
/*     */   public void setTime(long granulePosition) throws IOException {
/* 150 */     for (Iterator iter = this.logicalStreams.values().iterator(); iter.hasNext(); ) {
/* 151 */       LogicalOggStream los = (LogicalOggStream)iter.next();
/* 152 */       los.setTime(granulePosition);
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean isSeekable()
/*     */   {
/* 244 */     return true;
/*     */   }
/*     */ 
/*     */   public class LoaderThread
/*     */     implements Runnable
/*     */   {
/*     */     private InputStream source;
/*     */     private RandomAccessFile drain;
/*     */     private byte[] memoryCache;
/* 162 */     private boolean bosDone = false;
/*     */     private int pageNumber;
/*     */ 
/*     */     public LoaderThread(InputStream source, RandomAccessFile drain, byte[] memoryCache)
/*     */     {
/* 167 */       this.source = source;
/* 168 */       this.drain = drain;
/* 169 */       this.memoryCache = memoryCache;
/*     */     }
/*     */ 
/*     */     public void run() {
/*     */       try {
/* 174 */         boolean eos = false;
/* 175 */         byte[] buffer = new byte[8192];
/* 176 */         while (!eos) {
/* 177 */           OggPage op = OggPage.create(this.source);
/* 178 */           synchronized (CachedUrlStream.this.drainLock) {
/* 179 */             int listSize = CachedUrlStream.this.pageOffsets.size();
/*     */ 
/* 181 */             long pos = listSize > 0 ? ((Long)CachedUrlStream.this.pageOffsets.get(listSize - 1)).longValue() + ((Long)CachedUrlStream.this.pageLengths.get(listSize - 1)).longValue() : 0L;
/*     */ 
/* 187 */             byte[] arr1 = op.getHeader();
/* 188 */             byte[] arr2 = op.getSegmentTable();
/* 189 */             byte[] arr3 = op.getData();
/*     */ 
/* 191 */             if (this.drain != null) {
/* 192 */               this.drain.seek(pos);
/* 193 */               this.drain.write(arr1);
/* 194 */               this.drain.write(arr2);
/* 195 */               this.drain.write(arr3);
/*     */             }
/*     */             else {
/* 198 */               System.arraycopy(arr1, 0, this.memoryCache, (int)pos, arr1.length);
/* 199 */               System.arraycopy(arr2, 0, this.memoryCache, (int)pos + arr1.length, arr2.length);
/* 200 */               System.arraycopy(arr3, 0, this.memoryCache, (int)pos + arr1.length + arr2.length, arr3.length);
/*     */             }
/*     */ 
/* 203 */             CachedUrlStream.this.pageOffsets.add(new Long(pos));
/* 204 */             CachedUrlStream.this.pageLengths.add(new Long(arr1.length + arr2.length + arr3.length));
/*     */           }
/*     */ 
/* 207 */           if (!op.isBos()) {
/* 208 */             this.bosDone = true;
/*     */           }
/*     */ 
/* 211 */           if (op.isEos()) {
/* 212 */             eos = true;
/*     */           }
/*     */ 
/* 215 */           LogicalOggStreamImpl los = (LogicalOggStreamImpl)CachedUrlStream.this.getLogicalStream(op.getStreamSerialNumber());
/* 216 */           if (los == null) {
/* 217 */             los = new LogicalOggStreamImpl(CachedUrlStream.this, op.getStreamSerialNumber());
/* 218 */             CachedUrlStream.this.logicalStreams.put(new Integer(op.getStreamSerialNumber()), los);
/* 219 */             los.checkFormat(op);
/*     */           }
/*     */ 
/* 222 */           los.addPageNumberMapping(this.pageNumber);
/* 223 */           los.addGranulePosition(op.getAbsoluteGranulePosition());
/*     */ 
/* 225 */           this.pageNumber += 1;
/* 226 */           CachedUrlStream.this.cacheLength = op.getAbsoluteGranulePosition();
/*     */         }
/*     */       }
/*     */       catch (EndOfOggStreamException e)
/*     */       {
/*     */       }
/*     */       catch (IOException e)
/*     */       {
/* 234 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */ 
/*     */     public boolean isBosDone() {
/* 239 */       return this.bosDone;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     de.jarnbjo.ogg.CachedUrlStream
 * JD-Core Version:    0.6.2
 */