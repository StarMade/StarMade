/*   1:    */package org.lwjgl.util.glu;
/*   2:    */
/*   3:    */import java.nio.IntBuffer;
/*   4:    */import org.lwjgl.BufferUtils;
/*   5:    */import org.lwjgl.opengl.GL11;
/*   6:    */
/*  52:    */public class Util
/*  53:    */{
/*  54: 54 */  private static IntBuffer scratch = BufferUtils.createIntBuffer(16);
/*  55:    */  
/*  63:    */  protected static int ceil(int a, int b)
/*  64:    */  {
/*  65: 65 */    return a % b == 0 ? a / b : a / b + 1;
/*  66:    */  }
/*  67:    */  
/*  76:    */  protected static float[] normalize(float[] v)
/*  77:    */  {
/*  78: 78 */    float r = (float)Math.sqrt(v[0] * v[0] + v[1] * v[1] + v[2] * v[2]);
/*  79: 79 */    if (r == 0.0D) {
/*  80: 80 */      return v;
/*  81:    */    }
/*  82: 82 */    r = 1.0F / r;
/*  83:    */    
/*  84: 84 */    v[0] *= r;
/*  85: 85 */    v[1] *= r;
/*  86: 86 */    v[2] *= r;
/*  87:    */    
/*  88: 88 */    return v;
/*  89:    */  }
/*  90:    */  
/*  97:    */  protected static void cross(float[] v1, float[] v2, float[] result)
/*  98:    */  {
/*  99: 99 */    result[0] = (v1[1] * v2[2] - v1[2] * v2[1]);
/* 100:100 */    result[1] = (v1[2] * v2[0] - v1[0] * v2[2]);
/* 101:101 */    result[2] = (v1[0] * v2[1] - v1[1] * v2[0]);
/* 102:    */  }
/* 103:    */  
/* 111:    */  protected static int compPerPix(int format)
/* 112:    */  {
/* 113:113 */    switch (format) {
/* 114:    */    case 6400: 
/* 115:    */    case 6401: 
/* 116:    */    case 6402: 
/* 117:    */    case 6403: 
/* 118:    */    case 6404: 
/* 119:    */    case 6405: 
/* 120:    */    case 6406: 
/* 121:    */    case 6409: 
/* 122:122 */      return 1;
/* 123:    */    case 6410: 
/* 124:124 */      return 2;
/* 125:    */    case 6407: 
/* 126:    */    case 32992: 
/* 127:127 */      return 3;
/* 128:    */    case 6408: 
/* 129:    */    case 32993: 
/* 130:130 */      return 4;
/* 131:    */    }
/* 132:132 */    return -1;
/* 133:    */  }
/* 134:    */  
/* 146:    */  protected static int nearestPower(int value)
/* 147:    */  {
/* 148:148 */    int i = 1;
/* 149:    */    
/* 151:151 */    if (value == 0) {
/* 152:152 */      return -1;
/* 153:    */    }
/* 154:    */    for (;;) {
/* 155:155 */      if (value == 1)
/* 156:156 */        return i;
/* 157:157 */      if (value == 3) {
/* 158:158 */        return i << 2;
/* 159:    */      }
/* 160:160 */      value >>= 1;
/* 161:161 */      i <<= 1;
/* 162:    */    }
/* 163:    */  }
/* 164:    */  
/* 169:    */  protected static int bytesPerPixel(int format, int type)
/* 170:    */  {
/* 171:    */    int n;
/* 172:    */    
/* 176:176 */    switch (format) {
/* 177:    */    case 6400: 
/* 178:    */    case 6401: 
/* 179:    */    case 6402: 
/* 180:    */    case 6403: 
/* 181:    */    case 6404: 
/* 182:    */    case 6405: 
/* 183:    */    case 6406: 
/* 184:    */    case 6409: 
/* 185:185 */      n = 1;
/* 186:186 */      break;
/* 187:    */    case 6410: 
/* 188:188 */      n = 2;
/* 189:189 */      break;
/* 190:    */    case 6407: 
/* 191:    */    case 32992: 
/* 192:192 */      n = 3;
/* 193:193 */      break;
/* 194:    */    case 6408: 
/* 195:    */    case 32993: 
/* 196:196 */      n = 4;
/* 197:197 */      break;
/* 198:    */    default: 
/* 199:199 */      n = 0;
/* 200:    */    }
/* 201:    */    int m;
/* 202:202 */    switch (type) {
/* 203:    */    case 5121: 
/* 204:204 */      m = 1;
/* 205:205 */      break;
/* 206:    */    case 5120: 
/* 207:207 */      m = 1;
/* 208:208 */      break;
/* 209:    */    case 6656: 
/* 210:210 */      m = 1;
/* 211:211 */      break;
/* 212:    */    case 5123: 
/* 213:213 */      m = 2;
/* 214:214 */      break;
/* 215:    */    case 5122: 
/* 216:216 */      m = 2;
/* 217:217 */      break;
/* 218:    */    case 5125: 
/* 219:219 */      m = 4;
/* 220:220 */      break;
/* 221:    */    case 5124: 
/* 222:222 */      m = 4;
/* 223:223 */      break;
/* 224:    */    case 5126: 
/* 225:225 */      m = 4;
/* 226:226 */      break;
/* 227:    */    default: 
/* 228:228 */      m = 0;
/* 229:    */    }
/* 230:    */    
/* 231:231 */    return n * m;
/* 232:    */  }
/* 233:    */  
/* 240:    */  protected static int glGetIntegerv(int what)
/* 241:    */  {
/* 242:242 */    scratch.rewind();
/* 243:243 */    GL11.glGetInteger(what, scratch);
/* 244:244 */    return scratch.get();
/* 245:    */  }
/* 246:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.glu.Util
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */