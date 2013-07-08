/*  1:   */import javax.vecmath.Vector2f;
/*  2:   */
/* 11:   */public class uv
/* 12:   */  extends uu
/* 13:   */{
/* 14:   */  private Vector2f jdField_a_of_type_JavaxVecmathVector2f;
/* 15:   */  private float jdField_a_of_type_Float;
/* 16:   */  private float b;
/* 17:   */  
/* 18:   */  public uv(uu[] paramArrayOfuu, q paramq1, q paramq2)
/* 19:   */  {
/* 20:20 */    super(paramArrayOfuu, paramq1, paramq2, 5, 0);new q();new q();new q();this.jdField_a_of_type_JavaxVecmathVector2f = new Vector2f();new Vector2f();
/* 21:   */    
/* 24:24 */    this.jdField_b_of_type_Float = 5.0F;this.jdField_a_of_type_Float = 57.0F;
/* 25:   */  }
/* 26:   */  
/* 27:   */  public final boolean a(q paramq)
/* 28:   */  {
/* 29:26 */    if ((paramq.b >= this.jdField_a_of_type_Q.b) && (paramq.b <= this.jdField_b_of_type_Q.b)) {
/* 30:27 */      this.jdField_a_of_type_JavaxVecmathVector2f.x = paramq.a;
/* 31:28 */      this.jdField_a_of_type_JavaxVecmathVector2f.y = paramq.c;
/* 32:29 */      if ((this.jdField_a_of_type_JavaxVecmathVector2f.length() < this.jdField_a_of_type_Float + this.jdField_b_of_type_Float) && (this.jdField_a_of_type_JavaxVecmathVector2f.length() > this.jdField_a_of_type_Float - this.jdField_b_of_type_Float)) {
/* 33:30 */        return true;
/* 34:   */      }
/* 35:   */    }
/* 36:   */    
/* 37:34 */    return false;
/* 38:   */  }
/* 39:   */  
/* 40:   */  protected final short a(q paramq)
/* 41:   */  {
/* 42:39 */    if ((!jdField_a_of_type_Boolean) && (!a(paramq))) throw new AssertionError();
/* 43:40 */    if ((paramq.b > this.jdField_a_of_type_Q.b) && (paramq.b < this.jdField_b_of_type_Q.b))
/* 44:   */    {
/* 45:42 */      this.jdField_a_of_type_JavaxVecmathVector2f.x = paramq.a;
/* 46:43 */      this.jdField_a_of_type_JavaxVecmathVector2f.y = paramq.c;
/* 47:44 */      if ((this.jdField_a_of_type_JavaxVecmathVector2f.length() < this.jdField_a_of_type_Float + this.jdField_b_of_type_Float) && (this.jdField_a_of_type_JavaxVecmathVector2f.length() > this.jdField_a_of_type_Float - this.jdField_b_of_type_Float))
/* 48:   */      {
/* 49:46 */        if ((this.jdField_a_of_type_JavaxVecmathVector2f.length() < this.jdField_a_of_type_Float + (this.jdField_b_of_type_Float - 2.0F)) && (this.jdField_a_of_type_JavaxVecmathVector2f.length() > this.jdField_a_of_type_Float - (this.jdField_b_of_type_Float - 2.0F))) {
/* 50:47 */          return 0;
/* 51:   */        }
/* 52:49 */        return 5;
/* 53:   */      }
/* 54:   */      
/* 56:   */    }
/* 57:54 */    else if ((paramq.b == this.jdField_a_of_type_Q.b) || (paramq.b == this.jdField_b_of_type_Q.b))
/* 58:   */    {
/* 59:56 */      this.jdField_a_of_type_JavaxVecmathVector2f.x = paramq.a;
/* 60:57 */      this.jdField_a_of_type_JavaxVecmathVector2f.y = paramq.c;
/* 61:58 */      if ((this.jdField_a_of_type_JavaxVecmathVector2f.length() < this.jdField_a_of_type_Float + 1.0F) && (this.jdField_a_of_type_JavaxVecmathVector2f.length() > this.jdField_a_of_type_Float - 1.0F)) {
/* 62:59 */        return 55;
/* 63:   */      }
/* 64:61 */      return 5;
/* 65:   */    }
/* 66:   */    
/* 68:65 */    return 0;
/* 69:   */  }
/* 70:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     uv
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */