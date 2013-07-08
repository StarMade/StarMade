/*   1:    */package com.bulletphysics.linearmath;
/*   2:    */
/*   3:    */import javax.vecmath.Vector3f;
/*   4:    */import javax.vecmath.Vector4f;
/*   5:    */
/*  33:    */public class VectorUtil
/*  34:    */{
/*  35:    */  public static int maxAxis(Vector3f v)
/*  36:    */  {
/*  37: 37 */    int maxIndex = -1;
/*  38: 38 */    float maxVal = -1.0E+030F;
/*  39: 39 */    if (v.x > maxVal) {
/*  40: 40 */      maxIndex = 0;
/*  41: 41 */      maxVal = v.x;
/*  42:    */    }
/*  43: 43 */    if (v.y > maxVal) {
/*  44: 44 */      maxIndex = 1;
/*  45: 45 */      maxVal = v.y;
/*  46:    */    }
/*  47: 47 */    if (v.z > maxVal) {
/*  48: 48 */      maxIndex = 2;
/*  49: 49 */      maxVal = v.z;
/*  50:    */    }
/*  51:    */    
/*  52: 52 */    return maxIndex;
/*  53:    */  }
/*  54:    */  
/*  55:    */  public static int maxAxis4(Vector4f v) {
/*  56: 56 */    int maxIndex = -1;
/*  57: 57 */    float maxVal = -1.0E+030F;
/*  58: 58 */    if (v.x > maxVal) {
/*  59: 59 */      maxIndex = 0;
/*  60: 60 */      maxVal = v.x;
/*  61:    */    }
/*  62: 62 */    if (v.y > maxVal) {
/*  63: 63 */      maxIndex = 1;
/*  64: 64 */      maxVal = v.y;
/*  65:    */    }
/*  66: 66 */    if (v.z > maxVal) {
/*  67: 67 */      maxIndex = 2;
/*  68: 68 */      maxVal = v.z;
/*  69:    */    }
/*  70: 70 */    if (v.w > maxVal) {
/*  71: 71 */      maxIndex = 3;
/*  72: 72 */      maxVal = v.w;
/*  73:    */    }
/*  74:    */    
/*  75: 75 */    return maxIndex;
/*  76:    */  }
/*  77:    */  
/*  78:    */  public static int closestAxis4(Vector4f vec) {
/*  79: 79 */    Vector4f tmp = new Vector4f(vec);
/*  80: 80 */    tmp.absolute();
/*  81: 81 */    return maxAxis4(tmp);
/*  82:    */  }
/*  83:    */  
/*  84:    */  public static float getCoord(Vector3f vec, int num) {
/*  85: 85 */    switch (num) {
/*  86: 86 */    case 0:  return vec.x;
/*  87: 87 */    case 1:  return vec.y;
/*  88: 88 */    case 2:  return vec.z; }
/*  89: 89 */    throw new InternalError();
/*  90:    */  }
/*  91:    */  
/*  92:    */  public static void setCoord(Vector3f vec, int num, float value)
/*  93:    */  {
/*  94: 94 */    switch (num) {
/*  95: 95 */    case 0:  vec.x = value;break;
/*  96: 96 */    case 1:  vec.y = value;break;
/*  97: 97 */    case 2:  vec.z = value;break;
/*  98: 98 */    default:  throw new InternalError();
/*  99:    */    }
/* 100:    */  }
/* 101:    */  
/* 102:    */  public static void mulCoord(Vector3f vec, int num, float value) {
/* 103:103 */    switch (num) {
/* 104:104 */    case 0:  vec.x *= value;break;
/* 105:105 */    case 1:  vec.y *= value;break;
/* 106:106 */    case 2:  vec.z *= value;break;
/* 107:107 */    default:  throw new InternalError();
/* 108:    */    }
/* 109:    */  }
/* 110:    */  
/* 111:    */  public static void setInterpolate3(Vector3f dest, Vector3f v0, Vector3f v1, float rt) {
/* 112:112 */    float s = 1.0F - rt;
/* 113:113 */    dest.x = (s * v0.x + rt * v1.x);
/* 114:114 */    dest.y = (s * v0.y + rt * v1.y);
/* 115:115 */    dest.z = (s * v0.z + rt * v1.z);
/* 116:    */  }
/* 117:    */  
/* 119:    */  public static void add(Vector3f dest, Vector3f v1, Vector3f v2)
/* 120:    */  {
/* 121:121 */    v1.x += v2.x;
/* 122:122 */    v1.y += v2.y;
/* 123:123 */    v1.z += v2.z;
/* 124:    */  }
/* 125:    */  
/* 126:    */  public static void add(Vector3f dest, Vector3f v1, Vector3f v2, Vector3f v3) {
/* 127:127 */    dest.x = (v1.x + v2.x + v3.x);
/* 128:128 */    dest.y = (v1.y + v2.y + v3.y);
/* 129:129 */    dest.z = (v1.z + v2.z + v3.z);
/* 130:    */  }
/* 131:    */  
/* 132:    */  public static void add(Vector3f dest, Vector3f v1, Vector3f v2, Vector3f v3, Vector3f v4) {
/* 133:133 */    dest.x = (v1.x + v2.x + v3.x + v4.x);
/* 134:134 */    dest.y = (v1.y + v2.y + v3.y + v4.y);
/* 135:135 */    dest.z = (v1.z + v2.z + v3.z + v4.z);
/* 136:    */  }
/* 137:    */  
/* 138:    */  public static void mul(Vector3f dest, Vector3f v1, Vector3f v2) {
/* 139:139 */    v1.x *= v2.x;
/* 140:140 */    v1.y *= v2.y;
/* 141:141 */    v1.z *= v2.z;
/* 142:    */  }
/* 143:    */  
/* 144:    */  public static void div(Vector3f dest, Vector3f v1, Vector3f v2) {
/* 145:145 */    v1.x /= v2.x;
/* 146:146 */    v1.y /= v2.y;
/* 147:147 */    v1.z /= v2.z;
/* 148:    */  }
/* 149:    */  
/* 150:    */  public static void setMin(Vector3f a, Vector3f b) {
/* 151:151 */    a.x = Math.min(a.x, b.x);
/* 152:152 */    a.y = Math.min(a.y, b.y);
/* 153:153 */    a.z = Math.min(a.z, b.z);
/* 154:    */  }
/* 155:    */  
/* 156:    */  public static void setMax(Vector3f a, Vector3f b) {
/* 157:157 */    a.x = Math.max(a.x, b.x);
/* 158:158 */    a.y = Math.max(a.y, b.y);
/* 159:159 */    a.z = Math.max(a.z, b.z);
/* 160:    */  }
/* 161:    */  
/* 162:    */  public static float dot3(Vector4f v0, Vector3f v1) {
/* 163:163 */    return v0.x * v1.x + v0.y * v1.y + v0.z * v1.z;
/* 164:    */  }
/* 165:    */  
/* 166:    */  public static float dot3(Vector4f v0, Vector4f v1) {
/* 167:167 */    return v0.x * v1.x + v0.y * v1.y + v0.z * v1.z;
/* 168:    */  }
/* 169:    */  
/* 170:    */  public static float dot3(Vector3f v0, Vector4f v1) {
/* 171:171 */    return v0.x * v1.x + v0.y * v1.y + v0.z * v1.z;
/* 172:    */  }
/* 173:    */  
/* 174:    */  public static float lengthSquared3(Vector4f v) {
/* 175:175 */    return v.x * v.x + v.y * v.y + v.z * v.z;
/* 176:    */  }
/* 177:    */  
/* 178:    */  public static void normalize3(Vector4f v) {
/* 179:179 */    float norm = (float)(1.0D / Math.sqrt(v.x * v.x + v.y * v.y + v.z * v.z));
/* 180:180 */    v.x *= norm;
/* 181:181 */    v.y *= norm;
/* 182:182 */    v.z *= norm;
/* 183:    */  }
/* 184:    */  
/* 185:    */  public static void cross3(Vector3f dest, Vector4f v1, Vector4f v2)
/* 186:    */  {
/* 187:187 */    float x = v1.y * v2.z - v1.z * v2.y;
/* 188:188 */    float y = v2.x * v1.z - v2.z * v1.x;
/* 189:189 */    dest.z = (v1.x * v2.y - v1.y * v2.x);
/* 190:190 */    dest.x = x;
/* 191:191 */    dest.y = y;
/* 192:    */  }
/* 193:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.linearmath.VectorUtil
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */