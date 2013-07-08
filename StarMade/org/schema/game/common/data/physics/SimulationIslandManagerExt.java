/*   1:    */package org.schema.game.common.data.physics;
/*   2:    */
/*   3:    */import com.bulletphysics.BulletStats;
/*   4:    */import com.bulletphysics.collision.broadphase.BroadphasePair;
/*   5:    */import com.bulletphysics.collision.broadphase.BroadphaseProxy;
/*   6:    */import com.bulletphysics.collision.broadphase.Dispatcher;
/*   7:    */import com.bulletphysics.collision.broadphase.OverlappingPairCache;
/*   8:    */import com.bulletphysics.collision.dispatch.CollisionObject;
/*   9:    */import com.bulletphysics.collision.dispatch.CollisionWorld;
/*  10:    */import com.bulletphysics.collision.dispatch.SimulationIslandManager;
/*  11:    */import com.bulletphysics.collision.dispatch.SimulationIslandManager.IslandCallback;
/*  12:    */import com.bulletphysics.collision.dispatch.UnionFind;
/*  13:    */import com.bulletphysics.collision.dispatch.UnionFind.Element;
/*  14:    */import com.bulletphysics.collision.narrowphase.PersistentManifold;
/*  15:    */import com.bulletphysics.linearmath.MiscUtil;
/*  16:    */import com.bulletphysics.util.ObjectArrayList;
/*  17:    */import java.io.PrintStream;
/*  18:    */import java.util.Comparator;
/*  19:    */
/*  40:    */public class SimulationIslandManagerExt
/*  41:    */  extends SimulationIslandManager
/*  42:    */{
/*  43: 43 */  private final UnionFind unionFind = new UnionFind();
/*  44:    */  
/*  45: 45 */  private final ObjectArrayList islandmanifold = new ObjectArrayList();
/*  46: 46 */  private final ObjectArrayList islandBodies = new ObjectArrayList();
/*  47:    */  
/*  48:    */  public void initUnionFind(int paramInt) {
/*  49: 49 */    this.unionFind.reset(paramInt);
/*  50:    */  }
/*  51:    */  
/*  52:    */  public UnionFind getUnionFind() {
/*  53: 53 */    return this.unionFind;
/*  54:    */  }
/*  55:    */  
/*  56:    */  public void findUnions(Dispatcher paramDispatcher, CollisionWorld paramCollisionWorld) {
/*  57: 57 */    paramDispatcher = paramCollisionWorld.getPairCache().getOverlappingPairArray();
/*  58: 58 */    for (paramCollisionWorld = 0; paramCollisionWorld < paramDispatcher.size(); paramCollisionWorld++)
/*  59:    */    {
/*  61: 61 */      CollisionObject localCollisionObject = (CollisionObject)(localObject = (BroadphasePair)paramDispatcher.getQuick(paramCollisionWorld)).pProxy0.clientObject;
/*  62: 62 */      Object localObject = (CollisionObject)((BroadphasePair)localObject).pProxy1.clientObject;
/*  63:    */      
/*  64: 64 */      if ((localCollisionObject != null) && (localCollisionObject.mergesSimulationIslands()) && (localObject != null) && (((CollisionObject)localObject).mergesSimulationIslands()))
/*  65:    */      {
/*  66: 66 */        this.unionFind.unite(localCollisionObject.getIslandTag(), ((CollisionObject)localObject).getIslandTag());
/*  67:    */      }
/*  68:    */    }
/*  69:    */  }
/*  70:    */  
/*  71:    */  public void updateActivationState(CollisionWorld paramCollisionWorld, Dispatcher paramDispatcher) {
/*  72: 72 */    initUnionFind(paramCollisionWorld.getCollisionObjectArray().size());
/*  73:    */    
/*  76: 76 */    int i = 0;
/*  77:    */    
/*  78: 78 */    for (int j = 0; j < paramCollisionWorld.getCollisionObjectArray().size(); j++) {
/*  79:    */      CollisionObject localCollisionObject;
/*  80: 80 */      (localCollisionObject = (CollisionObject)paramCollisionWorld.getCollisionObjectArray().getQuick(j)).setIslandTag(i);
/*  81: 81 */      localCollisionObject.setCompanionId(-1);
/*  82: 82 */      localCollisionObject.setHitFraction(1.0F);
/*  83: 83 */      i++;
/*  84:    */    }
/*  85:    */    
/*  88: 88 */    findUnions(paramDispatcher, paramCollisionWorld);
/*  89:    */  }
/*  90:    */  
/*  92:    */  public void storeIslandActivationState(CollisionWorld paramCollisionWorld)
/*  93:    */  {
/*  94: 94 */    int i = 0;
/*  95:    */    
/*  96: 96 */    for (int j = 0; j < paramCollisionWorld.getCollisionObjectArray().size(); j++) {
/*  97:    */      CollisionObject localCollisionObject;
/*  98: 98 */      if (!(localCollisionObject = (CollisionObject)paramCollisionWorld.getCollisionObjectArray().getQuick(j)).isStaticOrKinematicObject()) {
/*  99: 99 */        localCollisionObject.setIslandTag(this.unionFind.find(i));
/* 100:100 */        localCollisionObject.setCompanionId(-1);
/* 101:    */      }
/* 102:    */      else {
/* 103:103 */        localCollisionObject.setIslandTag(-1);
/* 104:104 */        localCollisionObject.setCompanionId(-2);
/* 105:    */      }
/* 106:106 */      i++;
/* 107:    */    }
/* 108:    */  }
/* 109:    */  
/* 111:    */  private static int getIslandId(PersistentManifold paramPersistentManifold)
/* 112:    */  {
/* 113:113 */    CollisionObject localCollisionObject = (CollisionObject)paramPersistentManifold.getBody0();
/* 114:114 */    paramPersistentManifold = (CollisionObject)paramPersistentManifold.getBody1();
/* 115:115 */    if (localCollisionObject.getIslandTag() >= 0) return localCollisionObject.getIslandTag();
/* 116:116 */    return paramPersistentManifold.getIslandTag();
/* 117:    */  }
/* 118:    */  
/* 119:    */  public void buildIslands(Dispatcher paramDispatcher, ObjectArrayList paramObjectArrayList) {
/* 120:120 */    BulletStats.pushProfile("islandUnionFindAndQuickSort");
/* 121:    */    try {
/* 122:122 */      this.islandmanifold.clear();
/* 123:    */      
/* 127:127 */      getUnionFind().sortIslands();
/* 128:128 */      int i = getUnionFind().getNumElements();
/* 129:    */      
/* 130:130 */      int j = 0;
/* 131:    */      
/* 132:    */      CollisionObject localCollisionObject3;
/* 133:    */      
/* 134:134 */      for (int k = 0; k < i; k = j) {
/* 135:135 */        m = getUnionFind().getElement(k).id;
/* 136:136 */        for (j = k + 1; (j < i) && (getUnionFind().getElement(j).id == m); j++) {}
/* 137:    */        
/* 141:141 */        n = 1;
/* 142:    */        
/* 143:    */        int i2;
/* 144:144 */        for (int i1 = k; i1 < j; i1++) {
/* 145:145 */          i2 = getUnionFind().getElement(i1).sz;
/* 146:    */          
/* 148:148 */          if ((localCollisionObject3 = (CollisionObject)paramObjectArrayList.getQuick(i2)).getIslandTag() != m) { localCollisionObject3.getIslandTag();
/* 149:    */          }
/* 150:    */          
/* 152:152 */          assert ((localCollisionObject3.getIslandTag() == m) || (localCollisionObject3.getIslandTag() == -1));
/* 153:153 */          if (localCollisionObject3.getIslandTag() == m) {
/* 154:154 */            if (localCollisionObject3.getActivationState() == 1) {
/* 155:155 */              n = 0;
/* 156:    */            }
/* 157:157 */            if (localCollisionObject3.getActivationState() == 4) {
/* 158:158 */              n = 0;
/* 159:    */            }
/* 160:    */          }
/* 161:    */        }
/* 162:    */        
/* 164:164 */        if (n != 0)
/* 165:    */        {
/* 166:166 */          for (i1 = k; i1 < j; i1++) {
/* 167:167 */            i2 = getUnionFind().getElement(i1).sz;
/* 168:    */            
/* 169:169 */            if ((localCollisionObject3 = (CollisionObject)paramObjectArrayList.getQuick(i2)).getIslandTag() != m) { localCollisionObject3.getIslandTag();
/* 170:    */            }
/* 171:    */            
/* 173:173 */            assert ((localCollisionObject3.getIslandTag() == m) || (localCollisionObject3.getIslandTag() == -1));
/* 174:    */            
/* 175:175 */            if (localCollisionObject3.getIslandTag() == m) {
/* 176:176 */              localCollisionObject3.setActivationState(2);
/* 177:    */            }
/* 178:    */          }
/* 179:    */        }
/* 180:    */        
/* 183:183 */        for (i1 = k; i1 < j; i1++) {
/* 184:184 */          i2 = getUnionFind().getElement(i1).sz;
/* 185:    */          
/* 187:187 */          if ((localCollisionObject3 = (CollisionObject)paramObjectArrayList.getQuick(i2)).getIslandTag() != m) { localCollisionObject3.getIslandTag();
/* 188:    */          }
/* 189:    */          
/* 191:191 */          assert ((localCollisionObject3.getIslandTag() == m) || (localCollisionObject3.getIslandTag() == -1));
/* 192:    */          
/* 193:193 */          if ((localCollisionObject3.getIslandTag() == m) && 
/* 194:194 */            (localCollisionObject3.getActivationState() == 2)) {
/* 195:195 */            localCollisionObject3.setActivationState(3);
/* 196:    */          }
/* 197:    */        }
/* 198:    */      }
/* 199:    */      
/* 204:204 */      int n = paramDispatcher.getNumManifolds();
/* 205:    */      
/* 210:210 */      for (int m = 0; m < n; m++)
/* 211:    */      {
/* 212:    */        PersistentManifold localPersistentManifold;
/* 213:213 */        CollisionObject localCollisionObject2 = (CollisionObject)(localPersistentManifold = paramDispatcher.getManifoldByIndexInternal(m)).getBody0();
/* 214:214 */        localCollisionObject3 = (CollisionObject)localPersistentManifold.getBody1();
/* 215:    */        
/* 216:216 */        i = 0;
/* 217:217 */        for (j = 0; j < paramObjectArrayList.size(); j++) {
/* 218:    */          CollisionObject localCollisionObject1;
/* 219:219 */          if (((localCollisionObject1 = (CollisionObject)paramObjectArrayList.getQuick(j)) == localCollisionObject2) || (localCollisionObject1 == localCollisionObject3)) {
/* 220:220 */            i = 1;
/* 221:    */          }
/* 222:    */        }
/* 223:223 */        if (i == 0) {
/* 224:224 */          for (j = 0; j < paramObjectArrayList.size(); j++) {
/* 225:225 */            paramObjectArrayList.getQuick(j);
/* 226:    */          }
/* 227:    */        }
/* 228:    */        
/* 229:229 */        assert (i != 0) : ("MANIFOLD OBJECTS NOT FOUND " + localCollisionObject2 + "; " + localCollisionObject3);
/* 230:    */        
/* 233:233 */        if (((localCollisionObject2 != null) && (localCollisionObject2.getActivationState() != 2)) || ((localCollisionObject3 != null) && (localCollisionObject3.getActivationState() != 2)))
/* 234:    */        {
/* 237:237 */          if ((localCollisionObject2.isKinematicObject()) && (localCollisionObject2.getActivationState() != 2)) {
/* 238:238 */            localCollisionObject3.activate();
/* 239:    */          }
/* 240:240 */          if ((localCollisionObject3.isKinematicObject()) && (localCollisionObject3.getActivationState() != 2)) {
/* 241:241 */            localCollisionObject2.activate();
/* 242:    */          }
/* 243:    */          
/* 245:245 */          if (paramDispatcher.needsResponse(localCollisionObject2, localCollisionObject3))
/* 246:    */          {
/* 254:254 */            this.islandmanifold.add(localPersistentManifold);
/* 255:    */          }
/* 256:    */        }
/* 257:    */      }
/* 258:    */    }
/* 259:    */    finally
/* 260:    */    {
/* 261:261 */      BulletStats.popProfile();
/* 262:    */    }
/* 263:    */  }
/* 264:    */  
/* 265:    */  public void buildAndProcessIslands(Dispatcher paramDispatcher, ObjectArrayList paramObjectArrayList, SimulationIslandManager.IslandCallback paramIslandCallback) {
/* 266:266 */    buildIslands(paramDispatcher, paramObjectArrayList);
/* 267:    */    int j;
/* 268:268 */    for (paramDispatcher = 0; paramDispatcher < this.islandmanifold.size(); paramDispatcher++) {
/* 269:    */      PersistentManifold localPersistentManifold;
/* 270:270 */      if (((localPersistentManifold = (PersistentManifold)this.islandmanifold.getQuick(paramDispatcher)).getBody0().toString().contains("|CLI")) || (localPersistentManifold.getBody1().toString().contains("|CLI"))) {
/* 271:271 */        localDispatcher2 = 0;
/* 272:272 */        CollisionObject localCollisionObject1; for (j = 0; j < paramObjectArrayList.size(); j++)
/* 273:    */        {
/* 274:274 */          if (((localCollisionObject1 = (CollisionObject)paramObjectArrayList.getQuick(j)) == localPersistentManifold.getBody0()) || (localCollisionObject1 == localPersistentManifold.getBody1())) {
/* 275:275 */            localDispatcher2 = 1;
/* 276:    */          }
/* 277:    */        }
/* 278:278 */        if (localDispatcher2 == 0) {
/* 279:279 */          for (j = 0; j < paramObjectArrayList.size(); j++) {
/* 280:280 */            localCollisionObject1 = (CollisionObject)paramObjectArrayList.getQuick(j);
/* 281:281 */            System.err.println("#" + j + " COLLISION OBJECTS: " + localCollisionObject1);
/* 282:    */          }
/* 283:    */        }
/* 284:284 */        assert (localDispatcher2 != 0) : ("MANIFOLD OBJECTS NOT FOUND " + localPersistentManifold.getBody0() + "; " + localPersistentManifold.getBody1());
/* 285:    */      }
/* 286:    */    }
/* 287:    */    
/* 290:290 */    Dispatcher localDispatcher2 = getUnionFind().getNumElements();
/* 291:    */    
/* 294:294 */    BulletStats.pushProfile("processIslands");
/* 295:    */    
/* 303:    */    try
/* 304:    */    {
/* 305:305 */      j = this.islandmanifold.size();
/* 306:    */      
/* 312:312 */      MiscUtil.quickSort(this.islandmanifold, persistentManifoldComparator);
/* 313:    */      
/* 316:316 */      int k = 0;
/* 317:317 */      int m = 1;
/* 318:    */      
/* 321:    */      int i;
/* 322:    */      
/* 324:324 */      for (Dispatcher localDispatcher1 = 0; localDispatcher1 < localDispatcher2; i = paramDispatcher) {
/* 325:325 */        int n = getUnionFind().getElement(localDispatcher1).id;
/* 326:326 */        int i1 = 0;
/* 327:    */        
/* 328:328 */        for (paramDispatcher = localDispatcher1; (paramDispatcher < localDispatcher2) && (getUnionFind().getElement(paramDispatcher).id == n); paramDispatcher++) {
/* 329:329 */          localDispatcher1 = getUnionFind().getElement(paramDispatcher).sz;
/* 330:330 */          CollisionObject localCollisionObject2 = (CollisionObject)paramObjectArrayList.getQuick(localDispatcher1);
/* 331:331 */          this.islandBodies.add(localCollisionObject2);
/* 332:332 */          if (!localCollisionObject2.isActive()) {
/* 333:333 */            i1 = 1;
/* 334:    */          }
/* 335:    */        }
/* 336:    */        
/* 339:339 */        localDispatcher1 = 0;
/* 340:    */        
/* 341:341 */        int i2 = -1;
/* 342:    */        
/* 343:343 */        if (k < j)
/* 344:    */        {
/* 345:345 */          if (getIslandId((PersistentManifold)this.islandmanifold.getQuick(k)) == n)
/* 346:    */          {
/* 348:348 */            i2 = k;
/* 349:    */            
/* 350:350 */            for (m = k + 1; (m < j) && (n == getIslandId((PersistentManifold)this.islandmanifold.getQuick(m))); m++) {}
/* 351:    */            
/* 354:354 */            localDispatcher1 = m - k;
/* 355:    */          }
/* 356:    */        }
/* 357:    */        
/* 359:359 */        if (i1 == 0)
/* 360:    */        {
/* 373:373 */          paramIslandCallback.processIsland(this.islandBodies, this.islandBodies.size(), this.islandmanifold, i2, localDispatcher1, n);
/* 374:    */        }
/* 375:    */        
/* 377:377 */        if (localDispatcher1 != 0) {
/* 378:378 */          k = m;
/* 379:    */        }
/* 380:    */        
/* 381:381 */        this.islandBodies.clear();
/* 382:    */      }
/* 383:    */    }
/* 384:    */    finally
/* 385:    */    {
/* 386:386 */      BulletStats.popProfile();
/* 387:    */    }
/* 388:    */  }
/* 389:    */  
/* 396:396 */  private static final Comparator persistentManifoldComparator = new SimulationIslandManagerExt.1();
/* 397:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.SimulationIslandManagerExt
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */