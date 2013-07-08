/*   1:    */package com.bulletphysics.linearmath;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.util.ArrayPool;
/*   5:    */import javax.vecmath.Matrix3f;
/*   6:    */import javax.vecmath.Quat4f;
/*   7:    */import javax.vecmath.Vector3f;
/*   8:    */
/*  37:    */public class MatrixUtil
/*  38:    */{
/*  39:    */  public static void scale(Matrix3f dest, Matrix3f mat, Vector3f s)
/*  40:    */  {
/*  41: 41 */    mat.m00 *= s.x;mat.m01 *= s.y;mat.m02 *= s.z;
/*  42: 42 */    mat.m10 *= s.x;mat.m11 *= s.y;mat.m12 *= s.z;
/*  43: 43 */    mat.m20 *= s.x;mat.m21 *= s.y;mat.m22 *= s.z;
/*  44:    */  }
/*  45:    */  
/*  46:    */  public static void absolute(Matrix3f mat) {
/*  47: 47 */    mat.m00 = Math.abs(mat.m00);
/*  48: 48 */    mat.m01 = Math.abs(mat.m01);
/*  49: 49 */    mat.m02 = Math.abs(mat.m02);
/*  50: 50 */    mat.m10 = Math.abs(mat.m10);
/*  51: 51 */    mat.m11 = Math.abs(mat.m11);
/*  52: 52 */    mat.m12 = Math.abs(mat.m12);
/*  53: 53 */    mat.m20 = Math.abs(mat.m20);
/*  54: 54 */    mat.m21 = Math.abs(mat.m21);
/*  55: 55 */    mat.m22 = Math.abs(mat.m22);
/*  56:    */  }
/*  57:    */  
/*  58:    */  public static void setFromOpenGLSubMatrix(Matrix3f mat, float[] m) {
/*  59: 59 */    mat.m00 = m[0];mat.m01 = m[4];mat.m02 = m[8];
/*  60: 60 */    mat.m10 = m[1];mat.m11 = m[5];mat.m12 = m[9];
/*  61: 61 */    mat.m20 = m[2];mat.m21 = m[6];mat.m22 = m[10];
/*  62:    */  }
/*  63:    */  
/*  64:    */  public static void getOpenGLSubMatrix(Matrix3f mat, float[] m) {
/*  65: 65 */    m[0] = mat.m00;
/*  66: 66 */    m[1] = mat.m10;
/*  67: 67 */    m[2] = mat.m20;
/*  68: 68 */    m[3] = 0.0F;
/*  69: 69 */    m[4] = mat.m01;
/*  70: 70 */    m[5] = mat.m11;
/*  71: 71 */    m[6] = mat.m21;
/*  72: 72 */    m[7] = 0.0F;
/*  73: 73 */    m[8] = mat.m02;
/*  74: 74 */    m[9] = mat.m12;
/*  75: 75 */    m[10] = mat.m22;
/*  76: 76 */    m[11] = 0.0F;
/*  77:    */  }
/*  78:    */  
/*  82:    */  public static void setEulerZYX(Matrix3f mat, float eulerX, float eulerY, float eulerZ)
/*  83:    */  {
/*  84: 84 */    float ci = (float)Math.cos(eulerX);
/*  85: 85 */    float cj = (float)Math.cos(eulerY);
/*  86: 86 */    float ch = (float)Math.cos(eulerZ);
/*  87: 87 */    float si = (float)Math.sin(eulerX);
/*  88: 88 */    float sj = (float)Math.sin(eulerY);
/*  89: 89 */    float sh = (float)Math.sin(eulerZ);
/*  90: 90 */    float cc = ci * ch;
/*  91: 91 */    float cs = ci * sh;
/*  92: 92 */    float sc = si * ch;
/*  93: 93 */    float ss = si * sh;
/*  94:    */    
/*  95: 95 */    mat.setRow(0, cj * ch, sj * sc - cs, sj * cc + ss);
/*  96: 96 */    mat.setRow(1, cj * sh, sj * ss + cc, sj * cs - sc);
/*  97: 97 */    mat.setRow(2, -sj, cj * si, cj * ci);
/*  98:    */  }
/*  99:    */  
/* 100:    */  private static float tdotx(Matrix3f mat, Vector3f vec) {
/* 101:101 */    return mat.m00 * vec.x + mat.m10 * vec.y + mat.m20 * vec.z;
/* 102:    */  }
/* 103:    */  
/* 104:    */  private static float tdoty(Matrix3f mat, Vector3f vec) {
/* 105:105 */    return mat.m01 * vec.x + mat.m11 * vec.y + mat.m21 * vec.z;
/* 106:    */  }
/* 107:    */  
/* 108:    */  private static float tdotz(Matrix3f mat, Vector3f vec) {
/* 109:109 */    return mat.m02 * vec.x + mat.m12 * vec.y + mat.m22 * vec.z;
/* 110:    */  }
/* 111:    */  
/* 112:    */  public static void transposeTransform(Vector3f dest, Vector3f vec, Matrix3f mat) {
/* 113:113 */    float x = tdotx(mat, vec);
/* 114:114 */    float y = tdoty(mat, vec);
/* 115:115 */    float z = tdotz(mat, vec);
/* 116:116 */    dest.x = x;
/* 117:117 */    dest.y = y;
/* 118:118 */    dest.z = z;
/* 119:    */  }
/* 120:    */  
/* 121:    */  public static void setRotation(Matrix3f dest, Quat4f q) {
/* 122:122 */    float d = q.x * q.x + q.y * q.y + q.z * q.z + q.w * q.w;
/* 123:123 */    assert (d != 0.0F);
/* 124:124 */    float s = 2.0F / d;
/* 125:125 */    float xs = q.x * s;float ys = q.y * s;float zs = q.z * s;
/* 126:126 */    float wx = q.w * xs;float wy = q.w * ys;float wz = q.w * zs;
/* 127:127 */    float xx = q.x * xs;float xy = q.x * ys;float xz = q.x * zs;
/* 128:128 */    float yy = q.y * ys;float yz = q.y * zs;float zz = q.z * zs;
/* 129:129 */    dest.m00 = (1.0F - (yy + zz));
/* 130:130 */    dest.m01 = (xy - wz);
/* 131:131 */    dest.m02 = (xz + wy);
/* 132:132 */    dest.m10 = (xy + wz);
/* 133:133 */    dest.m11 = (1.0F - (xx + zz));
/* 134:134 */    dest.m12 = (yz - wx);
/* 135:135 */    dest.m20 = (xz - wy);
/* 136:136 */    dest.m21 = (yz + wx);
/* 137:137 */    dest.m22 = (1.0F - (xx + yy));
/* 138:    */  }
/* 139:    */  
/* 140:    */  public static void getRotation(Matrix3f mat, Quat4f dest) {
/* 141:141 */    ArrayPool<float[]> floatArrays = ArrayPool.get(Float.TYPE);
/* 142:    */    
/* 143:143 */    float trace = mat.m00 + mat.m11 + mat.m22;
/* 144:144 */    float[] temp = (float[])floatArrays.getFixed(4);
/* 145:    */    
/* 146:146 */    if (trace > 0.0F) {
/* 147:147 */      float s = (float)Math.sqrt(trace + 1.0F);
/* 148:148 */      temp[3] = (s * 0.5F);
/* 149:149 */      s = 0.5F / s;
/* 150:    */      
/* 151:151 */      temp[0] = ((mat.m21 - mat.m12) * s);
/* 152:152 */      temp[1] = ((mat.m02 - mat.m20) * s);
/* 153:153 */      temp[2] = ((mat.m10 - mat.m01) * s);
/* 154:    */    }
/* 155:    */    else {
/* 156:156 */      int i = mat.m00 < mat.m22 ? 2 : mat.m00 < mat.m11 ? 1 : mat.m11 < mat.m22 ? 2 : 0;
/* 157:157 */      int j = (i + 1) % 3;
/* 158:158 */      int k = (i + 2) % 3;
/* 159:    */      
/* 160:160 */      float s = (float)Math.sqrt(mat.getElement(i, i) - mat.getElement(j, j) - mat.getElement(k, k) + 1.0F);
/* 161:161 */      temp[i] = (s * 0.5F);
/* 162:162 */      s = 0.5F / s;
/* 163:    */      
/* 164:164 */      temp[3] = ((mat.getElement(k, j) - mat.getElement(j, k)) * s);
/* 165:165 */      temp[j] = ((mat.getElement(j, i) + mat.getElement(i, j)) * s);
/* 166:166 */      temp[k] = ((mat.getElement(k, i) + mat.getElement(i, k)) * s);
/* 167:    */    }
/* 168:168 */    dest.set(temp[0], temp[1], temp[2], temp[3]);
/* 169:    */    
/* 170:170 */    floatArrays.release(temp);
/* 171:    */  }
/* 172:    */  
/* 173:    */  private static float cofac(Matrix3f mat, int r1, int c1, int r2, int c2) {
/* 174:174 */    return mat.getElement(r1, c1) * mat.getElement(r2, c2) - mat.getElement(r1, c2) * mat.getElement(r2, c1);
/* 175:    */  }
/* 176:    */  
/* 177:    */  public static void invert(Matrix3f mat) {
/* 178:178 */    float co_x = cofac(mat, 1, 1, 2, 2);
/* 179:179 */    float co_y = cofac(mat, 1, 2, 2, 0);
/* 180:180 */    float co_z = cofac(mat, 1, 0, 2, 1);
/* 181:    */    
/* 182:182 */    float det = mat.m00 * co_x + mat.m01 * co_y + mat.m02 * co_z;
/* 183:183 */    assert (det != 0.0F);
/* 184:    */    
/* 185:185 */    float s = 1.0F / det;
/* 186:186 */    float m00 = co_x * s;
/* 187:187 */    float m01 = cofac(mat, 0, 2, 2, 1) * s;
/* 188:188 */    float m02 = cofac(mat, 0, 1, 1, 2) * s;
/* 189:189 */    float m10 = co_y * s;
/* 190:190 */    float m11 = cofac(mat, 0, 0, 2, 2) * s;
/* 191:191 */    float m12 = cofac(mat, 0, 2, 1, 0) * s;
/* 192:192 */    float m20 = co_z * s;
/* 193:193 */    float m21 = cofac(mat, 0, 1, 2, 0) * s;
/* 194:194 */    float m22 = cofac(mat, 0, 0, 1, 1) * s;
/* 195:    */    
/* 196:196 */    mat.m00 = m00;
/* 197:197 */    mat.m01 = m01;
/* 198:198 */    mat.m02 = m02;
/* 199:199 */    mat.m10 = m10;
/* 200:200 */    mat.m11 = m11;
/* 201:201 */    mat.m12 = m12;
/* 202:202 */    mat.m20 = m20;
/* 203:203 */    mat.m21 = m21;
/* 204:204 */    mat.m22 = m22;
/* 205:    */  }
/* 206:    */  
/* 215:    */  public static void diagonalize(Matrix3f arg0, Matrix3f arg1, float arg2, int arg3)
/* 216:    */  {
/* 217:217 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f row = localStack.get$javax$vecmath$Vector3f();
/* 218:    */      
/* 219:219 */      rot.setIdentity();
/* 220:220 */      for (int step = maxSteps; step > 0; step--)
/* 221:    */      {
/* 222:222 */        int p = 0;
/* 223:223 */        int q = 1;
/* 224:224 */        int r = 2;
/* 225:225 */        float max = Math.abs(mat.m01);
/* 226:226 */        float v = Math.abs(mat.m02);
/* 227:227 */        if (v > max) {
/* 228:228 */          q = 2;
/* 229:229 */          r = 1;
/* 230:230 */          max = v;
/* 231:    */        }
/* 232:232 */        v = Math.abs(mat.m12);
/* 233:233 */        if (v > max) {
/* 234:234 */          p = 1;
/* 235:235 */          q = 2;
/* 236:236 */          r = 0;
/* 237:237 */          max = v;
/* 238:    */        }
/* 239:    */        
/* 240:240 */        float t = threshold * (Math.abs(mat.m00) + Math.abs(mat.m11) + Math.abs(mat.m22));
/* 241:241 */        if (max <= t) {
/* 242:242 */          if (max <= 1.192093E-007F * t) {
/* 243:243 */            return;
/* 244:    */          }
/* 245:245 */          step = 1;
/* 246:    */        }
/* 247:    */        
/* 249:249 */        float mpq = mat.getElement(p, q);
/* 250:250 */        float theta = (mat.getElement(q, q) - mat.getElement(p, p)) / (2.0F * mpq);
/* 251:251 */        float theta2 = theta * theta;
/* 252:    */        float sin;
/* 253:    */        float cos;
/* 254:254 */        float sin; if (theta2 * theta2 < 83886080.0F) {
/* 255:255 */          t = theta >= 0.0F ? 1.0F / (theta + (float)Math.sqrt(1.0F + theta2)) : 1.0F / (theta - (float)Math.sqrt(1.0F + theta2));
/* 256:    */          
/* 257:257 */          float cos = 1.0F / (float)Math.sqrt(1.0F + t * t);
/* 258:258 */          sin = cos * t;
/* 259:    */        }
/* 260:    */        else
/* 261:    */        {
/* 262:262 */          t = 1.0F / (theta * (2.0F + 0.5F / theta2));
/* 263:263 */          cos = 1.0F - 0.5F * t * t;
/* 264:264 */          sin = cos * t;
/* 265:    */        }
/* 266:    */        
/* 268:268 */        mat.setElement(p, q, 0.0F);
/* 269:269 */        mat.setElement(q, p, 0.0F);
/* 270:270 */        mat.setElement(p, p, mat.getElement(p, p) - t * mpq);
/* 271:271 */        mat.setElement(q, q, mat.getElement(q, q) + t * mpq);
/* 272:272 */        float mrp = mat.getElement(r, p);
/* 273:273 */        float mrq = mat.getElement(r, q);
/* 274:274 */        mat.setElement(r, p, cos * mrp - sin * mrq);
/* 275:275 */        mat.setElement(p, r, cos * mrp - sin * mrq);
/* 276:276 */        mat.setElement(r, q, cos * mrq + sin * mrp);
/* 277:277 */        mat.setElement(q, r, cos * mrq + sin * mrp);
/* 278:    */        
/* 280:280 */        for (int i = 0; i < 3; i++) {
/* 281:281 */          rot.getRow(i, row);
/* 282:    */          
/* 283:283 */          mrp = VectorUtil.getCoord(row, p);
/* 284:284 */          mrq = VectorUtil.getCoord(row, q);
/* 285:285 */          VectorUtil.setCoord(row, p, cos * mrp - sin * mrq);
/* 286:286 */          VectorUtil.setCoord(row, q, cos * mrq + sin * mrp);
/* 287:287 */          rot.setRow(i, row);
/* 288:    */        }
/* 289:    */      }
/* 290:290 */    } finally { localStack.pop$javax$vecmath$Vector3f();
/* 291:    */    }
/* 292:    */  }
/* 293:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.linearmath.MatrixUtil
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */