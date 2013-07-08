/*   1:    */import com.bulletphysics.dynamics.RigidBody;
/*   2:    */import java.util.List;
/*   3:    */import javax.vecmath.Vector3f;
/*   4:    */import org.lwjgl.input.Mouse;
/*   5:    */import org.schema.game.common.controller.elements.ElementCollectionManager;
/*   6:    */import org.schema.game.common.controller.elements.ManagerModuleSingle;
/*   7:    */import org.schema.game.common.controller.elements.ShipManagerContainer;
/*   8:    */import org.schema.game.common.controller.elements.thrust.ThrusterCollectionManager;
/*   9:    */import org.schema.game.common.controller.elements.thrust.ThrusterElementManager;
/*  10:    */import org.schema.game.network.objects.NetworkSector;
/*  11:    */import org.schema.schine.graphicsengine.core.GlUtil;
/*  12:    */import org.schema.schine.network.client.ClientState;
/*  13:    */import org.schema.schine.network.objects.container.PhysicsDataContainer;
/*  14:    */import org.schema.schine.network.objects.remote.RemoteVector3i;
/*  15:    */
/*  84:    */public class fg
/*  85:    */  extends yz
/*  86:    */{
/*  87:    */  private fW jdField_a_of_type_FW;
/*  88:    */  private gW jdField_a_of_type_GW;
/*  89:    */  private iw jdField_a_of_type_Iw;
/*  90:    */  private hO jdField_a_of_type_HO;
/*  91:    */  private fr jdField_a_of_type_Fr;
/*  92:    */  private hV jdField_a_of_type_HV;
/*  93:    */  private iD jdField_a_of_type_ID;
/*  94:    */  private eR jdField_a_of_type_ER;
/*  95:    */  private iH jdField_a_of_type_IH;
/*  96:    */  private yr jdField_a_of_type_Yr;
/*  97:    */  private yr b;
/*  98:    */  private yr c;
/*  99:    */  private yr d;
/* 100:    */  private yr e;
/* 101:    */  private in jdField_a_of_type_In;
/* 102:    */  private yr f;
/* 103:    */  private yr g;
/* 104:    */  private fw jdField_a_of_type_Fw;
/* 105:    */  private yr h;
/* 106:    */  
/* 107:    */  public fg(ClientState paramClientState)
/* 108:    */  {
/* 109:109 */    super(paramClientState);
/* 110:    */  }
/* 111:    */  
/* 186:    */  public final void e()
/* 187:    */  {
/* 188:188 */    this.jdField_a_of_type_ID.e();
/* 189:    */  }
/* 190:    */  
/* 191:    */  private boolean b() {
/* 192:192 */    return ((ct)super.a()).a().a.a.jdField_a_of_type_V.c;
/* 193:    */  }
/* 194:    */  
/* 195:    */  private boolean c() {
/* 196:196 */    fg localfg = this;return (localfg.a().jdField_a_of_type_Au.c ? localfg.a().jdField_a_of_type_Au : a().a().c ? localfg.a().a() : null) != null;
/* 197:    */  }
/* 198:    */  
/* 205:    */  public final void a() {}
/* 206:    */  
/* 212:    */  protected final void d()
/* 213:    */  {
/* 214:214 */    this.jdField_a_of_type_Fr.h(48);
/* 215:215 */    this.jdField_a_of_type_HO.h(48);
/* 216:216 */    this.jdField_a_of_type_ID.h(48);
/* 217:217 */    this.jdField_a_of_type_HV.h(48);
/* 218:218 */    this.jdField_a_of_type_Iw.h(48);
/* 219:219 */    this.jdField_a_of_type_ER.h(40);
/* 220:220 */    this.jdField_a_of_type_IH.h(40);
/* 221:    */  }
/* 222:    */  
/* 223:    */  public final void b() {
/* 224:224 */    if (k()) {
/* 225:225 */      d();
/* 226:    */    }
/* 227:227 */    yz.i();
/* 228:228 */    this.jdField_a_of_type_In.b();
/* 229:229 */    eR localeR; if ((e()) || (n())) {
/* 230:230 */      localeR = null;this.jdField_a_of_type_ER.a(true);
/* 231:    */    } else {
/* 232:232 */      localeR = null;this.jdField_a_of_type_ER.a(false);
/* 233:    */    }
/* 234:234 */    if (e()) {
/* 235:235 */      this.jdField_a_of_type_HO.b();
/* 236:    */    }
/* 237:237 */    if (b()) {
/* 238:238 */      this.jdField_a_of_type_Fr.b();
/* 239:    */    }
/* 240:240 */    if (n()) {
/* 241:241 */      this.jdField_a_of_type_Iw.b();
/* 242:    */    }
/* 243:243 */    if (f()) {
/* 244:244 */      this.jdField_a_of_type_HV.b();
/* 245:    */    }
/* 246:246 */    if (o()) {
/* 247:247 */      this.jdField_a_of_type_GW.b();
/* 248:    */    }
/* 249:249 */    if (p()) {
/* 250:250 */      this.jdField_a_of_type_FW.b();
/* 251:    */    }
/* 252:252 */    if (m()) {
/* 253:253 */      this.jdField_a_of_type_ID.b();
/* 254:    */    }
/* 255:    */    
/* 257:257 */    if ((d()) && (!((ct)super.a()).a().a.a.jdField_a_of_type_Aa.c)) {
/* 258:258 */      if (((((ct)super.a()).a().a.a.jdField_a_of_type_Ar.a().a().jdField_a_of_type_Bl.c) || (m())) && (!n()) && (!e())) {
/* 259:259 */        this.jdField_a_of_type_IH.b();
/* 260:    */      }
/* 261:    */      else {
/* 262:262 */        this.jdField_a_of_type_ER.b();
/* 263:    */        
/* 264:264 */        if ((!n()) && (!e()) && (c())) {
/* 265:265 */          this.jdField_a_of_type_Fw.b();
/* 266:    */        }
/* 267:    */      }
/* 268:    */    }
/* 269:    */    
/* 271:271 */    if ((((ct)super.a()).getDragging() != null) && ((((ct)super.a()).getDragging() instanceof hR)))
/* 272:    */    {
/* 275:275 */      if (((localObject = (hR)((ct)super.a()).getDragging()).a() == 0) || (((hR)localObject).a(true) <= 0)) {
/* 276:276 */        ((ct)super.a()).setDragging(null);
/* 277:    */      } else {
/* 278:278 */        this.jdField_a_of_type_HO.a((hR)localObject);
/* 279:    */      }
/* 280:    */    }
/* 281:    */    
/* 282:282 */    Object localObject = this; if (d()) { yz.i();localeR = ((fg)localObject).jdField_a_of_type_ER; if (!Mouse.isGrabbed()) { GlUtil.d(); for (int i = 0; i < localeR.a.length; i++) localeR.a[i].e(); GlUtil.c(); } yz.h(); } if (((fg)localObject).e()) { yz.i();((fg)localObject).jdField_a_of_type_HO.e();yz.h(); }
/* 283:283 */    yz.h();
/* 284:    */  }
/* 285:    */  
/* 314:    */  public final float a()
/* 315:    */  {
/* 316:316 */    return 0.0F;
/* 317:    */  }
/* 318:    */  
/* 319:319 */  private ax a() { return ((ct)super.a()).a().a.a.jdField_a_of_type_Ar.a(); }
/* 320:    */  
/* 321:    */  private bj a() {
/* 322:322 */    return ((ct)super.a()).a().a.a.jdField_a_of_type_Ar.a().a();
/* 323:    */  }
/* 324:    */  
/* 325:    */  public final ct a() {
/* 326:326 */    return (ct)super.a();
/* 327:    */  }
/* 328:    */  
/* 329:    */  public final float b()
/* 330:    */  {
/* 331:331 */    return 0.0F;
/* 332:    */  }
/* 333:    */  
/* 334:    */  private boolean d() {
/* 335:335 */    return ((ct)super.a()).a().a.c;
/* 336:    */  }
/* 337:    */  
/* 338:338 */  private boolean e() { return ((ct)super.a()).a().a.a.jdField_a_of_type_An.c; }
/* 339:    */  
/* 340:    */  public final boolean a() {
/* 341:341 */    if ((c()) && (cv.U.a()) && (this.jdField_a_of_type_Fw.a_())) { return true;
/* 342:    */    }
/* 343:343 */    return false;
/* 344:    */  }
/* 345:    */  
/* 349:    */  private boolean f()
/* 350:    */  {
/* 351:351 */    return ((ct)super.a()).a().a.a.jdField_a_of_type_Bg.c;
/* 352:    */  }
/* 353:    */  
/* 354:    */  public final void c()
/* 355:    */  {
/* 356:356 */    fh localfh = new fh(this, (byte)0);
/* 357:    */    
/* 358:358 */    this.d = new yr((ct)super.a(), 39.0F, 26.0F);
/* 359:359 */    this.d.a = "X";
/* 360:360 */    this.d.g = true;
/* 361:361 */    this.d.a(localfh);
/* 362:362 */    this.d.a().set(804.0F, 4.0F, 0.0F);
/* 363:363 */    this.d.c();
/* 364:    */    
/* 365:365 */    this.jdField_a_of_type_Yr = new yr((ct)super.a(), 147.0F, 40.0F);
/* 366:366 */    this.jdField_a_of_type_Yr.a = "INVENTORY";
/* 367:367 */    this.jdField_a_of_type_Yr.g = true;
/* 368:368 */    this.jdField_a_of_type_Yr.a(localfh);
/* 369:369 */    this.jdField_a_of_type_Yr.a().set(216.0F, 26.0F, 0.0F);
/* 370:370 */    this.jdField_a_of_type_Yr.c();
/* 371:    */    
/* 372:372 */    this.f = new yr((ct)super.a(), 147.0F, 40.0F);
/* 373:373 */    this.f.a = "AI";
/* 374:374 */    this.f.g = true;
/* 375:375 */    this.f.a(localfh);
/* 376:376 */    this.f.a().set(662.0F, 472.0F, 0.0F);
/* 377:377 */    this.f.c();
/* 378:    */    
/* 379:379 */    this.g = new yr((ct)super.a(), 147.0F, 40.0F);
/* 380:380 */    this.g.a = "FACTION";
/* 381:381 */    this.g.g = true;
/* 382:382 */    this.g.a(localfh);
/* 383:383 */    this.g.a().set(517.0F, 472.0F, 0.0F);
/* 384:384 */    this.g.c();
/* 385:    */    
/* 386:386 */    this.b = new yr((ct)super.a(), 147.0F, 40.0F);
/* 387:387 */    this.b.a = "WEAPON";
/* 388:388 */    this.b.g = true;
/* 389:389 */    this.b.a(localfh);
/* 390:390 */    this.b.a().set(366.0F, 26.0F, 0.0F);
/* 391:391 */    this.b.c();
/* 392:    */    
/* 393:393 */    this.h = new yr((ct)super.a(), 147.0F, 40.0F);
/* 394:394 */    this.h.a = "CATALOG";
/* 395:395 */    this.h.g = true;
/* 396:396 */    this.h.a(localfh);
/* 397:397 */    this.h.a().set(366.0F, 472.0F, 0.0F);
/* 398:398 */    this.h.c();
/* 399:    */    
/* 401:401 */    this.c = new yr((ct)super.a(), 147.0F, 40.0F);
/* 402:402 */    this.c.a = "SHOP";
/* 403:403 */    this.c.g = true;
/* 404:404 */    this.c.a(localfh);
/* 405:405 */    this.c.a().set(514.0F, 26.0F, 0.0F);
/* 406:406 */    this.c.c();
/* 407:    */    
/* 408:408 */    this.e = new yr((ct)super.a(), 147.0F, 40.0F);
/* 409:409 */    this.e.a = "NAVIGATION";
/* 410:410 */    this.e.g = true;
/* 411:411 */    this.e.a(localfh);
/* 412:412 */    this.e.a().set(662.0F, 26.0F, 0.0F);
/* 413:413 */    this.e.c();
/* 414:    */    
/* 416:416 */    this.jdField_a_of_type_Fw = new fw((ct)super.a());
/* 417:417 */    this.jdField_a_of_type_Fw.c();
/* 418:    */    
/* 420:420 */    this.jdField_a_of_type_Fr = new fr((ct)super.a());
/* 421:    */    
/* 422:422 */    this.jdField_a_of_type_Fr.c();
/* 423:423 */    this.jdField_a_of_type_Fr.a(this.jdField_a_of_type_Yr);
/* 424:424 */    this.jdField_a_of_type_Fr.a(this.c);
/* 425:425 */    this.jdField_a_of_type_Fr.a(this.b);
/* 426:426 */    this.jdField_a_of_type_Fr.a(this.d);
/* 427:427 */    this.jdField_a_of_type_Fr.a(this.e);
/* 428:428 */    this.jdField_a_of_type_Fr.a(this.f);
/* 429:429 */    this.jdField_a_of_type_Fr.a(this.g);
/* 430:430 */    this.jdField_a_of_type_Fr.a(this.h);
/* 431:    */    
/* 432:432 */    this.jdField_a_of_type_HO = new hO((ct)super.a());
/* 433:    */    
/* 434:434 */    this.jdField_a_of_type_HO.c();
/* 435:435 */    this.jdField_a_of_type_HO.a(this.jdField_a_of_type_Yr);
/* 436:436 */    this.jdField_a_of_type_HO.a(this.c);
/* 437:437 */    this.jdField_a_of_type_HO.a(this.b);
/* 438:438 */    this.jdField_a_of_type_HO.a(this.d);
/* 439:439 */    this.jdField_a_of_type_HO.a(this.e);
/* 440:440 */    this.jdField_a_of_type_HO.a(this.f);
/* 441:441 */    this.jdField_a_of_type_HO.a(this.g);
/* 442:442 */    this.jdField_a_of_type_HO.a(this.h);
/* 443:    */    
/* 444:444 */    this.jdField_a_of_type_ID = new iD((ct)super.a());
/* 445:    */    
/* 446:446 */    this.jdField_a_of_type_ID.c();
/* 447:447 */    this.jdField_a_of_type_ID.a(this.jdField_a_of_type_Yr);
/* 448:448 */    this.jdField_a_of_type_ID.a(this.c);
/* 449:449 */    this.jdField_a_of_type_ID.a(this.b);
/* 450:450 */    this.jdField_a_of_type_ID.a(this.d);
/* 451:451 */    this.jdField_a_of_type_ID.a(this.e);
/* 452:452 */    this.jdField_a_of_type_ID.a(this.f);
/* 453:453 */    this.jdField_a_of_type_ID.a(this.g);
/* 454:454 */    this.jdField_a_of_type_ID.a(this.h);
/* 455:    */    
/* 456:456 */    this.jdField_a_of_type_HV = new hV((ct)super.a());
/* 457:    */    
/* 458:458 */    this.jdField_a_of_type_HV.c();
/* 459:459 */    this.jdField_a_of_type_HV.a(this.jdField_a_of_type_Yr);
/* 460:460 */    this.jdField_a_of_type_HV.a(this.c);
/* 461:461 */    this.jdField_a_of_type_HV.a(this.b);
/* 462:462 */    this.jdField_a_of_type_HV.a(this.d);
/* 463:463 */    this.jdField_a_of_type_HV.a(this.e);
/* 464:464 */    this.jdField_a_of_type_HV.a(this.f);
/* 465:465 */    this.jdField_a_of_type_HV.a(this.g);
/* 466:466 */    this.jdField_a_of_type_HV.a(this.h);
/* 467:    */    
/* 468:468 */    this.jdField_a_of_type_Iw = new iw((ct)super.a());
/* 469:    */    
/* 470:470 */    this.jdField_a_of_type_Iw.c();
/* 471:471 */    this.jdField_a_of_type_Iw.a(this.jdField_a_of_type_Yr);
/* 472:472 */    this.jdField_a_of_type_Iw.a(this.c);
/* 473:473 */    this.jdField_a_of_type_Iw.a(this.b);
/* 474:474 */    this.jdField_a_of_type_Iw.a(this.d);
/* 475:475 */    this.jdField_a_of_type_Iw.a(this.e);
/* 476:476 */    this.jdField_a_of_type_Iw.a(this.f);
/* 477:477 */    this.jdField_a_of_type_Iw.a(this.g);
/* 478:478 */    this.jdField_a_of_type_Iw.a(this.h);
/* 479:    */    
/* 480:480 */    this.jdField_a_of_type_GW = new gW((ct)super.a());
/* 481:    */    
/* 482:482 */    this.jdField_a_of_type_GW.c();
/* 483:483 */    this.jdField_a_of_type_GW.a(this.jdField_a_of_type_Yr);
/* 484:484 */    this.jdField_a_of_type_GW.a(this.c);
/* 485:485 */    this.jdField_a_of_type_GW.a(this.b);
/* 486:486 */    this.jdField_a_of_type_GW.a(this.d);
/* 487:487 */    this.jdField_a_of_type_GW.a(this.e);
/* 488:488 */    this.jdField_a_of_type_GW.a(this.f);
/* 489:489 */    this.jdField_a_of_type_GW.a(this.g);
/* 490:490 */    this.jdField_a_of_type_GW.a(this.h);
/* 491:    */    
/* 492:492 */    this.jdField_a_of_type_FW = new fW((ct)super.a());
/* 493:    */    
/* 494:494 */    this.jdField_a_of_type_FW.c();
/* 495:495 */    this.jdField_a_of_type_FW.a(this.jdField_a_of_type_Yr);
/* 496:496 */    this.jdField_a_of_type_FW.a(this.c);
/* 497:497 */    this.jdField_a_of_type_FW.a(this.b);
/* 498:498 */    this.jdField_a_of_type_FW.a(this.d);
/* 499:499 */    this.jdField_a_of_type_FW.a(this.e);
/* 500:500 */    this.jdField_a_of_type_FW.a(this.f);
/* 501:501 */    this.jdField_a_of_type_FW.a(this.g);
/* 502:502 */    this.jdField_a_of_type_FW.a(this.h);
/* 503:    */    
/* 505:505 */    this.jdField_a_of_type_ER = new eR(xe.a().a("sidebar-gui-"), (ct)super.a());
/* 506:    */    
/* 508:508 */    this.jdField_a_of_type_ER.c();
/* 509:    */    
/* 513:513 */    this.jdField_a_of_type_IH = new iH(xe.a().a("sidebar-gui-"), (ct)super.a());
/* 514:    */    
/* 515:515 */    this.jdField_a_of_type_IH.c();
/* 516:    */    
/* 517:517 */    this.jdField_a_of_type_In = new in((ct)super.a());
/* 518:518 */    this.jdField_a_of_type_In.c();
/* 519:    */    
/* 520:520 */    d();
/* 521:    */  }
/* 522:    */  
/* 526:    */  private boolean m()
/* 527:    */  {
/* 528:528 */    return ((ct)super.a()).a().a.a.jdField_a_of_type_Bm.b;
/* 529:    */  }
/* 530:    */  
/* 535:    */  private boolean n()
/* 536:    */  {
/* 537:537 */    return ((ct)super.a()).a().a.a.jdField_a_of_type_Bz.c;
/* 538:    */  }
/* 539:    */  
/* 540:    */  private boolean o() {
/* 541:541 */    return ((ct)super.a()).a().a.a.jdField_a_of_type_AO.c;
/* 542:    */  }
/* 543:    */  
/* 544:    */  private boolean p() {
/* 545:545 */    return ((ct)super.a()).a().a.a.jdField_a_of_type_AC.c;
/* 546:    */  }
/* 547:    */  
/* 549:    */  public final void a(xq paramxq)
/* 550:    */  {
/* 551:    */    in localin;
/* 552:    */    
/* 553:    */    Object localObject1;
/* 554:    */    
/* 555:555 */    long l = (localObject1 = (ct)(localin = this.jdField_a_of_type_In).a()).a().b();localin.a.b.set(0, String.valueOf(l));mF localmF = ((ct)localObject1).a();int i = 0; try { if (localmF != null) { Object localObject2; Object localObject3; if ((localmF.getPhysicsDataContainer().isInitialized()) && ((localmF.getPhysicsDataContainer().getObject() instanceof RigidBody))) { localObject2 = (RigidBody)localmF.getPhysicsDataContainer().getObject();localObject3 = new Vector3f();((RigidBody)localObject2).getLinearVelocity((Vector3f)localObject3);i = 1;localin.c.b.set(0, i.a(((Vector3f)localObject3).length())); } if ((localmF instanceof kd)) { localObject2 = (kd)localmF; if (((ct)localin.a()).a().a.a.jdField_a_of_type_Ar.a().a().jdField_a_of_type_Au.b) { localin.b.b.set(0, ((kd)localObject2).getRealName() + ", Sec: " + ((ct)localObject1).a().a());localin.b.b.set(1, "Thrust/Mass: " + i.a(((ThrusterCollectionManager)((kd)localObject2).a().getThrust().getCollectionManager()).getTotalThrust()) + " - " + i.a(((kd)localObject2).getMass()) + " = " + i.a(((kd)localObject2).a().getThrusterElementManager().getActualThrust())); } else { localin.b.b.set(0, ((kd)localObject2).getRealName());localObject1 = mJ.a(localObject3 = ((ct)localObject1).a().a().pos.getVector(), new q());boolean bool2 = mI.a((q)localObject3);localin.b.b.set(1, (bool2 ? "Star" : "Void") + "Sys: " + localObject1 + "; Sec: " + localObject3); } } else { localin.b.b.set(0, ((ct)localObject1).getPlayerName());mJ.a(localObject2 = ((ct)localObject1).a().a().pos.getVector(), new q());boolean bool1 = mI.a((q)localObject2);localin.b.b.set(1, (bool1 ? "Star" : "Void") + "System Sector: " + localObject2); } } } catch (Exception localException) {} if (i == 0) localin.c.b.set(0, "0");
/* 556:556 */    this.jdField_a_of_type_ID.a(paramxq);
/* 557:557 */    this.jdField_a_of_type_ER.a(paramxq);
/* 558:558 */    this.jdField_a_of_type_IH.a(paramxq);
/* 559:559 */    this.jdField_a_of_type_Fr.a(paramxq);
/* 560:560 */    this.jdField_a_of_type_Fw.a(paramxq);
/* 561:    */    
/* 562:562 */    super.a(paramxq);
/* 563:    */  }
/* 564:    */  
/* 565:    */  public final void a(ElementCollectionManager paramElementCollectionManager) {
/* 566:566 */    this.jdField_a_of_type_ID.a(paramElementCollectionManager);
/* 567:    */  }
/* 568:    */  
/* 585:    */  public final eR a()
/* 586:    */  {
/* 587:587 */    return this.jdField_a_of_type_ER;
/* 588:    */  }
/* 589:    */  
/* 599:    */  public final hV a()
/* 600:    */  {
/* 601:601 */    return this.jdField_a_of_type_HV;
/* 602:    */  }
/* 603:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     fg
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */