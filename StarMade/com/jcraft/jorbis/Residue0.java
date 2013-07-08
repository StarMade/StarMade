/*   1:    */package com.jcraft.jorbis;
/*   2:    */
/*   3:    */import com.jcraft.jogg.Buffer;
/*   4:    */
/*  28:    */class Residue0
/*  29:    */  extends FuncResidue
/*  30:    */{
/*  31:    */  void pack(Object vr, Buffer opb)
/*  32:    */  {
/*  33: 33 */    InfoResidue0 info = (InfoResidue0)vr;
/*  34: 34 */    int acc = 0;
/*  35: 35 */    opb.write(info.begin, 24);
/*  36: 36 */    opb.write(info.end, 24);
/*  37:    */    
/*  38: 38 */    opb.write(info.grouping - 1, 24);
/*  39:    */    
/*  40: 40 */    opb.write(info.partitions - 1, 6);
/*  41: 41 */    opb.write(info.groupbook, 8);
/*  42:    */    
/*  46: 46 */    for (int j = 0; j < info.partitions; j++) {
/*  47: 47 */      int i = info.secondstages[j];
/*  48: 48 */      if (Util.ilog(i) > 3)
/*  49:    */      {
/*  50: 50 */        opb.write(i, 3);
/*  51: 51 */        opb.write(1, 1);
/*  52: 52 */        opb.write(i >>> 3, 5);
/*  53:    */      }
/*  54:    */      else {
/*  55: 55 */        opb.write(i, 4);
/*  56:    */      }
/*  57: 57 */      acc += Util.icount(i);
/*  58:    */    }
/*  59: 59 */    for (int j = 0; j < acc; j++) {
/*  60: 60 */      opb.write(info.booklist[j], 8);
/*  61:    */    }
/*  62:    */  }
/*  63:    */  
/*  64:    */  Object unpack(Info vi, Buffer opb) {
/*  65: 65 */    int acc = 0;
/*  66: 66 */    InfoResidue0 info = new InfoResidue0();
/*  67: 67 */    info.begin = opb.read(24);
/*  68: 68 */    info.end = opb.read(24);
/*  69: 69 */    info.grouping = (opb.read(24) + 1);
/*  70: 70 */    info.partitions = (opb.read(6) + 1);
/*  71: 71 */    info.groupbook = opb.read(8);
/*  72:    */    
/*  73: 73 */    for (int j = 0; j < info.partitions; j++) {
/*  74: 74 */      int cascade = opb.read(3);
/*  75: 75 */      if (opb.read(1) != 0) {
/*  76: 76 */        cascade |= opb.read(5) << 3;
/*  77:    */      }
/*  78: 78 */      info.secondstages[j] = cascade;
/*  79: 79 */      acc += Util.icount(cascade);
/*  80:    */    }
/*  81:    */    
/*  82: 82 */    for (int j = 0; j < acc; j++) {
/*  83: 83 */      info.booklist[j] = opb.read(8);
/*  84:    */    }
/*  85:    */    
/*  86: 86 */    if (info.groupbook >= vi.books) {
/*  87: 87 */      free_info(info);
/*  88: 88 */      return null;
/*  89:    */    }
/*  90:    */    
/*  91: 91 */    for (int j = 0; j < acc; j++) {
/*  92: 92 */      if (info.booklist[j] >= vi.books) {
/*  93: 93 */        free_info(info);
/*  94: 94 */        return null;
/*  95:    */      }
/*  96:    */    }
/*  97: 97 */    return info;
/*  98:    */  }
/*  99:    */  
/* 100:    */  Object look(DspState vd, InfoMode vm, Object vr) {
/* 101:101 */    InfoResidue0 info = (InfoResidue0)vr;
/* 102:102 */    LookResidue0 look = new LookResidue0();
/* 103:103 */    int acc = 0;
/* 104:    */    
/* 105:105 */    int maxstage = 0;
/* 106:106 */    look.info = info;
/* 107:107 */    look.map = vm.mapping;
/* 108:    */    
/* 109:109 */    look.parts = info.partitions;
/* 110:110 */    look.fullbooks = vd.fullbooks;
/* 111:111 */    look.phrasebook = vd.fullbooks[info.groupbook];
/* 112:    */    
/* 113:113 */    int dim = look.phrasebook.dim;
/* 114:    */    
/* 115:115 */    look.partbooks = new int[look.parts][];
/* 116:    */    
/* 117:117 */    for (int j = 0; j < look.parts; j++) {
/* 118:118 */      int i = info.secondstages[j];
/* 119:119 */      int stages = Util.ilog(i);
/* 120:120 */      if (stages != 0) {
/* 121:121 */        if (stages > maxstage)
/* 122:122 */          maxstage = stages;
/* 123:123 */        look.partbooks[j] = new int[stages];
/* 124:124 */        for (int k = 0; k < stages; k++) {
/* 125:125 */          if ((i & 1 << k) != 0) {
/* 126:126 */            look.partbooks[j][k] = info.booklist[(acc++)];
/* 127:    */          }
/* 128:    */        }
/* 129:    */      }
/* 130:    */    }
/* 131:    */    
/* 132:132 */    look.partvals = ((int)Math.rint(Math.pow(look.parts, dim)));
/* 133:133 */    look.stages = maxstage;
/* 134:134 */    look.decodemap = new int[look.partvals][];
/* 135:135 */    for (int j = 0; j < look.partvals; j++) {
/* 136:136 */      int val = j;
/* 137:137 */      int mult = look.partvals / look.parts;
/* 138:138 */      look.decodemap[j] = new int[dim];
/* 139:    */      
/* 140:140 */      for (int k = 0; k < dim; k++) {
/* 141:141 */        int deco = val / mult;
/* 142:142 */        val -= deco * mult;
/* 143:143 */        mult /= look.parts;
/* 144:144 */        look.decodemap[j][k] = deco;
/* 145:    */      }
/* 146:    */    }
/* 147:147 */    return look;
/* 148:    */  }
/* 149:    */  
/* 156:156 */  private static int[][][] _01inverse_partword = new int[2][][];
/* 157:    */  
/* 158:    */  void free_info(Object i) {}
/* 159:    */  
/* 160:    */  void free_look(Object i) {}
/* 161:    */  
/* 162:162 */  static synchronized int _01inverse(Block vb, Object vl, float[][] in, int ch, int decodepart) { LookResidue0 look = (LookResidue0)vl;
/* 163:163 */    InfoResidue0 info = look.info;
/* 164:    */    
/* 166:166 */    int samples_per_partition = info.grouping;
/* 167:167 */    int partitions_per_word = look.phrasebook.dim;
/* 168:168 */    int n = info.end - info.begin;
/* 169:    */    
/* 170:170 */    int partvals = n / samples_per_partition;
/* 171:171 */    int partwords = (partvals + partitions_per_word - 1) / partitions_per_word;
/* 172:    */    
/* 173:173 */    if (_01inverse_partword.length < ch) {
/* 174:174 */      _01inverse_partword = new int[ch][][];
/* 175:    */    }
/* 176:    */    
/* 177:177 */    for (int j = 0; j < ch; j++) {
/* 178:178 */      if ((_01inverse_partword[j] == null) || (_01inverse_partword[j].length < partwords)) {
/* 179:179 */        _01inverse_partword[j] = new int[partwords][];
/* 180:    */      }
/* 181:    */    }
/* 182:    */    
/* 183:183 */    for (int s = 0; s < look.stages; s++)
/* 184:    */    {
/* 186:186 */      int i = 0; for (int l = 0; i < partvals; l++) {
/* 187:187 */        if (s == 0)
/* 188:    */        {
/* 189:189 */          for (j = 0; j < ch; j++) {
/* 190:190 */            int temp = look.phrasebook.decode(vb.opb);
/* 191:191 */            if (temp == -1) {
/* 192:192 */              return 0;
/* 193:    */            }
/* 194:194 */            _01inverse_partword[j][l] = look.decodemap[temp];
/* 195:195 */            if (_01inverse_partword[j][l] == null) {
/* 196:196 */              return 0;
/* 197:    */            }
/* 198:    */          }
/* 199:    */        }
/* 200:    */        
/* 202:202 */        for (int k = 0; (k < partitions_per_word) && (i < partvals); i++) {
/* 203:203 */          for (j = 0; j < ch; j++) {
/* 204:204 */            int offset = info.begin + i * samples_per_partition;
/* 205:205 */            int index = _01inverse_partword[j][l][k];
/* 206:206 */            if ((info.secondstages[index] & 1 << s) != 0) {
/* 207:207 */              CodeBook stagebook = look.fullbooks[look.partbooks[index][s]];
/* 208:208 */              if (stagebook != null) {
/* 209:209 */                if (decodepart == 0) {
/* 210:210 */                  if (stagebook.decodevs_add(in[j], offset, vb.opb, samples_per_partition) == -1)
/* 211:    */                  {
/* 212:212 */                    return 0;
/* 213:    */                  }
/* 214:    */                }
/* 215:215 */                else if ((decodepart == 1) && 
/* 216:216 */                  (stagebook.decodev_add(in[j], offset, vb.opb, samples_per_partition) == -1))
/* 217:    */                {
/* 218:218 */                  return 0;
/* 219:    */                }
/* 220:    */              }
/* 221:    */            }
/* 222:    */          }
/* 223:202 */          k++;
/* 224:    */        }
/* 225:    */      }
/* 226:    */    }
/* 227:    */    
/* 247:226 */    return 0;
/* 248:    */  }
/* 249:    */  
/* 250:229 */  static int[][] _2inverse_partword = (int[][])null;
/* 251:    */  
/* 252:    */  static synchronized int _2inverse(Block vb, Object vl, float[][] in, int ch)
/* 253:    */  {
/* 254:233 */    LookResidue0 look = (LookResidue0)vl;
/* 255:234 */    InfoResidue0 info = look.info;
/* 256:    */    
/* 258:237 */    int samples_per_partition = info.grouping;
/* 259:238 */    int partitions_per_word = look.phrasebook.dim;
/* 260:239 */    int n = info.end - info.begin;
/* 261:    */    
/* 262:241 */    int partvals = n / samples_per_partition;
/* 263:242 */    int partwords = (partvals + partitions_per_word - 1) / partitions_per_word;
/* 264:    */    
/* 265:244 */    if ((_2inverse_partword == null) || (_2inverse_partword.length < partwords)) {
/* 266:245 */      _2inverse_partword = new int[partwords][];
/* 267:    */    }
/* 268:247 */    for (int s = 0; s < look.stages; s++) {
/* 269:248 */      int i = 0; for (int l = 0; i < partvals; l++) {
/* 270:249 */        if (s == 0)
/* 271:    */        {
/* 272:251 */          int temp = look.phrasebook.decode(vb.opb);
/* 273:252 */          if (temp == -1) {
/* 274:253 */            return 0;
/* 275:    */          }
/* 276:255 */          _2inverse_partword[l] = look.decodemap[temp];
/* 277:256 */          if (_2inverse_partword[l] == null) {
/* 278:257 */            return 0;
/* 279:    */          }
/* 280:    */        }
/* 281:    */        
/* 283:262 */        for (int k = 0; (k < partitions_per_word) && (i < partvals); i++) {
/* 284:263 */          int offset = info.begin + i * samples_per_partition;
/* 285:264 */          int index = _2inverse_partword[l][k];
/* 286:265 */          if ((info.secondstages[index] & 1 << s) != 0) {
/* 287:266 */            CodeBook stagebook = look.fullbooks[look.partbooks[index][s]];
/* 288:267 */            if ((stagebook != null) && 
/* 289:268 */              (stagebook.decodevv_add(in, offset, ch, vb.opb, samples_per_partition) == -1))
/* 290:    */            {
/* 291:270 */              return 0;
/* 292:    */            }
/* 293:    */          }
/* 294:262 */          k++;
/* 295:    */        }
/* 296:    */      }
/* 297:    */    }
/* 298:    */    
/* 309:277 */    return 0;
/* 310:    */  }
/* 311:    */  
/* 312:    */  int inverse(Block vb, Object vl, float[][] in, int[] nonzero, int ch) {
/* 313:281 */    int used = 0;
/* 314:282 */    for (int i = 0; i < ch; i++) {
/* 315:283 */      if (nonzero[i] != 0) {
/* 316:284 */        in[(used++)] = in[i];
/* 317:    */      }
/* 318:    */    }
/* 319:287 */    if (used != 0) {
/* 320:288 */      return _01inverse(vb, vl, in, used, 0);
/* 321:    */    }
/* 322:290 */    return 0;
/* 323:    */  }
/* 324:    */  
/* 329:    */  class InfoResidue0
/* 330:    */  {
/* 331:    */    int begin;
/* 332:    */    
/* 336:    */    int end;
/* 337:    */    
/* 340:    */    int grouping;
/* 341:    */    
/* 344:    */    int partitions;
/* 345:    */    
/* 348:    */    int groupbook;
/* 349:    */    
/* 352:320 */    int[] secondstages = new int[64];
/* 353:321 */    int[] booklist = new int[256];
/* 354:    */    
/* 356:324 */    float[] entmax = new float[64];
/* 357:325 */    float[] ampmax = new float[64];
/* 358:326 */    int[] subgrp = new int[64];
/* 359:327 */    int[] blimit = new int[64];
/* 360:    */    
/* 361:    */    InfoResidue0() {}
/* 362:    */  }
/* 363:    */  
/* 364:    */  class LookResidue0
/* 365:    */  {
/* 366:    */    Residue0.InfoResidue0 info;
/* 367:    */    int map;
/* 368:    */    int parts;
/* 369:    */    int stages;
/* 370:    */    CodeBook[] fullbooks;
/* 371:    */    CodeBook phrasebook;
/* 372:    */    int[][] partbooks;
/* 373:    */    int partvals;
/* 374:    */    int[][] decodemap;
/* 375:    */    int postbits;
/* 376:    */    int phrasebits;
/* 377:    */    int frames;
/* 378:    */    
/* 379:    */    LookResidue0() {}
/* 380:    */  }
/* 381:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.jcraft.jorbis.Residue0
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */