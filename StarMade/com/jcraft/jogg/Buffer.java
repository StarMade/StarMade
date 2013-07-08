/*   1:    */package com.jcraft.jogg;
/*   2:    */
/*   3:    */import java.io.PrintStream;
/*   4:    */
/*  29:    */public class Buffer
/*  30:    */{
/*  31:    */  private static final int BUFFER_INCREMENT = 256;
/*  32: 32 */  private static final int[] mask = { 0, 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767, 65535, 131071, 262143, 524287, 1048575, 2097151, 4194303, 8388607, 16777215, 33554431, 67108863, 134217727, 268435455, 536870911, 1073741823, 2147483647, -1 };
/*  33:    */  
/*  39: 39 */  int ptr = 0;
/*  40: 40 */  byte[] buffer = null;
/*  41: 41 */  int endbit = 0;
/*  42: 42 */  int endbyte = 0;
/*  43: 43 */  int storage = 0;
/*  44:    */  
/*  45:    */  public void writeinit() {
/*  46: 46 */    this.buffer = new byte[256];
/*  47: 47 */    this.ptr = 0;
/*  48: 48 */    this.buffer[0] = 0;
/*  49: 49 */    this.storage = 256;
/*  50:    */  }
/*  51:    */  
/*  52:    */  public void write(byte[] s) {
/*  53: 53 */    for (int i = 0; i < s.length; i++) {
/*  54: 54 */      if (s[i] == 0)
/*  55:    */        break;
/*  56: 56 */      write(s[i], 8);
/*  57:    */    }
/*  58:    */  }
/*  59:    */  
/*  60:    */  public void read(byte[] s, int bytes) {
/*  61: 61 */    int i = 0;
/*  62: 62 */    while (bytes-- != 0) {
/*  63: 63 */      s[(i++)] = ((byte)read(8));
/*  64:    */    }
/*  65:    */  }
/*  66:    */  
/*  67:    */  void reset() {
/*  68: 68 */    this.ptr = 0;
/*  69: 69 */    this.buffer[0] = 0;
/*  70: 70 */    this.endbit = (this.endbyte = 0);
/*  71:    */  }
/*  72:    */  
/*  73:    */  public void writeclear() {
/*  74: 74 */    this.buffer = null;
/*  75:    */  }
/*  76:    */  
/*  77:    */  public void readinit(byte[] buf, int bytes) {
/*  78: 78 */    readinit(buf, 0, bytes);
/*  79:    */  }
/*  80:    */  
/*  81:    */  public void readinit(byte[] buf, int start, int bytes) {
/*  82: 82 */    this.ptr = start;
/*  83: 83 */    this.buffer = buf;
/*  84: 84 */    this.endbit = (this.endbyte = 0);
/*  85: 85 */    this.storage = bytes;
/*  86:    */  }
/*  87:    */  
/*  88:    */  public void write(int value, int bits) {
/*  89: 89 */    if (this.endbyte + 4 >= this.storage) {
/*  90: 90 */      byte[] foo = new byte[this.storage + 256];
/*  91: 91 */      System.arraycopy(this.buffer, 0, foo, 0, this.storage);
/*  92: 92 */      this.buffer = foo;
/*  93: 93 */      this.storage += 256;
/*  94:    */    }
/*  95:    */    
/*  96: 96 */    value &= mask[bits];
/*  97: 97 */    bits += this.endbit; int 
/*  98: 98 */      tmp78_75 = this.ptr; byte[] tmp78_71 = this.buffer;tmp78_71[tmp78_75] = ((byte)(tmp78_71[tmp78_75] | (byte)(value << this.endbit)));
/*  99:    */    
/* 100:100 */    if (bits >= 8) {
/* 101:101 */      this.buffer[(this.ptr + 1)] = ((byte)(value >>> 8 - this.endbit));
/* 102:102 */      if (bits >= 16) {
/* 103:103 */        this.buffer[(this.ptr + 2)] = ((byte)(value >>> 16 - this.endbit));
/* 104:104 */        if (bits >= 24) {
/* 105:105 */          this.buffer[(this.ptr + 3)] = ((byte)(value >>> 24 - this.endbit));
/* 106:106 */          if (bits >= 32) {
/* 107:107 */            if (this.endbit > 0) {
/* 108:108 */              this.buffer[(this.ptr + 4)] = ((byte)(value >>> 32 - this.endbit));
/* 109:    */            } else {
/* 110:110 */              this.buffer[(this.ptr + 4)] = 0;
/* 111:    */            }
/* 112:    */          }
/* 113:    */        }
/* 114:    */      }
/* 115:    */    }
/* 116:116 */    this.endbyte += bits / 8;
/* 117:117 */    this.ptr += bits / 8;
/* 118:118 */    this.endbit = (bits & 0x7);
/* 119:    */  }
/* 120:    */  
/* 121:    */  public int look(int bits)
/* 122:    */  {
/* 123:123 */    int m = mask[bits];
/* 124:    */    
/* 125:125 */    bits += this.endbit;
/* 126:    */    
/* 127:127 */    if ((this.endbyte + 4 >= this.storage) && 
/* 128:128 */      (this.endbyte + (bits - 1) / 8 >= this.storage)) {
/* 129:129 */      return -1;
/* 130:    */    }
/* 131:    */    
/* 132:132 */    int ret = (this.buffer[this.ptr] & 0xFF) >>> this.endbit;
/* 133:133 */    if (bits > 8) {
/* 134:134 */      ret |= (this.buffer[(this.ptr + 1)] & 0xFF) << 8 - this.endbit;
/* 135:135 */      if (bits > 16) {
/* 136:136 */        ret |= (this.buffer[(this.ptr + 2)] & 0xFF) << 16 - this.endbit;
/* 137:137 */        if (bits > 24) {
/* 138:138 */          ret |= (this.buffer[(this.ptr + 3)] & 0xFF) << 24 - this.endbit;
/* 139:139 */          if ((bits > 32) && (this.endbit != 0)) {
/* 140:140 */            ret |= (this.buffer[(this.ptr + 4)] & 0xFF) << 32 - this.endbit;
/* 141:    */          }
/* 142:    */        }
/* 143:    */      }
/* 144:    */    }
/* 145:145 */    return m & ret;
/* 146:    */  }
/* 147:    */  
/* 148:    */  public int look1() {
/* 149:149 */    if (this.endbyte >= this.storage)
/* 150:150 */      return -1;
/* 151:151 */    return this.buffer[this.ptr] >> this.endbit & 0x1;
/* 152:    */  }
/* 153:    */  
/* 154:    */  public void adv(int bits) {
/* 155:155 */    bits += this.endbit;
/* 156:156 */    this.ptr += bits / 8;
/* 157:157 */    this.endbyte += bits / 8;
/* 158:158 */    this.endbit = (bits & 0x7);
/* 159:    */  }
/* 160:    */  
/* 161:    */  public void adv1() {
/* 162:162 */    this.endbit += 1;
/* 163:163 */    if (this.endbit > 7) {
/* 164:164 */      this.endbit = 0;
/* 165:165 */      this.ptr += 1;
/* 166:166 */      this.endbyte += 1;
/* 167:    */    }
/* 168:    */  }
/* 169:    */  
/* 170:    */  public int read(int bits)
/* 171:    */  {
/* 172:172 */    int m = mask[bits];
/* 173:    */    
/* 174:174 */    bits += this.endbit;
/* 175:    */    
/* 176:176 */    if (this.endbyte + 4 >= this.storage) {
/* 177:177 */      int ret = -1;
/* 178:178 */      if (this.endbyte + (bits - 1) / 8 >= this.storage) {
/* 179:179 */        this.ptr += bits / 8;
/* 180:180 */        this.endbyte += bits / 8;
/* 181:181 */        this.endbit = (bits & 0x7);
/* 182:182 */        return ret;
/* 183:    */      }
/* 184:    */    }
/* 185:    */    
/* 186:186 */    int ret = (this.buffer[this.ptr] & 0xFF) >>> this.endbit;
/* 187:187 */    if (bits > 8) {
/* 188:188 */      ret |= (this.buffer[(this.ptr + 1)] & 0xFF) << 8 - this.endbit;
/* 189:189 */      if (bits > 16) {
/* 190:190 */        ret |= (this.buffer[(this.ptr + 2)] & 0xFF) << 16 - this.endbit;
/* 191:191 */        if (bits > 24) {
/* 192:192 */          ret |= (this.buffer[(this.ptr + 3)] & 0xFF) << 24 - this.endbit;
/* 193:193 */          if ((bits > 32) && (this.endbit != 0)) {
/* 194:194 */            ret |= (this.buffer[(this.ptr + 4)] & 0xFF) << 32 - this.endbit;
/* 195:    */          }
/* 196:    */        }
/* 197:    */      }
/* 198:    */    }
/* 199:    */    
/* 200:200 */    ret &= m;
/* 201:    */    
/* 202:202 */    this.ptr += bits / 8;
/* 203:203 */    this.endbyte += bits / 8;
/* 204:204 */    this.endbit = (bits & 0x7);
/* 205:205 */    return ret;
/* 206:    */  }
/* 207:    */  
/* 208:    */  public int readB(int bits)
/* 209:    */  {
/* 210:210 */    int m = 32 - bits;
/* 211:    */    
/* 212:212 */    bits += this.endbit;
/* 213:    */    
/* 214:214 */    if (this.endbyte + 4 >= this.storage)
/* 215:    */    {
/* 216:216 */      int ret = -1;
/* 217:217 */      if (this.endbyte * 8 + bits > this.storage * 8) {
/* 218:218 */        this.ptr += bits / 8;
/* 219:219 */        this.endbyte += bits / 8;
/* 220:220 */        this.endbit = (bits & 0x7);
/* 221:221 */        return ret;
/* 222:    */      }
/* 223:    */    }
/* 224:    */    
/* 225:225 */    int ret = (this.buffer[this.ptr] & 0xFF) << 24 + this.endbit;
/* 226:226 */    if (bits > 8) {
/* 227:227 */      ret |= (this.buffer[(this.ptr + 1)] & 0xFF) << 16 + this.endbit;
/* 228:228 */      if (bits > 16) {
/* 229:229 */        ret |= (this.buffer[(this.ptr + 2)] & 0xFF) << 8 + this.endbit;
/* 230:230 */        if (bits > 24) {
/* 231:231 */          ret |= (this.buffer[(this.ptr + 3)] & 0xFF) << this.endbit;
/* 232:232 */          if ((bits > 32) && (this.endbit != 0))
/* 233:233 */            ret |= (this.buffer[(this.ptr + 4)] & 0xFF) >> 8 - this.endbit;
/* 234:    */        }
/* 235:    */      }
/* 236:    */    }
/* 237:237 */    ret = ret >>> (m >> 1) >>> (m + 1 >> 1);
/* 238:    */    
/* 239:239 */    this.ptr += bits / 8;
/* 240:240 */    this.endbyte += bits / 8;
/* 241:241 */    this.endbit = (bits & 0x7);
/* 242:242 */    return ret;
/* 243:    */  }
/* 244:    */  
/* 245:    */  public int read1()
/* 246:    */  {
/* 247:247 */    if (this.endbyte >= this.storage) {
/* 248:248 */      int ret = -1;
/* 249:249 */      this.endbit += 1;
/* 250:250 */      if (this.endbit > 7) {
/* 251:251 */        this.endbit = 0;
/* 252:252 */        this.ptr += 1;
/* 253:253 */        this.endbyte += 1;
/* 254:    */      }
/* 255:255 */      return ret;
/* 256:    */    }
/* 257:    */    
/* 258:258 */    int ret = this.buffer[this.ptr] >> this.endbit & 0x1;
/* 259:    */    
/* 260:260 */    this.endbit += 1;
/* 261:261 */    if (this.endbit > 7) {
/* 262:262 */      this.endbit = 0;
/* 263:263 */      this.ptr += 1;
/* 264:264 */      this.endbyte += 1;
/* 265:    */    }
/* 266:266 */    return ret;
/* 267:    */  }
/* 268:    */  
/* 269:    */  public int bytes() {
/* 270:270 */    return this.endbyte + (this.endbit + 7) / 8;
/* 271:    */  }
/* 272:    */  
/* 273:    */  public int bits() {
/* 274:274 */    return this.endbyte * 8 + this.endbit;
/* 275:    */  }
/* 276:    */  
/* 277:    */  public byte[] buffer() {
/* 278:278 */    return this.buffer;
/* 279:    */  }
/* 280:    */  
/* 281:    */  public static int ilog(int v) {
/* 282:282 */    int ret = 0;
/* 283:283 */    while (v > 0) {
/* 284:284 */      ret++;
/* 285:285 */      v >>>= 1;
/* 286:    */    }
/* 287:287 */    return ret;
/* 288:    */  }
/* 289:    */  
/* 290:    */  public static void report(String in) {
/* 291:291 */    System.err.println(in);
/* 292:292 */    System.exit(1);
/* 293:    */  }
/* 294:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.jcraft.jogg.Buffer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */