/*   1:    */package com.bulletphysics.collision.dispatch;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
/*   5:    */import com.bulletphysics.collision.broadphase.CollisionAlgorithmConstructionInfo;
/*   6:    */import com.bulletphysics.collision.broadphase.Dispatcher;
/*   7:    */import com.bulletphysics.collision.broadphase.DispatcherInfo;
/*   8:    */import com.bulletphysics.collision.narrowphase.PersistentManifold;
/*   9:    */import com.bulletphysics.collision.shapes.SphereShape;
/*  10:    */import com.bulletphysics.linearmath.Transform;
/*  11:    */import com.bulletphysics.util.ObjectArrayList;
/*  12:    */import com.bulletphysics.util.ObjectPool;
/*  13:    */import javax.vecmath.Vector3f;
/*  14:    */
/*  41:    */public class SphereSphereCollisionAlgorithm
/*  42:    */  extends CollisionAlgorithm
/*  43:    */{
/*  44:    */  private boolean ownManifold;
/*  45:    */  private PersistentManifold manifoldPtr;
/*  46:    */  
/*  47:    */  public void init(PersistentManifold mf, CollisionAlgorithmConstructionInfo ci, CollisionObject col0, CollisionObject col1)
/*  48:    */  {
/*  49: 49 */    super.init(ci);
/*  50: 50 */    this.manifoldPtr = mf;
/*  51:    */    
/*  52: 52 */    if (this.manifoldPtr == null) {
/*  53: 53 */      this.manifoldPtr = this.dispatcher.getNewManifold(col0, col1);
/*  54: 54 */      this.ownManifold = true;
/*  55:    */    }
/*  56:    */  }
/*  57:    */  
/*  58:    */  public void init(CollisionAlgorithmConstructionInfo ci)
/*  59:    */  {
/*  60: 60 */    super.init(ci);
/*  61:    */  }
/*  62:    */  
/*  63:    */  public void destroy()
/*  64:    */  {
/*  65: 65 */    if (this.ownManifold) {
/*  66: 66 */      if (this.manifoldPtr != null) {
/*  67: 67 */        this.dispatcher.releaseManifold(this.manifoldPtr);
/*  68:    */      }
/*  69: 69 */      this.manifoldPtr = null;
/*  70:    */    }
/*  71:    */  }
/*  72:    */  
/*  73:    */  public void processCollision(CollisionObject arg1, CollisionObject arg2, DispatcherInfo arg3, ManifoldResult arg4)
/*  74:    */  {
/*  75: 75 */    .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$com$bulletphysics$linearmath$Transform();tmp7_5.push$javax$vecmath$Vector3f(); if (this.manifoldPtr == null) {
/*  76: 76 */        return;
/*  77:    */      }
/*  78:    */      
/*  79: 79 */      Transform tmpTrans1 = localStack.get$com$bulletphysics$linearmath$Transform();
/*  80: 80 */      Transform tmpTrans2 = localStack.get$com$bulletphysics$linearmath$Transform();
/*  81:    */      
/*  82: 82 */      resultOut.setPersistentManifold(this.manifoldPtr);
/*  83:    */      
/*  84: 84 */      SphereShape sphere0 = (SphereShape)col0.getCollisionShape();
/*  85: 85 */      SphereShape sphere1 = (SphereShape)col1.getCollisionShape();
/*  86:    */      
/*  87: 87 */      Vector3f diff = localStack.get$javax$vecmath$Vector3f();
/*  88: 88 */      diff.sub(col0.getWorldTransform(tmpTrans1).origin, col1.getWorldTransform(tmpTrans2).origin);
/*  89:    */      
/*  90: 90 */      float len = diff.length();
/*  91: 91 */      float radius0 = sphere0.getRadius();
/*  92: 92 */      float radius1 = sphere1.getRadius();
/*  93:    */      
/*  99: 99 */      if (len > radius0 + radius1)
/* 100:    */      {
/* 101:101 */        resultOut.refreshContactPoints();
/* 102:    */        
/* 103:103 */        return;
/* 104:    */      }
/* 105:    */      
/* 106:106 */      float dist = len - (radius0 + radius1);
/* 107:    */      
/* 108:108 */      Vector3f normalOnSurfaceB = localStack.get$javax$vecmath$Vector3f();
/* 109:109 */      normalOnSurfaceB.set(1.0F, 0.0F, 0.0F);
/* 110:110 */      if (len > 1.192093E-007F) {
/* 111:111 */        normalOnSurfaceB.scale(1.0F / len, diff);
/* 112:    */      }
/* 113:    */      
/* 114:114 */      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 115:    */      
/* 117:117 */      Vector3f pos0 = localStack.get$javax$vecmath$Vector3f();
/* 118:118 */      tmp.scale(radius0, normalOnSurfaceB);
/* 119:119 */      pos0.sub(col0.getWorldTransform(tmpTrans1).origin, tmp);
/* 120:    */      
/* 122:122 */      Vector3f pos1 = localStack.get$javax$vecmath$Vector3f();
/* 123:123 */      tmp.scale(radius1, normalOnSurfaceB);
/* 124:124 */      pos1.add(col1.getWorldTransform(tmpTrans2).origin, tmp);
/* 125:    */      
/* 127:127 */      resultOut.addContactPoint(normalOnSurfaceB, pos1, dist);
/* 128:    */      
/* 130:130 */      resultOut.refreshContactPoints();
/* 131:    */    } finally {
/* 132:132 */      .Stack tmp292_290 = localStack;tmp292_290.pop$com$bulletphysics$linearmath$Transform();tmp292_290.pop$javax$vecmath$Vector3f();
/* 133:    */    }
/* 134:    */  }
/* 135:    */  
/* 136:136 */  public float calculateTimeOfImpact(CollisionObject body0, CollisionObject body1, DispatcherInfo dispatchInfo, ManifoldResult resultOut) { return 1.0F; }
/* 137:    */  
/* 139:    */  public void getAllContactManifolds(ObjectArrayList<PersistentManifold> manifoldArray)
/* 140:    */  {
/* 141:141 */    if ((this.manifoldPtr != null) && (this.ownManifold)) {
/* 142:142 */      manifoldArray.add(this.manifoldPtr);
/* 143:    */    }
/* 144:    */  }
/* 145:    */  
/* 146:    */  public static class CreateFunc
/* 147:    */    extends CollisionAlgorithmCreateFunc
/* 148:    */  {
/* 149:149 */    private final ObjectPool<SphereSphereCollisionAlgorithm> pool = ObjectPool.get(SphereSphereCollisionAlgorithm.class);
/* 150:    */    
/* 151:    */    public CollisionAlgorithm createCollisionAlgorithm(CollisionAlgorithmConstructionInfo ci, CollisionObject body0, CollisionObject body1)
/* 152:    */    {
/* 153:153 */      SphereSphereCollisionAlgorithm algo = (SphereSphereCollisionAlgorithm)this.pool.get();
/* 154:154 */      algo.init(null, ci, body0, body1);
/* 155:155 */      return algo;
/* 156:    */    }
/* 157:    */    
/* 158:    */    public void releaseCollisionAlgorithm(CollisionAlgorithm algo)
/* 159:    */    {
/* 160:160 */      this.pool.release((SphereSphereCollisionAlgorithm)algo);
/* 161:    */    }
/* 162:    */  }
/* 163:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.dispatch.SphereSphereCollisionAlgorithm
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */