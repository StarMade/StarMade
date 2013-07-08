/*   1:    */import java.util.ArrayList;
/*   2:    */import java.util.HashSet;
/*   3:    */import java.util.Iterator;
/*   4:    */import javax.vecmath.Vector3f;
/*   5:    */import org.schema.game.client.view.SegmentDrawer;
/*   6:    */import org.schema.schine.graphicsengine.camera.Camera;
/*   7:    */
/* 288:    */public final class de
/* 289:    */  extends Thread
/* 290:    */{
/* 291:    */  private ArrayList jdField_a_of_type_JavaUtilArrayList;
/* 292:    */  public Object a;
/* 293:    */  public int a;
/* 294:    */  public long a;
/* 295:    */  
/* 296:    */  public de(SegmentDrawer paramSegmentDrawer)
/* 297:    */  {
/* 298:298 */    super("SegmentLightingUpdateThreadManager");
/* 299:299 */    SegmentDrawer.a(paramSegmentDrawer, new HashSet(100));
/* 300:300 */    SegmentDrawer.b(paramSegmentDrawer, new HashSet(100));
/* 301:301 */    this.jdField_a_of_type_JavaUtilArrayList = new ArrayList(2);
/* 302:302 */    for (int i = 0; i < 2; i++) {
/* 303:    */      dd localdd;
/* 304:304 */      (localdd = new dd(paramSegmentDrawer, this)).start();
/* 305:305 */      this.jdField_a_of_type_JavaUtilArrayList.add(localdd);
/* 306:    */    }
/* 307:    */  }
/* 308:    */  
/* 311:    */  public final void a(dd paramdd, mr parammr, boolean arg3)
/* 312:    */  {
/* 313:313 */    if (??? != 0) {
/* 314:314 */      synchronized (SegmentDrawer.b(this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer)) {
/* 315:315 */        SegmentDrawer.b(this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer).remove(parammr);
/* 316:    */      }
/* 317:    */    }
/* 318:318 */    synchronized (this.jdField_a_of_type_JavaUtilArrayList)
/* 319:    */    {
/* 320:320 */      this.jdField_a_of_type_JavaUtilArrayList.add(paramdd);
/* 321:321 */      this.jdField_a_of_type_JavaUtilArrayList.notify(); return;
/* 322:    */    }
/* 323:    */  }
/* 324:    */  
/* 325:325 */  public final void a(mr parammr) { if (parammr.g()) {
/* 326:326 */      parammr.e(false);return;
/* 327:    */    }
/* 328:328 */    if (!parammr.b)
/* 329:    */    {
/* 330:330 */      parammr.b = true;
/* 331:    */      
/* 332:332 */      SegmentDrawer.c(this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer).add(parammr);
/* 333:    */    }
/* 334:    */  }
/* 335:    */  
/* 337:    */  public final void a()
/* 338:    */  {
/* 339:339 */    if (!SegmentDrawer.c(this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer).isEmpty()) {
/* 340:340 */      synchronized (SegmentDrawer.d(this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer)) {
/* 341:341 */        SegmentDrawer.d(this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer).addAll(SegmentDrawer.c(this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer));
/* 342:342 */        SegmentDrawer.d(this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer).notify();
/* 343:    */      }
/* 344:    */      
/* 345:345 */      SegmentDrawer.c(this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer).clear();
/* 346:    */    }
/* 347:    */  }
/* 348:    */  
/* 349:    */  public final void run()
/* 350:    */  {
/* 351:    */    try {
/* 352:352 */      while (xe.a() == null) {}
/* 353:353 */      while (!xm.a()) {
/* 354:354 */        dd localdd = null;
/* 355:355 */        synchronized (this.jdField_a_of_type_JavaUtilArrayList) {
/* 356:356 */          while (this.jdField_a_of_type_JavaUtilArrayList.isEmpty()) {
/* 357:357 */            this.jdField_a_of_type_JavaUtilArrayList.wait();
/* 358:    */          }
/* 359:359 */          localdd = (dd)this.jdField_a_of_type_JavaUtilArrayList.remove(0);
/* 360:    */        }
/* 361:    */        
/* 363:363 */        synchronized (SegmentDrawer.d(this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer)) {
/* 364:364 */          while (SegmentDrawer.d(this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer).isEmpty()) {
/* 365:365 */            SegmentDrawer.d(this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer).wait();
/* 366:    */          }
/* 367:    */          da localda;
/* 368:368 */          da.a(localda = new da()).set(xe.a().a());
/* 369:    */          
/* 370:370 */          Object localObject7 = null;
/* 371:371 */          for (Object localObject8 = SegmentDrawer.d(this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer).iterator(); ((Iterator)localObject8).hasNext();) { mr localmr = (mr)((Iterator)localObject8).next();
/* 372:372 */            if ((localObject7 == null) || (localda.a((mr)localObject7, localmr) > 0)) {
/* 373:373 */              localObject7 = localmr;
/* 374:    */            }
/* 375:    */          }
/* 376:    */          
/* 377:377 */          localObject8 = localObject7;
/* 378:    */          
/* 379:379 */          SegmentDrawer.d(this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer).remove(localObject8);
/* 380:380 */          ((mr)localObject8).b = false;
/* 381:381 */          synchronized (SegmentDrawer.b(this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer)) {
/* 382:382 */            synchronized (SegmentDrawer.a(this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer)) {
/* 383:383 */              if ((((mr)localObject8).c()) || (SegmentDrawer.b(this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer).contains(localObject8)))
/* 384:    */              {
/* 385:385 */                a(localdd, (mr)localObject8, false);
/* 386:386 */                continue;
/* 387:    */              }
/* 388:388 */              SegmentDrawer.b(this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer).add(localObject8);
/* 389:    */            }
/* 390:    */          }
/* 391:391 */          synchronized (((mr)localObject8).a) {
/* 392:392 */            ((mr)localObject8).d(true);
/* 393:393 */            ((mr)localObject8).c = System.currentTimeMillis();
/* 394:394 */            ((mr)localObject8).e(false);
/* 395:    */          }
/* 396:396 */          localObject3.a((mr)localObject8);
/* 397:    */        }
/* 398:    */      }
/* 399:    */      
/* 402:402 */      return;
/* 403:403 */    } catch (InterruptedException localInterruptedException) { Object localObject5 = null;localInterruptedException.printStackTrace();
/* 404:    */    }
/* 405:    */  }
/* 406:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     de
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */