/*   1:    */import com.bulletphysics.linearmath.Transform;
/*   2:    */import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*   3:    */import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
/*   4:    */import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*   5:    */import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*   6:    */import java.io.PrintStream;
/*   7:    */import java.util.ArrayList;
/*   8:    */import java.util.HashMap;
/*   9:    */import java.util.HashSet;
/*  10:    */import java.util.Iterator;
/*  11:    */import javax.vecmath.Vector3f;
/*  12:    */import org.lwjgl.input.Keyboard;
/*  13:    */import org.schema.common.FastMath;
/*  14:    */import org.schema.game.common.controller.BlockedByDockedElementException;
/*  15:    */import org.schema.game.common.controller.EditableSendableSegmentController;
/*  16:    */import org.schema.game.common.controller.ElementPositionBlockedException;
/*  17:    */import org.schema.game.common.controller.SegmentController;
/*  18:    */import org.schema.game.common.data.element.ElementInformation;
/*  19:    */import org.schema.game.common.data.element.ElementKeyMap;
/*  20:    */import org.schema.game.common.data.player.inventory.NoSlotFreeException;
/*  21:    */import org.schema.game.network.objects.NetworkPlayer;
/*  22:    */import org.schema.game.network.objects.NetworkSegmentController;
/*  23:    */import org.schema.schine.graphicsengine.camera.Camera;
/*  24:    */import org.schema.schine.network.NetworkStateContainer;
/*  25:    */import org.schema.schine.network.objects.remote.RemoteBuffer;
/*  26:    */import org.schema.schine.network.objects.remote.RemoteIntPrimitive;
/*  27:    */import org.schema.schine.network.objects.remote.RemoteInteger;
/*  28:    */import org.schema.schine.network.objects.remote.RemoteShort;
/*  29:    */
/*  45:    */public class ar
/*  46:    */  extends U
/*  47:    */{
/*  48:    */  private int jdField_a_of_type_Int;
/*  49:    */  private HashMap jdField_a_of_type_JavaUtilHashMap;
/*  50:    */  private bi jdField_a_of_type_Bi;
/*  51:    */  private ax jdField_a_of_type_Ax;
/*  52:    */  private aG jdField_a_of_type_AG;
/*  53:    */  private mF jdField_a_of_type_MF;
/*  54:    */  private ai jdField_a_of_type_Ai;
/*  55: 55 */  private int b = 4;
/*  56:    */  
/*  57: 57 */  public ar(ct paramct) { super(paramct);
/*  58: 58 */    paramct = this;ar localar = this;this.jdField_a_of_type_JavaUtilHashMap = new HashMap();localar.jdField_a_of_type_JavaUtilHashMap.put(Integer.valueOf(2), Integer.valueOf(0));localar.jdField_a_of_type_JavaUtilHashMap.put(Integer.valueOf(3), Integer.valueOf(1));localar.jdField_a_of_type_JavaUtilHashMap.put(Integer.valueOf(4), Integer.valueOf(2));localar.jdField_a_of_type_JavaUtilHashMap.put(Integer.valueOf(5), Integer.valueOf(3));localar.jdField_a_of_type_JavaUtilHashMap.put(Integer.valueOf(6), Integer.valueOf(4));localar.jdField_a_of_type_JavaUtilHashMap.put(Integer.valueOf(7), Integer.valueOf(5));localar.jdField_a_of_type_JavaUtilHashMap.put(Integer.valueOf(8), Integer.valueOf(6));localar.jdField_a_of_type_JavaUtilHashMap.put(Integer.valueOf(9), Integer.valueOf(7));localar.jdField_a_of_type_JavaUtilHashMap.put(Integer.valueOf(10), Integer.valueOf(8));localar.jdField_a_of_type_JavaUtilHashMap.put(Integer.valueOf(11), Integer.valueOf(9));paramct.jdField_a_of_type_Bi = new bi(paramct.a());paramct.jdField_a_of_type_AG = new aG(paramct.a());paramct.jdField_a_of_type_Ax = new ax(paramct.a());paramct.jdField_a_of_type_Ai = new ai(paramct.a());paramct.jdField_a_of_type_JavaUtilHashSet.add(paramct.jdField_a_of_type_Ai);paramct.jdField_a_of_type_JavaUtilHashSet.add(paramct.jdField_a_of_type_Bi);paramct.jdField_a_of_type_JavaUtilHashSet.add(paramct.jdField_a_of_type_AG);paramct.jdField_a_of_type_JavaUtilHashSet.add(paramct.jdField_a_of_type_Ax);
/*  59:    */  }
/*  60:    */  
/*  62:    */  public final int a(EditableSendableSegmentController paramEditableSendableSegmentController, Vector3f paramVector3f1, Vector3f paramVector3f2, ag paramag, ju paramju, az paramaz, float paramFloat)
/*  63:    */  {
/*  64: 64 */    if (!a().a().c(paramEditableSendableSegmentController)) {
/*  65: 65 */      return 0;
/*  66:    */    }
/*  67:    */    
/*  68:    */    short s;
/*  69:    */    q localq;
/*  70: 70 */    if ((s = a()) == 0) {
/*  71: 71 */      a().a().d("No element available to build!\nSelected slot is empty!");
/*  72: 72 */      if (paramaz.jdField_a_of_type_Int != 0)
/*  73:    */      {
/*  74:    */        try
/*  75:    */        {
/*  76: 76 */          le localle = paramEditableSendableSegmentController.getNextToNearestPiece(paramVector3f1, paramVector3f2, new q(), paramFloat, true, new q(), new q());
/*  77:    */          
/*  78: 78 */          if ((paramaz.jdField_a_of_type_Int > 0) && (localle != null)) {
/*  79: 79 */            localq = localle.a(new q());
/*  80: 80 */            switch (paramaz.jdField_a_of_type_Int) {
/*  81:    */            case 1: 
/*  82: 82 */              System.err.println("SYM XY PLANE SET");
/*  83: 83 */              paramaz.jdField_a_of_type_Q.c = localq.c;
/*  84: 84 */              paramaz.jdField_a_of_type_Boolean = true;
/*  85: 85 */              break;
/*  86:    */            case 2: 
/*  87: 87 */              System.err.println("SYM XZ PLANE SET");
/*  88: 88 */              paramaz.jdField_b_of_type_Q.b = localq.b;
/*  89: 89 */              paramaz.jdField_b_of_type_Boolean = true;
/*  90: 90 */              break;
/*  91:    */            case 4: 
/*  92: 92 */              System.err.println("SYM YZ PLANE SET");
/*  93: 93 */              paramaz.jdField_c_of_type_Q.jdField_a_of_type_Int = localq.jdField_a_of_type_Int;
/*  94: 94 */              paramaz.jdField_c_of_type_Boolean = true;
/*  95:    */            }
/*  96:    */            
/*  97: 97 */            paramaz.jdField_a_of_type_Int = 0;
/*  98:    */          }
/*  99:    */        } catch (ElementPositionBlockedException localElementPositionBlockedException2) {
/* 100:100 */          
/* 101:    */          
/* 106:106 */            localElementPositionBlockedException2;
/* 107:    */        }
/* 108:    */        catch (BlockedByDockedElementException localBlockedByDockedElementException2) {
/* 109:103 */          
/* 110:    */          
/* 112:106 */            localBlockedByDockedElementException2;
/* 113:    */        }
/* 114:    */      }
/* 115:    */      
/* 117:108 */      return 0;
/* 118:    */    }
/* 119:110 */    if (a().a().getInventory(null).a(this.jdField_a_of_type_Int)) {
/* 120:111 */      a().a().d("No element available to build!\nSelected slot is empty!");
/* 121:112 */      return 0;
/* 122:    */    }
/* 123:114 */    if ((!d) && (a().a().getInventory(null).a(this.jdField_a_of_type_Int) <= 0)) { throw new AssertionError();
/* 124:    */    }
/* 125:116 */    int i = a().a().getInventory(null).a(this.jdField_a_of_type_Int);
/* 126:    */    try
/* 127:    */    {
/* 128:119 */      localq = new q(1, 1, 1);
/* 129:    */      
/* 130:121 */      if (cv.U.a()) {
/* 131:122 */        localq.b(this.jdField_a_of_type_Ai.a());
/* 132:    */      }
/* 133:    */      
/* 134:125 */      localObject = ElementKeyMap.getInfo(s);
/* 135:126 */      boolean bool = true;
/* 136:    */      
/* 137:    */      int j;
/* 138:129 */      if ((j = this.b) > 7) {
/* 139:130 */        if (((ElementInformation)localObject).getBlockStyle() == 1) {
/* 140:131 */          j -= 8;
/* 141:132 */          bool = false;
/* 142:133 */          System.err.println("[CLIENT][PLACEBLOCK] BLOCK ORIENTATION (1) EXTENSION: " + (j + 4) + " -> " + j);
/* 143:134 */        } else if (((ElementInformation)localObject).getBlockStyle() == 2) {
/* 144:135 */          j -= 8;
/* 145:136 */          bool = false;
/* 146:137 */          System.err.println("[CLIENT][PLACEBLOCK] BLOCK ORIENTATION (2) EXTENSION: " + (j + 8) + " -> " + j);
/* 147:    */        } else {
/* 148:139 */          System.err.println("[CLIENT][PLACEBLOCK] Exception: Block orientation was set over 8 on block style " + localObject + ": " + j);
/* 149:140 */          j = 0;
/* 150:    */        }
/* 151:    */      }
/* 152:    */      
/* 158:149 */      if ((paramag = paramEditableSendableSegmentController.getNearestIntersection(s, paramVector3f1, paramVector3f2, paramag, j, bool, paramju, localq, i, paramFloat, paramaz)) > 0)
/* 159:    */      {
/* 162:153 */        paramju = ((ct)paramEditableSendableSegmentController.getState()).a().getInventory(null);
/* 163:    */        
/* 165:    */        try
/* 166:    */        {
/* 167:158 */          paramju.a(this.jdField_a_of_type_Int, s, -paramag);
/* 168:    */          
/* 169:160 */          a().a().sendInventoryModification(this.jdField_a_of_type_Int, null);
/* 170:    */          
/* 171:162 */          xe.b("0022_action - buttons push big");
/* 172:163 */          if (s == 56) {
/* 173:164 */            a().a().d("INFO:\nUse gravity when you are\noutside the ship. Look at\ngravity module and press 'R'");
/* 174:    */          }
/* 175:    */          
/* 178:169 */          paramEditableSendableSegmentController.getNetworkObject().lastModifiedPlayerId.forceClientUpdates();
/* 179:170 */          paramEditableSendableSegmentController.getNetworkObject().lastModifiedPlayerId.set(a().a().getId());
/* 180:171 */          return paramag;
/* 181:    */        } catch (NoSlotFreeException localNoSlotFreeException) {
/* 182:173 */          localNoSlotFreeException.printStackTrace();
/* 183:    */          
/* 184:175 */          System.err.println("[WARNING] ILLEGIT BLOCK WITH SLOT " + this.jdField_a_of_type_Int);
/* 185:    */          
/* 186:177 */          paramEditableSendableSegmentController.getNearestIntersectingElementPosition(paramVector3f1, paramVector3f2, new q(1, 1, 1), paramFloat, new as(), paramaz);
/* 188:    */        }
/* 189:    */        
/* 191:    */      }
/* 192:    */      
/* 195:    */    }
/* 196:    */    catch (ElementPositionBlockedException localElementPositionBlockedException1)
/* 197:    */    {
/* 200:191 */      Object localObject = "unknown";
/* 201:192 */      if (localElementPositionBlockedException1.a != null) {
/* 202:193 */        if ((localElementPositionBlockedException1.a instanceof SegmentController)) {
/* 203:194 */          localObject = ((SegmentController)localElementPositionBlockedException1.a).getRealName();
/* 204:195 */        } else if ((localElementPositionBlockedException1.a instanceof mF)) {
/* 205:196 */          localObject = ((mF)localElementPositionBlockedException1.a).toNiceString();
/* 206:    */        }
/* 207:    */      }
/* 208:199 */      a().a().b("Cannot build here!\nPosition is blocked by\n" + (String)localObject);
/* 209:    */    } catch (BlockedByDockedElementException localBlockedByDockedElementException1) {
/* 210:201 */      a().a().a().a(localBlockedByDockedElementException1.a);
/* 211:202 */      a().a().b("Cannot build here!\nPosition blocked\nby active docking area!");
/* 212:    */    }
/* 213:204 */    return 0;
/* 214:    */  }
/* 215:    */  
/* 216:207 */  public final void a(EditableSendableSegmentController paramEditableSendableSegmentController, Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat, az paramaz) { if (!a().a().c(paramEditableSendableSegmentController)) {
/* 217:208 */      return;
/* 218:    */    }
/* 219:    */    
/* 222:213 */    q localq = new q(1, 1, 1);
/* 223:    */    
/* 224:215 */    if (cv.U.a()) {
/* 225:216 */      localq.b(this.jdField_a_of_type_Ai.a());
/* 226:    */    }
/* 227:218 */    IntOpenHashSet localIntOpenHashSet = new IntOpenHashSet(localq.jdField_a_of_type_Int * localq.b * localq.c);
/* 228:    */    
/* 229:220 */    paramEditableSendableSegmentController.getNearestIntersectingElementPosition(paramVector3f1, paramVector3f2, localq, paramFloat, new at(this, paramEditableSendableSegmentController, localIntOpenHashSet), paramaz);
/* 230:    */    
/* 251:242 */    xe.b("0022_action - buttons push medium");
/* 252:243 */    a().a().sendInventoryModification(localIntOpenHashSet, null);
/* 253:244 */    paramEditableSendableSegmentController.getNetworkObject().lastModifiedPlayerId.forceClientUpdates();
/* 254:245 */    paramEditableSendableSegmentController.getNetworkObject().lastModifiedPlayerId.set(a().a().getId());
/* 255:    */  }
/* 256:    */  
/* 259:    */  public final int a()
/* 260:    */  {
/* 261:252 */    return this.b;
/* 262:    */  }
/* 263:    */  
/* 264:    */  public final ai a() {
/* 265:256 */    return this.jdField_a_of_type_Ai;
/* 266:    */  }
/* 267:    */  
/* 269:    */  public final bi a()
/* 270:    */  {
/* 271:262 */    return this.jdField_a_of_type_Bi;
/* 272:    */  }
/* 273:    */  
/* 274:    */  public final aG a() {
/* 275:266 */    return this.jdField_a_of_type_AG;
/* 276:    */  }
/* 277:    */  
/* 319:    */  public final ax a()
/* 320:    */  {
/* 321:312 */    return this.jdField_a_of_type_Ax;
/* 322:    */  }
/* 323:    */  
/* 324:315 */  public final mF a() { return this.jdField_a_of_type_MF; }
/* 325:    */  
/* 328:    */  public final int b()
/* 329:    */  {
/* 330:321 */    return this.jdField_a_of_type_Int;
/* 331:    */  }
/* 332:    */  
/* 333:324 */  public final short a() { return a().a().getInventory(null).a(this.jdField_a_of_type_Int); }
/* 334:    */  
/* 335:    */  public final int a(int paramInt) {
/* 336:327 */    if (this.jdField_a_of_type_JavaUtilHashMap.containsKey(Integer.valueOf(paramInt))) {
/* 337:328 */      return ((Integer)this.jdField_a_of_type_JavaUtilHashMap.get(Integer.valueOf(paramInt))).intValue();
/* 338:    */    }
/* 339:330 */    System.err.println("[WARNING] UNKNOWN SLOT KEY: " + paramInt + ": " + this.jdField_a_of_type_JavaUtilHashMap);
/* 340:331 */    return -1;
/* 341:    */  }
/* 342:    */  
/* 343:    */  public void handleKeyEvent() {
/* 344:335 */    if (H.b()) {
/* 345:336 */      return;
/* 346:    */    }
/* 347:    */    
/* 350:341 */    synchronized (a().b()) {
/* 351:    */      int i;
/* 352:343 */      if ((i = a().b().size()) > 0)
/* 353:    */      {
/* 354:345 */        ((H)a().b().get(i - 1)).handleKeyEvent();
/* 355:346 */        return;
/* 356:    */      }
/* 357:    */    }
/* 358:    */    
/* 359:350 */    if (Keyboard.getEventKeyState())
/* 360:    */    {
/* 363:354 */      if (a().b().isEmpty())
/* 364:    */      {
/* 368:359 */        if (Keyboard.getEventKey() == cv.o.a()) {
/* 369:360 */          a().a().a().dropOrPickupSlots.add(new RemoteInteger(Integer.valueOf(this.jdField_a_of_type_Int), a().a().a()));
/* 370:    */        }
/* 371:    */        
/* 372:363 */        if (Keyboard.getEventKey() == cv.L.a()) {
/* 373:364 */          d(1);
/* 375:    */        }
/* 376:367 */        else if (Keyboard.getEventKey() == cv.M.a()) {
/* 377:368 */          d(-1);
/* 379:    */        }
/* 380:371 */        else if (Keyboard.getEventKey() == cv.N.a()) {
/* 381:372 */          ??? = this;Vector3f localVector3f1 = new Vector3f();Vector3f localVector3f2 = new Vector3f();float f = 0.0F; if (((ar)???).a().a() != null) localVector3f1.set(((ar)???).a().a().getWorldTransform().origin); else localVector3f1.set(xe.a().a()); Object localObject2 = null; for (Iterator localIterator = ((ar)???).a().a().values().iterator(); localIterator.hasNext(); f = localVector3f2.length()) { mF localmF = (mF)localIterator.next();localVector3f2.sub(localmF.getWorldTransformClient().origin, localVector3f1); if ((localmF != ((ar)???).a().a()) && ((localObject2 == null) || (localVector3f2.length() < f))) { System.err.println("!!!!!!!!!NEAREST IS NOW " + localmF);localObject2 = localmF; } } ((ar)???).jdField_a_of_type_MF = localObject2;
/* 383:    */        }
/* 384:375 */        else if (Keyboard.getEventKey() == cv.O.a()) {
/* 385:376 */          c();
/* 386:    */        }
/* 387:    */      }
/* 388:    */      
/* 392:383 */      ??? = this; if ((!this.jdField_a_of_type_Boolean) && (((U)???).jdField_b_of_type_Boolean)) { if ((!d) && (!((ar)???).a().b().isEmpty())) throw new AssertionError(); ((ar)???).a().a().a().handleKeyEvent(Keyboard.getEventKeyState(), Keyboard.getEventKey());
/* 393:    */      }
/* 394:    */    }
/* 395:386 */    if (a().b().isEmpty()) {
/* 396:387 */      super.handleKeyEvent();
/* 397:    */    }
/* 398:    */  }
/* 399:    */  
/* 401:    */  public final void b()
/* 402:    */  {
/* 403:394 */    xe.a().getWorldTransform();
/* 404:    */    
/* 405:396 */    if (a().a() != null) {
/* 406:397 */      a().a().getWorldTransform();
/* 407:    */    }
/* 408:    */    else
/* 409:    */    {
/* 410:401 */      new Transform().setIdentity();
/* 411:    */    }
/* 412:    */    
/* 433:424 */    if (Keyboard.getEventKey() == 52) {
/* 434:425 */      c((this.b + 1) % 8);
/* 435:    */      short s;
/* 436:427 */      if ((s = a()) != 0) {
/* 437:    */        ElementInformation localElementInformation;
/* 438:429 */        if ((localElementInformation = ElementKeyMap.getInfo(s)).getBlockStyle() > 0) {
/* 439:430 */          System.err.println("BLOCK ORIENTATION: " + this.b + "; " + dO.a[(localElementInformation.getBlockStyle() - 1)][org.schema.game.common.data.element.Element.orientationMapping[this.b]].toString());
/* 440:    */        }
/* 441:    */      }
/* 442:    */    }
/* 443:    */  }
/* 444:    */  
/* 470:    */  public final void a(xp paramxp)
/* 471:    */  {
/* 472:463 */    if ((a().a().a().a()) || (H.b()) || (a().b().size() > 0)) {
/* 473:464 */      return;
/* 474:    */    }
/* 475:    */    
/* 476:467 */    if ((xu.X.a().equals("SLOTS")) && (!Keyboard.isKeyDown(42)))
/* 477:    */    {
/* 478:469 */      this.jdField_a_of_type_Int = FastMath.b(this.jdField_a_of_type_Int + (paramxp.f < 0 ? -1 : paramxp.f > 0 ? 1 : 0), 10);
/* 479:    */    }
/* 480:    */    
/* 483:474 */    super.a(paramxp);
/* 484:    */  }
/* 485:    */  
/* 486:    */  public final boolean a() {
/* 487:478 */    if (this.jdField_a_of_type_JavaUtilHashMap.containsKey(Integer.valueOf(Keyboard.getEventKey()))) {
/* 488:479 */      int i = a();
/* 489:480 */      this.jdField_a_of_type_Int = ((Integer)this.jdField_a_of_type_JavaUtilHashMap.get(Integer.valueOf(Keyboard.getEventKey()))).intValue();
/* 490:    */      
/* 491:482 */      if (i != a()) {
/* 492:483 */        c(4);
/* 493:    */      }
/* 494:    */      
/* 495:486 */      System.err.println("Selected slot is now: " + this.jdField_a_of_type_Int);
/* 496:487 */      return true;
/* 497:    */    }
/* 498:489 */    return false;
/* 499:    */  }
/* 500:    */  
/* 535:    */  protected final void a(boolean paramBoolean)
/* 536:    */  {
/* 537:528 */    super.a(paramBoolean);
/* 538:529 */    if (paramBoolean) {
/* 539:530 */      a().a().a().keyboardOfController.set(Short.valueOf((short)0));
/* 540:    */    }
/* 541:    */  }
/* 542:    */  
/* 547:    */  public final void b(boolean paramBoolean)
/* 548:    */  {
/* 549:540 */    if (paramBoolean) {
/* 550:541 */      if ((!this.jdField_a_of_type_Bi.jdField_b_of_type_Boolean) && (!this.jdField_a_of_type_AG.jdField_b_of_type_Boolean) && (!this.jdField_a_of_type_AG.d()) && (!this.jdField_a_of_type_Bi.d())) {
/* 551:542 */        if ((!d) && (a().a() == null)) throw new AssertionError();
/* 552:543 */        this.jdField_a_of_type_AG.d(true);
/* 553:    */      }
/* 554:    */    } else {
/* 555:546 */      a().a().a().keyboardOfController.set(Short.valueOf((short)0));
/* 556:    */    }
/* 557:548 */    super.b(paramBoolean);
/* 558:    */  }
/* 559:    */  
/* 560:    */  private void d(int paramInt) {
/* 561:552 */    ObjectIterator localObjectIterator = a().a().values().iterator();
/* 562:553 */    int i = paramInt > 0 ? 2147483647 : -2147483648;
/* 563:554 */    int j = this.jdField_a_of_type_MF != null ? this.jdField_a_of_type_MF.getId() : -1;
/* 564:555 */    mF localmF; while (localObjectIterator.hasNext())
/* 565:    */    {
/* 566:557 */      localmF = (mF)localObjectIterator.next();
/* 567:558 */      if (this.jdField_a_of_type_MF == null) {
/* 568:559 */        e(-paramInt);
/* 569:560 */        return;
/* 570:    */      }
/* 571:    */      
/* 572:563 */      if ((paramInt > 0) && (localmF.getId() > j) && (localmF.getId() < i)) {
/* 573:564 */        i = localmF.getId();
/* 574:    */      }
/* 575:566 */      if ((paramInt <= 0) && (localmF.getId() < j) && (localmF.getId() > i)) {
/* 576:567 */        i = localmF.getId();
/* 577:    */      }
/* 578:    */    }
/* 579:570 */    if ((i == 2147483647) || (i == -2147483648)) {
/* 580:571 */      e(paramInt);
/* 581:572 */      return;
/* 582:    */    }
/* 583:574 */    if (a().getLocalAndRemoteObjectContainer().getLocalObjects().containsKey(i)) {
/* 584:575 */      localmF = (mF)a().getLocalAndRemoteObjectContainer().getLocalObjects().get(i);
/* 585:576 */      this.jdField_a_of_type_MF = localmF;
/* 586:    */    }
/* 587:    */  }
/* 588:    */  
/* 589:    */  private void e(int paramInt) {
/* 590:581 */    ObjectIterator localObjectIterator = a().a().values().iterator();
/* 591:582 */    int i = paramInt > 0 ? 2147483647 : -2147483648;
/* 592:    */    mF localmF;
/* 593:584 */    while (localObjectIterator.hasNext())
/* 594:    */    {
/* 595:586 */      localmF = (mF)localObjectIterator.next();
/* 596:587 */      if ((paramInt > 0) && (localmF.getId() < i)) {
/* 597:588 */        i = localmF.getId();
/* 598:    */      }
/* 599:590 */      if ((paramInt <= 0) && (localmF.getId() > i)) {
/* 600:591 */        i = localmF.getId();
/* 601:    */      }
/* 602:    */    }
/* 603:594 */    if (a().getLocalAndRemoteObjectContainer().getLocalObjects().containsKey(i)) {
/* 604:595 */      localmF = (mF)a().getLocalAndRemoteObjectContainer().getLocalObjects().get(i);
/* 605:596 */      this.jdField_a_of_type_MF = localmF;
/* 606:    */    }
/* 607:    */  }
/* 608:    */  
/* 609:    */  private void c() {
/* 610:601 */    if ((a().a() == null) || (a().a().a() == null)) {
/* 611:602 */      return;
/* 612:    */    }
/* 613:604 */    zG localzG = a().a().a();
/* 614:    */    
/* 615:606 */    Vector3f localVector3f1 = new Vector3f();
/* 616:607 */    float f = 0.0F;
/* 617:608 */    Object localObject = null;
/* 618:609 */    Vector3f localVector3f2 = localzG.a(new Vector3f());
/* 619:610 */    for (Iterator localIterator = a().a().values().iterator(); localIterator.hasNext();) { mF localmF;
/* 620:611 */      if ((localmF = (mF)localIterator.next()) != a().a()) {
/* 621:612 */        localVector3f1.set(localmF.getWorldTransform().origin);
/* 622:    */        
/* 626:617 */        if (a().a() != null) {
/* 627:618 */          localVector3f1.sub(a().a().getWorldTransform().origin);
/* 628:    */        } else {
/* 629:620 */          localVector3f1.sub(xe.a().a());
/* 630:    */        }
/* 631:    */        
/* 632:623 */        Vector3f localVector3f3 = localzG.a(localmF.getWorldTransformClient().origin, new Vector3f(), true);
/* 633:    */        
/* 634:    */        Vector3f localVector3f4;
/* 635:626 */        (localVector3f4 = new Vector3f()).sub(localVector3f3, localVector3f2);
/* 636:    */        
/* 637:628 */        if ((localVector3f4.length() < 250.0F) && (
/* 638:629 */          (localObject == null) || (localVector3f4.length() < f))) {
/* 639:630 */          localObject = localmF;
/* 640:631 */          f = localVector3f4.length();
/* 641:    */        }
/* 642:    */      }
/* 643:    */    }
/* 644:635 */    this.jdField_a_of_type_MF = localObject;
/* 645:    */  }
/* 646:    */  
/* 666:    */  public final void c(int paramInt)
/* 667:    */  {
/* 668:659 */    System.err.println("SET ORIENTATION " + paramInt);
/* 669:660 */    this.b = paramInt;
/* 670:    */  }
/* 671:    */  
/* 672:663 */  public final void a(mF parammF) { this.jdField_a_of_type_MF = parammF; }
/* 673:    */  
/* 678:    */  public final void a(xq paramxq)
/* 679:    */  {
/* 680:671 */    super.a(paramxq);
/* 681:    */    
/* 683:674 */    if (a().b().isEmpty())
/* 684:    */    {
/* 685:676 */      a().a().a();
/* 686:    */    }
/* 687:    */  }
/* 688:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ar
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */