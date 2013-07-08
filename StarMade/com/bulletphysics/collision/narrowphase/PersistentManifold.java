/*   1:    */package com.bulletphysics.collision.narrowphase;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.BulletGlobals;
/*   5:    */import com.bulletphysics.ContactDestroyedCallback;
/*   6:    */import com.bulletphysics.ContactProcessedCallback;
/*   7:    */import com.bulletphysics.linearmath.Transform;
/*   8:    */import com.bulletphysics.linearmath.VectorUtil;
/*   9:    */import javax.vecmath.Vector3f;
/*  10:    */import javax.vecmath.Vector4f;
/*  11:    */
/*  55:    */public class PersistentManifold
/*  56:    */{
/*  57:    */  public static final int MANIFOLD_CACHE_SIZE = 4;
/*  58: 58 */  private final ManifoldPoint[] pointCache = new ManifoldPoint[4];
/*  59:    */  
/*  60:    */  private Object body0;
/*  61:    */  
/*  62:    */  private Object body1;
/*  63:    */  private int cachedPoints;
/*  64:    */  public int index1a;
/*  65:    */  
/*  66:    */  public PersistentManifold()
/*  67:    */  {
/*  68: 68 */    for (int i = 0; i < this.pointCache.length; i++) this.pointCache[i] = new ManifoldPoint(); } public PersistentManifold(Object body0, Object body1, int bla) { for (int i = 0; i < this.pointCache.length; i++) { this.pointCache[i] = new ManifoldPoint();
/*  69:    */    }
/*  70:    */    
/*  75: 75 */    init(body0, body1, bla);
/*  76:    */  }
/*  77:    */  
/*  78:    */  public void init(Object body0, Object body1, int bla) {
/*  79: 79 */    this.body0 = body0;
/*  80: 80 */    this.body1 = body1;
/*  81: 81 */    this.cachedPoints = 0;
/*  82: 82 */    this.index1a = 0;
/*  83:    */  }
/*  84:    */  
/*  88:    */  private int sortCachedPoints(ManifoldPoint arg1)
/*  89:    */  {
/*  90: 90 */    .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$javax$vecmath$Vector3f();tmp7_5.push$javax$vecmath$Vector4f();int maxPenetrationIndex = -1;
/*  91:    */      
/*  93: 93 */      float maxPenetration = pt.getDistance();
/*  94: 94 */      for (int i = 0; i < 4; i++) {
/*  95: 95 */        if (this.pointCache[i].getDistance() < maxPenetration) {
/*  96: 96 */          maxPenetrationIndex = i;
/*  97: 97 */          maxPenetration = this.pointCache[i].getDistance();
/*  98:    */        }
/*  99:    */      }
/* 100:    */      
/* 102:102 */      float res0 = 0.0F;float res1 = 0.0F;float res2 = 0.0F;float res3 = 0.0F;
/* 103:103 */      if (maxPenetrationIndex != 0) {
/* 104:104 */        Vector3f a0 = localStack.get$javax$vecmath$Vector3f(pt.localPointA);
/* 105:105 */        a0.sub(this.pointCache[1].localPointA);
/* 106:    */        
/* 107:107 */        Vector3f b0 = localStack.get$javax$vecmath$Vector3f(this.pointCache[3].localPointA);
/* 108:108 */        b0.sub(this.pointCache[2].localPointA);
/* 109:    */        
/* 110:110 */        Vector3f cross = localStack.get$javax$vecmath$Vector3f();
/* 111:111 */        cross.cross(a0, b0);
/* 112:    */        
/* 113:113 */        res0 = cross.lengthSquared();
/* 114:    */      }
/* 115:    */      
/* 116:116 */      if (maxPenetrationIndex != 1) {
/* 117:117 */        Vector3f a1 = localStack.get$javax$vecmath$Vector3f(pt.localPointA);
/* 118:118 */        a1.sub(this.pointCache[0].localPointA);
/* 119:    */        
/* 120:120 */        Vector3f b1 = localStack.get$javax$vecmath$Vector3f(this.pointCache[3].localPointA);
/* 121:121 */        b1.sub(this.pointCache[2].localPointA);
/* 122:    */        
/* 123:123 */        Vector3f cross = localStack.get$javax$vecmath$Vector3f();
/* 124:124 */        cross.cross(a1, b1);
/* 125:125 */        res1 = cross.lengthSquared();
/* 126:    */      }
/* 127:    */      
/* 128:128 */      if (maxPenetrationIndex != 2) {
/* 129:129 */        Vector3f a2 = localStack.get$javax$vecmath$Vector3f(pt.localPointA);
/* 130:130 */        a2.sub(this.pointCache[0].localPointA);
/* 131:    */        
/* 132:132 */        Vector3f b2 = localStack.get$javax$vecmath$Vector3f(this.pointCache[3].localPointA);
/* 133:133 */        b2.sub(this.pointCache[1].localPointA);
/* 134:    */        
/* 135:135 */        Vector3f cross = localStack.get$javax$vecmath$Vector3f();
/* 136:136 */        cross.cross(a2, b2);
/* 137:    */        
/* 138:138 */        res2 = cross.lengthSquared();
/* 139:    */      }
/* 140:    */      
/* 141:141 */      if (maxPenetrationIndex != 3) {
/* 142:142 */        Vector3f a3 = localStack.get$javax$vecmath$Vector3f(pt.localPointA);
/* 143:143 */        a3.sub(this.pointCache[0].localPointA);
/* 144:    */        
/* 145:145 */        Vector3f b3 = localStack.get$javax$vecmath$Vector3f(this.pointCache[2].localPointA);
/* 146:146 */        b3.sub(this.pointCache[1].localPointA);
/* 147:    */        
/* 148:148 */        Vector3f cross = localStack.get$javax$vecmath$Vector3f();
/* 149:149 */        cross.cross(a3, b3);
/* 150:150 */        res3 = cross.lengthSquared();
/* 151:    */      }
/* 152:    */      
/* 153:153 */      Vector4f maxvec = localStack.get$javax$vecmath$Vector4f();
/* 154:154 */      maxvec.set(res0, res1, res2, res3);
/* 155:155 */      return VectorUtil.closestAxis4(maxvec);
/* 156:156 */    } finally { .Stack tmp457_455 = localStack;tmp457_455.pop$javax$vecmath$Vector3f();tmp457_455.pop$javax$vecmath$Vector4f();
/* 157:    */    }
/* 158:    */  }
/* 159:    */  
/* 160:    */  public Object getBody0()
/* 161:    */  {
/* 162:162 */    return this.body0;
/* 163:    */  }
/* 164:    */  
/* 165:    */  public Object getBody1() {
/* 166:166 */    return this.body1;
/* 167:    */  }
/* 168:    */  
/* 169:    */  public void setBodies(Object body0, Object body1) {
/* 170:170 */    this.body0 = body0;
/* 171:171 */    this.body1 = body1;
/* 172:    */  }
/* 173:    */  
/* 174:    */  public void clearUserCache(ManifoldPoint pt) {
/* 175:175 */    Object oldPtr = pt.userPersistentData;
/* 176:176 */    if (oldPtr != null)
/* 177:    */    {
/* 191:191 */      if ((pt.userPersistentData != null) && (BulletGlobals.getContactDestroyedCallback() != null)) {
/* 192:192 */        BulletGlobals.getContactDestroyedCallback().contactDestroyed(pt.userPersistentData);
/* 193:193 */        pt.userPersistentData = null;
/* 194:    */      }
/* 195:    */    }
/* 196:    */  }
/* 197:    */  
/* 201:    */  public int getNumContacts()
/* 202:    */  {
/* 203:203 */    return this.cachedPoints;
/* 204:    */  }
/* 205:    */  
/* 206:    */  public ManifoldPoint getContactPoint(int index) {
/* 207:207 */    return this.pointCache[index];
/* 208:    */  }
/* 209:    */  
/* 210:    */  public float getContactBreakingThreshold()
/* 211:    */  {
/* 212:212 */    return BulletGlobals.getContactBreakingThreshold();
/* 213:    */  }
/* 214:    */  
/* 215:    */  public int getCacheEntry(ManifoldPoint arg1) {
/* 216:216 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();float shortestDist = getContactBreakingThreshold() * getContactBreakingThreshold();
/* 217:217 */      int size = getNumContacts();
/* 218:218 */      int nearestPoint = -1;
/* 219:219 */      Vector3f diffA = localStack.get$javax$vecmath$Vector3f();
/* 220:220 */      for (int i = 0; i < size; i++) {
/* 221:221 */        ManifoldPoint mp = this.pointCache[i];
/* 222:    */        
/* 223:223 */        diffA.sub(mp.localPointA, newPoint.localPointA);
/* 224:    */        
/* 225:225 */        float distToManiPoint = diffA.dot(diffA);
/* 226:226 */        if (distToManiPoint < shortestDist) {
/* 227:227 */          shortestDist = distToManiPoint;
/* 228:228 */          nearestPoint = i;
/* 229:    */        }
/* 230:    */      }
/* 231:231 */      return nearestPoint; } finally { localStack.pop$javax$vecmath$Vector3f();
/* 232:    */    }
/* 233:    */  }
/* 234:    */  
/* 235:235 */  public int addManifoldPoint(ManifoldPoint newPoint) { assert (validContactDistance(newPoint));
/* 236:    */    
/* 237:237 */    int insertIndex = getNumContacts();
/* 238:238 */    if (insertIndex == 4)
/* 239:    */    {
/* 242:242 */      insertIndex = sortCachedPoints(newPoint);
/* 243:    */      
/* 250:250 */      clearUserCache(this.pointCache[insertIndex]);
/* 251:    */    }
/* 252:    */    else {
/* 253:253 */      this.cachedPoints += 1;
/* 254:    */    }
/* 255:255 */    assert (this.pointCache[insertIndex].userPersistentData == null);
/* 256:256 */    this.pointCache[insertIndex].set(newPoint);
/* 257:257 */    return insertIndex;
/* 258:    */  }
/* 259:    */  
/* 260:    */  public void removeContactPoint(int index) {
/* 261:261 */    clearUserCache(this.pointCache[index]);
/* 262:    */    
/* 263:263 */    int lastUsedIndex = getNumContacts() - 1;
/* 264:    */    
/* 265:265 */    if (index != lastUsedIndex)
/* 266:    */    {
/* 267:267 */      this.pointCache[index].set(this.pointCache[lastUsedIndex]);
/* 268:    */      
/* 269:269 */      this.pointCache[lastUsedIndex].userPersistentData = null;
/* 270:270 */      this.pointCache[lastUsedIndex].appliedImpulse = 0.0F;
/* 271:271 */      this.pointCache[lastUsedIndex].lateralFrictionInitialized = false;
/* 272:272 */      this.pointCache[lastUsedIndex].appliedImpulseLateral1 = 0.0F;
/* 273:273 */      this.pointCache[lastUsedIndex].appliedImpulseLateral2 = 0.0F;
/* 274:274 */      this.pointCache[lastUsedIndex].lifeTime = 0;
/* 275:    */    }
/* 276:    */    
/* 277:277 */    assert (this.pointCache[lastUsedIndex].userPersistentData == null);
/* 278:278 */    this.cachedPoints -= 1;
/* 279:    */  }
/* 280:    */  
/* 281:    */  public void replaceContactPoint(ManifoldPoint newPoint, int insertIndex) {
/* 282:282 */    assert (validContactDistance(newPoint));
/* 283:    */    
/* 286:286 */    int lifeTime = this.pointCache[insertIndex].getLifeTime();
/* 287:287 */    float appliedImpulse = this.pointCache[insertIndex].appliedImpulse;
/* 288:288 */    float appliedLateralImpulse1 = this.pointCache[insertIndex].appliedImpulseLateral1;
/* 289:289 */    float appliedLateralImpulse2 = this.pointCache[insertIndex].appliedImpulseLateral2;
/* 290:    */    
/* 291:291 */    assert (lifeTime >= 0);
/* 292:292 */    Object cache = this.pointCache[insertIndex].userPersistentData;
/* 293:    */    
/* 294:294 */    this.pointCache[insertIndex].set(newPoint);
/* 295:    */    
/* 296:296 */    this.pointCache[insertIndex].userPersistentData = cache;
/* 297:297 */    this.pointCache[insertIndex].appliedImpulse = appliedImpulse;
/* 298:298 */    this.pointCache[insertIndex].appliedImpulseLateral1 = appliedLateralImpulse1;
/* 299:299 */    this.pointCache[insertIndex].appliedImpulseLateral2 = appliedLateralImpulse2;
/* 300:    */    
/* 301:301 */    this.pointCache[insertIndex].lifeTime = lifeTime;
/* 302:    */  }
/* 303:    */  
/* 307:    */  private boolean validContactDistance(ManifoldPoint pt)
/* 308:    */  {
/* 309:309 */    return pt.distance1 <= getContactBreakingThreshold();
/* 310:    */  }
/* 311:    */  
/* 312:    */  public void refreshContactPoints(Transform arg1, Transform arg2)
/* 313:    */  {
/* 314:314 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 315:    */      
/* 326:326 */      for (int i = getNumContacts() - 1; i >= 0; i--) {
/* 327:327 */        ManifoldPoint manifoldPoint = this.pointCache[i];
/* 328:    */        
/* 329:329 */        manifoldPoint.positionWorldOnA.set(manifoldPoint.localPointA);
/* 330:330 */        trA.transform(manifoldPoint.positionWorldOnA);
/* 331:    */        
/* 332:332 */        manifoldPoint.positionWorldOnB.set(manifoldPoint.localPointB);
/* 333:333 */        trB.transform(manifoldPoint.positionWorldOnB);
/* 334:    */        
/* 335:335 */        tmp.set(manifoldPoint.positionWorldOnA);
/* 336:336 */        tmp.sub(manifoldPoint.positionWorldOnB);
/* 337:337 */        manifoldPoint.distance1 = tmp.dot(manifoldPoint.normalWorldOnB);
/* 338:    */        
/* 339:339 */        manifoldPoint.lifeTime += 1;
/* 340:    */      }
/* 341:    */      
/* 344:344 */      Vector3f projectedDifference = localStack.get$javax$vecmath$Vector3f();Vector3f projectedPoint = localStack.get$javax$vecmath$Vector3f();
/* 345:    */      
/* 346:346 */      for (i = getNumContacts() - 1; i >= 0; i--)
/* 347:    */      {
/* 348:348 */        ManifoldPoint manifoldPoint = this.pointCache[i];
/* 349:    */        
/* 350:350 */        if (!validContactDistance(manifoldPoint)) {
/* 351:351 */          removeContactPoint(i);
/* 352:    */        }
/* 353:    */        else
/* 354:    */        {
/* 355:355 */          tmp.scale(manifoldPoint.distance1, manifoldPoint.normalWorldOnB);
/* 356:356 */          projectedPoint.sub(manifoldPoint.positionWorldOnA, tmp);
/* 357:357 */          projectedDifference.sub(manifoldPoint.positionWorldOnB, projectedPoint);
/* 358:358 */          float distance2d = projectedDifference.dot(projectedDifference);
/* 359:359 */          if (distance2d > getContactBreakingThreshold() * getContactBreakingThreshold()) {
/* 360:360 */            removeContactPoint(i);
/* 363:    */          }
/* 364:364 */          else if (BulletGlobals.getContactProcessedCallback() != null) {
/* 365:365 */            BulletGlobals.getContactProcessedCallback().contactProcessed(manifoldPoint, this.body0, this.body1);
/* 366:    */          }
/* 367:    */          
/* 368:    */        }
/* 369:    */      }
/* 370:    */    }
/* 371:    */    finally
/* 372:    */    {
/* 373:373 */      localStack.pop$javax$vecmath$Vector3f();
/* 374:    */    }
/* 375:    */  }
/* 376:    */  
/* 377:377 */  public void clearManifold() { for (int i = 0; i < this.cachedPoints; i++) {
/* 378:378 */      clearUserCache(this.pointCache[i]);
/* 379:    */    }
/* 380:380 */    this.cachedPoints = 0;
/* 381:    */  }
/* 382:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.narrowphase.PersistentManifold
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */