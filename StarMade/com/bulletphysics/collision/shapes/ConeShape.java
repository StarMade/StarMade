/*   1:    */package com.bulletphysics.collision.shapes;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
/*   5:    */import com.bulletphysics.linearmath.Transform;
/*   6:    */import com.bulletphysics.linearmath.VectorUtil;
/*   7:    */import javax.vecmath.Vector3f;
/*   8:    */
/*  39:    */public class ConeShape
/*  40:    */  extends ConvexInternalShape
/*  41:    */{
/*  42:    */  private float sinAngle;
/*  43:    */  private float radius;
/*  44:    */  private float height;
/*  45: 45 */  private int[] coneIndices = new int[3];
/*  46:    */  
/*  47:    */  public ConeShape(float radius, float height) {
/*  48: 48 */    this.radius = radius;
/*  49: 49 */    this.height = height;
/*  50: 50 */    setConeUpIndex(1);
/*  51: 51 */    this.sinAngle = (radius / (float)Math.sqrt(this.radius * this.radius + this.height * this.height));
/*  52:    */  }
/*  53:    */  
/*  54:    */  public float getRadius() {
/*  55: 55 */    return this.radius;
/*  56:    */  }
/*  57:    */  
/*  58:    */  public float getHeight() {
/*  59: 59 */    return this.height;
/*  60:    */  }
/*  61:    */  
/*  62:    */  private Vector3f coneLocalSupport(Vector3f v, Vector3f out) {
/*  63: 63 */    float halfHeight = this.height * 0.5F;
/*  64:    */    
/*  65: 65 */    if (VectorUtil.getCoord(v, this.coneIndices[1]) > v.length() * this.sinAngle) {
/*  66: 66 */      VectorUtil.setCoord(out, this.coneIndices[0], 0.0F);
/*  67: 67 */      VectorUtil.setCoord(out, this.coneIndices[1], halfHeight);
/*  68: 68 */      VectorUtil.setCoord(out, this.coneIndices[2], 0.0F);
/*  69: 69 */      return out;
/*  70:    */    }
/*  71:    */    
/*  72: 72 */    float v0 = VectorUtil.getCoord(v, this.coneIndices[0]);
/*  73: 73 */    float v2 = VectorUtil.getCoord(v, this.coneIndices[2]);
/*  74: 74 */    float s = (float)Math.sqrt(v0 * v0 + v2 * v2);
/*  75: 75 */    if (s > 1.192093E-007F) {
/*  76: 76 */      float d = this.radius / s;
/*  77: 77 */      VectorUtil.setCoord(out, this.coneIndices[0], VectorUtil.getCoord(v, this.coneIndices[0]) * d);
/*  78: 78 */      VectorUtil.setCoord(out, this.coneIndices[1], -halfHeight);
/*  79: 79 */      VectorUtil.setCoord(out, this.coneIndices[2], VectorUtil.getCoord(v, this.coneIndices[2]) * d);
/*  80: 80 */      return out;
/*  81:    */    }
/*  82: 82 */    VectorUtil.setCoord(out, this.coneIndices[0], 0.0F);
/*  83: 83 */    VectorUtil.setCoord(out, this.coneIndices[1], -halfHeight);
/*  84: 84 */    VectorUtil.setCoord(out, this.coneIndices[2], 0.0F);
/*  85: 85 */    return out;
/*  86:    */  }
/*  87:    */  
/*  90:    */  public Vector3f localGetSupportingVertexWithoutMargin(Vector3f vec, Vector3f out)
/*  91:    */  {
/*  92: 92 */    return coneLocalSupport(vec, out);
/*  93:    */  }
/*  94:    */  
/*  95:    */  public void batchedUnitVectorGetSupportingVertexWithoutMargin(Vector3f[] vectors, Vector3f[] supportVerticesOut, int numVectors)
/*  96:    */  {
/*  97: 97 */    for (int i = 0; i < numVectors; i++) {
/*  98: 98 */      Vector3f vec = vectors[i];
/*  99: 99 */      coneLocalSupport(vec, supportVerticesOut[i]);
/* 100:    */    }
/* 101:    */  }
/* 102:    */  
/* 103:    */  public Vector3f localGetSupportingVertex(Vector3f arg1, Vector3f arg2)
/* 104:    */  {
/* 105:105 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f supVertex = coneLocalSupport(vec, out);
/* 106:106 */      if (getMargin() != 0.0F) {
/* 107:107 */        Vector3f vecnorm = localStack.get$javax$vecmath$Vector3f(vec);
/* 108:108 */        if (vecnorm.lengthSquared() < 1.421086E-014F) {
/* 109:109 */          vecnorm.set(-1.0F, -1.0F, -1.0F);
/* 110:    */        }
/* 111:111 */        vecnorm.normalize();
/* 112:112 */        supVertex.scaleAdd(getMargin(), vecnorm, supVertex);
/* 113:    */      }
/* 114:114 */      return supVertex; } finally { localStack.pop$javax$vecmath$Vector3f();
/* 115:    */    }
/* 116:    */  }
/* 117:    */  
/* 118:    */  public BroadphaseNativeType getShapeType() {
/* 119:119 */    return BroadphaseNativeType.CONE_SHAPE_PROXYTYPE;
/* 120:    */  }
/* 121:    */  
/* 122:    */  public void calculateLocalInertia(float arg1, Vector3f arg2)
/* 123:    */  {
/* 124:124 */    .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$com$bulletphysics$linearmath$Transform();tmp7_5.push$javax$vecmath$Vector3f();Transform identity = localStack.get$com$bulletphysics$linearmath$Transform();
/* 125:125 */      identity.setIdentity();
/* 126:126 */      Vector3f aabbMin = localStack.get$javax$vecmath$Vector3f();Vector3f aabbMax = localStack.get$javax$vecmath$Vector3f();
/* 127:127 */      getAabb(identity, aabbMin, aabbMax);
/* 128:    */      
/* 129:129 */      Vector3f halfExtents = localStack.get$javax$vecmath$Vector3f();
/* 130:130 */      halfExtents.sub(aabbMax, aabbMin);
/* 131:131 */      halfExtents.scale(0.5F);
/* 132:    */      
/* 133:133 */      float margin = getMargin();
/* 134:    */      
/* 135:135 */      float lx = 2.0F * (halfExtents.x + margin);
/* 136:136 */      float ly = 2.0F * (halfExtents.y + margin);
/* 137:137 */      float lz = 2.0F * (halfExtents.z + margin);
/* 138:138 */      float x2 = lx * lx;
/* 139:139 */      float y2 = ly * ly;
/* 140:140 */      float z2 = lz * lz;
/* 141:141 */      float scaledmass = mass * 0.08333333F;
/* 142:    */      
/* 143:143 */      inertia.set(y2 + z2, x2 + z2, x2 + y2);
/* 144:144 */      inertia.scale(scaledmass);
/* 146:    */    }
/* 147:    */    finally
/* 148:    */    {
/* 149:149 */      .Stack tmp176_174 = localStack;tmp176_174.pop$com$bulletphysics$linearmath$Transform();tmp176_174.pop$javax$vecmath$Vector3f();
/* 150:    */    }
/* 151:    */  }
/* 152:    */  
/* 153:153 */  public String getName() { return "Cone"; }
/* 154:    */  
/* 156:    */  protected void setConeUpIndex(int upIndex)
/* 157:    */  {
/* 158:158 */    switch (upIndex) {
/* 159:    */    case 0: 
/* 160:160 */      this.coneIndices[0] = 1;
/* 161:161 */      this.coneIndices[1] = 0;
/* 162:162 */      this.coneIndices[2] = 2;
/* 163:163 */      break;
/* 164:    */    
/* 165:    */    case 1: 
/* 166:166 */      this.coneIndices[0] = 0;
/* 167:167 */      this.coneIndices[1] = 1;
/* 168:168 */      this.coneIndices[2] = 2;
/* 169:169 */      break;
/* 170:    */    
/* 171:    */    case 2: 
/* 172:172 */      this.coneIndices[0] = 0;
/* 173:173 */      this.coneIndices[1] = 2;
/* 174:174 */      this.coneIndices[2] = 1;
/* 175:175 */      break;
/* 176:    */    
/* 177:    */    default: 
/* 178:178 */      if (!$assertionsDisabled) throw new AssertionError();
/* 179:    */      break; }
/* 180:    */  }
/* 181:    */  
/* 182:    */  public int getConeUpIndex() {
/* 183:183 */    return this.coneIndices[1];
/* 184:    */  }
/* 185:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.ConeShape
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */