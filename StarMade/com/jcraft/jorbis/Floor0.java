/*   1:    */package com.jcraft.jorbis;
/*   2:    */
/*   3:    */import com.jcraft.jogg.Buffer;
/*   4:    */
/*  27:    */class Floor0
/*  28:    */  extends FuncFloor
/*  29:    */{
/*  30:    */  float[] lsp;
/*  31:    */  
/*  32:    */  void pack(Object i, Buffer opb)
/*  33:    */  {
/*  34: 34 */    InfoFloor0 info = (InfoFloor0)i;
/*  35: 35 */    opb.write(info.order, 8);
/*  36: 36 */    opb.write(info.rate, 16);
/*  37: 37 */    opb.write(info.barkmap, 16);
/*  38: 38 */    opb.write(info.ampbits, 6);
/*  39: 39 */    opb.write(info.ampdB, 8);
/*  40: 40 */    opb.write(info.numbooks - 1, 4);
/*  41: 41 */    for (int j = 0; j < info.numbooks; j++)
/*  42: 42 */      opb.write(info.books[j], 8);
/*  43:    */  }
/*  44:    */  
/*  45:    */  Object unpack(Info vi, Buffer opb) {
/*  46: 46 */    InfoFloor0 info = new InfoFloor0();
/*  47: 47 */    info.order = opb.read(8);
/*  48: 48 */    info.rate = opb.read(16);
/*  49: 49 */    info.barkmap = opb.read(16);
/*  50: 50 */    info.ampbits = opb.read(6);
/*  51: 51 */    info.ampdB = opb.read(8);
/*  52: 52 */    info.numbooks = (opb.read(4) + 1);
/*  53:    */    
/*  54: 54 */    if ((info.order < 1) || (info.rate < 1) || (info.barkmap < 1) || (info.numbooks < 1)) {
/*  55: 55 */      return null;
/*  56:    */    }
/*  57:    */    
/*  58: 58 */    for (int j = 0; j < info.numbooks; j++) {
/*  59: 59 */      info.books[j] = opb.read(8);
/*  60: 60 */      if ((info.books[j] < 0) || (info.books[j] >= vi.books)) {
/*  61: 61 */        return null;
/*  62:    */      }
/*  63:    */    }
/*  64: 64 */    return info;
/*  65:    */  }
/*  66:    */  
/*  67:    */  Object look(DspState vd, InfoMode mi, Object i)
/*  68:    */  {
/*  69: 69 */    Info vi = vd.vi;
/*  70: 70 */    InfoFloor0 info = (InfoFloor0)i;
/*  71: 71 */    LookFloor0 look = new LookFloor0();
/*  72: 72 */    look.m = info.order;
/*  73: 73 */    look.n = (vi.blocksizes[mi.blockflag] / 2);
/*  74: 74 */    look.ln = info.barkmap;
/*  75: 75 */    look.vi = info;
/*  76: 76 */    look.lpclook.init(look.ln, look.m);
/*  77:    */    
/*  79: 79 */    float scale = look.ln / toBARK((float)(info.rate / 2.0D));
/*  80:    */    
/*  87: 87 */    look.linearmap = new int[look.n];
/*  88: 88 */    for (int j = 0; j < look.n; j++) {
/*  89: 89 */      int val = (int)Math.floor(toBARK((float)(info.rate / 2.0D / look.n * j)) * scale);
/*  90: 90 */      if (val >= look.ln)
/*  91: 91 */        val = look.ln;
/*  92: 92 */      look.linearmap[j] = val;
/*  93:    */    }
/*  94: 94 */    return look;
/*  95:    */  }
/*  96:    */  
/*  97:    */  static float toBARK(float f) {
/*  98: 98 */    return (float)(13.1D * Math.atan(0.00074D * f) + 2.24D * Math.atan(f * f * 1.85E-008D) + 0.0001D * f);
/*  99:    */  }
/* 100:    */  
/* 101:    */  Object state(Object i) {
/* 102:102 */    EchstateFloor0 state = new EchstateFloor0();
/* 103:103 */    InfoFloor0 info = (InfoFloor0)i;
/* 104:    */    
/* 106:106 */    state.codewords = new int[info.order];
/* 107:107 */    state.curve = new float[info.barkmap];
/* 108:108 */    state.frameno = -1L;
/* 109:109 */    return state;
/* 110:    */  }
/* 111:    */  
/* 120:    */  int forward(Block vb, Object i, float[] in, float[] out, Object vs)
/* 121:    */  {
/* 122:122 */    return 0;
/* 123:    */  }
/* 124:    */  
/* 125:125 */  Floor0() { this.lsp = null; }
/* 126:    */  
/* 127:    */  int inverse(Block vb, Object i, float[] out)
/* 128:    */  {
/* 129:129 */    LookFloor0 look = (LookFloor0)i;
/* 130:130 */    InfoFloor0 info = look.vi;
/* 131:131 */    int ampraw = vb.opb.read(info.ampbits);
/* 132:132 */    if (ampraw > 0) {
/* 133:133 */      int maxval = (1 << info.ampbits) - 1;
/* 134:134 */      float amp = ampraw / maxval * info.ampdB;
/* 135:135 */      int booknum = vb.opb.read(Util.ilog(info.numbooks));
/* 136:    */      
/* 137:137 */      if ((booknum != -1) && (booknum < info.numbooks))
/* 138:    */      {
/* 139:139 */        synchronized (this) {
/* 140:140 */          if ((this.lsp == null) || (this.lsp.length < look.m)) {
/* 141:141 */            this.lsp = new float[look.m];
/* 142:    */          }
/* 143:    */          else {
/* 144:144 */            for (int j = 0; j < look.m; j++) {
/* 145:145 */              this.lsp[j] = 0.0F;
/* 146:    */            }
/* 147:    */          }
/* 148:148 */          CodeBook b = vb.vd.fullbooks[info.books[booknum]];
/* 149:149 */          float last = 0.0F;
/* 150:    */          
/* 151:151 */          for (int j = 0; j < look.m; j++) {
/* 152:152 */            out[j] = 0.0F;
/* 153:    */          }
/* 154:154 */          for (int j = 0; j < look.m; j += b.dim) {
/* 155:155 */            if (b.decodevs(this.lsp, j, vb.opb, 1, -1) == -1) {
/* 156:156 */              for (int k = 0; k < look.n; k++)
/* 157:157 */                out[k] = 0.0F;
/* 158:158 */              return 0;
/* 159:    */            }
/* 160:    */          }
/* 161:161 */          for (int j = 0; j < look.m;) {
/* 162:162 */            for (int k = 0; k < b.dim; j++) {
/* 163:163 */              this.lsp[j] += last;k++;
/* 164:    */            }
/* 165:164 */            last = this.lsp[(j - 1)];
/* 166:    */          }
/* 167:    */          
/* 168:167 */          Lsp.lsp_to_curve(out, look.linearmap, look.n, look.ln, this.lsp, look.m, amp, info.ampdB);
/* 169:    */          
/* 171:170 */          return 1;
/* 172:    */        }
/* 173:    */      }
/* 174:    */    }
/* 175:174 */    return 0;
/* 176:    */  }
/* 177:    */  
/* 178:    */  Object inverse1(Block vb, Object i, Object memo) {
/* 179:178 */    LookFloor0 look = (LookFloor0)i;
/* 180:179 */    InfoFloor0 info = look.vi;
/* 181:180 */    float[] lsp = null;
/* 182:181 */    if ((memo instanceof float[])) {
/* 183:182 */      lsp = (float[])memo;
/* 184:    */    }
/* 185:    */    
/* 186:185 */    int ampraw = vb.opb.read(info.ampbits);
/* 187:186 */    if (ampraw > 0) {
/* 188:187 */      int maxval = (1 << info.ampbits) - 1;
/* 189:188 */      float amp = ampraw / maxval * info.ampdB;
/* 190:189 */      int booknum = vb.opb.read(Util.ilog(info.numbooks));
/* 191:    */      
/* 192:191 */      if ((booknum != -1) && (booknum < info.numbooks)) {
/* 193:192 */        CodeBook b = vb.vd.fullbooks[info.books[booknum]];
/* 194:193 */        float last = 0.0F;
/* 195:    */        
/* 196:195 */        if ((lsp == null) || (lsp.length < look.m + 1)) {
/* 197:196 */          lsp = new float[look.m + 1];
/* 198:    */        }
/* 199:    */        else {
/* 200:199 */          for (int j = 0; j < lsp.length; j++) {
/* 201:200 */            lsp[j] = 0.0F;
/* 202:    */          }
/* 203:    */        }
/* 204:203 */        for (int j = 0; j < look.m; j += b.dim) {
/* 205:204 */          if (b.decodev_set(lsp, j, vb.opb, b.dim) == -1) {
/* 206:205 */            return null;
/* 207:    */          }
/* 208:    */        }
/* 209:    */        
/* 210:209 */        for (int j = 0; j < look.m;) {
/* 211:210 */          for (int k = 0; k < b.dim; j++) {
/* 212:211 */            lsp[j] += last;k++;
/* 213:    */          }
/* 214:212 */          last = lsp[(j - 1)];
/* 215:    */        }
/* 216:214 */        lsp[look.m] = amp;
/* 217:215 */        return lsp;
/* 218:    */      }
/* 219:    */    }
/* 220:218 */    return null;
/* 221:    */  }
/* 222:    */  
/* 223:    */  int inverse2(Block vb, Object i, Object memo, float[] out) {
/* 224:222 */    LookFloor0 look = (LookFloor0)i;
/* 225:223 */    InfoFloor0 info = look.vi;
/* 226:    */    
/* 227:225 */    if (memo != null) {
/* 228:226 */      float[] lsp = (float[])memo;
/* 229:227 */      float amp = lsp[look.m];
/* 230:    */      
/* 231:229 */      Lsp.lsp_to_curve(out, look.linearmap, look.n, look.ln, lsp, look.m, amp, info.ampdB);
/* 232:    */      
/* 233:231 */      return 1;
/* 234:    */    }
/* 235:233 */    for (int j = 0; j < look.n; j++) {
/* 236:234 */      out[j] = 0.0F;
/* 237:    */    }
/* 238:236 */    return 0;
/* 239:    */  }
/* 240:    */  
/* 241:    */  static float fromdB(float x) {
/* 242:240 */    return (float)Math.exp(x * 0.11512925D);
/* 243:    */  }
/* 244:    */  
/* 245:    */  static void lsp_to_lpc(float[] lsp, float[] lpc, int m) {
/* 246:244 */    int m2 = m / 2;
/* 247:245 */    float[] O = new float[m2];
/* 248:246 */    float[] E = new float[m2];
/* 249:    */    
/* 250:248 */    float[] Ae = new float[m2 + 1];
/* 251:249 */    float[] Ao = new float[m2 + 1];
/* 252:    */    
/* 253:251 */    float[] Be = new float[m2];
/* 254:252 */    float[] Bo = new float[m2];
/* 255:    */    
/* 258:256 */    for (int i = 0; i < m2; i++) {
/* 259:257 */      O[i] = ((float)(-2.0D * Math.cos(lsp[(i * 2)])));
/* 260:258 */      E[i] = ((float)(-2.0D * Math.cos(lsp[(i * 2 + 1)])));
/* 261:    */    }
/* 262:    */    
/* 264:262 */    for (int j = 0; j < m2; j++) {
/* 265:263 */      Ae[j] = 0.0F;
/* 266:264 */      Ao[j] = 1.0F;
/* 267:265 */      Be[j] = 0.0F;
/* 268:266 */      Bo[j] = 1.0F;
/* 269:    */    }
/* 270:268 */    Ao[j] = 1.0F;
/* 271:269 */    Ae[j] = 1.0F;
/* 272:    */    
/* 274:272 */    for (i = 1; i < m + 1; i++) { float B;
/* 275:273 */      float A = B = 0.0F;
/* 276:274 */      for (j = 0; j < m2; j++) {
/* 277:275 */        float temp = O[j] * Ao[j] + Ae[j];
/* 278:276 */        Ae[j] = Ao[j];
/* 279:277 */        Ao[j] = A;
/* 280:278 */        A += temp;
/* 281:    */        
/* 282:280 */        temp = E[j] * Bo[j] + Be[j];
/* 283:281 */        Be[j] = Bo[j];
/* 284:282 */        Bo[j] = B;
/* 285:283 */        B += temp;
/* 286:    */      }
/* 287:285 */      lpc[(i - 1)] = ((A + Ao[j] + B - Ae[j]) / 2.0F);
/* 288:286 */      Ao[j] = A;
/* 289:287 */      Ae[j] = B;
/* 290:    */    }
/* 291:    */  }
/* 292:    */  
/* 294:    */  static void lpc_to_curve(float[] curve, float[] lpc, float amp, LookFloor0 l, String name, int frameno)
/* 295:    */  {
/* 296:294 */    float[] lcurve = new float[Math.max(l.ln * 2, l.m * 2 + 2)];
/* 297:    */    
/* 298:296 */    if (amp == 0.0F) {
/* 299:297 */      for (int j = 0; j < l.n; j++)
/* 300:298 */        curve[j] = 0.0F;
/* 301:299 */      return;
/* 302:    */    }
/* 303:301 */    l.lpclook.lpc_to_curve(lcurve, lpc, amp);
/* 304:    */    
/* 305:303 */    for (int i = 0; i < l.n; i++)
/* 306:304 */      curve[i] = lcurve[l.linearmap[i]];
/* 307:    */  }
/* 308:    */  
/* 309:    */  void free_info(Object i) {}
/* 310:    */  
/* 311:    */  class InfoFloor0 {
/* 312:    */    int order;
/* 313:    */    int rate;
/* 314:    */    int barkmap;
/* 315:    */    int ampbits;
/* 316:    */    int ampdB;
/* 317:    */    int numbooks;
/* 318:316 */    int[] books = new int[16];
/* 319:    */    
/* 320:    */    InfoFloor0() {}
/* 321:    */  }
/* 322:    */  
/* 323:    */  class LookFloor0 { int n;
/* 324:    */    int ln;
/* 325:    */    int m;
/* 326:    */    int[] linearmap;
/* 327:    */    Floor0.InfoFloor0 vi;
/* 328:326 */    Lpc lpclook = new Lpc();
/* 329:    */    
/* 330:    */    LookFloor0() {}
/* 331:    */  }
/* 332:    */  
/* 333:    */  void free_look(Object i) {}
/* 334:    */  
/* 335:    */  void free_state(Object vs) {}
/* 336:    */  
/* 337:    */  class EchstateFloor0
/* 338:    */  {
/* 339:    */    int[] codewords;
/* 340:    */    float[] curve;
/* 341:    */    long frameno;
/* 342:    */    long codes;
/* 343:    */    
/* 344:    */    EchstateFloor0() {}
/* 345:    */  }
/* 346:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.jcraft.jorbis.Floor0
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */