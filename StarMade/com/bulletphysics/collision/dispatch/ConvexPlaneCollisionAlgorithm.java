/*   1:    */package com.bulletphysics.collision.dispatch;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
/*   5:    */import com.bulletphysics.collision.broadphase.CollisionAlgorithmConstructionInfo;
/*   6:    */import com.bulletphysics.collision.broadphase.Dispatcher;
/*   7:    */import com.bulletphysics.collision.broadphase.DispatcherInfo;
/*   8:    */import com.bulletphysics.collision.narrowphase.PersistentManifold;
/*   9:    */import com.bulletphysics.collision.shapes.ConvexShape;
/*  10:    */import com.bulletphysics.collision.shapes.StaticPlaneShape;
/*  11:    */import com.bulletphysics.linearmath.Transform;
/*  12:    */import com.bulletphysics.util.ObjectArrayList;
/*  13:    */import com.bulletphysics.util.ObjectPool;
/*  14:    */import javax.vecmath.Matrix3f;
/*  15:    */import javax.vecmath.Vector3f;
/*  16:    */
/*  41:    */public class ConvexPlaneCollisionAlgorithm
/*  42:    */  extends CollisionAlgorithm
/*  43:    */{
/*  44:    */  private boolean ownManifold;
/*  45:    */  private PersistentManifold manifoldPtr;
/*  46:    */  private boolean isSwapped;
/*  47:    */  
/*  48:    */  public void init(PersistentManifold mf, CollisionAlgorithmConstructionInfo ci, CollisionObject col0, CollisionObject col1, boolean isSwapped)
/*  49:    */  {
/*  50: 50 */    super.init(ci);
/*  51: 51 */    this.ownManifold = false;
/*  52: 52 */    this.manifoldPtr = mf;
/*  53: 53 */    this.isSwapped = isSwapped;
/*  54:    */    
/*  55: 55 */    CollisionObject convexObj = isSwapped ? col1 : col0;
/*  56: 56 */    CollisionObject planeObj = isSwapped ? col0 : col1;
/*  57:    */    
/*  58: 58 */    if ((this.manifoldPtr == null) && (this.dispatcher.needsCollision(convexObj, planeObj))) {
/*  59: 59 */      this.manifoldPtr = this.dispatcher.getNewManifold(convexObj, planeObj);
/*  60: 60 */      this.ownManifold = true;
/*  61:    */    }
/*  62:    */  }
/*  63:    */  
/*  64:    */  public void destroy()
/*  65:    */  {
/*  66: 66 */    if (this.ownManifold) {
/*  67: 67 */      if (this.manifoldPtr != null) {
/*  68: 68 */        this.dispatcher.releaseManifold(this.manifoldPtr);
/*  69:    */      }
/*  70: 70 */      this.manifoldPtr = null;
/*  71:    */    }
/*  72:    */  }
/*  73:    */  
/*  74:    */  public void processCollision(CollisionObject arg1, CollisionObject arg2, DispatcherInfo arg3, ManifoldResult arg4)
/*  75:    */  {
/*  76: 76 */    .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$com$bulletphysics$linearmath$Transform();tmp7_5.push$javax$vecmath$Vector3f(); if (this.manifoldPtr == null) {
/*  77: 77 */        return;
/*  78:    */      }
/*  79:    */      
/*  80: 80 */      Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/*  81:    */      
/*  82: 82 */      CollisionObject convexObj = this.isSwapped ? body1 : body0;
/*  83: 83 */      CollisionObject planeObj = this.isSwapped ? body0 : body1;
/*  84:    */      
/*  85: 85 */      ConvexShape convexShape = (ConvexShape)convexObj.getCollisionShape();
/*  86: 86 */      StaticPlaneShape planeShape = (StaticPlaneShape)planeObj.getCollisionShape();
/*  87:    */      
/*  88: 88 */      boolean hasCollision = false;
/*  89: 89 */      Vector3f planeNormal = planeShape.getPlaneNormal(localStack.get$javax$vecmath$Vector3f());
/*  90: 90 */      float planeConstant = planeShape.getPlaneConstant();
/*  91:    */      
/*  92: 92 */      Transform planeInConvex = localStack.get$com$bulletphysics$linearmath$Transform();
/*  93: 93 */      convexObj.getWorldTransform(planeInConvex);
/*  94: 94 */      planeInConvex.inverse();
/*  95: 95 */      planeInConvex.mul(planeObj.getWorldTransform(tmpTrans));
/*  96:    */      
/*  97: 97 */      Transform convexInPlaneTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/*  98: 98 */      convexInPlaneTrans.inverse(planeObj.getWorldTransform(tmpTrans));
/*  99: 99 */      convexInPlaneTrans.mul(convexObj.getWorldTransform(tmpTrans));
/* 100:    */      
/* 101:101 */      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 102:102 */      tmp.negate(planeNormal);
/* 103:103 */      planeInConvex.basis.transform(tmp);
/* 104:    */      
/* 105:105 */      Vector3f vtx = convexShape.localGetSupportingVertex(tmp, localStack.get$javax$vecmath$Vector3f());
/* 106:106 */      Vector3f vtxInPlane = localStack.get$javax$vecmath$Vector3f(vtx);
/* 107:107 */      convexInPlaneTrans.transform(vtxInPlane);
/* 108:    */      
/* 109:109 */      float distance = planeNormal.dot(vtxInPlane) - planeConstant;
/* 110:    */      
/* 111:111 */      Vector3f vtxInPlaneProjected = localStack.get$javax$vecmath$Vector3f();
/* 112:112 */      tmp.scale(distance, planeNormal);
/* 113:113 */      vtxInPlaneProjected.sub(vtxInPlane, tmp);
/* 114:    */      
/* 115:115 */      Vector3f vtxInPlaneWorld = localStack.get$javax$vecmath$Vector3f(vtxInPlaneProjected);
/* 116:116 */      planeObj.getWorldTransform(tmpTrans).transform(vtxInPlaneWorld);
/* 117:    */      
/* 118:118 */      hasCollision = distance < this.manifoldPtr.getContactBreakingThreshold();
/* 119:119 */      resultOut.setPersistentManifold(this.manifoldPtr);
/* 120:120 */      if (hasCollision)
/* 121:    */      {
/* 122:122 */        Vector3f normalOnSurfaceB = localStack.get$javax$vecmath$Vector3f(planeNormal);
/* 123:123 */        planeObj.getWorldTransform(tmpTrans).basis.transform(normalOnSurfaceB);
/* 124:    */        
/* 125:125 */        Vector3f pOnB = localStack.get$javax$vecmath$Vector3f(vtxInPlaneWorld);
/* 126:126 */        resultOut.addContactPoint(normalOnSurfaceB, pOnB, distance);
/* 127:    */      }
/* 128:128 */      if ((this.ownManifold) && 
/* 129:129 */        (this.manifoldPtr.getNumContacts() != 0)) {
/* 130:130 */        resultOut.refreshContactPoints();
/* 131:    */      }
/* 132:    */    } finally {
/* 133:133 */      .Stack tmp399_397 = localStack;tmp399_397.pop$com$bulletphysics$linearmath$Transform();tmp399_397.pop$javax$vecmath$Vector3f();
/* 134:    */    }
/* 135:    */  }
/* 136:    */  
/* 137:    */  public float calculateTimeOfImpact(CollisionObject body0, CollisionObject body1, DispatcherInfo dispatchInfo, ManifoldResult resultOut) {
/* 138:138 */    return 1.0F;
/* 139:    */  }
/* 140:    */  
/* 141:    */  public void getAllContactManifolds(ObjectArrayList<PersistentManifold> manifoldArray)
/* 142:    */  {
/* 143:143 */    if ((this.manifoldPtr != null) && (this.ownManifold)) {
/* 144:144 */      manifoldArray.add(this.manifoldPtr);
/* 145:    */    }
/* 146:    */  }
/* 147:    */  
/* 148:    */  public static class CreateFunc
/* 149:    */    extends CollisionAlgorithmCreateFunc
/* 150:    */  {
/* 151:151 */    private final ObjectPool<ConvexPlaneCollisionAlgorithm> pool = ObjectPool.get(ConvexPlaneCollisionAlgorithm.class);
/* 152:    */    
/* 153:    */    public CollisionAlgorithm createCollisionAlgorithm(CollisionAlgorithmConstructionInfo ci, CollisionObject body0, CollisionObject body1)
/* 154:    */    {
/* 155:155 */      ConvexPlaneCollisionAlgorithm algo = (ConvexPlaneCollisionAlgorithm)this.pool.get();
/* 156:156 */      if (!this.swapped) {
/* 157:157 */        algo.init(null, ci, body0, body1, false);
/* 158:    */      }
/* 159:    */      else {
/* 160:160 */        algo.init(null, ci, body0, body1, true);
/* 161:    */      }
/* 162:162 */      return algo;
/* 163:    */    }
/* 164:    */    
/* 165:    */    public void releaseCollisionAlgorithm(CollisionAlgorithm algo)
/* 166:    */    {
/* 167:167 */      this.pool.release((ConvexPlaneCollisionAlgorithm)algo);
/* 168:    */    }
/* 169:    */  }
/* 170:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.dispatch.ConvexPlaneCollisionAlgorithm
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */