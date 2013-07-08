import java.util.Collection;
import java.util.Iterator;
import org.lwjgl.opengl.GL11;
import org.schema.game.common.data.element.ElementCollection;
import org.schema.schine.graphicsengine.core.GlUtil;

public class class_398
  implements class_965
{
  private Collection jdField_field_98_of_type_JavaUtilCollection;
  private class_797 jdField_field_98_of_type_Class_797;
  private int jdField_field_98_of_type_Int;
  private boolean jdField_field_98_of_type_Boolean;
  
  public final void a() {}
  
  public final void a39(Collection paramCollection, class_797 paramclass_797)
  {
    this.jdField_field_98_of_type_JavaUtilCollection = paramCollection;
    this.jdField_field_98_of_type_Class_797 = paramclass_797;
    this.jdField_field_98_of_type_Boolean = false;
  }
  
  public final void b()
  {
    if (this.jdField_field_98_of_type_JavaUtilCollection == null) {
      return;
    }
    if (!this.jdField_field_98_of_type_Boolean)
    {
      class_398 localclass_398 = this;
      if (this.jdField_field_98_of_type_Int != 0) {
        GL11.glDeleteLists(localclass_398.jdField_field_98_of_type_Int, 1);
      }
      localclass_398.jdField_field_98_of_type_Int = GL11.glGenLists(1);
      GL11.glNewList(localclass_398.jdField_field_98_of_type_Int, 4864);
      GL11.glBegin(0);
      class_48 localclass_48 = new class_48();
      Iterator localIterator = localclass_398.jdField_field_98_of_type_JavaUtilCollection.iterator();
      while (localIterator.hasNext())
      {
        ElementCollection.getPosFromIndex(((Long)localIterator.next()).longValue(), localclass_48);
        localclass_48.c(8, 8, 8);
        GL11.glVertex3f(localclass_48.field_475, localclass_48.field_476, localclass_48.field_477);
      }
      GL11.glEnd();
      GL11.glEndList();
      localclass_398.jdField_field_98_of_type_Boolean = true;
    }
    GlUtil.d1();
    GlUtil.b3(this.jdField_field_98_of_type_Class_797.getWorldTransform());
    GL11.glBlendFunc(770, 771);
    GL11.glEnable(3042);
    GL11.glDisable(2929);
    GL11.glDisable(2896);
    GL11.glEnable(2903);
    GL11.glDisable(3553);
    GlUtil.a38(1.0F, 1.0F, 1.0F, 1.0F);
    if ((!field_106) && (!this.jdField_field_98_of_type_Boolean)) {
      throw new AssertionError();
    }
    GL11.glCallList(this.jdField_field_98_of_type_Int);
    GL11.glDisable(2903);
    GlUtil.a38(1.0F, 1.0F, 1.0F, 1.0F);
    GL11.glDisable(3042);
    GL11.glEnable(2929);
    GlUtil.c2();
  }
  
  public final void c() {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_398
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */