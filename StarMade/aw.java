/*     */ import org.schema.game.common.controller.CannotBeControlledException;
/*     */ import org.schema.game.common.controller.EditableSendableSegmentController;
/*     */ 
/*     */ final class aw
/*     */   implements ag
/*     */ {
/*     */   aw(au paramau)
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void a(q paramq1, q paramq2, short paramShort)
/*     */   {
/* 601 */     paramShort = this.a.a().a().a().a().a().a();
/*     */ 
/* 604 */     int i = 0;
/* 605 */     if (au.a(this.a) == null) {
/* 606 */       i = 1;
/* 607 */       au.a(this.a, new ju());
/*     */     }
/*     */ 
/* 610 */     if ((paramq1 != null) && (i != 0)) {
/* 611 */       if (paramq1.a - paramq2.a != 0) {
/* 612 */         i = paramq1.a; (paramq2 = au.a(this.a)).jdField_a_of_type_ArrayOfInt[0] = i; paramq2.jdField_a_of_type_ArrayOfBoolean[0] = true;
/* 613 */       } else if (paramq1.b - paramq2.b != 0) {
/* 614 */         i = paramq1.b; (paramq2 = au.a(this.a)).jdField_a_of_type_ArrayOfInt[1] = i; paramq2.jdField_a_of_type_ArrayOfBoolean[1] = true;
/* 615 */       } else if (paramq1.c - paramq2.c != 0) {
/* 616 */         i = paramq1.c; (paramq2 = au.a(this.a)).jdField_a_of_type_ArrayOfInt[2] = i; paramq2.jdField_a_of_type_ArrayOfBoolean[2] = true;
/*     */       }
/*     */     }
/* 619 */     if (paramShort == 8)
/*     */     {
/* 621 */       if ((
/* 621 */         paramq2 = au.a(this.a).a().getSegmentBuffer().a(au.a(this.a).a(), false)) != null) try {
/* 623 */           au.a(this.a).a().setCurrentBlockController(paramq2, paramq1);
/*     */           return;
/*     */         } catch (CannotBeControlledException paramq1) {
/* 625 */           this.a.a(paramq1);
/*     */         }
/*     */ 
/* 628 */       return; } if ((au.a(this.a) != null) && (paramq1 != null)) {
/* 629 */       au.a(this.a).a();
/* 630 */       if (au.a(this.a).a() != 0) try { au.a(this.a).a().setCurrentBlockController(au.a(this.a), paramq1);
/*     */           return;
/*     */         } catch (CannotBeControlledException paramq2) {
/* 634 */           this.a.a(paramq2);
/*     */         }
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     aw
 * JD-Core Version:    0.6.2
 */