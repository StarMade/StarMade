/*  1:   */
/*  4:   */public final class xq
/*  5:   */{
/*  6: 6 */  private long jdField_a_of_type_Long = 0L;
/*  7:   */  private long jdField_b_of_type_Long;
/*  8:   */  private long c;
/*  9:   */  private int jdField_a_of_type_Int;
/* 10:10 */  private long d = 0L;
/* 11:   */  
/* 13:   */  private double jdField_a_of_type_Double;
/* 14:   */  
/* 15:15 */  private static double jdField_b_of_type_Double = 1000000000.0D;
/* 16:   */  
/* 42:   */  public final float a()
/* 43:   */  {
/* 44:44 */    return (float)this.jdField_a_of_type_Double;
/* 45:   */  }
/* 46:   */  
/* 51:51 */  public final long a() { return this.jdField_b_of_type_Long; }
/* 52:   */  
/* 53:   */  public final void a() {
/* 54:54 */    this.c = System.currentTimeMillis();
/* 55:   */    
/* 56:56 */    this.jdField_a_of_type_Long = System.nanoTime();
/* 57:   */  }
/* 58:   */  
/* 59:   */  public final void b()
/* 60:   */  {
/* 61:61 */    if (System.currentTimeMillis() - this.c > 1000L) {
/* 62:62 */      this.jdField_b_of_type_Long = this.jdField_a_of_type_Int;
/* 63:63 */      this.jdField_a_of_type_Int = 0;
/* 64:64 */      this.c += 1000L;
/* 65:   */    }
/* 66:66 */    this.jdField_a_of_type_Int += 1;
/* 67:67 */    this.jdField_a_of_type_Long = this.d;
/* 68:68 */    this.d = System.nanoTime();
/* 69:   */    
/* 70:70 */    long l = this.d - this.jdField_a_of_type_Long;
/* 71:71 */    this.jdField_a_of_type_Double = (l / jdField_b_of_type_Double);
/* 72:   */  }
/* 73:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     xq
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */