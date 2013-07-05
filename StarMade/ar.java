/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ import org.schema.common.FastMath;
/*     */ import org.schema.game.common.controller.BlockedByDockedElementException;
/*     */ import org.schema.game.common.controller.EditableSendableSegmentController;
/*     */ import org.schema.game.common.controller.ElementPositionBlockedException;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.data.element.ElementInformation;
/*     */ import org.schema.game.common.data.element.ElementKeyMap;
/*     */ import org.schema.game.common.data.player.inventory.NoSlotFreeException;
/*     */ import org.schema.game.network.objects.NetworkPlayer;
/*     */ import org.schema.game.network.objects.NetworkSegmentController;
/*     */ import org.schema.schine.graphicsengine.camera.Camera;
/*     */ import org.schema.schine.network.NetworkStateContainer;
/*     */ import org.schema.schine.network.objects.remote.RemoteBuffer;
/*     */ import org.schema.schine.network.objects.remote.RemoteIntPrimitive;
/*     */ import org.schema.schine.network.objects.remote.RemoteInteger;
/*     */ import org.schema.schine.network.objects.remote.RemoteShort;
/*     */ 
/*     */ public class ar extends U
/*     */ {
/*     */   private int jdField_a_of_type_Int;
/*     */   private HashMap jdField_a_of_type_JavaUtilHashMap;
/*     */   private bi jdField_a_of_type_Bi;
/*     */   private ax jdField_a_of_type_Ax;
/*     */   private aG jdField_a_of_type_AG;
/*     */   private mF jdField_a_of_type_MF;
/*     */   private ai jdField_a_of_type_Ai;
/*  55 */   private int b = 4;
/*     */ 
/*  57 */   public ar(ct paramct) { super(paramct);
/*  58 */     paramct = this; ar localar = this; this.jdField_a_of_type_JavaUtilHashMap = new HashMap(); localar.jdField_a_of_type_JavaUtilHashMap.put(Integer.valueOf(2), Integer.valueOf(0)); localar.jdField_a_of_type_JavaUtilHashMap.put(Integer.valueOf(3), Integer.valueOf(1)); localar.jdField_a_of_type_JavaUtilHashMap.put(Integer.valueOf(4), Integer.valueOf(2)); localar.jdField_a_of_type_JavaUtilHashMap.put(Integer.valueOf(5), Integer.valueOf(3)); localar.jdField_a_of_type_JavaUtilHashMap.put(Integer.valueOf(6), Integer.valueOf(4)); localar.jdField_a_of_type_JavaUtilHashMap.put(Integer.valueOf(7), Integer.valueOf(5)); localar.jdField_a_of_type_JavaUtilHashMap.put(Integer.valueOf(8), Integer.valueOf(6)); localar.jdField_a_of_type_JavaUtilHashMap.put(Integer.valueOf(9), Integer.valueOf(7)); localar.jdField_a_of_type_JavaUtilHashMap.put(Integer.valueOf(10), Integer.valueOf(8)); localar.jdField_a_of_type_JavaUtilHashMap.put(Integer.valueOf(11), Integer.valueOf(9)); paramct.jdField_a_of_type_Bi = new bi(paramct.a()); paramct.jdField_a_of_type_AG = new aG(paramct.a()); paramct.jdField_a_of_type_Ax = new ax(paramct.a()); paramct.jdField_a_of_type_Ai = new ai(paramct.a()); paramct.jdField_a_of_type_JavaUtilHashSet.add(paramct.jdField_a_of_type_Ai); paramct.jdField_a_of_type_JavaUtilHashSet.add(paramct.jdField_a_of_type_Bi); paramct.jdField_a_of_type_JavaUtilHashSet.add(paramct.jdField_a_of_type_AG); paramct.jdField_a_of_type_JavaUtilHashSet.add(paramct.jdField_a_of_type_Ax);
/*     */   }
/*     */ 
/*     */   public final int a(EditableSendableSegmentController paramEditableSendableSegmentController, Vector3f paramVector3f1, Vector3f paramVector3f2, ag paramag, ju paramju, az paramaz, float paramFloat)
/*     */   {
/*  64 */     if (!a().a().c(paramEditableSendableSegmentController))
/*  65 */       return 0;
/*     */     short s;
/*     */     q localq;
/*  70 */     if ((
/*  70 */       s = a()) == 0)
/*     */     {
/*  71 */       a().a().d("No element available to build!\nSelected slot is empty!");
/*  72 */       if (paramaz.jdField_a_of_type_Int != 0)
/*     */       {
/*     */         try
/*     */         {
/*  76 */           le localle = paramEditableSendableSegmentController.getNextToNearestPiece(paramVector3f1, paramVector3f2, new q(), paramFloat, true, new q(), new q());
/*     */ 
/*  78 */           if ((paramaz.jdField_a_of_type_Int > 0) && (localle != null)) {
/*  79 */             localq = localle.a(new q());
/*  80 */             switch (paramaz.jdField_a_of_type_Int) {
/*     */             case 1:
/*  82 */               System.err.println("SYM XY PLANE SET");
/*  83 */               paramaz.jdField_a_of_type_Q.c = localq.c;
/*  84 */               paramaz.jdField_a_of_type_Boolean = true;
/*  85 */               break;
/*     */             case 2:
/*  87 */               System.err.println("SYM XZ PLANE SET");
/*  88 */               paramaz.jdField_b_of_type_Q.b = localq.b;
/*  89 */               paramaz.jdField_b_of_type_Boolean = true;
/*  90 */               break;
/*     */             case 4:
/*  92 */               System.err.println("SYM YZ PLANE SET");
/*  93 */               paramaz.jdField_c_of_type_Q.jdField_a_of_type_Int = localq.jdField_a_of_type_Int;
/*  94 */               paramaz.jdField_c_of_type_Boolean = true;
/*     */             case 3:
/*     */             }
/*  97 */             paramaz.jdField_a_of_type_Int = 0;
/*     */           }
/*     */ 
/*     */         }
/*     */         catch (ElementPositionBlockedException localElementPositionBlockedException2)
/*     */         {
/* 106 */           localElementPositionBlockedException2.printStackTrace();
/*     */         }
/*     */         catch (BlockedByDockedElementException localBlockedByDockedElementException2)
/*     */         {
/* 106 */           localBlockedByDockedElementException2.printStackTrace();
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 108 */       return 0;
/*     */     }
/* 110 */     if (a().a().getInventory(null).a(this.jdField_a_of_type_Int)) {
/* 111 */       a().a().d("No element available to build!\nSelected slot is empty!");
/* 112 */       return 0;
/*     */     }
/* 114 */     if ((!d) && (a().a().getInventory(null).a(this.jdField_a_of_type_Int) <= 0)) throw new AssertionError();
/*     */ 
/* 116 */     int i = a().a().getInventory(null).a(this.jdField_a_of_type_Int);
/*     */     try
/*     */     {
/* 119 */       localq = new q(1, 1, 1);
/*     */ 
/* 121 */       if (cv.U.a()) {
/* 122 */         localq.b(this.jdField_a_of_type_Ai.a());
/*     */       }
/*     */ 
/* 125 */       localObject = ElementKeyMap.getInfo(s);
/* 126 */       boolean bool = true;
/*     */       int j;
/* 129 */       if ((
/* 129 */         j = this.b) > 
/* 129 */         7) {
/* 130 */         if (((ElementInformation)localObject).getBlockStyle() == 1) {
/* 131 */           j -= 8;
/* 132 */           bool = false;
/* 133 */           System.err.println("[CLIENT][PLACEBLOCK] BLOCK ORIENTATION (1) EXTENSION: " + (j + 4) + " -> " + j);
/* 134 */         } else if (((ElementInformation)localObject).getBlockStyle() == 2) {
/* 135 */           j -= 8;
/* 136 */           bool = false;
/* 137 */           System.err.println("[CLIENT][PLACEBLOCK] BLOCK ORIENTATION (2) EXTENSION: " + (j + 8) + " -> " + j);
/*     */         } else {
/* 139 */           System.err.println("[CLIENT][PLACEBLOCK] Exception: Block orientation was set over 8 on block style " + localObject + ": " + j);
/* 140 */           j = 0;
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 149 */       if ((
/* 149 */         paramag = paramEditableSendableSegmentController.getNearestIntersection(s, paramVector3f1, paramVector3f2, paramag, j, bool, paramju, localq, i, paramFloat, paramaz)) > 0)
/*     */       {
/* 153 */         paramju = ((ct)paramEditableSendableSegmentController.getState()).a().getInventory(null);
/*     */         try
/*     */         {
/* 158 */           paramju.a(this.jdField_a_of_type_Int, s, -paramag);
/*     */ 
/* 160 */           a().a().sendInventoryModification(this.jdField_a_of_type_Int, null);
/*     */ 
/* 162 */           xe.b("0022_action - buttons push big");
/* 163 */           if (s == 56) {
/* 164 */             a().a().d("INFO:\nUse gravity when you are\noutside the ship. Look at\ngravity module and press 'R'");
/*     */           }
/*     */ 
/* 169 */           paramEditableSendableSegmentController.getNetworkObject().lastModifiedPlayerId.forceClientUpdates();
/* 170 */           paramEditableSendableSegmentController.getNetworkObject().lastModifiedPlayerId.set(a().a().getId());
/* 171 */           return paramag; } catch (NoSlotFreeException localNoSlotFreeException) { localNoSlotFreeException
/* 173 */             .printStackTrace();
/*     */ 
/* 175 */           System.err.println("[WARNING] ILLEGIT BLOCK WITH SLOT " + this.jdField_a_of_type_Int);
/*     */ 
/* 177 */           paramEditableSendableSegmentController.getNearestIntersectingElementPosition(paramVector3f1, paramVector3f2, new q(1, 1, 1), paramFloat, new as(), paramaz);
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*     */     }
/*     */     catch (ElementPositionBlockedException localElementPositionBlockedException1)
/*     */     {
/* 191 */       Object localObject = "unknown";
/* 192 */       if (localElementPositionBlockedException1.a != null) {
/* 193 */         if ((localElementPositionBlockedException1.a instanceof SegmentController))
/* 194 */           localObject = ((SegmentController)localElementPositionBlockedException1.a).getRealName();
/* 195 */         else if ((localElementPositionBlockedException1.a instanceof mF)) {
/* 196 */           localObject = ((mF)localElementPositionBlockedException1.a).toNiceString();
/*     */         }
/*     */       }
/* 199 */       a().a().b("Cannot build here!\nPosition is blocked by\n" + (String)localObject);
/*     */     } catch (BlockedByDockedElementException localBlockedByDockedElementException1) {
/* 201 */       a().a().a().a(localBlockedByDockedElementException1.a);
/* 202 */       a().a().b("Cannot build here!\nPosition blocked\nby active docking area!");
/*     */     }
/* 204 */     return 0;
/*     */   }
/*     */   public final void a(EditableSendableSegmentController paramEditableSendableSegmentController, Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat, az paramaz) {
/* 207 */     if (!a().a().c(paramEditableSendableSegmentController)) {
/* 208 */       return;
/*     */     }
/*     */ 
/* 213 */     q localq = new q(1, 1, 1);
/*     */ 
/* 215 */     if (cv.U.a()) {
/* 216 */       localq.b(this.jdField_a_of_type_Ai.a());
/*     */     }
/* 218 */     IntOpenHashSet localIntOpenHashSet = new IntOpenHashSet(localq.jdField_a_of_type_Int * localq.b * localq.c);
/*     */ 
/* 220 */     paramEditableSendableSegmentController.getNearestIntersectingElementPosition(paramVector3f1, paramVector3f2, localq, paramFloat, new at(this, paramEditableSendableSegmentController, localIntOpenHashSet), paramaz);
/*     */ 
/* 242 */     xe.b("0022_action - buttons push medium");
/* 243 */     a().a().sendInventoryModification(localIntOpenHashSet, null);
/* 244 */     paramEditableSendableSegmentController.getNetworkObject().lastModifiedPlayerId.forceClientUpdates();
/* 245 */     paramEditableSendableSegmentController.getNetworkObject().lastModifiedPlayerId.set(a().a().getId());
/*     */   }
/*     */ 
/*     */   public final int a()
/*     */   {
/* 252 */     return this.b;
/*     */   }
/*     */ 
/*     */   public final ai a() {
/* 256 */     return this.jdField_a_of_type_Ai;
/*     */   }
/*     */ 
/*     */   public final bi a()
/*     */   {
/* 262 */     return this.jdField_a_of_type_Bi;
/*     */   }
/*     */ 
/*     */   public final aG a() {
/* 266 */     return this.jdField_a_of_type_AG;
/*     */   }
/*     */ 
/*     */   public final ax a()
/*     */   {
/* 312 */     return this.jdField_a_of_type_Ax;
/*     */   }
/*     */   public final mF a() {
/* 315 */     return this.jdField_a_of_type_MF;
/*     */   }
/*     */ 
/*     */   public final int b()
/*     */   {
/* 321 */     return this.jdField_a_of_type_Int;
/*     */   }
/*     */   public final short a() {
/* 324 */     return a().a().getInventory(null).a(this.jdField_a_of_type_Int);
/*     */   }
/*     */   public final int a(int paramInt) {
/* 327 */     if (this.jdField_a_of_type_JavaUtilHashMap.containsKey(Integer.valueOf(paramInt))) {
/* 328 */       return ((Integer)this.jdField_a_of_type_JavaUtilHashMap.get(Integer.valueOf(paramInt))).intValue();
/*     */     }
/* 330 */     System.err.println("[WARNING] UNKNOWN SLOT KEY: " + paramInt + ": " + this.jdField_a_of_type_JavaUtilHashMap);
/* 331 */     return -1;
/*     */   }
/*     */ 
/*     */   public void handleKeyEvent() {
/* 335 */     if (H.b()) {
/* 336 */       return;
/*     */     }
/*     */ 
/* 341 */     synchronized (a().b())
/*     */     {
/*     */       int i;
/* 343 */       if ((
/* 343 */         i = a().b().size()) > 0)
/*     */       {
/* 345 */         ((H)a().b().get(i - 1)).handleKeyEvent();
/* 346 */         return;
/*     */       }
/*     */     }
/*     */ 
/* 350 */     if (Keyboard.getEventKeyState())
/*     */     {
/* 354 */       if (a().b().isEmpty())
/*     */       {
/* 359 */         if (Keyboard.getEventKey() == cv.o.a()) {
/* 360 */           a().a().a().dropOrPickupSlots.add(new RemoteInteger(Integer.valueOf(this.jdField_a_of_type_Int), a().a().a()));
/*     */         }
/*     */ 
/* 363 */         if (Keyboard.getEventKey() == cv.L.a()) {
/* 364 */           d(1);
/*     */         }
/* 367 */         else if (Keyboard.getEventKey() == cv.M.a()) {
/* 368 */           d(-1);
/*     */         }
/* 371 */         else if (Keyboard.getEventKey() == cv.N.a()) {
/* 372 */           ??? = this; Vector3f localVector3f1 = new Vector3f(); Vector3f localVector3f2 = new Vector3f(); float f = 0.0F; if (((ar)???).a().a() != null) localVector3f1.set(((ar)???).a().a().getWorldTransform().origin); else localVector3f1.set(xe.a().a()); Object localObject2 = null; for (Iterator localIterator = ((ar)???).a().a().values().iterator(); localIterator.hasNext(); f = localVector3f2.length()) { mF localmF = (mF)localIterator.next(); localVector3f2.sub(localmF.getWorldTransformClient().origin, localVector3f1); if ((localmF != ((ar)???).a().a()) && ((localObject2 == null) || (localVector3f2.length() < f))) { System.err.println("!!!!!!!!!NEAREST IS NOW " + localmF); localObject2 = localmF; }  } ((ar)???).jdField_a_of_type_MF = localObject2;
/*     */         }
/* 375 */         else if (Keyboard.getEventKey() == cv.O.a()) {
/* 376 */           c();
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 383 */       ??? = this; if ((!this.jdField_a_of_type_Boolean) && (((U)???).jdField_b_of_type_Boolean)) { if ((!d) && (!((ar)???).a().b().isEmpty())) throw new AssertionError(); ((ar)???).a().a().a().handleKeyEvent(Keyboard.getEventKeyState(), Keyboard.getEventKey());
/*     */       }
/*     */     }
/* 386 */     if (a().b().isEmpty())
/* 387 */       super.handleKeyEvent();
/*     */   }
/*     */ 
/*     */   public final void b()
/*     */   {
/* 394 */     xe.a().getWorldTransform();
/*     */ 
/* 396 */     if (a().a() != null) {
/* 397 */       a().a().getWorldTransform();
/*     */     }
/*     */     else {
/* 400 */       new Transform()
/* 401 */         .setIdentity();
/*     */     }
/*     */ 
/* 424 */     if (Keyboard.getEventKey() == 52) {
/* 425 */       c((this.b + 1) % 8);
/*     */       short s;
/* 427 */       if ((
/* 427 */         s = a()) != 0)
/*     */       {
/*     */         ElementInformation localElementInformation;
/* 429 */         if ((
/* 429 */           localElementInformation = ElementKeyMap.getInfo(s))
/* 429 */           .getBlockStyle() > 0)
/* 430 */           System.err.println("BLOCK ORIENTATION: " + this.b + "; " + dO.a[(localElementInformation.getBlockStyle() - 1)][org.schema.game.common.data.element.Element.orientationMapping[this.b]].toString());
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void a(xp paramxp)
/*     */   {
/* 463 */     if ((a().a().a().a()) || (H.b()) || (a().b().size() > 0)) {
/* 464 */       return;
/*     */     }
/*     */ 
/* 467 */     if ((xu.W.a().equals("SLOTS")) && (!Keyboard.isKeyDown(42)))
/*     */     {
/* 469 */       this.jdField_a_of_type_Int = FastMath.b(this.jdField_a_of_type_Int + (paramxp.f < 0 ? -1 : paramxp.f > 0 ? 1 : 0), 10);
/*     */     }
/*     */ 
/* 474 */     super.a(paramxp);
/*     */   }
/*     */ 
/*     */   public final boolean a() {
/* 478 */     if (this.jdField_a_of_type_JavaUtilHashMap.containsKey(Integer.valueOf(Keyboard.getEventKey()))) {
/* 479 */       int i = a();
/* 480 */       this.jdField_a_of_type_Int = ((Integer)this.jdField_a_of_type_JavaUtilHashMap.get(Integer.valueOf(Keyboard.getEventKey()))).intValue();
/*     */ 
/* 482 */       if (i != a()) {
/* 483 */         c(4);
/*     */       }
/*     */ 
/* 486 */       System.err.println("Selected slot is now: " + this.jdField_a_of_type_Int);
/* 487 */       return true;
/*     */     }
/* 489 */     return false;
/*     */   }
/*     */ 
/*     */   protected final void a(boolean paramBoolean)
/*     */   {
/* 528 */     super.a(paramBoolean);
/* 529 */     if (paramBoolean)
/* 530 */       a().a().a().keyboardOfController.set(Short.valueOf((short)0));
/*     */   }
/*     */ 
/*     */   public final void b(boolean paramBoolean)
/*     */   {
/* 540 */     if (paramBoolean) {
/* 541 */       if ((!this.jdField_a_of_type_Bi.jdField_b_of_type_Boolean) && (!this.jdField_a_of_type_AG.jdField_b_of_type_Boolean) && (!this.jdField_a_of_type_AG.d()) && (!this.jdField_a_of_type_Bi.d())) {
/* 542 */         if ((!d) && (a().a() == null)) throw new AssertionError();
/* 543 */         this.jdField_a_of_type_AG.d(true);
/*     */       }
/*     */     }
/* 546 */     else a().a().a().keyboardOfController.set(Short.valueOf((short)0));
/*     */ 
/* 548 */     super.b(paramBoolean);
/*     */   }
/*     */ 
/*     */   private void d(int paramInt) {
/* 552 */     ObjectIterator localObjectIterator = a().a().values().iterator();
/* 553 */     int i = paramInt > 0 ? 2147483647 : -2147483648;
/* 554 */     int j = this.jdField_a_of_type_MF != null ? this.jdField_a_of_type_MF.getId() : -1;
/*     */     mF localmF;
/* 555 */     while (localObjectIterator.hasNext())
/*     */     {
/* 557 */       localmF = (mF)localObjectIterator.next();
/*     */ 
/* 558 */       if (this.jdField_a_of_type_MF == null) {
/* 559 */         e(-paramInt);
/* 560 */         return;
/*     */       }
/*     */ 
/* 563 */       if ((paramInt > 0) && (localmF.getId() > j) && (localmF.getId() < i)) {
/* 564 */         i = localmF.getId();
/*     */       }
/* 566 */       if ((paramInt <= 0) && (localmF.getId() < j) && (localmF.getId() > i)) {
/* 567 */         i = localmF.getId();
/*     */       }
/*     */     }
/* 570 */     if ((i == 2147483647) || (i == -2147483648)) {
/* 571 */       e(paramInt);
/* 572 */       return;
/*     */     }
/* 574 */     if (a().getLocalAndRemoteObjectContainer().getLocalObjects().containsKey(i)) {
/* 575 */       localmF = (mF)a().getLocalAndRemoteObjectContainer().getLocalObjects().get(i);
/* 576 */       this.jdField_a_of_type_MF = localmF;
/*     */     }
/*     */   }
/*     */ 
/*     */   private void e(int paramInt) {
/* 581 */     ObjectIterator localObjectIterator = a().a().values().iterator();
/* 582 */     int i = paramInt > 0 ? 2147483647 : -2147483648;
/*     */     mF localmF;
/* 584 */     while (localObjectIterator.hasNext())
/*     */     {
/* 586 */       localmF = (mF)localObjectIterator.next();
/*     */ 
/* 587 */       if ((paramInt > 0) && (localmF.getId() < i)) {
/* 588 */         i = localmF.getId();
/*     */       }
/* 590 */       if ((paramInt <= 0) && (localmF.getId() > i)) {
/* 591 */         i = localmF.getId();
/*     */       }
/*     */     }
/* 594 */     if (a().getLocalAndRemoteObjectContainer().getLocalObjects().containsKey(i)) {
/* 595 */       localmF = (mF)a().getLocalAndRemoteObjectContainer().getLocalObjects().get(i);
/* 596 */       this.jdField_a_of_type_MF = localmF;
/*     */     }
/*     */   }
/*     */ 
/*     */   private void c() {
/* 601 */     if ((a().a() == null) || (a().a().a() == null)) {
/* 602 */       return;
/*     */     }
/* 604 */     zC localzC = a().a().a();
/*     */ 
/* 606 */     Vector3f localVector3f1 = new Vector3f();
/* 607 */     float f = 0.0F;
/* 608 */     Object localObject = null;
/* 609 */     Vector3f localVector3f2 = localzC.a(new Vector3f());
/* 610 */     for (Iterator localIterator = a().a().values().iterator(); localIterator.hasNext(); )
/*     */     {
/*     */       mF localmF;
/* 611 */       if ((
/* 611 */         localmF = (mF)localIterator.next()) != 
/* 611 */         a().a()) {
/* 612 */         localVector3f1.set(localmF.getWorldTransform().origin);
/*     */ 
/* 617 */         if (a().a() != null)
/* 618 */           localVector3f1.sub(a().a().getWorldTransform().origin);
/*     */         else {
/* 620 */           localVector3f1.sub(xe.a().a());
/*     */         }
/*     */ 
/* 623 */         Vector3f localVector3f3 = localzC.a(localmF.getWorldTransformClient().origin, new Vector3f(), true);
/*     */         Vector3f localVector3f4;
/* 625 */         (
/* 626 */           localVector3f4 = new Vector3f())
/* 626 */           .sub(localVector3f3, localVector3f2);
/*     */ 
/* 628 */         if ((localVector3f4.length() < 250.0F) && (
/* 629 */           (localObject == null) || (localVector3f4.length() < f))) {
/* 630 */           localObject = localmF;
/* 631 */           f = localVector3f4.length();
/*     */         }
/*     */       }
/*     */     }
/* 635 */     this.jdField_a_of_type_MF = localObject;
/*     */   }
/*     */ 
/*     */   public final void c(int paramInt)
/*     */   {
/* 659 */     System.err.println("SET ORIENTATION " + paramInt);
/* 660 */     this.b = paramInt;
/*     */   }
/*     */   public final void a(mF parammF) {
/* 663 */     this.jdField_a_of_type_MF = parammF;
/*     */   }
/*     */ 
/*     */   public final void a(xq paramxq)
/*     */   {
/* 671 */     super.a(paramxq);
/*     */ 
/* 674 */     if (a().b().isEmpty())
/*     */     {
/* 676 */       a().a().a();
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ar
 * JD-Core Version:    0.6.2
 */