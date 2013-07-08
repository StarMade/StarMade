/*   1:    */import java.util.Set;
/*   2:    */import org.schema.game.common.controller.CannotBeControlledException;
/*   3:    */import org.schema.game.common.controller.EditableSendableSegmentController;
/*   4:    */import org.schema.game.common.data.element.ElementInformation;
/*   5:    */import org.schema.game.common.data.element.ElementKeyMap;
/*   6:    */
/* 345:    */final class av
/* 346:    */  implements ag
/* 347:    */{
/* 348:    */  av(au paramau) {}
/* 349:    */  
/* 350:    */  public final void a(q paramq1, q paramq2, short paramShort)
/* 351:    */  {
/* 352:352 */    if (ElementKeyMap.getInfo(paramq2 = this.a.a().a().a().a().a().a()).getControlledBy().contains(Short.valueOf((short)1)))
/* 353:    */    {
/* 354:354 */      if ((paramShort = au.a(this.a).a().getSegmentBuffer().a(au.a(this.a).a(), false)) != null) {
/* 355:    */        try {
/* 356:356 */          au.a(this.a).a().setCurrentBlockController(paramShort, paramq1);
/* 357:    */        } catch (CannotBeControlledException paramShort) {
/* 358:358 */          this.a.a(paramShort);
/* 359:    */        }
/* 360:    */      }
/* 361:    */      
/* 362:362 */      if ((xu.P.b()) && (ElementKeyMap.getInfo(paramq2).getControlling().size() > 0))
/* 363:    */      {
/* 364:364 */        if ((paramShort = au.a(this.a).a().getSegmentBuffer().a(paramq1, false)) != null) {
/* 365:365 */          au.a(this.a, paramShort);
/* 366:    */        }
/* 367:    */      }
/* 368:368 */      return; } if ((au.a(this.a) != null) && (paramq1 != null)) {
/* 369:369 */      au.a(this.a).a();
/* 370:    */      
/* 371:371 */      if (au.a(this.a).a() != 0)
/* 372:    */        try {
/* 373:373 */          au.a(this.a).a().setCurrentBlockController(au.a(this.a), paramq1); return;
/* 374:    */        } catch (CannotBeControlledException paramShort) {
/* 375:375 */          this.a.a(paramShort);
/* 376:376 */          return;
/* 377:    */        }
/* 378:378 */      if (ElementKeyMap.getInfo(paramq2).getControlledBy().size() > 0) {
/* 379:379 */        this.a.a().a().d("Note:\nYou placed a controllable Block\nWithout having a conroller selected!\nE.g. if you place weapons, please\nselect/place the weapon computer\nfirst.");
/* 382:    */      }
/* 383:    */      
/* 386:    */    }
/* 387:387 */    else if ((au.a(this.a) == null) && (ElementKeyMap.getInfo(paramq2).getControlledBy().size() > 0)) {
/* 388:388 */      this.a.a().a().d("Note:\nYou placed a controllable Block\nWithout having a conroller selected!\nE.g. if you place weapons, please\nselect/place the weapon computer\nfirst.");
/* 389:    */    }
/* 390:    */  }
/* 391:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     av
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */