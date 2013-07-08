/*  1:   */import java.util.Random;
/*  2:   */
/*  6:   */public final class ue
/*  7:   */  extends tX
/*  8:   */{
/*  9:   */  public ue(long paramLong)
/* 10:   */  {
/* 11:11 */    super(paramLong);
/* 12:   */  }
/* 13:   */  
/* 14:   */  protected final short a(float paramFloat)
/* 15:   */  {
/* 16:16 */    short s = 92;
/* 17:   */    
/* 18:18 */    if ((paramFloat < 3.4F) && (this.jdField_a_of_type_JavaUtilRandom.nextFloat() > 0.3F)) {
/* 19:19 */      s = 91;
/* 20:20 */    } else if ((paramFloat > 9.1D) && (this.jdField_a_of_type_JavaUtilRandom.nextFloat() > 0.68F)) {
/* 21:21 */      s = 80;
/* 22:   */    }
/* 23:23 */    return s;
/* 24:   */  }
/* 25:   */  
/* 26:   */  public final void a()
/* 27:   */  {
/* 28:28 */    this.jdField_a_of_type_ArrayOfUX = new uX[3];
/* 29:29 */    this.jdField_a_of_type_ArrayOfUX[0] = new va(208, 6, 92);
/* 30:30 */    this.jdField_a_of_type_ArrayOfUX[1] = new va(207, 6, 92);
/* 31:31 */    this.jdField_a_of_type_ArrayOfUX[2] = new va(210, 9, 92);
/* 32:   */  }
/* 33:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */