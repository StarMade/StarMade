/*  1:   */package com.bulletphysics.collision.shapes;
/*  2:   */
/*  3:   */import com.bulletphysics..Stack;
/*  4:   */import javax.vecmath.Vector3f;
/*  5:   */
/* 32:   */public class CylinderShapeZ
/* 33:   */  extends CylinderShape
/* 34:   */{
/* 35:   */  public CylinderShapeZ(Vector3f halfExtents)
/* 36:   */  {
/* 37:37 */    super(halfExtents, false);
/* 38:38 */    this.upAxis = 2;
/* 39:39 */    recalcLocalAabb();
/* 40:   */  }
/* 41:   */  
/* 42:   */  public Vector3f localGetSupportingVertexWithoutMargin(Vector3f arg1, Vector3f arg2)
/* 43:   */  {
/* 44:44 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();return cylinderLocalSupportZ(getHalfExtentsWithoutMargin(localStack.get$javax$vecmath$Vector3f()), vec, out); } finally { localStack.pop$javax$vecmath$Vector3f();
/* 45:   */    }
/* 46:   */  }
/* 47:   */  
/* 48:   */  public void batchedUnitVectorGetSupportingVertexWithoutMargin(Vector3f[] arg1, Vector3f[] arg2, int arg3) {
/* 49:49 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f(); for (int i = 0; i < numVectors; i++)
/* 50:50 */        cylinderLocalSupportZ(getHalfExtentsWithoutMargin(localStack.get$javax$vecmath$Vector3f()), vectors[i], supportVerticesOut[i]);
/* 51:   */    } finally {
/* 52:52 */      localStack.pop$javax$vecmath$Vector3f();
/* 53:   */    }
/* 54:   */  }
/* 55:   */  
/* 56:56 */  public float getRadius() { .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();return getHalfExtentsWithMargin(localStack.get$javax$vecmath$Vector3f()).x; } finally { localStack.pop$javax$vecmath$Vector3f();
/* 57:   */    }
/* 58:   */  }
/* 59:   */  
/* 60:   */  public String getName() {
/* 61:61 */    return "CylinderZ";
/* 62:   */  }
/* 63:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.CylinderShapeZ
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */