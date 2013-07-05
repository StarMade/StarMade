/*    */ import org.schema.schine.graphicsengine.core.GlUtil;
/*    */ import org.schema.schine.network.client.ClientState;
/*    */ 
/*    */ public final class il extends yz
/*    */ {
/*    */   private zy jdField_a_of_type_Zy;
/*    */   private yE jdField_a_of_type_YE;
/*    */   private yQ jdField_a_of_type_YQ;
/*    */   private int jdField_a_of_type_Int;
/*    */   private int b;
/*    */   private im jdField_a_of_type_Im;
/*    */   private ih jdField_a_of_type_Ih;
/*    */ 
/*    */   public il(ClientState paramClientState)
/*    */   {
/* 23 */     super(paramClientState);
/* 24 */     this.b = 113;
/* 25 */     this.jdField_a_of_type_Int = 113;
/*    */   }
/*    */ 
/*    */   public final void a()
/*    */   {
/*    */   }
/*    */ 
/*    */   public final void b()
/*    */   {
/* 38 */     GlUtil.d();
/* 39 */     r();
/*    */ 
/* 41 */     this.jdField_a_of_type_YE.b();
/*    */ 
/* 43 */     GlUtil.c();
/*    */   }
/*    */ 
/*    */   public final void c()
/*    */   {
/* 48 */     this.jdField_a_of_type_Zy = xe.a().a("radarBackground").a().a();
/* 49 */     this.jdField_a_of_type_YQ = new yQ(a(), this.b, this.jdField_a_of_type_Int, this.jdField_a_of_type_Zy);
/* 50 */     this.jdField_a_of_type_YQ.c();
/*    */ 
/* 52 */     this.jdField_a_of_type_YE = new yE(xe.a().a("radarGUIBackground"), a());
/*    */ 
/* 54 */     this.jdField_a_of_type_YE.a(this.jdField_a_of_type_YQ);
/* 55 */     this.jdField_a_of_type_YQ.a(8.0F, 8.0F, 0.0F);
/*    */ 
/* 57 */     this.jdField_a_of_type_Im = new im(a(), this.b);
/* 58 */     this.jdField_a_of_type_Im.c();
/* 59 */     this.jdField_a_of_type_YQ.a(this.jdField_a_of_type_Im);
/*    */ 
/* 61 */     this.jdField_a_of_type_Ih = new ih(a());
/* 62 */     this.jdField_a_of_type_Ih.c();
/* 63 */     this.jdField_a_of_type_YE.a(this.jdField_a_of_type_Ih);
/*    */   }
/*    */ 
/*    */   public final float a()
/*    */   {
/* 69 */     return this.jdField_a_of_type_YE.a();
/*    */   }
/*    */ 
/*    */   public final float b()
/*    */   {
/* 74 */     return this.jdField_a_of_type_YE.b();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     il
 * JD-Core Version:    0.6.2
 */