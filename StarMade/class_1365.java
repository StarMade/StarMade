import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.schema.schine.graphicsengine.core.GlUtil;

public final class class_1365
  implements class_965, class_1369
{
  private class_925 jdField_field_98_of_type_Class_925;
  private class_925 field_106;
  private class_1366 jdField_field_98_of_type_Class_1366;
  
  public class_1365(class_925 paramclass_9251, class_925 paramclass_9252, class_1366 paramclass_1366)
  {
    this.jdField_field_98_of_type_Class_925 = paramclass_9251;
    this.field_106 = paramclass_9252;
    this.jdField_field_98_of_type_Class_1366 = paramclass_1366;
  }
  
  public final void a() {}
  
  public final void b()
  {
    class_1377 localclass_1377;
    (localclass_1377 = class_1376.field_1562).field_1578 = this;
    this.field_106.d();
    GL11.glClear(16640);
    this.jdField_field_98_of_type_Class_925.a2(localclass_1377);
    this.field_106.b1();
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
    class_1366.d();
    GlUtil.c5(paramclass_1377, "MVP", class_1370.field_274);
    GlUtil.a33(paramclass_1377, "LumThresh", 1.0F - ((Float)class_949.field_1249.a4()).floatValue());
    GL13.glActiveTexture(33984);
    GL11.glBindTexture(3553, this.jdField_field_98_of_type_Class_925.a3());
    GlUtil.a35(paramclass_1377, "RenderTex", 0);
    GL13.glActiveTexture(33985);
    GL11.glBindTexture(3553, this.jdField_field_98_of_type_Class_1366.field_274);
    GlUtil.a35(paramclass_1377, "SilhouetteTex", 1);
    GL13.glActiveTexture(33984);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1365
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */