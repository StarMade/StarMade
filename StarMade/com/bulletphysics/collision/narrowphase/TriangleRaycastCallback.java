package com.bulletphysics.collision.narrowphase;

import com.bulletphysics..Stack;
import com.bulletphysics.collision.shapes.TriangleCallback;
import com.bulletphysics.linearmath.VectorUtil;
import javax.vecmath.Vector3f;

public abstract class TriangleRaycastCallback
  extends TriangleCallback
{
  public final Vector3f from = new Vector3f();
  public final Vector3f field_332 = new Vector3f();
  public float hitFraction;
  
  public TriangleRaycastCallback(Vector3f from, Vector3f local_to)
  {
    this.from.set(from);
    this.field_332.set(local_to);
    this.hitFraction = 1.0F;
  }
  
  public void processTriangle(Vector3f[] arg1, int arg2, int arg3)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f vert0 = triangle[0];
      Vector3f vert1 = triangle[1];
      Vector3f vert2 = triangle[2];
      Vector3f v10 = localStack.get$javax$vecmath$Vector3f();
      v10.sub(vert1, vert0);
      Vector3f v20 = localStack.get$javax$vecmath$Vector3f();
      v20.sub(vert2, vert0);
      Vector3f triangleNormal = localStack.get$javax$vecmath$Vector3f();
      triangleNormal.cross(v10, v20);
      float dist = vert0.dot(triangleNormal);
      float dist_a = triangleNormal.dot(this.from);
      dist_a -= dist;
      float dist_b = triangleNormal.dot(this.field_332);
      dist_b -= dist;
      if (dist_a * dist_b >= 0.0F) {
        return;
      }
      float proj_length = dist_a - dist_b;
      float distance = dist_a / proj_length;
      if (distance < this.hitFraction)
      {
        float edge_tolerance = triangleNormal.lengthSquared();
        edge_tolerance *= -1.0E-004F;
        Vector3f point = new Vector3f();
        VectorUtil.setInterpolate3(point, this.from, this.field_332, distance);
        Vector3f v0p = localStack.get$javax$vecmath$Vector3f();
        v0p.sub(vert0, point);
        Vector3f v1p = localStack.get$javax$vecmath$Vector3f();
        v1p.sub(vert1, point);
        Vector3f cp0 = localStack.get$javax$vecmath$Vector3f();
        cp0.cross(v0p, v1p);
        if (cp0.dot(triangleNormal) >= edge_tolerance)
        {
          Vector3f v2p = localStack.get$javax$vecmath$Vector3f();
          v2p.sub(vert2, point);
          Vector3f cp1 = localStack.get$javax$vecmath$Vector3f();
          cp1.cross(v1p, v2p);
          if (cp1.dot(triangleNormal) >= edge_tolerance)
          {
            Vector3f cp2 = localStack.get$javax$vecmath$Vector3f();
            cp2.cross(v2p, v0p);
            if (cp2.dot(triangleNormal) >= edge_tolerance) {
              if (dist_a > 0.0F)
              {
                this.hitFraction = reportHit(triangleNormal, distance, partId, triangleIndex);
              }
              else
              {
                Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
                tmp.negate(triangleNormal);
                this.hitFraction = reportHit(tmp, distance, partId, triangleIndex);
              }
            }
          }
        }
      }
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public abstract float reportHit(Vector3f paramVector3f, float paramFloat, int paramInt1, int paramInt2);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.narrowphase.TriangleRaycastCallback
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */