/*   1:    */package org.lwjgl.util.vector;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */import java.nio.FloatBuffer;
/*   5:    */
/*  50:    */public class Vector3f
/*  51:    */  extends Vector
/*  52:    */  implements Serializable, ReadableVector3f, WritableVector3f
/*  53:    */{
/*  54:    */  private static final long serialVersionUID = 1L;
/*  55:    */  public float x;
/*  56:    */  public float y;
/*  57:    */  public float z;
/*  58:    */  
/*  59:    */  public Vector3f() {}
/*  60:    */  
/*  61:    */  public Vector3f(ReadableVector3f src)
/*  62:    */  {
/*  63: 63 */    set(src);
/*  64:    */  }
/*  65:    */  
/*  68:    */  public Vector3f(float x, float y, float z)
/*  69:    */  {
/*  70: 70 */    set(x, y, z);
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
/*  94:    */  public Vector3f set(ReadableVector3f src)
/*  95:    */  {
/*  96: 96 */    this.x = src.getX();
/*  97: 97 */    this.y = src.getY();
/*  98: 98 */    this.z = src.getZ();
/*  99: 99 */    return this;
/* 100:    */  }
/* 101:    */  
/* 104:    */  public float lengthSquared()
/* 105:    */  {
/* 106:106 */    return this.x * this.x + this.y * this.y + this.z * this.z;
/* 107:    */  }
/* 108:    */  
/* 114:    */  public Vector3f translate(float x, float y, float z)
/* 115:    */  {
/* 116:116 */    this.x += x;
/* 117:117 */    this.y += y;
/* 118:118 */    this.z += z;
/* 119:119 */    return this;
/* 120:    */  }
/* 121:    */  
/* 129:    */  public static Vector3f add(Vector3f left, Vector3f right, Vector3f dest)
/* 130:    */  {
/* 131:131 */    if (dest == null) {
/* 132:132 */      return new Vector3f(left.x + right.x, left.y + right.y, left.z + right.z);
/* 133:    */    }
/* 134:134 */    dest.set(left.x + right.x, left.y + right.y, left.z + right.z);
/* 135:135 */    return dest;
/* 136:    */  }
/* 137:    */  
/* 146:    */  public static Vector3f sub(Vector3f left, Vector3f right, Vector3f dest)
/* 147:    */  {
/* 148:148 */    if (dest == null) {
/* 149:149 */      return new Vector3f(left.x - right.x, left.y - right.y, left.z - right.z);
/* 150:    */    }
/* 151:151 */    dest.set(left.x - right.x, left.y - right.y, left.z - right.z);
/* 152:152 */    return dest;
/* 153:    */  }
/* 154:    */  
/* 168:    */  public static Vector3f cross(Vector3f left, Vector3f right, Vector3f dest)
/* 169:    */  {
/* 170:170 */    if (dest == null) {
/* 171:171 */      dest = new Vector3f();
/* 172:    */    }
/* 173:173 */    dest.set(left.y * right.z - left.z * right.y, right.x * left.z - right.z * left.x, left.x * right.y - left.y * right.x);
/* 174:    */    
/* 179:179 */    return dest;
/* 180:    */  }
/* 181:    */  
/* 187:    */  public Vector negate()
/* 188:    */  {
/* 189:189 */    this.x = (-this.x);
/* 190:190 */    this.y = (-this.y);
/* 191:191 */    this.z = (-this.z);
/* 192:192 */    return this;
/* 193:    */  }
/* 194:    */  
/* 199:    */  public Vector3f negate(Vector3f dest)
/* 200:    */  {
/* 201:201 */    if (dest == null)
/* 202:202 */      dest = new Vector3f();
/* 203:203 */    dest.x = (-this.x);
/* 204:204 */    dest.y = (-this.y);
/* 205:205 */    dest.z = (-this.z);
/* 206:206 */    return dest;
/* 207:    */  }
/* 208:    */  
/* 214:    */  public Vector3f normalise(Vector3f dest)
/* 215:    */  {
/* 216:216 */    float l = length();
/* 217:    */    
/* 218:218 */    if (dest == null) {
/* 219:219 */      dest = new Vector3f(this.x / l, this.y / l, this.z / l);
/* 220:    */    } else {
/* 221:221 */      dest.set(this.x / l, this.y / l, this.z / l);
/* 222:    */    }
/* 223:223 */    return dest;
/* 224:    */  }
/* 225:    */  
/* 232:    */  public static float dot(Vector3f left, Vector3f right)
/* 233:    */  {
/* 234:234 */    return left.x * right.x + left.y * right.y + left.z * right.z;
/* 235:    */  }
/* 236:    */  
/* 242:    */  public static float angle(Vector3f a, Vector3f b)
/* 243:    */  {
/* 244:244 */    float dls = dot(a, b) / (a.length() * b.length());
/* 245:245 */    if (dls < -1.0F) {
/* 246:246 */      dls = -1.0F;
/* 247:247 */    } else if (dls > 1.0F)
/* 248:248 */      dls = 1.0F;
/* 249:249 */    return (float)Math.acos(dls);
/* 250:    */  }
/* 251:    */  
/* 254:    */  public Vector load(FloatBuffer buf)
/* 255:    */  {
/* 256:256 */    this.x = buf.get();
/* 257:257 */    this.y = buf.get();
/* 258:258 */    this.z = buf.get();
/* 259:259 */    return this;
/* 260:    */  }
/* 261:    */  
/* 265:    */  public Vector scale(float scale)
/* 266:    */  {
/* 267:267 */    this.x *= scale;
/* 268:268 */    this.y *= scale;
/* 269:269 */    this.z *= scale;
/* 270:    */    
/* 271:271 */    return this;
/* 272:    */  }
/* 273:    */  
/* 278:    */  public Vector store(FloatBuffer buf)
/* 279:    */  {
/* 280:280 */    buf.put(this.x);
/* 281:281 */    buf.put(this.y);
/* 282:282 */    buf.put(this.z);
/* 283:    */    
/* 284:284 */    return this;
/* 285:    */  }
/* 286:    */  
/* 289:    */  public String toString()
/* 290:    */  {
/* 291:291 */    StringBuilder sb = new StringBuilder(64);
/* 292:    */    
/* 293:293 */    sb.append("Vector3f[");
/* 294:294 */    sb.append(this.x);
/* 295:295 */    sb.append(", ");
/* 296:296 */    sb.append(this.y);
/* 297:297 */    sb.append(", ");
/* 298:298 */    sb.append(this.z);
/* 299:299 */    sb.append(']');
/* 300:300 */    return sb.toString();
/* 301:    */  }
/* 302:    */  
/* 305:    */  public final float getX()
/* 306:    */  {
/* 307:307 */    return this.x;
/* 308:    */  }
/* 309:    */  
/* 312:    */  public final float getY()
/* 313:    */  {
/* 314:314 */    return this.y;
/* 315:    */  }
/* 316:    */  
/* 320:    */  public final void setX(float x)
/* 321:    */  {
/* 322:322 */    this.x = x;
/* 323:    */  }
/* 324:    */  
/* 328:    */  public final void setY(float y)
/* 329:    */  {
/* 330:330 */    this.y = y;
/* 331:    */  }
/* 332:    */  
/* 336:    */  public void setZ(float z)
/* 337:    */  {
/* 338:338 */    this.z = z;
/* 339:    */  }
/* 340:    */  
/* 343:    */  public float getZ()
/* 344:    */  {
/* 345:345 */    return this.z;
/* 346:    */  }
/* 347:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.vector.Vector3f
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */