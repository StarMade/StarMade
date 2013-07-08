/*   1:    */package de.jarnbjo.vorbis;
/*   2:    */
/*   3:    */import de.jarnbjo.util.io.BitInputStream;
/*   4:    */import java.io.IOException;
/*   5:    */import java.util.HashMap;
/*   6:    */
/*  34:    */abstract class Residue
/*  35:    */{
/*  36:    */  protected int begin;
/*  37:    */  protected int end;
/*  38:    */  protected int partitionSize;
/*  39:    */  protected int classifications;
/*  40:    */  protected int classBook;
/*  41:    */  protected int[] cascade;
/*  42:    */  protected int[][] books;
/*  43: 43 */  protected HashMap looks = new HashMap();
/*  44:    */  
/*  45:    */  protected Residue() {}
/*  46:    */  
/*  47:    */  protected Residue(BitInputStream source, SetupHeader header) throws VorbisFormatException, IOException
/*  48:    */  {
/*  49: 49 */    this.begin = source.getInt(24);
/*  50: 50 */    this.end = source.getInt(24);
/*  51: 51 */    this.partitionSize = (source.getInt(24) + 1);
/*  52: 52 */    this.classifications = (source.getInt(6) + 1);
/*  53: 53 */    this.classBook = source.getInt(8);
/*  54:    */    
/*  55: 55 */    this.cascade = new int[this.classifications];
/*  56:    */    
/*  57: 57 */    int acc = 0;
/*  58:    */    
/*  59: 59 */    for (int i = 0; i < this.classifications; i++) {
/*  60: 60 */      int highBits = 0;int lowBits = 0;
/*  61: 61 */      lowBits = source.getInt(3);
/*  62: 62 */      if (source.getBit()) {
/*  63: 63 */        highBits = source.getInt(5);
/*  64:    */      }
/*  65: 65 */      this.cascade[i] = (highBits << 3 | lowBits);
/*  66: 66 */      acc += Util.icount(this.cascade[i]);
/*  67:    */    }
/*  68:    */    
/*  69: 69 */    this.books = new int[this.classifications][8];
/*  70:    */    
/*  71: 71 */    for (int i = 0; i < this.classifications; i++) {
/*  72: 72 */      for (int j = 0; j < 8; j++) {
/*  73: 73 */        if ((this.cascade[i] & 1 << j) != 0) {
/*  74: 74 */          this.books[i][j] = source.getInt(8);
/*  75: 75 */          if (this.books[i][j] > header.getCodeBooks().length) {
/*  76: 76 */            throw new VorbisFormatException("Reference to invalid codebook entry in residue header.");
/*  77:    */          }
/*  78:    */        }
/*  79:    */      }
/*  80:    */    }
/*  81:    */  }
/*  82:    */  
/*  83:    */  protected static Residue createInstance(BitInputStream source, SetupHeader header)
/*  84:    */    throws VorbisFormatException, IOException
/*  85:    */  {
/*  86: 86 */    int type = source.getInt(16);
/*  87: 87 */    switch (type)
/*  88:    */    {
/*  89:    */    case 0: 
/*  90: 90 */      return new Residue0(source, header);
/*  91:    */    
/*  92:    */    case 1: 
/*  93: 93 */      return new Residue2(source, header);
/*  94:    */    
/*  95:    */    case 2: 
/*  96: 96 */      return new Residue2(source, header);
/*  97:    */    }
/*  98: 98 */    throw new VorbisFormatException("Residue type " + type + " is not supported.");
/*  99:    */  }
/* 100:    */  
/* 101:    */  protected abstract int getType();
/* 102:    */  
/* 103:    */  protected abstract void decodeResidue(VorbisStream paramVorbisStream, BitInputStream paramBitInputStream, Mode paramMode, int paramInt, boolean[] paramArrayOfBoolean, float[][] paramArrayOfFloat) throws VorbisFormatException, IOException;
/* 104:    */  
/* 105:    */  protected int getBegin()
/* 106:    */  {
/* 107:107 */    return this.begin;
/* 108:    */  }
/* 109:    */  
/* 110:    */  protected int getEnd() {
/* 111:111 */    return this.end;
/* 112:    */  }
/* 113:    */  
/* 114:    */  protected int getPartitionSize() {
/* 115:115 */    return this.partitionSize;
/* 116:    */  }
/* 117:    */  
/* 118:    */  protected int getClassifications() {
/* 119:119 */    return this.classifications;
/* 120:    */  }
/* 121:    */  
/* 122:    */  protected int getClassBook() {
/* 123:123 */    return this.classBook;
/* 124:    */  }
/* 125:    */  
/* 126:    */  protected int[] getCascade() {
/* 127:127 */    return this.cascade;
/* 128:    */  }
/* 129:    */  
/* 130:    */  protected int[][] getBooks() {
/* 131:131 */    return this.books;
/* 132:    */  }
/* 133:    */  
/* 134:    */  protected final void fill(Residue clone) {
/* 135:135 */    clone.begin = this.begin;
/* 136:136 */    clone.books = this.books;
/* 137:137 */    clone.cascade = this.cascade;
/* 138:138 */    clone.classBook = this.classBook;
/* 139:139 */    clone.classifications = this.classifications;
/* 140:140 */    clone.end = this.end;
/* 141:141 */    clone.partitionSize = this.partitionSize;
/* 142:    */  }
/* 143:    */  
/* 144:    */  protected Look getLook(VorbisStream source, Mode key)
/* 145:    */  {
/* 146:146 */    Look look = (Look)this.looks.get(key);
/* 147:147 */    if (look == null) {
/* 148:148 */      look = new Look(source, key);
/* 149:149 */      this.looks.put(key, look);
/* 150:    */    }
/* 151:151 */    return look;
/* 152:    */  }
/* 153:    */  
/* 154:    */  class Look
/* 155:    */  {
/* 156:    */    int map;
/* 157:    */    int parts;
/* 158:    */    int stages;
/* 159:    */    CodeBook[] fullbooks;
/* 160:    */    CodeBook phrasebook;
/* 161:    */    int[][] partbooks;
/* 162:    */    int partvals;
/* 163:    */    int[][] decodemap;
/* 164:    */    int postbits;
/* 165:    */    int phrasebits;
/* 166:    */    int frames;
/* 167:    */    
/* 168:    */    protected Look(VorbisStream source, Mode mode) {
/* 169:169 */      int dim = 0;int acc = 0;int maxstage = 0;
/* 170:    */      
/* 171:171 */      this.map = mode.getMapping();
/* 172:172 */      this.parts = Residue.this.getClassifications();
/* 173:173 */      this.fullbooks = source.getSetupHeader().getCodeBooks();
/* 174:174 */      this.phrasebook = this.fullbooks[Residue.this.getClassBook()];
/* 175:175 */      dim = this.phrasebook.getDimensions();
/* 176:    */      
/* 177:177 */      this.partbooks = new int[this.parts][];
/* 178:    */      
/* 179:179 */      for (int j = 0; j < this.parts; j++) {
/* 180:180 */        int stages = Util.ilog(Residue.this.getCascade()[j]);
/* 181:181 */        if (stages != 0) {
/* 182:182 */          if (stages > maxstage) {
/* 183:183 */            maxstage = stages;
/* 184:    */          }
/* 185:185 */          this.partbooks[j] = new int[stages];
/* 186:186 */          for (int k = 0; k < stages; k++) {
/* 187:187 */            if ((Residue.this.getCascade()[j] & 1 << k) != 0) {
/* 188:188 */              this.partbooks[j][k] = Residue.this.getBooks()[j][k];
/* 189:    */            }
/* 190:    */          }
/* 191:    */        }
/* 192:    */      }
/* 193:    */      
/* 194:194 */      this.partvals = ((int)Math.rint(Math.pow(this.parts, dim)));
/* 195:195 */      this.stages = maxstage;
/* 196:    */      
/* 197:197 */      this.decodemap = new int[this.partvals][];
/* 198:    */      
/* 199:199 */      for (int j = 0; j < this.partvals; j++) {
/* 200:200 */        int val = j;
/* 201:201 */        int mult = this.partvals / this.parts;
/* 202:202 */        this.decodemap[j] = new int[dim];
/* 203:    */        
/* 204:204 */        for (int k = 0; k < dim; k++) {
/* 205:205 */          int deco = val / mult;
/* 206:206 */          val -= deco * mult;
/* 207:207 */          mult /= this.parts;
/* 208:208 */          this.decodemap[j][k] = deco;
/* 209:    */        }
/* 210:    */      }
/* 211:    */    }
/* 212:    */    
/* 213:    */    protected int[][] getDecodeMap() {
/* 214:214 */      return this.decodemap;
/* 215:    */    }
/* 216:    */    
/* 217:    */    protected int getFrames() {
/* 218:218 */      return this.frames;
/* 219:    */    }
/* 220:    */    
/* 221:    */    protected int getMap() {
/* 222:222 */      return this.map;
/* 223:    */    }
/* 224:    */    
/* 225:    */    protected int[][] getPartBooks() {
/* 226:226 */      return this.partbooks;
/* 227:    */    }
/* 228:    */    
/* 229:    */    protected int getParts() {
/* 230:230 */      return this.parts;
/* 231:    */    }
/* 232:    */    
/* 233:    */    protected int getPartVals() {
/* 234:234 */      return this.partvals;
/* 235:    */    }
/* 236:    */    
/* 237:    */    protected int getPhraseBits() {
/* 238:238 */      return this.phrasebits;
/* 239:    */    }
/* 240:    */    
/* 241:    */    protected CodeBook getPhraseBook() {
/* 242:242 */      return this.phrasebook;
/* 243:    */    }
/* 244:    */    
/* 245:    */    protected int getPostBits() {
/* 246:246 */      return this.postbits;
/* 247:    */    }
/* 248:    */    
/* 249:    */    protected int getStages() {
/* 250:250 */      return this.stages;
/* 251:    */    }
/* 252:    */  }
/* 253:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     de.jarnbjo.vorbis.Residue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */