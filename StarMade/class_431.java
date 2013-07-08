import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
import com.bulletphysics.linearmath.Transform;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Observer;
import javax.vecmath.Tuple3f;
import javax.vecmath.Vector3f;
import org.lwjgl.input.Keyboard;
import org.schema.game.common.controller.CannotBeControlledException;
import org.schema.game.common.controller.CannotImmediateRequestOnClientException;
import org.schema.game.common.controller.EditableSendableSegmentController;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.LiftContainerInterface;
import org.schema.game.common.controller.elements.PowerAddOn;
import org.schema.game.common.controller.elements.PowerManagerInterface;
import org.schema.game.common.controller.elements.ShieldContainerInterface;
import org.schema.game.common.controller.elements.lift.LiftCollectionManager;
import org.schema.game.common.controller.elements.shield.ShieldCollectionManager;
import org.schema.game.common.data.element.ControlElementMap;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.game.common.data.physics.CubeRayCastResult;
import org.schema.game.common.data.physics.PhysicsExt;
import org.schema.game.common.data.world.Segment;
import org.schema.game.network.objects.NetworkSegmentController;
import org.schema.schine.graphicsengine.camera.Camera;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.graphicsengine.shader.ErrorDialogException;
import org.schema.schine.network.objects.remote.RemoteBuffer;
import org.schema.schine.network.objects.remote.RemoteVector4i;

public final class class_431
  extends class_485
{
  private Camera jdField_field_4_of_type_OrgSchemaSchineGraphicsengineCameraCamera;
  String jdField_field_4_of_type_JavaLangString;
  private String field_5 = "To create a new ship, please enter the name of your choice.\nthe name cannot be changed later (yet)";
  public class_796 field_4;
  private String field_6;
  private String jdField_field_7_of_type_JavaLangString;
  private final class_433 jdField_field_4_of_type_Class_433 = new class_433();
  private boolean jdField_field_7_of_type_Boolean;
  
  public class_431(class_371 paramclass_371)
  {
    super(paramclass_371);
  }
  
  private void f2(boolean paramBoolean)
  {
    Object localObject;
    if (((localObject = a47()) != null) && (((CollisionWorld.ClosestRayResultCallback)localObject).hasHit()) && ((localObject instanceof CubeRayCastResult)))
    {
      if (((localObject = (CubeRayCastResult)localObject).getSegment() != null) && ((((CubeRayCastResult)localObject).getSegment().a15() instanceof EditableSendableSegmentController)))
      {
        ((CubeRayCastResult)localObject).getSegment().a15().getControlElementMap().setObs(this);
        Vector3f localVector3f1 = new Vector3f(a6().a3().a145().origin);
        Vector3f localVector3f2 = new Vector3f(class_969.a1().c10());
        if (paramBoolean)
        {
          a6().a14().field_4.jdField_field_4_of_type_Class_445.field_4.a48((EditableSendableSegmentController)((CubeRayCastResult)localObject).getSegment().a15(), localVector3f1, localVector3f2, new class_417(this), new class_713(), this.jdField_field_4_of_type_Class_433, 6.0F);
          return;
        }
        a6().a14().field_4.jdField_field_4_of_type_Class_445.field_4.a49((EditableSendableSegmentController)((CubeRayCastResult)localObject).getSegment().a15(), localVector3f1, localVector3f2, 6.0F, this.jdField_field_4_of_type_Class_433);
      }
      return;
    }
    System.err.println("[PLAYEREXTERNALCONTROLLER] CUBE RESULT NOT AVAILABLE");
  }
  
  private CollisionWorld.ClosestRayResultCallback a47()
  {
    Vector3f localVector3f1 = new Vector3f(a6().a3().a145().origin);
    Vector3f localVector3f2 = new Vector3f(a6().a3().a145().origin);
    Object localObject;
    (localObject = new Vector3f(class_969.a1().c10())).scale(6.0F);
    localVector3f2.add((Tuple3f)localObject);
    (localObject = new CubeRayCastResult(localVector3f1, localVector3f2, Boolean.valueOf(false), null)).setIgnoereNotPhysical(true);
    ((CubeRayCastResult)localObject).setRespectShields(false);
    ((CubeRayCastResult)localObject).onlyCubeMeshes = true;
    return ((PhysicsExt)a6().a19()).testRayCollisionPoint(localVector3f1, localVector3f2, (CubeRayCastResult)localObject, false);
  }
  
  public final class_796 a40()
  {
    return this.jdField_field_4_of_type_Class_796;
  }
  
  public final void handleKeyEvent()
  {
    super.handleKeyEvent();
    if (Keyboard.getEventKeyState())
    {
      Object localObject6 = null;
      Keyboard.getEventKey();
      if (a6().a14().field_4.jdField_field_4_of_type_Class_445.field_4.a1()) {
        return;
      }
      Object localObject1;
      Object localObject4;
      Object localObject5;
      Object localObject7;
      if (Keyboard.getEventKey() == class_367.field_733.a5())
      {
        localObject1 = a6().a3().a146(true);
        localObject4 = localObject1;
        localObject1 = this;
        if ((localObject4 != null) && (((class_431)localObject1).a6().a4().b5(((class_796)localObject4).a7().a15()))) {
          if (((class_796)localObject4).a9() == 56)
          {
            if (!((class_431)localObject1).a6().a3().getGravity().a())
            {
              ((class_431)localObject1).a6().a3().scheduleGravity(new Vector3f(0.0F, -9.89F, 0.0F), ((class_796)localObject4).a7().a15());
              ((class_431)localObject1).a6().a4().c1("You entered the\nGravity of \n" + ((class_796)localObject4).a7().a15().toNiceString());
              System.err.println("[CLIENT][ACTIVATE] Enter gravity of " + ((class_796)localObject4).a7().a15().getId());
              break label2050;
            }
            if (((class_431)localObject1).a6().a3().getGravity().field_928 == ((class_796)localObject4).a7().a15())
            {
              ((class_431)localObject1).a6().a3().scheduleGravity(new Vector3f(0.0F, 0.0F, 0.0F), null);
              ((class_431)localObject1).a6().a4().c1("You exited the\nGravity of \n" + ((class_796)localObject4).a7().a15().toNiceString());
              System.err.println("[CLIENT][ACTIVATE] Exit gravity of " + ((class_796)localObject4).a7().a15().getId());
              break label2050;
            }
          }
          else if (((class_796)localObject4).a9() == 94)
          {
            localObject5 = new Transform();
            ((class_796)localObject4).a8((Transform)localObject5);
            (localObject6 = GlUtil.f(new Vector3f(), (Transform)localObject5)).scale(1.5F);
            ((Transform)localObject5).origin.add((Tuple3f)localObject6);
            ((class_431)localObject1).a6().a4().a24(((Transform)localObject5).origin);
            ((class_431)localObject1).a6().a4().d1("Spawning Point Set");
          }
          else if (((class_796)localObject4).a9() == 121)
          {
            ((class_431)localObject1).a6().a4().c1("Acitvated ships AI Element\n" + ((class_796)localObject4).a7().a15().toNiceString());
            ((class_431)localObject1).a6().a14().field_4.jdField_field_4_of_type_Class_445.a16((class_796)localObject4);
          }
          else if (((class_796)localObject4).a9() == 291)
          {
            if (((class_431)localObject1).a6().a20().h1() != 0)
            {
              if (((class_431)localObject1).a6().a45().a156(((class_431)localObject1).a6().a20().h1()) != null)
              {
                ((class_431)localObject1).a6().a4().c1("Acitvated faction block\n" + ((class_796)localObject4).a7().a15().toNiceString());
                ((class_431)localObject1).e2(true);
                new class_432((class_431)localObject1, ((class_431)localObject1).a6(), ((class_796)localObject4).a7().a15()).c1();
              }
              else
              {
                ((class_431)localObject1).a6().a4().c1("Cannot activate:\ninvalid faction ID\non you" + ((class_796)localObject4).a7().a15().toNiceString());
              }
            }
            else {
              ((class_431)localObject1).a6().a4().c1("You have to be in a faction\nto activate this block" + ((class_796)localObject4).a7().a15().toNiceString());
            }
          }
          else
          {
            Object localObject8;
            Object localObject9;
            if (((class_796)localObject4).a9() == 113)
            {
              System.err.println("CHECKING FOR LIFT");
              if ((((localObject5 = ((class_796)localObject4).a7().a15()) instanceof class_798)) && ((((class_798)localObject5).a() instanceof LiftContainerInterface)))
              {
                localObject8 = (localObject6 = (LiftContainerInterface)((class_798)localObject5).a()).getLiftManager();
                localObject9 = ((class_796)localObject4).a2(new class_48());
                ((LiftCollectionManager)localObject8).activate((class_48)localObject9, true);
                ((LiftContainerInterface)localObject6).handleClientRemoteLift((class_48)localObject9);
              }
            }
            else if (((class_796)localObject4).a9() == 2)
            {
              if ((((localObject5 = ((class_796)localObject4).a7().a15()) instanceof class_798)) && ((((class_798)localObject5).a() instanceof PowerManagerInterface)))
              {
                localObject8 = ((PowerManagerInterface)((class_798)localObject5).a()).getPowerAddOn();
                if (((class_431)localObject1).field_6 != null) {
                  ((class_431)localObject1).a6().a4().a2(((class_431)localObject1).field_6);
                }
                ((class_431)localObject1).field_6 = ("POWER INFO\n\nLoad: " + (int)((PowerAddOn)localObject8).getPower() + "/" + (int)((PowerAddOn)localObject8).getMaxPower() + "\nRecharge Rate (e/sec): " + (int)((PowerAddOn)localObject8).getRecharge());
                ((class_431)localObject1).a6().a4().d1(((class_431)localObject1).field_6);
              }
            }
            else if (((class_796)localObject4).a9() == 3)
            {
              if ((((localObject5 = ((class_796)localObject4).a7().a15()) instanceof class_798)) && ((((class_798)localObject5).a() instanceof ShieldContainerInterface)))
              {
                localObject8 = ((ShieldContainerInterface)((class_798)localObject5).a()).getShieldManager();
                if (((class_431)localObject1).jdField_field_7_of_type_JavaLangString != null) {
                  ((class_431)localObject1).a6().a4().a2(((class_431)localObject1).jdField_field_7_of_type_JavaLangString);
                }
                ((class_431)localObject1).jdField_field_7_of_type_JavaLangString = ("SHIELD INFO\n\nLoad: " + (int)((ShieldCollectionManager)localObject8).getShields() + "/" + (int)((ShieldCollectionManager)localObject8).getShieldCapabilityHP() + "\nRecharge Rate (e/sec): " + (int)((ShieldCollectionManager)localObject8).getShieldRechargeRate() + "\nPower Usage (e/sec): " + (int)(((ShieldCollectionManager)localObject8).getShieldRechargeRate() * 500L));
                ((class_431)localObject1).a6().a4().d1(((class_431)localObject1).jdField_field_7_of_type_JavaLangString);
              }
            }
            else if ((((class_796)localObject4).a9() == 120) || (((class_796)localObject4).a9() == 114) || (ElementKeyMap.getFactorykeyset().contains(Short.valueOf(((class_796)localObject4).a9()))))
            {
              if ((((localObject5 = ((class_796)localObject4).a7().a15()) instanceof class_798)) && ((((class_798)localObject5).a() instanceof class_635)))
              {
                localObject6 = ((class_796)localObject4).a2(new class_48());
                localObject9 = (localObject8 = ((class_798)localObject5).a()).getInventory((class_48)localObject6);
                System.err.println("CHECK FOR INVENTORY: " + localObject6 + " -> " + localObject9);
                if (localObject9 == null) {
                  System.err.println("Inventory NULL: " + ((class_635)localObject8).printInventories());
                } else {
                  ((class_431)localObject1).a6().a4().c1("Opened\n" + ElementKeyMap.getInfo(((class_796)localObject4).a9()).getName() + "\nLocation: " + ((class_639)localObject9).a44() + "\nof " + ((SegmentController)localObject5).toNiceString());
                }
                ((class_431)localObject1).a6().a14().field_4.jdField_field_4_of_type_Class_445.a63((class_639)localObject9);
              }
              else
              {
                ((class_431)localObject1).a6().a4().b1("Error: Chest/Recycler Element\ndoes only work on\nships & space stations (yet)");
              }
            }
            else
            {
              int i = 0;
              if (!ElementKeyMap.getInfo(((class_796)localObject4).a9()).isEnterable())
              {
                System.err.println("[EXTERNALPLAYER] ACTIVATE BLOCK (std) " + localObject4);
                localObject5 = ((class_796)localObject4).a2(new class_48());
                localObject7 = new class_46(((class_48)localObject5).field_475, ((class_48)localObject5).field_476, ((class_48)localObject5).field_477, ((class_796)localObject4).a10() ? 0 : 1);
                ((NetworkSegmentController)((class_796)localObject4).a7().a15().getNetworkObject()).blockActivationBuffer.add(new RemoteVector4i((class_46)localObject7, ((class_796)localObject4).a7().a15().getNetworkObject()));
              }
              else
              {
                localObject5 = localObject4;
                localObject4 = localObject1;
                ((class_431)localObject4).a6().a4().b1("ERROR\n \nCannot enter here.\nOnly ships can be entered\nat the moment!");
                localObject7 = null;
                localObject7 = ((class_431)localObject4).a6().a14().field_4.jdField_field_4_of_type_Class_445.field_4;
                if ((((class_796)localObject5).a7().a15() instanceof class_747))
                {
                  ((class_431)localObject4).a6().a4().b1("ERROR\n \nCannot enter here.\nThere is already someone in this ship\nyou can still enter\ncomputers, etc. though");
                  ((class_443)localObject7).a51().a16((class_796)localObject5);
                }
                else if ((((class_796)localObject5).a7().a15() instanceof class_737))
                {
                  ((class_443)localObject7).a53().a16((class_796)localObject5);
                }
                else if ((((class_796)localObject5).a7().a15() instanceof class_864))
                {
                  ((class_443)localObject7).a53().a16((class_796)localObject5);
                }
                else if ((((class_796)localObject5).a7().a15() instanceof class_705))
                {
                  ((class_443)localObject7).a53().a16((class_796)localObject5);
                }
                else
                {
                  throw new ErrorDialogException("Cannot enter " + ((class_796)localObject5).a7().a15());
                }
                ((class_431)localObject4).a6().a4().a14(((class_431)localObject4).a6().a3(), (class_365)((class_796)localObject5).a7().a15(), new class_48(), ((class_796)localObject5).a2(new class_48()), true);
                ((class_431)localObject4).a6().a4().b1("ERROR\n \nCannot enter ship here.\nMust enter at core or\nanother controller!");
                if ((ElementKeyMap.getInfo(((class_796)localObject5).a9()).isEnterable() ? 1 : (((class_796)localObject5).a9() == 1) && (!((class_747)((class_796)localObject5).a7().a15()).a75().isEmpty()) ? 0 : !((localObject5 = localObject5).a7().a15() instanceof class_365) ? 0 : (localObject5 != null) && (!((class_431)localObject4).a6().a4().b5(((class_796)localObject5).a7().a15())) ? 0 : 0) == 0) {
                  ((class_431)localObject1).a6().a4().b1("Cannot activate this block");
                }
              }
            }
          }
        }
      }
      else if (Keyboard.getEventKey() == class_367.field_736.a5())
      {
        localObject1 = this;
        if (((localObject4 = a47()) != null) && (((CollisionWorld.ClosestRayResultCallback)localObject4).hasHit()) && ((localObject4 instanceof CubeRayCastResult)))
        {
          if (((localObject5 = (CubeRayCastResult)localObject4).getSegment() != null) && ((((CubeRayCastResult)localObject5).getSegment().a15() instanceof EditableSendableSegmentController)))
          {
            localObject7 = ((CubeRayCastResult)localObject5).getSegment().a14(((CubeRayCastResult)localObject5).cubePos, new class_48());
            if ((((class_431)localObject1).jdField_field_4_of_type_Class_796 != null) && (((class_431)localObject1).jdField_field_4_of_type_Class_796.a2(new class_48()).equals(localObject7))) {
              ((class_431)localObject1).jdField_field_4_of_type_Class_796 = null;
            } else {
              try
              {
                ((class_431)localObject1).jdField_field_4_of_type_Class_796 = ((CubeRayCastResult)localObject5).getSegment().a15().getSegmentBuffer().a9((class_48)localObject7, true);
                System.err.println("External Selected " + ((class_431)localObject1).jdField_field_4_of_type_Class_796);
              }
              catch (CannotImmediateRequestOnClientException localCannotImmediateRequestOnClientException)
              {
                localCannotImmediateRequestOnClientException;
              }
            }
          }
          else
          {
            ((class_431)localObject1).jdField_field_4_of_type_Class_796 = null;
          }
        }
        else {
          ((class_431)localObject1).jdField_field_4_of_type_Class_796 = null;
        }
      }
      label2050:
      if (Keyboard.getEventKey() == class_367.field_737.a5())
      {
        localObject1 = this;
        if ((this.jdField_field_4_of_type_Class_796 != null) && ((localObject4 = ((class_431)localObject1).a47()) != null) && (((CollisionWorld.ClosestRayResultCallback)localObject4).hasHit()) && ((localObject4 instanceof CubeRayCastResult)) && ((localObject5 = (CubeRayCastResult)localObject4).getSegment() != null) && ((((CubeRayCastResult)localObject5).getSegment().a15() instanceof EditableSendableSegmentController)) && (((class_431)localObject1).a6().a4().a31((EditableSendableSegmentController)((CubeRayCastResult)localObject5).getSegment().a15())))
        {
          localObject7 = ((CubeRayCastResult)localObject5).getSegment().a14(((CubeRayCastResult)localObject5).cubePos, new class_48());
          try
          {
            ((class_431)localObject1).jdField_field_4_of_type_Class_796.a7().a15().setCurrentBlockController(((class_431)localObject1).jdField_field_4_of_type_Class_796, (class_48)localObject7);
            return;
          }
          catch (CannotBeControlledException localCannotBeControlledException)
          {
            ((class_431)localObject1).a81(localCannotBeControlledException);
          }
        }
        return;
      }
      if (Keyboard.isKeyDown(42))
      {
        localObject7 = null;
        a6().a14().field_4.jdField_field_4_of_type_Class_445.field_4.b();
        return;
      }
      if (Keyboard.getEventKey() == class_367.field_734.a5())
      {
        if (a6().a3().a146(false) != null)
        {
          a6().a4().b1("ERROR\n \nCannot spawn ship here.\nNo space!");
          return;
        }
        (localObject1 = new class_418(this, a6(), "New Ship", this.field_5, a6().getPlayerName() + "_" + System.currentTimeMillis())).a10(new class_419());
        synchronized (a6().b())
        {
          a6().b().add(localObject1);
          e2(true);
          return;
        }
      }
      if (Keyboard.getEventKey() == class_367.field_735.a5())
      {
        if (a6().a20().b5() < 1000000)
        {
          a6().a4().b1("You don't have enough money\nfor a Space Station \n(1000000)");
          return;
        }
        if (a6().a3().a146(false) != null)
        {
          a6().a4().b1("ERROR\n \nCannot spawn station here.\nNo space!");
          return;
        }
        class_420 localclass_420;
        (localclass_420 = new class_420(this, a6(), "New Space Station", this.field_5, a6().getPlayerName() + "_" + System.currentTimeMillis())).a10(new class_421());
        synchronized (a6().b())
        {
          a6().b().add(localclass_420);
          e2(true);
          return;
        }
      }
    }
  }
  
  public final void a12(class_939 paramclass_939)
  {
    if ((!this.field_4) && (!e1()) && (!paramclass_939.jdField_field_1163_of_type_Boolean)) {
      if (paramclass_939.jdField_field_1163_of_type_Int == 0) {
        f2(!class_949.field_1175.b1());
      } else if (paramclass_939.jdField_field_1163_of_type_Int == 1) {
        f2(class_949.field_1175.b1());
      }
    }
    super.a12(paramclass_939);
  }
  
  public final void a9(String paramString)
  {
    setChanged();
    notifyObservers(paramString);
  }
  
  public final void b2(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      if (a6().a3() == null)
      {
        c2(false);
        return;
      }
      this.jdField_field_4_of_type_Class_433.field_795 = false;
      this.jdField_field_4_of_type_Class_433.field_796 = false;
      this.jdField_field_4_of_type_Class_433.field_797 = false;
      class_459 localclass_459;
      (localclass_459 = a6().a14().field_4.jdField_field_4_of_type_Class_445.field_4.a50()).field_4.field_802 = 1;
      localclass_459.field_5.field_802 = 1;
      localclass_459.field_6.field_802 = 1;
      if (this.jdField_field_4_of_type_OrgSchemaSchineGraphicsengineCameraCamera == null) {
        this.jdField_field_4_of_type_OrgSchemaSchineGraphicsengineCameraCamera = new class_195(a6(), a6().a3());
      }
      if (((class_195)this.jdField_field_4_of_type_OrgSchemaSchineGraphicsengineCameraCamera).a80() != a6().a3()) {
        ((class_195)this.jdField_field_4_of_type_OrgSchemaSchineGraphicsengineCameraCamera).a82(a6().a3());
      }
      class_969.a9(this.jdField_field_4_of_type_OrgSchemaSchineGraphicsengineCameraCamera);
    }
    super.b2(paramBoolean);
  }
  
  public final synchronized void addObserver(Observer paramObserver)
  {
    super.addObserver(paramObserver);
  }
  
  public final void a15(class_941 paramclass_941)
  {
    class_1046.field_1306 = true;
    if (this.jdField_field_4_of_type_Class_796 != null)
    {
      this.jdField_field_4_of_type_Class_796.a12();
      if (this.jdField_field_4_of_type_Class_796.a9() == 0) {
        this.jdField_field_4_of_type_Class_796 = null;
      }
    }
    if (this.jdField_field_7_of_type_Boolean) {
      this.jdField_field_7_of_type_Boolean = false;
    }
    if (a6().a3() == null)
    {
      c2(false);
      a6().a14().field_4.jdField_field_4_of_type_Class_471.c2(true);
    }
  }
  
  public final void b()
  {
    this.jdField_field_7_of_type_Boolean = true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_431
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */