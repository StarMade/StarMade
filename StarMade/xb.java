/*  1:   */import com.bulletphysics.linearmath.Transform;
/*  2:   */import javax.vecmath.Vector3f;
/*  3:   */
/* 12:   */public class xb
/* 13:   */  extends xa
/* 14:   */{
/* 15:   */  public yh a;
/* 16:   */  protected Transform a;
/* 17:17 */  private Vector3f jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/* 18:   */  
/* 19:   */  public xb(yh paramyh)
/* 20:   */  {
/* 21:21 */    a(paramyh);
/* 22:   */  }
/* 23:   */  
/* 24:   */  public final yh a()
/* 25:   */  {
/* 26:26 */    return this.jdField_a_of_type_Yh;
/* 27:   */  }
/* 28:   */  
/* 35:   */  public Vector3f a()
/* 36:   */  {
/* 37:37 */    this.jdField_a_of_type_ComBulletphysicsLinearmathTransform = this.jdField_a_of_type_Yh.getWorldTransform();
/* 38:38 */    if (this.jdField_a_of_type_ComBulletphysicsLinearmathTransform != null) {
/* 39:39 */      this.jdField_a_of_type_JavaxVecmathVector3f.set(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform.origin);
/* 40:   */    } else {
/* 41:41 */      this.jdField_a_of_type_JavaxVecmathVector3f.set(0.0F, 0.0F, 0.0F);
/* 42:   */    }
/* 43:43 */    return this.jdField_a_of_type_JavaxVecmathVector3f;
/* 44:   */  }
/* 45:   */  
/* 48:   */  public final void a(yh paramyh)
/* 49:   */  {
/* 50:50 */    if ((!jdField_a_of_type_Boolean) && (paramyh == null)) throw new AssertionError();
/* 51:51 */    this.jdField_a_of_type_Yh = paramyh;
/* 52:   */  }
/* 53:   */  
/* 54:   */  public void a(xq paramxq) {}
/* 55:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     xb
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */