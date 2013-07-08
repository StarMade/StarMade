import java.nio.FloatBuffer;
import javax.vecmath.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.schema.schine.graphicsengine.core.GlUtil;

public final class class_1393
  implements class_1369
{
  private float[] jdField_field_107_of_type_ArrayOfFloat = { 0.3F, 0.3F, 0.3F, 1.0F };
  private float[] jdField_field_201_of_type_ArrayOfFloat = { 0.6F, 0.6F, 0.6F, 1.0F };
  private float[] jdField_field_202_of_type_ArrayOfFloat = { 0.8F, 0.8F, 0.8F, 1.0F };
  private float[] jdField_field_203_of_type_ArrayOfFloat = { 10.0F };
  private static FloatBuffer jdField_field_107_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(4);
  private static FloatBuffer jdField_field_201_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(4);
  private static FloatBuffer jdField_field_202_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(4);
  private static FloatBuffer jdField_field_203_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(1);
  private boolean jdField_field_107_of_type_Boolean = false;
  private boolean jdField_field_201_of_type_Boolean = false;
  private class_1395 jdField_field_107_of_type_Class_1395;
  private String jdField_field_107_of_type_JavaLangString;
  private String jdField_field_201_of_type_JavaLangString;
  private class_1395 jdField_field_201_of_type_Class_1395;
  private class_1395 jdField_field_202_of_type_Class_1395;
  private boolean jdField_field_202_of_type_Boolean;
  private int jdField_field_107_of_type_Int;
  
  public final void a2()
  {
    jdField_field_107_of_type_JavaNioFloatBuffer.rewind();
    jdField_field_201_of_type_JavaNioFloatBuffer.rewind();
    jdField_field_202_of_type_JavaNioFloatBuffer.rewind();
    jdField_field_203_of_type_JavaNioFloatBuffer.rewind();
    jdField_field_107_of_type_JavaNioFloatBuffer.put(this.jdField_field_107_of_type_ArrayOfFloat);
    jdField_field_201_of_type_JavaNioFloatBuffer.put(this.jdField_field_201_of_type_ArrayOfFloat);
    jdField_field_202_of_type_JavaNioFloatBuffer.put(this.jdField_field_202_of_type_ArrayOfFloat);
    jdField_field_203_of_type_JavaNioFloatBuffer.put(this.jdField_field_203_of_type_ArrayOfFloat);
    jdField_field_107_of_type_JavaNioFloatBuffer.rewind();
    jdField_field_201_of_type_JavaNioFloatBuffer.rewind();
    jdField_field_202_of_type_JavaNioFloatBuffer.rewind();
    jdField_field_203_of_type_JavaNioFloatBuffer.rewind();
    GL11.glMaterial(1032, 4608, jdField_field_107_of_type_JavaNioFloatBuffer);
    GL11.glMaterial(1032, 4609, jdField_field_201_of_type_JavaNioFloatBuffer);
    GL11.glMaterial(1032, 4610, jdField_field_202_of_type_JavaNioFloatBuffer);
    GL11.glMaterialf(1032, 5633, jdField_field_203_of_type_JavaNioFloatBuffer.get(0));
    if (class_949.field_1210.b1())
    {
      class_984.g3();
      if ((this.jdField_field_107_of_type_Boolean) && (this.jdField_field_201_of_type_Boolean) && (this.jdField_field_202_of_type_Boolean))
      {
        class_1376.field_1553.field_1578 = this;
        class_1376.field_1553.b();
        return;
      }
    }
    if (this.jdField_field_107_of_type_Boolean)
    {
      if (this.jdField_field_107_of_type_Class_1395 == null) {
        throw new IllegalArgumentException("no texture loaded for " + this.jdField_field_107_of_type_JavaLangString + " but sould be " + this.jdField_field_201_of_type_JavaLangString);
      }
      this.jdField_field_107_of_type_Class_1395.a();
    }
  }
  
  public final void b()
  {
    if (this.jdField_field_107_of_type_Class_1395 != null) {
      GL11.glDeleteTextures(3);
    }
    if (this.jdField_field_201_of_type_Boolean) {
      GL11.glDeleteTextures(3);
    }
    if (this.jdField_field_202_of_type_Boolean) {
      GL11.glDeleteTextures(3);
    }
  }
  
  public final void c()
  {
    if (class_949.field_1210.b1())
    {
      class_984.g3();
      if ((this.jdField_field_107_of_type_Boolean) && (this.jdField_field_201_of_type_Boolean) && (this.jdField_field_202_of_type_Boolean)) {
        class_1376.field_1553.d();
      }
    }
  }
  
  public final String a10()
  {
    return this.jdField_field_107_of_type_JavaLangString;
  }
  
  public final class_1395 a1()
  {
    return this.jdField_field_107_of_type_Class_1395;
  }
  
  public final String b1()
  {
    return this.jdField_field_201_of_type_JavaLangString;
  }
  
  public final boolean a7()
  {
    return this.jdField_field_107_of_type_Boolean;
  }
  
  public final void d()
  {
    GL13.glActiveTexture(33984);
    GL11.glDisable(3553);
    GL11.glBindTexture(3553, 0);
    if (this.jdField_field_201_of_type_Boolean)
    {
      GL13.glActiveTexture(33985);
      GL11.glDisable(3553);
      GL11.glBindTexture(3553, 0);
    }
    if (this.jdField_field_202_of_type_Boolean)
    {
      GL13.glActiveTexture(33986);
      GL11.glDisable(3553);
      GL11.glBindTexture(3553, 0);
    }
    GL13.glActiveTexture(33984);
  }
  
  public final void a11(float[] paramArrayOfFloat)
  {
    this.jdField_field_107_of_type_ArrayOfFloat = paramArrayOfFloat;
  }
  
  public final void b2(float[] paramArrayOfFloat)
  {
    this.jdField_field_201_of_type_ArrayOfFloat = paramArrayOfFloat;
  }
  
  public final void e()
  {
    this.jdField_field_201_of_type_Boolean = true;
  }
  
  public final void a12(boolean paramBoolean)
  {
    if (paramBoolean) {
      this.jdField_field_107_of_type_Int += 1;
    }
    this.jdField_field_107_of_type_Boolean = paramBoolean;
  }
  
  public final void a13(String paramString)
  {
    this.jdField_field_107_of_type_JavaLangString = paramString;
  }
  
  public final void a14(class_1395 paramclass_1395)
  {
    this.jdField_field_201_of_type_Class_1395 = paramclass_1395;
    if (paramclass_1395 != null)
    {
      this.jdField_field_107_of_type_Int += 1;
      this.jdField_field_201_of_type_Boolean = true;
    }
  }
  
  public final void f()
  {
    this.jdField_field_203_of_type_ArrayOfFloat[0] = 64.0F;
  }
  
  public final void c1(float[] paramArrayOfFloat)
  {
    this.jdField_field_202_of_type_ArrayOfFloat = paramArrayOfFloat;
  }
  
  public final void b3(class_1395 paramclass_1395)
  {
    this.jdField_field_202_of_type_Class_1395 = paramclass_1395;
    if (paramclass_1395 != null)
    {
      this.jdField_field_107_of_type_Int += 1;
      this.jdField_field_202_of_type_Boolean = true;
    }
  }
  
  public final void g()
  {
    this.jdField_field_202_of_type_Boolean = true;
  }
  
  public final void c2(class_1395 paramclass_1395)
  {
    if (paramclass_1395 == null) {
      a12(false);
    } else {
      a12(true);
    }
    this.jdField_field_107_of_type_Class_1395 = paramclass_1395;
  }
  
  public final void b4(String paramString)
  {
    this.jdField_field_201_of_type_JavaLangString = paramString;
  }
  
  public final void a(class_1377 paramclass_1377)
  {
    GlUtil.a42(paramclass_1377, "light.ambient", class_971.field_98.a63());
    GlUtil.a42(paramclass_1377, "light.diffuse", class_971.field_98.c13());
    GlUtil.a42(paramclass_1377, "light.specular", class_971.field_98.d8());
    GlUtil.a41(paramclass_1377, "light.position", class_971.field_98.a83().field_615, class_971.field_98.a83().field_616, class_971.field_98.a83().field_617, 1.0F);
    GlUtil.a33(paramclass_1377, "shininess", 2.0F);
    GL13.glActiveTexture(33984);
    GL11.glEnable(3553);
    GL11.glBindTexture(3553, this.jdField_field_107_of_type_Class_1395.field_1592);
    GlUtil.a35(paramclass_1377, "diffuseTexture", 0);
    GL13.glActiveTexture(33985);
    GL11.glEnable(3553);
    GL11.glBindTexture(3553, this.jdField_field_201_of_type_Class_1395.field_1592);
    GlUtil.a35(paramclass_1377, "specularMap", 1);
    GL13.glActiveTexture(33986);
    GL11.glEnable(3553);
    GL11.glBindTexture(3553, this.jdField_field_202_of_type_Class_1395.field_1592);
    GlUtil.a35(paramclass_1377, "normalMap", 2);
    GL13.glActiveTexture(33987);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1393
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */