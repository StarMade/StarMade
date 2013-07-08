/*   1:    */import javax.vecmath.Vector3f;
/*   2:    */import javax.vecmath.Vector4f;
/*   3:    */import org.lwjgl.opengl.GL11;
/*   4:    */
/*  61:    */public final class yb
/*  62:    */  extends ya
/*  63:    */{
/*  64: 64 */  private Vector4f jdField_a_of_type_JavaxVecmathVector4f = new Vector4f(0.5F, 0.5F, 0.5F, 0.7F);
/*  65:    */  
/*  68:    */  private xK jdField_a_of_type_XK;
/*  69:    */  
/*  72:    */  private float jdField_a_of_type_Float;
/*  73:    */  
/*  76:    */  private float b;
/*  77:    */  
/*  79:    */  private float c;
/*  80:    */  
/*  83:    */  public yb(xK paramxK)
/*  84:    */  {
/*  85: 85 */    this.c = 300.0F;
/*  86: 86 */    this.jdField_a_of_type_Float = 1.4F;
/*  87:    */    
/*  88: 88 */    this.b = 0.2F;
/*  89: 89 */    this.jdField_a_of_type_XK = paramxK;
/*  90:    */  }
/*  91:    */  
/*  96:    */  public final void b()
/*  97:    */  {
/*  98: 98 */    GL11.glDisable(2929);
/*  99: 99 */    GL11.glDisable(2896);
/* 100:100 */    GL11.glEnable(3042);
/* 101:101 */    GL11.glEnable(2903);
/* 102:    */    
/* 103:    */    yg localyg;
/* 104:104 */    (localyg = xe.a().a("box")).a(a().x, a().y, a().z);
/* 105:105 */    localyg.b(false);
/* 106:106 */    localyg.a(true);
/* 107:107 */    localyg.a.set(this.jdField_a_of_type_Float, this.b, 1.0F);
/* 108:108 */    localyg.c(this.jdField_a_of_type_JavaxVecmathVector4f);
/* 109:109 */    localyg.b();
/* 110:    */    
/* 111:111 */    this.jdField_a_of_type_XK.a.set(1.0F, 1.0F, 1.0F);
/* 112:112 */    this.jdField_a_of_type_XK.a(a().x, a().y, a().z);
/* 113:113 */    this.jdField_a_of_type_XK.a.jdField_a_of_type_Float = (-(this.c / 2.0F) + 9.0F);
/* 114:114 */    this.jdField_a_of_type_XK.a.b = (0.0F - this.jdField_a_of_type_XK.a() - 3.0F);
/* 115:115 */    this.jdField_a_of_type_XK.d();
/* 116:116 */    this.jdField_a_of_type_XK.b();
/* 117:    */    
/* 119:119 */    GL11.glDisable(3042);
/* 120:120 */    GL11.glDisable(2903);
/* 121:121 */    GL11.glEnable(2929);
/* 122:122 */    GL11.glEnable(2896);
/* 123:    */  }
/* 124:    */  
/* 125:    */  public final void c() {}
/* 126:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     yb
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */