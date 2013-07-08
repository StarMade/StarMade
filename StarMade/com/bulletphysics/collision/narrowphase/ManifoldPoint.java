/*   1:    */package com.bulletphysics.collision.narrowphase;
/*   2:    */
/*   3:    */import javax.vecmath.Vector3f;
/*   4:    */
/*  34:    */public class ManifoldPoint
/*  35:    */{
/*  36: 36 */  public final Vector3f localPointA = new Vector3f();
/*  37: 37 */  public final Vector3f localPointB = new Vector3f();
/*  38: 38 */  public final Vector3f positionWorldOnB = new Vector3f();
/*  39:    */  
/*  40: 40 */  public final Vector3f positionWorldOnA = new Vector3f();
/*  41: 41 */  public final Vector3f normalWorldOnB = new Vector3f();
/*  42:    */  
/*  43:    */  public float distance1;
/*  44:    */  
/*  45:    */  public float combinedFriction;
/*  46:    */  
/*  47:    */  public float combinedRestitution;
/*  48:    */  
/*  49:    */  public int partId0;
/*  50:    */  
/*  51:    */  public int partId1;
/*  52:    */  
/*  53:    */  public int index0;
/*  54:    */  public int index1;
/*  55:    */  public Object userPersistentData;
/*  56:    */  public float appliedImpulse;
/*  57:    */  public boolean lateralFrictionInitialized;
/*  58:    */  public float appliedImpulseLateral1;
/*  59:    */  public float appliedImpulseLateral2;
/*  60:    */  public int lifeTime;
/*  61: 61 */  public final Vector3f lateralFrictionDir1 = new Vector3f();
/*  62: 62 */  public final Vector3f lateralFrictionDir2 = new Vector3f();
/*  63:    */  
/*  64:    */  public ManifoldPoint() {
/*  65: 65 */    this.userPersistentData = null;
/*  66: 66 */    this.appliedImpulse = 0.0F;
/*  67: 67 */    this.lateralFrictionInitialized = false;
/*  68: 68 */    this.lifeTime = 0;
/*  69:    */  }
/*  70:    */  
/*  71:    */  public ManifoldPoint(Vector3f pointA, Vector3f pointB, Vector3f normal, float distance) {
/*  72: 72 */    init(pointA, pointB, normal, distance);
/*  73:    */  }
/*  74:    */  
/*  75:    */  public void init(Vector3f pointA, Vector3f pointB, Vector3f normal, float distance) {
/*  76: 76 */    this.localPointA.set(pointA);
/*  77: 77 */    this.localPointB.set(pointB);
/*  78: 78 */    this.normalWorldOnB.set(normal);
/*  79: 79 */    this.distance1 = distance;
/*  80: 80 */    this.combinedFriction = 0.0F;
/*  81: 81 */    this.combinedRestitution = 0.0F;
/*  82: 82 */    this.userPersistentData = null;
/*  83: 83 */    this.appliedImpulse = 0.0F;
/*  84: 84 */    this.lateralFrictionInitialized = false;
/*  85: 85 */    this.appliedImpulseLateral1 = 0.0F;
/*  86: 86 */    this.appliedImpulseLateral2 = 0.0F;
/*  87: 87 */    this.lifeTime = 0;
/*  88:    */  }
/*  89:    */  
/*  90:    */  public float getDistance() {
/*  91: 91 */    return this.distance1;
/*  92:    */  }
/*  93:    */  
/*  94:    */  public int getLifeTime() {
/*  95: 95 */    return this.lifeTime;
/*  96:    */  }
/*  97:    */  
/*  98:    */  public void set(ManifoldPoint p) {
/*  99: 99 */    this.localPointA.set(p.localPointA);
/* 100:100 */    this.localPointB.set(p.localPointB);
/* 101:101 */    this.positionWorldOnA.set(p.positionWorldOnA);
/* 102:102 */    this.positionWorldOnB.set(p.positionWorldOnB);
/* 103:103 */    this.normalWorldOnB.set(p.normalWorldOnB);
/* 104:104 */    this.distance1 = p.distance1;
/* 105:105 */    this.combinedFriction = p.combinedFriction;
/* 106:106 */    this.combinedRestitution = p.combinedRestitution;
/* 107:107 */    this.partId0 = p.partId0;
/* 108:108 */    this.partId1 = p.partId1;
/* 109:109 */    this.index0 = p.index0;
/* 110:110 */    this.index1 = p.index1;
/* 111:111 */    this.userPersistentData = p.userPersistentData;
/* 112:112 */    this.appliedImpulse = p.appliedImpulse;
/* 113:113 */    this.lateralFrictionInitialized = p.lateralFrictionInitialized;
/* 114:114 */    this.appliedImpulseLateral1 = p.appliedImpulseLateral1;
/* 115:115 */    this.appliedImpulseLateral2 = p.appliedImpulseLateral2;
/* 116:116 */    this.lifeTime = p.lifeTime;
/* 117:117 */    this.lateralFrictionDir1.set(p.lateralFrictionDir1);
/* 118:118 */    this.lateralFrictionDir2.set(p.lateralFrictionDir2);
/* 119:    */  }
/* 120:    */  
/* 121:    */  public Vector3f getPositionWorldOnA(Vector3f out) {
/* 122:122 */    out.set(this.positionWorldOnA);
/* 123:123 */    return out;
/* 124:    */  }
/* 125:    */  
/* 126:    */  public Vector3f getPositionWorldOnB(Vector3f out)
/* 127:    */  {
/* 128:128 */    out.set(this.positionWorldOnB);
/* 129:129 */    return out;
/* 130:    */  }
/* 131:    */  
/* 132:    */  public void setDistance(float dist) {
/* 133:133 */    this.distance1 = dist;
/* 134:    */  }
/* 135:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.narrowphase.ManifoldPoint
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */