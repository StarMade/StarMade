/*    */ import org.schema.schine.network.client.ClientState;
/*    */ 
/*    */ public final class gx extends eX
/*    */ {
/*    */   mF a;
/*    */ 
/*    */   public gx(ClientState paramClientState, aN paramaN, mF parammF)
/*    */   {
/* 18 */     super(paramClientState, paramaN, "Faction Block Config", "");
/* 19 */     this.jdField_a_of_type_MF = parammF;
/*    */   }
/*    */ 
/*    */   public final void c()
/*    */   {
/* 27 */     super.c();
/*    */     yN localyN1;
/* 29 */     (
/* 30 */       localyN1 = new yN(a(), 200, 20, "Reset Faction Signitaure", this.jdField_a_of_type_Ys)).jdField_a_of_type_JavaLangObject = 
/* 30 */       "NEUTRAL";
/*    */     yN localyN2;
/* 31 */     (
/* 32 */       localyN2 = new yN(a(), 200, 20, "Enter Faction Signiture", this.jdField_a_of_type_Ys)).jdField_a_of_type_JavaLangObject = 
/* 32 */       "FACTION";
/* 33 */     localyN2.a().y = 30.0F;
/*    */     gy localgy;
/* 37 */     (
/* 53 */       localgy = new gy(this, a(), "Make Faction Home", this.jdField_a_of_type_Ys)).jdField_a_of_type_JavaLangObject = 
/* 53 */       "HOMEBASE";
/* 54 */     localgy.a().y = 60.0F;
/* 55 */     this.jdField_a_of_type_Yr.a(localgy);
/*    */ 
/* 65 */     this.jdField_a_of_type_Yr.a(localyN1);
/* 66 */     this.jdField_a_of_type_Yr.a(localyN2);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     gx
 * JD-Core Version:    0.6.2
 */