import javax.vecmath.Vector4f;
import org.lwjgl.opengl.GL11;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.network.client.ClientState;

public class class_1402
  extends class_1414
{
  protected int field_89;
  protected boolean field_89;
  public Vector4f field_89;
  public float field_92;
  
  public class_1402(ClientState paramClientState, float paramFloat1, float paramFloat2, Vector4f paramVector4f)
  {
    super(paramClientState, paramFloat1, paramFloat2);
    this.jdField_field_89_of_type_JavaxVecmathVector4f = paramVector4f;
  }
  
  public void b()
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
    GL11.glEnable(2903);
    GL11.glDisable(3553);
    GlUtil.a38(this.jdField_field_89_of_type_JavaxVecmathVector4f.field_596, this.jdField_field_89_of_type_JavaxVecmathVector4f.field_597, this.jdField_field_89_of_type_JavaxVecmathVector4f.field_598, this.jdField_field_89_of_type_JavaxVecmathVector4f.field_599);
    if ((!jdField_field_90_of_type_Boolean) && (!this.jdField_field_89_of_type_Boolean)) {
      throw new AssertionError();
    }
    GL11.glCallList(this.jdField_field_89_of_type_Int);
    GL11.glDisable(2903);
    GlUtil.a38(1.0F, 1.0F, 1.0F, 1.0F);
    GL11.glDisable(3042);
    GL11.glEnable(2929);
    GlUtil.c2();
    super.b();
  }
  
  public final void e()
  {
    super.b();
  }
  
  protected void f()
  {
    if (this.jdField_field_89_of_type_Int != 0) {
      GL11.glDeleteLists(this.jdField_field_89_of_type_Int, 1);
    }
    this.jdField_field_89_of_type_Int = GL11.glGenLists(1);
    GL11.glNewList(this.jdField_field_89_of_type_Int, 4864);
    if (this.field_92 == 0.0F)
    {
      GL11.glBegin(7);
      GL11.glVertex2f(0.0F, 0.0F);
      GL11.glVertex2f(0.0F, a3());
      GL11.glVertex2f(b1(), a3());
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
  
  public void c()
  {
    super.c();
    f();
  }
  
  public final void b13(int paramInt)
  {
    this.jdField_field_89_of_type_Float = paramInt;
    this.jdField_field_89_of_type_Boolean = false;
  }
  
  public final void c4(int paramInt)
  {
    this.jdField_field_90_of_type_Float = paramInt;
    this.jdField_field_89_of_type_Boolean = false;
  }
  
  public final Vector4f a63()
  {
    return this.jdField_field_89_of_type_JavaxVecmathVector4f;
  }
  
  static
  {
    jdField_field_90_of_type_Boolean = !yx.class.desiredAssertionStatus();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1402
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */