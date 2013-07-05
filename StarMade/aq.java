/*     */ import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ 
/*     */ public final class aq extends U
/*     */ {
/*     */   public ar a;
/*     */   public an a;
/*     */   public bz a;
/*     */   public bm a;
/*     */   public bg a;
/*     */   public aa a;
/*     */   public V a;
/*     */   public aO a;
/*     */   public aC a;
/*     */   private final ObjectArrayList a;
/*     */ 
/*     */   public aq(ct paramct)
/*     */   {
/*  38 */     super(paramct);
/*     */ 
/*  35 */     this.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectArrayList = new ObjectArrayList();
/*     */ 
/*  39 */     paramct = this; this.jdField_a_of_type_Ar = new ar(paramct.a()); paramct.jdField_a_of_type_An = new an(paramct.a()); paramct.jdField_a_of_type_Bz = new bz(paramct.a()); paramct.jdField_a_of_type_Bm = new bm(paramct.a()); paramct.jdField_a_of_type_Bg = new bg(paramct.a()); paramct.jdField_a_of_type_Aa = new aa(paramct.a()); paramct.jdField_a_of_type_V = new V(paramct.a()); paramct.jdField_a_of_type_AO = new aO(paramct.a()); paramct.jdField_a_of_type_AC = new aC(paramct.a()); paramct.jdField_a_of_type_JavaUtilHashSet.add(paramct.jdField_a_of_type_Ar); paramct.jdField_a_of_type_JavaUtilHashSet.add(paramct.jdField_a_of_type_An); paramct.jdField_a_of_type_JavaUtilHashSet.add(paramct.jdField_a_of_type_Bz); paramct.jdField_a_of_type_JavaUtilHashSet.add(paramct.jdField_a_of_type_Bm); paramct.jdField_a_of_type_JavaUtilHashSet.add(paramct.jdField_a_of_type_Bg); paramct.jdField_a_of_type_JavaUtilHashSet.add(paramct.jdField_a_of_type_V); paramct.jdField_a_of_type_JavaUtilHashSet.add(paramct.jdField_a_of_type_Aa); paramct.jdField_a_of_type_JavaUtilHashSet.add(paramct.jdField_a_of_type_AO); paramct.jdField_a_of_type_JavaUtilHashSet.add(paramct.jdField_a_of_type_AC); paramct.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.add(paramct.jdField_a_of_type_An); paramct.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.add(paramct.jdField_a_of_type_Bz); paramct.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.add(paramct.jdField_a_of_type_Bm); paramct.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.add(paramct.jdField_a_of_type_Bg); paramct.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.add(paramct.jdField_a_of_type_V); paramct.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.add(paramct.jdField_a_of_type_Aa); paramct.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.add(paramct.jdField_a_of_type_AO); paramct.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.add(paramct.jdField_a_of_type_AC);
/*     */   }
/*     */ 
/*     */   public final void a(le paramle)
/*     */   {
/*  44 */     if (a().a() != null) {
/*  45 */       if (a().a().a().a() != null) {
/*  46 */         this.jdField_a_of_type_V.a(a().a().a().a());
/*     */       } else {
/*  48 */         this.jdField_a_of_type_V.a(null);
/*     */ 
/*  50 */         a().a().b("No AI context available\nEither use this in a ship\nwith an AI Module or\nactivate an AI Module externally");
/*     */       }
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/*  57 */       this.jdField_a_of_type_V.a(paramle);
/*     */     }
/*     */ 
/*  60 */     if (this.jdField_a_of_type_An.b) {
/*  61 */       this.jdField_a_of_type_An.c(false);
/*     */     }
/*  63 */     if (this.jdField_a_of_type_AO.b) {
/*  64 */       this.jdField_a_of_type_AO.c(false);
/*     */     }
/*  66 */     if (this.jdField_a_of_type_AC.b) {
/*  67 */       this.jdField_a_of_type_AC.c(false);
/*     */     }
/*  69 */     if (this.jdField_a_of_type_Bz.b) {
/*  70 */       this.jdField_a_of_type_Bz.c(false);
/*     */     }
/*  72 */     if (this.jdField_a_of_type_Bm.b) {
/*  73 */       this.jdField_a_of_type_Bm.c(false);
/*     */     }
/*  75 */     if (this.jdField_a_of_type_Bg.b) {
/*  76 */       this.jdField_a_of_type_Bg.c(false);
/*     */     }
/*     */ 
/*  80 */     paramle = !this.jdField_a_of_type_V.b ? 1 : 0;
/*  81 */     this.jdField_a_of_type_V.d(paramle);
/*     */   }
/*     */ 
/*     */   public final V a()
/*     */   {
/*  88 */     return this.jdField_a_of_type_V;
/*     */   }
/*     */ 
/*     */   public final an a()
/*     */   {
/*  95 */     return this.jdField_a_of_type_An;
/*     */   }
/*     */ 
/*     */   public final bg a()
/*     */   {
/* 101 */     return this.jdField_a_of_type_Bg;
/*     */   }
/*     */ 
/*     */   public final ar a()
/*     */   {
/* 107 */     return this.jdField_a_of_type_Ar;
/*     */   }
/*     */ 
/*     */   public final bz a()
/*     */   {
/* 113 */     return this.jdField_a_of_type_Bz;
/*     */   }
/*     */ 
/*     */   public final bm a()
/*     */   {
/* 120 */     return this.jdField_a_of_type_Bm;
/*     */   }
/*     */ 
/*     */   public final void b()
/*     */   {
/* 125 */     for (Iterator localIterator = this.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.iterator(); localIterator.hasNext(); ) ((U)localIterator.next())
/* 126 */         .c(false);
/*     */ 
/* 129 */     this.jdField_a_of_type_Ar.a(600);
/*     */   }
/*     */ 
/*     */   public final void handleKeyEvent()
/*     */   {
/* 134 */     if (Keyboard.getEventKeyState())
/*     */     {
/* 138 */       if (a().b().isEmpty()) {
/* 139 */         if ((this.jdField_a_of_type_An.b) && (Keyboard.getEventKey() == cv.w.a()))
/*     */         {
/* 141 */           a(null);
/*     */         }
/* 143 */         if (Keyboard.getEventKey() == cv.H.a())
/*     */         {
/* 145 */           a(null);
/*     */         }
/* 147 */         else if (Keyboard.getEventKey() == cv.G.a())
/*     */         {
/* 150 */           aq localaq = this; boolean bool2 = !this.jdField_a_of_type_Bz.b; if (!localaq.a().d()) { localaq.a().a().d("ERROR: You are not near any shop"); } else { if (localaq.jdField_a_of_type_An.b) localaq.jdField_a_of_type_An.c(false); if (localaq.jdField_a_of_type_Aa.b) localaq.jdField_a_of_type_Aa.c(false); if (localaq.jdField_a_of_type_AO.b) localaq.jdField_a_of_type_AO.c(false); if (localaq.jdField_a_of_type_Bm.b) localaq.jdField_a_of_type_Bm.c(false); if (localaq.jdField_a_of_type_Bg.b) localaq.jdField_a_of_type_Bg.c(false); if (localaq.jdField_a_of_type_AC.b) localaq.jdField_a_of_type_AC.c(false); if (localaq.jdField_a_of_type_V.b) localaq.jdField_a_of_type_V.c(false); localaq.jdField_a_of_type_Bz.d(bool2); }
/* 151 */         } else if (Keyboard.getEventKey() == cv.I.a())
/*     */         {
/* 153 */           if (!this.jdField_a_of_type_Ar.a().b)
/*     */           {
/* 155 */             a().a().b("ERROR: Weapon Menu only available\ninside ship");
/*     */ 
/* 160 */             return;
/*     */           }
/* 162 */           if (this.jdField_a_of_type_AO.b) {
/* 163 */             this.jdField_a_of_type_AO.c(false);
/*     */           }
/* 165 */           if (this.jdField_a_of_type_AC.b) {
/* 166 */             this.jdField_a_of_type_AC.c(false);
/*     */           }
/* 168 */           if (this.jdField_a_of_type_An.b) {
/* 169 */             this.jdField_a_of_type_An.c(false);
/*     */           }
/* 171 */           if (this.jdField_a_of_type_Bz.b) {
/* 172 */             this.jdField_a_of_type_Bz.c(false);
/*     */           }
/* 174 */           if (this.jdField_a_of_type_Aa.b) {
/* 175 */             this.jdField_a_of_type_Aa.c(false);
/*     */           }
/* 177 */           if (this.jdField_a_of_type_Bg.b) {
/* 178 */             this.jdField_a_of_type_Bg.c(false);
/*     */           }
/* 180 */           if (this.jdField_a_of_type_V.b) {
/* 181 */             this.jdField_a_of_type_V.c(false);
/*     */           }
/* 183 */           bool1 = !this.jdField_a_of_type_Bm.b;
/* 184 */           this.jdField_a_of_type_Bm.d(bool1);
/*     */         }
/* 186 */         else if (Keyboard.getEventKey() == cv.J.a())
/*     */         {
/* 191 */           if (this.jdField_a_of_type_An.b) {
/* 192 */             this.jdField_a_of_type_An.c(false);
/*     */           }
/* 194 */           if (this.jdField_a_of_type_Bz.b) {
/* 195 */             this.jdField_a_of_type_Bz.c(false);
/*     */           }
/* 197 */           if (this.jdField_a_of_type_AO.b) {
/* 198 */             this.jdField_a_of_type_AO.c(false);
/*     */           }
/* 200 */           if (this.jdField_a_of_type_AC.b) {
/* 201 */             this.jdField_a_of_type_AC.c(false);
/*     */           }
/* 203 */           if (this.jdField_a_of_type_Bm.b) {
/* 204 */             this.jdField_a_of_type_Bm.c(false);
/*     */           }
/* 206 */           if (this.jdField_a_of_type_Aa.b) {
/* 207 */             this.jdField_a_of_type_Aa.c(false);
/*     */           }
/* 209 */           if (this.jdField_a_of_type_V.b) {
/* 210 */             this.jdField_a_of_type_V.c(false);
/*     */           }
/* 212 */           bool1 = !this.jdField_a_of_type_Bg.b;
/* 213 */           this.jdField_a_of_type_Bg.d(bool1);
/*     */         }
/* 215 */         else if (Keyboard.getEventKey() == cv.Z.a())
/*     */         {
/* 219 */           if (this.jdField_a_of_type_An.b) {
/* 220 */             this.jdField_a_of_type_An.c(false);
/*     */           }
/* 222 */           if (this.jdField_a_of_type_Bz.b) {
/* 223 */             this.jdField_a_of_type_Bz.c(false);
/*     */           }
/* 225 */           if (this.jdField_a_of_type_AO.b) {
/* 226 */             this.jdField_a_of_type_AO.c(false);
/*     */           }
/* 228 */           if (this.jdField_a_of_type_AC.b) {
/* 229 */             this.jdField_a_of_type_AC.c(false);
/*     */           }
/* 231 */           if (this.jdField_a_of_type_Bm.b) {
/* 232 */             this.jdField_a_of_type_Bm.c(false);
/*     */           }
/* 234 */           if (this.jdField_a_of_type_Bg.b) {
/* 235 */             this.jdField_a_of_type_Bg.c(false);
/*     */           }
/* 237 */           if (this.jdField_a_of_type_V.b) {
/* 238 */             this.jdField_a_of_type_V.c(false);
/*     */           }
/*     */ 
/* 241 */           bool1 = !this.jdField_a_of_type_Aa.b;
/* 242 */           System.err.println("ACTIVATE MAP " + bool1);
/* 243 */           this.jdField_a_of_type_Aa.d(bool1);
/*     */         }
/* 246 */         else if (Keyboard.getEventKey() == cv.K.a())
/*     */         {
/* 249 */           a(null);
/* 250 */         } else if (Keyboard.getEventKey() == cv.Y.a()) {
/* 251 */           if (this.jdField_a_of_type_An.b) {
/* 252 */             this.jdField_a_of_type_An.c(false);
/*     */           }
/* 254 */           if (this.jdField_a_of_type_Bz.b) {
/* 255 */             this.jdField_a_of_type_Bz.c(false);
/*     */           }
/* 257 */           if (this.jdField_a_of_type_Bg.b) {
/* 258 */             this.jdField_a_of_type_Bg.c(false);
/*     */           }
/* 260 */           if (this.jdField_a_of_type_AC.b) {
/* 261 */             this.jdField_a_of_type_AC.c(false);
/*     */           }
/* 263 */           if (this.jdField_a_of_type_Aa.b) {
/* 264 */             this.jdField_a_of_type_Aa.c(false);
/*     */           }
/* 266 */           if (this.jdField_a_of_type_Bm.b) {
/* 267 */             this.jdField_a_of_type_Bm.c(false);
/*     */           }
/* 269 */           if (this.jdField_a_of_type_V.b) {
/* 270 */             this.jdField_a_of_type_V.c(false);
/*     */           }
/* 272 */           bool1 = !this.jdField_a_of_type_AO.b;
/* 273 */           this.jdField_a_of_type_AO.d(bool1);
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 278 */     boolean bool1 = false;
/* 279 */     for (U localU : this.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectArrayList) {
/* 280 */       bool1 = (bool1) || (localU.b);
/*     */     }
/* 282 */     if (this.jdField_a_of_type_Ar.jdField_a_of_type_Boolean != bool1)
/*     */     {
/* 284 */       this.jdField_a_of_type_Ar.e(bool1);
/*     */     }
/* 286 */     if (a().b().isEmpty())
/* 287 */       super.handleKeyEvent();
/*     */   }
/*     */ 
/*     */   public final void a(mf parammf)
/*     */   {
/* 324 */     if (this.jdField_a_of_type_Bz.b) {
/* 325 */       this.jdField_a_of_type_Bz.c(false);
/*     */     }
/* 327 */     if (this.jdField_a_of_type_Bm.b) {
/* 328 */       this.jdField_a_of_type_Bm.c(false);
/*     */     }
/* 330 */     if (this.jdField_a_of_type_V.b) {
/* 331 */       this.jdField_a_of_type_V.c(false);
/*     */     }
/* 333 */     if (this.jdField_a_of_type_Bg.b) {
/* 334 */       this.jdField_a_of_type_Bg.c(false);
/*     */     }
/* 336 */     if (this.jdField_a_of_type_AO.b) {
/* 337 */       this.jdField_a_of_type_AO.c(false);
/*     */     }
/* 339 */     if (this.jdField_a_of_type_Aa.b) {
/* 340 */       this.jdField_a_of_type_Aa.c(false);
/*     */     }
/* 342 */     if (this.jdField_a_of_type_AC.b)
/* 343 */       this.jdField_a_of_type_AC.c(false);
/*     */     boolean bool;
/* 346 */     if ((
/* 346 */       bool = !this.jdField_a_of_type_An.b ? 1 : 0) != 0)
/*     */     {
/* 347 */       mf localmf = parammf; parammf = this.jdField_a_of_type_An; System.err.println("SECOND INVENTORY SET TO " + localmf); parammf.a = localmf;
/*     */     }
/*     */ 
/* 350 */     this.jdField_a_of_type_An.d(bool);
/*     */   }
/*     */ 
/*     */   public final void b(boolean paramBoolean)
/*     */   {
/* 356 */     if (paramBoolean) {
/* 357 */       this.jdField_a_of_type_Bg.c(false);
/* 358 */       this.jdField_a_of_type_Bz.c(false);
/* 359 */       this.jdField_a_of_type_Bm.c(false);
/* 360 */       this.jdField_a_of_type_An.c(false);
/* 361 */       this.jdField_a_of_type_Aa.c(false);
/* 362 */       this.jdField_a_of_type_Ar.d(true);
/*     */     }
/*     */     else {
/* 365 */       this.jdField_a_of_type_An.c(false);
/* 366 */       this.jdField_a_of_type_Bg.c(false);
/* 367 */       this.jdField_a_of_type_Bz.c(false);
/* 368 */       this.jdField_a_of_type_Bm.c(false);
/* 369 */       this.jdField_a_of_type_Aa.c(false);
/*     */     }
/* 371 */     super.b(paramBoolean);
/*     */   }
/*     */ 
/*     */   public final void a(xq paramxq)
/*     */   {
/* 415 */     boolean bool = (this.jdField_a_of_type_An.b) || (this.jdField_a_of_type_Bz.b) || (this.jdField_a_of_type_Bm.b) || (this.jdField_a_of_type_AO.b) || (this.jdField_a_of_type_V.b) || (this.jdField_a_of_type_AC.b);
/*     */ 
/* 421 */     if (this.jdField_a_of_type_Ar.jdField_a_of_type_Boolean != bool)
/*     */     {
/* 423 */       this.jdField_a_of_type_Ar.e(bool);
/*     */     }
/*     */ 
/* 426 */     super.a(paramxq);
/*     */   }
/*     */ 
/*     */   public final aO a()
/*     */   {
/* 431 */     return this.jdField_a_of_type_AO;
/*     */   }
/*     */ 
/*     */   public final aC a()
/*     */   {
/* 439 */     return this.jdField_a_of_type_AC;
/*     */   }
/*     */ 
/*     */   public final aa a()
/*     */   {
/* 447 */     return this.jdField_a_of_type_Aa;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     aq
 * JD-Core Version:    0.6.2
 */