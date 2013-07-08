/*  1:   */public final class jb
/*  2:   */{
/*  3:   */  public String a;
/*  4:   */  public int a;
/*  5:   */  public String b;
/*  6:   */  
/*  7:   */  public jb(String paramString1, int paramInt, String paramString2)
/*  8:   */  {
/*  9: 9 */    this.jdField_a_of_type_JavaLangString = paramString1;
/* 10:10 */    this.jdField_a_of_type_Int = paramInt;
/* 11:11 */    this.b = paramString2;
/* 12:   */  }
/* 13:   */  
/* 17:   */  public final boolean equals(Object paramObject)
/* 18:   */  {
/* 19:19 */    return (this.jdField_a_of_type_JavaLangString.equals(((jb)paramObject).jdField_a_of_type_JavaLangString)) && (this.jdField_a_of_type_Int == ((jb)paramObject).jdField_a_of_type_Int);
/* 20:   */  }
/* 21:   */  
/* 25:   */  public final int hashCode()
/* 26:   */  {
/* 27:27 */    return this.jdField_a_of_type_JavaLangString.hashCode() + this.jdField_a_of_type_Int;
/* 28:   */  }
/* 29:   */  
/* 30:   */  public final String toString()
/* 31:   */  {
/* 32:32 */    return this.jdField_a_of_type_JavaLangString + ":" + this.jdField_a_of_type_Int;
/* 33:   */  }
/* 34:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     jb
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */