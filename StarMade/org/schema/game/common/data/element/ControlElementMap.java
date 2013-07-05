/*      */ package org.schema.game.common.data.element;
/*      */ 
/*      */ import Ad;
/*      */ import Af;
/*      */ import Ag;
/*      */ import af;
/*      */ import ct;
/*      */ import dj;
/*      */ import ep;
/*      */ import it.unimi.dsi.fastutil.io.FastByteArrayInputStream;
/*      */ import it.unimi.dsi.fastutil.io.FastByteArrayOutputStream;
/*      */ import it.unimi.dsi.fastutil.longs.Long2ObjectMap.Entry;
/*      */ import it.unimi.dsi.fastutil.longs.Long2ObjectMap.FastEntrySet;
/*      */ import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
/*      */ import it.unimi.dsi.fastutil.longs.LongSet;
/*      */ import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*      */ import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
/*      */ import it.unimi.dsi.fastutil.objects.ObjectSet;
/*      */ import it.unimi.dsi.fastutil.shorts.Short2ObjectOpenHashMap;
/*      */ import jD;
/*      */ import jL;
/*      */ import ja;
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.DataInputStream;
/*      */ import java.io.DataOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.PrintStream;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.ConcurrentModificationException;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.Map;
/*      */ import java.util.Map.Entry;
/*      */ import java.util.Set;
/*      */ import java.util.zip.DataFormatException;
/*      */ import java.util.zip.Deflater;
/*      */ import java.util.zip.Inflater;
/*      */ import ka;
/*      */ import ld;
/*      */ import le;
/*      */ import org.schema.game.common.controller.CannotImmediateRequestOnClientException;
/*      */ import org.schema.game.common.controller.elements.ManagerContainer;
/*      */ import org.schema.game.network.objects.NetworkSegmentController;
/*      */ import org.schema.schine.network.objects.remote.RemoteArrayBuffer;
/*      */ import org.schema.schine.network.objects.remote.RemoteField;
/*      */ import org.schema.schine.network.objects.remote.RemoteIntegerArray;
/*      */ import q;
/*      */ import s;
/*      */ import vg;
/*      */ import x;
/*      */ import xq;
/*      */ 
/*      */ public class ControlElementMap
/*      */   implements Ag
/*      */ {
/*   58 */   private final Map positionControlMapChache = new HashMap();
/*      */ 
/*   60 */   private ControlElementMapper controllingMap = new ControlElementMapper();
/*      */   private ka sendableSegmentController;
/*   64 */   private ja elementPositionTmp = new ja();
/*   65 */   private final HashSet delayedControllerUpdates = new HashSet();
/*   66 */   private final HashSet failedDelayedUpdates = new HashSet();
/*      */ 
/*   68 */   private final ObjectOpenHashSet delayedNTUpdates = new ObjectOpenHashSet();
/*   69 */   private final ControlElementMapper delayedNTUpdatesMap = new ControlElementMapper();
/*      */ 
/*   72 */   le pTmp = new le();
/*      */ 
/*   74 */   le pointUnsaveTmp = new le();
/*      */   private ControlledElementContainer addedDouble;
/*  123 */   private final HashSet loopMap = new HashSet();
/*      */   private boolean needsControllerUpdates;
/*      */   private Iterator controllingMapCheck;
/*      */   private af currentBuildController;
/*  131 */   private final ArrayList toRemoveControlled = new ArrayList();
/*      */   private boolean testMode;
/*      */   private boolean structureCompleteChange;
/*      */   private boolean initialStructureReceived;
/*      */   private boolean flagRequested;
/*      */   public static final int blockSize = 14;
/*      */ 
/*      */   private boolean addControl(q paramq1, q paramq2, short paramShort, boolean paramBoolean)
/*      */   {
/*   82 */     return addControl(new ControlledElementContainer(ElementCollection.getIndex(paramq1), paramq2, paramShort, true, paramBoolean));
/*      */   }
/*      */ 
/*      */   private boolean addControl(ControlledElementContainer paramControlledElementContainer)
/*      */   {
/*   87 */     if (paramControlledElementContainer.from == ElementCollection.getIndex(paramControlledElementContainer.to)) {
/*   88 */       System.err.println("WARNING: tried to add controlled element that is equal with the controlling");
/*   89 */       if (!this.sendableSegmentController.isOnServer()) {
/*   90 */         ((ct)this.sendableSegmentController.getState()).a().b("Error: Cannot connect a block\nto itself");
/*      */       }
/*   92 */       return false;
/*      */     }
/*      */ 
/*   96 */     new ja(paramControlledElementContainer.to, paramControlledElementContainer.controlledType);
/*      */ 
/*   98 */     boolean bool = getControllingMap().put(paramControlledElementContainer.from, paramControlledElementContainer.to, paramControlledElementContainer.controlledType);
/*      */ 
/*  101 */     if ((paramControlledElementContainer.send) && (this.sendableSegmentController != null))
/*      */     {
/*  103 */       send(paramControlledElementContainer.from, paramControlledElementContainer.to, paramControlledElementContainer.controlledType, true);
/*      */     }
/*  105 */     clearCache(paramControlledElementContainer.controlledType);
/*      */ 
/*  107 */     if (bool) {
/*  108 */       paramControlledElementContainer.send = false;
/*  109 */       if (this.needsControllerUpdates) {
/*  110 */         assert (paramControlledElementContainer.controlledType != 1);
/*  111 */         this.delayedControllerUpdates.add(paramControlledElementContainer);
/*      */       }
/*      */     }
/*  114 */     if ((!this.sendableSegmentController.isOnServer()) && (ElementKeyMap.getFactorykeyset().contains(Short.valueOf(paramControlledElementContainer.controlledType))) && 
/*  115 */       (((ct)this.sendableSegmentController.getState()).a() != null)) {
/*  116 */       ((ct)this.sendableSegmentController.getState()).a().a().a(this.sendableSegmentController);
/*      */     }
/*      */ 
/*  121 */     return bool; } 
/*      */   private void addControlChain(long paramLong, short paramShort, jD paramjD) { // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: invokevirtual 282	org/schema/game/common/data/element/ControlElementMap:getControllingMap	()Lorg/schema/game/common/data/element/ControlElementMapper;
/*      */     //   4: lload_1
/*      */     //   5: invokevirtual 300	org/schema/game/common/data/element/ControlElementMapper:get	(J)Ljava/lang/Object;
/*      */     //   8: checkcast 57	it/unimi/dsi/fastutil/shorts/Short2ObjectOpenHashMap
/*      */     //   11: dup
/*      */     //   12: astore 5
/*      */     //   14: ifnull +166 -> 180
/*      */     //   17: iload_3
/*      */     //   18: sipush 32767
/*      */     //   21: if_icmpne +121 -> 142
/*      */     //   24: aload 4
/*      */     //   26: getfield 119	jD:jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet	Lit/unimi/dsi/fastutil/objects/ObjectOpenHashSet;
/*      */     //   29: aload_0
/*      */     //   30: invokevirtual 282	org/schema/game/common/data/element/ControlElementMap:getControllingMap	()Lorg/schema/game/common/data/element/ControlElementMapper;
/*      */     //   33: invokevirtual 301	org/schema/game/common/data/element/ControlElementMapper:getAll	()Lit/unimi/dsi/fastutil/longs/Long2ObjectOpenHashMap;
/*      */     //   36: lload_1
/*      */     //   37: invokevirtual 176	it/unimi/dsi/fastutil/longs/Long2ObjectOpenHashMap:get	(J)Ljava/lang/Object;
/*      */     //   40: checkcast 79	java/util/Collection
/*      */     //   43: invokevirtual 184	it/unimi/dsi/fastutil/objects/ObjectOpenHashSet:addAll	(Ljava/util/Collection;)Z
/*      */     //   46: pop
/*      */     //   47: aload_0
/*      */     //   48: invokevirtual 282	org/schema/game/common/data/element/ControlElementMap:getControllingMap	()Lorg/schema/game/common/data/element/ControlElementMapper;
/*      */     //   51: invokevirtual 302	org/schema/game/common/data/element/ControlElementMapper:getControllers	()Lit/unimi/dsi/fastutil/longs/Long2ObjectOpenHashMap;
/*      */     //   54: lload_1
/*      */     //   55: invokevirtual 176	it/unimi/dsi/fastutil/longs/Long2ObjectOpenHashMap:get	(J)Ljava/lang/Object;
/*      */     //   58: checkcast 55	it/unimi/dsi/fastutil/objects/ObjectOpenHashSet
/*      */     //   61: dup
/*      */     //   62: astore_1
/*      */     //   63: ifnull +78 -> 141
/*      */     //   66: new 105	q
/*      */     //   69: invokespecial 325	q:<init>	()V
/*      */     //   72: aload_1
/*      */     //   73: invokevirtual 188	it/unimi/dsi/fastutil/objects/ObjectOpenHashSet:iterator	()Ljava/util/Iterator;
/*      */     //   76: astore_1
/*      */     //   77: aload_1
/*      */     //   78: invokeinterface 338 1 0
/*      */     //   83: ifeq +58 -> 141
/*      */     //   86: aload_1
/*      */     //   87: invokeinterface 339 1 0
/*      */     //   92: checkcast 60	ja
/*      */     //   95: astore_2
/*      */     //   96: aload_0
/*      */     //   97: getfield 138	org/schema/game/common/data/element/ControlElementMap:loopMap	Ljava/util/HashSet;
/*      */     //   100: aload_2
/*      */     //   101: invokevirtual 244	java/util/HashSet:contains	(Ljava/lang/Object;)Z
/*      */     //   104: ifne +34 -> 138
/*      */     //   107: aload_0
/*      */     //   108: getfield 138	org/schema/game/common/data/element/ControlElementMap:loopMap	Ljava/util/HashSet;
/*      */     //   111: aload_2
/*      */     //   112: invokevirtual 241	java/util/HashSet:add	(Ljava/lang/Object;)Z
/*      */     //   115: pop
/*      */     //   116: aload_0
/*      */     //   117: aload_2
/*      */     //   118: getfield 121	ja:jdField_a_of_type_Int	I
/*      */     //   121: aload_2
/*      */     //   122: getfield 123	ja:b	I
/*      */     //   125: aload_2
/*      */     //   126: getfield 124	ja:c	I
/*      */     //   129: invokestatic 311	org/schema/game/common/data/element/ElementCollection:getIndex	(III)J
/*      */     //   132: iload_3
/*      */     //   133: aload 4
/*      */     //   135: invokespecial 270	org/schema/game/common/data/element/ControlElementMap:addControlChain	(JSLjD;)V
/*      */     //   138: goto -61 -> 77
/*      */     //   141: return
/*      */     //   142: aload 5
/*      */     //   144: iload_3
/*      */     //   145: invokevirtual 190	it/unimi/dsi/fastutil/shorts/Short2ObjectOpenHashMap:containsKey	(S)Z
/*      */     //   148: ifeq +32 -> 180
/*      */     //   151: aload_0
/*      */     //   152: invokevirtual 282	org/schema/game/common/data/element/ControlElementMap:getControllingMap	()Lorg/schema/game/common/data/element/ControlElementMapper;
/*      */     //   155: lload_1
/*      */     //   156: invokevirtual 300	org/schema/game/common/data/element/ControlElementMapper:get	(J)Ljava/lang/Object;
/*      */     //   159: checkcast 57	it/unimi/dsi/fastutil/shorts/Short2ObjectOpenHashMap
/*      */     //   162: iload_3
/*      */     //   163: invokevirtual 192	it/unimi/dsi/fastutil/shorts/Short2ObjectOpenHashMap:get	(S)Ljava/lang/Object;
/*      */     //   166: checkcast 55	it/unimi/dsi/fastutil/objects/ObjectOpenHashSet
/*      */     //   169: astore_1
/*      */     //   170: aload 4
/*      */     //   172: getfield 119	jD:jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet	Lit/unimi/dsi/fastutil/objects/ObjectOpenHashSet;
/*      */     //   175: aload_1
/*      */     //   176: invokevirtual 184	it/unimi/dsi/fastutil/objects/ObjectOpenHashSet:addAll	(Ljava/util/Collection;)Z
/*      */     //   179: pop
/*      */     //   180: return } 
/*  211 */   public void addControllerForElement(q paramq1, q paramq2, short paramShort) { if (this.sendableSegmentController.isOnServer())
/*      */     {
/*      */       le localle;
/*  213 */       if (((
/*  213 */         localle = this.sendableSegmentController.getSegmentBuffer().a(paramq1, true, this.pointUnsaveTmp)) == null) || 
/*  213 */         (localle.a() == 0)) {
/*  214 */         System.err.println("add controller failed: " + localle);
/*  215 */         return;
/*      */       }
/*      */     }
/*      */ 
/*  219 */     addControllerForElementForced(paramq1, paramq2, paramShort); }
/*      */ 
/*      */   public void addControllerForElementForced(q paramq1, q paramq2, short paramShort)
/*      */   {
/*  223 */     addControl(paramq1, paramq2, paramShort, true);
/*  224 */     if (this.currentBuildController != null)
/*  225 */       this.currentBuildController.b();
/*      */   }
/*      */ 
/*      */   public void addDelayed(ControlledElementContainer paramControlledElementContainer)
/*      */   {
/*  232 */     synchronized (this.delayedNTUpdates) {
/*  233 */       if (!this.delayedNTUpdates.contains(paramControlledElementContainer)) {
/*  234 */         this.delayedNTUpdates.add(paramControlledElementContainer);
/*      */       }
/*  236 */       return;
/*      */     }
/*      */   }
/*      */ 
/*  240 */   private void cache(q paramq, short paramShort, jD paramjD) { if (!this.positionControlMapChache.containsKey(paramq)) {
/*  241 */       this.positionControlMapChache.put(new q(paramq), new HashMap());
/*      */     }
/*      */ 
/*  244 */     ((Map)this.positionControlMapChache.get(paramq)).put(Short.valueOf(paramjD.jdField_a_of_type_Short), paramjD);
/*      */   }
/*      */ 
/*      */   private void clearCache(short paramShort)
/*      */   {
/*  257 */     for (Iterator localIterator = this.positionControlMapChache.entrySet().iterator(); localIterator.hasNext(); )
/*      */     {
/*      */       Map.Entry localEntry;
/*  259 */       if (((Map)(
/*  259 */         localEntry = (Map.Entry)localIterator.next())
/*  259 */         .getValue()).containsKey(Short.valueOf(paramShort))) {
/*  260 */         ((jD)((Map)localEntry.getValue()).get(Short.valueOf(paramShort))).jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.clear();
/*      */       }
/*      */     }
/*  263 */     this.positionControlMapChache.clear();
/*      */   }
/*      */ 
/*      */   public Set getAllControlledElements(short paramShort) {
/*  267 */     HashSet localHashSet = new HashSet();
/*      */ 
/*  269 */     for (Iterator localIterator = getControllingMap().keySet().iterator(); localIterator.hasNext(); ) { long l = ((Long)localIterator.next()).longValue();
/*      */ 
/*  272 */       if ((
/*  272 */         localObject = (ObjectOpenHashSet)((Short2ObjectOpenHashMap)getControllingMap().get(l))
/*  271 */         .get(paramShort)) != null)
/*      */       {
/*  273 */         for (localObject = ((ObjectOpenHashSet)localObject).iterator(); ((Iterator)localObject).hasNext(); )
/*      */         {
/*      */           ja localja;
/*  274 */           if ((
/*  274 */             localja = (ja)((Iterator)localObject).next()).jdField_a_of_type_Short == 
/*  274 */             paramShort)
/*  275 */             localHashSet.add(localja);
/*      */         }
/*      */       }
/*      */     }
/*      */     Object localObject;
/*  281 */     return localHashSet;
/*      */   }
/*      */ 
/*      */   private jD getChached(q paramq, short paramShort) {
/*  285 */     return (jD)((Map)this.positionControlMapChache.get(paramq)).get(Short.valueOf(paramShort));
/*      */   }
/*      */ 
/*      */   public jD getControlledElements(short paramShort, q paramq) {
/*      */     try {
/*  290 */       if (isCached(paramq, paramShort))
/*      */       {
/*  292 */         return getChached(paramq, paramShort);
/*      */       }
/*      */ 
/*      */     }
/*      */     catch (NullPointerException localNullPointerException)
/*      */     {
/*  297 */       localNullPointerException.printStackTrace();
/*      */ 
/*  296 */       System.err.println("Exception successfully catched. rebuilding cache");
/*      */     }
/*      */     jD localjD;
/*  298 */     (
/*  300 */       localjD = new jD()).jdField_a_of_type_Short = 
/*  300 */       paramShort;
/*      */ 
/*  302 */     this.loopMap.clear();
/*  303 */     addControlChain(ElementCollection.getIndex(paramq), paramShort, localjD);
/*      */ 
/*  305 */     cache(paramq, paramShort, localjD);
/*      */ 
/*  307 */     return localjD;
/*      */   }
/*      */ 
/*      */   public ControlElementMapper getControllingMap()
/*      */   {
/*  314 */     return this.controllingMap;
/*      */   }
/*      */ 
/*      */   public ka getSegmentController()
/*      */   {
/*  320 */     return this.sendableSegmentController;
/*      */   }
/*      */ 
/*      */   public String getUniqueIdentifier() {
/*  324 */     return "ControlElementMap";
/*      */   }
/*      */ 
/*      */   public void handleReceived() {
/*  328 */     if ((!getSegmentController().isOnServer()) && (!this.initialStructureReceived) && (!this.flagRequested))
/*      */     {
/*  330 */       return;
/*      */     }
/*      */ 
/*  334 */     synchronized ((
/*  334 */       localRemoteArrayBuffer = this.sendableSegmentController.getNetworkObject().controlledByBuffer)
/*  334 */       .getReceiveBuffer())
/*      */     {
/*      */       RemoteArrayBuffer localRemoteArrayBuffer;
/*  335 */       for (int i = 0; i < localRemoteArrayBuffer.getReceiveBuffer().size(); i++) {
/*  336 */         Object localObject2 = (RemoteIntegerArray)localRemoteArrayBuffer.getReceiveBuffer().get(i);
/*      */ 
/*  340 */         q localq1 = new q(((Integer)((RemoteIntegerArray)localObject2).get(0).get()).intValue(), ((Integer)((RemoteIntegerArray)localObject2).get(1).get()).intValue(), ((Integer)((RemoteIntegerArray)localObject2).get(2).get()).intValue());
/*      */ 
/*  342 */         q localq2 = new q(((Integer)((RemoteIntegerArray)localObject2).get(3).get()).intValue(), ((Integer)((RemoteIntegerArray)localObject2).get(4).get()).intValue(), ((Integer)((RemoteIntegerArray)localObject2).get(5).get()).intValue());
/*      */ 
/*  344 */         short s = ((Integer)((RemoteIntegerArray)localObject2).get(6).get()).shortValue();
/*      */ 
/*  346 */         localObject2 = new ControlledElementContainer(ElementCollection.getIndex(localq1), localq2, s, ((Integer)((RemoteIntegerArray)localObject2).get(7).get()).intValue() == 1, this.sendableSegmentController.isOnServer());
/*  347 */         addDelayed((ControlledElementContainer)localObject2);
/*  350 */       }
/*      */ localRemoteArrayBuffer.getReceiveBuffer().clear();
/*      */       return;
/*      */     }
/*      */   }
/*      */ 
/*      */   private boolean isCached(q paramq, short paramShort) {
/*  356 */     return (this.positionControlMapChache.containsKey(paramq)) && (((Map)this.positionControlMapChache.get(paramq)).containsKey(Short.valueOf(paramShort)));
/*      */   }
/*      */ 
/*      */   public boolean isControlling(q paramq1, q paramq2, short paramShort)
/*      */   {
/*  361 */     long l = ElementCollection.getIndex(paramq1);
/*  362 */     if (getControllingMap().containsKey(l)) {
/*  363 */       this.elementPositionTmp.a(paramq2, paramShort);
/*      */ 
/*  369 */       return (((Short2ObjectOpenHashMap)getControllingMap().get(l)).containsKey(paramShort)) && (((ObjectOpenHashSet)((Short2ObjectOpenHashMap)getControllingMap().get(l)).get(paramShort)).contains(this.elementPositionTmp));
/*      */     }
/*      */ 
/*  372 */     return false;
/*      */   }
/*      */ 
/*      */   public boolean isVolatile()
/*      */   {
/*  377 */     return false;
/*      */   }
/*      */   public void executeDelayedRemove(s params) {
/*  380 */     long l = ElementCollection.getIndex(params.jdField_a_of_type_Int, params.b, params.c);
/*  381 */     removeControlledFromAllControllers(l, (short)params.d, true, false);
/*      */ 
/*  383 */     getControllingMap().remove(l);
/*      */ 
/*  391 */     if (this.currentBuildController != null)
/*  392 */       this.currentBuildController.b();
/*      */   }
/*      */ 
/*      */   public void onRemoveElement(q paramq, short paramShort) {
/*  396 */     synchronized (this.toRemoveControlled) {
/*  397 */       this.toRemoveControlled.add(new s(paramq, paramShort));
/*  398 */       return;
/*      */     }
/*      */   }
/*      */ 
/*      */   private boolean removeControlled(long paramLong, q paramq, short paramShort, boolean paramBoolean)
/*      */   {
/*  416 */     boolean bool = false;
/*      */ 
/*  418 */     if ((getControllingMap().containsKey(paramLong)) && 
/*  419 */       (((Short2ObjectOpenHashMap)getControllingMap().get(paramLong)).containsKey(paramShort)))
/*      */     {
/*  421 */       if ((
/*  421 */         bool = getControllingMap().remove(paramLong, paramq, paramShort)))
/*      */       {
/*  422 */         if ((!this.sendableSegmentController.isOnServer()) && (ElementKeyMap.getFactorykeyset().contains(Short.valueOf(paramShort))) && 
/*  423 */           (((ct)this.sendableSegmentController.getState()).a() != null)) {
/*  424 */           ((ct)this.sendableSegmentController.getState()).a().a().a(this.sendableSegmentController);
/*      */         }
/*      */ 
/*  427 */         ControlledElementContainer localControlledElementContainer = new ControlledElementContainer(paramLong, paramq, paramShort, false, paramBoolean);
/*  428 */         if (this.needsControllerUpdates) {
/*  429 */           assert (localControlledElementContainer.controlledType != 1);
/*  430 */           this.delayedControllerUpdates.add(localControlledElementContainer);
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  448 */     if ((paramBoolean) && (bool) && (this.sendableSegmentController != null)) {
/*  449 */       send(paramLong, paramq, paramShort, false);
/*      */     }
/*      */ 
/*  452 */     if (bool) {
/*  453 */       clearCache(paramShort);
/*      */     }
/*  455 */     if (paramShort != 32767) {
/*  456 */       removeControlled(paramLong, paramq, (short)32767, false);
/*      */     }
/*  458 */     return bool;
/*      */   }
/*      */ 
/*      */   public void removeControlledFromAll(q paramq, short paramShort, boolean paramBoolean) {
/*  462 */     removeControlledFromAllControllers(ElementCollection.getIndex(paramq), paramShort, false, paramBoolean);
/*      */ 
/*  465 */     if (this.currentBuildController != null)
/*  466 */       this.currentBuildController.b();
/*      */   }
/*      */ 
/*      */   private boolean removeControlledFromAllControllers(long paramLong, short paramShort, boolean paramBoolean1, boolean paramBoolean2)
/*      */   {
/*  475 */     if ((paramShort != 0) && (ElementKeyMap.getInfo(paramShort).getControlledBy().size() == 0))
/*      */     {
/*  477 */       return false;
/*      */     }
/*      */ 
/*  480 */     if ((!paramBoolean1) && (ElementKeyMap.getFactorykeyset().contains(Short.valueOf(paramShort))))
/*      */     {
/*  482 */       return false;
/*      */     }
/*      */ 
/*  486 */     paramBoolean1 = false;
/*  487 */     q localq = new q();
/*  488 */     for (Iterator localIterator = getControllingMap().keySet().iterator(); localIterator.hasNext(); ) { long l = ((Long)localIterator.next()).longValue();
/*      */ 
/*  490 */       paramBoolean1 = removeControlled(l, ElementCollection.getPosFromIndex(paramLong, localq), paramShort, (paramBoolean2) || (getSegmentController().isOnServer())) | paramBoolean1;
/*      */     }
/*      */ 
/*  495 */     return paramBoolean1;
/*      */   }
/*      */ 
/*      */   public void removeControllerForElement(q paramq1, q paramq2, short paramShort)
/*      */   {
/*  501 */     removeControlled(ElementCollection.getIndex(paramq1), paramq2, paramShort, true);
/*      */ 
/*  504 */     if (this.currentBuildController != null)
/*  505 */       this.currentBuildController.b();
/*      */   }
/*      */ 
/*      */   private void send(long paramLong, q paramq, short paramShort, boolean paramBoolean)
/*      */   {
/*  512 */     synchronized (this.sendableSegmentController.getNetworkObject())
/*      */     {
/*  514 */       RemoteIntegerArray localRemoteIntegerArray = new RemoteIntegerArray(8, this.sendableSegmentController.getNetworkObject());
/*      */ 
/*  522 */       q localq = new q();
/*  523 */       ElementCollection.getPosFromIndex(paramLong, localq);
/*  524 */       localRemoteIntegerArray.set(0, Integer.valueOf(localq.jdField_a_of_type_Int));
/*  525 */       localRemoteIntegerArray.set(1, Integer.valueOf(localq.b));
/*  526 */       localRemoteIntegerArray.set(2, Integer.valueOf(localq.c));
/*      */ 
/*  528 */       localRemoteIntegerArray.set(3, Integer.valueOf(paramq.jdField_a_of_type_Int));
/*  529 */       localRemoteIntegerArray.set(4, Integer.valueOf(paramq.b));
/*  530 */       localRemoteIntegerArray.set(5, Integer.valueOf(paramq.c));
/*      */ 
/*  532 */       localRemoteIntegerArray.set(6, Integer.valueOf(paramShort));
/*  533 */       localRemoteIntegerArray.set(7, Integer.valueOf(paramBoolean ? 1 : 0));
/*      */ 
/*  535 */       this.sendableSegmentController.getNetworkObject().controlledByBuffer.add(localRemoteIntegerArray);
/*      */ 
/*  537 */       return;
/*      */     }
/*      */   }
/*      */ 
/*      */   public void deserializeZipped(DataInputStream paramDataInputStream)
/*      */   {
/*  561 */     int i = paramDataInputStream.readInt();
/*      */ 
/*  566 */     DataInputStream localDataInputStream1 = paramDataInputStream.readInt();
/*  567 */     DataInputStream localDataInputStream2 = paramDataInputStream.readInt();
/*      */     byte[] arrayOfByte1;
/*      */     Inflater localInflater;
/*      */     byte[] arrayOfByte2;
/*  568 */     if ((getSegmentController() == null) || (getSegmentController().isOnServer())) {
/*  569 */       arrayOfByte1 = vg.jdField_a_of_type_ArrayOfByte;
/*  570 */       localInflater = vg.jdField_a_of_type_JavaUtilZipInflater;
/*  571 */       arrayOfByte2 = vg.a(localDataInputStream1);
/*      */     } else {
/*  573 */       arrayOfByte1 = ct.jdField_a_of_type_ArrayOfByte;
/*  574 */       localInflater = ct.jdField_a_of_type_JavaUtilZipInflater;
/*  575 */       arrayOfByte2 = ct.a(localDataInputStream1);
/*      */     }
/*      */ 
/*  583 */     assert (localDataInputStream2 <= arrayOfByte1.length) : (localDataInputStream2 + "/" + arrayOfByte1.length);
/*  584 */     synchronized (arrayOfByte1) {
/*  585 */       paramDataInputStream = paramDataInputStream.read(arrayOfByte1, 0, localDataInputStream2);
/*      */ 
/*  587 */       assert (paramDataInputStream == localDataInputStream2) : (paramDataInputStream + "/" + localDataInputStream2);
/*      */ 
/*  601 */       if (localDataInputStream2 == 0) {
/*  602 */         System.err.println("[CONTROLSTRUCTURE] WARNING: controlstructure deserializing with 0 data " + getSegmentController());
/*      */       }
/*  604 */       localInflater.reset();
/*      */ 
/*  606 */       localInflater.setInput(arrayOfByte1, 0, localDataInputStream2);
/*      */       try
/*      */       {
/*  611 */         paramDataInputStream = localInflater.inflate(arrayOfByte2, 0, localDataInputStream1);
/*      */ 
/*  613 */         assert (paramDataInputStream == localDataInputStream1) : (paramDataInputStream + " / " + localDataInputStream1 + "; " + i);
/*      */ 
/*  615 */         deserialize(arrayOfByte2, 0, localDataInputStream1);
/*  616 */         if (paramDataInputStream == 0)
/*  617 */           System.err.println("[CONTROLSTRUCTURE] WARNING: INFLATED BYTES 0: " + localInflater.needsInput() + " " + localInflater.needsDictionary());
/*      */       }
/*      */       catch (DataFormatException paramDataInputStream)
/*      */       {
/*  621 */         System.err.println("Exception in " + getSegmentController());
/*  622 */         paramDataInputStream.printStackTrace();
/*      */       }
/*      */       catch (IOException paramDataInputStream) {
/*  625 */         System.err.println("Exception in " + getSegmentController() + "; DEBUG-ID: " + i);
/*  626 */         throw paramDataInputStream;
/*      */       }
/*      */       return;
/*      */     }
/*      */   }
/*      */ 
/*      */   public void deserialize(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*      */   {
/*  634 */     paramArrayOfByte = new FastByteArrayInputStream(paramArrayOfByte, paramInt1, paramInt2);
/*  635 */     paramArrayOfByte = new DataInputStream(paramArrayOfByte);
/*  636 */     deserialize(paramArrayOfByte);
/*      */   }
/*      */ 
/*      */   public static void deserialize(DataInputStream paramDataInputStream, ControlElementMapper paramControlElementMapper)
/*      */   {
/*  641 */     int i = paramDataInputStream.readInt();
/*      */ 
/*  643 */     for (int j = 0; j < i; j++) {
/*  644 */       int k = paramDataInputStream.readShort();
/*  645 */       int m = paramDataInputStream.readShort();
/*  646 */       short s = paramDataInputStream.readShort();
/*  647 */       long l = ElementCollection.getIndex(k, m, s);
/*  648 */       k = paramDataInputStream.readInt();
/*      */ 
/*  650 */       for (m = 0; m < k; m++)
/*      */       {
/*  652 */         s = paramDataInputStream.readShort();
/*      */ 
/*  654 */         int n = paramDataInputStream.readInt();
/*      */ 
/*  656 */         for (int i1 = 0; i1 < n; i1++) {
/*  657 */           q localq = new q(paramDataInputStream.readShort(), paramDataInputStream.readShort(), paramDataInputStream.readShort());
/*  658 */           paramControlElementMapper.put(l, localq, s);
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public void deserialize(DataInputStream paramDataInputStream)
/*      */   {
/*  666 */     long l1 = System.currentTimeMillis();
/*  667 */     int i = paramDataInputStream.readInt();
/*      */ 
/*  669 */     synchronized (this.delayedNTUpdatesMap)
/*      */     {
/*  672 */       for (int j = 0; j < i; j++) {
/*  673 */         int k = paramDataInputStream.readShort();
/*  674 */         int m = paramDataInputStream.readShort();
/*  675 */         short s = paramDataInputStream.readShort();
/*  676 */         long l3 = ElementCollection.getIndex(k, m, s);
/*      */ 
/*  678 */         k = paramDataInputStream.readInt();
/*      */ 
/*  680 */         for (m = 0; m < k; m++)
/*      */         {
/*  682 */           s = paramDataInputStream.readShort();
/*      */ 
/*  684 */           int n = paramDataInputStream.readInt();
/*      */ 
/*  686 */           for (int i1 = 0; i1 < n; i1++) {
/*  687 */             q localq = new q(paramDataInputStream.readShort(), paramDataInputStream.readShort(), paramDataInputStream.readShort());
/*      */ 
/*  689 */             this.delayedNTUpdatesMap.put(l3, localq, s);
/*  690 */             this.structureCompleteChange = true;
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     long l2;
/*  697 */     if ((
/*  697 */       l2 = System.currentTimeMillis() - l1) > 
/*  697 */       10L)
/*  698 */       System.err.println("RECEIVED " + i + " CONTROL ELEMENT MAP ENTRIES FOR " + getSegmentController() + " TOOK " + l2);
/*      */   }
/*      */ 
/*      */   public int serializeZipped(DataOutputStream paramDataOutputStream)
/*      */   {
/*  704 */     paramDataOutputStream.writeInt(getSegmentController().getId());
/*      */ 
/*  709 */     int j = getByteSize(getControllingMap());
/*      */     byte[] arrayOfByte1;
/*      */     Deflater localDeflater;
/*      */     byte[] arrayOfByte2;
/*  710 */     if (getSegmentController().isOnServer()) {
/*  711 */       arrayOfByte1 = vg.jdField_a_of_type_ArrayOfByte;
/*  712 */       localDeflater = vg.jdField_a_of_type_JavaUtilZipDeflater;
/*  713 */       arrayOfByte2 = vg.a(j);
/*      */     } else {
/*  715 */       arrayOfByte1 = ct.jdField_a_of_type_ArrayOfByte;
/*  716 */       localDeflater = ct.jdField_a_of_type_JavaUtilZipDeflater;
/*  717 */       arrayOfByte2 = ct.a(j);
/*      */     }
/*      */ 
/*  720 */     synchronized (arrayOfByte1)
/*      */     {
/*  722 */       serialize(arrayOfByte2, j);
/*  723 */       paramDataOutputStream.writeInt(j);
/*      */ 
/*  726 */       localDeflater.reset();
/*  727 */       localDeflater.setInput(arrayOfByte2, 0, j);
/*  728 */       localDeflater.finish();
/*  729 */       int i = localDeflater.deflate(arrayOfByte1);
/*      */ 
/*  731 */       paramDataOutputStream.writeInt(i);
/*      */ 
/*  733 */       paramDataOutputStream.write(arrayOfByte1, 0, i);
/*      */     }
/*      */ 
/*  741 */     return 1;
/*      */   }
/*      */ 
/*      */   public static void serialize(DataOutputStream paramDataOutputStream, ControlElementMapper paramControlElementMapper) {
/*  745 */     paramDataOutputStream.writeInt(paramControlElementMapper.size());
/*      */ 
/*  747 */     for (paramControlElementMapper = paramControlElementMapper.entrySet().iterator(); paramControlElementMapper.hasNext(); )
/*      */     {
/*  750 */       ElementCollection.writeIndexAsShortPos(((Long)(
/*  750 */         localObject1 = (Map.Entry)paramControlElementMapper.next())
/*  750 */         .getKey()).longValue(), paramDataOutputStream);
/*      */ 
/*  752 */       int i = ((Short2ObjectOpenHashMap)((Map.Entry)localObject1).getValue()).size();
/*  753 */       paramDataOutputStream.writeInt(i);
/*      */ 
/*  757 */       for (localObject1 = ((Short2ObjectOpenHashMap)((Map.Entry)localObject1).getValue())
/*  757 */         .entrySet().iterator(); ((Iterator)localObject1).hasNext(); ) { localObject2 = (Map.Entry)((Iterator)localObject1).next();
/*      */ 
/*  759 */         paramDataOutputStream.writeShort(((Short)((Map.Entry)localObject2).getKey()).shortValue());
/*      */ 
/*  761 */         paramDataOutputStream.writeInt(((ObjectOpenHashSet)((Map.Entry)localObject2).getValue()).size());
/*      */ 
/*  763 */         for (localObject2 = ((ObjectOpenHashSet)((Map.Entry)localObject2).getValue()).iterator(); ((Iterator)localObject2).hasNext(); ) { ja localja = (ja)((Iterator)localObject2).next();
/*  764 */           paramDataOutputStream.writeShort(localja.jdField_a_of_type_Int);
/*  765 */           paramDataOutputStream.writeShort(localja.b);
/*  766 */           paramDataOutputStream.writeShort(localja.c); } }
/*      */     }
/*      */     Object localObject1;
/*      */     Object localObject2;
/*      */   }
/*      */ 
/*      */   public void serialize(byte[] paramArrayOfByte, int paramInt) {
/*  774 */     paramArrayOfByte = new FastByteArrayOutputStream(paramArrayOfByte);
/*  775 */     DataOutputStream localDataOutputStream = new DataOutputStream(paramArrayOfByte);
/*  776 */     long l1 = paramArrayOfByte.position();
/*  777 */     serialize(localDataOutputStream, getControllingMap());
/*      */ 
/*  779 */     long l2 = paramArrayOfByte.position() - 
/*  779 */       l1;
/*  780 */     assert (l2 == paramInt) : (l2 + "/" + paramInt + " in " + getSegmentController() + "; total " + this.controllingMap.size() + "; " + this.controllingMap.getAll().size());
/*      */   }
/*      */ 
/*      */   public static int getByteSize(ControlElementMapper paramControlElementMapper)
/*      */   {
/*  785 */     int i = 0;
/*  786 */     i += 4;
/*      */ 
/*  788 */     for (paramControlElementMapper = paramControlElementMapper.entrySet().iterator(); paramControlElementMapper.hasNext(); ) { localObject1 = (Map.Entry)paramControlElementMapper.next();
/*      */ 
/*  790 */       i += 10;
/*      */ 
/*  799 */       for (localObject1 = ((Short2ObjectOpenHashMap)((Map.Entry)localObject1).getValue())
/*  799 */         .entrySet().iterator(); ((Iterator)localObject1).hasNext(); ) { localObject2 = (Map.Entry)((Iterator)localObject1).next();
/*  800 */         i += 6;
/*      */ 
/*  805 */         for (localObject2 = ((ObjectOpenHashSet)((Map.Entry)localObject2).getValue()).iterator(); ((Iterator)localObject2).hasNext(); ) { ((Iterator)localObject2).next();
/*  806 */           i += 6;
/*      */         }
/*      */       }
/*      */     }
/*      */     Object localObject1;
/*      */     Object localObject2;
/*  814 */     return i;
/*      */   }
/*      */ 
/*      */   private boolean checkDeserialize(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt1, int paramInt2)
/*      */   {
/*  820 */     paramArrayOfByte2 = new ControlElementMap();
/*  821 */     ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream(paramInt2 + 12);
/*      */     DataOutputStream localDataOutputStream;
/*  822 */     (
/*  823 */       localDataOutputStream = new DataOutputStream(localByteArrayOutputStream))
/*  823 */       .writeInt(getSegmentController().getId());
/*  824 */     localDataOutputStream.writeInt(paramInt2);
/*  825 */     localDataOutputStream.writeInt(paramInt1);
/*  826 */     localDataOutputStream.write(paramArrayOfByte1);
/*      */ 
/*  828 */     paramArrayOfByte1 = new DataInputStream(new ByteArrayInputStream(localByteArrayOutputStream.toByteArray()));
/*  829 */     paramArrayOfByte2.testMode = true;
/*  830 */     paramArrayOfByte2.deserializeZipped(paramArrayOfByte1);
/*  831 */     paramArrayOfByte2.testMode = false;
/*  832 */     return true;
/*      */   }
/*      */ 
/*      */   public void setSendableSegmentController(ka paramka)
/*      */   {
/*  841 */     this.needsControllerUpdates = (paramka instanceof ld);
/*  842 */     this.sendableSegmentController = paramka;
/*      */   }
/*      */ 
/*      */   public void switchControllerForElement(q paramq1, q paramq2, short paramShort)
/*      */   {
/*  848 */     if (isControlling(paramq1, paramq2, paramShort))
/*      */     {
/*  850 */       removeControllerForElement(paramq1, paramq2, paramShort);
/*      */     }
/*      */     else {
/*  853 */       removeControlledFromAll(paramq2, paramShort, true);
/*      */ 
/*  857 */       addControllerForElement(paramq1, paramq2, paramShort);
/*      */     }
/*      */ 
/*  861 */     if (this.currentBuildController != null)
/*  862 */       this.currentBuildController.b();
/*      */   }
/*      */ 
/*      */   public static ControlElementMapper mapFromTag(Ad paramAd, ControlElementMapper paramControlElementMapper)
/*      */   {
/*      */     q localq;
/*      */     int k;
/*      */     Object localObject2;
/*  868 */     if ("cs0".equals(paramAd.a())) {
/*  869 */       System.err.println("[ControlElementMap] WARNING: OLD TAG NAME (cs0): " + paramAd.a());
/*  870 */       paramAd = (Ad[])paramAd.a();
/*  871 */       for (i = 0; i < paramAd.length - 1; i++)
/*      */       {
/*      */         Object localObject1;
/*  873 */         localq = (q)(
/*  873 */           localObject1 = (Ad[])paramAd[i].a())[
/*  873 */           0].a();
/*      */ 
/*  875 */         k = (
/*  875 */           localObject1 = (byte[])localObject1[1].a()).length / 
/*  875 */           14;
/*  876 */         localObject2 = new DataInputStream(new ByteArrayInputStream((byte[])localObject1));
/*      */         try
/*      */         {
/*  879 */           for (int j = 0; j < k; j++) {
/*  880 */             int m = ((DataInputStream)localObject2).readInt();
/*  881 */             int n = ((DataInputStream)localObject2).readInt();
/*  882 */             int i1 = ((DataInputStream)localObject2).readInt();
/*      */             short s;
/*  884 */             if (ElementKeyMap.exists(s = ((DataInputStream)localObject2).readShort()))
/*      */             {
/*  885 */               paramControlElementMapper.put(localq, new q(m, n, i1), s);
/*      */             }
/*      */           }
/*      */         }
/*      */         catch (IOException localIOException) {
/*  890 */           localIOException.printStackTrace();
/*      */         }
/*      */       }
/*      */ 
/*  892 */       return paramControlElementMapper;
/*  893 */     }if ("cs1".equals(paramAd.a()))
/*      */     {
/*  895 */       return (ControlElementMapper)paramAd.a();
/*      */     }
/*      */ 
/*  897 */     System.err.println("[ControlElementMap] WARNING: OLD TAG NAME: " + paramAd.a());
/*  898 */     paramAd = (Ad[])paramAd.a();
/*  899 */     for (int i = 0; (i < paramAd.length) && 
/*  900 */       (paramAd[i].a() != Af.a); i++)
/*      */     {
/*  904 */       localq = (q)(
/*  904 */         arrayOfAd = (Ad[])paramAd[i].a())[
/*  904 */         0].a();
/*  905 */       Ad[] arrayOfAd = (Ad[])arrayOfAd[1].a();
/*  906 */       for (k = 0; (k < arrayOfAd.length) && 
/*  907 */         (arrayOfAd[k].a() != Af.a); k++)
/*      */       {
/*  913 */         if (ElementKeyMap.exists(((Short)(
/*  913 */           localObject2 = (Ad[])arrayOfAd[k].a())[
/*  913 */           1].a()).shortValue())) {
/*  914 */           paramControlElementMapper.put(localq, (q)localObject2[0].a(), ((Short)localObject2[1].a()).shortValue());
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  923 */     return paramControlElementMapper;
/*      */   }
/*      */ 
/*      */   public void fromTagStructure(Ad paramAd)
/*      */   {
/*  932 */     if ("cs1".equals(paramAd.a())) {
/*  933 */       this.controllingMap = ((ControlElementMapper)paramAd.a()); return;
/*  934 */     }if ("cs0".equals(paramAd.a())) {
/*  935 */       mapFromTag(paramAd, this.controllingMap); return;
/*      */     }
/*  937 */     mapFromTag(paramAd, this.controllingMap);
/*  938 */     this.controllingMapCheck = this.controllingMap.keySet().iterator();
/*  939 */     System.err.println("[SERVER][ONTROL-ELEMENT-MAP][TAG][OLD] ADDED CONTROLLER FROM TAG. MAP NOW: " + getSegmentController() + ". CONTROLLER MAP SIZE: " + this.delayedNTUpdates.size());
/*      */   }
/*      */ 
/*      */   public static Ad mapToTag(ControlElementMapper paramControlElementMapper)
/*      */   {
/*  946 */     return new Ad(Af.o, "cs1", paramControlElementMapper);
/*      */   }
/*      */ 
/*      */   public Ad toTagStructure()
/*      */   {
/* 1001 */     return mapToTag(this.controllingMap);
/*      */   }
/*      */ 
/*      */   public void updateLocal(xq paramxq)
/*      */   {
/*      */     Object localObject2;
/*      */     Object localObject3;
/* 1006 */     if (this.structureCompleteChange)
/*      */     {
/*      */       ld localld1;
/*      */       Object localObject1;
/*      */       q localq;
/* 1008 */       synchronized (this.delayedNTUpdatesMap)
/*      */       {
/* 1010 */         this.controllingMap.clear();
/* 1011 */         this.controllingMap.set(this.delayedNTUpdatesMap);
/*      */ 
/* 1013 */         this.delayedNTUpdatesMap.clearAndTrim();
/* 1014 */         this.structureCompleteChange = false;
/*      */ 
/* 1016 */         this.initialStructureReceived = true;
/* 1017 */         if ((!this.sendableSegmentController.isOnServer()) && 
/* 1018 */           (((ct)this.sendableSegmentController.getState()).a() != null)) {
/* 1019 */           ((ct)this.sendableSegmentController.getState()).a().a().a(this.sendableSegmentController);
/*      */         }
/*      */ 
/* 1022 */         localld1 = (ld)this.sendableSegmentController;
/* 1023 */         localObject1 = this.controllingMap.getControllers();
/* 1024 */         localq = new q();
/* 1025 */         for (localObject2 = ((Long2ObjectOpenHashMap)localObject1).long2ObjectEntrySet().iterator(); ((Iterator)localObject2).hasNext(); ) {
/* 1026 */           for (localObject3 = ((ObjectOpenHashSet)(
/* 1026 */             paramxq = (Long2ObjectMap.Entry)((Iterator)localObject2).next())
/* 1026 */             .getValue()).iterator(); ((Iterator)localObject3).hasNext(); ) { localObject1 = (ja)((Iterator)localObject3).next();
/*      */             try
/*      */             {
/* 1029 */               localld1.a().onControllerAdded(ElementCollection.getPosFromIndex(paramxq.getLongKey(), localq), new q(((ja)localObject1).jdField_a_of_type_Int, ((ja)localObject1).b, ((ja)localObject1).c), ((ja)localObject1).jdField_a_of_type_Short);
/*      */             } catch (CannotImmediateRequestOnClientException localCannotImmediateRequestOnClientException1) {
/* 1031 */               localObject1 = new ControlledElementContainer(paramxq.getLongKey(), new q(((ja)localObject1).jdField_a_of_type_Int, ((ja)localObject1).b, ((ja)localObject1).c), ((ja)localObject1).jdField_a_of_type_Short, true, false);
/*      */ 
/* 1033 */               this.failedDelayedUpdates.add(localObject1);
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/* 1039 */     if (!this.delayedNTUpdates.isEmpty()) {
/* 1040 */       ??? = this.delayedNTUpdates.size();
/* 1041 */       long l2 = System.currentTimeMillis();
/* 1042 */       synchronized (this.delayedNTUpdates) {
/* 1043 */         for (localObject2 = this.delayedNTUpdates.iterator(); ((Iterator)localObject2).hasNext(); ) {
/* 1044 */           if ((
/* 1044 */             paramxq = (ControlledElementContainer)((Iterator)localObject2).next()).add)
/*      */           {
/* 1045 */             addControl(paramxq);
/*      */           }
/* 1047 */           else removeControlled(paramxq.from, paramxq.to, paramxq.controlledType, paramxq.send);
/*      */         }
/*      */ 
/* 1050 */         this.delayedNTUpdates.clear();
/*      */       }
/*      */       long l3;
/* 1053 */       if ((
/* 1053 */         l3 = System.currentTimeMillis() - l2) > 
/* 1053 */         5L) {
/* 1054 */         System.err.println("[CONTROLELEMENTMAP][" + getSegmentController().getState() + "] NTUPDATE " + getSegmentController() + " took " + l3 + " for " + ??? + " elements");
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1059 */     if (this.addedDouble != null) {
/* 1060 */       System.err.println("[WARNING][CONTROLELEMENTMAP] DOUBLE CONTROL SENT " + this.sendableSegmentController.getState() + ": " + this.sendableSegmentController + "; " + this.addedDouble.from + "; " + this.addedDouble.to + "; ty " + this.addedDouble.controlledType + "; add " + this.addedDouble.add + "; send " + this.addedDouble.send);
/* 1061 */       this.addedDouble = null;
/*      */     }
/* 1063 */     if (!this.toRemoveControlled.isEmpty())
/* 1064 */       synchronized (this.toRemoveControlled) {
/* 1065 */         for (int i = 0; i < this.toRemoveControlled.size(); i++) {
/* 1066 */           executeDelayedRemove((s)this.toRemoveControlled.get(i));
/*      */         }
/* 1068 */         this.toRemoveControlled.clear();
/*      */       }
/*      */     long l1;
/* 1072 */     if (!this.delayedControllerUpdates.isEmpty()) {
/* 1073 */       l1 = System.currentTimeMillis();
/* 1074 */       int j = this.delayedControllerUpdates.size();
/*      */       ld localld2;
/* 1076 */       if (this.needsControllerUpdates) {
/* 1077 */         localld2 = (ld)this.sendableSegmentController;
/* 1078 */         localObject2 = new q();
/* 1079 */         for (paramxq = this.delayedControllerUpdates.iterator(); paramxq.hasNext(); )
/*      */         {
/* 1081 */           if ((
/* 1081 */             localObject3 = (ControlledElementContainer)paramxq.next()).controlledType != 
/* 1081 */             32767) {
/*      */             try {
/* 1083 */               if (((ControlledElementContainer)localObject3).add)
/* 1084 */                 localld2.a().onControllerAdded(ElementCollection.getPosFromIndex(((ControlledElementContainer)localObject3).from, (q)localObject2), ((ControlledElementContainer)localObject3).to, ((ControlledElementContainer)localObject3).controlledType);
/*      */               else
/* 1086 */                 localld2.a().onControllerRemoved(ElementCollection.getPosFromIndex(((ControlledElementContainer)localObject3).from, (q)localObject2), ((ControlledElementContainer)localObject3).to, ((ControlledElementContainer)localObject3).controlledType);
/*      */             }
/*      */             catch (CannotImmediateRequestOnClientException localCannotImmediateRequestOnClientException2) {
/* 1089 */               this.failedDelayedUpdates.add(localObject3);
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */ 
/* 1095 */       this.delayedControllerUpdates.clear();
/* 1096 */       this.delayedControllerUpdates.addAll(this.failedDelayedUpdates);
/* 1097 */       this.failedDelayedUpdates.clear();
/*      */       long l4;
/* 1100 */       if ((
/* 1100 */         l4 = System.currentTimeMillis() - l1) > 
/* 1100 */         5L) {
/* 1101 */         System.err.println("[CONTROLELEMENTMAP][" + getSegmentController().getState() + "] DELAYED " + getSegmentController() + " took " + l4 + " for " + j + " elements. failed: " + this.failedDelayedUpdates.size());
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1111 */     if (this.controllingMapCheck != null)
/*      */       try
/*      */       {
/* 1114 */         if (this.controllingMapCheck.hasNext()) {
/* 1115 */           l1 = ((Long)this.controllingMapCheck.next()).longValue();
/*      */           le localle;
/* 1118 */           if (((
/* 1118 */             localle = this.sendableSegmentController.getSegmentBuffer().a(ElementCollection.getPosFromIndex(l1, new q()), true, this.pointUnsaveTmp)) == null) || 
/* 1118 */             (localle.a() == 0))
/*      */           {
/* 1120 */             System.err.println("Exception: REMOVING DUE TO CONTROLLING MAP CHECK: " + l1);
/* 1121 */             this.controllingMapCheck.remove();
/*      */           }
/* 1123 */           return;
/* 1124 */         }this.controllingMapCheck = null;
/*      */         return;
/*      */       } catch (ConcurrentModificationException localConcurrentModificationException) {
/* 1128 */         this.controllingMapCheck = getControllingMap().keySet().iterator();
/*      */       }
/*      */   }
/*      */ 
/*      */   public static void getMapInstance(Ad paramAd)
/*      */   {
/*      */   }
/*      */ 
/*      */   public void setObs(af paramaf)
/*      */   {
/* 1139 */     this.currentBuildController = paramaf;
/*      */   }
/*      */ 
/*      */   public void set(ControlElementMapper paramControlElementMapper) {
/* 1143 */     this.controllingMap.set(paramControlElementMapper);
/*      */   }
/*      */ 
/*      */   public boolean isFlagRequested()
/*      */   {
/* 1150 */     return this.flagRequested;
/*      */   }
/*      */ 
/*      */   public void setFlagRequested(boolean paramBoolean)
/*      */   {
/* 1157 */     this.flagRequested = paramBoolean;
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.ControlElementMap
 * JD-Core Version:    0.6.2
 */