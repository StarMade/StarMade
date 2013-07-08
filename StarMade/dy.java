/*   1:    */import com.bulletphysics.linearmath.Transform;
/*   2:    */import javax.vecmath.Matrix3f;
/*   3:    */import javax.vecmath.Tuple3f;
/*   4:    */import javax.vecmath.Vector3f;
/*   5:    */import org.schema.game.common.controller.SegmentController;
/*   6:    */
/*  13:    */public class dy
/*  14:    */  extends xb
/*  15:    */{
/*  16:    */  protected q a;
/*  17: 17 */  private Vector3f jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/*  18:    */  protected SegmentController a;
/*  19:    */  protected al a;
/*  20: 20 */  private Transform jdField_b_of_type_ComBulletphysicsLinearmathTransform = new Transform();
/*  21: 21 */  private float jdField_a_of_type_Float = 13.0F;
/*  22:    */  
/*  23: 23 */  private Vector3f jdField_b_of_type_JavaxVecmathVector3f = new Vector3f();
/*  24:    */  
/*  27:    */  public dy(al paramal)
/*  28:    */  {
/*  29: 29 */    super(paramal.a());this.jdField_a_of_type_Q = new q();new Vector3f();new Vector3f();new q();new o();
/*  30: 30 */    this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController = paramal.a();
/*  31: 31 */    this.jdField_a_of_type_Al = paramal;
/*  32:    */  }
/*  33:    */  
/*  44:    */  public final synchronized Vector3f a()
/*  45:    */  {
/*  46: 46 */    Vector3f localVector3f = super.a();
/*  47:    */    
/*  48: 48 */    Object localObject = this;localObject = new Vector3f(((dy)localObject).jdField_a_of_type_JavaxVecmathVector3f.x + ((dy)localObject).jdField_a_of_type_Al.a().a - 8.0F, ((dy)localObject).jdField_a_of_type_JavaxVecmathVector3f.y + ((dy)localObject).jdField_a_of_type_Al.a().b - 8.0F, ((dy)localObject).jdField_a_of_type_JavaxVecmathVector3f.z + ((dy)localObject).jdField_a_of_type_Al.a().c - 8.0F);
/*  49:    */    
/*  52: 52 */    this.jdField_b_of_type_ComBulletphysicsLinearmathTransform.set(this.a.getWorldTransform());
/*  53:    */    
/*  54: 54 */    this.jdField_b_of_type_ComBulletphysicsLinearmathTransform.basis.transform((Tuple3f)localObject);
/*  55: 55 */    localVector3f.add((Tuple3f)localObject);
/*  56: 56 */    return localVector3f;
/*  57:    */  }
/*  58:    */  
/*  59: 59 */  public final q a() { return this.jdField_a_of_type_Q; }
/*  60:    */  
/*  77:    */  public final void a()
/*  78:    */  {
/*  79: 79 */    this.jdField_a_of_type_Float = 50.0F;
/*  80:    */  }
/*  81:    */  
/*  82:    */  public final void a(xq paramxq) {
/*  83: 83 */    this.jdField_b_of_type_JavaxVecmathVector3f.set(this.jdField_a_of_type_Q.a, this.jdField_a_of_type_Q.b, this.jdField_a_of_type_Q.c);
/*  84: 84 */    this.jdField_b_of_type_JavaxVecmathVector3f.sub(this.jdField_a_of_type_JavaxVecmathVector3f);
/*  85:    */    
/*  86:    */    float f1;
/*  87: 87 */    if ((f1 = this.jdField_b_of_type_JavaxVecmathVector3f.length()) <= 0.0F) {
/*  88: 88 */      return;
/*  89:    */    }
/*  90: 90 */    float f2 = this.jdField_b_of_type_JavaxVecmathVector3f.length();
/*  91: 91 */    this.jdField_b_of_type_JavaxVecmathVector3f.normalize();
/*  92:    */    
/*  93: 93 */    this.jdField_b_of_type_JavaxVecmathVector3f.scale(paramxq.a() * Math.max(f2 * 3.0F, this.jdField_a_of_type_Float));
/*  94:    */    
/*  97: 97 */    if (this.jdField_b_of_type_JavaxVecmathVector3f.length() < f1)
/*  98:    */    {
/*  99: 99 */      this.jdField_a_of_type_JavaxVecmathVector3f.add(this.jdField_b_of_type_JavaxVecmathVector3f);return;
/* 100:    */    }
/* 101:    */    
/* 104:104 */    this.jdField_a_of_type_JavaxVecmathVector3f.set(this.jdField_a_of_type_Q.a, this.jdField_a_of_type_Q.b, this.jdField_a_of_type_Q.c);
/* 105:    */  }
/* 106:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     dy
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */