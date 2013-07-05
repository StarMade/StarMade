/*      */ package org.schema.game.common.controller.elements;
/*      */ 
/*      */ import Ad;
/*      */ import Af;
/*      */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*      */ import it.unimi.dsi.fastutil.objects.ObjectArrayFIFOQueue;
/*      */ import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*      */ import jE;
/*      */ import jL;
/*      */ import java.io.PrintStream;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.Map.Entry;
/*      */ import java.util.Set;
/*      */ import lA;
/*      */ import le;
/*      */ import md;
/*      */ import mf;
/*      */ import mh;
/*      */ import mi;
/*      */ import mk;
/*      */ import ml;
/*      */ import mo;
/*      */ import org.schema.game.common.controller.SegmentController;
/*      */ import org.schema.game.common.controller.elements.dockingBlock.DockingElementManagerInterface;
/*      */ import org.schema.game.common.controller.elements.dockingBlock.DockingMetaDataDummy;
/*      */ import org.schema.game.common.controller.elements.shield.ShieldCollectionManager;
/*      */ import org.schema.game.common.data.element.ElementInformation;
/*      */ import org.schema.game.common.data.element.ElementKeyMap;
/*      */ import org.schema.game.common.data.element.PointDistributionTagDummy;
/*      */ import org.schema.game.common.data.world.Segment;
/*      */ import org.schema.game.network.objects.remote.RemoteInventory;
/*      */ import org.schema.game.network.objects.remote.RemoteInventoryBuffer;
/*      */ import org.schema.game.network.objects.remote.RemoteInventoryMultMod;
/*      */ import org.schema.game.network.objects.remote.RemoteInventoryMultModBuffer;
/*      */ import org.schema.schine.network.NetworkStateContainer;
/*      */ import org.schema.schine.network.StateInterface;
/*      */ import org.schema.schine.network.objects.NetworkObject;
/*      */ import org.schema.schine.network.objects.Sendable;
/*      */ import org.schema.schine.network.objects.remote.RemoteIntArray;
/*      */ import org.schema.schine.network.objects.remote.RemoteIntArrayBuffer;
/*      */ import q;
/*      */ import s;
/*      */ import xq;
/*      */ 
/*      */ public abstract class ManagerContainer
/*      */   implements mh
/*      */ {
/*   58 */   protected final ArrayList modules = new ArrayList();
/*   59 */   protected final ArrayList handleModules = new ArrayList();
/*   60 */   private final ArrayList receiverModules = new ArrayList();
/*   61 */   private final ArrayList updatableModules = new ArrayList();
/*   62 */   private final ArrayList distributionReceiverModules = new ArrayList();
/*   63 */   private final ArrayList hittableModules = new ArrayList();
/*   64 */   private final ArrayList senderModules = new ArrayList();
/*   65 */   private final ArrayList changeListenModules = new ArrayList();
/*   66 */   private final ArrayList blockActivateListenModules = new ArrayList();
/*   67 */   private final HashMap modulesMap = new HashMap();
/*   68 */   private final HashMap modulesControllerMap = new HashMap();
/*   69 */   protected Collection dockingModules = new ArrayList();
/*      */ 
/*   72 */   private final Set activeInventories = new HashSet();
/*   73 */   private final ArrayList delayedInventoryAdd = new ArrayList();
/*   74 */   private final ArrayList timedUpdateInterfaces = new ArrayList();
/*   75 */   private final ArrayList delayedInventoryRemove = new ArrayList();
/*   76 */   private final HashMap inventories = new HashMap();
/*   77 */   private final ArrayList initialPointDists = new ArrayList();
/*   78 */   private final ArrayList initialBlockMetaData = new ArrayList();
/*   79 */   private final ObjectArrayFIFOQueue ntInventoryMods = new ObjectArrayFIFOQueue();
/*   80 */   private final ObjectArrayFIFOQueue ntInventoryMultMods = new ObjectArrayFIFOQueue();
/*   81 */   private final ObjectArrayFIFOQueue ntInventorySingleMods = new ObjectArrayFIFOQueue();
/*   82 */   private final ObjectArrayFIFOQueue ntActiveInventorySingleMods = new ObjectArrayFIFOQueue();
/*      */   private final SegmentController segmentController;
/*      */   private boolean flagAnyBlockAdded;
/*      */   private boolean flagAnyBlockRemoved;
/*   88 */   public boolean loadInventoriesFromTag = true;
/*      */ 
/*  806 */   private final ArrayList receivedDistributions = new ArrayList();
/*      */ 
/*      */   public ManagerContainer(SegmentController paramSegmentController)
/*      */   {
/*   93 */     this.segmentController = paramSegmentController;
/*   94 */     initialize();
/*   95 */     for (paramSegmentController = 0; paramSegmentController < this.modules.size(); paramSegmentController++)
/*      */     {
/*      */       ManagerModule localManagerModule;
/*      */       Object localObject;
/*   99 */       if (((
/*   99 */         localManagerModule = (ManagerModule)this.modules.get(paramSegmentController)) instanceof ManagerModuleCollection))
/*      */       {
/*  100 */         localObject = (ManagerModuleCollection)localManagerModule;
/*  101 */         assert (!getModulesMap().containsKey(Short.valueOf(((ManagerModuleCollection)localObject).getControllerID()))) : (localObject + "; " + getModulesMap());
/*      */ 
/*  103 */         getModulesMap().put(Short.valueOf(((ManagerModuleCollection)localObject).getControllerID()), localObject);
/*      */ 
/*  105 */         assert (!getModulesControllerMap().containsKey(Short.valueOf(((ManagerModuleCollection)localObject).getControllerID()))) : (localObject + "; " + getModulesMap());
/*  106 */         getModulesControllerMap().put(Short.valueOf(((ManagerModuleCollection)localObject).getControllerID()), localObject);
/*      */ 
/*  108 */         if ((((ManagerModuleCollection)localObject).getElementManager() instanceof TimedUpdateInterface)) {
/*  109 */           this.timedUpdateInterfaces.add((TimedUpdateInterface)((ManagerModuleCollection)localObject).getElementManager());
/*      */         }
/*  111 */         if ((((ManagerModuleCollection)localObject).getElementManager() instanceof BlockActivationListenerInterface)) {
/*  112 */           this.blockActivateListenModules.add((BlockActivationListenerInterface)((ManagerModuleCollection)localObject).getElementManager());
/*      */         }
/*      */       }
/*      */ 
/*  116 */       if (getModulesMap().containsKey(Short.valueOf(localManagerModule.getElementID())))
/*      */       {
/*  118 */         localObject = (ManagerModule)getModulesMap().get(Short.valueOf(localManagerModule.getElementID()));
/*  119 */         while (((ManagerModule)localObject).getNext() != null) {
/*  120 */           localObject = ((ManagerModule)localObject).getNext();
/*      */         }
/*  122 */         ((ManagerModule)localObject).setNext(localManagerModule);
/*      */       }
/*      */       else {
/*  125 */         getModulesMap().put(Short.valueOf(localManagerModule.getElementID()), localManagerModule);
/*      */       }
/*      */ 
/*  128 */       if ((localManagerModule instanceof ManagerModuleSingle))
/*      */       {
/*  130 */         localObject = ((ManagerModuleSingle)localManagerModule)
/*  130 */           .getCollectionManager();
/*  131 */         addTypeModifiers(localObject);
/*      */       }
/*      */ 
/*  134 */       if (!(localManagerModule.getElementManager() instanceof VoidElementManager)) {
/*  135 */         this.handleModules.add(localManagerModule);
/*      */       }
/*  137 */       if ((localManagerModule.getElementManager() instanceof DockingElementManagerInterface)) {
/*  138 */         this.dockingModules.add((ManagerModuleCollection)localManagerModule);
/*      */       }
/*  140 */       addTypeModifiers(localManagerModule.getElementManager());
/*      */     }
/*      */   }
/*      */ 
/*      */   private void addInventory(byte paramByte1, byte paramByte2, byte paramByte3, Segment paramSegment, short paramShort)
/*      */   {
/*  146 */     if (getSegmentController().isOnServer())
/*      */     {
/*  153 */       paramByte1 = paramSegment.a(paramByte1, paramByte2, paramByte3, new q());
/*  154 */       if ((!getInventories().containsKey(paramByte1)) && (!this.delayedInventoryAdd.contains(paramByte1)))
/*      */       {
/*  156 */         if (paramShort == 120) {
/*  157 */           this.delayedInventoryAdd.add(new mo(this, paramByte1)); return;
/*  158 */         }if (paramShort == 114) {
/*  159 */           this.delayedInventoryAdd.add(new mk(this, paramByte1)); return;
/*  160 */         }if (ElementKeyMap.getFactorykeyset().contains(Short.valueOf(paramShort)))
/*  161 */           this.delayedInventoryAdd.add(new mo(this, paramByte1));
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private void addTypeModifiers(Object paramObject) {
/*  167 */     if ((paramObject instanceof UpdatableCollectionManager)) {
/*  168 */       this.updatableModules.add((UpdatableCollectionManager)paramObject);
/*      */     }
/*      */ 
/*  171 */     if ((paramObject instanceof NTReceiveInterface)) {
/*  172 */       this.receiverModules.add((NTReceiveInterface)paramObject);
/*      */     }
/*  174 */     if ((paramObject instanceof NTDistributionReceiverInterface)) {
/*  175 */       this.distributionReceiverModules.add((NTDistributionReceiverInterface)paramObject);
/*      */     }
/*  177 */     if ((paramObject instanceof BlockActivationListenerInterface)) {
/*  178 */       this.blockActivateListenModules.add((BlockActivationListenerInterface)paramObject);
/*      */     }
/*  180 */     if ((paramObject instanceof HittableInterface)) {
/*  181 */       this.hittableModules.add((HittableInterface)paramObject);
/*      */     }
/*  183 */     if ((paramObject instanceof NTSenderInterface)) {
/*  184 */       this.senderModules.add((NTSenderInterface)paramObject);
/*      */     }
/*  186 */     if ((paramObject instanceof ElementChangeListenerInterface))
/*  187 */       this.changeListenModules.add((ElementChangeListenerInterface)paramObject);
/*      */   }
/*      */ 
/*      */   private void announceInventory(q paramq, boolean paramBoolean1, mf parammf, boolean paramBoolean2)
/*      */   {
/*  192 */     synchronized (getInventories())
/*      */     {
/*  194 */       if (paramBoolean2) {
/*  195 */         getInventories().put(paramq, parammf);
/*  196 */         paramq = new RemoteInventory(parammf, this, paramBoolean2, getSegmentController().isOnServer());
/*  197 */         getInventoryInterface().getInventoriesChangeBuffer().add(paramq);
/*      */       } else {
/*  199 */         System.err.println("ANNOUNEC INVENTORY REMOVE ON " + getSegmentController() + ": " + paramBoolean1);
/*      */ 
/*  201 */         if ((
/*  201 */           paramq = (mf)getInventories().remove(paramq)) != null)
/*      */         {
/*  202 */           onInventoryRemove(paramq, paramBoolean1);
/*  203 */           paramq = new RemoteInventory(paramq, this, paramBoolean2, getSegmentController().isOnServer());
/*  204 */           getInventoryInterface().getInventoriesChangeBuffer().add(paramq);
/*      */         }
/*      */       }
/*  207 */       return;
/*      */     }
/*      */   }
/*      */ 
/*      */   public void clear()
/*      */   {
/*  216 */     for (int i = 0; i < this.modules.size(); i++)
/*  217 */       ((ManagerModule)this.modules.get(i)).clear();
/*      */   }
/*      */ 
/*      */   private void fromTagDistribution(Ad paramAd)
/*      */   {
/*  222 */     paramAd = (Ad[])paramAd.a();
/*  223 */     for (int i = 0; (i < paramAd.length) && (paramAd[i].a() != Af.a); i++)
/*      */     {
/*  225 */       Ad[] arrayOfAd = (Ad[])paramAd[i].a();
/*  226 */       assert (arrayOfAd != null) : paramAd[i].a();
/*      */       int j;
/*      */       Object localObject;
/*  229 */       if (("ElementCollection".equals(paramAd[i].a())) || ("D".equals(paramAd[i].a())))
/*      */       {
/*  233 */         for (j = 0; (j < arrayOfAd.length) && (arrayOfAd[j].a() != Af.a); j++) {
/*  234 */           (
/*  235 */             localObject = new PointDistributionTagDummy())
/*  235 */             .fromTagStructure(arrayOfAd[j]);
/*  236 */           getInitialPointDists().add(localObject);
/*      */         }
/*      */       }
/*  238 */       else if ("A".equals(paramAd[i].a()))
/*  239 */         for (j = 0; (j < arrayOfAd.length) && (arrayOfAd[j].a() != Af.a); j++) {
/*  240 */           (
/*  241 */             localObject = new DockingMetaDataDummy())
/*  241 */             .fromTagStructure(arrayOfAd[j]);
/*  242 */           getInitialBlockMetaData().add(localObject);
/*      */         }
/*      */     }
/*      */   }
/*      */ 
/*      */   private void fromTagInventory(Ad paramAd)
/*      */   {
/*  253 */     paramAd = (Ad[])paramAd.a();
/*  254 */     for (int i = 0; (i < paramAd.length) && 
/*  255 */       (paramAd[i].a() != Af.a); i++)
/*      */     {
/*      */       Ad[] arrayOfAd;
/*  260 */       int j = ((Integer)(
/*  260 */         arrayOfAd = (Ad[])paramAd[i].a())[
/*  260 */         0].a()).intValue();
/*  261 */       q localq = (q)arrayOfAd[1].a();
/*      */ 
/*  263 */       Object localObject = null;
/*      */ 
/*  265 */       if (j == 3) {
/*  266 */         localObject = new mo(this, localq);
/*      */       }
/*  268 */       if (j == 1) {
/*  269 */         localObject = new mk(this, localq);
/*      */       }
/*  271 */       assert (localObject != null) : ("unknown type: " + j);
/*  272 */       if (this.loadInventoriesFromTag)
/*  273 */         ((mf)localObject).fromTagStructure(arrayOfAd[2]);
/*      */       else {
/*  275 */         System.err.println("[TAG] " + getSegmentController() + " not loading inventory");
/*      */       }
/*      */ 
/*  278 */       getInventories().put(((mf)localObject).a(), localObject);
/*      */     }
/*      */   }
/*      */ 
/*      */   public ArrayList getInitialPointDists()
/*      */   {
/*  288 */     return this.initialPointDists;
/*      */   }
/*      */ 
/*      */   public mf getInventory(q paramq)
/*      */   {
/*  293 */     return (mf)getInventories().get(paramq);
/*      */   }
/*      */ 
/*      */   private ml getInventoryInterface() {
/*  297 */     return (ml)getSegmentController().getNetworkObject();
/*      */   }
/*      */ 
/*      */   public String getName() {
/*  301 */     return this.segmentController.getUniqueIdentifier();
/*      */   }
/*      */ 
/*      */   public SegmentController getSegmentController()
/*      */   {
/*  308 */     return this.segmentController;
/*      */   }
/*      */ 
/*      */   public StateInterface getState()
/*      */   {
/*  316 */     return this.segmentController.getState();
/*      */   }
/*      */ 
/*      */   public void handle(lA paramlA) {
/*  320 */     for (int i = 0; i < this.handleModules.size(); i++)
/*  321 */       if (((ManagerModule)this.handleModules.get(i)).getElementManager().canHandle(paramlA))
/*  322 */         ((ManagerModule)this.handleModules.get(i)).getElementManager().handle(paramlA);
/*      */   }
/*      */ 
/*      */   public void handleBlockActivate(le paramle, boolean paramBoolean)
/*      */   {
/*  331 */     System.err.println("BlockActivationListenerInterfaces: " + this.blockActivateListenModules.size() + " on " + getClass().getSimpleName());
/*  332 */     for (Iterator localIterator = this.blockActivateListenModules.iterator(); localIterator.hasNext(); ) ((BlockActivationListenerInterface)localIterator.next())
/*  333 */         .onActivate(paramle, paramBoolean);
/*      */   }
/*      */ 
/*      */   public void onAction()
/*      */   {
/*      */   }
/*      */ 
/*      */   public void handleInventoryFromNT(RemoteInventoryBuffer arg1)
/*      */   {
/*  347 */     ??? = ???.getReceiveBuffer();
/*      */ 
/*  349 */     for (int i = 0; i < ???.size(); i++) {
/*  350 */       mf localmf = (mf)((RemoteInventory)???.get(i)).get();
/*  351 */       synchronized (this.ntInventoryMods) {
/*  352 */         this.ntInventoryMods.enqueue(localmf);
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  358 */     ObjectArrayList localObjectArrayList1 = getInventoryInterface().getInventoryUpdateBuffer().getReceiveBuffer();
/*      */ 
/*  362 */     for (int j = 0; j < localObjectArrayList1.size(); j++) {
/*  363 */       ??? = (RemoteIntArray)localObjectArrayList1.get(j);
/*  364 */       synchronized (this.ntInventorySingleMods) {
/*  365 */         this.ntInventorySingleMods.enqueue(???);
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  372 */     ObjectArrayList localObjectArrayList2 = getInventoryInterface().getInventoryMultModBuffer().getReceiveBuffer();
/*      */ 
/*  376 */     for (j = 0; j < localObjectArrayList2.size(); j++) {
/*  377 */       ??? = (RemoteInventoryMultMod)localObjectArrayList2.get(j);
/*  378 */       synchronized (this.ntInventoryMultMods) {
/*  379 */         this.ntInventoryMultMods.enqueue(((RemoteInventoryMultMod)???).get());
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  386 */     ObjectArrayList localObjectArrayList3 = getInventoryInterface().getInventoryActivateBuffer().getReceiveBuffer();
/*  387 */     for (j = 0; j < localObjectArrayList3.size(); j++) {
/*  388 */       ??? = (RemoteIntArray)localObjectArrayList3.get(j);
/*  389 */       synchronized (this.ntActiveInventorySingleMods) {
/*  390 */         this.ntActiveInventorySingleMods.enqueue(???);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   protected abstract void initialize();
/*      */ 
/*      */   public void onAddedElement(short paramShort, int paramInt, byte paramByte1, byte paramByte2, byte paramByte3, Segment paramSegment)
/*      */   {
/*  403 */     if (ElementKeyMap.getFactorykeyset().contains(Short.valueOf(paramShort))) {
/*  404 */       addInventory(paramByte1, paramByte2, paramByte3, paramSegment, paramShort);
/*      */     }
/*      */ 
/*  408 */     if ((
/*  408 */       paramInt = (ManagerModuleCollection)getModulesControllerMap().get(Short.valueOf(paramShort))) != null)
/*      */     {
/*  409 */       paramInt.addControllerBlock(paramByte1, paramByte2, paramByte3, paramSegment);
/*      */     }
/*  413 */     else if (((
/*  413 */       paramInt = (ManagerModule)getModulesMap().get(Short.valueOf(paramShort))) != null) && 
/*  413 */       ((paramInt instanceof ManagerModuleSingle)))
/*      */     {
/*  416 */       ((ManagerModuleSingle)paramInt).addElement(paramByte1, paramByte2, paramByte3, paramSegment);
/*      */     }
/*      */     else {
/*  419 */       assert (paramShort != 2);
/*  420 */       switch (paramShort)
/*      */       {
/*      */       case 120:
/*  434 */         addInventory(paramByte1, paramByte2, paramByte3, paramSegment, paramShort);
/*  435 */         break;
/*      */       case 114:
/*  439 */         addInventory(paramByte1, paramByte2, paramByte3, paramSegment, paramShort);
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  448 */     this.flagAnyBlockAdded = true;
/*      */   }
/*      */ 
/*      */   public void onControllerAdded(q paramq1, q paramq2, short paramShort)
/*      */   {
/*  456 */     ManagerModule localManagerModule = (ManagerModule)getModulesMap().get(Short.valueOf(paramShort));
/*  457 */     assert (localManagerModule != null) : ("critical: no module found for " + getSegmentController() + ": " + paramShort + "; " + getModulesMap());
/*  458 */     if (localManagerModule.getNext() != null)
/*      */     {
/*  461 */       int i = getSegmentController().getSegmentBuffer().a(paramq1, true)
/*  461 */         .a();
/*  462 */       while (localManagerModule.getNext() != null) {
/*  463 */         assert ((localManagerModule instanceof ManagerModuleCollection));
/*      */ 
/*  465 */         if (((ManagerModuleCollection)localManagerModule)
/*  465 */           .getControllerID() == i) break;
/*  466 */         localManagerModule = localManagerModule.getNext();
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  473 */     assert (localManagerModule != null) : (paramShort + " -> " + ElementKeyMap.getInfo(paramShort).getName() + ": " + getModulesMap());
/*  474 */     if (localManagerModule == null) {
/*  475 */       throw new NullPointerException("Could not find Manager Module for " + paramShort + ": " + getModulesMap());
/*      */     }
/*      */ 
/*  478 */     localManagerModule.addControlledBlock(paramq1, paramq2, paramShort);
/*      */   }
/*      */ 
/*      */   public void onControllerRemoved(q paramq1, q paramq2, short paramShort)
/*      */   {
/*  483 */     assert (getModulesMap() != null);
/*      */ 
/*  485 */     assert (paramShort != 0);
/*      */ 
/*  487 */     assert (getModulesMap().containsKey(Short.valueOf(paramShort)));
/*      */ 
/*  491 */     if ((paramShort == 0) || (!getModulesMap().containsKey(Short.valueOf(paramShort)))) {
/*  492 */       System.err.println("Exception: tried to remove controller: " + paramShort + ": " + getModulesMap() + " for " + getSegmentController());
/*      */       return;
/*      */     }
/*      */     ManagerModule localManagerModule;
/*  497 */     if ((
/*  497 */       localManagerModule = (ManagerModule)getModulesMap().get(Short.valueOf(paramShort)))
/*  497 */       .getNext() != null)
/*      */     {
/*  500 */       int i = getSegmentController().getSegmentBuffer().a(paramq1, true)
/*  500 */         .a();
/*  501 */       while (localManagerModule.getNext() != null) {
/*  502 */         assert ((localManagerModule instanceof ManagerModuleCollection));
/*      */ 
/*  504 */         if (((ManagerModuleCollection)localManagerModule)
/*  504 */           .getControllerID() == i) break;
/*  505 */         localManagerModule = localManagerModule.getNext();
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  514 */     assert (localManagerModule != null) : (paramShort + " -> " + ElementKeyMap.getInfo(paramShort).getName() + ": " + getModulesMap());
/*  515 */     localManagerModule.removeControllerBlock(paramq1, paramq2, paramShort);
/*      */   }
/*      */ 
/*      */   public void onHit(int paramInt)
/*      */   {
/*  522 */     for (int i = 0; i < this.hittableModules.size(); i++)
/*  523 */       ((HittableInterface)this.hittableModules.get(i)).onHit(paramInt);
/*      */   }
/*      */ 
/*      */   public void onHitNotice() {
/*      */   }
/*      */ 
/*      */   private void onInventoryRemove(mf parammf, boolean paramBoolean) {
/*  530 */     if ((!paramBoolean) && (this.segmentController.isOnServer())) {
/*  531 */       System.err.println("[MANAGERCONTAINER] REMOVING INVENTORY! (now spawning in space)" + getSegmentController().getState() + " - " + getSegmentController());
/*  532 */       parammf.a(getSegmentController());
/*      */     }
/*      */   }
/*      */ 
/*      */   public void onRemovedElement(short paramShort, int paramInt, byte paramByte1, byte paramByte2, byte paramByte3, Segment paramSegment, boolean paramBoolean)
/*      */   {
/*  539 */     if (ElementKeyMap.getFactorykeyset().contains(Short.valueOf(paramShort))) {
/*  540 */       removeInventory(paramByte1, paramByte2, paramByte3, paramSegment, paramBoolean);
/*      */     }
/*      */ 
/*  544 */     if ((
/*  544 */       paramInt = (ManagerModuleCollection)getModulesControllerMap().get(Short.valueOf(paramShort))) != null)
/*      */     {
/*  545 */       paramInt.removeController(paramByte1, paramByte2, paramByte3, paramSegment);
/*      */     }
/*  549 */     else if (((
/*  549 */       paramInt = (ManagerModule)getModulesMap().get(Short.valueOf(paramShort))) != null) && 
/*  549 */       ((paramInt instanceof ManagerModuleSingle))) {
/*  550 */       ((ManagerModuleSingle)paramInt).removeElement(paramByte1, paramByte2, paramByte3, paramSegment);
/*      */     }
/*      */     else {
/*  553 */       switch (paramShort)
/*      */       {
/*      */       case 120:
/*  566 */         System.err.println("[MANAGER_CONTAINER] onRemovedElement REMOVING INVENTORY!! " + getSegmentController() + "; " + getSegmentController().getState() + " preserveC: " + paramBoolean);
/*      */       case 114:
/*  567 */         removeInventory(paramByte1, paramByte2, paramByte3, paramSegment, paramBoolean);
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  578 */     this.flagAnyBlockRemoved = true;
/*      */   }
/*      */ 
/*      */   public String printInventories()
/*      */   {
/*  585 */     return getInventories().toString();
/*      */   }
/*      */ 
/*      */   private void removeInventory(byte paramByte1, byte paramByte2, byte paramByte3, Segment paramSegment, boolean paramBoolean)
/*      */   {
/*  590 */     if (getSegmentController().isOnServer())
/*      */     {
/*  597 */       if (!paramBoolean) {
/*  598 */         paramByte1 = paramSegment.a(paramByte1, paramByte2, paramByte3, new q());
/*  599 */         if ((getInventories().containsKey(paramByte1)) && (!this.delayedInventoryRemove.contains(paramByte1))) {
/*  600 */           this.delayedInventoryRemove.add(new s(paramByte1, paramBoolean ? 1 : 0));
/*      */         }
/*  602 */         return;
/*      */       }
/*      */ 
/*  605 */       System.err.println("[SERVER] keeping inventory for now so it's not emptied by anything " + getSegmentController());
/*      */     }
/*      */   }
/*      */ 
/*      */   public void sendInventoryModification(int paramInt, q paramq)
/*      */   {
/*      */     RemoteIntArray localRemoteIntArray;
/*  611 */     (
/*  613 */       localRemoteIntArray = new RemoteIntArray(6, getSegmentController().getNetworkObject()))
/*  613 */       .set(0, paramInt);
/*      */ 
/*  615 */     mf localmf = getInventory(paramq);
/*  616 */     assert (localmf != null) : (paramq + " --- " + getInventories());
/*  617 */     localRemoteIntArray.set(1, localmf.a(paramInt));
/*  618 */     localRemoteIntArray.set(2, localmf.a(paramInt));
/*  619 */     localRemoteIntArray.set(3, paramq.a);
/*  620 */     localRemoteIntArray.set(4, paramq.b);
/*  621 */     localRemoteIntArray.set(5, paramq.c);
/*  622 */     getInventoryInterface().getInventoryUpdateBuffer().add(localRemoteIntArray);
/*      */     try
/*      */     {
/*  625 */       if (getInventoryInterface().getInventoryUpdateBuffer().size() > 200) {
/*  626 */         paramInt = (Sendable)getSegmentController().getState().getLocalAndRemoteObjectContainer().getLocalObjects().get(getSegmentController().getId());
/*  627 */         paramq = (NetworkObject)getSegmentController().getState().getLocalAndRemoteObjectContainer().getRemoteObjects().get(getSegmentController().getId());
/*  628 */         throw new IllegalArgumentException("WARNING: inventory high of " + getSegmentController() + ": " + getInventoryInterface().getInventoryUpdateBuffer().size() + "; " + paramInt + "; " + paramq);
/*      */       }
/*      */       return;
/*      */     } catch (IllegalArgumentException localIllegalArgumentException) {
/*  632 */       localIllegalArgumentException.printStackTrace();
/*      */     }
/*      */   }
/*      */ 
/*      */   public void sendInventoryModification(Collection paramCollection, q paramq)
/*      */   {
/*      */     mf localmf;
/*  639 */     if ((
/*  639 */       localmf = getInventory(paramq)) != null)
/*      */     {
/*  640 */       paramCollection = new mi(paramCollection, localmf, paramq);
/*      */ 
/*  642 */       getInventoryInterface().getInventoryMultModBuffer().add(new RemoteInventoryMultMod(paramCollection, getSegmentController().getNetworkObject()));
/*  643 */       return;
/*      */     }try {
/*  645 */       throw new IllegalArgumentException("[INVENTORY] Exception: tried to send inventory " + paramq); } catch (Exception localException) { localException
/*  646 */         .printStackTrace();
/*      */     }
/*      */   }
/*      */ 
/*      */   public Ad toTagStructure()
/*      */   {
/*      */     Object localObject2;
/*      */     Object localObject3;
/*  666 */     synchronized (getInventories()) {
/*  667 */       Ad[] arrayOfAd1 = new Ad[getInventories().size() + 1];
/*      */ 
/*  669 */       int i = 0;
/*      */ 
/*  671 */       for (localObject1 = getInventories().entrySet().iterator(); ((Iterator)localObject1).hasNext(); ) { localObject2 = (Map.Entry)((Iterator)localObject1).next();
/*      */ 
/*  673 */         (
/*  674 */           localObject3 = new Ad[4])[
/*  674 */           0] = new Ad(Af.d, "type", Integer.valueOf(((mf)((Map.Entry)localObject2).getValue()).c()));
/*  675 */         localObject3[1] = new Ad(Af.k, "index", ((Map.Entry)localObject2).getKey());
/*  676 */         localObject3[2] = ((mf)((Map.Entry)localObject2).getValue()).toTagStructure();
/*  677 */         localObject3[3] = new Ad(Af.a, "fin", null);
/*  678 */         arrayOfAd1[i] = new Ad(Af.n, "inventory", (Ad[])localObject3);
/*  679 */         i++;
/*      */       }
/*  681 */       arrayOfAd1[getInventories().size()] = new Ad(Af.a, "fin", null);
/*      */     }
/*  683 */     ??? = new Ad(Af.n, "controllerStructure", arrayOfAd2);
/*      */ 
/*  685 */     ArrayList localArrayList = new ArrayList();
/*  686 */     for (Object localObject1 = this.modules.iterator(); ((Iterator)localObject1).hasNext(); ) {
/*  687 */       if (((
/*  687 */         localObject2 = (ManagerModule)((Iterator)localObject1).next())
/*  687 */         .getElementManager() instanceof UsableDistributionControllableElementManager)) {
/*  688 */         localObject3 = (UsableDistributionControllableElementManager)((ManagerModule)localObject2).getElementManager();
/*  689 */         localArrayList.add(((UsableDistributionControllableElementManager)localObject3).toDistributionTagStructure());
/*      */       }
/*      */     }
/*      */ 
/*  693 */     for (localObject1 = this.modules.iterator(); ((Iterator)localObject1).hasNext(); ) {
/*  694 */       if (((
/*  694 */         localObject2 = (ManagerModule)((Iterator)localObject1).next())
/*  694 */         .getElementManager() instanceof UsableControllableElementManager))
/*      */       {
/*  697 */         if ((
/*  697 */           localObject3 = (UsableControllableElementManager)((ManagerModule)localObject2).getElementManager())
/*  697 */           .hasMetaData()) {
/*  698 */           localArrayList.add(((UsableControllableElementManager)localObject3).toTagStructure());
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/*  703 */     if (localArrayList.size() > 0)
/*      */     {
/*  705 */       localObject2 = new Ad[localArrayList.size() + 1];
/*  706 */       for (int j = 0; j < localArrayList.size(); j++) {
/*  707 */         localObject2[j] = ((Ad)localArrayList.get(j));
/*      */       }
/*  709 */       localObject2[(localObject2.length - 1)] = new Ad(Af.a, null, null);
/*  710 */       localObject1 = new Ad(Af.n, "shipMan0", (Ad[])localObject2);
/*      */     } else {
/*  712 */       localObject1 = new Ad(Af.d, "shipMan0", Integer.valueOf(0));
/*      */     }
/*  714 */     if ((this instanceof PowerManagerInterface))
/*      */     {
/*  717 */       localObject2 = ((PowerManagerInterface)this).getPowerAddOn()
/*  717 */         .toTagStructure();
/*      */     }
/*  719 */     else localObject2 = new Ad(Af.b, null, Integer.valueOf(0));
/*      */     Ad localAd;
/*  721 */     if ((this instanceof ShieldContainerInterface))
/*      */     {
/*  723 */       localAd = new Ad(Af.g, "sh", Double.valueOf(((ShieldContainerInterface)this).getShieldManager().getShields()));
/*      */     }
/*  725 */     else localAd = new Ad(Af.b, null, Integer.valueOf(0));
/*      */ 
/*  731 */     return new Ad(Af.n, "container", new Ad[] { ???, localObject1, localObject2, localAd, new Ad(Af.a, null, null) });
/*      */   }
/*      */ 
/*      */   public void fromTagStructure(Ad paramAd)
/*      */   {
/*  737 */     if (paramAd.a().equals("container")) {
/*  738 */       paramAd = (Ad[])paramAd.a();
/*  739 */       fromTagInventory(paramAd[0]);
/*  740 */       if (paramAd[1].a() == Af.n) {
/*  741 */         Ad[] arrayOfAd = (Ad[])paramAd[1].a();
/*  742 */         for (int j = 0; j < arrayOfAd.length; j++) {
/*  743 */           if (arrayOfAd[j].a() != Af.a)
/*      */           {
/*  745 */             fromTagDistribution(arrayOfAd[j]);
/*      */           }
/*      */         }
/*      */       }
/*      */ 
/*  750 */       if (((this instanceof PowerManagerInterface)) && (paramAd.length > 2) && ("pw".equals(paramAd[2].a()))) {
/*  751 */         ((PowerManagerInterface)this).getPowerAddOn()
/*  753 */           .fromTagStructure(paramAd[2]);
/*      */       }
/*      */ 
/*  756 */       if (((this instanceof ShieldContainerInterface)) && (paramAd.length > 3) && ("sh".equals(paramAd[3].a()))) {
/*  757 */         ((ShieldContainerInterface)this).getShieldManager().setInitialShields(((Double)paramAd[3].a()).doubleValue());
/*      */       }
/*  759 */       return; } if (paramAd.a().equals("controllerStructure")) {
/*  760 */       handleTag(paramAd); return;
/*      */     }
/*  762 */     paramAd = (Ad[])paramAd.a();
/*      */ 
/*  764 */     for (int i = 0; i < paramAd.length; i++)
/*  765 */       if (paramAd[i].a() != Af.a)
/*  766 */         handleTag(paramAd[i]);
/*      */   }
/*      */ 
/*      */   private void handleTag(Ad paramAd)
/*      */   {
/*  775 */     if (paramAd.a().equals("wepContr")) {
/*  776 */       fromTagDistribution(paramAd); return;
/*  777 */     }if (paramAd.a().equals("controllerStructure")) {
/*  778 */       fromTagInventory(paramAd); return;
/*      */     }
/*  780 */     if (!$assertionsDisabled) throw new AssertionError(paramAd.a());
/*      */   }
/*      */ 
/*      */   public void updateFromNetworkObject(NetworkObject paramNetworkObject)
/*      */   {
/*  786 */     for (int i = 0; i < this.receiverModules.size(); i++)
/*  787 */       ((NTReceiveInterface)this.receiverModules.get(i)).updateFromNT(getSegmentController().getNetworkObject());
/*      */     RemoteIntArray localRemoteIntArray;
/*  790 */     if ((paramNetworkObject instanceof DistributionInterface))
/*      */     {
/*  792 */       localRemoteIntArray = null; for (paramNetworkObject = ((DistributionInterface)paramNetworkObject).getDistributionModification().getReceiveBuffer()
/*  792 */         .iterator(); paramNetworkObject.hasNext(); ) { localRemoteIntArray = (RemoteIntArray)paramNetworkObject.next();
/*      */ 
/*  794 */         q localq1 = new q(localRemoteIntArray.getIntArray()[0], localRemoteIntArray.getIntArray()[1], localRemoteIntArray.getIntArray()[2]);
/*  795 */         q localq2 = new q(localRemoteIntArray.getIntArray()[3], localRemoteIntArray.getIntArray()[4], localRemoteIntArray.getIntArray()[5]);
/*  796 */         synchronized (this.receivedDistributions) {
/*  797 */           this.receivedDistributions.add(new jE(localq1, localq2, localRemoteIntArray.getIntArray()[6], localRemoteIntArray.getIntArray()[7], localRemoteIntArray.getIntArray()[8]));
/*      */         } }
/*      */     }
/*  800 */     handleInventoryFromNT(getInventoryInterface().getInventoriesChangeBuffer());
/*      */   }
/*      */ 
/*      */   public void updateLocal(xq paramxq)
/*      */   {
/*      */     Object localObject1;
/*  811 */     if (!this.ntInventoryMods.isEmpty())
/*  812 */       synchronized (this.ntInventoryMods) {
/*  813 */         while (!this.ntInventoryMods.isEmpty()) {
/*  814 */           localObject1 = (mf)this.ntInventoryMods.dequeue();
/*  815 */           getInventories().put(((mf)localObject1).a(), localObject1);
/*  816 */           System.err.println("ADDED INVENTORY: " + ((mf)localObject1).a() + " on " + getSegmentController().getState() + ": " + localObject1);
/*      */         }
/*      */       }
/*      */     Object localObject4;
/*      */     Object localObject5;
/*      */     Object localObject6;
/*  832 */     if (!this.ntInventorySingleMods.isEmpty()) {
/*  833 */       synchronized (this.ntInventorySingleMods) {
/*  834 */         localObject1 = new ArrayList();
/*  835 */         while (!this.ntInventorySingleMods.isEmpty()) {
/*  836 */           localObject4 = (RemoteIntArray)this.ntInventorySingleMods.dequeue();
/*  837 */           localObject5 = new q(localObject4.getIntArray()[3], localObject4.getIntArray()[4], localObject4.getIntArray()[5]);
/*      */ 
/*  839 */           if ((
/*  839 */             localObject6 = getInventory((q)localObject5)) != null)
/*      */           {
/*  840 */             ((mf)localObject6).a((RemoteIntArray)localObject4, getInventoryInterface());
/*      */           } else {
/*  842 */             if (!this.segmentController.isOnServer()) {
/*  843 */               ((ArrayList)localObject1).add(localObject4);
/*      */             }
/*  845 */             System.err.println("[MANAGERCONTAINER] Exc: NOT FOUND INVENTORY (received mod): " + getSegmentController() + " - " + localObject5 + "; " + getInventories().keySet());
/*      */           }
/*      */         }
/*  848 */         if (!((ArrayList)localObject1).isEmpty()) {
/*  849 */           while (!((ArrayList)localObject1).isEmpty()) {
/*  850 */             this.ntInventorySingleMods.enqueue(((ArrayList)localObject1).remove(0));
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/*  856 */     if (!this.ntInventoryMultMods.isEmpty()) {
/*  857 */       synchronized (this.ntInventoryMultMods) {
/*  858 */         localObject1 = new ArrayList();
/*  859 */         while (!this.ntInventoryMultMods.isEmpty()) {
/*  860 */           localObject4 = (mi)this.ntInventoryMultMods.dequeue();
/*      */ 
/*  862 */           if ((
/*  862 */             localObject5 = getInventory(((mi)localObject4).a)) != null)
/*      */           {
/*  863 */             getInventoryInterface(); ((mf)localObject5).a((mi)localObject4);
/*      */           } else {
/*  865 */             if (!this.segmentController.isOnServer()) {
/*  866 */               ((ArrayList)localObject1).add(localObject4);
/*      */             }
/*  868 */             System.err.println("[MANAGERCONTAINER] Exc: NOT FOUND MULT INVENTORY (received mod): " + getSegmentController() + " - " + ((mi)localObject4).a + "; " + getInventories().keySet());
/*      */           }
/*      */         }
/*  871 */         if (!((ArrayList)localObject1).isEmpty()) {
/*  872 */           while (!((ArrayList)localObject1).isEmpty()) {
/*  873 */             this.ntInventoryMultMods.enqueue(((ArrayList)localObject1).remove(0));
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*  878 */     if (!this.ntActiveInventorySingleMods.isEmpty()) {
/*  879 */       synchronized (this.ntActiveInventorySingleMods) {
/*  880 */         ArrayList localArrayList = new ArrayList();
/*  881 */         while (!this.ntActiveInventorySingleMods.isEmpty()) {
/*  882 */           localObject4 = (RemoteIntArray)this.ntActiveInventorySingleMods.dequeue();
/*  883 */           localObject5 = new q(localObject4.getIntArray()[0], localObject4.getIntArray()[1], localObject4.getIntArray()[2]);
/*  884 */           localObject6 = getInventory((q)localObject5);
/*  885 */           System.err.println("[INVENTORY] HANDLE RECEIVED ACTIVATE: " + localObject5 + ": " + localObject6);
/*  886 */           assert (localObject6 != null);
/*  887 */           if ((localObject6 != null) && ((localObject6 instanceof md))) {
/*  888 */             ((md)localObject6).b();
/*      */           } else {
/*  890 */             localArrayList.add(localObject4);
/*  891 */             System.err.println("Exception: NOT FOUND active INVENTORY: " + getSegmentController() + " - " + localObject5);
/*      */           }
/*      */         }
/*  894 */         if (!localArrayList.isEmpty()) {
/*  895 */           while (!localArrayList.isEmpty()) {
/*  896 */             this.ntActiveInventorySingleMods.enqueue(localArrayList.remove(0));
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/*  902 */     long l = System.currentTimeMillis();
/*  903 */     for (int j = 0; j < this.modules.size(); j++) {
/*  904 */       ((ManagerModule)this.modules.get(j)).update(paramxq, l);
/*      */     }
/*      */ 
/*  907 */     for (j = 0; j < this.updatableModules.size(); j++) {
/*  908 */       ((UpdatableCollectionManager)this.updatableModules.get(j)).update(paramxq);
/*      */     }
/*      */ 
/*  911 */     j = 0;
/*      */ 
/*  913 */     if (!this.receivedDistributions.isEmpty()) {
/*  914 */       synchronized (this.receivedDistributions)
/*      */       {
/*  916 */         localObject6 = (jE)this.receivedDistributions.remove(0);
/*  917 */         paramxq = 0;
/*  918 */         for (int i = 0; i < this.distributionReceiverModules.size(); i++)
/*      */         {
/*  920 */           if (((NTDistributionReceiverInterface)this.distributionReceiverModules.get(i)).receiveDistribution((jE)localObject6, getSegmentController().getNetworkObject()))
/*      */           {
/*  921 */             paramxq = 1;
/*      */           }
/*      */         }
/*      */ 
/*  925 */         if ((paramxq == 0) && (!getSegmentController().isOnServer())) {
/*  926 */           PointDistributionTagDummy localPointDistributionTagDummy = new PointDistributionTagDummy((jE)localObject6);
/*  927 */           getInitialPointDists().add(localPointDistributionTagDummy);
/*      */         }
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  933 */     while (!this.delayedInventoryAdd.isEmpty())
/*      */     {
/*  936 */       ??? = (mf)this.delayedInventoryAdd.remove(0);
/*      */ 
/*  938 */       if (!getInventories().containsKey(((mf)???).a())) {
/*  939 */         if (getSegmentController().isOnServer()) {
/*  940 */           System.err.println("[SERVER] " + getSegmentController() + " ADDING NEW INVENTORY " + ((mf)???).a());
/*      */         }
/*  942 */         announceInventory(((mf)???).a(), false, (mf)???, true);
/*  943 */         j = 1;
/*      */       }
/*      */     }
/*  946 */     while (!this.delayedInventoryRemove.isEmpty()) {
/*  947 */       ??? = (s)this.delayedInventoryRemove.remove(0);
/*  948 */       announceInventory(new q(((s)???).a, ((s)???).b, ((s)???).c), ((s)???).d == 1, null, false);
/*  949 */       j = 1;
/*      */     }
/*      */ 
/*  952 */     if (j != 0) {
/*  953 */       this.activeInventories.clear();
/*  954 */       for (??? = getInventories().values().iterator(); ((Iterator)???).hasNext(); )
/*      */       {
/*  956 */         if (((
/*  956 */           localObject6 = (mf)((Iterator)???).next()) instanceof md))
/*      */         {
/*  957 */           this.activeInventories.add((md)localObject6);
/*      */         }
/*      */       }
/*      */     }
/*  961 */     else if (getSegmentController().isOnServer())
/*      */     {
/*  965 */       for (??? = this.activeInventories.iterator(); ((Iterator)???).hasNext(); )
/*      */       {
/*  967 */         if ((
/*  967 */           localObject6 = (md)((Iterator)???).next())
/*  967 */           .a())
/*  968 */           ((md)localObject6).a();
/*      */       }
/*      */     }
/*      */     int k;
/*  973 */     if (this.flagAnyBlockAdded) {
/*  974 */       for (k = 0; k < this.changeListenModules.size(); k++) {
/*  975 */         ((ElementChangeListenerInterface)this.changeListenModules.get(k)).onAddedAnyElement();
/*      */       }
/*  977 */       this.flagAnyBlockAdded = false;
/*      */     }
/*  979 */     if (this.flagAnyBlockRemoved) {
/*  980 */       for (k = 0; k < this.changeListenModules.size(); k++) {
/*  981 */         ((ElementChangeListenerInterface)this.changeListenModules.get(k)).onRemovedAnyElement();
/*      */       }
/*  983 */       this.flagAnyBlockRemoved = false;
/*      */     }
/*      */   }
/*      */ 
/*      */   public void updateToFullNetworkObject(NetworkObject paramNetworkObject)
/*      */   {
/*  991 */     for (int i = 0; i < this.senderModules.size(); i++)
/*  992 */       ((NTSenderInterface)this.senderModules.get(i)).updateToFullNT(paramNetworkObject);
/*      */   }
/*      */ 
/*      */   public HashMap getInventories()
/*      */   {
/* 1001 */     return this.inventories;
/*      */   }
/*      */ 
/*      */   public boolean canBeControlled(short paramShort)
/*      */   {
/* 1006 */     return getModulesMap().containsKey(Short.valueOf(paramShort));
/*      */   }
/*      */ 
/*      */   public ArrayList getModules()
/*      */   {
/* 1014 */     return this.modules;
/*      */   }
/*      */ 
/*      */   public ArrayList getInitialBlockMetaData()
/*      */   {
/* 1022 */     return this.initialBlockMetaData;
/*      */   }
/*      */ 
/*      */   public HashMap getModulesControllerMap()
/*      */   {
/* 1030 */     return this.modulesControllerMap;
/*      */   }
/*      */ 
/*      */   public HashMap getModulesMap()
/*      */   {
/* 1038 */     return this.modulesMap;
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.ManagerContainer
 * JD-Core Version:    0.6.2
 */