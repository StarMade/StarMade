/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public final class bg extends U
/*     */   implements ys
/*     */ {
/*  24 */   public bh a = new bh();
/*     */ 
/*     */   public bg(ct paramct)
/*     */   {
/*  21 */     super(paramct);
/*     */   }
/*     */ 
/*     */   public final void a(yz paramyz, xp paramxp)
/*     */   {
/*  28 */     if ((this.c) && (!this.jdField_a_of_type_Boolean))
/*  29 */       for (paramxp = a().getMouseEvents().iterator(); paramxp.hasNext(); )
/*     */       {
/*     */         Object localObject;
/*  30 */         if (((
/*  30 */           localObject = (xp)paramxp.next()).jdField_a_of_type_Int == 0) && 
/*  30 */           (!((xp)localObject).jdField_a_of_type_Boolean) && 
/*  31 */           ((paramyz instanceof yD)))
/*     */         {
/*     */           yA localyA;
/*  33 */           (
/*  34 */             localyA = (yA)(
/*  33 */             localObject = (yD)paramyz)
/*  33 */             .a())
/*  34 */             .indexOf(localObject);
/*  35 */           localyA.e();
/*  36 */           ((yD)localObject).a(true);
/*  37 */           a().a().a.a.a.a(((ib)((yD)localObject).a()).a());
/*     */         }
/*     */       }
/*     */   }
/*     */ 
/*     */   public final void handleKeyEvent()
/*     */   {
/*  55 */     super.handleKeyEvent();
/*     */   }
/*     */ 
/*     */   public final void a(xp paramxp)
/*     */   {
/*  62 */     super.a(paramxp);
/*     */   }
/*     */ 
/*     */   public final boolean a()
/*     */   {
/*  68 */     return !a().b().isEmpty();
/*     */   }
/*     */ 
/*     */   public final void b(boolean paramBoolean)
/*     */   {
/*  75 */     wV.jdField_a_of_type_Boolean = !paramBoolean;
/*     */ 
/*  77 */     if (paramBoolean)
/*  78 */       xe.b("0022_menu_ui - swoosh scroll large");
/*     */     else {
/*  80 */       xe.b("0022_menu_ui - swoosh scroll small");
/*     */     }
/*  82 */     a().a().a.a.a.a().a().jdField_a_of_type_Bl.e(paramBoolean);
/*     */ 
/*  85 */     a().a().a.a.a.a().a().jdField_a_of_type_Au.e(paramBoolean);
/*     */ 
/*  88 */     super.b(paramBoolean);
/*     */   }
/*     */ 
/*     */   public final void a(xq paramxq)
/*     */   {
/*  94 */     wV.jdField_a_of_type_Boolean = false;
/*     */ 
/*  96 */     a().a().a.a.a.e(true);
/*     */   }
/*     */ 
/*     */   public final bh a()
/*     */   {
/* 110 */     return new bh(this.a);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     bg
 * JD-Core Version:    0.6.2
 */