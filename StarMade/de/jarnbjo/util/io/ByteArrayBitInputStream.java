/*   1:    */package de.jarnbjo.util.io;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */
/*  39:    */public class ByteArrayBitInputStream
/*  40:    */  implements BitInputStream
/*  41:    */{
/*  42:    */  private byte[] source;
/*  43:    */  private byte currentByte;
/*  44:    */  private int endian;
/*  45: 45 */  private int byteIndex = 0;
/*  46: 46 */  private int bitIndex = 0;
/*  47:    */  
/*  48:    */  public ByteArrayBitInputStream(byte[] source) {
/*  49: 49 */    this(source, 0);
/*  50:    */  }
/*  51:    */  
/*  52:    */  public ByteArrayBitInputStream(byte[] source, int endian) {
/*  53: 53 */    this.endian = endian;
/*  54: 54 */    this.source = source;
/*  55: 55 */    this.currentByte = source[0];
/*  56: 56 */    this.bitIndex = (endian == 0 ? 0 : 7);
/*  57:    */  }
/*  58:    */  
/*  59:    */  public boolean getBit() throws IOException {
/*  60: 60 */    if (this.endian == 0) {
/*  61: 61 */      if (this.bitIndex > 7) {
/*  62: 62 */        this.bitIndex = 0;
/*  63: 63 */        this.currentByte = this.source[(++this.byteIndex)];
/*  64:    */      }
/*  65: 65 */      return (this.currentByte & 1 << this.bitIndex++) != 0;
/*  66:    */    }
/*  67:    */    
/*  68: 68 */    if (this.bitIndex < 0) {
/*  69: 69 */      this.bitIndex = 7;
/*  70: 70 */      this.currentByte = this.source[(++this.byteIndex)];
/*  71:    */    }
/*  72: 72 */    return (this.currentByte & 1 << this.bitIndex--) != 0;
/*  73:    */  }
/*  74:    */  
/*  75:    */  public int getInt(int bits) throws IOException
/*  76:    */  {
/*  77: 77 */    if (bits > 32) {
/*  78: 78 */      throw new IllegalArgumentException("Argument \"bits\" must be <= 32");
/*  79:    */    }
/*  80: 80 */    int res = 0;
/*  81: 81 */    if (this.endian == 0) {
/*  82: 82 */      for (int i = 0; i < bits; i++) {
/*  83: 83 */        if (getBit()) {
/*  84: 84 */          res |= 1 << i;
/*  85:    */        }
/*  86:    */      }
/*  87:    */    }
/*  88:    */    else {
/*  89: 89 */      if (this.bitIndex < 0) {
/*  90: 90 */        this.bitIndex = 7;
/*  91: 91 */        this.currentByte = this.source[(++this.byteIndex)];
/*  92:    */      }
/*  93: 93 */      if (bits <= this.bitIndex + 1) {
/*  94: 94 */        int ci = this.currentByte & 0xFF;
/*  95: 95 */        int offset = 1 + this.bitIndex - bits;
/*  96: 96 */        int mask = (1 << bits) - 1 << offset;
/*  97: 97 */        res = (ci & mask) >> offset;
/*  98: 98 */        this.bitIndex -= bits;
/*  99:    */      }
/* 100:    */      else {
/* 101:101 */        res = (this.currentByte & 0xFF & (1 << this.bitIndex + 1) - 1) << bits - this.bitIndex - 1;
/* 102:102 */        bits -= this.bitIndex + 1;
/* 103:103 */        this.currentByte = this.source[(++this.byteIndex)];
/* 104:104 */        while (bits >= 8) {
/* 105:105 */          bits -= 8;
/* 106:106 */          res |= (this.source[this.byteIndex] & 0xFF) << bits;
/* 107:107 */          this.currentByte = this.source[(++this.byteIndex)];
/* 108:    */        }
/* 109:109 */        if (bits > 0) {
/* 110:110 */          int ci = this.source[this.byteIndex] & 0xFF;
/* 111:111 */          res |= ci >> 8 - bits & (1 << bits) - 1;
/* 112:112 */          this.bitIndex = (7 - bits);
/* 113:    */        }
/* 114:    */        else {
/* 115:115 */          this.currentByte = this.source[(--this.byteIndex)];
/* 116:116 */          this.bitIndex = -1;
/* 117:    */        }
/* 118:    */      }
/* 119:    */    }
/* 120:    */    
/* 121:121 */    return res;
/* 122:    */  }
/* 123:    */  
/* 124:    */  public int getSignedInt(int bits) throws IOException {
/* 125:125 */    int raw = getInt(bits);
/* 126:126 */    if (raw >= 1 << bits - 1) {
/* 127:127 */      raw -= (1 << bits);
/* 128:    */    }
/* 129:129 */    return raw;
/* 130:    */  }
/* 131:    */  
/* 132:    */  public int getInt(HuffmanNode root) throws IOException {
/* 133:133 */    while (root.value == null) {
/* 134:134 */      if (this.bitIndex > 7) {
/* 135:135 */        this.bitIndex = 0;
/* 136:136 */        this.currentByte = this.source[(++this.byteIndex)];
/* 137:    */      }
/* 138:138 */      root = (this.currentByte & 1 << this.bitIndex++) != 0 ? root.o1 : root.o0;
/* 139:    */    }
/* 140:140 */    return root.value.intValue();
/* 141:    */  }
/* 142:    */  
/* 143:    */  public long getLong(int bits) throws IOException {
/* 144:144 */    if (bits > 64) {
/* 145:145 */      throw new IllegalArgumentException("Argument \"bits\" must be <= 64");
/* 146:    */    }
/* 147:147 */    long res = 0L;
/* 148:148 */    if (this.endian == 0) {
/* 149:149 */      for (int i = 0; i < bits; i++) {
/* 150:150 */        if (getBit()) {
/* 151:151 */          res |= 1L << i;
/* 152:    */        }
/* 153:    */        
/* 154:    */      }
/* 155:    */    } else {
/* 156:156 */      for (int i = bits - 1; i >= 0; i--) {
/* 157:157 */        if (getBit()) {
/* 158:158 */          res |= 1L << i;
/* 159:    */        }
/* 160:    */      }
/* 161:    */    }
/* 162:162 */    return res;
/* 163:    */  }
/* 164:    */  
/* 177:    */  public int readSignedRice(int order)
/* 178:    */    throws IOException
/* 179:    */  {
/* 180:180 */    int msbs = -1;int lsbs = 0;int res = 0;
/* 181:    */    
/* 182:182 */    if (this.endian == 0)
/* 183:    */    {
/* 184:184 */      throw new UnsupportedOperationException("ByteArrayBitInputStream.readSignedRice() is only supported in big endian mode");
/* 185:    */    }
/* 186:    */    
/* 189:189 */    byte cb = this.source[this.byteIndex];
/* 190:    */    do {
/* 191:191 */      msbs++;
/* 192:192 */      if (this.bitIndex < 0) {
/* 193:193 */        this.bitIndex = 7;
/* 194:194 */        this.byteIndex += 1;
/* 195:195 */        cb = this.source[this.byteIndex];
/* 196:    */      }
/* 197:197 */    } while ((cb & 1 << this.bitIndex--) == 0);
/* 198:    */    
/* 199:199 */    int bits = order;
/* 200:    */    
/* 201:201 */    if (this.bitIndex < 0) {
/* 202:202 */      this.bitIndex = 7;
/* 203:203 */      this.byteIndex += 1;
/* 204:    */    }
/* 205:205 */    if (bits <= this.bitIndex + 1) {
/* 206:206 */      int ci = this.source[this.byteIndex] & 0xFF;
/* 207:207 */      int offset = 1 + this.bitIndex - bits;
/* 208:208 */      int mask = (1 << bits) - 1 << offset;
/* 209:209 */      lsbs = (ci & mask) >> offset;
/* 210:210 */      this.bitIndex -= bits;
/* 211:    */    }
/* 212:    */    else {
/* 213:213 */      lsbs = (this.source[this.byteIndex] & 0xFF & (1 << this.bitIndex + 1) - 1) << bits - this.bitIndex - 1;
/* 214:214 */      bits -= this.bitIndex + 1;
/* 215:215 */      this.byteIndex += 1;
/* 216:216 */      while (bits >= 8) {
/* 217:217 */        bits -= 8;
/* 218:218 */        lsbs |= (this.source[this.byteIndex] & 0xFF) << bits;
/* 219:219 */        this.byteIndex += 1;
/* 220:    */      }
/* 221:221 */      if (bits > 0) {
/* 222:222 */        int ci = this.source[this.byteIndex] & 0xFF;
/* 223:223 */        lsbs |= ci >> 8 - bits & (1 << bits) - 1;
/* 224:224 */        this.bitIndex = (7 - bits);
/* 225:    */      }
/* 226:    */      else {
/* 227:227 */        this.byteIndex -= 1;
/* 228:228 */        this.bitIndex = -1;
/* 229:    */      }
/* 230:    */    }
/* 231:    */    
/* 232:232 */    res = msbs << order | lsbs;
/* 233:    */    
/* 235:235 */    return (res & 0x1) == 1 ? -(res >> 1) - 1 : res >> 1;
/* 236:    */  }
/* 237:    */  
/* 254:    */  public void readSignedRice(int order, int[] buffer, int off, int len)
/* 255:    */    throws IOException
/* 256:    */  {
/* 257:257 */    if (this.endian == 0)
/* 258:    */    {
/* 259:259 */      throw new UnsupportedOperationException("ByteArrayBitInputStream.readSignedRice() is only supported in big endian mode");
/* 260:    */    }
/* 261:    */    
/* 263:263 */    for (int i = off; i < off + len; i++)
/* 264:    */    {
/* 265:265 */      int msbs = -1;int lsbs = 0;
/* 266:    */      
/* 267:267 */      byte cb = this.source[this.byteIndex];
/* 268:    */      do {
/* 269:269 */        msbs++;
/* 270:270 */        if (this.bitIndex < 0) {
/* 271:271 */          this.bitIndex = 7;
/* 272:272 */          this.byteIndex += 1;
/* 273:273 */          cb = this.source[this.byteIndex];
/* 274:    */        }
/* 275:275 */      } while ((cb & 1 << this.bitIndex--) == 0);
/* 276:    */      
/* 277:277 */      int bits = order;
/* 278:    */      
/* 279:279 */      if (this.bitIndex < 0) {
/* 280:280 */        this.bitIndex = 7;
/* 281:281 */        this.byteIndex += 1;
/* 282:    */      }
/* 283:283 */      if (bits <= this.bitIndex + 1) {
/* 284:284 */        int ci = this.source[this.byteIndex] & 0xFF;
/* 285:285 */        int offset = 1 + this.bitIndex - bits;
/* 286:286 */        int mask = (1 << bits) - 1 << offset;
/* 287:287 */        lsbs = (ci & mask) >> offset;
/* 288:288 */        this.bitIndex -= bits;
/* 289:    */      }
/* 290:    */      else {
/* 291:291 */        lsbs = (this.source[this.byteIndex] & 0xFF & (1 << this.bitIndex + 1) - 1) << bits - this.bitIndex - 1;
/* 292:292 */        bits -= this.bitIndex + 1;
/* 293:293 */        this.byteIndex += 1;
/* 294:294 */        while (bits >= 8) {
/* 295:295 */          bits -= 8;
/* 296:296 */          lsbs |= (this.source[this.byteIndex] & 0xFF) << bits;
/* 297:297 */          this.byteIndex += 1;
/* 298:    */        }
/* 299:299 */        if (bits > 0) {
/* 300:300 */          int ci = this.source[this.byteIndex] & 0xFF;
/* 301:301 */          lsbs |= ci >> 8 - bits & (1 << bits) - 1;
/* 302:302 */          this.bitIndex = (7 - bits);
/* 303:    */        }
/* 304:    */        else {
/* 305:305 */          this.byteIndex -= 1;
/* 306:306 */          this.bitIndex = -1;
/* 307:    */        }
/* 308:    */      }
/* 309:    */      
/* 310:310 */      int res = msbs << order | lsbs;
/* 311:311 */      buffer[i] = ((res & 0x1) == 1 ? -(res >> 1) - 1 : res >> 1);
/* 312:    */    }
/* 313:    */  }
/* 314:    */  
/* 315:    */  public void align()
/* 316:    */  {
/* 317:317 */    if ((this.endian == 1) && (this.bitIndex >= 0)) {
/* 318:318 */      this.bitIndex = 7;
/* 319:319 */      this.byteIndex += 1;
/* 320:    */    }
/* 321:321 */    else if ((this.endian == 0) && (this.bitIndex <= 7)) {
/* 322:322 */      this.bitIndex = 0;
/* 323:323 */      this.byteIndex += 1;
/* 324:    */    }
/* 325:    */  }
/* 326:    */  
/* 327:    */  public void setEndian(int endian) {
/* 328:328 */    if ((this.endian == 1) && (endian == 0)) {
/* 329:329 */      this.bitIndex = 0;
/* 330:330 */      this.byteIndex += 1;
/* 331:    */    }
/* 332:332 */    else if ((this.endian == 0) && (endian == 1)) {
/* 333:333 */      this.bitIndex = 7;
/* 334:334 */      this.byteIndex += 1;
/* 335:    */    }
/* 336:336 */    this.endian = endian;
/* 337:    */  }
/* 338:    */  
/* 342:    */  public byte[] getSource()
/* 343:    */  {
/* 344:344 */    return this.source;
/* 345:    */  }
/* 346:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     de.jarnbjo.util.io.ByteArrayBitInputStream
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */