/*    */ import javax.vecmath.Vector4f;
/*    */ import org.schema.schine.network.client.ClientState;
/*    */ 
/*    */ public final class yt extends yz
/*    */ {
/*    */   private yx jdField_a_of_type_Yx;
/*    */   private yG jdField_a_of_type_YG;
/*    */   private yP jdField_a_of_type_YP;
/*    */ 
/*    */   public yt(ClientState paramClientState)
/*    */   {
/* 18 */     super(paramClientState);
/* 19 */     this.jdField_a_of_type_YP = new yP(400, 150, d.n(), paramClientState);
/* 20 */     this.jdField_a_of_type_YP.a = 100;
/*    */ 
/* 22 */     this.jdField_a_of_type_YP.b = a().getGeneralChatLog();
/*    */ 
/* 24 */     this.jdField_a_of_type_YG = new yH(paramClientState);
/* 25 */     this.jdField_a_of_type_Yx = new yx(paramClientState, 400.0F, 150.0F, new Vector4f(1.0F, 1.0F, 1.0F, 0.1F));
/*    */   }
/*    */ 
/*    */   public final void a()
/*    */   {
/*    */   }
/*    */ 
/*    */   public final void b()
/*    */   {
/* 36 */     this.jdField_a_of_type_Yx.b();
/*    */   }
/*    */ 
/*    */   public final void c()
/*    */   {
/* 42 */     this.jdField_a_of_type_Yx.c = 5.0F;
/*    */ 
/* 44 */     this.jdField_a_of_type_Yx.a(this.jdField_a_of_type_YG);
/* 45 */     this.jdField_a_of_type_YG.c(this.jdField_a_of_type_YP);
/*    */ 
/* 47 */     this.jdField_a_of_type_Yx.c();
/* 48 */     this.jdField_a_of_type_YG.c();
/* 49 */     this.jdField_a_of_type_YP.c();
/*    */   }
/*    */ 
/*    */   public final float a()
/*    */   {
/* 55 */     return 0.0F;
/*    */   }
/*    */ 
/*    */   public final float b()
/*    */   {
/* 61 */     return 0.0F;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     yt
 * JD-Core Version:    0.6.2
 */