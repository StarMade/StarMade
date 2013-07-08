/*   1:    */package com.bulletphysics.collision.shapes;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
/*   5:    */import com.bulletphysics.linearmath.MatrixUtil;
/*   6:    */import com.bulletphysics.linearmath.Transform;
/*   7:    */import com.bulletphysics.linearmath.VectorUtil;
/*   8:    */import javax.vecmath.Matrix3f;
/*   9:    */import javax.vecmath.Vector3f;
/*  10:    */
/*  42:    */public class ScaledBvhTriangleMeshShape
/*  43:    */  extends ConcaveShape
/*  44:    */{
/*  45: 45 */  protected final Vector3f localScaling = new Vector3f();
/*  46:    */  protected BvhTriangleMeshShape bvhTriMeshShape;
/*  47:    */  
/*  48:    */  public ScaledBvhTriangleMeshShape(BvhTriangleMeshShape childShape, Vector3f localScaling) {
/*  49: 49 */    this.localScaling.set(localScaling);
/*  50: 50 */    this.bvhTriMeshShape = childShape;
/*  51:    */  }
/*  52:    */  
/*  53:    */  public BvhTriangleMeshShape getChildShape() {
/*  54: 54 */    return this.bvhTriMeshShape;
/*  55:    */  }
/*  56:    */  
/*  57:    */  public void processAllTriangles(TriangleCallback arg1, Vector3f arg2, Vector3f arg3)
/*  58:    */  {
/*  59: 59 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();ScaledTriangleCallback scaledCallback = new ScaledTriangleCallback(callback, this.localScaling);
/*  60:    */      
/*  61: 61 */      Vector3f invLocalScaling = localStack.get$javax$vecmath$Vector3f();
/*  62: 62 */      invLocalScaling.set(1.0F / this.localScaling.x, 1.0F / this.localScaling.y, 1.0F / this.localScaling.z);
/*  63:    */      
/*  64: 64 */      Vector3f scaledAabbMin = localStack.get$javax$vecmath$Vector3f();
/*  65: 65 */      Vector3f scaledAabbMax = localStack.get$javax$vecmath$Vector3f();
/*  66:    */      
/*  68: 68 */      scaledAabbMin.x = (this.localScaling.x >= 0.0F ? aabbMin.x * invLocalScaling.x : aabbMax.x * invLocalScaling.x);
/*  69: 69 */      scaledAabbMin.y = (this.localScaling.y >= 0.0F ? aabbMin.y * invLocalScaling.y : aabbMax.y * invLocalScaling.y);
/*  70: 70 */      scaledAabbMin.z = (this.localScaling.z >= 0.0F ? aabbMin.z * invLocalScaling.z : aabbMax.z * invLocalScaling.z);
/*  71:    */      
/*  72: 72 */      scaledAabbMax.x = (this.localScaling.x <= 0.0F ? aabbMin.x * invLocalScaling.x : aabbMax.x * invLocalScaling.x);
/*  73: 73 */      scaledAabbMax.y = (this.localScaling.y <= 0.0F ? aabbMin.y * invLocalScaling.y : aabbMax.y * invLocalScaling.y);
/*  74: 74 */      scaledAabbMax.z = (this.localScaling.z <= 0.0F ? aabbMin.z * invLocalScaling.z : aabbMax.z * invLocalScaling.z);
/*  75:    */      
/*  76: 76 */      this.bvhTriMeshShape.processAllTriangles(scaledCallback, scaledAabbMin, scaledAabbMax);
/*  77: 77 */    } finally { localStack.pop$javax$vecmath$Vector3f();
/*  78:    */    }
/*  79:    */  }
/*  80:    */  
/*  81: 81 */  public void getAabb(Transform arg1, Vector3f arg2, Vector3f arg3) { .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$javax$vecmath$Vector3f();tmp7_5.push$javax$vecmath$Matrix3f();Vector3f localAabbMin = this.bvhTriMeshShape.getLocalAabbMin(localStack.get$javax$vecmath$Vector3f());
/*  82: 82 */      Vector3f localAabbMax = this.bvhTriMeshShape.getLocalAabbMax(localStack.get$javax$vecmath$Vector3f());
/*  83:    */      
/*  84: 84 */      Vector3f tmpLocalAabbMin = localStack.get$javax$vecmath$Vector3f();
/*  85: 85 */      Vector3f tmpLocalAabbMax = localStack.get$javax$vecmath$Vector3f();
/*  86: 86 */      VectorUtil.mul(tmpLocalAabbMin, localAabbMin, this.localScaling);
/*  87: 87 */      VectorUtil.mul(tmpLocalAabbMax, localAabbMax, this.localScaling);
/*  88:    */      
/*  89: 89 */      localAabbMin.x = (this.localScaling.x >= 0.0F ? tmpLocalAabbMin.x : tmpLocalAabbMax.x);
/*  90: 90 */      localAabbMin.y = (this.localScaling.y >= 0.0F ? tmpLocalAabbMin.y : tmpLocalAabbMax.y);
/*  91: 91 */      localAabbMin.z = (this.localScaling.z >= 0.0F ? tmpLocalAabbMin.z : tmpLocalAabbMax.z);
/*  92: 92 */      localAabbMax.x = (this.localScaling.x <= 0.0F ? tmpLocalAabbMin.x : tmpLocalAabbMax.x);
/*  93: 93 */      localAabbMax.y = (this.localScaling.y <= 0.0F ? tmpLocalAabbMin.y : tmpLocalAabbMax.y);
/*  94: 94 */      localAabbMax.z = (this.localScaling.z <= 0.0F ? tmpLocalAabbMin.z : tmpLocalAabbMax.z);
/*  95:    */      
/*  96: 96 */      Vector3f localHalfExtents = localStack.get$javax$vecmath$Vector3f();
/*  97: 97 */      localHalfExtents.sub(localAabbMax, localAabbMin);
/*  98: 98 */      localHalfExtents.scale(0.5F);
/*  99:    */      
/* 100:100 */      float margin = this.bvhTriMeshShape.getMargin();
/* 101:101 */      localHalfExtents.x += margin;
/* 102:102 */      localHalfExtents.y += margin;
/* 103:103 */      localHalfExtents.z += margin;
/* 104:    */      
/* 105:105 */      Vector3f localCenter = localStack.get$javax$vecmath$Vector3f();
/* 106:106 */      localCenter.add(localAabbMax, localAabbMin);
/* 107:107 */      localCenter.scale(0.5F);
/* 108:    */      
/* 109:109 */      Matrix3f abs_b = localStack.get$javax$vecmath$Matrix3f(trans.basis);
/* 110:110 */      MatrixUtil.absolute(abs_b);
/* 111:    */      
/* 112:112 */      Vector3f center = localStack.get$javax$vecmath$Vector3f(localCenter);
/* 113:113 */      trans.transform(center);
/* 114:    */      
/* 115:115 */      Vector3f extent = localStack.get$javax$vecmath$Vector3f();
/* 116:116 */      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 117:117 */      abs_b.getRow(0, tmp);
/* 118:118 */      extent.x = tmp.dot(localHalfExtents);
/* 119:119 */      abs_b.getRow(1, tmp);
/* 120:120 */      extent.y = tmp.dot(localHalfExtents);
/* 121:121 */      abs_b.getRow(2, tmp);
/* 122:122 */      extent.z = tmp.dot(localHalfExtents);
/* 123:    */      
/* 124:124 */      aabbMin.sub(center, extent);
/* 125:125 */      aabbMax.add(center, extent);
/* 126:126 */    } finally { .Stack tmp484_482 = localStack;tmp484_482.pop$javax$vecmath$Vector3f();tmp484_482.pop$javax$vecmath$Matrix3f();
/* 127:    */    }
/* 128:    */  }
/* 129:    */  
/* 130:130 */  public BroadphaseNativeType getShapeType() { return BroadphaseNativeType.SCALED_TRIANGLE_MESH_SHAPE_PROXYTYPE; }
/* 131:    */  
/* 133:    */  public void setLocalScaling(Vector3f scaling)
/* 134:    */  {
/* 135:135 */    this.localScaling.set(scaling);
/* 136:    */  }
/* 137:    */  
/* 138:    */  public Vector3f getLocalScaling(Vector3f out)
/* 139:    */  {
/* 140:140 */    out.set(this.localScaling);
/* 141:141 */    return out;
/* 142:    */  }
/* 143:    */  
/* 145:    */  public void calculateLocalInertia(float mass, Vector3f inertia) {}
/* 146:    */  
/* 148:    */  public String getName()
/* 149:    */  {
/* 150:150 */    return "SCALEDBVHTRIANGLEMESH";
/* 151:    */  }
/* 152:    */  
/* 153:    */  private static class ScaledTriangleCallback
/* 154:    */    extends TriangleCallback
/* 155:    */  {
/* 156:    */    private TriangleCallback originalCallback;
/* 157:    */    private Vector3f localScaling;
/* 158:158 */    private Vector3f[] newTriangle = new Vector3f[3];
/* 159:    */    
/* 160:    */    public ScaledTriangleCallback(TriangleCallback originalCallback, Vector3f localScaling) {
/* 161:161 */      this.originalCallback = originalCallback;
/* 162:162 */      this.localScaling = localScaling;
/* 163:    */      
/* 164:164 */      for (int i = 0; i < this.newTriangle.length; i++) {
/* 165:165 */        this.newTriangle[i] = new Vector3f();
/* 166:    */      }
/* 167:    */    }
/* 168:    */    
/* 169:    */    public void processTriangle(Vector3f[] triangle, int partId, int triangleIndex) {
/* 170:170 */      VectorUtil.mul(this.newTriangle[0], triangle[0], this.localScaling);
/* 171:171 */      VectorUtil.mul(this.newTriangle[1], triangle[1], this.localScaling);
/* 172:172 */      VectorUtil.mul(this.newTriangle[2], triangle[2], this.localScaling);
/* 173:173 */      this.originalCallback.processTriangle(this.newTriangle, partId, triangleIndex);
/* 174:    */    }
/* 175:    */  }
/* 176:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.ScaledBvhTriangleMeshShape
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */