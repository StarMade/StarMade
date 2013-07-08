import javax.vecmath.Vector3f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.schema.schine.graphicsengine.camera.Camera;
import org.schema.schine.graphicsengine.core.GlUtil;

final class class_830
  implements class_1369
{
  private class_830(class_834 paramclass_834) {}
  
  public final void d()
  {
    GL13.glActiveTexture(33984);
    GL11.glDisable(3553);
    GL11.glBindTexture(3553, 0);
    GL13.glActiveTexture(33985);
    GL11.glDisable(3553);
    GL11.glBindTexture(3553, 0);
    GL13.glActiveTexture(33986);
    GL11.glDisable(3553);
    GL11.glBindTexture(3553, 0);
    GL13.glActiveTexture(33987);
    GL11.glDisable(3553);
    GL11.glBindTexture(3553, 0);
    GL13.glActiveTexture(33988);
    GL11.glDisable(3553);
    GL11.glBindTexture(3553, 0);
    GL13.glActiveTexture(33984);
  }
  
  public final void a(class_1377 paramclass_1377)
  {
    GlUtil.a33(paramclass_1377, "fCloudRotation", class_834.a50(this.field_107) * 0.005F);
    GlUtil.a41(paramclass_1377, "fvSpecular", 1.0F, 1.0F, 1.0F, 1.0F);
    GlUtil.a41(paramclass_1377, "fvDiffuse", 1.0F, 1.0F, 1.0F, 1.0F);
    GlUtil.a33(paramclass_1377, "fSpecularPower", 20.0F);
    GlUtil.a33(paramclass_1377, "fCloudHeight", class_834.a48(this.field_107).field_1090);
    GlUtil.a33(paramclass_1377, "density", 1.5F / class_969.field_1259.a());
    if (class_834.a49(this.field_107).a3(0, 0, 0)) {
      GlUtil.a33(paramclass_1377, "dist", class_969.a1().a83().length());
    } else {
      GlUtil.a33(paramclass_1377, "dist", -1.0F);
    }
    GL11.glEnable(3553);
    GL13.glActiveTexture(33984);
    GL11.glBindTexture(3553, this.field_107.b6());
    GL13.glActiveTexture(33985);
    GL11.glBindTexture(3553, this.field_107.c3());
    GL13.glActiveTexture(33986);
    GL11.glBindTexture(3553, this.field_107.d1());
    GL13.glActiveTexture(33987);
    GL11.glBindTexture(3553, this.field_107.a44());
    GL13.glActiveTexture(33988);
    GlUtil.a35(paramclass_1377, "baseMap", 0);
    GlUtil.a35(paramclass_1377, "normalMap", 1);
    GlUtil.a35(paramclass_1377, "specMap", 2);
    GlUtil.a35(paramclass_1377, "cloudsMap", 3);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_830
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */