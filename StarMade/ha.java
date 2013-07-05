/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import javax.vecmath.Vector4f;
/*     */ import org.schema.game.common.data.player.faction.FactionNotFoundException;
/*     */ 
/*     */ public final class ha extends yz
/*     */   implements ys
/*     */ {
/*     */   private yx jdField_a_of_type_Yx;
/*     */   private lP jdField_a_of_type_LP;
/*     */   private mc jdField_a_of_type_Mc;
/*     */   private boolean i;
/*     */   boolean jdField_a_of_type_Boolean;
/*     */   boolean jdField_b_of_type_Boolean;
/*     */   boolean jdField_c_of_type_Boolean;
/*     */   boolean jdField_d_of_type_Boolean;
/*     */   private yr jdField_a_of_type_Yr;
/*     */   private yN jdField_a_of_type_YN;
/*     */   private yP jdField_a_of_type_YP;
/*     */   private yP jdField_b_of_type_YP;
/*     */   private yP jdField_c_of_type_YP;
/*     */   private yP jdField_d_of_type_YP;
/*     */   private yP e;
/*     */   private yw jdField_a_of_type_Yw;
/*     */   private yw jdField_b_of_type_Yw;
/*     */   private yw jdField_c_of_type_Yw;
/*     */   private yw jdField_d_of_type_Yw;
/*     */   private String jdField_b_of_type_JavaLangString;
/*     */   int jdField_a_of_type_Int;
/*     */ 
/*     */   public ha(ct paramct, lP paramlP, int paramInt)
/*     */   {
/*  58 */     super(paramct);
/*  59 */     this.jdField_a_of_type_LP = paramlP;
/*  60 */     this.jdField_a_of_type_Mc = paramlP.a();
/*  61 */     this.jdField_a_of_type_Int = paramInt;
/*  62 */     this.jdField_d_of_type_Boolean = this.jdField_a_of_type_Mc.a(paramInt);
/*  63 */     this.jdField_a_of_type_Boolean = this.jdField_a_of_type_Mc.b(paramInt);
/*  64 */     this.jdField_b_of_type_Boolean = this.jdField_a_of_type_Mc.c(paramInt);
/*  65 */     this.jdField_c_of_type_Boolean = this.jdField_a_of_type_Mc.d(paramInt);
/*     */ 
/*  67 */     this.jdField_b_of_type_JavaLangString = this.jdField_a_of_type_Mc.a()[paramInt];
/*  68 */     this.jdField_a_of_type_Yx = new yx(a(), 410.0F, 90.0F, paramInt % 2 == 0 ? new Vector4f(0.1F, 0.1F, 0.1F, 1.0F) : new Vector4f(0.2F, 0.2F, 0.2F, 1.0F));
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/*     */     try
/*     */     {
/* 117 */       Object localObject = this; int j = ((ct)a()).a().a().a();
/*     */       lP locallP;
/* 117 */       if ((locallP = ((ct)((ha)localObject).a()).a().a().a(j)) == null) throw new FactionNotFoundException(Integer.valueOf(j)); if ((localObject = (lX)locallP.a().get(((ct)((ha)localObject).a()).a().getName())) == null) throw new FactionNotFoundException(Integer.valueOf(j));
/*     */ 
/* 119 */       if ((
/* 119 */         localObject = localObject)
/* 119 */         .d(this.jdField_a_of_type_LP))
/*     */       {
/* 121 */         this.jdField_a_of_type_Yr = new yr(a(), 400.0F, 100.0F);
/*     */ 
/* 123 */         this.jdField_c_of_type_YP = new yP(100, 20, a());
/* 124 */         this.jdField_d_of_type_YP = new yP(100, 20, a());
/* 125 */         this.e = new yP(100, 20, a());
/*     */ 
/* 127 */         this.jdField_b_of_type_YP = new yP(100, 20, a());
/* 128 */         this.jdField_a_of_type_YP = new yP(100, 20, a());
/*     */ 
/* 130 */         this.jdField_b_of_type_YP.a("Edit");
/* 131 */         this.jdField_c_of_type_YP.a("Kick");
/* 132 */         this.jdField_d_of_type_YP.a("Invite");
/* 133 */         this.e.a("Permission-Edit");
/* 134 */         this.jdField_a_of_type_YP.a("Permissions");
/*     */ 
/* 136 */         this.jdField_a_of_type_Yw = new hb(this, a());
/*     */ 
/* 164 */         this.jdField_b_of_type_Yw = new hc(this, a());
/*     */ 
/* 192 */         this.jdField_c_of_type_Yw = new hd(this, a());
/*     */ 
/* 220 */         this.jdField_d_of_type_Yw = new he(this, a());
/*     */ 
/* 247 */         this.jdField_a_of_type_Yr.a(this.jdField_a_of_type_YP);
/* 248 */         this.jdField_b_of_type_YP.a(0.0F, 20.0F, 0.0F);
/*     */ 
/* 252 */         this.jdField_c_of_type_YP.a(70.0F, 20.0F, 0.0F);
/* 253 */         this.jdField_d_of_type_YP.a(140.0F, 20.0F, 0.0F);
/* 254 */         this.e.a(210.0F, 20.0F, 0.0F);
/*     */ 
/* 256 */         this.jdField_a_of_type_Yr.a(this.jdField_b_of_type_YP);
/* 257 */         this.jdField_a_of_type_Yr.a(this.jdField_c_of_type_YP);
/* 258 */         this.jdField_a_of_type_Yr.a(this.jdField_d_of_type_YP);
/* 259 */         this.jdField_a_of_type_Yr.a(this.e);
/*     */ 
/* 261 */         this.jdField_a_of_type_Yw.a(0.0F, 35.0F, 0.0F);
/* 262 */         this.jdField_b_of_type_Yw.a(70.0F, 35.0F, 0.0F);
/* 263 */         this.jdField_c_of_type_Yw.a(140.0F, 35.0F, 0.0F);
/* 264 */         this.jdField_d_of_type_Yw.a(210.0F, 35.0F, 0.0F);
/*     */ 
/* 266 */         this.jdField_a_of_type_Yr.a(this.jdField_a_of_type_Yw);
/* 267 */         this.jdField_a_of_type_Yr.a(this.jdField_b_of_type_Yw);
/* 268 */         this.jdField_a_of_type_Yr.a(this.jdField_c_of_type_Yw);
/* 269 */         this.jdField_a_of_type_Yr.a(this.jdField_d_of_type_Yw);
/*     */ 
/* 271 */         this.jdField_a_of_type_Yr.a().y = 20.0F;
/*     */ 
/* 273 */         this.jdField_a_of_type_Yx.a(this.jdField_a_of_type_Yr);
/*     */ 
/* 275 */         this.jdField_a_of_type_YN = new yN(a(), 200, 20, new hf(this), new hg(this));
/*     */ 
/* 380 */         this.jdField_a_of_type_Yx.a(this.jdField_a_of_type_YN);
/*     */       }
/*     */ 
/* 383 */       if (!((lX)localObject).d(this.jdField_a_of_type_LP)) {
/* 384 */         (
/* 385 */           localObject = new yP(100, 20, a()))
/* 385 */           .a("You don't have permission to edit this member!");
/* 386 */         this.jdField_a_of_type_Yx.a((yz)localObject);
/*     */       }
/*     */     }
/*     */     catch (FactionNotFoundException localFactionNotFoundException)
/*     */     {
/*     */       yP localyP;
/* 390 */       (
/* 391 */         localyP = new yP(100, 20, a()))
/* 391 */         .a("You don't have permission to edit this!");
/* 392 */       this.jdField_a_of_type_Yx.a(localyP);
/* 393 */       localFactionNotFoundException.printStackTrace();
/*     */     }
/* 395 */     a(this.jdField_a_of_type_Yx);
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void b()
/*     */   {
/* 423 */     k();
/*     */   }
/*     */ 
/*     */   public final float a()
/*     */   {
/* 428 */     return this.jdField_a_of_type_Yx.a();
/*     */   }
/*     */ 
/*     */   public final float b()
/*     */   {
/* 433 */     return this.jdField_a_of_type_Yx.b();
/*     */   }
/*     */ 
/*     */   public final void a(yz paramyz, xp paramxp)
/*     */   {
/* 439 */     System.err.println("TODO CALLBACK");
/*     */   }
/*     */ 
/*     */   public final boolean a()
/*     */   {
/* 444 */     return false;
/*     */   }
/*     */ 
/*     */   public final boolean b()
/*     */   {
/* 451 */     return this.jdField_a_of_type_Boolean;
/*     */   }
/*     */ 
/*     */   public final boolean c()
/*     */   {
/* 466 */     return this.jdField_b_of_type_Boolean;
/*     */   }
/*     */ 
/*     */   public final boolean d()
/*     */   {
/* 482 */     return this.jdField_c_of_type_Boolean;
/*     */   }
/*     */ 
/*     */   public final boolean e()
/*     */   {
/* 498 */     return this.jdField_d_of_type_Boolean;
/*     */   }
/*     */ 
/*     */   public final String a()
/*     */   {
/* 513 */     return this.jdField_b_of_type_JavaLangString;
/*     */   }
/*     */ 
/*     */   public final boolean f()
/*     */   {
/* 528 */     return this.i;
/*     */   }
/*     */ 
/*     */   public final void a(boolean paramBoolean)
/*     */   {
/* 536 */     this.i = paramBoolean;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ha
 * JD-Core Version:    0.6.2
 */