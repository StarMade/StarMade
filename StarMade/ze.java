/*   1:    */import java.io.IOException;
/*   2:    */import org.lwjgl.opengl.GL11;
/*   3:    */import org.schema.schine.graphicsengine.core.GlUtil;
/*   4:    */
/*  23:    */public class ze
/*  24:    */  extends zf
/*  25:    */  implements zr
/*  26:    */{
/*  27:    */  private zj jdField_a_of_type_Zj;
/*  28:    */  private boolean jdField_b_of_type_Boolean;
/*  29:    */  private static zC jdField_a_of_type_ZC;
/*  30: 30 */  private float jdField_b_of_type_Float = 0.0F;
/*  31:    */  
/*  32:    */  public ze() {
/*  33: 33 */    super((byte)0);
/*  34: 34 */    this.jdField_a_of_type_Zj = zk.y;
/*  35: 35 */    if ((!c) && (zk.y == null)) { throw new AssertionError();
/*  36:    */    }
/*  37:    */  }
/*  38:    */  
/*  45:    */  public final void b()
/*  46:    */  {
/*  47: 47 */    if (!this.jdField_b_of_type_Boolean) {
/*  48: 48 */      c();
/*  49:    */    }
/*  50: 50 */    if (this.jdField_a_of_type_YW.b() > 1) {
/*  51: 51 */      this.jdField_a_of_type_Zj.a = this;
/*  52: 52 */      this.jdField_a_of_type_Zj.b();
/*  53: 53 */      super.b();
/*  54: 54 */      this.jdField_a_of_type_Zj.d();
/*  55:    */    }
/*  56:    */  }
/*  57:    */  
/*  59:    */  public final void d()
/*  60:    */  {
/*  61: 61 */    GL11.glBindTexture(32879, 0);
/*  62:    */  }
/*  63:    */  
/*  71:    */  public final void c()
/*  72:    */  {
/*  73: 73 */    this.jdField_a_of_type_Boolean = false;
/*  74:    */    
/*  75: 75 */    if (jdField_a_of_type_ZC == null) {
/*  76:    */      try {
/*  77: 77 */        jdField_a_of_type_ZC = xe.a().a("data/./effects/noise.jpg", true);
/*  78: 78 */      } catch (IOException localIOException) { 
/*  79:    */        
/*  80: 80 */          localIOException;
/*  81:    */      }
/*  82:    */    }
/*  83:    */    
/*  84: 82 */    super.c();
/*  85: 83 */    this.jdField_b_of_type_Boolean = true;
/*  86:    */  }
/*  87:    */  
/*  88:    */  public final void a(xq paramxq) {
/*  89: 87 */    this.jdField_b_of_type_Float += paramxq.a();
/*  90: 88 */    while (this.jdField_b_of_type_Float > 1.0F) {
/*  91: 89 */      this.jdField_b_of_type_Float -= 1.0F;
/*  92:    */    }
/*  93:    */  }
/*  94:    */  
/* 100:    */  public final void a(zj paramzj)
/* 101:    */  {
/* 102:100 */    GlUtil.a(paramzj, "time", this.jdField_b_of_type_Float);
/* 103:101 */    GL11.glBindTexture(3553, jdField_a_of_type_ZC.c);
/* 104:102 */    GlUtil.a(paramzj, "tex", 0);
/* 105:    */  }
/* 106:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ze
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */