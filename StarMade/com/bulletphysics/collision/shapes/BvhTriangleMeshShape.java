/*   1:    */package com.bulletphysics.collision.shapes;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
/*   5:    */import com.bulletphysics.linearmath.VectorUtil;
/*   6:    */import com.bulletphysics.util.ObjectPool;
/*   7:    */import javax.vecmath.Vector3f;
/*   8:    */
/*  48:    */public class BvhTriangleMeshShape
/*  49:    */  extends TriangleMeshShape
/*  50:    */{
/*  51:    */  private OptimizedBvh bvh;
/*  52:    */  private boolean useQuantizedAabbCompression;
/*  53:    */  private boolean ownsBvh;
/*  54: 54 */  private ObjectPool<MyNodeOverlapCallback> myNodeCallbacks = ObjectPool.get(MyNodeOverlapCallback.class);
/*  55:    */  
/*  56:    */  public BvhTriangleMeshShape() {
/*  57: 57 */    super(null);
/*  58: 58 */    this.bvh = null;
/*  59: 59 */    this.ownsBvh = false;
/*  60:    */  }
/*  61:    */  
/*  62:    */  public BvhTriangleMeshShape(StridingMeshInterface meshInterface, boolean useQuantizedAabbCompression) {
/*  63: 63 */    this(meshInterface, useQuantizedAabbCompression, true);
/*  64:    */  }
/*  65:    */  
/*  66:    */  public BvhTriangleMeshShape(StridingMeshInterface meshInterface, boolean useQuantizedAabbCompression, boolean buildBvh) {
/*  67: 67 */    super(meshInterface);
/*  68: 68 */    this.bvh = null;
/*  69: 69 */    this.useQuantizedAabbCompression = useQuantizedAabbCompression;
/*  70: 70 */    this.ownsBvh = false;
/*  71:    */    
/*  75: 75 */    Vector3f bvhAabbMin = new Vector3f();Vector3f bvhAabbMax = new Vector3f();
/*  76: 76 */    meshInterface.calculateAabbBruteForce(bvhAabbMin, bvhAabbMax);
/*  77:    */    
/*  78: 78 */    if (buildBvh) {
/*  79: 79 */      this.bvh = new OptimizedBvh();
/*  80: 80 */      this.bvh.build(meshInterface, useQuantizedAabbCompression, bvhAabbMin, bvhAabbMax);
/*  81: 81 */      this.ownsBvh = true;
/*  82:    */      
/*  84: 84 */      recalcLocalAabb();
/*  85:    */    }
/*  86:    */  }
/*  87:    */  
/*  92:    */  public BvhTriangleMeshShape(StridingMeshInterface meshInterface, boolean useQuantizedAabbCompression, Vector3f bvhAabbMin, Vector3f bvhAabbMax)
/*  93:    */  {
/*  94: 94 */    this(meshInterface, useQuantizedAabbCompression, bvhAabbMin, bvhAabbMax, true);
/*  95:    */  }
/*  96:    */  
/*  99:    */  public BvhTriangleMeshShape(StridingMeshInterface meshInterface, boolean useQuantizedAabbCompression, Vector3f bvhAabbMin, Vector3f bvhAabbMax, boolean buildBvh)
/* 100:    */  {
/* 101:101 */    super(meshInterface);
/* 102:    */    
/* 103:103 */    this.bvh = null;
/* 104:104 */    this.useQuantizedAabbCompression = useQuantizedAabbCompression;
/* 105:105 */    this.ownsBvh = false;
/* 106:    */    
/* 110:110 */    if (buildBvh) {
/* 111:111 */      this.bvh = new OptimizedBvh();
/* 112:    */      
/* 113:113 */      this.bvh.build(meshInterface, useQuantizedAabbCompression, bvhAabbMin, bvhAabbMax);
/* 114:114 */      this.ownsBvh = true;
/* 115:    */    }
/* 116:    */    
/* 118:118 */    recalcLocalAabb();
/* 119:    */  }
/* 120:    */  
/* 121:    */  public boolean getOwnsBvh()
/* 122:    */  {
/* 123:123 */    return this.ownsBvh;
/* 124:    */  }
/* 125:    */  
/* 126:    */  public BroadphaseNativeType getShapeType()
/* 127:    */  {
/* 128:128 */    return BroadphaseNativeType.TRIANGLE_MESH_SHAPE_PROXYTYPE;
/* 129:    */  }
/* 130:    */  
/* 131:    */  public void performRaycast(TriangleCallback callback, Vector3f raySource, Vector3f rayTarget) {
/* 132:132 */    MyNodeOverlapCallback myNodeCallback = (MyNodeOverlapCallback)this.myNodeCallbacks.get();
/* 133:133 */    myNodeCallback.init(callback, this.meshInterface);
/* 134:    */    
/* 135:135 */    this.bvh.reportRayOverlappingNodex(myNodeCallback, raySource, rayTarget);
/* 136:    */    
/* 137:137 */    this.myNodeCallbacks.release(myNodeCallback);
/* 138:    */  }
/* 139:    */  
/* 140:    */  public void performConvexcast(TriangleCallback callback, Vector3f raySource, Vector3f rayTarget, Vector3f aabbMin, Vector3f aabbMax) {
/* 141:141 */    MyNodeOverlapCallback myNodeCallback = (MyNodeOverlapCallback)this.myNodeCallbacks.get();
/* 142:142 */    myNodeCallback.init(callback, this.meshInterface);
/* 143:    */    
/* 144:144 */    this.bvh.reportBoxCastOverlappingNodex(myNodeCallback, raySource, rayTarget, aabbMin, aabbMax);
/* 145:    */    
/* 146:146 */    this.myNodeCallbacks.release(myNodeCallback);
/* 147:    */  }
/* 148:    */  
/* 158:    */  public void processAllTriangles(TriangleCallback callback, Vector3f aabbMin, Vector3f aabbMax)
/* 159:    */  {
/* 160:160 */    MyNodeOverlapCallback myNodeCallback = (MyNodeOverlapCallback)this.myNodeCallbacks.get();
/* 161:161 */    myNodeCallback.init(callback, this.meshInterface);
/* 162:    */    
/* 163:163 */    this.bvh.reportAabbOverlappingNodex(myNodeCallback, aabbMin, aabbMax);
/* 164:    */    
/* 165:165 */    this.myNodeCallbacks.release(myNodeCallback);
/* 166:    */  }
/* 167:    */  
/* 170:    */  public void refitTree(Vector3f aabbMin, Vector3f aabbMax)
/* 171:    */  {
/* 172:172 */    this.bvh.refit(this.meshInterface);
/* 173:    */    
/* 174:174 */    recalcLocalAabb();
/* 175:    */  }
/* 176:    */  
/* 179:    */  public void partialRefitTree(Vector3f aabbMin, Vector3f aabbMax)
/* 180:    */  {
/* 181:181 */    this.bvh.refitPartial(this.meshInterface, aabbMin, aabbMax);
/* 182:    */    
/* 183:183 */    VectorUtil.setMin(this.localAabbMin, aabbMin);
/* 184:184 */    VectorUtil.setMax(this.localAabbMax, aabbMax);
/* 185:    */  }
/* 186:    */  
/* 187:    */  public String getName()
/* 188:    */  {
/* 189:189 */    return "BVHTRIANGLEMESH";
/* 190:    */  }
/* 191:    */  
/* 192:    */  public void setLocalScaling(Vector3f arg1)
/* 193:    */  {
/* 194:194 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 195:195 */      tmp.sub(getLocalScaling(localStack.get$javax$vecmath$Vector3f()), scaling);
/* 196:    */      
/* 197:197 */      if (tmp.lengthSquared() > 1.192093E-007F) {
/* 198:198 */        super.setLocalScaling(scaling);
/* 199:    */        
/* 207:207 */        this.bvh = new OptimizedBvh();
/* 208:    */        
/* 209:209 */        this.bvh.build(this.meshInterface, this.useQuantizedAabbCompression, this.localAabbMin, this.localAabbMax);
/* 210:210 */        this.ownsBvh = true;
/* 211:    */      }
/* 212:212 */    } finally { localStack.pop$javax$vecmath$Vector3f();
/* 213:    */    } }
/* 214:    */  
/* 215:215 */  public OptimizedBvh getOptimizedBvh() { return this.bvh; }
/* 216:    */  
/* 217:    */  public void setOptimizedBvh(OptimizedBvh arg1)
/* 218:    */  {
/* 219:219 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f scaling = localStack.get$javax$vecmath$Vector3f();
/* 220:220 */      scaling.set(1.0F, 1.0F, 1.0F);
/* 221:221 */      setOptimizedBvh(bvh, scaling);
/* 222:222 */    } finally { localStack.pop$javax$vecmath$Vector3f();
/* 223:    */    } }
/* 224:    */  
/* 225:225 */  public void setOptimizedBvh(OptimizedBvh arg1, Vector3f arg2) { .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();assert (this.bvh == null);
/* 226:226 */      assert (!this.ownsBvh);
/* 227:    */      
/* 228:228 */      this.bvh = bvh;
/* 229:229 */      this.ownsBvh = false;
/* 230:    */      
/* 232:232 */      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 233:233 */      tmp.sub(getLocalScaling(localStack.get$javax$vecmath$Vector3f()), scaling);
/* 234:    */      
/* 235:235 */      if (tmp.lengthSquared() > 1.192093E-007F)
/* 236:236 */        super.setLocalScaling(scaling);
/* 237:    */    } finally {
/* 238:238 */      localStack.pop$javax$vecmath$Vector3f();
/* 239:    */    } }
/* 240:    */  
/* 241:241 */  public boolean usesQuantizedAabbCompression() { return this.useQuantizedAabbCompression; }
/* 242:    */  
/* 244:    */  protected static class MyNodeOverlapCallback
/* 245:    */    extends NodeOverlapCallback
/* 246:    */  {
/* 247:    */    public StridingMeshInterface meshInterface;
/* 248:    */    
/* 249:    */    public TriangleCallback callback;
/* 250:250 */    private Vector3f[] triangle = { new Vector3f(), new Vector3f(), new Vector3f() };
/* 251:    */    
/* 254:    */    public void init(TriangleCallback callback, StridingMeshInterface meshInterface)
/* 255:    */    {
/* 256:256 */      this.meshInterface = meshInterface;
/* 257:257 */      this.callback = callback;
/* 258:    */    }
/* 259:    */    
/* 260:    */    public void processNode(int arg1, int arg2) {
/* 261:261 */      .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();VertexData data = this.meshInterface.getLockedReadOnlyVertexIndexBase(nodeSubPart);
/* 262:    */        
/* 263:263 */        Vector3f meshScaling = this.meshInterface.getScaling(localStack.get$javax$vecmath$Vector3f());
/* 264:    */        
/* 265:265 */        data.getTriangle(nodeTriangleIndex * 3, meshScaling, this.triangle);
/* 266:    */        
/* 268:268 */        this.callback.processTriangle(this.triangle, nodeSubPart, nodeTriangleIndex);
/* 269:    */        
/* 270:270 */        this.meshInterface.unLockReadOnlyVertexBase(nodeSubPart);
/* 271:271 */      } finally { localStack.pop$javax$vecmath$Vector3f();
/* 272:    */      }
/* 273:    */    }
/* 274:    */  }
/* 275:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.BvhTriangleMeshShape
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */