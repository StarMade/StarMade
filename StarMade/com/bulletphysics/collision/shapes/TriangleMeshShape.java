/*   1:    */package com.bulletphysics.collision.shapes;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.linearmath.AabbUtil2;
/*   5:    */import com.bulletphysics.linearmath.MatrixUtil;
/*   6:    */import com.bulletphysics.linearmath.Transform;
/*   7:    */import com.bulletphysics.linearmath.VectorUtil;
/*   8:    */import javax.vecmath.Matrix3f;
/*   9:    */import javax.vecmath.Vector3f;
/*  10:    */
/*  39:    */public abstract class TriangleMeshShape
/*  40:    */  extends ConcaveShape
/*  41:    */{
/*  42: 42 */  protected final Vector3f localAabbMin = new Vector3f();
/*  43: 43 */  protected final Vector3f localAabbMax = new Vector3f();
/*  44:    */  
/*  46:    */  protected StridingMeshInterface meshInterface;
/*  47:    */  
/*  49:    */  protected TriangleMeshShape(StridingMeshInterface meshInterface)
/*  50:    */  {
/*  51: 51 */    this.meshInterface = meshInterface;
/*  52:    */  }
/*  53:    */  
/*  56:    */  public Vector3f localGetSupportingVertex(Vector3f arg1, Vector3f arg2)
/*  57:    */  {
/*  58: 58 */    .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$com$bulletphysics$linearmath$Transform();tmp7_5.push$javax$vecmath$Vector3f();Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*  59:    */      
/*  60: 60 */      Vector3f supportVertex = out;
/*  61:    */      
/*  62: 62 */      Transform ident = localStack.get$com$bulletphysics$linearmath$Transform();
/*  63: 63 */      ident.setIdentity();
/*  64:    */      
/*  65: 65 */      SupportVertexCallback supportCallback = new SupportVertexCallback(vec, ident);
/*  66:    */      
/*  67: 67 */      Vector3f aabbMax = localStack.get$javax$vecmath$Vector3f();
/*  68: 68 */      aabbMax.set(1.0E+030F, 1.0E+030F, 1.0E+030F);
/*  69: 69 */      tmp.negate(aabbMax);
/*  70:    */      
/*  71: 71 */      processAllTriangles(supportCallback, tmp, aabbMax);
/*  72:    */      
/*  73: 73 */      supportCallback.getSupportVertexLocal(supportVertex);
/*  74:    */      
/*  75: 75 */      return out; } finally { .Stack tmp102_100 = localStack;tmp102_100.pop$com$bulletphysics$linearmath$Transform();tmp102_100.pop$javax$vecmath$Vector3f();
/*  76:    */    }
/*  77:    */  }
/*  78:    */  
/*  79: 79 */  public Vector3f localGetSupportingVertexWithoutMargin(Vector3f vec, Vector3f out) { if (!$assertionsDisabled) throw new AssertionError();
/*  80: 80 */    return localGetSupportingVertex(vec, out);
/*  81:    */  }
/*  82:    */  
/*  83:    */  public void recalcLocalAabb() {
/*  84: 84 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f(); for (int i = 0; i < 3; i++) {
/*  85: 85 */        Vector3f vec = localStack.get$javax$vecmath$Vector3f();
/*  86: 86 */        vec.set(0.0F, 0.0F, 0.0F);
/*  87: 87 */        VectorUtil.setCoord(vec, i, 1.0F);
/*  88: 88 */        Vector3f tmp = localGetSupportingVertex(vec, localStack.get$javax$vecmath$Vector3f());
/*  89: 89 */        VectorUtil.setCoord(this.localAabbMax, i, VectorUtil.getCoord(tmp, i) + this.collisionMargin);
/*  90: 90 */        VectorUtil.setCoord(vec, i, -1.0F);
/*  91: 91 */        localGetSupportingVertex(vec, tmp);
/*  92: 92 */        VectorUtil.setCoord(this.localAabbMin, i, VectorUtil.getCoord(tmp, i) - this.collisionMargin);
/*  93:    */      }
/*  94: 94 */    } finally { localStack.pop$javax$vecmath$Vector3f();
/*  95:    */    }
/*  96:    */  }
/*  97:    */  
/*  98: 98 */  public void getAabb(Transform arg1, Vector3f arg2, Vector3f arg3) { .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$javax$vecmath$Vector3f();tmp7_5.push$javax$vecmath$Matrix3f();Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*  99:    */      
/* 100:100 */      Vector3f localHalfExtents = localStack.get$javax$vecmath$Vector3f();
/* 101:101 */      localHalfExtents.sub(this.localAabbMax, this.localAabbMin);
/* 102:102 */      localHalfExtents.scale(0.5F);
/* 103:    */      
/* 104:104 */      Vector3f localCenter = localStack.get$javax$vecmath$Vector3f();
/* 105:105 */      localCenter.add(this.localAabbMax, this.localAabbMin);
/* 106:106 */      localCenter.scale(0.5F);
/* 107:    */      
/* 108:108 */      Matrix3f abs_b = localStack.get$javax$vecmath$Matrix3f(trans.basis);
/* 109:109 */      MatrixUtil.absolute(abs_b);
/* 110:    */      
/* 111:111 */      Vector3f center = localStack.get$javax$vecmath$Vector3f(localCenter);
/* 112:112 */      trans.transform(center);
/* 113:    */      
/* 114:114 */      Vector3f extent = localStack.get$javax$vecmath$Vector3f();
/* 115:115 */      abs_b.getRow(0, tmp);
/* 116:116 */      extent.x = tmp.dot(localHalfExtents);
/* 117:117 */      abs_b.getRow(1, tmp);
/* 118:118 */      extent.y = tmp.dot(localHalfExtents);
/* 119:119 */      abs_b.getRow(2, tmp);
/* 120:120 */      extent.z = tmp.dot(localHalfExtents);
/* 121:    */      
/* 122:122 */      Vector3f margin = localStack.get$javax$vecmath$Vector3f();
/* 123:123 */      margin.set(getMargin(), getMargin(), getMargin());
/* 124:124 */      extent.add(margin);
/* 125:    */      
/* 126:126 */      aabbMin.sub(center, extent);
/* 127:127 */      aabbMax.add(center, extent);
/* 128:128 */    } finally { .Stack tmp234_232 = localStack;tmp234_232.pop$javax$vecmath$Vector3f();tmp234_232.pop$javax$vecmath$Matrix3f();
/* 129:    */    }
/* 130:    */  }
/* 131:    */  
/* 132:132 */  public void processAllTriangles(TriangleCallback callback, Vector3f aabbMin, Vector3f aabbMax) { FilteredCallback filterCallback = new FilteredCallback(callback, aabbMin, aabbMax);
/* 133:    */    
/* 134:134 */    this.meshInterface.internalProcessAllTriangles(filterCallback, aabbMin, aabbMax);
/* 135:    */  }
/* 136:    */  
/* 138:    */  public void calculateLocalInertia(float mass, Vector3f inertia)
/* 139:    */  {
/* 140:140 */    if (!$assertionsDisabled) throw new AssertionError();
/* 141:141 */    inertia.set(0.0F, 0.0F, 0.0F);
/* 142:    */  }
/* 143:    */  
/* 145:    */  public void setLocalScaling(Vector3f scaling)
/* 146:    */  {
/* 147:147 */    this.meshInterface.setScaling(scaling);
/* 148:148 */    recalcLocalAabb();
/* 149:    */  }
/* 150:    */  
/* 151:    */  public Vector3f getLocalScaling(Vector3f out)
/* 152:    */  {
/* 153:153 */    return this.meshInterface.getScaling(out);
/* 154:    */  }
/* 155:    */  
/* 156:    */  public StridingMeshInterface getMeshInterface() {
/* 157:157 */    return this.meshInterface;
/* 158:    */  }
/* 159:    */  
/* 160:    */  public Vector3f getLocalAabbMin(Vector3f out) {
/* 161:161 */    out.set(this.localAabbMin);
/* 162:162 */    return out;
/* 163:    */  }
/* 164:    */  
/* 165:    */  public Vector3f getLocalAabbMax(Vector3f out) {
/* 166:166 */    out.set(this.localAabbMax);
/* 167:167 */    return out;
/* 168:    */  }
/* 169:    */  
/* 170:    */  public String getName()
/* 171:    */  {
/* 172:172 */    return "TRIANGLEMESH";
/* 173:    */  }
/* 174:    */  
/* 175:    */  private class SupportVertexCallback
/* 176:    */    extends TriangleCallback
/* 177:    */  {
/* 178:178 */    private final Vector3f supportVertexLocal = new Vector3f(0.0F, 0.0F, 0.0F);
/* 179:179 */    public final Transform worldTrans = new Transform();
/* 180:180 */    public float maxDot = -1.0E+030F;
/* 181:181 */    public final Vector3f supportVecLocal = new Vector3f();
/* 182:    */    
/* 183:    */    public SupportVertexCallback(Vector3f supportVecWorld, Transform trans) {
/* 184:184 */      this.worldTrans.set(trans);
/* 185:185 */      MatrixUtil.transposeTransform(this.supportVecLocal, supportVecWorld, this.worldTrans.basis);
/* 186:    */    }
/* 187:    */    
/* 188:    */    public void processTriangle(Vector3f[] triangle, int partId, int triangleIndex) {
/* 189:189 */      for (int i = 0; i < 3; i++) {
/* 190:190 */        float dot = this.supportVecLocal.dot(triangle[i]);
/* 191:191 */        if (dot > this.maxDot) {
/* 192:192 */          this.maxDot = dot;
/* 193:193 */          this.supportVertexLocal.set(triangle[i]);
/* 194:    */        }
/* 195:    */      }
/* 196:    */    }
/* 197:    */    
/* 198:    */    public Vector3f getSupportVertexWorldSpace(Vector3f out) {
/* 199:199 */      out.set(this.supportVertexLocal);
/* 200:200 */      this.worldTrans.transform(out);
/* 201:201 */      return out;
/* 202:    */    }
/* 203:    */    
/* 204:    */    public Vector3f getSupportVertexLocal(Vector3f out) {
/* 205:205 */      out.set(this.supportVertexLocal);
/* 206:206 */      return out;
/* 207:    */    }
/* 208:    */  }
/* 209:    */  
/* 210:    */  private static class FilteredCallback extends InternalTriangleIndexCallback {
/* 211:    */    public TriangleCallback callback;
/* 212:212 */    public final Vector3f aabbMin = new Vector3f();
/* 213:213 */    public final Vector3f aabbMax = new Vector3f();
/* 214:    */    
/* 215:    */    public FilteredCallback(TriangleCallback callback, Vector3f aabbMin, Vector3f aabbMax) {
/* 216:216 */      this.callback = callback;
/* 217:217 */      this.aabbMin.set(aabbMin);
/* 218:218 */      this.aabbMax.set(aabbMax);
/* 219:    */    }
/* 220:    */    
/* 221:    */    public void internalProcessTriangleIndex(Vector3f[] triangle, int partId, int triangleIndex) {
/* 222:222 */      if (AabbUtil2.testTriangleAgainstAabb2(triangle, this.aabbMin, this.aabbMax))
/* 223:    */      {
/* 224:224 */        this.callback.processTriangle(triangle, partId, triangleIndex);
/* 225:    */      }
/* 226:    */    }
/* 227:    */  }
/* 228:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.TriangleMeshShape
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */