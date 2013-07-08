/*   1:    */package com.bulletphysics.collision.narrowphase;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.BulletStats;
/*   5:    */import com.bulletphysics.collision.shapes.ConvexShape;
/*   6:    */import com.bulletphysics.linearmath.IDebugDraw;
/*   7:    */import com.bulletphysics.linearmath.MatrixUtil;
/*   8:    */import com.bulletphysics.linearmath.Transform;
/*   9:    */import javax.vecmath.Vector3f;
/*  10:    */
/*  44:    */public class GjkPairDetector
/*  45:    */  extends DiscreteCollisionDetectorInterface
/*  46:    */{
/*  47:    */  private static final float REL_ERROR2 = 1.0E-006F;
/*  48: 48 */  private final Vector3f cachedSeparatingAxis = new Vector3f();
/*  49:    */  
/*  50:    */  private ConvexPenetrationDepthSolver penetrationDepthSolver;
/*  51:    */  private SimplexSolverInterface simplexSolver;
/*  52:    */  private ConvexShape minkowskiA;
/*  53:    */  private ConvexShape minkowskiB;
/*  54:    */  private boolean ignoreMargin;
/*  55:    */  public int lastUsedMethod;
/*  56:    */  public int curIter;
/*  57:    */  public int degenerateSimplex;
/*  58:    */  public int catchDegeneracies;
/*  59:    */  
/*  60:    */  public void init(ConvexShape objectA, ConvexShape objectB, SimplexSolverInterface simplexSolver, ConvexPenetrationDepthSolver penetrationDepthSolver)
/*  61:    */  {
/*  62: 62 */    this.cachedSeparatingAxis.set(0.0F, 0.0F, 1.0F);
/*  63: 63 */    this.ignoreMargin = false;
/*  64: 64 */    this.lastUsedMethod = -1;
/*  65: 65 */    this.catchDegeneracies = 1;
/*  66:    */    
/*  67: 67 */    this.penetrationDepthSolver = penetrationDepthSolver;
/*  68: 68 */    this.simplexSolver = simplexSolver;
/*  69: 69 */    this.minkowskiA = objectA;
/*  70: 70 */    this.minkowskiB = objectB;
/*  71:    */  }
/*  72:    */  
/*  73:    */  public void getClosestPoints(DiscreteCollisionDetectorInterface.ClosestPointInput arg1, DiscreteCollisionDetectorInterface.Result arg2, IDebugDraw arg3, boolean arg4)
/*  74:    */  {
/*  75: 75 */    .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$com$bulletphysics$linearmath$Transform();tmp7_5.push$javax$vecmath$Vector3f();Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*  76:    */      
/*  77: 77 */      float distance = 0.0F;
/*  78: 78 */      Vector3f normalInB = localStack.get$javax$vecmath$Vector3f();
/*  79: 79 */      normalInB.set(0.0F, 0.0F, 0.0F);
/*  80: 80 */      Vector3f pointOnA = localStack.get$javax$vecmath$Vector3f();Vector3f pointOnB = localStack.get$javax$vecmath$Vector3f();
/*  81: 81 */      Transform localTransA = localStack.get$com$bulletphysics$linearmath$Transform(input.transformA);
/*  82: 82 */      Transform localTransB = localStack.get$com$bulletphysics$linearmath$Transform(input.transformB);
/*  83: 83 */      Vector3f positionOffset = localStack.get$javax$vecmath$Vector3f();
/*  84: 84 */      positionOffset.add(localTransA.origin, localTransB.origin);
/*  85: 85 */      positionOffset.scale(0.5F);
/*  86: 86 */      localTransA.origin.sub(positionOffset);
/*  87: 87 */      localTransB.origin.sub(positionOffset);
/*  88:    */      
/*  89: 89 */      float marginA = this.minkowskiA.getMargin();
/*  90: 90 */      float marginB = this.minkowskiB.getMargin();
/*  91:    */      
/*  92: 92 */      BulletStats.gNumGjkChecks += 1;
/*  93:    */      
/*  95: 95 */      if (this.ignoreMargin) {
/*  96: 96 */        marginA = 0.0F;
/*  97: 97 */        marginB = 0.0F;
/*  98:    */      }
/*  99:    */      
/* 100:100 */      this.curIter = 0;
/* 101:101 */      int gGjkMaxIter = 1000;
/* 102:102 */      this.cachedSeparatingAxis.set(0.0F, 1.0F, 0.0F);
/* 103:    */      
/* 104:104 */      boolean isValid = false;
/* 105:105 */      boolean checkSimplex = false;
/* 106:106 */      boolean checkPenetration = true;
/* 107:107 */      this.degenerateSimplex = 0;
/* 108:    */      
/* 109:109 */      this.lastUsedMethod = -1;
/* 110:    */      
/* 112:112 */      float squaredDistance = 3.4028235E+38F;
/* 113:113 */      float delta = 0.0F;
/* 114:    */      
/* 115:115 */      float margin = marginA + marginB;
/* 116:    */      
/* 117:117 */      this.simplexSolver.reset();
/* 118:    */      
/* 119:119 */      Vector3f seperatingAxisInA = localStack.get$javax$vecmath$Vector3f();
/* 120:120 */      Vector3f seperatingAxisInB = localStack.get$javax$vecmath$Vector3f();
/* 121:    */      
/* 122:122 */      Vector3f pInA = localStack.get$javax$vecmath$Vector3f();
/* 123:123 */      Vector3f qInB = localStack.get$javax$vecmath$Vector3f();
/* 124:    */      
/* 125:125 */      Vector3f pWorld = localStack.get$javax$vecmath$Vector3f();
/* 126:126 */      Vector3f qWorld = localStack.get$javax$vecmath$Vector3f();
/* 127:127 */      Vector3f w = localStack.get$javax$vecmath$Vector3f();
/* 128:    */      
/* 129:129 */      Vector3f tmpPointOnA = localStack.get$javax$vecmath$Vector3f();Vector3f tmpPointOnB = localStack.get$javax$vecmath$Vector3f();
/* 130:130 */      Vector3f tmpNormalInB = localStack.get$javax$vecmath$Vector3f();
/* 131:    */      
/* 132:    */      for (;;)
/* 133:    */      {
/* 134:134 */        seperatingAxisInA.negate(this.cachedSeparatingAxis);
/* 135:135 */        MatrixUtil.transposeTransform(seperatingAxisInA, seperatingAxisInA, input.transformA.basis);
/* 136:    */        
/* 137:137 */        seperatingAxisInB.set(this.cachedSeparatingAxis);
/* 138:138 */        MatrixUtil.transposeTransform(seperatingAxisInB, seperatingAxisInB, input.transformB.basis);
/* 139:    */        
/* 140:140 */        this.minkowskiA.localGetSupportingVertexWithoutMargin(seperatingAxisInA, pInA);
/* 141:141 */        this.minkowskiB.localGetSupportingVertexWithoutMargin(seperatingAxisInB, qInB);
/* 142:    */        
/* 143:143 */        pWorld.set(pInA);
/* 144:144 */        localTransA.transform(pWorld);
/* 145:    */        
/* 146:146 */        qWorld.set(qInB);
/* 147:147 */        localTransB.transform(qWorld);
/* 148:    */        
/* 149:149 */        w.sub(pWorld, qWorld);
/* 150:    */        
/* 151:151 */        delta = this.cachedSeparatingAxis.dot(w);
/* 152:    */        
/* 154:154 */        if ((delta > 0.0F) && (delta * delta > squaredDistance * input.maximumDistanceSquared)) {
/* 155:155 */          checkPenetration = false;
/* 156:156 */          break;
/* 157:    */        }
/* 158:    */        
/* 160:160 */        if (this.simplexSolver.inSimplex(w)) {
/* 161:161 */          this.degenerateSimplex = 1;
/* 162:162 */          checkSimplex = true;
/* 163:163 */          break;
/* 164:    */        }
/* 165:    */        
/* 166:166 */        float f0 = squaredDistance - delta;
/* 167:167 */        float f1 = squaredDistance * 1.0E-006F;
/* 168:    */        
/* 169:169 */        if (f0 <= f1) {
/* 170:170 */          if (f0 <= 0.0F) {
/* 171:171 */            this.degenerateSimplex = 2;
/* 172:    */          }
/* 173:173 */          checkSimplex = true;
/* 174:174 */          break;
/* 175:    */        }
/* 176:    */        
/* 177:177 */        this.simplexSolver.addVertex(w, pWorld, qWorld);
/* 178:    */        
/* 180:180 */        if (!this.simplexSolver.closest(this.cachedSeparatingAxis)) {
/* 181:181 */          this.degenerateSimplex = 3;
/* 182:182 */          checkSimplex = true;
/* 183:183 */          break;
/* 184:    */        }
/* 185:    */        
/* 186:186 */        if (this.cachedSeparatingAxis.lengthSquared() < 1.0E-006F) {
/* 187:187 */          this.degenerateSimplex = 6;
/* 188:188 */          checkSimplex = true;
/* 189:189 */          break;
/* 190:    */        }
/* 191:    */        
/* 192:192 */        float previousSquaredDistance = squaredDistance;
/* 193:193 */        squaredDistance = this.cachedSeparatingAxis.lengthSquared();
/* 194:    */        
/* 198:198 */        if (previousSquaredDistance - squaredDistance <= 1.192093E-007F * previousSquaredDistance) {
/* 199:199 */          this.simplexSolver.backup_closest(this.cachedSeparatingAxis);
/* 200:200 */          checkSimplex = true;
/* 201:201 */          break;
/* 202:    */        }
/* 203:    */        
/* 205:205 */        if (this.curIter++ > gGjkMaxIter) {
/* 206:    */          break;
/* 207:    */        }
/* 208:    */        
/* 222:222 */        boolean check = !this.simplexSolver.fullSimplex();
/* 223:    */        
/* 225:225 */        if (!check)
/* 226:    */        {
/* 227:227 */          this.simplexSolver.backup_closest(this.cachedSeparatingAxis);
/* 228:228 */          break;
/* 229:    */        }
/* 230:    */      }
/* 231:    */      
/* 232:232 */      if (checkSimplex) {
/* 233:233 */        this.simplexSolver.compute_points(pointOnA, pointOnB);
/* 234:234 */        normalInB.sub(pointOnA, pointOnB);
/* 235:235 */        float lenSqr = this.cachedSeparatingAxis.lengthSquared();
/* 236:    */        
/* 237:237 */        if (lenSqr < 1.0E-004F) {
/* 238:238 */          this.degenerateSimplex = 5;
/* 239:    */        }
/* 240:240 */        if (lenSqr > 1.421086E-014F) {
/* 241:241 */          float rlen = 1.0F / (float)Math.sqrt(lenSqr);
/* 242:242 */          normalInB.scale(rlen);
/* 243:243 */          float s = (float)Math.sqrt(squaredDistance);
/* 244:    */          
/* 245:245 */          assert (s > 0.0F);
/* 246:    */          
/* 247:247 */          tmp.scale(marginA / s, this.cachedSeparatingAxis);
/* 248:248 */          pointOnA.sub(tmp);
/* 249:    */          
/* 250:250 */          tmp.scale(marginB / s, this.cachedSeparatingAxis);
/* 251:251 */          pointOnB.add(tmp);
/* 252:    */          
/* 253:253 */          distance = 1.0F / rlen - margin;
/* 254:254 */          isValid = true;
/* 255:    */          
/* 256:256 */          this.lastUsedMethod = 1;
/* 257:    */        }
/* 258:    */        else {
/* 259:259 */          this.lastUsedMethod = 2;
/* 260:    */        }
/* 261:    */      }
/* 262:    */      
/* 263:263 */      boolean catchDegeneratePenetrationCase = (this.catchDegeneracies != 0) && (this.penetrationDepthSolver != null) && (this.degenerateSimplex != 0) && (distance + margin < 0.01F);
/* 264:    */      
/* 267:267 */      if ((checkPenetration) && ((!isValid) || (catchDegeneratePenetrationCase)))
/* 268:    */      {
/* 271:271 */        if (this.penetrationDepthSolver != null)
/* 272:    */        {
/* 273:273 */          BulletStats.gNumDeepPenetrationChecks += 1;
/* 274:    */          
/* 275:275 */          boolean isValid2 = this.penetrationDepthSolver.calcPenDepth(this.simplexSolver, this.minkowskiA, this.minkowskiB, localTransA, localTransB, this.cachedSeparatingAxis, tmpPointOnA, tmpPointOnB, debugDraw);
/* 276:    */          
/* 282:282 */          if (isValid2) {
/* 283:283 */            tmpNormalInB.sub(tmpPointOnB, tmpPointOnA);
/* 284:    */            
/* 285:285 */            float lenSqr = tmpNormalInB.lengthSquared();
/* 286:286 */            if (lenSqr > 1.421086E-014F) {
/* 287:287 */              tmpNormalInB.scale(1.0F / (float)Math.sqrt(lenSqr));
/* 288:288 */              tmp.sub(tmpPointOnA, tmpPointOnB);
/* 289:289 */              float distance2 = -tmp.length();
/* 290:    */              
/* 291:291 */              if ((!isValid) || (distance2 < distance)) {
/* 292:292 */                distance = distance2;
/* 293:293 */                pointOnA.set(tmpPointOnA);
/* 294:294 */                pointOnB.set(tmpPointOnB);
/* 295:295 */                normalInB.set(tmpNormalInB);
/* 296:296 */                isValid = true;
/* 297:297 */                this.lastUsedMethod = 3;
/* 298:    */              }
/* 299:    */              
/* 301:    */            }
/* 302:    */            else
/* 303:    */            {
/* 305:305 */              this.lastUsedMethod = 4;
/* 306:    */            }
/* 307:    */          }
/* 308:    */          else {
/* 309:309 */            this.lastUsedMethod = 5;
/* 310:    */          }
/* 311:    */        }
/* 312:    */      }
/* 313:    */      
/* 316:316 */      if (isValid)
/* 317:    */      {
/* 321:321 */        tmp.add(pointOnB, positionOffset);
/* 322:322 */        output.addContactPoint(normalInB, tmp, distance);
/* 323:    */      }
/* 324:    */      
/* 325:    */    }
/* 326:    */    finally
/* 327:    */    {
/* 328:328 */      .Stack tmp1101_1099 = localStack;tmp1101_1099.pop$com$bulletphysics$linearmath$Transform();tmp1101_1099.pop$javax$vecmath$Vector3f();
/* 329:    */    } }
/* 330:    */  
/* 331:331 */  public void setMinkowskiA(ConvexShape minkA) { this.minkowskiA = minkA; }
/* 332:    */  
/* 333:    */  public void setMinkowskiB(ConvexShape minkB)
/* 334:    */  {
/* 335:335 */    this.minkowskiB = minkB;
/* 336:    */  }
/* 337:    */  
/* 338:    */  public void setCachedSeperatingAxis(Vector3f seperatingAxis) {
/* 339:339 */    this.cachedSeparatingAxis.set(seperatingAxis);
/* 340:    */  }
/* 341:    */  
/* 342:    */  public void setPenetrationDepthSolver(ConvexPenetrationDepthSolver penetrationDepthSolver) {
/* 343:343 */    this.penetrationDepthSolver = penetrationDepthSolver;
/* 344:    */  }
/* 345:    */  
/* 348:    */  public void setIgnoreMargin(boolean ignoreMargin)
/* 349:    */  {
/* 350:350 */    this.ignoreMargin = ignoreMargin;
/* 351:    */  }
/* 352:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.narrowphase.GjkPairDetector
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */