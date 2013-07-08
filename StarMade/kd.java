/*    1:     */import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
/*    2:     */import com.bulletphysics.collision.narrowphase.ManifoldPoint;
/*    3:     */import com.bulletphysics.linearmath.Transform;
/*    4:     */import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*    5:     */import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*    6:     */import java.io.IOException;
/*    7:     */import java.io.PrintStream;
/*    8:     */import java.util.ArrayList;
/*    9:     */import java.util.Collection;
/*   10:     */import java.util.HashSet;
/*   11:     */import java.util.Iterator;
/*   12:     */import java.util.List;
/*   13:     */import java.util.Set;
/*   14:     */import javax.vecmath.Tuple3f;
/*   15:     */import javax.vecmath.Vector3f;
/*   16:     */import javax.vecmath.Vector4f;
/*   17:     */import org.schema.game.common.controller.EditableSendableSegmentController;
/*   18:     */import org.schema.game.common.controller.SegmentController;
/*   19:     */import org.schema.game.common.controller.elements.ParticleHandler;
/*   20:     */import org.schema.game.common.controller.elements.ShipManagerContainer;
/*   21:     */import org.schema.game.common.controller.elements.explosive.ExplosiveCollectionManager;
/*   22:     */import org.schema.game.common.controller.elements.explosive.ExplosiveElementManager;
/*   23:     */import org.schema.game.common.controller.elements.thrust.ThrusterElementManager;
/*   24:     */import org.schema.game.common.data.element.BeamHandler;
/*   25:     */import org.schema.game.common.data.element.BeamHandler.BeamState;
/*   26:     */import org.schema.game.common.data.element.ElementDocking;
/*   27:     */import org.schema.game.common.data.element.ElementInformation;
/*   28:     */import org.schema.game.common.data.element.ElementKeyMap;
/*   29:     */import org.schema.game.common.data.element.factory.FactoryElement;
/*   30:     */import org.schema.game.common.data.element.factory.ManufacturingElement;
/*   31:     */import org.schema.game.common.data.element.ship.ShipElement;
/*   32:     */import org.schema.game.common.data.element.spacestation.SpaceStationElement;
/*   33:     */import org.schema.game.common.data.physics.CubeRayCastResult;
/*   34:     */import org.schema.game.common.data.world.Segment;
/*   35:     */import org.schema.game.common.data.world.Universe;
/*   36:     */import org.schema.game.network.objects.NetworkSegmentController;
/*   37:     */import org.schema.game.network.objects.NetworkShip;
/*   38:     */import org.schema.game.network.objects.remote.RemoteSegmentPiece;
/*   39:     */import org.schema.game.network.objects.remote.RemoteSegmentPieceBuffer;
/*   40:     */import org.schema.game.server.controller.GameServerController;
/*   41:     */import org.schema.schine.graphicsengine.shader.ErrorDialogException;
/*   42:     */import org.schema.schine.network.NetworkStateContainer;
/*   43:     */import org.schema.schine.network.StateInterface;
/*   44:     */import org.schema.schine.network.objects.NetworkObject;
/*   45:     */import org.schema.schine.network.objects.Sendable;
/*   46:     */import org.schema.schine.network.objects.container.PhysicsDataContainer;
/*   47:     */import org.schema.schine.network.objects.remote.RemoteBoolean;
/*   48:     */import org.schema.schine.network.objects.remote.RemoteBuffer;
/*   49:     */import org.schema.schine.network.objects.remote.RemoteLongPrimitive;
/*   50:     */import org.schema.schine.network.objects.remote.RemoteString;
/*   51:     */
/*   67:     */public class kd
/*   68:     */  extends EditableSendableSegmentController
/*   69:     */  implements An, cw, jI, kh, ld, ParticleHandler, wp, zP
/*   70:     */{
/*   71:     */  public static final q a;
/*   72:     */  private final ArrayList jdField_a_of_type_JavaUtilArrayList;
/*   73:     */  
/*   74:     */  static
/*   75:     */  {
/*   76:  76 */    jdField_a_of_type_Q = new q(8, 8, 8);
/*   77:     */  }
/*   78:     */  
/*   84:  84 */  private String jdField_a_of_type_JavaLangString = "";
/*   85:     */  
/*   86:  86 */  private Set jdField_a_of_type_JavaUtilSet = new HashSet();
/*   87:     */  
/*   88:     */  private final kp jdField_a_of_type_Kp;
/*   89:     */  
/*   90:     */  private final ShipManagerContainer jdField_a_of_type_OrgSchemaGameCommonControllerElementsShipManagerContainer;
/*   91:     */  
/*   92:  92 */  private long jdField_a_of_type_Long = -1L;
/*   93:  93 */  private long jdField_b_of_type_Long = -1L;
/*   94:     */  
/*   98:  98 */  private Vector3f jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/*   99:  99 */  private Vector3f jdField_b_of_type_JavaxVecmathVector3f = new Vector3f();
/*  100:     */  
/*  104: 104 */  private q jdField_b_of_type_Q = new q();
/*  105: 105 */  private le jdField_a_of_type_Le = new le();
/*  106:     */  
/*  108: 108 */  private o jdField_a_of_type_O = new o();
/*  109:     */  
/*  114:     */  private int jdField_a_of_type_Int;
/*  115:     */  
/*  120:     */  private long c;
/*  121:     */  
/*  126:     */  private vc jdField_a_of_type_Vc;
/*  127:     */  
/*  132:     */  private String jdField_b_of_type_JavaLangString;
/*  133:     */  
/*  138:     */  public kd(StateInterface paramStateInterface)
/*  139:     */  {
/*  140: 140 */    super(paramStateInterface);
/*  141:     */    
/*  142: 142 */    this.jdField_a_of_type_JavaUtilArrayList = new ArrayList();
/*  143: 143 */    this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsShipManagerContainer = new ShipManagerContainer(this);
/*  144: 144 */    this.jdField_a_of_type_Kp = new kt(paramStateInterface, this);
/*  145:     */  }
/*  146:     */  
/*  155:     */  public boolean allowedType(short paramShort)
/*  156:     */  {
/*  157: 157 */    if ((!SpaceStationElement.class.isAssignableFrom(ElementKeyMap.getInfo(paramShort).getType()) ? 1 : 0) != 0) {}
/*  158: 158 */    if ((!FactoryElement.class.isAssignableFrom(ElementKeyMap.getInfo(paramShort).getType()) ? 1 : 0) != 0) {}
/*  159:     */    
/*  160: 160 */    if ((!ManufacturingElement.class.isAssignableFrom(ElementKeyMap.getInfo(paramShort).getType()) ? 1 : 0) == 0) {}
/*  161:     */    int i;
/*  162: 162 */    if (((i = ShipElement.class.isAssignableFrom(ElementKeyMap.getInfo(paramShort).getType()) ? 1 : 0) == 0) && (!isOnServer())) {
/*  163: 163 */      ((x)getState().getController()).b("Cannot place\n" + ElementKeyMap.getInfo(paramShort).getName() + "\non a ship");
/*  164:     */    }
/*  165:     */    
/*  168: 168 */    return (super.allowedType(paramShort)) && (i != 0);
/*  169:     */  }
/*  170:     */  
/*  174:     */  public void cleanUpOnEntityDelete()
/*  175:     */  {
/*  176: 176 */    super.cleanUpOnEntityDelete();
/*  177: 177 */    Iterator localIterator = null; if (!this.jdField_a_of_type_JavaUtilArrayList.isEmpty()) {
/*  178: 178 */      for (localIterator = this.jdField_a_of_type_JavaUtilArrayList.iterator(); localIterator.hasNext();) {
/*  179: 179 */        ((lE)localIterator.next()).a().b();
/*  180:     */      }
/*  181:     */    }
/*  182:     */  }
/*  183:     */  
/*  187:     */  public boolean isClientOwnObject()
/*  188:     */  {
/*  189: 189 */    return (!isOnServer()) && (this.jdField_a_of_type_JavaUtilArrayList.contains(((ct)getState()).a()));
/*  190:     */  }
/*  191:     */  
/*  198:     */  public final kp a()
/*  199:     */  {
/*  200: 200 */    return this.jdField_a_of_type_Kp;
/*  201:     */  }
/*  202:     */  
/*  212:     */  public final ArrayList a()
/*  213:     */  {
/*  214: 214 */    return this.jdField_a_of_type_JavaUtilArrayList;
/*  215:     */  }
/*  216:     */  
/*  219:     */  public final long a()
/*  220:     */  {
/*  221: 221 */    return this.jdField_b_of_type_Long;
/*  222:     */  }
/*  223:     */  
/*  229:     */  public final long b()
/*  230:     */  {
/*  231: 231 */    return this.jdField_a_of_type_Long;
/*  232:     */  }
/*  233:     */  
/*  234:     */  public final ShipManagerContainer a() {
/*  235: 235 */    return this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsShipManagerContainer;
/*  236:     */  }
/*  237:     */  
/*  239:     */  public final float a()
/*  240:     */  {
/*  241: 241 */    return this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsShipManagerContainer.getThrusterElementManager().getMaxVelocity();
/*  242:     */  }
/*  243:     */  
/*  244:     */  public int getNearestIntersection(short paramShort, Vector3f paramVector3f1, Vector3f paramVector3f2, ag paramag, int paramInt1, boolean paramBoolean, ju paramju, q paramq, int paramInt2, float paramFloat, az paramaz)
/*  245:     */  {
/*  246: 246 */    if ((paramShort == 121) && (this.jdField_a_of_type_Kp.a() != null)) {
/*  247: 247 */      if (!isOnServer()) {
/*  248: 248 */        ((ct)getState()).a().b("ERROR\nOnly one AI block is permitted\nper ship");
/*  249:     */      }
/*  250:     */      
/*  253: 253 */      return 0;
/*  254:     */    }
/*  255:     */    
/*  256: 256 */    if (paramShort == 1) {
/*  257: 257 */      if (!isOnServer()) {
/*  258: 258 */        ((ct)getState()).a().b("ERROR\nCore Elements cannot be placed\nthey are used to spawn new ships");
/*  259:     */      }
/*  260:     */      
/*  263: 263 */      return 0;
/*  264:     */    }
/*  265:     */    
/*  266: 266 */    return super.getNearestIntersection(paramShort, paramVector3f1, paramVector3f2, paramag, paramInt1, paramBoolean, paramju, paramq, paramInt2, paramFloat, paramaz);
/*  267:     */  }
/*  268:     */  
/*  280:     */  public final NetworkShip a()
/*  281:     */  {
/*  282: 282 */    return (NetworkShip)super.getNetworkObject();
/*  283:     */  }
/*  284:     */  
/*  285:     */  public L getParticleController()
/*  286:     */  {
/*  287: 287 */    if (!isOnServer()) {
/*  288: 288 */      return ((ct)getState()).getParticleController();
/*  289:     */    }
/*  290: 290 */    return ((vg)getState()).a().getSector(getSectorId()).a();
/*  291:     */  }
/*  292:     */  
/*  294:     */  public final SegmentController a()
/*  295:     */  {
/*  296: 296 */    return this;
/*  297:     */  }
/*  298:     */  
/*  304:     */  public final Set a()
/*  305:     */  {
/*  306: 306 */    return this.jdField_a_of_type_JavaUtilSet;
/*  307:     */  }
/*  308:     */  
/*  314:     */  public final Vector3f a()
/*  315:     */  {
/*  316: 316 */    return this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsShipManagerContainer.getThrusterElementManager().getVelocity();
/*  317:     */  }
/*  318:     */  
/*  322:     */  public final void a(xq paramxq, lA paramlA)
/*  323:     */  {
/*  324: 324 */    paramlA.jdField_a_of_type_LE.a().addAll(this.jdField_a_of_type_JavaUtilSet);
/*  325:     */    
/*  326: 326 */    if ((paramlA.jdField_a_of_type_JavaLangObject instanceof q))
/*  327:     */    {
/*  329: 329 */      if (getPhysicsDataContainer().isInitialized())
/*  330:     */      {
/*  331: 331 */        this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsShipManagerContainer.handle(paramlA);
/*  332:     */      }
/*  333:     */    }
/*  334:     */  }
/*  335:     */  
/*  339:     */  public void handleHit(CollisionWorld.ClosestRayResultCallback paramClosestRayResultCallback, lb paramlb, float paramFloat)
/*  340:     */  {
/*  341: 341 */    this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsShipManagerContainer.onHitNotice();
/*  342:     */    
/*  343: 343 */    super.handleHit(paramClosestRayResultCallback, paramlb, paramFloat);
/*  344:     */    
/*  345: 345 */    for (paramClosestRayResultCallback = 0; paramClosestRayResultCallback < this.jdField_a_of_type_JavaUtilArrayList.size(); paramClosestRayResultCallback++) {
/*  346: 346 */      ((lE)this.jdField_a_of_type_JavaUtilArrayList.get(paramClosestRayResultCallback)).a(this);
/*  347:     */    }
/*  348:     */  }
/*  349:     */  
/*  357:     */  protected void onCoreHitAlreadyDestroyed(float paramFloat)
/*  358:     */  {
/*  359: 359 */    super.onCoreHitAlreadyDestroyed(paramFloat);
/*  360: 360 */    if ((isOnServer()) && ((this instanceof kd)) && (this.jdField_b_of_type_Long > 0L)) {
/*  361: 361 */      this.jdField_b_of_type_Long = (Math.max(60000.0F, (float)this.jdField_b_of_type_Long - paramFloat * 10.0F));
/*  362:     */    }
/*  363:     */  }
/*  364:     */  
/*  367:     */  public final int a(BeamHandler.BeamState paramBeamState, jq paramjq, CubeRayCastResult paramCubeRayCastResult, Collection paramCollection)
/*  368:     */  {
/*  369: 369 */    le localle = new le(paramCubeRayCastResult.getSegment(), paramCubeRayCastResult.cubePos);
/*  370:     */    
/*  377: 377 */    if (((paramBeamState = paramjq.getHandler().beamHit(paramBeamState, localle)) > 0) && (isOnServer())) {
/*  378: 378 */      paramjq = localle.a();
/*  379:     */      
/*  380: 380 */      if (localle.a().a(localle.a(this.jdField_a_of_type_O), false)) {
/*  381: 381 */        paramCollection.add(paramCubeRayCastResult.getSegment());
/*  382: 382 */        ((mw)localle.a()).a(System.currentTimeMillis());
/*  383: 383 */        localle.a();
/*  384: 384 */        if ((!jdField_a_of_type_Boolean) && (localle.a() != 0)) throw new AssertionError();
/*  385: 385 */        localle.a(getState().getId());
/*  386:     */        
/*  387: 387 */        ((NetworkSegmentController)localle.a().a().getNetworkObject()).modificationBuffer.add(new RemoteSegmentPiece(localle, this, (NetworkShip)super.getNetworkObject()));
/*  388:     */        
/*  390: 390 */        if (this.jdField_a_of_type_JavaUtilArrayList.size() > 0)
/*  391:     */        {
/*  393: 393 */          ((lE)this.jdField_a_of_type_JavaUtilArrayList.get(0)).a().a(paramjq);
/*  394:     */        }
/*  395:     */      }
/*  396:     */    }
/*  397:     */    
/*  400: 400 */    return paramBeamState;
/*  401:     */  }
/*  402:     */  
/*  408:     */  public final boolean a()
/*  409:     */  {
/*  410: 410 */    return ((Boolean)((NetworkShip)super.getNetworkObject()).cloaked.get()).booleanValue();
/*  411:     */  }
/*  412:     */  
/*  413:     */  public final boolean b()
/*  414:     */  {
/*  415: 415 */    return this.jdField_a_of_type_Long > 0L;
/*  416:     */  }
/*  417:     */  
/*  418: 418 */  public final boolean c() { return ((Boolean)((NetworkShip)super.getNetworkObject()).jamming.get()).booleanValue(); }
/*  419:     */  
/*  424:     */  public final boolean a(String[] paramArrayOfString, q paramq)
/*  425:     */  {
/*  426: 426 */    if (this.jdField_a_of_type_Long < 0L) {
/*  427: 427 */      paramArrayOfString[0] = "You can only salvage overheating ships.";
/*  428: 428 */      return false;
/*  429:     */    }
/*  430:     */    
/*  431: 431 */    if (jdField_a_of_type_Q.equals(paramq)) {
/*  432: 432 */      paramArrayOfString[0] = "Can't salvage core! Please Pick up manually.";
/*  433: 433 */      return false;
/*  434:     */    }
/*  435:     */    
/*  436: 436 */    if ((isHomeBase()) || ((getDockingController().b()) && (getDockingController().a().to.a().a().isHomeBaseFor(getFactionId()))))
/*  437:     */    {
/*  438: 438 */      paramArrayOfString[0] = "Cannot salvage: home base protected";
/*  439: 439 */      return false;
/*  440:     */    }
/*  441: 441 */    if (this.jdField_a_of_type_Kp.a()) {
/*  442: 442 */      paramArrayOfString[0] = "Can only salvage defeated AI ships!";
/*  443: 443 */      return false;
/*  444:     */    }
/*  445: 445 */    if (this.jdField_a_of_type_JavaUtilArrayList.isEmpty()) {
/*  446: 446 */      return true;
/*  447:     */    }
/*  448: 448 */    paramArrayOfString[0] = "Can only salvage empty ships!";
/*  449: 449 */    return false;
/*  450:     */  }
/*  451:     */  
/*  454:     */  public void newNetworkObject()
/*  455:     */  {
/*  456: 456 */    setNetworkObject(new NetworkShip(getState(), this));
/*  457:     */  }
/*  458:     */  
/*  462:     */  public void onAddedElement(short paramShort, byte paramByte1, byte paramByte2, byte paramByte3, int paramInt, Segment paramSegment, boolean paramBoolean)
/*  463:     */  {
/*  464: 464 */    switch (paramShort)
/*  465:     */    {
/*  466:     */    case 1: 
/*  467: 467 */      isOnServer();
/*  468:     */    }
/*  469:     */    
/*  470:     */    
/*  474: 474 */    this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsShipManagerContainer.onAddedElement(paramShort, paramInt, paramByte1, paramByte2, paramByte3, paramSegment);
/*  475: 475 */    super.onAddedElement(paramShort, paramByte1, paramByte2, paramByte3, paramInt, paramSegment, paramBoolean);
/*  476:     */    
/*  477: 477 */    if ((this.jdField_a_of_type_Vc != null) && (isOnServer()) && (getLastModifier().length() == 0) && 
/*  478: 478 */      (this.jdField_a_of_type_Vc.a().b() < getElementClassCountMap().b())) {
/*  479: 479 */      System.err.println("[SERVER] WARNING: Possible modified Blueprint: " + this.jdField_a_of_type_Vc.a() + " spawned by " + getSpawner());
/*  480:     */      
/*  481: 481 */      paramShort = "MODIFIED BLUEPRINT WARNING\n" + this.jdField_b_of_type_JavaLangString + " spawned blueprint\n" + this.jdField_a_of_type_Vc.a() + "\nBB-Price: " + this.jdField_a_of_type_Vc.a().b() + "\nPrice of actual spawned: " + getElementClassCountMap().b();
/*  482:     */      
/*  487: 487 */      System.err.println(paramShort);
/*  488: 488 */      ((vg)getState()).a().broadcastMessage(paramShort, 3);
/*  489: 489 */      ((vg)getState()).a(this.jdField_a_of_type_Vc, this.jdField_b_of_type_JavaLangString);
/*  490:     */    }
/*  491:     */  }
/*  492:     */  
/*  498:     */  public final void a(lE paramlE, Sendable paramSendable, q paramq)
/*  499:     */  {
/*  500: 500 */    a();
/*  501:     */    
/*  502: 502 */    if (((paramSendable instanceof lD)) && 
/*  503: 503 */      (((lD)paramSendable).getGravity().a != null) && (((lD)paramSendable).getGravity().a != this)) {
/*  504: 504 */      System.err.println("[SHIP] removing gravity due to entering a ship != current gravity entity " + paramSendable + " -> " + this + "; current: " + ((lD)paramSendable).getGravity().a);
/*  505: 505 */      ((lD)paramSendable).removeGravity();
/*  506:     */    }
/*  507:     */    
/*  508: 508 */    if (isOnServer())
/*  509:     */    {
/*  514: 514 */      if (paramlE.c() != getSectorId()) {
/*  515: 515 */        System.err.println("[SERVER][ONATTACHPLAYER] entering! " + this + " in a different sector");
/*  516:     */        
/*  518: 518 */        if (isOnServer()) {
/*  519: 519 */          paramSendable = ((vg)getState()).a().getSector(getSectorId()).jdField_a_of_type_Q;
/*  520:     */        } else {
/*  521: 521 */          paramSendable = ((mv)getState().getLocalAndRemoteObjectContainer().getLocalObjects().get(getSectorId())).a();
/*  522:     */        }
/*  523:     */        
/*  524: 524 */        paramlE.a(new q(paramSendable));
/*  525: 525 */        paramlE.c(getSectorId());
/*  526:     */        
/*  529: 529 */        if ((paramq = paramlE.a()) != null) {
/*  530: 530 */          System.err.println("[SERVER][ONATTACHPLAYER] entering! Moving along playercharacter " + this + " in a different sector");
/*  531: 531 */          paramq.setSectorId(getSectorId());
/*  532:     */        } else {
/*  533: 533 */          System.err.println("[SERVER] WARNING NO PLAYER CHARACTER ATTACHED TO " + paramlE);
/*  534:     */        }
/*  535:     */      }
/*  536:     */    }
/*  537:     */    
/*  543: 543 */    if (!isOnServer())
/*  544:     */    {
/*  549: 549 */      if ((paramSendable = (ct)getState()).a() == paramlE)
/*  550:     */      {
/*  551: 551 */        xe.a().a(this, "0022_ambience loop - interior cockpit (loop)", 1.0F, 1.0F);
/*  552:     */        
/*  553: 553 */        if (!paramSendable.a().a(this)) {
/*  554: 554 */          paramq = new cz(paramSendable.a(), getUniqueIdentifier());
/*  555: 555 */          paramSendable.a().a().add(paramq);
/*  556: 556 */          paramq.a(9, new q(8, 8, 8), true);
/*  557:     */        }
/*  558:     */        
/*  560: 560 */        paramSendable.a().a().a().a().a().c(true);
/*  561:     */      }
/*  562:     */    }
/*  563:     */  }
/*  564:     */  
/*  573:     */  public void onCollision(ManifoldPoint paramManifoldPoint, Sendable paramSendable)
/*  574:     */  {
/*  575: 575 */    super.onCollision(paramManifoldPoint, paramSendable);
/*  576:     */    
/*  577: 577 */    if ((isOnServer()) && ((paramSendable instanceof EditableSendableSegmentController)) && (!this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsShipManagerContainer.getExplosiveCollectionManager().getCollection().isEmpty())) {
/*  578: 578 */      paramManifoldPoint.getPositionWorldOnB(this.jdField_a_of_type_JavaxVecmathVector3f);
/*  579: 579 */      getWorldTransformInverse().transform(this.jdField_a_of_type_JavaxVecmathVector3f);
/*  580: 580 */      this.jdField_a_of_type_JavaxVecmathVector3f.add(this.jdField_b_of_type_JavaxVecmathVector3f);
/*  581:     */      try
/*  582:     */      {
/*  583: 583 */        for (int i = -1; i < 2; i++) {
/*  584: 584 */          for (int j = -1; j < 2; j++) {
/*  585: 585 */            for (int k = -1; k < 2; k++) {
/*  586: 586 */              this.jdField_b_of_type_Q.b(Math.round(this.jdField_a_of_type_JavaxVecmathVector3f.x) + 8, Math.round(this.jdField_a_of_type_JavaxVecmathVector3f.y) + 8, Math.round(this.jdField_a_of_type_JavaxVecmathVector3f.z) + 8);
/*  587: 587 */              this.jdField_b_of_type_Q.jdField_a_of_type_Int += i;
/*  588: 588 */              this.jdField_b_of_type_Q.b += j;
/*  589: 589 */              this.jdField_b_of_type_Q.c += k;
/*  590:     */              le localle;
/*  591: 591 */              if (((localle = getSegmentBuffer().a(this.jdField_b_of_type_Q, false, this.jdField_a_of_type_Le)) != null) && (localle.a() == 14)) {
/*  592: 592 */                this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsShipManagerContainer.getExplosiveElementManager().addExplosion(this.jdField_b_of_type_Q, paramManifoldPoint.getPositionWorldOnB(new Vector3f()), (EditableSendableSegmentController)paramSendable, localle);
/*  593:     */              }
/*  594:     */            }
/*  595:     */          }
/*  596:     */        }
/*  597:     */        return;
/*  598:     */      }
/*  599:     */      catch (IOException localIOException) {
/*  600: 600 */        
/*  601:     */        
/*  604: 604 */          localIOException.printStackTrace(); return;
/*  605:     */      } catch (InterruptedException localInterruptedException) {
/*  606: 602 */        
/*  607:     */        
/*  608: 604 */          localInterruptedException;
/*  609:     */      }
/*  610:     */    }
/*  611:     */  }
/*  612:     */  
/*  623:     */  protected void onCoreDestroyed(lb paramlb)
/*  624:     */  {
/*  625: 619 */    for (int i = 0; i < this.jdField_a_of_type_JavaUtilArrayList.size(); i++) {
/*  626: 620 */      ((lE)this.jdField_a_of_type_JavaUtilArrayList.get(i)).b(0.0F, paramlb);
/*  627:     */    }
/*  628: 622 */    this.jdField_a_of_type_Kp.a(paramlb);
/*  629:     */    
/*  630: 624 */    if ((isOnServer()) && 
/*  631: 625 */      (this.jdField_a_of_type_Long < 0L)) {
/*  632: 626 */      this.jdField_a_of_type_Long = System.currentTimeMillis();
/*  633: 627 */      this.jdField_b_of_type_Long = Math.min(900000, getTotalElements() * 1000);
/*  634:     */      
/*  635: 629 */      System.err.println("[SERVER] MAIN CORE STARTED DESTRUCTION: in " + this.jdField_b_of_type_Long / 1000L + " - started " + this.jdField_a_of_type_Long);
/*  636:     */    }
/*  637:     */  }
/*  638:     */  
/*  645:     */  protected void onDamageServer(int paramInt, lb paramlb)
/*  646:     */  {
/*  647: 641 */    this.jdField_a_of_type_Kp.b(paramlb);
/*  648:     */  }
/*  649:     */  
/*  651:     */  public final void a(lE paramlE, boolean paramBoolean)
/*  652:     */  {
/*  653: 647 */    if (!isOnServer())
/*  654:     */    {
/*  656: 650 */      if (((paramBoolean = (ct)getState()).a() == paramlE) && (((ct)getState()).a() == paramlE))
/*  657:     */      {
/*  659: 653 */        paramBoolean.a().a().a().a().a().c(false);
/*  660:     */        
/*  661: 655 */        xe.a().a(this, "0022_spaceship user - small engine thruster loop", 1.0F, 1.0F);
/*  662:     */      }
/*  663:     */    }
/*  664:     */    
/*  666: 660 */    a();
/*  667:     */  }
/*  668:     */  
/*  672:     */  public void onProximity(SegmentController paramSegmentController)
/*  673:     */  {
/*  674: 668 */    this.jdField_a_of_type_Kp.a(paramSegmentController);
/*  675:     */  }
/*  676:     */  
/*  682:     */  public void onRemovedElement(short paramShort, int paramInt, byte paramByte1, byte paramByte2, byte paramByte3, Segment paramSegment, boolean paramBoolean)
/*  683:     */  {
/*  684: 678 */    switch (paramShort)
/*  685:     */    {
/*  686:     */    case 1: 
/*  687: 681 */      if (!isOnServer()) {
/*  688: 682 */        xe.a().a(this);
/*  689:     */      }
/*  690:     */      
/*  691:     */      break;
/*  692:     */    }
/*  693:     */    
/*  694: 688 */    this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsShipManagerContainer.onRemovedElement(paramShort, paramInt, paramByte1, paramByte2, paramByte3, paramSegment, paramBoolean);
/*  695: 689 */    super.onRemovedElement(paramShort, paramInt, paramByte1, paramByte2, paramByte3, paramSegment, paramBoolean);
/*  696:     */  }
/*  697:     */  
/*  698:     */  private void a()
/*  699:     */  {
/*  700: 694 */    if (getRealName().equals("undef")) {
/*  701:     */      return;
/*  702:     */    }
/*  703:     */    StringBuffer localStringBuffer;
/*  704: 698 */    (localStringBuffer = new StringBuffer()).append(getRealName());
/*  705:     */    
/*  706: 700 */    if (!this.jdField_a_of_type_JavaUtilArrayList.isEmpty()) {
/*  707: 701 */      localStringBuffer.append(" <");
/*  708: 702 */      for (int i = 0; i < this.jdField_a_of_type_JavaUtilArrayList.size(); i++) {
/*  709:     */        try {
/*  710: 704 */          localStringBuffer.append(((lE)this.jdField_a_of_type_JavaUtilArrayList.get(i)).getName());
/*  711: 705 */          if (i < this.jdField_a_of_type_JavaUtilArrayList.size() - 1)
/*  712: 706 */            localStringBuffer.append(", ");
/*  713:     */        } catch (Exception localException) {
/*  714: 708 */          
/*  715:     */          
/*  716: 710 */            localException;
/*  717:     */        }
/*  718:     */      }
/*  719:     */      
/*  720: 712 */      localStringBuffer.append(">");
/*  721:     */    }
/*  722:     */    
/*  724: 716 */    if (getFactionId() != 0) {
/*  725: 717 */      localStringBuffer.append("[");
/*  726:     */      lP locallP;
/*  727: 719 */      if ((locallP = ((vf)getState()).a().a(getFactionId())) != null) {
/*  728: 720 */        localStringBuffer.append(locallP.a());
/*  729:     */      } else {
/*  730: 722 */        localStringBuffer.append("factionUnknown");
/*  731: 723 */        localStringBuffer.append(getFactionId());
/*  732:     */      }
/*  733: 725 */      localStringBuffer.append("]");
/*  734:     */    }
/*  735: 727 */    this.jdField_a_of_type_JavaLangString = localStringBuffer.toString();
/*  736:     */  }
/*  737:     */  
/*  749:     */  public void startCreatorThread()
/*  750:     */  {
/*  751: 743 */    if (getCreatorThread() == null) {
/*  752: 744 */      setCreatorThread(new kI(this));
/*  753:     */    }
/*  754:     */  }
/*  755:     */  
/*  759:     */  public String toNiceString()
/*  760:     */  {
/*  761: 753 */    if (((String)((NetworkShip)super.getNetworkObject()).debugState.get()).length() > 0) {
/*  762: 754 */      return this.jdField_a_of_type_JavaLangString + (String)((NetworkShip)super.getNetworkObject()).debugState.get() + "\n[CLIENTAI " + (this.jdField_a_of_type_Kp.a() ? "ACTIVE" : "INACTIVE") + "] " + this.jdField_a_of_type_Kp.a();
/*  763:     */    }
/*  764:     */    
/*  765: 757 */    getState().getLocalAndRemoteObjectContainer().getLocalObjects().get(getSectorId());
/*  766: 758 */    if (!isOnServer()) {
/*  767: 759 */      return this.jdField_a_of_type_JavaLangString;
/*  768:     */    }
/*  769: 761 */    return this.jdField_a_of_type_JavaLangString;
/*  770:     */  }
/*  771:     */  
/*  775:     */  public String toString()
/*  776:     */  {
/*  777: 769 */    return "Ship[" + getRealName() + "](" + getId() + ")";
/*  778:     */  }
/*  779:     */  
/*  783:     */  public void updateFromNetworkObject(NetworkObject paramNetworkObject)
/*  784:     */  {
/*  785: 777 */    super.updateFromNetworkObject(paramNetworkObject);
/*  786:     */    
/*  787: 779 */    this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsShipManagerContainer.updateFromNetworkObject(paramNetworkObject);
/*  788: 780 */    this.jdField_a_of_type_Kp.d();
/*  789: 781 */    if (!isOnServer())
/*  790:     */    {
/*  792: 784 */      this.jdField_a_of_type_Long = ((NetworkShip)paramNetworkObject).coreDestructionStarted.get().longValue();
/*  793: 785 */      this.jdField_b_of_type_Long = ((NetworkShip)paramNetworkObject).coreDestructionDuration.get().longValue();
/*  794:     */      
/*  797: 789 */      if (!((NetworkShip)paramNetworkObject).onHitNotices.getReceiveBuffer().isEmpty()) {
/*  798: 790 */        this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsShipManagerContainer.onHitNotice();
/*  799:     */      }
/*  800:     */    }
/*  801:     */  }
/*  802:     */  
/*  805:     */  public void updateLocal(xq paramxq)
/*  806:     */  {
/*  807: 799 */    if (!isOnServer()) {
/*  808: 800 */      if ((!this.jdField_a_of_type_JavaUtilArrayList.isEmpty()) && (((lE)this.jdField_a_of_type_JavaUtilArrayList.get(0)).a())) {
/*  809: 801 */        getRemoteTransformable().jdField_a_of_type_Boolean = false;
/*  810: 802 */        getRemoteTransformable().a(true);
/*  811:     */      } else {
/*  812: 804 */        getRemoteTransformable().jdField_a_of_type_Boolean = true;
/*  813: 805 */        getRemoteTransformable().a(false);
/*  814:     */      }
/*  815:     */    }
/*  816:     */    
/*  818: 810 */    super.updateLocal(paramxq);
/*  819:     */    
/*  826: 818 */    if ((!getDockingController().b()) && (this.flagUpdateMass)) {
/*  827: 819 */      if (isOnServer())
/*  828:     */      {
/*  830: 822 */        if (updateMass()) {
/*  831: 823 */          this.flagUpdateMass = false;
/*  832:     */        }
/*  833:     */      } else {
/*  834: 826 */        getPhysicsDataContainer().updateMass(getMass(), true);
/*  835: 827 */        this.flagUpdateMass = false;
/*  836:     */      }
/*  837:     */    }
/*  838:     */    
/*  839:     */    label209:
/*  840:     */    
/*  841:     */    Object localObject;
/*  842:     */    
/*  843: 835 */    if (this.jdField_a_of_type_Long > 0L) {
/*  844: 836 */      long l1 = System.currentTimeMillis() - this.jdField_a_of_type_Long;
/*  845: 837 */      long l3 = this.jdField_b_of_type_Long - l1;
/*  846:     */      
/*  847: 839 */      if ((l1 <= 1000L) || (this.jdField_a_of_type_JavaUtilArrayList.size() <= 0)) {
/*  848: 840 */        if (l1 <= this.jdField_b_of_type_Long) {
/*  849:     */          break label209;
/*  850:     */        }
/*  851:     */        
/*  852: 844 */        if (isOnServer()) {
/*  853: 845 */          destroy();
/*  854:     */        }
/*  855:     */      }
/*  856:     */      
/*  857: 849 */      this.jdField_a_of_type_Long = -1L;
/*  858: 850 */      this.jdField_b_of_type_Long = -1L;
/*  859:     */      
/*  860: 852 */      if (!isOnServer())
/*  861:     */      {
/*  862: 854 */        float f1 = 3.0E-005F;
/*  863:     */        
/*  864: 856 */        if (l3 < 4000L) {
/*  865: 857 */          f1 = 0.1F;
/*  866: 858 */        } else if (l3 < 30000L) {
/*  867: 859 */          f1 = 0.02F;
/*  868: 860 */        } else if (l3 < 120000L) {
/*  869: 861 */          f1 = 0.003F;
/*  870: 862 */        } else if (l3 < 240000L) {
/*  871: 863 */          f1 = 0.0005F;
/*  872:     */        }
/*  873:     */        
/*  875: 867 */        if (Math.random() < f1)
/*  876:     */        {
/*  877: 869 */          ct localct = (ct)getState();
/*  878:     */          
/*  879: 871 */          Vector3f localVector3f = new Vector3f();
/*  880: 872 */          getSegmentBuffer().a().a(localVector3f);
/*  881:     */          
/*  882: 874 */          float f2 = localVector3f.length() / 2.0F;
/*  883:     */          
/*  884: 876 */          localObject = new Vector3f((float)(Math.random() - 0.5D), (float)(Math.random() - 0.5D), (float)(Math.random() - 0.5D));
/*  885: 877 */          while (((Vector3f)localObject).lengthSquared() == 0.0F) {
/*  886: 878 */            ((Vector3f)localObject).set((float)(Math.random() - 0.5D), (float)(Math.random() - 0.5D), (float)(Math.random() - 0.5D));
/*  887:     */          }
/*  888: 880 */          ((Vector3f)localObject).normalize();
/*  889: 881 */          ((Vector3f)localObject).scale((float)(f2 * (1.0D + Math.random())));
/*  890: 882 */          localVector3f.set(getWorldTransform().origin);
/*  891: 883 */          localVector3f.add((Tuple3f)localObject);
/*  892:     */          
/*  894: 886 */          localct.a().a().a(localVector3f, (float)(2.0D + Math.random() * 40.0D));
/*  895:     */        }
/*  896:     */      }
/*  897:     */    }
/*  898:     */    
/*  901: 893 */    if (isOnServer()) {
/*  902:     */      try
/*  903:     */      {
/*  904: 896 */        if ((getTotalElements() <= 0) && (System.currentTimeMillis() - this.c > 2000L) && (System.currentTimeMillis() - getTimeCreated() > 5000L) && (isEmptyOnServer()))
/*  905:     */        {
/*  910: 902 */          if (((localObject = getSegmentBuffer().a(new q(jdField_a_of_type_Q), true)) != null) && (((le)localObject).a() != 1)) {
/*  911: 903 */            System.err.println("[SERVER][SHIP] Empty SHIP: deleting " + this);
/*  912: 904 */            markedForPermanentDelete(true);
/*  913: 905 */            setMarkedForDeleteVolatile(true);
/*  914:     */          }
/*  915: 907 */          this.c = System.currentTimeMillis();
/*  916:     */        }
/*  917: 909 */      } catch (IOException localIOException) { 
/*  918:     */        
/*  921: 913 */          localIOException;
/*  922:     */      } catch (InterruptedException localInterruptedException) {
/*  923: 911 */        
/*  924:     */        
/*  925: 913 */          localInterruptedException;
/*  926:     */      }
/*  927:     */    }
/*  928:     */    
/*  930: 916 */    long l2 = System.currentTimeMillis();
/*  931:     */    try
/*  932:     */    {
/*  933: 919 */      this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsShipManagerContainer.updateLocal(paramxq);
/*  934:     */    }
/*  935:     */    catch (Exception localException)
/*  936:     */    {
/*  937: 923 */      System.err.println("Exception catched: " + localException + " printing startrace and then rethrowing");
/*  938: 924 */      localException.printStackTrace();
/*  939: 925 */      throw new ErrorDialogException(localException.getClass().getSimpleName() + ": " + localException.getMessage());
/*  940:     */    }
/*  941:     */    long l4;
/*  942: 928 */    if ((l4 = System.currentTimeMillis() - l2) > 20L) {
/*  943: 929 */      System.err.println("[SHIP] " + getState() + " " + this + " manager udpate took " + l4);
/*  944:     */    }
/*  945:     */    
/*  947: 933 */    l2 = System.currentTimeMillis();
/*  948: 934 */    if ((this.jdField_a_of_type_JavaLangString.length() == 0) || (this.jdField_a_of_type_Int != getFactionId())) {
/*  949: 935 */      a();
/*  950: 936 */      this.jdField_a_of_type_Int = getFactionId();
/*  951:     */    }
/*  952:     */    long l5;
/*  953: 939 */    if ((l5 = System.currentTimeMillis() - l2) > 5L) {
/*  954: 940 */      System.err.println("[SHIP] " + getState() + " " + this + " name udpate took " + l5);
/*  955:     */    }
/*  956:     */    
/*  958: 944 */    l2 = System.currentTimeMillis();
/*  959: 945 */    this.jdField_a_of_type_Kp.a(paramxq);
/*  960:     */    long l6;
/*  961: 947 */    if ((l6 = System.currentTimeMillis() - l2) > 15L) {
/*  962: 948 */      System.err.println("[SHIP] " + getState() + " " + this + " AI udpate took " + l6);
/*  963:     */    }
/*  964:     */  }
/*  965:     */  
/*  969:     */  public boolean updateMass()
/*  970:     */  {
/*  971: 957 */    if (isOnServer()) {
/*  972: 958 */      float f = Math.max(0.1F, getTotalElements() * 0.1F);
/*  973: 959 */      setMass(f);
/*  974:     */      
/*  979: 965 */      return getPhysicsDataContainer().updateMass(f, false);
/*  980:     */    }
/*  981: 967 */    return true;
/*  982:     */  }
/*  983:     */  
/*  990:     */  public void updateToFullNetworkObject()
/*  991:     */  {
/*  992: 978 */    super.updateToFullNetworkObject();
/*  993: 979 */    if (isOnServer()) {
/*  994: 980 */      super.getNetworkObject();this.jdField_a_of_type_Kp.c();
/*  995:     */      
/*  996: 982 */      this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsShipManagerContainer.updateToFullNetworkObject((NetworkShip)super.getNetworkObject());
/*  997:     */    }
/*  998:     */  }
/*  999:     */  
/* 1002:     */  public void updateToNetworkObject()
/* 1003:     */  {
/* 1004: 990 */    super.updateToNetworkObject();
/* 1005: 991 */    if (isOnServer()) {
/* 1006: 992 */      ((NetworkShip)super.getNetworkObject()).jamming.set(Boolean.valueOf(this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsShipManagerContainer.isJamming()));
/* 1007: 993 */      ((NetworkShip)super.getNetworkObject()).cloaked.set(Boolean.valueOf(this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsShipManagerContainer.isCloaked()));
/* 1008: 994 */      ((NetworkShip)super.getNetworkObject()).coreDestructionStarted.set(this.jdField_a_of_type_Long);
/* 1009: 995 */      ((NetworkShip)super.getNetworkObject()).coreDestructionDuration.set(this.jdField_b_of_type_Long);
/* 1010: 996 */      this.jdField_a_of_type_Kp.a((NetworkShip)super.getNetworkObject());
/* 1011:     */    }
/* 1012:     */  }
/* 1013:     */  
/* 1020:     */  public void getRelationColor(lZ paramlZ, Vector4f paramVector4f, float paramFloat)
/* 1021:     */  {
/* 1022:1008 */    switch (ke.a[paramlZ.ordinal()]) {
/* 1023:     */    case 1: 
/* 1024:1010 */      paramVector4f.x = (paramFloat + 1.0F);
/* 1025:1011 */      paramVector4f.y = paramFloat;
/* 1026:1012 */      paramVector4f.z = paramFloat;
/* 1027:1013 */      return;
/* 1028:     */    
/* 1029:     */    case 2: 
/* 1030:1016 */      paramVector4f.x = paramFloat;
/* 1031:1017 */      paramVector4f.y = (paramFloat + 1.0F);
/* 1032:1018 */      paramVector4f.z = paramFloat;
/* 1033:1019 */      return;
/* 1034:     */    
/* 1035:     */    case 3: 
/* 1036:1022 */      if (this.jdField_a_of_type_JavaUtilArrayList.isEmpty()) {
/* 1037:1023 */        paramVector4f.x = (paramFloat + 0.5F);
/* 1038:1024 */        paramVector4f.y = (paramFloat + 0.7F);
/* 1039:1025 */        paramVector4f.z = (paramFloat + 0.9F);return;
/* 1040:     */      }
/* 1041:1027 */      paramVector4f.x = (paramFloat + 0.3F);
/* 1042:1028 */      paramVector4f.y = (paramFloat + 0.5F);
/* 1043:1029 */      paramVector4f.z = (paramFloat + 0.7F);
/* 1044:     */    }
/* 1045:     */    
/* 1046:     */  }
/* 1047:     */  
/* 1051:     */  protected String getSegmentControllerTypeString()
/* 1052:     */  {
/* 1053:1039 */    return "Ship";
/* 1054:     */  }
/* 1055:     */  
/* 1058:     */  public final String a()
/* 1059:     */  {
/* 1060:1046 */    return "0022_ambience loop - interior cockpit (loop)";
/* 1061:     */  }
/* 1062:     */  
/* 1065:     */  public final String b()
/* 1066:     */  {
/* 1067:1053 */    return "0022_spaceship user - small engine thruster loop";
/* 1068:     */  }
/* 1069:     */  
/* 1086:     */  public final float b()
/* 1087:     */  {
/* 1088:1074 */    return 1.0F;
/* 1089:     */  }
/* 1090:     */  
/* 1099:     */  public final void a(vc paramvc, String paramString)
/* 1100:     */  {
/* 1101:1087 */    this.jdField_a_of_type_Vc = paramvc;
/* 1102:1088 */    this.jdField_b_of_type_JavaLangString = paramString;
/* 1103:     */  }
/* 1104:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     kd
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */