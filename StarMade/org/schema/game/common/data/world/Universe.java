/*     */ package org.schema.game.common.data.world;
/*     */ 
/*     */ import Ad;
/*     */ import Ag;
/*     */ import com.bulletphysics.dynamics.DynamicsWorld;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectArrayFIFOQueue;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
/*     */ import jA;
/*     */ import jF;
/*     */ import jL;
/*     */ import jY;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import java.util.concurrent.ThreadPoolExecutor;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import javax.vecmath.Matrix3f;
/*     */ import javax.vecmath.Tuple3f;
/*     */ import javax.vecmath.Vector3f;
/*     */ import jv;
/*     */ import jy;
/*     */ import ka;
/*     */ import kd;
/*     */ import kf;
/*     */ import ki;
/*     */ import kl;
/*     */ import kw;
/*     */ import lD;
/*     */ import lE;
/*     */ import lT;
/*     */ import lg;
/*     */ import mF;
/*     */ import mI;
/*     */ import mJ;
/*     */ import mv;
/*     */ import mx;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.controller.database.DatabaseIndex;
/*     */ import org.schema.game.common.data.element.Element;
/*     */ import org.schema.game.common.data.physics.PairCachingGhostObjectAlignable;
/*     */ import org.schema.game.common.data.physics.PhysicsExt;
/*     */ import org.schema.game.server.controller.GameServerController;
/*     */ import org.schema.schine.network.NetworkStateContainer;
/*     */ import org.schema.schine.network.SynchronizationContainerController;
/*     */ import org.schema.schine.network.objects.Sendable;
/*     */ import org.schema.schine.network.objects.container.PhysicsDataContainer;
/*     */ import org.schema.schine.network.server.ServerEntityWriterThread;
/*     */ import q;
/*     */ import vR;
/*     */ import vg;
/*     */ import vo;
/*     */ import xq;
/*     */ import zM;
/*     */ import zS;
/*     */ 
/*     */ public class Universe
/*     */   implements Ag
/*     */ {
/*     */   public static final int SECTOR_GENERATION_LENGTH = 5;
/* 195 */   private final HashMap starSystemMap = new HashMap();
/*     */   public static final float SECTOR_MARGIN = 300.0F;
/*     */   private String name;
/*     */   private final Int2ObjectOpenHashMap sectors;
/*     */   private final HashMap sectorPositions;
/*     */   private final vg state;
/* 205 */   Vector3f tmpObjectPos = new Vector3f();
/* 206 */   q belogingVector = new q();
/*     */   private static Random random;
/* 210 */   private final ObjectArrayFIFOQueue toClear = new ObjectArrayFIFOQueue();
/* 211 */   private ObjectOpenHashSet inactiveWrittenSectors = new ObjectOpenHashSet();
/* 212 */   private ObjectOpenHashSet entityCleaningSectors = new ObjectOpenHashSet();
/*     */ 
/* 214 */   private final ArrayList physicsRepository = new ArrayList();
/*     */ 
/* 615 */   private final q where = new q();
/*     */   private static final int MAX_PHYSICS_REPOSITORY_SIZE = 30;
/*     */   private long lastPing;
/*     */ 
/*     */   public static Random getRandom()
/*     */   {
/*  68 */     return random;
/*     */   }
/*     */   public static int getSectorSizeWithMargin() {
/*  71 */     return 1300;
/*     */   }
/*     */ 
/*     */   private static String getVoidSystemFileName(q paramq)
/*     */   {
/*  78 */     return "VOIDSYSTEM_" + paramq.jdField_a_of_type_Int + "_" + paramq.jdField_b_of_type_Int + "_" + paramq.c + ".ssys";
/*     */   }
/*     */ 
/*     */   public static Sendable loadEntity(vg paramvg, kw paramkw, mx parammx) {
/*  82 */     if (paramkw.jdField_a_of_type_Int == 3)
/*     */     {
/*     */       jy localjy;
/*  83 */       (
/*  84 */         localjy = new jy(paramvg))
/*  84 */         .setId(paramvg.getNextFreeObjectId());
/*  85 */       localjy.setSectorId(parammx.a());
/*  86 */       localjy.initialize();
/*  87 */       localjy.getMinPos().b(paramkw.jdField_b_of_type_Q);
/*  88 */       localjy.getMaxPos().b(paramkw.jdField_c_of_type_Q);
/*  89 */       localjy.a(paramkw.jdField_a_of_type_Boolean);
/*  90 */       localjy.setRealName(paramkw.d);
/*  91 */       localjy.setFactionId(paramkw.jdField_b_of_type_Int);
/*  92 */       localjy.setLastModified(paramkw.jdField_b_of_type_JavaLangString);
/*  93 */       localjy.setSpawner(paramkw.jdField_c_of_type_JavaLangString);
/*  94 */       localjy.setUniqueIdentifier(paramkw.jdField_a_of_type_JavaLangString);
/*  95 */       localjy.setMass(0.0F);
/*     */ 
/*  97 */       localjy.getRemoteTransformable().a().setIdentity();
/*  98 */       localjy.getRemoteTransformable().a().origin.set(paramkw.jdField_a_of_type_JavaxVecmathVector3f);
/*  99 */       localjy.getRemoteTransformable().getWorldTransform().setIdentity();
/* 100 */       localjy.getRemoteTransformable().getWorldTransform().origin.set(paramkw.jdField_a_of_type_JavaxVecmathVector3f);
/* 101 */       if (paramkw.jdField_a_of_type_Long == 0L)
/* 102 */         localjy.setSeed(getRandom().nextLong());
/*     */       else {
/* 104 */         localjy.setSeed(paramkw.jdField_a_of_type_Long);
/*     */       }
/*     */ 
/* 108 */       paramvg.a().a().addNewSynchronizedObjectQueued(localjy);
/*     */ 
/* 112 */       return localjy;
/*     */     }
/* 114 */     if (!$assertionsDisabled) throw new AssertionError(paramkw);
/* 115 */     return null;
/*     */   }
/*     */ 
/*     */   public static Sendable loadEntity(vg paramvg, String paramString, File paramFile, mx parammx)
/*     */   {
/*     */     Object localObject;
/* 120 */     if (paramString.equals("SHIP")) {
/* 121 */       paramString = paramvg.a().a(paramFile.getName(), "");
/* 122 */       (
/* 123 */         localObject = new kd(paramvg))
/* 123 */         .setId(paramvg.getNextFreeObjectId());
/* 124 */       ((kd)localObject).setSectorId(parammx.a());
/* 125 */       ((kd)localObject).initialize();
/* 126 */       System.err.println("adding loaded object from disk: " + paramFile.getName() + " in sector " + parammx.jdField_a_of_type_Q);
/* 127 */       ((kd)localObject).fromTagStructure(paramString);
/* 128 */       System.err.println("added loaded object from disk: " + localObject + " from " + paramFile.getName());
/*     */ 
/* 130 */       paramvg.a().a().addNewSynchronizedObjectQueued((Sendable)localObject);
/* 131 */       return localObject;
/* 132 */     }if (paramString.equals("SPACEOBJECT")) {
/* 133 */       System.err.println("found existing playerstate! ");
/* 134 */       throw new IllegalArgumentException();
/*     */     }
/* 136 */     if (paramString.equals("FLOATINGROCK")) {
/* 137 */       paramString = paramvg.a().a(paramFile.getName(), "");
/* 138 */       (
/* 139 */         localObject = new jy(paramvg))
/* 139 */         .setId(paramvg.getNextFreeObjectId());
/* 140 */       ((jy)localObject).setSectorId(parammx.a());
/* 141 */       ((jy)localObject).initialize();
/* 142 */       ((jy)localObject).fromTagStructure(paramString);
/*     */ 
/* 144 */       paramvg.a().a().addNewSynchronizedObjectQueued((Sendable)localObject);
/* 145 */       return localObject;
/*     */     }
/* 147 */     if (paramString.equals("SHOP")) {
/* 148 */       paramString = paramvg.a().a(paramFile.getName(), "");
/* 149 */       (
/* 150 */         localObject = new kf(paramvg))
/* 150 */         .setId(paramvg.getNextFreeObjectId());
/* 151 */       ((kf)localObject).setSectorId(parammx.a());
/* 152 */       ((kf)localObject).initialize();
/* 153 */       ((kf)localObject).fromTagStructure(paramString);
/*     */ 
/* 155 */       paramvg.a().a().addNewSynchronizedObjectQueued((Sendable)localObject);
/* 156 */       return localObject;
/*     */     }
/* 158 */     if (paramString.equals("SPACESTATION")) {
/* 159 */       paramString = paramvg.a().a(paramFile.getName(), "");
/* 160 */       (
/* 161 */         localObject = new ki(paramvg))
/* 161 */         .setId(paramvg.getNextFreeObjectId());
/* 162 */       ((ki)localObject).setSectorId(parammx.a());
/* 163 */       ((ki)localObject).initialize();
/* 164 */       ((ki)localObject).fromTagStructure(paramString);
/* 165 */       System.err.println("[UNIVERSE] adding loaded object from disk: " + localObject);
/* 166 */       paramvg.a().a().addNewSynchronizedObjectQueued((Sendable)localObject);
/* 167 */       return localObject;
/*     */     }
/* 169 */     if (paramString.equals("PLANET")) {
/* 170 */       paramString = paramvg.a().a(paramFile.getName(), "");
/* 171 */       (
/* 172 */         localObject = new jA(paramvg))
/* 172 */         .setId(paramvg.getNextFreeObjectId());
/* 173 */       ((jA)localObject).setSectorId(parammx.a());
/* 174 */       ((jA)localObject).initialize();
/* 175 */       ((jA)localObject).fromTagStructure(paramString);
/*     */ 
/* 177 */       paramvg.a().a().addNewSynchronizedObjectQueued((Sendable)localObject);
/* 178 */       return localObject;
/*     */     }
/* 180 */     if (paramString.equals("DEATHSTAR")) {
/* 181 */       paramString = paramvg.a().a(paramFile.getName(), "");
/* 182 */       (
/* 183 */         localObject = new kl(paramvg))
/* 183 */         .setId(paramvg.getNextFreeObjectId());
/* 184 */       ((kl)localObject).setSectorId(parammx.a());
/* 185 */       ((kl)localObject).initialize();
/* 186 */       ((kl)localObject).fromTagStructure(paramString);
/*     */ 
/* 188 */       paramvg.a().a().addNewSynchronizedObjectQueued((Sendable)localObject);
/* 189 */       return localObject;
/*     */     }
/* 191 */     System.err.println("[LOADENTITY] WARNING: type not known: " + paramString);
/* 192 */     return null;
/*     */   }
/*     */ 
/*     */   public Universe(vg paramvg, long paramLong)
/*     */   {
/* 217 */     this.sectors = new Int2ObjectOpenHashMap();
/* 218 */     this.sectorPositions = new HashMap();
/* 219 */     this.state = paramvg;
/*     */ 
/* 221 */     random = new Random(System.currentTimeMillis());
/*     */   }
/*     */ 
/*     */   private mx addNewSector(q paramq)
/*     */   {
/*     */     mx localmx;
/* 225 */     (
/* 226 */       localmx = new mx(this.state)).jdField_a_of_type_Q = 
/* 226 */       new q(paramq);
/*     */ 
/* 230 */     addSector(localmx);
/* 231 */     localmx.b(this.state);
/*     */ 
/* 233 */     return localmx;
/*     */   }
/*     */ 
/*     */   public void addSector(mx parammx)
/*     */   {
/* 238 */     assert (parammx != null);
/* 239 */     this.sectors.put(parammx.a(), parammx);
/* 240 */     parammx.f();
/* 241 */     this.sectorPositions.put(parammx.jdField_a_of_type_Q, parammx);
/*     */   }
/*     */   public void addToClear(ka paramka) {
/* 244 */     synchronized (this.toClear) {
/* 245 */       this.toClear.enqueue(paramka);
/* 246 */       return;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void fromTagStructure(Ad paramAd)
/*     */   {
/*     */   }
/*     */ 
/*     */   public boolean getIsSectorActive(int paramInt) {
/* 255 */     return ((
/* 255 */       paramInt = (mx)this.sectors.get(paramInt)) != null) && 
/* 255 */       (paramInt.a());
/*     */   }
/*     */ 
/*     */   public boolean existsSector(int paramInt) {
/* 259 */     return (mx)this.sectors.get(paramInt) != null;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 265 */     return this.name;
/*     */   }
/*     */   public mx getSector(int paramInt) {
/* 268 */     return (mx)this.sectors.get(paramInt);
/*     */   }
/*     */   public mx getSectorWithoutLoading(q paramq) {
/* 271 */     return (mx)this.sectorPositions.get(paramq);
/*     */   }
/*     */ 
/*     */   public mx getSector(q paramq) {
/* 275 */     if (!this.sectorPositions.containsKey(paramq))
/* 276 */       loadOrGenerateSector(paramq);
/*     */     mx localmx;
/* 279 */     if ((!(
/* 279 */       localmx = (mx)this.sectorPositions.get(paramq))
/* 279 */       .a()) && ((this.entityCleaningSectors.contains(localmx)) || (this.inactiveWrittenSectors.contains(localmx)))) {
/* 280 */       loadOrGenerateSector(paramq);
/*     */     }
/* 282 */     if (!this.sectors.containsKey(localmx.a())) {
/* 283 */       assert (localmx != null);
/*     */ 
/* 285 */       this.sectors.put(localmx.a(), localmx);
/*     */     }
/* 287 */     assert (localmx != null) : (paramq + " - " + this.sectorPositions);
/* 288 */     localmx.a(true);
/* 289 */     return localmx;
/*     */   }
/*     */ 
/*     */   public void updateProximitySectorInformation(q paramq)
/*     */   {
/*     */     q[] arrayOfq;
/* 292 */     (
/* 293 */       arrayOfq = new q[8])[
/* 293 */       0] = new q(paramq.jdField_a_of_type_Int + 8, paramq.jdField_b_of_type_Int + 8, paramq.c + 8);
/* 294 */     arrayOfq[1] = new q(paramq.jdField_a_of_type_Int - 8, paramq.jdField_b_of_type_Int + 8, paramq.c + 8);
/* 295 */     arrayOfq[2] = new q(paramq.jdField_a_of_type_Int + 8, paramq.jdField_b_of_type_Int - 8, paramq.c + 8);
/* 296 */     arrayOfq[3] = new q(paramq.jdField_a_of_type_Int - 8, paramq.jdField_b_of_type_Int - 8, paramq.c + 8);
/* 297 */     arrayOfq[4] = new q(paramq.jdField_a_of_type_Int + 8, paramq.jdField_b_of_type_Int + 8, paramq.c - 8);
/* 298 */     arrayOfq[5] = new q(paramq.jdField_a_of_type_Int - 8, paramq.jdField_b_of_type_Int + 8, paramq.c - 8);
/* 299 */     arrayOfq[6] = new q(paramq.jdField_a_of_type_Int + 8, paramq.jdField_b_of_type_Int - 8, paramq.c - 8);
/* 300 */     arrayOfq[7] = new q(paramq.jdField_a_of_type_Int - 8, paramq.jdField_b_of_type_Int - 8, paramq.c - 8);
/*     */ 
/* 302 */     for (paramq = 0; paramq < arrayOfq.length; paramq++)
/* 303 */       getStellarSystemFromSecPos(arrayOfq[paramq]);
/*     */   }
/*     */ 
/*     */   public Collection getSectorSet()
/*     */   {
/* 318 */     return this.sectors.values();
/*     */   }
/*     */ 
/*     */   public mI getStellarSystemFromSecPos(q paramq) {
/* 322 */     paramq = mJ.a(paramq, new q());
/*     */     mJ localmJ;
/* 326 */     if ((
/* 326 */       localmJ = (mJ)this.starSystemMap.get(paramq)) == null)
/*     */     {
/* 327 */       localmJ = loadOrGenerateVoidSystem(paramq);
/* 328 */       this.starSystemMap.put(localmJ.jdField_a_of_type_Q, localmJ);
/*     */     }
/*     */ 
/* 331 */     return localmJ;
/*     */   }
/*     */ 
/*     */   public q getSectorBelonging(mF parammF)
/*     */   {
/* 336 */     for (int i = 0; i < Element.DIRECTIONSi.length; i++) {
/* 337 */       parammF.surround[i].b(getSector(parammF.getSectorId()).jdField_a_of_type_Q, Element.DIRECTIONSi[i]);
/*     */     }
/*     */ 
/* 340 */     if (parammF.isHidden())
/*     */     {
/* 344 */       return getSector(parammF.getSectorId()).jdField_a_of_type_Q;
/*     */     }
/* 346 */     if (((parammF instanceof SegmentController)) && (((SegmentController)parammF).getDockingController().b()))
/*     */     {
/* 350 */       return getSector(parammF.getSectorId()).jdField_a_of_type_Q;
/*     */     }
/* 352 */     if (((parammF instanceof lD)) && (((PairCachingGhostObjectAlignable)((lD)parammF).getPhysicsDataContainer().getObject()).getAttached() != null))
/* 353 */       return getSector(parammF.getSectorId()).jdField_a_of_type_Q;
/*     */     mx localmx;
/* 357 */     q localq = (
/* 357 */       localmx = getSector(parammF.getSectorId())).jdField_a_of_type_Q;
/*     */ 
/* 358 */     int j = -1;
/* 359 */     Vector3f localVector3f = new Vector3f(parammF.getWorldTransform().origin);
/* 360 */     boolean bool = mI.a(localmx.jdField_a_of_type_Q);
/* 361 */     for (int k = 0; k < Element.DIRECTIONSi.length; k++)
/*     */     {
/*     */       Object localObject;
/* 362 */       (
/* 363 */         localObject = new q(Element.DIRECTIONSi[k]))
/* 363 */         .a(localq);
/*     */       Transform localTransform;
/* 364 */       (
/* 365 */         localTransform = new Transform())
/* 365 */         .setIdentity();
/*     */ 
/* 367 */       if (bool) {
/* 368 */         calcSecPos(localq, (q)localObject, this.state.a().calculateStartTime(), System.currentTimeMillis(), localTransform);
/*     */       } else {
/* 370 */         localTransform.origin.set(Element.DIRECTIONSi[k].jdField_a_of_type_Int, Element.DIRECTIONSi[k].jdField_b_of_type_Int, Element.DIRECTIONSi[k].c);
/* 371 */         localTransform.origin.scale(getSectorSizeWithMargin());
/*     */       }
/*     */ 
/* 374 */       (
/* 377 */         localObject = new Vector3f())
/* 377 */         .sub(parammF.getWorldTransform().origin, localTransform.origin);
/*     */ 
/* 381 */       if (((Vector3f)localObject).lengthSquared() < localVector3f.lengthSquared()) {
/* 382 */         localVector3f.set((Tuple3f)localObject);
/* 383 */         j = k;
/*     */       }
/*     */     }
/*     */ 
/* 387 */     if (j >= 0) {
/* 388 */       this.belogingVector.b(localq);
/* 389 */       this.belogingVector.a(Element.DIRECTIONSi[j]);
/*     */ 
/* 391 */       this.state.a().a(parammF, this.belogingVector, 0);
/*     */ 
/* 394 */       return this.belogingVector;
/*     */     }
/* 396 */     return localq;
/*     */   }
/*     */ 
/*     */   public static void calcSunPosInnerStarSystem(q paramq, mI parammI, long paramLong, Transform paramTransform)
/*     */   {
/* 401 */     paramLong = (float)((System.currentTimeMillis() - paramLong) % 1200000L) / 
/* 401 */       1200000.0F;
/* 402 */     paramTransform.basis.rotX(6.283186F * paramLong);
/*     */ 
/* 404 */     paramTransform.origin.set((parammI.jdField_a_of_type_Q.jdField_a_of_type_Int << 4) + 8 - paramq.jdField_a_of_type_Int, (parammI.jdField_a_of_type_Q.jdField_b_of_type_Int << 4) + 8 - paramq.jdField_b_of_type_Int, (parammI.jdField_a_of_type_Q.c << 4) + 8 - paramq.c);
/*     */ 
/* 408 */     paramTransform.origin.scale(getSectorSizeWithMargin());
/*     */ 
/* 410 */     paramTransform.basis.transform(paramTransform.origin);
/*     */   }
/*     */ 
/*     */   public static void calcSecPos(q paramq1, q paramq2, long paramLong1, long paramLong2, Transform paramTransform) {
/* 414 */     q localq1 = mJ.a(paramq2, new q());
/*     */     q localq2;
/* 416 */     (
/* 417 */       localq2 = new q())
/* 417 */       .a(paramq2, paramq1);
/*     */ 
/* 419 */     if (!mI.a(paramq1)) {
/* 420 */       paramLong1 = 0L;
/* 421 */       paramLong2 = 0L;
/*     */     }
/*     */ 
/* 424 */     paramq2 = (float)((paramLong2 - paramLong1) % 1200000L) / 
/* 424 */       1200000.0F;
/*     */ 
/* 426 */     localq1.a(16);
/* 427 */     localq1.a(8, 8, 8);
/* 428 */     localq1.c(paramq1);
/*     */ 
/* 431 */     paramq1 = new Vector3f();
/* 432 */     paramLong1 = new Vector3f();
/* 433 */     paramq1.set(localq2.jdField_a_of_type_Int * getSectorSizeWithMargin(), localq2.jdField_b_of_type_Int * getSectorSizeWithMargin(), localq2.c * getSectorSizeWithMargin());
/*     */ 
/* 438 */     paramLong1.set(localq1.jdField_a_of_type_Int * getSectorSizeWithMargin(), localq1.jdField_b_of_type_Int * getSectorSizeWithMargin(), localq1.c * getSectorSizeWithMargin());
/*     */ 
/* 443 */     paramTransform.setIdentity();
/*     */ 
/* 445 */     if (paramLong1.lengthSquared() > 0.0F) {
/* 446 */       paramTransform.origin.add(paramLong1);
/* 447 */       paramTransform.basis.rotX(6.283186F * paramq2);
/* 448 */       (
/* 449 */         paramq2 = new Vector3f())
/* 449 */         .sub(paramq1, paramLong1);
/* 450 */       paramTransform.origin.add(paramq2);
/* 451 */       paramTransform.basis.transform(paramTransform.origin);
/*     */ 
/* 453 */       return;
/*     */     }
/*     */ 
/* 456 */     paramTransform.basis.rotX(6.283186F * paramq2);
/* 457 */     paramTransform.origin.set(paramq1);
/* 458 */     paramTransform.basis.transform(paramTransform.origin);
/*     */   }
/*     */ 
/*     */   public mI getStellarSystemFromStellarPos(q paramq)
/*     */   {
/*     */     mJ localmJ;
/* 468 */     if ((
/* 468 */       localmJ = (mJ)this.starSystemMap.get(paramq)) == null)
/*     */     {
/* 469 */       localmJ = loadOrGenerateVoidSystem(paramq);
/* 470 */       this.starSystemMap.put(localmJ.jdField_a_of_type_Q, localmJ);
/*     */     }
/* 472 */     return localmJ;
/*     */   }
/*     */ 
/*     */   public String getUniqueIdentifier()
/*     */   {
/* 477 */     return this.name;
/*     */   }
/*     */ 
/*     */   public boolean isEmpty() {
/* 481 */     return this.sectors.isEmpty();
/*     */   }
/*     */ 
/*     */   public boolean isVolatile()
/*     */   {
/* 487 */     return false;
/*     */   }
/*     */   public boolean isSectorLoaded(q paramq) {
/* 490 */     return this.sectorPositions.containsKey(paramq);
/*     */   }
/*     */ 
/*     */   public void loadOrGenerateSector(q paramq) {
/* 494 */     q localq = new q(paramq);
/*     */ 
/* 496 */     System.out.println("[SERVER][UNIVERSE] LOADING SECTOR... " + localq);
/*     */ 
/* 498 */     mx localmx = new mx(this.state);
/*     */ 
/* 507 */     if (!this.state.a().a(paramq, localmx))
/*     */     {
/* 509 */       (
/* 510 */         localmx = addNewSector(localq))
/* 510 */         .e();
/*     */     }
/*     */     else {
/* 513 */       localmx.c(this.state);
/* 514 */       addSector(localmx);
/*     */     }
/*     */ 
/* 535 */     localmx.a(true);
/* 536 */     localmx.a(this.state);
/*     */   }
/*     */ 
/*     */   private mJ loadOrGenerateVoidSystem(q paramq)
/*     */   {
/* 565 */     mJ localmJ = new mJ();
/*     */ 
/* 567 */     if (this.state.a().a(paramq, localmJ))
/*     */     {
/* 569 */       if ((!$assertionsDisabled) && (localmJ.b < 0L)) throw new AssertionError(); 
/*     */     }
/*     */     else
/*     */     {
/* 572 */       localmJ.jdField_a_of_type_Q.b(paramq);
/* 573 */       localmJ.a(random);
/*     */       try
/*     */       {
/* 576 */         long l = this.state.a().a(localmJ, false);
/* 577 */         if (localmJ.b < 0L)
/* 578 */           localmJ.b = l;
/*     */       }
/*     */       catch (SQLException localSQLException)
/*     */       {
/* 582 */         localSQLException.printStackTrace();
/*     */       }
/*     */     }
/*     */ 
/* 584 */     return localmJ;
/*     */   }
/*     */ 
/*     */   private mJ loadVoidSystemFromFile(String paramString, q paramq)
/*     */   {
/* 589 */     paramString = new File(paramString);
/*     */ 
/* 593 */     mJ localmJ = new mJ();
/*     */ 
/* 595 */     if (paramString.exists())
/*     */     {
/* 597 */       paramq = Ad.a(paramString = new DataInputStream(new BufferedInputStream(new FileInputStream(paramString))), 
/* 597 */         true);
/* 598 */       paramString.close();
/* 599 */       localmJ.fromTagStructure(paramq);
/*     */     } else {
/* 601 */       localmJ.jdField_a_of_type_Q.b(paramq);
/* 602 */       localmJ.a(random);
/*     */     }
/*     */ 
/* 605 */     return localmJ;
/*     */   }
/*     */ 
/*     */   public void pingSectors()
/*     */   {
/* 610 */     for (lE locallE : this.state
/* 610 */       .b().values())
/* 611 */       pingSectorBlock(locallE.a());
/*     */   }
/*     */ 
/*     */   private void pingSectorBlock(q paramq)
/*     */   {
/* 617 */     for (int i = paramq.jdField_a_of_type_Int - 1; i <= paramq.jdField_a_of_type_Int + 1; i++)
/* 618 */       for (int j = paramq.jdField_b_of_type_Int - 1; j <= paramq.jdField_b_of_type_Int + 1; j++)
/* 619 */         for (int k = paramq.c - 1; k <= paramq.c + 1; k++) {
/* 620 */           this.where.b(i, j, k);
/* 621 */           getSector(this.where).b();
/*     */         }
/*     */   }
/*     */ 
/*     */   public void resetAllSectors()
/*     */   {
/* 631 */     mx localmx1 = getSector(new q(mx.jdField_b_of_type_Q));
/*     */     mx localmx2;
/* 632 */     (
/* 634 */       localmx2 = new mx(this.state))
/* 634 */       .a(localmx1.a());
/* 635 */     localmx2.jdField_a_of_type_Q = new q(mx.jdField_b_of_type_Q);
/*     */ 
/* 637 */     localmx2.b(this.state);
/*     */ 
/* 639 */     addSector(localmx2);
/* 640 */     localmx1.a();
/*     */   }
/*     */ 
/*     */   public void setName(String paramString)
/*     */   {
/* 646 */     this.name = paramString;
/*     */   }
/*     */ 
/*     */   public Ad toTagStructure()
/*     */   {
/* 654 */     return null;
/*     */   }
/*     */ 
/*     */   public void update(xq paramxq)
/*     */   {
/* 664 */     if (System.currentTimeMillis() - this.lastPing > 500L) {
/* 665 */       l1 = System.currentTimeMillis();
/* 666 */       pingSectors();
/*     */       long l2;
/* 668 */       if ((
/* 668 */         l2 = System.currentTimeMillis() - l1) > 
/* 668 */         10L) {
/* 669 */         System.err.println("[UNIVERSE] WARNING: Sector Ping took " + l2);
/*     */       }
/* 671 */       this.lastPing = System.currentTimeMillis();
/*     */     }
/*     */ 
/* 675 */     long l1 = System.currentTimeMillis();
/* 676 */     int i = ((Integer)vo.f.a()).intValue();
/* 677 */     this.sectors.int2ObjectEntrySet();
/*     */ 
/* 679 */     long l3 = System.currentTimeMillis();
/* 680 */     vg.g = 0;
/* 681 */     vg.h = 0;
/* 682 */     for (Iterator localIterator2 = getSectorSet().iterator(); localIterator2.hasNext(); )
/*     */     {
/*     */       mx localmx1;
/* 683 */       if (((
/* 683 */         localmx1 = (mx)localIterator2.next())
/* 683 */         .a()) && (i >= 0) && (l3 - localmx1.a() > i * 1000)) {
/* 684 */         int k = 0;
/*     */ 
/* 686 */         for (Iterator localIterator3 = this.state.b().values().iterator(); localIterator3.hasNext(); ) {
/* 687 */           if (((lE)localIterator3.next())
/* 687 */             .c() == localmx1.a()) {
/* 688 */             k = 1;
/* 689 */             localmx1.b();
/*     */           }
/*     */         }
/* 692 */         if (k == 0) {
/* 693 */           localmx1.a(false);
/*     */         }
/*     */       }
/* 696 */       localmx1.a(paramxq);
/* 697 */       if ((!localmx1.a()) && (localmx1.a(l3)) && (localmx1.c()) && (!this.entityCleaningSectors.contains(localmx1))) {
/* 698 */         this.inactiveWrittenSectors.add(localmx1);
/*     */       }
/*     */     }
/* 701 */     vg.i = vg.g;
/* 702 */     vg.j = vg.h;
/*     */     long l4;
/* 704 */     if ((
/* 704 */       l4 = System.currentTimeMillis() - l1) > 
/* 704 */       30L) {
/* 705 */       System.err.println("[UNIVERSE] WARNING: Sector UPDATE took " + l4 + "; sectors updated: " + getSectorSet().size());
/*     */     }
/*     */ 
/* 708 */     long l5 = System.currentTimeMillis();
/* 709 */     if (!this.toClear.isEmpty()) {
/* 710 */       synchronized (this.toClear) {
/* 711 */         if (!this.toClear.isEmpty()) {
/* 712 */           (
/* 713 */             paramxq = (ka)this.toClear.dequeue())
/* 713 */             .getSegmentBuffer().d();
/* 714 */           paramxq.getSegmentBuffer().a();
/* 715 */           paramxq.getSegmentProvider().f();
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 720 */     int j = this.inactiveWrittenSectors.size();
/* 721 */     for (paramxq = this.inactiveWrittenSectors.iterator(); paramxq.hasNext(); ) { localObject1 = (mx)paramxq.next();
/* 722 */       localmx2 = (mx)this.sectorPositions.remove(((mx)localObject1).jdField_a_of_type_Q);
/* 723 */       mx localmx3 = (mx)this.sectors.remove(((mx)localObject1).a());
/* 724 */       System.err.println("[SECTOR][CLEANUP] removing entities and " + localObject1 + ": " + localmx2 + "; " + localmx3);
/* 725 */       assert ((localmx2 != null) && (localmx3 != null));
/*     */ 
/* 727 */       for (localIterator1 = ((mx)localObject1).a()
/* 727 */         .iterator(); localIterator1.hasNext(); ) ((mF)localIterator1.next())
/* 728 */           .setMarkedForDeleteVolatile(true);
/*     */ 
/* 730 */       this.entityCleaningSectors.add(localObject1);
/*     */     }
/*     */     mx localmx2;
/*     */     Iterator localIterator1;
/* 732 */     this.inactiveWrittenSectors.clear();
/* 733 */     paramxq = this.entityCleaningSectors.size();
/*     */ 
/* 735 */     Object localObject1 = this.entityCleaningSectors.iterator();
/* 736 */     while (((ObjectIterator)localObject1).hasNext())
/*     */     {
/* 738 */       localmx2 = (mx)((ObjectIterator)localObject1).next();
/* 739 */       int m = 0;
/*     */ 
/* 741 */       for (mF localmF : localmx2.a())
/*     */       {
/* 742 */         if (this.state.getLocalAndRemoteObjectContainer().getLocalObjects().containsKey(localmF.getId())) {
/* 743 */           m = 1;
/* 744 */           break;
/*     */         }
/*     */       }
/* 747 */       if (m == 0)
/*     */       {
/* 750 */         localmx2.a();
/* 751 */         localmx2.a().setMarkedForDeleteVolatile(true);
/* 752 */         localmx2.jdField_a_of_type_Boolean = true;
/* 753 */         ((ObjectIterator)localObject1).remove();
/*     */       }
/*     */       else
/*     */       {
/* 757 */         System.err.println("[SERVER] waiting for sector " + localmx2 + " entities to be cleaned up");
/*     */       }
/*     */     }
/*     */     long l6;
/* 762 */     if ((
/* 762 */       l6 = System.currentTimeMillis() - l5) > 
/* 762 */       5L)
/* 763 */       System.err.println("[UNIVERSE] WARNING SECTOR CLEANUP TIME: " + l6 + "; QUEUE: " + paramxq + "; InactSectors: " + j);
/*     */   }
/*     */ 
/*     */   public void writeStarSystems()
/*     */   {
/* 768 */     for (Iterator localIterator = this.starSystemMap.values().iterator(); localIterator.hasNext(); localIterator.next());
/*     */   }
/*     */ 
/*     */   private void write(Ag paramAg, String paramString)
/*     */   {
/* 786 */     Object localObject1 = null;
/* 787 */     synchronized (this.state.jdField_a_of_type_JavaUtilHashMap)
/*     */     {
/* 789 */       if ((
/* 789 */         localObject1 = this.state.jdField_a_of_type_JavaUtilHashMap.get(paramString)) == null)
/*     */       {
/* 790 */         localObject1 = new Object();
/* 791 */         this.state.jdField_a_of_type_JavaUtilHashMap.put(paramString, localObject1);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 796 */     if (( = new File("./tmp/" + paramString + ".tmp"))
/* 796 */       .exists()) {
/* 797 */       System.err.println("Exception: tried parallel write: " + ((File)???).getName());
/* 798 */       return;
/*     */     }
/*     */ 
/* 801 */     DataOutputStream localDataOutputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream((File)???), 1024));
/*     */ 
/* 803 */     Ad localAd = paramAg.toTagStructure();
/*     */     try {
/* 805 */       localAd.a(localDataOutputStream, true);
/* 806 */       localDataOutputStream.close();
/*     */     } catch (RuntimeException localRuntimeException) {
/* 808 */       System.err.println("Exception during write of: " + paramAg);
/* 809 */       throw localRuntimeException;
/*     */     }
/*     */ 
/* 813 */     synchronized (localRuntimeException) {
/* 814 */       paramAg = new File(vg.jdField_a_of_type_JavaLangString + paramString);
/* 815 */       paramString = new File(vg.jdField_a_of_type_JavaLangString + paramString + ".old");
/* 816 */       if (paramAg.exists()) {
/* 817 */         if (paramString.exists()) {
/* 818 */           System.err.println("Exception: tried parallel write off OLD: " + paramString.getName());
/* 819 */           paramString.delete();
/*     */         }
/* 821 */         paramAg.renameTo(paramString);
/* 822 */         paramAg.delete();
/*     */       }
/* 824 */       ((File)???).renameTo(paramAg);
/* 825 */       if (paramString.exists())
/* 826 */         paramString.delete();
/*     */       return;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void writeToDatabase(boolean paramBoolean1, boolean paramBoolean2)
/*     */   {
/* 834 */     writeFactionsAndCatalog();
/*     */ 
/* 836 */     for (Iterator localIterator = this.sectors.values().iterator(); localIterator.hasNext(); )
/*     */     {
/*     */       mx localmx;
/* 837 */       if (((
/* 837 */         localmx = (mx)localIterator.next())
/* 837 */         .a()) || (!localmx.c())) {
/* 838 */         if (paramBoolean1)
/*     */         {
/* 841 */           localmx.a(1, this);
/* 842 */         } else if (paramBoolean2)
/* 843 */           localmx.a(2, this);
/*     */         else {
/* 845 */           localmx.a(0, this);
/*     */         }
/*     */       }
/*     */     }
/* 849 */     writeStarSystems();
/*     */ 
/* 851 */     writeSimulationState();
/* 852 */     if (paramBoolean1)
/*     */       try {
/* 854 */         this.state.getThreadPool().shutdown();
/* 855 */         System.out.println("[SERVER] WAITING FOR THREAD POOL TO TERMINATE; Active: " + this.state.getThreadPool().getActiveCount());
/* 856 */         this.state.getThreadPool().awaitTermination(20L, TimeUnit.MINUTES);
/* 857 */         System.out.println("[SERVER] SERVER THREAD POOL TERMINATED");
/*     */ 
/* 859 */         System.out.println("[SERVER] WAITING FOR WRITING QUEUE TO FINISH; Active: " + this.state.getThreadQueue().getActiveCount());
/* 860 */         while (this.state.getThreadQueue().getActiveCount() > 0) {
/* 861 */           Thread.sleep(300L);
/* 863 */         }System.out.println("[SERVER] SERVER THREAD QUEUE TERMINATED");
/*     */         return;
/*     */       } catch (InterruptedException localInterruptedException) {
/* 866 */         localInterruptedException.printStackTrace();
/*     */       }
/*     */   }
/*     */ 
/*     */   private void writeSimulationState()
/*     */   {
/* 870 */     this.state.a().c();
/* 871 */     write(this.state.a(), this.state.a().getUniqueIdentifier() + ".sim");
/*     */   }
/*     */   private void writeFactionsAndCatalog() {
/* 874 */     if ((this.state != null) && (this.state.a() != null)) {
/* 875 */       System.err.println("[SERVER] WRITING FACTIONS AND CATALOG!!! Count: " + this.state.a().a().a().size());
/* 876 */       write(this.state.a().a(), "FACTIONS.fac");
/* 877 */       write(this.state.a().a(), "CATALOG.cat");
/*     */     }
/*     */   }
/*     */ 
/* 881 */   public void addToFreePhysics(zM paramzM, mx parammx) { int i = 0;
/* 882 */     ((PhysicsExt)paramzM).setState(null);
/* 883 */     ((PhysicsExt)paramzM).getDynamicsWorld().setInternalTickCallback(null, null);
/* 884 */     synchronized (getPhysicsRepository())
/*     */     {
/* 886 */       if (((
/* 886 */         i = getPhysicsRepository().size() > 30 ? 1 : 0) == 0) && 
/* 886 */         (!getPhysicsRepository().contains(paramzM))) {
/* 887 */         System.err.println("[SERVER] physics for " + this + ": " + paramzM + " has been added to repository");
/* 888 */         getPhysicsRepository().add(paramzM);
/* 889 */         paramzM.softClean();
/*     */       }
/*     */     }
/* 892 */     if (i != 0) {
/* 893 */       System.out.println("Cleaned up Physics, because repository is full");
/* 894 */       paramzM.cleanUp();
/* 895 */       parammx.g();
/* 896 */       parammx.jdField_a_of_type_Vg = null;
/* 897 */       parammx.jdField_a_of_type_JavaUtilSet = null;
/*     */     } }
/*     */ 
/*     */   public zM getPhysicsInstance(zS paramzS)
/*     */   {
/* 902 */     synchronized (getPhysicsRepository()) {
/* 903 */       if (getPhysicsRepository().size() > 0)
/*     */       {
/*     */         PhysicsExt localPhysicsExt;
/* 904 */         (
/* 905 */           localPhysicsExt = (PhysicsExt)getPhysicsRepository().remove(0))
/* 905 */           .setState(paramzS);
/* 906 */         System.err.println("[SERVER] physics for " + this + ": " + localPhysicsExt + " has been fetched repository");
/* 907 */         return localPhysicsExt;
/*     */       }
/*     */     }
/* 910 */     return new PhysicsExt(paramzS);
/*     */   }
/*     */ 
/*     */   public ArrayList getPhysicsRepository()
/*     */   {
/* 916 */     return this.physicsRepository;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.world.Universe
 * JD-Core Version:    0.6.2
 */