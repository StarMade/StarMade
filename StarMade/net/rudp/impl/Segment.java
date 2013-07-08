/*   1:    */package net.rudp.impl;
/*   2:    */
/*   5:    */public abstract class Segment
/*   6:    */{
/*   7:    */  public static final int RUDP_VERSION = 1;
/*   8:    */  
/*  10:    */  public static final int RUDP_HEADER_LEN = 6;
/*  11:    */  
/*  13:    */  public static final byte SYN_FLAG = -128;
/*  14:    */  
/*  16:    */  public static final byte ACK_FLAG = 64;
/*  17:    */  
/*  19:    */  public static final byte EAK_FLAG = 32;
/*  20:    */  
/*  22:    */  public static final byte RST_FLAG = 16;
/*  23:    */  
/*  25:    */  public static final byte NUL_FLAG = 8;
/*  26:    */  
/*  28:    */  public static final byte CHK_FLAG = 4;
/*  29:    */  
/*  31:    */  public static final byte FIN_FLAG = 2;
/*  32:    */  
/*  34:    */  private int _flags;
/*  35:    */  
/*  37:    */  private int _hlen;
/*  38:    */  
/*  40:    */  private int _seqn;
/*  41:    */  
/*  42:    */  private int _ackn;
/*  43:    */  
/*  44:    */  private int _nretx;
/*  45:    */  
/*  47:    */  protected Segment()
/*  48:    */  {
/*  49: 49 */    this._nretx = 0;
/*  50: 50 */    this._ackn = -1;
/*  51:    */  }
/*  52:    */  
/*  53:    */  public abstract String type();
/*  54:    */  
/*  55:    */  public int flags()
/*  56:    */  {
/*  57: 57 */    return this._flags;
/*  58:    */  }
/*  59:    */  
/*  60:    */  public int seq()
/*  61:    */  {
/*  62: 62 */    return this._seqn;
/*  63:    */  }
/*  64:    */  
/*  65:    */  public int length()
/*  66:    */  {
/*  67: 67 */    return this._hlen;
/*  68:    */  }
/*  69:    */  
/*  70:    */  public void setAck(int paramInt)
/*  71:    */  {
/*  72: 72 */    this._flags |= 64;
/*  73: 73 */    this._ackn = paramInt;
/*  74:    */  }
/*  75:    */  
/*  76:    */  public int getAck()
/*  77:    */  {
/*  78: 78 */    if ((this._flags & 0x40) == 64) {
/*  79: 79 */      return this._ackn;
/*  80:    */    }
/*  81:    */    
/*  82: 82 */    return -1;
/*  83:    */  }
/*  84:    */  
/*  85:    */  public int getRetxCounter()
/*  86:    */  {
/*  87: 87 */    return this._nretx;
/*  88:    */  }
/*  89:    */  
/*  90:    */  public void setRetxCounter(int paramInt)
/*  91:    */  {
/*  92: 92 */    this._nretx = paramInt;
/*  93:    */  }
/*  94:    */  
/*  95:    */  public byte[] getBytes()
/*  96:    */  {
/*  97: 97 */    byte[] arrayOfByte = new byte[length()];
/*  98:    */    
/*  99: 99 */    arrayOfByte[0] = ((byte)(this._flags & 0xFF));
/* 100:100 */    arrayOfByte[1] = ((byte)(this._hlen & 0xFF));
/* 101:101 */    arrayOfByte[2] = ((byte)(this._seqn & 0xFF));
/* 102:102 */    arrayOfByte[3] = ((byte)(this._ackn & 0xFF));
/* 103:    */    
/* 104:104 */    return arrayOfByte;
/* 105:    */  }
/* 106:    */  
/* 107:    */  public String toString()
/* 108:    */  {
/* 109:109 */    return type() + " [" + " SEQ = " + seq() + ", ACK = " + (getAck() >= 0 ? "" + getAck() : "N/A") + ", LEN = " + length() + " ]";
/* 110:    */  }
/* 111:    */  
/* 117:    */  public static Segment parse(byte[] paramArrayOfByte)
/* 118:    */  {
/* 119:119 */    return parse(paramArrayOfByte, 0, paramArrayOfByte.length);
/* 120:    */  }
/* 121:    */  
/* 122:    */  public static Segment parse(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/* 123:    */  {
/* 124:124 */    Object localObject = null;
/* 125:    */    
/* 126:126 */    if (paramInt2 < 6) {
/* 127:127 */      throw new IllegalArgumentException("Invalid segment");
/* 128:    */    }
/* 129:    */    
/* 130:130 */    int i = paramArrayOfByte[paramInt1];
/* 131:131 */    if ((i & 0xFFFFFF80) != 0) {
/* 132:132 */      localObject = new SYNSegment();
/* 133:    */    }
/* 134:134 */    else if ((i & 0x8) != 0) {
/* 135:135 */      localObject = new NULSegment();
/* 136:    */    }
/* 137:137 */    else if ((i & 0x20) != 0) {
/* 138:138 */      localObject = new EAKSegment();
/* 139:    */    }
/* 140:140 */    else if ((i & 0x10) != 0) {
/* 141:141 */      localObject = new RSTSegment();
/* 142:    */    }
/* 143:143 */    else if ((i & 0x2) != 0) {
/* 144:144 */      localObject = new FINSegment();
/* 145:    */    }
/* 146:146 */    else if ((i & 0x40) != 0) {
/* 147:147 */      if (paramInt2 == 6) {
/* 148:148 */        localObject = new ACKSegment();
/* 149:    */      }
/* 150:    */      else {
/* 151:151 */        localObject = new DATSegment();
/* 152:    */      }
/* 153:    */    }
/* 154:    */    
/* 155:155 */    if (localObject == null) {
/* 156:156 */      throw new IllegalArgumentException("Invalid segment");
/* 157:    */    }
/* 158:    */    
/* 159:159 */    ((Segment)localObject).parseBytes(paramArrayOfByte, paramInt1, paramInt2);
/* 160:160 */    return localObject;
/* 161:    */  }
/* 162:    */  
/* 178:    */  protected void init(int paramInt1, int paramInt2, int paramInt3)
/* 179:    */  {
/* 180:180 */    this._flags = paramInt1;
/* 181:181 */    this._seqn = paramInt2;
/* 182:182 */    this._hlen = paramInt3;
/* 183:    */  }
/* 184:    */  
/* 185:    */  protected void parseBytes(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/* 186:    */  {
/* 187:187 */    this._flags = (paramArrayOfByte[paramInt1] & 0xFF);
/* 188:188 */    this._hlen = (paramArrayOfByte[(paramInt1 + 1)] & 0xFF);
/* 189:189 */    this._seqn = (paramArrayOfByte[(paramInt1 + 2)] & 0xFF);
/* 190:190 */    this._ackn = (paramArrayOfByte[(paramInt1 + 3)] & 0xFF);
/* 191:    */  }
/* 192:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     net.rudp.impl.Segment
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */