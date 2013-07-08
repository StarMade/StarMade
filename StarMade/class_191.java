import com.bulletphysics.linearmath.Transform;
import java.io.PrintStream;
import javax.vecmath.Matrix3f;
import javax.vecmath.Tuple3f;
import javax.vecmath.Vector3f;
import org.lwjgl.input.Keyboard;
import org.schema.game.common.controller.SegmentController;
import org.schema.schine.graphicsengine.camera.Camera;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.network.client.ClientStateInterface;

public final class class_191
  extends Camera
  implements class_197
{
  boolean jdField_field_89_of_type_Boolean;
  private Matrix3f jdField_field_89_of_type_JavaxVecmathMatrix3f;
  class_193 jdField_field_89_of_type_Class_193;
  private Camera jdField_field_89_of_type_OrgSchemaSchineGraphicsengineCameraCamera;
  private Vector3f jdField_field_89_of_type_JavaxVecmathVector3f = new Vector3f(0.0F, 0.0F, 1.0F);
  private Vector3f jdField_field_90_of_type_JavaxVecmathVector3f = new Vector3f(1.0F, 0.0F, 0.0F);
  private Vector3f jdField_field_92_of_type_JavaxVecmathVector3f = new Vector3f(0.0F, 1.0F, 0.0F);
  public boolean field_90;
  private boolean jdField_field_92_of_type_Boolean = false;
  private Matrix3f jdField_field_90_of_type_JavaxVecmathMatrix3f;
  private float[] jdField_field_89_of_type_ArrayOfFloat;
  int jdField_field_89_of_type_Int;
  final SegmentController jdField_field_89_of_type_OrgSchemaGameCommonControllerSegmentController;
  private Camera jdField_field_90_of_type_OrgSchemaSchineGraphicsengineCameraCamera;
  private class_1008 jdField_field_89_of_type_Class_1008;
  
  public class_191(class_457 paramclass_457, Camera paramCamera, class_796 paramclass_796)
  {
    super(new class_201(paramclass_457));
    this.jdField_field_89_of_type_OrgSchemaGameCommonControllerSegmentController = paramclass_457.a1();
    this.jdField_field_89_of_type_ArrayOfFloat = new float[6];
    this.jdField_field_89_of_type_ArrayOfFloat[4] = 0.0F;
    this.jdField_field_89_of_type_ArrayOfFloat[5] = 3.141593F;
    this.jdField_field_89_of_type_ArrayOfFloat[2] = -1.570796F;
    this.jdField_field_89_of_type_ArrayOfFloat[3] = 1.570796F;
    this.jdField_field_89_of_type_ArrayOfFloat[0] = -1.570796F;
    this.jdField_field_89_of_type_ArrayOfFloat[1] = 1.570796F;
    this.jdField_field_90_of_type_OrgSchemaSchineGraphicsengineCameraCamera = paramCamera;
    this.jdField_field_92_of_type_Boolean = true;
    paramCamera = paramclass_796.b1();
    paramCamera = (byte)Math.max(0, Math.min(5, paramCamera));
    this.jdField_field_89_of_type_Int = org.schema.game.common.data.element.Element.orientationBackMapping[paramCamera];
    if ((this.jdField_field_89_of_type_Int > 1) && (this.jdField_field_89_of_type_Int < 4))
    {
      (paramclass_796 = new Matrix3f()).setIdentity();
      paramclass_796.rotY(1.570796F);
      this.jdField_field_89_of_type_JavaxVecmathMatrix3f = new Matrix3f();
      this.jdField_field_89_of_type_JavaxVecmathMatrix3f.setIdentity();
      System.err.println("ROTATION_X: " + this.jdField_field_89_of_type_ArrayOfFloat[this.jdField_field_89_of_type_Int] + " oo " + this.jdField_field_89_of_type_Int + " orig: " + paramCamera);
      this.jdField_field_89_of_type_JavaxVecmathMatrix3f.rotX(this.jdField_field_89_of_type_ArrayOfFloat[this.jdField_field_89_of_type_Int]);
      this.jdField_field_89_of_type_JavaxVecmathMatrix3f.mul(paramclass_796);
      this.jdField_field_90_of_type_JavaxVecmathMatrix3f = new Matrix3f();
      this.jdField_field_90_of_type_JavaxVecmathMatrix3f.setIdentity();
      this.jdField_field_90_of_type_JavaxVecmathMatrix3f.rotX(-this.jdField_field_89_of_type_ArrayOfFloat[this.jdField_field_89_of_type_Int]);
      this.jdField_field_90_of_type_JavaxVecmathMatrix3f.mul(paramclass_796);
    }
    else
    {
      this.jdField_field_89_of_type_JavaxVecmathMatrix3f = new Matrix3f();
      this.jdField_field_89_of_type_JavaxVecmathMatrix3f.setIdentity();
      System.err.println("ROTATION_Y: " + this.jdField_field_89_of_type_ArrayOfFloat[this.jdField_field_89_of_type_Int] + " oo " + this.jdField_field_89_of_type_Int + " orig: " + paramCamera);
      this.jdField_field_89_of_type_JavaxVecmathMatrix3f.rotY(this.jdField_field_89_of_type_ArrayOfFloat[this.jdField_field_89_of_type_Int]);
      this.jdField_field_90_of_type_JavaxVecmathMatrix3f = new Matrix3f();
      this.jdField_field_90_of_type_JavaxVecmathMatrix3f.setIdentity();
      this.jdField_field_90_of_type_JavaxVecmathMatrix3f.rotY(-this.jdField_field_89_of_type_ArrayOfFloat[this.jdField_field_89_of_type_Int]);
    }
    this.jdField_field_89_of_type_Class_193 = new class_193(this, new class_201(paramclass_457));
    this.jdField_field_89_of_type_OrgSchemaSchineGraphicsengineCameraCamera = new Camera(new class_201(paramclass_457));
    a2();
    this.jdField_field_89_of_type_Class_193.a2();
    this.jdField_field_89_of_type_OrgSchemaSchineGraphicsengineCameraCamera.a2();
  }
  
  public final Camera a76()
  {
    return this.jdField_field_89_of_type_Class_193;
  }
  
  public final Vector3f b9()
  {
    Transform localTransform = new Transform(this.jdField_field_89_of_type_Class_193.getWorldTransform());
    return GlUtil.c(new Vector3f(), localTransform);
  }
  
  public final void a2()
  {
    super.a2();
    if (this.jdField_field_92_of_type_Boolean) {
      getWorldTransform().basis.set(this.jdField_field_89_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransform().basis);
    }
  }
  
  public final synchronized void a12(class_941 paramclass_941)
  {
    if (this.jdField_field_92_of_type_Boolean)
    {
      GlUtil.e(this.jdField_field_90_of_type_JavaxVecmathVector3f, this.jdField_field_89_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransform());
      GlUtil.f(this.jdField_field_92_of_type_JavaxVecmathVector3f, this.jdField_field_89_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransform());
      GlUtil.c(this.jdField_field_89_of_type_JavaxVecmathVector3f, this.jdField_field_89_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransform());
      this.jdField_field_92_of_type_Boolean = false;
    }
    super.a12(paramclass_941);
    Object localObject;
    if (class_1046.a1())
    {
      ((class_201)this.jdField_field_89_of_type_Class_193.a184()).a85().b1(((class_201)a184()).a85());
      ((class_201)this.jdField_field_89_of_type_OrgSchemaSchineGraphicsengineCameraCamera.a184()).a85().b1(((class_201)a184()).a85());
      this.jdField_field_89_of_type_Class_193.a12(paramclass_941);
      this.jdField_field_89_of_type_OrgSchemaSchineGraphicsengineCameraCamera.a12(paramclass_941);
      if (this.jdField_field_89_of_type_OrgSchemaGameCommonControllerSegmentController != null) {
        if (Keyboard.isKeyDown(class_367.field_731.a5()))
        {
          b24(this.jdField_field_89_of_type_OrgSchemaSchineGraphicsengineCameraCamera.e7());
          c12(this.jdField_field_89_of_type_OrgSchemaSchineGraphicsengineCameraCamera.f5());
          a159(this.jdField_field_89_of_type_OrgSchemaSchineGraphicsengineCameraCamera.c10());
        }
        else if (this.jdField_field_90_of_type_Boolean)
        {
          localObject = new Vector3f();
          Vector3f localVector3f1 = new Vector3f();
          Vector3f localVector3f2 = new Vector3f();
          GlUtil.e((Vector3f)localObject, this.jdField_field_89_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransform());
          GlUtil.f(localVector3f1, this.jdField_field_89_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransform());
          GlUtil.c(localVector3f2, this.jdField_field_89_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransform());
          Transform localTransform1 = new Transform();
          GlUtil.a30(this.jdField_field_89_of_type_JavaxVecmathVector3f, localTransform1);
          GlUtil.d2(this.jdField_field_92_of_type_JavaxVecmathVector3f, localTransform1);
          GlUtil.c3(this.jdField_field_90_of_type_JavaxVecmathVector3f, localTransform1);
          Transform localTransform2 = new Transform();
          GlUtil.a30(localVector3f2, localTransform2);
          GlUtil.d2(localVector3f1, localTransform2);
          GlUtil.c3((Vector3f)localObject, localTransform2);
          Matrix3f localMatrix3f;
          (localMatrix3f = new Matrix3f()).sub(localTransform2.basis, localTransform1.basis);
          this.jdField_field_89_of_type_Class_193.getWorldTransform().basis.add(localMatrix3f);
          this.jdField_field_90_of_type_JavaxVecmathVector3f.set((Tuple3f)localObject);
          this.jdField_field_92_of_type_JavaxVecmathVector3f.set(localVector3f1);
          this.jdField_field_89_of_type_JavaxVecmathVector3f.set(localVector3f2);
        }
      }
    }
    if ((this.jdField_field_89_of_type_OrgSchemaGameCommonControllerSegmentController != null) && (!Keyboard.isKeyDown(157)) && (!Keyboard.isKeyDown(54)))
    {
      getWorldTransform().basis.set(this.jdField_field_89_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransform().basis);
      (localObject = new Matrix3f(this.jdField_field_90_of_type_JavaxVecmathMatrix3f)).invert();
      getWorldTransform().basis.mul((Matrix3f)localObject);
    }
    if ((this.jdField_field_89_of_type_Class_1008 != null) && (this.jdField_field_89_of_type_Class_1008.a4()))
    {
      this.jdField_field_89_of_type_Class_1008.a12(paramclass_941);
      getWorldTransform().set(this.jdField_field_89_of_type_Class_1008.getWorldTransform());
    }
    if (this.jdField_field_90_of_type_OrgSchemaSchineGraphicsengineCameraCamera != null)
    {
      a78(this.jdField_field_90_of_type_OrgSchemaSchineGraphicsengineCameraCamera);
      this.jdField_field_90_of_type_OrgSchemaSchineGraphicsengineCameraCamera = null;
    }
  }
  
  public final void a77(ClientStateInterface paramClientStateInterface, class_941 paramclass_941)
  {
    if (Keyboard.isKeyDown(class_367.field_731.a5())) {
      this.jdField_field_89_of_type_OrgSchemaSchineGraphicsengineCameraCamera.a77(paramClientStateInterface, paramclass_941);
    } else {
      this.jdField_field_89_of_type_Class_193.a77(paramClientStateInterface, paramclass_941);
    }
    super.a77(paramClientStateInterface, paramclass_941);
  }
  
  public final void b()
  {
    this.jdField_field_89_of_type_Boolean = true;
  }
  
  public final void a78(Camera paramCamera)
  {
    this.jdField_field_89_of_type_Class_1008 = new class_1008(paramCamera, this);
  }
  
  public final SegmentController a79()
  {
    return this.jdField_field_89_of_type_OrgSchemaGameCommonControllerSegmentController;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_191
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */