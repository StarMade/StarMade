/*     */ package de.jarnbjo.vorbis;
/*     */ 
/*     */ import de.jarnbjo.util.io.BitInputStream;
/*     */ import java.io.IOException;
/*     */ 
/*     */ class Residue2 extends Residue
/*     */ {
/*     */   private double[][] decodedVectors;
/*     */ 
/*     */   private Residue2()
/*     */   {
/*     */   }
/*     */ 
/*     */   protected Residue2(BitInputStream source, SetupHeader header)
/*     */     throws VorbisFormatException, IOException
/*     */   {
/*  39 */     super(source, header);
/*     */   }
/*     */ 
/*     */   protected int getType() {
/*  43 */     return 2;
/*     */   }
/*     */ 
/*     */   protected void decodeResidue(VorbisStream vorbis, BitInputStream source, Mode mode, int ch, boolean[] doNotDecodeFlags, float[][] vectors) throws VorbisFormatException, IOException
/*     */   {
/*  48 */     Residue.Look look = getLook(vorbis, mode);
/*     */ 
/*  50 */     CodeBook codeBook = vorbis.getSetupHeader().getCodeBooks()[getClassBook()];
/*     */ 
/*  52 */     int classvalsPerCodeword = codeBook.getDimensions();
/*  53 */     int nToRead = getEnd() - getBegin();
/*  54 */     int partitionsToRead = nToRead / getPartitionSize();
/*     */ 
/*  56 */     int samplesPerPartition = getPartitionSize();
/*  57 */     int partitionsPerWord = look.getPhraseBook().getDimensions();
/*     */ 
/*  59 */     int partWords = (partitionsToRead + partitionsPerWord - 1) / partitionsPerWord;
/*     */ 
/*  61 */     int realCh = 0;
/*  62 */     for (int i = 0; i < doNotDecodeFlags.length; i++) {
/*  63 */       if (doNotDecodeFlags[i] == 0) {
/*  64 */         realCh++;
/*     */       }
/*     */     }
/*     */ 
/*  68 */     float[][] realVectors = new float[realCh][];
/*     */ 
/*  70 */     realCh = 0;
/*  71 */     for (int i = 0; i < doNotDecodeFlags.length; i++) {
/*  72 */       if (doNotDecodeFlags[i] == 0) {
/*  73 */         realVectors[(realCh++)] = vectors[i];
/*     */       }
/*     */     }
/*     */ 
/*  77 */     int[][] partword = new int[partWords][];
/*  78 */     for (int s = 0; s < look.getStages(); s++) {
/*  79 */       int i = 0; for (int l = 0; i < partitionsToRead; l++) {
/*  80 */         if (s == 0)
/*     */         {
/*  82 */           int temp = source.getInt(look.getPhraseBook().getHuffmanRoot());
/*  83 */           if (temp == -1) {
/*  84 */             throw new VorbisFormatException("");
/*     */           }
/*  86 */           partword[l] = look.getDecodeMap()[temp];
/*  87 */           if (partword[l] == null) {
/*  88 */             throw new VorbisFormatException("");
/*     */           }
/*     */         }
/*     */ 
/*  92 */         for (int k = 0; (k < partitionsPerWord) && (i < partitionsToRead); i++) {
/*  93 */           int offset = this.begin + i * samplesPerPartition;
/*  94 */           if ((this.cascade[partword[l][k]] & 1 << s) != 0) {
/*  95 */             CodeBook stagebook = vorbis.getSetupHeader().getCodeBooks()[look.getPartBooks()[partword[l][k]][s]];
/*  96 */             if (stagebook != null)
/*  97 */               stagebook.readVvAdd(realVectors, source, offset, samplesPerPartition);
/*     */           }
/*  92 */           k++;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 107 */     Residue2 clone = new Residue2();
/* 108 */     fill(clone);
/* 109 */     return clone;
/*     */   }
/*     */ 
/*     */   protected double[][] getDecodedVectors() {
/* 113 */     return this.decodedVectors;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     de.jarnbjo.vorbis.Residue2
 * JD-Core Version:    0.6.2
 */