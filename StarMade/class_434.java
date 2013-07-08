import com.bulletphysics.linearmath.Transform;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import java.io.PrintStream;
import java.util.Iterator;
import javax.vecmath.Vector3f;
import org.lwjgl.input.Keyboard;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.world.Segment;
import org.schema.schine.graphicsengine.camera.Camera;

public final class class_434
  extends class_16
{
  private Camera jdField_field_4_of_type_OrgSchemaSchineGraphicsengineCameraCamera;
  private float jdField_field_4_of_type_Float = 0.0F;
  private float field_5 = 0.0F;
  private class_797 jdField_field_4_of_type_Class_797;
  private boolean field_7 = false;
  private boolean field_132;
  
  public class_434(class_371 paramclass_371)
  {
    super(paramclass_371);
    new Vector3f();
    this.field_132 = true;
    new class_48();
  }
  
  private class_796 a40()
  {
    return a6().a14().field_4.field_4.field_4.a53().a40();
  }
  
  public final void handleKeyEvent()
  {
    if ((Keyboard.getEventKeyState()) && (Keyboard.getEventKey() >= 2) && (Keyboard.getEventKey() <= 11) && (!Keyboard.isKeyDown(29)))
    {
      int i = Keyboard.getEventKey() - 2;
      class_434 localclass_434 = this;
      a6().a20().d6(i);
      localclass_434.field_132 = true;
    }
  }
  
  public final void a12(class_939 paramclass_939) {}
  
  public final void b2(boolean paramBoolean)
  {
    Object localObject = a6().a14().field_4.field_4.field_4.a53();
    if (a6().a20() != null) {
      a6().a20().a55(null);
    }
    if (paramBoolean)
    {
      System.err.println("[CLIENT][SEGMENTEXTERNALCONTROLLER] ENTERED: " + a40());
      this.jdField_field_4_of_type_OrgSchemaSchineGraphicsengineCameraCamera = new class_191((class_457)localObject, class_969.a1(), a40());
      this.jdField_field_4_of_type_OrgSchemaSchineGraphicsengineCameraCamera.a(0.0F);
      class_48 localclass_48 = new class_48();
      if (!a6().a14().field_4.field_4.field_4.a53().a40().a2(localclass_48).equals(class_747.field_136)) {
        ((class_191)this.jdField_field_4_of_type_OrgSchemaSchineGraphicsengineCameraCamera).field_90 = true;
      } else {
        ((class_191)this.jdField_field_4_of_type_OrgSchemaSchineGraphicsengineCameraCamera).field_90 = false;
      }
      (localObject = ((class_447)localObject).a40().a2(new class_48())).c1(class_747.field_136);
      ((class_201)this.jdField_field_4_of_type_OrgSchemaSchineGraphicsengineCameraCamera.a184()).a85().b1((class_48)localObject);
      ((class_201)this.jdField_field_4_of_type_OrgSchemaSchineGraphicsengineCameraCamera.a184()).a2();
      class_969.a9(this.jdField_field_4_of_type_OrgSchemaSchineGraphicsengineCameraCamera);
    }
    else
    {
      this.jdField_field_4_of_type_OrgSchemaSchineGraphicsengineCameraCamera = null;
    }
    super.b2(paramBoolean);
  }
  
  private class_797 a43()
  {
    if ((a6().a23() == null) || (a6().a23().a26() == null)) {
      return null;
    }
    class_1387 localclass_1387 = a6().a23().a26();
    Vector3f localVector3f1 = new Vector3f();
    float f1 = 0.0F;
    float f2 = -1.0F;
    Object localObject = null;
    Vector3f localVector3f2 = localclass_1387.a1(new Vector3f());
    Iterator localIterator = a6().a7().values().iterator();
    while (localIterator.hasNext())
    {
      class_797 localclass_797;
      if ((localclass_797 = (class_797)localIterator.next()) != a6().a6())
      {
        localVector3f1.set(localclass_797.getWorldTransform().origin);
        if (a6().a6() != null) {
          localVector3f1.sub(a6().a6().getWorldTransform().origin);
        } else {
          localVector3f1.sub(class_969.a1().a83());
        }
        Vector3f localVector3f3 = localclass_1387.a2(localclass_797.getWorldTransform().origin, new Vector3f(), true);
        Vector3f localVector3f4 = new Vector3f();
        Vector3f localVector3f5 = new Vector3f();
        localVector3f4.sub(localVector3f3, localVector3f2);
        localVector3f5.sub(a40().a7().a15().getWorldTransform().origin, localclass_797.getWorldTransform().origin);
        if ((localVector3f4.length() < 90.0F) && ((localObject == null) || ((localVector3f4.length() < f1) && (localVector3f5.length() < f2))))
        {
          localObject = localclass_797;
          f1 = localVector3f4.length();
          f2 = localVector3f5.length();
        }
      }
    }
    return localObject;
  }
  
  public final void a15(class_941 paramclass_941)
  {
    super.a15(paramclass_941);
    Object localObject1 = this;
    if (this.field_132) {
      try
      {
        Object localObject2;
        if ((localObject2 = ((class_434)localObject1).a6().a20().a128(((class_434)localObject1).a40().a7().a15())) != null)
        {
          localObject2 = ((class_359)localObject2).a17(((class_434)localObject1).a6().a20().d1());
          if ((localObject2 = ((class_434)localObject1).a40().a7().a15().getSegmentBuffer().a9((class_48)localObject2, true)) != null) {
            ((class_434)localObject1).field_7 = (((class_796)localObject2).a9() == 54);
          }
          ((class_434)localObject1).field_132 = false;
        }
      }
      catch (Exception localException) {}
    }
    class_1046.field_1306 = true;
    if (this.field_7)
    {
      if (a6().a20().a120() != null) {
        this.field_5 += paramclass_941.a();
      }
      if ((this.field_5 > 0.0F) && (this.field_5 < 3.0F))
      {
        if (a43() == a6().a20().a120()) {
          this.field_5 = 0.0F;
        }
      }
      else
      {
        this.field_5 = 0.0F;
        localObject1 = a43();
        if (this.jdField_field_4_of_type_Class_797 != localObject1)
        {
          this.jdField_field_4_of_type_Class_797 = ((class_797)localObject1);
          this.jdField_field_4_of_type_Float = 0.0F;
          a6().a20().a55(null);
          return;
        }
        if (this.jdField_field_4_of_type_Class_797 != null)
        {
          this.jdField_field_4_of_type_Float += paramclass_941.a();
          if (this.jdField_field_4_of_type_Float > 5.0F) {
            a6().a20().a55(this.jdField_field_4_of_type_Class_797);
          }
        }
      }
    }
    else
    {
      a6().a20().a55(null);
      this.jdField_field_4_of_type_Float = 0.0F;
      this.field_5 = 0.0F;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_434
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */