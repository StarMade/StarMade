/*  1:   */import com.bulletphysics.linearmath.Transform;
/*  2:   */import javax.vecmath.Vector3f;
/*  3:   */import org.schema.schine.network.StateInterface;
/*  4:   */
/*  9:   */public final class lk
/* 10:   */  extends ln
/* 11:   */{
/* 12:12 */  private Transform b = new Transform();
/* 13:   */  
/* 15:   */  public lk(StateInterface paramStateInterface)
/* 16:   */  {
/* 17:17 */    super(paramStateInterface);
/* 18:18 */    this.jdField_a_of_type_Float = 15.0F;
/* 19:   */  }
/* 20:   */  
/* 27:   */  public final void a(xq paramxq)
/* 28:   */  {
/* 29:29 */    super.a(paramxq);
/* 30:30 */    c(paramxq);
/* 31:   */  }
/* 32:   */  
/* 33:   */  private void c(xq paramxq)
/* 34:   */  {
/* 35:   */    Vector3f localVector3f;
/* 36:36 */    (localVector3f = new Vector3f(this.jdField_a_of_type_JavaxVecmathVector3f)).scale((float)(paramxq.a() * 10.0D));
/* 37:   */    
/* 38:38 */    this.b.set(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform);
/* 39:39 */    this.b.origin.add(localVector3f);
/* 40:   */    
/* 41:41 */    a(this.b);
/* 42:   */  }
/* 43:   */  
/* 45:   */  public final void b(xq paramxq)
/* 46:   */  {
/* 47:47 */    c(paramxq);
/* 48:   */  }
/* 49:   */  
/* 52:   */  public final byte a()
/* 53:   */  {
/* 54:54 */    return 1;
/* 55:   */  }
/* 56:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     lk
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */