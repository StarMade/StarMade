/*     */ package de.jarnbjo.ogg;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedList;
/*     */ 
/*     */ public class OnDemandUrlStream
/*     */   implements PhysicalOggStream
/*     */ {
/*  42 */   private boolean closed = false;
/*     */   private URLConnection source;
/*     */   private InputStream sourceStream;
/*  45 */   private Object drainLock = new Object();
/*  46 */   private LinkedList pageCache = new LinkedList();
/*  47 */   private long numberOfSamples = -1L;
/*  48 */   private int contentLength = 0;
/*  49 */   private int position = 0;
/*     */ 
/*  51 */   private HashMap logicalStreams = new HashMap();
/*     */   private OggPage firstPage;
/*     */   private static final int PAGECACHE_SIZE = 20;
/*  90 */   int pageNumber = 2;
/*     */ 
/*     */   public OnDemandUrlStream(URL source)
/*     */     throws OggFormatException, IOException
/*     */   {
/*  57 */     this.source = source.openConnection();
/*  58 */     this.sourceStream = this.source.getInputStream();
/*     */ 
/*  60 */     this.contentLength = this.source.getContentLength();
/*     */ 
/*  62 */     this.firstPage = OggPage.create(this.sourceStream);
/*  63 */     this.position += this.firstPage.getTotalLength();
/*  64 */     LogicalOggStreamImpl los = new LogicalOggStreamImpl(this, this.firstPage.getStreamSerialNumber());
/*  65 */     this.logicalStreams.put(new Integer(this.firstPage.getStreamSerialNumber()), los);
/*  66 */     los.checkFormat(this.firstPage);
/*     */   }
/*     */ 
/*     */   public Collection getLogicalStreams() {
/*  70 */     return this.logicalStreams.values();
/*     */   }
/*     */ 
/*     */   public boolean isOpen() {
/*  74 */     return !this.closed;
/*     */   }
/*     */ 
/*     */   public void close() throws IOException {
/*  78 */     this.closed = true;
/*  79 */     this.sourceStream.close();
/*     */   }
/*     */ 
/*     */   public int getContentLength() {
/*  83 */     return this.contentLength;
/*     */   }
/*     */ 
/*     */   public int getPosition() {
/*  87 */     return this.position;
/*     */   }
/*     */ 
/*     */   public OggPage getOggPage(int index)
/*     */     throws IOException
/*     */   {
/*  93 */     if (this.firstPage != null) {
/*  94 */       OggPage tmp = this.firstPage;
/*  95 */       this.firstPage = null;
/*  96 */       return tmp;
/*     */     }
/*     */ 
/*  99 */     OggPage page = OggPage.create(this.sourceStream);
/* 100 */     this.position += page.getTotalLength();
/* 101 */     return page;
/*     */   }
/*     */ 
/*     */   private LogicalOggStream getLogicalStream(int serialNumber)
/*     */   {
/* 106 */     return (LogicalOggStream)this.logicalStreams.get(new Integer(serialNumber));
/*     */   }
/*     */ 
/*     */   public void setTime(long granulePosition) throws IOException {
/* 110 */     throw new UnsupportedOperationException("Method not supported by this class");
/*     */   }
/*     */ 
/*     */   public boolean isSeekable()
/*     */   {
/* 118 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     de.jarnbjo.ogg.OnDemandUrlStream
 * JD-Core Version:    0.6.2
 */