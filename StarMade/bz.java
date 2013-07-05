/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.schema.game.common.data.element.ElementInformation;
/*     */ import org.schema.game.common.data.element.ElementKeyMap;
/*     */ import org.schema.game.server.data.blueprintnw.BlueprintEntry;
/*     */ 
/*     */ public final class bz extends U
/*     */   implements ys
/*     */ {
/*     */   public short a;
/*     */   public yD a;
/*     */   private long jdField_b_of_type_Long;
/*     */   private yD jdField_b_of_type_YD;
/*     */ 
/*     */   public bz(ct paramct)
/*     */   {
/*  43 */     super(paramct);
/*     */ 
/*  38 */     this.jdField_a_of_type_Short = -1;
/*     */   }
/*     */ 
/*     */   public final void a(yz paramyz, xp arg2)
/*     */   {
/*  49 */     String str = null; if ((???.jdField_a_of_type_Boolean) && (???.jdField_a_of_type_Int == 0))
/*     */     {
/*  51 */       if ((paramyz instanceof yD))
/*     */       {
/*  53 */         ??? = (yD)paramyz;
/*  54 */         if (!"CATEGORY".equals(???.a().b()))
/*     */         {
/*  57 */           if (this.jdField_b_of_type_YD != null) {
/*  58 */             this.jdField_b_of_type_YD.a().e();
/*  59 */             this.jdField_b_of_type_YD.a(false);
/*     */           }
/*  61 */           ???.a(true);
/*  62 */           this.jdField_a_of_type_Short = ((Short)???.a().b()).shortValue();
/*  63 */           this.jdField_b_of_type_YD = ???;
/*     */         }
/*     */       }
/*     */ 
/*  67 */       if ("upload".equals(paramyz.b()))
/*     */       {
/*  69 */         if (System.currentTimeMillis() - this.jdField_b_of_type_Long < 5000L) {
/*  70 */           a().a().b("Cannot upload now!\nplease wait " + (System.currentTimeMillis() - this.jdField_b_of_type_Long) / 1000L + " seconds");
/*  71 */           return;
/*     */         }
/*  73 */         this.jdField_b_of_type_Long = System.currentTimeMillis();
/*     */ 
/*  75 */         ??? = tH.a.a();
/*     */ 
/*  77 */         str = "Please enter in a name for your blue print!\n\nAvailable:\n" + ???;
/*  78 */         ( = new bA(this, a(), "BluePrint", str, ???.isEmpty() ? "" : ((BlueprintEntry)???.get(0)).toString()))
/* 124 */           .a(new bB());
/*     */ 
/* 135 */         synchronized (a().b()) {
/* 136 */           a().b().add(???);
/* 137 */           e(true);
/*     */         }
/*     */       }
/* 140 */       if ("save_local".equals(paramyz.b()))
/*     */       {
/* 142 */         if (System.currentTimeMillis() - this.jdField_b_of_type_Long < 5000L) {
/* 143 */           a().a().b("Cannot save now!\nplease wait " + (System.currentTimeMillis() - this.jdField_b_of_type_Long) / 1000L + " seconds");
/* 144 */           return;
/*     */         }
/* 146 */         this.jdField_b_of_type_Long = System.currentTimeMillis();
/*     */ 
/* 148 */         ??? = "Please enter in a name for your blue print!";
/* 149 */         ( = new bC(this, a(), "BluePrint", ???, "BLUEPRINT_" + System.currentTimeMillis()))
/* 196 */           .a(new bD());
/*     */ 
/* 207 */         synchronized (a().b()) {
/* 208 */           a().b().add(???);
/* 209 */           e(true);
/*     */         }
/*     */       }
/*     */ 
/* 213 */       if ("save_catalog".equals(paramyz.b()))
/*     */       {
/* 215 */         if (System.currentTimeMillis() - this.jdField_b_of_type_Long < 5000L) {
/* 216 */           a().a().b("Cannot save now!\nplease wait " + (System.currentTimeMillis() - this.jdField_b_of_type_Long) / 1000L + " seconds");
/* 217 */           return;
/*     */         }
/* 219 */         this.jdField_b_of_type_Long = System.currentTimeMillis();
/*     */ 
/* 221 */         ??? = "Please enter in a name for your blue print!";
/* 222 */         ( = new bE(this, a(), "BluePrint", ???, "BLUEPRINT_" + System.currentTimeMillis()))
/* 267 */           .a(new bF());
/*     */ 
/* 278 */         synchronized (a().b()) {
/* 279 */           a().b().add(???);
/* 280 */           e(true);
/*     */         }
/*     */       }
/* 283 */       if (("buy_catalog".equals(paramyz.b())) && (a() != null))
/*     */       {
/* 285 */         if (System.currentTimeMillis() - this.jdField_b_of_type_Long < 5000L) {
/* 286 */           a().a().b("Cannot buy now!\nplease wait " + (System.currentTimeMillis() - this.jdField_b_of_type_Long) / 1000L + " seconds");
/* 287 */           return;
/*     */         }
/* 289 */         this.jdField_b_of_type_Long = System.currentTimeMillis();
/*     */ 
/* 291 */         ??? = "Please type in a name for your new Ship!";
/* 292 */         ( = new bG(this, a(), "New Ship", ???, a().a + "_" + System.currentTimeMillis()))
/* 337 */           .a(new bH());
/*     */ 
/* 348 */         synchronized (a().b()) {
/* 349 */           a().b().add(???);
/* 350 */           e(true);
/*     */         }
/*     */       }
/* 353 */       if (((paramyz instanceof yE)) && 
/* 354 */         (this.jdField_a_of_type_Short >= 0))
/*     */       {
/*     */         short s;
/* 355 */         if ("buy".equals(paramyz.b())) {
/* 356 */           s = this.jdField_a_of_type_Short; ??? = this;
/*     */           kf localkf2;
/* 356 */           ???.a().a().b("ERROR: no shop available"); int k = localkf2.a().a(s); int i = localkf2.a().a(k); ???.a().a().b("ERROR: shop out of item"); ???.a().a().b("ERROR: not enough credits"); if ((???.a().a().b() < localkf2.a(ElementKeyMap.getInfo(s), 1) ? 0 : (k < 0) || (i <= 0) ? 0 : (localkf2 = a().a()) == null ? 0 : 1) != 0) {
/* 357 */             xe.b("0022_action - purchase with credits");
/* 358 */             a().a().a().a(this.jdField_a_of_type_Short, 1);
/*     */           }
/*     */         }
/* 361 */         if ("sell".equals(paramyz.b()))
/*     */         {
/* 363 */           s = this.jdField_a_of_type_Short; ??? = this; if (a().a().getInventory(null).a(s) >= 0)
/*     */           {
/*     */             kf localkf1;
/* 363 */             if ((localkf1 = ???.a().a()) != null)
/*     */             {
/* 363 */               int j;
/* 363 */               if ((j = localkf1.a().a(s)) >= 0) localkf1.a(); ???.a().a().b("ERROR: shop has reached the max\nstock for the item\n" + ElementKeyMap.getInfo(s).getName()); } else { ???.a().a().b("ERROR: no shop in distance"); }  } else { ???.a().a().b("ERROR: you don't own that item\n" + ElementKeyMap.getInfo(s).getName()); } if ((localkf1.a().a(j) < mn.e() ? 1 : 0) != 0) {
/* 364 */             xe.b("0022_action - receive credits");
/* 365 */             a().a().a().b(this.jdField_a_of_type_Short, 1);
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/* 371 */         if ("sell_more".equals(paramyz.b()))
/*     */         {
/* 373 */           if (( = a().a()) != null)
/*     */           {
/* 374 */             a(this.jdField_a_of_type_Short, 1, ???);
/*     */           }
/* 376 */           else a().a().b("ERROR: shop no more in distance");
/*     */         }
/*     */ 
/* 379 */         if ("buy_more".equals(paramyz.b()))
/*     */         {
/* 382 */           if (( = a().a()) != null)
/*     */           {
/* 383 */             synchronized (a().b()) {
/* 384 */               a().b().add(new bo(a(), this.jdField_a_of_type_Short, ???));
/* 385 */               e(true);
/* 386 */               return;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public final lL a()
/*     */   {
/* 454 */     if (this.jdField_a_of_type_YD != null) {
/* 455 */       return (lL)this.jdField_a_of_type_YD.b();
/*     */     }
/* 457 */     return null;
/*     */   }
/*     */ 
/*     */   public final void handleKeyEvent()
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void a(xp paramxp)
/*     */   {
/* 473 */     super.a(paramxp);
/*     */   }
/*     */ 
/*     */   public final boolean a()
/*     */   {
/* 479 */     return !a().b().isEmpty();
/*     */   }
/*     */ 
/*     */   public final void b(boolean paramBoolean)
/*     */   {
/* 487 */     wV.jdField_a_of_type_Boolean = !paramBoolean;
/* 488 */     if (paramBoolean)
/* 489 */       xe.b("0022_menu_ui - swoosh scroll large");
/*     */     else {
/* 491 */       xe.b("0022_menu_ui - swoosh scroll small");
/*     */     }
/* 493 */     a().a().a.a.jdField_a_of_type_Ar.a().a().jdField_a_of_type_Bl.e(paramBoolean);
/*     */ 
/* 496 */     a().a().a.a.jdField_a_of_type_Ar.a().a().jdField_a_of_type_Au.e(paramBoolean);
/*     */ 
/* 502 */     super.b(paramBoolean);
/*     */   }
/*     */   public final void a(short paramShort, int paramInt, kf paramkf) {
/* 505 */     synchronized (a().b()) {
/* 506 */       a().b().add(new bw(a(), paramShort, paramInt, paramkf));
/* 507 */       e(true);
/* 508 */       return;
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void a(xq paramxq)
/*     */   {
/* 528 */     wV.jdField_a_of_type_Boolean = false;
/* 529 */     if (!a().d()) {
/* 530 */       a().a().b("no shop in range");
/* 531 */       d(false);
/* 532 */       a().a().a.a.jdField_a_of_type_An.d(true);
/*     */     }
/*     */ 
/* 535 */     a().a().a.a.jdField_a_of_type_Ar.e(true);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     bz
 * JD-Core Version:    0.6.2
 */