/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ import org.schema.schine.network.ChatSystem;
/*     */ 
/*     */ public final class am extends U
/*     */ {
/*     */   private ak a;
/*     */   public ae a;
/*     */   public ac a;
/*     */   public aq a;
/*     */ 
/*     */   public am(ct paramct)
/*     */   {
/*  24 */     super(paramct);
/*  25 */     paramct = this; this.jdField_a_of_type_Ak = new ak(paramct.a()); paramct.jdField_a_of_type_Ae = new ae(paramct.a()); paramct.jdField_a_of_type_Ac = new ac(paramct.a()); paramct.jdField_a_of_type_Aq = new aq(paramct.a()); paramct.a.add(paramct.jdField_a_of_type_Ak); paramct.a.add(paramct.jdField_a_of_type_Ae); paramct.a.add(paramct.jdField_a_of_type_Ac); paramct.a.add(paramct.jdField_a_of_type_Aq); paramct.jdField_a_of_type_Ac.c(true);
/*     */   }
/*     */ 
/*     */   public final ac a()
/*     */   {
/*  36 */     return this.jdField_a_of_type_Ac;
/*     */   }
/*     */ 
/*     */   public final ak a()
/*     */   {
/*  44 */     return this.jdField_a_of_type_Ak;
/*     */   }
/*     */ 
/*     */   public final ae a()
/*     */   {
/*  49 */     return this.jdField_a_of_type_Ae;
/*     */   }
/*     */ 
/*     */   public final aq a()
/*     */   {
/*  54 */     return this.jdField_a_of_type_Aq;
/*     */   }
/*     */ 
/*     */   public final void handleKeyEvent()
/*     */   {
/*  63 */     synchronized (a().b())
/*     */     {
/*  65 */       int i = 0; if (b())
/*     */       {
/*  66 */         return;
/*     */       }
/*     */     }
/*     */ 
/*  70 */     boolean bool1 = this.jdField_a_of_type_Ak.b;
/*     */ 
/*  72 */     if (Keyboard.getEventKeyState()) {
/*  73 */       if (Keyboard.getEventKey() == cv.F.a()) {
/*  74 */         if (!bool1) {
/*  75 */           if ((Keyboard.isKeyDown(42)) || (Keyboard.isKeyDown(54))) {
/*  76 */             System.err.println("USING FACTION CHAT");
/*  77 */             a().getChat().getTextInput().a("/f ");
/*     */           }
/*  79 */           this.jdField_a_of_type_Ak.d(true);
/*     */         }
/*     */         else
/*     */         {
/*  84 */           this.jdField_a_of_type_Ak.d(false);
/*     */         }
/*     */       }
/*  87 */       else if ((Keyboard.getEventKey() == 87) && 
/*  88 */         (!bool1))
/*     */       {
/*     */         boolean bool2;
/*  92 */         if ((!(
/*  92 */           bool2 = this.jdField_a_of_type_Ae.b)) && 
/*  92 */           (!a().b().isEmpty()))
/*     */         {
/*  95 */           return;
/*     */         }
/*  97 */         if ((bool2) && (a().a() == null)) {
/*  98 */           System.out.println("no player character: spawning");
/*  99 */           a().a().e();
/*     */         }
/*     */         else {
/* 102 */           this.jdField_a_of_type_Aq.c(bool2);
/* 103 */           this.jdField_a_of_type_Ae.c(!bool2);
/*     */         }
/*     */ 
/* 106 */         if ((this.jdField_a_of_type_Ac.b) && (!bool2)) {
/* 107 */           this.jdField_a_of_type_Ac.c(false);
/* 108 */           this.jdField_a_of_type_Ae.c(true);
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 120 */     super.handleKeyEvent();
/*     */   }
/*     */ 
/*     */   public final void a(xp paramxp)
/*     */   {
/* 126 */     super.a(paramxp);
/*     */   }
/*     */ 
/*     */   public final void a(xq paramxq)
/*     */   {
/* 173 */     super.a(paramxq);
/* 174 */     if (!a().b().isEmpty())
/* 175 */       wV.a = false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     am
 * JD-Core Version:    0.6.2
 */