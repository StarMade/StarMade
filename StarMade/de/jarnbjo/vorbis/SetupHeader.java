/*     */ package de.jarnbjo.vorbis;
/*     */ 
/*     */ import de.jarnbjo.util.io.BitInputStream;
/*     */ import java.io.IOException;
/*     */ 
/*     */ class SetupHeader
/*     */ {
/*     */   private static final long HEADER = 126896460427126L;
/*     */   private CodeBook[] codeBooks;
/*     */   private Floor[] floors;
/*     */   private Residue[] residues;
/*     */   private Mapping[] mappings;
/*     */   private Mode[] modes;
/*     */ 
/*     */   public SetupHeader(VorbisStream vorbis, BitInputStream source)
/*     */     throws VorbisFormatException, IOException
/*     */   {
/*  42 */     if (source.getLong(48) != 126896460427126L) {
/*  43 */       throw new VorbisFormatException("The setup header has an illegal leading.");
/*     */     }
/*     */ 
/*  48 */     int codeBookCount = source.getInt(8) + 1;
/*  49 */     this.codeBooks = new CodeBook[codeBookCount];
/*     */ 
/*  51 */     for (int i = 0; i < this.codeBooks.length; i++) {
/*  52 */       this.codeBooks[i] = new CodeBook(source);
/*     */     }
/*     */ 
/*  58 */     int timeCount = source.getInt(6) + 1;
/*  59 */     for (int i = 0; i < timeCount; i++) {
/*  60 */       if (source.getInt(16) != 0) {
/*  61 */         throw new VorbisFormatException("Time domain transformation != 0");
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/*  67 */     int floorCount = source.getInt(6) + 1;
/*  68 */     this.floors = new Floor[floorCount];
/*     */ 
/*  70 */     for (int i = 0; i < floorCount; i++) {
/*  71 */       this.floors[i] = Floor.createInstance(source, this);
/*     */     }
/*     */ 
/*  76 */     int residueCount = source.getInt(6) + 1;
/*  77 */     this.residues = new Residue[residueCount];
/*     */ 
/*  79 */     for (int i = 0; i < residueCount; i++) {
/*  80 */       this.residues[i] = Residue.createInstance(source, this);
/*     */     }
/*     */ 
/*  85 */     int mappingCount = source.getInt(6) + 1;
/*  86 */     this.mappings = new Mapping[mappingCount];
/*     */ 
/*  88 */     for (int i = 0; i < mappingCount; i++) {
/*  89 */       this.mappings[i] = Mapping.createInstance(vorbis, source, this);
/*     */     }
/*     */ 
/*  94 */     int modeCount = source.getInt(6) + 1;
/*  95 */     this.modes = new Mode[modeCount];
/*     */ 
/*  97 */     for (int i = 0; i < modeCount; i++) {
/*  98 */       this.modes[i] = new Mode(source, this);
/*     */     }
/*     */ 
/* 101 */     if (!source.getBit())
/* 102 */       throw new VorbisFormatException("The setup header framing bit is incorrect.");
/*     */   }
/*     */ 
/*     */   public CodeBook[] getCodeBooks()
/*     */   {
/* 107 */     return this.codeBooks;
/*     */   }
/*     */ 
/*     */   public Floor[] getFloors() {
/* 111 */     return this.floors;
/*     */   }
/*     */ 
/*     */   public Residue[] getResidues() {
/* 115 */     return this.residues;
/*     */   }
/*     */ 
/*     */   public Mapping[] getMappings() {
/* 119 */     return this.mappings;
/*     */   }
/*     */ 
/*     */   public Mode[] getModes() {
/* 123 */     return this.modes;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     de.jarnbjo.vorbis.SetupHeader
 * JD-Core Version:    0.6.2
 */