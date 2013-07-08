/*   1:    */package com.bulletphysics.collision.shapes;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.linearmath.AabbUtil2;
/*   5:    */import com.bulletphysics.linearmath.Transform;
/*   6:    */import com.bulletphysics.linearmath.VectorUtil;
/*   7:    */import javax.vecmath.Vector3f;
/*   8:    */
/*  36:    */public abstract class PolyhedralConvexShape
/*  37:    */  extends ConvexInternalShape
/*  38:    */{
/*  39: 39 */  private static Vector3f[] _directions = { new Vector3f(1.0F, 0.0F, 0.0F), new Vector3f(0.0F, 1.0F, 0.0F), new Vector3f(0.0F, 0.0F, 1.0F), new Vector3f(-1.0F, 0.0F, 0.0F), new Vector3f(0.0F, -1.0F, 0.0F), new Vector3f(0.0F, 0.0F, -1.0F) };
/*  40:    */  
/*  48: 48 */  private static Vector3f[] _supporting = { new Vector3f(0.0F, 0.0F, 0.0F), new Vector3f(0.0F, 0.0F, 0.0F), new Vector3f(0.0F, 0.0F, 0.0F), new Vector3f(0.0F, 0.0F, 0.0F), new Vector3f(0.0F, 0.0F, 0.0F), new Vector3f(0.0F, 0.0F, 0.0F) };
/*  49:    */  
/*  57: 57 */  protected final Vector3f localAabbMin = new Vector3f(1.0F, 1.0F, 1.0F);
/*  58: 58 */  protected final Vector3f localAabbMax = new Vector3f(-1.0F, -1.0F, -1.0F);
/*  59: 59 */  protected boolean isLocalAabbValid = false;
/*  60:    */  
/*  65:    */  public Vector3f localGetSupportingVertexWithoutMargin(Vector3f arg1, Vector3f arg2)
/*  66:    */  {
/*  67: 67 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f supVec = out;
/*  68: 68 */      supVec.set(0.0F, 0.0F, 0.0F);
/*  69:    */      
/*  70: 70 */      float maxDot = -1.0E+030F;
/*  71:    */      
/*  72: 72 */      Vector3f vec = localStack.get$javax$vecmath$Vector3f(vec0);
/*  73: 73 */      float lenSqr = vec.lengthSquared();
/*  74: 74 */      if (lenSqr < 1.0E-004F) {
/*  75: 75 */        vec.set(1.0F, 0.0F, 0.0F);
/*  76:    */      }
/*  77:    */      else {
/*  78: 78 */        float rlen = 1.0F / (float)Math.sqrt(lenSqr);
/*  79: 79 */        vec.scale(rlen);
/*  80:    */      }
/*  81:    */      
/*  82: 82 */      Vector3f vtx = localStack.get$javax$vecmath$Vector3f();
/*  83:    */      
/*  85: 85 */      for (int i = 0; i < getNumVertices(); i++) {
/*  86: 86 */        getVertex(i, vtx);
/*  87: 87 */        float newDot = vec.dot(vtx);
/*  88: 88 */        if (newDot > maxDot) {
/*  89: 89 */          maxDot = newDot;
/*  90: 90 */          supVec = vtx;
/*  91:    */        }
/*  92:    */      }
/*  93:    */      
/*  94: 94 */      return out; } finally { localStack.pop$javax$vecmath$Vector3f();
/*  95:    */    }
/*  96:    */  }
/*  97:    */  
/*  99:    */  public void batchedUnitVectorGetSupportingVertexWithoutMargin(Vector3f[] arg1, Vector3f[] arg2, int arg3)
/* 100:    */  {
/* 101:101 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f vtx = localStack.get$javax$vecmath$Vector3f();
/* 102:    */      
/* 106:106 */      float[] wcoords = new float[numVectors];
/* 107:    */      
/* 108:108 */      for (int i = 0; i < numVectors; i++)
/* 109:    */      {
/* 111:111 */        wcoords[i] = -1.0E+030F;
/* 112:    */      }
/* 113:    */      
/* 114:114 */      for (int j = 0; j < numVectors; j++) {
/* 115:115 */        Vector3f vec = vectors[j];
/* 116:    */        
/* 117:117 */        for (i = 0; i < getNumVertices(); i++) {
/* 118:118 */          getVertex(i, vtx);
/* 119:119 */          float newDot = vec.dot(vtx);
/* 120:    */          
/* 121:121 */          if (newDot > wcoords[j])
/* 122:    */          {
/* 123:123 */            supportVerticesOut[j].set(vtx);
/* 124:    */            
/* 125:125 */            wcoords[j] = newDot;
/* 126:    */          }
/* 127:    */        }
/* 128:    */      }
/* 129:129 */    } finally { localStack.pop$javax$vecmath$Vector3f();
/* 130:    */    }
/* 131:    */  }
/* 132:    */  
/* 133:    */  public void calculateLocalInertia(float arg1, Vector3f arg2)
/* 134:    */  {
/* 135:135 */    .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$com$bulletphysics$linearmath$Transform();tmp7_5.push$javax$vecmath$Vector3f();float margin = getMargin();
/* 136:    */      
/* 137:137 */      Transform ident = localStack.get$com$bulletphysics$linearmath$Transform();
/* 138:138 */      ident.setIdentity();
/* 139:139 */      Vector3f aabbMin = localStack.get$javax$vecmath$Vector3f();Vector3f aabbMax = localStack.get$javax$vecmath$Vector3f();
/* 140:140 */      getAabb(ident, aabbMin, aabbMax);
/* 141:    */      
/* 142:142 */      Vector3f halfExtents = localStack.get$javax$vecmath$Vector3f();
/* 143:143 */      halfExtents.sub(aabbMax, aabbMin);
/* 144:144 */      halfExtents.scale(0.5F);
/* 145:    */      
/* 146:146 */      float lx = 2.0F * (halfExtents.x + margin);
/* 147:147 */      float ly = 2.0F * (halfExtents.y + margin);
/* 148:148 */      float lz = 2.0F * (halfExtents.z + margin);
/* 149:149 */      float x2 = lx * lx;
/* 150:150 */      float y2 = ly * ly;
/* 151:151 */      float z2 = lz * lz;
/* 152:152 */      float scaledmass = mass * 0.08333333F;
/* 153:    */      
/* 154:154 */      inertia.set(y2 + z2, x2 + z2, x2 + y2);
/* 155:155 */      inertia.scale(scaledmass);
/* 156:156 */    } finally { .Stack tmp175_173 = localStack;tmp175_173.pop$com$bulletphysics$linearmath$Transform();tmp175_173.pop$javax$vecmath$Vector3f();
/* 157:    */    }
/* 158:    */  }
/* 159:    */  
/* 160:160 */  private void getNonvirtualAabb(Transform trans, Vector3f aabbMin, Vector3f aabbMax, float margin) { assert (this.isLocalAabbValid);
/* 161:    */    
/* 162:162 */    AabbUtil2.transformAabb(this.localAabbMin, this.localAabbMax, margin, trans, aabbMin, aabbMax);
/* 163:    */  }
/* 164:    */  
/* 165:    */  public void getAabb(Transform trans, Vector3f aabbMin, Vector3f aabbMax)
/* 166:    */  {
/* 167:167 */    getNonvirtualAabb(trans, aabbMin, aabbMax, getMargin());
/* 168:    */  }
/* 169:    */  
/* 170:    */  protected final void _PolyhedralConvexShape_getAabb(Transform trans, Vector3f aabbMin, Vector3f aabbMax) {
/* 171:171 */    getNonvirtualAabb(trans, aabbMin, aabbMax, getMargin());
/* 172:    */  }
/* 173:    */  
/* 174:    */  public void recalcLocalAabb() {
/* 175:175 */    this.isLocalAabbValid = true;
/* 176:    */    
/* 179:179 */    batchedUnitVectorGetSupportingVertexWithoutMargin(_directions, _supporting, 6);
/* 180:    */    
/* 181:181 */    for (int i = 0; i < 3; i++) {
/* 182:182 */      VectorUtil.setCoord(this.localAabbMax, i, VectorUtil.getCoord(_supporting[i], i) + this.collisionMargin);
/* 183:183 */      VectorUtil.setCoord(this.localAabbMin, i, VectorUtil.getCoord(_supporting[(i + 3)], i) - this.collisionMargin);
/* 184:    */    }
/* 185:    */  }
/* 186:    */  
/* 200:    */  public void setLocalScaling(Vector3f scaling)
/* 201:    */  {
/* 202:202 */    super.setLocalScaling(scaling);
/* 203:203 */    recalcLocalAabb();
/* 204:    */  }
/* 205:    */  
/* 206:    */  public abstract int getNumVertices();
/* 207:    */  
/* 208:    */  public abstract int getNumEdges();
/* 209:    */  
/* 210:    */  public abstract void getEdge(int paramInt, Vector3f paramVector3f1, Vector3f paramVector3f2);
/* 211:    */  
/* 212:    */  public abstract void getVertex(int paramInt, Vector3f paramVector3f);
/* 213:    */  
/* 214:    */  public abstract int getNumPlanes();
/* 215:    */  
/* 216:    */  public abstract void getPlane(Vector3f paramVector3f1, Vector3f paramVector3f2, int paramInt);
/* 217:    */  
/* 218:    */  public abstract boolean isInside(Vector3f paramVector3f, float paramFloat);
/* 219:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.PolyhedralConvexShape
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */