/*   1:    */import com.bulletphysics.linearmath.Transform;
/*   2:    */import javax.vecmath.Vector3f;
/*   3:    */
/*  15:    */public final class ey
/*  16:    */  extends yW
/*  17:    */{
/*  18:    */  Transform jdField_a_of_type_ComBulletphysicsLinearmathTransform;
/*  19:    */  Transform jdField_b_of_type_ComBulletphysicsLinearmathTransform;
/*  20: 20 */  boolean jdField_a_of_type_Boolean = false;
/*  21:    */  
/*  25: 25 */  private Vector3f jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/*  26: 26 */  private Vector3f jdField_b_of_type_JavaxVecmathVector3f = new Vector3f();
/*  27: 27 */  private Vector3f c = new Vector3f();
/*  28: 28 */  private Vector3f jdField_d_of_type_JavaxVecmathVector3f = new Vector3f();
/*  29:    */  
/*  30:    */  boolean jdField_b_of_type_Boolean;
/*  31:    */  
/*  32:    */  public int a;
/*  33: 33 */  private int jdField_d_of_type_Int = 10;
/*  34:    */  
/*  35: 35 */  public ey(Transform paramTransform) { super(512);this.jdField_a_of_type_Int = -1;
/*  36: 36 */    this.c = true;
/*  37: 37 */    this.jdField_a_of_type_ComBulletphysicsLinearmathTransform = paramTransform;
/*  38:    */  }
/*  39:    */  
/*  40:    */  private void a(Vector3f paramVector3f) {
/*  41: 41 */    if (!this.jdField_b_of_type_Boolean) {
/*  42: 42 */      a(paramVector3f, this.jdField_a_of_type_JavaxVecmathVector3f);
/*  43:    */    }
/*  44:    */  }
/*  45:    */  
/*  87:    */  public final boolean a(int paramInt, xq paramxq)
/*  88:    */  {
/*  89: 89 */    float f = this.a.a(paramInt);
/*  90: 90 */    this.a.a(paramInt, this.c);
/*  91: 91 */    this.a.d(paramInt, this.jdField_d_of_type_JavaxVecmathVector3f);
/*  92: 92 */    this.a.a(paramInt, f + paramxq.a() * 1000.0F);
/*  93:    */    
/*  94: 94 */    this.a.a(paramInt, this.c.x + this.jdField_d_of_type_JavaxVecmathVector3f.x, this.c.y + this.jdField_d_of_type_JavaxVecmathVector3f.y, this.c.z + this.jdField_d_of_type_JavaxVecmathVector3f.z);
/*  95: 95 */    return f < 3000.0F;
/*  96:    */  }
/*  97:    */  
/* 101:    */  public final void a(xq paramxq)
/* 102:    */  {
/* 103:103 */    if (this.jdField_b_of_type_ComBulletphysicsLinearmathTransform == null)
/* 104:    */    {
/* 105:105 */      a(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform.origin);
/* 106:106 */      this.jdField_b_of_type_ComBulletphysicsLinearmathTransform = new Transform(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform);
/* 107:    */    }
/* 108:    */    else {
/* 109:109 */      this.jdField_b_of_type_JavaxVecmathVector3f.set(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform.origin);
/* 110:110 */      this.jdField_b_of_type_JavaxVecmathVector3f.sub(this.jdField_b_of_type_ComBulletphysicsLinearmathTransform.origin);
/* 111:    */      
/* 112:    */      float f1;
/* 113:113 */      if ((f1 = this.jdField_b_of_type_JavaxVecmathVector3f.length()) > zf.a) {
/* 114:114 */        this.jdField_b_of_type_JavaxVecmathVector3f.normalize();
/* 115:    */        
/* 116:116 */        this.jdField_b_of_type_ComBulletphysicsLinearmathTransform.origin.add(this.jdField_b_of_type_JavaxVecmathVector3f);
/* 117:    */        
/* 118:118 */        float f2 = zf.a;
/* 119:119 */        int i = (int)(f1 / f2);
/* 120:    */        
/* 121:121 */        float f3 = zf.a;
/* 122:122 */        if (i > this.jdField_d_of_type_Int)
/* 123:    */        {
/* 125:125 */          f3 = f1 / this.jdField_d_of_type_Int;
/* 126:    */        }
/* 127:    */        
/* 128:128 */        this.jdField_b_of_type_JavaxVecmathVector3f.scale(f3);
/* 129:129 */        while (f2 < f1) {
/* 130:130 */          this.jdField_b_of_type_ComBulletphysicsLinearmathTransform.origin.add(this.jdField_b_of_type_JavaxVecmathVector3f);
/* 131:131 */          a(this.jdField_b_of_type_ComBulletphysicsLinearmathTransform.origin);
/* 132:132 */          f2 += f3;
/* 133:    */        }
/* 134:134 */        this.jdField_b_of_type_ComBulletphysicsLinearmathTransform.set(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform);
/* 135:    */      }
/* 136:    */    }
/* 137:    */    
/* 141:141 */    super.a(paramxq);
/* 142:    */    
/* 143:143 */    if ((this.jdField_b_of_type_Boolean) && (b() <= 0)) {
/* 144:144 */      this.jdField_a_of_type_Boolean = false;
/* 145:    */    }
/* 146:    */  }
/* 147:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ey
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */