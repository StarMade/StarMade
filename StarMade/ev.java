/*  1:   */import com.bulletphysics.linearmath.Transform;
/*  2:   */import javax.vecmath.Vector4f;
/*  3:   */
/*  5:   */public abstract class ev
/*  6:   */{
/*  7:   */  public Transform a;
/*  8:   */  private float a;
/*  9:   */  public String a;
/* 10:   */  public Vector4f a;
/* 11:   */  
/* 12:   */  public ev(Transform paramTransform, String paramString)
/* 13:   */  {
/* 14:14 */    this.jdField_a_of_type_JavaxVecmathVector4f = null;
/* 15:   */    
/* 17:17 */    this.jdField_a_of_type_ComBulletphysicsLinearmathTransform = paramTransform;
/* 18:18 */    this.jdField_a_of_type_JavaLangString = paramString;
/* 19:   */  }
/* 20:   */  
/* 24:   */  public boolean equals(Object paramObject)
/* 25:   */  {
/* 26:26 */    return ((ev)paramObject).jdField_a_of_type_JavaLangString.equals(this.jdField_a_of_type_JavaLangString);
/* 27:   */  }
/* 28:   */  
/* 39:   */  public abstract Transform a();
/* 40:   */  
/* 50:   */  public int hashCode()
/* 51:   */  {
/* 52:52 */    return this.jdField_a_of_type_JavaLangString.hashCode();
/* 53:   */  }
/* 54:   */  
/* 55:   */  public boolean a() {
/* 56:56 */    return this.jdField_a_of_type_Float < 0.3F;
/* 57:   */  }
/* 58:   */  
/* 70:   */  public void a(xq paramxq)
/* 71:   */  {
/* 72:72 */    this.jdField_a_of_type_Float += paramxq.a();
/* 73:   */  }
/* 74:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ev
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */