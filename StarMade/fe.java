/*   1:    */import java.awt.Color;
/*   2:    */import java.awt.Font;
/*   3:    */import java.util.ArrayList;
/*   4:    */import java.util.LinkedList;
/*   5:    */import java.util.List;
/*   6:    */import java.util.Observable;
/*   7:    */import java.util.Observer;
/*   8:    */import org.lwjgl.input.Keyboard;
/*   9:    */import org.lwjgl.opengl.GL11;
/*  10:    */import org.lwjgl.util.vector.Vector4f;
/*  11:    */import org.newdawn.slick.SlickException;
/*  12:    */import org.newdawn.slick.UnicodeFont;
/*  13:    */import org.newdawn.slick.font.effects.ColorEffect;
/*  14:    */import org.newdawn.slick.font.effects.OutlineEffect;
/*  15:    */import org.schema.game.common.controller.elements.ElementCollectionManager;
/*  16:    */import org.schema.game.common.controller.elements.ShieldContainerInterface;
/*  17:    */import org.schema.game.common.controller.elements.shield.ShieldCollectionManager;
/*  18:    */import org.schema.schine.network.objects.Sendable;
/*  19:    */
/*  59:    */public final class fe
/*  60:    */  implements Observer, xg, xs
/*  61:    */{
/*  62:    */  private fg jdField_a_of_type_Fg;
/*  63:    */  private ct jdField_a_of_type_Ct;
/*  64:    */  private u jdField_a_of_type_U;
/*  65:    */  private yu jdField_a_of_type_Yu;
/*  66:    */  private fi jdField_a_of_type_Fi;
/*  67:    */  private yE jdField_a_of_type_YE;
/*  68:    */  private yP jdField_a_of_type_YP;
/*  69:    */  private yR jdField_a_of_type_YR;
/*  70: 70 */  private float jdField_a_of_type_Float = 1.0F;
/*  71:    */  private float b;
/*  72:    */  private float c;
/*  73: 73 */  private float d = -1.0F;
/*  74:    */  
/*  76:    */  public final LinkedList a;
/*  77:    */  
/*  79:    */  public final LinkedList b;
/*  80:    */  
/*  82:    */  public final LinkedList c;
/*  83:    */  
/*  85:    */  public fm a;
/*  86:    */  
/*  88:    */  public fm b;
/*  89:    */  
/*  91:    */  private fp jdField_a_of_type_Fp;
/*  92:    */  
/*  93: 93 */  private int jdField_a_of_type_Int = 1;
/*  94:    */  private ii jdField_a_of_type_Ii;
/*  95:    */  private ff jdField_a_of_type_Ff;
/*  96:    */  private iy jdField_a_of_type_Iy;
/*  97:    */  private eS jdField_a_of_type_ES;
/*  98:    */  
/*  99:    */  public fe(u paramu)
/* 100:    */  {
/* 101: 71 */    this.jdField_b_of_type_Float = 0.2F;
/* 102: 72 */    this.jdField_c_of_type_Float = 0.3F;
/* 103:    */    
/* 105: 75 */    this.jdField_a_of_type_JavaUtilLinkedList = new LinkedList();
/* 106: 76 */    this.jdField_b_of_type_JavaUtilLinkedList = new LinkedList();
/* 107: 77 */    this.jdField_c_of_type_JavaUtilLinkedList = new LinkedList();
/* 108:    */    
/* 133:103 */    this.jdField_a_of_type_Ct = paramu.a();
/* 134:104 */    this.jdField_a_of_type_U = paramu;
/* 135:105 */    this.jdField_a_of_type_U.addObserver(this);
/* 136:    */  }
/* 137:    */  
/* 143:    */  public final void a() {}
/* 144:    */  
/* 150:    */  private void e()
/* 151:    */  {
/* 152:122 */    this.jdField_a_of_type_ES.h(48);
/* 153:123 */    this.jdField_a_of_type_Fi.h(48);
/* 154:124 */    this.jdField_a_of_type_YP.h(48);
/* 155:125 */    this.jdField_a_of_type_Yu.h(9);
/* 156:126 */    this.jdField_a_of_type_YE.h(6);
/* 157:127 */    this.jdField_a_of_type_Ii.h(48);
/* 158:    */  }
/* 159:    */  
/* 160:    */  public final void b() {
/* 161:131 */    if (xm.b()) {
/* 162:132 */      e();
/* 163:    */    }
/* 164:    */    
/* 171:141 */    GL11.glClear(256);
/* 172:142 */    if (xu.K.b()) {
/* 173:143 */      return;
/* 174:    */    }
/* 175:145 */    if (xu.J.b())
/* 176:    */    {
/* 178:148 */      if (c()) {
/* 179:149 */        this.jdField_a_of_type_Fp.b();
/* 180:    */      }
/* 181:    */      
/* 182:152 */      if (!this.jdField_a_of_type_Ct.a().a().a().c()) {
/* 183:153 */        this.jdField_a_of_type_Fg.b();
/* 184:    */      }
/* 185:    */      
/* 186:156 */      int i = (!this.jdField_a_of_type_Ct.a().a().a().a().g()) && (!this.jdField_a_of_type_Ct.a().a().a().a().g()) && (!this.jdField_a_of_type_Ct.a().a().a().a().g()) && (!this.jdField_a_of_type_Ct.a().a().a().a().c()) && (!this.jdField_a_of_type_Ct.a().a().a().a().g()) && (!this.jdField_a_of_type_Ct.a().a().a().a().g()) && (!this.jdField_a_of_type_Ct.a().a().a().a().g()) && (!b()) ? 1 : 0;
/* 187:    */      
/* 189:159 */      yz.i();
/* 190:    */      
/* 193:163 */      if (i != 0) {
/* 194:164 */        h.a("HUD");
/* 195:165 */        this.jdField_a_of_type_Ii.b();
/* 196:166 */        h.b("HUD");
/* 197:167 */      } else if (b()) {
/* 198:168 */        this.jdField_a_of_type_Ii.a().e();
/* 199:    */      }
/* 200:    */      
/* 201:171 */      if (this.jdField_a_of_type_Ct.d()) {
/* 202:172 */        this.jdField_a_of_type_YE.b();
/* 203:    */      }
/* 204:174 */      yz.h();
/* 205:    */    }
/* 206:    */    
/* 208:178 */    yz.i();
/* 209:179 */    synchronized (this.jdField_a_of_type_Ct.b())
/* 210:    */    {
/* 211:181 */      for (int k = 0; k < this.jdField_a_of_type_Ct.b().size(); k++) {
/* 212:182 */        ((H)this.jdField_a_of_type_Ct.b().get(k)).a().b();
/* 213:    */      }
/* 214:    */    }
/* 215:    */    
/* 217:187 */    yz.h = true;
/* 218:188 */    for (int j = 0; j < this.jdField_a_of_type_Ct.a().size(); j++)
/* 219:    */    {
/* 220:    */      H localH;
/* 221:    */      
/* 222:192 */      (localH = (H)this.jdField_a_of_type_Ct.a().get(j)).f();
/* 223:193 */      localH.a().b();
/* 224:194 */      if (System.currentTimeMillis() - localH.a() > 200L) {
/* 225:195 */        this.jdField_a_of_type_Ct.a().remove(j);
/* 226:196 */        j--;
/* 227:    */      }
/* 228:    */    }
/* 229:199 */    yz.h = false;
/* 230:    */    
/* 234:204 */    if ((!this.jdField_a_of_type_Ct.a().a().a().c()) && (Keyboard.isKeyDown(cv.Q.a()))) {
/* 235:205 */      this.jdField_a_of_type_Fi.b();
/* 236:    */    }
/* 237:    */    
/* 238:208 */    if ((!this.jdField_a_of_type_Ct.a().a().a().c()) && (Keyboard.isKeyDown(61))) {
/* 239:209 */      this.jdField_a_of_type_ES.b();
/* 240:    */    }
/* 241:    */    
/* 244:214 */    this.jdField_a_of_type_Yu.b();
/* 245:    */    
/* 246:216 */    yz.h();
/* 247:    */    
/* 248:218 */    if (xu.ac.b())
/* 249:    */    {
/* 250:220 */      if (this.jdField_b_of_type_Fm != null) {
/* 251:221 */        yz.i();
/* 252:222 */        this.jdField_b_of_type_Fm.b();
/* 253:223 */        yz.h();
/* 254:    */      }
/* 255:    */      
/* 256:226 */      if (this.jdField_a_of_type_Fm != null) {
/* 257:227 */        yz.i();
/* 258:228 */        this.jdField_a_of_type_Fm.a().y = (this.jdField_a_of_type_Fm.a() + 5.0F);
/* 259:229 */        this.jdField_a_of_type_Fm.b();
/* 260:230 */        yz.h();
/* 261:    */      }
/* 262:    */      
/* 264:234 */      synchronized (this.jdField_a_of_type_JavaUtilLinkedList) {
/* 265:235 */        for (int m = 0; m < this.jdField_a_of_type_JavaUtilLinkedList.size(); m++) {
/* 266:236 */          yz.i();
/* 267:237 */          ((fl)this.jdField_a_of_type_JavaUtilLinkedList.get(m)).b();
/* 268:238 */          yz.h();
/* 269:    */        }
/* 270:    */      }
/* 271:241 */      synchronized (this.jdField_b_of_type_JavaUtilLinkedList) {
/* 272:242 */        for (int n = 0; n < this.jdField_b_of_type_JavaUtilLinkedList.size(); n++) {
/* 273:243 */          yz.i();
/* 274:244 */          ((fq)this.jdField_b_of_type_JavaUtilLinkedList.get(n)).b();
/* 275:245 */          yz.h();
/* 276:    */        }
/* 277:    */      }
/* 278:248 */      synchronized (this.jdField_c_of_type_JavaUtilLinkedList) {
/* 279:249 */        for (int i1 = 0; i1 < this.jdField_c_of_type_JavaUtilLinkedList.size(); i1++) {
/* 280:250 */          yz.i();
/* 281:251 */          ((eQ)this.jdField_c_of_type_JavaUtilLinkedList.get(i1)).b();
/* 282:252 */          yz.h();
/* 283:    */        }
/* 284:    */      }
/* 285:    */    }
/* 286:    */    
/* 288:258 */    if (((xu.O.b()) && (this.jdField_a_of_type_Ct.a().a().c()) ? 1 : 0) != 0) { if ((this.jdField_a_of_type_Ct.a().a() != null) && (this.jdField_a_of_type_Ct.a().a())) {
/* 289:259 */        yz.i();
/* 290:260 */        this.jdField_a_of_type_Iy.b();
/* 291:261 */        yz.h();
/* 292:    */      }
/* 293:    */    }
/* 294:264 */    yz.i();
/* 295:265 */    this.jdField_a_of_type_Ff.a(this.jdField_a_of_type_Ct.a().a().g());
/* 296:266 */    this.jdField_a_of_type_Ff.b();
/* 297:267 */    yz.h();
/* 298:    */    
/* 299:269 */    ??? = this; if (this.d < ((fe)???).jdField_b_of_type_Float) { ((fe)???).jdField_a_of_type_YR.a().w = (((fe)???).d / ((fe)???).jdField_b_of_type_Float * ((fe)???).jdField_c_of_type_Float);yz.i();((fe)???).jdField_a_of_type_YR.b();yz.h();
/* 300:    */    }
/* 301:271 */    if (wV.b) {
/* 302:272 */      yz.i();
/* 303:273 */      this.jdField_a_of_type_YP.b();
/* 304:274 */      yz.h();
/* 305:    */    }
/* 306:    */    
/* 308:278 */    this.jdField_a_of_type_Fg.e();
/* 309:    */  }
/* 310:    */  
/* 311:281 */  private boolean b() { return this.jdField_a_of_type_Ct.a().a().a().a().g(); }
/* 312:    */  
/* 342:    */  private void a(int paramInt, U paramU)
/* 343:    */  {
/* 344:314 */    int i = paramInt;
/* 345:315 */    if ((this.jdField_a_of_type_Int & paramInt) == paramInt) {
/* 346:316 */      i = 0;
/* 347:    */    }
/* 348:    */    
/* 349:319 */    this.jdField_a_of_type_Int += (paramU.g() ? i : -paramInt);
/* 350:    */  }
/* 351:    */  
/* 370:    */  public final boolean a()
/* 371:    */  {
/* 372:342 */    return this.jdField_a_of_type_Fg.a();
/* 373:    */  }
/* 374:    */  
/* 380:    */  public final void c()
/* 381:    */  {
/* 382:352 */    this.jdField_a_of_type_Fg = new fg(this.jdField_a_of_type_Ct);
/* 383:353 */    this.jdField_a_of_type_Fg.c();
/* 384:    */    
/* 386:356 */    this.jdField_a_of_type_YR = new yR(this.jdField_a_of_type_Ct);
/* 387:357 */    this.jdField_a_of_type_YR.a().set(1.0F, 0.0F, 0.0F, 0.0F);
/* 388:358 */    this.jdField_a_of_type_YR.c();
/* 389:    */    
/* 399:369 */    this.jdField_a_of_type_Fi = new fi(this.jdField_a_of_type_Ct);
/* 400:370 */    this.jdField_a_of_type_ES = new eS(this.jdField_a_of_type_Ct);
/* 401:    */    
/* 402:372 */    this.jdField_a_of_type_Fi.c();
/* 403:373 */    this.jdField_a_of_type_ES.c();
/* 404:    */    
/* 405:375 */    this.jdField_a_of_type_Ff = new ff(this.jdField_a_of_type_Ct);
/* 406:376 */    this.jdField_a_of_type_Ff.c();
/* 407:    */    
/* 409:379 */    this.jdField_a_of_type_Ii = new ii(this.jdField_a_of_type_Ct);
/* 410:    */    
/* 411:381 */    this.jdField_a_of_type_Ii.c();
/* 412:    */    
/* 413:383 */    this.jdField_a_of_type_Yu = new yu(this.jdField_a_of_type_Ct);
/* 414:384 */    this.jdField_a_of_type_Yu.a("use \"/pm playername msg\" for PMs, and \"/f msg\" for faction chat");
/* 415:    */    
/* 416:386 */    this.jdField_a_of_type_Yu.a(this.jdField_a_of_type_Ct.getChat());
/* 417:387 */    this.jdField_a_of_type_Yu.c();
/* 418:    */    
/* 420:390 */    this.jdField_a_of_type_YE = new yE(xe.a().a("shopinrange-gui-"), this.jdField_a_of_type_Ct);
/* 421:    */    
/* 422:392 */    this.jdField_a_of_type_YE.c();
/* 423:    */    
/* 426:396 */    this.jdField_a_of_type_Iy = new iy(this.jdField_a_of_type_Ct);
/* 427:397 */    this.jdField_a_of_type_Iy.h(10);
/* 428:    */    
/* 431:401 */    this.jdField_a_of_type_Fp = new fp(this.jdField_a_of_type_Ct);
/* 432:402 */    this.jdField_a_of_type_Fp.c();
/* 433:    */    
/* 436:406 */    Object localObject = new Font("Arial", 1, 26);
/* 437:407 */    (
/* 438:408 */      localObject = new UnicodeFont((Font)localObject)).getEffects().add(new OutlineEffect(4, Color.black));
/* 439:409 */    ((UnicodeFont)localObject).getEffects().add(new ColorEffect(Color.white));
/* 440:410 */    ((UnicodeFont)localObject).addAsciiGlyphs();
/* 441:    */    try {
/* 442:412 */      ((UnicodeFont)localObject).loadGlyphs();
/* 443:413 */    } catch (SlickException localSlickException) { 
/* 444:    */      
/* 445:415 */        localSlickException;
/* 446:    */    }
/* 447:    */    
/* 449:417 */    this.jdField_a_of_type_YP = new yP(500, 50, (UnicodeFont)localObject, this.jdField_a_of_type_Ct);
/* 450:418 */    this.jdField_a_of_type_YP.b(new ArrayList());
/* 451:419 */    this.jdField_a_of_type_YP.b().add("Detached Mouse From Game. Click to reattach");
/* 452:    */    
/* 453:421 */    e();
/* 454:    */  }
/* 455:    */  
/* 456:    */  public final void d() {
/* 457:425 */    this.jdField_a_of_type_Ii.e();
/* 458:    */  }
/* 459:    */  
/* 460:428 */  private boolean c() { return this.jdField_a_of_type_Ct.a().a().a().a().a().a().a().g(); }
/* 461:    */  
/* 474:    */  public final void a(Sendable paramSendable)
/* 475:    */  {
/* 476:444 */    if (this.d < 0.0F) {
/* 477:445 */      this.d = 0.0F;
/* 478:446 */      this.jdField_a_of_type_Float = 1.0F;
/* 479:447 */      if ((paramSendable != null) && ((paramSendable instanceof ld)) && (((ld)paramSendable instanceof ShieldContainerInterface)) && (((ShieldContainerInterface)paramSendable).getShieldManager().getShields() > 0.0D))
/* 480:    */      {
/* 483:451 */        this.jdField_a_of_type_YR.a().x = 0.0F;
/* 484:452 */        this.jdField_a_of_type_YR.a().y = 0.0F;
/* 485:453 */        this.jdField_a_of_type_YR.a().z = 1.0F;return;
/* 486:    */      }
/* 487:455 */      this.jdField_a_of_type_YR.a().x = 1.0F;
/* 488:456 */      this.jdField_a_of_type_YR.a().y = 0.0F;
/* 489:457 */      this.jdField_a_of_type_YR.a().z = 0.0F;
/* 490:    */    }
/* 491:    */  }
/* 492:    */  
/* 499:    */  public final void update(Observable paramObservable, Object paramObject)
/* 500:    */  {
/* 501:469 */    if ("ON_SWITCH".equals(paramObject)) {
/* 502:470 */      if ((paramObservable instanceof ak)) {
/* 503:471 */        a(8, (U)paramObservable);
/* 504:472 */        if (this.jdField_a_of_type_Yu != null) {
/* 505:473 */          this.jdField_a_of_type_Yu.a(((ak)paramObservable).c());
/* 506:    */        }
/* 507:    */      }
/* 508:476 */      if ((paramObservable instanceof an)) {
/* 509:477 */        a(2, (U)paramObservable);
/* 510:    */      }
/* 511:479 */      if ((paramObservable instanceof bl)) {
/* 512:480 */        a(64, (U)paramObservable);
/* 513:    */      }
/* 514:482 */      if ((paramObservable instanceof bz)) {
/* 515:483 */        a(4, (U)paramObservable);
/* 516:    */      }
/* 517:485 */      if ((paramObservable instanceof bk)) {
/* 518:486 */        a(16, (U)paramObservable);
/* 519:    */      }
/* 520:488 */      if ((paramObservable instanceof Y)) {
/* 521:489 */        a(32, (U)paramObservable);
/* 522:    */      }
/* 523:    */    }
/* 524:    */  }
/* 525:    */  
/* 528:    */  public final void a(xq paramxq)
/* 529:    */  {
/* 530:498 */    if (this.d >= 0.0F)
/* 531:    */    {
/* 532:500 */      this.d += this.jdField_a_of_type_Float * (paramxq.a() * 2.0F);
/* 533:501 */      if (this.d > this.jdField_b_of_type_Float) {
/* 534:502 */        this.jdField_a_of_type_Float = -1.0F;
/* 535:    */      }
/* 536:    */    } else {
/* 537:505 */      this.jdField_a_of_type_Float = 1.0F;
/* 538:    */    }
/* 539:507 */    this.jdField_a_of_type_Yu.a(paramxq);
/* 540:    */    
/* 541:509 */    if (!c()) {
/* 542:510 */      if (this.jdField_b_of_type_Fm != null) {
/* 543:511 */        this.jdField_b_of_type_Fm.e();
/* 544:    */      }
/* 545:513 */      if (this.jdField_a_of_type_Fm != null) {
/* 546:514 */        this.jdField_a_of_type_Fm.e();
/* 547:    */      }
/* 548:    */    }
/* 549:    */    
/* 550:518 */    if (this.jdField_b_of_type_Fm != null) {
/* 551:519 */      this.jdField_b_of_type_Fm.a(paramxq);
/* 552:    */    }
/* 553:521 */    if (this.jdField_a_of_type_Fm != null) {
/* 554:522 */      this.jdField_a_of_type_Fm.a(paramxq);
/* 555:    */    }
/* 556:    */    
/* 560:528 */    if ((!this.jdField_a_of_type_Ct.a().a().a().c()) && (Keyboard.isKeyDown(cv.Q.a()))) {
/* 561:529 */      this.jdField_a_of_type_Fi.a(paramxq);
/* 562:    */    }
/* 563:531 */    if ((!this.jdField_a_of_type_Ct.a().a().a().c()) && (Keyboard.isKeyDown(61))) {
/* 564:532 */      this.jdField_a_of_type_ES.a(paramxq);
/* 565:    */    }
/* 566:534 */    this.jdField_a_of_type_Fg.a(paramxq);
/* 567:    */    
/* 568:536 */    this.jdField_a_of_type_Ii.a(paramxq);
/* 569:    */    
/* 570:538 */    eV.a.a(paramxq);
/* 571:    */    int i;
/* 572:540 */    synchronized (this.jdField_a_of_type_JavaUtilLinkedList) {
/* 573:541 */      for (i = 0; i < this.jdField_a_of_type_JavaUtilLinkedList.size(); i++) {
/* 574:542 */        ((fl)this.jdField_a_of_type_JavaUtilLinkedList.get(i)).a(paramxq);
/* 575:543 */        if (!((fl)this.jdField_a_of_type_JavaUtilLinkedList.get(i)).a()) {
/* 576:544 */          this.jdField_a_of_type_JavaUtilLinkedList.remove(i);
/* 577:545 */          i--;
/* 578:    */        }
/* 579:    */        else {
/* 580:548 */          ((fl)this.jdField_a_of_type_JavaUtilLinkedList.get(i)).jdField_a_of_type_Float = i;
/* 581:    */        }
/* 582:    */      } }
/* 583:551 */    synchronized (this.jdField_b_of_type_JavaUtilLinkedList) {
/* 584:552 */      for (i = 0; i < this.jdField_b_of_type_JavaUtilLinkedList.size(); i++) {
/* 585:553 */        ((fq)this.jdField_b_of_type_JavaUtilLinkedList.get(i)).a(paramxq);
/* 586:554 */        if (!((fq)this.jdField_b_of_type_JavaUtilLinkedList.get(i)).a()) {
/* 587:555 */          this.jdField_b_of_type_JavaUtilLinkedList.remove(i);
/* 588:556 */          i--;
/* 589:    */        }
/* 590:    */        else {
/* 591:559 */          ((fq)this.jdField_b_of_type_JavaUtilLinkedList.get(i)).jdField_a_of_type_Float = i;
/* 592:    */        }
/* 593:    */      } }
/* 594:562 */    synchronized (this.jdField_c_of_type_JavaUtilLinkedList) {
/* 595:563 */      for (i = 0; i < this.jdField_c_of_type_JavaUtilLinkedList.size(); i++) {
/* 596:564 */        ((eQ)this.jdField_c_of_type_JavaUtilLinkedList.get(i)).a(paramxq);
/* 597:565 */        if (!((eQ)this.jdField_c_of_type_JavaUtilLinkedList.get(i)).a()) {
/* 598:566 */          this.jdField_c_of_type_JavaUtilLinkedList.remove(i);
/* 599:567 */          i--;
/* 600:    */        }
/* 601:    */        else {
/* 602:570 */          ((eQ)this.jdField_c_of_type_JavaUtilLinkedList.get(i)).jdField_a_of_type_Float = i;
/* 603:    */        }
/* 604:    */      }
/* 605:    */      return; } }
/* 606:    */  
/* 607:575 */  public final void a(ElementCollectionManager paramElementCollectionManager) { this.jdField_a_of_type_Fg.a(paramElementCollectionManager); }
/* 608:    */  
/* 611:    */  public final fg a()
/* 612:    */  {
/* 613:581 */    return this.jdField_a_of_type_Fg;
/* 614:    */  }
/* 615:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     fe
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */