/*     */ package de.jarnbjo.ogg;
/*     */ 
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class LogicalOggStreamImpl
/*     */   implements LogicalOggStream
/*     */ {
/*     */   private PhysicalOggStream source;
/*     */   private int serialNumber;
/*  39 */   private ArrayList pageNumberMapping = new ArrayList();
/*  40 */   private ArrayList granulePositions = new ArrayList();
/*     */ 
/*  42 */   private int pageIndex = 0;
/*     */   private OggPage currentPage;
/*     */   private int currentSegmentIndex;
/*  46 */   private boolean open = true;
/*     */ 
/*  48 */   private String format = "application/octet-stream";
/*     */ 
/*     */   public LogicalOggStreamImpl(PhysicalOggStream source, int serialNumber) {
/*  51 */     this.source = source;
/*  52 */     this.serialNumber = serialNumber;
/*     */   }
/*     */ 
/*     */   public void addPageNumberMapping(int physicalPageNumber) {
/*  56 */     this.pageNumberMapping.add(new Integer(physicalPageNumber));
/*     */   }
/*     */ 
/*     */   public void addGranulePosition(long granulePosition) {
/*  60 */     this.granulePositions.add(new Long(granulePosition));
/*     */   }
/*     */ 
/*     */   public synchronized void reset() throws OggFormatException, IOException {
/*  64 */     this.currentPage = null;
/*  65 */     this.currentSegmentIndex = 0;
/*  66 */     this.pageIndex = 0;
/*     */   }
/*     */ 
/*     */   public synchronized OggPage getNextOggPage() throws EndOfOggStreamException, OggFormatException, IOException {
/*  70 */     if (this.source.isSeekable()) {
/*  71 */       this.currentPage = this.source.getOggPage(((Integer)this.pageNumberMapping.get(this.pageIndex++)).intValue());
/*     */     }
/*     */     else {
/*  74 */       this.currentPage = this.source.getOggPage(-1);
/*     */     }
/*  76 */     return this.currentPage;
/*     */   }
/*     */ 
/*     */   public synchronized byte[] getNextOggPacket() throws EndOfOggStreamException, OggFormatException, IOException {
/*  80 */     ByteArrayOutputStream res = new ByteArrayOutputStream();
/*  81 */     int segmentLength = 0;
/*     */ 
/*  83 */     if (this.currentPage == null) {
/*  84 */       this.currentPage = getNextOggPage();
/*     */     }
/*     */     do
/*     */     {
/*  88 */       if (this.currentSegmentIndex >= this.currentPage.getSegmentOffsets().length) {
/*  89 */         this.currentSegmentIndex = 0;
/*     */ 
/*  91 */         if (!this.currentPage.isEos()) {
/*  92 */           if ((this.source.isSeekable()) && (this.pageNumberMapping.size() <= this.pageIndex)) {
/*  93 */             while (this.pageNumberMapping.size() <= this.pageIndex + 10)
/*     */               try {
/*  95 */                 Thread.sleep(1000L);
/*     */               }
/*     */               catch (InterruptedException ex)
/*     */               {
/*     */               }
/*     */           }
/* 101 */           this.currentPage = getNextOggPage();
/*     */ 
/* 103 */           if ((res.size() == 0) && (this.currentPage.isContinued())) {
/* 104 */             boolean done = false;
/* 105 */             while (!done) {
/* 106 */               if (this.currentPage.getSegmentLengths()[(this.currentSegmentIndex++)] != 255) {
/* 107 */                 done = true;
/*     */               }
/* 109 */               if (this.currentSegmentIndex > this.currentPage.getSegmentTable().length)
/* 110 */                 this.currentPage = this.source.getOggPage(((Integer)this.pageNumberMapping.get(this.pageIndex++)).intValue());
/*     */             }
/*     */           }
/*     */         }
/*     */         else
/*     */         {
/* 116 */           throw new EndOfOggStreamException();
/*     */         }
/*     */       }
/* 119 */       segmentLength = this.currentPage.getSegmentLengths()[this.currentSegmentIndex];
/* 120 */       res.write(this.currentPage.getData(), this.currentPage.getSegmentOffsets()[this.currentSegmentIndex], segmentLength);
/* 121 */       this.currentSegmentIndex += 1;
/* 122 */     }while (segmentLength == 255);
/*     */ 
/* 124 */     return res.toByteArray();
/*     */   }
/*     */ 
/*     */   public boolean isOpen() {
/* 128 */     return this.open;
/*     */   }
/*     */ 
/*     */   public void close() throws IOException {
/* 132 */     this.open = false;
/*     */   }
/*     */ 
/*     */   public long getMaximumGranulePosition() {
/* 136 */     Long mgp = (Long)this.granulePositions.get(this.granulePositions.size() - 1);
/* 137 */     return mgp.longValue();
/*     */   }
/*     */ 
/*     */   public synchronized long getTime() {
/* 141 */     return this.currentPage != null ? this.currentPage.getAbsoluteGranulePosition() : -1L;
/*     */   }
/*     */ 
/*     */   public synchronized void setTime(long granulePosition) throws IOException
/*     */   {
/* 146 */     int page = 0;
/* 147 */     for (page = 0; page < this.granulePositions.size(); page++) {
/* 148 */       Long gp = (Long)this.granulePositions.get(page);
/* 149 */       if (gp.longValue() > granulePosition)
/*     */       {
/*     */         break;
/*     */       }
/*     */     }
/* 154 */     this.pageIndex = page;
/* 155 */     this.currentPage = this.source.getOggPage(((Integer)this.pageNumberMapping.get(this.pageIndex++)).intValue());
/* 156 */     this.currentSegmentIndex = 0;
/* 157 */     int segmentLength = 0;
/*     */     do {
/* 159 */       if (this.currentSegmentIndex >= this.currentPage.getSegmentOffsets().length) {
/* 160 */         this.currentSegmentIndex = 0;
/* 161 */         if (this.pageIndex >= this.pageNumberMapping.size()) {
/* 162 */           throw new EndOfOggStreamException();
/*     */         }
/* 164 */         this.currentPage = this.source.getOggPage(((Integer)this.pageNumberMapping.get(this.pageIndex++)).intValue());
/*     */       }
/* 166 */       segmentLength = this.currentPage.getSegmentLengths()[this.currentSegmentIndex];
/* 167 */       this.currentSegmentIndex += 1;
/* 168 */     }while (segmentLength == 255);
/*     */   }
/*     */ 
/*     */   public void checkFormat(OggPage page) {
/* 172 */     byte[] data = page.getData();
/*     */ 
/* 174 */     if ((data.length >= 7) && (data[1] == 118) && (data[2] == 111) && (data[3] == 114) && (data[4] == 98) && (data[5] == 105) && (data[6] == 115))
/*     */     {
/* 182 */       this.format = "audio/x-vorbis";
/*     */     }
/* 184 */     else if ((data.length >= 7) && (data[1] == 116) && (data[2] == 104) && (data[3] == 101) && (data[4] == 111) && (data[5] == 114) && (data[6] == 97))
/*     */     {
/* 192 */       this.format = "video/x-theora";
/*     */     }
/* 194 */     else if ((data.length == 4) && (data[0] == 102) && (data[1] == 76) && (data[2] == 97) && (data[3] == 67))
/*     */     {
/* 200 */       this.format = "audio/x-flac";
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getFormat() {
/* 205 */     return this.format;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     de.jarnbjo.ogg.LogicalOggStreamImpl
 * JD-Core Version:    0.6.2
 */