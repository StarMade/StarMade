/*  1:   */import java.util.Random;
/*  2:   */
/*  6:   */public final class ua
/*  7:   */  extends tX
/*  8:   */{
/*  9:   */  public ua(long paramLong)
/* 10:   */  {
/* 11:11 */    super(paramLong);
/* 12:   */  }
/* 13:   */  
/* 14:   */  protected final short a(float paramFloat)
/* 15:   */  {
/* 16:16 */    short s = 80;
/* 17:   */    
/* 18:18 */    if ((paramFloat < 5.1D) && (this.jdField_a_of_type_JavaUtilRandom.nextFloat() > 0.1F)) {
/* 19:19 */      s = 73;
/* 20:   */    }
/* 21:21 */    return s;
/* 22:   */  }
/* 23:   */  
/* 24:   */  public final void a() {
/* 25:25 */    this.jdField_a_of_type_ArrayOfUX = new uX[3];
/* 26:26 */    this.jdField_a_of_type_ArrayOfUX[0] = new va(133, 6, 73);
/* 27:27 */    this.jdField_a_of_type_ArrayOfUX[1] = new va(136, 6, 73);
/* 28:28 */    this.jdField_a_of_type_ArrayOfUX[2] = new va(129, 8, 73);
/* 29:   */  }
/* 30:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ua
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */