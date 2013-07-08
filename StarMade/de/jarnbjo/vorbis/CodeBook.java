/*   1:    */package de.jarnbjo.vorbis;
/*   2:    */
/*   3:    */import de.jarnbjo.util.io.BitInputStream;
/*   4:    */import de.jarnbjo.util.io.HuffmanNode;
/*   5:    */import java.io.IOException;
/*   6:    */import java.util.Arrays;
/*   7:    */
/*  36:    */class CodeBook
/*  37:    */{
/*  38:    */  private HuffmanNode huffmanRoot;
/*  39:    */  private int dimensions;
/*  40:    */  private int entries;
/*  41:    */  private int[] entryLengths;
/*  42:    */  private float[][] valueVector;
/*  43:    */  
/*  44:    */  protected CodeBook(BitInputStream source)
/*  45:    */    throws VorbisFormatException, IOException
/*  46:    */  {
/*  47: 47 */    if (source.getInt(24) != 5653314) {
/*  48: 48 */      throw new VorbisFormatException("The code book sync pattern is not correct.");
/*  49:    */    }
/*  50:    */    
/*  51: 51 */    this.dimensions = source.getInt(16);
/*  52: 52 */    this.entries = source.getInt(24);
/*  53:    */    
/*  54: 54 */    this.entryLengths = new int[this.entries];
/*  55:    */    
/*  56: 56 */    boolean ordered = source.getBit();
/*  57:    */    int cl;
/*  58: 58 */    int i; if (ordered) {
/*  59: 59 */      cl = source.getInt(5) + 1;
/*  60: 60 */      for (i = 0; i < this.entryLengths.length;) {
/*  61: 61 */        int num = source.getInt(Util.ilog(this.entryLengths.length - i));
/*  62: 62 */        if (i + num > this.entryLengths.length) {
/*  63: 63 */          throw new VorbisFormatException("The codebook entry length list is longer than the actual number of entry lengths.");
/*  64:    */        }
/*  65: 65 */        Arrays.fill(this.entryLengths, i, i + num, cl);
/*  66: 66 */        cl++;
/*  67: 67 */        i += num;
/*  68:    */      }
/*  69:    */    }
/*  70:    */    else
/*  71:    */    {
/*  72: 72 */      boolean sparse = source.getBit();
/*  73:    */      
/*  74: 74 */      if (sparse) {
/*  75: 75 */        for (int i = 0; i < this.entryLengths.length; i++) {
/*  76: 76 */          if (source.getBit()) {
/*  77: 77 */            this.entryLengths[i] = (source.getInt(5) + 1);
/*  78:    */          }
/*  79:    */          else {
/*  80: 80 */            this.entryLengths[i] = -1;
/*  81:    */          }
/*  82:    */          
/*  83:    */        }
/*  84:    */        
/*  85:    */      } else {
/*  86: 86 */        for (int i = 0; i < this.entryLengths.length; i++) {
/*  87: 87 */          this.entryLengths[i] = (source.getInt(5) + 1);
/*  88:    */        }
/*  89:    */      }
/*  90:    */    }
/*  91:    */    
/*  92: 92 */    if (!createHuffmanTree(this.entryLengths)) {
/*  93: 93 */      throw new VorbisFormatException("An exception was thrown when building the codebook Huffman tree.");
/*  94:    */    }
/*  95:    */    
/*  96: 96 */    int codeBookLookupType = source.getInt(4);
/*  97:    */    
/*  98: 98 */    switch (codeBookLookupType)
/*  99:    */    {
/* 100:    */    case 0: 
/* 101:101 */      break;
/* 102:    */    case 1: 
/* 103:    */    case 2: 
/* 104:104 */      float codeBookMinimumValue = Util.float32unpack(source.getInt(32));
/* 105:105 */      float codeBookDeltaValue = Util.float32unpack(source.getInt(32));
/* 106:    */      
/* 107:107 */      int codeBookValueBits = source.getInt(4) + 1;
/* 108:108 */      boolean codeBookSequenceP = source.getBit();
/* 109:    */      
/* 110:110 */      int codeBookLookupValues = 0;
/* 111:    */      
/* 112:112 */      if (codeBookLookupType == 1) {
/* 113:113 */        codeBookLookupValues = Util.lookup1Values(this.entries, this.dimensions);
/* 114:    */      }
/* 115:    */      else {
/* 116:116 */        codeBookLookupValues = this.entries * this.dimensions;
/* 117:    */      }
/* 118:    */      
/* 119:119 */      int[] codeBookMultiplicands = new int[codeBookLookupValues];
/* 120:    */      
/* 121:121 */      for (int i = 0; i < codeBookMultiplicands.length; i++) {
/* 122:122 */        codeBookMultiplicands[i] = source.getInt(codeBookValueBits);
/* 123:    */      }
/* 124:    */      
/* 125:125 */      this.valueVector = new float[this.entries][this.dimensions];
/* 126:    */      
/* 127:127 */      if (codeBookLookupType == 1) {
/* 128:128 */        for (int i = 0; i < this.entries; i++) {
/* 129:129 */          float last = 0.0F;
/* 130:130 */          int indexDivisor = 1;
/* 131:131 */          for (int j = 0; j < this.dimensions; j++) {
/* 132:132 */            int multiplicandOffset = i / indexDivisor % codeBookLookupValues;
/* 133:    */            
/* 134:134 */            this.valueVector[i][j] = (codeBookMultiplicands[multiplicandOffset] * codeBookDeltaValue + codeBookMinimumValue + last);
/* 135:    */            
/* 136:136 */            if (codeBookSequenceP) {
/* 137:137 */              last = this.valueVector[i][j];
/* 138:    */            }
/* 139:139 */            indexDivisor *= codeBookLookupValues;
/* 140:    */          }
/* 141:    */          
/* 142:    */        }
/* 143:    */      } else {
/* 144:144 */        throw new UnsupportedOperationException();
/* 145:    */      }
/* 146:    */      
/* 147:    */      break;
/* 148:    */    default: 
/* 149:149 */      throw new VorbisFormatException("Unsupported codebook lookup type: " + codeBookLookupType);
/* 150:    */    }
/* 151:    */  }
/* 152:    */  
/* 153:153 */  private static long totalTime = 0L;
/* 154:    */  
/* 155:    */  private boolean createHuffmanTree(int[] entryLengths) {
/* 156:156 */    this.huffmanRoot = new HuffmanNode();
/* 157:157 */    for (int i = 0; i < entryLengths.length; i++) {
/* 158:158 */      int el = entryLengths[i];
/* 159:159 */      if ((el > 0) && 
/* 160:160 */        (!this.huffmanRoot.setNewValue(el, i))) {
/* 161:161 */        return false;
/* 162:    */      }
/* 163:    */    }
/* 164:    */    
/* 165:165 */    return true;
/* 166:    */  }
/* 167:    */  
/* 168:    */  protected int getDimensions() {
/* 169:169 */    return this.dimensions;
/* 170:    */  }
/* 171:    */  
/* 172:    */  protected int getEntries() {
/* 173:173 */    return this.entries;
/* 174:    */  }
/* 175:    */  
/* 176:    */  protected HuffmanNode getHuffmanRoot() {
/* 177:177 */    return this.huffmanRoot;
/* 178:    */  }
/* 179:    */  
/* 182:    */  protected int readInt(BitInputStream source)
/* 183:    */    throws IOException
/* 184:    */  {
/* 185:185 */    return source.getInt(this.huffmanRoot);
/* 186:    */  }
/* 187:    */  
/* 194:    */  protected void readVvAdd(float[][] a, BitInputStream source, int offset, int length)
/* 195:    */    throws VorbisFormatException, IOException
/* 196:    */  {
/* 197:197 */    int chptr = 0;
/* 198:198 */    int ch = a.length;
/* 199:    */    
/* 200:200 */    if (ch == 0) {
/* 201:201 */      return;
/* 202:    */    }
/* 203:    */    
/* 204:204 */    int lim = (offset + length) / ch;
/* 205:    */    
/* 206:206 */    for (int i = offset / ch; i < lim;) {
/* 207:207 */      float[] ve = this.valueVector[source.getInt(this.huffmanRoot)];
/* 208:208 */      for (int j = 0; j < this.dimensions; j++) {
/* 209:209 */        a[(chptr++)][i] += ve[j];
/* 210:210 */        if (chptr == ch) {
/* 211:211 */          chptr = 0;
/* 212:212 */          i++;
/* 213:    */        }
/* 214:    */      }
/* 215:    */    }
/* 216:    */  }
/* 217:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     de.jarnbjo.vorbis.CodeBook
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */