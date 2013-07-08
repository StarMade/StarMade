/*   1:    */package org.schema.game.common.data.element;
/*   2:    */
/*   3:    */import javax.vecmath.Vector3f;
/*   4:    */import o;
/*   5:    */import q;
/*   6:    */
/*  25:    */public abstract class Element
/*  26:    */{
/*  27:    */  public static byte[] orientationMapping;
/*  28:    */  public static byte[] orientationBackMapping;
/*  29:    */  public static final float HALF_SIZE = 1.0F;
/*  30:    */  public static final short TYPE_BLENDED_START = -1;
/*  31:    */  public static final short TYPE_NONE = 0;
/*  32:    */  public static final short TYPE_ALL = 32767;
/*  33:    */  public static final int RIGHT = 0;
/*  34:    */  public static final int LEFT = 1;
/*  35:    */  public static final int TOP = 2;
/*  36:    */  public static final int BOTTOM = 3;
/*  37:    */  public static final int FRONT = 4;
/*  38:    */  public static final int BACK = 5;
/*  39:    */  public static final int TOP_FRONT = 0;
/*  40:    */  public static final int TOP_RIGHT = 1;
/*  41:    */  public static final int TOP_BACK = 2;
/*  42:    */  public static final int TOP_LEFT = 3;
/*  43:    */  public static final int BACK_FRONT = 4;
/*  44:    */  public static final int BACK_RIGHT = 5;
/*  45:    */  public static final int BACK_BACK = 6;
/*  46:    */  public static final int BACK_LEFT = 7;
/*  47:    */  public static final int TOP_FRONT_B = 8;
/*  48:    */  public static final int TOP_RIGHT_B = 9;
/*  49:    */  public static final int TOP_BACK_B = 10;
/*  50:    */  public static final int TOP_LEFT_B = 11;
/*  51:    */  public static final int BACK_FRONT_B = 12;
/*  52:    */  public static final int BACK_RIGHT_B = 13;
/*  53:    */  public static final int BACK_BACK_B = 14;
/*  54:    */  public static final int BACK_LEFT_B = 15;
/*  55:    */  
/*  56:    */  public static int getRelativeOrientation(Vector3f paramVector3f)
/*  57:    */  {
/*  58: 58 */    int i = 0;
/*  59: 59 */    if ((Math.abs(paramVector3f.x) >= Math.abs(paramVector3f.y)) && (Math.abs(paramVector3f.x) >= Math.abs(paramVector3f.z)))
/*  60:    */    {
/*  61: 61 */      if (paramVector3f.x >= 0.0F) {
/*  62: 62 */        i = 0;
/*  63:    */      } else {
/*  64: 64 */        i = 1;
/*  65:    */      }
/*  66: 66 */    } else if ((Math.abs(paramVector3f.y) >= Math.abs(paramVector3f.x)) && (Math.abs(paramVector3f.y) >= Math.abs(paramVector3f.z)))
/*  67:    */    {
/*  68: 68 */      if (paramVector3f.y >= 0.0F) {
/*  69: 69 */        i = 2;
/*  70:    */      } else {
/*  71: 71 */        i = 3;
/*  72:    */      }
/*  73:    */    }
/*  74: 74 */    else if ((Math.abs(paramVector3f.z) >= Math.abs(paramVector3f.y)) && (Math.abs(paramVector3f.z) >= Math.abs(paramVector3f.x)))
/*  75:    */    {
/*  77: 77 */      if (paramVector3f.z >= 0.0F) {
/*  78: 78 */        i = 4;
/*  79:    */      } else {
/*  80: 80 */        i = 5;
/*  81:    */      }
/*  82:    */    }
/*  83:    */    
/*  84: 84 */    return i; }
/*  85:    */  
/*  86: 86 */  public static final int[] OPPOSITE_SIDE = { 1, 0, 3, 2, 5, 4 };
/*  87:    */  
/*  89:    */  public static final int FLAG_RIGHT = 1;
/*  90:    */  
/*  92:    */  public static final int FLAG_LEFT = 2;
/*  93:    */  
/*  95:    */  public static final int FLAG_TOP = 4;
/*  96:    */  
/*  98:    */  public static final int FLAG_BOTTOM = 8;
/*  99:    */  
/* 100:    */  public static final int FLAG_FRONT = 16;
/* 101:    */  
/* 102:    */  public static final int FLAG_BACK = 32;
/* 103:    */  
/* 104:104 */  public static final int[] SIDE_FLAG = { 1, 2, 4, 8, 16, 32 };
/* 105:    */  
/* 106:106 */  public static final o[] DIRECTIONSb = { new o(1.0F, 0.0F, 0.0F), new o(-1.0F, 0.0F, 0.0F), new o(0.0F, 1.0F, 0.0F), new o(0.0F, -1.0F, 0.0F), new o(0.0F, 0.0F, 1.0F), new o(0.0F, 0.0F, -1.0F) };
/* 107:    */  
/* 111:111 */  public static final q[] DIRECTIONSi = { new q(1, 0, 0), new q(-1, 0, 0), new q(0, 1, 0), new q(0, -1, 0), new q(0, 0, 1), new q(0, 0, -1) };
/* 112:    */  
/* 116:116 */  public static final Vector3f[] DIRECTIONSf = { new Vector3f(1.0F, 0.0F, 0.0F), new Vector3f(-1.0F, 0.0F, 0.0F), new Vector3f(0.0F, 1.0F, 0.0F), new Vector3f(0.0F, -1.0F, 0.0F), new Vector3f(0.0F, 0.0F, 1.0F), new Vector3f(0.0F, 0.0F, -1.0F) };
/* 117:    */  
/* 119:    */  public static final int SIDE_INDEX_COUNT = 4;
/* 120:    */  
/* 121:    */  public static final int INDEX_BOTTOM = 0;
/* 122:    */  
/* 123:    */  public static final int INDEX_TOP = 4;
/* 124:    */  
/* 125:    */  public static final int INDEX_FRONT = 8;
/* 126:    */  
/* 127:    */  public static final int INDEX_BACK = 16;
/* 128:    */  
/* 129:    */  public static final int INDEX_LEFT = 12;
/* 130:    */  
/* 131:    */  public static final int INDEX_RIGHT = 20;
/* 132:    */  
/* 133:133 */  public static byte FULLVIS = 63;
/* 134:    */  
/* 140:    */  private static final float margin = 0.001F;
/* 141:    */  
/* 147:    */  public static int countBits(int paramInt)
/* 148:    */  {
/* 149:149 */    return ((paramInt = ((paramInt = ((paramInt = ((paramInt = (paramInt >>> 1 & 0x55555555) + (paramInt & 0x55555555)) >>> 2 & 0x33333333) + (paramInt & 0x33333333)) >>> 4 & 0xF0F0F0F) + (paramInt & 0xF0F0F0F)) >>> 8 & 0xFF00FF) + (paramInt & 0xFF00FF)) >>> 16) + (paramInt & 0xFFFF);
/* 150:    */  }
/* 151:    */  
/* 152:    */  private static void createOrientationMapping() {
/* 153:153 */    (Element.orientationMapping = new byte[16])[4] = 0;
/* 154:154 */    orientationMapping[5] = 1;
/* 155:155 */    orientationMapping[2] = 2;
/* 156:156 */    orientationMapping[3] = 3;
/* 157:157 */    orientationMapping[1] = 4;
/* 158:158 */    orientationMapping[0] = 5;
/* 159:159 */    orientationMapping[6] = 6;
/* 160:160 */    orientationMapping[7] = 7;
/* 161:161 */    orientationMapping[12] = 8;
/* 162:162 */    orientationMapping[13] = 9;
/* 163:163 */    orientationMapping[10] = 10;
/* 164:164 */    orientationMapping[11] = 11;
/* 165:165 */    orientationMapping[9] = 12;
/* 166:166 */    orientationMapping[8] = 13;
/* 167:167 */    orientationMapping[14] = 14;
/* 168:168 */    orientationMapping[15] = 15;
/* 169:    */    
/* 171:171 */    (
/* 172:172 */      Element.orientationBackMapping = new byte[16])[0] = 4;
/* 173:173 */    orientationBackMapping[1] = 5;
/* 174:174 */    orientationBackMapping[2] = 2;
/* 175:175 */    orientationBackMapping[3] = 3;
/* 176:176 */    orientationBackMapping[4] = 1;
/* 177:177 */    orientationBackMapping[5] = 0;
/* 178:178 */    orientationBackMapping[6] = 6;
/* 179:179 */    orientationBackMapping[7] = 7;
/* 180:180 */    orientationBackMapping[8] = 12;
/* 181:181 */    orientationBackMapping[9] = 13;
/* 182:182 */    orientationBackMapping[10] = 10;
/* 183:183 */    orientationBackMapping[11] = 11;
/* 184:184 */    orientationBackMapping[12] = 9;
/* 185:185 */    orientationBackMapping[13] = 8;
/* 186:186 */    orientationBackMapping[14] = 14;
/* 187:187 */    orientationBackMapping[15] = 15;
/* 188:    */  }
/* 189:    */  
/* 202:202 */  public static int getSide(Vector3f paramVector3f, q paramq) { return getSide(paramVector3f, paramq, 0.001F); }
/* 203:    */  
/* 204:    */  public static int getSide(Vector3f paramVector3f, q paramq, float paramFloat) {
/* 205:    */    for (;;) {
/* 206:206 */      float f1 = paramq.a - 0.5F;
/* 207:    */      
/* 209:209 */      float f2 = paramq.b - 0.5F;
/* 210:210 */      float f3 = paramq.c - 0.5F;
/* 211:    */      
/* 212:212 */      float f4 = paramq.a + 0.5F;
/* 213:213 */      float f5 = paramq.b + 0.5F;
/* 214:214 */      float f6 = paramq.c + 0.5F;
/* 215:    */      
/* 217:217 */      if (paramVector3f.x >= f4 - paramFloat)
/* 218:    */      {
/* 219:219 */        return 0;
/* 220:    */      }
/* 221:221 */      if (paramVector3f.y >= f5 - paramFloat)
/* 222:    */      {
/* 223:223 */        return 2;
/* 224:    */      }
/* 225:225 */      if (paramVector3f.z >= f6 - paramFloat)
/* 226:    */      {
/* 227:227 */        return 4;
/* 228:    */      }
/* 229:229 */      if (paramVector3f.x <= f1 + paramFloat)
/* 230:230 */        return 1;
/* 231:231 */      if (paramVector3f.y <= f2 + paramFloat)
/* 232:232 */        return 3;
/* 233:233 */      if (paramVector3f.z <= f3 + paramFloat) {
/* 234:234 */        return 5;
/* 235:    */      }
/* 236:236 */      if (paramFloat >= 0.5F)
/* 237:    */        break;
/* 238:238 */      paramFloat *= 2.0F;
/* 239:    */    }
/* 240:    */    
/* 241:241 */    return -1;
/* 242:    */  }
/* 243:    */  
/* 244:244 */  public static String getSideString(int paramInt) { switch (paramInt) {
/* 245:245 */    case 1:  return "LEFT";
/* 246:246 */    case 0:  return "RIGHT";
/* 247:247 */    case 2:  return "TOP";
/* 248:248 */    case 3:  return "BOTTOM";
/* 249:249 */    case 4:  return "FRONT";
/* 250:250 */    case 5:  return "BACK";
/* 251:    */    }
/* 252:252 */    return "[WARNING] UNKNOWN SIDE";
/* 253:    */  }
/* 254:    */  
/* 256:256 */  public String toString() { return "ELEMENT"; }
/* 257:    */  
/* 258:    */  public static int getOpposite(int paramInt) {
/* 259:259 */    switch (paramInt) {
/* 260:260 */    case 1:  return 0;
/* 261:261 */    case 0:  return 1;
/* 262:262 */    case 2:  return 3;
/* 263:263 */    case 3:  return 2;
/* 264:264 */    case 4:  return 5;
/* 265:265 */    case 5:  return 4;
/* 266:    */    }
/* 267:267 */    throw new RuntimeException("SIDE NOT FOUND: " + paramInt);
/* 268:    */  }
/* 269:    */  
/* 270:    */  static {}
/* 271:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.Element
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */