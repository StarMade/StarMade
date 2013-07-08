/*   1:    */package org.schema.game.common.data.physics;
/*   2:    */
/*   3:    */import com.bulletphysics.collision.narrowphase.SimplexSolverInterface;
/*   4:    */import com.bulletphysics.linearmath.VectorUtil;
/*   5:    */import com.bulletphysics.util.ObjectPool;
/*   6:    */import javax.vecmath.Vector3f;
/*   7:    */
/*  38:    */public class VoronoiSimplexSolverExt
/*  39:    */  extends SimplexSolverInterface
/*  40:    */{
/*  41: 41 */  private static ThreadLocal threadLocal = new VoronoiSimplexSolverExt.1();
/*  42:    */  protected final ObjectPool subsimplexResultsPool;
/*  43:    */  private static final int VORONOI_SIMPLEX_MAX_VERTS = 5;
/*  44:    */  private static final int VERTA = 0;
/*  45:    */  private static final int VERTB = 1;
/*  46:    */  private static final int VERTC = 2;
/*  47:    */  private static final int VERTD = 3;
/*  48:    */  private final VoronoiSimplexSolverVariables v;
/*  49:    */  public int numVertices;
/*  50:    */  public final Vector3f[] simplexVectorW;
/*  51:    */  
/*  52: 52 */  public VoronoiSimplexSolverExt() { this.subsimplexResultsPool = ObjectPool.get(VoronoiSimplexSolverExt.SubSimplexClosestResult.class);
/*  53:    */    
/*  65: 65 */    this.simplexVectorW = new Vector3f[5];
/*  66: 66 */    this.simplexPointsP = new Vector3f[5];
/*  67: 67 */    this.simplexPointsQ = new Vector3f[5];
/*  68:    */    
/*  69: 69 */    this.cachedP1 = new Vector3f();
/*  70: 70 */    this.cachedP2 = new Vector3f();
/*  71: 71 */    this.cachedV = new Vector3f();
/*  72: 72 */    this.lastW = new Vector3f();
/*  73:    */    
/*  75: 75 */    this.cachedBC = new VoronoiSimplexSolverExt.SubSimplexClosestResult();
/*  76:    */    
/*  80: 80 */    for (int i = 0; i < 5; i++) {
/*  81: 81 */      this.simplexVectorW[i] = new Vector3f();
/*  82: 82 */      this.simplexPointsP[i] = new Vector3f();
/*  83: 83 */      this.simplexPointsQ[i] = new Vector3f();
/*  84:    */    }
/*  85: 48 */    this.v = ((VoronoiSimplexSolverVariables)threadLocal.get());
/*  86:    */  }
/*  87:    */  
/*  91:    */  public final Vector3f[] simplexPointsP;
/*  92:    */  
/*  95:    */  public final Vector3f[] simplexPointsQ;
/*  96:    */  
/*  99:    */  public final Vector3f cachedP1;
/* 100:    */  
/* 103:    */  public final Vector3f cachedP2;
/* 104:    */  
/* 107:    */  public final Vector3f cachedV;
/* 108:    */  
/* 111:    */  public final Vector3f lastW;
/* 112:    */  
/* 114:    */  public boolean cachedValidClosest;
/* 115:    */  
/* 117:    */  public final VoronoiSimplexSolverExt.SubSimplexClosestResult cachedBC;
/* 118:    */  
/* 120:    */  public boolean needsUpdate;
/* 121:    */  
/* 123:    */  public void removeVertex(int paramInt)
/* 124:    */  {
/* 125: 88 */    assert (this.numVertices > 0);
/* 126: 89 */    this.numVertices -= 1;
/* 127: 90 */    this.simplexVectorW[paramInt].set(this.simplexVectorW[this.numVertices]);
/* 128: 91 */    this.simplexPointsP[paramInt].set(this.simplexPointsP[this.numVertices]);
/* 129: 92 */    this.simplexPointsQ[paramInt].set(this.simplexPointsQ[this.numVertices]);
/* 130:    */  }
/* 131:    */  
/* 132:    */  public void reduceVertices(VoronoiSimplexSolverExt.UsageBitfield paramUsageBitfield) {
/* 133: 96 */    if ((numVertices() >= 4) && (!paramUsageBitfield.usedVertexD)) {
/* 134: 97 */      removeVertex(3);
/* 135:    */    }
/* 136: 99 */    if ((numVertices() >= 3) && (!paramUsageBitfield.usedVertexC)) {
/* 137:100 */      removeVertex(2);
/* 138:    */    }
/* 139:102 */    if ((numVertices() >= 2) && (!paramUsageBitfield.usedVertexB)) {
/* 140:103 */      removeVertex(1);
/* 141:    */    }
/* 142:105 */    if ((numVertices() > 0) && (!paramUsageBitfield.usedVertexA))
/* 143:106 */      removeVertex(0);
/* 144:    */  }
/* 145:    */  
/* 146:    */  public boolean updateClosestVectorAndPoints() {
/* 147:110 */    if (this.needsUpdate)
/* 148:    */    {
/* 149:112 */      this.cachedBC.reset();
/* 150:    */      
/* 151:114 */      this.needsUpdate = false;
/* 152:    */      Vector3f localVector3f1;
/* 153:116 */      Vector3f localVector3f2; Vector3f localVector3f3; Vector3f localVector3f4; Vector3f localVector3f5; Vector3f localVector3f6; Vector3f localVector3f7; switch (numVertices())
/* 154:    */      {
/* 155:    */      case 0: 
/* 156:119 */        this.cachedValidClosest = false;
/* 157:120 */        break;
/* 158:    */      
/* 159:    */      case 1: 
/* 160:123 */        this.cachedP1.set(this.simplexPointsP[0]);
/* 161:124 */        this.cachedP2.set(this.simplexPointsQ[0]);
/* 162:125 */        this.cachedV.sub(this.cachedP1, this.cachedP2);
/* 163:126 */        this.cachedBC.reset();
/* 164:127 */        this.cachedBC.setBarycentricCoordinates(1.0F, 0.0F, 0.0F, 0.0F);
/* 165:128 */        this.cachedValidClosest = this.cachedBC.isValid();
/* 166:129 */        break;
/* 167:    */      
/* 169:    */      case 2: 
/* 170:133 */        localVector3f1 = this.v.tmp;
/* 171:    */        
/* 173:136 */        localVector3f2 = this.simplexVectorW[0];
/* 174:137 */        localVector3f3 = this.simplexVectorW[1];
/* 175:138 */        localVector3f4 = this.v.nearest;
/* 176:    */        
/* 177:140 */        (
/* 178:141 */          localVector3f5 = this.v.p).set(0.0F, 0.0F, 0.0F);
/* 179:142 */        (
/* 180:143 */          localVector3f6 = this.v.diff).sub(localVector3f5, localVector3f2);
/* 181:    */        
/* 182:145 */        (
/* 183:146 */          localVector3f7 = this.v.v).sub(localVector3f3, localVector3f2);
/* 184:    */        
/* 185:    */        float f1;
/* 186:    */        
/* 187:150 */        if ((f1 = localVector3f7.dot(localVector3f6)) > 0.0F) {
/* 188:151 */          float f2 = localVector3f7.dot(localVector3f7);
/* 189:152 */          if (f1 < f2) {
/* 190:153 */            f1 /= f2;
/* 191:154 */            localVector3f1.scale(f1, localVector3f7);
/* 192:155 */            localVector3f6.sub(localVector3f1);
/* 193:156 */            this.cachedBC.usedVertices.usedVertexA = true;
/* 194:157 */            this.cachedBC.usedVertices.usedVertexB = true;
/* 195:    */          } else {
/* 196:159 */            f1 = 1.0F;
/* 197:160 */            localVector3f6.sub(localVector3f7);
/* 198:    */            
/* 199:162 */            this.cachedBC.usedVertices.usedVertexB = true;
/* 200:    */          }
/* 201:    */        }
/* 202:    */        else {
/* 203:166 */          f1 = 0.0F;
/* 204:    */          
/* 205:168 */          this.cachedBC.usedVertices.usedVertexA = true;
/* 206:    */        }
/* 207:170 */        this.cachedBC.setBarycentricCoordinates(1.0F - f1, f1, 0.0F, 0.0F);
/* 208:    */        
/* 209:172 */        localVector3f1.scale(f1, localVector3f7);
/* 210:173 */        localVector3f4.add(localVector3f2, localVector3f1);
/* 211:    */        
/* 212:175 */        localVector3f1.sub(this.simplexPointsP[1], this.simplexPointsP[0]);
/* 213:176 */        localVector3f1.scale(f1);
/* 214:177 */        this.cachedP1.add(this.simplexPointsP[0], localVector3f1);
/* 215:    */        
/* 216:179 */        localVector3f1.sub(this.simplexPointsQ[1], this.simplexPointsQ[0]);
/* 217:180 */        localVector3f1.scale(f1);
/* 218:181 */        this.cachedP2.add(this.simplexPointsQ[0], localVector3f1);
/* 219:    */        
/* 220:183 */        this.cachedV.sub(this.cachedP1, this.cachedP2);
/* 221:    */        
/* 222:185 */        reduceVertices(this.cachedBC.usedVertices);
/* 223:    */        
/* 224:187 */        this.cachedValidClosest = this.cachedBC.isValid();
/* 225:188 */        break;
/* 226:    */      
/* 228:    */      case 3: 
/* 229:192 */        localVector3f1 = this.v.tmp1;
/* 230:193 */        localVector3f2 = this.v.tmp2;
/* 231:194 */        localVector3f3 = this.v.tmp3;
/* 232:    */        
/* 234:197 */        (
/* 235:198 */          localVector3f4 = this.v.p).set(0.0F, 0.0F, 0.0F);
/* 236:    */        
/* 237:200 */        localVector3f5 = this.simplexVectorW[0];
/* 238:201 */        localVector3f6 = this.simplexVectorW[1];
/* 239:202 */        localVector3f7 = this.simplexVectorW[2];
/* 240:    */        
/* 241:204 */        closestPtPointTriangle(localVector3f4, localVector3f5, localVector3f6, localVector3f7, this.cachedBC);
/* 242:    */        
/* 243:206 */        localVector3f1.scale(this.cachedBC.barycentricCoords[0], this.simplexPointsP[0]);
/* 244:207 */        localVector3f2.scale(this.cachedBC.barycentricCoords[1], this.simplexPointsP[1]);
/* 245:208 */        localVector3f3.scale(this.cachedBC.barycentricCoords[2], this.simplexPointsP[2]);
/* 246:209 */        VectorUtil.add(this.cachedP1, localVector3f1, localVector3f2, localVector3f3);
/* 247:    */        
/* 248:211 */        localVector3f1.scale(this.cachedBC.barycentricCoords[0], this.simplexPointsQ[0]);
/* 249:212 */        localVector3f2.scale(this.cachedBC.barycentricCoords[1], this.simplexPointsQ[1]);
/* 250:213 */        localVector3f3.scale(this.cachedBC.barycentricCoords[2], this.simplexPointsQ[2]);
/* 251:214 */        VectorUtil.add(this.cachedP2, localVector3f1, localVector3f2, localVector3f3);
/* 252:    */        
/* 253:216 */        this.cachedV.sub(this.cachedP1, this.cachedP2);
/* 254:    */        
/* 255:218 */        reduceVertices(this.cachedBC.usedVertices);
/* 256:219 */        this.cachedValidClosest = this.cachedBC.isValid();
/* 257:    */        
/* 258:221 */        break;
/* 259:    */      
/* 261:    */      case 4: 
/* 262:225 */        localVector3f1 = this.v.tmp1;
/* 263:226 */        localVector3f2 = this.v.tmp2;
/* 264:227 */        localVector3f3 = this.v.tmp3;
/* 265:228 */        localVector3f4 = this.v.tmp4;
/* 266:    */        
/* 267:230 */        (
/* 268:231 */          localVector3f5 = this.v.p).set(0.0F, 0.0F, 0.0F);
/* 269:    */        
/* 270:233 */        localVector3f6 = this.simplexVectorW[0];
/* 271:234 */        localVector3f7 = this.simplexVectorW[1];
/* 272:235 */        Vector3f localVector3f8 = this.simplexVectorW[2];
/* 273:236 */        Vector3f localVector3f9 = this.simplexVectorW[3];
/* 274:    */        
/* 277:240 */        if (closestPtPointTetrahedron(localVector3f5, localVector3f6, localVector3f7, localVector3f8, localVector3f9, this.cachedBC))
/* 278:    */        {
/* 279:242 */          localVector3f1.scale(this.cachedBC.barycentricCoords[0], this.simplexPointsP[0]);
/* 280:243 */          localVector3f2.scale(this.cachedBC.barycentricCoords[1], this.simplexPointsP[1]);
/* 281:244 */          localVector3f3.scale(this.cachedBC.barycentricCoords[2], this.simplexPointsP[2]);
/* 282:245 */          localVector3f4.scale(this.cachedBC.barycentricCoords[3], this.simplexPointsP[3]);
/* 283:246 */          VectorUtil.add(this.cachedP1, localVector3f1, localVector3f2, localVector3f3, localVector3f4);
/* 284:    */          
/* 285:248 */          localVector3f1.scale(this.cachedBC.barycentricCoords[0], this.simplexPointsQ[0]);
/* 286:249 */          localVector3f2.scale(this.cachedBC.barycentricCoords[1], this.simplexPointsQ[1]);
/* 287:250 */          localVector3f3.scale(this.cachedBC.barycentricCoords[2], this.simplexPointsQ[2]);
/* 288:251 */          localVector3f4.scale(this.cachedBC.barycentricCoords[3], this.simplexPointsQ[3]);
/* 289:252 */          VectorUtil.add(this.cachedP2, localVector3f1, localVector3f2, localVector3f3, localVector3f4);
/* 290:    */          
/* 291:254 */          this.cachedV.sub(this.cachedP1, this.cachedP2);
/* 292:255 */          reduceVertices(this.cachedBC.usedVertices);
/* 294:    */        }
/* 295:    */        else
/* 296:    */        {
/* 297:260 */          if (this.cachedBC.degenerate)
/* 298:    */            break;
/* 299:262 */          this.cachedValidClosest = true;
/* 300:    */          
/* 304:267 */          this.cachedV.set(0.0F, 0.0F, 0.0F);
/* 305:    */          
/* 306:    */          break label1084;
/* 307:    */        }
/* 308:    */        
/* 309:272 */        this.cachedValidClosest = this.cachedBC.isValid();
/* 310:    */        
/* 312:275 */        break;
/* 313:    */      }
/* 314:    */      
/* 315:    */      
/* 316:279 */      this.cachedValidClosest = false;
/* 317:    */    }
/* 318:    */    
/* 319:    */    label1084:
/* 320:    */    
/* 321:284 */    return this.cachedValidClosest;
/* 322:    */  }
/* 323:    */  
/* 324:    */  public boolean closestPtPointTriangle(Vector3f paramVector3f1, Vector3f paramVector3f2, Vector3f paramVector3f3, Vector3f paramVector3f4, VoronoiSimplexSolverExt.SubSimplexClosestResult paramSubSimplexClosestResult)
/* 325:    */  {
/* 326:289 */    paramSubSimplexClosestResult.usedVertices.reset();
/* 327:    */    
/* 328:    */    Vector3f localVector3f1;
/* 329:    */    
/* 330:293 */    (localVector3f1 = this.v.ab).sub(paramVector3f3, paramVector3f2);
/* 331:    */    
/* 332:    */    Vector3f localVector3f2;
/* 333:296 */    (localVector3f2 = this.v.ac).sub(paramVector3f4, paramVector3f2);
/* 334:    */    
/* 335:    */    Vector3f localVector3f3;
/* 336:299 */    (localVector3f3 = this.v.ap).sub(paramVector3f1, paramVector3f2);
/* 337:    */    
/* 338:301 */    float f3 = localVector3f1.dot(localVector3f3);
/* 339:302 */    float f1 = localVector3f2.dot(localVector3f3);
/* 340:    */    
/* 341:304 */    if ((f3 <= 0.0F) && (f1 <= 0.0F))
/* 342:    */    {
/* 343:306 */      paramSubSimplexClosestResult.closestPointOnSimplex.set(paramVector3f2);
/* 344:307 */      paramSubSimplexClosestResult.usedVertices.usedVertexA = true;
/* 345:308 */      paramSubSimplexClosestResult.setBarycentricCoordinates(1.0F, 0.0F, 0.0F, 0.0F);
/* 346:309 */      return true;
/* 347:    */    }
/* 348:    */    
/* 349:    */    Vector3f localVector3f5;
/* 350:    */    
/* 351:314 */    (localVector3f5 = this.v.bp).sub(paramVector3f1, paramVector3f3);
/* 352:    */    
/* 353:316 */    float f5 = localVector3f1.dot(localVector3f5);
/* 354:317 */    float f4 = localVector3f2.dot(localVector3f5);
/* 355:    */    
/* 356:319 */    if ((f5 >= 0.0F) && (f4 <= f5))
/* 357:    */    {
/* 358:321 */      paramSubSimplexClosestResult.closestPointOnSimplex.set(paramVector3f3);
/* 359:322 */      paramSubSimplexClosestResult.usedVertices.usedVertexB = true;
/* 360:323 */      paramSubSimplexClosestResult.setBarycentricCoordinates(0.0F, 1.0F, 0.0F, 0.0F);
/* 361:    */      
/* 362:325 */      return true;
/* 363:    */    }
/* 364:    */    
/* 365:    */    float f6;
/* 366:    */    
/* 367:330 */    if (((f6 = f3 * f4 - f5 * f1) <= 0.0F) && (f3 >= 0.0F) && (f5 <= 0.0F)) {
/* 368:331 */      float f7 = f3 / (f3 - f5);
/* 369:332 */      paramSubSimplexClosestResult.closestPointOnSimplex.scaleAdd(f7, localVector3f1, paramVector3f2);
/* 370:333 */      paramSubSimplexClosestResult.usedVertices.usedVertexA = true;
/* 371:334 */      paramSubSimplexClosestResult.usedVertices.usedVertexB = true;
/* 372:335 */      paramSubSimplexClosestResult.setBarycentricCoordinates(1.0F - f7, f7, 0.0F, 0.0F);
/* 373:336 */      return true;
/* 374:    */    }
/* 375:    */    
/* 377:    */    Vector3f localVector3f6;
/* 378:    */    
/* 379:342 */    (localVector3f6 = this.v.cp).sub(paramVector3f1, paramVector3f4);
/* 380:    */    
/* 381:344 */    paramVector3f1 = localVector3f1.dot(localVector3f6);
/* 382:    */    
/* 383:    */    float f8;
/* 384:347 */    if (((f8 = localVector3f2.dot(localVector3f6)) >= 0.0F) && (paramVector3f1 <= f8))
/* 385:    */    {
/* 386:349 */      paramSubSimplexClosestResult.closestPointOnSimplex.set(paramVector3f4);
/* 387:350 */      paramSubSimplexClosestResult.usedVertices.usedVertexC = true;
/* 388:351 */      paramSubSimplexClosestResult.setBarycentricCoordinates(0.0F, 0.0F, 1.0F, 0.0F);
/* 389:352 */      return true;
/* 390:    */    }
/* 391:    */    
/* 394:357 */    if (((f3 = paramVector3f1 * f1 - f3 * f8) <= 0.0F) && (f1 >= 0.0F) && (f8 <= 0.0F)) {
/* 395:358 */      f1 /= (f1 - f8);
/* 396:359 */      paramSubSimplexClosestResult.closestPointOnSimplex.scaleAdd(f1, localVector3f2, paramVector3f2);
/* 397:360 */      paramSubSimplexClosestResult.usedVertices.usedVertexA = true;
/* 398:361 */      paramSubSimplexClosestResult.usedVertices.usedVertexC = true;
/* 399:362 */      paramSubSimplexClosestResult.setBarycentricCoordinates(1.0F - f1, 0.0F, f1, 0.0F);
/* 400:363 */      return true;
/* 401:    */    }
/* 402:    */    
/* 404:    */    Vector3f localVector3f4;
/* 405:    */    
/* 406:369 */    if (((f1 = f5 * f8 - paramVector3f1 * f4) <= 0.0F) && (f4 - f5 >= 0.0F) && (paramVector3f1 - f8 >= 0.0F)) {
/* 407:370 */      paramVector3f1 = (f4 - f5) / (f4 - f5 + (paramVector3f1 - f8));
/* 408:    */      
/* 409:372 */      (
/* 410:373 */        localVector3f4 = this.v.tmptmp).sub(paramVector3f4, paramVector3f3);
/* 411:374 */      paramSubSimplexClosestResult.closestPointOnSimplex.scaleAdd(paramVector3f1, localVector3f4, paramVector3f3);
/* 412:    */      
/* 413:376 */      paramSubSimplexClosestResult.usedVertices.usedVertexB = true;
/* 414:377 */      paramSubSimplexClosestResult.usedVertices.usedVertexC = true;
/* 415:378 */      paramSubSimplexClosestResult.setBarycentricCoordinates(0.0F, 1.0F - paramVector3f1, paramVector3f1, 0.0F);
/* 416:379 */      return true;
/* 417:    */    }
/* 418:    */    
/* 421:384 */    paramVector3f1 = 1.0F / (localVector3f4 + f3 + f6);
/* 422:385 */    float f2 = f3 * paramVector3f1;
/* 423:386 */    paramVector3f1 = f6 * paramVector3f1;
/* 424:    */    
/* 425:388 */    paramVector3f3 = this.v.tmptmp1;
/* 426:389 */    paramVector3f4 = this.v.tmptmp2;
/* 427:    */    
/* 428:391 */    paramVector3f3.scale(f2, localVector3f1);
/* 429:392 */    paramVector3f4.scale(paramVector3f1, localVector3f2);
/* 430:393 */    VectorUtil.add(paramSubSimplexClosestResult.closestPointOnSimplex, paramVector3f2, paramVector3f3, paramVector3f4);
/* 431:394 */    paramSubSimplexClosestResult.usedVertices.usedVertexA = true;
/* 432:395 */    paramSubSimplexClosestResult.usedVertices.usedVertexB = true;
/* 433:396 */    paramSubSimplexClosestResult.usedVertices.usedVertexC = true;
/* 434:397 */    paramSubSimplexClosestResult.setBarycentricCoordinates(1.0F - f2 - paramVector3f1, f2, paramVector3f1, 0.0F);
/* 435:    */    
/* 436:399 */    return true;
/* 437:    */  }
/* 438:    */  
/* 442:    */  public static int pointOutsideOfPlane(Vector3f paramVector3f1, Vector3f paramVector3f2, Vector3f paramVector3f3, Vector3f paramVector3f4, Vector3f paramVector3f5, VoronoiSimplexSolverVariables paramVoronoiSimplexSolverVariables)
/* 443:    */  {
/* 444:407 */    Vector3f localVector3f = paramVoronoiSimplexSolverVariables.tmpTmpTmp;
/* 445:    */    
/* 446:409 */    (
/* 447:410 */      paramVoronoiSimplexSolverVariables = paramVoronoiSimplexSolverVariables.normal).sub(paramVector3f3, paramVector3f2);
/* 448:411 */    localVector3f.sub(paramVector3f4, paramVector3f2);
/* 449:412 */    paramVoronoiSimplexSolverVariables.cross(paramVoronoiSimplexSolverVariables, localVector3f);
/* 450:    */    
/* 451:414 */    localVector3f.sub(paramVector3f1, paramVector3f2);
/* 452:415 */    paramVector3f1 = localVector3f.dot(paramVoronoiSimplexSolverVariables);
/* 453:    */    
/* 454:417 */    localVector3f.sub(paramVector3f5, paramVector3f2); float 
/* 455:418 */      tmp66_63 = localVector3f.dot(paramVoronoiSimplexSolverVariables);
/* 456:    */    
/* 465:427 */    if (tmp66_63 * (paramVector3f2 = tmp66_63) < 9.999999E-009F)
/* 466:    */    {
/* 468:430 */      return -1;
/* 469:    */    }
/* 470:    */    
/* 474:436 */    if (paramVector3f1 * paramVector3f2 < 0.0F) return 1; return 0;
/* 475:    */  }
/* 476:    */  
/* 477:    */  public boolean closestPtPointTetrahedron(Vector3f paramVector3f1, Vector3f paramVector3f2, Vector3f paramVector3f3, Vector3f paramVector3f4, Vector3f paramVector3f5, VoronoiSimplexSolverExt.SubSimplexClosestResult paramSubSimplexClosestResult)
/* 478:    */  {
/* 479:    */    VoronoiSimplexSolverExt.SubSimplexClosestResult localSubSimplexClosestResult;
/* 480:442 */    (localSubSimplexClosestResult = (VoronoiSimplexSolverExt.SubSimplexClosestResult)this.subsimplexResultsPool.get()).reset();
/* 481:    */    try {
/* 482:444 */      Vector3f localVector3f1 = this.v.tmptmptmptmp;
/* 483:445 */      Vector3f localVector3f2 = this.v.q;
/* 484:    */      
/* 486:448 */      paramSubSimplexClosestResult.closestPointOnSimplex.set(paramVector3f1);
/* 487:449 */      paramSubSimplexClosestResult.usedVertices.reset();
/* 488:450 */      paramSubSimplexClosestResult.usedVertices.usedVertexA = true;
/* 489:451 */      paramSubSimplexClosestResult.usedVertices.usedVertexB = true;
/* 490:452 */      paramSubSimplexClosestResult.usedVertices.usedVertexC = true;
/* 491:453 */      paramSubSimplexClosestResult.usedVertices.usedVertexD = true;
/* 492:    */      
/* 493:455 */      int i = pointOutsideOfPlane(paramVector3f1, paramVector3f2, paramVector3f3, paramVector3f4, paramVector3f5, this.v);
/* 494:456 */      int j = pointOutsideOfPlane(paramVector3f1, paramVector3f2, paramVector3f4, paramVector3f5, paramVector3f3, this.v);
/* 495:457 */      int k = pointOutsideOfPlane(paramVector3f1, paramVector3f2, paramVector3f5, paramVector3f3, paramVector3f4, this.v);
/* 496:458 */      int m = pointOutsideOfPlane(paramVector3f1, paramVector3f3, paramVector3f5, paramVector3f4, paramVector3f2, this.v);
/* 497:    */      
/* 498:460 */      if ((i < 0) || (j < 0) || (k < 0) || (m < 0))
/* 499:    */      {
/* 500:462 */        paramSubSimplexClosestResult.degenerate = true;
/* 501:463 */        return false;
/* 502:    */      }
/* 503:    */      
/* 504:466 */      if ((i == 0) && (j == 0) && (k == 0) && (m == 0))
/* 505:    */      {
/* 506:468 */        return false;
/* 507:    */      }
/* 508:    */      
/* 510:472 */      float f2 = 3.4028235E+38F;
/* 511:    */      float f1;
/* 512:474 */      if (i != 0)
/* 513:    */      {
/* 514:476 */        closestPtPointTriangle(paramVector3f1, paramVector3f2, paramVector3f3, paramVector3f4, localSubSimplexClosestResult);
/* 515:477 */        localVector3f2.set(localSubSimplexClosestResult.closestPointOnSimplex);
/* 516:    */        
/* 517:479 */        localVector3f1.sub(localVector3f2, paramVector3f1);
/* 518:    */        
/* 520:482 */        if ((f1 = localVector3f1.dot(localVector3f1)) < 3.4028235E+38F) {
/* 521:483 */          f2 = f1;
/* 522:484 */          paramSubSimplexClosestResult.closestPointOnSimplex.set(localVector3f2);
/* 523:    */          
/* 524:486 */          paramSubSimplexClosestResult.usedVertices.reset();
/* 525:487 */          paramSubSimplexClosestResult.usedVertices.usedVertexA = localSubSimplexClosestResult.usedVertices.usedVertexA;
/* 526:488 */          paramSubSimplexClosestResult.usedVertices.usedVertexB = localSubSimplexClosestResult.usedVertices.usedVertexB;
/* 527:489 */          paramSubSimplexClosestResult.usedVertices.usedVertexC = localSubSimplexClosestResult.usedVertices.usedVertexC;
/* 528:490 */          paramSubSimplexClosestResult.setBarycentricCoordinates(localSubSimplexClosestResult.barycentricCoords[0], localSubSimplexClosestResult.barycentricCoords[1], localSubSimplexClosestResult.barycentricCoords[2], 0.0F);
/* 529:    */        }
/* 530:    */      }
/* 531:    */      
/* 540:502 */      if (j != 0)
/* 541:    */      {
/* 542:504 */        closestPtPointTriangle(paramVector3f1, paramVector3f2, paramVector3f4, paramVector3f5, localSubSimplexClosestResult);
/* 543:505 */        localVector3f2.set(localSubSimplexClosestResult.closestPointOnSimplex);
/* 544:    */        
/* 546:508 */        localVector3f1.sub(localVector3f2, paramVector3f1);
/* 547:    */        
/* 548:510 */        if ((f1 = localVector3f1.dot(localVector3f1)) < f2)
/* 549:    */        {
/* 550:512 */          f2 = f1;
/* 551:513 */          paramSubSimplexClosestResult.closestPointOnSimplex.set(localVector3f2);
/* 552:514 */          paramSubSimplexClosestResult.usedVertices.reset();
/* 553:515 */          paramSubSimplexClosestResult.usedVertices.usedVertexA = localSubSimplexClosestResult.usedVertices.usedVertexA;
/* 554:    */          
/* 555:517 */          paramSubSimplexClosestResult.usedVertices.usedVertexC = localSubSimplexClosestResult.usedVertices.usedVertexB;
/* 556:518 */          paramSubSimplexClosestResult.usedVertices.usedVertexD = localSubSimplexClosestResult.usedVertices.usedVertexC;
/* 557:519 */          paramSubSimplexClosestResult.setBarycentricCoordinates(localSubSimplexClosestResult.barycentricCoords[0], 0.0F, localSubSimplexClosestResult.barycentricCoords[1], localSubSimplexClosestResult.barycentricCoords[2]);
/* 558:    */        }
/* 559:    */      }
/* 560:    */      
/* 569:531 */      if (k != 0)
/* 570:    */      {
/* 571:533 */        closestPtPointTriangle(paramVector3f1, paramVector3f2, paramVector3f5, paramVector3f3, localSubSimplexClosestResult);
/* 572:534 */        localVector3f2.set(localSubSimplexClosestResult.closestPointOnSimplex);
/* 573:    */        
/* 575:537 */        localVector3f1.sub(localVector3f2, paramVector3f1);
/* 576:    */        
/* 577:539 */        if ((f1 = localVector3f1.dot(localVector3f1)) < f2)
/* 578:    */        {
/* 579:541 */          f2 = f1;
/* 580:542 */          paramSubSimplexClosestResult.closestPointOnSimplex.set(localVector3f2);
/* 581:543 */          paramSubSimplexClosestResult.usedVertices.reset();
/* 582:544 */          paramSubSimplexClosestResult.usedVertices.usedVertexA = localSubSimplexClosestResult.usedVertices.usedVertexA;
/* 583:545 */          paramSubSimplexClosestResult.usedVertices.usedVertexB = localSubSimplexClosestResult.usedVertices.usedVertexC;
/* 584:    */          
/* 585:547 */          paramSubSimplexClosestResult.usedVertices.usedVertexD = localSubSimplexClosestResult.usedVertices.usedVertexB;
/* 586:548 */          paramSubSimplexClosestResult.setBarycentricCoordinates(localSubSimplexClosestResult.barycentricCoords[0], localSubSimplexClosestResult.barycentricCoords[2], 0.0F, localSubSimplexClosestResult.barycentricCoords[1]);
/* 587:    */        }
/* 588:    */      }
/* 589:    */      
/* 598:560 */      if (m != 0)
/* 599:    */      {
/* 600:562 */        closestPtPointTriangle(paramVector3f1, paramVector3f3, paramVector3f5, paramVector3f4, localSubSimplexClosestResult);
/* 601:563 */        localVector3f2.set(localSubSimplexClosestResult.closestPointOnSimplex);
/* 602:    */        
/* 603:565 */        localVector3f1.sub(localVector3f2, paramVector3f1);
/* 604:    */        
/* 605:567 */        if (localVector3f1.dot(localVector3f1) < f2)
/* 606:    */        {
/* 607:569 */          paramSubSimplexClosestResult.closestPointOnSimplex.set(localVector3f2);
/* 608:    */          
/* 609:571 */          paramSubSimplexClosestResult.usedVertices.reset();
/* 610:    */          
/* 611:573 */          paramSubSimplexClosestResult.usedVertices.usedVertexB = localSubSimplexClosestResult.usedVertices.usedVertexA;
/* 612:574 */          paramSubSimplexClosestResult.usedVertices.usedVertexC = localSubSimplexClosestResult.usedVertices.usedVertexC;
/* 613:575 */          paramSubSimplexClosestResult.usedVertices.usedVertexD = localSubSimplexClosestResult.usedVertices.usedVertexB;
/* 614:    */          
/* 615:577 */          paramSubSimplexClosestResult.setBarycentricCoordinates(0.0F, localSubSimplexClosestResult.barycentricCoords[0], localSubSimplexClosestResult.barycentricCoords[2], localSubSimplexClosestResult.barycentricCoords[1]);
/* 616:    */        }
/* 617:    */      }
/* 618:    */      
/* 627:589 */      if ((paramSubSimplexClosestResult.usedVertices.usedVertexA) && (paramSubSimplexClosestResult.usedVertices.usedVertexB) && (paramSubSimplexClosestResult.usedVertices.usedVertexC) && (paramSubSimplexClosestResult.usedVertices.usedVertexD))
/* 628:    */      {
/* 632:594 */        return true;
/* 633:    */      }
/* 634:    */      
/* 635:597 */      return true; } finally { this.subsimplexResultsPool.release(localSubSimplexClosestResult);
/* 636:    */    }
/* 637:    */  }
/* 638:    */  
/* 644:    */  public void reset()
/* 645:    */  {
/* 646:608 */    this.cachedValidClosest = false;
/* 647:609 */    this.numVertices = 0;
/* 648:610 */    this.needsUpdate = true;
/* 649:611 */    this.lastW.set(1.0E+030F, 1.0E+030F, 1.0E+030F);
/* 650:612 */    this.cachedBC.reset();
/* 651:    */  }
/* 652:    */  
/* 653:    */  public void addVertex(Vector3f paramVector3f1, Vector3f paramVector3f2, Vector3f paramVector3f3) {
/* 654:616 */    this.lastW.set(paramVector3f1);
/* 655:617 */    this.needsUpdate = true;
/* 656:    */    
/* 657:619 */    this.simplexVectorW[this.numVertices].set(paramVector3f1);
/* 658:620 */    this.simplexPointsP[this.numVertices].set(paramVector3f2);
/* 659:621 */    this.simplexPointsQ[this.numVertices].set(paramVector3f3);
/* 660:    */    
/* 661:623 */    this.numVertices += 1;
/* 662:    */  }
/* 663:    */  
/* 666:    */  public boolean closest(Vector3f paramVector3f)
/* 667:    */  {
/* 668:630 */    boolean bool = updateClosestVectorAndPoints();
/* 669:631 */    paramVector3f.set(this.cachedV);
/* 670:632 */    return bool;
/* 671:    */  }
/* 672:    */  
/* 673:    */  public float maxVertex() {
/* 674:636 */    int j = numVertices();
/* 675:637 */    float f1 = 0.0F;
/* 676:638 */    for (int i = 0; i < j; i++) {
/* 677:639 */      float f2 = this.simplexVectorW[i].lengthSquared();
/* 678:640 */      if (f1 < f2) {
/* 679:641 */        f1 = f2;
/* 680:    */      }
/* 681:    */    }
/* 682:644 */    return f1;
/* 683:    */  }
/* 684:    */  
/* 685:    */  public boolean fullSimplex() {
/* 686:648 */    return this.numVertices == 4;
/* 687:    */  }
/* 688:    */  
/* 689:    */  public int getSimplex(Vector3f[] paramArrayOfVector3f1, Vector3f[] paramArrayOfVector3f2, Vector3f[] paramArrayOfVector3f3) {
/* 690:652 */    for (int i = 0; i < numVertices(); i++) {
/* 691:653 */      paramArrayOfVector3f3[i].set(this.simplexVectorW[i]);
/* 692:654 */      paramArrayOfVector3f1[i].set(this.simplexPointsP[i]);
/* 693:655 */      paramArrayOfVector3f2[i].set(this.simplexPointsQ[i]);
/* 694:    */    }
/* 695:657 */    return numVertices();
/* 696:    */  }
/* 697:    */  
/* 698:    */  public boolean inSimplex(Vector3f paramVector3f) {
/* 699:661 */    boolean bool = false;
/* 700:662 */    int j = numVertices();
/* 701:    */    
/* 704:666 */    for (int i = 0; i < j; i++) {
/* 705:667 */      if (this.simplexVectorW[i].equals(paramVector3f)) {
/* 706:668 */        bool = true;
/* 707:    */      }
/* 708:    */    }
/* 709:    */    
/* 711:673 */    if (paramVector3f.equals(this.lastW)) {
/* 712:674 */      return true;
/* 713:    */    }
/* 714:    */    
/* 715:677 */    return bool;
/* 716:    */  }
/* 717:    */  
/* 718:    */  public void backup_closest(Vector3f paramVector3f) {
/* 719:681 */    paramVector3f.set(this.cachedV);
/* 720:    */  }
/* 721:    */  
/* 722:    */  public boolean emptySimplex() {
/* 723:685 */    return numVertices() == 0;
/* 724:    */  }
/* 725:    */  
/* 726:    */  public void compute_points(Vector3f paramVector3f1, Vector3f paramVector3f2) {
/* 727:689 */    updateClosestVectorAndPoints();
/* 728:690 */    paramVector3f1.set(this.cachedP1);
/* 729:691 */    paramVector3f2.set(this.cachedP2);
/* 730:    */  }
/* 731:    */  
/* 732:    */  public int numVertices() {
/* 733:695 */    return this.numVertices;
/* 734:    */  }
/* 735:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.VoronoiSimplexSolverExt
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */