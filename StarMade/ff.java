/*     */ import javax.vecmath.Vector3f;
/*     */ import org.schema.schine.network.client.ClientState;
/*     */ 
/*     */ public final class ff extends yz
/*     */ {
/*     */   private yE jdField_a_of_type_YE;
/*     */   private yE b;
/*     */   private yE c;
/*     */   private yE d;
/*     */   private boolean jdField_a_of_type_Boolean;
/*     */   private yE e;
/*     */ 
/*     */   public ff(ClientState paramClientState)
/*     */   {
/*  22 */     super(paramClientState);
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void b()
/*     */   {
/*  31 */     if (this.jdField_a_of_type_Boolean) {
/*  32 */       int i = ((ct)a()).a().jdField_a_of_type_Y.a;
/*  33 */       if (a().a().b) {
/*  34 */         this.jdField_a_of_type_YE.b();
/*     */       }
/*  36 */       if (a().a().b) {
/*  37 */         if (i == 0) {
/*  38 */           this.b.b();
/*     */         }
/*  40 */         if (i == 1) {
/*  41 */           this.c.b();
/*     */         }
/*  43 */         if (i == 2) {
/*  44 */           this.d.b();
/*     */         }
/*     */       }
/*  47 */       return;
/*  48 */     }this.e.b();
/*     */   }
/*     */ 
/*     */   public final float a()
/*     */   {
/*  55 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   private ar a()
/*     */   {
/*  62 */     return ((ct)a()).a().jdField_a_of_type_Am.a.a;
/*     */   }
/*     */ 
/*     */   public final float b()
/*     */   {
/*  69 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/*  86 */     this.e = new yE(xe.a().a("help-gui-"), a());
/*  87 */     this.e.h(9);
/*  88 */     this.e.c();
/*  89 */     this.e.a().y -= 64.0F;
/*     */ 
/*  92 */     this.jdField_a_of_type_YE = new yE(xe.a().a("help-gamecharacter-gui-"), a());
/*  93 */     this.jdField_a_of_type_YE.h(17);
/*  94 */     this.b = new yE(xe.a().a("help-shipcommon-gui-"), a());
/*  95 */     this.b.h(17);
/*  96 */     this.c = new yE(xe.a().a("help-shipbuild-gui-"), a());
/*  97 */     this.c.h(17);
/*  98 */     this.d = new yE(xe.a().a("help-shipflight-gui-"), a());
/*  99 */     this.d.h(17);
/*     */   }
/*     */ 
/*     */   public final void a(boolean paramBoolean)
/*     */   {
/* 105 */     this.jdField_a_of_type_Boolean = paramBoolean;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ff
 * JD-Core Version:    0.6.2
 */