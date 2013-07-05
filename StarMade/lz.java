/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.data.world.Segment;
/*     */ import org.schema.game.network.objects.NetworkPlayer;
/*     */ import org.schema.schine.network.NetworkStateContainer;
/*     */ import org.schema.schine.network.StateInterface;
/*     */ import org.schema.schine.network.objects.Sendable;
/*     */ import org.schema.schine.network.objects.remote.RemoteArrayBuffer;
/*     */ import org.schema.schine.network.objects.remote.RemoteField;
/*     */ import org.schema.schine.network.objects.remote.RemoteIntegerArray;
/*     */ 
/*     */ public class lz
/*     */ {
/*  36 */   private com.bulletphysics.util.ObjectArrayList jdField_a_of_type_ComBulletphysicsUtilObjectArrayList = new com.bulletphysics.util.ObjectArrayList();
/*     */ 
/*  38 */   private final Set jdField_a_of_type_JavaUtilSet = new HashSet();
/*     */   private final lE jdField_a_of_type_LE;
/*     */   private lA jdField_a_of_type_LA;
/*     */   private boolean jdField_a_of_type_Boolean;
/*     */   private final com.bulletphysics.util.ObjectArrayList jdField_b_of_type_ComBulletphysicsUtilObjectArrayList;
/*     */   private boolean jdField_b_of_type_Boolean;
/*     */ 
/*     */   public lz(lE paramlE)
/*     */   {
/*  39 */     new HashSet();
/*     */ 
/*  41 */     this.jdField_a_of_type_LA = new lA();
/*     */ 
/*  46 */     this.jdField_b_of_type_ComBulletphysicsUtilObjectArrayList = new com.bulletphysics.util.ObjectArrayList();
/*     */ 
/*  51 */     this.jdField_a_of_type_LE = paramlE;
/*     */   }
/*     */ 
/*     */   public final lE a()
/*     */   {
/* 113 */     return this.jdField_a_of_type_LE;
/*     */   }
/*     */ 
/*     */   public final Set a()
/*     */   {
/* 120 */     return this.jdField_a_of_type_JavaUtilSet;
/*     */   }
/*     */   private void a(xq paramxq, lE paramlE) {
/* 123 */     for (Iterator localIterator = this.jdField_a_of_type_JavaUtilSet.iterator(); localIterator.hasNext(); )
/*     */     {
/*     */       lA locallA;
/* 124 */       if ((
/* 124 */         locallA = (lA)localIterator.next()).jdField_a_of_type_LE == 
/* 124 */         paramlE) {
/* 125 */         if ((!paramlE.isOnServer()) && ((locallA.jdField_a_of_type_Cw instanceof mF)) && 
/* 126 */           (locallA.jdField_a_of_type_LE == ((ct)paramlE.getState()).a())) {
/* 127 */           ((ct)paramlE.getState()).a((mF)locallA.jdField_a_of_type_Cw);
/*     */         }
/*     */ 
/* 130 */         locallA.jdField_a_of_type_Cw.a(paramxq, locallA);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/* 135 */   public final void a() { for (lA locallA : this.jdField_a_of_type_JavaUtilSet)
/*     */     {
/*     */       RemoteIntegerArray localRemoteIntegerArray;
/* 138 */       (
/* 140 */         localRemoteIntegerArray = new RemoteIntegerArray(9, this.jdField_a_of_type_LE.a()))
/* 140 */         .set(0, Integer.valueOf(-1));
/* 141 */       localRemoteIntegerArray.set(1, Integer.valueOf(0));
/* 142 */       localRemoteIntegerArray.set(2, Integer.valueOf(0));
/* 143 */       localRemoteIntegerArray.set(3, Integer.valueOf(0));
/*     */ 
/* 146 */       localRemoteIntegerArray.set(4, Integer.valueOf(locallA.jdField_a_of_type_Cw.getId()));
/*     */ 
/* 148 */       localRemoteIntegerArray.set(5, Integer.valueOf(((q)locallA.jdField_a_of_type_JavaLangObject).jdField_a_of_type_Int));
/* 149 */       localRemoteIntegerArray.set(6, Integer.valueOf(((q)locallA.jdField_a_of_type_JavaLangObject).jdField_b_of_type_Int));
/* 150 */       localRemoteIntegerArray.set(7, Integer.valueOf(((q)locallA.jdField_a_of_type_JavaLangObject).c));
/*     */ 
/* 152 */       localRemoteIntegerArray.set(8, Integer.valueOf(locallA.jdField_a_of_type_Cw.isHidden() ? 1 : 0));
/* 153 */       this.jdField_a_of_type_LE.a().controlRequestParameterBuffer.add(localRemoteIntegerArray);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void b(cw paramcw1, cw paramcw2, q paramq1, q paramq2, boolean paramBoolean)
/*     */   {
/* 159 */     RemoteIntegerArray localRemoteIntegerArray = new RemoteIntegerArray(9, this.jdField_a_of_type_LE.a());
/* 160 */     if (paramcw1 != null)
/* 161 */       localRemoteIntegerArray.set(0, Integer.valueOf(paramcw1.getId()));
/*     */     else {
/* 163 */       localRemoteIntegerArray.set(0, Integer.valueOf(-1));
/*     */     }
/* 165 */     if (paramq1 != null) {
/* 166 */       localRemoteIntegerArray.set(1, Integer.valueOf(paramq1.jdField_a_of_type_Int));
/* 167 */       localRemoteIntegerArray.set(2, Integer.valueOf(paramq1.jdField_b_of_type_Int));
/* 168 */       localRemoteIntegerArray.set(3, Integer.valueOf(paramq1.c));
/*     */     } else {
/* 170 */       localRemoteIntegerArray.set(1, Integer.valueOf(0));
/* 171 */       localRemoteIntegerArray.set(2, Integer.valueOf(0));
/* 172 */       localRemoteIntegerArray.set(3, Integer.valueOf(0));
/*     */     }
/* 174 */     if (paramcw2 != null)
/* 175 */       localRemoteIntegerArray.set(4, Integer.valueOf(paramcw2.getId()));
/*     */     else {
/* 177 */       localRemoteIntegerArray.set(4, Integer.valueOf(-1));
/*     */     }
/* 179 */     if (paramq2 != null) {
/* 180 */       localRemoteIntegerArray.set(5, Integer.valueOf(paramq2.jdField_a_of_type_Int));
/* 181 */       localRemoteIntegerArray.set(6, Integer.valueOf(paramq2.jdField_b_of_type_Int));
/* 182 */       localRemoteIntegerArray.set(7, Integer.valueOf(paramq2.c));
/*     */     } else {
/* 184 */       localRemoteIntegerArray.set(5, Integer.valueOf(0));
/* 185 */       localRemoteIntegerArray.set(6, Integer.valueOf(0));
/* 186 */       localRemoteIntegerArray.set(7, Integer.valueOf(0));
/*     */     }
/* 188 */     localRemoteIntegerArray.set(8, Integer.valueOf(paramBoolean ? 1 : -1));
/*     */ 
/* 190 */     this.jdField_a_of_type_LE.a().controlRequestParameterBuffer.add(localRemoteIntegerArray);
/*     */   }
/*     */ 
/*     */   public final void a(cw paramcw1, cw arg2, q paramq1, q paramq2, boolean paramBoolean)
/*     */   {
/* 196 */     System.err.println(this.jdField_a_of_type_LE + " request control: " + paramcw1 + " -> " + ???);
/* 197 */     if (this.jdField_a_of_type_LE.isOnServer()) {
/* 198 */       paramcw1 = new ly(this.jdField_a_of_type_LE, paramcw1 != null ? paramcw1.getId() : -1, paramq1 != null ? paramq1 : new q(), ??? != null ? ???.getId() : -1, paramq2 != null ? paramq2 : new q(), paramBoolean);
/*     */ 
/* 205 */       synchronized (this.jdField_a_of_type_ComBulletphysicsUtilObjectArrayList) {
/* 206 */         this.jdField_a_of_type_ComBulletphysicsUtilObjectArrayList.add(paramcw1);
/* 207 */         return; } 
/* 208 */     }b(paramcw1, ???, paramq1, paramq2, paramBoolean);
/*     */   }
/*     */ 
/*     */   public final void a(NetworkPlayer paramNetworkPlayer)
/*     */   {
/* 219 */     for (int i = 0; i < paramNetworkPlayer.controlRequestParameterBuffer.getReceiveBuffer().size(); 
/* 220 */       i++)
/*     */     {
/*     */       RemoteIntegerArray localRemoteIntegerArray;
/* 225 */       int j = ((Integer)(
/* 225 */         localRemoteIntegerArray = (RemoteIntegerArray)paramNetworkPlayer.controlRequestParameterBuffer.getReceiveBuffer().get(i))
/* 225 */         .get(0).get()).intValue();
/* 226 */       q localq1 = new q(((Integer)localRemoteIntegerArray.get(1).get()).intValue(), ((Integer)localRemoteIntegerArray.get(2).get()).intValue(), ((Integer)localRemoteIntegerArray.get(3).get()).intValue());
/* 227 */       int k = ((Integer)localRemoteIntegerArray.get(4).get()).intValue();
/* 228 */       q localq2 = new q(((Integer)localRemoteIntegerArray.get(5).get()).intValue(), ((Integer)localRemoteIntegerArray.get(6).get()).intValue(), ((Integer)localRemoteIntegerArray.get(7).get()).intValue());
/* 229 */       boolean bool = ((Integer)localRemoteIntegerArray.get(8).get()).intValue() == 1;
/*     */ 
/* 233 */       ly locally = new ly(this.jdField_a_of_type_LE, j, localq1, k, localq2, bool);
/*     */ 
/* 235 */       System.err.println(this.jdField_a_of_type_LE.getState() + "; " + this + " CONTROLLER REQUEST RECEIVED  " + locally);
/*     */ 
/* 237 */       synchronized (this.jdField_a_of_type_ComBulletphysicsUtilObjectArrayList) {
/* 238 */         this.jdField_a_of_type_ComBulletphysicsUtilObjectArrayList.add(locally);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void e()
/*     */   {
/* 342 */     if (!this.jdField_a_of_type_ComBulletphysicsUtilObjectArrayList.isEmpty())
/* 343 */       synchronized (this.jdField_a_of_type_ComBulletphysicsUtilObjectArrayList) {
/* 344 */         while (!this.jdField_a_of_type_ComBulletphysicsUtilObjectArrayList.isEmpty()) {
/* 345 */           Object localObject3 = (ly)this.jdField_a_of_type_ComBulletphysicsUtilObjectArrayList.remove(0); lz locallz = this; if ((!c) && (((ly)localObject3).jdField_a_of_type_LE != locallz.jdField_a_of_type_LE)) throw new AssertionError(); q localq1 = ((ly)localObject3).jdField_a_of_type_Q; q localq2 = ((ly)localObject3).jdField_b_of_type_Q; boolean bool1 = ((ly)localObject3).jdField_a_of_type_Boolean; Sendable localSendable1 = (Sendable)locallz.jdField_a_of_type_LE.getState().getLocalAndRemoteObjectContainer().getLocalObjects().get(((ly)localObject3).jdField_a_of_type_Int); localObject3 = (Sendable)locallz.jdField_a_of_type_LE.getState().getLocalAndRemoteObjectContainer().getLocalObjects().get(((ly)localObject3).jdField_b_of_type_Int);
/*     */           Object localObject4;
/* 345 */           if ((localSendable1 != null) && ((localSendable1 instanceof cw))) { localObject4 = (cw)localSendable1; locallz.a((cw)localObject4, localq1, locallz.jdField_a_of_type_LE, bool1); } if (localObject3 != null) { if ((localObject3 instanceof cw)) { localObject4 = (cw)localObject3; q localq4 = localq1; Sendable localSendable2 = localSendable1; q localq3 = localq2; Object localObject5 = localObject4; lE locallE = locallz.jdField_a_of_type_LE; localObject4 = locallz;
/*     */               lA locallA;
/* 345 */               (locallA = new lA()).jdField_a_of_type_Cw = localObject5; locallA.jdField_a_of_type_JavaLangObject = localq3; locallA.jdField_a_of_type_LE = locallE; boolean bool2 = false; synchronized (((lz)localObject4).jdField_a_of_type_JavaUtilSet) { bool2 = ((lz)localObject4).jdField_a_of_type_JavaUtilSet.add(locallA); if (!localObject5.a().contains(locallE)) localObject5.a().add(locallE); localObject5.a(locallE, localSendable2, localq4); if ((!((lz)localObject4).jdField_a_of_type_LE.isOnServer()) && (((lz)localObject4).jdField_a_of_type_LE == ((ct)((lz)localObject4).jdField_a_of_type_LE.getState()).a()) && ((localObject5 instanceof Aj))) xe.a().a((Aj)localObject5);  } if (bool2) { System.err.println("[CONTROLLER][ADD-UNIT] (" + locallE.getState() + "): " + locallE + " Added to controllers: " + localObject5); if (((lz)localObject4).jdField_a_of_type_LE.a()) ((ct)((lz)localObject4).jdField_a_of_type_LE.getState()).a().a((lz)localObject4);  }  }
/*     */           } else localObject1.jdField_a_of_type_JavaUtilSet.clear(); if (localObject1.jdField_a_of_type_LE.isOnServer()) localObject1.b((cw)localSendable1, (cw)localObject3, localq1, localq2, bool1);
/*     */         }
/* 347 */         return;
/*     */       }
/*     */   }
/*     */ 
/*     */   private boolean a(lA paramlA)
/*     */   {
/* 353 */     synchronized (this.jdField_a_of_type_JavaUtilSet) {
/* 354 */       return this.jdField_a_of_type_JavaUtilSet.contains(paramlA);
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void a(lE paramlE)
/*     */   {
/* 407 */     if ((!c) && (!this.jdField_a_of_type_LE.isOnServer())) throw new AssertionError();
/* 408 */     synchronized (this.jdField_a_of_type_JavaUtilSet)
/*     */     {
/* 410 */       Iterator localIterator = this.jdField_a_of_type_JavaUtilSet.iterator();
/* 411 */       while (localIterator.hasNext())
/*     */       {
/*     */         lA locallA;
/* 413 */         if ((
/* 413 */           locallA = (lA)localIterator.next()).jdField_a_of_type_LE == 
/* 413 */           paramlE) {
/* 414 */           localIterator.remove();
/* 415 */           locallA.jdField_a_of_type_Cw.a(paramlE, true);
/* 416 */           locallA.jdField_a_of_type_Cw.a().remove(paramlE);
/* 417 */           if ((!this.jdField_a_of_type_LE.isOnServer()) && (this.jdField_a_of_type_LE == ((ct)this.jdField_a_of_type_LE.getState()).a()) && ((locallA.jdField_a_of_type_Cw instanceof Aj))) {
/* 418 */             xe.a().a((Aj)locallA.jdField_a_of_type_Cw);
/*     */           }
/*     */         }
/*     */       }
/*     */ 
/* 423 */       System.err.println("REMOVED ALL UNITS OF " + this.jdField_a_of_type_LE + " ON " + this.jdField_a_of_type_LE.getState() + "! LEFT " + this.jdField_a_of_type_JavaUtilSet);
/*     */     }
/*     */ 
/* 426 */     System.err.println("[NOTIFIED] REMOVED ALL UNITS OF " + this.jdField_a_of_type_LE + " ON " + this.jdField_a_of_type_LE.getState() + "! LEFT " + this.jdField_a_of_type_JavaUtilSet);
/*     */   }
/*     */ 
/*     */   private Transform a(cw paramcw, q paramq, lE paramlE, boolean paramBoolean) {
/* 430 */     this.jdField_a_of_type_LA.jdField_a_of_type_Cw = paramcw;
/* 431 */     this.jdField_a_of_type_LA.jdField_a_of_type_JavaLangObject = paramq;
/* 432 */     this.jdField_a_of_type_LA.jdField_a_of_type_LE = paramlE;
/* 433 */     boolean bool = false;
/* 434 */     Transform localTransform = new Transform();
/* 435 */     synchronized (this.jdField_a_of_type_JavaUtilSet)
/*     */     {
/* 438 */       localTransform.set(((mF)paramcw).getWorldTransform());
/* 439 */       if (paramq != null) {
/* 440 */         paramq = new Vector3f(paramq.jdField_a_of_type_Int - 8, paramq.jdField_b_of_type_Int - 8, paramq.c - 8);
/* 441 */         localTransform.transform(paramq);
/* 442 */         localTransform.origin.set(paramq);
/*     */       }
/* 444 */       System.err.println("[CONTROLLERSTATE][REMOVE-UNIT] " + paramlE.getState() + "; REMOVING CONTROLLER UNIT FROM " + paramlE + ": " + paramcw + "; detach pos: " + localTransform.origin);
/*     */ 
/* 446 */       paramcw.a().remove(paramlE);
/* 447 */       paramcw.a(paramlE, paramBoolean);
/* 448 */       if ((!paramlE.isOnServer()) && (paramlE == ((ct)paramlE.getState()).a()) && ((paramcw instanceof Aj))) {
/* 449 */         xe.a().b((Aj)paramcw);
/*     */       }
/* 451 */       bool = this.jdField_a_of_type_JavaUtilSet.remove(this.jdField_a_of_type_LA);
/*     */     }
/* 453 */     if ((bool) && 
/* 454 */       (paramlE.a())) {
/* 455 */       ((ct)paramlE.getState()).a().a(this);
/*     */     }
/*     */ 
/* 458 */     return localTransform;
/*     */   }
/*     */ 
/*     */   public final void a(boolean paramBoolean)
/*     */   {
/* 464 */     this.jdField_a_of_type_Boolean = paramBoolean;
/*     */   }
/*     */ 
/*     */   public final void a(xq paramxq)
/*     */   {
/* 498 */     if (this.jdField_b_of_type_Boolean) {
/* 499 */       localObject1 = this.jdField_a_of_type_JavaUtilSet.iterator();
/* 500 */       while (((Iterator)localObject1).hasNext()) {
/* 501 */         lA locallA = (lA)((Iterator)localObject1).next();
/* 502 */         if (!this.jdField_a_of_type_LE.getState().getLocalAndRemoteObjectContainer().getLocalObjects().containsKey(locallA.jdField_a_of_type_Cw.getId())) {
/* 503 */           ((Iterator)localObject1).remove();
/*     */         }
/*     */       }
/* 506 */       this.jdField_b_of_type_Boolean = false;
/*     */     }
/*     */ 
/* 509 */     Object localObject1 = this; if (!this.jdField_b_of_type_ComBulletphysicsUtilObjectArrayList.isEmpty()) synchronized (((lz)localObject1).jdField_b_of_type_ComBulletphysicsUtilObjectArrayList) { ArrayList localArrayList1 = new ArrayList();
/*     */         cw localcw;
/*     */         Iterator localIterator;
/*     */         Object localObject2;
/*     */         ly locally;
/* 509 */         for (ArrayList localArrayList2 = new ArrayList(); !((lz)localObject1).jdField_b_of_type_ComBulletphysicsUtilObjectArrayList.isEmpty(); ) { localcw = (cw)((lz)localObject1).jdField_b_of_type_ComBulletphysicsUtilObjectArrayList.remove(0); for (localIterator = ((lz)localObject1).jdField_a_of_type_JavaUtilSet.iterator(); localIterator.hasNext(); ) if ((localObject2 = (lA)localIterator.next()).jdField_a_of_type_Cw == localcw) localArrayList1.add(localObject2);  
/*     */         }
/* 509 */         System.err.println("[PlayerControllerState][CLEANUP] Units: " + localArrayList1); System.err.println("[PlayerControllerState][CLEANUP] Requests: " + localArrayList2); ((lz)localObject1).jdField_a_of_type_JavaUtilSet.removeAll(localArrayList1); synchronized (((lz)localObject1).jdField_a_of_type_ComBulletphysicsUtilObjectArrayList) { ((lz)localObject1).jdField_a_of_type_ComBulletphysicsUtilObjectArrayList.removeAll(localArrayList2); } }
/* 510 */     e();
/*     */ 
/* 512 */     if (!this.jdField_a_of_type_Boolean) {
/* 513 */       paramxq = null; a(paramxq, this.jdField_a_of_type_LE);
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void a(cw paramcw) {
/* 518 */     synchronized (this.jdField_b_of_type_ComBulletphysicsUtilObjectArrayList) {
/* 519 */       this.jdField_b_of_type_ComBulletphysicsUtilObjectArrayList.add(paramcw);
/* 520 */       return;
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void b() {
/* 525 */     this.jdField_b_of_type_Boolean = true;
/*     */   }
/*     */ 
/*     */   public static void c()
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void a(le paramle)
/*     */   {
/* 536 */     if ((paramle.a().a() instanceof cw))
/*     */     {
/*     */       lA locallA1;
/* 537 */       (
/* 538 */         locallA1 = new lA()).jdField_a_of_type_Cw = 
/* 538 */         ((cw)paramle.a().a());
/*     */ 
/* 540 */       locallA1.jdField_a_of_type_LE = this.jdField_a_of_type_LE;
/* 541 */       locallA1.jdField_a_of_type_JavaLangObject = paramle.a(new q());
/* 542 */       lA locallA2 = locallA1; paramle = this; if (((locallA2.jdField_a_of_type_LE == paramle.jdField_a_of_type_LE) && (paramle.a(locallA2)) ? 1 : 0) != 0) {
/* 543 */         System.err.println("Forcing " + this + " to leave a controllable element");
/*     */ 
/* 545 */         a(locallA1.jdField_a_of_type_Cw, null, (q)locallA1.jdField_a_of_type_JavaLangObject, null, false);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void d()
/*     */   {
/* 552 */     if ((!c) && (!this.jdField_a_of_type_LE.isOnServer())) throw new AssertionError();
/* 553 */     for (Iterator localIterator = this.jdField_a_of_type_JavaUtilSet.iterator(); localIterator.hasNext(); )
/*     */     {
/*     */       lA locallA;
/* 555 */       if (((
/* 555 */         locallA = (lA)localIterator.next()).jdField_a_of_type_Cw instanceof SegmentController))
/*     */       {
/* 556 */         Object localObject = (SegmentController)locallA.jdField_a_of_type_Cw;
/*     */ 
/* 558 */         q localq = (q)locallA.jdField_a_of_type_JavaLangObject;
/*     */ 
/* 560 */         localObject = ((SegmentController)localObject).getSegmentBuffer().a(localq, true);
/*     */ 
/* 562 */         System.err.println("PARAMETER " + locallA.jdField_a_of_type_JavaLangObject + ": POINT " + localObject);
/*     */ 
/* 564 */         if ((((le)localObject).a() == 0) || (((le)localObject).a() <= 0)) {
/* 565 */           localObject = this.jdField_a_of_type_LE.a();
/* 566 */           System.err.println("FORCING PLAYER OUT OF SHIP " + this.jdField_a_of_type_LE + ": " + this + " from " + locallA.jdField_a_of_type_Cw + ": " + localObject);
/* 567 */           if ((!c) && (localObject == null)) throw new AssertionError();
/* 568 */           a(locallA.jdField_a_of_type_Cw, (cw)localObject, localq, new q(), false);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     lz
 * JD-Core Version:    0.6.2
 */