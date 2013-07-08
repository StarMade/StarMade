import com.bulletphysics.linearmath.Transform;
import java.util.Iterator;
import java.util.List;
import javax.vecmath.Color4f;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import org.lwjgl.opengl.GL11;
import org.schema.game.common.data.world.Universe;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.graphicsengine.forms.Mesh;

public final class class_834
  implements class_965
{
  private static final Vector4f jdField_field_98_of_type_JavaxVecmathVector4f = new Vector4f(1.0F, 1.0F, 1.0F, 1.0F);
  private Mesh jdField_field_98_of_type_OrgSchemaSchineGraphicsengineFormsMesh;
  private class_830 jdField_field_98_of_type_Class_830;
  private class_828 jdField_field_98_of_type_Class_828;
  private class_840 jdField_field_98_of_type_Class_840;
  private float jdField_field_106_of_type_Float;
  private class_48 jdField_field_106_of_type_Class_48;
  private Vector3f jdField_field_98_of_type_JavaxVecmathVector3f = new Vector3f();
  private Vector3f jdField_field_106_of_type_JavaxVecmathVector3f = new Vector3f();
  private class_810 jdField_field_98_of_type_Class_810;
  private class_840[] jdField_field_98_of_type_ArrayOfClass_840;
  public float field_98;
  public class_48 field_98;
  private Transform jdField_field_98_of_type_ComBulletphysicsLinearmathTransform = new Transform();
  private Transform jdField_field_106_of_type_ComBulletphysicsLinearmathTransform = new Transform();
  private Mesh jdField_field_106_of_type_OrgSchemaSchineGraphicsengineFormsMesh;
  
  public class_834()
  {
    this.jdField_field_98_of_type_Class_48 = new class_48();
    this.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
    this.jdField_field_98_of_type_ArrayOfClass_840 = new class_840[class_810.values().length];
    for (int i = 0; i < this.jdField_field_98_of_type_ArrayOfClass_840.length; i++)
    {
      this.jdField_field_98_of_type_ArrayOfClass_840[i] = new class_840();
      this.jdField_field_98_of_type_ArrayOfClass_840[i].field_1087.set(class_810.values()[i].jdField_field_1072_of_type_JavaxVecmathVector4f);
    }
    new class_840();
  }
  
  public final void a() {}
  
  public final void b()
  {
    this.jdField_field_98_of_type_Class_840 = this.jdField_field_98_of_type_ArrayOfClass_840[this.jdField_field_98_of_type_Class_810.ordinal()];
    class_834 localclass_834 = this;
    GlUtil.d1();
    localclass_834.jdField_field_98_of_type_OrgSchemaSchineGraphicsengineFormsMesh.h1();
    localclass_834.jdField_field_98_of_type_JavaxVecmathVector3f.set(localclass_834.jdField_field_106_of_type_Class_48.field_475 * Universe.getSectorSizeWithMargin(), localclass_834.jdField_field_106_of_type_Class_48.field_476 * Universe.getSectorSizeWithMargin(), localclass_834.jdField_field_106_of_type_Class_48.field_477 * Universe.getSectorSizeWithMargin());
    localclass_834.jdField_field_106_of_type_JavaxVecmathVector3f.set(localclass_834.jdField_field_98_of_type_Class_48.field_475 * Universe.getSectorSizeWithMargin(), localclass_834.jdField_field_98_of_type_Class_48.field_476 * Universe.getSectorSizeWithMargin(), localclass_834.jdField_field_98_of_type_Class_48.field_477 * Universe.getSectorSizeWithMargin());
    localclass_834.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
    localclass_834.jdField_field_106_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
    if (localclass_834.jdField_field_98_of_type_Class_48.a4() > 0.0F)
    {
      if (localclass_834.jdField_field_106_of_type_Class_48.a4() > 0.0F)
      {
        localclass_834.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform.origin.add(localclass_834.jdField_field_106_of_type_JavaxVecmathVector3f);
        localclass_834.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform.basis.rotX(6.283186F * localclass_834.jdField_field_98_of_type_Float);
        Vector3f localVector3f;
        (localVector3f = new Vector3f()).sub(localclass_834.jdField_field_98_of_type_JavaxVecmathVector3f, localclass_834.jdField_field_106_of_type_JavaxVecmathVector3f);
        localclass_834.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform.origin.add(localVector3f);
        localclass_834.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform.basis.transform(localclass_834.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform.origin);
      }
    }
    else
    {
      localclass_834.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform.basis.rotX(6.283186F * localclass_834.jdField_field_98_of_type_Float);
      localclass_834.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform.origin.set(localclass_834.jdField_field_98_of_type_JavaxVecmathVector3f);
      localclass_834.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform.basis.transform(localclass_834.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform.origin);
    }
    GlUtil.a31(localclass_834.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform.origin);
    if (localclass_834.jdField_field_106_of_type_Class_48.a3(0, 0, 0))
    {
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
    }
    else
    {
      GL11.glDisable(3042);
    }
    class_1376.field_1554.field_1578 = localclass_834.jdField_field_98_of_type_Class_830;
    class_1376.field_1554.b();
    localclass_834.jdField_field_98_of_type_OrgSchemaSchineGraphicsengineFormsMesh.i();
    class_1376.field_1554.d();
    localclass_834.jdField_field_98_of_type_OrgSchemaSchineGraphicsengineFormsMesh.m1();
    localclass_834.jdField_field_106_of_type_OrgSchemaSchineGraphicsengineFormsMesh.h1();
    if (!localclass_834.jdField_field_106_of_type_Class_48.a3(0, 0, 0))
    {
      GL11.glDepthRange(0.9999998807907105D, 1.0D);
      GL11.glDisable(2929);
      GL11.glDepthMask(false);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 1);
    }
    class_1376.field_1555.field_1578 = localclass_834.jdField_field_98_of_type_Class_828;
    class_1376.field_1555.b();
    GL11.glEnable(2884);
    GL11.glCullFace(1029);
    GL11.glCullFace(1028);
    localclass_834.jdField_field_106_of_type_OrgSchemaSchineGraphicsengineFormsMesh.i();
    GL11.glCullFace(1029);
    localclass_834.jdField_field_106_of_type_OrgSchemaSchineGraphicsengineFormsMesh.i();
    GL11.glCullFace(1029);
    class_1376.field_1555.d();
    GL11.glEnable(2929);
    GL11.glDisable(3042);
    GL11.glEnable(2884);
    GlUtil.c2();
    GL11.glDepthRange(0.0D, 1.0D);
    GL11.glDepthMask(true);
    localclass_834.jdField_field_106_of_type_OrgSchemaSchineGraphicsengineFormsMesh.m1();
  }
  
  public final int a44()
  {
    return class_969.a2().a5(this.jdField_field_98_of_type_Class_810.field_1073).a153().a1().field_1592;
  }
  
  public final int b6()
  {
    return class_969.a2().a5(this.jdField_field_98_of_type_Class_810.jdField_field_1072_of_type_JavaLangString).a153().a1().field_1592;
  }
  
  public final int c3()
  {
    return class_969.a2().a5(this.jdField_field_98_of_type_Class_810.field_1074).a153().a1().field_1592;
  }
  
  public final int d1()
  {
    return class_969.a2().a5(this.jdField_field_98_of_type_Class_810.field_1075).a153().a1().field_1592;
  }
  
  public final void c()
  {
    this.jdField_field_106_of_type_OrgSchemaSchineGraphicsengineFormsMesh = ((Mesh)class_969.a2().a4("Sphere").a152().iterator().next());
    this.jdField_field_98_of_type_OrgSchemaSchineGraphicsengineFormsMesh = ((Mesh)class_969.a2().a4("Planet").a152().iterator().next());
    this.jdField_field_98_of_type_Class_830 = new class_830(this, (byte)0);
    this.jdField_field_98_of_type_Class_828 = new class_828(this, (byte)0);
  }
  
  public final void a45(class_48 paramclass_48)
  {
    this.jdField_field_106_of_type_Class_48 = paramclass_48;
  }
  
  public final void a46(class_810 paramclass_810)
  {
    this.jdField_field_98_of_type_Class_810 = paramclass_810;
  }
  
  public final void a1(class_941 paramclass_941)
  {
    this.jdField_field_106_of_type_Float += paramclass_941.a();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_834
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */