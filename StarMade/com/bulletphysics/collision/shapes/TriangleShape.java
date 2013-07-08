/*   1:    */package com.bulletphysics.collision.shapes;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
/*   5:    */import com.bulletphysics.linearmath.Transform;
/*   6:    */import javax.vecmath.Vector3f;
/*   7:    */
/*  36:    */public class TriangleShape
/*  37:    */  extends PolyhedralConvexShape
/*  38:    */{
/*  39: 39 */  public final Vector3f[] vertices1 = { new Vector3f(), new Vector3f(), new Vector3f() };
/*  40:    */  
/*  42:    */  public TriangleShape() {}
/*  43:    */  
/*  44:    */  public TriangleShape(Vector3f p0, Vector3f p1, Vector3f p2)
/*  45:    */  {
/*  46: 46 */    this.vertices1[0].set(p0);
/*  47: 47 */    this.vertices1[1].set(p1);
/*  48: 48 */    this.vertices1[2].set(p2);
/*  49:    */  }
/*  50:    */  
/*  51:    */  public void init(Vector3f p0, Vector3f p1, Vector3f p2)
/*  52:    */  {
/*  53: 53 */    this.vertices1[0].set(p0);
/*  54: 54 */    this.vertices1[1].set(p1);
/*  55: 55 */    this.vertices1[2].set(p2);
/*  56:    */  }
/*  57:    */  
/*  58:    */  public int getNumVertices()
/*  59:    */  {
/*  60: 60 */    return 3;
/*  61:    */  }
/*  62:    */  
/*  63:    */  public Vector3f getVertexPtr(int index) {
/*  64: 64 */    return this.vertices1[index];
/*  65:    */  }
/*  66:    */  
/*  67:    */  public void getVertex(int index, Vector3f vert)
/*  68:    */  {
/*  69: 69 */    vert.set(this.vertices1[index]);
/*  70:    */  }
/*  71:    */  
/*  72:    */  public BroadphaseNativeType getShapeType()
/*  73:    */  {
/*  74: 74 */    return BroadphaseNativeType.TRIANGLE_SHAPE_PROXYTYPE;
/*  75:    */  }
/*  76:    */  
/*  77:    */  public int getNumEdges()
/*  78:    */  {
/*  79: 79 */    return 3;
/*  80:    */  }
/*  81:    */  
/*  82:    */  public void getEdge(int i, Vector3f pa, Vector3f pb)
/*  83:    */  {
/*  84: 84 */    getVertex(i, pa);
/*  85: 85 */    getVertex((i + 1) % 3, pb);
/*  86:    */  }
/*  87:    */  
/*  89:    */  public void getAabb(Transform t, Vector3f aabbMin, Vector3f aabbMax)
/*  90:    */  {
/*  91: 91 */    getAabbSlow(t, aabbMin, aabbMax);
/*  92:    */  }
/*  93:    */  
/*  94:    */  public Vector3f localGetSupportingVertexWithoutMargin(Vector3f arg1, Vector3f arg2)
/*  95:    */  {
/*  96: 96 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f dots = localStack.get$javax$vecmath$Vector3f();
/*  97: 97 */      dots.set(dir.dot(this.vertices1[0]), dir.dot(this.vertices1[1]), dir.dot(this.vertices1[2]));
/*  98: 98 */      out.set(this.vertices1[com.bulletphysics.linearmath.VectorUtil.maxAxis(dots)]);
/*  99: 99 */      return out; } finally { localStack.pop$javax$vecmath$Vector3f();
/* 100:    */    }
/* 101:    */  }
/* 102:    */  
/* 103:    */  public void batchedUnitVectorGetSupportingVertexWithoutMargin(Vector3f[] arg1, Vector3f[] arg2, int arg3) {
/* 104:104 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f dots = localStack.get$javax$vecmath$Vector3f();
/* 105:    */      
/* 106:106 */      for (int i = 0; i < numVectors; i++) {
/* 107:107 */        Vector3f dir = vectors[i];
/* 108:108 */        dots.set(dir.dot(this.vertices1[0]), dir.dot(this.vertices1[1]), dir.dot(this.vertices1[2]));
/* 109:109 */        supportVerticesOut[i].set(this.vertices1[com.bulletphysics.linearmath.VectorUtil.maxAxis(dots)]);
/* 110:    */      }
/* 111:111 */    } finally { localStack.pop$javax$vecmath$Vector3f();
/* 112:    */    }
/* 113:    */  }
/* 114:    */  
/* 115:115 */  public void getPlane(Vector3f planeNormal, Vector3f planeSupport, int i) { getPlaneEquation(i, planeNormal, planeSupport); }
/* 116:    */  
/* 118:    */  public int getNumPlanes()
/* 119:    */  {
/* 120:120 */    return 1;
/* 121:    */  }
/* 122:    */  
/* 123:    */  public void calcNormal(Vector3f arg1) {
/* 124:124 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
/* 125:125 */      Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/* 126:    */      
/* 127:127 */      tmp1.sub(this.vertices1[1], this.vertices1[0]);
/* 128:128 */      tmp2.sub(this.vertices1[2], this.vertices1[0]);
/* 129:    */      
/* 130:130 */      normal.cross(tmp1, tmp2);
/* 131:131 */      normal.normalize();
/* 132:132 */    } finally { localStack.pop$javax$vecmath$Vector3f();
/* 133:    */    } }
/* 134:    */  
/* 135:135 */  public void getPlaneEquation(int i, Vector3f planeNormal, Vector3f planeSupport) { calcNormal(planeNormal);
/* 136:136 */    planeSupport.set(this.vertices1[0]);
/* 137:    */  }
/* 138:    */  
/* 139:    */  public void calculateLocalInertia(float mass, Vector3f inertia)
/* 140:    */  {
/* 141:141 */    if (!$assertionsDisabled) throw new AssertionError();
/* 142:142 */    inertia.set(0.0F, 0.0F, 0.0F);
/* 143:    */  }
/* 144:    */  
/* 145:    */  public boolean isInside(Vector3f arg1, float arg2)
/* 146:    */  {
/* 147:147 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f normal = localStack.get$javax$vecmath$Vector3f();
/* 148:148 */      calcNormal(normal);
/* 149:    */      
/* 150:150 */      float dist = pt.dot(normal);
/* 151:151 */      float planeconst = this.vertices1[0].dot(normal);
/* 152:152 */      dist -= planeconst;
/* 153:153 */      if ((dist >= -tolerance) && (dist <= tolerance))
/* 154:    */      {
/* 156:156 */        for (int i = 0; i < 3; i++) {
/* 157:157 */          Vector3f pa = localStack.get$javax$vecmath$Vector3f();Vector3f pb = localStack.get$javax$vecmath$Vector3f();
/* 158:158 */          getEdge(i, pa, pb);
/* 159:159 */          Vector3f edge = localStack.get$javax$vecmath$Vector3f();
/* 160:160 */          edge.sub(pb, pa);
/* 161:161 */          Vector3f edgeNormal = localStack.get$javax$vecmath$Vector3f();
/* 162:162 */          edgeNormal.cross(edge, normal);
/* 163:163 */          edgeNormal.normalize();
/* 164:164 */          dist = pt.dot(edgeNormal);
/* 165:165 */          float edgeConst = pa.dot(edgeNormal);
/* 166:166 */          dist -= edgeConst;
/* 167:167 */          if (dist < -tolerance) {
/* 168:168 */            return false;
/* 169:    */          }
/* 170:    */        }
/* 171:    */        
/* 172:172 */        return true;
/* 173:    */      }
/* 174:    */      
/* 175:175 */      return false; } finally { localStack.pop$javax$vecmath$Vector3f();
/* 176:    */    }
/* 177:    */  }
/* 178:    */  
/* 179:    */  public String getName() {
/* 180:180 */    return "Triangle";
/* 181:    */  }
/* 182:    */  
/* 183:    */  public int getNumPreferredPenetrationDirections()
/* 184:    */  {
/* 185:185 */    return 2;
/* 186:    */  }
/* 187:    */  
/* 188:    */  public void getPreferredPenetrationDirection(int index, Vector3f penetrationVector)
/* 189:    */  {
/* 190:190 */    calcNormal(penetrationVector);
/* 191:191 */    if (index != 0) {
/* 192:192 */      penetrationVector.scale(-1.0F);
/* 193:    */    }
/* 194:    */  }
/* 195:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.TriangleShape
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */