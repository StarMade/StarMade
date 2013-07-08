/*   1:    */package com.bulletphysics.collision.shapes;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
/*   5:    */import com.bulletphysics.linearmath.Transform;
/*   6:    */import javax.vecmath.Vector3f;
/*   7:    */
/*  36:    */public class UniformScalingShape
/*  37:    */  extends ConvexShape
/*  38:    */{
/*  39:    */  private ConvexShape childConvexShape;
/*  40:    */  private float uniformScalingFactor;
/*  41:    */  
/*  42:    */  public UniformScalingShape(ConvexShape convexChildShape, float uniformScalingFactor)
/*  43:    */  {
/*  44: 44 */    this.childConvexShape = convexChildShape;
/*  45: 45 */    this.uniformScalingFactor = uniformScalingFactor;
/*  46:    */  }
/*  47:    */  
/*  48:    */  public float getUniformScalingFactor() {
/*  49: 49 */    return this.uniformScalingFactor;
/*  50:    */  }
/*  51:    */  
/*  52:    */  public ConvexShape getChildShape() {
/*  53: 53 */    return this.childConvexShape;
/*  54:    */  }
/*  55:    */  
/*  56:    */  public Vector3f localGetSupportingVertex(Vector3f vec, Vector3f out)
/*  57:    */  {
/*  58: 58 */    this.childConvexShape.localGetSupportingVertex(vec, out);
/*  59: 59 */    out.scale(this.uniformScalingFactor);
/*  60: 60 */    return out;
/*  61:    */  }
/*  62:    */  
/*  63:    */  public Vector3f localGetSupportingVertexWithoutMargin(Vector3f vec, Vector3f out)
/*  64:    */  {
/*  65: 65 */    this.childConvexShape.localGetSupportingVertexWithoutMargin(vec, out);
/*  66: 66 */    out.scale(this.uniformScalingFactor);
/*  67: 67 */    return out;
/*  68:    */  }
/*  69:    */  
/*  70:    */  public void batchedUnitVectorGetSupportingVertexWithoutMargin(Vector3f[] vectors, Vector3f[] supportVerticesOut, int numVectors)
/*  71:    */  {
/*  72: 72 */    this.childConvexShape.batchedUnitVectorGetSupportingVertexWithoutMargin(vectors, supportVerticesOut, numVectors);
/*  73: 73 */    for (int i = 0; i < numVectors; i++) {
/*  74: 74 */      supportVerticesOut[i].scale(this.uniformScalingFactor);
/*  75:    */    }
/*  76:    */  }
/*  77:    */  
/*  78:    */  public void getAabbSlow(Transform arg1, Vector3f arg2, Vector3f arg3)
/*  79:    */  {
/*  80: 80 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();this.childConvexShape.getAabbSlow(t, aabbMin, aabbMax);
/*  81: 81 */      Vector3f aabbCenter = localStack.get$javax$vecmath$Vector3f();
/*  82: 82 */      aabbCenter.add(aabbMax, aabbMin);
/*  83: 83 */      aabbCenter.scale(0.5F);
/*  84:    */      
/*  85: 85 */      Vector3f scaledAabbHalfExtends = localStack.get$javax$vecmath$Vector3f();
/*  86: 86 */      scaledAabbHalfExtends.sub(aabbMax, aabbMin);
/*  87: 87 */      scaledAabbHalfExtends.scale(0.5F * this.uniformScalingFactor);
/*  88:    */      
/*  89: 89 */      aabbMin.sub(aabbCenter, scaledAabbHalfExtends);
/*  90: 90 */      aabbMax.add(aabbCenter, scaledAabbHalfExtends);
/*  91: 91 */    } finally { localStack.pop$javax$vecmath$Vector3f();
/*  92:    */    }
/*  93:    */  }
/*  94:    */  
/*  95: 95 */  public void setLocalScaling(Vector3f scaling) { this.childConvexShape.setLocalScaling(scaling); }
/*  96:    */  
/*  98:    */  public Vector3f getLocalScaling(Vector3f out)
/*  99:    */  {
/* 100:100 */    this.childConvexShape.getLocalScaling(out);
/* 101:101 */    return out;
/* 102:    */  }
/* 103:    */  
/* 104:    */  public void setMargin(float margin)
/* 105:    */  {
/* 106:106 */    this.childConvexShape.setMargin(margin);
/* 107:    */  }
/* 108:    */  
/* 109:    */  public float getMargin()
/* 110:    */  {
/* 111:111 */    return this.childConvexShape.getMargin() * this.uniformScalingFactor;
/* 112:    */  }
/* 113:    */  
/* 114:    */  public int getNumPreferredPenetrationDirections()
/* 115:    */  {
/* 116:116 */    return this.childConvexShape.getNumPreferredPenetrationDirections();
/* 117:    */  }
/* 118:    */  
/* 119:    */  public void getPreferredPenetrationDirection(int index, Vector3f penetrationVector)
/* 120:    */  {
/* 121:121 */    this.childConvexShape.getPreferredPenetrationDirection(index, penetrationVector);
/* 122:    */  }
/* 123:    */  
/* 124:    */  public void getAabb(Transform arg1, Vector3f arg2, Vector3f arg3)
/* 125:    */  {
/* 126:126 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();this.childConvexShape.getAabb(t, aabbMin, aabbMax);
/* 127:127 */      Vector3f aabbCenter = localStack.get$javax$vecmath$Vector3f();
/* 128:128 */      aabbCenter.add(aabbMax, aabbMin);
/* 129:129 */      aabbCenter.scale(0.5F);
/* 130:    */      
/* 131:131 */      Vector3f scaledAabbHalfExtends = localStack.get$javax$vecmath$Vector3f();
/* 132:132 */      scaledAabbHalfExtends.sub(aabbMax, aabbMin);
/* 133:133 */      scaledAabbHalfExtends.scale(0.5F * this.uniformScalingFactor);
/* 134:    */      
/* 135:135 */      aabbMin.sub(aabbCenter, scaledAabbHalfExtends);
/* 136:136 */      aabbMax.add(aabbCenter, scaledAabbHalfExtends);
/* 137:137 */    } finally { localStack.pop$javax$vecmath$Vector3f();
/* 138:    */    }
/* 139:    */  }
/* 140:    */  
/* 141:141 */  public BroadphaseNativeType getShapeType() { return BroadphaseNativeType.UNIFORM_SCALING_SHAPE_PROXYTYPE; }
/* 142:    */  
/* 145:    */  public void calculateLocalInertia(float mass, Vector3f inertia)
/* 146:    */  {
/* 147:147 */    this.childConvexShape.calculateLocalInertia(mass, inertia);
/* 148:148 */    inertia.scale(this.uniformScalingFactor);
/* 149:    */  }
/* 150:    */  
/* 151:    */  public String getName()
/* 152:    */  {
/* 153:153 */    return "UniformScalingShape";
/* 154:    */  }
/* 155:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.UniformScalingShape
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */