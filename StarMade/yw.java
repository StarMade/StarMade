/*     */ import javax.vecmath.Vector3f;
/*     */ import org.schema.schine.graphicsengine.core.GlUtil;
/*     */ import org.schema.schine.graphicsengine.core.settings.StateParameterNotFoundException;
/*     */ import org.schema.schine.network.client.ClientState;
/*     */ 
/*     */ public abstract class yw extends yL
/*     */   implements ys
/*     */ {
/*     */   private yE jdField_a_of_type_YE;
/*     */   private yE jdField_b_of_type_YE;
/*     */   private boolean jdField_a_of_type_Boolean;
/*     */ 
/*     */   public yw(ClientState paramClientState)
/*     */   {
/*  18 */     super(paramClientState);
/*  19 */     this.g = true;
/*  20 */     super.a(this);
/*  21 */     this.jdField_a_of_type_YE = new yE(xe.a().a("tools-16x16-gui-"), a());
/*  22 */     this.jdField_b_of_type_YE = new yE(xe.a().a("tools-16x16-gui-"), a());
/*     */   }
/*     */ 
/*     */   public final void a(yz paramyz, xp paramxp)
/*     */   {
/*  28 */     if ((paramxp.jdField_a_of_type_Boolean) && (paramxp.jdField_a_of_type_Int == 0)) {
/*  29 */       if (b()) try { f();
/*     */           return;
/*     */         }
/*     */         catch (StateParameterNotFoundException localStateParameterNotFoundException1) {
/*  34 */           localStateParameterNotFoundException1.printStackTrace();
/*     */ 
/*  34 */           return;
/*     */         } try {
/*  37 */         e();
/*     */         return;
/*     */       } catch (StateParameterNotFoundException localStateParameterNotFoundException2) {
/*  40 */         localStateParameterNotFoundException2.printStackTrace();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void a(ys paramys)
/*     */   {
/*  48 */     if (!jdField_b_of_type_Boolean) throw new AssertionError("CANNOT SET CALLBACK BESIDES OWN");
/*     */   }
/*     */ 
/*     */   protected abstract void e();
/*     */ 
/*     */   protected abstract void f();
/*     */ 
/*     */   protected abstract boolean b();
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
/*  69 */     if (!this.jdField_a_of_type_Boolean) {
/*  70 */       c();
/*     */     }
/*  72 */     GlUtil.d();
/*  73 */     r();
/*  74 */     l();
/*  75 */     this.jdField_a_of_type_YE.b();
/*  76 */     if (b()) {
/*  77 */       this.jdField_b_of_type_YE.b();
/*     */     }
/*  79 */     GlUtil.c();
/*     */   }
/*     */ 
/*     */   public final float a()
/*     */   {
/*  84 */     return this.jdField_a_of_type_YE.a();
/*     */   }
/*     */ 
/*     */   public final float b()
/*     */   {
/*  89 */     return this.jdField_a_of_type_YE.b();
/*     */   }
/*     */ 
/*     */   public final boolean a()
/*     */   {
/*  95 */     return false;
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/* 107 */     this.jdField_a_of_type_YE.a_(18);
/* 108 */     this.jdField_b_of_type_YE.a_(19);
/*     */ 
/* 110 */     this.jdField_a_of_type_YE.a().y += 2.0F;
/* 111 */     this.jdField_b_of_type_YE.a().set(this.jdField_a_of_type_YE.a());
/* 112 */     this.jdField_a_of_type_Boolean = true;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     yw
 * JD-Core Version:    0.6.2
 */