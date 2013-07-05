/*     */ package de.jarnbjo.vorbis;
/*     */ 
/*     */ import de.jarnbjo.util.io.BitInputStream;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ class Floor1 extends Floor
/*     */   implements Cloneable
/*     */ {
/*     */   private int[] partitionClassList;
/*     */   private int maximumClass;
/*     */   private int multiplier;
/*     */   private int rangeBits;
/*     */   private int[] classDimensions;
/*     */   private int[] classSubclasses;
/*     */   private int[] classMasterbooks;
/*     */   private int[][] subclassBooks;
/*     */   private int[] xList;
/*     */   private int[] yList;
/*     */   private int[] lowNeighbours;
/*     */   private int[] highNeighbours;
/*  45 */   private static final int[] RANGES = { 256, 128, 86, 64 };
/*     */ 
/*     */   private Floor1()
/*     */   {
/*     */   }
/*     */ 
/*     */   protected Floor1(BitInputStream source, SetupHeader header) throws VorbisFormatException, IOException {
/*  52 */     this.maximumClass = -1;
/*  53 */     int partitions = source.getInt(5);
/*  54 */     this.partitionClassList = new int[partitions];
/*     */ 
/*  56 */     for (int i = 0; i < this.partitionClassList.length; i++) {
/*  57 */       this.partitionClassList[i] = source.getInt(4);
/*  58 */       if (this.partitionClassList[i] > this.maximumClass) {
/*  59 */         this.maximumClass = this.partitionClassList[i];
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/*  64 */     this.classDimensions = new int[this.maximumClass + 1];
/*  65 */     this.classSubclasses = new int[this.maximumClass + 1];
/*  66 */     this.classMasterbooks = new int[this.maximumClass + 1];
/*  67 */     this.subclassBooks = new int[this.maximumClass + 1][];
/*     */ 
/*  69 */     int xListLength = 2;
/*     */ 
/*  71 */     for (int i = 0; i <= this.maximumClass; i++) {
/*  72 */       this.classDimensions[i] = (source.getInt(3) + 1);
/*  73 */       xListLength += this.classDimensions[i];
/*  74 */       this.classSubclasses[i] = source.getInt(2);
/*     */ 
/*  76 */       if ((this.classDimensions[i] > header.getCodeBooks().length) || (this.classSubclasses[i] > header.getCodeBooks().length))
/*     */       {
/*  78 */         throw new VorbisFormatException("There is a class dimension or class subclasses entry higher than the number of codebooks in the setup header.");
/*     */       }
/*  80 */       if (this.classSubclasses[i] != 0) {
/*  81 */         this.classMasterbooks[i] = source.getInt(8);
/*     */       }
/*  83 */       this.subclassBooks[i] = new int[1 << this.classSubclasses[i]];
/*  84 */       for (int j = 0; j < this.subclassBooks[i].length; j++) {
/*  85 */         this.subclassBooks[i][j] = (source.getInt(8) - 1);
/*     */       }
/*     */     }
/*     */ 
/*  89 */     this.multiplier = (source.getInt(2) + 1);
/*  90 */     this.rangeBits = source.getInt(4);
/*     */ 
/*  97 */     int floorValues = 0;
/*     */ 
/*  99 */     ArrayList alXList = new ArrayList();
/*     */ 
/* 101 */     alXList.add(new Integer(0));
/* 102 */     alXList.add(new Integer(1 << this.rangeBits));
/*     */ 
/* 107 */     for (int i = 0; i < partitions; i++) {
/* 108 */       for (int j = 0; j < this.classDimensions[this.partitionClassList[i]]; j++) {
/* 109 */         alXList.add(new Integer(source.getInt(this.rangeBits)));
/*     */       }
/*     */     }
/*     */ 
/* 113 */     this.xList = new int[alXList.size()];
/* 114 */     this.lowNeighbours = new int[this.xList.length];
/* 115 */     this.highNeighbours = new int[this.xList.length];
/*     */ 
/* 117 */     Iterator iter = alXList.iterator();
/* 118 */     for (int i = 0; i < this.xList.length; i++) {
/* 119 */       this.xList[i] = ((Integer)iter.next()).intValue();
/*     */     }
/*     */ 
/* 122 */     for (int i = 0; i < this.xList.length; i++) {
/* 123 */       this.lowNeighbours[i] = Util.lowNeighbour(this.xList, i);
/* 124 */       this.highNeighbours[i] = Util.highNeighbour(this.xList, i);
/*     */     }
/*     */   }
/*     */ 
/*     */   protected int getType() {
/* 129 */     return 1;
/*     */   }
/*     */ 
/*     */   protected Floor decodeFloor(VorbisStream vorbis, BitInputStream source)
/*     */     throws VorbisFormatException, IOException
/*     */   {
/* 135 */     if (!source.getBit())
/*     */     {
/* 137 */       return null;
/*     */     }
/*     */ 
/* 140 */     Floor1 clone = (Floor1)clone();
/*     */ 
/* 142 */     clone.yList = new int[this.xList.length];
/*     */ 
/* 144 */     int range = RANGES[(this.multiplier - 1)];
/*     */ 
/* 146 */     clone.yList[0] = source.getInt(Util.ilog(range - 1));
/* 147 */     clone.yList[1] = source.getInt(Util.ilog(range - 1));
/*     */ 
/* 149 */     int offset = 2;
/*     */ 
/* 151 */     for (int i = 0; i < this.partitionClassList.length; i++) {
/* 152 */       int cls = this.partitionClassList[i];
/* 153 */       int cdim = this.classDimensions[cls];
/* 154 */       int cbits = this.classSubclasses[cls];
/* 155 */       int csub = (1 << cbits) - 1;
/* 156 */       int cval = 0;
/* 157 */       if (cbits > 0) {
/* 158 */         cval = source.getInt(vorbis.getSetupHeader().getCodeBooks()[this.classMasterbooks[cls]].getHuffmanRoot());
/*     */       }
/*     */ 
/* 163 */       for (int j = 0; j < cdim; j++)
/*     */       {
/* 165 */         int book = this.subclassBooks[cls][(cval & csub)];
/* 166 */         cval >>>= cbits;
/* 167 */         if (book >= 0) {
/* 168 */           clone.yList[(j + offset)] = source.getInt(vorbis.getSetupHeader().getCodeBooks()[book].getHuffmanRoot());
/*     */         }
/*     */         else
/*     */         {
/* 174 */           clone.yList[(j + offset)] = 0;
/*     */         }
/*     */       }
/* 177 */       offset += cdim;
/*     */     }
/*     */ 
/* 195 */     return clone;
/*     */   }
/*     */ 
/*     */   protected void computeFloor(float[] vector)
/*     */   {
/* 200 */     int n = vector.length;
/* 201 */     int values = this.xList.length;
/* 202 */     boolean[] step2Flags = new boolean[values];
/*     */ 
/* 204 */     int range = RANGES[(this.multiplier - 1)];
/*     */ 
/* 206 */     for (int i = 2; i < values; i++) {
/* 207 */       int lowNeighbourOffset = this.lowNeighbours[i];
/* 208 */       int highNeighbourOffset = this.highNeighbours[i];
/* 209 */       int predicted = Util.renderPoint(this.xList[lowNeighbourOffset], this.xList[highNeighbourOffset], this.yList[lowNeighbourOffset], this.yList[highNeighbourOffset], this.xList[i]);
/*     */ 
/* 213 */       int val = this.yList[i];
/* 214 */       int highRoom = range - predicted;
/* 215 */       int lowRoom = predicted;
/* 216 */       int room = highRoom < lowRoom ? highRoom * 2 : lowRoom * 2;
/* 217 */       if (val != 0) {
/* 218 */         step2Flags[lowNeighbourOffset] = true;
/* 219 */         step2Flags[highNeighbourOffset] = true;
/* 220 */         step2Flags[i] = true;
/* 221 */         if (val >= room) {
/* 222 */           this.yList[i] = (highRoom > lowRoom ? val - lowRoom + predicted : -val + highRoom + predicted - 1);
/*     */         }
/*     */         else
/*     */         {
/* 227 */           this.yList[i] = ((val & 0x1) == 1 ? predicted - (val + 1 >> 1) : predicted + (val >> 1));
/*     */         }
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/* 233 */         step2Flags[i] = false;
/* 234 */         this.yList[i] = predicted;
/*     */       }
/*     */     }
/*     */ 
/* 238 */     int[] xList2 = new int[values];
/*     */ 
/* 240 */     System.arraycopy(this.xList, 0, xList2, 0, values);
/* 241 */     sort(xList2, this.yList, step2Flags);
/*     */ 
/* 243 */     int hx = 0; int hy = 0; int lx = 0; int ly = this.yList[0] * this.multiplier;
/*     */ 
/* 245 */     float[] vector2 = new float[vector.length];
/* 246 */     float[] vector3 = new float[vector.length];
/* 247 */     Arrays.fill(vector2, 1.0F);
/* 248 */     System.arraycopy(vector, 0, vector3, 0, vector.length);
/*     */ 
/* 250 */     for (int i = 1; i < values; i++) {
/* 251 */       if (step2Flags[i] != 0) {
/* 252 */         hy = this.yList[i] * this.multiplier;
/* 253 */         hx = xList2[i];
/* 254 */         Util.renderLine(lx, ly, hx, hy, vector);
/* 255 */         Util.renderLine(lx, ly, hx, hy, vector2);
/* 256 */         lx = hx;
/* 257 */         ly = hy;
/*     */       }
/*     */     }
/*     */ 
/* 261 */     float r = DB_STATIC_TABLE[hy];
/* 262 */     while (hx < n / 2) vector[(hx++)] = r; 
/*     */   }
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 266 */     Floor1 clone = new Floor1();
/* 267 */     clone.classDimensions = this.classDimensions;
/* 268 */     clone.classMasterbooks = this.classMasterbooks;
/* 269 */     clone.classSubclasses = this.classSubclasses;
/* 270 */     clone.maximumClass = this.maximumClass;
/* 271 */     clone.multiplier = this.multiplier;
/* 272 */     clone.partitionClassList = this.partitionClassList;
/* 273 */     clone.rangeBits = this.rangeBits;
/* 274 */     clone.subclassBooks = this.subclassBooks;
/* 275 */     clone.xList = this.xList;
/* 276 */     clone.yList = this.yList;
/* 277 */     clone.lowNeighbours = this.lowNeighbours;
/* 278 */     clone.highNeighbours = this.highNeighbours;
/* 279 */     return clone;
/*     */   }
/*     */ 
/*     */   private static final void sort(int[] x, int[] y, boolean[] b) {
/* 283 */     int off = 0;
/* 284 */     int len = x.length;
/* 285 */     int lim = len + off;
/*     */ 
/* 289 */     for (int i = off; i < lim; i++)
/* 290 */       for (int j = i; (j > off) && (x[(j - 1)] > x[j]); j--) {
/* 291 */         int itmp = x[j];
/* 292 */         x[j] = x[(j - 1)];
/* 293 */         x[(j - 1)] = itmp;
/* 294 */         itmp = y[j];
/* 295 */         y[j] = y[(j - 1)];
/* 296 */         y[(j - 1)] = itmp;
/* 297 */         boolean btmp = b[j];
/* 298 */         b[j] = b[(j - 1)];
/* 299 */         b[(j - 1)] = btmp;
/*     */       }
/*     */   }
/*     */ 
/*     */   private static final void swap(int[] x, int a, int b)
/*     */   {
/* 308 */     int t = x[a];
/* 309 */     x[a] = x[b];
/* 310 */     x[b] = t;
/*     */   }
/*     */ 
/*     */   private static final void swap(boolean[] x, int a, int b) {
/* 314 */     boolean t = x[a];
/* 315 */     x[a] = x[b];
/* 316 */     x[b] = t;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     de.jarnbjo.vorbis.Floor1
 * JD-Core Version:    0.6.2
 */