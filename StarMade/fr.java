/*     */ import javax.vecmath.Vector3f;
/*     */ import org.schema.game.common.data.world.Segment;
/*     */ import org.schema.schine.graphicsengine.core.GlUtil;
/*     */ import org.schema.schine.network.client.ClientState;
/*     */ 
/*     */ public final class fr extends yz
/*     */ {
/*     */   private yE jdField_a_of_type_YE;
/*     */   private yG jdField_a_of_type_YG;
/*  26 */   private boolean jdField_a_of_type_Boolean = true;
/*     */   private yA jdField_a_of_type_YA;
/*     */ 
/*     */   public fr(ClientState paramClientState)
/*     */   {
/*  30 */     super(paramClientState);
/*  31 */     this.jdField_a_of_type_YE = new yE(xe.a().a("ai-panel-gui-"), a());
/*     */   }
/*     */ 
/*     */   public final void a(yz paramyz)
/*     */   {
/*  37 */     this.jdField_a_of_type_YE.a(paramyz);
/*     */   }
/*     */ 
/*     */   public final void a() {
/*  41 */     this.jdField_a_of_type_YE.a();
/*     */   }
/*     */ 
/*     */   public final void b(yz paramyz) {
/*  45 */     this.jdField_a_of_type_YE.b(paramyz);
/*     */   }
/*     */ 
/*     */   public final void b() {
/*  49 */     if (this.jdField_a_of_type_Boolean) {
/*  50 */       c();
/*     */     }
/*     */ 
/*  55 */     GlUtil.d();
/*  56 */     r();
/*  57 */     this.jdField_a_of_type_YE.b();
/*  58 */     GlUtil.c();
/*     */   }
/*     */ 
/*     */   private V a() {
/*  62 */     return ((ct)a()).a().a.a.a;
/*     */   }
/*     */ 
/*     */   public final float a()
/*     */   {
/*  68 */     return this.jdField_a_of_type_YE.a();
/*     */   }
/*     */ 
/*     */   public final float b() {
/*  72 */     return this.jdField_a_of_type_YE.b();
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/*  86 */     this.jdField_a_of_type_YG = new yG(533.0F, 316.0F, a());
/*  87 */     this.jdField_a_of_type_YG.a().set(251.0F, 107.0F, 0.0F);
/*     */ 
/*  89 */     this.jdField_a_of_type_YA = new yA(a());
/*     */ 
/*  91 */     this.jdField_a_of_type_YA.a(a());
/*     */ 
/*  93 */     this.jdField_a_of_type_YG.c(this.jdField_a_of_type_YA);
/*     */ 
/*  96 */     this.jdField_a_of_type_YE.c();
/*     */ 
/*  99 */     super.a(this.jdField_a_of_type_YE);
/*     */ 
/* 101 */     this.jdField_a_of_type_YE.a(this.jdField_a_of_type_YG);
/*     */ 
/* 103 */     d();
/*     */ 
/* 105 */     this.jdField_a_of_type_Boolean = false;
/*     */   }
/*     */ 
/*     */   private void a(kp paramkp) {
/* 109 */     this.jdField_a_of_type_YA.clear();
/*     */ 
/* 111 */     if (paramkp != null)
/*     */     {
/* 113 */       int i = (paramkp = paramkp.a()).length; for (int j = 0; j < i; j++)
/*     */       {
/*     */         ko localko;
/*     */         Object localObject;
/* 114 */         if (((
/* 114 */           localko = paramkp[j])
/* 114 */           .a() instanceof Boolean)) {
/* 115 */           localObject = new fs(a(), localko);
/* 116 */           this.jdField_a_of_type_YA.a(new yM(a(), localko.a(), (yL)localObject, true));
/*     */         } else {
/* 118 */           localObject = new ft(a(), localko);
/* 119 */           this.jdField_a_of_type_YA.a(new yM(a(), localko.a(), (yL)localObject, true));
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void a(xq paramxq)
/*     */   {
/* 127 */     super.a(paramxq);
/*     */ 
/* 129 */     if (a().d) {
/* 130 */       if (a().a != null) {
/* 131 */         if (!(a().a.a().a() instanceof wp)) {
/* 132 */           ((ct)a()).a().b("this structure has no AI");
/* 133 */           return;
/*     */         }
/* 135 */         a((kp)((wp)a().a.a().a()).a());
/*     */       } else {
/* 137 */         a(null);
/*     */       }
/* 139 */       a().d = false;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     fr
 * JD-Core Version:    0.6.2
 */