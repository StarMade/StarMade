import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Iterator;
import java.util.List;
import javax.vecmath.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector4f;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.network.client.ClientState;
import org.schema.schine.network.client.GUICallbackController;

public abstract class class_1363
  extends class_984
  implements class_957, class_965
{
  public final Vector3f field_90;
  public boolean field_95;
  public class_1412 field_89;
  private int[] jdField_field_89_of_type_ArrayOfInt = new int[4];
  public boolean field_96;
  private ClientState jdField_field_89_of_type_OrgSchemaSchineNetworkClientClientState;
  public Object field_89;
  private boolean jdField_field_89_of_type_Boolean;
  private static FloatBuffer jdField_field_90_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(3);
  public Vector4f field_89;
  private static DoubleBuffer jdField_field_89_of_type_JavaNioDoubleBuffer = BufferUtils.createDoubleBuffer(4);
  private static DoubleBuffer jdField_field_90_of_type_JavaNioDoubleBuffer = BufferUtils.createDoubleBuffer(4);
  private static DoubleBuffer field_92 = BufferUtils.createDoubleBuffer(4);
  private static DoubleBuffer field_93 = BufferUtils.createDoubleBuffer(4);
  public static boolean field_97;
  
  public static void h1()
  {
    GlUtil.a12(5889);
    GlUtil.c2();
    GlUtil.a12(5888);
    GlUtil.c2();
    GL11.glEnable(2929);
    GL11.glEnable(2896);
  }
  
  public static void i()
  {
    GL11.glDisable(2896);
    GL11.glDisable(2929);
    GlUtil.d1();
    GlUtil.a12(5889);
    GlUtil.d1();
    GlUtil.b2();
    GlUtil.a14(class_933.b(), class_933.a(), 0.0F);
    GlUtil.a12(5888);
    GlUtil.b2();
  }
  
  public static void j()
  {
    a167(class_933.b(), class_933.a());
  }
  
  public static void a167(int paramInt1, int paramInt2)
  {
    GL11.glDisable(2896);
    GL11.glDisable(2929);
    GlUtil.d1();
    GlUtil.a12(5889);
    GlUtil.d1();
    GlUtil.b2();
    GlUtil.a13(paramInt1, paramInt2, 0.0F, -500.0F, 500.0F);
    GlUtil.a12(5888);
    GlUtil.b2();
  }
  
  public class_1363(ClientState paramClientState)
  {
    this.jdField_field_90_of_type_JavaxVecmathVector3f = new Vector3f();
    this.jdField_field_89_of_type_OrgLwjglUtilVectorVector4f = new Vector4f();
    this.jdField_field_89_of_type_OrgSchemaSchineNetworkClientClientState = paramClientState;
  }
  
  public void a9(class_1363 paramclass_1363)
  {
    paramclass_1363.b25(this);
    this.jdField_field_89_of_type_JavaUtilList.add(paramclass_1363);
  }
  
  public final void k()
  {
    if (h2()) {
      return;
    }
    GlUtil.d1();
    this.field_95 = false;
    r();
    if (this.field_96) {
      l();
    }
    Iterator localIterator = this.jdField_field_89_of_type_JavaUtilList.iterator();
    while (localIterator.hasNext()) {
      ((class_984)localIterator.next()).b();
    }
    GlUtil.c2();
  }
  
  public void l()
  {
    if (field_97)
    {
      this.field_95 = false;
      return;
    }
    Matrix4f localMatrix4f = class_969.jdField_field_1259_of_type_OrgLwjglUtilVectorMatrix4f;
    float f2 = new Vector3f(localMatrix4f.m00, localMatrix4f.m01, localMatrix4f.m02).length();
    float f3 = new Vector3f(localMatrix4f.m10, localMatrix4f.m11, localMatrix4f.m12).length();
    Mouse.getX();
    Mouse.getY();
    jdField_field_90_of_type_JavaNioFloatBuffer.rewind();
    float f4 = (Mouse.getX() - localMatrix4f.m30) * f2;
    float f1 = (class_933.a() - Mouse.getY() - localMatrix4f.m31) * f3;
    this.jdField_field_90_of_type_JavaxVecmathVector3f.set(f4, f1, 0.0F);
    if (System.currentTimeMillis() - a24().getLastDeactivatedMenu() > 200L)
    {
      int j = (this.jdField_field_90_of_type_JavaxVecmathVector3f.field_615 < b1() * f2 * f2) && (this.jdField_field_90_of_type_JavaxVecmathVector3f.field_615 > 0.0F) ? 1 : 0;
      int i = (this.jdField_field_90_of_type_JavaxVecmathVector3f.field_616 < a3() * f3 * f3) && (this.jdField_field_90_of_type_JavaxVecmathVector3f.field_616 > 0.0F) ? 1 : 0;
      this.field_95 = ((j != 0) && (i != 0));
    }
    else
    {
      this.field_95 = false;
    }
    if ((a_()) && (this.jdField_field_89_of_type_Class_1412 != null) && (!this.jdField_field_89_of_type_Class_1412.a1())) {
      this.jdField_field_89_of_type_OrgSchemaSchineNetworkClientClientState.getGuiCallbackController().addCallback(this.jdField_field_89_of_type_Class_1412, this);
    }
    this.jdField_field_89_of_type_Boolean = a_();
  }
  
  public final void m1()
  {
    GlUtil.d1();
    r();
    l();
    GlUtil.c2();
  }
  
  public void b2(class_1363 paramclass_1363)
  {
    paramclass_1363.b25(null);
    this.jdField_field_89_of_type_JavaUtilList.remove(paramclass_1363);
  }
  
  protected void d() {}
  
  public final void a168(Vector4f paramVector4f)
  {
    jdField_field_89_of_type_JavaNioDoubleBuffer.put(new double[] { 1.0D, 0.0D, 0.0D, -paramVector4f.field_140 }).rewind();
    GL11.glClipPlane(12288, jdField_field_89_of_type_JavaNioDoubleBuffer);
    GL11.glEnable(12288);
    jdField_field_90_of_type_JavaNioDoubleBuffer.put(new double[] { -1.0D, 0.0D, 0.0D, paramVector4f.field_141 }).rewind();
    GL11.glClipPlane(12289, jdField_field_90_of_type_JavaNioDoubleBuffer);
    GL11.glEnable(12289);
    field_92.put(new double[] { 0.0D, 1.0D, 0.0D, -paramVector4f.field_142 }).rewind();
    GL11.glClipPlane(12290, field_92);
    GL11.glEnable(12290);
    field_93.put(new double[] { 0.0D, -1.0D, 0.0D, paramVector4f.field_143 }).rewind();
    GL11.glClipPlane(12291, field_93);
    GL11.glEnable(12291);
    b();
    GL11.glDisable(12288);
    GL11.glDisable(12289);
    GL11.glDisable(12290);
    GL11.glDisable(12291);
  }
  
  public abstract float a3();
  
  public final Vector3f f5()
  {
    return this.jdField_field_90_of_type_JavaxVecmathVector3f;
  }
  
  public ClientState a24()
  {
    return this.jdField_field_89_of_type_OrgSchemaSchineNetworkClientClientState;
  }
  
  public final Object b29()
  {
    return this.jdField_field_89_of_type_JavaLangObject;
  }
  
  public abstract float b1();
  
  public boolean a_()
  {
    return this.field_95;
  }
  
  public final boolean j1()
  {
    if (a83().field_615 > class_933.b()) {
      return false;
    }
    if (a83().field_616 > class_933.a()) {
      return false;
    }
    if (a83().field_615 + b1() * this.jdField_field_89_of_type_JavaxVecmathVector3f.field_615 < 0.0F) {
      return false;
    }
    return a83().field_616 + a3() * this.jdField_field_89_of_type_JavaxVecmathVector3f.field_616 >= 0.0F;
  }
  
  public final boolean k1()
  {
    for (int i = 0; i < 4; i++) {
      if (this.jdField_field_89_of_type_ArrayOfInt[i] != class_969.jdField_field_1259_of_type_JavaNioIntBuffer.get(i))
      {
        for (i = 0; i < 4; i++) {
          this.jdField_field_89_of_type_ArrayOfInt[i] = class_969.jdField_field_1259_of_type_JavaNioIntBuffer.get(i);
        }
        return true;
      }
    }
    return false;
  }
  
  public final void h3(int paramInt)
  {
    a169(paramInt, class_969.jdField_field_1259_of_type_JavaNioIntBuffer.get(0), class_969.jdField_field_1259_of_type_JavaNioIntBuffer.get(1), class_969.jdField_field_1259_of_type_JavaNioIntBuffer.get(2), class_969.jdField_field_1259_of_type_JavaNioIntBuffer.get(3));
  }
  
  public final void a169(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    if ((paramInt1 & 0x10) == 16) {
      a83().field_616 = ((paramInt5 - a3() * this.jdField_field_89_of_type_JavaxVecmathVector3f.field_616 - paramInt3) / 2.0F);
    }
    if ((paramInt1 & 0x20) == 32) {
      a83().field_615 = ((paramInt4 - b1() * this.jdField_field_89_of_type_JavaxVecmathVector3f.field_615 - paramInt2) / 2.0F);
    }
    if ((paramInt1 & 0x1) == 1) {
      a83().field_615 = paramInt2;
    }
    if ((paramInt1 & 0x2) == 2) {
      a83().field_615 = (paramInt4 - b1() * this.jdField_field_89_of_type_JavaxVecmathVector3f.field_615);
    }
    if ((paramInt1 & 0x4) == 4) {
      a83().field_616 = paramInt3;
    }
    if ((paramInt1 & 0x8) == 8) {
      a83().field_616 = (paramInt5 - a3() * this.jdField_field_89_of_type_JavaxVecmathVector3f.field_616);
    }
  }
  
  public void a141(class_1412 paramclass_1412)
  {
    this.jdField_field_89_of_type_Class_1412 = paramclass_1412;
  }
  
  public final void r()
  {
    GlUtil.b3(this.jdField_field_89_of_type_ComBulletphysicsLinearmathTransform);
    GlUtil.b5(this.jdField_field_89_of_type_JavaxVecmathVector3f.field_615, this.jdField_field_89_of_type_JavaxVecmathVector3f.field_616, this.jdField_field_89_of_type_JavaxVecmathVector3f.field_617);
  }
  
  public final boolean l1()
  {
    return this.jdField_field_89_of_type_Boolean;
  }
  
  static
  {
    BufferUtils.createIntBuffer(16);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1363
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */