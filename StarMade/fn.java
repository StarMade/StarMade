/*     */ import javax.vecmath.Vector3f;
/*     */ import org.schema.game.common.controller.elements.PowerAddOn;
/*     */ import org.schema.game.common.controller.elements.ShipManagerContainer;
/*     */ import org.schema.game.common.controller.elements.shield.ShieldCollectionManager;
/*     */ import org.schema.schine.graphicsengine.core.GlUtil;
/*     */ 
/*     */ public final class fn extends yz
/*     */ {
/*     */   private yE jdField_a_of_type_YE;
/*     */   private yE b;
/*     */   private yE c;
/*     */   private yE d;
/*     */   private zF jdField_a_of_type_ZF;
/*  26 */   private float jdField_a_of_type_Float = 32.0F;
/*     */   private boolean jdField_a_of_type_Boolean;
/*     */   private yE e;
/*     */   private yE f;
/*     */ 
/*     */   public fn(ct paramct)
/*     */   {
/*  34 */     super(paramct);
/*     */ 
/*  36 */     this.jdField_a_of_type_ZF = new zF();
/*     */ 
/*  38 */     paramct = "powerbar-1x4-gui-";
/*     */ 
/*  41 */     this.b = new yE(xe.a().a(paramct), a());
/*  42 */     this.b.a_(0);
/*  43 */     this.b.a(66.0F, -11.0F, 0.0F);
/*     */ 
/*  45 */     this.jdField_a_of_type_YE = new yE(xe.a().a(paramct), a());
/*  46 */     this.jdField_a_of_type_YE.a_(2);
/*  47 */     this.jdField_a_of_type_YE.a(66.0F, -11.0F, 0.0F);
/*     */ 
/*  49 */     this.e = new yE(xe.a().a(paramct), a());
/*  50 */     this.e.a_(2);
/*  51 */     this.e.a(66.0F, -11.0F, 0.0F);
/*     */ 
/*  53 */     this.f = new yE(xe.a().a(paramct), a());
/*  54 */     this.f.a_(2);
/*  55 */     this.f.a(66.0F, -11.0F, 0.0F);
/*     */ 
/*  57 */     this.d = new yE(xe.a().a(paramct), a());
/*  58 */     this.d.a_(1);
/*  59 */     this.d.a(549.0F, -11.0F, 0.0F);
/*     */ 
/*  61 */     this.c = new yE(xe.a().a(paramct), a());
/*  62 */     this.c.a_(3);
/*  63 */     this.c.a(549.0F, -11.0F, 0.0F);
/*     */ 
/*  65 */     if (xe.a().a(paramct).a() == null)
/*  66 */       xe.a().a(paramct).c(new javax.vecmath.Vector4f(1.0F, 1.0F, 1.0F, 1.0F));
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void b()
/*     */   {
/*  76 */     GlUtil.d();
/*  77 */     r();
/*     */ 
/*  79 */     Object localObject = (ct)a();
/*  80 */     this.jdField_a_of_type_YE.a().a().w = 0.0F;
/*  81 */     this.c.a().a().w = 0.0F;
/*  82 */     this.jdField_a_of_type_YE.b();
/*     */ 
/*  84 */     this.b.a().a().set(1.0F, 1.0F, 1.0F, 1.0F);
/*     */ 
/*  86 */     float f1 = 1.0F - ((ct)localObject).a().a() / 300.0F;
/*     */ 
/*  91 */     org.lwjgl.util.vector.Vector4f localVector4f = new org.lwjgl.util.vector.Vector4f(this.b.a().x + f1 * 402.0F, this.b.a().x + 402.0F, this.b.a().y, this.b.a().y + this.b.a());
/*     */ 
/*  99 */     this.b.a(localVector4f);
/*     */ 
/* 106 */     if (((ct)localObject).a() != null) {
/* 107 */       kd localkd = ((ct)localObject).a();
/*     */ 
/* 109 */       this.e.a().a().set(1.0F, 1.0F, 1.0F, 1.0F);
/* 110 */       float f3 = (float)(1.0D - localkd.a().getShieldManager().getShields() / localkd.a().getShieldManager().getShieldCapabilityHP());
/* 111 */       localObject = new org.lwjgl.util.vector.Vector4f(this.e.a().x + f3 * 402.0F, this.e.a().x + 402.0F, this.e.a().y, this.e.a().y + this.e.a());
/*     */ 
/* 117 */       this.e.a((org.lwjgl.util.vector.Vector4f)localObject);
/*     */ 
/* 120 */       this.c.a().a().w = 0.0F;
/* 121 */       if (this.jdField_a_of_type_Boolean != localkd.a().getPowerAddOn().isInRecovery()) {
/* 122 */         this.jdField_a_of_type_Boolean = localkd.a().getPowerAddOn().isInRecovery();
/*     */       }
/*     */ 
/* 125 */       if ((this.jdField_a_of_type_Boolean) || (localkd.a().getPowerAddOn().getPower() <= 0.0D)) {
/* 126 */         this.c.a().a().x = 1.0F;
/* 127 */         this.c.a().a().y = 0.0F;
/* 128 */         this.c.a().a().z = 0.0F;
/* 129 */         this.c.a().a().w = (this.jdField_a_of_type_ZF.a() * 0.3F);
/*     */       }
/* 131 */       this.c.b();
/*     */ 
/* 133 */       this.d.a().a().set(1.0F, 1.0F, 1.0F, 1.0F);
/*     */ 
/* 135 */       f3 = (float)localkd.a().getPowerAddOn().getMaxPower(); float f2 = (float)localkd.a().getPowerAddOn().getPower() * 0.785F; localObject = this.d; f2 /= f3; ((yz)localObject).a.set(((yz)localObject).a().x, ((yz)localObject).a().x + f2 * ((yz)localObject).b(), ((yz)localObject).a().y, ((yz)localObject).a().y + 1.0F * ((yz)localObject).a()); ((yz)localObject).a(((yz)localObject).a);
/*     */     }
/*     */ 
/* 140 */     GlUtil.c();
/*     */   }
/*     */ 
/*     */   public final float a() {
/* 144 */     return this.jdField_a_of_type_YE.a();
/*     */   }
/*     */ 
/*     */   public final float b()
/*     */   {
/* 153 */     return this.jdField_a_of_type_YE.b() * 2.0F + this.jdField_a_of_type_Float;
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/* 171 */     this.jdField_a_of_type_YE.c();
/* 172 */     this.b.c();
/* 173 */     this.c.c();
/* 174 */     this.d.c();
/*     */   }
/*     */ 
/*     */   public final void a(xq paramxq)
/*     */   {
/* 179 */     this.jdField_a_of_type_ZF.a(paramxq);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     fn
 * JD-Core Version:    0.6.2
 */