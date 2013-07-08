package org.schema.schine.graphicsengine.camera;

import class_1010;
import class_1012;
import class_1044;
import class_1046;
import class_1382;
import class_933;
import class_941;
import class_949;
import class_957;
import class_962;
import class_969;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.util.ObjectPool;
import java.nio.FloatBuffer;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Matrix4f;
import org.schema.common.FastMath;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.network.client.ClientStateInterface;

public class Camera
  implements class_957, class_1382
{
  private float jdField_field_93_of_type_Float = 0.0007F;
  private float[][] jdField_field_89_of_type_Array2dOfFloat;
  protected class_1046 field_89;
  private float[] jdField_field_89_of_type_ArrayOfFloat = new float[16];
  private float[] jdField_field_90_of_type_ArrayOfFloat;
  private Transform jdField_field_89_of_type_ComBulletphysicsLinearmathTransform;
  private static FloatBuffer jdField_field_89_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(16);
  private static FloatBuffer jdField_field_90_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(16);
  private int jdField_field_89_of_type_Int = 0;
  private class_962 jdField_field_89_of_type_Class_962;
  public class_1010 field_89;
  private Transform jdField_field_90_of_type_ComBulletphysicsLinearmathTransform;
  private Transform jdField_field_92_of_type_ComBulletphysicsLinearmathTransform;
  private Vector3f jdField_field_89_of_type_JavaxVecmathVector3f;
  private Vector3f jdField_field_90_of_type_JavaxVecmathVector3f;
  private Vector3f jdField_field_92_of_type_JavaxVecmathVector3f;
  private Vector3f jdField_field_93_of_type_JavaxVecmathVector3f;
  private Vector3f jdField_field_94_of_type_JavaxVecmathVector3f;
  private Vector3f field_95;
  private Vector3f field_96;
  private boolean jdField_field_89_of_type_Boolean;
  public float field_89;
  private float jdField_field_94_of_type_Float;
  private ThreadLocal jdField_field_89_of_type_JavaLangThreadLocal;
  private final Vector3f field_97;
  public float field_90;
  public float field_92;
  private final Vector3f field_100;
  private final Vector3f field_216;
  private final Vector3f field_275;
  
  public static void main(String[] paramArrayOfString)
  {
    paramArrayOfString = new float[] { -0.3499902F, -5.811467E-007F, 0.9367533F, 0.0F, 0.5169892F, 0.8339127F, 0.1931583F, 0.0F, -0.7811701F, 0.5518945F, -0.2918607F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F };
    Transform localTransform;
    (localTransform = new Transform()).setFromOpenGLMatrix(paramArrayOfString);
    (paramArrayOfString = new Vector3f(188.99323F, 77.021645F, 294.43661F)).negate();
    Vector3f localVector3f1 = new Vector3f(paramArrayOfString);
    Vector3f localVector3f2 = new Vector3f();
    localTransform.transform(localVector3f1);
    do
    {
      localVector3f2.set(paramArrayOfString);
      localTransform.basis.transform(localVector3f2);
    } while (localVector3f2.equals(localVector3f1));
    throw new NullPointerException(localVector3f2 + "; " + localVector3f1);
  }
  
  public Camera(class_962 paramclass_962)
  {
    new Vector3f();
    new Vector3f();
    new Vector3f();
    new Vector3f();
    this.jdField_field_90_of_type_ComBulletphysicsLinearmathTransform = new Transform();
    this.jdField_field_92_of_type_ComBulletphysicsLinearmathTransform = new Transform();
    this.jdField_field_89_of_type_JavaxVecmathVector3f = new Vector3f();
    this.jdField_field_90_of_type_JavaxVecmathVector3f = new Vector3f();
    this.jdField_field_92_of_type_JavaxVecmathVector3f = new Vector3f();
    this.jdField_field_93_of_type_JavaxVecmathVector3f = new Vector3f();
    this.jdField_field_94_of_type_JavaxVecmathVector3f = new Vector3f();
    this.field_95 = new Vector3f();
    this.field_96 = new Vector3f();
    this.jdField_field_89_of_type_Boolean = true;
    this.jdField_field_89_of_type_JavaLangThreadLocal = new class_1044();
    this.field_97 = new Vector3f();
    this.jdField_field_90_of_type_Float = 1.0F;
    this.jdField_field_92_of_type_Float = 1.0F;
    this.field_100 = new Vector3f();
    this.field_216 = new Vector3f();
    this.field_275 = new Vector3f();
    class_962 localclass_9621 = paramclass_962;
    paramclass_962 = this;
    this.jdField_field_89_of_type_ComBulletphysicsLinearmathTransform = new Transform();
    paramclass_962.jdField_field_89_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
    paramclass_962.jdField_field_90_of_type_ArrayOfFloat = new float[16];
    paramclass_962.a2();
    paramclass_962.jdField_field_89_of_type_Class_1010 = new class_1012(paramclass_962);
    paramclass_962.jdField_field_89_of_type_Array2dOfFloat = new float[6][4];
    class_962 localclass_9622 = localclass_9621;
    (localclass_9621 = paramclass_962).jdField_field_89_of_type_Class_962 = localclass_9622;
    localclass_9622.field_89 = localclass_9621;
    localclass_9621.a2();
    paramclass_962.jdField_field_89_of_type_Class_1046 = new class_1046();
  }
  
  public final Vector3f a182(Vector3f paramVector3f)
  {
    return GlUtil.c(paramVector3f, getWorldTransform());
  }
  
  public final Vector3f c10()
  {
    return GlUtil.c(new Vector3f(), getWorldTransform());
  }
  
  public final Vector3f d7()
  {
    return GlUtil.d(new Vector3f(), getWorldTransform());
  }
  
  public final Vector3f b31(Vector3f paramVector3f)
  {
    return GlUtil.d(paramVector3f, getWorldTransform());
  }
  
  public final float a3()
  {
    return this.jdField_field_92_of_type_Float * ((Float)class_949.field_1239.a4()).floatValue();
  }
  
  public final float b1()
  {
    return this.jdField_field_90_of_type_Float * ((Float)class_949.field_1239.a4()).floatValue();
  }
  
  public final Vector3f c16(Vector3f paramVector3f)
  {
    Vector3f localVector3f1 = (Vector3f)a183().get();
    Vector3f localVector3f2 = (Vector3f)a183().get();
    try
    {
      localVector3f1.set(getWorldTransform().origin);
      localVector3f2.set(c10());
      localVector3f2.negate();
      localVector3f2.scale(this.jdField_field_89_of_type_Float);
      localVector3f1.add(localVector3f2);
      paramVector3f.set(localVector3f1);
      paramVector3f = localVector3f1;
      return paramVector3f;
    }
    finally
    {
      a183().release(localVector3f1);
      a183().release(localVector3f2);
    }
  }
  
  private ObjectPool a183()
  {
    return (ObjectPool)this.jdField_field_89_of_type_JavaLangThreadLocal.get();
  }
  
  public final Vector3f a83()
  {
    return this.field_97;
  }
  
  public final Vector3f e7()
  {
    return GlUtil.e(new Vector3f(), getWorldTransform());
  }
  
  public final Vector3f f5()
  {
    return GlUtil.f(new Vector3f(), getWorldTransform());
  }
  
  public final Vector3f d11(Vector3f paramVector3f)
  {
    return GlUtil.f(paramVector3f, getWorldTransform());
  }
  
  public final class_962 a184()
  {
    if ((!jdField_field_90_of_type_Boolean) && (this.jdField_field_89_of_type_Class_962 == null)) {
      throw new AssertionError();
    }
    return this.jdField_field_89_of_type_Class_962;
  }
  
  public Transform getWorldTransform()
  {
    return this.jdField_field_89_of_type_ComBulletphysicsLinearmathTransform;
  }
  
  public final boolean a185(Vector3f paramVector3f1, Vector3f paramVector3f2)
  {
    float f5 = paramVector3f2.field_617;
    float f4 = paramVector3f2.field_616;
    float f3 = paramVector3f2.field_615;
    float f2 = paramVector3f1.field_617;
    float f1 = paramVector3f1.field_616;
    paramVector3f2 = paramVector3f1.field_615;
    paramVector3f1 = this;
    for (int i = 0; i < 6; i++) {
      if ((paramVector3f1.jdField_field_89_of_type_Array2dOfFloat[i][0] * paramVector3f2 + paramVector3f1.jdField_field_89_of_type_Array2dOfFloat[i][1] * f1 + paramVector3f1.jdField_field_89_of_type_Array2dOfFloat[i][2] * f2 + paramVector3f1.jdField_field_89_of_type_Array2dOfFloat[i][3] <= 0.0F) && (paramVector3f1.jdField_field_89_of_type_Array2dOfFloat[i][0] * f3 + paramVector3f1.jdField_field_89_of_type_Array2dOfFloat[i][1] * f1 + paramVector3f1.jdField_field_89_of_type_Array2dOfFloat[i][2] * f2 + paramVector3f1.jdField_field_89_of_type_Array2dOfFloat[i][3] <= 0.0F) && (paramVector3f1.jdField_field_89_of_type_Array2dOfFloat[i][0] * paramVector3f2 + paramVector3f1.jdField_field_89_of_type_Array2dOfFloat[i][1] * f4 + paramVector3f1.jdField_field_89_of_type_Array2dOfFloat[i][2] * f2 + paramVector3f1.jdField_field_89_of_type_Array2dOfFloat[i][3] <= 0.0F) && (paramVector3f1.jdField_field_89_of_type_Array2dOfFloat[i][0] * f3 + paramVector3f1.jdField_field_89_of_type_Array2dOfFloat[i][1] * f4 + paramVector3f1.jdField_field_89_of_type_Array2dOfFloat[i][2] * f2 + paramVector3f1.jdField_field_89_of_type_Array2dOfFloat[i][3] <= 0.0F) && (paramVector3f1.jdField_field_89_of_type_Array2dOfFloat[i][0] * paramVector3f2 + paramVector3f1.jdField_field_89_of_type_Array2dOfFloat[i][1] * f1 + paramVector3f1.jdField_field_89_of_type_Array2dOfFloat[i][2] * f5 + paramVector3f1.jdField_field_89_of_type_Array2dOfFloat[i][3] <= 0.0F) && (paramVector3f1.jdField_field_89_of_type_Array2dOfFloat[i][0] * f3 + paramVector3f1.jdField_field_89_of_type_Array2dOfFloat[i][1] * f1 + paramVector3f1.jdField_field_89_of_type_Array2dOfFloat[i][2] * f5 + paramVector3f1.jdField_field_89_of_type_Array2dOfFloat[i][3] <= 0.0F) && (paramVector3f1.jdField_field_89_of_type_Array2dOfFloat[i][0] * paramVector3f2 + paramVector3f1.jdField_field_89_of_type_Array2dOfFloat[i][1] * f4 + paramVector3f1.jdField_field_89_of_type_Array2dOfFloat[i][2] * f5 + paramVector3f1.jdField_field_89_of_type_Array2dOfFloat[i][3] <= 0.0F) && (paramVector3f1.jdField_field_89_of_type_Array2dOfFloat[i][0] * f3 + paramVector3f1.jdField_field_89_of_type_Array2dOfFloat[i][1] * f4 + paramVector3f1.jdField_field_89_of_type_Array2dOfFloat[i][2] * f5 + paramVector3f1.jdField_field_89_of_type_Array2dOfFloat[i][3] <= 0.0F)) {
        return false;
      }
    }
    return true;
  }
  
  public final boolean a186(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    for (int i = 0; i < 6; i++) {
      if (this.jdField_field_89_of_type_Array2dOfFloat[i][0] * paramFloat1 + this.jdField_field_89_of_type_Array2dOfFloat[i][1] * paramFloat2 + this.jdField_field_89_of_type_Array2dOfFloat[i][2] * paramFloat3 + this.jdField_field_89_of_type_Array2dOfFloat[i][3] <= 0.0F) {
        return false;
      }
    }
    return true;
  }
  
  public final boolean a187(Vector3f paramVector3f)
  {
    return a186(paramVector3f.field_615, paramVector3f.field_616, paramVector3f.field_617);
  }
  
  protected int a81(int paramInt)
  {
    return Math.max(0, paramInt);
  }
  
  public final void c()
  {
    c16(this.field_97);
    c10().normalize();
    f5().normalize();
    this.jdField_field_93_of_type_JavaxVecmathVector3f.cross(f5(), c10());
    this.jdField_field_93_of_type_JavaxVecmathVector3f.normalize();
    this.jdField_field_93_of_type_JavaxVecmathVector3f.negate();
    this.field_96.set(c10());
    this.field_96.negate();
    this.jdField_field_92_of_type_ComBulletphysicsLinearmathTransform.basis.setRow(0, this.jdField_field_93_of_type_JavaxVecmathVector3f);
    this.jdField_field_92_of_type_ComBulletphysicsLinearmathTransform.basis.setRow(1, f5());
    this.jdField_field_92_of_type_ComBulletphysicsLinearmathTransform.basis.setRow(2, this.field_96);
    this.field_95.set(this.field_97);
    this.field_95.negate();
    this.jdField_field_92_of_type_ComBulletphysicsLinearmathTransform.origin.set(this.field_95);
    this.jdField_field_92_of_type_ComBulletphysicsLinearmathTransform.basis.transform(this.jdField_field_92_of_type_ComBulletphysicsLinearmathTransform.origin);
    GlUtil.a11(this.jdField_field_92_of_type_ComBulletphysicsLinearmathTransform);
    this.jdField_field_89_of_type_JavaxVecmathVector3f.set(this.field_95);
    this.jdField_field_90_of_type_JavaxVecmathVector3f.set(this.field_96);
    this.jdField_field_92_of_type_JavaxVecmathVector3f.set(f5());
    this.jdField_field_94_of_type_JavaxVecmathVector3f.set(this.jdField_field_93_of_type_JavaxVecmathVector3f);
    this.jdField_field_90_of_type_ComBulletphysicsLinearmathTransform.set(this.jdField_field_92_of_type_ComBulletphysicsLinearmathTransform);
    d();
  }
  
  public void a2()
  {
    getWorldTransform().setIdentity();
    a159(new Vector3f(0.0F, 0.0F, -1.0F));
    b24(new Vector3f(1.0F, 0.0F, 0.0F));
    c12(new Vector3f(0.0F, 1.0F, 0.0F));
    getWorldTransform().getOpenGLMatrix(this.jdField_field_90_of_type_ArrayOfFloat);
    this.jdField_field_89_of_type_Float = this.jdField_field_94_of_type_Float;
  }
  
  public final void a(float paramFloat)
  {
    this.jdField_field_94_of_type_Float = paramFloat;
    this.jdField_field_89_of_type_Boolean = true;
    a2();
  }
  
  public final void a159(Vector3f paramVector3f)
  {
    GlUtil.a30(paramVector3f, getWorldTransform());
  }
  
  public final void b24(Vector3f paramVector3f)
  {
    GlUtil.c3(paramVector3f, getWorldTransform());
  }
  
  public final void c12(Vector3f paramVector3f)
  {
    GlUtil.d2(paramVector3f, getWorldTransform());
  }
  
  public void a12(class_941 paramclass_941)
  {
    boolean bool;
    if (((bool = class_1046.a1())) || (this.jdField_field_89_of_type_Boolean))
    {
      e();
      if ((!bool) && (this.jdField_field_89_of_type_Boolean)) {
        this.jdField_field_89_of_type_Class_1010.a1(0.0F, 0.0F, 0.0F, a3(), b1(), 0.0F);
      } else {
        this.jdField_field_89_of_type_Class_1010.a1(this.jdField_field_89_of_type_Class_1046.field_1307 / (class_933.b() / 2), this.jdField_field_89_of_type_Class_1046.field_1308 / (class_933.a() / 2), 0.0F, a3(), b1(), 0.0F);
      }
    }
    b32(paramclass_941);
    GlUtil.f(this.field_100, getWorldTransform());
    GlUtil.e(this.field_216, getWorldTransform());
    GlUtil.c(this.field_275, getWorldTransform());
  }
  
  public final void d()
  {
    jdField_field_89_of_type_JavaNioFloatBuffer.rewind();
    jdField_field_90_of_type_JavaNioFloatBuffer.rewind();
    class_969.field_1260.store(jdField_field_89_of_type_JavaNioFloatBuffer);
    class_969.field_1259.store(jdField_field_90_of_type_JavaNioFloatBuffer);
    this.jdField_field_89_of_type_ArrayOfFloat[0] = (jdField_field_90_of_type_JavaNioFloatBuffer.get(0) * jdField_field_89_of_type_JavaNioFloatBuffer.get(0) + jdField_field_90_of_type_JavaNioFloatBuffer.get(1) * jdField_field_89_of_type_JavaNioFloatBuffer.get(4) + jdField_field_90_of_type_JavaNioFloatBuffer.get(2) * jdField_field_89_of_type_JavaNioFloatBuffer.get(8) + jdField_field_90_of_type_JavaNioFloatBuffer.get(3) * jdField_field_89_of_type_JavaNioFloatBuffer.get(12));
    this.jdField_field_89_of_type_ArrayOfFloat[1] = (jdField_field_90_of_type_JavaNioFloatBuffer.get(0) * jdField_field_89_of_type_JavaNioFloatBuffer.get(1) + jdField_field_90_of_type_JavaNioFloatBuffer.get(1) * jdField_field_89_of_type_JavaNioFloatBuffer.get(5) + jdField_field_90_of_type_JavaNioFloatBuffer.get(2) * jdField_field_89_of_type_JavaNioFloatBuffer.get(9) + jdField_field_90_of_type_JavaNioFloatBuffer.get(3) * jdField_field_89_of_type_JavaNioFloatBuffer.get(13));
    this.jdField_field_89_of_type_ArrayOfFloat[2] = (jdField_field_90_of_type_JavaNioFloatBuffer.get(0) * jdField_field_89_of_type_JavaNioFloatBuffer.get(2) + jdField_field_90_of_type_JavaNioFloatBuffer.get(1) * jdField_field_89_of_type_JavaNioFloatBuffer.get(6) + jdField_field_90_of_type_JavaNioFloatBuffer.get(2) * jdField_field_89_of_type_JavaNioFloatBuffer.get(10) + jdField_field_90_of_type_JavaNioFloatBuffer.get(3) * jdField_field_89_of_type_JavaNioFloatBuffer.get(14));
    this.jdField_field_89_of_type_ArrayOfFloat[3] = (jdField_field_90_of_type_JavaNioFloatBuffer.get(0) * jdField_field_89_of_type_JavaNioFloatBuffer.get(3) + jdField_field_90_of_type_JavaNioFloatBuffer.get(1) * jdField_field_89_of_type_JavaNioFloatBuffer.get(7) + jdField_field_90_of_type_JavaNioFloatBuffer.get(2) * jdField_field_89_of_type_JavaNioFloatBuffer.get(11) + jdField_field_90_of_type_JavaNioFloatBuffer.get(3) * jdField_field_89_of_type_JavaNioFloatBuffer.get(15));
    this.jdField_field_89_of_type_ArrayOfFloat[4] = (jdField_field_90_of_type_JavaNioFloatBuffer.get(4) * jdField_field_89_of_type_JavaNioFloatBuffer.get(0) + jdField_field_90_of_type_JavaNioFloatBuffer.get(5) * jdField_field_89_of_type_JavaNioFloatBuffer.get(4) + jdField_field_90_of_type_JavaNioFloatBuffer.get(6) * jdField_field_89_of_type_JavaNioFloatBuffer.get(8) + jdField_field_90_of_type_JavaNioFloatBuffer.get(7) * jdField_field_89_of_type_JavaNioFloatBuffer.get(12));
    this.jdField_field_89_of_type_ArrayOfFloat[5] = (jdField_field_90_of_type_JavaNioFloatBuffer.get(4) * jdField_field_89_of_type_JavaNioFloatBuffer.get(1) + jdField_field_90_of_type_JavaNioFloatBuffer.get(5) * jdField_field_89_of_type_JavaNioFloatBuffer.get(5) + jdField_field_90_of_type_JavaNioFloatBuffer.get(6) * jdField_field_89_of_type_JavaNioFloatBuffer.get(9) + jdField_field_90_of_type_JavaNioFloatBuffer.get(7) * jdField_field_89_of_type_JavaNioFloatBuffer.get(13));
    this.jdField_field_89_of_type_ArrayOfFloat[6] = (jdField_field_90_of_type_JavaNioFloatBuffer.get(4) * jdField_field_89_of_type_JavaNioFloatBuffer.get(2) + jdField_field_90_of_type_JavaNioFloatBuffer.get(5) * jdField_field_89_of_type_JavaNioFloatBuffer.get(6) + jdField_field_90_of_type_JavaNioFloatBuffer.get(6) * jdField_field_89_of_type_JavaNioFloatBuffer.get(10) + jdField_field_90_of_type_JavaNioFloatBuffer.get(7) * jdField_field_89_of_type_JavaNioFloatBuffer.get(14));
    this.jdField_field_89_of_type_ArrayOfFloat[7] = (jdField_field_90_of_type_JavaNioFloatBuffer.get(4) * jdField_field_89_of_type_JavaNioFloatBuffer.get(3) + jdField_field_90_of_type_JavaNioFloatBuffer.get(5) * jdField_field_89_of_type_JavaNioFloatBuffer.get(7) + jdField_field_90_of_type_JavaNioFloatBuffer.get(6) * jdField_field_89_of_type_JavaNioFloatBuffer.get(11) + jdField_field_90_of_type_JavaNioFloatBuffer.get(7) * jdField_field_89_of_type_JavaNioFloatBuffer.get(15));
    this.jdField_field_89_of_type_ArrayOfFloat[8] = (jdField_field_90_of_type_JavaNioFloatBuffer.get(8) * jdField_field_89_of_type_JavaNioFloatBuffer.get(0) + jdField_field_90_of_type_JavaNioFloatBuffer.get(9) * jdField_field_89_of_type_JavaNioFloatBuffer.get(4) + jdField_field_90_of_type_JavaNioFloatBuffer.get(10) * jdField_field_89_of_type_JavaNioFloatBuffer.get(8) + jdField_field_90_of_type_JavaNioFloatBuffer.get(11) * jdField_field_89_of_type_JavaNioFloatBuffer.get(12));
    this.jdField_field_89_of_type_ArrayOfFloat[9] = (jdField_field_90_of_type_JavaNioFloatBuffer.get(8) * jdField_field_89_of_type_JavaNioFloatBuffer.get(1) + jdField_field_90_of_type_JavaNioFloatBuffer.get(9) * jdField_field_89_of_type_JavaNioFloatBuffer.get(5) + jdField_field_90_of_type_JavaNioFloatBuffer.get(10) * jdField_field_89_of_type_JavaNioFloatBuffer.get(9) + jdField_field_90_of_type_JavaNioFloatBuffer.get(11) * jdField_field_89_of_type_JavaNioFloatBuffer.get(13));
    this.jdField_field_89_of_type_ArrayOfFloat[10] = (jdField_field_90_of_type_JavaNioFloatBuffer.get(8) * jdField_field_89_of_type_JavaNioFloatBuffer.get(2) + jdField_field_90_of_type_JavaNioFloatBuffer.get(9) * jdField_field_89_of_type_JavaNioFloatBuffer.get(6) + jdField_field_90_of_type_JavaNioFloatBuffer.get(10) * jdField_field_89_of_type_JavaNioFloatBuffer.get(10) + jdField_field_90_of_type_JavaNioFloatBuffer.get(11) * jdField_field_89_of_type_JavaNioFloatBuffer.get(14));
    this.jdField_field_89_of_type_ArrayOfFloat[11] = (jdField_field_90_of_type_JavaNioFloatBuffer.get(8) * jdField_field_89_of_type_JavaNioFloatBuffer.get(3) + jdField_field_90_of_type_JavaNioFloatBuffer.get(9) * jdField_field_89_of_type_JavaNioFloatBuffer.get(7) + jdField_field_90_of_type_JavaNioFloatBuffer.get(10) * jdField_field_89_of_type_JavaNioFloatBuffer.get(11) + jdField_field_90_of_type_JavaNioFloatBuffer.get(11) * jdField_field_89_of_type_JavaNioFloatBuffer.get(15));
    this.jdField_field_89_of_type_ArrayOfFloat[12] = (jdField_field_90_of_type_JavaNioFloatBuffer.get(12) * jdField_field_89_of_type_JavaNioFloatBuffer.get(0) + jdField_field_90_of_type_JavaNioFloatBuffer.get(13) * jdField_field_89_of_type_JavaNioFloatBuffer.get(4) + jdField_field_90_of_type_JavaNioFloatBuffer.get(14) * jdField_field_89_of_type_JavaNioFloatBuffer.get(8) + jdField_field_90_of_type_JavaNioFloatBuffer.get(15) * jdField_field_89_of_type_JavaNioFloatBuffer.get(12));
    this.jdField_field_89_of_type_ArrayOfFloat[13] = (jdField_field_90_of_type_JavaNioFloatBuffer.get(12) * jdField_field_89_of_type_JavaNioFloatBuffer.get(1) + jdField_field_90_of_type_JavaNioFloatBuffer.get(13) * jdField_field_89_of_type_JavaNioFloatBuffer.get(5) + jdField_field_90_of_type_JavaNioFloatBuffer.get(14) * jdField_field_89_of_type_JavaNioFloatBuffer.get(9) + jdField_field_90_of_type_JavaNioFloatBuffer.get(15) * jdField_field_89_of_type_JavaNioFloatBuffer.get(13));
    this.jdField_field_89_of_type_ArrayOfFloat[14] = (jdField_field_90_of_type_JavaNioFloatBuffer.get(12) * jdField_field_89_of_type_JavaNioFloatBuffer.get(2) + jdField_field_90_of_type_JavaNioFloatBuffer.get(13) * jdField_field_89_of_type_JavaNioFloatBuffer.get(6) + jdField_field_90_of_type_JavaNioFloatBuffer.get(14) * jdField_field_89_of_type_JavaNioFloatBuffer.get(10) + jdField_field_90_of_type_JavaNioFloatBuffer.get(15) * jdField_field_89_of_type_JavaNioFloatBuffer.get(14));
    this.jdField_field_89_of_type_ArrayOfFloat[15] = (jdField_field_90_of_type_JavaNioFloatBuffer.get(12) * jdField_field_89_of_type_JavaNioFloatBuffer.get(3) + jdField_field_90_of_type_JavaNioFloatBuffer.get(13) * jdField_field_89_of_type_JavaNioFloatBuffer.get(7) + jdField_field_90_of_type_JavaNioFloatBuffer.get(14) * jdField_field_89_of_type_JavaNioFloatBuffer.get(11) + jdField_field_90_of_type_JavaNioFloatBuffer.get(15) * jdField_field_89_of_type_JavaNioFloatBuffer.get(15));
    this.jdField_field_89_of_type_Array2dOfFloat[0][0] = (this.jdField_field_89_of_type_ArrayOfFloat[3] - this.jdField_field_89_of_type_ArrayOfFloat[0]);
    this.jdField_field_89_of_type_Array2dOfFloat[0][1] = (this.jdField_field_89_of_type_ArrayOfFloat[7] - this.jdField_field_89_of_type_ArrayOfFloat[4]);
    this.jdField_field_89_of_type_Array2dOfFloat[0][2] = (this.jdField_field_89_of_type_ArrayOfFloat[11] - this.jdField_field_89_of_type_ArrayOfFloat[8]);
    this.jdField_field_89_of_type_Array2dOfFloat[0][3] = (this.jdField_field_89_of_type_ArrayOfFloat[15] - this.jdField_field_89_of_type_ArrayOfFloat[12]);
    float f = FastMath.l(this.jdField_field_89_of_type_Array2dOfFloat[0][0] * this.jdField_field_89_of_type_Array2dOfFloat[0][0] + this.jdField_field_89_of_type_Array2dOfFloat[0][1] * this.jdField_field_89_of_type_Array2dOfFloat[0][1] + this.jdField_field_89_of_type_Array2dOfFloat[0][2] * this.jdField_field_89_of_type_Array2dOfFloat[0][2]);
    this.jdField_field_89_of_type_Array2dOfFloat[0][0] /= f;
    this.jdField_field_89_of_type_Array2dOfFloat[0][1] /= f;
    this.jdField_field_89_of_type_Array2dOfFloat[0][2] /= f;
    this.jdField_field_89_of_type_Array2dOfFloat[0][3] /= f;
    this.jdField_field_89_of_type_Array2dOfFloat[1][0] = (this.jdField_field_89_of_type_ArrayOfFloat[3] + this.jdField_field_89_of_type_ArrayOfFloat[0]);
    this.jdField_field_89_of_type_Array2dOfFloat[1][1] = (this.jdField_field_89_of_type_ArrayOfFloat[7] + this.jdField_field_89_of_type_ArrayOfFloat[4]);
    this.jdField_field_89_of_type_Array2dOfFloat[1][2] = (this.jdField_field_89_of_type_ArrayOfFloat[11] + this.jdField_field_89_of_type_ArrayOfFloat[8]);
    this.jdField_field_89_of_type_Array2dOfFloat[1][3] = (this.jdField_field_89_of_type_ArrayOfFloat[15] + this.jdField_field_89_of_type_ArrayOfFloat[12]);
    f = FastMath.l(this.jdField_field_89_of_type_Array2dOfFloat[1][0] * this.jdField_field_89_of_type_Array2dOfFloat[1][0] + this.jdField_field_89_of_type_Array2dOfFloat[1][1] * this.jdField_field_89_of_type_Array2dOfFloat[1][1] + this.jdField_field_89_of_type_Array2dOfFloat[1][2] * this.jdField_field_89_of_type_Array2dOfFloat[1][2]);
    this.jdField_field_89_of_type_Array2dOfFloat[1][0] /= f;
    this.jdField_field_89_of_type_Array2dOfFloat[1][1] /= f;
    this.jdField_field_89_of_type_Array2dOfFloat[1][2] /= f;
    this.jdField_field_89_of_type_Array2dOfFloat[1][3] /= f;
    this.jdField_field_89_of_type_Array2dOfFloat[2][0] = (this.jdField_field_89_of_type_ArrayOfFloat[3] + this.jdField_field_89_of_type_ArrayOfFloat[1]);
    this.jdField_field_89_of_type_Array2dOfFloat[2][1] = (this.jdField_field_89_of_type_ArrayOfFloat[7] + this.jdField_field_89_of_type_ArrayOfFloat[5]);
    this.jdField_field_89_of_type_Array2dOfFloat[2][2] = (this.jdField_field_89_of_type_ArrayOfFloat[11] + this.jdField_field_89_of_type_ArrayOfFloat[9]);
    this.jdField_field_89_of_type_Array2dOfFloat[2][3] = (this.jdField_field_89_of_type_ArrayOfFloat[15] + this.jdField_field_89_of_type_ArrayOfFloat[13]);
    f = FastMath.l(this.jdField_field_89_of_type_Array2dOfFloat[2][0] * this.jdField_field_89_of_type_Array2dOfFloat[2][0] + this.jdField_field_89_of_type_Array2dOfFloat[2][1] * this.jdField_field_89_of_type_Array2dOfFloat[2][1] + this.jdField_field_89_of_type_Array2dOfFloat[2][2] * this.jdField_field_89_of_type_Array2dOfFloat[2][2]);
    this.jdField_field_89_of_type_Array2dOfFloat[2][0] /= f;
    this.jdField_field_89_of_type_Array2dOfFloat[2][1] /= f;
    this.jdField_field_89_of_type_Array2dOfFloat[2][2] /= f;
    this.jdField_field_89_of_type_Array2dOfFloat[2][3] /= f;
    this.jdField_field_89_of_type_Array2dOfFloat[3][0] = (this.jdField_field_89_of_type_ArrayOfFloat[3] - this.jdField_field_89_of_type_ArrayOfFloat[1]);
    this.jdField_field_89_of_type_Array2dOfFloat[3][1] = (this.jdField_field_89_of_type_ArrayOfFloat[7] - this.jdField_field_89_of_type_ArrayOfFloat[5]);
    this.jdField_field_89_of_type_Array2dOfFloat[3][2] = (this.jdField_field_89_of_type_ArrayOfFloat[11] - this.jdField_field_89_of_type_ArrayOfFloat[9]);
    this.jdField_field_89_of_type_Array2dOfFloat[3][3] = (this.jdField_field_89_of_type_ArrayOfFloat[15] - this.jdField_field_89_of_type_ArrayOfFloat[13]);
    f = FastMath.l(this.jdField_field_89_of_type_Array2dOfFloat[3][0] * this.jdField_field_89_of_type_Array2dOfFloat[3][0] + this.jdField_field_89_of_type_Array2dOfFloat[3][1] * this.jdField_field_89_of_type_Array2dOfFloat[3][1] + this.jdField_field_89_of_type_Array2dOfFloat[3][2] * this.jdField_field_89_of_type_Array2dOfFloat[3][2]);
    this.jdField_field_89_of_type_Array2dOfFloat[3][0] /= f;
    this.jdField_field_89_of_type_Array2dOfFloat[3][1] /= f;
    this.jdField_field_89_of_type_Array2dOfFloat[3][2] /= f;
    this.jdField_field_89_of_type_Array2dOfFloat[3][3] /= f;
    this.jdField_field_89_of_type_Array2dOfFloat[4][0] = (this.jdField_field_89_of_type_ArrayOfFloat[3] - this.jdField_field_89_of_type_ArrayOfFloat[2]);
    this.jdField_field_89_of_type_Array2dOfFloat[4][1] = (this.jdField_field_89_of_type_ArrayOfFloat[7] - this.jdField_field_89_of_type_ArrayOfFloat[6]);
    this.jdField_field_89_of_type_Array2dOfFloat[4][2] = (this.jdField_field_89_of_type_ArrayOfFloat[11] - this.jdField_field_89_of_type_ArrayOfFloat[10]);
    this.jdField_field_89_of_type_Array2dOfFloat[4][3] = (this.jdField_field_89_of_type_ArrayOfFloat[15] - this.jdField_field_89_of_type_ArrayOfFloat[14]);
    f = FastMath.l(this.jdField_field_89_of_type_Array2dOfFloat[4][0] * this.jdField_field_89_of_type_Array2dOfFloat[4][0] + this.jdField_field_89_of_type_Array2dOfFloat[4][1] * this.jdField_field_89_of_type_Array2dOfFloat[4][1] + this.jdField_field_89_of_type_Array2dOfFloat[4][2] * this.jdField_field_89_of_type_Array2dOfFloat[4][2]);
    this.jdField_field_89_of_type_Array2dOfFloat[4][0] /= f;
    this.jdField_field_89_of_type_Array2dOfFloat[4][1] /= f;
    this.jdField_field_89_of_type_Array2dOfFloat[4][2] /= f;
    this.jdField_field_89_of_type_Array2dOfFloat[4][3] /= f;
    this.jdField_field_89_of_type_Array2dOfFloat[5][0] = (this.jdField_field_89_of_type_ArrayOfFloat[3] + this.jdField_field_89_of_type_ArrayOfFloat[2]);
    this.jdField_field_89_of_type_Array2dOfFloat[5][1] = (this.jdField_field_89_of_type_ArrayOfFloat[7] + this.jdField_field_89_of_type_ArrayOfFloat[6]);
    this.jdField_field_89_of_type_Array2dOfFloat[5][2] = (this.jdField_field_89_of_type_ArrayOfFloat[11] + this.jdField_field_89_of_type_ArrayOfFloat[10]);
    this.jdField_field_89_of_type_Array2dOfFloat[5][3] = (this.jdField_field_89_of_type_ArrayOfFloat[15] + this.jdField_field_89_of_type_ArrayOfFloat[14]);
    f = FastMath.l(this.jdField_field_89_of_type_Array2dOfFloat[5][0] * this.jdField_field_89_of_type_Array2dOfFloat[5][0] + this.jdField_field_89_of_type_Array2dOfFloat[5][1] * this.jdField_field_89_of_type_Array2dOfFloat[5][1] + this.jdField_field_89_of_type_Array2dOfFloat[5][2] * this.jdField_field_89_of_type_Array2dOfFloat[5][2]);
    this.jdField_field_89_of_type_Array2dOfFloat[5][0] /= f;
    this.jdField_field_89_of_type_Array2dOfFloat[5][1] /= f;
    this.jdField_field_89_of_type_Array2dOfFloat[5][2] /= f;
    this.jdField_field_89_of_type_Array2dOfFloat[5][3] /= f;
  }
  
  public void a77(ClientStateInterface paramClientStateInterface, class_941 paramclass_941)
  {
    this.jdField_field_89_of_type_Class_1046.a2(paramClientStateInterface);
  }
  
  protected final void e()
  {
    if ((class_949.field_1242.a4().equals("ZOOM")) || (Keyboard.isKeyDown(42)))
    {
      int i = this.jdField_field_89_of_type_Int;
      this.jdField_field_89_of_type_Int = a81(this.jdField_field_89_of_type_Int - this.jdField_field_89_of_type_Class_1046.field_1306);
      if (this.jdField_field_89_of_type_Boolean)
      {
        this.jdField_field_89_of_type_Int = ((int)this.jdField_field_94_of_type_Float);
        this.jdField_field_89_of_type_Boolean = false;
      }
      if (i != this.jdField_field_89_of_type_Int)
      {
        float f = this.jdField_field_89_of_type_Int * this.jdField_field_93_of_type_Float;
        this.jdField_field_89_of_type_Float = (this.jdField_field_89_of_type_Int > 0 ? FastMath.f(FastMath.b2(f, 1.3F)) : 0.0F);
      }
    }
  }
  
  public final void b32(class_941 paramclass_941)
  {
    this.jdField_field_89_of_type_Class_962.a12(paramclass_941);
    getWorldTransform().origin.set(a184().a83());
  }
  
  public final Vector3f g5()
  {
    return this.field_100;
  }
  
  public final Vector3f h4()
  {
    return this.field_216;
  }
  
  public final Vector3f i2()
  {
    return this.field_275;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.graphicsengine.camera.Camera
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */