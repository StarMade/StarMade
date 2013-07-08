/*   1:    */package com.bulletphysics.collision.dispatch;
/*   2:    */
/*   3:    */import com.bulletphysics.BulletStats;
/*   4:    */import com.bulletphysics.collision.broadphase.BroadphasePair;
/*   5:    */import com.bulletphysics.collision.broadphase.BroadphaseProxy;
/*   6:    */import com.bulletphysics.collision.broadphase.Dispatcher;
/*   7:    */import com.bulletphysics.collision.broadphase.OverlappingPairCache;
/*   8:    */import com.bulletphysics.collision.narrowphase.PersistentManifold;
/*   9:    */import com.bulletphysics.linearmath.MiscUtil;
/*  10:    */import com.bulletphysics.util.ObjectArrayList;
/*  11:    */import java.util.Comparator;
/*  12:    */
/*  33:    */public class SimulationIslandManager
/*  34:    */{
/*  35:    */  private final UnionFind unionFind;
/*  36:    */  private final ObjectArrayList<PersistentManifold> islandmanifold;
/*  37:    */  private final ObjectArrayList<CollisionObject> islandBodies;
/*  38:    */  
/*  39:    */  public SimulationIslandManager()
/*  40:    */  {
/*  41: 41 */    this.unionFind = new UnionFind();
/*  42:    */    
/*  43: 43 */    this.islandmanifold = new ObjectArrayList();
/*  44: 44 */    this.islandBodies = new ObjectArrayList();
/*  45:    */  }
/*  46:    */  
/*  47: 47 */  public void initUnionFind(int n) { this.unionFind.reset(n); }
/*  48:    */  
/*  49:    */  public UnionFind getUnionFind()
/*  50:    */  {
/*  51: 51 */    return this.unionFind;
/*  52:    */  }
/*  53:    */  
/*  54:    */  public void findUnions(Dispatcher dispatcher, CollisionWorld colWorld) {
/*  55: 55 */    ObjectArrayList<BroadphasePair> pairPtr = colWorld.getPairCache().getOverlappingPairArray();
/*  56: 56 */    for (int i = 0; i < pairPtr.size(); i++) {
/*  57: 57 */      BroadphasePair collisionPair = (BroadphasePair)pairPtr.getQuick(i);
/*  58:    */      
/*  59: 59 */      CollisionObject colObj0 = (CollisionObject)collisionPair.pProxy0.clientObject;
/*  60: 60 */      CollisionObject colObj1 = (CollisionObject)collisionPair.pProxy1.clientObject;
/*  61:    */      
/*  62: 62 */      if ((colObj0 != null) && (colObj0.mergesSimulationIslands()) && (colObj1 != null) && (colObj1.mergesSimulationIslands()))
/*  63:    */      {
/*  64: 64 */        this.unionFind.unite(colObj0.getIslandTag(), colObj1.getIslandTag());
/*  65:    */      }
/*  66:    */    }
/*  67:    */  }
/*  68:    */  
/*  69:    */  public void updateActivationState(CollisionWorld colWorld, Dispatcher dispatcher) {
/*  70: 70 */    initUnionFind(colWorld.getCollisionObjectArray().size());
/*  71:    */    
/*  74: 74 */    int index = 0;
/*  75:    */    
/*  76: 76 */    for (int i = 0; i < colWorld.getCollisionObjectArray().size(); i++) {
/*  77: 77 */      CollisionObject collisionObject = (CollisionObject)colWorld.getCollisionObjectArray().getQuick(i);
/*  78: 78 */      collisionObject.setIslandTag(index);
/*  79: 79 */      collisionObject.setCompanionId(-1);
/*  80: 80 */      collisionObject.setHitFraction(1.0F);
/*  81: 81 */      index++;
/*  82:    */    }
/*  83:    */    
/*  86: 86 */    findUnions(dispatcher, colWorld);
/*  87:    */  }
/*  88:    */  
/*  90:    */  public void storeIslandActivationState(CollisionWorld colWorld)
/*  91:    */  {
/*  92: 92 */    int index = 0;
/*  93:    */    
/*  94: 94 */    for (int i = 0; i < colWorld.getCollisionObjectArray().size(); i++) {
/*  95: 95 */      CollisionObject collisionObject = (CollisionObject)colWorld.getCollisionObjectArray().getQuick(i);
/*  96: 96 */      if (!collisionObject.isStaticOrKinematicObject()) {
/*  97: 97 */        collisionObject.setIslandTag(this.unionFind.find(index));
/*  98: 98 */        collisionObject.setCompanionId(-1);
/*  99:    */      }
/* 100:    */      else {
/* 101:101 */        collisionObject.setIslandTag(-1);
/* 102:102 */        collisionObject.setCompanionId(-2);
/* 103:    */      }
/* 104:104 */      index++;
/* 105:    */    }
/* 106:    */  }
/* 107:    */  
/* 109:    */  private static int getIslandId(PersistentManifold lhs)
/* 110:    */  {
/* 111:111 */    CollisionObject rcolObj0 = (CollisionObject)lhs.getBody0();
/* 112:112 */    CollisionObject rcolObj1 = (CollisionObject)lhs.getBody1();
/* 113:113 */    int islandId = rcolObj0.getIslandTag() >= 0 ? rcolObj0.getIslandTag() : rcolObj1.getIslandTag();
/* 114:114 */    return islandId;
/* 115:    */  }
/* 116:    */  
/* 117:    */  public void buildIslands(Dispatcher dispatcher, ObjectArrayList<CollisionObject> collisionObjects) {
/* 118:118 */    BulletStats.pushProfile("islandUnionFindAndQuickSort");
/* 119:    */    try {
/* 120:120 */      this.islandmanifold.clear();
/* 121:    */      
/* 125:125 */      getUnionFind().sortIslands();
/* 126:126 */      int numElem = getUnionFind().getNumElements();
/* 127:    */      
/* 128:128 */      int endIslandIndex = 1;
/* 129:    */      
/* 132:132 */      for (int startIslandIndex = 0; startIslandIndex < numElem; startIslandIndex = endIslandIndex) {
/* 133:133 */        int islandId = getUnionFind().getElement(startIslandIndex).id;
/* 134:134 */        for (endIslandIndex = startIslandIndex + 1; (endIslandIndex < numElem) && (getUnionFind().getElement(endIslandIndex).id == islandId); endIslandIndex++) {}
/* 135:    */        
/* 139:139 */        boolean allSleeping = true;
/* 140:    */        
/* 142:142 */        for (int idx = startIslandIndex; idx < endIslandIndex; idx++) {
/* 143:143 */          int i = getUnionFind().getElement(idx).sz;
/* 144:    */          
/* 145:145 */          CollisionObject colObj0 = (CollisionObject)collisionObjects.getQuick(i);
/* 146:146 */          if (((colObj0.getIslandTag() == islandId) || (colObj0.getIslandTag() == -1)) || (
/* 147:    */          
/* 150:150 */            (!$assertionsDisabled) && (colObj0.getIslandTag() != islandId) && (colObj0.getIslandTag() != -1))) throw new AssertionError();
/* 151:151 */          if (colObj0.getIslandTag() == islandId) {
/* 152:152 */            if (colObj0.getActivationState() == 1) {
/* 153:153 */              allSleeping = false;
/* 154:    */            }
/* 155:155 */            if (colObj0.getActivationState() == 4) {
/* 156:156 */              allSleeping = false;
/* 157:    */            }
/* 158:    */          }
/* 159:    */        }
/* 160:    */        
/* 162:162 */        if (allSleeping)
/* 163:    */        {
/* 164:164 */          for (idx = startIslandIndex; idx < endIslandIndex; idx++) {
/* 165:165 */            int i = getUnionFind().getElement(idx).sz;
/* 166:166 */            CollisionObject colObj0 = (CollisionObject)collisionObjects.getQuick(i);
/* 167:167 */            if (((colObj0.getIslandTag() == islandId) || (colObj0.getIslandTag() == -1)) || (
/* 168:    */            
/* 171:171 */              (!$assertionsDisabled) && (colObj0.getIslandTag() != islandId) && (colObj0.getIslandTag() != -1))) { throw new AssertionError();
/* 172:    */            }
/* 173:173 */            if (colObj0.getIslandTag() == islandId) {
/* 174:174 */              colObj0.setActivationState(2);
/* 175:    */            }
/* 176:    */          }
/* 177:    */        }
/* 178:    */        
/* 181:181 */        for (idx = startIslandIndex; idx < endIslandIndex; idx++) {
/* 182:182 */          int i = getUnionFind().getElement(idx).sz;
/* 183:    */          
/* 184:184 */          CollisionObject colObj0 = (CollisionObject)collisionObjects.getQuick(i);
/* 185:185 */          if (((colObj0.getIslandTag() == islandId) || (colObj0.getIslandTag() == -1)) || (
/* 186:    */          
/* 189:189 */            (!$assertionsDisabled) && (colObj0.getIslandTag() != islandId) && (colObj0.getIslandTag() != -1))) { throw new AssertionError();
/* 190:    */          }
/* 191:191 */          if ((colObj0.getIslandTag() == islandId) && 
/* 192:192 */            (colObj0.getActivationState() == 2)) {
/* 193:193 */            colObj0.setActivationState(3);
/* 194:    */          }
/* 195:    */        }
/* 196:    */      }
/* 197:    */      
/* 202:202 */      int maxNumManifolds = dispatcher.getNumManifolds();
/* 203:    */      
/* 208:208 */      for (int i = 0; i < maxNumManifolds; i++) {
/* 209:209 */        PersistentManifold manifold = dispatcher.getManifoldByIndexInternal(i);
/* 210:    */        
/* 211:211 */        CollisionObject colObj0 = (CollisionObject)manifold.getBody0();
/* 212:212 */        CollisionObject colObj1 = (CollisionObject)manifold.getBody1();
/* 213:    */        
/* 215:215 */        if (((colObj0 != null) && (colObj0.getActivationState() != 2)) || ((colObj1 != null) && (colObj1.getActivationState() != 2)))
/* 216:    */        {
/* 219:219 */          if ((colObj0.isKinematicObject()) && (colObj0.getActivationState() != 2)) {
/* 220:220 */            colObj1.activate();
/* 221:    */          }
/* 222:222 */          if ((colObj1.isKinematicObject()) && (colObj1.getActivationState() != 2)) {
/* 223:223 */            colObj0.activate();
/* 224:    */          }
/* 225:    */          
/* 227:227 */          if (dispatcher.needsResponse(colObj0, colObj1)) {
/* 228:228 */            this.islandmanifold.add(manifold);
/* 229:    */          }
/* 230:    */        }
/* 231:    */      }
/* 232:    */    }
/* 233:    */    finally
/* 234:    */    {
/* 235:235 */      BulletStats.popProfile();
/* 236:    */    }
/* 237:    */  }
/* 238:    */  
/* 239:    */  public void buildAndProcessIslands(Dispatcher dispatcher, ObjectArrayList<CollisionObject> collisionObjects, IslandCallback callback) {
/* 240:240 */    buildIslands(dispatcher, collisionObjects);
/* 241:    */    
/* 242:242 */    int endIslandIndex = 1;
/* 243:    */    
/* 244:244 */    int numElem = getUnionFind().getNumElements();
/* 245:    */    
/* 246:246 */    BulletStats.pushProfile("processIslands");
/* 247:    */    
/* 255:    */    try
/* 256:    */    {
/* 257:257 */      int numManifolds = this.islandmanifold.size();
/* 258:    */      
/* 264:264 */      MiscUtil.quickSort(this.islandmanifold, persistentManifoldComparator);
/* 265:    */      
/* 268:268 */      int startManifoldIndex = 0;
/* 269:269 */      int endManifoldIndex = 1;
/* 270:    */      
/* 276:276 */      for (int startIslandIndex = 0; startIslandIndex < numElem; startIslandIndex = endIslandIndex) {
/* 277:277 */        int islandId = getUnionFind().getElement(startIslandIndex).id;
/* 278:278 */        boolean islandSleeping = false;
/* 279:    */        
/* 280:280 */        for (endIslandIndex = startIslandIndex; (endIslandIndex < numElem) && (getUnionFind().getElement(endIslandIndex).id == islandId); endIslandIndex++) {
/* 281:281 */          int i = getUnionFind().getElement(endIslandIndex).sz;
/* 282:282 */          CollisionObject colObj0 = (CollisionObject)collisionObjects.getQuick(i);
/* 283:283 */          this.islandBodies.add(colObj0);
/* 284:284 */          if (!colObj0.isActive()) {
/* 285:285 */            islandSleeping = true;
/* 286:    */          }
/* 287:    */        }
/* 288:    */        
/* 291:291 */        int numIslandManifolds = 0;
/* 292:    */        
/* 293:293 */        int startManifold_idx = -1;
/* 294:    */        
/* 295:295 */        if (startManifoldIndex < numManifolds) {
/* 296:296 */          int curIslandId = getIslandId((PersistentManifold)this.islandmanifold.getQuick(startManifoldIndex));
/* 297:297 */          if (curIslandId == islandId)
/* 298:    */          {
/* 300:300 */            startManifold_idx = startManifoldIndex;
/* 301:    */            
/* 302:302 */            for (endManifoldIndex = startManifoldIndex + 1; (endManifoldIndex < numManifolds) && (islandId == getIslandId((PersistentManifold)this.islandmanifold.getQuick(endManifoldIndex))); endManifoldIndex++) {}
/* 303:    */            
/* 306:306 */            numIslandManifolds = endManifoldIndex - startManifoldIndex;
/* 307:    */          }
/* 308:    */        }
/* 309:    */        
/* 311:311 */        if (!islandSleeping) {
/* 312:312 */          callback.processIsland(this.islandBodies, this.islandBodies.size(), this.islandmanifold, startManifold_idx, numIslandManifolds, islandId);
/* 313:    */        }
/* 314:    */        
/* 316:316 */        if (numIslandManifolds != 0) {
/* 317:317 */          startManifoldIndex = endManifoldIndex;
/* 318:    */        }
/* 319:    */        
/* 320:320 */        this.islandBodies.clear();
/* 321:    */      }
/* 322:    */    }
/* 323:    */    finally
/* 324:    */    {
/* 325:325 */      BulletStats.popProfile();
/* 326:    */    }
/* 327:    */  }
/* 328:    */  
/* 335:335 */  private static final Comparator<PersistentManifold> persistentManifoldComparator = new Comparator() {
/* 336:    */    public int compare(PersistentManifold lhs, PersistentManifold rhs) {
/* 337:337 */      return SimulationIslandManager.getIslandId(lhs) < SimulationIslandManager.getIslandId(rhs) ? -1 : 1;
/* 338:    */    }
/* 339:    */  };
/* 340:    */  
/* 341:    */  public static abstract class IslandCallback
/* 342:    */  {
/* 343:    */    public abstract void processIsland(ObjectArrayList<CollisionObject> paramObjectArrayList, int paramInt1, ObjectArrayList<PersistentManifold> paramObjectArrayList1, int paramInt2, int paramInt3, int paramInt4);
/* 344:    */  }
/* 345:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.dispatch.SimulationIslandManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */