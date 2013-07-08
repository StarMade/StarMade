/*   1:    */package com.bulletphysics.collision.narrowphase;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.linearmath.VectorUtil;
/*   5:    */import com.bulletphysics.util.ObjectPool;
/*   6:    */import javax.vecmath.Vector3f;
/*   7:    */
/*  19:    */public class VoronoiSimplexSolver
/*  20:    */  extends SimplexSolverInterface
/*  21:    */{
/*  22:    */  protected final ObjectPool<SubSimplexClosestResult> subsimplexResultsPool;
/*  23:    */  private static final int VORONOI_SIMPLEX_MAX_VERTS = 5;
/*  24:    */  private static final int VERTA = 0;
/*  25:    */  private static final int VERTB = 1;
/*  26:    */  private static final int VERTC = 2;
/*  27:    */  private static final int VERTD = 3;
/*  28:    */  public int numVertices;
/*  29:    */  public final Vector3f[] simplexVectorW;
/*  30:    */  public final Vector3f[] simplexPointsP;
/*  31:    */  public final Vector3f[] simplexPointsQ;
/*  32:    */  public final Vector3f cachedP1;
/*  33:    */  public final Vector3f cachedP2;
/*  34:    */  public final Vector3f cachedV;
/*  35:    */  public final Vector3f lastW;
/*  36:    */  public boolean cachedValidClosest;
/*  37:    */  public final SubSimplexClosestResult cachedBC;
/*  38:    */  public boolean needsUpdate;
/*  39:    */  
/*  40:    */  public VoronoiSimplexSolver()
/*  41:    */  {
/*  42: 42 */    this.subsimplexResultsPool = ObjectPool.get(SubSimplexClosestResult.class);
/*  43:    */    
/*  53: 53 */    this.simplexVectorW = new Vector3f[5];
/*  54: 54 */    this.simplexPointsP = new Vector3f[5];
/*  55: 55 */    this.simplexPointsQ = new Vector3f[5];
/*  56:    */    
/*  57: 57 */    this.cachedP1 = new Vector3f();
/*  58: 58 */    this.cachedP2 = new Vector3f();
/*  59: 59 */    this.cachedV = new Vector3f();
/*  60: 60 */    this.lastW = new Vector3f();
/*  61:    */    
/*  63: 63 */    this.cachedBC = new SubSimplexClosestResult();
/*  64:    */    
/*  68: 68 */    for (int i = 0; i < 5; i++) {
/*  69: 69 */      this.simplexVectorW[i] = new Vector3f();
/*  70: 70 */      this.simplexPointsP[i] = new Vector3f();
/*  71: 71 */      this.simplexPointsQ[i] = new Vector3f();
/*  72:    */    }
/*  73:    */  }
/*  74:    */  
/*  75:    */  public void removeVertex(int index) {
/*  76: 76 */    assert (this.numVertices > 0);
/*  77: 77 */    this.numVertices -= 1;
/*  78: 78 */    this.simplexVectorW[index].set(this.simplexVectorW[this.numVertices]);
/*  79: 79 */    this.simplexPointsP[index].set(this.simplexPointsP[this.numVertices]);
/*  80: 80 */    this.simplexPointsQ[index].set(this.simplexPointsQ[this.numVertices]);
/*  81:    */  }
/*  82:    */  
/*  83:    */  public void reduceVertices(UsageBitfield usedVerts) {
/*  84: 84 */    if ((numVertices() >= 4) && (!usedVerts.usedVertexD)) {
/*  85: 85 */      removeVertex(3);
/*  86:    */    }
/*  87: 87 */    if ((numVertices() >= 3) && (!usedVerts.usedVertexC)) {
/*  88: 88 */      removeVertex(2);
/*  89:    */    }
/*  90: 90 */    if ((numVertices() >= 2) && (!usedVerts.usedVertexB)) {
/*  91: 91 */      removeVertex(1);
/*  92:    */    }
/*  93: 93 */    if ((numVertices() >= 1) && (!usedVerts.usedVertexA)) {
/*  94: 94 */      removeVertex(0);
/*  95:    */    }
/*  96:    */  }
/*  97:    */  
/*  98:    */  public boolean updateClosestVectorAndPoints() {
/*  99: 99 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f(); if (this.needsUpdate)
/* 100:    */      {
/* 101:101 */        this.cachedBC.reset();
/* 102:    */        
/* 103:103 */        this.needsUpdate = false;
/* 104:    */        
/* 105:105 */        switch (numVertices())
/* 106:    */        {
/* 107:    */        case 0: 
/* 108:108 */          this.cachedValidClosest = false;
/* 109:109 */          break;
/* 110:    */        
/* 111:    */        case 1: 
/* 112:112 */          this.cachedP1.set(this.simplexPointsP[0]);
/* 113:113 */          this.cachedP2.set(this.simplexPointsQ[0]);
/* 114:114 */          this.cachedV.sub(this.cachedP1, this.cachedP2);
/* 115:115 */          this.cachedBC.reset();
/* 116:116 */          this.cachedBC.setBarycentricCoordinates(1.0F, 0.0F, 0.0F, 0.0F);
/* 117:117 */          this.cachedValidClosest = this.cachedBC.isValid();
/* 118:118 */          break;
/* 119:    */        
/* 121:    */        case 2: 
/* 122:122 */          Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 123:    */          
/* 125:125 */          Vector3f from = this.simplexVectorW[0];
/* 126:126 */          Vector3f to = this.simplexVectorW[1];
/* 127:127 */          Vector3f nearest = localStack.get$javax$vecmath$Vector3f();
/* 128:    */          
/* 129:129 */          Vector3f p = localStack.get$javax$vecmath$Vector3f();
/* 130:130 */          p.set(0.0F, 0.0F, 0.0F);
/* 131:131 */          Vector3f diff = localStack.get$javax$vecmath$Vector3f();
/* 132:132 */          diff.sub(p, from);
/* 133:    */          
/* 134:134 */          Vector3f v = localStack.get$javax$vecmath$Vector3f();
/* 135:135 */          v.sub(to, from);
/* 136:    */          
/* 137:137 */          float t = v.dot(diff);
/* 138:    */          
/* 139:139 */          if (t > 0.0F) {
/* 140:140 */            float dotVV = v.dot(v);
/* 141:141 */            if (t < dotVV) {
/* 142:142 */              t /= dotVV;
/* 143:143 */              tmp.scale(t, v);
/* 144:144 */              diff.sub(tmp);
/* 145:145 */              this.cachedBC.usedVertices.usedVertexA = true;
/* 146:146 */              this.cachedBC.usedVertices.usedVertexB = true;
/* 147:    */            } else {
/* 148:148 */              t = 1.0F;
/* 149:149 */              diff.sub(v);
/* 150:    */              
/* 151:151 */              this.cachedBC.usedVertices.usedVertexB = true;
/* 152:    */            }
/* 153:    */          }
/* 154:    */          else {
/* 155:155 */            t = 0.0F;
/* 156:    */            
/* 157:157 */            this.cachedBC.usedVertices.usedVertexA = true;
/* 158:    */          }
/* 159:159 */          this.cachedBC.setBarycentricCoordinates(1.0F - t, t, 0.0F, 0.0F);
/* 160:    */          
/* 161:161 */          tmp.scale(t, v);
/* 162:162 */          nearest.add(from, tmp);
/* 163:    */          
/* 164:164 */          tmp.sub(this.simplexPointsP[1], this.simplexPointsP[0]);
/* 165:165 */          tmp.scale(t);
/* 166:166 */          this.cachedP1.add(this.simplexPointsP[0], tmp);
/* 167:    */          
/* 168:168 */          tmp.sub(this.simplexPointsQ[1], this.simplexPointsQ[0]);
/* 169:169 */          tmp.scale(t);
/* 170:170 */          this.cachedP2.add(this.simplexPointsQ[0], tmp);
/* 171:    */          
/* 172:172 */          this.cachedV.sub(this.cachedP1, this.cachedP2);
/* 173:    */          
/* 174:174 */          reduceVertices(this.cachedBC.usedVertices);
/* 175:    */          
/* 176:176 */          this.cachedValidClosest = this.cachedBC.isValid();
/* 177:177 */          break;
/* 178:    */        
/* 180:    */        case 3: 
/* 181:181 */          Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
/* 182:182 */          Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/* 183:183 */          Vector3f tmp3 = localStack.get$javax$vecmath$Vector3f();
/* 184:    */          
/* 186:186 */          Vector3f p = localStack.get$javax$vecmath$Vector3f();
/* 187:187 */          p.set(0.0F, 0.0F, 0.0F);
/* 188:    */          
/* 189:189 */          Vector3f a = this.simplexVectorW[0];
/* 190:190 */          Vector3f b = this.simplexVectorW[1];
/* 191:191 */          Vector3f c = this.simplexVectorW[2];
/* 192:    */          
/* 193:193 */          closestPtPointTriangle(p, a, b, c, this.cachedBC);
/* 194:    */          
/* 195:195 */          tmp1.scale(this.cachedBC.barycentricCoords[0], this.simplexPointsP[0]);
/* 196:196 */          tmp2.scale(this.cachedBC.barycentricCoords[1], this.simplexPointsP[1]);
/* 197:197 */          tmp3.scale(this.cachedBC.barycentricCoords[2], this.simplexPointsP[2]);
/* 198:198 */          VectorUtil.add(this.cachedP1, tmp1, tmp2, tmp3);
/* 199:    */          
/* 200:200 */          tmp1.scale(this.cachedBC.barycentricCoords[0], this.simplexPointsQ[0]);
/* 201:201 */          tmp2.scale(this.cachedBC.barycentricCoords[1], this.simplexPointsQ[1]);
/* 202:202 */          tmp3.scale(this.cachedBC.barycentricCoords[2], this.simplexPointsQ[2]);
/* 203:203 */          VectorUtil.add(this.cachedP2, tmp1, tmp2, tmp3);
/* 204:    */          
/* 205:205 */          this.cachedV.sub(this.cachedP1, this.cachedP2);
/* 206:    */          
/* 207:207 */          reduceVertices(this.cachedBC.usedVertices);
/* 208:208 */          this.cachedValidClosest = this.cachedBC.isValid();
/* 209:    */          
/* 210:210 */          break;
/* 211:    */        
/* 213:    */        case 4: 
/* 214:214 */          Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
/* 215:215 */          Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/* 216:216 */          Vector3f tmp3 = localStack.get$javax$vecmath$Vector3f();
/* 217:217 */          Vector3f tmp4 = localStack.get$javax$vecmath$Vector3f();
/* 218:    */          
/* 219:219 */          Vector3f p = localStack.get$javax$vecmath$Vector3f();
/* 220:220 */          p.set(0.0F, 0.0F, 0.0F);
/* 221:    */          
/* 222:222 */          Vector3f a = this.simplexVectorW[0];
/* 223:223 */          Vector3f b = this.simplexVectorW[1];
/* 224:224 */          Vector3f c = this.simplexVectorW[2];
/* 225:225 */          Vector3f d = this.simplexVectorW[3];
/* 226:    */          
/* 227:227 */          boolean hasSeperation = closestPtPointTetrahedron(p, a, b, c, d, this.cachedBC);
/* 228:    */          
/* 229:229 */          if (hasSeperation)
/* 230:    */          {
/* 231:231 */            tmp1.scale(this.cachedBC.barycentricCoords[0], this.simplexPointsP[0]);
/* 232:232 */            tmp2.scale(this.cachedBC.barycentricCoords[1], this.simplexPointsP[1]);
/* 233:233 */            tmp3.scale(this.cachedBC.barycentricCoords[2], this.simplexPointsP[2]);
/* 234:234 */            tmp4.scale(this.cachedBC.barycentricCoords[3], this.simplexPointsP[3]);
/* 235:235 */            VectorUtil.add(this.cachedP1, tmp1, tmp2, tmp3, tmp4);
/* 236:    */            
/* 237:237 */            tmp1.scale(this.cachedBC.barycentricCoords[0], this.simplexPointsQ[0]);
/* 238:238 */            tmp2.scale(this.cachedBC.barycentricCoords[1], this.simplexPointsQ[1]);
/* 239:239 */            tmp3.scale(this.cachedBC.barycentricCoords[2], this.simplexPointsQ[2]);
/* 240:240 */            tmp4.scale(this.cachedBC.barycentricCoords[3], this.simplexPointsQ[3]);
/* 241:241 */            VectorUtil.add(this.cachedP2, tmp1, tmp2, tmp3, tmp4);
/* 242:    */            
/* 243:243 */            this.cachedV.sub(this.cachedP1, this.cachedP2);
/* 244:244 */            reduceVertices(this.cachedBC.usedVertices);
/* 246:    */          }
/* 247:    */          else
/* 248:    */          {
/* 249:249 */            if (this.cachedBC.degenerate)
/* 250:    */            {
/* 251:251 */              this.cachedValidClosest = false;break;
/* 252:    */            }
/* 253:    */            
/* 254:254 */            this.cachedValidClosest = true;
/* 255:    */            
/* 256:256 */            this.cachedV.set(0.0F, 0.0F, 0.0F);
/* 257:    */            
/* 258:258 */            break;
/* 259:    */          }
/* 260:    */          
/* 261:261 */          this.cachedValidClosest = this.cachedBC.isValid();
/* 262:    */          
/* 264:264 */          break;
/* 265:    */        
/* 267:    */        default: 
/* 268:268 */          this.cachedValidClosest = false;
/* 269:    */        }
/* 270:    */        
/* 271:    */      }
/* 272:    */      
/* 273:273 */      return this.cachedValidClosest; } finally { localStack.pop$javax$vecmath$Vector3f();
/* 274:    */    }
/* 275:    */  }
/* 276:    */  
/* 277:    */  public boolean closestPtPointTriangle(Vector3f arg1, Vector3f arg2, Vector3f arg3, Vector3f arg4, SubSimplexClosestResult arg5) {
/* 278:278 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();result.usedVertices.reset();
/* 279:    */      
/* 281:281 */      Vector3f ab = localStack.get$javax$vecmath$Vector3f();
/* 282:282 */      ab.sub(b, a);
/* 283:    */      
/* 284:284 */      Vector3f ac = localStack.get$javax$vecmath$Vector3f();
/* 285:285 */      ac.sub(c, a);
/* 286:    */      
/* 287:287 */      Vector3f ap = localStack.get$javax$vecmath$Vector3f();
/* 288:288 */      ap.sub(p, a);
/* 289:    */      
/* 290:290 */      float d1 = ab.dot(ap);
/* 291:291 */      float d2 = ac.dot(ap);
/* 292:    */      
/* 293:293 */      if ((d1 <= 0.0F) && (d2 <= 0.0F))
/* 294:    */      {
/* 295:295 */        result.closestPointOnSimplex.set(a);
/* 296:296 */        result.usedVertices.usedVertexA = true;
/* 297:297 */        result.setBarycentricCoordinates(1.0F, 0.0F, 0.0F, 0.0F);
/* 298:298 */        return true;
/* 299:    */      }
/* 300:    */      
/* 302:302 */      Vector3f bp = localStack.get$javax$vecmath$Vector3f();
/* 303:303 */      bp.sub(p, b);
/* 304:    */      
/* 305:305 */      float d3 = ab.dot(bp);
/* 306:306 */      float d4 = ac.dot(bp);
/* 307:    */      
/* 308:308 */      if ((d3 >= 0.0F) && (d4 <= d3))
/* 309:    */      {
/* 310:310 */        result.closestPointOnSimplex.set(b);
/* 311:311 */        result.usedVertices.usedVertexB = true;
/* 312:312 */        result.setBarycentricCoordinates(0.0F, 1.0F, 0.0F, 0.0F);
/* 313:    */        
/* 314:314 */        return true;
/* 315:    */      }
/* 316:    */      
/* 318:318 */      float vc = d1 * d4 - d3 * d2;
/* 319:319 */      if ((vc <= 0.0F) && (d1 >= 0.0F) && (d3 <= 0.0F)) {
/* 320:320 */        float v = d1 / (d1 - d3);
/* 321:321 */        result.closestPointOnSimplex.scaleAdd(v, ab, a);
/* 322:322 */        result.usedVertices.usedVertexA = true;
/* 323:323 */        result.usedVertices.usedVertexB = true;
/* 324:324 */        result.setBarycentricCoordinates(1.0F - v, v, 0.0F, 0.0F);
/* 325:325 */        return true;
/* 326:    */      }
/* 327:    */      
/* 330:330 */      Vector3f cp = localStack.get$javax$vecmath$Vector3f();
/* 331:331 */      cp.sub(p, c);
/* 332:    */      
/* 333:333 */      float d5 = ab.dot(cp);
/* 334:334 */      float d6 = ac.dot(cp);
/* 335:    */      
/* 336:336 */      if ((d6 >= 0.0F) && (d5 <= d6))
/* 337:    */      {
/* 338:338 */        result.closestPointOnSimplex.set(c);
/* 339:339 */        result.usedVertices.usedVertexC = true;
/* 340:340 */        result.setBarycentricCoordinates(0.0F, 0.0F, 1.0F, 0.0F);
/* 341:341 */        return true;
/* 342:    */      }
/* 343:    */      
/* 345:345 */      float vb = d5 * d2 - d1 * d6;
/* 346:346 */      if ((vb <= 0.0F) && (d2 >= 0.0F) && (d6 <= 0.0F)) {
/* 347:347 */        float w = d2 / (d2 - d6);
/* 348:348 */        result.closestPointOnSimplex.scaleAdd(w, ac, a);
/* 349:349 */        result.usedVertices.usedVertexA = true;
/* 350:350 */        result.usedVertices.usedVertexC = true;
/* 351:351 */        result.setBarycentricCoordinates(1.0F - w, 0.0F, w, 0.0F);
/* 352:352 */        return true;
/* 353:    */      }
/* 354:    */      
/* 357:357 */      float va = d3 * d6 - d5 * d4;
/* 358:358 */      if ((va <= 0.0F) && (d4 - d3 >= 0.0F) && (d5 - d6 >= 0.0F)) {
/* 359:359 */        float w = (d4 - d3) / (d4 - d3 + (d5 - d6));
/* 360:    */        
/* 361:361 */        Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 362:362 */        tmp.sub(c, b);
/* 363:363 */        result.closestPointOnSimplex.scaleAdd(w, tmp, b);
/* 364:    */        
/* 365:365 */        result.usedVertices.usedVertexB = true;
/* 366:366 */        result.usedVertices.usedVertexC = true;
/* 367:367 */        result.setBarycentricCoordinates(0.0F, 1.0F - w, w, 0.0F);
/* 368:368 */        return true;
/* 369:    */      }
/* 370:    */      
/* 373:373 */      float denom = 1.0F / (va + vb + vc);
/* 374:374 */      float v = vb * denom;
/* 375:375 */      float w = vc * denom;
/* 376:    */      
/* 377:377 */      Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
/* 378:378 */      Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/* 379:    */      
/* 380:380 */      tmp1.scale(v, ab);
/* 381:381 */      tmp2.scale(w, ac);
/* 382:382 */      VectorUtil.add(result.closestPointOnSimplex, a, tmp1, tmp2);
/* 383:383 */      result.usedVertices.usedVertexA = true;
/* 384:384 */      result.usedVertices.usedVertexB = true;
/* 385:385 */      result.usedVertices.usedVertexC = true;
/* 386:386 */      result.setBarycentricCoordinates(1.0F - v - w, v, w, 0.0F);
/* 387:    */      
/* 388:388 */      return true; } finally { localStack.pop$javax$vecmath$Vector3f();
/* 389:    */    }
/* 390:    */  }
/* 391:    */  
/* 394:    */  public static int pointOutsideOfPlane(Vector3f arg0, Vector3f arg1, Vector3f arg2, Vector3f arg3, Vector3f arg4)
/* 395:    */  {
/* 396:396 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 397:    */      
/* 398:398 */      Vector3f normal = localStack.get$javax$vecmath$Vector3f();
/* 399:399 */      normal.sub(b, a);
/* 400:400 */      tmp.sub(c, a);
/* 401:401 */      normal.cross(normal, tmp);
/* 402:    */      
/* 403:403 */      tmp.sub(p, a);
/* 404:404 */      float signp = tmp.dot(normal);
/* 405:    */      
/* 406:406 */      tmp.sub(d, a);
/* 407:407 */      float signd = tmp.dot(normal);
/* 408:    */      
/* 416:416 */      if (signd * signd < 9.999999E-009F)
/* 417:    */      {
/* 419:419 */        return -1;
/* 420:    */      }
/* 421:    */      
/* 425:425 */      return signp * signd < 0.0F ? 1 : 0; } finally { localStack.pop$javax$vecmath$Vector3f();
/* 426:    */    }
/* 427:    */  }
/* 428:    */  
/* 429:    */  /* Error */
/* 430:    */  public boolean closestPtPointTetrahedron(Vector3f arg1, Vector3f arg2, Vector3f arg3, Vector3f arg4, Vector3f arg5, SubSimplexClosestResult arg6)
/* 431:    */  {
/* 432:    */    // Byte code:
/* 433:    */    //   0: invokestatic 118	com/bulletphysics/$Stack:get	()Lcom/bulletphysics/$Stack;
/* 434:    */    //   3: astore 17
/* 435:    */    //   5: aload 17
/* 436:    */    //   7: invokevirtual 121	com/bulletphysics/$Stack:push$javax$vecmath$Vector3f	()V
/* 437:    */    //   10: aload_0
/* 438:    */    //   11: getfield 53	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver:subsimplexResultsPool	Lcom/bulletphysics/util/ObjectPool;
/* 439:    */    //   14: invokevirtual 252	com/bulletphysics/util/ObjectPool:get	()Ljava/lang/Object;
/* 440:    */    //   17: checkcast 7	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult
/* 441:    */    //   20: astore 7
/* 442:    */    //   22: aload 7
/* 443:    */    //   24: invokevirtual 126	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:reset	()V
/* 444:    */    //   27: aload 17
/* 445:    */    //   29: invokevirtual 143	com/bulletphysics/$Stack:get$javax$vecmath$Vector3f	()Ljavax/vecmath/Vector3f;
/* 446:    */    //   32: astore 8
/* 447:    */    //   34: aload 17
/* 448:    */    //   36: invokevirtual 143	com/bulletphysics/$Stack:get$javax$vecmath$Vector3f	()Ljavax/vecmath/Vector3f;
/* 449:    */    //   39: astore 9
/* 450:    */    //   41: aload 6
/* 451:    */    //   43: getfield 218	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:closestPointOnSimplex	Ljavax/vecmath/Vector3f;
/* 452:    */    //   46: aload_1
/* 453:    */    //   47: invokevirtual 89	javax/vecmath/Vector3f:set	(Ljavax/vecmath/Tuple3f;)V
/* 454:    */    //   50: aload 6
/* 455:    */    //   52: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/* 456:    */    //   55: invokevirtual 215	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:reset	()V
/* 457:    */    //   58: aload 6
/* 458:    */    //   60: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/* 459:    */    //   63: iconst_1
/* 460:    */    //   64: putfield 109	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexA	Z
/* 461:    */    //   67: aload 6
/* 462:    */    //   69: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/* 463:    */    //   72: iconst_1
/* 464:    */    //   73: putfield 106	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexB	Z
/* 465:    */    //   76: aload 6
/* 466:    */    //   78: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/* 467:    */    //   81: iconst_1
/* 468:    */    //   82: putfield 103	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexC	Z
/* 469:    */    //   85: aload 6
/* 470:    */    //   87: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/* 471:    */    //   90: iconst_1
/* 472:    */    //   91: putfield 98	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexD	Z
/* 473:    */    //   94: aload_1
/* 474:    */    //   95: aload_2
/* 475:    */    //   96: aload_3
/* 476:    */    //   97: aload 4
/* 477:    */    //   99: aload 5
/* 478:    */    //   101: invokestatic 254	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver:pointOutsideOfPlane	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;)I
/* 479:    */    //   104: istore 10
/* 480:    */    //   106: aload_1
/* 481:    */    //   107: aload_2
/* 482:    */    //   108: aload 4
/* 483:    */    //   110: aload 5
/* 484:    */    //   112: aload_3
/* 485:    */    //   113: invokestatic 254	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver:pointOutsideOfPlane	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;)I
/* 486:    */    //   116: istore 11
/* 487:    */    //   118: aload_1
/* 488:    */    //   119: aload_2
/* 489:    */    //   120: aload 5
/* 490:    */    //   122: aload_3
/* 491:    */    //   123: aload 4
/* 492:    */    //   125: invokestatic 254	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver:pointOutsideOfPlane	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;)I
/* 493:    */    //   128: istore 12
/* 494:    */    //   130: aload_1
/* 495:    */    //   131: aload_3
/* 496:    */    //   132: aload 5
/* 497:    */    //   134: aload 4
/* 498:    */    //   136: aload_2
/* 499:    */    //   137: invokestatic 254	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver:pointOutsideOfPlane	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;)I
/* 500:    */    //   140: istore 13
/* 501:    */    //   142: iload 10
/* 502:    */    //   144: iflt +18 -> 162
/* 503:    */    //   147: iload 11
/* 504:    */    //   149: iflt +13 -> 162
/* 505:    */    //   152: iload 12
/* 506:    */    //   154: iflt +8 -> 162
/* 507:    */    //   157: iload 13
/* 508:    */    //   159: ifge +29 -> 188
/* 509:    */    //   162: aload 6
/* 510:    */    //   164: iconst_1
/* 511:    */    //   165: putfield 190	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:degenerate	Z
/* 512:    */    //   168: iconst_0
/* 513:    */    //   169: istore 14
/* 514:    */    //   171: aload_0
/* 515:    */    //   172: getfield 53	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver:subsimplexResultsPool	Lcom/bulletphysics/util/ObjectPool;
/* 516:    */    //   175: aload 7
/* 517:    */    //   177: invokevirtual 258	com/bulletphysics/util/ObjectPool:release	(Ljava/lang/Object;)V
/* 518:    */    //   180: iload 14
/* 519:    */    //   182: aload 17
/* 520:    */    //   184: invokevirtual 193	com/bulletphysics/$Stack:pop$javax$vecmath$Vector3f	()V
/* 521:    */    //   187: ireturn
/* 522:    */    //   188: iload 10
/* 523:    */    //   190: ifne +38 -> 228
/* 524:    */    //   193: iload 11
/* 525:    */    //   195: ifne +33 -> 228
/* 526:    */    //   198: iload 12
/* 527:    */    //   200: ifne +28 -> 228
/* 528:    */    //   203: iload 13
/* 529:    */    //   205: ifne +23 -> 228
/* 530:    */    //   208: iconst_0
/* 531:    */    //   209: istore 14
/* 532:    */    //   211: aload_0
/* 533:    */    //   212: getfield 53	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver:subsimplexResultsPool	Lcom/bulletphysics/util/ObjectPool;
/* 534:    */    //   215: aload 7
/* 535:    */    //   217: invokevirtual 258	com/bulletphysics/util/ObjectPool:release	(Ljava/lang/Object;)V
/* 536:    */    //   220: iload 14
/* 537:    */    //   222: aload 17
/* 538:    */    //   224: invokevirtual 193	com/bulletphysics/$Stack:pop$javax$vecmath$Vector3f	()V
/* 539:    */    //   227: ireturn
/* 540:    */    //   228: ldc_w 259
/* 541:    */    //   231: fstore 14
/* 542:    */    //   233: iload 10
/* 543:    */    //   235: ifeq +147 -> 382
/* 544:    */    //   238: aload_0
/* 545:    */    //   239: aload_1
/* 546:    */    //   240: aload_2
/* 547:    */    //   241: aload_3
/* 548:    */    //   242: aload 4
/* 549:    */    //   244: aload 7
/* 550:    */    //   246: invokevirtual 171	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver:closestPtPointTriangle	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult;)Z
/* 551:    */    //   249: pop
/* 552:    */    //   250: aload 9
/* 553:    */    //   252: aload 7
/* 554:    */    //   254: getfield 218	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:closestPointOnSimplex	Ljavax/vecmath/Vector3f;
/* 555:    */    //   257: invokevirtual 89	javax/vecmath/Vector3f:set	(Ljavax/vecmath/Tuple3f;)V
/* 556:    */    //   260: aload 8
/* 557:    */    //   262: aload 9
/* 558:    */    //   264: aload_1
/* 559:    */    //   265: invokevirtual 132	javax/vecmath/Vector3f:sub	(Ljavax/vecmath/Tuple3f;Ljavax/vecmath/Tuple3f;)V
/* 560:    */    //   268: aload 8
/* 561:    */    //   270: aload 8
/* 562:    */    //   272: invokevirtual 150	javax/vecmath/Vector3f:dot	(Ljavax/vecmath/Vector3f;)F
/* 563:    */    //   275: fstore 15
/* 564:    */    //   277: fload 15
/* 565:    */    //   279: fload 14
/* 566:    */    //   281: fcmpg
/* 567:    */    //   282: ifge +100 -> 382
/* 568:    */    //   285: fload 15
/* 569:    */    //   287: fstore 14
/* 570:    */    //   289: aload 6
/* 571:    */    //   291: getfield 218	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:closestPointOnSimplex	Ljavax/vecmath/Vector3f;
/* 572:    */    //   294: aload 9
/* 573:    */    //   296: invokevirtual 89	javax/vecmath/Vector3f:set	(Ljavax/vecmath/Tuple3f;)V
/* 574:    */    //   299: aload 6
/* 575:    */    //   301: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/* 576:    */    //   304: invokevirtual 215	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:reset	()V
/* 577:    */    //   307: aload 6
/* 578:    */    //   309: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/* 579:    */    //   312: aload 7
/* 580:    */    //   314: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/* 581:    */    //   317: getfield 109	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexA	Z
/* 582:    */    //   320: putfield 109	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexA	Z
/* 583:    */    //   323: aload 6
/* 584:    */    //   325: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/* 585:    */    //   328: aload 7
/* 586:    */    //   330: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/* 587:    */    //   333: getfield 106	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexB	Z
/* 588:    */    //   336: putfield 106	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexB	Z
/* 589:    */    //   339: aload 6
/* 590:    */    //   341: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/* 591:    */    //   344: aload 7
/* 592:    */    //   346: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/* 593:    */    //   349: getfield 103	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexC	Z
/* 594:    */    //   352: putfield 103	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexC	Z
/* 595:    */    //   355: aload 6
/* 596:    */    //   357: aload 7
/* 597:    */    //   359: getfield 175	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:barycentricCoords	[F
/* 598:    */    //   362: iconst_0
/* 599:    */    //   363: faload
/* 600:    */    //   364: aload 7
/* 601:    */    //   366: getfield 175	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:barycentricCoords	[F
/* 602:    */    //   369: iconst_1
/* 603:    */    //   370: faload
/* 604:    */    //   371: aload 7
/* 605:    */    //   373: getfield 175	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:barycentricCoords	[F
/* 606:    */    //   376: iconst_2
/* 607:    */    //   377: faload
/* 608:    */    //   378: fconst_0
/* 609:    */    //   379: invokevirtual 136	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:setBarycentricCoordinates	(FFFF)V
/* 610:    */    //   382: iload 11
/* 611:    */    //   384: ifeq +148 -> 532
/* 612:    */    //   387: aload_0
/* 613:    */    //   388: aload_1
/* 614:    */    //   389: aload_2
/* 615:    */    //   390: aload 4
/* 616:    */    //   392: aload 5
/* 617:    */    //   394: aload 7
/* 618:    */    //   396: invokevirtual 171	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver:closestPtPointTriangle	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult;)Z
/* 619:    */    //   399: pop
/* 620:    */    //   400: aload 9
/* 621:    */    //   402: aload 7
/* 622:    */    //   404: getfield 218	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:closestPointOnSimplex	Ljavax/vecmath/Vector3f;
/* 623:    */    //   407: invokevirtual 89	javax/vecmath/Vector3f:set	(Ljavax/vecmath/Tuple3f;)V
/* 624:    */    //   410: aload 8
/* 625:    */    //   412: aload 9
/* 626:    */    //   414: aload_1
/* 627:    */    //   415: invokevirtual 132	javax/vecmath/Vector3f:sub	(Ljavax/vecmath/Tuple3f;Ljavax/vecmath/Tuple3f;)V
/* 628:    */    //   418: aload 8
/* 629:    */    //   420: aload 8
/* 630:    */    //   422: invokevirtual 150	javax/vecmath/Vector3f:dot	(Ljavax/vecmath/Vector3f;)F
/* 631:    */    //   425: fstore 15
/* 632:    */    //   427: fload 15
/* 633:    */    //   429: fload 14
/* 634:    */    //   431: fcmpg
/* 635:    */    //   432: ifge +100 -> 532
/* 636:    */    //   435: fload 15
/* 637:    */    //   437: fstore 14
/* 638:    */    //   439: aload 6
/* 639:    */    //   441: getfield 218	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:closestPointOnSimplex	Ljavax/vecmath/Vector3f;
/* 640:    */    //   444: aload 9
/* 641:    */    //   446: invokevirtual 89	javax/vecmath/Vector3f:set	(Ljavax/vecmath/Tuple3f;)V
/* 642:    */    //   449: aload 6
/* 643:    */    //   451: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/* 644:    */    //   454: invokevirtual 215	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:reset	()V
/* 645:    */    //   457: aload 6
/* 646:    */    //   459: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/* 647:    */    //   462: aload 7
/* 648:    */    //   464: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/* 649:    */    //   467: getfield 109	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexA	Z
/* 650:    */    //   470: putfield 109	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexA	Z
/* 651:    */    //   473: aload 6
/* 652:    */    //   475: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/* 653:    */    //   478: aload 7
/* 654:    */    //   480: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/* 655:    */    //   483: getfield 106	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexB	Z
/* 656:    */    //   486: putfield 103	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexC	Z
/* 657:    */    //   489: aload 6
/* 658:    */    //   491: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/* 659:    */    //   494: aload 7
/* 660:    */    //   496: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/* 661:    */    //   499: getfield 103	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexC	Z
/* 662:    */    //   502: putfield 98	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexD	Z
/* 663:    */    //   505: aload 6
/* 664:    */    //   507: aload 7
/* 665:    */    //   509: getfield 175	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:barycentricCoords	[F
/* 666:    */    //   512: iconst_0
/* 667:    */    //   513: faload
/* 668:    */    //   514: fconst_0
/* 669:    */    //   515: aload 7
/* 670:    */    //   517: getfield 175	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:barycentricCoords	[F
/* 671:    */    //   520: iconst_1
/* 672:    */    //   521: faload
/* 673:    */    //   522: aload 7
/* 674:    */    //   524: getfield 175	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:barycentricCoords	[F
/* 675:    */    //   527: iconst_2
/* 676:    */    //   528: faload
/* 677:    */    //   529: invokevirtual 136	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:setBarycentricCoordinates	(FFFF)V
/* 678:    */    //   532: iload 12
/* 679:    */    //   534: ifeq +147 -> 681
/* 680:    */    //   537: aload_0
/* 681:    */    //   538: aload_1
/* 682:    */    //   539: aload_2
/* 683:    */    //   540: aload 5
/* 684:    */    //   542: aload_3
/* 685:    */    //   543: aload 7
/* 686:    */    //   545: invokevirtual 171	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver:closestPtPointTriangle	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult;)Z
/* 687:    */    //   548: pop
/* 688:    */    //   549: aload 9
/* 689:    */    //   551: aload 7
/* 690:    */    //   553: getfield 218	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:closestPointOnSimplex	Ljavax/vecmath/Vector3f;
/* 691:    */    //   556: invokevirtual 89	javax/vecmath/Vector3f:set	(Ljavax/vecmath/Tuple3f;)V
/* 692:    */    //   559: aload 8
/* 693:    */    //   561: aload 9
/* 694:    */    //   563: aload_1
/* 695:    */    //   564: invokevirtual 132	javax/vecmath/Vector3f:sub	(Ljavax/vecmath/Tuple3f;Ljavax/vecmath/Tuple3f;)V
/* 696:    */    //   567: aload 8
/* 697:    */    //   569: aload 8
/* 698:    */    //   571: invokevirtual 150	javax/vecmath/Vector3f:dot	(Ljavax/vecmath/Vector3f;)F
/* 699:    */    //   574: fstore 15
/* 700:    */    //   576: fload 15
/* 701:    */    //   578: fload 14
/* 702:    */    //   580: fcmpg
/* 703:    */    //   581: ifge +100 -> 681
/* 704:    */    //   584: fload 15
/* 705:    */    //   586: fstore 14
/* 706:    */    //   588: aload 6
/* 707:    */    //   590: getfield 218	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:closestPointOnSimplex	Ljavax/vecmath/Vector3f;
/* 708:    */    //   593: aload 9
/* 709:    */    //   595: invokevirtual 89	javax/vecmath/Vector3f:set	(Ljavax/vecmath/Tuple3f;)V
/* 710:    */    //   598: aload 6
/* 711:    */    //   600: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/* 712:    */    //   603: invokevirtual 215	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:reset	()V
/* 713:    */    //   606: aload 6
/* 714:    */    //   608: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/* 715:    */    //   611: aload 7
/* 716:    */    //   613: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/* 717:    */    //   616: getfield 109	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexA	Z
/* 718:    */    //   619: putfield 109	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexA	Z
/* 719:    */    //   622: aload 6
/* 720:    */    //   624: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/* 721:    */    //   627: aload 7
/* 722:    */    //   629: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/* 723:    */    //   632: getfield 103	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexC	Z
/* 724:    */    //   635: putfield 106	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexB	Z
/* 725:    */    //   638: aload 6
/* 726:    */    //   640: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/* 727:    */    //   643: aload 7
/* 728:    */    //   645: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/* 729:    */    //   648: getfield 106	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexB	Z
/* 730:    */    //   651: putfield 98	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexD	Z
/* 731:    */    //   654: aload 6
/* 732:    */    //   656: aload 7
/* 733:    */    //   658: getfield 175	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:barycentricCoords	[F
/* 734:    */    //   661: iconst_0
/* 735:    */    //   662: faload
/* 736:    */    //   663: aload 7
/* 737:    */    //   665: getfield 175	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:barycentricCoords	[F
/* 738:    */    //   668: iconst_2
/* 739:    */    //   669: faload
/* 740:    */    //   670: fconst_0
/* 741:    */    //   671: aload 7
/* 742:    */    //   673: getfield 175	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:barycentricCoords	[F
/* 743:    */    //   676: iconst_1
/* 744:    */    //   677: faload
/* 745:    */    //   678: invokevirtual 136	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:setBarycentricCoordinates	(FFFF)V
/* 746:    */    //   681: iload 13
/* 747:    */    //   683: ifeq +148 -> 831
/* 748:    */    //   686: aload_0
/* 749:    */    //   687: aload_1
/* 750:    */    //   688: aload_3
/* 751:    */    //   689: aload 5
/* 752:    */    //   691: aload 4
/* 753:    */    //   693: aload 7
/* 754:    */    //   695: invokevirtual 171	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver:closestPtPointTriangle	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult;)Z
/* 755:    */    //   698: pop
/* 756:    */    //   699: aload 9
/* 757:    */    //   701: aload 7
/* 758:    */    //   703: getfield 218	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:closestPointOnSimplex	Ljavax/vecmath/Vector3f;
/* 759:    */    //   706: invokevirtual 89	javax/vecmath/Vector3f:set	(Ljavax/vecmath/Tuple3f;)V
/* 760:    */    //   709: aload 8
/* 761:    */    //   711: aload 9
/* 762:    */    //   713: aload_1
/* 763:    */    //   714: invokevirtual 132	javax/vecmath/Vector3f:sub	(Ljavax/vecmath/Tuple3f;Ljavax/vecmath/Tuple3f;)V
/* 764:    */    //   717: aload 8
/* 765:    */    //   719: aload 8
/* 766:    */    //   721: invokevirtual 150	javax/vecmath/Vector3f:dot	(Ljavax/vecmath/Vector3f;)F
/* 767:    */    //   724: fstore 15
/* 768:    */    //   726: fload 15
/* 769:    */    //   728: fload 14
/* 770:    */    //   730: fcmpg
/* 771:    */    //   731: ifge +100 -> 831
/* 772:    */    //   734: fload 15
/* 773:    */    //   736: fstore 14
/* 774:    */    //   738: aload 6
/* 775:    */    //   740: getfield 218	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:closestPointOnSimplex	Ljavax/vecmath/Vector3f;
/* 776:    */    //   743: aload 9
/* 777:    */    //   745: invokevirtual 89	javax/vecmath/Vector3f:set	(Ljavax/vecmath/Tuple3f;)V
/* 778:    */    //   748: aload 6
/* 779:    */    //   750: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/* 780:    */    //   753: invokevirtual 215	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:reset	()V
/* 781:    */    //   756: aload 6
/* 782:    */    //   758: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/* 783:    */    //   761: aload 7
/* 784:    */    //   763: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/* 785:    */    //   766: getfield 109	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexA	Z
/* 786:    */    //   769: putfield 106	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexB	Z
/* 787:    */    //   772: aload 6
/* 788:    */    //   774: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/* 789:    */    //   777: aload 7
/* 790:    */    //   779: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/* 791:    */    //   782: getfield 103	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexC	Z
/* 792:    */    //   785: putfield 103	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexC	Z
/* 793:    */    //   788: aload 6
/* 794:    */    //   790: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/* 795:    */    //   793: aload 7
/* 796:    */    //   795: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/* 797:    */    //   798: getfield 106	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexB	Z
/* 798:    */    //   801: putfield 98	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexD	Z
/* 799:    */    //   804: aload 6
/* 800:    */    //   806: fconst_0
/* 801:    */    //   807: aload 7
/* 802:    */    //   809: getfield 175	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:barycentricCoords	[F
/* 803:    */    //   812: iconst_0
/* 804:    */    //   813: faload
/* 805:    */    //   814: aload 7
/* 806:    */    //   816: getfield 175	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:barycentricCoords	[F
/* 807:    */    //   819: iconst_2
/* 808:    */    //   820: faload
/* 809:    */    //   821: aload 7
/* 810:    */    //   823: getfield 175	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:barycentricCoords	[F
/* 811:    */    //   826: iconst_1
/* 812:    */    //   827: faload
/* 813:    */    //   828: invokevirtual 136	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:setBarycentricCoordinates	(FFFF)V
/* 814:    */    //   831: aload 6
/* 815:    */    //   833: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/* 816:    */    //   836: getfield 109	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexA	Z
/* 817:    */    //   839: ifeq +56 -> 895
/* 818:    */    //   842: aload 6
/* 819:    */    //   844: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/* 820:    */    //   847: getfield 106	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexB	Z
/* 821:    */    //   850: ifeq +45 -> 895
/* 822:    */    //   853: aload 6
/* 823:    */    //   855: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/* 824:    */    //   858: getfield 103	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexC	Z
/* 825:    */    //   861: ifeq +34 -> 895
/* 826:    */    //   864: aload 6
/* 827:    */    //   866: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/* 828:    */    //   869: getfield 98	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexD	Z
/* 829:    */    //   872: ifeq +23 -> 895
/* 830:    */    //   875: iconst_1
/* 831:    */    //   876: istore 15
/* 832:    */    //   878: aload_0
/* 833:    */    //   879: getfield 53	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver:subsimplexResultsPool	Lcom/bulletphysics/util/ObjectPool;
/* 834:    */    //   882: aload 7
/* 835:    */    //   884: invokevirtual 258	com/bulletphysics/util/ObjectPool:release	(Ljava/lang/Object;)V
/* 836:    */    //   887: iload 15
/* 837:    */    //   889: aload 17
/* 838:    */    //   891: invokevirtual 193	com/bulletphysics/$Stack:pop$javax$vecmath$Vector3f	()V
/* 839:    */    //   894: ireturn
/* 840:    */    //   895: iconst_1
/* 841:    */    //   896: istore 15
/* 842:    */    //   898: aload_0
/* 843:    */    //   899: getfield 53	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver:subsimplexResultsPool	Lcom/bulletphysics/util/ObjectPool;
/* 844:    */    //   902: aload 7
/* 845:    */    //   904: invokevirtual 258	com/bulletphysics/util/ObjectPool:release	(Ljava/lang/Object;)V
/* 846:    */    //   907: iload 15
/* 847:    */    //   909: aload 17
/* 848:    */    //   911: invokevirtual 193	com/bulletphysics/$Stack:pop$javax$vecmath$Vector3f	()V
/* 849:    */    //   914: ireturn
/* 850:    */    //   915: astore 16
/* 851:    */    //   917: aload_0
/* 852:    */    //   918: getfield 53	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver:subsimplexResultsPool	Lcom/bulletphysics/util/ObjectPool;
/* 853:    */    //   921: aload 7
/* 854:    */    //   923: invokevirtual 258	com/bulletphysics/util/ObjectPool:release	(Ljava/lang/Object;)V
/* 855:    */    //   926: aload 16
/* 856:    */    //   928: athrow
/* 857:    */    //   929: aload 17
/* 858:    */    //   931: invokevirtual 193	com/bulletphysics/$Stack:pop$javax$vecmath$Vector3f	()V
/* 859:    */    //   934: athrow
/* 860:    */    // Line number table:
/* 861:    */    //   Java source line #430	-> byte code offset #10
/* 862:    */    //   Java source line #431	-> byte code offset #22
/* 863:    */    //   Java source line #433	-> byte code offset #27
/* 864:    */    //   Java source line #434	-> byte code offset #34
/* 865:    */    //   Java source line #437	-> byte code offset #41
/* 866:    */    //   Java source line #438	-> byte code offset #50
/* 867:    */    //   Java source line #439	-> byte code offset #58
/* 868:    */    //   Java source line #440	-> byte code offset #67
/* 869:    */    //   Java source line #441	-> byte code offset #76
/* 870:    */    //   Java source line #442	-> byte code offset #85
/* 871:    */    //   Java source line #444	-> byte code offset #94
/* 872:    */    //   Java source line #445	-> byte code offset #106
/* 873:    */    //   Java source line #446	-> byte code offset #118
/* 874:    */    //   Java source line #447	-> byte code offset #130
/* 875:    */    //   Java source line #449	-> byte code offset #142
/* 876:    */    //   Java source line #451	-> byte code offset #162
/* 877:    */    //   Java source line #452	-> byte code offset #168
/* 878:    */    //   Java source line #589	-> byte code offset #171
/* 879:    */    //   Java source line #455	-> byte code offset #188
/* 880:    */    //   Java source line #457	-> byte code offset #208
/* 881:    */    //   Java source line #589	-> byte code offset #211
/* 882:    */    //   Java source line #461	-> byte code offset #228
/* 883:    */    //   Java source line #463	-> byte code offset #233
/* 884:    */    //   Java source line #465	-> byte code offset #238
/* 885:    */    //   Java source line #466	-> byte code offset #250
/* 886:    */    //   Java source line #468	-> byte code offset #260
/* 887:    */    //   Java source line #469	-> byte code offset #268
/* 888:    */    //   Java source line #471	-> byte code offset #277
/* 889:    */    //   Java source line #472	-> byte code offset #285
/* 890:    */    //   Java source line #473	-> byte code offset #289
/* 891:    */    //   Java source line #475	-> byte code offset #299
/* 892:    */    //   Java source line #476	-> byte code offset #307
/* 893:    */    //   Java source line #477	-> byte code offset #323
/* 894:    */    //   Java source line #478	-> byte code offset #339
/* 895:    */    //   Java source line #479	-> byte code offset #355
/* 896:    */    //   Java source line #491	-> byte code offset #382
/* 897:    */    //   Java source line #493	-> byte code offset #387
/* 898:    */    //   Java source line #494	-> byte code offset #400
/* 899:    */    //   Java source line #497	-> byte code offset #410
/* 900:    */    //   Java source line #498	-> byte code offset #418
/* 901:    */    //   Java source line #499	-> byte code offset #427
/* 902:    */    //   Java source line #501	-> byte code offset #435
/* 903:    */    //   Java source line #502	-> byte code offset #439
/* 904:    */    //   Java source line #503	-> byte code offset #449
/* 905:    */    //   Java source line #504	-> byte code offset #457
/* 906:    */    //   Java source line #506	-> byte code offset #473
/* 907:    */    //   Java source line #507	-> byte code offset #489
/* 908:    */    //   Java source line #508	-> byte code offset #505
/* 909:    */    //   Java source line #520	-> byte code offset #532
/* 910:    */    //   Java source line #522	-> byte code offset #537
/* 911:    */    //   Java source line #523	-> byte code offset #549
/* 912:    */    //   Java source line #526	-> byte code offset #559
/* 913:    */    //   Java source line #527	-> byte code offset #567
/* 914:    */    //   Java source line #528	-> byte code offset #576
/* 915:    */    //   Java source line #530	-> byte code offset #584
/* 916:    */    //   Java source line #531	-> byte code offset #588
/* 917:    */    //   Java source line #532	-> byte code offset #598
/* 918:    */    //   Java source line #533	-> byte code offset #606
/* 919:    */    //   Java source line #534	-> byte code offset #622
/* 920:    */    //   Java source line #536	-> byte code offset #638
/* 921:    */    //   Java source line #537	-> byte code offset #654
/* 922:    */    //   Java source line #549	-> byte code offset #681
/* 923:    */    //   Java source line #551	-> byte code offset #686
/* 924:    */    //   Java source line #552	-> byte code offset #699
/* 925:    */    //   Java source line #554	-> byte code offset #709
/* 926:    */    //   Java source line #555	-> byte code offset #717
/* 927:    */    //   Java source line #556	-> byte code offset #726
/* 928:    */    //   Java source line #558	-> byte code offset #734
/* 929:    */    //   Java source line #559	-> byte code offset #738
/* 930:    */    //   Java source line #560	-> byte code offset #748
/* 931:    */    //   Java source line #562	-> byte code offset #756
/* 932:    */    //   Java source line #563	-> byte code offset #772
/* 933:    */    //   Java source line #564	-> byte code offset #788
/* 934:    */    //   Java source line #566	-> byte code offset #804
/* 935:    */    //   Java source line #578	-> byte code offset #831
/* 936:    */    //   Java source line #583	-> byte code offset #875
/* 937:    */    //   Java source line #589	-> byte code offset #878
/* 938:    */    //   Java source line #586	-> byte code offset #895
/* 939:    */    //   Java source line #589	-> byte code offset #898
/* 940:    */    // Local variable table:
/* 941:    */    //   start	length	slot	name	signature
/* 942:    */    //   10	919	0	this	VoronoiSimplexSolver
/* 943:    */    //   10	919	1	p	Vector3f
/* 944:    */    //   10	919	2	a	Vector3f
/* 945:    */    //   10	919	3	b	Vector3f
/* 946:    */    //   10	919	4	c	Vector3f
/* 947:    */    //   10	919	5	d	Vector3f
/* 948:    */    //   10	919	6	finalResult	SubSimplexClosestResult
/* 949:    */    //   20	902	7	tempResult	SubSimplexClosestResult
/* 950:    */    //   32	688	8	tmp	Vector3f
/* 951:    */    //   39	705	9	q	Vector3f
/* 952:    */    //   104	130	10	pointOutsideABC	int
/* 953:    */    //   116	267	11	pointOutsideACD	int
/* 954:    */    //   128	405	12	pointOutsideADB	int
/* 955:    */    //   140	542	13	pointOutsideBDC	int
/* 956:    */    //   169	52	14	bool	boolean
/* 957:    */    //   231	506	14	bestSqDist	float
/* 958:    */    //   275	11	15	sqDist	float
/* 959:    */    //   425	11	15	sqDist	float
/* 960:    */    //   574	11	15	sqDist	float
/* 961:    */    //   724	184	15	sqDist	float
/* 962:    */    //   915	12	16	localObject	Object
/* 963:    */    //   3	927	17	localStack	.Stack
/* 964:    */    // Exception table:
/* 965:    */    //   from	to	target	type
/* 966:    */    //   27	171	915	finally
/* 967:    */    //   188	211	915	finally
/* 968:    */    //   228	878	915	finally
/* 969:    */    //   895	898	915	finally
/* 970:    */    //   915	917	915	finally
/* 971:    */    //   5	929	929	finally
/* 972:    */  }
/* 973:    */  
/* 974:    */  public void reset()
/* 975:    */  {
/* 976:597 */    this.cachedValidClosest = false;
/* 977:598 */    this.numVertices = 0;
/* 978:599 */    this.needsUpdate = true;
/* 979:600 */    this.lastW.set(1.0E+030F, 1.0E+030F, 1.0E+030F);
/* 980:601 */    this.cachedBC.reset();
/* 981:    */  }
/* 982:    */  
/* 983:    */  public void addVertex(Vector3f w, Vector3f p, Vector3f q) {
/* 984:605 */    this.lastW.set(w);
/* 985:606 */    this.needsUpdate = true;
/* 986:    */    
/* 987:608 */    this.simplexVectorW[this.numVertices].set(w);
/* 988:609 */    this.simplexPointsP[this.numVertices].set(p);
/* 989:610 */    this.simplexPointsQ[this.numVertices].set(q);
/* 990:    */    
/* 991:612 */    this.numVertices += 1;
/* 992:    */  }
/* 993:    */  
/* 996:    */  public boolean closest(Vector3f v)
/* 997:    */  {
/* 998:619 */    boolean succes = updateClosestVectorAndPoints();
/* 999:620 */    v.set(this.cachedV);
/* 1000:621 */    return succes;
/* 1001:    */  }
/* 1002:    */  
/* 1003:    */  public float maxVertex() {
/* 1004:625 */    int numverts = numVertices();
/* 1005:626 */    float maxV = 0.0F;
/* 1006:627 */    for (int i = 0; i < numverts; i++) {
/* 1007:628 */      float curLen2 = this.simplexVectorW[i].lengthSquared();
/* 1008:629 */      if (maxV < curLen2) {
/* 1009:630 */        maxV = curLen2;
/* 1010:    */      }
/* 1011:    */    }
/* 1012:633 */    return maxV;
/* 1013:    */  }
/* 1014:    */  
/* 1015:    */  public boolean fullSimplex() {
/* 1016:637 */    return this.numVertices == 4;
/* 1017:    */  }
/* 1018:    */  
/* 1019:    */  public int getSimplex(Vector3f[] pBuf, Vector3f[] qBuf, Vector3f[] yBuf) {
/* 1020:641 */    for (int i = 0; i < numVertices(); i++) {
/* 1021:642 */      yBuf[i].set(this.simplexVectorW[i]);
/* 1022:643 */      pBuf[i].set(this.simplexPointsP[i]);
/* 1023:644 */      qBuf[i].set(this.simplexPointsQ[i]);
/* 1024:    */    }
/* 1025:646 */    return numVertices();
/* 1026:    */  }
/* 1027:    */  
/* 1028:    */  public boolean inSimplex(Vector3f w) {
/* 1029:650 */    boolean found = false;
/* 1030:651 */    int numverts = numVertices();
/* 1031:    */    
/* 1034:655 */    for (int i = 0; i < numverts; i++) {
/* 1035:656 */      if (this.simplexVectorW[i].equals(w)) {
/* 1036:657 */        found = true;
/* 1037:    */      }
/* 1038:    */    }
/* 1039:    */    
/* 1041:662 */    if (w.equals(this.lastW)) {
/* 1042:663 */      return true;
/* 1043:    */    }
/* 1044:    */    
/* 1045:666 */    return found;
/* 1046:    */  }
/* 1047:    */  
/* 1048:    */  public void backup_closest(Vector3f v) {
/* 1049:670 */    v.set(this.cachedV);
/* 1050:    */  }
/* 1051:    */  
/* 1052:    */  public boolean emptySimplex() {
/* 1053:674 */    return numVertices() == 0;
/* 1054:    */  }
/* 1055:    */  
/* 1056:    */  public void compute_points(Vector3f p1, Vector3f p2) {
/* 1057:678 */    updateClosestVectorAndPoints();
/* 1058:679 */    p1.set(this.cachedP1);
/* 1059:680 */    p2.set(this.cachedP2);
/* 1060:    */  }
/* 1061:    */  
/* 1062:    */  public int numVertices() {
/* 1063:684 */    return this.numVertices;
/* 1064:    */  }
/* 1065:    */  
/* 1066:    */  public static class UsageBitfield
/* 1067:    */  {
/* 1068:    */    public boolean usedVertexA;
/* 1069:    */    public boolean usedVertexB;
/* 1070:    */    public boolean usedVertexC;
/* 1071:    */    public boolean usedVertexD;
/* 1072:    */    
/* 1073:    */    public void reset()
/* 1074:    */    {
/* 1075:696 */      this.usedVertexA = false;
/* 1076:697 */      this.usedVertexB = false;
/* 1077:698 */      this.usedVertexC = false;
/* 1078:699 */      this.usedVertexD = false;
/* 1079:    */    }
/* 1080:    */  }
/* 1081:    */  
/* 1082:    */  public static class SubSimplexClosestResult {
/* 1083:704 */    public final Vector3f closestPointOnSimplex = new Vector3f();
/* 1084:    */    
/* 1087:708 */    public final VoronoiSimplexSolver.UsageBitfield usedVertices = new VoronoiSimplexSolver.UsageBitfield();
/* 1088:709 */    public final float[] barycentricCoords = new float[4];
/* 1089:    */    public boolean degenerate;
/* 1090:    */    
/* 1091:    */    public void reset() {
/* 1092:713 */      this.degenerate = false;
/* 1093:714 */      setBarycentricCoordinates(0.0F, 0.0F, 0.0F, 0.0F);
/* 1094:715 */      this.usedVertices.reset();
/* 1095:    */    }
/* 1096:    */    
/* 1097:    */    public boolean isValid() {
/* 1098:719 */      boolean valid = (this.barycentricCoords[0] >= 0.0F) && (this.barycentricCoords[1] >= 0.0F) && (this.barycentricCoords[2] >= 0.0F) && (this.barycentricCoords[3] >= 0.0F);
/* 1099:    */      
/* 1102:723 */      return valid;
/* 1103:    */    }
/* 1104:    */    
/* 1105:    */    public void setBarycentricCoordinates(float a, float b, float c, float d) {
/* 1106:727 */      this.barycentricCoords[0] = a;
/* 1107:728 */      this.barycentricCoords[1] = b;
/* 1108:729 */      this.barycentricCoords[2] = c;
/* 1109:730 */      this.barycentricCoords[3] = d;
/* 1110:    */    }
/* 1111:    */  }
/* 1112:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.narrowphase.VoronoiSimplexSolver
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */