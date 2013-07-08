/*  1:   */
/*  3:   */public final class cx
/*  4:   */{
/*  5:   */  public final int a;
/*  6:   */  public final int b;
/*  7:   */  public final mF a;
/*  8:   */  
/*  9:   */  public cx(mF parammF, int paramInt1, int paramInt2)
/* 10:   */  {
/* 11:11 */    this.jdField_a_of_type_Int = paramInt1;
/* 12:12 */    this.b = paramInt2;
/* 13:13 */    this.jdField_a_of_type_MF = parammF;
/* 14:   */  }
/* 15:   */  
/* 19:   */  public final int hashCode()
/* 20:   */  {
/* 21:21 */    return this.jdField_a_of_type_MF.hashCode() + this.jdField_a_of_type_Int + this.b * 10000;
/* 22:   */  }
/* 23:   */  
/* 27:   */  public final boolean equals(Object paramObject)
/* 28:   */  {
/* 29:29 */    return (((cx)paramObject).jdField_a_of_type_MF == this.jdField_a_of_type_MF) && (((cx)paramObject).jdField_a_of_type_Int == this.jdField_a_of_type_Int) && (((cx)paramObject).b == this.b);
/* 30:   */  }
/* 31:   */  
/* 34:   */  public final String toString()
/* 35:   */  {
/* 36:36 */    return "SectorChange[" + this.jdField_a_of_type_Int + " -> " + this.b + "]";
/* 37:   */  }
/* 38:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     cx
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */