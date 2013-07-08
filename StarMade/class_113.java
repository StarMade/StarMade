import com.bulletphysics.dynamics.RigidBody;
import java.util.List;
import javax.vecmath.Vector3f;
import org.lwjgl.input.Mouse;
import org.schema.game.common.controller.elements.ElementCollectionManager;
import org.schema.game.common.controller.elements.ManagerModuleSingle;
import org.schema.game.common.controller.elements.ShipManagerContainer;
import org.schema.game.common.controller.elements.thrust.ThrusterCollectionManager;
import org.schema.game.common.controller.elements.thrust.ThrusterElementManager;
import org.schema.game.network.objects.NetworkSector;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.network.client.ClientState;
import org.schema.schine.network.objects.container.PhysicsDataContainer;
import org.schema.schine.network.objects.remote.RemoteVector3i;

public class class_113
  extends class_1363
{
  private class_250 jdField_field_89_of_type_Class_250;
  private class_84 jdField_field_89_of_type_Class_84;
  private class_877 jdField_field_89_of_type_Class_877;
  private class_159 jdField_field_89_of_type_Class_159;
  private class_122 jdField_field_89_of_type_Class_122;
  private class_177 jdField_field_89_of_type_Class_177;
  private class_154 jdField_field_89_of_type_Class_154;
  private class_236 jdField_field_89_of_type_Class_236;
  private class_146 jdField_field_89_of_type_Class_146;
  private class_1414 jdField_field_89_of_type_Class_1414;
  private class_1414 field_90;
  private class_1414 field_92;
  private class_1414 field_93;
  private class_1414 field_94;
  private class_891 jdField_field_89_of_type_Class_891;
  private class_1414 field_95;
  private class_1414 field_96;
  private class_85 jdField_field_89_of_type_Class_85;
  private class_1414 field_97;
  
  public class_113(ClientState paramClientState)
  {
    super(paramClientState);
  }
  
  public final void e()
  {
    this.jdField_field_89_of_type_Class_154.e();
  }
  
  private boolean b3()
  {
    return ((class_371)super.a24()).a14().field_4.field_4.jdField_field_4_of_type_Class_19.field_6;
  }
  
  private boolean c1()
  {
    class_113 localclass_113 = this;
    return (localclass_113.a19().jdField_field_4_of_type_Class_453.field_6 ? localclass_113.a19().jdField_field_4_of_type_Class_453 : a18().a36().field_6 ? localclass_113.a18().a36() : null) != null;
  }
  
  public final void a2() {}
  
  protected final void d()
  {
    this.jdField_field_89_of_type_Class_122.h3(48);
    this.jdField_field_89_of_type_Class_159.h3(48);
    this.jdField_field_89_of_type_Class_154.h3(48);
    this.jdField_field_89_of_type_Class_177.h3(48);
    this.jdField_field_89_of_type_Class_877.h3(48);
    this.jdField_field_89_of_type_Class_236.h3(40);
    this.jdField_field_89_of_type_Class_146.h3(40);
  }
  
  public final void b()
  {
    if (k1()) {
      d();
    }
    class_1363.i();
    this.jdField_field_89_of_type_Class_891.b();
    class_236 localclass_236;
    if ((e1()) || (n()))
    {
      localclass_236 = null;
      this.jdField_field_89_of_type_Class_236.a29(true);
    }
    else
    {
      localclass_236 = null;
      this.jdField_field_89_of_type_Class_236.a29(false);
    }
    if (e1()) {
      this.jdField_field_89_of_type_Class_159.b();
    }
    if (b3()) {
      this.jdField_field_89_of_type_Class_122.b();
    }
    if (n()) {
      this.jdField_field_89_of_type_Class_877.b();
    }
    if (f1()) {
      this.jdField_field_89_of_type_Class_177.b();
    }
    if (o()) {
      this.jdField_field_89_of_type_Class_84.b();
    }
    if (p()) {
      this.jdField_field_89_of_type_Class_250.b();
    }
    if (m()) {
      this.jdField_field_89_of_type_Class_154.b();
    }
    if ((d1()) && (!((class_371)super.a24()).a14().field_4.field_4.jdField_field_4_of_type_Class_475.field_6)) {
      if (((((class_371)super.a24()).a14().field_4.field_4.jdField_field_4_of_type_Class_443.a51().a45().jdField_field_4_of_type_Class_332.field_6) || (m())) && (!n()) && (!e1()))
      {
        this.jdField_field_89_of_type_Class_146.b();
      }
      else
      {
        this.jdField_field_89_of_type_Class_236.b();
        if ((!n()) && (!e1()) && (c1())) {
          this.jdField_field_89_of_type_Class_85.b();
        }
      }
    }
    if ((((class_371)super.a24()).getDragging() != null) && ((((class_371)super.a24()).getDragging() instanceof class_185))) {
      if (((localObject = (class_185)((class_371)super.a24()).getDragging()).a70() == 0) || (((class_185)localObject).a68(true) <= 0)) {
        ((class_371)super.a24()).setDragging(null);
      } else {
        this.jdField_field_89_of_type_Class_159.a48((class_185)localObject);
      }
    }
    Object localObject = this;
    if (d1())
    {
      class_1363.i();
      localclass_236 = ((class_113)localObject).jdField_field_89_of_type_Class_236;
      if (!Mouse.isGrabbed())
      {
        GlUtil.d1();
        for (int i = 0; i < localclass_236.field_89.length; i++) {
          localclass_236.field_89[i].e();
        }
        GlUtil.c2();
      }
      class_1363.h1();
    }
    if (((class_113)localObject).e1())
    {
      class_1363.i();
      ((class_113)localObject).jdField_field_89_of_type_Class_159.e();
      class_1363.h1();
    }
    class_1363.h1();
  }
  
  public final float a3()
  {
    return 0.0F;
  }
  
  private class_447 a18()
  {
    return ((class_371)super.a24()).a14().field_4.field_4.jdField_field_4_of_type_Class_443.a53();
  }
  
  private class_328 a19()
  {
    return ((class_371)super.a24()).a14().field_4.field_4.jdField_field_4_of_type_Class_443.a51().a45();
  }
  
  public final class_371 a20()
  {
    return (class_371)super.a24();
  }
  
  public final float b1()
  {
    return 0.0F;
  }
  
  private boolean d1()
  {
    return ((class_371)super.a24()).a14().field_4.field_6;
  }
  
  private boolean e1()
  {
    return ((class_371)super.a24()).a14().field_4.field_4.jdField_field_4_of_type_Class_469.field_6;
  }
  
  public final boolean a4()
  {
    return (c1()) && (class_367.field_758.a6()) && (this.jdField_field_89_of_type_Class_85.a_());
  }
  
  private boolean f1()
  {
    return ((class_371)super.a24()).a14().field_4.field_4.jdField_field_4_of_type_Class_338.field_6;
  }
  
  public final void c()
  {
    class_119 localclass_119 = new class_119(this, (byte)0);
    this.field_93 = new class_1414((class_371)super.a24(), 39.0F, 26.0F);
    this.field_93.field_89 = "X";
    this.field_93.field_96 = true;
    this.field_93.a141(localclass_119);
    this.field_93.a83().set(804.0F, 4.0F, 0.0F);
    this.field_93.c();
    this.jdField_field_89_of_type_Class_1414 = new class_1414((class_371)super.a24(), 147.0F, 40.0F);
    this.jdField_field_89_of_type_Class_1414.field_89 = "INVENTORY";
    this.jdField_field_89_of_type_Class_1414.field_96 = true;
    this.jdField_field_89_of_type_Class_1414.a141(localclass_119);
    this.jdField_field_89_of_type_Class_1414.a83().set(216.0F, 26.0F, 0.0F);
    this.jdField_field_89_of_type_Class_1414.c();
    this.field_95 = new class_1414((class_371)super.a24(), 147.0F, 40.0F);
    this.field_95.field_89 = "AI";
    this.field_95.field_96 = true;
    this.field_95.a141(localclass_119);
    this.field_95.a83().set(662.0F, 472.0F, 0.0F);
    this.field_95.c();
    this.field_96 = new class_1414((class_371)super.a24(), 147.0F, 40.0F);
    this.field_96.field_89 = "FACTION";
    this.field_96.field_96 = true;
    this.field_96.a141(localclass_119);
    this.field_96.a83().set(517.0F, 472.0F, 0.0F);
    this.field_96.c();
    this.field_90 = new class_1414((class_371)super.a24(), 147.0F, 40.0F);
    this.field_90.field_89 = "WEAPON";
    this.field_90.field_96 = true;
    this.field_90.a141(localclass_119);
    this.field_90.a83().set(366.0F, 26.0F, 0.0F);
    this.field_90.c();
    this.field_97 = new class_1414((class_371)super.a24(), 147.0F, 40.0F);
    this.field_97.field_89 = "CATALOG";
    this.field_97.field_96 = true;
    this.field_97.a141(localclass_119);
    this.field_97.a83().set(366.0F, 472.0F, 0.0F);
    this.field_97.c();
    this.field_92 = new class_1414((class_371)super.a24(), 147.0F, 40.0F);
    this.field_92.field_89 = "SHOP";
    this.field_92.field_96 = true;
    this.field_92.a141(localclass_119);
    this.field_92.a83().set(514.0F, 26.0F, 0.0F);
    this.field_92.c();
    this.field_94 = new class_1414((class_371)super.a24(), 147.0F, 40.0F);
    this.field_94.field_89 = "NAVIGATION";
    this.field_94.field_96 = true;
    this.field_94.a141(localclass_119);
    this.field_94.a83().set(662.0F, 26.0F, 0.0F);
    this.field_94.c();
    this.jdField_field_89_of_type_Class_85 = new class_85((class_371)super.a24());
    this.jdField_field_89_of_type_Class_85.c();
    this.jdField_field_89_of_type_Class_122 = new class_122((class_371)super.a24());
    this.jdField_field_89_of_type_Class_122.c();
    this.jdField_field_89_of_type_Class_122.a9(this.jdField_field_89_of_type_Class_1414);
    this.jdField_field_89_of_type_Class_122.a9(this.field_92);
    this.jdField_field_89_of_type_Class_122.a9(this.field_90);
    this.jdField_field_89_of_type_Class_122.a9(this.field_93);
    this.jdField_field_89_of_type_Class_122.a9(this.field_94);
    this.jdField_field_89_of_type_Class_122.a9(this.field_95);
    this.jdField_field_89_of_type_Class_122.a9(this.field_96);
    this.jdField_field_89_of_type_Class_122.a9(this.field_97);
    this.jdField_field_89_of_type_Class_159 = new class_159((class_371)super.a24());
    this.jdField_field_89_of_type_Class_159.c();
    this.jdField_field_89_of_type_Class_159.a9(this.jdField_field_89_of_type_Class_1414);
    this.jdField_field_89_of_type_Class_159.a9(this.field_92);
    this.jdField_field_89_of_type_Class_159.a9(this.field_90);
    this.jdField_field_89_of_type_Class_159.a9(this.field_93);
    this.jdField_field_89_of_type_Class_159.a9(this.field_94);
    this.jdField_field_89_of_type_Class_159.a9(this.field_95);
    this.jdField_field_89_of_type_Class_159.a9(this.field_96);
    this.jdField_field_89_of_type_Class_159.a9(this.field_97);
    this.jdField_field_89_of_type_Class_154 = new class_154((class_371)super.a24());
    this.jdField_field_89_of_type_Class_154.c();
    this.jdField_field_89_of_type_Class_154.a9(this.jdField_field_89_of_type_Class_1414);
    this.jdField_field_89_of_type_Class_154.a9(this.field_92);
    this.jdField_field_89_of_type_Class_154.a9(this.field_90);
    this.jdField_field_89_of_type_Class_154.a9(this.field_93);
    this.jdField_field_89_of_type_Class_154.a9(this.field_94);
    this.jdField_field_89_of_type_Class_154.a9(this.field_95);
    this.jdField_field_89_of_type_Class_154.a9(this.field_96);
    this.jdField_field_89_of_type_Class_154.a9(this.field_97);
    this.jdField_field_89_of_type_Class_177 = new class_177((class_371)super.a24());
    this.jdField_field_89_of_type_Class_177.c();
    this.jdField_field_89_of_type_Class_177.a9(this.jdField_field_89_of_type_Class_1414);
    this.jdField_field_89_of_type_Class_177.a9(this.field_92);
    this.jdField_field_89_of_type_Class_177.a9(this.field_90);
    this.jdField_field_89_of_type_Class_177.a9(this.field_93);
    this.jdField_field_89_of_type_Class_177.a9(this.field_94);
    this.jdField_field_89_of_type_Class_177.a9(this.field_95);
    this.jdField_field_89_of_type_Class_177.a9(this.field_96);
    this.jdField_field_89_of_type_Class_177.a9(this.field_97);
    this.jdField_field_89_of_type_Class_877 = new class_877((class_371)super.a24());
    this.jdField_field_89_of_type_Class_877.c();
    this.jdField_field_89_of_type_Class_877.a9(this.jdField_field_89_of_type_Class_1414);
    this.jdField_field_89_of_type_Class_877.a9(this.field_92);
    this.jdField_field_89_of_type_Class_877.a9(this.field_90);
    this.jdField_field_89_of_type_Class_877.a9(this.field_93);
    this.jdField_field_89_of_type_Class_877.a9(this.field_94);
    this.jdField_field_89_of_type_Class_877.a9(this.field_95);
    this.jdField_field_89_of_type_Class_877.a9(this.field_96);
    this.jdField_field_89_of_type_Class_877.a9(this.field_97);
    this.jdField_field_89_of_type_Class_84 = new class_84((class_371)super.a24());
    this.jdField_field_89_of_type_Class_84.c();
    this.jdField_field_89_of_type_Class_84.a9(this.jdField_field_89_of_type_Class_1414);
    this.jdField_field_89_of_type_Class_84.a9(this.field_92);
    this.jdField_field_89_of_type_Class_84.a9(this.field_90);
    this.jdField_field_89_of_type_Class_84.a9(this.field_93);
    this.jdField_field_89_of_type_Class_84.a9(this.field_94);
    this.jdField_field_89_of_type_Class_84.a9(this.field_95);
    this.jdField_field_89_of_type_Class_84.a9(this.field_96);
    this.jdField_field_89_of_type_Class_84.a9(this.field_97);
    this.jdField_field_89_of_type_Class_250 = new class_250((class_371)super.a24());
    this.jdField_field_89_of_type_Class_250.c();
    this.jdField_field_89_of_type_Class_250.a9(this.jdField_field_89_of_type_Class_1414);
    this.jdField_field_89_of_type_Class_250.a9(this.field_92);
    this.jdField_field_89_of_type_Class_250.a9(this.field_90);
    this.jdField_field_89_of_type_Class_250.a9(this.field_93);
    this.jdField_field_89_of_type_Class_250.a9(this.field_94);
    this.jdField_field_89_of_type_Class_250.a9(this.field_95);
    this.jdField_field_89_of_type_Class_250.a9(this.field_96);
    this.jdField_field_89_of_type_Class_250.a9(this.field_97);
    this.jdField_field_89_of_type_Class_236 = new class_236(class_969.a2().a5("sidebar-gui-"), (class_371)super.a24());
    this.jdField_field_89_of_type_Class_236.c();
    this.jdField_field_89_of_type_Class_146 = new class_146(class_969.a2().a5("sidebar-gui-"), (class_371)super.a24());
    this.jdField_field_89_of_type_Class_146.c();
    this.jdField_field_89_of_type_Class_891 = new class_891((class_371)super.a24());
    this.jdField_field_89_of_type_Class_891.c();
    d();
  }
  
  private boolean m()
  {
    return ((class_371)super.a24()).a14().field_4.field_4.jdField_field_4_of_type_Class_334.field_5;
  }
  
  private boolean n()
  {
    return ((class_371)super.a24()).a14().field_4.field_4.jdField_field_4_of_type_Class_303.field_6;
  }
  
  private boolean o()
  {
    return ((class_371)super.a24()).a14().field_4.field_4.jdField_field_4_of_type_Class_423.field_6;
  }
  
  private boolean p()
  {
    return ((class_371)super.a24()).a14().field_4.field_4.jdField_field_4_of_type_Class_427.field_6;
  }
  
  public final void a12(class_941 paramclass_941)
  {
    class_891 localclass_891;
    Object localObject1;
    long l = (localObject1 = (class_371)(localclass_891 = this.jdField_field_89_of_type_Class_891).a24()).a20().b5();
    localclass_891.field_89.field_90.set(0, String.valueOf(l));
    class_797 localclass_797 = ((class_371)localObject1).a6();
    int i = 0;
    try
    {
      if (localclass_797 != null)
      {
        Object localObject2;
        Object localObject3;
        if ((localclass_797.getPhysicsDataContainer().isInitialized()) && ((localclass_797.getPhysicsDataContainer().getObject() instanceof RigidBody)))
        {
          localObject2 = (RigidBody)localclass_797.getPhysicsDataContainer().getObject();
          localObject3 = new Vector3f();
          ((RigidBody)localObject2).getLinearVelocity((Vector3f)localObject3);
          i = 1;
          localclass_891.field_92.field_90.set(0, class_41.a2(((Vector3f)localObject3).length()));
        }
        if ((localclass_797 instanceof class_747))
        {
          localObject2 = (class_747)localclass_797;
          if (((class_371)localclass_891.a24()).a14().field_4.field_4.jdField_field_4_of_type_Class_443.a51().a45().jdField_field_4_of_type_Class_453.field_5)
          {
            localclass_891.field_90.field_90.set(0, ((class_747)localObject2).getRealName() + ", Sec: " + ((class_371)localObject1).a20().a44());
            localclass_891.field_90.field_90.set(1, "Thrust/Mass: " + class_41.a2(((ThrusterCollectionManager)((class_747)localObject2).a112().getThrust().getCollectionManager()).getTotalThrust()) + " - " + class_41.a2(((class_747)localObject2).getMass()) + " = " + class_41.a2(((class_747)localObject2).a112().getThrusterElementManager().getActualThrust()));
          }
          else
          {
            localclass_891.field_90.field_90.set(0, ((class_747)localObject2).getRealName());
            localObject1 = class_789.a192(localObject3 = ((class_371)localObject1).a9().a35().pos.getVector(), new class_48());
            boolean bool2 = class_791.a19((class_48)localObject3);
            localclass_891.field_90.field_90.set(1, (bool2 ? "Star" : "Void") + "Sys: " + localObject1 + "; Sec: " + localObject3);
          }
        }
        else
        {
          localclass_891.field_90.field_90.set(0, ((class_371)localObject1).getPlayerName());
          class_789.a192(localObject2 = ((class_371)localObject1).a9().a35().pos.getVector(), new class_48());
          boolean bool1 = class_791.a19((class_48)localObject2);
          localclass_891.field_90.field_90.set(1, (bool1 ? "Star" : "Void") + "System Sector: " + localObject2);
        }
      }
    }
    catch (Exception localException) {}
    if (i == 0) {
      localclass_891.field_92.field_90.set(0, "0");
    }
    this.jdField_field_89_of_type_Class_154.a12(paramclass_941);
    this.jdField_field_89_of_type_Class_236.a12(paramclass_941);
    this.jdField_field_89_of_type_Class_146.a12(paramclass_941);
    this.jdField_field_89_of_type_Class_122.a12(paramclass_941);
    this.jdField_field_89_of_type_Class_85.a12(paramclass_941);
    super.a12(paramclass_941);
  }
  
  public final void a21(ElementCollectionManager paramElementCollectionManager)
  {
    this.jdField_field_89_of_type_Class_154.a21(paramElementCollectionManager);
  }
  
  public final class_236 a22()
  {
    return this.jdField_field_89_of_type_Class_236;
  }
  
  public final class_177 a23()
  {
    return this.jdField_field_89_of_type_Class_177;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_113
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */