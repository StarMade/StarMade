/*     */ package de.jarnbjo.vorbis;
/*     */ 
/*     */ import de.jarnbjo.util.io.BitInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ 
/*     */ class Mapping0 extends Mapping
/*     */ {
/*     */   private int[] magnitudes;
/*     */   private int[] angles;
/*     */   private int[] mux;
/*     */   private int[] submapFloors;
/*     */   private int[] submapResidues;
/*     */ 
/*     */   protected Mapping0(VorbisStream vorbis, BitInputStream source, SetupHeader header)
/*     */     throws VorbisFormatException, IOException
/*     */   {
/*  36 */     int submaps = 1;
/*     */ 
/*  38 */     if (source.getBit()) {
/*  39 */       submaps = source.getInt(4) + 1;
/*     */     }
/*     */ 
/*  44 */     int channels = vorbis.getIdentificationHeader().getChannels();
/*  45 */     int ilogChannels = Util.ilog(channels - 1);
/*     */ 
/*  49 */     if (source.getBit()) {
/*  50 */       int couplingSteps = source.getInt(8) + 1;
/*  51 */       this.magnitudes = new int[couplingSteps];
/*  52 */       this.angles = new int[couplingSteps];
/*     */ 
/*  54 */       for (int i = 0; i < couplingSteps; i++) {
/*  55 */         this.magnitudes[i] = source.getInt(ilogChannels);
/*  56 */         this.angles[i] = source.getInt(ilogChannels);
/*  57 */         if ((this.magnitudes[i] == this.angles[i]) || (this.magnitudes[i] >= channels) || (this.angles[i] >= channels)) {
/*  58 */           System.err.println(this.magnitudes[i]);
/*  59 */           System.err.println(this.angles[i]);
/*  60 */           throw new VorbisFormatException("The channel magnitude and/or angle mismatch.");
/*     */         }
/*     */       }
/*     */     }
/*     */     else {
/*  65 */       this.magnitudes = new int[0];
/*  66 */       this.angles = new int[0];
/*     */     }
/*     */ 
/*  69 */     if (source.getInt(2) != 0) {
/*  70 */       throw new VorbisFormatException("A reserved mapping field has an invalid value.");
/*     */     }
/*     */ 
/*  73 */     this.mux = new int[channels];
/*  74 */     if (submaps > 1) {
/*  75 */       for (int i = 0; i < channels; i++) {
/*  76 */         this.mux[i] = source.getInt(4);
/*  77 */         if (this.mux[i] > submaps) {
/*  78 */           throw new VorbisFormatException("A mapping mux value is higher than the number of submaps");
/*     */         }
/*     */       }
/*     */     }
/*     */     else {
/*  83 */       for (int i = 0; i < channels; i++) {
/*  84 */         this.mux[i] = 0;
/*     */       }
/*     */     }
/*     */ 
/*  88 */     this.submapFloors = new int[submaps];
/*  89 */     this.submapResidues = new int[submaps];
/*     */ 
/*  91 */     int floorCount = header.getFloors().length;
/*  92 */     int residueCount = header.getResidues().length;
/*     */ 
/*  94 */     for (int i = 0; i < submaps; i++) {
/*  95 */       source.getInt(8);
/*  96 */       this.submapFloors[i] = source.getInt(8);
/*  97 */       this.submapResidues[i] = source.getInt(8);
/*     */ 
/*  99 */       if (this.submapFloors[i] > floorCount) {
/* 100 */         throw new VorbisFormatException("A mapping floor value is higher than the number of floors.");
/*     */       }
/*     */ 
/* 103 */       if (this.submapResidues[i] > residueCount)
/* 104 */         throw new VorbisFormatException("A mapping residue value is higher than the number of residues.");
/*     */     }
/*     */   }
/*     */ 
/*     */   protected int getType()
/*     */   {
/* 110 */     return 0;
/*     */   }
/*     */ 
/*     */   protected int[] getAngles() {
/* 114 */     return this.angles;
/*     */   }
/*     */ 
/*     */   protected int[] getMagnitudes() {
/* 118 */     return this.magnitudes;
/*     */   }
/*     */ 
/*     */   protected int[] getMux() {
/* 122 */     return this.mux;
/*     */   }
/*     */ 
/*     */   protected int[] getSubmapFloors() {
/* 126 */     return this.submapFloors;
/*     */   }
/*     */ 
/*     */   protected int[] getSubmapResidues() {
/* 130 */     return this.submapResidues;
/*     */   }
/*     */ 
/*     */   protected int getCouplingSteps() {
/* 134 */     return this.angles.length;
/*     */   }
/*     */ 
/*     */   protected int getSubmaps() {
/* 138 */     return this.submapFloors.length;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     de.jarnbjo.vorbis.Mapping0
 * JD-Core Version:    0.6.2
 */