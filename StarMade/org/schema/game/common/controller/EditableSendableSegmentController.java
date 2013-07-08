package org.schema.game.common.controller;

import class_1039;
import class_1041;
import class_13;
import class_216;
import class_227;
import class_293;
import class_35;
import class_365;
import class_371;
import class_433;
import class_479;
import class_48;
import class_481;
import class_52;
import class_597;
import class_619;
import class_670;
import class_672;
import class_703;
import class_707;
import class_709;
import class_713;
import class_721;
import class_737;
import class_747;
import class_748;
import class_753;
import class_755;
import class_765;
import class_773;
import class_784;
import class_796;
import class_798;
import class_807;
import class_809;
import class_852;
import class_854;
import class_866;
import class_880;
import class_883;
import class_886;
import class_892;
import class_912;
import class_941;
import class_969;
import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
import com.bulletphysics.collision.narrowphase.ManifoldPoint;
import com.bulletphysics.linearmath.Transform;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadPoolExecutor;
import javax.vecmath.Vector3f;
import org.schema.game.common.controller.elements.ManagerModuleCollection;
import org.schema.game.common.controller.elements.ShieldContainerInterface;
import org.schema.game.common.controller.elements.dockingBlock.DockingBlockCollectionManager;
import org.schema.game.common.controller.elements.dockingBlock.DockingBlockManagerInterface;
import org.schema.game.common.controller.elements.shield.ShieldCollectionManager;
import org.schema.game.common.data.element.BeamHandler;
import org.schema.game.common.data.element.BeamHandler.BeamState;
import org.schema.game.common.data.element.Element;
import org.schema.game.common.data.element.ElementClassNotFoundException;
import org.schema.game.common.data.element.ElementDocking;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.game.common.data.physics.CubeRayCastResult;
import org.schema.game.common.data.physics.PhysicsExt;
import org.schema.game.common.data.world.Segment;
import org.schema.game.common.data.world.SegmentData;
import org.schema.game.common.data.world.Universe;
import org.schema.game.network.objects.NetworkSegmentController;
import org.schema.game.network.objects.remote.RemoteSegmentPiece;
import org.schema.game.network.objects.remote.RemoteSegmentPieceBuffer;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.objects.Sendable;
import org.schema.schine.network.objects.container.PhysicsDataContainer;
import org.schema.schine.network.server.ServerMessage;

public abstract class EditableSendableSegmentController
  extends class_753
  implements class_854
{
  private static final long MIN_TIME_BETWEEN_EDITS = 50L;
  private long lastEditBlocks;
  private final class_48 tmpPos = new class_48();
  private final class_48 absPosCache = new class_48();
  private boolean flagCharacterExitCheckByExplosion;
  private Object flagCoreDestroyedByExplosion;
  private final ArrayList explosionOrdersRunning = new ArrayList();
  private final class_48 tmpVisPos = new class_48();
  
  public EditableSendableSegmentController(StateInterface paramStateInterface)
  {
    super(paramStateInterface);
  }
  
  public boolean allowedType(short paramShort)
  {
    if (!ElementKeyMap.getInfo(paramShort).isPlacable())
    {
      if (!isOnServer()) {
        if (1 == paramShort) {
          ((class_371)getState()).a4().b1("ERROR\nCore Elements cannot be placed\nthey are used to spawn new ships");
        } else {
          ((class_371)getState()).a4().b1("ERROR\nThis Element cannot be placed.\nhow did you even get that...");
        }
      }
      return false;
    }
    return true;
  }
  
  private void checkCharacterExit()
  {
    System.err.println("[SegController] CHECKING CHARACTER EXIT");
    if ((this instanceof class_365))
    {
      Iterator localIterator = ((class_365)this).a26().iterator();
      while (localIterator.hasNext()) {
        ((class_748)localIterator.next()).a124().d();
      }
    }
  }
  
  public boolean isEmptyOnServer()
  {
    int i = 1;
    Iterator localIterator = ((class_1041)getState()).b10().values().iterator();
    while (localIterator.hasNext()) {
      if (((class_748)localIterator.next()).c2() == getSectorId())
      {
        i = 0;
        break;
      }
    }
    if (i != 0) {
      return false;
    }
    class_48 localclass_482 = new class_48(getMaxPos());
    class_48 localclass_481 = new class_48(getMinPos());
    System.err.println("[SERVER][SEGMENTCONTROLLER] CHECKING EMPTY FROM " + localclass_481 + " TO " + localclass_482 + ": " + this);
    for (int j = localclass_481.field_477; j <= localclass_482.field_477; j++) {
      for (int k = localclass_481.field_476; k <= localclass_482.field_476; k++) {
        for (int m = localclass_481.field_475; m <= localclass_482.field_475; m++)
        {
          Object localObject;
          (localObject = new class_48()).field_475 = ((m << 4) + 8);
          ((class_48)localObject).field_476 = ((k << 4) + 8);
          ((class_48)localObject).field_477 = ((j << 4) + 8);
          if (!(localObject = getSegmentBuffer().a9((class_48)localObject, true)).a7().g())
          {
            System.err.println("[SERVER][SEGMENTCONTROLLER] CHECKING EMPTY FROM " + localclass_481 + " TO " + localclass_482 + ": " + this + " --- IS NOT EMPTY " + ((class_796)localObject).a7());
            return false;
          }
        }
      }
    }
    System.err.println("[SERVER][SEGMENTCONTROLLER] CHECKING EMPTY FROM " + localclass_481 + " TO " + localclass_482 + ": " + this + " --- IS EMPTY");
    return true;
  }
  
  public int damageElement(short paramShort, int paramInt1, SegmentData paramSegmentData, int paramInt2)
  {
    try
    {
      paramShort = ElementKeyMap.getInfo(paramShort);
      paramShort = (int)Math.max(0.0D, paramInt2 - Math.ceil(paramInt2 * paramShort.getArmourPercent()));
      paramSegmentData.setHitpoints(paramInt1, (short)Math.max(0, paramSegmentData.getHitpoints(paramInt1) - paramShort));
      return paramShort;
    }
    catch (ElementClassNotFoundException localElementClassNotFoundException)
    {
      localElementClassNotFoundException.printStackTrace();
      System.err.println("[WARNING] Exception catched! returning zero damage");
    }
    return 0;
  }
  
  public void destroy()
  {
    System.out.println("[SEGMENTCONTROLLER] ENTITY " + this + " HAS BEEN DESTROYED... ");
    markedForPermanentDelete(true);
    setMarkedForDeleteVolatile(true);
  }
  
  public void doDimExtensionIfNecessary(Segment paramSegment, byte paramByte1, byte paramByte2, byte paramByte3)
  {
    paramSegment.a13(paramByte1, paramByte2, paramByte3, this.absPosCache);
    if (paramByte1 == 0)
    {
      Segment.a9(this.absPosCache.field_475 - 1, this.absPosCache.field_476, this.absPosCache.field_477, this.tmpPos);
      extendDim(0, this.absPosCache, this.tmpPos, -1, 0, 0);
    }
    if (paramByte2 == 0)
    {
      Segment.a9(this.absPosCache.field_475, this.absPosCache.field_476 - 1, this.absPosCache.field_477, this.tmpPos);
      extendDim(1, this.absPosCache, this.tmpPos, 0, -1, 0);
    }
    if (paramByte3 == 0)
    {
      Segment.a9(this.absPosCache.field_475, this.absPosCache.field_476, this.absPosCache.field_477 - 1, this.tmpPos);
      extendDim(2, this.absPosCache, this.tmpPos, 0, 0, -1);
    }
    if (paramByte1 == 15)
    {
      Segment.a9(this.absPosCache.field_475 + 1, this.absPosCache.field_476, this.absPosCache.field_477, this.tmpPos);
      extendDim(0, this.absPosCache, this.tmpPos, 1, 0, 0);
    }
    if (paramByte2 == 15)
    {
      Segment.a9(this.absPosCache.field_475, this.absPosCache.field_476 + 1, this.absPosCache.field_477, this.tmpPos);
      extendDim(1, this.absPosCache, this.tmpPos, 0, 1, 0);
    }
    if (paramByte3 == 15)
    {
      Segment.a9(this.absPosCache.field_475, this.absPosCache.field_476, this.absPosCache.field_477 + 1, this.tmpPos);
      extendDim(2, this.absPosCache, this.tmpPos, 0, 0, 1);
    }
  }
  
  public void extendDim(int paramInt1, class_48 paramclass_481, class_48 paramclass_482, int paramInt2, int paramInt3, int paramInt4)
  {
    if (!isInboundCoord(paramInt1, paramclass_482))
    {
      getMaxPos().field_475 += (paramInt2 > 0 ? paramInt2 : 0);
      getMaxPos().field_476 += (paramInt3 > 0 ? paramInt3 : 0);
      getMaxPos().field_477 += (paramInt4 > 0 ? paramInt4 : 0);
      getMinPos().field_475 += (paramInt2 < 0 ? paramInt2 : 0);
      getMinPos().field_476 += (paramInt3 < 0 ? paramInt3 : 0);
      getMinPos().field_477 += (paramInt4 < 0 ? paramInt4 : 0);
    }
  }
  
  private void forceCharacterExit(class_796 paramclass_796)
  {
    synchronized (getState().getLocalAndRemoteObjectContainer().getLocalObjects())
    {
      Iterator localIterator = getState().getLocalAndRemoteObjectContainer().getLocalObjects().values().iterator();
      while (localIterator.hasNext())
      {
        Sendable localSendable;
        if (((localSendable = (Sendable)localIterator.next()) instanceof class_748)) {
          ((class_748)localSendable).a133(paramclass_796);
        }
      }
      return;
    }
  }
  
  protected short getCoreType()
  {
    return 1;
  }
  
  public void getNearestIntersectingElementPosition(Vector3f paramVector3f1, Vector3f paramVector3f2, class_48 paramclass_48, float paramFloat, class_481 paramclass_481, class_433 paramclass_433)
  {
    if (System.currentTimeMillis() - this.lastEditBlocks < 50L) {
      return;
    }
    Object localObject1 = new class_48();
    if ((paramVector3f1 = getNearestPiece(paramVector3f1, paramVector3f2, paramFloat, (class_48)localObject1, paramclass_48)) == null)
    {
      System.err.println("[SEGCONTROLLER][ELEMENT][REMOVE] NO NEAREST PIECE FOUND");
      return;
    }
    paramVector3f2 = paramclass_48.a3(1, 1, 1);
    paramclass_48 = Math.min((paramVector3f1 = paramVector3f1.a2(new class_48())).field_475, paramVector3f1.field_475 + ((class_48)localObject1).field_475);
    paramFloat = Math.min(paramVector3f1.field_476, paramVector3f1.field_476 + ((class_48)localObject1).field_476);
    Object localObject2 = Math.min(paramVector3f1.field_477, paramVector3f1.field_477 + ((class_48)localObject1).field_477);
    int j = Math.max(paramVector3f1.field_475, paramVector3f1.field_475 + ((class_48)localObject1).field_475);
    int k = Math.max(paramVector3f1.field_476, paramVector3f1.field_476 + ((class_48)localObject1).field_476);
    Object localObject3 = Math.max(paramVector3f1.field_477, paramVector3f1.field_477 + ((class_48)localObject1).field_477);
    if (j == paramVector3f1.field_475)
    {
      paramclass_48++;
      j++;
    }
    if (k == paramVector3f1.field_476)
    {
      paramFloat++;
      k++;
    }
    if (localObject3 == paramVector3f1.field_477)
    {
      localObject2++;
      localObject3++;
    }
    if (paramclass_433.jdField_field_795_of_type_Int > 0)
    {
      paramclass_433.jdField_field_795_of_type_Int = 0;
      return;
    }
    System.err.println("[SEGCONTROLLER][ELEMENT][REMOVE] REMOVING BLOCKS: SIZE: " + localObject1);
    paramVector3f1 = new HashSet(8);
    long l = System.currentTimeMillis();
    for (localObject1 = localObject2; localObject1 < localObject3; localObject1++) {
      for (int i = paramFloat; i < k; i++) {
        for (int m = paramclass_48; m < j; m++)
        {
          remove(m, i, localObject1, paramclass_481, paramVector3f2, paramVector3f1);
          if ((paramclass_433.jdField_field_795_of_type_Boolean) && (!paramclass_433.jdField_field_796_of_type_Boolean) && (!paramclass_433.jdField_field_797_of_type_Boolean))
          {
            Object localObject8 = (paramclass_433.jdField_field_795_of_type_Class_48.field_477 - localObject1 << 1) + paramclass_433.jdField_field_796_of_type_Int;
            remove(m, i, localObject1 + localObject8, paramclass_481, paramVector3f2, paramVector3f1);
          }
          else if ((!paramclass_433.jdField_field_795_of_type_Boolean) && (paramclass_433.jdField_field_796_of_type_Boolean) && (!paramclass_433.jdField_field_797_of_type_Boolean))
          {
            int i4 = (paramclass_433.jdField_field_796_of_type_Class_48.field_476 - i << 1) + paramclass_433.jdField_field_796_of_type_Int;
            remove(m, i + i4, localObject1, paramclass_481, paramVector3f2, paramVector3f1);
          }
          else if ((!paramclass_433.jdField_field_795_of_type_Boolean) && (!paramclass_433.jdField_field_796_of_type_Boolean) && (paramclass_433.jdField_field_797_of_type_Boolean))
          {
            int i5 = (paramclass_433.jdField_field_797_of_type_Class_48.field_475 - m << 1) + paramclass_433.jdField_field_796_of_type_Int;
            remove(m + i5, i, localObject1, paramclass_481, paramVector3f2, paramVector3f1);
          }
          else if ((paramclass_433.jdField_field_795_of_type_Boolean) && (paramclass_433.jdField_field_796_of_type_Boolean) && (!paramclass_433.jdField_field_797_of_type_Boolean))
          {
            Object localObject4 = paramclass_433.jdField_field_795_of_type_Class_48.field_477;
            int i6 = paramclass_433.jdField_field_796_of_type_Class_48.field_476;
            Object localObject9 = (localObject4 - localObject1 << 1) + paramclass_433.jdField_field_796_of_type_Int;
            int n = (i6 - i << 1) + paramclass_433.jdField_field_796_of_type_Int;
            remove(m, i, localObject1 + localObject9, paramclass_481, paramVector3f2, paramVector3f1);
            remove(m, i + n, localObject1, paramclass_481, paramVector3f2, paramVector3f1);
            remove(m, i + n, localObject1 + localObject9, paramclass_481, paramVector3f2, paramVector3f1);
          }
          else if ((paramclass_433.jdField_field_795_of_type_Boolean) && (!paramclass_433.jdField_field_796_of_type_Boolean) && (paramclass_433.jdField_field_797_of_type_Boolean))
          {
            Object localObject5 = paramclass_433.jdField_field_795_of_type_Class_48.field_477;
            int i7 = paramclass_433.jdField_field_797_of_type_Class_48.field_475;
            Object localObject10 = (localObject5 - localObject1 << 1) + paramclass_433.jdField_field_796_of_type_Int;
            int i1 = (i7 - m << 1) + paramclass_433.jdField_field_796_of_type_Int;
            remove(m, i, localObject1 + localObject10, paramclass_481, paramVector3f2, paramVector3f1);
            remove(m + i1, i, localObject1, paramclass_481, paramVector3f2, paramVector3f1);
            remove(m + i1, i, localObject1 + localObject10, paramclass_481, paramVector3f2, paramVector3f1);
          }
          else if ((!paramclass_433.jdField_field_795_of_type_Boolean) && (paramclass_433.jdField_field_796_of_type_Boolean) && (paramclass_433.jdField_field_797_of_type_Boolean))
          {
            int i2 = paramclass_433.jdField_field_796_of_type_Class_48.field_476;
            int i8 = paramclass_433.jdField_field_797_of_type_Class_48.field_475;
            int i11 = (i2 - i << 1) + paramclass_433.jdField_field_796_of_type_Int;
            int i3 = (i8 - m << 1) + paramclass_433.jdField_field_796_of_type_Int;
            remove(m, i + i11, localObject1, paramclass_481, paramVector3f2, paramVector3f1);
            remove(m + i3, i, localObject1, paramclass_481, paramVector3f2, paramVector3f1);
            remove(m + i3, i + i11, localObject1, paramclass_481, paramVector3f2, paramVector3f1);
          }
          else if ((paramclass_433.jdField_field_795_of_type_Boolean) && (paramclass_433.jdField_field_796_of_type_Boolean) && (paramclass_433.jdField_field_797_of_type_Boolean))
          {
            Object localObject6 = paramclass_433.jdField_field_795_of_type_Class_48.field_477;
            int i9 = paramclass_433.jdField_field_796_of_type_Class_48.field_476;
            int i12 = paramclass_433.jdField_field_797_of_type_Class_48.field_475;
            Object localObject7 = (localObject6 - localObject1 << 1) + paramclass_433.jdField_field_796_of_type_Int;
            int i10 = (i9 - i << 1) + paramclass_433.jdField_field_796_of_type_Int;
            int i13 = (i12 - m << 1) + paramclass_433.jdField_field_796_of_type_Int;
            remove(m + i13, i, localObject1, paramclass_481, paramVector3f2, paramVector3f1);
            remove(m, i + i10, localObject1, paramclass_481, paramVector3f2, paramVector3f1);
            remove(m, i, localObject1 + localObject7, paramclass_481, paramVector3f2, paramVector3f1);
            remove(m + i13, i + i10, localObject1, paramclass_481, paramVector3f2, paramVector3f1);
            remove(m + i13, i, localObject1 + localObject7, paramclass_481, paramVector3f2, paramVector3f1);
            remove(m, i + i10, localObject1 + localObject7, paramclass_481, paramVector3f2, paramVector3f1);
            remove(m + i13, i + i10, localObject1 + localObject7, paramclass_481, paramVector3f2, paramVector3f1);
          }
        }
      }
    }
    System.err.println("[SEGMENTCONTROLLER] REMOVAL TOOK " + (System.currentTimeMillis() - l));
    if (paramVector3f2 == 0)
    {
      localObject1 = paramVector3f1.iterator();
      while (((Iterator)localObject1).hasNext())
      {
        Segment localSegment;
        if (!(localSegment = (Segment)((Iterator)localObject1).next()).g()) {
          localSegment.a16().restructBB(true);
        }
      }
    }
  }
  
  private void remove(int paramInt1, int paramInt2, int paramInt3, class_481 paramclass_481, boolean paramBoolean, HashSet paramHashSet)
  {
    if (((paramInt1 = getSegmentBuffer().a9(new class_48(paramInt1, paramInt2, paramInt3), false)) != null) && (paramInt1.a9() != 0))
    {
      paramInt2 = paramInt1.a9();
      paramInt3 = 0;
      if (((1 == paramInt2) || (((this instanceof class_737)) && (getTotalElements() == 1))) && ((paramInt1.a2(this.tmpVisPos).equals(class_747.field_136)) || ((this instanceof class_737))))
      {
        paramInt3 = 1;
        if (!isOnServer()) {
          if (getTotalElements() == 1)
          {
            String str = (this instanceof class_747) ? "Are you sure you want to delete the core?\n\nThis will destroy the ship,\nand you will get the core.\n" : "Are you sure you want to delete the last block?\n\nThis will destroy the station!\n\nNo refunds!!";
            new class_709(this, (class_371)getState(), "Delete last block?", str, paramInt1, paramInt2, paramclass_481, paramBoolean).c1();
          }
          else
          {
            ((class_371)getState()).a4().d1("Cannot delete core!\nIt must be the last\nelement to delete");
          }
        }
      }
      if (paramInt3 == 0)
      {
        removeBlock(paramInt1, paramInt2, paramclass_481, paramBoolean);
        paramHashSet.add(paramInt1.a7());
      }
    }
  }
  
  private short removeBlock(class_796 paramclass_796, short paramShort, class_481 paramclass_481, boolean paramBoolean)
  {
    if (paramclass_796.a7().a20(paramclass_796.a6(this.tmpLocalPos), paramBoolean))
    {
      this.lastEditBlocks = System.currentTimeMillis();
      ((class_672)paramclass_796.a7()).a46(System.currentTimeMillis());
    }
    paramclass_796.a12();
    paramclass_796.a15(getState().getId());
    getNetworkObject().modificationBuffer.add(new RemoteSegmentPiece(paramclass_796, this, getNetworkObject()));
    assert (getNetworkObject().modificationBuffer.hasChanged());
    assert (getNetworkObject().isChanged());
    paramclass_481.a(paramShort);
    return paramShort;
  }
  
  public int getNearestIntersection(short paramShort, Vector3f paramVector3f1, Vector3f paramVector3f2, class_479 paramclass_479, int paramInt1, boolean paramBoolean, class_713 paramclass_713, class_48 paramclass_48, int paramInt2, float paramFloat, class_433 paramclass_433)
  {
    if ((paramShort == 291) && (getElementClassCountMap().a1((short)291) > 0))
    {
      if (!isOnServer()) {
        ((class_371)getState()).a4().b1("ERROR\nOnly one Faction block is permitted\nper structure");
      }
      return 0;
    }
    if (!allowedType(paramShort))
    {
      System.err.println("Type is not allowed on " + this + "; " + paramShort);
      return 0;
    }
    if (System.currentTimeMillis() - this.lastEditBlocks < 50L) {
      return 0;
    }
    class_48 localclass_481 = new class_48();
    class_796 localclass_796 = null;
    class_48 localclass_482 = new class_48();
    try
    {
      localclass_796 = getNextToNearestPiece(paramVector3f1, paramVector3f2, localclass_482, paramFloat, paramBoolean, paramclass_48, localclass_481);
      if ((paramclass_433.jdField_field_795_of_type_Int > 0) && (localclass_796 != null))
      {
        paramVector3f1 = localclass_796.a2(new class_48());
        switch (paramclass_433.jdField_field_795_of_type_Int)
        {
        case 1: 
          System.err.println("SYM XY PLANE SET");
          paramclass_433.jdField_field_795_of_type_Class_48.field_477 = paramVector3f1.field_477;
          paramclass_433.jdField_field_795_of_type_Boolean = true;
          break;
        case 2: 
          System.err.println("SYM XZ PLANE SET");
          paramclass_433.jdField_field_796_of_type_Class_48.field_476 = paramVector3f1.field_476;
          paramclass_433.jdField_field_796_of_type_Boolean = true;
          break;
        case 4: 
          System.err.println("SYM YZ PLANE SET");
          paramclass_433.jdField_field_797_of_type_Class_48.field_475 = paramVector3f1.field_475;
          paramclass_433.jdField_field_797_of_type_Boolean = true;
        }
        paramclass_433.jdField_field_795_of_type_Int = 0;
        return 0;
      }
      System.err.println("[CLIENT][EDIT] PLACING AT " + localclass_796 + "; size: " + paramclass_48 + " --> " + localclass_481 + ": PHY: " + (localclass_796 != null ? localclass_796.a7().a15().getPhysicsDataContainer().getObject() : ""));
    }
    catch (CannotImmediateRequestOnClientException paramVector3f1)
    {
      System.err.println("[CLIENT][WARNING] Cannot ADD! segment not yet in buffer " + paramVector3f1.a() + ". -> requested");
      return 0;
    }
    if (localclass_796 != null)
    {
      if (paramclass_713 != null)
      {
        paramVector3f2 = localclass_796.a2(new class_48());
        if (paramVector3f2.field_475 != paramVector3f1.jdField_field_978_of_type_ArrayOfInt[0]) {
          System.err.println("X valid " + paramVector3f2.field_475 + "/" + paramVector3f1.jdField_field_978_of_type_ArrayOfInt[0] + " ");
        }
        if (paramVector3f2.field_475 != paramVector3f1.jdField_field_978_of_type_ArrayOfInt[0])
        {
          if (paramVector3f2.field_476 != paramVector3f1.jdField_field_978_of_type_ArrayOfInt[1]) {
            System.err.println("Y valid " + paramVector3f2.field_476 + "/" + paramVector3f1.jdField_field_978_of_type_ArrayOfInt[1] + " ");
          }
          if (paramVector3f2.field_476 != paramVector3f1.jdField_field_978_of_type_ArrayOfInt[1]) {
            if (paramVector3f1.jdField_field_978_of_type_ArrayOfBoolean[2] != 0) {
              if (paramVector3f2.field_477 != paramVector3f1.jdField_field_978_of_type_ArrayOfInt[2]) {
                System.err.println("Z valid " + paramVector3f2.field_477 + "/" + paramVector3f1.jdField_field_978_of_type_ArrayOfInt[2] + " ");
              }
            }
          }
        }
        if ((paramVector3f2.field_477 != paramVector3f1.jdField_field_978_of_type_ArrayOfInt[2] ? 0 : paramVector3f1.jdField_field_978_of_type_ArrayOfBoolean[1] != 0 ? 0 : (paramVector3f1 = paramclass_713).jdField_field_978_of_type_ArrayOfBoolean[0] != 0 ? 0 : 1) == 0) {
          return 0;
        }
      }
      if (localclass_796.a7().g()) {
        getSegmentProvider().a25().assignData(localclass_796.a7());
      }
      System.err.println("adding new element to " + getClass().getSimpleName() + " at " + localclass_796 + ", type " + paramShort);
      paramVector3f1 = new int[2];
      paramVector3f2 = localclass_796.a2(new class_48());
      paramVector3f1[1] = paramInt2;
      paramclass_713 = localclass_481.field_475 < 0 ? paramVector3f2.field_475 + localclass_481.field_475 + 1 : paramVector3f2.field_475;
      paramclass_48 = localclass_481.field_476 < 0 ? paramVector3f2.field_476 + localclass_481.field_476 + 1 : paramVector3f2.field_476;
      paramInt2 = localclass_481.field_477 < 0 ? paramVector3f2.field_477 + localclass_481.field_477 + 1 : paramVector3f2.field_477;
      paramFloat = localclass_481.field_475 < 0 ? paramVector3f2.field_475 + 1 : paramVector3f2.field_475 + localclass_481.field_475;
      int j = localclass_481.field_476 < 0 ? paramVector3f2.field_476 + 1 : paramVector3f2.field_476 + localclass_481.field_476;
      paramVector3f2 = localclass_481.field_477 < 0 ? paramVector3f2.field_477 + 1 : paramVector3f2.field_477 + localclass_481.field_477;
      while ((paramInt2 < paramVector3f2) && (paramVector3f1[1] > 0))
      {
        for (int i = paramclass_48; (i < j) && (paramVector3f1[1] > 0); i++) {
          for (float f = paramclass_713; (f < paramFloat) && (paramVector3f1[1] > 0); f++)
          {
            build(f, i, paramInt2, paramShort, paramInt1, paramBoolean, paramclass_479, localclass_482, paramVector3f1);
            int m;
            if ((paramclass_433.jdField_field_795_of_type_Boolean) && (!paramclass_433.jdField_field_796_of_type_Boolean) && (!paramclass_433.jdField_field_797_of_type_Boolean))
            {
              m = (paramclass_433.jdField_field_795_of_type_Class_48.field_477 - paramInt2 << 1) + paramclass_433.jdField_field_796_of_type_Int;
              build(f, i, paramInt2 + m, paramShort, class_433.a(paramShort, paramBoolean, paramInt1, true, false, false), paramBoolean, paramclass_479, localclass_482, paramVector3f1);
            }
            else if ((!paramclass_433.jdField_field_795_of_type_Boolean) && (paramclass_433.jdField_field_796_of_type_Boolean) && (!paramclass_433.jdField_field_797_of_type_Boolean))
            {
              m = (paramclass_433.jdField_field_796_of_type_Class_48.field_476 - i << 1) + paramclass_433.jdField_field_796_of_type_Int;
              build(f, i + m, paramInt2, paramShort, class_433.a(paramShort, paramBoolean, paramInt1, false, true, false), paramBoolean, paramclass_479, localclass_482, paramVector3f1);
            }
            else if ((!paramclass_433.jdField_field_795_of_type_Boolean) && (!paramclass_433.jdField_field_796_of_type_Boolean) && (paramclass_433.jdField_field_797_of_type_Boolean))
            {
              m = (paramclass_433.jdField_field_797_of_type_Class_48.field_475 - f << 1) + paramclass_433.jdField_field_796_of_type_Int;
              build(f + m, i, paramInt2, paramShort, class_433.a(paramShort, paramBoolean, paramInt1, false, false, true), paramBoolean, paramclass_479, localclass_482, paramVector3f1);
            }
            else
            {
              int k;
              int n;
              if ((paramclass_433.jdField_field_795_of_type_Boolean) && (paramclass_433.jdField_field_796_of_type_Boolean) && (!paramclass_433.jdField_field_797_of_type_Boolean))
              {
                k = paramclass_433.jdField_field_795_of_type_Class_48.field_477;
                m = paramclass_433.jdField_field_796_of_type_Class_48.field_476;
                n = (k - paramInt2 << 1) + paramclass_433.jdField_field_796_of_type_Int;
                k = (m - i << 1) + paramclass_433.jdField_field_796_of_type_Int;
                build(f, i, paramInt2 + n, paramShort, class_433.a(paramShort, paramBoolean, paramInt1, true, false, false), paramBoolean, paramclass_479, localclass_482, paramVector3f1);
                build(f, i + k, paramInt2, paramShort, class_433.a(paramShort, paramBoolean, paramInt1, false, true, false), paramBoolean, paramclass_479, localclass_482, paramVector3f1);
                build(f, i + k, paramInt2 + n, paramShort, class_433.a(paramShort, paramBoolean, paramInt1, true, true, false), paramBoolean, paramclass_479, localclass_482, paramVector3f1);
              }
              else if ((paramclass_433.jdField_field_795_of_type_Boolean) && (!paramclass_433.jdField_field_796_of_type_Boolean) && (paramclass_433.jdField_field_797_of_type_Boolean))
              {
                k = paramclass_433.jdField_field_795_of_type_Class_48.field_477;
                m = paramclass_433.jdField_field_797_of_type_Class_48.field_475;
                n = (k - paramInt2 << 1) + paramclass_433.jdField_field_796_of_type_Int;
                k = (m - f << 1) + paramclass_433.jdField_field_796_of_type_Int;
                build(f, i, paramInt2 + n, paramShort, class_433.a(paramShort, paramBoolean, paramInt1, true, false, false), paramBoolean, paramclass_479, localclass_482, paramVector3f1);
                build(f + k, i, paramInt2, paramShort, class_433.a(paramShort, paramBoolean, paramInt1, false, false, true), paramBoolean, paramclass_479, localclass_482, paramVector3f1);
                build(f + k, i, paramInt2 + n, paramShort, class_433.a(paramShort, paramBoolean, paramInt1, true, false, true), paramBoolean, paramclass_479, localclass_482, paramVector3f1);
              }
              else if ((!paramclass_433.jdField_field_795_of_type_Boolean) && (paramclass_433.jdField_field_796_of_type_Boolean) && (paramclass_433.jdField_field_797_of_type_Boolean))
              {
                k = paramclass_433.jdField_field_796_of_type_Class_48.field_476;
                m = paramclass_433.jdField_field_797_of_type_Class_48.field_475;
                n = (k - i << 1) + paramclass_433.jdField_field_796_of_type_Int;
                k = (m - f << 1) + paramclass_433.jdField_field_796_of_type_Int;
                build(f, i + n, paramInt2, paramShort, class_433.a(paramShort, paramBoolean, paramInt1, false, true, false), paramBoolean, paramclass_479, localclass_482, paramVector3f1);
                build(f + k, i, paramInt2, paramShort, class_433.a(paramShort, paramBoolean, paramInt1, false, false, true), paramBoolean, paramclass_479, localclass_482, paramVector3f1);
                build(f + k, i + n, paramInt2, paramShort, class_433.a(paramShort, paramBoolean, paramInt1, false, true, true), paramBoolean, paramclass_479, localclass_482, paramVector3f1);
              }
              else if ((paramclass_433.jdField_field_795_of_type_Boolean) && (paramclass_433.jdField_field_796_of_type_Boolean) && (paramclass_433.jdField_field_797_of_type_Boolean))
              {
                k = paramclass_433.jdField_field_795_of_type_Class_48.field_477;
                m = paramclass_433.jdField_field_796_of_type_Class_48.field_476;
                n = paramclass_433.jdField_field_797_of_type_Class_48.field_475;
                k = (k - paramInt2 << 1) + paramclass_433.jdField_field_796_of_type_Int;
                m = (m - i << 1) + paramclass_433.jdField_field_796_of_type_Int;
                n = (n - f << 1) + paramclass_433.jdField_field_796_of_type_Int;
                build(f + n, i, paramInt2, paramShort, class_433.a(paramShort, paramBoolean, paramInt1, false, false, true), paramBoolean, paramclass_479, localclass_482, paramVector3f1);
                build(f, i + m, paramInt2, paramShort, class_433.a(paramShort, paramBoolean, paramInt1, false, true, false), paramBoolean, paramclass_479, localclass_482, paramVector3f1);
                build(f, i, paramInt2 + k, paramShort, class_433.a(paramShort, paramBoolean, paramInt1, true, false, false), paramBoolean, paramclass_479, localclass_482, paramVector3f1);
                build(f + n, i + m, paramInt2, paramShort, class_433.a(paramShort, paramBoolean, paramInt1, false, true, true), paramBoolean, paramclass_479, localclass_482, paramVector3f1);
                build(f + n, i, paramInt2 + k, paramShort, class_433.a(paramShort, paramBoolean, paramInt1, true, false, true), paramBoolean, paramclass_479, localclass_482, paramVector3f1);
                build(f, i + m, paramInt2 + k, paramShort, class_433.a(paramShort, paramBoolean, paramInt1, true, true, false), paramBoolean, paramclass_479, localclass_482, paramVector3f1);
                build(f + n, i + m, paramInt2 + k, paramShort, class_433.a(paramShort, paramBoolean, paramInt1, true, true, true), paramBoolean, paramclass_479, localclass_482, paramVector3f1);
              }
            }
          }
        }
        paramInt2++;
      }
      return paramVector3f1[0];
    }
    System.err.println("no intersection found in world currentSegmentContext");
    return 0;
  }
  
  private void build(int paramInt1, int paramInt2, int paramInt3, short paramShort, int paramInt4, boolean paramBoolean, class_479 paramclass_479, class_48 paramclass_48, int[] paramArrayOfInt)
  {
    if ((paramArrayOfInt[1] > 0) && ((paramInt1 = getSegmentBuffer().a9(new class_48(paramInt1, paramInt2, paramInt3), false)) != null))
    {
      paramInt2 = 0;
      if (paramInt1.a7().a10(paramShort, paramInt1.a6(this.tmpLocalPos), paramInt4, paramBoolean))
      {
        this.lastEditBlocks = System.currentTimeMillis();
        ((class_672)paramInt1.a7()).a46(System.currentTimeMillis());
        paramInt1.a12();
        paramInt2 = paramInt1.a2(new class_48());
        paramclass_479.a(paramInt2, paramclass_48, paramShort);
        paramInt1.a15(getState().getId());
        getNetworkObject().modificationBuffer.add(new RemoteSegmentPiece(paramInt1, this, getNetworkObject()));
        assert (getNetworkObject().modificationBuffer.hasChanged());
        assert (getNetworkObject().isChanged());
        paramArrayOfInt[0] += 1;
        paramArrayOfInt[1] -= 1;
        return;
      }
      System.err.println("Block at " + paramInt1 + " already exists");
    }
  }
  
  public class_796 getNearestPiece(Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat, class_48 paramclass_481, class_48 paramclass_482)
  {
    Vector3f localVector3f = new Vector3f();
    paramVector3f2.scale(paramFloat);
    localVector3f.add(paramVector3f1, paramVector3f2);
    (paramVector3f2 = new CubeRayCastResult(paramVector3f1, localVector3f, Boolean.valueOf(false), this)).setRespectShields(false);
    paramVector3f2.onlyCubeMeshes = true;
    paramVector3f2.setIgnoereNotPhysical(true);
    if (((paramVector3f1 = getPhysics().testRayCollisionPoint(paramVector3f1, localVector3f, paramVector3f2, false)).hasHit()) && (paramVector3f1.collisionObject != null) && ((paramVector3f1 instanceof CubeRayCastResult)) && (((CubeRayCastResult)paramVector3f1).getSegment() != null))
    {
      (paramVector3f1 = (CubeRayCastResult)paramVector3f1).getSegment().a16().getSegmentController();
      paramVector3f2 = paramVector3f1.getSegment();
      (paramFloat = new class_48(paramVector3f1.getSegment().field_34.field_475, paramVector3f1.getSegment().field_34.field_476, paramVector3f1.getSegment().field_34.field_477)).field_475 += paramVector3f1.cubePos.field_453 - 8;
      paramFloat.field_476 += paramVector3f1.cubePos.field_454 - 8;
      paramFloat.field_477 += paramVector3f1.cubePos.field_455 - 8;
      getWorldTransformInverse().transform(paramVector3f1.hitPointWorld);
      int i = Element.getSide(paramVector3f1.hitPointWorld, paramFloat);
      System.err.println("SIDE: " + Element.getSideString(i) + ": " + paramVector3f1.hitPointWorld + "; " + paramFloat);
      paramclass_482.field_475 = (-paramclass_482.field_475);
      paramclass_482.field_476 = (-paramclass_482.field_476);
      paramclass_482.field_477 = (-paramclass_482.field_477);
      switch (i)
      {
      case 0: 
        paramclass_481.b(paramclass_482.field_475, paramclass_482.field_476, paramclass_482.field_477);
        break;
      case 1: 
        paramclass_481.b(-paramclass_482.field_475, paramclass_482.field_476, paramclass_482.field_477);
        break;
      case 2: 
        paramclass_481.b(paramclass_482.field_475, paramclass_482.field_476, paramclass_482.field_477);
        break;
      case 3: 
        paramclass_481.b(paramclass_482.field_475, -paramclass_482.field_476, paramclass_482.field_477);
        break;
      case 4: 
        paramclass_481.b(paramclass_482.field_475, paramclass_482.field_476, paramclass_482.field_477);
        break;
      case 5: 
        paramclass_481.b(paramclass_482.field_475, paramclass_482.field_476, -paramclass_482.field_477);
        break;
      default: 
        System.err.println("[BUILDMODEDRAWER] WARNING: NO SIDE recognized!!!");
      }
      return new class_796(paramVector3f2, paramVector3f1.cubePos);
    }
    return null;
  }
  
  public NetworkSegmentController getNetworkObject()
  {
    return super.getNetworkObject();
  }
  
  public class_796 getNextToNearestPiece(Vector3f paramVector3f1, Vector3f paramVector3f2, class_48 paramclass_481, float paramFloat, boolean paramBoolean, class_48 paramclass_482, class_48 paramclass_483)
  {
    paramBoolean = new Vector3f();
    paramVector3f2.normalize();
    paramVector3f2.scale(paramFloat);
    paramBoolean.add(paramVector3f1, paramVector3f2);
    (paramVector3f2 = new CubeRayCastResult(paramVector3f1, paramBoolean, Boolean.valueOf(false), this)).setRespectShields(false);
    paramVector3f2.onlyCubeMeshes = true;
    paramVector3f2.setIgnoereNotPhysical(true);
    if (((paramVector3f1 = getPhysics().testRayCollisionPoint(paramVector3f1, paramBoolean, paramVector3f2, false)) != null) && (paramVector3f1.hasHit()) && ((paramVector3f1 instanceof CubeRayCastResult)))
    {
      if ((paramVector3f2 = (CubeRayCastResult)paramVector3f1).getSegment() == null)
      {
        System.err.println("CUBERESULT SEGMENT NULL");
        return null;
      }
      paramFloat = new class_48(paramVector3f2.getSegment().field_34.field_475, paramVector3f2.getSegment().field_34.field_476, paramVector3f2.getSegment().field_34.field_477);
      paramclass_481.b(paramVector3f2.getSegment().field_34.field_475 + paramVector3f2.cubePos.field_453, paramVector3f2.getSegment().field_34.field_476 + paramVector3f2.cubePos.field_454, paramVector3f2.getSegment().field_34.field_477 + paramVector3f2.cubePos.field_455);
      paramFloat.field_475 += paramVector3f2.cubePos.field_453 - 8;
      paramFloat.field_476 += paramVector3f2.cubePos.field_454 - 8;
      paramFloat.field_477 += paramVector3f2.cubePos.field_455 - 8;
      if (((class_371)getState()).a8() == getSectorId())
      {
        getWorldTransformInverse().transform(paramVector3f1.hitPointWorld);
      }
      else
      {
        (paramclass_481 = new Transform(getWorldTransformClient())).inverse();
        paramclass_481.transform(paramVector3f1.hitPointWorld);
      }
      paramclass_481 = Element.getSide(paramVector3f2.hitPointWorld, paramFloat);
      System.err.println("SIDE: " + Element.getSideString(paramclass_481) + ": " + paramVector3f2.hitPointWorld + "; " + paramFloat);
      switch (paramclass_481)
      {
      case 0: 
        float tmp422_420 = paramFloat;
        tmp422_420.field_475 = ((int)(tmp422_420.field_475 + 1.0F));
        paramclass_483.b(paramclass_482.field_475, paramclass_482.field_476, paramclass_482.field_477);
        break;
      case 1: 
        float tmp458_456 = paramFloat;
        tmp458_456.field_475 = ((int)(tmp458_456.field_475 - 1.0F));
        paramclass_483.b(-paramclass_482.field_475, paramclass_482.field_476, paramclass_482.field_477);
        break;
      case 2: 
        float tmp495_493 = paramFloat;
        tmp495_493.field_476 = ((int)(tmp495_493.field_476 + 1.0F));
        paramclass_483.b(paramclass_482.field_475, paramclass_482.field_476, paramclass_482.field_477);
        break;
      case 3: 
        float tmp531_529 = paramFloat;
        tmp531_529.field_476 = ((int)(tmp531_529.field_476 - 1.0F));
        paramclass_483.b(paramclass_482.field_475, -paramclass_482.field_476, paramclass_482.field_477);
        break;
      case 4: 
        float tmp568_566 = paramFloat;
        tmp568_566.field_477 = ((int)(tmp568_566.field_477 + 1.0F));
        paramclass_483.b(paramclass_482.field_475, paramclass_482.field_476, paramclass_482.field_477);
        break;
      case 5: 
        float tmp604_602 = paramFloat;
        tmp604_602.field_477 = ((int)(tmp604_602.field_477 - 1.0F));
        paramclass_483.b(paramclass_482.field_475, paramclass_482.field_476, -paramclass_482.field_477);
        break;
      default: 
        System.err.println("[BUILDMODEDRAWER] WARNING: NO SIDE recognized!!!");
      }
      paramFloat.a(8, 8, 8);
      paramVector3f1 = new class_796();
      if (((paramVector3f1 = getSegmentBuffer().a10(paramFloat, true, paramVector3f1)) != null) && (paramVector3f1.a7().g())) {
        getSegmentProvider().a25().assignData(paramVector3f1.a7());
      }
      paramVector3f2 = 0;
      paramclass_481 = new class_892();
      try
      {
        if ((paramVector3f1 != null) && (getCollisionChecker().a(paramVector3f1, paramclass_481))) {
          paramVector3f2 = 1;
        }
      }
      catch (Exception localException)
      {
        localException;
      }
      if ((!getDockingController().a5().isEmpty()) && ((this instanceof class_798)) && ((((class_798)this).a() instanceof DockingBlockManagerInterface)))
      {
        paramBoolean = getDockingController().a5().iterator();
        while (paramBoolean.hasNext())
        {
          paramclass_482 = (ElementDocking)paramBoolean.next();
          paramclass_483 = ((DockingBlockManagerInterface)((class_798)this).a()).getDockingBlock().iterator();
          while (paramclass_483.hasNext())
          {
            Iterator localIterator = ((ManagerModuleCollection)paramclass_483.next()).getCollectionManagers().iterator();
            while (localIterator.hasNext())
            {
              DockingBlockCollectionManager localDockingBlockCollectionManager;
              if (((localDockingBlockCollectionManager = (DockingBlockCollectionManager)localIterator.next()).getControllerElement().equals(paramclass_482.field_1740)) && (!localDockingBlockCollectionManager.isValidPositionToBuild(paramFloat))) {
                throw new BlockedByDockedElementException(paramclass_482.field_1740);
              }
            }
          }
        }
      }
      if (paramVector3f2 != 0) {
        throw new ElementPositionBlockedException(paramclass_481.field_1122);
      }
      return paramVector3f1;
    }
    return null;
  }
  
  public void handleBeingSalvaged(BeamHandler.BeamState paramBeamState, class_721 paramclass_721, Vector3f paramVector3f, CubeRayCastResult paramCubeRayCastResult, int paramInt)
  {
    if ((this instanceof class_784)) {
      ((class_784)this).a(true);
    }
  }
  
  public void handleExplosion(Transform paramTransform, float paramFloat1, float paramFloat2, class_809 paramclass_809, byte paramByte)
  {
    System.err.println("[HIT][EXPLOSION] Handling explosion: " + this + " " + paramTransform + ", R: " + paramFloat1 + ", dam " + paramFloat2 + " from " + paramclass_809);
    if ((this instanceof class_784)) {
      ((class_784)this).a(true);
    }
    if ((paramByte != 1) && ((this instanceof class_798)) && ((((class_798)this).a() instanceof ShieldContainerInterface)) && ((paramByte = (ShieldContainerInterface)((class_798)this).a()).getShieldManager().getShields() > 0.0D))
    {
      double d1 = paramFloat2 * Math.max(1.0F, paramFloat1 / 2.0F);
      double d2 = paramByte.getShieldManager().getShields();
      if ((paramFloat2 = (float)paramByte.handleShieldHit(paramTransform.origin, null, paramFloat2 * Math.max(1.0F, paramFloat1 / 2.0F))) <= 0.0F)
      {
        System.err.println("[EXPLOSION] " + this + " Shield completely absorbed damage: SHIELDS: " + d2 + " -> " + paramByte.getShieldManager().getShields() + " DAM: " + d1 + " -> " + paramFloat2);
        return;
      }
    }
    paramByte = paramFloat2;
    if (isOnServer())
    {
      Object localObject1;
      if (getFactionId() > 0) {
        if ((localObject1 = ((class_1039)getState()).a().a156(getFactionId())) != null) {
          ((class_773)localObject1).a117(paramclass_809);
        } else {
          System.err.println("[SERVER][EDITABLESEGMENTCONTROLLER] ON HIT: faction not found: " + getFactionId());
        }
      }
      if (((localObject1 = ((class_1041)getState()).a62().getSector(getSectorId())) != null) && (((class_670)localObject1).b2()))
      {
        if ((paramclass_809 != null) && ((paramclass_809 instanceof class_365)))
        {
          localObject2 = ((class_365)paramclass_809).a26();
          for (int i = 0; i < ((ArrayList)localObject2).size(); i++)
          {
            class_748 localclass_748 = (class_748)((ArrayList)localObject2).get(i);
            if (System.currentTimeMillis() - localclass_748.field_136 > 5000L)
            {
              localclass_748.field_136 = System.currentTimeMillis();
              localclass_748.a129(new ServerMessage("This Sector is Protected!", 2, localclass_748.getId()));
            }
          }
        }
        return;
      }
      if (!canAttack(paramclass_809)) {
        return;
      }
      Object localObject2 = new class_807(this, paramTransform, paramFloat1, paramByte, paramclass_809);
      this.explosionOrdersRunning.add(localObject2);
      getState().getThreadPool().execute((Runnable)localObject2);
    }
  }
  
  public boolean canAttack(class_809 paramclass_809)
  {
    if ((isHomeBase()) || ((getDockingController().b3()) && (getDockingController().a4().field_1740.a7().a15().isHomeBaseFor(getFactionId()))))
    {
      if ((paramclass_809 != null) && ((paramclass_809 instanceof class_365)))
      {
        paramclass_809 = ((class_365)paramclass_809).a26();
        for (int i = 0; i < paramclass_809.size(); i++)
        {
          class_748 localclass_748 = (class_748)paramclass_809.get(i);
          if (System.currentTimeMillis() - localclass_748.field_136 > 5000L)
          {
            localclass_748.field_136 = System.currentTimeMillis();
            localclass_748.a129(new ServerMessage("Cannot attack a faction's\nhome base!", 2, localclass_748.getId()));
          }
        }
      }
      return false;
    }
    return true;
  }
  
  public void handleHit(CollisionWorld.ClosestRayResultCallback paramClosestRayResultCallback, class_809 paramclass_809, float paramFloat)
  {
    CubeRayCastResult localCubeRayCastResult = (CubeRayCastResult)paramClosestRayResultCallback;
    assert (localCubeRayCastResult != null);
    assert (localCubeRayCastResult.getSegment() != null) : localCubeRayCastResult.hasHit();
    assert (localCubeRayCastResult.getSegment().a16() != null) : localCubeRayCastResult.getSegment().field_34;
    if ((localCubeRayCastResult == null) || (localCubeRayCastResult.getSegment() == null) || (localCubeRayCastResult.getSegment().a16() == null)) {
      return;
    }
    if ((this instanceof class_784)) {
      ((class_784)this).a(true);
    }
    SegmentData localSegmentData = localCubeRayCastResult.getSegment().a16();
    int i = SegmentData.getInfoIndex(localCubeRayCastResult.cubePos);
    short s = localSegmentData.getType(i);
    localSegmentData.getHitpoints(i);
    class_796 localclass_796 = new class_796(localCubeRayCastResult.getSegment(), localCubeRayCastResult.cubePos);
    if (isOnServer())
    {
      Object localObject;
      if (getFactionId() > 0) {
        if ((localObject = ((class_1039)getState()).a().a156(getFactionId())) != null) {
          ((class_773)localObject).a117(paramclass_809);
        } else {
          System.err.println("[SERVER][EDITABLESEGMENTCONTROLLER] ON HIT: faction not found: " + getFactionId());
        }
      }
      if (((localObject = ((class_1041)getState()).a62().getSector(getSectorId())) != null) && (((class_670)localObject).b2()))
      {
        if ((paramclass_809 != null) && ((paramclass_809 instanceof class_365)))
        {
          localObject = ((class_365)paramclass_809).a26();
          for (paramclass_809 = 0; paramclass_809 < ((ArrayList)localObject).size(); paramclass_809++)
          {
            paramFloat = (class_748)((ArrayList)localObject).get(paramclass_809);
            if (System.currentTimeMillis() - paramFloat.field_136 > 5000L)
            {
              paramFloat.field_136 = System.currentTimeMillis();
              paramFloat.a129(new ServerMessage("This Sector is Protected!", 2, paramFloat.getId()));
            }
          }
        }
        return;
      }
      if (!canAttack(paramclass_809)) {
        return;
      }
      int j;
      if ((j = damageElement(s, i, localSegmentData, (int)paramFloat)) > 0) {
        onDamageServer(j, paramclass_809);
      }
      if (localSegmentData.getHitpoints(i) <= 0)
      {
        if ((s == getCoreType()) && (localclass_796.a2(this.absPosCache).equals(class_747.field_136)))
        {
          localSegmentData.setHitpoints(i, (short)0);
          onCoreDestroyed(paramclass_809);
          onCoreHitAlreadyDestroyed(paramFloat);
        }
        else
        {
          localCubeRayCastResult.getSegment().a20(localCubeRayCastResult.cubePos, true);
        }
        if (isEnterable(s)) {
          forceCharacterExit(localclass_796);
        }
      }
      ((class_672)localCubeRayCastResult.getSegment()).a46(System.currentTimeMillis());
      localclass_796.a12();
      getNetworkObject().modificationBuffer.add(new RemoteSegmentPiece(localclass_796, this, getNetworkObject()));
    }
    if (!isOnServer())
    {
      ElementInformation localElementInformation = ElementKeyMap.getInfo(s);
      int k = (int)Math.max(0.0D, paramFloat - Math.ceil(paramFloat * localElementInformation.getArmourPercent()));
      (paramclass_809 = new Transform()).setIdentity();
      paramclass_809.origin.set(paramClosestRayResultCallback.hitPointWorld);
      class_883.field_89.add(new class_216(paramclass_809, String.valueOf(k), 1.0F, 0.0F, 0.0F));
      ((class_371)getState()).a27().a91().a22(paramClosestRayResultCallback);
      if (k < 300)
      {
        class_969.a8("0022_spaceship enemy - hit small explosion small enemy ship blow up", paramclass_809, 5.0F);
        return;
      }
      if (k < 600)
      {
        class_969.a8("0022_spaceship enemy - hit medium explosion medium enemy ship blow up", paramclass_809, 10.0F);
        return;
      }
      class_969.a8("0022_spaceship enemy - hit large explosion big enemy ship blow up", paramclass_809, 30.0F);
    }
  }
  
  public void onCoreHitAlreadyDestroyed(float paramFloat) {}
  
  public void handleHitMissile(class_597 paramclass_597, Transform paramTransform)
  {
    handleExplosion(paramTransform, paramclass_597.a(), paramclass_597.a1(), paramclass_597.a4(), (byte)0);
  }
  
  public void handleMovingInput(class_941 paramclass_941, class_755 paramclass_755) {}
  
  public int handleRepair(BeamHandler.BeamState paramBeamState, class_721 paramclass_721, class_48 paramclass_48, Vector3f paramVector3f, CubeRayCastResult paramCubeRayCastResult, class_941 paramclass_941)
  {
    paramclass_48 = new class_796(paramCubeRayCastResult.getSegment(), paramCubeRayCastResult.cubePos);
    paramclass_721 = (paramBeamState = paramclass_721.getHandler().beamHit(paramBeamState, paramclass_48)) * 20;
    if ((paramBeamState > 0) && (isOnServer()))
    {
      paramclass_48.a7().a16();
      paramVector3f = SegmentData.getInfoIndex(paramclass_48.a6(this.tmpLocalPos));
      paramclass_941 = paramclass_48.a7().a16().getHitpoints(paramVector3f);
      int i = ElementKeyMap.getInfo(paramclass_48.a9()).getMaxHitPoints();
      paramclass_48.a7().a16().setHitpoints(paramVector3f, (short)Math.min(i, paramclass_941 + paramclass_721));
      ((class_672)paramclass_48.a7()).a46(System.currentTimeMillis());
      paramclass_48.a12();
      paramclass_48.a15(getState().getId());
      ((NetworkSegmentController)paramclass_48.a7().a15().getNetworkObject()).modificationBuffer.add(new RemoteSegmentPiece(paramclass_48, this, getNetworkObject()));
    }
    if ((paramBeamState > 0) && (!isOnServer()))
    {
      ElementKeyMap.getInfo(paramclass_48.a9());
      paramclass_941 = Math.max(0, paramclass_721);
      Transform localTransform;
      (localTransform = new Transform()).setIdentity();
      localTransform.origin.set(paramCubeRayCastResult.hitPointWorld);
      class_883.field_89.add(new class_216(localTransform, String.valueOf(paramclass_941), 0.0F, 1.0F, 0.0F));
    }
    return paramBeamState;
  }
  
  private boolean isEnterable(short paramShort)
  {
    return (paramShort != 0) && (ElementKeyMap.getInfo(paramShort).isEnterable());
  }
  
  public boolean isRepariableFor(class_852 paramclass_852, String[] paramArrayOfString, class_48 paramclass_48)
  {
    return true;
  }
  
  public void newNetworkObject()
  {
    setNetworkObject(new NetworkSegmentController(getState(), this));
  }
  
  public void onAddedElement(short paramShort, byte paramByte1, byte paramByte2, byte paramByte3, int paramInt, Segment paramSegment, boolean paramBoolean)
  {
    super.onAddedElement(paramShort, paramByte1, paramByte2, paramByte3, paramInt, paramSegment, paramBoolean);
    if (isOnServer()) {
      doDimExtensionIfNecessary(paramSegment, paramByte1, paramByte2, paramByte3);
    }
  }
  
  public void onCollision(ManifoldPoint paramManifoldPoint, Sendable paramSendable) {}
  
  public boolean needsManifoldCollision()
  {
    return getElementClassCountMap().a1((short)14) > 0;
  }
  
  protected abstract void onCoreDestroyed(class_809 paramclass_809);
  
  protected void onDamageServer(int paramInt, class_809 paramclass_809) {}
  
  public void startCreatorThread()
  {
    if (getCreatorThread() == null) {
      setCreatorThread(new class_912(this));
    }
  }
  
  public String toString()
  {
    return getSegmentControllerTypeString() + "(" + getId() + ")";
  }
  
  protected abstract String getSegmentControllerTypeString();
  
  public void updateLocal(class_941 paramclass_941)
  {
    if (isMarkedForDeleteVolatile()) {
      System.err.println("[EditableSegmentControleler] " + this + " MARKED TO DELETE ON " + getState());
    }
    if (isOnServer()) {
      for (int i = 0; i < this.explosionOrdersRunning.size(); i++)
      {
        class_807 localclass_807;
        if ((localclass_807 = (class_807)this.explosionOrdersRunning.get(i)).a1())
        {
          localclass_807.a();
          this.explosionOrdersRunning.remove(i);
          i--;
        }
      }
    }
    if (getFlagCoreDestroyedByExplosion() != null)
    {
      System.err.println("[EditSegController] " + this + " CORE HAS BEEN DESTROYED");
      if ((getFlagCoreDestroyedByExplosion() instanceof Sendable)) {
        onCoreDestroyed((class_809)getFlagCoreDestroyedByExplosion());
      } else {
        onCoreDestroyed(null);
      }
      setFlagCoreDestroyedByExplosion(null);
    }
    if (isFlagCharacterExitCheckByExplosion())
    {
      try
      {
        checkCharacterExit();
      }
      catch (IOException localIOException)
      {
        localIOException;
      }
      catch (InterruptedException localInterruptedException)
      {
        localInterruptedException;
      }
      setFlagCharacterExitCheckByExplosion(false);
    }
    super.updateLocal(paramclass_941);
  }
  
  public Object getFlagCoreDestroyedByExplosion()
  {
    return this.flagCoreDestroyedByExplosion;
  }
  
  public void setFlagCoreDestroyedByExplosion(Object paramObject)
  {
    this.flagCoreDestroyedByExplosion = paramObject;
  }
  
  public boolean isFlagCharacterExitCheckByExplosion()
  {
    return this.flagCharacterExitCheckByExplosion;
  }
  
  public void setFlagCharacterExitCheckByExplosion(boolean paramBoolean)
  {
    this.flagCharacterExitCheckByExplosion = paramBoolean;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.EditableSendableSegmentController
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */