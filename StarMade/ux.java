/*  1:   */import javax.vecmath.Vector2f;
/*  2:   */
/*  8:   */public final class ux
/*  9:   */  extends uu
/* 10:   */{
/* 11:11 */  private q c = new q();
/* 12:12 */  private q d = new q();
/* 13:13 */  private q e = new q();
/* 14:   */  private Vector2f a;
/* 15:   */  private Vector2f b;
/* 16:   */  
/* 17:   */  public ux(uu[] paramArrayOfuu, q paramq1, q paramq2)
/* 18:   */  {
/* 19:19 */    super(paramArrayOfuu, paramq1, paramq2, 5, 0);this.jdField_a_of_type_JavaxVecmathVector2f = new Vector2f();this.jdField_b_of_type_JavaxVecmathVector2f = new Vector2f();
/* 20:   */  }
/* 21:   */  
/* 22:   */  protected final short a(q paramq) {
/* 23:23 */    a(paramq, this.c);
/* 24:24 */    a(this.jdField_b_of_type_Q, this.d);
/* 25:25 */    a(this.jdField_a_of_type_Q, this.e);
/* 26:   */    
/* 27:27 */    a(this.e, this.d);
/* 28:   */    
/* 32:32 */    this.jdField_a_of_type_JavaxVecmathVector2f.set(this.c.a + 0.5F, this.c.c + 0.5F);
/* 33:33 */    this.jdField_b_of_type_JavaxVecmathVector2f.set(this.d.a / 2, this.d.c / 2);
/* 34:34 */    this.jdField_b_of_type_JavaxVecmathVector2f.sub(this.jdField_a_of_type_JavaxVecmathVector2f);
/* 35:35 */    if (this.jdField_b_of_type_JavaxVecmathVector2f.length() <= this.d.a / 2) {
/* 36:36 */      if (this.c.b % 9 < 3)
/* 37:37 */        return 63;
/* 38:38 */      if (this.c.b % 9 == 6)
/* 39:39 */        return 5;
/* 40:40 */      if (this.c.b % 9 == 7)
/* 41:41 */        return 55;
/* 42:42 */      if (this.c.b % 9 == 8) {
/* 43:43 */        return 5;
/* 44:   */      }
/* 45:45 */      return 75;
/* 46:   */    }
/* 47:   */    
/* 50:50 */    return 0;
/* 51:   */  }
/* 52:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ux
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */