/*   1:    */import java.io.PrintStream;
/*   2:    */import java.util.HashSet;
/*   3:    */import java.util.Iterator;
/*   4:    */import java.util.Map;
/*   5:    */import java.util.Set;
/*   6:    */import org.lwjgl.opengl.GL20;
/*   7:    */import org.schema.schine.graphicsengine.core.GlUtil;
/*   8:    */import org.schema.schine.graphicsengine.core.ResourceException;
/*   9:    */
/*  20:    */public final class zk
/*  21:    */{
/*  22:    */  public static int a;
/*  23:    */  private static Set a;
/*  24:    */  private static zj D;
/*  25:    */  private static zj E;
/*  26:    */  private static zj F;
/*  27:    */  public static zj a;
/*  28:    */  public static zj b;
/*  29:    */  public static zj c;
/*  30:    */  public static zj d;
/*  31:    */  public static zj e;
/*  32:    */  public static zj f;
/*  33:    */  public static zj g;
/*  34:    */  public static zj h;
/*  35:    */  public static zj i;
/*  36:    */  public static zj j;
/*  37:    */  public static zj k;
/*  38:    */  private static zj G;
/*  39:    */  private static zj H;
/*  40:    */  private static zj I;
/*  41:    */  public static zj l;
/*  42:    */  public static zj m;
/*  43:    */  private static zj J;
/*  44:    */  public static zj n;
/*  45:    */  public static zj o;
/*  46:    */  public static zj p;
/*  47:    */  public static zj q;
/*  48:    */  public static zj r;
/*  49:    */  public static zj s;
/*  50:    */  public static zj t;
/*  51:    */  public static zj u;
/*  52:    */  public static zj v;
/*  53:    */  public static zj w;
/*  54:    */  public static zj x;
/*  55:    */  public static zj y;
/*  56:    */  public static zj z;
/*  57:    */  public static zj A;
/*  58:    */  public static zj B;
/*  59:    */  public static zj C;
/*  60:    */  
/*  61:    */  static
/*  62:    */  {
/*  63: 63 */    jdField_a_of_type_Int = xu.al.b() ? 2 : 3;
/*  64:    */  }
/*  65:    */  
/* 159:    */  public static void a()
/* 160:    */  {
/* 161:161 */    for (Iterator localIterator = jdField_a_of_type_JavaUtilSet.iterator(); localIterator.hasNext();) { zj localzj;
/* 162:162 */      if ((localzj = (zj)localIterator.next()) != null) {
/* 163:163 */        try { GL20.glDeleteProgram(localzj.jdField_a_of_type_Int); } catch (Exception localException) { localException;
/* 164:    */        }
/* 165:    */      }
/* 166:    */    }
/* 167:    */  }
/* 168:    */  
/* 173:    */  public static void b()
/* 174:    */  {
/* 175:    */    try
/* 176:    */    {
/* 177:177 */      jdField_a_of_type_JavaUtilSet = new HashSet();
/* 178:    */      
/* 179:179 */      if (xu.r.a().equals("normal")) {
/* 180:180 */        B = new zj("data//shader/atmosphere/SkyFromSpace.vert", "data//shader/atmosphere/SkyFromSpace.frag");
/* 181:    */        
/* 184:184 */        A = new zj("data//shader/atmosphere/SkyFromAtmosphere.vert", "data//shader/atmosphere/SkyFromAtmosphere.frag");
/* 187:    */      }
/* 188:188 */      else if (xu.r.a().equals("fallback")) {
/* 189:189 */        System.err.println("LOADING FALLBACK ATHMOSPHERE SHADER");
/* 190:190 */        B = new zj("data//shader/atmosphere/SkyFromSpace-le.vert", "data//shader/atmosphere/SkyFromSpace.frag");
/* 191:    */        
/* 194:194 */        A = new zj("data//shader/atmosphere/SkyFromAtmosphere-le.vert", "data//shader/atmosphere/SkyFromAtmosphere.frag");
/* 195:    */      }
/* 196:    */      
/* 200:200 */      z = new zj("data//shader/perpixel/perpixel.vert.glsl", "data//shader/perpixel/perpixel.frag.glsl");
/* 201:    */      
/* 205:205 */      l = new zj("data//shader/blackhole/blackhole.vert.glsl", "data//shader/blackhole/blackhole.frag.glsl");
/* 206:    */      
/* 210:210 */      u = new zj("data//shader/cube/selectionSingle.vert.glsl", "data//shader/cube/selectionSingle.frag.glsl");
/* 211:    */      
/* 214:214 */      e = new zj("data//shader/plasma/plasma.vert.glsl", "data//shader/plasma/plasma.frag.glsl");
/* 215:    */      
/* 218:218 */      x = new zj("data//shader/bloom/godrays.vert.glsl", "data//shader/bloom/godrays.frag.glsl");
/* 219:    */      
/* 226:226 */      C = new zj("data//shader/tubes/tube.vert.glsl", "data//shader/tubes/tube.frag.glsl");
/* 227:    */      
/* 230:230 */      w = new zj("data//shader/simplebeam/simplebeam.vert.glsl", "data//shader/simplebeam/simplebeam.frag.glsl");
/* 231:    */      
/* 234:234 */      if (xu.s.b()) {
/* 235:235 */        t = new zj("data//shader/shieldhit/shieldhit.vert.glsl", "data//shader/shieldhit/shieldhit.frag.glsl");
/* 236:    */      }
/* 237:    */      
/* 240:240 */      D = new zj("data//shader/smoke.vert", "data//shader/smoke.frag");
/* 241:    */      
/* 263:263 */      i = new zj("data//shader/bump.vert", "data//shader/bump.frag");
/* 264:    */      
/* 266:266 */      E = new zj("data//shader/volumesmoke.vsh", "data//shader/volumesmoke.fsh");
/* 267:    */      
/* 269:269 */      F = new zj("data//shader/projectiles/standard/projectile.vsh", "data//shader/projectiles/standard/projectile.fsh");
/* 270:    */      
/* 272:272 */      y = new zj("data//shader/projectiles/trail/projectileTrail.vsh", "data//shader/projectiles/trail/projectileTrail.fsh");
/* 273:    */      
/* 275:275 */      jdField_a_of_type_Zj = new zj("data//shader/projectiles/standard/projectileQuad.vsh", "data//shader/projectiles/standard/projectileQuad.fsh");
/* 276:    */      
/* 278:278 */      b = new zj("data//shader/projectiles/beam/projectileQuad.vsh", "data//shader/projectiles/beam/projectileQuad.fsh");
/* 279:    */      
/* 281:281 */      g = new zj("data//shader/explosion/explosion.vsh", "data//shader/explosion/explosion.fsh");
/* 282:    */      
/* 284:284 */      h = new zj("data//shader/spacedustExt/spacedust.vsh", "data//shader/spacedustExt/spacedust.fsh");
/* 285:    */      
/* 287:287 */      j = new zj("data//shader/planet.vert.glsl", "data//shader/planet.frag.glsl");
/* 288:    */      
/* 290:290 */      k = new zj("data//shader/atmosphere.vert.glsl", "data//shader/atmosphere.frag.glsl");
/* 291:    */      
/* 293:293 */      G = new zj("data//shader/bloom.vert.glsl", "data//shader/bloom2D.frag.glsl");
/* 294:    */      
/* 296:296 */      H = new zj("data//shader/sun.vert.glsl", "data//shader/sun.frag.glsl");
/* 297:    */      
/* 302:302 */      (zk.I = new zj("data//shader/silhouette.vert.glsl", "data//shader/silhouette.frag.glsl")).jdField_a_of_type_Zr = new zs();
/* 303:    */      
/* 304:304 */      q = new zj("data//shader/silhouette.vert.glsl", "data//shader/silhouette2DAlpha.frag.glsl");
/* 305:    */      
/* 314:314 */      s = new zj("data//shader/thruster/thruster.vert.glsl", "data//shader/thruster/thruster.frag.glsl");
/* 315:    */      
/* 369:369 */      c = new zj("data//shader/starsExt/stars.vsh", "data//shader/starsExt/stars.fsh");
/* 370:    */      
/* 373:373 */      d = new zj("data//shader/starsExt/starsflare.vsh", "data//shader/starsExt/starsflare.fsh");
/* 374:    */      
/* 378:378 */      if (jdField_a_of_type_Int == 3)
/* 379:    */        try {
/* 380:380 */          r = new zl("data//shader/cube/quads13/cube-3rd.vsh", "data//shader/cube/quads13/cube.fsh");
/* 381:    */        }
/* 382:    */        catch (Exception localException)
/* 383:    */        {
/* 384:384 */          
/* 385:    */          
/* 391:391 */            localException.printStackTrace();System.err.println("Exception: CANNOT COMPILE SHADER. Trying fallback without arrays");r = new zm("data//shader/cube/quads13/cube-3rd-bk.vsh", "data//shader/cube/quads13/cube.fsh");
/* 392:    */        } else {
/* 393:393 */        r = new zn("data//shader/cube/quads13/cube.vsh", "data//shader/cube/quads13/cube.fsh");
/* 394:    */      }
/* 395:    */      
/* 398:398 */      if (xu.s.b())
/* 399:    */      {
/* 400:400 */        if (jdField_a_of_type_Int == 3) {
/* 401:401 */          f = new zo("data//shader/cube/shieldCube/shieldcube-3rd.vsh", "data//shader/cube/shieldCube/shieldcube.fsh");
/* 402:    */        }
/* 403:    */        else
/* 404:    */        {
/* 405:405 */          f = new zp("data//shader/cube/shieldCube/shieldcube.vsh", "data//shader/cube/shieldCube/shieldcube.fsh");
/* 406:    */        }
/* 407:    */      }
/* 408:    */      
/* 411:411 */      m = new zj("data//shader/cubemap.vsh", "data//shader/cubemap.fsh");
/* 412:    */      
/* 414:414 */      v = new zq("data//shader/skin/skin-tex.vert.glsl", "data//shader/skin/skin-tex.frag.glsl");
/* 415:    */      
/* 426:426 */      J = new zj("data//shader/bloom/bloom.vert.glsl", "data//shader/bloom/bloom1.frag.glsl");
/* 427:    */      
/* 428:428 */      n = new zj("data//shader/bloom/bloom.vert.glsl", "data//shader/bloom/bloom2.frag.glsl");
/* 429:    */      
/* 430:430 */      o = new zj("data//shader/bloom/bloom.vert.glsl", "data//shader/bloom/bloom3.frag.glsl");
/* 431:    */      
/* 432:432 */      p = new zj("data//shader/bloom/bloom.vert.glsl", "data//shader/bloom/bloom4.frag.glsl");
/* 433:    */      
/* 435:435 */      GlUtil.f();
/* 436:    */      
/* 438:438 */      jdField_a_of_type_JavaUtilSet.add(l);
/* 439:439 */      jdField_a_of_type_JavaUtilSet.add(C);
/* 440:    */      
/* 441:441 */      if (!xu.r.a().equals("none")) {
/* 442:442 */        jdField_a_of_type_JavaUtilSet.add(B);
/* 443:443 */        jdField_a_of_type_JavaUtilSet.add(A);
/* 444:    */      }
/* 445:    */      
/* 446:446 */      jdField_a_of_type_JavaUtilSet.add(z);
/* 447:447 */      jdField_a_of_type_JavaUtilSet.add(y);
/* 448:448 */      jdField_a_of_type_JavaUtilSet.add(x);
/* 449:449 */      jdField_a_of_type_JavaUtilSet.add(e);
/* 450:450 */      jdField_a_of_type_JavaUtilSet.add(v);
/* 451:451 */      jdField_a_of_type_JavaUtilSet.add(w);
/* 452:452 */      jdField_a_of_type_JavaUtilSet.add(u);
/* 453:453 */      jdField_a_of_type_JavaUtilSet.add(t);
/* 454:454 */      jdField_a_of_type_JavaUtilSet.add(s);
/* 455:455 */      jdField_a_of_type_JavaUtilSet.add(d);
/* 456:456 */      jdField_a_of_type_JavaUtilSet.add(f);
/* 457:    */      
/* 469:469 */      jdField_a_of_type_JavaUtilSet.add(I);
/* 470:470 */      jdField_a_of_type_JavaUtilSet.add(D);
/* 471:    */      
/* 474:474 */      jdField_a_of_type_JavaUtilSet.add(i);
/* 475:475 */      jdField_a_of_type_JavaUtilSet.add(E);
/* 476:476 */      jdField_a_of_type_JavaUtilSet.add(j);
/* 477:477 */      jdField_a_of_type_JavaUtilSet.add(k);
/* 478:478 */      jdField_a_of_type_JavaUtilSet.add(G);
/* 479:479 */      jdField_a_of_type_JavaUtilSet.add(H);
/* 480:480 */      jdField_a_of_type_JavaUtilSet.add(h);
/* 481:    */      
/* 482:482 */      jdField_a_of_type_JavaUtilSet.add(F);
/* 483:483 */      jdField_a_of_type_JavaUtilSet.add(jdField_a_of_type_Zj);
/* 484:484 */      jdField_a_of_type_JavaUtilSet.add(b);
/* 485:485 */      jdField_a_of_type_JavaUtilSet.add(g);
/* 486:486 */      jdField_a_of_type_JavaUtilSet.add(c);
/* 487:487 */      jdField_a_of_type_JavaUtilSet.add(m);
/* 488:488 */      jdField_a_of_type_JavaUtilSet.add(J);
/* 489:489 */      jdField_a_of_type_JavaUtilSet.add(n);
/* 490:490 */      jdField_a_of_type_JavaUtilSet.add(o);
/* 491:491 */      jdField_a_of_type_JavaUtilSet.add(p);
/* 492:492 */      jdField_a_of_type_JavaUtilSet.add(null);
/* 493:    */      
/* 494:494 */      jdField_a_of_type_JavaUtilSet.add(r); return;
/* 495:495 */    } catch (ResourceException localResourceException) { 
/* 496:    */      
/* 497:497 */        localResourceException;
/* 498:    */    }
/* 499:    */  }
/* 500:    */  
/* 507:    */  public static void c()
/* 508:    */  {
/* 509:507 */    for (zj localzj : jdField_a_of_type_JavaUtilSet) {
/* 510:    */      try {
/* 511:509 */        if (localzj != null) {
/* 512:510 */          localzj.jdField_a_of_type_JavaUtilMap.clear();
/* 513:511 */          localzj.a();
/* 514:    */        }
/* 515:513 */      } catch (ResourceException localResourceException) { 
/* 516:    */        
/* 517:515 */          localResourceException;
/* 518:    */      }
/* 519:    */    }
/* 520:    */  }
/* 521:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     zk
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */