/*   1:    */package com.bulletphysics.collision.dispatch;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
/*   5:    */import com.bulletphysics.collision.broadphase.CollisionAlgorithmConstructionInfo;
/*   6:    */import com.bulletphysics.collision.broadphase.Dispatcher;
/*   7:    */import com.bulletphysics.collision.broadphase.DispatcherInfo;
/*   8:    */import com.bulletphysics.collision.narrowphase.PersistentManifold;
/*   9:    */import com.bulletphysics.collision.shapes.CollisionShape;
/*  10:    */import com.bulletphysics.collision.shapes.CompoundShape;
/*  11:    */import com.bulletphysics.linearmath.Transform;
/*  12:    */import com.bulletphysics.util.ObjectArrayList;
/*  13:    */import com.bulletphysics.util.ObjectPool;
/*  14:    */
/*  42:    */public class CompoundCollisionAlgorithm
/*  43:    */  extends CollisionAlgorithm
/*  44:    */{
/*  45: 45 */  private final ObjectArrayList<CollisionAlgorithm> childCollisionAlgorithms = new ObjectArrayList();
/*  46:    */  private boolean isSwapped;
/*  47:    */  
/*  48:    */  public void init(CollisionAlgorithmConstructionInfo ci, CollisionObject body0, CollisionObject body1, boolean isSwapped) {
/*  49: 49 */    super.init(ci);
/*  50:    */    
/*  51: 51 */    this.isSwapped = isSwapped;
/*  52:    */    
/*  53: 53 */    CollisionObject colObj = isSwapped ? body1 : body0;
/*  54: 54 */    CollisionObject otherObj = isSwapped ? body0 : body1;
/*  55: 55 */    assert (colObj.getCollisionShape().isCompound());
/*  56:    */    
/*  57: 57 */    CompoundShape compoundShape = (CompoundShape)colObj.getCollisionShape();
/*  58: 58 */    int numChildren = compoundShape.getNumChildShapes();
/*  59:    */    
/*  62: 62 */    for (int i = 0; i < numChildren; i++) {
/*  63: 63 */      CollisionShape tmpShape = colObj.getCollisionShape();
/*  64: 64 */      CollisionShape childShape = compoundShape.getChildShape(i);
/*  65: 65 */      colObj.internalSetTemporaryCollisionShape(childShape);
/*  66: 66 */      this.childCollisionAlgorithms.add(ci.dispatcher1.findAlgorithm(colObj, otherObj));
/*  67: 67 */      colObj.internalSetTemporaryCollisionShape(tmpShape);
/*  68:    */    }
/*  69:    */  }
/*  70:    */  
/*  71:    */  public void destroy()
/*  72:    */  {
/*  73: 73 */    int numChildren = this.childCollisionAlgorithms.size();
/*  74: 74 */    for (int i = 0; i < numChildren; i++)
/*  75:    */    {
/*  76: 76 */      this.dispatcher.freeCollisionAlgorithm((CollisionAlgorithm)this.childCollisionAlgorithms.getQuick(i));
/*  77:    */    }
/*  78: 78 */    this.childCollisionAlgorithms.clear();
/*  79:    */  }
/*  80:    */  
/*  81:    */  public void processCollision(CollisionObject arg1, CollisionObject arg2, DispatcherInfo arg3, ManifoldResult arg4)
/*  82:    */  {
/*  83: 83 */    .Stack localStack = .Stack.get(); try { localStack.push$com$bulletphysics$linearmath$Transform();CollisionObject colObj = this.isSwapped ? body1 : body0;
/*  84: 84 */      CollisionObject otherObj = this.isSwapped ? body0 : body1;
/*  85:    */      
/*  86: 86 */      assert (colObj.getCollisionShape().isCompound());
/*  87: 87 */      CompoundShape compoundShape = (CompoundShape)colObj.getCollisionShape();
/*  88:    */      
/*  96: 96 */      Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/*  97: 97 */      Transform orgTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/*  98: 98 */      Transform childTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/*  99: 99 */      Transform orgInterpolationTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/* 100:100 */      Transform newChildWorldTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/* 101:    */      
/* 102:102 */      int numChildren = this.childCollisionAlgorithms.size();
/* 103:    */      
/* 104:104 */      for (int i = 0; i < numChildren; i++)
/* 105:    */      {
/* 106:106 */        CollisionShape childShape = compoundShape.getChildShape(i);
/* 107:    */        
/* 109:109 */        colObj.getWorldTransform(orgTrans);
/* 110:110 */        colObj.getInterpolationWorldTransform(orgInterpolationTrans);
/* 111:    */        
/* 112:112 */        compoundShape.getChildTransform(i, childTrans);
/* 113:113 */        newChildWorldTrans.mul(orgTrans, childTrans);
/* 114:114 */        colObj.setWorldTransform(newChildWorldTrans);
/* 115:115 */        colObj.setInterpolationWorldTransform(newChildWorldTrans);
/* 116:    */        
/* 118:118 */        CollisionShape tmpShape = colObj.getCollisionShape();
/* 119:119 */        colObj.internalSetTemporaryCollisionShape(childShape);
/* 120:120 */        ((CollisionAlgorithm)this.childCollisionAlgorithms.getQuick(i)).processCollision(colObj, otherObj, dispatchInfo, resultOut);
/* 121:    */        
/* 122:122 */        colObj.internalSetTemporaryCollisionShape(tmpShape);
/* 123:123 */        colObj.setWorldTransform(orgTrans);
/* 124:124 */        colObj.setInterpolationWorldTransform(orgInterpolationTrans);
/* 125:    */      }
/* 126:126 */    } finally { localStack.pop$com$bulletphysics$linearmath$Transform();
/* 127:    */    }
/* 128:    */  }
/* 129:    */  
/* 130:130 */  public float calculateTimeOfImpact(CollisionObject arg1, CollisionObject arg2, DispatcherInfo arg3, ManifoldResult arg4) { .Stack localStack = .Stack.get(); try { localStack.push$com$bulletphysics$linearmath$Transform();CollisionObject colObj = this.isSwapped ? body1 : body0;
/* 131:131 */      CollisionObject otherObj = this.isSwapped ? body0 : body1;
/* 132:    */      
/* 133:133 */      assert (colObj.getCollisionShape().isCompound());
/* 134:    */      
/* 135:135 */      CompoundShape compoundShape = (CompoundShape)colObj.getCollisionShape();
/* 136:    */      
/* 144:144 */      Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/* 145:145 */      Transform orgTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/* 146:146 */      Transform childTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/* 147:147 */      float hitFraction = 1.0F;
/* 148:    */      
/* 149:149 */      int numChildren = this.childCollisionAlgorithms.size();
/* 150:    */      
/* 151:151 */      for (int i = 0; i < numChildren; i++)
/* 152:    */      {
/* 153:153 */        CollisionShape childShape = compoundShape.getChildShape(i);
/* 154:    */        
/* 156:156 */        colObj.getWorldTransform(orgTrans);
/* 157:    */        
/* 158:158 */        compoundShape.getChildTransform(i, childTrans);
/* 159:    */        
/* 160:160 */        tmpTrans.set(orgTrans);
/* 161:161 */        tmpTrans.mul(childTrans);
/* 162:162 */        colObj.setWorldTransform(tmpTrans);
/* 163:    */        
/* 164:164 */        CollisionShape tmpShape = colObj.getCollisionShape();
/* 165:165 */        colObj.internalSetTemporaryCollisionShape(childShape);
/* 166:166 */        float frac = ((CollisionAlgorithm)this.childCollisionAlgorithms.getQuick(i)).calculateTimeOfImpact(colObj, otherObj, dispatchInfo, resultOut);
/* 167:167 */        if (frac < hitFraction) {
/* 168:168 */          hitFraction = frac;
/* 169:    */        }
/* 170:    */        
/* 171:171 */        colObj.internalSetTemporaryCollisionShape(tmpShape);
/* 172:172 */        colObj.setWorldTransform(orgTrans);
/* 173:    */      }
/* 174:174 */      return hitFraction; } finally { localStack.pop$com$bulletphysics$linearmath$Transform();
/* 175:    */    }
/* 176:    */  }
/* 177:    */  
/* 178:    */  public void getAllContactManifolds(ObjectArrayList<PersistentManifold> manifoldArray) {
/* 179:179 */    for (int i = 0; i < this.childCollisionAlgorithms.size(); i++) {
/* 180:180 */      ((CollisionAlgorithm)this.childCollisionAlgorithms.getQuick(i)).getAllContactManifolds(manifoldArray);
/* 181:    */    }
/* 182:    */  }
/* 183:    */  
/* 184:    */  public static class CreateFunc
/* 185:    */    extends CollisionAlgorithmCreateFunc
/* 186:    */  {
/* 187:187 */    private final ObjectPool<CompoundCollisionAlgorithm> pool = ObjectPool.get(CompoundCollisionAlgorithm.class);
/* 188:    */    
/* 189:    */    public CollisionAlgorithm createCollisionAlgorithm(CollisionAlgorithmConstructionInfo ci, CollisionObject body0, CollisionObject body1)
/* 190:    */    {
/* 191:191 */      CompoundCollisionAlgorithm algo = (CompoundCollisionAlgorithm)this.pool.get();
/* 192:192 */      algo.init(ci, body0, body1, false);
/* 193:193 */      return algo;
/* 194:    */    }
/* 195:    */    
/* 196:    */    public void releaseCollisionAlgorithm(CollisionAlgorithm algo)
/* 197:    */    {
/* 198:198 */      this.pool.release((CompoundCollisionAlgorithm)algo);
/* 199:    */    }
/* 200:    */  }
/* 201:    */  
/* 202:    */  public static class SwappedCreateFunc extends CollisionAlgorithmCreateFunc {
/* 203:203 */    private final ObjectPool<CompoundCollisionAlgorithm> pool = ObjectPool.get(CompoundCollisionAlgorithm.class);
/* 204:    */    
/* 205:    */    public CollisionAlgorithm createCollisionAlgorithm(CollisionAlgorithmConstructionInfo ci, CollisionObject body0, CollisionObject body1)
/* 206:    */    {
/* 207:207 */      CompoundCollisionAlgorithm algo = (CompoundCollisionAlgorithm)this.pool.get();
/* 208:208 */      algo.init(ci, body0, body1, true);
/* 209:209 */      return algo;
/* 210:    */    }
/* 211:    */    
/* 212:    */    public void releaseCollisionAlgorithm(CollisionAlgorithm algo)
/* 213:    */    {
/* 214:214 */      this.pool.release((CompoundCollisionAlgorithm)algo);
/* 215:    */    }
/* 216:    */  }
/* 217:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.dispatch.CompoundCollisionAlgorithm
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */