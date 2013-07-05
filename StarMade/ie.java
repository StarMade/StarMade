/*     */ import java.io.PrintStream;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ import org.lwjgl.input.Mouse;
/*     */ 
/*     */ final class ie extends H
/*     */ {
/*     */   private boolean b;
/*     */ 
/*     */   ie(id paramid, ct paramct)
/*     */   {
/*  54 */     super(paramct);
/*     */   }
/*     */ 
/*     */   public final void a(yz paramyz, xp paramxp)
/*     */   {
/*  61 */     if ((Mouse.isButtonDown(0)) && (!this.b))
/*     */     {
/*  63 */       if ((paramyz.b().equals("CANCEL")) || (paramyz.b().equals("X"))) {
/*  64 */         System.err.println("CANCEL");
/*  65 */         d();
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/*  70 */     this.b = Mouse.isButtonDown(0);
/*     */   }
/*     */ 
/*     */   public final yz a()
/*     */   {
/*  76 */     return id.a(this.a);
/*     */   }
/*     */ 
/*     */   public final void handleKeyEvent()
/*     */   {
/*  81 */     if (Keyboard.getEventKeyState()) {
/*  82 */       if (Keyboard.getEventKey() == 1) {
/*  83 */         d(); return;
/*     */       }
/*  85 */       if (!xu.C.b()) {
/*  86 */         Keyboard.getEventKey(); this.a.c();
/*     */       }
/*     */ 
/*  89 */       id.a(this.a).a(Keyboard.getEventKey());
/*  90 */       cv.b();
/*  91 */       d();
/*     */     }
/*     */   }
/*     */ 
/*     */   protected final void e()
/*     */   {
/* 105 */     id.a(this.a, new eX(this.a, this, "Assign New Key to " + id.a(this.a).a(), "Press a Key to assign it to \n\n<" + id.a(this.a).a() + "> \n\nor press ESC to cancel."));
/*     */ 
/* 107 */     id.a(this.a).e();
/* 108 */     id.a(this.a).a(this);
/*     */   }
/*     */ 
/*     */   public final boolean a()
/*     */   {
/* 114 */     return false;
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/* 119 */     id.d();
/* 120 */     id.a(System.currentTimeMillis());
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ie
 * JD-Core Version:    0.6.2
 */