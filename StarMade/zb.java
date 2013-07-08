/*   1:    */import java.nio.FloatBuffer;
/*   2:    */import javax.vecmath.Vector3f;
/*   3:    */import javax.vecmath.Vector4f;
/*   4:    */import org.lwjgl.BufferUtils;
/*   5:    */import org.lwjgl.opengl.GL11;
/*   6:    */import org.lwjgl.util.vector.Matrix4f;
/*   7:    */import org.schema.common.FastMath;
/*   8:    */import org.schema.schine.graphicsengine.camera.Camera;
/*   9:    */import org.schema.schine.graphicsengine.core.GlUtil;
/*  10:    */
/*  18:    */public class zb
/*  19:    */  extends yX
/*  20:    */  implements zr
/*  21:    */{
/*  22:    */  static
/*  23:    */  {
/*  24: 24 */    jdField_b_of_type_Boolean = !zb.class.desiredAssertionStatus();
/*  25:    */  }
/*  26:    */  
/*  33: 33 */  private zj jdField_a_of_type_Zj = zk.h;
/*  34:    */  private static zC jdField_a_of_type_ZC;
/*  35:    */  private int jdField_a_of_type_Int;
/*  36: 36 */  private static Matrix4f[] jdField_a_of_type_ArrayOfOrgLwjglUtilVectorMatrix4f; private static FloatBuffer c = BufferUtils.createFloatBuffer(16);
/*  37:    */  
/*  38: 38 */  public zb(yW paramyW) { super(paramyW, 0.1F);
/*  39:    */    
/*  40: 40 */    if ((!jdField_b_of_type_Boolean) && (zk.h == null)) throw new AssertionError();
/*  41: 41 */    jdField_a_of_type_ArrayOfOrgLwjglUtilVectorMatrix4f = new Matrix4f[300];
/*  42: 42 */    for (paramyW = 0; paramyW < jdField_a_of_type_ArrayOfOrgLwjglUtilVectorMatrix4f.length; paramyW++) {
/*  43: 43 */      jdField_a_of_type_ArrayOfOrgLwjglUtilVectorMatrix4f[paramyW] = new Matrix4f();
/*  44: 44 */      jdField_a_of_type_ArrayOfOrgLwjglUtilVectorMatrix4f[paramyW].setIdentity();
/*  45:    */    }
/*  46:    */  }
/*  47:    */  
/*  57:    */  public final void b()
/*  58:    */  {
/*  59: 59 */    if (this.jdField_a_of_type_YW.b() > 0) {
/*  60: 60 */      this.jdField_a_of_type_Zj.a = this;
/*  61: 61 */      this.jdField_a_of_type_Zj.b();
/*  62: 62 */      super.b();
/*  63: 63 */      this.jdField_a_of_type_Zj.d();
/*  64:    */    }
/*  65:    */  }
/*  66:    */  
/*  71:    */  protected final int a(int paramInt, yV paramyV)
/*  72:    */  {
/*  73: 73 */    paramyV.a(paramInt, this.jdField_a_of_type_JavaxVecmathVector3f);
/*  74: 74 */    if (!xe.a().a(this.jdField_a_of_type_JavaxVecmathVector3f)) {
/*  75: 75 */      return 0;
/*  76:    */    }
/*  77:    */    
/*  78: 78 */    this.jdField_a_of_type_JavaxVecmathVector4f.set(this.jdField_a_of_type_JavaxVecmathVector3f.x, this.jdField_a_of_type_JavaxVecmathVector3f.y, this.jdField_a_of_type_JavaxVecmathVector3f.z, paramyV.a(paramInt));
/*  79: 79 */    paramyV.b(paramInt, this.jdField_b_of_type_JavaxVecmathVector3f);
/*  80: 80 */    this.jdField_b_of_type_JavaxVecmathVector4f.set(this.jdField_b_of_type_JavaxVecmathVector3f.x, this.jdField_b_of_type_JavaxVecmathVector3f.y, this.jdField_b_of_type_JavaxVecmathVector3f.z, 0.0F);
/*  81: 81 */    for (paramInt = 0; paramInt < 4; paramInt++) {
/*  82: 82 */      GlUtil.a(jdField_a_of_type_JavaNioFloatBuffer, this.jdField_a_of_type_JavaxVecmathVector4f);
/*  83:    */      
/*  84: 84 */      this.jdField_b_of_type_JavaxVecmathVector4f.w = jdField_a_of_type_ArrayOfFloat[paramInt];
/*  85: 85 */      GlUtil.a(jdField_b_of_type_JavaNioFloatBuffer, this.jdField_b_of_type_JavaxVecmathVector4f);
/*  86:    */    }
/*  87: 87 */    return 4;
/*  88:    */  }
/*  89:    */  
/*  90:    */  public final void d() {
/*  91: 91 */    GL11.glBindTexture(3553, 0);
/*  92:    */  }
/*  93:    */  
/*  96:    */  public final void c()
/*  97:    */  {
/*  98: 98 */    this.jdField_a_of_type_Boolean = true;
/*  99:    */    
/* 100:100 */    if (jdField_a_of_type_ZC == null) {
/* 101:101 */      jdField_a_of_type_ZC = xe.a().a("star").a().a();
/* 102:    */    }
/* 103:103 */    super.c();
/* 104:    */  }
/* 105:    */  
/* 115:    */  public final void a(zj paramzj)
/* 116:    */  {
/* 117:117 */    c.rewind();
/* 118:118 */    jdField_a_of_type_ArrayOfOrgLwjglUtilVectorMatrix4f[FastMath.b(this.jdField_a_of_type_Int - jdField_a_of_type_ArrayOfOrgLwjglUtilVectorMatrix4f.length - 2, jdField_a_of_type_ArrayOfOrgLwjglUtilVectorMatrix4f.length - 1)].store(c);
/* 119:119 */    c.rewind();
/* 120:120 */    GlUtil.c(paramzj, "oldModelViewMatrix", c);
/* 121:    */    
/* 122:122 */    GlUtil.a(paramzj, "Param", new Vector4f(1.0F, 0.1F, 0.15F, 0.0005F));
/* 123:123 */    GL11.glBindTexture(3553, jdField_a_of_type_ZC.c);
/* 124:124 */    GlUtil.a(paramzj, "Texture", 0);
/* 125:    */    
/* 128:128 */    if ((paramzj = (za)this.jdField_a_of_type_YW).a > 0.015F)
/* 129:    */    {
/* 130:130 */      jdField_a_of_type_ArrayOfOrgLwjglUtilVectorMatrix4f[this.jdField_a_of_type_Int].load(xe.a);
/* 131:    */      
/* 132:132 */      this.jdField_a_of_type_Int = FastMath.b(this.jdField_a_of_type_Int + 1, jdField_a_of_type_ArrayOfOrgLwjglUtilVectorMatrix4f.length - 1);
/* 133:    */      
/* 134:134 */      paramzj.a = 0.0F;
/* 135:    */    }
/* 136:    */  }
/* 137:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     zb
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */