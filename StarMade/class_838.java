import java.nio.FloatBuffer;
import javax.vecmath.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.schema.schine.graphicsengine.core.GlUtil;

public final class class_838
  implements class_1369
{
  private float[] jdField_field_107_of_type_ArrayOfFloat = new float[8];
  private Vector3f[] jdField_field_107_of_type_ArrayOfJavaxVecmathVector3f = new Vector3f[8];
  private static FloatBuffer jdField_field_107_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(8);
  private static FloatBuffer jdField_field_201_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(24);
  private int jdField_field_107_of_type_Int;
  private float jdField_field_107_of_type_Float = 4.0F;
  private boolean jdField_field_107_of_type_Boolean = false;
  private static Vector3f[] jdField_field_201_of_type_ArrayOfJavaxVecmathVector3f = new Vector3f[16];
  
  public class_838()
  {
    for (int i = 0; i < 8; i++) {
      this.jdField_field_107_of_type_ArrayOfJavaxVecmathVector3f[i] = new Vector3f();
    }
  }
  
  public final void a3(Vector3f paramVector3f)
  {
    if (this.jdField_field_107_of_type_Int < 8)
    {
      this.jdField_field_107_of_type_ArrayOfFloat[this.jdField_field_107_of_type_Int] = 1.0F;
      this.jdField_field_107_of_type_ArrayOfJavaxVecmathVector3f[this.jdField_field_107_of_type_Int].set(paramVector3f);
      this.jdField_field_107_of_type_Int += 1;
      this.jdField_field_107_of_type_Boolean = true;
    }
  }
  
  public final int a4()
  {
    return this.jdField_field_107_of_type_Int;
  }
  
  public final void d()
  {
    GL13.glActiveTexture(33986);
    GL11.glBindTexture(3553, 0);
    GL13.glActiveTexture(33985);
    GL11.glBindTexture(3553, 0);
    GL13.glActiveTexture(33984);
    GL11.glBindTexture(3553, 0);
  }
  
  public final void a5(class_941 paramclass_941)
  {
    for (int i = 0; i < this.jdField_field_107_of_type_Int; i++) {
      this.jdField_field_107_of_type_ArrayOfFloat[i] -= paramclass_941.a() * 0.7F;
    }
    for (i = 0; i < this.jdField_field_107_of_type_Int; i++) {
      if (this.jdField_field_107_of_type_ArrayOfFloat[i] <= 0.0F)
      {
        this.jdField_field_107_of_type_ArrayOfFloat[i] = 0.0F;
        this.jdField_field_107_of_type_ArrayOfFloat[i] = this.jdField_field_107_of_type_ArrayOfFloat[(this.jdField_field_107_of_type_Int - 1)];
        this.jdField_field_107_of_type_ArrayOfJavaxVecmathVector3f[i].set(this.jdField_field_107_of_type_ArrayOfJavaxVecmathVector3f[(this.jdField_field_107_of_type_Int - 1)]);
        this.jdField_field_107_of_type_Int -= 1;
        this.jdField_field_107_of_type_Boolean = true;
      }
    }
  }
  
  public final void a(class_1377 paramclass_1377)
  {
    if (paramclass_1377 == null) {
      throw new NullPointerException("Shield Shader NULL; Shield Drawing enabled: " + class_949.field_1195.b1());
    }
    GL13.glActiveTexture(33984);
    GL11.glBindTexture(3553, class_969.a2().a5("shield_tex").a153().a1().field_1592);
    GlUtil.a35(paramclass_1377, "m_ShieldTex", 0);
    GL13.glActiveTexture(33985);
    GL11.glBindTexture(3553, class_333.field_135[0].field_1592);
    GlUtil.a35(paramclass_1377, "m_Distortion", 1);
    GL13.glActiveTexture(33986);
    GL11.glBindTexture(3553, class_333.field_135[1].field_1592);
    GlUtil.a35(paramclass_1377, "m_Noise", 2);
    for (int i = 0; i < this.jdField_field_107_of_type_Int; i++)
    {
      jdField_field_107_of_type_JavaNioFloatBuffer.put(i, this.jdField_field_107_of_type_ArrayOfFloat[i]);
      if (this.jdField_field_107_of_type_Boolean)
      {
        jdField_field_201_of_type_JavaNioFloatBuffer.put(i * 3, this.jdField_field_107_of_type_ArrayOfJavaxVecmathVector3f[i].field_615);
        jdField_field_201_of_type_JavaNioFloatBuffer.put(i * 3 + 1, this.jdField_field_107_of_type_ArrayOfJavaxVecmathVector3f[i].field_616);
        jdField_field_201_of_type_JavaNioFloatBuffer.put(i * 3 + 2, this.jdField_field_107_of_type_ArrayOfJavaxVecmathVector3f[i].field_617);
      }
    }
    jdField_field_107_of_type_JavaNioFloatBuffer.rewind();
    GlUtil.a34(paramclass_1377, "m_CollisionAlphas", jdField_field_107_of_type_JavaNioFloatBuffer);
    if (this.jdField_field_107_of_type_Boolean)
    {
      jdField_field_201_of_type_JavaNioFloatBuffer.rewind();
      GlUtil.b7(paramclass_1377, "m_Collisions", jdField_field_201_of_type_JavaNioFloatBuffer);
      this.jdField_field_107_of_type_Boolean = false;
      GlUtil.a35(paramclass_1377, "m_CollisionNum", this.jdField_field_107_of_type_Int);
    }
    GlUtil.a33(paramclass_1377, "m_MinAlpha", 0.0F);
    GlUtil.a33(paramclass_1377, "m_MaxDistance", this.jdField_field_107_of_type_Float);
    GlUtil.a33(paramclass_1377, "m_Time", class_220.field_98);
  }
  
  public final void a2()
  {
    this.jdField_field_107_of_type_Int = 0;
  }
  
  static
  {
    for (int i = 0; i < jdField_field_201_of_type_ArrayOfJavaxVecmathVector3f.length; i++) {
      jdField_field_201_of_type_ArrayOfJavaxVecmathVector3f[i] = new Vector3f();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_838
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */