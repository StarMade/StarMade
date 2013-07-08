/*  1:   */
/*  2:   */public final class qe
/*  3:   */{
/*  4:   */  public String a;
/*  5:   */  public String b;
/*  6:   */  public int a;
/*  7:   */  
/*  8:   */  public qe(String paramString1, int paramInt, String paramString2)
/*  9:   */  {
/* 10:10 */    this.b = paramString1;
/* 11:11 */    this.jdField_a_of_type_Int = paramInt;
/* 12:12 */    this.jdField_a_of_type_JavaLangString = paramString2;
/* 13:   */  }
/* 14:   */  
/* 15:   */  public final String toString()
/* 16:   */  {
/* 17:17 */    return this.jdField_a_of_type_JavaLangString + "@" + this.b + ":" + this.jdField_a_of_type_Int;
/* 18:   */  }
/* 19:   */  
/* 24:   */  public final boolean equals(Object paramObject)
/* 25:   */  {
/* 26:26 */    return (this.jdField_a_of_type_JavaLangString.equals(((qe)paramObject).jdField_a_of_type_JavaLangString)) && (this.b.equals(((qe)paramObject).b)) && (this.jdField_a_of_type_Int == ((qe)paramObject).jdField_a_of_type_Int);
/* 27:   */  }
/* 28:   */  
/* 33:   */  public final int hashCode()
/* 34:   */  {
/* 35:35 */    return this.jdField_a_of_type_JavaLangString.hashCode() + this.b.hashCode() + this.jdField_a_of_type_Int;
/* 36:   */  }
/* 37:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     qe
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */