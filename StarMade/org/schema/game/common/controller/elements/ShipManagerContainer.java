package org.schema.game.common.controller.elements;

import class_216;
import class_220;
import class_222;
import class_227;
import class_293;
import class_371;
import class_46;
import class_48;
import class_643;
import class_747;
import class_748;
import class_755;
import class_774;
import class_796;
import class_883;
import class_933;
import class_941;
import com.bulletphysics.linearmath.Transform;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import javax.vecmath.Vector3f;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.cloaking.CloakingElementManager;
import org.schema.game.common.controller.elements.dockingBeam.DockingBeamElementManager;
import org.schema.game.common.controller.elements.dockingBlock.DockingBlockManagerInterface;
import org.schema.game.common.controller.elements.dockingBlock.fixed.FixedDockingBlockElementManager;
import org.schema.game.common.controller.elements.dockingBlock.turret.TurretDockingBlockElementManager;
import org.schema.game.common.controller.elements.door.DoorCollectionManager;
import org.schema.game.common.controller.elements.explosive.ExplosiveCollectionManager;
import org.schema.game.common.controller.elements.explosive.ExplosiveElementManager;
import org.schema.game.common.controller.elements.harvest.SalvageElementManager;
import org.schema.game.common.controller.elements.jamming.JammingCollectionManager;
import org.schema.game.common.controller.elements.jamming.JammingElementManager;
import org.schema.game.common.controller.elements.missile.dumb.DumbMissileElementManager;
import org.schema.game.common.controller.elements.missile.fireandforget.FafoMissileElementManager;
import org.schema.game.common.controller.elements.missile.heatseeking.HeatMissileElementManager;
import org.schema.game.common.controller.elements.power.PowerCollectionManager;
import org.schema.game.common.controller.elements.powerbeamdrain.PowerDrainElementManager;
import org.schema.game.common.controller.elements.powerbeamsupply.PowerSupplyElementManager;
import org.schema.game.common.controller.elements.powercap.PowerCapacityCollectionManager;
import org.schema.game.common.controller.elements.repair.RepairElementManager;
import org.schema.game.common.controller.elements.shield.ShieldCollectionManager;
import org.schema.game.common.controller.elements.thrust.ThrusterElementManager;
import org.schema.game.common.controller.elements.weapon.WeaponElementManager;
import org.schema.game.common.data.world.Segment;
import org.schema.game.network.objects.NetworkDoorInterface;
import org.schema.game.network.objects.NetworkShip;
import org.schema.schine.network.objects.remote.RemoteBoolean;
import org.schema.schine.network.objects.remote.RemoteBuffer;
import org.schema.schine.network.objects.remote.RemoteVector4i;

public class ShipManagerContainer
  extends ManagerContainer
  implements DoorContainerInterface, ManagerThrustInterface, ShieldContainerInterface, WeaponManagerInterface, DockingBlockManagerInterface
{
  private DockingBeamElementManager dockingBeam;
  private final PowerAddOn powerAddOn;
  private ManagerModuleSingle shields;
  private ManagerModuleSingle thrust;
  private ManagerModuleSingle cloaking;
  private ManagerModuleSingle explosive;
  private ManagerModuleSingle jamming;
  private ManagerModuleSingle power;
  private ManagerModuleSingle powerCapacity;
  private ManagerModuleSingle door;
  private ManagerModuleCollection salvage;
  private ManagerModuleCollection powerDrain;
  private ManagerModuleCollection powerSupply;
  private ManagerModuleCollection turretDockingBlock;
  private ManagerModuleCollection fixedDockingBlock;
  private ManagerModuleCollection repair;
  private ManagerModuleCollection weapon;
  private ManagerModuleCollection dumbMissile;
  private ManagerModuleCollection heatMissile;
  private ManagerModuleCollection fafoMissile;
  private final ArrayList cockpits = new ArrayList();
  
  public ManagerModuleSingle getDoor()
  {
    return this.door;
  }
  
  public ManagerModuleCollection getTurretDockingBlock()
  {
    return this.turretDockingBlock;
  }
  
  public ManagerModuleCollection getFixedDockingBlock()
  {
    return this.fixedDockingBlock;
  }
  
  public ShipManagerContainer(class_747 paramclass_747)
  {
    super(paramclass_747);
    this.powerAddOn = new PowerAddOn(this, paramclass_747);
  }
  
  public CloakingElementManager getCloakElementManager()
  {
    return (CloakingElementManager)this.cloaking.getElementManager();
  }
  
  public ManagerModuleSingle getCloaking()
  {
    return this.cloaking;
  }
  
  public ArrayList getCockpits()
  {
    return this.cockpits;
  }
  
  public DockingBeamElementManager getDockingBeam()
  {
    return this.dockingBeam;
  }
  
  public Collection getDockingBlock()
  {
    return this.dockingModules;
  }
  
  public ManagerModuleCollection getDumbMissile()
  {
    return this.dumbMissile;
  }
  
  public ManagerModuleSingle getExplosive()
  {
    return this.explosive;
  }
  
  public ExplosiveCollectionManager getExplosiveCollectionManager()
  {
    return (ExplosiveCollectionManager)this.explosive.getCollectionManager();
  }
  
  public ExplosiveElementManager getExplosiveElementManager()
  {
    return (ExplosiveElementManager)this.explosive.getElementManager();
  }
  
  public ManagerModuleCollection getFafoMissile()
  {
    return this.fafoMissile;
  }
  
  public ManagerModuleCollection getHeatMissile()
  {
    return this.heatMissile;
  }
  
  public ManagerModuleSingle getJamming()
  {
    return this.jamming;
  }
  
  public JammingElementManager getJammingElementManager()
  {
    return (JammingElementManager)this.jamming.getElementManager();
  }
  
  public ManagerModuleSingle getPower()
  {
    return this.power;
  }
  
  public PowerCollectionManager getPowerManager()
  {
    return (PowerCollectionManager)this.power.getCollectionManager();
  }
  
  public ManagerModuleCollection getRepair()
  {
    return this.repair;
  }
  
  public ManagerModuleCollection getSalvage()
  {
    return this.salvage;
  }
  
  public ShieldCollectionManager getShieldManager()
  {
    return (ShieldCollectionManager)this.shields.getCollectionManager();
  }
  
  public ManagerModuleSingle getShields()
  {
    return this.shields;
  }
  
  public ManagerModuleSingle getThrust()
  {
    return this.thrust;
  }
  
  public ThrusterElementManager getThrusterElementManager()
  {
    return (ThrusterElementManager)this.thrust.getElementManager();
  }
  
  public ManagerModuleCollection getWeapon()
  {
    return this.weapon;
  }
  
  public void handle(class_755 paramclass_755)
  {
    super.handle(paramclass_755);
    if (paramclass_755.field_1015.b2()) {
      this.dockingBeam.handle(paramclass_755);
    }
  }
  
  public void initialize()
  {
    this.dockingBeam = new DockingBeamElementManager(getSegmentController());
    this.modules.add(this.shields = new ManagerModuleSingle(new VoidElementManager(new ShieldCollectionManager(getSegmentController()), getSegmentController()), (short)0, (short)3));
    this.modules.add(this.thrust = new ManagerModuleSingle(new ThrusterElementManager(getSegmentController()), (short)1, (short)8));
    this.modules.add(this.door = new ManagerModuleSingle(new VoidElementManager(new DoorCollectionManager(getSegmentController()), getSegmentController()), (short)0, (short)122));
    this.modules.add(this.cloaking = new ManagerModuleSingle(new CloakingElementManager(getSegmentController()), (short)1, (short)22));
    this.modules.add(this.explosive = new ManagerModuleSingle(new ExplosiveElementManager(getSegmentController()), (short)1, (short)14));
    this.modules.add(this.jamming = new ManagerModuleSingle(new JammingElementManager(new JammingCollectionManager(getSegmentController()), getSegmentController()), (short)1, (short)15));
    this.modules.add(this.power = new ManagerModuleSingle(new VoidElementManager(new PowerCollectionManager(getSegmentController()), getSegmentController()), (short)0, (short)2));
    this.modules.add(this.powerCapacity = new ManagerModuleSingle(new VoidElementManager(new PowerCapacityCollectionManager(getSegmentController()), getSegmentController()), (short)0, (short)331));
    this.modules.add(this.salvage = new ManagerModuleCollection(new SalvageElementManager(getSegmentController()), (short)4, (short)24));
    this.modules.add(this.powerDrain = new ManagerModuleCollection(new PowerDrainElementManager(getSegmentController()), (short)332, (short)333));
    this.modules.add(this.powerSupply = new ManagerModuleCollection(new PowerSupplyElementManager(getSegmentController()), (short)334, (short)335));
    this.modules.add(this.turretDockingBlock = new ManagerModuleCollection(new TurretDockingBlockElementManager(getSegmentController()), (short)7, (short)88));
    this.modules.add(this.fixedDockingBlock = new ManagerModuleCollection(new FixedDockingBlockElementManager(getSegmentController()), (short)289, (short)290));
    this.modules.add(this.repair = new ManagerModuleCollection(new RepairElementManager(getSegmentController()), (short)39, (short)30));
    this.modules.add(this.weapon = new ManagerModuleCollection(new WeaponElementManager(getSegmentController()), (short)6, (short)16));
    this.modules.add(this.dumbMissile = new ManagerModuleCollection(new DumbMissileElementManager(getSegmentController()), (short)38, (short)32));
    this.modules.add(this.fafoMissile = new ManagerModuleCollection(new FafoMissileElementManager(getSegmentController()), (short)54, (short)48));
    this.modules.add(this.heatMissile = new ManagerModuleCollection(new HeatMissileElementManager(getSegmentController()), (short)46, (short)40));
  }
  
  public boolean isCloaked()
  {
    return getCloakElementManager().isCloaked();
  }
  
  public boolean isJamming()
  {
    return getJammingElementManager().isJamming();
  }
  
  public DoorCollectionManager getDoorManager()
  {
    return (DoorCollectionManager)this.door.getCollectionManager();
  }
  
  public void onAction()
  {
    ((CloakingElementManager)this.cloaking.getElementManager()).stopCloak();
  }
  
  public void onAddedElement(short paramShort, int paramInt, byte paramByte1, byte paramByte2, byte paramByte3, Segment paramSegment)
  {
    super.onAddedElement(paramShort, paramInt, paramByte1, paramByte2, paramByte3, paramSegment);
    switch (paramShort)
    {
    case 47: 
      paramShort = paramSegment.a13(paramByte1, paramByte2, paramByte3, new class_48());
      if (!getCockpits().contains(paramShort))
      {
        getCockpits().add(paramShort);
        return;
      }
      break;
    case 121: 
      ((class_747)getSegmentController()).a87().a133(new class_796(paramSegment, paramByte1, paramByte2, paramByte3));
    }
  }
  
  public void onHitNotice()
  {
    if (((class_747)getSegmentController()).isOnServer())
    {
      if (((isCloaked()) || (isJamming())) && (((class_747)getSegmentController()).a113().onHitNotices.isEmpty()))
      {
        ((CloakingElementManager)this.cloaking.getElementManager()).onHit();
        ((JammingElementManager)this.jamming.getElementManager()).onHit();
        ((class_747)getSegmentController()).a113().onHitNotices.add(new RemoteBoolean(true, ((class_747)getSegmentController()).a113()));
      }
    }
    else
    {
      ((CloakingElementManager)this.cloaking.getElementManager()).onHit();
      ((JammingElementManager)this.jamming.getElementManager()).onHit();
    }
  }
  
  public void onRemovedElement(short paramShort, int paramInt, byte paramByte1, byte paramByte2, byte paramByte3, Segment paramSegment, boolean paramBoolean)
  {
    super.onRemovedElement(paramShort, paramInt, paramByte1, paramByte2, paramByte3, paramSegment, paramBoolean);
    switch (paramShort)
    {
    case 47: 
      paramShort = paramSegment.a13(paramByte1, paramByte2, paramByte3, new class_48());
      getCockpits().remove(paramShort);
      return;
    case 121: 
      ((class_747)getSegmentController()).a87().a133(null);
    }
  }
  
  public void handleClientRemoteDoor(class_48 paramclass_48)
  {
    getDoorInterface().getDoorActivate().forceClientUpdates();
    (paramclass_48 = new class_46(paramclass_48)).field_470 = -1;
    getDoorInterface().getDoorActivate().add(new RemoteVector4i(paramclass_48, ((class_747)getSegmentController()).a113()));
  }
  
  private NetworkDoorInterface getDoorInterface()
  {
    return ((class_747)getSegmentController()).a113();
  }
  
  public void updateLocal(class_941 paramclass_941)
  {
    super.updateLocal(paramclass_941);
    this.dockingBeam.update(paramclass_941);
    this.powerAddOn.update(paramclass_941);
  }
  
  public class_643 getInventoryNetworkObject()
  {
    return ((class_747)getSegmentController()).a113();
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
    if ((!((class_747)getSegmentController()).isOnServer()) && (!class_933.a1()))
    {
      paramSegmentController = (class_371)getState();
      Transform localTransform;
      (localTransform = new Transform()).setIdentity();
      localTransform.origin.set(paramVector3f);
      class_883.field_89.add(new class_216(localTransform, String.valueOf((int)paramFloat), 1.0F, 0.0F, 0.0F));
      paramSegmentController.a27().a91().a23(paramVector3f, 4.0F);
      if ((paramSegmentController = paramSegmentController.a27().a95().a3(getSegmentController())) != null) {
        paramSegmentController.a4(paramVector3f);
      } else {
        System.err.println("[CLIENT] ERROR: shield drawer for " + getSegmentController() + " not initialized");
      }
    }
    return d2;
  }
  
  public PowerCapacityCollectionManager getPowerCapacityManager()
  {
    return (PowerCapacityCollectionManager)this.powerCapacity.getCollectionManager();
  }
  
  public PowerAddOn getPowerAddOn()
  {
    return this.powerAddOn;
  }
  
  public ManagerModuleCollection getPowerDrain()
  {
    return this.powerDrain;
  }
  
  public ManagerModuleCollection getPowerSupply()
  {
    return this.powerSupply;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.ShipManagerContainer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */