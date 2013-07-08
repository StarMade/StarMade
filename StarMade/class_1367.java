import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.schema.schine.graphicsengine.core.GlUtil;

public final class class_1367
  implements class_965, class_1369
{
  private class_925 jdField_field_98_of_type_Class_925;
  private class_925 field_106;
  private class_925 field_108;
  private float[] jdField_field_98_of_type_ArrayOfFloat;
  private FloatBuffer jdField_field_98_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(10);
  
  public class_1367(class_925 paramclass_9251, class_925 paramclass_9252, class_925 paramclass_9253)
  {
    this.jdField_field_98_of_type_Class_925 = paramclass_9251;
    this.field_106 = paramclass_9252;
    this.field_108 = paramclass_9253;
    this.jdField_field_98_of_type_ArrayOfFloat = class_1370.a1();
    this.jdField_field_98_of_type_JavaNioFloatBuffer.rewind();
    for (paramclass_9251 = 9; paramclass_9251 >= 0; paramclass_9251--) {
      this.jdField_field_98_of_type_JavaNioFloatBuffer.put(this.jdField_field_98_of_type_ArrayOfFloat[paramclass_9251]);
    }
    this.jdField_field_98_of_type_JavaNioFloatBuffer.rewind();
  }
  
  public final void a() {}
  
  public final void b()
  {
    class_1377 localclass_1377;
    (localclass_1377 = class_1376.field_1564).field_1578 = this;
    this.jdField_field_98_of_type_Class_925.a2(localclass_1377);
  }
  
  public final void d()
  {
    GL13.glActiveTexture(33984);
    GL11.glBindTexture(3553, 0);
    GL13.glActiveTexture(33985);
    GL11.glBindTexture(3553, 0);
    GL13.glActiveTexture(33984);
  }
  
  public final void c() {}
  
  public final void a13(class_1377 paramclass_1377)
  {
    GlUtil.c5(paramclass_1377, "MVP", class_1370.field_274);
    GlUtil.a33(paramclass_1377, "Width", this.field_106.field_1150);
    this.jdField_field_98_of_type_JavaNioFloatBuffer.rewind();
    GlUtil.a34(paramclass_1377, "Weight", this.jdField_field_98_of_type_JavaNioFloatBuffer);
    GL13.glActiveTexture(33984);
    GL11.glBindTexture(3553, this.jdField_field_98_of_type_Class_925.a3());
    GlUtil.a35(paramclass_1377, "RenderTex", 0);
    GL13.glActiveTexture(33985);
    GL11.glBindTexture(3553, this.field_108.a3());
    GlUtil.a35(paramclass_1377, "BlurTex", 1);
    GL13.glActiveTexture(33984);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1367
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */