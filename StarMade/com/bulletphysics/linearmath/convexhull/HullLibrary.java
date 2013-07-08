/*   1:    */package com.bulletphysics.linearmath.convexhull;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.linearmath.MiscUtil;
/*   5:    */import com.bulletphysics.linearmath.VectorUtil;
/*   6:    */import com.bulletphysics.util.IntArrayList;
/*   7:    */import com.bulletphysics.util.ObjectArrayList;
/*   8:    */import javax.vecmath.Tuple3f;
/*   9:    */import javax.vecmath.Vector3f;
/*  10:    */
/*  44:    */public class HullLibrary
/*  45:    */{
/*  46: 46 */  public final IntArrayList vertexIndexMapping = new IntArrayList();
/*  47:    */  
/*  48: 48 */  private ObjectArrayList<Tri> tris = new ObjectArrayList();
/*  49:    */  
/*  52:    */  private static final float EPSILON = 1.0E-006F;
/*  53:    */  
/*  56:    */  public boolean createConvexHull(HullDesc arg1, HullResult arg2)
/*  57:    */  {
/*  58: 58 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();boolean ret = false;
/*  59:    */      
/*  60: 60 */      PHullResult hr = new PHullResult();
/*  61:    */      
/*  62: 62 */      int vcount = desc.vcount;
/*  63: 63 */      if (vcount < 8) { vcount = 8;
/*  64:    */      }
/*  65: 65 */      ObjectArrayList<Vector3f> vertexSource = new ObjectArrayList();
/*  66: 66 */      MiscUtil.resize(vertexSource, vcount, Vector3f.class);
/*  67:    */      
/*  68: 68 */      Vector3f scale = localStack.get$javax$vecmath$Vector3f();
/*  69:    */      
/*  70: 70 */      int[] ovcount = new int[1];
/*  71:    */      
/*  72: 72 */      boolean ok = cleanupVertices(desc.vcount, desc.vertices, desc.vertexStride, ovcount, vertexSource, desc.normalEpsilon, scale);
/*  73:    */      
/*  74: 74 */      if (ok)
/*  75:    */      {
/*  77: 77 */        for (int i = 0; i < ovcount[0]; i++) {
/*  78: 78 */          Vector3f v = (Vector3f)vertexSource.getQuick(i);
/*  79: 79 */          VectorUtil.mul(v, v, scale);
/*  80:    */        }
/*  81:    */        
/*  83: 83 */        ok = computeHull(ovcount[0], vertexSource, hr, desc.maxVertices);
/*  84:    */        
/*  85: 85 */        if (ok)
/*  86:    */        {
/*  87: 87 */          ObjectArrayList<Vector3f> vertexScratch = new ObjectArrayList();
/*  88: 88 */          MiscUtil.resize(vertexScratch, hr.vcount, Vector3f.class);
/*  89:    */          
/*  90: 90 */          bringOutYourDead(hr.vertices, hr.vcount, vertexScratch, ovcount, hr.indices, hr.indexCount);
/*  91:    */          
/*  92: 92 */          ret = true;
/*  93:    */          
/*  94: 94 */          if (desc.hasHullFlag(HullFlags.TRIANGLES)) {
/*  95: 95 */            result.polygons = false;
/*  96: 96 */            result.numOutputVertices = ovcount[0];
/*  97: 97 */            MiscUtil.resize(result.outputVertices, ovcount[0], Vector3f.class);
/*  98: 98 */            result.numFaces = hr.faceCount;
/*  99: 99 */            result.numIndices = hr.indexCount;
/* 100:    */            
/* 101:101 */            MiscUtil.resize(result.indices, hr.indexCount, 0);
/* 102:    */            
/* 103:103 */            for (int i = 0; i < ovcount[0]; i++) {
/* 104:104 */              ((Vector3f)result.outputVertices.getQuick(i)).set((Tuple3f)vertexScratch.getQuick(i));
/* 105:    */            }
/* 106:    */            
/* 107:107 */            if (desc.hasHullFlag(HullFlags.REVERSE_ORDER)) {
/* 108:108 */              IntArrayList source_ptr = hr.indices;
/* 109:109 */              int source_idx = 0;
/* 110:    */              
/* 111:111 */              IntArrayList dest_ptr = result.indices;
/* 112:112 */              int dest_idx = 0;
/* 113:    */              
/* 114:114 */              for (int i = 0; i < hr.faceCount; i++) {
/* 115:115 */                dest_ptr.set(dest_idx + 0, source_ptr.get(source_idx + 2));
/* 116:116 */                dest_ptr.set(dest_idx + 1, source_ptr.get(source_idx + 1));
/* 117:117 */                dest_ptr.set(dest_idx + 2, source_ptr.get(source_idx + 0));
/* 118:118 */                dest_idx += 3;
/* 119:119 */                source_idx += 3;
/* 120:    */              }
/* 121:    */            }
/* 122:    */            else {
/* 123:123 */              for (int i = 0; i < hr.indexCount; i++) {
/* 124:124 */                result.indices.set(i, hr.indices.get(i));
/* 125:    */              }
/* 126:    */            }
/* 127:    */          }
/* 128:    */          else {
/* 129:129 */            result.polygons = true;
/* 130:130 */            result.numOutputVertices = ovcount[0];
/* 131:131 */            MiscUtil.resize(result.outputVertices, ovcount[0], Vector3f.class);
/* 132:132 */            result.numFaces = hr.faceCount;
/* 133:133 */            result.numIndices = (hr.indexCount + hr.faceCount);
/* 134:134 */            MiscUtil.resize(result.indices, result.numIndices, 0);
/* 135:135 */            for (int i = 0; i < ovcount[0]; i++) {
/* 136:136 */              ((Vector3f)result.outputVertices.getQuick(i)).set((Tuple3f)vertexScratch.getQuick(i));
/* 137:    */            }
/* 138:    */            
/* 141:141 */            IntArrayList source_ptr = hr.indices;
/* 142:142 */            int source_idx = 0;
/* 143:    */            
/* 144:144 */            IntArrayList dest_ptr = result.indices;
/* 145:145 */            int dest_idx = 0;
/* 146:    */            
/* 147:147 */            for (int i = 0; i < hr.faceCount; i++) {
/* 148:148 */              dest_ptr.set(dest_idx + 0, 3);
/* 149:149 */              if (desc.hasHullFlag(HullFlags.REVERSE_ORDER)) {
/* 150:150 */                dest_ptr.set(dest_idx + 1, source_ptr.get(source_idx + 2));
/* 151:151 */                dest_ptr.set(dest_idx + 2, source_ptr.get(source_idx + 1));
/* 152:152 */                dest_ptr.set(dest_idx + 3, source_ptr.get(source_idx + 0));
/* 153:    */              }
/* 154:    */              else {
/* 155:155 */                dest_ptr.set(dest_idx + 1, source_ptr.get(source_idx + 0));
/* 156:156 */                dest_ptr.set(dest_idx + 2, source_ptr.get(source_idx + 1));
/* 157:157 */                dest_ptr.set(dest_idx + 3, source_ptr.get(source_idx + 2));
/* 158:    */              }
/* 159:    */              
/* 160:160 */              dest_idx += 4;
/* 161:161 */              source_idx += 3;
/* 162:    */            }
/* 163:    */          }
/* 164:    */          
/* 165:165 */          releaseHull(hr);
/* 166:    */        }
/* 167:    */      }
/* 168:    */      
/* 169:169 */      return ret; } finally { localStack.pop$javax$vecmath$Vector3f();
/* 170:    */    }
/* 171:    */  }
/* 172:    */  
/* 174:    */  public boolean releaseResult(HullResult result)
/* 175:    */  {
/* 176:176 */    if (result.outputVertices.size() != 0) {
/* 177:177 */      result.numOutputVertices = 0;
/* 178:178 */      result.outputVertices.clear();
/* 179:    */    }
/* 180:180 */    if (result.indices.size() != 0) {
/* 181:181 */      result.numIndices = 0;
/* 182:182 */      result.indices.clear();
/* 183:    */    }
/* 184:184 */    return true;
/* 185:    */  }
/* 186:    */  
/* 187:    */  private boolean computeHull(int vcount, ObjectArrayList<Vector3f> vertices, PHullResult result, int vlimit) {
/* 188:188 */    int[] tris_count = new int[1];
/* 189:189 */    int ret = calchull(vertices, vcount, result.indices, tris_count, vlimit);
/* 190:190 */    if (ret == 0) return false;
/* 191:191 */    result.indexCount = (tris_count[0] * 3);
/* 192:192 */    result.faceCount = tris_count[0];
/* 193:193 */    result.vertices = vertices;
/* 194:194 */    result.vcount = vcount;
/* 195:195 */    return true;
/* 196:    */  }
/* 197:    */  
/* 198:    */  private Tri allocateTriangle(int a, int b, int c) {
/* 199:199 */    Tri tr = new Tri(a, b, c);
/* 200:200 */    tr.id = this.tris.size();
/* 201:201 */    this.tris.add(tr);
/* 202:    */    
/* 203:203 */    return tr;
/* 204:    */  }
/* 205:    */  
/* 206:    */  private void deAllocateTriangle(Tri tri) {
/* 207:207 */    assert (this.tris.getQuick(tri.id) == tri);
/* 208:208 */    this.tris.setQuick(tri.id, null);
/* 209:    */  }
/* 210:    */  
/* 211:    */  private void b2bfix(Tri s, Tri t) {
/* 212:212 */    for (int i = 0; i < 3; i++) {
/* 213:213 */      int i1 = (i + 1) % 3;
/* 214:214 */      int i2 = (i + 2) % 3;
/* 215:215 */      int a = s.getCoord(i1);
/* 216:216 */      int b = s.getCoord(i2);
/* 217:217 */      assert (((Tri)this.tris.getQuick(s.neib(a, b).get())).neib(b, a).get() == s.id);
/* 218:218 */      assert (((Tri)this.tris.getQuick(t.neib(a, b).get())).neib(b, a).get() == t.id);
/* 219:219 */      ((Tri)this.tris.getQuick(s.neib(a, b).get())).neib(b, a).set(t.neib(b, a).get());
/* 220:220 */      ((Tri)this.tris.getQuick(t.neib(b, a).get())).neib(a, b).set(s.neib(a, b).get());
/* 221:    */    }
/* 222:    */  }
/* 223:    */  
/* 224:    */  private void removeb2b(Tri s, Tri t) {
/* 225:225 */    b2bfix(s, t);
/* 226:226 */    deAllocateTriangle(s);
/* 227:    */    
/* 228:228 */    deAllocateTriangle(t);
/* 229:    */  }
/* 230:    */  
/* 231:    */  private void checkit(Tri t) {
/* 232:232 */    assert (this.tris.getQuick(t.id) == t);
/* 233:233 */    for (int i = 0; i < 3; i++) {
/* 234:234 */      int i1 = (i + 1) % 3;
/* 235:235 */      int i2 = (i + 2) % 3;
/* 236:236 */      int a = t.getCoord(i1);
/* 237:237 */      int b = t.getCoord(i2);
/* 238:    */      
/* 239:239 */      assert (a != b);
/* 240:240 */      assert (((Tri)this.tris.getQuick(t.n.getCoord(i))).neib(b, a).get() == t.id);
/* 241:    */    }
/* 242:    */  }
/* 243:    */  
/* 244:    */  private Tri extrudable(float epsilon) {
/* 245:245 */    Tri t = null;
/* 246:246 */    for (int i = 0; i < this.tris.size(); i++) {
/* 247:247 */      if ((t == null) || ((this.tris.getQuick(i) != null) && (t.rise < ((Tri)this.tris.getQuick(i)).rise))) {
/* 248:248 */        t = (Tri)this.tris.getQuick(i);
/* 249:    */      }
/* 250:    */    }
/* 251:251 */    return t.rise > epsilon ? t : null;
/* 252:    */  }
/* 253:    */  
/* 254:    */  private int calchull(ObjectArrayList<Vector3f> verts, int verts_count, IntArrayList tris_out, int[] tris_count, int vlimit) {
/* 255:255 */    int rc = calchullgen(verts, verts_count, vlimit);
/* 256:256 */    if (rc == 0) { return 0;
/* 257:    */    }
/* 258:258 */    IntArrayList ts = new IntArrayList();
/* 259:    */    
/* 260:260 */    for (int i = 0; i < this.tris.size(); i++) {
/* 261:261 */      if (this.tris.getQuick(i) != null) {
/* 262:262 */        for (int j = 0; j < 3; j++) {
/* 263:263 */          ts.add(((Tri)this.tris.getQuick(i)).getCoord(j));
/* 264:    */        }
/* 265:265 */        deAllocateTriangle((Tri)this.tris.getQuick(i));
/* 266:    */      }
/* 267:    */    }
/* 268:268 */    tris_count[0] = (ts.size() / 3);
/* 269:269 */    MiscUtil.resize(tris_out, ts.size(), 0);
/* 270:    */    
/* 271:271 */    for (int i = 0; i < ts.size(); i++) {
/* 272:272 */      tris_out.set(i, ts.get(i));
/* 273:    */    }
/* 274:274 */    MiscUtil.resize(this.tris, 0, Tri.class);
/* 275:    */    
/* 276:276 */    return 1;
/* 277:    */  }
/* 278:    */  
/* 279:    */  private int calchullgen(ObjectArrayList<Vector3f> arg1, int arg2, int arg3) {
/* 280:280 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f(); if (verts_count < 4) { return 0;
/* 281:    */      }
/* 282:282 */      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 283:283 */      Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
/* 284:284 */      Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/* 285:    */      
/* 286:286 */      if (vlimit == 0) {
/* 287:287 */        vlimit = 1000000000;
/* 288:    */      }
/* 289:    */      
/* 290:290 */      Vector3f bmin = localStack.get$javax$vecmath$Vector3f((Vector3f)verts.getQuick(0));
/* 291:291 */      Vector3f bmax = localStack.get$javax$vecmath$Vector3f((Vector3f)verts.getQuick(0));
/* 292:292 */      IntArrayList isextreme = new IntArrayList();
/* 293:    */      
/* 294:294 */      IntArrayList allow = new IntArrayList();
/* 295:    */      
/* 297:297 */      for (int j = 0; j < verts_count; j++) {
/* 298:298 */        allow.add(1);
/* 299:299 */        isextreme.add(0);
/* 300:300 */        VectorUtil.setMin(bmin, (Vector3f)verts.getQuick(j));
/* 301:301 */        VectorUtil.setMax(bmax, (Vector3f)verts.getQuick(j));
/* 302:    */      }
/* 303:303 */      tmp.sub(bmax, bmin);
/* 304:304 */      float epsilon = tmp.length() * 0.001F;
/* 305:305 */      assert (epsilon != 0.0F);
/* 306:    */      
/* 307:307 */      Int4 p = findSimplex(verts, verts_count, allow, new Int4());
/* 308:308 */      if (p.x == -1) {
/* 309:309 */        return 0;
/* 310:    */      }
/* 311:    */      
/* 313:313 */      Vector3f center = localStack.get$javax$vecmath$Vector3f();
/* 314:314 */      VectorUtil.add(center, (Vector3f)verts.getQuick(p.getCoord(0)), (Vector3f)verts.getQuick(p.getCoord(1)), (Vector3f)verts.getQuick(p.getCoord(2)), (Vector3f)verts.getQuick(p.getCoord(3)));
/* 315:315 */      center.scale(0.25F);
/* 316:    */      
/* 317:317 */      Tri t0 = allocateTriangle(p.getCoord(2), p.getCoord(3), p.getCoord(1));
/* 318:318 */      t0.n.set(2, 3, 1);
/* 319:319 */      Tri t1 = allocateTriangle(p.getCoord(3), p.getCoord(2), p.getCoord(0));
/* 320:320 */      t1.n.set(3, 2, 0);
/* 321:321 */      Tri t2 = allocateTriangle(p.getCoord(0), p.getCoord(1), p.getCoord(3));
/* 322:322 */      t2.n.set(0, 1, 3);
/* 323:323 */      Tri t3 = allocateTriangle(p.getCoord(1), p.getCoord(0), p.getCoord(2));
/* 324:324 */      t3.n.set(1, 0, 2);
/* 325:325 */      isextreme.set(p.getCoord(0), 1);
/* 326:326 */      isextreme.set(p.getCoord(1), 1);
/* 327:327 */      isextreme.set(p.getCoord(2), 1);
/* 328:328 */      isextreme.set(p.getCoord(3), 1);
/* 329:329 */      checkit(t0);
/* 330:330 */      checkit(t1);
/* 331:331 */      checkit(t2);
/* 332:332 */      checkit(t3);
/* 333:    */      
/* 334:334 */      Vector3f n = localStack.get$javax$vecmath$Vector3f();
/* 335:    */      
/* 336:336 */      for (int j = 0; j < this.tris.size(); j++) {
/* 337:337 */        Tri t = (Tri)this.tris.getQuick(j);
/* 338:338 */        assert (t != null);
/* 339:339 */        assert (t.vmax < 0);
/* 340:340 */        triNormal((Vector3f)verts.getQuick(t.getCoord(0)), (Vector3f)verts.getQuick(t.getCoord(1)), (Vector3f)verts.getQuick(t.getCoord(2)), n);
/* 341:341 */        t.vmax = maxdirsterid(verts, verts_count, n, allow);
/* 342:342 */        tmp.sub((Tuple3f)verts.getQuick(t.vmax), (Tuple3f)verts.getQuick(t.getCoord(0)));
/* 343:343 */        t.rise = n.dot(tmp);
/* 344:    */      }
/* 345:    */      
/* 346:346 */      vlimit -= 4;
/* 347:347 */      Tri te; while ((vlimit > 0) && ((te = extrudable(epsilon)) != null)) {
/* 348:348 */        Int3 ti = te;
/* 349:349 */        int v = te.vmax;
/* 350:350 */        assert (v != -1);
/* 351:351 */        assert (isextreme.get(v) == 0);
/* 352:352 */        isextreme.set(v, 1);
/* 353:    */        
/* 354:354 */        int j = this.tris.size();
/* 355:355 */        while (j-- != 0) {
/* 356:356 */          if (this.tris.getQuick(j) != null)
/* 357:    */          {
/* 359:359 */            Int3 t = (Int3)this.tris.getQuick(j);
/* 360:360 */            if (above(verts, t, (Vector3f)verts.getQuick(v), 0.01F * epsilon)) {
/* 361:361 */              extrude((Tri)this.tris.getQuick(j), v);
/* 362:    */            }
/* 363:    */          }
/* 364:    */        }
/* 365:365 */        j = this.tris.size();
/* 366:366 */        while (j-- != 0)
/* 367:367 */          if (this.tris.getQuick(j) != null)
/* 368:    */          {
/* 370:370 */            if (!hasvert((Int3)this.tris.getQuick(j), v)) {
/* 371:    */              break;
/* 372:    */            }
/* 373:373 */            Int3 nt = (Int3)this.tris.getQuick(j);
/* 374:374 */            tmp1.sub((Tuple3f)verts.getQuick(nt.getCoord(1)), (Tuple3f)verts.getQuick(nt.getCoord(0)));
/* 375:375 */            tmp2.sub((Tuple3f)verts.getQuick(nt.getCoord(2)), (Tuple3f)verts.getQuick(nt.getCoord(1)));
/* 376:376 */            tmp.cross(tmp1, tmp2);
/* 377:377 */            if ((above(verts, nt, center, 0.01F * epsilon)) || (tmp.length() < epsilon * epsilon * 0.1F)) {
/* 378:378 */              Tri nb = (Tri)this.tris.getQuick(((Tri)this.tris.getQuick(j)).n.getCoord(0));
/* 379:379 */              assert (nb != null);
/* 380:380 */              assert (!hasvert(nb, v));
/* 381:381 */              assert (nb.id < j);
/* 382:382 */              extrude(nb, v);
/* 383:383 */              j = this.tris.size();
/* 384:    */            }
/* 385:    */          }
/* 386:386 */        j = this.tris.size();
/* 387:387 */        while (j-- != 0) {
/* 388:388 */          Tri t = (Tri)this.tris.getQuick(j);
/* 389:389 */          if (t != null)
/* 390:    */          {
/* 392:392 */            if (t.vmax >= 0) {
/* 393:    */              break;
/* 394:    */            }
/* 395:395 */            triNormal((Vector3f)verts.getQuick(t.getCoord(0)), (Vector3f)verts.getQuick(t.getCoord(1)), (Vector3f)verts.getQuick(t.getCoord(2)), n);
/* 396:396 */            t.vmax = maxdirsterid(verts, verts_count, n, allow);
/* 397:397 */            if (isextreme.get(t.vmax) != 0) {
/* 398:398 */              t.vmax = -1;
/* 399:    */            }
/* 400:    */            else {
/* 401:401 */              tmp.sub((Tuple3f)verts.getQuick(t.vmax), (Tuple3f)verts.getQuick(t.getCoord(0)));
/* 402:402 */              t.rise = n.dot(tmp);
/* 403:    */            }
/* 404:    */          } }
/* 405:405 */        vlimit--;
/* 406:    */      }
/* 407:407 */      return 1; } finally { localStack.pop$javax$vecmath$Vector3f();
/* 408:    */    }
/* 409:    */  }
/* 410:    */  
/* 411:411 */  private Int4 findSimplex(ObjectArrayList<Vector3f> arg1, int arg2, IntArrayList arg3, Int4 arg4) { .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 412:412 */      Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
/* 413:413 */      Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/* 414:    */      
/* 415:415 */      Vector3f[] basis = { localStack.get$javax$vecmath$Vector3f(), localStack.get$javax$vecmath$Vector3f(), localStack.get$javax$vecmath$Vector3f() };
/* 416:416 */      basis[0].set(0.01F, 0.02F, 1.0F);
/* 417:417 */      int p0 = maxdirsterid(verts, verts_count, basis[0], allow);
/* 418:418 */      tmp.negate(basis[0]);
/* 419:419 */      int p1 = maxdirsterid(verts, verts_count, tmp, allow);
/* 420:420 */      basis[0].sub((Tuple3f)verts.getQuick(p0), (Tuple3f)verts.getQuick(p1));
/* 421:421 */      if ((p0 == p1) || ((basis[0].x == 0.0F) && (basis[0].y == 0.0F) && (basis[0].z == 0.0F))) {
/* 422:422 */        out.set(-1, -1, -1, -1);
/* 423:423 */        return out;
/* 424:    */      }
/* 425:425 */      tmp.set(1.0F, 0.02F, 0.0F);
/* 426:426 */      basis[1].cross(tmp, basis[0]);
/* 427:427 */      tmp.set(-0.02F, 1.0F, 0.0F);
/* 428:428 */      basis[2].cross(tmp, basis[0]);
/* 429:429 */      if (basis[1].length() > basis[2].length()) {
/* 430:430 */        basis[1].normalize();
/* 431:    */      }
/* 432:    */      else {
/* 433:433 */        basis[1].set(basis[2]);
/* 434:434 */        basis[1].normalize();
/* 435:    */      }
/* 436:436 */      int p2 = maxdirsterid(verts, verts_count, basis[1], allow);
/* 437:437 */      if ((p2 == p0) || (p2 == p1)) {
/* 438:438 */        tmp.negate(basis[1]);
/* 439:439 */        p2 = maxdirsterid(verts, verts_count, tmp, allow);
/* 440:    */      }
/* 441:441 */      if ((p2 == p0) || (p2 == p1)) {
/* 442:442 */        out.set(-1, -1, -1, -1);
/* 443:443 */        return out;
/* 444:    */      }
/* 445:445 */      basis[1].sub((Tuple3f)verts.getQuick(p2), (Tuple3f)verts.getQuick(p0));
/* 446:446 */      basis[2].cross(basis[1], basis[0]);
/* 447:447 */      basis[2].normalize();
/* 448:448 */      int p3 = maxdirsterid(verts, verts_count, basis[2], allow);
/* 449:449 */      if ((p3 == p0) || (p3 == p1) || (p3 == p2)) {
/* 450:450 */        tmp.negate(basis[2]);
/* 451:451 */        p3 = maxdirsterid(verts, verts_count, tmp, allow);
/* 452:    */      }
/* 453:453 */      if ((p3 == p0) || (p3 == p1) || (p3 == p2)) {
/* 454:454 */        out.set(-1, -1, -1, -1);
/* 455:455 */        return out;
/* 456:    */      }
/* 457:457 */      assert ((p0 != p1) && (p0 != p2) && (p0 != p3) && (p1 != p2) && (p1 != p3) && (p2 != p3));
/* 458:    */      
/* 459:459 */      tmp1.sub((Tuple3f)verts.getQuick(p1), (Tuple3f)verts.getQuick(p0));
/* 460:460 */      tmp2.sub((Tuple3f)verts.getQuick(p2), (Tuple3f)verts.getQuick(p0));
/* 461:461 */      tmp2.cross(tmp1, tmp2);
/* 462:462 */      tmp1.sub((Tuple3f)verts.getQuick(p3), (Tuple3f)verts.getQuick(p0));
/* 463:463 */      if (tmp1.dot(tmp2) < 0.0F) {
/* 464:464 */        int swap_tmp = p2;
/* 465:465 */        p2 = p3;
/* 466:466 */        p3 = swap_tmp;
/* 467:    */      }
/* 468:468 */      out.set(p0, p1, p2, p3);
/* 469:469 */      return out; } finally { localStack.pop$javax$vecmath$Vector3f();
/* 470:    */    }
/* 471:    */  }
/* 472:    */  
/* 473:    */  private void extrude(Tri t0, int v)
/* 474:    */  {
/* 475:475 */    Int3 t = new Int3(t0);
/* 476:476 */    int n = this.tris.size();
/* 477:477 */    Tri ta = allocateTriangle(v, t.getCoord(1), t.getCoord(2));
/* 478:478 */    ta.n.set(t0.n.getCoord(0), n + 1, n + 2);
/* 479:479 */    ((Tri)this.tris.getQuick(t0.n.getCoord(0))).neib(t.getCoord(1), t.getCoord(2)).set(n + 0);
/* 480:480 */    Tri tb = allocateTriangle(v, t.getCoord(2), t.getCoord(0));
/* 481:481 */    tb.n.set(t0.n.getCoord(1), n + 2, n + 0);
/* 482:482 */    ((Tri)this.tris.getQuick(t0.n.getCoord(1))).neib(t.getCoord(2), t.getCoord(0)).set(n + 1);
/* 483:483 */    Tri tc = allocateTriangle(v, t.getCoord(0), t.getCoord(1));
/* 484:484 */    tc.n.set(t0.n.getCoord(2), n + 0, n + 1);
/* 485:485 */    ((Tri)this.tris.getQuick(t0.n.getCoord(2))).neib(t.getCoord(0), t.getCoord(1)).set(n + 2);
/* 486:486 */    checkit(ta);
/* 487:487 */    checkit(tb);
/* 488:488 */    checkit(tc);
/* 489:489 */    if (hasvert((Int3)this.tris.getQuick(ta.n.getCoord(0)), v)) {
/* 490:490 */      removeb2b(ta, (Tri)this.tris.getQuick(ta.n.getCoord(0)));
/* 491:    */    }
/* 492:492 */    if (hasvert((Int3)this.tris.getQuick(tb.n.getCoord(0)), v)) {
/* 493:493 */      removeb2b(tb, (Tri)this.tris.getQuick(tb.n.getCoord(0)));
/* 494:    */    }
/* 495:495 */    if (hasvert((Int3)this.tris.getQuick(tc.n.getCoord(0)), v)) {
/* 496:496 */      removeb2b(tc, (Tri)this.tris.getQuick(tc.n.getCoord(0)));
/* 497:    */    }
/* 498:498 */    deAllocateTriangle(t0);
/* 499:    */  }
/* 500:    */  
/* 506:    */  private void bringOutYourDead(ObjectArrayList<Vector3f> verts, int vcount, ObjectArrayList<Vector3f> overts, int[] ocount, IntArrayList indices, int indexcount)
/* 507:    */  {
/* 508:508 */    IntArrayList tmpIndices = new IntArrayList();
/* 509:509 */    for (int i = 0; i < this.vertexIndexMapping.size(); i++) {
/* 510:510 */      tmpIndices.add(this.vertexIndexMapping.size());
/* 511:    */    }
/* 512:    */    
/* 513:513 */    IntArrayList usedIndices = new IntArrayList();
/* 514:514 */    MiscUtil.resize(usedIndices, vcount, 0);
/* 515:    */    
/* 522:522 */    ocount[0] = 0;
/* 523:    */    
/* 524:524 */    for (int i = 0; i < indexcount; i++) {
/* 525:525 */      int v = indices.get(i);
/* 526:    */      
/* 527:527 */      assert ((v >= 0) && (v < vcount));
/* 528:    */      
/* 529:529 */      if (usedIndices.get(v) != 0) {
/* 530:530 */        indices.set(i, usedIndices.get(v) - 1);
/* 531:    */      }
/* 532:    */      else {
/* 533:533 */        indices.set(i, ocount[0]);
/* 534:    */        
/* 535:535 */        ((Vector3f)overts.getQuick(ocount[0])).set((Tuple3f)verts.getQuick(v));
/* 536:    */        
/* 537:537 */        for (int k = 0; k < this.vertexIndexMapping.size(); k++) {
/* 538:538 */          if (tmpIndices.get(k) == v) {
/* 539:539 */            this.vertexIndexMapping.set(k, ocount[0]);
/* 540:    */          }
/* 541:    */        }
/* 542:    */        
/* 543:543 */        ocount[0] += 1;
/* 544:    */        
/* 545:545 */        assert ((ocount[0] >= 0) && (ocount[0] <= vcount));
/* 546:    */        
/* 547:547 */        usedIndices.set(v, ocount[0]);
/* 548:    */      }
/* 549:    */    }
/* 550:    */  }
/* 551:    */  
/* 560:    */  private boolean cleanupVertices(int arg1, ObjectArrayList<Vector3f> arg2, int arg3, int[] arg4, ObjectArrayList<Vector3f> arg5, float arg6, Vector3f arg7)
/* 561:    */  {
/* 562:562 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f(); if (svcount == 0) {
/* 563:563 */        return false;
/* 564:    */      }
/* 565:    */      
/* 566:566 */      this.vertexIndexMapping.clear();
/* 567:    */      
/* 568:568 */      vcount[0] = 0;
/* 569:    */      
/* 570:570 */      float[] recip = new float[3];
/* 571:    */      
/* 572:572 */      if (scale != null) {
/* 573:573 */        scale.set(1.0F, 1.0F, 1.0F);
/* 574:    */      }
/* 575:    */      
/* 576:576 */      float[] bmin = { 3.4028235E+38F, 3.4028235E+38F, 3.4028235E+38F };
/* 577:577 */      float[] bmax = { -3.402824E+038F, -3.402824E+038F, -3.402824E+038F };
/* 578:    */      
/* 579:579 */      ObjectArrayList<Vector3f> vtx_ptr = svertices;
/* 580:580 */      int vtx_idx = 0;
/* 581:    */      
/* 584:584 */      for (int i = 0; i < svcount; i++) {
/* 585:585 */        Vector3f p = (Vector3f)vtx_ptr.getQuick(vtx_idx);
/* 586:    */        
/* 587:587 */        vtx_idx++;
/* 588:    */        
/* 589:589 */        for (int j = 0; j < 3; j++) {
/* 590:590 */          if (VectorUtil.getCoord(p, j) < bmin[j]) {
/* 591:591 */            bmin[j] = VectorUtil.getCoord(p, j);
/* 592:    */          }
/* 593:593 */          if (VectorUtil.getCoord(p, j) > bmax[j]) {
/* 594:594 */            bmax[j] = VectorUtil.getCoord(p, j);
/* 595:    */          }
/* 596:    */        }
/* 597:    */      }
/* 598:    */      
/* 600:600 */      float dx = bmax[0] - bmin[0];
/* 601:601 */      float dy = bmax[1] - bmin[1];
/* 602:602 */      float dz = bmax[2] - bmin[2];
/* 603:    */      
/* 604:604 */      Vector3f center = localStack.get$javax$vecmath$Vector3f();
/* 605:    */      
/* 606:606 */      center.x = (dx * 0.5F + bmin[0]);
/* 607:607 */      center.y = (dy * 0.5F + bmin[1]);
/* 608:608 */      center.z = (dz * 0.5F + bmin[2]);
/* 609:    */      
/* 610:610 */      if ((dx < 1.0E-006F) || (dy < 1.0E-006F) || (dz < 1.0E-006F) || (svcount < 3))
/* 611:    */      {
/* 612:612 */        float len = 3.4028235E+38F;
/* 613:    */        
/* 614:614 */        if ((dx > 1.0E-006F) && (dx < len)) len = dx;
/* 615:615 */        if ((dy > 1.0E-006F) && (dy < len)) len = dy;
/* 616:616 */        if ((dz > 1.0E-006F) && (dz < len)) { len = dz;
/* 617:    */        }
/* 618:618 */        if (len == 3.4028235E+38F) {
/* 619:619 */          dx = dy = dz = 0.01F;
/* 620:    */        }
/* 621:    */        else {
/* 622:622 */          if (dx < 1.0E-006F) dx = len * 0.05F;
/* 623:623 */          if (dy < 1.0E-006F) dy = len * 0.05F;
/* 624:624 */          if (dz < 1.0E-006F) { dz = len * 0.05F;
/* 625:    */          }
/* 626:    */        }
/* 627:627 */        float x1 = center.x - dx;
/* 628:628 */        float x2 = center.x + dx;
/* 629:    */        
/* 630:630 */        float y1 = center.y - dy;
/* 631:631 */        float y2 = center.y + dy;
/* 632:    */        
/* 633:633 */        float z1 = center.z - dz;
/* 634:634 */        float z2 = center.z + dz;
/* 635:    */        
/* 636:636 */        addPoint(vcount, vertices, x1, y1, z1);
/* 637:637 */        addPoint(vcount, vertices, x2, y1, z1);
/* 638:638 */        addPoint(vcount, vertices, x2, y2, z1);
/* 639:639 */        addPoint(vcount, vertices, x1, y2, z1);
/* 640:640 */        addPoint(vcount, vertices, x1, y1, z2);
/* 641:641 */        addPoint(vcount, vertices, x2, y1, z2);
/* 642:642 */        addPoint(vcount, vertices, x2, y2, z2);
/* 643:643 */        addPoint(vcount, vertices, x1, y2, z2);
/* 644:    */        
/* 645:645 */        return true;
/* 646:    */      }
/* 647:    */      
/* 648:648 */      if (scale != null) {
/* 649:649 */        scale.x = dx;
/* 650:650 */        scale.y = dy;
/* 651:651 */        scale.z = dz;
/* 652:    */        
/* 653:653 */        recip[0] = (1.0F / dx);
/* 654:654 */        recip[1] = (1.0F / dy);
/* 655:655 */        recip[2] = (1.0F / dz);
/* 656:    */        
/* 657:657 */        center.x *= recip[0];
/* 658:658 */        center.y *= recip[1];
/* 659:659 */        center.z *= recip[2];
/* 660:    */      }
/* 661:    */      
/* 663:663 */      vtx_ptr = svertices;
/* 664:664 */      vtx_idx = 0;
/* 665:    */      
/* 666:666 */      for (int i = 0; i < svcount; i++) {
/* 667:667 */        Vector3f p = (Vector3f)vtx_ptr.getQuick(vtx_idx);
/* 668:668 */        vtx_idx++;
/* 669:    */        
/* 670:670 */        float px = p.x;
/* 671:671 */        float py = p.y;
/* 672:672 */        float pz = p.z;
/* 673:    */        
/* 674:674 */        if (scale != null) {
/* 675:675 */          px *= recip[0];
/* 676:676 */          py *= recip[1];
/* 677:677 */          pz *= recip[2];
/* 678:    */        }
/* 679:    */        
/* 684:684 */        for (int j = 0; j < vcount[0]; j++)
/* 685:    */        {
/* 686:686 */          Vector3f v = (Vector3f)vertices.getQuick(j);
/* 687:    */          
/* 688:688 */          float x = v.x;
/* 689:689 */          float y = v.y;
/* 690:690 */          float z = v.z;
/* 691:    */          
/* 692:692 */          dx = Math.abs(x - px);
/* 693:693 */          dy = Math.abs(y - py);
/* 694:694 */          dz = Math.abs(z - pz);
/* 695:    */          
/* 696:696 */          if ((dx < normalepsilon) && (dy < normalepsilon) && (dz < normalepsilon))
/* 697:    */          {
/* 701:701 */            float dist1 = getDist(px, py, pz, center);
/* 702:702 */            float dist2 = getDist(v.x, v.y, v.z, center);
/* 703:    */            
/* 704:704 */            if (dist1 <= dist2) break;
/* 705:705 */            v.x = px;
/* 706:706 */            v.y = py;
/* 707:707 */            v.z = pz;break;
/* 708:    */          }
/* 709:    */        }
/* 710:    */        
/* 714:714 */        if (j == vcount[0]) {
/* 715:715 */          Vector3f dest = (Vector3f)vertices.getQuick(vcount[0]);
/* 716:716 */          dest.x = px;
/* 717:717 */          dest.y = py;
/* 718:718 */          dest.z = pz;
/* 719:719 */          vcount[0] += 1;
/* 720:    */        }
/* 721:    */        
/* 722:722 */        this.vertexIndexMapping.add(j);
/* 723:    */      }
/* 724:    */      
/* 729:729 */      bmin = new float[] { 3.4028235E+38F, 3.4028235E+38F, 3.4028235E+38F };
/* 730:730 */      bmax = new float[] { -3.402824E+038F, -3.402824E+038F, -3.402824E+038F };
/* 731:    */      
/* 732:732 */      for (int i = 0; i < vcount[0]; i++) {
/* 733:733 */        Vector3f p = (Vector3f)vertices.getQuick(i);
/* 734:734 */        for (int j = 0; j < 3; j++) {
/* 735:735 */          if (VectorUtil.getCoord(p, j) < bmin[j]) {
/* 736:736 */            bmin[j] = VectorUtil.getCoord(p, j);
/* 737:    */          }
/* 738:738 */          if (VectorUtil.getCoord(p, j) > bmax[j]) {
/* 739:739 */            bmax[j] = VectorUtil.getCoord(p, j);
/* 740:    */          }
/* 741:    */        }
/* 742:    */      }
/* 743:    */      
/* 744:744 */      dx = bmax[0] - bmin[0];
/* 745:745 */      dy = bmax[1] - bmin[1];
/* 746:746 */      dz = bmax[2] - bmin[2];
/* 747:    */      
/* 748:748 */      if ((dx < 1.0E-006F) || (dy < 1.0E-006F) || (dz < 1.0E-006F) || (vcount[0] < 3)) {
/* 749:749 */        float cx = dx * 0.5F + bmin[0];
/* 750:750 */        float cy = dy * 0.5F + bmin[1];
/* 751:751 */        float cz = dz * 0.5F + bmin[2];
/* 752:    */        
/* 753:753 */        float len = 3.4028235E+38F;
/* 754:    */        
/* 755:755 */        if ((dx >= 1.0E-006F) && (dx < len)) len = dx;
/* 756:756 */        if ((dy >= 1.0E-006F) && (dy < len)) len = dy;
/* 757:757 */        if ((dz >= 1.0E-006F) && (dz < len)) { len = dz;
/* 758:    */        }
/* 759:759 */        if (len == 3.4028235E+38F) {
/* 760:760 */          dx = dy = dz = 0.01F;
/* 761:    */        }
/* 762:    */        else {
/* 763:763 */          if (dx < 1.0E-006F) dx = len * 0.05F;
/* 764:764 */          if (dy < 1.0E-006F) dy = len * 0.05F;
/* 765:765 */          if (dz < 1.0E-006F) { dz = len * 0.05F;
/* 766:    */          }
/* 767:    */        }
/* 768:768 */        float x1 = cx - dx;
/* 769:769 */        float x2 = cx + dx;
/* 770:    */        
/* 771:771 */        float y1 = cy - dy;
/* 772:772 */        float y2 = cy + dy;
/* 773:    */        
/* 774:774 */        float z1 = cz - dz;
/* 775:775 */        float z2 = cz + dz;
/* 776:    */        
/* 777:777 */        vcount[0] = 0;
/* 778:    */        
/* 779:779 */        addPoint(vcount, vertices, x1, y1, z1);
/* 780:780 */        addPoint(vcount, vertices, x2, y1, z1);
/* 781:781 */        addPoint(vcount, vertices, x2, y2, z1);
/* 782:782 */        addPoint(vcount, vertices, x1, y2, z1);
/* 783:783 */        addPoint(vcount, vertices, x1, y1, z2);
/* 784:784 */        addPoint(vcount, vertices, x2, y1, z2);
/* 785:785 */        addPoint(vcount, vertices, x2, y2, z2);
/* 786:786 */        addPoint(vcount, vertices, x1, y2, z2);
/* 787:    */        
/* 788:788 */        return true;
/* 789:    */      }
/* 790:    */      
/* 792:792 */      return true; } finally { localStack.pop$javax$vecmath$Vector3f();
/* 793:    */    }
/* 794:    */  }
/* 795:    */  
/* 796:    */  private static boolean hasvert(Int3 t, int v)
/* 797:    */  {
/* 798:798 */    return (t.getCoord(0) == v) || (t.getCoord(1) == v) || (t.getCoord(2) == v);
/* 799:    */  }
/* 800:    */  
/* 801:    */  private static Vector3f orth(Vector3f arg0, Vector3f arg1) {
/* 802:802 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f a = localStack.get$javax$vecmath$Vector3f();
/* 803:803 */      a.set(0.0F, 0.0F, 1.0F);
/* 804:804 */      a.cross(v, a);
/* 805:    */      
/* 806:806 */      Vector3f b = localStack.get$javax$vecmath$Vector3f();
/* 807:807 */      b.set(0.0F, 1.0F, 0.0F);
/* 808:808 */      b.cross(v, b);
/* 809:    */      
/* 810:810 */      if (a.length() > b.length()) {
/* 811:811 */        out.normalize(a);
/* 812:812 */        return out;
/* 813:    */      }
/* 814:    */      
/* 815:815 */      out.normalize(b);
/* 816:816 */      return out; } finally { localStack.pop$javax$vecmath$Vector3f();
/* 817:    */    }
/* 818:    */  }
/* 819:    */  
/* 820:    */  private static int maxdirfiltered(ObjectArrayList<Vector3f> p, int count, Vector3f dir, IntArrayList allow) {
/* 821:821 */    assert (count != 0);
/* 822:822 */    int m = -1;
/* 823:823 */    for (int i = 0; i < count; i++) {
/* 824:824 */      if ((allow.get(i) != 0) && (
/* 825:825 */        (m == -1) || (((Vector3f)p.getQuick(i)).dot(dir) > ((Vector3f)p.getQuick(m)).dot(dir)))) {
/* 826:826 */        m = i;
/* 827:    */      }
/* 828:    */    }
/* 829:    */    
/* 830:830 */    assert (m != -1);
/* 831:831 */    return m;
/* 832:    */  }
/* 833:    */  
/* 834:    */  private static int maxdirsterid(ObjectArrayList<Vector3f> arg0, int arg1, Vector3f arg2, IntArrayList arg3) {
/* 835:835 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 836:836 */      Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
/* 837:837 */      Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/* 838:838 */      Vector3f u = localStack.get$javax$vecmath$Vector3f();
/* 839:839 */      Vector3f v = localStack.get$javax$vecmath$Vector3f();
/* 840:    */      
/* 841:841 */      int m = -1;
/* 842:842 */      while (m == -1) {
/* 843:843 */        m = maxdirfiltered(p, count, dir, allow);
/* 844:844 */        if (allow.get(m) == 3) {
/* 845:845 */          return m;
/* 846:    */        }
/* 847:847 */        orth(dir, u);
/* 848:848 */        v.cross(u, dir);
/* 849:849 */        int ma = -1;
/* 850:850 */        for (float x = 0.0F; x <= 360.0F; x += 45.0F) {
/* 851:851 */          float s = (float)Math.sin(0.01745329F * x);
/* 852:852 */          float c = (float)Math.cos(0.01745329F * x);
/* 853:    */          
/* 854:854 */          tmp1.scale(s, u);
/* 855:855 */          tmp2.scale(c, v);
/* 856:856 */          tmp.add(tmp1, tmp2);
/* 857:857 */          tmp.scale(0.025F);
/* 858:858 */          tmp.add(dir);
/* 859:859 */          int mb = maxdirfiltered(p, count, tmp, allow);
/* 860:860 */          if ((ma == m) && (mb == m)) {
/* 861:861 */            allow.set(m, 3);
/* 862:862 */            return m;
/* 863:    */          }
/* 864:864 */          if ((ma != -1) && (ma != mb)) {
/* 865:865 */            int mc = ma;
/* 866:866 */            for (float xx = x - 40.0F; xx <= x; xx += 5.0F) {
/* 867:867 */              s = (float)Math.sin(0.01745329F * xx);
/* 868:868 */              c = (float)Math.cos(0.01745329F * xx);
/* 869:    */              
/* 870:870 */              tmp1.scale(s, u);
/* 871:871 */              tmp2.scale(c, v);
/* 872:872 */              tmp.add(tmp1, tmp2);
/* 873:873 */              tmp.scale(0.025F);
/* 874:874 */              tmp.add(dir);
/* 875:    */              
/* 876:876 */              int md = maxdirfiltered(p, count, tmp, allow);
/* 877:877 */              if ((mc == m) && (md == m)) {
/* 878:878 */                allow.set(m, 3);
/* 879:879 */                return m;
/* 880:    */              }
/* 881:881 */              mc = md;
/* 882:    */            }
/* 883:    */          }
/* 884:884 */          ma = mb;
/* 885:    */        }
/* 886:886 */        allow.set(m, 0);
/* 887:887 */        m = -1;
/* 888:    */      }
/* 889:889 */      if (!$assertionsDisabled) throw new AssertionError();
/* 890:890 */      return m; } finally { localStack.pop$javax$vecmath$Vector3f();
/* 891:    */    }
/* 892:    */  }
/* 893:    */  
/* 894:894 */  private static Vector3f triNormal(Vector3f arg0, Vector3f arg1, Vector3f arg2, Vector3f arg3) { .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
/* 895:895 */      Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/* 896:    */      
/* 899:899 */      tmp1.sub(v1, v0);
/* 900:900 */      tmp2.sub(v2, v1);
/* 901:901 */      Vector3f cp = localStack.get$javax$vecmath$Vector3f();
/* 902:902 */      cp.cross(tmp1, tmp2);
/* 903:903 */      float m = cp.length();
/* 904:904 */      if (m == 0.0F) {
/* 905:905 */        out.set(1.0F, 0.0F, 0.0F);
/* 906:906 */        return out;
/* 907:    */      }
/* 908:908 */      out.scale(1.0F / m, cp);
/* 909:909 */      return out; } finally { localStack.pop$javax$vecmath$Vector3f();
/* 910:    */    }
/* 911:    */  }
/* 912:    */  
/* 913:913 */  private static boolean above(ObjectArrayList<Vector3f> arg0, Int3 arg1, Vector3f arg2, float arg3) { .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f n = triNormal((Vector3f)vertices.getQuick(t.getCoord(0)), (Vector3f)vertices.getQuick(t.getCoord(1)), (Vector3f)vertices.getQuick(t.getCoord(2)), localStack.get$javax$vecmath$Vector3f());
/* 914:914 */      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 915:915 */      tmp.sub(p, (Tuple3f)vertices.getQuick(t.getCoord(0)));
/* 916:916 */      return n.dot(tmp) > epsilon; } finally { localStack.pop$javax$vecmath$Vector3f();
/* 917:    */    }
/* 918:    */  }
/* 919:    */  
/* 920:920 */  private static void releaseHull(PHullResult result) { if (result.indices.size() != 0) {
/* 921:921 */      result.indices.clear();
/* 922:    */    }
/* 923:    */    
/* 924:924 */    result.vcount = 0;
/* 925:925 */    result.indexCount = 0;
/* 926:926 */    result.vertices = null;
/* 927:    */  }
/* 928:    */  
/* 929:    */  private static void addPoint(int[] vcount, ObjectArrayList<Vector3f> p, float x, float y, float z)
/* 930:    */  {
/* 931:931 */    Vector3f dest = (Vector3f)p.getQuick(vcount[0]);
/* 932:932 */    dest.x = x;
/* 933:933 */    dest.y = y;
/* 934:934 */    dest.z = z;
/* 935:935 */    vcount[0] += 1;
/* 936:    */  }
/* 937:    */  
/* 938:    */  private static float getDist(float px, float py, float pz, Vector3f p2) {
/* 939:939 */    float dx = px - p2.x;
/* 940:940 */    float dy = py - p2.y;
/* 941:941 */    float dz = pz - p2.z;
/* 942:    */    
/* 943:943 */    return dx * dx + dy * dy + dz * dz;
/* 944:    */  }
/* 945:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.linearmath.convexhull.HullLibrary
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */