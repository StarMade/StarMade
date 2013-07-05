/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.schema.schine.graphicsengine.core.GlUtil;
/*     */ import org.schema.schine.network.client.ClientState;
/*     */ 
/*     */ public final class fz extends yL
/*     */   implements ys
/*     */ {
/*     */   private yP jdField_a_of_type_YP;
/*     */   private yE jdField_a_of_type_YE;
/*     */   private yE b;
/*     */   private aj jdField_a_of_type_Aj;
/*     */   private boolean jdField_a_of_type_Boolean;
/*     */ 
/*     */   public fz(ClientState paramClientState, aj paramaj)
/*     */   {
/*  29 */     super(paramClientState);
/*  30 */     this.g = true;
/*  31 */     a(this);
/*  32 */     this.jdField_a_of_type_Aj = paramaj;
/*  33 */     this.jdField_a_of_type_YE = new yE(xe.a().a("tools-16x16-gui-"), a());
/*  34 */     this.b = new yE(xe.a().a("tools-16x16-gui-"), a());
/*  35 */     this.jdField_a_of_type_YP = new yP(30, 30, d.e(), a());
/*     */   }
/*     */ 
/*     */   public final void a(yz paramyz, xp paramxp)
/*     */   {
/*  41 */     if ((paramxp.jdField_a_of_type_Boolean) && (paramxp.jdField_a_of_type_Int == 0))
/*     */     {
/*  43 */       System.err.println("XCALLBACk");
/*     */     }
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
/*  61 */     if (!this.jdField_a_of_type_Boolean) {
/*  62 */       c();
/*     */     }
/*  64 */     GlUtil.d();
/*  65 */     r();
/*  66 */     this.jdField_a_of_type_YP.b();
/*  67 */     this.jdField_a_of_type_YE.b();
/*  68 */     this.b.b();
/*  69 */     GlUtil.c();
/*     */   }
/*     */ 
/*     */   public final float a()
/*     */   {
/*  74 */     return this.jdField_a_of_type_YP.a();
/*     */   }
/*     */ 
/*     */   public final float b()
/*     */   {
/*  79 */     return this.jdField_a_of_type_YP.b() + this.jdField_a_of_type_YE.b() + this.b.b();
/*     */   }
/*     */ 
/*     */   public final boolean a()
/*     */   {
/*  85 */     return false;
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/*  98 */     this.jdField_a_of_type_YP.b = new ArrayList();
/*  99 */     this.jdField_a_of_type_YP.b.add(this.jdField_a_of_type_Aj.toString());
/* 100 */     this.jdField_a_of_type_YP.c();
/*     */ 
/* 102 */     this.jdField_a_of_type_YE.g = true;
/* 103 */     this.b.g = true;
/*     */ 
/* 106 */     this.jdField_a_of_type_YE.a(new fA(this));
/*     */ 
/* 129 */     this.b.a(new fB(this));
/*     */ 
/* 146 */     this.jdField_a_of_type_YE.a_(21);
/* 147 */     this.b.a_(20);
/* 148 */     this.jdField_a_of_type_YP.a().x = this.jdField_a_of_type_YE.b();
/* 149 */     this.b.a().x = (this.jdField_a_of_type_YE.b() + this.jdField_a_of_type_YP.b());
/*     */ 
/* 151 */     this.jdField_a_of_type_Boolean = true;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     fz
 * JD-Core Version:    0.6.2
 */