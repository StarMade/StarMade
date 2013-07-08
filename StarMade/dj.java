/*    1:     */import com.bulletphysics.collision.dispatch.CollisionObject;
/*    2:     */import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
/*    3:     */import com.bulletphysics.dynamics.DynamicsWorld;
/*    4:     */import com.bulletphysics.linearmath.Transform;
/*    5:     */import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*    6:     */import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*    7:     */import java.io.File;
/*    8:     */import java.io.PrintStream;
/*    9:     */import java.util.ArrayList;
/*   10:     */import java.util.Iterator;
/*   11:     */import java.util.List;
/*   12:     */import java.util.Set;
/*   13:     */import java.util.concurrent.ThreadPoolExecutor;
/*   14:     */import javax.vecmath.Matrix3f;
/*   15:     */import javax.vecmath.Tuple3f;
/*   16:     */import javax.vecmath.Vector3f;
/*   17:     */import org.lwjgl.input.Keyboard;
/*   18:     */import org.lwjgl.opengl.GL11;
/*   19:     */import org.lwjgl.opengl.GL15;
/*   20:     */import org.schema.game.client.view.SegmentDrawer;
/*   21:     */import org.schema.game.common.controller.SegmentController;
/*   22:     */import org.schema.game.common.data.element.ControlElementMap;
/*   23:     */import org.schema.game.common.data.element.ElementClassNotFoundException;
/*   24:     */import org.schema.game.common.data.element.ElementInformation;
/*   25:     */import org.schema.game.common.data.element.ElementKeyMap;
/*   26:     */import org.schema.game.common.data.physics.CubeRayCastResult;
/*   27:     */import org.schema.game.common.data.physics.PhysicsExt;
/*   28:     */import org.schema.game.common.data.world.Segment;
/*   29:     */import org.schema.game.common.data.world.Universe;
/*   30:     */import org.schema.schine.graphicsengine.camera.Camera;
/*   31:     */import org.schema.schine.graphicsengine.core.GLException;
/*   32:     */import org.schema.schine.graphicsengine.core.GlUtil;
/*   33:     */import org.schema.schine.graphicsengine.forms.Mesh;
/*   34:     */import org.schema.schine.graphicsengine.shader.ErrorDialogException;
/*   35:     */import org.schema.schine.network.objects.container.PhysicsDataContainer;
/*   36:     */
/*   75:     */public final class dj
/*   76:     */  extends xM
/*   77:     */{
/*   78:     */  public static boolean a;
/*   79:     */  private ct jdField_a_of_type_Ct;
/*   80:     */  private iN jdField_a_of_type_IN;
/*   81:     */  private iU jdField_a_of_type_IU;
/*   82:     */  private es jdField_a_of_type_Es;
/*   83:     */  private eL jdField_a_of_type_EL;
/*   84:     */  private ez jdField_a_of_type_Ez;
/*   85:     */  private final ex jdField_a_of_type_Ex;
/*   86:     */  private ArrayList jdField_a_of_type_JavaUtilArrayList;
/*   87:     */  private final eG jdField_a_of_type_EG;
/*   88:  88 */  private zJ jdField_a_of_type_ZJ = new zJ(9.0F);
/*   89:     */  
/*   90:     */  private iJ jdField_a_of_type_IJ;
/*   91:     */  
/*   92:     */  private dq jdField_a_of_type_Dq;
/*   93:     */  
/*   94:     */  private iV jdField_a_of_type_IV;
/*   95:     */  
/*   96:     */  private ep jdField_a_of_type_Ep;
/*   97:     */  
/*   98:     */  private final fe jdField_a_of_type_Fe;
/*   99:     */  
/*  100:     */  private final cL jdField_a_of_type_CL;
/*  101:     */  
/*  102:     */  private final eN jdField_a_of_type_EN;
/*  103:     */  
/*  104:     */  private boolean f;
/*  105:     */  
/*  106:     */  private SegmentDrawer jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer;
/*  107:     */  
/*  108:     */  private dD jdField_a_of_type_DD;
/*  109:     */  
/*  110:     */  public static SegmentController a;
/*  111:     */  
/*  112:     */  public static long a;
/*  113:     */  private final do jdField_a_of_type_Do;
/*  114:     */  private final eJ jdField_a_of_type_EJ;
/*  115:     */  private final eu jdField_a_of_type_Eu;
/*  116: 116 */  private final dF jdField_a_of_type_DF = new dF();
/*  117:     */  
/*  123: 123 */  private Vector3f jdField_b_of_type_JavaxVecmathVector3f = new Vector3f();
/*  124:     */  public static boolean b;
/*  125:     */  
/*  126: 126 */  static { jdField_b_of_type_Boolean = false; }
/*  127: 127 */  public static boolean c = false;
/*  128: 128 */  public static boolean d = false;
/*  129:     */  
/*  137:     */  private iW jdField_a_of_type_IW;
/*  138:     */  
/*  146:     */  private ew jdField_a_of_type_Ew;
/*  147:     */  
/*  155:     */  private Integer[] jdField_a_of_type_ArrayOfJavaLangInteger;
/*  156:     */  
/*  164:     */  public dj(ct paramct)
/*  165:     */  {
/*  166: 166 */    this.jdField_a_of_type_Ct = paramct;
/*  167:     */    
/*  168: 168 */    this.jdField_a_of_type_JavaUtilArrayList = new ArrayList();
/*  169: 169 */    this.jdField_a_of_type_Do = new do();
/*  170: 170 */    this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer = new SegmentDrawer(paramct);
/*  171: 171 */    this.jdField_a_of_type_EJ = new eJ();
/*  172: 172 */    this.jdField_a_of_type_Eu = new eu();
/*  173:     */    
/*  174: 174 */    this.jdField_a_of_type_CL = new cL(paramct);
/*  175: 175 */    this.jdField_a_of_type_EN = new eN(paramct);
/*  176: 176 */    this.jdField_a_of_type_Ex = new ex();
/*  177: 177 */    this.jdField_a_of_type_Fe = new fe(paramct.a());
/*  178: 178 */    this.jdField_a_of_type_IJ = new iJ();
/*  179: 179 */    this.jdField_a_of_type_Dq = new dq();
/*  180: 180 */    this.jdField_a_of_type_IV = new iV();
/*  181: 181 */    this.jdField_a_of_type_IU = new iU();
/*  182:     */    
/*  183: 183 */    this.jdField_a_of_type_DD = new dD(paramct);
/*  184: 184 */    this.jdField_a_of_type_EG = new eG();
/*  185:     */  }
/*  186:     */  
/*  193:     */  public final void d()
/*  194:     */  {
/*  195: 195 */    this.jdField_a_of_type_EG.d();
/*  196: 196 */    this.jdField_a_of_type_EJ.d();
/*  197:     */    
/*  198: 198 */    this.jdField_a_of_type_Do.e();
/*  199: 199 */    this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.d();
/*  200: 200 */    for (dD localdD = this.jdField_a_of_type_DD; localdD.jdField_a_of_type_JavaUtilArrayList.size() > 0; ((dE)localdD.jdField_a_of_type_JavaUtilArrayList.remove(0)).e()) {}
/*  201:     */  }
/*  202:     */  
/*  205:     */  private void s()
/*  206:     */  {
/*  207:     */    StringBuffer localStringBuffer;
/*  208:     */    
/*  209: 209 */    (localStringBuffer = new StringBuffer()).append("Physics Data (!!warning slow): ");
/*  210:     */    
/*  211: 211 */    for (CollisionObject localCollisionObject : this.jdField_a_of_type_Ct.a().getDynamicsWorld().getCollisionObjectArray())
/*  212:     */    {
/*  213: 212 */      localStringBuffer.append(localCollisionObject.getCollisionShape().getClass().getSimpleName() + ": ");
/*  214: 213 */      for (Iterator localIterator2 = this.jdField_a_of_type_Ct.a().values().iterator(); localIterator2.hasNext();) { mF localmF;
/*  215: 214 */        if ((((localmF = (mF)localIterator2.next()) instanceof zP)) && 
/*  216: 215 */          (((zP)localmF).getPhysicsDataContainer().getObject() == localCollisionObject)) {
/*  217: 216 */          localStringBuffer.append(localmF);
/*  218:     */        }
/*  219:     */      }
/*  220:     */      
/*  221: 220 */      localStringBuffer.append("; \n");
/*  222:     */    }
/*  223:     */    
/*  224: 223 */    xd.jdField_a_of_type_JavaUtilArrayList.add(localStringBuffer.toString());
/*  225:     */  }
/*  226:     */  
/*  227: 226 */  private Integer[] jdField_b_of_type_ArrayOfJavaLangInteger = new Integer[0];
/*  228: 227 */  private int jdField_a_of_type_Int = -2;
/*  229:     */  
/*  246:     */  private float jdField_a_of_type_Float;
/*  247:     */  
/*  264:     */  private void a(long paramLong, q paramq1, q paramq2, Transform paramTransform)
/*  265:     */  {
/*  266: 265 */    paramLong = (float)((System.currentTimeMillis() - paramLong) % 1200000L) / 1200000.0F;
/*  267: 266 */    paramq1.a(16);
/*  268: 267 */    paramq1.a(8, 8, 8);
/*  269:     */    
/*  270: 269 */    paramq1.c(this.jdField_a_of_type_Ct.a().a());
/*  271:     */    
/*  274: 273 */    this.jdField_a_of_type_Float = paramLong;
/*  275:     */    
/*  280: 279 */    paramLong = new Vector3f(paramq2.jdField_a_of_type_Int * Universe.getSectorSizeWithMargin(), paramq2.b * Universe.getSectorSizeWithMargin(), paramq2.c * Universe.getSectorSizeWithMargin());
/*  281:     */    
/*  285: 284 */    new Vector3f(paramq1.jdField_a_of_type_Int * Universe.getSectorSizeWithMargin(), paramq1.b * Universe.getSectorSizeWithMargin(), paramq1.c * Universe.getSectorSizeWithMargin());
/*  286:     */    
/*  289: 288 */    paramTransform.setIdentity();
/*  290: 289 */    if (mI.a(this.jdField_a_of_type_Ct.a().a())) {
/*  291: 290 */      paramTransform.basis.rotX(6.283186F * this.jdField_a_of_type_Float);
/*  292:     */    }
/*  293:     */    
/*  298: 297 */    paramTransform.transform(paramLong);
/*  299: 298 */    xd.jdField_a_of_type_XT.c(paramLong);
/*  300:     */  }
/*  301:     */  
/*  302: 301 */  private q jdField_a_of_type_Q = new q();
/*  303: 302 */  private q jdField_b_of_type_Q = new q();
/*  304:     */  
/*  305:     */  private void a(long paramLong)
/*  306:     */  {
/*  307: 306 */    mq localmq = this.jdField_a_of_type_Ct.a().a();
/*  308: 307 */    int j = -1;
/*  309: 308 */    q localq = new q();
/*  310:     */    Object localObject3;
/*  311: 310 */    for (int k = 0; k < 27; k++)
/*  312:     */    {
/*  313: 312 */      int m = localmq.a(k);
/*  314:     */      
/*  315: 314 */      localObject3 = mD.values()[m];
/*  316:     */      
/*  317: 316 */      localmq.a(k, this.jdField_a_of_type_Q);
/*  318:     */      
/*  319: 318 */      this.jdField_b_of_type_Q.b((this.jdField_a_of_type_Q.jdField_a_of_type_Int << 4) + 8, (this.jdField_a_of_type_Q.b << 4) + 8, (this.jdField_a_of_type_Q.c << 4) + 8);
/*  320:     */      
/*  324: 323 */      (
/*  325: 324 */        localObject2 = new q(this.jdField_b_of_type_Q)).c(this.jdField_a_of_type_Ct.a().a());
/*  326: 325 */      if ((j < 0) || (((q)localObject2).a() < localq.a())) {
/*  327: 326 */        j = k;
/*  328: 327 */        localq.b((q)localObject2);
/*  329:     */      }
/*  330: 329 */      this.jdField_b_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
/*  331: 330 */      if (localObject3 == mD.e)
/*  332:     */      {
/*  333: 332 */        a(paramLong, this.jdField_a_of_type_Q, (q)localObject2, this.jdField_b_of_type_ComBulletphysicsLinearmathTransform);
/*  334:     */      }
/*  335: 334 */      else if (localObject3 == mD.f)
/*  336:     */      {
/*  337: 336 */        a(paramLong, this.jdField_a_of_type_Q, (q)localObject2, this.jdField_b_of_type_ComBulletphysicsLinearmathTransform);
/*  338:     */        
/*  339: 338 */        this.jdField_a_of_type_Dq.b();
/*  340:     */      }
/*  341:     */    }
/*  342:     */    
/*  345: 344 */    mJ.a(this.jdField_a_of_type_Ct.a().a(), this.jdField_a_of_type_Q);
/*  346:     */    
/*  347: 346 */    if (!localmq.a().equals(this.jdField_a_of_type_Q)) {
/*  348: 347 */      System.err.println("[CLIENT] WARNING: System not yet right: " + this.jdField_a_of_type_Q + " / " + localmq.a());
/*  349: 348 */      return;
/*  350:     */    }
/*  351:     */    
/*  352: 351 */    k = localmq.a(localmq.a(this.jdField_a_of_type_Q));
/*  353:     */    
/*  354: 353 */    Object localObject2 = mD.values()[k];
/*  355:     */    
/*  356: 355 */    this.jdField_b_of_type_Q.b((this.jdField_a_of_type_Q.jdField_a_of_type_Int << 4) + 8, (this.jdField_a_of_type_Q.b << 4) + 8, (this.jdField_a_of_type_Q.c << 4) + 8);
/*  357:     */    
/*  361: 360 */    (
/*  362: 361 */      localObject3 = new q(this.jdField_b_of_type_Q)).c(this.jdField_a_of_type_Ct.a().a());
/*  363:     */    
/*  364: 363 */    a(paramLong, this.jdField_a_of_type_Q, (q)localObject3, this.jdField_b_of_type_ComBulletphysicsLinearmathTransform);
/*  365: 364 */    GL11.glDepthRange(0.0D, 1.0D);
/*  366: 365 */    Object localObject1 = localObject3;this.jdField_a_of_type_IV.jdField_a_of_type_Q.b(localObject1);
/*  367:     */    
/*  368: 367 */    if ((!xu.V.b()) && (localObject2 == mD.e))
/*  369:     */    {
/*  371: 370 */      this.jdField_a_of_type_IV.b();
/*  372:     */    }
/*  373:     */    
/*  375: 374 */    xd.jdField_a_of_type_JavaUtilArrayList.add("#####SECTORSYSTEM " + this.jdField_a_of_type_Q + " CENTER: " + this.jdField_b_of_type_Q + ": " + localObject2); }
/*  376:     */  
/*  377: 376 */  private Transform jdField_b_of_type_ComBulletphysicsLinearmathTransform = new Transform();
/*  378:     */  
/*  383:     */  private boolean g;
/*  384:     */  
/*  389:     */  private boolean h;
/*  390:     */  
/*  395:     */  private boolean i;
/*  396:     */  
/*  402:     */  public final void b()
/*  403:     */  {
/*  404: 403 */    if (xu.m.b()) {
/*  405: 404 */      this.jdField_a_of_type_Ct.a().b(0);
/*  406:     */    }
/*  407:     */    
/*  408: 407 */    mC localmC = null;
/*  409:     */    
/*  410: 409 */    int j = 0;
/*  411:     */    Object localObject2;
/*  412: 411 */    if (((localObject2 = this.jdField_a_of_type_IV).jdField_a_of_type_Q.jdField_a_of_type_Int == 0) && (((iV)localObject2).jdField_a_of_type_Q.b == 0) && (((iV)localObject2).jdField_a_of_type_Q.c == 0)) { GL11.glDisable(2929);GL11.glDisable(2884);GlUtil.d();GlUtil.a(xe.a().a());GlUtil.b(3.0F, 3.0F, 3.0F);GL11.glEnable(3042);GL11.glBlendFunc(770, 771);zk.e.c();GlUtil.a(zk.e, "time", ((iV)localObject2).jdField_a_of_type_Float * 10.0F);((iV)localObject2).jdField_a_of_type_OrgSchemaSchineGraphicsengineFormsMesh.b();zj.e();GL11.glDisable(3042);GlUtil.c();GL11.glEnable(2929);GL11.glEnable(2884);GL11.glClear(256);
/*  413:     */    }
/*  414:     */    Object localObject3;
/*  415: 414 */    if (this.jdField_a_of_type_Ct.a().a().a() != this.jdField_a_of_type_Int) {
/*  416: 415 */      synchronized (this.jdField_a_of_type_Ct.a().a()) {
/*  417: 416 */        this.jdField_a_of_type_Int = this.jdField_a_of_type_Ct.a().a().a();
/*  418: 417 */        localObject2 = this;localObject3 = new dk((dj)localObject2);((dj)localObject2).jdField_a_of_type_Ct.getThreadPool().execute((Runnable)localObject3);
/*  419:     */      }
/*  420:     */    }
/*  421: 420 */    if (this.jdField_a_of_type_ArrayOfJavaLangInteger != null) {
/*  422: 421 */      this.jdField_b_of_type_ArrayOfJavaLangInteger = this.jdField_a_of_type_ArrayOfJavaLangInteger;
/*  423: 422 */      this.jdField_a_of_type_ArrayOfJavaLangInteger = null;
/*  424:     */    }
/*  425:     */    
/*  426: 425 */    this.jdField_b_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
/*  427: 426 */    ??? = new q();
/*  428:     */    
/*  431: 430 */    long l = this.jdField_a_of_type_Ct.a().calculateStartTime();
/*  432:     */    
/*  433: 432 */    for (int k = 0; k < this.jdField_b_of_type_ArrayOfJavaLangInteger.length; k++)
/*  434:     */    {
/*  436: 435 */      int m = this.jdField_b_of_type_ArrayOfJavaLangInteger[k].intValue();
/*  437:     */      
/*  440: 439 */      this.jdField_a_of_type_Ct.a().a().a(m, (q)???);
/*  441: 440 */      localObject3 = mD.values()[this.jdField_a_of_type_Ct.a().a().b(m)];
/*  442: 441 */      mC.values();this.jdField_a_of_type_Ct.a().a().a(m);
/*  443:     */      
/*  444: 443 */      if (localObject3 == mD.c)
/*  445:     */      {
/*  446: 445 */        if (this.jdField_a_of_type_Ct.a().a().equals(???))
/*  447:     */        {
/*  450: 449 */          localmC = mC.values()[this.jdField_a_of_type_Ct.a().a().a(m)];
/*  451:     */          
/*  452: 451 */          this.jdField_a_of_type_IN.a = localmC;
/*  453: 452 */          if (this.jdField_a_of_type_IN.a()) {
/*  454: 453 */            j = 1;
/*  455:     */          } else {
/*  456: 455 */            this.jdField_a_of_type_IN.b();
/*  457:     */          }
/*  458:     */          
/*  459:     */        }
/*  460:     */        else
/*  461:     */        {
/*  462: 461 */          (localObject3 = new q((q)???)).c(this.jdField_a_of_type_Ct.a().a());
/*  463:     */          
/*  464: 463 */          this.jdField_b_of_type_ComBulletphysicsLinearmathTransform.basis.setIdentity();
/*  465: 464 */          q localq = mJ.a((q)???, new q());
/*  466: 465 */          if (mI.a((q)???))
/*  467:     */          {
/*  468: 467 */            float f1 = (float)((System.currentTimeMillis() - l) % 1200000L) / 1200000.0F;
/*  469: 468 */            if (mI.a(this.jdField_a_of_type_Ct.a().a())) {
/*  470: 469 */              this.jdField_a_of_type_IJ.jdField_a_of_type_Float = f1;
/*  471:     */            } else {
/*  472: 471 */              this.jdField_a_of_type_IJ.jdField_a_of_type_Float = 0.0F;
/*  473:     */            }
/*  474:     */            
/*  475: 474 */            localq.a(16);
/*  476: 475 */            localq.a(8, 8, 8);
/*  477: 476 */            localq.c(this.jdField_a_of_type_Ct.a().a());
/*  478: 477 */            this.jdField_a_of_type_IJ.jdField_a_of_type_Q.b(localq);
/*  479:     */          }
/*  480:     */          else {
/*  481: 480 */            this.jdField_a_of_type_IJ.jdField_a_of_type_Float = 0.0F;
/*  482:     */          }
/*  483: 482 */          this.jdField_a_of_type_IJ.a((q)localObject3);
/*  484: 483 */          this.jdField_a_of_type_IJ.a(mC.values()[this.jdField_a_of_type_Ct.a().a().a(m)]);
/*  485: 484 */          this.jdField_a_of_type_IJ.b();
/*  486:     */        }
/*  487:     */      }
/*  488:     */    }
/*  489: 488 */    GL11.glDepthRange(0.0D, 1.0D);
/*  490:     */    
/*  498: 497 */    this.jdField_a_of_type_DD.b();
/*  499:     */    
/*  500: 499 */    this.jdField_a_of_type_Ep.b();
/*  501:     */    
/*  502: 501 */    this.jdField_a_of_type_Ex.b();
/*  503:     */    
/*  504: 503 */    for (k = 0; k < this.jdField_a_of_type_JavaUtilArrayList.size(); k++) {
/*  505: 504 */      ((cX)this.jdField_a_of_type_JavaUtilArrayList.get(k)).b();
/*  506:     */    }
/*  507: 506 */    this.jdField_a_of_type_Do.f();
/*  508:     */    
/*  509: 508 */    h.a("SEGMENTS");
/*  510:     */    
/*  512: 511 */    this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.b();
/*  513: 512 */    h.b("SEGMENTS");
/*  514:     */    
/*  515: 514 */    this.jdField_a_of_type_DF.b();
/*  516:     */    
/*  517: 516 */    if (Keyboard.isKeyDown(cv.U.a())) { if (((((localObject3 = this.jdField_a_of_type_Ct.a().a.a.a).a().a().a.c) || (((ar)localObject3).a().a().c)) && (jdField_a_of_type_Boolean) ? 1 : 0) != 0) {
/*  518: 517 */        this.jdField_b_of_type_JavaxVecmathVector3f = xd.a(this.jdField_b_of_type_JavaxVecmathVector3f);
/*  519:     */      }
/*  520:     */    }
/*  521:     */    
/*  522: 521 */    this.jdField_a_of_type_Ew.b();
/*  523:     */    
/*  524: 523 */    this.jdField_a_of_type_CL.b();
/*  525:     */    try
/*  526:     */    {
/*  527: 526 */      if ((jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController != null) && (System.currentTimeMillis() - jdField_a_of_type_Long < 8000L) && (this.jdField_a_of_type_Ct.a() != null) && (this.jdField_a_of_type_Ct.a().a.a.a.a().a().a.c))
/*  528:     */      {
/*  530: 529 */        System.currentTimeMillis();
/*  531: 530 */        GlUtil.d();
/*  532: 531 */        Mesh localMesh = (Mesh)xe.a().a("Arrow").a.get(0);
/*  533: 532 */        localObject3 = new Transform(jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransform());
/*  534:     */        
/*  535: 534 */        (
/*  536: 535 */          ??? = new Vector3f(0.0F, 0.0F, 1.0F)).scale(this.jdField_a_of_type_ZJ.a());
/*  537:     */        
/*  538: 537 */        ((Transform)localObject3).basis.transform((Tuple3f)???);
/*  539: 538 */        ((Transform)localObject3).origin.add((Tuple3f)???);
/*  540: 539 */        GlUtil.b((Transform)localObject3);
/*  541:     */        
/*  543: 542 */        GlUtil.b(0.3F, 0.3F, 0.3F);
/*  544: 543 */        GL11.glEnable(3042);
/*  545: 544 */        GL11.glBlendFunc(770, 771);
/*  546: 545 */        GL11.glEnable(2903);
/*  547: 546 */        GlUtil.a(1.0F, 1.0F, 1.0F, this.jdField_a_of_type_ZJ.a());
/*  548:     */        
/*  550: 549 */        localMesh.b();
/*  551: 550 */        GlUtil.c();
/*  552: 551 */        GL11.glDisable(2903);
/*  553: 552 */        GL11.glDisable(3042);
/*  554: 553 */        GlUtil.a(1.0F, 1.0F, 1.0F, 1.0F);
/*  555:     */      } else {
/*  556: 555 */        jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController = null;
/*  557: 556 */        jdField_a_of_type_Long = 0L;
/*  558:     */      }
/*  559: 558 */    } catch (NullPointerException localNullPointerException) { 
/*  560:     */      
/*  561: 560 */        localNullPointerException;
/*  562:     */    }
/*  563:     */    
/*  565: 562 */    if (j != 0) {
/*  566: 563 */      this.jdField_a_of_type_IN.b();
/*  567:     */    }
/*  568:     */    
/*  571: 568 */    this.jdField_a_of_type_EG.b();
/*  572:     */    
/*  576: 573 */    this.jdField_a_of_type_EL.jdField_a_of_type_Boolean = this.jdField_a_of_type_IN.a();
/*  577: 574 */    this.jdField_a_of_type_EL.b();
/*  578: 575 */    this.jdField_a_of_type_Eu.a();
/*  579:     */    
/*  581: 578 */    this.jdField_a_of_type_EJ.b();
/*  582:     */    
/*  587: 584 */    this.jdField_a_of_type_Es.b();
/*  588:     */    
/*  589: 586 */    this.jdField_a_of_type_Ez.b();
/*  590:     */    
/*  591: 588 */    if ((localmC != null) && (j == 0)) {
/*  592: 589 */      this.jdField_a_of_type_IJ.a(new q(0, 0, 0));
/*  593: 590 */      this.jdField_a_of_type_IJ.a(localmC);
/*  594: 591 */      this.jdField_a_of_type_IJ.b();
/*  595:     */    }
/*  596:     */    
/*  609: 606 */    a(l);
/*  610:     */    
/*  612: 609 */    this.jdField_a_of_type_EN.b();
/*  613:     */    
/*  679: 676 */    if (xu.G.b()) {
/*  680: 677 */      s();
/*  681:     */    }
/*  682:     */    
/*  685: 682 */    SegmentDrawer.jdField_a_of_type_Boolean = false;
/*  686:     */  }
/*  687:     */  
/*  698:     */  public final void e()
/*  699:     */  {
/*  700: 697 */    if (d) {
/*  701: 698 */      t();
/*  702: 699 */      d = false;
/*  703:     */    }
/*  704:     */    
/*  705: 702 */    h.a("GUI");
/*  706: 703 */    this.jdField_a_of_type_Fe.b();
/*  707: 704 */    h.b("GUI");
/*  708:     */    
/*  709: 706 */    if (c) {
/*  710: 707 */      t();
/*  711: 708 */      c = false;
/*  712:     */    }
/*  713:     */    
/*  714: 711 */    eV.e();
/*  715:     */    iW localiW;
/*  716: 713 */    if (xu.o.b()) {
/*  717: 714 */      if (this.jdField_a_of_type_IW == null) {
/*  718: 715 */        this.jdField_a_of_type_IW = new iW();
/*  719:     */      }
/*  720: 717 */      iW tmp77_74 = this.jdField_a_of_type_IW;tmp77_74.b = (localiW = tmp77_74).d;localiW.c = localiW.e;localiW.a();
/*  721:     */    }
/*  722: 719 */    if (jdField_b_of_type_Boolean) {
/*  723: 720 */      localiW = new iW();
/*  724: 721 */      if (this.jdField_a_of_type_IW == null) {
/*  725: 722 */        this.jdField_a_of_type_IW = new iW();
/*  726:     */      }
/*  727: 724 */      localiW.jdField_a_of_type_Int = this.jdField_a_of_type_IW.jdField_a_of_type_Int;
/*  728:     */      try { xi localxi;
/*  729: 726 */        (localxi = new xi(1024, 1024)).e();localxi.d();GL11.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);GL11.glClear(16640);localiW.b = 32;localiW.c = 32;GL11.glViewport(0, 0, 1024, 1024);localiW.jdField_a_of_type_Boolean = true;localiW.a();localiW.jdField_a_of_type_Boolean = false;System.err.println("SHEET: " + localiW.jdField_a_of_type_Int + " WRITING SCREEN TO DISK: ./data/image-resource/build-icons-" + i.b(localiW.jdField_a_of_type_Int) + "-16x16-gui-");GlUtil.a("./data/image-resource/build-icons-" + i.b(localiW.jdField_a_of_type_Int) + "-16x16-gui-", "png", 1024, 1024);GL11.glViewport(0, 0, xm.b(), xm.a());localxi.b();localxi.a();
/*  730: 727 */      } catch (GLException localGLException) { 
/*  731:     */        
/*  732: 729 */          localGLException;
/*  733:     */      }
/*  734:     */      
/*  735: 730 */      jdField_b_of_type_Boolean = false;
/*  736:     */    }
/*  737:     */  }
/*  738:     */  
/*  741:     */  private static void t()
/*  742:     */  {
/*  743: 738 */    File[] arrayOfFile1 = new File("./").listFiles();
/*  744: 739 */    int j = 0;
/*  745: 740 */    int k = 1;
/*  746: 741 */    label120: while (k != 0) {
/*  747: 742 */      k = 0;
/*  748: 743 */      File[] arrayOfFile2 = arrayOfFile1;int m = arrayOfFile1.length; for (int n = 0;; n++) { if (n >= m) break label120;
/*  749: 744 */        if (arrayOfFile2[n].getName().startsWith("starmade-screenshot-" + i.a(j) + ".png"))
/*  750:     */        {
/*  751: 746 */          System.err.println("Screen Already Exists: ./starmade-screenshot-" + i.a(j) + ".png");
/*  752: 747 */          j++;
/*  753: 748 */          k = 1;
/*  754: 749 */          break;
/*  755:     */        }
/*  756:     */      }
/*  757:     */    }
/*  758:     */    
/*  759: 754 */    GlUtil.a("./starmade-screenshot-" + i.a(j), "png", xm.b(), xm.a());
/*  760:     */  }
/*  761:     */  
/*  762:     */  public final void f() {
/*  763: 758 */    Keyboard.enableRepeatEvents(true);
/*  764: 759 */    if ((xu.o.b()) && 
/*  765: 760 */      (this.jdField_a_of_type_IW != null)) {
/*  766: 761 */      iW localiW = this.jdField_a_of_type_IW; if (Keyboard.getEventKeyState()) { int j; switch (Keyboard.getEventKey()) {case 17:  localiW.e += 8;break; case 30:  localiW.d -= 8;break; case 31:  localiW.e -= 8;break; case 32:  localiW.d += 8;break; case 205:  j = ElementKeyMap.highestType / 256 + 1;localiW.jdField_a_of_type_Int = ((localiW.jdField_a_of_type_Int + 1) % j);break; case 203:  j = ElementKeyMap.highestType / 256 + 1; if (localiW.jdField_a_of_type_Int - 1 < 0) localiW.jdField_a_of_type_Int = (j - 1); else localiW.jdField_a_of_type_Int -= 1;
/*  767:     */          break; }
/*  768:     */      } }
/*  769: 764 */    Keyboard.enableRepeatEvents(false);
/*  770:     */  }
/*  771:     */  
/*  772: 767 */  public final void g() { this.jdField_a_of_type_Ct.a();cW.b().b();
/*  773: 768 */    Vector3f localVector3f = xd.jdField_a_of_type_XT.a();
/*  774: 769 */    GlUtil.d();
/*  775:     */    
/*  776: 771 */    GlUtil.c(localVector3f.x, localVector3f.y, localVector3f.z);
/*  777:     */    
/*  778: 773 */    float f2 = 4.0F;
/*  779:     */    
/*  780:     */    float f1;
/*  781:     */    
/*  782: 777 */    if ((f1 = (float)Math.pow(localVector3f.length(), 1.299999952316284D)) < 30000.0F) {
/*  783: 778 */      f2 = f1 / 30000.0F;
/*  784: 779 */      f2 = 4.0F * f2;
/*  785:     */    }
/*  786:     */    
/*  787: 782 */    xe.a().a("Sphere").b(f2, f2, f2);
/*  788: 783 */    xe.a().a("Sphere").b();
/*  789: 784 */    GlUtil.c();
/*  790:     */  }
/*  791:     */  
/*  792:     */  public static void a(SegmentController paramSegmentController)
/*  793:     */  {
/*  794: 789 */    jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController = paramSegmentController;
/*  795: 790 */    jdField_a_of_type_Long = System.currentTimeMillis();
/*  796:     */  }
/*  797:     */  
/*  802:     */  public final Vector3f b()
/*  803:     */  {
/*  804: 799 */    return this.jdField_b_of_type_JavaxVecmathVector3f;
/*  805:     */  }
/*  806:     */  
/*  808:     */  public final do a()
/*  809:     */  {
/*  810: 805 */    return this.jdField_a_of_type_Do;
/*  811:     */  }
/*  812:     */  
/*  814:     */  public final cL a()
/*  815:     */  {
/*  816: 811 */    return this.jdField_a_of_type_CL;
/*  817:     */  }
/*  818:     */  
/*  819: 814 */  public final es a() { return this.jdField_a_of_type_Es; }
/*  820:     */  
/*  821:     */  public final fe a() {
/*  822: 817 */    return this.jdField_a_of_type_Fe;
/*  823:     */  }
/*  824:     */  
/*  826:     */  public final ex a()
/*  827:     */  {
/*  828: 823 */    return this.jdField_a_of_type_Ex;
/*  829:     */  }
/*  830:     */  
/*  832:     */  public final SegmentDrawer a()
/*  833:     */  {
/*  834: 829 */    return this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer;
/*  835:     */  }
/*  836:     */  
/*  838:     */  public final eJ a()
/*  839:     */  {
/*  840: 835 */    return this.jdField_a_of_type_EJ;
/*  841:     */  }
/*  842:     */  
/*  843: 838 */  public final iU a() { return this.jdField_a_of_type_IU; }
/*  844:     */  
/*  847:     */  public final ez a()
/*  848:     */  {
/*  849: 844 */    return this.jdField_a_of_type_Ez;
/*  850:     */  }
/*  851:     */  
/*  852:     */  public final void h() {
/*  853: 848 */    h.a("CONTEXT");
/*  854: 849 */    this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.e();
/*  855: 850 */    h.b("CONTEXT");
/*  856:     */  }
/*  857:     */  
/*  859:     */  public final void c()
/*  860:     */  {
/*  861: 856 */    this.jdField_a_of_type_IN = new iN();
/*  862: 857 */    this.jdField_a_of_type_IN.c();
/*  863:     */    
/*  864: 859 */    this.jdField_a_of_type_EG.c();
/*  865:     */    
/*  867: 862 */    this.jdField_a_of_type_DD.c();
/*  868:     */    
/*  869: 864 */    this.jdField_a_of_type_Ex.c();
/*  870:     */    
/*  871: 866 */    this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.c();
/*  872:     */    
/*  875: 870 */    this.jdField_a_of_type_IJ.c();
/*  876: 871 */    this.jdField_a_of_type_Dq.c();
/*  877: 872 */    this.jdField_a_of_type_IV.c();
/*  878: 873 */    this.jdField_a_of_type_CL.c();
/*  879: 874 */    this.jdField_a_of_type_EN.c();
/*  880: 875 */    this.jdField_a_of_type_IU.c();
/*  881: 876 */    this.jdField_a_of_type_Es = new es();
/*  882: 877 */    this.jdField_a_of_type_Es.c();
/*  883: 878 */    this.jdField_a_of_type_Fe.c();
/*  884:     */    
/*  885: 880 */    this.jdField_a_of_type_EL = new eL();
/*  886: 881 */    this.jdField_a_of_type_EL.c();
/*  887:     */    
/*  888: 883 */    this.jdField_a_of_type_Ez = new ez(this.jdField_a_of_type_Ct);
/*  889: 884 */    this.jdField_a_of_type_Ez.c();
/*  890:     */    
/*  891: 886 */    this.jdField_a_of_type_Ew = new ew(this.jdField_a_of_type_Ct);
/*  892: 887 */    this.jdField_a_of_type_Ew.c();
/*  893:     */    
/*  894: 889 */    this.jdField_a_of_type_Ep = new ep(this.jdField_a_of_type_Ct);
/*  895: 890 */    this.jdField_a_of_type_Ep.c();
/*  896:     */    
/*  897: 892 */    cX localcX = new cX(this.jdField_a_of_type_Ct.getParticleController());
/*  898: 893 */    this.jdField_a_of_type_Ct.a().jdField_a_of_type_JavaUtilArrayList.add(localcX);
/*  899: 894 */    localcX.c();
/*  900:     */    
/*  948: 943 */    this.f = true;
/*  949:     */  }
/*  950:     */  
/*  957:     */  public final void i()
/*  958:     */  {
/*  959: 954 */    long l1 = System.currentTimeMillis();
/*  960:     */    
/*  964: 959 */    this.jdField_a_of_type_Fe.d();
/*  965:     */    long l2;
/*  966: 961 */    if ((l2 = System.currentTimeMillis() - l1) > 10L) {
/*  967: 962 */      System.err.println("[WORLDDRAWER] WARNING: SECTOR CHANGE UPDATE FOR DRAWER TOOK " + l2);
/*  968:     */    }
/*  969:     */  }
/*  970:     */  
/*  971:     */  public final void j()
/*  972:     */  {
/*  973:     */    iV localiV;
/*  974: 969 */    if ((this.jdField_a_of_type_IV != null) && 
/*  975: 970 */      ((localiV = this.jdField_a_of_type_IV).c > 5)) { localiV.b = GL15.glGetQueryObjectui(localiV.jdField_a_of_type_Int, 34918);localiV.c = 0;
/*  976:     */    }
/*  977:     */  }
/*  978:     */  
/*  999:     */  public final void a(xq paramxq)
/* 1000:     */  {
/* 1001: 996 */    if (!this.f) {
/* 1002: 997 */      return;
/* 1003:     */    }
/* 1004:     */    
/* 1005:1000 */    h.a("update");
/* 1006:     */    
/* 1007:1002 */    long l1 = System.currentTimeMillis();
/* 1008:     */    long l2;
/* 1009:1004 */    if ((l2 = System.currentTimeMillis() - l1) > 10L) {
/* 1010:1005 */      System.err.println("[DRAWER][WARNING] synUPDATE took " + l2 + " ms");
/* 1011:     */    }
/* 1012:1007 */    l1 = System.currentTimeMillis();
/* 1013:1008 */    if (this.h) {
/* 1014:1009 */      this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.f();
/* 1015:1010 */      this.h = false;
/* 1016:     */    }
/* 1017:     */    
/* 1018:     */    long l3;
/* 1019:     */    
/* 1020:1015 */    if ((l3 = System.currentTimeMillis() - l1) > 10L) {
/* 1021:1016 */      System.err.println("[DRAWER][WARNING] seg controller set update took " + l3 + " ms");
/* 1022:     */    }
/* 1023:1018 */    l1 = System.currentTimeMillis();
/* 1024:1019 */    if (this.g) {
/* 1025:1020 */      u();
/* 1026:1021 */      this.g = false;
/* 1027:     */    }
/* 1028:     */    long l4;
/* 1029:1024 */    if ((l4 = System.currentTimeMillis() - l1) > 10L) {
/* 1030:1025 */      System.err.println("[DRAWER][WARNING] segManControllerUpdate took " + l4 + " ms");
/* 1031:     */    }
/* 1032:1027 */    if (this.i) {
/* 1033:1028 */      this.jdField_a_of_type_DD.d();
/* 1034:1029 */      this.i = false;
/* 1035:     */    }
/* 1036:1031 */    this.jdField_a_of_type_ZJ.a(paramxq);
/* 1037:1032 */    Object localObject4 = null;Object localObject2 = paramxq; if ((localObject1 = this.jdField_a_of_type_IU).a != null) { ((iU)localObject1).a.jdField_a_of_type_ZJ.a((xq)localObject2);
/* 1038:     */    }
/* 1039:1034 */    this.jdField_a_of_type_DD.a(paramxq);
/* 1040:     */    
/* 1041:1036 */    this.jdField_a_of_type_IJ.a(paramxq);
/* 1042:1037 */    localObject2 = paramxq;(localObject1 = this.jdField_a_of_type_Dq).jdField_a_of_type_Float += ((xq)localObject2).a() * 0.07F; if (((dq)localObject1).jdField_a_of_type_Float > 1.0F) { Object tmp293_292 = localObject1;tmp293_292.jdField_a_of_type_Float = ((float)(tmp293_292.jdField_a_of_type_Float - Math.floor(((dq)localObject1).jdField_a_of_type_Float))); }
/* 1043:1038 */    localObject2 = paramxq;this.jdField_a_of_type_IV.jdField_a_of_type_Float += ((xq)localObject2).a() * 0.07F;
/* 1044:     */    
/* 1045:1040 */    this.jdField_a_of_type_EN.a(paramxq);
/* 1046:     */    
/* 1049:1044 */    this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.a(paramxq);
/* 1050:     */    
/* 1052:1047 */    localObject2 = paramxq;this.jdField_a_of_type_Es.a.a((xq)localObject2);
/* 1053:     */    
/* 1055:1050 */    localObject2 = paramxq; if (!(localObject1 = this.jdField_a_of_type_EL).jdField_a_of_type_Boolean) { ((eL)localObject1).jdField_a_of_type_JavaxVecmathVector3f.set(xe.a().a());((eL)localObject1).jdField_a_of_type_JavaxVecmathVector3f.sub(((eL)localObject1).jdField_b_of_type_JavaxVecmathVector3f);float f1 = ((eL)localObject1).jdField_a_of_type_JavaxVecmathVector3f.length();localObject1.jdField_b_of_type_Float += f1; if (((eL)localObject1).jdField_b_of_type_Float > 0.05F) { ((eL)localObject1).jdField_a_of_type_Za.a(Math.min(((eL)localObject1).jdField_b_of_type_Float, ((eL)localObject1).jdField_a_of_type_Float), ((eL)localObject1).jdField_a_of_type_Float);((eL)localObject1).jdField_b_of_type_Float = 0.0F; } } ((eL)localObject1).jdField_a_of_type_Za.a((xq)localObject2);((eL)localObject1).jdField_b_of_type_JavaxVecmathVector3f.set(xe.a().a());
/* 1056:     */    
/* 1058:1053 */    this.jdField_a_of_type_EJ.a(paramxq);
/* 1059:1054 */    eu.b();
/* 1060:     */    
/* 1061:1056 */    this.jdField_a_of_type_EG.a(paramxq);
/* 1062:     */    
/* 1064:1059 */    this.jdField_a_of_type_Ez.a(paramxq);
/* 1065:     */    
/* 1067:1062 */    this.jdField_a_of_type_Fe.a(paramxq);
/* 1068:     */    
/* 1070:1065 */    localObject2 = paramxq;Object localObject1 = this.jdField_a_of_type_CL;ij.jdField_a_of_type_JavaUtilArrayList.remove(((cL)localObject1).jdField_a_of_type_Eq); if (((((cL)localObject1).a().a().c) || (((cL)localObject1).a().a.c) || (((cL)localObject1).a().jdField_b_of_type_Boolean)) && (((cL)localObject1).jdField_a_of_type_Ct.a() != null)) { if (((cL)localObject1).d) { ((cL)localObject1).jdField_b_of_type_OrgSchemaGameCommonDataWorldSegment = null;((cL)localObject1).jdField_a_of_type_OrgSchemaGameCommonDataWorldSegment = null;((cL)localObject1).jdField_a_of_type_Le = null;((cL)localObject1).jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation = null;((cL)localObject1).d = false; } ((cL)localObject1).jdField_a_of_type_ZI.a((xq)localObject2);((cL)localObject1).jdField_b_of_type_ZI.a((xq)localObject2); try { localObject3 = ((cL)localObject1).a();localObject2 = new Vector3f(xe.a().a()); if ((localObject3 == null) && (((cL)localObject1).jdField_a_of_type_Ct.a() != null)) ((Vector3f)localObject2).set(((cL)localObject1).jdField_a_of_type_Ct.a().a().origin); localObject4 = new Vector3f((Vector3f)localObject2); Vector3f localVector3f; if (!Float.isNaN((localVector3f = new Vector3f(xe.a().c())).x)) { if (Keyboard.isKeyDown(cv.U.a())) { localObject4 = new Vector3f(((cL)localObject1).jdField_a_of_type_Ct.a().jdField_b_of_type_JavaxVecmathVector3f);localVector3f.sub((Tuple3f)localObject4, (Tuple3f)localObject2);localVector3f.normalize(); } localVector3f.scale(localObject3 != null ? 160.0F : 6.0F);((Vector3f)localObject4).add(localVector3f);((cL)localObject1).jdField_a_of_type_ComBulletphysicsCollisionDispatchCollisionWorld$ClosestRayResultCallback = ((PhysicsExt)((cL)localObject1).jdField_a_of_type_Ct.a()).testRayCollisionPoint((Vector3f)localObject2, (Vector3f)localObject4, false, null, localObject3 != null ? ((au)localObject3).a() : null, false, ((cL)localObject1).jdField_a_of_type_OrgSchemaGameCommonDataWorldSegment, true); if ((((cL)localObject1).jdField_a_of_type_ComBulletphysicsCollisionDispatchCollisionWorld$ClosestRayResultCallback != null) && (((cL)localObject1).jdField_a_of_type_ComBulletphysicsCollisionDispatchCollisionWorld$ClosestRayResultCallback.hasHit()) && ((((cL)localObject1).jdField_a_of_type_ComBulletphysicsCollisionDispatchCollisionWorld$ClosestRayResultCallback instanceof CubeRayCastResult)) && ((localObject2 = (CubeRayCastResult)((cL)localObject1).jdField_a_of_type_ComBulletphysicsCollisionDispatchCollisionWorld$ClosestRayResultCallback).getSegment() != null)) { ((cL)localObject1).jdField_a_of_type_OrgSchemaGameCommonDataWorldSegment = ((CubeRayCastResult)localObject2).getSegment(); if (((((CubeRayCastResult)localObject2).getSegment() != null) && (((cL)localObject1).jdField_b_of_type_OrgSchemaGameCommonDataWorldSegment != null) && (!((CubeRayCastResult)localObject2).getSegment().equals(((cL)localObject1).jdField_b_of_type_OrgSchemaGameCommonDataWorldSegment))) || (!((CubeRayCastResult)localObject2).cubePos.equals(((cL)localObject1).jdField_a_of_type_O)) || (((cL)localObject1).jdField_a_of_type_Le == null)) { ((cL)localObject1).jdField_a_of_type_O.b(((CubeRayCastResult)localObject2).cubePos);((cL)localObject1).jdField_b_of_type_OrgSchemaGameCommonDataWorldSegment = ((CubeRayCastResult)localObject2).getSegment();((cL)localObject1).jdField_a_of_type_Le = new le(((cL)localObject1).jdField_b_of_type_OrgSchemaGameCommonDataWorldSegment, ((cL)localObject1).jdField_a_of_type_O);((cL)localObject1).jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation = ElementKeyMap.getInfo(((cL)localObject1).jdField_a_of_type_Le.a()); } } else { ((cL)localObject1).jdField_a_of_type_OrgSchemaGameCommonDataWorldSegment = null;((cL)localObject1).jdField_a_of_type_Le = null;((cL)localObject1).jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation = null; } if (((cL)localObject1).jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation != null) if (((cL)localObject1).jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation.isEnterable()) { ((cL)localObject1).jdField_a_of_type_Eq.a = ("Enter " + ((cL)localObject1).jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation.getName() + " [" + cv.v.b() + "]");((cL)localObject1).jdField_a_of_type_Le.a(((cL)localObject1).jdField_a_of_type_Eq.a);ij.jdField_a_of_type_JavaUtilArrayList.add(((cL)localObject1).jdField_a_of_type_Eq); } else if (((cL)localObject1).jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation.getFactory() != null) { localObject2 = "[" + cv.z.b() + "]: Enter Connection Mode"; String str1; if ((localObject4 = ((cL)localObject1).a().jdField_a_of_type_Le) != null) if (!((le)localObject4).equals(((cL)localObject1).jdField_a_of_type_Le)) { boolean bool = ((le)localObject4).a().a().getControlElementMap().isControlling(((le)localObject4).a(((cL)localObject1).jdField_a_of_type_Q), ((cL)localObject1).jdField_a_of_type_Le.a(new q(((cL)localObject1).jdField_b_of_type_Q)), ((cL)localObject1).jdField_a_of_type_Le.a());str1 = "[" + cv.A.b() + "]: " + (!bool ? "Connect" : "Disconnect") + " this Block to " + ElementKeyMap.getInfo(((le)localObject4).a()).getName() + "\n[" + cv.z.b() + "]: Switch Connection Mode"; } else { str1 = "[" + cv.z.b() + "]: Exit Connection Mode"; } ((cL)localObject1).jdField_a_of_type_Eq.a = ("[" + cv.w.b() + "]: Open Block Inventory\n" + str1);((cL)localObject1).jdField_a_of_type_Le.a(((cL)localObject1).jdField_a_of_type_Eq.a);ij.jdField_a_of_type_JavaUtilArrayList.add(((cL)localObject1).jdField_a_of_type_Eq); } else if (((cL)localObject1).jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation.canActivate()) { if (((cL)localObject1).jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation.getId() == 16) ((cL)localObject1).jdField_a_of_type_Eq.a = ("Make Output [" + cv.w.b() + "] " + (((cL)localObject1).jdField_a_of_type_Le.a() ? "ON" : "OFF")); else ((cL)localObject1).jdField_a_of_type_Eq.a = ("Activate " + ((cL)localObject1).jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation.getName() + " [" + cv.w.b() + "]"); ((cL)localObject1).jdField_a_of_type_Le.a(((cL)localObject1).jdField_a_of_type_Eq.a);ij.jdField_a_of_type_JavaUtilArrayList.add(((cL)localObject1).jdField_a_of_type_Eq); } if (localObject3 != null) { if (((au)localObject3).a() != null) { ((au)localObject3).a().a(); if ((((cL)localObject1).jdField_b_of_type_Le != ((au)localObject3).a()) && (((au)localObject3).a().a() != 0)) { ((cL)localObject1).jdField_b_of_type_Le = ((au)localObject3).a();((cL)localObject1).jdField_b_of_type_OrgSchemaGameCommonDataElementElementInformation = ElementKeyMap.getInfo(((cL)localObject1).jdField_b_of_type_Le.a()); } else if (((au)localObject3).a().a() != 0) {} } else { ((cL)localObject1).jdField_b_of_type_Le = null;((cL)localObject1).jdField_b_of_type_OrgSchemaGameCommonDataElementElementInformation = null; } ((cL)localObject1).jdField_a_of_type_Boolean = false;((cL)localObject1).jdField_b_of_type_Boolean = false;((cL)localObject1).c = false; short s; if ((((cL)localObject1).jdField_b_of_type_Le != null) && ((s = ((cL)localObject1).jdField_b_of_type_Le.a()) != 0) && (((cL)localObject1).jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation != null)) { if (((cL)localObject1).jdField_b_of_type_OrgSchemaGameCommonDataElementElementInformation != null) try { if (((cL)localObject1).jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation.getControlledBy().contains(Short.valueOf(s))) ((cL)localObject1).jdField_a_of_type_Boolean = true; } catch (ElementClassNotFoundException localElementClassNotFoundException) { localElementClassNotFoundException; } if (!((cL)localObject1).jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation.getControlling().isEmpty()) ((cL)localObject1).jdField_b_of_type_Boolean = true; } if ((((cL)localObject1).jdField_b_of_type_OrgSchemaGameCommonDataElementElementInformation != null) && (((cL)localObject1).jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation == ((cL)localObject1).jdField_b_of_type_OrgSchemaGameCommonDataElementElementInformation)) { ((cL)localObject1).c = true;((cL)localObject1).jdField_b_of_type_Boolean = false; } String str2; if ((localObject1 = localObject1).jdField_b_of_type_OrgSchemaGameCommonDataElementElementInformation != null) { str2 = "SELECTED:\n" + ((cL)localObject1).jdField_b_of_type_OrgSchemaGameCommonDataElementElementInformation.getName() + (((cL)localObject1).c ? "\ndeselect with " + cv.z.b() : "");((cL)localObject1).jdField_a_of_type_Ct.a().b(str2); } else { ((cL)localObject1).jdField_a_of_type_Ct.a().c(); } if (((cL)localObject1).jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation != null) { str2 = ((cL)localObject1).jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation.getName() + (((cL)localObject1).jdField_a_of_type_Boolean ? "\n\n(dis)connect to " + ((cL)localObject1).jdField_b_of_type_OrgSchemaGameCommonDataElementElementInformation.getName() + "\nwith " + cv.A.b() : "") + (((cL)localObject1).jdField_b_of_type_Boolean ? "\nselect with " + cv.A.b() : "");((cL)localObject1).jdField_a_of_type_Ct.a().a(str2); } else { ((cL)localObject1).jdField_a_of_type_Ct.a().b(); } } } } catch (Exception localException) { Object localObject3; (localObject3 = localException).printStackTrace();System.err.println("[BUILDMODEDRAWER] " + localObject3.getClass().getSimpleName() + ": " + ((Exception)localObject3).getMessage());
/* 1071:     */      }
/* 1072:     */    }
/* 1073:1068 */    this.jdField_a_of_type_Do.a(paramxq);
/* 1074:     */    
/* 1075:1070 */    h.b("update");
/* 1076:     */    
/* 1077:1072 */    if (h.a("update") > 15L)
/* 1078:1073 */      System.err.println("[DRAWER][WARNING] update took " + h.a("update") + " ms");
/* 1079:     */  }
/* 1080:     */  
/* 1081:     */  private void u() {
/* 1082:1077 */    long l1 = System.currentTimeMillis();
/* 1083:1078 */    this.jdField_a_of_type_EG.d();
/* 1084:1079 */    this.jdField_a_of_type_EJ.d();
/* 1085:     */    
/* 1086:     */    long l2;
/* 1087:     */    
/* 1088:1083 */    if ((l2 = System.currentTimeMillis() - l1) > 5L) {
/* 1089:1084 */      System.err.println("[WORLDDRAWER] WARNING: CLEAR TOOK " + l2);
/* 1090:     */    }
/* 1091:1086 */    this.jdField_a_of_type_Do.a(this.jdField_a_of_type_Ct.a());
/* 1092:1087 */    l1 = System.currentTimeMillis();
/* 1093:1088 */    this.jdField_a_of_type_Eu.a(this.jdField_a_of_type_Ct.a());
/* 1094:1089 */    for (mF localmF : this.jdField_a_of_type_Ct.a().values()) {
/* 1095:1090 */      long l4 = System.currentTimeMillis();
/* 1096:     */      long l6;
/* 1097:1092 */      if ((localmF instanceof kd)) {
/* 1098:1093 */        l6 = System.currentTimeMillis();
/* 1099:     */        
/* 1100:1095 */        long l8 = System.currentTimeMillis();
/* 1101:1096 */        Object localObject = new er((kd)localmF);this.jdField_a_of_type_EG.jdField_a_of_type_JavaUtilArrayList.add(localObject);
/* 1102:     */        long l7;
/* 1103:1098 */        if ((l7 = System.currentTimeMillis() - l8) > 3L) {
/* 1104:1099 */          System.err.println("[BEAMDRAWER] WARNING SHIP PLUM ADD of " + localmF + " took " + l7 + " ms");
/* 1105:     */        }
/* 1106:     */        
/* 1107:1102 */        l8 = System.currentTimeMillis();
/* 1108:1103 */        localObject = new eB((kd)localmF);this.jdField_a_of_type_EG.b.add(localObject);
/* 1109:     */        
/* 1110:1105 */        if ((l7 = System.currentTimeMillis() - l8) > 3L) {
/* 1111:1106 */          System.err.println("[BEAMDRAWER] WARNING SHIP MUZZLE ADD of " + localmF + " took " + l7 + " ms");
/* 1112:     */        }
/* 1113:1108 */        l8 = System.currentTimeMillis();
/* 1114:1109 */        this.jdField_a_of_type_EJ.a((kd)localmF);
/* 1115:     */        
/* 1116:1111 */        if ((l7 = System.currentTimeMillis() - l8) > 3L) {
/* 1117:1112 */          System.err.println("[BEAMDRAWER] WARNING SHIP SHIELD ADD of " + localmF + " took " + l7 + " ms");
/* 1118:     */        }
/* 1119:     */        
/* 1120:     */        long l9;
/* 1121:1116 */        if ((l9 = System.currentTimeMillis() - l6) > 5L) {
/* 1122:1117 */          System.err.println("[BEAMDRAWER] WARNING SHIP NORMAL ADD of " + localmF + " took " + l9 + " ms");
/* 1123:     */        }
/* 1124:     */      }
/* 1125:1120 */      if (((localmF instanceof ki)) || ((localmF instanceof mM))) {
/* 1126:1121 */        this.jdField_a_of_type_EJ.a((ld)localmF);
/* 1127:1122 */        this.jdField_a_of_type_Eu.a((ld)localmF);
/* 1128:     */      }
/* 1129:     */      
/* 1133:1128 */      if ((l6 = System.currentTimeMillis() - l4) > 5L) {
/* 1134:1129 */        System.err.println("[WORLDDRAWER] WARNING: DRAWER UPDATE OF " + localmF + " took " + l6);
/* 1135:     */      }
/* 1136:     */    }
/* 1137:     */    long l3;
/* 1138:1133 */    if ((l3 = System.currentTimeMillis() - l1) > 5L) {
/* 1139:1134 */      System.err.println("[WORLDDRAWER] WARNING: ADD TOOK " + l3);
/* 1140:     */    }
/* 1141:     */    
/* 1143:1138 */    l1 = System.currentTimeMillis();
/* 1144:     */    try {
/* 1145:1140 */      this.jdField_a_of_type_Ep.d();
/* 1146:     */    } catch (ErrorDialogException localErrorDialogException2) { ErrorDialogException localErrorDialogException1;
/* 1147:1142 */      (localErrorDialogException1 = 
/* 1148:     */      
/* 1149:1144 */        localErrorDialogException2).printStackTrace();xm.a(localErrorDialogException1);
/* 1150:     */    }
/* 1151:     */    long l5;
/* 1152:1146 */    if ((l5 = System.currentTimeMillis() - l1) > 5L) {
/* 1153:1147 */      System.err.println("[WORLDDRAWER] WARNING: CONNECTION UPDATE TOOK " + l5);
/* 1154:     */    }
/* 1155:     */  }
/* 1156:     */  
/* 1158:     */  public final ep a()
/* 1159:     */  {
/* 1160:1154 */    return this.jdField_a_of_type_Ep;
/* 1161:     */  }
/* 1162:     */  
/* 1170:     */  public final dF a()
/* 1171:     */  {
/* 1172:1166 */    return this.jdField_a_of_type_DF;
/* 1173:     */  }
/* 1174:     */  
/* 1176:     */  public final void k()
/* 1177:     */  {
/* 1178:1172 */    this.h = true;
/* 1179:     */  }
/* 1180:     */  
/* 1182:     */  public final eG a()
/* 1183:     */  {
/* 1184:1178 */    return this.jdField_a_of_type_EG;
/* 1185:     */  }
/* 1186:     */  
/* 1188:     */  public final eN a()
/* 1189:     */  {
/* 1190:1184 */    return this.jdField_a_of_type_EN;
/* 1191:     */  }
/* 1192:     */  
/* 1194:     */  public final eu a()
/* 1195:     */  {
/* 1196:1190 */    return this.jdField_a_of_type_Eu;
/* 1197:     */  }
/* 1198:     */  
/* 1206:     */  public final void l()
/* 1207:     */  {
/* 1208:1202 */    this.i = true;
/* 1209:     */  }
/* 1210:     */  
/* 1219:     */  public final void m()
/* 1220:     */  {
/* 1221:1215 */    this.g = true;
/* 1222:     */  }
/* 1223:     */  
/* 1224:     */  public final void a() {}
/* 1225:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     dj
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */