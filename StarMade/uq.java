/*  1:   */import java.util.Random;
/*  2:   */import javax.vecmath.Vector3f;
/*  3:   */import org.schema.game.common.data.world.Universe;
/*  4:   */
/*  9:   */public final class uq
/* 10:   */  extends uu
/* 11:   */{
/* 12:12 */  private q jdField_c_of_type_Q = new q();
/* 13:13 */  private q jdField_d_of_type_Q = new q();
/* 14:14 */  private q e = new q();
/* 15:15 */  private Vector3f jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/* 16:16 */  private Vector3f jdField_b_of_type_JavaxVecmathVector3f = new Vector3f();
/* 17:   */  private q[] jdField_a_of_type_ArrayOfQ;
/* 18:18 */  private int jdField_b_of_type_Int = 20;
/* 19:19 */  private int jdField_c_of_type_Int = 5;
/* 20:   */  
/* 21:21 */  private int jdField_d_of_type_Int = 25;
/* 22:   */  
/* 23:   */  public uq(uu[] paramArrayOfuu, q paramq1, q paramq2) {
/* 24:24 */    super(paramArrayOfuu, paramq1, paramq2, 4, 0);
/* 25:   */  }
/* 26:   */  
/* 27:   */  protected final short a(q paramq)
/* 28:   */  {
/* 29:29 */    a(paramq, this.jdField_c_of_type_Q);
/* 30:30 */    a(this.jdField_b_of_type_Q, this.jdField_d_of_type_Q);
/* 31:31 */    a(this.jdField_a_of_type_Q, this.e);
/* 32:   */    
/* 33:33 */    a(this.e, this.jdField_d_of_type_Q);
/* 34:   */    
/* 36:36 */    if (this.jdField_a_of_type_ArrayOfQ == null) {
/* 37:37 */      this.jdField_a_of_type_ArrayOfQ = new q[this.jdField_b_of_type_Int];
/* 38:   */      
/* 39:39 */      for (paramq = 0; paramq < this.jdField_a_of_type_ArrayOfQ.length; paramq++) {
/* 40:40 */        this.jdField_a_of_type_ArrayOfQ[paramq] = new q(Universe.getRandom().nextInt(this.jdField_d_of_type_Q.a), this.jdField_c_of_type_Int + Universe.getRandom().nextInt(this.jdField_d_of_type_Int - this.jdField_c_of_type_Int), Universe.getRandom().nextInt(this.jdField_d_of_type_Q.jdField_c_of_type_Int));
/* 41:   */      }
/* 42:   */    }
/* 43:   */    
/* 46:46 */    for (paramq = 0; paramq < this.jdField_a_of_type_ArrayOfQ.length; paramq++) {
/* 47:47 */      this.jdField_a_of_type_JavaxVecmathVector3f.set(this.jdField_c_of_type_Q.a + 0.5F, 0.0F, this.jdField_c_of_type_Q.jdField_c_of_type_Int + 0.5F);
/* 48:48 */      this.jdField_b_of_type_JavaxVecmathVector3f.set(this.jdField_d_of_type_Q.a / 2.0F, 0.0F, this.jdField_d_of_type_Q.jdField_c_of_type_Int / 2.0F);
/* 49:   */      
/* 50:50 */      this.jdField_a_of_type_JavaxVecmathVector3f.sub(this.jdField_b_of_type_JavaxVecmathVector3f);
/* 51:   */      
/* 52:   */      float f;
/* 53:   */      
/* 54:54 */      if (((f = this.jdField_a_of_type_JavaxVecmathVector3f.length()) < this.jdField_d_of_type_Q.a / 2.0F + 0.5F) && (f > 5.5F) && (this.jdField_c_of_type_Q.a == this.jdField_a_of_type_ArrayOfQ[paramq].a) && (this.jdField_c_of_type_Q.jdField_c_of_type_Int == this.jdField_a_of_type_ArrayOfQ[paramq].jdField_c_of_type_Int) && (this.jdField_c_of_type_Q.jdField_b_of_type_Int > this.jdField_d_of_type_Q.jdField_b_of_type_Int - this.jdField_a_of_type_ArrayOfQ[paramq].jdField_b_of_type_Int)) {
/* 55:55 */        if ((Math.random() < 0.2D) && (this.jdField_c_of_type_Q.jdField_b_of_type_Int == this.jdField_d_of_type_Q.jdField_b_of_type_Int - this.jdField_a_of_type_ArrayOfQ[paramq].jdField_b_of_type_Int + 1)) {
/* 56:56 */          return 62;
/* 57:   */        }
/* 58:58 */        return 5;
/* 59:   */      }
/* 60:   */    }
/* 61:   */    
/* 63:63 */    return 32767;
/* 64:   */  }
/* 65:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     uq
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */