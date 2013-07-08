/*   1:    */package org.schema.game.common.data.world;
/*   2:    */
/*   3:    */import Ah;
/*   4:    */import Ak;
/*   5:    */import com.bulletphysics.dynamics.DynamicsWorld;
/*   6:    */import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*   7:    */import it.unimi.dsi.fastutil.objects.ObjectArrayFIFOQueue;
/*   8:    */import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*   9:    */import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*  10:    */import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
/*  11:    */import jA;
/*  12:    */import jF;
/*  13:    */import jL;
/*  14:    */import jY;
/*  15:    */import java.io.BufferedInputStream;
/*  16:    */import java.io.BufferedOutputStream;
/*  17:    */import java.io.DataInputStream;
/*  18:    */import java.io.DataOutputStream;
/*  19:    */import java.io.FileInputStream;
/*  20:    */import java.io.FileOutputStream;
/*  21:    */import java.sql.SQLException;
/*  22:    */import java.util.ArrayList;
/*  23:    */import java.util.Collection;
/*  24:    */import java.util.Iterator;
/*  25:    */import java.util.Random;
/*  26:    */import java.util.concurrent.ThreadPoolExecutor;
/*  27:    */import java.util.concurrent.TimeUnit;
/*  28:    */import javax.vecmath.Matrix3f;
/*  29:    */import javax.vecmath.Tuple3f;
/*  30:    */import jv;
/*  31:    */import ka;
/*  32:    */import kd;
/*  33:    */import kf;
/*  34:    */import ki;
/*  35:    */import kl;
/*  36:    */import kw;
/*  37:    */import lD;
/*  38:    */import lE;
/*  39:    */import lT;
/*  40:    */import lg;
/*  41:    */import mF;
/*  42:    */import mI;
/*  43:    */import mJ;
/*  44:    */import mv;
/*  45:    */import org.schema.game.common.controller.SegmentController;
/*  46:    */import org.schema.game.common.controller.database.DatabaseIndex;
/*  47:    */import org.schema.game.common.data.element.Element;
/*  48:    */import org.schema.game.common.data.physics.PairCachingGhostObjectAlignable;
/*  49:    */import org.schema.game.common.data.physics.PhysicsExt;
/*  50:    */import org.schema.game.server.controller.GameServerController;
/*  51:    */import org.schema.schine.network.NetworkStateContainer;
/*  52:    */import org.schema.schine.network.SynchronizationContainerController;
/*  53:    */import org.schema.schine.network.objects.Sendable;
/*  54:    */import org.schema.schine.network.objects.container.PhysicsDataContainer;
/*  55:    */import org.schema.schine.network.server.ServerEntityWriterThread;
/*  56:    */import vR;
/*  57:    */import vo;
/*  58:    */import xq;
/*  59:    */import zQ;
/*  60:    */import zW;
/*  61:    */
/*  62:    */public class Universe implements Ak
/*  63:    */{
/*  64:    */  public static final int SECTOR_GENERATION_LENGTH = 5;
/*  65:    */  
/*  66:    */  public static Random getRandom()
/*  67:    */  {
/*  68: 68 */    return random;
/*  69:    */  }
/*  70:    */  
/*  71: 71 */  public static int getSectorSizeWithMargin() { return 1300; }
/*  72:    */  
/*  76:    */  private static String getVoidSystemFileName(q paramq)
/*  77:    */  {
/*  78: 78 */    return "VOIDSYSTEM_" + paramq.jdField_a_of_type_Int + "_" + paramq.jdField_b_of_type_Int + "_" + paramq.c + ".ssys";
/*  79:    */  }
/*  80:    */  
/*  81:    */  public static Sendable loadEntity(vg paramvg, kw paramkw, mx parammx) {
/*  82: 82 */    if (paramkw.jdField_a_of_type_Int == 3) {
/*  83:    */      jy localjy;
/*  84: 84 */      (localjy = new jy(paramvg)).setId(paramvg.getNextFreeObjectId());
/*  85: 85 */      localjy.setSectorId(parammx.a());
/*  86: 86 */      localjy.initialize();
/*  87: 87 */      localjy.getMinPos().b(paramkw.jdField_b_of_type_Q);
/*  88: 88 */      localjy.getMaxPos().b(paramkw.jdField_c_of_type_Q);
/*  89: 89 */      localjy.a(paramkw.jdField_a_of_type_Boolean);
/*  90: 90 */      localjy.setRealName(paramkw.d);
/*  91: 91 */      localjy.setFactionId(paramkw.jdField_b_of_type_Int);
/*  92: 92 */      localjy.setLastModified(paramkw.jdField_b_of_type_JavaLangString);
/*  93: 93 */      localjy.setSpawner(paramkw.jdField_c_of_type_JavaLangString);
/*  94: 94 */      localjy.setUniqueIdentifier(paramkw.jdField_a_of_type_JavaLangString);
/*  95: 95 */      localjy.setMass(0.0F);
/*  96:    */      
/*  97: 97 */      localjy.getRemoteTransformable().a().setIdentity();
/*  98: 98 */      localjy.getRemoteTransformable().a().origin.set(paramkw.jdField_a_of_type_JavaxVecmathVector3f);
/*  99: 99 */      localjy.getRemoteTransformable().getWorldTransform().setIdentity();
/* 100:100 */      localjy.getRemoteTransformable().getWorldTransform().origin.set(paramkw.jdField_a_of_type_JavaxVecmathVector3f);
/* 101:101 */      if (paramkw.jdField_a_of_type_Long == 0L) {
/* 102:102 */        localjy.setSeed(getRandom().nextLong());
/* 103:    */      } else {
/* 104:104 */        localjy.setSeed(paramkw.jdField_a_of_type_Long);
/* 105:    */      }
/* 106:    */      
/* 108:108 */      paramvg.a().a().addNewSynchronizedObjectQueued(localjy);
/* 109:    */      
/* 112:112 */      return localjy;
/* 113:    */    }
/* 114:114 */    if (!$assertionsDisabled) throw new AssertionError(paramkw);
/* 115:115 */    return null;
/* 116:    */  }
/* 117:    */  
/* 118:    */  public static Sendable loadEntity(vg paramvg, String paramString, java.io.File paramFile, mx parammx) {
/* 119:    */    Object localObject;
/* 120:120 */    if (paramString.equals("SHIP")) {
/* 121:121 */      paramString = paramvg.a().a(paramFile.getName(), "");
/* 122:122 */      (
/* 123:123 */        localObject = new kd(paramvg)).setId(paramvg.getNextFreeObjectId());
/* 124:124 */      ((kd)localObject).setSectorId(parammx.a());
/* 125:125 */      ((kd)localObject).initialize();
/* 126:126 */      System.err.println("adding loaded object from disk: " + paramFile.getName() + " in sector " + parammx.jdField_a_of_type_Q);
/* 127:127 */      ((kd)localObject).fromTagStructure(paramString);
/* 128:128 */      System.err.println("added loaded object from disk: " + localObject + " from " + paramFile.getName());
/* 129:    */      
/* 130:130 */      paramvg.a().a().addNewSynchronizedObjectQueued((Sendable)localObject);
/* 131:131 */      return localObject; }
/* 132:132 */    if (paramString.equals("SPACEOBJECT")) {
/* 133:133 */      System.err.println("found existing playerstate! ");
/* 134:134 */      throw new IllegalArgumentException();
/* 135:    */    }
/* 136:136 */    if (paramString.equals("FLOATINGROCK")) {
/* 137:137 */      paramString = paramvg.a().a(paramFile.getName(), "");
/* 138:138 */      (
/* 139:139 */        localObject = new jy(paramvg)).setId(paramvg.getNextFreeObjectId());
/* 140:140 */      ((jy)localObject).setSectorId(parammx.a());
/* 141:141 */      ((jy)localObject).initialize();
/* 142:142 */      ((jy)localObject).fromTagStructure(paramString);
/* 143:    */      
/* 144:144 */      paramvg.a().a().addNewSynchronizedObjectQueued((Sendable)localObject);
/* 145:145 */      return localObject;
/* 146:    */    }
/* 147:147 */    if (paramString.equals("SHOP")) {
/* 148:148 */      paramString = paramvg.a().a(paramFile.getName(), "");
/* 149:149 */      (
/* 150:150 */        localObject = new kf(paramvg)).setId(paramvg.getNextFreeObjectId());
/* 151:151 */      ((kf)localObject).setSectorId(parammx.a());
/* 152:152 */      ((kf)localObject).initialize();
/* 153:153 */      ((kf)localObject).fromTagStructure(paramString);
/* 154:    */      
/* 155:155 */      paramvg.a().a().addNewSynchronizedObjectQueued((Sendable)localObject);
/* 156:156 */      return localObject;
/* 157:    */    }
/* 158:158 */    if (paramString.equals("SPACESTATION")) {
/* 159:159 */      paramString = paramvg.a().a(paramFile.getName(), "");
/* 160:160 */      (
/* 161:161 */        localObject = new ki(paramvg)).setId(paramvg.getNextFreeObjectId());
/* 162:162 */      ((ki)localObject).setSectorId(parammx.a());
/* 163:163 */      ((ki)localObject).initialize();
/* 164:164 */      ((ki)localObject).fromTagStructure(paramString);
/* 165:165 */      System.err.println("[UNIVERSE] adding loaded object from disk: " + localObject);
/* 166:166 */      paramvg.a().a().addNewSynchronizedObjectQueued((Sendable)localObject);
/* 167:167 */      return localObject;
/* 168:    */    }
/* 169:169 */    if (paramString.equals("PLANET")) {
/* 170:170 */      paramString = paramvg.a().a(paramFile.getName(), "");
/* 171:171 */      (
/* 172:172 */        localObject = new jA(paramvg)).setId(paramvg.getNextFreeObjectId());
/* 173:173 */      ((jA)localObject).setSectorId(parammx.a());
/* 174:174 */      ((jA)localObject).initialize();
/* 175:175 */      ((jA)localObject).fromTagStructure(paramString);
/* 176:    */      
/* 177:177 */      paramvg.a().a().addNewSynchronizedObjectQueued((Sendable)localObject);
/* 178:178 */      return localObject;
/* 179:    */    }
/* 180:180 */    if (paramString.equals("DEATHSTAR")) {
/* 181:181 */      paramString = paramvg.a().a(paramFile.getName(), "");
/* 182:182 */      (
/* 183:183 */        localObject = new kl(paramvg)).setId(paramvg.getNextFreeObjectId());
/* 184:184 */      ((kl)localObject).setSectorId(parammx.a());
/* 185:185 */      ((kl)localObject).initialize();
/* 186:186 */      ((kl)localObject).fromTagStructure(paramString);
/* 187:    */      
/* 188:188 */      paramvg.a().a().addNewSynchronizedObjectQueued((Sendable)localObject);
/* 189:189 */      return localObject;
/* 190:    */    }
/* 191:191 */    System.err.println("[LOADENTITY] WARNING: type not known: " + paramString);
/* 192:192 */    return null;
/* 193:    */  }
/* 194:    */  
/* 195:195 */  private final java.util.HashMap starSystemMap = new java.util.HashMap();
/* 196:    */  
/* 197:    */  public static final float SECTOR_MARGIN = 300.0F;
/* 198:    */  
/* 199:    */  private String name;
/* 200:    */  
/* 201:    */  private final Int2ObjectOpenHashMap sectors;
/* 202:    */  
/* 203:    */  private final java.util.HashMap sectorPositions;
/* 204:    */  private final vg state;
/* 205:205 */  javax.vecmath.Vector3f tmpObjectPos = new javax.vecmath.Vector3f();
/* 206:206 */  q belogingVector = new q();
/* 207:    */  
/* 208:    */  private static Random random;
/* 209:    */  
/* 210:210 */  private final ObjectArrayFIFOQueue toClear = new ObjectArrayFIFOQueue();
/* 211:211 */  private ObjectOpenHashSet inactiveWrittenSectors = new ObjectOpenHashSet();
/* 212:212 */  private ObjectOpenHashSet entityCleaningSectors = new ObjectOpenHashSet();
/* 213:    */  
/* 214:214 */  private final ArrayList physicsRepository = new ArrayList();
/* 215:    */  
/* 216:    */  public Universe(vg paramvg, long paramLong) {
/* 217:217 */    this.sectors = new Int2ObjectOpenHashMap();
/* 218:218 */    this.sectorPositions = new java.util.HashMap();
/* 219:219 */    this.state = paramvg;
/* 220:    */    
/* 221:221 */    random = new Random(System.currentTimeMillis());
/* 222:    */  }
/* 223:    */  
/* 224:    */  private mx addNewSector(q paramq) {
/* 225:    */    mx localmx;
/* 226:226 */    (localmx = new mx(this.state)).jdField_a_of_type_Q = new q(paramq);
/* 227:    */    
/* 230:230 */    addSector(localmx);
/* 231:231 */    localmx.b(this.state);
/* 232:    */    
/* 233:233 */    return localmx;
/* 234:    */  }
/* 235:    */  
/* 236:    */  public void addSector(mx parammx)
/* 237:    */  {
/* 238:238 */    assert (parammx != null);
/* 239:239 */    this.sectors.put(parammx.a(), parammx);
/* 240:240 */    parammx.f();
/* 241:241 */    this.sectorPositions.put(parammx.jdField_a_of_type_Q, parammx);
/* 242:    */  }
/* 243:    */  
/* 244:244 */  public void addToClear(ka paramka) { synchronized (this.toClear) {
/* 245:245 */      this.toClear.enqueue(paramka);
/* 246:246 */      return;
/* 247:    */    }
/* 248:    */  }
/* 249:    */  
/* 251:    */  public void fromTagStructure(Ah paramAh) {}
/* 252:    */  
/* 253:    */  public boolean getIsSectorActive(int paramInt)
/* 254:    */  {
/* 255:255 */    return ((paramInt = (mx)this.sectors.get(paramInt)) != null) && (paramInt.a());
/* 256:    */  }
/* 257:    */  
/* 258:    */  public boolean existsSector(int paramInt) {
/* 259:259 */    return (mx)this.sectors.get(paramInt) != null;
/* 260:    */  }
/* 261:    */  
/* 263:    */  public String getName()
/* 264:    */  {
/* 265:265 */    return this.name;
/* 266:    */  }
/* 267:    */  
/* 268:268 */  public mx getSector(int paramInt) { return (mx)this.sectors.get(paramInt); }
/* 269:    */  
/* 270:    */  public mx getSectorWithoutLoading(q paramq) {
/* 271:271 */    return (mx)this.sectorPositions.get(paramq);
/* 272:    */  }
/* 273:    */  
/* 274:    */  public mx getSector(q paramq) {
/* 275:275 */    if (!this.sectorPositions.containsKey(paramq)) {
/* 276:276 */      loadOrGenerateSector(paramq);
/* 277:    */    }
/* 278:    */    mx localmx;
/* 279:279 */    if ((!(localmx = (mx)this.sectorPositions.get(paramq)).a()) && ((this.entityCleaningSectors.contains(localmx)) || (this.inactiveWrittenSectors.contains(localmx)))) {
/* 280:280 */      loadOrGenerateSector(paramq);
/* 281:    */    }
/* 282:282 */    if (!this.sectors.containsKey(localmx.a())) {
/* 283:283 */      assert (localmx != null);
/* 284:    */      
/* 285:285 */      this.sectors.put(localmx.a(), localmx);
/* 286:    */    }
/* 287:287 */    assert (localmx != null) : (paramq + " - " + this.sectorPositions);
/* 288:288 */    localmx.a(true);
/* 289:289 */    return localmx;
/* 290:    */  }
/* 291:    */  
/* 292:    */  public void updateProximitySectorInformation(q paramq) { q[] arrayOfq;
/* 293:293 */    (arrayOfq = new q[8])[0] = new q(paramq.jdField_a_of_type_Int + 8, paramq.jdField_b_of_type_Int + 8, paramq.c + 8);
/* 294:294 */    arrayOfq[1] = new q(paramq.jdField_a_of_type_Int - 8, paramq.jdField_b_of_type_Int + 8, paramq.c + 8);
/* 295:295 */    arrayOfq[2] = new q(paramq.jdField_a_of_type_Int + 8, paramq.jdField_b_of_type_Int - 8, paramq.c + 8);
/* 296:296 */    arrayOfq[3] = new q(paramq.jdField_a_of_type_Int - 8, paramq.jdField_b_of_type_Int - 8, paramq.c + 8);
/* 297:297 */    arrayOfq[4] = new q(paramq.jdField_a_of_type_Int + 8, paramq.jdField_b_of_type_Int + 8, paramq.c - 8);
/* 298:298 */    arrayOfq[5] = new q(paramq.jdField_a_of_type_Int - 8, paramq.jdField_b_of_type_Int + 8, paramq.c - 8);
/* 299:299 */    arrayOfq[6] = new q(paramq.jdField_a_of_type_Int + 8, paramq.jdField_b_of_type_Int - 8, paramq.c - 8);
/* 300:300 */    arrayOfq[7] = new q(paramq.jdField_a_of_type_Int - 8, paramq.jdField_b_of_type_Int - 8, paramq.c - 8);
/* 301:    */    
/* 302:302 */    for (paramq = 0; paramq < arrayOfq.length; paramq++) {
/* 303:303 */      getStellarSystemFromSecPos(arrayOfq[paramq]);
/* 304:    */    }
/* 305:    */  }
/* 306:    */  
/* 316:    */  public Collection getSectorSet()
/* 317:    */  {
/* 318:318 */    return this.sectors.values();
/* 319:    */  }
/* 320:    */  
/* 321:    */  public mI getStellarSystemFromSecPos(q paramq) {
/* 322:322 */    paramq = mJ.a(paramq, new q());
/* 323:    */    
/* 324:    */    mJ localmJ;
/* 325:    */    
/* 326:326 */    if ((localmJ = (mJ)this.starSystemMap.get(paramq)) == null) {
/* 327:327 */      localmJ = loadOrGenerateVoidSystem(paramq);
/* 328:328 */      this.starSystemMap.put(localmJ.jdField_a_of_type_Q, localmJ);
/* 329:    */    }
/* 330:    */    
/* 331:331 */    return localmJ;
/* 332:    */  }
/* 333:    */  
/* 334:    */  public q getSectorBelonging(mF parammF)
/* 335:    */  {
/* 336:336 */    for (int i = 0; i < Element.DIRECTIONSi.length; i++) {
/* 337:337 */      parammF.surround[i].b(getSector(parammF.getSectorId()).jdField_a_of_type_Q, Element.DIRECTIONSi[i]);
/* 338:    */    }
/* 339:    */    
/* 340:340 */    if (parammF.isHidden())
/* 341:    */    {
/* 344:344 */      return getSector(parammF.getSectorId()).jdField_a_of_type_Q;
/* 345:    */    }
/* 346:346 */    if (((parammF instanceof SegmentController)) && (((SegmentController)parammF).getDockingController().b()))
/* 347:    */    {
/* 350:350 */      return getSector(parammF.getSectorId()).jdField_a_of_type_Q;
/* 351:    */    }
/* 352:352 */    if (((parammF instanceof lD)) && (((PairCachingGhostObjectAlignable)((lD)parammF).getPhysicsDataContainer().getObject()).getAttached() != null)) {
/* 353:353 */      return getSector(parammF.getSectorId()).jdField_a_of_type_Q;
/* 354:    */    }
/* 355:    */    
/* 356:    */    mx localmx;
/* 357:357 */    q localq = (localmx = getSector(parammF.getSectorId())).jdField_a_of_type_Q;
/* 358:358 */    int j = -1;
/* 359:359 */    javax.vecmath.Vector3f localVector3f = new javax.vecmath.Vector3f(parammF.getWorldTransform().origin);
/* 360:360 */    boolean bool = mI.a(localmx.jdField_a_of_type_Q);
/* 361:361 */    for (int k = 0; k < Element.DIRECTIONSi.length; k++) {
/* 362:    */      Object localObject;
/* 363:363 */      (localObject = new q(Element.DIRECTIONSi[k])).a(localq);
/* 364:    */      com.bulletphysics.linearmath.Transform localTransform;
/* 365:365 */      (localTransform = new com.bulletphysics.linearmath.Transform()).setIdentity();
/* 366:    */      
/* 367:367 */      if (bool) {
/* 368:368 */        calcSecPos(localq, (q)localObject, this.state.a().calculateStartTime(), System.currentTimeMillis(), localTransform);
/* 369:    */      } else {
/* 370:370 */        localTransform.origin.set(Element.DIRECTIONSi[k].jdField_a_of_type_Int, Element.DIRECTIONSi[k].jdField_b_of_type_Int, Element.DIRECTIONSi[k].c);
/* 371:371 */        localTransform.origin.scale(getSectorSizeWithMargin());
/* 372:    */      }
/* 373:    */      
/* 377:377 */      (localObject = new javax.vecmath.Vector3f()).sub(parammF.getWorldTransform().origin, localTransform.origin);
/* 378:    */      
/* 381:381 */      if (((javax.vecmath.Vector3f)localObject).lengthSquared() < localVector3f.lengthSquared()) {
/* 382:382 */        localVector3f.set((Tuple3f)localObject);
/* 383:383 */        j = k;
/* 384:    */      }
/* 385:    */    }
/* 386:    */    
/* 387:387 */    if (j >= 0) {
/* 388:388 */      this.belogingVector.b(localq);
/* 389:389 */      this.belogingVector.a(Element.DIRECTIONSi[j]);
/* 390:    */      
/* 391:391 */      this.state.a().a(parammF, this.belogingVector, 0);
/* 392:    */      
/* 394:394 */      return this.belogingVector;
/* 395:    */    }
/* 396:396 */    return localq;
/* 397:    */  }
/* 398:    */  
/* 399:    */  public static void calcSunPosInnerStarSystem(q paramq, mI parammI, long paramLong, com.bulletphysics.linearmath.Transform paramTransform)
/* 400:    */  {
/* 401:401 */    paramLong = (float)((System.currentTimeMillis() - paramLong) % 1200000L) / 1200000.0F;
/* 402:402 */    paramTransform.basis.rotX(6.283186F * paramLong);
/* 403:    */    
/* 404:404 */    paramTransform.origin.set((parammI.jdField_a_of_type_Q.jdField_a_of_type_Int << 4) + 8 - paramq.jdField_a_of_type_Int, (parammI.jdField_a_of_type_Q.jdField_b_of_type_Int << 4) + 8 - paramq.jdField_b_of_type_Int, (parammI.jdField_a_of_type_Q.c << 4) + 8 - paramq.c);
/* 405:    */    
/* 408:408 */    paramTransform.origin.scale(getSectorSizeWithMargin());
/* 409:    */    
/* 410:410 */    paramTransform.basis.transform(paramTransform.origin);
/* 411:    */  }
/* 412:    */  
/* 413:    */  public static void calcSecPos(q paramq1, q paramq2, long paramLong1, long paramLong2, com.bulletphysics.linearmath.Transform paramTransform) {
/* 414:414 */    q localq1 = mJ.a(paramq2, new q());
/* 415:    */    
/* 416:    */    q localq2;
/* 417:417 */    (localq2 = new q()).a(paramq2, paramq1);
/* 418:    */    
/* 419:419 */    if (!mI.a(paramq1)) {
/* 420:420 */      paramLong1 = 0L;
/* 421:421 */      paramLong2 = 0L;
/* 422:    */    }
/* 423:    */    
/* 424:424 */    paramq2 = (float)((paramLong2 - paramLong1) % 1200000L) / 1200000.0F;
/* 425:    */    
/* 426:426 */    localq1.a(16);
/* 427:427 */    localq1.a(8, 8, 8);
/* 428:428 */    localq1.c(paramq1);
/* 429:    */    
/* 431:431 */    paramq1 = new javax.vecmath.Vector3f();
/* 432:432 */    paramLong1 = new javax.vecmath.Vector3f();
/* 433:433 */    paramq1.set(localq2.jdField_a_of_type_Int * getSectorSizeWithMargin(), localq2.jdField_b_of_type_Int * getSectorSizeWithMargin(), localq2.c * getSectorSizeWithMargin());
/* 434:    */    
/* 438:438 */    paramLong1.set(localq1.jdField_a_of_type_Int * getSectorSizeWithMargin(), localq1.jdField_b_of_type_Int * getSectorSizeWithMargin(), localq1.c * getSectorSizeWithMargin());
/* 439:    */    
/* 443:443 */    paramTransform.setIdentity();
/* 444:    */    
/* 445:445 */    if (paramLong1.lengthSquared() > 0.0F) {
/* 446:446 */      paramTransform.origin.add(paramLong1);
/* 447:447 */      paramTransform.basis.rotX(6.283186F * paramq2);
/* 448:448 */      (
/* 449:449 */        paramq2 = new javax.vecmath.Vector3f()).sub(paramq1, paramLong1);
/* 450:450 */      paramTransform.origin.add(paramq2);
/* 451:451 */      paramTransform.basis.transform(paramTransform.origin);
/* 452:    */      
/* 453:453 */      return;
/* 454:    */    }
/* 455:    */    
/* 456:456 */    paramTransform.basis.rotX(6.283186F * paramq2);
/* 457:457 */    paramTransform.origin.set(paramq1);
/* 458:458 */    paramTransform.basis.transform(paramTransform.origin);
/* 459:    */  }
/* 460:    */  
/* 463:    */  public mI getStellarSystemFromStellarPos(q paramq)
/* 464:    */  {
/* 465:    */    mJ localmJ;
/* 466:    */    
/* 468:468 */    if ((localmJ = (mJ)this.starSystemMap.get(paramq)) == null) {
/* 469:469 */      localmJ = loadOrGenerateVoidSystem(paramq);
/* 470:470 */      this.starSystemMap.put(localmJ.jdField_a_of_type_Q, localmJ);
/* 471:    */    }
/* 472:472 */    return localmJ;
/* 473:    */  }
/* 474:    */  
/* 475:    */  public String getUniqueIdentifier()
/* 476:    */  {
/* 477:477 */    return this.name;
/* 478:    */  }
/* 479:    */  
/* 480:    */  public boolean isEmpty() {
/* 481:481 */    return this.sectors.isEmpty();
/* 482:    */  }
/* 483:    */  
/* 485:    */  public boolean isVolatile()
/* 486:    */  {
/* 487:487 */    return false;
/* 488:    */  }
/* 489:    */  
/* 490:490 */  public boolean isSectorLoaded(q paramq) { return this.sectorPositions.containsKey(paramq); }
/* 491:    */  
/* 492:    */  public void loadOrGenerateSector(q paramq)
/* 493:    */  {
/* 494:494 */    q localq = new q(paramq);
/* 495:    */    
/* 496:496 */    System.out.println("[SERVER][UNIVERSE] LOADING SECTOR... " + localq);
/* 497:    */    
/* 498:498 */    mx localmx = new mx(this.state);
/* 499:    */    
/* 507:507 */    if (!this.state.a().a(paramq, localmx))
/* 508:    */    {
/* 510:510 */      (localmx = addNewSector(localq)).e();
/* 511:    */    }
/* 512:    */    else {
/* 513:513 */      localmx.c(this.state);
/* 514:514 */      addSector(localmx);
/* 515:    */    }
/* 516:    */    
/* 535:535 */    localmx.a(true);
/* 536:536 */    localmx.a(this.state);
/* 537:    */  }
/* 538:    */  
/* 563:    */  private mJ loadOrGenerateVoidSystem(q paramq)
/* 564:    */  {
/* 565:565 */    mJ localmJ = new mJ();
/* 566:    */    
/* 567:567 */    if (this.state.a().a(paramq, localmJ))
/* 568:    */    {
/* 569:569 */      if ((!$assertionsDisabled) && (localmJ.b < 0L)) throw new AssertionError();
/* 570:    */    }
/* 571:    */    else {
/* 572:572 */      localmJ.jdField_a_of_type_Q.b(paramq);
/* 573:573 */      localmJ.a(random);
/* 574:    */      try
/* 575:    */      {
/* 576:576 */        long l = this.state.a().a(localmJ, false);
/* 577:577 */        if (localmJ.b < 0L)
/* 578:578 */          localmJ.b = l;
/* 579:    */      } catch (SQLException localSQLException) {
/* 580:580 */        
/* 581:    */        
/* 582:582 */          localSQLException;
/* 583:    */      }
/* 584:    */    }
/* 585:    */    
/* 586:584 */    return localmJ;
/* 587:    */  }
/* 588:    */  
/* 589:    */  private mJ loadVoidSystemFromFile(String paramString, q paramq)
/* 590:    */  {
/* 591:589 */    paramString = new java.io.File(paramString);
/* 592:    */    
/* 595:593 */    mJ localmJ = new mJ();
/* 596:    */    
/* 597:595 */    if (paramString.exists())
/* 598:    */    {
/* 599:597 */      paramq = Ah.a(paramString = new DataInputStream(new BufferedInputStream(new FileInputStream(paramString))), true);
/* 600:598 */      paramString.close();
/* 601:599 */      localmJ.fromTagStructure(paramq);
/* 602:    */    } else {
/* 603:601 */      localmJ.jdField_a_of_type_Q.b(paramq);
/* 604:602 */      localmJ.a(random);
/* 605:    */    }
/* 606:    */    
/* 607:605 */    return localmJ;
/* 608:    */  }
/* 609:    */  
/* 610:    */  public void pingSectors()
/* 611:    */  {
/* 612:610 */    for (lE locallE : this.state
/* 613:    */    
/* 614:610 */      .b().values()) {
/* 615:611 */      pingSectorBlock(locallE.a());
/* 616:    */    }
/* 617:    */  }
/* 618:    */  
/* 619:615 */  private final q where = new q();
/* 620:    */  
/* 621:617 */  private void pingSectorBlock(q paramq) { for (int i = paramq.jdField_a_of_type_Int - 1; i <= paramq.jdField_a_of_type_Int + 1; i++) {
/* 622:618 */      for (int j = paramq.jdField_b_of_type_Int - 1; j <= paramq.jdField_b_of_type_Int + 1; j++) {
/* 623:619 */        for (int k = paramq.c - 1; k <= paramq.c + 1; k++) {
/* 624:620 */          this.where.b(i, j, k);
/* 625:621 */          getSector(this.where).b();
/* 626:    */        }
/* 627:    */      }
/* 628:    */    }
/* 629:    */  }
/* 630:    */  
/* 633:    */  public void resetAllSectors()
/* 634:    */  {
/* 635:631 */    mx localmx1 = getSector(new q(mx.jdField_b_of_type_Q));
/* 636:    */    
/* 637:    */    mx localmx2;
/* 638:634 */    (localmx2 = new mx(this.state)).a(localmx1.a());
/* 639:635 */    localmx2.jdField_a_of_type_Q = new q(mx.jdField_b_of_type_Q);
/* 640:    */    
/* 641:637 */    localmx2.b(this.state);
/* 642:    */    
/* 643:639 */    addSector(localmx2);
/* 644:640 */    localmx1.a();
/* 645:    */  }
/* 646:    */  
/* 648:    */  public void setName(String paramString)
/* 649:    */  {
/* 650:646 */    this.name = paramString;
/* 651:    */  }
/* 652:    */  
/* 656:    */  public Ah toTagStructure()
/* 657:    */  {
/* 658:654 */    return null;
/* 659:    */  }
/* 660:    */  
/* 666:    */  public void update(xq paramxq)
/* 667:    */  {
/* 668:664 */    if (System.currentTimeMillis() - this.lastPing > 500L) {
/* 669:665 */      l1 = System.currentTimeMillis();
/* 670:666 */      pingSectors();
/* 671:    */      long l2;
/* 672:668 */      if ((l2 = System.currentTimeMillis() - l1) > 10L) {
/* 673:669 */        System.err.println("[UNIVERSE] WARNING: Sector Ping took " + l2);
/* 674:    */      }
/* 675:671 */      this.lastPing = System.currentTimeMillis();
/* 676:    */    }
/* 677:    */    
/* 679:675 */    long l1 = System.currentTimeMillis();
/* 680:676 */    int i = ((Integer)vo.g.a()).intValue();
/* 681:677 */    this.sectors.int2ObjectEntrySet();
/* 682:    */    
/* 683:679 */    long l3 = System.currentTimeMillis();
/* 684:680 */    vg.g = 0;
/* 685:681 */    vg.h = 0;
/* 686:682 */    for (Iterator localIterator2 = getSectorSet().iterator(); localIterator2.hasNext();) { mx localmx1;
/* 687:683 */      if (((localmx1 = (mx)localIterator2.next()).a()) && (i >= 0) && (l3 - localmx1.a() > i * 1000)) {
/* 688:684 */        int k = 0;
/* 689:    */        
/* 690:686 */        for (Iterator localIterator3 = this.state.b().values().iterator(); localIterator3.hasNext();) {
/* 691:687 */          if (((lE)localIterator3.next()).c() == localmx1.a()) {
/* 692:688 */            k = 1;
/* 693:689 */            localmx1.b();
/* 694:    */          }
/* 695:    */        }
/* 696:692 */        if (k == 0) {
/* 697:693 */          localmx1.a(false);
/* 698:    */        }
/* 699:    */      }
/* 700:696 */      localmx1.a(paramxq);
/* 701:697 */      if ((!localmx1.a()) && (localmx1.a(l3)) && (localmx1.c()) && (!this.entityCleaningSectors.contains(localmx1))) {
/* 702:698 */        this.inactiveWrittenSectors.add(localmx1);
/* 703:    */      }
/* 704:    */    }
/* 705:701 */    vg.i = vg.g;
/* 706:702 */    vg.j = vg.h;
/* 707:    */    long l4;
/* 708:704 */    if ((l4 = System.currentTimeMillis() - l1) > 30L) {
/* 709:705 */      System.err.println("[UNIVERSE] WARNING: Sector UPDATE took " + l4 + "; sectors updated: " + getSectorSet().size());
/* 710:    */    }
/* 711:    */    
/* 712:708 */    long l5 = System.currentTimeMillis();
/* 713:709 */    if (!this.toClear.isEmpty()) {
/* 714:710 */      synchronized (this.toClear) {
/* 715:711 */        if (!this.toClear.isEmpty())
/* 716:    */        {
/* 717:713 */          (paramxq = (ka)this.toClear.dequeue()).getSegmentBuffer().d();
/* 718:714 */          paramxq.getSegmentBuffer().a();
/* 719:715 */          paramxq.getSegmentProvider().f();
/* 720:    */        }
/* 721:    */      }
/* 722:    */    }
/* 723:    */    
/* 724:720 */    int j = this.inactiveWrittenSectors.size();
/* 725:721 */    for (paramxq = this.inactiveWrittenSectors.iterator(); paramxq.hasNext();) { localObject1 = (mx)paramxq.next();
/* 726:722 */      localmx2 = (mx)this.sectorPositions.remove(((mx)localObject1).jdField_a_of_type_Q);
/* 727:723 */      mx localmx3 = (mx)this.sectors.remove(((mx)localObject1).a());
/* 728:724 */      System.err.println("[SECTOR][CLEANUP] removing entities and " + localObject1 + ": " + localmx2 + "; " + localmx3);
/* 729:725 */      assert ((localmx2 != null) && (localmx3 != null));
/* 730:    */      
/* 731:727 */      for (localIterator1 = ((mx)localObject1).a().iterator(); localIterator1.hasNext();) {
/* 732:728 */        ((mF)localIterator1.next()).setMarkedForDeleteVolatile(true);
/* 733:    */      }
/* 734:730 */      this.entityCleaningSectors.add(localObject1); }
/* 735:    */    mx localmx2;
/* 736:732 */    Iterator localIterator1; this.inactiveWrittenSectors.clear();
/* 737:733 */    paramxq = this.entityCleaningSectors.size();
/* 738:    */    
/* 739:735 */    Object localObject1 = this.entityCleaningSectors.iterator();
/* 740:736 */    while (((ObjectIterator)localObject1).hasNext())
/* 741:    */    {
/* 742:738 */      localmx2 = (mx)((ObjectIterator)localObject1).next();
/* 743:739 */      int m = 0;
/* 744:    */      
/* 745:741 */      for (mF localmF : localmx2.a())
/* 746:    */      {
/* 747:742 */        if (this.state.getLocalAndRemoteObjectContainer().getLocalObjects().containsKey(localmF.getId())) {
/* 748:743 */          m = 1;
/* 749:744 */          break;
/* 750:    */        }
/* 751:    */      }
/* 752:747 */      if (m == 0)
/* 753:    */      {
/* 755:750 */        localmx2.a();
/* 756:751 */        localmx2.a().setMarkedForDeleteVolatile(true);
/* 757:752 */        localmx2.jdField_a_of_type_Boolean = true;
/* 758:753 */        ((ObjectIterator)localObject1).remove();
/* 759:    */      }
/* 760:    */      else
/* 761:    */      {
/* 762:757 */        System.err.println("[SERVER] waiting for sector " + localmx2 + " entities to be cleaned up");
/* 763:    */      }
/* 764:    */    }
/* 765:    */    
/* 766:    */    long l6;
/* 767:762 */    if ((l6 = System.currentTimeMillis() - l5) > 5L) {
/* 768:763 */      System.err.println("[UNIVERSE] WARNING SECTOR CLEANUP TIME: " + l6 + "; QUEUE: " + paramxq + "; InactSectors: " + j);
/* 769:    */    }
/* 770:    */  }
/* 771:    */  
/* 772:    */  public void writeStarSystems() {
/* 773:768 */    for (Iterator localIterator = this.starSystemMap.values().iterator(); localIterator.hasNext(); localIterator.next()) {}
/* 774:    */  }
/* 775:    */  
/* 780:    */  private static final int MAX_PHYSICS_REPOSITORY_SIZE = 30;
/* 781:    */  
/* 785:    */  private long lastPing;
/* 786:    */  
/* 789:    */  private void write(Ak paramAk, String paramString)
/* 790:    */  {
/* 791:786 */    Object localObject1 = null;
/* 792:787 */    synchronized (this.state.jdField_a_of_type_JavaUtilHashMap)
/* 793:    */    {
/* 794:789 */      if ((localObject1 = this.state.jdField_a_of_type_JavaUtilHashMap.get(paramString)) == null) {
/* 795:790 */        localObject1 = new Object();
/* 796:791 */        this.state.jdField_a_of_type_JavaUtilHashMap.put(paramString, localObject1);
/* 797:    */      }
/* 798:    */    }
/* 799:    */    
/* 801:796 */    if ((??? = new java.io.File("./tmp/" + paramString + ".tmp")).exists()) {
/* 802:797 */      System.err.println("Exception: tried parallel write: " + ((java.io.File)???).getName());
/* 803:798 */      return;
/* 804:    */    }
/* 805:    */    
/* 806:801 */    DataOutputStream localDataOutputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream((java.io.File)???), 1024));
/* 807:    */    
/* 808:803 */    Ah localAh = paramAk.toTagStructure();
/* 809:    */    try {
/* 810:805 */      localAh.a(localDataOutputStream, true);
/* 811:806 */      localDataOutputStream.close();
/* 812:    */    } catch (RuntimeException localRuntimeException) {
/* 813:808 */      System.err.println("Exception during write of: " + paramAk);
/* 814:809 */      throw localRuntimeException;
/* 815:    */    }
/* 816:    */    
/* 818:813 */    synchronized (localRuntimeException) {
/* 819:814 */      paramAk = new java.io.File(vg.jdField_a_of_type_JavaLangString + paramString);
/* 820:815 */      paramString = new java.io.File(vg.jdField_a_of_type_JavaLangString + paramString + ".old");
/* 821:816 */      if (paramAk.exists()) {
/* 822:817 */        if (paramString.exists()) {
/* 823:818 */          System.err.println("Exception: tried parallel write off OLD: " + paramString.getName());
/* 824:819 */          paramString.delete();
/* 825:    */        }
/* 826:821 */        paramAk.renameTo(paramString);
/* 827:822 */        paramAk.delete();
/* 828:    */      }
/* 829:824 */      ((java.io.File)???).renameTo(paramAk);
/* 830:825 */      if (paramString.exists()) {
/* 831:826 */        paramString.delete();
/* 832:    */      }
/* 833:    */      return;
/* 834:    */    }
/* 835:    */  }
/* 836:    */  
/* 837:    */  public void writeToDatabase(boolean paramBoolean1, boolean paramBoolean2)
/* 838:    */  {
/* 839:834 */    writeFactionsAndCatalog();
/* 840:    */    
/* 841:836 */    for (Iterator localIterator = this.sectors.values().iterator(); localIterator.hasNext();) { mx localmx;
/* 842:837 */      if (((localmx = (mx)localIterator.next()).a()) || (!localmx.c())) {
/* 843:838 */        if (paramBoolean1)
/* 844:    */        {
/* 846:841 */          localmx.a(1, this);
/* 847:842 */        } else if (paramBoolean2) {
/* 848:843 */          localmx.a(2, this);
/* 849:    */        } else {
/* 850:845 */          localmx.a(0, this);
/* 851:    */        }
/* 852:    */      }
/* 853:    */    }
/* 854:849 */    writeStarSystems();
/* 855:    */    
/* 856:851 */    writeSimulationState();
/* 857:852 */    if (paramBoolean1) {
/* 858:    */      try {
/* 859:854 */        this.state.getThreadPool().shutdown();
/* 860:855 */        System.out.println("[SERVER] WAITING FOR THREAD POOL TO TERMINATE; Active: " + this.state.getThreadPool().getActiveCount());
/* 861:856 */        this.state.getThreadPool().awaitTermination(20L, TimeUnit.MINUTES);
/* 862:857 */        System.out.println("[SERVER] SERVER THREAD POOL TERMINATED");
/* 863:    */        
/* 864:859 */        System.out.println("[SERVER] WAITING FOR WRITING QUEUE TO FINISH; Active: " + this.state.getThreadQueue().getActiveCount());
/* 865:860 */        while (this.state.getThreadQueue().getActiveCount() > 0) {
/* 866:861 */          Thread.sleep(300L);
/* 867:    */        }
/* 868:863 */        System.out.println("[SERVER] SERVER THREAD QUEUE TERMINATED"); return;
/* 869:864 */      } catch (InterruptedException localInterruptedException) { 
/* 870:    */        
/* 871:866 */          localInterruptedException;
/* 872:    */      }
/* 873:    */    }
/* 874:    */  }
/* 875:    */  
/* 876:    */  private void writeSimulationState() {
/* 877:870 */    this.state.a().c();
/* 878:871 */    write(this.state.a(), this.state.a().getUniqueIdentifier() + ".sim");
/* 879:    */  }
/* 880:    */  
/* 881:874 */  private void writeFactionsAndCatalog() { if ((this.state != null) && (this.state.a() != null)) {
/* 882:875 */      System.err.println("[SERVER] WRITING FACTIONS AND CATALOG!!! Count: " + this.state.a().a().a().size());
/* 883:876 */      write(this.state.a().a(), "FACTIONS.fac");
/* 884:877 */      write(this.state.a().a(), "CATALOG.cat");
/* 885:    */    }
/* 886:    */  }
/* 887:    */  
/* 888:881 */  public void addToFreePhysics(zQ paramzQ, mx parammx) { int i = 0;
/* 889:882 */    ((PhysicsExt)paramzQ).setState(null);
/* 890:883 */    ((PhysicsExt)paramzQ).getDynamicsWorld().setInternalTickCallback(null, null);
/* 891:884 */    synchronized (getPhysicsRepository())
/* 892:    */    {
/* 893:886 */      if (((i = getPhysicsRepository().size() > 30 ? 1 : 0) == 0) && (!getPhysicsRepository().contains(paramzQ))) {
/* 894:887 */        System.err.println("[SERVER] physics for " + this + ": " + paramzQ + " has been added to repository");
/* 895:888 */        getPhysicsRepository().add(paramzQ);
/* 896:889 */        paramzQ.softClean();
/* 897:    */      }
/* 898:    */    }
/* 899:892 */    if (i != 0) {
/* 900:893 */      System.out.println("Cleaned up Physics, because repository is full");
/* 901:894 */      paramzQ.cleanUp();
/* 902:895 */      parammx.g();
/* 903:896 */      parammx.jdField_a_of_type_JavaUtilSet = null;
/* 904:    */    }
/* 905:    */  }
/* 906:    */  
/* 907:    */  public zQ getPhysicsInstance(zW paramzW) {
/* 908:901 */    synchronized (getPhysicsRepository()) {
/* 909:902 */      if (getPhysicsRepository().size() > 0) {
/* 910:    */        PhysicsExt localPhysicsExt;
/* 911:904 */        (localPhysicsExt = (PhysicsExt)getPhysicsRepository().remove(0)).setState(paramzW);
/* 912:905 */        System.err.println("[SERVER] physics for " + this + ": " + localPhysicsExt + " has been fetched repository");
/* 913:906 */        return localPhysicsExt;
/* 914:    */      }
/* 915:    */    }
/* 916:909 */    return new PhysicsExt(paramzW);
/* 917:    */  }
/* 918:    */  
/* 920:    */  public ArrayList getPhysicsRepository()
/* 921:    */  {
/* 922:915 */    return this.physicsRepository;
/* 923:    */  }
/* 924:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.world.Universe
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */