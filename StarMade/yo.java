/*  1:   */import javax.vecmath.Vector4f;
/*  2:   */
/*  7:   */public final class yo
/*  8:   */  implements yn
/*  9:   */{
/* 10:10 */  private final Vector4f jdField_a_of_type_JavaxVecmathVector4f = new Vector4f();
/* 11:   */  private float jdField_a_of_type_Float;
/* 12:   */  private float jdField_b_of_type_Float;
/* 13:   */  
/* 14:   */  static {
/* 15:15 */    jdField_a_of_type_ZI = new zI(25.0F);
/* 16:   */  }
/* 17:   */  
/* 18:   */  public yo(String paramString, Vector4f paramVector4f)
/* 19:   */  {
/* 20:20 */    this.jdField_b_of_type_JavaxVecmathVector4f = paramVector4f;
/* 21:21 */    this.jdField_a_of_type_JavaLangString = paramString;
/* 22:22 */    this.jdField_a_of_type_Float = 15.0F;
/* 23:23 */    this.jdField_b_of_type_Float = (paramString = this).jdField_a_of_type_Float;paramString.jdField_a_of_type_JavaxVecmathVector4f.set(paramString.jdField_b_of_type_JavaxVecmathVector4f);
/* 24:   */  }
/* 25:   */  
/* 26:   */  public final Vector4f a()
/* 27:   */  {
/* 28:28 */    return this.jdField_a_of_type_JavaxVecmathVector4f;
/* 29:   */  }
/* 30:   */  
/* 31:   */  public final void a(xq paramxq) {
/* 32:32 */    this.jdField_b_of_type_Float = Math.max(0.0F, this.jdField_b_of_type_Float - paramxq.a());
/* 33:   */    
/* 34:34 */    if (this.jdField_b_of_type_Float > this.jdField_a_of_type_Float - 0.25F) {
/* 35:35 */      this.jdField_a_of_type_JavaxVecmathVector4f.x = (1.0F - 0.5F * jdField_a_of_type_ZI.a());
/* 36:36 */      this.jdField_a_of_type_JavaxVecmathVector4f.y = (1.0F - 0.5F * jdField_a_of_type_ZI.a());
/* 37:   */    } else {
/* 38:38 */      this.jdField_a_of_type_JavaxVecmathVector4f.set(this.jdField_b_of_type_JavaxVecmathVector4f);
/* 39:   */    }
/* 40:40 */    if (this.jdField_b_of_type_Float < 3.0F) {
/* 41:41 */      paramxq = this.jdField_b_of_type_Float / 3.0F;
/* 42:42 */      this.jdField_a_of_type_JavaxVecmathVector4f.w = paramxq;
/* 43:   */    }
/* 44:   */  }
/* 45:   */  
/* 49:   */  private String jdField_a_of_type_JavaLangString;
/* 50:   */  
/* 53:   */  private Vector4f jdField_b_of_type_JavaxVecmathVector4f;
/* 54:   */  
/* 56:   */  public static final zI a;
/* 57:   */  
/* 59:   */  public final String toString()
/* 60:   */  {
/* 61:61 */    return this.jdField_a_of_type_JavaLangString;
/* 62:   */  }
/* 63:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     yo
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */