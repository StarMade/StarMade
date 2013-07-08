/*   1:    */package com.bulletphysics.collision.shapes;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
/*   5:    */import com.bulletphysics.linearmath.VectorUtil;
/*   6:    */import com.bulletphysics.util.ObjectArrayList;
/*   7:    */import javax.vecmath.Vector3f;
/*   8:    */
/*  39:    */public class ConvexHullShape
/*  40:    */  extends PolyhedralConvexShape
/*  41:    */{
/*  42: 42 */  private final ObjectArrayList<Vector3f> points = new ObjectArrayList();
/*  43:    */  
/*  51:    */  public ConvexHullShape(ObjectArrayList<Vector3f> points)
/*  52:    */  {
/*  53: 53 */    for (int i = 0; i < points.size(); i++) {
/*  54: 54 */      this.points.add(new Vector3f((Vector3f)points.getQuick(i)));
/*  55:    */    }
/*  56:    */    
/*  57: 57 */    recalcLocalAabb();
/*  58:    */  }
/*  59:    */  
/*  60:    */  public void setLocalScaling(Vector3f scaling)
/*  61:    */  {
/*  62: 62 */    this.localScaling.set(scaling);
/*  63: 63 */    recalcLocalAabb();
/*  64:    */  }
/*  65:    */  
/*  66:    */  public void addPoint(Vector3f point) {
/*  67: 67 */    this.points.add(new Vector3f(point));
/*  68: 68 */    recalcLocalAabb();
/*  69:    */  }
/*  70:    */  
/*  71:    */  public ObjectArrayList<Vector3f> getPoints() {
/*  72: 72 */    return this.points;
/*  73:    */  }
/*  74:    */  
/*  75:    */  public int getNumPoints() {
/*  76: 76 */    return this.points.size();
/*  77:    */  }
/*  78:    */  
/*  79:    */  public Vector3f localGetSupportingVertexWithoutMargin(Vector3f arg1, Vector3f arg2)
/*  80:    */  {
/*  81: 81 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f supVec = out;
/*  82: 82 */      supVec.set(0.0F, 0.0F, 0.0F);
/*  83: 83 */      float maxDot = -1.0E+030F;
/*  84:    */      
/*  85: 85 */      Vector3f vec = localStack.get$javax$vecmath$Vector3f(vec0);
/*  86: 86 */      float lenSqr = vec.lengthSquared();
/*  87: 87 */      if (lenSqr < 1.0E-004F) {
/*  88: 88 */        vec.set(1.0F, 0.0F, 0.0F);
/*  89:    */      }
/*  90:    */      else {
/*  91: 91 */        float rlen = 1.0F / (float)Math.sqrt(lenSqr);
/*  92: 92 */        vec.scale(rlen);
/*  93:    */      }
/*  94:    */      
/*  96: 96 */      Vector3f vtx = localStack.get$javax$vecmath$Vector3f();
/*  97: 97 */      for (int i = 0; i < this.points.size(); i++) {
/*  98: 98 */        VectorUtil.mul(vtx, (Vector3f)this.points.getQuick(i), this.localScaling);
/*  99:    */        
/* 100:100 */        float newDot = vec.dot(vtx);
/* 101:101 */        if (newDot > maxDot) {
/* 102:102 */          maxDot = newDot;
/* 103:103 */          supVec.set(vtx);
/* 104:    */        }
/* 105:    */      }
/* 106:106 */      return out; } finally { localStack.pop$javax$vecmath$Vector3f();
/* 107:    */    }
/* 108:    */  }
/* 109:    */  
/* 113:    */  public void batchedUnitVectorGetSupportingVertexWithoutMargin(Vector3f[] arg1, Vector3f[] arg2, int arg3)
/* 114:    */  {
/* 115:115 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();float[] wcoords = new float[numVectors];
/* 116:    */      
/* 119:119 */      for (int i = 0; i < numVectors; i++)
/* 120:    */      {
/* 121:121 */        wcoords[i] = -1.0E+030F;
/* 122:    */      }
/* 123:    */      
/* 124:124 */      Vector3f vtx = localStack.get$javax$vecmath$Vector3f();
/* 125:125 */      for (int i = 0; i < this.points.size(); i++) {
/* 126:126 */        VectorUtil.mul(vtx, (Vector3f)this.points.getQuick(i), this.localScaling);
/* 127:    */        
/* 128:128 */        for (int j = 0; j < numVectors; j++) {
/* 129:129 */          Vector3f vec = vectors[j];
/* 130:    */          
/* 131:131 */          float newDot = vec.dot(vtx);
/* 132:    */          
/* 133:133 */          if (newDot > wcoords[j])
/* 134:    */          {
/* 135:135 */            supportVerticesOut[j].set(vtx);
/* 136:    */            
/* 137:137 */            wcoords[j] = newDot;
/* 138:    */          }
/* 139:    */        }
/* 140:    */      }
/* 141:141 */    } finally { localStack.pop$javax$vecmath$Vector3f();
/* 142:    */    }
/* 143:    */  }
/* 144:    */  
/* 145:145 */  public Vector3f localGetSupportingVertex(Vector3f arg1, Vector3f arg2) { .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f supVertex = localGetSupportingVertexWithoutMargin(vec, out);
/* 146:    */      
/* 147:147 */      if (getMargin() != 0.0F) {
/* 148:148 */        Vector3f vecnorm = localStack.get$javax$vecmath$Vector3f(vec);
/* 149:149 */        if (vecnorm.lengthSquared() < 1.421086E-014F) {
/* 150:150 */          vecnorm.set(-1.0F, -1.0F, -1.0F);
/* 151:    */        }
/* 152:152 */        vecnorm.normalize();
/* 153:153 */        supVertex.scaleAdd(getMargin(), vecnorm, supVertex);
/* 154:    */      }
/* 155:155 */      return out; } finally { localStack.pop$javax$vecmath$Vector3f();
/* 156:    */    }
/* 157:    */  }
/* 158:    */  
/* 162:    */  public int getNumVertices()
/* 163:    */  {
/* 164:164 */    return this.points.size();
/* 165:    */  }
/* 166:    */  
/* 167:    */  public int getNumEdges()
/* 168:    */  {
/* 169:169 */    return this.points.size();
/* 170:    */  }
/* 171:    */  
/* 172:    */  public void getEdge(int i, Vector3f pa, Vector3f pb)
/* 173:    */  {
/* 174:174 */    int index0 = i % this.points.size();
/* 175:175 */    int index1 = (i + 1) % this.points.size();
/* 176:176 */    VectorUtil.mul(pa, (Vector3f)this.points.getQuick(index0), this.localScaling);
/* 177:177 */    VectorUtil.mul(pb, (Vector3f)this.points.getQuick(index1), this.localScaling);
/* 178:    */  }
/* 179:    */  
/* 180:    */  public void getVertex(int i, Vector3f vtx)
/* 181:    */  {
/* 182:182 */    VectorUtil.mul(vtx, (Vector3f)this.points.getQuick(i), this.localScaling);
/* 183:    */  }
/* 184:    */  
/* 185:    */  public int getNumPlanes()
/* 186:    */  {
/* 187:187 */    return 0;
/* 188:    */  }
/* 189:    */  
/* 190:    */  public void getPlane(Vector3f planeNormal, Vector3f planeSupport, int i)
/* 191:    */  {
/* 192:192 */    if (!$assertionsDisabled) throw new AssertionError();
/* 193:    */  }
/* 194:    */  
/* 195:    */  public boolean isInside(Vector3f pt, float tolerance)
/* 196:    */  {
/* 197:197 */    if (!$assertionsDisabled) throw new AssertionError();
/* 198:198 */    return false;
/* 199:    */  }
/* 200:    */  
/* 201:    */  public BroadphaseNativeType getShapeType()
/* 202:    */  {
/* 203:203 */    return BroadphaseNativeType.CONVEX_HULL_SHAPE_PROXYTYPE;
/* 204:    */  }
/* 205:    */  
/* 206:    */  public String getName()
/* 207:    */  {
/* 208:208 */    return "Convex";
/* 209:    */  }
/* 210:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.ConvexHullShape
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */