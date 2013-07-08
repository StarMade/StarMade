/*   1:    */package com.bulletphysics.collision.dispatch;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.collision.broadphase.BroadphaseProxy;
/*   5:    */import com.bulletphysics.collision.broadphase.Dispatcher;
/*   6:    */import com.bulletphysics.collision.shapes.CollisionShape;
/*   7:    */import com.bulletphysics.collision.shapes.ConvexShape;
/*   8:    */import com.bulletphysics.linearmath.AabbUtil2;
/*   9:    */import com.bulletphysics.linearmath.Transform;
/*  10:    */import com.bulletphysics.linearmath.TransformUtil;
/*  11:    */import com.bulletphysics.util.ObjectArrayList;
/*  12:    */import javax.vecmath.Vector3f;
/*  13:    */
/*  43:    */public class GhostObject
/*  44:    */  extends CollisionObject
/*  45:    */{
/*  46: 46 */  protected ObjectArrayList<CollisionObject> overlappingObjects = new ObjectArrayList();
/*  47:    */  
/*  48:    */  public GhostObject() {
/*  49: 49 */    this.internalType = CollisionObjectType.GHOST_OBJECT;
/*  50:    */  }
/*  51:    */  
/*  54:    */  public void addOverlappingObjectInternal(BroadphaseProxy otherProxy, BroadphaseProxy thisProxy)
/*  55:    */  {
/*  56: 56 */    CollisionObject otherObject = (CollisionObject)otherProxy.clientObject;
/*  57: 57 */    assert (otherObject != null);
/*  58:    */    
/*  60: 60 */    int index = this.overlappingObjects.indexOf(otherObject);
/*  61: 61 */    if (index == -1)
/*  62:    */    {
/*  63: 63 */      this.overlappingObjects.add(otherObject);
/*  64:    */    }
/*  65:    */  }
/*  66:    */  
/*  69:    */  public void removeOverlappingObjectInternal(BroadphaseProxy otherProxy, Dispatcher dispatcher, BroadphaseProxy thisProxy)
/*  70:    */  {
/*  71: 71 */    CollisionObject otherObject = (CollisionObject)otherProxy.clientObject;
/*  72: 72 */    assert (otherObject != null);
/*  73:    */    
/*  74: 74 */    int index = this.overlappingObjects.indexOf(otherObject);
/*  75: 75 */    if (index != -1) {
/*  76: 76 */      this.overlappingObjects.set(index, this.overlappingObjects.getQuick(this.overlappingObjects.size() - 1));
/*  77: 77 */      this.overlappingObjects.removeQuick(this.overlappingObjects.size() - 1);
/*  78:    */    }
/*  79:    */  }
/*  80:    */  
/*  81:    */  public void convexSweepTest(ConvexShape arg1, Transform arg2, Transform arg3, CollisionWorld.ConvexResultCallback arg4, float arg5) {
/*  82: 82 */    .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$com$bulletphysics$linearmath$Transform(); .Stack tmp11_7 = tmp7_5;tmp11_7.push$javax$vecmath$Vector3f();tmp11_7.push$javax$vecmath$Quat4f();Transform convexFromTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/*  83: 83 */      Transform convexToTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/*  84:    */      
/*  85: 85 */      convexFromTrans.set(convexFromWorld);
/*  86: 86 */      convexToTrans.set(convexToWorld);
/*  87:    */      
/*  88: 88 */      Vector3f castShapeAabbMin = localStack.get$javax$vecmath$Vector3f();
/*  89: 89 */      Vector3f castShapeAabbMax = localStack.get$javax$vecmath$Vector3f();
/*  90:    */      
/*  93: 93 */      Vector3f linVel = localStack.get$javax$vecmath$Vector3f();
/*  94: 94 */      Vector3f angVel = localStack.get$javax$vecmath$Vector3f();
/*  95: 95 */      TransformUtil.calculateVelocity(convexFromTrans, convexToTrans, 1.0F, linVel, angVel);
/*  96: 96 */      Transform R = localStack.get$com$bulletphysics$linearmath$Transform();
/*  97: 97 */      R.setIdentity();
/*  98: 98 */      R.setRotation(convexFromTrans.getRotation(localStack.get$javax$vecmath$Quat4f()));
/*  99: 99 */      castShape.calculateTemporalAabb(R, linVel, angVel, 1.0F, castShapeAabbMin, castShapeAabbMax);
/* 100:    */      
/* 102:102 */      Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/* 103:    */      
/* 106:106 */      for (int i = 0; i < this.overlappingObjects.size(); i++) {
/* 107:107 */        CollisionObject collisionObject = (CollisionObject)this.overlappingObjects.getQuick(i);
/* 108:    */        
/* 110:110 */        if (resultCallback.needsCollision(collisionObject.getBroadphaseHandle()))
/* 111:    */        {
/* 112:112 */          Vector3f collisionObjectAabbMin = localStack.get$javax$vecmath$Vector3f();
/* 113:113 */          Vector3f collisionObjectAabbMax = localStack.get$javax$vecmath$Vector3f();
/* 114:114 */          collisionObject.getCollisionShape().getAabb(collisionObject.getWorldTransform(tmpTrans), collisionObjectAabbMin, collisionObjectAabbMax);
/* 115:115 */          AabbUtil2.aabbExpand(collisionObjectAabbMin, collisionObjectAabbMax, castShapeAabbMin, castShapeAabbMax);
/* 116:116 */          float[] hitLambda = { 1.0F };
/* 117:117 */          Vector3f hitNormal = localStack.get$javax$vecmath$Vector3f();
/* 118:118 */          if (AabbUtil2.rayAabb(convexFromWorld.origin, convexToWorld.origin, collisionObjectAabbMin, collisionObjectAabbMax, hitLambda, hitNormal)) {
/* 119:119 */            CollisionWorld.objectQuerySingle(castShape, convexFromTrans, convexToTrans, collisionObject, collisionObject.getCollisionShape(), collisionObject.getWorldTransform(tmpTrans), resultCallback, allowedCcdPenetration);
/* 120:    */          }
/* 121:    */          
/* 122:    */        }
/* 123:    */        
/* 124:    */      }
/* 125:    */    }
/* 126:    */    finally
/* 127:    */    {
/* 128:128 */      .Stack tmp305_303 = localStack;tmp305_303.pop$com$bulletphysics$linearmath$Transform(); .Stack tmp309_305 = tmp305_303;tmp309_305.pop$javax$vecmath$Vector3f();tmp309_305.pop$javax$vecmath$Quat4f();
/* 129:    */    } }
/* 130:    */  
/* 131:131 */  public void rayTest(Vector3f arg1, Vector3f arg2, CollisionWorld.RayResultCallback arg3) { .Stack localStack = .Stack.get(); try { localStack.push$com$bulletphysics$linearmath$Transform();Transform rayFromTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/* 132:132 */      rayFromTrans.setIdentity();
/* 133:133 */      rayFromTrans.origin.set(rayFromWorld);
/* 134:134 */      Transform rayToTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/* 135:135 */      rayToTrans.setIdentity();
/* 136:136 */      rayToTrans.origin.set(rayToWorld);
/* 137:    */      
/* 138:138 */      Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/* 139:    */      
/* 140:140 */      for (int i = 0; i < this.overlappingObjects.size(); i++) {
/* 141:141 */        CollisionObject collisionObject = (CollisionObject)this.overlappingObjects.getQuick(i);
/* 142:    */        
/* 144:144 */        if (resultCallback.needsCollision(collisionObject.getBroadphaseHandle())) {
/* 145:145 */          CollisionWorld.rayTestSingle(rayFromTrans, rayToTrans, collisionObject, collisionObject.getCollisionShape(), collisionObject.getWorldTransform(tmpTrans), resultCallback);
/* 146:    */        }
/* 147:    */        
/* 148:    */      }
/* 149:    */    }
/* 150:    */    finally
/* 151:    */    {
/* 152:152 */      localStack.pop$com$bulletphysics$linearmath$Transform();
/* 153:    */    } }
/* 154:    */  
/* 155:155 */  public int getNumOverlappingObjects() { return this.overlappingObjects.size(); }
/* 156:    */  
/* 157:    */  public CollisionObject getOverlappingObject(int index)
/* 158:    */  {
/* 159:159 */    return (CollisionObject)this.overlappingObjects.getQuick(index);
/* 160:    */  }
/* 161:    */  
/* 162:    */  public ObjectArrayList<CollisionObject> getOverlappingPairs() {
/* 163:163 */    return this.overlappingObjects;
/* 164:    */  }
/* 165:    */  
/* 169:    */  public static GhostObject upcast(CollisionObject colObj)
/* 170:    */  {
/* 171:171 */    if (colObj.getInternalType() == CollisionObjectType.GHOST_OBJECT) {
/* 172:172 */      return (GhostObject)colObj;
/* 173:    */    }
/* 174:    */    
/* 175:175 */    return null;
/* 176:    */  }
/* 177:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.dispatch.GhostObject
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */