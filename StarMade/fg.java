/*     */ import com.bulletphysics.dynamics.RigidBody;
/*     */ import java.util.List;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.lwjgl.input.Mouse;
/*     */ import org.schema.game.common.controller.elements.ElementCollectionManager;
/*     */ import org.schema.game.common.controller.elements.ManagerModuleSingle;
/*     */ import org.schema.game.common.controller.elements.ShipManagerContainer;
/*     */ import org.schema.game.common.controller.elements.thrust.ThrusterCollectionManager;
/*     */ import org.schema.game.common.controller.elements.thrust.ThrusterElementManager;
/*     */ import org.schema.game.network.objects.NetworkSector;
/*     */ import org.schema.schine.graphicsengine.core.GlUtil;
/*     */ import org.schema.schine.network.client.ClientState;
/*     */ import org.schema.schine.network.objects.container.PhysicsDataContainer;
/*     */ import org.schema.schine.network.objects.remote.RemoteVector3i;
/*     */ 
/*     */ public class fg extends yz
/*     */ {
/*     */   private fW jdField_a_of_type_FW;
/*     */   private gW jdField_a_of_type_GW;
/*     */   private iw jdField_a_of_type_Iw;
/*     */   private hO jdField_a_of_type_HO;
/*     */   private fr jdField_a_of_type_Fr;
/*     */   private hV jdField_a_of_type_HV;
/*     */   private iD jdField_a_of_type_ID;
/*     */   private eR jdField_a_of_type_ER;
/*     */   private iH jdField_a_of_type_IH;
/*     */   private yr jdField_a_of_type_Yr;
/*     */   private yr b;
/*     */   private yr c;
/*     */   private yr d;
/*     */   private yr e;
/*     */   private in jdField_a_of_type_In;
/*     */   private yr f;
/*     */   private yr g;
/*     */   private fw jdField_a_of_type_Fw;
/*     */   private yr h;
/*     */ 
/*     */   public fg(ClientState paramClientState)
/*     */   {
/* 109 */     super(paramClientState);
/*     */   }
/*     */ 
/*     */   public final void e()
/*     */   {
/* 188 */     this.jdField_a_of_type_ID.e();
/*     */   }
/*     */ 
/*     */   private boolean b() {
/* 192 */     return ((ct)super.a()).a().a.a.jdField_a_of_type_V.c;
/*     */   }
/*     */ 
/*     */   private boolean c() {
/* 196 */     fg localfg = this; return (localfg.a().jdField_a_of_type_Au.c ? localfg.a().jdField_a_of_type_Au : a().a().c ? localfg.a().a() : null) != null;
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/*     */   }
/*     */ 
/*     */   protected final void d()
/*     */   {
/* 214 */     this.jdField_a_of_type_Fr.h(48);
/* 215 */     this.jdField_a_of_type_HO.h(48);
/* 216 */     this.jdField_a_of_type_ID.h(48);
/* 217 */     this.jdField_a_of_type_HV.h(48);
/* 218 */     this.jdField_a_of_type_Iw.h(48);
/* 219 */     this.jdField_a_of_type_ER.h(40);
/* 220 */     this.jdField_a_of_type_IH.h(40);
/*     */   }
/*     */ 
/*     */   public final void b() {
/* 224 */     if (k()) {
/* 225 */       d();
/*     */     }
/* 227 */     yz.i();
/* 228 */     this.jdField_a_of_type_In.b();
/*     */     eR localeR;
/* 229 */     if ((e()) || (n())) {
/* 230 */       localeR = null; this.jdField_a_of_type_ER.a(true);
/*     */     } else {
/* 232 */       localeR = null; this.jdField_a_of_type_ER.a(false);
/*     */     }
/* 234 */     if (e()) {
/* 235 */       this.jdField_a_of_type_HO.b();
/*     */     }
/* 237 */     if (b()) {
/* 238 */       this.jdField_a_of_type_Fr.b();
/*     */     }
/* 240 */     if (n()) {
/* 241 */       this.jdField_a_of_type_Iw.b();
/*     */     }
/* 243 */     if (f()) {
/* 244 */       this.jdField_a_of_type_HV.b();
/*     */     }
/* 246 */     if (o()) {
/* 247 */       this.jdField_a_of_type_GW.b();
/*     */     }
/* 249 */     if (p()) {
/* 250 */       this.jdField_a_of_type_FW.b();
/*     */     }
/* 252 */     if (m()) {
/* 253 */       this.jdField_a_of_type_ID.b();
/*     */     }
/*     */ 
/* 257 */     if ((d()) && (!((ct)super.a()).a().a.a.jdField_a_of_type_Aa.c)) {
/* 258 */       if (((((ct)super.a()).a().a.a.jdField_a_of_type_Ar.a().a().jdField_a_of_type_Bl.c) || (m())) && (!n()) && (!e())) {
/* 259 */         this.jdField_a_of_type_IH.b();
/*     */       }
/*     */       else {
/* 262 */         this.jdField_a_of_type_ER.b();
/*     */ 
/* 264 */         if ((!n()) && (!e()) && (c())) {
/* 265 */           this.jdField_a_of_type_Fw.b();
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 271 */     if ((((ct)super.a()).getDragging() != null) && ((((ct)super.a()).getDragging() instanceof hR)))
/*     */     {
/* 275 */       if (((
/* 275 */         localObject = (hR)((ct)super.a()).getDragging())
/* 275 */         .a() == 0) || (((hR)localObject).a(true) <= 0))
/* 276 */         ((ct)super.a()).setDragging(null);
/*     */       else {
/* 278 */         this.jdField_a_of_type_HO.a((hR)localObject);
/*     */       }
/*     */     }
/*     */ 
/* 282 */     Object localObject = this; if (d()) { yz.i(); localeR = ((fg)localObject).jdField_a_of_type_ER; if (!Mouse.isGrabbed()) { GlUtil.d(); for (int i = 0; i < localeR.a.length; i++) localeR.a[i].e(); GlUtil.c(); } yz.h(); } if (((fg)localObject).e()) { yz.i(); ((fg)localObject).jdField_a_of_type_HO.e(); yz.h(); }
/* 283 */     yz.h();
/*     */   }
/*     */ 
/*     */   public final float a()
/*     */   {
/* 316 */     return 0.0F;
/*     */   }
/*     */   private ax a() {
/* 319 */     return ((ct)super.a()).a().a.a.jdField_a_of_type_Ar.a();
/*     */   }
/*     */   private bj a() {
/* 322 */     return ((ct)super.a()).a().a.a.jdField_a_of_type_Ar.a().a();
/*     */   }
/*     */ 
/*     */   public final ct a() {
/* 326 */     return (ct)super.a();
/*     */   }
/*     */ 
/*     */   public final float b()
/*     */   {
/* 331 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   private boolean d() {
/* 335 */     return ((ct)super.a()).a().a.c;
/*     */   }
/*     */   private boolean e() {
/* 338 */     return ((ct)super.a()).a().a.a.jdField_a_of_type_An.c;
/*     */   }
/*     */   public final boolean a() {
/* 341 */     if ((c()) && (cv.U.a()) && (this.jdField_a_of_type_Fw.a_())) return true;
/*     */ 
/* 343 */     return false;
/*     */   }
/*     */ 
/*     */   private boolean f()
/*     */   {
/* 351 */     return ((ct)super.a()).a().a.a.jdField_a_of_type_Bg.c;
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/* 356 */     fh localfh = new fh(this, (byte)0);
/*     */ 
/* 358 */     this.d = new yr((ct)super.a(), 39.0F, 26.0F);
/* 359 */     this.d.a = "X";
/* 360 */     this.d.g = true;
/* 361 */     this.d.a(localfh);
/* 362 */     this.d.a().set(804.0F, 4.0F, 0.0F);
/* 363 */     this.d.c();
/*     */ 
/* 365 */     this.jdField_a_of_type_Yr = new yr((ct)super.a(), 147.0F, 40.0F);
/* 366 */     this.jdField_a_of_type_Yr.a = "INVENTORY";
/* 367 */     this.jdField_a_of_type_Yr.g = true;
/* 368 */     this.jdField_a_of_type_Yr.a(localfh);
/* 369 */     this.jdField_a_of_type_Yr.a().set(216.0F, 26.0F, 0.0F);
/* 370 */     this.jdField_a_of_type_Yr.c();
/*     */ 
/* 372 */     this.f = new yr((ct)super.a(), 147.0F, 40.0F);
/* 373 */     this.f.a = "AI";
/* 374 */     this.f.g = true;
/* 375 */     this.f.a(localfh);
/* 376 */     this.f.a().set(662.0F, 472.0F, 0.0F);
/* 377 */     this.f.c();
/*     */ 
/* 379 */     this.g = new yr((ct)super.a(), 147.0F, 40.0F);
/* 380 */     this.g.a = "FACTION";
/* 381 */     this.g.g = true;
/* 382 */     this.g.a(localfh);
/* 383 */     this.g.a().set(517.0F, 472.0F, 0.0F);
/* 384 */     this.g.c();
/*     */ 
/* 386 */     this.b = new yr((ct)super.a(), 147.0F, 40.0F);
/* 387 */     this.b.a = "WEAPON";
/* 388 */     this.b.g = true;
/* 389 */     this.b.a(localfh);
/* 390 */     this.b.a().set(366.0F, 26.0F, 0.0F);
/* 391 */     this.b.c();
/*     */ 
/* 393 */     this.h = new yr((ct)super.a(), 147.0F, 40.0F);
/* 394 */     this.h.a = "CATALOG";
/* 395 */     this.h.g = true;
/* 396 */     this.h.a(localfh);
/* 397 */     this.h.a().set(366.0F, 472.0F, 0.0F);
/* 398 */     this.h.c();
/*     */ 
/* 401 */     this.c = new yr((ct)super.a(), 147.0F, 40.0F);
/* 402 */     this.c.a = "SHOP";
/* 403 */     this.c.g = true;
/* 404 */     this.c.a(localfh);
/* 405 */     this.c.a().set(514.0F, 26.0F, 0.0F);
/* 406 */     this.c.c();
/*     */ 
/* 408 */     this.e = new yr((ct)super.a(), 147.0F, 40.0F);
/* 409 */     this.e.a = "NAVIGATION";
/* 410 */     this.e.g = true;
/* 411 */     this.e.a(localfh);
/* 412 */     this.e.a().set(662.0F, 26.0F, 0.0F);
/* 413 */     this.e.c();
/*     */ 
/* 416 */     this.jdField_a_of_type_Fw = new fw((ct)super.a());
/* 417 */     this.jdField_a_of_type_Fw.c();
/*     */ 
/* 420 */     this.jdField_a_of_type_Fr = new fr((ct)super.a());
/*     */ 
/* 422 */     this.jdField_a_of_type_Fr.c();
/* 423 */     this.jdField_a_of_type_Fr.a(this.jdField_a_of_type_Yr);
/* 424 */     this.jdField_a_of_type_Fr.a(this.c);
/* 425 */     this.jdField_a_of_type_Fr.a(this.b);
/* 426 */     this.jdField_a_of_type_Fr.a(this.d);
/* 427 */     this.jdField_a_of_type_Fr.a(this.e);
/* 428 */     this.jdField_a_of_type_Fr.a(this.f);
/* 429 */     this.jdField_a_of_type_Fr.a(this.g);
/* 430 */     this.jdField_a_of_type_Fr.a(this.h);
/*     */ 
/* 432 */     this.jdField_a_of_type_HO = new hO((ct)super.a());
/*     */ 
/* 434 */     this.jdField_a_of_type_HO.c();
/* 435 */     this.jdField_a_of_type_HO.a(this.jdField_a_of_type_Yr);
/* 436 */     this.jdField_a_of_type_HO.a(this.c);
/* 437 */     this.jdField_a_of_type_HO.a(this.b);
/* 438 */     this.jdField_a_of_type_HO.a(this.d);
/* 439 */     this.jdField_a_of_type_HO.a(this.e);
/* 440 */     this.jdField_a_of_type_HO.a(this.f);
/* 441 */     this.jdField_a_of_type_HO.a(this.g);
/* 442 */     this.jdField_a_of_type_HO.a(this.h);
/*     */ 
/* 444 */     this.jdField_a_of_type_ID = new iD((ct)super.a());
/*     */ 
/* 446 */     this.jdField_a_of_type_ID.c();
/* 447 */     this.jdField_a_of_type_ID.a(this.jdField_a_of_type_Yr);
/* 448 */     this.jdField_a_of_type_ID.a(this.c);
/* 449 */     this.jdField_a_of_type_ID.a(this.b);
/* 450 */     this.jdField_a_of_type_ID.a(this.d);
/* 451 */     this.jdField_a_of_type_ID.a(this.e);
/* 452 */     this.jdField_a_of_type_ID.a(this.f);
/* 453 */     this.jdField_a_of_type_ID.a(this.g);
/* 454 */     this.jdField_a_of_type_ID.a(this.h);
/*     */ 
/* 456 */     this.jdField_a_of_type_HV = new hV((ct)super.a());
/*     */ 
/* 458 */     this.jdField_a_of_type_HV.c();
/* 459 */     this.jdField_a_of_type_HV.a(this.jdField_a_of_type_Yr);
/* 460 */     this.jdField_a_of_type_HV.a(this.c);
/* 461 */     this.jdField_a_of_type_HV.a(this.b);
/* 462 */     this.jdField_a_of_type_HV.a(this.d);
/* 463 */     this.jdField_a_of_type_HV.a(this.e);
/* 464 */     this.jdField_a_of_type_HV.a(this.f);
/* 465 */     this.jdField_a_of_type_HV.a(this.g);
/* 466 */     this.jdField_a_of_type_HV.a(this.h);
/*     */ 
/* 468 */     this.jdField_a_of_type_Iw = new iw((ct)super.a());
/*     */ 
/* 470 */     this.jdField_a_of_type_Iw.c();
/* 471 */     this.jdField_a_of_type_Iw.a(this.jdField_a_of_type_Yr);
/* 472 */     this.jdField_a_of_type_Iw.a(this.c);
/* 473 */     this.jdField_a_of_type_Iw.a(this.b);
/* 474 */     this.jdField_a_of_type_Iw.a(this.d);
/* 475 */     this.jdField_a_of_type_Iw.a(this.e);
/* 476 */     this.jdField_a_of_type_Iw.a(this.f);
/* 477 */     this.jdField_a_of_type_Iw.a(this.g);
/* 478 */     this.jdField_a_of_type_Iw.a(this.h);
/*     */ 
/* 480 */     this.jdField_a_of_type_GW = new gW((ct)super.a());
/*     */ 
/* 482 */     this.jdField_a_of_type_GW.c();
/* 483 */     this.jdField_a_of_type_GW.a(this.jdField_a_of_type_Yr);
/* 484 */     this.jdField_a_of_type_GW.a(this.c);
/* 485 */     this.jdField_a_of_type_GW.a(this.b);
/* 486 */     this.jdField_a_of_type_GW.a(this.d);
/* 487 */     this.jdField_a_of_type_GW.a(this.e);
/* 488 */     this.jdField_a_of_type_GW.a(this.f);
/* 489 */     this.jdField_a_of_type_GW.a(this.g);
/* 490 */     this.jdField_a_of_type_GW.a(this.h);
/*     */ 
/* 492 */     this.jdField_a_of_type_FW = new fW((ct)super.a());
/*     */ 
/* 494 */     this.jdField_a_of_type_FW.c();
/* 495 */     this.jdField_a_of_type_FW.a(this.jdField_a_of_type_Yr);
/* 496 */     this.jdField_a_of_type_FW.a(this.c);
/* 497 */     this.jdField_a_of_type_FW.a(this.b);
/* 498 */     this.jdField_a_of_type_FW.a(this.d);
/* 499 */     this.jdField_a_of_type_FW.a(this.e);
/* 500 */     this.jdField_a_of_type_FW.a(this.f);
/* 501 */     this.jdField_a_of_type_FW.a(this.g);
/* 502 */     this.jdField_a_of_type_FW.a(this.h);
/*     */ 
/* 505 */     this.jdField_a_of_type_ER = new eR(xe.a().a("sidebar-gui-"), (ct)super.a());
/*     */ 
/* 508 */     this.jdField_a_of_type_ER.c();
/*     */ 
/* 513 */     this.jdField_a_of_type_IH = new iH(xe.a().a("sidebar-gui-"), (ct)super.a());
/*     */ 
/* 515 */     this.jdField_a_of_type_IH.c();
/*     */ 
/* 517 */     this.jdField_a_of_type_In = new in((ct)super.a());
/* 518 */     this.jdField_a_of_type_In.c();
/*     */ 
/* 520 */     d();
/*     */   }
/*     */ 
/*     */   private boolean m()
/*     */   {
/* 528 */     return ((ct)super.a()).a().a.a.jdField_a_of_type_Bm.b;
/*     */   }
/*     */ 
/*     */   private boolean n()
/*     */   {
/* 537 */     return ((ct)super.a()).a().a.a.jdField_a_of_type_Bz.c;
/*     */   }
/*     */ 
/*     */   private boolean o() {
/* 541 */     return ((ct)super.a()).a().a.a.jdField_a_of_type_AO.c;
/*     */   }
/*     */ 
/*     */   private boolean p() {
/* 545 */     return ((ct)super.a()).a().a.a.jdField_a_of_type_AC.c;
/*     */   }
/*     */ 
/*     */   public final void a(xq paramxq)
/*     */   {
/*     */     in localin;
/*     */     Object localObject1;
/* 555 */     long l = (localObject1 = (ct)(localin = this.jdField_a_of_type_In).a()).a().b(); localin.a.b.set(0, String.valueOf(l)); mF localmF = ((ct)localObject1).a(); int i = 0;
/*     */     try { if (localmF != null)
/*     */       {
/* 555 */         Object localObject2;
/*     */         Object localObject3;
/* 555 */         if ((localmF.getPhysicsDataContainer().isInitialized()) && ((localmF.getPhysicsDataContainer().getObject() instanceof RigidBody))) { localObject2 = (RigidBody)localmF.getPhysicsDataContainer().getObject(); localObject3 = new Vector3f(); ((RigidBody)localObject2).getLinearVelocity((Vector3f)localObject3); i = 1; localin.c.b.set(0, i.a(((Vector3f)localObject3).length())); } if ((localmF instanceof kd)) { localObject2 = (kd)localmF; if (((ct)localin.a()).a().a.a.jdField_a_of_type_Ar.a().a().jdField_a_of_type_Au.b) { localin.b.b.set(0, ((kd)localObject2).getRealName() + ", Sec: " + ((ct)localObject1).a().a()); localin.b.b.set(1, "Thrust/Mass: " + i.a(((ThrusterCollectionManager)((kd)localObject2).a().getThrust().getCollectionManager()).getTotalThrust()) + " - " + i.a(((kd)localObject2).getMass()) + " = " + i.a(((kd)localObject2).a().getThrusterElementManager().getActualThrust())); } else { localin.b.b.set(0, ((kd)localObject2).getRealName()); localObject1 = mJ.a(localObject3 = ((ct)localObject1).a().a().pos.getVector(), new q()); boolean bool2 = mI.a((q)localObject3); localin.b.b.set(1, (bool2 ? "Star" : "Void") + "Sys: " + localObject1 + "; Sec: " + localObject3); }  } else { localin.b.b.set(0, ((ct)localObject1).getPlayerName()); mJ.a(localObject2 = ((ct)localObject1).a().a().pos.getVector(), new q()); boolean bool1 = mI.a((q)localObject2); localin.b.b.set(1, (bool1 ? "Star" : "Void") + "System Sector: " + localObject2); }  }  } catch (Exception localException) {  }
/* 555 */     if (i == 0) localin.c.b.set(0, "0");
/* 556 */     this.jdField_a_of_type_ID.a(paramxq);
/* 557 */     this.jdField_a_of_type_ER.a(paramxq);
/* 558 */     this.jdField_a_of_type_IH.a(paramxq);
/* 559 */     this.jdField_a_of_type_Fr.a(paramxq);
/* 560 */     this.jdField_a_of_type_Fw.a(paramxq);
/*     */ 
/* 562 */     super.a(paramxq);
/*     */   }
/*     */ 
/*     */   public final void a(ElementCollectionManager paramElementCollectionManager) {
/* 566 */     this.jdField_a_of_type_ID.a(paramElementCollectionManager);
/*     */   }
/*     */ 
/*     */   public final eR a()
/*     */   {
/* 587 */     return this.jdField_a_of_type_ER;
/*     */   }
/*     */ 
/*     */   public final hV a()
/*     */   {
/* 601 */     return this.jdField_a_of_type_HV;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     fg
 * JD-Core Version:    0.6.2
 */