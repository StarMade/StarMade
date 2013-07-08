/*   1:    */import com.bulletphysics.linearmath.Transform;
/*   2:    */import javax.vecmath.Vector3f;
/*   3:    */import org.schema.game.common.controller.SegmentController;
/*   4:    */import org.schema.schine.graphicsengine.core.GlUtil;
/*   5:    */
/*  19:    */public final class et
/*  20:    */  implements xg
/*  21:    */{
/*  22:    */  private zd jdField_a_of_type_Zd;
/*  23:    */  eK jdField_a_of_type_EK;
/*  24: 24 */  private boolean jdField_a_of_type_Boolean = true;
/*  25:    */  ld jdField_a_of_type_Ld;
/*  26:    */  
/*  27:    */  public et(ld paramld)
/*  28:    */  {
/*  29: 29 */    this.jdField_a_of_type_Ld = paramld;
/*  30: 30 */    this.jdField_a_of_type_EK = new eK();
/*  31:    */    
/*  32: 32 */    this.jdField_a_of_type_Zd = new zd(this.jdField_a_of_type_EK, 8.0F);
/*  33:    */  }
/*  34:    */  
/*  36:    */  public final void a() {}
/*  37:    */  
/*  39:    */  public final void b()
/*  40:    */  {
/*  41: 41 */    if (this.jdField_a_of_type_Boolean) {
/*  42: 42 */      c();
/*  43:    */    }
/*  44: 44 */    GlUtil.d();
/*  45: 45 */    GlUtil.b(this.jdField_a_of_type_Ld.a().getWorldTransformClient());
/*  46:    */    
/*  47: 47 */    this.jdField_a_of_type_Zd.a.set(this.jdField_a_of_type_Ld.a().getWorldTransformClient());
/*  48: 48 */    this.jdField_a_of_type_Zd.b();
/*  49: 49 */    GlUtil.c();
/*  50:    */  }
/*  51:    */  
/*  57:    */  public final void c()
/*  58:    */  {
/*  59: 59 */    if (this.jdField_a_of_type_Zd.a == null) {
/*  60: 60 */      this.jdField_a_of_type_Zd.a = new Transform();
/*  61:    */    }
/*  62: 62 */    this.jdField_a_of_type_Zd.c();
/*  63:    */    
/*  64: 64 */    this.jdField_a_of_type_Boolean = false;
/*  65:    */  }
/*  66:    */  
/*  79: 79 */  Vector3f jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/*  80:    */  
/*  99: 99 */  q jdField_a_of_type_Q = new q();
/* 100:    */  
/* 107:    */  public final boolean a()
/* 108:    */  {
/* 109:109 */    return this.jdField_a_of_type_EK.b() > 0;
/* 110:    */  }
/* 111:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     et
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */