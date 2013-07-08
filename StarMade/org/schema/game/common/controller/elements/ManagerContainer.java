/*    1:     */package org.schema.game.common.controller.elements;
/*    2:     */
/*    3:     */import Ah;
/*    4:     */import Aj;
/*    5:     */import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*    6:     */import it.unimi.dsi.fastutil.objects.ObjectArrayFIFOQueue;
/*    7:     */import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*    8:     */import jE;
/*    9:     */import jL;
/*   10:     */import java.io.PrintStream;
/*   11:     */import java.util.ArrayList;
/*   12:     */import java.util.Collection;
/*   13:     */import java.util.HashMap;
/*   14:     */import java.util.HashSet;
/*   15:     */import java.util.Iterator;
/*   16:     */import java.util.Map.Entry;
/*   17:     */import java.util.Set;
/*   18:     */import lA;
/*   19:     */import le;
/*   20:     */import md;
/*   21:     */import mf;
/*   22:     */import mh;
/*   23:     */import mi;
/*   24:     */import mk;
/*   25:     */import ml;
/*   26:     */import mo;
/*   27:     */import org.schema.game.common.controller.SegmentController;
/*   28:     */import org.schema.game.common.controller.elements.dockingBlock.DockingElementManagerInterface;
/*   29:     */import org.schema.game.common.controller.elements.dockingBlock.DockingMetaDataDummy;
/*   30:     */import org.schema.game.common.controller.elements.shield.ShieldCollectionManager;
/*   31:     */import org.schema.game.common.data.element.ElementInformation;
/*   32:     */import org.schema.game.common.data.element.ElementKeyMap;
/*   33:     */import org.schema.game.common.data.element.PointDistributionTagDummy;
/*   34:     */import org.schema.game.common.data.world.Segment;
/*   35:     */import org.schema.game.network.objects.remote.RemoteInventory;
/*   36:     */import org.schema.game.network.objects.remote.RemoteInventoryBuffer;
/*   37:     */import org.schema.game.network.objects.remote.RemoteInventoryMultMod;
/*   38:     */import org.schema.game.network.objects.remote.RemoteInventoryMultModBuffer;
/*   39:     */import org.schema.schine.network.NetworkStateContainer;
/*   40:     */import org.schema.schine.network.StateInterface;
/*   41:     */import org.schema.schine.network.objects.NetworkObject;
/*   42:     */import org.schema.schine.network.objects.Sendable;
/*   43:     */import org.schema.schine.network.objects.remote.RemoteIntArray;
/*   44:     */import org.schema.schine.network.objects.remote.RemoteIntArrayBuffer;
/*   45:     */import q;
/*   46:     */import s;
/*   47:     */import xq;
/*   48:     */
/*   55:     */public abstract class ManagerContainer
/*   56:     */  implements mh
/*   57:     */{
/*   58:  58 */  protected final ArrayList modules = new ArrayList();
/*   59:  59 */  protected final ArrayList handleModules = new ArrayList();
/*   60:  60 */  private final ArrayList receiverModules = new ArrayList();
/*   61:  61 */  private final ArrayList updatableModules = new ArrayList();
/*   62:  62 */  private final ArrayList distributionReceiverModules = new ArrayList();
/*   63:  63 */  private final ArrayList hittableModules = new ArrayList();
/*   64:  64 */  private final ArrayList senderModules = new ArrayList();
/*   65:  65 */  private final ArrayList changeListenModules = new ArrayList();
/*   66:  66 */  private final ArrayList blockActivateListenModules = new ArrayList();
/*   67:  67 */  private final HashMap modulesMap = new HashMap();
/*   68:  68 */  private final HashMap modulesControllerMap = new HashMap();
/*   69:  69 */  protected Collection dockingModules = new ArrayList();
/*   70:     */  
/*   72:  72 */  private final Set activeInventories = new HashSet();
/*   73:  73 */  private final ArrayList delayedInventoryAdd = new ArrayList();
/*   74:  74 */  private final ArrayList timedUpdateInterfaces = new ArrayList();
/*   75:  75 */  private final ArrayList delayedInventoryRemove = new ArrayList();
/*   76:  76 */  private final HashMap inventories = new HashMap();
/*   77:  77 */  private final ArrayList initialPointDists = new ArrayList();
/*   78:  78 */  private final ArrayList initialBlockMetaData = new ArrayList();
/*   79:  79 */  private final ObjectArrayFIFOQueue ntInventoryMods = new ObjectArrayFIFOQueue();
/*   80:  80 */  private final ObjectArrayFIFOQueue ntInventoryMultMods = new ObjectArrayFIFOQueue();
/*   81:  81 */  private final ObjectArrayFIFOQueue ntInventorySingleMods = new ObjectArrayFIFOQueue();
/*   82:  82 */  private final ObjectArrayFIFOQueue ntActiveInventorySingleMods = new ObjectArrayFIFOQueue();
/*   83:     */  
/*   84:     */  private final SegmentController segmentController;
/*   85:     */  
/*   86:     */  private boolean flagAnyBlockAdded;
/*   87:     */  private boolean flagAnyBlockRemoved;
/*   88:  88 */  public boolean loadInventoriesFromTag = true;
/*   89:     */  
/*   91:     */  public ManagerContainer(SegmentController paramSegmentController)
/*   92:     */  {
/*   93:  93 */    this.segmentController = paramSegmentController;
/*   94:  94 */    initialize();
/*   95:  95 */    for (paramSegmentController = 0; paramSegmentController < this.modules.size(); paramSegmentController++)
/*   96:     */    {
/*   97:     */      ManagerModule localManagerModule;
/*   98:     */      Object localObject;
/*   99:  99 */      if (((localManagerModule = (ManagerModule)this.modules.get(paramSegmentController)) instanceof ManagerModuleCollection)) {
/*  100: 100 */        localObject = (ManagerModuleCollection)localManagerModule;
/*  101: 101 */        assert (!getModulesMap().containsKey(Short.valueOf(((ManagerModuleCollection)localObject).getControllerID()))) : (localObject + "; " + getModulesMap());
/*  102:     */        
/*  103: 103 */        getModulesMap().put(Short.valueOf(((ManagerModuleCollection)localObject).getControllerID()), localObject);
/*  104:     */        
/*  105: 105 */        assert (!getModulesControllerMap().containsKey(Short.valueOf(((ManagerModuleCollection)localObject).getControllerID()))) : (localObject + "; " + getModulesMap());
/*  106: 106 */        getModulesControllerMap().put(Short.valueOf(((ManagerModuleCollection)localObject).getControllerID()), localObject);
/*  107:     */        
/*  108: 108 */        if ((((ManagerModuleCollection)localObject).getElementManager() instanceof TimedUpdateInterface)) {
/*  109: 109 */          this.timedUpdateInterfaces.add((TimedUpdateInterface)((ManagerModuleCollection)localObject).getElementManager());
/*  110:     */        }
/*  111: 111 */        if ((((ManagerModuleCollection)localObject).getElementManager() instanceof BlockActivationListenerInterface)) {
/*  112: 112 */          this.blockActivateListenModules.add((BlockActivationListenerInterface)((ManagerModuleCollection)localObject).getElementManager());
/*  113:     */        }
/*  114:     */      }
/*  115:     */      
/*  116: 116 */      if (getModulesMap().containsKey(Short.valueOf(localManagerModule.getElementID())))
/*  117:     */      {
/*  118: 118 */        localObject = (ManagerModule)getModulesMap().get(Short.valueOf(localManagerModule.getElementID()));
/*  119: 119 */        while (((ManagerModule)localObject).getNext() != null) {
/*  120: 120 */          localObject = ((ManagerModule)localObject).getNext();
/*  121:     */        }
/*  122: 122 */        ((ManagerModule)localObject).setNext(localManagerModule);
/*  123:     */      }
/*  124:     */      else {
/*  125: 125 */        getModulesMap().put(Short.valueOf(localManagerModule.getElementID()), localManagerModule);
/*  126:     */      }
/*  127:     */      
/*  128: 128 */      if ((localManagerModule instanceof ManagerModuleSingle))
/*  129:     */      {
/*  130: 130 */        localObject = ((ManagerModuleSingle)localManagerModule).getCollectionManager();
/*  131: 131 */        addTypeModifiers(localObject);
/*  132:     */      }
/*  133:     */      
/*  134: 134 */      if (!(localManagerModule.getElementManager() instanceof VoidElementManager)) {
/*  135: 135 */        this.handleModules.add(localManagerModule);
/*  136:     */      }
/*  137: 137 */      if ((localManagerModule.getElementManager() instanceof DockingElementManagerInterface)) {
/*  138: 138 */        this.dockingModules.add((ManagerModuleCollection)localManagerModule);
/*  139:     */      }
/*  140: 140 */      addTypeModifiers(localManagerModule.getElementManager());
/*  141:     */    }
/*  142:     */  }
/*  143:     */  
/*  144:     */  private void addInventory(byte paramByte1, byte paramByte2, byte paramByte3, Segment paramSegment, short paramShort)
/*  145:     */  {
/*  146: 146 */    if (getSegmentController().isOnServer())
/*  147:     */    {
/*  153: 153 */      paramByte1 = paramSegment.a(paramByte1, paramByte2, paramByte3, new q());
/*  154: 154 */      if ((!getInventories().containsKey(paramByte1)) && (!this.delayedInventoryAdd.contains(paramByte1)))
/*  155:     */      {
/*  156: 156 */        if (paramShort == 120) {
/*  157: 157 */          this.delayedInventoryAdd.add(new mo(this, paramByte1));return; }
/*  158: 158 */        if (paramShort == 114) {
/*  159: 159 */          this.delayedInventoryAdd.add(new mk(this, paramByte1));return; }
/*  160: 160 */        if (ElementKeyMap.getFactorykeyset().contains(Short.valueOf(paramShort)))
/*  161: 161 */          this.delayedInventoryAdd.add(new mo(this, paramByte1));
/*  162:     */      }
/*  163:     */    }
/*  164:     */  }
/*  165:     */  
/*  166:     */  private void addTypeModifiers(Object paramObject) {
/*  167: 167 */    if ((paramObject instanceof UpdatableCollectionManager)) {
/*  168: 168 */      this.updatableModules.add((UpdatableCollectionManager)paramObject);
/*  169:     */    }
/*  170:     */    
/*  171: 171 */    if ((paramObject instanceof NTReceiveInterface)) {
/*  172: 172 */      this.receiverModules.add((NTReceiveInterface)paramObject);
/*  173:     */    }
/*  174: 174 */    if ((paramObject instanceof NTDistributionReceiverInterface)) {
/*  175: 175 */      this.distributionReceiverModules.add((NTDistributionReceiverInterface)paramObject);
/*  176:     */    }
/*  177: 177 */    if ((paramObject instanceof BlockActivationListenerInterface)) {
/*  178: 178 */      this.blockActivateListenModules.add((BlockActivationListenerInterface)paramObject);
/*  179:     */    }
/*  180: 180 */    if ((paramObject instanceof HittableInterface)) {
/*  181: 181 */      this.hittableModules.add((HittableInterface)paramObject);
/*  182:     */    }
/*  183: 183 */    if ((paramObject instanceof NTSenderInterface)) {
/*  184: 184 */      this.senderModules.add((NTSenderInterface)paramObject);
/*  185:     */    }
/*  186: 186 */    if ((paramObject instanceof ElementChangeListenerInterface)) {
/*  187: 187 */      this.changeListenModules.add((ElementChangeListenerInterface)paramObject);
/*  188:     */    }
/*  189:     */  }
/*  190:     */  
/*  191:     */  private void announceInventory(q paramq, boolean paramBoolean1, mf parammf, boolean paramBoolean2) {
/*  192: 192 */    synchronized (getInventories())
/*  193:     */    {
/*  194: 194 */      if (paramBoolean2) {
/*  195: 195 */        getInventories().put(paramq, parammf);
/*  196: 196 */        paramq = new RemoteInventory(parammf, this, paramBoolean2, getSegmentController().isOnServer());
/*  197: 197 */        getInventoryInterface().getInventoriesChangeBuffer().add(paramq);
/*  198:     */      } else {
/*  199: 199 */        System.err.println("ANNOUNEC INVENTORY REMOVE ON " + getSegmentController() + ": " + paramBoolean1);
/*  200:     */        
/*  201: 201 */        if ((paramq = (mf)getInventories().remove(paramq)) != null) {
/*  202: 202 */          onInventoryRemove(paramq, paramBoolean1);
/*  203: 203 */          paramq = new RemoteInventory(paramq, this, paramBoolean2, getSegmentController().isOnServer());
/*  204: 204 */          getInventoryInterface().getInventoriesChangeBuffer().add(paramq);
/*  205:     */        }
/*  206:     */      }
/*  207: 207 */      return;
/*  208:     */    }
/*  209:     */  }
/*  210:     */  
/*  214:     */  public void clear()
/*  215:     */  {
/*  216: 216 */    for (int i = 0; i < this.modules.size(); i++) {
/*  217: 217 */      ((ManagerModule)this.modules.get(i)).clear();
/*  218:     */    }
/*  219:     */  }
/*  220:     */  
/*  221:     */  private void fromTagDistribution(Ah paramAh) {
/*  222: 222 */    paramAh = (Ah[])paramAh.a();
/*  223: 223 */    for (int i = 0; (i < paramAh.length) && (paramAh[i].a() != Aj.a); i++)
/*  224:     */    {
/*  225: 225 */      Ah[] arrayOfAh = (Ah[])paramAh[i].a();
/*  226: 226 */      assert (arrayOfAh != null) : paramAh[i].a();
/*  227:     */      int j;
/*  228:     */      Object localObject;
/*  229: 229 */      if (("ElementCollection".equals(paramAh[i].a())) || ("D".equals(paramAh[i].a())))
/*  230:     */      {
/*  233: 233 */        for (j = 0; (j < arrayOfAh.length) && (arrayOfAh[j].a() != Aj.a); j++)
/*  234:     */        {
/*  235: 235 */          (localObject = new PointDistributionTagDummy()).fromTagStructure(arrayOfAh[j]);
/*  236: 236 */          getInitialPointDists().add(localObject);
/*  237:     */        }
/*  238: 238 */      } else if ("A".equals(paramAh[i].a())) {
/*  239: 239 */        for (j = 0; (j < arrayOfAh.length) && (arrayOfAh[j].a() != Aj.a); j++)
/*  240:     */        {
/*  241: 241 */          (localObject = new DockingMetaDataDummy()).fromTagStructure(arrayOfAh[j]);
/*  242: 242 */          getInitialBlockMetaData().add(localObject);
/*  243:     */        }
/*  244:     */      }
/*  245:     */    }
/*  246:     */  }
/*  247:     */  
/*  251:     */  private void fromTagInventory(Ah paramAh)
/*  252:     */  {
/*  253: 253 */    paramAh = (Ah[])paramAh.a();
/*  254: 254 */    for (int i = 0; i < paramAh.length; i++) {
/*  255: 255 */      if (paramAh[i].a() == Aj.a) {
/*  256:     */        break;
/*  257:     */      }
/*  258:     */      
/*  259:     */      Ah[] arrayOfAh;
/*  260: 260 */      int j = ((Integer)(arrayOfAh = (Ah[])paramAh[i].a())[0].a()).intValue();
/*  261: 261 */      q localq = (q)arrayOfAh[1].a();
/*  262:     */      
/*  263: 263 */      Object localObject = null;
/*  264:     */      
/*  265: 265 */      if (j == 3) {
/*  266: 266 */        localObject = new mo(this, localq);
/*  267:     */      }
/*  268: 268 */      if (j == 1) {
/*  269: 269 */        localObject = new mk(this, localq);
/*  270:     */      }
/*  271: 271 */      assert (localObject != null) : ("unknown type: " + j);
/*  272: 272 */      if (this.loadInventoriesFromTag) {
/*  273: 273 */        ((mf)localObject).fromTagStructure(arrayOfAh[2]);
/*  274:     */      } else {
/*  275: 275 */        System.err.println("[TAG] " + getSegmentController() + " not loading inventory");
/*  276:     */      }
/*  277:     */      
/*  278: 278 */      getInventories().put(((mf)localObject).a(), localObject);
/*  279:     */    }
/*  280:     */  }
/*  281:     */  
/*  286:     */  public ArrayList getInitialPointDists()
/*  287:     */  {
/*  288: 288 */    return this.initialPointDists;
/*  289:     */  }
/*  290:     */  
/*  291:     */  public mf getInventory(q paramq)
/*  292:     */  {
/*  293: 293 */    return (mf)getInventories().get(paramq);
/*  294:     */  }
/*  295:     */  
/*  296:     */  private ml getInventoryInterface() {
/*  297: 297 */    return (ml)getSegmentController().getNetworkObject();
/*  298:     */  }
/*  299:     */  
/*  300:     */  public String getName() {
/*  301: 301 */    return this.segmentController.getUniqueIdentifier();
/*  302:     */  }
/*  303:     */  
/*  306:     */  public SegmentController getSegmentController()
/*  307:     */  {
/*  308: 308 */    return this.segmentController;
/*  309:     */  }
/*  310:     */  
/*  314:     */  public StateInterface getState()
/*  315:     */  {
/*  316: 316 */    return this.segmentController.getState();
/*  317:     */  }
/*  318:     */  
/*  319:     */  public void handle(lA paramlA) {
/*  320: 320 */    for (int i = 0; i < this.handleModules.size(); i++) {
/*  321: 321 */      if (((ManagerModule)this.handleModules.get(i)).getElementManager().canHandle(paramlA)) {
/*  322: 322 */        ((ManagerModule)this.handleModules.get(i)).getElementManager().handle(paramlA);
/*  323:     */      }
/*  324:     */    }
/*  325:     */  }
/*  326:     */  
/*  329:     */  public void handleBlockActivate(le paramle, boolean paramBoolean)
/*  330:     */  {
/*  331: 331 */    System.err.println("BlockActivationListenerInterfaces: " + this.blockActivateListenModules.size() + " on " + getClass().getSimpleName());
/*  332: 332 */    for (Iterator localIterator = this.blockActivateListenModules.iterator(); localIterator.hasNext();) {
/*  333: 333 */      ((BlockActivationListenerInterface)localIterator.next()).onActivate(paramle, paramBoolean);
/*  334:     */    }
/*  335:     */  }
/*  336:     */  
/*  340:     */  public void onAction() {}
/*  341:     */  
/*  345:     */  public void handleInventoryFromNT(RemoteInventoryBuffer arg1)
/*  346:     */  {
/*  347: 347 */    ??? = ???.getReceiveBuffer();
/*  348:     */    
/*  349: 349 */    for (int i = 0; i < ???.size(); i++) {
/*  350: 350 */      mf localmf = (mf)((RemoteInventory)???.get(i)).get();
/*  351: 351 */      synchronized (this.ntInventoryMods) {
/*  352: 352 */        this.ntInventoryMods.enqueue(localmf);
/*  353:     */      }
/*  354:     */    }
/*  355:     */    
/*  358: 358 */    ObjectArrayList localObjectArrayList1 = getInventoryInterface().getInventoryUpdateBuffer().getReceiveBuffer();
/*  359:     */    
/*  362: 362 */    for (int j = 0; j < localObjectArrayList1.size(); j++) {
/*  363: 363 */      ??? = (RemoteIntArray)localObjectArrayList1.get(j);
/*  364: 364 */      synchronized (this.ntInventorySingleMods) {
/*  365: 365 */        this.ntInventorySingleMods.enqueue(???);
/*  366:     */      }
/*  367:     */    }
/*  368:     */    
/*  372: 372 */    ObjectArrayList localObjectArrayList2 = getInventoryInterface().getInventoryMultModBuffer().getReceiveBuffer();
/*  373:     */    
/*  376: 376 */    for (j = 0; j < localObjectArrayList2.size(); j++) {
/*  377: 377 */      ??? = (RemoteInventoryMultMod)localObjectArrayList2.get(j);
/*  378: 378 */      synchronized (this.ntInventoryMultMods) {
/*  379: 379 */        this.ntInventoryMultMods.enqueue(((RemoteInventoryMultMod)???).get());
/*  380:     */      }
/*  381:     */    }
/*  382:     */    
/*  386: 386 */    ObjectArrayList localObjectArrayList3 = getInventoryInterface().getInventoryActivateBuffer().getReceiveBuffer();
/*  387: 387 */    for (j = 0; j < localObjectArrayList3.size(); j++) {
/*  388: 388 */      ??? = (RemoteIntArray)localObjectArrayList3.get(j);
/*  389: 389 */      synchronized (this.ntActiveInventorySingleMods) {
/*  390: 390 */        this.ntActiveInventorySingleMods.enqueue(???);
/*  391:     */      }
/*  392:     */    }
/*  393:     */  }
/*  394:     */  
/*  397:     */  protected abstract void initialize();
/*  398:     */  
/*  401:     */  public void onAddedElement(short paramShort, int paramInt, byte paramByte1, byte paramByte2, byte paramByte3, Segment paramSegment)
/*  402:     */  {
/*  403: 403 */    if (ElementKeyMap.getFactorykeyset().contains(Short.valueOf(paramShort))) {
/*  404: 404 */      addInventory(paramByte1, paramByte2, paramByte3, paramSegment, paramShort);
/*  405:     */    }
/*  406:     */    
/*  408: 408 */    if ((paramInt = (ManagerModuleCollection)getModulesControllerMap().get(Short.valueOf(paramShort))) != null) {
/*  409: 409 */      paramInt.addControllerBlock(paramByte1, paramByte2, paramByte3, paramSegment);
/*  412:     */    }
/*  413: 413 */    else if (((paramInt = (ManagerModule)getModulesMap().get(Short.valueOf(paramShort))) != null) && ((paramInt instanceof ManagerModuleSingle)))
/*  414:     */    {
/*  416: 416 */      ((ManagerModuleSingle)paramInt).addElement(paramByte1, paramByte2, paramByte3, paramSegment);
/*  417:     */    }
/*  418:     */    else {
/*  419: 419 */      assert (paramShort != 2);
/*  420: 420 */      switch (paramShort)
/*  421:     */      {
/*  433:     */      case 120: 
/*  434: 434 */        addInventory(paramByte1, paramByte2, paramByte3, paramSegment, paramShort);
/*  435: 435 */        break;
/*  436:     */      
/*  438:     */      case 114: 
/*  439: 439 */        addInventory(paramByte1, paramByte2, paramByte3, paramSegment, paramShort);
/*  440:     */      }
/*  441:     */      
/*  442:     */    }
/*  443:     */    
/*  448: 448 */    this.flagAnyBlockAdded = true;
/*  449:     */  }
/*  450:     */  
/*  454:     */  public void onControllerAdded(q paramq1, q paramq2, short paramShort)
/*  455:     */  {
/*  456: 456 */    ManagerModule localManagerModule = (ManagerModule)getModulesMap().get(Short.valueOf(paramShort));
/*  457: 457 */    assert (localManagerModule != null) : ("critical: no module found for " + getSegmentController() + ": " + paramShort + "; " + getModulesMap());
/*  458: 458 */    if (localManagerModule.getNext() != null)
/*  459:     */    {
/*  461: 461 */      int i = getSegmentController().getSegmentBuffer().a(paramq1, true).a();
/*  462: 462 */      while (localManagerModule.getNext() != null) {
/*  463: 463 */        assert ((localManagerModule instanceof ManagerModuleCollection));
/*  464:     */        
/*  465: 465 */        if (((ManagerModuleCollection)localManagerModule).getControllerID() == i) break;
/*  466: 466 */        localManagerModule = localManagerModule.getNext();
/*  467:     */      }
/*  468:     */    }
/*  469:     */    
/*  473: 473 */    assert (localManagerModule != null) : (paramShort + " -> " + ElementKeyMap.getInfo(paramShort).getName() + ": " + getModulesMap());
/*  474: 474 */    if (localManagerModule == null) {
/*  475: 475 */      throw new NullPointerException("Could not find Manager Module for " + paramShort + ": " + getModulesMap());
/*  476:     */    }
/*  477:     */    
/*  478: 478 */    localManagerModule.addControlledBlock(paramq1, paramq2, paramShort);
/*  479:     */  }
/*  480:     */  
/*  481:     */  public void onControllerRemoved(q paramq1, q paramq2, short paramShort)
/*  482:     */  {
/*  483: 483 */    assert (getModulesMap() != null);
/*  484:     */    
/*  485: 485 */    assert (paramShort != 0);
/*  486:     */    
/*  487: 487 */    assert (getModulesMap().containsKey(Short.valueOf(paramShort)));
/*  488:     */    
/*  491: 491 */    if ((paramShort == 0) || (!getModulesMap().containsKey(Short.valueOf(paramShort)))) {
/*  492: 492 */      System.err.println("Exception: tried to remove controller: " + paramShort + ": " + getModulesMap() + " for " + getSegmentController()); return;
/*  493:     */    }
/*  494:     */    
/*  495:     */    ManagerModule localManagerModule;
/*  496:     */    
/*  497: 497 */    if ((localManagerModule = (ManagerModule)getModulesMap().get(Short.valueOf(paramShort))).getNext() != null)
/*  498:     */    {
/*  500: 500 */      int i = getSegmentController().getSegmentBuffer().a(paramq1, true).a();
/*  501: 501 */      while (localManagerModule.getNext() != null) {
/*  502: 502 */        assert ((localManagerModule instanceof ManagerModuleCollection));
/*  503:     */        
/*  504: 504 */        if (((ManagerModuleCollection)localManagerModule).getControllerID() == i) break;
/*  505: 505 */        localManagerModule = localManagerModule.getNext();
/*  506:     */      }
/*  507:     */    }
/*  508:     */    
/*  514: 514 */    assert (localManagerModule != null) : (paramShort + " -> " + ElementKeyMap.getInfo(paramShort).getName() + ": " + getModulesMap());
/*  515: 515 */    localManagerModule.removeControllerBlock(paramq1, paramq2, paramShort);
/*  516:     */  }
/*  517:     */  
/*  520:     */  public void onHit(int paramInt)
/*  521:     */  {
/*  522: 522 */    for (int i = 0; i < this.hittableModules.size(); i++) {
/*  523: 523 */      ((HittableInterface)this.hittableModules.get(i)).onHit(paramInt);
/*  524:     */    }
/*  525:     */  }
/*  526:     */  
/*  527:     */  public void onHitNotice() {}
/*  528:     */  
/*  529:     */  private void onInventoryRemove(mf parammf, boolean paramBoolean) {
/*  530: 530 */    if ((!paramBoolean) && (this.segmentController.isOnServer())) {
/*  531: 531 */      System.err.println("[MANAGERCONTAINER] REMOVING INVENTORY! (now spawning in space)" + getSegmentController().getState() + " - " + getSegmentController());
/*  532: 532 */      parammf.a(getSegmentController());
/*  533:     */    }
/*  534:     */  }
/*  535:     */  
/*  537:     */  public void onRemovedElement(short paramShort, int paramInt, byte paramByte1, byte paramByte2, byte paramByte3, Segment paramSegment, boolean paramBoolean)
/*  538:     */  {
/*  539: 539 */    if (ElementKeyMap.getFactorykeyset().contains(Short.valueOf(paramShort))) {
/*  540: 540 */      removeInventory(paramByte1, paramByte2, paramByte3, paramSegment, paramBoolean);
/*  541:     */    }
/*  542:     */    
/*  544: 544 */    if ((paramInt = (ManagerModuleCollection)getModulesControllerMap().get(Short.valueOf(paramShort))) != null) {
/*  545: 545 */      paramInt.removeController(paramByte1, paramByte2, paramByte3, paramSegment);
/*  548:     */    }
/*  549: 549 */    else if (((paramInt = (ManagerModule)getModulesMap().get(Short.valueOf(paramShort))) != null) && ((paramInt instanceof ManagerModuleSingle))) {
/*  550: 550 */      ((ManagerModuleSingle)paramInt).removeElement(paramByte1, paramByte2, paramByte3, paramSegment);
/*  551:     */    }
/*  552:     */    else {
/*  553: 553 */      switch (paramShort)
/*  554:     */      {
/*  565:     */      case 120: 
/*  566: 566 */        System.err.println("[MANAGER_CONTAINER] onRemovedElement REMOVING INVENTORY!! " + getSegmentController() + "; " + getSegmentController().getState() + " preserveC: " + paramBoolean);
/*  567: 567 */      case 114:  removeInventory(paramByte1, paramByte2, paramByte3, paramSegment, paramBoolean);
/*  568:     */      }
/*  569:     */      
/*  570:     */    }
/*  571:     */    
/*  578: 578 */    this.flagAnyBlockRemoved = true;
/*  579:     */  }
/*  580:     */  
/*  583:     */  public String printInventories()
/*  584:     */  {
/*  585: 585 */    return getInventories().toString();
/*  586:     */  }
/*  587:     */  
/*  588:     */  private void removeInventory(byte paramByte1, byte paramByte2, byte paramByte3, Segment paramSegment, boolean paramBoolean)
/*  589:     */  {
/*  590: 590 */    if (getSegmentController().isOnServer())
/*  591:     */    {
/*  597: 597 */      if (!paramBoolean) {
/*  598: 598 */        paramByte1 = paramSegment.a(paramByte1, paramByte2, paramByte3, new q());
/*  599: 599 */        if ((getInventories().containsKey(paramByte1)) && (!this.delayedInventoryRemove.contains(paramByte1))) {
/*  600: 600 */          this.delayedInventoryRemove.add(new s(paramByte1, paramBoolean ? 1 : 0));
/*  601:     */        }
/*  602: 602 */        return;
/*  603:     */      }
/*  604:     */      
/*  605: 605 */      System.err.println("[SERVER] keeping inventory for now so it's not emptied by anything " + getSegmentController());
/*  606:     */    }
/*  607:     */  }
/*  608:     */  
/*  610:     */  public void sendInventoryModification(int paramInt, q paramq)
/*  611:     */  {
/*  612:     */    RemoteIntArray localRemoteIntArray;
/*  613: 613 */    (localRemoteIntArray = new RemoteIntArray(6, getSegmentController().getNetworkObject())).set(0, paramInt);
/*  614:     */    
/*  615: 615 */    mf localmf = getInventory(paramq);
/*  616: 616 */    assert (localmf != null) : (paramq + " --- " + getInventories());
/*  617: 617 */    localRemoteIntArray.set(1, localmf.a(paramInt));
/*  618: 618 */    localRemoteIntArray.set(2, localmf.a(paramInt));
/*  619: 619 */    localRemoteIntArray.set(3, paramq.a);
/*  620: 620 */    localRemoteIntArray.set(4, paramq.b);
/*  621: 621 */    localRemoteIntArray.set(5, paramq.c);
/*  622: 622 */    getInventoryInterface().getInventoryUpdateBuffer().add(localRemoteIntArray);
/*  623:     */    try
/*  624:     */    {
/*  625: 625 */      if (getInventoryInterface().getInventoryUpdateBuffer().size() > 200) {
/*  626: 626 */        paramInt = (Sendable)getSegmentController().getState().getLocalAndRemoteObjectContainer().getLocalObjects().get(getSegmentController().getId());
/*  627: 627 */        paramq = (NetworkObject)getSegmentController().getState().getLocalAndRemoteObjectContainer().getRemoteObjects().get(getSegmentController().getId());
/*  628: 628 */        throw new IllegalArgumentException("WARNING: inventory high of " + getSegmentController() + ": " + getInventoryInterface().getInventoryUpdateBuffer().size() + "; " + paramInt + "; " + paramq);
/*  629:     */      }
/*  630:     */      return; } catch (IllegalArgumentException localIllegalArgumentException) { 
/*  631:     */      
/*  632: 632 */        localIllegalArgumentException;
/*  633:     */    }
/*  634:     */  }
/*  635:     */  
/*  637:     */  public void sendInventoryModification(Collection paramCollection, q paramq)
/*  638:     */  {
/*  639:     */    mf localmf;
/*  640:     */    
/*  641: 639 */    if ((localmf = getInventory(paramq)) != null) {
/*  642: 640 */      paramCollection = new mi(paramCollection, localmf, paramq);
/*  643:     */      
/*  644: 642 */      getInventoryInterface().getInventoryMultModBuffer().add(new RemoteInventoryMultMod(paramCollection, getSegmentController().getNetworkObject()));
/*  645: 643 */      return;
/*  646:     */    }
/*  647: 645 */    try { throw new IllegalArgumentException("[INVENTORY] Exception: tried to send inventory " + paramq);
/*  648: 646 */    } catch (Exception localException) { localException;
/*  649:     */    }
/*  650:     */  }
/*  651:     */  
/*  656:     */  public Ah toTagStructure()
/*  657:     */  {
/*  658:     */    Object localObject2;
/*  659:     */    
/*  663:     */    Object localObject3;
/*  664:     */    
/*  668: 666 */    synchronized (getInventories()) {
/*  669: 667 */      Ah[] arrayOfAh1 = new Ah[getInventories().size() + 1];
/*  670:     */      
/*  671: 669 */      int i = 0;
/*  672:     */      
/*  673: 671 */      for (localObject1 = getInventories().entrySet().iterator(); ((Iterator)localObject1).hasNext();) { localObject2 = (Map.Entry)((Iterator)localObject1).next();
/*  674:     */        
/*  675: 673 */        (
/*  676: 674 */          localObject3 = new Ah[4])[0] = new Ah(Aj.d, "type", Integer.valueOf(((mf)((Map.Entry)localObject2).getValue()).c()));
/*  677: 675 */        localObject3[1] = new Ah(Aj.k, "index", ((Map.Entry)localObject2).getKey());
/*  678: 676 */        localObject3[2] = ((mf)((Map.Entry)localObject2).getValue()).toTagStructure();
/*  679: 677 */        localObject3[3] = new Ah(Aj.a, "fin", null);
/*  680: 678 */        arrayOfAh1[i] = new Ah(Aj.n, "inventory", (Ah[])localObject3);
/*  681: 679 */        i++;
/*  682:     */      }
/*  683: 681 */      arrayOfAh1[getInventories().size()] = new Ah(Aj.a, "fin", null);
/*  684:     */    }
/*  685: 683 */    ??? = new Ah(Aj.n, "controllerStructure", arrayOfAh2);
/*  686:     */    
/*  687: 685 */    ArrayList localArrayList = new ArrayList();
/*  688: 686 */    for (Object localObject1 = this.modules.iterator(); ((Iterator)localObject1).hasNext();) {
/*  689: 687 */      if (((localObject2 = (ManagerModule)((Iterator)localObject1).next()).getElementManager() instanceof UsableDistributionControllableElementManager)) {
/*  690: 688 */        localObject3 = (UsableDistributionControllableElementManager)((ManagerModule)localObject2).getElementManager();
/*  691: 689 */        localArrayList.add(((UsableDistributionControllableElementManager)localObject3).toDistributionTagStructure());
/*  692:     */      }
/*  693:     */    }
/*  694:     */    
/*  695: 693 */    for (localObject1 = this.modules.iterator(); ((Iterator)localObject1).hasNext();) {
/*  696: 694 */      if (((localObject2 = (ManagerModule)((Iterator)localObject1).next()).getElementManager() instanceof UsableControllableElementManager))
/*  697:     */      {
/*  699: 697 */        if ((localObject3 = (UsableControllableElementManager)((ManagerModule)localObject2).getElementManager()).hasMetaData()) {
/*  700: 698 */          localArrayList.add(((UsableControllableElementManager)localObject3).toTagStructure());
/*  701:     */        }
/*  702:     */      }
/*  703:     */    }
/*  704:     */    
/*  705: 703 */    if (localArrayList.size() > 0)
/*  706:     */    {
/*  707: 705 */      localObject2 = new Ah[localArrayList.size() + 1];
/*  708: 706 */      for (int j = 0; j < localArrayList.size(); j++) {
/*  709: 707 */        localObject2[j] = ((Ah)localArrayList.get(j));
/*  710:     */      }
/*  711: 709 */      localObject2[(localObject2.length - 1)] = new Ah(Aj.a, null, null);
/*  712: 710 */      localObject1 = new Ah(Aj.n, "shipMan0", (Ah[])localObject2);
/*  713:     */    } else {
/*  714: 712 */      localObject1 = new Ah(Aj.d, "shipMan0", Integer.valueOf(0));
/*  715:     */    }
/*  716: 714 */    if ((this instanceof PowerManagerInterface))
/*  717:     */    {
/*  719: 717 */      localObject2 = ((PowerManagerInterface)this).getPowerAddOn().toTagStructure();
/*  720:     */    } else
/*  721: 719 */      localObject2 = new Ah(Aj.b, null, Integer.valueOf(0));
/*  722:     */    Ah localAh;
/*  723: 721 */    if ((this instanceof ShieldContainerInterface))
/*  724:     */    {
/*  725: 723 */      localAh = new Ah(Aj.g, "sh", Double.valueOf(((ShieldContainerInterface)this).getShieldManager().getShields()));
/*  726:     */    } else {
/*  727: 725 */      localAh = new Ah(Aj.b, null, Integer.valueOf(0));
/*  728:     */    }
/*  729:     */    
/*  733: 731 */    return new Ah(Aj.n, "container", new Ah[] { ???, localObject1, localObject2, localAh, new Ah(Aj.a, null, null) });
/*  734:     */  }
/*  735:     */  
/*  737:     */  public void fromTagStructure(Ah paramAh)
/*  738:     */  {
/*  739: 737 */    if (paramAh.a().equals("container")) {
/*  740: 738 */      paramAh = (Ah[])paramAh.a();
/*  741: 739 */      fromTagInventory(paramAh[0]);
/*  742: 740 */      if (paramAh[1].a() == Aj.n) {
/*  743: 741 */        Ah[] arrayOfAh = (Ah[])paramAh[1].a();
/*  744: 742 */        for (int j = 0; j < arrayOfAh.length; j++) {
/*  745: 743 */          if (arrayOfAh[j].a() != Aj.a)
/*  746:     */          {
/*  747: 745 */            fromTagDistribution(arrayOfAh[j]);
/*  748:     */          }
/*  749:     */        }
/*  750:     */      }
/*  751:     */      
/*  752: 750 */      if (((this instanceof PowerManagerInterface)) && (paramAh.length > 2) && ("pw".equals(paramAh[2].a())))
/*  753:     */      {
/*  755: 753 */        ((PowerManagerInterface)this).getPowerAddOn().fromTagStructure(paramAh[2]);
/*  756:     */      }
/*  757:     */      
/*  758: 756 */      if (((this instanceof ShieldContainerInterface)) && (paramAh.length > 3) && ("sh".equals(paramAh[3].a()))) {
/*  759: 757 */        ((ShieldContainerInterface)this).getShieldManager().setInitialShields(((Double)paramAh[3].a()).doubleValue());
/*  760:     */      }
/*  761: 759 */      return; } if (paramAh.a().equals("controllerStructure")) {
/*  762: 760 */      handleTag(paramAh);return;
/*  763:     */    }
/*  764: 762 */    paramAh = (Ah[])paramAh.a();
/*  765:     */    
/*  766: 764 */    for (int i = 0; i < paramAh.length; i++) {
/*  767: 765 */      if (paramAh[i].a() != Aj.a) {
/*  768: 766 */        handleTag(paramAh[i]);
/*  769:     */      }
/*  770:     */    }
/*  771:     */  }
/*  772:     */  
/*  775:     */  private void handleTag(Ah paramAh)
/*  776:     */  {
/*  777: 775 */    if (paramAh.a().equals("wepContr")) {
/*  778: 776 */      fromTagDistribution(paramAh);return; }
/*  779: 777 */    if (paramAh.a().equals("controllerStructure")) {
/*  780: 778 */      fromTagInventory(paramAh);return;
/*  781:     */    }
/*  782: 780 */    if (!$assertionsDisabled) { throw new AssertionError(paramAh.a());
/*  783:     */    }
/*  784:     */  }
/*  785:     */  
/*  786:     */  public void updateFromNetworkObject(NetworkObject paramNetworkObject)
/*  787:     */  {
/*  788: 786 */    for (int i = 0; i < this.receiverModules.size(); i++) {
/*  789: 787 */      ((NTReceiveInterface)this.receiverModules.get(i)).updateFromNT(getSegmentController().getNetworkObject());
/*  790:     */    }
/*  791:     */    RemoteIntArray localRemoteIntArray;
/*  792: 790 */    if ((paramNetworkObject instanceof DistributionInterface))
/*  793:     */    {
/*  794: 792 */      localRemoteIntArray = null; for (paramNetworkObject = ((DistributionInterface)paramNetworkObject).getDistributionModification().getReceiveBuffer().iterator(); paramNetworkObject.hasNext();) { localRemoteIntArray = (RemoteIntArray)paramNetworkObject.next();
/*  795:     */        
/*  796: 794 */        q localq1 = new q(localRemoteIntArray.getIntArray()[0], localRemoteIntArray.getIntArray()[1], localRemoteIntArray.getIntArray()[2]);
/*  797: 795 */        q localq2 = new q(localRemoteIntArray.getIntArray()[3], localRemoteIntArray.getIntArray()[4], localRemoteIntArray.getIntArray()[5]);
/*  798: 796 */        synchronized (this.receivedDistributions) {
/*  799: 797 */          this.receivedDistributions.add(new jE(localq1, localq2, localRemoteIntArray.getIntArray()[6], localRemoteIntArray.getIntArray()[7], localRemoteIntArray.getIntArray()[8]));
/*  800:     */        }
/*  801:     */      } }
/*  802: 800 */    handleInventoryFromNT(getInventoryInterface().getInventoriesChangeBuffer());
/*  803:     */  }
/*  804:     */  
/*  808: 806 */  private final ArrayList receivedDistributions = new ArrayList();
/*  809:     */  
/*  810:     */  public void updateLocal(xq paramxq)
/*  811:     */  {
/*  812:     */    Object localObject1;
/*  813: 811 */    if (!this.ntInventoryMods.isEmpty()) {
/*  814: 812 */      synchronized (this.ntInventoryMods) {
/*  815: 813 */        while (!this.ntInventoryMods.isEmpty()) {
/*  816: 814 */          localObject1 = (mf)this.ntInventoryMods.dequeue();
/*  817: 815 */          getInventories().put(((mf)localObject1).a(), localObject1);
/*  818: 816 */          System.err.println("ADDED INVENTORY: " + ((mf)localObject1).a() + " on " + getSegmentController().getState() + ": " + localObject1);
/*  819:     */        }
/*  820:     */      }
/*  821:     */    }
/*  822:     */    
/*  825:     */    Object localObject4;
/*  826:     */    
/*  828:     */    Object localObject5;
/*  829:     */    
/*  831:     */    Object localObject6;
/*  832:     */    
/*  834: 832 */    if (!this.ntInventorySingleMods.isEmpty()) {
/*  835: 833 */      synchronized (this.ntInventorySingleMods) {
/*  836: 834 */        localObject1 = new ArrayList();
/*  837: 835 */        while (!this.ntInventorySingleMods.isEmpty()) {
/*  838: 836 */          localObject4 = (RemoteIntArray)this.ntInventorySingleMods.dequeue();
/*  839: 837 */          localObject5 = new q(localObject4.getIntArray()[3], localObject4.getIntArray()[4], localObject4.getIntArray()[5]);
/*  840:     */          
/*  841: 839 */          if ((localObject6 = getInventory((q)localObject5)) != null) {
/*  842: 840 */            ((mf)localObject6).a((RemoteIntArray)localObject4, getInventoryInterface());
/*  843:     */          } else {
/*  844: 842 */            if (!this.segmentController.isOnServer()) {
/*  845: 843 */              ((ArrayList)localObject1).add(localObject4);
/*  846:     */            }
/*  847: 845 */            System.err.println("[MANAGERCONTAINER] Exc: NOT FOUND INVENTORY (received mod): " + getSegmentController() + " - " + localObject5 + "; " + getInventories().keySet());
/*  848:     */          }
/*  849:     */        }
/*  850: 848 */        if (!((ArrayList)localObject1).isEmpty()) {
/*  851: 849 */          while (!((ArrayList)localObject1).isEmpty()) {
/*  852: 850 */            this.ntInventorySingleMods.enqueue(((ArrayList)localObject1).remove(0));
/*  853:     */          }
/*  854:     */        }
/*  855:     */      }
/*  856:     */    }
/*  857:     */    
/*  858: 856 */    if (!this.ntInventoryMultMods.isEmpty()) {
/*  859: 857 */      synchronized (this.ntInventoryMultMods) {
/*  860: 858 */        localObject1 = new ArrayList();
/*  861: 859 */        while (!this.ntInventoryMultMods.isEmpty()) {
/*  862: 860 */          localObject4 = (mi)this.ntInventoryMultMods.dequeue();
/*  863:     */          
/*  864: 862 */          if ((localObject5 = getInventory(((mi)localObject4).a)) != null) {
/*  865: 863 */            getInventoryInterface();((mf)localObject5).a((mi)localObject4);
/*  866:     */          } else {
/*  867: 865 */            if (!this.segmentController.isOnServer()) {
/*  868: 866 */              ((ArrayList)localObject1).add(localObject4);
/*  869:     */            }
/*  870: 868 */            System.err.println("[MANAGERCONTAINER] Exc: NOT FOUND MULT INVENTORY (received mod): " + getSegmentController() + " - " + ((mi)localObject4).a + "; " + getInventories().keySet());
/*  871:     */          }
/*  872:     */        }
/*  873: 871 */        if (!((ArrayList)localObject1).isEmpty()) {
/*  874: 872 */          while (!((ArrayList)localObject1).isEmpty()) {
/*  875: 873 */            this.ntInventoryMultMods.enqueue(((ArrayList)localObject1).remove(0));
/*  876:     */          }
/*  877:     */        }
/*  878:     */      }
/*  879:     */    }
/*  880: 878 */    if (!this.ntActiveInventorySingleMods.isEmpty()) {
/*  881: 879 */      synchronized (this.ntActiveInventorySingleMods) {
/*  882: 880 */        ArrayList localArrayList = new ArrayList();
/*  883: 881 */        while (!this.ntActiveInventorySingleMods.isEmpty()) {
/*  884: 882 */          localObject4 = (RemoteIntArray)this.ntActiveInventorySingleMods.dequeue();
/*  885: 883 */          localObject5 = new q(localObject4.getIntArray()[0], localObject4.getIntArray()[1], localObject4.getIntArray()[2]);
/*  886: 884 */          localObject6 = getInventory((q)localObject5);
/*  887: 885 */          System.err.println("[INVENTORY] HANDLE RECEIVED ACTIVATE: " + localObject5 + ": " + localObject6);
/*  888: 886 */          assert (localObject6 != null);
/*  889: 887 */          if ((localObject6 != null) && ((localObject6 instanceof md))) {
/*  890: 888 */            ((md)localObject6).b();
/*  891:     */          } else {
/*  892: 890 */            localArrayList.add(localObject4);
/*  893: 891 */            System.err.println("Exception: NOT FOUND active INVENTORY: " + getSegmentController() + " - " + localObject5);
/*  894:     */          }
/*  895:     */        }
/*  896: 894 */        if (!localArrayList.isEmpty()) {
/*  897: 895 */          while (!localArrayList.isEmpty()) {
/*  898: 896 */            this.ntActiveInventorySingleMods.enqueue(localArrayList.remove(0));
/*  899:     */          }
/*  900:     */        }
/*  901:     */      }
/*  902:     */    }
/*  903:     */    
/*  904: 902 */    long l = System.currentTimeMillis();
/*  905: 903 */    for (int j = 0; j < this.modules.size(); j++) {
/*  906: 904 */      ((ManagerModule)this.modules.get(j)).update(paramxq, l);
/*  907:     */    }
/*  908:     */    
/*  909: 907 */    for (j = 0; j < this.updatableModules.size(); j++) {
/*  910: 908 */      ((UpdatableCollectionManager)this.updatableModules.get(j)).update(paramxq);
/*  911:     */    }
/*  912:     */    
/*  913: 911 */    j = 0;
/*  914:     */    
/*  915: 913 */    if (!this.receivedDistributions.isEmpty()) {
/*  916: 914 */      synchronized (this.receivedDistributions)
/*  917:     */      {
/*  918: 916 */        localObject6 = (jE)this.receivedDistributions.remove(0);
/*  919: 917 */        paramxq = 0;
/*  920: 918 */        for (int i = 0; i < this.distributionReceiverModules.size(); i++)
/*  921:     */        {
/*  922: 920 */          if (((NTDistributionReceiverInterface)this.distributionReceiverModules.get(i)).receiveDistribution((jE)localObject6, getSegmentController().getNetworkObject())) {
/*  923: 921 */            paramxq = 1;
/*  924:     */          }
/*  925:     */        }
/*  926:     */        
/*  927: 925 */        if ((paramxq == 0) && (!getSegmentController().isOnServer())) {
/*  928: 926 */          PointDistributionTagDummy localPointDistributionTagDummy = new PointDistributionTagDummy((jE)localObject6);
/*  929: 927 */          getInitialPointDists().add(localPointDistributionTagDummy);
/*  930:     */        }
/*  931:     */      }
/*  932:     */    }
/*  933:     */    
/*  935: 933 */    while (!this.delayedInventoryAdd.isEmpty())
/*  936:     */    {
/*  938: 936 */      ??? = (mf)this.delayedInventoryAdd.remove(0);
/*  939:     */      
/*  940: 938 */      if (!getInventories().containsKey(((mf)???).a())) {
/*  941: 939 */        if (getSegmentController().isOnServer()) {
/*  942: 940 */          System.err.println("[SERVER] " + getSegmentController() + " ADDING NEW INVENTORY " + ((mf)???).a());
/*  943:     */        }
/*  944: 942 */        announceInventory(((mf)???).a(), false, (mf)???, true);
/*  945: 943 */        j = 1;
/*  946:     */      }
/*  947:     */    }
/*  948: 946 */    while (!this.delayedInventoryRemove.isEmpty()) {
/*  949: 947 */      ??? = (s)this.delayedInventoryRemove.remove(0);
/*  950: 948 */      announceInventory(new q(((s)???).a, ((s)???).b, ((s)???).c), ((s)???).d == 1, null, false);
/*  951: 949 */      j = 1;
/*  952:     */    }
/*  953:     */    
/*  954: 952 */    if (j != 0) {
/*  955: 953 */      this.activeInventories.clear();
/*  956: 954 */      for (??? = getInventories().values().iterator(); ((Iterator)???).hasNext();)
/*  957:     */      {
/*  958: 956 */        if (((localObject6 = (mf)((Iterator)???).next()) instanceof md)) {
/*  959: 957 */          this.activeInventories.add((md)localObject6);
/*  960:     */        }
/*  961:     */      }
/*  962:     */    }
/*  963: 961 */    else if (getSegmentController().isOnServer())
/*  964:     */    {
/*  967: 965 */      for (??? = this.activeInventories.iterator(); ((Iterator)???).hasNext();)
/*  968:     */      {
/*  969: 967 */        if ((localObject6 = (md)((Iterator)???).next()).a()) {
/*  970: 968 */          ((md)localObject6).a();
/*  971:     */        }
/*  972:     */      }
/*  973:     */    }
/*  974:     */    int k;
/*  975: 973 */    if (this.flagAnyBlockAdded) {
/*  976: 974 */      for (k = 0; k < this.changeListenModules.size(); k++) {
/*  977: 975 */        ((ElementChangeListenerInterface)this.changeListenModules.get(k)).onAddedAnyElement();
/*  978:     */      }
/*  979: 977 */      this.flagAnyBlockAdded = false;
/*  980:     */    }
/*  981: 979 */    if (this.flagAnyBlockRemoved) {
/*  982: 980 */      for (k = 0; k < this.changeListenModules.size(); k++) {
/*  983: 981 */        ((ElementChangeListenerInterface)this.changeListenModules.get(k)).onRemovedAnyElement();
/*  984:     */      }
/*  985: 983 */      this.flagAnyBlockRemoved = false;
/*  986:     */    }
/*  987:     */  }
/*  988:     */  
/*  991:     */  public void updateToFullNetworkObject(NetworkObject paramNetworkObject)
/*  992:     */  {
/*  993: 991 */    for (int i = 0; i < this.senderModules.size(); i++) {
/*  994: 992 */      ((NTSenderInterface)this.senderModules.get(i)).updateToFullNT(paramNetworkObject);
/*  995:     */    }
/*  996:     */  }
/*  997:     */  
/* 1001:     */  public HashMap getInventories()
/* 1002:     */  {
/* 1003:1001 */    return this.inventories;
/* 1004:     */  }
/* 1005:     */  
/* 1006:     */  public boolean canBeControlled(short paramShort)
/* 1007:     */  {
/* 1008:1006 */    return getModulesMap().containsKey(Short.valueOf(paramShort));
/* 1009:     */  }
/* 1010:     */  
/* 1014:     */  public ArrayList getModules()
/* 1015:     */  {
/* 1016:1014 */    return this.modules;
/* 1017:     */  }
/* 1018:     */  
/* 1022:     */  public ArrayList getInitialBlockMetaData()
/* 1023:     */  {
/* 1024:1022 */    return this.initialBlockMetaData;
/* 1025:     */  }
/* 1026:     */  
/* 1030:     */  public HashMap getModulesControllerMap()
/* 1031:     */  {
/* 1032:1030 */    return this.modulesControllerMap;
/* 1033:     */  }
/* 1034:     */  
/* 1038:     */  public HashMap getModulesMap()
/* 1039:     */  {
/* 1040:1038 */    return this.modulesMap;
/* 1041:     */  }
/* 1042:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.ManagerContainer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */