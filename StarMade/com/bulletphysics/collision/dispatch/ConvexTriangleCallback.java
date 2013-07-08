/*   1:    */package com.bulletphysics.collision.dispatch;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
/*   5:    */import com.bulletphysics.collision.broadphase.CollisionAlgorithmConstructionInfo;
/*   6:    */import com.bulletphysics.collision.broadphase.Dispatcher;
/*   7:    */import com.bulletphysics.collision.broadphase.DispatcherInfo;
/*   8:    */import com.bulletphysics.collision.narrowphase.PersistentManifold;
/*   9:    */import com.bulletphysics.collision.shapes.CollisionShape;
/*  10:    */import com.bulletphysics.collision.shapes.TriangleCallback;
/*  11:    */import com.bulletphysics.collision.shapes.TriangleShape;
/*  12:    */import com.bulletphysics.linearmath.IDebugDraw;
/*  13:    */import com.bulletphysics.linearmath.Transform;
/*  14:    */import javax.vecmath.Vector3f;
/*  15:    */
/*  46:    */class ConvexTriangleCallback
/*  47:    */  extends TriangleCallback
/*  48:    */{
/*  49:    */  private CollisionObject convexBody;
/*  50:    */  private CollisionObject triBody;
/*  51: 51 */  private final Vector3f aabbMin = new Vector3f();
/*  52: 52 */  private final Vector3f aabbMax = new Vector3f();
/*  53:    */  
/*  54:    */  private ManifoldResult resultOut;
/*  55:    */  
/*  56:    */  private Dispatcher dispatcher;
/*  57:    */  private DispatcherInfo dispatchInfoPtr;
/*  58:    */  private float collisionMarginTriangle;
/*  59:    */  public int triangleCount;
/*  60:    */  public PersistentManifold manifoldPtr;
/*  61:    */  
/*  62:    */  public ConvexTriangleCallback(Dispatcher dispatcher, CollisionObject body0, CollisionObject body1, boolean isSwapped)
/*  63:    */  {
/*  64: 64 */    this.dispatcher = dispatcher;
/*  65: 65 */    this.dispatchInfoPtr = null;
/*  66:    */    
/*  67: 67 */    this.convexBody = (isSwapped ? body1 : body0);
/*  68: 68 */    this.triBody = (isSwapped ? body0 : body1);
/*  69:    */    
/*  73: 73 */    this.manifoldPtr = dispatcher.getNewManifold(this.convexBody, this.triBody);
/*  74:    */    
/*  75: 75 */    clearCache();
/*  76:    */  }
/*  77:    */  
/*  78:    */  public void destroy() {
/*  79: 79 */    clearCache();
/*  80: 80 */    this.dispatcher.releaseManifold(this.manifoldPtr);
/*  81:    */  }
/*  82:    */  
/*  83:    */  public void setTimeStepAndCounters(float arg1, DispatcherInfo arg2, ManifoldResult arg3) {
/*  84: 84 */    .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$com$bulletphysics$linearmath$Transform();tmp7_5.push$javax$vecmath$Vector3f();this.dispatchInfoPtr = dispatchInfo;
/*  85: 85 */      this.collisionMarginTriangle = collisionMarginTriangle;
/*  86: 86 */      this.resultOut = resultOut;
/*  87:    */      
/*  89: 89 */      Transform convexInTriangleSpace = localStack.get$com$bulletphysics$linearmath$Transform();
/*  90:    */      
/*  91: 91 */      this.triBody.getWorldTransform(convexInTriangleSpace);
/*  92: 92 */      convexInTriangleSpace.inverse();
/*  93: 93 */      convexInTriangleSpace.mul(this.convexBody.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform()));
/*  94:    */      
/*  95: 95 */      CollisionShape convexShape = this.convexBody.getCollisionShape();
/*  96:    */      
/*  97: 97 */      convexShape.getAabb(convexInTriangleSpace, this.aabbMin, this.aabbMax);
/*  98: 98 */      float extraMargin = collisionMarginTriangle;
/*  99: 99 */      Vector3f extra = localStack.get$javax$vecmath$Vector3f();
/* 100:100 */      extra.set(extraMargin, extraMargin, extraMargin);
/* 101:    */      
/* 102:102 */      this.aabbMax.add(extra);
/* 103:103 */      this.aabbMin.sub(extra);
/* 104:104 */    } finally { .Stack tmp143_141 = localStack;tmp143_141.pop$com$bulletphysics$linearmath$Transform();tmp143_141.pop$javax$vecmath$Vector3f(); } }
/* 105:    */  
/* 106:106 */  private CollisionAlgorithmConstructionInfo ci = new CollisionAlgorithmConstructionInfo();
/* 107:107 */  private TriangleShape tm = new TriangleShape();
/* 108:    */  
/* 113:    */  public void processTriangle(Vector3f[] arg1, int arg2, int arg3)
/* 114:    */  {
/* 115:115 */    .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$com$bulletphysics$linearmath$Transform();tmp7_5.push$javax$vecmath$Vector3f();this.ci.dispatcher1 = this.dispatcher;
/* 116:    */      
/* 117:117 */      CollisionObject ob = this.triBody;
/* 118:    */      
/* 120:120 */      if ((this.dispatchInfoPtr != null) && (this.dispatchInfoPtr.debugDraw != null) && (this.dispatchInfoPtr.debugDraw.getDebugMode() > 0)) {
/* 121:121 */        Vector3f color = localStack.get$javax$vecmath$Vector3f();
/* 122:122 */        color.set(255.0F, 255.0F, 0.0F);
/* 123:123 */        Transform tr = ob.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform());
/* 124:    */        
/* 125:125 */        Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
/* 126:126 */        Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/* 127:    */        
/* 128:128 */        tmp1.set(triangle[0]);tr.transform(tmp1);
/* 129:129 */        tmp2.set(triangle[1]);tr.transform(tmp2);
/* 130:130 */        this.dispatchInfoPtr.debugDraw.drawLine(tmp1, tmp2, color);
/* 131:    */        
/* 132:132 */        tmp1.set(triangle[1]);tr.transform(tmp1);
/* 133:133 */        tmp2.set(triangle[2]);tr.transform(tmp2);
/* 134:134 */        this.dispatchInfoPtr.debugDraw.drawLine(tmp1, tmp2, color);
/* 135:    */        
/* 136:136 */        tmp1.set(triangle[2]);tr.transform(tmp1);
/* 137:137 */        tmp2.set(triangle[0]);tr.transform(tmp2);
/* 138:138 */        this.dispatchInfoPtr.debugDraw.drawLine(tmp1, tmp2, color);
/* 139:    */      }
/* 140:    */      
/* 149:149 */      if (this.convexBody.getCollisionShape().isConvex()) {
/* 150:150 */        this.tm.init(triangle[0], triangle[1], triangle[2]);
/* 151:151 */        this.tm.setMargin(this.collisionMarginTriangle);
/* 152:    */        
/* 153:153 */        CollisionShape tmpShape = ob.getCollisionShape();
/* 154:154 */        ob.internalSetTemporaryCollisionShape(this.tm);
/* 155:    */        
/* 156:156 */        CollisionAlgorithm colAlgo = this.ci.dispatcher1.findAlgorithm(this.convexBody, this.triBody, this.manifoldPtr);
/* 157:    */        
/* 160:160 */        this.resultOut.setShapeIdentifiers(-1, -1, partId, triangleIndex);
/* 161:    */        
/* 163:163 */        colAlgo.processCollision(this.convexBody, this.triBody, this.dispatchInfoPtr, this.resultOut);
/* 164:    */        
/* 165:165 */        this.ci.dispatcher1.freeCollisionAlgorithm(colAlgo);
/* 166:166 */        ob.internalSetTemporaryCollisionShape(tmpShape);
/* 167:    */      }
/* 168:168 */    } finally { .Stack tmp385_383 = localStack;tmp385_383.pop$com$bulletphysics$linearmath$Transform();tmp385_383.pop$javax$vecmath$Vector3f();
/* 169:    */    } }
/* 170:    */  
/* 171:171 */  public void clearCache() { this.dispatcher.clearManifold(this.manifoldPtr); }
/* 172:    */  
/* 173:    */  public Vector3f getAabbMin(Vector3f out)
/* 174:    */  {
/* 175:175 */    out.set(this.aabbMin);
/* 176:176 */    return out;
/* 177:    */  }
/* 178:    */  
/* 179:    */  public Vector3f getAabbMax(Vector3f out) {
/* 180:180 */    out.set(this.aabbMax);
/* 181:181 */    return out;
/* 182:    */  }
/* 183:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.dispatch.ConvexTriangleCallback
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */