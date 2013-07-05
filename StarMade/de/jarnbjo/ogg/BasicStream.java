/*     */ package de.jarnbjo.ogg;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedList;
/*     */ 
/*     */ public class BasicStream
/*     */   implements PhysicalOggStream
/*     */ {
/*  37 */   private boolean closed = false;
/*     */   private InputStream sourceStream;
/*  39 */   private Object drainLock = new Object();
/*  40 */   private LinkedList pageCache = new LinkedList();
/*  41 */   private long numberOfSamples = -1L;
/*  42 */   private int position = 0;
/*     */ 
/*  44 */   private HashMap logicalStreams = new HashMap();
/*     */   private OggPage firstPage;
/*  76 */   int pageNumber = 2;
/*     */ 
/*     */   public BasicStream(InputStream sourceStream)
/*     */     throws OggFormatException, IOException
/*     */   {
/*  48 */     this.firstPage = OggPage.create(sourceStream);
/*  49 */     this.position += this.firstPage.getTotalLength();
/*  50 */     LogicalOggStreamImpl los = new LogicalOggStreamImpl(this, this.firstPage.getStreamSerialNumber());
/*  51 */     this.logicalStreams.put(new Integer(this.firstPage.getStreamSerialNumber()), los);
/*  52 */     los.checkFormat(this.firstPage);
/*     */   }
/*     */ 
/*     */   public Collection getLogicalStreams() {
/*  56 */     return this.logicalStreams.values();
/*     */   }
/*     */ 
/*     */   public boolean isOpen() {
/*  60 */     return !this.closed;
/*     */   }
/*     */ 
/*     */   public void close() throws IOException {
/*  64 */     this.closed = true;
/*  65 */     this.sourceStream.close();
/*     */   }
/*     */ 
/*     */   public int getContentLength() {
/*  69 */     return -1;
/*     */   }
/*     */ 
/*     */   public int getPosition() {
/*  73 */     return this.position;
/*     */   }
/*     */ 
/*     */   public OggPage getOggPage(int index)
/*     */     throws IOException
/*     */   {
/*  79 */     if (this.firstPage != null) {
/*  80 */       OggPage tmp = this.firstPage;
/*  81 */       this.firstPage = null;
/*  82 */       return tmp;
/*     */     }
/*     */ 
/*  85 */     OggPage page = OggPage.create(this.sourceStream);
/*  86 */     this.position += page.getTotalLength();
/*  87 */     return page;
/*     */   }
/*     */ 
/*     */   private LogicalOggStream getLogicalStream(int serialNumber)
/*     */   {
/*  92 */     return (LogicalOggStream)this.logicalStreams.get(new Integer(serialNumber));
/*     */   }
/*     */ 
/*     */   public void setTime(long granulePosition) throws IOException {
/*  96 */     throw new UnsupportedOperationException("Method not supported by this class");
/*     */   }
/*     */ 
/*     */   public boolean isSeekable()
/*     */   {
/* 104 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     de.jarnbjo.ogg.BasicStream
 * JD-Core Version:    0.6.2
 */