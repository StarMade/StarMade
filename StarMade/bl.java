/*   1:    */import com.bulletphysics.linearmath.Transform;
/*   2:    */import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*   3:    */import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*   4:    */import java.io.IOException;
/*   5:    */import java.io.PrintStream;
/*   6:    */import java.util.ArrayList;
/*   7:    */import java.util.Iterator;
/*   8:    */import java.util.Set;
/*   9:    */import javax.vecmath.Vector3f;
/*  10:    */import org.lwjgl.input.Keyboard;
/*  11:    */import org.schema.common.FastMath;
/*  12:    */import org.schema.game.common.controller.SegmentController;
/*  13:    */import org.schema.game.common.controller.elements.ShipManagerContainer;
/*  14:    */import org.schema.game.common.data.element.ElementDocking;
/*  15:    */import org.schema.game.common.data.world.Segment;
/*  16:    */import org.schema.game.network.objects.NetworkPlayer;
/*  17:    */import org.schema.schine.graphicsengine.camera.Camera;
/*  18:    */import org.schema.schine.network.NetworkStateContainer;
/*  19:    */import org.schema.schine.network.objects.Sendable;
/*  20:    */import org.schema.schine.network.objects.remote.RemoteVector3i;
/*  21:    */
/*  43:    */public class bl
/*  44:    */  extends U
/*  45:    */{
/*  46:    */  private Camera jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera;
/*  47: 47 */  private float jdField_a_of_type_Float = 0.0F;
/*  48: 48 */  private float b = 0.0F;
/*  49: 49 */  private mF jdField_a_of_type_MF; private boolean d = false;
/*  50:    */  
/*  55: 55 */  private int jdField_a_of_type_Int = -1;
/*  56:    */  
/*  57:    */  private boolean e;
/*  58:    */  
/*  59:    */  private q jdField_a_of_type_Q;
/*  60:    */  
/*  61:    */  public bl(bj parambj)
/*  62:    */  {
/*  63: 63 */    super(parambj.a());new lA();new Vector3f();this.e = true;this.jdField_a_of_type_Q = new q();
/*  64:    */  }
/*  65:    */  
/*  92:    */  public static float a()
/*  93:    */  {
/*  94: 94 */    return 5.0F;
/*  95:    */  }
/*  96:    */  
/* 102:    */  private le a()
/* 103:    */  {
/* 104:104 */    return a().a().a.a.a.a().a();
/* 105:    */  }
/* 106:    */  
/* 107:107 */  public final q a(q paramq) { return a().a().a.a.a.a().a().a(paramq); }
/* 108:    */  
/* 122:    */  public final kd a()
/* 123:    */  {
/* 124:124 */    return a().a();
/* 125:    */  }
/* 126:    */  
/* 130:    */  public final mF a()
/* 131:    */  {
/* 132:132 */    return this.jdField_a_of_type_MF;
/* 133:    */  }
/* 134:    */  
/* 137:    */  public final float b()
/* 138:    */  {
/* 139:139 */    return this.jdField_a_of_type_Float;
/* 140:    */  }
/* 141:    */  
/* 144:    */  public void handleKeyEvent()
/* 145:    */  {
/* 146:146 */    if (Keyboard.getEventKeyState()) {
/* 147:147 */      if ((Keyboard.getEventKey() >= 2) && (Keyboard.getEventKey() <= 11) && (!Keyboard.isKeyDown(29))) {
/* 148:148 */        int j = Keyboard.getEventKey() - 2;bl localbl = this;a().a().d(j);localbl.e = true;
/* 149:    */      }
/* 150:    */      Object localObject2;
/* 151:    */      Object localObject3;
/* 152:    */      le localle;
/* 153:    */      Object localObject1;
/* 154:154 */      if ((Keyboard.isKeyDown(29)) && 
/* 155:155 */        (!a().a().getDockingController().a().isEmpty())) {
/* 156:    */        int i;
/* 157:157 */        if ((i = Keyboard.getEventKey() - 2) < a().a().getDockingController().a().size()) {
/* 158:158 */          localObject2 = a().a().getDockingController().a().iterator();
/* 159:159 */          localObject3 = null;
/* 160:160 */          for (int k = 0; k <= i; k++) {
/* 161:161 */            localObject3 = (ElementDocking)((Iterator)localObject2).next();
/* 162:    */          }
/* 163:    */          
/* 164:164 */          (localle = a()).a().a();
/* 165:165 */          localObject1 = a().a(new q());
/* 166:166 */          System.err.println("EXIT SHIP FROM EXTRYPOINT " + localObject1);
/* 167:    */          
/* 173:173 */          a().a().a.a.a
/* 174:    */          
/* 175:175 */            .a().a(((ElementDocking)localObject3).from);
/* 176:176 */          a().a().a((cw)localle.a().a(), (cw)a().a().a(), localle.a(new q()), a().a(new q()), true);
/* 177:    */          
/* 187:187 */          this.jdField_a_of_type_Int = -1;
/* 188:    */        }
/* 189:    */      }
/* 190:    */      
/* 192:192 */      if (Keyboard.getEventKey() == cv.C.a())
/* 193:    */      {
/* 194:194 */        if (a().a().getDockingController().a() != null)
/* 195:    */        {
/* 199:199 */          localObject1 = null;localObject2 = a().a().a.a.a;
/* 200:    */          
/* 202:    */          try
/* 203:    */          {
/* 204:204 */            (localle = a()).a().a();
/* 205:205 */            localObject1 = a().a(new q());
/* 206:206 */            System.err.println("EXIT SHIP FROM EXTRYPOINT " + localObject1);
/* 207:207 */            localObject3 = a().a().getDockingController().a().to.a().a().getSegmentBuffer().a(kd.jdField_a_of_type_Q, false);
/* 208:208 */            ((ar)localObject2).a().a((le)localObject3);
/* 209:    */            
/* 210:210 */            a().a().a((cw)localle.a().a(), (cw)a().a().a(), localle.a(new q()), a().a(new q()), true);
/* 211:    */            
/* 219:219 */            this.jdField_a_of_type_Int = -1;
/* 220:220 */          } catch (IOException localIOException) { 
/* 221:    */            
/* 224:224 */              localIOException;
/* 225:    */          } catch (InterruptedException localInterruptedException) {
/* 226:222 */            
/* 227:    */            
/* 228:224 */              localInterruptedException;
/* 229:    */          }
/* 230:    */        }
/* 231:    */      }
/* 232:    */      
/* 235:229 */      if ((!(localObject1 = a().a().a().getCockpits()).isEmpty()) && (a().a() == 1))
/* 236:    */      {
/* 240:234 */        if (Keyboard.getEventKey() == cv.E.a()) {
/* 241:235 */          if (this.jdField_a_of_type_Int == 0) {
/* 242:236 */            this.jdField_a_of_type_Int = -1;
/* 243:    */            
/* 245:239 */            (
/* 246:240 */              localObject3 = a().a().a.a.a.a().a().a(new q())).c(kd.jdField_a_of_type_Q);
/* 247:241 */            ((dy)this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera.a()).a().b((q)localObject3);
/* 248:242 */            return; }
/* 249:243 */          if (this.jdField_a_of_type_Int < 0) {
/* 250:244 */            this.jdField_a_of_type_Int = (((ArrayList)localObject1).size() - 1);
/* 251:    */          } else {
/* 252:246 */            this.jdField_a_of_type_Int = FastMath.b(this.jdField_a_of_type_Int - 1, ((ArrayList)localObject1).size());
/* 253:    */          }
/* 254:    */          
/* 256:250 */          (localObject2 = new q((q)((ArrayList)localObject1).get(this.jdField_a_of_type_Int))).c(kd.jdField_a_of_type_Q);
/* 257:251 */          ((dy)this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera.a()).a().b((q)localObject2);
/* 258:252 */          return;
/* 259:    */        }
/* 260:    */        
/* 261:255 */        if (Keyboard.getEventKey() == cv.D.a()) {
/* 262:256 */          if (this.jdField_a_of_type_Int + 1 >= ((ArrayList)localObject1).size()) {
/* 263:257 */            this.jdField_a_of_type_Int = -1;
/* 264:    */            
/* 266:260 */            (
/* 267:261 */              localObject3 = a().a().a.a.a.a().a().a(new q())).c(kd.jdField_a_of_type_Q);
/* 268:262 */            ((dy)this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera.a()).a().b((q)localObject3);
/* 269:263 */            return; }
/* 270:264 */          if (this.jdField_a_of_type_Int < 0) {
/* 271:265 */            this.jdField_a_of_type_Int = 0;
/* 272:    */          } else {
/* 273:267 */            this.jdField_a_of_type_Int = FastMath.b(this.jdField_a_of_type_Int + 1, ((ArrayList)localObject1).size());
/* 274:    */          }
/* 275:    */          
/* 276:270 */          (localObject2 = new q((q)((ArrayList)localObject1).get(this.jdField_a_of_type_Int))).c(kd.jdField_a_of_type_Q);
/* 277:271 */          ((dy)this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera.a()).a().b((q)localObject2);
/* 278:    */        }
/* 279:    */      }
/* 280:    */    }
/* 281:    */  }
/* 282:    */  
/* 292:    */  public final void a(xp paramxp)
/* 293:    */  {
/* 294:288 */    if ((xu.X.a().equals("SLOTS")) && (!Keyboard.isKeyDown(42)))
/* 295:    */    {
/* 296:290 */      paramxp = FastMath.b(a().a().d() + (paramxp.f < 0 ? -1 : paramxp.f > 0 ? 1 : 0), 10);
/* 297:    */      
/* 299:293 */      a().a().d(paramxp);
/* 300:    */    }
/* 301:    */  }
/* 302:    */  
/* 305:    */  public final boolean a()
/* 306:    */  {
/* 307:301 */    return this.d;
/* 308:    */  }
/* 309:    */  
/* 321:    */  public final void b(boolean paramBoolean)
/* 322:    */  {
/* 323:317 */    Object localObject = a().a().a.a.a.a();
/* 324:    */    
/* 325:319 */    if (a().a() != null) {
/* 326:320 */      a().a().a(null);
/* 327:    */    }
/* 328:322 */    if (paramBoolean)
/* 329:    */    {
/* 334:328 */      this.e = true;
/* 335:329 */      if ((this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera == null) || (((xb)this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera.a()).a() != a().a()))
/* 336:    */      {
/* 337:331 */        if ((!f) && (a().a() == null)) { throw new AssertionError("SHIP NOT FOUND ");
/* 338:    */        }
/* 339:333 */        this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera = new dt(((bi)localObject).a(), xe.a(), a());
/* 340:334 */        this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera.a(0.0F);
/* 341:335 */        if (!a(new q()).equals(kd.jdField_a_of_type_Q)) {
/* 342:336 */          ((dt)this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera).b = true;
/* 343:    */        } else {
/* 344:338 */          ((dt)this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera).b = false;
/* 345:    */        }
/* 346:    */        
/* 347:    */      }
/* 348:342 */      else if (this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera != null) {
/* 349:343 */        ((dt)this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera).a(xe.a());
/* 350:    */      }
/* 351:345 */      if ((this.jdField_a_of_type_Int >= 0) && (this.jdField_a_of_type_Int < a().a().a().getCockpits().size()))
/* 352:    */      {
/* 353:347 */        (localObject = new q((q)a().a().a().getCockpits().get(this.jdField_a_of_type_Int))).c(kd.jdField_a_of_type_Q);
/* 354:348 */        ((dy)this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera.a()).a().b((q)localObject);
/* 355:    */      } else {
/* 356:350 */        this.jdField_a_of_type_Int = -1;
/* 357:351 */        (
/* 358:352 */          localObject = ((bi)localObject).a().a(new q())).c(kd.jdField_a_of_type_Q);
/* 359:353 */        ((dy)this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera.a()).a().b((q)localObject);
/* 360:    */      }
/* 361:    */      
/* 363:357 */      a().a().c("BuildMode");
/* 364:358 */      a().a().a("FlightMode", "Flight Mode", "(press " + cv.r.b() + " to switch to BUILD MODE; press " + cv.v.b() + " to exit structure)");
/* 365:359 */      xe.a(this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera);
/* 366:    */    } else {
/* 367:361 */      this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera = null;
/* 368:    */    }
/* 369:    */    
/* 370:364 */    super.b(paramBoolean);
/* 371:    */  }
/* 372:    */  
/* 373:    */  private mF b() {
/* 374:368 */    if ((a().a() == null) || (a().a().a() == null)) {
/* 375:369 */      return null;
/* 376:    */    }
/* 377:    */    
/* 380:374 */    zG localzG = a().a().a();
/* 381:    */    
/* 382:376 */    Vector3f localVector3f1 = new Vector3f();
/* 383:377 */    float f1 = 0.0F;
/* 384:378 */    float f2 = -1.0F;
/* 385:379 */    Object localObject2 = null;
/* 386:380 */    Vector3f localVector3f2 = localzG.a(new Vector3f());
/* 387:381 */    Iterator localIterator; synchronized (a().getLocalAndRemoteObjectContainer().getLocalObjects()) {
/* 388:382 */      for (localIterator = a().getLocalAndRemoteObjectContainer().getLocalObjects().values().iterator(); localIterator.hasNext();) { Object localObject3;
/* 389:383 */        if (((localObject3 = (Sendable)localIterator.next()) instanceof mF))
/* 390:    */        {
/* 391:385 */          if ((localObject3 = (mF)localObject3) != a().a()) {
/* 392:386 */            localVector3f1.set(((mF)localObject3).getWorldTransform().origin);
/* 393:    */            
/* 396:390 */            if (a().a() != null) {
/* 397:391 */              localVector3f1.sub(a().a().getWorldTransform().origin);
/* 398:    */            } else {
/* 399:393 */              localVector3f1.sub(xe.a().a());
/* 400:    */            }
/* 401:395 */            Vector3f localVector3f3 = localzG.a(((mF)localObject3).getWorldTransformClient().origin, new Vector3f(), true);
/* 402:    */            
/* 403:397 */            Vector3f localVector3f4 = new Vector3f();
/* 404:398 */            Vector3f localVector3f5 = new Vector3f();
/* 405:399 */            localVector3f4.sub(localVector3f3, localVector3f2);
/* 406:400 */            localVector3f5.sub(a().a().getWorldTransform().origin, ((mF)localObject3).getWorldTransformClient().origin);
/* 407:    */            
/* 409:403 */            if ((localVector3f4.length() < 90.0F) && (
/* 410:404 */              (localObject2 == null) || ((localVector3f4.length() < f1) && (localVector3f5.length() < f2)))) {
/* 411:405 */              localObject2 = localObject3;
/* 412:406 */              f1 = localVector3f4.length();
/* 413:407 */              f2 = localVector3f5.length();
/* 414:    */            }
/* 415:    */          }
/* 416:    */        }
/* 417:    */      }
/* 418:    */    }
/* 419:413 */    return localObject2;
/* 420:    */  }
/* 421:    */  
/* 429:    */  public final void a(xq paramxq)
/* 430:    */  {
/* 431:425 */    super.a(paramxq);
/* 432:    */    
/* 433:427 */    Object localObject1 = this; if (this.e) try { Object localObject2; if ((localObject2 = ((bl)localObject1).a().a().a(((bl)localObject1).a().a())) != null) { if ((localObject2 = ((cz)localObject2).a(((bl)localObject1).a().a().d())) != null) { if ((localObject2 = ((bl)localObject1).a().a().getSegmentBuffer().a((q)localObject2, true)) != null) ((bl)localObject1).d = (((le)localObject2).a() == 54); } else ((bl)localObject1).d = false; ((bl)localObject1).e = false; } } catch (Exception localException) {}
/* 434:428 */    if ((cv.V.a()) && 
/* 435:429 */      ((xe.a() instanceof dt))) {
/* 436:430 */      ((dt)xe.a()).b();
/* 437:    */    }
/* 438:    */    
/* 441:435 */    wV.a = true;
/* 442:    */    
/* 443:437 */    if (this.jdField_a_of_type_Int >= 0) {
/* 444:438 */      this.jdField_a_of_type_Q.b(((dy)this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera.a()).a());
/* 445:439 */      this.jdField_a_of_type_Q.a(kd.jdField_a_of_type_Q);
/* 446:440 */      if (!a().a().a().getCockpits().contains(this.jdField_a_of_type_Q)) {
/* 447:441 */        this.jdField_a_of_type_Int = -1;
/* 448:    */        
/* 450:444 */        (
/* 451:445 */          localObject1 = a().a().a.a.a.a().a().a(new q())).c(kd.jdField_a_of_type_Q);
/* 452:446 */        ((dy)this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera.a()).a().b((q)localObject1);
/* 453:    */      }
/* 454:448 */      if (this.jdField_a_of_type_Int >= 0) {
/* 455:449 */        if (!a().a().a().cockpit.getVector().equals(this.jdField_a_of_type_Q)) {
/* 456:450 */          a().a().a().cockpit.forceClientUpdates();
/* 457:451 */          a().a().a().cockpit.set(this.jdField_a_of_type_Q);
/* 458:    */        }
/* 459:    */      }
/* 460:454 */      else if (!a().a().a().cockpit.getVector().equals(kd.jdField_a_of_type_Q)) {
/* 461:455 */        a().a().a().cockpit.forceClientUpdates();
/* 462:456 */        a().a().a().cockpit.set(kd.jdField_a_of_type_Q);
/* 463:    */      }
/* 464:    */    }
/* 465:    */    
/* 467:461 */    if (this.d)
/* 468:    */    {
/* 469:463 */      if (a().a().a() != null) {
/* 470:464 */        this.b += paramxq.a();
/* 471:    */      }
/* 472:466 */      if ((this.b > 0.0F) && (this.b < 3.0F))
/* 473:    */      {
/* 477:471 */        if (b() == a().a().a())
/* 478:    */        {
/* 479:473 */          this.b = 0.0F;
/* 480:    */        }
/* 481:    */        
/* 483:    */      }
/* 484:    */      else
/* 485:    */      {
/* 487:481 */        this.b = 0.0F;
/* 488:    */        
/* 489:483 */        localObject1 = b();
/* 490:    */        
/* 491:485 */        if (this.jdField_a_of_type_MF != localObject1) {
/* 492:486 */          this.jdField_a_of_type_MF = ((mF)localObject1);
/* 493:487 */          this.jdField_a_of_type_Float = 0.0F;
/* 494:488 */          a().a().a(null);return;
/* 495:    */        }
/* 496:490 */        if (this.jdField_a_of_type_MF != null) {
/* 497:491 */          this.jdField_a_of_type_Float += paramxq.a();
/* 498:492 */          if (this.jdField_a_of_type_Float > 5.0F)
/* 499:    */          {
/* 500:494 */            a().a().a(this.jdField_a_of_type_MF);
/* 501:    */          }
/* 502:    */        }
/* 503:    */      }
/* 504:    */    }
/* 505:    */    else
/* 506:    */    {
/* 507:501 */      a().a().a(null);
/* 508:502 */      this.jdField_a_of_type_Float = 0.0F;
/* 509:503 */      this.b = 0.0F;
/* 510:    */    }
/* 511:    */  }
/* 512:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     bl
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */