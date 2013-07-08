/*   1:    */
/* 103:    */final class hs
/* 104:    */{
/* 105:    */  hs(hp paramhp) {}
/* 106:    */  
/* 107:107 */  private String jdField_a_of_type_JavaLangString = "";
/* 108:108 */  private String b = "";
/* 109:    */  
/* 110:    */  public final String toString() {
/* 111:111 */    int i = ((ct)this.jdField_a_of_type_Hp.a()).a().h();
/* 112:    */    lP locallP;
/* 113:113 */    if (((locallP = ((ct)this.jdField_a_of_type_Hp.a()).a().a(i)) != null) && (locallP.d().length() > 0)) {
/* 114:114 */      hp.a(this.jdField_a_of_type_Hp, locallP);
/* 115:    */      
/* 116:116 */      if (!this.jdField_a_of_type_JavaLangString.equals(locallP.d()))
/* 117:    */      {
/* 118:118 */        this.b = ("Home Base: " + locallP.d().replaceFirst("ENTITY_", "").replaceFirst("SPACESTATION_", "").replaceFirst("PLANET_", "") + " at " + locallP.a().a + ", " + locallP.a().b + ", " + locallP.a().c);
/* 119:    */        
/* 121:121 */        this.jdField_a_of_type_JavaLangString = new String(locallP.d());
/* 122:    */      }
/* 123:    */      
/* 124:124 */      return this.b;
/* 125:    */    }
/* 126:126 */    hp.a(this.jdField_a_of_type_Hp, null);
/* 127:127 */    return "No Home Base";
/* 128:    */  }
/* 129:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     hs
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */