/*    */ import org.schema.game.common.data.element.ElementInformation;
/*    */ 
/*    */ final class ir
/*    */ {
/*    */   private ElementInformation jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation;
/*    */ 
/*    */   public ir(ip paramip, ElementInformation paramElementInformation)
/*    */   {
/* 55 */     this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation = paramElementInformation;
/*    */   }
/*    */ 
/*    */   public final String toString()
/*    */   {
/*    */     kf localkf;
/* 61 */     if ((
/* 61 */       localkf = ((ct)this.jdField_a_of_type_Ip.a()).a()) == null)
/*    */     {
/* 62 */       return "err";
/*    */     }
/* 64 */     int i = localkf.a(this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation, 1);
/* 65 */     return "est. price: " + i;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ir
 * JD-Core Version:    0.6.2
 */