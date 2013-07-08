package org.schema.game.common.data.element;

import class_1041;
import class_227;
import class_283;
import class_371;
import class_46;
import class_48;
import class_485;
import class_52;
import class_69;
import class_753;
import class_79;
import class_796;
import class_798;
import class_80;
import class_846;
import class_886;
import class_916;
import class_941;
import it.unimi.dsi.fastutil.io.FastByteArrayInputStream;
import it.unimi.dsi.fastutil.io.FastByteArrayOutputStream;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap.Entry;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap.FastEntrySet;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.longs.LongSet;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import it.unimi.dsi.fastutil.shorts.Short2ObjectOpenHashMap;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import org.schema.game.common.controller.CannotImmediateRequestOnClientException;
import org.schema.game.common.controller.elements.ManagerContainer;
import org.schema.game.network.objects.NetworkSegmentController;
import org.schema.schine.network.objects.remote.RemoteArrayBuffer;
import org.schema.schine.network.objects.remote.RemoteField;
import org.schema.schine.network.objects.remote.RemoteIntegerArray;

public class ControlElementMap
  implements class_80
{
  private final Map positionControlMapChache = new HashMap();
  private ControlElementMapper controllingMap = new ControlElementMapper();
  private class_753 sendableSegmentController;
  private class_916 elementPositionTmp = new class_916();
  private final HashSet delayedControllerUpdates = new HashSet();
  private final HashSet failedDelayedUpdates = new HashSet();
  private final ObjectOpenHashSet delayedNTUpdates = new ObjectOpenHashSet();
  private final ControlElementMapper delayedNTUpdatesMap = new ControlElementMapper();
  class_796 pTmp = new class_796();
  class_796 pointUnsaveTmp = new class_796();
  private ControlledElementContainer addedDouble;
  private final HashSet loopMap = new HashSet();
  private boolean needsControllerUpdates;
  private Iterator controllingMapCheck;
  private class_485 currentBuildController;
  private final ArrayList toRemoveControlled = new ArrayList();
  private boolean testMode;
  private boolean structureCompleteChange;
  private boolean initialStructureReceived;
  private boolean flagRequested;
  public static final int blockSize = 14;
  
  private boolean addControl(class_48 paramclass_481, class_48 paramclass_482, short paramShort, boolean paramBoolean)
  {
    return addControl(new ControlledElementContainer(ElementCollection.getIndex(paramclass_481), paramclass_482, paramShort, true, paramBoolean));
  }
  
  private boolean addControl(ControlledElementContainer paramControlledElementContainer)
  {
    if (paramControlledElementContainer.from == ElementCollection.getIndex(paramControlledElementContainer.field_1664))
    {
      System.err.println("WARNING: tried to add controlled element that is equal with the controlling");
      if (!this.sendableSegmentController.isOnServer()) {
        ((class_371)this.sendableSegmentController.getState()).a4().b1("Error: Cannot connect a block\nto itself");
      }
      return false;
    }
    new class_916(paramControlledElementContainer.field_1664, paramControlledElementContainer.controlledType);
    boolean bool = getControllingMap().put(paramControlledElementContainer.from, paramControlledElementContainer.field_1664, paramControlledElementContainer.controlledType);
    if ((paramControlledElementContainer.send) && (this.sendableSegmentController != null)) {
      send(paramControlledElementContainer.from, paramControlledElementContainer.field_1664, paramControlledElementContainer.controlledType, true);
    }
    clearCache(paramControlledElementContainer.controlledType);
    if (bool)
    {
      paramControlledElementContainer.send = false;
      if (this.needsControllerUpdates)
      {
        assert (paramControlledElementContainer.controlledType != 1);
        this.delayedControllerUpdates.add(paramControlledElementContainer);
      }
    }
    if ((!this.sendableSegmentController.isOnServer()) && (ElementKeyMap.getFactorykeyset().contains(Short.valueOf(paramControlledElementContainer.controlledType))) && (((class_371)this.sendableSegmentController.getState()).a27() != null)) {
      ((class_371)this.sendableSegmentController.getState()).a27().a98().a19(this.sendableSegmentController);
    }
    return bool;
  }
  
  private void addControlChain(long paramLong, short paramShort, class_846 paramclass_846)
  {
    ;
    Short2ObjectOpenHashMap localShort2ObjectOpenHashMap;
    if ((localShort2ObjectOpenHashMap = (Short2ObjectOpenHashMap)getControllingMap().get(paramLong)) != null)
    {
      if (paramShort == 32767)
      {
        paramclass_846.jdField_field_1094_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.addAll((Collection)getControllingMap().getAll().get(paramLong));
        if ((paramLong = (ObjectOpenHashSet)getControllingMap().getControllers().get(paramLong)) != null)
        {
          new class_48();
          paramLong = paramLong.iterator();
          while (paramLong.hasNext())
          {
            // INTERNAL ERROR //

/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.element.ControlElementMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */