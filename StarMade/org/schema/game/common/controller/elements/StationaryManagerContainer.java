package org.schema.game.common.controller.elements;

import class_216;
import class_220;
import class_222;
import class_227;
import class_293;
import class_371;
import class_46;
import class_48;
import class_635;
import class_643;
import class_883;
import class_941;
import com.bulletphysics.linearmath.Transform;
import java.util.ArrayList;
import java.util.Collection;
import javax.vecmath.Vector3f;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.dockingBlock.DockingBlockManagerInterface;
import org.schema.game.common.controller.elements.dockingBlock.fixed.FixedDockingBlockElementManager;
import org.schema.game.common.controller.elements.dockingBlock.turret.TurretDockingBlockElementManager;
import org.schema.game.common.controller.elements.door.DoorCollectionManager;
import org.schema.game.common.controller.elements.lift.LiftCollectionManager;
import org.schema.game.common.controller.elements.missile.dumb.DumbMissileElementManager;
import org.schema.game.common.controller.elements.missile.fireandforget.FafoMissileElementManager;
import org.schema.game.common.controller.elements.missile.heatseeking.HeatMissileElementManager;
import org.schema.game.common.controller.elements.power.PowerCollectionManager;
import org.schema.game.common.controller.elements.powercap.PowerCapacityCollectionManager;
import org.schema.game.common.controller.elements.repair.RepairElementManager;
import org.schema.game.common.controller.elements.shield.ShieldCollectionManager;
import org.schema.game.common.controller.elements.weapon.WeaponElementManager;
import org.schema.game.network.objects.NetworkDoorInterface;
import org.schema.game.network.objects.NetworkLiftInterface;
import org.schema.schine.network.objects.remote.RemoteBuffer;
import org.schema.schine.network.objects.remote.RemoteVector4i;

public class StationaryManagerContainer
  extends ManagerContainer
  implements class_635, DoorContainerInterface, FactoryAddOnInterface, LiftContainerInterface, ShieldContainerInterface, WeaponManagerInterface, DockingBlockManagerInterface
{
  private ManagerModuleSingle shields;
  private ManagerModuleSingle power;
  private ManagerModuleSingle lift;
  private ManagerModuleSingle door;
  private ManagerModuleCollection repair;
  private ManagerModuleSingle powerCapacity;
  private ManagerModuleCollection weapon;
  private ManagerModuleCollection dumbMissile;
  private ManagerModuleCollection heatMissile;
  private ManagerModuleCollection fafoMissile;
  private ManagerModuleCollection turretDockingBlock;
  private ManagerModuleCollection fixedDockingBlock;
  private ManagerContainerFactoryAddOn factory;
  private final ArrayList initialPointDists = new ArrayList();
  private final PowerAddOn powerAddOn;
  
  public StationaryManagerContainer(SegmentController paramSegmentController)
  {
    super(paramSegmentController);
    this.powerAddOn = new PowerAddOn(this, paramSegmentController);
  }
  
  public Collection getDockingBlock()
  {
    return this.dockingModules;
  }
  
  private NetworkDoorInterface getDoorInterface()
  {
    return (NetworkDoorInterface)getSegmentController().getNetworkObject();
  }
  
  public DoorCollectionManager getDoorManager()
  {
    return (DoorCollectionManager)this.door.getCollectionManager();
  }
  
  private NetworkLiftInterface getLiftInterface()
  {
    return (NetworkLiftInterface)getSegmentController().getNetworkObject();
  }
  
  public LiftCollectionManager getLiftManager()
  {
    return (LiftCollectionManager)this.lift.getCollectionManager();
  }
  
  public PowerCollectionManager getPowerManager()
  {
    return (PowerCollectionManager)this.power.getCollectionManager();
  }
  
  public ManagerModuleCollection getRepair()
  {
    return this.repair;
  }
  
  public ShieldCollectionManager getShieldManager()
  {
    return (ShieldCollectionManager)this.shields.getCollectionManager();
  }
  
  public void handleClientRemoteDoor(class_48 paramclass_48)
  {
    getDoorInterface().getDoorActivate().forceClientUpdates();
    (paramclass_48 = new class_46(paramclass_48)).field_470 = -1;
    getDoorInterface().getDoorActivate().add(new RemoteVector4i(paramclass_48, getSegmentController().getNetworkObject()));
  }
  
  public void handleClientRemoteLift(class_48 paramclass_48)
  {
    getLiftInterface().getLiftActivate().forceClientUpdates();
    (paramclass_48 = new class_46(paramclass_48)).field_470 = 1;
    getLiftInterface().getLiftActivate().add(new RemoteVector4i(paramclass_48, getSegmentController().getNetworkObject()));
  }
  
  public void initialize()
  {
    getModules().add(this.lift = new ManagerModuleSingle(new VoidElementManager(new LiftCollectionManager(getSegmentController()), getSegmentController()), (short)0, (short)113));
    getModules().add(this.door = new ManagerModuleSingle(new VoidElementManager(new DoorCollectionManager(getSegmentController()), getSegmentController()), (short)0, (short)122));
    getModules().add(this.shields = new ManagerModuleSingle(new VoidElementManager(new ShieldCollectionManager(getSegmentController()), getSegmentController()), (short)0, (short)3));
    getModules().add(this.power = new ManagerModuleSingle(new VoidElementManager(new PowerCollectionManager(getSegmentController()), getSegmentController()), (short)0, (short)2));
    getModules().add(this.powerCapacity = new ManagerModuleSingle(new VoidElementManager(new PowerCapacityCollectionManager(getSegmentController()), getSegmentController()), (short)0, (short)331));
    getModules().add(this.turretDockingBlock = new ManagerModuleCollection(new TurretDockingBlockElementManager(getSegmentController()), (short)7, (short)88));
    getModules().add(this.fixedDockingBlock = new ManagerModuleCollection(new FixedDockingBlockElementManager(getSegmentController()), (short)289, (short)290));
    getModules().add(this.repair = new ManagerModuleCollection(new RepairElementManager(getSegmentController()), (short)39, (short)30));
    getModules().add(this.weapon = new ManagerModuleCollection(new WeaponElementManager(getSegmentController()), (short)6, (short)16));
    getModules().add(this.dumbMissile = new ManagerModuleCollection(new DumbMissileElementManager(getSegmentController()), (short)38, (short)32));
    getModules().add(this.fafoMissile = new ManagerModuleCollection(new FafoMissileElementManager(getSegmentController()), (short)54, (short)48));
    getModules().add(this.heatMissile = new ManagerModuleCollection(new HeatMissileElementManager(getSegmentController()), (short)46, (short)40));
    this.factory = new ManagerContainerFactoryAddOn();
    this.factory.initialize(getModules(), getSegmentController());
  }
  
  public void updateLocal(class_941 paramclass_941)
  {
    super.updateLocal(paramclass_941);
    this.factory.update(paramclass_941, getSegmentController().isOnServer());
    this.powerAddOn.update(paramclass_941);
  }
  
  public void onAction() {}
  
  public class_643 getInventoryNetworkObject()
  {
    return (class_643)getSegmentController().getNetworkObject();
  }
  
  public ManagerContainerFactoryAddOn getFactory()
  {
    return this.factory;
  }
  
  public double handleShieldHit(Vector3f paramVector3f, SegmentController paramSegmentController, float paramFloat)
  {
    double d1 = getShieldManager().getShields();
    onHit((int)paramFloat);
    double d2;
    if (getShieldManager().getShields() <= 0.0D)
    {
      if (paramFloat < d1) {
        d2 = d1;
      } else {
        d2 = paramFloat;
      }
    }
    else {
      d2 = 0.0D;
    }
    if (!getSegmentController().isOnServer())
    {
      paramSegmentController = (class_371)getState();
      Transform localTransform;
      (localTransform = new Transform()).setIdentity();
      localTransform.origin.set(paramVector3f);
      class_883.field_89.add(new class_216(localTransform, String.valueOf((int)paramFloat), 1.0F, 0.0F, 0.0F));
      paramSegmentController.a27().a91().a23(paramVector3f, 4.0F);
      if ((paramSegmentController = paramSegmentController.a27().a95().a3(getSegmentController())) != null) {
        paramSegmentController.a4(paramVector3f);
      }
    }
    return d2;
  }
  
  public ManagerModuleSingle getShields()
  {
    return this.shields;
  }
  
  public ManagerModuleSingle getPower()
  {
    return this.power;
  }
  
  public ManagerModuleSingle getLift()
  {
    return this.lift;
  }
  
  public ManagerModuleSingle getDoor()
  {
    return this.door;
  }
  
  public ManagerModuleCollection getWeapon()
  {
    return this.weapon;
  }
  
  public ManagerModuleCollection getDumbMissile()
  {
    return this.dumbMissile;
  }
  
  public ManagerModuleCollection getHeatMissile()
  {
    return this.heatMissile;
  }
  
  public ManagerModuleCollection getFafoMissile()
  {
    return this.fafoMissile;
  }
  
  public ManagerModuleCollection getTurretDockingBlock()
  {
    return this.turretDockingBlock;
  }
  
  public ManagerModuleCollection getFixedDockingBlock()
  {
    return this.fixedDockingBlock;
  }
  
  public ArrayList getInitialPointDists()
  {
    return this.initialPointDists;
  }
  
  public PowerAddOn getPowerAddOn()
  {
    return this.powerAddOn;
  }
  
  public PowerCapacityCollectionManager getPowerCapacityManager()
  {
    return (PowerCapacityCollectionManager)this.powerCapacity.getCollectionManager();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.StationaryManagerContainer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */