/*   1:    */import com.bulletphysics.linearmath.Transform;
/*   2:    */import javax.vecmath.Matrix3f;
/*   3:    */import javax.vecmath.Vector3f;
/*   4:    */import org.lwjgl.input.Keyboard;
/*   5:    */import org.schema.game.common.controller.SegmentController;
/*   6:    */
/*  17:    */public final class dx
/*  18:    */  extends xb
/*  19:    */  implements wx
/*  20:    */{
/*  21: 21 */  private Vector3f jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/*  22:    */  private SegmentController jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController;
/*  23:    */  private al jdField_a_of_type_Al;
/*  24: 24 */  private Transform jdField_b_of_type_ComBulletphysicsLinearmathTransform = new Transform();
/*  25:    */  
/*  28:    */  private float jdField_a_of_type_Float;
/*  29:    */  
/*  32:    */  private float jdField_b_of_type_Float;
/*  33:    */  
/*  37:    */  public dx(al paramal)
/*  38:    */  {
/*  39: 39 */    super(paramal.a());new Vector3f();new Vector3f();new Vector3f();this.jdField_a_of_type_Float = 25.0F;this.jdField_b_of_type_Float = 5.0F;new q();new o();
/*  40: 40 */    this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController = paramal.a();
/*  41: 41 */    this.jdField_a_of_type_Al = paramal;
/*  42:    */  }
/*  43:    */  
/*  44:    */  public final synchronized Vector3f a() {
/*  45: 45 */    Vector3f localVector3f1 = super.a();
/*  46: 46 */    Vector3f localVector3f2 = b();
/*  47:    */    
/*  49: 49 */    this.jdField_b_of_type_ComBulletphysicsLinearmathTransform.set(this.a.getWorldTransform());
/*  50: 50 */    this.jdField_b_of_type_ComBulletphysicsLinearmathTransform.basis.transform(localVector3f2);
/*  51: 51 */    localVector3f1.add(localVector3f2);
/*  52: 52 */    return localVector3f1;
/*  53:    */  }
/*  54:    */  
/*  55: 55 */  public final Vector3f b() { return new Vector3f(this.jdField_a_of_type_JavaxVecmathVector3f.x + this.jdField_a_of_type_Al.a().a - 8.0F, this.jdField_a_of_type_JavaxVecmathVector3f.y + this.jdField_a_of_type_Al.a().b - 8.0F, this.jdField_a_of_type_JavaxVecmathVector3f.z + this.jdField_a_of_type_Al.a().c - 10.0F); }
/*  56:    */  
/*  63:    */  public final void handleKeyEvent() {}
/*  64:    */  
/*  71:    */  public final void a(q paramq)
/*  72:    */  {
/*  73: 73 */    paramq.c(this.jdField_a_of_type_Al.a());
/*  74: 74 */    this.jdField_a_of_type_JavaxVecmathVector3f.set(paramq.a, paramq.b, paramq.c);
/*  75:    */  }
/*  76:    */  
/*  80:    */  public final void a(xq paramxq)
/*  81:    */  {
/*  82: 82 */    if ((!((ct)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState()).a().a().a().a().a().a().a().g()) && (!((ct)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState()).a().a().a().a().a().g()))
/*  83:    */    {
/*  88: 88 */      return;
/*  89:    */    }
/*  90: 90 */    if (((ct)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState()).a().a().a().c()) {
/*  91: 91 */      return;
/*  92:    */    }
/*  93:    */    
/*  94: 94 */    Vector3f localVector3f1 = new Vector3f(this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getCamForwLocal());
/*  95: 95 */    Vector3f localVector3f2 = new Vector3f(this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getCamUpLocal());
/*  96: 96 */    Vector3f localVector3f3 = new Vector3f(this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getCamLeftLocal());
/*  97:    */    
/* 100:100 */    float f = Keyboard.isKeyDown(42) ? this.jdField_a_of_type_Float : this.jdField_b_of_type_Float;
/* 101:101 */    localVector3f1.scale(f * paramxq.a());
/* 102:102 */    localVector3f2.scale(f * paramxq.a());
/* 103:103 */    localVector3f3.scale(f * paramxq.a());
/* 104:    */    
/* 107:107 */    if ((!cv.c.a()) || (!cv.d.a())) {
/* 108:108 */      if (cv.c.a()) {
/* 109:109 */        this.jdField_a_of_type_JavaxVecmathVector3f.add(localVector3f1);
/* 110:    */      }
/* 111:111 */      if (cv.d.a()) {
/* 112:112 */        localVector3f1.scale(-1.0F);
/* 113:113 */        this.jdField_a_of_type_JavaxVecmathVector3f.add(localVector3f1);
/* 114:    */      }
/* 115:    */    }
/* 116:116 */    if ((!cv.a.a()) || (!cv.b.a())) {
/* 117:117 */      if (cv.a.a()) {
/* 118:118 */        localVector3f3.scale(-1.0F);
/* 119:119 */        this.jdField_a_of_type_JavaxVecmathVector3f.add(localVector3f3);
/* 120:    */      }
/* 121:121 */      if (cv.b.a()) {
/* 122:122 */        this.jdField_a_of_type_JavaxVecmathVector3f.add(localVector3f3);
/* 123:    */      }
/* 124:    */    }
/* 125:125 */    if ((!cv.f.a()) || (!cv.e.a())) {
/* 126:126 */      if (cv.f.a()) {
/* 127:127 */        localVector3f2.scale(-1.0F);
/* 128:128 */        this.jdField_a_of_type_JavaxVecmathVector3f.add(localVector3f2);
/* 129:    */      }
/* 130:130 */      if (cv.e.a()) {
/* 131:131 */        this.jdField_a_of_type_JavaxVecmathVector3f.add(localVector3f2);
/* 132:    */      }
/* 133:    */    }
/* 134:    */  }
/* 135:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     dx
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */