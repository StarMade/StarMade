/*   1:    */package com.bulletphysics.collision.shapes;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
/*   5:    */import com.bulletphysics.linearmath.Transform;
/*   6:    */import com.bulletphysics.linearmath.VectorUtil;
/*   7:    */import javax.vecmath.Vector3f;
/*   8:    */
/*  38:    */public class CylinderShape
/*  39:    */  extends BoxShape
/*  40:    */{
/*  41:    */  protected int upAxis;
/*  42:    */  
/*  43:    */  public CylinderShape(Vector3f halfExtents)
/*  44:    */  {
/*  45: 45 */    super(halfExtents);
/*  46: 46 */    this.upAxis = 1;
/*  47: 47 */    recalcLocalAabb();
/*  48:    */  }
/*  49:    */  
/*  50:    */  protected CylinderShape(Vector3f halfExtents, boolean unused) {
/*  51: 51 */    super(halfExtents);
/*  52:    */  }
/*  53:    */  
/*  54:    */  public void getAabb(Transform t, Vector3f aabbMin, Vector3f aabbMax)
/*  55:    */  {
/*  56: 56 */    _PolyhedralConvexShape_getAabb(t, aabbMin, aabbMax);
/*  57:    */  }
/*  58:    */  
/*  59:    */  protected Vector3f cylinderLocalSupportX(Vector3f halfExtents, Vector3f v, Vector3f out) {
/*  60: 60 */    return cylinderLocalSupport(halfExtents, v, 0, 1, 0, 2, out);
/*  61:    */  }
/*  62:    */  
/*  63:    */  protected Vector3f cylinderLocalSupportY(Vector3f halfExtents, Vector3f v, Vector3f out) {
/*  64: 64 */    return cylinderLocalSupport(halfExtents, v, 1, 0, 1, 2, out);
/*  65:    */  }
/*  66:    */  
/*  67:    */  protected Vector3f cylinderLocalSupportZ(Vector3f halfExtents, Vector3f v, Vector3f out) {
/*  68: 68 */    return cylinderLocalSupport(halfExtents, v, 2, 0, 2, 1, out);
/*  69:    */  }
/*  70:    */  
/*  73:    */  private Vector3f cylinderLocalSupport(Vector3f halfExtents, Vector3f v, int cylinderUpAxis, int XX, int YY, int ZZ, Vector3f out)
/*  74:    */  {
/*  75: 75 */    float radius = VectorUtil.getCoord(halfExtents, XX);
/*  76: 76 */    float halfHeight = VectorUtil.getCoord(halfExtents, cylinderUpAxis);
/*  77:    */    
/*  80: 80 */    float s = (float)Math.sqrt(VectorUtil.getCoord(v, XX) * VectorUtil.getCoord(v, XX) + VectorUtil.getCoord(v, ZZ) * VectorUtil.getCoord(v, ZZ));
/*  81: 81 */    if (s != 0.0F) {
/*  82: 82 */      float d = radius / s;
/*  83: 83 */      VectorUtil.setCoord(out, XX, VectorUtil.getCoord(v, XX) * d);
/*  84: 84 */      VectorUtil.setCoord(out, YY, VectorUtil.getCoord(v, YY) < 0.0F ? -halfHeight : halfHeight);
/*  85: 85 */      VectorUtil.setCoord(out, ZZ, VectorUtil.getCoord(v, ZZ) * d);
/*  86: 86 */      return out;
/*  87:    */    }
/*  88:    */    
/*  89: 89 */    VectorUtil.setCoord(out, XX, radius);
/*  90: 90 */    VectorUtil.setCoord(out, YY, VectorUtil.getCoord(v, YY) < 0.0F ? -halfHeight : halfHeight);
/*  91: 91 */    VectorUtil.setCoord(out, ZZ, 0.0F);
/*  92: 92 */    return out;
/*  93:    */  }
/*  94:    */  
/*  96:    */  public Vector3f localGetSupportingVertexWithoutMargin(Vector3f arg1, Vector3f arg2)
/*  97:    */  {
/*  98: 98 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();return cylinderLocalSupportY(getHalfExtentsWithoutMargin(localStack.get$javax$vecmath$Vector3f()), vec, out); } finally { localStack.pop$javax$vecmath$Vector3f();
/*  99:    */    }
/* 100:    */  }
/* 101:    */  
/* 102:    */  public void batchedUnitVectorGetSupportingVertexWithoutMargin(Vector3f[] arg1, Vector3f[] arg2, int arg3) {
/* 103:103 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f(); for (int i = 0; i < numVectors; i++)
/* 104:104 */        cylinderLocalSupportY(getHalfExtentsWithoutMargin(localStack.get$javax$vecmath$Vector3f()), vectors[i], supportVerticesOut[i]);
/* 105:    */    } finally {
/* 106:106 */      localStack.pop$javax$vecmath$Vector3f();
/* 107:    */    }
/* 108:    */  }
/* 109:    */  
/* 110:110 */  public Vector3f localGetSupportingVertex(Vector3f arg1, Vector3f arg2) { .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f supVertex = out;
/* 111:111 */      localGetSupportingVertexWithoutMargin(vec, supVertex);
/* 112:    */      
/* 113:113 */      if (getMargin() != 0.0F) {
/* 114:114 */        Vector3f vecnorm = localStack.get$javax$vecmath$Vector3f(vec);
/* 115:115 */        if (vecnorm.lengthSquared() < 1.421086E-014F) {
/* 116:116 */          vecnorm.set(-1.0F, -1.0F, -1.0F);
/* 117:    */        }
/* 118:118 */        vecnorm.normalize();
/* 119:119 */        supVertex.scaleAdd(getMargin(), vecnorm, supVertex);
/* 120:    */      }
/* 121:121 */      return out; } finally { localStack.pop$javax$vecmath$Vector3f();
/* 122:    */    }
/* 123:    */  }
/* 124:    */  
/* 125:    */  public BroadphaseNativeType getShapeType() {
/* 126:126 */    return BroadphaseNativeType.CYLINDER_SHAPE_PROXYTYPE;
/* 127:    */  }
/* 128:    */  
/* 129:    */  public int getUpAxis() {
/* 130:130 */    return this.upAxis;
/* 131:    */  }
/* 132:    */  
/* 133:    */  public float getRadius() {
/* 134:134 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();return getHalfExtentsWithMargin(localStack.get$javax$vecmath$Vector3f()).x; } finally { localStack.pop$javax$vecmath$Vector3f();
/* 135:    */    }
/* 136:    */  }
/* 137:    */  
/* 138:    */  public String getName() {
/* 139:139 */    return "CylinderY";
/* 140:    */  }
/* 141:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.CylinderShape
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */