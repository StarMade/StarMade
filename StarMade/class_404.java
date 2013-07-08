import com.bulletphysics.linearmath.Transform;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.vecmath.AxisAngle4f;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3f;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.schema.common.FastMath;
import org.schema.schine.graphicsengine.camera.Camera;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.graphicsengine.forms.Mesh;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.objects.Sendable;

public final class class_404
  implements class_965
{
  private final class_750 jdField_field_98_of_type_Class_750;
  private class_1056 jdField_field_98_of_type_Class_1056;
  class_1028 jdField_field_98_of_type_Class_1028;
  class_297 jdField_field_98_of_type_Class_297;
  private boolean jdField_field_106_of_type_Boolean = true;
  private Vector3f jdField_field_98_of_type_JavaxVecmathVector3f = new Vector3f();
  private float jdField_field_98_of_type_Float;
  private class_371 jdField_field_98_of_type_Class_371;
  boolean jdField_field_98_of_type_Boolean;
  class_748 jdField_field_98_of_type_Class_748;
  class_941 jdField_field_98_of_type_Class_941;
  private Transform jdField_field_98_of_type_ComBulletphysicsLinearmathTransform = new Transform();
  private Transform jdField_field_106_of_type_ComBulletphysicsLinearmathTransform = new Transform();
  private Matrix3f jdField_field_98_of_type_JavaxVecmathMatrix3f = new Matrix3f();
  private Vector3f jdField_field_106_of_type_JavaxVecmathVector3f = new Vector3f(0.0F, 1.0F, 0.0F);
  private Vector3f field_108 = new Vector3f(0.0F, -1.0F, 0.0F);
  
  public class_404(class_750 paramclass_750)
  {
    this.jdField_field_98_of_type_Class_750 = paramclass_750;
    this.jdField_field_98_of_type_Class_371 = ((class_371)paramclass_750.getState());
    this.jdField_field_98_of_type_Class_297 = new class_297(paramclass_750.getWorldTransform(), a40() + " (" + this.jdField_field_98_of_type_Class_371.a51() + ")");
    this.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
  }
  
  public final void a() {}
  
  public final void b()
  {
    if (this.jdField_field_106_of_type_Boolean) {
      c();
    }
    if ((this.jdField_field_98_of_type_Class_748 == null) || (this.jdField_field_98_of_type_Class_941 == null)) {
      return;
    }
    if (this.jdField_field_98_of_type_Class_750.isHidden())
    {
      class_883.field_89.remove(this.jdField_field_98_of_type_Class_297);
      return;
    }
    GlUtil.c3(this.jdField_field_98_of_type_Class_750.a144().upAxisDirection[0], this.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform);
    GlUtil.d2(this.jdField_field_98_of_type_Class_750.a144().upAxisDirection[1], this.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform);
    GlUtil.a30(this.jdField_field_98_of_type_Class_750.a144().upAxisDirection[2], this.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform);
    this.jdField_field_98_of_type_Class_1056.a5(this.jdField_field_98_of_type_Class_941);
    Object localObject = this.jdField_field_98_of_type_Class_748.a8();
    Vector3f localVector3f = this.jdField_field_98_of_type_Class_748.c9();
    if ((!Mouse.isButtonDown(2)) && (!Keyboard.isKeyDown(54))) {
      if (((Vector3f)localObject).epsilonEquals(this.jdField_field_106_of_type_JavaxVecmathVector3f, 0.01F)) {
        this.jdField_field_98_of_type_Float = FastMath.a3(-localVector3f.field_615, -localVector3f.field_617);
      } else if (((Vector3f)localObject).epsilonEquals(this.field_108, 0.01F)) {
        this.jdField_field_98_of_type_Float = FastMath.a3(localVector3f.field_615, localVector3f.field_617);
      } else {
        this.jdField_field_98_of_type_Float = FastMath.a3(((Vector3f)localObject).field_615, ((Vector3f)localObject).field_617);
      }
    }
    localObject = new AxisAngle4f(this.jdField_field_106_of_type_JavaxVecmathVector3f, this.jdField_field_98_of_type_Float);
    this.jdField_field_98_of_type_JavaxVecmathMatrix3f.set((AxisAngle4f)localObject);
    if (this.jdField_field_98_of_type_Class_750 == this.jdField_field_98_of_type_Class_371.a3())
    {
      this.jdField_field_98_of_type_JavaxVecmathVector3f.set(class_969.a1().a83());
      this.jdField_field_98_of_type_JavaxVecmathVector3f.sub(this.jdField_field_98_of_type_Class_750.a145().origin);
      if (this.jdField_field_98_of_type_JavaxVecmathVector3f.length() < 0.2F)
      {
        class_883.field_89.remove(this.jdField_field_98_of_type_Class_297);
        return;
      }
    }
    if (!class_883.field_89.contains(this.jdField_field_98_of_type_Class_297)) {
      class_883.field_89.add(this.jdField_field_98_of_type_Class_297);
    }
    GlUtil.d1();
    this.jdField_field_106_of_type_ComBulletphysicsLinearmathTransform.set(this.jdField_field_98_of_type_Class_750.getWorldTransformClient());
    this.jdField_field_106_of_type_ComBulletphysicsLinearmathTransform.basis.mul(this.jdField_field_98_of_type_JavaxVecmathMatrix3f);
    GlUtil.b3(this.jdField_field_106_of_type_ComBulletphysicsLinearmathTransform);
    class_986.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform.set(this.jdField_field_106_of_type_ComBulletphysicsLinearmathTransform);
    localObject = (Mesh)class_969.a2().a4("Konata").a152().get(0);
    GlUtil.c4(0.0F, -0.1F, 0.0F);
    GlUtil.b5(1.0F, 1.0F, 1.0F);
    ((Mesh)localObject).a177().a9(this.jdField_field_98_of_type_Class_748.a140().a5());
    ((Mesh)localObject).e();
    GlUtil.c2();
  }
  
  final void d()
  {
    int i = this.jdField_field_98_of_type_Class_750.a3();
    synchronized (this.jdField_field_98_of_type_Class_371.getLocalAndRemoteObjectContainer().getLocalObjects())
    {
      Iterator localIterator = this.jdField_field_98_of_type_Class_371.getLocalAndRemoteObjectContainer().getLocalObjects().values().iterator();
      while (localIterator.hasNext())
      {
        Sendable localSendable;
        if ((((localSendable = (Sendable)localIterator.next()) instanceof class_748)) && (((class_748)localSendable).a3() == i))
        {
          this.jdField_field_98_of_type_Class_748 = ((class_748)localSendable);
          return;
        }
      }
    }
    throw new IllegalArgumentException("[WARNING] Could not find Player for ID " + localObject1);
  }
  
  final String a40()
  {
    if (this.jdField_field_98_of_type_Class_748 == null) {
      return "<nobody>";
    }
    return this.jdField_field_98_of_type_Class_748.getName();
  }
  
  public final void c()
  {
    try
    {
      d();
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      System.err.println(localIllegalArgumentException.getMessage());
    }
    Object localObject = ((Mesh)class_969.a2().a4("Konata").a152().get(0)).a177();
    this.jdField_field_98_of_type_Class_1056 = new class_1056((class_1386)localObject);
    this.jdField_field_98_of_type_Class_1028 = this.jdField_field_98_of_type_Class_1056.a();
    localObject = ((class_1386)localObject).a8().a7().jdField_field_98_of_type_JavaLangString;
    class_1028 tmp81_67 = this.jdField_field_98_of_type_Class_1028;
    tmp81_67.a1(tmp81_67.field_1293.a3().a3((String)localObject));
    try
    {
      this.jdField_field_98_of_type_Class_1028.a2("default", 0.15F);
    }
    catch (Exception localException) {}
    this.jdField_field_106_of_type_Boolean = false;
  }
  
  public final void e()
  {
    class_883.field_89.remove(this.jdField_field_98_of_type_Class_297);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_404
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */