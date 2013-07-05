/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Observable;
/*     */ import java.util.Observer;
/*     */ import javax.vecmath.Vector3f;
/*     */ import javax.vecmath.Vector4f;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import org.schema.schine.graphicsengine.core.GlUtil;
/*     */ import org.schema.schine.network.client.ClientState;
/*     */ 
/*     */ public class ig extends yz
/*     */   implements Observer, ys
/*     */ {
/*     */   private eW jdField_a_of_type_EW;
/*     */   private eW jdField_b_of_type_EW;
/*     */   private yE jdField_a_of_type_YE;
/*     */   private ys jdField_b_of_type_Ys;
/*     */   private yG jdField_a_of_type_YG;
/*     */   private yG jdField_b_of_type_YG;
/*     */   private yA jdField_a_of_type_YA;
/*  45 */   private int jdField_a_of_type_Int = 0;
/*     */ 
/*  48 */   private boolean jdField_a_of_type_Boolean = true;
/*     */   private yz jdField_a_of_type_Yz;
/*     */   private yA jdField_b_of_type_YA;
/*     */   private yr jdField_a_of_type_Yr;
/*     */   private yr jdField_b_of_type_Yr;
/*     */   private boolean jdField_b_of_type_Boolean;
/*     */ 
/*     */   public ig(ClientState paramClientState, ys paramys)
/*     */   {
/*  58 */     super(paramClientState);
/*  59 */     this.jdField_b_of_type_Ys = paramys;
/*     */   }
/*     */ 
/*     */   public final void a(yz paramyz, xp paramxp)
/*     */   {
/*  65 */     if ((paramxp.jdField_a_of_type_Boolean) && (paramxp.jdField_a_of_type_Int == 0))
/*  66 */       if ((paramyz.a.equals("GENERAL")) || (paramyz.a.equals("X"))) {
/*  67 */         if (this.jdField_a_of_type_Int != 0) {
/*  68 */           this.jdField_a_of_type_Int = 0;
/*  69 */           this.jdField_b_of_type_Boolean = true;
/*     */         }
/*  71 */       } else if (paramyz.a.equals("KEYBOARD")) {
/*  72 */         if (this.jdField_a_of_type_Int != 1) {
/*  73 */           this.jdField_a_of_type_Int = 1;
/*  74 */           this.jdField_b_of_type_Boolean = true;
/*     */         }
/*     */ 
/*     */       }
/*  78 */       else if (!c) throw new AssertionError("not known command: " + paramyz.a);
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/* 105 */     this.jdField_a_of_type_YE.a();
/*     */   }
/*     */ 
/*     */   protected final void d()
/*     */   {
/* 155 */     this.jdField_a_of_type_YE.h(48);
/*     */   }
/*     */ 
/*     */   public final void b()
/*     */   {
/* 160 */     if (this.jdField_a_of_type_Boolean) {
/* 161 */       c();
/*     */     }
/* 163 */     ig localig = this; if (this.jdField_b_of_type_Boolean) { if (localig.jdField_a_of_type_Int == 0) { localig.jdField_a_of_type_YE.a(xe.a().a("optionsPanel-gui-")); localig.jdField_a_of_type_YE.b(localig.jdField_b_of_type_YG); if (!localig.a.contains(localig.jdField_a_of_type_YG)) localig.jdField_a_of_type_YE.a(localig.jdField_a_of_type_YG);  } else if (localig.jdField_a_of_type_Int == 1) { localig.jdField_a_of_type_YE.a(xe.a().a("optionsKeyboard-gui-")); localig.jdField_a_of_type_YE.b(localig.jdField_a_of_type_YG); if (!localig.a.contains(localig.jdField_b_of_type_YG)) localig.jdField_a_of_type_YE.a(localig.jdField_b_of_type_YG);  } localig.jdField_b_of_type_Boolean = false;
/*     */     }
/* 165 */     if (k()) {
/* 166 */       d();
/*     */     }
/* 168 */     GlUtil.d();
/* 169 */     GL11.glEnable(3042);
/* 170 */     GL11.glBlendFunc(770, 771);
/* 171 */     r();
/*     */ 
/* 173 */     this.jdField_a_of_type_YE.b();
/* 174 */     GL11.glDisable(3042);
/* 175 */     GlUtil.c();
/*     */   }
/*     */ 
/*     */   public final float a()
/*     */   {
/* 180 */     return 256.0F;
/*     */   }
/*     */ 
/*     */   public final float b()
/*     */   {
/* 185 */     return 256.0F;
/*     */   }
/*     */ 
/*     */   public final boolean a()
/*     */   {
/* 191 */     return false;
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/* 201 */     this.jdField_a_of_type_YE = new yE(xe.a().a("optionsPanel-gui-"), a());
/*     */ 
/* 204 */     this.jdField_a_of_type_EW = new eW(xe.a().a("buttons-8x8-gui-"), a(), cU.c, "OK", this.jdField_b_of_type_Ys);
/*     */ 
/* 208 */     this.jdField_a_of_type_EW.b(0.5F, 0.5F, 0.5F);
/*     */ 
/* 210 */     this.jdField_b_of_type_EW = new eW(xe.a().a("buttons-8x8-gui-"), a(), cU.d, "CANCEL", this.jdField_b_of_type_Ys);
/*     */ 
/* 214 */     this.jdField_b_of_type_EW.b(0.5F, 0.5F, 0.5F);
/*     */ 
/* 216 */     this.jdField_a_of_type_EW.a().x = 600.0F;
/* 217 */     this.jdField_a_of_type_EW.a().y = 410.0F;
/* 218 */     this.jdField_b_of_type_EW.a().y = 410.0F;
/* 219 */     this.jdField_b_of_type_EW.a().x = 686.0F;
/*     */ 
/* 222 */     this.jdField_a_of_type_Yz = new yr(a(), 39.0F, 26.0F);
/* 223 */     this.jdField_a_of_type_Yz.a = "X";
/* 224 */     this.jdField_a_of_type_Yz.g = true;
/* 225 */     this.jdField_a_of_type_Yz.a(this.jdField_b_of_type_Ys);
/* 226 */     this.jdField_a_of_type_Yz.a().set(804.0F, 4.0F, 0.0F);
/* 227 */     this.jdField_a_of_type_Yz.c();
/*     */ 
/* 229 */     this.jdField_a_of_type_Yr = new yr(a(), 147.0F, 40.0F);
/* 230 */     this.jdField_a_of_type_Yr.a = "GENERAL";
/* 231 */     this.jdField_a_of_type_Yr.g = true;
/* 232 */     this.jdField_a_of_type_Yr.a(this);
/* 233 */     this.jdField_a_of_type_Yr.a().set(216.0F, 26.0F, 0.0F);
/* 234 */     this.jdField_a_of_type_Yr.c();
/*     */ 
/* 236 */     this.jdField_b_of_type_Yr = new yr(a(), 147.0F, 40.0F);
/* 237 */     this.jdField_b_of_type_Yr.a = "KEYBOARD";
/* 238 */     this.jdField_b_of_type_Yr.g = true;
/* 239 */     this.jdField_b_of_type_Yr.a(this);
/* 240 */     this.jdField_b_of_type_Yr.a().set(366.0F, 26.0F, 0.0F);
/* 241 */     this.jdField_b_of_type_Yr.c();
/*     */ 
/* 246 */     this.jdField_a_of_type_YG = new yG(516.0F, 295.0F, a());
/* 247 */     this.jdField_a_of_type_YG.a().set(254.0F, 110.0F, 0.0F);
/*     */ 
/* 249 */     this.jdField_b_of_type_YG = new yG(516.0F, 295.0F, a());
/* 250 */     this.jdField_b_of_type_YG.a().set(254.0F, 110.0F, 0.0F);
/*     */ 
/* 252 */     this.jdField_a_of_type_YA = new yA(a());
/*     */ 
/* 254 */     int i = 0;
/*     */     Object localObject1;
/* 255 */     int j = (localObject1 = xu.values()).length;
/*     */     Object localObject2;
/*     */     Object localObject3;
/* 255 */     for (int k = 0; k < j; k++) {
/* 256 */       if (!(
/* 256 */         localObject2 = localObject1[k])
/* 256 */         .a()) {
/* 257 */         if ((((xu)localObject2).a() instanceof Boolean)) {
/* 258 */           localObject3 = new yB(a(), (xu)localObject2);
/* 259 */           this.jdField_a_of_type_YA.a(new yM(a(), ((xu)localObject2).a(), (yL)localObject3, i % 2 == 0));
/*     */         } else {
/* 261 */           this.jdField_a_of_type_YA.a(new yM(a(), ((xu)localObject2).a(), new yI(a(), (xu)localObject2), i % 2 == 0));
/*     */         }
/* 263 */         i++;
/*     */       }
/*     */     }
/*     */ 
/* 267 */     this.jdField_b_of_type_YA = new yA(a());
/*     */ 
/* 269 */     this.jdField_b_of_type_YG.c(this.jdField_b_of_type_YA);
/*     */ 
/* 271 */     ig localig = this; for (localObject2 : cu.values()) { (localObject3 = new yP(176, 30, d.g(), localig.a())).b = new ArrayList(); ((yP)localObject3).b.add("+ " + ((cu)localObject2).a()); ((yP)localObject3).a().y += 8.0F; yx localyx = new yx(localig.a(), 468.0F, 30.0F, new Vector4f(0.0F, 0.0F, 0.0F, 0.8F));
/*     */       yP localyP;
/* 271 */       (localyP = new yP(176, 30, d.g(), localig.a())).b = new ArrayList(); localyP.b.add(((cu)localObject2).a()); localyP.g = true; localyP.a().y += 8.0F; localyx.a(localyP); (localObject3 = new yC(localig.a(), (yz)localObject3, localyx)).a().x = (((cu)localObject2).a() * 5); ((yC)localObject3).addObserver(localig); ((yz)localObject3).a = "CATEGORY"; ((yC)localObject3).c(); ((yz)localObject3).g = true; ((yC)localObject3).a(true); localObject2 = new yD((yz)localObject3, (yz)localObject3, localig.a()); ((yC)localObject3).b(localig); localig.jdField_b_of_type_YA.a((yD)localObject2); } for (localObject2 : cv.values()) (localObject3 = (yC)localig.jdField_b_of_type_YA.a(((cv)localObject2).a().ordinal()).a()).a().a(new if(localig.a(), ((cv)localObject2).a(), new id(localig.a(), (cv)localObject2), ((yC)localObject3).a().size() % 2 == 0));
/*     */ 
/* 273 */     this.jdField_a_of_type_YG.c(this.jdField_a_of_type_YA);
/*     */ 
/* 278 */     this.jdField_a_of_type_YE.c();
/*     */ 
/* 281 */     a(this.jdField_a_of_type_YE);
/*     */ 
/* 285 */     this.jdField_a_of_type_YE.a(this.jdField_a_of_type_EW);
/* 286 */     this.jdField_a_of_type_YE.a(this.jdField_b_of_type_EW);
/* 287 */     this.jdField_a_of_type_YE.a(this.jdField_a_of_type_YG);
/* 288 */     this.jdField_a_of_type_YE.a(this.jdField_a_of_type_Yz);
/* 289 */     this.jdField_a_of_type_YE.a(this.jdField_a_of_type_Yr);
/* 290 */     this.jdField_a_of_type_YE.a(this.jdField_b_of_type_Yr);
/*     */ 
/* 292 */     d();
/*     */ 
/* 294 */     this.jdField_a_of_type_Boolean = false;
/*     */ 
/* 296 */     this.g = true;
/*     */   }
/*     */ 
/*     */   public void update(Observable paramObservable, Object paramObject)
/*     */   {
/* 301 */     this.jdField_b_of_type_YA.f();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ig
 * JD-Core Version:    0.6.2
 */