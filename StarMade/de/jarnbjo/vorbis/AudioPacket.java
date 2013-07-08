/*   1:    */package de.jarnbjo.vorbis;
/*   2:    */
/*   3:    */import de.jarnbjo.util.io.BitInputStream;
/*   4:    */import java.io.IOException;
/*   5:    */
/*  26:    */class AudioPacket
/*  27:    */{
/*  28:    */  private int modeNumber;
/*  29:    */  private Mode mode;
/*  30:    */  private Mapping mapping;
/*  31:    */  private int n;
/*  32:    */  private boolean blockFlag;
/*  33:    */  private boolean previousWindowFlag;
/*  34:    */  private boolean nextWindowFlag;
/*  35:    */  private int windowCenter;
/*  36:    */  private int leftWindowStart;
/*  37:    */  private int leftWindowEnd;
/*  38:    */  private int leftN;
/*  39:    */  private int rightWindowStart;
/*  40:    */  private int rightWindowEnd;
/*  41:    */  private int rightN;
/*  42:    */  private float[] window;
/*  43:    */  private float[][] pcm;
/*  44:    */  private int[][] pcmInt;
/*  45:    */  private Floor[] channelFloors;
/*  46:    */  private boolean[] noResidues;
/*  47: 47 */  private static final float[][] windows = new float[8][];
/*  48:    */  
/*  49:    */  protected AudioPacket(VorbisStream vorbis, BitInputStream source) throws VorbisFormatException, IOException
/*  50:    */  {
/*  51: 51 */    SetupHeader sHeader = vorbis.getSetupHeader();
/*  52: 52 */    IdentificationHeader iHeader = vorbis.getIdentificationHeader();
/*  53: 53 */    Mode[] modes = sHeader.getModes();
/*  54: 54 */    Mapping[] mappings = sHeader.getMappings();
/*  55: 55 */    Residue[] residues = sHeader.getResidues();
/*  56: 56 */    int channels = iHeader.getChannels();
/*  57:    */    
/*  58: 58 */    if (source.getInt(1) != 0) {
/*  59: 59 */      throw new VorbisFormatException("Packet type mismatch when trying to create an audio packet.");
/*  60:    */    }
/*  61:    */    
/*  62: 62 */    this.modeNumber = source.getInt(Util.ilog(modes.length - 1));
/*  63:    */    try
/*  64:    */    {
/*  65: 65 */      this.mode = modes[this.modeNumber];
/*  66:    */    }
/*  67:    */    catch (ArrayIndexOutOfBoundsException e) {
/*  68: 68 */      throw new VorbisFormatException("Reference to invalid mode in audio packet.");
/*  69:    */    }
/*  70:    */    
/*  71: 71 */    this.mapping = mappings[this.mode.getMapping()];
/*  72:    */    
/*  73: 73 */    int[] magnitudes = this.mapping.getMagnitudes();
/*  74: 74 */    int[] angles = this.mapping.getAngles();
/*  75:    */    
/*  76: 76 */    this.blockFlag = this.mode.getBlockFlag();
/*  77:    */    
/*  78: 78 */    int blockSize0 = iHeader.getBlockSize0();
/*  79: 79 */    int blockSize1 = iHeader.getBlockSize1();
/*  80:    */    
/*  81: 81 */    this.n = (this.blockFlag ? blockSize1 : blockSize0);
/*  82:    */    
/*  83: 83 */    if (this.blockFlag) {
/*  84: 84 */      this.previousWindowFlag = source.getBit();
/*  85: 85 */      this.nextWindowFlag = source.getBit();
/*  86:    */    }
/*  87:    */    
/*  88: 88 */    this.windowCenter = (this.n / 2);
/*  89:    */    
/*  90: 90 */    if ((this.blockFlag) && (!this.previousWindowFlag)) {
/*  91: 91 */      this.leftWindowStart = (this.n / 4 - blockSize0 / 4);
/*  92: 92 */      this.leftWindowEnd = (this.n / 4 + blockSize0 / 4);
/*  93: 93 */      this.leftN = (blockSize0 / 2);
/*  94:    */    }
/*  95:    */    else {
/*  96: 96 */      this.leftWindowStart = 0;
/*  97: 97 */      this.leftWindowEnd = (this.n / 2);
/*  98: 98 */      this.leftN = this.windowCenter;
/*  99:    */    }
/* 100:    */    
/* 101:101 */    if ((this.blockFlag) && (!this.nextWindowFlag)) {
/* 102:102 */      this.rightWindowStart = (this.n * 3 / 4 - blockSize0 / 4);
/* 103:103 */      this.rightWindowEnd = (this.n * 3 / 4 + blockSize0 / 4);
/* 104:104 */      this.rightN = (blockSize0 / 2);
/* 105:    */    }
/* 106:    */    else {
/* 107:107 */      this.rightWindowStart = this.windowCenter;
/* 108:108 */      this.rightWindowEnd = this.n;
/* 109:109 */      this.rightN = (this.n / 2);
/* 110:    */    }
/* 111:    */    
/* 112:112 */    this.window = getComputedWindow();
/* 113:    */    
/* 114:114 */    this.channelFloors = new Floor[channels];
/* 115:115 */    this.noResidues = new boolean[channels];
/* 116:    */    
/* 117:117 */    this.pcm = new float[channels][this.n];
/* 118:118 */    this.pcmInt = new int[channels][this.n];
/* 119:    */    
/* 120:120 */    boolean allFloorsEmpty = true;
/* 121:    */    
/* 122:122 */    for (int i = 0; i < channels; i++) {
/* 123:123 */      int submapNumber = this.mapping.getMux()[i];
/* 124:124 */      int floorNumber = this.mapping.getSubmapFloors()[submapNumber];
/* 125:125 */      Floor decodedFloor = sHeader.getFloors()[floorNumber].decodeFloor(vorbis, source);
/* 126:126 */      this.channelFloors[i] = decodedFloor;
/* 127:127 */      this.noResidues[i] = (decodedFloor == null ? 1 : false);
/* 128:128 */      if (decodedFloor != null) {
/* 129:129 */        allFloorsEmpty = false;
/* 130:    */      }
/* 131:    */    }
/* 132:    */    
/* 133:133 */    if (allFloorsEmpty) {
/* 134:134 */      return;
/* 135:    */    }
/* 136:    */    
/* 137:137 */    for (int i = 0; i < magnitudes.length; i++) {
/* 138:138 */      if ((this.noResidues[magnitudes[i]] == 0) || (this.noResidues[angles[i]] == 0))
/* 139:    */      {
/* 141:141 */        this.noResidues[magnitudes[i]] = false;
/* 142:142 */        this.noResidues[angles[i]] = false;
/* 143:    */      }
/* 144:    */    }
/* 145:    */    
/* 146:146 */    Residue[] decodedResidues = new Residue[this.mapping.getSubmaps()];
/* 147:    */    
/* 148:148 */    for (int i = 0; i < this.mapping.getSubmaps(); i++) {
/* 149:149 */      int ch = 0;
/* 150:150 */      boolean[] doNotDecodeFlags = new boolean[channels];
/* 151:151 */      for (int j = 0; j < channels; j++) {
/* 152:152 */        if (this.mapping.getMux()[j] == i) {
/* 153:153 */          doNotDecodeFlags[(ch++)] = this.noResidues[j];
/* 154:    */        }
/* 155:    */      }
/* 156:156 */      int residueNumber = this.mapping.getSubmapResidues()[i];
/* 157:157 */      Residue residue = residues[residueNumber];
/* 158:    */      
/* 159:159 */      residue.decodeResidue(vorbis, source, this.mode, ch, doNotDecodeFlags, this.pcm);
/* 160:    */    }
/* 161:    */    
/* 163:163 */    for (int i = this.mapping.getCouplingSteps() - 1; i >= 0; i--) {
/* 164:164 */      double newA = 0.0D;double newM = 0.0D;
/* 165:165 */      float[] magnitudeVector = this.pcm[magnitudes[i]];
/* 166:166 */      float[] angleVector = this.pcm[angles[i]];
/* 167:167 */      for (int j = 0; j < magnitudeVector.length; j++) {
/* 168:168 */        float a = angleVector[j];
/* 169:169 */        float m = magnitudeVector[j];
/* 170:170 */        if (a > 0.0F)
/* 171:    */        {
/* 172:172 */          angleVector[j] = (m > 0.0F ? m - a : m + a);
/* 173:    */        }
/* 174:    */        else {
/* 175:175 */          magnitudeVector[j] = (m > 0.0F ? m + a : m - a);
/* 176:176 */          angleVector[j] = m;
/* 177:    */        }
/* 178:    */      }
/* 179:    */    }
/* 180:    */    
/* 181:181 */    for (int i = 0; i < channels; i++) {
/* 182:182 */      if (this.channelFloors[i] != null) {
/* 183:183 */        this.channelFloors[i].computeFloor(this.pcm[i]);
/* 184:    */      }
/* 185:    */    }
/* 186:    */    
/* 189:189 */    for (int i = 0; i < channels; i++) {
/* 190:190 */      MdctFloat mdct = this.blockFlag ? iHeader.getMdct1() : iHeader.getMdct0();
/* 191:191 */      mdct.imdct(this.pcm[i], this.window, this.pcmInt[i]);
/* 192:    */    }
/* 193:    */  }
/* 194:    */  
/* 195:    */  private float[] getComputedWindow()
/* 196:    */  {
/* 197:197 */    int ix = (this.blockFlag ? 4 : 0) + (this.previousWindowFlag ? 2 : 0) + (this.nextWindowFlag ? 1 : 0);
/* 198:198 */    float[] w = windows[ix];
/* 199:199 */    if (w == null) {
/* 200:200 */      w = new float[this.n];
/* 201:    */      
/* 202:202 */      for (int i = 0; i < this.leftN; i++) {
/* 203:203 */        float x = (float)((i + 0.5D) / this.leftN * 3.141592653589793D / 2.0D);
/* 204:204 */        x = (float)Math.sin(x);
/* 205:205 */        x *= x;
/* 206:206 */        x = (float)(x * 1.570796370506287D);
/* 207:207 */        x = (float)Math.sin(x);
/* 208:208 */        w[(i + this.leftWindowStart)] = x;
/* 209:    */      }
/* 210:    */      
/* 211:211 */      for (int i = this.leftWindowEnd; i < this.rightWindowStart; w[(i++)] = 1.0F) {}
/* 212:    */      
/* 213:213 */      for (int i = 0; i < this.rightN; i++) {
/* 214:214 */        float x = (float)((this.rightN - i - 0.5D) / this.rightN * 3.141592653589793D / 2.0D);
/* 215:215 */        x = (float)Math.sin(x);
/* 216:216 */        x *= x;
/* 217:217 */        x = (float)(x * 1.570796370506287D);
/* 218:218 */        x = (float)Math.sin(x);
/* 219:219 */        w[(i + this.rightWindowStart)] = x;
/* 220:    */      }
/* 221:    */      
/* 222:222 */      windows[ix] = w;
/* 223:    */    }
/* 224:224 */    return w;
/* 225:    */  }
/* 226:    */  
/* 227:    */  protected int getNumberOfSamples() {
/* 228:228 */    return this.rightWindowStart - this.leftWindowStart;
/* 229:    */  }
/* 230:    */  
/* 231:    */  protected int getPcm(AudioPacket previousPacket, int[][] buffer) {
/* 232:232 */    int channels = this.pcm.length;
/* 233:    */    
/* 237:237 */    for (int i = 0; i < channels; i++) {
/* 238:238 */      int j1 = 0;int j2 = previousPacket.rightWindowStart;
/* 239:239 */      int[] ppcm = previousPacket.pcmInt[i];
/* 240:240 */      int[] tpcm = this.pcmInt[i];
/* 241:241 */      int[] target = buffer[i];
/* 242:    */      
/* 243:243 */      for (int j = this.leftWindowStart; j < this.leftWindowEnd; j++) {
/* 244:244 */        int val = ppcm[(j2++)] + tpcm[j];
/* 245:245 */        if (val > 32767) val = 32767;
/* 246:246 */        if (val < -32768) val = -32768;
/* 247:247 */        target[(j1++)] = val;
/* 248:    */      }
/* 249:    */    }
/* 250:    */    
/* 253:253 */    if (this.leftWindowEnd + 1 < this.rightWindowStart) {
/* 254:254 */      for (int i = 0; i < channels; i++) {
/* 255:255 */        System.arraycopy(this.pcmInt[i], this.leftWindowEnd, buffer[i], this.leftWindowEnd - this.leftWindowStart, this.rightWindowStart - this.leftWindowEnd);
/* 256:    */      }
/* 257:    */    }
/* 258:    */    
/* 259:259 */    return this.rightWindowStart - this.leftWindowStart;
/* 260:    */  }
/* 261:    */  
/* 262:    */  protected void getPcm(AudioPacket previousPacket, byte[] buffer) {
/* 263:263 */    int channels = this.pcm.length;
/* 264:    */    
/* 268:268 */    for (int i = 0; i < channels; i++) {
/* 269:269 */      int ix = 0;int j2 = previousPacket.rightWindowStart;
/* 270:270 */      int[] ppcm = previousPacket.pcmInt[i];
/* 271:271 */      int[] tpcm = this.pcmInt[i];
/* 272:272 */      for (int j = this.leftWindowStart; j < this.leftWindowEnd; j++) {
/* 273:273 */        int val = ppcm[(j2++)] + tpcm[j];
/* 274:274 */        if (val > 32767) val = 32767;
/* 275:275 */        if (val < -32768) val = -32768;
/* 276:276 */        buffer[(ix + i * 2 + 1)] = ((byte)(val & 0xFF));
/* 277:277 */        buffer[(ix + i * 2)] = ((byte)(val >> 8 & 0xFF));
/* 278:278 */        ix += channels * 2;
/* 279:    */      }
/* 280:    */      
/* 281:281 */      ix = (this.leftWindowEnd - this.leftWindowStart) * channels * 2;
/* 282:282 */      for (int j = this.leftWindowEnd; j < this.rightWindowStart; j++) {
/* 283:283 */        int val = tpcm[j];
/* 284:284 */        if (val > 32767) val = 32767;
/* 285:285 */        if (val < -32768) val = -32768;
/* 286:286 */        buffer[(ix + i * 2 + 1)] = ((byte)(val & 0xFF));
/* 287:287 */        buffer[(ix + i * 2)] = ((byte)(val >> 8 & 0xFF));
/* 288:288 */        ix += channels * 2;
/* 289:    */      }
/* 290:    */    }
/* 291:    */  }
/* 292:    */  
/* 293:    */  protected float[] getWindow() {
/* 294:294 */    return this.window;
/* 295:    */  }
/* 296:    */  
/* 297:    */  protected int getLeftWindowStart() {
/* 298:298 */    return this.leftWindowStart;
/* 299:    */  }
/* 300:    */  
/* 301:    */  protected int getLeftWindowEnd() {
/* 302:302 */    return this.leftWindowEnd;
/* 303:    */  }
/* 304:    */  
/* 305:    */  protected int getRightWindowStart() {
/* 306:306 */    return this.rightWindowStart;
/* 307:    */  }
/* 308:    */  
/* 309:    */  protected int getRightWindowEnd() {
/* 310:310 */    return this.rightWindowEnd;
/* 311:    */  }
/* 312:    */  
/* 313:    */  public int[][] getPcm() {
/* 314:314 */    return this.pcmInt;
/* 315:    */  }
/* 316:    */  
/* 317:    */  public float[][] getFreqencyDomain() {
/* 318:318 */    return this.pcm;
/* 319:    */  }
/* 320:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     de.jarnbjo.vorbis.AudioPacket
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */