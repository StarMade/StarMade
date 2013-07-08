/*  1:   */package com.bulletphysics.collision.shapes;
/*  2:   */
/*  3:   */import com.bulletphysics..Stack;
/*  4:   */import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
/*  5:   */import com.bulletphysics.linearmath.Transform;
/*  6:   */import javax.vecmath.Vector3f;
/*  7:   */
/* 34:   */public class SphereShape
/* 35:   */  extends ConvexInternalShape
/* 36:   */{
/* 37:   */  public SphereShape(float radius)
/* 38:   */  {
/* 39:39 */    this.implicitShapeDimensions.x = radius;
/* 40:40 */    this.collisionMargin = radius;
/* 41:   */  }
/* 42:   */  
/* 43:   */  public Vector3f localGetSupportingVertexWithoutMargin(Vector3f vec, Vector3f out)
/* 44:   */  {
/* 45:45 */    out.set(0.0F, 0.0F, 0.0F);
/* 46:46 */    return out;
/* 47:   */  }
/* 48:   */  
/* 49:   */  public void batchedUnitVectorGetSupportingVertexWithoutMargin(Vector3f[] vectors, Vector3f[] supportVerticesOut, int numVectors)
/* 50:   */  {
/* 51:51 */    for (int i = 0; i < numVectors; i++) {
/* 52:52 */      supportVerticesOut[i].set(0.0F, 0.0F, 0.0F);
/* 53:   */    }
/* 54:   */  }
/* 55:   */  
/* 56:   */  public void getAabb(Transform arg1, Vector3f arg2, Vector3f arg3)
/* 57:   */  {
/* 58:58 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f center = t.origin;
/* 59:59 */      Vector3f extent = localStack.get$javax$vecmath$Vector3f();
/* 60:60 */      extent.set(getMargin(), getMargin(), getMargin());
/* 61:61 */      aabbMin.sub(center, extent);
/* 62:62 */      aabbMax.add(center, extent);
/* 63:63 */    } finally { localStack.pop$javax$vecmath$Vector3f();
/* 64:   */    }
/* 65:   */  }
/* 66:   */  
/* 67:67 */  public BroadphaseNativeType getShapeType() { return BroadphaseNativeType.SPHERE_SHAPE_PROXYTYPE; }
/* 68:   */  
/* 70:   */  public void calculateLocalInertia(float mass, Vector3f inertia)
/* 71:   */  {
/* 72:72 */    float elem = 0.4F * mass * getMargin() * getMargin();
/* 73:73 */    inertia.set(elem, elem, elem);
/* 74:   */  }
/* 75:   */  
/* 76:   */  public String getName()
/* 77:   */  {
/* 78:78 */    return "SPHERE";
/* 79:   */  }
/* 80:   */  
/* 81:   */  public float getRadius() {
/* 82:82 */    return this.implicitShapeDimensions.x * this.localScaling.x;
/* 83:   */  }
/* 84:   */  
/* 85:   */  public void setMargin(float margin)
/* 86:   */  {
/* 87:87 */    super.setMargin(margin);
/* 88:   */  }
/* 89:   */  
/* 92:   */  public float getMargin()
/* 93:   */  {
/* 94:94 */    return getRadius();
/* 95:   */  }
/* 96:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.SphereShape
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */