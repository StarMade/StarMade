package org.schema.game.common.controller.elements;

import class_46;
import class_48;
import class_627;
import class_635;
import class_639;
import class_641;
import class_643;
import class_645;
import class_655;
import class_69;
import class_755;
import class_79;
import class_796;
import class_844;
import class_886;
import class_941;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayFIFOQueue;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.dockingBlock.DockingElementManagerInterface;
import org.schema.game.common.controller.elements.dockingBlock.DockingMetaDataDummy;
import org.schema.game.common.controller.elements.shield.ShieldCollectionManager;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.game.common.data.element.PointDistributionTagDummy;
import org.schema.game.common.data.world.Segment;
import org.schema.game.network.objects.remote.RemoteInventory;
import org.schema.game.network.objects.remote.RemoteInventoryBuffer;
import org.schema.game.network.objects.remote.RemoteInventoryMultMod;
import org.schema.game.network.objects.remote.RemoteInventoryMultModBuffer;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.Sendable;
import org.schema.schine.network.objects.remote.RemoteIntArray;
import org.schema.schine.network.objects.remote.RemoteIntArrayBuffer;

public abstract class ManagerContainer
  implements class_635
{
  protected final ArrayList modules = new ArrayList();
  protected final ArrayList handleModules = new ArrayList();
  private final ArrayList receiverModules = new ArrayList();
  private final ArrayList updatableModules = new ArrayList();
  private final ArrayList distributionReceiverModules = new ArrayList();
  private final ArrayList hittableModules = new ArrayList();
  private final ArrayList senderModules = new ArrayList();
  private final ArrayList changeListenModules = new ArrayList();
  private final ArrayList blockActivateListenModules = new ArrayList();
  private final HashMap modulesMap = new HashMap();
  private final HashMap modulesControllerMap = new HashMap();
  protected Collection dockingModules = new ArrayList();
  private final Set activeInventories = new HashSet();
  private final ArrayList delayedInventoryAdd = new ArrayList();
  private final ArrayList timedUpdateInterfaces = new ArrayList();
  private final ArrayList delayedInventoryRemove = new ArrayList();
  private final HashMap inventories = new HashMap();
  private final ArrayList initialPointDists = new ArrayList();
  private final ArrayList initialBlockMetaData = new ArrayList();
  private final ObjectArrayFIFOQueue ntInventoryMods = new ObjectArrayFIFOQueue();
  private final ObjectArrayFIFOQueue ntInventoryMultMods = new ObjectArrayFIFOQueue();
  private final ObjectArrayFIFOQueue ntInventorySingleMods = new ObjectArrayFIFOQueue();
  private final ObjectArrayFIFOQueue ntActiveInventorySingleMods = new ObjectArrayFIFOQueue();
  private final SegmentController segmentController;
  private boolean flagAnyBlockAdded;
  private boolean flagAnyBlockRemoved;
  public boolean loadInventoriesFromTag = true;
  private final ArrayList receivedDistributions = new ArrayList();
  
  public ManagerContainer(SegmentController paramSegmentController)
  {
    this.segmentController = paramSegmentController;
    initialize();
    for (paramSegmentController = 0; paramSegmentController < this.modules.size(); paramSegmentController++)
    {
      ManagerModule localManagerModule;
      Object localObject;
      if (((localManagerModule = (ManagerModule)this.modules.get(paramSegmentController)) instanceof ManagerModuleCollection))
      {
        localObject = (ManagerModuleCollection)localManagerModule;
        assert (!getModulesMap().containsKey(Short.valueOf(((ManagerModuleCollection)localObject).getControllerID()))) : (localObject + "; " + getModulesMap());
        getModulesMap().put(Short.valueOf(((ManagerModuleCollection)localObject).getControllerID()), localObject);
        assert (!getModulesControllerMap().containsKey(Short.valueOf(((ManagerModuleCollection)localObject).getControllerID()))) : (localObject + "; " + getModulesMap());
        getModulesControllerMap().put(Short.valueOf(((ManagerModuleCollection)localObject).getControllerID()), localObject);
        if ((((ManagerModuleCollection)localObject).getElementManager() instanceof TimedUpdateInterface)) {
          this.timedUpdateInterfaces.add((TimedUpdateInterface)((ManagerModuleCollection)localObject).getElementManager());
        }
        if ((((ManagerModuleCollection)localObject).getElementManager() instanceof BlockActivationListenerInterface)) {
          this.blockActivateListenModules.add((BlockActivationListenerInterface)((ManagerModuleCollection)localObject).getElementManager());
        }
      }
      if (getModulesMap().containsKey(Short.valueOf(localManagerModule.getElementID())))
      {
        for (localObject = (ManagerModule)getModulesMap().get(Short.valueOf(localManagerModule.getElementID())); ((ManagerModule)localObject).getNext() != null; localObject = ((ManagerModule)localObject).getNext()) {}
        ((ManagerModule)localObject).setNext(localManagerModule);
      }
      else
      {
        getModulesMap().put(Short.valueOf(localManagerModule.getElementID()), localManagerModule);
      }
      if ((localManagerModule instanceof ManagerModuleSingle))
      {
        localObject = ((ManagerModuleSingle)localManagerModule).getCollectionManager();
        addTypeModifiers(localObject);
      }
      if (!(localManagerModule.getElementManager() instanceof VoidElementManager)) {
        this.handleModules.add(localManagerModule);
      }
      if ((localManagerModule.getElementManager() instanceof DockingElementManagerInterface)) {
        this.dockingModules.add((ManagerModuleCollection)localManagerModule);
      }
      addTypeModifiers(localManagerModule.getElementManager());
    }
  }
  
  private void addInventory(byte paramByte1, byte paramByte2, byte paramByte3, Segment paramSegment, short paramShort)
  {
    if (getSegmentController().isOnServer())
    {
      paramByte1 = paramSegment.a13(paramByte1, paramByte2, paramByte3, new class_48());
      if ((!getInventories().containsKey(paramByte1)) && (!this.delayedInventoryAdd.contains(paramByte1)))
      {
        if (paramShort == 120)
        {
          this.delayedInventoryAdd.add(new class_655(this, paramByte1));
          return;
        }
        if (paramShort == 114)
        {
          this.delayedInventoryAdd.add(new class_641(this, paramByte1));
          return;
        }
        if (ElementKeyMap.getFactorykeyset().contains(Short.valueOf(paramShort))) {
          this.delayedInventoryAdd.add(new class_655(this, paramByte1));
        }
      }
    }
  }
  
  private void addTypeModifiers(Object paramObject)
  {
    if ((paramObject instanceof UpdatableCollectionManager)) {
      this.updatableModules.add((UpdatableCollectionManager)paramObject);
    }
    if ((paramObject instanceof NTReceiveInterface)) {
      this.receiverModules.add((NTReceiveInterface)paramObject);
    }
    if ((paramObject instanceof NTDistributionReceiverInterface)) {
      this.distributionReceiverModules.add((NTDistributionReceiverInterface)paramObject);
    }
    if ((paramObject instanceof BlockActivationListenerInterface)) {
      this.blockActivateListenModules.add((BlockActivationListenerInterface)paramObject);
    }
    if ((paramObject instanceof HittableInterface)) {
      this.hittableModules.add((HittableInterface)paramObject);
    }
    if ((paramObject instanceof NTSenderInterface)) {
      this.senderModules.add((NTSenderInterface)paramObject);
    }
    if ((paramObject instanceof ElementChangeListenerInterface)) {
      this.changeListenModules.add((ElementChangeListenerInterface)paramObject);
    }
  }
  
  private void announceInventory(class_48 paramclass_48, boolean paramBoolean1, class_639 paramclass_639, boolean paramBoolean2)
  {
    synchronized (getInventories())
    {
      if (paramBoolean2)
      {
        getInventories().put(paramclass_48, paramclass_639);
        paramclass_48 = new RemoteInventory(paramclass_639, this, paramBoolean2, getSegmentController().isOnServer());
        getInventoryInterface().getInventoriesChangeBuffer().add(paramclass_48);
      }
      else
      {
        System.err.println("ANNOUNEC INVENTORY REMOVE ON " + getSegmentController() + ": " + paramBoolean1);
        if ((paramclass_48 = (class_639)getInventories().remove(paramclass_48)) != null)
        {
          onInventoryRemove(paramclass_48, paramBoolean1);
          paramclass_48 = new RemoteInventory(paramclass_48, this, paramBoolean2, getSegmentController().isOnServer());
          getInventoryInterface().getInventoriesChangeBuffer().add(paramclass_48);
        }
      }
      return;
    }
  }
  
  public void clear()
  {
    for (int i = 0; i < this.modules.size(); i++) {
      ((ManagerModule)this.modules.get(i)).clear();
    }
  }
  
  private void fromTagDistribution(class_69 paramclass_69)
  {
    paramclass_69 = (class_69[])paramclass_69.a4();
    for (int i = 0; (i < paramclass_69.length) && (paramclass_69[i].a3() != class_79.field_548); i++)
    {
      class_69[] arrayOfclass_69 = (class_69[])paramclass_69[i].a4();
      assert (arrayOfclass_69 != null) : paramclass_69[i].a2();
      int j;
      Object localObject;
      if (("ElementCollection".equals(paramclass_69[i].a2())) || ("D".equals(paramclass_69[i].a2()))) {
        for (j = 0; (j < arrayOfclass_69.length) && (arrayOfclass_69[j].a3() != class_79.field_548); j++)
        {
          (localObject = new PointDistributionTagDummy()).fromTagStructure(arrayOfclass_69[j]);
          getInitialPointDists().add(localObject);
        }
      } else if ("A".equals(paramclass_69[i].a2())) {
        for (j = 0; (j < arrayOfclass_69.length) && (arrayOfclass_69[j].a3() != class_79.field_548); j++)
        {
          (localObject = new DockingMetaDataDummy()).fromTagStructure(arrayOfclass_69[j]);
          getInitialBlockMetaData().add(localObject);
        }
      }
    }
  }
  
  private void fromTagInventory(class_69 paramclass_69)
  {
    paramclass_69 = (class_69[])paramclass_69.a4();
    for (int i = 0; (i < paramclass_69.length) && (paramclass_69[i].a3() != class_79.field_548); i++)
    {
      class_69[] arrayOfclass_69;
      int j = ((Integer)(arrayOfclass_69 = (class_69[])paramclass_69[i].a4())[0].a4()).intValue();
      class_48 localclass_48 = (class_48)arrayOfclass_69[1].a4();
      Object localObject = null;
      if (j == 3) {
        localObject = new class_655(this, localclass_48);
      }
      if (j == 1) {
        localObject = new class_641(this, localclass_48);
      }
      assert (localObject != null) : ("unknown type: " + j);
      if (this.loadInventoriesFromTag) {
        ((class_639)localObject).fromTagStructure(arrayOfclass_69[2]);
      } else {
        System.err.println("[TAG] " + getSegmentController() + " not loading inventory");
      }
      getInventories().put(((class_639)localObject).a44(), localObject);
    }
  }
  
  public ArrayList getInitialPointDists()
  {
    return this.initialPointDists;
  }
  
  public class_639 getInventory(class_48 paramclass_48)
  {
    return (class_639)getInventories().get(paramclass_48);
  }
  
  private class_643 getInventoryInterface()
  {
    return (class_643)getSegmentController().getNetworkObject();
  }
  
  public String getName()
  {
    return this.segmentController.getUniqueIdentifier();
  }
  
  public SegmentController getSegmentController()
  {
    return this.segmentController;
  }
  
  public StateInterface getState()
  {
    return this.segmentController.getState();
  }
  
  public void handle(class_755 paramclass_755)
  {
    for (int i = 0; i < this.handleModules.size(); i++) {
      if (((ManagerModule)this.handleModules.get(i)).getElementManager().canHandle(paramclass_755)) {
        ((ManagerModule)this.handleModules.get(i)).getElementManager().handle(paramclass_755);
      }
    }
  }
  
  public void handleBlockActivate(class_796 paramclass_796, boolean paramBoolean)
  {
    System.err.println("BlockActivationListenerInterfaces: " + this.blockActivateListenModules.size() + " on " + getClass().getSimpleName());
    Iterator localIterator = this.blockActivateListenModules.iterator();
    while (localIterator.hasNext()) {
      ((BlockActivationListenerInterface)localIterator.next()).onActivate(paramclass_796, paramBoolean);
    }
  }
  
  public void onAction() {}
  
  public void handleInventoryFromNT(RemoteInventoryBuffer arg1)
  {
    ??? = ???.getReceiveBuffer();
    for (int i = 0; i < ???.size(); i++)
    {
      class_639 localclass_639 = (class_639)((RemoteInventory)???.get(i)).get();
      synchronized (this.ntInventoryMods)
      {
        this.ntInventoryMods.enqueue(localclass_639);
      }
    }
    ObjectArrayList localObjectArrayList1 = getInventoryInterface().getInventoryUpdateBuffer().getReceiveBuffer();
    for (int j = 0; j < localObjectArrayList1.size(); j++)
    {
      ??? = (RemoteIntArray)localObjectArrayList1.get(j);
      synchronized (this.ntInventorySingleMods)
      {
        this.ntInventorySingleMods.enqueue(???);
      }
    }
    ObjectArrayList localObjectArrayList2 = getInventoryInterface().getInventoryMultModBuffer().getReceiveBuffer();
    for (j = 0; j < localObjectArrayList2.size(); j++)
    {
      ??? = (RemoteInventoryMultMod)localObjectArrayList2.get(j);
      synchronized (this.ntInventoryMultMods)
      {
        this.ntInventoryMultMods.enqueue(((RemoteInventoryMultMod)???).get());
      }
    }
    ObjectArrayList localObjectArrayList3 = getInventoryInterface().getInventoryActivateBuffer().getReceiveBuffer();
    for (j = 0; j < localObjectArrayList3.size(); j++)
    {
      ??? = (RemoteIntArray)localObjectArrayList3.get(j);
      synchronized (this.ntActiveInventorySingleMods)
      {
        this.ntActiveInventorySingleMods.enqueue(???);
      }
    }
  }
  
  protected abstract void initialize();
  
  public void onAddedElement(short paramShort, int paramInt, byte paramByte1, byte paramByte2, byte paramByte3, Segment paramSegment)
  {
    if (ElementKeyMap.getFactorykeyset().contains(Short.valueOf(paramShort))) {
      addInventory(paramByte1, paramByte2, paramByte3, paramSegment, paramShort);
    }
    if ((paramInt = (ManagerModuleCollection)getModulesControllerMap().get(Short.valueOf(paramShort))) != null)
    {
      paramInt.addControllerBlock(paramByte1, paramByte2, paramByte3, paramSegment);
    }
    else if (((paramInt = (ManagerModule)getModulesMap().get(Short.valueOf(paramShort))) != null) && ((paramInt instanceof ManagerModuleSingle)))
    {
      ((ManagerModuleSingle)paramInt).addElement(paramByte1, paramByte2, paramByte3, paramSegment);
    }
    else
    {
      assert (paramShort != 2);
      switch (paramShort)
      {
      case 120: 
        addInventory(paramByte1, paramByte2, paramByte3, paramSegment, paramShort);
        break;
      case 114: 
        addInventory(paramByte1, paramByte2, paramByte3, paramSegment, paramShort);
      }
    }
    this.flagAnyBlockAdded = true;
  }
  
  public void onControllerAdded(class_48 paramclass_481, class_48 paramclass_482, short paramShort)
  {
    ManagerModule localManagerModule = (ManagerModule)getModulesMap().get(Short.valueOf(paramShort));
    assert (localManagerModule != null) : ("critical: no module found for " + getSegmentController() + ": " + paramShort + "; " + getModulesMap());
    if (localManagerModule.getNext() != null)
    {
      int i = getSegmentController().getSegmentBuffer().a9(paramclass_481, true).a9();
      while (localManagerModule.getNext() != null)
      {
        assert ((localManagerModule instanceof ManagerModuleCollection));
        if (((ManagerModuleCollection)localManagerModule).getControllerID() == i) {
          break;
        }
        localManagerModule = localManagerModule.getNext();
      }
    }
    assert (localManagerModule != null) : (paramShort + " -> " + ElementKeyMap.getInfo(paramShort).getName() + ": " + getModulesMap());
    if (localManagerModule == null) {
      throw new NullPointerException("Could not find Manager Module for " + paramShort + ": " + getModulesMap());
    }
    localManagerModule.addControlledBlock(paramclass_481, paramclass_482, paramShort);
  }
  
  public void onControllerRemoved(class_48 paramclass_481, class_48 paramclass_482, short paramShort)
  {
    assert (getModulesMap() != null);
    assert (paramShort != 0);
    assert (getModulesMap().containsKey(Short.valueOf(paramShort)));
    if ((paramShort == 0) || (!getModulesMap().containsKey(Short.valueOf(paramShort))))
    {
      System.err.println("Exception: tried to remove controller: " + paramShort + ": " + getModulesMap() + " for " + getSegmentController());
      return;
    }
    ManagerModule localManagerModule;
    if ((localManagerModule = (ManagerModule)getModulesMap().get(Short.valueOf(paramShort))).getNext() != null)
    {
      int i = getSegmentController().getSegmentBuffer().a9(paramclass_481, true).a9();
      while (localManagerModule.getNext() != null)
      {
        assert ((localManagerModule instanceof ManagerModuleCollection));
        if (((ManagerModuleCollection)localManagerModule).getControllerID() == i) {
          break;
        }
        localManagerModule = localManagerModule.getNext();
      }
    }
    assert (localManagerModule != null) : (paramShort + " -> " + ElementKeyMap.getInfo(paramShort).getName() + ": " + getModulesMap());
    localManagerModule.removeControllerBlock(paramclass_481, paramclass_482, paramShort);
  }
  
  public void onHit(int paramInt)
  {
    for (int i = 0; i < this.hittableModules.size(); i++) {
      ((HittableInterface)this.hittableModules.get(i)).onHit(paramInt);
    }
  }
  
  public void onHitNotice() {}
  
  private void onInventoryRemove(class_639 paramclass_639, boolean paramBoolean)
  {
    if ((!paramBoolean) && (this.segmentController.isOnServer()))
    {
      System.err.println("[MANAGERCONTAINER] REMOVING INVENTORY! (now spawning in space)" + getSegmentController().getState() + " - " + getSegmentController());
      paramclass_639.a55(getSegmentController());
    }
  }
  
  public void onRemovedElement(short paramShort, int paramInt, byte paramByte1, byte paramByte2, byte paramByte3, Segment paramSegment, boolean paramBoolean)
  {
    if (ElementKeyMap.getFactorykeyset().contains(Short.valueOf(paramShort))) {
      removeInventory(paramByte1, paramByte2, paramByte3, paramSegment, paramBoolean);
    }
    if ((paramInt = (ManagerModuleCollection)getModulesControllerMap().get(Short.valueOf(paramShort))) != null) {
      paramInt.removeController(paramByte1, paramByte2, paramByte3, paramSegment);
    } else if (((paramInt = (ManagerModule)getModulesMap().get(Short.valueOf(paramShort))) != null) && ((paramInt instanceof ManagerModuleSingle))) {
      ((ManagerModuleSingle)paramInt).removeElement(paramByte1, paramByte2, paramByte3, paramSegment);
    } else {
      switch (paramShort)
      {
      case 120: 
        System.err.println("[MANAGER_CONTAINER] onRemovedElement REMOVING INVENTORY!! " + getSegmentController() + "; " + getSegmentController().getState() + " preserveC: " + paramBoolean);
      case 114: 
        removeInventory(paramByte1, paramByte2, paramByte3, paramSegment, paramBoolean);
      }
    }
    this.flagAnyBlockRemoved = true;
  }
  
  public String printInventories()
  {
    return getInventories().toString();
  }
  
  private void removeInventory(byte paramByte1, byte paramByte2, byte paramByte3, Segment paramSegment, boolean paramBoolean)
  {
    if (getSegmentController().isOnServer())
    {
      if (!paramBoolean)
      {
        paramByte1 = paramSegment.a13(paramByte1, paramByte2, paramByte3, new class_48());
        if ((getInventories().containsKey(paramByte1)) && (!this.delayedInventoryRemove.contains(paramByte1))) {
          this.delayedInventoryRemove.add(new class_46(paramByte1, paramBoolean ? 1 : 0));
        }
        return;
      }
      System.err.println("[SERVER] keeping inventory for now so it's not emptied by anything " + getSegmentController());
    }
  }
  
  public void sendInventoryModification(int paramInt, class_48 paramclass_48)
  {
    RemoteIntArray localRemoteIntArray;
    (localRemoteIntArray = new RemoteIntArray(6, getSegmentController().getNetworkObject())).set(0, paramInt);
    class_639 localclass_639 = getInventory(paramclass_48);
    assert (localclass_639 != null) : (paramclass_48 + " --- " + getInventories());
    localRemoteIntArray.set(1, localclass_639.a45(paramInt));
    localRemoteIntArray.set(2, localclass_639.a41(paramInt));
    localRemoteIntArray.set(3, paramclass_48.field_475);
    localRemoteIntArray.set(4, paramclass_48.field_476);
    localRemoteIntArray.set(5, paramclass_48.field_477);
    getInventoryInterface().getInventoryUpdateBuffer().add(localRemoteIntArray);
    try
    {
      if (getInventoryInterface().getInventoryUpdateBuffer().size() > 200)
      {
        paramInt = (Sendable)getSegmentController().getState().getLocalAndRemoteObjectContainer().getLocalObjects().get(getSegmentController().getId());
        paramclass_48 = (NetworkObject)getSegmentController().getState().getLocalAndRemoteObjectContainer().getRemoteObjects().get(getSegmentController().getId());
        throw new IllegalArgumentException("WARNING: inventory high of " + getSegmentController() + ": " + getInventoryInterface().getInventoryUpdateBuffer().size() + "; " + paramInt + "; " + paramclass_48);
      }
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      localIllegalArgumentException;
    }
  }
  
  public void sendInventoryModification(Collection paramCollection, class_48 paramclass_48)
  {
    class_639 localclass_639;
    if ((localclass_639 = getInventory(paramclass_48)) != null)
    {
      paramCollection = new class_645(paramCollection, localclass_639, paramclass_48);
      getInventoryInterface().getInventoryMultModBuffer().add(new RemoteInventoryMultMod(paramCollection, getSegmentController().getNetworkObject()));
      return;
    }
    try
    {
      throw new IllegalArgumentException("[INVENTORY] Exception: tried to send inventory " + paramclass_48);
    }
    catch (Exception localException)
    {
      localException;
    }
  }
  
  public class_69 toTagStructure()
  {
    Object localObject2;
    Object localObject3;
    synchronized (getInventories())
    {
      class_69[] arrayOfclass_691 = new class_69[getInventories().size() + 1];
      int i = 0;
      localObject1 = getInventories().entrySet().iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (Map.Entry)((Iterator)localObject1).next();
        (localObject3 = new class_69[4])[0] = new class_69(class_79.field_551, "type", Integer.valueOf(((class_639)((Map.Entry)localObject2).getValue()).c2()));
        localObject3[1] = new class_69(class_79.field_558, "index", ((Map.Entry)localObject2).getKey());
        localObject3[2] = ((class_639)((Map.Entry)localObject2).getValue()).toTagStructure();
        localObject3[3] = new class_69(class_79.field_548, "fin", null);
        arrayOfclass_691[i] = new class_69(class_79.field_561, "inventory", (class_69[])localObject3);
        i++;
      }
      arrayOfclass_691[getInventories().size()] = new class_69(class_79.field_548, "fin", null);
    }
    ??? = new class_69(class_79.field_561, "controllerStructure", arrayOfclass_692);
    ArrayList localArrayList = new ArrayList();
    Object localObject1 = this.modules.iterator();
    while (((Iterator)localObject1).hasNext()) {
      if (((localObject2 = (ManagerModule)((Iterator)localObject1).next()).getElementManager() instanceof UsableDistributionControllableElementManager))
      {
        localObject3 = (UsableDistributionControllableElementManager)((ManagerModule)localObject2).getElementManager();
        localArrayList.add(((UsableDistributionControllableElementManager)localObject3).toDistributionTagStructure());
      }
    }
    localObject1 = this.modules.iterator();
    while (((Iterator)localObject1).hasNext()) {
      if ((((localObject2 = (ManagerModule)((Iterator)localObject1).next()).getElementManager() instanceof UsableControllableElementManager)) && ((localObject3 = (UsableControllableElementManager)((ManagerModule)localObject2).getElementManager()).hasMetaData())) {
        localArrayList.add(((UsableControllableElementManager)localObject3).toTagStructure());
      }
    }
    if (localArrayList.size() > 0)
    {
      localObject2 = new class_69[localArrayList.size() + 1];
      for (int j = 0; j < localArrayList.size(); j++) {
        localObject2[j] = ((class_69)localArrayList.get(j));
      }
      localObject2[(localObject2.length - 1)] = new class_69(class_79.field_548, null, null);
      localObject1 = new class_69(class_79.field_561, "shipMan0", (class_69[])localObject2);
    }
    else
    {
      localObject1 = new class_69(class_79.field_551, "shipMan0", Integer.valueOf(0));
    }
    if ((this instanceof PowerManagerInterface)) {
      localObject2 = ((PowerManagerInterface)this).getPowerAddOn().toTagStructure();
    } else {
      localObject2 = new class_69(class_79.field_549, null, Integer.valueOf(0));
    }
    class_69 localclass_69;
    if ((this instanceof ShieldContainerInterface)) {
      localclass_69 = new class_69(class_79.field_554, "sh", Double.valueOf(((ShieldContainerInterface)this).getShieldManager().getShields()));
    } else {
      localclass_69 = new class_69(class_79.field_549, null, Integer.valueOf(0));
    }
    return new class_69(class_79.field_561, "container", new class_69[] { ???, localObject1, localObject2, localclass_69, new class_69(class_79.field_548, null, null) });
  }
  
  public void fromTagStructure(class_69 paramclass_69)
  {
    if (paramclass_69.a2().equals("container"))
    {
      paramclass_69 = (class_69[])paramclass_69.a4();
      fromTagInventory(paramclass_69[0]);
      if (paramclass_69[1].a3() == class_79.field_561)
      {
        class_69[] arrayOfclass_69 = (class_69[])paramclass_69[1].a4();
        for (int j = 0; j < arrayOfclass_69.length; j++) {
          if (arrayOfclass_69[j].a3() != class_79.field_548) {
            fromTagDistribution(arrayOfclass_69[j]);
          }
        }
      }
      if (((this instanceof PowerManagerInterface)) && (paramclass_69.length > 2) && ("pw".equals(paramclass_69[2].a2()))) {
        ((PowerManagerInterface)this).getPowerAddOn().fromTagStructure(paramclass_69[2]);
      }
      if (((this instanceof ShieldContainerInterface)) && (paramclass_69.length > 3) && ("sh".equals(paramclass_69[3].a2()))) {
        ((ShieldContainerInterface)this).getShieldManager().setInitialShields(((Double)paramclass_69[3].a4()).doubleValue());
      }
      return;
    }
    if (paramclass_69.a2().equals("controllerStructure"))
    {
      handleTag(paramclass_69);
      return;
    }
    paramclass_69 = (class_69[])paramclass_69.a4();
    for (int i = 0; i < paramclass_69.length; i++) {
      if (paramclass_69[i].a3() != class_79.field_548) {
        handleTag(paramclass_69[i]);
      }
    }
  }
  
  private void handleTag(class_69 paramclass_69)
  {
    if (paramclass_69.a2().equals("wepContr"))
    {
      fromTagDistribution(paramclass_69);
      return;
    }
    if (paramclass_69.a2().equals("controllerStructure"))
    {
      fromTagInventory(paramclass_69);
      return;
    }
    if (!$assertionsDisabled) {
      throw new AssertionError(paramclass_69.a2());
    }
  }
  
  public void updateFromNetworkObject(NetworkObject paramNetworkObject)
  {
    for (int i = 0; i < this.receiverModules.size(); i++) {
      ((NTReceiveInterface)this.receiverModules.get(i)).updateFromNT(getSegmentController().getNetworkObject());
    }
    if ((paramNetworkObject instanceof DistributionInterface))
    {
      RemoteIntArray localRemoteIntArray = null;
      paramNetworkObject = ((DistributionInterface)paramNetworkObject).getDistributionModification().getReceiveBuffer().iterator();
      while (paramNetworkObject.hasNext())
      {
        localRemoteIntArray = (RemoteIntArray)paramNetworkObject.next();
        class_48 localclass_481 = new class_48(localRemoteIntArray.getIntArray()[0], localRemoteIntArray.getIntArray()[1], localRemoteIntArray.getIntArray()[2]);
        class_48 localclass_482 = new class_48(localRemoteIntArray.getIntArray()[3], localRemoteIntArray.getIntArray()[4], localRemoteIntArray.getIntArray()[5]);
        synchronized (this.receivedDistributions)
        {
          this.receivedDistributions.add(new class_844(localclass_481, localclass_482, localRemoteIntArray.getIntArray()[6], localRemoteIntArray.getIntArray()[7], localRemoteIntArray.getIntArray()[8]));
        }
      }
    }
    handleInventoryFromNT(getInventoryInterface().getInventoriesChangeBuffer());
  }
  
  public void updateLocal(class_941 paramclass_941)
  {
    Object localObject1;
    if (!this.ntInventoryMods.isEmpty()) {
      synchronized (this.ntInventoryMods)
      {
        while (!this.ntInventoryMods.isEmpty())
        {
          localObject1 = (class_639)this.ntInventoryMods.dequeue();
          getInventories().put(((class_639)localObject1).a44(), localObject1);
          System.err.println("ADDED INVENTORY: " + ((class_639)localObject1).a44() + " on " + getSegmentController().getState() + ": " + localObject1);
        }
      }
    }
    Object localObject4;
    Object localObject5;
    Object localObject6;
    if (!this.ntInventorySingleMods.isEmpty()) {
      synchronized (this.ntInventorySingleMods)
      {
        localObject1 = new ArrayList();
        while (!this.ntInventorySingleMods.isEmpty())
        {
          localObject4 = (RemoteIntArray)this.ntInventorySingleMods.dequeue();
          localObject5 = new class_48(localObject4.getIntArray()[3], localObject4.getIntArray()[4], localObject4.getIntArray()[5]);
          if ((localObject6 = getInventory((class_48)localObject5)) != null)
          {
            ((class_639)localObject6).a57((RemoteIntArray)localObject4, getInventoryInterface());
          }
          else
          {
            if (!this.segmentController.isOnServer()) {
              ((ArrayList)localObject1).add(localObject4);
            }
            System.err.println("[MANAGERCONTAINER] Exc: NOT FOUND INVENTORY (received mod): " + getSegmentController() + " - " + localObject5 + "; " + getInventories().keySet());
          }
        }
        if (!((ArrayList)localObject1).isEmpty()) {
          while (!((ArrayList)localObject1).isEmpty()) {
            this.ntInventorySingleMods.enqueue(((ArrayList)localObject1).remove(0));
          }
        }
      }
    }
    if (!this.ntInventoryMultMods.isEmpty()) {
      synchronized (this.ntInventoryMultMods)
      {
        localObject1 = new ArrayList();
        while (!this.ntInventoryMultMods.isEmpty())
        {
          localObject4 = (class_645)this.ntInventoryMultMods.dequeue();
          if ((localObject5 = getInventory(((class_645)localObject4).field_920)) != null)
          {
            getInventoryInterface();
            ((class_639)localObject5).a58((class_645)localObject4);
          }
          else
          {
            if (!this.segmentController.isOnServer()) {
              ((ArrayList)localObject1).add(localObject4);
            }
            System.err.println("[MANAGERCONTAINER] Exc: NOT FOUND MULT INVENTORY (received mod): " + getSegmentController() + " - " + ((class_645)localObject4).field_920 + "; " + getInventories().keySet());
          }
        }
        if (!((ArrayList)localObject1).isEmpty()) {
          while (!((ArrayList)localObject1).isEmpty()) {
            this.ntInventoryMultMods.enqueue(((ArrayList)localObject1).remove(0));
          }
        }
      }
    }
    if (!this.ntActiveInventorySingleMods.isEmpty()) {
      synchronized (this.ntActiveInventorySingleMods)
      {
        ArrayList localArrayList = new ArrayList();
        while (!this.ntActiveInventorySingleMods.isEmpty())
        {
          localObject4 = (RemoteIntArray)this.ntActiveInventorySingleMods.dequeue();
          localObject5 = new class_48(localObject4.getIntArray()[0], localObject4.getIntArray()[1], localObject4.getIntArray()[2]);
          localObject6 = getInventory((class_48)localObject5);
          System.err.println("[INVENTORY] HANDLE RECEIVED ACTIVATE: " + localObject5 + ": " + localObject6);
          assert (localObject6 != null);
          if ((localObject6 != null) && ((localObject6 instanceof class_627)))
          {
            ((class_627)localObject6).b4();
          }
          else
          {
            localArrayList.add(localObject4);
            System.err.println("Exception: NOT FOUND active INVENTORY: " + getSegmentController() + " - " + localObject5);
          }
        }
        if (!localArrayList.isEmpty()) {
          while (!localArrayList.isEmpty()) {
            this.ntActiveInventorySingleMods.enqueue(localArrayList.remove(0));
          }
        }
      }
    }
    long l = System.currentTimeMillis();
    for (int j = 0; j < this.modules.size(); j++) {
      ((ManagerModule)this.modules.get(j)).update(paramclass_941, l);
    }
    for (j = 0; j < this.updatableModules.size(); j++) {
      ((UpdatableCollectionManager)this.updatableModules.get(j)).update(paramclass_941);
    }
    j = 0;
    if (!this.receivedDistributions.isEmpty()) {
      synchronized (this.receivedDistributions)
      {
        localObject6 = (class_844)this.receivedDistributions.remove(0);
        paramclass_941 = 0;
        for (int i = 0; i < this.distributionReceiverModules.size(); i++) {
          if (((NTDistributionReceiverInterface)this.distributionReceiverModules.get(i)).receiveDistribution((class_844)localObject6, getSegmentController().getNetworkObject())) {
            paramclass_941 = 1;
          }
        }
        if ((paramclass_941 == 0) && (!getSegmentController().isOnServer()))
        {
          PointDistributionTagDummy localPointDistributionTagDummy = new PointDistributionTagDummy((class_844)localObject6);
          getInitialPointDists().add(localPointDistributionTagDummy);
        }
      }
    }
    while (!this.delayedInventoryAdd.isEmpty())
    {
      ??? = (class_639)this.delayedInventoryAdd.remove(0);
      if (!getInventories().containsKey(((class_639)???).a44()))
      {
        if (getSegmentController().isOnServer()) {
          System.err.println("[SERVER] " + getSegmentController() + " ADDING NEW INVENTORY " + ((class_639)???).a44());
        }
        announceInventory(((class_639)???).a44(), false, (class_639)???, true);
        j = 1;
      }
    }
    while (!this.delayedInventoryRemove.isEmpty())
    {
      ??? = (class_46)this.delayedInventoryRemove.remove(0);
      announceInventory(new class_48(((class_46)???).field_467, ((class_46)???).field_468, ((class_46)???).field_469), ((class_46)???).field_470 == 1, null, false);
      j = 1;
    }
    if (j != 0)
    {
      this.activeInventories.clear();
      ??? = getInventories().values().iterator();
      while (((Iterator)???).hasNext()) {
        if (((localObject6 = (class_639)((Iterator)???).next()) instanceof class_627)) {
          this.activeInventories.add((class_627)localObject6);
        }
      }
    }
    else if (getSegmentController().isOnServer())
    {
      ??? = this.activeInventories.iterator();
      while (((Iterator)???).hasNext()) {
        if ((localObject6 = (class_627)((Iterator)???).next()).a7()) {
          ((class_627)localObject6).a13();
        }
      }
    }
    int k;
    if (this.flagAnyBlockAdded)
    {
      for (k = 0; k < this.changeListenModules.size(); k++) {
        ((ElementChangeListenerInterface)this.changeListenModules.get(k)).onAddedAnyElement();
      }
      this.flagAnyBlockAdded = false;
    }
    if (this.flagAnyBlockRemoved)
    {
      for (k = 0; k < this.changeListenModules.size(); k++) {
        ((ElementChangeListenerInterface)this.changeListenModules.get(k)).onRemovedAnyElement();
      }
      this.flagAnyBlockRemoved = false;
    }
  }
  
  public void updateToFullNetworkObject(NetworkObject paramNetworkObject)
  {
    for (int i = 0; i < this.senderModules.size(); i++) {
      ((NTSenderInterface)this.senderModules.get(i)).updateToFullNT(paramNetworkObject);
    }
  }
  
  public HashMap getInventories()
  {
    return this.inventories;
  }
  
  public boolean canBeControlled(short paramShort)
  {
    return getModulesMap().containsKey(Short.valueOf(paramShort));
  }
  
  public ArrayList getModules()
  {
    return this.modules;
  }
  
  public ArrayList getInitialBlockMetaData()
  {
    return this.initialBlockMetaData;
  }
  
  public HashMap getModulesControllerMap()
  {
    return this.modulesControllerMap;
  }
  
  public HashMap getModulesMap()
  {
    return this.modulesMap;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.ManagerContainer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */