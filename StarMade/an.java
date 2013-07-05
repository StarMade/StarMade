/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public final class an extends U
/*     */   implements ys
/*     */ {
/*     */   public mf a;
/*     */ 
/*     */   public an(ct paramct)
/*     */   {
/*  23 */     super(paramct);
/*     */   }
/*     */ 
/*     */   public final ct a()
/*     */   {
/*  38 */     return super.a();
/*     */   }
/*     */ 
/*     */   public final void handleKeyEvent()
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void a(xp paramxp)
/*     */   {
/*  47 */     super.a(paramxp);
/*     */   }
/*     */ 
/*     */   public final void b(boolean paramBoolean)
/*     */   {
/*  58 */     wV.jdField_a_of_type_Boolean = !paramBoolean;
/*     */ 
/*  60 */     if (paramBoolean) {
/*  61 */       xe.b("0022_menu_ui - swoosh scroll large");
/*  62 */       setChanged();
/*  63 */       notifyObservers();
/*     */     } else {
/*  65 */       xe.b("0022_menu_ui - swoosh scroll small");
/*     */     }
/*  67 */     super.a().a().a.a.a.a().a().jdField_a_of_type_Bl.e(paramBoolean);
/*     */ 
/*  70 */     super.a().a().a.a.a.a().a().jdField_a_of_type_Au.e(paramBoolean);
/*     */ 
/*  75 */     super.b(paramBoolean);
/*     */   }
/*     */ 
/*     */   public final void a(xq paramxq)
/*     */   {
/*  85 */     wV.jdField_a_of_type_Boolean = false;
/*  86 */     super.a().a().a.a.a.e(true);
/*     */   }
/*     */ 
/*     */   public final void a(yz paramyz, xp paramxp)
/*     */   {
/*  92 */     if ((paramxp.jdField_a_of_type_Boolean) && (paramxp.jdField_a_of_type_Int == 0) && 
/*  93 */       ("CONVERT".equals(paramyz.b())) && 
/*  94 */       (this.a != null) && ((this.a instanceof md)))
/*  95 */       ((md)this.a).c();
/*     */   }
/*     */ 
/*     */   public final boolean a()
/*     */   {
/* 104 */     return false;
/*     */   }
/*     */ 
/*     */   public final void b()
/*     */   {
/* 109 */     synchronized (super.a().b()) {
/* 110 */       super.a().b().add(new br(super.a()));
/* 111 */       e(true);
/* 112 */       return;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     an
 * JD-Core Version:    0.6.2
 */