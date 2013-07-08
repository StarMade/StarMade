/*   1:    */package org.lwjgl.util.vector;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */import java.nio.FloatBuffer;
/*   5:    */
/*  51:    */public class Vector2f
/*  52:    */  extends Vector
/*  53:    */  implements Serializable, ReadableVector2f, WritableVector2f
/*  54:    */{
/*  55:    */  private static final long serialVersionUID = 1L;
/*  56:    */  public float x;
/*  57:    */  public float y;
/*  58:    */  
/*  59:    */  public Vector2f() {}
/*  60:    */  
/*  61:    */  public Vector2f(ReadableVector2f src)
/*  62:    */  {
/*  63: 63 */    set(src);
/*  64:    */  }
/*  65:    */  
/*  68:    */  public Vector2f(float x, float y)
/*  69:    */  {
/*  70: 70 */    set(x, y);
/*  71:    */  }
/*  72:    */  
/*  75:    */  public void set(float x, float y)
/*  76:    */  {
/*  77: 77 */    this.x = x;
/*  78: 78 */    this.y = y;
/*  79:    */  }
/*  80:    */  
/*  85:    */  public Vector2f set(ReadableVector2f src)
/*  86:    */  {
/*  87: 87 */    this.x = src.getX();
/*  88: 88 */    this.y = src.getY();
/*  89: 89 */    return this;
/*  90:    */  }
/*  91:    */  
/*  94:    */  public float lengthSquared()
/*  95:    */  {
/*  96: 96 */    return this.x * this.x + this.y * this.y;
/*  97:    */  }
/*  98:    */  
/* 104:    */  public Vector2f translate(float x, float y)
/* 105:    */  {
/* 106:106 */    this.x += x;
/* 107:107 */    this.y += y;
/* 108:108 */    return this;
/* 109:    */  }
/* 110:    */  
/* 114:    */  public Vector negate()
/* 115:    */  {
/* 116:116 */    this.x = (-this.x);
/* 117:117 */    this.y = (-this.y);
/* 118:118 */    return this;
/* 119:    */  }
/* 120:    */  
/* 125:    */  public Vector2f negate(Vector2f dest)
/* 126:    */  {
/* 127:127 */    if (dest == null)
/* 128:128 */      dest = new Vector2f();
/* 129:129 */    dest.x = (-this.x);
/* 130:130 */    dest.y = (-this.y);
/* 131:131 */    return dest;
/* 132:    */  }
/* 133:    */  
/* 139:    */  public Vector2f normalise(Vector2f dest)
/* 140:    */  {
/* 141:141 */    float l = length();
/* 142:    */    
/* 143:143 */    if (dest == null) {
/* 144:144 */      dest = new Vector2f(this.x / l, this.y / l);
/* 145:    */    } else {
/* 146:146 */      dest.set(this.x / l, this.y / l);
/* 147:    */    }
/* 148:148 */    return dest;
/* 149:    */  }
/* 150:    */  
/* 157:    */  public static float dot(Vector2f left, Vector2f right)
/* 158:    */  {
/* 159:159 */    return left.x * right.x + left.y * right.y;
/* 160:    */  }
/* 161:    */  
/* 169:    */  public static float angle(Vector2f a, Vector2f b)
/* 170:    */  {
/* 171:171 */    float dls = dot(a, b) / (a.length() * b.length());
/* 172:172 */    if (dls < -1.0F) {
/* 173:173 */      dls = -1.0F;
/* 174:174 */    } else if (dls > 1.0F)
/* 175:175 */      dls = 1.0F;
/* 176:176 */    return (float)Math.acos(dls);
/* 177:    */  }
/* 178:    */  
/* 186:    */  public static Vector2f add(Vector2f left, Vector2f right, Vector2f dest)
/* 187:    */  {
/* 188:188 */    if (dest == null) {
/* 189:189 */      return new Vector2f(left.x + right.x, left.y + right.y);
/* 190:    */    }
/* 191:191 */    dest.set(left.x + right.x, left.y + right.y);
/* 192:192 */    return dest;
/* 193:    */  }
/* 194:    */  
/* 203:    */  public static Vector2f sub(Vector2f left, Vector2f right, Vector2f dest)
/* 204:    */  {
/* 205:205 */    if (dest == null) {
/* 206:206 */      return new Vector2f(left.x - right.x, left.y - right.y);
/* 207:    */    }
/* 208:208 */    dest.set(left.x - right.x, left.y - right.y);
/* 209:209 */    return dest;
/* 210:    */  }
/* 211:    */  
/* 217:    */  public Vector store(FloatBuffer buf)
/* 218:    */  {
/* 219:219 */    buf.put(this.x);
/* 220:220 */    buf.put(this.y);
/* 221:221 */    return this;
/* 222:    */  }
/* 223:    */  
/* 228:    */  public Vector load(FloatBuffer buf)
/* 229:    */  {
/* 230:230 */    this.x = buf.get();
/* 231:231 */    this.y = buf.get();
/* 232:232 */    return this;
/* 233:    */  }
/* 234:    */  
/* 238:    */  public Vector scale(float scale)
/* 239:    */  {
/* 240:240 */    this.x *= scale;
/* 241:241 */    this.y *= scale;
/* 242:    */    
/* 243:243 */    return this;
/* 244:    */  }
/* 245:    */  
/* 248:    */  public String toString()
/* 249:    */  {
/* 250:250 */    StringBuilder sb = new StringBuilder(64);
/* 251:    */    
/* 252:252 */    sb.append("Vector2f[");
/* 253:253 */    sb.append(this.x);
/* 254:254 */    sb.append(", ");
/* 255:255 */    sb.append(this.y);
/* 256:256 */    sb.append(']');
/* 257:257 */    return sb.toString();
/* 258:    */  }
/* 259:    */  
/* 262:    */  public final float getX()
/* 263:    */  {
/* 264:264 */    return this.x;
/* 265:    */  }
/* 266:    */  
/* 269:    */  public final float getY()
/* 270:    */  {
/* 271:271 */    return this.y;
/* 272:    */  }
/* 273:    */  
/* 277:    */  public final void setX(float x)
/* 278:    */  {
/* 279:279 */    this.x = x;
/* 280:    */  }
/* 281:    */  
/* 285:    */  public final void setY(float y)
/* 286:    */  {
/* 287:287 */    this.y = y;
/* 288:    */  }
/* 289:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.vector.Vector2f
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */