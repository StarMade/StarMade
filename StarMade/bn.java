/*    */ import org.schema.game.common.data.element.ElementInformation;
/*    */ 
/*    */ public final class bn
/*    */ {
/*    */   private ElementInformation jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation;
/*    */   private kf jdField_a_of_type_Kf;
/*    */   public int a;
/*    */ 
/*    */   public bn(ElementInformation paramElementInformation, kf paramkf)
/*    */   {
/* 12 */     this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation = paramElementInformation;
/* 13 */     this.jdField_a_of_type_Kf = paramkf;
/*    */   }
/*    */ 
/*    */   public final String toString()
/*    */   {
/* 18 */     return "how many " + this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation.getName() + " you want to buy?\nIf you enter too many, the maximal amount you can affort\nwill be displayed.\nCurrent Buying Value: " + this.jdField_a_of_type_Kf.a(this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation, this.jdField_a_of_type_Int) + "c (base " + this.jdField_a_of_type_Int * this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation.getPrice() + "c)";
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     bn
 * JD-Core Version:    0.6.2
 */