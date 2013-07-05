/*    */ import org.schema.schine.graphicsengine.core.GlUtil;
/*    */ import org.schema.schine.graphicsengine.core.settings.StateParameterNotFoundException;
/*    */ import org.schema.schine.network.client.ClientState;
/*    */ 
/*    */ public class fs extends yL
/*    */   implements ys
/*    */ {
/*    */   private yE jdField_a_of_type_YE;
/*    */   private yE b;
/*    */   private ko jdField_a_of_type_Ko;
/*    */ 
/*    */   public fs(ClientState paramClientState, ko paramko)
/*    */   {
/* 24 */     super(paramClientState);
/* 25 */     this.g = true;
/* 26 */     a(this);
/* 27 */     this.jdField_a_of_type_Ko = paramko;
/* 28 */     if ((!jdField_a_of_type_Boolean) && (!(paramko.a() instanceof Boolean))) throw new AssertionError();
/* 29 */     this.jdField_a_of_type_YE = new yE(xe.a().a("tools-16x16-gui-"), a());
/* 30 */     this.b = new yE(xe.a().a("tools-16x16-gui-"), a());
/*    */   }
/*    */ 
/*    */   public final void a(yz paramyz, xp paramxp)
/*    */   {
/* 36 */     paramyz = null; if ((paramxp.jdField_a_of_type_Boolean) && (paramxp.jdField_a_of_type_Int == 0)) {
/*    */       try {
/* 40 */         this.jdField_a_of_type_Ko.a();
/*    */         return; } catch (StateParameterNotFoundException localStateParameterNotFoundException) {
/* 41 */         (
/* 42 */           paramyz = 
/* 44 */           localStateParameterNotFoundException).printStackTrace();
/* 43 */         xm.a(paramyz);
/*    */       }
/*    */     }
/*    */   }
/*    */ 
/*    */   public final void a()
/*    */   {
/*    */   }
/*    */ 
/*    */   protected final void d()
/*    */   {
/*    */   }
/*    */ 
/*    */   public final void b()
/*    */   {
/* 62 */     GlUtil.d();
/* 63 */     r();
/* 64 */     l();
/* 65 */     this.jdField_a_of_type_YE.b();
/* 66 */     if (this.jdField_a_of_type_Ko.a()) {
/* 67 */       this.b.b();
/*    */     }
/* 69 */     GlUtil.c();
/*    */   }
/*    */ 
/*    */   public final float a()
/*    */   {
/* 74 */     return this.jdField_a_of_type_YE.a();
/*    */   }
/*    */ 
/*    */   public final float b()
/*    */   {
/* 79 */     return this.jdField_a_of_type_YE.b();
/*    */   }
/*    */ 
/*    */   public final boolean a()
/*    */   {
/* 85 */     return false;
/*    */   }
/*    */ 
/*    */   public final void c()
/*    */   {
/* 97 */     this.jdField_a_of_type_YE.a_(18);
/* 98 */     this.b.a_(19);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     fs
 * JD-Core Version:    0.6.2
 */