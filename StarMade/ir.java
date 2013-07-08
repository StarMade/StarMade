/*  1:   */import org.schema.game.common.data.element.ElementInformation;
/*  2:   */
/* 49:   */final class ir
/* 50:   */{
/* 51:   */  private ElementInformation jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation;
/* 52:   */  
/* 53:   */  public ir(ip paramip, ElementInformation paramElementInformation)
/* 54:   */  {
/* 55:55 */    this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation = paramElementInformation;
/* 56:   */  }
/* 57:   */  
/* 58:   */  public final String toString()
/* 59:   */  {
/* 60:   */    kf localkf;
/* 61:61 */    if ((localkf = ((ct)this.jdField_a_of_type_Ip.a()).a()) == null) {
/* 62:62 */      return "err";
/* 63:   */    }
/* 64:64 */    int i = localkf.a(this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation, 1);
/* 65:65 */    return "est. price: " + i;
/* 66:   */  }
/* 67:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ir
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */