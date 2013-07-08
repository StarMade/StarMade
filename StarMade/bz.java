/*   1:    */import java.util.ArrayList;
/*   2:    */import java.util.List;
/*   3:    */import org.schema.game.common.data.element.ElementInformation;
/*   4:    */import org.schema.game.common.data.element.ElementKeyMap;
/*   5:    */import org.schema.game.server.data.blueprintnw.BlueprintEntry;
/*   6:    */
/*  32:    */public final class bz
/*  33:    */  extends U
/*  34:    */  implements ys
/*  35:    */{
/*  36:    */  public short a;
/*  37:    */  public yD a;
/*  38:    */  private long jdField_b_of_type_Long;
/*  39:    */  private yD jdField_b_of_type_YD;
/*  40:    */  
/*  41:    */  public bz(ct paramct)
/*  42:    */  {
/*  43: 43 */    super(paramct);this.jdField_a_of_type_Short = -1;
/*  44:    */  }
/*  45:    */  
/*  47:    */  public final void a(yz paramyz, xp arg2)
/*  48:    */  {
/*  49: 49 */    String str = null; if ((???.jdField_a_of_type_Boolean) && (???.jdField_a_of_type_Int == 0))
/*  50:    */    {
/*  51: 51 */      if ((paramyz instanceof yD))
/*  52:    */      {
/*  53: 53 */        ??? = (yD)paramyz;
/*  54: 54 */        if (!"CATEGORY".equals(???.a().b()))
/*  55:    */        {
/*  57: 57 */          if (this.jdField_b_of_type_YD != null) {
/*  58: 58 */            this.jdField_b_of_type_YD.a().e();
/*  59: 59 */            this.jdField_b_of_type_YD.a(false);
/*  60:    */          }
/*  61: 61 */          ???.a(true);
/*  62: 62 */          this.jdField_a_of_type_Short = ((Short)???.a().b()).shortValue();
/*  63: 63 */          this.jdField_b_of_type_YD = ???;
/*  64:    */        }
/*  65:    */      }
/*  66:    */      
/*  67: 67 */      if ("upload".equals(paramyz.b()))
/*  68:    */      {
/*  69: 69 */        if (System.currentTimeMillis() - this.jdField_b_of_type_Long < 5000L) {
/*  70: 70 */          a().a().b("Cannot upload now!\nplease wait " + (System.currentTimeMillis() - this.jdField_b_of_type_Long) / 1000L + " seconds");
/*  71: 71 */          return;
/*  72:    */        }
/*  73: 73 */        this.jdField_b_of_type_Long = System.currentTimeMillis();
/*  74:    */        
/*  75: 75 */        ??? = tH.a.a();
/*  76:    */        
/*  77: 77 */        str = "Please enter in a name for your blue print!\n\nAvailable:\n" + ???;
/*  78: 78 */        (
/*  79:    */        
/* 124:124 */          ??? = new bA(this, a(), "BluePrint", str, ???.isEmpty() ? "" : ((BlueprintEntry)???.get(0)).toString())).a(new bB());
/* 125:    */        
/* 135:135 */        synchronized (a().b()) {
/* 136:136 */          a().b().add(???);
/* 137:137 */          e(true);
/* 138:    */        }
/* 139:    */      }
/* 140:140 */      if ("save_local".equals(paramyz.b()))
/* 141:    */      {
/* 142:142 */        if (System.currentTimeMillis() - this.jdField_b_of_type_Long < 5000L) {
/* 143:143 */          a().a().b("Cannot save now!\nplease wait " + (System.currentTimeMillis() - this.jdField_b_of_type_Long) / 1000L + " seconds");
/* 144:144 */          return;
/* 145:    */        }
/* 146:146 */        this.jdField_b_of_type_Long = System.currentTimeMillis();
/* 147:    */        
/* 148:148 */        ??? = "Please enter in a name for your blue print!";
/* 149:149 */        (
/* 150:    */        
/* 196:196 */          ??? = new bC(this, a(), "BluePrint", ???, "BLUEPRINT_" + System.currentTimeMillis())).a(new bD());
/* 197:    */        
/* 207:207 */        synchronized (a().b()) {
/* 208:208 */          a().b().add(???);
/* 209:209 */          e(true);
/* 210:    */        }
/* 211:    */      }
/* 212:    */      
/* 213:213 */      if ("save_catalog".equals(paramyz.b()))
/* 214:    */      {
/* 215:215 */        if (System.currentTimeMillis() - this.jdField_b_of_type_Long < 5000L) {
/* 216:216 */          a().a().b("Cannot save now!\nplease wait " + (System.currentTimeMillis() - this.jdField_b_of_type_Long) / 1000L + " seconds");
/* 217:217 */          return;
/* 218:    */        }
/* 219:219 */        this.jdField_b_of_type_Long = System.currentTimeMillis();
/* 220:    */        
/* 221:221 */        ??? = "Please enter in a name for your blue print!";
/* 222:222 */        (
/* 223:    */        
/* 267:267 */          ??? = new bE(this, a(), "BluePrint", ???, "BLUEPRINT_" + System.currentTimeMillis())).a(new bF());
/* 268:    */        
/* 278:278 */        synchronized (a().b()) {
/* 279:279 */          a().b().add(???);
/* 280:280 */          e(true);
/* 281:    */        }
/* 282:    */      }
/* 283:283 */      if (("buy_catalog".equals(paramyz.b())) && (a() != null))
/* 284:    */      {
/* 285:285 */        if (System.currentTimeMillis() - this.jdField_b_of_type_Long < 5000L) {
/* 286:286 */          a().a().b("Cannot buy now!\nplease wait " + (System.currentTimeMillis() - this.jdField_b_of_type_Long) / 1000L + " seconds");
/* 287:287 */          return;
/* 288:    */        }
/* 289:289 */        this.jdField_b_of_type_Long = System.currentTimeMillis();
/* 290:    */        
/* 291:291 */        ??? = "Please type in a name for your new Ship!";
/* 292:292 */        (
/* 293:    */        
/* 337:337 */          ??? = new bG(this, a(), "New Ship", ???, a().a + "_" + System.currentTimeMillis())).a(new bH());
/* 338:    */        
/* 348:348 */        synchronized (a().b()) {
/* 349:349 */          a().b().add(???);
/* 350:350 */          e(true);
/* 351:    */        }
/* 352:    */      }
/* 353:353 */      if (((paramyz instanceof yE)) && 
/* 354:354 */        (this.jdField_a_of_type_Short >= 0)) { short s;
/* 355:355 */        if ("buy".equals(paramyz.b())) {
/* 356:356 */          s = this.jdField_a_of_type_Short;??? = this; kf localkf2; ???.a().a().b("ERROR: no shop available");int k = localkf2.a().a(s);int i = localkf2.a().a(k);???.a().a().b("ERROR: shop out of item");???.a().a().b("ERROR: not enough credits"); if ((???.a().a().b() < localkf2.a(ElementKeyMap.getInfo(s), 1) ? 0 : (k < 0) || (i <= 0) ? 0 : (localkf2 = a().a()) == null ? 0 : 1) != 0) {
/* 357:357 */            xe.b("0022_action - purchase with credits");
/* 358:358 */            a().a().a().a(this.jdField_a_of_type_Short, 1);
/* 359:    */          }
/* 360:    */        }
/* 361:361 */        if ("sell".equals(paramyz.b()))
/* 362:    */        {
/* 363:363 */          s = this.jdField_a_of_type_Short;??? = this; if (a().a().getInventory(null).a(s) >= 0) { kf localkf1; if ((localkf1 = ???.a().a()) != null) { int j; if ((j = localkf1.a().a(s)) >= 0) localkf1.a(); ???.a().a().b("ERROR: shop has reached the max\nstock for the item\n" + ElementKeyMap.getInfo(s).getName()); } else { ???.a().a().b("ERROR: no shop in distance"); } } else { ???.a().a().b("ERROR: you don't own that item\n" + ElementKeyMap.getInfo(s).getName()); } if ((localkf1.a().a(j) < mn.e() ? 1 : 0) != 0) {
/* 364:364 */            xe.b("0022_action - receive credits");
/* 365:365 */            a().a().a().b(this.jdField_a_of_type_Short, 1);
/* 366:    */          }
/* 367:    */        }
/* 368:    */        
/* 371:371 */        if ("sell_more".equals(paramyz.b()))
/* 372:    */        {
/* 373:373 */          if ((??? = a().a()) != null) {
/* 374:374 */            a(this.jdField_a_of_type_Short, 1, ???);
/* 375:    */          } else {
/* 376:376 */            a().a().b("ERROR: shop no more in distance");
/* 377:    */          }
/* 378:    */        }
/* 379:379 */        if ("buy_more".equals(paramyz.b()))
/* 380:    */        {
/* 382:382 */          if ((??? = a().a()) != null) {
/* 383:383 */            synchronized (a().b()) {
/* 384:384 */              a().b().add(new bo(a(), this.jdField_a_of_type_Short, ???));
/* 385:385 */              e(true);
/* 386:386 */              return;
/* 387:    */            }
/* 388:    */          }
/* 389:    */        }
/* 390:    */      }
/* 391:    */    }
/* 392:    */  }
/* 393:    */  
/* 452:    */  public final lL a()
/* 453:    */  {
/* 454:454 */    if (this.jdField_a_of_type_YD != null) {
/* 455:455 */      return (lL)this.jdField_a_of_type_YD.b();
/* 456:    */    }
/* 457:457 */    return null;
/* 458:    */  }
/* 459:    */  
/* 465:    */  public final void handleKeyEvent() {}
/* 466:    */  
/* 471:    */  public final void a(xp paramxp)
/* 472:    */  {
/* 473:473 */    super.a(paramxp);
/* 474:    */  }
/* 475:    */  
/* 477:    */  public final boolean a()
/* 478:    */  {
/* 479:479 */    return !a().b().isEmpty();
/* 480:    */  }
/* 481:    */  
/* 485:    */  public final void b(boolean paramBoolean)
/* 486:    */  {
/* 487:487 */    wV.jdField_a_of_type_Boolean = !paramBoolean;
/* 488:488 */    if (paramBoolean) {
/* 489:489 */      xe.b("0022_menu_ui - swoosh scroll large");
/* 490:    */    } else {
/* 491:491 */      xe.b("0022_menu_ui - swoosh scroll small");
/* 492:    */    }
/* 493:493 */    a().a().a.a.jdField_a_of_type_Ar.a().a().jdField_a_of_type_Bl.e(paramBoolean);
/* 494:    */    
/* 496:496 */    a().a().a.a.jdField_a_of_type_Ar.a().a().jdField_a_of_type_Au.e(paramBoolean);
/* 497:    */    
/* 502:502 */    super.b(paramBoolean);
/* 503:    */  }
/* 504:    */  
/* 505:505 */  public final void a(short paramShort, int paramInt, kf paramkf) { synchronized (a().b()) {
/* 506:506 */      a().b().add(new bw(a(), paramShort, paramInt, paramkf));
/* 507:507 */      e(true);
/* 508:508 */      return;
/* 509:    */    }
/* 510:    */  }
/* 511:    */  
/* 526:    */  public final void a(xq paramxq)
/* 527:    */  {
/* 528:528 */    wV.jdField_a_of_type_Boolean = false;
/* 529:529 */    if (!a().d()) {
/* 530:530 */      a().a().b("no shop in range");
/* 531:531 */      d(false);
/* 532:532 */      a().a().a.a.jdField_a_of_type_An.d(true);
/* 533:    */    }
/* 534:    */    
/* 535:535 */    a().a().a.a.jdField_a_of_type_Ar.e(true);
/* 536:    */  }
/* 537:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     bz
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */