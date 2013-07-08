/*   1:    */package de.jarnbjo.vorbis;
/*   2:    */
/*   3:    */import de.jarnbjo.util.io.BitInputStream;
/*   4:    */import java.io.IOException;
/*   5:    */import java.util.ArrayList;
/*   6:    */import java.util.Arrays;
/*   7:    */import java.util.Iterator;
/*   8:    */
/*  29:    */class Floor1
/*  30:    */  extends Floor
/*  31:    */  implements Cloneable
/*  32:    */{
/*  33:    */  private int[] partitionClassList;
/*  34:    */  private int maximumClass;
/*  35:    */  private int multiplier;
/*  36:    */  private int rangeBits;
/*  37:    */  private int[] classDimensions;
/*  38:    */  private int[] classSubclasses;
/*  39:    */  private int[] classMasterbooks;
/*  40:    */  private int[][] subclassBooks;
/*  41:    */  private int[] xList;
/*  42:    */  private int[] yList;
/*  43:    */  private int[] lowNeighbours;
/*  44:    */  private int[] highNeighbours;
/*  45: 45 */  private static final int[] RANGES = { 256, 128, 86, 64 };
/*  46:    */  
/*  47:    */  private Floor1() {}
/*  48:    */  
/*  49:    */  protected Floor1(BitInputStream source, SetupHeader header)
/*  50:    */    throws VorbisFormatException, IOException
/*  51:    */  {
/*  52: 52 */    this.maximumClass = -1;
/*  53: 53 */    int partitions = source.getInt(5);
/*  54: 54 */    this.partitionClassList = new int[partitions];
/*  55:    */    
/*  56: 56 */    for (int i = 0; i < this.partitionClassList.length; i++) {
/*  57: 57 */      this.partitionClassList[i] = source.getInt(4);
/*  58: 58 */      if (this.partitionClassList[i] > this.maximumClass) {
/*  59: 59 */        this.maximumClass = this.partitionClassList[i];
/*  60:    */      }
/*  61:    */    }
/*  62:    */    
/*  64: 64 */    this.classDimensions = new int[this.maximumClass + 1];
/*  65: 65 */    this.classSubclasses = new int[this.maximumClass + 1];
/*  66: 66 */    this.classMasterbooks = new int[this.maximumClass + 1];
/*  67: 67 */    this.subclassBooks = new int[this.maximumClass + 1][];
/*  68:    */    
/*  69: 69 */    int xListLength = 2;
/*  70:    */    
/*  71: 71 */    for (int i = 0; i <= this.maximumClass; i++) {
/*  72: 72 */      this.classDimensions[i] = (source.getInt(3) + 1);
/*  73: 73 */      xListLength += this.classDimensions[i];
/*  74: 74 */      this.classSubclasses[i] = source.getInt(2);
/*  75:    */      
/*  76: 76 */      if ((this.classDimensions[i] > header.getCodeBooks().length) || (this.classSubclasses[i] > header.getCodeBooks().length))
/*  77:    */      {
/*  78: 78 */        throw new VorbisFormatException("There is a class dimension or class subclasses entry higher than the number of codebooks in the setup header.");
/*  79:    */      }
/*  80: 80 */      if (this.classSubclasses[i] != 0) {
/*  81: 81 */        this.classMasterbooks[i] = source.getInt(8);
/*  82:    */      }
/*  83: 83 */      this.subclassBooks[i] = new int[1 << this.classSubclasses[i]];
/*  84: 84 */      for (int j = 0; j < this.subclassBooks[i].length; j++) {
/*  85: 85 */        this.subclassBooks[i][j] = (source.getInt(8) - 1);
/*  86:    */      }
/*  87:    */    }
/*  88:    */    
/*  89: 89 */    this.multiplier = (source.getInt(2) + 1);
/*  90: 90 */    this.rangeBits = source.getInt(4);
/*  91:    */    
/*  97: 97 */    int floorValues = 0;
/*  98:    */    
/*  99: 99 */    ArrayList alXList = new ArrayList();
/* 100:    */    
/* 101:101 */    alXList.add(new Integer(0));
/* 102:102 */    alXList.add(new Integer(1 << this.rangeBits));
/* 103:    */    
/* 107:107 */    for (int i = 0; i < partitions; i++) {
/* 108:108 */      for (int j = 0; j < this.classDimensions[this.partitionClassList[i]]; j++) {
/* 109:109 */        alXList.add(new Integer(source.getInt(this.rangeBits)));
/* 110:    */      }
/* 111:    */    }
/* 112:    */    
/* 113:113 */    this.xList = new int[alXList.size()];
/* 114:114 */    this.lowNeighbours = new int[this.xList.length];
/* 115:115 */    this.highNeighbours = new int[this.xList.length];
/* 116:    */    
/* 117:117 */    Iterator iter = alXList.iterator();
/* 118:118 */    for (int i = 0; i < this.xList.length; i++) {
/* 119:119 */      this.xList[i] = ((Integer)iter.next()).intValue();
/* 120:    */    }
/* 121:    */    
/* 122:122 */    for (int i = 0; i < this.xList.length; i++) {
/* 123:123 */      this.lowNeighbours[i] = Util.lowNeighbour(this.xList, i);
/* 124:124 */      this.highNeighbours[i] = Util.highNeighbour(this.xList, i);
/* 125:    */    }
/* 126:    */  }
/* 127:    */  
/* 128:    */  protected int getType() {
/* 129:129 */    return 1;
/* 130:    */  }
/* 131:    */  
/* 132:    */  protected Floor decodeFloor(VorbisStream vorbis, BitInputStream source)
/* 133:    */    throws VorbisFormatException, IOException
/* 134:    */  {
/* 135:135 */    if (!source.getBit())
/* 136:    */    {
/* 137:137 */      return null;
/* 138:    */    }
/* 139:    */    
/* 140:140 */    Floor1 clone = (Floor1)clone();
/* 141:    */    
/* 142:142 */    clone.yList = new int[this.xList.length];
/* 143:    */    
/* 144:144 */    int range = RANGES[(this.multiplier - 1)];
/* 145:    */    
/* 146:146 */    clone.yList[0] = source.getInt(Util.ilog(range - 1));
/* 147:147 */    clone.yList[1] = source.getInt(Util.ilog(range - 1));
/* 148:    */    
/* 149:149 */    int offset = 2;
/* 150:    */    
/* 151:151 */    for (int i = 0; i < this.partitionClassList.length; i++) {
/* 152:152 */      int cls = this.partitionClassList[i];
/* 153:153 */      int cdim = this.classDimensions[cls];
/* 154:154 */      int cbits = this.classSubclasses[cls];
/* 155:155 */      int csub = (1 << cbits) - 1;
/* 156:156 */      int cval = 0;
/* 157:157 */      if (cbits > 0) {
/* 158:158 */        cval = source.getInt(vorbis.getSetupHeader().getCodeBooks()[this.classMasterbooks[cls]].getHuffmanRoot());
/* 159:    */      }
/* 160:    */      
/* 163:163 */      for (int j = 0; j < cdim; j++)
/* 164:    */      {
/* 165:165 */        int book = this.subclassBooks[cls][(cval & csub)];
/* 166:166 */        cval >>>= cbits;
/* 167:167 */        if (book >= 0) {
/* 168:168 */          clone.yList[(j + offset)] = source.getInt(vorbis.getSetupHeader().getCodeBooks()[book].getHuffmanRoot());
/* 170:    */        }
/* 171:    */        else
/* 172:    */        {
/* 174:174 */          clone.yList[(j + offset)] = 0;
/* 175:    */        }
/* 176:    */      }
/* 177:177 */      offset += cdim;
/* 178:    */    }
/* 179:    */    
/* 195:195 */    return clone;
/* 196:    */  }
/* 197:    */  
/* 198:    */  protected void computeFloor(float[] vector)
/* 199:    */  {
/* 200:200 */    int n = vector.length;
/* 201:201 */    int values = this.xList.length;
/* 202:202 */    boolean[] step2Flags = new boolean[values];
/* 203:    */    
/* 204:204 */    int range = RANGES[(this.multiplier - 1)];
/* 205:    */    
/* 206:206 */    for (int i = 2; i < values; i++) {
/* 207:207 */      int lowNeighbourOffset = this.lowNeighbours[i];
/* 208:208 */      int highNeighbourOffset = this.highNeighbours[i];
/* 209:209 */      int predicted = Util.renderPoint(this.xList[lowNeighbourOffset], this.xList[highNeighbourOffset], this.yList[lowNeighbourOffset], this.yList[highNeighbourOffset], this.xList[i]);
/* 210:    */      
/* 213:213 */      int val = this.yList[i];
/* 214:214 */      int highRoom = range - predicted;
/* 215:215 */      int lowRoom = predicted;
/* 216:216 */      int room = highRoom < lowRoom ? highRoom * 2 : lowRoom * 2;
/* 217:217 */      if (val != 0) {
/* 218:218 */        step2Flags[lowNeighbourOffset] = true;
/* 219:219 */        step2Flags[highNeighbourOffset] = true;
/* 220:220 */        step2Flags[i] = true;
/* 221:221 */        if (val >= room) {
/* 222:222 */          this.yList[i] = (highRoom > lowRoom ? val - lowRoom + predicted : -val + highRoom + predicted - 1);
/* 224:    */        }
/* 225:    */        else
/* 226:    */        {
/* 227:227 */          this.yList[i] = ((val & 0x1) == 1 ? predicted - (val + 1 >> 1) : predicted + (val >> 1));
/* 228:    */        }
/* 229:    */        
/* 230:    */      }
/* 231:    */      else
/* 232:    */      {
/* 233:233 */        step2Flags[i] = false;
/* 234:234 */        this.yList[i] = predicted;
/* 235:    */      }
/* 236:    */    }
/* 237:    */    
/* 238:238 */    int[] xList2 = new int[values];
/* 239:    */    
/* 240:240 */    System.arraycopy(this.xList, 0, xList2, 0, values);
/* 241:241 */    sort(xList2, this.yList, step2Flags);
/* 242:    */    
/* 243:243 */    int hx = 0;int hy = 0;int lx = 0;int ly = this.yList[0] * this.multiplier;
/* 244:    */    
/* 245:245 */    float[] vector2 = new float[vector.length];
/* 246:246 */    float[] vector3 = new float[vector.length];
/* 247:247 */    Arrays.fill(vector2, 1.0F);
/* 248:248 */    System.arraycopy(vector, 0, vector3, 0, vector.length);
/* 249:    */    
/* 250:250 */    for (int i = 1; i < values; i++) {
/* 251:251 */      if (step2Flags[i] != 0) {
/* 252:252 */        hy = this.yList[i] * this.multiplier;
/* 253:253 */        hx = xList2[i];
/* 254:254 */        Util.renderLine(lx, ly, hx, hy, vector);
/* 255:255 */        Util.renderLine(lx, ly, hx, hy, vector2);
/* 256:256 */        lx = hx;
/* 257:257 */        ly = hy;
/* 258:    */      }
/* 259:    */    }
/* 260:    */    
/* 261:261 */    float r = DB_STATIC_TABLE[hy];
/* 262:262 */    while (hx < n / 2) vector[(hx++)] = r;
/* 263:    */  }
/* 264:    */  
/* 265:    */  public Object clone() {
/* 266:266 */    Floor1 clone = new Floor1();
/* 267:267 */    clone.classDimensions = this.classDimensions;
/* 268:268 */    clone.classMasterbooks = this.classMasterbooks;
/* 269:269 */    clone.classSubclasses = this.classSubclasses;
/* 270:270 */    clone.maximumClass = this.maximumClass;
/* 271:271 */    clone.multiplier = this.multiplier;
/* 272:272 */    clone.partitionClassList = this.partitionClassList;
/* 273:273 */    clone.rangeBits = this.rangeBits;
/* 274:274 */    clone.subclassBooks = this.subclassBooks;
/* 275:275 */    clone.xList = this.xList;
/* 276:276 */    clone.yList = this.yList;
/* 277:277 */    clone.lowNeighbours = this.lowNeighbours;
/* 278:278 */    clone.highNeighbours = this.highNeighbours;
/* 279:279 */    return clone;
/* 280:    */  }
/* 281:    */  
/* 282:    */  private static final void sort(int[] x, int[] y, boolean[] b) {
/* 283:283 */    int off = 0;
/* 284:284 */    int len = x.length;
/* 285:285 */    int lim = len + off;
/* 286:    */    
/* 289:289 */    for (int i = off; i < lim; i++) {
/* 290:290 */      for (int j = i; (j > off) && (x[(j - 1)] > x[j]); j--) {
/* 291:291 */        int itmp = x[j];
/* 292:292 */        x[j] = x[(j - 1)];
/* 293:293 */        x[(j - 1)] = itmp;
/* 294:294 */        itmp = y[j];
/* 295:295 */        y[j] = y[(j - 1)];
/* 296:296 */        y[(j - 1)] = itmp;
/* 297:297 */        boolean btmp = b[j];
/* 298:298 */        b[j] = b[(j - 1)];
/* 299:299 */        b[(j - 1)] = btmp;
/* 300:    */      }
/* 301:    */    }
/* 302:    */  }
/* 303:    */  
/* 306:    */  private static final void swap(int[] x, int a, int b)
/* 307:    */  {
/* 308:308 */    int t = x[a];
/* 309:309 */    x[a] = x[b];
/* 310:310 */    x[b] = t;
/* 311:    */  }
/* 312:    */  
/* 313:    */  private static final void swap(boolean[] x, int a, int b) {
/* 314:314 */    boolean t = x[a];
/* 315:315 */    x[a] = x[b];
/* 316:316 */    x[b] = t;
/* 317:    */  }
/* 318:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     de.jarnbjo.vorbis.Floor1
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */