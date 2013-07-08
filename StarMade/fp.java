/*   1:    */import com.bulletphysics.linearmath.Transform;
/*   2:    */import java.io.PrintStream;
/*   3:    */import java.nio.FloatBuffer;
/*   4:    */import java.util.List;
/*   5:    */import javax.vecmath.Matrix3f;
/*   6:    */import javax.vecmath.Vector3f;
/*   7:    */import org.lwjgl.BufferUtils;
/*   8:    */import org.lwjgl.opengl.GL11;
/*   9:    */import org.lwjgl.opengl.GL15;
/*  10:    */import org.lwjgl.util.vector.Matrix4f;
/*  11:    */import org.schema.schine.graphicsengine.core.GlUtil;
/*  12:    */import org.schema.schine.graphicsengine.forms.Mesh;
/*  13:    */
/*  14:    */public final class fp
/*  15:    */  implements xg
/*  16:    */{
/*  17:    */  private ct jdField_a_of_type_Ct;
/*  18:    */  private float jdField_a_of_type_Float;
/*  19:    */  private Matrix3f jdField_a_of_type_JavaxVecmathMatrix3f;
/*  20:    */  private Transform jdField_a_of_type_ComBulletphysicsLinearmathTransform;
/*  21:    */  private FloatBuffer jdField_a_of_type_JavaNioFloatBuffer;
/*  22:    */  private float[] jdField_a_of_type_ArrayOfFloat;
/*  23:    */  private boolean jdField_a_of_type_Boolean;
/*  24:    */  private Mesh jdField_a_of_type_OrgSchemaSchineGraphicsengineFormsMesh;
/*  25:    */  
/*  26:    */  public fp(ct paramct)
/*  27:    */  {
/*  28: 28 */    new Transform();
/*  29:    */    
/*  30: 30 */    new Vector3f();
/*  31: 31 */    this.jdField_a_of_type_Float = 0.0F;
/*  32:    */    
/*  33: 33 */    this.jdField_a_of_type_JavaxVecmathMatrix3f = new Matrix3f();
/*  34: 34 */    this.jdField_a_of_type_ComBulletphysicsLinearmathTransform = new Transform();
/*  35: 35 */    this.jdField_a_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(16);
/*  36: 36 */    this.jdField_a_of_type_ArrayOfFloat = new float[16];
/*  37:    */    
/*  40: 40 */    this.jdField_a_of_type_Ct = paramct;
/*  41:    */  }
/*  42:    */  
/*  44:    */  public final void a() {}
/*  45:    */  
/*  47:    */  public final void b()
/*  48:    */  {
/*  49: 49 */    if (!this.jdField_a_of_type_Boolean) {
/*  50: 50 */      c();
/*  51:    */    }
/*  52:    */    try {
/*  53:    */      kd localkd;
/*  54: 54 */      if ((localkd = this.jdField_a_of_type_Ct.a()) != null)
/*  55:    */      {
/*  63: 63 */        Matrix4f localMatrix4f = xe.a;
/*  64: 64 */        this.jdField_a_of_type_JavaNioFloatBuffer.rewind();
/*  65: 65 */        localMatrix4f.store(this.jdField_a_of_type_JavaNioFloatBuffer);
/*  66: 66 */        this.jdField_a_of_type_JavaNioFloatBuffer.rewind();
/*  67: 67 */        this.jdField_a_of_type_JavaNioFloatBuffer.get(this.jdField_a_of_type_ArrayOfFloat);
/*  68: 68 */        this.jdField_a_of_type_ComBulletphysicsLinearmathTransform.setFromOpenGLMatrix(this.jdField_a_of_type_ArrayOfFloat);
/*  69: 69 */        this.jdField_a_of_type_ComBulletphysicsLinearmathTransform.origin.set(0.0F, 0.0F, 0.0F);
/*  70:    */        
/*  72: 72 */        yz.j();
/*  73: 73 */        GlUtil.d();
/*  74:    */        
/*  76: 76 */        GlUtil.c(xm.b() / 2, 105.0F, 0.0F);
/*  77:    */        
/*  79: 79 */        GlUtil.b(10.0F, -10.0F, 10.0F);
/*  80:    */        
/*  81: 81 */        this.jdField_a_of_type_JavaxVecmathMatrix3f.set(localkd.getWorldTransform().basis);
/*  82: 82 */        this.jdField_a_of_type_ComBulletphysicsLinearmathTransform.basis.mul(this.jdField_a_of_type_JavaxVecmathMatrix3f);
/*  83:    */        
/*  84: 84 */        GlUtil.b(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform);
/*  85:    */        
/*  88: 88 */        GL11.glDisable(2896);
/*  89: 89 */        GL11.glDisable(2929);
/*  90: 90 */        GL11.glEnable(2977);
/*  91: 91 */        GL11.glDisable(2884);
/*  92: 92 */        GL11.glEnable(2929);
/*  93:    */        
/*  94: 94 */        this.jdField_a_of_type_OrgSchemaSchineGraphicsengineFormsMesh.a().a();
/*  95:    */        
/*  97: 97 */        this.jdField_a_of_type_OrgSchemaSchineGraphicsengineFormsMesh.e();
/*  98:    */        
/*  99: 99 */        this.jdField_a_of_type_OrgSchemaSchineGraphicsengineFormsMesh.a().c();
/* 100:100 */        GlUtil.c();
/* 101:101 */        yz.h();
/* 102:102 */        this.jdField_a_of_type_Float += 0.15F;
/* 103:103 */        GL11.glEnable(2896);
/* 104:104 */        GL11.glDisable(2977);
/* 105:105 */        GL11.glEnable(2929);
/* 106:106 */        GL15.glBindBuffer(34962, 0);
/* 107:    */      }
/* 108:    */      return;
/* 109:109 */    } catch (NullPointerException localNullPointerException) { System.err.println("EXCPETION HAS BEEN HANDLED");
/* 110:110 */      localNullPointerException.printStackTrace();
/* 111:    */    }
/* 112:    */  }
/* 113:    */  
/* 125:    */  public final void c()
/* 126:    */  {
/* 127:127 */    this.jdField_a_of_type_OrgSchemaSchineGraphicsengineFormsMesh = ((Mesh)xe.a().a("Arrow").a().get(0));
/* 128:128 */    this.jdField_a_of_type_Boolean = true;
/* 129:    */  }
/* 130:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     fp
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */