/*   1:    */package com.bulletphysics.collision.dispatch;
/*   2:    */
/*   3:    */import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
/*   4:    */import com.bulletphysics.collision.broadphase.BroadphasePair;
/*   5:    */import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
/*   6:    */import com.bulletphysics.collision.broadphase.CollisionAlgorithmConstructionInfo;
/*   7:    */import com.bulletphysics.collision.broadphase.Dispatcher;
/*   8:    */import com.bulletphysics.collision.broadphase.DispatcherInfo;
/*   9:    */import com.bulletphysics.collision.broadphase.OverlapCallback;
/*  10:    */import com.bulletphysics.collision.broadphase.OverlappingPairCache;
/*  11:    */import com.bulletphysics.collision.narrowphase.PersistentManifold;
/*  12:    */import com.bulletphysics.util.ObjectArrayList;
/*  13:    */import com.bulletphysics.util.ObjectPool;
/*  14:    */import java.io.PrintStream;
/*  15:    */import java.util.Collections;
/*  16:    */
/*  44:    */public class CollisionDispatcher
/*  45:    */  extends Dispatcher
/*  46:    */{
/*  47: 47 */  protected final ObjectPool<PersistentManifold> manifoldsPool = ObjectPool.get(PersistentManifold.class);
/*  48:    */  
/*  49: 49 */  private static final int MAX_BROADPHASE_COLLISION_TYPES = BroadphaseNativeType.MAX_BROADPHASE_COLLISION_TYPES.ordinal();
/*  50: 50 */  private int count = 0;
/*  51: 51 */  private final ObjectArrayList<PersistentManifold> manifoldsPtr = new ObjectArrayList();
/*  52: 52 */  private boolean useIslands = true;
/*  53: 53 */  private boolean staticWarningReported = false;
/*  54:    */  
/*  55:    */  private ManifoldResult defaultManifoldResult;
/*  56:    */  
/*  57:    */  private NearCallback nearCallback;
/*  58: 58 */  private final CollisionAlgorithmCreateFunc[][] doubleDispatch = new CollisionAlgorithmCreateFunc[MAX_BROADPHASE_COLLISION_TYPES][MAX_BROADPHASE_COLLISION_TYPES];
/*  59:    */  
/*  60:    */  private CollisionConfiguration collisionConfiguration;
/*  61:    */  
/*  62: 62 */  private CollisionAlgorithmConstructionInfo tmpCI = new CollisionAlgorithmConstructionInfo();
/*  63:    */  
/*  64:    */  public CollisionDispatcher(CollisionConfiguration collisionConfiguration) {
/*  65: 65 */    this.collisionConfiguration = collisionConfiguration;
/*  66:    */    
/*  67: 67 */    setNearCallback(new DefaultNearCallback());
/*  68:    */    
/*  72: 72 */    for (int i = 0; i < MAX_BROADPHASE_COLLISION_TYPES; i++) {
/*  73: 73 */      for (int j = 0; j < MAX_BROADPHASE_COLLISION_TYPES; j++) {
/*  74: 74 */        this.doubleDispatch[i][j] = collisionConfiguration.getCollisionAlgorithmCreateFunc(BroadphaseNativeType.forValue(i), BroadphaseNativeType.forValue(j));
/*  75:    */        
/*  78: 78 */        assert (this.doubleDispatch[i][j] != null);
/*  79:    */      }
/*  80:    */    }
/*  81:    */  }
/*  82:    */  
/*  83:    */  public void registerCollisionCreateFunc(int proxyType0, int proxyType1, CollisionAlgorithmCreateFunc createFunc) {
/*  84: 84 */    this.doubleDispatch[proxyType0][proxyType1] = createFunc;
/*  85:    */  }
/*  86:    */  
/*  87:    */  public NearCallback getNearCallback() {
/*  88: 88 */    return this.nearCallback;
/*  89:    */  }
/*  90:    */  
/*  91:    */  public void setNearCallback(NearCallback nearCallback) {
/*  92: 92 */    this.nearCallback = nearCallback;
/*  93:    */  }
/*  94:    */  
/*  95:    */  public CollisionConfiguration getCollisionConfiguration() {
/*  96: 96 */    return this.collisionConfiguration;
/*  97:    */  }
/*  98:    */  
/*  99:    */  public void setCollisionConfiguration(CollisionConfiguration collisionConfiguration) {
/* 100:100 */    this.collisionConfiguration = collisionConfiguration;
/* 101:    */  }
/* 102:    */  
/* 103:    */  public CollisionAlgorithm findAlgorithm(CollisionObject body0, CollisionObject body1, PersistentManifold sharedManifold)
/* 104:    */  {
/* 105:105 */    CollisionAlgorithmConstructionInfo ci = this.tmpCI;
/* 106:106 */    ci.dispatcher1 = this;
/* 107:107 */    ci.manifold = sharedManifold;
/* 108:108 */    CollisionAlgorithmCreateFunc createFunc = this.doubleDispatch[body0.getCollisionShape().getShapeType().ordinal()][body1.getCollisionShape().getShapeType().ordinal()];
/* 109:109 */    CollisionAlgorithm algo = createFunc.createCollisionAlgorithm(ci, body0, body1);
/* 110:110 */    algo.internalSetCreateFunc(createFunc);
/* 111:    */    
/* 112:112 */    return algo;
/* 113:    */  }
/* 114:    */  
/* 115:    */  public void freeCollisionAlgorithm(CollisionAlgorithm algo)
/* 116:    */  {
/* 117:117 */    CollisionAlgorithmCreateFunc createFunc = algo.internalGetCreateFunc();
/* 118:118 */    algo.internalSetCreateFunc(null);
/* 119:119 */    createFunc.releaseCollisionAlgorithm(algo);
/* 120:120 */    algo.destroy();
/* 121:    */  }
/* 122:    */  
/* 127:    */  public PersistentManifold getNewManifold(Object b0, Object b1)
/* 128:    */  {
/* 129:129 */    CollisionObject body0 = (CollisionObject)b0;
/* 130:130 */    CollisionObject body1 = (CollisionObject)b1;
/* 131:    */    
/* 148:148 */    PersistentManifold manifold = (PersistentManifold)this.manifoldsPool.get();
/* 149:149 */    manifold.init(body0, body1, 0);
/* 150:    */    
/* 151:151 */    manifold.index1a = this.manifoldsPtr.size();
/* 152:152 */    this.manifoldsPtr.add(manifold);
/* 153:    */    
/* 154:154 */    return manifold;
/* 155:    */  }
/* 156:    */  
/* 160:    */  public void releaseManifold(PersistentManifold manifold)
/* 161:    */  {
/* 162:162 */    clearManifold(manifold);
/* 163:    */    
/* 165:165 */    int findIndex = manifold.index1a;
/* 166:166 */    assert (findIndex < this.manifoldsPtr.size());
/* 167:167 */    Collections.swap(this.manifoldsPtr, findIndex, this.manifoldsPtr.size() - 1);
/* 168:168 */    ((PersistentManifold)this.manifoldsPtr.getQuick(findIndex)).index1a = findIndex;
/* 169:169 */    this.manifoldsPtr.removeQuick(this.manifoldsPtr.size() - 1);
/* 170:    */    
/* 171:171 */    this.manifoldsPool.release(manifold);
/* 172:    */  }
/* 173:    */  
/* 184:    */  public void clearManifold(PersistentManifold manifold)
/* 185:    */  {
/* 186:186 */    manifold.clearManifold();
/* 187:    */  }
/* 188:    */  
/* 189:    */  public boolean needsCollision(CollisionObject body0, CollisionObject body1)
/* 190:    */  {
/* 191:191 */    assert (body0 != null);
/* 192:192 */    assert (body1 != null);
/* 193:    */    
/* 194:194 */    boolean needsCollision = true;
/* 195:    */    
/* 197:197 */    if (!this.staticWarningReported)
/* 198:    */    {
/* 199:199 */      if (((body0.isStaticObject()) || (body0.isKinematicObject())) && ((body1.isStaticObject()) || (body1.isKinematicObject())))
/* 200:    */      {
/* 201:201 */        this.staticWarningReported = true;
/* 202:202 */        System.err.println("warning CollisionDispatcher.needsCollision: static-static collision!");
/* 203:    */      }
/* 204:    */    }
/* 205:    */    
/* 207:207 */    if ((!body0.isActive()) && (!body1.isActive())) {
/* 208:208 */      needsCollision = false;
/* 209:    */    }
/* 210:210 */    else if (!body0.checkCollideWith(body1)) {
/* 211:211 */      needsCollision = false;
/* 212:    */    }
/* 213:    */    
/* 214:214 */    return needsCollision;
/* 215:    */  }
/* 216:    */  
/* 218:    */  public boolean needsResponse(CollisionObject body0, CollisionObject body1)
/* 219:    */  {
/* 220:220 */    boolean hasResponse = (body0.hasContactResponse()) && (body1.hasContactResponse());
/* 221:    */    
/* 222:222 */    hasResponse = (hasResponse) && ((!body0.isStaticOrKinematicObject()) || (!body1.isStaticOrKinematicObject()));
/* 223:223 */    return hasResponse;
/* 224:    */  }
/* 225:    */  
/* 226:    */  private static class CollisionPairCallback extends OverlapCallback {
/* 227:    */    private DispatcherInfo dispatchInfo;
/* 228:    */    private CollisionDispatcher dispatcher;
/* 229:    */    
/* 230:    */    public void init(DispatcherInfo dispatchInfo, CollisionDispatcher dispatcher) {
/* 231:231 */      this.dispatchInfo = dispatchInfo;
/* 232:232 */      this.dispatcher = dispatcher;
/* 233:    */    }
/* 234:    */    
/* 235:    */    public boolean processOverlap(BroadphasePair pair) {
/* 236:236 */      this.dispatcher.getNearCallback().handleCollision(pair, this.dispatcher, this.dispatchInfo);
/* 237:237 */      return false;
/* 238:    */    }
/* 239:    */  }
/* 240:    */  
/* 241:241 */  private CollisionPairCallback collisionPairCallback = new CollisionPairCallback(null);
/* 242:    */  
/* 244:    */  public void dispatchAllCollisionPairs(OverlappingPairCache pairCache, DispatcherInfo dispatchInfo, Dispatcher dispatcher)
/* 245:    */  {
/* 246:246 */    this.collisionPairCallback.init(dispatchInfo, this);
/* 247:247 */    pairCache.processAllOverlappingPairs(this.collisionPairCallback, dispatcher);
/* 248:    */  }
/* 249:    */  
/* 251:    */  public int getNumManifolds()
/* 252:    */  {
/* 253:253 */    return this.manifoldsPtr.size();
/* 254:    */  }
/* 255:    */  
/* 256:    */  public PersistentManifold getManifoldByIndexInternal(int index)
/* 257:    */  {
/* 258:258 */    return (PersistentManifold)this.manifoldsPtr.getQuick(index);
/* 259:    */  }
/* 260:    */  
/* 261:    */  public ObjectArrayList<PersistentManifold> getInternalManifoldPointer()
/* 262:    */  {
/* 263:263 */    return this.manifoldsPtr;
/* 264:    */  }
/* 265:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.dispatch.CollisionDispatcher
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */