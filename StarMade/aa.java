/*     */ import java.io.PrintStream;
/*     */ import java.util.HashSet;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ import org.lwjgl.input.Mouse;
/*     */ 
/*     */ public final class aa extends U
/*     */ {
/*     */   public static final HashSet b;
/*     */   private long b;
/*     */ 
/*     */   public aa(ct paramct)
/*     */   {
/*  31 */     super(paramct);
/*     */   }
/*     */ 
/*     */   public final void handleKeyEvent()
/*     */   {
/*  43 */     super.handleKeyEvent();
/*     */ 
/*  45 */     if ((Keyboard.getEventKeyState()) && 
/*  46 */       (Keyboard.getEventKey() == 88)) {
/*  47 */       a().a().a().a().a(new q(0, 0, 0));
/*     */     }
/*     */ 
/*  50 */     a().a().a().handleKeyEvent();
/*     */   }
/*     */ 
/*     */   public final void a(xp paramxp)
/*     */   {
/*  57 */     if ((paramxp.jdField_a_of_type_Boolean) && 
/*  58 */       (paramxp.jdField_a_of_type_Int == 0)) {
/*  59 */       int i = System.currentTimeMillis() - this.jdField_b_of_type_Long < 300L ? 1 : 0;
/*  60 */       for (cD localcD : jdField_b_of_type_JavaUtilHashSet) {
/*  61 */         if (i != 0) {
/*  62 */           q localq = a().a().a().a().b;
/*  63 */           cG localcG = (cG)localcD;
/*  64 */           a().a().a().a().a((int)(localcG.a().x / 6.25F) + (localq.jdField_a_of_type_Int << 4), (int)(localcG.a().y / 6.25F) + (localq.b << 4), (int)(localcG.a().z / 6.25F) + (localq.c << 4), false);
/*     */         }
/*     */ 
/*  69 */         System.err.println("[CLIENT][MAPMANAGER] clicked on " + localcD);
/*     */       }
/*  71 */       this.jdField_b_of_type_Long = System.currentTimeMillis();
/*     */     }
/*     */ 
/*  75 */     super.a(paramxp);
/*     */   }
/*     */ 
/*     */   public final void b(boolean paramBoolean)
/*     */   {
/*  83 */     System.err.println("MAP CONTROL MANAGER ACTIVE: " + paramBoolean);
/*  84 */     if (paramBoolean) {
/*  85 */       xe.b("0022_menu_ui - swoosh scroll large");
/*  86 */       a().a().a().a().a(new q(0, 0, 0));
/*     */     }
/*     */     else {
/*  89 */       xe.b("0022_menu_ui - swoosh scroll small");
/*     */     }
/*  91 */     a().a().a.a.a.a().a().jdField_a_of_type_Bl.e(paramBoolean);
/*     */ 
/*  94 */     a().a().a.a.a.a().a().jdField_a_of_type_Au.e(paramBoolean);
/*     */ 
/*  97 */     if (paramBoolean) {
/*  98 */       a().a().a().d();
/*     */     }
/*     */ 
/* 101 */     super.b(paramBoolean);
/*     */   }
/*     */ 
/*     */   public final void a(xq paramxq)
/*     */   {
/* 107 */     wV.jdField_a_of_type_Boolean = Mouse.isButtonDown(1);
/* 108 */     a().a().a.a.a.e(true);
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*  27 */     jdField_b_of_type_JavaUtilHashSet = new HashSet();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     aa
 * JD-Core Version:    0.6.2
 */