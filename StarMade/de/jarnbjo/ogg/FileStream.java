/*     */ package de.jarnbjo.ogg;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.RandomAccessFile;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class FileStream
/*     */   implements PhysicalOggStream
/*     */ {
/*  36 */   private boolean closed = false;
/*     */   private RandomAccessFile source;
/*     */   private long[] pageOffsets;
/*  39 */   private long numberOfSamples = -1L;
/*     */ 
/*  41 */   private HashMap logicalStreams = new HashMap();
/*     */ 
/*     */   public FileStream(RandomAccessFile source)
/*     */     throws OggFormatException, IOException
/*     */   {
/*  54 */     this.source = source;
/*     */ 
/*  56 */     ArrayList po = new ArrayList();
/*  57 */     int pageNumber = 0;
/*     */     try {
/*     */       while (true) {
/*  60 */         po.add(new Long(this.source.getFilePointer()));
/*     */ 
/*  63 */         OggPage op = getNextPage(pageNumber > 0);
/*  64 */         if (op == null)
/*     */         {
/*     */           break;
/*     */         }
/*  68 */         LogicalOggStreamImpl los = (LogicalOggStreamImpl)getLogicalStream(op.getStreamSerialNumber());
/*  69 */         if (los == null) {
/*  70 */           los = new LogicalOggStreamImpl(this, op.getStreamSerialNumber());
/*  71 */           this.logicalStreams.put(new Integer(op.getStreamSerialNumber()), los);
/*     */         }
/*     */ 
/*  74 */         if (pageNumber == 0) {
/*  75 */           los.checkFormat(op);
/*     */         }
/*     */ 
/*  78 */         los.addPageNumberMapping(pageNumber);
/*  79 */         los.addGranulePosition(op.getAbsoluteGranulePosition());
/*     */ 
/*  81 */         if (pageNumber > 0) {
/*  82 */           this.source.seek(this.source.getFilePointer() + op.getTotalLength());
/*     */         }
/*     */ 
/*  85 */         pageNumber++;
/*     */       }
/*     */     }
/*     */     catch (EndOfOggStreamException e)
/*     */     {
/*     */     }
/*     */     catch (IOException e) {
/*  92 */       throw e;
/*     */     }
/*     */ 
/*  95 */     this.source.seek(0L);
/*  96 */     this.pageOffsets = new long[po.size()];
/*  97 */     int i = 0;
/*  98 */     Iterator iter = po.iterator();
/*  99 */     while (iter.hasNext())
/* 100 */       this.pageOffsets[(i++)] = ((Long)iter.next()).longValue();
/*     */   }
/*     */ 
/*     */   public Collection getLogicalStreams()
/*     */   {
/* 105 */     return this.logicalStreams.values();
/*     */   }
/*     */ 
/*     */   public boolean isOpen() {
/* 109 */     return !this.closed;
/*     */   }
/*     */ 
/*     */   public void close() throws IOException {
/* 113 */     this.closed = true;
/* 114 */     this.source.close();
/*     */   }
/*     */ 
/*     */   private OggPage getNextPage() throws EndOfOggStreamException, IOException, OggFormatException {
/* 118 */     return getNextPage(false);
/*     */   }
/*     */ 
/*     */   private OggPage getNextPage(boolean skipData) throws EndOfOggStreamException, IOException, OggFormatException {
/* 122 */     return OggPage.create(this.source, skipData);
/*     */   }
/*     */ 
/*     */   public OggPage getOggPage(int index) throws IOException {
/* 126 */     this.source.seek(this.pageOffsets[index]);
/* 127 */     return OggPage.create(this.source);
/*     */   }
/*     */ 
/*     */   private LogicalOggStream getLogicalStream(int serialNumber) {
/* 131 */     return (LogicalOggStream)this.logicalStreams.get(new Integer(serialNumber));
/*     */   }
/*     */ 
/*     */   public void setTime(long granulePosition) throws IOException {
/* 135 */     for (Iterator iter = this.logicalStreams.values().iterator(); iter.hasNext(); ) {
/* 136 */       LogicalOggStream los = (LogicalOggStream)iter.next();
/* 137 */       los.setTime(granulePosition);
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean isSeekable()
/*     */   {
/* 146 */     return true;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     de.jarnbjo.ogg.FileStream
 * JD-Core Version:    0.6.2
 */