/*   1:    */import com.bulletphysics.linearmath.Transform;
/*   2:    */import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*   3:    */import java.io.PrintStream;
/*   4:    */import java.util.ArrayList;
/*   5:    */import java.util.HashSet;
/*   6:    */import java.util.Iterator;
/*   7:    */import java.util.Set;
/*   8:    */import javax.vecmath.Vector3f;
/*   9:    */import org.schema.game.common.controller.SegmentController;
/*  10:    */import org.schema.game.common.data.world.Segment;
/*  11:    */import org.schema.game.network.objects.NetworkPlayer;
/*  12:    */import org.schema.schine.network.NetworkStateContainer;
/*  13:    */import org.schema.schine.network.StateInterface;
/*  14:    */import org.schema.schine.network.objects.Sendable;
/*  15:    */import org.schema.schine.network.objects.remote.RemoteArrayBuffer;
/*  16:    */import org.schema.schine.network.objects.remote.RemoteField;
/*  17:    */import org.schema.schine.network.objects.remote.RemoteIntegerArray;
/*  18:    */
/*  34:    */public class lz
/*  35:    */{
/*  36: 36 */  private com.bulletphysics.util.ObjectArrayList jdField_a_of_type_ComBulletphysicsUtilObjectArrayList = new com.bulletphysics.util.ObjectArrayList();
/*  37:    */  
/*  38: 38 */  private final Set jdField_a_of_type_JavaUtilSet = new HashSet();
/*  39: 39 */  public lz(lE paramlE) { new HashSet();
/*  40:    */    
/*  41: 41 */    this.jdField_a_of_type_LA = new lA();
/*  42:    */    
/*  46: 46 */    this.jdField_b_of_type_ComBulletphysicsUtilObjectArrayList = new com.bulletphysics.util.ObjectArrayList();
/*  47:    */    
/*  51: 51 */    this.jdField_a_of_type_LE = paramlE;
/*  52:    */  }
/*  53:    */  
/*  73:    */  private final lE jdField_a_of_type_LE;
/*  74:    */  
/*  92:    */  private lA jdField_a_of_type_LA;
/*  93:    */  
/* 111:    */  public final lE a()
/* 112:    */  {
/* 113:113 */    return this.jdField_a_of_type_LE;
/* 114:    */  }
/* 115:    */  
/* 120:120 */  public final Set a() { return this.jdField_a_of_type_JavaUtilSet; }
/* 121:    */  
/* 122:    */  private void a(xq paramxq, lE paramlE) {
/* 123:123 */    for (Iterator localIterator = this.jdField_a_of_type_JavaUtilSet.iterator(); localIterator.hasNext();) { lA locallA;
/* 124:124 */      if ((locallA = (lA)localIterator.next()).jdField_a_of_type_LE == paramlE) {
/* 125:125 */        if ((!paramlE.isOnServer()) && ((locallA.jdField_a_of_type_Cw instanceof mF)) && 
/* 126:126 */          (locallA.jdField_a_of_type_LE == ((ct)paramlE.getState()).a())) {
/* 127:127 */          ((ct)paramlE.getState()).a((mF)locallA.jdField_a_of_type_Cw);
/* 128:    */        }
/* 129:    */        
/* 130:130 */        locallA.jdField_a_of_type_Cw.a(paramxq, locallA);
/* 131:    */      }
/* 132:    */    }
/* 133:    */  }
/* 134:    */  
/* 135:135 */  public final void a() { for (lA locallA : this.jdField_a_of_type_JavaUtilSet)
/* 136:    */    {
/* 137:    */      RemoteIntegerArray localRemoteIntegerArray;
/* 138:    */      
/* 140:140 */      (localRemoteIntegerArray = new RemoteIntegerArray(9, this.jdField_a_of_type_LE.a())).set(0, Integer.valueOf(-1));
/* 141:141 */      localRemoteIntegerArray.set(1, Integer.valueOf(0));
/* 142:142 */      localRemoteIntegerArray.set(2, Integer.valueOf(0));
/* 143:143 */      localRemoteIntegerArray.set(3, Integer.valueOf(0));
/* 144:    */      
/* 146:146 */      localRemoteIntegerArray.set(4, Integer.valueOf(locallA.jdField_a_of_type_Cw.getId()));
/* 147:    */      
/* 148:148 */      localRemoteIntegerArray.set(5, Integer.valueOf(((q)locallA.jdField_a_of_type_JavaLangObject).jdField_a_of_type_Int));
/* 149:149 */      localRemoteIntegerArray.set(6, Integer.valueOf(((q)locallA.jdField_a_of_type_JavaLangObject).jdField_b_of_type_Int));
/* 150:150 */      localRemoteIntegerArray.set(7, Integer.valueOf(((q)locallA.jdField_a_of_type_JavaLangObject).c));
/* 151:    */      
/* 152:152 */      localRemoteIntegerArray.set(8, Integer.valueOf(locallA.jdField_a_of_type_Cw.isHidden() ? 1 : 0));
/* 153:153 */      this.jdField_a_of_type_LE.a().controlRequestParameterBuffer.add(localRemoteIntegerArray);
/* 154:    */    }
/* 155:    */  }
/* 156:    */  
/* 157:    */  private void b(cw paramcw1, cw paramcw2, q paramq1, q paramq2, boolean paramBoolean)
/* 158:    */  {
/* 159:159 */    RemoteIntegerArray localRemoteIntegerArray = new RemoteIntegerArray(9, this.jdField_a_of_type_LE.a());
/* 160:160 */    if (paramcw1 != null) {
/* 161:161 */      localRemoteIntegerArray.set(0, Integer.valueOf(paramcw1.getId()));
/* 162:    */    } else {
/* 163:163 */      localRemoteIntegerArray.set(0, Integer.valueOf(-1));
/* 164:    */    }
/* 165:165 */    if (paramq1 != null) {
/* 166:166 */      localRemoteIntegerArray.set(1, Integer.valueOf(paramq1.jdField_a_of_type_Int));
/* 167:167 */      localRemoteIntegerArray.set(2, Integer.valueOf(paramq1.jdField_b_of_type_Int));
/* 168:168 */      localRemoteIntegerArray.set(3, Integer.valueOf(paramq1.c));
/* 169:    */    } else {
/* 170:170 */      localRemoteIntegerArray.set(1, Integer.valueOf(0));
/* 171:171 */      localRemoteIntegerArray.set(2, Integer.valueOf(0));
/* 172:172 */      localRemoteIntegerArray.set(3, Integer.valueOf(0));
/* 173:    */    }
/* 174:174 */    if (paramcw2 != null) {
/* 175:175 */      localRemoteIntegerArray.set(4, Integer.valueOf(paramcw2.getId()));
/* 176:    */    } else {
/* 177:177 */      localRemoteIntegerArray.set(4, Integer.valueOf(-1));
/* 178:    */    }
/* 179:179 */    if (paramq2 != null) {
/* 180:180 */      localRemoteIntegerArray.set(5, Integer.valueOf(paramq2.jdField_a_of_type_Int));
/* 181:181 */      localRemoteIntegerArray.set(6, Integer.valueOf(paramq2.jdField_b_of_type_Int));
/* 182:182 */      localRemoteIntegerArray.set(7, Integer.valueOf(paramq2.c));
/* 183:    */    } else {
/* 184:184 */      localRemoteIntegerArray.set(5, Integer.valueOf(0));
/* 185:185 */      localRemoteIntegerArray.set(6, Integer.valueOf(0));
/* 186:186 */      localRemoteIntegerArray.set(7, Integer.valueOf(0));
/* 187:    */    }
/* 188:188 */    localRemoteIntegerArray.set(8, Integer.valueOf(paramBoolean ? 1 : -1));
/* 189:    */    
/* 190:190 */    this.jdField_a_of_type_LE.a().controlRequestParameterBuffer.add(localRemoteIntegerArray);
/* 191:    */  }
/* 192:    */  
/* 194:    */  public final void a(cw paramcw1, cw arg2, q paramq1, q paramq2, boolean paramBoolean)
/* 195:    */  {
/* 196:196 */    System.err.println(this.jdField_a_of_type_LE + " request control: " + paramcw1 + " -> " + ???);
/* 197:197 */    if (this.jdField_a_of_type_LE.isOnServer()) {
/* 198:198 */      paramcw1 = new ly(this.jdField_a_of_type_LE, paramcw1 != null ? paramcw1.getId() : -1, paramq1 != null ? paramq1 : new q(), ??? != null ? ???.getId() : -1, paramq2 != null ? paramq2 : new q(), paramBoolean);
/* 199:    */      
/* 205:205 */      synchronized (this.jdField_a_of_type_ComBulletphysicsUtilObjectArrayList) {
/* 206:206 */        this.jdField_a_of_type_ComBulletphysicsUtilObjectArrayList.add(paramcw1);
/* 207:207 */        return; } }
/* 208:208 */    b(paramcw1, ???, paramq1, paramq2, paramBoolean);
/* 209:    */  }
/* 210:    */  
/* 217:    */  public final void a(NetworkPlayer paramNetworkPlayer)
/* 218:    */  {
/* 219:219 */    for (int i = 0; i < paramNetworkPlayer.controlRequestParameterBuffer.getReceiveBuffer().size(); 
/* 220:220 */        i++)
/* 221:    */    {
/* 222:    */      RemoteIntegerArray localRemoteIntegerArray;
/* 223:    */      
/* 225:225 */      int j = ((Integer)(localRemoteIntegerArray = (RemoteIntegerArray)paramNetworkPlayer.controlRequestParameterBuffer.getReceiveBuffer().get(i)).get(0).get()).intValue();
/* 226:226 */      q localq1 = new q(((Integer)localRemoteIntegerArray.get(1).get()).intValue(), ((Integer)localRemoteIntegerArray.get(2).get()).intValue(), ((Integer)localRemoteIntegerArray.get(3).get()).intValue());
/* 227:227 */      int k = ((Integer)localRemoteIntegerArray.get(4).get()).intValue();
/* 228:228 */      q localq2 = new q(((Integer)localRemoteIntegerArray.get(5).get()).intValue(), ((Integer)localRemoteIntegerArray.get(6).get()).intValue(), ((Integer)localRemoteIntegerArray.get(7).get()).intValue());
/* 229:229 */      boolean bool = ((Integer)localRemoteIntegerArray.get(8).get()).intValue() == 1;
/* 230:    */      
/* 233:233 */      ly locally = new ly(this.jdField_a_of_type_LE, j, localq1, k, localq2, bool);
/* 234:    */      
/* 235:235 */      System.err.println(this.jdField_a_of_type_LE.getState() + "; " + this + " CONTROLLER REQUEST RECEIVED  " + locally);
/* 236:    */      
/* 237:237 */      synchronized (this.jdField_a_of_type_ComBulletphysicsUtilObjectArrayList) {
/* 238:238 */        this.jdField_a_of_type_ComBulletphysicsUtilObjectArrayList.add(locally);
/* 239:    */      }
/* 240:    */    }
/* 241:    */  }
/* 242:    */  
/* 267:    */  private boolean jdField_a_of_type_Boolean;
/* 268:    */  
/* 292:    */  private final com.bulletphysics.util.ObjectArrayList jdField_b_of_type_ComBulletphysicsUtilObjectArrayList;
/* 293:    */  
/* 316:    */  private boolean jdField_b_of_type_Boolean;
/* 317:    */  
/* 340:    */  private void e()
/* 341:    */  {
/* 342:342 */    if (!this.jdField_a_of_type_ComBulletphysicsUtilObjectArrayList.isEmpty()) {
/* 343:343 */      synchronized (this.jdField_a_of_type_ComBulletphysicsUtilObjectArrayList) {
/* 344:344 */        while (!this.jdField_a_of_type_ComBulletphysicsUtilObjectArrayList.isEmpty()) {
/* 345:345 */          Object localObject3 = (ly)this.jdField_a_of_type_ComBulletphysicsUtilObjectArrayList.remove(0);lz locallz = this; if ((!c) && (((ly)localObject3).jdField_a_of_type_LE != locallz.jdField_a_of_type_LE)) throw new AssertionError(); q localq1 = ((ly)localObject3).jdField_a_of_type_Q;q localq2 = ((ly)localObject3).jdField_b_of_type_Q;boolean bool1 = ((ly)localObject3).jdField_a_of_type_Boolean;Sendable localSendable1 = (Sendable)locallz.jdField_a_of_type_LE.getState().getLocalAndRemoteObjectContainer().getLocalObjects().get(((ly)localObject3).jdField_a_of_type_Int);localObject3 = (Sendable)locallz.jdField_a_of_type_LE.getState().getLocalAndRemoteObjectContainer().getLocalObjects().get(((ly)localObject3).jdField_b_of_type_Int); Object localObject4; if ((localSendable1 != null) && ((localSendable1 instanceof cw))) { localObject4 = (cw)localSendable1;locallz.a((cw)localObject4, localq1, locallz.jdField_a_of_type_LE, bool1); } if (localObject3 != null) { if ((localObject3 instanceof cw)) { localObject4 = (cw)localObject3;q localq4 = localq1;Sendable localSendable2 = localSendable1;q localq3 = localq2;Object localObject5 = localObject4;lE locallE = locallz.jdField_a_of_type_LE;localObject4 = locallz; lA locallA; (locallA = new lA()).jdField_a_of_type_Cw = localObject5;locallA.jdField_a_of_type_JavaLangObject = localq3;locallA.jdField_a_of_type_LE = locallE;boolean bool2 = false; synchronized (((lz)localObject4).jdField_a_of_type_JavaUtilSet) { bool2 = ((lz)localObject4).jdField_a_of_type_JavaUtilSet.add(locallA); if (!localObject5.a().contains(locallE)) localObject5.a().add(locallE); localObject5.a(locallE, localSendable2, localq4); if ((!((lz)localObject4).jdField_a_of_type_LE.isOnServer()) && (((lz)localObject4).jdField_a_of_type_LE == ((ct)((lz)localObject4).jdField_a_of_type_LE.getState()).a()) && ((localObject5 instanceof An))) xe.a().a((An)localObject5); } if (bool2) { System.err.println("[CONTROLLER][ADD-UNIT] (" + locallE.getState() + "): " + locallE + " Added to controllers: " + localObject5); if (((lz)localObject4).jdField_a_of_type_LE.a()) ((ct)((lz)localObject4).jdField_a_of_type_LE.getState()).a().a((lz)localObject4); } } } else localObject1.jdField_a_of_type_JavaUtilSet.clear(); if (localObject1.jdField_a_of_type_LE.isOnServer()) localObject1.b((cw)localSendable1, (cw)localObject3, localq1, localq2, bool1);
/* 346:    */        }
/* 347:347 */        return;
/* 348:    */      }
/* 349:    */    }
/* 350:    */  }
/* 351:    */  
/* 352:    */  private boolean a(lA paramlA) {
/* 353:353 */    synchronized (this.jdField_a_of_type_JavaUtilSet) {
/* 354:354 */      return this.jdField_a_of_type_JavaUtilSet.contains(paramlA);
/* 355:    */    }
/* 356:    */  }
/* 357:    */  
/* 405:    */  public final void a(lE paramlE)
/* 406:    */  {
/* 407:407 */    if ((!c) && (!this.jdField_a_of_type_LE.isOnServer())) throw new AssertionError();
/* 408:408 */    synchronized (this.jdField_a_of_type_JavaUtilSet)
/* 409:    */    {
/* 410:410 */      Iterator localIterator = this.jdField_a_of_type_JavaUtilSet.iterator();
/* 411:411 */      while (localIterator.hasNext()) {
/* 412:    */        lA locallA;
/* 413:413 */        if ((locallA = (lA)localIterator.next()).jdField_a_of_type_LE == paramlE) {
/* 414:414 */          localIterator.remove();
/* 415:415 */          locallA.jdField_a_of_type_Cw.a(paramlE, true);
/* 416:416 */          locallA.jdField_a_of_type_Cw.a().remove(paramlE);
/* 417:417 */          if ((!this.jdField_a_of_type_LE.isOnServer()) && (this.jdField_a_of_type_LE == ((ct)this.jdField_a_of_type_LE.getState()).a()) && ((locallA.jdField_a_of_type_Cw instanceof An))) {
/* 418:418 */            xe.a().a((An)locallA.jdField_a_of_type_Cw);
/* 419:    */          }
/* 420:    */        }
/* 421:    */      }
/* 422:    */      
/* 423:423 */      System.err.println("REMOVED ALL UNITS OF " + this.jdField_a_of_type_LE + " ON " + this.jdField_a_of_type_LE.getState() + "! LEFT " + this.jdField_a_of_type_JavaUtilSet);
/* 424:    */    }
/* 425:    */    
/* 426:426 */    System.err.println("[NOTIFIED] REMOVED ALL UNITS OF " + this.jdField_a_of_type_LE + " ON " + this.jdField_a_of_type_LE.getState() + "! LEFT " + this.jdField_a_of_type_JavaUtilSet);
/* 427:    */  }
/* 428:    */  
/* 429:    */  private Transform a(cw paramcw, q paramq, lE paramlE, boolean paramBoolean) {
/* 430:430 */    this.jdField_a_of_type_LA.jdField_a_of_type_Cw = paramcw;
/* 431:431 */    this.jdField_a_of_type_LA.jdField_a_of_type_JavaLangObject = paramq;
/* 432:432 */    this.jdField_a_of_type_LA.jdField_a_of_type_LE = paramlE;
/* 433:433 */    boolean bool = false;
/* 434:434 */    Transform localTransform = new Transform();
/* 435:435 */    synchronized (this.jdField_a_of_type_JavaUtilSet)
/* 436:    */    {
/* 438:438 */      localTransform.set(((mF)paramcw).getWorldTransform());
/* 439:439 */      if (paramq != null) {
/* 440:440 */        paramq = new Vector3f(paramq.jdField_a_of_type_Int - 8, paramq.jdField_b_of_type_Int - 8, paramq.c - 8);
/* 441:441 */        localTransform.transform(paramq);
/* 442:442 */        localTransform.origin.set(paramq);
/* 443:    */      }
/* 444:444 */      System.err.println("[CONTROLLERSTATE][REMOVE-UNIT] " + paramlE.getState() + "; REMOVING CONTROLLER UNIT FROM " + paramlE + ": " + paramcw + "; detach pos: " + localTransform.origin);
/* 445:    */      
/* 446:446 */      paramcw.a().remove(paramlE);
/* 447:447 */      paramcw.a(paramlE, paramBoolean);
/* 448:448 */      if ((!paramlE.isOnServer()) && (paramlE == ((ct)paramlE.getState()).a()) && ((paramcw instanceof An))) {
/* 449:449 */        xe.a().b((An)paramcw);
/* 450:    */      }
/* 451:451 */      bool = this.jdField_a_of_type_JavaUtilSet.remove(this.jdField_a_of_type_LA);
/* 452:    */    }
/* 453:453 */    if ((bool) && 
/* 454:454 */      (paramlE.a())) {
/* 455:455 */      ((ct)paramlE.getState()).a().a(this);
/* 456:    */    }
/* 457:    */    
/* 458:458 */    return localTransform;
/* 459:    */  }
/* 460:    */  
/* 462:    */  public final void a(boolean paramBoolean)
/* 463:    */  {
/* 464:464 */    this.jdField_a_of_type_Boolean = paramBoolean;
/* 465:    */  }
/* 466:    */  
/* 496:    */  public final void a(xq paramxq)
/* 497:    */  {
/* 498:498 */    if (this.jdField_b_of_type_Boolean) {
/* 499:499 */      localObject1 = this.jdField_a_of_type_JavaUtilSet.iterator();
/* 500:500 */      while (((Iterator)localObject1).hasNext()) {
/* 501:501 */        lA locallA = (lA)((Iterator)localObject1).next();
/* 502:502 */        if (!this.jdField_a_of_type_LE.getState().getLocalAndRemoteObjectContainer().getLocalObjects().containsKey(locallA.jdField_a_of_type_Cw.getId())) {
/* 503:503 */          ((Iterator)localObject1).remove();
/* 504:    */        }
/* 505:    */      }
/* 506:506 */      this.jdField_b_of_type_Boolean = false;
/* 507:    */    }
/* 508:    */    
/* 509:509 */    Object localObject1 = this; if (!this.jdField_b_of_type_ComBulletphysicsUtilObjectArrayList.isEmpty()) synchronized (((lz)localObject1).jdField_b_of_type_ComBulletphysicsUtilObjectArrayList) { ArrayList localArrayList1 = new ArrayList(); cw localcw; Iterator localIterator; Object localObject2; ly locally; for (ArrayList localArrayList2 = new ArrayList(); !((lz)localObject1).jdField_b_of_type_ComBulletphysicsUtilObjectArrayList.isEmpty(); ) { localcw = (cw)((lz)localObject1).jdField_b_of_type_ComBulletphysicsUtilObjectArrayList.remove(0); for (localIterator = ((lz)localObject1).jdField_a_of_type_JavaUtilSet.iterator(); localIterator.hasNext();) if ((localObject2 = (lA)localIterator.next()).jdField_a_of_type_Cw == localcw) localArrayList1.add(localObject2); } System.err.println("[PlayerControllerState][CLEANUP] Units: " + localArrayList1);System.err.println("[PlayerControllerState][CLEANUP] Requests: " + localArrayList2);((lz)localObject1).jdField_a_of_type_JavaUtilSet.removeAll(localArrayList1); synchronized (((lz)localObject1).jdField_a_of_type_ComBulletphysicsUtilObjectArrayList) { ((lz)localObject1).jdField_a_of_type_ComBulletphysicsUtilObjectArrayList.removeAll(localArrayList2); } }
/* 510:510 */    e();
/* 511:    */    
/* 512:512 */    if (!this.jdField_a_of_type_Boolean) {
/* 513:513 */      paramxq = null;a(paramxq, this.jdField_a_of_type_LE);
/* 514:    */    }
/* 515:    */  }
/* 516:    */  
/* 517:    */  public final void a(cw paramcw) {
/* 518:518 */    synchronized (this.jdField_b_of_type_ComBulletphysicsUtilObjectArrayList) {
/* 519:519 */      this.jdField_b_of_type_ComBulletphysicsUtilObjectArrayList.add(paramcw);
/* 520:520 */      return;
/* 521:    */    }
/* 522:    */  }
/* 523:    */  
/* 524:    */  public final void b() {
/* 525:525 */    this.jdField_b_of_type_Boolean = true;
/* 526:    */  }
/* 527:    */  
/* 530:    */  public static void c() {}
/* 531:    */  
/* 534:    */  public final void a(le paramle)
/* 535:    */  {
/* 536:536 */    if ((paramle.a().a() instanceof cw)) {
/* 537:    */      lA locallA1;
/* 538:538 */      (locallA1 = new lA()).jdField_a_of_type_Cw = ((cw)paramle.a().a());
/* 539:    */      
/* 540:540 */      locallA1.jdField_a_of_type_LE = this.jdField_a_of_type_LE;
/* 541:541 */      locallA1.jdField_a_of_type_JavaLangObject = paramle.a(new q());
/* 542:542 */      lA locallA2 = locallA1;paramle = this; if (((locallA2.jdField_a_of_type_LE == paramle.jdField_a_of_type_LE) && (paramle.a(locallA2)) ? 1 : 0) != 0) {
/* 543:543 */        System.err.println("Forcing " + this + " to leave a controllable element");
/* 544:    */        
/* 545:545 */        a(locallA1.jdField_a_of_type_Cw, null, (q)locallA1.jdField_a_of_type_JavaLangObject, null, false);
/* 546:    */      }
/* 547:    */    }
/* 548:    */  }
/* 549:    */  
/* 550:    */  public final void d()
/* 551:    */  {
/* 552:552 */    if ((!c) && (!this.jdField_a_of_type_LE.isOnServer())) throw new AssertionError();
/* 553:553 */    for (Iterator localIterator = this.jdField_a_of_type_JavaUtilSet.iterator(); localIterator.hasNext();) {
/* 554:    */      lA locallA;
/* 555:555 */      if (((locallA = (lA)localIterator.next()).jdField_a_of_type_Cw instanceof SegmentController)) {
/* 556:556 */        Object localObject = (SegmentController)locallA.jdField_a_of_type_Cw;
/* 557:    */        
/* 558:558 */        q localq = (q)locallA.jdField_a_of_type_JavaLangObject;
/* 559:    */        
/* 560:560 */        localObject = ((SegmentController)localObject).getSegmentBuffer().a(localq, true);
/* 561:    */        
/* 562:562 */        System.err.println("PARAMETER " + locallA.jdField_a_of_type_JavaLangObject + ": POINT " + localObject);
/* 563:    */        
/* 564:564 */        if ((((le)localObject).a() == 0) || (((le)localObject).a() <= 0)) {
/* 565:565 */          localObject = this.jdField_a_of_type_LE.a();
/* 566:566 */          System.err.println("FORCING PLAYER OUT OF SHIP " + this.jdField_a_of_type_LE + ": " + this + " from " + locallA.jdField_a_of_type_Cw + ": " + localObject);
/* 567:567 */          if ((!c) && (localObject == null)) throw new AssertionError();
/* 568:568 */          a(locallA.jdField_a_of_type_Cw, (cw)localObject, localq, new q(), false);
/* 569:    */        }
/* 570:    */      }
/* 571:    */    }
/* 572:    */  }
/* 573:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     lz
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */