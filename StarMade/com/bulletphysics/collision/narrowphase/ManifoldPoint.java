package com.bulletphysics.collision.narrowphase;

import javax.vecmath.Vector3f;

public class ManifoldPoint
{
  public final Vector3f localPointA = new Vector3f();
  public final Vector3f localPointB = new Vector3f();
  public final Vector3f positionWorldOnB = new Vector3f();
  public final Vector3f positionWorldOnA = new Vector3f();
  public final Vector3f normalWorldOnB = new Vector3f();
  public float distance1;
  public float combinedFriction;
  public float combinedRestitution;
  public int partId0;
  public int partId1;
  public int index0;
  public int index1;
  public Object userPersistentData;
  public float appliedImpulse;
  public boolean lateralFrictionInitialized;
  public float appliedImpulseLateral1;
  public float appliedImpulseLateral2;
  public int lifeTime;
  public final Vector3f lateralFrictionDir1 = new Vector3f();
  public final Vector3f lateralFrictionDir2 = new Vector3f();
  
  public ManifoldPoint()
  {
    this.userPersistentData = null;
    this.appliedImpulse = 0.0F;
    this.lateralFrictionInitialized = false;
    this.lifeTime = 0;
  }
  
  public ManifoldPoint(Vector3f pointA, Vector3f pointB, Vector3f normal, float distance)
  {
    init(pointA, pointB, normal, distance);
  }
  
  public void init(Vector3f pointA, Vector3f pointB, Vector3f normal, float distance)
  {
    this.localPointA.set(pointA);
    this.localPointB.set(pointB);
    this.normalWorldOnB.set(normal);
    this.distance1 = distance;
    this.combinedFriction = 0.0F;
    this.combinedRestitution = 0.0F;
    this.userPersistentData = null;
    this.appliedImpulse = 0.0F;
    this.lateralFrictionInitialized = false;
    this.appliedImpulseLateral1 = 0.0F;
    this.appliedImpulseLateral2 = 0.0F;
    this.lifeTime = 0;
  }
  
  public float getDistance()
  {
    return this.distance1;
  }
  
  public int getLifeTime()
  {
    return this.lifeTime;
  }
  
  public void set(ManifoldPoint local_p)
  {
    this.localPointA.set(local_p.localPointA);
    this.localPointB.set(local_p.localPointB);
    this.positionWorldOnA.set(local_p.positionWorldOnA);
    this.positionWorldOnB.set(local_p.positionWorldOnB);
    this.normalWorldOnB.set(local_p.normalWorldOnB);
    this.distance1 = local_p.distance1;
    this.combinedFriction = local_p.combinedFriction;
    this.combinedRestitution = local_p.combinedRestitution;
    this.partId0 = local_p.partId0;
    this.partId1 = local_p.partId1;
    this.index0 = local_p.index0;
    this.index1 = local_p.index1;
    this.userPersistentData = local_p.userPersistentData;
    this.appliedImpulse = local_p.appliedImpulse;
    this.lateralFrictionInitialized = local_p.lateralFrictionInitialized;
    this.appliedImpulseLateral1 = local_p.appliedImpulseLateral1;
    this.appliedImpulseLateral2 = local_p.appliedImpulseLateral2;
    this.lifeTime = local_p.lifeTime;
    this.lateralFrictionDir1.set(local_p.lateralFrictionDir1);
    this.lateralFrictionDir2.set(local_p.lateralFrictionDir2);
  }
  
  public Vector3f getPositionWorldOnA(Vector3f out)
  {
    out.set(this.positionWorldOnA);
    return out;
  }
  
  public Vector3f getPositionWorldOnB(Vector3f out)
  {
    out.set(this.positionWorldOnB);
    return out;
  }
  
  public void setDistance(float dist)
  {
    this.distance1 = dist;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.narrowphase.ManifoldPoint
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */