import javax.vecmath.Vector4f;
import org.lwjgl.opengl.GL11;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.network.client.ClientState;

public class class_940
  extends class_1402
{
  private class_1395 field_89;
  private int jdField_field_90_of_type_Int;
  
  public class_940(ClientState paramClientState, float paramFloat1, float paramFloat2, class_1395 paramclass_1395)
  {
    super(paramClientState, paramFloat1, paramFloat2, new Vector4f(1.0F, 1.0F, 1.0F, 1.0F));
    this.jdField_field_89_of_type_Class_1395 = paramclass_1395;
    this.jdField_field_90_of_type_Int = 32;
  }
  
  public final void b()
  {
    if (!this.jdField_field_89_of_type_Boolean) {
      f();
    }
    GlUtil.d1();
    r();
    GL11.glBlendFunc(770, 771);
    GL11.glEnable(3042);
    GL11.glDisable(2929);
    GL11.glDisable(2896);
    GL11.glEnable(3553);
    GL11.glDisable(2903);
    GL11.glBindTexture(3553, this.jdField_field_89_of_type_Class_1395.field_1592);
    if ((!jdField_field_90_of_type_Boolean) && (!this.jdField_field_89_of_type_Boolean)) {
      throw new AssertionError();
    }
    GL11.glCallList(this.jdField_field_89_of_type_Int);
    GL11.glBindTexture(3553, 0);
    GlUtil.a38(1.0F, 1.0F, 1.0F, 1.0F);
    GL11.glDisable(3042);
    GL11.glEnable(2929);
    GlUtil.c2();
    e();
  }
  
  protected final void f()
  {
    if (this.jdField_field_89_of_type_Int != 0) {
      GL11.glDeleteLists(this.jdField_field_89_of_type_Int, 1);
    }
    this.jdField_field_89_of_type_Int = GL11.glGenLists(1);
    GL11.glNewList(this.jdField_field_89_of_type_Int, 4864);
    float f1 = 0.5F - 1.0F / this.jdField_field_90_of_type_Int / 2.0F;
    float f2 = b1() / this.jdField_field_90_of_type_Int + f1;
    float f3 = a3() / this.jdField_field_90_of_type_Int + f1;
    if (this.field_92 == 0.0F)
    {
      GL11.glBegin(7);
      GL11.glTexCoord2f(f1, f1);
      GL11.glVertex2f(0.0F, 0.0F);
      GL11.glTexCoord2f(f1, f3);
      GL11.glVertex2f(0.0F, a3());
      GL11.glTexCoord2f(f2, f3);
      GL11.glVertex2f(b1(), a3());
      GL11.glTexCoord2f(f2, f1);
      GL11.glVertex2f(b1(), 0.0F);
    }
    else
    {
      GL11.glBegin(9);
      GL11.glVertex2f(0.0F, this.field_92);
      GL11.glVertex2f(0.0F, a3() - this.field_92);
      GL11.glVertex2f(this.field_92, a3());
      GL11.glVertex2f(b1() - this.field_92, a3());
      GL11.glVertex2f(b1(), a3() - this.field_92);
      GL11.glVertex2f(b1(), this.field_92);
      GL11.glVertex2f(b1() - this.field_92, 0.0F);
      GL11.glVertex2f(this.field_92, 0.0F);
      GL11.glVertex2f(this.field_92, this.field_92);
    }
    GL11.glEnd();
    GL11.glEndList();
    this.jdField_field_89_of_type_Boolean = true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_940
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */