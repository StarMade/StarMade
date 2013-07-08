/*    1:     */package org.schema.game.common.data.element;
/*    2:     */
/*    3:     */import Ah;
/*    4:     */import Aj;
/*    5:     */import Ak;
/*    6:     */import af;
/*    7:     */import ct;
/*    8:     */import dj;
/*    9:     */import ep;
/*   10:     */import it.unimi.dsi.fastutil.io.FastByteArrayInputStream;
/*   11:     */import it.unimi.dsi.fastutil.io.FastByteArrayOutputStream;
/*   12:     */import it.unimi.dsi.fastutil.longs.Long2ObjectMap.Entry;
/*   13:     */import it.unimi.dsi.fastutil.longs.Long2ObjectMap.FastEntrySet;
/*   14:     */import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
/*   15:     */import it.unimi.dsi.fastutil.longs.LongSet;
/*   16:     */import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*   17:     */import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
/*   18:     */import it.unimi.dsi.fastutil.objects.ObjectSet;
/*   19:     */import it.unimi.dsi.fastutil.shorts.Short2ObjectOpenHashMap;
/*   20:     */import jD;
/*   21:     */import jL;
/*   22:     */import ja;
/*   23:     */import java.io.ByteArrayInputStream;
/*   24:     */import java.io.ByteArrayOutputStream;
/*   25:     */import java.io.DataInputStream;
/*   26:     */import java.io.DataOutputStream;
/*   27:     */import java.io.IOException;
/*   28:     */import java.io.PrintStream;
/*   29:     */import java.util.ArrayList;
/*   30:     */import java.util.Collection;
/*   31:     */import java.util.ConcurrentModificationException;
/*   32:     */import java.util.HashMap;
/*   33:     */import java.util.HashSet;
/*   34:     */import java.util.Iterator;
/*   35:     */import java.util.Map;
/*   36:     */import java.util.Map.Entry;
/*   37:     */import java.util.Set;
/*   38:     */import java.util.zip.DataFormatException;
/*   39:     */import java.util.zip.Deflater;
/*   40:     */import java.util.zip.Inflater;
/*   41:     */import ka;
/*   42:     */import ld;
/*   43:     */import le;
/*   44:     */import org.schema.game.common.controller.CannotImmediateRequestOnClientException;
/*   45:     */import org.schema.game.common.controller.elements.ManagerContainer;
/*   46:     */import org.schema.game.network.objects.NetworkSegmentController;
/*   47:     */import org.schema.schine.network.objects.remote.RemoteArrayBuffer;
/*   48:     */import org.schema.schine.network.objects.remote.RemoteField;
/*   49:     */import org.schema.schine.network.objects.remote.RemoteIntegerArray;
/*   50:     */import q;
/*   51:     */import s;
/*   52:     */import vg;
/*   53:     */import x;
/*   54:     */import xq;
/*   55:     */
/*   56:     */public class ControlElementMap implements Ak
/*   57:     */{
/*   58:  58 */  private final Map positionControlMapChache = new HashMap();
/*   59:     */  
/*   60:  60 */  private ControlElementMapper controllingMap = new ControlElementMapper();
/*   61:     */  
/*   62:     */  private ka sendableSegmentController;
/*   63:     */  
/*   64:  64 */  private ja elementPositionTmp = new ja();
/*   65:  65 */  private final HashSet delayedControllerUpdates = new HashSet();
/*   66:  66 */  private final HashSet failedDelayedUpdates = new HashSet();
/*   67:     */  
/*   68:  68 */  private final ObjectOpenHashSet delayedNTUpdates = new ObjectOpenHashSet();
/*   69:  69 */  private final ControlElementMapper delayedNTUpdatesMap = new ControlElementMapper();
/*   70:     */  
/*   72:  72 */  le pTmp = new le();
/*   73:     */  
/*   74:  74 */  le pointUnsaveTmp = new le();
/*   75:     */  
/*   77:     */  private ControlledElementContainer addedDouble;
/*   78:     */  
/*   80:     */  private boolean addControl(q paramq1, q paramq2, short paramShort, boolean paramBoolean)
/*   81:     */  {
/*   82:  82 */    return addControl(new ControlledElementContainer(ElementCollection.getIndex(paramq1), paramq2, paramShort, true, paramBoolean));
/*   83:     */  }
/*   84:     */  
/*   85:     */  private boolean addControl(ControlledElementContainer paramControlledElementContainer)
/*   86:     */  {
/*   87:  87 */    if (paramControlledElementContainer.from == ElementCollection.getIndex(paramControlledElementContainer.to)) {
/*   88:  88 */      System.err.println("WARNING: tried to add controlled element that is equal with the controlling");
/*   89:  89 */      if (!this.sendableSegmentController.isOnServer()) {
/*   90:  90 */        ((ct)this.sendableSegmentController.getState()).a().b("Error: Cannot connect a block\nto itself");
/*   91:     */      }
/*   92:  92 */      return false;
/*   93:     */    }
/*   94:     */    
/*   96:  96 */    new ja(paramControlledElementContainer.to, paramControlledElementContainer.controlledType);
/*   97:     */    
/*   98:  98 */    boolean bool = getControllingMap().put(paramControlledElementContainer.from, paramControlledElementContainer.to, paramControlledElementContainer.controlledType);
/*   99:     */    
/*  101: 101 */    if ((paramControlledElementContainer.send) && (this.sendableSegmentController != null))
/*  102:     */    {
/*  103: 103 */      send(paramControlledElementContainer.from, paramControlledElementContainer.to, paramControlledElementContainer.controlledType, true);
/*  104:     */    }
/*  105: 105 */    clearCache(paramControlledElementContainer.controlledType);
/*  106:     */    
/*  107: 107 */    if (bool) {
/*  108: 108 */      paramControlledElementContainer.send = false;
/*  109: 109 */      if (this.needsControllerUpdates) {
/*  110: 110 */        assert (paramControlledElementContainer.controlledType != 1);
/*  111: 111 */        this.delayedControllerUpdates.add(paramControlledElementContainer);
/*  112:     */      }
/*  113:     */    }
/*  114: 114 */    if ((!this.sendableSegmentController.isOnServer()) && (ElementKeyMap.getFactorykeyset().contains(Short.valueOf(paramControlledElementContainer.controlledType))) && 
/*  115: 115 */      (((ct)this.sendableSegmentController.getState()).a() != null)) {
/*  116: 116 */      ((ct)this.sendableSegmentController.getState()).a().a().a(this.sendableSegmentController);
/*  117:     */    }
/*  118:     */    
/*  121: 121 */    return bool; }
/*  122:     */  
/*  123: 123 */  private final HashSet loopMap = new HashSet();
/*  124:     */  
/*  125:     */  private boolean needsControllerUpdates;
/*  126:     */  
/*  127:     */  private Iterator controllingMapCheck;
/*  128:     */  
/*  129:     */  private af currentBuildController;
/*  130:     */  
/*  131: 131 */  private final ArrayList toRemoveControlled = new ArrayList();
/*  132:     */  
/*  133:     */  private boolean testMode;
/*  134:     */  
/*  135:     */  private boolean structureCompleteChange;
/*  136:     */  
/*  137:     */  private boolean initialStructureReceived;
/*  138:     */  
/*  139:     */  private boolean flagRequested;
/*  140:     */  
/*  141:     */  public static final int blockSize = 14;
/*  142:     */  
/*  144:     */  private void addControlChain(long paramLong, short paramShort, jD paramjD)
/*  145:     */  {
/*  146:     */    ;
/*  147:     */    
/*  148:     */    Short2ObjectOpenHashMap localShort2ObjectOpenHashMap;
/*  149: 149 */    if ((localShort2ObjectOpenHashMap = (Short2ObjectOpenHashMap)getControllingMap().get(paramLong)) != null) {
/*  150: 150 */      if (paramShort == 32767) {
/*  151: 151 */        paramjD.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.addAll((Collection)getControllingMap().getAll().get(paramLong));
/*  152:     */        
/*  153: 153 */        if ((paramLong = (ObjectOpenHashSet)getControllingMap().getControllers().get(paramLong)) != null) {
/*  154: 154 */          new q();
/*  155: 155 */          for (paramLong = paramLong.iterator(); paramLong.hasNext();) { // INTERNAL ERROR //

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.ControlElementMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */