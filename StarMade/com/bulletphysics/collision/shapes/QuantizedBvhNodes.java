/*   1:    */package com.bulletphysics.collision.shapes;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */
/*  45:    */public class QuantizedBvhNodes
/*  46:    */  implements Serializable
/*  47:    */{
/*  48:    */  private static final long serialVersionUID = 1L;
/*  49:    */  private static final int STRIDE = 4;
/*  50:    */  private int[] buf;
/*  51: 51 */  private int size = 0;
/*  52:    */  
/*  53:    */  public QuantizedBvhNodes() {
/*  54: 54 */    resize(16);
/*  55:    */  }
/*  56:    */  
/*  57:    */  public int add() {
/*  58: 58 */    while (this.size + 1 >= capacity()) {
/*  59: 59 */      resize(capacity() * 2);
/*  60:    */    }
/*  61: 61 */    return this.size++;
/*  62:    */  }
/*  63:    */  
/*  64:    */  public int size() {
/*  65: 65 */    return this.size;
/*  66:    */  }
/*  67:    */  
/*  68:    */  public int capacity() {
/*  69: 69 */    return this.buf.length / 4;
/*  70:    */  }
/*  71:    */  
/*  72:    */  public void clear() {
/*  73: 73 */    this.size = 0;
/*  74:    */  }
/*  75:    */  
/*  76:    */  public void resize(int num) {
/*  77: 77 */    int[] oldBuf = this.buf;
/*  78:    */    
/*  79: 79 */    this.buf = new int[num * 4];
/*  80: 80 */    if (oldBuf != null) {
/*  81: 81 */      System.arraycopy(oldBuf, 0, this.buf, 0, Math.min(oldBuf.length, this.buf.length));
/*  82:    */    }
/*  83:    */  }
/*  84:    */  
/*  85:    */  public static int getNodeSize() {
/*  86: 86 */    return 16;
/*  87:    */  }
/*  88:    */  
/*  91:    */  public void set(int destId, QuantizedBvhNodes srcNodes, int srcId)
/*  92:    */  {
/*  93: 93 */    int[] buf = this.buf;
/*  94: 94 */    int[] srcBuf = srcNodes.buf;
/*  95:    */    
/*  96: 96 */    buf[(destId * 4 + 0)] = srcBuf[(srcId * 4 + 0)];
/*  97: 97 */    buf[(destId * 4 + 1)] = srcBuf[(srcId * 4 + 1)];
/*  98: 98 */    buf[(destId * 4 + 2)] = srcBuf[(srcId * 4 + 2)];
/*  99: 99 */    buf[(destId * 4 + 3)] = srcBuf[(srcId * 4 + 3)];
/* 100:    */  }
/* 101:    */  
/* 104:    */  public void swap(int id1, int id2)
/* 105:    */  {
/* 106:106 */    int[] buf = this.buf;
/* 107:    */    
/* 108:108 */    int temp0 = buf[(id1 * 4 + 0)];
/* 109:109 */    int temp1 = buf[(id1 * 4 + 1)];
/* 110:110 */    int temp2 = buf[(id1 * 4 + 2)];
/* 111:111 */    int temp3 = buf[(id1 * 4 + 3)];
/* 112:    */    
/* 113:113 */    buf[(id1 * 4 + 0)] = buf[(id2 * 4 + 0)];
/* 114:114 */    buf[(id1 * 4 + 1)] = buf[(id2 * 4 + 1)];
/* 115:115 */    buf[(id1 * 4 + 2)] = buf[(id2 * 4 + 2)];
/* 116:116 */    buf[(id1 * 4 + 3)] = buf[(id2 * 4 + 3)];
/* 117:    */    
/* 118:118 */    buf[(id2 * 4 + 0)] = temp0;
/* 119:119 */    buf[(id2 * 4 + 1)] = temp1;
/* 120:120 */    buf[(id2 * 4 + 2)] = temp2;
/* 121:121 */    buf[(id2 * 4 + 3)] = temp3;
/* 122:    */  }
/* 123:    */  
/* 124:    */  public int getQuantizedAabbMin(int nodeId, int index) {
/* 125:125 */    switch (index) {
/* 126:    */    case 0: default: 
/* 127:127 */      return this.buf[(nodeId * 4 + 0)] & 0xFFFF;
/* 128:128 */    case 1:  return this.buf[(nodeId * 4 + 0)] >>> 16 & 0xFFFF; }
/* 129:129 */    return this.buf[(nodeId * 4 + 1)] & 0xFFFF;
/* 130:    */  }
/* 131:    */  
/* 132:    */  public long getQuantizedAabbMin(int nodeId)
/* 133:    */  {
/* 134:134 */    return this.buf[(nodeId * 4 + 0)] & 0xFFFFFFFF | (this.buf[(nodeId * 4 + 1)] & 0xFFFF) << 32;
/* 135:    */  }
/* 136:    */  
/* 137:    */  public void setQuantizedAabbMin(int nodeId, long value) {
/* 138:138 */    this.buf[(nodeId * 4 + 0)] = ((int)value);
/* 139:139 */    setQuantizedAabbMin(nodeId, 2, (short)(int)((value & 0x0) >>> 32));
/* 140:    */  }
/* 141:    */  
/* 142:    */  public void setQuantizedAabbMax(int nodeId, long value) {
/* 143:143 */    setQuantizedAabbMax(nodeId, 0, (short)(int)value);
/* 144:144 */    this.buf[(nodeId * 4 + 2)] = ((int)(value >>> 16));
/* 145:    */  }
/* 146:    */  
/* 147:    */  public void setQuantizedAabbMin(int nodeId, int index, int value) {
/* 148:148 */    switch (index) {
/* 149:149 */    case 0:  this.buf[(nodeId * 4 + 0)] = (this.buf[(nodeId * 4 + 0)] & 0xFFFF0000 | value & 0xFFFF);break;
/* 150:150 */    case 1:  this.buf[(nodeId * 4 + 0)] = (this.buf[(nodeId * 4 + 0)] & 0xFFFF | (value & 0xFFFF) << 16);break;
/* 151:151 */    case 2:  this.buf[(nodeId * 4 + 1)] = (this.buf[(nodeId * 4 + 1)] & 0xFFFF0000 | value & 0xFFFF);
/* 152:    */    }
/* 153:    */  }
/* 154:    */  
/* 155:    */  public int getQuantizedAabbMax(int nodeId, int index) {
/* 156:156 */    switch (index) {
/* 157:    */    case 0: default: 
/* 158:158 */      return this.buf[(nodeId * 4 + 1)] >>> 16 & 0xFFFF;
/* 159:159 */    case 1:  return this.buf[(nodeId * 4 + 2)] & 0xFFFF; }
/* 160:160 */    return this.buf[(nodeId * 4 + 2)] >>> 16 & 0xFFFF;
/* 161:    */  }
/* 162:    */  
/* 163:    */  public long getQuantizedAabbMax(int nodeId)
/* 164:    */  {
/* 165:165 */    return (this.buf[(nodeId * 4 + 1)] & 0xFFFF0000) >>> 16 | (this.buf[(nodeId * 4 + 2)] & 0xFFFFFFFF) << 16;
/* 166:    */  }
/* 167:    */  
/* 168:    */  public void setQuantizedAabbMax(int nodeId, int index, int value) {
/* 169:169 */    switch (index) {
/* 170:170 */    case 0:  this.buf[(nodeId * 4 + 1)] = (this.buf[(nodeId * 4 + 1)] & 0xFFFF | (value & 0xFFFF) << 16);break;
/* 171:171 */    case 1:  this.buf[(nodeId * 4 + 2)] = (this.buf[(nodeId * 4 + 2)] & 0xFFFF0000 | value & 0xFFFF);break;
/* 172:172 */    case 2:  this.buf[(nodeId * 4 + 2)] = (this.buf[(nodeId * 4 + 2)] & 0xFFFF | (value & 0xFFFF) << 16);
/* 173:    */    }
/* 174:    */  }
/* 175:    */  
/* 176:    */  public int getEscapeIndexOrTriangleIndex(int nodeId) {
/* 177:177 */    return this.buf[(nodeId * 4 + 3)];
/* 178:    */  }
/* 179:    */  
/* 180:    */  public void setEscapeIndexOrTriangleIndex(int nodeId, int value) {
/* 181:181 */    this.buf[(nodeId * 4 + 3)] = value;
/* 182:    */  }
/* 183:    */  
/* 184:    */  public boolean isLeafNode(int nodeId)
/* 185:    */  {
/* 186:186 */    return getEscapeIndexOrTriangleIndex(nodeId) >= 0;
/* 187:    */  }
/* 188:    */  
/* 189:    */  public int getEscapeIndex(int nodeId) {
/* 190:190 */    assert (!isLeafNode(nodeId));
/* 191:191 */    return -getEscapeIndexOrTriangleIndex(nodeId);
/* 192:    */  }
/* 193:    */  
/* 194:    */  public int getTriangleIndex(int nodeId) {
/* 195:195 */    assert (isLeafNode(nodeId));
/* 196:    */    
/* 197:197 */    return getEscapeIndexOrTriangleIndex(nodeId) & 0x1FFFFF;
/* 198:    */  }
/* 199:    */  
/* 200:    */  public int getPartId(int nodeId) {
/* 201:201 */    assert (isLeafNode(nodeId));
/* 202:    */    
/* 203:203 */    return getEscapeIndexOrTriangleIndex(nodeId) >>> 21;
/* 204:    */  }
/* 205:    */  
/* 206:    */  public static int getCoord(long vec, int index) {
/* 207:207 */    switch (index) {
/* 208:    */    case 0: default: 
/* 209:209 */      return (int)(vec & 0xFFFF) & 0xFFFF;
/* 210:210 */    case 1:  return (int)((vec & 0xFFFF0000) >>> 16) & 0xFFFF; }
/* 211:211 */    return (int)((vec & 0x0) >>> 32) & 0xFFFF;
/* 212:    */  }
/* 213:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.QuantizedBvhNodes
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */