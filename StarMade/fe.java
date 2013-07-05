/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.util.ArrayList;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Observable;
/*     */ import java.util.Observer;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import org.lwjgl.util.vector.Vector4f;
/*     */ import org.newdawn.slick.SlickException;
/*     */ import org.newdawn.slick.UnicodeFont;
/*     */ import org.newdawn.slick.font.effects.ColorEffect;
/*     */ import org.newdawn.slick.font.effects.OutlineEffect;
/*     */ import org.schema.game.common.controller.elements.ElementCollectionManager;
/*     */ import org.schema.game.common.controller.elements.ShieldContainerInterface;
/*     */ import org.schema.game.common.controller.elements.shield.ShieldCollectionManager;
/*     */ import org.schema.schine.network.objects.Sendable;
/*     */ 
/*     */ public final class fe
/*     */   implements Observer, xg, xs
/*     */ {
/*     */   private fg jdField_a_of_type_Fg;
/*     */   private ct jdField_a_of_type_Ct;
/*     */   private u jdField_a_of_type_U;
/*     */   private yu jdField_a_of_type_Yu;
/*     */   private fi jdField_a_of_type_Fi;
/*     */   private yE jdField_a_of_type_YE;
/*     */   private yP jdField_a_of_type_YP;
/*     */   private yR jdField_a_of_type_YR;
/*  70 */   private float jdField_a_of_type_Float = 1.0F;
/*     */   private float b;
/*     */   private float c;
/*  73 */   private float d = -1.0F;
/*     */   public final LinkedList a;
/*     */   public final LinkedList b;
/*     */   public final LinkedList c;
/*     */   public fm a;
/*     */   public fm b;
/*     */   private fp jdField_a_of_type_Fp;
/*  93 */   private int jdField_a_of_type_Int = 1;
/*     */   private ii jdField_a_of_type_Ii;
/*     */   private ff jdField_a_of_type_Ff;
/*     */   private iy jdField_a_of_type_Iy;
/*     */   private eS jdField_a_of_type_ES;
/*     */ 
/*     */   public fe(u paramu)
/*     */   {
/*  71 */     this.jdField_b_of_type_Float = 0.2F;
/*  72 */     this.jdField_c_of_type_Float = 0.3F;
/*     */ 
/*  75 */     this.jdField_a_of_type_JavaUtilLinkedList = new LinkedList();
/*  76 */     this.jdField_b_of_type_JavaUtilLinkedList = new LinkedList();
/*  77 */     this.jdField_c_of_type_JavaUtilLinkedList = new LinkedList();
/*     */ 
/* 103 */     this.jdField_a_of_type_Ct = paramu.a();
/* 104 */     this.jdField_a_of_type_U = paramu;
/* 105 */     this.jdField_a_of_type_U.addObserver(this);
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/*     */   }
/*     */ 
/*     */   private void e()
/*     */   {
/* 122 */     this.jdField_a_of_type_ES.h(48);
/* 123 */     this.jdField_a_of_type_Fi.h(48);
/* 124 */     this.jdField_a_of_type_YP.h(48);
/* 125 */     this.jdField_a_of_type_Yu.h(9);
/* 126 */     this.jdField_a_of_type_YE.h(6);
/* 127 */     this.jdField_a_of_type_Ii.h(48);
/*     */   }
/*     */ 
/*     */   public final void b() {
/* 131 */     if (xm.b()) {
/* 132 */       e();
/*     */     }
/*     */ 
/* 141 */     GL11.glClear(256);
/* 142 */     if (xu.K.b()) {
/* 143 */       return;
/*     */     }
/* 145 */     if (xu.J.b())
/*     */     {
/* 148 */       if (c()) {
/* 149 */         this.jdField_a_of_type_Fp.b();
/*     */       }
/*     */ 
/* 152 */       if (!this.jdField_a_of_type_Ct.a().a().a().c()) {
/* 153 */         this.jdField_a_of_type_Fg.b();
/*     */       }
/*     */ 
/* 156 */       int i = (!this.jdField_a_of_type_Ct.a().a().a().a().g()) && (!this.jdField_a_of_type_Ct.a().a().a().a().g()) && (!this.jdField_a_of_type_Ct.a().a().a().a().g()) && (!this.jdField_a_of_type_Ct.a().a().a().a().c()) && (!this.jdField_a_of_type_Ct.a().a().a().a().g()) && (!this.jdField_a_of_type_Ct.a().a().a().a().g()) && (!this.jdField_a_of_type_Ct.a().a().a().a().g()) && (!b()) ? 1 : 0;
/*     */ 
/* 159 */       yz.i();
/*     */ 
/* 163 */       if (i != 0) {
/* 164 */         h.a("HUD");
/* 165 */         this.jdField_a_of_type_Ii.b();
/* 166 */         h.b("HUD");
/* 167 */       } else if (b()) {
/* 168 */         this.jdField_a_of_type_Ii.a().e();
/*     */       }
/*     */ 
/* 171 */       if (this.jdField_a_of_type_Ct.d()) {
/* 172 */         this.jdField_a_of_type_YE.b();
/*     */       }
/* 174 */       yz.h();
/*     */     }
/*     */ 
/* 178 */     yz.i();
/* 179 */     synchronized (this.jdField_a_of_type_Ct.b())
/*     */     {
/* 181 */       for (int k = 0; k < this.jdField_a_of_type_Ct.b().size(); k++) {
/* 182 */         ((H)this.jdField_a_of_type_Ct.b().get(k)).a().b();
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 187 */     yz.h = true;
/* 188 */     for (int j = 0; j < this.jdField_a_of_type_Ct.a().size(); j++)
/*     */     {
/*     */       H localH;
/* 191 */       (
/* 192 */         localH = (H)this.jdField_a_of_type_Ct.a().get(j))
/* 192 */         .f();
/* 193 */       localH.a().b();
/* 194 */       if (System.currentTimeMillis() - localH.a() > 200L) {
/* 195 */         this.jdField_a_of_type_Ct.a().remove(j);
/* 196 */         j--;
/*     */       }
/*     */     }
/* 199 */     yz.h = false;
/*     */ 
/* 204 */     if ((!this.jdField_a_of_type_Ct.a().a().a().c()) && (Keyboard.isKeyDown(cv.Q.a()))) {
/* 205 */       this.jdField_a_of_type_Fi.b();
/*     */     }
/*     */ 
/* 208 */     if ((!this.jdField_a_of_type_Ct.a().a().a().c()) && (Keyboard.isKeyDown(61))) {
/* 209 */       this.jdField_a_of_type_ES.b();
/*     */     }
/*     */ 
/* 214 */     this.jdField_a_of_type_Yu.b();
/*     */ 
/* 216 */     yz.h();
/*     */ 
/* 218 */     if (xu.ab.b())
/*     */     {
/* 220 */       if (this.jdField_b_of_type_Fm != null) {
/* 221 */         yz.i();
/* 222 */         this.jdField_b_of_type_Fm.b();
/* 223 */         yz.h();
/*     */       }
/*     */ 
/* 226 */       if (this.jdField_a_of_type_Fm != null) {
/* 227 */         yz.i();
/* 228 */         this.jdField_a_of_type_Fm.a().y = (this.jdField_a_of_type_Fm.a() + 5.0F);
/* 229 */         this.jdField_a_of_type_Fm.b();
/* 230 */         yz.h();
/*     */       }
/*     */ 
/* 234 */       synchronized (this.jdField_a_of_type_JavaUtilLinkedList) {
/* 235 */         for (int m = 0; m < this.jdField_a_of_type_JavaUtilLinkedList.size(); m++) {
/* 236 */           yz.i();
/* 237 */           ((fl)this.jdField_a_of_type_JavaUtilLinkedList.get(m)).b();
/* 238 */           yz.h();
/*     */         }
/*     */       }
/* 241 */       synchronized (this.jdField_b_of_type_JavaUtilLinkedList) {
/* 242 */         for (int n = 0; n < this.jdField_b_of_type_JavaUtilLinkedList.size(); n++) {
/* 243 */           yz.i();
/* 244 */           ((fq)this.jdField_b_of_type_JavaUtilLinkedList.get(n)).b();
/* 245 */           yz.h();
/*     */         }
/*     */       }
/* 248 */       synchronized (this.jdField_c_of_type_JavaUtilLinkedList) {
/* 249 */         for (int i1 = 0; i1 < this.jdField_c_of_type_JavaUtilLinkedList.size(); i1++) {
/* 250 */           yz.i();
/* 251 */           ((eQ)this.jdField_c_of_type_JavaUtilLinkedList.get(i1)).b();
/* 252 */           yz.h();
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 258 */     if (((xu.O.b()) && (this.jdField_a_of_type_Ct.a().a().c()) ? 1 : 0) != 0) {
/* 258 */       if ((this.jdField_a_of_type_Ct.a().a() != null) && (this.jdField_a_of_type_Ct.a().a())) {
/* 259 */         yz.i();
/* 260 */         this.jdField_a_of_type_Iy.b();
/* 261 */         yz.h();
/*     */       }
/*     */     }
/* 264 */     yz.i();
/* 265 */     this.jdField_a_of_type_Ff.a(this.jdField_a_of_type_Ct.a().a().g());
/* 266 */     this.jdField_a_of_type_Ff.b();
/* 267 */     yz.h();
/*     */ 
/* 269 */     ??? = this; if (this.d < ((fe)???).jdField_b_of_type_Float) { ((fe)???).jdField_a_of_type_YR.a().w = (((fe)???).d / ((fe)???).jdField_b_of_type_Float * ((fe)???).jdField_c_of_type_Float); yz.i(); ((fe)???).jdField_a_of_type_YR.b(); yz.h();
/*     */     }
/* 271 */     if (wV.b) {
/* 272 */       yz.i();
/* 273 */       this.jdField_a_of_type_YP.b();
/* 274 */       yz.h();
/*     */     }
/*     */ 
/* 278 */     this.jdField_a_of_type_Fg.e();
/*     */   }
/*     */   private boolean b() {
/* 281 */     return this.jdField_a_of_type_Ct.a().a().a().a().g();
/*     */   }
/*     */ 
/*     */   private void a(int paramInt, U paramU)
/*     */   {
/* 314 */     int i = paramInt;
/* 315 */     if ((this.jdField_a_of_type_Int & paramInt) == paramInt) {
/* 316 */       i = 0;
/*     */     }
/*     */ 
/* 319 */     this.jdField_a_of_type_Int += (paramU.g() ? i : -paramInt);
/*     */   }
/*     */ 
/*     */   public final boolean a()
/*     */   {
/* 342 */     return this.jdField_a_of_type_Fg.a();
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/* 352 */     this.jdField_a_of_type_Fg = new fg(this.jdField_a_of_type_Ct);
/* 353 */     this.jdField_a_of_type_Fg.c();
/*     */ 
/* 356 */     this.jdField_a_of_type_YR = new yR(this.jdField_a_of_type_Ct);
/* 357 */     this.jdField_a_of_type_YR.a().set(1.0F, 0.0F, 0.0F, 0.0F);
/* 358 */     this.jdField_a_of_type_YR.c();
/*     */ 
/* 369 */     this.jdField_a_of_type_Fi = new fi(this.jdField_a_of_type_Ct);
/* 370 */     this.jdField_a_of_type_ES = new eS(this.jdField_a_of_type_Ct);
/*     */ 
/* 372 */     this.jdField_a_of_type_Fi.c();
/* 373 */     this.jdField_a_of_type_ES.c();
/*     */ 
/* 375 */     this.jdField_a_of_type_Ff = new ff(this.jdField_a_of_type_Ct);
/* 376 */     this.jdField_a_of_type_Ff.c();
/*     */ 
/* 379 */     this.jdField_a_of_type_Ii = new ii(this.jdField_a_of_type_Ct);
/*     */ 
/* 381 */     this.jdField_a_of_type_Ii.c();
/*     */ 
/* 383 */     this.jdField_a_of_type_Yu = new yu(this.jdField_a_of_type_Ct);
/* 384 */     this.jdField_a_of_type_Yu.a("use \"/pm playername msg\" for PMs, and \"/f msg\" for faction chat");
/*     */ 
/* 386 */     this.jdField_a_of_type_Yu.a(this.jdField_a_of_type_Ct.getChat());
/* 387 */     this.jdField_a_of_type_Yu.c();
/*     */ 
/* 390 */     this.jdField_a_of_type_YE = new yE(xe.a().a("shopinrange-gui-"), this.jdField_a_of_type_Ct);
/*     */ 
/* 392 */     this.jdField_a_of_type_YE.c();
/*     */ 
/* 396 */     this.jdField_a_of_type_Iy = new iy(this.jdField_a_of_type_Ct);
/* 397 */     this.jdField_a_of_type_Iy.h(10);
/*     */ 
/* 401 */     this.jdField_a_of_type_Fp = new fp(this.jdField_a_of_type_Ct);
/* 402 */     this.jdField_a_of_type_Fp.c();
/*     */ 
/* 406 */     Object localObject = new Font("Arial", 1, 26);
/* 407 */     (
/* 408 */       localObject = new UnicodeFont((Font)localObject))
/* 408 */       .getEffects().add(new OutlineEffect(4, Color.black));
/* 409 */     ((UnicodeFont)localObject).getEffects().add(new ColorEffect(Color.white));
/* 410 */     ((UnicodeFont)localObject).addAsciiGlyphs();
/*     */     try {
/* 412 */       ((UnicodeFont)localObject).loadGlyphs();
/*     */     }
/*     */     catch (SlickException localSlickException) {
/* 415 */       localSlickException.printStackTrace();
/*     */     }
/*     */ 
/* 417 */     this.jdField_a_of_type_YP = new yP(500, 50, (UnicodeFont)localObject, this.jdField_a_of_type_Ct);
/* 418 */     this.jdField_a_of_type_YP.b(new ArrayList());
/* 419 */     this.jdField_a_of_type_YP.b().add("Detached Mouse From Game. Click to reattach");
/*     */ 
/* 421 */     e();
/*     */   }
/*     */ 
/*     */   public final void d() {
/* 425 */     this.jdField_a_of_type_Ii.e();
/*     */   }
/*     */   private boolean c() {
/* 428 */     return this.jdField_a_of_type_Ct.a().a().a().a().a().a().a().g();
/*     */   }
/*     */ 
/*     */   public final void a(Sendable paramSendable)
/*     */   {
/* 444 */     if (this.d < 0.0F) {
/* 445 */       this.d = 0.0F;
/* 446 */       this.jdField_a_of_type_Float = 1.0F;
/* 447 */       if ((paramSendable != null) && ((paramSendable instanceof ld)) && (((ld)paramSendable instanceof ShieldContainerInterface)) && (((ShieldContainerInterface)paramSendable).getShieldManager().getShields() > 0.0D))
/*     */       {
/* 451 */         this.jdField_a_of_type_YR.a().x = 0.0F;
/* 452 */         this.jdField_a_of_type_YR.a().y = 0.0F;
/* 453 */         this.jdField_a_of_type_YR.a().z = 1.0F; return;
/*     */       }
/* 455 */       this.jdField_a_of_type_YR.a().x = 1.0F;
/* 456 */       this.jdField_a_of_type_YR.a().y = 0.0F;
/* 457 */       this.jdField_a_of_type_YR.a().z = 0.0F;
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void update(Observable paramObservable, Object paramObject)
/*     */   {
/* 469 */     if ("ON_SWITCH".equals(paramObject)) {
/* 470 */       if ((paramObservable instanceof ak)) {
/* 471 */         a(8, (U)paramObservable);
/* 472 */         if (this.jdField_a_of_type_Yu != null) {
/* 473 */           this.jdField_a_of_type_Yu.a(((ak)paramObservable).c());
/*     */         }
/*     */       }
/* 476 */       if ((paramObservable instanceof an)) {
/* 477 */         a(2, (U)paramObservable);
/*     */       }
/* 479 */       if ((paramObservable instanceof bl)) {
/* 480 */         a(64, (U)paramObservable);
/*     */       }
/* 482 */       if ((paramObservable instanceof bz)) {
/* 483 */         a(4, (U)paramObservable);
/*     */       }
/* 485 */       if ((paramObservable instanceof bk)) {
/* 486 */         a(16, (U)paramObservable);
/*     */       }
/* 488 */       if ((paramObservable instanceof Y))
/* 489 */         a(32, (U)paramObservable);
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void a(xq paramxq)
/*     */   {
/* 498 */     if (this.d >= 0.0F)
/*     */     {
/* 500 */       this.d += this.jdField_a_of_type_Float * (paramxq.a() * 2.0F);
/* 501 */       if (this.d > this.jdField_b_of_type_Float)
/* 502 */         this.jdField_a_of_type_Float = -1.0F;
/*     */     }
/*     */     else {
/* 505 */       this.jdField_a_of_type_Float = 1.0F;
/*     */     }
/* 507 */     this.jdField_a_of_type_Yu.a(paramxq);
/*     */ 
/* 509 */     if (!c()) {
/* 510 */       if (this.jdField_b_of_type_Fm != null) {
/* 511 */         this.jdField_b_of_type_Fm.e();
/*     */       }
/* 513 */       if (this.jdField_a_of_type_Fm != null) {
/* 514 */         this.jdField_a_of_type_Fm.e();
/*     */       }
/*     */     }
/*     */ 
/* 518 */     if (this.jdField_b_of_type_Fm != null) {
/* 519 */       this.jdField_b_of_type_Fm.a(paramxq);
/*     */     }
/* 521 */     if (this.jdField_a_of_type_Fm != null) {
/* 522 */       this.jdField_a_of_type_Fm.a(paramxq);
/*     */     }
/*     */ 
/* 528 */     if ((!this.jdField_a_of_type_Ct.a().a().a().c()) && (Keyboard.isKeyDown(cv.Q.a()))) {
/* 529 */       this.jdField_a_of_type_Fi.a(paramxq);
/*     */     }
/* 531 */     if ((!this.jdField_a_of_type_Ct.a().a().a().c()) && (Keyboard.isKeyDown(61))) {
/* 532 */       this.jdField_a_of_type_ES.a(paramxq);
/*     */     }
/* 534 */     this.jdField_a_of_type_Fg.a(paramxq);
/*     */ 
/* 536 */     this.jdField_a_of_type_Ii.a(paramxq);
/*     */ 
/* 538 */     eV.a.a(paramxq);
/*     */     int i;
/* 540 */     synchronized (this.jdField_a_of_type_JavaUtilLinkedList) {
/* 541 */       for (i = 0; i < this.jdField_a_of_type_JavaUtilLinkedList.size(); i++) {
/* 542 */         ((fl)this.jdField_a_of_type_JavaUtilLinkedList.get(i)).a(paramxq);
/* 543 */         if (!((fl)this.jdField_a_of_type_JavaUtilLinkedList.get(i)).a()) {
/* 544 */           this.jdField_a_of_type_JavaUtilLinkedList.remove(i);
/* 545 */           i--;
/*     */         }
/*     */         else {
/* 548 */           ((fl)this.jdField_a_of_type_JavaUtilLinkedList.get(i)).jdField_a_of_type_Float = i;
/*     */         }
/*     */       }
/*     */     }
/* 551 */     synchronized (this.jdField_b_of_type_JavaUtilLinkedList) {
/* 552 */       for (i = 0; i < this.jdField_b_of_type_JavaUtilLinkedList.size(); i++) {
/* 553 */         ((fq)this.jdField_b_of_type_JavaUtilLinkedList.get(i)).a(paramxq);
/* 554 */         if (!((fq)this.jdField_b_of_type_JavaUtilLinkedList.get(i)).a()) {
/* 555 */           this.jdField_b_of_type_JavaUtilLinkedList.remove(i);
/* 556 */           i--;
/*     */         }
/*     */         else {
/* 559 */           ((fq)this.jdField_b_of_type_JavaUtilLinkedList.get(i)).jdField_a_of_type_Float = i;
/*     */         }
/*     */       }
/*     */     }
/* 562 */     synchronized (this.jdField_c_of_type_JavaUtilLinkedList) {
/* 563 */       for (i = 0; i < this.jdField_c_of_type_JavaUtilLinkedList.size(); i++) {
/* 564 */         ((eQ)this.jdField_c_of_type_JavaUtilLinkedList.get(i)).a(paramxq);
/* 565 */         if (!((eQ)this.jdField_c_of_type_JavaUtilLinkedList.get(i)).a()) {
/* 566 */           this.jdField_c_of_type_JavaUtilLinkedList.remove(i);
/* 567 */           i--;
/*     */         }
/*     */         else {
/* 570 */           ((eQ)this.jdField_c_of_type_JavaUtilLinkedList.get(i)).jdField_a_of_type_Float = i; } 
/*     */       }return;
/*     */     }
/*     */   }
/*     */ 
/* 575 */   public final void a(ElementCollectionManager paramElementCollectionManager) { this.jdField_a_of_type_Fg.a(paramElementCollectionManager); }
/*     */ 
/*     */ 
/*     */   public final fg a()
/*     */   {
/* 581 */     return this.jdField_a_of_type_Fg;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     fe
 * JD-Core Version:    0.6.2
 */