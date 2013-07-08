import com.bulletphysics.linearmath.Transform;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.graphicsengine.forms.AnimationNotFoundException;

public abstract class class_984
  extends Observable
  implements class_957, class_965
{
  private static float[] jdField_field_89_of_type_ArrayOfFloat = new float[16];
  public static FloatBuffer field_89;
  private Vector3f jdField_field_90_of_type_JavaxVecmathVector3f;
  public Transform field_89;
  private int jdField_field_89_of_type_Int = 1;
  protected class_1393 field_89;
  private class_1034 jdField_field_89_of_type_Class_1034 = new class_1034();
  private List jdField_field_90_of_type_JavaUtilList = new ArrayList();
  public boolean field_94;
  public String field_89;
  private Vector4f jdField_field_89_of_type_JavaxVecmathVector4f;
  public List field_89;
  private Vector4f jdField_field_90_of_type_JavaxVecmathVector4f;
  private Vector4f jdField_field_92_of_type_JavaxVecmathVector4f;
  private Vector3f jdField_field_92_of_type_JavaxVecmathVector3f;
  public class_988 field_89;
  private float jdField_field_89_of_type_Float;
  public Vector3f field_89;
  private class_984 jdField_field_89_of_type_Class_984;
  
  public static boolean g3()
  {
    return false;
  }
  
  public static void a149(class_984 paramclass_984)
  {
    if ((paramclass_984.jdField_field_90_of_type_JavaxVecmathVector3f.field_615 != 0.0F) || (paramclass_984.jdField_field_90_of_type_JavaxVecmathVector3f.field_616 != 0.0F) || (paramclass_984.jdField_field_90_of_type_JavaxVecmathVector3f.field_617 != 0.0F)) {
      GlUtil.c4(paramclass_984.jdField_field_90_of_type_JavaxVecmathVector3f.field_615, paramclass_984.jdField_field_90_of_type_JavaxVecmathVector3f.field_616, paramclass_984.jdField_field_90_of_type_JavaxVecmathVector3f.field_617);
    }
    GlUtil.b3(paramclass_984.jdField_field_89_of_type_ComBulletphysicsLinearmathTransform);
    if ((paramclass_984.jdField_field_89_of_type_JavaxVecmathVector4f.field_596 != 0.0F) || (paramclass_984.jdField_field_89_of_type_JavaxVecmathVector4f.field_597 != 0.0F) || (paramclass_984.jdField_field_89_of_type_JavaxVecmathVector4f.field_598 != 0.0F) || (paramclass_984.jdField_field_89_of_type_JavaxVecmathVector4f.field_599 != 1.0F))
    {
      paramclass_984.jdField_field_90_of_type_JavaxVecmathVector4f.set(paramclass_984.jdField_field_89_of_type_JavaxVecmathVector4f);
      class_49.a5(paramclass_984.jdField_field_90_of_type_JavaxVecmathVector4f);
      GL11.glRotatef((float)Math.toDegrees(paramclass_984.jdField_field_90_of_type_JavaxVecmathVector4f.field_599), paramclass_984.jdField_field_90_of_type_JavaxVecmathVector4f.field_596, paramclass_984.jdField_field_90_of_type_JavaxVecmathVector4f.field_597, paramclass_984.jdField_field_90_of_type_JavaxVecmathVector4f.field_598);
    }
    if ((paramclass_984.jdField_field_89_of_type_JavaxVecmathVector3f.field_615 != 1.0F) || (paramclass_984.jdField_field_89_of_type_JavaxVecmathVector3f.field_616 != 1.0F) || (paramclass_984.jdField_field_89_of_type_JavaxVecmathVector3f.field_617 != 1.0F)) {
      GL11.glScalef(paramclass_984.jdField_field_89_of_type_JavaxVecmathVector3f.field_615, paramclass_984.jdField_field_89_of_type_JavaxVecmathVector3f.field_616, paramclass_984.jdField_field_89_of_type_JavaxVecmathVector3f.field_617);
    }
  }
  
  public class_984()
  {
    this.jdField_field_89_of_type_JavaLangString = "default";
    this.jdField_field_89_of_type_JavaUtilList = new ArrayList();
    this.jdField_field_89_of_type_JavaxVecmathVector3f = new Vector3f(1.0F, 1.0F, 1.0F);
    this.jdField_field_89_of_type_JavaLangString = getClass().getSimpleName();
    this.jdField_field_89_of_type_ComBulletphysicsLinearmathTransform = new Transform();
    this.jdField_field_89_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
    this.jdField_field_90_of_type_JavaxVecmathVector3f = new Vector3f();
    this.jdField_field_89_of_type_JavaxVecmathVector4f = new Vector4f(0.0F, 0.0F, 0.0F, 1.0F);
    this.jdField_field_90_of_type_JavaxVecmathVector4f = new Vector4f();
    this.jdField_field_92_of_type_JavaxVecmathVector4f = new Vector4f(0.0F, 0.0F, 0.0F, 1.0F);
    this.jdField_field_92_of_type_JavaxVecmathVector3f = new Vector3f(1.0F, 1.0F, 1.0F);
  }
  
  public static void n1()
  {
    if (class_949.field_1209.b1())
    {
      GL11.glEnable(2884);
      GL11.glCullFace(1029);
    }
  }
  
  public final boolean a150(class_984 paramclass_984)
  {
    paramclass_984.b25(this);
    return this.jdField_field_89_of_type_JavaUtilList.add(paramclass_984);
  }
  
  public final class_988 a151()
  {
    return this.jdField_field_89_of_type_Class_988;
  }
  
  public final List a152()
  {
    return this.jdField_field_89_of_type_JavaUtilList;
  }
  
  public final Vector4f b22()
  {
    return this.jdField_field_92_of_type_JavaxVecmathVector4f;
  }
  
  public final Vector3f c10()
  {
    return this.jdField_field_92_of_type_JavaxVecmathVector3f;
  }
  
  public final Vector3f d7()
  {
    return this.jdField_field_90_of_type_JavaxVecmathVector3f;
  }
  
  public final class_1393 a153()
  {
    return this.jdField_field_89_of_type_Class_1393;
  }
  
  public String b14()
  {
    return this.jdField_field_89_of_type_JavaLangString;
  }
  
  public class_984 a154()
  {
    return this.jdField_field_89_of_type_Class_984;
  }
  
  public final Vector3f a83()
  {
    return this.jdField_field_89_of_type_ComBulletphysicsLinearmathTransform.origin;
  }
  
  public final Vector3f e7()
  {
    return this.jdField_field_89_of_type_JavaxVecmathVector3f;
  }
  
  public boolean h2()
  {
    return this.jdField_field_89_of_type_Int != 1;
  }
  
  public final boolean i1()
  {
    return this.field_94;
  }
  
  public final class_988 a155(Vector3f paramVector3f1, Vector3f paramVector3f2)
  {
    class_988 localclass_988 = new class_988(paramVector3f1, paramVector3f2);
    Iterator localIterator = this.jdField_field_89_of_type_JavaUtilList.iterator();
    while (localIterator.hasNext()) {
      localclass_988 = ((class_984)localIterator.next()).a155(paramVector3f1, paramVector3f2);
    }
    if (this.jdField_field_89_of_type_Class_988 != null)
    {
      if (localclass_988.field_1274.field_615 < this.jdField_field_89_of_type_Class_988.field_1274.field_615) {
        localclass_988.field_1274.field_615 = this.jdField_field_89_of_type_Class_988.field_1274.field_615;
      }
      if (localclass_988.field_1274.field_616 < this.jdField_field_89_of_type_Class_988.field_1274.field_616) {
        localclass_988.field_1274.field_616 = this.jdField_field_89_of_type_Class_988.field_1274.field_616;
      }
      if (localclass_988.field_1274.field_617 < this.jdField_field_89_of_type_Class_988.field_1274.field_617) {
        localclass_988.field_1274.field_617 = this.jdField_field_89_of_type_Class_988.field_1274.field_617;
      }
      if (localclass_988.field_1273.field_615 > this.jdField_field_89_of_type_Class_988.field_1273.field_615) {
        localclass_988.field_1273.field_615 = this.jdField_field_89_of_type_Class_988.field_1273.field_615;
      }
      if (localclass_988.field_1273.field_616 > this.jdField_field_89_of_type_Class_988.field_1273.field_616) {
        localclass_988.field_1273.field_616 = this.jdField_field_89_of_type_Class_988.field_1273.field_616;
      }
      if (localclass_988.field_1273.field_617 > this.jdField_field_89_of_type_Class_988.field_1273.field_617) {
        localclass_988.field_1273.field_617 = this.jdField_field_89_of_type_Class_988.field_1273.field_617;
      }
    }
    return localclass_988;
  }
  
  public final void o1()
  {
    this.jdField_field_89_of_type_Float = 0.0F;
    float f;
    if ((f = this.jdField_field_89_of_type_Class_988.field_1274.length()) > this.jdField_field_89_of_type_Float) {
      this.jdField_field_89_of_type_Float = f;
    }
    if ((f = this.jdField_field_89_of_type_Class_988.field_1273.length()) > this.jdField_field_89_of_type_Float) {
      this.jdField_field_89_of_type_Float = f;
    }
  }
  
  public final void b23(String paramString)
  {
    if ((this.jdField_field_90_of_type_JavaUtilList == null) || (this.jdField_field_89_of_type_Class_1034 == null)) {
      throw new AnimationNotFoundException("Animation " + this.jdField_field_89_of_type_Class_1034 + " not found in " + this.jdField_field_90_of_type_JavaUtilList);
    }
    if (!paramString.equals(this.jdField_field_89_of_type_Class_1034.field_1303))
    {
      localIterator = this.jdField_field_90_of_type_JavaUtilList.iterator();
      while (localIterator.hasNext())
      {
        class_1034 localclass_1034;
        if ((localclass_1034 = (class_1034)localIterator.next()).field_1303.equals(paramString))
        {
          this.jdField_field_89_of_type_Class_1034 = localclass_1034;
          return;
        }
      }
    }
    Iterator localIterator = this.jdField_field_89_of_type_JavaUtilList.iterator();
    while (localIterator.hasNext()) {
      ((class_984)localIterator.next()).b23(paramString);
    }
  }
  
  public static void p1() {}
  
  public final void a156(List paramList)
  {
    this.jdField_field_90_of_type_JavaUtilList = paramList;
  }
  
  public final void a157(class_988 paramclass_988)
  {
    this.jdField_field_89_of_type_Class_988 = paramclass_988;
  }
  
  public final void a158(Vector4f paramVector4f)
  {
    this.jdField_field_92_of_type_JavaxVecmathVector4f = paramVector4f;
  }
  
  public final void a159(Vector3f paramVector3f)
  {
    this.jdField_field_92_of_type_JavaxVecmathVector3f = paramVector3f;
  }
  
  public final void b24(Vector3f paramVector3f)
  {
    this.jdField_field_90_of_type_JavaxVecmathVector3f = paramVector3f;
  }
  
  public final void q()
  {
    this.field_94 = true;
  }
  
  public final void a160(class_1393 paramclass_1393)
  {
    this.jdField_field_89_of_type_Class_1393 = paramclass_1393;
  }
  
  public final void c11(String paramString)
  {
    this.jdField_field_89_of_type_JavaLangString = paramString;
  }
  
  public void b25(class_984 paramclass_984)
  {
    this.jdField_field_89_of_type_Class_984 = paramclass_984;
  }
  
  public final void a161(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    this.jdField_field_89_of_type_ComBulletphysicsLinearmathTransform.origin.set(paramFloat1, paramFloat2, paramFloat3);
  }
  
  public final void c12(Vector3f paramVector3f)
  {
    this.jdField_field_89_of_type_ComBulletphysicsLinearmathTransform.origin.set(paramVector3f);
  }
  
  public final void b26(Vector4f paramVector4f)
  {
    this.jdField_field_89_of_type_JavaxVecmathVector4f = paramVector4f;
  }
  
  public final void b27(float paramFloat)
  {
    this.jdField_field_89_of_type_ComBulletphysicsLinearmathTransform.basis.setIdentity();
    float f = paramFloat;
    paramFloat = this;
    Matrix3f localMatrix3f1 = new Matrix3f();
    Matrix3f localMatrix3f2 = new Matrix3f();
    Matrix3f localMatrix3f3 = new Matrix3f();
    localMatrix3f1.setIdentity();
    localMatrix3f2.setIdentity();
    localMatrix3f3.setIdentity();
    localMatrix3f1.rotX((float)Math.toRadians(0.0D));
    localMatrix3f2.rotY((float)Math.toRadians(0.0D));
    localMatrix3f3.rotZ((float)Math.toRadians(f));
    paramFloat.jdField_field_89_of_type_ComBulletphysicsLinearmathTransform.basis.mul(localMatrix3f1);
    paramFloat.jdField_field_89_of_type_ComBulletphysicsLinearmathTransform.basis.mul(localMatrix3f2);
    paramFloat.jdField_field_89_of_type_ComBulletphysicsLinearmathTransform.basis.mul(localMatrix3f3);
  }
  
  public final void b28(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    this.jdField_field_89_of_type_JavaxVecmathVector3f.set(paramFloat1, paramFloat2, paramFloat3);
  }
  
  public final void g4(int paramInt)
  {
    this.jdField_field_89_of_type_Int = paramInt;
  }
  
  public String toString()
  {
    return this.jdField_field_89_of_type_JavaLangString;
  }
  
  public void r()
  {
    a149(this);
  }
  
  public void a12(class_941 paramclass_941)
  {
    Iterator localIterator = this.jdField_field_89_of_type_JavaUtilList.iterator();
    while (localIterator.hasNext()) {
      ((class_984)localIterator.next()).a12(paramclass_941);
    }
  }
  
  static
  {
    jdField_field_89_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(16);
    BufferUtils.createFloatBuffer(jdField_field_89_of_type_ArrayOfFloat.length);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_984
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */