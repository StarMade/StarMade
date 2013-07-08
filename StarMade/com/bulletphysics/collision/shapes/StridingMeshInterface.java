/*   1:    */package com.bulletphysics.collision.shapes;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.linearmath.VectorUtil;
/*   5:    */import javax.vecmath.Vector3f;
/*   6:    */
/*  35:    */public abstract class StridingMeshInterface
/*  36:    */{
/*  37:    */  protected final Vector3f scaling;
/*  38:    */  
/*  39: 39 */  public StridingMeshInterface() { this.scaling = new Vector3f(1.0F, 1.0F, 1.0F); }
/*  40:    */  
/*  41:    */  public void internalProcessAllTriangles(InternalTriangleIndexCallback arg1, Vector3f arg2, Vector3f arg3) {
/*  42: 42 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();int graphicssubparts = getNumSubParts();
/*  43: 43 */      Vector3f[] triangle = { localStack.get$javax$vecmath$Vector3f(), localStack.get$javax$vecmath$Vector3f(), localStack.get$javax$vecmath$Vector3f() };
/*  44:    */      
/*  45: 45 */      Vector3f meshScaling = getScaling(localStack.get$javax$vecmath$Vector3f());
/*  46:    */      
/*  47: 47 */      for (int part = 0; part < graphicssubparts; part++) {
/*  48: 48 */        VertexData data = getLockedReadOnlyVertexIndexBase(part);
/*  49:    */        
/*  50: 50 */        int i = 0; for (int cnt = data.getIndexCount() / 3; i < cnt; i++) {
/*  51: 51 */          data.getTriangle(i * 3, meshScaling, triangle);
/*  52: 52 */          callback.internalProcessTriangleIndex(triangle, part, i);
/*  53:    */        }
/*  54:    */        
/*  55: 55 */        unLockReadOnlyVertexBase(part);
/*  56:    */      }
/*  57: 57 */    } finally { localStack.pop$javax$vecmath$Vector3f();
/*  58:    */    } }
/*  59:    */  
/*  60: 60 */  private static class AabbCalculationCallback extends InternalTriangleIndexCallback { public final Vector3f aabbMin = new Vector3f(1.0E+030F, 1.0E+030F, 1.0E+030F);
/*  61: 61 */    public final Vector3f aabbMax = new Vector3f(-1.0E+030F, -1.0E+030F, -1.0E+030F);
/*  62:    */    
/*  63:    */    public void internalProcessTriangleIndex(Vector3f[] triangle, int partId, int triangleIndex) {
/*  64: 64 */      VectorUtil.setMin(this.aabbMin, triangle[0]);
/*  65: 65 */      VectorUtil.setMax(this.aabbMax, triangle[0]);
/*  66: 66 */      VectorUtil.setMin(this.aabbMin, triangle[1]);
/*  67: 67 */      VectorUtil.setMax(this.aabbMax, triangle[1]);
/*  68: 68 */      VectorUtil.setMin(this.aabbMin, triangle[2]);
/*  69: 69 */      VectorUtil.setMax(this.aabbMax, triangle[2]);
/*  70:    */    }
/*  71:    */  }
/*  72:    */  
/*  73:    */  public void calculateAabbBruteForce(Vector3f aabbMin, Vector3f aabbMax)
/*  74:    */  {
/*  75: 75 */    AabbCalculationCallback aabbCallback = new AabbCalculationCallback(null);
/*  76: 76 */    aabbMin.set(-1.0E+030F, -1.0E+030F, -1.0E+030F);
/*  77: 77 */    aabbMax.set(1.0E+030F, 1.0E+030F, 1.0E+030F);
/*  78: 78 */    internalProcessAllTriangles(aabbCallback, aabbMin, aabbMax);
/*  79:    */    
/*  80: 80 */    aabbMin.set(aabbCallback.aabbMin);
/*  81: 81 */    aabbMax.set(aabbCallback.aabbMax);
/*  82:    */  }
/*  83:    */  
/*  86:    */  public abstract VertexData getLockedVertexIndexBase(int paramInt);
/*  87:    */  
/*  90:    */  public abstract VertexData getLockedReadOnlyVertexIndexBase(int paramInt);
/*  91:    */  
/*  94:    */  public abstract void unLockVertexBase(int paramInt);
/*  95:    */  
/*  98:    */  public abstract void unLockReadOnlyVertexBase(int paramInt);
/*  99:    */  
/* 102:    */  public abstract int getNumSubParts();
/* 103:    */  
/* 105:    */  public abstract void preallocateVertices(int paramInt);
/* 106:    */  
/* 108:    */  public abstract void preallocateIndices(int paramInt);
/* 109:    */  
/* 111:    */  public Vector3f getScaling(Vector3f out)
/* 112:    */  {
/* 113:113 */    out.set(this.scaling);
/* 114:114 */    return out;
/* 115:    */  }
/* 116:    */  
/* 117:    */  public void setScaling(Vector3f scaling) {
/* 118:118 */    this.scaling.set(scaling);
/* 119:    */  }
/* 120:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.StridingMeshInterface
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */