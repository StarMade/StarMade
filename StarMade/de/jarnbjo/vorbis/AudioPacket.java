/*     */ package de.jarnbjo.vorbis;
/*     */ 
/*     */ import de.jarnbjo.util.io.BitInputStream;
/*     */ import java.io.IOException;
/*     */ 
/*     */ class AudioPacket
/*     */ {
/*     */   private int modeNumber;
/*     */   private Mode mode;
/*     */   private Mapping mapping;
/*     */   private int n;
/*     */   private boolean blockFlag;
/*     */   private boolean previousWindowFlag;
/*     */   private boolean nextWindowFlag;
/*     */   private int windowCenter;
/*     */   private int leftWindowStart;
/*     */   private int leftWindowEnd;
/*     */   private int leftN;
/*     */   private int rightWindowStart;
/*     */   private int rightWindowEnd;
/*     */   private int rightN;
/*     */   private float[] window;
/*     */   private float[][] pcm;
/*     */   private int[][] pcmInt;
/*     */   private Floor[] channelFloors;
/*     */   private boolean[] noResidues;
/*  47 */   private static final float[][] windows = new float[8][];
/*     */ 
/*     */   protected AudioPacket(VorbisStream vorbis, BitInputStream source) throws VorbisFormatException, IOException
/*     */   {
/*  51 */     SetupHeader sHeader = vorbis.getSetupHeader();
/*  52 */     IdentificationHeader iHeader = vorbis.getIdentificationHeader();
/*  53 */     Mode[] modes = sHeader.getModes();
/*  54 */     Mapping[] mappings = sHeader.getMappings();
/*  55 */     Residue[] residues = sHeader.getResidues();
/*  56 */     int channels = iHeader.getChannels();
/*     */ 
/*  58 */     if (source.getInt(1) != 0) {
/*  59 */       throw new VorbisFormatException("Packet type mismatch when trying to create an audio packet.");
/*     */     }
/*     */ 
/*  62 */     this.modeNumber = source.getInt(Util.ilog(modes.length - 1));
/*     */     try
/*     */     {
/*  65 */       this.mode = modes[this.modeNumber];
/*     */     }
/*     */     catch (ArrayIndexOutOfBoundsException e) {
/*  68 */       throw new VorbisFormatException("Reference to invalid mode in audio packet.");
/*     */     }
/*     */ 
/*  71 */     this.mapping = mappings[this.mode.getMapping()];
/*     */ 
/*  73 */     int[] magnitudes = this.mapping.getMagnitudes();
/*  74 */     int[] angles = this.mapping.getAngles();
/*     */ 
/*  76 */     this.blockFlag = this.mode.getBlockFlag();
/*     */ 
/*  78 */     int blockSize0 = iHeader.getBlockSize0();
/*  79 */     int blockSize1 = iHeader.getBlockSize1();
/*     */ 
/*  81 */     this.n = (this.blockFlag ? blockSize1 : blockSize0);
/*     */ 
/*  83 */     if (this.blockFlag) {
/*  84 */       this.previousWindowFlag = source.getBit();
/*  85 */       this.nextWindowFlag = source.getBit();
/*     */     }
/*     */ 
/*  88 */     this.windowCenter = (this.n / 2);
/*     */ 
/*  90 */     if ((this.blockFlag) && (!this.previousWindowFlag)) {
/*  91 */       this.leftWindowStart = (this.n / 4 - blockSize0 / 4);
/*  92 */       this.leftWindowEnd = (this.n / 4 + blockSize0 / 4);
/*  93 */       this.leftN = (blockSize0 / 2);
/*     */     }
/*     */     else {
/*  96 */       this.leftWindowStart = 0;
/*  97 */       this.leftWindowEnd = (this.n / 2);
/*  98 */       this.leftN = this.windowCenter;
/*     */     }
/*     */ 
/* 101 */     if ((this.blockFlag) && (!this.nextWindowFlag)) {
/* 102 */       this.rightWindowStart = (this.n * 3 / 4 - blockSize0 / 4);
/* 103 */       this.rightWindowEnd = (this.n * 3 / 4 + blockSize0 / 4);
/* 104 */       this.rightN = (blockSize0 / 2);
/*     */     }
/*     */     else {
/* 107 */       this.rightWindowStart = this.windowCenter;
/* 108 */       this.rightWindowEnd = this.n;
/* 109 */       this.rightN = (this.n / 2);
/*     */     }
/*     */ 
/* 112 */     this.window = getComputedWindow();
/*     */ 
/* 114 */     this.channelFloors = new Floor[channels];
/* 115 */     this.noResidues = new boolean[channels];
/*     */ 
/* 117 */     this.pcm = new float[channels][this.n];
/* 118 */     this.pcmInt = new int[channels][this.n];
/*     */ 
/* 120 */     boolean allFloorsEmpty = true;
/*     */ 
/* 122 */     for (int i = 0; i < channels; i++) {
/* 123 */       int submapNumber = this.mapping.getMux()[i];
/* 124 */       int floorNumber = this.mapping.getSubmapFloors()[submapNumber];
/* 125 */       Floor decodedFloor = sHeader.getFloors()[floorNumber].decodeFloor(vorbis, source);
/* 126 */       this.channelFloors[i] = decodedFloor;
/* 127 */       this.noResidues[i] = (decodedFloor == null ? 1 : false);
/* 128 */       if (decodedFloor != null) {
/* 129 */         allFloorsEmpty = false;
/*     */       }
/*     */     }
/*     */ 
/* 133 */     if (allFloorsEmpty) {
/* 134 */       return;
/*     */     }
/*     */ 
/* 137 */     for (int i = 0; i < magnitudes.length; i++) {
/* 138 */       if ((this.noResidues[magnitudes[i]] == 0) || (this.noResidues[angles[i]] == 0))
/*     */       {
/* 141 */         this.noResidues[magnitudes[i]] = false;
/* 142 */         this.noResidues[angles[i]] = false;
/*     */       }
/*     */     }
/*     */ 
/* 146 */     Residue[] decodedResidues = new Residue[this.mapping.getSubmaps()];
/*     */ 
/* 148 */     for (int i = 0; i < this.mapping.getSubmaps(); i++) {
/* 149 */       int ch = 0;
/* 150 */       boolean[] doNotDecodeFlags = new boolean[channels];
/* 151 */       for (int j = 0; j < channels; j++) {
/* 152 */         if (this.mapping.getMux()[j] == i) {
/* 153 */           doNotDecodeFlags[(ch++)] = this.noResidues[j];
/*     */         }
/*     */       }
/* 156 */       int residueNumber = this.mapping.getSubmapResidues()[i];
/* 157 */       Residue residue = residues[residueNumber];
/*     */ 
/* 159 */       residue.decodeResidue(vorbis, source, this.mode, ch, doNotDecodeFlags, this.pcm);
/*     */     }
/*     */ 
/* 163 */     for (int i = this.mapping.getCouplingSteps() - 1; i >= 0; i--) {
/* 164 */       double newA = 0.0D; double newM = 0.0D;
/* 165 */       float[] magnitudeVector = this.pcm[magnitudes[i]];
/* 166 */       float[] angleVector = this.pcm[angles[i]];
/* 167 */       for (int j = 0; j < magnitudeVector.length; j++) {
/* 168 */         float a = angleVector[j];
/* 169 */         float m = magnitudeVector[j];
/* 170 */         if (a > 0.0F)
/*     */         {
/* 172 */           angleVector[j] = (m > 0.0F ? m - a : m + a);
/*     */         }
/*     */         else {
/* 175 */           magnitudeVector[j] = (m > 0.0F ? m + a : m - a);
/* 176 */           angleVector[j] = m;
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 181 */     for (int i = 0; i < channels; i++) {
/* 182 */       if (this.channelFloors[i] != null) {
/* 183 */         this.channelFloors[i].computeFloor(this.pcm[i]);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 189 */     for (int i = 0; i < channels; i++) {
/* 190 */       MdctFloat mdct = this.blockFlag ? iHeader.getMdct1() : iHeader.getMdct0();
/* 191 */       mdct.imdct(this.pcm[i], this.window, this.pcmInt[i]);
/*     */     }
/*     */   }
/*     */ 
/*     */   private float[] getComputedWindow()
/*     */   {
/* 197 */     int ix = (this.blockFlag ? 4 : 0) + (this.previousWindowFlag ? 2 : 0) + (this.nextWindowFlag ? 1 : 0);
/* 198 */     float[] w = windows[ix];
/* 199 */     if (w == null) {
/* 200 */       w = new float[this.n];
/*     */ 
/* 202 */       for (int i = 0; i < this.leftN; i++) {
/* 203 */         float x = (float)((i + 0.5D) / this.leftN * 3.141592653589793D / 2.0D);
/* 204 */         x = (float)Math.sin(x);
/* 205 */         x *= x;
/* 206 */         x = (float)(x * 1.570796370506287D);
/* 207 */         x = (float)Math.sin(x);
/* 208 */         w[(i + this.leftWindowStart)] = x;
/*     */       }
/*     */ 
/* 211 */       for (int i = this.leftWindowEnd; i < this.rightWindowStart; w[(i++)] = 1.0F);
/* 213 */       for (int i = 0; i < this.rightN; i++) {
/* 214 */         float x = (float)((this.rightN - i - 0.5D) / this.rightN * 3.141592653589793D / 2.0D);
/* 215 */         x = (float)Math.sin(x);
/* 216 */         x *= x;
/* 217 */         x = (float)(x * 1.570796370506287D);
/* 218 */         x = (float)Math.sin(x);
/* 219 */         w[(i + this.rightWindowStart)] = x;
/*     */       }
/*     */ 
/* 222 */       windows[ix] = w;
/*     */     }
/* 224 */     return w;
/*     */   }
/*     */ 
/*     */   protected int getNumberOfSamples() {
/* 228 */     return this.rightWindowStart - this.leftWindowStart;
/*     */   }
/*     */ 
/*     */   protected int getPcm(AudioPacket previousPacket, int[][] buffer) {
/* 232 */     int channels = this.pcm.length;
/*     */ 
/* 237 */     for (int i = 0; i < channels; i++) {
/* 238 */       int j1 = 0; int j2 = previousPacket.rightWindowStart;
/* 239 */       int[] ppcm = previousPacket.pcmInt[i];
/* 240 */       int[] tpcm = this.pcmInt[i];
/* 241 */       int[] target = buffer[i];
/*     */ 
/* 243 */       for (int j = this.leftWindowStart; j < this.leftWindowEnd; j++) {
/* 244 */         int val = ppcm[(j2++)] + tpcm[j];
/* 245 */         if (val > 32767) val = 32767;
/* 246 */         if (val < -32768) val = -32768;
/* 247 */         target[(j1++)] = val;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 253 */     if (this.leftWindowEnd + 1 < this.rightWindowStart) {
/* 254 */       for (int i = 0; i < channels; i++) {
/* 255 */         System.arraycopy(this.pcmInt[i], this.leftWindowEnd, buffer[i], this.leftWindowEnd - this.leftWindowStart, this.rightWindowStart - this.leftWindowEnd);
/*     */       }
/*     */     }
/*     */ 
/* 259 */     return this.rightWindowStart - this.leftWindowStart;
/*     */   }
/*     */ 
/*     */   protected void getPcm(AudioPacket previousPacket, byte[] buffer) {
/* 263 */     int channels = this.pcm.length;
/*     */ 
/* 268 */     for (int i = 0; i < channels; i++) {
/* 269 */       int ix = 0; int j2 = previousPacket.rightWindowStart;
/* 270 */       int[] ppcm = previousPacket.pcmInt[i];
/* 271 */       int[] tpcm = this.pcmInt[i];
/* 272 */       for (int j = this.leftWindowStart; j < this.leftWindowEnd; j++) {
/* 273 */         int val = ppcm[(j2++)] + tpcm[j];
/* 274 */         if (val > 32767) val = 32767;
/* 275 */         if (val < -32768) val = -32768;
/* 276 */         buffer[(ix + i * 2 + 1)] = ((byte)(val & 0xFF));
/* 277 */         buffer[(ix + i * 2)] = ((byte)(val >> 8 & 0xFF));
/* 278 */         ix += channels * 2;
/*     */       }
/*     */ 
/* 281 */       ix = (this.leftWindowEnd - this.leftWindowStart) * channels * 2;
/* 282 */       for (int j = this.leftWindowEnd; j < this.rightWindowStart; j++) {
/* 283 */         int val = tpcm[j];
/* 284 */         if (val > 32767) val = 32767;
/* 285 */         if (val < -32768) val = -32768;
/* 286 */         buffer[(ix + i * 2 + 1)] = ((byte)(val & 0xFF));
/* 287 */         buffer[(ix + i * 2)] = ((byte)(val >> 8 & 0xFF));
/* 288 */         ix += channels * 2;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   protected float[] getWindow() {
/* 294 */     return this.window;
/*     */   }
/*     */ 
/*     */   protected int getLeftWindowStart() {
/* 298 */     return this.leftWindowStart;
/*     */   }
/*     */ 
/*     */   protected int getLeftWindowEnd() {
/* 302 */     return this.leftWindowEnd;
/*     */   }
/*     */ 
/*     */   protected int getRightWindowStart() {
/* 306 */     return this.rightWindowStart;
/*     */   }
/*     */ 
/*     */   protected int getRightWindowEnd() {
/* 310 */     return this.rightWindowEnd;
/*     */   }
/*     */ 
/*     */   public int[][] getPcm() {
/* 314 */     return this.pcmInt;
/*     */   }
/*     */ 
/*     */   public float[][] getFreqencyDomain() {
/* 318 */     return this.pcm;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     de.jarnbjo.vorbis.AudioPacket
 * JD-Core Version:    0.6.2
 */