/*   1:    */package net.rudp;
/*   2:    */
/*   5:    */public class ReliableSocketProfile
/*   6:    */{
/*   7:    */  public static final int MAX_SEND_QUEUE_SIZE = 32;
/*   8:    */  
/*  10:    */  public static final int MAX_RECV_QUEUE_SIZE = 32;
/*  11:    */  
/*  13:    */  public static final int MAX_SEGMENT_SIZE = 128;
/*  14:    */  
/*  16:    */  public static final int MAX_OUTSTANDING_SEGS = 3;
/*  17:    */  
/*  19:    */  public static final int MAX_RETRANS = 3;
/*  20:    */  
/*  22:    */  public static final int MAX_CUMULATIVE_ACKS = 3;
/*  23:    */  
/*  25:    */  public static final int MAX_OUT_OF_SEQUENCE = 3;
/*  26:    */  
/*  28:    */  public static final int MAX_AUTO_RESET = 3;
/*  29:    */  
/*  31:    */  public static final int NULL_SEGMENT_TIMEOUT = 2000;
/*  32:    */  
/*  34:    */  public static final int RETRANSMISSION_TIMEOUT = 600;
/*  35:    */  
/*  37:    */  public static final int CUMULATIVE_ACK_TIMEOUT = 300;
/*  38:    */  
/*  40:    */  private int _maxSendQueueSize;
/*  41:    */  
/*  43:    */  private int _maxRecvQueueSize;
/*  44:    */  
/*  46:    */  private int _maxSegmentSize;
/*  47:    */  
/*  48:    */  private int _maxOutstandingSegs;
/*  49:    */  
/*  50:    */  private int _maxRetrans;
/*  51:    */  
/*  52:    */  private int _maxCumulativeAcks;
/*  53:    */  
/*  54:    */  private int _maxOutOfSequence;
/*  55:    */  
/*  56:    */  private int _maxAutoReset;
/*  57:    */  
/*  58:    */  private int _nullSegmentTimeout;
/*  59:    */  
/*  60:    */  private int _retransmissionTimeout;
/*  61:    */  
/*  62:    */  private int _cumulativeAckTimeout;
/*  63:    */  
/*  65:    */  public ReliableSocketProfile()
/*  66:    */  {
/*  67: 67 */    this(32, 32, 128, 3, 0, 3, 3, 3, 2000, 600, 300);
/*  68:    */  }
/*  69:    */  
/* 105:    */  public ReliableSocketProfile(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11)
/* 106:    */  {
/* 107:107 */    checkValue("maxSendQueueSize", paramInt1, 1, 255);
/* 108:108 */    checkValue("maxRecvQueueSize", paramInt2, 1, 255);
/* 109:109 */    checkValue("maxSegmentSize", paramInt3, 22, 65535);
/* 110:110 */    checkValue("maxOutstandingSegs", paramInt4, 1, 255);
/* 111:111 */    checkValue("maxRetrans", paramInt5, 0, 255);
/* 112:112 */    checkValue("maxCumulativeAcks", paramInt6, 0, 255);
/* 113:113 */    checkValue("maxOutOfSequence", paramInt7, 0, 255);
/* 114:114 */    checkValue("maxAutoReset", paramInt8, 0, 255);
/* 115:115 */    checkValue("nullSegmentTimeout", paramInt9, 0, 65535);
/* 116:116 */    checkValue("retransmissionTimeout", paramInt10, 100, 65535);
/* 117:117 */    checkValue("cumulativeAckTimeout", paramInt11, 100, 65535);
/* 118:    */    
/* 119:119 */    this._maxSendQueueSize = paramInt1;
/* 120:120 */    this._maxRecvQueueSize = paramInt2;
/* 121:121 */    this._maxSegmentSize = paramInt3;
/* 122:122 */    this._maxOutstandingSegs = paramInt4;
/* 123:123 */    this._maxRetrans = paramInt5;
/* 124:124 */    this._maxCumulativeAcks = paramInt6;
/* 125:125 */    this._maxOutOfSequence = paramInt7;
/* 126:126 */    this._maxAutoReset = paramInt8;
/* 127:127 */    this._nullSegmentTimeout = paramInt9;
/* 128:128 */    this._retransmissionTimeout = paramInt10;
/* 129:129 */    this._cumulativeAckTimeout = paramInt11;
/* 130:    */  }
/* 131:    */  
/* 135:    */  public int maxSendQueueSize()
/* 136:    */  {
/* 137:137 */    return this._maxSendQueueSize;
/* 138:    */  }
/* 139:    */  
/* 143:    */  public int maxRecvQueueSize()
/* 144:    */  {
/* 145:145 */    return this._maxRecvQueueSize;
/* 146:    */  }
/* 147:    */  
/* 151:    */  public int maxSegmentSize()
/* 152:    */  {
/* 153:153 */    return this._maxSegmentSize;
/* 154:    */  }
/* 155:    */  
/* 159:    */  public int maxOutstandingSegs()
/* 160:    */  {
/* 161:161 */    return this._maxOutstandingSegs;
/* 162:    */  }
/* 163:    */  
/* 167:    */  public int maxRetrans()
/* 168:    */  {
/* 169:169 */    return this._maxRetrans;
/* 170:    */  }
/* 171:    */  
/* 175:    */  public int maxCumulativeAcks()
/* 176:    */  {
/* 177:177 */    return this._maxCumulativeAcks;
/* 178:    */  }
/* 179:    */  
/* 183:    */  public int maxOutOfSequence()
/* 184:    */  {
/* 185:185 */    return this._maxOutOfSequence;
/* 186:    */  }
/* 187:    */  
/* 191:    */  public int maxAutoReset()
/* 192:    */  {
/* 193:193 */    return this._maxAutoReset;
/* 194:    */  }
/* 195:    */  
/* 199:    */  public int nullSegmentTimeout()
/* 200:    */  {
/* 201:201 */    return this._nullSegmentTimeout;
/* 202:    */  }
/* 203:    */  
/* 207:    */  public int retransmissionTimeout()
/* 208:    */  {
/* 209:209 */    return this._retransmissionTimeout;
/* 210:    */  }
/* 211:    */  
/* 215:    */  public int cumulativeAckTimeout()
/* 216:    */  {
/* 217:217 */    return this._cumulativeAckTimeout;
/* 218:    */  }
/* 219:    */  
/* 220:    */  public String toString()
/* 221:    */  {
/* 222:222 */    StringBuilder localStringBuilder = new StringBuilder();
/* 223:223 */    localStringBuilder.append("[");
/* 224:224 */    localStringBuilder.append(this._maxSendQueueSize).append(", ");
/* 225:225 */    localStringBuilder.append(this._maxRecvQueueSize).append(", ");
/* 226:226 */    localStringBuilder.append(this._maxSegmentSize).append(", ");
/* 227:227 */    localStringBuilder.append(this._maxOutstandingSegs).append(", ");
/* 228:228 */    localStringBuilder.append(this._maxRetrans).append(", ");
/* 229:229 */    localStringBuilder.append(this._maxCumulativeAcks).append(", ");
/* 230:230 */    localStringBuilder.append(this._maxOutOfSequence).append(", ");
/* 231:231 */    localStringBuilder.append(this._maxAutoReset).append(", ");
/* 232:232 */    localStringBuilder.append(this._nullSegmentTimeout).append(", ");
/* 233:233 */    localStringBuilder.append(this._retransmissionTimeout).append(", ");
/* 234:234 */    localStringBuilder.append(this._cumulativeAckTimeout);
/* 235:235 */    localStringBuilder.append("]");
/* 236:236 */    return localStringBuilder.toString();
/* 237:    */  }
/* 238:    */  
/* 242:    */  private void checkValue(String paramString, int paramInt1, int paramInt2, int paramInt3)
/* 243:    */  {
/* 244:244 */    if ((paramInt1 < paramInt2) || (paramInt1 > paramInt3)) {
/* 245:245 */      throw new IllegalArgumentException(paramString);
/* 246:    */    }
/* 247:    */  }
/* 248:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     net.rudp.ReliableSocketProfile
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */