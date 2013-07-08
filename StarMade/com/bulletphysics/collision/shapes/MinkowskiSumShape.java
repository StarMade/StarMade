/*   1:    */package com.bulletphysics.collision.shapes;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
/*   5:    */import com.bulletphysics.linearmath.MatrixUtil;
/*   6:    */import com.bulletphysics.linearmath.Transform;
/*   7:    */import javax.vecmath.Vector3f;
/*   8:    */
/*  37:    */public class MinkowskiSumShape
/*  38:    */  extends ConvexInternalShape
/*  39:    */{
/*  40: 40 */  private final Transform transA = new Transform();
/*  41: 41 */  private final Transform transB = new Transform();
/*  42:    */  private ConvexShape shapeA;
/*  43:    */  private ConvexShape shapeB;
/*  44:    */  
/*  45:    */  public MinkowskiSumShape(ConvexShape shapeA, ConvexShape shapeB) {
/*  46: 46 */    this.shapeA = shapeA;
/*  47: 47 */    this.shapeB = shapeB;
/*  48: 48 */    this.transA.setIdentity();
/*  49: 49 */    this.transB.setIdentity();
/*  50:    */  }
/*  51:    */  
/*  52:    */  public Vector3f localGetSupportingVertexWithoutMargin(Vector3f arg1, Vector3f arg2)
/*  53:    */  {
/*  54: 54 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*  55: 55 */      Vector3f supVertexA = localStack.get$javax$vecmath$Vector3f();
/*  56: 56 */      Vector3f supVertexB = localStack.get$javax$vecmath$Vector3f();
/*  57:    */      
/*  59: 59 */      tmp.negate(vec);
/*  60: 60 */      MatrixUtil.transposeTransform(tmp, tmp, this.transA.basis);
/*  61: 61 */      this.shapeA.localGetSupportingVertexWithoutMargin(tmp, supVertexA);
/*  62: 62 */      this.transA.transform(supVertexA);
/*  63:    */      
/*  65: 65 */      MatrixUtil.transposeTransform(tmp, vec, this.transB.basis);
/*  66: 66 */      this.shapeB.localGetSupportingVertexWithoutMargin(tmp, supVertexB);
/*  67: 67 */      this.transB.transform(supVertexB);
/*  68:    */      
/*  70: 70 */      out.sub(supVertexA, supVertexB);
/*  71: 71 */      return out; } finally { localStack.pop$javax$vecmath$Vector3f();
/*  72:    */    }
/*  73:    */  }
/*  74:    */  
/*  75:    */  public void batchedUnitVectorGetSupportingVertexWithoutMargin(Vector3f[] vectors, Vector3f[] supportVerticesOut, int numVectors)
/*  76:    */  {
/*  77: 77 */    for (int i = 0; i < numVectors; i++) {
/*  78: 78 */      localGetSupportingVertexWithoutMargin(vectors[i], supportVerticesOut[i]);
/*  79:    */    }
/*  80:    */  }
/*  81:    */  
/*  82:    */  public void getAabb(Transform t, Vector3f aabbMin, Vector3f aabbMax)
/*  83:    */  {
/*  84: 84 */    throw new UnsupportedOperationException("Not supported yet.");
/*  85:    */  }
/*  86:    */  
/*  87:    */  public BroadphaseNativeType getShapeType()
/*  88:    */  {
/*  89: 89 */    return BroadphaseNativeType.MINKOWSKI_SUM_SHAPE_PROXYTYPE;
/*  90:    */  }
/*  91:    */  
/*  92:    */  public void calculateLocalInertia(float mass, Vector3f inertia)
/*  93:    */  {
/*  94: 94 */    if (!$assertionsDisabled) throw new AssertionError();
/*  95: 95 */    inertia.set(0.0F, 0.0F, 0.0F);
/*  96:    */  }
/*  97:    */  
/*  98:    */  public String getName()
/*  99:    */  {
/* 100:100 */    return "MinkowskiSum";
/* 101:    */  }
/* 102:    */  
/* 103:    */  public float getMargin()
/* 104:    */  {
/* 105:105 */    return this.shapeA.getMargin() + this.shapeB.getMargin();
/* 106:    */  }
/* 107:    */  
/* 108:    */  public void setTransformA(Transform transA) {
/* 109:109 */    this.transA.set(transA);
/* 110:    */  }
/* 111:    */  
/* 112:    */  public void setTransformB(Transform transB) {
/* 113:113 */    this.transB.set(transB);
/* 114:    */  }
/* 115:    */  
/* 116:    */  public void getTransformA(Transform dest) {
/* 117:117 */    dest.set(this.transA);
/* 118:    */  }
/* 119:    */  
/* 120:    */  public void getTransformB(Transform dest) {
/* 121:121 */    dest.set(this.transB);
/* 122:    */  }
/* 123:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.MinkowskiSumShape
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */