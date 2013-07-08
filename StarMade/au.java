/*   1:    */import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
/*   2:    */import com.bulletphysics.linearmath.Transform;
/*   3:    */import it.unimi.dsi.fastutil.longs.LongSet;
/*   4:    */import java.io.PrintStream;
/*   5:    */import java.util.ArrayList;
/*   6:    */import java.util.Iterator;
/*   7:    */import java.util.List;
/*   8:    */import javax.vecmath.Matrix4f;
/*   9:    */import javax.vecmath.Vector3f;
/*  10:    */import org.lwjgl.input.Keyboard;
/*  11:    */import org.schema.common.FastMath;
/*  12:    */import org.schema.game.common.controller.CannotBeControlledException;
/*  13:    */import org.schema.game.common.controller.CannotImmediateRequestOnClientException;
/*  14:    */import org.schema.game.common.controller.EditableSendableSegmentController;
/*  15:    */import org.schema.game.common.controller.SegmentBufferManager;
/*  16:    */import org.schema.game.common.controller.SegmentController;
/*  17:    */import org.schema.game.common.controller.elements.ElementCollectionManager;
/*  18:    */import org.schema.game.common.controller.elements.ManagerContainer;
/*  19:    */import org.schema.game.common.controller.elements.ManagerModule;
/*  20:    */import org.schema.game.common.controller.elements.UsableControllableElementManager;
/*  21:    */import org.schema.game.common.controller.elements.UsableControllableSingleElementManager;
/*  22:    */import org.schema.game.common.data.element.ControlElementMap;
/*  23:    */import org.schema.game.common.data.element.ControlElementMapper;
/*  24:    */import org.schema.game.common.data.element.ElementCollection;
/*  25:    */import org.schema.game.common.data.physics.CubeRayCastResult;
/*  26:    */import org.schema.game.common.data.physics.PhysicsExt;
/*  27:    */import org.schema.game.common.data.world.Segment;
/*  28:    */import org.schema.game.common.data.world.SegmentData;
/*  29:    */import org.schema.schine.graphicsengine.camera.Camera;
/*  30:    */import org.schema.schine.graphicsengine.core.GlUtil;
/*  31:    */
/*  79:    */public class au
/*  80:    */  extends af
/*  81:    */{
/*  82:    */  private boolean jdField_d_of_type_Boolean;
/*  83: 83 */  private final az jdField_a_of_type_Az = new az();
/*  84:    */  
/*  86:    */  private dr jdField_a_of_type_Dr;
/*  87:    */  
/*  89:    */  private al jdField_a_of_type_Al;
/*  90:    */  
/*  92:    */  private le jdField_a_of_type_Le;
/*  93:    */  
/*  95:    */  private CollisionWorld.ClosestRayResultCallback jdField_a_of_type_ComBulletphysicsCollisionDispatchCollisionWorld$ClosestRayResultCallback;
/*  96:    */  
/*  98:    */  private q jdField_a_of_type_Q;
/*  99:    */  
/* 101:    */  private boolean e;
/* 102:    */  
/* 104:    */  private ArrayList jdField_a_of_type_JavaUtilArrayList;
/* 105:    */  
/* 107:    */  private int jdField_a_of_type_Int;
/* 108:    */  
/* 110:    */  private Segment jdField_a_of_type_OrgSchemaGameCommonDataWorldSegment;
/* 111:    */  
/* 113:    */  private boolean f;
/* 114:    */  
/* 115:    */  private SegmentController jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController;
/* 116:    */  
/* 117:    */  private long jdField_b_of_type_Long;
/* 118:    */  
/* 119:    */  private ju jdField_a_of_type_Ju;
/* 120:    */  
/* 121:    */  private int jdField_b_of_type_Int;
/* 122:    */  
/* 123:    */  private int c;
/* 124:    */  
/* 125:    */  private int jdField_d_of_type_Int;
/* 126:    */  
/* 127:    */  private ElementCollectionManager jdField_a_of_type_OrgSchemaGameCommonControllerElementsElementCollectionManager;
/* 128:    */  
/* 129:    */  private ArrayList jdField_b_of_type_JavaUtilArrayList;
/* 130:    */  
/* 132:    */  public au(ct paramct, al paramal)
/* 133:    */  {
/* 134:134 */    super(paramct);new Matrix4f();new Matrix4f();this.jdField_a_of_type_Q = new q();this.jdField_a_of_type_JavaUtilArrayList = new ArrayList();
/* 135:    */    
/* 189:189 */    this.jdField_b_of_type_Int = 0;
/* 190:190 */    this.c = 0;
/* 191:191 */    this.jdField_d_of_type_Int = 0;this.jdField_a_of_type_Al = paramal;
/* 192:    */  }
/* 193:    */  
/* 237:    */  public final SegmentController a()
/* 238:    */  {
/* 239:183 */    return this.jdField_a_of_type_Al.a();
/* 240:    */  }
/* 241:    */  
/* 242:186 */  public final le a() { return this.jdField_a_of_type_Le; }
/* 243:    */  
/* 246:    */  private void a(int paramInt, boolean paramBoolean)
/* 247:    */  {
/* 248:    */    ld localld;
/* 249:    */    
/* 250:    */    Object localObject;
/* 251:    */    
/* 252:    */    for (;;)
/* 253:    */    {
/* 254:198 */      localld = (ld)a();
/* 255:199 */      if (paramInt == -2147483648) {
/* 256:200 */        a().a().a().a(null, null);
/* 257:201 */        return;
/* 258:    */      }
/* 259:203 */      if (paramBoolean) {
/* 260:204 */        this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsElementCollectionManager = null;
/* 261:205 */        this.jdField_b_of_type_Int = 0;
/* 262:    */      }
/* 263:    */      
/* 264:208 */      if (this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsElementCollectionManager != null) break;
/* 265:209 */      this.c = FastMath.b(this.c + paramInt, localld.a().getModules().size());
/* 266:    */      
/* 267:211 */      if ((this.jdField_b_of_type_JavaUtilArrayList != null) && (this.jdField_d_of_type_Int < this.jdField_b_of_type_JavaUtilArrayList.size()) && (!paramBoolean)) {
/* 268:212 */        this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsElementCollectionManager = ((ElementCollectionManager)this.jdField_b_of_type_JavaUtilArrayList.get(this.jdField_d_of_type_Int));
/* 269:213 */        this.jdField_d_of_type_Int += 1;
/* 270:    */      } else {
/* 271:215 */        this.jdField_d_of_type_Int = 0;
/* 272:216 */        this.jdField_b_of_type_JavaUtilArrayList = null;
/* 273:    */        
/* 276:220 */        if (((localObject = (ManagerModule)localld.a().getModules().get(this.c)).getElementManager() instanceof UsableControllableElementManager)) {
/* 277:221 */          localObject = (UsableControllableElementManager)((ManagerModule)localObject).getElementManager();
/* 278:222 */          this.jdField_b_of_type_JavaUtilArrayList = ((UsableControllableElementManager)localObject).getCollectionManagers();
/* 279:223 */          if (((UsableControllableElementManager)localObject).getCollectionManagers().size() > 0) {
/* 280:224 */            this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsElementCollectionManager = ((ElementCollectionManager)((UsableControllableElementManager)localObject).getCollectionManagers().get(0));
/* 281:    */            break label260; }
/* 282:226 */          this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsElementCollectionManager = null;
/* 283:227 */          continue; }
/* 284:228 */        if ((((ManagerModule)localObject).getElementManager() instanceof UsableControllableSingleElementManager))
/* 285:    */        {
/* 287:231 */          localObject = (UsableControllableSingleElementManager)((ManagerModule)localObject).getElementManager();
/* 288:232 */          this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsElementCollectionManager = ((UsableControllableSingleElementManager)localObject).getCollection();
/* 289:    */        }
/* 290:    */      }
/* 291:    */      label260:
/* 292:236 */      if ((this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsElementCollectionManager == null) || (this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsElementCollectionManager.getCollection().size() != 0)) break;
/* 293:237 */      this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsElementCollectionManager = null;
/* 294:    */    }
/* 295:239 */    if ((this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsElementCollectionManager != null) && (this.jdField_b_of_type_Int < this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsElementCollectionManager.getCollection().size()) && (this.jdField_b_of_type_Int >= 0))
/* 296:    */    {
/* 301:245 */      localObject = (ElementCollection)this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsElementCollectionManager.getCollection().get(this.jdField_b_of_type_Int);
/* 302:246 */      if (Keyboard.isKeyDown(71)) {
/* 303:247 */        a().a().a().a(this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsElementCollectionManager.rawCollection, (mF)localld);
/* 304:    */      } else {
/* 305:249 */        a().a().a().a(((ElementCollection)localObject).getNeighboringCollection(), (mF)localld);
/* 306:    */      }
/* 307:251 */      this.jdField_b_of_type_Int = FastMath.b(this.jdField_b_of_type_Int + paramInt, localld.a().getModules().size());
/* 308:    */      
/* 309:253 */      return; }
/* 310:254 */    this.jdField_b_of_type_Int = 0;
/* 311:255 */    this.jdField_a_of_type_OrgSchemaGameCommonControllerElementsElementCollectionManager = null;
/* 312:    */  }
/* 313:    */  
/* 314:    */  public void handleKeyEvent()
/* 315:    */  {
/* 316:260 */    if (Keyboard.getEventKeyState()) {
/* 317:261 */      q localq = null;Keyboard.getEventKey(); if (a().a().a.a.a.a())
/* 318:    */      {
/* 319:263 */        return;
/* 320:    */      }
/* 321:265 */      if (Keyboard.getEventKey() == 73) {
/* 322:266 */        a(1, false);
/* 323:    */      }
/* 324:268 */      if (Keyboard.getEventKey() == 72) {
/* 325:269 */        a(-1, false);
/* 326:    */      }
/* 327:271 */      if (Keyboard.getEventKey() == 77) {
/* 328:272 */        a(1, true);
/* 329:    */      }
/* 330:274 */      if (Keyboard.getEventKey() == 76) {
/* 331:275 */        a(-1, true);
/* 332:    */      }
/* 333:277 */      if (Keyboard.getEventKey() == 80) {
/* 334:278 */        a(-2147483648, true);
/* 335:    */      }
/* 336:    */      
/* 337:281 */      if (Keyboard.getEventKey() == cv.B.a()) {
/* 338:282 */        this.jdField_d_of_type_Boolean = (!this.jdField_d_of_type_Boolean); } else { au localau;
/* 339:283 */        if (Keyboard.getEventKey() == cv.z.a()) {
/* 340:284 */          localau = this; if ((this.e) && (localau.jdField_a_of_type_Q != null)) if ((localau.jdField_a_of_type_Le != null) && (localau.jdField_a_of_type_Le.a(new q()).equals(localau.jdField_a_of_type_Q))) localau.jdField_a_of_type_Le = null; else try { localau.jdField_a_of_type_Le = localau.jdField_a_of_type_Al.a().getSegmentBuffer().a(localau.jdField_a_of_type_Q, true); } catch (CannotImmediateRequestOnClientException localCannotImmediateRequestOnClientException1) { localCannotImmediateRequestOnClientException1;
/* 341:285 */              } } else if ((!Keyboard.isKeyDown(42)) && (Keyboard.getEventKey() == cv.R.a())) {
/* 342:286 */          f(true);
/* 343:287 */        } else if ((!Keyboard.isKeyDown(42)) && (Keyboard.getEventKey() == cv.S.a())) {
/* 344:288 */          f(false);
/* 345:289 */        } else if ((!Keyboard.isKeyDown(42)) && (Keyboard.getEventKey() == cv.T.a())) {
/* 346:290 */          localau = this; if (!this.jdField_a_of_type_JavaUtilArrayList.isEmpty()) try { localau.jdField_a_of_type_Le = localau.jdField_a_of_type_Al.a().getSegmentBuffer().a(localau.jdField_a_of_type_Al.a(), true);localau.jdField_a_of_type_Int = Math.max(0, localau.jdField_a_of_type_JavaUtilArrayList.indexOf(localau.jdField_a_of_type_Al.a())); } catch (CannotImmediateRequestOnClientException localCannotImmediateRequestOnClientException2) { localCannotImmediateRequestOnClientException2;
/* 347:291 */            } } else if ((!Keyboard.isKeyDown(42)) && (Keyboard.getEventKey() == cv.t.a())) {
/* 348:292 */          this.jdField_a_of_type_Dr.a(new q(8, 8, 0));
/* 349:293 */        } else if ((!Keyboard.isKeyDown(42)) && (Keyboard.getEventKey() == cv.A.a())) {
/* 350:294 */          localau = this;System.err.println("[CLIENT][SEGBUILDCONTROLLER] NORMAL CONNECTION"); try { localau.jdField_a_of_type_Al.a().setCurrentBlockController(localau.jdField_a_of_type_Le, localau.jdField_a_of_type_Q); } catch (CannotBeControlledException localCannotBeControlledException1) { localau.a(localCannotBeControlledException1);
/* 351:295 */          } } else if ((Keyboard.isKeyDown(42)) && (Keyboard.getEventKey() == cv.A.a())) {
/* 352:296 */          localau = this;System.err.println("[CLIENT][SEGBUILDCONTROLLER] BULK CONNECTION"); try { Object localObject = ((SegmentBufferManager)localau.jdField_a_of_type_Al.a().getSegmentBuffer()).a(localau.jdField_a_of_type_Q);new q(); if ((localau.jdField_a_of_type_Le != null) && (localObject != null) && (((jz)localObject).jdField_a_of_type_JavaUtilArrayList.size() > 0)) { System.err.println("[CLIENT][SEGBUILDCONTROLLER] BULK CONNECTING " + ((jz)localObject).jdField_a_of_type_JavaUtilArrayList.size() + " elements of type " + ((jz)localObject).jdField_a_of_type_Short);boolean bool = localau.jdField_a_of_type_Al.a().getControlElementMap().isControlling(localau.jdField_a_of_type_Le.a(new q()), localau.jdField_a_of_type_Q, ((jz)localObject).jdField_a_of_type_Short); int i; for (localObject = ((jz)localObject).jdField_a_of_type_JavaUtilArrayList.iterator(); ((Iterator)localObject).hasNext(); localau.jdField_a_of_type_Al.a().setCurrentBlockController(localau.jdField_a_of_type_Le, localq, i)) { localq = (q)((Iterator)localObject).next();i = bool ? 2 : 1; } } } catch (CannotBeControlledException localCannotBeControlledException2) { localau.a(localCannotBeControlledException2);
/* 353:    */          } } }
/* 354:298 */      if (cv.U.a())
/* 355:    */      {
/* 357:301 */        a().a().a.a.a.b();
/* 358:    */      }
/* 359:    */    }
/* 360:    */    
/* 363:307 */    this.jdField_a_of_type_Dr.handleKeyEvent();
/* 364:    */  }
/* 365:    */  
/* 366:    */  public final void a(xp paramxp)
/* 367:    */  {
/* 368:312 */    if (System.currentTimeMillis() - a().a().a.a.a.jdField_a_of_type_Long < 300L)
/* 369:    */    {
/* 370:314 */      return;
/* 371:    */    }
/* 372:    */    
/* 374:318 */    if ((this.jdField_a_of_type_Al.a() != null) && (xe.a() != null) && (!this.jdField_a_of_type_Boolean))
/* 375:    */    {
/* 376:320 */      int i = xu.a.b() ? 1 : 0;
/* 377:321 */      int j = xu.a.b() ? 0 : 1;
/* 378:    */      Vector3f localVector3f1;
/* 379:323 */      Vector3f localVector3f2; if (paramxp.jdField_a_of_type_Int == i) {
/* 380:324 */        localVector3f1 = new Vector3f(xe.a().a());
/* 381:325 */        localVector3f2 = new Vector3f(xe.a().c());
/* 382:    */        
/* 383:327 */        if (Keyboard.isKeyDown(cv.U.a()))
/* 384:    */        {
/* 385:329 */          (localVector3f2 = new Vector3f(a().a().b())).sub(localVector3f1);
/* 386:    */        }
/* 387:331 */        int k = 0;
/* 388:332 */        if (paramxp.jdField_a_of_type_Boolean) {
/* 389:333 */          this.jdField_a_of_type_Ju = null;
/* 390:334 */          this.jdField_b_of_type_Long = System.currentTimeMillis();
/* 391:    */        } else {
/* 392:336 */          k = (this.jdField_b_of_type_Long > 0L) && (System.currentTimeMillis() - this.jdField_b_of_type_Long > 500L) ? 1 : 0;
/* 393:337 */          this.jdField_b_of_type_Long = 0L;
/* 394:    */        }
/* 395:    */        
/* 396:340 */        if ((!paramxp.jdField_a_of_type_Boolean) && (k == 0))
/* 397:    */        {
/* 399:343 */          a().a().a.a.a.a(this.jdField_a_of_type_Al.a(), localVector3f1, localVector3f2, new av(this), new ju(), this.jdField_a_of_type_Az, 160.0F);
/* 400:    */        }
/* 401:    */      }
/* 402:    */      
/* 459:403 */      if ((paramxp.jdField_a_of_type_Int == j) && (!paramxp.jdField_a_of_type_Boolean)) {
/* 460:404 */        localVector3f1 = new Vector3f(xe.a().a());
/* 461:405 */        localVector3f2 = new Vector3f(xe.a().c());
/* 462:    */        
/* 463:407 */        if (Keyboard.isKeyDown(cv.U.a()))
/* 464:    */        {
/* 465:409 */          (localVector3f2 = new Vector3f(a().a().b())).sub(localVector3f1);
/* 466:    */        }
/* 467:    */        
/* 468:412 */        a().a().a.a.a.a(this.jdField_a_of_type_Al.a(), localVector3f1, localVector3f2, 160.0F, this.jdField_a_of_type_Az);
/* 469:    */      }
/* 470:    */    }
/* 471:    */  }
/* 472:    */  
/* 489:    */  public final void b(boolean paramBoolean)
/* 490:    */  {
/* 491:435 */    if (paramBoolean)
/* 492:    */    {
/* 494:438 */      if (this.jdField_a_of_type_Al.a() != this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController) {
/* 495:439 */        this.jdField_a_of_type_Le = null;
/* 496:    */        
/* 497:441 */        this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController = this.jdField_a_of_type_Al.a();
/* 498:    */      }
/* 499:    */      
/* 503:447 */      this.jdField_a_of_type_Al.a().getControlElementMap().setObs(this);
/* 504:    */      
/* 506:450 */      this.jdField_a_of_type_JavaUtilArrayList.clear();
/* 507:451 */      this.f = true;
/* 508:    */      
/* 513:457 */      if ((this.jdField_a_of_type_Dr == null) || (((xb)this.jdField_a_of_type_Dr.a()).a() != this.jdField_a_of_type_Al.a())) {
/* 514:458 */        if (this.jdField_a_of_type_Al.a() != null) {
/* 515:459 */          Transform localTransform = new Transform(this.jdField_a_of_type_Al.a().getWorldTransform());
/* 516:460 */          GlUtil.d(new Vector3f(0.0F, 1.0F, 0.0F), localTransform);
/* 517:461 */          GlUtil.c(new Vector3f(1.0F, 0.0F, 0.0F), localTransform);
/* 518:462 */          GlUtil.a(new Vector3f(0.0F, 0.0F, 1.0F), localTransform);
/* 519:    */          
/* 527:471 */          a();this.jdField_a_of_type_Dr = new dr(xe.a(), this.jdField_a_of_type_Al);
/* 528:    */          
/* 530:474 */          this.jdField_a_of_type_Dr.a(new q(8, 8, 0));
/* 531:    */          
/* 532:476 */          this.jdField_a_of_type_Dr.a(0.0F);
/* 535:    */        }
/* 536:480 */        else if (this.jdField_a_of_type_Dr != null) {
/* 537:481 */          this.jdField_a_of_type_Dr.a(xe.a());
/* 538:    */        }
/* 539:483 */      } else if (this.jdField_a_of_type_Dr != null) {
/* 540:484 */        this.jdField_a_of_type_Dr.a(xe.a());
/* 541:    */      }
/* 542:486 */      if (this.jdField_a_of_type_Al.a() != null)
/* 543:    */      {
/* 544:488 */        a().a();dj.a(this.jdField_a_of_type_Al.a());
/* 545:    */      }
/* 546:490 */      a().a().c("FlightMode");
/* 547:491 */      a().a().a("BuildMode", "Build Mode", "(press " + cv.r.b() + " to switch to FLIGHT MODE; press " + cv.v.b() + " to exit structure)");
/* 548:492 */      if ((!g) && (this.jdField_a_of_type_Al.a() == null)) { throw new AssertionError();
/* 549:    */      }
/* 550:    */      
/* 551:495 */      xe.a(this.jdField_a_of_type_Dr);
/* 552:    */    }
/* 553:497 */    super.b(paramBoolean);
/* 554:    */  }
/* 555:    */  
/* 586:    */  private void f(boolean paramBoolean)
/* 587:    */  {
/* 588:532 */    if (this.jdField_a_of_type_JavaUtilArrayList.isEmpty()) {
/* 589:533 */      return;
/* 590:    */    }
/* 591:535 */    this.jdField_a_of_type_Int = FastMath.b(this.jdField_a_of_type_Int + (paramBoolean ? 1 : -1), this.jdField_a_of_type_JavaUtilArrayList.size() - 1);
/* 592:536 */    System.err.println("SWITCH " + paramBoolean + " " + this.jdField_a_of_type_Int);
/* 593:    */    try
/* 594:    */    {
/* 595:539 */      this.jdField_a_of_type_Le = this.jdField_a_of_type_Al.a().getSegmentBuffer().a((q)this.jdField_a_of_type_JavaUtilArrayList.get(this.jdField_a_of_type_Int), true); return;
/* 596:540 */    } catch (CannotImmediateRequestOnClientException localCannotImmediateRequestOnClientException) { 
/* 597:    */      
/* 598:542 */        localCannotImmediateRequestOnClientException;
/* 599:    */    }
/* 600:    */  }
/* 601:    */  
/* 627:    */  public final void a(xq paramxq)
/* 628:    */  {
/* 629:571 */    if (this.jdField_a_of_type_Le != null) {
/* 630:572 */      this.jdField_a_of_type_Le.a();
/* 631:573 */      if (this.jdField_a_of_type_Le.a() == 0) {
/* 632:574 */        this.jdField_a_of_type_Le = null;
/* 633:    */      }
/* 634:    */    }
/* 635:577 */    if (this.f)
/* 636:    */    {
/* 637:579 */      this.jdField_a_of_type_JavaUtilArrayList.clear();
/* 638:580 */      for (paramxq = this.jdField_a_of_type_Al.a().getControlElementMap().getControllingMap().keySet().iterator(); paramxq.hasNext();) { long l = ((Long)paramxq.next()).longValue();
/* 639:581 */        this.jdField_a_of_type_JavaUtilArrayList.add(ElementCollection.getPosFromIndex(l, new q()));
/* 640:    */      }
/* 641:    */      
/* 642:584 */      this.f = false; }
/* 643:    */    Vector3f localVector3f1;
/* 644:586 */    if ((this.jdField_b_of_type_Long > 0L) && (Keyboard.isKeyDown(cv.U.a())) && (System.currentTimeMillis() - this.jdField_b_of_type_Long > 500L)) {
/* 645:587 */      paramxq = new Vector3f(xe.a().a());
/* 646:588 */      (
/* 647:589 */        localVector3f1 = new Vector3f(a().a().b())).sub(paramxq);
/* 648:    */      
/* 653:595 */      a().a().a.a.a.a(this.jdField_a_of_type_Al.a(), paramxq, localVector3f1, new aw(this), this.jdField_a_of_type_Ju, this.jdField_a_of_type_Az, 160.0F);
/* 654:    */    }
/* 655:    */    
/* 705:647 */    wV.jdField_a_of_type_Boolean = !Keyboard.isKeyDown(cv.U.a());
/* 706:    */    
/* 711:653 */    if (this.jdField_a_of_type_Al.a() != null) {
/* 712:654 */      paramxq = this;Object localObject = new Vector3f(xe.a().a());localVector3f1 = new Vector3f(xe.a().c());Vector3f localVector3f2 = new Vector3f((Vector3f)localObject);localVector3f1.scale(160.0F);localVector3f2.add(localVector3f1); if (Keyboard.isKeyDown(cv.U.a())) localVector3f2 = new Vector3f(paramxq.a().a().b()); paramxq.jdField_a_of_type_ComBulletphysicsCollisionDispatchCollisionWorld$ClosestRayResultCallback = ((PhysicsExt)paramxq.a().a()).testRayCollisionPoint((Vector3f)localObject, localVector3f2, false, null, paramxq.jdField_a_of_type_Al.a(), false, paramxq.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegment, true); if ((paramxq.jdField_a_of_type_ComBulletphysicsCollisionDispatchCollisionWorld$ClosestRayResultCallback != null) && (paramxq.jdField_a_of_type_ComBulletphysicsCollisionDispatchCollisionWorld$ClosestRayResultCallback.hasHit()) && ((paramxq.jdField_a_of_type_ComBulletphysicsCollisionDispatchCollisionWorld$ClosestRayResultCallback instanceof CubeRayCastResult))) { if (((localObject = (CubeRayCastResult)paramxq.jdField_a_of_type_ComBulletphysicsCollisionDispatchCollisionWorld$ClosestRayResultCallback).getSegment() != null) && (((CubeRayCastResult)localObject).cubePos != null)) { ((CubeRayCastResult)localObject).getSegment().a(((CubeRayCastResult)localObject).cubePos, paramxq.jdField_a_of_type_Q);((CubeRayCastResult)localObject).getSegment().a().getType(((CubeRayCastResult)localObject).cubePos);paramxq.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegment = ((CubeRayCastResult)localObject).getSegment();paramxq.e = true;return; } paramxq.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegment = null;paramxq.e = false;return; } paramxq.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegment = null;paramxq.e = false;
/* 713:    */    }
/* 714:    */  }
/* 715:    */  
/* 757:    */  public final void b()
/* 758:    */  {
/* 759:701 */    this.f = true;
/* 760:    */  }
/* 761:    */  
/* 765:    */  public final az a()
/* 766:    */  {
/* 767:709 */    return this.jdField_a_of_type_Az;
/* 768:    */  }
/* 769:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     au
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */