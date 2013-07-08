import com.bulletphysics.linearmath.Transform;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
import javax.vecmath.Matrix3f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;
import org.lwjgl.opengl.GL11;
import org.schema.common.FastMath;
import org.schema.schine.graphicsengine.core.GlUtil;

public final class class_986
  implements class_965
{
  private Vector3f jdField_field_98_of_type_JavaxVecmathVector3f = new Vector3f();
  private Quat4f jdField_field_98_of_type_JavaxVecmathQuat4f = new Quat4f();
  private Vector3f jdField_field_106_of_type_JavaxVecmathVector3f = new Vector3f();
  private Quat4f jdField_field_106_of_type_JavaxVecmathQuat4f = new Quat4f();
  private Vector3f jdField_field_108_of_type_JavaxVecmathVector3f = new Vector3f();
  private Quat4f jdField_field_108_of_type_JavaxVecmathQuat4f = new Quat4f();
  private Vector3f jdField_field_109_of_type_JavaxVecmathVector3f = new Vector3f();
  private Quat4f jdField_field_109_of_type_JavaxVecmathQuat4f = new Quat4f();
  private final ArrayList jdField_field_98_of_type_JavaUtilArrayList = new ArrayList();
  public int field_98;
  private class_986 jdField_field_98_of_type_Class_986;
  public String field_98;
  private float jdField_field_98_of_type_Float;
  private float jdField_field_106_of_type_Float;
  private float jdField_field_108_of_type_Float;
  private class_47 jdField_field_98_of_type_Class_47;
  public static Transform field_98;
  private Quat4f field_110;
  
  public class_986(int paramInt, String paramString)
  {
    new Vector();
    this.jdField_field_98_of_type_Int = -1;
    this.jdField_field_98_of_type_Float = FastMath.a8();
    this.jdField_field_106_of_type_Float = FastMath.a8();
    this.jdField_field_108_of_type_Float = FastMath.a8();
    this.jdField_field_98_of_type_Class_47 = new class_47(this.jdField_field_98_of_type_Float, this.jdField_field_106_of_type_Float, this.jdField_field_108_of_type_Float, 1.0F);
    this.field_110 = new Quat4f();
    this.jdField_field_98_of_type_Int = paramInt;
    this.jdField_field_98_of_type_JavaLangString = paramString;
    new class_37();
  }
  
  public final void a() {}
  
  public final void b()
  {
    GlUtil.d1();
    GL11.glDisable(2896);
    GL11.glEnable(2903);
    GL11.glDisable(2929);
    GL11.glBegin(1);
    GlUtil.a38(this.jdField_field_98_of_type_Class_47.field_471, this.jdField_field_98_of_type_Class_47.field_472, this.jdField_field_98_of_type_Class_47.field_473, this.jdField_field_98_of_type_Class_47.field_474);
    GL11.glVertex3f(0.0F, 0.0F, 0.0F);
    GL11.glVertex3f(100.0F, 0.0F, 0.0F);
    GL11.glVertex3f(100.0F, 0.0F, 0.0F);
    GL11.glVertex3f(90.0F, 10.0F, 0.0F);
    GL11.glVertex3f(100.0F, 0.0F, 0.0F);
    GL11.glVertex3f(90.0F, -10.0F, 0.0F);
    GL11.glEnd();
    GL11.glDisable(2903);
    GL11.glEnable(2929);
    GL11.glEnable(2896);
    Iterator localIterator = this.jdField_field_98_of_type_JavaUtilArrayList.iterator();
    while (localIterator.hasNext())
    {
      class_986 localclass_986;
      if (((localclass_986 = (class_986)localIterator.next()) instanceof class_965))
      {
        GlUtil.d1();
        localclass_986.b();
        GlUtil.c2();
      }
    }
    GlUtil.c2();
  }
  
  public final ArrayList a57()
  {
    return this.jdField_field_98_of_type_JavaUtilArrayList;
  }
  
  final void a14(Transform paramTransform)
  {
    this.field_110.mul(this.jdField_field_109_of_type_JavaxVecmathQuat4f, this.jdField_field_106_of_type_JavaxVecmathQuat4f);
    Vector3f localVector3f1 = new Vector3f();
    Vector3f localVector3f2 = new Vector3f();
    class_29.a3(this.field_110, this.jdField_field_106_of_type_JavaxVecmathVector3f, localVector3f2);
    localVector3f1.add(this.jdField_field_109_of_type_JavaxVecmathVector3f, localVector3f2);
    class_949.field_1221.b1();
    paramTransform.setIdentity();
    paramTransform.origin.set(localVector3f1);
    paramTransform.basis.set(this.field_110);
  }
  
  public final class_986 a58()
  {
    return this.jdField_field_98_of_type_Class_986;
  }
  
  public final void c() {}
  
  final void d()
  {
    this.jdField_field_108_of_type_JavaxVecmathVector3f.set(this.jdField_field_98_of_type_JavaxVecmathVector3f);
    this.jdField_field_108_of_type_JavaxVecmathQuat4f.set(this.jdField_field_98_of_type_JavaxVecmathQuat4f);
    Iterator localIterator = this.jdField_field_98_of_type_JavaUtilArrayList.iterator();
    while (localIterator.hasNext()) {
      ((class_986)localIterator.next()).d();
    }
  }
  
  public final void a59(Vector3f paramVector3f, Quat4f paramQuat4f)
  {
    this.jdField_field_108_of_type_JavaxVecmathVector3f.add(paramVector3f);
    this.jdField_field_108_of_type_JavaxVecmathQuat4f.mul(paramQuat4f);
  }
  
  final void e()
  {
    this.jdField_field_98_of_type_JavaxVecmathVector3f.set(this.jdField_field_108_of_type_JavaxVecmathVector3f);
    this.jdField_field_98_of_type_JavaxVecmathQuat4f.set(this.jdField_field_108_of_type_JavaxVecmathQuat4f);
    this.jdField_field_106_of_type_JavaxVecmathVector3f.set(this.jdField_field_109_of_type_JavaxVecmathVector3f);
    this.jdField_field_106_of_type_JavaxVecmathVector3f.negate();
    this.jdField_field_106_of_type_JavaxVecmathQuat4f.set(this.jdField_field_109_of_type_JavaxVecmathQuat4f);
    this.jdField_field_106_of_type_JavaxVecmathQuat4f.inverse();
    Iterator localIterator = this.jdField_field_98_of_type_JavaUtilArrayList.iterator();
    while (localIterator.hasNext()) {
      ((class_986)localIterator.next()).e();
    }
  }
  
  public final void b8(Vector3f paramVector3f, Quat4f paramQuat4f)
  {
    this.jdField_field_98_of_type_JavaxVecmathVector3f.set(paramVector3f);
    this.jdField_field_98_of_type_JavaxVecmathQuat4f.set(paramQuat4f);
    this.jdField_field_108_of_type_JavaxVecmathVector3f.set(paramVector3f);
    this.jdField_field_108_of_type_JavaxVecmathQuat4f.set(paramQuat4f);
  }
  
  public final void a60(class_986 paramclass_986)
  {
    this.jdField_field_98_of_type_Class_986 = paramclass_986;
  }
  
  public static void f() {}
  
  public final String toString()
  {
    return "Bone - " + this.jdField_field_98_of_type_JavaLangString + " - pos: " + this.jdField_field_108_of_type_JavaxVecmathVector3f + ", rot: " + this.jdField_field_108_of_type_JavaxVecmathQuat4f;
  }
  
  final void a1(class_941 paramclass_941)
  {
    Object localObject = this;
    if (this.jdField_field_98_of_type_Class_986 != null)
    {
      Quat4f localQuat4f3 = new Quat4f();
      Quat4f localQuat4f2 = ((class_986)localObject).jdField_field_108_of_type_JavaxVecmathQuat4f;
      Quat4f localQuat4f1;
      float f3 = (localQuat4f1 = ((class_986)localObject).jdField_field_98_of_type_Class_986.jdField_field_109_of_type_JavaxVecmathQuat4f).field_596;
      float f4 = localQuat4f1.field_597;
      float f5 = localQuat4f1.field_598;
      float f1 = localQuat4f1.field_599;
      float f6 = localQuat4f2.field_599;
      float f7 = localQuat4f2.field_596;
      float f8 = localQuat4f2.field_597;
      float f2 = localQuat4f2.field_598;
      localQuat4f3.field_596 = (f3 * f6 + f4 * f2 - f5 * f8 + f1 * f7);
      localQuat4f3.field_597 = (-f3 * f2 + f4 * f6 + f5 * f7 + f1 * f8);
      localQuat4f3.field_598 = (f3 * f8 - f4 * f7 + f5 * f6 + f1 * f2);
      localQuat4f3.field_599 = (-f3 * f7 - f4 * f8 - f5 * f2 + f1 * f6);
      ((class_986)localObject).jdField_field_109_of_type_JavaxVecmathQuat4f = localQuat4f3;
      ((class_986)localObject).jdField_field_109_of_type_JavaxVecmathVector3f.set(class_29.a3(((class_986)localObject).jdField_field_98_of_type_Class_986.jdField_field_109_of_type_JavaxVecmathQuat4f, ((class_986)localObject).jdField_field_108_of_type_JavaxVecmathVector3f, new Vector3f()));
      ((class_986)localObject).jdField_field_109_of_type_JavaxVecmathVector3f.add(((class_986)localObject).jdField_field_98_of_type_Class_986.jdField_field_109_of_type_JavaxVecmathVector3f);
    }
    else
    {
      ((class_986)localObject).jdField_field_109_of_type_JavaxVecmathQuat4f.set(((class_986)localObject).jdField_field_108_of_type_JavaxVecmathQuat4f);
      ((class_986)localObject).jdField_field_109_of_type_JavaxVecmathVector3f.set(((class_986)localObject).jdField_field_108_of_type_JavaxVecmathVector3f);
    }
    localObject = this.jdField_field_98_of_type_JavaUtilArrayList.iterator();
    while (((Iterator)localObject).hasNext()) {
      ((class_986)((Iterator)localObject).next()).a1(paramclass_941);
    }
  }
  
  static
  {
    jdField_field_98_of_type_ComBulletphysicsLinearmathTransform = new Transform();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_986
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */