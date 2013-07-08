/*   1:    */package com.bulletphysics.collision.narrowphase;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.collision.shapes.TriangleCallback;
/*   5:    */import com.bulletphysics.linearmath.VectorUtil;
/*   6:    */import javax.vecmath.Vector3f;
/*   7:    */
/*  36:    */public abstract class TriangleRaycastCallback
/*  37:    */  extends TriangleCallback
/*  38:    */{
/*  39: 39 */  public final Vector3f from = new Vector3f();
/*  40: 40 */  public final Vector3f to = new Vector3f();
/*  41:    */  public float hitFraction;
/*  42:    */  
/*  43:    */  public TriangleRaycastCallback(Vector3f from, Vector3f to)
/*  44:    */  {
/*  45: 45 */    this.from.set(from);
/*  46: 46 */    this.to.set(to);
/*  47: 47 */    this.hitFraction = 1.0F;
/*  48:    */  }
/*  49:    */  
/*  50:    */  public void processTriangle(Vector3f[] arg1, int arg2, int arg3) {
/*  51: 51 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f vert0 = triangle[0];
/*  52: 52 */      Vector3f vert1 = triangle[1];
/*  53: 53 */      Vector3f vert2 = triangle[2];
/*  54:    */      
/*  55: 55 */      Vector3f v10 = localStack.get$javax$vecmath$Vector3f();
/*  56: 56 */      v10.sub(vert1, vert0);
/*  57:    */      
/*  58: 58 */      Vector3f v20 = localStack.get$javax$vecmath$Vector3f();
/*  59: 59 */      v20.sub(vert2, vert0);
/*  60:    */      
/*  61: 61 */      Vector3f triangleNormal = localStack.get$javax$vecmath$Vector3f();
/*  62: 62 */      triangleNormal.cross(v10, v20);
/*  63:    */      
/*  64: 64 */      float dist = vert0.dot(triangleNormal);
/*  65: 65 */      float dist_a = triangleNormal.dot(this.from);
/*  66: 66 */      dist_a -= dist;
/*  67: 67 */      float dist_b = triangleNormal.dot(this.to);
/*  68: 68 */      dist_b -= dist;
/*  69:    */      
/*  70: 70 */      if (dist_a * dist_b >= 0.0F) {
/*  71: 71 */        return;
/*  72:    */      }
/*  73:    */      
/*  74: 74 */      float proj_length = dist_a - dist_b;
/*  75: 75 */      float distance = dist_a / proj_length;
/*  76:    */      
/*  81: 81 */      if (distance < this.hitFraction) {
/*  82: 82 */        float edge_tolerance = triangleNormal.lengthSquared();
/*  83: 83 */        edge_tolerance *= -1.0E-004F;
/*  84: 84 */        Vector3f point = new Vector3f();
/*  85: 85 */        VectorUtil.setInterpolate3(point, this.from, this.to, distance);
/*  86:    */        
/*  87: 87 */        Vector3f v0p = localStack.get$javax$vecmath$Vector3f();
/*  88: 88 */        v0p.sub(vert0, point);
/*  89: 89 */        Vector3f v1p = localStack.get$javax$vecmath$Vector3f();
/*  90: 90 */        v1p.sub(vert1, point);
/*  91: 91 */        Vector3f cp0 = localStack.get$javax$vecmath$Vector3f();
/*  92: 92 */        cp0.cross(v0p, v1p);
/*  93:    */        
/*  94: 94 */        if (cp0.dot(triangleNormal) >= edge_tolerance) {
/*  95: 95 */          Vector3f v2p = localStack.get$javax$vecmath$Vector3f();
/*  96: 96 */          v2p.sub(vert2, point);
/*  97: 97 */          Vector3f cp1 = localStack.get$javax$vecmath$Vector3f();
/*  98: 98 */          cp1.cross(v1p, v2p);
/*  99: 99 */          if (cp1.dot(triangleNormal) >= edge_tolerance) {
/* 100:100 */            Vector3f cp2 = localStack.get$javax$vecmath$Vector3f();
/* 101:101 */            cp2.cross(v2p, v0p);
/* 102:    */            
/* 103:103 */            if (cp2.dot(triangleNormal) >= edge_tolerance)
/* 104:    */            {
/* 105:105 */              if (dist_a > 0.0F) {
/* 106:106 */                this.hitFraction = reportHit(triangleNormal, distance, partId, triangleIndex);
/* 107:    */              }
/* 108:    */              else {
/* 109:109 */                Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 110:110 */                tmp.negate(triangleNormal);
/* 111:111 */                this.hitFraction = reportHit(tmp, distance, partId, triangleIndex);
/* 112:    */              }
/* 113:    */            }
/* 114:    */          }
/* 115:    */        }
/* 116:    */      }
/* 117:    */    } finally {
/* 118:118 */      localStack.pop$javax$vecmath$Vector3f();
/* 119:    */    }
/* 120:    */  }
/* 121:    */  
/* 122:    */  public abstract float reportHit(Vector3f paramVector3f, float paramFloat, int paramInt1, int paramInt2);
/* 123:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.narrowphase.TriangleRaycastCallback
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */