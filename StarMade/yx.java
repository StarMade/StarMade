/*   1:    */import org.schema.schine.network.client.ClientState;
/*   2:    */
/*   3:    */public class yx extends yr {
/*   4:    */  protected int a;
/*   5:    */  protected boolean a;
/*   6:    */  public javax.vecmath.Vector4f a;
/*   7:    */  public float c;
/*   8:    */  
/*   9:    */  static {
/*  10: 10 */    jdField_b_of_type_Boolean = !yx.class.desiredAssertionStatus();
/*  11:    */  }
/*  12:    */  
/*  17:    */  public yx(ClientState paramClientState, float paramFloat1, float paramFloat2, javax.vecmath.Vector4f paramVector4f)
/*  18:    */  {
/*  19: 19 */    super(paramClientState, paramFloat1, paramFloat2);
/*  20: 20 */    this.jdField_a_of_type_JavaxVecmathVector4f = paramVector4f;
/*  21:    */  }
/*  22:    */  
/*  29:    */  public void b()
/*  30:    */  {
/*  31: 31 */    if (!this.jdField_a_of_type_Boolean) {
/*  32: 32 */      f();
/*  33:    */    }
/*  34: 34 */    org.schema.schine.graphicsengine.core.GlUtil.d();
/*  35: 35 */    r();
/*  36: 36 */    org.lwjgl.opengl.GL11.glBlendFunc(770, 771);
/*  37: 37 */    org.lwjgl.opengl.GL11.glEnable(3042);
/*  38: 38 */    org.lwjgl.opengl.GL11.glDisable(2929);
/*  39: 39 */    org.lwjgl.opengl.GL11.glDisable(2896);
/*  40: 40 */    org.lwjgl.opengl.GL11.glEnable(2903);
/*  41: 41 */    org.lwjgl.opengl.GL11.glDisable(3553);
/*  42:    */    
/*  43: 43 */    org.schema.schine.graphicsengine.core.GlUtil.a(this.jdField_a_of_type_JavaxVecmathVector4f.x, this.jdField_a_of_type_JavaxVecmathVector4f.y, this.jdField_a_of_type_JavaxVecmathVector4f.z, this.jdField_a_of_type_JavaxVecmathVector4f.w);
/*  44: 44 */    if ((!jdField_b_of_type_Boolean) && (!this.jdField_a_of_type_Boolean)) throw new AssertionError();
/*  45: 45 */    org.lwjgl.opengl.GL11.glCallList(this.jdField_a_of_type_Int);
/*  46: 46 */    org.lwjgl.opengl.GL11.glDisable(2903);
/*  47:    */    
/*  48: 48 */    org.schema.schine.graphicsengine.core.GlUtil.a(1.0F, 1.0F, 1.0F, 1.0F);
/*  49: 49 */    org.lwjgl.opengl.GL11.glDisable(3042);
/*  50: 50 */    org.lwjgl.opengl.GL11.glEnable(2929);
/*  51: 51 */    org.schema.schine.graphicsengine.core.GlUtil.c();
/*  52:    */    
/*  53: 53 */    super.b();
/*  54:    */  }
/*  55:    */  
/*  56:    */  public final void e()
/*  57:    */  {
/*  58: 58 */    super.b();
/*  59:    */  }
/*  60:    */  
/*  61:    */  protected void f()
/*  62:    */  {
/*  63: 63 */    if (this.jdField_a_of_type_Int != 0) {
/*  64: 64 */      org.lwjgl.opengl.GL11.glDeleteLists(this.jdField_a_of_type_Int, 1);
/*  65:    */    }
/*  66: 66 */    this.jdField_a_of_type_Int = org.lwjgl.opengl.GL11.glGenLists(1);
/*  67:    */    
/*  68: 68 */    org.lwjgl.opengl.GL11.glNewList(this.jdField_a_of_type_Int, 4864);
/*  69:    */    
/*  70: 70 */    if (this.c == 0.0F) {
/*  71: 71 */      org.lwjgl.opengl.GL11.glBegin(7);
/*  72: 72 */      org.lwjgl.opengl.GL11.glVertex2f(0.0F, 0.0F);
/*  73: 73 */      org.lwjgl.opengl.GL11.glVertex2f(0.0F, a());
/*  74: 74 */      org.lwjgl.opengl.GL11.glVertex2f(b(), a());
/*  75: 75 */      org.lwjgl.opengl.GL11.glVertex2f(b(), 0.0F);
/*  76:    */    }
/*  77:    */    else {
/*  78: 78 */      org.lwjgl.opengl.GL11.glBegin(9);
/*  79: 79 */      org.lwjgl.opengl.GL11.glVertex2f(0.0F, this.c);
/*  80: 80 */      org.lwjgl.opengl.GL11.glVertex2f(0.0F, a() - this.c);
/*  81: 81 */      org.lwjgl.opengl.GL11.glVertex2f(this.c, a());
/*  82: 82 */      org.lwjgl.opengl.GL11.glVertex2f(b() - this.c, a());
/*  83: 83 */      org.lwjgl.opengl.GL11.glVertex2f(b(), a() - this.c);
/*  84: 84 */      org.lwjgl.opengl.GL11.glVertex2f(b(), this.c);
/*  85: 85 */      org.lwjgl.opengl.GL11.glVertex2f(b() - this.c, 0.0F);
/*  86: 86 */      org.lwjgl.opengl.GL11.glVertex2f(this.c, 0.0F);
/*  87: 87 */      org.lwjgl.opengl.GL11.glVertex2f(this.c, this.c);
/*  88:    */    }
/*  89:    */    
/*  90: 90 */    org.lwjgl.opengl.GL11.glEnd();
/*  91: 91 */    org.lwjgl.opengl.GL11.glEndList();
/*  92:    */    
/*  93: 93 */    this.jdField_a_of_type_Boolean = true;
/*  94:    */  }
/*  95:    */  
/*  98:    */  public void c()
/*  99:    */  {
/* 100:100 */    super.c();
/* 101:101 */    f();
/* 102:    */  }
/* 103:    */  
/* 104:104 */  public final void b(int paramInt) { this.jdField_a_of_type_Float = paramInt;
/* 105:105 */    this.jdField_a_of_type_Boolean = false;
/* 106:    */  }
/* 107:    */  
/* 108:108 */  public final void c(int paramInt) { this.jdField_b_of_type_Float = paramInt;
/* 109:109 */    this.jdField_a_of_type_Boolean = false;
/* 110:    */  }
/* 111:    */  
/* 113:    */  public final javax.vecmath.Vector4f a()
/* 114:    */  {
/* 115:115 */    return this.jdField_a_of_type_JavaxVecmathVector4f;
/* 116:    */  }
/* 117:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     yx
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */