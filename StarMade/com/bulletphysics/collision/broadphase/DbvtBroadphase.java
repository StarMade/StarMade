/*     */ package com.bulletphysics.collision.broadphase;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.util.ObjectArrayList;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class DbvtBroadphase extends BroadphaseInterface
/*     */ {
/*     */   public static final float DBVT_BP_MARGIN = 0.05F;
/*     */   public static final int DYNAMIC_SET = 0;
/*     */   public static final int FIXED_SET = 1;
/*     */   public static final int STAGECOUNT = 2;
/*  44 */   public final Dbvt[] sets = new Dbvt[2];
/*  45 */   public DbvtProxy[] stageRoots = new DbvtProxy[3];
/*     */   public OverlappingPairCache paircache;
/*     */   public float predictedframes;
/*     */   public int stageCurrent;
/*     */   public int fupdates;
/*     */   public int dupdates;
/*     */   public int pid;
/*     */   public int gid;
/*     */   public boolean releasepaircache;
/*     */ 
/*     */   public DbvtBroadphase()
/*     */   {
/*  67 */     this(null);
/*     */   }
/*     */ 
/*     */   public DbvtBroadphase(OverlappingPairCache paircache) {
/*  71 */     this.sets[0] = new Dbvt();
/*  72 */     this.sets[1] = new Dbvt();
/*     */ 
/*  75 */     this.releasepaircache = (paircache == null);
/*  76 */     this.predictedframes = 2.0F;
/*  77 */     this.stageCurrent = 0;
/*  78 */     this.fupdates = 1;
/*  79 */     this.dupdates = 1;
/*  80 */     this.paircache = (paircache != null ? paircache : new HashedOverlappingPairCache());
/*  81 */     this.gid = 0;
/*  82 */     this.pid = 0;
/*     */ 
/*  84 */     for (int i = 0; i <= 2; i++)
/*  85 */       this.stageRoots[i] = null;
/*     */   }
/*     */ 
/*     */   public void collide(Dispatcher dispatcher)
/*     */   {
/*  96 */     this.sets[0].optimizeIncremental(1 + this.sets[0].leaves * this.dupdates / 100);
/*  97 */     this.sets[1].optimizeIncremental(1 + this.sets[1].leaves * this.fupdates / 100);
/*     */ 
/* 100 */     this.stageCurrent = ((this.stageCurrent + 1) % 2);
/* 101 */     DbvtProxy current = this.stageRoots[this.stageCurrent];
/* 102 */     if (current != null) {
/* 103 */       DbvtTreeCollider collider = new DbvtTreeCollider(this);
/*     */       do {
/* 105 */         DbvtProxy next = current.links[1];
/* 106 */         this.stageRoots[current.stage] = listremove(current, this.stageRoots[current.stage]);
/* 107 */         this.stageRoots[2] = listappend(current, this.stageRoots[2]);
/* 108 */         Dbvt.collideTT(this.sets[1].root, current.leaf, collider);
/* 109 */         this.sets[0].remove(current.leaf);
/* 110 */         current.leaf = this.sets[1].insert(current.aabb, current);
/* 111 */         current.stage = 2;
/* 112 */         current = next;
/* 113 */       }while (current != null);
/*     */     }
/*     */ 
/* 118 */     DbvtTreeCollider collider = new DbvtTreeCollider(this);
/*     */ 
/* 121 */     Dbvt.collideTT(this.sets[0].root, this.sets[1].root, collider);
/*     */ 
/* 125 */     Dbvt.collideTT(this.sets[0].root, this.sets[0].root, collider);
/*     */ 
/* 132 */     ObjectArrayList pairs = this.paircache.getOverlappingPairArray();
/* 133 */     if (pairs.size() > 0) {
/* 134 */       int i = 0; for (int ni = pairs.size(); i < ni; i++) {
/* 135 */         BroadphasePair p = (BroadphasePair)pairs.getQuick(i);
/* 136 */         DbvtProxy pa = (DbvtProxy)p.pProxy0;
/* 137 */         DbvtProxy pb = (DbvtProxy)p.pProxy1;
/* 138 */         if (!DbvtAabbMm.Intersect(pa.aabb, pb.aabb))
/*     */         {
/* 140 */           if (pa.hashCode() > pb.hashCode()) {
/* 141 */             DbvtProxy tmp = pa;
/* 142 */             pa = pb;
/* 143 */             pb = tmp;
/*     */           }
/* 145 */           this.paircache.removeOverlappingPair(pa, pb, dispatcher);
/* 146 */           ni--;
/* 147 */           i--;
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 152 */     this.pid += 1;
/*     */   }
/*     */ 
/*     */   private static DbvtProxy listappend(DbvtProxy item, DbvtProxy list) {
/* 156 */     item.links[0] = null;
/* 157 */     item.links[1] = list;
/* 158 */     if (list != null) list.links[0] = item;
/* 159 */     list = item;
/* 160 */     return list;
/*     */   }
/*     */ 
/*     */   private static DbvtProxy listremove(DbvtProxy item, DbvtProxy list) {
/* 164 */     if (item.links[0] != null) {
/* 165 */       item.links[0].links[1] = item.links[1];
/*     */     }
/*     */     else {
/* 168 */       list = item.links[1];
/*     */     }
/*     */ 
/* 171 */     if (item.links[1] != null) {
/* 172 */       item.links[1].links[0] = item.links[0];
/*     */     }
/* 174 */     return list;
/*     */   }
/*     */ 
/*     */   public BroadphaseProxy createProxy(Vector3f aabbMin, Vector3f aabbMax, BroadphaseNativeType shapeType, Object userPtr, short collisionFilterGroup, short collisionFilterMask, Dispatcher dispatcher, Object multiSapProxy) {
/* 178 */     DbvtProxy proxy = new DbvtProxy(userPtr, collisionFilterGroup, collisionFilterMask);
/* 179 */     DbvtAabbMm.FromMM(aabbMin, aabbMax, proxy.aabb);
/* 180 */     proxy.leaf = this.sets[0].insert(proxy.aabb, proxy);
/* 181 */     proxy.stage = this.stageCurrent;
/* 182 */     proxy.uniqueId = (++this.gid);
/* 183 */     this.stageRoots[this.stageCurrent] = listappend(proxy, this.stageRoots[this.stageCurrent]);
/* 184 */     return proxy;
/*     */   }
/*     */ 
/*     */   public void destroyProxy(BroadphaseProxy absproxy, Dispatcher dispatcher) {
/* 188 */     DbvtProxy proxy = (DbvtProxy)absproxy;
/* 189 */     if (proxy.stage == 2) {
/* 190 */       this.sets[1].remove(proxy.leaf);
/*     */     }
/*     */     else {
/* 193 */       this.sets[0].remove(proxy.leaf);
/*     */     }
/* 195 */     this.stageRoots[proxy.stage] = listremove(proxy, this.stageRoots[proxy.stage]);
/* 196 */     this.paircache.removeOverlappingPairsContainingProxy(proxy, dispatcher);
/*     */   }
/*     */ 
/*     */   public void setAabb(BroadphaseProxy arg1, Vector3f arg2, Vector3f arg3, Dispatcher arg4)
/*     */   {
/* 201 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); DbvtProxy proxy = (DbvtProxy)absproxy;
/* 202 */       DbvtAabbMm aabb = DbvtAabbMm.FromMM(aabbMin, aabbMax, new DbvtAabbMm());
/* 203 */       if (proxy.stage == 2)
/*     */       {
/* 205 */         this.sets[1].remove(proxy.leaf);
/* 206 */         proxy.leaf = this.sets[0].insert(aabb, proxy);
/*     */       }
/* 210 */       else if (DbvtAabbMm.Intersect(proxy.leaf.volume, aabb)) {
/* 211 */         Vector3f delta = localStack.get$javax$vecmath$Vector3f();
/* 212 */         delta.add(aabbMin, aabbMax);
/* 213 */         delta.scale(0.5F);
/* 214 */         delta.sub(proxy.aabb.Center(localStack.get$javax$vecmath$Vector3f()));
/*     */ 
/* 216 */         delta.scale(this.predictedframes);
/* 217 */         this.sets[0].update(proxy.leaf, aabb, delta, 0.05F);
/*     */       }
/*     */       else
/*     */       {
/* 224 */         this.sets[0].update(proxy.leaf, aabb); } 
/*     */ this.stageRoots[proxy.stage] = listremove(proxy, this.stageRoots[proxy.stage]);
/* 229 */       proxy.aabb.set(aabb);
/* 230 */       proxy.stage = this.stageCurrent;
/* 231 */       this.stageRoots[this.stageCurrent] = listappend(proxy, this.stageRoots[this.stageCurrent]);
/*     */       return; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */   public void calculateOverlappingPairs(Dispatcher dispatcher) {
/* 235 */     collide(dispatcher);
/*     */   }
/*     */ 
/*     */   public OverlappingPairCache getOverlappingPairCache()
/*     */   {
/* 261 */     return this.paircache;
/*     */   }
/*     */ 
/*     */   public void getBroadphaseAabb(Vector3f aabbMin, Vector3f aabbMax) {
/* 265 */     DbvtAabbMm bounds = new DbvtAabbMm();
/* 266 */     if (!this.sets[0].empty()) {
/* 267 */       if (!this.sets[1].empty()) {
/* 268 */         DbvtAabbMm.Merge(this.sets[0].root.volume, this.sets[1].root.volume, bounds);
/*     */       }
/*     */       else {
/* 271 */         bounds.set(this.sets[0].root.volume);
/*     */       }
/*     */     }
/* 274 */     else if (!this.sets[1].empty()) {
/* 275 */       bounds.set(this.sets[1].root.volume);
/*     */     }
/*     */     else {
/* 278 */       DbvtAabbMm.FromCR(new Vector3f(0.0F, 0.0F, 0.0F), 0.0F, bounds);
/*     */     }
/* 280 */     aabbMin.set(bounds.Mins());
/* 281 */     aabbMax.set(bounds.Maxs());
/*     */   }
/*     */ 
/*     */   public void printStats()
/*     */   {
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.broadphase.DbvtBroadphase
 * JD-Core Version:    0.6.2
 */