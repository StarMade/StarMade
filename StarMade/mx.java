/*    1:     */import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
/*    2:     */import com.bulletphysics.collision.shapes.CollisionShape;
/*    3:     */import com.bulletphysics.dynamics.DynamicsWorld;
/*    4:     */import com.bulletphysics.linearmath.AabbUtil2;
/*    5:     */import com.bulletphysics.linearmath.Transform;
/*    6:     */import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*    7:     */import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*    8:     */import java.io.ByteArrayInputStream;
/*    9:     */import java.io.ByteArrayOutputStream;
/*   10:     */import java.io.DataInputStream;
/*   11:     */import java.io.DataOutputStream;
/*   12:     */import java.io.File;
/*   13:     */import java.io.IOException;
/*   14:     */import java.io.PrintStream;
/*   15:     */import java.sql.SQLException;
/*   16:     */import java.util.ArrayList;
/*   17:     */import java.util.Arrays;
/*   18:     */import java.util.ConcurrentModificationException;
/*   19:     */import java.util.HashMap;
/*   20:     */import java.util.HashSet;
/*   21:     */import java.util.Iterator;
/*   22:     */import java.util.List;
/*   23:     */import java.util.Map;
/*   24:     */import java.util.Map.Entry;
/*   25:     */import java.util.Random;
/*   26:     */import java.util.Set;
/*   27:     */import java.util.Vector;
/*   28:     */import java.util.concurrent.RejectedExecutionException;
/*   29:     */import java.util.concurrent.ThreadPoolExecutor;
/*   30:     */import javax.vecmath.Matrix3f;
/*   31:     */import javax.vecmath.Vector3f;
/*   32:     */import javax.vecmath.Vector4f;
/*   33:     */import org.schema.common.util.ByteUtil;
/*   34:     */import org.schema.game.common.controller.EditableSendableSegmentController;
/*   35:     */import org.schema.game.common.controller.SegmentController;
/*   36:     */import org.schema.game.common.controller.database.DatabaseIndex;
/*   37:     */import org.schema.game.common.data.physics.CubeRayCastResult;
/*   38:     */import org.schema.game.common.data.physics.ModifiedDynamicsWorld;
/*   39:     */import org.schema.game.common.data.physics.PhysicsExt;
/*   40:     */import org.schema.game.common.data.world.Segment;
/*   41:     */import org.schema.game.common.data.world.Universe;
/*   42:     */import org.schema.game.server.controller.EntityAlreadyExistsException;
/*   43:     */import org.schema.game.server.controller.EntityNotFountException;
/*   44:     */import org.schema.game.server.controller.GameServerController;
/*   45:     */import org.schema.schine.graphicsengine.shader.ErrorDialogException;
/*   46:     */import org.schema.schine.network.NetworkStateContainer;
/*   47:     */import org.schema.schine.network.StateInterface;
/*   48:     */import org.schema.schine.network.SynchronizationContainerController;
/*   49:     */import org.schema.schine.network.objects.Sendable;
/*   50:     */import org.schema.schine.network.objects.container.PhysicsDataContainer;
/*   51:     */import org.schema.schine.network.server.ServerEntityWriterThread;
/*   52:     */import org.schema.schine.network.server.ServerMessage;
/*   53:     */
/*  162:     */public class mx
/*  163:     */  implements Ak, zW
/*  164:     */{
/*  165:     */  private static kF[] jdField_a_of_type_ArrayOfKF;
/*  166:     */  public q a;
/*  167:     */  private final vg jdField_a_of_type_Vg;
/*  168:     */  private float jdField_a_of_type_Float;
/*  169: 169 */  private boolean jdField_b_of_type_Boolean = false;
/*  170:     */  private boolean jdField_c_of_type_Boolean;
/*  171:     */  public static final q b;
/*  172:     */  public Set a;
/*  173:     */  private zQ jdField_a_of_type_ZQ;
/*  174:     */  
/*  175:     */  static
/*  176:     */  {
/*  177:  92 */    jdField_a_of_type_ArrayOfKF = new kF[kF.values().length];
/*  178:  93 */    for (int k = 0; k < jdField_a_of_type_ArrayOfKF.length; k++) {
/*  179:  94 */      jdField_a_of_type_ArrayOfKF[k] = kF.values()[k];
/*  180:     */    }
/*  181:  96 */    Arrays.sort(jdField_a_of_type_ArrayOfKF, new my());
/*  182:     */    
/*  188: 103 */    for (k = 0; k < jdField_a_of_type_ArrayOfKF.length; k++) {}
/*  189:     */    
/*  258: 173 */    jdField_b_of_type_Q = new q(2, 2, 2);
/*  259:     */  }
/*  260:     */  
/*  262: 177 */  private static int jdField_a_of_type_Int = 1;
/*  263:     */  
/*  264:     */  private mv jdField_a_of_type_Mv;
/*  265:     */  
/*  266: 181 */  private int jdField_b_of_type_Int = 0;
/*  267:     */  
/*  271: 186 */  private Vector3f jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/*  272: 187 */  private Vector3f jdField_b_of_type_JavaxVecmathVector3f = new Vector3f();
/*  273: 188 */  private Vector3f jdField_c_of_type_JavaxVecmathVector3f = new Vector3f();
/*  274: 189 */  private Vector3f jdField_d_of_type_JavaxVecmathVector3f = new Vector3f();
/*  275: 190 */  private Vector3f jdField_e_of_type_JavaxVecmathVector3f = new Vector3f(-jdField_a_of_type_Int << 4, -jdField_a_of_type_Int << 4, -jdField_a_of_type_Int << 4);
/*  276:     */  
/*  279: 194 */  private Vector3f jdField_f_of_type_JavaxVecmathVector3f = new Vector3f(jdField_a_of_type_Int << 4, jdField_a_of_type_Int << 4, jdField_a_of_type_Int << 4);
/*  280:     */  
/*  283: 198 */  private ArrayList jdField_a_of_type_JavaUtilArrayList = new ArrayList();
/*  284:     */  
/*  285:     */  private int jdField_c_of_type_Int;
/*  286:     */  
/*  287:     */  private long jdField_a_of_type_Long;
/*  288:     */  private boolean jdField_d_of_type_Boolean;
/*  289: 204 */  private static ArrayList jdField_b_of_type_JavaUtilArrayList = new ArrayList();
/*  290:     */  
/*  365: 280 */  private Object jdField_a_of_type_JavaLangObject = new Object();
/*  366:     */  private L jdField_a_of_type_L;
/*  367:     */  private boolean jdField_e_of_type_Boolean;
/*  368:     */  
/*  369:     */  public mx(vg paramvg)
/*  370:     */  {
/*  371: 175 */    this.jdField_a_of_type_JavaUtilSet = new HashSet();
/*  372:     */    
/*  481: 285 */    this.jdField_a_of_type_Vg = paramvg;
/*  482: 286 */    this.jdField_c_of_type_Int = paramvg.getNextFreeObjectId();
/*  483: 287 */    this.jdField_a_of_type_ZQ = paramvg.a().getPhysicsInstance(this);
/*  484: 288 */    this.jdField_a_of_type_ZQ.getDynamicsWorld().setInternalTickCallback(new mB(this, (byte)0), null);
/*  485: 289 */    this.jdField_b_of_type_Long = System.currentTimeMillis();
/*  486:     */  }
/*  487:     */  
/*  488:     */  private void a(vg paramvg, q paramq1, q paramq2) {
/*  489:     */    q localq1;
/*  490: 294 */    a(localq1 = new q());
/*  491:     */    
/*  492:     */    jy localjy;
/*  493: 297 */    (localjy = new jy(paramvg)).setSeed(Universe.getRandom().nextInt());
/*  494:     */    
/*  495: 299 */    int k = Universe.getRandom().nextInt(5) - 2;
/*  496:     */    
/*  497: 301 */    q localq2 = mJ.a(localq1, new q());
/*  498:     */    
/*  501: 305 */    if (paramvg.a().getStellarSystemFromSecPos(localq2).a == mD.e) {
/*  502: 306 */      int i1 = ByteUtil.d((localq2 = this.jdField_a_of_type_Q).jdField_a_of_type_Int) - 8;int i2 = ByteUtil.d(localq2.jdField_b_of_type_Int) - 8;int m = ByteUtil.d(localq2.jdField_c_of_type_Int) - 8;Vector3f localVector3f2 = new Vector3f(8.0F, 8.0F, 8.0F);Vector3f localVector3f1 = new Vector3f(i1, i2, m);float f1 = Math.min(1.0F, localVector3f1.length() / localVector3f2.length());f1 = 1.0F - f1;
/*  503:     */      
/*  504: 308 */      for (int i3 = 0; i3 < kF.values().length;)
/*  505:     */      {
/*  506: 310 */        if (jdField_a_of_type_ArrayOfKF[i3].jdField_a_of_type_Float > f1) break;
/*  507: 311 */        i3++;
/*  508:     */      }
/*  509:     */      
/*  510: 314 */      i3 = Math.min(kF.values().length - 1, Math.max(0, i3 + k));
/*  511: 315 */      localjy.setCreatorId(jdField_a_of_type_ArrayOfKF[i3].ordinal());
/*  512:     */    }
/*  513:     */    else {
/*  514: 318 */      localjy.setCreatorId(Universe.getRandom().nextInt(kF.values().length));
/*  515:     */    }
/*  516:     */    
/*  520: 324 */    localjy.setUniqueIdentifier("ENTITY_FLOATINGROCK_" + System.currentTimeMillis());
/*  521: 325 */    localjy.getMinPos().b(paramq1);
/*  522: 326 */    localjy.getMaxPos().b(paramq2);
/*  523: 327 */    localjy.setId(paramvg.getNextFreeObjectId());
/*  524: 328 */    localjy.setSectorId(this.jdField_c_of_type_Int);
/*  525:     */    
/*  526: 330 */    localjy.initialize();
/*  527: 331 */    int n = 0;
/*  528: 332 */    long l1 = System.currentTimeMillis();
/*  529: 333 */    while ((a(localjy, localq1) != null) && (n < 1000)) {
/*  530: 334 */      a(localq1);
/*  531: 335 */      n++;
/*  532:     */    }
/*  533:     */    long l2;
/*  534: 338 */    if ((l2 = System.currentTimeMillis() - l1) > 10L) {
/*  535: 339 */      System.err.println("[SECTOR] Placing ROCK took " + l2 + "ms");
/*  536:     */    }
/*  537: 341 */    if (n < 1000) {
/*  538: 342 */      this.jdField_a_of_type_JavaUtilArrayList.add(localjy);
/*  539: 343 */      paramvg.a().a().addImmediateSynchronizedObject(localjy);return;
/*  540:     */    }
/*  541:     */    try {
/*  542: 346 */      throw new RuntimeException("Could not place rock " + localjy.getMinPos() + "; " + localjy.getMaxPos());
/*  543: 347 */    } catch (RuntimeException localRuntimeException) { localRuntimeException.printStackTrace();
/*  544:     */      
/*  545: 349 */      System.out.println("[ERRORLOG][SECTOR] PRINTING AABB of all objects");
/*  546: 350 */      for (paramvg = this.jdField_a_of_type_JavaUtilArrayList.iterator(); paramvg.hasNext();) {
/*  547: 351 */        if (((paramq1 = (SegmentController)paramvg.next()) instanceof zP)) {
/*  548: 352 */          if ((!(paramq1 instanceof mF)) || (((mF)paramq1).getSectorId() == localjy.getSectorId()))
/*  549:     */          {
/*  553: 357 */            (paramq2 = (zP)paramq1).getPhysicsDataContainer().getShape().getAabb(paramq2.getPhysicsDataContainer().getCurrentPhysicsTransform(), this.jdField_c_of_type_JavaxVecmathVector3f, this.jdField_d_of_type_JavaxVecmathVector3f);
/*  554: 358 */            System.out.println("[ERRORLOG][SECTOR] " + paramq1 + ": [" + this.jdField_c_of_type_JavaxVecmathVector3f + " " + this.jdField_d_of_type_JavaxVecmathVector3f + "]");
/*  555:     */          }
/*  556:     */        }
/*  557:     */      }
/*  558:     */    }
/*  559:     */  }
/*  560:     */  
/*  561:     */  private Sendable a(SegmentController paramSegmentController, q paramq)
/*  562:     */  {
/*  563: 367 */    long l1 = System.currentTimeMillis();
/*  564: 368 */    SegmentController localSegmentController; Iterator localIterator; if ((paramSegmentController instanceof SegmentController))
/*  565:     */    {
/*  566: 370 */      (localSegmentController = paramSegmentController).getInitialTransform().setIdentity();
/*  567: 371 */      localSegmentController.getInitialTransform().origin.set(paramq.jdField_a_of_type_Int, paramq.jdField_b_of_type_Int, paramq.jdField_c_of_type_Int);
/*  568:     */      
/*  569: 373 */      this.jdField_e_of_type_JavaxVecmathVector3f.set(localSegmentController.getMinPos().jdField_a_of_type_Int << 4, localSegmentController.getMinPos().jdField_b_of_type_Int << 4, localSegmentController.getMinPos().jdField_c_of_type_Int << 4);
/*  570:     */      
/*  573: 377 */      this.jdField_f_of_type_JavaxVecmathVector3f.set(localSegmentController.getMaxPos().jdField_a_of_type_Int << 4, localSegmentController.getMaxPos().jdField_b_of_type_Int << 4, localSegmentController.getMaxPos().jdField_c_of_type_Int << 4);
/*  574:     */      
/*  579: 383 */      AabbUtil2.transformAabb(this.jdField_e_of_type_JavaxVecmathVector3f, this.jdField_f_of_type_JavaxVecmathVector3f, 100.0F, localSegmentController.getInitialTransform(), this.jdField_a_of_type_JavaxVecmathVector3f, this.jdField_b_of_type_JavaxVecmathVector3f);
/*  580:     */      
/*  582: 386 */      for (localIterator = this.jdField_a_of_type_JavaUtilArrayList.iterator(); localIterator.hasNext();) {
/*  583: 387 */        if (((paramSegmentController = (SegmentController)localIterator.next()) instanceof zP))
/*  584: 388 */          if ((!(paramSegmentController instanceof mF)) || (((mF)paramSegmentController).getSectorId() == localSegmentController.getSectorId()))
/*  585:     */          {
/*  586:     */            zP localzP;
/*  587:     */            
/*  589: 393 */            (localzP = (zP)paramSegmentController).getPhysicsDataContainer().getShape().getAabb(localzP.getPhysicsDataContainer().getCurrentPhysicsTransform(), this.jdField_c_of_type_JavaxVecmathVector3f, this.jdField_d_of_type_JavaxVecmathVector3f);
/*  590:     */            
/*  591: 395 */            if (AabbUtil2.testAabbAgainstAabb2(this.jdField_a_of_type_JavaxVecmathVector3f, this.jdField_b_of_type_JavaxVecmathVector3f, this.jdField_c_of_type_JavaxVecmathVector3f, this.jdField_d_of_type_JavaxVecmathVector3f)) {
/*  592:     */              long l3;
/*  593: 397 */              if ((l3 = System.currentTimeMillis() - l1) > 10L) {
/*  594: 398 */                System.err.println("[Sector] [Sector] collision test at " + paramq + " is true: trying another pos " + l3 + "ms");
/*  595:     */              }
/*  596: 400 */              return paramSegmentController;
/*  597:     */            }
/*  598:     */          }
/*  599:     */      }
/*  600:     */    }
/*  601:     */    long l2;
/*  602: 406 */    if ((l2 = System.currentTimeMillis() - l1) > 10L) {
/*  603: 407 */      System.err.println("[Sector] No Collission: " + l2 + "ms");
/*  604:     */    }
/*  605: 409 */    return null;
/*  606:     */  }
/*  607:     */  
/*  608:     */  public final Sendable a(mF parammF, Vector3f paramVector3f)
/*  609:     */  {
/*  610: 414 */    long l1 = System.currentTimeMillis();
/*  611:     */    Object localObject1;
/*  612: 416 */    (localObject1 = new Transform()).basis.set(parammF.getWorldTransform().basis);
/*  613: 417 */    ((Transform)localObject1).origin.set(paramVector3f);
/*  614:     */    
/*  615: 419 */    parammF.getRemoteTransformable().getPhysicsDataContainer().getShape().getAabb((Transform)localObject1, this.jdField_a_of_type_JavaxVecmathVector3f, this.jdField_b_of_type_JavaxVecmathVector3f);
/*  616:     */    
/*  618:     */    Iterator localIterator;
/*  619:     */    
/*  620: 424 */    synchronized (this.jdField_a_of_type_Vg.getLocalAndRemoteObjectContainer().getLocalObjects()) {
/*  621: 425 */      for (localIterator = this.jdField_a_of_type_Vg.getLocalAndRemoteObjectContainer().getLocalObjects().values().iterator(); localIterator.hasNext();) {
/*  622: 426 */        if (((localObject1 = (Sendable)localIterator.next()) instanceof zP))
/*  623: 427 */          if ((!(localObject1 instanceof mF)) || (((mF)localObject1).getSectorId() == parammF.getSectorId()))
/*  624:     */          {
/*  625:     */            zP localzP;
/*  626:     */            
/*  630: 434 */            (localzP = (zP)localObject1).getPhysicsDataContainer().getShape().getAabb(localzP.getPhysicsDataContainer().getCurrentPhysicsTransform(), this.jdField_c_of_type_JavaxVecmathVector3f, this.jdField_d_of_type_JavaxVecmathVector3f);
/*  631:     */            
/*  637: 441 */            if (AabbUtil2.testAabbAgainstAabb2(this.jdField_a_of_type_JavaxVecmathVector3f, this.jdField_b_of_type_JavaxVecmathVector3f, this.jdField_c_of_type_JavaxVecmathVector3f, this.jdField_d_of_type_JavaxVecmathVector3f))
/*  638:     */            {
/*  639:     */              long l3;
/*  640:     */              
/*  641: 445 */              if ((l3 = System.currentTimeMillis() - l1) > 10L) {
/*  642: 446 */                System.err.println("[Sector] collision test at " + paramVector3f + " is true: trying another pos " + this.jdField_a_of_type_JavaxVecmathVector3f + ", " + this.jdField_b_of_type_JavaxVecmathVector3f + " ---> " + this.jdField_c_of_type_JavaxVecmathVector3f + ", " + this.jdField_d_of_type_JavaxVecmathVector3f + ": " + l3 + "ms");
/*  643:     */              }
/*  644:     */              
/*  645: 449 */              return localObject1;
/*  646:     */            }
/*  647:     */          }
/*  648:     */      }
/*  649:     */    }
/*  650:     */    long l2;
/*  651: 455 */    if ((l2 = System.currentTimeMillis() - l1) > 10L) {
/*  652: 456 */      System.err.println("[Sector] No Collission: " + l2 + "ms");
/*  653:     */    }
/*  654: 458 */    return null;
/*  655:     */  }
/*  656:     */  
/*  657: 461 */  public final void a() { synchronized (this.jdField_a_of_type_JavaLangObject)
/*  658:     */    {
/*  659: 463 */      ((ModifiedDynamicsWorld)this.jdField_a_of_type_ZQ.getDynamicsWorld()).clean();
/*  660: 464 */      this.jdField_a_of_type_Vg.a().addToFreePhysics(this.jdField_a_of_type_ZQ, this);
/*  661: 465 */      return;
/*  662:     */    }
/*  663:     */  }
/*  664:     */  
/*  666: 470 */  private final Map jdField_a_of_type_JavaUtilMap = new HashMap();
/*  667:     */  private long jdField_b_of_type_Long;
/*  668:     */  private boolean jdField_f_of_type_Boolean;
/*  669:     */  private mI jdField_a_of_type_MI;
/*  670:     */  
/*  671:     */  public void fromTagStructure(Ah paramAh) {
/*  672: 476 */    paramAh = (Ah[])paramAh.a();
/*  673: 477 */    this.jdField_a_of_type_Q = ((q)paramAh[0].a());
/*  674: 478 */    if ((!jdField_h_of_type_Boolean) && (this.jdField_a_of_type_Q == null)) throw new AssertionError();
/*  675:     */    Ah[] arrayOfAh;
/*  676: 480 */    int k = (arrayOfAh = (Ah[])paramAh[1].a()).length; for (int m = 0; m < k; m++) {
/*  677: 481 */      if ((localObject = arrayOfAh[m]).a() == Aj.a) break;
/*  678: 482 */      Object localObject = (String)((Ah)localObject).a();
/*  679:     */      
/*  682: 486 */      this.jdField_a_of_type_JavaUtilSet.add(localObject);
/*  683:     */    }
/*  684: 488 */    if ((paramAh.length > 2) && (paramAh[2].a() == Aj.d)) {
/*  685: 489 */      this.jdField_b_of_type_Int = ((Integer)paramAh[2].a()).intValue();
/*  686:     */    }
/*  687: 491 */    if ((paramAh.length > 3) && (paramAh[3].a() == Aj.n)) {
/*  688: 492 */      arrayOfAh = (Ah[])paramAh[3].a();
/*  689:     */      
/*  690: 494 */      for (k = 0; k < arrayOfAh.length - 1; k++) {
/*  691:     */        me localme;
/*  692: 496 */        (localme = new me()).fromTagStructure(arrayOfAh[k]);
/*  693: 497 */        localme.b(vg.e++);
/*  694: 498 */        if (localme.a() != 0) {
/*  695: 499 */          this.jdField_a_of_type_JavaUtilMap.put(Integer.valueOf(localme.b()), localme);
/*  696:     */        }
/*  697:     */      }
/*  698:     */    }
/*  699: 503 */    System.err.println("Read From Disk: " + this);
/*  700:     */  }
/*  701:     */  
/*  702:     */  public Ah toTagStructure()
/*  703:     */  {
/*  704:     */    ArrayList localArrayList;
/*  705: 509 */    Ah[] arrayOfAh1 = new Ah[(localArrayList = a()).size() + 1];
/*  706: 510 */    int k = 0;
/*  707: 511 */    for (int m = 0; m < localArrayList.size(); m++)
/*  708:     */    {
/*  710: 514 */      arrayOfAh1[k] = new Ah(Aj.i, null, ((mF)localArrayList.get(m)).getUniqueIdentifier());
/*  711: 515 */      k++;
/*  712:     */    }
/*  713:     */    
/*  715: 519 */    k = 0;
/*  716: 520 */    Ah[] arrayOfAh2 = new Ah[this.jdField_a_of_type_Mv.a().size() + 1];
/*  717: 521 */    for (me localme : this.jdField_a_of_type_Mv.a().values()) {
/*  718: 522 */      arrayOfAh2[k] = localme.toTagStructure();
/*  719: 523 */      k++;
/*  720:     */    }
/*  721: 525 */    arrayOfAh2[(arrayOfAh2.length - 1)] = new Ah(Aj.a, null, null);
/*  722: 526 */    arrayOfAh1[localArrayList.size()] = new Ah(Aj.a, null, null);
/*  723: 527 */    return new Ah(Aj.n, "SECTOR", new Ah[] { new Ah(Aj.k, "POS", this.jdField_a_of_type_Q), new Ah(Aj.n, "idents", arrayOfAh1), new Ah(Aj.d, "PROT", Integer.valueOf(this.jdField_b_of_type_Int)), new Ah(Aj.n, "items", arrayOfAh2), new Ah(Aj.a, null, null) });
/*  724:     */  }
/*  725:     */  
/*  733:     */  public final int a()
/*  734:     */  {
/*  735: 539 */    return this.jdField_c_of_type_Int;
/*  736:     */  }
/*  737:     */  
/*  740:     */  public final long a()
/*  741:     */  {
/*  742: 546 */    return this.jdField_a_of_type_Long;
/*  743:     */  }
/*  744:     */  
/*  745:     */  public final L a() {
/*  746: 550 */    return this.jdField_a_of_type_L;
/*  747:     */  }
/*  748:     */  
/*  760:     */  public final zQ a()
/*  761:     */  {
/*  762: 566 */    return this.jdField_a_of_type_ZQ;
/*  763:     */  }
/*  764:     */  
/*  765:     */  public String getUniqueIdentifier() {
/*  766: 570 */    return "SECTOR_" + this.jdField_a_of_type_Q.jdField_a_of_type_Int + "." + this.jdField_a_of_type_Q.jdField_b_of_type_Int + "." + this.jdField_a_of_type_Q.jdField_c_of_type_Int;
/*  767:     */  }
/*  768:     */  
/*  770:     */  public final void d()
/*  771:     */  {
/*  772: 576 */    this.jdField_a_of_type_Float = Math.max(this.jdField_a_of_type_Float, 0.0F);
/*  773:     */  }
/*  774:     */  
/*  782:     */  public final boolean a()
/*  783:     */  {
/*  784: 588 */    return this.jdField_b_of_type_Boolean;
/*  785:     */  }
/*  786:     */  
/*  787:     */  public boolean isVolatile() {
/*  788: 592 */    return false;
/*  789:     */  }
/*  790:     */  
/*  791:     */  public final void a(vg paramvg)
/*  792:     */  {
/*  793: 597 */    for (String str : this.jdField_a_of_type_JavaUtilSet) {
/*  794: 598 */      a(paramvg, str);
/*  795:     */    }
/*  796:     */  }
/*  797:     */  
/*  798:     */  public final void a(vg paramvg, String paramString)
/*  799:     */  {
/*  800: 604 */    synchronized (paramvg.getLocalAndRemoteObjectContainer().getLocalObjects()) {
/*  801: 605 */      int k = 0;
/*  802: 606 */      for (Iterator localIterator = paramvg.getLocalAndRemoteObjectContainer().getLocalObjects().values().iterator(); localIterator.hasNext();) { Sendable localSendable;
/*  803: 607 */        if ((((localSendable = (Sendable)localIterator.next()) instanceof Ak)) && (((Ak)localSendable).getUniqueIdentifier().equals(paramString))) {
/*  804: 608 */          System.err.println("[SECTOR] entity " + paramString + " is still active -> not loaded from disk again");
/*  805: 609 */          k = 1;
/*  806: 610 */          break;
/*  807:     */        }
/*  808:     */      }
/*  809: 613 */      if (k != 0) {
/*  810:     */        return;
/*  811:     */      }
/*  812:     */    }
/*  813:     */    Object localObject1;
/*  814: 618 */    if (paramString.startsWith("ENTITY_FLOATINGROCK_"))
/*  815:     */    {
/*  816: 620 */      ??? = paramString.substring(20);
/*  817:     */      
/*  820: 624 */      if ((localObject1 = paramvg.a().a((String)???, 0)).size() > 0) {
/*  821: 625 */        int m = 0;
/*  822: 626 */        for (int n = 0; n < ((List)localObject1).size(); n++) {
/*  823: 627 */          if ((((kw)((List)localObject1).get(n)).jdField_a_of_type_Int == 3) && (!((kw)((List)localObject1).get(n)).jdField_a_of_type_Boolean)) {
/*  824: 628 */            Universe.loadEntity(paramvg, (kw)((List)localObject1).get(n), this);
/*  825: 629 */            m = 1;
/*  826: 630 */            break;
/*  827:     */          }
/*  828:     */        }
/*  829:     */        
/*  830: 634 */        if (m != 0)
/*  831:     */        {
/*  832: 636 */          return;
/*  833:     */        }
/*  834:     */      }
/*  835:     */    }
/*  836:     */    
/*  837: 641 */    ??? = new File(vg.jdField_a_of_type_JavaLangString + paramString + ".ent");
/*  838:     */    
/*  839:     */    try
/*  840:     */    {
/*  841: 645 */      if ((localObject1 = ((File)???).getName().split("_")).length <= 0) {
/*  842: 646 */        return;
/*  843:     */      }
/*  844: 648 */      a(localObject1[1], (File)???); return;
/*  845:     */    }
/*  846:     */    catch (Exception localException) {
/*  847: 651 */      System.err.println("[SERVER][ERROR] Exception Loading Sector " + ((File)???).getName() + ";");
/*  848:     */      
/*  849: 653 */      localException.printStackTrace();
/*  850:     */    }
/*  851:     */  }
/*  852:     */  
/*  853:     */  private Sendable a(String paramString, File paramFile) {
/*  854: 658 */    return Universe.loadEntity(this.jdField_a_of_type_Vg, paramString, paramFile, this);
/*  855:     */  }
/*  856:     */  
/*  857: 661 */  public final void b() { this.jdField_a_of_type_Long = System.currentTimeMillis(); }
/*  858:     */  
/*  859:     */  public final void c() {
/*  860: 664 */    this.jdField_a_of_type_Long = (System.currentTimeMillis() - ((Integer)vo.g.a()).intValue() * 1000 + 1000L);
/*  861:     */  }
/*  862:     */  
/*  864:     */  public final mD a()
/*  865:     */  {
/*  866: 670 */    return this.jdField_a_of_type_Vg.a().getStellarSystemFromSecPos(this.jdField_a_of_type_Q).a(this.jdField_a_of_type_Q);
/*  867:     */  }
/*  868:     */  
/*  869:     */  public final mC a() {
/*  870: 674 */    return this.jdField_a_of_type_Vg.a().getStellarSystemFromSecPos(this.jdField_a_of_type_Q).a(this.jdField_a_of_type_Q);
/*  871:     */  }
/*  872:     */  
/*  873:     */  public final kk a() {
/*  874: 678 */    return this.jdField_a_of_type_Vg.a().getStellarSystemFromSecPos(this.jdField_a_of_type_Q).a(this.jdField_a_of_type_Q);
/*  875:     */  }
/*  876:     */  
/*  877:     */  public final void b(vg paramvg)
/*  878:     */  {
/*  879: 683 */    Object localObject = paramvg.a().getStellarSystemFromSecPos(this.jdField_a_of_type_Q);
/*  880:     */    
/*  881: 685 */    switch (mA.a[localObject.a(this.jdField_a_of_type_Q).ordinal()]) {
/*  882: 686 */    case 1:  localObject = paramvg;paramvg = this;int k = Universe.getRandom().nextInt(5) + 2; if (Math.random() < 0.08D) { kf localkf; (localkf = new kf((StateInterface)localObject)).setSeed(Universe.getRandom().nextInt());localkf.setUniqueIdentifier("ENTITY_SHOP_" + System.currentTimeMillis());localkf.getMinPos().b(new q(0, -6, 0));localkf.getMaxPos().b(new q(0, 6, 0));localkf.setId(((vg)localObject).getNextFreeObjectId());localkf.setSectorId(paramvg.jdField_c_of_type_Int);localkf.initialize();localkf.a(false);localkf.getInitialTransform().setIdentity();localkf.getInitialTransform().origin.set(500.0F, Universe.getRandom().nextInt(250), 500.0F);((vg)localObject).a().a().addImmediateSynchronizedObject(localkf);paramvg.jdField_a_of_type_JavaUtilArrayList.add(localkf); } for (int m = 0; m < k; m++) paramvg.a((vg)localObject, new q(-jdField_a_of_type_Int - Universe.getRandom().nextInt(4), -jdField_a_of_type_Int - Universe.getRandom().nextInt(4), -jdField_a_of_type_Int - Universe.getRandom().nextInt(4)), new q(jdField_a_of_type_Int + Universe.getRandom().nextInt(4), jdField_a_of_type_Int + Universe.getRandom().nextInt(4), jdField_a_of_type_Int + Universe.getRandom().nextInt(4))); return;
/*  883: 687 */    case 2:  kk localkk = ((mI)localObject).a(this.jdField_a_of_type_Q);localObject = paramvg;paramvg = this; switch (mA.b[localkk.ordinal()]) {case 1:  return; case 2:  paramvg.e((vg)localObject);return; case 3:  paramvg.f((vg)localObject); } return;
/*  884: 688 */    case 3:  a(paramvg, ((mI)localObject).a(this.jdField_a_of_type_Q));return;
/*  885: 689 */    case 4:  d(paramvg);return;
/*  886: 690 */    case 5:  return;
/*  887: 691 */    case 6:  return;
/*  888: 692 */    case 7:  return; }
/*  889: 693 */    if (!jdField_h_of_type_Boolean) { throw new AssertionError("unknown sector type " + ((mI)localObject).a(this.jdField_a_of_type_Q));
/*  890:     */    }
/*  891:     */  }
/*  892:     */  
/*  907:     */  private void d(vg paramvg)
/*  908:     */  {
/*  909:     */    kf localkf;
/*  910:     */    
/*  923: 727 */    (localkf = new kf(paramvg)).setSeed(Universe.getRandom().nextInt());
/*  924:     */    
/*  925: 729 */    localkf.setUniqueIdentifier("ENTITY_SHOP_" + System.currentTimeMillis());
/*  926: 730 */    localkf.getMinPos().b(new q(0, -10, 0));
/*  927: 731 */    localkf.getMaxPos().b(new q(0, 10, 0));
/*  928: 732 */    localkf.setId(paramvg.getNextFreeObjectId());
/*  929: 733 */    localkf.setSectorId(this.jdField_c_of_type_Int);
/*  930: 734 */    localkf.initialize();
/*  931: 735 */    localkf.a(false);
/*  932: 736 */    localkf.getInitialTransform().setIdentity();
/*  933: 737 */    localkf.getInitialTransform().origin.set(0.0F, 0.0F, 0.0F);
/*  934: 738 */    paramvg.a().a().addImmediateSynchronizedObject(localkf);
/*  935: 739 */    this.jdField_a_of_type_JavaUtilArrayList.add(localkf);
/*  936:     */    
/*  938: 742 */    int k = 1 + Universe.getRandom().nextInt(3);
/*  939:     */    
/*  940: 744 */    for (int m = 0; m < k; m++) {
/*  941: 745 */      a(paramvg, new q(-jdField_a_of_type_Int - Universe.getRandom().nextInt(3), -jdField_a_of_type_Int - Universe.getRandom().nextInt(3), -jdField_a_of_type_Int - Universe.getRandom().nextInt(3)), new q(jdField_a_of_type_Int + Universe.getRandom().nextInt(3), jdField_a_of_type_Int + Universe.getRandom().nextInt(3), jdField_a_of_type_Int + Universe.getRandom().nextInt(3)));
/*  942:     */    }
/*  943:     */  }
/*  944:     */  
/*  947:     */  private void a(vg paramvg, mC parammC)
/*  948:     */  {
/*  949: 753 */    q localq = new q();
/*  950: 754 */    System.err.println("populating sector " + localq);
/*  951:     */    
/*  954:     */    jA localjA;
/*  955:     */    
/*  958: 762 */    (localjA = new jA(paramvg)).setCreatorId(parammC.ordinal());
/*  959: 763 */    localjA.setSeed(Universe.getRandom().nextInt());
/*  960:     */    
/*  961: 765 */    localjA.setUniqueIdentifier("ENTITY_PLANET_" + this.jdField_a_of_type_Q.jdField_a_of_type_Int + "_" + this.jdField_a_of_type_Q.jdField_b_of_type_Int + "_" + this.jdField_a_of_type_Q.jdField_c_of_type_Int + "_" + System.currentTimeMillis());
/*  962: 766 */    localjA.getMinPos().b(new q(-16, 0, -16));
/*  963: 767 */    localjA.getMaxPos().b(new q(16, 4, 16));
/*  964: 768 */    localjA.setId(paramvg.getNextFreeObjectId());
/*  965: 769 */    localjA.setSectorId(this.jdField_c_of_type_Int);
/*  966: 770 */    localjA.initialize();
/*  967:     */    
/*  968: 772 */    while (a(localjA, localq) != null) {
/*  969: 773 */      localq.jdField_b_of_type_Int += 16;
/*  970:     */    }
/*  971: 775 */    paramvg.a().a().addImmediateSynchronizedObject(localjA);
/*  972: 776 */    this.jdField_a_of_type_JavaUtilArrayList.add(localjA);
/*  973:     */  }
/*  974:     */  
/* 1021:     */  private void e(vg paramvg)
/* 1022:     */  {
/* 1023:     */    ki localki;
/* 1024:     */    
/* 1069: 873 */    (localki = new ki(paramvg)).setSeed(Universe.getRandom().nextLong());
/* 1070: 874 */    localki.setUniqueIdentifier("ENTITY_SPACESTATION_" + System.currentTimeMillis());
/* 1071:     */    
/* 1072: 876 */    localki.setRealName("Station " + this.jdField_c_of_type_Int);
/* 1073:     */    
/* 1074: 878 */    localki.getMinPos().b(new q(-3, -3, -3));
/* 1075:     */    
/* 1076: 880 */    localki.getMaxPos().b(new q(3, 3, 3));
/* 1077:     */    
/* 1078: 882 */    localki.setCreatorId(kk.a.ordinal());
/* 1079:     */    
/* 1080: 884 */    localki.setId(paramvg.getNextFreeObjectId());
/* 1081:     */    
/* 1082: 886 */    localki.setSectorId(this.jdField_c_of_type_Int);
/* 1083: 887 */    localki.initialize();
/* 1084: 888 */    localki.getInitialTransform().setIdentity();
/* 1085: 889 */    paramvg.a().a().addImmediateSynchronizedObject(localki);
/* 1086: 890 */    this.jdField_a_of_type_JavaUtilArrayList.add(localki);
/* 1087:     */    
/* 1090: 894 */    int k = Universe.getRandom().nextInt(4) + 2;
/* 1091:     */    
/* 1092: 896 */    for (int m = 0; m < k; m++) {
/* 1093: 897 */      a(paramvg, new q(-jdField_a_of_type_Int - Universe.getRandom().nextInt(3), -jdField_a_of_type_Int - Universe.getRandom().nextInt(3), -jdField_a_of_type_Int - Universe.getRandom().nextInt(3)), new q(jdField_a_of_type_Int + Universe.getRandom().nextInt(3), jdField_a_of_type_Int + Universe.getRandom().nextInt(3), jdField_a_of_type_Int + Universe.getRandom().nextInt(3)));
/* 1094:     */    }
/* 1095:     */  }
/* 1096:     */  
/* 1098:     */  private void f(vg paramvg)
/* 1099:     */  {
/* 1100:     */    ki localki;
/* 1101:     */    
/* 1102: 906 */    (localki = new ki(paramvg)).setSeed(Universe.getRandom().nextLong());
/* 1103: 907 */    localki.setUniqueIdentifier("ENTITY_SPACESTATION_P" + System.currentTimeMillis());
/* 1104:     */    
/* 1105: 909 */    localki.setRealName("Station " + this.jdField_c_of_type_Int);
/* 1106:     */    
/* 1107: 911 */    localki.getMinPos().b(new q(-3, -3, -3));
/* 1108:     */    
/* 1109: 913 */    localki.getMaxPos().b(new q(3, 3, 3));
/* 1110:     */    
/* 1111: 915 */    localki.setCreatorId(kk.c.ordinal());
/* 1112:     */    
/* 1113: 917 */    localki.setFactionId(-1);
/* 1114:     */    
/* 1115: 919 */    localki.setId(paramvg.getNextFreeObjectId());
/* 1116: 920 */    localki.setSectorId(this.jdField_c_of_type_Int);
/* 1117: 921 */    localki.initialize();
/* 1118: 922 */    localki.getInitialTransform().setIdentity();
/* 1119: 923 */    paramvg.a().a().addImmediateSynchronizedObject(localki);
/* 1120: 924 */    this.jdField_a_of_type_JavaUtilArrayList.add(localki);
/* 1121:     */    
/* 1124: 928 */    int k = Universe.getRandom().nextInt(4) + 2;
/* 1125:     */    
/* 1126: 930 */    for (int m = 0; m < k; m++) {
/* 1127: 931 */      a(paramvg, new q(-jdField_a_of_type_Int - Universe.getRandom().nextInt(3), -jdField_a_of_type_Int - Universe.getRandom().nextInt(3), -jdField_a_of_type_Int - Universe.getRandom().nextInt(3)), new q(jdField_a_of_type_Int + Universe.getRandom().nextInt(3), jdField_a_of_type_Int + Universe.getRandom().nextInt(3), jdField_a_of_type_Int + Universe.getRandom().nextInt(3)));
/* 1128:     */    }
/* 1129:     */  }
/* 1130:     */  
/* 1142:     */  public final void a(boolean paramBoolean)
/* 1143:     */  {
/* 1144: 948 */    if ((!this.jdField_b_of_type_Boolean) && (paramBoolean)) {
/* 1145: 949 */      this.jdField_c_of_type_Boolean = false;
/* 1146: 950 */      this.jdField_d_of_type_Boolean = true;
/* 1147: 951 */      this.jdField_a_of_type_Long = System.currentTimeMillis();
/* 1148: 952 */      mx localmx = this; if (jdField_b_of_type_JavaUtilArrayList.isEmpty()) { localmx.jdField_a_of_type_L = new L(localmx.jdField_a_of_type_Vg, localmx.jdField_c_of_type_Int); } else { L localL; (localL = (L)jdField_b_of_type_JavaUtilArrayList.remove(0)).a(localmx.jdField_c_of_type_Int);localmx.jdField_a_of_type_L = localL; }
/* 1149: 953 */      synchronized (this.jdField_a_of_type_JavaLangObject) {
/* 1150: 954 */        this.jdField_b_of_type_Boolean = paramBoolean;
/* 1151: 955 */        return;
/* 1152:     */      } }
/* 1153: 957 */    if ((this.jdField_b_of_type_Boolean) && (!paramBoolean)) {
/* 1154: 958 */      ??? = this; if ((!jdField_h_of_type_Boolean) && (((mx)???).jdField_a_of_type_L == null)) throw new AssertionError(); jdField_b_of_type_JavaUtilArrayList.add(((mx)???).jdField_a_of_type_L);((mx)???).jdField_a_of_type_L = null; if ((!jdField_h_of_type_Boolean) && (jdField_b_of_type_JavaUtilArrayList.get(jdField_b_of_type_JavaUtilArrayList.size() - 1) == null)) throw new AssertionError();
/* 1155: 959 */      this.jdField_b_of_type_Long = System.currentTimeMillis();
/* 1156:     */    }
/* 1157: 961 */    this.jdField_b_of_type_Boolean = paramBoolean;
/* 1158:     */  }
/* 1159:     */  
/* 1168:     */  public final void a(int paramInt)
/* 1169:     */  {
/* 1170: 974 */    this.jdField_c_of_type_Int = paramInt;
/* 1171:     */  }
/* 1172:     */  
/* 1173:     */  private static void a(q paramq) {
/* 1174: 978 */    paramq.b(0, 0, 0);
/* 1175: 979 */    while (paramq.a() < 25.0F)
/* 1176:     */    {
/* 1177: 981 */      int k = Universe.getRandom().nextInt(500) - 250;
/* 1178: 982 */      int m = Universe.getRandom().nextInt(250) - 125;
/* 1179: 983 */      int n = Universe.getRandom().nextInt(500) - 250;
/* 1180: 984 */      paramq.b(k, m, n);
/* 1181:     */    }
/* 1182:     */  }
/* 1183:     */  
/* 1203:     */  public String toString()
/* 1204:     */  {
/* 1205:1009 */    return "Sector[" + this.jdField_c_of_type_Int + "]" + this.jdField_a_of_type_Q;
/* 1206:     */  }
/* 1207:     */  
/* 1209:1013 */  public final String c() { return "Sector[" + this.jdField_c_of_type_Int + "]" + this.jdField_a_of_type_Q + "; @" + this.jdField_a_of_type_ZQ; }
/* 1210:     */  
/* 1211:     */  public final void a(xq paramxq) {
/* 1212:1016 */    if ((!jdField_h_of_type_Boolean) && (this.jdField_a_of_type_Boolean)) throw new AssertionError();
/* 1213:1017 */    vg.g += 1;
/* 1214:1018 */    if (this.jdField_g_of_type_Boolean) {
/* 1215:1019 */      i();
/* 1216:1020 */      this.jdField_g_of_type_Boolean = false;
/* 1217:     */    }
/* 1218:     */    
/* 1219:1023 */    if (this.jdField_b_of_type_Boolean) {
/* 1220:1024 */      vg.h += 1;
/* 1221:1025 */      if ((!jdField_h_of_type_Boolean) && (this.jdField_a_of_type_L == null)) { throw new AssertionError();
/* 1222:     */      }
/* 1223:1027 */      this.jdField_a_of_type_L.a(paramxq);
/* 1224:     */      
/* 1225:1029 */      if (this.jdField_e_of_type_Boolean) {
/* 1226:1030 */        mx localmx = this;mD localmD = this.jdField_a_of_type_Vg.a().getStellarSystemFromSecPos(localmx.jdField_a_of_type_Q).a(localmx.jdField_a_of_type_Q); if ((localmx.jdField_a_of_type_Q.equals(jdField_b_of_type_Q)) && (vo.a.a())) localmx.a(2, true); if (((localmx.jdField_b_of_type_Int & 0x1) == 1 ? 1 : 0) == 0) { if (vo.d.a()) {} } else { System.err.println("[SECTOR] NEW SECTOR IS PROTECTED FROM SPAWNING ANY ENEMIES"); break label251; } if ((localmD == mD.b) && (Universe.getRandom().nextInt(100) == 0)) try { localmx.jdField_a_of_type_Vg.a().a(Universe.getRandom().nextInt(8) + 3, 1, 3, tH.a, localmx.jdField_c_of_type_Int); } catch (EntityNotFountException localEntityNotFountException) { localEntityNotFountException; } catch (ErrorDialogException localErrorDialogException) { localErrorDialogException; } catch (EntityAlreadyExistsException localEntityAlreadyExistsException) { localEntityAlreadyExistsException; }
/* 1227:1031 */        label251: this.jdField_e_of_type_Boolean = false;
/* 1228:     */      }
/* 1229:1033 */      this.jdField_a_of_type_ZQ.update(paramxq, this.jdField_a_of_type_Float);
/* 1230:1034 */      this.jdField_c_of_type_Boolean = false;
/* 1231:1035 */      this.jdField_a_of_type_Float = 0.0F;
/* 1232:     */      
/* 1233:1037 */      if ((this.jdField_f_of_type_Boolean) && (this.jdField_a_of_type_MI.a == mD.e)) {
/* 1234:1038 */        b(paramxq);
/* 1235:     */      }
/* 1236:     */      
/* 1238:     */    }
/* 1239:     */    else
/* 1240:     */    {
/* 1241:1045 */      if (this.jdField_d_of_type_Boolean) {
/* 1242:1046 */        long l1 = System.currentTimeMillis();
/* 1243:1047 */        a(2, this.jdField_a_of_type_Vg.a());
/* 1244:     */        long l2;
/* 1245:1049 */        if ((l2 = System.currentTimeMillis() - l1) > 4L) {
/* 1246:1050 */          System.err.println("[SERVER][SECTOR] WRITING SECTOR ID " + this.jdField_c_of_type_Int + " -> " + this.jdField_a_of_type_Q + " TOOK " + l2 + "ms");
/* 1247:     */        }
/* 1248:     */      }
/* 1249:1053 */      this.jdField_d_of_type_Boolean = false;
/* 1250:     */    }
/* 1251:     */  }
/* 1252:     */  
/* 1256:     */  private void i()
/* 1257:     */  {
/* 1258:     */    try
/* 1259:     */    {
/* 1260:1064 */      localIterator = null; for (kw localkw : this.jdField_a_of_type_Vg.a().a(this.jdField_a_of_type_Q, 0))
/* 1261:     */      {
/* 1263:     */        try
/* 1264:     */        {
/* 1266:1067 */          if (!this.jdField_a_of_type_Vg.c().containsKey(localkw.jdField_a_of_type_JavaLangString.trim())) {
/* 1267:1068 */            String str = localkw.jdField_a_of_type_JavaLangString.split("_", 3)[1];
/* 1268:1069 */            System.err.println("[REPAIR] FOUND SECTOR ENTITY: " + localkw.jdField_a_of_type_JavaLangString + " [" + str + "]");
/* 1269:1070 */            a(str, new File(localkw.jdField_a_of_type_JavaLangString + ".ent"));
/* 1270:     */          }
/* 1271:1072 */        } catch (IOException localIOException) { 
/* 1272:     */          
/* 1277:1078 */            localIOException;
/* 1278:     */        } catch (EntityNotFountException localEntityNotFountException) {
/* 1279:1074 */          
/* 1280:     */          
/* 1283:1078 */            localEntityNotFountException;
/* 1284:     */        } catch (ErrorDialogException localErrorDialogException) {
/* 1285:1076 */          
/* 1286:     */          
/* 1287:1078 */            localErrorDialogException;
/* 1288:     */        }
/* 1289:     */      }
/* 1290:     */    }
/* 1291:     */    catch (SQLException localSQLException) {
/* 1292:1081 */      Iterator localIterator = null;
/* 1293:     */      
/* 1294:1083 */      localSQLException.printStackTrace();
/* 1295:     */    }
/* 1296:     */    
/* 1299:1086 */    new File(vg.jdField_a_of_type_JavaLangString).listFiles();
/* 1300:     */  }
/* 1301:     */  
/* 1361:1148 */  private float jdField_b_of_type_Float = 0.0F;
/* 1362:1149 */  private Transform jdField_a_of_type_ComBulletphysicsLinearmathTransform = new Transform();
/* 1363:1150 */  private Vector3f jdField_g_of_type_JavaxVecmathVector3f = new Vector3f();
/* 1364:1151 */  private Vector3f jdField_h_of_type_JavaxVecmathVector3f = new Vector3f();
/* 1365:1152 */  private Vector3f i = new Vector3f();
/* 1366:1153 */  private Vector3f j = new Vector3f();
/* 1367:     */  
/* 1368:1155 */  private ArrayList jdField_c_of_type_JavaUtilArrayList = new ArrayList();
/* 1369:     */  private boolean jdField_g_of_type_Boolean;
/* 1370:1157 */  private long jdField_c_of_type_Long = -1L;
/* 1371:     */  public boolean a;
/* 1372:     */  private long jdField_d_of_type_Long;
/* 1373:     */  
/* 1374:1161 */  private void b(xq arg1) { this.jdField_b_of_type_Float += ???.a();
/* 1375:1162 */    ??? = mI.b(this.jdField_a_of_type_Q) ? 0.005F : 0.5F;
/* 1376:     */    
/* 1378:1165 */    float f1 = mI.b(this.jdField_a_of_type_Q) ? 666.0F : 80.0F;
/* 1379:     */    
/* 1383:1170 */    if (this.jdField_b_of_type_Float > ???)
/* 1384:     */    {
/* 1387:1174 */      Universe.calcSunPosInnerStarSystem(this.jdField_a_of_type_Q, this.jdField_a_of_type_MI, this.jdField_a_of_type_Vg.a().calculateStartTime(), this.jdField_a_of_type_ComBulletphysicsLinearmathTransform);
/* 1388:     */      
/* 1389:1176 */      this.jdField_c_of_type_JavaUtilArrayList.clear();
/* 1390:1177 */      Iterator localIterator; synchronized (this.jdField_a_of_type_Vg.getLocalAndRemoteObjectContainer().getLocalObjects()) {
/* 1391:1178 */        for (localIterator = this.jdField_a_of_type_Vg.getLocalAndRemoteObjectContainer().getLocalUpdatableObjects().values().iterator(); localIterator.hasNext();) { Sendable localSendable;
/* 1392:1179 */          if ((((localSendable = (Sendable)localIterator.next()) instanceof kd)) && (((SegmentController)localSendable).getSectorId() == this.jdField_c_of_type_Int)) {
/* 1393:1180 */            this.jdField_c_of_type_JavaUtilArrayList.add((EditableSendableSegmentController)localSendable);
/* 1394:     */          }
/* 1395:     */        }
/* 1396:     */      }
/* 1397:1184 */      if (this.jdField_c_of_type_JavaUtilArrayList.isEmpty()) {
/* 1398:1185 */        return;
/* 1399:     */      }
/* 1400:1187 */      ??? = (EditableSendableSegmentController)this.jdField_c_of_type_JavaUtilArrayList.get(Universe.getRandom().nextInt(this.jdField_c_of_type_JavaUtilArrayList.size()));
/* 1401:     */      
/* 1404:1191 */      this.jdField_h_of_type_JavaxVecmathVector3f.set(???.getWorldTransform().origin);
/* 1405:     */      
/* 1406:1193 */      float f2 = ???.getSegmentBuffer().a().a() / 3.0F;
/* 1407:     */      
/* 1409:1196 */      this.jdField_g_of_type_JavaxVecmathVector3f.sub(this.jdField_h_of_type_JavaxVecmathVector3f, this.jdField_a_of_type_ComBulletphysicsLinearmathTransform.origin);
/* 1410:1197 */      float f3 = this.jdField_g_of_type_JavaxVecmathVector3f.length();
/* 1411:1198 */      this.jdField_g_of_type_JavaxVecmathVector3f.normalize();
/* 1412:1199 */      this.i.set(0.0F, 1.0F, 0.0F);
/* 1413:     */      
/* 1414:1201 */      this.j.cross(this.jdField_g_of_type_JavaxVecmathVector3f, this.i);
/* 1415:1202 */      this.j.normalize();
/* 1416:1203 */      this.i.cross(this.j, this.jdField_g_of_type_JavaxVecmathVector3f);
/* 1417:1204 */      this.i.normalize();
/* 1418:     */      
/* 1419:1206 */      this.jdField_g_of_type_JavaxVecmathVector3f.scale(f3 + f2);
/* 1420:     */      
/* 1421:1208 */      this.jdField_h_of_type_JavaxVecmathVector3f.add(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform.origin, this.jdField_g_of_type_JavaxVecmathVector3f);
/* 1422:     */      
/* 1429:1216 */      this.i.scale(f2 * ((Universe.getRandom().nextFloat() - 0.5F) * 2.0F));
/* 1430:1217 */      this.j.scale(f2 * ((Universe.getRandom().nextFloat() - 0.5F) * 2.0F));
/* 1431:1218 */      this.jdField_h_of_type_JavaxVecmathVector3f.add(this.i);
/* 1432:1219 */      this.jdField_h_of_type_JavaxVecmathVector3f.add(this.j);
/* 1433:     */      Object localObject2;
/* 1434:1221 */      if (xu.F.b()) {
/* 1435:1222 */        localObject2 = new ym(new Vector3f(this.jdField_h_of_type_JavaxVecmathVector3f), new Vector4f(1.0F, 1.0F, 0.0F, 1.0F));
/* 1436:1223 */        yk.b.add(localObject2);
/* 1437:     */        
/* 1438:1225 */        localObject2 = new xQ(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform.origin, this.jdField_h_of_type_JavaxVecmathVector3f);
/* 1439:1226 */        yk.c.add(localObject2);
/* 1440:     */      }
/* 1441:     */      
/* 1444:1231 */      if (((localObject2 = ((PhysicsExt)this.jdField_a_of_type_ZQ).testRayCollisionPoint(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform.origin, this.jdField_h_of_type_JavaxVecmathVector3f, false, null, null, true, null, false)) != null) && (((CollisionWorld.ClosestRayResultCallback)localObject2).hasHit()) && ((localObject2 instanceof CubeRayCastResult)) && 
/* 1445:1232 */        (((CubeRayCastResult)localObject2).getSegment() != null) && 
/* 1446:1233 */        (((CubeRayCastResult)localObject2).getSegment().a() == ???)) {
/* 1447:1234 */        ???.handleHit((CollisionWorld.ClosestRayResultCallback)localObject2, null, f1);
/* 1448:1235 */        if (((??? instanceof cw)) && (System.currentTimeMillis() - this.jdField_d_of_type_Long > 3000L))
/* 1449:     */        {
/* 1450:1237 */          for (localObject2 = ((cw)???).a().iterator(); ((Iterator)localObject2).hasNext();) {
/* 1451:1238 */            (??? = (lE)((Iterator)localObject2).next()).a(new ServerMessage("WARNING!\nthe heat of the sun is\ndamaging the hulls!\nHide behind natural objects!\nINTESITY: " + f1, 3, ???.getId()));
/* 1452:     */          }
/* 1453:     */          
/* 1454:1241 */          this.jdField_d_of_type_Long = System.currentTimeMillis();
/* 1455:     */        }
/* 1456:     */      }
/* 1457:     */      
/* 1462:1249 */      this.jdField_b_of_type_Float = 0.0F;
/* 1463:     */    }
/* 1464:     */  }
/* 1465:     */  
/* 1486:     */  public final ArrayList a()
/* 1487:     */  {
/* 1488:     */    ArrayList localArrayList;
/* 1489:     */    
/* 1509:     */    for (;;)
/* 1510:     */    {
/* 1511:1298 */      localArrayList = new ArrayList();
/* 1512:     */      
/* 1513:     */      try
/* 1514:     */      {
/* 1515:1302 */        for (localIterator = this.jdField_a_of_type_Vg.getLocalAndRemoteObjectContainer().getLocalObjects().values().iterator(); localIterator.hasNext();) { Object localObject;
/* 1516:1303 */          if ((((localObject = (Sendable)localIterator.next()) instanceof mF)) && (((mF)localObject).getSectorId() == this.jdField_c_of_type_Int))
/* 1517:     */          {
/* 1519:1306 */            (localObject = (mF)localObject).getWorldTransform();
/* 1520:1307 */            localArrayList.add(localObject);
/* 1521:     */          }
/* 1522:     */        } } catch (ConcurrentModificationException localConcurrentModificationException) { Iterator localIterator;
/* 1523:1310 */        
/* 1524:     */        
/* 1527:1314 */          localConcurrentModificationException.printStackTrace();System.err.println("CATCHED EXCEPTION!!!!!!!!!!!!!!!!!! (sector entity calc)"); } }
/* 1528:1315 */    return localArrayList;
/* 1529:     */  }
/* 1530:     */  
/* 1565:     */  public final void a(int paramInt, Universe paramUniverse)
/* 1566:     */  {
/* 1567:1354 */    ArrayList localArrayList = a();
/* 1568:     */    
/* 1576:1363 */    vg localvg = this.jdField_a_of_type_Vg;
/* 1577:1364 */    paramInt = new mz(this, localvg, localArrayList, paramInt, paramUniverse);
/* 1578:     */    
/* 1613:     */    try
/* 1614:     */    {
/* 1615:1402 */      localvg.getThreadQueue().enqueue(paramInt); return;
/* 1616:1403 */    } catch (RejectedExecutionException localRejectedExecutionException) { 
/* 1617:     */      
/* 1619:1406 */        localRejectedExecutionException.printStackTrace();System.err.println(localvg.getThreadPool().getActiveCount() + "/" + localvg.getThreadPool().getMaximumPoolSize());
/* 1620:     */    }
/* 1621:     */  }
/* 1622:     */  
/* 1623:     */  public final int b()
/* 1624:     */  {
/* 1625:1412 */    return this.jdField_b_of_type_Int;
/* 1626:     */  }
/* 1627:     */  
/* 1629:     */  public final void b(int paramInt)
/* 1630:     */  {
/* 1631:1418 */    this.jdField_b_of_type_Int = paramInt;
/* 1632:     */  }
/* 1633:     */  
/* 1634:1421 */  public final void e() { this.jdField_e_of_type_Boolean = true; }
/* 1635:     */  
/* 1636:     */  public final void b(boolean paramBoolean) {
/* 1637:1424 */    a(2, paramBoolean);
/* 1638:     */  }
/* 1639:     */  
/* 1640:1427 */  public final void c(boolean paramBoolean) { a(1, paramBoolean); }
/* 1641:     */  
/* 1642:     */  private void a(int paramInt, boolean paramBoolean) {
/* 1643:1430 */    if (paramBoolean) {
/* 1644:1431 */      if ((this.jdField_b_of_type_Int & paramInt) != paramInt) {
/* 1645:1432 */        this.jdField_b_of_type_Int += paramInt;
/* 1646:     */      }
/* 1647:     */    }
/* 1648:1435 */    else if ((this.jdField_b_of_type_Int & paramInt) == paramInt) {
/* 1649:1436 */      this.jdField_b_of_type_Int -= paramInt;
/* 1650:     */    }
/* 1651:     */  }
/* 1652:     */  
/* 1653:     */  public final boolean b() {
/* 1654:1441 */    return (this.jdField_b_of_type_Int & 0x2) == 2;
/* 1655:     */  }
/* 1656:     */  
/* 1658:     */  public final void f()
/* 1659:     */  {
/* 1660:1447 */    if ((!jdField_h_of_type_Boolean) && (this.jdField_a_of_type_Mv != null)) throw new AssertionError();
/* 1661:1448 */    this.jdField_a_of_type_Mv = new mv(this.jdField_a_of_type_Vg);
/* 1662:1449 */    this.jdField_a_of_type_Mv.a(this);
/* 1663:1450 */    this.jdField_a_of_type_Mv.a(this.jdField_a_of_type_JavaUtilMap);
/* 1664:1451 */    this.jdField_a_of_type_Vg.a().a().addNewSynchronizedObjectQueued(this.jdField_a_of_type_Mv);
/* 1665:     */    q localq;
/* 1666:1453 */    int m = ByteUtil.d((localq = this.jdField_a_of_type_Q).jdField_a_of_type_Int) - 8;int n = ByteUtil.d(localq.jdField_b_of_type_Int) - 8;int k = ByteUtil.d(localq.jdField_c_of_type_Int) - 8;this.jdField_f_of_type_Boolean = (p.a(m, n, k) < 1.42F);
/* 1667:1454 */    mI localmI = this.jdField_a_of_type_Vg.a().getStellarSystemFromSecPos(this.jdField_a_of_type_Q);
/* 1668:1455 */    this.jdField_a_of_type_MI = localmI;
/* 1669:1456 */    localmI.a(this.jdField_a_of_type_Q);
/* 1670:     */  }
/* 1671:     */  
/* 1674:     */  public final mv a()
/* 1675:     */  {
/* 1676:1463 */    return this.jdField_a_of_type_Mv;
/* 1677:     */  }
/* 1678:     */  
/* 1683:     */  public final Map a()
/* 1684:     */  {
/* 1685:1472 */    return this.jdField_a_of_type_Mv.a();
/* 1686:     */  }
/* 1687:     */  
/* 1691:1478 */  public final boolean c() { return this.jdField_c_of_type_Boolean; }
/* 1692:     */  
/* 1693:     */  public final boolean a(long paramLong) {
/* 1694:     */    int k;
/* 1695:1482 */    return ((k = ((Integer)vo.h.a()).intValue()) >= 0) && (paramLong > this.jdField_b_of_type_Long + k * 1000);
/* 1696:     */  }
/* 1697:     */  
/* 1698:     */  public static boolean a(q paramq1, q paramq2) {
/* 1699:1486 */    (paramq2 = new q(paramq2)).c(paramq1);
/* 1700:1487 */    return paramq2.a() < 1.42F;
/* 1701:     */  }
/* 1702:     */  
/* 1704:     */  public final void g()
/* 1705:     */  {
/* 1706:1493 */    this.jdField_a_of_type_ZQ = null;
/* 1707:     */  }
/* 1708:     */  
/* 1709:     */  public final String b() {
/* 1710:1497 */    return "[PHYSICS][SERVER] WARNING: PHYSICS SYNC IN DANGER. SECTOR: " + this.jdField_a_of_type_Q + " [" + this.jdField_c_of_type_Int + "]";
/* 1711:     */  }
/* 1712:     */  
/* 1713:     */  public final void h() {
/* 1714:1501 */    this.jdField_g_of_type_Boolean = true;
/* 1715:     */  }
/* 1716:     */  
/* 1719:     */  public final long b()
/* 1720:     */  {
/* 1721:1508 */    return this.jdField_c_of_type_Long;
/* 1722:     */  }
/* 1723:     */  
/* 1725:     */  public final void a(long paramLong)
/* 1726:     */  {
/* 1727:1514 */    this.jdField_c_of_type_Long = paramLong;
/* 1728:     */  }
/* 1729:     */  
/* 1730:     */  public static byte[] a(Map paramMap) {
/* 1731:1518 */    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream(paramMap.size() * 18);
/* 1732:1519 */    DataOutputStream localDataOutputStream = new DataOutputStream(localByteArrayOutputStream);
/* 1733:1520 */    for (Map.Entry localEntry : paramMap.entrySet()) {
/* 1734:1521 */      localDataOutputStream.writeShort(((me)localEntry.getValue()).a());
/* 1735:1522 */      localDataOutputStream.writeInt(((me)localEntry.getValue()).a());
/* 1736:1523 */      localDataOutputStream.writeFloat(((me)localEntry.getValue()).a().x);
/* 1737:1524 */      localDataOutputStream.writeFloat(((me)localEntry.getValue()).a().y);
/* 1738:1525 */      localDataOutputStream.writeFloat(((me)localEntry.getValue()).a().z);
/* 1739:     */    }
/* 1740:1527 */    localByteArrayOutputStream.flush();
/* 1741:1528 */    return localByteArrayOutputStream.toByteArray();
/* 1742:     */  }
/* 1743:     */  
/* 1765:     */  public final void a(byte[] paramArrayOfByte)
/* 1766:     */  {
/* 1767:1554 */    DataInputStream localDataInputStream = new DataInputStream(new ByteArrayInputStream(paramArrayOfByte));
/* 1768:1555 */    paramArrayOfByte = paramArrayOfByte.length / 18;
/* 1769:1556 */    System.err.println("[SERVER][SECTOR] READING " + paramArrayOfByte + " ITEMS");
/* 1770:1557 */    for (byte[] arrayOfByte = 0; arrayOfByte < paramArrayOfByte; arrayOfByte++) {
/* 1771:     */      me localme;
/* 1772:1559 */      (localme = new me()).b(vg.b());
/* 1773:1560 */      localme.a(localDataInputStream.readShort());
/* 1774:1561 */      localme.a(localDataInputStream.readInt());
/* 1775:1562 */      localme.a(new Vector3f(localDataInputStream.readFloat(), localDataInputStream.readFloat(), localDataInputStream.readFloat()));
/* 1776:     */      
/* 1777:1564 */      if (localme.a() != 0) {
/* 1778:1565 */        System.err.println("[SERVER][SECTOR] LOADED ITEM " + localme.a() + ": " + localme.a() + " at " + localme.a() + " with ID: " + localme.b());
/* 1779:1566 */        this.jdField_a_of_type_JavaUtilMap.put(Integer.valueOf(localme.b()), localme);
/* 1780:     */      }
/* 1781:     */    }
/* 1782:     */  }
/* 1783:     */  
/* 1784:1571 */  public final void c(vg paramvg) { this.jdField_a_of_type_JavaUtilSet = paramvg.a().a(this.jdField_a_of_type_Q); }
/* 1785:     */  
/* 1789:     */  public boolean equals(Object paramObject)
/* 1790:     */  {
/* 1791:1578 */    return this.jdField_c_of_type_Int == ((mx)paramObject).jdField_c_of_type_Int;
/* 1792:     */  }
/* 1793:     */  
/* 1796:     */  public int hashCode()
/* 1797:     */  {
/* 1798:1585 */    return this.jdField_c_of_type_Int;
/* 1799:     */  }
/* 1800:     */  
/* 1801:     */  public final float b() {
/* 1802:1589 */    if (this.jdField_a_of_type_Vg.a() != null) {
/* 1803:1590 */      return this.jdField_a_of_type_Vg.a().b();
/* 1804:     */    }
/* 1805:1592 */    throw new NullPointerException();
/* 1806:     */  }
/* 1807:     */  
/* 1809:     */  public final float c()
/* 1810:     */  {
/* 1811:1598 */    if (this.jdField_a_of_type_Vg.a() != null) {
/* 1812:1599 */      return this.jdField_a_of_type_Vg.a().c();
/* 1813:     */    }
/* 1814:1601 */    throw new NullPointerException();
/* 1815:     */  }
/* 1816:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     mx
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */