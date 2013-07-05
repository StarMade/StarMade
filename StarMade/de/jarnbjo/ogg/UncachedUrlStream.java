/*     */ package de.jarnbjo.ogg;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.RandomAccessFile;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedList;
/*     */ 
/*     */ public class UncachedUrlStream
/*     */   implements PhysicalOggStream
/*     */ {
/*  37 */   private boolean closed = false;
/*     */   private URLConnection source;
/*     */   private InputStream sourceStream;
/*  40 */   private Object drainLock = new Object();
/*  41 */   private LinkedList pageCache = new LinkedList();
/*  42 */   private long numberOfSamples = -1L;
/*     */ 
/*  44 */   private HashMap logicalStreams = new HashMap();
/*     */   private LoaderThread loaderThread;
/*     */   private static final int PAGECACHE_SIZE = 10;
/*     */ 
/*     */   public UncachedUrlStream(URL source)
/*     */     throws OggFormatException, IOException
/*     */   {
/*  56 */     this.source = source.openConnection();
/*  57 */     this.sourceStream = this.source.getInputStream();
/*     */ 
/*  59 */     this.loaderThread = new LoaderThread(this.sourceStream, this.pageCache);
/*  60 */     new Thread(this.loaderThread).start();
/*     */ 
/*  62 */     while ((!this.loaderThread.isBosDone()) || (this.pageCache.size() < 10))
/*     */       try {
/*  64 */         Thread.sleep(200L);
/*     */       }
/*     */       catch (InterruptedException ex)
/*     */       {
/*     */       }
/*     */   }
/*     */ 
/*     */   public Collection getLogicalStreams()
/*     */   {
/*  74 */     return this.logicalStreams.values();
/*     */   }
/*     */ 
/*     */   public boolean isOpen() {
/*  78 */     return !this.closed;
/*     */   }
/*     */ 
/*     */   public void close() throws IOException {
/*  82 */     this.closed = true;
/*  83 */     this.sourceStream.close();
/*     */   }
/*     */ 
/*     */   public OggPage getOggPage(int index)
/*     */     throws IOException
/*     */   {
/* 103 */     while (this.pageCache.size() == 0)
/*     */       try {
/* 105 */         Thread.sleep(100L);
/*     */       }
/*     */       catch (InterruptedException ex)
/*     */       {
/*     */       }
/* 110 */     synchronized (this.drainLock)
/*     */     {
/* 114 */       return (OggPage)this.pageCache.removeFirst();
/*     */     }
/*     */   }
/*     */ 
/*     */   private LogicalOggStream getLogicalStream(int serialNumber) {
/* 119 */     return (LogicalOggStream)this.logicalStreams.get(new Integer(serialNumber));
/*     */   }
/*     */ 
/*     */   public void setTime(long granulePosition) throws IOException {
/* 123 */     throw new UnsupportedOperationException("Method not supported by this class");
/*     */   }
/*     */ 
/*     */   public boolean isSeekable()
/*     */   {
/* 198 */     return false;
/*     */   }
/*     */ 
/*     */   public class LoaderThread
/*     */     implements Runnable
/*     */   {
/*     */     private InputStream source;
/*     */     private LinkedList pageCache;
/*     */     private RandomAccessFile drain;
/*     */     private byte[] memoryCache;
/* 133 */     private boolean bosDone = false;
/*     */     private int pageNumber;
/*     */ 
/*     */     public LoaderThread(InputStream source, LinkedList pageCache)
/*     */     {
/* 138 */       this.source = source;
/* 139 */       this.pageCache = pageCache;
/*     */     }
/*     */ 
/*     */     public void run() {
/*     */       try {
/* 144 */         boolean eos = false;
/* 145 */         byte[] buffer = new byte[8192];
/* 146 */         while (!eos) {
/* 147 */           OggPage op = OggPage.create(this.source);
/* 148 */           synchronized (UncachedUrlStream.this.drainLock) {
/* 149 */             this.pageCache.add(op);
/*     */           }
/*     */ 
/* 152 */           if (!op.isBos()) {
/* 153 */             this.bosDone = true;
/*     */           }
/* 155 */           if (op.isEos()) {
/* 156 */             eos = true;
/*     */           }
/*     */ 
/* 159 */           LogicalOggStreamImpl los = (LogicalOggStreamImpl)UncachedUrlStream.this.getLogicalStream(op.getStreamSerialNumber());
/* 160 */           if (los == null) {
/* 161 */             los = new LogicalOggStreamImpl(UncachedUrlStream.this, op.getStreamSerialNumber());
/* 162 */             UncachedUrlStream.this.logicalStreams.put(new Integer(op.getStreamSerialNumber()), los);
/* 163 */             los.checkFormat(op);
/*     */           }
/*     */ 
/* 169 */           this.pageNumber += 1;
/*     */ 
/* 171 */           while (this.pageCache.size() > 10)
/*     */             try {
/* 173 */               Thread.sleep(200L);
/*     */             }
/*     */             catch (InterruptedException ex)
/*     */             {
/*     */             }
/*     */         }
/*     */       }
/*     */       catch (EndOfOggStreamException e)
/*     */       {
/*     */       }
/*     */       catch (IOException e) {
/* 184 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */ 
/*     */     public boolean isBosDone() {
/* 189 */       return this.bosDone;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     de.jarnbjo.ogg.UncachedUrlStream
 * JD-Core Version:    0.6.2
 */