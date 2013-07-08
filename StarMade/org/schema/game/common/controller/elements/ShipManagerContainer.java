/*   1:    */package org.schema.game.common.controller.elements;
/*   2:    */
/*   3:    */import com.bulletphysics.linearmath.Transform;
/*   4:    */import ct;
/*   5:    */import dj;
/*   6:    */import eH;
/*   7:    */import eI;
/*   8:    */import eJ;
/*   9:    */import es;
/*  10:    */import ij;
/*  11:    */import java.io.PrintStream;
/*  12:    */import java.util.ArrayList;
/*  13:    */import java.util.Collection;
/*  14:    */import javax.vecmath.Vector3f;
/*  15:    */import kd;
/*  16:    */import kp;
/*  17:    */import lA;
/*  18:    */import lE;
/*  19:    */import le;
/*  20:    */import ml;
/*  21:    */import org.schema.game.common.controller.SegmentController;
/*  22:    */import org.schema.game.common.controller.elements.cloaking.CloakingElementManager;
/*  23:    */import org.schema.game.common.controller.elements.dockingBeam.DockingBeamElementManager;
/*  24:    */import org.schema.game.common.controller.elements.dockingBlock.DockingBlockManagerInterface;
/*  25:    */import org.schema.game.common.controller.elements.dockingBlock.fixed.FixedDockingBlockElementManager;
/*  26:    */import org.schema.game.common.controller.elements.dockingBlock.turret.TurretDockingBlockElementManager;
/*  27:    */import org.schema.game.common.controller.elements.door.DoorCollectionManager;
/*  28:    */import org.schema.game.common.controller.elements.explosive.ExplosiveCollectionManager;
/*  29:    */import org.schema.game.common.controller.elements.explosive.ExplosiveElementManager;
/*  30:    */import org.schema.game.common.controller.elements.harvest.SalvageElementManager;
/*  31:    */import org.schema.game.common.controller.elements.jamming.JammingCollectionManager;
/*  32:    */import org.schema.game.common.controller.elements.jamming.JammingElementManager;
/*  33:    */import org.schema.game.common.controller.elements.missile.dumb.DumbMissileElementManager;
/*  34:    */import org.schema.game.common.controller.elements.missile.fireandforget.FafoMissileElementManager;
/*  35:    */import org.schema.game.common.controller.elements.missile.heatseeking.HeatMissileElementManager;
/*  36:    */import org.schema.game.common.controller.elements.power.PowerCollectionManager;
/*  37:    */import org.schema.game.common.controller.elements.powerbeamdrain.PowerDrainElementManager;
/*  38:    */import org.schema.game.common.controller.elements.powerbeamsupply.PowerSupplyElementManager;
/*  39:    */import org.schema.game.common.controller.elements.powercap.PowerCapacityCollectionManager;
/*  40:    */import org.schema.game.common.controller.elements.repair.RepairElementManager;
/*  41:    */import org.schema.game.common.controller.elements.shield.ShieldCollectionManager;
/*  42:    */import org.schema.game.common.controller.elements.thrust.ThrusterElementManager;
/*  43:    */import org.schema.game.common.controller.elements.weapon.WeaponElementManager;
/*  44:    */import org.schema.game.common.data.world.Segment;
/*  45:    */import org.schema.game.network.objects.NetworkDoorInterface;
/*  46:    */import org.schema.game.network.objects.NetworkShip;
/*  47:    */import org.schema.schine.network.objects.remote.RemoteBoolean;
/*  48:    */import org.schema.schine.network.objects.remote.RemoteBuffer;
/*  49:    */import org.schema.schine.network.objects.remote.RemoteVector4i;
/*  50:    */import q;
/*  51:    */import s;
/*  52:    */import xm;
/*  53:    */import xq;
/*  54:    */
/*  79:    */public class ShipManagerContainer
/*  80:    */  extends ManagerContainer
/*  81:    */  implements DoorContainerInterface, ManagerThrustInterface, ShieldContainerInterface, WeaponManagerInterface, DockingBlockManagerInterface
/*  82:    */{
/*  83:    */  private DockingBeamElementManager dockingBeam;
/*  84:    */  private final PowerAddOn powerAddOn;
/*  85:    */  private ManagerModuleSingle shields;
/*  86:    */  private ManagerModuleSingle thrust;
/*  87:    */  private ManagerModuleSingle cloaking;
/*  88:    */  private ManagerModuleSingle explosive;
/*  89:    */  private ManagerModuleSingle jamming;
/*  90:    */  private ManagerModuleSingle power;
/*  91:    */  private ManagerModuleSingle powerCapacity;
/*  92:    */  private ManagerModuleSingle door;
/*  93:    */  private ManagerModuleCollection salvage;
/*  94:    */  private ManagerModuleCollection powerDrain;
/*  95:    */  private ManagerModuleCollection powerSupply;
/*  96:    */  private ManagerModuleCollection turretDockingBlock;
/*  97:    */  private ManagerModuleCollection fixedDockingBlock;
/*  98:    */  private ManagerModuleCollection repair;
/*  99:    */  private ManagerModuleCollection weapon;
/* 100:    */  private ManagerModuleCollection dumbMissile;
/* 101:    */  private ManagerModuleCollection heatMissile;
/* 102:    */  private ManagerModuleCollection fafoMissile;
/* 103:    */  
/* 104:    */  public ManagerModuleSingle getDoor()
/* 105:    */  {
/* 106:106 */    return this.door;
/* 107:    */  }
/* 108:    */  
/* 111:    */  public ManagerModuleCollection getTurretDockingBlock()
/* 112:    */  {
/* 113:113 */    return this.turretDockingBlock;
/* 114:    */  }
/* 115:    */  
/* 118:    */  public ManagerModuleCollection getFixedDockingBlock()
/* 119:    */  {
/* 120:120 */    return this.fixedDockingBlock;
/* 121:    */  }
/* 122:    */  
/* 136:136 */  private final ArrayList cockpits = new ArrayList();
/* 137:    */  
/* 140:    */  public ShipManagerContainer(kd paramkd)
/* 141:    */  {
/* 142:142 */    super(paramkd);
/* 143:143 */    this.powerAddOn = new PowerAddOn(this, paramkd);
/* 144:    */  }
/* 145:    */  
/* 146:    */  public CloakingElementManager getCloakElementManager() {
/* 147:147 */    return (CloakingElementManager)this.cloaking.getElementManager();
/* 148:    */  }
/* 149:    */  
/* 151:    */  public ManagerModuleSingle getCloaking()
/* 152:    */  {
/* 153:153 */    return this.cloaking;
/* 154:    */  }
/* 155:    */  
/* 157:    */  public ArrayList getCockpits()
/* 158:    */  {
/* 159:159 */    return this.cockpits;
/* 160:    */  }
/* 161:    */  
/* 167:    */  public DockingBeamElementManager getDockingBeam()
/* 168:    */  {
/* 169:169 */    return this.dockingBeam;
/* 170:    */  }
/* 171:    */  
/* 175:    */  public Collection getDockingBlock()
/* 176:    */  {
/* 177:177 */    return this.dockingModules;
/* 178:    */  }
/* 179:    */  
/* 183:    */  public ManagerModuleCollection getDumbMissile()
/* 184:    */  {
/* 185:185 */    return this.dumbMissile;
/* 186:    */  }
/* 187:    */  
/* 189:    */  public ManagerModuleSingle getExplosive()
/* 190:    */  {
/* 191:191 */    return this.explosive;
/* 192:    */  }
/* 193:    */  
/* 194:194 */  public ExplosiveCollectionManager getExplosiveCollectionManager() { return (ExplosiveCollectionManager)this.explosive.getCollectionManager(); }
/* 195:    */  
/* 196:    */  public ExplosiveElementManager getExplosiveElementManager()
/* 197:    */  {
/* 198:198 */    return (ExplosiveElementManager)this.explosive.getElementManager();
/* 199:    */  }
/* 200:    */  
/* 203:    */  public ManagerModuleCollection getFafoMissile()
/* 204:    */  {
/* 205:205 */    return this.fafoMissile;
/* 206:    */  }
/* 207:    */  
/* 210:    */  public ManagerModuleCollection getHeatMissile()
/* 211:    */  {
/* 212:212 */    return this.heatMissile;
/* 213:    */  }
/* 214:    */  
/* 217:    */  public ManagerModuleSingle getJamming()
/* 218:    */  {
/* 219:219 */    return this.jamming;
/* 220:    */  }
/* 221:    */  
/* 222:    */  public JammingElementManager getJammingElementManager() {
/* 223:223 */    return (JammingElementManager)this.jamming.getElementManager();
/* 224:    */  }
/* 225:    */  
/* 228:    */  public ManagerModuleSingle getPower()
/* 229:    */  {
/* 230:230 */    return this.power;
/* 231:    */  }
/* 232:    */  
/* 233:    */  public PowerCollectionManager getPowerManager()
/* 234:    */  {
/* 235:235 */    return (PowerCollectionManager)this.power.getCollectionManager();
/* 236:    */  }
/* 237:    */  
/* 240:    */  public ManagerModuleCollection getRepair()
/* 241:    */  {
/* 242:242 */    return this.repair;
/* 243:    */  }
/* 244:    */  
/* 248:    */  public ManagerModuleCollection getSalvage()
/* 249:    */  {
/* 250:250 */    return this.salvage;
/* 251:    */  }
/* 252:    */  
/* 253:    */  public ShieldCollectionManager getShieldManager() {
/* 254:254 */    return (ShieldCollectionManager)this.shields.getCollectionManager();
/* 255:    */  }
/* 256:    */  
/* 259:    */  public ManagerModuleSingle getShields()
/* 260:    */  {
/* 261:261 */    return this.shields;
/* 262:    */  }
/* 263:    */  
/* 266:    */  public ManagerModuleSingle getThrust()
/* 267:    */  {
/* 268:268 */    return this.thrust;
/* 269:    */  }
/* 270:    */  
/* 271:    */  public ThrusterElementManager getThrusterElementManager() {
/* 272:272 */    return (ThrusterElementManager)this.thrust.getElementManager();
/* 273:    */  }
/* 274:    */  
/* 277:    */  public ManagerModuleCollection getWeapon()
/* 278:    */  {
/* 279:279 */    return this.weapon;
/* 280:    */  }
/* 281:    */  
/* 282:    */  public void handle(lA paramlA)
/* 283:    */  {
/* 284:284 */    super.handle(paramlA);
/* 285:285 */    if (paramlA.a.b()) {
/* 286:286 */      this.dockingBeam.handle(paramlA);
/* 287:    */    }
/* 288:    */  }
/* 289:    */  
/* 290:    */  public void initialize()
/* 291:    */  {
/* 292:292 */    this.dockingBeam = new DockingBeamElementManager(getSegmentController());
/* 293:    */    
/* 295:295 */    this.modules.add(this.shields = new ManagerModuleSingle(new VoidElementManager(new ShieldCollectionManager(getSegmentController()), getSegmentController()), (short)0, (short)3));
/* 296:    */    
/* 297:297 */    this.modules.add(this.thrust = new ManagerModuleSingle(new ThrusterElementManager(getSegmentController()), (short)1, (short)8));
/* 298:    */    
/* 299:299 */    this.modules.add(this.door = new ManagerModuleSingle(new VoidElementManager(new DoorCollectionManager(getSegmentController()), getSegmentController()), (short)0, (short)122));
/* 300:    */    
/* 301:301 */    this.modules.add(this.cloaking = new ManagerModuleSingle(new CloakingElementManager(getSegmentController()), (short)1, (short)22));
/* 302:    */    
/* 303:303 */    this.modules.add(this.explosive = new ManagerModuleSingle(new ExplosiveElementManager(getSegmentController()), (short)1, (short)14));
/* 304:    */    
/* 305:305 */    this.modules.add(this.jamming = new ManagerModuleSingle(new JammingElementManager(new JammingCollectionManager(getSegmentController()), getSegmentController()), (short)1, (short)15));
/* 306:    */    
/* 307:307 */    this.modules.add(this.power = new ManagerModuleSingle(new VoidElementManager(new PowerCollectionManager(getSegmentController()), getSegmentController()), (short)0, (short)2));
/* 308:    */    
/* 309:309 */    this.modules.add(this.powerCapacity = new ManagerModuleSingle(new VoidElementManager(new PowerCapacityCollectionManager(getSegmentController()), getSegmentController()), (short)0, (short)331));
/* 310:    */    
/* 311:311 */    this.modules.add(this.salvage = new ManagerModuleCollection(new SalvageElementManager(getSegmentController()), (short)4, (short)24));
/* 312:    */    
/* 313:313 */    this.modules.add(this.powerDrain = new ManagerModuleCollection(new PowerDrainElementManager(getSegmentController()), (short)332, (short)333));
/* 314:    */    
/* 315:315 */    this.modules.add(this.powerSupply = new ManagerModuleCollection(new PowerSupplyElementManager(getSegmentController()), (short)334, (short)335));
/* 316:    */    
/* 317:317 */    this.modules.add(this.turretDockingBlock = new ManagerModuleCollection(new TurretDockingBlockElementManager(getSegmentController()), (short)7, (short)88));
/* 318:    */    
/* 319:319 */    this.modules.add(this.fixedDockingBlock = new ManagerModuleCollection(new FixedDockingBlockElementManager(getSegmentController()), (short)289, (short)290));
/* 320:    */    
/* 321:321 */    this.modules.add(this.repair = new ManagerModuleCollection(new RepairElementManager(getSegmentController()), (short)39, (short)30));
/* 322:    */    
/* 323:323 */    this.modules.add(this.weapon = new ManagerModuleCollection(new WeaponElementManager(getSegmentController()), (short)6, (short)16));
/* 324:    */    
/* 325:325 */    this.modules.add(this.dumbMissile = new ManagerModuleCollection(new DumbMissileElementManager(getSegmentController()), (short)38, (short)32));
/* 326:    */    
/* 327:327 */    this.modules.add(this.fafoMissile = new ManagerModuleCollection(new FafoMissileElementManager(getSegmentController()), (short)54, (short)48));
/* 328:    */    
/* 329:329 */    this.modules.add(this.heatMissile = new ManagerModuleCollection(new HeatMissileElementManager(getSegmentController()), (short)46, (short)40));
/* 330:    */  }
/* 331:    */  
/* 332:    */  public boolean isCloaked() {
/* 333:333 */    return getCloakElementManager().isCloaked();
/* 334:    */  }
/* 335:    */  
/* 336:    */  public boolean isJamming() {
/* 337:337 */    return getJammingElementManager().isJamming();
/* 338:    */  }
/* 339:    */  
/* 342:    */  public DoorCollectionManager getDoorManager()
/* 343:    */  {
/* 344:344 */    return (DoorCollectionManager)this.door.getCollectionManager();
/* 345:    */  }
/* 346:    */  
/* 347:    */  public void onAction() {
/* 348:348 */    ((CloakingElementManager)this.cloaking.getElementManager()).stopCloak();
/* 349:    */  }
/* 350:    */  
/* 354:    */  public void onAddedElement(short paramShort, int paramInt, byte paramByte1, byte paramByte2, byte paramByte3, Segment paramSegment)
/* 355:    */  {
/* 356:356 */    super.onAddedElement(paramShort, paramInt, paramByte1, paramByte2, paramByte3, paramSegment);
/* 357:    */    
/* 358:358 */    switch (paramShort)
/* 359:    */    {
/* 360:    */    case 47: 
/* 361:361 */      paramShort = paramSegment.a(paramByte1, paramByte2, paramByte3, new q());
/* 362:    */      
/* 363:363 */      if (!getCockpits().contains(paramShort)) {
/* 364:364 */        getCockpits().add(paramShort); return;
/* 365:    */      }
/* 366:    */      
/* 367:    */      break;
/* 368:    */    case 121: 
/* 369:369 */      ((kd)getSegmentController()).a().a(new le(paramSegment, paramByte1, paramByte2, paramByte3));
/* 370:    */    }
/* 371:    */    
/* 372:    */  }
/* 373:    */  
/* 376:    */  public void onHitNotice()
/* 377:    */  {
/* 378:378 */    if (((kd)getSegmentController()).isOnServer()) {
/* 379:379 */      if (((isCloaked()) || (isJamming())) && 
/* 380:380 */        (((kd)getSegmentController()).a().onHitNotices.isEmpty())) {
/* 381:381 */        ((CloakingElementManager)this.cloaking.getElementManager()).onHit();
/* 382:382 */        ((JammingElementManager)this.jamming.getElementManager()).onHit();
/* 383:383 */        ((kd)getSegmentController()).a().onHitNotices.add(new RemoteBoolean(true, ((kd)getSegmentController()).a()));
/* 384:    */      }
/* 385:    */    }
/* 386:    */    else {
/* 387:387 */      ((CloakingElementManager)this.cloaking.getElementManager()).onHit();
/* 388:388 */      ((JammingElementManager)this.jamming.getElementManager()).onHit();
/* 389:    */    }
/* 390:    */  }
/* 391:    */  
/* 395:    */  public void onRemovedElement(short paramShort, int paramInt, byte paramByte1, byte paramByte2, byte paramByte3, Segment paramSegment, boolean paramBoolean)
/* 396:    */  {
/* 397:397 */    super.onRemovedElement(paramShort, paramInt, paramByte1, paramByte2, paramByte3, paramSegment, paramBoolean);
/* 398:398 */    switch (paramShort)
/* 399:    */    {
/* 400:    */    case 47: 
/* 401:401 */      paramShort = paramSegment.a(paramByte1, paramByte2, paramByte3, new q());
/* 402:402 */      getCockpits().remove(paramShort);
/* 403:403 */      return;
/* 404:    */    
/* 405:    */    case 121: 
/* 406:406 */      ((kd)getSegmentController()).a().a(null);
/* 407:    */    }
/* 408:    */    
/* 409:    */  }
/* 410:    */  
/* 412:    */  public void handleClientRemoteDoor(q paramq)
/* 413:    */  {
/* 414:414 */    getDoorInterface().getDoorActivate().forceClientUpdates();
/* 415:    */    
/* 416:416 */    (paramq = new s(paramq)).d = -1;
/* 417:417 */    getDoorInterface().getDoorActivate().add(new RemoteVector4i(paramq, ((kd)getSegmentController()).a()));
/* 418:    */  }
/* 419:    */  
/* 420:420 */  private NetworkDoorInterface getDoorInterface() { return ((kd)getSegmentController()).a(); }
/* 421:    */  
/* 429:    */  public void updateLocal(xq paramxq)
/* 430:    */  {
/* 431:431 */    super.updateLocal(paramxq);
/* 432:432 */    this.dockingBeam.update(paramxq);
/* 433:433 */    this.powerAddOn.update(paramxq);
/* 434:    */  }
/* 435:    */  
/* 436:    */  public ml getInventoryNetworkObject()
/* 437:    */  {
/* 438:438 */    return ((kd)getSegmentController()).a();
/* 439:    */  }
/* 440:    */  
/* 484:    */  public double handleShieldHit(Vector3f paramVector3f, SegmentController paramSegmentController, float paramFloat)
/* 485:    */  {
/* 486:486 */    double d1 = getShieldManager().getShields();
/* 487:    */    
/* 488:488 */    onHit((int)paramFloat);
/* 489:    */    double d2;
/* 490:490 */    if (getShieldManager().getShields() <= 0.0D)
/* 491:    */    {
/* 493:493 */      if (paramFloat < d1) {
/* 494:494 */        d2 = d1;
/* 495:    */      } else {
/* 496:496 */        d2 = paramFloat;
/* 497:    */      }
/* 498:    */    } else {
/* 499:499 */      d2 = 0.0D;
/* 500:    */    }
/* 501:    */    
/* 504:504 */    if ((!((kd)getSegmentController()).isOnServer()) && (!xm.a()))
/* 505:    */    {
/* 506:506 */      paramSegmentController = (ct)getState();
/* 507:    */      Transform localTransform;
/* 508:508 */      (localTransform = new Transform()).setIdentity();
/* 509:509 */      localTransform.origin.set(paramVector3f);
/* 510:    */      
/* 512:512 */      ij.a.add(new eH(localTransform, String.valueOf((int)paramFloat), 1.0F, 0.0F, 0.0F));
/* 513:    */      
/* 514:514 */      paramSegmentController.a().a().a(paramVector3f, 4.0F);
/* 515:    */      
/* 517:517 */      if ((paramSegmentController = paramSegmentController.a().a().a(getSegmentController())) != null) {
/* 518:518 */        paramSegmentController.a(paramVector3f);
/* 519:    */      } else {
/* 520:520 */        System.err.println("[CLIENT] ERROR: shield drawer for " + getSegmentController() + " not initialized");
/* 521:    */      }
/* 522:    */    }
/* 523:    */    
/* 524:524 */    return d2;
/* 525:    */  }
/* 526:    */  
/* 528:    */  public PowerCapacityCollectionManager getPowerCapacityManager()
/* 529:    */  {
/* 530:530 */    return (PowerCapacityCollectionManager)this.powerCapacity.getCollectionManager();
/* 531:    */  }
/* 532:    */  
/* 533:    */  public PowerAddOn getPowerAddOn()
/* 534:    */  {
/* 535:535 */    return this.powerAddOn;
/* 536:    */  }
/* 537:    */  
/* 540:    */  public ManagerModuleCollection getPowerDrain()
/* 541:    */  {
/* 542:542 */    return this.powerDrain;
/* 543:    */  }
/* 544:    */  
/* 547:    */  public ManagerModuleCollection getPowerSupply()
/* 548:    */  {
/* 549:549 */    return this.powerSupply;
/* 550:    */  }
/* 551:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.ShipManagerContainer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */