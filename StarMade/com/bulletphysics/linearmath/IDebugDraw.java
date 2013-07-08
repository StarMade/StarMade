/*   1:    */package com.bulletphysics.linearmath;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import javax.vecmath.Vector3f;
/*   5:    */
/*  44:    */public abstract class IDebugDraw
/*  45:    */{
/*  46:    */  public abstract void drawLine(Vector3f paramVector3f1, Vector3f paramVector3f2, Vector3f paramVector3f3);
/*  47:    */  
/*  48:    */  public void drawTriangle(Vector3f v0, Vector3f v1, Vector3f v2, Vector3f n0, Vector3f n1, Vector3f n2, Vector3f color, float alpha)
/*  49:    */  {
/*  50: 50 */    drawTriangle(v0, v1, v2, color, alpha);
/*  51:    */  }
/*  52:    */  
/*  53:    */  public void drawTriangle(Vector3f v0, Vector3f v1, Vector3f v2, Vector3f color, float alpha) {
/*  54: 54 */    drawLine(v0, v1, color);
/*  55: 55 */    drawLine(v1, v2, color);
/*  56: 56 */    drawLine(v2, v0, color);
/*  57:    */  }
/*  58:    */  
/*  59:    */  public abstract void drawContactPoint(Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat, int paramInt, Vector3f paramVector3f3);
/*  60:    */  
/*  61:    */  public abstract void reportErrorWarning(String paramString);
/*  62:    */  
/*  63:    */  public abstract void draw3dText(Vector3f paramVector3f, String paramString);
/*  64:    */  
/*  65:    */  public abstract void setDebugMode(int paramInt);
/*  66:    */  
/*  67:    */  public abstract int getDebugMode();
/*  68:    */  
/*  69:    */  public void drawAabb(Vector3f arg1, Vector3f arg2, Vector3f arg3) {
/*  70: 70 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f halfExtents = localStack.get$javax$vecmath$Vector3f(to);
/*  71: 71 */      halfExtents.sub(from);
/*  72: 72 */      halfExtents.scale(0.5F);
/*  73:    */      
/*  74: 74 */      Vector3f center = localStack.get$javax$vecmath$Vector3f(to);
/*  75: 75 */      center.add(from);
/*  76: 76 */      center.scale(0.5F);
/*  77:    */      
/*  80: 80 */      Vector3f edgecoord = localStack.get$javax$vecmath$Vector3f();
/*  81: 81 */      edgecoord.set(1.0F, 1.0F, 1.0F);
/*  82: 82 */      Vector3f pa = localStack.get$javax$vecmath$Vector3f();Vector3f pb = localStack.get$javax$vecmath$Vector3f();
/*  83: 83 */      for (int i = 0; i < 4; i++) {
/*  84: 84 */        for (int j = 0; j < 3; j++) {
/*  85: 85 */          pa.set(edgecoord.x * halfExtents.x, edgecoord.y * halfExtents.y, edgecoord.z * halfExtents.z);
/*  86: 86 */          pa.add(center);
/*  87:    */          
/*  88: 88 */          int othercoord = j % 3;
/*  89:    */          
/*  90: 90 */          VectorUtil.mulCoord(edgecoord, othercoord, -1.0F);
/*  91: 91 */          pb.set(edgecoord.x * halfExtents.x, edgecoord.y * halfExtents.y, edgecoord.z * halfExtents.z);
/*  92: 92 */          pb.add(center);
/*  93:    */          
/*  94: 94 */          drawLine(pa, pb, color);
/*  95:    */        }
/*  96: 96 */        edgecoord.set(-1.0F, -1.0F, -1.0F);
/*  97: 97 */        if (i < 3)
/*  98: 98 */          VectorUtil.mulCoord(edgecoord, i, -1.0F);
/*  99:    */      }
/* 100:    */    } finally {
/* 101:101 */      localStack.pop$javax$vecmath$Vector3f();
/* 102:    */    }
/* 103:    */  }
/* 104:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.linearmath.IDebugDraw
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */