/*     */ package de.jarnbjo.vorbis;
/*     */ 
/*     */ import de.jarnbjo.util.io.BitInputStream;
/*     */ import de.jarnbjo.util.io.HuffmanNode;
/*     */ import java.io.IOException;
/*     */ import java.util.Arrays;
/*     */ 
/*     */ class CodeBook
/*     */ {
/*     */   private HuffmanNode huffmanRoot;
/*     */   private int dimensions;
/*     */   private int entries;
/*     */   private int[] entryLengths;
/*     */   private float[][] valueVector;
/* 153 */   private static long totalTime = 0L;
/*     */ 
/*     */   protected CodeBook(BitInputStream source)
/*     */     throws VorbisFormatException, IOException
/*     */   {
/*  47 */     if (source.getInt(24) != 5653314) {
/*  48 */       throw new VorbisFormatException("The code book sync pattern is not correct.");
/*     */     }
/*     */ 
/*  51 */     this.dimensions = source.getInt(16);
/*  52 */     this.entries = source.getInt(24);
/*     */ 
/*  54 */     this.entryLengths = new int[this.entries];
/*     */ 
/*  56 */     boolean ordered = source.getBit();
/*     */     int cl;
/*     */     int i;
/*  58 */     if (ordered) {
/*  59 */       cl = source.getInt(5) + 1;
/*  60 */       for (i = 0; i < this.entryLengths.length; ) {
/*  61 */         int num = source.getInt(Util.ilog(this.entryLengths.length - i));
/*  62 */         if (i + num > this.entryLengths.length) {
/*  63 */           throw new VorbisFormatException("The codebook entry length list is longer than the actual number of entry lengths.");
/*     */         }
/*  65 */         Arrays.fill(this.entryLengths, i, i + num, cl);
/*  66 */         cl++;
/*  67 */         i += num;
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/*  72 */       boolean sparse = source.getBit();
/*     */ 
/*  74 */       if (sparse) {
/*  75 */         for (int i = 0; i < this.entryLengths.length; i++) {
/*  76 */           if (source.getBit()) {
/*  77 */             this.entryLengths[i] = (source.getInt(5) + 1);
/*     */           }
/*     */           else {
/*  80 */             this.entryLengths[i] = -1;
/*     */           }
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/*  86 */         for (int i = 0; i < this.entryLengths.length; i++) {
/*  87 */           this.entryLengths[i] = (source.getInt(5) + 1);
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/*  92 */     if (!createHuffmanTree(this.entryLengths)) {
/*  93 */       throw new VorbisFormatException("An exception was thrown when building the codebook Huffman tree.");
/*     */     }
/*     */ 
/*  96 */     int codeBookLookupType = source.getInt(4);
/*     */ 
/*  98 */     switch (codeBookLookupType)
/*     */     {
/*     */     case 0:
/* 101 */       break;
/*     */     case 1:
/*     */     case 2:
/* 104 */       float codeBookMinimumValue = Util.float32unpack(source.getInt(32));
/* 105 */       float codeBookDeltaValue = Util.float32unpack(source.getInt(32));
/*     */ 
/* 107 */       int codeBookValueBits = source.getInt(4) + 1;
/* 108 */       boolean codeBookSequenceP = source.getBit();
/*     */ 
/* 110 */       int codeBookLookupValues = 0;
/*     */ 
/* 112 */       if (codeBookLookupType == 1) {
/* 113 */         codeBookLookupValues = Util.lookup1Values(this.entries, this.dimensions);
/*     */       }
/*     */       else {
/* 116 */         codeBookLookupValues = this.entries * this.dimensions;
/*     */       }
/*     */ 
/* 119 */       int[] codeBookMultiplicands = new int[codeBookLookupValues];
/*     */ 
/* 121 */       for (int i = 0; i < codeBookMultiplicands.length; i++) {
/* 122 */         codeBookMultiplicands[i] = source.getInt(codeBookValueBits);
/*     */       }
/*     */ 
/* 125 */       this.valueVector = new float[this.entries][this.dimensions];
/*     */ 
/* 127 */       if (codeBookLookupType == 1) {
/* 128 */         for (int i = 0; i < this.entries; i++) {
/* 129 */           float last = 0.0F;
/* 130 */           int indexDivisor = 1;
/* 131 */           for (int j = 0; j < this.dimensions; j++) {
/* 132 */             int multiplicandOffset = i / indexDivisor % codeBookLookupValues;
/*     */ 
/* 134 */             this.valueVector[i][j] = (codeBookMultiplicands[multiplicandOffset] * codeBookDeltaValue + codeBookMinimumValue + last);
/*     */ 
/* 136 */             if (codeBookSequenceP) {
/* 137 */               last = this.valueVector[i][j];
/*     */             }
/* 139 */             indexDivisor *= codeBookLookupValues;
/*     */           }
/*     */         }
/*     */       }
/*     */       else {
/* 144 */         throw new UnsupportedOperationException();
/*     */       }
/*     */ 
/*     */       break;
/*     */     default:
/* 149 */       throw new VorbisFormatException("Unsupported codebook lookup type: " + codeBookLookupType);
/*     */     }
/*     */   }
/*     */ 
/*     */   private boolean createHuffmanTree(int[] entryLengths)
/*     */   {
/* 156 */     this.huffmanRoot = new HuffmanNode();
/* 157 */     for (int i = 0; i < entryLengths.length; i++) {
/* 158 */       int el = entryLengths[i];
/* 159 */       if ((el > 0) && 
/* 160 */         (!this.huffmanRoot.setNewValue(el, i))) {
/* 161 */         return false;
/*     */       }
/*     */     }
/*     */ 
/* 165 */     return true;
/*     */   }
/*     */ 
/*     */   protected int getDimensions() {
/* 169 */     return this.dimensions;
/*     */   }
/*     */ 
/*     */   protected int getEntries() {
/* 173 */     return this.entries;
/*     */   }
/*     */ 
/*     */   protected HuffmanNode getHuffmanRoot() {
/* 177 */     return this.huffmanRoot;
/*     */   }
/*     */ 
/*     */   protected int readInt(BitInputStream source)
/*     */     throws IOException
/*     */   {
/* 185 */     return source.getInt(this.huffmanRoot);
/*     */   }
/*     */ 
/*     */   protected void readVvAdd(float[][] a, BitInputStream source, int offset, int length)
/*     */     throws VorbisFormatException, IOException
/*     */   {
/* 197 */     int chptr = 0;
/* 198 */     int ch = a.length;
/*     */ 
/* 200 */     if (ch == 0) {
/* 201 */       return;
/*     */     }
/*     */ 
/* 204 */     int lim = (offset + length) / ch;
/*     */ 
/* 206 */     for (int i = offset / ch; i < lim; ) {
/* 207 */       float[] ve = this.valueVector[source.getInt(this.huffmanRoot)];
/* 208 */       for (int j = 0; j < this.dimensions; j++) {
/* 209 */         a[(chptr++)][i] += ve[j];
/* 210 */         if (chptr == ch) {
/* 211 */           chptr = 0;
/* 212 */           i++;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     de.jarnbjo.vorbis.CodeBook
 * JD-Core Version:    0.6.2
 */