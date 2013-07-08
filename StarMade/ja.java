/*  1:   */
/*  3:   */public final class ja
/*  4:   */{
/*  5:   */  public int a;
/*  6:   */  public int b;
/*  7:   */  public int c;
/*  8:   */  public short a;
/*  9:   */  
/* 10:   */  public ja(q paramq, short paramShort)
/* 11:   */  {
/* 12:12 */    a(paramq);
/* 13:13 */    this.jdField_a_of_type_Short = paramShort;
/* 14:   */  }
/* 15:   */  
/* 16:   */  public ja() {}
/* 17:   */  
/* 18:18 */  public ja(ja paramja) { this.jdField_a_of_type_Int = paramja.jdField_a_of_type_Int;
/* 19:19 */    this.b = paramja.b;
/* 20:20 */    this.c = paramja.c;
/* 21:21 */    this.jdField_a_of_type_Short = paramja.jdField_a_of_type_Short;
/* 22:   */  }
/* 23:   */  
/* 24:   */  public final boolean equals(Object paramObject) {
/* 25:25 */    if (paramObject != null) {
/* 26:26 */      if ((paramObject instanceof ja)) {
/* 27:27 */        paramObject = (ja)paramObject;
/* 28:28 */        return (this.jdField_a_of_type_Short == paramObject.jdField_a_of_type_Short) && (this.jdField_a_of_type_Int == paramObject.jdField_a_of_type_Int) && (this.b == paramObject.b) && (this.c == paramObject.c);
/* 29:   */      }
/* 30:   */      
/* 33:33 */      paramObject = (q)paramObject;
/* 34:34 */      return (this.jdField_a_of_type_Int == paramObject.jdField_a_of_type_Int) && (this.b == paramObject.b) && (this.c == paramObject.c);
/* 35:   */    }
/* 36:   */    
/* 39:39 */    return false;
/* 40:   */  }
/* 41:   */  
/* 54:54 */  public final int hashCode() { return ((this.jdField_a_of_type_Int ^ this.jdField_a_of_type_Int >>> 16) * 15 + (this.b ^ this.b >>> 16)) * 15 + (this.c ^ this.c >>> 16); }
/* 55:   */  
/* 56:   */  public final void a(q paramq, short paramShort) {
/* 57:57 */    a(paramq);
/* 58:58 */    this.jdField_a_of_type_Short = paramShort;
/* 59:   */  }
/* 60:   */  
/* 61:   */  private void a(q paramq) {
/* 62:62 */    this.jdField_a_of_type_Int = paramq.jdField_a_of_type_Int;
/* 63:63 */    this.b = paramq.b;
/* 64:64 */    this.c = paramq.c;
/* 65:   */  }
/* 66:   */  
/* 69:   */  public final String toString()
/* 70:   */  {
/* 71:71 */    return "ElementPosition [x=" + this.jdField_a_of_type_Int + ", y=" + this.b + ", z=" + this.c + ", type=" + this.jdField_a_of_type_Short + "]";
/* 72:   */  }
/* 73:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ja
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */