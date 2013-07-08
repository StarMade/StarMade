import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector4f;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.network.client.ClientState;

public class class_942
  extends class_1363
{
  private final Vector4f jdField_field_90_of_type_OrgLwjglUtilVectorVector4f = new Vector4f(1.0F, 1.0F, 1.0F, 0.5F);
  private int jdField_field_89_of_type_Int;
  private boolean jdField_field_89_of_type_Boolean;
  
  public class_942(ClientState paramClientState)
  {
    super(paramClientState);
  }
  
  public final void a2() {}
  
  protected final void d() {}
  
  public final void b()
  {
    GlUtil.a38(this.jdField_field_90_of_type_OrgLwjglUtilVectorVector4f.field_140, this.jdField_field_90_of_type_OrgLwjglUtilVectorVector4f.field_141, this.jdField_field_90_of_type_OrgLwjglUtilVectorVector4f.field_142, this.jdField_field_90_of_type_OrgLwjglUtilVectorVector4f.field_143);
    if ((!jdField_field_90_of_type_Boolean) && (!this.jdField_field_89_of_type_Boolean)) {
      throw new AssertionError();
    }
    GL11.glCallList(this.jdField_field_89_of_type_Int);
    GlUtil.a38(1.0F, 1.0F, 1.0F, 1.0F);
  }
  
  public final Vector4f a138()
  {
    return this.jdField_field_90_of_type_OrgLwjglUtilVectorVector4f;
  }
  
  public final float a3()
  {
    return class_933.a();
  }
  
  public final float b1()
  {
    return class_933.b();
  }
  
  public final void c()
  {
    class_942 localclass_942 = this;
    this.jdField_field_89_of_type_Int = GL11.glGenLists(1);
    GL11.glNewList(localclass_942.jdField_field_89_of_type_Int, 4864);
    GL11.glEnable(3042);
    GL11.glBlendFunc(770, 771);
    GL11.glBegin(7);
    GL11.glVertex2f(0.0F, 0.0F);
    GL11.glVertex2f(0.0F, class_933.a());
    GL11.glVertex2f(class_933.b(), class_933.a());
    GL11.glVertex2f(class_933.b(), 0.0F);
    GL11.glEnd();
    GL11.glDisable(3042);
    GL11.glEndList();
    localclass_942.jdField_field_89_of_type_Boolean = true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_942
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */