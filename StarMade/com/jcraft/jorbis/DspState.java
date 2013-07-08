/*   1:    */package com.jcraft.jorbis;
/*   2:    */
/*   5:    */public class DspState
/*   6:    */{
/*   7:    */  static final float M_PI = 3.141593F;
/*   8:    */  
/*  10:    */  static final int VI_TRANSFORMB = 1;
/*  11:    */  
/*  13:    */  static final int VI_WINDOWB = 1;
/*  14:    */  
/*  16:    */  int analysisp;
/*  17:    */  
/*  19:    */  Info vi;
/*  20:    */  
/*  22:    */  int modebits;
/*  23:    */  
/*  25:    */  float[][] pcm;
/*  26:    */  
/*  27:    */  int pcm_storage;
/*  28:    */  
/*  29:    */  int pcm_current;
/*  30:    */  
/*  31:    */  int pcm_returned;
/*  32:    */  
/*  33:    */  float[] multipliers;
/*  34:    */  
/*  35:    */  int envelope_storage;
/*  36:    */  
/*  37:    */  int envelope_current;
/*  38:    */  
/*  39:    */  int eofflag;
/*  40:    */  
/*  41:    */  int lW;
/*  42:    */  
/*  43:    */  int W;
/*  44:    */  
/*  45:    */  int nW;
/*  46:    */  
/*  47:    */  int centerW;
/*  48:    */  
/*  49:    */  long granulepos;
/*  50:    */  
/*  51:    */  long sequence;
/*  52:    */  
/*  53:    */  long glue_bits;
/*  54:    */  
/*  55:    */  long time_bits;
/*  56:    */  
/*  57:    */  long floor_bits;
/*  58:    */  
/*  59:    */  long res_bits;
/*  60:    */  
/*  61:    */  float[][][][][] window;
/*  62:    */  
/*  63:    */  Object[][] transform;
/*  64:    */  
/*  65:    */  CodeBook[] fullbooks;
/*  66:    */  
/*  67:    */  Object[] mode;
/*  68:    */  
/*  69:    */  byte[] header;
/*  70:    */  
/*  71:    */  byte[] header1;
/*  72:    */  
/*  73:    */  byte[] header2;
/*  74:    */  
/*  76:    */  public DspState()
/*  77:    */  {
/*  78: 78 */    this.transform = new Object[2][];
/*  79: 79 */    this.window = new float[2][][][][];
/*  80: 80 */    this.window[0] = new float[2][][][];
/*  81: 81 */    this.window[0][0] = new float[2][][];
/*  82: 82 */    this.window[0][1] = new float[2][][];
/*  83: 83 */    this.window[0][0][0] = new float[2][];
/*  84: 84 */    this.window[0][0][1] = new float[2][];
/*  85: 85 */    this.window[0][1][0] = new float[2][];
/*  86: 86 */    this.window[0][1][1] = new float[2][];
/*  87: 87 */    this.window[1] = new float[2][][][];
/*  88: 88 */    this.window[1][0] = new float[2][][];
/*  89: 89 */    this.window[1][1] = new float[2][][];
/*  90: 90 */    this.window[1][0][0] = new float[2][];
/*  91: 91 */    this.window[1][0][1] = new float[2][];
/*  92: 92 */    this.window[1][1][0] = new float[2][];
/*  93: 93 */    this.window[1][1][1] = new float[2][];
/*  94:    */  }
/*  95:    */  
/*  96:    */  static float[] window(int type, int window, int left, int right) {
/*  97: 97 */    float[] ret = new float[window];
/*  98: 98 */    switch (type)
/*  99:    */    {
/* 101:    */    case 0: 
/* 102:102 */      int leftbegin = window / 4 - left / 2;
/* 103:103 */      int rightbegin = window - window / 4 - right / 2;
/* 104:    */      
/* 105:105 */      for (int i = 0; i < left; i++) {
/* 106:106 */        float x = (float)((i + 0.5D) / left * 3.141592741012573D / 2.0D);
/* 107:107 */        x = (float)Math.sin(x);
/* 108:108 */        x *= x;
/* 109:109 */        x = (float)(x * 1.570796370506287D);
/* 110:110 */        x = (float)Math.sin(x);
/* 111:111 */        ret[(i + leftbegin)] = x;
/* 112:    */      }
/* 113:    */      
/* 114:114 */      for (int i = leftbegin + left; i < rightbegin; i++) {
/* 115:115 */        ret[i] = 1.0F;
/* 116:    */      }
/* 117:    */      
/* 118:118 */      for (int i = 0; i < right; i++) {
/* 119:119 */        float x = (float)((right - i - 0.5D) / right * 3.141592741012573D / 2.0D);
/* 120:120 */        x = (float)Math.sin(x);
/* 121:121 */        x *= x;
/* 122:122 */        x = (float)(x * 1.570796370506287D);
/* 123:123 */        x = (float)Math.sin(x);
/* 124:124 */        ret[(i + rightbegin)] = x;
/* 125:    */      }
/* 126:    */      
/* 127:127 */      break;
/* 128:    */    
/* 129:    */    default: 
/* 130:130 */      return null; }
/* 131:    */    
/* 132:132 */    return ret;
/* 133:    */  }
/* 134:    */  
/* 138:    */  int init(Info vi, boolean encp)
/* 139:    */  {
/* 140:140 */    this.vi = vi;
/* 141:141 */    this.modebits = Util.ilog2(vi.modes);
/* 142:    */    
/* 143:143 */    this.transform[0] = new Object[1];
/* 144:144 */    this.transform[1] = new Object[1];
/* 145:    */    
/* 148:148 */    this.transform[0][0] = new Mdct();
/* 149:149 */    this.transform[1][0] = new Mdct();
/* 150:150 */    ((Mdct)this.transform[0][0]).init(vi.blocksizes[0]);
/* 151:151 */    ((Mdct)this.transform[1][0]).init(vi.blocksizes[1]);
/* 152:    */    
/* 153:153 */    this.window[0][0][0] = new float[1][];
/* 154:154 */    this.window[0][0][1] = this.window[0][0][0];
/* 155:155 */    this.window[0][1][0] = this.window[0][0][0];
/* 156:156 */    this.window[0][1][1] = this.window[0][0][0];
/* 157:157 */    this.window[1][0][0] = new float[1][];
/* 158:158 */    this.window[1][0][1] = new float[1][];
/* 159:159 */    this.window[1][1][0] = new float[1][];
/* 160:160 */    this.window[1][1][1] = new float[1][];
/* 161:    */    
/* 162:162 */    for (int i = 0; i < 1; i++) {
/* 163:163 */      this.window[0][0][0][i] = window(i, vi.blocksizes[0], vi.blocksizes[0] / 2, vi.blocksizes[0] / 2);
/* 164:    */      
/* 165:165 */      this.window[1][0][0][i] = window(i, vi.blocksizes[1], vi.blocksizes[0] / 2, vi.blocksizes[0] / 2);
/* 166:    */      
/* 167:167 */      this.window[1][0][1][i] = window(i, vi.blocksizes[1], vi.blocksizes[0] / 2, vi.blocksizes[1] / 2);
/* 168:    */      
/* 169:169 */      this.window[1][1][0][i] = window(i, vi.blocksizes[1], vi.blocksizes[1] / 2, vi.blocksizes[0] / 2);
/* 170:    */      
/* 171:171 */      this.window[1][1][1][i] = window(i, vi.blocksizes[1], vi.blocksizes[1] / 2, vi.blocksizes[1] / 2);
/* 172:    */    }
/* 173:    */    
/* 175:175 */    this.fullbooks = new CodeBook[vi.books];
/* 176:176 */    for (int i = 0; i < vi.books; i++) {
/* 177:177 */      this.fullbooks[i] = new CodeBook();
/* 178:178 */      this.fullbooks[i].init_decode(vi.book_param[i]);
/* 179:    */    }
/* 180:    */    
/* 184:184 */    this.pcm_storage = 8192;
/* 185:    */    
/* 187:187 */    this.pcm = new float[vi.channels][];
/* 188:    */    
/* 189:189 */    for (int i = 0; i < vi.channels; i++) {
/* 190:190 */      this.pcm[i] = new float[this.pcm_storage];
/* 191:    */    }
/* 192:    */    
/* 196:196 */    this.lW = 0;
/* 197:197 */    this.W = 0;
/* 198:    */    
/* 200:200 */    this.centerW = (vi.blocksizes[1] / 2);
/* 201:    */    
/* 202:202 */    this.pcm_current = this.centerW;
/* 203:    */    
/* 205:205 */    this.mode = new Object[vi.modes];
/* 206:206 */    for (int i = 0; i < vi.modes; i++) {
/* 207:207 */      int mapnum = vi.mode_param[i].mapping;
/* 208:208 */      int maptype = vi.map_type[mapnum];
/* 209:209 */      this.mode[i] = FuncMapping.mapping_P[maptype].look(this, vi.mode_param[i], vi.map_param[mapnum]);
/* 210:    */    }
/* 211:    */    
/* 212:212 */    return 0;
/* 213:    */  }
/* 214:    */  
/* 215:    */  public int synthesis_init(Info vi) {
/* 216:216 */    init(vi, false);
/* 217:    */    
/* 218:218 */    this.pcm_returned = this.centerW;
/* 219:219 */    this.centerW -= vi.blocksizes[this.W] / 4 + vi.blocksizes[this.lW] / 4;
/* 220:220 */    this.granulepos = -1L;
/* 221:221 */    this.sequence = -1L;
/* 222:222 */    return 0;
/* 223:    */  }
/* 224:    */  
/* 225:    */  DspState(Info vi) {
/* 226:226 */    this();
/* 227:227 */    init(vi, false);
/* 228:    */    
/* 229:229 */    this.pcm_returned = this.centerW;
/* 230:230 */    this.centerW -= vi.blocksizes[this.W] / 4 + vi.blocksizes[this.lW] / 4;
/* 231:231 */    this.granulepos = -1L;
/* 232:232 */    this.sequence = -1L;
/* 233:    */  }
/* 234:    */  
/* 240:    */  public int synthesis_blockin(Block vb)
/* 241:    */  {
/* 242:242 */    if ((this.centerW > this.vi.blocksizes[1] / 2) && (this.pcm_returned > 8192))
/* 243:    */    {
/* 246:246 */      int shiftPCM = this.centerW - this.vi.blocksizes[1] / 2;
/* 247:247 */      shiftPCM = this.pcm_returned < shiftPCM ? this.pcm_returned : shiftPCM;
/* 248:    */      
/* 249:249 */      this.pcm_current -= shiftPCM;
/* 250:250 */      this.centerW -= shiftPCM;
/* 251:251 */      this.pcm_returned -= shiftPCM;
/* 252:252 */      if (shiftPCM != 0) {
/* 253:253 */        for (int i = 0; i < this.vi.channels; i++) {
/* 254:254 */          System.arraycopy(this.pcm[i], shiftPCM, this.pcm[i], 0, this.pcm_current);
/* 255:    */        }
/* 256:    */      }
/* 257:    */    }
/* 258:    */    
/* 259:259 */    this.lW = this.W;
/* 260:260 */    this.W = vb.W;
/* 261:261 */    this.nW = -1;
/* 262:    */    
/* 263:263 */    this.glue_bits += vb.glue_bits;
/* 264:264 */    this.time_bits += vb.time_bits;
/* 265:265 */    this.floor_bits += vb.floor_bits;
/* 266:266 */    this.res_bits += vb.res_bits;
/* 267:    */    
/* 268:268 */    if (this.sequence + 1L != vb.sequence) {
/* 269:269 */      this.granulepos = -1L;
/* 270:    */    }
/* 271:271 */    this.sequence = vb.sequence;
/* 272:    */    
/* 274:274 */    int sizeW = this.vi.blocksizes[this.W];
/* 275:275 */    int _centerW = this.centerW + this.vi.blocksizes[this.lW] / 4 + sizeW / 4;
/* 276:276 */    int beginW = _centerW - sizeW / 2;
/* 277:277 */    int endW = beginW + sizeW;
/* 278:278 */    int beginSl = 0;
/* 279:279 */    int endSl = 0;
/* 280:    */    
/* 282:282 */    if (endW > this.pcm_storage)
/* 283:    */    {
/* 284:284 */      this.pcm_storage = (endW + this.vi.blocksizes[1]);
/* 285:285 */      for (int i = 0; i < this.vi.channels; i++) {
/* 286:286 */        float[] foo = new float[this.pcm_storage];
/* 287:287 */        System.arraycopy(this.pcm[i], 0, foo, 0, this.pcm[i].length);
/* 288:288 */        this.pcm[i] = foo;
/* 289:    */      }
/* 290:    */    }
/* 291:    */    
/* 293:293 */    switch (this.W) {
/* 294:    */    case 0: 
/* 295:295 */      beginSl = 0;
/* 296:296 */      endSl = this.vi.blocksizes[0] / 2;
/* 297:297 */      break;
/* 298:    */    case 1: 
/* 299:299 */      beginSl = this.vi.blocksizes[1] / 4 - this.vi.blocksizes[this.lW] / 4;
/* 300:300 */      endSl = beginSl + this.vi.blocksizes[this.lW] / 2;
/* 301:    */    }
/* 302:    */    
/* 303:    */    
/* 304:304 */    for (int j = 0; j < this.vi.channels; j++) {
/* 305:305 */      int _pcm = beginW;
/* 306:    */      
/* 307:307 */      int i = 0;
/* 308:308 */      for (i = beginSl; i < endSl; i++) {
/* 309:309 */        this.pcm[j][(_pcm + i)] += vb.pcm[j][i];
/* 310:    */      }
/* 311:312 */      for (; 
/* 312:312 */          i < sizeW; i++) {
/* 313:313 */        this.pcm[j][(_pcm + i)] = vb.pcm[j][i];
/* 314:    */      }
/* 315:    */    }
/* 316:    */    
/* 328:328 */    if (this.granulepos == -1L) {
/* 329:329 */      this.granulepos = vb.granulepos;
/* 330:    */    }
/* 331:    */    else {
/* 332:332 */      this.granulepos += _centerW - this.centerW;
/* 333:333 */      if ((vb.granulepos != -1L) && (this.granulepos != vb.granulepos)) {
/* 334:334 */        if ((this.granulepos > vb.granulepos) && (vb.eofflag != 0))
/* 335:    */        {
/* 336:336 */          _centerW = (int)(_centerW - (this.granulepos - vb.granulepos));
/* 337:    */        }
/* 338:    */        
/* 339:339 */        this.granulepos = vb.granulepos;
/* 340:    */      }
/* 341:    */    }
/* 342:    */    
/* 345:345 */    this.centerW = _centerW;
/* 346:346 */    this.pcm_current = endW;
/* 347:347 */    if (vb.eofflag != 0) {
/* 348:348 */      this.eofflag = 1;
/* 349:    */    }
/* 350:350 */    return 0;
/* 351:    */  }
/* 352:    */  
/* 353:    */  public int synthesis_pcmout(float[][][] _pcm, int[] index)
/* 354:    */  {
/* 355:355 */    if (this.pcm_returned < this.centerW) {
/* 356:356 */      if (_pcm != null) {
/* 357:357 */        for (int i = 0; i < this.vi.channels; i++) {
/* 358:358 */          index[i] = this.pcm_returned;
/* 359:    */        }
/* 360:360 */        _pcm[0] = this.pcm;
/* 361:    */      }
/* 362:362 */      return this.centerW - this.pcm_returned;
/* 363:    */    }
/* 364:364 */    return 0;
/* 365:    */  }
/* 366:    */  
/* 367:    */  public int synthesis_read(int bytes) {
/* 368:368 */    if ((bytes != 0) && (this.pcm_returned + bytes > this.centerW))
/* 369:369 */      return -1;
/* 370:370 */    this.pcm_returned += bytes;
/* 371:371 */    return 0;
/* 372:    */  }
/* 373:    */  
/* 374:    */  public void clear() {}
/* 375:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.jcraft.jorbis.DspState
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */