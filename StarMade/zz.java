/*   1:    */import javax.vecmath.Vector3f;
/*   2:    */import org.lwjgl.opengl.GL11;
/*   3:    */import org.lwjgl.opengl.GL13;
/*   4:    */import org.schema.schine.graphicsengine.core.GlUtil;
/*   5:    */
/*  21:    */public class zz
/*  22:    */  implements zr
/*  23:    */{
/*  24: 24 */  private static int jdField_a_of_type_Int = 33992;
/*  25: 25 */  private static int jdField_b_of_type_Int = 8;
/*  26:    */  
/*  27: 27 */  private static int c = 33991;
/*  28: 28 */  private static int d = 7;
/*  29:    */  
/*  30: 30 */  private static int e = 33990;
/*  31: 31 */  private static int f = 6;
/*  32:    */  
/*  33: 33 */  private int g = -1;
/*  34: 34 */  public zz() { this.jdField_a_of_type_JavaxVecmathVector3f = new Vector3f(); }
/*  35:    */  
/*  38:    */  public Vector3f a;
/*  39:    */  
/*  41:    */  private zC jdField_a_of_type_ZC;
/*  42:    */  
/*  43:    */  private float jdField_a_of_type_Float;
/*  44:    */  
/*  45:    */  private float jdField_b_of_type_Float;
/*  46:    */  
/*  47:    */  private xi jdField_a_of_type_Xi;
/*  48:    */  
/*  49:    */  public final void d()
/*  50:    */  {
/*  51: 51 */    GL13.glActiveTexture(c);
/*  52: 52 */    GL11.glBindTexture(3553, 0);
/*  53:    */    
/*  54: 54 */    GL13.glActiveTexture(jdField_a_of_type_Int);
/*  55: 55 */    GL11.glBindTexture(3553, 0);
/*  56:    */    
/*  57: 57 */    GL13.glActiveTexture(e);
/*  58: 58 */    GL11.glBindTexture(3553, 0);
/*  59:    */    
/*  60: 60 */    GL13.glActiveTexture(33984);
/*  61:    */  }
/*  62:    */  
/*  67:    */  public final void a(int paramInt, xi paramxi)
/*  68:    */  {
/*  69: 69 */    this.g = paramInt;
/*  70: 70 */    this.jdField_a_of_type_Xi = paramxi;
/*  71:    */  }
/*  72:    */  
/*  73:    */  public final boolean a() {
/*  74: 74 */    this.jdField_a_of_type_Float = (this.jdField_a_of_type_JavaxVecmathVector3f.x / xm.b());
/*  75: 75 */    this.jdField_b_of_type_Float = (1.0F - this.jdField_a_of_type_JavaxVecmathVector3f.y / xm.a());
/*  76:    */    
/*  77: 77 */    return true;
/*  78:    */  }
/*  79:    */  
/*  87:    */  public final void a(zj paramzj)
/*  88:    */  {
/*  89: 89 */    if (this.jdField_a_of_type_ZC == null) {
/*  90: 90 */      this.jdField_a_of_type_ZC = xe.a().a("lens_flare").a().a();
/*  91:    */    }
/*  92:    */    
/* 100:100 */    if ((!jdField_a_of_type_Boolean) && (this.g < 0)) { throw new AssertionError();
/* 101:    */    }
/* 102:102 */    GlUtil.a(paramzj, "screenRatio", xm.a() / xm.b());
/* 103:    */    
/* 110:110 */    GlUtil.a(paramzj, "lightPositionOnScreen", this.jdField_a_of_type_Float, this.jdField_b_of_type_Float);
/* 111:    */    
/* 118:118 */    GlUtil.a(paramzj, "Param1", this.jdField_a_of_type_Float, this.jdField_b_of_type_Float, 1.0F / this.jdField_a_of_type_Xi.jdField_b_of_type_Int, 0.5F / this.jdField_a_of_type_ZC.jdField_b_of_type_Int);
/* 119:119 */    GlUtil.a(paramzj, "Param2", 1.0F, 0.2F, 1.0F, 1.0F);
/* 120:    */    
/* 121:121 */    GL13.glActiveTexture(c);
/* 122:122 */    GL11.glBindTexture(3553, this.g);
/* 123:123 */    GlUtil.a(paramzj, "firstPass", d);
/* 124:    */    
/* 125:125 */    GL13.glActiveTexture(jdField_a_of_type_Int);
/* 126:126 */    GL11.glBindTexture(3553, this.jdField_a_of_type_ZC.c);
/* 127:127 */    GlUtil.a(paramzj, "Texture", jdField_b_of_type_Int);
/* 128:    */    
/* 129:129 */    GL13.glActiveTexture(e);
/* 130:130 */    GL11.glBindTexture(3553, this.jdField_a_of_type_Xi.jdField_a_of_type_Int);
/* 131:131 */    GlUtil.a(paramzj, "Scene", f);
/* 132:    */    
/* 137:137 */    GL13.glActiveTexture(33984);
/* 138:    */  }
/* 139:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     zz
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */