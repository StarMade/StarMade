/*   1:    */import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
/*   2:    */import com.bulletphysics.linearmath.AabbUtil2;
/*   3:    */import com.bulletphysics.linearmath.Transform;
/*   4:    */import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
/*   5:    */import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*   6:    */import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
/*   7:    */import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*   8:    */import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*   9:    */import it.unimi.dsi.fastutil.shorts.Short2IntOpenHashMap;
/*  10:    */import java.io.PrintStream;
/*  11:    */import java.util.ArrayList;
/*  12:    */import java.util.Collection;
/*  13:    */import java.util.HashMap;
/*  14:    */import java.util.Iterator;
/*  15:    */import java.util.Map.Entry;
/*  16:    */import java.util.Random;
/*  17:    */import java.util.Set;
/*  18:    */import javax.vecmath.Vector3f;
/*  19:    */import javax.vecmath.Vector4f;
/*  20:    */import org.schema.game.common.controller.EditableSendableSegmentController;
/*  21:    */import org.schema.game.common.data.element.ElementInformation;
/*  22:    */import org.schema.game.common.data.element.ElementKeyMap;
/*  23:    */import org.schema.game.common.data.element.ship.ShipElement;
/*  24:    */import org.schema.game.common.data.element.terrain.MineralElement;
/*  25:    */import org.schema.game.common.data.element.terrain.TerrainElement;
/*  26:    */import org.schema.game.common.data.world.Universe;
/*  27:    */import org.schema.game.network.objects.NetworkShop;
/*  28:    */import org.schema.game.network.objects.remote.RemoteInventoryMultMod;
/*  29:    */import org.schema.game.network.objects.remote.RemoteInventoryMultModBuffer;
/*  30:    */import org.schema.schine.graphicsengine.core.GlUtil;
/*  31:    */import org.schema.schine.network.NetworkStateContainer;
/*  32:    */import org.schema.schine.network.StateInterface;
/*  33:    */import org.schema.schine.network.objects.NetworkObject;
/*  34:    */import org.schema.schine.network.objects.Sendable;
/*  35:    */import org.schema.schine.network.objects.remote.RemoteIntArray;
/*  36:    */import org.schema.schine.network.objects.remote.RemoteIntArrayBuffer;
/*  37:    */import org.schema.schine.network.server.ServerMessage;
/*  38:    */
/*  60:    */public class kf
/*  61:    */  extends EditableSendableSegmentController
/*  62:    */  implements An, mh
/*  63:    */{
/*  64: 64 */  private static long jdField_a_of_type_Long = 300000L;
/*  65:    */  
/*  66:    */  private mn jdField_a_of_type_Mn;
/*  67:    */  
/*  68: 68 */  private final Short2IntOpenHashMap jdField_a_of_type_ItUnimiDsiFastutilShortsShort2IntOpenHashMap = new Short2IntOpenHashMap();
/*  69:    */  
/*  70: 70 */  private final Vector3f jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/*  71: 71 */  private final Vector3f jdField_b_of_type_JavaxVecmathVector3f = new Vector3f();
/*  72: 72 */  private final Vector3f jdField_c_of_type_JavaxVecmathVector3f = new Vector3f();
/*  73: 73 */  private final Vector3f jdField_d_of_type_JavaxVecmathVector3f = new Vector3f();
/*  74:    */  
/*  75:    */  private long jdField_b_of_type_Long;
/*  76:    */  
/*  77:    */  private long jdField_c_of_type_Long;
/*  78:    */  private long jdField_d_of_type_Long;
/*  79:    */  private long e;
/*  80: 80 */  private double jdField_a_of_type_Double = 1.0D;
/*  81:    */  
/*  82:    */  public kf(StateInterface paramStateInterface)
/*  83:    */  {
/*  84: 84 */    super(paramStateInterface);
/*  85: 85 */    this.jdField_a_of_type_Double = (0.5D + Math.random());
/*  86:    */  }
/*  87:    */  
/* 114:    */  public void cleanUpOnEntityDelete()
/* 115:    */  {
/* 116:116 */    super.cleanUpOnEntityDelete();
/* 117:117 */    synchronized (getState().getLocalAndRemoteObjectContainer().getLocalObjects()) {
/* 118:118 */      for (Iterator localIterator = getState().getLocalAndRemoteObjectContainer().getLocalObjects().values().iterator(); localIterator.hasNext();) { Sendable localSendable;
/* 119:119 */        if (((localSendable = (Sendable)localIterator.next()) instanceof kh))
/* 120:120 */          ((kh)localSendable).a().remove(this);
/* 121:    */      }
/* 122:    */      return;
/* 123:    */    }
/* 124:    */  }
/* 125:    */  
/* 126:    */  public final void a(boolean paramBoolean) {
/* 127:127 */    IntOpenHashSet localIntOpenHashSet = new IntOpenHashSet();
/* 128:128 */    int i = Universe.getRandom().nextInt(5) == 0 ? 1 : 0;
/* 129:129 */    short[] arrayOfShort; int j = (arrayOfShort = ElementKeyMap.typeList()).length; for (int k = 0; k < j; k++) { Short localShort;
/* 130:130 */      ElementInformation localElementInformation = ElementKeyMap.getInfo((localShort = Short.valueOf(arrayOfShort[k])).shortValue());
/* 131:131 */      if (ShipElement.class.isAssignableFrom(localElementInformation.getType())) {
/* 132:132 */        if (Universe.getRandom().nextInt(10) != 0)
/* 133:    */        {
/* 135:135 */          localIntOpenHashSet.add(this.jdField_a_of_type_Mn.b(localShort.shortValue(), 100 + Universe.getRandom().nextInt(1000)));
/* 136:    */        }
/* 137:137 */      } else if (TerrainElement.class.isAssignableFrom(localElementInformation.getType())) {
/* 138:138 */        if (MineralElement.class.isAssignableFrom(localElementInformation.getType())) {
/* 139:139 */          if (i != 0) {
/* 140:140 */            localIntOpenHashSet.add(this.jdField_a_of_type_Mn.b(localShort.shortValue(), 2000 + Universe.getRandom().nextInt(1000)));
/* 141:141 */          } else if (Universe.getRandom().nextInt(3) == 0) {
/* 142:142 */            if (Universe.getRandom().nextInt(10) == 0) {
/* 143:143 */              localIntOpenHashSet.add(this.jdField_a_of_type_Mn.b(localShort.shortValue(), 500 + Universe.getRandom().nextInt(500)));
/* 144:    */            } else {
/* 145:145 */              localIntOpenHashSet.add(this.jdField_a_of_type_Mn.b(localShort.shortValue(), Universe.getRandom().nextInt(10)));
/* 146:    */            }
/* 147:    */          }
/* 148:    */        } else {
/* 149:149 */          localIntOpenHashSet.add(this.jdField_a_of_type_Mn.b(localShort.shortValue(), Universe.getRandom().nextInt(1000)));
/* 150:    */        }
/* 151:151 */      } else if (ki.class.isAssignableFrom(localElementInformation.getType())) {
/* 152:152 */        localIntOpenHashSet.add(this.jdField_a_of_type_Mn.b(localShort.shortValue(), 100 + Universe.getRandom().nextInt(500)));
/* 153:    */      }
/* 154:    */      else {
/* 155:155 */        localIntOpenHashSet.add(this.jdField_a_of_type_Mn.b(localShort.shortValue(), Universe.getRandom().nextInt(100)));
/* 156:    */      }
/* 157:    */    }
/* 158:    */    
/* 159:159 */    if (paramBoolean) {
/* 160:160 */      this.jdField_a_of_type_Mn.a(localIntOpenHashSet);
/* 161:    */    }
/* 162:    */  }
/* 163:    */  
/* 211:    */  public void fromTagStructure(Ah paramAh)
/* 212:    */  {
/* 213:213 */    if (paramAh.a().equals("ShopSpaceStation1")) {
/* 214:214 */      paramAh = (Ah[])paramAh.a();
/* 215:215 */      if ((!jdField_a_of_type_Boolean) && (!"inventory".equals(paramAh[1].a()))) throw new AssertionError();
/* 216:216 */      this.jdField_a_of_type_Mn.fromTagStructure(paramAh[1]);
/* 217:    */      
/* 219:219 */      this.jdField_a_of_type_Double = ((Double)paramAh[2].a()).doubleValue();
/* 220:220 */      this.jdField_c_of_type_Long = ((Long)paramAh[3].a()).longValue();
/* 221:    */      
/* 222:222 */      Ah[] arrayOfAh1 = (Ah[])paramAh[4].a();
/* 223:223 */      for (int i = 0; i < arrayOfAh1.length - 1; i++) {
/* 224:224 */        Ah[] arrayOfAh2 = (Ah[])arrayOfAh1[i].a();
/* 225:225 */        this.jdField_a_of_type_ItUnimiDsiFastutilShortsShort2IntOpenHashMap.put((Short)arrayOfAh2[0].a(), (Integer)arrayOfAh2[1].a());
/* 226:    */      }
/* 227:    */      
/* 228:228 */      a(false, true);
/* 229:229 */      super.fromTagStructure(paramAh[0]);
/* 230:230 */      return; } if (paramAh.a().equals("ShopSpaceStation0")) {
/* 231:231 */      paramAh = (Ah[])paramAh.a();
/* 232:232 */      if ((!jdField_a_of_type_Boolean) && (!"inventory".equals(paramAh[1].a()))) throw new AssertionError();
/* 233:233 */      this.jdField_a_of_type_Mn.fromTagStructure(paramAh[1]);
/* 234:234 */      super.fromTagStructure(paramAh[0]);
/* 235:235 */      a(false, false);
/* 236:236 */      return; }
/* 237:237 */    super.fromTagStructure(paramAh);
/* 238:    */  }
/* 239:    */  
/* 241:    */  public mf getInventory(q paramq)
/* 242:    */  {
/* 243:243 */    return this.jdField_a_of_type_Mn;
/* 244:    */  }
/* 245:    */  
/* 246:    */  public String getName()
/* 247:    */  {
/* 248:248 */    return null;
/* 249:    */  }
/* 250:    */  
/* 253:    */  public final int a(ElementInformation paramElementInformation, int paramInt)
/* 254:    */  {
/* 255:255 */    if (this.jdField_a_of_type_ItUnimiDsiFastutilShortsShort2IntOpenHashMap.containsKey(paramElementInformation.getId())) {
/* 256:256 */      long l = Math.abs(paramInt) * this.jdField_a_of_type_ItUnimiDsiFastutilShortsShort2IntOpenHashMap.get(paramElementInformation.getId());
/* 257:257 */      return (int)Math.min(2147483647L, l);
/* 258:    */    }
/* 259:259 */    System.err.println("Shop Exception: " + this + "; " + getState() + ": price not found for " + paramElementInformation.getId() + ": " + this.jdField_a_of_type_ItUnimiDsiFastutilShortsShort2IntOpenHashMap);
/* 260:260 */    return 0;
/* 261:    */  }
/* 262:    */  
/* 265:    */  public final mn a()
/* 266:    */  {
/* 267:267 */    return this.jdField_a_of_type_Mn;
/* 268:    */  }
/* 269:    */  
/* 275:    */  public void handleExplosion(Transform paramTransform, float paramFloat1, float paramFloat2, lb paramlb, byte paramByte)
/* 276:    */  {
/* 277:277 */    super.handleExplosion(paramTransform, paramFloat1, paramFloat2, paramlb, paramByte);
/* 278:    */    
/* 279:279 */    if (!isOnServer()) {
/* 280:280 */      if ((paramlb != null) && ((paramlb instanceof cw)) && (((cw)paramlb).a().contains(((ct)getState()).a())))
/* 281:    */      {
/* 282:282 */        ((ct)getState()).a().b("WARNING:\nAttacking a shop is not a good idea!\n");
/* 283:    */      }
/* 284:    */    }
/* 285:285 */    else if ((paramlb != null) && ((paramlb instanceof Sendable))) {
/* 286:286 */      a((Sendable)paramlb);
/* 287:    */    }
/* 288:    */  }
/* 289:    */  
/* 290:    */  private void a(Sendable paramSendable)
/* 291:    */  {
/* 292:292 */    if ((paramSendable != null) && ((paramSendable instanceof cw)) && (((cw)paramSendable).a().size() > 0))
/* 293:    */    {
/* 294:294 */      if (System.currentTimeMillis() - this.jdField_d_of_type_Long > 2000L)
/* 295:    */      {
/* 296:296 */        for (lE locallE : ((cw)paramSendable).a()) {
/* 297:297 */          int i = 0;
/* 298:    */          
/* 299:299 */          if (this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2IntOpenHashMap.containsKey(locallE.getId())) {
/* 300:300 */            i = this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2IntOpenHashMap.get(locallE.getId());
/* 301:    */          }
/* 302:302 */          i++;
/* 303:303 */          if (i < 3) {
/* 304:304 */            System.err.println("STRIKES: " + i);
/* 305:305 */            if (i <= 1) {
/* 306:306 */              locallE.a(new ServerMessage("####### Transmission Start\n\nCease fire immediately!\n\n####### Transmission End", 2, locallE.getId()));
/* 307:    */            }
/* 308:    */            else
/* 309:    */            {
/* 310:310 */              locallE.a(new ServerMessage("####### Transmission Start\n\nCease fire immediately!\nThis is the last warning!\n\n####### Transmission End", 3, locallE.getId()));
/* 311:    */            }
/* 312:    */            
/* 315:315 */            this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2IntOpenHashMap.put(locallE.getId(), i);
/* 317:    */          }
/* 318:318 */          else if (System.currentTimeMillis() - this.e > 40000L) {
/* 319:319 */            locallE.a(new ServerMessage("####### Transmission Start\n\nFleet dispatched!\n\n####### Transmission End", 3, locallE.getId()));
/* 320:    */            
/* 322:322 */            this.e = System.currentTimeMillis();
/* 323:    */            
/* 325:325 */            ((vg)getState()).a().a((mF)paramSendable);
/* 326:    */          }
/* 327:    */          else {
/* 328:328 */            switch (Universe.getRandom().nextInt(7)) {
/* 329:    */            case 0: 
/* 330:330 */              locallE.a(new ServerMessage("####### Transmission Start\n\nThe Fleet is on the way to kill you!\n\n####### Transmission End", 3, locallE.getId()));
/* 331:    */              
/* 333:333 */              break;
/* 334:334 */            case 1:  locallE.a(new ServerMessage("####### Transmission Start\n\nThe Traiding Guild will\neliminate you!\n\n####### Transmission End", 3, locallE.getId()));
/* 335:    */              
/* 338:338 */              break;
/* 339:339 */            case 2:  locallE.a(new ServerMessage("####### Transmission Start\n\nAre you suicidal?!\n\n####### Transmission End", 3, locallE.getId()));
/* 340:    */              
/* 342:342 */              break;
/* 343:343 */            case 3:  locallE.a(new ServerMessage("####### Transmission Start\n\nIn space, nobody can hear you scream!\n\n####### Transmission End", 3, locallE.getId()));
/* 344:    */              
/* 346:346 */              break;
/* 347:347 */            case 4:  locallE.a(new ServerMessage("####### Transmission Start\n\nFreeze!\n\n####### Transmission End", 3, locallE.getId()));
/* 348:    */              
/* 350:350 */              break;
/* 351:351 */            case 5:  locallE.a(new ServerMessage("####### Transmission Start\n\nPlease pull over, sir!\n\n####### Transmission End", 3, locallE.getId()));
/* 352:    */              
/* 354:354 */              break;
/* 355:355 */            case 6:  locallE.a(new ServerMessage("####### Transmission Start\n\nNo soup for YOU!\n\n####### Transmission End", 3, locallE.getId()));
/* 356:    */            }
/* 357:    */            
/* 358:    */          }
/* 359:    */        }
/* 360:    */        
/* 367:367 */        this.jdField_d_of_type_Long = System.currentTimeMillis();
/* 368:    */      }
/* 369:    */    }
/* 370:    */  }
/* 371:    */  
/* 376:    */  public void handleHit(CollisionWorld.ClosestRayResultCallback paramClosestRayResultCallback, lb paramlb, float paramFloat)
/* 377:    */  {
/* 378:378 */    if (!isOnServer())
/* 379:    */    {
/* 385:385 */      ((ct)getState()).a().a().a(paramClosestRayResultCallback);
/* 386:386 */      (
/* 387:387 */        paramlb = new Transform()).setIdentity();
/* 388:388 */      paramlb.origin.set(paramClosestRayResultCallback.hitPointWorld);
/* 389:389 */      xe.a("0022_spaceship enemy - hit no explosion metallic impact on enemy ship", paramlb, 5.0F);
/* 390:390 */      return; }
/* 391:391 */    if ((paramlb != null) && ((paramlb instanceof Sendable))) {
/* 392:392 */      a((Sendable)paramlb);
/* 393:    */    }
/* 394:    */  }
/* 395:    */  
/* 397:397 */  private Int2IntOpenHashMap jdField_a_of_type_ItUnimiDsiFastutilIntsInt2IntOpenHashMap = new Int2IntOpenHashMap();
/* 398:    */  
/* 403:    */  public void initFromNetworkObject(NetworkObject paramNetworkObject)
/* 404:    */  {
/* 405:405 */    super.initFromNetworkObject(paramNetworkObject);
/* 406:    */    
/* 407:407 */    if (!isOnServer()) {
/* 408:408 */      a();
/* 409:409 */      paramNetworkObject = (NetworkShop)paramNetworkObject;
/* 410:410 */      this.jdField_a_of_type_Mn.a(paramNetworkObject);
/* 411:    */    }
/* 412:    */  }
/* 413:    */  
/* 417:    */  public void initialize()
/* 418:    */  {
/* 419:419 */    super.initialize();
/* 420:420 */    this.jdField_a_of_type_Mn = new mn(this, new q(0, 0, 0));
/* 421:    */    
/* 422:422 */    setMass(0.0F);
/* 423:    */  }
/* 424:    */  
/* 428:    */  private boolean a(kh paramkh)
/* 429:    */  {
/* 430:430 */    if (paramkh.getSectorId() != getSectorId()) {
/* 431:431 */      return false;
/* 432:    */    }
/* 433:433 */    paramkh.getTransformedAABB(this.jdField_c_of_type_JavaxVecmathVector3f, this.jdField_d_of_type_JavaxVecmathVector3f, 0.0F, new Vector3f(), new Vector3f());
/* 434:434 */    if (getSegmentBuffer().b()) {
/* 435:435 */      this.jdField_d_of_type_JavaxVecmathVector3f.sub(this.jdField_c_of_type_JavaxVecmathVector3f);
/* 436:436 */      this.jdField_d_of_type_JavaxVecmathVector3f.scale(0.5F);
/* 437:437 */      this.jdField_c_of_type_JavaxVecmathVector3f.add(this.jdField_d_of_type_JavaxVecmathVector3f);
/* 438:438 */      this.jdField_c_of_type_JavaxVecmathVector3f.sub(getWorldTransform().origin);
/* 439:    */      
/* 440:440 */      return this.jdField_c_of_type_JavaxVecmathVector3f.length() < 64.0F;
/* 441:    */    }
/* 442:442 */    paramkh = new Vector3f(getSegmentBuffer().a().jdField_a_of_type_JavaxVecmathVector3f);
/* 443:443 */    Vector3f localVector3f = new Vector3f(getSegmentBuffer().a().jdField_b_of_type_JavaxVecmathVector3f);
/* 444:    */    
/* 445:445 */    if (!GlUtil.a(paramkh, localVector3f)) {
/* 446:446 */      getSegmentBuffer().a();
/* 447:447 */      return false;
/* 448:    */    }
/* 449:449 */    AabbUtil2.transformAabb(paramkh, localVector3f, 64.0F, getWorldTransform(), this.jdField_a_of_type_JavaxVecmathVector3f, this.jdField_b_of_type_JavaxVecmathVector3f);
/* 450:    */    
/* 452:452 */    return AabbUtil2.testAabbAgainstAabb2(this.jdField_a_of_type_JavaxVecmathVector3f, this.jdField_b_of_type_JavaxVecmathVector3f, this.jdField_c_of_type_JavaxVecmathVector3f, this.jdField_d_of_type_JavaxVecmathVector3f);
/* 453:    */  }
/* 454:    */  
/* 455:    */  public final boolean a(String[] paramArrayOfString, q paramq)
/* 456:    */  {
/* 457:457 */    return false;
/* 458:    */  }
/* 459:    */  
/* 460:    */  public void newNetworkObject() {
/* 461:461 */    setNetworkObject(new NetworkShop(getState(), this));
/* 462:    */  }
/* 463:    */  
/* 468:    */  protected void onCoreDestroyed(lb paramlb) {}
/* 469:    */  
/* 474:    */  public void onSectorInactiveClient()
/* 475:    */  {
/* 476:476 */    super.onSectorInactiveClient();
/* 477:477 */    synchronized (getState().getLocalAndRemoteObjectContainer().getLocalObjects()) {
/* 478:478 */      for (Iterator localIterator = getState().getLocalAndRemoteObjectContainer().getLocalObjects().values().iterator(); localIterator.hasNext();) { Sendable localSendable;
/* 479:479 */        if (((localSendable = (Sendable)localIterator.next()) instanceof kh)) {
/* 480:480 */          if ((a((kh)localSendable)) && (((kh)localSendable).getSectorId() == getSectorId())) {
/* 481:481 */            ((kh)localSendable).a().add(this);
/* 482:    */          } else {
/* 483:483 */            ((kh)localSendable).a().remove(this);
/* 484:    */          }
/* 485:    */        }
/* 486:    */      }
/* 487:    */      return;
/* 488:    */    }
/* 489:    */  }
/* 490:    */  
/* 491:    */  public String printInventories()
/* 492:    */  {
/* 493:493 */    return this.jdField_a_of_type_Mn.toString();
/* 494:    */  }
/* 495:    */  
/* 496:    */  private void a()
/* 497:    */  {
/* 498:498 */    if ((!jdField_a_of_type_Boolean) && (isOnServer())) { throw new AssertionError();
/* 499:    */    }
/* 500:500 */    for (int i = 0; i < ((NetworkShop)super.getNetworkObject()).pricesUpdateBuffer.getReceiveBuffer().size(); i++) {
/* 501:501 */      RemoteIntArray localRemoteIntArray = (RemoteIntArray)((NetworkShop)super.getNetworkObject()).pricesUpdateBuffer.getReceiveBuffer().get(i);
/* 502:502 */      this.jdField_a_of_type_ItUnimiDsiFastutilShortsShort2IntOpenHashMap.put((short)localRemoteIntArray.getIntArray()[0], localRemoteIntArray.getIntArray()[1]);
/* 503:    */    }
/* 504:    */  }
/* 505:    */  
/* 511:    */  public void sendInventoryModification(int paramInt, q paramq)
/* 512:    */  {
/* 513:513 */    (paramq = new RemoteIntArray(3, (NetworkShop)super.getNetworkObject())).set(0, paramInt);
/* 514:514 */    if (!this.jdField_a_of_type_Mn.a(paramInt)) {
/* 515:515 */      paramq.set(1, this.jdField_a_of_type_Mn.a(paramInt));
/* 516:516 */      paramq.set(2, this.jdField_a_of_type_Mn.a(paramInt));
/* 517:    */    }
/* 518:518 */    ((NetworkShop)super.getNetworkObject()).getInventoryUpdateBuffer().add(paramq);
/* 519:    */  }
/* 520:    */  
/* 521:    */  private void b() {
/* 522:522 */    for (Map.Entry localEntry : this.jdField_a_of_type_ItUnimiDsiFastutilShortsShort2IntOpenHashMap.entrySet()) {
/* 523:    */      RemoteIntArray localRemoteIntArray;
/* 524:524 */      (localRemoteIntArray = new RemoteIntArray(2, (NetworkShop)super.getNetworkObject())).set(0, ((Short)localEntry.getKey()).intValue());
/* 525:525 */      localRemoteIntArray.set(1, ((Integer)localEntry.getValue()).intValue());
/* 526:526 */      ((NetworkShop)super.getNetworkObject()).pricesUpdateBuffer.add(localRemoteIntArray);
/* 527:    */    }
/* 528:    */  }
/* 529:    */  
/* 538:    */  public void startCreatorThread()
/* 539:    */  {
/* 540:540 */    if (getCreatorThread() == null) {
/* 541:541 */      setCreatorThread(new kL(this));
/* 542:    */    }
/* 543:    */  }
/* 544:    */  
/* 546:    */  public String toNiceString()
/* 547:    */  {
/* 548:548 */    return "Shop (" + getTotalElements() + ")";
/* 549:    */  }
/* 550:    */  
/* 556:    */  public Ah toTagStructure()
/* 557:    */  {
/* 558:558 */    Ah localAh = this.jdField_a_of_type_Mn.toTagStructure(); Ah[] 
/* 559:    */    
/* 560:560 */      tmp20_17 = new Ah[this.jdField_a_of_type_ItUnimiDsiFastutilShortsShort2IntOpenHashMap.size() + 1]; Ah[] 
/* 561:561 */      tmp21_20 = tmp20_17;Ah[] arrayOfAh = tmp21_20;tmp20_17[(tmp21_20.length - 1)] = new Ah(Aj.a, null, null);
/* 562:    */    
/* 563:563 */    int i = 0;
/* 564:564 */    for (Map.Entry localEntry : this.jdField_a_of_type_ItUnimiDsiFastutilShortsShort2IntOpenHashMap.entrySet()) {
/* 565:565 */      arrayOfAh[i] = new Ah(Aj.n, null, new Ah[] { new Ah(Aj.c, null, localEntry.getKey()), new Ah(Aj.d, null, localEntry.getValue()), new Ah(Aj.a, "fin", null) });
/* 566:566 */      i++;
/* 567:    */    }
/* 568:    */    
/* 569:569 */    return new Ah(Aj.n, "ShopSpaceStation1", new Ah[] { super.toTagStructure(), localAh, new Ah(Aj.g, null, Double.valueOf(this.jdField_a_of_type_Double)), new Ah(Aj.e, null, Long.valueOf(this.jdField_c_of_type_Long)), new Ah(Aj.n, null, arrayOfAh), new Ah(Aj.a, "fin", null) });
/* 570:    */  }
/* 571:    */  
/* 576:    */  public void updateFromNetworkObject(NetworkObject paramNetworkObject)
/* 577:    */  {
/* 578:578 */    super.updateFromNetworkObject(paramNetworkObject);
/* 579:579 */    paramNetworkObject = (NetworkShop)paramNetworkObject;
/* 580:580 */    this.jdField_a_of_type_Mn.a(paramNetworkObject);
/* 581:    */    
/* 582:582 */    if (!isOnServer()) {
/* 583:583 */      a();
/* 584:    */    }
/* 585:    */  }
/* 586:    */  
/* 590:    */  public void updateLocal(xq paramxq)
/* 591:    */  {
/* 592:592 */    super.updateLocal(paramxq);
/* 593:    */    
/* 594:    */    long l1;
/* 595:595 */    if ((l1 = System.currentTimeMillis()) > this.jdField_b_of_type_Long + 500L) {
/* 596:596 */      synchronized (getState().getLocalAndRemoteObjectContainer().getLocalObjects()) {
/* 597:597 */        for (Iterator localIterator = getState().getLocalAndRemoteObjectContainer().getLocalUpdatableObjects().values().iterator(); localIterator.hasNext();) { Sendable localSendable;
/* 598:598 */          if (((localSendable = (Sendable)localIterator.next()) instanceof kh)) {
/* 599:599 */            if ((a((kh)localSendable)) && (((kh)localSendable).getSectorId() == getSectorId())) {
/* 600:600 */              ((kh)localSendable).a().add(this);
/* 601:    */            } else {
/* 602:602 */              ((kh)localSendable).a().remove(this);
/* 603:    */            }
/* 604:    */          }
/* 605:    */        }
/* 606:606 */        this.jdField_b_of_type_Long = l1;
/* 607:    */      }
/* 608:    */    }
/* 609:    */    
/* 610:610 */    if ((isOnServer()) && ((vg.jdField_a_of_type_Boolean) || (l1 > this.jdField_c_of_type_Long + jdField_a_of_type_Long)))
/* 611:    */    {
/* 612:612 */      long l2 = System.currentTimeMillis();
/* 613:613 */      a(true, false);
/* 614:    */      long l3;
/* 615:615 */      if ((l3 = System.currentTimeMillis() - l2) > 3L) {
/* 616:616 */        System.err.println("[SERVER] updating prices for: " + this + " took " + l3);
/* 617:    */      }
/* 618:618 */      jdField_a_of_type_Long = (300000.0D + Math.random() * 10.0D * 60000.0D);
/* 619:619 */      this.jdField_c_of_type_Long = l1;
/* 620:    */    }
/* 621:    */  }
/* 622:    */  
/* 623:    */  private void a(boolean paramBoolean1, boolean paramBoolean2) {
/* 624:624 */    if ((!jdField_a_of_type_Boolean) && (!isOnServer())) throw new AssertionError();
/* 625:625 */    System.err.println("[SERVER] update prices for " + this);
/* 626:626 */    short[] arrayOfShort; int i = (arrayOfShort = ElementKeyMap.typeList()).length; for (int j = 0; j < i; j++) { Short localShort = Short.valueOf(arrayOfShort[j]);
/* 627:627 */      if ((!paramBoolean2) || (!this.jdField_a_of_type_ItUnimiDsiFastutilShortsShort2IntOpenHashMap.containsKey(localShort))) {
/* 628:628 */        ElementInformation localElementInformation1 = ElementKeyMap.getInfo(localShort.shortValue());
/* 629:    */        
/* 631:631 */        ElementInformation localElementInformation2 = localElementInformation1;kf localkf = this; if ((!jdField_a_of_type_Boolean) && (!localkf.isOnServer())) throw new AssertionError(); int k = (k = localkf.jdField_a_of_type_Mn.a(localElementInformation2.getId())) >= 0 ? localkf.jdField_a_of_type_Mn.a(k) : 0;double d1 = Math.pow(Math.max(1, mn.e() - k), 0.35D) * 0.1000000014901161D * localElementInformation2.getPrice();double d2 = Math.max(localElementInformation2.getPrice(), d1 * localkf.jdField_a_of_type_Double); if (localElementInformation2.getId() == 1) System.err.println("CORE PRICE DIF: " + d1 + " -> " + d2 + "; basic " + localkf.jdField_a_of_type_Double); this.jdField_a_of_type_ItUnimiDsiFastutilShortsShort2IntOpenHashMap.put(localElementInformation1.getId(), (int)Math.min(2147483647L, d2));
/* 632:632 */        if (localShort.shortValue() == 1)
/* 633:633 */          System.err.println("CORE PRICE: " + this.jdField_a_of_type_ItUnimiDsiFastutilShortsShort2IntOpenHashMap.get(localElementInformation1.getId()));
/* 634:    */      }
/* 635:    */    }
/* 636:636 */    if (paramBoolean1) {
/* 637:637 */      b();
/* 638:    */    }
/* 639:    */  }
/* 640:    */  
/* 643:    */  public void updateToFullNetworkObject()
/* 644:    */  {
/* 645:645 */    this.jdField_a_of_type_Mn.b((NetworkShop)super.getNetworkObject());
/* 646:646 */    super.updateToFullNetworkObject();
/* 647:647 */    System.currentTimeMillis();
/* 648:    */    
/* 649:649 */    b();
/* 650:    */  }
/* 651:    */  
/* 653:    */  public ml getInventoryNetworkObject()
/* 654:    */  {
/* 655:655 */    return (NetworkShop)super.getNetworkObject();
/* 656:    */  }
/* 657:    */  
/* 659:    */  public HashMap getInventories()
/* 660:    */  {
/* 661:    */    HashMap localHashMap;
/* 662:662 */    (localHashMap = new HashMap(1)).put(new q(), this.jdField_a_of_type_Mn);
/* 663:663 */    return localHashMap;
/* 664:    */  }
/* 665:    */  
/* 666:666 */  public void getRelationColor(lZ paramlZ, Vector4f paramVector4f, float paramFloat) { switch (kg.a[paramlZ.ordinal()]) {
/* 667:    */    case 1: 
/* 668:668 */      paramVector4f.x = (paramFloat + 0.3F);
/* 669:669 */      paramVector4f.y = 0.0F;
/* 670:670 */      paramVector4f.z = 0.3F;
/* 671:671 */      return;
/* 672:    */    
/* 673:    */    case 2: 
/* 674:674 */      paramVector4f.x = 0.5F;
/* 675:675 */      paramVector4f.y = paramFloat;
/* 676:676 */      paramVector4f.z = 0.5F;
/* 677:677 */      return;
/* 678:    */    
/* 679:    */    case 3: 
/* 680:680 */      paramVector4f.x = 1.0F;
/* 681:681 */      paramVector4f.y = paramFloat;
/* 682:682 */      paramVector4f.z = 1.0F;
/* 683:    */    }
/* 684:    */    
/* 685:    */  }
/* 686:    */  
/* 688:    */  public void sendInventoryModification(Collection paramCollection, q paramq)
/* 689:    */  {
/* 690:    */    mf localmf;
/* 691:691 */    if ((localmf = getInventory(paramq)) != null) {
/* 692:692 */      paramCollection = new mi(paramCollection, localmf, paramq);
/* 693:    */      
/* 694:694 */      ((NetworkShop)super.getNetworkObject()).getInventoryMultModBuffer().add(new RemoteInventoryMultMod(paramCollection, (NetworkShop)super.getNetworkObject()));
/* 695:695 */      return;
/* 696:    */    }
/* 697:697 */    try { throw new IllegalArgumentException("[INVENTORY] Exception: tried to send inventory " + paramq);
/* 698:698 */    } catch (Exception localException) { localException;
/* 699:    */    }
/* 700:    */  }
/* 701:    */  
/* 704:    */  protected String getSegmentControllerTypeString()
/* 705:    */  {
/* 706:706 */    return "Shop";
/* 707:    */  }
/* 708:    */  
/* 709:    */  public final String a()
/* 710:    */  {
/* 711:711 */    return "0022_ambience loop - shop machinery (loop)";
/* 712:    */  }
/* 713:    */  
/* 714:    */  public final String b()
/* 715:    */  {
/* 716:716 */    return "0022_ambience loop - shop machinery (loop)";
/* 717:    */  }
/* 718:    */  
/* 729:    */  public final float b()
/* 730:    */  {
/* 731:731 */    return 1.5F;
/* 732:    */  }
/* 733:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     kf
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */