/*  1:   */import org.schema.game.common.data.element.ElementInformation;
/*  2:   */
/* 25:   */final class iq
/* 26:   */{
/* 27:   */  private ElementInformation jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation;
/* 28:   */  
/* 29:   */  public iq(ip paramip, ElementInformation paramElementInformation)
/* 30:   */  {
/* 31:31 */    this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation = paramElementInformation;
/* 32:   */  }
/* 33:   */  
/* 34:   */  public final String toString()
/* 35:   */  {
/* 36:   */    kf localkf;
/* 37:37 */    if ((localkf = ((ct)this.jdField_a_of_type_Ip.a()).a()) == null) {
/* 38:38 */      return "err";
/* 39:   */    }
/* 40:   */    
/* 41:   */    int j;
/* 42:42 */    if ((j = localkf.a().a(this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation.getId())) < 0) {
/* 43:43 */      return "n/a";
/* 44:   */    }
/* 45:45 */    int i = localkf.a().a(j);
/* 46:46 */    return "in stock: " + String.valueOf(i);
/* 47:   */  }
/* 48:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     iq
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */