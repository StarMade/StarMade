/*   1:    */import org.schema.game.common.controller.CannotBeControlledException;
/*   2:    */import org.schema.game.common.controller.EditableSendableSegmentController;
/*   3:    */
/* 594:    */final class aw
/* 595:    */  implements ag
/* 596:    */{
/* 597:    */  aw(au paramau) {}
/* 598:    */  
/* 599:    */  public final void a(q paramq1, q paramq2, short paramShort)
/* 600:    */  {
/* 601:601 */    paramShort = this.a.a().a().a().a().a().a();
/* 602:    */    
/* 604:604 */    int i = 0;
/* 605:605 */    if (au.a(this.a) == null) {
/* 606:606 */      i = 1;
/* 607:607 */      au.a(this.a, new ju());
/* 608:    */    }
/* 609:    */    
/* 610:610 */    if ((paramq1 != null) && (i != 0)) {
/* 611:611 */      if (paramq1.a - paramq2.a != 0) {
/* 612:612 */        i = paramq1.a;(paramq2 = au.a(this.a)).jdField_a_of_type_ArrayOfInt[0] = i;paramq2.jdField_a_of_type_ArrayOfBoolean[0] = true;
/* 613:613 */      } else if (paramq1.b - paramq2.b != 0) {
/* 614:614 */        i = paramq1.b;(paramq2 = au.a(this.a)).jdField_a_of_type_ArrayOfInt[1] = i;paramq2.jdField_a_of_type_ArrayOfBoolean[1] = true;
/* 615:615 */      } else if (paramq1.c - paramq2.c != 0) {
/* 616:616 */        i = paramq1.c;(paramq2 = au.a(this.a)).jdField_a_of_type_ArrayOfInt[2] = i;paramq2.jdField_a_of_type_ArrayOfBoolean[2] = true;
/* 617:    */      }
/* 618:    */    }
/* 619:619 */    if (paramShort == 8)
/* 620:    */    {
/* 621:621 */      if ((paramq2 = au.a(this.a).a().getSegmentBuffer().a(au.a(this.a).a(), false)) != null) {
/* 622:    */        try {
/* 623:623 */          au.a(this.a).a().setCurrentBlockController(paramq2, paramq1); return;
/* 624:    */        } catch (CannotBeControlledException paramq1) {
/* 625:625 */          this.a.a(paramq1);
/* 626:    */        }
/* 627:    */      }
/* 628:628 */      return; } if ((au.a(this.a) != null) && (paramq1 != null)) {
/* 629:629 */      au.a(this.a).a();
/* 630:630 */      if (au.a(this.a).a() != 0) {
/* 631:    */        try {
/* 632:632 */          au.a(this.a).a().setCurrentBlockController(au.a(this.a), paramq1); return;
/* 633:    */        } catch (CannotBeControlledException paramq2) {
/* 634:634 */          this.a.a(paramq2);
/* 635:    */        }
/* 636:    */      }
/* 637:    */    }
/* 638:    */  }
/* 639:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     aw
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */