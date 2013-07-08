/*   1:    */import com.bulletphysics.linearmath.Transform;
/*   2:    */import org.lwjgl.opengl.GL11;
/*   3:    */import org.lwjgl.opengl.GL13;
/*   4:    */import org.schema.schine.graphicsengine.core.GlUtil;
/*   5:    */
/*  13:    */public class yf
/*  14:    */  implements zr
/*  15:    */{
/*  16:    */  private final ye jdField_a_of_type_Ye;
/*  17:    */  private final yj jdField_a_of_type_Yj;
/*  18:    */  private boolean jdField_a_of_type_Boolean;
/*  19:    */  private Transform[] jdField_a_of_type_ArrayOfComBulletphysicsLinearmathTransform;
/*  20:    */  private int jdField_a_of_type_Int;
/*  21:    */  
/*  22:    */  public yf(ye paramye, yj paramyj)
/*  23:    */  {
/*  24: 24 */    this.jdField_a_of_type_Ye = paramye;
/*  25: 25 */    this.jdField_a_of_type_Yj = paramyj;
/*  26:    */  }
/*  27:    */  
/*  30:    */  public final ye a()
/*  31:    */  {
/*  32: 32 */    return this.jdField_a_of_type_Ye;
/*  33:    */  }
/*  34:    */  
/*  40:    */  public final void a()
/*  41:    */  {
/*  42: 42 */    if (!this.jdField_a_of_type_Boolean) {
/*  43: 43 */      yf localyf = this;this.jdField_a_of_type_Yj.a();localyf.jdField_a_of_type_Boolean = true;
/*  44:    */    }
/*  45: 45 */    zk.v.a = this;
/*  46: 46 */    zk.v.b();
/*  47: 47 */    this.jdField_a_of_type_Yj.b();
/*  48:    */  }
/*  49:    */  
/*  54:    */  public final void d()
/*  55:    */  {
/*  56: 56 */    GL13.glActiveTexture(33984);
/*  57: 57 */    GL11.glBindTexture(3553, 0);
/*  58:    */  }
/*  59:    */  
/*  60:    */  public final void b() {
/*  61: 61 */    yj.c();
/*  62: 62 */    zk.v.d();
/*  63:    */  }
/*  64:    */  
/*  68:    */  public final void a(xq paramxq)
/*  69:    */  {
/*  70: 70 */    this.jdField_a_of_type_Ye.b(paramxq);
/*  71: 71 */    this.jdField_a_of_type_ArrayOfComBulletphysicsLinearmathTransform = this.jdField_a_of_type_Ye.a();
/*  72:    */  }
/*  73:    */  
/*  80:    */  public final void a(zj paramzj)
/*  81:    */  {
/*  82: 82 */    if ((!b) && (this.jdField_a_of_type_ArrayOfComBulletphysicsLinearmathTransform == null)) throw new AssertionError();
/*  83: 83 */    GlUtil.a(paramzj, "m_BoneMatrices", this.jdField_a_of_type_ArrayOfComBulletphysicsLinearmathTransform);
/*  84:    */    
/*  91: 91 */    GL13.glActiveTexture(33984);
/*  92: 92 */    GL11.glBindTexture(3553, this.jdField_a_of_type_Int);
/*  93: 93 */    GlUtil.a(paramzj, "diffuseMap", 0);
/*  94:    */    
/*  99: 99 */    GL13.glActiveTexture(33984);
/* 100:    */  }
/* 101:    */  
/* 112:    */  public final void a(int paramInt)
/* 113:    */  {
/* 114:114 */    this.jdField_a_of_type_Int = paramInt;
/* 115:    */  }
/* 116:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     yf
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */