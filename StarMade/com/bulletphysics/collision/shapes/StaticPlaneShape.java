/*   1:    */package com.bulletphysics.collision.shapes;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
/*   5:    */import com.bulletphysics.linearmath.Transform;
/*   6:    */import com.bulletphysics.linearmath.TransformUtil;
/*   7:    */import com.bulletphysics.linearmath.VectorUtil;
/*   8:    */import javax.vecmath.Vector3f;
/*   9:    */
/*  37:    */public class StaticPlaneShape
/*  38:    */  extends ConcaveShape
/*  39:    */{
/*  40: 40 */  protected final Vector3f localAabbMin = new Vector3f();
/*  41: 41 */  protected final Vector3f localAabbMax = new Vector3f();
/*  42:    */  
/*  43: 43 */  protected final Vector3f planeNormal = new Vector3f();
/*  44:    */  protected float planeConstant;
/*  45: 45 */  protected final Vector3f localScaling = new Vector3f(0.0F, 0.0F, 0.0F);
/*  46:    */  
/*  47:    */  public StaticPlaneShape(Vector3f planeNormal, float planeConstant) {
/*  48: 48 */    this.planeNormal.normalize(planeNormal);
/*  49: 49 */    this.planeConstant = planeConstant;
/*  50:    */  }
/*  51:    */  
/*  52:    */  public Vector3f getPlaneNormal(Vector3f out) {
/*  53: 53 */    out.set(this.planeNormal);
/*  54: 54 */    return out;
/*  55:    */  }
/*  56:    */  
/*  57:    */  public float getPlaneConstant() {
/*  58: 58 */    return this.planeConstant;
/*  59:    */  }
/*  60:    */  
/*  61:    */  public void processAllTriangles(TriangleCallback arg1, Vector3f arg2, Vector3f arg3)
/*  62:    */  {
/*  63: 63 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*  64: 64 */      Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
/*  65: 65 */      Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/*  66:    */      
/*  67: 67 */      Vector3f halfExtents = localStack.get$javax$vecmath$Vector3f();
/*  68: 68 */      halfExtents.sub(aabbMax, aabbMin);
/*  69: 69 */      halfExtents.scale(0.5F);
/*  70:    */      
/*  71: 71 */      float radius = halfExtents.length();
/*  72: 72 */      Vector3f center = localStack.get$javax$vecmath$Vector3f();
/*  73: 73 */      center.add(aabbMax, aabbMin);
/*  74: 74 */      center.scale(0.5F);
/*  75:    */      
/*  78: 78 */      Vector3f tangentDir0 = localStack.get$javax$vecmath$Vector3f();Vector3f tangentDir1 = localStack.get$javax$vecmath$Vector3f();
/*  79:    */      
/*  81: 81 */      TransformUtil.planeSpace1(this.planeNormal, tangentDir0, tangentDir1);
/*  82:    */      
/*  83: 83 */      Vector3f supVertex0 = localStack.get$javax$vecmath$Vector3f();Vector3f supVertex1 = localStack.get$javax$vecmath$Vector3f();
/*  84:    */      
/*  85: 85 */      Vector3f projectedCenter = localStack.get$javax$vecmath$Vector3f();
/*  86: 86 */      tmp.scale(this.planeNormal.dot(center) - this.planeConstant, this.planeNormal);
/*  87: 87 */      projectedCenter.sub(center, tmp);
/*  88:    */      
/*  89: 89 */      Vector3f[] triangle = { localStack.get$javax$vecmath$Vector3f(), localStack.get$javax$vecmath$Vector3f(), localStack.get$javax$vecmath$Vector3f() };
/*  90:    */      
/*  91: 91 */      tmp1.scale(radius, tangentDir0);
/*  92: 92 */      tmp2.scale(radius, tangentDir1);
/*  93: 93 */      VectorUtil.add(triangle[0], projectedCenter, tmp1, tmp2);
/*  94:    */      
/*  95: 95 */      tmp1.scale(radius, tangentDir0);
/*  96: 96 */      tmp2.scale(radius, tangentDir1);
/*  97: 97 */      tmp.sub(tmp1, tmp2);
/*  98: 98 */      VectorUtil.add(triangle[1], projectedCenter, tmp);
/*  99:    */      
/* 100:100 */      tmp1.scale(radius, tangentDir0);
/* 101:101 */      tmp2.scale(radius, tangentDir1);
/* 102:102 */      tmp.sub(tmp1, tmp2);
/* 103:103 */      triangle[2].sub(projectedCenter, tmp);
/* 104:    */      
/* 105:105 */      callback.processTriangle(triangle, 0, 0);
/* 106:    */      
/* 107:107 */      tmp1.scale(radius, tangentDir0);
/* 108:108 */      tmp2.scale(radius, tangentDir1);
/* 109:109 */      tmp.sub(tmp1, tmp2);
/* 110:110 */      triangle[0].sub(projectedCenter, tmp);
/* 111:    */      
/* 112:112 */      tmp1.scale(radius, tangentDir0);
/* 113:113 */      tmp2.scale(radius, tangentDir1);
/* 114:114 */      tmp.add(tmp1, tmp2);
/* 115:115 */      triangle[1].sub(projectedCenter, tmp);
/* 116:    */      
/* 117:117 */      tmp1.scale(radius, tangentDir0);
/* 118:118 */      tmp2.scale(radius, tangentDir1);
/* 119:119 */      VectorUtil.add(triangle[2], projectedCenter, tmp1, tmp2);
/* 120:    */      
/* 121:121 */      callback.processTriangle(triangle, 0, 1);
/* 122:122 */    } finally { localStack.pop$javax$vecmath$Vector3f();
/* 123:    */    }
/* 124:    */  }
/* 125:    */  
/* 126:126 */  public void getAabb(Transform t, Vector3f aabbMin, Vector3f aabbMax) { aabbMin.set(-1.0E+030F, -1.0E+030F, -1.0E+030F);
/* 127:127 */    aabbMax.set(1.0E+030F, 1.0E+030F, 1.0E+030F);
/* 128:    */  }
/* 129:    */  
/* 130:    */  public BroadphaseNativeType getShapeType()
/* 131:    */  {
/* 132:132 */    return BroadphaseNativeType.STATIC_PLANE_PROXYTYPE;
/* 133:    */  }
/* 134:    */  
/* 135:    */  public void setLocalScaling(Vector3f scaling)
/* 136:    */  {
/* 137:137 */    this.localScaling.set(scaling);
/* 138:    */  }
/* 139:    */  
/* 140:    */  public Vector3f getLocalScaling(Vector3f out)
/* 141:    */  {
/* 142:142 */    out.set(this.localScaling);
/* 143:143 */    return out;
/* 144:    */  }
/* 145:    */  
/* 147:    */  public void calculateLocalInertia(float mass, Vector3f inertia)
/* 148:    */  {
/* 149:149 */    inertia.set(0.0F, 0.0F, 0.0F);
/* 150:    */  }
/* 151:    */  
/* 152:    */  public String getName()
/* 153:    */  {
/* 154:154 */    return "STATICPLANE";
/* 155:    */  }
/* 156:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.StaticPlaneShape
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */