/*     */ package de.jarnbjo.vorbis;
/*     */ 
/*     */ import de.jarnbjo.util.io.BitInputStream;
/*     */ import java.io.IOException;
/*     */ import java.util.HashMap;
/*     */ 
/*     */ abstract class Residue
/*     */ {
/*     */   protected int begin;
/*     */   protected int end;
/*     */   protected int partitionSize;
/*     */   protected int classifications;
/*     */   protected int classBook;
/*     */   protected int[] cascade;
/*     */   protected int[][] books;
/*  43 */   protected HashMap looks = new HashMap();
/*     */ 
/*     */   protected Residue() {
/*     */   }
/*     */ 
/*     */   protected Residue(BitInputStream source, SetupHeader header) throws VorbisFormatException, IOException {
/*  49 */     this.begin = source.getInt(24);
/*  50 */     this.end = source.getInt(24);
/*  51 */     this.partitionSize = (source.getInt(24) + 1);
/*  52 */     this.classifications = (source.getInt(6) + 1);
/*  53 */     this.classBook = source.getInt(8);
/*     */ 
/*  55 */     this.cascade = new int[this.classifications];
/*     */ 
/*  57 */     int acc = 0;
/*     */ 
/*  59 */     for (int i = 0; i < this.classifications; i++) {
/*  60 */       int highBits = 0; int lowBits = 0;
/*  61 */       lowBits = source.getInt(3);
/*  62 */       if (source.getBit()) {
/*  63 */         highBits = source.getInt(5);
/*     */       }
/*  65 */       this.cascade[i] = (highBits << 3 | lowBits);
/*  66 */       acc += Util.icount(this.cascade[i]);
/*     */     }
/*     */ 
/*  69 */     this.books = new int[this.classifications][8];
/*     */ 
/*  71 */     for (int i = 0; i < this.classifications; i++)
/*  72 */       for (int j = 0; j < 8; j++)
/*  73 */         if ((this.cascade[i] & 1 << j) != 0) {
/*  74 */           this.books[i][j] = source.getInt(8);
/*  75 */           if (this.books[i][j] > header.getCodeBooks().length)
/*  76 */             throw new VorbisFormatException("Reference to invalid codebook entry in residue header.");
/*     */         }
/*     */   }
/*     */ 
/*     */   protected static Residue createInstance(BitInputStream source, SetupHeader header)
/*     */     throws VorbisFormatException, IOException
/*     */   {
/*  86 */     int type = source.getInt(16);
/*  87 */     switch (type)
/*     */     {
/*     */     case 0:
/*  90 */       return new Residue0(source, header);
/*     */     case 1:
/*  93 */       return new Residue2(source, header);
/*     */     case 2:
/*  96 */       return new Residue2(source, header);
/*     */     }
/*  98 */     throw new VorbisFormatException("Residue type " + type + " is not supported.");
/*     */   }
/*     */ 
/*     */   protected abstract int getType();
/*     */ 
/*     */   protected abstract void decodeResidue(VorbisStream paramVorbisStream, BitInputStream paramBitInputStream, Mode paramMode, int paramInt, boolean[] paramArrayOfBoolean, float[][] paramArrayOfFloat) throws VorbisFormatException, IOException;
/*     */ 
/*     */   protected int getBegin()
/*     */   {
/* 107 */     return this.begin;
/*     */   }
/*     */ 
/*     */   protected int getEnd() {
/* 111 */     return this.end;
/*     */   }
/*     */ 
/*     */   protected int getPartitionSize() {
/* 115 */     return this.partitionSize;
/*     */   }
/*     */ 
/*     */   protected int getClassifications() {
/* 119 */     return this.classifications;
/*     */   }
/*     */ 
/*     */   protected int getClassBook() {
/* 123 */     return this.classBook;
/*     */   }
/*     */ 
/*     */   protected int[] getCascade() {
/* 127 */     return this.cascade;
/*     */   }
/*     */ 
/*     */   protected int[][] getBooks() {
/* 131 */     return this.books;
/*     */   }
/*     */ 
/*     */   protected final void fill(Residue clone) {
/* 135 */     clone.begin = this.begin;
/* 136 */     clone.books = this.books;
/* 137 */     clone.cascade = this.cascade;
/* 138 */     clone.classBook = this.classBook;
/* 139 */     clone.classifications = this.classifications;
/* 140 */     clone.end = this.end;
/* 141 */     clone.partitionSize = this.partitionSize;
/*     */   }
/*     */ 
/*     */   protected Look getLook(VorbisStream source, Mode key)
/*     */   {
/* 146 */     Look look = (Look)this.looks.get(key);
/* 147 */     if (look == null) {
/* 148 */       look = new Look(source, key);
/* 149 */       this.looks.put(key, look);
/*     */     }
/* 151 */     return look; } 
/*     */   class Look { int map;
/*     */     int parts;
/*     */     int stages;
/*     */     CodeBook[] fullbooks;
/*     */     CodeBook phrasebook;
/*     */     int[][] partbooks;
/*     */     int partvals;
/*     */     int[][] decodemap;
/*     */     int postbits;
/*     */     int phrasebits;
/*     */     int frames;
/*     */ 
/* 169 */     protected Look(VorbisStream source, Mode mode) { int dim = 0; int acc = 0; int maxstage = 0;
/*     */ 
/* 171 */       this.map = mode.getMapping();
/* 172 */       this.parts = Residue.this.getClassifications();
/* 173 */       this.fullbooks = source.getSetupHeader().getCodeBooks();
/* 174 */       this.phrasebook = this.fullbooks[Residue.this.getClassBook()];
/* 175 */       dim = this.phrasebook.getDimensions();
/*     */ 
/* 177 */       this.partbooks = new int[this.parts][];
/*     */ 
/* 179 */       for (int j = 0; j < this.parts; j++) {
/* 180 */         int stages = Util.ilog(Residue.this.getCascade()[j]);
/* 181 */         if (stages != 0) {
/* 182 */           if (stages > maxstage) {
/* 183 */             maxstage = stages;
/*     */           }
/* 185 */           this.partbooks[j] = new int[stages];
/* 186 */           for (int k = 0; k < stages; k++) {
/* 187 */             if ((Residue.this.getCascade()[j] & 1 << k) != 0) {
/* 188 */               this.partbooks[j][k] = Residue.this.getBooks()[j][k];
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */ 
/* 194 */       this.partvals = ((int)Math.rint(Math.pow(this.parts, dim)));
/* 195 */       this.stages = maxstage;
/*     */ 
/* 197 */       this.decodemap = new int[this.partvals][];
/*     */ 
/* 199 */       for (int j = 0; j < this.partvals; j++) {
/* 200 */         int val = j;
/* 201 */         int mult = this.partvals / this.parts;
/* 202 */         this.decodemap[j] = new int[dim];
/*     */ 
/* 204 */         for (int k = 0; k < dim; k++) {
/* 205 */           int deco = val / mult;
/* 206 */           val -= deco * mult;
/* 207 */           mult /= this.parts;
/* 208 */           this.decodemap[j][k] = deco;
/*     */         }
/*     */       } }
/*     */ 
/*     */     protected int[][] getDecodeMap()
/*     */     {
/* 214 */       return this.decodemap;
/*     */     }
/*     */ 
/*     */     protected int getFrames() {
/* 218 */       return this.frames;
/*     */     }
/*     */ 
/*     */     protected int getMap() {
/* 222 */       return this.map;
/*     */     }
/*     */ 
/*     */     protected int[][] getPartBooks() {
/* 226 */       return this.partbooks;
/*     */     }
/*     */ 
/*     */     protected int getParts() {
/* 230 */       return this.parts;
/*     */     }
/*     */ 
/*     */     protected int getPartVals() {
/* 234 */       return this.partvals;
/*     */     }
/*     */ 
/*     */     protected int getPhraseBits() {
/* 238 */       return this.phrasebits;
/*     */     }
/*     */ 
/*     */     protected CodeBook getPhraseBook() {
/* 242 */       return this.phrasebook;
/*     */     }
/*     */ 
/*     */     protected int getPostBits() {
/* 246 */       return this.postbits;
/*     */     }
/*     */ 
/*     */     protected int getStages() {
/* 250 */       return this.stages;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     de.jarnbjo.vorbis.Residue
 * JD-Core Version:    0.6.2
 */