/*  1:   */import javax.vecmath.Vector3f;
/*  2:   */import javax.vecmath.Vector4f;
/*  3:   */import org.lwjgl.opengl.GL11;
/*  4:   */import org.schema.schine.graphicsengine.core.GlUtil;
/*  5:   */
/* 18:   */public final class ym
/* 19:   */  extends yl
/* 20:   */{
/* 21:   */  private Vector3f a;
/* 22:   */  
/* 23:   */  public ym(Vector3f paramVector3f, Vector4f paramVector4f)
/* 24:   */  {
/* 25:25 */    super((byte)0);
/* 26:26 */    this.jdField_a_of_type_JavaxVecmathVector3f = paramVector3f;
/* 27:27 */    this.jdField_a_of_type_JavaxVecmathVector4f = paramVector4f;
/* 28:   */  }
/* 29:   */  
/* 30:   */  public final void a() {
/* 31:31 */    GL11.glDisable(3553);
/* 32:32 */    GL11.glEnable(2903);
/* 33:33 */    GL11.glDisable(2896);
/* 34:34 */    GL11.glEnable(3042);
/* 35:35 */    GL11.glBlendFunc(770, 771);
/* 36:   */    
/* 37:37 */    if (this.jdField_a_of_type_JavaxVecmathVector4f != null) {
/* 38:38 */      GlUtil.a(this.jdField_a_of_type_JavaxVecmathVector4f.x, this.jdField_a_of_type_JavaxVecmathVector4f.y, this.jdField_a_of_type_JavaxVecmathVector4f.z, this.jdField_a_of_type_JavaxVecmathVector4f.w * a());
/* 39:   */    } else {
/* 40:40 */      GlUtil.a(1.0F, 1.0F, 1.0F, a());
/* 41:   */    }
/* 42:42 */    GL11.glBegin(1);
/* 43:43 */    GL11.glVertex3f(this.jdField_a_of_type_JavaxVecmathVector3f.x - this.jdField_a_of_type_Float, this.jdField_a_of_type_JavaxVecmathVector3f.y, this.jdField_a_of_type_JavaxVecmathVector3f.z);
/* 44:44 */    GL11.glVertex3f(this.jdField_a_of_type_JavaxVecmathVector3f.x + this.jdField_a_of_type_Float, this.jdField_a_of_type_JavaxVecmathVector3f.y, this.jdField_a_of_type_JavaxVecmathVector3f.z);
/* 45:   */    
/* 46:46 */    GL11.glVertex3f(this.jdField_a_of_type_JavaxVecmathVector3f.x, this.jdField_a_of_type_JavaxVecmathVector3f.y - this.jdField_a_of_type_Float, this.jdField_a_of_type_JavaxVecmathVector3f.z);
/* 47:47 */    GL11.glVertex3f(this.jdField_a_of_type_JavaxVecmathVector3f.x, this.jdField_a_of_type_JavaxVecmathVector3f.y + this.jdField_a_of_type_Float, this.jdField_a_of_type_JavaxVecmathVector3f.z);
/* 48:   */    
/* 49:49 */    GL11.glVertex3f(this.jdField_a_of_type_JavaxVecmathVector3f.x, this.jdField_a_of_type_JavaxVecmathVector3f.y, this.jdField_a_of_type_JavaxVecmathVector3f.z - this.jdField_a_of_type_Float);
/* 50:50 */    GL11.glVertex3f(this.jdField_a_of_type_JavaxVecmathVector3f.x, this.jdField_a_of_type_JavaxVecmathVector3f.y, this.jdField_a_of_type_JavaxVecmathVector3f.z + this.jdField_a_of_type_Float);
/* 51:51 */    GL11.glEnd();
/* 52:   */    
/* 53:53 */    GL11.glDisable(3042);
/* 54:54 */    GL11.glDisable(2903);
/* 55:55 */    GL11.glEnable(2896);
/* 56:56 */    GL11.glEnable(3553);
/* 57:   */  }
/* 58:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ym
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */