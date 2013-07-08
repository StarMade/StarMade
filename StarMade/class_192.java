import com.bulletphysics.linearmath.Transform;
import java.nio.FloatBuffer;
import java.util.Iterator;
import java.util.List;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.util.vector.Matrix4f;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.network.client.ClientState;

public final class class_192
  extends class_1363
{
  private Transform jdField_field_90_of_type_ComBulletphysicsLinearmathTransform = new Transform();
  private Transform jdField_field_92_of_type_ComBulletphysicsLinearmathTransform = new Transform();
  private Matrix3f jdField_field_89_of_type_JavaxVecmathMatrix3f = new Matrix3f();
  private static Transform field_93 = new Transform();
  private static FloatBuffer jdField_field_90_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(16);
  private static float[] jdField_field_89_of_type_ArrayOfFloat = new float[16];
  private static Transform field_94 = new Transform();
  private static FloatBuffer jdField_field_92_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(16);
  private static float[] jdField_field_90_of_type_ArrayOfFloat = new float[16];
  private short jdField_field_89_of_type_Short = 1;
  private int jdField_field_89_of_type_Int = 0;
  private int jdField_field_90_of_type_Int = 0;
  private boolean jdField_field_89_of_type_Boolean;
  public static class_1383 field_89;
  
  public class_192(ClientState paramClientState)
  {
    super(paramClientState);
    this.jdField_field_90_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
    this.jdField_field_92_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
  }
  
  public static void e()
  {
    Matrix4f localMatrix4f = class_969.field_1259;
    jdField_field_90_of_type_JavaNioFloatBuffer.rewind();
    localMatrix4f.store(jdField_field_90_of_type_JavaNioFloatBuffer);
    jdField_field_90_of_type_JavaNioFloatBuffer.rewind();
    jdField_field_90_of_type_JavaNioFloatBuffer.get(jdField_field_89_of_type_ArrayOfFloat);
    field_93.setFromOpenGLMatrix(jdField_field_89_of_type_ArrayOfFloat);
    field_93.origin.set(0.0F, 0.0F, 0.0F);
  }
  
  public final void a2() {}
  
  public final void b()
  {
    if (h2()) {
      return;
    }
    if (!this.jdField_field_89_of_type_Boolean) {
      this.jdField_field_89_of_type_Boolean = true;
    }
    GlUtil.d1();
    this.field_95 = false;
    r();
    if (this.field_96) {
      l();
    }
    short s = this.jdField_field_89_of_type_Short;
    Object localObject1 = this;
    if (s != 0)
    {
      Object localObject2 = class_969.field_1259;
      jdField_field_92_of_type_JavaNioFloatBuffer.rewind();
      ((Matrix4f)localObject2).store(jdField_field_92_of_type_JavaNioFloatBuffer);
      jdField_field_92_of_type_JavaNioFloatBuffer.rewind();
      jdField_field_92_of_type_JavaNioFloatBuffer.get(jdField_field_90_of_type_ArrayOfFloat);
      field_94.setFromOpenGLMatrix(jdField_field_90_of_type_ArrayOfFloat);
      GL11.glClear(256);
      class_1363.j();
      GL11.glDisable(2929);
      GL15.glBindBuffer(34962, 0);
      GlUtil.b3(field_94);
      ElementKeyMap.getInfo(s).getBuildIconNum();
      GlUtil.d1();
      GlUtil.c4(0.0F, 0.0F, 0.0F);
      GlUtil.b5(32.0F, -32.0F, 32.0F);
      if (ElementKeyMap.getInfo(s).getBlockStyle() == 3)
      {
        ((class_192)localObject1).jdField_field_92_of_type_ComBulletphysicsLinearmathTransform.basis.set(field_93.basis);
        field_93.basis.setIdentity();
      }
      else
      {
        ((class_192)localObject1).jdField_field_89_of_type_JavaxVecmathMatrix3f.set(((class_192)localObject1).jdField_field_90_of_type_ComBulletphysicsLinearmathTransform.basis);
        field_93.basis.mul(((class_192)localObject1).jdField_field_89_of_type_JavaxVecmathMatrix3f);
      }
      GlUtil.b3(field_93);
      (localObject2 = new Transform()).setIdentity();
      ((Transform)localObject2).basis.set(((class_371)((class_192)localObject1).a24()).a6().getWorldTransform().basis);
      GlUtil.b3((Transform)localObject2);
      if (ElementKeyMap.getInfo(s).getBlockStyle() == 3) {
        field_93.basis.set(((class_192)localObject1).jdField_field_92_of_type_ComBulletphysicsLinearmathTransform.basis);
      }
      (localObject2 = new class_822()).a43((ElementKeyMap.getInfo(s).getIndividualSides() < 4) && (ElementKeyMap.getInfo(s).isOrientatable()));
      ((class_822)localObject2).a42((byte)((class_192)localObject1).jdField_field_89_of_type_Int);
      ((class_822)localObject2).b5((byte)((class_192)localObject1).jdField_field_90_of_type_Int);
      ((class_822)localObject2).a41(s);
      GlUtil.c2();
      class_1363.h1();
      GL11.glEnable(2896);
      GL11.glDisable(2977);
      GL11.glEnable(2929);
    }
    localObject1 = this.field_89.iterator();
    while (((Iterator)localObject1).hasNext()) {
      ((class_984)((Iterator)localObject1).next()).b();
    }
    GlUtil.c2();
  }
  
  public final void c()
  {
    this.jdField_field_89_of_type_Boolean = true;
  }
  
  public final float a3()
  {
    return 32.0F;
  }
  
  public final float b1()
  {
    return 32.0F;
  }
  
  public final void a74(short paramShort)
  {
    this.jdField_field_89_of_type_Short = paramShort;
  }
  
  public final void a72(int paramInt)
  {
    this.jdField_field_89_of_type_Int = paramInt;
  }
  
  public final void b13(int paramInt)
  {
    this.jdField_field_90_of_type_Int = paramInt;
  }
  
  static
  {
    jdField_field_89_of_type_Class_1383 = new class_1383();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_192
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */