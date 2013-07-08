/*  1:   */import java.util.Random;
/*  2:   */
/*  6:   */public final class ud
/*  7:   */  extends tX
/*  8:   */{
/*  9:   */  public ud(long paramLong)
/* 10:   */  {
/* 11:11 */    super(paramLong);
/* 12:   */  }
/* 13:   */  
/* 14:   */  protected final short a(float paramFloat)
/* 15:   */  {
/* 16:16 */    short s = 73;
/* 17:   */    
/* 18:18 */    if ((paramFloat < 3.4F) && (this.jdField_a_of_type_JavaUtilRandom.nextFloat() > 0.3F)) {
/* 19:19 */      s = 64;
/* 20:20 */    } else if ((paramFloat > 9.1D) && (this.jdField_a_of_type_JavaUtilRandom.nextFloat() > 0.68F)) {
/* 21:21 */      s = 275;
/* 22:   */    }
/* 23:23 */    return s;
/* 24:   */  }
/* 25:   */  
/* 26:   */  public final void a()
/* 27:   */  {
/* 28:28 */    this.jdField_a_of_type_ArrayOfUX = new uX[3];
/* 29:29 */    this.jdField_a_of_type_ArrayOfUX[0] = new va(209, 6, 73);
/* 30:30 */    this.jdField_a_of_type_ArrayOfUX[1] = new va(132, 6, 73);
/* 31:31 */    this.jdField_a_of_type_ArrayOfUX[2] = new va(208, 8, 73);
/* 32:   */  }
/* 33:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ud
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */