/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.schema.schine.graphicsengine.core.GlUtil;
/*     */ import org.schema.schine.graphicsengine.core.settings.StateParameterNotFoundException;
/*     */ import org.schema.schine.network.client.ClientState;
/*     */ 
/*     */ public final class ft extends yL
/*     */   implements ys
/*     */ {
/*     */   private yP jdField_a_of_type_YP;
/*     */   private yE jdField_a_of_type_YE;
/*     */   private yE b;
/*     */   private ko jdField_a_of_type_Ko;
/*     */   private boolean jdField_a_of_type_Boolean;
/*     */ 
/*     */   public ft(ClientState paramClientState, ko paramko)
/*     */   {
/*  31 */     super(paramClientState);
/*  32 */     this.g = true;
/*  33 */     a(this);
/*  34 */     this.jdField_a_of_type_Ko = paramko;
/*  35 */     this.jdField_a_of_type_YE = new yE(xe.a().a("tools-16x16-gui-"), a());
/*  36 */     this.b = new yE(xe.a().a("tools-16x16-gui-"), a());
/*  37 */     this.jdField_a_of_type_YP = new yP(140, 30, d.d(), a());
/*     */   }
/*     */ 
/*     */   public final void a(yz paramyz, xp paramxp)
/*     */   {
/*  44 */     paramyz = null; if ((paramxp.jdField_a_of_type_Boolean) && (paramxp.jdField_a_of_type_Int == 0)) try {
/*  47 */         this.jdField_a_of_type_Ko.a();
/*     */         return; } catch (StateParameterNotFoundException localStateParameterNotFoundException) {
/*  48 */         (
/*  49 */           paramyz = 
/*  51 */           localStateParameterNotFoundException).printStackTrace();
/*  50 */         xm.a(paramyz);
/*     */       }
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/*     */   }
/*     */ 
/*     */   protected final void d()
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void b()
/*     */   {
/*  68 */     if (!this.jdField_a_of_type_Boolean) {
/*  69 */       c();
/*     */     }
/*  71 */     GlUtil.d();
/*  72 */     r();
/*  73 */     this.jdField_a_of_type_YP.b();
/*  74 */     this.jdField_a_of_type_YE.b();
/*  75 */     this.b.b();
/*  76 */     GlUtil.c();
/*     */   }
/*     */ 
/*     */   public final float a()
/*     */   {
/*  81 */     return this.jdField_a_of_type_YP.a();
/*     */   }
/*     */ 
/*     */   public final float b()
/*     */   {
/*  86 */     return this.jdField_a_of_type_YP.b() + this.jdField_a_of_type_YE.b() + this.b.b();
/*     */   }
/*     */ 
/*     */   public final boolean a()
/*     */   {
/*  92 */     return false;
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/* 105 */     this.jdField_a_of_type_YP.b = new ArrayList();
/* 106 */     this.jdField_a_of_type_YP.b.add(this.jdField_a_of_type_Ko.a().toString());
/* 107 */     this.jdField_a_of_type_YP.a(0.0F, 5.0F, 0.0F);
/* 108 */     this.jdField_a_of_type_YP.c();
/*     */ 
/* 110 */     this.jdField_a_of_type_YE.g = true;
/* 111 */     this.b.g = true;
/*     */ 
/* 114 */     this.jdField_a_of_type_YE.a(new fu(this));
/*     */ 
/* 144 */     this.b.a(new fv(this));
/*     */ 
/* 167 */     this.jdField_a_of_type_YE.a_(21);
/* 168 */     this.b.a_(20);
/* 169 */     this.jdField_a_of_type_YP.a().x = this.jdField_a_of_type_YE.b();
/* 170 */     this.b.a().x = (this.jdField_a_of_type_YE.b() + this.jdField_a_of_type_YP.b());
/*     */ 
/* 172 */     this.jdField_a_of_type_Boolean = true;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ft
 * JD-Core Version:    0.6.2
 */