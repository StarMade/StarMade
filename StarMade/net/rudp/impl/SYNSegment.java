/*   1:    */package net.rudp.impl;
/*   2:    */
/*   7:    */public class SYNSegment
/*   8:    */  extends Segment
/*   9:    */{
/*  10:    */  private int _version;
/*  11:    */  
/*  15:    */  private int _maxseg;
/*  16:    */  
/*  20:    */  private int _optflags;
/*  21:    */  
/*  25:    */  private int _maxsegsize;
/*  26:    */  
/*  30:    */  private int _rettoval;
/*  31:    */  
/*  35:    */  private int _cumacktoval;
/*  36:    */  
/*  40:    */  private int _niltoval;
/*  41:    */  
/*  45:    */  private int _maxret;
/*  46:    */  
/*  50:    */  private int _maxcumack;
/*  51:    */  
/*  55:    */  private int _maxoutseq;
/*  56:    */  
/*  60:    */  private int _maxautorst;
/*  61:    */  
/*  65:    */  private static final int SYN_HEADER_LEN = 22;
/*  66:    */  
/*  70:    */  protected SYNSegment() {}
/*  71:    */  
/*  75:    */  public SYNSegment(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10)
/*  76:    */  {
/*  77: 77 */    init(-128, paramInt1, 22);
/*  78:    */    
/*  79: 79 */    this._version = 1;
/*  80: 80 */    this._maxseg = paramInt2;
/*  81: 81 */    this._optflags = 1;
/*  82: 82 */    this._maxsegsize = paramInt3;
/*  83: 83 */    this._rettoval = paramInt4;
/*  84: 84 */    this._cumacktoval = paramInt5;
/*  85: 85 */    this._niltoval = paramInt6;
/*  86: 86 */    this._maxret = paramInt7;
/*  87: 87 */    this._maxcumack = paramInt8;
/*  88: 88 */    this._maxoutseq = paramInt9;
/*  89: 89 */    this._maxautorst = paramInt10;
/*  90:    */  }
/*  91:    */  
/*  92:    */  public String type()
/*  93:    */  {
/*  94: 94 */    return "SYN";
/*  95:    */  }
/*  96:    */  
/*  97:    */  public int getVersion()
/*  98:    */  {
/*  99: 99 */    return this._version;
/* 100:    */  }
/* 101:    */  
/* 102:    */  public int getMaxOutstandingSegments()
/* 103:    */  {
/* 104:104 */    return this._maxseg;
/* 105:    */  }
/* 106:    */  
/* 107:    */  public int getOptionFlags()
/* 108:    */  {
/* 109:109 */    return this._optflags;
/* 110:    */  }
/* 111:    */  
/* 112:    */  public int getMaxSegmentSize()
/* 113:    */  {
/* 114:114 */    return this._maxsegsize;
/* 115:    */  }
/* 116:    */  
/* 117:    */  public int getRetransmissionTimeout()
/* 118:    */  {
/* 119:119 */    return this._rettoval;
/* 120:    */  }
/* 121:    */  
/* 122:    */  public int getCummulativeAckTimeout()
/* 123:    */  {
/* 124:124 */    return this._cumacktoval;
/* 125:    */  }
/* 126:    */  
/* 127:    */  public int getNulSegmentTimeout()
/* 128:    */  {
/* 129:129 */    return this._niltoval;
/* 130:    */  }
/* 131:    */  
/* 132:    */  public int getMaxRetransmissions()
/* 133:    */  {
/* 134:134 */    return this._maxret;
/* 135:    */  }
/* 136:    */  
/* 137:    */  public int getMaxCumulativeAcks()
/* 138:    */  {
/* 139:139 */    return this._maxcumack;
/* 140:    */  }
/* 141:    */  
/* 142:    */  public int getMaxOutOfSequence()
/* 143:    */  {
/* 144:144 */    return this._maxoutseq;
/* 145:    */  }
/* 146:    */  
/* 147:    */  public int getMaxAutoReset()
/* 148:    */  {
/* 149:149 */    return this._maxautorst;
/* 150:    */  }
/* 151:    */  
/* 152:    */  public byte[] getBytes()
/* 153:    */  {
/* 154:154 */    byte[] arrayOfByte = super.getBytes();
/* 155:155 */    arrayOfByte[4] = ((byte)(this._version << 4 & 0xFF));
/* 156:156 */    arrayOfByte[5] = ((byte)(this._maxseg & 0xFF));
/* 157:157 */    arrayOfByte[6] = ((byte)(this._optflags & 0xFF));
/* 158:158 */    arrayOfByte[7] = 0;
/* 159:159 */    arrayOfByte[8] = ((byte)(this._maxsegsize >>> 8 & 0xFF));
/* 160:160 */    arrayOfByte[9] = ((byte)(this._maxsegsize >>> 0 & 0xFF));
/* 161:161 */    arrayOfByte[10] = ((byte)(this._rettoval >>> 8 & 0xFF));
/* 162:162 */    arrayOfByte[11] = ((byte)(this._rettoval >>> 0 & 0xFF));
/* 163:163 */    arrayOfByte[12] = ((byte)(this._cumacktoval >>> 8 & 0xFF));
/* 164:164 */    arrayOfByte[13] = ((byte)(this._cumacktoval >>> 0 & 0xFF));
/* 165:165 */    arrayOfByte[14] = ((byte)(this._niltoval >>> 8 & 0xFF));
/* 166:166 */    arrayOfByte[15] = ((byte)(this._niltoval >>> 0 & 0xFF));
/* 167:167 */    arrayOfByte[16] = ((byte)(this._maxret & 0xFF));
/* 168:168 */    arrayOfByte[17] = ((byte)(this._maxcumack & 0xFF));
/* 169:169 */    arrayOfByte[18] = ((byte)(this._maxoutseq & 0xFF));
/* 170:170 */    arrayOfByte[19] = ((byte)(this._maxautorst & 0xFF));
/* 171:    */    
/* 172:172 */    return arrayOfByte;
/* 173:    */  }
/* 174:    */  
/* 175:    */  protected void parseBytes(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/* 176:    */  {
/* 177:177 */    super.parseBytes(paramArrayOfByte, paramInt1, paramInt2);
/* 178:    */    
/* 179:179 */    if (paramInt2 < 22) {
/* 180:180 */      throw new IllegalArgumentException("Invalid SYN segment");
/* 181:    */    }
/* 182:    */    
/* 183:183 */    this._version = ((paramArrayOfByte[(paramInt1 + 4)] & 0xFF) >>> 4);
/* 184:184 */    if (this._version != 1) {
/* 185:185 */      throw new IllegalArgumentException("Invalid RUDP version");
/* 186:    */    }
/* 187:    */    
/* 188:188 */    this._maxseg = (paramArrayOfByte[(paramInt1 + 5)] & 0xFF);
/* 189:189 */    this._optflags = (paramArrayOfByte[(paramInt1 + 6)] & 0xFF);
/* 190:    */    
/* 191:191 */    this._maxsegsize = ((paramArrayOfByte[(paramInt1 + 8)] & 0xFF) << 8 | (paramArrayOfByte[(paramInt1 + 9)] & 0xFF) << 0);
/* 192:192 */    this._rettoval = ((paramArrayOfByte[(paramInt1 + 10)] & 0xFF) << 8 | (paramArrayOfByte[(paramInt1 + 11)] & 0xFF) << 0);
/* 193:193 */    this._cumacktoval = ((paramArrayOfByte[(paramInt1 + 12)] & 0xFF) << 8 | (paramArrayOfByte[(paramInt1 + 13)] & 0xFF) << 0);
/* 194:194 */    this._niltoval = ((paramArrayOfByte[(paramInt1 + 14)] & 0xFF) << 8 | (paramArrayOfByte[(paramInt1 + 15)] & 0xFF) << 0);
/* 195:195 */    this._maxret = (paramArrayOfByte[(paramInt1 + 16)] & 0xFF);
/* 196:196 */    this._maxcumack = (paramArrayOfByte[(paramInt1 + 17)] & 0xFF);
/* 197:197 */    this._maxoutseq = (paramArrayOfByte[(paramInt1 + 18)] & 0xFF);
/* 198:198 */    this._maxautorst = (paramArrayOfByte[(paramInt1 + 19)] & 0xFF);
/* 199:    */  }
/* 200:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     net.rudp.impl.SYNSegment
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */