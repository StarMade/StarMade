/*   1:    */package com.bulletphysics.collision.broadphase;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.linearmath.MatrixUtil;
/*   5:    */import com.bulletphysics.linearmath.Transform;
/*   6:    */import com.bulletphysics.linearmath.VectorUtil;
/*   7:    */import javax.vecmath.Vector3f;
/*   8:    */
/*  38:    */public class DbvtAabbMm
/*  39:    */{
/*  40: 40 */  private final Vector3f mi = new Vector3f();
/*  41: 41 */  private final Vector3f mx = new Vector3f();
/*  42:    */  
/*  43:    */  public DbvtAabbMm() {}
/*  44:    */  
/*  45:    */  public DbvtAabbMm(DbvtAabbMm o)
/*  46:    */  {
/*  47: 47 */    set(o);
/*  48:    */  }
/*  49:    */  
/*  50:    */  public void set(DbvtAabbMm o) {
/*  51: 51 */    this.mi.set(o.mi);
/*  52: 52 */    this.mx.set(o.mx);
/*  53:    */  }
/*  54:    */  
/*  55:    */  public static void swap(DbvtAabbMm arg0, DbvtAabbMm arg1) {
/*  56: 56 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*  57:    */      
/*  58: 58 */      tmp.set(p1.mi);
/*  59: 59 */      p1.mi.set(p2.mi);
/*  60: 60 */      p2.mi.set(tmp);
/*  61:    */      
/*  62: 62 */      tmp.set(p1.mx);
/*  63: 63 */      p1.mx.set(p2.mx);
/*  64: 64 */      p2.mx.set(tmp);
/*  65: 65 */    } finally { localStack.pop$javax$vecmath$Vector3f();
/*  66:    */    } }
/*  67:    */  
/*  68: 68 */  public Vector3f Center(Vector3f out) { out.add(this.mi, this.mx);
/*  69: 69 */    out.scale(0.5F);
/*  70: 70 */    return out;
/*  71:    */  }
/*  72:    */  
/*  73:    */  public Vector3f Lengths(Vector3f out) {
/*  74: 74 */    out.sub(this.mx, this.mi);
/*  75: 75 */    return out;
/*  76:    */  }
/*  77:    */  
/*  78:    */  public Vector3f Extents(Vector3f out) {
/*  79: 79 */    out.sub(this.mx, this.mi);
/*  80: 80 */    out.scale(0.5F);
/*  81: 81 */    return out;
/*  82:    */  }
/*  83:    */  
/*  84:    */  public Vector3f Mins() {
/*  85: 85 */    return this.mi;
/*  86:    */  }
/*  87:    */  
/*  88:    */  public Vector3f Maxs() {
/*  89: 89 */    return this.mx;
/*  90:    */  }
/*  91:    */  
/*  92:    */  public static DbvtAabbMm FromCE(Vector3f c, Vector3f e, DbvtAabbMm out) {
/*  93: 93 */    DbvtAabbMm box = out;
/*  94: 94 */    box.mi.sub(c, e);
/*  95: 95 */    box.mx.add(c, e);
/*  96: 96 */    return box;
/*  97:    */  }
/*  98:    */  
/*  99:    */  public static DbvtAabbMm FromCR(Vector3f arg0, float arg1, DbvtAabbMm arg2) {
/* 100:100 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 101:101 */      tmp.set(r, r, r);
/* 102:102 */      return FromCE(c, tmp, out); } finally { localStack.pop$javax$vecmath$Vector3f();
/* 103:    */    }
/* 104:    */  }
/* 105:    */  
/* 106:106 */  public static DbvtAabbMm FromMM(Vector3f mi, Vector3f mx, DbvtAabbMm out) { DbvtAabbMm box = out;
/* 107:107 */    box.mi.set(mi);
/* 108:108 */    box.mx.set(mx);
/* 109:109 */    return box;
/* 110:    */  }
/* 111:    */  
/* 114:    */  public void Expand(Vector3f e)
/* 115:    */  {
/* 116:116 */    this.mi.sub(e);
/* 117:117 */    this.mx.add(e);
/* 118:    */  }
/* 119:    */  
/* 120:    */  public void SignedExpand(Vector3f e) {
/* 121:121 */    if (e.x > 0.0F) {
/* 122:122 */      this.mx.x += e.x;
/* 123:    */    }
/* 124:    */    else {
/* 125:125 */      this.mi.x += e.x;
/* 126:    */    }
/* 127:    */    
/* 128:128 */    if (e.y > 0.0F) {
/* 129:129 */      this.mx.y += e.y;
/* 130:    */    }
/* 131:    */    else {
/* 132:132 */      this.mi.y += e.y;
/* 133:    */    }
/* 134:    */    
/* 135:135 */    if (e.z > 0.0F) {
/* 136:136 */      this.mx.z += e.z;
/* 137:    */    }
/* 138:    */    else {
/* 139:139 */      this.mi.z += e.z;
/* 140:    */    }
/* 141:    */  }
/* 142:    */  
/* 143:    */  public boolean Contain(DbvtAabbMm a) {
/* 144:144 */    return (this.mi.x <= a.mi.x) && (this.mi.y <= a.mi.y) && (this.mi.z <= a.mi.z) && (this.mx.x >= a.mx.x) && (this.mx.y >= a.mx.y) && (this.mx.z >= a.mx.z);
/* 145:    */  }
/* 146:    */  
/* 151:    */  public int Classify(Vector3f arg1, float arg2, int arg3)
/* 152:    */  {
/* 153:153 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f pi = localStack.get$javax$vecmath$Vector3f();
/* 154:154 */      Vector3f px = localStack.get$javax$vecmath$Vector3f();
/* 155:    */      
/* 156:156 */      switch (s) {
/* 157:    */      case 0: 
/* 158:158 */        px.set(this.mi.x, this.mi.y, this.mi.z);
/* 159:159 */        pi.set(this.mx.x, this.mx.y, this.mx.z);
/* 160:160 */        break;
/* 161:    */      case 1: 
/* 162:162 */        px.set(this.mx.x, this.mi.y, this.mi.z);
/* 163:163 */        pi.set(this.mi.x, this.mx.y, this.mx.z);
/* 164:164 */        break;
/* 165:    */      case 2: 
/* 166:166 */        px.set(this.mi.x, this.mx.y, this.mi.z);
/* 167:167 */        pi.set(this.mx.x, this.mi.y, this.mx.z);
/* 168:168 */        break;
/* 169:    */      case 3: 
/* 170:170 */        px.set(this.mx.x, this.mx.y, this.mi.z);
/* 171:171 */        pi.set(this.mi.x, this.mi.y, this.mx.z);
/* 172:172 */        break;
/* 173:    */      case 4: 
/* 174:174 */        px.set(this.mi.x, this.mi.y, this.mx.z);
/* 175:175 */        pi.set(this.mx.x, this.mx.y, this.mi.z);
/* 176:176 */        break;
/* 177:    */      case 5: 
/* 178:178 */        px.set(this.mx.x, this.mi.y, this.mx.z);
/* 179:179 */        pi.set(this.mi.x, this.mx.y, this.mi.z);
/* 180:180 */        break;
/* 181:    */      case 6: 
/* 182:182 */        px.set(this.mi.x, this.mx.y, this.mx.z);
/* 183:183 */        pi.set(this.mx.x, this.mi.y, this.mi.z);
/* 184:184 */        break;
/* 185:    */      case 7: 
/* 186:186 */        px.set(this.mx.x, this.mx.y, this.mx.z);
/* 187:187 */        pi.set(this.mi.x, this.mi.y, this.mi.z);
/* 188:    */      }
/* 189:    */      
/* 190:    */      
/* 191:191 */      if (n.dot(px) + o < 0.0F) {
/* 192:192 */        return -1;
/* 193:    */      }
/* 194:194 */      if (n.dot(pi) + o >= 0.0F) {
/* 195:195 */        return 1;
/* 196:    */      }
/* 197:197 */      return 0; } finally { localStack.pop$javax$vecmath$Vector3f();
/* 198:    */    }
/* 199:    */  }
/* 200:    */  
/* 201:201 */  public float ProjectMinimum(Vector3f arg1, int arg2) { .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f[] b = { this.mx, this.mi };
/* 202:202 */      Vector3f p = localStack.get$javax$vecmath$Vector3f();
/* 203:203 */      p.set(b[(signs >> 0 & 0x1)].x, b[(signs >> 1 & 0x1)].y, b[(signs >> 2 & 0x1)].z);
/* 204:    */      
/* 206:206 */      return p.dot(v); } finally { localStack.pop$javax$vecmath$Vector3f();
/* 207:    */    }
/* 208:    */  }
/* 209:    */  
/* 210:210 */  public static boolean Intersect(DbvtAabbMm a, DbvtAabbMm b) { return (a.mi.x <= b.mx.x) && (a.mx.x >= b.mi.x) && (a.mi.y <= b.mx.y) && (a.mx.y >= b.mi.y) && (a.mi.z <= b.mx.z) && (a.mx.z >= b.mi.z); }
/* 211:    */  
/* 217:    */  public static boolean Intersect(DbvtAabbMm arg0, DbvtAabbMm arg1, Transform arg2)
/* 218:    */  {
/* 219:219 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f d0 = localStack.get$javax$vecmath$Vector3f();
/* 220:220 */      Vector3f d1 = localStack.get$javax$vecmath$Vector3f();
/* 221:221 */      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 222:    */      
/* 224:224 */      b.Center(d0);
/* 225:225 */      xform.transform(d0);
/* 226:226 */      d0.sub(a.Center(tmp));
/* 227:    */      
/* 228:228 */      MatrixUtil.transposeTransform(d1, d0, xform.basis);
/* 229:    */      
/* 230:230 */      float[] s0 = { 0.0F, 0.0F };
/* 231:231 */      float[] s1 = new float[2];
/* 232:232 */      s1[0] = xform.origin.dot(d0);
/* 233:233 */      s1[1] = s1[0];
/* 234:    */      
/* 235:235 */      a.AddSpan(d0, s0, 0, s0, 1);
/* 236:236 */      b.AddSpan(d1, s1, 0, s1, 1);
/* 237:237 */      if (s0[0] > s1[1]) {
/* 238:238 */        return false;
/* 239:    */      }
/* 240:240 */      if (s0[1] < s1[0]) {
/* 241:241 */        return false;
/* 242:    */      }
/* 243:243 */      return true; } finally { localStack.pop$javax$vecmath$Vector3f();
/* 244:    */    }
/* 245:    */  }
/* 246:    */  
/* 247:247 */  public static boolean Intersect(DbvtAabbMm a, Vector3f b) { return (b.x >= a.mi.x) && (b.y >= a.mi.y) && (b.z >= a.mi.z) && (b.x <= a.mx.x) && (b.y <= a.mx.y) && (b.z <= a.mx.z); }
/* 248:    */  
/* 254:    */  public static boolean Intersect(DbvtAabbMm a, Vector3f org, Vector3f invdir, int[] signs)
/* 255:    */  {
/* 256:256 */    Vector3f[] bounds = { a.mi, a.mx };
/* 257:257 */    float txmin = (bounds[signs[0]].x - org.x) * invdir.x;
/* 258:258 */    float txmax = (bounds[(1 - signs[0])].x - org.x) * invdir.x;
/* 259:259 */    float tymin = (bounds[signs[1]].y - org.y) * invdir.y;
/* 260:260 */    float tymax = (bounds[(1 - signs[1])].y - org.y) * invdir.y;
/* 261:261 */    if ((txmin > tymax) || (tymin > txmax)) {
/* 262:262 */      return false;
/* 263:    */    }
/* 264:    */    
/* 265:265 */    if (tymin > txmin) {
/* 266:266 */      txmin = tymin;
/* 267:    */    }
/* 268:268 */    if (tymax < txmax) {
/* 269:269 */      txmax = tymax;
/* 270:    */    }
/* 271:271 */    float tzmin = (bounds[signs[2]].z - org.z) * invdir.z;
/* 272:272 */    float tzmax = (bounds[(1 - signs[2])].z - org.z) * invdir.z;
/* 273:273 */    if ((txmin > tzmax) || (tzmin > txmax)) {
/* 274:274 */      return false;
/* 275:    */    }
/* 276:    */    
/* 277:277 */    if (tzmin > txmin) {
/* 278:278 */      txmin = tzmin;
/* 279:    */    }
/* 280:280 */    if (tzmax < txmax) {
/* 281:281 */      txmax = tzmax;
/* 282:    */    }
/* 283:283 */    return txmax > 0.0F;
/* 284:    */  }
/* 285:    */  
/* 286:    */  public static float Proximity(DbvtAabbMm arg0, DbvtAabbMm arg1) {
/* 287:287 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f d = localStack.get$javax$vecmath$Vector3f();
/* 288:288 */      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 289:    */      
/* 290:290 */      d.add(a.mi, a.mx);
/* 291:291 */      tmp.add(b.mi, b.mx);
/* 292:292 */      d.sub(tmp);
/* 293:293 */      return Math.abs(d.x) + Math.abs(d.y) + Math.abs(d.z); } finally { localStack.pop$javax$vecmath$Vector3f();
/* 294:    */    }
/* 295:    */  }
/* 296:    */  
/* 297:297 */  public static void Merge(DbvtAabbMm a, DbvtAabbMm b, DbvtAabbMm r) { for (int i = 0; i < 3; i++) {
/* 298:298 */      if (VectorUtil.getCoord(a.mi, i) < VectorUtil.getCoord(b.mi, i)) {
/* 299:299 */        VectorUtil.setCoord(r.mi, i, VectorUtil.getCoord(a.mi, i));
/* 300:    */      }
/* 301:    */      else {
/* 302:302 */        VectorUtil.setCoord(r.mi, i, VectorUtil.getCoord(b.mi, i));
/* 303:    */      }
/* 304:    */      
/* 305:305 */      if (VectorUtil.getCoord(a.mx, i) > VectorUtil.getCoord(b.mx, i)) {
/* 306:306 */        VectorUtil.setCoord(r.mx, i, VectorUtil.getCoord(a.mx, i));
/* 307:    */      }
/* 308:    */      else {
/* 309:309 */        VectorUtil.setCoord(r.mx, i, VectorUtil.getCoord(b.mx, i));
/* 310:    */      }
/* 311:    */    }
/* 312:    */  }
/* 313:    */  
/* 314:    */  public static boolean NotEqual(DbvtAabbMm a, DbvtAabbMm b) {
/* 315:315 */    return (a.mi.x != b.mi.x) || (a.mi.y != b.mi.y) || (a.mi.z != b.mi.z) || (a.mx.x != b.mx.x) || (a.mx.y != b.mx.y) || (a.mx.z != b.mx.z);
/* 316:    */  }
/* 317:    */  
/* 322:    */  private void AddSpan(Vector3f d, float[] smi, int smi_idx, float[] smx, int smx_idx)
/* 323:    */  {
/* 324:324 */    for (int i = 0; i < 3; i++) {
/* 325:325 */      if (VectorUtil.getCoord(d, i) < 0.0F) {
/* 326:326 */        smi[smi_idx] += VectorUtil.getCoord(this.mx, i) * VectorUtil.getCoord(d, i);
/* 327:327 */        smx[smx_idx] += VectorUtil.getCoord(this.mi, i) * VectorUtil.getCoord(d, i);
/* 328:    */      }
/* 329:    */      else {
/* 330:330 */        smi[smi_idx] += VectorUtil.getCoord(this.mi, i) * VectorUtil.getCoord(d, i);
/* 331:331 */        smx[smx_idx] += VectorUtil.getCoord(this.mx, i) * VectorUtil.getCoord(d, i);
/* 332:    */      }
/* 333:    */    }
/* 334:    */  }
/* 335:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.broadphase.DbvtAabbMm
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */