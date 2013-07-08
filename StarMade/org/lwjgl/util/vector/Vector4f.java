/*   1:    */package org.lwjgl.util.vector;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */import java.nio.FloatBuffer;
/*   5:    */
/*  49:    */public class Vector4f
/*  50:    */  extends Vector
/*  51:    */  implements Serializable, ReadableVector4f, WritableVector4f
/*  52:    */{
/*  53:    */  private static final long serialVersionUID = 1L;
/*  54:    */  public float x;
/*  55:    */  public float y;
/*  56:    */  public float z;
/*  57:    */  public float w;
/*  58:    */  
/*  59:    */  public Vector4f() {}
/*  60:    */  
/*  61:    */  public Vector4f(ReadableVector4f src)
/*  62:    */  {
/*  63: 63 */    set(src);
/*  64:    */  }
/*  65:    */  
/*  68:    */  public Vector4f(float x, float y, float z, float w)
/*  69:    */  {
/*  70: 70 */    set(x, y, z, w);
/*  71:    */  }
/*  72:    */  
/*  75:    */  public void set(float x, float y)
/*  76:    */  {
/*  77: 77 */    this.x = x;
/*  78: 78 */    this.y = y;
/*  79:    */  }
/*  80:    */  
/*  83:    */  public void set(float x, float y, float z)
/*  84:    */  {
/*  85: 85 */    this.x = x;
/*  86: 86 */    this.y = y;
/*  87: 87 */    this.z = z;
/*  88:    */  }
/*  89:    */  
/*  92:    */  public void set(float x, float y, float z, float w)
/*  93:    */  {
/*  94: 94 */    this.x = x;
/*  95: 95 */    this.y = y;
/*  96: 96 */    this.z = z;
/*  97: 97 */    this.w = w;
/*  98:    */  }
/*  99:    */  
/* 104:    */  public Vector4f set(ReadableVector4f src)
/* 105:    */  {
/* 106:106 */    this.x = src.getX();
/* 107:107 */    this.y = src.getY();
/* 108:108 */    this.z = src.getZ();
/* 109:109 */    this.w = src.getW();
/* 110:110 */    return this;
/* 111:    */  }
/* 112:    */  
/* 115:    */  public float lengthSquared()
/* 116:    */  {
/* 117:117 */    return this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w;
/* 118:    */  }
/* 119:    */  
/* 125:    */  public Vector4f translate(float x, float y, float z, float w)
/* 126:    */  {
/* 127:127 */    this.x += x;
/* 128:128 */    this.y += y;
/* 129:129 */    this.z += z;
/* 130:130 */    this.w += w;
/* 131:131 */    return this;
/* 132:    */  }
/* 133:    */  
/* 141:    */  public static Vector4f add(Vector4f left, Vector4f right, Vector4f dest)
/* 142:    */  {
/* 143:143 */    if (dest == null) {
/* 144:144 */      return new Vector4f(left.x + right.x, left.y + right.y, left.z + right.z, left.w + right.w);
/* 145:    */    }
/* 146:146 */    dest.set(left.x + right.x, left.y + right.y, left.z + right.z, left.w + right.w);
/* 147:147 */    return dest;
/* 148:    */  }
/* 149:    */  
/* 158:    */  public static Vector4f sub(Vector4f left, Vector4f right, Vector4f dest)
/* 159:    */  {
/* 160:160 */    if (dest == null) {
/* 161:161 */      return new Vector4f(left.x - right.x, left.y - right.y, left.z - right.z, left.w - right.w);
/* 162:    */    }
/* 163:163 */    dest.set(left.x - right.x, left.y - right.y, left.z - right.z, left.w - right.w);
/* 164:164 */    return dest;
/* 165:    */  }
/* 166:    */  
/* 172:    */  public Vector negate()
/* 173:    */  {
/* 174:174 */    this.x = (-this.x);
/* 175:175 */    this.y = (-this.y);
/* 176:176 */    this.z = (-this.z);
/* 177:177 */    this.w = (-this.w);
/* 178:178 */    return this;
/* 179:    */  }
/* 180:    */  
/* 185:    */  public Vector4f negate(Vector4f dest)
/* 186:    */  {
/* 187:187 */    if (dest == null)
/* 188:188 */      dest = new Vector4f();
/* 189:189 */    dest.x = (-this.x);
/* 190:190 */    dest.y = (-this.y);
/* 191:191 */    dest.z = (-this.z);
/* 192:192 */    dest.w = (-this.w);
/* 193:193 */    return dest;
/* 194:    */  }
/* 195:    */  
/* 201:    */  public Vector4f normalise(Vector4f dest)
/* 202:    */  {
/* 203:203 */    float l = length();
/* 204:    */    
/* 205:205 */    if (dest == null) {
/* 206:206 */      dest = new Vector4f(this.x / l, this.y / l, this.z / l, this.w / l);
/* 207:    */    } else {
/* 208:208 */      dest.set(this.x / l, this.y / l, this.z / l, this.w / l);
/* 209:    */    }
/* 210:210 */    return dest;
/* 211:    */  }
/* 212:    */  
/* 219:    */  public static float dot(Vector4f left, Vector4f right)
/* 220:    */  {
/* 221:221 */    return left.x * right.x + left.y * right.y + left.z * right.z + left.w * right.w;
/* 222:    */  }
/* 223:    */  
/* 229:    */  public static float angle(Vector4f a, Vector4f b)
/* 230:    */  {
/* 231:231 */    float dls = dot(a, b) / (a.length() * b.length());
/* 232:232 */    if (dls < -1.0F) {
/* 233:233 */      dls = -1.0F;
/* 234:234 */    } else if (dls > 1.0F)
/* 235:235 */      dls = 1.0F;
/* 236:236 */    return (float)Math.acos(dls);
/* 237:    */  }
/* 238:    */  
/* 241:    */  public Vector load(FloatBuffer buf)
/* 242:    */  {
/* 243:243 */    this.x = buf.get();
/* 244:244 */    this.y = buf.get();
/* 245:245 */    this.z = buf.get();
/* 246:246 */    this.w = buf.get();
/* 247:247 */    return this;
/* 248:    */  }
/* 249:    */  
/* 252:    */  public Vector scale(float scale)
/* 253:    */  {
/* 254:254 */    this.x *= scale;
/* 255:255 */    this.y *= scale;
/* 256:256 */    this.z *= scale;
/* 257:257 */    this.w *= scale;
/* 258:258 */    return this;
/* 259:    */  }
/* 260:    */  
/* 264:    */  public Vector store(FloatBuffer buf)
/* 265:    */  {
/* 266:266 */    buf.put(this.x);
/* 267:267 */    buf.put(this.y);
/* 268:268 */    buf.put(this.z);
/* 269:269 */    buf.put(this.w);
/* 270:    */    
/* 271:271 */    return this;
/* 272:    */  }
/* 273:    */  
/* 274:    */  public String toString() {
/* 275:275 */    return "Vector4f: " + this.x + " " + this.y + " " + this.z + " " + this.w;
/* 276:    */  }
/* 277:    */  
/* 280:    */  public final float getX()
/* 281:    */  {
/* 282:282 */    return this.x;
/* 283:    */  }
/* 284:    */  
/* 287:    */  public final float getY()
/* 288:    */  {
/* 289:289 */    return this.y;
/* 290:    */  }
/* 291:    */  
/* 295:    */  public final void setX(float x)
/* 296:    */  {
/* 297:297 */    this.x = x;
/* 298:    */  }
/* 299:    */  
/* 303:    */  public final void setY(float y)
/* 304:    */  {
/* 305:305 */    this.y = y;
/* 306:    */  }
/* 307:    */  
/* 311:    */  public void setZ(float z)
/* 312:    */  {
/* 313:313 */    this.z = z;
/* 314:    */  }
/* 315:    */  
/* 319:    */  public float getZ()
/* 320:    */  {
/* 321:321 */    return this.z;
/* 322:    */  }
/* 323:    */  
/* 327:    */  public void setW(float w)
/* 328:    */  {
/* 329:329 */    this.w = w;
/* 330:    */  }
/* 331:    */  
/* 334:    */  public float getW()
/* 335:    */  {
/* 336:336 */    return this.w;
/* 337:    */  }
/* 338:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.vector.Vector4f
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */