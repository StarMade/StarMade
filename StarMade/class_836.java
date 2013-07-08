import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import javax.vecmath.Vector3f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.schema.schine.graphicsengine.core.GlUtil;

public final class class_836
  implements class_1369
{
  public int field_107;
  public float field_107;
  private int field_201 = 0;
  private boolean field_107;
  
  public class_836()
  {
    this.jdField_field_107_of_type_Boolean = false;
  }
  
  public final void d()
  {
    GL13.glActiveTexture(33984);
    GL11.glBindTexture(3553, 0);
    GL13.glActiveTexture(33985);
    GL11.glBindTexture(3553, 0);
    GL13.glActiveTexture(33986);
    GL11.glBindTexture(3553, 0);
    GL13.glActiveTexture(33987);
    GL11.glBindTexture(3553, 0);
    GL13.glActiveTexture(33984);
  }
  
  public final void a(class_1377 paramclass_1377)
  {
    if (!this.jdField_field_107_of_type_Boolean)
    {
      class_40.a("Shader load " + this.field_201);
      class_40.b("Shader load " + this.field_201);
      this.field_201 = ((this.field_201 + 1) % 2);
      this.jdField_field_107_of_type_Boolean = true;
    }
    if (paramclass_1377.field_1578)
    {
      Object localObject = GlUtil.a5(72, 0).asFloatBuffer();
      for (int i = 0; i < 6; i++)
      {
        ((FloatBuffer)localObject).put(org.schema.game.common.data.element.Element.DIRECTIONSf[i].field_615);
        ((FloatBuffer)localObject).put(org.schema.game.common.data.element.Element.DIRECTIONSf[i].field_616);
        ((FloatBuffer)localObject).put(org.schema.game.common.data.element.Element.DIRECTIONSf[i].field_617);
      }
      ((FloatBuffer)localObject).rewind();
      GlUtil.b7(paramclass_1377, "normals", (FloatBuffer)localObject);
      ((FloatBuffer)localObject).rewind();
      ((FloatBuffer)localObject).put(0.0F);
      ((FloatBuffer)localObject).put(2.0F);
      ((FloatBuffer)localObject).put(4.0F);
      ((FloatBuffer)localObject).put(0.0F);
      ((FloatBuffer)localObject).put(2.0F);
      ((FloatBuffer)localObject).put(4.0F);
      ((FloatBuffer)localObject).put(4.0F);
      ((FloatBuffer)localObject).put(0.0F);
      ((FloatBuffer)localObject).put(2.0F);
      ((FloatBuffer)localObject).put(2.0F);
      ((FloatBuffer)localObject).put(0.0F);
      ((FloatBuffer)localObject).put(4.0F);
      ((FloatBuffer)localObject).put(4.0F);
      ((FloatBuffer)localObject).put(2.0F);
      ((FloatBuffer)localObject).put(0.0F);
      ((FloatBuffer)localObject).put(4.0F);
      ((FloatBuffer)localObject).put(2.0F);
      ((FloatBuffer)localObject).put(0.0F);
      ((FloatBuffer)localObject).rewind();
      GlUtil.b7(paramclass_1377, "quadPosMark", (FloatBuffer)localObject);
      localObject = paramclass_1377;
      FloatBuffer localFloatBuffer;
      (localFloatBuffer = GlUtil.a5(144, 0).asFloatBuffer()).rewind();
      localFloatBuffer.put(1.0F);
      localFloatBuffer.put(1.0F);
      localFloatBuffer.put(0.0F);
      localFloatBuffer.put(0.0F);
      localFloatBuffer.put(1.0F);
      localFloatBuffer.put(0.0F);
      localFloatBuffer.put(1.0F);
      localFloatBuffer.put(1.0F);
      localFloatBuffer.put(0.0F);
      localFloatBuffer.put(1.0F);
      localFloatBuffer.put(0.0F);
      localFloatBuffer.put(0.0F);
      localFloatBuffer.put(0.0F);
      localFloatBuffer.put(1.0F);
      localFloatBuffer.put(0.0F);
      localFloatBuffer.put(1.0F);
      localFloatBuffer.put(1.0F);
      localFloatBuffer.put(0.0F);
      localFloatBuffer.put(0.0F);
      localFloatBuffer.put(1.0F);
      localFloatBuffer.put(0.0F);
      localFloatBuffer.put(1.0F);
      localFloatBuffer.put(1.0F);
      localFloatBuffer.put(0.0F);
      localFloatBuffer.put(0.0F);
      localFloatBuffer.put(0.0F);
      localFloatBuffer.put(0.0F);
      localFloatBuffer.put(1.0F);
      localFloatBuffer.put(1.0F);
      localFloatBuffer.put(0.0F);
      localFloatBuffer.put(1.0F);
      localFloatBuffer.put(1.0F);
      localFloatBuffer.put(0.0F);
      localFloatBuffer.put(0.0F);
      localFloatBuffer.put(1.0F);
      localFloatBuffer.put(0.0F);
      localFloatBuffer.rewind();
      GlUtil.b7((class_1377)localObject, "texOrder", localFloatBuffer);
      paramclass_1377.field_1578 = false;
    }
    GlUtil.a35(paramclass_1377, "animationTime", this.jdField_field_107_of_type_Int);
    GlUtil.a33(paramclass_1377, "density", 1.5F / class_969.field_1259.a());
    GL13.glActiveTexture(33984);
    GL11.glBindTexture(3553, class_333.field_134[0].field_1592);
    GlUtil.a35(paramclass_1377, "overlayTex", 0);
    GL13.glActiveTexture(33985);
    GL11.glBindTexture(3553, class_333.field_134[1].field_1592);
    GlUtil.a35(paramclass_1377, "mainTex0", 1);
    GL13.glActiveTexture(33986);
    GL11.glBindTexture(3553, class_333.field_134[2].field_1592);
    GlUtil.a35(paramclass_1377, "mainTex1", 2);
    GL13.glActiveTexture(33987);
    GL11.glBindTexture(3553, class_333.field_134[3].field_1592);
    GlUtil.a35(paramclass_1377, "mainTex2", 3);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_836
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */