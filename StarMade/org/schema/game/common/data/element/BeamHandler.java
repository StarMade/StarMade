package org.schema.game.common.data.element;

import class_229;
import class_371;
import class_48;
import class_52;
import class_721;
import class_748;
import class_796;
import class_941;
import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import javax.vecmath.Vector3f;
import org.schema.common.FastMath;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.physics.CubeRayCastResult;
import org.schema.game.common.data.physics.PhysicsExt;
import org.schema.game.common.data.world.Segment;
import org.schema.game.common.data.world.SegmentData;
import org.schema.schine.network.StateInterface;

public abstract class BeamHandler
{
  private final SegmentController segmentController;
  private class_721 owner;
  private final Long2ObjectOpenHashMap beamStates = new Long2ObjectOpenHashMap();
  BeamHandler.BeamState sTmp = new BeamHandler.BeamState(this, new class_48(), new Vector3f(), new Vector3f(), null, 0.0F);
  Vector3f start = new Vector3f();
  Vector3f end = new Vector3f();
  private boolean lastActive;
  String[] cannotHitReason = new String[1];
  private class_48 posTmp = new class_48();
  private final HashSet updatedSegments = new HashSet();
  private Vector3f dirTmp = new Vector3f();
  private class_229 drawer;
  
  public BeamHandler(SegmentController paramSegmentController, class_721 paramclass_721)
  {
    this.segmentController = paramSegmentController;
    this.owner = paramclass_721;
  }
  
  public void clearStates()
  {
    this.beamStates.clear();
    if (this.drawer != null) {
      this.drawer.a12(this, false);
    }
  }
  
  public void addBeam(class_48 paramclass_48, Vector3f paramVector3f1, Vector3f paramVector3f2, class_748 paramclass_748, float paramFloat)
  {
    long l = ElementCollection.getIndex(paramclass_48);
    if (!this.beamStates.containsKey(l))
    {
      (paramclass_48 = new BeamHandler.BeamState(this, paramclass_48, paramVector3f1, paramVector3f2, paramclass_748, paramFloat)).timeRunning = 0.0F;
      this.beamStates.put(l, paramclass_48);
      return;
    }
    (paramclass_48 = (BeamHandler.BeamState)this.beamStates.get(l)).from.set(paramVector3f1);
    paramclass_48.field_1883.set(paramVector3f2);
    paramclass_48.timeRunning = 0.0F;
    paramclass_48.playerState = paramclass_748;
  }
  
  public int beamHit(BeamHandler.BeamState paramBeamState, class_796 paramclass_796)
  {
    assert (paramclass_796 != null);
    if (paramclass_796.equals(paramBeamState.segmentHit))
    {
      paramBeamState.hitOneSegment += paramBeamState.timeSpent;
      paramBeamState.timeSpent = 0.0F;
    }
    else
    {
      paramBeamState.segmentHit = paramclass_796;
      paramBeamState.hitOneSegment = 0.0F;
    }
    float f = getBeamToHitInSecs(paramBeamState);
    paramclass_796 = FastMath.a9(paramBeamState.hitOneSegment / f);
    paramBeamState.hitOneSegment -= paramclass_796 * f;
    this.cannotHitReason[0] = "";
    return paramclass_796;
  }
  
  public abstract boolean canhit(SegmentController paramSegmentController, String[] paramArrayOfString, class_48 paramclass_48);
  
  public synchronized BeamHandler.BeamState getBeam(class_48 paramclass_48)
  {
    if ((paramclass_48 = (BeamHandler.BeamState)getBeamStates().get(ElementCollection.getIndex(paramclass_48))) != null) {
      return paramclass_48;
    }
    throw new BeamHandler.ElementNotFoundException(this);
  }
  
  public Long2ObjectOpenHashMap getBeamStates()
  {
    return this.beamStates;
  }
  
  public abstract float getBeamTimeoutInSecs();
  
  public abstract float getBeamToHitInSecs(BeamHandler.BeamState paramBeamState);
  
  public SegmentController getSegmentController()
  {
    return this.segmentController;
  }
  
  public boolean isAnyBeamActiveActive()
  {
    return !this.beamStates.isEmpty();
  }
  
  public abstract void onBeamHit(BeamHandler.BeamState paramBeamState, class_721 paramclass_721, class_48 paramclass_48, Segment paramSegment, Vector3f paramVector3f1, Vector3f paramVector3f2, CubeRayCastResult paramCubeRayCastResult, class_941 paramclass_941, Collection paramCollection);
  
  public void update(class_941 paramclass_941)
  {
    if (getBeamStates().isEmpty()) {
      return;
    }
    System.currentTimeMillis();
    this.updatedSegments.clear();
    ObjectIterator localObjectIterator = getBeamStates().values().iterator();
    float f = paramclass_941.a();
    while (localObjectIterator.hasNext())
    {
      if ((localObject = (BeamHandler.BeamState)localObjectIterator.next()).timeRunning > getBeamTimeoutInSecs())
      {
        localObjectIterator.remove();
      }
      else
      {
        this.posTmp.b(((BeamHandler.BeamState)localObject).elementPos.field_475 - 8, ((BeamHandler.BeamState)localObject).elementPos.field_476 - 8, ((BeamHandler.BeamState)localObject).elementPos.field_477 - 8);
        getSegmentController().getAbsoluteElementWorldPosition(this.posTmp, ((BeamHandler.BeamState)localObject).from);
        this.start.set(((BeamHandler.BeamState)localObject).from);
        this.end.set(((BeamHandler.BeamState)localObject).field_1883);
        updateBeam(this.start, this.end, (BeamHandler.BeamState)localObject, paramclass_941, this.updatedSegments);
      }
      localObject.timeRunning += f;
    }
    Object localObject = this.updatedSegments.iterator();
    while (((Iterator)localObject).hasNext()) {
      if ((paramclass_941 = (Segment)((Iterator)localObject).next()).a16() != null) {
        paramclass_941.a16().restructBB(true);
      }
    }
    System.currentTimeMillis();
    if ((this.lastActive != isAnyBeamActiveActive()) && (this.drawer != null)) {
      this.drawer.a12(this, isAnyBeamActiveActive());
    }
    this.lastActive = isAnyBeamActiveActive();
  }
  
  public void updateBeam(Vector3f paramVector3f1, Vector3f paramVector3f2, BeamHandler.BeamState paramBeamState, class_941 paramclass_941, Collection paramCollection)
  {
    Object localObject = getSegmentController();
    paramBeamState.timeSpent += paramclass_941.a();
    if (!paramBeamState.checkNecessary(paramclass_941, paramBeamState))
    {
      if (paramBeamState.hitPoint != null)
      {
        paramBeamState.hitPoint.sub(paramVector3f1);
        this.dirTmp.set(paramVector3f2);
        this.dirTmp.sub(paramVector3f1);
        this.dirTmp.normalize();
        this.dirTmp.scale(paramBeamState.hitPoint.length());
        this.dirTmp.add(paramVector3f1);
        paramBeamState.hitPoint.set(this.dirTmp);
      }
      return;
    }
    if (((localObject = ((SegmentController)localObject).getPhysics().testRayCollisionPoint(paramVector3f1, paramVector3f2, false, (SegmentController)localObject, null, false, null, true)).hasHit()) && (((CollisionWorld.ClosestRayResultCallback)localObject).collisionObject != null))
    {
      paramBeamState.hitPoint = ((CollisionWorld.ClosestRayResultCallback)localObject).hitPointWorld;
      if (((localObject instanceof CubeRayCastResult)) && (((CubeRayCastResult)localObject).getSegment() != null))
      {
        SegmentController localSegmentController = (localObject = (CubeRayCastResult)localObject).getSegment().a15();
        paramBeamState.cachedLastSegment = ((CubeRayCastResult)localObject).getSegment();
        this.cannotHitReason[0] = "";
        class_796 localclass_796 = new class_796(((CubeRayCastResult)localObject).getSegment(), ((CubeRayCastResult)localObject).cubePos);
        if (canhit(localSegmentController, this.cannotHitReason, localclass_796.a2(new class_48())))
        {
          onBeamHit(paramBeamState, this.owner, paramBeamState.elementPos, ((CubeRayCastResult)localObject).getSegment(), paramVector3f1, paramVector3f2, (CubeRayCastResult)localObject, paramclass_941, paramCollection);
          return;
        }
        if ((!localSegmentController.isOnServer()) && (((class_371)getSegmentController().getState()).a6() == getSegmentController())) {
          ((class_52)localSegmentController.getState().getController()).d1(localSegmentController.toNiceString() + "\ncannot be hit by this beam.\n" + this.cannotHitReason[0]);
        }
      }
    }
    else
    {
      paramBeamState.hitPoint = null;
      paramBeamState.cachedLastSegment = null;
    }
    paramBeamState.segmentHit = null;
    paramBeamState.hitOneSegment = 0.0F;
  }
  
  public void setDrawer(class_229 paramclass_229)
  {
    this.drawer = paramclass_229;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.element.BeamHandler
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */