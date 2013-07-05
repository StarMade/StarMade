/*     */ import java.util.HashSet;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ import org.schema.game.common.controller.EditableSendableSegmentController;
/*     */ 
/*     */ public final class bj extends U
/*     */   implements al
/*     */ {
/*     */   public bl a;
/*     */   public au a;
/*     */ 
/*     */   public bj(ct paramct)
/*     */   {
/*  30 */     super(paramct);
/*  31 */     paramct = this; this.jdField_a_of_type_Au = new au(paramct.a(), paramct); paramct.a.add(paramct.jdField_a_of_type_Au); paramct.jdField_a_of_type_Bl = new bl(paramct); paramct.a.add(paramct.jdField_a_of_type_Bl);
/*     */   }
/*     */ 
/*     */   public final q a()
/*     */   {
/*  36 */     return new q(kd.a);
/*     */   }
/*     */ 
/*     */   public final au a()
/*     */   {
/*  47 */     return this.jdField_a_of_type_Au;
/*     */   }
/*     */ 
/*     */   public final EditableSendableSegmentController a()
/*     */   {
/*  52 */     return a().a();
/*     */   }
/*     */ 
/*     */   public final bl a()
/*     */   {
/*  66 */     return this.jdField_a_of_type_Bl;
/*     */   }
/*     */ 
/*     */   public final void handleKeyEvent()
/*     */   {
/*  71 */     super.handleKeyEvent();
/*     */ 
/*  74 */     if ((Keyboard.getEventKeyState()) && 
/*  75 */       (Keyboard.getEventKey() == cv.r.a())) {
/*  76 */       bj localbj = this; boolean bool = this.jdField_a_of_type_Au.b; localbj.jdField_a_of_type_Bl.c(bool); localbj.jdField_a_of_type_Au.c(!bool);
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void a(xp paramxp)
/*     */   {
/*  84 */     super.a(paramxp);
/*     */   }
/*     */ 
/*     */   public final void b(boolean paramBoolean)
/*     */   {
/* 104 */     if ((paramBoolean) && 
/* 105 */       (!this.jdField_a_of_type_Au.b) && (!this.jdField_a_of_type_Bl.b)) {
/* 106 */       this.jdField_a_of_type_Au.c(true);
/*     */     }
/*     */ 
/* 110 */     super.b(paramBoolean);
/*     */   }
/*     */ 
/*     */   public final void a(xq paramxq)
/*     */   {
/* 139 */     if (a().a() == null) {
/* 140 */       return;
/*     */     }
/*     */ 
/* 143 */     super.a(paramxq);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     bj
 * JD-Core Version:    0.6.2
 */