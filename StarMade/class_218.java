import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.lwjgl.opengl.GL11;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.graphicsengine.forms.Mesh;
import org.schema.schine.graphicsengine.shader.ErrorDialogException;

public final class class_218
  implements class_965
{
  static Mesh jdField_field_98_of_type_OrgSchemaSchineGraphicsengineFormsMesh;
  private static class_1391 jdField_field_98_of_type_Class_1391;
  private boolean jdField_field_98_of_type_Boolean = true;
  public final ArrayList field_98;
  public final ArrayList field_106;
  public final Map field_98;
  private final Map field_106;
  
  public class_218()
  {
    this.jdField_field_98_of_type_JavaUtilArrayList = new ArrayList();
    this.jdField_field_106_of_type_JavaUtilArrayList = new ArrayList();
    this.jdField_field_98_of_type_JavaUtilMap = new HashMap();
    this.jdField_field_106_of_type_JavaUtilMap = new HashMap();
  }
  
  public final void a() {}
  
  public final void d()
  {
    Iterator localIterator = this.jdField_field_106_of_type_JavaUtilMap.values().iterator();
    Object localObject;
    while (localIterator.hasNext())
    {
      localObject = (class_356)localIterator.next();
      try
      {
        ((class_356)localObject).a3();
      }
      catch (ErrorDialogException localErrorDialogException1)
      {
        localErrorDialogException1;
      }
    }
    this.jdField_field_106_of_type_JavaUtilMap.clear();
    localIterator = this.jdField_field_98_of_type_JavaUtilMap.values().iterator();
    while (localIterator.hasNext())
    {
      localObject = (class_295)localIterator.next();
      try
      {
        ((class_295)localObject).a();
      }
      catch (ErrorDialogException localErrorDialogException2)
      {
        localErrorDialogException2;
      }
    }
    this.jdField_field_98_of_type_JavaUtilMap.clear();
  }
  
  public final void b()
  {
    if (this.jdField_field_98_of_type_Boolean) {
      c();
    }
    GL11.glEnable(3042);
    GL11.glBlendFunc(770, 771);
    GL11.glEnable(32879);
    class_1377 localclass_1377;
    (localclass_1377 = class_1376.field_1567).c();
    GL11.glBindTexture(32879, jdField_field_98_of_type_Class_1391.a1());
    GlUtil.a35(localclass_1377, "noiseTex", 0);
    ((Mesh)jdField_field_98_of_type_OrgSchemaSchineGraphicsengineFormsMesh.a152().get(0)).g();
    Iterator localIterator = this.jdField_field_98_of_type_JavaUtilMap.values().iterator();
    while (localIterator.hasNext()) {
      ((class_295)localIterator.next()).b();
    }
    GlUtil.a35(localclass_1377, "noiseTex", 0);
    localIterator = this.jdField_field_106_of_type_JavaUtilMap.values().iterator();
    while (localIterator.hasNext()) {
      ((class_356)localIterator.next()).b();
    }
    ((Mesh)jdField_field_98_of_type_OrgSchemaSchineGraphicsengineFormsMesh.a152().get(0)).l();
    GL11.glDisable(32879);
    GL11.glDisable(3042);
    class_1377.e();
  }
  
  public final void c()
  {
    if (jdField_field_98_of_type_OrgSchemaSchineGraphicsengineFormsMesh == null) {
      jdField_field_98_of_type_OrgSchemaSchineGraphicsengineFormsMesh = class_969.a2().a4("ExaustPlum");
    }
    if (jdField_field_98_of_type_Class_1391 == null) {
      jdField_field_98_of_type_Class_1391 = class_333.field_134;
    }
    this.jdField_field_98_of_type_Boolean = false;
  }
  
  public final void a1(class_941 paramclass_941)
  {
    while (!this.jdField_field_98_of_type_JavaUtilArrayList.isEmpty())
    {
      localObject = (class_295)this.jdField_field_98_of_type_JavaUtilArrayList.remove(0);
      this.jdField_field_98_of_type_JavaUtilMap.put(((class_295)localObject).a24(), localObject);
    }
    while (!this.jdField_field_106_of_type_JavaUtilArrayList.isEmpty())
    {
      localObject = (class_356)this.jdField_field_106_of_type_JavaUtilArrayList.remove(0);
      this.jdField_field_106_of_type_JavaUtilMap.put(((class_356)localObject).a10(), localObject);
    }
    Object localObject = this.jdField_field_98_of_type_JavaUtilMap.values().iterator();
    while (((Iterator)localObject).hasNext()) {
      ((class_295)((Iterator)localObject).next()).a1(paramclass_941);
    }
    localObject = this.jdField_field_106_of_type_JavaUtilMap.values().iterator();
    while (((Iterator)localObject).hasNext()) {
      ((class_356)((Iterator)localObject).next()).a5(paramclass_941);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_218
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */