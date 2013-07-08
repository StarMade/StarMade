/*   1:    */package com.bulletphysics.collision.shapes;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.linearmath.MatrixUtil;
/*   5:    */import com.bulletphysics.linearmath.Transform;
/*   6:    */import com.bulletphysics.linearmath.VectorUtil;
/*   7:    */import javax.vecmath.Vector3f;
/*   8:    */
/*  38:    */public abstract class ConvexInternalShape
/*  39:    */  extends ConvexShape
/*  40:    */{
/*  41: 41 */  protected final Vector3f localScaling = new Vector3f(1.0F, 1.0F, 1.0F);
/*  42: 42 */  protected final Vector3f implicitShapeDimensions = new Vector3f();
/*  43: 43 */  protected float collisionMargin = 0.04F;
/*  44:    */  
/*  48:    */  public void getAabb(Transform t, Vector3f aabbMin, Vector3f aabbMax)
/*  49:    */  {
/*  50: 50 */    getAabbSlow(t, aabbMin, aabbMax);
/*  51:    */  }
/*  52:    */  
/*  53:    */  public void getAabbSlow(Transform arg1, Vector3f arg2, Vector3f arg3)
/*  54:    */  {
/*  55: 55 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();float margin = getMargin();
/*  56: 56 */      Vector3f vec = localStack.get$javax$vecmath$Vector3f();
/*  57: 57 */      Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
/*  58: 58 */      Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/*  59:    */      
/*  60: 60 */      for (int i = 0; i < 3; i++)
/*  61:    */      {
/*  62: 62 */        vec.set(0.0F, 0.0F, 0.0F);
/*  63: 63 */        VectorUtil.setCoord(vec, i, 1.0F);
/*  64:    */        
/*  65: 65 */        MatrixUtil.transposeTransform(tmp1, vec, trans.basis);
/*  66: 66 */        localGetSupportingVertex(tmp1, tmp2);
/*  67:    */        
/*  68: 68 */        trans.transform(tmp2);
/*  69:    */        
/*  70: 70 */        VectorUtil.setCoord(maxAabb, i, VectorUtil.getCoord(tmp2, i) + margin);
/*  71:    */        
/*  72: 72 */        VectorUtil.setCoord(vec, i, -1.0F);
/*  73:    */        
/*  74: 74 */        MatrixUtil.transposeTransform(tmp1, vec, trans.basis);
/*  75: 75 */        localGetSupportingVertex(tmp1, tmp2);
/*  76: 76 */        trans.transform(tmp2);
/*  77:    */        
/*  78: 78 */        VectorUtil.setCoord(minAabb, i, VectorUtil.getCoord(tmp2, i) - margin);
/*  79:    */      }
/*  80: 80 */    } finally { localStack.pop$javax$vecmath$Vector3f();
/*  81:    */    }
/*  82:    */  }
/*  83:    */  
/*  84: 84 */  public Vector3f localGetSupportingVertex(Vector3f arg1, Vector3f arg2) { .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f supVertex = localGetSupportingVertexWithoutMargin(vec, out);
/*  85:    */      
/*  86: 86 */      if (getMargin() != 0.0F) {
/*  87: 87 */        Vector3f vecnorm = localStack.get$javax$vecmath$Vector3f(vec);
/*  88: 88 */        if (vecnorm.lengthSquared() < 1.421086E-014F) {
/*  89: 89 */          vecnorm.set(-1.0F, -1.0F, -1.0F);
/*  90:    */        }
/*  91: 91 */        vecnorm.normalize();
/*  92: 92 */        supVertex.scaleAdd(getMargin(), vecnorm, supVertex);
/*  93:    */      }
/*  94: 94 */      return out; } finally { localStack.pop$javax$vecmath$Vector3f();
/*  95:    */    }
/*  96:    */  }
/*  97:    */  
/*  98: 98 */  public void setLocalScaling(Vector3f scaling) { this.localScaling.absolute(scaling); }
/*  99:    */  
/* 100:    */  public Vector3f getLocalScaling(Vector3f out)
/* 101:    */  {
/* 102:102 */    out.set(this.localScaling);
/* 103:103 */    return out;
/* 104:    */  }
/* 105:    */  
/* 106:    */  public float getMargin() {
/* 107:107 */    return this.collisionMargin;
/* 108:    */  }
/* 109:    */  
/* 110:    */  public void setMargin(float margin) {
/* 111:111 */    this.collisionMargin = margin;
/* 112:    */  }
/* 113:    */  
/* 114:    */  public int getNumPreferredPenetrationDirections()
/* 115:    */  {
/* 116:116 */    return 0;
/* 117:    */  }
/* 118:    */  
/* 119:    */  public void getPreferredPenetrationDirection(int index, Vector3f penetrationVector)
/* 120:    */  {
/* 121:121 */    throw new InternalError();
/* 122:    */  }
/* 123:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.ConvexInternalShape
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */