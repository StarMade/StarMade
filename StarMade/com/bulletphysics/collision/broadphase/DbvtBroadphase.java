/*   1:    */package com.bulletphysics.collision.broadphase;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.util.ObjectArrayList;
/*   5:    */import javax.vecmath.Vector3f;
/*   6:    */
/*  37:    */public class DbvtBroadphase
/*  38:    */  extends BroadphaseInterface
/*  39:    */{
/*  40:    */  public static final float DBVT_BP_MARGIN = 0.05F;
/*  41:    */  public static final int DYNAMIC_SET = 0;
/*  42:    */  public static final int FIXED_SET = 1;
/*  43:    */  public static final int STAGECOUNT = 2;
/*  44: 44 */  public final Dbvt[] sets = new Dbvt[2];
/*  45: 45 */  public DbvtProxy[] stageRoots = new DbvtProxy[3];
/*  46:    */  
/*  48:    */  public OverlappingPairCache paircache;
/*  49:    */  
/*  50:    */  public float predictedframes;
/*  51:    */  
/*  52:    */  public int stageCurrent;
/*  53:    */  
/*  54:    */  public int fupdates;
/*  55:    */  
/*  56:    */  public int dupdates;
/*  57:    */  
/*  58:    */  public int pid;
/*  59:    */  
/*  60:    */  public int gid;
/*  61:    */  
/*  62:    */  public boolean releasepaircache;
/*  63:    */  
/*  65:    */  public DbvtBroadphase()
/*  66:    */  {
/*  67: 67 */    this(null);
/*  68:    */  }
/*  69:    */  
/*  70:    */  public DbvtBroadphase(OverlappingPairCache paircache) {
/*  71: 71 */    this.sets[0] = new Dbvt();
/*  72: 72 */    this.sets[1] = new Dbvt();
/*  73:    */    
/*  75: 75 */    this.releasepaircache = (paircache == null);
/*  76: 76 */    this.predictedframes = 2.0F;
/*  77: 77 */    this.stageCurrent = 0;
/*  78: 78 */    this.fupdates = 1;
/*  79: 79 */    this.dupdates = 1;
/*  80: 80 */    this.paircache = (paircache != null ? paircache : new HashedOverlappingPairCache());
/*  81: 81 */    this.gid = 0;
/*  82: 82 */    this.pid = 0;
/*  83:    */    
/*  84: 84 */    for (int i = 0; i <= 2; i++) {
/*  85: 85 */      this.stageRoots[i] = null;
/*  86:    */    }
/*  87:    */  }
/*  88:    */  
/*  94:    */  public void collide(Dispatcher dispatcher)
/*  95:    */  {
/*  96: 96 */    this.sets[0].optimizeIncremental(1 + this.sets[0].leaves * this.dupdates / 100);
/*  97: 97 */    this.sets[1].optimizeIncremental(1 + this.sets[1].leaves * this.fupdates / 100);
/*  98:    */    
/* 100:100 */    this.stageCurrent = ((this.stageCurrent + 1) % 2);
/* 101:101 */    DbvtProxy current = this.stageRoots[this.stageCurrent];
/* 102:102 */    if (current != null) {
/* 103:103 */      DbvtTreeCollider collider = new DbvtTreeCollider(this);
/* 104:    */      do {
/* 105:105 */        DbvtProxy next = current.links[1];
/* 106:106 */        this.stageRoots[current.stage] = listremove(current, this.stageRoots[current.stage]);
/* 107:107 */        this.stageRoots[2] = listappend(current, this.stageRoots[2]);
/* 108:108 */        Dbvt.collideTT(this.sets[1].root, current.leaf, collider);
/* 109:109 */        this.sets[0].remove(current.leaf);
/* 110:110 */        current.leaf = this.sets[1].insert(current.aabb, current);
/* 111:111 */        current.stage = 2;
/* 112:112 */        current = next;
/* 113:113 */      } while (current != null);
/* 114:    */    }
/* 115:    */    
/* 118:118 */    DbvtTreeCollider collider = new DbvtTreeCollider(this);
/* 119:    */    
/* 121:121 */    Dbvt.collideTT(this.sets[0].root, this.sets[1].root, collider);
/* 122:    */    
/* 125:125 */    Dbvt.collideTT(this.sets[0].root, this.sets[0].root, collider);
/* 126:    */    
/* 132:132 */    ObjectArrayList<BroadphasePair> pairs = this.paircache.getOverlappingPairArray();
/* 133:133 */    if (pairs.size() > 0) {
/* 134:134 */      int i = 0; for (int ni = pairs.size(); i < ni; i++) {
/* 135:135 */        BroadphasePair p = (BroadphasePair)pairs.getQuick(i);
/* 136:136 */        DbvtProxy pa = (DbvtProxy)p.pProxy0;
/* 137:137 */        DbvtProxy pb = (DbvtProxy)p.pProxy1;
/* 138:138 */        if (!DbvtAabbMm.Intersect(pa.aabb, pb.aabb))
/* 139:    */        {
/* 140:140 */          if (pa.hashCode() > pb.hashCode()) {
/* 141:141 */            DbvtProxy tmp = pa;
/* 142:142 */            pa = pb;
/* 143:143 */            pb = tmp;
/* 144:    */          }
/* 145:145 */          this.paircache.removeOverlappingPair(pa, pb, dispatcher);
/* 146:146 */          ni--;
/* 147:147 */          i--;
/* 148:    */        }
/* 149:    */      }
/* 150:    */    }
/* 151:    */    
/* 152:152 */    this.pid += 1;
/* 153:    */  }
/* 154:    */  
/* 155:    */  private static DbvtProxy listappend(DbvtProxy item, DbvtProxy list) {
/* 156:156 */    item.links[0] = null;
/* 157:157 */    item.links[1] = list;
/* 158:158 */    if (list != null) list.links[0] = item;
/* 159:159 */    list = item;
/* 160:160 */    return list;
/* 161:    */  }
/* 162:    */  
/* 163:    */  private static DbvtProxy listremove(DbvtProxy item, DbvtProxy list) {
/* 164:164 */    if (item.links[0] != null) {
/* 165:165 */      item.links[0].links[1] = item.links[1];
/* 166:    */    }
/* 167:    */    else {
/* 168:168 */      list = item.links[1];
/* 169:    */    }
/* 170:    */    
/* 171:171 */    if (item.links[1] != null) {
/* 172:172 */      item.links[1].links[0] = item.links[0];
/* 173:    */    }
/* 174:174 */    return list;
/* 175:    */  }
/* 176:    */  
/* 177:    */  public BroadphaseProxy createProxy(Vector3f aabbMin, Vector3f aabbMax, BroadphaseNativeType shapeType, Object userPtr, short collisionFilterGroup, short collisionFilterMask, Dispatcher dispatcher, Object multiSapProxy) {
/* 178:178 */    DbvtProxy proxy = new DbvtProxy(userPtr, collisionFilterGroup, collisionFilterMask);
/* 179:179 */    DbvtAabbMm.FromMM(aabbMin, aabbMax, proxy.aabb);
/* 180:180 */    proxy.leaf = this.sets[0].insert(proxy.aabb, proxy);
/* 181:181 */    proxy.stage = this.stageCurrent;
/* 182:182 */    proxy.uniqueId = (++this.gid);
/* 183:183 */    this.stageRoots[this.stageCurrent] = listappend(proxy, this.stageRoots[this.stageCurrent]);
/* 184:184 */    return proxy;
/* 185:    */  }
/* 186:    */  
/* 187:    */  public void destroyProxy(BroadphaseProxy absproxy, Dispatcher dispatcher) {
/* 188:188 */    DbvtProxy proxy = (DbvtProxy)absproxy;
/* 189:189 */    if (proxy.stage == 2) {
/* 190:190 */      this.sets[1].remove(proxy.leaf);
/* 191:    */    }
/* 192:    */    else {
/* 193:193 */      this.sets[0].remove(proxy.leaf);
/* 194:    */    }
/* 195:195 */    this.stageRoots[proxy.stage] = listremove(proxy, this.stageRoots[proxy.stage]);
/* 196:196 */    this.paircache.removeOverlappingPairsContainingProxy(proxy, dispatcher);
/* 197:    */  }
/* 198:    */  
/* 199:    */  public void setAabb(BroadphaseProxy arg1, Vector3f arg2, Vector3f arg3, Dispatcher arg4)
/* 200:    */  {
/* 201:201 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();DbvtProxy proxy = (DbvtProxy)absproxy;
/* 202:202 */      DbvtAabbMm aabb = DbvtAabbMm.FromMM(aabbMin, aabbMax, new DbvtAabbMm());
/* 203:203 */      if (proxy.stage == 2)
/* 204:    */      {
/* 205:205 */        this.sets[1].remove(proxy.leaf);
/* 206:206 */        proxy.leaf = this.sets[0].insert(aabb, proxy);
/* 209:    */      }
/* 210:210 */      else if (DbvtAabbMm.Intersect(proxy.leaf.volume, aabb)) {
/* 211:211 */        Vector3f delta = localStack.get$javax$vecmath$Vector3f();
/* 212:212 */        delta.add(aabbMin, aabbMax);
/* 213:213 */        delta.scale(0.5F);
/* 214:214 */        delta.sub(proxy.aabb.Center(localStack.get$javax$vecmath$Vector3f()));
/* 215:    */        
/* 216:216 */        delta.scale(this.predictedframes);
/* 217:217 */        this.sets[0].update(proxy.leaf, aabb, delta, 0.05F);
/* 220:    */      }
/* 221:    */      else
/* 222:    */      {
/* 224:224 */        this.sets[0].update(proxy.leaf, aabb);
/* 225:    */      }
/* 226:    */      
/* 228:228 */      this.stageRoots[proxy.stage] = listremove(proxy, this.stageRoots[proxy.stage]);
/* 229:229 */      proxy.aabb.set(aabb);
/* 230:230 */      proxy.stage = this.stageCurrent;
/* 231:231 */      this.stageRoots[this.stageCurrent] = listappend(proxy, this.stageRoots[this.stageCurrent]);
/* 232:232 */    } finally { localStack.pop$javax$vecmath$Vector3f();
/* 233:    */    } }
/* 234:    */  
/* 235:235 */  public void calculateOverlappingPairs(Dispatcher dispatcher) { collide(dispatcher); }
/* 236:    */  
/* 259:    */  public OverlappingPairCache getOverlappingPairCache()
/* 260:    */  {
/* 261:261 */    return this.paircache;
/* 262:    */  }
/* 263:    */  
/* 264:    */  public void getBroadphaseAabb(Vector3f aabbMin, Vector3f aabbMax) {
/* 265:265 */    DbvtAabbMm bounds = new DbvtAabbMm();
/* 266:266 */    if (!this.sets[0].empty()) {
/* 267:267 */      if (!this.sets[1].empty()) {
/* 268:268 */        DbvtAabbMm.Merge(this.sets[0].root.volume, this.sets[1].root.volume, bounds);
/* 269:    */      }
/* 270:    */      else {
/* 271:271 */        bounds.set(this.sets[0].root.volume);
/* 272:    */      }
/* 273:    */    }
/* 274:274 */    else if (!this.sets[1].empty()) {
/* 275:275 */      bounds.set(this.sets[1].root.volume);
/* 276:    */    }
/* 277:    */    else {
/* 278:278 */      DbvtAabbMm.FromCR(new Vector3f(0.0F, 0.0F, 0.0F), 0.0F, bounds);
/* 279:    */    }
/* 280:280 */    aabbMin.set(bounds.Mins());
/* 281:281 */    aabbMax.set(bounds.Maxs());
/* 282:    */  }
/* 283:    */  
/* 284:    */  public void printStats() {}
/* 285:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.broadphase.DbvtBroadphase
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */