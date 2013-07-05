/*     */ import java.util.Set;
/*     */ import org.schema.game.common.controller.CannotBeControlledException;
/*     */ import org.schema.game.common.controller.EditableSendableSegmentController;
/*     */ import org.schema.game.common.data.element.ElementInformation;
/*     */ import org.schema.game.common.data.element.ElementKeyMap;
/*     */ 
/*     */ final class av
/*     */   implements ag
/*     */ {
/*     */   av(au paramau)
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void a(q paramq1, q paramq2, short paramShort)
/*     */   {
/* 352 */     if (ElementKeyMap.getInfo(paramq2 = this.a.a().a().a().a().a().a())
/* 352 */       .getControlledBy().contains(Short.valueOf((short)1)))
/*     */     {
/* 354 */       if ((
/* 354 */         paramShort = au.a(this.a).a().getSegmentBuffer().a(au.a(this.a).a(), false)) != null) {
/*     */         try
/*     */         {
/* 356 */           au.a(this.a).a().setCurrentBlockController(paramShort, paramq1);
/*     */         } catch (CannotBeControlledException paramShort) {
/* 358 */           this.a.a(paramShort);
/*     */         }
/*     */       }
/*     */ 
/* 362 */       if ((xu.P.b()) && (ElementKeyMap.getInfo(paramq2).getControlling().size() > 0))
/*     */       {
/* 364 */         if ((
/* 364 */           paramShort = au.a(this.a).a().getSegmentBuffer().a(paramq1, false)) != null)
/*     */         {
/* 365 */           au.a(this.a, paramShort);
/*     */         }
/*     */       }
/* 368 */       return; } if ((au.a(this.a) != null) && (paramq1 != null)) {
/* 369 */       au.a(this.a).a();
/*     */ 
/* 371 */       if (au.a(this.a).a() != 0) try { au.a(this.a).a().setCurrentBlockController(au.a(this.a), paramq1);
/*     */           return;
/*     */         } catch (CannotBeControlledException paramShort) {
/* 375 */           this.a.a(paramShort);
/* 376 */           return;
/*     */         }
/* 378 */       if (ElementKeyMap.getInfo(paramq2).getControlledBy().size() > 0) {
/* 379 */         this.a.a().a().d("Note:\nYou placed a controllable Block\nWithout having a conroller selected!\nE.g. if you place weapons, please\nselect/place the weapon computer\nfirst.");
/*     */       }
/*     */ 
/*     */     }
/* 387 */     else if ((au.a(this.a) == null) && (ElementKeyMap.getInfo(paramq2).getControlledBy().size() > 0)) {
/* 388 */       this.a.a().a().d("Note:\nYou placed a controllable Block\nWithout having a conroller selected!\nE.g. if you place weapons, please\nselect/place the weapon computer\nfirst.");
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     av
 * JD-Core Version:    0.6.2
 */