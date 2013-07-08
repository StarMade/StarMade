/*  1:   */import org.schema.game.common.data.element.ElementInformation;
/*  2:   */
/*  4:   */public final class bv
/*  5:   */{
/*  6:   */  private ElementInformation jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation;
/*  7:   */  private kf jdField_a_of_type_Kf;
/*  8:   */  public int a;
/*  9:   */  
/* 10:   */  public bv(ElementInformation paramElementInformation, kf paramkf)
/* 11:   */  {
/* 12:12 */    this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation = paramElementInformation;
/* 13:13 */    this.jdField_a_of_type_Kf = paramkf;
/* 14:   */  }
/* 15:   */  
/* 16:   */  public final String toString()
/* 17:   */  {
/* 18:18 */    return "how many " + this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation.getName() + " (base price: " + this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation.getPrice() + "c) you want to sell?\nIf you enter too many, the maximal amount you can sell\nwill be automatically displayed.\nCurrent Selling Value: " + this.jdField_a_of_type_Kf.a(this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation, this.jdField_a_of_type_Int) + "c (base " + -this.jdField_a_of_type_Int * this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation.getPrice() + "c)";
/* 19:   */  }
/* 20:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     bv
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */