/*   1:    */package org.lwjgl.util.vector;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */import java.nio.FloatBuffer;
/*   5:    */
/*  44:    */public class Matrix2f
/*  45:    */  extends Matrix
/*  46:    */  implements Serializable
/*  47:    */{
/*  48:    */  private static final long serialVersionUID = 1L;
/*  49:    */  public float m00;
/*  50:    */  public float m01;
/*  51:    */  public float m10;
/*  52:    */  public float m11;
/*  53:    */  
/*  54:    */  public Matrix2f()
/*  55:    */  {
/*  56: 56 */    setIdentity();
/*  57:    */  }
/*  58:    */  
/*  61:    */  public Matrix2f(Matrix2f src)
/*  62:    */  {
/*  63: 63 */    load(src);
/*  64:    */  }
/*  65:    */  
/*  70:    */  public Matrix2f load(Matrix2f src)
/*  71:    */  {
/*  72: 72 */    return load(src, this);
/*  73:    */  }
/*  74:    */  
/*  80:    */  public static Matrix2f load(Matrix2f src, Matrix2f dest)
/*  81:    */  {
/*  82: 82 */    if (dest == null) {
/*  83: 83 */      dest = new Matrix2f();
/*  84:    */    }
/*  85: 85 */    dest.m00 = src.m00;
/*  86: 86 */    dest.m01 = src.m01;
/*  87: 87 */    dest.m10 = src.m10;
/*  88: 88 */    dest.m11 = src.m11;
/*  89:    */    
/*  90: 90 */    return dest;
/*  91:    */  }
/*  92:    */  
/* 100:    */  public Matrix load(FloatBuffer buf)
/* 101:    */  {
/* 102:102 */    this.m00 = buf.get();
/* 103:103 */    this.m01 = buf.get();
/* 104:104 */    this.m10 = buf.get();
/* 105:105 */    this.m11 = buf.get();
/* 106:    */    
/* 107:107 */    return this;
/* 108:    */  }
/* 109:    */  
/* 117:    */  public Matrix loadTranspose(FloatBuffer buf)
/* 118:    */  {
/* 119:119 */    this.m00 = buf.get();
/* 120:120 */    this.m10 = buf.get();
/* 121:121 */    this.m01 = buf.get();
/* 122:122 */    this.m11 = buf.get();
/* 123:    */    
/* 124:124 */    return this;
/* 125:    */  }
/* 126:    */  
/* 131:    */  public Matrix store(FloatBuffer buf)
/* 132:    */  {
/* 133:133 */    buf.put(this.m00);
/* 134:134 */    buf.put(this.m01);
/* 135:135 */    buf.put(this.m10);
/* 136:136 */    buf.put(this.m11);
/* 137:137 */    return this;
/* 138:    */  }
/* 139:    */  
/* 144:    */  public Matrix storeTranspose(FloatBuffer buf)
/* 145:    */  {
/* 146:146 */    buf.put(this.m00);
/* 147:147 */    buf.put(this.m10);
/* 148:148 */    buf.put(this.m01);
/* 149:149 */    buf.put(this.m11);
/* 150:150 */    return this;
/* 151:    */  }
/* 152:    */  
/* 161:    */  public static Matrix2f add(Matrix2f left, Matrix2f right, Matrix2f dest)
/* 162:    */  {
/* 163:163 */    if (dest == null) {
/* 164:164 */      dest = new Matrix2f();
/* 165:    */    }
/* 166:166 */    left.m00 += right.m00;
/* 167:167 */    left.m01 += right.m01;
/* 168:168 */    left.m10 += right.m10;
/* 169:169 */    left.m11 += right.m11;
/* 170:    */    
/* 171:171 */    return dest;
/* 172:    */  }
/* 173:    */  
/* 180:    */  public static Matrix2f sub(Matrix2f left, Matrix2f right, Matrix2f dest)
/* 181:    */  {
/* 182:182 */    if (dest == null) {
/* 183:183 */      dest = new Matrix2f();
/* 184:    */    }
/* 185:185 */    left.m00 -= right.m00;
/* 186:186 */    left.m01 -= right.m01;
/* 187:187 */    left.m10 -= right.m10;
/* 188:188 */    left.m11 -= right.m11;
/* 189:    */    
/* 190:190 */    return dest;
/* 191:    */  }
/* 192:    */  
/* 199:    */  public static Matrix2f mul(Matrix2f left, Matrix2f right, Matrix2f dest)
/* 200:    */  {
/* 201:201 */    if (dest == null) {
/* 202:202 */      dest = new Matrix2f();
/* 203:    */    }
/* 204:204 */    float m00 = left.m00 * right.m00 + left.m10 * right.m01;
/* 205:205 */    float m01 = left.m01 * right.m00 + left.m11 * right.m01;
/* 206:206 */    float m10 = left.m00 * right.m10 + left.m10 * right.m11;
/* 207:207 */    float m11 = left.m01 * right.m10 + left.m11 * right.m11;
/* 208:    */    
/* 209:209 */    dest.m00 = m00;
/* 210:210 */    dest.m01 = m01;
/* 211:211 */    dest.m10 = m10;
/* 212:212 */    dest.m11 = m11;
/* 213:    */    
/* 214:214 */    return dest;
/* 215:    */  }
/* 216:    */  
/* 224:    */  public static Vector2f transform(Matrix2f left, Vector2f right, Vector2f dest)
/* 225:    */  {
/* 226:226 */    if (dest == null) {
/* 227:227 */      dest = new Vector2f();
/* 228:    */    }
/* 229:229 */    float x = left.m00 * right.x + left.m10 * right.y;
/* 230:230 */    float y = left.m01 * right.x + left.m11 * right.y;
/* 231:    */    
/* 232:232 */    dest.x = x;
/* 233:233 */    dest.y = y;
/* 234:    */    
/* 235:235 */    return dest;
/* 236:    */  }
/* 237:    */  
/* 241:    */  public Matrix transpose()
/* 242:    */  {
/* 243:243 */    return transpose(this);
/* 244:    */  }
/* 245:    */  
/* 250:    */  public Matrix2f transpose(Matrix2f dest)
/* 251:    */  {
/* 252:252 */    return transpose(this, dest);
/* 253:    */  }
/* 254:    */  
/* 260:    */  public static Matrix2f transpose(Matrix2f src, Matrix2f dest)
/* 261:    */  {
/* 262:262 */    if (dest == null) {
/* 263:263 */      dest = new Matrix2f();
/* 264:    */    }
/* 265:265 */    float m01 = src.m10;
/* 266:266 */    float m10 = src.m01;
/* 267:    */    
/* 268:268 */    dest.m01 = m01;
/* 269:269 */    dest.m10 = m10;
/* 270:    */    
/* 271:271 */    return dest;
/* 272:    */  }
/* 273:    */  
/* 277:    */  public Matrix invert()
/* 278:    */  {
/* 279:279 */    return invert(this, this);
/* 280:    */  }
/* 281:    */  
/* 291:    */  public static Matrix2f invert(Matrix2f src, Matrix2f dest)
/* 292:    */  {
/* 293:293 */    float determinant = src.determinant();
/* 294:294 */    if (determinant != 0.0F) {
/* 295:295 */      if (dest == null)
/* 296:296 */        dest = new Matrix2f();
/* 297:297 */      float determinant_inv = 1.0F / determinant;
/* 298:298 */      float t00 = src.m11 * determinant_inv;
/* 299:299 */      float t01 = -src.m01 * determinant_inv;
/* 300:300 */      float t11 = src.m00 * determinant_inv;
/* 301:301 */      float t10 = -src.m10 * determinant_inv;
/* 302:    */      
/* 303:303 */      dest.m00 = t00;
/* 304:304 */      dest.m01 = t01;
/* 305:305 */      dest.m10 = t10;
/* 306:306 */      dest.m11 = t11;
/* 307:307 */      return dest;
/* 308:    */    }
/* 309:309 */    return null;
/* 310:    */  }
/* 311:    */  
/* 314:    */  public String toString()
/* 315:    */  {
/* 316:316 */    StringBuilder buf = new StringBuilder();
/* 317:317 */    buf.append(this.m00).append(' ').append(this.m10).append(' ').append('\n');
/* 318:318 */    buf.append(this.m01).append(' ').append(this.m11).append(' ').append('\n');
/* 319:319 */    return buf.toString();
/* 320:    */  }
/* 321:    */  
/* 325:    */  public Matrix negate()
/* 326:    */  {
/* 327:327 */    return negate(this);
/* 328:    */  }
/* 329:    */  
/* 334:    */  public Matrix2f negate(Matrix2f dest)
/* 335:    */  {
/* 336:336 */    return negate(this, dest);
/* 337:    */  }
/* 338:    */  
/* 344:    */  public static Matrix2f negate(Matrix2f src, Matrix2f dest)
/* 345:    */  {
/* 346:346 */    if (dest == null) {
/* 347:347 */      dest = new Matrix2f();
/* 348:    */    }
/* 349:349 */    dest.m00 = (-src.m00);
/* 350:350 */    dest.m01 = (-src.m01);
/* 351:351 */    dest.m10 = (-src.m10);
/* 352:352 */    dest.m11 = (-src.m11);
/* 353:    */    
/* 354:354 */    return dest;
/* 355:    */  }
/* 356:    */  
/* 360:    */  public Matrix setIdentity()
/* 361:    */  {
/* 362:362 */    return setIdentity(this);
/* 363:    */  }
/* 364:    */  
/* 369:    */  public static Matrix2f setIdentity(Matrix2f src)
/* 370:    */  {
/* 371:371 */    src.m00 = 1.0F;
/* 372:372 */    src.m01 = 0.0F;
/* 373:373 */    src.m10 = 0.0F;
/* 374:374 */    src.m11 = 1.0F;
/* 375:375 */    return src;
/* 376:    */  }
/* 377:    */  
/* 381:    */  public Matrix setZero()
/* 382:    */  {
/* 383:383 */    return setZero(this);
/* 384:    */  }
/* 385:    */  
/* 386:    */  public static Matrix2f setZero(Matrix2f src) {
/* 387:387 */    src.m00 = 0.0F;
/* 388:388 */    src.m01 = 0.0F;
/* 389:389 */    src.m10 = 0.0F;
/* 390:390 */    src.m11 = 0.0F;
/* 391:391 */    return src;
/* 392:    */  }
/* 393:    */  
/* 396:    */  public float determinant()
/* 397:    */  {
/* 398:398 */    return this.m00 * this.m11 - this.m01 * this.m10;
/* 399:    */  }
/* 400:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.vector.Matrix2f
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */