package com.bulletphysics.collision.narrowphase;

import com.bulletphysics..Stack;
import com.bulletphysics.BulletGlobals;
import com.bulletphysics.ContactDestroyedCallback;
import com.bulletphysics.ContactProcessedCallback;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.linearmath.VectorUtil;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;

public class PersistentManifold
{
  public static final int MANIFOLD_CACHE_SIZE = 4;
  private final ManifoldPoint[] pointCache = new ManifoldPoint[4];
  private Object body0;
  private Object body1;
  private int cachedPoints;
  public int index1a;
  
  public PersistentManifold()
  {
    for (int local_i = 0; local_i < this.pointCache.length; local_i++) {
      this.pointCache[local_i] = new ManifoldPoint();
    }
  }
  
  public PersistentManifold(Object body0, Object body1, int bla)
  {
    for (int local_i = 0; local_i < this.pointCache.length; local_i++) {
      this.pointCache[local_i] = new ManifoldPoint();
    }
    init(body0, body1, bla);
  }
  
  public void init(Object body0, Object body1, int bla)
  {
    this.body0 = body0;
    this.body1 = body1;
    this.cachedPoints = 0;
    this.index1a = 0;
  }
  
  private int sortCachedPoints(ManifoldPoint arg1)
  {
    .Stack localStack = .Stack.get();
    try
    {
      .Stack tmp7_5 = localStack;
      tmp7_5.push$javax$vecmath$Vector3f();
      tmp7_5.push$javax$vecmath$Vector4f();
      int maxPenetrationIndex = -1;
      float maxPenetration = local_pt.getDistance();
      for (int local_i = 0; local_i < 4; local_i++) {
        if (this.pointCache[local_i].getDistance() < maxPenetration)
        {
          maxPenetrationIndex = local_i;
          maxPenetration = this.pointCache[local_i].getDistance();
        }
      }
      float local_i = 0.0F;
      float res1 = 0.0F;
      float res2 = 0.0F;
      float res3 = 0.0F;
      if (maxPenetrationIndex != 0)
      {
        Vector3f local_a0 = localStack.get$javax$vecmath$Vector3f(local_pt.localPointA);
        local_a0.sub(this.pointCache[1].localPointA);
        Vector3f local_b0 = localStack.get$javax$vecmath$Vector3f(this.pointCache[3].localPointA);
        local_b0.sub(this.pointCache[2].localPointA);
        Vector3f cross = localStack.get$javax$vecmath$Vector3f();
        cross.cross(local_a0, local_b0);
        local_i = cross.lengthSquared();
      }
      if (maxPenetrationIndex != 1)
      {
        Vector3f local_a0 = localStack.get$javax$vecmath$Vector3f(local_pt.localPointA);
        local_a0.sub(this.pointCache[0].localPointA);
        Vector3f local_b0 = localStack.get$javax$vecmath$Vector3f(this.pointCache[3].localPointA);
        local_b0.sub(this.pointCache[2].localPointA);
        Vector3f cross = localStack.get$javax$vecmath$Vector3f();
        cross.cross(local_a0, local_b0);
        res1 = cross.lengthSquared();
      }
      if (maxPenetrationIndex != 2)
      {
        Vector3f local_a0 = localStack.get$javax$vecmath$Vector3f(local_pt.localPointA);
        local_a0.sub(this.pointCache[0].localPointA);
        Vector3f local_b0 = localStack.get$javax$vecmath$Vector3f(this.pointCache[3].localPointA);
        local_b0.sub(this.pointCache[1].localPointA);
        Vector3f cross = localStack.get$javax$vecmath$Vector3f();
        cross.cross(local_a0, local_b0);
        res2 = cross.lengthSquared();
      }
      if (maxPenetrationIndex != 3)
      {
        Vector3f local_a0 = localStack.get$javax$vecmath$Vector3f(local_pt.localPointA);
        local_a0.sub(this.pointCache[0].localPointA);
        Vector3f local_b0 = localStack.get$javax$vecmath$Vector3f(this.pointCache[2].localPointA);
        local_b0.sub(this.pointCache[1].localPointA);
        Vector3f cross = localStack.get$javax$vecmath$Vector3f();
        cross.cross(local_a0, local_b0);
        res3 = cross.lengthSquared();
      }
      Vector4f local_a0 = localStack.get$javax$vecmath$Vector4f();
      local_a0.set(local_i, res1, res2, res3);
      int local_b0 = VectorUtil.closestAxis4(local_a0);
      return local_b0;
    }
    finally
    {
      .Stack tmp457_455 = localStack;
      tmp457_455.pop$javax$vecmath$Vector3f();
      tmp457_455.pop$javax$vecmath$Vector4f();
    }
  }
  
  public Object getBody0()
  {
    return this.body0;
  }
  
  public Object getBody1()
  {
    return this.body1;
  }
  
  public void setBodies(Object body0, Object body1)
  {
    this.body0 = body0;
    this.body1 = body1;
  }
  
  public void clearUserCache(ManifoldPoint local_pt)
  {
    Object oldPtr = local_pt.userPersistentData;
    if ((oldPtr != null) && (local_pt.userPersistentData != null) && (BulletGlobals.getContactDestroyedCallback() != null))
    {
      BulletGlobals.getContactDestroyedCallback().contactDestroyed(local_pt.userPersistentData);
      local_pt.userPersistentData = null;
    }
  }
  
  public int getNumContacts()
  {
    return this.cachedPoints;
  }
  
  public ManifoldPoint getContactPoint(int index)
  {
    return this.pointCache[index];
  }
  
  public float getContactBreakingThreshold()
  {
    return BulletGlobals.getContactBreakingThreshold();
  }
  
  public int getCacheEntry(ManifoldPoint arg1)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      float shortestDist = getContactBreakingThreshold() * getContactBreakingThreshold();
      int size = getNumContacts();
      int nearestPoint = -1;
      Vector3f diffA = localStack.get$javax$vecmath$Vector3f();
      for (int local_i = 0; local_i < size; local_i++)
      {
        ManifoldPoint local_mp = this.pointCache[local_i];
        diffA.sub(local_mp.localPointA, newPoint.localPointA);
        float distToManiPoint = diffA.dot(diffA);
        if (distToManiPoint < shortestDist)
        {
          shortestDist = distToManiPoint;
          nearestPoint = local_i;
        }
      }
      return nearestPoint;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public int addManifoldPoint(ManifoldPoint newPoint)
  {
    assert (validContactDistance(newPoint));
    int insertIndex = getNumContacts();
    if (insertIndex == 4)
    {
      insertIndex = sortCachedPoints(newPoint);
      clearUserCache(this.pointCache[insertIndex]);
    }
    else
    {
      this.cachedPoints += 1;
    }
    assert (this.pointCache[insertIndex].userPersistentData == null);
    this.pointCache[insertIndex].set(newPoint);
    return insertIndex;
  }
  
  public void removeContactPoint(int index)
  {
    clearUserCache(this.pointCache[index]);
    int lastUsedIndex = getNumContacts() - 1;
    if (index != lastUsedIndex)
    {
      this.pointCache[index].set(this.pointCache[lastUsedIndex]);
      this.pointCache[lastUsedIndex].userPersistentData = null;
      this.pointCache[lastUsedIndex].appliedImpulse = 0.0F;
      this.pointCache[lastUsedIndex].lateralFrictionInitialized = false;
      this.pointCache[lastUsedIndex].appliedImpulseLateral1 = 0.0F;
      this.pointCache[lastUsedIndex].appliedImpulseLateral2 = 0.0F;
      this.pointCache[lastUsedIndex].lifeTime = 0;
    }
    assert (this.pointCache[lastUsedIndex].userPersistentData == null);
    this.cachedPoints -= 1;
  }
  
  public void replaceContactPoint(ManifoldPoint newPoint, int insertIndex)
  {
    assert (validContactDistance(newPoint));
    int lifeTime = this.pointCache[insertIndex].getLifeTime();
    float appliedImpulse = this.pointCache[insertIndex].appliedImpulse;
    float appliedLateralImpulse1 = this.pointCache[insertIndex].appliedImpulseLateral1;
    float appliedLateralImpulse2 = this.pointCache[insertIndex].appliedImpulseLateral2;
    assert (lifeTime >= 0);
    Object cache = this.pointCache[insertIndex].userPersistentData;
    this.pointCache[insertIndex].set(newPoint);
    this.pointCache[insertIndex].userPersistentData = cache;
    this.pointCache[insertIndex].appliedImpulse = appliedImpulse;
    this.pointCache[insertIndex].appliedImpulseLateral1 = appliedLateralImpulse1;
    this.pointCache[insertIndex].appliedImpulseLateral2 = appliedLateralImpulse2;
    this.pointCache[insertIndex].lifeTime = lifeTime;
  }
  
  private boolean validContactDistance(ManifoldPoint local_pt)
  {
    return local_pt.distance1 <= getContactBreakingThreshold();
  }
  
  public void refreshContactPoints(Transform arg1, Transform arg2)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
      for (int local_i = getNumContacts() - 1; local_i >= 0; local_i--)
      {
        ManifoldPoint manifoldPoint = this.pointCache[local_i];
        manifoldPoint.positionWorldOnA.set(manifoldPoint.localPointA);
        trA.transform(manifoldPoint.positionWorldOnA);
        manifoldPoint.positionWorldOnB.set(manifoldPoint.localPointB);
        trB.transform(manifoldPoint.positionWorldOnB);
        tmp.set(manifoldPoint.positionWorldOnA);
        tmp.sub(manifoldPoint.positionWorldOnB);
        manifoldPoint.distance1 = tmp.dot(manifoldPoint.normalWorldOnB);
        manifoldPoint.lifeTime += 1;
      }
      Vector3f projectedDifference = localStack.get$javax$vecmath$Vector3f();
      Vector3f projectedPoint = localStack.get$javax$vecmath$Vector3f();
      for (local_i = getNumContacts() - 1; local_i >= 0; local_i--)
      {
        ManifoldPoint manifoldPoint = this.pointCache[local_i];
        if (!validContactDistance(manifoldPoint))
        {
          removeContactPoint(local_i);
        }
        else
        {
          tmp.scale(manifoldPoint.distance1, manifoldPoint.normalWorldOnB);
          projectedPoint.sub(manifoldPoint.positionWorldOnA, tmp);
          projectedDifference.sub(manifoldPoint.positionWorldOnB, projectedPoint);
          float manifoldPoint = projectedDifference.dot(projectedDifference);
          if (manifoldPoint > getContactBreakingThreshold() * getContactBreakingThreshold()) {
            removeContactPoint(local_i);
          } else if (BulletGlobals.getContactProcessedCallback() != null) {
            BulletGlobals.getContactProcessedCallback().contactProcessed(manifoldPoint, this.body0, this.body1);
          }
        }
      }
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void clearManifold()
  {
    for (int local_i = 0; local_i < this.cachedPoints; local_i++) {
      clearUserCache(this.pointCache[local_i]);
    }
    this.cachedPoints = 0;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.narrowphase.PersistentManifold
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */