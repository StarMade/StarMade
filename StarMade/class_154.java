import it.unimi.dsi.fastutil.longs.LongSet;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import javax.vecmath.Vector3f;
import org.schema.game.client.view.gui.weapon.NoneElementException;
import org.schema.game.common.controller.CannotImmediateRequestOnClientException;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.ElementCollectionManager;
import org.schema.game.common.controller.elements.ManagerModuleCollection;
import org.schema.game.common.controller.elements.ManagerModuleSingle;
import org.schema.game.common.controller.elements.ShipManagerContainer;
import org.schema.game.common.controller.elements.cloaking.CloakingCollectionManager;
import org.schema.game.common.controller.elements.cloaking.CloakingElementManager;
import org.schema.game.common.controller.elements.cloaking.CloakingUnit;
import org.schema.game.common.controller.elements.explosive.ExplosiveCollectionManager;
import org.schema.game.common.controller.elements.harvest.SalvageBeamCollectionManager;
import org.schema.game.common.controller.elements.jamming.JammingCollectionManager;
import org.schema.game.common.controller.elements.jamming.JammingElementManager;
import org.schema.game.common.controller.elements.jamming.JammingUnit;
import org.schema.game.common.controller.elements.missile.dumb.DumbMissileCollectionManager;
import org.schema.game.common.controller.elements.missile.fireandforget.FafoMissileCollectionManager;
import org.schema.game.common.controller.elements.missile.heatseeking.HeatMissileCollectionManager;
import org.schema.game.common.controller.elements.repair.RepairBeamCollectionManager;
import org.schema.game.common.controller.elements.weapon.WeaponCollectionManager;
import org.schema.game.common.data.element.ControlElementMap;
import org.schema.game.common.data.element.ControlElementMapper;
import org.schema.game.common.data.element.ElementClassNotFoundException;
import org.schema.game.common.data.element.ElementCollection;
import org.schema.game.common.data.element.ElementDocking;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.game.common.data.world.Segment;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.graphicsengine.shader.ErrorDialogException;
import org.schema.schine.network.client.ClientState;

