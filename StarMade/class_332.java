import com.bulletphysics.linearmath.Transform;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import javax.vecmath.Vector3f;
import org.lwjgl.input.Keyboard;
import org.schema.common.FastMath;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.ShipManagerContainer;
import org.schema.game.common.data.element.ElementDocking;
import org.schema.game.common.data.world.Segment;
import org.schema.game.network.objects.NetworkPlayer;
import org.schema.schine.graphicsengine.camera.Camera;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.objects.Sendable;
import org.schema.schine.network.objects.remote.RemoteVector3i;

public class class_332
  extends class_16
{
  private Camera jdField_field_4_of_type_OrgSchemaSchineGraphicsengineCameraCamera;
  private float jdField_field_4_of_type_Float = 0.0F;
  private float field_5 = 0.0F;
  private class_797 jdField_field_4_of_type_Class_797;
  private boolean field_7 = false;
  private int jdField_field_4_of_type_Int = -1;
  private boolean field_132;
  private class_48 jdField_field_4_of_type_Class_48;
  
  public class_332(class_328 paramclass_328)
  {
    super(paramclass_328.a6());
    new class_755();
    new Vector3f();
    this.field_132 = true;
    this.jdField_field_4_of_type_Class_48 = new class_48();
  }
  
  public static float a39()
  {
    return 5.0F;
  }
  
  private class_796 a40()
  {
    return a6().a14().field_4.field_4.field_4.a51().a40();
  }
  
  public final class_48 a41(class_48 paramclass_48)
  {
    return a6().a14().field_4.field_4.field_4.a51().a40().a2(paramclass_48);
  }
  
  public final class_747 a42()
  {
    return a6().a25();
  }
  
  public final class_797 a43()
  {
    return this.jdField_field_4_of_type_Class_797;
  }
  
  public final float b4()
  {
    return this.jdField_field_4_of_type_Float;
  }
  
  public void handleKeyEvent()
  {
    if (Keyboard.getEventKeyState())
    {
      if ((Keyboard.getEventKey() >= 2) && (Keyboard.getEventKey() <= 11) && (!Keyboard.isKeyDown(29)))
      {
        int j = Keyboard.getEventKey() - 2;
        class_332 localclass_332 = this;
        a6().a20().d6(j);
        localclass_332.field_132 = true;
      }
      int i;
      Object localObject2;
      Object localObject3;
      class_796 localclass_796;
      Object localObject1;
      if ((Keyboard.isKeyDown(29)) && (!a6().a25().getDockingController().a5().isEmpty()) && ((i = Keyboard.getEventKey() - 2) < a6().a25().getDockingController().a5().size()))
      {
        localObject2 = a6().a25().getDockingController().a5().iterator();
        localObject3 = null;
        for (int k = 0; k <= i; k++) {
          localObject3 = (ElementDocking)((Iterator)localObject2).next();
        }
        (localclass_796 = a40()).a7().a15();
        localObject1 = a40().a2(new class_48());
        System.err.println("EXIT SHIP FROM EXTRYPOINT " + localObject1);
        a6().a14().field_4.field_4.field_4.a51().a16(((ElementDocking)localObject3).from);
        a6().a4().a14((class_365)localclass_796.a7().a15(), (class_365)a40().a7().a15(), localclass_796.a2(new class_48()), a40().a2(new class_48()), true);
        this.jdField_field_4_of_type_Int = -1;
      }
      if ((Keyboard.getEventKey() == class_367.field_739.a5()) && (a6().a25().getDockingController().a4() != null))
      {
        localObject1 = null;
        localObject2 = a6().a14().field_4.field_4.field_4;
        try
        {
          (localclass_796 = a40()).a7().a15();
          localObject1 = a40().a2(new class_48());
          System.err.println("EXIT SHIP FROM EXTRYPOINT " + localObject1);
          localObject3 = a6().a25().getDockingController().a4().field_1740.a7().a15().getSegmentBuffer().a9(class_747.field_136, false);
          ((class_443)localObject2).a51().a16((class_796)localObject3);
          a6().a4().a14((class_365)localclass_796.a7().a15(), (class_365)a40().a7().a15(), localclass_796.a2(new class_48()), a40().a2(new class_48()), true);
          this.jdField_field_4_of_type_Int = -1;
        }
        catch (IOException localIOException)
        {
          localIOException;
        }
        catch (InterruptedException localInterruptedException)
        {
          localInterruptedException;
        }
      }
      if ((!(localObject1 = a6().a25().a112().getCockpits()).isEmpty()) && (a40().a9() == 1))
      {
        if (Keyboard.getEventKey() == class_367.field_741.a5())
        {
          if (this.jdField_field_4_of_type_Int == 0)
          {
            this.jdField_field_4_of_type_Int = -1;
            (localObject3 = a6().a14().field_4.field_4.field_4.a51().a40().a2(new class_48())).c1(class_747.field_136);
            ((class_201)this.jdField_field_4_of_type_OrgSchemaSchineGraphicsengineCameraCamera.a184()).a85().b1((class_48)localObject3);
            return;
          }
          if (this.jdField_field_4_of_type_Int < 0) {
            this.jdField_field_4_of_type_Int = (((ArrayList)localObject1).size() - 1);
          } else {
            this.jdField_field_4_of_type_Int = FastMath.b1(this.jdField_field_4_of_type_Int - 1, ((ArrayList)localObject1).size());
          }
          (localObject2 = new class_48((class_48)((ArrayList)localObject1).get(this.jdField_field_4_of_type_Int))).c1(class_747.field_136);
          ((class_201)this.jdField_field_4_of_type_OrgSchemaSchineGraphicsengineCameraCamera.a184()).a85().b1((class_48)localObject2);
          return;
        }
        if (Keyboard.getEventKey() == class_367.field_740.a5())
        {
          if (this.jdField_field_4_of_type_Int + 1 >= ((ArrayList)localObject1).size())
          {
            this.jdField_field_4_of_type_Int = -1;
            (localObject3 = a6().a14().field_4.field_4.field_4.a51().a40().a2(new class_48())).c1(class_747.field_136);
            ((class_201)this.jdField_field_4_of_type_OrgSchemaSchineGraphicsengineCameraCamera.a184()).a85().b1((class_48)localObject3);
            return;
          }
          if (this.jdField_field_4_of_type_Int < 0) {
            this.jdField_field_4_of_type_Int = 0;
          } else {
            this.jdField_field_4_of_type_Int = FastMath.b1(this.jdField_field_4_of_type_Int + 1, ((ArrayList)localObject1).size());
          }
          (localObject2 = new class_48((class_48)((ArrayList)localObject1).get(this.jdField_field_4_of_type_Int))).c1(class_747.field_136);
          ((class_201)this.jdField_field_4_of_type_OrgSchemaSchineGraphicsengineCameraCamera.a184()).a85().b1((class_48)localObject2);
        }
      }
    }
  }
  
  public final void a12(class_939 paramclass_939)
  {
    if ((class_949.field_1242.a4().equals("SLOTS")) && (!Keyboard.isKeyDown(42)))
    {
      paramclass_939 = FastMath.b1(a6().a20().d1() + (paramclass_939.field_1168 < 0 ? -1 : paramclass_939.field_1168 > 0 ? 1 : 0), 10);
      a6().a20().d6(paramclass_939);
    }
  }
  
  public final boolean a1()
  {
    return this.field_7;
  }
  
  public final void b2(boolean paramBoolean)
  {
    Object localObject = a6().a14().field_4.field_4.field_4.a51();
    if (a6().a20() != null) {
      a6().a20().a55(null);
    }
    if (paramBoolean)
    {
      this.field_132 = true;
      if ((this.jdField_field_4_of_type_OrgSchemaSchineGraphicsengineCameraCamera == null) || (((class_960)this.jdField_field_4_of_type_OrgSchemaSchineGraphicsengineCameraCamera.a184()).a142() != a6().a25()))
      {
        if ((!field_133) && (a6().a25() == null)) {
          throw new AssertionError("SHIP NOT FOUND ");
        }
        this.jdField_field_4_of_type_OrgSchemaSchineGraphicsengineCameraCamera = new class_191(((class_342)localObject).a45(), class_969.a1(), a40());
        this.jdField_field_4_of_type_OrgSchemaSchineGraphicsengineCameraCamera.a(0.0F);
        if (!a41(new class_48()).equals(class_747.field_136)) {
          ((class_191)this.jdField_field_4_of_type_OrgSchemaSchineGraphicsengineCameraCamera).field_90 = true;
        } else {
          ((class_191)this.jdField_field_4_of_type_OrgSchemaSchineGraphicsengineCameraCamera).field_90 = false;
        }
      }
      else if (this.jdField_field_4_of_type_OrgSchemaSchineGraphicsengineCameraCamera != null)
      {
        ((class_191)this.jdField_field_4_of_type_OrgSchemaSchineGraphicsengineCameraCamera).a78(class_969.a1());
      }
      if ((this.jdField_field_4_of_type_Int >= 0) && (this.jdField_field_4_of_type_Int < a6().a25().a112().getCockpits().size()))
      {
        (localObject = new class_48((class_48)a6().a25().a112().getCockpits().get(this.jdField_field_4_of_type_Int))).c1(class_747.field_136);
        ((class_201)this.jdField_field_4_of_type_OrgSchemaSchineGraphicsengineCameraCamera.a184()).a85().b1((class_48)localObject);
      }
      else
      {
        this.jdField_field_4_of_type_Int = -1;
        (localObject = ((class_342)localObject).a40().a2(new class_48())).c1(class_747.field_136);
        ((class_201)this.jdField_field_4_of_type_OrgSchemaSchineGraphicsengineCameraCamera.a184()).a85().b1((class_48)localObject);
      }
      a6().a4().c2("BuildMode");
      a6().a4().a26("FlightMode", "Flight Mode", "(press " + class_367.field_728.b1() + " to switch to BUILD MODE; press " + class_367.field_732.b1() + " to exit structure)");
      class_969.a9(this.jdField_field_4_of_type_OrgSchemaSchineGraphicsengineCameraCamera);
    }
    else
    {
      this.jdField_field_4_of_type_OrgSchemaSchineGraphicsengineCameraCamera = null;
    }
    super.b2(paramBoolean);
  }
  
  private class_797 b5()
  {
    if ((a6().a23() == null) || (a6().a23().a26() == null)) {
      return null;
    }
    class_1387 localclass_1387 = a6().a23().a26();
    Vector3f localVector3f1 = new Vector3f();
    float f1 = 0.0F;
    float f2 = -1.0F;
    Object localObject2 = null;
    Vector3f localVector3f2 = localclass_1387.a1(new Vector3f());
    synchronized (a6().getLocalAndRemoteObjectContainer().getLocalObjects())
    {
      Iterator localIterator = a6().getLocalAndRemoteObjectContainer().getLocalObjects().values().iterator();
      while (localIterator.hasNext())
      {
        Object localObject3;
        if (((localObject3 = (Sendable)localIterator.next()) instanceof class_797)) {
          if ((localObject3 = (class_797)localObject3) != a6().a6())
          {
            localVector3f1.set(((class_797)localObject3).getWorldTransform().origin);
            if (a6().a6() != null) {
              localVector3f1.sub(a6().a6().getWorldTransform().origin);
            } else {
              localVector3f1.sub(class_969.a1().a83());
            }
            Vector3f localVector3f3 = localclass_1387.a2(((class_797)localObject3).getWorldTransformClient().origin, new Vector3f(), true);
            Vector3f localVector3f4 = new Vector3f();
            Vector3f localVector3f5 = new Vector3f();
            localVector3f4.sub(localVector3f3, localVector3f2);
            localVector3f5.sub(a6().a25().getWorldTransform().origin, ((class_797)localObject3).getWorldTransformClient().origin);
            if ((localVector3f4.length() < 90.0F) && ((localObject2 == null) || ((localVector3f4.length() < f1) && (localVector3f5.length() < f2))))
            {
              localObject2 = localObject3;
              f1 = localVector3f4.length();
              f2 = localVector3f5.length();
            }
          }
        }
      }
    }
    return localObject2;
  }
  
  public final void a15(class_941 paramclass_941)
  {
    super.a15(paramclass_941);
    Object localObject1 = this;
    if (this.field_132) {
      try
      {
        Object localObject2;
        if ((localObject2 = ((class_332)localObject1).a6().a20().a128(((class_332)localObject1).a6().a25())) != null)
        {
          if ((localObject2 = ((class_359)localObject2).a17(((class_332)localObject1).a6().a20().d1())) != null)
          {
            if ((localObject2 = ((class_332)localObject1).a6().a25().getSegmentBuffer().a9((class_48)localObject2, true)) != null) {
              ((class_332)localObject1).field_7 = (((class_796)localObject2).a9() == 54);
            }
          }
          else {
            ((class_332)localObject1).field_7 = false;
          }
          ((class_332)localObject1).field_132 = false;
        }
      }
      catch (Exception localException) {}
    }
    if ((class_367.field_759.a6()) && ((class_969.a1() instanceof class_191))) {
      ((class_191)class_969.a1()).b();
    }
    class_1046.field_1306 = true;
    if (this.jdField_field_4_of_type_Int >= 0)
    {
      this.jdField_field_4_of_type_Class_48.b1(((class_201)this.jdField_field_4_of_type_OrgSchemaSchineGraphicsengineCameraCamera.a184()).a85());
      this.jdField_field_4_of_type_Class_48.a1(class_747.field_136);
      if (!a6().a25().a112().getCockpits().contains(this.jdField_field_4_of_type_Class_48))
      {
        this.jdField_field_4_of_type_Int = -1;
        (localObject1 = a6().a14().field_4.field_4.field_4.a51().a40().a2(new class_48())).c1(class_747.field_136);
        ((class_201)this.jdField_field_4_of_type_OrgSchemaSchineGraphicsengineCameraCamera.a184()).a85().b1((class_48)localObject1);
      }
      if (this.jdField_field_4_of_type_Int >= 0)
      {
        if (!a6().a20().a127().cockpit.getVector().equals(this.jdField_field_4_of_type_Class_48))
        {
          a6().a20().a127().cockpit.forceClientUpdates();
          a6().a20().a127().cockpit.set(this.jdField_field_4_of_type_Class_48);
        }
      }
      else if (!a6().a20().a127().cockpit.getVector().equals(class_747.field_136))
      {
        a6().a20().a127().cockpit.forceClientUpdates();
        a6().a20().a127().cockpit.set(class_747.field_136);
      }
    }
    if (this.field_7)
    {
      if (a6().a20().a120() != null) {
        this.field_5 += paramclass_941.a();
      }
      if ((this.field_5 > 0.0F) && (this.field_5 < 3.0F))
      {
        if (b5() == a6().a20().a120()) {
          this.field_5 = 0.0F;
        }
      }
      else
      {
        this.field_5 = 0.0F;
        localObject1 = b5();
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
 * Qualified Name:     class_332
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */