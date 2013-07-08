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
/*  49:    */public class CapsuleShape
/*  50:    */  extends ConvexInternalShape
/*  51:    */{
/*  52:    */  protected int upAxis;
/*  53:    */  
/*  54:    */  CapsuleShape() {}
/*  55:    */  
/*  56:    */  public CapsuleShape(float radius, float height)
/*  57:    */  {
/*  58: 58 */    this.upAxis = 1;
/*  59: 59 */    this.implicitShapeDimensions.set(radius, 0.5F * height, radius);
/*  60:    */  }
/*  61:    */  
/*  62:    */  public Vector3f localGetSupportingVertexWithoutMargin(Vector3f arg1, Vector3f arg2)
/*  63:    */  {
/*  64: 64 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f supVec = out;
/*  65: 65 */      supVec.set(0.0F, 0.0F, 0.0F);
/*  66:    */      
/*  67: 67 */      float maxDot = -1.0E+030F;
/*  68:    */      
/*  69: 69 */      Vector3f vec = localStack.get$javax$vecmath$Vector3f(vec0);
/*  70: 70 */      float lenSqr = vec.lengthSquared();
/*  71: 71 */      if (lenSqr < 1.0E-004F) {
/*  72: 72 */        vec.set(1.0F, 0.0F, 0.0F);
/*  73:    */      }
/*  74:    */      else {
/*  75: 75 */        float rlen = 1.0F / (float)Math.sqrt(lenSqr);
/*  76: 76 */        vec.scale(rlen);
/*  77:    */      }
/*  78:    */      
/*  79: 79 */      Vector3f vtx = localStack.get$javax$vecmath$Vector3f();
/*  80:    */      
/*  82: 82 */      float radius = getRadius();
/*  83:    */      
/*  84: 84 */      Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
/*  85: 85 */      Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/*  86: 86 */      Vector3f pos = localStack.get$javax$vecmath$Vector3f();
/*  87:    */      
/*  89: 89 */      pos.set(0.0F, 0.0F, 0.0F);
/*  90: 90 */      VectorUtil.setCoord(pos, getUpAxis(), getHalfHeight());
/*  91:    */      
/*  92: 92 */      VectorUtil.mul(tmp1, vec, this.localScaling);
/*  93: 93 */      tmp1.scale(radius);
/*  94: 94 */      tmp2.scale(getMargin(), vec);
/*  95: 95 */      vtx.add(pos, tmp1);
/*  96: 96 */      vtx.sub(tmp2);
/*  97: 97 */      float newDot = vec.dot(vtx);
/*  98: 98 */      if (newDot > maxDot) {
/*  99: 99 */        maxDot = newDot;
/* 100:100 */        supVec.set(vtx);
/* 101:    */      }
/* 102:    */      
/* 104:104 */      pos.set(0.0F, 0.0F, 0.0F);
/* 105:105 */      VectorUtil.setCoord(pos, getUpAxis(), -getHalfHeight());
/* 106:    */      
/* 107:107 */      VectorUtil.mul(tmp1, vec, this.localScaling);
/* 108:108 */      tmp1.scale(radius);
/* 109:109 */      tmp2.scale(getMargin(), vec);
/* 110:110 */      vtx.add(pos, tmp1);
/* 111:111 */      vtx.sub(tmp2);
/* 112:112 */      newDot = vec.dot(vtx);
/* 113:113 */      if (newDot > maxDot) {
/* 114:114 */        maxDot = newDot;
/* 115:115 */        supVec.set(vtx);
/* 116:    */      }
/* 117:    */      
/* 119:119 */      return out; } finally { localStack.pop$javax$vecmath$Vector3f();
/* 120:    */    }
/* 121:    */  }
/* 122:    */  
/* 123:    */  public void batchedUnitVectorGetSupportingVertexWithoutMargin(Vector3f[] vectors, Vector3f[] supportVerticesOut, int numVectors)
/* 124:    */  {
/* 125:125 */    throw new UnsupportedOperationException("Not supported yet.");
/* 126:    */  }
/* 127:    */  
/* 130:    */  public void calculateLocalInertia(float arg1, Vector3f arg2)
/* 131:    */  {
/* 132:132 */    .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$com$bulletphysics$linearmath$Transform();tmp7_5.push$javax$vecmath$Vector3f();Transform ident = localStack.get$com$bulletphysics$linearmath$Transform();
/* 133:133 */      ident.setIdentity();
/* 134:    */      
/* 135:135 */      float radius = getRadius();
/* 136:    */      
/* 137:137 */      Vector3f halfExtents = localStack.get$javax$vecmath$Vector3f();
/* 138:138 */      halfExtents.set(radius, radius, radius);
/* 139:139 */      VectorUtil.setCoord(halfExtents, getUpAxis(), radius + getHalfHeight());
/* 140:    */      
/* 141:141 */      float margin = 0.04F;
/* 142:    */      
/* 143:143 */      float lx = 2.0F * (halfExtents.x + margin);
/* 144:144 */      float ly = 2.0F * (halfExtents.y + margin);
/* 145:145 */      float lz = 2.0F * (halfExtents.z + margin);
/* 146:146 */      float x2 = lx * lx;
/* 147:147 */      float y2 = ly * ly;
/* 148:148 */      float z2 = lz * lz;
/* 149:149 */      float scaledmass = mass * 0.08333333F;
/* 150:    */      
/* 151:151 */      inertia.x = (scaledmass * (y2 + z2));
/* 152:152 */      inertia.y = (scaledmass * (x2 + z2));
/* 153:153 */      inertia.z = (scaledmass * (x2 + y2));
/* 154:154 */    } finally { .Stack tmp179_177 = localStack;tmp179_177.pop$com$bulletphysics$linearmath$Transform();tmp179_177.pop$javax$vecmath$Vector3f();
/* 155:    */    }
/* 156:    */  }
/* 157:    */  
/* 158:158 */  public BroadphaseNativeType getShapeType() { return BroadphaseNativeType.CAPSULE_SHAPE_PROXYTYPE; }
/* 159:    */  
/* 161:    */  public void getAabb(Transform arg1, Vector3f arg2, Vector3f arg3)
/* 162:    */  {
/* 163:163 */    .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$javax$vecmath$Vector3f();tmp7_5.push$javax$vecmath$Matrix3f();Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 164:    */      
/* 165:165 */      Vector3f halfExtents = localStack.get$javax$vecmath$Vector3f();
/* 166:166 */      halfExtents.set(getRadius(), getRadius(), getRadius());
/* 167:167 */      VectorUtil.setCoord(halfExtents, this.upAxis, getRadius() + getHalfHeight());
/* 168:    */      
/* 169:169 */      halfExtents.x += getMargin();
/* 170:170 */      halfExtents.y += getMargin();
/* 171:171 */      halfExtents.z += getMargin();
/* 172:    */      
/* 173:173 */      Matrix3f abs_b = localStack.get$javax$vecmath$Matrix3f();
/* 174:174 */      abs_b.set(t.basis);
/* 175:175 */      MatrixUtil.absolute(abs_b);
/* 176:    */      
/* 177:177 */      Vector3f center = t.origin;
/* 178:178 */      Vector3f extent = localStack.get$javax$vecmath$Vector3f();
/* 179:    */      
/* 180:180 */      abs_b.getRow(0, tmp);
/* 181:181 */      extent.x = tmp.dot(halfExtents);
/* 182:182 */      abs_b.getRow(1, tmp);
/* 183:183 */      extent.y = tmp.dot(halfExtents);
/* 184:184 */      abs_b.getRow(2, tmp);
/* 185:185 */      extent.z = tmp.dot(halfExtents);
/* 186:    */      
/* 187:187 */      aabbMin.sub(center, extent);
/* 188:188 */      aabbMax.add(center, extent);
/* 189:189 */    } finally { .Stack tmp227_225 = localStack;tmp227_225.pop$javax$vecmath$Vector3f();tmp227_225.pop$javax$vecmath$Matrix3f();
/* 190:    */    }
/* 191:    */  }
/* 192:    */  
/* 193:193 */  public String getName() { return "CapsuleShape"; }
/* 194:    */  
/* 195:    */  public int getUpAxis()
/* 196:    */  {
/* 197:197 */    return this.upAxis;
/* 198:    */  }
/* 199:    */  
/* 200:    */  public float getRadius() {
/* 201:201 */    int radiusAxis = (this.upAxis + 2) % 3;
/* 202:202 */    return VectorUtil.getCoord(this.implicitShapeDimensions, radiusAxis);
/* 203:    */  }
/* 204:    */  
/* 205:    */  public float getHalfHeight() {
/* 206:206 */    return VectorUtil.getCoord(this.implicitShapeDimensions, this.upAxis);
/* 207:    */  }
/* 208:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.CapsuleShape
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */