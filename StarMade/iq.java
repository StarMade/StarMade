/*    */ import org.schema.game.common.data.element.ElementInformation;
/*    */ 
/*    */ final class iq
/*    */ {
/*    */   private ElementInformation jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation;
/*    */ 
/*    */   public iq(ip paramip, ElementInformation paramElementInformation)
/*    */   {
/* 31 */     this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation = paramElementInformation;
/*    */   }
/*    */ 
/*    */   public final String toString()
/*    */   {
/*    */     kf localkf;
/* 37 */     if ((
/* 37 */       localkf = ((ct)this.jdField_a_of_type_Ip.a()).a()) == null)
/*    */     {
/* 38 */       return "err";
/*    */     }
/*    */     int j;
/* 42 */     if ((
/* 42 */       j = localkf.a().a(this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation.getId())) < 0)
/*    */     {
/* 43 */       return "n/a";
/*    */     }
/* 45 */     int i = localkf.a().a(j);
/* 46 */     return "in stock: " + String.valueOf(i);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     iq
 * JD-Core Version:    0.6.2
 */