public class class_154
  extends class_1363
  implements Observer
{
  private class_972 jdField_field_89_of_type_Class_972;
  private class_968 jdField_field_89_of_type_Class_968;
  private class_964 jdField_field_89_of_type_Class_964;
  private boolean jdField_field_89_of_type_Boolean = true;
  private boolean jdField_field_90_of_type_Boolean;
  private class_930 jdField_field_89_of_type_Class_930;
  private class_150 jdField_field_89_of_type_Class_150;
  private Object jdField_field_90_of_type_JavaLangObject;
  private boolean field_92;
  
  public class_154(ClientState paramClientState)
  {
    super(paramClientState);
    paramClientState = this;
    this.jdField_field_89_of_type_Class_972 = new class_972(class_969.a2().a5("weapon-assign-panel-gui-"), paramClientState.a24());
    paramClientState.jdField_field_89_of_type_Class_968 = new class_968(244.0F, 360.0F, paramClientState.a24());
    paramClientState.jdField_field_89_of_type_Class_964 = new class_964(paramClientState.a24());
    paramClientState.jdField_field_89_of_type_Class_968.c7(paramClientState.jdField_field_89_of_type_Class_964);
    paramClientState.a9(paramClientState.jdField_field_89_of_type_Class_972);
    paramClientState.jdField_field_89_of_type_Class_972.a9(paramClientState.jdField_field_89_of_type_Class_968);
    paramClientState.jdField_field_89_of_type_Class_968.a161(254.0F, 96.0F, 0.0F);
    paramClientState.jdField_field_89_of_type_Class_964.a141(paramClientState.a40().jdField_field_4_of_type_Class_334);
    paramClientState.a40().jdField_field_4_of_type_Class_334.addObserver(paramClientState);
  }
  
  public final void a9(class_1363 paramclass_1363)
  {
    this.jdField_field_89_of_type_Class_972.a9(paramclass_1363);
  }
  
  public final void e()
  {
    try
    {
      if (this.field_92)
      {
        b7(null);
        this.field_92 = false;
        return;
      }
      if (this.jdField_field_90_of_type_Boolean) {
        try
        {
          a41(null);
          this.jdField_field_90_of_type_Boolean = false;
          return;
        }
        catch (ConcurrentModificationException localConcurrentModificationException)
        {
          localConcurrentModificationException.printStackTrace();
          this.jdField_field_90_of_type_Boolean = true;
          return;
        }
      }
      if (this.jdField_field_90_of_type_JavaLangObject != null)
      {
        if ((this.jdField_field_90_of_type_JavaLangObject instanceof class_796))
        {
          if ((localObject = (class_796)this.jdField_field_90_of_type_JavaLangObject).b() == -1) {
            a41((class_796)localObject);
          } else {
            b7((class_796)localObject);
          }
        }
        else {
          a41(null);
        }
        this.jdField_field_90_of_type_JavaLangObject = null;
        return;
      }
      if (a40().jdField_field_4_of_type_Class_334.h())
      {
        a41(a40().jdField_field_4_of_type_Class_334.a40());
        a40().jdField_field_4_of_type_Class_334.c1();
      }
      return;
    }
    catch (IOException localIOException)
    {
      (localObject = localIOException).printStackTrace();
      throw new ErrorDialogException(((IOException)localObject).getMessage());
    }
    catch (InterruptedException localInterruptedException)
    {
      Object localObject;
      (localObject = localInterruptedException).printStackTrace();
      throw new ErrorDialogException(((InterruptedException)localObject).getMessage());
    }
  }
  
  public final void a2() {}
  
  public final void b2(class_1363 paramclass_1363)
  {
    this.jdField_field_89_of_type_Class_972.b2(paramclass_1363);
  }
  
  public final void b()
  {
    if (this.jdField_field_89_of_type_Boolean) {
      c();
    }
    GlUtil.d1();
    r();
    this.jdField_field_89_of_type_Class_972.b();
    GlUtil.c2();
  }
  
  public final float a3()
  {
    return this.jdField_field_89_of_type_Class_972.a3();
  }
  
  private class_342 a39()
  {
    return a40().jdField_field_4_of_type_Class_443.a51();
  }
  
  private class_445 a40()
  {
    return ((class_371)a24()).a14().field_4.field_4;
  }
  
  public final float b1()
  {
    return this.jdField_field_89_of_type_Class_972.b1();
  }
  
  public final void c()
  {
    this.jdField_field_89_of_type_Class_972.c();
    this.jdField_field_89_of_type_Class_968.c();
    this.jdField_field_89_of_type_Class_930 = new class_930(243, 74, class_29.b2(), a24());
    this.jdField_field_89_of_type_Class_930.c();
    this.jdField_field_89_of_type_Class_930.a83().set(534.0F, 96.0F, 0.0F);
    this.jdField_field_89_of_type_Class_930.field_90 = new ArrayList();
    this.jdField_field_89_of_type_Class_930.field_90.add("Select a Weapon and press");
    this.jdField_field_89_of_type_Class_930.field_90.add("a number on you keyboard to");
    this.jdField_field_89_of_type_Class_930.field_90.add("assign this weapon to your sidebar");
    this.jdField_field_89_of_type_Class_972.a9(this.jdField_field_89_of_type_Class_930);
    this.jdField_field_89_of_type_Class_150 = new class_150(a24(), class_29.b2());
    this.jdField_field_89_of_type_Class_150.c();
    this.jdField_field_89_of_type_Class_150.a83().set(534.0F, 173.0F, 0.0F);
    this.jdField_field_89_of_type_Class_972.a9(this.jdField_field_89_of_type_Class_150);
    this.jdField_field_89_of_type_Boolean = false;
  }
  
  private void a41(class_796 paramclass_796)
  {
    if (a39().a40() != null)
    {
      class_747 localclass_747;
      if ((localclass_747 = ((class_371)a24()).a25()) == null) {
        return;
      }
      this.jdField_field_89_of_type_Class_964.clear();
      class_796 localclass_796;
      class_48 localclass_48 = (localclass_796 = a39().a40()).a2(new class_48());
      class_846 localclass_846 = localclass_747.getControlElementMap().getControlledElements((short)32767, localclass_48);
      if (!((class_371)a24()).a20().a131(localclass_747))
      {
        (localObject1 = new class_359(((class_371)a24()).a20(), localclass_747.getUniqueIdentifier())).a21((class_371)a24(), localclass_846);
        ((class_371)a24()).a20().a75().add(localObject1);
      }
      Object localObject1 = localclass_747.getControlElementMap().getControllingMap().keySet().iterator();
      Object localObject4;
      Object localObject2;
      Object localObject3;
      while (((Iterator)localObject1).hasNext())
      {
        long l = 0L;
        localObject4 = ElementCollection.getPosFromIndex(((Long)((Iterator)localObject1).next()).longValue(), new class_48());
        localObject2 = null;
        if (localclass_846.field_1094.contains(localObject4)) {
          if ((localObject2 = localclass_747.getSegmentBuffer().a9((class_48)localObject4, false)) != null) {
            try
            {
              localObject3 = new class_148((class_796)localObject2, a24());
              if (((class_796)localObject2).equals(paramclass_796)) {
                ((class_148)localObject3).a29(true);
              }
              this.jdField_field_89_of_type_Class_964.a144((class_959)localObject3);
            }
            catch (NoneElementException localNoneElementException1)
            {
              localNoneElementException1;
            }
          } else {
            this.jdField_field_90_of_type_Boolean = true;
          }
        }
      }
      if (localclass_48.equals(class_747.field_136))
      {
        try
        {
          localObject1 = new class_148(localclass_796, a24());
          if (localclass_796.equals(paramclass_796)) {
            ((class_148)localObject1).a29(true);
          }
          this.jdField_field_89_of_type_Class_964.a144((class_959)localObject1);
          if (!localclass_747.getDockingController().a5().isEmpty())
          {
            localObject2 = localclass_747.getDockingController().a5().iterator();
            while (((Iterator)localObject2).hasNext())
            {
              localObject3 = (ElementDocking)((Iterator)localObject2).next();
              localObject4 = new class_148(((ElementDocking)localObject3).field_1740, a24());
              if (((ElementDocking)localObject3).field_1740.equals(paramclass_796)) {
                ((class_148)localObject4).a29(true);
              }
              this.jdField_field_89_of_type_Class_964.a144((class_959)localObject4);
            }
          }
        }
        catch (ElementClassNotFoundException localElementClassNotFoundException)
        {
          localElementClassNotFoundException;
        }
        catch (NoneElementException localNoneElementException2)
        {
          localNoneElementException2;
        }
        if (!localclass_747.a112().getCloakElementManager().getCollection().getCollection().isEmpty()) {
          try
          {
            if (((localObject1 = ((CloakingUnit)localclass_747.a112().getCloakElementManager().getCollection().getCollection().get(0)).getId()) != null) && (((class_796)localObject1).a9() != 22)) {
              System.err.println("[CLIENT] Exception: WARNING Cloaking Element Manager contains wrong id: " + localObject1);
            } else if (localObject1 != null) {
              try
              {
                localObject2 = new class_148((class_796)localObject1, a24());
                if (localclass_796.equals(paramclass_796)) {
                  ((class_148)localObject2).a29(true);
                }
                this.jdField_field_89_of_type_Class_964.a144((class_959)localObject2);
              }
              catch (NoneElementException localNoneElementException3)
              {
                localObject2 = null;
                localNoneElementException3.printStackTrace();
              }
            } else {
              System.err.println("[CLIENT][ReconstructWeaponList] Exception: Cloaking manager id (seg piece) is null for " + localclass_747 + ", but cloaking manager has: " + localclass_747.a112().getCloakElementManager().getCollection().getCollection().size() + " elements");
            }
          }
          catch (CannotImmediateRequestOnClientException localCannotImmediateRequestOnClientException1)
          {
            System.err.println("[CLIENT][WeaponContolManager][WARNING] ship not yet loaded. canot check jamming ID");
          }
        }
        if (!localclass_747.a112().getJammingElementManager().getCollection().getCollection().isEmpty()) {
          try
          {
            if (((localObject1 = ((JammingUnit)localclass_747.a112().getJammingElementManager().getCollection().getCollection().get(0)).getId()) != null) && (((class_796)localObject1).a9() != 15))
            {
              System.err.println("[CLIENT] Exception: WARNING Jamming Element Manager contains wrong id: " + localObject1 + "; size " + ((JammingUnit)localclass_747.a112().getJammingElementManager().getCollection().getCollection().get(0)).getNeighboringCollection().size());
              if (!field_93) {
                throw new AssertionError();
              }
            }
            else if (localObject1 != null)
            {
              try
              {
                localObject2 = new class_148((class_796)localObject1, a24());
                if (localclass_796.equals(paramclass_796)) {
                  ((class_148)localObject2).a29(true);
                }
                this.jdField_field_89_of_type_Class_964.a144((class_959)localObject2);
              }
              catch (NoneElementException localNoneElementException4)
              {
                localObject2 = null;
                localNoneElementException4.printStackTrace();
              }
            }
            else
            {
              System.err.println("[CLIENT][ReconstructWeaponList] Exception: Jamming manager id (seg piece) is null for " + localclass_747 + ", but JammingManager has: " + localclass_747.a112().getJammingElementManager().getCollection().getCollection().size() + " elements");
            }
          }
          catch (CannotImmediateRequestOnClientException localCannotImmediateRequestOnClientException2)
          {
            System.err.println("[CLIENT][WeaponContolManager][WARNING] ship not yet loaded. canot check jamming ID");
          }
        }
      }
      this.jdField_field_89_of_type_Class_968.e();
      System.err.println("[CLIENT][WeaponContolManager] RECONTRUCTED LIST: " + this.jdField_field_89_of_type_Class_964.size());
      b7(paramclass_796);
    }
  }
  
  public void update(Observable paramObservable, Object paramObject)
  {
    this.jdField_field_90_of_type_JavaLangObject = paramObject;
    if (paramObject == null) {
      this.field_92 = true;
    }
  }
  
  public final void a12(class_941 paramclass_941)
  {
    this.jdField_field_89_of_type_Class_972.a12(paramclass_941);
  }
  
  private void b7(class_796 paramclass_796)
  {
    Object localObject1;
    if (paramclass_796 == null)
    {
      (localObject1 = this.jdField_field_89_of_type_Class_150).jdField_field_89_of_type_JavaUtilArrayList.set(0, "");
      ((class_150)localObject1).jdField_field_89_of_type_Class_968.c7(((class_150)localObject1).jdField_field_89_of_type_Class_930);
      return;
    }
    if (a39().a40() != null)
    {
      if ((localObject1 = ((class_371)a24()).a25()) == null) {
        return;
      }
      System.err.println("CURRENTLY SELECTED: " + paramclass_796);
      if ((paramclass_796 != null) && (paramclass_796.a9() != 0))
      {
        Object localObject3;
        if (paramclass_796.a9() == 6)
        {
          System.err.println("CURRENTLY WEAPON CONTROLLER SELECTED: " + paramclass_796 + "; collection size: " + ((class_747)localObject1).a112().getWeapon().getCollectionManagers().size());
          for (int i = 0; i < ((class_747)localObject1).a112().getWeapon().getCollectionManagers().size(); i++) {
            if ((localObject3 = (WeaponCollectionManager)((class_747)localObject1).a112().getWeapon().getCollectionManagers().get(i)).getControllerElement().equals(paramclass_796)) {
              this.jdField_field_89_of_type_Class_150.a21((ElementCollectionManager)localObject3);
            }
          }
        }
        Object localObject2;
        if (paramclass_796.a9() == 22)
        {
          localObject2 = (CloakingCollectionManager)((class_747)localObject1).a112().getCloaking().getCollectionManager();
          this.jdField_field_89_of_type_Class_150.a21((ElementCollectionManager)localObject2);
        }
        if (paramclass_796.a9() == 14)
        {
          localObject2 = (ExplosiveCollectionManager)((class_747)localObject1).a112().getExplosive().getCollectionManager();
          this.jdField_field_89_of_type_Class_150.a21((ElementCollectionManager)localObject2);
        }
        if (paramclass_796.a9() == 15)
        {
          localObject2 = (JammingCollectionManager)((class_747)localObject1).a112().getJamming().getCollectionManager();
          this.jdField_field_89_of_type_Class_150.a21((ElementCollectionManager)localObject2);
        }
        int j;
        if (paramclass_796.a9() == 4) {
          for (j = 0; j < ((class_747)localObject1).a112().getSalvage().getCollectionManagers().size(); j++) {
            if ((localObject3 = (SalvageBeamCollectionManager)((class_747)localObject1).a112().getSalvage().getCollectionManagers().get(j)).getControllerElement().equals(paramclass_796)) {
              this.jdField_field_89_of_type_Class_150.a21((ElementCollectionManager)localObject3);
            }
          }
        }
        if (paramclass_796.a9() == 39) {
          for (j = 0; j < ((class_747)localObject1).a112().getRepair().getCollectionManagers().size(); j++) {
            if ((localObject3 = (RepairBeamCollectionManager)((class_747)localObject1).a112().getRepair().getCollectionManagers().get(j)).getControllerElement().equals(paramclass_796)) {
              this.jdField_field_89_of_type_Class_150.a21((ElementCollectionManager)localObject3);
            }
          }
        }
        if (paramclass_796.a9() == 38) {
          for (j = 0; j < ((class_747)localObject1).a112().getDumbMissile().getCollectionManagers().size(); j++) {
            if ((localObject3 = (DumbMissileCollectionManager)((class_747)localObject1).a112().getDumbMissile().getCollectionManagers().get(j)).getControllerElement().equals(paramclass_796)) {
              this.jdField_field_89_of_type_Class_150.a21((ElementCollectionManager)localObject3);
            }
          }
        }
        if (paramclass_796.a9() == 46) {
          for (j = 0; j < ((class_747)localObject1).a112().getHeatMissile().getCollectionManagers().size(); j++) {
            if ((localObject3 = (HeatMissileCollectionManager)((class_747)localObject1).a112().getHeatMissile().getCollectionManagers().get(j)).getControllerElement().equals(paramclass_796)) {
              this.jdField_field_89_of_type_Class_150.a21((ElementCollectionManager)localObject3);
            }
          }
        }
        if (paramclass_796.a9() == 54) {
          for (j = 0; j < ((class_747)localObject1).a112().getFafoMissile().getCollectionManagers().size(); j++) {
            if ((localObject3 = (FafoMissileCollectionManager)((class_747)localObject1).a112().getFafoMissile().getCollectionManagers().get(j)).getControllerElement().equals(paramclass_796)) {
              this.jdField_field_89_of_type_Class_150.a21((ElementCollectionManager)localObject3);
            }
          }
        }
        if ((paramclass_796.a9() == 1) && (paramclass_796.a7().a15().getDockingController().a4() != null)) {
          this.jdField_field_89_of_type_Class_150.a17("DOCK");
        } else if (paramclass_796.a9() == 1) {
          this.jdField_field_89_of_type_Class_150.a17("CORE");
        }
        if (ElementKeyMap.getInfo(paramclass_796.a9()).isDockable())
        {
          Iterator localIterator = paramclass_796.a7().a15().getDockingController().a5().iterator();
          while (localIterator.hasNext()) {
            if ((localObject3 = (ElementDocking)localIterator.next()).field_1740.equals(paramclass_796))
            {
              localObject3 = ((ElementDocking)localObject3).from.a7().a15();
              localObject1 = this.jdField_field_89_of_type_Class_150;
              if ((localObject3 instanceof SegmentController))
              {
                StringBuffer localStringBuffer;
                (localStringBuffer = new StringBuffer()).append("Undock " + ((SegmentController)localObject3).toNiceString() + "\nfrom you by executing this!\n");
                ((class_150)localObject1).jdField_field_89_of_type_JavaUtilArrayList.set(0, localStringBuffer.toString());
                ((class_150)localObject1).jdField_field_89_of_type_Class_968.c7(((class_150)localObject1).jdField_field_89_of_type_Class_930);
              }
            }
          }
        }
      }
    }
  }
  
  public final void a21(ElementCollectionManager paramElementCollectionManager)
  {
    try
    {
      this.jdField_field_89_of_type_Class_150.a21(paramElementCollectionManager);
      return;
    }
    catch (ErrorDialogException localErrorDialogException)
    {
      localErrorDialogException;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_154
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */