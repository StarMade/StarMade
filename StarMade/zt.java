/*  1:   */import javax.vecmath.Vector4f;
/*  2:   */import org.lwjgl.opengl.GL11;
/*  3:   */import org.lwjgl.opengl.GL13;
/*  4:   */import org.schema.schine.graphicsengine.core.GlUtil;
/*  5:   */
/*  7:   */public final class zt
/*  8:   */  implements zr
/*  9:   */{
/* 10:   */  public int a;
/* 11:   */  public Vector4f a;
/* 12:   */  
/* 13:   */  public zt()
/* 14:   */  {
/* 15:15 */    this.jdField_a_of_type_JavaxVecmathVector4f = new Vector4f(0.0F, 0.0F, 0.0F, 1.0F);
/* 16:   */  }
/* 17:   */  
/* 22:   */  public final void d()
/* 23:   */  {
/* 24:24 */    GL13.glActiveTexture(33984);
/* 25:25 */    GL11.glBindTexture(3553, 0);
/* 26:   */  }
/* 27:   */  
/* 37:   */  public final void a(zj paramzj)
/* 38:   */  {
/* 39:39 */    GlUtil.a(paramzj, "silhouetteColor", this.jdField_a_of_type_JavaxVecmathVector4f);
/* 40:   */    
/* 41:41 */    GL13.glActiveTexture(33984);
/* 42:42 */    GL11.glBindTexture(3553, this.jdField_a_of_type_Int);
/* 43:43 */    GlUtil.a(paramzj, "silhouetteTexture", 0);
/* 44:   */  }
/* 45:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     zt
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */