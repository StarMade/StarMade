/*     */ package de.jarnbjo.vorbis;
/*     */ 
/*     */ import de.jarnbjo.util.io.BitInputStream;
/*     */ import java.io.IOException;
/*     */ 
/*     */ public class IdentificationHeader
/*     */ {
/*     */   private int version;
/*     */   private int channels;
/*     */   private int sampleRate;
/*     */   private int bitrateMaximum;
/*     */   private int bitrateNominal;
/*     */   private int bitrateMinimum;
/*     */   private int blockSize0;
/*     */   private int blockSize1;
/*     */   private boolean framingFlag;
/*  45 */   private MdctFloat[] mdct = new MdctFloat[2];
/*     */   private static final long HEADER = 126896460427126L;
/*     */ 
/*     */   public IdentificationHeader(BitInputStream source)
/*     */     throws VorbisFormatException, IOException
/*     */   {
/*  55 */     long leading = source.getLong(48);
/*  56 */     if (leading != 126896460427126L) {
/*  57 */       throw new VorbisFormatException("The identification header has an illegal leading.");
/*     */     }
/*  59 */     this.version = source.getInt(32);
/*  60 */     this.channels = source.getInt(8);
/*  61 */     this.sampleRate = source.getInt(32);
/*  62 */     this.bitrateMaximum = source.getInt(32);
/*  63 */     this.bitrateNominal = source.getInt(32);
/*  64 */     this.bitrateMinimum = source.getInt(32);
/*  65 */     int bs = source.getInt(8);
/*  66 */     this.blockSize0 = (1 << (bs & 0xF));
/*  67 */     this.blockSize1 = (1 << (bs >> 4));
/*     */ 
/*  69 */     this.mdct[0] = new MdctFloat(this.blockSize0);
/*  70 */     this.mdct[1] = new MdctFloat(this.blockSize1);
/*     */ 
/*  74 */     this.framingFlag = (source.getInt(8) != 0);
/*     */   }
/*     */ 
/*     */   public int getSampleRate() {
/*  78 */     return this.sampleRate;
/*     */   }
/*     */ 
/*     */   public int getMaximumBitrate() {
/*  82 */     return this.bitrateMaximum;
/*     */   }
/*     */ 
/*     */   public int getNominalBitrate() {
/*  86 */     return this.bitrateNominal;
/*     */   }
/*     */ 
/*     */   public int getMinimumBitrate() {
/*  90 */     return this.bitrateMinimum;
/*     */   }
/*     */ 
/*     */   public int getChannels() {
/*  94 */     return this.channels;
/*     */   }
/*     */ 
/*     */   public int getBlockSize0() {
/*  98 */     return this.blockSize0;
/*     */   }
/*     */ 
/*     */   public int getBlockSize1() {
/* 102 */     return this.blockSize1;
/*     */   }
/*     */ 
/*     */   protected MdctFloat getMdct0() {
/* 106 */     return this.mdct[0];
/*     */   }
/*     */ 
/*     */   protected MdctFloat getMdct1() {
/* 110 */     return this.mdct[1];
/*     */   }
/*     */ 
/*     */   public int getVersion() {
/* 114 */     return this.version;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     de.jarnbjo.vorbis.IdentificationHeader
 * JD-Core Version:    0.6.2
 */