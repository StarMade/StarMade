/*   1:    */import java.util.Random;
/*   2:    */import javax.vecmath.Vector3f;
/*   3:    */import org.schema.schine.graphicsengine.camera.Camera;
/*   4:    */
/*  16:    */public final class za
/*  17:    */  extends yW
/*  18:    */{
/*  19: 19 */  private float jdField_b_of_type_Float = 30.0F;
/*  20: 20 */  private float jdField_c_of_type_Float = 30.0F;
/*  21: 21 */  private float d = this.jdField_c_of_type_Float * this.jdField_c_of_type_Float;
/*  22: 22 */  private float e = 3.3F;
/*  23: 23 */  private Random jdField_a_of_type_JavaUtilRandom = new Random();
/*  24: 24 */  float jdField_a_of_type_Float = 0.0F;
/*  25:    */  
/*  27:    */  private float f;
/*  28:    */  
/*  30:    */  private float g;
/*  31:    */  
/*  32: 32 */  private Vector3f jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/*  33: 33 */  private Vector3f jdField_b_of_type_JavaxVecmathVector3f = new Vector3f();
/*  34: 34 */  private Vector3f jdField_c_of_type_JavaxVecmathVector3f = new Vector3f();
/*  35:    */  
/*  51:    */  public final boolean a(int paramInt, xq paramxq)
/*  52:    */  {
/*  53: 53 */    float f1 = this.f / this.g / 3.0F;
/*  54: 54 */    float f2 = this.a.a(paramInt);
/*  55: 55 */    this.a.a(paramInt, this.jdField_b_of_type_JavaxVecmathVector3f);
/*  56: 56 */    this.jdField_c_of_type_JavaxVecmathVector3f.sub(this.jdField_b_of_type_JavaxVecmathVector3f, xe.a().a());
/*  57: 57 */    if (this.jdField_c_of_type_JavaxVecmathVector3f.lengthSquared() > this.d * 2.0F) {
/*  58: 58 */      return false;
/*  59:    */    }
/*  60:    */    
/*  65: 65 */    if ((this.jdField_b_of_type_JavaxVecmathVector3f.x - xe.a().a().x) * this.jdField_a_of_type_JavaxVecmathVector3f.x + (this.jdField_b_of_type_JavaxVecmathVector3f.y - xe.a().a().y) * this.jdField_a_of_type_JavaxVecmathVector3f.y + (this.jdField_b_of_type_JavaxVecmathVector3f.z - xe.a().a().z) * this.jdField_a_of_type_JavaxVecmathVector3f.z <= 0.0D) {
/*  66: 66 */      return false;
/*  67:    */    }
/*  68: 68 */    this.a.a(paramInt, (float)(f2 + f1 * paramxq.a() * 1000.0D));
/*  69:    */    
/*  71: 71 */    return f2 < 1000.0F;
/*  72:    */  }
/*  73:    */  
/*  76:    */  public final void a(xq paramxq)
/*  77:    */  {
/*  78: 78 */    xe.a().a(this.jdField_a_of_type_JavaxVecmathVector3f);
/*  79: 79 */    super.a(paramxq);
/*  80: 80 */    this.jdField_a_of_type_Float += paramxq.a();
/*  81:    */  }
/*  82:    */  
/*  83:    */  public final void a(float paramFloat1, float paramFloat2)
/*  84:    */  {
/*  85:    */    Camera localCamera;
/*  86: 86 */    if ((localCamera = xe.a()) == null) {
/*  87: 87 */      return;
/*  88:    */    }
/*  89: 89 */    this.f = paramFloat1;
/*  90: 90 */    this.g = paramFloat2;
/*  91:    */    
/*  92: 92 */    Vector3f localVector3f1 = new Vector3f(0.0F, 0.0F, 0.0F);
/*  93: 93 */    paramFloat1 = (int)(this.e * (paramFloat1 / paramFloat2));
/*  94:    */    
/*  95: 95 */    for (paramFloat2 = 0; paramFloat2 < paramFloat1; paramFloat2++) {
/*  96: 96 */      Vector3f localVector3f2 = new Vector3f(localCamera.a());
/*  97: 97 */      Vector3f localVector3f3 = new Vector3f(localCamera.c());
/*  98: 98 */      Vector3f localVector3f4 = new Vector3f(localCamera.f());
/*  99: 99 */      Vector3f localVector3f5 = new Vector3f(localCamera.d());
/* 100:100 */      localVector3f3.scale(this.jdField_c_of_type_Float);
/* 101:101 */      localVector3f4.scale(this.jdField_a_of_type_JavaUtilRandom.nextFloat() * this.jdField_b_of_type_Float - this.jdField_b_of_type_Float / 2.0F);
/* 102:102 */      localVector3f5.scale(this.jdField_a_of_type_JavaUtilRandom.nextFloat() * this.jdField_b_of_type_Float - this.jdField_b_of_type_Float / 2.0F);
/* 103:    */      
/* 104:104 */      localVector3f2.add(localVector3f3);
/* 105:105 */      localVector3f2.add(localVector3f4);
/* 106:106 */      localVector3f2.add(localVector3f5);
/* 107:    */      
/* 108:108 */      a(localVector3f2, localVector3f1);
/* 109:    */    }
/* 110:    */  }
/* 111:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     za
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */