/*    1:     */package org.schema.game.common.controller;
/*    2:     */
/*    3:     */import C;
/*    4:     */import Q;
/*    5:     */import ag;
/*    6:     */import ah;
/*    7:     */import az;
/*    8:     */import com.bulletphysics.collision.broadphase.DispatcherInfo;
/*    9:     */import com.bulletphysics.collision.dispatch.CollisionObject;
/*   10:     */import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestConvexResultCallback;
/*   11:     */import com.bulletphysics.collision.shapes.BoxShape;
/*   12:     */import com.bulletphysics.collision.shapes.CollisionShape;
/*   13:     */import com.bulletphysics.collision.shapes.CompoundShape;
/*   14:     */import com.bulletphysics.collision.shapes.CompoundShapeChild;
/*   15:     */import com.bulletphysics.collision.shapes.ConvexShape;
/*   16:     */import com.bulletphysics.dynamics.DynamicsWorld;
/*   17:     */import com.bulletphysics.dynamics.constraintsolver.TypedConstraint;
/*   18:     */import com.bulletphysics.linearmath.AabbUtil2;
/*   19:     */import com.bulletphysics.util.ObjectArrayList;
/*   20:     */import ct;
/*   21:     */import dw;
/*   22:     */import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*   23:     */import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*   24:     */import jF;
/*   25:     */import jP;
/*   26:     */import jQ;
/*   27:     */import jR;
/*   28:     */import java.io.FilenameFilter;
/*   29:     */import java.sql.SQLException;
/*   30:     */import java.util.Random;
/*   31:     */import java.util.Set;
/*   32:     */import java.util.Vector;
/*   33:     */import ju;
/*   34:     */import jx;
/*   35:     */import kG;
/*   36:     */import mx;
/*   37:     */import org.schema.common.FastMath;
/*   38:     */import org.schema.game.common.controller.database.DatabaseIndex;
/*   39:     */import org.schema.game.common.controller.elements.ManagerContainer;
/*   40:     */import org.schema.game.common.data.element.ElementClassNotFoundException;
/*   41:     */import org.schema.game.common.data.element.ElementInformation;
/*   42:     */import org.schema.game.common.data.element.ElementKeyMap;
/*   43:     */import org.schema.game.common.data.physics.CubeShape;
/*   44:     */import org.schema.game.common.data.physics.CubesCompoundShape;
/*   45:     */import org.schema.game.common.data.physics.ModifiedDynamicsWorld;
/*   46:     */import org.schema.game.common.data.physics.PhysicsExt;
/*   47:     */import org.schema.game.common.data.world.Universe;
/*   48:     */import org.schema.game.server.controller.GameServerController;
/*   49:     */import org.schema.schine.graphicsengine.camera.Camera;
/*   50:     */import org.schema.schine.graphicsengine.shader.ErrorDialogException;
/*   51:     */import org.schema.schine.network.NetworkStateContainer;
/*   52:     */import org.schema.schine.network.StateInterface;
/*   53:     */import org.schema.schine.network.objects.Sendable;
/*   54:     */import org.schema.schine.network.server.ServerStateInterface;
/*   55:     */import sB;
/*   56:     */import tR;
/*   57:     */import vo;
/*   58:     */import x;
/*   59:     */import xR;
/*   60:     */import xq;
/*   61:     */import xu;
/*   62:     */import yk;
/*   63:     */import zQ;
/*   64:     */import zW;
/*   65:     */
/*   66:     */public abstract class SegmentController extends mF implements C, sB
/*   67:     */{
/*   68:     */  private final jv dockingController;
/*   69:     */  private jY segmentProvider;
/*   70:     */  private int creatorId;
/*   71:     */  private kG creatorThread;
/*   72:     */  
/*   73:     */  public static void setContraintFrameOrientation(byte paramByte, com.bulletphysics.linearmath.Transform paramTransform, javax.vecmath.Vector3f paramVector3f1, javax.vecmath.Vector3f paramVector3f2, javax.vecmath.Vector3f paramVector3f3)
/*   74:     */  {
/*   75:  75 */    switch (paramByte) {
/*   76:     */    case 5: 
/*   77:  77 */      paramTransform.basis.setRow(0, -paramVector3f1.x, -paramVector3f2.x, -paramVector3f3.x);
/*   78:  78 */      paramTransform.basis.setRow(1, -paramVector3f1.y, -paramVector3f2.y, -paramVector3f3.y);
/*   79:  79 */      paramTransform.basis.setRow(2, -paramVector3f1.z, -paramVector3f2.z, -paramVector3f3.z);
/*   80:  80 */      return;
/*   81:     */    case 4: 
/*   82:     */    default: 
/*   83:  83 */      paramTransform.basis.setRow(0, paramVector3f1.x, paramVector3f2.x, paramVector3f3.x);
/*   84:  84 */      paramTransform.basis.setRow(1, paramVector3f1.y, paramVector3f2.y, paramVector3f3.y);
/*   85:  85 */      paramTransform.basis.setRow(2, paramVector3f1.z, paramVector3f2.z, paramVector3f3.z);
/*   86:  86 */      return;
/*   87:     */    case 0: 
/*   88:  88 */      paramTransform.basis.setRow(0, -paramVector3f3.x, -paramVector3f2.x, -paramVector3f1.x);
/*   89:  89 */      paramTransform.basis.setRow(1, -paramVector3f3.y, -paramVector3f2.y, -paramVector3f1.y);
/*   90:  90 */      paramTransform.basis.setRow(2, -paramVector3f3.z, -paramVector3f2.z, -paramVector3f1.z);
/*   91:  91 */      return;
/*   92:     */    case 1: 
/*   93:  93 */      paramTransform.basis.setRow(0, paramVector3f3.x, -paramVector3f2.x, paramVector3f1.x);
/*   94:  94 */      paramTransform.basis.setRow(1, paramVector3f3.y, -paramVector3f2.y, paramVector3f1.y);
/*   95:  95 */      paramTransform.basis.setRow(2, paramVector3f3.z, -paramVector3f2.z, paramVector3f1.z);
/*   96:  96 */      return;
/*   97:     */    case 2: 
/*   98:  98 */      paramTransform.basis.setRow(0, paramVector3f3.x, paramVector3f1.x, paramVector3f2.x);
/*   99:  99 */      paramTransform.basis.setRow(1, paramVector3f3.y, paramVector3f1.y, paramVector3f2.y);
/*  100: 100 */      paramTransform.basis.setRow(2, paramVector3f3.z, paramVector3f1.z, paramVector3f2.z);
/*  101: 101 */      return;
/*  102:     */    }
/*  103: 103 */    paramTransform.basis.setRow(0, -paramVector3f3.x, paramVector3f1.x, -paramVector3f2.x);
/*  104: 104 */    paramTransform.basis.setRow(1, -paramVector3f3.y, paramVector3f1.y, -paramVector3f2.y);
/*  105: 105 */    paramTransform.basis.setRow(2, -paramVector3f3.z, paramVector3f1.z, -paramVector3f2.z);
/*  106:     */  }
/*  107:     */  
/*  115: 115 */  private int id = -1234;
/*  116: 116 */  private final q maxPos = new q();
/*  117: 117 */  private final q minPos = new q();
/*  118: 118 */  private String realName = "undef";
/*  119: 119 */  private final com.bulletphysics.linearmath.Transform worldTransformInverse = new com.bulletphysics.linearmath.Transform();
/*  120: 120 */  private final jx elementClassCountMap = new jx();
/*  121: 121 */  private int totalElements = 0;
/*  122:     */  private final jR collisionChecker;
/*  123: 123 */  private final q testPos = new q();
/*  124:     */  private final jL segmentBuffer;
/*  125: 125 */  private final javax.vecmath.Vector3f camPosLocal = new javax.vecmath.Vector3f((1.0F / 1.0F), (1.0F / 1.0F), (1.0F / 1.0F));
/*  126: 126 */  private final javax.vecmath.Vector3f camForwLocal = new javax.vecmath.Vector3f();
/*  127:     */  
/*  128: 128 */  private final javax.vecmath.Vector3f camLeftLocal = new javax.vecmath.Vector3f();
/*  129:     */  
/*  130: 130 */  private final javax.vecmath.Vector3f camUpLocal = new javax.vecmath.Vector3f();
/*  131:     */  
/*  132:     */  private final org.schema.game.common.data.element.ControlElementMap controlElementMap;
/*  133:     */  
/*  134:     */  private String uniqueIdentifier;
/*  135:     */  
/*  136: 136 */  protected final o tmpLocalPos = new o();
/*  137: 137 */  protected final q posTmp = new q();
/*  138:     */  
/*  139:     */  private long timeCreated;
/*  140:     */  
/*  141:     */  private boolean aabbRecalcFlag;
/*  142:     */  
/*  143:     */  protected boolean flagUpdateMass;
/*  144:     */  private boolean flagCheckDocking;
/*  145:     */  public boolean flagPhysicsAABBUpdate;
/*  146:     */  private boolean flagSegmentBufferAABBUpdate;
/*  147: 147 */  private String spawner = "";
/*  148: 148 */  private String lastModifier = "";
/*  149:     */  private long seed;
/*  150: 150 */  private final java.util.ArrayList needsActiveUpdateClient = new java.util.ArrayList();
/*  151:     */  private long delayDockingCheck;
/*  152:     */  private int lastSector;
/*  153: 153 */  private final com.bulletphysics.linearmath.Transform clientTransformInverse = new com.bulletphysics.linearmath.Transform();
/*  154: 154 */  private final javax.vecmath.Vector3f camLocalTmp = new javax.vecmath.Vector3f();
/*  155:     */  
/*  156:     */  public SegmentController(StateInterface paramStateInterface)
/*  157:     */  {
/*  158: 158 */    super(paramStateInterface);
/*  159: 159 */    this.collisionChecker = new jR(this);
/*  160: 160 */    if ((paramStateInterface instanceof ServerStateInterface)) {
/*  161: 161 */      this.segmentProvider = new tR(this);
/*  162:     */    } else {
/*  163: 163 */      this.segmentProvider = new Q(this);
/*  164:     */    }
/*  165:     */    
/*  166: 166 */    this.segmentBuffer = new SegmentBufferManager(this);
/*  167:     */    
/*  169: 169 */    this.controlElementMap = new org.schema.game.common.data.element.ControlElementMap();
/*  170:     */    
/*  177: 177 */    setTimeCreated(System.currentTimeMillis());
/*  178: 178 */    this.dockingController = new jv(this);
/*  179:     */  }
/*  180:     */  
/*  181:     */  public void aabbRecalcFlag()
/*  182:     */  {
/*  183: 183 */    this.aabbRecalcFlag = true;
/*  184:     */  }
/*  185:     */  
/*  186:     */  public void cleanUpOnEntityDelete()
/*  187:     */  {
/*  188: 188 */    super.cleanUpOnEntityDelete();
/*  189:     */    try
/*  190:     */    {
/*  191: 191 */      getSegmentProvider().f();
/*  192: 192 */    } catch (java.io.IOException localIOException) { 
/*  193:     */      
/*  194: 194 */        localIOException;
/*  195:     */    }
/*  196:     */    
/*  197: 195 */    getCreatorThread().a = false;
/*  198:     */  }
/*  199:     */  
/*  308:     */  public void createConstraint(zP paramzP1, zP paramzP2, Object paramObject) {}
/*  309:     */  
/*  418:     */  public void decTotalElements()
/*  419:     */  {
/*  420: 418 */    this.totalElements -= 1;
/*  421: 419 */    flagupdateMass();
/*  422: 420 */    flagUpdateDocking();
/*  423:     */  }
/*  424:     */  
/*  425: 423 */  public boolean existsNeighborSegment(q paramq, int paramInt) { getNeighborSegmentPos(paramq, paramInt, this.testPos);
/*  426: 424 */    return this.segmentBuffer.a(this.testPos);
/*  427:     */  }
/*  428:     */  
/*  429: 427 */  public void flagUpdateDocking() { this.flagCheckDocking = true; }
/*  430:     */  
/*  434:     */  public void flagupdateMass()
/*  435:     */  {
/*  436: 434 */    if (vg.jdField_f_of_type_Int == getId()) {
/*  437:     */      try {
/*  438: 436 */        throw new IllegalArgumentException("MASS SET DEBUG");
/*  439: 437 */      } catch (Exception localException) { localException;
/*  440:     */      }
/*  441:     */    }
/*  442:     */    
/*  443: 441 */    this.flagUpdateMass = true;
/*  444:     */  }
/*  445:     */  
/*  452:     */  public void setSpawner(String paramString)
/*  453:     */  {
/*  454: 452 */    this.spawner = paramString;
/*  455:     */  }
/*  456:     */  
/*  457: 455 */  public void setLastModified(String paramString) { this.lastModifier = paramString; }
/*  458:     */  
/*  466:     */  public void fromTagStructure(Ah paramAh)
/*  467:     */  {
/*  468: 466 */    assert (paramAh.a().equals("sc"));
/*  469:     */    
/*  470: 468 */    paramAh = (Ah[])paramAh.a();
/*  471:     */    
/*  472: 470 */    setUniqueIdentifier((String)paramAh[0].a());
/*  473:     */    
/*  475: 473 */    getMinPos().b((q)paramAh[1].a());
/*  476: 474 */    getMaxPos().b((q)paramAh[2].a());
/*  477:     */    
/*  479: 477 */    getDockingController().a(paramAh[3]);
/*  480:     */    
/*  481: 479 */    getControlElementMap().fromTagStructure(paramAh[4]);
/*  482:     */    
/*  483: 481 */    setRealName((String)paramAh[5].a());
/*  484:     */    
/*  485: 483 */    super.fromTagStructure(paramAh[6]);
/*  486:     */    
/*  487: 485 */    if ((this instanceof ld)) {
/*  488: 486 */      ((ld)this).a().fromTagStructure(paramAh[7]);
/*  489:     */    }
/*  490:     */    
/*  491: 489 */    setCreatorId(((Integer)paramAh[8].a()).intValue());
/*  492: 490 */    if ((paramAh.length > 9) && (paramAh[9].a() == Aj.i)) {
/*  493: 491 */      this.spawner = ((String)paramAh[9].a());
/*  494:     */    }
/*  495:     */    
/*  496: 494 */    if ((paramAh.length > 10) && (paramAh[10].a() == Aj.i)) {
/*  497: 495 */      this.lastModifier = ((String)paramAh[10].a());
/*  498:     */    }
/*  499: 497 */    if ((paramAh.length > 11) && (paramAh[11].a() == Aj.e)) {
/*  500: 498 */      this.seed = ((Long)paramAh[11].a()).longValue();
/*  501:     */    } else {
/*  502: 500 */      this.seed = Universe.getRandom().nextLong();
/*  503:     */    }
/*  504: 502 */    if ((paramAh.length > 12) && (paramAh[12].a() == Aj.b)) {
/*  505: 503 */      if ((this instanceof km)) {
/*  506: 504 */        ((km)this).a(((Byte)paramAh[12].a()).byteValue() == 1);
/*  507:     */      }
/*  508:     */      
/*  510:     */    }
/*  511: 509 */    else if ((this instanceof km)) {
/*  512: 510 */      ((km)this).a(true);
/*  513:     */    }
/*  514:     */  }
/*  515:     */  
/*  520:     */  public Ah toTagStructure()
/*  521:     */  {
/*  522: 520 */    Ah localAh1 = new Ah(Aj.i, "uniqueId", getUniqueIdentifier());
/*  523: 521 */    Ah localAh2 = new Ah(Aj.d, "creatoreId", Integer.valueOf(getCreatorId()));
/*  524:     */    
/*  525: 523 */    Ah localAh3 = new Ah(Aj.k, "maxPos", getMinPos());
/*  526: 524 */    Ah localAh4 = new Ah(Aj.k, "minPos", getMaxPos());
/*  527: 525 */    Ah localAh5 = new Ah(Aj.i, "realname", getRealName());
/*  528:     */    
/*  529: 527 */    Ah localAh6 = this.dockingController.a();
/*  530:     */    
/*  531:     */    Ah localAh7;
/*  532: 530 */    if ((this instanceof ld))
/*  533:     */    {
/*  534: 532 */      localAh7 = ((ld)this).a().toTagStructure();
/*  535:     */    } else {
/*  536: 534 */      localAh7 = new Ah(Aj.b, "dummy", Byte.valueOf((byte)0));
/*  537:     */    }
/*  538:     */    
/*  539: 537 */    Ah localAh8 = getControlElementMap().toTagStructure();
/*  540:     */    
/*  541: 539 */    Ah localAh9 = new Ah(Aj.i, null, this.spawner != null ? this.spawner : "");
/*  542: 540 */    Ah localAh10 = new Ah(Aj.i, null, this.lastModifier != null ? this.lastModifier : "");
/*  543:     */    
/*  544: 542 */    Ah localAh11 = new Ah(Aj.e, null, Long.valueOf(getSeed()));
/*  545:     */    
/*  546:     */    Ah localAh12;
/*  547:     */    
/*  548: 546 */    if ((this instanceof km)) {
/*  549: 547 */      localAh12 = new Ah(Aj.b, null, Byte.valueOf((byte)(((km)this).a() ? 1 : 0)));
/*  550:     */    } else {
/*  551: 549 */      localAh12 = new Ah(Aj.b, null, Byte.valueOf((byte)1));
/*  552:     */    }
/*  553:     */    
/*  555: 553 */    return new Ah(Aj.n, "sc", new Ah[] { localAh1, localAh3, localAh4, localAh6, localAh8, localAh5, super.toTagStructure(), localAh7, localAh2, localAh9, localAh10, localAh11, localAh12, new Ah(Aj.a, null, null) });
/*  556:     */  }
/*  557:     */  
/*  562:     */  public javax.vecmath.Vector3f getAbsoluteElementWorldPosition(q paramq, javax.vecmath.Vector3f paramVector3f)
/*  563:     */  {
/*  564: 562 */    paramVector3f.set(paramq.a, paramq.b, paramq.c);
/*  565: 563 */    getWorldTransform().basis.transform(paramVector3f);
/*  566: 564 */    paramVector3f.add(getWorldTransform().origin);
/*  567: 565 */    return paramVector3f;
/*  568:     */  }
/*  569:     */  
/*  570:     */  public void getAbsoluteSegmentWorldPositionClient(org.schema.game.common.data.world.Segment paramSegment, javax.vecmath.Vector3f paramVector3f)
/*  571:     */  {
/*  572: 570 */    paramVector3f.set(paramSegment.a.a, paramSegment.a.b, paramSegment.a.c);
/*  573:     */    
/*  574: 572 */    (paramSegment = getWorldTransformClient()).basis.transform(paramVector3f);
/*  575: 573 */    paramVector3f.add(paramSegment.origin);
/*  576:     */  }
/*  577:     */  
/*  578: 576 */  public xO getBoundingBox() { return getSegmentBuffer().a(); }
/*  579:     */  
/*  580:     */  public javax.vecmath.Vector3f getCamForwLocal() {
/*  581: 579 */    return this.camForwLocal;
/*  582:     */  }
/*  583:     */  
/*  584: 582 */  public javax.vecmath.Vector3f getCamLeftLocal() { return this.camLeftLocal; }
/*  585:     */  
/*  586:     */  public javax.vecmath.Vector3f getCamUpLocal() {
/*  587: 585 */    return this.camUpLocal;
/*  588:     */  }
/*  589:     */  
/*  592:     */  public jR getCollisionChecker()
/*  593:     */  {
/*  594: 592 */    return this.collisionChecker;
/*  595:     */  }
/*  596:     */  
/*  600:     */  public org.schema.game.common.data.element.ControlElementMap getControlElementMap()
/*  601:     */  {
/*  602: 600 */    return this.controlElementMap;
/*  603:     */  }
/*  604:     */  
/*  605: 603 */  public int getCreatorId() { return this.creatorId; }
/*  606:     */  
/*  607:     */  public void setCreatorId(int paramInt) {
/*  608: 606 */    this.creatorId = paramInt;
/*  609:     */  }
/*  610:     */  
/*  613:     */  public kG getCreatorThread()
/*  614:     */  {
/*  615: 613 */    return this.creatorThread;
/*  616:     */  }
/*  617:     */  
/*  620:     */  public jv getDockingController()
/*  621:     */  {
/*  622: 620 */    return this.dockingController;
/*  623:     */  }
/*  624:     */  
/*  627:     */  public jx getElementClassCountMap()
/*  628:     */  {
/*  629: 627 */    return this.elementClassCountMap;
/*  630:     */  }
/*  631:     */  
/*  652:     */  public final int getId()
/*  653:     */  {
/*  654: 652 */    return this.id;
/*  655:     */  }
/*  656:     */  
/*  657:     */  public javax.vecmath.Vector3f getLocalCamPos() {
/*  658: 656 */    return this.camPosLocal;
/*  659:     */  }
/*  660:     */  
/*  664: 662 */  public float getMass() { return super.getMass(); }
/*  665:     */  
/*  666:     */  private float getMaxPhysicsSubsteps(xq paramxq) { Object localObject;
/*  667:     */    com.bulletphysics.linearmath.Transform localTransform;
/*  668:     */    float f1;
/*  669:     */    java.util.Iterator localIterator;
/*  670: 668 */    if ((vo.n.a()) && 
/*  671: 669 */      (getMass() > 0.0F) && ((getPhysicsDataContainer().getObject() instanceof com.bulletphysics.dynamics.RigidBody)))
/*  672:     */    {
/*  673:     */      javax.vecmath.Vector3f localVector3f;
/*  674:     */      float f2;
/*  675: 673 */      if ((f2 = (localVector3f = ((com.bulletphysics.dynamics.RigidBody)getPhysicsDataContainer().getObject()).getLinearVelocity(new javax.vecmath.Vector3f())).length()) > 47.0F) {
/*  676: 674 */        System.err.println("Tuned on Tunneling prevention for " + this + " at speed " + f2 + " on " + getState());
/*  677: 675 */        (
/*  678: 676 */          localObject = new javax.vecmath.Vector3f()).sub(getSegmentBuffer().a().b, getSegmentBuffer().a().a);
/*  679: 677 */        ((javax.vecmath.Vector3f)localObject).scale(0.5F);
/*  680:     */        
/*  681: 679 */        localVector3f.scale(paramxq.a());
/*  682: 680 */        paramxq = new com.bulletphysics.linearmath.Transform(getWorldTransform());
/*  683: 681 */        localTransform = new com.bulletphysics.linearmath.Transform(getWorldTransform());
/*  684: 682 */        paramxq.basis.transform(localVector3f);
/*  685: 683 */        localTransform.origin.add(localVector3f);
/*  686:     */        
/*  689: 687 */        f1 = getPhysics().getDynamicsWorld().getDispatchInfo().allowedCcdPenetration;
/*  690: 688 */        localObject = new BoxShape((javax.vecmath.Vector3f)localObject);
/*  691: 689 */        for (localIterator = getState().getLocalAndRemoteObjectContainer().getLocalObjects().values().iterator(); localIterator.hasNext();) { Sendable localSendable;
/*  692: 690 */          if (((localSendable = (Sendable)localIterator.next()) != this) && ((localSendable instanceof SegmentController)) && (((SegmentController)localSendable).getSectorId() != getSectorId())) {
/*  693: 691 */            CollisionWorld.ClosestConvexResultCallback localClosestConvexResultCallback = new CollisionWorld.ClosestConvexResultCallback(paramxq.origin, localTransform.origin);
/*  694: 692 */            SegmentController localSegmentController = (SegmentController)localSendable;
/*  695:     */            
/*  696: 694 */            ModifiedDynamicsWorld.objectQuerySingle((ConvexShape)localObject, paramxq, localTransform, localSegmentController.getPhysicsDataContainer().getObject(), localSegmentController.getPhysicsDataContainer().getObject().getCollisionShape(), localSegmentController.getWorldTransform(), localClosestConvexResultCallback, f1);
/*  697:     */            
/*  703: 701 */            if (localClosestConvexResultCallback.hasHit())
/*  704:     */            {
/*  707: 705 */              (paramxq = new javax.vecmath.Vector3f(getWorldTransform().origin)).sub(localClosestConvexResultCallback.hitPointWorld);
/*  708: 706 */              System.err.println("[TunnelPrevention] HIT AT " + localClosestConvexResultCallback.hitPointWorld + " " + localSendable + " on " + getState() + " DIST: " + paramxq.length());
/*  709: 707 */              return paramxq.length();
/*  710:     */            }
/*  711:     */          }
/*  712:     */        }
/*  713:     */      }
/*  714:     */    }
/*  715:     */    
/*  716: 714 */    return 0.0F;
/*  717:     */  }
/*  718:     */  
/*  719:     */  public q getMaxPos()
/*  720:     */  {
/*  721: 719 */    return this.maxPos;
/*  722:     */  }
/*  723:     */  
/*  746:     */  public q getMinPos()
/*  747:     */  {
/*  748: 746 */    return this.minPos;
/*  749:     */  }
/*  750:     */  
/*  753:     */  public abstract void getNearestIntersectingElementPosition(javax.vecmath.Vector3f paramVector3f1, javax.vecmath.Vector3f paramVector3f2, q paramq, float paramFloat, ah paramah, az paramaz);
/*  754:     */  
/*  757:     */  public abstract int getNearestIntersection(short paramShort, javax.vecmath.Vector3f paramVector3f1, javax.vecmath.Vector3f paramVector3f2, ag paramag, int paramInt1, boolean paramBoolean, ju paramju, q paramq, int paramInt2, float paramFloat, az paramaz);
/*  758:     */  
/*  760:     */  public le[] getNeighborElements(q paramq, short paramShort, le[] paramArrayOfle)
/*  761:     */  {
/*  762: 760 */    assert (paramArrayOfle.length == 6);
/*  763:     */    
/*  764: 762 */    for (int i = 0; i < 6; i++) {
/*  765: 763 */      this.posTmp.b(paramq);
/*  766: 764 */      this.posTmp.a(org.schema.game.common.data.element.Element.DIRECTIONSi[i]);
/*  767:     */      
/*  769:     */      le localle;
/*  770:     */      
/*  771: 769 */      if (((localle = getSegmentBuffer().a(this.posTmp, true)) != null) && ((paramShort == 32767) || (paramShort == localle.a()))) {
/*  772: 770 */        paramArrayOfle[i] = localle;
/*  773:     */      } else {
/*  774: 772 */        paramArrayOfle[i] = null;
/*  775:     */      }
/*  776:     */    }
/*  777: 775 */    return paramArrayOfle;
/*  778:     */  }
/*  779:     */  
/*  782:     */  public le[] getNeighborElements(q paramq, short paramShort, le[] paramArrayOfle, Set paramSet)
/*  783:     */  {
/*  784: 782 */    assert (paramArrayOfle.length == 6);
/*  785: 783 */    for (int i = 0; i < 6; i++) {
/*  786: 784 */      this.posTmp.b(paramq);
/*  787: 785 */      this.posTmp.a(org.schema.game.common.data.element.Element.DIRECTIONSi[i]);
/*  788: 786 */      int j = 0;
/*  789: 787 */      if (paramSet.contains(this.posTmp))
/*  790:     */      {
/*  791:     */        le localle;
/*  792: 790 */        if (((localle = getSegmentBuffer().a(this.posTmp, true, paramArrayOfle[i])) != null) && ((paramShort == 32767) || (paramShort == localle.a()))) {
/*  793: 791 */          assert (localle.a() != null);
/*  794: 792 */          j = 1;
/*  795:     */        }
/*  796:     */      }
/*  797: 795 */      paramArrayOfle[i].a(j);
/*  798:     */    }
/*  799: 797 */    for (i = 0; i < 6; i++) {
/*  800: 798 */      if ((paramArrayOfle[i].b() == 1) && 
/*  801: 799 */        (!$assertionsDisabled) && (paramArrayOfle[i].a() == null)) { throw new AssertionError();
/*  802:     */      }
/*  803:     */    }
/*  804: 802 */    return paramArrayOfle;
/*  805:     */  }
/*  806:     */  
/*  815:     */  public org.schema.game.common.data.world.Segment getNeighboringSegment(o paramo, org.schema.game.common.data.world.Segment paramSegment, q paramq)
/*  816:     */  {
/*  817: 815 */    assert (paramSegment != null) : (this + ", " + getState() + " has null seg");
/*  818:     */    
/*  819: 817 */    if (org.schema.game.common.data.world.SegmentData.valid(paramo.a, paramo.b, paramo.c))
/*  820:     */    {
/*  822: 820 */      return paramSegment;
/*  823:     */    }
/*  824:     */    
/*  825: 823 */    paramq.b(paramSegment.a);
/*  826: 824 */    paramSegment = org.schema.common.util.ByteUtil.b(paramo.a);
/*  827: 825 */    int i = org.schema.common.util.ByteUtil.b(paramo.b);
/*  828: 826 */    int j = org.schema.common.util.ByteUtil.b(paramo.c);
/*  829:     */    
/*  830: 828 */    paramq.a(paramSegment << 4, i << 4, j << 4);
/*  831:     */    
/*  832: 830 */    paramo.a = ((byte)org.schema.common.util.ByteUtil.d(paramo.a));
/*  833: 831 */    paramo.b = ((byte)org.schema.common.util.ByteUtil.d(paramo.b));
/*  834: 832 */    paramo.c = ((byte)org.schema.common.util.ByteUtil.d(paramo.c));
/*  835:     */    
/*  837: 835 */    return this.segmentBuffer.a(paramq);
/*  838:     */  }
/*  839:     */  
/*  848:     */  public org.schema.game.common.data.world.Segment getNeighboringSegmentFast(org.schema.game.common.data.world.Segment paramSegment, byte paramByte1, byte paramByte2, byte paramByte3)
/*  849:     */  {
/*  850: 848 */    assert (paramSegment != null) : (this + ", " + getState() + " has null seg");
/*  851:     */    
/*  852: 850 */    if (org.schema.game.common.data.world.SegmentData.valid(paramByte1, paramByte2, paramByte3))
/*  853:     */    {
/*  855: 853 */      return paramSegment;
/*  856:     */    }
/*  857:     */    
/*  861: 859 */    paramByte1 = paramSegment.a.a + (org.schema.common.util.ByteUtil.b(paramByte1) << 4);
/*  862: 860 */    paramByte2 = paramSegment.a.b + (org.schema.common.util.ByteUtil.b(paramByte2) << 4);
/*  863: 861 */    paramSegment = paramSegment.a.c + (org.schema.common.util.ByteUtil.b(paramByte3) << 4);
/*  864:     */    
/*  866: 864 */    return this.segmentBuffer.a(paramByte1, paramByte2, paramSegment);
/*  867:     */  }
/*  868:     */  
/*  869:     */  public static void main(String[] paramArrayOfString)
/*  870:     */  {
/*  871: 869 */    System.err.println(org.schema.common.util.ByteUtil.b(16));
/*  872:     */  }
/*  873:     */  
/*  874:     */  public q getNeighboringSegmentPosUnsave(o paramo, org.schema.game.common.data.world.Segment paramSegment, q paramq1, q paramq2) {
/*  875: 873 */    int i = paramo.a >> 4;
/*  876: 874 */    int j = paramo.b >> 4;
/*  877: 875 */    int k = paramo.c >> 4;
/*  878:     */    
/*  879: 877 */    paramq2.a = (paramSegment.b.a + i);
/*  880: 878 */    paramq2.b = (paramSegment.b.b + j);
/*  881: 879 */    paramq2.c = (paramSegment.b.c + k);
/*  882:     */    
/*  883: 881 */    paramq1.a = (paramSegment.a.a + (i << 4));
/*  884: 882 */    paramq1.b = (paramSegment.a.b + (j << 4));
/*  885: 883 */    paramq1.c = (paramSegment.a.c + (k << 4));
/*  886:     */    
/*  887: 885 */    paramo.a = ((byte)(paramo.a & 0xF));
/*  888: 886 */    paramo.b = ((byte)(paramo.b & 0xF));
/*  889: 887 */    paramo.c = ((byte)(paramo.c & 0xF));
/*  890:     */    
/*  891: 889 */    return paramq1;
/*  892:     */  }
/*  893:     */  
/*  894:     */  private static void modVec(o paramo) {
/*  895: 893 */    paramo.a = ((byte)org.schema.common.util.ByteUtil.d(paramo.a));
/*  896: 894 */    paramo.b = ((byte)org.schema.common.util.ByteUtil.d(paramo.b));
/*  897: 895 */    paramo.c = ((byte)org.schema.common.util.ByteUtil.d(paramo.c));
/*  898:     */  }
/*  899:     */  
/*  900: 898 */  public q getNeighborSegmentPos(q paramq1, int paramInt, q paramq2) { paramq2.b(paramq1);
/*  901: 899 */    switch (paramInt) {
/*  902: 900 */    case 1:  paramq2.a -= 16;break;
/*  903: 901 */    case 0:  paramq2.a += 16;break;
/*  904: 902 */    case 3:  paramq2.b -= 16;break;
/*  905: 903 */    case 2:  paramq2.b += 16;break;
/*  906: 904 */    case 5:  paramq2.c -= 16;break;
/*  907: 905 */    case 4:  paramq2.c += 16;break;
/*  908: 906 */    default:  if (!$assertionsDisabled) throw new AssertionError();
/*  909:     */      break; }
/*  910: 908 */    return paramq2;
/*  911:     */  }
/*  912:     */  
/*  923:     */  public java.util.ArrayList getRadius(javax.vecmath.Vector3f paramVector3f, float paramFloat)
/*  924:     */  {
/*  925: 923 */    paramVector3f = new javax.vecmath.Vector3f(paramVector3f);
/*  926: 924 */    getWorldTransformInverse().transform(paramVector3f);
/*  927: 925 */    paramVector3f.x += 8.0F;
/*  928: 926 */    paramVector3f.y += 8.0F;
/*  929: 927 */    paramVector3f.z += 8.0F;
/*  930: 928 */    return getRadiusRelative(paramVector3f, paramFloat);
/*  931:     */  }
/*  932:     */  
/*  941:     */  public java.util.ArrayList getRadiusRelative(javax.vecmath.Vector3f paramVector3f, float paramFloat)
/*  942:     */  {
/*  943: 941 */    java.util.ArrayList localArrayList = new java.util.ArrayList();
/*  944: 942 */    new com.bulletphysics.linearmath.Transform()
/*  945: 943 */      .setIdentity();
/*  946: 944 */    javax.vecmath.Vector3f localVector3f = new javax.vecmath.Vector3f(paramVector3f);
/*  947: 945 */    paramVector3f = new javax.vecmath.Vector3f(paramVector3f);
/*  948: 946 */    localVector3f.x += paramFloat;
/*  949: 947 */    localVector3f.y += paramFloat;
/*  950: 948 */    localVector3f.z += paramFloat;
/*  951:     */    
/*  952: 950 */    paramVector3f.x -= paramFloat;
/*  953: 951 */    paramVector3f.y -= paramFloat;
/*  954: 952 */    paramVector3f.z -= paramFloat;
/*  955:     */    
/*  956: 954 */    paramVector3f = new q(paramVector3f.x, paramVector3f.y, paramVector3f.z);
/*  957: 955 */    paramFloat = new q(FastMath.c(localVector3f.x), FastMath.c(localVector3f.y), FastMath.c(localVector3f.z));
/*  958:     */    
/*  959: 957 */    paramVector3f.b(org.schema.common.util.ByteUtil.b(paramVector3f.a), org.schema.common.util.ByteUtil.b(paramVector3f.b), org.schema.common.util.ByteUtil.b(paramVector3f.c));
/*  960: 958 */    paramFloat.b(org.schema.common.util.ByteUtil.b(paramFloat.a), org.schema.common.util.ByteUtil.b(paramFloat.b), org.schema.common.util.ByteUtil.b(paramFloat.c));
/*  961: 959 */    paramFloat.a(1, 1, 1);
/*  962: 960 */    paramVector3f.a(16);
/*  963: 961 */    paramFloat.a(16);
/*  964:     */    
/*  965: 963 */    getSegmentBuffer().a(new jP(localArrayList), paramVector3f, paramFloat);
/*  966:     */    
/*  978: 976 */    return localArrayList;
/*  979:     */  }
/*  980:     */  
/*  982:     */  public String getRealName()
/*  983:     */  {
/*  984: 982 */    return this.realName;
/*  985:     */  }
/*  986:     */  
/*  990:     */  public long getSeed()
/*  991:     */  {
/*  992: 990 */    return this.seed;
/*  993:     */  }
/*  994:     */  
/*  995:     */  public void setSeed(long paramLong) {
/*  996: 994 */    this.seed = paramLong;
/*  997:     */  }
/*  998:     */  
/*  999:     */  public jL getSegmentBuffer() {
/* 1000: 998 */    return this.segmentBuffer;
/* 1001:     */  }
/* 1002:     */  
/* 1005:     */  public org.schema.game.common.data.world.Segment getSegmentFromCache(int paramInt1, int paramInt2, int paramInt3)
/* 1006:     */  {
/* 1007:1005 */    return this.segmentBuffer.a(paramInt1, paramInt2, paramInt3);
/* 1008:     */  }
/* 1009:     */  
/* 1011:     */  public jY getSegmentProvider()
/* 1012:     */  {
/* 1013:1011 */    return this.segmentProvider;
/* 1014:     */  }
/* 1015:     */  
/* 1018:     */  public int getTotalElements()
/* 1019:     */  {
/* 1020:1018 */    return this.totalElements;
/* 1021:     */  }
/* 1022:     */  
/* 1029:     */  public void getTransformedAABB(javax.vecmath.Vector3f paramVector3f1, javax.vecmath.Vector3f paramVector3f2, float paramFloat, javax.vecmath.Vector3f paramVector3f3, javax.vecmath.Vector3f paramVector3f4)
/* 1030:     */  {
/* 1031:1029 */    paramVector3f3.set(getSegmentBuffer().a().a);
/* 1032:1030 */    paramVector3f4.set(getSegmentBuffer().a().b);
/* 1033:1031 */    if (getSegmentBuffer().c() == 0) {
/* 1034:1032 */      paramVector3f3.set(0.0F, 0.0F, 0.0F);
/* 1035:1033 */      paramVector3f4.set(0.0F, 0.0F, 0.0F);return;
/* 1036:     */    }
/* 1037:1035 */    if ((paramVector3f3.x > paramVector3f4.x) || (paramVector3f3.y > paramVector3f4.y) || (paramVector3f3.z > paramVector3f4.z)) {
/* 1038:1036 */      paramVector3f3.set(0.0F, 0.0F, 0.0F);
/* 1039:1037 */      paramVector3f4.set(0.0F, 0.0F, 0.0F);
/* 1040:     */    }
/* 1041:1039 */    AabbUtil2.transformAabb(paramVector3f3, paramVector3f4, paramFloat, getWorldTransform(), paramVector3f1, paramVector3f2);
/* 1042:     */  }
/* 1043:     */  
/* 1044:     */  public String getUniqueIdentifier()
/* 1045:     */  {
/* 1046:1044 */    return this.uniqueIdentifier;
/* 1047:     */  }
/* 1048:     */  
/* 1052:     */  public com.bulletphysics.linearmath.Transform getWorldTransformInverse()
/* 1053:     */  {
/* 1054:1052 */    return this.worldTransformInverse;
/* 1055:     */  }
/* 1056:     */  
/* 1062:     */  public boolean hasNeighborElements(org.schema.game.common.data.world.Segment paramSegment, byte paramByte1, byte paramByte2, byte paramByte3, q paramq)
/* 1063:     */  {
/* 1064:     */    byte b1;
/* 1065:     */    
/* 1069:     */    byte b2;
/* 1070:     */    
/* 1074:     */    byte b3;
/* 1075:     */    
/* 1079:1077 */    if (org.schema.game.common.data.world.SegmentData.allNeighborsInside(paramByte1, paramByte2, paramByte3)) {
/* 1080:1078 */      for (paramq = 0; paramq < 6; paramq++) {
/* 1081:1079 */        b1 = (byte)org.schema.common.util.ByteUtil.d(paramByte1 + org.schema.game.common.data.element.Element.DIRECTIONSb[paramq].a);
/* 1082:1080 */        b2 = (byte)org.schema.common.util.ByteUtil.d(paramByte2 + org.schema.game.common.data.element.Element.DIRECTIONSb[paramq].b);
/* 1083:1081 */        b3 = (byte)org.schema.common.util.ByteUtil.d(paramByte3 + org.schema.game.common.data.element.Element.DIRECTIONSb[paramq].c);
/* 1084:1082 */        if (paramSegment.a().containsUnsave(b1, b2, b3)) {
/* 1085:1083 */          return true;
/* 1086:     */        }
/* 1087:     */      }
/* 1088:     */    } else {
/* 1089:1087 */      for (paramq = 0; paramq < 6; paramq++) {
/* 1090:1088 */        b1 = (byte)(paramByte1 + org.schema.game.common.data.element.Element.DIRECTIONSb[paramq].a);
/* 1091:1089 */        b2 = (byte)(paramByte2 + org.schema.game.common.data.element.Element.DIRECTIONSb[paramq].b);
/* 1092:1090 */        b3 = (byte)(paramByte3 + org.schema.game.common.data.element.Element.DIRECTIONSb[paramq].c);
/* 1093:     */        org.schema.game.common.data.world.Segment localSegment;
/* 1094:1092 */        if (((localSegment = getNeighboringSegmentFast(paramSegment, b1, b2, b3)) != null) && (!localSegment.g()))
/* 1095:     */        {
/* 1096:1094 */          b1 = (byte)org.schema.common.util.ByteUtil.d(b1);
/* 1097:1095 */          b2 = (byte)org.schema.common.util.ByteUtil.d(b2);
/* 1098:1096 */          b3 = (byte)org.schema.common.util.ByteUtil.d(b3);
/* 1099:1097 */          if (localSegment.a().containsUnsave(b1, b2, b3)) {
/* 1100:1098 */            return true;
/* 1101:     */          }
/* 1102:     */        }
/* 1103:     */      }
/* 1104:     */    }
/* 1105:     */    
/* 1109:1107 */    return false;
/* 1110:     */  }
/* 1111:     */  
/* 1112:     */  public void incTotalElements() {
/* 1113:1111 */    this.totalElements += 1;
/* 1114:1112 */    flagupdateMass();
/* 1115:1113 */    flagUpdateDocking();
/* 1116:     */  }
/* 1117:     */  
/* 1118:     */  public void initPhysics()
/* 1119:     */  {
/* 1120:1118 */    if (getPhysicsDataContainer().getObject() == null) {
/* 1121:1119 */      com.bulletphysics.linearmath.Transform localTransform1 = getRemoteTransformable().a();
/* 1122:     */      
/* 1124:1122 */      Object localObject = new CubeShape(getSegmentBuffer());
/* 1125:     */      
/* 1126:1124 */      CubesCompoundShape localCubesCompoundShape = new CubesCompoundShape(this);
/* 1127:     */      com.bulletphysics.linearmath.Transform localTransform2;
/* 1128:1126 */      (localTransform2 = new com.bulletphysics.linearmath.Transform()).setIdentity();
/* 1129:1127 */      localCubesCompoundShape.addChildShape(localTransform2, (CollisionShape)localObject);
/* 1130:1128 */      getPhysicsDataContainer().setShapeChield((CompoundShapeChild)localCubesCompoundShape.getChildList().get(localCubesCompoundShape.getChildList().size() - 1));
/* 1131:     */      
/* 1132:1130 */      localCubesCompoundShape.recalculateLocalAabb();
/* 1133:     */      
/* 1134:1132 */      getPhysicsDataContainer().setShape(localCubesCompoundShape);
/* 1135:1133 */      getPhysicsDataContainer().initialTransform = new com.bulletphysics.linearmath.Transform();
/* 1136:1134 */      getPhysicsDataContainer().initialTransform.set(localTransform1);
/* 1137:     */      
/* 1138:1136 */      (
/* 1139:     */      
/* 1143:1141 */        localObject = getPhysics().getBodyFromShape(localCubesCompoundShape, getMass(), getPhysicsDataContainer().initialTransform)).setUserPointer(Integer.valueOf(getId()));
/* 1144:     */      
/* 1145:1143 */      getPhysicsDataContainer().setObject((CollisionObject)localObject);
/* 1146:     */      
/* 1147:1145 */      getWorldTransform().set(localTransform1);
/* 1148:     */    }
/* 1149:     */    else {
/* 1150:1148 */      System.err.println("[SegmentController][WARNING] not adding initial physics object. it already exists");
/* 1151:     */    }
/* 1152:1150 */    setFlagPhysicsInit(true);
/* 1153:     */  }
/* 1154:     */  
/* 1157:     */  public boolean isClientOwnObject()
/* 1158:     */  {
/* 1159:1157 */    return (!isOnServer()) && (((ct)getState()).a() == this);
/* 1160:     */  }
/* 1161:     */  
/* 1168:     */  public boolean isInbound(int paramInt1, int paramInt2, int paramInt3)
/* 1169:     */  {
/* 1170:1168 */    if ((paramInt1 <= this.maxPos.a) && (paramInt2 <= this.maxPos.b) && (paramInt3 <= this.maxPos.c) && (paramInt1 >= this.minPos.a) && (paramInt2 >= this.minPos.b) && (paramInt3 >= this.minPos.c)) { return true;
/* 1171:     */    }
/* 1172:     */    
/* 1174:1172 */    return false;
/* 1175:     */  }
/* 1176:     */  
/* 1179:     */  public boolean isInboundCoord(int paramInt, q paramq)
/* 1180:     */  {
/* 1181:1179 */    if ((paramq.a(paramInt) <= this.maxPos.a(paramInt)) && (paramq.a(paramInt) >= this.minPos.a(paramInt))) { return true;
/* 1182:     */    }
/* 1183:1181 */    return false;
/* 1184:     */  }
/* 1185:     */  
/* 1188:     */  public boolean isInbound(q paramq)
/* 1189:     */  {
/* 1190:1188 */    return isInbound(paramq.a, paramq.b, paramq.c);
/* 1191:     */  }
/* 1192:     */  
/* 1203:1201 */  public boolean isInboundAbs(q paramq) { return isInbound(paramq.a, paramq.b, paramq.c); }
/* 1204:     */  
/* 1205:     */  public boolean isInboundAbs(int paramInt1, int paramInt2, int paramInt3) {
/* 1206:1204 */    boolean bool = true;
/* 1207:     */    
/* 1208:1206 */    paramInt1 = org.schema.common.util.ByteUtil.a(paramInt1);
/* 1209:1207 */    paramInt2 = org.schema.common.util.ByteUtil.a(paramInt2);
/* 1210:1208 */    paramInt3 = org.schema.common.util.ByteUtil.a(paramInt3);
/* 1211:1209 */    if (getMaxPos() != null) {
/* 1212:1210 */      bool = (paramInt1 <= getMaxPos().a) && (paramInt2 <= getMaxPos().b) && (paramInt3 <= getMaxPos().c);
/* 1213:     */    }
/* 1214:     */    
/* 1217:1215 */    if ((getMinPos() != null) && (bool)) {
/* 1218:1216 */      bool = (paramInt1 >= getMinPos().a) && (paramInt2 >= getMinPos().b) && (paramInt3 >= getMinPos().c);
/* 1219:     */    }
/* 1220:     */    
/* 1222:1220 */    return bool;
/* 1223:     */  }
/* 1224:     */  
/* 1225:     */  public boolean isInboundSegmentPos(int paramInt1, int paramInt2, int paramInt3)
/* 1226:     */  {
/* 1227:1225 */    boolean bool = true;
/* 1228:1226 */    if (getMaxPos() != null) {
/* 1229:1227 */      bool = (paramInt1 <= getMaxPos().a << 4) && (paramInt2 <= getMaxPos().b << 4) && (paramInt3 <= getMaxPos().c << 4);
/* 1230:     */    }
/* 1231:     */    
/* 1233:1231 */    if (getMinPos() != null) {
/* 1234:1232 */      bool = (bool) && (paramInt1 >= getMinPos().a << 4) && (paramInt2 >= getMinPos().b << 4) && (paramInt3 >= getMinPos().c << 4);
/* 1235:     */    }
/* 1236:     */    
/* 1239:1237 */    return bool;
/* 1240:     */  }
/* 1241:     */  
/* 1243:     */  public boolean isInboundSegmentPos(q paramq)
/* 1244:     */  {
/* 1245:1243 */    return isInboundSegmentPos(paramq.a, paramq.b, paramq.c);
/* 1246:     */  }
/* 1247:     */  
/* 1248:     */  public void onAddedElement(short paramShort, byte paramByte1, byte paramByte2, byte paramByte3, int paramInt, org.schema.game.common.data.world.Segment paramSegment, boolean paramBoolean) {
/* 1249:1247 */    if (paramBoolean) {
/* 1250:1248 */      this.segmentBuffer.a(paramShort, paramInt, paramByte1, paramByte2, paramByte3, paramSegment);
/* 1251:     */    }
/* 1252:     */    
/* 1253:1251 */    getElementClassCountMap().b(paramShort);
/* 1254:1252 */    incTotalElements();
/* 1255:     */  }
/* 1256:     */  
/* 1261:     */  public void onProximity(SegmentController paramSegmentController) {}
/* 1262:     */  
/* 1267:     */  public void onRemovedElement(short paramShort, int paramInt, byte paramByte1, byte paramByte2, byte paramByte3, org.schema.game.common.data.world.Segment paramSegment, boolean paramBoolean)
/* 1268:     */  {
/* 1269:1267 */    this.segmentBuffer.b(paramShort, paramInt, paramByte1, paramByte2, paramByte3, paramSegment);
/* 1270:     */    
/* 1272:1270 */    getElementClassCountMap().a(paramShort);
/* 1273:1271 */    decTotalElements();
/* 1274:     */    
/* 1276:1274 */    if (!paramBoolean)
/* 1277:     */    {
/* 1278:1276 */      paramInt = new q();
/* 1279:1277 */      paramSegment.a(paramByte1, paramByte2, paramByte3, paramInt);
/* 1280:     */      
/* 1281:1279 */      getControlElementMap().onRemoveElement(paramInt, paramShort);
/* 1282:     */      
/* 1283:1281 */      if ((isOnServer()) && (paramShort == 291)) {
/* 1284:1282 */        System.err.println("[SREVER] FACTION BLOCK REMOVED FROM " + this + "; resetting faction !!!!!!!!!!!!!!");
/* 1285:1283 */        setFactionId(0);
/* 1286:     */      }
/* 1287:     */    }
/* 1288:     */  }
/* 1289:     */  
/* 1300:     */  public void removeAllConstraints(zP paramzP)
/* 1301:     */  {
/* 1302:1300 */    com.bulletphysics.dynamics.RigidBody localRigidBody = (com.bulletphysics.dynamics.RigidBody)paramzP.getPhysicsDataContainer().getObject();
/* 1303:     */    
/* 1304:1302 */    PhysicsExt localPhysicsExt = getPhysics();
/* 1305:1303 */    for (int i = 0; i < localPhysicsExt.getDynamicsWorld().getNumConstraints(); i++) {
/* 1306:     */      TypedConstraint localTypedConstraint;
/* 1307:1305 */      if (((localTypedConstraint = localPhysicsExt.getDynamicsWorld().getConstraint(i)).getRigidBodyA() == localRigidBody) || (localTypedConstraint.getRigidBodyB() == localRigidBody)) {
/* 1308:1306 */        localPhysicsExt.getDynamicsWorld().removeConstraint(localTypedConstraint);
/* 1309:1307 */        System.err.println("[SEGMENT-CONTROLLER][PHYSICS] constraint removed on " + this + " " + getState());
/* 1310:     */        
/* 1312:1310 */        getPhysics().removeTimedExceptions(this, paramzP);
/* 1313:1311 */        getPhysics().addTimedPhysicsException(localTypedConstraint.getRigidBodyA(), localTypedConstraint.getRigidBodyB(), 4000);
/* 1314:1312 */        getPhysics().addTimedPhysicsException(getPhysicsDataContainer().getObject(), paramzP.getPhysicsDataContainer().getObject(), 4000);
/* 1315:     */        
/* 1316:1314 */        localRigidBody.setDamping(0.1F, 0.1F);
/* 1317:     */        
/* 1318:1316 */        removeAllConstraints(paramzP);
/* 1319:1317 */        return;
/* 1320:     */      }
/* 1321:     */    }
/* 1322:     */  }
/* 1323:     */  
/* 1326:     */  public void resetTotalElements()
/* 1327:     */  {
/* 1328:1326 */    this.totalElements = 0;
/* 1329:1327 */    flagupdateMass();
/* 1330:1328 */    flagUpdateDocking();
/* 1331:     */  }
/* 1332:     */  
/* 1337:     */  public void setCreatorThread(kG paramkG)
/* 1338:     */  {
/* 1339:1337 */    this.creatorThread = paramkG;
/* 1340:     */  }
/* 1341:     */  
/* 1342:1340 */  public void setCurrentBlockController(le paramle, q paramq) { setCurrentBlockController(paramle, paramq, 0); }
/* 1343:     */  
/* 1344:     */  public void setCurrentBlockController(le paramle, q paramq, int paramInt) {
/* 1345:1343 */    if (paramq != null)
/* 1346:     */      try
/* 1347:     */      {
/* 1348:     */        le localle;
/* 1349:     */        
/* 1350:1348 */        if (((localle = getSegmentBuffer().a(paramq, true)) != null) && (paramle != null)) {
/* 1351:1349 */          paramle.a();
/* 1352:1350 */          short s2 = paramle.a().a().getType(paramle.a(this.tmpLocalPos));
/* 1353:1351 */          short s1 = localle.a().a().getType(localle.a(this.tmpLocalPos));
/* 1354:     */          
/* 1356:1354 */          ElementInformation localElementInformation1 = ElementKeyMap.getInfo(s2);
/* 1357:     */          ElementInformation localElementInformation2;
/* 1358:1356 */          if ((localElementInformation2 = ElementKeyMap.getInfo(s1)) == null) {
/* 1359:1357 */            System.err.println("WARNING. cannot connect block controller to null");
/* 1360:1358 */            return;
/* 1361:     */          }
/* 1362:1360 */          if (((this instanceof ld)) && 
/* 1363:1361 */            (!((ld)this).a().canBeControlled(s1))) {
/* 1364:1362 */            System.err.println("This cant be controlled by " + this + ": " + s1);
/* 1365:1363 */            throw new CannotBeControlledException(localElementInformation1, localElementInformation2);
/* 1366:     */          }
/* 1367:     */          
/* 1368:     */          org.schema.game.common.data.element.ControlElementMap localControlElementMap;
/* 1369:1367 */          if (localElementInformation2.getControlledBy().contains(Short.valueOf(s2))) {
/* 1370:1368 */            localControlElementMap = paramle.a().a().getControlElementMap();
/* 1371:1369 */            if (paramInt == 0) {
/* 1372:1370 */              localControlElementMap.switchControllerForElement(paramle.a(new q()), paramq, s1);return; }
/* 1373:1371 */            if (paramInt == 1)
/* 1374:     */            {
/* 1375:1373 */              if (!localControlElementMap.isControlling(paramle.a(new q()), paramq, s1)) {
/* 1376:1374 */                localControlElementMap.switchControllerForElement(paramle.a(new q()), paramq, s1);
/* 1377:     */              }
/* 1378:     */              
/* 1380:     */            }
/* 1381:1379 */            else if (localControlElementMap.isControlling(paramle.a(new q()), paramq, s1)) {
/* 1382:1380 */              localControlElementMap.switchControllerForElement(paramle.a(new q()), paramq, s1);
/* 1383:     */            }
/* 1384:     */            
/* 1386:1384 */            return; }
/* 1387:1385 */          System.err.println("OBJECT " + s1 + " CANNOT BE CONTROLLED BY " + localControlElementMap);
/* 1388:1386 */          if ((!isOnServer()) && 
/* 1389:1387 */            (localElementInformation2.getControlledBy().size() > 0)) {
/* 1390:1388 */            ((ct)getState()).a().b(ElementKeyMap.getInfo(s1).getName() + " cannot be\nconnected to " + ElementKeyMap.getInfo(localControlElementMap).getName() + ".\nReason: Incompatible blocks");
/* 1391:     */          }
/* 1392:     */        }
/* 1393:     */        return;
/* 1394:     */      }
/* 1395:     */      catch (ElementClassNotFoundException localElementClassNotFoundException) {
/* 1396:1394 */        System.err.println("WARING TRIED TO CONNECT TO AIR");
/* 1397:1395 */        return;
/* 1398:     */      }
/* 1399:1397 */    System.err.println("ADDING CONTROL WARNING: CONTROLLED IS NULL " + paramle + ": " + paramq);
/* 1400:     */  }
/* 1401:     */  
/* 1408:     */  public void setId(int paramInt)
/* 1409:     */  {
/* 1410:1408 */    this.id = paramInt;
/* 1411:     */  }
/* 1412:     */  
/* 1416:     */  public void setRealName(String paramString)
/* 1417:     */  {
/* 1418:1416 */    this.realName = paramString;
/* 1419:     */  }
/* 1420:     */  
/* 1422:     */  public void setSegmentProvider(jY paramjY)
/* 1423:     */  {
/* 1424:1422 */    this.segmentProvider = paramjY;
/* 1425:     */  }
/* 1426:     */  
/* 1428:     */  public void setUniqueIdentifier(String paramString)
/* 1429:     */  {
/* 1430:1428 */    this.uniqueIdentifier = paramString;
/* 1431:     */  }
/* 1432:     */  
/* 1442:     */  public abstract void startCreatorThread();
/* 1443:     */  
/* 1453:     */  public void updateLocal(xq paramxq)
/* 1454:     */  {
/* 1455:1453 */    super.updateLocal(paramxq);
/* 1456:     */    
/* 1460:1458 */    long l = System.currentTimeMillis();
/* 1461:     */    
/* 1465:     */    Object localObject1;
/* 1466:     */    
/* 1469:1467 */    if (xu.F.b()) {
/* 1470:1468 */      xO localxO = getSegmentBuffer().a();
/* 1471:     */      
/* 1472:1470 */      if (isOnServer()) {
/* 1473:1471 */        if (((vg)getState()).a().getSector(getSectorId()).a.a(2, 2, 2)) {
/* 1474:1472 */          localObject1 = new xR(localxO.a, localxO.b, getWorldTransform(), 0.0F, 0.0F);
/* 1475:1473 */          yk.a.add(localObject1);
/* 1476:     */        }
/* 1477:     */      } else {
/* 1478:1476 */        localObject1 = new xR(localxO.a, localxO.b, getWorldTransformClient(), 1.0F, 1.0F);
/* 1479:1477 */        yk.a.add(localObject1);
/* 1480:     */      }
/* 1481:     */    }
/* 1482:     */    
/* 1485:1483 */    if ((l - getLastSlowdown() < 5000L) || (isImmediateStuck())) {
/* 1486:1484 */      if (((getSlowdownStart() > 0L) && (l - getSlowdownStart() > 10000L)) || (isImmediateStuck())) {
/* 1487:1485 */        System.err.println("[SEGCON] " + getState() + " stuck physics detected on " + this);
/* 1488:1486 */        if (isOnServer()) {
/* 1489:1487 */          if (isImmediateStuck()) {
/* 1490:1488 */            ((vg)getState()).a().broadcastMessage("Structure\n" + this + "\ndetected to be stuck\nWARPING OUT OF COLLISION!", 3);
/* 1491:     */          } else {
/* 1492:1490 */            ((vg)getState()).a().broadcastMessage("Structure\n" + this + "\nis slowing down the server\nWARPING OUT OF COLLISION!", 3);
/* 1493:     */          }
/* 1494:     */        }
/* 1495:1493 */        warpOutOfCollision();
/* 1496:1494 */        getPhysicsDataContainer().updatePhysical();
/* 1497:1495 */        resetSlowdownStart();
/* 1498:1496 */        setImmediateStuck(false);
/* 1499:     */      }
/* 1500:     */    } else {
/* 1501:1499 */      resetSlowdownStart();
/* 1502:     */    }
/* 1503:     */    
/* 1504:1502 */    getDockingController().a(paramxq);
/* 1505:1503 */    assert (getUniqueIdentifier() != null);
/* 1506:     */    
/* 1507:1505 */    getWorldTransformInverse().set(getWorldTransform());
/* 1508:     */    
/* 1509:1507 */    getWorldTransformInverse().inverse();
/* 1510:     */    
/* 1511:1509 */    if ((this.flagCheckDocking) && (getPhysicsDataContainer().isInitialized()) && (System.currentTimeMillis() - this.delayDockingCheck > 1000L)) {
/* 1512:     */      try
/* 1513:     */      {
/* 1514:1512 */        getDockingController().a();
/* 1515:1513 */        this.flagCheckDocking = false;
/* 1516:1514 */        this.delayDockingCheck = System.currentTimeMillis();
/* 1517:     */      } catch (CollectionNotLoadedException localCollectionNotLoadedException) {
/* 1518:1516 */        System.err.println("[SEGMENT-CONTROLLER] Cannot validate docking yet, because enhancers aren't fully loaded -> DELAY by 1 sec");
/* 1519:1517 */        this.delayDockingCheck = System.currentTimeMillis();
/* 1520:     */      }
/* 1521:     */    }
/* 1522:     */    
/* 1525:1523 */    if ((!isOnServer()) && (!getNeedsActiveUpdateClient().isEmpty())) {
/* 1526:1524 */      synchronized (getNeedsActiveUpdateClient()) {
/* 1527:1525 */        while (!getNeedsActiveUpdateClient().isEmpty()) {
/* 1528:1526 */          localObject1 = (le)getNeedsActiveUpdateClient().remove(0);
/* 1529:1527 */          ((ld)this).a().handleBlockActivate((le)localObject1, ((le)localObject1).a());
/* 1530:     */        }
/* 1531:     */      }
/* 1532:     */    }
/* 1533:1531 */    if ((this.aabbRecalcFlag) && (getPhysicsDataContainer().isInitialized())) {
/* 1534:1532 */      flagupdateMass();
/* 1535:1533 */      ((CompoundShape)getPhysicsDataContainer().getShape()).recalculateLocalAabb();
/* 1536:1534 */      this.aabbRecalcFlag = false;
/* 1537:     */    }
/* 1538:     */    
/* 1539:1537 */    if ((isFlagSegmentBufferAABBUpdate()) && (getPhysicsDataContainer().isInitialized())) {
/* 1540:1538 */      if (getPhysicsDataContainer().getObject() != null) {
/* 1541:1539 */        getPhysicsDataContainer().getObject().activate(true);
/* 1542:     */      }
/* 1543:1541 */      aabbRecalcFlag();
/* 1544:1542 */      setFlagSegmentBufferAABBUpdate(false);
/* 1545:     */    }
/* 1546:1544 */    if ((!isOnServer()) && (xe.a() != null))
/* 1547:     */    {
/* 1557:1555 */      this.camLocalTmp.set(xe.a().a());
/* 1558:     */      
/* 1560:1558 */      getClientTransformInverse().set(getWorldTransformClient());
/* 1561:     */      
/* 1562:1560 */      getClientTransformInverse().inverse();
/* 1563:     */      
/* 1566:1564 */      getClientTransformInverse().transform(this.camLocalTmp);
/* 1567:1565 */      this.camPosLocal.set(this.camLocalTmp);
/* 1568:     */      
/* 1569:1567 */      if (((xe.a() instanceof dw)) && (((dw)xe.a()).a() == this))
/* 1570:     */      {
/* 1571:1569 */        getCamForwLocal().set(xe.a().i());
/* 1572:1570 */        getCamLeftLocal().set(xe.a().h());
/* 1573:1571 */        getCamLeftLocal().negate();
/* 1574:1572 */        getCamUpLocal().set(xe.a().g());
/* 1575:     */        
/* 1576:1574 */        getWorldTransformInverse().basis.transform(getCamForwLocal());
/* 1577:1575 */        getWorldTransformInverse().basis.transform(getCamLeftLocal());
/* 1578:1576 */        getWorldTransformInverse().basis.transform(getCamUpLocal());
/* 1579:     */      }
/* 1580:     */    }
/* 1581:     */    
/* 1589:     */    try
/* 1590:     */    {
/* 1591:1589 */      this.segmentProvider.d();
/* 1592:     */    } catch (InterruptedException localInterruptedException) {
/* 1593:1591 */      (??? = 
/* 1594:     */      
/* 1597:1595 */        localInterruptedException).printStackTrace();throw new ErrorDialogException(((InterruptedException)???).getMessage());
/* 1598:1593 */    } catch (java.io.IOException localIOException) { localIOException;
/* 1599:     */    }
/* 1600:     */    
/* 1604:1599 */    getPhysicsState().d();
/* 1605:1600 */    getSegmentBuffer();
/* 1606:     */    
/* 1607:1602 */    this.lastSector = getSectorId();
/* 1608:     */  }
/* 1609:     */  
/* 1610:     */  private void warpOutOfCollision() {
/* 1611:1606 */    if ((isOnServer()) && (getMass() > 0.0F)) {
/* 1612:1607 */      javax.vecmath.Vector3f localVector3f1 = new javax.vecmath.Vector3f();
/* 1613:1608 */      javax.vecmath.Vector3f localVector3f2 = new javax.vecmath.Vector3f();
/* 1614:     */      
/* 1616:1611 */      com.bulletphysics.linearmath.Transform localTransform = new com.bulletphysics.linearmath.Transform(getWorldTransform());
/* 1617:     */      
/* 1618:1613 */      int i = 0;
/* 1619:     */      do {
/* 1620:1615 */        getTransformedAABB(localVector3f1, localVector3f2, 1.0F, new javax.vecmath.Vector3f(), new javax.vecmath.Vector3f());
/* 1621:     */        
/* 1622:1617 */        i = 0;
/* 1623:1618 */        for (localObject1 = getState().getLocalAndRemoteObjectContainer().getLocalObjects().values().iterator(); ((java.util.Iterator)localObject1).hasNext();) { Object localObject2;
/* 1624:1619 */          if (((localObject2 = (Sendable)((java.util.Iterator)localObject1).next()) instanceof mF))
/* 1625:     */          {
/* 1627:1622 */            if (((localObject2 = (mF)localObject2) != this) && (((mF)localObject2).getSectorId() == getSectorId()) && (!((mF)localObject2).isHidden()))
/* 1628:     */            {
/* 1629:1624 */              javax.vecmath.Vector3f localVector3f3 = new javax.vecmath.Vector3f();
/* 1630:1625 */              javax.vecmath.Vector3f localVector3f4 = new javax.vecmath.Vector3f();
/* 1631:1626 */              ((mF)localObject2).getTransformedAABB(localVector3f3, localVector3f4, 1.0F, new javax.vecmath.Vector3f(), new javax.vecmath.Vector3f());
/* 1632:1627 */              if (AabbUtil2.testAabbAgainstAabb2(localVector3f1, localVector3f2, localVector3f3, localVector3f4)) {
/* 1633:1628 */                i = 1;
/* 1634:1629 */                break;
/* 1635:     */              }
/* 1636:     */            }
/* 1637:     */          }
/* 1638:     */        }
/* 1639:1634 */        if (i != 0) {
/* 1640:1635 */          getWorldTransform().origin.y += 20.0F;
/* 1641:     */        }
/* 1642:1637 */      } while (i != 0);
/* 1643:1638 */      System.err.println("[SEREVR][SEGMENTCONTROLLER] WARNING: COLLISION RECOVER: " + this + " warped from " + localTransform.origin + " to " + getWorldTransform().origin);
/* 1644:1639 */      Object localObject1 = new com.bulletphysics.linearmath.Transform(getWorldTransform());
/* 1645:1640 */      getWorldTransform().set(localTransform);
/* 1646:1641 */      warpTransformable((com.bulletphysics.linearmath.Transform)localObject1, true);
/* 1647:     */    }
/* 1648:     */  }
/* 1649:     */  
/* 1651:     */  public boolean updateMass()
/* 1652:     */  {
/* 1653:1648 */    return true;
/* 1654:     */  }
/* 1655:     */  
/* 1656:     */  public abstract void writeAllBufferedSegmentsToDatabase();
/* 1657:     */  
/* 1658:     */  public long getTimeCreated()
/* 1659:     */  {
/* 1660:1655 */    return this.timeCreated;
/* 1661:     */  }
/* 1662:     */  
/* 1664:     */  public void setTimeCreated(long paramLong)
/* 1665:     */  {
/* 1666:1661 */    this.timeCreated = paramLong;
/* 1667:     */  }
/* 1668:     */  
/* 1670:     */  public boolean isFlagSegmentBufferAABBUpdate()
/* 1671:     */  {
/* 1672:1667 */    return this.flagSegmentBufferAABBUpdate;
/* 1673:     */  }
/* 1674:     */  
/* 1676:     */  public void setFlagSegmentBufferAABBUpdate(boolean paramBoolean)
/* 1677:     */  {
/* 1678:1673 */    this.flagSegmentBufferAABBUpdate = paramBoolean;
/* 1679:     */  }
/* 1680:     */  
/* 1682:     */  public String getLastModifier()
/* 1683:     */  {
/* 1684:1679 */    return this.lastModifier;
/* 1685:     */  }
/* 1686:     */  
/* 1688:     */  public void setLastModifier(String paramString)
/* 1689:     */  {
/* 1690:1685 */    this.lastModifier = paramString;
/* 1691:     */  }
/* 1692:     */  
/* 1694:     */  public String getSpawner()
/* 1695:     */  {
/* 1696:1691 */    return this.spawner;
/* 1697:     */  }
/* 1698:     */  
/* 1700:     */  public java.util.ArrayList getNeedsActiveUpdateClient()
/* 1701:     */  {
/* 1702:1697 */    return this.needsActiveUpdateClient;
/* 1703:     */  }
/* 1704:     */  
/* 1706:     */  public com.bulletphysics.linearmath.Transform getClientTransformInverse()
/* 1707:     */  {
/* 1708:1703 */    return this.clientTransformInverse;
/* 1709:     */  }
/* 1710:     */  
/* 1713:     */  public void destroyPersistent()
/* 1714:     */  {
/* 1715:1710 */    assert (isOnServer());
/* 1716:1711 */    vg localvg = (vg)getState();
/* 1717:1712 */    new StringBuilder().append(vg.a).append(getUniqueIdentifier()).toString();
/* 1718:     */    try {
/* 1719:1714 */      Object localObject1 = new java.io.File(vg.a + getUniqueIdentifier() + ".ent");
/* 1720:1715 */      System.err.println("[SERVER][SEGMENTCONTROLLER] PERMANENTLY DELETING ENTITY: " + ((java.io.File)localObject1).getName());
/* 1721:1716 */      ((java.io.File)localObject1).delete();
/* 1722:1717 */      localObject1 = new jQ(this);
/* 1723:     */      
/* 1730:1725 */      for (Object localObject2 : new java.io.File(vg.jdField_f_of_type_JavaLangString)
/* 1731:1724 */        .listFiles((FilenameFilter)localObject1))
/* 1732:     */      {
/* 1733:1726 */        System.err.println("[SERVER][SEGMENTCONTROLLER] PERMANENTLY DELETING ENTITY DATA: " + localObject2.getName());
/* 1734:1727 */        localObject2.delete();
/* 1735:     */      }
/* 1736:1729 */      localvg.a().b(this); return;
/* 1737:1730 */    } catch (SQLException localSQLException) { 
/* 1738:     */      
/* 1739:1732 */        localSQLException;
/* 1740:     */    }
/* 1741:     */  }
/* 1742:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.SegmentController
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */