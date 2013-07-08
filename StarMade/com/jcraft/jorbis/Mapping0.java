/*   1:    */package com.jcraft.jorbis;
/*   2:    */
/*   3:    */import com.jcraft.jogg.Buffer;
/*   4:    */
/*  10:    */class Mapping0
/*  11:    */  extends FuncMapping
/*  12:    */{
/*  13:    */  class LookMapping0
/*  14:    */  {
/*  15:    */    InfoMode mode;
/*  16:    */    Mapping0.InfoMapping0 map;
/*  17:    */    Object[] time_look;
/*  18:    */    Object[] floor_look;
/*  19:    */    Object[] floor_state;
/*  20:    */    Object[] residue_look;
/*  21:    */    PsyLook[] psy_look;
/*  22:    */    FuncTime[] time_func;
/*  23:    */    FuncFloor[] floor_func;
/*  24:    */    FuncResidue[] residue_func;
/*  25:    */    int ch;
/*  26:    */    float[][] decay;
/*  27:    */    int lastframe;
/*  28:    */    
/*  29:    */    LookMapping0() {}
/*  30:    */  }
/*  31:    */  
/*  32: 32 */  static int seq = 0;
/*  33:    */  
/*  34:    */  float[][] pcmbundle;
/*  35:    */  
/*  36:    */  int[] zerobundle;
/*  37:    */  int[] nonzero;
/*  38:    */  Object[] floormemo;
/*  39:    */  
/*  40:    */  Object look(DspState vd, InfoMode vm, Object m)
/*  41:    */  {
/*  42: 42 */    Info vi = vd.vi;
/*  43: 43 */    LookMapping0 look = new LookMapping0();
/*  44: 44 */    InfoMapping0 info = look.map = (InfoMapping0)m;
/*  45: 45 */    look.mode = vm;
/*  46:    */    
/*  47: 47 */    look.time_look = new Object[info.submaps];
/*  48: 48 */    look.floor_look = new Object[info.submaps];
/*  49: 49 */    look.residue_look = new Object[info.submaps];
/*  50:    */    
/*  51: 51 */    look.time_func = new FuncTime[info.submaps];
/*  52: 52 */    look.floor_func = new FuncFloor[info.submaps];
/*  53: 53 */    look.residue_func = new FuncResidue[info.submaps];
/*  54:    */    
/*  55: 55 */    for (int i = 0; i < info.submaps; i++) {
/*  56: 56 */      int timenum = info.timesubmap[i];
/*  57: 57 */      int floornum = info.floorsubmap[i];
/*  58: 58 */      int resnum = info.residuesubmap[i];
/*  59:    */      
/*  60: 60 */      look.time_func[i] = FuncTime.time_P[vi.time_type[timenum]];
/*  61: 61 */      look.time_look[i] = look.time_func[i].look(vd, vm, vi.time_param[timenum]);
/*  62: 62 */      look.floor_func[i] = FuncFloor.floor_P[vi.floor_type[floornum]];
/*  63: 63 */      look.floor_look[i] = look.floor_func[i].look(vd, vm, vi.floor_param[floornum]);
/*  64:    */      
/*  65: 65 */      look.residue_func[i] = FuncResidue.residue_P[vi.residue_type[resnum]];
/*  66: 66 */      look.residue_look[i] = look.residue_func[i].look(vd, vm, vi.residue_param[resnum]);
/*  67:    */    }
/*  68:    */    
/*  71: 71 */    if ((vi.psys != 0) && (vd.analysisp != 0)) {}
/*  72:    */    
/*  75: 75 */    look.ch = vi.channels;
/*  76:    */    
/*  77: 77 */    return look;
/*  78:    */  }
/*  79:    */  
/*  80:    */  void pack(Info vi, Object imap, Buffer opb) {
/*  81: 81 */    InfoMapping0 info = (InfoMapping0)imap;
/*  82:    */    
/*  90: 90 */    if (info.submaps > 1) {
/*  91: 91 */      opb.write(1, 1);
/*  92: 92 */      opb.write(info.submaps - 1, 4);
/*  93:    */    }
/*  94:    */    else {
/*  95: 95 */      opb.write(0, 1);
/*  96:    */    }
/*  97:    */    
/*  98: 98 */    if (info.coupling_steps > 0) {
/*  99: 99 */      opb.write(1, 1);
/* 100:100 */      opb.write(info.coupling_steps - 1, 8);
/* 101:101 */      for (int i = 0; i < info.coupling_steps; i++) {
/* 102:102 */        opb.write(info.coupling_mag[i], Util.ilog2(vi.channels));
/* 103:103 */        opb.write(info.coupling_ang[i], Util.ilog2(vi.channels));
/* 104:    */      }
/* 105:    */    }
/* 106:    */    else {
/* 107:107 */      opb.write(0, 1);
/* 108:    */    }
/* 109:    */    
/* 110:110 */    opb.write(0, 2);
/* 111:    */    
/* 113:113 */    if (info.submaps > 1) {
/* 114:114 */      for (int i = 0; i < vi.channels; i++)
/* 115:115 */        opb.write(info.chmuxlist[i], 4);
/* 116:    */    }
/* 117:117 */    for (int i = 0; i < info.submaps; i++) {
/* 118:118 */      opb.write(info.timesubmap[i], 8);
/* 119:119 */      opb.write(info.floorsubmap[i], 8);
/* 120:120 */      opb.write(info.residuesubmap[i], 8);
/* 121:    */    }
/* 122:    */  }
/* 123:    */  
/* 124:    */  Object unpack(Info vi, Buffer opb)
/* 125:    */  {
/* 126:126 */    InfoMapping0 info = new InfoMapping0();
/* 127:    */    
/* 128:128 */    if (opb.read(1) != 0) {
/* 129:129 */      info.submaps = (opb.read(4) + 1);
/* 130:    */    }
/* 131:    */    else {
/* 132:132 */      info.submaps = 1;
/* 133:    */    }
/* 134:    */    
/* 135:135 */    if (opb.read(1) != 0) {
/* 136:136 */      info.coupling_steps = (opb.read(8) + 1);
/* 137:    */      
/* 138:138 */      for (int i = 0; i < info.coupling_steps; i++) {
/* 139:139 */        int testM = info.coupling_mag[i] = opb.read(Util.ilog2(vi.channels));
/* 140:140 */        int testA = info.coupling_ang[i] = opb.read(Util.ilog2(vi.channels));
/* 141:    */        
/* 142:142 */        if ((testM < 0) || (testA < 0) || (testM == testA) || (testM >= vi.channels) || (testA >= vi.channels))
/* 143:    */        {
/* 145:145 */          info.free();
/* 146:146 */          return null;
/* 147:    */        }
/* 148:    */      }
/* 149:    */    }
/* 150:    */    
/* 151:151 */    if (opb.read(2) > 0) {
/* 152:152 */      info.free();
/* 153:153 */      return null;
/* 154:    */    }
/* 155:    */    
/* 156:156 */    if (info.submaps > 1) {
/* 157:157 */      for (int i = 0; i < vi.channels; i++) {
/* 158:158 */        info.chmuxlist[i] = opb.read(4);
/* 159:159 */        if (info.chmuxlist[i] >= info.submaps) {
/* 160:160 */          info.free();
/* 161:161 */          return null;
/* 162:    */        }
/* 163:    */      }
/* 164:    */    }
/* 165:    */    
/* 166:166 */    for (int i = 0; i < info.submaps; i++) {
/* 167:167 */      info.timesubmap[i] = opb.read(8);
/* 168:168 */      if (info.timesubmap[i] >= vi.times) {
/* 169:169 */        info.free();
/* 170:170 */        return null;
/* 171:    */      }
/* 172:172 */      info.floorsubmap[i] = opb.read(8);
/* 173:173 */      if (info.floorsubmap[i] >= vi.floors) {
/* 174:174 */        info.free();
/* 175:175 */        return null;
/* 176:    */      }
/* 177:177 */      info.residuesubmap[i] = opb.read(8);
/* 178:178 */      if (info.residuesubmap[i] >= vi.residues) {
/* 179:179 */        info.free();
/* 180:180 */        return null;
/* 181:    */      }
/* 182:    */    }
/* 183:183 */    return info;
/* 184:    */  }
/* 185:    */  
/* 186:186 */  Mapping0() { this.pcmbundle = ((float[][])null);
/* 187:187 */    this.zerobundle = null;
/* 188:188 */    this.nonzero = null;
/* 189:189 */    this.floormemo = null;
/* 190:    */  }
/* 191:    */  
/* 192:192 */  synchronized int inverse(Block vb, Object l) { DspState vd = vb.vd;
/* 193:193 */    Info vi = vd.vi;
/* 194:194 */    LookMapping0 look = (LookMapping0)l;
/* 195:195 */    InfoMapping0 info = look.map;
/* 196:196 */    InfoMode mode = look.mode;
/* 197:197 */    int n = vb.pcmend = vi.blocksizes[vb.W];
/* 198:    */    
/* 199:199 */    float[] window = vd.window[vb.W][vb.lW][vb.nW][mode.windowtype];
/* 200:200 */    if ((this.pcmbundle == null) || (this.pcmbundle.length < vi.channels)) {
/* 201:201 */      this.pcmbundle = new float[vi.channels][];
/* 202:202 */      this.nonzero = new int[vi.channels];
/* 203:203 */      this.zerobundle = new int[vi.channels];
/* 204:204 */      this.floormemo = new Object[vi.channels];
/* 205:    */    }
/* 206:    */    
/* 213:213 */    for (int i = 0; i < vi.channels; i++) {
/* 214:214 */      float[] pcm = vb.pcm[i];
/* 215:215 */      int submap = info.chmuxlist[i];
/* 216:    */      
/* 217:217 */      this.floormemo[i] = look.floor_func[submap].inverse1(vb, look.floor_look[submap], this.floormemo[i]);
/* 218:    */      
/* 219:219 */      if (this.floormemo[i] != null) {
/* 220:220 */        this.nonzero[i] = 1;
/* 221:    */      }
/* 222:    */      else {
/* 223:223 */        this.nonzero[i] = 0;
/* 224:    */      }
/* 225:225 */      for (int j = 0; j < n / 2; j++) {
/* 226:226 */        pcm[j] = 0.0F;
/* 227:    */      }
/* 228:    */    }
/* 229:    */    
/* 231:231 */    for (int i = 0; i < info.coupling_steps; i++) {
/* 232:232 */      if ((this.nonzero[info.coupling_mag[i]] != 0) || (this.nonzero[info.coupling_ang[i]] != 0)) {
/* 233:233 */        this.nonzero[info.coupling_mag[i]] = 1;
/* 234:234 */        this.nonzero[info.coupling_ang[i]] = 1;
/* 235:    */      }
/* 236:    */    }
/* 237:    */    
/* 240:240 */    for (int i = 0; i < info.submaps; i++) {
/* 241:241 */      int ch_in_bundle = 0;
/* 242:242 */      for (int j = 0; j < vi.channels; j++) {
/* 243:243 */        if (info.chmuxlist[j] == i) {
/* 244:244 */          if (this.nonzero[j] != 0) {
/* 245:245 */            this.zerobundle[ch_in_bundle] = 1;
/* 246:    */          }
/* 247:    */          else {
/* 248:248 */            this.zerobundle[ch_in_bundle] = 0;
/* 249:    */          }
/* 250:250 */          this.pcmbundle[(ch_in_bundle++)] = vb.pcm[j];
/* 251:    */        }
/* 252:    */      }
/* 253:    */      
/* 254:254 */      look.residue_func[i].inverse(vb, look.residue_look[i], this.pcmbundle, this.zerobundle, ch_in_bundle);
/* 255:    */    }
/* 256:    */    
/* 258:258 */    for (int i = info.coupling_steps - 1; i >= 0; i--) {
/* 259:259 */      float[] pcmM = vb.pcm[info.coupling_mag[i]];
/* 260:260 */      float[] pcmA = vb.pcm[info.coupling_ang[i]];
/* 261:    */      
/* 262:262 */      for (int j = 0; j < n / 2; j++) {
/* 263:263 */        float mag = pcmM[j];
/* 264:264 */        float ang = pcmA[j];
/* 265:    */        
/* 266:266 */        if (mag > 0.0F) {
/* 267:267 */          if (ang > 0.0F) {
/* 268:268 */            pcmM[j] = mag;
/* 269:269 */            pcmA[j] = (mag - ang);
/* 270:    */          }
/* 271:    */          else {
/* 272:272 */            pcmA[j] = mag;
/* 273:273 */            pcmM[j] = (mag + ang);
/* 274:    */          }
/* 275:    */          
/* 276:    */        }
/* 277:277 */        else if (ang > 0.0F) {
/* 278:278 */          pcmM[j] = mag;
/* 279:279 */          pcmA[j] = (mag + ang);
/* 280:    */        }
/* 281:    */        else {
/* 282:282 */          pcmA[j] = mag;
/* 283:283 */          pcmM[j] = (mag - ang);
/* 284:    */        }
/* 285:    */      }
/* 286:    */    }
/* 287:    */    
/* 291:291 */    for (int i = 0; i < vi.channels; i++) {
/* 292:292 */      float[] pcm = vb.pcm[i];
/* 293:293 */      int submap = info.chmuxlist[i];
/* 294:294 */      look.floor_func[submap].inverse2(vb, look.floor_look[submap], this.floormemo[i], pcm);
/* 295:    */    }
/* 296:    */    
/* 301:301 */    for (int i = 0; i < vi.channels; i++) {
/* 302:302 */      float[] pcm = vb.pcm[i];
/* 303:    */      
/* 304:304 */      ((Mdct)vd.transform[vb.W][0]).backward(pcm, pcm);
/* 305:    */    }
/* 306:    */    
/* 311:311 */    for (int i = 0; i < vi.channels; i++) {
/* 312:312 */      float[] pcm = vb.pcm[i];
/* 313:313 */      if (this.nonzero[i] != 0) {
/* 314:314 */        for (int j = 0; j < n; j++) {
/* 315:315 */          pcm[j] *= window[j];
/* 316:    */        }
/* 317:    */        
/* 318:    */      } else {
/* 319:319 */        for (int j = 0; j < n; j++) {
/* 320:320 */          pcm[j] = 0.0F;
/* 321:    */        }
/* 322:    */      }
/* 323:    */    }
/* 324:    */    
/* 328:328 */    return 0; }
/* 329:    */  
/* 330:    */  class InfoMapping0 { int submaps;
/* 331:    */    
/* 332:    */    InfoMapping0() {}
/* 333:333 */    int[] chmuxlist = new int[256];
/* 334:    */    
/* 335:335 */    int[] timesubmap = new int[16];
/* 336:336 */    int[] floorsubmap = new int[16];
/* 337:337 */    int[] residuesubmap = new int[16];
/* 338:338 */    int[] psysubmap = new int[16];
/* 339:    */    int coupling_steps;
/* 340:    */    
/* 341:341 */    int[] coupling_mag = new int[256];
/* 342:342 */    int[] coupling_ang = new int[256];
/* 343:    */    
/* 344:    */    void free() {
/* 345:345 */      this.chmuxlist = null;
/* 346:346 */      this.timesubmap = null;
/* 347:347 */      this.floorsubmap = null;
/* 348:348 */      this.residuesubmap = null;
/* 349:349 */      this.psysubmap = null;
/* 350:    */      
/* 351:351 */      this.coupling_mag = null;
/* 352:352 */      this.coupling_ang = null;
/* 353:    */    }
/* 354:    */  }
/* 355:    */  
/* 356:    */  void free_info(Object imap) {}
/* 357:    */  
/* 358:    */  void free_look(Object imap) {}
/* 359:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.jcraft.jorbis.Mapping0
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */